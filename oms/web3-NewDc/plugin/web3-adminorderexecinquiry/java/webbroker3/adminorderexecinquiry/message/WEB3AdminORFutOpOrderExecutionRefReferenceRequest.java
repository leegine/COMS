head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.44.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORFutOpOrderExecutionRefReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・先物OP注文約定照会リクエスト (WEB3AdminORFutOpOrderExecutionRefReferenceRequest.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.adminorderexecinquiry.message;


import webbroker3.adminorderexecinquiry.define.WEB3AdminKeyItemDef;
import webbroker3.adminorderexecinquiry.define.WEB3OptionProductTypeDef;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者・先物OP注文約定照会リクエスト)<BR>
 * <BR>
 * 管理者・先物OP注文約定照会リクエストクラス<BR>
 * <BR>
 * WEB3AdminORFutOpOrderExecutionRefReferenceRequest<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminORFutOpOrderExecutionRefReferenceRequest
    extends WEB3AdminOROrderExecutionRefCommonRequest
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_or_fut_op_order_execution_ref_reference";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminORFutOpOrderExecutionRefReferenceRequest.class);

    /**
    * (指数種別)<BR>
    * <BR>
    * 指数種別<BR>
    * <BR>
    * 0005：　@TOPIX<BR>
    * 0018：　@日経225<BR>
    * 0016：　@日経300 <BR>
    * 0019：ミニ日経225<BR>
    * <BR>
    * -----<English>---------------<BR>
    * <BR>
    * targetProductCode<BR>
    * <BR>
    */
    public String targetProductCode = null;

    /**
     * (限月)<BR>
     * <BR>
     * 限月<BR>
     * (YYYYMM)<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * delivaryMonth<BR>
     * (YYYYMM)<BR>
     * <BR>
     */
    public String delivaryMonth = null;

    /**
     * (・s使価格)<BR>
     * <BR>
     * 行使価格<BR>
     * <BR>
     * strikePrice<BR>
     * <BR>
     */
    public String strikePrice = null;

    /**
     * (オプション商品区分)<BR>
     * <BR>
     * オプション商品区分<BR>
     * <BR>
     * P：　@プット<BR>
     * C：　@コール<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * opProductType<BR>
     * P: Def.PUT<BR>
     * C: Def.CALL<BR>
     * <BR>
     */
    public String opProductType = null;

    /**
     * @@roseuid 4212FBA202E6
     */
    public WEB3AdminORFutOpOrderExecutionRefReferenceRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）スーパークラスのvalidate()をコールする。<BR>
     * <BR>
     * ２）指数種別チェック<BR>
     * 　@this.指数種別≠nullの場合、以下のチェックを行う。<BR>
     * 　@２－１）this.指数種別が数字以外の値であれば例外をスローする。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_02441<BR>
     * 　@２－２）this.指数種別の桁数が４桁以外の値であれば例外をスローする。 <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_02442<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01464<BR>
     * <BR>
     * ３）行使価格チェック <BR>
     * 　@this.行使価格 != nullの場合、以下のチェックを行う。<BR>
     * 　@３－１）this.行使価格が以下の条件に該当する場合、<BR>
     * 　@　@　@　@「行使価格エラー」の例外をスローする。<BR>
     * 　@　@　@　@・this.行使価格 != 数値<BR>
     * 　@　@　@　@・this.行使価格 < 0<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01475<BR>
     * <BR>
     * ４）オプション商品区分チェック<BR>
     * 　@this.オプション商品区分 != nullの場合、以下のチェッ・Nを行う。<BR>
     * 　@４－１）this.オプション商品区分に下記の項目以外が設定されていたら、<BR>
     * 　@　@　@　@「オプション商品区分が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@・"プット"<BR>
     * 　@　@　@　@・"コール"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00270<BR>
     * <BR>
     * ５）ソートキーチェック<BR>
     * 　@５－１）this.ソートキーの要素数分以下の処理を繰り返す。<BR>
     * 　@　@５－１－１）ソートキー.キー項目に下記の項目以外が<BR>
     * 　@　@　@設定されていたら、<BR>
     * 　@　@　@「ソートキー.キー項目が未定義の値」の例外をスローする。<BR>
     * 　@　@　@・「部店コード」<BR>
     * 　@　@　@・「顧客コード」<BR>
     * 　@　@　@・「扱者コード（SONAR）」<BR>
     * 　@　@　@・「銘柄コード」<BR>
     * 　@　@　@・「市場コード」<BR>
     * 　@　@　@・「取引区分」<BR>
     * 　@　@　@・「注文時間」<BR>
     * 　@　@　@・「発注日」<BR>
     * 　@　@　@・「執行条件」<BR>
     * 　@　@　@・「・黒ｶ期限」<BR>
     * 　@　@　@・「発注条件」<BR>
     * 　@　@　@・「受渡日」<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00086<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * Check l_request<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1)Call super.validate()<BR>
     * <BR>
     * 2)targetProductCode check<BR>
     * 　@Check the following if this.targetProductCode != null<BR>
     * 　@2-1)If this.targetProductCode meets with the following conditions<BR>
     * 　@　@　@　@Throw the exception "targetProductCode has an undefined value"<BR>
     * 　@　@　@　@・Def.TOPIX<BR>
     * 　@　@　@　@・Def.NK225<BR>
     * 　@　@　@　@・Def.NK300<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01464<BR>
     * <BR>
     * 3)strikePrice check<BR>
     * 　@Check the followings if this.strikePrice != null<BR>
     * 　@3-1)If this.strikePrice meets with the following conditions,<BR>
     * 　@　@　@　@Throw the exception "strikePrice error"<BR>
     * 　@　@　@　@・this.strikePrice != numerical value<BR>
     * 　@　@　@　@・this.strikePrice < 0<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01475<BR>
     * <BR>
     * 4)opProductType check<BR>
     * 　@Check the followings if thisopProductType != null<BR>
     * 　@4-1)If this.opProductType contains values other than the followings,<BR>
     * 　@　@　@　@Throw the exception "opProductType has an undefined value"<BR>
     * 　@　@　@　@・Def.PUT_OPTIONS<BR>
     * 　@　@　@　@・Def.CALL_OPTIONS<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00270<BR>
     * <BR>
     * 5)sortKeys check<BR>
     * 　@5-1)Loop for as many times as the number of elements in this.sortKeys<BR>
     * 　@　@5-1-1)If sortKeys.keyItem contains values other than the followings,<BR>
     * 　@　@　@Throw the exception "sortKeys.keyItem has an undefined value"<BR>
     * 　@　@　@・branchCode<BR>
     * 　@　@　@・accountCode<BR>
     * 　@　@　@・productCode<BR>
     * 　@　@　@・marketCode<BR>
     * 　@　@　@・tradingDiv<BR>
     * 　@　@　@・orderDate<BR>
     * 　@　@　@・orderBizDate<BR>
     * 　@　@　@・execCondType<BR>
     * 　@　@　@・expirationDateType<BR>
     * 　@　@　@・orderCondType<BR>
     * 　@　@　@・deliveryDate<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00086<BR>
     * <BR>
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 41ADC837035B
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        final int l_intMin = 0;
        int l_intSortKeysLength = 0;

        //1-1 Call super.validate()
        super.validate();

        // 2-1 if targetProductCode is not any of Def, throw Exception.
        if (this.targetProductCode != null)
        {
            //　@２－１）this.指数種別が数字以外の値であれば例外をスローする。
            if (!WEB3StringTypeUtility.isNumber(this.targetProductCode))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02441,
                    getClass().getName() + "validate",
                    "指数種別が数字以外の値です。");
            }
            
            //　@２－２）this.指数種別の桁数が４桁以外の値であれば例外をスローする。
            if (this.targetProductCode.length() != 4)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02442,
                    getClass().getName() + "validate",
                    "指数種別のサイズが不正です。");
            }
        }

        // 3-1 if strikePrice is not numeric & is less than 0, throw Exception.
        if (this.strikePrice != null)
        {
            if ((!WEB3StringTypeUtility.isNumber(strikePrice))
                || (Double.parseDouble(this.strikePrice) < l_intMin))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01475,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        //4-1 if opProductType is not any of Def, throw Exception.
        if (this.opProductType != null)
        {
            if ((!WEB3OptionProductTypeDef.PUT_OPTIONS.equals(opProductType))
            && (!WEB3OptionProductTypeDef.CALL_OPTIONS.equals(opProductType)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00270,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 5-1 if sortKeys.keyItem is equal to Def, throw Exception.
        l_intSortKeysLength = this.sortKeys.length;
        for (int i = 0; i < l_intSortKeysLength; i++)
        {
            if ((!WEB3AdminKeyItemDef.BRANCH_CODE.equals(this.sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.ACCOUNT_CODE.equals(this.sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.SONAR_TRADER_CODE.equals(this.sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.PRODUCT_CODE.equals(this.sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.MARKET_CODE.equals(this.sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.TRADING_TYPE.equals(this.sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.ORDER_DATE.equals(this.sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.ORDER_BIZ_DATE.equals(this.sortKeys[i].keyItem))
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
     * @@see webbroker3.adminorderexecinquiry.message.WEB3AdminOROrderExecutionRefCommonRequest#
     *  createResponse()
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminORFutOpOrderExecutionRefReferenceResponse(this);
    }
}
@
