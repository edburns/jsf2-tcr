<%@ taglib uri="http://java.sun.com/jsf/core"  prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html"  prefix="h" %>

<f:view>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>convertDateTime example</title>
  </head>

  <body>
    <h1>convertDateTime example</h1>

<h:form id="form">

<p><h:outputText id="date" value="#{transaction.date}">
  <f:convertDateTime dateStyle="short" />
</h:outputText> </p>

</p>
</h:form>

  </body>
</html>

</f:view>
