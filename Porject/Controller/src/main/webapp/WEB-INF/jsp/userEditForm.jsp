<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>Edit Users</h1>
<form:form method="PUT" action="./">
    <table >
        <tr>
            <td>Name : </td>
            <td><form:input path="name"  /></td>
        </tr>
        <tr>
            <td>Email : </td>
            <td><form:input path="email"  type = "email"/></td>
        </tr>
        <tr>
            <td>Age : </td>
            <td><form:input path="age"  type = "number" min = "1" max = "100"/></td>
        </tr>
        <tr>
            <td> </td>
            <td><input type="submit" value="Edit Save"/></td>
        </tr>
    </table>
</form:form>