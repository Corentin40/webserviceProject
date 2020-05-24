<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Prise de rendez-vous</title>
        <link type="text/css" rel="stylesheet" href="Style.css" />
    </head>
    <body>
        <form method="post" action="Formulaire">
            <fieldset>
                <legend>Formulaire pour prise de rendez-vous</legend>
                <p>Vous pouvez prendre rendez-vous via ce formulaire.</p>
                
                 <label for="prenom">Prenom <span class="requis">*</span></label>
                <input type="text" id="prenom" name="prenom" value="" size="20" maxlength="20" />
                 <span class="erreur">${erreurs['prenom']}</span>
                <br />
                
                 <label for="nom">Nom <span class="requis">*</span></label>
                <input type="text" id="nom" name="nom" value="" size="20" maxlength="20" />
                 <span class="erreur">${erreurs['nom']}</span>
                <br />
                
                 <label for="telephone">Téléphone <span class="requis">*</span></label>
                <input type="text" id="telephone" name="telephone" value="" size="20" maxlength="10" />
                <span class="erreur">${erreurs['tel']}</span>
                <br />
				
				
				<label for="date">Date :</label>
                <input type="date" id="start" name="date"       
                 value=sysdate       
                  min=sysdate
                  max= sysdate +1 >
                 <br />
                  
				<label for="heures">Heures :</label>
				<SELECT name="nom" size="1">
				<OPTION>9h00
				<OPTION>9h30
				<OPTION>10h00
				<OPTION>10h30
				<OPTION>11h00
				<OPTION>11h30
				<OPTION>12h00
				<OPTION>14h00
				<OPTION>14h30
				<OPTION>15h00
				<OPTION>15h30
				<OPTION>16h00
				<OPTION>16h30
				<OPTION>17h00
				<OPTION>17h30
				</SELECT>
			 <min="09:00" max="18:00" required>
				<small> Ouvert de 9h à 18h</small>
				<br />
				

                <input type="submit" value="Valider" class="sansLabel" />
                <br />
            </fieldset>
        </form>
    </body>
</html>