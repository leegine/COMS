head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.39.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	SequenceOfHachuUlimitVolBeanSequenceCodec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@
/**
 * This code was automatically generated at 13:33:46 on 2008/07/29
 * by weblogic.xml.schema.binding.internal.codegen.SequenceCodecGenerator -- do not edit.
 *
 * @@version WebLogic Temporary Patch for CR362079 Thu Feb 21 18:25:04 2008
 * @@author Copyright (c) 2008 by BEA Systems, Inc. All Rights Reserved.
 */

package jp.co.hitachi.www.TFX.WebService;

// original type: ['http://www.hitachi.co.jp/TFX/WebService']:SequenceOfHachuUlimitVolBean

// from array_deserialzer.j

public final class SequenceOfHachuUlimitVolBeanSequenceCodec extends weblogic.xml.schema.binding.SequenceCodecBase
{
  private static final  weblogic.xml.stream.XMLName XML_TYPE =
    weblogic.xml.stream.ElementFactory.createXMLName("http://www.hitachi.co.jp/TFX/WebService","SequenceOfHachuUlimitVolBean",null);

  private static final  weblogic.xml.stream.XMLName elementType =
    weblogic.xml.stream.ElementFactory.createXMLName("http://www.hitachi.co.jp/TFX/WebService","HachuUlimitVolBean",null);

  private static final  weblogic.xml.stream.XMLName elementName = 
    weblogic.xml.stream.ElementFactory.createXMLName("http://www.hitachi.co.jp/TFX/WebService","item",null);

  private static final java.lang.Class elementClass =
    jp.co.hitachi.www.TFX.WebService.HachuUlimitVolBean.class;


  private static final weblogic.xml.schema.binding.util.runtime.PropertyInfo _componentProp = 
    new weblogic.xml.schema.binding.util.runtime.PropertyInfo(elementType, elementName, "item", elementClass, false, true);


  protected  weblogic.xml.stream.XMLName getXmlType() {
    return XML_TYPE;
  }

  protected weblogic.xml.stream.XMLName getComponentXMLType(java.lang.Object obj, 
							    weblogic.xml.schema.binding.SerializationContext context) 
    throws weblogic.xml.schema.binding.SerializationException
  {
    return elementType;
  }

  private static final weblogic.xml.schema.binding.SchemaContext elementSchemaContext =
    weblogic.xml.schema.binding.SchemaContextFactory.newInstance().createSchemaContext(elementClass.getName());

  private static final weblogic.xml.schema.binding.ClassContext elementClassContext =
    weblogic.xml.schema.binding.ClassContextFactory.newInstance().createClassContext(elementType);

  protected weblogic.xml.schema.binding.util.runtime.Accumulator 
    createAccumulator(weblogic.xml.schema.binding.DeserializationContext context) {
    return weblogic.xml.schema.binding.util.runtime.AccumulatorFactory.createAccumulator(elementClass);
  }

  protected weblogic.xml.schema.binding.Deserializer 
    getSequenceElementDeserializer(weblogic.xml.schema.binding.DeserializationContext context) 
    throws weblogic.xml.schema.binding.DeserializationException {
    return weblogic.xml.schema.binding.RuntimeUtils.lookup_deserializer(elementType,
                                                                        elementSchemaContext,
                                                                        context);
  }

  protected weblogic.xml.stream.XMLName 
    getSequenceElementXMLName() {
    return elementName;
  }

  protected weblogic.xml.schema.binding.Serializer 
    getSequenceElementSerializer(weblogic.xml.schema.binding.SerializationContext context)
    throws weblogic.xml.schema.binding.SerializationException {
    return weblogic.xml.schema.binding.RuntimeUtils.lookup_serializer(elementClass,
                                                                      elementClassContext,
                                                                      context);
  }



  protected weblogic.xml.stream.XMLName getComponentXMLName(java.lang.Object obj,
                                                            weblogic.xml.schema.binding.SerializationContext context) 
    throws weblogic.xml.schema.binding.SerializationException
  {
    return elementName;
  }


  protected weblogic.xml.stream.XMLName getExpectedArrayElementType() 
    throws weblogic.xml.schema.binding.DeserializationException
  {
    return elementType;
  }

  protected java.lang.Class getExpectedComponentClass()
    throws weblogic.xml.schema.binding.DeserializationException
  {
    return elementClass;
  }

  public int getPropertyCount() {
    return 1;
  }
  public weblogic.xml.schema.binding.util.runtime.PropertyInfo getPropertyInfo(int idx) {
    if (idx == 0) {
      return _componentProp;
    } else {
      throw new java.lang.IndexOutOfBoundsException("invalid index " + idx);
    }
  }









}

@
