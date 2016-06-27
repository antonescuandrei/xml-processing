<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" version="5.0" doctype-system="about:legacy-compat" encoding="utf-8" />
    <xsl:template match="/">
        <html>
            <head>
                <title><xsl:value-of select="stock/@name" /></title>
                <link rel="stylesheet" type="text/css" href="style.css" />
            </head>
            <body>
                <h1><xsl:value-of select="stock/@name" /></h1>
                <xsl:for-each select="stock/ebooks/ebook">
                    <div class="header">
                        <span class="title"><xsl:value-of select="title"/></span> - <xsl:value-of select="isbn" />
                    </div>
                    <div class="description">
                        <xsl:text>Authors: </xsl:text>
                        <xsl:for-each select="authors/author">
                            <xsl:value-of select="." />
                            <xsl:if test="position() != last()">
                                <xsl:text>, </xsl:text>
                            </xsl:if>
                        </xsl:for-each>
                        <br />
                        <xsl:text>Devices: </xsl:text>
                        <xsl:for-each select="devices/device">
                            <xsl:value-of select="." />
                            <xsl:if test="position() != last()">
                                <xsl:text>, </xsl:text>
                            </xsl:if>
                        </xsl:for-each>
                        <br /><br />
                        <xsl:text>Price: </xsl:text>
                        <span class="title">$<xsl:value-of select="price" /></span>
                    </div>
                </xsl:for-each>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>