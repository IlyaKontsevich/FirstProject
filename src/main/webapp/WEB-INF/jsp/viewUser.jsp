<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="position" value="${position}"/>
<c:set var="size" value="${size}"/>
<c:set var="pageSize" value="${pageSize}"/>
<h1 align ="center">Users List</h1>
<p align="center"><a href="pagesize">Change page size</a></p>
<table align ="center" border="2" width="70%" cellpadding="2">
    <tr><th><a href="changesort{id}">Id</a></th><th><a href="changesort{name}">Name</a></th><th>View users tasks</th><th>Edit</th><th>Delete</th>
    <c:forEach var="user" items="${list}">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td><a href="${user.id}/task/0">View task</a></td>
            <td><a href="${user.id}/edit">Edit</a></td>
            <td><a href="${user.id}/delete">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<br/>
<p align="center">page: ${Math.round(position/pageSize)+1} of ${Math.round(Math.ceil(size/pageSize))}</p>
<c:if test="${position < size-pageSize}">
    <a href=${position+pageSize}>Next</a>
</c:if>
<c:if test="${position > 0}">
    <a href=${position-pageSize}>Back</a>
</c:if>
<br/>
<p align="center"><a href="form">Add New User</a></p>