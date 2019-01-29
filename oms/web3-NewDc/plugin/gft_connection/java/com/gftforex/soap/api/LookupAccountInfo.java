head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.06.27.06.05.56;	author zhang-tengyu;	state dead;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	444;
commitid	8d84e081ce81473;
filename	LookupAccountInfo.java;

1.1
date	2011.03.15.02.46.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	LookupAccountInfo.java;


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

// original type: ['http://soap-api.gftforex.com']:LookupAccountInfo


public  class LookupAccountInfo 
  
  implements java.io.Serializable
{

  public LookupAccountInfo() {}

  public LookupAccountInfo(long p_accountId
,     com.gftforex.soap.api.CanTrade p_canTrade
,     java.lang.String p_baseCurrency
,     long p_lotSize
,     double p_withdrawableAmount
,     java.lang.String p_label
,     boolean p_isDisabled
,     long[] p_profiles) 
  {
     this.accountId = p_accountId;
     
     this.canTrade = p_canTrade;
     
     this.baseCurrency = p_baseCurrency;
     
     this.lotSize = p_lotSize;
     
     this.withdrawableAmount = p_withdrawableAmount;
     
     this.label = p_label;
     this.__isset_label = true;

     this.isDisabled = p_isDisabled;
     
     this.profiles = p_profiles;
     this.__isset_profiles = true;


  }




  
  private long accountId ;

  /**
  <br>  Derived from AccountId.

  <br>  schema name = ['http://soap-api.gftforex.com']:AccountId
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:unsignedInt
  <br>  schema formQualified = true
  */
  public long getAccountId() {
    return accountId;
  }

  public void setAccountId(long v) {
    
    this.accountId = v;
    
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
    
  }





  
  private double withdrawableAmount ;

  /**
  <br>  Derived from WithdrawableAmount.

  <br>  schema name = ['http://soap-api.gftforex.com']:WithdrawableAmount
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:double
  <br>  schema formQualified = true
  */
  public double getWithdrawableAmount() {
    return withdrawableAmount;
  }

  public void setWithdrawableAmount(double v) {
    
    this.withdrawableAmount = v;
    
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
    
  }





  
  private long[] profiles ;

  /**
  <br>  Derived from Profiles.

  <br>  schema name = ['http://soap-api.gftforex.com']:Profiles
  <br>  schema type = ['http://soap-api.gftforex.com']:LookupAccountInfo__profiles__ArrayAnonType
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
  return "LookupAccountInfo" + "{"
             + " accountId=<" + getAccountId() + ">"
             + " canTrade=<" + getCanTrade() + ">"
             + " baseCurrency=<" + getBaseCurrency() + ">"
             + " lotSize=<" + getLotSize() + ">"
             + " withdrawableAmount=<" + getWithdrawableAmount() + ">"
             + " label=<" + getLabel() + ">"
             + " isDisabled=<" + getIsDisabled() + ">"
             + " profiles=<" + weblogic.xml.schema.binding.RuntimeUtils.arrayToString( getProfiles() ) + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof LookupAccountInfo) {
      
      LookupAccountInfo __obj__ =  (LookupAccountInfo) __other__;


      return true
            && (accountId == __obj__.getAccountId())
            && (lotSize == __obj__.getLotSize())
            && (withdrawableAmount == __obj__.getWithdrawableAmount())
            && (isDisabled == __obj__.getIsDisabled())
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

    __hash__result__ = 37*__hash__result__ + (int)accountId ;
    __hash__result__ = 37*__hash__result__ + (canTrade==null ? 0 : canTrade.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (baseCurrency==null ? 0 : baseCurrency.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (int)lotSize ;
    __hash__result__ = 37*__hash__result__ + (int)withdrawableAmount ;
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

