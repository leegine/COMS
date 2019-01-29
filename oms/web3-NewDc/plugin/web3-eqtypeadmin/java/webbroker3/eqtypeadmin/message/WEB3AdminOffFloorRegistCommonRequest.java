head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminOffFloorRegistCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者立会外分売銘柄新規登録共通リクエスト(WEB3AdminOffFloorRegistCommonRequest.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者立会外分売銘柄新規登録共通リクエスト)<BR>
 * <BR>
 * 管理者立会外分売銘柄新規登録サービス（確認／完了）リクエストデータのスーパークラ
 * ス。<BR>
 * <BR>
 * ------<English>----------<BR>
 * <BR>
 * WEB3AdminOffFloorRegistCommonRequest<BR>
 * <BR>
 * super class of WEB3AdminOffFloorRegistService(validate/submit) request data<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public abstract class WEB3AdminOffFloorRegistCommonRequest extends WEB3GenRequest
{

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminOffFloorRegistCommonRequest.class);

    /**
     * (銘柄コード)<BR>
     * <BR>
     * 銘柄コード。<BR>
     * <BR>
     * productCode<BR>
     * <BR>
     */
    public String productCode;

    /**
     * (市場コード)<BR>
     * <BR>
     * 市場コード。<BR>
     * <BR>
     * marketCode<BR>
     * <BR>
     */
    public String marketCode;

    /**
     * (受付開始日時)<BR>
     * <BR>
     * 受付開始日時。<BR>
     * <BR>
     * orderStartDatetime<BR>
     * <BR>
     */
    public Date orderStartDatetime;

    /**
     * (分売価格)<BR>
     * <BR>
     * 分売価格。<BR>
     * <BR>
     * offFloorOrderPrice<BR>
     * <BR>
     */
    public String offFloorOrderPrice;

    /**
     * (申込株数上限)<BR>
     * <BR>
     * 申込株数上限。<BR>
     * （一人あたりの注文可能株数の上限値）<BR>
     * <BR>
     * ----<English>-------<BR>
     * <BR>
     * maxApplyQuantity<BR>
     * （maximum value of applyQuantity per person）<BR>
     * <BR>
     */
    public String maxApplyQuantity;

    /**
     * @@roseuid 421AE4400226
     */
    public WEB3AdminOffFloorRegistCommonRequest()
    {

    }

    /**
     * 当クラスのプロパティの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）　@銘柄コードチェック <BR>
     * <BR>
     * 　@１－１）　@this.銘柄コード＝nullの場合、 <BR>
     * 　@　@　@　@　@「銘柄コードがnull」の例外をthrowする。 <BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00079<BR>
     * <BR>
     * ２）　@市場コードチェック <BR>
     * <BR>
     * 　@２－１）　@this.市場コード＝nullの場合、 <BR>
     * 　@　@　@　@　@「市場コードがnull」の例外をthrowする。 <BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00443<BR>
     * <BR>
     * 　@２－２）　@this.市場コード≠null、かつ 以下の値以外の場合、 <BR>
     * 　@　@　@　@　@「市場コードが未定義の値」の例外をthrowする。 <BR>
     *          ・"1：東京" <BR>
     *          ・"2：大阪" <BR>
     *          ・"3：名古屋" <BR>
     *          ・"6：福岡" <BR>
     *          ・"8：札幌" <BR>
     *          ・"9：NNM" <BR>
     *          ・"10：JASDAQ" <BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00608<BR>
     * <BR>
     * ３）　@受付開始日時チェック<BR>
     * <BR>
     * 　@３－１）　@this.受付開始日時＝nullの場合、 <BR>
     * 　@　@　@　@　@「受付開始日時がnull」の例外をthrowする。 <BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01389<BR>
     * <BR>
     * ４）　@分売価格チェック<BR>
     * <BR>
     * 　@４－１）　@this.分売価格≠null、かつ
     * 以下のいずれかに該当する場合は、以下の例外をthrowする。 <BR>
     * 　@　@　@・this.分売価格≠数字 <BR>
     * 　@　@　@　@　@　@「分売価格が数字以外」の例外をthrow。 <BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01453<BR>
     * <BR>
     * 　@　@　@・this.分売価格≦0 <BR>
     * 　@　@　@　@　@　@「分売価格が0以下」の例外をthrow。 <BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01454<BR>
     * <BR>
     * 　@　@　@・this.分売価格＝9桁を超える数字 <BR>
     * 　@　@　@　@　@　@「分売価格の桁数が9桁を超過」の例外をthrow。 <BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01455<BR>
     * <BR>
     * ５）　@申込株数上限チェック<BR>
     * <BR>
     * 　@５－１）　@this.申込株数上限≠null、かつ
     * 以下のいずれかに該当する場合は、以下の例外をthrowする。 <BR>
     * 　@　@　@・this.申込株数上限≠数字 <BR>
     * 　@　@　@　@　@　@「申込株数上限が数字以外」の例外をthrow。 <BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01456<BR>
     * <BR>
     * 　@　@　@・this.申込株数上限≦0 <BR>
     * 　@　@　@　@　@　@「申込株数上限が0以下」の例外をthrow。 <BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01457<BR>
     * <BR>
     * 　@　@　@・this.申込株数上限＝8桁を超える数字 <BR>
     * 　@　@　@　@　@　@「申込株数上限の桁数が8桁を超過」の例外をthrow。<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01458<BR>
     * <BR>
     * ------<English>----------------<BR>
     * <BR>
     * Check the correspondence of the properties in this class<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1)productCode check<BR>
     * <BR>
     * 　@1-1)If this.productCode＝null,<BR>
     * 　@　@　@　@　@Throw the exception "productCode is null"<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00079<BR>
     * <BR>
     * 2)marketCode check<BR>
     * <BR>
     * 　@2-1)If this.marketCode＝null<BR>
     * 　@　@　@　@　@Throw the exception "marketCode is null"<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00443<BR>
     * <BR>
     * 　@2-2)If this.marketCode ≠null and it has a value other than the
     * followings,<BR>
     * 　@　@　@　@　@Throw the exception "marketCode has an undefined value"<BR>
     *          ・1: Def.TOKYO<BR>
     *          ・2: Def.OSAKA<BR>
     *          ・3: Def.NAGOYA<BR>
     *          ・6: Def.FUKUOKA<BR>
     *          ・8: Def.SAPPORO<BR>
     *          ・9: Def.NNM<BR>
     *          ・10: Def.JASDAQ<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00608<BR>
     * <BR>
     * 3)orderStartDatetime check<BR>
     * <BR>
     * 　@3-1)If this.orderStartDatetime＝null,<BR>
     * 　@　@　@　@　@Throw the exception, "orderStartDatetime is null"<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01389<BR>
     * <BR>
     * 4)offFloorOrderPrice check<BR>
     * <BR>
     * 　@4-1)If this.offFloorOrderPrice≠null and it meets either of the following
     * cases, throw the following exception<BR>
     * 　@　@　@・this.offFloorOrderPrice≠numerical value<BR>
     * 　@　@　@　@　@　@Throw the exception, "offFloorOrderPrice is not a numerical
     * value"<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01453<BR>
     * <BR>
     * 　@　@　@・this.offFloorOrderPrice≦0 <BR>
     * 　@　@　@　@　@　@Throw the exception, "offFloorOrderPrice is less than 0"<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01454<BR>
     * <BR>
     * 　@　@　@・this.offFloorOrderPrice＝a numerical value with more than 9 digits<BR>
     * 　@　@　@　@　@　@Throw the exception, "offFloorOrderPrice has more than 9 digits"<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01455<BR>
     * <BR>
     * 5)maxApplyQuantity check<BR>
     * <BR>
     * 　@5-1)If this.maxApplyQuantity≠null and it meets either of the following cases,
     * throw the following exception<BR>
     * 　@　@　@・this.maxApplyQuantity≠numerical value<BR>
     * 　@　@　@　@　@　@Throw the exception, "maxApplyQuantity is not a numerical value"<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01456<BR>
     * <BR>
     * 　@　@　@・this.maxApplyQuantity≦0 <BR>
     * 　@　@　@　@　@　@Throw the exception, "maxApplyQuantity is less than 0"<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01457<BR>
     * <BR>
     * 　@　@　@・this.maxApplyQuantity＝a numerical value with more than 8 digits<BR>
     * 　@　@　@　@　@　@Throw the exception, "maxApplyQuantity has more than 8 digits"<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01458<BR>
     * <BR>
     * @@throws WEB3BusinessLayerException WEB3BusinessLayerException
     * @@roseuid 41B7D1AB025D
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        final int l_intnine = 9;
        final int l_intEight = 8;
        int l_offFloorOrderPriceGetByteLength =
            WEB3StringTypeUtility.getByteLength(offFloorOrderPrice);
        int l_maxApplyQuantityGetByteLength = WEB3StringTypeUtility.getByteLength(maxApplyQuantity);

        //1-1) If this.productCode＝null, throw Exception
        if (productCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //2-1)If this.marketCode＝null
        if (marketCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00443,
                this.getClass().getName() + "." + STR_METHOD_NAME);

            /*
             * 2-2)If this.marketCode ≠null and it has a value other than the
             * followings Throw the exception
             * 1: Def.TOKYO
             * 2: Def.OSAKA
             * 3: Def.NAGOYA
             * 6: Def.FUKUOKA
             * 8: Def.SAPPORO
             * 9: Def.NNM
             * 10: Def.JASDAQ
             */
        } else if (
            (marketCode != null)
                && (!WEB3MarketCodeDef.TOKYO.equals(marketCode))
                && (!WEB3MarketCodeDef.OSAKA.equals(marketCode))
                && (!WEB3MarketCodeDef.NAGOYA.equals(marketCode))
                && (!WEB3MarketCodeDef.FUKUOKA.equals(marketCode))
                && (!WEB3MarketCodeDef.SAPPORO.equals(marketCode))
                && (!WEB3MarketCodeDef.NNM.equals(marketCode))
                && (!WEB3MarketCodeDef.JASDAQ.equals(marketCode)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 3-1)If this.orderStartDatetime＝null Throw the exception
        if (orderStartDatetime == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01389,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        /*
         * 4-1)If this.offFloorOrderPrice≠null and it meets either of the following
         *  cases, throw the following exception
         * this.offFloorOrderPrice≠numerical value Throw the exception
         * maxApplyQuantity≦0 Throw the exception
         * offFloorOrderPrice＝a numerical value with more than 9 digits
         * Throw the exception
         */
        if (offFloorOrderPrice != null)
        {
            if (!WEB3StringTypeUtility.isNumber(offFloorOrderPrice))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01453,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            int l_intoffFloorOrderPrice = Integer.parseInt(offFloorOrderPrice);
            if (l_intoffFloorOrderPrice <= 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01454,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            if ((WEB3StringTypeUtility.isNumber(offFloorOrderPrice))
                && (l_offFloorOrderPriceGetByteLength > l_intnine))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01455,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        /*
         * 5-1)If this.maxApplyQuantity≠null and it meets either of the following
         * cases, throw the following exception
         * this.maxApplyQuantity≠numerical value Throw the exception
         * maxApplyQuantity≦0 Throw the exception
         * maxApplyQuantity＝a numerical value with more than 9 digits Throw the exception
         */
        if (maxApplyQuantity != null)
        {
            if (!WEB3StringTypeUtility.isNumber(maxApplyQuantity))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01456,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            int l_intoffFloorOrderPrice = Integer.parseInt(maxApplyQuantity);
            if (l_intoffFloorOrderPrice <= 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01457,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            if ((WEB3StringTypeUtility.isNumber(maxApplyQuantity))
                && (l_maxApplyQuantityGetByteLength > l_intEight))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01458,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /** (non-Javadoc)
      * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
      */
    public abstract WEB3GenResponse createResponse();
}
@
