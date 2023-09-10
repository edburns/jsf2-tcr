<%@ taglib uri="http://java.sun.com/jsf/core"  prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html"  prefix="h" %>

<f:view>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Simple Conversion and Validation Example</title>
  </head>

  <body>
    <h1>Simple Conversion and Validation Example</h1>

<h:form id="form">

<p>Enter a number from 1 to 10:</p>
<p>
<h:inputText value="#{bean.number}" id="numberField" required="true">
  <f:validateLongRange minimum="1" maximum="10" />
</h:inputText>
</p>
<p><h:message for="form:numberField" /> </p>

<p><h:commandButton value="Submit" /></p>

</h:form>

  </body>
</html>

</f:view>
