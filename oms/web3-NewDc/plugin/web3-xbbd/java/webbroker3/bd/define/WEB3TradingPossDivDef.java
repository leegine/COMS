head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3TradingPossDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取引可能区分リスト(WEB3TradingPossDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/27 謝旋 (中訊) 新規作成
*/

package webbroker3.bd.define;

/**
 * 取引可能区分 定数定義インタフェイス
 *
 * @@author 謝旋 (中訊)
 * @@version 1.0
 */

public interface WEB3TradingPossDivDef
{
    /**
     * 0：不可
     */
    public static final String SELL_POSS_NO = "0";

    /**
     * 1：可能
     */
    public static final String SELL_POSS = "1";

    /**
     * 2:応募枠超過
     */
    public static final String OVER_RECRUIT_LIMIT = "2";
    
}
@
