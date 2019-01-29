head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.36.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	InquirySyoriKekkaOut.java;


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

// original type: ['http://www.hitachi.co.jp/TFX/WebService']:InquirySyoriKekkaOut


public  class InquirySyoriKekkaOut 
  
  implements java.io.Serializable
{

  public InquirySyoriKekkaOut() {}

  public InquirySyoriKekkaOut(int p_ntcNo
,     int p_refReturnCd
,     int p_returnCd
,     java.lang.String p_webServDiv) 
  {
     this.ntcNo = p_ntcNo;
     
     this.refReturnCd = p_refReturnCd;
     
     this.returnCd = p_returnCd;
     
     this.webServDiv = p_webServDiv;
     

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





  
  private int refReturnCd ;

  /**
  <br>  Derived from refReturnCd.

  <br>  schema name = ['http://www.hitachi.co.jp/TFX/WebService']:refReturnCd
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:int
  <br>  schema formQualified = true
  */
  public int getRefReturnCd() {
    return refReturnCd;
  }

  public void setRefReturnCd(int v) {
    
    this.refReturnCd = v;
    
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





  
  private java.lang.String webServDiv ;

  /**
  <br>  Derived from webServDiv.

  <br>  schema name = ['http://www.hitachi.co.jp/TFX/WebService']:webServDiv
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getWebServDiv() {
    return webServDiv;
  }

  public void setWebServDiv(java.lang.String v) {
    
    this.webServDiv = v;
    
  }










  public java.lang.String toString() {
  return "InquirySyoriKekkaOut" + "{"
             + " ntcNo=<" + getNtcNo() + ">"
             + " refReturnCd=<" + getRefReturnCd() + ">"
             + " returnCd=<" + getReturnCd() + ">"
             + " webServDiv=<" + getWebServDiv() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof InquirySyoriKekkaOut) {
      
      InquirySyoriKekkaOut __obj__ =  (InquirySyoriKekkaOut) __other__;


      return true
            && (ntcNo == __obj__.getNtcNo())
            && (refReturnCd == __obj__.getRefReturnCd())
            && (returnCd == __obj__.getReturnCd())
            && (webServDiv==null ? __obj__.getWebServDiv()==null : webServDiv.equals(__obj__.getWebServDiv()))


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + ntcNo ;
    __hash__result__ = 37*__hash__result__ + refReturnCd ;
    __hash__result__ = 37*__hash__result__ + returnCd ;
    __hash__result__ = 37*__hash__result__ + (webServDiv==null ? 0 : webServDiv.hashCode()) ;
    

    return __hash__result__;
  }

}


@
