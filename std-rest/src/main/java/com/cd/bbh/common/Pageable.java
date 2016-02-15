package com.cd.bbh.common;

public class Pageable {

	private int pagesize;
	private int page;

	public Pageable(int pagesize, int page) {
		this.page = page > 0 ? page - 1 : 0;
		this.pagesize = pagesize;
	}

	public int getPage() {
		return page * pagesize;
	}

	public int getPagesize() {
		return pagesize;
	}
}
