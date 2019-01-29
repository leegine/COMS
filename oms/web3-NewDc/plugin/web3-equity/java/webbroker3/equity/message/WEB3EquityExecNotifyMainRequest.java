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
filename	WEB3EquityExecNotifyMainRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式出来通知メインリクエスト(WEB3EquityExecNotifyMainRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/01 中尾寿彦 (SRA) 新規作成
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
 * （株式出来通知メインリクエスト）。<br>
 * <br>
 * 株式出来通知メインリクエストクラス
 * @@author 中尾寿彦
 * @@version 1.0
 */
public class WEB3EquityExecNotifyMainRequest extends WEB3BackRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_execNotifyMain";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412010000L;

    /**
     * 識別コードプレフィクス一覧<BR>
     */
    public String[] orderRequestNumberPrefixGroup;

    /**
     * スレッドNo<BR>
     */
    public Long threadNo;

    /**
     * ログ出力ユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityExecNotifyMainRequest.class);

    /**
     * デフォルトコンストラクタ。<BR>
     */
    public WEB3EquityExecNotifyMainRequest()
    {
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return WEB3BackResponse
     */
    public WEB3BackResponse createResponse()
    {
        return new WEB3EquityExecNotifyMainResponse(this);
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
