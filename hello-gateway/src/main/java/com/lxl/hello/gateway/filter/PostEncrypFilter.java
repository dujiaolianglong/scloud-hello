/**
 * 
 */
package com.lxl.hello.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * 签名校验过滤器
 * 
 * @author Administrator
 *
 */
public class PostEncrypFilter extends ZuulFilter {

	private static final Logger logger = LoggerFactory.getLogger(PostEncrypFilter.class);

	@SuppressWarnings("unused")
	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		try {
			logger.info("permisson.............. ");
		} catch (Exception e) {
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
		return FilterConstants.POST_TYPE;
	}

}
