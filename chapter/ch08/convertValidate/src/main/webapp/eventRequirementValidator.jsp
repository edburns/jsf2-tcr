<%@ taglib uri="http://java.sun.com/jsf/core"  prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html"  prefix="h" %>

<f:view>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>EventRequirementValidator example</title>
  </head>

  <body>
    <h1>EventRequirementValidator example</h1>

<h:form id="form">

<p>Press the button to check your status.
</p>

<p>Qualified? <h:selectBooleanCheckbox value="#{user.status.qualified}">
   <f:validator validatorId="com.jsfcompref.EventRequirements" />
   </h:selectBooleanCheckbox></p>


<p><h:commandButton value="Submit" /></p>

<p>
<h:messages />
</p>



</h:form>

  </body>
</html>

</f:view>
