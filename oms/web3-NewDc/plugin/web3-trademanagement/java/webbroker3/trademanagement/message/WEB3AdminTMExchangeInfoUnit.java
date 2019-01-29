head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.21.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMExchangeInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 為替情報(WEB3AdminTMExchangeInfoUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/10 徐宏偉 (中訊) 新規作成
*/
package webbroker3.trademanagement.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.trademanagement.define.WEB3AdminTMRateDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (為替情報)<BR>
 * 為替情報クラス<BR>
 * <BR>
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3AdminTMExchangeInfoUnit extends Message
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMExchangeInfoUnit.class);

    /**
     * (通貨コード)<BR>
     * 通貨コード<BR>
     */
    public String currencyCode;

    /**
     * (レート区分)<BR>
     * レート区分<BR>
     * <BR>
     * 0：基準為替<BR>
     * 1：約定為替<BR>
     */
    public String rateDiv;

    /**
     * (売付為替レート)<BR>
     * 売付為替レート<BR>
     * <BR>
     * ※登録入力レスポンスでセットされる値は、変更前の値。<BR>
     * それ以外でセットされる値は、変更後の値。 <BR>
     * （入力されてない場合は、null）<BR>
     */
    public String sellExchangeRate;

    /**
     * (買付為替レート)<BR>
     * 買付為替レート<BR>
     * <BR>
     * ※登録入力レスポンスでセットされる値は、変更前の値。<BR>
     * それ以外でセットされる値は、変更後の値。 <BR>
     * （入力されてない場合は、null）<BR>
     */
    public String buyExchangeRate;

    /**
     * (登録日時)<BR>
     * 登録日時<BR>
     */
    public Date registrationTime;

    /**
     * コンストラクタ
     */
    public WEB3AdminTMExchangeInfoUnit()
    {

    }

    /**
     * 為替情報データの整合性をチェックする。<BR>
     * <BR>
     * １）　@売付為替レートのチェック<BR>
     * 　@入力がある場合（売付為替レート != null）のみ、以下の処理実施<BR>
     * <BR>
     * 　@１－１）　@数値でない場合、例外※をスローする。<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02013<BR>
     * <BR>
     * 　@１－２）　@数値に変換した時の有効桁数が、整数部３桁，小数部４桁の範囲外であれば、<BR>
     * 　@　@　@　@　@　@例外※をスローする。<BR>
     * 　@１－３）　@売付為替レート <= 0 の場合、例外※をスローする。 <BR>
     * 　@　@※レート区分によって例外メッセージを分ける <BR>
     * 　@　@（レート区分 == ”基準為替”）の場合、「売付基準為替エラー」<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02014<BR>　@
     * <BR>　@
     * 　@　@（レート区分 == ”約定為替”）の場合、「売付約定為替エラー」<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02015<BR>
     * <BR>
     * ２）　@買付為替レートのチェック<BR>
     * 　@入力がある場合（買付為替レート != null）のみ、以下の処理実施<BR>
     * <BR>
     * 　@２－１）　@数値でない場合、例外※をスローする。<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02016<BR>
     * <BR>
     * 　@２－２）　@数値に変換した時の有効桁数が、整数部３桁，小数部４桁の範囲外であれば、<BR>
     * 　@　@　@　@　@　@例外※をスローする。<BR>
     * 　@２－３）　@買付為替レート <= 0 の場合、例外※をスローする。<BR>
     * 　@　@※レート区分によって例外メッセージを分ける <BR>
     * 　@　@（レート区分 == ”基準為替”）の場合、「買付基準為替エラー」<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02017<BR>　@
     * <BR>　@
     * 　@　@（レート区分 == ”約定為替”）の場合、「買付約定為替エラー」<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02018<BR>　@
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // １）　@売付為替レートのチェック
        // 　@入力がある場合（売付為替レート != null）のみ、以下の処理実施
        if (this.sellExchangeRate != null)
        {
            // 　@１－１）　@数値でない場合、例外※をスローする。
            if (!WEB3StringTypeUtility.isNumber(this.sellExchangeRate))
            {
                //※レート区分によって例外メッセージを分ける
                log.debug("売付為替レート数値でない場合");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02013,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            // 　@１－２）　@数値に変換した時の有効桁数が、整数部３桁，小数部４桁の範囲外であれば、
            // 　@　@　@　@　@　@例外※をスローする。
            int l_intIntegerCnt = WEB3StringTypeUtility.getIntegerDigits(this.sellExchangeRate);
            int l_intFractionCnt = WEB3StringTypeUtility.getFractionDigits(this.sellExchangeRate);

            if (l_intIntegerCnt > 3 || l_intFractionCnt > 4)
            {
                //※レート区分によって例外メッセージを分ける
                //（レート区分 == ”基準為替”）の場合、「売付基準為替エラー」
                if (WEB3AdminTMRateDivDef.BASE_EXCHANGE.equals(this.rateDiv))
                {
                    log.debug("（レート区分 == ”基準為替”）の場合");
                    log.debug("数値に変換した時の有効桁数が、整数部３桁，小数部４桁の範囲外");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02014,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                //（レート区分 == ”約定為替”）の場合、「売付約定為替エラー」
                else if (WEB3AdminTMRateDivDef.EXECUTED_EXCHANGE.equals(this.rateDiv))
                {
                    log.debug("（レート区分 == ”約定為替”）の場合");
                    log.debug("数値に変換した時の有効桁数が、整数部３桁，小数部４桁の範囲外");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02015,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
            }
            // 　@１－３）　@売付為替レート <= 0 の場合、例外※をスローする。
            double l_dblSellExecRate = Double.parseDouble(this.sellExchangeRate);
            if (l_dblSellExecRate <= 0)
            {
                //（レート区分 == ”基準為替”）の場合、「売付基準為替エラー」
                if (WEB3AdminTMRateDivDef.BASE_EXCHANGE.equals(this.rateDiv))
                {
                    log.debug("（レート区分 == ”基準為替”）の場合");
                    log.debug("売付為替レート <= 0 の場合");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02014,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                //（レート区分 == ”約定為替”）の場合、「売付約定為替エラー」
                else if (WEB3AdminTMRateDivDef.EXECUTED_EXCHANGE.equals(this.rateDiv))
                {
                    log.debug("（レート区分 == ”約定為替”）の場合");
                    log.debug("売付為替レート <= 0 の場合");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02015,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
            }
        }
        // ２）　@買付為替レートのチェック
        // 　@入力がある場合（買付為替レート != null）のみ、以下の処理実施
        // 　@２－１）　@数値でない場合、例外※をスローする。
        if (this.buyExchangeRate != null)
        {
            if (!WEB3StringTypeUtility.isNumber(this.buyExchangeRate))
            {
                log.debug("買付為替レート数値でない場合");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02016,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            // 　@２－２）　@数値に変換した時の有効桁数が、整数部３桁，小数部４桁の範囲外であれば、
            // 　@　@　@　@　@　@例外※をスローする。
            int l_intIntegerCnt = WEB3StringTypeUtility.getIntegerDigits(this.buyExchangeRate);
            int l_intFractionCnt = WEB3StringTypeUtility.getFractionDigits(this.buyExchangeRate);

            if (l_intIntegerCnt > 3 || l_intFractionCnt > 4)
            {
                //※レート区分によって例外メッセージを分ける
                //（レート区分 == ”基準為替”）の場合、「買付基準為替エラー」
                if (WEB3AdminTMRateDivDef.BASE_EXCHANGE.equals(this.rateDiv))
                {
                    log.debug("（レート区分 == ”基準為替”）の場合");
                    log.debug("数値に変換した時の有効桁数が、整数部３桁，小数部４桁の範囲外");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02017,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                //（レート区分 == ”約定為替”）の場合、「買付約定為替エラー」
                else if (WEB3AdminTMRateDivDef.EXECUTED_EXCHANGE.equals(this.rateDiv))
                {
                    log.debug("（レート区分 == ”約定為替”）の場合");
                    log.debug("数値に変換した時の有効桁数が、整数部３桁，小数部４桁の範囲外");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02018,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
            }
            // 　@２－３）　@買付為替レート <= 0 の場合、例外※をスローする
            double l_dblSellExecRate = Double.parseDouble(this.buyExchangeRate);
            if (l_dblSellExecRate <= 0)
            {
                //（レート区分 == ”基準為替”）の場合、「買付基準為替エラー」
                if (WEB3AdminTMRateDivDef.BASE_EXCHANGE.equals(this.rateDiv))
                {
                    log.debug("（レート区分 == ”基準為替”）の場合");
                    log.debug("買付為替レート <= 0 の場合");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02017,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                //（レート区分 == ”約定為替”）の場合、「買付約定為替エラー」
                else if (WEB3AdminTMRateDivDef.EXECUTED_EXCHANGE.equals(this.rateDiv))
                {
                    log.debug("（レート区分 == ”約定為替”）の場合");
                    log.debug("買付為替レート <= 0 の場合");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02018,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
