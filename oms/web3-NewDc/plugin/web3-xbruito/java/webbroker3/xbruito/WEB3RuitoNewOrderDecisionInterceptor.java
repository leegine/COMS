head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoNewOrderDecisionInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累投新規注文確定インタセプタ(WEB3RuitoNewOrderDecisionInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 韋念瓊 (中訊) 新規作成
*/
package webbroker3.xbruito;

import java.util.Date;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitParams;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.common.define.WEB3BalanceAddFlagDef;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;


/**
 * 累投新規注文確定インタセプタ<BR>
 */
public class WEB3RuitoNewOrderDecisionInterceptor
    extends WEB3RuitoDefaultRuitoOrderDecisionInterceptor
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        WEB3RuitoMRFCancelAcceptUnitServiceInterceptor.class);

    /**
     * 識別コード<BR>
     */
    private String requestNumber;

    /**
     * 累投の買付でMRFの自動解約を行う場合、対応する注文の識別コードを設定する。<BR>
     */
    private String MRFOrderRequestNumber;

    /**
     * 返還方法@<BR>
     * 1:当日解約　@3:キャッシング(野村MMFの時)<BR>
     */
    private String returnMethod;

    /**
     * 1：銀行振込み、2：証券口座入金<BR>
     */
    private String paymentMethod;

    /**
     * 0:その他　@1:MMF　@2:中期国債ファ@ンド　@3:MRF<BR>
     */
    private RuitoTypeEnum ruitoTypeEnum;

    /**
     * 2:全部指定　@3:金額指定　@4:口数指定<BR>
     */
    private String ruitoSellDiv;

    /**
     * 1：コールセンター　@2：ＰＣ　@3:スリングショット<BR>
     * 4：i-mode　@5：Vodafone　@6：AU　@9：HOST<BR>
     * （コールセンターの時のみ使用）<BR>
     */
    private String orderRootDiv;

    /**
     * 0：営業店　@1：インターネット　@2：コールセンタ　@3：モバイル<BR>
     */
    private String orderChannel;

    /**
     * （mutateのオーバーライド）<BR>
     * <BR>
     * 引数で与えられた累投注文単位Paramsに値を設定し、<BR>
     *        累投注文単位Paramsを返す。<BR>
     * <BR>
     * １）　@受渡日の設定を行う<BR>
     * 　@－累投注文単位Params.getBranchId()に該当する<BR>
     *       証券会社オブジェクトを取得する。<BR>
     * 　@－拡張累投銘柄マネージャ.getProduct()をコールし、<BR>
     *      銘柄オブジェクトを取得する。<BR>
     * 　@　@［getProductに渡すパラメタ］<BR>
     * 　@　@　@銘柄ID： 累投注文単位Params.getProductId()の戻り値<BR>
     * 　@－拡張累投銘柄マネージャ.toProduct()をコールして、<BR>
     *          拡張累投銘柄オブジェクトを取得する。<BR>
     * 　@　@［toProductに渡すパラメタ］<BR>
     * 　@　@　@銘柄Row： 取得した銘柄オブジェクト.getDataSourceObject()<BR>
     *       の戻り値<BR>
     * 　@－拡張累投銘柄マネージャ.get累投取引銘柄()をコールし、<BR>
     *         拡張累投取引銘柄オブジェクトを取得する。<BR>
     * 　@　@［get累投取引銘柄に渡すパラメタ］<BR>
     * 　@　@　@証券会社： 取得した証券会社オブジェクト<BR>
     * 　@　@　@銘柄コード： <BR>
     *        取得した拡張累投銘柄オブジェクト.getProductCode()の戻り値<BR>
     * 　@－拡張累投取引銘柄.get受渡日()をコールし、受渡日を取得する。<BR>
     * 　@　@［get受渡日に渡すパラメタ］<BR>
     * 　@　@　@is買付： <BR>
     * 　@　@　@　@(*) 累投注文単位Params.getOrderType()の戻り値が<BR>
     * OrderTypeEnum.RUITO_BUYの場合はtrueを設定する。<BR>
     * 　@　@　@　@(*) 累投注文単位Params.getOrderType()の戻り値が<BR>
     * OrderTypeEnum.RUITO_SELLの場合はfalseを設定する。<BR>
     * 　@－累投注文単位Params.setDeliveryDate()をコールし、<BR>
     * 受渡日を設定する。<BR>
     * 　@　@［setDeliveryDateに渡すパラメタ］<BR>
     * 　@　@　@受渡日： 取得した受渡日<BR>
     * <BR>
     * ２）　@受注日時の設定を行う。<BR>
     * 　@－ThreadLocalSystemAttributesRegistry.getAttribute( )をコールし、<BR>
     *      処理日時を取得する。<BR>
     * 　@　@［getAttributeに渡すパラメタ］<BR>
     * 　@　@　@設定キー： ”xblocks.gtl.attributes.systemtimestamp”<BR>
     * 　@－累投注文単位Params.setReceivedDateTime()をコールし、<BR>
     *      受注日時の設定を行う。<BR>
     * 　@　@［setReceivedDateTimeに渡すパラメタ］<BR>
     * 　@　@　@受注日時： 取得した処理日時<BR>
     * <BR>
     * ３）　@扱者コード(SONAR)の設定を行う。<BR>
     * 　@－累投注文単位Params.getAccountId()に<BR>
     *      対応する顧客オブジェクトを取得する。<BR>
     * 　@－累投注文単位Params.setSonarTraderCode()をコールし、<BR>
     *      扱者コード(SONAR)の設定を行う。<BR>
     * 　@　@［setSonarTraderCodeに渡すパラメタ］<BR>
     * 　@　@　@扱者コード(SONAR)： <BR>
     *      顧客.getDataSourceObject().getSonarTraderCode()<BR>
     *          の戻り値<BR>
     * ４）　@注文エラー理由コードの設定を行う。<BR>
     * 　@－累投注文単位Params.set注文エラー理由コード()をコールし、<BR>
     *       注文エラー理由コードの設定を行う。<BR>
     * 　@　@［set注文エラー理由コードに渡すパラメタ］<BR>
     * 　@　@　@注文エラー理由コード： null<BR>
     * <BR>
     * ５）　@残高計上フラグの設定を行う。<BR>
     * 　@－累投注文単位Params.set残高計上フラグ()をコールし、<BR>
     *      残高計上フラグの設定を行う。<BR>
     * 　@　@[set残高計上フラグに渡すパラメタ]<BR>
     * 　@　@　@残高計上フラグ： ”0：残高計上未済”<BR>
     * <BR>
     * ６）　@当オブジェクトに設定されている値を<BR>
     *     累投注文単位Paramsに設定する。<BR>
     * <BR>
     * 　@　@累投注文単位Params.setOrderRequestNumber<BR>
     *          (this.get識別コード())<BR>
     * 　@　@累投注文単位Params.setPaymentMethod(this.get受渡方法@)<BR>
     * 　@　@累投注文単位Params.setMrfOrderRequestNumber<BR>
     *              (this.getMRF識別コード())<BR>
     * 　@　@累投注文単位Params.setRuitoType(this.get累投タイプ())<BR>
     * 　@　@累投注文単位Params.setGpSellDiv(this.get累投解約区分())<BR>
     * 　@　@累投注文単位Params.setReturnMethod(this.get返還方法@())<BR>
     * 　@　@累投注文単位Params.setOrderRootDiv(this.get注文経路区分())<BR>
     * 　@　@累投注文単位Params.setOrderChanel(this.get注文チャネル())<BR>
     * <BR>
     * ７）　@引数で与えられた累投注文単位Paramsを返す。<BR>
     * @@param l_persistenceType - データの更新または挿入中に呼ぶかどうか指定<BR>
     * @@param l_persistenceContext - 
     * 呼び出し時のコンテキストを指定。例えば現物注文中など<BR>
     * @@param l_unitParams - 永続化前の累投注文単位Params<BR>
     * @@return RuitoOrderUnitParams
     * @@roseuid 4070B277028B
     */
    public RuitoOrderUnitParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_persistenceContext,
        RuitoOrderUnitParams l_unitParams)
    {
        String STR_METHOD_NAME =
            "mutate("
                + "OrderManagerPersistenceType l_persistenceType,"
                + "OrderManagerPersistenceContext l_persistenceContext,"
                + "RuitoOrderUnitParams l_unitParams)";

        WEB3GentradeBranch l_branch = null;
        Institution l_institution = null;
        RuitoOrderUnitParams l_orderUnitParams = null;
        l_orderUnitParams = l_unitParams;

        try
        {
            if (l_unitParams == null)
            {
                // --------- Start
                // Modify by Alan Wang 2004/08/13 according to ソースコードチェック指摘事項(Ruito 20040802版ベース).xls NO.8
//                throw new WEB3BaseException(
//                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
//                    this.getClass().getName() + STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
                // ------------ End
            }
            //FinAppサービスを取得
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gAccMgr =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();

            //証券会社取得 NotFoundException
            l_branch = (WEB3GentradeBranch) l_gAccMgr.getBranch(
                l_orderUnitParams.getBranchId());
            l_institution = l_branch.getInstitution();

            //銘柄オブジェクトを取得する。 
            WEB3RuitoProductManager l_ruitoProductManager =
                (WEB3RuitoProductManager) l_finApp
                    .getTradingModule(ProductTypeEnum.RUITO)
                    .getProductManager();
            
            //NotFoundException
            WEB3RuitoProduct l_ruitoProduct = (WEB3RuitoProduct)
                l_ruitoProductManager.getProduct(l_orderUnitParams.getProductId());

//            //－拡張累投銘柄マネージャ.toProduct()をコールして
//            WEB3RuitoProduct l_ruitoProduct = null;
//            l_ruitoProduct =
//                (WEB3RuitoProduct) l_ruitoProductManager.toProduct(
//                    (Row) l_product.getDataSourceObject());

            //－拡張累投銘柄マネージャ.get累投取引銘柄()をコールし
            WEB3RuitoTradedProduct l_ruitoTradeProduct = null;            
            l_ruitoTradeProduct =
                (WEB3RuitoTradedProduct) l_ruitoProductManager.getRuitoTradedProduct(
                l_institution,
                l_ruitoProduct.getProductCode());

            //－拡張累投取引銘柄.get受渡日()をコールし、受渡日を取得する。            
            boolean isBuy = false;
            if (OrderTypeEnum.RUITO_BUY.equals(l_orderUnitParams.getOrderType()))
            {
                isBuy = true;
            }
            if (OrderTypeEnum.RUITO_SELL.equals(l_orderUnitParams.getOrderType()))
            {
                isBuy = false;
            }

            Date l_datDeliveryDate = l_ruitoTradeProduct.getDailyDeliveryDate(isBuy);
            l_orderUnitParams.setDeliveryDate(l_datDeliveryDate);

            //２）　@受注日時の設定を行う。
            String l_strSystemTs = null;
            l_strSystemTs = "xblocks.gtl.attributes.systemtimestamp";
            l_datDeliveryDate =
                (Date) ThreadLocalSystemAttributesRegistry.getAttribute(l_strSystemTs);
            l_orderUnitParams.setReceivedDateTime(l_datDeliveryDate);

            // ３）　@扱者コード(SONAR)の設定を行う。
            String l_sonarTraderCode;

            //顧客オブジェクトを取得する
            MainAccount l_acc = null;

            AccountManager l_accMgr = l_finApp.getAccountManager();
            l_acc = l_accMgr.getMainAccount(l_orderUnitParams.getAccountId());
            MainAccountRow l_accRow = (MainAccountRow) l_acc.getDataSourceObject();
            l_sonarTraderCode = l_accRow.getSonarTraderCode();
            l_orderUnitParams.setSonarTraderCode(l_sonarTraderCode);

            //４）　@注文エラー理由コードの設定を行う。
            l_orderUnitParams.setErrorReasonCode(null);

            //５）　@残高計上フラグの設定を行う。
            l_orderUnitParams.setBalanceAddFlag(
                WEB3BalanceAddFlagDef.BALANCE_OUTSTANDING);

            //６）　@当オブジェクトに設定されている値を累投注文単位Paramsに設定する。
            l_orderUnitParams.setOrderRequestNumber(this.getRequestNumber());
            l_orderUnitParams.setPaymentMethod(this.getPaymentMethod());
            l_orderUnitParams.setMrfOrderRequestNumber(this.MRFOrderRequestNumber);
            l_orderUnitParams.setRuitoType(this.getRuitoTypeEnum());
            l_orderUnitParams.setGpSellDiv(this.getRuitoSellDiv());
            l_orderUnitParams.setReturnMethod(this.getReturnMethod());
            l_orderUnitParams.setOrderRootDiv(this.getOrderRootDiv());
            l_orderUnitParams.setOrderChanel(this.getOrderChannel());

        }
        catch (NotFoundException l_ex)
        {         
         // --------- Start
            // Modify by Alan Wang 2004/08/13 according to ソースコードチェック指摘事項(Ruito 20040802版ベース).xls No.8
//            log.error(
//                "__NotFoundException__",
//                new WEB3SystemLayerException(
//                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
//                    this.getClass().getName() + STR_METHOD_NAME,
//                    l_ex.getMessage(),
//                    l_ex));

            log.error("該当する証券会社または銘柄オブジェクトまたは" +
                    "累投取引銘柄または顧客オブジェクトがありません"); 
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
            // ------------ End
        }
        // --------- Start
        // Modify by Alan Wang 2004/08/13 according to ソースコードチェック指摘事項(Ruito 20040802版ベース).xls No.8
//        catch (WEB3BaseException l_ex)
//        {
//            log.error(
//                "__parameter_error__",
//                new WEB3SystemLayerException(
//                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
//                    this.getClass().getName() + STR_METHOD_NAME,
//                    l_ex.getMessage(),
//                    l_ex));
//        }
        // ------------ End

        //７）　@引数で与えられた累投注文単位Paramsを返す。
        return l_orderUnitParams;
    }

    /**
     * （mutateのオーバーライド）<BR>
     * <BR>
     * 引数で与えられた累投注文履歴Paramsに値を設定し、<BR>
     *     累投注文履歴Paramsを返す。<BR>
     * <BR>
     * １）　@注文エラー理由コードの設定を行う。<BR>
     * 　@－累投注文履歴Params.set注文エラー理由コード()をコールし、<BR>
     *         注文エラー理由コードの設定を行う。<BR>
     * 　@　@［set注文エラー理由コードに渡すパラメタ］<BR>
     * 　@　@　@注文エラー理由コード： null<BR>
     * <BR>
     * ２）　@引数で与えられた累投注文履歴Paramsを返す。<BR>
     * @@param l_persistenceType - データの更新または挿入中に呼ぶかどうか指定<BR>
     * @@param l_persistenceContext - 
     * 呼び出し時のコンテキストを指定。例えば現物注文中など<BR>
     * @@param l_actionParams - 永続化前の累投注文履歴Params<BR>
     * @@return RuitoOrderActionParams
     * @@roseuid 408CAA44000A
     */
    public RuitoOrderActionParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_persistenceContext,
        RuitoOrderActionParams l_actionParams)
    {
        //１）　@注文エラー理由コードの設定を行う。
        //RuitoOrderActionParams l_ruitoOrderActionParams = null;
        //l_ruitoOrderActionParams.setErrorReasonCode(null);

        //２）　@引数で与えられた累投注文履歴Paramsを返す。        
        //return l_ruitoOrderActionParams;
        
        //１）　@注文エラー理由コードの設定を行う。
        l_actionParams.setErrorReasonCode(null);
        //２）　@引数で与えられた累投注文履歴Paramsを返す。        
        return l_actionParams;
    }

    /**
     * 累投注文単位の識別コードを設定する。<BR>
     * @@param l_strRequestNumber - 累投注文単位の識別コード<BR>
     * @@roseuid 4070B4A40308
     */
    public void setRequestNumber(String l_strRequestNumber)
    {
        requestNumber = l_strRequestNumber;
    }

    /**
     * this.識別コードを返す。<BR>
     * @@return String
     * @@roseuid 4070B6290318
     */
    public String getRequestNumber()
    {
        return requestNumber;
    }

    /**
     * MRF注文識別コードを設定する。<BR>
     * 累投の買付注文でMRFの自動解約を行う場合、<BR>
     * 対応する識別コードを設定する。<BR>
     * @@param l_strMRFRequestNumber - MRF識別コード<BR>
     * @@roseuid 4070B4B201A1
     */
    public void setMRFOrderRequestNumber(String l_strMRFRequestNumber)
    {
        MRFOrderRequestNumber = l_strMRFRequestNumber;
    }

    /**
     * this.MRF識別コードを返す。<BR>
     * @@return String
     * @@roseuid 4070B6390114
     */
    public String getMRFOrderRequestNumber()
    {
        return MRFOrderRequestNumber;
    }

    /**
     * 返還方法@の設定を行う。<BR>
     * @@param l_strReturnMethod - 返還方法@<BR>
     * @@roseuid 40767684000F
     */
    public void setReturnMethod(String l_strReturnMethod)
    {
        returnMethod = l_strReturnMethod;
    }

    /**
     * this.返還方法@を返す。<BR>
     * @@return String
     * @@roseuid 407676DF0203
     */
    public String getReturnMethod()
    {
        return returnMethod;
    }

    /**
     * 受渡方法@の設定を行う。<BR>
     * @@param l_strPaymentMethod - 受渡方法@<BR>
     * @@roseuid 407A4A4A00BB
     */
    public void setPaymentMethod(String l_strPaymentMethod)
    {
        paymentMethod = l_strPaymentMethod;
    }

    /**
     * this.受渡方法@を返す。<BR>
     * @@return String
     * @@roseuid 407A4B9D0157
     */
    public String getPaymentMethod()
    {
        return paymentMethod;
    }

    /**
     * 累投タイプの設定を行う。<BR>
     * @@param ruitoType - 累投タイプ<BR>
     * @@roseuid 407A4AF3033C
     */
    public void setRuitoTypeEnum(RuitoTypeEnum l_ruitoTypeEnum)
    {
        ruitoTypeEnum = l_ruitoTypeEnum;
    }

    /**
     * this.累投タイプを返す。<BR>
     * @@return RuitoTypeEnum
     * @@roseuid 407A4BAA01E4
     */
    public RuitoTypeEnum getRuitoTypeEnum()
    {
        return ruitoTypeEnum;
    }

    /**
     * 累投解約区分の設定を行う。<BR>
     * @@param l_strRuitoSellDiv - 累投解約区分<BR>
     * @@roseuid 407A4B04003E
     */
    public void setRuitoSellDiv(String l_strRuitoSellDiv)
    {
        ruitoSellDiv = l_strRuitoSellDiv;
    }

    /**
     * this.累投解約区分を返す。<BR>
     * @@return String
     * @@roseuid 407A4BB90157
     */
    public String getRuitoSellDiv()
    {
        return ruitoSellDiv;
    }

    /**
     * 注文経路区分の設定を行う。<BR>
     * @@param l_strOrderRootDiv - 注文経路区分<BR>
     * @@roseuid 407C90A40186
     */
    public void setOrderRootDiv(String l_strOrderRootDiv)
    {
        orderRootDiv = l_strOrderRootDiv;
    }

    /**
     * this.注文経路区分を返す。<BR>
     * @@return String
     * @@roseuid 407C90D30119
     */
    public String getOrderRootDiv()
    {
        return orderRootDiv;
    }

    /**
     * 注文チャネルの設定を行う。<BR>
     * @@param l_strOrderChannel - 注文チャネル<BR>
     * @@roseuid 408CD3AB01B0
     */
    public void setOrderChannel(String l_strOrderChannel)
    {
        orderChannel = l_strOrderChannel;
    }

    /**
     * this.注文チャネルを返す。<BR>
     * @@return String
     * @@roseuid 408CD3B203B4
     */
    public String getOrderChannel()
    {
        return orderChannel;
    }

