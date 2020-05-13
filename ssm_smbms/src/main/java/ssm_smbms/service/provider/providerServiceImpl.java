package ssm_smbms.service.provider;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ssm_smbms.dao.BaseDao;
import ssm_smbms.dao.billDao;
import ssm_smbms.dao.billDaoImpl;
import ssm_smbms.dao.providerDao;
import ssm_smbms.dao.providerDaoImpl;
import ssm_smbms.pojo.Provider;

public class providerServiceImpl implements providerService {
	private providerDao prDao;
	private billDao bDao;

	public providerServiceImpl() {
		prDao = new providerDaoImpl();
		bDao = new billDaoImpl();
	}

	@Override
	public boolean add(Provider provider) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Connection connection = null;
		connection = BaseDao.getConnection();
		try {
			if (prDao.add(connection, provider)) {
				System.out.println("添加成功！");
				flag = true;
			} else {
				System.out.println("添加失败");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BaseDao.closeResource(connection, null, null);
		}
		return flag;
	}

	@Override
	public boolean update(Provider provider) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Connection connection = null;
		connection = BaseDao.getConnection();
		try {
			if (prDao.update(connection, provider)) {
				System.out.println("修改成功！");
				flag = true;
			} else {
				System.out.println("修改失败！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BaseDao.closeResource(connection, null, null);
		}
		return flag;
	}

	// 业务：根据ID删除供应商表的数据之前，需要先去订单表里进行查询操作
	// 若订单表中无该供应商的订单数据，则可以删除
	// 若有该供应商的订单数据，则不可以删除
	// billCount==0 删除 成功（0） 不成功（-1）
	// billCount>0 不能删除 查询成功（0） 查询不成功（-1）
	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		int billCount = -1;
		Connection connection = null;
		try {
			connection = BaseDao.getConnection();
			connection.setAutoCommit(false);
			billCount = bDao.getBillCountByProviderId(connection, id);
			if (billCount == 0) {
				prDao.delete(connection, id);
			}
			connection.commit();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			try {
				billCount = -1;
				connection.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} finally {
			BaseDao.closeResource(connection, null, null);
		}
		return billCount;
	}

	@Override
	public List<Provider> getProviderList(String proName) {
		// TODO Auto-generated method stub
		Connection connection = null;
		connection = BaseDao.getConnection();
		List<Provider> list = new ArrayList<Provider>();
		try {
			list = prDao.getProviderList(connection, proName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BaseDao.closeResource(connection, null, null);
		}

		return list;
	}

	@Override
	public Provider getProviderById(String id) {
		// TODO Auto-generated method stub
		Connection connection = null;
		connection = BaseDao.getConnection();
		Provider provider = new Provider();
		try {
			provider = prDao.getProviderById(connection, id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return provider;
	}

}
