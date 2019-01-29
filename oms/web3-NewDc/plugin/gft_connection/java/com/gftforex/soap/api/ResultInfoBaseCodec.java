head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.06.27.06.09.01;	author zhang-tengyu;	state dead;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	444;
commitid	8d84e081ce81473;
filename	ResultInfoBaseCodec.java;

1.1
date	2011.03.15.02.43.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	ResultInfoBaseCodec.java;


desc
@@


1.2
log
@*** empty log message ***
@
text
@/**
 * This code was automatically generated at 17:28:55 on 2008/10/30
 * by weblogic.xml.schema.binding.internal.codegen.BeanCodecGenerator -- do not edit.
 *
 * @@version WebLogic Temporary Patch for CR112756 03/08/2004 13:53:04
 * @@author Copyright (c) 2008 by BEA Systems, Inc. All Rights Reserved.
 */

package com.gftforex.soap.api;

// original type: ['http://soap-api.gftforex.com']:ResultInfoBase


public  class ResultInfoBaseCodec 
  extends weblogic.xml.schema.binding.BeanCodecBase
{

  private static final int _SUPER_PROP_COUNT = 0;

  private static final weblogic.xml.stream.XMLName XML_TYPE = 
     weblogic.xml.stream.ElementFactory.createXMLName( "http://soap-api.gftforex.com" , "ResultInfoBase" );

  private static final java.lang.String JAVA_TYPE = 
     "com.gftforex.soap.api.ResultInfoBase";



  private static final weblogic.xml.schema.binding.util.runtime.PropertyInfo[] PROPS = 
  {
  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://soap-api.gftforex.com']:CommandID
  //schema  type = ['http://soap-api.gftforex.com']:CommandID
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","CommandID",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","CommandID",null),
                                                            "commandID",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = 
  //class   name = int
  //java    type = int
  //schema  name = ['http://soap-api.gftforex.com']:StatusCode
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:unsignedShort
  //array: false primitive: true  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","StatusCode",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","unsignedShort",null),
                                                            "statusCode",
                                                            int.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://soap-api.gftforex.com']:StatusMessage
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:string
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","StatusMessage",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","string",null),
                                                            "statusMessage",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = 
  //class   name = int
  //java    type = int
  //schema  name = ['http://soap-api.gftforex.com']:MajorStatusCode
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:unsignedShort
  //array: false primitive: true  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","MajorStatusCode",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","unsignedShort",null),
                                                            "majorStatusCode",
                                                            int.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://soap-api.gftforex.com']:MajorStatusMessage
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:string
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","MajorStatusMessage",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","string",null),
                                                            "majorStatusMessage",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = 
  //class   name = int
  //java    type = int
  //schema  name = ['http://soap-api.gftforex.com']:MinorStatusCode
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:unsignedShort
  //array: false primitive: true  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","MinorStatusCode",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","unsignedShort",null),
                                                            "minorStatusCode",
                                                            int.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://soap-api.gftforex.com']:MinorStatusMessage
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:string
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","MinorStatusMessage",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","string",null),
                                                            "minorStatusMessage",
                                                            java.lang.String.class,
                                                            false,
                                                            true),



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
    return new com.gftforex.soap.api.ResultInfoBase();
  }

  protected weblogic.xml.stream.XMLName getXmlType() {
    return XML_TYPE;
  }



  protected boolean isPropertySet(Object my_obj, int idx) {

    com.gftforex.soap.api.ResultInfoBase typed_obj = (com.gftforex.soap.api.ResultInfoBase) my_obj;

    switch(idx) {

    case 2:
      return typed_obj._isSetStatusMessage();
    case 5:
      return typed_obj._isSetMinorStatusCode();
    case 6:
      return typed_obj._isSetMinorStatusMessage();


    default:
      return true;
    }
  }




  protected java.lang.Object invokeGetter(java.lang.Object my_obj, int idx) {

    com.gftforex.soap.api.ResultInfoBase typed_obj = (com.gftforex.soap.api.ResultInfoBase) my_obj;
    return typedInvokeGetter(typed_obj, idx);
  }

  protected void invokeSetter(java.lang.Object my_obj, int idx,
                              java.lang.Object setter_arg)
  {

    com.gftforex.soap.api.ResultInfoBase typed_obj = (com.gftforex.soap.api.ResultInfoBase) my_obj;
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


  private static java.lang.Object typedInvokeGetter(com.gftforex.soap.api.ResultInfoBase my_obj, 
                                          int idx) 
  {
    java.lang.Object obj;

    switch(idx) {

    case 0:
      obj = my_obj.getCommandID();
      break;
    case 1:
      obj = new java.lang.Integer(my_obj.getStatusCode());
      break;
    case 2:
      obj = my_obj.getStatusMessage();
      break;
    case 3:
      obj = new java.lang.Integer(my_obj.getMajorStatusCode());
      break;
    case 4:
      obj = my_obj.getMajorStatusMessage();
      break;
    case 5:
      obj = new java.lang.Integer(my_obj.getMinorStatusCode());
      break;
    case 6:
      obj = my_obj.getMinorStatusMessage();
      break;


    default:
      throw new java.lang.NoSuchFieldError("impossible case: " + idx);
    }

    return obj;

  }


  private static void typedInvokeSetter(com.gftforex.soap.api.ResultInfoBase my_obj, 
                                        int idx,
                                        java.lang.Object setter_arg) 
  {
    switch(idx) {

    case 0:
      my_obj.setCommandID((java.lang.String)setter_arg);
      break;
    case 1:
      my_obj.setStatusCode(((java.lang.Number)setter_arg).intValue());
      break;
    case 2:
      my_obj.setStatusMessage((java.lang.String)setter_arg);
      break;
    case 3:
      my_obj.setMajorStatusCode(((java.lang.Number)setter_arg).intValue());
      break;
    case 4:
      my_obj.setMajorStatusMessage((java.lang.String)setter_arg);
      break;
    case 5:
      my_obj.setMinorStatusCode(((java.lang.Number)setter_arg).intValue());
      break;
    case 6:
      my_obj.setMinorStatusMessage((java.lang.String)setter_arg);
      break;


    default:
      throw new java.lang.NoSuchFieldError("impossible case: " + idx);
    }

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

