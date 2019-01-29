head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.06.27.06.07.31;	author zhang-tengyu;	state dead;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	444;
commitid	8d84e081ce81473;
filename	GetResponseByCommandIdResponse.java;

1.1
date	2011.03.15.02.44.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	GetResponseByCommandIdResponse.java;


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

// original type: ['http://soap-api.gftforex.com']:getResponseByCommandIdResponse


public  class GetResponseByCommandIdResponse 
  
  implements java.io.Serializable
{

  public GetResponseByCommandIdResponse() {}

  public GetResponseByCommandIdResponse(com.gftforex.soap.api.ResultInfoBase p_getResponseByCommandIdResult) 
  {
     this.getResponseByCommandIdResult = p_getResponseByCommandIdResult;
     this.__isset_getResponseByCommandIdResult = true;


  }




  
  private com.gftforex.soap.api.ResultInfoBase getResponseByCommandIdResult ;

  /**
  <br>  Derived from getResponseByCommandIdResult.

  <br>  schema name = ['http://soap-api.gftforex.com']:getResponseByCommandIdResult
  <br>  schema type = ['http://soap-api.gftforex.com']:ResultInfoBase
  <br>  schema formQualified = true
  */
  public com.gftforex.soap.api.ResultInfoBase getGetResponseByCommandIdResult() {
    return getResponseByCommandIdResult;
  }

  public void setGetResponseByCommandIdResult(com.gftforex.soap.api.ResultInfoBase v) {
    
    this.getResponseByCommandIdResult = v;
    this.__isset_getResponseByCommandIdResult = true;

  }

  private boolean __isset_getResponseByCommandIdResult;
  public boolean _isSetGetResponseByCommandIdResult() {
    return this.__isset_getResponseByCommandIdResult;
  }
  public void _unsetGetResponseByCommandIdResult() {
    this.__isset_getResponseByCommandIdResult = false;
  }









  public java.lang.String toString() {
  return "GetResponseByCommandIdResponse" + "{"
             + " getResponseByCommandIdResult=<" + getGetResponseByCommandIdResult() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof GetResponseByCommandIdResponse) {
      
      GetResponseByCommandIdResponse __obj__ =  (GetResponseByCommandIdResponse) __other__;


      return true
            && (getResponseByCommandIdResult==null ? __obj__.getGetResponseByCommandIdResult()==null : getResponseByCommandIdResult.equals(__obj__.getGetResponseByCommandIdResult()))


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (getResponseByCommandIdResult==null ? 0 : getResponseByCommandIdResult.hashCode()) ;
    

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

