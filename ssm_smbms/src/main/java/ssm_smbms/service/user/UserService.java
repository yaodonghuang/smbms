package ssm_smbms.service.user;

import java.util.List;

import ssm_smbms.pojo.User;

public interface UserService {
	public boolean add(User user) throws Exception;

	public boolean delete(Integer id) throws Exception;

	public boolean update(User user) throws Exception;

	public User query(User user) throws Exception;

	public User login(String userCode, String userPassword) throws Exception;

	// 查询用户列表

	public List<User> getUserList(String queryUserName) throws Exception;

	public User getUserById(String id) throws Exception;

	// 根据userCode查询出User
	public User selectUserCodeExist(String userCode) throws Exception;

	// 根据id修改密码
	public boolean updatePwd(int id, String pwd) throws Exception;

}
