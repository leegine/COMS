head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.57.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminSLCashOutStopReleaseCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券担保ローン出金停止解除完了レスポンス(WEB3AdminSLCashOutStopReleaseCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 孫洪江 (中訊) 新規作成 仕様変更モデル764
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (証券担保ローン出金停止解除完了レスポンス)<BR>
 * 証券担保ローン出金停止解除完了レスポンスクラス<BR>
 *
 * @@author 孫洪江
 * @@version 1.0
 */
public class WEB3AdminSLCashOutStopReleaseCompleteResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_sl_cash_out_stop_release_complete";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200709131839L;

    public WEB3AdminSLCashOutStopReleaseCompleteResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminSLCashOutStopReleaseCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
