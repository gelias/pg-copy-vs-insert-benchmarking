package me.umov.copy;

import me.umov.bench.HibernateManager;
import me.umov.bench.PgsqlCopyManager;

public class InsertVsCopyBencharking {

	private static final int LINES = 90000;
	public static void main(String[] args) throws Exception {

		HibernateManager hibernate = new HibernateManager();
		PgsqlCopyManager copy = new PgsqlCopyManager();

		hibernate.run(LINES);
		copy.run(LINES);
	}
	
}
