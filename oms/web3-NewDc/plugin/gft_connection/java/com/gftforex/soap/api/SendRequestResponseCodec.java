head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.06.27.06.07.26;	author zhang-tengyu;	state dead;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	444;
commitid	8d84e081ce81473;
filename	SendRequestResponseCodec.java;

1.1
date	2011.03.15.02.41.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	SendRequestResponseCodec.java;


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

// original type: ['http://soap-api.gftforex.com']:sendRequestResponse


public final class SendRequestResponseCodec 
  extends weblogic.xml.schema.binding.BeanCodecBase
{

  private static final int _SUPER_PROP_COUNT = 0;

  private static final weblogic.xml.stream.XMLName XML_TYPE = 
     weblogic.xml.stream.ElementFactory.createXMLName( "http://soap-api.gftforex.com" , "sendRequestResponse" );

  private static final java.lang.String JAVA_TYPE = 
     "com.gftforex.soap.api.SendRequestResponse";



  private static final weblogic.xml.schema.binding.util.runtime.PropertyInfo[] PROPS = 
  {
  //package name = com.gftforex.soap.api
  //class   name = [Lcom.gftforex.soap.api.RejectedCommand;
  //java    type = [Lcom.gftforex.soap.api.RejectedCommand;
  //schema  name = ['http://soap-api.gftforex.com']:RejectedCommand
  //schema  type = ['http://soap-api.gftforex.com']:sendRequestResponse__rejectedCommand__ArrayAnonType
  //array: true primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","RejectedCommand",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","sendRequestResponse__rejectedCommand__ArrayAnonType",null),
                                                            "rejectedCommand",
                                                            com.gftforex.soap.api.RejectedCommand[].class,
                                                            false,
                                                            true),


  //package name = 
  //class   name = double
  //java    type = double
  //schema  name = ['http://soap-api.gftforex.com']:ResponseWaitTime
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:double
  //array: false primitive: true  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","ResponseWaitTime",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","double",null),
                                                            "responseWaitTime",
                                                            double.class,
                                                            false,
                                                            true),



  };




  protected java.lang.Object createObject() {
    return new com.gftforex.soap.api.SendRequestResponse();
  }

  protected weblogic.xml.stream.XMLName getXmlType() {
    return XML_TYPE;
  }



  protected boolean isPropertySet(Object my_obj, int idx) {

    com.gftforex.soap.api.SendRequestResponse typed_obj = (com.gftforex.soap.api.SendRequestResponse) my_obj;

    switch(idx) {

    case 0:
      return typed_obj._isSetRejectedCommand();


    default:
      return true;
    }
  }




  protected java.lang.Object invokeGetter(java.lang.Object my_obj, int idx) {

    com.gftforex.soap.api.SendRequestResponse typed_obj = (com.gftforex.soap.api.SendRequestResponse) my_obj;
    return typedInvokeGetter(typed_obj, idx);
  }

  protected void invokeSetter(java.lang.Object my_obj, int idx,
                              java.lang.Object setter_arg)
  {

    com.gftforex.soap.api.SendRequestResponse typed_obj = (com.gftforex.soap.api.SendRequestResponse) my_obj;
    typedInvokeSetter(typed_obj, idx, setter_arg);
  }

  public int getPropertyCount()
  {
    return (_SUPER_PROP_COUNT + PROPS.length);
  }

  public weblogic.xml.schema.binding.util.runtime.PropertyInfo getPropertyInfo(int idx)
  {

    return PROPS[idx];
  }


  private static java.lang.Object typedInvokeGetter(com.gftforex.soap.api.SendRequestResponse my_obj, 
                                          int idx) 
  {
    java.lang.Object obj;

    switch(idx) {

    case 0:
      obj = my_obj.getRejectedCommand();
      break;
    case 1:
      obj = new java.lang.Double(my_obj.getResponseWaitTime());
      break;


    default:
      throw new java.lang.NoSuchFieldError("impossible case: " + idx);
    }

    return obj;

  }


  private static void typedInvokeSetter(com.gftforex.soap.api.SendRequestResponse my_obj, 
                                        int idx,
                                        java.lang.Object setter_arg) 
  {
    switch(idx) {

    case 0:
      my_obj.setRejectedCommand((com.gftforex.soap.api.RejectedCommand[])setter_arg);
      break;
    case 1:
      my_obj.setResponseWaitTime(((java.lang.Number)setter_arg).doubleValue());
      break;


    default:
      throw new java.lang.NoSuchFieldError("impossible case: " + idx);
    }

  }

  protected weblogic.xml.schema.binding.ModelGroupCompositor getCompositor() {
    return weblogic.xml.schema.binding.ModelGroupCompositor.SEQUENCE ;
  }





  protected boolean writeXsiType(weblogic.xml.schema.binding.SerializationContext context) {
    return false;
  }
  







}
@


1.1
log
@*** empty log message ***
@
text
@@

