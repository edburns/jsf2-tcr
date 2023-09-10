<%@ taglib uri="http://java.sun.com/jsf/core"  prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html"  prefix="h" %>

<f:view>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>convertNumber example</title>
  </head>

  <body>
    <h1>convertNumber example</h1>

<h:form id="form">

<p>Interest Rate: <h:outputText value="#{rates.prime}"> 
                     <f:convertNumber type="percent" />
                  </h:outputText>
</p>

</h:form>

  </body>
</html>

</f:view>
