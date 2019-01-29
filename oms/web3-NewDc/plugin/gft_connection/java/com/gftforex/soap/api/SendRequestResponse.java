head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.06.27.06.05.46;	author zhang-tengyu;	state dead;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	444;
commitid	8d84e081ce81473;
filename	SendRequestResponse.java;

1.1
date	2011.03.15.02.45.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	SendRequestResponse.java;


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

// original type: ['http://soap-api.gftforex.com']:sendRequestResponse


public  class SendRequestResponse 
  
  implements java.io.Serializable
{

  public SendRequestResponse() {}

  public SendRequestResponse(com.gftforex.soap.api.RejectedCommand[] p_rejectedCommand
,     double p_responseWaitTime) 
  {
     this.rejectedCommand = p_rejectedCommand;
     this.__isset_rejectedCommand = true;

     this.responseWaitTime = p_responseWaitTime;
     

  }




  
  private com.gftforex.soap.api.RejectedCommand[] rejectedCommand ;

  /**
  <br>  Derived from RejectedCommand.

  <br>  schema name = ['http://soap-api.gftforex.com']:RejectedCommand
  <br>  schema type = ['http://soap-api.gftforex.com']:sendRequestResponse__rejectedCommand__ArrayAnonType
  <br>  schema formQualified = true
  */
  public com.gftforex.soap.api.RejectedCommand[] getRejectedCommand() {
    return rejectedCommand;
  }

  public void setRejectedCommand(com.gftforex.soap.api.RejectedCommand[] v) {
    
    this.rejectedCommand = v;
    this.__isset_rejectedCommand = true;

  }

  private boolean __isset_rejectedCommand;
  public boolean _isSetRejectedCommand() {
    return this.__isset_rejectedCommand;
  }
  public void _unsetRejectedCommand() {
    this.__isset_rejectedCommand = false;
  }




  
  private double responseWaitTime ;

  /**
  <br>  Derived from ResponseWaitTime.

  <br>  schema name = ['http://soap-api.gftforex.com']:ResponseWaitTime
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:double
  <br>  schema formQualified = true
  */
  public double getResponseWaitTime() {
    return responseWaitTime;
  }

  public void setResponseWaitTime(double v) {
    
    this.responseWaitTime = v;
    
  }










  public java.lang.String toString() {
  return "SendRequestResponse" + "{"
             + " rejectedCommand=<" + weblogic.xml.schema.binding.RuntimeUtils.arrayToString( getRejectedCommand() ) + ">"
             + " responseWaitTime=<" + getResponseWaitTime() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof SendRequestResponse) {
      
      SendRequestResponse __obj__ =  (SendRequestResponse) __other__;


      return true
            && (responseWaitTime == __obj__.getResponseWaitTime())
            && weblogic.xml.schema.binding.RuntimeUtils.compareObjects(rejectedCommand, __obj__.getRejectedCommand())


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (rejectedCommand==null ? 0 : weblogic.xml.schema.binding.RuntimeUtils.arrayHashCode(rejectedCommand)) ;
    __hash__result__ = 37*__hash__result__ + (int)responseWaitTime ;
    

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

