head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.19.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesCloseMarginCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物返済注文完了レスポンス(WEB3FuturesCloseMarginCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/19 鄒鋭 (中訊) 新規作成
*/

package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (株価指数先物返済注文完了レスポンス)<BR>
 * 株価指数先物返済完了レスポンスクラス
 * @@author 鄒鋭
 * @@version 1.0
 */
public class WEB3FuturesCloseMarginCompleteResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "futures_closeMarginComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200407191548L;

    /**
     * (更新時間)<BR>
     */
    public Date lastUpdatedTimestamp;

    /**
     * (識別番号)<BR>
     * 注文履歴ＩＤ
     */
    public String orderActionId;

    /**
     * @@roseuid 40F7AE180251
     */
    public WEB3FuturesCloseMarginCompleteResponse()
    {

    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3FuturesCloseMarginCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
