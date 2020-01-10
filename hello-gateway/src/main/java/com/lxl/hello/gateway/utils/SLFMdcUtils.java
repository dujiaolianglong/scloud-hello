package com.lxl.hello.gateway.utils;

import java.util.UUID;

import org.slf4j.MDC;

/**
 * MDC工具
 * 
 * @author Administrator
 *
 */
public class SLFMdcUtils {

	/**
	 * 日志追踪ID
	 */
	private final static String TRACE_ID = "traceId";

	public static void setMdc(String traceId) {
		MDC.put(TRACE_ID, traceId);
	}

	public static void setMdc() {
		MDC.put(TRACE_ID, getTraceId());
	}

	public static String getMdc() {
		return MDC.get(TRACE_ID);
	}

	public static void removeMdc() {
		MDC.remove(TRACE_ID);
	}

	private static String getTraceId() {
		String uuid = UUID.randomUUID().toString();
		return uuid.replaceAll("-", "");
	}

}
