package Query;

public class InstallationQuery {
	public static String createBillTableQuery()
	{
		return "CREATE TABLE \"Bills\" (\r\n"
				+ "	\"BillID\"	INTEGER,\r\n"
				+ "	\"TotalSpend\"	DOUBLE,\r\n"
				+ "	\"CreationDate\"	datetime,\r\n"
				+ "	\"Priority\" INTEGER,\r\n"
				+ "	\"Description\" VARCHAR,\r\n"
				+ "	PRIMARY KEY(\"BillID\" AUTOINCREMENT)\r\n"
				+ ");";
	}
	public static String createIncomeTableQuery()
	{
		return "CREATE TABLE \"Income\" (\r\n"
				+ "	\"IncomeID\"	INTEGER,\r\n"
				+ "	\"TotalIncome\"	DOUBLE,\r\n"
				+ "	\"CreationDate\"	datetime,\r\n"
				+ "	\"Description\" VARCHAR,\r\n"
				+ "	PRIMARY KEY(\"IncomeID\" AUTOINCREMENT)\r\n"
				+ ");";
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
