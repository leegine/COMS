head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.45.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TradeHistoryTradeCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3TradeHistoryTradeCodeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/28  範慧琴(sinocom)　@新規作成
                   2006/01/23 李志強(日本中訊) 仕様変更・モデル043
                   2006/04/25 凌建平(中訊) 仕様変更・モデル048
                   2006/05/01 鈴木(SCS) calc口座残高、create取引履歴情報 修正対応
*/
package webbroker3.tradehistory.define;

/**
 *取引コード 定数定義インタフェイス
 *                                                                     
 * @@author 範慧琴
 * @@version 1.0
 */
public interface WEB3TradeHistoryTradeCodeDef
{

    /**
     * 53：現引・現渡
     */
    public static final String SWAP = "53";
    
    /**
     *A2
     */
    public static final String TRADE_CODE_A2 = "A2";
    
    /**
     *A3
     */
    public static final String TRADE_CODE_A3 = "A3";    

    /**
     * A1
     */
    public static final String TRADE_CODE_A1 = "A1";

    //SCS鈴木修正　@START
    /**
     * 11
     */
    public static final String TRADE_CODE_11 = "11";
    //SCS鈴木修正　@END

}
@
