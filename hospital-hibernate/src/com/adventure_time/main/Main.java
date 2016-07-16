package com.adventure_time.main;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.hibernate.Cache;

import com.adventure_time.entity.Consulta;
import com.adventure_time.entity.Medico;
import com.adventure_time.entity.Paciente;
import com.adventure_time.factory.Factory;
import com.adventure_time.factory.ManageFactory;


public class Main {
	public static void main(String[] args) {

		// Consultas:

		ManageFactory ConsultaFactory = new ManageFactory(Consulta.class);

		Date data1 = Date.from(Instant.now().plus(2, ChronoUnit.DAYS));
		Date data2 = Date.from(Instant.now().plus(5, ChronoUnit.DAYS));
		Date data3 = Date.from(Instant.now().plus(10, ChronoUnit.DAYS));
		Consulta consulta1 = ConsultaFactory.insert(new Consulta(data1));
		Consulta consulta2 = ConsultaFactory.insert(new Consulta(data2));
		Consulta consulta3 = ConsultaFactory.insert(new Consulta(data3));

		// Medico:

		ManageFactory MedicoFactory = new ManageFactory(Medico.class);
		//CacheManager cm = CacheManager.getInstance();
		//cm.addCache(cache);

		Medico m1 = new Medico("Medico 1", "1");
		m1.getConsultas().add(consulta1);
		
		Medico m2 = new Medico("Medico 2", "2");
		m2.getConsultas().add(consulta2);
		
		Medico m3 = new Medico("Medico 3", "3");
		m3.getConsultas().add(consulta3);
		
		Medico medico1 = MedicoFactory.insert(m1);
		Medico medico2 = MedicoFactory.insert(m2);
		Medico medico3 = MedicoFactory.insert(m3);

		// Paciente:

		ManageFactory PacienteFactory = new ManageFactory(Paciente.class);

		Paciente p1 = new Paciente("Paciente 1", "1");
		p1.getConsultas().add(consulta1);
		
		Paciente p2 = new Paciente("Paciente 2", "2");
		p2.getConsultas().add(consulta2);
		
		Paciente p3 = new Paciente("Paciente 3", "3");
		p3.getConsultas().add(consulta3);
		
		Paciente paciente1 = PacienteFactory.insert(p1);
		Paciente paciente2 = PacienteFactory.insert(p2);
		Paciente paciente3 = PacienteFactory.insert(p3);
		
		MedicoFactory.list();
		
		

		Cache cache = Factory.getInstance().getCache();
	}

}