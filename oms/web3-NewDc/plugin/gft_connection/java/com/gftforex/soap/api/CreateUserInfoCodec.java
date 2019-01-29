head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.06.27.06.08.43;	author zhang-tengyu;	state dead;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	444;
commitid	8d84e081ce81473;
filename	CreateUserInfoCodec.java;

1.1
date	2011.03.15.02.42.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	CreateUserInfoCodec.java;


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

// original type: ['http://soap-api.gftforex.com']:CreateUserInfo


public final class CreateUserInfoCodec 
  extends weblogic.xml.schema.binding.BeanCodecBase
{

  private static final int _SUPER_PROP_COUNT = 0;

  private static final weblogic.xml.stream.XMLName XML_TYPE = 
     weblogic.xml.stream.ElementFactory.createXMLName( "http://soap-api.gftforex.com" , "CreateUserInfo" );

  private static final java.lang.String JAVA_TYPE = 
     "com.gftforex.soap.api.CreateUserInfo";



  private static final weblogic.xml.schema.binding.util.runtime.PropertyInfo[] PROPS = 
  {
  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://soap-api.gftforex.com']:UserTemplateId
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:string
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","UserTemplateId",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","string",null),
                                                            "userTemplateId",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = com.gftforex.soap.api
  //class   name = UserPersonalInfo
  //java    type = com.gftforex.soap.api.UserPersonalInfo
  //schema  name = ['http://soap-api.gftforex.com']:UserPersonalInfo
  //schema  type = ['http://soap-api.gftforex.com']:UserPersonalInfo
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","UserPersonalInfo",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","UserPersonalInfo",null),
                                                            "userPersonalInfo",
                                                            com.gftforex.soap.api.UserPersonalInfo.class,
                                                            false,
                                                            true),


  //package name = com.gftforex.soap.api
  //class   name = UserSystemInfo
  //java    type = com.gftforex.soap.api.UserSystemInfo
  //schema  name = ['http://soap-api.gftforex.com']:UserSystemInfo
  //schema  type = ['http://soap-api.gftforex.com']:UserSystemInfo
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","UserSystemInfo",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","UserSystemInfo",null),
                                                            "userSystemInfo",
                                                            com.gftforex.soap.api.UserSystemInfo.class,
                                                            false,
                                                            true),


  //package name = com.gftforex.soap.api
  //class   name = [Lcom.gftforex.soap.api.CreateAccountInfo;
  //java    type = [Lcom.gftforex.soap.api.CreateAccountInfo;
  //schema  name = ['http://soap-api.gftforex.com']:Accounts
  //schema  type = ['http://soap-api.gftforex.com']:CreateUserInfo__accounts__ArrayAnonType
  //array: true primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","Accounts",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","CreateUserInfo__accounts__ArrayAnonType",null),
                                                            "accounts",
                                                            com.gftforex.soap.api.CreateAccountInfo[].class,
                                                            false,
                                                            true),



  };




  protected java.lang.Object createObject() {
    return new com.gftforex.soap.api.CreateUserInfo();
  }

  protected weblogic.xml.stream.XMLName getXmlType() {
    return XML_TYPE;
  }



  protected boolean isPropertySet(Object my_obj, int idx) {

    com.gftforex.soap.api.CreateUserInfo typed_obj = (com.gftforex.soap.api.CreateUserInfo) my_obj;

    switch(idx) {

    case 0:
      return typed_obj._isSetUserTemplateId();
    case 1:
      return typed_obj._isSetUserPersonalInfo();
    case 2:
      return typed_obj._isSetUserSystemInfo();
    case 3:
      return typed_obj._isSetAccounts();


    default:
      return true;
    }
  }




  protected java.lang.Object invokeGetter(java.lang.Object my_obj, int idx) {

    com.gftforex.soap.api.CreateUserInfo typed_obj = (com.gftforex.soap.api.CreateUserInfo) my_obj;
    return typedInvokeGetter(typed_obj, idx);
  }

  protected void invokeSetter(java.lang.Object my_obj, int idx,
                              java.lang.Object setter_arg)
  {

    com.gftforex.soap.api.CreateUserInfo typed_obj = (com.gftforex.soap.api.CreateUserInfo) my_obj;
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


  private static java.lang.Object typedInvokeGetter(com.gftforex.soap.api.CreateUserInfo my_obj, 
                                          int idx) 
  {
    java.lang.Object obj;

    switch(idx) {

    case 0:
      obj = my_obj.getUserTemplateId();
      break;
    case 1:
      obj = my_obj.getUserPersonalInfo();
      break;
    case 2:
      obj = my_obj.getUserSystemInfo();
      break;
    case 3:
      obj = my_obj.getAccounts();
      break;


    default:
      throw new java.lang.NoSuchFieldError("impossible case: " + idx);
    }

    return obj;

  }


  private static void typedInvokeSetter(com.gftforex.soap.api.CreateUserInfo my_obj, 
                                        int idx,
                                        java.lang.Object setter_arg) 
  {
    switch(idx) {

    case 0:
      my_obj.setUserTemplateId((java.lang.String)setter_arg);
      break;
    case 1:
      my_obj.setUserPersonalInfo((com.gftforex.soap.api.UserPersonalInfo)setter_arg);
      break;
    case 2:
      my_obj.setUserSystemInfo((com.gftforex.soap.api.UserSystemInfo)setter_arg);
      break;
    case 3:
      my_obj.setAccounts((com.gftforex.soap.api.CreateAccountInfo[])setter_arg);
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

