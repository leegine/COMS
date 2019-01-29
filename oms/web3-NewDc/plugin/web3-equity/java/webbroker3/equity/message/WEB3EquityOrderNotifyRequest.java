head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderNotifyRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式注文通知リクエスト(WEB3EquityOrderNotifyRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 欒学峰 (中訊) 新規作成
Revesion History : 2004/12/06 岡村和明(SRA) 残案件対応 Ｎｏ.３３７
*/

package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * （現物株式注文通知リクエスト）。<BR>
 * <BR>
 * 現物株式注文受信要求　@リクエストデータクラス
 * @@version 1.0
 */
public class WEB3EquityOrderNotifyRequest extends WEB3BackRequest
{

    /**
     * ログ出力ユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderNotifyRequest.class);

    public static final String PTYPE = "equity_order_notify";

    public static final long serialVersionUID = 200402041600L;

    /**
     * 識別コードプレフィクス一覧<BR>
     */
    public String[] orderRequestNumberPrefixGroup;

    /**
     * @@roseuid 40B40ADD0119
     */
    public WEB3EquityOrderNotifyRequest()
    {

    }

    /**
     * @@return webbroker3.common.message.WEB3BackResponse
     * @@roseuid 40B40ADD0167
     */
    public WEB3BackResponse createResponse()
    {
        return new WEB3EquityOrderNotifyResponse(this);
    }
    
    /**
     * （validate）<BR>
     * <BR>
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@識別コードプレフィクス一覧のチェック<BR>
     * 　@this.識別コードプレフィクス一覧＝nullまたは要素数が0の場合、<BR>
     * 　@「識別コードプレフィクス一覧の指定なし」の例外をthrowする。<BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        log.debug("識別コードプレフィクス一覧のチェック");
        if (this.orderRequestNumberPrefixGroup == null
            || this.orderRequestNumberPrefixGroup.length == 0)
        {
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01291,
                            this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
