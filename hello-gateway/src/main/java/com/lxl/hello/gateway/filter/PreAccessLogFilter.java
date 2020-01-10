/**
 * 
 */
package com.lxl.hello.gateway.filter;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import com.lxl.hello.gateway.constant.Constants;
import com.lxl.hello.gateway.utils.SLFMdcUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * @author Administrator
 *
 */
public class PreAccessLogFilter extends ZuulFilter {

	private static final Logger logger = LoggerFactory.getLogger(PreAccessLogFilter.class);

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		try {
			String traceId = this.uuid();
			ctx.addZuulRequestHeader(Constants.TRACE_ID, traceId);
			SLFMdcUtils.setMdc(traceId);

			HttpServletRequest request = ctx.getRequest();
			logger.info("req: [uri={}]", request.getRequestURI());
		} catch (Exception e) {
			ctx.setSendZuulResponse(false);
		}
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

	private String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
