head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.06.27.06.10.23;	author zhang-tengyu;	state dead;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	444;
commitid	8d84e081ce81473;
filename	CommandAddAccount.java;

1.1
date	2011.03.15.02.39.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	CommandAddAccount.java;


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

// original type: ['http://soap-api.gftforex.com']:CommandAddAccount


public  class CommandAddAccount 
  extends com.gftforex.soap.api.CommandBase
  implements java.io.Serializable
{

  public CommandAddAccount() {}

  public CommandAddAccount(java.lang.String p_commandID
,     java.lang.String p_login
,     com.gftforex.soap.api.CreateAccountInfo[] p_account) 
  {
    super(p_commandID);
     this.login = p_login;
     
     this.account = p_account;
     

  }




  
  private java.lang.String login ;

  /**
  <br>  Derived from Login.

  <br>  schema name = ['http://soap-api.gftforex.com']:Login
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getLogin() {
    return login;
  }

  public void setLogin(java.lang.String v) {
    
    this.login = v;
    
  }





  
  private com.gftforex.soap.api.CreateAccountInfo[] account ;

  /**
  <br>  Derived from Account.

  <br>  schema name = ['http://soap-api.gftforex.com']:Account
  <br>  schema type = ['http://soap-api.gftforex.com']:CommandAddAccount__account__ArrayAnonType
  <br>  schema formQualified = true
  */
  public com.gftforex.soap.api.CreateAccountInfo[] getAccount() {
    return account;
  }

  public void setAccount(com.gftforex.soap.api.CreateAccountInfo[] v) {
    
    this.account = v;
    
  }










  public java.lang.String toString() {
  return "CommandAddAccount" + "{"
             + " login=<" + getLogin() + ">"
             + " account=<" + weblogic.xml.schema.binding.RuntimeUtils.arrayToString( getAccount() ) + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof CommandAddAccount) {
      if (!super.equals(__other__)) return false;

      CommandAddAccount __obj__ =  (CommandAddAccount) __other__;


      return true
            && (login==null ? __obj__.getLogin()==null : login.equals(__obj__.getLogin()))
            && weblogic.xml.schema.binding.RuntimeUtils.compareObjects(account, __obj__.getAccount())


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;
    __hash__result__ = 37*__hash__result__ + super.hashCode() ;

    __hash__result__ = 37*__hash__result__ + (login==null ? 0 : login.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (account==null ? 0 : weblogic.xml.schema.binding.RuntimeUtils.arrayHashCode(account)) ;
    

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

