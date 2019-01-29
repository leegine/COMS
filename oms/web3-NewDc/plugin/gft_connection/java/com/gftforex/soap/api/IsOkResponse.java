head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.06.27.06.07.12;	author zhang-tengyu;	state dead;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	444;
commitid	8d84e081ce81473;
filename	IsOkResponse.java;

1.1
date	2011.03.15.02.41.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	IsOkResponse.java;


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

// original type: ['http://soap-api.gftforex.com']:isOkResponse


public  class IsOkResponse 
  
  implements java.io.Serializable
{

  public IsOkResponse() {}

  public IsOkResponse(boolean p_isOkResult) 
  {
     this.isOkResult = p_isOkResult;
     

  }




  
  private boolean isOkResult ;

  /**
  <br>  Derived from isOkResult.

  <br>  schema name = ['http://soap-api.gftforex.com']:isOkResult
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:boolean
  <br>  schema formQualified = true
  */
  public boolean getIsOkResult() {
    return isOkResult;
  }

  public void setIsOkResult(boolean v) {
    
    this.isOkResult = v;
    
  }










  public java.lang.String toString() {
  return "IsOkResponse" + "{"
             + " isOkResult=<" + getIsOkResult() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof IsOkResponse) {
      
      IsOkResponse __obj__ =  (IsOkResponse) __other__;


      return true
            && (isOkResult == __obj__.getIsOkResult())


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (isOkResult ? 0 : 1) ;
    

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

