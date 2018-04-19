package jessie.booksmanage.test.base;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Locale;
public class DBUtil {
	public static Connection getConnection() {
		Connection conn = null;
		try {	
	  		  InputStream in = new BufferedInputStream( 
					  new FileInputStream("resources/jdbc.properties"));       
	           Properties p = new Properties();
	           p.load(in);
	           
 			Class.forName(p.getProperty("driver"));
			conn = DriverManager.getConnection(p.getProperty("url"), 
					p.getProperty("username"), p.getProperty("password"));
		} catch (ClassNotFoundException e) {
			System.out.println("数据库驱动加载失败,堆栈轨迹如下");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("数据库连接创建失败,堆栈轨迹如下");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public static void closeAll(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		if (null != rs) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("数据库操作的ResultSet关闭失败,堆栈轨迹如下");
				e.printStackTrace();
			}
		}
		if (null != pstmt) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				System.out.println("数据库操作的PreparedStatement关闭失败,堆栈轨迹如下");
				e.printStackTrace();
			}
		}
		close(conn);
	}

	public static void close(Connection conn) {
		if (null != conn) {
			try {
				conn.close();
				if (conn.isClosed()) {
					System.out.println("此数据库连接已关闭-->" + conn);
				} else {
					System.out.println("此数据库连接关闭失败-->" + conn);
				}
			} catch (SQLException e) {
				System.out.println("数据库连接关闭失败,堆栈轨迹如下");
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) 
	{
		Connection c = DBUtil.getConnection();
		System.out.print(c.toString());		
		
	}

}
