package ssm_smbms.service.bill;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import ssm_smbms.dao.BaseDao;
import ssm_smbms.dao.billDao;
import ssm_smbms.dao.billDaoImpl;
import ssm_smbms.pojo.Bill;

public class BillServiceImpl implements BillService {
	private billDao bDao;

	public BillServiceImpl() {
		bDao = new billDaoImpl();
	}

	@Override
	public boolean add(Bill bill) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Connection connection = null;
		connection = BaseDao.getConnection();
		try {
			if (bDao.add(connection, bill)) {
				System.out.println("增加成功！");
				flag = true;
			} else {
				System.out.println("增加失败！");
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
	public boolean update(Bill bill) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Connection connection = null;
		connection = BaseDao.getConnection();
		try {
			if (bDao.update(connection, bill)) {
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

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Connection connection = null;
		connection = BaseDao.getConnection();
		try {
			if (bDao.delete(connection, id)) {
				System.out.println("删除成功！");
				flag = true;
			} else {
				System.out.println("删除失败！");
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
	public List<Bill> getBillList(Bill bill) {
		// TODO Auto-generated method stub
		List<Bill> list = new ArrayList<Bill>();
		Connection connection = null;
		connection = BaseDao.getConnection();
		try {
			list = bDao.getBillList(connection, bill);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BaseDao.closeResource(connection, null, null);
		}

		return list;
	}

	// 通过ID查询账单
	@Override
	public Bill getBillById(String id) {
		// TODO Auto-generated method stub
		Connection connection = null;
		connection = BaseDao.getConnection();
		Bill bill = new Bill();
		try {
			bill = bDao.getBillById(connection, id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BaseDao.closeResource(connection, null, null);
		}
		return bill;
	}

}
