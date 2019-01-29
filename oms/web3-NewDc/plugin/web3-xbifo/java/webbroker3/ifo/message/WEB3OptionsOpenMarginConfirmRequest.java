head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.22.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsOpenMarginConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション新規建注文確認リクエスト(WEB3OptionsOpenMarginConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 李頴淵 (中訊) 新規作成
*/

package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.ifo.define.WEB3IfoContractTypeDef;
import webbroker3.ifo.define.WEB3IfoProductTypeDef;

/**
 * (株価指数オプション新規建注文確認リクエスト)<BR>
 * 株価指数オプション新規建注文確認リクエストクラス<BR>
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3OptionsOpenMarginConfirmRequest extends WEB3OptionsCommonRequest
{
    /**
      * Logger
      */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3OptionsOpenMarginConfirmRequest.class);
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200406141500L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "options_openMarginConfirm";

    /**
     * 株価指数オプション銘柄コード<BR>
     */
    public String opProductCode;

    /**
     * (建区分)<BR>
     * 1：買建　@2：売建<BR>
     */
    public String contractType;

    /**
     * (取引市場)<BR>
     * 1：東京　@2：大阪<BR>
     */
    public String marketCode;

    /**
     * (指数種別)<BR>
     * 0005：TOPIX　@0018：日経225　@0016：日経300　@0019：ミニ日経225<BR>
     */
    public String targetProductCode;

    /**
     * (限月)<BR>
     * YYYYMM形式<BR>
     */
    public String delivaryMonth;

    /**
     * (オプション商品区分)<BR>
     * P：プットオプション C：コールオプション<BR>
     */
    public String opProductType;

    /**
     * (行使価格)<BR>
     */
    public String strikePrice;

    /**
     * @@roseuid 40C0A8ED0261
     */
    public WEB3OptionsOpenMarginConfirmRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で簡潔する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@スーパークラスのvalidateメソッドを呼び出す<BR>
     * <BR>
     * ２）　@建区分チェック<BR>
     * 　@２－１）this.建区分がnullの値であれば例外をスローする。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00263<BR>
     * 　@２－２）this.建区分が以下の値以外の場合例外をスローする。<BR>
     * 　@　@　@　@・”1：買建”<BR>
     * 　@　@　@　@・”2：売建”<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00264<BR>
     * <BR>
     * ３）　@取引市場チェック<BR>
     * 　@３－１）this.取引市場がnullの値であれば例外をスローする。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00265<BR>
     * 　@３－２)this.取引市場が以下の値以外の値の場合例外をスローする。<BR> 
     * 　@・”1：東京”<BR> 
     * 　@・”2：大阪”<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01087<BR>
     * <BR>
     * ４）　@指数種別チェック<BR>
     *　@４－１）this.指数種別がnullの値であれば例外をスローする。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00266<BR>
     *　@４－２）this.指数種別が数字以外の値であれば例外をスローする。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_02441<BR>
     *　@４－３）this.指数種別の桁数が４桁以外の値であれば例外をスローする。 <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_02442<BR>
     * <BR>
     * ５）　@限月チェック<BR>
     * 　@５－１）this.限月がnullの値であれば例外をスローする。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00267<BR>
     * 　@５－２）this.限月がＹＹＹＹＭＭ形式の値以外の値であれば例外をスローする。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00268<BR>
     * <BR>
     * ６）　@オプション商品区分チェック<BR>
     * 　@６－１）this.オプション商品区分がnullの値であれば例外をスローする。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00269<BR>
     * 　@６－２）this.オプション商品区分が以下の値以外の場合例外をスローする。<BR>
     * 　@　@　@　@・”P：プットオプション”<BR>
     * 　@　@　@　@・”C：コールオプション”<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00270<BR>
     * <BR>
     * ７）　@行使価格チェック<BR>
     * 　@７－１）this.行使価格がnullの値であれば例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00271<BR>
     * 　@７－２）this.行使価格が数字以外の値であれば例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00272<BR>
     * 　@７－３）this.行使価格が≦０の値であれば例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00273<BR>
     * 　@７－４）this.行使価格が８桁を超える値であれば例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00274<BR>
     * <BR>
     * ８）　@注文数量チェック<BR>
     * 　@８－１）this.注文数量がnullの値であれば例外をスローする。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00074<BR>
     * 　@８－２）this.注文数量が数字以外の値であれば例外をスローする。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00075<BR>
     * 　@８－３）this.注文数量が≦０の値であれば例外をスローする。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00076<BR>
     * 　@８－４）this.注文数量が５桁を超える値であれば例外をスローする。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00077<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4068CE9D037B
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = getClass().getName() + ".validate()";
        log.entering(STR_METHOD_NAME);
        
        super.validate();

        //建区分チェック
        //this.建区分がnullの値であれば例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.contractType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00263,
                getClass().getName() + "validate",
                "建区分がnullである");   
        }

        //建区分チェック
        //this.建区分が”1：買建””2：売建"以外の場合例外をスローする。
        if (!WEB3IfoContractTypeDef.OPEN_BUY.equals(this.contractType)
            && !WEB3IfoContractTypeDef.OPEN_SELL.equals(this.contractType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00264,
                getClass().getName() + "validate",
                "建区分が”1：買建”、”2：売建”以外である");
        }

        //３－１)取引市場チェック
        if (WEB3StringTypeUtility.isEmpty(this.marketCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00265,
                getClass().getName() + "validate",
                "取引市場がnullである");
        }

        //３－２)this.取引市場が以下の値以外の値の場合例外をスローする。<BR> 
        //　@・”1：東京” 
        //　@・”2：大阪”
        if (!WEB3MarketCodeDef.TOKYO.equals(this.marketCode)
            && !WEB3MarketCodeDef.OSAKA.equals(this.marketCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01087,
                getClass().getName() + "validate",
                "取引市場の値は規定枚挙値の範囲ではないです。");
        }

        //４－１指数種別チェック
        //this.指数種別がnullの値であれば例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.targetProductCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00266,
                getClass().getName() + "validate",
                "指数種別がnullである");
        }

        //４－２）this.指数種別が数字以外の値であれば例外をスローする。
        if (!WEB3StringTypeUtility.isNumber(this.targetProductCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02441,
                getClass().getName() + "validate",
                "指数種別が数字以外の値です。");
        }
        
        //４－３）this.指数種別の桁数が４桁以外の値であれば例外をスローする。
        if (this.targetProductCode.length() != 4)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02442,
                getClass().getName() + "validate",
                "指数種別のサイズが不正です。");
        }

        //５）限月チェック
        //this.銘柄コードがnullの値でかつ、this.限月がnullの値であれば例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.delivaryMonth))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00267,
                getClass().getName() + "validate",
                "限月がnullである");
        }

        //限月チェック
        //this.銘柄コードがnullの値でかつ、this.限月がＹＹＹＹＭＭ形式の値以外の値であれば例外をスローする。
        if (!WEB3StringTypeUtility.isDateStr(this.delivaryMonth ,"yyyymm"))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00268,
                getClass().getName() + "validate",
                "限月がＹＹＹＹＭＭ形式で入力してください");
        }

        //６）オプション商品区分チェック
        //this.オプション商品区分がnullの値であれば例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.opProductType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00269,
                getClass().getName() + "validate",
                "オプション商品区分がnullである");
        }

        //オプション商品区分チェック
        //オプション商品区分が”P”、”C”以外の場合例外をスローする。
        if (!WEB3IfoProductTypeDef.CALL_OPTIONS.equals(this.opProductType)
                && !WEB3IfoProductTypeDef.PUT_OPTIONS.equals(this.opProductType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00270,
                getClass().getName() + "validate",
                "オプション商品区分が”P：プットオプション”、”C：コールオプション”以外である");
        }

        //７）行使価格チェック
        //this.銘柄コードがnullの値でかつ、this.行使価格がnullの値であれば例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.strikePrice))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00271,
                getClass().getName() + "validate",
                "行使価格がnullである");
        }

        //行使価格チェック
        //this.行使価格が数字以外の値であれば例外をスローする。
        if (!WEB3StringTypeUtility.isNumber(this.strikePrice))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00272,
                getClass().getName() + "validate",
                "行使価格が数字以外の値である");
        }

        //行使価格チェック
        //this.行使価格が≦０の値であれば例外をスローする。
        if (Double.parseDouble(this.strikePrice) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00273,
                getClass().getName() + "validate",
                "行使価格が0以下の値である");   
        }

        //行使価格チェック
        //this.行使価格が８桁を超える値であれば例外をスローする。
        if (WEB3StringTypeUtility.getByteLength(this.strikePrice) > 8)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00274,
                getClass().getName() + "validate",
                "行使価格が８桁を超えました。");
        }

        //８）注文数量チェック
        //this.注文数量がnullの値であれば例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.opOrderQuantity))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00074,
                getClass().getName() + "validate",
                "注文数量を入力してください。");
        }

        //注文数量チェック
        //this.注文数量が数字以外の値であれば例外をスローする。
        if (!WEB3StringTypeUtility.isNumber(this.opOrderQuantity))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00075,
                getClass().getName() + "validate",
                "注文数量が数字以外の値である。");
        }

        //注文数量チェック
        //this.注文数量が≦０の値であれば例外をスローする。
        if (Long.parseLong(this.opOrderQuantity) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00076,
                getClass().getName() + "validate",
                "注文数量が0以下の値である。");
        }

        //注文数量チェック
        //this.注文数量が５桁を超える値であれば例外をスローする。
        if (WEB3StringTypeUtility.getByteLength(this.opOrderQuantity) > 5)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00077,
                getClass().getName() + "validate",
                "注文数量が５桁を超えました。");
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3OptionsOpenMarginConfirmResponse(this);
    }
}
@
