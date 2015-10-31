package me.umov.bench;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;

public class PgsqlCopyManager {

	private CopyManager copyManager;

	public void run(int lines) throws ClassNotFoundException, SQLException, IOException {
		System.out.println("Adding through copy");
		System.out.println("	Open connection");
		openConnection();
		System.out.println("	generating data to copy");
		InputStream is = buildDataToCopy(lines);
		System.out.println("	starting copy");
		long init = System.currentTimeMillis();
		copyData(is);
		System.out.println("	" + lines + " lines copied in " + (System.currentTimeMillis() - init) / 1000);

	}

	protected void copyData(InputStream is) throws SQLException, IOException {
		copyManager.copyIn("COPY public.custom(id, token_a, token_b, ite_id) FROM STDIN WITH DELIMITER '^'", is);
	}

	protected InputStream buildDataToCopy(int lines) {
		InputStream is = new ByteArrayInputStream(getValues(lines).getBytes());
		return is;
	}

	protected void openConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gelias", "gelias", "postgres");

		this.copyManager = new CopyManager((BaseConnection) con);
	}

	protected static String getValues(int lines) {
		StringBuilder builder = new StringBuilder(1000);
		for (int i = 1; i <= lines; i++) {
			builder.append(String.format("%s^'value_%s'^'value_%s'^1\n", i, i, i));
		}
		return builder.toString();
	}

}
