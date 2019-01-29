head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.32.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PerferenceMoneyDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 優遇金区分定数定義インタフェイス(WEB3PerferenceMoneyDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/26 劉江涛(sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * 優遇金区分 定数定義インタフェイス
 *
 * @@author 劉江涛
 * @@version 1.0
 */
public interface WEB3PerferenceMoneyDivDef
{

    /**
     * 0：償還優遇不可　@　@　@　@  　@　@
     */
    public final static String REDEMPTION_PREFERENCE_IMPOSSIBLE  = "0";

    /**
     * 1：可能(内枠)
     */
    public final static String POSSIBLE_PRICE_IN_LIMIT = "1";
    
    /**
     * 2：可能(外枠)半額
     */
    public final static String POSSIBLE_HALF_PRICE= "2";

    /**
     * 9：可能金額
     */
    public final static String POSSIBLE_PRICE = "9";
    
}@
