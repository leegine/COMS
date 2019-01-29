head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.38.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	EntryReceiptOut.java;


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

// original type: ['http://www.hitachi.co.jp/TFX/WebService']:EntryReceiptOut


public  class EntryReceiptOut 
  
  implements java.io.Serializable
{

  public EntryReceiptOut() {}

  public EntryReceiptOut(int p_returnCd) 
  {
     this.returnCd = p_returnCd;
     

  }




  
  private int returnCd ;

  /**
  <br>  Derived from returnCd.

  <br>  schema name = ['http://www.hitachi.co.jp/TFX/WebService']:returnCd
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:int
  <br>  schema formQualified = true
  */
  public int getReturnCd() {
    return returnCd;
  }

  public void setReturnCd(int v) {
    
    this.returnCd = v;
    
  }










  public java.lang.String toString() {
  return "EntryReceiptOut" + "{"
             + " returnCd=<" + getReturnCd() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof EntryReceiptOut) {
      
      EntryReceiptOut __obj__ =  (EntryReceiptOut) __other__;


      return true
            && (returnCd == __obj__.getReturnCd())


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + returnCd ;
    

    return __hash__result__;
  }

}


@
