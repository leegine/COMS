head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.06.27.06.08.49;	author zhang-tengyu;	state dead;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	444;
commitid	8d84e081ce81473;
filename	CommandDepositCodec.java;

1.1
date	2011.03.15.02.43.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	CommandDepositCodec.java;


desc
@@


1.2
log
@*** empty log message ***
@
text
@/**
 * This code was automatically generated at 17:28:54 on 2008/10/30
 * by weblogic.xml.schema.binding.internal.codegen.BeanCodecGenerator -- do not edit.
 *
 * @@version WebLogic Temporary Patch for CR112756 03/08/2004 13:53:04
 * @@author Copyright (c) 2008 by BEA Systems, Inc. All Rights Reserved.
 */

package com.gftforex.soap.api;

// original type: ['http://soap-api.gftforex.com']:CommandDeposit


public final class CommandDepositCodec 
  extends com.gftforex.soap.api.CommandTransferBaseCodec
{

  private static final int _SUPER_PROP_COUNT = 4;

  private static final weblogic.xml.stream.XMLName XML_TYPE = 
     weblogic.xml.stream.ElementFactory.createXMLName( "http://soap-api.gftforex.com" , "CommandDeposit" );

  private static final java.lang.String JAVA_TYPE = 
     "com.gftforex.soap.api.CommandDeposit";



  private static final weblogic.xml.schema.binding.util.runtime.PropertyInfo[] PROPS = 
  {

  };


  public void serialize(java.lang.Object obj,
                        weblogic.xml.stream.XMLName name,
                        weblogic.xml.stream.XMLOutputStream writer,
                        weblogic.xml.schema.binding.SerializationContext context)
    throws weblogic.xml.schema.binding.SerializationException
  {
    if ( (obj == null) || JAVA_TYPE.equals(obj.getClass().getName()) ) {
      serialize_internal(obj, name, writer, context);
    } else {
      weblogic.xml.schema.binding.RuntimeUtils.invoke_serializer(obj,
                                                                 obj.getClass(),
                                                                 name,
                                                                 writer,
                                                                 context);
    }
  }



  protected java.lang.Object createObject() {
    return new com.gftforex.soap.api.CommandDeposit();
  }

  protected weblogic.xml.stream.XMLName getXmlType() {
    return XML_TYPE;
  }





  protected java.lang.Object invokeGetter(java.lang.Object my_obj, int idx) {
    if (idx < _SUPER_PROP_COUNT) return super.invokeGetter(my_obj,idx);

    idx -= _SUPER_PROP_COUNT;	

    com.gftforex.soap.api.CommandDeposit typed_obj = (com.gftforex.soap.api.CommandDeposit) my_obj;
    return typedInvokeGetter(typed_obj, idx);
  }

  protected void invokeSetter(java.lang.Object my_obj, int idx,
                              java.lang.Object setter_arg)
  {
    if (idx < _SUPER_PROP_COUNT) {
      super.invokeSetter(my_obj,idx,setter_arg);
      return;
    }

    idx -= _SUPER_PROP_COUNT;	

    com.gftforex.soap.api.CommandDeposit typed_obj = (com.gftforex.soap.api.CommandDeposit) my_obj;
    typedInvokeSetter(typed_obj, idx, setter_arg);
  }

  public int getPropertyCount()
  {
    return (_SUPER_PROP_COUNT + PROPS.length);
  }

  public weblogic.xml.schema.binding.util.runtime.PropertyInfo getPropertyInfo(int idx)
  {
    if (idx < _SUPER_PROP_COUNT) return super.getPropertyInfo(idx);

    idx -= _SUPER_PROP_COUNT;	

    return PROPS[idx];
  }


  private static java.lang.Object typedInvokeGetter(com.gftforex.soap.api.CommandDeposit my_obj, 
                                          int idx) 
  {
    throw new java.lang.NoSuchFieldError("no properties");
  }


  private static void typedInvokeSetter(com.gftforex.soap.api.CommandDeposit my_obj, 
                                        int idx,
                                        java.lang.Object setter_arg) 
  {
    throw new java.lang.NoSuchFieldError("no properties");
  }

  protected weblogic.xml.schema.binding.ModelGroupCompositor getCompositor() {
    return weblogic.xml.schema.binding.ModelGroupCompositor.SEQUENCE ;
  }





  protected boolean writeXsiType(weblogic.xml.schema.binding.SerializationContext context) {
    return true;
  }
  







}
@


1.1
log
@*** empty log message ***
@
text
@@

