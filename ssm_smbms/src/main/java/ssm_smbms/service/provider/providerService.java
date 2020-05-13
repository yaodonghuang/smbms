package ssm_smbms.service.provider;

import java.util.List;

import ssm_smbms.pojo.Provider;

public interface providerService {
	// 增加供应商
	public boolean add(Provider provider);

	// 修改供应商
	public boolean update(Provider provider);

	// 删除供应商
	public int delete(String id);

	// 获取供应商列表
	public List<Provider> getProviderList(String proName);

	// 根据id获取供应商详细信息
	public Provider getProviderById(String id);

}
