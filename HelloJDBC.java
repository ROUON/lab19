
import java.io.BufferedWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class HelloJDBC {
private static final String URL = "jdbc:oracle:thin:@10.120.26.2:1521:xe";
private static final String USER ="david";
private static final String PASSWORD ="123456";
private static final String SQL = "INSERT INTO DEPARTMENT (DEPTNO,DNAME,LOC) VALUES (?,?,?)";
private static final String QUERY_SQL ="select * from department";
	public static void main(String[] args) {
		Connection con =null;
		PreparedStatement pstmt =null;
		Statement stmt =null;
		ResultSet rs = null;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("輸入部門編號");
		int deptno = sc.nextInt();
		System.out.println("輸入部門名稱");
		String dname = sc.next();
		System.out.println("輸入部門地址");
		String loc = sc.next();
		sc.close();
		try {
			//ST1
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("LOAD OK!");
			
			//ST2
			 con = DriverManager.getConnection(URL,USER,PASSWORD);
			System.out.println("ONLINE!");
			
		 //ST3
			//stmt = con.createStatement();
			//int count= stmt.executeUpdate(SQL);
			//System.out.println(count+"筆資料新增完成");
			
			//st4
//			stmt = con.createStatement();
//			rs = stmt.executeQuery(QUERY_SQL);
//			while (rs.next()) {
//				int deptno1 =rs.getInt("DEPTNO");
//				String dname1 =rs.getString("DNAME");
//				String loc1 =rs.getString("LOC");
//				System.out.println("DEPTNO = "+deptno1);
//				System.out.println("DNAME = "+dname1);
//				System.out.println("LOC = "+loc);
//				System.out.println("=============================");
			//exst4
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1,deptno);
			pstmt.setString(2,dname);
			pstmt.setString(3,loc);
			pstmt.executeUpdate();
			
	
			
			
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
		}

	}

}
