head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.37.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	EntryRiyousyaInCodec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 * This code was automatically generated at 13:33:46 on 2008/07/29
 * by weblogic.xml.schema.binding.internal.codegen.BeanCodecGenerator -- do not edit.
 *
 * @@version WebLogic Temporary Patch for CR362079 Thu Feb 21 18:25:04 2008
 * @@author Copyright (c) 2008 by BEA Systems, Inc. All Rights Reserved.
 */

package jp.co.hitachi.www.TFX.WebService;

// original type: ['http://www.hitachi.co.jp/TFX/WebService']:EntryRiyousyaIn


public final class EntryRiyousyaInCodec 
  extends weblogic.xml.schema.binding.BeanCodecBase
{

  private static final int _SUPER_PROP_COUNT = 0;

  private static final weblogic.xml.stream.XMLName XML_TYPE = 
     weblogic.xml.stream.ElementFactory.createXMLName( "http://www.hitachi.co.jp/TFX/WebService" , "EntryRiyousyaIn" );

  private static final java.lang.String JAVA_TYPE = 
     "jp.co.hitachi.www.TFX.WebService.EntryRiyousyaIn";



  private static final weblogic.xml.schema.binding.util.runtime.PropertyInfo[] PROPS = 
  {
  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://www.hitachi.co.jp/TFX/WebService']:biko
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:string
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://www.hitachi.co.jp/TFX/WebService","biko",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","string",null),
                                                            "biko",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://www.hitachi.co.jp/TFX/WebService']:chgDiv
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:string
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://www.hitachi.co.jp/TFX/WebService","chgDiv",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","string",null),
                                                            "chgDiv",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://www.hitachi.co.jp/TFX/WebService']:edocConsDate
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:string
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://www.hitachi.co.jp/TFX/WebService","edocConsDate",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","string",null),
                                                            "edocConsDate",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = jp.co.hitachi.www.TFX.WebService
  //class   name = [Ljp.co.hitachi.www.TFX.WebService.HachuUlimitVolBean;
  //java    type = [Ljp.co.hitachi.www.TFX.WebService.HachuUlimitVolBean;
  //schema  name = ['http://www.hitachi.co.jp/TFX/WebService']:hachuUlimitVols
  //schema  type = ['http://www.hitachi.co.jp/TFX/WebService']:SequenceOfHachuUlimitVolBean
  //array: true primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://www.hitachi.co.jp/TFX/WebService","hachuUlimitVols",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.hitachi.co.jp/TFX/WebService","SequenceOfHachuUlimitVolBean",null),
                                                            "hachuUlimitVols",
                                                            jp.co.hitachi.www.TFX.WebService.HachuUlimitVolBean[].class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://www.hitachi.co.jp/TFX/WebService']:hchupw
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:string
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://www.hitachi.co.jp/TFX/WebService","hchupw",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","string",null),
                                                            "hchupw",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://www.hitachi.co.jp/TFX/WebService']:jkjtDiv
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:string
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://www.hitachi.co.jp/TFX/WebService","jkjtDiv",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","string",null),
                                                            "jkjtDiv",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://www.hitachi.co.jp/TFX/WebService']:lastManualConfDate
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:string
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://www.hitachi.co.jp/TFX/WebService","lastManualConfDate",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","string",null),
                                                            "lastManualConfDate",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://www.hitachi.co.jp/TFX/WebService']:lcutDiv
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:string
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://www.hitachi.co.jp/TFX/WebService","lcutDiv",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","string",null),
                                                            "lcutDiv",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://www.hitachi.co.jp/TFX/WebService']:linpw
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:string
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://www.hitachi.co.jp/TFX/WebService","linpw",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","string",null),
                                                            "linpw",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://www.hitachi.co.jp/TFX/WebService']:loginId
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:string
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://www.hitachi.co.jp/TFX/WebService","loginId",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","string",null),
                                                            "loginId",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://www.hitachi.co.jp/TFX/WebService']:mailad1
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:string
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://www.hitachi.co.jp/TFX/WebService","mailad1",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","string",null),
                                                            "mailad1",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://www.hitachi.co.jp/TFX/WebService']:mailad2
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:string
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://www.hitachi.co.jp/TFX/WebService","mailad2",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","string",null),
                                                            "mailad2",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://www.hitachi.co.jp/TFX/WebService']:rysAttribute
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:string
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://www.hitachi.co.jp/TFX/WebService","rysAttribute",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","string",null),
                                                            "rysAttribute",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://www.hitachi.co.jp/TFX/WebService']:rysCd
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:string
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://www.hitachi.co.jp/TFX/WebService","rysCd",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","string",null),
                                                            "rysCd",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://www.hitachi.co.jp/TFX/WebService']:rysName
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:string
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://www.hitachi.co.jp/TFX/WebService","rysName",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","string",null),
                                                            "rysName",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://www.hitachi.co.jp/TFX/WebService']:tradeKanouDiv
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:string
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://www.hitachi.co.jp/TFX/WebService","tradeKanouDiv",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","string",null),
                                                            "tradeKanouDiv",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://www.hitachi.co.jp/TFX/WebService']:webServReqNo
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:string
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://www.hitachi.co.jp/TFX/WebService","webServReqNo",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","string",null),
                                                            "webServReqNo",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://www.hitachi.co.jp/TFX/WebService']:yakudakusyoNo
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:string
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://www.hitachi.co.jp/TFX/WebService","yakudakusyoNo",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","string",null),
                                                            "yakudakusyoNo",
                                                            java.lang.String.class,
                                                            false,
                                                            true),



  };




  protected java.lang.Object createObject() {
    return new jp.co.hitachi.www.TFX.WebService.EntryRiyousyaIn();
  }

  protected weblogic.xml.stream.XMLName getXmlType() {
    return XML_TYPE;
  }





  protected java.lang.Object invokeGetter(java.lang.Object my_obj, int idx) {

    jp.co.hitachi.www.TFX.WebService.EntryRiyousyaIn typed_obj = (jp.co.hitachi.www.TFX.WebService.EntryRiyousyaIn) my_obj;
    return typedInvokeGetter(typed_obj, idx);
  }

  protected void invokeSetter(java.lang.Object my_obj, int idx,
                              java.lang.Object setter_arg)
  {

    jp.co.hitachi.www.TFX.WebService.EntryRiyousyaIn typed_obj = (jp.co.hitachi.www.TFX.WebService.EntryRiyousyaIn) my_obj;
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


  private static java.lang.Object typedInvokeGetter(jp.co.hitachi.www.TFX.WebService.EntryRiyousyaIn my_obj, 
                                          int idx) 
  {
    java.lang.Object obj;

    switch(idx) {

    case 0:
      obj = my_obj.getBiko();
      break;
    case 1:
      obj = my_obj.getChgDiv();
      break;
    case 2:
      obj = my_obj.getEdocConsDate();
      break;
    case 3:
      obj = my_obj.getHachuUlimitVols();
      break;
    case 4:
      obj = my_obj.getHchupw();
      break;
    case 5:
      obj = my_obj.getJkjtDiv();
      break;
    case 6:
      obj = my_obj.getLastManualConfDate();
      break;
    case 7:
      obj = my_obj.getLcutDiv();
      break;
    case 8:
      obj = my_obj.getLinpw();
      break;
    case 9:
      obj = my_obj.getLoginId();
      break;
    case 10:
      obj = my_obj.getMailad1();
      break;
    case 11:
      obj = my_obj.getMailad2();
      break;
    case 12:
      obj = my_obj.getRysAttribute();
      break;
    case 13:
      obj = my_obj.getRysCd();
      break;
    case 14:
      obj = my_obj.getRysName();
      break;
    case 15:
      obj = my_obj.getTradeKanouDiv();
      break;
    case 16:
      obj = my_obj.getWebServReqNo();
      break;
    case 17:
      obj = my_obj.getYakudakusyoNo();
      break;


    default:
      throw new java.lang.NoSuchFieldError("impossible case: " + idx);
    }

    return obj;

  }


  private static void typedInvokeSetter(jp.co.hitachi.www.TFX.WebService.EntryRiyousyaIn my_obj, 
                                        int idx,
                                        java.lang.Object setter_arg) 
  {
    switch(idx) {

    case 0:
      my_obj.setBiko((java.lang.String)setter_arg);
      break;
    case 1:
      my_obj.setChgDiv((java.lang.String)setter_arg);
      break;
    case 2:
      my_obj.setEdocConsDate((java.lang.String)setter_arg);
      break;
    case 3:
      my_obj.setHachuUlimitVols((jp.co.hitachi.www.TFX.WebService.HachuUlimitVolBean[])setter_arg);
      break;
    case 4:
      my_obj.setHchupw((java.lang.String)setter_arg);
      break;
    case 5:
      my_obj.setJkjtDiv((java.lang.String)setter_arg);
      break;
    case 6:
      my_obj.setLastManualConfDate((java.lang.String)setter_arg);
      break;
    case 7:
      my_obj.setLcutDiv((java.lang.String)setter_arg);
      break;
    case 8:
      my_obj.setLinpw((java.lang.String)setter_arg);
      break;
    case 9:
      my_obj.setLoginId((java.lang.String)setter_arg);
      break;
    case 10:
      my_obj.setMailad1((java.lang.String)setter_arg);
      break;
    case 11:
      my_obj.setMailad2((java.lang.String)setter_arg);
      break;
    case 12:
      my_obj.setRysAttribute((java.lang.String)setter_arg);
      break;
    case 13:
      my_obj.setRysCd((java.lang.String)setter_arg);
      break;
    case 14:
      my_obj.setRysName((java.lang.String)setter_arg);
      break;
    case 15:
      my_obj.setTradeKanouDiv((java.lang.String)setter_arg);
      break;
    case 16:
      my_obj.setWebServReqNo((java.lang.String)setter_arg);
      break;
    case 17:
      my_obj.setYakudakusyoNo((java.lang.String)setter_arg);
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
