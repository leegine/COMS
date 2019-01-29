head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.06.27.06.13.05;	author zhang-tengyu;	state dead;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	444;
commitid	8d84e081ce81473;
filename	CommandCreateUser.java;

1.1
date	2011.03.15.02.45.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	CommandCreateUser.java;


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

// original type: ['http://soap-api.gftforex.com']:CommandCreateUser


public  class CommandCreateUser 
  extends com.gftforex.soap.api.CommandBase
  implements java.io.Serializable
{

  public CommandCreateUser() {}

  public CommandCreateUser(java.lang.String p_commandID
,     com.gftforex.soap.api.CreateUserInfo p_userInfo) 
  {
    super(p_commandID);
     this.userInfo = p_userInfo;
     this.__isset_userInfo = true;


  }




  
  private com.gftforex.soap.api.CreateUserInfo userInfo ;

  /**
  <br>  Derived from UserInfo.

  <br>  schema name = ['http://soap-api.gftforex.com']:UserInfo
  <br>  schema type = ['http://soap-api.gftforex.com']:CreateUserInfo
  <br>  schema formQualified = true
  */
  public com.gftforex.soap.api.CreateUserInfo getUserInfo() {
    return userInfo;
  }

  public void setUserInfo(com.gftforex.soap.api.CreateUserInfo v) {
    
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
  return "CommandCreateUser" + "{"
             + " userInfo=<" + getUserInfo() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof CommandCreateUser) {
      if (!super.equals(__other__)) return false;

      CommandCreateUser __obj__ =  (CommandCreateUser) __other__;


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

