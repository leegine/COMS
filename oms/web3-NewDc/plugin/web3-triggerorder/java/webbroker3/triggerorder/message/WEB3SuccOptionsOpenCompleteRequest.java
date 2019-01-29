head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.48.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccOptionsOpenCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）株価指数オプション新規建注文完了リクエスト(WEB3SuccOptionsOpenCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/14 金シュ (中訊) 新規作成 モデル No.263,304
*/

package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3ReserveOrderTradingTypeDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.message.WEB3OptionsOpenMarginCompleteRequest;
import webbroker3.util.WEB3LogUtility;


/**
 * (（連続）株価指数オプション新規建注文完了リクエスト)<BR>
 * （連続）株価指数オプション新規建注文完了リクエストクラス<BR>
 * <BR>
 * @@author 金シュ
 * @@version 1.0
 */
public class WEB3SuccOptionsOpenCompleteRequest extends WEB3OptionsOpenMarginCompleteRequest
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SuccOptionsOpenCompleteRequest.class);

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803141558L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_options_open_complete";

    /**
     * (連続注文共通情報)<BR>
     * 連続注文共通情報。<BR>
     */
    public WEB3SuccCommonInfo succCommonInfo;

    /**
     * (単価調整値情報)<BR>
     * 単価調整値情報。<BR>
     * ±指値が指定された場合のみセット。<BR>
     */
    public WEB3SuccPriceAdjustmentValueInfo priceAdjustmentValueInfo;

    /**
     * (調整後単価)<BR>
     * 調整後単価。 <BR>
     * ±指値が指定された場合のみセット。<BR>
     */
    public String afterAdjustmentPrice;

    /**
     * @@roseuid 47D9F2CA02BB
     */
    public WEB3SuccOptionsOpenCompleteRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SuccOptionsOpenCompleteResponse(this);
    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@super.validate()をコールする。<BR>
     * <BR>
     * ２）　@連続注文共通情報チェック<BR>
     * 　@２－１）　@連続注文共通情報＝nullの場合、<BR>
     * 　@　@　@　@　@　@「連続注文共通情報指定なし」の例外をスローする。<BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@ tag: BUSINESS_ERROR_02251<BR>
     * <BR>
     * 　@２－２）　@連続注文共通情報.validate()をコールする。<BR>
     * <BR>
     * 　@２－３）　@連続注文共通情報.連続注文取引区分が以下の値以外の場合、<BR>
     * 　@　@　@　@　@　@「連続注文取引区分の値が処理対象外」の例外をthrowする。<BR>
     * <BR>
     * 　@　@　@　@　@"OP新規建（前提注文）"<BR>
     * 　@　@　@　@　@"OP新規建"<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@ tag: BUSINESS_ERROR_02252<BR>
     * <BR>
     * 　@　@　@　@　@※コード値は、先物OP予約注文単位テーブルを参照。<BR>
     * <BR>
     * ３）　@連続注文単価調整値情報チェック<BR>
     * 　@３－１）　@連続注文単価調整値情報≠nullの場合、<BR>
     * 　@　@　@　@　@　@連続注文単価調整値情報.validate()をコールする。<BR>
     * <BR>
     * 　@３－２）　@連続注文単価調整値情報≠nullの場合、<BR>
     * 　@　@　@　@　@　@連続注文共通情報.連続注文取引区分≠"OP新規建（前提注文）"であれば<BR>
     * 　@　@　@　@　@　@「連続注文取引区分が、連続注文の±指値指定不可の区分」の例外をthrowする。<BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@ tag: BUSINESS_ERROR_02253<BR>
     * <BR>
     * 　@３－３）　@連続注文単価調整値情報≠nullの場合、<BR>
     * 　@　@　@　@　@　@注文単価区分≠"成行"の場合は「単価調整値と注文単価区分の指定が不整合」の<BR>
     * 　@　@　@　@　@　@例外をthrowする。<BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@ tag: BUSINESS_ERROR_02254<BR>
     * <BR>
     * ４）　@連続注文・注文条件チェック<BR>
     * 　@super.validate連続注文()をコールする。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47B0E5FF0014
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //１）　@super.validate()をコールする。
        super.validate();

        //２）　@連続注文共通情報チェック
        // 　@２－１）　@連続注文共通情報＝nullの場合、
        // 　@　@　@　@　@　@「連続注文共通情報指定なし」の例外をスローする。
        if (this.succCommonInfo == null)
        {
            log.debug("連続注文共通情報が未指定です。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02251,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "連続注文共通情報が未指定です。");
        }

        //２－２）　@連続注文共通情報.validate()をコールする。
        this.succCommonInfo.validate();

        //２－３）　@連続注文共通情報.連続注文取引区分が以下の値以外の場合、
        // 　@　@　@　@　@　@「連続注文取引区分の値が処理対象外」の例外をthrowする。
        // 　@　@　@　@　@"OP新規建（前提注文）"
        // 　@　@　@　@　@"OP新規建"
        // 　@　@　@　@　@※コード値は、先物OP予約注文単位テーブルを参照。
        if (!WEB3ReserveOrderTradingTypeDef.OPEN_OP_ASSUMPTION_ORDER.equals(
                this.succCommonInfo.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.OPEN_OP.equals(
                this.succCommonInfo.succTradingType))
        {
            log.debug("連続注文取引区分の値が処理対象外");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02252,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "連続注文取引区分の値が処理対象外");
        }

        //３）　@連続注文単価調整値情報チェック
        // 　@３－１）　@連続注文単価調整値情報≠nullの場合、
        // 　@　@　@　@　@　@連続注文単価調整値情報.validate()をコールする。
        if (this.priceAdjustmentValueInfo != null)
        {
            this.priceAdjustmentValueInfo.validate();

            //３－２）　@連続注文単価調整値情報≠nullの場合、
            // 　@　@　@　@　@　@連続注文共通情報.連続注文取引区分≠"OP新規建（前提注文）"であれば
            // 　@　@　@　@　@　@「連続注文取引区分が、連続注文の±指値指定不可の区分」の例外をthrowする。
            if (!WEB3ReserveOrderTradingTypeDef.OPEN_OP_ASSUMPTION_ORDER.equals(
                this.succCommonInfo.succTradingType))
            {
                log.debug("連続注文取引区分が、連続注文の±指値指定不可の区分");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02253,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "連続注文取引区分が、連続注文の±指値指定不可の区分");
            }

            //３－３）　@連続注文単価調整値情報≠nullの場合、
            // 　@　@　@　@　@　@注文単価区分≠"成行"の場合は「単価調整値と注文単価区分の指定が不整合」の
            // 　@　@　@　@　@　@例外をthrowする。
            if (!WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.orderPriceDiv))
            {
                log.debug("単価調整値と注文単価区分の指定が不整合です。");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02254,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "単価調整値と注文単価区分の指定が不整合です。");
            }
        }

        //４）　@連続注文・注文条件チェック
        // 　@super.validate連続注文()をコールする。
        super.validateSuccOrder();
        log.exiting(STR_METHOD_NAME);
    }
}
@
