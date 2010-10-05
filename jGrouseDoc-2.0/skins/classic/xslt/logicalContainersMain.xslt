<!-- 
	jGrouseDoc template file. Renders overview of all logical containers (classes, namespaces, etc)
		- shown in main frame
	@Copyright (c) 2007 by Denis Riabtchik. All rights reserved. See license.txt and http://jgrouse.com for details@
	$Id: logicalContainersMain.xslt 249 2007-11-12 00:51:31Z denis.riabtchik $
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0">
	<xsl:param name='rootPath' />
	<xsl:param name="projectDesc"/>
	<xsl:param name='version'/>
    <xsl:param name='aux_css'>not_specified</xsl:param>
	
	<xsl:output method='HTML' doctype-public="-//W3C//DTD HTML 4.01//EN"
		doctype-system="http://www.w3.org/TR/html4/strict.dtd">

	</xsl:output>
	<xsl:import href="../../common/xslt/common.xslt"/>
	
	<xsl:template match="/">
		<xsl:comment>Generated by jGrouseDoc</xsl:comment>
	
		<html>
		    <head>
                <xsl:call-template name="writeCss">
                     <xsl:with-param name="rootPath"><xsl:value-of select="$rootPath"/></xsl:with-param>
                     <xsl:with-param name="aux_css"><xsl:value-of select="$aux_css"/></xsl:with-param>
                 </xsl:call-template>
			</head>
			<body>
			    <div class="logicalSummary">
                <xsl:call-template name="navbarLog">
                    <xsl:with-param name="isStartup">yes</xsl:with-param>
                </xsl:call-template>
                <br/>
				<H1><xsl:value-of select="$projectDesc"/></H1>			
				<br/>
				<xsl:if test="count(/jgdoc/project) != 0">
				    <div class="projectDescRef">
				        <a href="#desc">Project Description</a>
				    </div>
				</xsl:if>
				<xsl:if test="count(/jgdoc/items/*[(name() = 'class') or (name() = 'interface') or (name = 'struct') or (name = 'object')] ) != 0">
					<div width="100%" class="summaryTable">
						<div class="summaryTableHeader">
							Interfaces, Classes and Structures
						</div>
						<xsl:for-each select="/jgdoc/items/*[(name() = 'class') or (name() = 'interface') or (name() = 'struct') or (name() = 'object')]">
							<xsl:sort select="@id"/>
                            <div class="summaryItem">
								<div class="summaryItemDef">
								    <xsl:element name="span">
								         <xsl:attribute name="class"><xsl:value-of select="name()"/></xsl:attribute>
									<xsl:value-of select="name()"/>
									</xsl:element>
									<xsl:text> </xsl:text>
									<span class="summaryItemRef">
									<xsl:element name="a">
										<xsl:attribute name="href">logical/<xsl:value-of select="@path"/>.html</xsl:attribute>
										<xsl:value-of select="@id"/>
									</xsl:element>
									</span>
								</div>
								<div class="summaryItemDesc">
									<xsl:apply-templates select="comment/summary/content"/>
								</div>
                            </div>
						</xsl:for-each>
					</div>
					<br/>
				</xsl:if>
				<xsl:if test="count(/jgdoc/items/namespace) != 0">
					<div width="100%" class="summaryTable">
                        <div class="summaryTableHeader">
								Namespaces
						</div>
						<xsl:for-each select="/jgdoc/items/namespace">
							<xsl:sort select="@id"/>
                            <div class="summaryItem">
                                <div class="summaryItemDef">
                                    <span class="summaryItemRef">
	                                <xsl:element name="a">
	                                    <xsl:attribute name="href">logical/<xsl:value-of select="@path"/>.html</xsl:attribute>
	                                    <xsl:value-of select="@id"/>
	                                </xsl:element>
	                                </span>
                                </div>
                                <div class="summaryItemDesc">
	                               <xsl:apply-templates select="comment/summary/content"/>
                                </div>
                            </div>							
						</xsl:for-each>	
					</div>
					<br/>
				</xsl:if>
				<xsl:if test="count(/jgdoc/project) != 0">
				    <div class="projectDesc">
				    <a name="desc"></a><br/>
				    <xsl:apply-templates select='/jgdoc/project/comment/commentContent/content'/>
                    <p />
                    <xsl:apply-templates select="/jgdoc/project/comment" mode="genCommonAttrs" />
                    </div>
				</xsl:if>
				</div>
			</body>
		</html>
	</xsl:template>



</xsl:stylesheet>
