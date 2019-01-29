head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.36.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	HachuUlimitVolBean.java;


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

// original type: ['http://www.hitachi.co.jp/TFX/WebService']:HachuUlimitVolBean


public  class HachuUlimitVolBean 
  
  implements java.io.Serializable
{

  public HachuUlimitVolBean() {}

  public HachuUlimitVolBean(java.math.BigDecimal p_hachuUlimitVol
,     java.lang.String p_shouhinCd) 
  {
     this.hachuUlimitVol = p_hachuUlimitVol;
     
     this.shouhinCd = p_shouhinCd;
     

  }




  
  private java.math.BigDecimal hachuUlimitVol ;

  /**
  <br>  Derived from hachuUlimitVol.

  <br>  schema name = ['http://www.hitachi.co.jp/TFX/WebService']:hachuUlimitVol
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:decimal
  <br>  schema formQualified = true
  */
  public java.math.BigDecimal getHachuUlimitVol() {
    return hachuUlimitVol;
  }

  public void setHachuUlimitVol(java.math.BigDecimal v) {
    
    this.hachuUlimitVol = v;
    
  }





  
  private java.lang.String shouhinCd ;

  /**
  <br>  Derived from shouhinCd.

  <br>  schema name = ['http://www.hitachi.co.jp/TFX/WebService']:shouhinCd
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getShouhinCd() {
    return shouhinCd;
  }

  public void setShouhinCd(java.lang.String v) {
    
    this.shouhinCd = v;
    
  }










  public java.lang.String toString() {
  return "HachuUlimitVolBean" + "{"
             + " hachuUlimitVol=<" + getHachuUlimitVol() + ">"
             + " shouhinCd=<" + getShouhinCd() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof HachuUlimitVolBean) {
      
      HachuUlimitVolBean __obj__ =  (HachuUlimitVolBean) __other__;


      return true
            && (hachuUlimitVol==null ? __obj__.getHachuUlimitVol()==null : hachuUlimitVol.equals(__obj__.getHachuUlimitVol()))
            && (shouhinCd==null ? __obj__.getShouhinCd()==null : shouhinCd.equals(__obj__.getShouhinCd()))


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (hachuUlimitVol==null ? 0 : hachuUlimitVol.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (shouhinCd==null ? 0 : shouhinCd.hashCode()) ;
    

    return __hash__result__;
  }

}


@
