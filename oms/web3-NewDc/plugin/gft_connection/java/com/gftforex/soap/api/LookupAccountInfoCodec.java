head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.06.27.06.08.36;	author zhang-tengyu;	state dead;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	444;
commitid	8d84e081ce81473;
filename	LookupAccountInfoCodec.java;

1.1
date	2011.03.15.02.45.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	LookupAccountInfoCodec.java;


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

// original type: ['http://soap-api.gftforex.com']:LookupAccountInfo


public final class LookupAccountInfoCodec 
  extends weblogic.xml.schema.binding.BeanCodecBase
{

  private static final int _SUPER_PROP_COUNT = 0;

  private static final weblogic.xml.stream.XMLName XML_TYPE = 
     weblogic.xml.stream.ElementFactory.createXMLName( "http://soap-api.gftforex.com" , "LookupAccountInfo" );

  private static final java.lang.String JAVA_TYPE = 
     "com.gftforex.soap.api.LookupAccountInfo";



  private static final weblogic.xml.schema.binding.util.runtime.PropertyInfo[] PROPS = 
  {
  //package name = 
  //class   name = long
  //java    type = long
  //schema  name = ['http://soap-api.gftforex.com']:AccountId
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:unsignedInt
  //array: false primitive: true  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","AccountId",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","unsignedInt",null),
                                                            "accountId",
                                                            long.class,
                                                            false,
                                                            true),


  //package name = com.gftforex.soap.api
  //class   name = CanTrade
  //java    type = com.gftforex.soap.api.CanTrade
  //schema  name = ['http://soap-api.gftforex.com']:CanTrade
  //schema  type = ['http://soap-api.gftforex.com']:CanTrade
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","CanTrade",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","CanTrade",null),
                                                            "canTrade",
                                                            com.gftforex.soap.api.CanTrade.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://soap-api.gftforex.com']:BaseCurrency
  //schema  type = ['http://soap-api.gftforex.com']:Currency
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","BaseCurrency",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","Currency",null),
                                                            "baseCurrency",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = 
  //class   name = long
  //java    type = long
  //schema  name = ['http://soap-api.gftforex.com']:LotSize
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:unsignedInt
  //array: false primitive: true  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","LotSize",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","unsignedInt",null),
                                                            "lotSize",
                                                            long.class,
                                                            false,
                                                            true),


  //package name = 
  //class   name = double
  //java    type = double
  //schema  name = ['http://soap-api.gftforex.com']:WithdrawableAmount
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:double
  //array: false primitive: true  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","WithdrawableAmount",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","double",null),
                                                            "withdrawableAmount",
                                                            double.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://soap-api.gftforex.com']:Label
  //schema  type = ['http://soap-api.gftforex.com']:String64
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","Label",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","String64",null),
                                                            "label",
                                                            java.lang.String.class,
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
  //schema  name = ['http://soap-api.gftforex.com']:Profiles
  //schema  type = ['http://soap-api.gftforex.com']:LookupAccountInfo__profiles__ArrayAnonType
  //array: true primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","Profiles",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","LookupAccountInfo__profiles__ArrayAnonType",null),
                                                            "profiles",
                                                            long[].class,
                                                            false,
                                                            true),



  };




  protected java.lang.Object createObject() {
    return new com.gftforex.soap.api.LookupAccountInfo();
  }

  protected weblogic.xml.stream.XMLName getXmlType() {
    return XML_TYPE;
  }



  protected boolean isPropertySet(Object my_obj, int idx) {

    com.gftforex.soap.api.LookupAccountInfo typed_obj = (com.gftforex.soap.api.LookupAccountInfo) my_obj;

    switch(idx) {

    case 5:
      return typed_obj._isSetLabel();
    case 7:
      return typed_obj._isSetProfiles();


    default:
      return true;
    }
  }




  protected java.lang.Object invokeGetter(java.lang.Object my_obj, int idx) {

    com.gftforex.soap.api.LookupAccountInfo typed_obj = (com.gftforex.soap.api.LookupAccountInfo) my_obj;
    return typedInvokeGetter(typed_obj, idx);
  }

  protected void invokeSetter(java.lang.Object my_obj, int idx,
                              java.lang.Object setter_arg)
  {

    com.gftforex.soap.api.LookupAccountInfo typed_obj = (com.gftforex.soap.api.LookupAccountInfo) my_obj;
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


  private static java.lang.Object typedInvokeGetter(com.gftforex.soap.api.LookupAccountInfo my_obj, 
                                          int idx) 
  {
    java.lang.Object obj;

    switch(idx) {

    case 0:
      obj = new java.lang.Long(my_obj.getAccountId());
      break;
    case 1:
      obj = my_obj.getCanTrade();
      break;
    case 2:
      obj = my_obj.getBaseCurrency();
      break;
    case 3:
      obj = new java.lang.Long(my_obj.getLotSize());
      break;
    case 4:
      obj = new java.lang.Double(my_obj.getWithdrawableAmount());
      break;
    case 5:
      obj = my_obj.getLabel();
      break;
    case 6:
      obj = new java.lang.Boolean(my_obj.getIsDisabled());
      break;
    case 7:
      obj = my_obj.getProfiles();
      break;


    default:
      throw new java.lang.NoSuchFieldError("impossible case: " + idx);
    }

    return obj;

  }


  private static void typedInvokeSetter(com.gftforex.soap.api.LookupAccountInfo my_obj, 
                                        int idx,
                                        java.lang.Object setter_arg) 
  {
    switch(idx) {

    case 0:
      my_obj.setAccountId(((java.lang.Number)setter_arg).longValue());
      break;
    case 1:
      my_obj.setCanTrade((com.gftforex.soap.api.CanTrade)setter_arg);
      break;
    case 2:
      my_obj.setBaseCurrency((java.lang.String)setter_arg);
      break;
    case 3:
      my_obj.setLotSize(((java.lang.Number)setter_arg).longValue());
      break;
    case 4:
      my_obj.setWithdrawableAmount(((java.lang.Number)setter_arg).doubleValue());
      break;
    case 5:
      my_obj.setLabel((java.lang.String)setter_arg);
      break;
    case 6:
      my_obj.setIsDisabled(((java.lang.Boolean)setter_arg).booleanValue());
      break;
    case 7:
      my_obj.setProfiles((long[])setter_arg);
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

