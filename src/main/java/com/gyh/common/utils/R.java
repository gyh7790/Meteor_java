package com.gyh.common.utils;

import com.gyh.common.constant.HttpStatus;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 作者 gyh:
 * @version 创建时间：2018年1月13日 上午1:58:49
 *
 */
public class R extends HashMap<String, Object>{
	private static final long serialVersionUID = 1L;

	private static final String KEY_CODE = "code";
	private static final String KEY_MESSAGE = "msg";
	private static final String KEY_DATA = "data";

	public R() {
		put(KEY_CODE, HttpStatus.SC_OK);
	}

	public static R error() {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知系统异常，请联系管理员");
	}

	public static R error(String msg) {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
	}

	public static R error(boolean status, String msg) {
		R r = new R();
		r.put(KEY_CODE, HttpStatus.SC_OK);
		r.put(KEY_MESSAGE, msg);
		return r;
	}

	public static R error(int code, String msg) {
		R r = new R();
		r.put(KEY_CODE, code);
		r.put(KEY_MESSAGE, msg);
		return r;
	}

	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}

	public static R ok() {
		return new R();
	}

	@Override
	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}

	public static R ok(String msg) {
		return ok(HttpStatus.SC_OK, msg);
	}

	public static R ok(int code, String msg) {
		R r = new R();
		r.put(KEY_CODE, code);
		r.put(KEY_MESSAGE, msg);
		return r;
	}

	public static R ok(Object data) {
		R r = new R();
		r.put(KEY_CODE, HttpStatus.SC_OK);
		r.put(KEY_DATA, data);
		return r;
	}

	public static R ok(String key, Object obj) {
		R r = new R();
		r.put(KEY_CODE, HttpStatus.SC_OK);
		r.put(key, obj);
		return r;
	}
}
