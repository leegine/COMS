head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.45.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TradeHistorySortDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ソート区分 定数定義インタフェイス(WEB3TradeHistorySortDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/22 趙林鵬(中訊) 新規作成
                 : 2006/12/15 何文敏(中訊) モデル068
*/

package webbroker3.tradehistory.define;

/**
 * ソート区分 定数定義インタフェイス
 *
 * @@author 趙林鵬
 * @@version 1.0
 */

public interface WEB3TradeHistorySortDivDef
{
    /**
     * 01：　@初期表示
     */
    public final static String INITIAL_DISPLAY = "01";

    /**
     * 02：　@初期表示以外
     */
    public final static String INITIAL_DISPLAY_OTHERS = "02";
    
    /**
     * 03：　@ダウンロード   
     */
    public final static String DOWNLOAD = "03";
}
@
