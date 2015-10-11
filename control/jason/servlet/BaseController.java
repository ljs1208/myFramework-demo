package jason.servlet;

import jason.Utils.AnnotationManagement;
import jason.Utils.MethodUtil;
import jason.proxy.CglibProxy;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class BaseController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BaseController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		InitMethod(request, response, "get");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		InitMethod(request, response, "post");
	}

	public void InitMethod(HttpServletRequest request,
			HttpServletResponse response, String type) throws ServletException,
			IOException {
		String uri = request.getRequestURI();
		uri = uri.substring(uri.lastIndexOf("/")+1);
		uri = uri.substring(0, uri.lastIndexOf("."));
		String [] datas  =  uri.split("_");
		String controllName =  datas[0];
		String methodName   =  datas[1];
		
		Object controller = AnnotationManagement.getInstance().getController(controllName);
		if(controller == null){
			System.out.println("------>this controller doesn't exist");
			throw new ServletException("this controller doesn't exist");
		}
		Method method = MethodUtil.getInstence().getMethod(methodName, controller.getClass());
		if(method == null){
			throw new ServletException("this method doesn't exist");
		}
		try {
			method.invoke(controller, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

//	public static void main(String[] args) throws ServletException {
//		String uri = "/ControllerTest/user_userList.action";
//		int index = uri.lastIndexOf("/");
//		String uriSubString = uri.substring(index+1,uri.length());
//		
//		String actionName = uriSubString.substring(0, uriSubString.lastIndexOf("."));
//		String data[] = actionName.split("_");
//		String controllerName = data[0];
//		
//		String methodName = data[1];
//		
//		Object obj = AnnotationManagement.getInstance().getController(controllerName);
//		if(obj == null){
//			throw new ServletException("Exception");
//		}
//		Method method = MethodUtil.getInstence().getMethod(methodName, obj.getClass());
//		try {
//			method.invoke(obj, null);
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			e.printStackTrace();
//		}
//
//	}

}
