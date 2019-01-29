head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.06.27.06.06.21;	author zhang-tengyu;	state dead;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	444;
commitid	8d84e081ce81473;
filename	ResultInfoBase.java;

1.1
date	2011.03.15.02.41.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	ResultInfoBase.java;


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

// original type: ['http://soap-api.gftforex.com']:ResultInfoBase


public  class ResultInfoBase 
  
  implements java.io.Serializable
{

  public ResultInfoBase() {}

  public ResultInfoBase(java.lang.String p_commandID
,     int p_statusCode
,     java.lang.String p_statusMessage
,     int p_majorStatusCode
,     java.lang.String p_majorStatusMessage
,     int p_minorStatusCode
,     java.lang.String p_minorStatusMessage) 
  {
     this.commandID = p_commandID;
     
     this.statusCode = p_statusCode;
     
     this.statusMessage = p_statusMessage;
     this.__isset_statusMessage = true;

     this.majorStatusCode = p_majorStatusCode;
     
     this.majorStatusMessage = p_majorStatusMessage;
     
     this.minorStatusCode = p_minorStatusCode;
     this.__isset_minorStatusCode = true;

     this.minorStatusMessage = p_minorStatusMessage;
     this.__isset_minorStatusMessage = true;


  }




  
  private java.lang.String commandID ;

  /**
  <br>  Derived from CommandID.

  <br>  schema name = ['http://soap-api.gftforex.com']:CommandID
  <br>  schema type = ['http://soap-api.gftforex.com']:CommandID
  <br>  schema formQualified = true
  */
  public java.lang.String getCommandID() {
    return commandID;
  }

  public void setCommandID(java.lang.String v) {
    
    this.commandID = v;
    
  }





  
  private int statusCode ;

  /**
  <br>  Derived from StatusCode.

  <br>  schema name = ['http://soap-api.gftforex.com']:StatusCode
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:unsignedShort
  <br>  schema formQualified = true
  */
  public int getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(int v) {
    
    this.statusCode = v;
    
  }





  
  private java.lang.String statusMessage ;

  /**
  <br>  Derived from StatusMessage.

  <br>  schema name = ['http://soap-api.gftforex.com']:StatusMessage
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getStatusMessage() {
    return statusMessage;
  }

  public void setStatusMessage(java.lang.String v) {
    
    this.statusMessage = v;
    this.__isset_statusMessage = true;

  }

  private boolean __isset_statusMessage;
  public boolean _isSetStatusMessage() {
    return this.__isset_statusMessage;
  }
  public void _unsetStatusMessage() {
    this.__isset_statusMessage = false;
  }




  
  private int majorStatusCode ;

  /**
  <br>  Derived from MajorStatusCode.

  <br>  schema name = ['http://soap-api.gftforex.com']:MajorStatusCode
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:unsignedShort
  <br>  schema formQualified = true
  */
  public int getMajorStatusCode() {
    return majorStatusCode;
  }

  public void setMajorStatusCode(int v) {
    
    this.majorStatusCode = v;
    
  }





  
  private java.lang.String majorStatusMessage ;

  /**
  <br>  Derived from MajorStatusMessage.

  <br>  schema name = ['http://soap-api.gftforex.com']:MajorStatusMessage
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getMajorStatusMessage() {
    return majorStatusMessage;
  }

  public void setMajorStatusMessage(java.lang.String v) {
    
    this.majorStatusMessage = v;
    
  }





  
  private int minorStatusCode ;

  /**
  <br>  Derived from MinorStatusCode.

  <br>  schema name = ['http://soap-api.gftforex.com']:MinorStatusCode
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:unsignedShort
  <br>  schema formQualified = true
  */
  public int getMinorStatusCode() {
    return minorStatusCode;
  }

  public void setMinorStatusCode(int v) {
    
    this.minorStatusCode = v;
    this.__isset_minorStatusCode = true;

  }

  private boolean __isset_minorStatusCode;
  public boolean _isSetMinorStatusCode() {
    return this.__isset_minorStatusCode;
  }
  public void _unsetMinorStatusCode() {
    this.__isset_minorStatusCode = false;
  }




  
  private java.lang.String minorStatusMessage ;

  /**
  <br>  Derived from MinorStatusMessage.

  <br>  schema name = ['http://soap-api.gftforex.com']:MinorStatusMessage
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getMinorStatusMessage() {
    return minorStatusMessage;
  }

  public void setMinorStatusMessage(java.lang.String v) {
    
    this.minorStatusMessage = v;
    this.__isset_minorStatusMessage = true;

  }

  private boolean __isset_minorStatusMessage;
  public boolean _isSetMinorStatusMessage() {
    return this.__isset_minorStatusMessage;
  }
  public void _unsetMinorStatusMessage() {
    this.__isset_minorStatusMessage = false;
  }









  public java.lang.String toString() {
  return "ResultInfoBase" + "{"
             + " commandID=<" + getCommandID() + ">"
             + " statusCode=<" + getStatusCode() + ">"
             + " statusMessage=<" + getStatusMessage() + ">"
             + " majorStatusCode=<" + getMajorStatusCode() + ">"
             + " majorStatusMessage=<" + getMajorStatusMessage() + ">"
             + " minorStatusCode=<" + getMinorStatusCode() + ">"
             + " minorStatusMessage=<" + getMinorStatusMessage() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof ResultInfoBase) {
      
      ResultInfoBase __obj__ =  (ResultInfoBase) __other__;


      return true
            && (statusCode == __obj__.getStatusCode())
            && (majorStatusCode == __obj__.getMajorStatusCode())
            && (minorStatusCode == __obj__.getMinorStatusCode())
            && (commandID==null ? __obj__.getCommandID()==null : commandID.equals(__obj__.getCommandID()))
            && (statusMessage==null ? __obj__.getStatusMessage()==null : statusMessage.equals(__obj__.getStatusMessage()))
            && (majorStatusMessage==null ? __obj__.getMajorStatusMessage()==null : majorStatusMessage.equals(__obj__.getMajorStatusMessage()))
            && (minorStatusMessage==null ? __obj__.getMinorStatusMessage()==null : minorStatusMessage.equals(__obj__.getMinorStatusMessage()))


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (commandID==null ? 0 : commandID.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + statusCode ;
    __hash__result__ = 37*__hash__result__ + (statusMessage==null ? 0 : statusMessage.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + majorStatusCode ;
    __hash__result__ = 37*__hash__result__ + (majorStatusMessage==null ? 0 : majorStatusMessage.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + minorStatusCode ;
    __hash__result__ = 37*__hash__result__ + (minorStatusMessage==null ? 0 : minorStatusMessage.hashCode()) ;
    

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

