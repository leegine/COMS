head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.06.27.06.06.07;	author zhang-tengyu;	state dead;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	444;
commitid	8d84e081ce81473;
filename	CommandLookupUser.java;

1.1
date	2011.03.15.02.46.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	CommandLookupUser.java;


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

// original type: ['http://soap-api.gftforex.com']:CommandLookupUser


public  class CommandLookupUser 
  extends com.gftforex.soap.api.CommandBase
  implements java.io.Serializable
{

  public CommandLookupUser() {}

  public CommandLookupUser(java.lang.String p_commandID
,     java.lang.String p_login) 
  {
    super(p_commandID);
     this.login = p_login;
     

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










  public java.lang.String toString() {
  return "CommandLookupUser" + "{"
             + " login=<" + getLogin() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof CommandLookupUser) {
      if (!super.equals(__other__)) return false;

      CommandLookupUser __obj__ =  (CommandLookupUser) __other__;


      return true
            && (login==null ? __obj__.getLogin()==null : login.equals(__obj__.getLogin()))


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;
    __hash__result__ = 37*__hash__result__ + super.hashCode() ;

    __hash__result__ = 37*__hash__result__ + (login==null ? 0 : login.hashCode()) ;
    

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

