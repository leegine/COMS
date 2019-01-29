head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.21.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOpenMarginCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物新規建注文完了リクエストクラス(WEB3FuturesOpenMarginCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/20 呉艶飛 (中訊) 新規作成
*/

package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.define.WEB3IfoContractTypeDef;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (株価指数先物新規建注文完了リクエスト)<BR>
 * 株価指数先物新規建注文完了リクエストクラス<BR>
 * 
 * @@author 呉艶飛
 * @@version 1.0 
 */
public class WEB3FuturesOpenMarginCompleteRequest extends WEB3FuturesCommonRequest
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "futures_OpenMarginComplete";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200407201655L;

    /**
     * (株価指数先物銘柄コード)<BR>
     */
    public String futProductCode;

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
     * (暗証番号)<BR>
     */
    public String password;

    /**
     * (確認時単価)<BR>
     * 確認レスポンスで送った値。<BR>
     */
    public String checkPrice;

    /**
     * (確認時発注日)<BR>
     * 確認レスポンスで送った値。<BR>
     */
    public Date checkDate;

    /**
     * (注文ID)<BR>
     * 確認レスポンスで送った値。<BR>
     */
    public String orderId;

    /**
     * (指数種別)<BR>
     *（原資産銘柄コード）<BR>
     * 0005：TOPIX　@0018：日経225　@0016：日経300　@0019：ミニ日経225<BR>
     */
    public String targetProductCode;

    /**
     * (限月)<BR>
     * YYYYMM形式<BR>
     */
    public String delivaryMonth;

    /**
     * @@roseuid 40F7AE13004E
     */
    public WEB3FuturesOpenMarginCompleteRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で簡潔する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@スーパークラスのvalidateメソッドを呼び出す<BR>
     * <BR>
     * ２）　@銘柄チェック<BR>
     * 　@this.銘柄コードがnullの場合、下記条件以外は例外「銘柄指定エラー」をスローする。<BR>
     * 　@・this.指数種別!=null　@&&　@this.限月!=null<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00334<BR>
     * <BR>
     * ３）　@建区分チェック<BR>
     * 　@３－１）this.建区分がnullの値であれば例外をスローする。<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00263<BR>
     * 　@３－２）this.建区分が以下の値以外の場合例外をスローする。<BR>
     * 　@　@　@　@・”1：買建”<BR>
     * 　@　@　@　@・”2：売建”<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00264<BR>
     * <BR>
     * ４）　@取引市場チェック<BR>
     * 　@４－１）this.取引市場がnullの値であれば例外をスローする。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00265<BR>
     * 　@４－２)this.取引市場が以下の値以外の値の場合例外をスローする。<BR> 
     *        ・”1：東京”<BR> 
     *        ・”2：大阪”<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01087<BR>
     * <BR>
     * ５）　@注文数量チェック<BR>
     * 　@５－１）this.注文数量がnullの値であれば例外をスローする。<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00074<BR>
     * 　@５－２）this.注文数量が数字以外の値であれば例外をスローする。<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00075<BR>
     * 　@５－３）this.注文数量≦０の場合、例外をスローする。<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00076<BR>
     * 　@５－４）this.注文数量が５桁を超える値であれば例外をスローする。<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00077<BR>
     * <BR>
     * ６）　@確認時単価チェック(this.注文ID==nullの場合、チェックを行わない）<BR>
     * 　@this.確認時単価がnullの値であれば例外をスローする。<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00206<BR>
     * <BR>
     * ７）　@確認時発注日チェック(this.注文ID==nullの場合、チェックを行わない）<BR>
     * 　@this.確認時発注日がnullの値であれば例外をスローする。<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00078<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40A214200260
     */
    public void validate() throws WEB3BaseException
    {
        // １）スーパークラスのvalidateメソッドを呼び出す
        super.validate();
        
        // ２）　@銘柄チェック<BR>
        // 　@this.銘柄コードがnullの場合、下記条件以外は例外「銘柄指定エラー」をスローする。
        // 　@・this.指数種別!=null　@&&　@this.限月!=null
        //      class: WEB3BusinessLayerException
        //      tag:   BUSINESS_ERROR_00334
        if (WEB3StringTypeUtility.isEmpty(this.futProductCode)
            && (WEB3StringTypeUtility.isEmpty(this.targetProductCode)
            || WEB3StringTypeUtility.isEmpty(this.delivaryMonth)))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00334, 
                this.getClass().getName() + "validate",
                "銘柄指定エラー。");
        }

        // ３）　@建区分チェック
        //３－１）this.建区分がnullの値であれば例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.contractType))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00263, 
                this.getClass().getName() + "validate",
                "建区分がnullである");
        }

        //３－２）this.建区分が以下の値以外の場合例外をスローする。
        if (!WEB3IfoContractTypeDef.OPEN_BUY.equals(this.contractType) 
            && !WEB3IfoContractTypeDef.OPEN_SELL.equals(this.contractType))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00264, 
                this.getClass().getName() + "validate",
                "建区分が”1：買建”、”2：売建”以外である");
        }

        // ４)取引市場チェック
        //４－１）this.取引市場がnullの値であれば例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.marketCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00265, 
                this.getClass().getName() + "validate",
                "取引市場がnullである");
        }

        //４－２)this.取引市場が以下の値以外の値の場合例外をスローする。
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

        // ５）注文数量チェック
        //５－１）this.注文数量がnullの値であれば例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.futOrderQuantity))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00074, 
                this.getClass().getName() + "validate",
                "注文数量を入力してください。");
        }

        //５－２）this.注文数量が数字以外の値であれば例外をスローする。
        if (!WEB3StringTypeUtility.isNumber(this.futOrderQuantity))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00075, 
                this.getClass().getName() + "validate",
                "注文数量が数字以外の値である。");
        }

        //５－３）this.注文数量≦０の場合、例外をスローする。
        if (Long.parseLong(this.futOrderQuantity) <= 0)
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00076, 
                this.getClass().getName() + "validate",
                "注文数量が0以下の値である。");
        }

        //５－４）this.注文数量が５桁を超える値であれば例外をスローする。
        if (WEB3StringTypeUtility.getByteLength(this.futOrderQuantity) > 5)
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00077, 
                this.getClass().getName() + "validate",
                "注文数量が５桁を超えました。");
        }

        // ６）　@確認時単価チェック(this.注文ID==nullの場合、チェックを行わない）
        //  this.確認時単価がnullの値であれば例外をスローする。
        if (!WEB3StringTypeUtility.isEmpty(this.orderId) 
            && WEB3StringTypeUtility.isEmpty(this.checkPrice))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00206, 
                this.getClass().getName() + "validate",
                "確認時単価がnullの値である");
        }

        // ７）　@確認時発注日チェック(this.注文ID==nullの場合、チェックを行わない）
        // this.確認時発注日がnullの値であれば例外をスローする。
        if (!WEB3StringTypeUtility.isEmpty(this.orderId) 
            && this.checkDate == null)
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00078, 
                this.getClass().getName() + "validate",
                "確認時発注日がnullの値である。");
        }
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FuturesOpenMarginCompleteResponse(this);
    }
}
@
