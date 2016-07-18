package com.adventure_time.main;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.stat.Statistics;

import com.adventure_time.entity.Medico;
import com.adventure_time.factory.Factory;


public class Main {
	
	private static Medico m1 = null;
	private static Medico m2 = null;
	
	public static void main(String[] args) {

		System.out.println("Temp Dir: " + System.getProperty("java.io.tmpdir"));
		
		// Inicializando as Sessions 
		SessionFactory sessionFactory = Factory.getInstance();
		Statistics stats = sessionFactory.getStatistics();
		System.out.println("Stats enabled="+stats.isStatisticsEnabled());
		stats.setStatisticsEnabled(true);
		System.out.println("Stats enabled="+stats.isStatisticsEnabled());
		
		Session session = sessionFactory.openSession();
		Session otherSession = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Transaction otherTransaction = otherSession.beginTransaction();
		
		//System.out.println("\nTestando a primeira camada de cache: ");
		//testFirstLevelCache(session, otherSession, m1, m2, stats, transaction, otherTransaction);
		
		//stats.clear();
		
		//System.out.println("\n\nTestando a segunda camada de cache: ");
		//testSecondLevelCache(session, otherSession, m1, m2, stats);
			
		testConcorrency(session, otherSession, m1, m2, stats, transaction, otherTransaction);
		
		sessionFactory.close();		
	}
	
	private static void testFirstLevelCache(Session session, Session otherSession, Medico m1, Medico m2, Statistics stats, Transaction transaction, Transaction otherTransaction){
		int count = 1;
		m1 = (Medico) session.load(Medico.class, 130);
		printData(m1, stats, count++);
		
		m1 = (Medico) session.load(Medico.class, 130);
		printData(m1, stats, count++);
		
		m1 = new Medico("Rafael", "12345");
		session.save(m1);
		
		transaction.commit();
		otherTransaction.commit();
		
		int id = getMaxId(session, Medico.class);
		
		m1 = (Medico) session.load(Medico.class, id);
		printData(m1, stats, count++);
		
		m2 = (Medico) session.load(Medico.class, id);
		printData(m2, stats, count++);
		
		m2 = (Medico) otherSession.load(Medico.class, id);
		printData(m2, stats, count++);
		
	}
	
	private static void testSecondLevelCache(Session session, Session otherSession, Medico m1, Medico m2, Statistics stats){
		int count = 1;
		
		//System.out.println("\nFoi feita a limpeza na primeira camada do cache para que a segunda seja utilizada:");
		
		session.clear();
		
		m1 = (Medico) session.load(Medico.class, 130);
		printData(m1, stats, count++);
		
		m1 = (Medico) session.load(Medico.class, 131);
		printData(m1, stats, count++);
		
		m1 = (Medico) otherSession.load(Medico.class, 130);
		printData(m1, stats, count++);
		
		m1 = (Medico) session.load(Medico.class, 130);
		printData(m1, stats, count++);
		
		m1 = (Medico) session.load(Medico.class, 131);
		printData(m1, stats, count++);
		
		m1 = (Medico) otherSession.load(Medico.class, 130);
		printData(m1, stats, count++);
	}
	
	private static void testConcorrency(Session session, Session otherSession, Medico m1, Medico m2, Statistics stats, Transaction transaction, Transaction otherTransaction){
		int count = 1;
		
		System.out.println("Cria o médico Almeida:");
		m1 = new Medico("Almeida", "12345");
		session.save(m1);		
		transaction.commit();
		
		System.out.println(m1);

		System.out.println("Após o commit, pega o ID do Almeida:");		
		int id = getMaxId(session, Medico.class);
		
		System.out.println("A sessão 1 pega o Almeida:");		
		m1 = (Medico) session.load(Medico.class, id);
		
		System.out.println("A sessão 2 pega o Almeida:");
		m2 = (Medico) otherSession.load(Medico.class, id);
		
		System.out.println("A sessão 1 altera o nome de Almeida para Diego:");
		m1.setNome("Diego");
		session.update(m1);
		
		System.out.println("A sessão 2 altera o nome de Almeida para Chico:");
		m2.setNome("Chico");
		otherSession.save(m2);		
		
		System.out.println("Commit na sessão 1:");
		transaction = session.beginTransaction();
		transaction.commit();
		m1 = (Medico) session.load(Medico.class, id);
		System.out.println(m1);

		System.out.println("Commit na sessão 2:");
		otherTransaction = otherSession.beginTransaction();
		otherTransaction.commit();		
		m2 = (Medico) otherSession.load(Medico.class, id);
		System.out.println(m2);
	}
	
	private static int getMaxId(Session session, Class classe){
		Criteria criteria = session
			    .createCriteria(classe)
			    .setProjection(Projections.max("id"));
			return (Integer)criteria.uniqueResult();
	}
	
	private static void printStats(Statistics stats, int i) {
		System.out.println("***** " + i + " *****");
		System.out.println("Fetch Count = " + stats.getEntityFetchCount());
		System.out.println("Second Level Hit Count = " + stats.getSecondLevelCacheHitCount());
		System.out.println("Second Level Miss Count = "+ stats.getSecondLevelCacheMissCount());
		System.out.println("Second Level Put Count = " + stats.getSecondLevelCachePutCount());
		System.out.println("**********");
	}

	private static void printData(Medico medico, Statistics stats, int count) {
		System.out.println(count + ":: Nome = " + medico.getNome() + ", Telefone = " + medico.getTelefone());
		printStats(stats, count);
	}

}