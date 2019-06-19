<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container-fluid">
	<div class="row align-items-start">
		<div class="lsb_wrap">
			<nav class="d-none d-md-block bg-light sidebar">
				<div class="sidebar-sticky">
					<ul class="nav flex-column">
						<li class="nav-item lsb_li">
							<c:forEach items="${menus }" var="menu">
								<a href="<c:url value='${menu.key }'/>"
								 class="list-group-item list-group-item-action" style="text-align:center;">${menu.value }</a>
							</c:forEach>
						</li>
					</ul>
				</div>
			</nav>
		</div>
	</div>
	
</div>  


	