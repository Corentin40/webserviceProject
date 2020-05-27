<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
        
      <!-- Parcours des différents  -->  
      <xsl:for-each select="//PLANNING">
         
         <!-- Premier niveau de liste -->
         <dl>
            <!-- affichage du nom  -->
            <dt><xsl:value-of select="@nom" /></dt>
            
            <dt>
               <ul>
               
               <!-- Parcours des enfants du nœud  -->
               <xsl:for-each select="child::*">
                  
                  <!-- Affichage de l'attribut nom du nœud -->
                  <li><xsl:value-of select="@nom" />
                     
                     <ol>
                     
                     <!-- Parcours des enfants du nœud  -->
                     <xsl:for-each select="child::*">
                        
                        <li>
                           <xsl:value-of select="name()" /> : 
                           
                           <!-- Si c'est un CLIENT, on affiche nom et ... -->
                           <xsl:if test="name()='CLIENT'">                            
                               <xsl:value-of select="NOM" /> 
                               - <xsl:value-of select="PRENOM" />
							   - <xsl:value-of select="NUMERO" />
							   - <xsl:value-of select="EMAIL" />
							   - <xsl:value-of select="RDV" />
                           </xsl:if>
                           
                                         
                        </li>                        
                     </xsl:for-each>
                     </ol>
                     
                  </li>
               </xsl:for-each>
               
               </ul>      
            </dt>
            
        </dl>
      </xsl:for-each>
  </xsl:template>
</xsl:stylesheet>