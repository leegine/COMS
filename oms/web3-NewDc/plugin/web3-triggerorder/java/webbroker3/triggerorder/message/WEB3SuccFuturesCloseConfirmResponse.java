head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.42.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccFuturesCloseConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :（連続）株価指数先物返済注文確認レスポンス(WEB3SuccFuturesCloseConfirmResponse.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/03/12 楊夫志(中訊) 新規作成モデル272,303
 */
package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginConfirmResponse;

/**
 * (（連続）株価指数先物返済注文確認レスポンス)<BR>
 * （連続）株価指数先物返済注文確認レスポンスクラス<BR>
 * <BR>
 * @@author 楊夫志
 * @@version 1.0
 */
public class WEB3SuccFuturesCloseConfirmResponse extends WEB3FuturesCloseMarginConfirmResponse
{

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803121821L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_futures_close_confirm";

    /**
     * (調整後単価)<BR>
     * 調整後単価。 <BR>
     * ±指値が指定された場合のみセット。<BR>
     */
    public String afterAdjustmentPrice;

    /**
     * @@roseuid 47D6593802A0
     */
    public WEB3SuccFuturesCloseConfirmResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成<BR>
     * <BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3SuccFuturesCloseConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
