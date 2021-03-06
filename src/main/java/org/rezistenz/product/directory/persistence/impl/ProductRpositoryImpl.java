package org.rezistenz.product.directory.persistence.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.rezistenz.product.directory.model.Product;
import org.rezistenz.product.directory.persistence.ProductRepository;
import org.rezistenz.product.directory.web.dto.ProductListItem;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRpositoryImpl implements ProductRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Product add(Product entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public Product udpate(Product entity) {
		return entityManager.merge(entity);
	}

	@Override
	public Product findByPK(Long primaryKey) {
		return entityManager.find(Product.class, primaryKey);
	}

	@Override
	public Collection<ProductListItem> findByParams(Map<String, Object> params) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<ProductListItem> criteriaQuery = criteriaBuilder.createQuery(ProductListItem.class);
		
		Root<Product> enityRoot = criteriaQuery.from(Product.class);
		
		Predicate criteria = buildCriteria(criteriaBuilder, enityRoot, params);
		
		Join<Object, Object> categoryJoin = enityRoot.join("category", JoinType.LEFT);
		
		criteriaQuery.multiselect(
				enityRoot.<Long>get("id"),
				enityRoot.<String>get("name"),
				categoryJoin.<String>get("name"),
				enityRoot.<String>get("producer"),
				enityRoot.<BigDecimal>get("price"),
				enityRoot.<Calendar>get("createDate")
			).where(
				criteria
			);
		
		String orderDir = (String) params.get("order_dir");
		if(orderDir == null){
			orderDir="desc";
		}
		String orderCol = (String) params.get("order_col");
		if(orderCol == null){
			orderCol="id";
		}
		
		
		Path<Object> col = null;
		if(orderCol.equals("category_name")){
			col=categoryJoin.get("name");
		}else{
			col=enityRoot.get(orderCol);
		}
			
		if(orderDir.equals("asc")){
			criteriaQuery.orderBy(criteriaBuilder.asc(col));
		}else if(orderDir.equals("desc")){
			criteriaQuery.orderBy(criteriaBuilder.desc(col));
		}
		
		
		TypedQuery<ProductListItem> query = entityManager.createQuery(criteriaQuery);
		
		Integer pageSize=(Integer) params.get("page_size");
		Integer pageIndex=(Integer) params.get("page_index");
		
		if(pageSize!=null && pageIndex!=null){
			query.setMaxResults(pageSize);
			query.setFirstResult(pageIndex*pageSize);
		}
		
		return query.getResultList();
	}
	
	private Predicate buildCriteria(CriteriaBuilder criteriaBuilder,
			Root<Product> enityRoot, Map<String, Object> params) {
		String categoryName = (String) params.get("category_name");
		
		Predicate criteria = criteriaBuilder.conjunction();
		
		if( categoryName!=null && !categoryName.isEmpty()){
			criteria.getExpressions().add(
					criteriaBuilder.like(
							criteriaBuilder.lower(
									enityRoot.join("category", JoinType.LEFT).<String>get("name")
								), 
							"%"+categoryName.toLowerCase()+"%"
						)
				);
		}
		return criteria;
	}

	@Override
	public Long findByParamsCount(Map<String, Object> params) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		
		Root<Product> enityRoot = criteriaQuery.from(Product.class);
		
		Predicate criteria = buildCriteria(criteriaBuilder, enityRoot, params);
		
		
		criteriaQuery.select(criteriaBuilder.count(enityRoot)).where(
				criteria
			);
		
		TypedQuery<Long> query = entityManager.createQuery(criteriaQuery);
		
		return query.getSingleResult();
	}

	
}
