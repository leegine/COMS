head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.06.27.06.13.35;	author zhang-tengyu;	state dead;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	444;
commitid	8d84e081ce81473;
filename	UpdateAccountInfo.java;

1.1
date	2011.03.15.02.46.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	UpdateAccountInfo.java;


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

// original type: ['http://soap-api.gftforex.com']:UpdateAccountInfo


public  class UpdateAccountInfo 
  
  implements java.io.Serializable
{

  public UpdateAccountInfo() {}

  public UpdateAccountInfo(com.gftforex.soap.api.CanTrade p_canTrade
,     long p_accountId
,     boolean p_isDisabled
,     java.lang.String p_label
,     long[] p_profilesToAdd
,     long[] p_profilesToRemove) 
  {
     this.canTrade = p_canTrade;
     this.__isset_canTrade = true;

     this.accountId = p_accountId;
     
     this.isDisabled = p_isDisabled;
     this.__isset_isDisabled = true;

     this.label = p_label;
     this.__isset_label = true;

     this.profilesToAdd = p_profilesToAdd;
     this.__isset_profilesToAdd = true;

     this.profilesToRemove = p_profilesToRemove;
     this.__isset_profilesToRemove = true;


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




  
  private long[] profilesToAdd ;

  /**
  <br>  Derived from ProfilesToAdd.

  <br>  schema name = ['http://soap-api.gftforex.com']:ProfilesToAdd
  <br>  schema type = ['http://soap-api.gftforex.com']:UpdateAccountInfo__profilesToAdd__ArrayAnonType
  <br>  schema formQualified = true
  */
  public long[] getProfilesToAdd() {
    return profilesToAdd;
  }

  public void setProfilesToAdd(long[] v) {
    
    this.profilesToAdd = v;
    this.__isset_profilesToAdd = true;

  }

  private boolean __isset_profilesToAdd;
  public boolean _isSetProfilesToAdd() {
    return this.__isset_profilesToAdd;
  }
  public void _unsetProfilesToAdd() {
    this.__isset_profilesToAdd = false;
  }




  
  private long[] profilesToRemove ;

  /**
  <br>  Derived from ProfilesToRemove.

  <br>  schema name = ['http://soap-api.gftforex.com']:ProfilesToRemove
  <br>  schema type = ['http://soap-api.gftforex.com']:UpdateAccountInfo__profilesToRemove__ArrayAnonType
  <br>  schema formQualified = true
  */
  public long[] getProfilesToRemove() {
    return profilesToRemove;
  }

  public void setProfilesToRemove(long[] v) {
    
    this.profilesToRemove = v;
    this.__isset_profilesToRemove = true;

  }

  private boolean __isset_profilesToRemove;
  public boolean _isSetProfilesToRemove() {
    return this.__isset_profilesToRemove;
  }
  public void _unsetProfilesToRemove() {
    this.__isset_profilesToRemove = false;
  }









  public java.lang.String toString() {
  return "UpdateAccountInfo" + "{"
             + " canTrade=<" + getCanTrade() + ">"
             + " accountId=<" + getAccountId() + ">"
             + " isDisabled=<" + getIsDisabled() + ">"
             + " label=<" + getLabel() + ">"
             + " profilesToAdd=<" + weblogic.xml.schema.binding.RuntimeUtils.arrayToString( getProfilesToAdd() ) + ">"
             + " profilesToRemove=<" + weblogic.xml.schema.binding.RuntimeUtils.arrayToString( getProfilesToRemove() ) + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof UpdateAccountInfo) {
      
      UpdateAccountInfo __obj__ =  (UpdateAccountInfo) __other__;


      return true
            && (accountId == __obj__.getAccountId())
            && (isDisabled == __obj__.getIsDisabled())
            && (canTrade==null ? __obj__.getCanTrade()==null : canTrade.equals(__obj__.getCanTrade()))
            && (label==null ? __obj__.getLabel()==null : label.equals(__obj__.getLabel()))
            && weblogic.xml.schema.binding.RuntimeUtils.compareObjects(profilesToAdd, __obj__.getProfilesToAdd())
            && weblogic.xml.schema.binding.RuntimeUtils.compareObjects(profilesToRemove, __obj__.getProfilesToRemove())


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (canTrade==null ? 0 : canTrade.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (int)accountId ;
    __hash__result__ = 37*__hash__result__ + (isDisabled ? 0 : 1) ;
    __hash__result__ = 37*__hash__result__ + (label==null ? 0 : label.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (profilesToAdd==null ? 0 : weblogic.xml.schema.binding.RuntimeUtils.arrayHashCode(profilesToAdd)) ;
    __hash__result__ = 37*__hash__result__ + (profilesToRemove==null ? 0 : weblogic.xml.schema.binding.RuntimeUtils.arrayHashCode(profilesToRemove)) ;
    

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

