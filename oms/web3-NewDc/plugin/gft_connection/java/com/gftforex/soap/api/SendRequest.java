head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.06.27.06.11.58;	author zhang-tengyu;	state dead;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	444;
commitid	8d84e081ce81473;
filename	SendRequest.java;

1.1
date	2011.03.15.02.44.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	SendRequest.java;


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

// original type: ['http://soap-api.gftforex.com']:sendRequest


public  class SendRequest 
  
  implements java.io.Serializable
{

  public SendRequest() {}

  public SendRequest(com.gftforex.soap.api.CommandBase[] p_commands
,     com.gftforex.soap.api.AuthToken p_authToken) 
  {
     this.commands = p_commands;
     
     this.authToken = p_authToken;
     

  }




  
  private com.gftforex.soap.api.CommandBase[] commands ;

  /**
  <br>  Derived from commands.

  <br>  schema name = ['http://soap-api.gftforex.com']:commands
  <br>  schema type = ['http://soap-api.gftforex.com']:sendRequest__commands__ArrayAnonType
  <br>  schema formQualified = true
  */
  public com.gftforex.soap.api.CommandBase[] getCommands() {
    return commands;
  }

  public void setCommands(com.gftforex.soap.api.CommandBase[] v) {
    
    this.commands = v;
    
  }





  
  private com.gftforex.soap.api.AuthToken authToken ;

  /**
  <br>  Derived from authToken.

  <br>  schema name = ['http://soap-api.gftforex.com']:authToken
  <br>  schema type = ['http://soap-api.gftforex.com']:AuthToken
  <br>  schema formQualified = true
  */
  public com.gftforex.soap.api.AuthToken getAuthToken() {
    return authToken;
  }

  public void setAuthToken(com.gftforex.soap.api.AuthToken v) {
    
    this.authToken = v;
    
  }










  public java.lang.String toString() {
  return "SendRequest" + "{"
             + " commands=<" + weblogic.xml.schema.binding.RuntimeUtils.arrayToString( getCommands() ) + ">"
             + " authToken=<" + getAuthToken() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof SendRequest) {
      
      SendRequest __obj__ =  (SendRequest) __other__;


      return true
            && weblogic.xml.schema.binding.RuntimeUtils.compareObjects(commands, __obj__.getCommands())
            && (authToken==null ? __obj__.getAuthToken()==null : authToken.equals(__obj__.getAuthToken()))


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (commands==null ? 0 : weblogic.xml.schema.binding.RuntimeUtils.arrayHashCode(commands)) ;
    __hash__result__ = 37*__hash__result__ + (authToken==null ? 0 : authToken.hashCode()) ;
    

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

