<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xs="http://www.w3.org/2001/XMLSchema"
                exclude-result-prefixes="xs"
                version="2.0">
    <xsl:template match="*">
        <xsl:element name="person">
            <xsl:attribute name="name">
                <xsl:value-of select="name"/>
            </xsl:attribute>
            <xsl:attribute name="surname">
                <xsl:value-of select="surname"/>
            </xsl:attribute>
            <xsl:attribute name="patronymic">
                <xsl:value-of select="patronymic"/>
            </xsl:attribute>
            <xsl:attribute name="gender">
                <xsl:value-of select="gender"/>
            </xsl:attribute>
            <xsl:attribute name="birthDate">
                <xsl:value-of select="birthDate"/>
            </xsl:attribute>
            <xsl:element name="document">
                <xsl:attribute name="number">
                    <xsl:value-of select="//number"/>
                </xsl:attribute>
                <xsl:attribute name="type">
                    <xsl:value-of select="//type"/>
                </xsl:attribute>
                <xsl:attribute name="issueDate">
                    <xsl:value-of select="//issueDate"/>
                </xsl:attribute>
                <xsl:attribute name="series">
                    <xsl:value-of select="//series"/>
                </xsl:attribute>
            </xsl:element>
        </xsl:element>
    </xsl:template>
</xsl:stylesheet>