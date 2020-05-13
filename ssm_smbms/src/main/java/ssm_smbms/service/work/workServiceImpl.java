package ssm_smbms.service.work;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import ssm_smbms.dao.workDaoMapper;
import ssm_smbms.pojo.Work;
import ssm_smbms.tools.MybatisUtil;

public class workServiceImpl implements workService {

	@Override
	public boolean add(Work work) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		try (SqlSession session = MybatisUtil.createSqlSession()) {
			workDaoMapper workMapper = session.getMapper(workDaoMapper.class);
			workMapper.add(work);
			flag = true;
			session.commit();
		}
		return flag;
	}

	@Override
	public boolean update(Work work) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		try (SqlSession session = MybatisUtil.createSqlSession()) {
			workDaoMapper workMapper = session.getMapper(workDaoMapper.class);
			workMapper.update(work);
			flag = true;
			session.commit();
		}
		return flag;
	}

	@Override
	public int delete(String id) throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		try (SqlSession session = MybatisUtil.createSqlSession()) {
			workDaoMapper workMapper = session.getMapper(workDaoMapper.class);
			// 这里可以改成假删除
			result = workMapper.delete(id);
			session.commit();
		}
		return result;
	}

	@Override
	public List<Work> getWorkList(String workName) throws Exception {
		// TODO Auto-generated method stub
		List<Work> list = new ArrayList<Work>();
		try (SqlSession session = MybatisUtil.createSqlSession()) {
			workDaoMapper workMapper = session.getMapper(workDaoMapper.class);
			list = workMapper.getWorkList(workName);
		}
		return list;
	}

	@Override
	public Work getWorkById(String id) throws Exception {
		// TODO Auto-generated method stub
		Work work = new Work();
		try (SqlSession session = MybatisUtil.createSqlSession()) {
			workDaoMapper workMapper = session.getMapper(workDaoMapper.class);
			work = workMapper.getWorkById(id);
		}
		return work;
	}
}
