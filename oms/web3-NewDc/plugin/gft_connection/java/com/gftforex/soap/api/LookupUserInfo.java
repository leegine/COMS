head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.06.27.06.06.40;	author zhang-tengyu;	state dead;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	444;
commitid	8d84e081ce81473;
filename	LookupUserInfo.java;

1.1
date	2011.03.15.02.39.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	LookupUserInfo.java;


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

// original type: ['http://soap-api.gftforex.com']:LookupUserInfo


public  class LookupUserInfo 
  
  implements java.io.Serializable
{

  public LookupUserInfo() {}

  public LookupUserInfo(com.gftforex.soap.api.UserPersonalInfo p_userPersonalInfo
,     com.gftforex.soap.api.UserSystemInfo p_userSystemInfo
,     com.gftforex.soap.api.LookupAccountInfo[] p_accounts) 
  {
     this.userPersonalInfo = p_userPersonalInfo;
     this.__isset_userPersonalInfo = true;

     this.userSystemInfo = p_userSystemInfo;
     
     this.accounts = p_accounts;
     

  }




  
  private com.gftforex.soap.api.UserPersonalInfo userPersonalInfo ;

  /**
  <br>  Derived from UserPersonalInfo.

  <br>  schema name = ['http://soap-api.gftforex.com']:UserPersonalInfo
  <br>  schema type = ['http://soap-api.gftforex.com']:UserPersonalInfo
  <br>  schema formQualified = true
  */
  public com.gftforex.soap.api.UserPersonalInfo getUserPersonalInfo() {
    return userPersonalInfo;
  }

  public void setUserPersonalInfo(com.gftforex.soap.api.UserPersonalInfo v) {
    
    this.userPersonalInfo = v;
    this.__isset_userPersonalInfo = true;

  }

  private boolean __isset_userPersonalInfo;
  public boolean _isSetUserPersonalInfo() {
    return this.__isset_userPersonalInfo;
  }
  public void _unsetUserPersonalInfo() {
    this.__isset_userPersonalInfo = false;
  }




  
  private com.gftforex.soap.api.UserSystemInfo userSystemInfo ;

  /**
  <br>  Derived from UserSystemInfo.

  <br>  schema name = ['http://soap-api.gftforex.com']:UserSystemInfo
  <br>  schema type = ['http://soap-api.gftforex.com']:UserSystemInfo
  <br>  schema formQualified = true
  */
  public com.gftforex.soap.api.UserSystemInfo getUserSystemInfo() {
    return userSystemInfo;
  }

  public void setUserSystemInfo(com.gftforex.soap.api.UserSystemInfo v) {
    
    this.userSystemInfo = v;
    
  }





  
  private com.gftforex.soap.api.LookupAccountInfo[] accounts ;

  /**
  <br>  Derived from Accounts.

  <br>  schema name = ['http://soap-api.gftforex.com']:Accounts
  <br>  schema type = ['http://soap-api.gftforex.com']:LookupUserInfo__accounts__ArrayAnonType
  <br>  schema formQualified = true
  */
  public com.gftforex.soap.api.LookupAccountInfo[] getAccounts() {
    return accounts;
  }

  public void setAccounts(com.gftforex.soap.api.LookupAccountInfo[] v) {
    
    this.accounts = v;
    
  }










  public java.lang.String toString() {
  return "LookupUserInfo" + "{"
             + " userPersonalInfo=<" + getUserPersonalInfo() + ">"
             + " userSystemInfo=<" + getUserSystemInfo() + ">"
             + " accounts=<" + weblogic.xml.schema.binding.RuntimeUtils.arrayToString( getAccounts() ) + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof LookupUserInfo) {
      
      LookupUserInfo __obj__ =  (LookupUserInfo) __other__;


      return true
            && (userPersonalInfo==null ? __obj__.getUserPersonalInfo()==null : userPersonalInfo.equals(__obj__.getUserPersonalInfo()))
            && (userSystemInfo==null ? __obj__.getUserSystemInfo()==null : userSystemInfo.equals(__obj__.getUserSystemInfo()))
            && weblogic.xml.schema.binding.RuntimeUtils.compareObjects(accounts, __obj__.getAccounts())


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (userPersonalInfo==null ? 0 : userPersonalInfo.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (userSystemInfo==null ? 0 : userSystemInfo.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (accounts==null ? 0 : weblogic.xml.schema.binding.RuntimeUtils.arrayHashCode(accounts)) ;
    

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

