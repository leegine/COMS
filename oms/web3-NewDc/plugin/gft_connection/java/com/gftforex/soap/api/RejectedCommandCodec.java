head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.06.27.06.09.32;	author zhang-tengyu;	state dead;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	444;
commitid	8d84e081ce81473;
filename	RejectedCommandCodec.java;

1.1
date	2011.03.15.02.43.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	RejectedCommandCodec.java;


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

// original type: ['http://soap-api.gftforex.com']:RejectedCommand


public final class RejectedCommandCodec 
  extends weblogic.xml.schema.binding.BeanCodecBase
{

  private static final int _SUPER_PROP_COUNT = 0;

  private static final weblogic.xml.stream.XMLName XML_TYPE = 
     weblogic.xml.stream.ElementFactory.createXMLName( "http://soap-api.gftforex.com" , "RejectedCommand" );

  private static final java.lang.String JAVA_TYPE = 
     "com.gftforex.soap.api.RejectedCommand";



  private static final weblogic.xml.schema.binding.util.runtime.PropertyInfo[] PROPS = 
  {
  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://soap-api.gftforex.com']:RejectedCommandId
  //schema  type = ['http://soap-api.gftforex.com']:CommandID
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","RejectedCommandId",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","CommandID",null),
                                                            "rejectedCommandId",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = 
  //class   name = int
  //java    type = int
  //schema  name = ['http://soap-api.gftforex.com']:ErrorCode
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:unsignedShort
  //array: false primitive: true  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","ErrorCode",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","unsignedShort",null),
                                                            "errorCode",
                                                            int.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://soap-api.gftforex.com']:ErrorMessage
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:string
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","ErrorMessage",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","string",null),
                                                            "errorMessage",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = 
  //class   name = int
  //java    type = int
  //schema  name = ['http://soap-api.gftforex.com']:MajorErrorCode
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:unsignedShort
  //array: false primitive: true  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","MajorErrorCode",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","unsignedShort",null),
                                                            "majorErrorCode",
                                                            int.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://soap-api.gftforex.com']:MajorErrorMessage
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:string
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","MajorErrorMessage",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","string",null),
                                                            "majorErrorMessage",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = 
  //class   name = int
  //java    type = int
  //schema  name = ['http://soap-api.gftforex.com']:MinorErrorCode
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:unsignedShort
  //array: false primitive: true  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","MinorErrorCode",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","unsignedShort",null),
                                                            "minorErrorCode",
                                                            int.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://soap-api.gftforex.com']:MinorErrorMessage
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:string
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","MinorErrorMessage",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","string",null),
                                                            "minorErrorMessage",
                                                            java.lang.String.class,
                                                            false,
                                                            true),



  };




  protected java.lang.Object createObject() {
    return new com.gftforex.soap.api.RejectedCommand();
  }

  protected weblogic.xml.stream.XMLName getXmlType() {
    return XML_TYPE;
  }



  protected boolean isPropertySet(Object my_obj, int idx) {

    com.gftforex.soap.api.RejectedCommand typed_obj = (com.gftforex.soap.api.RejectedCommand) my_obj;

    switch(idx) {

    case 5:
      return typed_obj._isSetMinorErrorCode();
    case 6:
      return typed_obj._isSetMinorErrorMessage();


    default:
      return true;
    }
  }




  protected java.lang.Object invokeGetter(java.lang.Object my_obj, int idx) {

    com.gftforex.soap.api.RejectedCommand typed_obj = (com.gftforex.soap.api.RejectedCommand) my_obj;
    return typedInvokeGetter(typed_obj, idx);
  }

  protected void invokeSetter(java.lang.Object my_obj, int idx,
                              java.lang.Object setter_arg)
  {

    com.gftforex.soap.api.RejectedCommand typed_obj = (com.gftforex.soap.api.RejectedCommand) my_obj;
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


  private static java.lang.Object typedInvokeGetter(com.gftforex.soap.api.RejectedCommand my_obj, 
                                          int idx) 
  {
    java.lang.Object obj;

    switch(idx) {

    case 0:
      obj = my_obj.getRejectedCommandId();
      break;
    case 1:
      obj = new java.lang.Integer(my_obj.getErrorCode());
      break;
    case 2:
      obj = my_obj.getErrorMessage();
      break;
    case 3:
      obj = new java.lang.Integer(my_obj.getMajorErrorCode());
      break;
    case 4:
      obj = my_obj.getMajorErrorMessage();
      break;
    case 5:
      obj = new java.lang.Integer(my_obj.getMinorErrorCode());
      break;
    case 6:
      obj = my_obj.getMinorErrorMessage();
      break;


    default:
      throw new java.lang.NoSuchFieldError("impossible case: " + idx);
    }

    return obj;

  }


  private static void typedInvokeSetter(com.gftforex.soap.api.RejectedCommand my_obj, 
                                        int idx,
                                        java.lang.Object setter_arg) 
  {
    switch(idx) {

    case 0:
      my_obj.setRejectedCommandId((java.lang.String)setter_arg);
      break;
    case 1:
      my_obj.setErrorCode(((java.lang.Number)setter_arg).intValue());
      break;
    case 2:
      my_obj.setErrorMessage((java.lang.String)setter_arg);
      break;
    case 3:
      my_obj.setMajorErrorCode(((java.lang.Number)setter_arg).intValue());
      break;
    case 4:
      my_obj.setMajorErrorMessage((java.lang.String)setter_arg);
      break;
    case 5:
      my_obj.setMinorErrorCode(((java.lang.Number)setter_arg).intValue());
      break;
    case 6:
      my_obj.setMinorErrorMessage((java.lang.String)setter_arg);
      break;


    default:
      throw new java.lang.NoSuchFieldError("impossible case: " + idx);
    }

  }

  protected weblogic.xml.schema.binding.ModelGroupCompositor getCompositor() {
    return weblogic.xml.schema.binding.ModelGroupCompositor.SEQUENCE ;
  }





  







}
@


1.1
log
@*** empty log message ***
@
text
@@

