head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.38.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PriceConditionDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  値段条件　@定数定義インタフェイス(WEB3PriceConditionDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/01 SRA水落 新規作成
*/
package webbroker3.common.define;

/**
 * 値段条件　@定数定義インタフェイス。
 *
 * @@author SRA水落
 * @@version 1.0
 */
public interface WEB3PriceConditionDef
{
    /**
      * 0： DEFAULT 
      */
    public static final String DEFAULT = "0";
    
    /**
      * 1： 現在値指値注文
      */
    public static final String PRESENT_VALUE_LIMIT_PRICE_ORDER = "1";
    
    /**
      * 3： 優先指値注文
      */
    public static final String PRIORITY_LIMIT_PRICE_ORDER = "3";
    
    /**
      * 5： 成行残数指値注文
      */
    public static final String PARTIALLY_LIMIT_PRICE_ORDER = "5";
    
    /**
      * 7： 成行残数取消注文
      */
    public static final String PARTIALLY_CANCEL_ORDER = "7";
}@
