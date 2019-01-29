head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.22.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOpenMarginConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物新規建注文確認リクエストクラス(WEB3FuturesOpenMarginConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/20 呉艶飛 (中訊) 新規作成
*/

package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.define.WEB3IfoContractTypeDef;

import webbroker3.util.WEB3StringTypeUtility;

/**
 * (株価指数先物新規建注文確認リクエスト)<BR>
 * 株価指数先物新規建注文確認リクエストクラス<BR> 
 * 
 * @@author 呉艶飛
 * @@version 1.0 
 */

public class WEB3FuturesOpenMarginConfirmRequest extends WEB3FuturesCommonRequest
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "futures_OpenMarginConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200407201455L;

    /**
     * (株価指数先物銘柄コード)<BR>
     */
    public String futProductCode;

    /**
     * (建区分)<BR>.
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
     * @@roseuid 40F7AE130203
     */
    public WEB3FuturesOpenMarginConfirmRequest()
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
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00263<BR>
     * 　@２－２）this.建区分が以下の値以外の場合例外をスローする。<BR>
     * 　@　@　@　@・”1：買建”<BR>
     * 　@　@　@　@・”2：売建”<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00264<BR>
     * <BR>
     * ３）　@取引市場チェック<BR>
     * 　@３－１）this.取引市場がnullの値であれば例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00265<BR>
     * 　@３－２)this.取引市場が以下の値以外の値の場合例外をスローする。<BR> 
     * 　@     ・”1：東京”<BR> 
     * 　@     ・”2：大阪”<BR>
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
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00267<BR>
     * 　@５－２）this.限月がＹＹＹＹＭＭ形式の値以外の値であれば例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00268<BR>
     * <BR>
     * ６）　@注文数量チェック<BR>
     * 　@６－１）this.注文数量がnullの値であれば例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00074<BR>
     * 　@６－２）this.注文数量が数字以外の値であれば例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00075<BR>
     * 　@６－３）this.注文数量≦０の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00076<BR>
     * 　@６－４）this.注文数量が５桁を超える値であれば例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00077<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40A212B90169
     */
    public void validate() throws WEB3BaseException
    {
        //(１）スーパークラスのvalidateメソッドを呼び出す
        super.validate();

        //(２）建区分チェック
        if (WEB3StringTypeUtility.isEmpty(this.contractType))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00263, 
                this.getClass().getName() + "validate",
                "銘柄コードがnullの値でかつ、建区分がnullの値である");
        }

        //２－２）this.建区分が以下の値以外の場合例外をスローする。
        if (!WEB3IfoContractTypeDef.OPEN_BUY.equals(this.contractType) 
            && !WEB3IfoContractTypeDef.OPEN_SELL.equals(this.contractType))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00264, 
                this.getClass().getName() + "validate",
                "建区分が”1：買建”、”2：売建”以外である");
        }

        //(３）　@取引市場チェック
        //this.取引市場がnullの値であれば例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.marketCode))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00265, 
                this.getClass().getName() + "validate",
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
                this.getClass().getName() + "validate",
                "取引市場の値は規定枚挙値の範囲ではないです。");
        }

        //（４）　@指数種別チェック
        //４－１）this.指数種別がnullの値であれば例外をスローする。
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

        //(５）限月チェック
        //５－１）this.限月がnullの値であれば例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.delivaryMonth))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00267, 
                this.getClass().getName() + "validate",
                "銘柄コードがnullの値でかつ、限月がnullの値である");
        }

        //５－２）this.限月がＹＹＹＹＭＭ形式の値以外の値であれば例外をスローする。
        if (!WEB3StringTypeUtility.isDateStr(this.delivaryMonth, "yyyymm"))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00268, 
                this.getClass().getName() + "validate",
                "銘柄コードがnullの値でかつ、.限月がＹＹＹＹＭＭ形式の値以外の値である");
        }

        //(６）　@注文数量チェック
        //６－１）this.注文数量がnullの値であれば例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.futOrderQuantity))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00074, 
                this.getClass().getName() + "validate",
                "注文数量を入力してください。");
        }

        //６－２）this.注文数量が数字以外の値であれば例外をスローする。
        if (!WEB3StringTypeUtility.isNumber(this.futOrderQuantity))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00075, 
                getClass().getName() + "validate",
                "注文数量が数字以外の値である。");
        }

        //６－３）this.注文数量≦０の場合、例外をスローする。
        if (Long.parseLong(this.futOrderQuantity) <= 0)
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00076, 
                this.getClass().getName() + "validate",
                "注文数量が0以下の値である。");
        }

        //６－４）this.注文数量が５桁を超える値であれば例外をスローする。
        if (WEB3StringTypeUtility.getByteLength(this.futOrderQuantity) > 5)
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00077, 
                this.getClass().getName() + "validate",
                "注文数量が５桁を超えました。");
        }
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FuturesOpenMarginConfirmResponse(this);
    }
}
@
