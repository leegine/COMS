head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.36.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	EntryReceipt.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 * This code was automatically generated at 13:33:46 on 2008/07/29
 * by weblogic.xml.schema.binding.internal.codegen.BeanImplGenerator -- do not edit.
 *
 * @@version WebLogic Temporary Patch for CR362079 Thu Feb 21 18:25:04 2008
 * @@author Copyright (c) 2008 by BEA Systems, Inc. All Rights Reserved.
 */

package jp.co.hitachi.www.TFX.WebService;

// original type: ['http://www.hitachi.co.jp/TFX/WebService']:EntryReceipt


public  class EntryReceipt 
  
  implements java.io.Serializable
{

  public EntryReceipt() {}

  public EntryReceipt(jp.co.hitachi.www.TFX.WebService.EntryReceiptIn p_in0) 
  {
     this.in0 = p_in0;
     

  }




  
  private jp.co.hitachi.www.TFX.WebService.EntryReceiptIn in0 ;

  /**
  <br>  Derived from in0.

  <br>  schema name = ['http://www.hitachi.co.jp/TFX/WebService']:in0
  <br>  schema type = ['http://www.hitachi.co.jp/TFX/WebService']:EntryReceiptIn
  <br>  schema formQualified = true
  */
  public jp.co.hitachi.www.TFX.WebService.EntryReceiptIn getIn0() {
    return in0;
  }

  public void setIn0(jp.co.hitachi.www.TFX.WebService.EntryReceiptIn v) {
    
    this.in0 = v;
    
  }










  public java.lang.String toString() {
  return "EntryReceipt" + "{"
             + " in0=<" + getIn0() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof EntryReceipt) {
      
      EntryReceipt __obj__ =  (EntryReceipt) __other__;


      return true
            && (in0==null ? __obj__.getIn0()==null : in0.equals(__obj__.getIn0()))


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (in0==null ? 0 : in0.hashCode()) ;
    

    return __hash__result__;
  }

}


@
