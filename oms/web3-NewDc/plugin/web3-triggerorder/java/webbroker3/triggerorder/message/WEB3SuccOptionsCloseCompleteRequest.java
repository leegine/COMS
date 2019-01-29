head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.48.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccOptionsCloseCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）株価指数オプション返済注文完了リクエスト(WEB3SuccOptionsCloseCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/14 金シュ (中訊) 新規作成 モデル No.263,305
*/

package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3ReserveOrderTradingTypeDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.message.WEB3OptionsCloseMarginCompleteRequest;
import webbroker3.util.WEB3LogUtility;


/**
 * (（連続）株価指数オプション返済注文完了リクエスト)<BR>
 * （連続）株価指数オプション返済注文完了リクエストクラス<BR>
 * <BR>
 * @@author 金シュ
 * @@version 1.0
 */
public class WEB3SuccOptionsCloseCompleteRequest extends WEB3OptionsCloseMarginCompleteRequest
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SuccOptionsCloseCompleteRequest.class);

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803141452L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_options_close_complete";

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
     * @@roseuid 47D9F2C90338
     */
    public WEB3SuccOptionsCloseCompleteRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SuccOptionsCloseCompleteResponse(this);
    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@連続注文共通情報チェック<BR>
     * 　@１－１）　@this.連続注文共通情報＝nullの場合、<BR>
     * 　@　@　@　@　@　@「連続注文共通情報指定なし」の例外をスローする。<BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@ tag: BUSINESS_ERROR_02251<BR>
     * <BR>
     * 　@１－２）　@this.連続注文共通情報.validate()をコールする。<BR>
     * <BR>
     * 　@１－３）　@this.連続注文共通情報.連続注文取引区分が以下の値以外の場合、<BR>
     * 　@　@　@　@　@　@「連続注文取引区分の値が処理対象外」の例外をthrowする。<BR>
     * <BR>
     * 　@　@　@　@　@"OP返済（前提注文）"<BR>
     * 　@　@　@　@　@"OP返済（既存残）"<BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@ tag: BUSINESS_ERROR_02252<BR>
     * <BR>
     * 　@　@　@　@　@※コード値は、先物OP予約注文単位テーブルを参照。<BR>
     * <BR>
     * ２）　@this.連続注文共通情報.連続注文取引区分=="OP返済（既存残）"の場合のみ、<BR>
     * 　@　@　@super.validate()をコールする。<BR>
     * 　@　@　@以外、super.validateAT反対取引()をコールする。<BR>
     * <BR>
     * ３）　@連続注文単価調整値情報チェック<BR>
     * 　@３－１）　@連続注文単価調整値情報≠nullの場合、<BR>
     * 　@　@　@　@　@　@連続注文単価調整値情報.validate()をコールする。<BR>
     * <BR>
     * 　@３－２）　@連続注文単価調整値情報≠nullの場合、<BR>
     * 　@　@　@　@　@　@連続注文共通情報.連続注文取引区分≠"OP返済（前提注文）"であれば<BR>
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
     * ４）　@this.連続注文共通情報.連続注文取引区分=="OP返済（前提注文）"の場合、<BR>
     * 　@　@　@リクエスト.決済順序==（null or "建日順"）の場合は、<BR>
     * 　@　@　@「連続注文・反対取引時の決済順序指定不正。」の例外をthrowする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@ tag: BUSINESS_ERROR_02306<BR>
     * <BR>
     * ５）　@連続注文・注文条件チェック<BR>
     * 　@super.validate連続注文()をコールする。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47B0FAEC0364
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //１）　@連続注文共通情報チェック
        // 　@１－１）　@this.連続注文共通情報＝nullの場合、
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

        //１－２）　@this.連続注文共通情報.validate()をコールする。
        this.succCommonInfo.validate();

        //１－３）　@this.連続注文共通情報.連続注文取引区分が以下の値以外の場合、
        // 　@　@　@　@　@　@「連続注文取引区分の値が処理対象外」の例外をthrowする。
        // 　@　@　@　@　@"OP返済（前提注文）"
        // 　@　@　@　@　@"OP返済（既存残）"
        // 　@　@　@　@　@※コード値は、先物OP予約注文単位テーブルを参照。
        if (!WEB3ReserveOrderTradingTypeDef.SETTLE_OP_ASSUMPTION_ORDER.equals(
                this.succCommonInfo.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.SETTLE_OP_EXISTING_REMAINDER.equals(
                this.succCommonInfo.succTradingType))
        {
            log.debug("連続注文取引区分の値が処理対象外");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02252,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "連続注文取引区分の値が処理対象外");
        }

        //２）　@this.連続注文共通情報.連続注文取引区分=="OP返済（既存残）"の場合のみ、
        // 　@　@　@super.validate()をコールする。
        // 　@　@　@以外、super.validateAT反対取引()をコールする。
        if (WEB3ReserveOrderTradingTypeDef.SETTLE_OP_EXISTING_REMAINDER.equals(
            this.succCommonInfo.succTradingType))
        {
            super.validate();
        }
        else
        {
            super.validateATReserveOrder();
        }

        //３）　@連続注文単価調整値情報チェック
        // 　@３－１）　@連続注文単価調整値情報≠nullの場合、
        // 　@　@　@　@　@　@連続注文単価調整値情報.validate()をコールする。
        if (this.priceAdjustmentValueInfo != null)
        {
            this.priceAdjustmentValueInfo.validate();

            //３－２）　@連続注文単価調整値情報≠nullの場合、
            // 　@　@　@　@　@　@連続注文共通情報.連続注文取引区分≠"OP返済（前提注文）"であれば
            // 　@　@　@　@　@　@「連続注文取引区分が、連続注文の±指値指定不可の区分」の例外をthrowする。
            if (!WEB3ReserveOrderTradingTypeDef.SETTLE_OP_ASSUMPTION_ORDER.equals(
                succCommonInfo.succTradingType))
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

        //４）　@this.連続注文共通情報.連続注文取引区分=="OP返済（前提注文）"の場合、
        //　@　@　@リクエスト.決済順序==（null or "建日順"）の場合は、
        // 　@　@　@「連続注文・反対取引時の決済順序指定不正。」の例外をthrowする。
        if (WEB3ReserveOrderTradingTypeDef.SETTLE_OP_ASSUMPTION_ORDER.equals(
            this.succCommonInfo.succTradingType))
        {
            if (this.closingOrder == null
                || WEB3ClosingOrderDef.OPEN_DATE.equals(this.closingOrder))
            {
                log.debug("連続注文の反対取引指定時は、決済順序区分に（ランダム／単価益順／単価損順）のいずれかを指定してください。");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02306,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "連続注文の反対取引指定時は、決済順序区分に（ランダム／単価益順／単価損順）のいずれかを指定してください。");
            }
        }

        //５）　@連続注文・注文条件チェック
        // 　@super.validate連続注文()をコールする。
        super.validateSuccOrder();
        log.exiting(STR_METHOD_NAME);
    }
}

@
