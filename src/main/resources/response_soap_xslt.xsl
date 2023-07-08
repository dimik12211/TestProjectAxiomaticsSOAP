<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    exclude-result-prefixes="xs"
    version="2.0">
    <xsl:template match="*">
        <xsl:element name="person">
            <xsl:call-template name="fillAttributesByElements"></xsl:call-template>
        </xsl:element>
    </xsl:template>
    <xsl:template name="fillAttributesByElements">
        <xsl:for-each select="*">
            <xsl:choose>
                <xsl:when test="not(*) and not(*[*]) and not(@*) and node()">
                    <xsl:attribute name="{local-name()}">
                        <xsl:value-of select="."/>
                    </xsl:attribute>
                </xsl:when>
            </xsl:choose>
        </xsl:for-each>
        <xsl:for-each select="*">
            <xsl:if test="* or @*">
                <xsl:choose>
                    <xsl:when test="local-name() = 'document'">
                        <xsl:element name="document">
                            <xsl:call-template name="fillOldAttributes"/>
                            <xsl:call-template name="fillAttributesByElements"/>
                        </xsl:element>
                    </xsl:when>
                </xsl:choose>
            </xsl:if>
        </xsl:for-each>
    </xsl:template>
    <xsl:template name="fillOldAttributes">
        <xsl:for-each select="@*">
            <xsl:attribute name="{local-name()}">
                <xsl:value-of select="."/>
            </xsl:attribute>
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>