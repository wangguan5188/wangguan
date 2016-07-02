package com.wangguan.page;

import java.util.List;

/**
 * 分页返回数据封装
 * 
 * @author zhangtb
 * @date 2016-7-2 15:45:00
 * @param <T>
 * @since 1.0.0
 */
public class Pagination<T> {

	private PageParam pageParam;
	private List<T> list;

	public Pagination() {
		super();
	}

	public Pagination(PageParam pageParam, List<T> list) {
		super();
		this.pageParam = pageParam;
		this.list = list;
	}

	public PageParam getPageParam() {
		return pageParam;
	}

	public void setPageParam(PageParam pageParam) {
		this.pageParam = pageParam;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}
