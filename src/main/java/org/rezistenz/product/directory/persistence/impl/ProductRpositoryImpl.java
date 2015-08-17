package org.rezistenz.product.directory.persistence.impl;

import java.util.Collection;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.rezistenz.product.directory.model.Category;
import org.rezistenz.product.directory.model.Product;
import org.rezistenz.product.directory.persistence.ProductRepository;
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
	public Collection<Product> findByParams(Map<String, Object> params) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
		Root<Product> enityRoot = criteriaQuery.from(Product.class);
		
		Predicate criteria = buildCriteria(criteriaBuilder, enityRoot, params);
		
		criteriaQuery.select(enityRoot).where(
				criteria
			);
		
		TypedQuery<Product> query = entityManager.createQuery(criteriaQuery);
		
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
									enityRoot.<Category>get("category").<String>get("name")
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
