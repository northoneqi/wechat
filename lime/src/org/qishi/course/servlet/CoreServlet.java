package org.qishi.course.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.qishi.course.service.CoreService;
import org.qishi.course.util.SignUtil;

/**
 * Servlet implementation class CoreServlet
 */
public class CoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public CoreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * 确认请求来自微信服务器
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 微信加密签名
				String signature = request.getParameter("signature");
				// 时间戳
				String timestamp = request.getParameter("timestamp");
				// 随机数
				String nonce = request.getParameter("nonce");
				// 随机字符串
				String echostr = request.getParameter("echostr");
				
				System.out.println("1---"+signature);
				System.out.println("2----"+timestamp);
				System.out.println("3----"+nonce);
				System.out.println("4----"+echostr);

				PrintWriter out = response.getWriter();
				// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
				if (SignUtil.checkSignature(signature, timestamp, nonce)) {
					out.print(echostr);
					System.out.println("echostr---"+echostr);
				}
				out.close();
				out = null;
	}

	/**
	 * 处理微信服务器发来的消息
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("UTF-8");
				long starttime = System.currentTimeMillis();
				// 调用核心业务类接收消息、处理消息
				String respMessage = CoreService.processRequest(request);
				
				// 响应消息
				PrintWriter out = response.getWriter();
				out.print(respMessage);
				out.close();
				
				long endtime = System.currentTimeMillis();
				
				System.out.println("zhixing------"+(endtime-starttime));
	}

}
