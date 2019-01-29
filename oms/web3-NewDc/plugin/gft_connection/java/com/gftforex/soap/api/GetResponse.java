head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.06.27.06.12.34;	author zhang-tengyu;	state dead;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	444;
commitid	8d84e081ce81473;
filename	GetResponse.java;

1.1
date	2011.03.15.02.40.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	GetResponse.java;


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

// original type: ['http://soap-api.gftforex.com']:getResponse


public  class GetResponse 
  
  implements java.io.Serializable
{

  public GetResponse() {}

  public GetResponse(long p_maxCommands
,     com.gftforex.soap.api.AuthToken p_authToken) 
  {
     this.maxCommands = p_maxCommands;
     this.__isset_maxCommands = true;

     this.authToken = p_authToken;
     

  }




  
  private long maxCommands ;

  /**
  <br>  Derived from maxCommands.

  <br>  schema name = ['http://soap-api.gftforex.com']:maxCommands
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:unsignedInt
  <br>  schema formQualified = true
  */
  public long getMaxCommands() {
    return maxCommands;
  }

  public void setMaxCommands(long v) {
    
    this.maxCommands = v;
    this.__isset_maxCommands = true;

  }

  private boolean __isset_maxCommands;
  public boolean _isSetMaxCommands() {
    return this.__isset_maxCommands;
  }
  public void _unsetMaxCommands() {
    this.__isset_maxCommands = false;
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
  return "GetResponse" + "{"
             + " maxCommands=<" + getMaxCommands() + ">"
             + " authToken=<" + getAuthToken() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof GetResponse) {
      
      GetResponse __obj__ =  (GetResponse) __other__;


      return true
            && (maxCommands == __obj__.getMaxCommands())
            && (authToken==null ? __obj__.getAuthToken()==null : authToken.equals(__obj__.getAuthToken()))


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (int)maxCommands ;
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

