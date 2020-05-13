package ssm_smbms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import ssm_smbms.pojo.Work;

public interface workDaoMapper {
	// 创建工程记录
	public boolean add(Work work) throws Exception;

	// 修改工程记录
	public boolean update(Work work) throws Exception;

	// 删除工程记录
	public int delete(String id) throws Exception;

	// 获取工程记录列表
	public List<Work> getWorkList(@Param("workName")String workName) throws Exception;

	// 根据id获取工程详细信息
	public Work getWorkById(String id) throws Exception;
}
