head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.37.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	ChangeRiyousyaResponse.java;


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

// original type: ['http://www.hitachi.co.jp/TFX/WebService']:ChangeRiyousyaResponse


public  class ChangeRiyousyaResponse 
  
  implements java.io.Serializable
{

  public ChangeRiyousyaResponse() {}

  public ChangeRiyousyaResponse(jp.co.hitachi.www.TFX.WebService.ChangeRiyousyaOut p_changeRiyousyaReturn) 
  {
     this.changeRiyousyaReturn = p_changeRiyousyaReturn;
     

  }




  
  private jp.co.hitachi.www.TFX.WebService.ChangeRiyousyaOut changeRiyousyaReturn ;

  /**
  <br>  Derived from ChangeRiyousyaReturn.

  <br>  schema name = ['http://www.hitachi.co.jp/TFX/WebService']:ChangeRiyousyaReturn
  <br>  schema type = ['http://www.hitachi.co.jp/TFX/WebService']:ChangeRiyousyaOut
  <br>  schema formQualified = true
  */
  public jp.co.hitachi.www.TFX.WebService.ChangeRiyousyaOut getChangeRiyousyaReturn() {
    return changeRiyousyaReturn;
  }

  public void setChangeRiyousyaReturn(jp.co.hitachi.www.TFX.WebService.ChangeRiyousyaOut v) {
    
    this.changeRiyousyaReturn = v;
    
  }










  public java.lang.String toString() {
  return "ChangeRiyousyaResponse" + "{"
             + " changeRiyousyaReturn=<" + getChangeRiyousyaReturn() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof ChangeRiyousyaResponse) {
      
      ChangeRiyousyaResponse __obj__ =  (ChangeRiyousyaResponse) __other__;


      return true
            && (changeRiyousyaReturn==null ? __obj__.getChangeRiyousyaReturn()==null : changeRiyousyaReturn.equals(__obj__.getChangeRiyousyaReturn()))


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (changeRiyousyaReturn==null ? 0 : changeRiyousyaReturn.hashCode()) ;
    

    return __hash__result__;
  }

}


@
