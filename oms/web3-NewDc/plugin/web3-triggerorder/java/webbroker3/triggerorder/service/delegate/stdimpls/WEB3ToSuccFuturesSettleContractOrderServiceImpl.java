head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.52.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccFuturesSettleContractOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :（連続）先物返済注文サービスImpl（WEB3ToSuccFuturesSettleContractOrderServiceImpl.java）
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/03/18 孟亞南(中訊) 新規作成モデルNo.259
 Revision History : 2008/04/18 孟亞南(中訊) 仕様変更モデルNo.330 No.333
 */
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.math.BigDecimal;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
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
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoSettleContractOrderSpec;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.service.delegate.WEB3FuturesSettleContractOrderRequestAdapter;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesSettleContractOrderServiceImpl;
import webbroker3.triggerorder.WEB3ToSuccIfoOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesSettleContractOrderRequestAdapter;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesSettleContractOrderService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (（連続）先物返済注文サービスImpl)<BR>
 * （連続）先物返済注文サービス実装クラス<BR>
 *
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3ToSuccFuturesSettleContractOrderServiceImpl
    extends WEB3FuturesSettleContractOrderServiceImpl
    implements WEB3ToSuccFuturesSettleContractOrderService
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccFuturesSettleContractOrderServiceImpl.class);

    /**
     * @@roseuid 47D6593403C9
     */
    public WEB3ToSuccFuturesSettleContractOrderServiceImpl()
    {

    }

    /**
     * （連続）先物返済サービス処理を実施する。<BR>
     * <BR>
     * 　@リクエストデータの型により、以下のメソッドを呼び分ける。<BR>
     * <BR>
     * 　@　@[（連続）株価指数先物返済注文確認リクエストの場合]<BR>
     * 　@　@　@this.validate注文()をコールする。<BR>
     * 　@　@[（連続）株価指数先物返済注文完了リクエストの場合]<BR>
     * 　@　@　@this.submit注文()をコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A94DBA028A
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
        //（連続）株価指数先物返済注文確認リクエスト
        if (l_request instanceof WEB3SuccFuturesCloseConfirmRequest)
        {
            //this.validate注文()
            l_response = this.validateOrder((WEB3SuccFuturesCloseConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3SuccFuturesCloseCompleteRequest)
        {
            //this.submit注文()
            l_response = this.submitOrder((WEB3SuccFuturesCloseCompleteRequest)l_request);
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
     * （連続）先物返済の発注審査を行う。<BR>
     * <BR>
     * 「（（連続）先物返済サービス）validate注文」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3SuccFuturesCloseConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A94DBA02A9
     */
    protected WEB3SuccFuturesCloseConfirmResponse validateOrder(WEB3SuccFuturesCloseConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrder(WEB3SuccFuturesCloseConfirmRequest)";
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

        //（連続）先物返済注文リクエストアダプタ
        WEB3ToSuccFuturesSettleContractOrderRequestAdapter l_adapter =
            (WEB3ToSuccFuturesSettleContractOrderRequestAdapter)this.createRequestAdapter(l_request);

        //リクエストアダプタ.is反対売買()
        boolean l_blnReversingOrder = l_adapter.isReversingOrder();
        WEB3IfoProductImpl l_product = (WEB3IfoProductImpl)l_ifoOrderUnit.getProduct();

        String l_strProductCode = null;
        if (l_blnReversingOrder)
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
            WEB3FuturesOptionDivDef.FUTURES,
            l_request.succCommonInfo.succTradingType,
            l_ifoOrderUnit);

        //validate連続注文最大設定数
        this.validateSuccOrderMaxQuantity(l_ifoOrderUnit);

        //validate注文
        WEB3SuccFuturesCloseConfirmResponse l_response =
            (WEB3SuccFuturesCloseConfirmResponse)super.validateOrder(l_request);
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit注文)<BR>
     * （連続）先物返済の注文を登録する。<BR>
     * <BR>
     * 「（（連続）先物返済サービス）submit注文」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3SuccFuturesCloseCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A94DBA02B9
     */
    protected WEB3SuccFuturesCloseCompleteResponse submitOrder(WEB3SuccFuturesCloseCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOrder(WEB3SuccFuturesCloseCompleteRequest)";
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

        //（連続）先物返済注文リクエストアダプタ
        WEB3ToSuccFuturesSettleContractOrderRequestAdapter l_adapter =
            (WEB3ToSuccFuturesSettleContractOrderRequestAdapter)this.createRequestAdapter(l_request);

        //リクエストアダプタ.is反対売買()
        boolean l_blnReversingOrder = l_adapter.isReversingOrder();
        WEB3IfoProductImpl l_product = (WEB3IfoProductImpl)l_ifoOrderUnit.getProduct();

        String l_strProductCode = null;
        if (l_blnReversingOrder)
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
            WEB3FuturesOptionDivDef.FUTURES,
            l_request.succCommonInfo.succTradingType,
            l_ifoOrderUnit);

        //validate連続注文最大設定数
        this.validateSuccOrderMaxQuantity(l_ifoOrderUnit);

        WEB3SuccFuturesCloseCompleteResponse l_response =
            (WEB3SuccFuturesCloseCompleteResponse)super.submitOrder(l_request);

        //notify予約注文登録
        this.notifyRsvOrderRegister(Long.parseLong(l_request.orderId));

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate連続注文最大設定数)<BR>
     * 連続注文の最大設定数を超過してしまわないかどうかをチェックする。<BR>
     * <BR>
     * ※処理の詳細はシーケンス図「（（連続）先物返済サービス）validate注文」<BR>
     * 「（（連続）先物返済サービス）submit注文」参照<BR>
     * @@param l_parentOrderUnit - (親注文の注文単位)<BR>
     * 親注文の注文単位<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47BB83FE0222
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
     * ※処理の詳細はシーケンス図「（（連続）先物返済サービス）submit注文」参照<BR>
     * @@param l_lngRsvOrderId - (子注文の注文ID)<BR>
     * 子注文の注文ID。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47BB8DD7008C
     */
    protected void notifyRsvOrderRegister(long l_lngRsvOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "notifyRsvOrderRegister(long)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        //連続注文マネージャを取得する
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        //get先物OP予約注文単位(long)
        //（親注文）注文ID：　@リクエスト.連続注文共通情報の同項目
        WEB3ToSuccIfoOrderUnitImpl l_orderUnit = l_orderManager.getReserveIfoOrderUnit(l_lngRsvOrderId);

        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl =
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();

        l_optionOrderManagerImpl.notifyRLS(l_orderUnit, OrderManagerPersistenceContext.NEW_SETTLE_CONTRACT_ORDER);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (createリクエストアダプタ)<BR>
     * リクエストアダプタのインスタンスを生成する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド） <BR>
     * <BR>
     * （連続）先物返済注文リクエストアダプタ.create(引数のリクエスト)をコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3FuturesSettleContractOrderRequestAdapter
     * @@throws WEB3BaseException
     * @@roseuid 47BBD11302B9
     */
    protected WEB3FuturesSettleContractOrderRequestAdapter createRequestAdapter(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        return WEB3ToSuccFuturesSettleContractOrderRequestAdapter.create(l_request);
    }

    /**
     * (create返済建玉エントリ)<BR>
     * 返済建玉エントリを作成する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@反対売買の場合<BR>
     * 　@（パラメータ.リクエストアダプタ.is反対売買() == true）<BR>
     * 　@連続注文マネージャImpl.create返済建玉エントリ()をコールし、<BR>
     * 　@戻り値を返却する。 <BR>
     * <BR>
     * 　@[引数]<BR>
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
     * ２）　@１）以外の場合、<BR>
     * 　@super.create返済建玉エントリ()をコールし、<BR>
     * 　@戻り値を返却する。 <BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@本メソッドの引数をそのまま設定。<BR>
     * @@param l_requestAdapter - (リクエストアダプタ)<BR>
     * リクエストアダプタ<BR>
     * @@param l_closeMarginContractUnits - (返済建玉)<BR>
     * 返済建玉オブジェクトの配列<BR>
     * （リクエストデータ）<BR>
     * @@return SettleContractEntry[]
     * @@throws WEB3BaseException
     * @@roseuid 47BCD8C50210
     */
    protected SettleContractEntry[] createSettleContractEntry(
        WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter,
        WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createSettleContractEntry("
            + "WEB3FuturesSettleContractOrderRequestAdapter, WEB3FuturesOptionsCloseMarginContractUnit[])";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccFuturesSettleContractOrderRequestAdapter l_adapter =
            (WEB3ToSuccFuturesSettleContractOrderRequestAdapter)l_requestAdapter;

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
     * １）　@注文数量を求める。<BR>
     * 　@　@取得した決済順序≠"ランダム"の場合は、<BR>
     * 　@　@　@　@リクエストアダプタ.リクエストデータ.注文数量 を使用。<BR>
     * 　@　@取得した決済順序=="ランダム"の場合は、<BR>
     * 　@　@　@　@リクエストアダプタ.リクエストデータ.返済建玉の全要素の注文数量のSUM値を使用。<BR>
     * <BR>
     * ２）　@１）で求めた注文数量 > リクエストアダプタ.親注文の注文単位.注文数量の場合、<BR>
     * 　@　@「注文数量が親注文の注文数量を超過」の例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_03065<BR>
     * <BR>
     * ３）　@２）の条件に該当しない場合、１）で求めた注文数量を返却する。<BR>
     * @@param l_requestAdapter - (リクエストアダプタ)<BR>
     * リクエストアダプタ<BR>
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 47BD406F02FD
     */
    private double getOrderQuantity(WEB3ToSuccFuturesSettleContractOrderRequestAdapter l_requestAdapter)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderQuantity(WEB3ToSuccFuturesSettleContractOrderRequestAdapter)";
        log.entering(STR_METHOD_NAME);

        // 注文数量を求める。
        double l_dblOrderQuantity = 0.0D;

        if (l_requestAdapter.request instanceof WEB3SuccFuturesCloseConfirmRequest)
        {
            WEB3SuccFuturesCloseConfirmRequest l_request =
                (WEB3SuccFuturesCloseConfirmRequest)l_requestAdapter.request;

            // 取得した決済順序≠"ランダム"の場合
            if (!WEB3ClosingOrderDef.RANDOM.equals(l_request.closingOrder))
            {
                // リクエストアダプタ.リクエストデータ.注文数量 を使用
                l_dblOrderQuantity = Double.parseDouble(l_request.futOrderQuantity);
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
        else if (l_requestAdapter.request instanceof WEB3SuccFuturesCloseCompleteRequest)
        {
            WEB3SuccFuturesCloseCompleteRequest l_request =
                (WEB3SuccFuturesCloseCompleteRequest)l_requestAdapter.request;

            // 取得した決済順序≠"ランダム"の場合
            if (!WEB3ClosingOrderDef.RANDOM.equals(l_request.closingOrder))
            {
                // リクエストアダプタ.リクエストデータ.注文数量 を使用
                l_dblOrderQuantity = Double.parseDouble(l_request.futOrderQuantity);
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
     * (validate先物返済注文)<BR>
     * 先物返済注文の発注審査を実施する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@反対売買の場合<BR>
     * 　@（パラメータ.リクエストアダプタ.is反対売買() == true）<BR>
     * 　@先物注文マネージャ.validate先物返済注文()<BR>
     * 　@をコールする。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@補助口座：　@パラメータ.補助口座 <BR>
     * 　@　@返済注文内容：　@パラメータ.返済注文内容 <BR>
     * 　@　@建玉：　@パラメータ.リクエストアダプタ.get建玉()<BR>
     * <BR>
     * ２）　@１）以外の場合 <BR>
     * 　@super.validate先物返済注文()をコールする。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@補助口座：　@パラメータ.補助口座 <BR>
     * 　@　@返済注文内容：　@パラメータ.返済注文内容 <BR>
     * 　@　@リクエストアダプタ：　@パラメータ.リクエストアダプタ<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_settleContractOrderSpec - (返済注文内容)<BR>
     * 返済注文内容オブジェクト<BR>
     * @@param l_requestAdapter - (リクエストアダプタ)<BR>
     * リクエストアダプタ<BR>
     * @@return NewOrderValidationResult
     * @@throws WEB3BaseException
     * @@roseuid 47BEA46A0104
     */
    protected NewOrderValidationResult validateFuturesSettleContractOrder(
        SubAccount l_subAccount,
        WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec,
        WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateFuturesSettleContractOrder("
            + "SubAccount, WEB3IfoSettleContractOrderSpec, WEB3FuturesSettleContractOrderRequestAdapter)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3ToSuccFuturesSettleContractOrderRequestAdapter l_adapter =
            (WEB3ToSuccFuturesSettleContractOrderRequestAdapter)l_requestAdapter;

        NewOrderValidationResult l_result = null;
        //反対売買の場合
        if (l_adapter.isReversingOrder())
        {
            //先物注文マネージャImplを取得
            WEB3FuturesOrderManagerImpl l_orderManager =
                (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();

            //先物注文マネージャ.validate先物返済注文()
            l_result =
                l_orderManager.validateFuturesSettleContractOrder(
                    (WEB3GentradeSubAccount)l_subAccount,
                    l_settleContractOrderSpec,
                    l_adapter.getContract());
        }
        else
        {
            //super.validate先物返済注文()
            l_result =
                super.validateFuturesSettleContractOrder(l_subAccount, l_settleContractOrderSpec, l_requestAdapter);
        }
        log.exiting(STR_METHOD_NAME);
        return l_result;
    }

    /**
     * (get概算決済損益)<BR>
     * 概算決済損益を取得する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@反対売買の場合、<BR>
     * 　@（パラメータ.リクエストアダプタ.is反対売買() == true）<BR>
     * 　@以下の手順にて建玉を取得する。<BR>
     * <BR>
     * 　@１－１）　@連続注文マネージャImpl.create建玉()を<BR>
     * 　@　@コールし、建玉を作成する。<BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@　@注文単位：　@パラメータ.リクエストアダプタ.親注文の注文単位<BR>
     * <BR>
     * 　@１－２）　@先物注文マネージャ.calc概算決済損益()をコールし、<BR>
     * 　@　@　@戻り値を返却する。<BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@　@手数料：　@パラメータの同項目<BR>
     * 　@　@　@指値：　@パラメータの同項目<BR>
     * 　@　@　@補助口座：　@パラメータの同項目<BR>
     * 　@　@　@先物OP取引銘柄：　@パラメータの同項目<BR>
     * 　@　@　@返済建玉エントリ： 　@パラメータの同項目<BR>
     * 　@　@　@数量：　@パラメータの同項目<BR>
     * 　@　@　@売買： <BR>
     * 　@　@　@　@[取得した建玉.isLong() == trueの場合]<BR>
     * 　@　@　@　@　@SideEnum.BUY<BR>
     * 　@　@　@　@[以外]<BR>
     * 　@　@　@　@　@SideEnum.SELL<BR>
     * 　@　@　@isSkip金額チェック：  false<BR>
     * 　@　@　@先物OP建玉：　@１－１）の戻り値<BR>
     * <BR>
     * ２）　@既存残に対する返済（１）以外）の場合、<BR>
     * 　@super.get概算決済損益()をコールし、<BR>
     * 　@戻り値を返却する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@本メソッドの引数をそのまま設定。<BR>
     * @@param l_genCommission - (手数料)<BR>
     * @@param l_dblLimitPrice - (指値)<BR>
     * 指値<BR>
     * <BR>
     * 0が指定された場合は、時価を計算単価として利用する。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * @@param l_tradedProduct - (先物OP取引銘柄)<BR>
     * 先物OP取引銘柄<BR>
     * @@param l_settleContractEntrys - (返済建玉エントリ[])<BR>
     * @@param l_dblQuantity - (数量)<BR>
     * 数量<BR>
     * @@param l_requestAdapter - (リクエストアダプタ)<BR>
     * リクエストアダプタ<BR>
     * @@return WEB3IfoEstimateDeliveryAmountCalcResult
     * @@throws WEB3BaseException
     * @@roseuid 47C21FF50205
     */
    protected WEB3IfoEstimateDeliveryAmountCalcResult getEstimateSettlementIncome(
        WEB3GentradeCommission l_genCommission,
        double l_dblLimitPrice,
        SubAccount l_subAccount,
        WEB3IfoTradedProductImpl l_tradedProduct,
        SettleContractEntry[] l_settleContractEntrys,
        double l_dblQuantity,
        WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getEstimateSettlementIncome("
            + "WEB3GentradeCommission,"
            + "double,"
            + "SubAccount,"
            + "WEB3IfoTradedProductImpl,"
            + "SettleContractEntry[], "
            + "double,"
            + "WEB3FuturesSettleContractOrderRequestAdapter)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3ToSuccFuturesSettleContractOrderRequestAdapter l_adapter =
            (WEB3ToSuccFuturesSettleContractOrderRequestAdapter)l_requestAdapter;

        WEB3IfoEstimateDeliveryAmountCalcResult l_amountCalcResult = null;
        //反対売買の場合
        if (l_adapter.isReversingOrder())
        {
            // 連続注文マネージャImplを取得
            WEB3ToSuccOrderManagerImpl l_orderManager =
                (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

            // 連続注文マネージャImpl.create建玉()
            WEB3IfoContractImpl l_ifoContractImpl =
                l_orderManager.createIfoContract(l_adapter.parentOrderUnit);

            SideEnum l_sideEnum = null;
            //取得した建玉.isLong() == trueの場合
            if (l_ifoContractImpl.isLong())
            {
                l_sideEnum = SideEnum.BUY;
            }
            else
            {
                l_sideEnum = SideEnum.SELL;
            }

            //先物注文マネージャImplを取得
            WEB3FuturesOrderManagerImpl l_futuresOrderManager =
                (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();

            //先物注文マネージャ.calc概算決済損益()
            l_amountCalcResult =
                l_futuresOrderManager.calcEstimateSettlementIncome(
                l_genCommission,
                l_dblLimitPrice,
                (WEB3GentradeSubAccount)l_subAccount,
                l_tradedProduct,
                l_settleContractEntrys,
                l_dblQuantity,
                l_sideEnum,
                false,
                l_ifoContractImpl);
        }
        else
        {
            //super.get概算決済損益()
            l_amountCalcResult =
                super.getEstimateSettlementIncome(
                    l_genCommission,
                    l_dblLimitPrice,
                    l_subAccount,
                    l_tradedProduct,
                    l_settleContractEntrys,
                    l_dblQuantity,
                    l_requestAdapter);
        }

        log.exiting(STR_METHOD_NAME);
        return l_amountCalcResult;
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
     * @@param l_settleContractEntrys - (返済建玉エントリ)<BR>
     * 返済建玉エントリ<BR>
     * @@param l_requestAdapter - (リクエストアダプタ)<BR>
     * リクエストアダプタ<BR>
     * @@param l_tradedProduct - (先物OP取引銘柄)<BR>
     * 先物OP取引銘柄<BR>
     * @@return WEB3FuturesOptionsContractUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 47C24E7F02BD
     */
    protected WEB3FuturesOptionsContractUnit[] createContractUnit(
        SettleContractEntry[] l_settleContractEntrys,
        WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter,
        WEB3IfoTradedProductImpl l_tradedProduct)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createContractUnit("
            + "SettleContractEntry[], "
            + "WEB3FuturesSettleContractOrderRequestAdapter "
            + "WEB3IfoTradedProductImpl)";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccFuturesSettleContractOrderRequestAdapter l_adapter =
            (WEB3ToSuccFuturesSettleContractOrderRequestAdapter)l_requestAdapter;

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
                super.createContractUnit(l_settleContractEntrys, l_requestAdapter, l_tradedProduct);
        }

        log.exiting(STR_METHOD_NAME);
        return l_contractUnits;
    }

    /**
     * (submit返済注文)<BR>
     * 先物返済注文を登録する。<BR>
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
     * @@param l_requestAdapter - (リクエストアダプタ)<BR>
     * リクエストアダプタ<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_changeSettleContractOrderSpec - (返済注文内容)<BR>
     * 返済注文内容<BR>
     * @@param l_lngOrderID - (注文ID)<BR>
     * 注文ID<BR>
     * @@param l_genCommission - (手数料)<BR>
     * 手数料<BR>
     * @@param l_estimateDeliveryAmountCalcResult - (概算受渡代金計算結果)<BR>
     * 概算受渡代金計算結果<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47C274BE0035
     */
    protected void submitSettleContractOrder(
        WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter,
        SubAccount l_subAccount,
        WEB3IfoSettleContractOrderSpec l_changeSettleContractOrderSpec,
        long l_lngOrderID,
        WEB3GentradeCommission l_genCommission,
        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitSettleContractOrder("
            + "WEB3FuturesSettleContractOrderRequestAdapter, "
            + "SubAccount"
            + "WEB3IfoSettleContractOrderSpec"
            + "long"
            + "WEB3GentradeCommission"
            + "WEB3IfoEstimateDeliveryAmountCalcResult)";
        log.entering(STR_METHOD_NAME);

        // 連続注文マネージャImplを取得
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        WEB3ToSuccFuturesSettleContractOrderRequestAdapter l_adapter =
            (WEB3ToSuccFuturesSettleContractOrderRequestAdapter)l_requestAdapter;
        WEB3SuccFuturesCloseCompleteRequest l_request =
            (WEB3SuccFuturesCloseCompleteRequest)l_adapter.request;

        //リクエストアダプタ.リクエスト.単価調整値情報.get単価調整値()
        Double l_priceAdjustmentValue = null;
        if (l_request.priceAdjustmentValueInfo != null)
        {
            l_priceAdjustmentValue = new Double(l_request.priceAdjustmentValueInfo.getPriceAdjustmentValue());
        }

        //連続注文マネージャImpl.submit先物OP返済新規予約注文
        l_orderManager.submitIfoCloseContractNewOrder(
            (WEB3GentradeSubAccount)l_subAccount,
            l_changeSettleContractOrderSpec,
            l_lngOrderID,
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
     * @@param l_requestAdapter - (先物返済注文リクエストアダプタ)<BR>
     * 先物返済注文リクエストアダプタ。<BR>
     * @@param l_response - (レスポンス)<BR>
     * レスポンス。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47C29A3E01B7
     */
    protected void setPrice(
        WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter,
        WEB3GenResponse l_response) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "setPrice(WEB3FuturesSettleContractOrderRequestAdapter, WEB3GenResponse)";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccFuturesSettleContractOrderRequestAdapter l_adapter =
            (WEB3ToSuccFuturesSettleContractOrderRequestAdapter)l_requestAdapter;
        WEB3SuccFuturesCloseConfirmRequest l_request =
            (WEB3SuccFuturesCloseConfirmRequest)l_adapter.request;

        //リクエスト.単価調整値情報≠null（±指値指定）の場合
        if (l_request.priceAdjustmentValueInfo != null)
        {
            WEB3SuccFuturesCloseConfirmResponse l_confirmResponse =
                (WEB3SuccFuturesCloseConfirmResponse)l_response;
            l_confirmResponse.afterAdjustmentPrice = WEB3StringTypeUtility.formatNumber(l_adapter.getPrice());
        }
        log.exiting(STR_METHOD_NAME);
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
        WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getDayTradeType(SettleContractEntry[], WEB3FuturesSettleContractOrderRequestAdapter)";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccFuturesSettleContractOrderRequestAdapter l_adapter =
            (WEB3ToSuccFuturesSettleContractOrderRequestAdapter)l_requestAdapter;
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
