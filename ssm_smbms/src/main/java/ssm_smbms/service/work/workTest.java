package ssm_smbms.service.work;

import java.util.Date;

import ssm_smbms.pojo.Work;
import ssm_smbms.proxy.proxyDemo;

public class workTest {
	public static void main(String[] args) throws Exception {
		workService wService = new workServiceImpl();
		Work work = new Work();
		work.setDate(new Date());
		work.setWorkName("测试工作名88");
		work.setWorkContent("工程内容");
		work.setPhone("151584d88wd5ad5wad");
		work.setWorkPeople("工程人员");
		work.setRemark("备注测试");
		work.setIfWork("是否去工地");
//		wService.add(work);
//		List<Work> workList = wService.getWorkList("工作");
//		System.out.println(workList);
		proxyDemo pd = new proxyDemo();
		System.out.println(pd.getWorkService().getWorkList("工作"));
	}
}
