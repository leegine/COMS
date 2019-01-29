head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.37.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	EntryOshiraseResponse.java;


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

// original type: ['http://www.hitachi.co.jp/TFX/WebService']:EntryOshiraseResponse


public  class EntryOshiraseResponse 
  
  implements java.io.Serializable
{

  public EntryOshiraseResponse() {}

  public EntryOshiraseResponse(jp.co.hitachi.www.TFX.WebService.EntryOshiraseOut p_entryOshiraseReturn) 
  {
     this.entryOshiraseReturn = p_entryOshiraseReturn;
     

  }




  
  private jp.co.hitachi.www.TFX.WebService.EntryOshiraseOut entryOshiraseReturn ;

  /**
  <br>  Derived from EntryOshiraseReturn.

  <br>  schema name = ['http://www.hitachi.co.jp/TFX/WebService']:EntryOshiraseReturn
  <br>  schema type = ['http://www.hitachi.co.jp/TFX/WebService']:EntryOshiraseOut
  <br>  schema formQualified = true
  */
  public jp.co.hitachi.www.TFX.WebService.EntryOshiraseOut getEntryOshiraseReturn() {
    return entryOshiraseReturn;
  }

  public void setEntryOshiraseReturn(jp.co.hitachi.www.TFX.WebService.EntryOshiraseOut v) {
    
    this.entryOshiraseReturn = v;
    
  }










  public java.lang.String toString() {
  return "EntryOshiraseResponse" + "{"
             + " entryOshiraseReturn=<" + getEntryOshiraseReturn() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof EntryOshiraseResponse) {
      
      EntryOshiraseResponse __obj__ =  (EntryOshiraseResponse) __other__;


      return true
            && (entryOshiraseReturn==null ? __obj__.getEntryOshiraseReturn()==null : entryOshiraseReturn.equals(__obj__.getEntryOshiraseReturn()))


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (entryOshiraseReturn==null ? 0 : entryOshiraseReturn.hashCode()) ;
    

    return __hash__result__;
  }

}


@
