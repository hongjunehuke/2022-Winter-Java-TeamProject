
/*
 * DB 연결하는 예시 코드입니다.
 * 드라이버 설치, 연결은 아래 문서 참고했으니 필요하시면 참고해주세요!!
 * 설치: https://docs.microsoft.com/ko-kr/sql/connect/jdbc/download-microsoft-jdbc-driver-for-sql-server?view=sql-server-ver15
 * 연결: https://docs.microsoft.com/ko-kr/sql/connect/jdbc/step-3-proof-of-concept-connecting-to-sql-using-java?view=sql-server-ver15
 */

package recipe_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Db_connection_test {
	public static void main(String[] args) throws ClassNotFoundException {

		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String connectionUrl = "jdbc:sqlserver://127.0.0.1:1433;" 
								+ "database=Test_DB;" 		// 사용할 DB 이름
								+ "encrypt=true;trustServerCertificate=true;" 
								+ "user=sa;" 				// 관리자 계정 로그인
								+ "password=비밀번호;"; 
								
		try (Connection connection = DriverManager.getConnection(connectionUrl);) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM music"); 
			
			while (rs.next()) {
	            String s = rs.getString("singer");
	            String t = rs.getString("title");
	            System.out.println(s + " - " + t);                  
	        }  
			
			rs.close();
	        stmt.close();
	        connection.close();
	        
		} catch (SQLException e) { e.printStackTrace(); }
	}
}


