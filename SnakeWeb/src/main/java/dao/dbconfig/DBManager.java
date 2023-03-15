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
			
			Statement createTables = connection.createStatement();
			String sqlUserTable = "CREATE TABLE `db`.`user` (`id` INT NOT NULL AUTO_INCREMENT , `uuid` VARCHAR(254) NOT NULL , `username` VARCHAR(254) NULL , `password` VARCHAR(254) NULL , `personnalBest` INT NULL , PRIMARY KEY (`id`), UNIQUE (`uuid`), UNIQUE (`username`)) ENGINE = InnoDB; ";
			createTables.executeUpdate(sqlUserTable);
			
			String sqlGameTable = "CREATE TABLE `db`.`games` (`id` INT NOT NULL AUTO_INCREMENT , `uuid` VARCHAR(254) NOT NULL , `player_one_uuid` VARCHAR(254) NULL , `player_two_uuid` VARCHAR(254) NULL , `player_one_score` INT NOT NULL , `player_two_score` INT NOT NULL , PRIMARY KEY (`id`) , UNIQUE (`uuid`)) ENGINE = InnoDB;";
			createTables.executeUpdate(sqlGameTable);
			
			String sqlForeignKeys = "ALTER TABLE `games` ADD CONSTRAINT `player_one_uuid` FOREIGN KEY (`player_one_uuid`) REFERENCES `user`(`uuid`) ON DELETE SET NULL ON UPDATE CASCADE; ALTER TABLE `games` ADD CONSTRAINT `player_two_uuid` FOREIGN KEY (`player_two_uuid`) REFERENCES `user`(`uuid`) ON DELETE SET NULL ON UPDATE CASCADE;";
			createTables.executeUpdate(sqlForeignKeys);
			
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
