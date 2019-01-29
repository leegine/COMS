head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.54.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccOptionSettleContractOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :（連続）OP返済注文サービスImpl(WEB3ToSuccOptionSettleContractOrderServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/04/09 楊夫志(中訊) 新規作成 モデル283,321
 Revision History : 2008/04/18 孟亞南(中訊) 仕様変更モデルNo.330 No.333
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.math.BigDecimal;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.common.define.WEB3ContractCheckDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoSettleContractOrderSpec;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.service.delegate.WEB3OptionSettleContractOrderRequestAdapter;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionSettleContractOrderServiceImpl;
import webbroker3.triggerorder.WEB3ToSuccIfoOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionSettleContractOrderRequestAdapter;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionSettleContractOrderService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (（連続）OP返済注文サービスImpl)<BR>
 * （連続）オプション返済注文サービス実装クラス<BR>
 * <BR>
 * @@author 楊夫志
 * @@version 1.0
 */
public class WEB3ToSuccOptionSettleContractOrderServiceImpl
    extends WEB3OptionSettleContractOrderServiceImpl
    implements WEB3ToSuccOptionSettleContractOrderService
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccOptionSettleContractOrderServiceImpl.class);

    /**
     * @@roseuid 47FDBE4100CB
     */
    public WEB3ToSuccOptionSettleContractOrderServiceImpl()
    {

    }

    /**
     * （連続）株価指数オプション返済注文サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータの型により、validate注文()または、submit注文()メソッドをコールする。<BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエスト。<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A923070071
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        WEB3GenResponse l_response = null;
        //（連続）株価指数オプション返済注文確認リクエスト
        if (l_request instanceof WEB3SuccOptionsCloseConfirmRequest)
        {
            //this.validate注文()
            l_response = this.validateOrder((WEB3SuccOptionsCloseConfirmRequest)l_request);
        }
        //（連続）株価指数オプション訂正返済完了リクエスト
        else if (l_request instanceof WEB3SuccOptionsCloseCompleteRequest)
        {
            //this.submit注文()
            l_response = this.submitOrder((WEB3SuccOptionsCloseCompleteRequest)l_request);
        }
        else
        {
            log.debug("パラメータタイプ不正");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate注文)<BR>
     * （連続）株価指数オプションの返済発注審査を行う。<BR>
     * <BR>
     * 「（（連続）OP返済サービス）validate注文」参照。<BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエスト。<BR>
     * @@return WEB3SuccOptionsCloseConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A923070081
     */
    protected WEB3SuccOptionsCloseConfirmResponse validateOrder(
        WEB3SuccOptionsCloseConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrder(WEB3SuccOptionsCloseConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //validate
        l_request.validate();
        //連続注文マネージャを取得する
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        //get先物OP親注文の注文単位(long)
        //（親注文）注文ID：　@リクエスト.連続注文共通情報の同項目
        IfoOrderUnit l_ifoOrderUnit =
            l_orderManager.getIfoParentOrderUnit(
                Long.parseLong(l_request.succCommonInfo.parentOrderId));
        //get補助口座
        SubAccount l_subAccount = this.getSubAccount();

        //（連続）OP返済注文リクエストアダプタ
        WEB3ToSuccOptionSettleContractOrderRequestAdapter l_adapter =
            (WEB3ToSuccOptionSettleContractOrderRequestAdapter)this.createRequestAdapter(l_request);

        //リクエストアダプタ.is反対売買()
        boolean l_blnIsReversingOrder = l_adapter.isReversingOrder();
        WEB3IfoProductImpl l_product = (WEB3IfoProductImpl)l_ifoOrderUnit.getProduct();

        String l_strProductCode = null;
        if (l_blnIsReversingOrder)
        {
            //get先物OP親注文の注文単位()の戻り値.銘柄IDに該当する先物OP銘柄.原資産銘柄コード
            l_strProductCode = l_product.getUnderlyingProductCode();
        }
        else
        {
            //リクエストアダプタ.get建玉()の戻り値.銘柄IDに該当する先物OP銘柄.原資産銘柄コード
            WEB3IfoContractImpl l_ifoContractImpl = l_adapter.getContract();
            WEB3IfoProductImpl l_ifoProduct = (WEB3IfoProductImpl)l_ifoContractImpl.getProduct();
            l_strProductCode = l_ifoProduct.getUnderlyingProductCode();
        }
        //reset銘柄コード
        WEB3GentradeTradingTimeManagement.resetProductCode(l_strProductCode);

        //validate連続注文
        l_orderManager.validateSuccOrder(
            (WEB3GentradeSubAccount)l_subAccount,
            ProductTypeEnum.IFO,
            WEB3FuturesOptionDivDef.OPTION,
            l_request.succCommonInfo.succTradingType,
            l_ifoOrderUnit);

        //validate連続注文最大設定数
        this.validateSuccOrderMaxQuantity(l_ifoOrderUnit);

        //validate注文
        WEB3SuccOptionsCloseConfirmResponse l_response =
            (WEB3SuccOptionsCloseConfirmResponse)super.validateOrder(l_request);
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit注文)<BR>
     * （連続）株価指数オプションの返済注文を登録する。<BR>
     * <BR>
     * 「（（連続）OP返済サービス）submit注文」参照。<BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエスト。<BR>
     * @@return WEB3SuccOptionsCloseCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A923070090
     */
    protected WEB3SuccOptionsCloseCompleteResponse submitOrder(
        WEB3SuccOptionsCloseCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOrder(WEB3SuccOptionsCloseCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //validate
        l_request.validate();
        //連続注文マネージャを取得する
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        //get先物OP親注文の注文単位(long)
        //（親注文）注文ID：　@リクエスト.連続注文共通情報の同項目
        IfoOrderUnit l_ifoOrderUnit =
            l_orderManager.getIfoParentOrderUnit(
                Long.parseLong(l_request.succCommonInfo.parentOrderId));
        //get補助口座
        SubAccount l_subAccount = this.getSubAccount();

        //（連続）OP返済注文リクエストアダプタ
        WEB3ToSuccOptionSettleContractOrderRequestAdapter l_adapter =
            (WEB3ToSuccOptionSettleContractOrderRequestAdapter)this.createRequestAdapter(l_request);

        //リクエストアダプタ.is反対売買()
        boolean l_blnIsReversingOrder = l_adapter.isReversingOrder();
        WEB3IfoProductImpl l_product = (WEB3IfoProductImpl)l_ifoOrderUnit.getProduct();

        String l_strProductCode = null;
        if (l_blnIsReversingOrder)
        {
            //get先物OP親注文の注文単位()の戻り値.銘柄IDに該当する先物OP銘柄.原資産銘柄コード
            l_strProductCode = l_product.getUnderlyingProductCode();
        }
        else
        {
            //リクエストアダプタ.get建玉()の戻り値.銘柄IDに該当する先物OP銘柄.原資産銘柄コード
            WEB3IfoContractImpl l_ifoContractImpl = l_adapter.getContract();
            WEB3IfoProductImpl l_ifoProduct = (WEB3IfoProductImpl)l_ifoContractImpl.getProduct();
            l_strProductCode = l_ifoProduct.getUnderlyingProductCode();
        }
        //reset銘柄コード
        WEB3GentradeTradingTimeManagement.resetProductCode(l_strProductCode);

        //validate連続注文
        l_orderManager.validateSuccOrder(
            (WEB3GentradeSubAccount)l_subAccount,
            ProductTypeEnum.IFO,
            WEB3FuturesOptionDivDef.OPTION,
            l_request.succCommonInfo.succTradingType,
            l_ifoOrderUnit);

        //validate連続注文最大設定数
        this.validateSuccOrderMaxQuantity(l_ifoOrderUnit);

        WEB3SuccOptionsCloseCompleteResponse l_response =
            (WEB3SuccOptionsCloseCompleteResponse)super.submitOrder(l_request);

        //notify予約注文登録
        this.notifyRsvOrderRegister(Long.parseLong(l_request.orderId));

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate連続注文最大設定数)<BR>
     * 連続注文の最大設定数を超過してしまわないかどうかをチェックする。 <BR>
     * <BR>
     * ※処理の詳細はシーケンス図「（（連続）OP返済サービス）validate注文」<BR>
     * 　@「（（連続）OP返済サービス）submit注文」参照<BR>
     * @@param l_parentOrderUnit - (親注文の注文単位)<BR>
     * 親注文の注文単位。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47CE5E0700C0
     */
    protected void validateSuccOrderMaxQuantity(OrderUnit l_parentOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateSuccOrderMaxQuantity(OrderUnit)";
        log.entering(STR_METHOD_NAME);
        //連続注文マネージャを取得する
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        //連続注文マネージャ.validate連続注文最大設定数
        l_orderManager.validateSuccOrderMaxQuantity(l_parentOrderUnit);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (notify予約注文登録)<BR>
     * 予約注文の登録をルールエンジンサーバに通知する。<BR>
     * <BR>
     * ※処理の詳細はシーケンス図「（（連続）OP返済サービス）submit注文」参照<BR>
     * @@param l_lngChildOrderId - (子注文の注文ID)<BR>
     * 子注文の注文ID。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47CE5E07010F
     */
    protected void notifyRsvOrderRegister(long l_lngChildOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "notifyRsvOrderRegister(long)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        //連続注文マネージャを取得する
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        //get先物OP予約注文単位(long)
        //注文ID：　@パラメータ.子注文の注文ID
        WEB3ToSuccIfoOrderUnitImpl l_orderUnit = l_orderManager.getReserveIfoOrderUnit(l_lngChildOrderId);

        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl =
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();

        l_optionOrderManagerImpl.notifyRLS(l_orderUnit, OrderManagerPersistenceContext.NEW_SETTLE_CONTRACT_ORDER);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (createリクエストアダプタ)<BR>
     * リクエストアダプタのインスタンスを生成する。 <BR>
     * （継承元クラスの同名メソッドのオーバーライド） <BR>
     * <BR>
     * （連続）OP返済注文リクエストアダプタ.create(引数のリクエスト)をコールする。 <BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエストデータ。<BR>
     * @@return WEB3OptionSettleContractOrderRequestAdapter
     * @@throws WEB3BaseException
     * @@roseuid 47CE5E07019B
     */
    protected WEB3OptionSettleContractOrderRequestAdapter createRequestAdapter(
        WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        return WEB3ToSuccOptionSettleContractOrderRequestAdapter.create(l_request);
    }

    /**
     * (create返済建玉エントリ)<BR>
     * 返済建玉エントリを作成する。 <BR>
     * （継承元クラスの同名メソッドのオーバーライド） <BR>
     * <BR>
     * １）　@反対売買の場合 <BR>
     * 　@（パラメータ.リクエストアダプタ.is反対売買() == true）<BR>
     * 　@連続注文マネージャImpl.create返済建玉エントリ()をコールし、 <BR>
     * 　@戻り値を返却する。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@返済建玉：　@生成した返済建玉(*1)<BR>
     * <BR>
     * 　@(*1)以下のプロパティをセットした返済建玉インスタンス<BR>
     * 　@　@のみを要素とする配列<BR>
     * <BR>
     * 　@　@返済建玉.注文数量：　@this.get注文数量()の戻り値を設定する。<BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@　@リクエストアダプタ：　@パラメータ.リクエストアダプタ<BR>
     * <BR>
     * ２）　@１）以外の場合、 <BR>
     * 　@super.create返済建玉エントリ()をコールし、 <BR>
     * 　@戻り値を返却する。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@本メソッドの引数をそのまま設定。 <BR>
     * <BR>
     * @@param l_requestAdapter - (リクエストアダプタ)<BR>
     * リクエストアダプタ。<BR>
     * @@param l_closeMarginContractUnits - (返済建玉オブジェクトの配列)<BR>
     * 返済建玉オブジェクトの配列。<BR>
     * （リクエストデータ）<BR>
     * @@return SettleContractEntry[]
     * @@throws WEB3BaseException
     * @@roseuid 47CE5E070228
     */
    protected SettleContractEntry[] createSettleContractEntry(
        WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter,
        WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createSettleContractEntry("
            + "WEB3OptionSettleContractOrderRequestAdapter,"
            + "WEB3FuturesOptionsCloseMarginContractUnit[])";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccOptionSettleContractOrderRequestAdapter l_adapter =
            (WEB3ToSuccOptionSettleContractOrderRequestAdapter)l_requestAdapter;

        SettleContractEntry[] l_settleContractEntries = null;

        // 反対売買の場合
        if (l_adapter.isReversingOrder())
        {
            // this.get注文数量()の戻り値を設定する。
            double l_dblOrderQuantity = this.getOrderQuantity(l_adapter);

            int l_intLength = l_closeMarginContractUnits.length;
            for (int i = 0; i < l_intLength; i++)
            {
                l_closeMarginContractUnits[i].contractOrderQuantity =
                    WEB3StringTypeUtility.formatNumber(l_dblOrderQuantity);
            }

            // 連続注文マネージャImplを取得
            WEB3ToSuccOrderManagerImpl l_orderManager =
                (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

            // 連続注文マネージャImpl.create返済建玉エントリ()
            l_settleContractEntries =
                l_orderManager.createSettleContractEntry(l_closeMarginContractUnits);
        }
        // 以外の場合
        else
        {
            l_settleContractEntries =
                super.createSettleContractEntry(l_requestAdapter, l_closeMarginContractUnits);
        }

        log.exiting(STR_METHOD_NAME);
        return l_settleContractEntries;
    }

    /**
     * (get注文数量)<BR>
     * 返済建玉に設定する注文数量を返却する。<BR>
     * <BR>
     * １）　@注文数量を求める。　@<BR>
     * 　@　@リクエストアダプタ.リクエストデータ.決済順序≠"ランダム"の場合は、<BR>
     * 　@　@　@　@リクエストアダプタ.リクエストデータ.注文数量 を使用。<BR>
     * 　@　@リクエストアダプタ.リクエストデータ.決済順序=="ランダム"の場合は、<BR>
     * 　@　@　@　@リクエストアダプタ.リクエストデータ.返済建玉の全要素の注文数量のSUM値を使用。<BR>
     * <BR>
     * ２）　@１）で求めた注文数量 > リクエストアダプタ.親注文の注文単位.注文数量の場合、<BR>
     * 　@　@「注文数量が親注文の注文数量を超過」の例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_03065<BR>
     * <BR>
     * ３）　@２）の条件に該当しない場合、１）で求めた注文数量を返却する。<BR>
     * <BR>
     * @@param l_requestAdapter - (リクエストアダプタ)<BR>
     * リクエストアダプタ。<BR>
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 47CE5E0702F3
     */
    private double getOrderQuantity(WEB3ToSuccOptionSettleContractOrderRequestAdapter l_requestAdapter)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderQuantity(WEB3ToSuccOptionSettleContractOrderRequestAdapter)";
        log.entering(STR_METHOD_NAME);

        // 注文数量を求める。
        double l_dblOrderQuantity = 0.0D;

        if (l_requestAdapter.request instanceof WEB3SuccOptionsCloseConfirmRequest)
        {
            WEB3SuccOptionsCloseConfirmRequest l_request =
                (WEB3SuccOptionsCloseConfirmRequest)l_requestAdapter.request;

            // 取得した決済順序≠"ランダム"の場合
            if (!WEB3ClosingOrderDef.RANDOM.equals(l_request.closingOrder))
            {
                // リクエストアダプタ.リクエストデータ.注文数量 を使用
                l_dblOrderQuantity = Double.parseDouble(l_request.opOrderQuantity);
            }
            // 取得した決済順序=="ランダム"の場合
            else
            {
                BigDecimal l_bdOrderQuantitySum = new BigDecimal("0");

                int l_intLength = l_request.closeMarginContractUnits.length;
                // リクエストアダプタ.リクエストデータ.返済建玉の全要素の注文数量のSUM値を使用。
                for (int i = 0; i < l_intLength; i++)
                {
                    String l_strOrderQuantity = l_request.closeMarginContractUnits[i].contractOrderQuantity;
                    l_bdOrderQuantitySum = l_bdOrderQuantitySum.add(new BigDecimal(l_strOrderQuantity));
                }

                l_dblOrderQuantity = l_bdOrderQuantitySum.doubleValue();
            }
        }
        else if (l_requestAdapter.request instanceof WEB3SuccOptionsCloseCompleteRequest)
        {
            WEB3SuccOptionsCloseCompleteRequest l_request =
                (WEB3SuccOptionsCloseCompleteRequest)l_requestAdapter.request;

            // 取得した決済順序≠"ランダム"の場合
            if (!WEB3ClosingOrderDef.RANDOM.equals(l_request.closingOrder))
            {
                // リクエストアダプタ.リクエストデータ.注文数量 を使用
                l_dblOrderQuantity = Double.parseDouble(l_request.opOrderQuantity);
            }
            // 取得した決済順序=="ランダム"の場合
            else
            {
                BigDecimal l_bdOrderQuantitySum = new BigDecimal("0");

                int l_intLength = l_request.closeMarginContractUnits.length;
                // リクエストアダプタ.リクエストデータ.返済建玉の全要素の注文数量のSUM値を使用。
                for (int i = 0; i < l_intLength; i++)
                {
                    String l_strOrderQuantity = l_request.closeMarginContractUnits[i].contractOrderQuantity;
                    l_bdOrderQuantitySum = l_bdOrderQuantitySum.add(new BigDecimal(l_strOrderQuantity));
                }

                l_dblOrderQuantity = l_bdOrderQuantitySum.doubleValue();
            }
        }
        else
        {
            log.debug("パラメータタイプ不正");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正");
        }

        // 求めた注文数量 > リクエストアダプタ.親注文の注文単位.注文数量の場合
        if (l_dblOrderQuantity > l_requestAdapter.parentOrderUnit.getQuantity())
        {
            log.debug("注文数量が親注文の注文数量を超過しています。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03065,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文数量が親注文の注文数量を超過しています。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblOrderQuantity;
    }

    /**
     * (validateOP返済注文)<BR>
     * OP返済注文の発注審査を実施する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@反対売買の場合<BR>
     * 　@（パラメータ.リクエストアダプタ.is反対売買() == true）<BR>
     * 　@OP注文マネージャ.validate返済注文()<BR>
     * 　@をコールする。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@補助口座：　@パラメータ.補助口座 <BR>
     * 　@　@返済注文内容：　@パラメータ.返済注文内容 <BR>
     * 　@　@建玉：　@パラメータ.リクエストアダプタ.get建玉()<BR>
     * <BR>
     * ２）　@１）以外の場合 <BR>
     * 　@super.validateOP返済注文()をコールする。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@補助口座：　@パラメータ.補助口座 <BR>
     * 　@　@返済注文内容：　@パラメータ.返済注文内容 <BR>
     * 　@　@リクエストアダプタ：　@パラメータ.リクエストアダプタ<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座。<BR>
     * @@param l_ifoOrderSpec - (返済注文内容)<BR>
     * 返済注文内容オブジェクト。<BR>
     * @@param l_requestAdapter - (リクエストアダプタ)<BR>
     * リクエストアダプタ。<BR>
     * @@return NewOrderValidationResult
     * @@throws WEB3BaseException
     * @@roseuid 47CE5E07038F
     */
    protected NewOrderValidationResult validateOptionsSettleContractOrder(
        SubAccount l_subAccount,
        WEB3IfoSettleContractOrderSpec l_ifoOrderSpec,
        WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOptionsSettleContractOrder("
            + "SubAccount,"
            + "IfoSettleContractOrderSpec,"
            + "WEB3OptionSettleContractOrderRequestAdapter)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3ToSuccOptionSettleContractOrderRequestAdapter l_adapter =
            (WEB3ToSuccOptionSettleContractOrderRequestAdapter)l_requestAdapter;

        NewOrderValidationResult l_result = null;
        //反対売買の場合
        if (l_adapter.isReversingOrder())
        {
            //OP注文マネージャImplを取得
            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();

            //OP注文マネージャ.validate返済注文()
            l_result =
                l_orderManager.validateSettleContractOrder(
                    (WEB3GentradeSubAccount)l_subAccount,
                    l_ifoOrderSpec,
                    l_adapter.getContract());
        }
        else
        {
            //super.validateOP返済注文()
            l_result =
                super.validateOptionsSettleContractOrder(l_subAccount, l_ifoOrderSpec, l_requestAdapter);
        }
        log.exiting(STR_METHOD_NAME);
        return l_result;
    }

    /**
     * (create建玉明細)<BR>
     * 返済建玉エントリより建玉明細の一覧を作成する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@反対売買の場合、<BR>
     * 　@（パラメータ.リクエストアダプタ.is反対売買() == true）<BR>
     * 　@以下の手順にて建玉明細を作成する。<BR>
     * 　@１－１）　@連続注文マネージャImpl.create建玉明細()を<BR>
     * 　@　@コールする。<BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@　@注文単位：　@パラメータ.リクエストアダプタ.親注文の注文単位<BR>
     * <BR>
     * 　@１－２）　@プロパティセットしたインスタンスのみを要素とする<BR>
     * 　@　@配列を生成し、返却する。<BR>
     * <BR>
     * ２）　@既存残に対する返済（１）以外）の場合、<BR>
     * 　@super.create建玉明細()をコールし、<BR>
     * 　@戻り値を返却する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@本メソッドの引数をそのまま設定。<BR>
     * <BR>
     * @@param l_settleContractEntries - (返済建玉エントリ)<BR>
     * 返済建玉エントリ。<BR>
     * @@param l_requestAdapter - (リクエストアダプタ)<BR>
     * リクエストアダプタ。<BR>
     * @@param l_ifoTradedProduct - (先物OP取引銘柄)<BR>
     * 先物OP取引銘柄。<BR>
     * @@return WEB3FuturesOptionsContractUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 47CE5E08037F
     */
    protected WEB3FuturesOptionsContractUnit[] createContractUnit(
        SettleContractEntry[] l_settleContractEntries,
        WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter,
        WEB3IfoTradedProductImpl l_ifoTradedProduct)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createContractUnit("
            + "SettleContractEntry[], "
            + "WEB3OptionSettleContractOrderRequestAdapter,"
            + "WEB3IfoTradedProductImpl)";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccOptionSettleContractOrderRequestAdapter l_adapter =
            (WEB3ToSuccOptionSettleContractOrderRequestAdapter)l_requestAdapter;

        WEB3FuturesOptionsContractUnit[] l_contractUnits = null;
        if (l_adapter.isReversingOrder())
        {
            // 連続注文マネージャImplを取得
            WEB3ToSuccOrderManagerImpl l_orderManager =
                (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

            //連続注文マネージャImpl.create建玉明細()
            WEB3FuturesOptionsContractUnit l_contractUnit =
                l_orderManager.createContractUnit(l_adapter.parentOrderUnit);

            l_contractUnits = new WEB3FuturesOptionsContractUnit[]{l_contractUnit};
        }
        else
        {
            //super.create建玉明細()
            l_contractUnits =
                super.createContractUnit(l_settleContractEntries, l_requestAdapter, l_ifoTradedProduct);
        }

        log.exiting(STR_METHOD_NAME);
        return l_contractUnits;
    }

    /**
     * (submit返済注文)<BR>
     * OP返済注文を登録する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * 連続注文マネージャImpl.submit先物OP返済新規予約注文()をコールする。<BR>
     * <BR>
     * [引数]<BR>
     * 補助口座：　@引数の補助口座<BR>
     * 注文内容：　@引数の返済注文内容<BR>
     * 注文ID：　@引数の注文ID<BR>
     * 取引パスワード：　@引数のリクエストアダプタ.リクエスト.暗証番号<BR>
     * 連続注文取引区分：　@引数のリクエストアダプタ.リクエスト.連続注文共通情報.連続注文取引区分<BR>
     * 単価調整値：　@引数のリクエストアダプタ.リクエスト.単価調整値情報.get単価調整値()<BR>
     * 　@　@※引数のリクエストアダプタ.リクエスト.単価調整値情報==nullの場合は、nullをセット<BR>
     * 親注文の注文単位：　@引数のリクエストアダプタ.親注文の注文単位<BR>
     * 計算結果：　@引数の概算受渡代金計算結果<BR>
     * 建玉：　@引数のリクエストアダプタ.get建玉()<BR>
     * 決済順序：　@パラメータ.リクエストアダプタ.リクエストデータ.決済順序<BR>
     * <BR>
     * @@param l_requestAdapter - (リクエストアダプタ)<BR>
     * リクエストアダプタ。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座。<BR>
     * @@param l_orderSpec - (返済注文内容)<BR>
     * 返済注文内容。<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 注文ID。<BR>
     * @@param l_commission - (手数料)<BR>
     * 手数料。<BR>
     * @@param l_estimateDeliveryAmountCalcResult - (概算受渡代金計算結果)<BR>
     * 概算受渡代金計算結果。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47CE5E090062
     */
    protected void submitSettleContractOrder(
        WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter,
        SubAccount l_subAccount,
        WEB3IfoSettleContractOrderSpec l_orderSpec,
        long l_lngOrderId,
        WEB3GentradeCommission l_commission,
        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitSettleContractOrder("
            + "WEB3OptionSettleContractOrderRequestAdapter, "
            + "SubAccount,"
            + "IfoSettleContractOrderSpec,"
            + "long,"
            + "WEB3GentradeCommission,"
            + "WEB3IfoEstimateDeliveryAmountCalcResult)";
        log.entering(STR_METHOD_NAME);

        // 連続注文マネージャImplを取得
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        WEB3ToSuccOptionSettleContractOrderRequestAdapter l_adapter =
            (WEB3ToSuccOptionSettleContractOrderRequestAdapter)l_requestAdapter;
        WEB3SuccOptionsCloseCompleteRequest l_request =
            (WEB3SuccOptionsCloseCompleteRequest)l_adapter.request;

        //リクエストアダプタ.リクエスト.単価調整値情報.get単価調整値()
        Double l_priceAdjustmentValue = null;
        if (l_request.priceAdjustmentValueInfo != null)
        {
            l_priceAdjustmentValue = new Double(l_request.priceAdjustmentValueInfo.getPriceAdjustmentValue());
        }

        //連続注文マネージャImpl.submit先物OP返済新規予約注文
        l_orderManager.submitIfoCloseContractNewOrder(
            (WEB3GentradeSubAccount)l_subAccount,
            l_orderSpec,
            l_lngOrderId,
            l_request.password,
            l_request.succCommonInfo.succTradingType,
            l_priceAdjustmentValue,
            l_adapter.parentOrderUnit,
            l_estimateDeliveryAmountCalcResult,
            l_adapter.getContract(),
            l_request.closingOrder);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set単価)<BR>
     * 引数のレスポンス．調整後単価に単価を設定する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）リクエスト.単価調整値情報≠null（±指値指定）の場合<BR>
     * 　@　@レスポンス．調整後単価に、引数のリクエストアダプタ．get単価()の戻り値をセットする。<BR>
     * <BR>
     * ２）上記以外の場合、<BR>
     * 　@　@何もせずリターンする。<BR>
     * <BR>
     * @@param l_requestAdapter - (OP返済注文リクエストアダプタ)<BR>
     * OP返済注文リクエストアダプタ。<BR>
     * @@param l_response - (レスポンス)<BR>
     * レスポンス。<BR>
     * @@roseuid 47CE5E090208
     */
    protected void setPrice(
        WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter,
        WEB3GenResponse l_response)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "setPrice("
            + "WEB3OptionSettleContractOrderRequestAdapter,"
            + "WEB3GenResponse)";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccOptionSettleContractOrderRequestAdapter l_adapter =
            (WEB3ToSuccOptionSettleContractOrderRequestAdapter)l_requestAdapter;
        WEB3SuccOptionsCloseConfirmRequest l_request =
            (WEB3SuccOptionsCloseConfirmRequest)l_adapter.request;

        //リクエスト.単価調整値情報≠null（±指値指定）の場合
        if (l_request.priceAdjustmentValueInfo != null)
        {
            WEB3SuccOptionsCloseConfirmResponse l_confirmResponse =
                (WEB3SuccOptionsCloseConfirmResponse)l_response;
            l_confirmResponse.afterAdjustmentPrice = WEB3StringTypeUtility.formatNumber(l_adapter.getPrice());
            log.exiting(STR_METHOD_NAME);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
    }

    /**
     * (exec余力再計算)<BR>
     * 余力再計算を行う。<BR>
     * （継承元クラスの同名メソッドのオーバーライド） <BR>
     * <BR>
     * 何もせずにreturnする。（カラ実装）<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。<BR>
     * @@roseuid 47CF38FD0220
     */
    public void execReCalcTradingPower(SubAccount l_subAccount)
    {
        return;
    }

    /**
     * (get日計り区分)<BR>
     * 日計り区分を取得する。<BR>
     * <BR>
     * １）　@反対売買の場合、<BR>
     * （パラメータ.リクエストアダプタ.is反対売買() == true）<BR>
     * 「日計り」を返却する。(*1) <BR>
     * <BR>
     * ２）　@既存残に対する返済（１）以外）の場合<BR>
     * 　@super.get日計り区分()をコールし、<BR>
     * 　@戻り値を返却する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@本メソッドの引数をそのまま設定。<BR>
     * <BR>
     * (*1) 日計り区分は、「定数定義インタフェイス：WEB3ContractCheckDef」を参照<BR>
     * @@param l_settleContractEntries - (返済建玉エントリ)<BR>
     * 返済建玉エントリ<BR>
     * @@param l_requestAdapter - (リクエストアダプタ)<BR>
     * リクエストアダプタ<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    protected String getDayTradeType(SettleContractEntry[] l_settleContractEntries,
        WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getDayTradeType(SettleContractEntry[], WEB3OptionSettleContractOrderRequestAdapter)";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccOptionSettleContractOrderRequestAdapter l_adapter =
            (WEB3ToSuccOptionSettleContractOrderRequestAdapter)l_requestAdapter;
        String l_strDayTrade = null;

        //パラメータ.リクエストアダプタ.is反対売買() == true
        if (l_adapter.isReversingOrder())
        {
            l_strDayTrade = WEB3ContractCheckDef.DAY_TRADE;
        }
        else
        {
            //super.get日計り区分()
            l_strDayTrade = super.getDayTradeType(l_settleContractEntries, l_requestAdapter);
        }

        log.exiting(STR_METHOD_NAME);
        return l_strDayTrade;
    }
}
@
