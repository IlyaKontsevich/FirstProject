<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
Sort by: ${sort}
</br>
Filter by: ${filter}
<h1 align ="center">Users List</h1>
<table align ="center" border="2" width="70%" cellpadding="2">
    <tr><th><form  method="post" action="savefilter{id}${url}">
        <input name="filtervalue"  type="number" min="0" max="1000"/>
    </form></th><th><form method="post" action="savefilter{name}${url}">
        <input name="filtervalue"  type="text" size="10"/>
    </form></th><th><form method="post" action="savefilter{age}${url}">
        <input name="filtervalue"  type="number" min="1" max="99"/>
    </form></th><th>
        <form method="post" action="savefilter{email}${url}">
            <input name="filtervalue"  type="email" size="10"/>
        </form></th><th>
        <form method="post" action="savefilter{nul}${url}">
            <input type="submit" value="Without filter" />
        </form></th><th></th><th></th>
    <tr><th>Id<a href="changesort{id,asc}${url}">&#8593</a><a href="changesort{id,desc}${url}">&#8595</a><a href="changesort{id,nul}${url}">&#215</a></th>
        <th>Name<a href="changesort{name,asc}${url}">&#8593</a><a href="changesort{name,desc}${url}">&#8595</a><a href="changesort{name,nul}${url}">&#215</a></th>
        <th>Age<a href="changesort{age,asc}${url}">&#8593</a><a href="changesort{age,desc}${url}">&#8595</a><a href="changesort{age,nul}${url}">&#215</a></th>
        <th>Email<a href="changesort{email,asc}${url}">&#8593</a><a href="changesort{email,desc}${url}">&#8595</a><a href="changesort{email,nul}${url}">&#215</a></th>
        <th>View users tasks</th><th>Edit</th><th>Delete</th>
    <c:forEach var="user" items="${list}">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.age}</td>
            <td>${user.email}</td>
            <td><a href="${url.substring(1)}/${user.id}/task/">View task</a></td>
            <td><a href="${url.substring(1)}/${user.id}/edit">Edit</a></td>
            </form>
            <form:form method="DELETE" action="${url.substring(1)}/${user.id}">
            <td><input type="submit" value="Delete" /></td>
            </form:form>
        </tr>
    </c:forEach>
</table>
<br/>
<p align="center">page: ${position} of ${Math.round(Math.ceil(size/pageSize))}</p>
<c:if test="${position < Math.round(Math.ceil(size/pageSize))}">
    <a href="changepage${position+1}${url}">Next</a>
</c:if>
<c:if test="${position > 1}">
    <a href="changepage${position-1}${url}">Back</a>
</c:if>
<br/>
<p align="center"><a href="${url.substring(1)}/form">Add New User</a></p>
Change page size
<a href="pagesize{5}${url}">5</a>
<a href="pagesize{10}${url}">10</a>
<a href="pagesize{15}${url}">15</a>
<br/>