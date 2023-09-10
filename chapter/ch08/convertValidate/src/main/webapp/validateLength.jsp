<%@ taglib uri="http://java.sun.com/jsf/core"  prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html"  prefix="h" %>

<f:view>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>validateLength example</title>
  </head>

  <body>
    <h1>validateLength example</h1>

<h:form id="form">

<p>Credit Card Number:
  <h:inputText value="#{user.creditCard}" id="cc">
     <f:validateLength minimum="16" maximum="16" />
  </h:inputText>
</p>

<p>Zip Code:
  <h:inputText id="zipcode" value="#{user.zipcode}">
    <f:validateLength maximum="5" minimum="5" />
    <f:validateLongRange minimum="90000" maximum="99999" />
  </h:inputText>
</p>

<p><h:commandButton value="Submit" /></p>

<p>
<h:messages />
</p>



</h:form>

  </body>
</html>

</f:view>
