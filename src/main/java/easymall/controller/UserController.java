package easymall.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import easymall.po.User;
import easymall.service.UserService;

@Controller("userController")
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/login")
	public String login(User user, HttpSession session, Model model) {
		User user1 = userService.login(user.getUsername());
		if (user1 == null) {
			model.addAttribute("message", "��������û���������");
			return "login";
		} else if (user1.getPassword().equals(user.getPassword())) {
			session.setAttribute("user", user1);
			return "redirect:/index.jsp";
		} else {
			model.addAttribute("message", "��������������");
			return "login";
		}
	}

	@RequestMapping("/checkUser")
	public void checkUser(String username, HttpServletResponse response) throws IOException {
		if (userService.login(username) == null) {
			response.getWriter().print("��ϲ�����û�������ʹ�ã�");
		} else {
			response.getWriter().print("�û����ѱ�ʹ��!");
		}
	}

	@RequestMapping("/regist")
	public String regist(User user, String valistr, HttpSession session, Model model) {
		if ("".equals(user.getUsername()) || user.getUsername() == null) {
			model.addAttribute("msg", "�û�������Ϊ�գ�");
			return "regist";
		}
		if ("".equals(user.getPassword()) || user.getPassword() == null) {
			model.addAttribute("msg", "���벻��Ϊ�գ�");
			return "regist";
		}
		if ("".equals(valistr) || valistr == null) {
			model.addAttribute("msg", "��֤�벻��Ϊ�գ�");
			return "regist";
		}
		if (!valistr.equalsIgnoreCase(session.getAttribute("code").toString())) {
			model.addAttribute("msg", "��֤���������");
			return "regist";
		}
		if (userService.login(user.getUsername()) != null) {
			model.addAttribute("msg", "���û����Ѿ���ע���ˣ�");
			return "regist";
		}
		if (userService.regist(user) > 0) {
			user.setPassword(user.getPassword());
			model.addAttribute("msg", "ע��ɹ�");
		} else {
			model.addAttribute("msg", "ע��ʧ��");
		}
		return "regist";
	}

}
