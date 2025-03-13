package jp.co.sfrontier.ojt.employee.db.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * MySQLとの接続を行うクラス
 */
public class DatabaseConnector {

	private static Properties properties = new Properties();

	/**
	 * JDBCドライバのロードする静的初期化ブロック
	 */
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 設定ファイルからDB接続に必要な設定値を読み込み、DBに接続するメソッド
	 * @return conn(DB接続)
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		properties = new Properties();

		String propertyFile = System.getenv("DB_PROPERTIY_FILE");

		//環境変数の値によって読み込む設定ファイルを切り替える
		if (propertyFile.equals("production")) {
			try (InputStream is = getClass().getResourceAsStream("/server.properties");
					BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
				properties.load(br);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (propertyFile.equals("localhost")) {
			try (InputStream is = getClass().getResourceAsStream("/localhost.properties");
					BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
				properties.load(br);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		//読み込んだ設定ファイルからDB接続情報を取得する
		String url = properties.getProperty("db.url");
		String user = properties.getProperty("db.user");
		String password = properties.getProperty("db.password");

		Connection conn = null;
		// DB接続を返す。接続に失敗した場合はnullが返される。
		conn = DriverManager.getConnection(url, user, password);
		return conn;
	}
}
