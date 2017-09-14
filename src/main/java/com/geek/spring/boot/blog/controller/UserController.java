package com.geek.spring.boot.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.geek.spring.boot.blog.domain.User;
import com.geek.spring.boot.blog.repository.UserRepository;

/**
 * User 控制器
 * @author zhuyangyong
 * @date 2017年9月5日 下午2:09:41 
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * 查询所有用户
	 * @param model
	 * @return
	 */
	@GetMapping
	public ModelAndView list(Model model) {
		model.addAttribute("userList", userRepository.listUsers());
		model.addAttribute("title", "用户管理");
		return new ModelAndView("users/list", "userModel",model);
	}
	
	/**
	 * 根据 id 查询用户
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("{id}")
	public ModelAndView view(@PathVariable("id") Long id,Model model) {
		User user = userRepository.getUserById(id);
		model.addAttribute("user", user);
		model.addAttribute("title", "查看用户");
		return new ModelAndView("users/view", "userModel", model);
	}
	
	/**
	 * 获取创建表单页面
	 * @param model
	 * @return
	 */
	@GetMapping("/form")
	public ModelAndView createForm(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("title", "创建用户");
		return new ModelAndView("users/form", "userModel", model);
	}
	
	/**
	 * 获取修改用户的界面
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/modify/{id}")
	public ModelAndView modifyUser(@PathVariable("id") Long id,Model model) {
		User user = userRepository.getUserById(id);
		model.addAttribute("user", user);
		model.addAttribute("title", "修改用户");
		return new ModelAndView("users/form", "userModel", model);
	}
	
	/**
	 * 新建用户
	 * @param user
	 * @return
	 */
	@PostMapping
	public ModelAndView createUser(User user) {
		user = userRepository.saveOrUpdate(user);
		return new ModelAndView("redirect:/users"); //重定向到list页面
	}
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/delete/{id}")
	public ModelAndView deleteUser(@PathVariable("id") Long id) {
		userRepository.deleteUser(id);
		return new ModelAndView("redirect:/users"); //重定向到list页面
	}

}
