package org.rezistenz.product.directory.persistence;

import java.util.Collection;
import java.util.Map;

public interface EnityRepository<ENITY_TYPE, PK_TYPE> {
	ENITY_TYPE add(ENITY_TYPE entity);
	ENITY_TYPE udpate(ENITY_TYPE entity);
	ENITY_TYPE findByPK(PK_TYPE primaryKey);
	Collection<ENITY_TYPE> findByParams(Map<String, Object> params);
	Long findByParamsCount(Map<String, Object> params);
}
