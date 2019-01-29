head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.06.27.06.07.57;	author zhang-tengyu;	state dead;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	444;
commitid	8d84e081ce81473;
filename	RejectedCommand.java;

1.1
date	2011.03.15.02.44.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	RejectedCommand.java;


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

// original type: ['http://soap-api.gftforex.com']:RejectedCommand


public  class RejectedCommand 
  
  implements java.io.Serializable
{

  public RejectedCommand() {}

  public RejectedCommand(java.lang.String p_rejectedCommandId
,     int p_errorCode
,     java.lang.String p_errorMessage
,     int p_majorErrorCode
,     java.lang.String p_majorErrorMessage
,     int p_minorErrorCode
,     java.lang.String p_minorErrorMessage) 
  {
     this.rejectedCommandId = p_rejectedCommandId;
     
     this.errorCode = p_errorCode;
     
     this.errorMessage = p_errorMessage;
     
     this.majorErrorCode = p_majorErrorCode;
     
     this.majorErrorMessage = p_majorErrorMessage;
     
     this.minorErrorCode = p_minorErrorCode;
     this.__isset_minorErrorCode = true;

     this.minorErrorMessage = p_minorErrorMessage;
     this.__isset_minorErrorMessage = true;


  }




  
  private java.lang.String rejectedCommandId ;

  /**
  <br>  Derived from RejectedCommandId.

  <br>  schema name = ['http://soap-api.gftforex.com']:RejectedCommandId
  <br>  schema type = ['http://soap-api.gftforex.com']:CommandID
  <br>  schema formQualified = true
  */
  public java.lang.String getRejectedCommandId() {
    return rejectedCommandId;
  }

  public void setRejectedCommandId(java.lang.String v) {
    
    this.rejectedCommandId = v;
    
  }





  
  private int errorCode ;

  /**
  <br>  Derived from ErrorCode.

  <br>  schema name = ['http://soap-api.gftforex.com']:ErrorCode
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:unsignedShort
  <br>  schema formQualified = true
  */
  public int getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(int v) {
    
    this.errorCode = v;
    
  }





  
  private java.lang.String errorMessage ;

  /**
  <br>  Derived from ErrorMessage.

  <br>  schema name = ['http://soap-api.gftforex.com']:ErrorMessage
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(java.lang.String v) {
    
    this.errorMessage = v;
    
  }





  
  private int majorErrorCode ;

  /**
  <br>  Derived from MajorErrorCode.

  <br>  schema name = ['http://soap-api.gftforex.com']:MajorErrorCode
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:unsignedShort
  <br>  schema formQualified = true
  */
  public int getMajorErrorCode() {
    return majorErrorCode;
  }

  public void setMajorErrorCode(int v) {
    
    this.majorErrorCode = v;
    
  }





  
  private java.lang.String majorErrorMessage ;

  /**
  <br>  Derived from MajorErrorMessage.

  <br>  schema name = ['http://soap-api.gftforex.com']:MajorErrorMessage
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getMajorErrorMessage() {
    return majorErrorMessage;
  }

  public void setMajorErrorMessage(java.lang.String v) {
    
    this.majorErrorMessage = v;
    
  }





  
  private int minorErrorCode ;

  /**
  <br>  Derived from MinorErrorCode.

  <br>  schema name = ['http://soap-api.gftforex.com']:MinorErrorCode
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:unsignedShort
  <br>  schema formQualified = true
  */
  public int getMinorErrorCode() {
    return minorErrorCode;
  }

  public void setMinorErrorCode(int v) {
    
    this.minorErrorCode = v;
    this.__isset_minorErrorCode = true;

  }

  private boolean __isset_minorErrorCode;
  public boolean _isSetMinorErrorCode() {
    return this.__isset_minorErrorCode;
  }
  public void _unsetMinorErrorCode() {
    this.__isset_minorErrorCode = false;
  }




  
  private java.lang.String minorErrorMessage ;

  /**
  <br>  Derived from MinorErrorMessage.

  <br>  schema name = ['http://soap-api.gftforex.com']:MinorErrorMessage
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getMinorErrorMessage() {
    return minorErrorMessage;
  }

  public void setMinorErrorMessage(java.lang.String v) {
    
    this.minorErrorMessage = v;
    this.__isset_minorErrorMessage = true;

  }

  private boolean __isset_minorErrorMessage;
  public boolean _isSetMinorErrorMessage() {
    return this.__isset_minorErrorMessage;
  }
  public void _unsetMinorErrorMessage() {
    this.__isset_minorErrorMessage = false;
  }









  public java.lang.String toString() {
  return "RejectedCommand" + "{"
             + " rejectedCommandId=<" + getRejectedCommandId() + ">"
             + " errorCode=<" + getErrorCode() + ">"
             + " errorMessage=<" + getErrorMessage() + ">"
             + " majorErrorCode=<" + getMajorErrorCode() + ">"
             + " majorErrorMessage=<" + getMajorErrorMessage() + ">"
             + " minorErrorCode=<" + getMinorErrorCode() + ">"
             + " minorErrorMessage=<" + getMinorErrorMessage() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof RejectedCommand) {
      
      RejectedCommand __obj__ =  (RejectedCommand) __other__;


      return true
            && (errorCode == __obj__.getErrorCode())
            && (majorErrorCode == __obj__.getMajorErrorCode())
            && (minorErrorCode == __obj__.getMinorErrorCode())
            && (rejectedCommandId==null ? __obj__.getRejectedCommandId()==null : rejectedCommandId.equals(__obj__.getRejectedCommandId()))
            && (errorMessage==null ? __obj__.getErrorMessage()==null : errorMessage.equals(__obj__.getErrorMessage()))
            && (majorErrorMessage==null ? __obj__.getMajorErrorMessage()==null : majorErrorMessage.equals(__obj__.getMajorErrorMessage()))
            && (minorErrorMessage==null ? __obj__.getMinorErrorMessage()==null : minorErrorMessage.equals(__obj__.getMinorErrorMessage()))


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (rejectedCommandId==null ? 0 : rejectedCommandId.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + errorCode ;
    __hash__result__ = 37*__hash__result__ + (errorMessage==null ? 0 : errorMessage.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + majorErrorCode ;
    __hash__result__ = 37*__hash__result__ + (majorErrorMessage==null ? 0 : majorErrorMessage.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + minorErrorCode ;
    __hash__result__ = 37*__hash__result__ + (minorErrorMessage==null ? 0 : minorErrorMessage.hashCode()) ;
    

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

