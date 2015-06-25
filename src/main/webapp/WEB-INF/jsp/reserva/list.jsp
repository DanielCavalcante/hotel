<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">
    <div class="col-md-12">
        <form  role="form" class="form-inline" method="get" action="/reserva/list">
            <div class="row">
                <div class="form-group col-md-12">
                    <label>Data Inicial</label>
                    <input type="date" class="form-control" name="dataInicial" />
                    <label>Data Final</label>
                    <input type="date" class="form-control" name="dataFinal">
                    <label>Status</label>
                    <select class="form-control" name="status">
                        <option>Confirmada</option>
                        <option>Pendente</option>
                        <option>Cancelada</option>
                    </select>
                    <label>Cliente</label>
                    <select name="cliente_id" class="form-control">
                        <c:forEach var="h" items="${hospedes}">
                            <option value="${h.id}">${h.nome}</option>
                        </c:forEach>
                    </select>
                    <label>Quarto</label>
                    <select name="quarto_id" class="form-control">
                        <c:forEach var="q" items="${quartos}">
                            <option value="${q.id}">${q.numero}</option>
                        </c:forEach>
                    </select>
                    <button type="submit" class="btn btn-default">
                        <span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;
                    </button>
                </div>
            </div>
            <br/>
        </form>
    </div>
</div>

<div class="row">
    <div class="col-md-12">
        <a href="/reserva/nova" class="btn btn-success">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Cadastrar
        </a>
    </div>
</div>
<c:if test="${!empty reservas}">
    <div class="row">
        <div class="col-md-12">

            <table class="table table-hover table-striped table-condensed">

                <thead>
                <tr>
                    <th>Número</th>
                    <th>Data Início</th>
                    <th>Data Fim</th>
                    <th>Data Checkin</th>
                    <th>Data Checkout</th>
                    <th>Cliente</th>
                    <th>Quarto</th>
                    <th>Status</th>
                    <th>Paga</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="reserva" items="${reservas}">
                    <tr>
                        <td>${reserva.id}</td>
                        <td>${reserva.dataInicial}</td>
                        <td>${reserva.dataFinal}</td>
                        <td>${reserva.dataCheckin}</td>
                        <td>${reserva.dataChekout}</td>
                        <td>${reserva.hospede.nome}</td>
                        <td>${reserva.quarto.numero}</td>
                        <td>${reserva.status.name()}</td>
                        <td>${reserva.pago}</td>
                        <td style="text-align: right">
                            <a href="/reserva/${reserva.id}/edit" class="btn btn-default btn-xs">Editar</a>
                            <a href="/reserva/${reserva.id}/delete" class="btn btn-default btn-xs">Excluir</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>

        </div>
    </div>
</c:if>