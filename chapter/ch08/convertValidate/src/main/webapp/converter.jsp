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

<f:loadBundle basename="com.jsfcompref.bundles.Localized" var="bundle" />

<p><h:outputText value="#{bundle.skuLabel}" /> 
 
<h:inputText value="#{product.sku}" required="true" id="sku">
  <f:validator validatorId="skuValidator" />
  <f:converter converterId="skuConverter" />
</h:inputText>

<p><h:commandButton value="Submit" /></p>

<p><h:message styleClass="errorMessage" for="form:sku" /></p>

</h:form>

  </body>
</html>

</f:view>
