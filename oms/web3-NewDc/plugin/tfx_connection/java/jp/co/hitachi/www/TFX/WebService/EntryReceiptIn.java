head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.36.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	EntryReceiptIn.java;


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

// original type: ['http://www.hitachi.co.jp/TFX/WebService']:EntryReceiptIn


public  class EntryReceiptIn 
  
  implements java.io.Serializable
{

  public EntryReceiptIn() {}

  public EntryReceiptIn(java.lang.String p_kamokuCd
,     java.math.BigDecimal p_reptAmt
,     java.lang.String p_rysCd
,     java.lang.String p_webServReqNo) 
  {
     this.kamokuCd = p_kamokuCd;
     
     this.reptAmt = p_reptAmt;
     
     this.rysCd = p_rysCd;
     
     this.webServReqNo = p_webServReqNo;
     

  }




  
  private java.lang.String kamokuCd ;

  /**
  <br>  Derived from kamokuCd.

  <br>  schema name = ['http://www.hitachi.co.jp/TFX/WebService']:kamokuCd
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getKamokuCd() {
    return kamokuCd;
  }

  public void setKamokuCd(java.lang.String v) {
    
    this.kamokuCd = v;
    
  }





  
  private java.math.BigDecimal reptAmt ;

  /**
  <br>  Derived from reptAmt.

  <br>  schema name = ['http://www.hitachi.co.jp/TFX/WebService']:reptAmt
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:decimal
  <br>  schema formQualified = true
  */
  public java.math.BigDecimal getReptAmt() {
    return reptAmt;
  }

  public void setReptAmt(java.math.BigDecimal v) {
    
    this.reptAmt = v;
    
  }





  
  private java.lang.String rysCd ;

  /**
  <br>  Derived from rysCd.

  <br>  schema name = ['http://www.hitachi.co.jp/TFX/WebService']:rysCd
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getRysCd() {
    return rysCd;
  }

  public void setRysCd(java.lang.String v) {
    
    this.rysCd = v;
    
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
  return "EntryReceiptIn" + "{"
             + " kamokuCd=<" + getKamokuCd() + ">"
             + " reptAmt=<" + getReptAmt() + ">"
             + " rysCd=<" + getRysCd() + ">"
             + " webServReqNo=<" + getWebServReqNo() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof EntryReceiptIn) {
      
      EntryReceiptIn __obj__ =  (EntryReceiptIn) __other__;


      return true
            && (kamokuCd==null ? __obj__.getKamokuCd()==null : kamokuCd.equals(__obj__.getKamokuCd()))
            && (reptAmt==null ? __obj__.getReptAmt()==null : reptAmt.equals(__obj__.getReptAmt()))
            && (rysCd==null ? __obj__.getRysCd()==null : rysCd.equals(__obj__.getRysCd()))
            && (webServReqNo==null ? __obj__.getWebServReqNo()==null : webServReqNo.equals(__obj__.getWebServReqNo()))


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (kamokuCd==null ? 0 : kamokuCd.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (reptAmt==null ? 0 : reptAmt.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (rysCd==null ? 0 : rysCd.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (webServReqNo==null ? 0 : webServReqNo.hashCode()) ;
    

    return __hash__result__;
  }

}


@
