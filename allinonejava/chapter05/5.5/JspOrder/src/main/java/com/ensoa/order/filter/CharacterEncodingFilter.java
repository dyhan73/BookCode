package com.ensoa.order.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(
		filterName="characterEncodingFilter",
		urlPatterns={"/*"},
		initParams={
				@WebInitParam(name="encoding", value="UTF-8")
		}
)
public class CharacterEncodingFilter implements Filter {
	
	private String encoding = null;

	public void init(FilterConfig config) throws ServletException {
		this.encoding = config.getInitParameter("encoding");
		System.out.println(config.getFilterName() + " 필터가 시작되었습니다.");
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if(request.getCharacterEncoding() == null) {
			if(encoding != null) {
				request.setCharacterEncoding(encoding);
				response.setCharacterEncoding(encoding);
			}
		}
		chain.doFilter(request, response);
	}
}
