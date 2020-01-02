<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
    <head>

    </head>
    <body>
        <h3>Apache Tomcat Server up! Running on OS ${osname}, ${osversion}.</h3>
        Dao : ${dao.id} - ${dao.value}
        <br>
        System Properties
        <br>
         <c:forEach items="${systemProperties}" var="prop">
            ${prop}<br>
         </c:forEach>
    </body>
</html>