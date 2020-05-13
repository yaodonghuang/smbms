package ssm_smbms.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import ssm_smbms.service.work.workService;
import ssm_smbms.service.work.workServiceImpl;

/**
 * 动态代理
 * 
 * @author 黄耀栋
 *
 */
public class proxyDemo {
	workService ws = null;

	public proxyDemo() {
		ws = new workServiceImpl();
	}

	public workService getWorkService() {
		return (workService) Proxy.newProxyInstance(ws.getClass().getClassLoader(), ws.getClass().getInterfaces(),
				new InvocationHandler() {
					@Override
					public Object invoke(Object arg0, Method arg1, Object[] arg2) throws Throwable {
						// TODO Auto-generated method stub
						Object ob = null;
						if ("getWorkList".equalsIgnoreCase(arg1.getName())) {
							ob = arg1.invoke(ws, "测试");
						}
						return ob;
					}
				});
	}
}
