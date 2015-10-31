package me.umov.bench;

import java.util.ArrayList;
import java.util.List;

import me.model.Custom;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateManager {

	private Session session;

	public void run(int lines) {
		System.out.println("Adding through insert");
		System.out.println("	Open connection");
		openDatabaseConnection();

		System.out.println("	cleanning bench table");
		cleanBenchTable();

		System.out.println("	generating list of objects to insert");
		List<Custom> buildCustoms = buildCustoms(lines);

		System.out.println("	starting inserting");
		long init = System.currentTimeMillis();
		performInsert(buildCustoms);
		System.out.println("	" + buildCustoms.size() + " inserted in " + (System.currentTimeMillis() - init) / 1000);

		cleanBenchTable();
	}

	protected void performInsert(List<Custom> buildCustoms) {
		Transaction beginTransaction = session.beginTransaction();
		for (Custom custom : buildCustoms) {
			session.save(custom);
		}
		beginTransaction.commit();
	}

	protected static List<Custom> buildCustoms(int lines) {
		List<Custom> customList = new ArrayList<Custom>();
		for (int i = 1; i <= lines; i++) {
			customList.add(new Custom(i, 1, "value_" + i, "value__" + i));
		}
		return customList;
	}

	protected void cleanBenchTable() {
		Transaction tx = session.beginTransaction();
		session.createSQLQuery("delete from custom;").executeUpdate();
		tx.commit();
	}

	protected void openDatabaseConnection() {
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		this.session = configuration.buildSessionFactory(serviceRegistry).openSession();
	}

}
