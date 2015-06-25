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
        <form  role="form" class="form-inline" method="get" action="/categoria/list">
            <div class="row">
                <div class="form-group col-md-5">
                    <label>Pesquisar</label>
                    <input type="text" class="form-control" name="descricao" />
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
        <a href="/categoria/nova" class="btn btn-success">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Cadastrar
        </a>
    </div>
</div>
<c:if test="${!empty categorias}">
    <div class="row">
        <div class="col-md-12">

            <table class="table table-hover table-striped table-condensed">

                <thead>
                    <tr>
                        <th>Descricao</th>
                        <th>Capacidade</th>
                        <th>Valor</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="categoria" items="${categorias}">
                        <tr>
                            <td>${categoria.descricao}</td>
                            <td>${categoria.capacidade}</td>
                            <td>R$ ${categoria.valor}</td>
                            <td style="text-align: right">
                                <a href="/categoria/${categoria.id}/edit" class="btn btn-default btn-xs">Editar</a>
                                <a href="/categoria/${categoria.id}/delete" class="btn btn-default btn-xs">Excluir</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>

            </table>

        </div>
    </div>
</c:if>