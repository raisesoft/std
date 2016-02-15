package com.cd.bbh.common.enums;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class MybatisEnumHandler<E extends Enum<E>> extends BaseTypeHandler<E> {
	private Class<E> type;
	private Map<String, E> map = new HashMap<String, E>();

	public MybatisEnumHandler(Class<E> type) {
		if (type == null) {
			throw new IllegalArgumentException("Type argument cannot be null");
		}
		this.type = type;
		E[] enums = type.getEnumConstants();
		if (enums == null) {
			throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type.");
		}
		for (E e : enums) {
			ValueEnum valueEnum = (ValueEnum) e;
			map.put(valueEnum.siginal(), e);
		}
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
		ValueEnum valueEnum = (ValueEnum) parameter;
		ps.setString(i, valueEnum.siginal());
	}

	@Override
	public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
		String value = rs.getString(columnName);
		if (rs.wasNull()) {
			return null;
		} else {
			return getValueEnum(value);
		}
	}

	@Override
	public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		String value = rs.getString(columnIndex);
		if (rs.wasNull()) {
			return null;
		} else {
			return getValueEnum(value);
		}
	}

	@Override
	public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		String value = cs.getString(columnIndex);
		if (cs.wasNull()) {
			return null;
		} else {
			return getValueEnum(value);
		}
	}

	private E getValueEnum(String value) {
		try {
			return map.get(value);
		} catch (Exception ex) {
			throw new IllegalArgumentException("Cannot convert " + value + " to " + type.getSimpleName() + " by value.", ex);
		}
	}

}
