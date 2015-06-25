<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${!empty success}">
    <div class="row">
        <div class="alert alert-success alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <p>${success}</p>
        </div>
    </div>
</c:if>
<c:if test="${!empty removed}">
    <div class="row">
        <div class="alert alert-success alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <p>${removed}</p>
        </div>
    </div>
</c:if>
<div class="row">
  <div class="col-md-12">
    <form  role="form" class="form-inline" method="get" action="/quarto/list">
      <div class="row">
        <div class="form-group col-md-8">
          <label>Número:</label>
          <input type="text" class="form-control" name="numero" placeholder="Numero"/>
          <label>Categoria:</label>
          <select class="form-control" name="categoriaId">
              <c:forEach var="categoria" items="${categorias}">
                  <option value="${categoria.id}">${categoria.descricao}</option>
              </c:forEach>
          </select>
          <button type="submit" class="btn btn-default">
            <span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;
          </button>
        </div>
      </div>
    </form>
  </div>
</div>

<div class="row">
  <div class="col-md-12">
    <a href="/quarto/novo" class="btn btn-success">
      <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Cadastrar
    </a>
  </div>
</div>
<c:if test="${!empty quartos}">
  <div class="row">
    <div class="col-md-12">
      <table class="table table-hover table-striped table-condensed">
        <thead>
        <tr>
          <th>Número</th>
          <th>Andar</th>
          <th>Categoria</th>
          <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="quarto" items="${quartos}">
          <tr>
            <td>${quarto.numero}</td>
            <td>${quarto.andar}</td>
            <td>${quarto.categoria.descricao}</td>
            <td style="text-align: right">
              <a href="/quarto/${quarto.id}/edit" class="btn btn-default btn-xs">Editar</a>
              <a href="/quarto/${quarto.id}/delete" class="btn btn-default btn-xs">Excluir</a>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</c:if>