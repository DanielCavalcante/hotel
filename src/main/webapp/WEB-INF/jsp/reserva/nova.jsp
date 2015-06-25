<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">
    <div class="col-lg-6 col-lg-offset-3">
        <form:form role="form" method="post" modelAttribute="reserva" action="/reserva/save">

            <form:hidden path="id" value="${reserva.id}"></form:hidden>

            <div class="form-group">
                <label>Hóspede</label>
                <form:select path="hospede.id" items="${hospedes}" cssClass="form-control" />
            </div>

            <div class="form-group">
                <label>Quarto</label>
                <form:select path="quarto.id" items="${quartos}" cssClass="form-control" />
            </div>

            <div class="form-group">
                <label>Data Início</label>
                <form:input type="date" path="dataInicial" cssClass="form-control"></form:input>
            </div>

            <div class="form-group">
                <label>Data Fim</label>
                <form:input type="date" path="dataFinal" cssClass="form-control"></form:input>
            </div>

            <div class="btn-group-lg">
                <div class="row">
                    <div class="col-lg-6">
                        <button type="submit" class="btn btn-success btn-block btn-lg">Reservar</button>
                    </div>
                    <div class="col-lg-6">
                        <a href="/reserva/list" class="btn btn-default btn-block btn-lg">Cancelar</a>
                    </div>
                </div>
            </div>

        </form:form>
    </div>
</div>