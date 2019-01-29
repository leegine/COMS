head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.02.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3HistorySettleDetailRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright          : (株)大和総研 証券ソリューションシステム第二部
File Name          : 決済明細リクエスト(WEB3HistorySettleDetailRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/25 王敏 (中訊) 新規作成
*/

package webbroker3.tradehistory.message;

import java.util.Date;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (決済明細リクエスト)<BR>
 * 決済明細リクエストクラス
 * @@author 王敏
 * @@version 1.0
 */
public class WEB3HistorySettleDetailRequest extends WEB3GenRequest
{
    /**
     * ログ出力ユーティリティ。
     */
    private  static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3HistorySettleDetailRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "tradeHistory_settleDetail";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410221704L;

    /**
     * (明細管理番号)<BR>
     * 明細管理番号<BR>
     */
    public String detailsManageNo;

    /**
     * (翻訳摘要名)<BR>
     * 翻訳摘要名<BR>
     */
    public String remarkName;

    /**
     * (銘柄名)<BR>
     * 銘柄名<BR>
     */
    public String productName;

    /**
     * (数量)<BR>
     * 数量<BR>
     */
    public String quantity;

    /**
     * (受渡日)<BR>
     * 受渡日<BR>
     */
    public Date deliveryDate;

    /**
     * @@roseuid 41789C49008C
     */
    public WEB3HistorySettleDetailRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@明細管理番号チェック<BR>
     * 　@　@　@this.明細管理番号 == nullの場合は、<BR>
     * 　@　@　@「明細管理番号がnull」の例外をスローする。<BR>
     *         class         :  WEB3BusinessLayerException           <BR>
     *         tag            :   BUSINESS_ERROR_01060             <BR>
     * <BR>
     * ２）　@翻訳摘要名チェック<BR>
     * 　@　@　@this.翻訳摘要名 == nullの場合は、<BR>
     * 　@　@　@「翻訳摘要名がnull」の例外をスローする。<BR>
     *         class         :  WEB3BusinessLayerException           <BR>
     *         tag            :  BUSINESS_ERROR_01061              <BR>
     * <BR>
     * ３）　@銘柄名チェック<BR>
     * 　@　@　@this.銘柄名 == nullの場合は、<BR>
     * 　@　@　@「銘柄名がnull」の例外をスローする。<BR>
     *         class         :  WEB3BusinessLayerException           <BR>
     *         tag            :  BUSINESS_ERROR_01062              <BR>
     * <BR>
     * ４）　@数量チェック<BR>
     * 　@　@　@this.数量 == nullの場合は、<BR>
     * 　@　@　@「数量がnull」の例外をスローする。<BR>
     *         class         :  WEB3BusinessLayerException           <BR>
     *         tag            :  BUSINESS_ERROR_01071           <BR>
     * <BR>
     * ５）　@受渡日チェック<BR>
     * 　@　@　@this.受渡日 == nullの場合は、<BR>
     * 　@　@　@「受渡日がnull」の例外をスローする。<BR>
     *        class         :  WEB3BusinessLayerException           <BR>
     *        tag            :  BUSINESS_ERROR_01079              <BR>
     * <BR>
     * @@roseuid 413419B0034F
     */
    public void validate() throws  WEB3BaseException
    {
        final  String  STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        // 　@明細管理番号チェック
        //   「明細管理番号がnull」の例外をスローする
        if (this.detailsManageNo == null)
        {
            //例外
            log.error("「明細管理番号がnull」の例外をスローする。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01060,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        // 　@翻訳摘要名チェック
        //   「翻訳摘要名がnull」の例外をスローする
        if (this.remarkName == null)
        {
            //例外
            log.error("「翻訳摘要名がnull」の例外をスローする。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01061,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        //   銘柄名チェック
        //   「銘柄名がnull」の例外をスローする
        if (this.productName == null)
        {
            //例外
            log.error("「銘柄名がnull」の例外をスローする。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01062,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        //   数量チェック
        //   「数量がnull」の例外をスローする
        if (this.quantity == null)
        {
            //例外
            log.error("「数量がnull」の例外をスローする。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01071,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        //   受渡日チェック
        //   「受渡日がnull」の例外をスローする
        if (this.deliveryDate == null)
        {
            //例外
            log.error("「受渡日がnull」の例外をスローする。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01079,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 41789C490138
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3HistorySettleDetailResponse(this);
    }
}
@
