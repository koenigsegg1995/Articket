package com.maddog.articket.filter.loginfilter;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class LoginFilter implements Filter {

	private FilterConfig config;

	@Override
	public void init(FilterConfig config) {
		this.config = config;
	}

	@Override
	public void destroy() {
		config = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String requestURI = req.getRequestURI();

		// 判斷需要過濾的頁面
//		if (requestURI.startsWith("/admin") || requestURI.startsWith("/user")) {
//			HttpSession session = req.getSession();
//			Object account = session.getAttribute("account");
//
//			if (account == null) {
//				session.setAttribute("location", requestURI);
//				res.sendRedirect(req.getContextPath() + "/login");
//				return;
//			} else {
//				// 如果已登入，繼續處理請求
//				chain.doFilter(request, response);
//			}
//		}
	}
}
