<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="position" value="${position}"/>
<c:set var="size" value="${size}"/>
<h1>Users List</h1>
<table border="2" width="70%" cellpadding="2">
    <tr><th>Id</th><th>Name</th><th>View users tasks</th><th>Edit</th><th>Delete</th>
    <c:forEach var="user" items="${list}">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td><a href="viewUser${user.id}/task0">View task</a></td>
            <td><a href="editUser/${user.id}">Edit</a></td>
            <td><a href="deleteUser/${user.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<br/>
<c:if test="${position < size-3}">
    <a href=viewUser${position+3}>Next</a>
</c:if>
<c:if test="${position > 0}">
    <a href=viewUser${position-3}>Back</a>
</c:if>
<br/>
<a href="userForm">Add New User</a>