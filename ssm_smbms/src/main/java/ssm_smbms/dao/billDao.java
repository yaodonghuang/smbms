package ssm_smbms.dao;

import java.sql.Connection;
import java.util.List;

import ssm_smbms.pojo.Bill;

public interface billDao {
	// 增加订单
	public boolean add(Connection connection, Bill bill) throws Exception;

	// 修改订单
	public boolean update(Connection connection, Bill bill) throws Exception;

	// 删除订单
	public boolean delete(Connection connection, String id) throws Exception;

	// 根据条件查询订单列表
	public List<Bill> getBillList(Connection connection, Bill bill) throws Exception;

	// 根据订单id查看订单明细
	public Bill getBillById(Connection connection, String id) throws Exception;

	// 根据供应商id获取订单数
	public int getBillCountByProviderId(Connection connection, String id) throws Exception;

}
