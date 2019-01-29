head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.38.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	EntryReceiptResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 * This code was automatically generated at 13:33:45 on 2008/07/29
 * by weblogic.xml.schema.binding.internal.codegen.BeanImplGenerator -- do not edit.
 *
 * @@version WebLogic Temporary Patch for CR362079 Thu Feb 21 18:25:04 2008
 * @@author Copyright (c) 2008 by BEA Systems, Inc. All Rights Reserved.
 */

package jp.co.hitachi.www.TFX.WebService;

// original type: ['http://www.hitachi.co.jp/TFX/WebService']:EntryReceiptResponse


public  class EntryReceiptResponse 
  
  implements java.io.Serializable
{

  public EntryReceiptResponse() {}

  public EntryReceiptResponse(jp.co.hitachi.www.TFX.WebService.EntryReceiptOut p_entryReceiptReturn) 
  {
     this.entryReceiptReturn = p_entryReceiptReturn;
     

  }




  
  private jp.co.hitachi.www.TFX.WebService.EntryReceiptOut entryReceiptReturn ;

  /**
  <br>  Derived from EntryReceiptReturn.

  <br>  schema name = ['http://www.hitachi.co.jp/TFX/WebService']:EntryReceiptReturn
  <br>  schema type = ['http://www.hitachi.co.jp/TFX/WebService']:EntryReceiptOut
  <br>  schema formQualified = true
  */
  public jp.co.hitachi.www.TFX.WebService.EntryReceiptOut getEntryReceiptReturn() {
    return entryReceiptReturn;
  }

  public void setEntryReceiptReturn(jp.co.hitachi.www.TFX.WebService.EntryReceiptOut v) {
    
    this.entryReceiptReturn = v;
    
  }










  public java.lang.String toString() {
  return "EntryReceiptResponse" + "{"
             + " entryReceiptReturn=<" + getEntryReceiptReturn() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof EntryReceiptResponse) {
      
      EntryReceiptResponse __obj__ =  (EntryReceiptResponse) __other__;


      return true
            && (entryReceiptReturn==null ? __obj__.getEntryReceiptReturn()==null : entryReceiptReturn.equals(__obj__.getEntryReceiptReturn()))


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (entryReceiptReturn==null ? 0 : entryReceiptReturn.hashCode()) ;
    

    return __hash__result__;
  }

}


@