//    /**
//     * @@param l_persistenceType
//     * @@param l_persistenceContext
//     * @@param l_executionParams
//     * @@return RuitoOrderExecutionParams
//     * @@roseuid 40C019F102CA
//     */
//    /*public RuitoOrderExecutionParams setUpdatePrice(
//        OrderManagerPersistenceType l_persistenceType,
//        OrderManagerPersistenceContext l_persistenceContext,
//        RuitoOrderExecutionParams l_executionParams)
//    {
//        return null;
//    }*/
//
//    /**
//     * @@param l_persistenceType
//     * @@param l_tableRow
//     * @@return com.fitechlabs.xtrade.kernel.data.BatchedQuery
//     * @@roseuid 40C019F201DB
//     */
//    /*public BatchedQuery getQueryToExecute(
//        OrderManagerPersistenceType l_persistenceType,
//        Class l_tableRow)
//    {
//        return null;
//    }*/
//
//    /**
//     * @@param arg0
//     * @@param arg1
//     * @@param arg2
//     * @@return RuitoOrderUnitParams
//     * @@roseuid 40C473EE02FD
//     */
//    /*public RuitoOrderUnitParams mutate(
//        OrderManagerPersistenceType arg0,
//        OrderManagerPersistenceContext arg1,
//        RuitoOrderUnitParams arg2)
//    {
//        return null;
//    }*/
//
//    /**
//     * @@param arg0
//     * @@param arg1
//     * @@param arg2
//     * @@return RuitoOrderExecutionParams
//     * @@roseuid 40C473F000EA
//     */
//    /*public RuitoOrderExecutionParams mutate(
//        OrderManagerPersistenceType arg0,
//        OrderManagerPersistenceContext arg1,
//        RuitoOrderExecutionParams arg2)
//    {
//        return null;
//    }*/
//
//    /**
//     * @@param arg0
//     * @@param arg1
//     * @@param arg2
//     * @@return RuitoOrderActionParams
//     * @@roseuid 40C473F102AF
//     */
//    /*public RuitoOrderActionParams mutate(
//        OrderManagerPersistenceType arg0,
//        OrderManagerPersistenceContext arg1,
//        RuitoOrderActionParams arg2)
//    {
//        return null;
//    }*/
}
@
