head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.06.27.06.06.25;	author zhang-tengyu;	state dead;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	444;
commitid	8d84e081ce81473;
filename	UserPersonalInfo.java;

1.1
date	2011.03.15.02.40.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	UserPersonalInfo.java;


desc
@@


1.2
log
@*** empty log message ***
@
text
@/**
 * This code was automatically generated at 17:28:54 on 2008/10/30
 * by weblogic.xml.schema.binding.internal.codegen.BeanImplGenerator -- do not edit.
 *
 * @@version WebLogic Temporary Patch for CR112756 03/08/2004 13:53:04
 * @@author Copyright (c) 2008 by BEA Systems, Inc. All Rights Reserved.
 */

package com.gftforex.soap.api;

// original type: ['http://soap-api.gftforex.com']:UserPersonalInfo


public  class UserPersonalInfo 
  
  implements java.io.Serializable
{

  public UserPersonalInfo() {}

  public UserPersonalInfo(java.lang.String p_firstName
,     java.lang.String p_lastName
,     java.lang.String p_email
,     java.lang.String p_mobilePhone
,     java.lang.String p_homePhone
,     java.lang.String p_dayPhone
,     java.lang.String p_faxNumber
,     java.lang.String p_streetAdress
,     java.lang.String p_city
,     java.lang.String p_state
,     java.lang.String p_country
,     java.lang.String p_zipCode
,     java.lang.String p_bestTimeToCall
,     java.lang.String p_course
,     java.lang.String p_howDidYouFindOurWebsite
,     java.lang.String p_iLearnedOfGFTVia
,     java.lang.String p_iWasReferredBy
,     java.lang.String p_notTraded
,     java.lang.String p_otherReason
,     java.lang.String p_otherServices
,     java.lang.String p_otherWants
,     java.lang.String p_pleaseContactMeVia
,     java.lang.String p_profession
,     java.lang.String p_serviceExpected
,     boolean p_tradedForex
,     boolean p_tradedFutures
,     boolean p_tradedStocks
,     boolean p_tradedOptions
,     java.lang.String p_webforexDemo
,     java.lang.String p_whatDoYouWant
,     java.lang.String p_whyForex
,     java.lang.String p_forexTradingExperience
,     java.lang.String p_futuresOptionsTradingExperience
,     java.lang.String p_otherTradingExperience
,     java.lang.String p_internetConnection) 
  {
     this.firstName = p_firstName;
     this.__isset_firstName = true;

     this.lastName = p_lastName;
     this.__isset_lastName = true;

     this.email = p_email;
     this.__isset_email = true;

     this.mobilePhone = p_mobilePhone;
     this.__isset_mobilePhone = true;

     this.homePhone = p_homePhone;
     this.__isset_homePhone = true;

     this.dayPhone = p_dayPhone;
     this.__isset_dayPhone = true;

     this.faxNumber = p_faxNumber;
     this.__isset_faxNumber = true;

     this.streetAdress = p_streetAdress;
     this.__isset_streetAdress = true;

     this.city = p_city;
     this.__isset_city = true;

     this.state = p_state;
     this.__isset_state = true;

     this.country = p_country;
     this.__isset_country = true;

     this.zipCode = p_zipCode;
     this.__isset_zipCode = true;

     this.bestTimeToCall = p_bestTimeToCall;
     this.__isset_bestTimeToCall = true;

     this.course = p_course;
     this.__isset_course = true;

     this.howDidYouFindOurWebsite = p_howDidYouFindOurWebsite;
     this.__isset_howDidYouFindOurWebsite = true;

     this.iLearnedOfGFTVia = p_iLearnedOfGFTVia;
     this.__isset_iLearnedOfGFTVia = true;

     this.iWasReferredBy = p_iWasReferredBy;
     this.__isset_iWasReferredBy = true;

     this.notTraded = p_notTraded;
     this.__isset_notTraded = true;

     this.otherReason = p_otherReason;
     this.__isset_otherReason = true;

     this.otherServices = p_otherServices;
     this.__isset_otherServices = true;

     this.otherWants = p_otherWants;
     this.__isset_otherWants = true;

     this.pleaseContactMeVia = p_pleaseContactMeVia;
     this.__isset_pleaseContactMeVia = true;

     this.profession = p_profession;
     this.__isset_profession = true;

     this.serviceExpected = p_serviceExpected;
     this.__isset_serviceExpected = true;

     this.tradedForex = p_tradedForex;
     this.__isset_tradedForex = true;

     this.tradedFutures = p_tradedFutures;
     this.__isset_tradedFutures = true;

     this.tradedStocks = p_tradedStocks;
     this.__isset_tradedStocks = true;

     this.tradedOptions = p_tradedOptions;
     this.__isset_tradedOptions = true;

     this.webforexDemo = p_webforexDemo;
     this.__isset_webforexDemo = true;

     this.whatDoYouWant = p_whatDoYouWant;
     this.__isset_whatDoYouWant = true;

     this.whyForex = p_whyForex;
     this.__isset_whyForex = true;

     this.forexTradingExperience = p_forexTradingExperience;
     this.__isset_forexTradingExperience = true;

     this.futuresOptionsTradingExperience = p_futuresOptionsTradingExperience;
     this.__isset_futuresOptionsTradingExperience = true;

     this.otherTradingExperience = p_otherTradingExperience;
     this.__isset_otherTradingExperience = true;

     this.internetConnection = p_internetConnection;
     this.__isset_internetConnection = true;


  }




  
  private java.lang.String firstName ;

  /**
  <br>  Derived from FirstName.

  <br>  schema name = ['http://soap-api.gftforex.com']:FirstName
  <br>  schema type = ['http://soap-api.gftforex.com']:String64
  <br>  schema formQualified = true
  */
  public java.lang.String getFirstName() {
    return firstName;
  }

  public void setFirstName(java.lang.String v) {
    
    this.firstName = v;
    this.__isset_firstName = true;

  }

  private boolean __isset_firstName;
  public boolean _isSetFirstName() {
    return this.__isset_firstName;
  }
  public void _unsetFirstName() {
    this.__isset_firstName = false;
  }




  
  private java.lang.String lastName ;

  /**
  <br>  Derived from LastName.

  <br>  schema name = ['http://soap-api.gftforex.com']:LastName
  <br>  schema type = ['http://soap-api.gftforex.com']:String64
  <br>  schema formQualified = true
  */
  public java.lang.String getLastName() {
    return lastName;
  }

  public void setLastName(java.lang.String v) {
    
    this.lastName = v;
    this.__isset_lastName = true;

  }

  private boolean __isset_lastName;
  public boolean _isSetLastName() {
    return this.__isset_lastName;
  }
  public void _unsetLastName() {
    this.__isset_lastName = false;
  }




  
  private java.lang.String email ;

  /**
  <br>  Derived from Email.

  <br>  schema name = ['http://soap-api.gftforex.com']:Email
  <br>  schema type = ['http://soap-api.gftforex.com']:String128
  <br>  schema formQualified = true
  */
  public java.lang.String getEmail() {
    return email;
  }

  public void setEmail(java.lang.String v) {
    
    this.email = v;
    this.__isset_email = true;

  }

  private boolean __isset_email;
  public boolean _isSetEmail() {
    return this.__isset_email;
  }
  public void _unsetEmail() {
    this.__isset_email = false;
  }




  
  private java.lang.String mobilePhone ;

  /**
  <br>  Derived from MobilePhone.

  <br>  schema name = ['http://soap-api.gftforex.com']:MobilePhone
  <br>  schema type = ['http://soap-api.gftforex.com']:String32
  <br>  schema formQualified = true
  */
  public java.lang.String getMobilePhone() {
    return mobilePhone;
  }

  public void setMobilePhone(java.lang.String v) {
    
    this.mobilePhone = v;
    this.__isset_mobilePhone = true;

  }

  private boolean __isset_mobilePhone;
  public boolean _isSetMobilePhone() {
    return this.__isset_mobilePhone;
  }
  public void _unsetMobilePhone() {
    this.__isset_mobilePhone = false;
  }




  
  private java.lang.String homePhone ;

  /**
  <br>  Derived from HomePhone.

  <br>  schema name = ['http://soap-api.gftforex.com']:HomePhone
  <br>  schema type = ['http://soap-api.gftforex.com']:String32
  <br>  schema formQualified = true
  */
  public java.lang.String getHomePhone() {
    return homePhone;
  }

  public void setHomePhone(java.lang.String v) {
    
    this.homePhone = v;
    this.__isset_homePhone = true;

  }

  private boolean __isset_homePhone;
  public boolean _isSetHomePhone() {
    return this.__isset_homePhone;
  }
  public void _unsetHomePhone() {
    this.__isset_homePhone = false;
  }




  
  private java.lang.String dayPhone ;

  /**
  <br>  Derived from DayPhone.

  <br>  schema name = ['http://soap-api.gftforex.com']:DayPhone
  <br>  schema type = ['http://soap-api.gftforex.com']:String32
  <br>  schema formQualified = true
  */
  public java.lang.String getDayPhone() {
    return dayPhone;
  }

  public void setDayPhone(java.lang.String v) {
    
    this.dayPhone = v;
    this.__isset_dayPhone = true;

  }

  private boolean __isset_dayPhone;
  public boolean _isSetDayPhone() {
    return this.__isset_dayPhone;
  }
  public void _unsetDayPhone() {
    this.__isset_dayPhone = false;
  }




  
  private java.lang.String faxNumber ;

  /**
  <br>  Derived from FaxNumber.

  <br>  schema name = ['http://soap-api.gftforex.com']:FaxNumber
  <br>  schema type = ['http://soap-api.gftforex.com']:String32
  <br>  schema formQualified = true
  */
  public java.lang.String getFaxNumber() {
    return faxNumber;
  }

  public void setFaxNumber(java.lang.String v) {
    
    this.faxNumber = v;
    this.__isset_faxNumber = true;

  }

  private boolean __isset_faxNumber;
  public boolean _isSetFaxNumber() {
    return this.__isset_faxNumber;
  }
  public void _unsetFaxNumber() {
    this.__isset_faxNumber = false;
  }




  
  private java.lang.String streetAdress ;

  /**
  <br>  Derived from StreetAdress.

  <br>  schema name = ['http://soap-api.gftforex.com']:StreetAdress
  <br>  schema type = ['http://soap-api.gftforex.com']:String128
  <br>  schema formQualified = true
  */
  public java.lang.String getStreetAdress() {
    return streetAdress;
  }

  public void setStreetAdress(java.lang.String v) {
    
    this.streetAdress = v;
    this.__isset_streetAdress = true;

  }

  private boolean __isset_streetAdress;
  public boolean _isSetStreetAdress() {
    return this.__isset_streetAdress;
  }
  public void _unsetStreetAdress() {
    this.__isset_streetAdress = false;
  }




  
  private java.lang.String city ;

  /**
  <br>  Derived from City.

  <br>  schema name = ['http://soap-api.gftforex.com']:City
  <br>  schema type = ['http://soap-api.gftforex.com']:String32
  <br>  schema formQualified = true
  */
  public java.lang.String getCity() {
    return city;
  }

  public void setCity(java.lang.String v) {
    
    this.city = v;
    this.__isset_city = true;

  }

  private boolean __isset_city;
  public boolean _isSetCity() {
    return this.__isset_city;
  }
  public void _unsetCity() {
    this.__isset_city = false;
  }




  
  private java.lang.String state ;

  /**
  <br>  Derived from State.

  <br>  schema name = ['http://soap-api.gftforex.com']:State
  <br>  schema type = ['http://soap-api.gftforex.com']:String32
  <br>  schema formQualified = true
  */
  public java.lang.String getState() {
    return state;
  }

  public void setState(java.lang.String v) {
    
    this.state = v;
    this.__isset_state = true;

  }

  private boolean __isset_state;
  public boolean _isSetState() {
    return this.__isset_state;
  }
  public void _unsetState() {
    this.__isset_state = false;
  }




  
  private java.lang.String country ;

  /**
  <br>  Derived from Country.

  <br>  schema name = ['http://soap-api.gftforex.com']:Country
  <br>  schema type = ['http://soap-api.gftforex.com']:String32
  <br>  schema formQualified = true
  */
  public java.lang.String getCountry() {
    return country;
  }

  public void setCountry(java.lang.String v) {
    
    this.country = v;
    this.__isset_country = true;

  }

  private boolean __isset_country;
  public boolean _isSetCountry() {
    return this.__isset_country;
  }
  public void _unsetCountry() {
    this.__isset_country = false;
  }




  
  private java.lang.String zipCode ;

  /**
  <br>  Derived from ZipCode.

  <br>  schema name = ['http://soap-api.gftforex.com']:ZipCode
  <br>  schema type = ['http://soap-api.gftforex.com']:String15
  <br>  schema formQualified = true
  */
  public java.lang.String getZipCode() {
    return zipCode;
  }

  public void setZipCode(java.lang.String v) {
    
    this.zipCode = v;
    this.__isset_zipCode = true;

  }

  private boolean __isset_zipCode;
  public boolean _isSetZipCode() {
    return this.__isset_zipCode;
  }
  public void _unsetZipCode() {
    this.__isset_zipCode = false;
  }




  
  private java.lang.String bestTimeToCall ;

  /**
  <br>  Derived from BestTimeToCall.

  <br>  schema name = ['http://soap-api.gftforex.com']:BestTimeToCall
  <br>  schema type = ['http://soap-api.gftforex.com']:String1024
  <br>  schema formQualified = true
  */
  public java.lang.String getBestTimeToCall() {
    return bestTimeToCall;
  }

  public void setBestTimeToCall(java.lang.String v) {
    
    this.bestTimeToCall = v;
    this.__isset_bestTimeToCall = true;

  }

  private boolean __isset_bestTimeToCall;
  public boolean _isSetBestTimeToCall() {
    return this.__isset_bestTimeToCall;
  }
  public void _unsetBestTimeToCall() {
    this.__isset_bestTimeToCall = false;
  }




  
  private java.lang.String course ;

  /**
  <br>  Derived from Course.

  <br>  schema name = ['http://soap-api.gftforex.com']:Course
  <br>  schema type = ['http://soap-api.gftforex.com']:String1024
  <br>  schema formQualified = true
  */
  public java.lang.String getCourse() {
    return course;
  }

  public void setCourse(java.lang.String v) {
    
    this.course = v;
    this.__isset_course = true;

  }

  private boolean __isset_course;
  public boolean _isSetCourse() {
    return this.__isset_course;
  }
  public void _unsetCourse() {
    this.__isset_course = false;
  }




  
  private java.lang.String howDidYouFindOurWebsite ;

  /**
  <br>  Derived from HowDidYouFindOurWebsite.

  <br>  schema name = ['http://soap-api.gftforex.com']:HowDidYouFindOurWebsite
  <br>  schema type = ['http://soap-api.gftforex.com']:String1024
  <br>  schema formQualified = true
  */
  public java.lang.String getHowDidYouFindOurWebsite() {
    return howDidYouFindOurWebsite;
  }

  public void setHowDidYouFindOurWebsite(java.lang.String v) {
    
    this.howDidYouFindOurWebsite = v;
    this.__isset_howDidYouFindOurWebsite = true;

  }

  private boolean __isset_howDidYouFindOurWebsite;
  public boolean _isSetHowDidYouFindOurWebsite() {
    return this.__isset_howDidYouFindOurWebsite;
  }
  public void _unsetHowDidYouFindOurWebsite() {
    this.__isset_howDidYouFindOurWebsite = false;
  }




  
  private java.lang.String iLearnedOfGFTVia ;

  /**
  <br>  Derived from ILearnedOfGFTVia.

  <br>  schema name = ['http://soap-api.gftforex.com']:ILearnedOfGFTVia
  <br>  schema type = ['http://soap-api.gftforex.com']:String1024
  <br>  schema formQualified = true
  */
  public java.lang.String getILearnedOfGFTVia() {
    return iLearnedOfGFTVia;
  }

  public void setILearnedOfGFTVia(java.lang.String v) {
    
    this.iLearnedOfGFTVia = v;
    this.__isset_iLearnedOfGFTVia = true;

  }

  private boolean __isset_iLearnedOfGFTVia;
  public boolean _isSetILearnedOfGFTVia() {
    return this.__isset_iLearnedOfGFTVia;
  }
  public void _unsetILearnedOfGFTVia() {
    this.__isset_iLearnedOfGFTVia = false;
  }




  
  private java.lang.String iWasReferredBy ;

  /**
  <br>  Derived from IWasReferredBy.

  <br>  schema name = ['http://soap-api.gftforex.com']:IWasReferredBy
  <br>  schema type = ['http://soap-api.gftforex.com']:String1024
  <br>  schema formQualified = true
  */
  public java.lang.String getIWasReferredBy() {
    return iWasReferredBy;
  }

  public void setIWasReferredBy(java.lang.String v) {
    
    this.iWasReferredBy = v;
    this.__isset_iWasReferredBy = true;

  }

  private boolean __isset_iWasReferredBy;
  public boolean _isSetIWasReferredBy() {
    return this.__isset_iWasReferredBy;
  }
  public void _unsetIWasReferredBy() {
    this.__isset_iWasReferredBy = false;
  }




  
  private java.lang.String notTraded ;

  /**
  <br>  Derived from NotTraded.

  <br>  schema name = ['http://soap-api.gftforex.com']:NotTraded
  <br>  schema type = ['http://soap-api.gftforex.com']:String1024
  <br>  schema formQualified = true
  */
  public java.lang.String getNotTraded() {
    return notTraded;
  }

  public void setNotTraded(java.lang.String v) {
    
    this.notTraded = v;
    this.__isset_notTraded = true;

  }

  private boolean __isset_notTraded;
  public boolean _isSetNotTraded() {
    return this.__isset_notTraded;
  }
  public void _unsetNotTraded() {
    this.__isset_notTraded = false;
  }




  
  private java.lang.String otherReason ;

  /**
  <br>  Derived from OtherReason.

  <br>  schema name = ['http://soap-api.gftforex.com']:OtherReason
  <br>  schema type = ['http://soap-api.gftforex.com']:String1024
  <br>  schema formQualified = true
  */
  public java.lang.String getOtherReason() {
    return otherReason;
  }

  public void setOtherReason(java.lang.String v) {
    
    this.otherReason = v;
    this.__isset_otherReason = true;

  }

  private boolean __isset_otherReason;
  public boolean _isSetOtherReason() {
    return this.__isset_otherReason;
  }
  public void _unsetOtherReason() {
    this.__isset_otherReason = false;
  }




  
  private java.lang.String otherServices ;

  /**
  <br>  Derived from OtherServices.

  <br>  schema name = ['http://soap-api.gftforex.com']:OtherServices
  <br>  schema type = ['http://soap-api.gftforex.com']:String1024
  <br>  schema formQualified = true
  */
  public java.lang.String getOtherServices() {
    return otherServices;
  }

  public void setOtherServices(java.lang.String v) {
    
    this.otherServices = v;
    this.__isset_otherServices = true;

  }

  private boolean __isset_otherServices;
  public boolean _isSetOtherServices() {
    return this.__isset_otherServices;
  }
  public void _unsetOtherServices() {
    this.__isset_otherServices = false;
  }




  
  private java.lang.String otherWants ;

  /**
  <br>  Derived from OtherWants.

  <br>  schema name = ['http://soap-api.gftforex.com']:OtherWants
  <br>  schema type = ['http://soap-api.gftforex.com']:String1024
  <br>  schema formQualified = true
  */
  public java.lang.String getOtherWants() {
    return otherWants;
  }

  public void setOtherWants(java.lang.String v) {
    
    this.otherWants = v;
    this.__isset_otherWants = true;

  }

  private boolean __isset_otherWants;
  public boolean _isSetOtherWants() {
    return this.__isset_otherWants;
  }
  public void _unsetOtherWants() {
    this.__isset_otherWants = false;
  }




  
  private java.lang.String pleaseContactMeVia ;

  /**
  <br>  Derived from PleaseContactMeVia.

  <br>  schema name = ['http://soap-api.gftforex.com']:PleaseContactMeVia
  <br>  schema type = ['http://soap-api.gftforex.com']:String1024
  <br>  schema formQualified = true
  */
  public java.lang.String getPleaseContactMeVia() {
    return pleaseContactMeVia;
  }

  public void setPleaseContactMeVia(java.lang.String v) {
    
    this.pleaseContactMeVia = v;
    this.__isset_pleaseContactMeVia = true;

  }

  private boolean __isset_pleaseContactMeVia;
  public boolean _isSetPleaseContactMeVia() {
    return this.__isset_pleaseContactMeVia;
  }
  public void _unsetPleaseContactMeVia() {
    this.__isset_pleaseContactMeVia = false;
  }




  
  private java.lang.String profession ;

  /**
  <br>  Derived from Profession.

  <br>  schema name = ['http://soap-api.gftforex.com']:Profession
  <br>  schema type = ['http://soap-api.gftforex.com']:String1024
  <br>  schema formQualified = true
  */
  public java.lang.String getProfession() {
    return profession;
  }

  public void setProfession(java.lang.String v) {
    
    this.profession = v;
    this.__isset_profession = true;

  }

  private boolean __isset_profession;
  public boolean _isSetProfession() {
    return this.__isset_profession;
  }
  public void _unsetProfession() {
    this.__isset_profession = false;
  }




  
  private java.lang.String serviceExpected ;

  /**
  <br>  Derived from ServiceExpected.

  <br>  schema name = ['http://soap-api.gftforex.com']:ServiceExpected
  <br>  schema type = ['http://soap-api.gftforex.com']:String1024
  <br>  schema formQualified = true
  */
  public java.lang.String getServiceExpected() {
    return serviceExpected;
  }

  public void setServiceExpected(java.lang.String v) {
    
    this.serviceExpected = v;
    this.__isset_serviceExpected = true;

  }

  private boolean __isset_serviceExpected;
  public boolean _isSetServiceExpected() {
    return this.__isset_serviceExpected;
  }
  public void _unsetServiceExpected() {
    this.__isset_serviceExpected = false;
  }




  
  private boolean tradedForex ;

  /**
  <br>  Derived from TradedForex.

  <br>  schema name = ['http://soap-api.gftforex.com']:TradedForex
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:boolean
  <br>  schema formQualified = true
  */
  public boolean getTradedForex() {
    return tradedForex;
  }

  public void setTradedForex(boolean v) {
    
    this.tradedForex = v;
    this.__isset_tradedForex = true;

  }

  private boolean __isset_tradedForex;
  public boolean _isSetTradedForex() {
    return this.__isset_tradedForex;
  }
  public void _unsetTradedForex() {
    this.__isset_tradedForex = false;
  }




  
  private boolean tradedFutures ;

  /**
  <br>  Derived from TradedFutures.

  <br>  schema name = ['http://soap-api.gftforex.com']:TradedFutures
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:boolean
  <br>  schema formQualified = true
  */
  public boolean getTradedFutures() {
    return tradedFutures;
  }

  public void setTradedFutures(boolean v) {
    
    this.tradedFutures = v;
    this.__isset_tradedFutures = true;

  }

  private boolean __isset_tradedFutures;
  public boolean _isSetTradedFutures() {
    return this.__isset_tradedFutures;
  }
  public void _unsetTradedFutures() {
    this.__isset_tradedFutures = false;
  }




  
  private boolean tradedStocks ;

  /**
  <br>  Derived from TradedStocks.

  <br>  schema name = ['http://soap-api.gftforex.com']:TradedStocks
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:boolean
  <br>  schema formQualified = true
  */
  public boolean getTradedStocks() {
    return tradedStocks;
  }

  public void setTradedStocks(boolean v) {
    
    this.tradedStocks = v;
    this.__isset_tradedStocks = true;

  }

  private boolean __isset_tradedStocks;
  public boolean _isSetTradedStocks() {
    return this.__isset_tradedStocks;
  }
  public void _unsetTradedStocks() {
    this.__isset_tradedStocks = false;
  }




  
  private boolean tradedOptions ;

  /**
  <br>  Derived from TradedOptions.

  <br>  schema name = ['http://soap-api.gftforex.com']:TradedOptions
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:boolean
  <br>  schema formQualified = true
  */
  public boolean getTradedOptions() {
    return tradedOptions;
  }

  public void setTradedOptions(boolean v) {
    
    this.tradedOptions = v;
    this.__isset_tradedOptions = true;

  }

  private boolean __isset_tradedOptions;
  public boolean _isSetTradedOptions() {
    return this.__isset_tradedOptions;
  }
  public void _unsetTradedOptions() {
    this.__isset_tradedOptions = false;
  }




  
  private java.lang.String webforexDemo ;

  /**
  <br>  Derived from WebforexDemo.

  <br>  schema name = ['http://soap-api.gftforex.com']:WebforexDemo
  <br>  schema type = ['http://soap-api.gftforex.com']:String1024
  <br>  schema formQualified = true
  */
  public java.lang.String getWebforexDemo() {
    return webforexDemo;
  }

  public void setWebforexDemo(java.lang.String v) {
    
    this.webforexDemo = v;
    this.__isset_webforexDemo = true;

  }

  private boolean __isset_webforexDemo;
  public boolean _isSetWebforexDemo() {
    return this.__isset_webforexDemo;
  }
  public void _unsetWebforexDemo() {
    this.__isset_webforexDemo = false;
  }




  
  private java.lang.String whatDoYouWant ;

  /**
  <br>  Derived from WhatDoYouWant.

  <br>  schema name = ['http://soap-api.gftforex.com']:WhatDoYouWant
  <br>  schema type = ['http://soap-api.gftforex.com']:String1024
  <br>  schema formQualified = true
  */
  public java.lang.String getWhatDoYouWant() {
    return whatDoYouWant;
  }

  public void setWhatDoYouWant(java.lang.String v) {
    
    this.whatDoYouWant = v;
    this.__isset_whatDoYouWant = true;

  }

  private boolean __isset_whatDoYouWant;
  public boolean _isSetWhatDoYouWant() {
    return this.__isset_whatDoYouWant;
  }
  public void _unsetWhatDoYouWant() {
    this.__isset_whatDoYouWant = false;
  }




  
  private java.lang.String whyForex ;

  /**
  <br>  Derived from WhyForex.

  <br>  schema name = ['http://soap-api.gftforex.com']:WhyForex
  <br>  schema type = ['http://soap-api.gftforex.com']:String1024
  <br>  schema formQualified = true
  */
  public java.lang.String getWhyForex() {
    return whyForex;
  }

  public void setWhyForex(java.lang.String v) {
    
    this.whyForex = v;
    this.__isset_whyForex = true;

  }

  private boolean __isset_whyForex;
  public boolean _isSetWhyForex() {
    return this.__isset_whyForex;
  }
  public void _unsetWhyForex() {
    this.__isset_whyForex = false;
  }




  
  private java.lang.String forexTradingExperience ;

  /**
  <br>  Derived from ForexTradingExperience.

  <br>  schema name = ['http://soap-api.gftforex.com']:ForexTradingExperience
  <br>  schema type = ['http://soap-api.gftforex.com']:String1024
  <br>  schema formQualified = true
  */
  public java.lang.String getForexTradingExperience() {
    return forexTradingExperience;
  }

  public void setForexTradingExperience(java.lang.String v) {
    
    this.forexTradingExperience = v;
    this.__isset_forexTradingExperience = true;

  }

  private boolean __isset_forexTradingExperience;
  public boolean _isSetForexTradingExperience() {
    return this.__isset_forexTradingExperience;
  }
  public void _unsetForexTradingExperience() {
    this.__isset_forexTradingExperience = false;
  }




  
  private java.lang.String futuresOptionsTradingExperience ;

  /**
  <br>  Derived from FuturesOptionsTradingExperience.

  <br>  schema name = ['http://soap-api.gftforex.com']:FuturesOptionsTradingExperience
  <br>  schema type = ['http://soap-api.gftforex.com']:String1024
  <br>  schema formQualified = true
  */
  public java.lang.String getFuturesOptionsTradingExperience() {
    return futuresOptionsTradingExperience;
  }

  public void setFuturesOptionsTradingExperience(java.lang.String v) {
    
    this.futuresOptionsTradingExperience = v;
    this.__isset_futuresOptionsTradingExperience = true;

  }

  private boolean __isset_futuresOptionsTradingExperience;
  public boolean _isSetFuturesOptionsTradingExperience() {
    return this.__isset_futuresOptionsTradingExperience;
  }
  public void _unsetFuturesOptionsTradingExperience() {
    this.__isset_futuresOptionsTradingExperience = false;
  }




  
  private java.lang.String otherTradingExperience ;

  /**
  <br>  Derived from OtherTradingExperience.

  <br>  schema name = ['http://soap-api.gftforex.com']:OtherTradingExperience
  <br>  schema type = ['http://soap-api.gftforex.com']:String1024
  <br>  schema formQualified = true
  */
  public java.lang.String getOtherTradingExperience() {
    return otherTradingExperience;
  }

  public void setOtherTradingExperience(java.lang.String v) {
    
    this.otherTradingExperience = v;
    this.__isset_otherTradingExperience = true;

  }

  private boolean __isset_otherTradingExperience;
  public boolean _isSetOtherTradingExperience() {
    return this.__isset_otherTradingExperience;
  }
  public void _unsetOtherTradingExperience() {
    this.__isset_otherTradingExperience = false;
  }




  
  private java.lang.String internetConnection ;

  /**
  <br>  Derived from InternetConnection.

  <br>  schema name = ['http://soap-api.gftforex.com']:InternetConnection
  <br>  schema type = ['http://soap-api.gftforex.com']:String1024
  <br>  schema formQualified = true
  */
  public java.lang.String getInternetConnection() {
    return internetConnection;
  }

  public void setInternetConnection(java.lang.String v) {
    
    this.internetConnection = v;
    this.__isset_internetConnection = true;

  }

  private boolean __isset_internetConnection;
  public boolean _isSetInternetConnection() {
    return this.__isset_internetConnection;
  }
  public void _unsetInternetConnection() {
    this.__isset_internetConnection = false;
  }









  public java.lang.String toString() {
  return "UserPersonalInfo" + "{"
             + " firstName=<" + getFirstName() + ">"
             + " lastName=<" + getLastName() + ">"
             + " email=<" + getEmail() + ">"
             + " mobilePhone=<" + getMobilePhone() + ">"
             + " homePhone=<" + getHomePhone() + ">"
             + " dayPhone=<" + getDayPhone() + ">"
             + " faxNumber=<" + getFaxNumber() + ">"
             + " streetAdress=<" + getStreetAdress() + ">"
             + " city=<" + getCity() + ">"
             + " state=<" + getState() + ">"
             + " country=<" + getCountry() + ">"
             + " zipCode=<" + getZipCode() + ">"
             + " bestTimeToCall=<" + getBestTimeToCall() + ">"
             + " course=<" + getCourse() + ">"
             + " howDidYouFindOurWebsite=<" + getHowDidYouFindOurWebsite() + ">"
             + " iLearnedOfGFTVia=<" + getILearnedOfGFTVia() + ">"
             + " iWasReferredBy=<" + getIWasReferredBy() + ">"
             + " notTraded=<" + getNotTraded() + ">"
             + " otherReason=<" + getOtherReason() + ">"
             + " otherServices=<" + getOtherServices() + ">"
             + " otherWants=<" + getOtherWants() + ">"
             + " pleaseContactMeVia=<" + getPleaseContactMeVia() + ">"
             + " profession=<" + getProfession() + ">"
             + " serviceExpected=<" + getServiceExpected() + ">"
             + " tradedForex=<" + getTradedForex() + ">"
             + " tradedFutures=<" + getTradedFutures() + ">"
             + " tradedStocks=<" + getTradedStocks() + ">"
             + " tradedOptions=<" + getTradedOptions() + ">"
             + " webforexDemo=<" + getWebforexDemo() + ">"
             + " whatDoYouWant=<" + getWhatDoYouWant() + ">"
             + " whyForex=<" + getWhyForex() + ">"
             + " forexTradingExperience=<" + getForexTradingExperience() + ">"
             + " futuresOptionsTradingExperience=<" + getFuturesOptionsTradingExperience() + ">"
             + " otherTradingExperience=<" + getOtherTradingExperience() + ">"
             + " internetConnection=<" + getInternetConnection() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof UserPersonalInfo) {
      
      UserPersonalInfo __obj__ =  (UserPersonalInfo) __other__;


      return true
            && (tradedForex == __obj__.getTradedForex())
            && (tradedFutures == __obj__.getTradedFutures())
            && (tradedStocks == __obj__.getTradedStocks())
            && (tradedOptions == __obj__.getTradedOptions())
            && (firstName==null ? __obj__.getFirstName()==null : firstName.equals(__obj__.getFirstName()))
            && (lastName==null ? __obj__.getLastName()==null : lastName.equals(__obj__.getLastName()))
            && (email==null ? __obj__.getEmail()==null : email.equals(__obj__.getEmail()))
            && (mobilePhone==null ? __obj__.getMobilePhone()==null : mobilePhone.equals(__obj__.getMobilePhone()))
            && (homePhone==null ? __obj__.getHomePhone()==null : homePhone.equals(__obj__.getHomePhone()))
            && (dayPhone==null ? __obj__.getDayPhone()==null : dayPhone.equals(__obj__.getDayPhone()))
            && (faxNumber==null ? __obj__.getFaxNumber()==null : faxNumber.equals(__obj__.getFaxNumber()))
            && (streetAdress==null ? __obj__.getStreetAdress()==null : streetAdress.equals(__obj__.getStreetAdress()))
            && (city==null ? __obj__.getCity()==null : city.equals(__obj__.getCity()))
            && (state==null ? __obj__.getState()==null : state.equals(__obj__.getState()))
            && (country==null ? __obj__.getCountry()==null : country.equals(__obj__.getCountry()))
            && (zipCode==null ? __obj__.getZipCode()==null : zipCode.equals(__obj__.getZipCode()))
            && (bestTimeToCall==null ? __obj__.getBestTimeToCall()==null : bestTimeToCall.equals(__obj__.getBestTimeToCall()))
            && (course==null ? __obj__.getCourse()==null : course.equals(__obj__.getCourse()))
            && (howDidYouFindOurWebsite==null ? __obj__.getHowDidYouFindOurWebsite()==null : howDidYouFindOurWebsite.equals(__obj__.getHowDidYouFindOurWebsite()))
            && (iLearnedOfGFTVia==null ? __obj__.getILearnedOfGFTVia()==null : iLearnedOfGFTVia.equals(__obj__.getILearnedOfGFTVia()))
            && (iWasReferredBy==null ? __obj__.getIWasReferredBy()==null : iWasReferredBy.equals(__obj__.getIWasReferredBy()))
            && (notTraded==null ? __obj__.getNotTraded()==null : notTraded.equals(__obj__.getNotTraded()))
            && (otherReason==null ? __obj__.getOtherReason()==null : otherReason.equals(__obj__.getOtherReason()))
            && (otherServices==null ? __obj__.getOtherServices()==null : otherServices.equals(__obj__.getOtherServices()))
            && (otherWants==null ? __obj__.getOtherWants()==null : otherWants.equals(__obj__.getOtherWants()))
            && (pleaseContactMeVia==null ? __obj__.getPleaseContactMeVia()==null : pleaseContactMeVia.equals(__obj__.getPleaseContactMeVia()))
            && (profession==null ? __obj__.getProfession()==null : profession.equals(__obj__.getProfession()))
            && (serviceExpected==null ? __obj__.getServiceExpected()==null : serviceExpected.equals(__obj__.getServiceExpected()))
            && (webforexDemo==null ? __obj__.getWebforexDemo()==null : webforexDemo.equals(__obj__.getWebforexDemo()))
            && (whatDoYouWant==null ? __obj__.getWhatDoYouWant()==null : whatDoYouWant.equals(__obj__.getWhatDoYouWant()))
            && (whyForex==null ? __obj__.getWhyForex()==null : whyForex.equals(__obj__.getWhyForex()))
            && (forexTradingExperience==null ? __obj__.getForexTradingExperience()==null : forexTradingExperience.equals(__obj__.getForexTradingExperience()))
            && (futuresOptionsTradingExperience==null ? __obj__.getFuturesOptionsTradingExperience()==null : futuresOptionsTradingExperience.equals(__obj__.getFuturesOptionsTradingExperience()))
            && (otherTradingExperience==null ? __obj__.getOtherTradingExperience()==null : otherTradingExperience.equals(__obj__.getOtherTradingExperience()))
            && (internetConnection==null ? __obj__.getInternetConnection()==null : internetConnection.equals(__obj__.getInternetConnection()))


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (firstName==null ? 0 : firstName.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (lastName==null ? 0 : lastName.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (email==null ? 0 : email.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (mobilePhone==null ? 0 : mobilePhone.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (homePhone==null ? 0 : homePhone.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (dayPhone==null ? 0 : dayPhone.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (faxNumber==null ? 0 : faxNumber.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (streetAdress==null ? 0 : streetAdress.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (city==null ? 0 : city.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (state==null ? 0 : state.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (country==null ? 0 : country.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (zipCode==null ? 0 : zipCode.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (bestTimeToCall==null ? 0 : bestTimeToCall.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (course==null ? 0 : course.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (howDidYouFindOurWebsite==null ? 0 : howDidYouFindOurWebsite.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (iLearnedOfGFTVia==null ? 0 : iLearnedOfGFTVia.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (iWasReferredBy==null ? 0 : iWasReferredBy.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (notTraded==null ? 0 : notTraded.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (otherReason==null ? 0 : otherReason.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (otherServices==null ? 0 : otherServices.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (otherWants==null ? 0 : otherWants.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (pleaseContactMeVia==null ? 0 : pleaseContactMeVia.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (profession==null ? 0 : profession.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (serviceExpected==null ? 0 : serviceExpected.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (tradedForex ? 0 : 1) ;
    __hash__result__ = 37*__hash__result__ + (tradedFutures ? 0 : 1) ;
    __hash__result__ = 37*__hash__result__ + (tradedStocks ? 0 : 1) ;
    __hash__result__ = 37*__hash__result__ + (tradedOptions ? 0 : 1) ;
    __hash__result__ = 37*__hash__result__ + (webforexDemo==null ? 0 : webforexDemo.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (whatDoYouWant==null ? 0 : whatDoYouWant.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (whyForex==null ? 0 : whyForex.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (forexTradingExperience==null ? 0 : forexTradingExperience.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (futuresOptionsTradingExperience==null ? 0 : futuresOptionsTradingExperience.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (otherTradingExperience==null ? 0 : otherTradingExperience.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (internetConnection==null ? 0 : internetConnection.hashCode()) ;
    

    return __hash__result__;
  }

}


@


1.1
log
@*** empty log message ***
@
text
@@

