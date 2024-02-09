package com.sametkula.webBlog;

import com.sametkula.webBlog.dto.CreateAccountRequest;
import com.sametkula.webBlog.services.AccountService;
import com.sametkula.webBlog.services.MVCService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebBlogApplication implements CommandLineRunner {

	private final AccountService accountService;

    public WebBlogApplication(AccountService accountService) {
        this.accountService = accountService;
    }

    public static void main(String[] args) {
		SpringApplication.run(WebBlogApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {/*
			var s = accountService.createAccount(
					CreateAccountRequest.builder()
							.username("samet")
							.password("1234")
							.email("sametkula8@gmail.com")
							.build()
			);
		System.out.println(s);*/
	}


}
