package Execution;

import java.sql.SQLException;

import Query.InstallationQuery;

public class Installation extends Execution{
	public static void createBillTable() throws SQLException
	{
		statement.executeUpdate(InstallationQuery.createBillTableQuery());
	}
	public static void createIncomeTable() throws SQLException
	{
		statement.executeUpdate(InstallationQuery.createIncomeTableQuery());
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		// TODO Auto-generated method stub
		Installation.getConnection();
		Installation.createBillTable();
		Installation.createIncomeTable();
	}

}
