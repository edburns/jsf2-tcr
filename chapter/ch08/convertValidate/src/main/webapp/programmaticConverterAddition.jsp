<%@ taglib uri="http://java.sun.com/jsf/core"  prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html"  prefix="h" %>

<f:view>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>converter by id example</title>
  </head>

  <body>
    <h1>converter by id example</h1>

<h:form id="form">


<p>int component <h:inputText id="intComponent" /></p>

<p><h:outputText value="#{intConverterAdded}" /></p>

<p>float component <h:inputText id="floatComponent" /></p>

<p><h:outputText value="#{floatConverterAdded}" /></p>

<p><h:commandButton value="Submit" /></p>

<p>
<h:messages />
</p>



</h:form>

  </body>
</html>

</f:view>
