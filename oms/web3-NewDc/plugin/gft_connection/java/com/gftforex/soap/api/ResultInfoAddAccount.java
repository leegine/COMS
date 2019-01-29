head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.06.27.06.03.43;	author zhang-tengyu;	state dead;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	444;
commitid	8d84e081ce81473;
filename	ResultInfoAddAccount.java;

1.1
date	2011.03.15.02.46.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	ResultInfoAddAccount.java;


desc
@@


1.2
log
@*** empty log message ***
@
text
@/**
 * This code was automatically generated at 9:57:20 on 2008/07/16
 * by weblogic.xml.schema.binding.internal.codegen.BeanImplGenerator -- do not edit.
 *
 * @@version WebLogic Temporary Patch for CR362079 Thu Feb 21 18:25:04 2008
 * @@author Copyright (c) 2008 by BEA Systems, Inc. All Rights Reserved.
 */

package com.gftforex.soap.api;

// original type: ['http://soap-api.gftforex.com']:ResultInfoAddAccount


public  class ResultInfoAddAccount 
  extends com.gftforex.soap.api.ResultInfoBase
  implements java.io.Serializable
{

  public ResultInfoAddAccount() {}

  public ResultInfoAddAccount(java.lang.String p_commandID
,     int p_statusCode
,     java.lang.String p_statusMessage
,     int p_majorStatusCode
,     java.lang.String p_majorStatusMessage
,     int p_minorStatusCode
,     java.lang.String p_minorStatusMessage
,     long[] p_accountId) 
  {
    super(p_commandID,p_statusCode,p_statusMessage,p_majorStatusCode,p_majorStatusMessage,p_minorStatusCode,p_minorStatusMessage);
     this.accountId = p_accountId;
     

  }




  
  private long[] accountId ;

  /**
  <br>  Derived from AccountId.

  <br>  schema name = ['http://soap-api.gftforex.com']:AccountId
  <br>  schema type = ['http://soap-api.gftforex.com']:ResultInfoAddAccount__accountId__ArrayAnonType
  <br>  schema formQualified = true
  */
  public long[] getAccountId() {
    return accountId;
  }

  public void setAccountId(long[] v) {
    
    this.accountId = v;
    
  }










  public java.lang.String toString() {
  return "ResultInfoAddAccount" + "{"
             + " accountId=<" + weblogic.xml.schema.binding.RuntimeUtils.arrayToString( getAccountId() ) + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof ResultInfoAddAccount) {
      if (!super.equals(__other__)) return false;

      ResultInfoAddAccount __obj__ =  (ResultInfoAddAccount) __other__;


      return true
              && weblogic.xml.schema.binding.RuntimeUtils.compareObjects(accountId, __obj__.getAccountId())

      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;
    __hash__result__ = 37*__hash__result__ + super.hashCode() ;

    __hash__result__ = 37*__hash__result__ + (accountId==null ? 0 : weblogic.xml.schema.binding.RuntimeUtils.arrayHashCode(accountId)) ;
    

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

