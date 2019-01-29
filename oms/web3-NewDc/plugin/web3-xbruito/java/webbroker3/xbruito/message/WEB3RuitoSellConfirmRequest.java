head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoSellConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累投解約確認リクエストクラス(WEB3RuitoSellConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 周勇 (中訊) 新規作成
*/
package webbroker3.xbruito.message;
import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3PaymentMethodDef;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
/**
 * 累投解約確認リクエスト<BR>
 */
public class WEB3RuitoSellConfirmRequest extends WEB3RuitoCommonRequest
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoSellConfirmRequest.class);
    /**
     * PTYPE
     */
    public static final String PTYPE = "ruito_sell_confirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408031539L;  
        
    /**
     * 指定方法@<BR>
     * 2：全部、3：金額、4：口数<BR>
     */
    public String specifyDiv;
    /**
     * 受渡方法@<BR>
     * 1：銀行振込み、2：証券口座入金<BR>
     */
    public String deliveryDiv;
    /**
     * コンストラクタ<BR>
     * @@roseuid 40762CC30299
     */
    public WEB3RuitoSellConfirmRequest()
    {
    }
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@注文数量チェック<BR>
     * 　@１－１）　@this.指定方法@が“全部”且つ、<BR>
     *               this.注文数量がnull以外の値<BR>
     *              である場合、 例外をスローする。<BR>
     *               class    : WEB3BusinessLayerException<BR>
     *               tag      : BUSINESS_ERROR_00093<BR>
     * 　@１－２）　@this.指定方法@が“金額”または“口数”であり且つ、<BR>
     *              this.注文数量がnullである場合、例外をスローする。<BR>
     *               class    : WEB3BusinessLayerException<BR>
     *               tag      : BUSINESS_ERROR_00094<BR>
     *   １－３）　@this.指定方法@が“金額”または“口数”であり且つ、<BR>
     *           this.注文数量が数値以外である場合、例外をスローする。<BR>
     *               class    : WEB3BusinessLayerException<BR>
     *               tag      : BUSINESS_ERROR_00095<BR>
     * 　@１－４）　@this.指定方法@が“金額”または“口数”であり且つ、<BR>
     *           this.注文数量が0以下の値である場合、例外をスローする。<BR>
     *               class    : WEB3BusinessLayerException<BR>
     *               tag      : BUSINESS_ERROR_00096<BR>
     * 　@１－５）　@this.指定方法@が“金額”または“口数”であり且つ、<BR>
     *               this.注文数量が11桁を超える値である場合、<BR>
     *              例外をスローする。<BR>
     *               class    : WEB3BusinessLayerException<BR>
     *               tag      : BUSINESS_ERROR_00097<BR>
     * <BR>
     * ２）　@指定方法@チェック<BR>
     * 　@ this.指定方法@が、“全部”、“金額”、“口数”以外である場合、<BR>
     * 　@ 例外をスローする。<BR>
     *    class    : WEB3BusinessLayerException<BR>
     *    tag      : BUSINESS_ERROR_00073<BR>
     *    code     : 110<BR>
     * <BR>
     * ３）　@受渡方法@チェック<BR>
     *    this.受渡方法@が、“銀行振込み”または“証券口座入金”<BR>
     *    以外である場合、例外をスローする。<BR>
     *    class    : WEB3BusinessLayerException<BR>
     *    tag      : BUSINESS_ERROR_00099<BR>
     *    code     : 111<BR>
     * ４）　@銘柄コードチェック<BR>
     * 　@　@ this.銘柄コードがnullの値であれば例外をスローする。<BR>
     *      class    : WEB3BusinessLayerException<BR>
     *      tag      : BUSINESS_ERROR_00079<BR>
     *      code     : 91<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4073675B021F
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //指定方法@が“全部”且つ、注文数量がnull以外である場合、 例外をスローする
        if (WEB3SellDivDef.ALL_DESIGNATE.equals(this.specifyDiv) && 
            this.ruitoOrderQuantity != null &&
            WEB3StringTypeUtility.isNotEmpty(this.ruitoOrderQuantity))
        {
            log.debug("指定方法@が“全部”の場合は、注文数量指定不可。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00093,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "指定方法@が“全部”の場合は、注文数量指定不可。");            
        }

        //指定方法@が“金額”または“口数”であり且つ、注文数量がnullである場合、例外をスローする
        if ((WEB3SellDivDef.MONEY_DESIGNATE.equals(this.specifyDiv)
            || WEB3SellDivDef.COUNT_DESIGNATE.equals(this.specifyDiv))
            && this.ruitoOrderQuantity == null)
        {
            log.debug("指定方法@が“金額”または“口数”であり且つ、注文数量が未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00094,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "指定方法@が“金額”または“口数”であり且つ、注文数量が未指定です。");            
        }

        //指定方法@が“金額”または“口数”であり且つ、注文数量が数値以外である場合、例外をスローする
        if (WEB3SellDivDef.MONEY_DESIGNATE.equals(this.specifyDiv)
            || WEB3SellDivDef.COUNT_DESIGNATE.equals(this.specifyDiv))
        {
            try
            {
                double l_dblTemp = Double.parseDouble(this.ruitoOrderQuantity);
            }
            catch (NumberFormatException l_ex)
            {
                log.debug("指定方法@が“金額”または“口数”であり且つ注文数量が数値以外です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00095,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "指定方法@が“金額”または“口数”であり且つ注文数量が数値以外です。");
            }
        }

        //指定方法@が“金額”または“口数”であり且つ,注文数量が0以下の値である場合、例外をスローする
        if (WEB3SellDivDef.MONEY_DESIGNATE.equals(this.specifyDiv)
            || WEB3SellDivDef.COUNT_DESIGNATE.equals(this.specifyDiv))
        {
            if (Double.parseDouble(this.ruitoOrderQuantity) <= 0)
            {
                log.debug("指定方法@が“金額”または“口数”であり且つ、注文数量が0以下の値です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00096,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "指定方法@が“金額”または“口数”であり且つ,注文数量が0以下の値である場合");
            }
        }

        //指定方法@が“金額”または“口数”であり且つ、注文数量が11桁を超える値である場合、例外をスローする
        if (WEB3SellDivDef.MONEY_DESIGNATE.equals(this.specifyDiv)
            || WEB3SellDivDef.COUNT_DESIGNATE.equals(this.specifyDiv))
        {
            if (this.ruitoOrderQuantity.length() > 11)
            {
                log.debug("指定方法@が“金額”または“口数”であり、且つ、注文数量が11桁を超える値です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00097,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "指定方法@が“金額”または“口数”であり且つ、注文数量が11桁を超える値である場合");
            }
        }

        //指定方法@が、“全部”、“金額”、“口数”以外である場合、例外をスローする
        if (!(WEB3SellDivDef.ALL_DESIGNATE.equals(this.specifyDiv)
            || WEB3SellDivDef.MONEY_DESIGNATE.equals(this.specifyDiv)
            || WEB3SellDivDef.COUNT_DESIGNATE.equals(this.specifyDiv)))
        {
            log.debug("指定方法@の値が存在しないコード値です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00073,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "指定方法@の値が存在しないコード値です。");
        }

        //受渡方法@が、“銀行振込み”または“証券口座入金”以外である場合、例外をスローする
        if (!(WEB3PaymentMethodDef.BANK_TRANSFER.equals(this.deliveryDiv)
            || WEB3PaymentMethodDef.SECURITIES_ACCOUNT_INPUT.equals(
                this.deliveryDiv)))
        {
            log.debug("受渡方法@が、“銀行振込み”または“証券口座入金”以外です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00099,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "受渡方法@が、“銀行振込み”または“証券口座入金”以外です。");
        }

        //銘柄コードがnullの値であれば例外をスローする
        if (WEB3StringTypeUtility.isEmpty(this.ruitoProductCode))
        {
            log.debug("銘柄コードが入力されていません。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "銘柄コードが入力されていません。");
        }

        log.exiting(STR_METHOD_NAME);
    }
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 累投解約確認レスポンスを作成する<BR>
     * <BR>
     * 取引時間管理.get発注日()で取得した発注日をリクエストデータ.<BR>
     * 確認時発注日に設定する。<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 40762B4200E4
     */
    public WEB3GenResponse createResponse()
    {
        final String STR_METHOD_NAME = "createResponse()";
        log.entering(STR_METHOD_NAME);
        //発注日取得
        Date l_orderBizDate = null;

        // ----------------------------------------------- delete according to QA:WEB3-RUITO-A-CD-0044
//        try
//        {
//            l_orderBizDate =
//                WEB3GentradeTradingTimeManagement.getOrderBizDate();
//        }
//        catch (WEB3SystemLayerException l_ex)
//        {
//            log.error(
//                "__an NullPoint error__",
//                new WEB3SystemLayerException(
//                    WEB3ErrorCatalog.BUSINESS_ERROR_00078,
//                    this.getClass().getName() + STR_METHOD_NAME));
//        }
//        log.debug(
//            "end l_orderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate()");
        // ----------------------------------------------- delete according to QA:WEB3-RUITO-A-CD-0044

        WEB3RuitoSellConfirmResponse l_RuitoSellConfirmResponse =
            new WEB3RuitoSellConfirmResponse(this);

        // ----------------------------------------------- delete according to QA:WEB3-RUITO-A-CD-0044
//        l_RuitoSellConfirmResponse.checkDate = l_orderBizDate;
        // ----------------------------------------------- delete according to QA:WEB3-RUITO-A-CD-0044

        log.exiting(STR_METHOD_NAME);
        return l_RuitoSellConfirmResponse;
    }
}
@
