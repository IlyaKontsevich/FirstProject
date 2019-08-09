<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <TITLE>Crunchify - Spring MVC Example with AJAX call</TITLE>

    <script type="text/javascript"
            src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
    <script type="text/javascript">
        function Ajax() {
            $.ajax({
                url : 'ajax',
                success : function(data) {
                    $('#result').html(data);
                }
            });
        }
        Ajax();
    </script>

</head>
<body>
<div align="center">
    <div id="result"></div>
</div>
</body>
</html>
