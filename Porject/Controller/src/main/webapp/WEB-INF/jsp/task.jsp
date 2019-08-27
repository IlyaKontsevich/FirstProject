<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
Sort by: ${sort}
</br>
Filter by: ${filter}
<h1 align="center">Tasks List</h1>
<table align ="center" border="2" width="70%" cellpadding="2">
    <tr><th><form  method="post" action="savefilter{id}${url}">
        <input name="filtervalue"  type="number" min="0" max="1000"/>
    </form></th><th><form method="post" action="savefilter{priority}${url}">
            <select name="filtervalue" size="1">
                <option value="low">low</option>
                <option value="medium">medium</option>
                <option value="high">high</option>
            </select>
        <input type="submit" value="Save" />
        </form></th><th><form method="post" action="savefilter{name}${url}">
        <input name="filtervalue"  type="text" size="10"/>
    </form></th><th><form method="post" action="savefilter{isdone}${url}">
            <select name="filtervalue" size="1">
                <option value="true">true</option>
                <option value="false">false</option>
            </select>
        <input type="submit" value="Save" />
        </form></th><th><form method="post" action="savefilter{deadline}${url}">
            <input name="filtervalue"  type="date"/>
            <input type="submit" value="Save" />
        </form></th><th><form method="post" action="savefilter{timeadd}${url}">
                <input name="filtervalue"  type="date"/>
                <input type="submit" value="Save" />
            </form></th><th><form method="post" action="savefilter{nul}${url}">
            <input type="submit" value="Without filter" />
        </form></th><th></th>
    <tr><th>Id<a href="changesort{id,asc}${url}">&#8593</a><a href="changesort{id,desc}${url}">&#8595</a><a href="changesort{id,nul}${url}">&#215</a></th>
        <th>Priority<a href="changesort{priority,asc}${url}">&#8593</a><a href="changesort{priority,desc}${url}">&#8595</a><a href="changesort{priority,nul}${url}">&#215</a></th>
        <th>Name<a href="changesort{name,asc}${url}">&#8593</a><a href="changesort{name,desc}${url}">&#8595</a><a href="changesort{name,nul}${url}">&#215</a></th>
        <th>Status<a href="changesort{isdone,asc}${url}">&#8593</a><a href="changesort{isdone,desc}${url}">&#8595</a><a href="changesort{isdone,nul}${url}">&#215</a></th>
        <th>Deadline<a href="changesort{deadline,asc}${url}">&#8593</a><a href="changesort{deadline,desc}${url}">&#8595</a><a href="changesort{deadline,nul}${url}">&#215</a></th>
        <th>Date of Add<a href="changesort{timeadd,asc}${url}">&#8593</a><a href="changesort{timeadd,desc}${url}">&#8595</a><a href="changesort{timeadd,nul}${url}">&#215</a></th>
        <th>Edit</th><th>Delete</th>
        <c:forEach var="task" items="${list}">
    <tr>
        <td>${task.id}</td>
        <td>${task.priority}</td>
        <td>${task.name}</td>
        <td>${task.isdone}</td>
        <td>${task.deadline}</td>
        <td>${task.timeadd}</td>
        <td><a href="${url.substring(1)}/${task.id}/edit">Edit</a></td>
        <form:form method="DELETE" action="${url.substring(1)}/${task.id}">
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

<p align="center"><a href="${url.substring(1)}/form">Add New Task</a></p>
Change page size
<a href="pagesize{5}${url}">5</a>
<a href="pagesize{10}${url}">10</a>
<a href="pagesize{15}${url}">15</a>
</br>
<p align="center"><a href="/FirstProject/user/?${userUrl}">View users</a></p>
