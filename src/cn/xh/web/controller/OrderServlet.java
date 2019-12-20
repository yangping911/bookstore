package cn.xh.web.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.xh.domain.Order;
import cn.xh.domain.Orderitem;
import cn.xh.domain.User;
import cn.xh.service.OrderService;
import cn.xh.service.impl.OrderServiceImpl;
import cn.xh.web.formbean.Cart;
import cn.xh.web.formbean.CartItem;

@WebServlet("/order/OrderServlet")
public class OrderServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3833283925393669169L;
	private OrderService service = new OrderServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		String op = req.getParameter("op");
		
		if (op.equals("genOrder")) {
			genOrder(req, resp);
		}
		
		if (op.equals("findAllOrders")) {
			findAllOrders(req, resp);
		}
	
		if (op.equals("findOrders")) {
			findOrders(req, resp);
		}
	
		if (op.equals("faHuo")) {
			faHuo(req, resp);
		}
	}

	private void faHuo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ordernum = req.getParameter("ordernum");
		service.faHuo(ordernum);
		List<Order> orders = service.findOrders();
		HttpSession session = req.getSession();
		session.setAttribute("orders", orders);
		System.out.println(orders);
		resp.sendRedirect(req.getContextPath() + "/manager/managerOrder.jsp");
	}

	private void findOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Order> orders = service.findOrders();
		HttpSession session = req.getSession();
		session.setAttribute("orders", orders);
		req.getRequestDispatcher("/manager/managerOrder.jsp").forward(req, resp);
	}

	private void findAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		List<Order> orders = service.findUserOrders(user);
		req.setAttribute("orders", orders);
		req.getRequestDispatcher("/person/personOrder.jsp").forward(req, resp);
	}

	private void genOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		HttpSession session = req.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		User user = (User) session.getAttribute("user");
		if (cart == null) {
			resp.getWriter().write("会话已经结束");
			return;
		}
		Order order = new Order();
		order.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		String ordernum = genOrdernum();
		order.setOrdernum(ordernum);
		order.setQuantity(cart.getTotalQuantity());
		order.setMoney(cart.getTotalMoney());
		order.setUser(user);
	
		List<Orderitem> oItems = new ArrayList<Orderitem>();
		for (Map.Entry<String, CartItem> me : cart.getItmes().entrySet()) {
			CartItem cItem = me.getValue();
			Orderitem oItem = new Orderitem();
			oItem.setId(genOrdernum());
			oItem.setBook(cItem.getBook());
			oItem.setPrice(cItem.getMoney());
			oItem.setQuantity(cItem.getQuantity());
			oItem.setOrdernum(ordernum);
			oItems.add(oItem);
		}
	
		order.setItems(oItems);
		service.genOrder(order);
		req.setAttribute("order", order);
		session.removeAttribute("cart");
		req.getRequestDispatcher("/order.jsp").forward(req, resp);
	}


	private String genOrdernum() {
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		String s1 = df.format(now);
		return s1 + System.nanoTime();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
