package ssm_smbms.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;

import ssm_smbms.pojo.User;
import ssm_smbms.service.user.UserService;
import ssm_smbms.service.user.UserServiceImpl;
import ssm_smbms.tools.Constants;

public class UserServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UserServlet() {
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
		if (method != null && method.equals("add")) {
			this.add(request, response);
		} else if (method != null && method.equals("query")) {
			this.query(request, response);

		} else if (method != null && method.equals("ucexist")) {
			this.userCodeExist(request, response);
		} else if (method != null && method.equals("deluser")) {
			this.delUser(request, response);
		} else if (method != null && method.equals("view")) {
			this.getUserById(request, response, "jsp/userview.jsp");
		} else if (method != null && method.equals("modify")) {
			this.getUserById(request, response, "jsp/usermodify.jsp");
		} else if (method != null && method.equals("modifyexe")) {
			this.modify(request, response);
		} else if (method != null && method.equals("pwdmodify")) {
			this.getPwdByUserId(request, response);
		} else if (method != null && method.equals("savepwd")) {
			this.updatepwd(request, response);
		}
	}

	private void updatepwd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object o = request.getSession().getAttribute(Constants.USER_SESSION);
		String newpassword = request.getParameter("newpassword");
		boolean flag = false;
		if (o != null && !StringUtils.isNullOrEmpty(newpassword)) {
			UserService userService = new UserServiceImpl();
			try {
				flag = userService.updatePwd(((User) o).getId(), newpassword);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (flag) {
				request.setAttribute(Constants.SYS_MESSAGE, "淇敼瀵嗙爜鎴愬姛锛�");
			} else {
				request.setAttribute(Constants.SYS_MESSAGE, "淇敼瀵嗙爜澶辫触锛�");
			}
		} else {
			request.setAttribute(Constants.SYS_MESSAGE, "淇敼瀵嗙爜澶辫触锛�");
		}
		request.getRequestDispatcher("/jsp/pwdmodify.jsp").forward(request, response);
	}

	private void getPwdByUserId(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object o = request.getSession().getAttribute(Constants.USER_SESSION);
		String oldpassword = request.getParameter("oldpassword");
		Map<String, String> resultMap = new HashMap<String, String>();
		if (o != null && !StringUtils.isNullOrEmpty(oldpassword)) {
			String sessionPwd = ((User) o).getUserPassword();
			if (oldpassword.equals(sessionPwd)) {
				resultMap.put("result", "true");
			} else {
				resultMap.put("result", "false");
			}
		} else {
			resultMap.put("result", "error");
		}
		response.setContentType("application/json");
		PrintWriter outPrintWriter = response.getWriter();
		outPrintWriter.write(JSONArray.toJSONString(resultMap));
		outPrintWriter.flush();
		outPrintWriter.close();
	}

	private void modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("uid");
		String userName = request.getParameter("userName");
		String gender = request.getParameter("gender");
		String birthday = request.getParameter("birthday");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String userType = request.getParameter("userType");
		User user = new User();
		user.setId(Integer.valueOf(id));
		user.setUserName(userName);
		user.setGender(Integer.valueOf(gender));
		try {
			user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setPhone(phone);
		user.setAddress(address);
		user.setUserType(Integer.valueOf(userType));
		user.setModifyBy(((User) request.getSession().getAttribute(Constants.USER_SESSION)).getId());
		user.setModifyDate(new Date());
		UserService userService = new UserServiceImpl();
		try {
			if (userService.update(user)) {
				request.getRequestDispatcher("/user.do?method=query").forward(request, response);
			} else {
				request.getRequestDispatcher("jsp/usermodify.jsp").forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getUserById(HttpServletRequest request, HttpServletResponse response, String url)
			throws ServletException, IOException {
		String id = request.getParameter("uid");
		if (!StringUtils.isNullOrEmpty(id)) {
			// 璋冪敤鍚庡彴鏂规硶寰楀埌user瀵硅薄
			UserService userService = new UserServiceImpl();
			User user = null;
			try {
				user = userService.getUserById(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("user", user);
			request.getRequestDispatcher(url).forward(request, response);
		}

	}

	private void delUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("uid");
		Integer delId = 0;
		try {
			delId = Integer.parseInt(id);
		} catch (Exception e) {
			// TODO: handle exception
			delId = 0;
		}
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if (delId <= 0) {
			resultMap.put("delResult", "notexist");
		} else {
			UserService userService = new UserServiceImpl();
			try {
				if (userService.delete(delId)) {
					resultMap.put("delResult", "true");
				} else {
					resultMap.put("delResult", "false");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 鎶妑esultMap杞崲鎴恓son瀵硅薄杈撳嚭
		response.setContentType("application/json");
		PrintWriter outPrintWriter = response.getWriter();
		outPrintWriter.write(JSONArray.toJSONString(resultMap));
		outPrintWriter.flush();
		outPrintWriter.close();
	}

	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 鏌ヨ鐢ㄦ埛鍒楄〃
		String queryUserName = request.getParameter("queryname");
		if (queryUserName == null) {
			queryUserName = "";
		}
		UserService userService = new UserServiceImpl();
		List<User> userList = null;
		try {
			userList = userService.getUserList(queryUserName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("userList", userList);
		request.setAttribute("queryUserName", queryUserName);

		request.getRequestDispatcher("jsp/userlist.jsp").forward(request, response);

	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userCode = request.getParameter("userCode");
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		String phone = request.getParameter("phone");
		String gender = request.getParameter("gender");
		String birthday = request.getParameter("birthday");
		String address = request.getParameter("address");
		String userType = request.getParameter("userType");
		String createBy = request.getParameter("createBy");
		String creationDate = request.getParameter("creationDate");
		User user = new User();
		user.setUserCode(userCode);
		user.setUserName(userName);
		user.setUserPassword(userPassword);
		user.setUserType(Integer.valueOf(userType));
		user.setAddress(address);
		user.setPhone(phone);
		user.setGender(Integer.valueOf(gender));
		try {
			if(birthday != null) {
				user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		user.setCreateBy(((User) request.getSession().getAttribute(Constants.USER_SESSION)).getId());
		user.setCreationDate(new Date());
		UserService userService = new UserServiceImpl();
		try {
			if (userService.add(user)) {
				request.getRequestDispatcher("/user.do?method=query").forward(request, response);
			} else {
				request.getRequestDispatcher("jsp/useradd.jsp").forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void userCodeExist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userCode = request.getParameter("userCode");
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if (StringUtils.isNullOrEmpty(userCode)) {
			// userCode==null || userCode.equals("")
			resultMap.put("userCode", "exist");
		} else {
			UserService userService = new UserServiceImpl();
			User user = null;
			try {
				user = userService.selectUserCodeExist(userCode);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (null != user) {
				resultMap.put("userCode", "exist");
			} else {
				resultMap.put("userCode", "notexist");
			}
		}
		// 鎶妑esulrMap杞负json瀛楃涓蹭互json鐨勫舰寮忚緭鍑�
		response.setContentType("application/json");// 閰嶇疆涓婁笅鏂囩殑杈撳嚭绫诲瀷
		// 浠巖esponse瀵硅薄涓幏鍙栧線澶栬緭鍑虹殑write瀵硅薄
		PrintWriter outPrintWriter = response.getWriter();
		// 鎶妑esultMap杞负json瀛楃涓茶緭鍑�
		outPrintWriter.write(JSONArray.toJSONString(resultMap));
		outPrintWriter.flush();// 鍒锋柊
		outPrintWriter.close();// 鍏抽棴娴�
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
