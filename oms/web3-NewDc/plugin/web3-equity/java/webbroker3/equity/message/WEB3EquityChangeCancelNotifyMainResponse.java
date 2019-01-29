head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityChangeCancelNotifyMainResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式訂正取消通知メインレスポンス(WEB3EquityChangeCancelNotifyMainResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/06 岡村和明 (SRA) 新規作成
                   2004/12/21 岡村和明(SRA) JavaDoc修正
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3BackResponse;

/**
 * （株式訂正取消通知メインレスポンス）。<br>
 * <br>
 * 株式訂正取消通知メインレスポンスクラス
 * @@author 岡村和明
 * @@version 1.0
 */
public class WEB3EquityChangeCancelNotifyMainResponse extends WEB3BackResponse
{

    /**
     * <p>（ポリモルフィックタイプ）。</p>
     */
    public static final String PTYPE = "equity_changeCancelNotifyMain";

    /**
     * <p>（シリアルバージョンUID）。</p>
     */
    public static final long serialVersionUID = 200412060000L;

    /**
     * <p>（株式訂正取消通知メインレスポンス）。</p>
     * <p>コンストラクタ。</p>
     */
    public WEB3EquityChangeCancelNotifyMainResponse()
    {
    }

    /**
     * <p>（株式訂正取消通知メインレスポンス）。</p>
     * <p>コンストラクタ。</p>
     * @@param l_request 株式訂正取消通知メインリクエスト
     */
    public WEB3EquityChangeCancelNotifyMainResponse(WEB3EquityChangeCancelNotifyMainRequest l_request)
    {
        super(l_request);
    }
}
@
