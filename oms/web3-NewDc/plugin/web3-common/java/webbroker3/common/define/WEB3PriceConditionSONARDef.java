head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.08.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PriceConditionSONARDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  値段条件（SONAR）　@定数定義インタフェイス(WEB3PriceConditionSONARDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/08 中尾寿彦(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 値段条件（SONAR）　@定数定義インタフェイス。
 *
 * @@author SRA中尾
 * @@version 1.0
 */
public interface WEB3PriceConditionSONARDef
{
    /**
      * ブランク： 指定無し 
      */
    public static final String DEFAULT = " ";
    
    /**
      * A： 現在値指値注文
      */
    public static final String PRESENT_VALUE_LIMIT_PRICE_ORDER = "A";
    
    /**
      * B： 優先指値注文
      */
    public static final String PRIORITY_LIMIT_PRICE_ORDER = "B";
    
    /**
      * C： 成行残数指値注文
      */
    public static final String PARTIALLY_LIMIT_PRICE_ORDER = "C";
    
    /**
      * D： 成行残数取消注文
      */
    public static final String PARTIALLY_CANCEL_ORDER = "D";
}
@
