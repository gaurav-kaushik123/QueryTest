import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.Connection;

public class QueryTest {

	public static void main(String[] args) {
		
		try {
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
			
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "sysgitc");

			Statement stmt = conn.createStatement();
			
			Scanner scan1 = new Scanner(System.in);
			Scanner scan2 = new Scanner(System.in);
			Scanner scan3 = new Scanner(System.in);
			
			System.out.println("Enter new emp salary : " );
			double empSalary = scan2.nextDouble();
			
			System.out.println("Enter new emp job    : " );
			String empJob = scan3.nextLine(); 
			
			System.out.println("Enter new emp number : " );
			int empNumber = scan1.nextInt();
			
			ResultSet resultSet = stmt.executeQuery("SELECT * FROM emp"
					+ " where empno="+empNumber);
			
			if(resultSet.getRow()==0)
			{
				throw new EmpNotExist("The employee with the given number does not exist.");
			}
			
			else
			{
			PreparedStatement prstmt = conn.prepareStatement("UPDATE EMP SET SAL=? and JOB=? WHERE EMPNO=?");
				
			
			prstmt.setDouble(1, empSalary);
			prstmt.setString(2, empJob);
			prstmt.setInt(3, empNumber);
			
			prstmt.executeUpdate();
			prstmt.close();
			}
			
			stmt.close();
			 conn.close(); 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}




