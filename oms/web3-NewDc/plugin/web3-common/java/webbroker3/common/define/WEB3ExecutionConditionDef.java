head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.49.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ExecutionConditionDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3ExecutionConditionDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 zhao-lin(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * 訂正後執行条件　@定数定義インタフェイス
 *                                                                       
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3ExecutionConditionDef
{
    /**
     * 1 : 無条件
     */
    public static final String NO_CONDITION = "1";

    /**
     * 2 : 出合
     */
    public static final String COME_TO_TERMS = "2";

    /**
     * 3 : 寄付
     */
    public static final String AT_MARKET_OPEN = "3";

    /**
     * 4 : 引け
     */
    public static final String AT_MARKET_CLOSE = "4";
    
    /**
     * 7 : 出来ずば引成(不成)
     */
    public static final String AT_MARKET_CLOSE_NOT_EXECUTED = "7";    


}
@
