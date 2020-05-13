package ssm_smbms.servlet.bill;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.StringUtils;

import ssm_smbms.pojo.Bill;
import ssm_smbms.service.bill.BillService;
import ssm_smbms.service.bill.BillServiceImpl;

public class BillServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public BillServlet() {
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
			System.out.println("billaccess?");
			this.query(request, response);
		}
	}

	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String queryBill = request.getParameter("productName");
		if (StringUtils.isNullOrEmpty(queryBill)) {
			queryBill = "";
		}
		List<Bill> billList = new ArrayList<Bill>();
		BillService bs = new BillServiceImpl();
		Bill bill = new Bill();
		bill.setProductName(queryBill);
		billList = bs.getBillList(bill);
		request.setAttribute("billList", billList);
		request.setAttribute("queryBill", queryBill);
		request.getRequestDispatcher("jsp/billlist.jsp").forward(request, response);

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
