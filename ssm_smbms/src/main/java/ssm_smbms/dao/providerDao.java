package ssm_smbms.dao;

import java.sql.Connection;
import java.util.List;

import ssm_smbms.pojo.Provider;

public interface providerDao {
	// 增加供应商
	public boolean add(Connection connection, Provider provider) throws Exception;

	// 修改供应商
	public boolean update(Connection connection, Provider provider) throws Exception;

	// 删除供应商
	public int delete(Connection connection, String id) throws Exception;

	// 获取供应商列表
	public List<Provider> getProviderList(Connection connection, String proName) throws Exception;

	// 根据id获取供应商详细信息
	public Provider getProviderById(Connection connection, String id) throws Exception;

}
