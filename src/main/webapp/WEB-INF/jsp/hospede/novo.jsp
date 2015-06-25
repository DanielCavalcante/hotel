<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">
  <div class="col-lg-6 col-lg-offset-3">
    <form:form role="form" modelAttribute="hospede" method="post" action="/hospede/save">

        <form:hidden path="id" value="${hospede.id}"></form:hidden>
        
        <div class="form-group">
          <label>Nome</label>
          <form:input path="nome" cssClass="form-control"></form:input>
        </div>

        <div class="form-group">
            <label>Cpf</label>
            <form:input path="cpf" cssClass="form-control"></form:input>
        </div>

        <div class="form-group">
            <label>Email</label>
            <form:input path="email" cssClass="form-control"></form:input>
        </div>

        <div class="form-group">
            <label>Telefone</label>
            <form:input path="telefone" cssClass="form-control"></form:input>
        </div>

        <div class="form-group">
            <label>Logradouro</label>
            <form:input path="endereco.logradouro" cssClass="form-control"></form:input>
        </div>

        <div class="form-group">
            <label>Cidade</label>
            <form:input path="endereco.cidade" cssClass="form-control"></form:input>
        </div>

        <div class="form-group">
            <label>Bairro</label>
            <form:input path="endereco.bairro" cssClass="form-control"></form:input>
        </div>

        <div class="form-group">
            <label>Cep</label>
            <form:input path="endereco.cep" cssClass="form-control"></form:input>
        </div>

        <div class="form-group">
            <label>Número</label>
            <form:input path="endereco.numero" cssClass="form-control"></form:input>
        </div>

        <div class="btn-group-lg">
            <div class="row">
                <div class="col-lg-6">
                    <button type="submit" class="btn btn-success btn-block btn-lg">Salvar</button>
                </div>
                <div class="col-lg-6">
                    <a href="/hospede/list" class="btn btn-default btn-block btn-lg">Cancelar</a>
                </div>
            </div>
        </div>

    </form:form>
  </div>
</div>
