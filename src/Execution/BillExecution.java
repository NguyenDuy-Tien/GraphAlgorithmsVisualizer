package Execution;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import Query.BillQuery;

public class BillExecution extends Execution{
	public static void insertBill(double TotalSpend, LocalDateTime creationDate, int priority, String description) throws SQLException
	{
		statement.executeUpdate(BillQuery.insertBillQuery(TotalSpend, creationDate, priority, description));
	}
	public static ResultSet listAllBillFromTimeToTime(LocalDateTime from, LocalDateTime to) throws SQLException
	{
		return statement.executeQuery(BillQuery.listAllBillFromTimeToTimeQuery(from, to));
	}
	public static ResultSet getBillFromID(int ID) throws SQLException
	{
		return statement.executeQuery(BillQuery.getBillFromIDQuery(ID));
	}
	public static ResultSet getBillFromDescription(String desc) throws SQLException
	{
		return statement.executeQuery(BillQuery.getBillFromDescriptionQuery(desc));
	}
	public static double getTotalSpendWithPriority(int priority) throws SQLException
	{
		return statement.executeQuery(BillQuery.getTotalSpendWithPriorityQuery(priority)).getDouble(1);
	}
	public static void main(String args[]) throws ClassNotFoundException, SQLException
	{
		BillExecution.getConnection();
		BillExecution.insertBill(29.5, LocalDateTime.of(2013, 1, 21, 4, 5), 2, "Another Bill");
	}
}
