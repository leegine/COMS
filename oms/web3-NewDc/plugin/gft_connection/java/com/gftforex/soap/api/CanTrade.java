head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.06.27.06.12.53;	author zhang-tengyu;	state dead;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	444;
commitid	8d84e081ce81473;
filename	CanTrade.java;

1.1
date	2011.03.15.02.41.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	CanTrade.java;


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

// original type: ['http://soap-api.gftforex.com']:CanTrade


public  class CanTrade 
  
  implements java.io.Serializable
{

  public CanTrade() {}

  public CanTrade(boolean p_fx
,     boolean p_fwd
,     boolean p_cfd
,     boolean p_futures
,     boolean p_sb) 
  {
     this.fx = p_fx;
     this.__isset_fx = true;

     this.fwd = p_fwd;
     this.__isset_fwd = true;

     this.cfd = p_cfd;
     this.__isset_cfd = true;

     this.futures = p_futures;
     this.__isset_futures = true;

     this.sb = p_sb;
     this.__isset_sb = true;


  }




  
  private boolean fx ;

  /**
  <br>  Derived from fx.

  <br>  schema name = ['http://soap-api.gftforex.com']:fx
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:boolean
  <br>  schema formQualified = true
  */
  public boolean getFx() {
    return fx;
  }

  public void setFx(boolean v) {
    
    this.fx = v;
    this.__isset_fx = true;

  }

  private boolean __isset_fx;
  public boolean _isSetFx() {
    return this.__isset_fx;
  }
  public void _unsetFx() {
    this.__isset_fx = false;
  }




  
  private boolean fwd ;

  /**
  <br>  Derived from fwd.

  <br>  schema name = ['http://soap-api.gftforex.com']:fwd
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:boolean
  <br>  schema formQualified = true
  */
  public boolean getFwd() {
    return fwd;
  }

  public void setFwd(boolean v) {
    
    this.fwd = v;
    this.__isset_fwd = true;

  }

  private boolean __isset_fwd;
  public boolean _isSetFwd() {
    return this.__isset_fwd;
  }
  public void _unsetFwd() {
    this.__isset_fwd = false;
  }




  
  private boolean cfd ;

  /**
  <br>  Derived from cfd.

  <br>  schema name = ['http://soap-api.gftforex.com']:cfd
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:boolean
  <br>  schema formQualified = true
  */
  public boolean getCfd() {
    return cfd;
  }

  public void setCfd(boolean v) {
    
    this.cfd = v;
    this.__isset_cfd = true;

  }

  private boolean __isset_cfd;
  public boolean _isSetCfd() {
    return this.__isset_cfd;
  }
  public void _unsetCfd() {
    this.__isset_cfd = false;
  }




  
  private boolean futures ;

  /**
  <br>  Derived from futures.

  <br>  schema name = ['http://soap-api.gftforex.com']:futures
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:boolean
  <br>  schema formQualified = true
  */
  public boolean getFutures() {
    return futures;
  }

  public void setFutures(boolean v) {
    
    this.futures = v;
    this.__isset_futures = true;

  }

  private boolean __isset_futures;
  public boolean _isSetFutures() {
    return this.__isset_futures;
  }
  public void _unsetFutures() {
    this.__isset_futures = false;
  }




  
  private boolean sb ;

  /**
  <br>  Derived from sb.

  <br>  schema name = ['http://soap-api.gftforex.com']:sb
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:boolean
  <br>  schema formQualified = true
  */
  public boolean getSb() {
    return sb;
  }

  public void setSb(boolean v) {
    
    this.sb = v;
    this.__isset_sb = true;

  }

  private boolean __isset_sb;
  public boolean _isSetSb() {
    return this.__isset_sb;
  }
  public void _unsetSb() {
    this.__isset_sb = false;
  }









  public java.lang.String toString() {
  return "CanTrade" + "{"
             + " fx=<" + getFx() + ">"
             + " fwd=<" + getFwd() + ">"
             + " cfd=<" + getCfd() + ">"
             + " futures=<" + getFutures() + ">"
             + " sb=<" + getSb() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof CanTrade) {
      
      CanTrade __obj__ =  (CanTrade) __other__;


      return true
            && (fx == __obj__.getFx())
            && (fwd == __obj__.getFwd())
            && (cfd == __obj__.getCfd())
            && (futures == __obj__.getFutures())
            && (sb == __obj__.getSb())


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (fx ? 0 : 1) ;
    __hash__result__ = 37*__hash__result__ + (fwd ? 0 : 1) ;
    __hash__result__ = 37*__hash__result__ + (cfd ? 0 : 1) ;
    __hash__result__ = 37*__hash__result__ + (futures ? 0 : 1) ;
    __hash__result__ = 37*__hash__result__ + (sb ? 0 : 1) ;
    

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

