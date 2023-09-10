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

<p>MethodBinding validator for age: 
   <h:inputText validator="#{user2.validateAge}" value="#{user2.age}" /></p>

<p>Programmatically added validators for age: 
   <h:inputText id="userComponent" value="#{user.age}" /></p>

<p><h:outputText value="#{progressValidatorAdded}" /></p>
<p><h:outputText value="#{methodExpressionValidatorAdded}" /></p>

<p>Press submit to postback and re-display this page.  This will cause
the PhaseListener to successfully add the validators to the
"userComponent" input field.</p>

<p><h:commandButton value="Submit" /></p>

<p>
<h:messages />
</p>



</h:form>

  </body>
</html>

</f:view>
