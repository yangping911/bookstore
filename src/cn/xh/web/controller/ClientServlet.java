package cn.xh.web.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.xh.domain.Book;
import cn.xh.domain.Favorite;
import cn.xh.domain.User;
import cn.xh.service.ClientService;
import cn.xh.service.impl.ClientServiceImpl;
import cn.xh.web.formbean.Cart;
import cn.xh.web.formbean.CartItem;

@WebServlet("/client/ClientServlet")
public class ClientServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1360076409260061707L;
	private ClientService service = new ClientServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		String op = req.getParameter("op");
		if (op != null && !op.equals("")) {
			
			if (op.equals("login")) {
				login(req, resp);
			}
			
			if (op.equals("layout")) {
				layout(req, resp);
			}
		
			if (op.equals("register")) {
				register(req, resp);
			}
			
			if (op.equals("wxys")) {
				wxys(req, resp);
			}
			
			if (op.equals("rwsk")) {
				rwsk(req, resp);
			}
			
			if (op.equals("sets")) {
				sets(req, resp);
			}
			
			if (op.equals("jyks")) {
				jyks(req, resp);
			}
			
			if (op.equals("jjjr")) {
				jjjr(req, resp);
			}
			
			if (op.equals("kxjs")) {
				kxjs(req, resp);
			}
			
			if (op.equals("personInformation")) {
				personInformation(req, resp);
			}
		
			if (op.equals("personPassword")) {
				personPassword(req, resp);
			}
			
			if (op.equals("search")) {
				search(req, resp);
			}
			
			if (op.equals("particulars")) {
				particulars(req, resp);
			}
		//ﳵ
			if (op.equals("addCart")) {
				addCart(req, resp);
			}
			
			if (op.equals("delItem")) {
				delItem(req, resp);
			}
			
			if (op.equals("changeNum")) {
				changeNum(req, resp);
			}
			
			if (op.equals("addfavorite")) {
				addfavorite(req, resp);
			}
			
			if (op.equals("showfavorite")) {
				showfavorite(req, resp);
			}
			
			if (op.equals("delFavorite")) {
				delFavorite(req, resp);
			}

		}
	}

	private void delFavorite(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String book_id = req.getParameter("book_id");
		service.delFavorite(book_id);
		HttpSession session = req.getSession();
		List<Favorite> lists = (List<Favorite>) session.getAttribute("favorite");
		Iterator<Favorite> iterator = lists.iterator();
		while (iterator.hasNext()) {
			Favorite favorite = iterator.next();
			if (book_id.equals(favorite.getBook().getBook_id())) {
				iterator.remove();
			}
		}
		resp.sendRedirect(req.getContextPath() + "/favorite.jsp");
	}

	private void showfavorite(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		List<Favorite> favorites = service.findFavoriteByUserId(user);
		session.setAttribute("favorite", favorites);
		req.getRequestDispatcher("/favorite.jsp").forward(req, resp);
	}

	private void addfavorite(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		String user_id = user.getId();
		String book_id = req.getParameter("book_id");
		boolean isExit = service.findFavorite(user_id, book_id);
		if (isExit == false) {
			service.addfavorite(user_id, book_id);
		}
	}

	private void changeNum(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String num = req.getParameter("num");
		String book_id = req.getParameter("book_id");
	//ﳵ
		Cart cart = (Cart) req.getSession().getAttribute("cart");
		CartItem item = cart.getItmes().get(book_id);
		item.setQuantity(Integer.parseInt(num));
		resp.sendRedirect(req.getContextPath() + "/showCart.jsp");

	}

	private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		HttpSession session = req.getSession();
		User user = service.login(username, password);
		if (user.getUsername() != null && user.getUsername() != "") {
			req.setAttribute("message", "登录成功");
			session.setAttribute("user", user);
			req.getRequestDispatcher("/message.jsp").forward(req, resp);
		} else {
			req.setAttribute("message", "账号或密码有误，请核对后再登录");
			req.getRequestDispatcher("/message.jsp").forward(req, resp);
		}
	}

	private void layout(HttpServletRequest req, HttpServletResponse resp) {
		try {
			HttpSession session = req.getSession();
			session.removeAttribute("user");
			resp.sendRedirect("http://localhost:8080/xinhua_library/index.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void register(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String name = req.getParameter("name");
			String sex = req.getParameter("sex");
			String tel = req.getParameter("tel");
			String address = req.getParameter("address");

			boolean isExist = false;

			if (!username.equals("") && !password.equals("")) {
				isExist = service.register(username, password, name, sex, tel, address);
				if (isExist == true) {
					resp.getWriter().write("该用户已注册，请直接");
					resp.getWriter().write("<a href='" + req.getContextPath() + "/index.jsp'>登录</a>");
				} else {
					resp.getWriter().write("注册成功");
					resp.getWriter().write("2s后跳转至登录页");
					resp.setHeader("Refresh", "2;URL=" + req.getContextPath() + "/index.jsp");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void wxys(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Book> books = service.xsys();
		req.setAttribute("books", books);
		req.getRequestDispatcher("/showBook.jsp").forward(req, resp);
	}

	private void rwsk(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Book> books = service.rwsk();
		req.setAttribute("books", books);
		req.getRequestDispatcher("/showBook.jsp").forward(req, resp);
	}

	private void sets(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Book> books = service.sets();
		req.setAttribute("books", books);
		req.getRequestDispatcher("/showBook.jsp").forward(req, resp);
	}

	private void jyks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Book> books = service.jyks();
		req.setAttribute("books", books);
		req.getRequestDispatcher("/showBook.jsp").forward(req, resp);
	}

	private void jjjr(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Book> books = service.jjjr();
		req.setAttribute("books", books);
		req.getRequestDispatcher("/showBook.jsp").forward(req, resp);
	}

	private void kxjs(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Book> books = service.kxjs();
		req.setAttribute("books", books);
		req.getRequestDispatcher("/showBook.jsp").forward(req, resp);
	}

	private void personInformation(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username = req.getParameter("username");
		String name = req.getParameter("name");
		String sex = req.getParameter("sex");
		String tel = req.getParameter("tel");
		String address = req.getParameter("address");

		service.personInformation(username, name, sex, tel, address);
		resp.getWriter().write("<div style='text-align: center;margin-top: 260px'><img src='" + req.getContextPath()
				+ "/img/duigou.png'/>修改成功</div>");
	}

	private void personPassword(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String repassword = req.getParameter("repassword");

		service.personPassword(password, username);
		resp.getWriter().write("<div style='text-align: center;margin-top: 260px'><img src='" + req.getContextPath()
				+ "/img/duigou.png'/>修改成功</div>");
	}

	private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String search = req.getParameter("search");
		List<Book> searchmessage = service.search(search);
		req.setAttribute("books", searchmessage);
		req.getRequestDispatcher("/showBook.jsp").forward(req, resp);
	}

	private void particulars(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String book_id = req.getParameter("book_id");
		Book book = findBookById(book_id);
		req.setAttribute("book", book);
		req.getRequestDispatcher("/particulars.jsp").forward(req, resp);
	}


	private Book findBookById(String book_id) {
		Book book = service.findBookById(book_id);
		return book;
	}

	private void addCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String book_id = req.getParameter("book_id");
		Book book = findBookById(book_id);

		HttpSession session = req.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		cart.addBook(book);
	}

	private void delItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String book_id = req.getParameter("book_id");
		Cart cart = (Cart) req.getSession().getAttribute("cart");
		cart.getItmes().remove(book_id);
		resp.sendRedirect(req.getContextPath() + "/showCart.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
