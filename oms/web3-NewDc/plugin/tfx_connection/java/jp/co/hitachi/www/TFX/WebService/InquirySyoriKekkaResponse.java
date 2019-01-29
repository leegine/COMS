head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.38.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	InquirySyoriKekkaResponse.java;


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

// original type: ['http://www.hitachi.co.jp/TFX/WebService']:InquirySyoriKekkaResponse


public  class InquirySyoriKekkaResponse 
  
  implements java.io.Serializable
{

  public InquirySyoriKekkaResponse() {}

  public InquirySyoriKekkaResponse(jp.co.hitachi.www.TFX.WebService.InquirySyoriKekkaOut p_inquirySyoriKekkaReturn) 
  {
     this.inquirySyoriKekkaReturn = p_inquirySyoriKekkaReturn;
     

  }




  
  private jp.co.hitachi.www.TFX.WebService.InquirySyoriKekkaOut inquirySyoriKekkaReturn ;

  /**
  <br>  Derived from InquirySyoriKekkaReturn.

  <br>  schema name = ['http://www.hitachi.co.jp/TFX/WebService']:InquirySyoriKekkaReturn
  <br>  schema type = ['http://www.hitachi.co.jp/TFX/WebService']:InquirySyoriKekkaOut
  <br>  schema formQualified = true
  */
  public jp.co.hitachi.www.TFX.WebService.InquirySyoriKekkaOut getInquirySyoriKekkaReturn() {
    return inquirySyoriKekkaReturn;
  }

  public void setInquirySyoriKekkaReturn(jp.co.hitachi.www.TFX.WebService.InquirySyoriKekkaOut v) {
    
    this.inquirySyoriKekkaReturn = v;
    
  }










  public java.lang.String toString() {
  return "InquirySyoriKekkaResponse" + "{"
             + " inquirySyoriKekkaReturn=<" + getInquirySyoriKekkaReturn() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof InquirySyoriKekkaResponse) {
      
      InquirySyoriKekkaResponse __obj__ =  (InquirySyoriKekkaResponse) __other__;


      return true
            && (inquirySyoriKekkaReturn==null ? __obj__.getInquirySyoriKekkaReturn()==null : inquirySyoriKekkaReturn.equals(__obj__.getInquirySyoriKekkaReturn()))


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (inquirySyoriKekkaReturn==null ? 0 : inquirySyoriKekkaReturn.hashCode()) ;
    

    return __hash__result__;
  }

}


@
