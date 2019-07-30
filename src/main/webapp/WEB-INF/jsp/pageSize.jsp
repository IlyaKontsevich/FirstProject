<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h1>Change page</h1>
<form method="post" action="savepage">
    <table >
        <tr>
            <td>Page size: </td>
            <td><input name="pageSize"  type="number" min="1"/></td>
        </tr>
        <tr>
        <tr>
            <td> </td>
            <td><input type="submit" value="Save" /></td>
        </tr>
    </table>
</form>
