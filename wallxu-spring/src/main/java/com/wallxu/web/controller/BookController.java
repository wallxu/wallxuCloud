package com.wallxu.web.controller;

import com.wallxu.service.BookService;
import com.wallxu.tx.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wallxu
 */
@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;

	@Autowired
	private UserService userService;


	@RequestMapping(value = "/hello")
	public String hello() {
		return "test";
	}

}
