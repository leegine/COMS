head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.06.27.06.06.51;	author zhang-tengyu;	state dead;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	444;
commitid	8d84e081ce81473;
filename	CommandIDDeserializer.java;

1.1
date	2011.03.15.02.43.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	CommandIDDeserializer.java;


desc
@@


1.2
log
@*** empty log message ***
@
text
@/**
 * This code was automatically generated at 17:28:54 on 2008/10/30
 * by weblogic.xml.schema.binding.internal.codegen.SimpleTypeDeserializerGenerator -- do not edit.
 *
 * @@version WebLogic Temporary Patch for CR112756 03/08/2004 13:53:04
 * @@author Copyright (c) 2008 by BEA Systems, Inc. All Rights Reserved.
 */

package com.gftforex.soap.api;

// original type: ['http://soap-api.gftforex.com']:CommandID


public final class CommandIDDeserializer 
  extends weblogic.xml.schema.binding.internal.builtin.XSDTokenDeserializer
{

  protected java.lang.Object getObjectFromContent(java.lang.String content,
                                                  weblogic.xml.schema.binding.DeserializationContext context) 
    throws weblogic.xml.schema.binding.DeserializationException
    {
      java.lang.Object _obj = super.getObjectFromContent(content, context);
      if (context.isStrictValidation()) {
        validate(_obj);
      }
      return _obj;
    }


  protected static void validate(java.lang.Object obj) 
    throws weblogic.xml.schema.binding.DeserializationException
  {

    java.lang.String __typed_obj = (java.lang.String) obj;

    weblogic.xml.schema.binding.FacetUtils.checkMinLengthFacet(__typed_obj,5L);
    weblogic.xml.schema.binding.FacetUtils.checkMaxLengthFacet(__typed_obj,50L);

  }





}
@


1.1
log
@*** empty log message ***
@
text
@@

