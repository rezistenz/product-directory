package org.rezistenz.product.directory.web.dto;

public class PagingInfo{

	private int pageIndex;
	private int pageSize;

	private long itemsCount;

	private String orderDir;
	private String orderCol;

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getItemsCount() {
		return itemsCount;
	}

	public void setItemsCount(long itemsCount) {
		this.itemsCount = itemsCount;
	}

	public String getOrderDir() {
		return orderDir;
	}

	public void setOrderDir(String orderDir) {
		this.orderDir = orderDir;
	}

	public String getOrderCol() {
		return orderCol;
	}

	public void setOrderCol(String orderCol) {
		this.orderCol = orderCol;
	}

	public long getPagesCount() {
		if(pageSize <= 0){
			throw new IllegalArgumentException("pageSize must be greatter zero");
		}
		long countPages = (long) Math.ceil( itemsCount / (double) pageSize);
		return countPages;
	}

	@Override
	public String toString() {
		return "PagingInfo [pageIndex=" + pageIndex + ", pageSize=" + pageSize
				+ ", itemsCount=" + itemsCount + ", orderDir=" + orderDir
				+ ", orderCol=" + orderCol + "]";
	}
	
}
