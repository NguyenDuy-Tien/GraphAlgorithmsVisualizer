package Query;
import java.time.*;
public class BillQuery {
	public static String insertBillQuery(double TotalSpend, LocalDateTime creationDate, int priority, String description)
	{
		return "insert into Bills (\"TotalSpend\", \"CreationDate\", \"Priority\", \"Description\")\r\n"
				+ "values (" + TotalSpend + ", '" + DataConverter.convertDateTimeToString(creationDate) + "', " + priority + ", '" + description + "')";
	}
	public static String listAllBillFromTimeToTimeQuery(LocalDateTime from, LocalDateTime to)
	{
		return "select * from Bills\r\n"
				+ "where CreationDate BETWEEN '"
				+ DataConverter.convertDateTimeToString(from)
				+ "' AND '"
				+ DataConverter.convertDateTimeToString(to) + "'";
	}
	public static String getBillFromIDQuery(int ID)
	{
		return "select * from Bills\r\n"
				+ "where BillID = " + ID;
	}
	public static String getBillFromDescriptionQuery(String desc)
	{
		return "select * from Bills\r\n"
				+ "where Description LIKE '%" + desc + "%'\r\n"
				;
	}
	public static String getTotalSpendWithPriority(int priority)
	{
		return "select * from Bills\r\n"
				+ "where Priority = " + priority;
	}
	public static void main(String[] args)
	{
		System.out.println(BillQuery.listAllBillFromTimeToTimeQuery(LocalDateTime.MIN, LocalDateTime.MAX));
	}
}
