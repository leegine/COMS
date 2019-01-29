head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.06.27.06.12.04;	author zhang-tengyu;	state dead;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	444;
commitid	8d84e081ce81473;
filename	LookupAccountInfoProfilesArrayAnonTypeSequenceCodec.java;

1.1
date	2011.03.15.02.45.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	LookupAccountInfoProfilesArrayAnonTypeSequenceCodec.java;


desc
@@


1.2
log
@*** empty log message ***
@
text
@
/**
 * This code was automatically generated at 17:28:55 on 2008/10/30
 * by weblogic.xml.schema.binding.internal.codegen.SequenceCodecGenerator -- do not edit.
 *
 * @@version WebLogic Temporary Patch for CR112756 03/08/2004 13:53:04
 * @@author Copyright (c) 2008 by BEA Systems, Inc. All Rights Reserved.
 */

package com.gftforex.soap.api;

// original type: ['http://soap-api.gftforex.com']:LookupAccountInfo__profiles__ArrayAnonType

// from array_deserialzer.j

public final class LookupAccountInfoProfilesArrayAnonTypeSequenceCodec extends weblogic.xml.schema.binding.SequenceCodecBase
{
  private static final  weblogic.xml.stream.XMLName XML_TYPE =
    weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","LookupAccountInfo__profiles__ArrayAnonType",null);

  private static final  weblogic.xml.stream.XMLName elementType =
    weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","unsignedInt",null);

  private static final  weblogic.xml.stream.XMLName elementName = 
    weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","Profiles",null);

  private static final java.lang.Class elementClass =
    long.class;


  private static final weblogic.xml.schema.binding.util.runtime.PropertyInfo _componentProp = 
    new weblogic.xml.schema.binding.util.runtime.PropertyInfo(elementType, elementName, "profiles", elementClass, false, true);


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

  protected boolean isCompleteType() {
    return false;
  }


  protected boolean writeXsiType(weblogic.xml.schema.binding.SerializationContext context) {
    return false;
  }


  protected long getMinOccurs() {
    return 0L ;
  }


  protected long getMaxOccurs() {
    return -1L ;
  }


}

@


1.1
log
@*** empty log message ***
@
text
@@

