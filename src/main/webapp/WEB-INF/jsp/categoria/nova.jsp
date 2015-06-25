<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">
    <div class="col-lg-6 col-lg-offset-3">
        <form role="form" method="post" action="/categoria/save">

            <c:if test="${categoria != null}">
                <input type="hidden" name="id" value="${categoria.id}">
            </c:if>

            <div class="form-group">
                <label>Descrição</label>
                <input type="text" class="form-control" name="descricao" value="${categoria.descricao != null ? categoria.descricao : ""}" />
            </div>

            <div class="form-group">
                <label>Capacidade</label>
                <input type="number" class="form-control" name="capacidade" value="${categoria.capacidade != null ? categoria.capacidade : ""}" />
            </div>

            <div class="form-group">
                <label>Valor</label>
                <input type="text" class="form-control" name="valor" value="${categoria.valor != null ? categoria.valor : ""}" />
            </div>

            <div class="btn-group-lg">
                <div class="row">
                    <div class="col-lg-6">
                        <button type="submit" class="btn btn-success btn-block btn-lg">Salvar</button>
                    </div>
                    <div class="col-lg-6">
                        <a href="/categoria/list" class="btn btn-default btn-block btn-lg">Cancelar</a>
                    </div>
                </div>
            </div>

        </form>
    </div>
</div>