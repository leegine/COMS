head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.08.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSwitchingCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託乗換完了リクエストクラス(WEB3MutualSwitchingCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 于美麗 (中訊) 新規作成
                   2004/08/25 黄建 (中訊) レビュー
                   2004/12/07 于美麗 (中訊) 残対応
                   2005/10/20 韋念瓊 (中訊) フィデリティ対応
                   2006/10/27 張騰宇 (中訊) モデル 482
*/

package webbroker3.mf.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ClaimDivDef;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * 投資信託乗換完了リクエストクラス
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3MutualSwitchingCompleteRequest extends WEB3MutualCommonRequest
{
    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualSwitchingCompleteRequest.class);

    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_switching_complete";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408091539L;

    /**
     * 請求方法@<BR>
     * <BR>
     * 0:解約請求　@1:買取請求<BR>
     */
    public String sellBuyDiv;

    /**
     * 銘柄コード（乗換先）
     */
    public String switchingProductCode;

    /**
     * 買付口座区分<BR>
     * <BR>
     * 0:一般　@1:特定<BR>
     */
    public String switchingTaxType;

    /**
     * 暗証番号
     */
    public String password;
    
    /**
     * 注文ID<BR>
     */
    public String orderId;

    /**
     * (紹介区分)<BR>
     * 紹介区分 <BR>
     * <BR>
     * null:指定無し <BR>
     * 1:直接取引 <BR>
     * 2:単純紹介 <BR>
     * 3:商品紹介 <BR>
     * 4:仲介取引<BR>
     */
    public String introduceStoreDiv;

    /**
     * (紹介店コード)<BR>
     * 紹介店コード<BR>
     */
    public String introduceStoreCode;

    /**
     * (投信乗換完了リクエスト)<BR>
     * デフォルトコンストラクタ
     * @@roseuid 40A8A7E600E7
     */
    public WEB3MutualSwitchingCompleteRequest()
    {

    }

    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 投信乗換完了レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40A8A80001D1
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3MutualSwitchingCompleteResponse(this);
    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １)　@銘柄コードチェック<BR>
     * 　@this.銘柄コードがnullの場合、例外をスローする。<BR>
     *        class: WEB3BusinessLayerException<BR>
     *        tag:   BUSINESS_ERROR_00079<BR>
     * <BR>
     * ２)　@指定方法@チェック<BR>
     * 　@２－１)　@this.指定方法@がnullの場合、例外をスローする。<BR>
     *        class: WEB3BusinessLayerException<BR>
     *        tag:   BUSINESS_ERROR_00400<BR>
     * 　@２－２)　@this.指定方法@が以下のコード以外の場合、例外をスローする。<BR>
     * 　@　@　@　@・”全部”<BR>
     * 　@　@　@　@・”金額”<BR>
     * 　@　@　@　@・”口数”<BR>
     *        class: WEB3BusinessLayerException<BR>
     *        tag:   BUSINESS_ERROR_00073<BR>
     * <BR>
     * ３）　@(乗換)数量チェック <BR>
     * 　@３－１）　@this.指定方法@が“全部”且つ、this.数量がnull以外の値 <BR>
     * 　@　@　@　@　@である場合、 例外をスローする。 <BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00093<BR>
     * 　@３－２）　@this.指定方法@が“金額”または“口数”であり且つ、 <BR>
     * 　@　@　@　@　@this.数量がnullである場合、例外をスローする。 <BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00094<BR>
     * 　@３－３）　@this.指定方法@が“金額”または“口数”であり且つ、 <BR>
     * 　@　@　@　@　@this.数量が数値以外である場合、例外をスローする。 <BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00095<BR>
     * 　@３－４）　@this.指定方法@が“金額”または“口数”であり且つ、 <BR>
     * 　@　@　@　@　@this.数量≦0の場合、例外をスローする。 <BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00096<BR>
     * 　@３－５）　@this.指定方法@が“金額”または“口数”であり且つ、 <BR>
     * 　@　@　@　@　@this.数量の桁数＞11桁の場合、例外をスローする。 <BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00097<BR>
     * <BR>
     * ４)　@請求方法@チェック<BR>
     * 　@４－１)　@this.請求方法@がnullの場合、例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00401<BR>
     * 　@４－２)　@this.請求方法@が以下のコード以外の場合、例外をスローする。<BR>
     * 　@　@　@　@・”解約請求”<BR>
     * 　@　@　@　@・”買取請求”<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00402<BR>
     * <BR>
     * ５)　@銘柄コード（乗換先）チェック<BR>
     * 　@this.銘柄コード（乗換先）がnullの場合、例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00414<BR>
     * <BR>
     * ６)　@買付口座区分チェック<BR>
     * 　@６－１)　@this.買付口座区分がnullの場合、例外をスローする。<BR> 
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00415<BR>
     * 　@６－２)　@this.買付口座区分が以下のコード以外の場合、例外をスローする。<BR>
     * 　@　@　@　@・”一般”<BR>
     * 　@　@　@　@・”特定”<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00416<BR>
     * <BR>
     * ７）　@発注日のチェック<BR>
     *    this.発注日がnullである場合、例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00406<BR>
     * ８)　@注文IDチェック <BR>
     * 　@this.注文ID==nullの場合、例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00600<BR>
     * <BR>
     * ９）　@IDチェック<BR> 
     * 　@this.ID==nullの場合、例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00080<BR>
     * <BR>
     * @@roseuid 40A8A81202BB
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１)　@銘柄コードチェック
        if (WEB3StringTypeUtility.isEmpty(this.mutualProductCode))
        {
            log.debug("銘柄コードを入力してください。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "銘柄コードを入力してください。");
        }

        //２)　@指定方法@チェック
        //２－１)　@this.指定方法@がnullの場合、例外をスローする
        if (WEB3StringTypeUtility.isEmpty(this.specifyDiv))
        {
            log.debug("指定方法@が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00400,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "指定方法@が未指定です。");
        }
        else
        {
            //２－２)　@this.指定方法@が以下のコード以外の場合、例外をスローする
            if (!WEB3SellDivDef.ALL_DESIGNATE.equals(this.specifyDiv)
                && !WEB3SellDivDef.MONEY_DESIGNATE.equals(this.specifyDiv)
                && !WEB3SellDivDef.COUNT_DESIGNATE.equals(this.specifyDiv))
            {
                log.debug("指定方法@の値が存在しないコード値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00073,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "[全部] [金額] [口数]");
            }
        }

        // ３）　@(乗換)数量チェック <BR>
        // 　@３－１）　@this.指定方法@が“全部”且つ、this.数量!=nullの場合
        if (WEB3SellDivDef.ALL_DESIGNATE.equals(this.specifyDiv))
        {
            if (this.mutualOrderQuantity != null)
            {
                log.debug("指定方法@が“全部”の場合は、注文数量指定不可。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00093,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "指定方法@が“全部”の場合は、注文数量指定不可。");
            }
        }
        else
        {

            //３－２）　@this.指定方法@が“金額”または“口数”であり且つ、this.数量==nullの場合
            if (WEB3StringTypeUtility.isEmpty(this.mutualOrderQuantity))
            {
                log.debug("指定方法@が“金額”または“口数”であり且つ、" +
                    "注文数量が未指定です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00094,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "指定方法@が“金額”または“口数”であり且つ、" +
                    "注文数量が未指定です。");
            }

            //３－３）　@this.指定方法@が“金額”または“口数”であり且つ、this.数量が数値以外の場合
            if (!WEB3StringTypeUtility.isNumber(this.mutualOrderQuantity))
            {
                log.debug("指定方法@が“金額”または“口数”" +
                    "であり且つ注文数量が数値以外です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00095,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "指定方法@が“金額”または“口数”" +
                    "であり且つ注文数量が数値以外です。");
            }

            //３－４）　@this.指定方法@が“金額”または“口数”であり且つ、this.数量≦0の場合
            double l_dblMutualOrderQuantity =
                Double.parseDouble(this.mutualOrderQuantity);
            if (l_dblMutualOrderQuantity <= 0)
            {
                log.debug("指定方法@が“金額”または“口数”であり且つ、" +
                    "注文数量が0以下の値です");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00096,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "指定方法@が“金額”または“口数”であり且つ、" +
                    "注文数量が0以下の値です");
            }

            //３－５）　@this.指定方法@が“金額”または“口数”であり且つ、this.数量の桁数＞10桁の場合
            if (WEB3StringTypeUtility.getByteLength(this.mutualOrderQuantity)
                > 10)
            {
                log.debug("指定方法@が“金額”または“口数”であり、且つ、" +
                    "注文数量が10桁を超える値です");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00097,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "指定方法@が“金額”または“口数”であり、且つ、" +
                    "注文数量が10桁を超える値です");
            }
        }

        //４)　@請求方法@チェック
        //４－１)　@this.請求方法@がnullの場合、例外をスローする
        if (WEB3StringTypeUtility.isEmpty(this.sellBuyDiv))
        {
            log.debug("請求方法@が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00401,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "請求方法@が未指定です。");
        }
        else
        {
            //４－２)　@this.請求方法@が以下のコード以外の場合、例外をスローする
            if (!WEB3ClaimDivDef.SELL.equals(this.sellBuyDiv)
                && !WEB3ClaimDivDef.BUY.equals(this.sellBuyDiv))
            {
                log.debug("請求方法@が”解約請求””買取請求”以外の場合");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00402,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "請求方法@が”解約請求””買取請求”以外の場合");
            }
        }

        //５)　@銘柄コード（乗換先）チェック
        if (WEB3StringTypeUtility.isEmpty(this.switchingProductCode))
        {
            log.debug("銘柄コード（乗換先）が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00414,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "銘柄コード（乗換先）が未指定です。");
        }

        //６)　@買付口座区分チェック
        //６－１)　@this.買付口座区分がnullの場合、例外をスローする
        if (WEB3StringTypeUtility.isEmpty(this.switchingTaxType))
        {
            log.debug("買付口座区分が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00415,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "買付口座区分が未指定です。");
        }
        else
        {
            //６－２)　@this.買付口座区分が以下のコード以外の場合、例外をスローする
            if (!WEB3TaxTypeSpecialDef.NORMAL.equals(this.switchingTaxType)
                && !WEB3TaxTypeSpecialDef.SPECIAL.equals(this.switchingTaxType))
            {
                log.debug("買付口座区分が”一般”、”特定”以外の場合");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00416,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "買付口座区分が”一般”、”特定”以外の場合");
            }
        }

        //７）　@発注日のチェック
        if (WEB3StringTypeUtility.isEmpty(WEB3DateUtility.formatDate(this.orderedDate, "yyyyMMdd")))
        {
            log.debug("発注日が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00406,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注日が未指定です。");
        }
        
        //８)　@注文IDチェック
        if(this.orderId == null)
        {
            log.debug("注文IDが未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                getClass().getName() + "." + STR_METHOD_NAME,
                "注文IDが未指定です。");
        }

        // ９）　@IDチェック
        // 　@this.ID==nullの場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.id))
        {
            log.debug("投信資産ＩＤが未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                getClass().getName() + "." + STR_METHOD_NAME,
                "投信資産ＩＤが未指定です。");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
