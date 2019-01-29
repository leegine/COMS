head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.09.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashinNoticeSearchRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金通知検索リクエスト(WEB3AdminMutualConditionsInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/1/21 韋念瓊  (中訊) 新規作成
                 : 2006/8/23 車進(中訊) 仕様変更 モデル 614
*/
package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * 入金通知検索リクエスト<BR>
 *
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */

public class WEB3AdminAioCashinNoticeSearchRequest extends WEB3GenRequest
{

    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_aio_cashin_notice_search";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200601211056L;

    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String[] branchCode;

    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     */
    public String accountCode;

    /**
     * (勘定日)<BR>
     * 勘定日<BR>
     */
    public Date settlementDate;

    /**
     * (振込依頼人コード)<BR>
     * 振込依頼人コード<BR>
     */
    public String clientCode;

    /**
     * (処理日時(FROM))<BR>
     * 処理日時(FROM)<BR>
     */
    public Date transactionDateFrom;

    /**
     * (処理日時(TO))<BR>
     * 処理日時(TO)<BR>
     */
    public Date transactionDateTo;

    /**
     * (処理区分)<BR>
     * 処理区分<BR>
     */
    public String transactionDiv;

    /**
     * (要求ページ番号)<BR>
     * 要求ページ番号<BR>
     */
    public String pageIndex;

    /**
     * (ページ内表示行数)<BR>
     * ページ内表示行数<BR>
     */
    public String pageSize;

    /**
     * (ソートキー)<BR>
     * ソートキー<BR>
     */
    public WEB3AioCashinNoticeSortKey[] sortKeys;

    /**
     * (通貨コード)<BR>
     * 通貨コード<BR>
     */
    public String currencyCode;

    /**
     * ログ出力ユーティリティ。
     */
    public static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioCashinNoticeSearchRequest.class);

    /**
     * (入金通知検索リクエスト)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40DF7B2003DF
     */
    public WEB3AdminAioCashinNoticeSearchRequest()
    {
    }

    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 入金通知検索レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40DF7B460371
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAioCashinNoticeSearchResponse(this);
    }

    /**
     * １）部店コードの配列と要素がnullでないことをチェック
     * nullの場合「部店コードが未指定」エラーをスローする。
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00833 <BR>
     * ２）入金通知ソートキーの配列件数分LOOPし
     * 入金通知ソートキー.validate()を呼ぶ。<BR>

     * @@roseuid 40DF7B460381
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //１）部店コードの配列と要素がnullでないことをチェック
        //nullの場合「部店コードが未指定」エラーをスローする。
        if (this.branchCode == null)
        {
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードが未指定です。");
        }
        else
        {
            for (int i = 0; i < this.branchCode.length; i++)
            {
                if (WEB3StringTypeUtility.isEmpty(this.branchCode[i]))
                {
                log.debug("部店コードが未指定です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "部店コードが未指定です。");
                }
            }
        }

        //２）勘定日がnullでないことをチェック
        //nullの場合「日付或いは日付区分チェック」エラーをスローする。
        //(エラーメッセージのみ勘定日であることを分かりやすく変更)
        if (this.settlementDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02157,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "勘定日が未指定です。");
        }



        //３）入金通知ソートキーの配列件数分LOOPし
        //入金通知ソートキー.validate()を呼ぶ。
        for (int i = 0; i < this.sortKeys.length; i++)
        {
            sortKeys[i].validate();
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
