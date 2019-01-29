head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.06.27.06.12.10;	author zhang-tengyu;	state dead;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	444;
commitid	8d84e081ce81473;
filename	String15Serializer.java;

1.1
date	2011.03.15.02.43.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	String15Serializer.java;


desc
@@


1.2
log
@*** empty log message ***
@
text
@/**
 * This code was automatically generated at 17:28:54 on 2008/10/30
 * by weblogic.xml.schema.binding.internal.codegen.SimpleTypeSerializerGenerator -- do not edit.
 *
 * @@version WebLogic Temporary Patch for CR112756 03/08/2004 13:53:04
 * @@author Copyright (c) 2008 by BEA Systems, Inc. All Rights Reserved.
 */

package com.gftforex.soap.api;

// original type: ['http://soap-api.gftforex.com']:String15


public final class String15Serializer 
  extends weblogic.xml.schema.binding.internal.builtin.XSDStringSerializer
{
  private static final weblogic.xml.stream.XMLName __XML_TYPE = 
    weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","String15",null) ;

  protected weblogic.xml.stream.XMLName getXmlType() {
    return __XML_TYPE;
  }

  

  protected java.lang.String getContentFromObject(java.lang.Object obj, 
                                                  weblogic.xml.schema.binding.SerializationContext context) 
    throws weblogic.xml.schema.binding.SerializationException
    {
      if (context.isStrictValidation()) {
	try {
          String15Deserializer.validate(obj);
	} catch (weblogic.xml.schema.binding.DeserializationException de) {
          throw new weblogic.xml.schema.binding.SerializationException(de);
	}
      }
      return super.getContentFromObject(obj, context);
    }



}
@


1.1
log
@*** empty log message ***
@
text
@@

