head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.06.27.06.05.35;	author zhang-tengyu;	state dead;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	444;
commitid	8d84e081ce81473;
filename	CreateAccountInfo.java;

1.1
date	2011.03.15.02.40.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	CreateAccountInfo.java;


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

// original type: ['http://soap-api.gftforex.com']:CreateAccountInfo


public  class CreateAccountInfo 
  
  implements java.io.Serializable
{

  public CreateAccountInfo() {}

  public CreateAccountInfo(java.lang.String p_accountTemplateId
,     com.gftforex.soap.api.CanTrade p_canTrade
,     double p_initialDeposit
,     java.lang.String p_baseCurrency
,     long p_lotSize
,     java.lang.String p_label
,     boolean p_isDisabled
,     long[] p_profiles) 
  {
     this.accountTemplateId = p_accountTemplateId;
     this.__isset_accountTemplateId = true;

     this.canTrade = p_canTrade;
     this.__isset_canTrade = true;

     this.initialDeposit = p_initialDeposit;
     this.__isset_initialDeposit = true;

     this.baseCurrency = p_baseCurrency;
     this.__isset_baseCurrency = true;

     this.lotSize = p_lotSize;
     this.__isset_lotSize = true;

     this.label = p_label;
     this.__isset_label = true;

     this.isDisabled = p_isDisabled;
     this.__isset_isDisabled = true;

     this.profiles = p_profiles;
     this.__isset_profiles = true;


  }




  
  private java.lang.String accountTemplateId ;

  /**
  <br>  Derived from AccountTemplateId.

  <br>  schema name = ['http://soap-api.gftforex.com']:AccountTemplateId
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getAccountTemplateId() {
    return accountTemplateId;
  }

  public void setAccountTemplateId(java.lang.String v) {
    
    this.accountTemplateId = v;
    this.__isset_accountTemplateId = true;

  }

  private boolean __isset_accountTemplateId;
  public boolean _isSetAccountTemplateId() {
    return this.__isset_accountTemplateId;
  }
  public void _unsetAccountTemplateId() {
    this.__isset_accountTemplateId = false;
  }




  
  private com.gftforex.soap.api.CanTrade canTrade ;

  /**
  <br>  Derived from CanTrade.

  <br>  schema name = ['http://soap-api.gftforex.com']:CanTrade
  <br>  schema type = ['http://soap-api.gftforex.com']:CanTrade
  <br>  schema formQualified = true
  */
  public com.gftforex.soap.api.CanTrade getCanTrade() {
    return canTrade;
  }

  public void setCanTrade(com.gftforex.soap.api.CanTrade v) {
    
    this.canTrade = v;
    this.__isset_canTrade = true;

  }

  private boolean __isset_canTrade;
  public boolean _isSetCanTrade() {
    return this.__isset_canTrade;
  }
  public void _unsetCanTrade() {
    this.__isset_canTrade = false;
  }




  
  private double initialDeposit ;

  /**
  <br>  Derived from InitialDeposit.

  <br>  schema name = ['http://soap-api.gftforex.com']:InitialDeposit
  <br>  schema type = ['http://soap-api.gftforex.com']:RoundSafeAmount
  <br>  schema formQualified = true
  */
  public double getInitialDeposit() {
    return initialDeposit;
  }

  public void setInitialDeposit(double v) {
    
    this.initialDeposit = v;
    this.__isset_initialDeposit = true;

  }

  private boolean __isset_initialDeposit;
  public boolean _isSetInitialDeposit() {
    return this.__isset_initialDeposit;
  }
  public void _unsetInitialDeposit() {
    this.__isset_initialDeposit = false;
  }




  
  private java.lang.String baseCurrency ;

  /**
  <br>  Derived from BaseCurrency.

  <br>  schema name = ['http://soap-api.gftforex.com']:BaseCurrency
  <br>  schema type = ['http://soap-api.gftforex.com']:Currency
  <br>  schema formQualified = true
  */
  public java.lang.String getBaseCurrency() {
    return baseCurrency;
  }

  public void setBaseCurrency(java.lang.String v) {
    
    this.baseCurrency = v;
    this.__isset_baseCurrency = true;

  }

  private boolean __isset_baseCurrency;
  public boolean _isSetBaseCurrency() {
    return this.__isset_baseCurrency;
  }
  public void _unsetBaseCurrency() {
    this.__isset_baseCurrency = false;
  }




  
  private long lotSize ;

  /**
  <br>  Derived from LotSize.

  <br>  schema name = ['http://soap-api.gftforex.com']:LotSize
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:unsignedInt
  <br>  schema formQualified = true
  */
  public long getLotSize() {
    return lotSize;
  }

  public void setLotSize(long v) {
    
    this.lotSize = v;
    this.__isset_lotSize = true;

  }

  private boolean __isset_lotSize;
  public boolean _isSetLotSize() {
    return this.__isset_lotSize;
  }
  public void _unsetLotSize() {
    this.__isset_lotSize = false;
  }




  
  private java.lang.String label ;

  /**
  <br>  Derived from Label.

  <br>  schema name = ['http://soap-api.gftforex.com']:Label
  <br>  schema type = ['http://soap-api.gftforex.com']:String64
  <br>  schema formQualified = true
  */
  public java.lang.String getLabel() {
    return label;
  }

  public void setLabel(java.lang.String v) {
    
    this.label = v;
    this.__isset_label = true;

  }

  private boolean __isset_label;
  public boolean _isSetLabel() {
    return this.__isset_label;
  }
  public void _unsetLabel() {
    this.__isset_label = false;
  }




  
  private boolean isDisabled ;

  /**
  <br>  Derived from IsDisabled.

  <br>  schema name = ['http://soap-api.gftforex.com']:IsDisabled
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:boolean
  <br>  schema formQualified = true
  */
  public boolean getIsDisabled() {
    return isDisabled;
  }

  public void setIsDisabled(boolean v) {
    
    this.isDisabled = v;
    this.__isset_isDisabled = true;

  }

  private boolean __isset_isDisabled;
  public boolean _isSetIsDisabled() {
    return this.__isset_isDisabled;
  }
  public void _unsetIsDisabled() {
    this.__isset_isDisabled = false;
  }




  
  private long[] profiles ;

  /**
  <br>  Derived from Profiles.

  <br>  schema name = ['http://soap-api.gftforex.com']:Profiles
  <br>  schema type = ['http://soap-api.gftforex.com']:CreateAccountInfo__profiles__ArrayAnonType
  <br>  schema formQualified = true
  */
  public long[] getProfiles() {
    return profiles;
  }

  public void setProfiles(long[] v) {
    
    this.profiles = v;
    this.__isset_profiles = true;

  }

  private boolean __isset_profiles;
  public boolean _isSetProfiles() {
    return this.__isset_profiles;
  }
  public void _unsetProfiles() {
    this.__isset_profiles = false;
  }









  public java.lang.String toString() {
  return "CreateAccountInfo" + "{"
             + " accountTemplateId=<" + getAccountTemplateId() + ">"
             + " canTrade=<" + getCanTrade() + ">"
             + " initialDeposit=<" + getInitialDeposit() + ">"
             + " baseCurrency=<" + getBaseCurrency() + ">"
             + " lotSize=<" + getLotSize() + ">"
             + " label=<" + getLabel() + ">"
             + " isDisabled=<" + getIsDisabled() + ">"
             + " profiles=<" + weblogic.xml.schema.binding.RuntimeUtils.arrayToString( getProfiles() ) + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof CreateAccountInfo) {
      
      CreateAccountInfo __obj__ =  (CreateAccountInfo) __other__;


      return true
            && (initialDeposit == __obj__.getInitialDeposit())
            && (lotSize == __obj__.getLotSize())
            && (isDisabled == __obj__.getIsDisabled())
            && (accountTemplateId==null ? __obj__.getAccountTemplateId()==null : accountTemplateId.equals(__obj__.getAccountTemplateId()))
            && (canTrade==null ? __obj__.getCanTrade()==null : canTrade.equals(__obj__.getCanTrade()))
            && (baseCurrency==null ? __obj__.getBaseCurrency()==null : baseCurrency.equals(__obj__.getBaseCurrency()))
            && (label==null ? __obj__.getLabel()==null : label.equals(__obj__.getLabel()))
            && weblogic.xml.schema.binding.RuntimeUtils.compareObjects(profiles, __obj__.getProfiles())


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (accountTemplateId==null ? 0 : accountTemplateId.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (canTrade==null ? 0 : canTrade.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (int)initialDeposit ;
    __hash__result__ = 37*__hash__result__ + (baseCurrency==null ? 0 : baseCurrency.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (int)lotSize ;
    __hash__result__ = 37*__hash__result__ + (label==null ? 0 : label.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (isDisabled ? 0 : 1) ;
    __hash__result__ = 37*__hash__result__ + (profiles==null ? 0 : weblogic.xml.schema.binding.RuntimeUtils.arrayHashCode(profiles)) ;
    

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

