head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.36.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	DeleteOshiraseIn.java;


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

// original type: ['http://www.hitachi.co.jp/TFX/WebService']:DeleteOshiraseIn


public  class DeleteOshiraseIn 
  
  implements java.io.Serializable
{

  public DeleteOshiraseIn() {}

  public DeleteOshiraseIn(int p_ntcNo
,     java.lang.String p_webServReqNo) 
  {
     this.ntcNo = p_ntcNo;
     
     this.webServReqNo = p_webServReqNo;
     

  }




  
  private int ntcNo ;

  /**
  <br>  Derived from ntcNo.

  <br>  schema name = ['http://www.hitachi.co.jp/TFX/WebService']:ntcNo
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:int
  <br>  schema formQualified = true
  */
  public int getNtcNo() {
    return ntcNo;
  }

  public void setNtcNo(int v) {
    
    this.ntcNo = v;
    
  }





  
  private java.lang.String webServReqNo ;

  /**
  <br>  Derived from webServReqNo.

  <br>  schema name = ['http://www.hitachi.co.jp/TFX/WebService']:webServReqNo
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getWebServReqNo() {
    return webServReqNo;
  }

  public void setWebServReqNo(java.lang.String v) {
    
    this.webServReqNo = v;
    
  }










  public java.lang.String toString() {
  return "DeleteOshiraseIn" + "{"
             + " ntcNo=<" + getNtcNo() + ">"
             + " webServReqNo=<" + getWebServReqNo() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof DeleteOshiraseIn) {
      
      DeleteOshiraseIn __obj__ =  (DeleteOshiraseIn) __other__;


      return true
            && (ntcNo == __obj__.getNtcNo())
            && (webServReqNo==null ? __obj__.getWebServReqNo()==null : webServReqNo.equals(__obj__.getWebServReqNo()))


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + ntcNo ;
    __hash__result__ = 37*__hash__result__ + (webServReqNo==null ? 0 : webServReqNo.hashCode()) ;
    

    return __hash__result__;
  }

}


@
