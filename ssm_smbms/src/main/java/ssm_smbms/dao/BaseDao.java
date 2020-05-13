package ssm_smbms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ssm_smbms.tools.ConfigManager;

//操作数据库的基类--静态类
public class BaseDao {

	public static Connection getConnection() {
		String driver = ConfigManager.getInstance().getValue("jdbc.driver.class");
		String url = ConfigManager.getInstance().getValue("jdbc.connection.url");
		String username = ConfigManager.getInstance().getValue("jdbc.connection.username");
		String password = ConfigManager.getInstance().getValue("jdbc.connection.password");
		Connection connection = null;

		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return connection;
	}

	// 关闭资源
	public static boolean closeResource(Connection connection, PreparedStatement pstm, ResultSet rs) {
		boolean flag = false;
		if (rs != null) {
			try {
				rs.close();
				rs = null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				flag = false;
			}

		}
		if (pstm != null) {
			try {
				pstm.close();
				pstm = null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				flag = false;
			}

		}
		if (connection != null) {
			try {
				connection.close();
				connection = null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				flag = false;
			}

		}
		return flag;
	}

	// 查询操作
	public static ResultSet execute(Connection connection, PreparedStatement pstm, ResultSet rs, String sql,
			Object[] params) throws Exception {
		pstm = connection.prepareStatement(sql);
		for (int i = 0; i < params.length; i++) {
			pstm.setObject(i + 1, params[i]);
			rs = pstm.executeQuery();
		}
		return rs;
	}

	// 更新(增删改)
	public static int execute(Connection connection, PreparedStatement pstm, String sql, Object[] params)
			throws Exception {
		int updateRows = 0;
		pstm = connection.prepareStatement(sql);
		for (int i = 0; i < params.length; i++) {
			pstm.setObject(i + 1, params[i]);
		}
		updateRows = pstm.executeUpdate();
		return updateRows;
	}
}
