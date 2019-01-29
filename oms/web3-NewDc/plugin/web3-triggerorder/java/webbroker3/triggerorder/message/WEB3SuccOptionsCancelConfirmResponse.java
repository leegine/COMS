head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.48.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccOptionsCancelConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）株価指数オプション取消注文確認レスポンス(WEB3SuccOptionsCancelConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/14 金シュ (中訊) 新規作成 モデル No.263,305
*/

package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.ifo.message.WEB3OptionsCancelConfirmResponse;


/**
 * (（連続）株価指数オプション取消注文確認レスポンス)<BR>
 * （連続）株価指数オプション取消注文確認レスポンスクラス<BR>
 * <BR>
 * @@author 金シュ
 * @@version 1.0
 */
public class WEB3SuccOptionsCancelConfirmResponse extends WEB3OptionsCancelConfirmResponse
{
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803141425L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_options_cancel_confirm";

    /**
     * (単価調整値情報)<BR>
     * 単価調整値情報。<BR>
     * ±指値が指定された場合のみセット。<BR>
     */
    public WEB3SuccPriceAdjustmentValueInfo priceAdjustmentValueInfo;

    /**
     * @@roseuid 47D9F2C900E6
     */
    public WEB3SuccOptionsCancelConfirmResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する<BR>
     * @@param l_request -リクエストオブジェクト
     */
    public WEB3SuccOptionsCancelConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
