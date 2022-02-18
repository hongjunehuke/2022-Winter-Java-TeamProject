package connectMySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {
  static final String TEST_DRIVER =
      "com.mysql.jdbc.Driver"; // jdbc 드라이버 주소
  static final String DB_URL =
      "jdbc:mysql://localhost:3306/스키마이름입력?useSSL=false";
  // DB 접속 주소
  // localhost는 접속하려는 데이터베이스 주소를 입력하시면 됩니다. localhost를
  // 사용하면 됩니다. 3306은 데이터베이스에 접속할때 사용하는 포터번호입니다.
  // 설치할때 설정한 포트번호를 사용합니다. databasename에는 접속하려는
  // database의 name을 입력해줍니다.
  static final String USERNAME = "root";             // DB ID
  static final String PASSWORD = "DB 비밀번호 입력"; // DB Password

  public static Connection conn = null;
  public static Statement stmt = null;
  public static ResultSet rs = null;

  public static void connection() {
    System.out.print("User Table 접속 : ");
    try {
      Class.forName(TEST_DRIVER);
      // Class 클래스의 forName()함수를 이용해서 해당 클래스를 메모리로 로드
      // 하는 것입니다. URL, ID, password를 입력하여 데이터베이스에 접속합니다.
      conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
      // 접속결과를 출력합니다.
      if (conn != null) {
        System.out.println("성공");
      } else {
        System.out.println("실패");
      }
    } catch (ClassNotFoundException e) {
      System.out.println("Class Not Found Exection");
      e.printStackTrace();
    } catch (SQLException e) {
      System.out.println("SQL Exception : " + e.getMessage());
      e.printStackTrace();
    }
  }

  public static void main(String[] args) throws SQLException {
    // MySql에 사용하는여러 객체를 만들어줍니다.
    connection();
    String sql = "SELECT * FROM recipe";
    stmt = conn.createStatement();
    rs = stmt.executeQuery(sql);

    while (rs.next()) {
      String id = rs.getString(1);
      String name = rs.getString(2);
      String cate1 = rs.getString(3);
      String cate2 = rs.getString(4);
      String recipe = rs.getString(7);

      System.out.println("--------------------------");
      System.out.println(id + "번 레시피 명 : " + name);
      System.out.println("--------------------------");
      System.out.println("카테고리1 : " + cate1 + " / 카테고리2 : " + cate2);
      System.out.println(recipe);
      System.out.println(
          "========================================================");
    }

    if (rs != null)
      rs.close();
    if (stmt != null)
      stmt.close();
    if (conn != null)
      conn.close();
  }
}
