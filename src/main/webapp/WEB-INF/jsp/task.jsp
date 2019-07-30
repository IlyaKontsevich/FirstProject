<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="position" value="${position}"/>
<c:set var="size" value="${size}"/>
<c:set var="userId" value="${userId}"/>
<c:set var="pageSize" value="${pageSize}"/>
<h1 align="center">Tasks List</h1>
<p align="center"><a href="pagesize">Change page size</a></p>
<table align ="center" border="2" width="70%" cellpadding="2">
    <tr><th><a href="changesort{id}">Id</a></th><th><a href="changesort{name}">Name</a></th><th>Deadline</th><th>User id</th><th>Edit</th><th>Delete</th>
        <c:forEach var="task" items="${list}">
    <tr>
        <td>${task.id}</td>
        <td>${task.name}</td>
        <td>${task.deadline}</td>
        <td>${task.userId}</td>
        <td><a href="${task.id}/edit">Edit</a></td>
        <td><a href="${task.id}/delete">Delete</a></td>
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
<p align="center"><a href="form">Add New Task</a></p>
<p align="center"><a href="/FirstProject/user/0" >View users</a></p>