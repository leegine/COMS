head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.36.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	InquirySyoriKekkaCodec.java;


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

// original type: ['http://www.hitachi.co.jp/TFX/WebService']:InquirySyoriKekka


public final class InquirySyoriKekkaCodec 
  extends weblogic.xml.schema.binding.BeanCodecBase
{

  private static final int _SUPER_PROP_COUNT = 0;

  private static final weblogic.xml.stream.XMLName XML_TYPE = 
     weblogic.xml.stream.ElementFactory.createXMLName( "http://www.hitachi.co.jp/TFX/WebService" , "InquirySyoriKekka" );

  private static final java.lang.String JAVA_TYPE = 
     "jp.co.hitachi.www.TFX.WebService.InquirySyoriKekka";



  private static final weblogic.xml.schema.binding.util.runtime.PropertyInfo[] PROPS = 
  {
  //package name = jp.co.hitachi.www.TFX.WebService
  //class   name = InquirySyoriKekkaIn
  //java    type = jp.co.hitachi.www.TFX.WebService.InquirySyoriKekkaIn
  //schema  name = ['http://www.hitachi.co.jp/TFX/WebService']:in0
  //schema  type = ['http://www.hitachi.co.jp/TFX/WebService']:InquirySyoriKekkaIn
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://www.hitachi.co.jp/TFX/WebService","in0",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.hitachi.co.jp/TFX/WebService","InquirySyoriKekkaIn",null),
                                                            "in0",
                                                            jp.co.hitachi.www.TFX.WebService.InquirySyoriKekkaIn.class,
                                                            false,
                                                            true),



  };




  protected java.lang.Object createObject() {
    return new jp.co.hitachi.www.TFX.WebService.InquirySyoriKekka();
  }

  protected weblogic.xml.stream.XMLName getXmlType() {
    return XML_TYPE;
  }





  protected java.lang.Object invokeGetter(java.lang.Object my_obj, int idx) {

    jp.co.hitachi.www.TFX.WebService.InquirySyoriKekka typed_obj = (jp.co.hitachi.www.TFX.WebService.InquirySyoriKekka) my_obj;
    return typedInvokeGetter(typed_obj, idx);
  }

  protected void invokeSetter(java.lang.Object my_obj, int idx,
                              java.lang.Object setter_arg)
  {

    jp.co.hitachi.www.TFX.WebService.InquirySyoriKekka typed_obj = (jp.co.hitachi.www.TFX.WebService.InquirySyoriKekka) my_obj;
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


  private static java.lang.Object typedInvokeGetter(jp.co.hitachi.www.TFX.WebService.InquirySyoriKekka my_obj, 
                                          int idx) 
  {
    java.lang.Object obj;

    switch(idx) {

    case 0:
      obj = my_obj.getIn0();
      break;


    default:
      throw new java.lang.NoSuchFieldError("impossible case: " + idx);
    }

    return obj;

  }


  private static void typedInvokeSetter(jp.co.hitachi.www.TFX.WebService.InquirySyoriKekka my_obj, 
                                        int idx,
                                        java.lang.Object setter_arg) 
  {
    switch(idx) {

    case 0:
      my_obj.setIn0((jp.co.hitachi.www.TFX.WebService.InquirySyoriKekkaIn)setter_arg);
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
