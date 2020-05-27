<?xml version="1.0" encoding="utf-8"?>
<!-- Définition du namespace à utiliser : ici, xsl -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <!-- Récupération des paramètres côté XSL -->
  <xsl:param name="bgColor" />
  <xsl:param name="fgColor" />
  <xsl:param name="contentBgColor" />

  <!-- Définition du template XSL et définition du nœud racine à utiliser, ici "/" -->
  <xsl:template match="/">
    
    	<!-- On crée les entêtes de notre tableau et on utilise les paramètres-->
        <table width="40%" border="1" cellspacing="0" cellpadding="0">
          <tr style="color:{$fgColor};background-color:{$bgColor}">
            <th>Nom</th>
            <th>Prenom</th>
			<th>Numero</th>
			<th>Mail</th>
			<th>RDV</th>
          </tr>
          
          <!-- On parcourt maintenant notre fichier pour remplir le tableau -->
          <xsl:for-each select="//PLANNIG/CLIENT">
          
            <tr style="text-align:center;background-color:{$contentBgColor}">
              <td>
                <xsl:value-of select="NOM" />
              </td>
              <td>
                <xsl:value-of select="PRENOM" />
              </td>
			  <td>
                <xsl:value-of select="NUMERO" />
              </td>
			  <td>
                <xsl:value-of select="EMAIL" />
              </td>
			  <td>
                <xsl:value-of select="RDV" />
              </td>
            </tr>
            
          </xsl:for-each>
        </table>
    
  </xsl:template>
</xsl:stylesheet>