head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.36.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	DeleteOshiraseResponse.java;


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

// original type: ['http://www.hitachi.co.jp/TFX/WebService']:DeleteOshiraseResponse


public  class DeleteOshiraseResponse 
  
  implements java.io.Serializable
{

  public DeleteOshiraseResponse() {}

  public DeleteOshiraseResponse(jp.co.hitachi.www.TFX.WebService.DeleteOshiraseOut p_deleteOshiraseReturn) 
  {
     this.deleteOshiraseReturn = p_deleteOshiraseReturn;
     

  }




  
  private jp.co.hitachi.www.TFX.WebService.DeleteOshiraseOut deleteOshiraseReturn ;

  /**
  <br>  Derived from DeleteOshiraseReturn.

  <br>  schema name = ['http://www.hitachi.co.jp/TFX/WebService']:DeleteOshiraseReturn
  <br>  schema type = ['http://www.hitachi.co.jp/TFX/WebService']:DeleteOshiraseOut
  <br>  schema formQualified = true
  */
  public jp.co.hitachi.www.TFX.WebService.DeleteOshiraseOut getDeleteOshiraseReturn() {
    return deleteOshiraseReturn;
  }

  public void setDeleteOshiraseReturn(jp.co.hitachi.www.TFX.WebService.DeleteOshiraseOut v) {
    
    this.deleteOshiraseReturn = v;
    
  }










  public java.lang.String toString() {
  return "DeleteOshiraseResponse" + "{"
             + " deleteOshiraseReturn=<" + getDeleteOshiraseReturn() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof DeleteOshiraseResponse) {
      
      DeleteOshiraseResponse __obj__ =  (DeleteOshiraseResponse) __other__;


      return true
            && (deleteOshiraseReturn==null ? __obj__.getDeleteOshiraseReturn()==null : deleteOshiraseReturn.equals(__obj__.getDeleteOshiraseReturn()))


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (deleteOshiraseReturn==null ? 0 : deleteOshiraseReturn.hashCode()) ;
    

    return __hash__result__;
  }

}


@
