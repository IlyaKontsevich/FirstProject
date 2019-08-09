<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>Add New User</h1>
<form:form method="post" action="save">
    <table >
        <tr>
            <td>Name : </td>
            <td><form:input path="name"  value="username"/></td>
        </tr>
        <tr>
            <td>Email : </td>
            <td><form:input path="email"  type = "email" value="s@mail.ru"/></td>
        </tr>
        <tr>
            <td>Age : </td>
            <td><form:input path="age"  type = "number" min = "1" max = "100" value="1"/></td>
        </tr>
        <tr>
            <td> </td>
            <td><input type="submit" value="Save" /></td>
        </tr>
    </table>
</form:form>
