/**
 * 
 */
package com.lxl.hello.gateway.filter;

import java.io.InputStream;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.util.StreamUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * 签名校验过滤器
 * 
 * @author Administrator
 *
 */
public class PreCheckSignFilter extends ZuulFilter {

	private static final Logger logger = LoggerFactory.getLogger(PreCheckSignFilter.class);

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		try {
			HttpServletRequest request = ctx.getRequest();
			InputStream in = request.getInputStream();
			logger.info("request uri: {}", request.getRequestURI());
			logger.info("inputstream: {}", in);
			String body = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
			logger.info("body: {}", body);
		} catch (Exception e) {
			logger.error("PreCheckSignFilter error", e);
		}
		return null;
	}

	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		if (!ctx.sendZuulResponse()) {
			return false;
		}
		return true;
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

}
