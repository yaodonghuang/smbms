package ssm_smbms.servlet.provider;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;

import ssm_smbms.pojo.Provider;
import ssm_smbms.pojo.User;
import ssm_smbms.service.provider.providerService;
import ssm_smbms.service.provider.providerServiceImpl;
import ssm_smbms.tools.Constants;

public class ProviderServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ProviderServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request  the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException      if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request  the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException      if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if (method != null && method.equals("query")) {
			this.query(request, response);
		} else if (method != null && method.equals("add")) {
			this.add(request, response);
		} else if (method != null && method.equals("delprovider")) {
			this.delete(request, response);
		} else if (method != null && method.equals("view")) {
			this.getProviderById(request, response, "jsp/providerview.jsp");
		} else if (method != null && method.equals("modify")) {
			this.getProviderById(request, response, "jsp/providermodify.jsp");
		}

	}

	private void getProviderById(HttpServletRequest request, HttpServletResponse response, String url)
			throws ServletException, IOException {
		String id = request.getParameter("proid");
		if (!StringUtils.isNullOrEmpty(id)) {
			// 调用后台方法得到user对象
			providerService providerService01 = new providerServiceImpl();
			Provider provider = providerService01.getProviderById(id);
			request.setAttribute("provider", provider);
			request.getRequestDispatcher(url).forward(request, response);
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("proid");

		HashMap<String, String> resultMap = new HashMap<String, String>();
		if (Integer.parseInt(id) <= 0) {
			resultMap.put("delResult", "notexist");
		} else {
			providerService providerService01 = new providerServiceImpl();
			if ((providerService01.delete(id)) == 0) {
				resultMap.put("delResult", "true");
			} else {
				resultMap.put("delResult", "false");
			}
		}
		// 把resultMap转换成json对象输出
		response.setContentType("application/json");
		PrintWriter outPrintWriter = response.getWriter();
		outPrintWriter.write(JSONArray.toJSONString(resultMap));
		outPrintWriter.flush();
		outPrintWriter.close();
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String proCode = request.getParameter("proCode");
		String proName = request.getParameter("proName");
		String proContact = request.getParameter("proContact");
		String proPhone = request.getParameter("proPhone");
		String proAddress = request.getParameter("proAddress");
		String proDesc = request.getParameter("proDesc");
		String proFax = request.getParameter("proFax");

		Provider provider = new Provider();
		provider.setProCode(proCode);
		provider.setProName(proName);
		provider.setProPhone(proPhone);
		provider.setProContact(proContact);
		provider.setProDesc(proDesc);
		provider.setProAddress(proAddress);
		provider.setProFax(proFax);
		provider.setCreateBy(((User) request.getSession().getAttribute(Constants.USER_SESSION)).getId());
		provider.setCreateDate(new Date());

		providerService providerService01 = new providerServiceImpl();
		if (providerService01.add(provider)) {
			request.getRequestDispatcher("/provider.do?method=query").forward(request, response);
		} else {
			request.getRequestDispatcher("jsp/provideradd.jsp").forward(request, response);
		}
	}

	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String queryProName = request.getParameter("queryProName");
		if (StringUtils.isNullOrEmpty(queryProName)) {
			queryProName = "";
		}
		List<Provider> providerList = new ArrayList<Provider>();
		providerService providerService01 = new providerServiceImpl();
		providerList = providerService01.getProviderList(queryProName);
		request.setAttribute("providerList", providerList);
		request.setAttribute("queryProName", queryProName);
		request.getRequestDispatcher("jsp/providerlist.jsp").forward(request, response);

	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
