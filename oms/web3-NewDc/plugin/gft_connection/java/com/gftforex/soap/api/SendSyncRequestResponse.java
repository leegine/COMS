head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.06.27.06.04.09;	author zhang-tengyu;	state dead;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	444;
commitid	8d84e081ce81473;
filename	SendSyncRequestResponse.java;

1.1
date	2011.03.15.02.43.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	SendSyncRequestResponse.java;


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

// original type: ['http://soap-api.gftforex.com']:sendSyncRequestResponse


public  class SendSyncRequestResponse 
  
  implements java.io.Serializable
{

  public SendSyncRequestResponse() {}

  public SendSyncRequestResponse(com.gftforex.soap.api.RejectedCommand p_rejectedCommand
,     com.gftforex.soap.api.ResultInfoBase p_sendSyncRequestResult) 
  {
     this.rejectedCommand = p_rejectedCommand;
     this.__isset_rejectedCommand = true;

     this.sendSyncRequestResult = p_sendSyncRequestResult;
     this.__isset_sendSyncRequestResult = true;


  }




  
  private com.gftforex.soap.api.RejectedCommand rejectedCommand ;

  /**
  <br>  Derived from RejectedCommand.

  <br>  schema name = ['http://soap-api.gftforex.com']:RejectedCommand
  <br>  schema type = ['http://soap-api.gftforex.com']:RejectedCommand
  <br>  schema formQualified = true
  */
  public com.gftforex.soap.api.RejectedCommand getRejectedCommand() {
    return rejectedCommand;
  }

  public void setRejectedCommand(com.gftforex.soap.api.RejectedCommand v) {
    
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




  
  private com.gftforex.soap.api.ResultInfoBase sendSyncRequestResult ;

  /**
  <br>  Derived from sendSyncRequestResult.

  <br>  schema name = ['http://soap-api.gftforex.com']:sendSyncRequestResult
  <br>  schema type = ['http://soap-api.gftforex.com']:ResultInfoBase
  <br>  schema formQualified = true
  */
  public com.gftforex.soap.api.ResultInfoBase getSendSyncRequestResult() {
    return sendSyncRequestResult;
  }

  public void setSendSyncRequestResult(com.gftforex.soap.api.ResultInfoBase v) {
    
    this.sendSyncRequestResult = v;
    this.__isset_sendSyncRequestResult = true;

  }

  private boolean __isset_sendSyncRequestResult;
  public boolean _isSetSendSyncRequestResult() {
    return this.__isset_sendSyncRequestResult;
  }
  public void _unsetSendSyncRequestResult() {
    this.__isset_sendSyncRequestResult = false;
  }









  public java.lang.String toString() {
  return "SendSyncRequestResponse" + "{"
             + " rejectedCommand=<" + getRejectedCommand() + ">"
             + " sendSyncRequestResult=<" + getSendSyncRequestResult() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof SendSyncRequestResponse) {
      
      SendSyncRequestResponse __obj__ =  (SendSyncRequestResponse) __other__;


      return true
            && (rejectedCommand==null ? __obj__.getRejectedCommand()==null : rejectedCommand.equals(__obj__.getRejectedCommand()))
            && (sendSyncRequestResult==null ? __obj__.getSendSyncRequestResult()==null : sendSyncRequestResult.equals(__obj__.getSendSyncRequestResult()))


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (rejectedCommand==null ? 0 : rejectedCommand.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (sendSyncRequestResult==null ? 0 : sendSyncRequestResult.hashCode()) ;
    

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

