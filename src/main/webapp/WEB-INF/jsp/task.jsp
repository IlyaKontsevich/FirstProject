<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="position" value="${position}"/>
<c:set var="size" value="${size}"/>
<c:set var="userId" value="${userId}"/>
<h1>Users List</h1>
<table border="2" width="70%" cellpadding="2">
    <tr><th>Id</th><th>Name</th><th>Deadline</th><th>User id</th><th>Edit</th><th>Delete</th>
        <c:forEach var="task" items="${list}">
    <tr>
        <td>${task.id}</td>
        <td>${task.name}</td>
        <td>${task.deadline}</td>
        <td>${task.userId}</td>
        <td><a href="editTask${task.id}">Edit</a></td>
        <td><a href="deleteTask${task.id}">Delete</a></td>
    </tr>
    </c:forEach>
</table>
<br/>
<c:if test="${position < size-3}">
    <a href=task${position+3}>Next</a>
</c:if>
<c:if test="${position > 0}">
    <a href=task${position-3}>Back</a>
</c:if>
<br/>
<a href="taskForm">Add New Task</a>
<br/>
<a href="/FirstProject/viewUser0" >View users</a>