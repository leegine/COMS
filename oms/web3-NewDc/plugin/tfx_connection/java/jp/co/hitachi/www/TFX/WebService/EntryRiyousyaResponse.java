head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.39.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	EntryRiyousyaResponse.java;


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

// original type: ['http://www.hitachi.co.jp/TFX/WebService']:EntryRiyousyaResponse


public  class EntryRiyousyaResponse 
  
  implements java.io.Serializable
{

  public EntryRiyousyaResponse() {}

  public EntryRiyousyaResponse(jp.co.hitachi.www.TFX.WebService.EntryRiyousyaOut p_entryRiyousyaReturn) 
  {
     this.entryRiyousyaReturn = p_entryRiyousyaReturn;
     

  }




  
  private jp.co.hitachi.www.TFX.WebService.EntryRiyousyaOut entryRiyousyaReturn ;

  /**
  <br>  Derived from EntryRiyousyaReturn.

  <br>  schema name = ['http://www.hitachi.co.jp/TFX/WebService']:EntryRiyousyaReturn
  <br>  schema type = ['http://www.hitachi.co.jp/TFX/WebService']:EntryRiyousyaOut
  <br>  schema formQualified = true
  */
  public jp.co.hitachi.www.TFX.WebService.EntryRiyousyaOut getEntryRiyousyaReturn() {
    return entryRiyousyaReturn;
  }

  public void setEntryRiyousyaReturn(jp.co.hitachi.www.TFX.WebService.EntryRiyousyaOut v) {
    
    this.entryRiyousyaReturn = v;
    
  }










  public java.lang.String toString() {
  return "EntryRiyousyaResponse" + "{"
             + " entryRiyousyaReturn=<" + getEntryRiyousyaReturn() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof EntryRiyousyaResponse) {
      
      EntryRiyousyaResponse __obj__ =  (EntryRiyousyaResponse) __other__;


      return true
            && (entryRiyousyaReturn==null ? __obj__.getEntryRiyousyaReturn()==null : entryRiyousyaReturn.equals(__obj__.getEntryRiyousyaReturn()))


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (entryRiyousyaReturn==null ? 0 : entryRiyousyaReturn.hashCode()) ;
    

    return __hash__result__;
  }

}


@
