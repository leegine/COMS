head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.06.27.06.10.29;	author zhang-tengyu;	state dead;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	444;
commitid	8d84e081ce81473;
filename	ResultInfoCreateUser.java;

1.1
date	2011.03.15.02.43.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	ResultInfoCreateUser.java;


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

// original type: ['http://soap-api.gftforex.com']:ResultInfoCreateUser


public  class ResultInfoCreateUser 
  extends com.gftforex.soap.api.ResultInfoBase
  implements java.io.Serializable
{

  public ResultInfoCreateUser() {}

  public ResultInfoCreateUser(java.lang.String p_commandID
,     int p_statusCode
,     java.lang.String p_statusMessage
,     int p_majorStatusCode
,     java.lang.String p_majorStatusMessage
,     int p_minorStatusCode
,     java.lang.String p_minorStatusMessage
,     java.lang.String p_login
,     java.lang.String p_password
,     long[] p_accountIds) 
  {
    super(p_commandID,p_statusCode,p_statusMessage,p_majorStatusCode,p_majorStatusMessage,p_minorStatusCode,p_minorStatusMessage);
     this.login = p_login;
     
     this.password = p_password;
     
     this.accountIds = p_accountIds;
     

  }




  
  private java.lang.String login ;

  /**
  <br>  Derived from Login.

  <br>  schema name = ['http://soap-api.gftforex.com']:Login
  <br>  schema type = ['http://soap-api.gftforex.com']:String32
  <br>  schema formQualified = true
  */
  public java.lang.String getLogin() {
    return login;
  }

  public void setLogin(java.lang.String v) {
    
    this.login = v;
    
  }





  
  private java.lang.String password ;

  /**
  <br>  Derived from Password.

  <br>  schema name = ['http://soap-api.gftforex.com']:Password
  <br>  schema type = ['http://soap-api.gftforex.com']:String32
  <br>  schema formQualified = true
  */
  public java.lang.String getPassword() {
    return password;
  }

  public void setPassword(java.lang.String v) {
    
    this.password = v;
    
  }





  
  private long[] accountIds ;

  /**
  <br>  Derived from AccountIds.

  <br>  schema name = ['http://soap-api.gftforex.com']:AccountIds
  <br>  schema type = ['http://soap-api.gftforex.com']:ResultInfoCreateUser__accountIds__ArrayAnonType
  <br>  schema formQualified = true
  */
  public long[] getAccountIds() {
    return accountIds;
  }

  public void setAccountIds(long[] v) {
    
    this.accountIds = v;
    
  }










  public java.lang.String toString() {
  return "ResultInfoCreateUser" + "{"
             + " login=<" + getLogin() + ">"
             + " password=<" + getPassword() + ">"
             + " accountIds=<" + weblogic.xml.schema.binding.RuntimeUtils.arrayToString( getAccountIds() ) + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof ResultInfoCreateUser) {
      if (!super.equals(__other__)) return false;

      ResultInfoCreateUser __obj__ =  (ResultInfoCreateUser) __other__;


      return true
            && (login==null ? __obj__.getLogin()==null : login.equals(__obj__.getLogin()))
            && (password==null ? __obj__.getPassword()==null : password.equals(__obj__.getPassword()))
            && weblogic.xml.schema.binding.RuntimeUtils.compareObjects(accountIds, __obj__.getAccountIds())


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;
    __hash__result__ = 37*__hash__result__ + super.hashCode() ;

    __hash__result__ = 37*__hash__result__ + (login==null ? 0 : login.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (password==null ? 0 : password.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (accountIds==null ? 0 : weblogic.xml.schema.binding.RuntimeUtils.arrayHashCode(accountIds)) ;
    

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

