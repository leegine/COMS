head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.12.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminSLCashOutStopReleaseCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券担保ローン出金停止解除完了リクエスト(WEB3AdminSLCashOutStopReleaseCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 孫洪江 (中訊) 新規作成 仕様変更モデル764 モデル772
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (証券担保ローン出金停止解除完了リクエスト)<BR>
 * 証券担保ローン出金停止解除完了リクエストクラス<BR>
 *
 * @@author 孫洪江
 * @@version 1.0
 */
public class WEB3AdminSLCashOutStopReleaseCompleteRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_sl_cash_out_stop_release_complete";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200709131743L;

    /**
     * (口座ID)<BR>
     */
    public long accountId;

    /**
     * (暗証番号)<BR>
     */
    public String password;

    public WEB3AdminSLCashOutStopReleaseCompleteRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminSLCashOutStopReleaseCompleteResponse(this);
    }
}
@
