package ssm_smbms.service.provider;

import ssm_smbms.pojo.Provider;

public class providerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		providerService p1 = new providerServiceImpl();
		Provider provider = new Provider();
		provider.setId(3);
		provider.setProCode("测试测试");
		p1.update(provider);
//		int i=p1.delete("1");

//		System.out.println(i);
	}

}
