package util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionUtils {
	
	private static String DRIVER;
	private static String URI;
	private static String USER;
	private static String PASSWORD;

	static {
		// 可以用于加载属性文件
		Properties props = new Properties();
		// 在类路径下加载属性文件
		try {
			InputStream is = ConnectionUtils.class.getClassLoader()
				.getResourceAsStream("DB.properties");
			props.load(is);
			DRIVER = props.getProperty("driver");
			URI = props.getProperty("uri");
			USER = props.getProperty("user");
			PASSWORD = props.getProperty("pwd");
			Class.forName(DRIVER);
			is.close();
		} catch (Exception e) {
		}
	}

	public static Connection getConnection() throws Exception {
		TimeUtil.message("Tarena:"+URI+"をリンクしています......");
		Connection con = DriverManager.getConnection(URI, USER, PASSWORD);
		return con;
	}
}
