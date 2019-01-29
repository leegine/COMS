head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.06.27.06.07.01;	author zhang-tengyu;	state dead;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	444;
commitid	8d84e081ce81473;
filename	UserPersonalInfoCodec.java;

1.1
date	2011.03.15.02.42.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	UserPersonalInfoCodec.java;


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

// original type: ['http://soap-api.gftforex.com']:UserPersonalInfo


public final class UserPersonalInfoCodec 
  extends weblogic.xml.schema.binding.BeanCodecBase
{

  private static final int _SUPER_PROP_COUNT = 0;

  private static final weblogic.xml.stream.XMLName XML_TYPE = 
     weblogic.xml.stream.ElementFactory.createXMLName( "http://soap-api.gftforex.com" , "UserPersonalInfo" );

  private static final java.lang.String JAVA_TYPE = 
     "com.gftforex.soap.api.UserPersonalInfo";



  private static final weblogic.xml.schema.binding.util.runtime.PropertyInfo[] PROPS = 
  {
  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://soap-api.gftforex.com']:FirstName
  //schema  type = ['http://soap-api.gftforex.com']:String64
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","FirstName",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","String64",null),
                                                            "firstName",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://soap-api.gftforex.com']:LastName
  //schema  type = ['http://soap-api.gftforex.com']:String64
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","LastName",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","String64",null),
                                                            "lastName",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://soap-api.gftforex.com']:Email
  //schema  type = ['http://soap-api.gftforex.com']:String128
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","Email",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","String128",null),
                                                            "email",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://soap-api.gftforex.com']:MobilePhone
  //schema  type = ['http://soap-api.gftforex.com']:String32
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","MobilePhone",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","String32",null),
                                                            "mobilePhone",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://soap-api.gftforex.com']:HomePhone
  //schema  type = ['http://soap-api.gftforex.com']:String32
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","HomePhone",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","String32",null),
                                                            "homePhone",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://soap-api.gftforex.com']:DayPhone
  //schema  type = ['http://soap-api.gftforex.com']:String32
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","DayPhone",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","String32",null),
                                                            "dayPhone",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://soap-api.gftforex.com']:FaxNumber
  //schema  type = ['http://soap-api.gftforex.com']:String32
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","FaxNumber",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","String32",null),
                                                            "faxNumber",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://soap-api.gftforex.com']:StreetAdress
  //schema  type = ['http://soap-api.gftforex.com']:String128
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","StreetAdress",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","String128",null),
                                                            "streetAdress",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://soap-api.gftforex.com']:City
  //schema  type = ['http://soap-api.gftforex.com']:String32
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","City",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","String32",null),
                                                            "city",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://soap-api.gftforex.com']:State
  //schema  type = ['http://soap-api.gftforex.com']:String32
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","State",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","String32",null),
                                                            "state",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://soap-api.gftforex.com']:Country
  //schema  type = ['http://soap-api.gftforex.com']:String32
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","Country",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","String32",null),
                                                            "country",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://soap-api.gftforex.com']:ZipCode
  //schema  type = ['http://soap-api.gftforex.com']:String15
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","ZipCode",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","String15",null),
                                                            "zipCode",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://soap-api.gftforex.com']:BestTimeToCall
  //schema  type = ['http://soap-api.gftforex.com']:String1024
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","BestTimeToCall",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","String1024",null),
                                                            "bestTimeToCall",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://soap-api.gftforex.com']:Course
  //schema  type = ['http://soap-api.gftforex.com']:String1024
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","Course",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","String1024",null),
                                                            "course",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://soap-api.gftforex.com']:HowDidYouFindOurWebsite
  //schema  type = ['http://soap-api.gftforex.com']:String1024
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","HowDidYouFindOurWebsite",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","String1024",null),
                                                            "howDidYouFindOurWebsite",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://soap-api.gftforex.com']:ILearnedOfGFTVia
  //schema  type = ['http://soap-api.gftforex.com']:String1024
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","ILearnedOfGFTVia",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","String1024",null),
                                                            "iLearnedOfGFTVia",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://soap-api.gftforex.com']:IWasReferredBy
  //schema  type = ['http://soap-api.gftforex.com']:String1024
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","IWasReferredBy",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","String1024",null),
                                                            "iWasReferredBy",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://soap-api.gftforex.com']:NotTraded
  //schema  type = ['http://soap-api.gftforex.com']:String1024
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","NotTraded",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","String1024",null),
                                                            "notTraded",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://soap-api.gftforex.com']:OtherReason
  //schema  type = ['http://soap-api.gftforex.com']:String1024
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","OtherReason",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","String1024",null),
                                                            "otherReason",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://soap-api.gftforex.com']:OtherServices
  //schema  type = ['http://soap-api.gftforex.com']:String1024
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","OtherServices",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","String1024",null),
                                                            "otherServices",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://soap-api.gftforex.com']:OtherWants
  //schema  type = ['http://soap-api.gftforex.com']:String1024
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","OtherWants",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","String1024",null),
                                                            "otherWants",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://soap-api.gftforex.com']:PleaseContactMeVia
  //schema  type = ['http://soap-api.gftforex.com']:String1024
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","PleaseContactMeVia",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","String1024",null),
                                                            "pleaseContactMeVia",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://soap-api.gftforex.com']:Profession
  //schema  type = ['http://soap-api.gftforex.com']:String1024
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","Profession",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","String1024",null),
                                                            "profession",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://soap-api.gftforex.com']:ServiceExpected
  //schema  type = ['http://soap-api.gftforex.com']:String1024
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","ServiceExpected",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","String1024",null),
                                                            "serviceExpected",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = 
  //class   name = boolean
  //java    type = boolean
  //schema  name = ['http://soap-api.gftforex.com']:TradedForex
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:boolean
  //array: false primitive: true  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","TradedForex",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","boolean",null),
                                                            "tradedForex",
                                                            boolean.class,
                                                            false,
                                                            true),


  //package name = 
  //class   name = boolean
  //java    type = boolean
  //schema  name = ['http://soap-api.gftforex.com']:TradedFutures
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:boolean
  //array: false primitive: true  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","TradedFutures",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","boolean",null),
                                                            "tradedFutures",
                                                            boolean.class,
                                                            false,
                                                            true),


  //package name = 
  //class   name = boolean
  //java    type = boolean
  //schema  name = ['http://soap-api.gftforex.com']:TradedStocks
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:boolean
  //array: false primitive: true  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","TradedStocks",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","boolean",null),
                                                            "tradedStocks",
                                                            boolean.class,
                                                            false,
                                                            true),


  //package name = 
  //class   name = boolean
  //java    type = boolean
  //schema  name = ['http://soap-api.gftforex.com']:TradedOptions
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:boolean
  //array: false primitive: true  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","TradedOptions",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","boolean",null),
                                                            "tradedOptions",
                                                            boolean.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://soap-api.gftforex.com']:WebforexDemo
  //schema  type = ['http://soap-api.gftforex.com']:String1024
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","WebforexDemo",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","String1024",null),
                                                            "webforexDemo",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://soap-api.gftforex.com']:WhatDoYouWant
  //schema  type = ['http://soap-api.gftforex.com']:String1024
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","WhatDoYouWant",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","String1024",null),
                                                            "whatDoYouWant",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://soap-api.gftforex.com']:WhyForex
  //schema  type = ['http://soap-api.gftforex.com']:String1024
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","WhyForex",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","String1024",null),
                                                            "whyForex",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://soap-api.gftforex.com']:ForexTradingExperience
  //schema  type = ['http://soap-api.gftforex.com']:String1024
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","ForexTradingExperience",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","String1024",null),
                                                            "forexTradingExperience",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://soap-api.gftforex.com']:FuturesOptionsTradingExperience
  //schema  type = ['http://soap-api.gftforex.com']:String1024
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","FuturesOptionsTradingExperience",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","String1024",null),
                                                            "futuresOptionsTradingExperience",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://soap-api.gftforex.com']:OtherTradingExperience
  //schema  type = ['http://soap-api.gftforex.com']:String1024
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","OtherTradingExperience",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","String1024",null),
                                                            "otherTradingExperience",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['http://soap-api.gftforex.com']:InternetConnection
  //schema  type = ['http://soap-api.gftforex.com']:String1024
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","InternetConnection",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://soap-api.gftforex.com","String1024",null),
                                                            "internetConnection",
                                                            java.lang.String.class,
                                                            false,
                                                            true),



  };




  protected java.lang.Object createObject() {
    return new com.gftforex.soap.api.UserPersonalInfo();
  }

  protected weblogic.xml.stream.XMLName getXmlType() {
    return XML_TYPE;
  }



  protected boolean isPropertySet(Object my_obj, int idx) {

    com.gftforex.soap.api.UserPersonalInfo typed_obj = (com.gftforex.soap.api.UserPersonalInfo) my_obj;

    switch(idx) {

    case 0:
      return typed_obj._isSetFirstName();
    case 1:
      return typed_obj._isSetLastName();
    case 2:
      return typed_obj._isSetEmail();
    case 3:
      return typed_obj._isSetMobilePhone();
    case 4:
      return typed_obj._isSetHomePhone();
    case 5:
      return typed_obj._isSetDayPhone();
    case 6:
      return typed_obj._isSetFaxNumber();
    case 7:
      return typed_obj._isSetStreetAdress();
    case 8:
      return typed_obj._isSetCity();
    case 9:
      return typed_obj._isSetState();
    case 10:
      return typed_obj._isSetCountry();
    case 11:
      return typed_obj._isSetZipCode();
    case 12:
      return typed_obj._isSetBestTimeToCall();
    case 13:
      return typed_obj._isSetCourse();
    case 14:
      return typed_obj._isSetHowDidYouFindOurWebsite();
    case 15:
      return typed_obj._isSetILearnedOfGFTVia();
    case 16:
      return typed_obj._isSetIWasReferredBy();
    case 17:
      return typed_obj._isSetNotTraded();
    case 18:
      return typed_obj._isSetOtherReason();
    case 19:
      return typed_obj._isSetOtherServices();
    case 20:
      return typed_obj._isSetOtherWants();
    case 21:
      return typed_obj._isSetPleaseContactMeVia();
    case 22:
      return typed_obj._isSetProfession();
    case 23:
      return typed_obj._isSetServiceExpected();
    case 24:
      return typed_obj._isSetTradedForex();
    case 25:
      return typed_obj._isSetTradedFutures();
    case 26:
      return typed_obj._isSetTradedStocks();
    case 27:
      return typed_obj._isSetTradedOptions();
    case 28:
      return typed_obj._isSetWebforexDemo();
    case 29:
      return typed_obj._isSetWhatDoYouWant();
    case 30:
      return typed_obj._isSetWhyForex();
    case 31:
      return typed_obj._isSetForexTradingExperience();
    case 32:
      return typed_obj._isSetFuturesOptionsTradingExperience();
    case 33:
      return typed_obj._isSetOtherTradingExperience();
    case 34:
      return typed_obj._isSetInternetConnection();


    default:
      return true;
    }
  }




  protected java.lang.Object invokeGetter(java.lang.Object my_obj, int idx) {

    com.gftforex.soap.api.UserPersonalInfo typed_obj = (com.gftforex.soap.api.UserPersonalInfo) my_obj;
    return typedInvokeGetter(typed_obj, idx);
  }

  protected void invokeSetter(java.lang.Object my_obj, int idx,
                              java.lang.Object setter_arg)
  {

    com.gftforex.soap.api.UserPersonalInfo typed_obj = (com.gftforex.soap.api.UserPersonalInfo) my_obj;
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


  private static java.lang.Object typedInvokeGetter(com.gftforex.soap.api.UserPersonalInfo my_obj, 
                                          int idx) 
  {
    java.lang.Object obj;

    switch(idx) {

    case 0:
      obj = my_obj.getFirstName();
      break;
    case 1:
      obj = my_obj.getLastName();
      break;
    case 2:
      obj = my_obj.getEmail();
      break;
    case 3:
      obj = my_obj.getMobilePhone();
      break;
    case 4:
      obj = my_obj.getHomePhone();
      break;
    case 5:
      obj = my_obj.getDayPhone();
      break;
    case 6:
      obj = my_obj.getFaxNumber();
      break;
    case 7:
      obj = my_obj.getStreetAdress();
      break;
    case 8:
      obj = my_obj.getCity();
      break;
    case 9:
      obj = my_obj.getState();
      break;
    case 10:
      obj = my_obj.getCountry();
      break;
    case 11:
      obj = my_obj.getZipCode();
      break;
    case 12:
      obj = my_obj.getBestTimeToCall();
      break;
    case 13:
      obj = my_obj.getCourse();
      break;
    case 14:
      obj = my_obj.getHowDidYouFindOurWebsite();
      break;
    case 15:
      obj = my_obj.getILearnedOfGFTVia();
      break;
    case 16:
      obj = my_obj.getIWasReferredBy();
      break;
    case 17:
      obj = my_obj.getNotTraded();
      break;
    case 18:
      obj = my_obj.getOtherReason();
      break;
    case 19:
      obj = my_obj.getOtherServices();
      break;
    case 20:
      obj = my_obj.getOtherWants();
      break;
    case 21:
      obj = my_obj.getPleaseContactMeVia();
      break;
    case 22:
      obj = my_obj.getProfession();
      break;
    case 23:
      obj = my_obj.getServiceExpected();
      break;
    case 24:
      obj = new java.lang.Boolean(my_obj.getTradedForex());
      break;
    case 25:
      obj = new java.lang.Boolean(my_obj.getTradedFutures());
      break;
    case 26:
      obj = new java.lang.Boolean(my_obj.getTradedStocks());
      break;
    case 27:
      obj = new java.lang.Boolean(my_obj.getTradedOptions());
      break;
    case 28:
      obj = my_obj.getWebforexDemo();
      break;
    case 29:
      obj = my_obj.getWhatDoYouWant();
      break;
    case 30:
      obj = my_obj.getWhyForex();
      break;
    case 31:
      obj = my_obj.getForexTradingExperience();
      break;
    case 32:
      obj = my_obj.getFuturesOptionsTradingExperience();
      break;
    case 33:
      obj = my_obj.getOtherTradingExperience();
      break;
    case 34:
      obj = my_obj.getInternetConnection();
      break;


    default:
      throw new java.lang.NoSuchFieldError("impossible case: " + idx);
    }

    return obj;

  }


  private static void typedInvokeSetter(com.gftforex.soap.api.UserPersonalInfo my_obj, 
                                        int idx,
                                        java.lang.Object setter_arg) 
  {
    switch(idx) {

    case 0:
      my_obj.setFirstName((java.lang.String)setter_arg);
      break;
    case 1:
      my_obj.setLastName((java.lang.String)setter_arg);
      break;
    case 2:
      my_obj.setEmail((java.lang.String)setter_arg);
      break;
    case 3:
      my_obj.setMobilePhone((java.lang.String)setter_arg);
      break;
    case 4:
      my_obj.setHomePhone((java.lang.String)setter_arg);
      break;
    case 5:
      my_obj.setDayPhone((java.lang.String)setter_arg);
      break;
    case 6:
      my_obj.setFaxNumber((java.lang.String)setter_arg);
      break;
    case 7:
      my_obj.setStreetAdress((java.lang.String)setter_arg);
      break;
    case 8:
      my_obj.setCity((java.lang.String)setter_arg);
      break;
    case 9:
      my_obj.setState((java.lang.String)setter_arg);
      break;
    case 10:
      my_obj.setCountry((java.lang.String)setter_arg);
      break;
    case 11:
      my_obj.setZipCode((java.lang.String)setter_arg);
      break;
    case 12:
      my_obj.setBestTimeToCall((java.lang.String)setter_arg);
      break;
    case 13:
      my_obj.setCourse((java.lang.String)setter_arg);
      break;
    case 14:
      my_obj.setHowDidYouFindOurWebsite((java.lang.String)setter_arg);
      break;
    case 15:
      my_obj.setILearnedOfGFTVia((java.lang.String)setter_arg);
      break;
    case 16:
      my_obj.setIWasReferredBy((java.lang.String)setter_arg);
      break;
    case 17:
      my_obj.setNotTraded((java.lang.String)setter_arg);
      break;
    case 18:
      my_obj.setOtherReason((java.lang.String)setter_arg);
      break;
    case 19:
      my_obj.setOtherServices((java.lang.String)setter_arg);
      break;
    case 20:
      my_obj.setOtherWants((java.lang.String)setter_arg);
      break;
    case 21:
      my_obj.setPleaseContactMeVia((java.lang.String)setter_arg);
      break;
    case 22:
      my_obj.setProfession((java.lang.String)setter_arg);
      break;
    case 23:
      my_obj.setServiceExpected((java.lang.String)setter_arg);
      break;
    case 24:
      my_obj.setTradedForex(((java.lang.Boolean)setter_arg).booleanValue());
      break;
    case 25:
      my_obj.setTradedFutures(((java.lang.Boolean)setter_arg).booleanValue());
      break;
    case 26:
      my_obj.setTradedStocks(((java.lang.Boolean)setter_arg).booleanValue());
      break;
    case 27:
      my_obj.setTradedOptions(((java.lang.Boolean)setter_arg).booleanValue());
      break;
    case 28:
      my_obj.setWebforexDemo((java.lang.String)setter_arg);
      break;
    case 29:
      my_obj.setWhatDoYouWant((java.lang.String)setter_arg);
      break;
    case 30:
      my_obj.setWhyForex((java.lang.String)setter_arg);
      break;
    case 31:
      my_obj.setForexTradingExperience((java.lang.String)setter_arg);
      break;
    case 32:
      my_obj.setFuturesOptionsTradingExperience((java.lang.String)setter_arg);
      break;
    case 33:
      my_obj.setOtherTradingExperience((java.lang.String)setter_arg);
      break;
    case 34:
      my_obj.setInternetConnection((java.lang.String)setter_arg);
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

