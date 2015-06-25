<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form:form class="form-signin" modelAttribute="usuario" method="post" action="/login/autenticar">
    <c:if test="${erro}">
        <div class="row">
            <div class="alert alert-danger alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <p>${erro}</p>
            </div>
        </div>
    </c:if>
    <h3>Sistema de reservas</h3>
    <h5 class="form-signin-heading">Login</h5>
    <label class="sr-only">Username</label>
    <form:input path="login" cssClass="form-control" placeholder="Login"></form:input>
    <h5 class="form-signin-heading">Senha</h5>
    <label class="sr-only">Senha</label>
    <form:password path="senha" cssClass="form-control" placeholder="Senha"></form:password>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
</form:form>