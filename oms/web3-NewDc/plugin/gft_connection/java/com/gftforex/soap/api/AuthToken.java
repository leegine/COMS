head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.06.27.06.08.02;	author zhang-tengyu;	state dead;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	444;
commitid	8d84e081ce81473;
filename	AuthToken.java;

1.1
date	2011.03.15.02.43.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	AuthToken.java;


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

// original type: ['http://soap-api.gftforex.com']:AuthToken


public  class AuthToken 
  
  implements java.io.Serializable
{

  public AuthToken() {}

  public AuthToken(java.lang.String p_login
,     java.lang.String p_timestamp
,     java.lang.String p_algorithm
,     java.lang.String p_hash) 
  {
     this.login = p_login;
     
     this.timestamp = p_timestamp;
     
     this.algorithm = p_algorithm;
     this.__isset_algorithm = true;

     this.hash = p_hash;
     

  }




  
  private java.lang.String login ;

  /**
  <br>  Derived from Login.

  <br>  schema name = ['http://soap-api.gftforex.com']:Login
  <br>  schema type = ['http://soap-api.gftforex.com']:String32
  <br>  schema formQualified = true
  */
  public java.lang.String getLogin() {
    return login;
  }

  public void setLogin(java.lang.String v) {
    
    this.login = v;
    
  }





  
  private java.lang.String timestamp ;

  /**
  <br>  Derived from Timestamp.

  <br>  schema name = ['http://soap-api.gftforex.com']:Timestamp
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(java.lang.String v) {
    
    this.timestamp = v;
    
  }





  
  private java.lang.String algorithm ;

  /**
  <br>  Derived from Algorithm.

  <br>  schema name = ['http://soap-api.gftforex.com']:Algorithm
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getAlgorithm() {
    return algorithm;
  }

  public void setAlgorithm(java.lang.String v) {
    
    this.algorithm = v;
    this.__isset_algorithm = true;

  }

  private boolean __isset_algorithm;
  public boolean _isSetAlgorithm() {
    return this.__isset_algorithm;
  }
  public void _unsetAlgorithm() {
    this.__isset_algorithm = false;
  }




  
  private java.lang.String hash ;

  /**
  <br>  Derived from Hash.

  <br>  schema name = ['http://soap-api.gftforex.com']:Hash
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getHash() {
    return hash;
  }

  public void setHash(java.lang.String v) {
    
    this.hash = v;
    
  }










  public java.lang.String toString() {
  return "AuthToken" + "{"
             + " login=<" + getLogin() + ">"
             + " timestamp=<" + getTimestamp() + ">"
             + " algorithm=<" + getAlgorithm() + ">"
             + " hash=<" + getHash() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof AuthToken) {
      
      AuthToken __obj__ =  (AuthToken) __other__;


      return true
            && (login==null ? __obj__.getLogin()==null : login.equals(__obj__.getLogin()))
            && (timestamp==null ? __obj__.getTimestamp()==null : timestamp.equals(__obj__.getTimestamp()))
            && (algorithm==null ? __obj__.getAlgorithm()==null : algorithm.equals(__obj__.getAlgorithm()))
            && (hash==null ? __obj__.getHash()==null : hash.equals(__obj__.getHash()))


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (login==null ? 0 : login.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (timestamp==null ? 0 : timestamp.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (algorithm==null ? 0 : algorithm.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (hash==null ? 0 : hash.hashCode()) ;
    

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

