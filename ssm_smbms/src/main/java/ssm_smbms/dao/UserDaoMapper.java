package ssm_smbms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import ssm_smbms.pojo.User;

public interface UserDaoMapper {
	// 增加
	public int add(User user) throws Exception;

	// 通过userCode获取User
	public User getLoginUser(@Param("userCode") String userCode) throws Exception;

	// 通过userName模糊查询-userList
	public List<User> getUserList(@Param("userName") String userName) throws Exception;

	// 删除
	public int delete(@Param("id") Integer id) throws Exception;

	// 修改
	public int update(User user) throws Exception;

	// 查询
	public User query(User user) throws Exception;

	// 通过id获取用户信息
	public User getUserById(@Param("userId") String id) throws Exception;

	// 修改当前用户密码
	public boolean updatePwd(@Param("id") int id, @Param("pwd") String pwd) throws Exception;
}
