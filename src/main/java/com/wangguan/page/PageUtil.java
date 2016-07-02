package com.wangguan.page;

/**
 * 分页计算工具类
 * 
 * @author zhangtb
 * @date 2016-7-2 15:44:07
 * @since 1.0.0
 */
public class PageUtil {

	public static PageParam get(int pageNum, int pageSize) {
		if (pageNum < 1)
			pageNum = 1;
		PageParam p = new PageParam();
		int pageFirst = (pageNum - 1) * pageSize;
		p.setPageFirst(pageFirst);
		p.setPageNum(pageNum);
		p.setPageSize(pageSize);
		return p;
	}

	public static PageParam get(int pageNum, int pageSize, int totalCount) {
		int pageFirst = (pageNum - 1) * pageSize;
		int totalPage = (totalCount - 1) / pageSize + 1;
		PageParam p = new PageParam();
		p.setPageFirst(pageFirst);
		p.setPageNum(pageNum);
		p.setPageSize(pageSize);
		p.setTotalCount(totalCount);
		p.setTotalPage(totalPage);
		return p;
	}

}
