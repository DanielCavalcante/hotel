<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row">
  <div class="col-lg-6 col-lg-offset-3">
    <form:form role="form" modelAttribute="quarto" method="post" action="/quarto/save">

        <form:hidden path="id" value="${quarto.id}"></form:hidden>

        <div class="form-group">
            <label>Categoria</label>
            <form:select path="categoria.id" items="${categorias}" cssClass="form-control"/>
        </div>
        
      <div class="form-group">
        <label>Número</label>
        <input type="text" class="form-control" name="numero" value="${quarto != null ? quarto.numero : ""}">
      </div>

      <div class="form-group">
        <label>Andar</label>
        <input type="text" class="form-control" name="andar" value="${quarto != null ? quarto.andar : ""}">
      </div>

      <div class="btn-group-lg">
        <div class="row">
          <div class="col-lg-6">
            <button type="submit" class="btn btn-success btn-block btn-lg">Salvar</button>
          </div>
          <div class="col-lg-6">
            <a href="/quarto/list" class="btn btn-default btn-block btn-lg">Cancelar</a>
          </div>
        </div>
      </div>

    </form:form>
  </div>
</div>
