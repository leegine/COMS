head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.06.27.06.11.45;	author zhang-tengyu;	state dead;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	444;
commitid	8d84e081ce81473;
filename	GetResponseByCommandId.java;

1.1
date	2011.03.15.02.43.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	GetResponseByCommandId.java;


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

// original type: ['http://soap-api.gftforex.com']:getResponseByCommandId


public  class GetResponseByCommandId 
  
  implements java.io.Serializable
{

  public GetResponseByCommandId() {}

  public GetResponseByCommandId(java.lang.String p_commandId
,     com.gftforex.soap.api.AuthToken p_authToken) 
  {
     this.commandId = p_commandId;
     
     this.authToken = p_authToken;
     

  }




  
  private java.lang.String commandId ;

  /**
  <br>  Derived from commandId.

  <br>  schema name = ['http://soap-api.gftforex.com']:commandId
  <br>  schema type = ['http://soap-api.gftforex.com']:CommandID
  <br>  schema formQualified = true
  */
  public java.lang.String getCommandId() {
    return commandId;
  }

  public void setCommandId(java.lang.String v) {
    
    this.commandId = v;
    
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
  return "GetResponseByCommandId" + "{"
             + " commandId=<" + getCommandId() + ">"
             + " authToken=<" + getAuthToken() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof GetResponseByCommandId) {
      
      GetResponseByCommandId __obj__ =  (GetResponseByCommandId) __other__;


      return true
            && (commandId==null ? __obj__.getCommandId()==null : commandId.equals(__obj__.getCommandId()))
            && (authToken==null ? __obj__.getAuthToken()==null : authToken.equals(__obj__.getAuthToken()))


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (commandId==null ? 0 : commandId.hashCode()) ;
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

