head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.06.27.06.09.20;	author zhang-tengyu;	state dead;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	444;
commitid	8d84e081ce81473;
filename	UpdateUserInfoCodec.java;

1.1
date	2011.03.15.02.46.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	UpdateUserInfoCodec.java;


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

// original type: ['http://soap-api.gftforex.com']:UpdateUserInfo


public final class UpdateUserInfoCodec 
  extends weblogic.xml.schema.binding.BeanCodecBase
{

  private static final int _SUPER_PROP_COUNT = 0;

  private static final weblogic.xml.stream.XMLName XML_TYPE = 
     weblogic.xml.stream.ElementFactory.createXMLName( "http://soap-api.gftforex.com" , "UpdateUserInfo" );

  private static final java.lang.String JAVA_TYPE = 
     "com.gftforex.soap.api.UpdateUserInfo";



  private static final weblogic.xml.schema.binding.util.runtime.PropertyInfo[] PROPS = 
  {
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


  //package name = java.lang
  //class   name = [Ljava.lang.String;
  //java    type = [Ljava.lang.String;
  //schema  name = ['http://soap-api.gftforex.com']:GroupsToRemoveFrom
  //schema  type = ['http://soap-api.gftforex.com']:UpdateUserInfo__groupsToRemoveFrom__ArrayAnonType
  //array: true primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","GroupsToRemoveFrom",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","UpdateUserInfo__groupsToRemoveFrom__ArrayAnonType",null),
                                                            "groupsToRemoveFrom",
                                                            java.lang.String[].class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = [Ljava.lang.String;
  //java    type = [Ljava.lang.String;
  //schema  name = ['http://soap-api.gftforex.com']:GroupsToAddTo
  //schema  type = ['http://soap-api.gftforex.com']:UpdateUserInfo__groupsToAddTo__ArrayAnonType
  //array: true primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","GroupsToAddTo",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","UpdateUserInfo__groupsToAddTo__ArrayAnonType",null),
                                                            "groupsToAddTo",
                                                            java.lang.String[].class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://soap-api.gftforex.com']:Password
  //schema  type = ['http://soap-api.gftforex.com']:String32
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","Password",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","String32",null),
                                                            "password",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = com.gftforex.soap.api
  //class   name = [Lcom.gftforex.soap.api.UpdateAccountInfo;
  //java    type = [Lcom.gftforex.soap.api.UpdateAccountInfo;
  //schema  name = ['http://soap-api.gftforex.com']:Accounts
  //schema  type = ['http://soap-api.gftforex.com']:UpdateUserInfo__accounts__ArrayAnonType
  //array: true primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","Accounts",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","UpdateUserInfo__accounts__ArrayAnonType",null),
                                                            "accounts",
                                                            com.gftforex.soap.api.UpdateAccountInfo[].class,
                                                            false,
                                                            true),


  //package name = java.util
  //class   name = Calendar
  //java    type = java.util.Calendar
  //schema  name = ['http://soap-api.gftforex.com']:ExpireDate
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:date
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","ExpireDate",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","date",null),
                                                            "expireDate",
                                                            java.util.Calendar.class,
                                                            false,
                                                            true),


  //package name = 
  //class   name = boolean
  //java    type = boolean
  //schema  name = ['http://soap-api.gftforex.com']:IsDisabled
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:boolean
  //array: false primitive: true  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","IsDisabled",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","boolean",null),
                                                            "isDisabled",
                                                            boolean.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = [J
  //java    type = [J
  //schema  name = ['http://soap-api.gftforex.com']:ProfilesToAdd
  //schema  type = ['http://soap-api.gftforex.com']:UpdateUserInfo__profilesToAdd__ArrayAnonType
  //array: true primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","ProfilesToAdd",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","UpdateUserInfo__profilesToAdd__ArrayAnonType",null),
                                                            "profilesToAdd",
                                                            long[].class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = [J
  //java    type = [J
  //schema  name = ['http://soap-api.gftforex.com']:ProfilesToRemove
  //schema  type = ['http://soap-api.gftforex.com']:UpdateUserInfo__profilesToRemove__ArrayAnonType
  //array: true primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","ProfilesToRemove",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","UpdateUserInfo__profilesToRemove__ArrayAnonType",null),
                                                            "profilesToRemove",
                                                            long[].class,
                                                            false,
                                                            true),



  };




  protected java.lang.Object createObject() {
    return new com.gftforex.soap.api.UpdateUserInfo();
  }

  protected weblogic.xml.stream.XMLName getXmlType() {
    return XML_TYPE;
  }



  protected boolean isPropertySet(Object my_obj, int idx) {

    com.gftforex.soap.api.UpdateUserInfo typed_obj = (com.gftforex.soap.api.UpdateUserInfo) my_obj;

    switch(idx) {

    case 0:
      return typed_obj._isSetUserPersonalInfo();
    case 1:
      return typed_obj._isSetGroupsToRemoveFrom();
    case 2:
      return typed_obj._isSetGroupsToAddTo();
    case 3:
      return typed_obj._isSetPassword();
    case 4:
      return typed_obj._isSetAccounts();
    case 5:
      return typed_obj._isSetExpireDate();
    case 6:
      return typed_obj._isSetIsDisabled();
    case 7:
      return typed_obj._isSetProfilesToAdd();
    case 8:
      return typed_obj._isSetProfilesToRemove();


    default:
      return true;
    }
  }




  protected java.lang.Object invokeGetter(java.lang.Object my_obj, int idx) {

    com.gftforex.soap.api.UpdateUserInfo typed_obj = (com.gftforex.soap.api.UpdateUserInfo) my_obj;
    return typedInvokeGetter(typed_obj, idx);
  }

  protected void invokeSetter(java.lang.Object my_obj, int idx,
                              java.lang.Object setter_arg)
  {

    com.gftforex.soap.api.UpdateUserInfo typed_obj = (com.gftforex.soap.api.UpdateUserInfo) my_obj;
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


  private static java.lang.Object typedInvokeGetter(com.gftforex.soap.api.UpdateUserInfo my_obj, 
                                          int idx) 
  {
    java.lang.Object obj;

    switch(idx) {

    case 0:
      obj = my_obj.getUserPersonalInfo();
      break;
    case 1:
      obj = my_obj.getGroupsToRemoveFrom();
      break;
    case 2:
      obj = my_obj.getGroupsToAddTo();
      break;
    case 3:
      obj = my_obj.getPassword();
      break;
    case 4:
      obj = my_obj.getAccounts();
      break;
    case 5:
      obj = my_obj.getExpireDate();
      break;
    case 6:
      obj = new java.lang.Boolean(my_obj.getIsDisabled());
      break;
    case 7:
      obj = my_obj.getProfilesToAdd();
      break;
    case 8:
      obj = my_obj.getProfilesToRemove();
      break;


    default:
      throw new java.lang.NoSuchFieldError("impossible case: " + idx);
    }

    return obj;

  }


  private static void typedInvokeSetter(com.gftforex.soap.api.UpdateUserInfo my_obj, 
                                        int idx,
                                        java.lang.Object setter_arg) 
  {
    switch(idx) {

    case 0:
      my_obj.setUserPersonalInfo((com.gftforex.soap.api.UserPersonalInfo)setter_arg);
      break;
    case 1:
      my_obj.setGroupsToRemoveFrom((java.lang.String[])setter_arg);
      break;
    case 2:
      my_obj.setGroupsToAddTo((java.lang.String[])setter_arg);
      break;
    case 3:
      my_obj.setPassword((java.lang.String)setter_arg);
      break;
    case 4:
      my_obj.setAccounts((com.gftforex.soap.api.UpdateAccountInfo[])setter_arg);
      break;
    case 5:
      my_obj.setExpireDate((java.util.Calendar)setter_arg);
      break;
    case 6:
      my_obj.setIsDisabled(((java.lang.Boolean)setter_arg).booleanValue());
      break;
    case 7:
      my_obj.setProfilesToAdd((long[])setter_arg);
      break;
    case 8:
      my_obj.setProfilesToRemove((long[])setter_arg);
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

