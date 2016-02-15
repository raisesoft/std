package com.cd.bbh.common.dao;


public interface BaseDao<T> {

	public int add(T t);

	public T search(Long id);

	public int update(T t);

	public int remove(Long id);
}
