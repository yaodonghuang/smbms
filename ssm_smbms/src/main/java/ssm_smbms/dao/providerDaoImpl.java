package ssm_smbms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ssm_smbms.pojo.Provider;

public class providerDaoImpl implements providerDao {
	// 增加
	@Override
	public boolean add(Connection connection, Provider provider) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		int updateRows = 0;
		PreparedStatement pstm = null;
		if (connection != null) {
			String sql = "insert into smbms_provider(proCode,proName,proDesc,proContact,proPhone,proAddress,proFax,createBy,createDate)"
					+ "value(?,?,?,?,?,?,?,?,?)";
			Object[] params = { provider.getProCode(), provider.getProName(), provider.getProDesc(),
					provider.getProContact(), provider.getProPhone(), provider.getProAddress(), provider.getProFax(),
					provider.getCreateBy(), provider.getCreateDate() };
			updateRows = BaseDao.execute(connection, pstm, sql, params);
			if (updateRows > 0) {
				flag = true;
			}
		}
		BaseDao.closeResource(null, pstm, null);
		return flag;
	}

	// 修改
	@Override
	public boolean update(Connection connection, Provider provider) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		int updateRows = 0;
		PreparedStatement pstm = null;
		if (connection != null) {
			String sql = "update smbms_provider set proCode=?,proName=?,proContact=?"
					+ ",proPhone=?,proAddress=?,proFax=?,proDesc=? where id=?";
			Object[] params = { provider.getProCode(), provider.getProName(), provider.getProContact(),
					provider.getProPhone(), provider.getProAddress(), provider.getProFax(), provider.getProDesc(),
					provider.getId() };
			updateRows = BaseDao.execute(connection, pstm, sql, params);
			if (updateRows > 0) {
				flag = true;
			}
		}
		BaseDao.closeResource(null, pstm, null);
		return flag;
	}

	// 删除
	@Override
	public int delete(Connection connection, String id) throws Exception {
		// TODO Auto-generated method stub
		int updateRows = 0;
		PreparedStatement pstm = null;
		if (connection != null) {
			String sql = "delete from smbms_provider where id=?";
			Object[] params = { id };
			updateRows = BaseDao.execute(connection, pstm, sql, params);
			BaseDao.closeResource(null, pstm, null);
		}

		return updateRows;
	}

	// 获取供应商列表
	@Override
	public List<Provider> getProviderList(Connection connection, String proName) throws Exception {
		// TODO Auto-generated method stub
		List<Provider> list = new ArrayList<Provider>();
		PreparedStatement pstm = null;
		Provider provider = null;
		ResultSet rs = null;
		if (connection != null) {
			String sql = "select * from smbms_provider where proName like ?";
			Object[] params = { "%" + proName + "%" };
			rs = BaseDao.execute(connection, pstm, rs, sql, params);
			while (rs.next()) {
				provider = new Provider();
				provider.setId(rs.getInt("id"));
				provider.setProCode(rs.getString("proCode"));
				provider.setProName(rs.getString("proName"));
				provider.setProPhone(rs.getString("proPhone"));
				provider.setProFax(rs.getString("proFax"));
				provider.setProContact(rs.getString("proContact"));
				provider.setCreateDate(rs.getDate("createDate"));
				list.add(provider);
			}
		}
		BaseDao.closeResource(null, pstm, rs);
		return list;
	}

	// 通过id获取供应商
	@Override
	public Provider getProviderById(Connection connection, String id) throws Exception {
		// TODO Auto-generated method stub
		Provider provider = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select * from smbms_provider where id=?";
		Object[] params = { id };
		rs = BaseDao.execute(connection, pstm, rs, sql, params);
		if (rs.next()) {
			provider = new Provider();
			provider.setId(rs.getInt("id"));
			provider.setProCode(rs.getString("proCode"));
			provider.setProName(rs.getString("proName"));
			provider.setProContact(rs.getString("proContact"));
			provider.setProPhone(rs.getString("proPhone"));
			provider.setProAddress(rs.getString("proAddress"));
			provider.setProFax(rs.getString("proFax"));
			provider.setProDesc(rs.getString("proDesc"));
		}
		BaseDao.closeResource(null, pstm, rs);
		return provider;
	}

}
