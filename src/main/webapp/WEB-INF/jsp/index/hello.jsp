<c:if test="${!empty user}">
    <div class="row">
        <div class="alert alert-info alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <p>${user}</p>
        </div>
    </div>
</c:if>
<p><h3>${categorias}</h3></p>
<p><h3>${quartos}</h3></p>