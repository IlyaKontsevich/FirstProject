<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<h1>Edit Tasks</h1>
Priority:  Status:
<form:form method="PUT" action="./">
    <table >
        <tr>
            <form:select path="priority">
                <form:option value="low"/>
                <form:option value="medium"/>
                <form:option value="high"/>
            </form:select>
        </tr>
        <tr>
            <td></td>
            <select name="done">
                <option value="true">true</option>
                <option value="false">false</option>
            </select>
        </tr>
        <tr>
            <td>Name : </td>
            <td><form:input path="name"  /></td>
        </tr>
        <tr>
            <td><input name="time" type="hidden" value="${date}"/></td>
        </tr>
        <tr>
            <td>Deadline : </td>
            <td><input name="date"  type="date" value="2019-12-12"/></td>
        </tr>
        <tr>
            <td> </td>
            <td><input type="submit" value="Edit Save" /></td>
        </tr>
    </table>
</form:form>