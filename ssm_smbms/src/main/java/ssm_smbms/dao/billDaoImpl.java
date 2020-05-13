package ssm_smbms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.StringUtils;

import ssm_smbms.pojo.Bill;

public class billDaoImpl implements billDao {
	// 添加账单
	@Override
	public boolean add(Connection connection, Bill bill) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		int updateRows = 0;
		PreparedStatement pstm = null;
		if (connection != null) {
			String sql = "insert into smbms_bill(billCode,productName,productDesc,productUnit,productCount,totalPrice,isPayment,provideId,creationDate,createBy)"
					+ "value(?,?,?,?,?,?,?,?,?,?)";
			Object[] params = { bill.getBillCode(), bill.getProductName(), bill.getProductDesc(), bill.getProductUnit(),
					bill.getProductCount(), bill.getTotalPrice(), bill.getIsPayment(), bill.getProvideId(),
					bill.getCreationDate(), bill.getCreateBy() };
			updateRows = BaseDao.execute(connection, pstm, sql, params);
			if (updateRows > 0) {
				flag = true;
			}
		}
		BaseDao.closeResource(null, pstm, null);
		return flag;
	}

	// 修改账单
	@Override
	public boolean update(Connection connection, Bill bill) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		int updateRows = 0;
		PreparedStatement pstm = null;
		if (connection != null) {
			String sql = "update smbms_bill set billCode=?,productName=?,"
					+ "productUnit=?,productCount=?,totalPrice=?,isPayment=?,provideId=?" + " where id=?";
			Object[] params = { bill.getBillCode(), bill.getProductName(), bill.getProductUnit(),
					bill.getProductCount(), bill.getTotalPrice(), bill.getIsPayment(), bill.getProvideId(),
					bill.getId() };
			updateRows = BaseDao.execute(connection, pstm, sql, params);
			if (updateRows > 0) {
				flag = true;
			}
			BaseDao.closeResource(null, pstm, null);
		}

		return flag;
	}

	// 根据id删除账单
	@Override
	public boolean delete(Connection connection, String id) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		int updateRows = 0;
		PreparedStatement pstm = null;
		if (connection != null) {
			String sql = "delete from smbms_bill where id=?";
			Object[] params = { id };
			updateRows = BaseDao.execute(connection, pstm, sql, params);
			if (updateRows > 0) {
				flag = true;
			}
		}
		BaseDao.closeResource(null, pstm, null);
		return flag;
	}

	// 根据条件查询订单列表
	@Override
	public List<Bill> getBillList(Connection connection, Bill bill) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		List<Bill> billList = new ArrayList<Bill>();
		ResultSet rs = null;
		if (connection != null) {
			StringBuffer sql = new StringBuffer();
			sql.append("select * from smbms_bill where 1=1");
			List<Object> list = new ArrayList<Object>();

			if (!StringUtils.isNullOrEmpty(bill.getProductName())) {
				sql.append(" and productName like ? ");
				list.add("%" + bill.getProductName() + "%");
			}
			if (bill.getProvideId() > 0) {
				sql.append(" and provideId = ? ");
				list.add(bill.getProvideId());
			}
			if (bill.getIsPayment() > 0) {
				sql.append(" and isPayment = ? ");
				list.add(bill.getIsPayment());
			}
			Object[] params = list.toArray();
			rs = BaseDao.execute(connection, pstm, rs, sql.toString(), params);
			if (rs != null) {
				while (rs.next()) {
					bill.setId(rs.getInt("id"));
					bill.setBillCode(rs.getString("billCode"));
					bill.setProductName(rs.getString("productName"));
					bill.setProductDesc(rs.getString("productDesc"));
					bill.setProductUnit(rs.getString("productUnit"));
					bill.setProductCount(rs.getBigDecimal("productCount"));
					bill.setTotalPrice(rs.getBigDecimal("totalPrice"));
					bill.setIsPayment(rs.getInt("isPayment"));
					bill.setProvideId(rs.getInt("provideId"));
					bill.setCreationDate(rs.getDate("creationDate"));
					bill.setCreateBy(rs.getInt("createBy"));
					billList.add(bill);
				}
				BaseDao.closeResource(null, pstm, rs);
			}
		}
		return billList;
	}

	@Override
	public Bill getBillById(Connection connection, String id) throws Exception {
		// TODO Auto-generated method stub
		Bill bill = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		if (connection != null) {
			String sql = "select * from smbms_bill where id=?";
			Object[] params = { id };
			rs = BaseDao.execute(connection, pstm, rs, sql, params);
			if (rs.next()) {
				bill = new Bill();
				bill.setBillCode(rs.getString("billCode"));
				bill.setProductName(rs.getString("productName"));
				bill.setProductUnit(rs.getString("productUnit"));
				bill.setProductCount(rs.getBigDecimal("productCount"));
				bill.setTotalPrice(rs.getBigDecimal("totalPrice"));
				bill.setProvideId(rs.getInt("provideId"));
				bill.setIsPayment(rs.getInt("isPayment"));
			}
		}
		BaseDao.closeResource(null, pstm, rs);
		return bill;
	}

	@Override
	public int getBillCountByProviderId(Connection connection, String id) throws Exception {
		// TODO Auto-generated method stub
		int count = 0;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		if (connection != null) {
			String sql = "select count(*) as billCount from smbms_bill where id=?";
			Object[] params = { id };
			rs = BaseDao.execute(connection, pstm, rs, sql, params);
			if (rs.next()) {
				count = rs.getInt("billCount");
			}
			BaseDao.closeResource(null, pstm, rs);
		}
		return count;
	}

}
