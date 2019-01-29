head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.43.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminOREquityOrderExecutionRefReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・株式注文約定照会リクエスト (WEB3AdminOREquityOrderExecutionRefReferenceRequest.java)
Author Name      : Daiwa Institute of Research
                 : 2006/08/30 張騰宇(中訊) モデル 057
Revision History : 2007/10/09 何文敏(中訊) 仕様変更 モデルNo.106
Revision History : 2008/01/23 トウ鋒鋼(中訊) 仕様変更 モデルNo.109
*/
package webbroker3.adminorderexecinquiry.message;

import webbroker3.adminorderexecinquiry.define.WEB3AdminKeyItemDef;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.define.WEB3RepaymentDivDef;
import webbroker3.common.define.WEB3TaxTypeDef;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者・株式注文約定照会リクエスト)<BR>
 * <BR>
 * 管理者・株式注文約定照会リクエストクラス<BR>
 * <BR>
 * WEB3AdminOREquityOrderExecutionRefReferenceRequest<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminOREquityOrderExecutionRefReferenceRequest
    extends WEB3AdminOROrderExecutionRefCommonRequest
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_or_equity_order_execution_ref_reference";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminOREquityOrderExecutionRefReferenceRequest.class);

    /**
     * (銘柄コード)<BR>
     * <BR>
     * 銘柄コード<BR>
     * <BR>
     * productCode<BR>
     * <BR>
     */
    public String productCode = null;

    /**
     * (市場コード)<BR>
     * <BR>
     * 市場コード<BR>
     * <BR>
     * marketCode<BR>
     * <BR>
     */
    public String marketCode = null;

    /**
     * (口座区分)<BR>
     * <BR>
     * 口座区分<BR>
     * <BR>
     * 0：　@一般<BR>
     * 1：　@特定<BR>
     * 5：　@ストックオプション<BR>
     * <BR>
     */
    public String taxType = null;

    /**
     * (弁済区分)<BR>
     * <BR>
     * 弁済区分<BR>
     * <BR>
     * 1：　@制度信用<BR>
     * 2：　@一般信用<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * repaymentDiv<BR>
     * <BR>
     * 1: Def.REPAYMENT_DIV_MARGIN_SYS<BR>
     * 2: Def.REPAYMENT_DIV_MARGIN_GEN<BR>
     * <BR>
     */
    public String repaymentDiv = null;

    /**
     * (値段条件)<BR>
     * <BR>
     * 値段条件<BR>
     * <BR>
     * 0：　@指定なし<BR>
     * 1：　@現在値指値注文<BR>
     * 3：　@優先指値注文<BR>
     * 5：　@成行残数指値注文<BR>
     * 7：　@成行残数取消注文<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * priceCondType<BR>
     * <BR>
     * 0: Def.DEFAULT<BR>
     * 1: Def.PRESENT_VALUE_LIMIT_PRICE_ORDER <BR>
     * 3: Def.PRIORITY_LIMIT_PRICE_ORDER <BR>
     * 5: Def.PARTIALLY_LIMIT_PRICE_ORDER<BR>
     * 7: Def.PARTIALLY_CANCEL_ORDER <BR>
     * <BR>
     */
    public String priceCondType = null;

    /**
     * (強制決済注文フラグ)<BR>
     * <BR>
     * false:指定なし<BR>
     * true:強制決済注文<BR>
     */
    public boolean forcedSettleFlag;

    /**
     * @@roseuid 4212FB35024A
     */
    public WEB3AdminOREquityOrderExecutionRefReferenceRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）スーパークラスのvalidate()をコールする。<BR>
     * <BR>
     * ２）銘柄コードチェック<BR>
     * 　@this.銘柄コード != nullの場合、以下のチェックを行う。<BR>
     * 　@２－１）this.銘柄コードが以下の条件に該当する場合、<BR>
     * 　@　@　@　@「銘柄コードエラー」の例外をスローする。<BR>
     * 　@　@　@　@・銘柄コード != 数字<BR>
     * 　@　@　@　@・銘柄コード.length != 5<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01067<BR>
     * <BR>
     * ３）市場コードチェック<BR>
     * 　@this.市場コード != nullの場合、以下のチェックを行う。<BR>
     * 　@３－１）this.市場コードに下記の項目以外が設定されていたら、<BR>
     * 　@　@　@　@「市場コードが未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@・"東京"<BR>
     * 　@　@　@　@・"大阪"<BR>
     * 　@　@　@　@・"名古屋"<BR>
     * 　@　@　@　@・"福岡"<BR>
     * 　@　@　@　@・"札幌"<BR>
     * 　@　@　@　@・"NNM"<BR>
     * 　@　@　@　@・"JASDAQ"<BR>
     * 　@　@　@　@・"JNX-PTS"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00608<BR>
     * <BR>
     * ４）口座区分チェック<BR>
     * 　@this.口座区分 != nullの場合、以下のチェックを行う。<BR>
     * 　@４－１）this.口座区分に下記の項目以外が設定されていたら、<BR>
     * 　@　@　@　@「口座区分が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@・"一般"<BR>
     * 　@　@　@　@・"特定"<BR>
     * 　@　@　@　@・"ストックオプション" <BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00605<BR>
     * <BR>
     * ５）弁済区分チェック<BR>
     * 　@this.弁済区分 != nullの場合、以下のチェックを行う。<BR>
     * 　@５－１）this.弁済区分に下記の項目以外が設定されていたら、<BR>
     * 　@　@　@　@「弁済区分が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@・"制度信用"<BR>
     * 　@　@　@　@・"一般信用"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00629<BR>
     * <BR>
     * ６）値段条件チェック<BR>
     * 　@this.値段条件 != nullの場合、以下のチェックを行う。<BR>
     * 　@６－１）this.値段条件に下記の項目以外が設定されていたら、<BR>
     * 　@　@　@　@「値段条件が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@・"指定なし"<BR>
     * 　@　@　@　@・"現在値指値注文"<BR>
     * 　@　@　@　@・"優先指値注文"<BR>
     * 　@　@　@　@・"成行残数指値注文"<BR>
     * 　@　@　@　@・"成行残数取消注文"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01344<BR>
     * <BR>
     * ７）ソートキーチェック<BR>
     * 　@７－１）this.ソートキーの要素数分以下の処理を繰り返す。<BR>
     * 　@　@７－１－１）ソートキー.キー項目に下記の項目以外が<BR>
     * 　@　@　@設定されていたら、<BR>
     * 　@　@　@「ソートキー.キー項目が未定義の値」の例外をスローする。<BR>
     * 　@　@　@・「部店コード」<BR>
     * 　@　@　@・「顧客コード」<BR>
     * 　@　@　@・「扱者コード(SONAR)」<BR>
     * 　@　@　@・「銘柄コード」<BR>
     * 　@　@　@・「市場コード」<BR>
     * 　@　@　@・「口座区分」<BR>
     * 　@　@　@・「取引区分」<BR>
     * 　@　@　@・「注文時間」<BR>
     * 　@　@　@・「発注日」<BR>
     * 　@　@　@・「弁済区分」<BR>
     * 　@　@　@・「値段条件」<BR>
     * 　@　@　@・「執行条件」<BR>
     * 　@　@　@・「注文期限」<BR>
     * 　@　@　@・「発注条件」<BR>
     * 　@　@　@・「受渡日」<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00086<BR>
     * <BR>
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 41A5BBF101F5
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        final int l_intFive = 5;
        int l_intSortkeysLength  = 0;

        // 1-1 Call super.validate()
        super.validate();

        // 2-1 if productCode is not Numeric & length of productCode is not 5, throw Exception.
        if (this.productCode != null)
        {
            if (!(WEB3StringTypeUtility.isNumber(this.productCode))
                || (WEB3StringTypeUtility.getByteLength(this.productCode) != l_intFive))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01067,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 3-1 if marketCode is not any of Def, throw Exception.
        if (this.marketCode != null)
        {
            if ((!WEB3MarketCodeDef.TOKYO.equals(this.marketCode))
                && (!WEB3MarketCodeDef.OSAKA.equals(this.marketCode))
                && (!WEB3MarketCodeDef.NAGOYA.equals(this.marketCode))
                && (!WEB3MarketCodeDef.FUKUOKA.equals(this.marketCode))
                && (!WEB3MarketCodeDef.SAPPORO.equals(this.marketCode))
                && (!WEB3MarketCodeDef.NNM.equals(this.marketCode))
                && (!WEB3MarketCodeDef.JASDAQ.equals(this.marketCode))
                && (!WEB3MarketCodeDef.JNX_PTS.equals(this.marketCode)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 4-1 if taxType is not any of Def throw Exception.
        if (this.taxType != null)
        {
            if ((!WEB3TaxTypeDef.NORMAL.equals(this.taxType))
                && (!WEB3TaxTypeDef.SPECIAL.equals(this.taxType))
                && (!WEB3TaxTypeDef.STOCK_OPTION.equals(this.taxType)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00605,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 5-1 if repaymentDiv is not any of Def throw Exception.
        if (this.repaymentDiv != null)
        {
            if ((!WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS.equals(this.repaymentDiv))
                && (!WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(this.repaymentDiv)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00629,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 6-1 if priceCondType is not any of Def, throw Exception.
        if (this.priceCondType != null)
        {
            if ((!WEB3PriceConditionDef.DEFAULT.equals(this.priceCondType))
                && (!WEB3PriceConditionDef.PRESENT_VALUE_LIMIT_PRICE_ORDER.equals
                    (this.priceCondType))
                && (!WEB3PriceConditionDef.PRIORITY_LIMIT_PRICE_ORDER.equals(this.priceCondType))
                && (!WEB3PriceConditionDef.PARTIALLY_LIMIT_PRICE_ORDER.equals(this.priceCondType))
                && (!WEB3PriceConditionDef.PARTIALLY_CANCEL_ORDER.equals(this.priceCondType)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01344,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 7-1 if sortkeys.keyItem is not any of Def values, throw Exception.
        l_intSortkeysLength = this.sortKeys.length;
        for (int i = 0; i < l_intSortkeysLength; i++)
        {
            if ((!WEB3AdminKeyItemDef.BRANCH_CODE.equals(this.sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.ACCOUNT_CODE.equals(this.sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.SONAR_TRADER_CODE.equals(this.sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.PRODUCT_CODE.equals(this.sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.MARKET_CODE.equals(this.sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.TAX_TYPE.equals(this.sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.TRADING_TYPE.equals(this.sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.ORDER_DATE.equals(this.sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.ORDER_BIZ_DATE.equals(this.sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.REPAYMENT_DIV.equals(this.sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.PRICE_COND_TYPE.equals(this.sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.EXEC_COND_TYPE.equals(this.sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.EXPIRATION_DATE.equals(this.sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.ORDER_COND_TYPE.equals(this.sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.DELIVERY_DATE.equals(this.sortKeys[i].keyItem)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * @@return WEB3GenResponse WEB3GenResponse
     * @@see webbroker3.adminorderexecinquiry.message.WEB3AdminOROrderExecutionRefCommonRequest#
     * createResponse()
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminOREquityOrderExecutionRefReferenceResponse(this);
    }

}
@
