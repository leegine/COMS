head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.06.27.06.06.16;	author zhang-tengyu;	state dead;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	444;
commitid	8d84e081ce81473;
filename	ResultInfoWithdraw.java;

1.1
date	2011.03.15.02.43.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	ResultInfoWithdraw.java;


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

// original type: ['http://soap-api.gftforex.com']:ResultInfoWithdraw


public  class ResultInfoWithdraw 
  extends com.gftforex.soap.api.ResultInfoBase
  implements java.io.Serializable
{

  public ResultInfoWithdraw() {}

  public ResultInfoWithdraw(java.lang.String p_commandID
,     int p_statusCode
,     java.lang.String p_statusMessage
,     int p_majorStatusCode
,     java.lang.String p_majorStatusMessage
,     int p_minorStatusCode
,     java.lang.String p_minorStatusMessage) 
  {
    super(p_commandID,p_statusCode,p_statusMessage,p_majorStatusCode,p_majorStatusMessage,p_minorStatusCode,p_minorStatusMessage);

  }









  public java.lang.String toString() {
  return "ResultInfoWithdraw" + "{"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof ResultInfoWithdraw) {
      if (!super.equals(__other__)) return false;

      ResultInfoWithdraw __obj__ =  (ResultInfoWithdraw) __other__;


      return true


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;
    __hash__result__ = 37*__hash__result__ + super.hashCode() ;

    

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

