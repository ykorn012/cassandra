package com.isd.poc.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

class CreateData {
	public static void main(String args[]) throws Exception {
		String query = "Truncate emp;"; // Query
		Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build(); // Creating Cluster object
		Session session = cluster.connect("tutorialspoint"); // Creating Session object
		session.execute(query); // Executing the query
		System.out.println("Table truncated");
		
		// queries
		String query1 = "INSERT INTO emp (emp_id, emp_name, emp_city, emp_phone,  emp_sal)"
				+ " VALUES(1,'ram', 'Hyderabad', 9848022338, 50000);";
		String query2 = "INSERT INTO emp (emp_id, emp_name, emp_city, emp_phone, emp_sal)"
				+ " VALUES(2,'robin', 'Hyderabad', 9848022339, 40000);";
		String query3 = "INSERT INTO emp (emp_id, emp_name, emp_city, emp_phone, emp_sal)"
				+ " VALUES(3,'rahman', 'Chennai', 9848022330, 45000);";
		
		// Executing the query
		session.execute(query1);
		session.execute(query2);
		session.execute(query3);
		System.out.println("Data created");
	}
}
