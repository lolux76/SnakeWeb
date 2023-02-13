package dao.dbconfig;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {
	
	public static void createDB(String url, String username, String password, Connection connection) {
		try {
			Statement createDB = connection.createStatement();
			String sqlDB = "CREATE DATABASE db;";
			createDB.executeUpdate(sqlDB);
			
			Statement createTable = connection.createStatement();
			String sqlTable = "CREATE TABLE `db`.`user` (`id` INT NOT NULL AUTO_INCREMENT , `uuid` VARCHAR(254) NOT NULL , `username` VARCHAR(254) NULL , `password` VARCHAR(254) NULL , `personnalBest` INT NULL , PRIMARY KEY (`id`), UNIQUE (`uuid`), UNIQUE (`username`)) ENGINE = InnoDB; ";
			createTable.executeUpdate(sqlTable);
			System.out.println("DB succesfully created"); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean doesDBExist(Connection connection) {
		ResultSet rs;
		try {
			rs = connection.getMetaData().getCatalogs();
			while(rs.next()) {
				String catalogs = rs.getString(1);
				if("db".equals(catalogs)) { //DB exist => return true
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
