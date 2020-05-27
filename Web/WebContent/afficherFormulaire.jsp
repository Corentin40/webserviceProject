<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Affichage du rendez-vous</title>
        <link type="text/css" rel="stylesheet" href="Style.css">
    </head>
    <body>
    <p><strong>Récapitulatif des informations du rendez-vous</strong></p>
        <div id="corps">
            <p class="info">${ form.resultat }</p>
            <p>Nom : <c:out value="${ utilisateur.nom }"/></p>
            <p>Prénom : <c:out value="${ utilisateur.prenom }"/></p>
            <p>Telephone : <value="${ utilisateur.tel }"/></p>
            <p>Date du rendez-vous : <c:out value="${ utilisateur.date}"/></p>
        </div>
    </body>
</html>