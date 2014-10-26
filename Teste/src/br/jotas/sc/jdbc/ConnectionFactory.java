package br.jotas.sc.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.management.RuntimeErrorException;

public class ConnectionFactory {
	
	public Connection getConnection(){
		try{
			return DriverManager.getConnection("jdbc:mysql://localhost/jotaslocadoraDB","root","123123");				
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

}
