head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.43.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccFuturesCancelConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :（連続）株価指数先物取消注文確認レスポンス(WEB3SuccFuturesCancelConfirmResponse.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/03/12 楊夫志(中訊) 新規作成モデル272,303
 */
package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.ifo.message.WEB3FuturesCancelConfirmResponse;

/**
 * (（連続）株価指数先物取消注文確認レスポンス)<BR>
 * （連続）株価指数先物取消注文確認レスポンスクラス<BR>
 * <BR>
 * @@author 楊夫志
 * @@version 1.0
 */
public class WEB3SuccFuturesCancelConfirmResponse extends WEB3FuturesCancelConfirmResponse
{

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803121738L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_futures_cancel_confirm";

    /**
     * (単価調整値情報)<BR>
     * 単価調整値情報。<BR>
     * ±指値が指定された場合のみセット。<BR>
     */
    public WEB3SuccPriceAdjustmentValueInfo priceAdjustmentValueInfo;

    /**
     * @@roseuid 47D65936035B
     */
    public WEB3SuccFuturesCancelConfirmResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成<BR>
     * @@param l_request -リクエストオブジェクト
     */
    public WEB3SuccFuturesCancelConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
