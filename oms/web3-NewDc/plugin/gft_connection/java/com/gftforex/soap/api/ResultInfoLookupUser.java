head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.06.27.06.13.23;	author zhang-tengyu;	state dead;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	444;
commitid	8d84e081ce81473;
filename	ResultInfoLookupUser.java;

1.1
date	2011.03.15.02.44.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	ResultInfoLookupUser.java;


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

// original type: ['http://soap-api.gftforex.com']:ResultInfoLookupUser


public  class ResultInfoLookupUser 
  extends com.gftforex.soap.api.ResultInfoBase
  implements java.io.Serializable
{

  public ResultInfoLookupUser() {}

  public ResultInfoLookupUser(java.lang.String p_commandID
,     int p_statusCode
,     java.lang.String p_statusMessage
,     int p_majorStatusCode
,     java.lang.String p_majorStatusMessage
,     int p_minorStatusCode
,     java.lang.String p_minorStatusMessage
,     com.gftforex.soap.api.LookupUserInfo p_userInfo) 
  {
    super(p_commandID,p_statusCode,p_statusMessage,p_majorStatusCode,p_majorStatusMessage,p_minorStatusCode,p_minorStatusMessage);
     this.userInfo = p_userInfo;
     this.__isset_userInfo = true;


  }




  
  private com.gftforex.soap.api.LookupUserInfo userInfo ;

  /**
  <br>  Derived from UserInfo.

  <br>  schema name = ['http://soap-api.gftforex.com']:UserInfo
  <br>  schema type = ['http://soap-api.gftforex.com']:LookupUserInfo
  <br>  schema formQualified = true
  */
  public com.gftforex.soap.api.LookupUserInfo getUserInfo() {
    return userInfo;
  }

  public void setUserInfo(com.gftforex.soap.api.LookupUserInfo v) {
    
    this.userInfo = v;
    this.__isset_userInfo = true;

  }

  private boolean __isset_userInfo;
  public boolean _isSetUserInfo() {
    return this.__isset_userInfo;
  }
  public void _unsetUserInfo() {
    this.__isset_userInfo = false;
  }









  public java.lang.String toString() {
  return "ResultInfoLookupUser" + "{"
             + " userInfo=<" + getUserInfo() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof ResultInfoLookupUser) {
      if (!super.equals(__other__)) return false;

      ResultInfoLookupUser __obj__ =  (ResultInfoLookupUser) __other__;


      return true
            && (userInfo==null ? __obj__.getUserInfo()==null : userInfo.equals(__obj__.getUserInfo()))


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;
    __hash__result__ = 37*__hash__result__ + super.hashCode() ;

    __hash__result__ = 37*__hash__result__ + (userInfo==null ? 0 : userInfo.hashCode()) ;
    

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

