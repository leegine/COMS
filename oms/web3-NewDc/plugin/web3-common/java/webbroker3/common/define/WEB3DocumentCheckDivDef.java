head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.53.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DocumentCheckDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 書面チェック区分定数定義インタフェイス(WEB3DocumentCheckDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/09/25 大澤喜宗@(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 書面チェック区分定数定義インタフェイス
 * 
 * @@author 大澤喜宗@
 * @@version 1.0
 */
public interface WEB3DocumentCheckDivDef 
{
    /**
     * 1 : IPO
     */
    public static final String IPO = "1";
    
    /**
     * 2 ： 投信
     */
    public static final String MF = "2";
    
    /**
     * 3 ： FX
     */ 
    public static final String FX = "3";
    
    /**
     * 4 : 金商法@
     */
    public static final String FINANCIAL_PRODUCTS_EXCHANGE_LAW = "4";
}
@
