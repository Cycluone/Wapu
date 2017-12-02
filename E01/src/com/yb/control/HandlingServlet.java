package com.yb.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yb.bean.CoreProfile;
import com.yb.bean.ProfileOptimizationList;

public class HandlingServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");
		if ("down".equals(type)) {
			downloadxls(request, response);
		} else if ("page".equals(type)) {
			try {
				pagination(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("query".equals(type)){
			listquery(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {}

	public void init() throws ServletException {}

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	// �����ļ�
	public void downloadxls(HttpServletRequest request,
			HttpServletResponse response) {
		String ExcelServerRCID = request.getParameter("ExcelServerRCID");
		String orderid = request.getParameter("orderid");
		String filePath = getServletContext().getRealPath("file/")
				+ File.separator + orderid + ".xls";
		if (ExcelServerRCID.length() != 0 && !"".equals(ExcelServerRCID)
				&& orderid.length() != 0 && !"".equals(orderid)) {
			try {
				String path = ExecutorsJdbc.exe(ExcelServerRCID, orderid,
						filePath);
				if (path.length() != 0) {
					String filepath = path;
					String filename = Paths.get(path).getFileName().toString();
					// �����ļ�MIME����
					response.setContentType(getServletContext().getMimeType(
							filename));
					// ����Content-Disposition
					response.setHeader("Content-Disposition",
							"attachment;filename=" + filename);
					// ��ȡĿ���ļ���ͨ��response��Ŀ���ļ�д���ͻ���
					// ��ȡĿ���ļ��ľ���·��
					String fullFileName = getServletContext().getRealPath(
							"/file/" + filename);
					// ��ȡ�ļ�
					InputStream in = new FileInputStream(fullFileName);
					OutputStream out = response.getOutputStream(); 
					// д�ļ�
					int b;
					while ((b = in.read()) != -1) {
						out.write(b);
					}
					in.close();
					out.close();
				} else {
					request.getRequestDispatcher("index.jsp").forward(request,
							response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	// ��ҳ
	public synchronized void pagination(HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {
		 
		int pages = Integer.parseInt(request.getParameter("pages").toString());
        request.setAttribute("pages",pages);
        List<CoreProfile> listCore = ExecutorsJdbc.getPaginationData(pages, "","");
    	request.setAttribute("list",listCore);
        request.setAttribute("customer","");
        int count = ExecutorsJdbc.getCountData();//������
    	int limit=10;
        int totalpages = (int) Math.ceil(count / (limit * 1.0)); // ��ҳ��
        request.setAttribute("totalpages", totalpages);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
        request.getRequestDispatcher("Main.jsp").include(request, response);
	}
	//ģ����ѯ
	
	public synchronized void listquery(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		String customer = request.getParameter("customer");
		String order = request.getParameter("order");
		customer=customer==null?"":customer;
		order=order==null?"":order;
		customer=new String(customer.getBytes("ISO-8859-1"),"UTF-8");
		order=new String(order.getBytes("ISO-8859-1"),"UTF-8");
		List<CoreProfile> listCore = ExecutorsJdbc.getPaginationData(1, customer,order);
	    request.setAttribute("list",listCore);
	    int count=listCore.size();
	    request.setAttribute("customer",customer);
	    int limit=10;
        int totalpages = (int) Math.ceil(count / (limit * 1.0)); // ��ҳ��
        request.setAttribute("totalpages", totalpages);
	    request.setAttribute("pages",1);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		request.getRequestDispatcher("Main.jsp").include(request, response);
		
	}
}
