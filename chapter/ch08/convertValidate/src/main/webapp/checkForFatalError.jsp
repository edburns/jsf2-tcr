<%@ taglib uri="http://java.sun.com/jsf/core"  prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html"  prefix="h" %>

<f:view>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Check for Fatal Error</title>
  </head>

  <body>
    <h1>Check for Fatal Error </h1>

<h:form id="form">

<p><h:commandButton value="submit" action="#{user.checkForFatalError}" /></p>

<p>
<h:messages />
</p>



</h:form>

  </body>
</html>

</f:view>
