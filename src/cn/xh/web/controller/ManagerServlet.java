package cn.xh.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.xh.domain.Administrator;
import cn.xh.domain.Book;
import cn.xh.domain.Category;
import cn.xh.domain.User;
import cn.xh.service.ManagerService;
import cn.xh.service.impl.ManagerServiceImpl;

@WebServlet("/manager/ManagerServlet")
public class ManagerServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -770267763271058314L;
	private ManagerService service = new ManagerServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		String op = req.getParameter("op");
		if (op.equals("login")) {
			login(req, resp);
		}
		
		if (op.equals("managerInformation")) {
			managerInformation(req, resp);
		}

		if (op.equals("managerPassword")) {
			managerPassword(req, resp);
		}
		
		if (op.equals("layout")) {
			layout(req, resp);
		}
		
		if (op.equals("addBookUI")) {
			addBookUI(req, resp);
		}
	
		if (op.equals("addBook")) {
			try {
				addBook(req, resp);
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		if (op.equals("addCategory")) {
			addCategory(req, resp);
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

		if (op.equals("editBookUI")) {
			editBookUI(req, resp);
		}

		if (op.equals("editBook")) {
			try {
				editBook(req, resp);
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		if (op.equals("delBook")) {
			delBook(req, resp);
		}
	
		if (op.equals("categoryList")) {
			categoryList(req, resp);
		}

		if (op.equals("editCategoryUI")) {
			editCategoryUI(req, resp);
		}
	
		if (op.equals("editCategory")) {
			editCategory(req, resp);
		}
	
		if (op.equals("delCategory")) {
			delCategory(req, resp);
		}
	
		if (op.equals("findUsers")) {
			findUsers(req, resp);
		}
	
		if (op.equals("addAdmin")) {
			addAdmin(req, resp);
		}
	
		if (op.equals("sales")) {
			sales(req, resp);
		}
	}

	private void sales(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Book> sales = service.sales();
		req.setAttribute("sales", sales);
		req.getRequestDispatcher("/manager/sales.jsp").forward(req, resp);
	}

	private void addAdmin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		String sex = req.getParameter("sex");
		String tel = req.getParameter("tel");
		Administrator admin = new Administrator(username, password, name, sex, tel);
		service.addAdmin(admin);
		resp.getWriter().write("<div style='text-align: center;margin-top: 260px'><img src='" + req.getContextPath()
				+ "/img/duigou.png'/>修改成功</div>");
	}

	private void findUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<User> list = service.findUsers();
		HttpSession session = req.getSession();
		session.setAttribute("users", list);
		req.getRequestDispatcher("/manager/managerUsers.jsp").forward(req, resp);
	}

	private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		HttpSession session = req.getSession();
		Administrator admin = service.login(username, password);
		if (admin.getUsername() != null && admin.getUsername() != "") {
			req.setAttribute("message", "登陆成功");
			session.setAttribute("admin", admin);
			req.getRequestDispatcher("/manager/message.jsp").forward(req, resp);
		} else {
			resp.getWriter()
					.write("无此用户，请联系管理员      <a href='http://localhost:8080/xinhua_library/index.jsp'>返回首页</a>");
		}

	}

	private void managerInformation(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String username = req.getParameter("username");
		String name = req.getParameter("name");
		String sex = req.getParameter("sex");
		String tel = req.getParameter("tel");
		service.managerInformation(username, name, sex, tel);
		HttpSession session = req.getSession();
		Administrator admin = (Administrator) session.getAttribute("admin");
		admin.setName(name);
		admin.setSex(sex);
		admin.setTel(tel);
		session.setAttribute("admin", admin);
		resp.getWriter().write("<div style='text-align: center;margin-top: 260px'><img src='" + req.getContextPath()
				+ "/img/duigou.png'/>修改成功</div>");

	}

	private void managerPassword(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String repassword = req.getParameter("repassword");

		service.managerPassword(username, password);
		resp.getWriter().write("<div style='text-align: center;margin-top: 260px'><img src='" + req.getContextPath()
				+ "/img/duigou.png'/>修改成功</div>");
	}

	private void layout(HttpServletRequest req, HttpServletResponse resp) {
		try {
			HttpSession session = req.getSession();
			session.removeAttribute("admin");
			resp.sendRedirect("http://localhost:8080/xinhua_library/index.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addBookUI(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Category> categorys = service.findAllCategory();
		req.setAttribute("cs", categorys);
		req.getRequestDispatcher("/manager/addBook.jsp").forward(req, resp);

	}

	private void addBook(HttpServletRequest req, HttpServletResponse resp) throws FileUploadException, IOException {
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		if (!isMultipart) {
			throw new RuntimeException("表单上传类型有误");
		}
	
		DiskFileItemFactory factory = new DiskFileItemFactory();

		ServletFileUpload sfu = new ServletFileUpload(factory);
		List<FileItem> items = new ArrayList<FileItem>();
		items = sfu.parseRequest(req);
		Book book = new Book();
		for (FileItem item : items) {
			if (item.isFormField()) {
				
				processFormField(item, req, book);
			} else {
			
				processUplodFiled(item, req, book);
			}
		}
		
		service.addBook(book);
		resp.getWriter().write("<div style='text-align: center;margin-top: 260px'><img src='" + req.getContextPath()
				+ "/img/duigou.png'/>添加成功</div>");
	}

	
	private void processUplodFiled(FileItem item, HttpServletRequest req, Book book) {
		try {
		
			String dirImages = getServletContext().getRealPath("/img");
	
			String FieldName = item.getFieldName();
			String name = item.getName();
			String fileType = name.substring(name.lastIndexOf(".") + 1);
			String fileName = UUID.randomUUID().toString();
			Date time = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String strTime = simpleDateFormat.format(time);
			
			String path = strTime;
			String filename = fileName + "." + fileType;
	
			File fileDir = new File(dirImages, path + File.separator + filename);
			
			File parentDir = new File(dirImages, path);
			if (!parentDir.exists()) {
				parentDir.mkdirs();
			}
			book.setFilename(filename);
			book.setPath(path);

			InputStream inputStream = item.getInputStream();
			FileOutputStream fos = new FileOutputStream(fileDir);
			int len = 0;
			while ((len = inputStream.read()) != -1) {
				fos.write(len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	
	private void processFormField(FileItem item, HttpServletRequest req, Book book) {
		try {
			
			String inputName = item.getFieldName();
			String inputValue = item.getString("UTF-8");
			if (inputName.equals("categoryid")) {
				String categoryid = item.getString();
				//System.out.println("categoryid=" + categoryid);
				Category category = service.findCategoryById(categoryid);
				//System.out.println(category);
				book.setCategory(category);
			} else {
				BeanUtils.setProperty(book, inputName, inputValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void addCategory(HttpServletRequest req, HttpServletResponse resp) {
		try {
			Category category = new Category();
			BeanUtils.populate(category, req.getParameterMap());
			service.addCategory(category);
			resp.getWriter().write("<div style='text-align: center;margin-top: 260px'><img src='" + req.getContextPath()
					+ "/img/duigou.png'/>添加成功</div>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void wxys(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Book> books = service.xsys();
		req.setAttribute("books", books);
		req.getRequestDispatcher("/manager/booksList.jsp").forward(req, resp);
	}

	private void rwsk(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Book> books = service.rwsk();
		req.setAttribute("books", books);
		req.getRequestDispatcher("/manager/booksList.jsp").forward(req, resp);
	}

	private void sets(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Book> books = service.sets();
		req.setAttribute("books", books);
		req.getRequestDispatcher("/manager/booksList.jsp").forward(req, resp);
	}

	private void jyks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Book> books = service.jyks();
		req.setAttribute("books", books);
		req.getRequestDispatcher("/manager/booksList.jsp").forward(req, resp);
	}

	private void jjjr(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Book> books = service.jjjr();
		req.setAttribute("books", books);
		req.getRequestDispatcher("/manager/booksList.jsp").forward(req, resp);
	}

	private void kxjs(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Book> books = service.kxjs();
		req.setAttribute("books", books);
		req.getRequestDispatcher("/manager/booksList.jsp").forward(req, resp);
	}

	private void editBookUI(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String book_id = req.getParameter("book_id");
		Book book = findBookById(book_id);
		List<Category> category = service.findAllCategory();
		HashMap map = new HashMap();
		map.put("book", book);
		map.put("category", category);
		req.setAttribute("map", map);
		req.getRequestDispatcher("/manager/editBook.jsp").forward(req, resp);
	}

	private void editBook(HttpServletRequest req, HttpServletResponse resp) throws FileUploadException, IOException {
		String book_id = req.getParameter("book_id");
		String book_name = req.getParameter("book_name");
		String book_author = req.getParameter("book_author");
		String book_press = req.getParameter("book_press");
		String book_desc = req.getParameter("book_desc");
		double book_price = Double.parseDouble(req.getParameter("book_price"));
		String book_kunumber = req.getParameter("book_kunumber");
		service.editBook(book_id, book_name, book_author, book_press, book_desc, book_price, book_kunumber);
		resp.getWriter().write("<div style='text-align: center;margin-top: 260px'><img src='" + req.getContextPath()
				+ "/img/duigou.png'/>修改成功</div>");
	}

	private void delBook(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String book_id = req.getParameter("book_id");
		service.delBook(book_id);
		resp.getWriter().write("<div style='text-align: center;margin-top: 260px'><img src='" + req.getContextPath()
				+ "/img/duigou.png'/>删除成功</div>");
	}

	private void categoryList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Category> categories = service.findAllCategory();
		req.setAttribute("categorys", categories);
		req.getRequestDispatcher("/manager/categorysList.jsp").forward(req, resp);
	}

	private void editCategoryUI(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Category category = service.findCategoryById(req.getParameter("category_id"));
		req.setAttribute("category", category);
		req.getRequestDispatcher("/manager/editCategory.jsp").forward(req, resp);
	}

	private void editCategory(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Category category = new Category(req.getParameter("category_id"), req.getParameter("category_name"),
				req.getParameter("category_desc"));
		service.editCategory(category);
		resp.getWriter().write("<div style='text-align: center;margin-top: 260px'><img src='" + req.getContextPath()
				+ "/img/duigou.png'/>修改成功</div>");
	}

	private void delCategory(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String category_id = req.getParameter("category_id");
		service.delCategory(category_id);
		resp.getWriter().write("<div style='text-align: center;margin-top: 260px'><img src='" + req.getContextPath()
				+ "/img/duigou.png'/>删除成功</div>");
	}

	
	private Book findBookById(String book_id) {
		return service.findBookById(book_id);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
