head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.06.27.06.07.42;	author zhang-tengyu;	state dead;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	444;
commitid	8d84e081ce81473;
filename	CanTradeCodec.java;

1.1
date	2011.03.15.02.46.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	CanTradeCodec.java;


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

// original type: ['http://soap-api.gftforex.com']:CanTrade


public final class CanTradeCodec 
  extends weblogic.xml.schema.binding.BeanCodecBase
{

  private static final int _SUPER_PROP_COUNT = 0;

  private static final weblogic.xml.stream.XMLName XML_TYPE = 
     weblogic.xml.stream.ElementFactory.createXMLName( "http://soap-api.gftforex.com" , "CanTrade" );

  private static final java.lang.String JAVA_TYPE = 
     "com.gftforex.soap.api.CanTrade";



  private static final weblogic.xml.schema.binding.util.runtime.PropertyInfo[] PROPS = 
  {
  //package name = 
  //class   name = boolean
  //java    type = boolean
  //schema  name = ['http://soap-api.gftforex.com']:fx
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:boolean
  //array: false primitive: true  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","fx",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","boolean",null),
                                                            "fx",
                                                            boolean.class,
                                                            false,
                                                            true),


  //package name = 
  //class   name = boolean
  //java    type = boolean
  //schema  name = ['http://soap-api.gftforex.com']:fwd
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:boolean
  //array: false primitive: true  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","fwd",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","boolean",null),
                                                            "fwd",
                                                            boolean.class,
                                                            false,
                                                            true),


  //package name = 
  //class   name = boolean
  //java    type = boolean
  //schema  name = ['http://soap-api.gftforex.com']:cfd
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:boolean
  //array: false primitive: true  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","cfd",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","boolean",null),
                                                            "cfd",
                                                            boolean.class,
                                                            false,
                                                            true),


  //package name = 
  //class   name = boolean
  //java    type = boolean
  //schema  name = ['http://soap-api.gftforex.com']:futures
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:boolean
  //array: false primitive: true  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","futures",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","boolean",null),
                                                            "futures",
                                                            boolean.class,
                                                            false,
                                                            true),


  //package name = 
  //class   name = boolean
  //java    type = boolean
  //schema  name = ['http://soap-api.gftforex.com']:sb
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:boolean
  //array: false primitive: true  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","sb",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","boolean",null),
                                                            "sb",
                                                            boolean.class,
                                                            false,
                                                            true),



  };




  protected java.lang.Object createObject() {
    return new com.gftforex.soap.api.CanTrade();
  }

  protected weblogic.xml.stream.XMLName getXmlType() {
    return XML_TYPE;
  }



  protected boolean isPropertySet(Object my_obj, int idx) {

    com.gftforex.soap.api.CanTrade typed_obj = (com.gftforex.soap.api.CanTrade) my_obj;

    switch(idx) {

    case 0:
      return typed_obj._isSetFx();
    case 1:
      return typed_obj._isSetFwd();
    case 2:
      return typed_obj._isSetCfd();
    case 3:
      return typed_obj._isSetFutures();
    case 4:
      return typed_obj._isSetSb();


    default:
      return true;
    }
  }




  protected java.lang.Object invokeGetter(java.lang.Object my_obj, int idx) {

    com.gftforex.soap.api.CanTrade typed_obj = (com.gftforex.soap.api.CanTrade) my_obj;
    return typedInvokeGetter(typed_obj, idx);
  }

  protected void invokeSetter(java.lang.Object my_obj, int idx,
                              java.lang.Object setter_arg)
  {

    com.gftforex.soap.api.CanTrade typed_obj = (com.gftforex.soap.api.CanTrade) my_obj;
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


  private static java.lang.Object typedInvokeGetter(com.gftforex.soap.api.CanTrade my_obj, 
                                          int idx) 
  {
    java.lang.Object obj;

    switch(idx) {

    case 0:
      obj = new java.lang.Boolean(my_obj.getFx());
      break;
    case 1:
      obj = new java.lang.Boolean(my_obj.getFwd());
      break;
    case 2:
      obj = new java.lang.Boolean(my_obj.getCfd());
      break;
    case 3:
      obj = new java.lang.Boolean(my_obj.getFutures());
      break;
    case 4:
      obj = new java.lang.Boolean(my_obj.getSb());
      break;


    default:
      throw new java.lang.NoSuchFieldError("impossible case: " + idx);
    }

    return obj;

  }


  private static void typedInvokeSetter(com.gftforex.soap.api.CanTrade my_obj, 
                                        int idx,
                                        java.lang.Object setter_arg) 
  {
    switch(idx) {

    case 0:
      my_obj.setFx(((java.lang.Boolean)setter_arg).booleanValue());
      break;
    case 1:
      my_obj.setFwd(((java.lang.Boolean)setter_arg).booleanValue());
      break;
    case 2:
      my_obj.setCfd(((java.lang.Boolean)setter_arg).booleanValue());
      break;
    case 3:
      my_obj.setFutures(((java.lang.Boolean)setter_arg).booleanValue());
      break;
    case 4:
      my_obj.setSb(((java.lang.Boolean)setter_arg).booleanValue());
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

