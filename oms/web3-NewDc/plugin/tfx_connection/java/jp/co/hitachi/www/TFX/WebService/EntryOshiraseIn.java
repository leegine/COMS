head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.39.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	EntryOshiraseIn.java;


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

// original type: ['http://www.hitachi.co.jp/TFX/WebService']:EntryOshiraseIn


public  class EntryOshiraseIn 
  
  implements java.io.Serializable
{

  public EntryOshiraseIn() {}

  public EntryOshiraseIn(java.lang.String p_cont
,     java.lang.String p_ntcDate
,     java.lang.String p_ntcDiv
,     java.lang.String p_ntcDtStiDiv
,     java.lang.String p_ntcEndDate
,     java.lang.String p_ntcJyuyodoDiv
,     java.lang.String p_ntcTargetaDiv
,     java.lang.String p_ntcTime
,     java.lang.String p_title
,     java.lang.String p_webServReqNo) 
  {
     this.cont = p_cont;
     
     this.ntcDate = p_ntcDate;
     
     this.ntcDiv = p_ntcDiv;
     
     this.ntcDtStiDiv = p_ntcDtStiDiv;
     
     this.ntcEndDate = p_ntcEndDate;
     
     this.ntcJyuyodoDiv = p_ntcJyuyodoDiv;
     
     this.ntcTargetaDiv = p_ntcTargetaDiv;
     
     this.ntcTime = p_ntcTime;
     
     this.title = p_title;
     
     this.webServReqNo = p_webServReqNo;
     

  }




  
  private java.lang.String cont ;

  /**
  <br>  Derived from cont.

  <br>  schema name = ['http://www.hitachi.co.jp/TFX/WebService']:cont
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getCont() {
    return cont;
  }

  public void setCont(java.lang.String v) {
    
    this.cont = v;
    
  }





  
  private java.lang.String ntcDate ;

  /**
  <br>  Derived from ntcDate.

  <br>  schema name = ['http://www.hitachi.co.jp/TFX/WebService']:ntcDate
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getNtcDate() {
    return ntcDate;
  }

  public void setNtcDate(java.lang.String v) {
    
    this.ntcDate = v;
    
  }





  
  private java.lang.String ntcDiv ;

  /**
  <br>  Derived from ntcDiv.

  <br>  schema name = ['http://www.hitachi.co.jp/TFX/WebService']:ntcDiv
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getNtcDiv() {
    return ntcDiv;
  }

  public void setNtcDiv(java.lang.String v) {
    
    this.ntcDiv = v;
    
  }





  
  private java.lang.String ntcDtStiDiv ;

  /**
  <br>  Derived from ntcDtStiDiv.

  <br>  schema name = ['http://www.hitachi.co.jp/TFX/WebService']:ntcDtStiDiv
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getNtcDtStiDiv() {
    return ntcDtStiDiv;
  }

  public void setNtcDtStiDiv(java.lang.String v) {
    
    this.ntcDtStiDiv = v;
    
  }





  
  private java.lang.String ntcEndDate ;

  /**
  <br>  Derived from ntcEndDate.

  <br>  schema name = ['http://www.hitachi.co.jp/TFX/WebService']:ntcEndDate
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getNtcEndDate() {
    return ntcEndDate;
  }

  public void setNtcEndDate(java.lang.String v) {
    
    this.ntcEndDate = v;
    
  }





  
  private java.lang.String ntcJyuyodoDiv ;

  /**
  <br>  Derived from ntcJyuyodoDiv.

  <br>  schema name = ['http://www.hitachi.co.jp/TFX/WebService']:ntcJyuyodoDiv
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getNtcJyuyodoDiv() {
    return ntcJyuyodoDiv;
  }

  public void setNtcJyuyodoDiv(java.lang.String v) {
    
    this.ntcJyuyodoDiv = v;
    
  }





  
  private java.lang.String ntcTargetaDiv ;

  /**
  <br>  Derived from ntcTargetaDiv.

  <br>  schema name = ['http://www.hitachi.co.jp/TFX/WebService']:ntcTargetaDiv
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getNtcTargetaDiv() {
    return ntcTargetaDiv;
  }

  public void setNtcTargetaDiv(java.lang.String v) {
    
    this.ntcTargetaDiv = v;
    
  }





  
  private java.lang.String ntcTime ;

  /**
  <br>  Derived from ntcTime.

  <br>  schema name = ['http://www.hitachi.co.jp/TFX/WebService']:ntcTime
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getNtcTime() {
    return ntcTime;
  }

  public void setNtcTime(java.lang.String v) {
    
    this.ntcTime = v;
    
  }





  
  private java.lang.String title ;

  /**
  <br>  Derived from title.

  <br>  schema name = ['http://www.hitachi.co.jp/TFX/WebService']:title
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getTitle() {
    return title;
  }

  public void setTitle(java.lang.String v) {
    
    this.title = v;
    
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
  return "EntryOshiraseIn" + "{"
             + " cont=<" + getCont() + ">"
             + " ntcDate=<" + getNtcDate() + ">"
             + " ntcDiv=<" + getNtcDiv() + ">"
             + " ntcDtStiDiv=<" + getNtcDtStiDiv() + ">"
             + " ntcEndDate=<" + getNtcEndDate() + ">"
             + " ntcJyuyodoDiv=<" + getNtcJyuyodoDiv() + ">"
             + " ntcTargetaDiv=<" + getNtcTargetaDiv() + ">"
             + " ntcTime=<" + getNtcTime() + ">"
             + " title=<" + getTitle() + ">"
             + " webServReqNo=<" + getWebServReqNo() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof EntryOshiraseIn) {
      
      EntryOshiraseIn __obj__ =  (EntryOshiraseIn) __other__;


      return true
            && (cont==null ? __obj__.getCont()==null : cont.equals(__obj__.getCont()))
            && (ntcDate==null ? __obj__.getNtcDate()==null : ntcDate.equals(__obj__.getNtcDate()))
            && (ntcDiv==null ? __obj__.getNtcDiv()==null : ntcDiv.equals(__obj__.getNtcDiv()))
            && (ntcDtStiDiv==null ? __obj__.getNtcDtStiDiv()==null : ntcDtStiDiv.equals(__obj__.getNtcDtStiDiv()))
            && (ntcEndDate==null ? __obj__.getNtcEndDate()==null : ntcEndDate.equals(__obj__.getNtcEndDate()))
            && (ntcJyuyodoDiv==null ? __obj__.getNtcJyuyodoDiv()==null : ntcJyuyodoDiv.equals(__obj__.getNtcJyuyodoDiv()))
            && (ntcTargetaDiv==null ? __obj__.getNtcTargetaDiv()==null : ntcTargetaDiv.equals(__obj__.getNtcTargetaDiv()))
            && (ntcTime==null ? __obj__.getNtcTime()==null : ntcTime.equals(__obj__.getNtcTime()))
            && (title==null ? __obj__.getTitle()==null : title.equals(__obj__.getTitle()))
            && (webServReqNo==null ? __obj__.getWebServReqNo()==null : webServReqNo.equals(__obj__.getWebServReqNo()))


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (cont==null ? 0 : cont.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (ntcDate==null ? 0 : ntcDate.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (ntcDiv==null ? 0 : ntcDiv.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (ntcDtStiDiv==null ? 0 : ntcDtStiDiv.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (ntcEndDate==null ? 0 : ntcEndDate.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (ntcJyuyodoDiv==null ? 0 : ntcJyuyodoDiv.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (ntcTargetaDiv==null ? 0 : ntcTargetaDiv.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (ntcTime==null ? 0 : ntcTime.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (title==null ? 0 : title.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (webServReqNo==null ? 0 : webServReqNo.hashCode()) ;
    

    return __hash__result__;
  }

}


@
