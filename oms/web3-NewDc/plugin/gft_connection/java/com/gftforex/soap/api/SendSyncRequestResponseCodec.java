head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.06.27.06.06.30;	author zhang-tengyu;	state dead;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	444;
commitid	8d84e081ce81473;
filename	SendSyncRequestResponseCodec.java;

1.1
date	2011.03.15.02.45.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	SendSyncRequestResponseCodec.java;


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

// original type: ['http://soap-api.gftforex.com']:sendSyncRequestResponse


public final class SendSyncRequestResponseCodec 
  extends weblogic.xml.schema.binding.BeanCodecBase
{

  private static final int _SUPER_PROP_COUNT = 0;

  private static final weblogic.xml.stream.XMLName XML_TYPE = 
     weblogic.xml.stream.ElementFactory.createXMLName( "http://soap-api.gftforex.com" , "sendSyncRequestResponse" );

  private static final java.lang.String JAVA_TYPE = 
     "com.gftforex.soap.api.SendSyncRequestResponse";



  private static final weblogic.xml.schema.binding.util.runtime.PropertyInfo[] PROPS = 
  {
  //package name = com.gftforex.soap.api
  //class   name = RejectedCommand
  //java    type = com.gftforex.soap.api.RejectedCommand
  //schema  name = ['http://soap-api.gftforex.com']:RejectedCommand
  //schema  type = ['http://soap-api.gftforex.com']:RejectedCommand
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","RejectedCommand",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","RejectedCommand",null),
                                                            "rejectedCommand",
                                                            com.gftforex.soap.api.RejectedCommand.class,
                                                            false,
                                                            true),


  //package name = com.gftforex.soap.api
  //class   name = ResultInfoBase
  //java    type = com.gftforex.soap.api.ResultInfoBase
  //schema  name = ['http://soap-api.gftforex.com']:sendSyncRequestResult
  //schema  type = ['http://soap-api.gftforex.com']:ResultInfoBase
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","sendSyncRequestResult",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","ResultInfoBase",null),
                                                            "sendSyncRequestResult",
                                                            com.gftforex.soap.api.ResultInfoBase.class,
                                                            false,
                                                            true),



  };




  protected java.lang.Object createObject() {
    return new com.gftforex.soap.api.SendSyncRequestResponse();
  }

  protected weblogic.xml.stream.XMLName getXmlType() {
    return XML_TYPE;
  }



  protected boolean isPropertySet(Object my_obj, int idx) {

    com.gftforex.soap.api.SendSyncRequestResponse typed_obj = (com.gftforex.soap.api.SendSyncRequestResponse) my_obj;

    switch(idx) {

    case 0:
      return typed_obj._isSetRejectedCommand();
    case 1:
      return typed_obj._isSetSendSyncRequestResult();


    default:
      return true;
    }
  }




  protected java.lang.Object invokeGetter(java.lang.Object my_obj, int idx) {

    com.gftforex.soap.api.SendSyncRequestResponse typed_obj = (com.gftforex.soap.api.SendSyncRequestResponse) my_obj;
    return typedInvokeGetter(typed_obj, idx);
  }

  protected void invokeSetter(java.lang.Object my_obj, int idx,
                              java.lang.Object setter_arg)
  {

    com.gftforex.soap.api.SendSyncRequestResponse typed_obj = (com.gftforex.soap.api.SendSyncRequestResponse) my_obj;
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


  private static java.lang.Object typedInvokeGetter(com.gftforex.soap.api.SendSyncRequestResponse my_obj, 
                                          int idx) 
  {
    java.lang.Object obj;

    switch(idx) {

    case 0:
      obj = my_obj.getRejectedCommand();
      break;
    case 1:
      obj = my_obj.getSendSyncRequestResult();
      break;


    default:
      throw new java.lang.NoSuchFieldError("impossible case: " + idx);
    }

    return obj;

  }


  private static void typedInvokeSetter(com.gftforex.soap.api.SendSyncRequestResponse my_obj, 
                                        int idx,
                                        java.lang.Object setter_arg) 
  {
    switch(idx) {

    case 0:
      my_obj.setRejectedCommand((com.gftforex.soap.api.RejectedCommand)setter_arg);
      break;
    case 1:
      my_obj.setSendSyncRequestResult((com.gftforex.soap.api.ResultInfoBase)setter_arg);
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

