head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.38.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoNewOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP現注文内容(WEB3IfoNewOrderSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15 nakazato(ACT) 新規作成
*/
package webbroker3.ifodeposit;

import java.util.Date;
import java.util.List;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProductManager;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoChangeOpenContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoOpenContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoTradingModuleImpl;

/**
 * (先物OP現注文内容)<BR>
 * 証拠金計算時使用する現注文内容を表現する。<BR>
 * （新規建余力チェック時に使用）<BR>
 */
public class WEB3IfoNewOrderSpec
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoNewOrderSpec.class);

    public final static long DEFAULT_NEW_ID = -1;

    /**
     * (注文ID)<BR>
     * 
     * 訂正新規建の場合、該当の注文ID。<BR>
     * 新規建の場合、-1。<BR>
     */
    public long orderId;

    /**
     * (注文単位ID)<BR>
     * 
     * 訂正新規建の場合、該当の注文単位ID。<BR>
     * 新規建の場合、-1。<BR>
     */
    public long orderUnitId;

    /**
     * (銘柄ID)
     */
    public long productId;

    /**
     * (市場ID)
     */
    public long marketId;

    /**
     * (建区分)<BR>
     * 
     * 1：買建<BR>
     * 2：売建<BR>
     */
    public ContractTypeEnum contractType;

    /**
     * (発注日)
     */
    public Date orderBizDate;

    /**
     * (受渡日)
     */
    public Date deliveryDate;

    /**
     * (先物オプション商品)<BR>
     * 
     * 1：先物<BR>
     * 2：コールオプション<BR>
     * 2：プットオプション<BR>
     */
    public IfoDerivativeTypeEnum ifoDerivativeType;

    /**
     * (注文数量)
     */
    public double quantity = 0;

    /**
     * (概算受渡代金)
     */
    public double estimatedNetAmount;

    /**
     * (原資産銘柄コード)
     */
    public String underlyingProductCode;

    /**
     * (コンストラクタ)
     */
    public WEB3IfoNewOrderSpec()
    {

    }

    /**
     * (create先物ＯＰ現注文内容)<BR>
     * (staticメソッド)<BR>
     * 注文内容インタセプタと注文内容より、先物OP現注文内容オブジェクトを生成する。<BR>
     * <BR>
     * シーケンス図「(先物OP現注文内容)create先物OP現注文内容」参照。 <BR>
     * <BR>
     * 
     * @@param l_subAccount - (補助口座)
     * @@param l_interceptor - (注文内容インタセプタ)
     * @@param l_orderspec - (注文内容)
     * @@param l_orderTypeEnum - (注文種別)
     * 
     * @@return webbroker3.ifodeposit.WEB3IfoNewOrderSpec
     */
    public static WEB3IfoNewOrderSpec createWEB3IfoNewOrderSpec(
        WEB3GentradeSubAccount l_subAccount,
        Object l_interceptor,
        Object l_orderspec,
        OrderTypeEnum l_orderTypeEnum)
    {
        final String STR_METHOD_NAME = "WEB3IfoNewOrderSpec.createWEB3IfoNewOrderSpec(WEB3GentradeSubAccount, Object, Object, OrderTypeEnum)";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoNewOrderSpec l_newOrderSpec = null;
            l_newOrderSpec = new WEB3IfoNewOrderSpec();
            IfoOrderUnitParams l_orderUnitParams = new IfoOrderUnitParams();

            //先物オプション新規建注文の場合
            if (l_orderspec instanceof IfoOpenContractOrderSpec)
            {
                IfoOpenContractOrderSpec l_ifoOrderspec = (IfoOpenContractOrderSpec)l_orderspec;

                //顧客属性
                l_orderUnitParams.setAccountId(l_subAccount.getAccountId());
                l_orderUnitParams.setSubAccountId(l_subAccount.getSubAccountId());
                l_orderUnitParams.setBranchId(
                    l_subAccount.getMainAccount().getBranch().getBranchId());

                //注文属性作成
                l_orderUnitParams.setOrderId(DEFAULT_NEW_ID);
                l_orderUnitParams.setOrderUnitId(DEFAULT_NEW_ID);
                OrderCategEnum orderCateg =
                    IfoDerivativeTypeEnum.FUTURES.equals(l_ifoOrderspec.getDerivativeType())
                        ? OrderCategEnum.IDX_FUTURES_OPEN
                        : OrderCategEnum.IDX_OPTIONS_OPEN;
                l_orderUnitParams.setOrderCateg(orderCateg);
                l_orderUnitParams.setOrderType(l_orderTypeEnum);
                l_orderUnitParams.setQuantity(l_ifoOrderspec.getQuantity());
                l_orderUnitParams.setTaxType(l_ifoOrderspec.getTaxType());

                //銘柄属性作成
                TradedProduct l_tp =
                    getIFOTradedProduct(
                        l_subAccount,
                        l_ifoOrderspec.getUnderlyingProductCode(),
                        l_ifoOrderspec.getDerivativeType(),
                        l_ifoOrderspec.getMonthOfDelivery(),
                        l_ifoOrderspec.getStrikePrice(),
                        l_ifoOrderspec.getMarketCode());

                Product l_p = l_tp.getProduct();
                l_orderUnitParams.setProductId(l_p.getProductId());
                l_orderUnitParams.setProductType(l_p.getProductType());
                l_orderUnitParams.setDeliveryDate(l_tp.getDailyDeliveryDate());
                l_orderUnitParams.setMarketId(l_tp.getMarket().getMarketId());
                Date bizDate = GtlUtils.getFinObjectManager().getTradingCalendar(l_tp.getTradedProductId()).getCurrentBizDate();
                l_orderUnitParams.setBizDate(GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(bizDate));

                //概算代金などカスタマイズ属性作成
                IfoOrderManagerPersistenceEventInterceptor l_persistenceInterceptor =
                    (IfoOrderManagerPersistenceEventInterceptor)l_interceptor;
                l_orderUnitParams =
                    l_persistenceInterceptor.mutate(
                        OrderManagerPersistenceType.INSERT,
                        OrderManagerPersistenceContext.NEW_OPEN_CONTRACT_ORDER,
                        l_orderUnitParams);

            }
            //先物オプション新規建注文訂正の場合
            else if (l_orderspec instanceof IfoChangeOpenContractOrderSpec)
            {
                IfoChangeOpenContractOrderSpec l_ifoChangeOrderspec =
                    (IfoChangeOpenContractOrderSpec)l_orderspec;

                IfoOrderRow orderRow = IfoOrderDao.findRowByPk(l_ifoChangeOrderspec.getOrderId());
                List orderUnits = IfoOrderUnitDao.findRowsByOrderId(orderRow);
                l_orderUnitParams = new IfoOrderUnitParams((IfoOrderUnitRow)orderUnits.get(0));

                //注文属性作成
                l_orderUnitParams.setQuantity(
                    l_ifoChangeOrderspec.getAfterChangeOriginalQuantity());
                l_orderUnitParams.setPrice(l_ifoChangeOrderspec.getAfterChangePrice());

                //概算代金などカスタマイズ属性作成
                IfoOrderManagerPersistenceEventInterceptor l_persistenceInterceptor =
                    (IfoOrderManagerPersistenceEventInterceptor)l_interceptor;
                l_orderUnitParams =
                    l_persistenceInterceptor.mutate(
                        OrderManagerPersistenceType.UPDATE,
                        OrderManagerPersistenceContext.MODIFY_ORDER_ACCEPTED,
                        l_orderUnitParams);
            }
            //その他の場合、未対応ため異常発生
            else
            {
                log.error("illegal Argument");
                throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
            }

            /*
             * 先物OP現注文内容に値をセット
             */
            //注文ID
            l_newOrderSpec.orderId = l_orderUnitParams.getOrderId();
            //注文単位ID
            l_newOrderSpec.orderUnitId = l_orderUnitParams.getOrderUnitId();
            //銘柄ID
            l_newOrderSpec.productId = l_orderUnitParams.getProductId();
            //市場ID
            l_newOrderSpec.marketId = l_orderUnitParams.getMarketId();
            //発注日
            l_newOrderSpec.orderBizDate =
                WEB3DateUtility.getDate(l_orderUnitParams.getBizDate(), "yyyyMMdd");
            //受渡日
            l_newOrderSpec.deliveryDate = l_orderUnitParams.getDeliveryDate();
            //注文数量
            l_newOrderSpec.quantity = l_orderUnitParams.getQuantity();
            //概算受渡代金
            l_newOrderSpec.estimatedNetAmount = l_orderUnitParams.getEstimatedPrice();

            /*
             * 建区分
             */
            //"先物新規買建注文"または、"オプション新規買建注文"の場合
            if (l_orderTypeEnum.intValue() == OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.intValue()
                || l_orderTypeEnum.intValue() == OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.intValue())
            {
                //買建をセット
                l_newOrderSpec.contractType = ContractTypeEnum.LONG;
            }
            //以外の場合
            else
            {
                //売建をセット
                l_newOrderSpec.contractType = ContractTypeEnum.SHORT;
            }

            /*
             * 先物オプション商品区分
             */
            IfoProductManager l_pm =
                (IfoProductManager) (GtlUtils
                    .getTradingModule(IfoTradingModuleImpl.TRADING_MODULE_NAME)
                    .getProductManager());
            IfoProduct l_product = (IfoProduct)l_pm.getProduct(l_orderUnitParams.getProductId());
            l_newOrderSpec.ifoDerivativeType = l_product.getDerivativeType();

            /*
             * 原資産銘柄コード
             */
            l_newOrderSpec.underlyingProductCode = l_product.getUnderlyingProductCode();

            log.exiting(STR_METHOD_NAME);
            return l_newOrderSpec;

        }
        catch(NotFoundException nfe)
        {
            nfe.printStackTrace();
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005, STR_METHOD_NAME, nfe
                        .getMessage(), nfe);
        }
        catch(DataException de)
        {
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, STR_METHOD_NAME, de
                        .getMessage(), de);
        }
    }

    /**
     * (get先物オプション取引銘柄())<BR>
     * <BR>
     * 先物オプション取引銘柄を取得する。<BR>
     * @@param l_subAccount   補助口座
     * @@param l_sUnderlyingProductCode   銘柄コード
     * @@param　@l_derivativeType　@受渡タイプ
     * @@param l_sMonthOfDelivery   限月
     * @@param l_dnlStrikePrice   行使価格
     * @@param l_sMarketCode  Marketコード
     * @@return TradedProduct  - 取引銘柄
     */
    private static TradedProduct getIFOTradedProduct(
        SubAccount l_subAccount,
        String l_sUnderlyingProductCode,
        IfoDerivativeTypeEnum l_derivativeType,
        String l_sMonthOfDelivery,
        double l_dnlStrikePrice,
        String l_sMarketCode)
        throws NotFoundException
    {
        String l_tmName = IfoTradingModuleImpl.TRADING_MODULE_NAME;
        IfoProductManager l_pm =
            (IfoProductManager) (GtlUtils.getTradingModule(l_tmName).getProductManager());
        TradedProduct tradedProduct =
            l_pm.getIfoTradedProduct(
                l_subAccount.getInstitution(),
                l_sUnderlyingProductCode,
                l_derivativeType,
                l_sMonthOfDelivery,
                l_dnlStrikePrice,
                l_sMarketCode);
        return tradedProduct;
    }
}@
