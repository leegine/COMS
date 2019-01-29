head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityCarryOverSkipResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  注文繰越スキップ銘柄通知レスポンス(WEB3EquityCarryOverSkipResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 鄒政 (中訊) 新規作成
                   2004/12/06 岡村和明(SRA) 残案件対応 Ｎｏ.３３５
                   2004/12/21 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity.message;

import webbroker3.common.message.WEB3BackResponse;

/**
 * （株式注文繰越スキップ銘柄通知レスポンス）。<br>
 * <br>
 * 株式注文繰越スキップ銘柄通知レスポンスクラス
 * @@version 1.0
 */
public class WEB3EquityCarryOverSkipResponse extends WEB3BackResponse
{

    /**
     * <p>（serialVersionUID）。</p>
     */
    public static final long serialVersionUID = 200405231030L;

    /**
     * <p>（PTYPE）。</p>
     */
    public static final String PTYPE = "equity_carryover_skip";

    /**
     * <p>（株式注文繰越スキップ銘柄通知レスポンス）。</p>
     * <p>コンストラクタ。</p>
     * @@param l_request 株式注文繰越スキップ銘柄通知リクエスト
     */
    public WEB3EquityCarryOverSkipResponse(WEB3EquityCarryOverSkipRequest l_request)
    {
        super(l_request);
    }
    
    /**
     * <p>（株式注文繰越スキップ銘柄通知レスポンス）。</p>
     * <p>コンストラクタ。</p>
     */
    public WEB3EquityCarryOverSkipResponse()
    {
    }
}
@
