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
filename	WEB3EquityOrderAcceptRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文受付リクエストクラス(WEB3EquityOrderAcceptRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/22 洪華 (中訊) 新規作成
                   2004/12/06 岡村和明(SRA) 残案件対応 Ｎｏ.３３０
                   2004/12/21 岡村和明(SRA) JavaDoc修正
                   2005/03/09 劉 (FLJ) スレッドNo追加
*/

package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式注文受付リクエスト）。</br>
 * <br>
 * 株式注文受付リクエストクラス
 * @@version 1.0
 */
public class WEB3EquityOrderAcceptRequest extends WEB3BackRequest
{

    /**
     * <p>（ポリモルフィックタイプ）。</p>
     */
    public static final String PTYPE = "equity_order_accept";

    /**
     * <p>（シリアルバージョンUID）。</p>
     */
    public static final long serialVersionUID = 200402241330L;

    /**
     * <p>（識別コードプレフィクス一覧）。</p>
     * <p>識別コードプレフィクス一覧。</p>
     */
    public String[] orderRequestNumberPrefixGroup;

    /**
     * スレッドNo<BR>
     */
    public Long threadNo;

    /**
     * <p>（ログ出力ユーティリティ）。</p>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderAcceptRequest.class);

    /**
     * <p>（株式注文受付リクエスト）。</p>
     * <p>コンストラクタ。</p>
     */
    public WEB3EquityOrderAcceptRequest()
    {
        super();
    }

    /**
     * <p>（createレスポンス）。</p>
     * <p>株式注文受付レスポンスを生成して返す。</p>
     * @@return 株式注文受付レスポンス
     */
    public WEB3BackResponse createResponse()
    {
        return new WEB3EquityOrderAcceptResponse(this);
    }

    /**
     * <p>（validate）。</p>
     * <p>当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@識別コードプレフィクス一覧のチェック<BR>
     * 　@this.識別コードプレフィクス一覧＝nullまたは要素数が0の場合、<BR>
     * 　@「識別コードプレフィクス一覧の指定なし」の例外をthrowする。<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:　@ WEB3ErrorCatalog.BUSINESS_ERROR_01291</p>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        if (this.orderRequestNumberPrefixGroup == null ||
            this.orderRequestNumberPrefixGroup.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01291,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (this.threadNo == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01974,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
