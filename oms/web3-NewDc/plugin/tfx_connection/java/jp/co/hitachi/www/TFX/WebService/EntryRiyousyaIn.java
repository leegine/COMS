head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.38.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	EntryRiyousyaIn.java;


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

// original type: ['http://www.hitachi.co.jp/TFX/WebService']:EntryRiyousyaIn


public  class EntryRiyousyaIn 
  
  implements java.io.Serializable
{

  public EntryRiyousyaIn() {}

  public EntryRiyousyaIn(java.lang.String p_biko
,     java.lang.String p_chgDiv
,     java.lang.String p_edocConsDate
,     jp.co.hitachi.www.TFX.WebService.HachuUlimitVolBean[] p_hachuUlimitVols
,     java.lang.String p_hchupw
,     java.lang.String p_jkjtDiv
,     java.lang.String p_lastManualConfDate
,     java.lang.String p_lcutDiv
,     java.lang.String p_linpw
,     java.lang.String p_loginId
,     java.lang.String p_mailad1
,     java.lang.String p_mailad2
,     java.lang.String p_rysAttribute
,     java.lang.String p_rysCd
,     java.lang.String p_rysName
,     java.lang.String p_tradeKanouDiv
,     java.lang.String p_webServReqNo
,     java.lang.String p_yakudakusyoNo) 
  {
     this.biko = p_biko;
     
     this.chgDiv = p_chgDiv;
     
     this.edocConsDate = p_edocConsDate;
     
     this.hachuUlimitVols = p_hachuUlimitVols;
     
     this.hchupw = p_hchupw;
     
     this.jkjtDiv = p_jkjtDiv;
     
     this.lastManualConfDate = p_lastManualConfDate;
     
     this.lcutDiv = p_lcutDiv;
     
     this.linpw = p_linpw;
     
     this.loginId = p_loginId;
     
     this.mailad1 = p_mailad1;
     
     this.mailad2 = p_mailad2;
     
     this.rysAttribute = p_rysAttribute;
     
     this.rysCd = p_rysCd;
     
     this.rysName = p_rysName;
     
     this.tradeKanouDiv = p_tradeKanouDiv;
     
     this.webServReqNo = p_webServReqNo;
     
     this.yakudakusyoNo = p_yakudakusyoNo;
     

  }




  
  private java.lang.String biko ;

  /**
  <br>  Derived from biko.

  <br>  schema name = ['http://www.hitachi.co.jp/TFX/WebService']:biko
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getBiko() {
    return biko;
  }

  public void setBiko(java.lang.String v) {
    
    this.biko = v;
    
  }





  
  private java.lang.String chgDiv ;

  /**
  <br>  Derived from chgDiv.

  <br>  schema name = ['http://www.hitachi.co.jp/TFX/WebService']:chgDiv
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getChgDiv() {
    return chgDiv;
  }

  public void setChgDiv(java.lang.String v) {
    
    this.chgDiv = v;
    
  }





  
  private java.lang.String edocConsDate ;

  /**
  <br>  Derived from edocConsDate.

  <br>  schema name = ['http://www.hitachi.co.jp/TFX/WebService']:edocConsDate
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getEdocConsDate() {
    return edocConsDate;
  }

  public void setEdocConsDate(java.lang.String v) {
    
    this.edocConsDate = v;
    
  }





  
  private jp.co.hitachi.www.TFX.WebService.HachuUlimitVolBean[] hachuUlimitVols ;

  /**
  <br>  Derived from hachuUlimitVols.

  <br>  schema name = ['http://www.hitachi.co.jp/TFX/WebService']:hachuUlimitVols
  <br>  schema type = ['http://www.hitachi.co.jp/TFX/WebService']:SequenceOfHachuUlimitVolBean
  <br>  schema formQualified = true
  */
  public jp.co.hitachi.www.TFX.WebService.HachuUlimitVolBean[] getHachuUlimitVols() {
    return hachuUlimitVols;
  }

  public void setHachuUlimitVols(jp.co.hitachi.www.TFX.WebService.HachuUlimitVolBean[] v) {
    
    this.hachuUlimitVols = v;
    
  }





  
  private java.lang.String hchupw ;

  /**
  <br>  Derived from hchupw.

  <br>  schema name = ['http://www.hitachi.co.jp/TFX/WebService']:hchupw
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getHchupw() {
    return hchupw;
  }

  public void setHchupw(java.lang.String v) {
    
    this.hchupw = v;
    
  }





  
  private java.lang.String jkjtDiv ;

  /**
  <br>  Derived from jkjtDiv.

  <br>  schema name = ['http://www.hitachi.co.jp/TFX/WebService']:jkjtDiv
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getJkjtDiv() {
    return jkjtDiv;
  }

  public void setJkjtDiv(java.lang.String v) {
    
    this.jkjtDiv = v;
    
  }





  
  private java.lang.String lastManualConfDate ;

  /**
  <br>  Derived from lastManualConfDate.

  <br>  schema name = ['http://www.hitachi.co.jp/TFX/WebService']:lastManualConfDate
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getLastManualConfDate() {
    return lastManualConfDate;
  }

  public void setLastManualConfDate(java.lang.String v) {
    
    this.lastManualConfDate = v;
    
  }





  
  private java.lang.String lcutDiv ;

  /**
  <br>  Derived from lcutDiv.

  <br>  schema name = ['http://www.hitachi.co.jp/TFX/WebService']:lcutDiv
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getLcutDiv() {
    return lcutDiv;
  }

  public void setLcutDiv(java.lang.String v) {
    
    this.lcutDiv = v;
    
  }





  
  private java.lang.String linpw ;

  /**
  <br>  Derived from linpw.

  <br>  schema name = ['http://www.hitachi.co.jp/TFX/WebService']:linpw
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getLinpw() {
    return linpw;
  }

  public void setLinpw(java.lang.String v) {
    
    this.linpw = v;
    
  }





  
  private java.lang.String loginId ;

  /**
  <br>  Derived from loginId.

  <br>  schema name = ['http://www.hitachi.co.jp/TFX/WebService']:loginId
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getLoginId() {
    return loginId;
  }

  public void setLoginId(java.lang.String v) {
    
    this.loginId = v;
    
  }





  
  private java.lang.String mailad1 ;

  /**
  <br>  Derived from mailad1.

  <br>  schema name = ['http://www.hitachi.co.jp/TFX/WebService']:mailad1
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getMailad1() {
    return mailad1;
  }

  public void setMailad1(java.lang.String v) {
    
    this.mailad1 = v;
    
  }





  
  private java.lang.String mailad2 ;

  /**
  <br>  Derived from mailad2.

  <br>  schema name = ['http://www.hitachi.co.jp/TFX/WebService']:mailad2
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getMailad2() {
    return mailad2;
  }

  public void setMailad2(java.lang.String v) {
    
    this.mailad2 = v;
    
  }





  
  private java.lang.String rysAttribute ;

  /**
  <br>  Derived from rysAttribute.

  <br>  schema name = ['http://www.hitachi.co.jp/TFX/WebService']:rysAttribute
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getRysAttribute() {
    return rysAttribute;
  }

  public void setRysAttribute(java.lang.String v) {
    
    this.rysAttribute = v;
    
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





  
  private java.lang.String rysName ;

  /**
  <br>  Derived from rysName.

  <br>  schema name = ['http://www.hitachi.co.jp/TFX/WebService']:rysName
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getRysName() {
    return rysName;
  }

  public void setRysName(java.lang.String v) {
    
    this.rysName = v;
    
  }





  
  private java.lang.String tradeKanouDiv ;

  /**
  <br>  Derived from tradeKanouDiv.

  <br>  schema name = ['http://www.hitachi.co.jp/TFX/WebService']:tradeKanouDiv
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getTradeKanouDiv() {
    return tradeKanouDiv;
  }

  public void setTradeKanouDiv(java.lang.String v) {
    
    this.tradeKanouDiv = v;
    
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





  
  private java.lang.String yakudakusyoNo ;

  /**
  <br>  Derived from yakudakusyoNo.

  <br>  schema name = ['http://www.hitachi.co.jp/TFX/WebService']:yakudakusyoNo
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getYakudakusyoNo() {
    return yakudakusyoNo;
  }

  public void setYakudakusyoNo(java.lang.String v) {
    
    this.yakudakusyoNo = v;
    
  }










  public java.lang.String toString() {
  return "EntryRiyousyaIn" + "{"
             + " biko=<" + getBiko() + ">"
             + " chgDiv=<" + getChgDiv() + ">"
             + " edocConsDate=<" + getEdocConsDate() + ">"
             + " hachuUlimitVols=<" + weblogic.xml.schema.binding.RuntimeUtils.arrayToString( getHachuUlimitVols() ) + ">"
             + " hchupw=<" + getHchupw() + ">"
             + " jkjtDiv=<" + getJkjtDiv() + ">"
             + " lastManualConfDate=<" + getLastManualConfDate() + ">"
             + " lcutDiv=<" + getLcutDiv() + ">"
             + " linpw=<" + getLinpw() + ">"
             + " loginId=<" + getLoginId() + ">"
             + " mailad1=<" + getMailad1() + ">"
             + " mailad2=<" + getMailad2() + ">"
             + " rysAttribute=<" + getRysAttribute() + ">"
             + " rysCd=<" + getRysCd() + ">"
             + " rysName=<" + getRysName() + ">"
             + " tradeKanouDiv=<" + getTradeKanouDiv() + ">"
             + " webServReqNo=<" + getWebServReqNo() + ">"
             + " yakudakusyoNo=<" + getYakudakusyoNo() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof EntryRiyousyaIn) {
      
      EntryRiyousyaIn __obj__ =  (EntryRiyousyaIn) __other__;


      return true
            && (biko==null ? __obj__.getBiko()==null : biko.equals(__obj__.getBiko()))
            && (chgDiv==null ? __obj__.getChgDiv()==null : chgDiv.equals(__obj__.getChgDiv()))
            && (edocConsDate==null ? __obj__.getEdocConsDate()==null : edocConsDate.equals(__obj__.getEdocConsDate()))
            && weblogic.xml.schema.binding.RuntimeUtils.compareObjects(hachuUlimitVols, __obj__.getHachuUlimitVols())
            && (hchupw==null ? __obj__.getHchupw()==null : hchupw.equals(__obj__.getHchupw()))
            && (jkjtDiv==null ? __obj__.getJkjtDiv()==null : jkjtDiv.equals(__obj__.getJkjtDiv()))
            && (lastManualConfDate==null ? __obj__.getLastManualConfDate()==null : lastManualConfDate.equals(__obj__.getLastManualConfDate()))
            && (lcutDiv==null ? __obj__.getLcutDiv()==null : lcutDiv.equals(__obj__.getLcutDiv()))
            && (linpw==null ? __obj__.getLinpw()==null : linpw.equals(__obj__.getLinpw()))
            && (loginId==null ? __obj__.getLoginId()==null : loginId.equals(__obj__.getLoginId()))
            && (mailad1==null ? __obj__.getMailad1()==null : mailad1.equals(__obj__.getMailad1()))
            && (mailad2==null ? __obj__.getMailad2()==null : mailad2.equals(__obj__.getMailad2()))
            && (rysAttribute==null ? __obj__.getRysAttribute()==null : rysAttribute.equals(__obj__.getRysAttribute()))
            && (rysCd==null ? __obj__.getRysCd()==null : rysCd.equals(__obj__.getRysCd()))
            && (rysName==null ? __obj__.getRysName()==null : rysName.equals(__obj__.getRysName()))
            && (tradeKanouDiv==null ? __obj__.getTradeKanouDiv()==null : tradeKanouDiv.equals(__obj__.getTradeKanouDiv()))
            && (webServReqNo==null ? __obj__.getWebServReqNo()==null : webServReqNo.equals(__obj__.getWebServReqNo()))
            && (yakudakusyoNo==null ? __obj__.getYakudakusyoNo()==null : yakudakusyoNo.equals(__obj__.getYakudakusyoNo()))


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (biko==null ? 0 : biko.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (chgDiv==null ? 0 : chgDiv.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (edocConsDate==null ? 0 : edocConsDate.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (hachuUlimitVols==null ? 0 : weblogic.xml.schema.binding.RuntimeUtils.arrayHashCode(hachuUlimitVols)) ;
    __hash__result__ = 37*__hash__result__ + (hchupw==null ? 0 : hchupw.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (jkjtDiv==null ? 0 : jkjtDiv.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (lastManualConfDate==null ? 0 : lastManualConfDate.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (lcutDiv==null ? 0 : lcutDiv.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (linpw==null ? 0 : linpw.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (loginId==null ? 0 : loginId.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (mailad1==null ? 0 : mailad1.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (mailad2==null ? 0 : mailad2.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (rysAttribute==null ? 0 : rysAttribute.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (rysCd==null ? 0 : rysCd.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (rysName==null ? 0 : rysName.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (tradeKanouDiv==null ? 0 : tradeKanouDiv.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (webServReqNo==null ? 0 : webServReqNo.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (yakudakusyoNo==null ? 0 : yakudakusyoNo.hashCode()) ;
    

    return __hash__result__;
  }

}


@
