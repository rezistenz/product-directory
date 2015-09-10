<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<link rel="stylesheet"href="<c:url value="/resources/css/displaytag-table.css"/>"></link>

<c:url var="searchURL" value="/products/list"/>

<form:form commandName="productFilter" action="${searchURL}">
	<spring:message code="category.name"/>
	<form:input path="categoryName"/>
	<input type="submit" value="<spring:message code="filter"/>" />
</form:form>

<display:table 
	list="${productPagedList}"
	requestURI="/products/list"
	sort="external">
	<display:column 
		titleKey="product.id"
		property="id" 
		sortProperty="id" 
		sortable="true"/>
	<display:column 
		titleKey="product.name" 
		property="name" 
		sortProperty="name" 
		sortable="true"/>
	<display:column 
		titleKey="category.name" 
		property="category.name" 
		sortProperty="category_name" 
		sortable="true"/>
	<display:column 
		titleKey="product.producer"  
		property="producer" 
		sortProperty="producer" 
		sortable="true"/>
	<display:column 
		titleKey="product.price" 
		property="price" 
		sortProperty="price" 
		sortable="true" 
		format="{0,number,0.00} RUB"/>
	<display:column 
		titleKey="product.createDate"  
		property="createDate.time" 
		sortProperty="createDate" 
		sortable="true" 
		format="{0,date,dd.MM.yyyy}"/>
</display:table>


