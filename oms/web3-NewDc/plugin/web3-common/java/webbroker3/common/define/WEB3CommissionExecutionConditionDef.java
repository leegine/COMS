head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.01.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3CommissionExecutionConditionDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 委託手数料執行条件　@定数定義インタフェイス(WEB3CommissionExecutionConditionDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/12 髙橋　@良和(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 委託手数料執行条件　@定数定義インタフェイス
 *
 * @@author 髙橋　@良和(SRA)
 * @@version 1.0
 */
public interface WEB3CommissionExecutionConditionDef
{
    /**
     * 成行
     */
    public static final String MARKET_PRICE = "1";
    
    /**
     * 指値
     */
    public static final String LIMIT_PRICE = "2";
    
}
@
