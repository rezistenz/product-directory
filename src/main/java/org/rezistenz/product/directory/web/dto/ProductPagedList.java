package org.rezistenz.product.directory.web.dto;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.displaytag.pagination.PaginatedList;
import org.displaytag.properties.SortOrderEnum;
import org.rezistenz.product.directory.model.Product;

public class ProductPagedList implements PaginatedList {

	private final PagingInfo pagingInfo;
	private final Collection<Product> products;
	
	public ProductPagedList(PagingInfo pagingInfo, Collection<Product> products){
		this.pagingInfo=pagingInfo;
		this.products=products;
	}
	
	@Override
	public int getFullListSize() {
		return pagingInfo.getSize();
	}

	@Override
	public List getList() {
		return new LinkedList<Product>(products);
	}

	@Override
	public int getObjectsPerPage() {
		return pagingInfo.getPageSize();
	}

	@Override
	public int getPageNumber() {
		return pagingInfo.getPageIndex()+1;
	}

	@Override
	public String getSearchId() {
		return "productPagedList";
	}

	@Override
	public String getSortCriterion() {
		return pagingInfo.getOrderCol();
	}

	@Override
	public SortOrderEnum getSortDirection() {
		return (pagingInfo.getOrderDir()!=null && pagingInfo.getOrderDir().equals("asc")) ? SortOrderEnum.ASCENDING : SortOrderEnum.DESCENDING;
	}

}
