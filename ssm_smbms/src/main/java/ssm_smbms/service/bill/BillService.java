package ssm_smbms.service.bill;

import java.util.List;

import ssm_smbms.pojo.Bill;

public interface BillService {
	// 增加订单
	public boolean add(Bill bill);

	// 修改订单
	public boolean update(Bill bill);

	// 删除订单
	public boolean delete(String id);

	// 根据条件查询订单列表
	public List<Bill> getBillList(Bill bill);

	// 根据订单id查看订单明细
	public Bill getBillById(String id);

}
