package ssm_smbms.service.user;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ssm_smbms.dao.UserDaoMapper;
import ssm_smbms.pojo.User;
import ssm_smbms.tools.MybatisUtil;

public class UserServiceImpl implements UserService {
	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

	@Override
	public boolean add(User user) throws Exception {
		boolean flag = false;
		try (SqlSession session = MybatisUtil.createSqlSession()) {
			UserDaoMapper userMapper = session.getMapper(UserDaoMapper.class);
			int updateRows = userMapper.add(user);
			if (updateRows > 0) {
				System.out.println("增加成功！");
				logger.info("create user success");
				flag = true;
			} else {
				System.out.println("增加失败！");
			}
			session.commit();
		}
		return flag;
	}

	@Override
	public boolean delete(Integer id) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		try (SqlSession session = MybatisUtil.createSqlSession()) {
			UserDaoMapper userMapper = session.getMapper(UserDaoMapper.class);
			int updateRows = userMapper.delete(id);
			if (updateRows > 0) {
				System.out.println("删除成功！");
				logger.info("delete user success");
				flag = true;
			} else {
				System.out.println("删除失败！");
			}
			session.commit();
		}
		return flag;
	}

	@Override
	public boolean update(User user) throws Exception {
		boolean flag = false;
		try (SqlSession session = MybatisUtil.createSqlSession()) {
			UserDaoMapper userMapper = session.getMapper(UserDaoMapper.class);
			int updateRows = userMapper.update(user);
			if (updateRows > 0) {
				System.out.println("修改成功！");
				logger.info("update user success");
				flag = true;
			} else {
				System.out.println("修改失败！");
			}
			session.commit();
		}
		return flag;
	}

	@Override
	public User query(User user) throws Exception {
		User rtUser = null;
		try (SqlSession session = MybatisUtil.createSqlSession()) {
			UserDaoMapper userMapper = session.getMapper(UserDaoMapper.class);
			rtUser = userMapper.query(user);
			if (rtUser != null) {
				logger.info("query success!");
			}
		}
		return rtUser;
	}

	@Override
	public User login(String userCode, String userPassword) throws Exception {
		User rtUser = null;
		try (SqlSession session = MybatisUtil.createSqlSession()) {
			UserDaoMapper userMapper = session.getMapper(UserDaoMapper.class);
			rtUser = userMapper.getLoginUser(userCode);
			if (rtUser != null) {
				if (!rtUser.getUserPassword().equals(userPassword)) {
					rtUser = null;
				} else {
					logger.info("login success!");
				}
			}
		}
		return rtUser;
	}

	@Override
	public List<User> getUserList(String queryUserName) throws Exception {
		List<User> userList = null;
		try (SqlSession session = MybatisUtil.createSqlSession()) {
			UserDaoMapper userMapper = session.getMapper(UserDaoMapper.class);
			userList = userMapper.getUserList(queryUserName);
			if (userList != null && userList.size() > 0) {
				logger.info("getUserList success");
			}
		}
		return userList;
	}

	@Override
	public User selectUserCodeExist(String userCode) throws Exception {
		// TODO Auto-generated method stub
		User user = null;
		try (SqlSession session = MybatisUtil.createSqlSession()) {
			UserDaoMapper userMapper = session.getMapper(UserDaoMapper.class);
			user = userMapper.getLoginUser(userCode);
			if (user != null) {
				logger.info("get user success");
			}
		}
		return user;
	}

	@Override
	public User getUserById(String id) throws Exception {
		// TODO Auto-generated method stub
		User user = null;
		try (SqlSession session = MybatisUtil.createSqlSession()) {
			UserDaoMapper userMapper = session.getMapper(UserDaoMapper.class);
			user = userMapper.getUserById(id);
			if (user != null) {
				logger.info("get user success");
			}
		}
		return user;
	}

	@Override
	public boolean updatePwd(int id, String pwd) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		try (SqlSession session = MybatisUtil.createSqlSession()) {
			UserDaoMapper userMapper = session.getMapper(UserDaoMapper.class);
			flag = userMapper.updatePwd(id, pwd);
			if (flag) {
				logger.info("update password success");
			}
			session.commit();
		}
		return flag;
	}

}
