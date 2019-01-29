head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研ビジネス・イノベーション
File Name        : 外国株式注文マネージャ(WEB3FeqOrderManager.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revesion History : 2005/07/14 王亞洲 (中訊) 新規作成
                   2005/07/27 艾興　@(中訊) レビュー
                   2006/10/17 何文敏 (中訊) レビュー
                   2006/11/20 徐大方 (中訊) 仕様変更 モデル301
Revesion History : 2007/11/06 何文敏 (中訊) 仕様変更 モデル359
Revesion History : 2007/11/20 何文敏 (中訊) 仕様変更 モデル365
Revesion History : 2008/01/23 柴双紅(中訊) モデルNo.372
Revesion History : 2008/10/02 大澤(SRA) 【外国株式】仕様変更管理台帳（モデル）No.478
Revesion History : 2009/07/31 孟玲玲(中訊) モデルNo.517、No.518、No.521
Revesion History : 2010/09/08 趙天月(中訊) モデルNo.544
*/
package webbroker3.feq;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderSentMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultChangeOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultChangeOrderSentMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderSentMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.MarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.ChangeOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrder;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqProduct;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqProductManager;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderPK;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitPK;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.stdimpls.FeqOrderManagerImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3InputOutputActionSettlementDivDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3PremiumRestraintRateDef;
import webbroker3.common.define.WEB3SonarExecutionConditionDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.InstBranchProductDao;
import webbroker3.gentrade.data.InstBranchProductRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
/**
 * (外国株式注文マネージャ)<BR>
 * 外国株式注文マネージャクラス<BR>
 *
 * @@author 王亞洲
 * @@version 1.0
 */
public class WEB3FeqOrderManager extends FeqOrderManagerImpl
{
    /**
     * ログユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderManager.class);

    /**
     * @@roseuid 42CE39EA032C
     */
    public WEB3FeqOrderManager()
    {

    }

    /**
     * (validate注文)<BR>
     * 注文共通チェックを実施する。<BR>
     * <BR>
     * 以下のチェックを行う。<BR>
     * 　@－受付時間チェック<BR>
     * 　@－システム停止中チェック<BR>
     * 　@－顧客のチェック（Ｙ客、管理ロック等）<BR>
     * 　@－外国株式口座開設チェック<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株注文）validate注文」参照。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@throws WEB3BaseException
     * @@roseuid 428B13C9022B
     */
    public void validateOrder(WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrder(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        // 1.1. getInstance()
        WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3FeqTypeOrderManagerReusableValidations)
            WEB3FeqTypeOrderManagerReusableValidations.getInstance();

        // 1.2. validate注文受付可能( )
        WEB3FeqTradingTimeManagement.validateOrderAccept();

        // 1.3. validate取引可能顧客(補助口座 : SubAccount)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeOrderValidator l_orderValidator =
            (WEB3GentradeOrderValidator) l_finApp.getCommonOrderValidator();
        OrderValidationResult l_result = l_orderValidator.validateSubAccountForTrading(l_subAccount);
        if (!OrderValidationResult.VALIDATION_OK_RESULT.equals(l_result))
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME
                + l_result.getProcessingResult().getErrorInfo().getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_result.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 1.4. validate外国株式口座開設(補助口座)
        l_orderMgrResVal.validateFeqAccountEstablish(l_subAccount);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate外株銘柄)<BR>
     * 銘柄チェックを行う。 <BR>
     * （* 外国株式発注審査チェック.validate外株銘柄()に委譲する。） <BR>
     * @@param l_institution - (証券会社)<BR>
     * 証券会社オブジェクト<BR>
     * @@param l_strProductCode - (銘柄コード)<BR>
     * 銘柄コード<BR>
     * @@return FeqProduct
     * @@throws WEB3BaseException
     * @@roseuid 428B19840131
     */
    public FeqProduct validateFeqProduct(
        WEB3GentradeInstitution l_institution,
        String l_strProductCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateFeqProduct(WEB3GentradeInstitution, String)";
        log.entering(STR_METHOD_NAME);

        WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3FeqTypeOrderManagerReusableValidations)
            WEB3FeqTypeOrderManagerReusableValidations.getInstance();
        FeqProduct l_feqProduct;

        try
        {
            l_feqProduct = l_orderMgrResVal.validateFeqProduct(l_institution, l_strProductCode);
        }
        catch (OrderValidationException l_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            throw new WEB3BusinessLayerException(
                l_ex.getValidationResult().getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_feqProduct;
    }

    /**
     * (validate取引銘柄)<BR>
     * 取引銘柄のチェックを行う。<BR>
     * <BR>
     *   ・存在チェック<BR>
     *   ・上場チェック<BR>
     * 　@・取引規制チェック<BR>
     * <BR>
     * （* 外国株式発注審査個別チェック.validate取引銘柄()に委譲する。）<BR>
     * @@param l_feqProduct - (外国株式銘柄)<BR>
     * 外国株式銘柄オブジェクト<BR>
     * @@param l_market - (市場)<BR>
     * 市場オブジェクト<BR>
     * @@param l_blnIsBuyOrder - (is買注文)<BR>
     * （isBuyOrder）<BR>
     * 買注文かの判定<BR>
     * <BR>
     * true：買<BR>
     * false：売<BR>
     *
     * @@return TradedProduct
     * @@throws WEB3BaseException
     * @@roseuid 428B1B970160
     */
    public TradedProduct validateTradedProduct(
        WEB3FeqProduct l_feqProduct,
        WEB3GentradeMarket l_market,
        boolean l_blnIsBuyOrder) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateTradedProduct(WEB3FeqProduct, WEB3GentradeMarket, boolean)";
        log.entering(STR_METHOD_NAME);

        WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3FeqTypeOrderManagerReusableValidations)
            WEB3FeqTypeOrderManagerReusableValidations.getInstance();
        TradedProduct l_tradedProduct = l_orderMgrResVal.validateTradedProduct(
            l_feqProduct,
            l_market,
            l_blnIsBuyOrder);

        log.exiting(STR_METHOD_NAME);
        return l_tradedProduct;
    }

    /**
     * (validate取引銘柄)<BR>
     * 取引銘柄のチェックを行う。<BR>
     * <BR>
     *   ・存在チェック<BR>
     *   ・上場チェック<BR>
     * <BR>
     * （* 外国株式発注審査個別チェック.validate取引銘柄(外国株式銘柄, 市場)<BR>
     * に委譲する。）<BR>
     * @@param l_feqProduct - (外国株式銘柄)<BR>
     * 外国株式銘柄オブジェクト<BR>
     * @@param l_market - (市場)<BR>
     * 市場オブジェクト<BR>
     * @@return TradedProduct
     * @@throws WEB3BaseException
     * @@roseuid 42C20D0201F1
     */
    public TradedProduct validateTradedProduct(
        WEB3FeqProduct l_feqProduct,
        WEB3GentradeMarket l_market) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateTradedProduct(WEB3FeqProduct, WEB3GentradeMarket)";
        log.entering(STR_METHOD_NAME);

        WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3FeqTypeOrderManagerReusableValidations)
            WEB3FeqTypeOrderManagerReusableValidations.getInstance();
        TradedProduct l_tradedProduct = l_orderMgrResVal.validateTradedProduct(
            l_feqProduct,
            l_market);

        log.exiting(STR_METHOD_NAME);
        return l_tradedProduct;
    }

    /**
     * (validate市場)<BR>
     * 市場のチェックを行う。<BR>
     * （* 外国株式発注審査個別チェック.validate市場()に委譲する。）<BR>
     * @@param l_market - (市場)<BR>
     * 市場オブジェクト<BR>
     * @@throws WEB3BaseException
     * @@roseuid 429608F5035A
     */
    public void validateMarket(WEB3GentradeMarket l_market) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateMarket(WEB3GentradeMarket)";
        log.entering(STR_METHOD_NAME);

        WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3FeqTypeOrderManagerReusableValidations)
            WEB3FeqTypeOrderManagerReusableValidations.getInstance();
        l_orderMgrResVal.validateMarket(l_market);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate新規注文)<BR>
     * （validateNewOrderのオーバーライド）<BR>
     * 新規注文の発注審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株注文）validate新規注文」 参照<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     *
     * @@param l_productType - (銘柄タイプ)<BR>
     * 銘柄タイプ<BR>
     *
     * @@param l_orderSpec - (注文内容)<BR>
     * 注文内容オブジェクト<BR>
     *
     * @@return NewOrderValidationResult
     * @@roseuid 428B390600D9
     */
    public NewOrderValidationResult validateNewOrder(
        SubAccount l_subAccount,
        ProductTypeEnum l_productType,
        NewOrderSpec l_orderSpec)
    {
        final String STR_METHOD_NAME = "validateNewOrder(SubAccount, ProductTypeEnum, NewOrderSpec)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_orderSpec == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値がNULL");
        }

        WEB3GentradeSubAccount l_genSubAccount = (WEB3GentradeSubAccount)l_subAccount;
        WEB3FeqNewOrderSpec l_feqNewOrderSpec = (WEB3FeqNewOrderSpec)l_orderSpec;

        try
        {
            // 1.1. getInstance( )
            WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
                (WEB3FeqTypeOrderManagerReusableValidations)
                WEB3FeqTypeOrderManagerReusableValidations.getInstance();

            // 1.2. validate注文(補助口座)
            // [引数] 
            //  補助口座： 引数.補助口座 
            this.validateOrder(l_genSubAccount);

            // 1.3. getProductCode( )
            String l_strProductCode = l_feqNewOrderSpec.getProductCode();

            WEB3GentradeInstitution l_genInstitution = (WEB3GentradeInstitution)l_subAccount.getInstitution();
            // 1.4. validate外株銘柄(証券会社, String)
            // [引数] 
            //  証券会社： 補助口座.getInstitutuin()の戻り値 
            //  銘柄コード： getProductCode()の戻り値 
            WEB3FeqProduct l_feqProduct =
                (WEB3FeqProduct)this.validateFeqProduct(
                    l_genInstitution,
                    l_strProductCode);

            // 1.5. get市場( )
            WEB3GentradeMarket l_market = l_feqProduct.getMarket();

            // 1.6. validate市場(市場)
            // [引数] 
            //  市場： get市場()の戻り値 
            this.validateMarket(l_market);

            // 1.7. validate取扱可能市場(String, String, String)
            // [引数] 
            //  証券会社コード： 証券会社.getInstitutionCode()の戻り値 
            //  部店コード： 補助口座.get取引店().getBranchCode()の戻り値 
            //  市場コード： 市場.getMarketCode()の戻り値 
            this.validateHandlingPossibleMarket(
                l_genInstitution.getInstitutionCode(),
                l_genSubAccount.getWeb3GenBranch().getBranchCode(),
                l_market.getMarketCode());

            // 1.8. isBuyOrder( )
            boolean l_blnIsBuyOrder = l_feqNewOrderSpec.isBuyOrder();
            log.debug("is買付注文 = " + l_blnIsBuyOrder);

            // 1.9. validate取引銘柄(外国株式銘柄, 市場, boolean)
            // [引数] 
            //  外国株式銘柄： validate外株銘柄()の戻り値 
            //  市場： get市場()の戻り値 
            //  is買注文： isBuyOrder()の戻り値 
            FeqTradedProduct l_tradedProduct = (FeqTradedProduct)this.validateTradedProduct(
                l_feqProduct,
                l_market,
                l_blnIsBuyOrder);

            // 1.10. validate顧客銘柄別取引停止(SubAccount, long, OrderTypeEnum)
            // [引数] 
            //  補助口座： 引数.補助口座 
            //  銘柄ID： 外国株式銘柄.銘柄ID 
            //  注文種別： （以下のとおり） 
            //     isBuyOrder()の戻り値 == true の場合、”外株買い” 
            //     isBuyOrder()の戻り値 == false の場合、”外株売り”
            OrderTypeEnum l_orderTypeEnum = (l_blnIsBuyOrder) ? OrderTypeEnum.FEQ_BUY : OrderTypeEnum.FEQ_SELL;
            l_orderMgrResVal.validateAccountProductTradedStop(
                l_subAccount,
                l_feqProduct.getProductId(),
                l_orderTypeEnum);

            // 1.11. getQuantity( )
            double l_dblQuantity = l_feqNewOrderSpec.getQuantity();
            log.debug("注文数量 = " + l_dblQuantity);

            // 1.12. validate注文株数(FeqTradedProduct, double, boolean)
            // [引数] 
            //  外国株式取引銘柄： 外国株式取引銘柄オブジェクト 
            //  注文株数： getQuantity()の戻り値 
            //  is買注文： isBuyOrder()の戻り値 
            l_orderMgrResVal.validateQuantity(
                l_tradedProduct,
                l_dblQuantity,
                l_blnIsBuyOrder);

            // 1.13. getTaxType( )
            TaxTypeEnum l_taxTypeEnum = l_feqNewOrderSpec.getTaxType();

            // 1.14. (*1) isBuyOrder()の戻り値 == false の場合
            if(!l_blnIsBuyOrder)
            {
                // 1.14.1. validate売付可能数量(補助口座, long, double, TaxTypeEnum)
                // [引数] 
                //  補助口座： 引数.補助口座 
                //  取引銘柄： 外国株式銘柄.銘柄ID 
                //  数量： getQuantity()の戻り値 
                //  税区分： getTaxType()の戻り値 
                l_orderMgrResVal.validateSellPossQuantity(
                    l_genSubAccount,
                    l_feqProduct.getProductId(),
                    l_dblQuantity,
                    l_taxTypeEnum);
            }

            // 1.15. getLimitPrice( )
            double l_dblLimitPrice = l_feqNewOrderSpec.getLimitPrice();
            log.debug("指値 = " + l_dblLimitPrice);

            // 1.16. isMarketOrder( )
            boolean l_blnIsMarketOrder = l_feqNewOrderSpec.isMarketOrder();
            log.debug("isMarketOrder = " + l_blnIsMarketOrder);

            // 1.17. validate注文単価(TradedProduct, double, boolean)
            // [引数] 
            //  取引銘柄： 外国株式取引銘柄オブジェクト 
            //  注文単価： getLimitPrice()の戻り値 
            //  is成行注文： isMarketOrder()の戻り値 
            l_orderMgrResVal.validatePrice(
                l_tradedProduct,
                l_dblLimitPrice,
                l_blnIsMarketOrder);

            // 1.18. get（W指値）訂正指値( )
            double l_dblWLimitPrice = l_feqNewOrderSpec.getWLimitPrice();
            log.debug("（W指値）訂正指値 = " + l_dblWLimitPrice);

            // 1.19. (*2) get（W指値）訂正指値 != 0 の場合
            if (l_dblWLimitPrice != 0.0D)
            {
                // 1.19.1. validate注文単価(TradedProduct, double, boolean)
                // [引数] 
                //  取引銘柄： 外国株式取引銘柄オブジェクト 
                //  注文単価： get（W指値）訂正指値()の戻り値 
                //  is成行注文： false
                l_orderMgrResVal.validatePrice(
                    l_tradedProduct,
                    l_dblWLimitPrice,
                    false);
            }

            // 1.20. get決済区分( )
            String l_strSettleDiv = l_feqNewOrderSpec.getSettleDiv();
            log.debug("決済区分 = " + l_strSettleDiv);

            // 1.21. (*3) get決済区分()の戻り値 == ”外貨” の場合、実施
            if (WEB3InputOutputActionSettlementDivDef.FOREIGN_SETTLE.equals(l_strSettleDiv))
            {
                // 1.21.1. getCurrencyCode( )
                String l_strCurrencyCode = l_feqNewOrderSpec.getCurrencyCode();
                log.debug("通貨コード = " + l_strCurrencyCode);

                // 1.21.2. validate外貨決済(補助口座, String)
                // [引数] 
                //  補助口座： 補助口座オブジェクト 
                //  通貨コード： getCurrencyCode()の戻り値 
                l_orderMgrResVal.validateFcSettle(
                    l_genSubAccount,
                    l_strCurrencyCode);
            }

            // 1.22. (*4) getTaxType()の戻り値 == ”特定” の場合、実施
            if (TaxTypeEnum.SPECIAL.equals(l_taxTypeEnum))
            {
                // 1.22.1. get発注日( )
                Date l_datBizDate = l_feqNewOrderSpec.getBizDate();
                log.debug("発注日 = " + l_datBizDate);

                // 1.22.2. validate特定口座開設(補助口座, Date)
                // [引数] 
                //  補助口座： 補助口座オブジェクト 
                //  発注日： get発注日()の戻り値 
                l_orderMgrResVal.validateSpecialAccountEstablish(
                    l_genSubAccount,
                    l_datBizDate);
            }

            // 1.23. validate為替登録(外国株式銘柄, boolean, boolean)
            // [引数] 
            //  外国株式銘柄： 外国株式銘柄オブジェクト 
            //  is買付： isBuyOrder()の戻り値 
            //  is約定： false 
            l_orderMgrResVal.validateRateRegistration(
                l_feqProduct,
                l_blnIsBuyOrder,
                false);

            // 1.24. validate注文条件(String, String, Date, Date, String, FeqExecutionConditionType, boolean)
            // [引数] 
            //  証券会社コード： 証券会社.getInstitutionCode()の戻り値 
            //  市場コード： 市場.getMarketCode()の戻り値 
            //  原注文発注日： （以下のとおり） 
            //    ・新規注文内容.初回注文の注文単位ID > 0 and 
            //      初回注文の注文単位ID != null(＝繰越) の場合 、初回注文の注文単位IDに対応する注文単位.発注日を設定  
            //    ・それ以外の場合、nullを設定  
            //  注文失効日：　@外国株式新規注文内容.getOrderExpDate()の戻り値 
            //  発注条件： 外国株式新規注文内容.get発注条件()の戻り値 
            //  執行条件：　@外国株式新規注文内容.getExecutionConditionType()の戻り値 
            //  is出来るまで注文：　@外国株式新規注文内容.is出来るまで注文()の戻り値 

            // 原注文発注日
            Date l_datOrgBizDate = null;
            Long l_lngFirstUnitId = l_feqNewOrderSpec.getFirstOrderUnitId();
            if (l_lngFirstUnitId != null && l_lngFirstUnitId.longValue() > 0)
            {
                FeqOrderUnitRow l_orderUnitRowOrg = null;
                try
                {
                    FeqOrderUnit l_orderUnitOrg = (FeqOrderUnit)this.getOrderUnit(l_lngFirstUnitId.longValue());
                    if (l_orderUnitOrg == null)
                    {
                        log.debug(STR_METHOD_NAME + "予期しないシステムエラーが発生しました。");
                        log.exiting(STR_METHOD_NAME);
                        return new NewOrderValidationResult(
                            ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80002));
                    }
                    l_orderUnitRowOrg = (FeqOrderUnitRow)l_orderUnitOrg.getDataSourceObject();
                    l_datOrgBizDate = WEB3DateUtility.getDate(
                        l_orderUnitRowOrg.getBizDate(),
                        "yyyyMMdd");
                }
                catch (NotFoundException l_ex)
                {
                    log.debug(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
                    log.exiting(STR_METHOD_NAME);
                    return new NewOrderValidationResult(
                        ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80002));
                }
            }
            log.debug("原注文発注日 = " + l_datOrgBizDate);

            // 注文失効日：　@外国株式新規注文内容.getOrderExpDate()の戻り値
            Date l_datOrdExpDate = l_feqNewOrderSpec.getOrderExpDate();
            log.debug("注文失効日 = " + l_datOrdExpDate);

            // 発注条件： 外国株式新規注文内容.get発注条件()の戻り値
            String l_strOrdCondType = l_feqNewOrderSpec.getOrderConditionType();
            log.debug("発注条件 = " + l_strOrdCondType);

            // 執行条件：　@外国株式新規注文内容.getExecutionConditionType()の戻り値
            FeqExecutionConditionType l_feqExeCondType = l_feqNewOrderSpec.getExecutionConditionType();
            log.debug("執行条件 = " + l_feqExeCondType);

            // is出来るまで注文：　@外国株式新規注文内容.is出来るまで注文()の戻り値
            boolean l_blnIsOrdUntDeaLine = l_feqNewOrderSpec.isOrderUntilDeadLine();
            log.debug("is出来るまで注文 = " + l_blnIsOrdUntDeaLine);

            l_orderMgrResVal.validateOrderCondition(
                l_genInstitution.getInstitutionCode(),
                l_market.getMarketCode(),
                l_datOrgBizDate,
                l_datOrdExpDate,
                l_strOrdCondType,
                l_feqExeCondType,
                l_blnIsOrdUntDeaLine);
        }
        catch (OrderValidationException l_ex)
        {
            log.debug(STR_METHOD_NAME + "Error in validate新規注文", l_ex);
            log.exiting(STR_METHOD_NAME);
            return new NewOrderValidationResult(
                    l_ex.getValidationResult().getProcessingResult());
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + "Error in validate新規注文", l_ex);
            log.exiting(STR_METHOD_NAME);
            return new NewOrderValidationResult(
                ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo()));
        }

        // 1.25. NewOrderValidationResult(arg0 : ProcessingResult)
        log.exiting(STR_METHOD_NAME);
        return new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
    }

    /**
     * (validate訂正注文)<BR>
     * （validateChangeOrderのオーバーライド）<BR>
     * 訂正注文の発注審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株注文）validate訂正注文」 参照<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     *
     * @@param l_orderSpec - (注文内容)<BR>
     * 注文内容オブジェクト<BR>
     *
     * @@return OrderValidationResult
     * @@roseuid 429740FA036B
     */
    public OrderValidationResult validateChangeOrder(SubAccount l_subAccount, ChangeOrderSpec l_orderSpec)
    {
        final String STR_METHOD_NAME = "validateChangeOrder(SubAccount, ChangeOrderSpec)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_orderSpec == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値がNULL");
        }

        WEB3GentradeSubAccount l_genSubAccount = (WEB3GentradeSubAccount)l_subAccount;
        WEB3FeqChangeOrderSpec l_feqChangeOrderSpec = (WEB3FeqChangeOrderSpec)l_orderSpec;

        try
        {
            // 1.1. getInstance( )
            WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
                (WEB3FeqTypeOrderManagerReusableValidations)
                WEB3FeqTypeOrderManagerReusableValidations.getInstance();

            // 1.2. validate注文(補助口座)
            // [引数] 
            //  補助口座： 引数.補助口座
            this.validateOrder(l_genSubAccount);

            // 1.3. getOrderId( )
            long l_lngOrderId = l_feqChangeOrderSpec.getOrderId();
            //1.4.validate訂正可能市場
            this.validateChangePossMarket(l_lngOrderId);
            // 1.5. validate注文訂正可能状態(long)
            // [引数] 
            //  注文ID： getOrderId()の戻り値 
            this.validateOrderChangePossibleStatus(l_lngOrderId);

            // 1.6. getOrderUnitId( )
            long l_lngOrderUnitId = l_feqChangeOrderSpec.getOrderUnitId();
            log.debug("注文単位ID = " + l_lngOrderUnitId);

            // 1.7. getOrderUnit(注文単位ID : long)
            // [引数] 
            //  注文単位ID： getOrderUnitId()の戻り値 
            WEB3FeqOrderUnit l_orderUnit = (WEB3FeqOrderUnit)this.getOrderUnit(l_lngOrderUnitId);
            if (l_orderUnit == null)
            {
                log.debug(STR_METHOD_NAME + "該当注文単位データなし。");
                log.exiting(STR_METHOD_NAME);
                return new OrderValidationResult(
                    ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));
            }

            // 1.8. get外国株式銘柄(long)
            // [引数] 
            //  銘柄ID： 注文単位.銘柄ID 
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            FeqProductManager l_productManager =
                (FeqProductManager)l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY).getProductManager();

            FeqOrderUnitRow l_orderUnitRow = (FeqOrderUnitRow)l_orderUnit.getDataSourceObject();
            WEB3FeqProduct l_feqProduct = (WEB3FeqProduct)l_productManager.getProduct(
                l_orderUnitRow.getProductId());
            if (l_feqProduct == null)
            {
                log.debug(STR_METHOD_NAME + "該当外国株式銘柄データなし。");
                log.exiting(STR_METHOD_NAME);
                return new OrderValidationResult(
                    ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));
            }

            // 1.9. getProductCode( )
            String l_strProductCode = l_feqProduct.getProductCode();
            log.debug("外国株式銘柄コード = " + l_strProductCode);

            // 1.10. validate外株銘柄(証券会社, String)
            // [引数] 
            //  証券会社： 補助口座.getInstitutuin()の戻り値 
            //  銘柄コード： getProductCode()の戻り値 
            WEB3GentradeInstitution l_genInstitution = (WEB3GentradeInstitution)l_subAccount.getInstitution();
            l_feqProduct = (WEB3FeqProduct)this.validateFeqProduct(
                l_genInstitution,
                l_strProductCode);

            // 1.11. get市場( )
            WEB3GentradeMarket l_market = l_feqProduct.getMarket();

            // 1.12. validate市場(市場)
            // [引数] 
            //  市場： get市場()の戻り値 
            this.validateMarket(l_market);

            // 1.13. validate取扱可能市場(String, String, String)
            // [引数] 
            //  証券会社コード： 証券会社.getInstitutionCode()の戻り値 
            //  部店コード： 補助口座.get取引店().getBranchCode()の戻り値 
            //  市場コード： 市場.getMarketCode()の戻り値
            this.validateHandlingPossibleMarket(
                l_genInstitution.getInstitutionCode(),
                l_genSubAccount.getWeb3GenBranch().getBranchCode(),
                l_market.getMarketCode());

            // 1.14. is買付( )
            boolean l_blnIsBuy = l_orderUnit.isBuy();
            log.debug("is買付 = " + l_blnIsBuy);

            // 1.15. validate取引銘柄(外国株式銘柄, 市場, boolean)
            // [引数] 
            //  外国株式銘柄： validate外株銘柄()の戻り値 
            //  市場： get市場()の戻り値 
            //  is買注文： is買付()の戻り値 
            WEB3FeqTradedProduct l_feqTradedProduct = (WEB3FeqTradedProduct)this.validateTradedProduct(
                l_feqProduct,
                l_market,
                l_blnIsBuy);

            // 1.16. validate顧客銘柄別取引停止(SubAccount, long, OrderTypeEnum)
            // [引数] 
            //  補助口座： 引数.補助口座 
            //  銘柄ID： 外国株式銘柄.銘柄ID 
            //  注文種別： 注文単位.注文種別 
            l_orderMgrResVal.validateAccountProductTradedStop(
                l_subAccount,
                l_feqProduct.getProductId(),
                l_orderUnit.getOrderType());

            // 1.17. getAfterChangeOriginalQuantity( )
            double l_dblAftChaOriQuantity = l_feqChangeOrderSpec.getAfterChangeOriginalQuantity();
            log.debug("getAfterChangeOriginalQuantity() = " + l_dblAftChaOriQuantity);

            // 1.18. validate注文株数(FeqTradedProduct, double, boolean)
            // [引数] 
            //  外国株式取引銘柄： 外国株式取引銘柄オブジェクト 
            //  注文株数： getAfterChangeOriginalQuantity()の戻り値 
            //  is買注文： is買付()の戻り値
            l_orderMgrResVal.validateQuantity(
                l_feqTradedProduct,
                l_dblAftChaOriQuantity,
                l_blnIsBuy);

            // 1.19. getAfterChangePrice( )
            double l_dblAftChaPrice = l_feqChangeOrderSpec.getAfterChangePrice();
            log.debug("getAfterChangePrice() = " + l_dblAftChaPrice);

            // 1.20. isAfterChangePriceMarket( )
            boolean l_blnIsAftChaPriMarket = l_feqChangeOrderSpec.isAfterChangePriceMarket();
            log.debug("isAfterChangePriceMarket() = " + l_blnIsAftChaPriMarket);

            // 1.21. validate注文単価(TradedProduct, double, boolean)
            // [引数] 
            //  取引銘柄： 外国株式取引銘柄オブジェクト 
            //  注文単価： getAfterChangePrice()の戻り値 
            //  is成行注文： isAfterChangePriceMarket()の戻り値 
            l_orderMgrResVal.validatePrice(
                l_feqTradedProduct,
                l_dblAftChaPrice,
                l_blnIsAftChaPriMarket);

            // 1.22. get訂正（W指値）訂正指値( )
            double l_dblChaWLimitPrice = l_feqChangeOrderSpec.getChangeWLimitPrice();
            log.debug("訂正（W指値）訂正指値 = " + l_dblChaWLimitPrice);

            // 1.23. (*2) get訂正（W指値）訂正指値 != 0 の場合、実施
            if (l_dblChaWLimitPrice != 0)
            {
                // 1.23.1. validate注文単価(TradedProduct, double, boolean)
                // [引数] 
                //  取引銘柄： 外国株式取引銘柄オブジェクト 
                //  注文単価： get（W指値）訂正指値()の戻り値 
                //  is成行注文： false 
                l_orderMgrResVal.validatePrice(
                    l_feqTradedProduct,
                    l_dblChaWLimitPrice,
                    false);
            }

            // 1.24. getConfirmedQuantity( )
            double l_dblConfirmedQuantity = l_orderUnit.getConfirmedQuantity();
            log.debug("getConfirmedQuantity() = " + l_dblConfirmedQuantity);

            // 1.25. (*3) getConfirmedQuantity()の戻り値 != NaN の場合、実施
            if (!Double.isNaN(l_dblConfirmedQuantity))
            {
                // 1.25.1. validate閉局後訂正取消受付可能(ProductTypeEnum)
                // [引数] 
                //  銘柄タイプ： ”外国株式” 
                WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
                    ProductTypeEnum.FOREIGN_EQUITY);
            }

            // 1.26. get訂正執行条件( )
            FeqExecutionConditionType l_feqExecutionConditionType = l_feqChangeOrderSpec.getChangeExecutionCondition();
            log.debug("訂正執行条件 = " + l_feqExecutionConditionType);

            // 1.27. get訂正注文期限区分( )
            String l_strChangeOrdExpDiv = l_feqChangeOrderSpec.getChangeOrderExpirationDiv();
            log.debug("訂正注文期限区分 = " + l_strChangeOrdExpDiv);

            // 1.28. get訂正注文有効期限( )
            Date l_datChaOrdExpDate = l_feqChangeOrderSpec.getChangeOrderExpirationDate();
            log.debug("訂正注文有効期限 = " + l_datChaOrdExpDate);

            // 1.29. get発注条件( )
            String l_strOrdCondType = l_feqChangeOrderSpec.getOrderConditionType();
            log.debug("発注条件 = " + l_strOrdCondType);

            // 1.30. get訂正発注条件演算子( )
            String l_strChaOrdCondOperator = l_feqChangeOrderSpec.getChangeOrderCondOperator();
            log.debug("訂正発注条件演算子 = " + l_strChaOrdCondOperator);

            // 1.31. get訂正発注条件単価( )
            double l_dblChaOrdCondPrice = l_feqChangeOrderSpec.getChangeOrderCondPrice();
            log.debug("訂正発注条件単価 = " + l_dblChaOrdCondPrice);
            
            // validate為替登録(外国株式, boolean, boolean)
            // [引数]
            // 外国株式銘柄：　@外国株式銘柄オブジェクト
            // is買付：　@isBuyOrder()の戻り値
            // is約定：　@false
            l_orderMgrResVal.validateRateRegistration(
                l_feqProduct,
                l_blnIsBuy,
                false);

            // 1.32. validate注文条件(String, String, Date, Date,
            //          String, FeqExecutionConditionType, boolean)
            // [引数] 
            //  証券会社コード： 証券会社.getInstitutionCode()の戻り値 
            //  市場コード： 市場.getMarketCode()の戻り値 
            //  原注文発注日： 注文単位.発注日  
            //  注文失効日：　@訂正注文内容.get訂正注文期限()の戻り値 
            //  発注条件： 訂正注文内容.get発注条件()の戻り値 
            //  執行条件：　@訂正注文内容.get訂正執行条件()の戻り値 
            //  is出来るまで注文：　@（以下のとおり） 
            //    訂正注文内容.get訂正注文期限区分 == ”出来るまで注文” の場合、true 
            //    訂正注文内容.get訂正注文期限区分 == ”当日限り” の場合、false

            // 訂正注文内容.get訂正注文期限区分
            String l_strChaOrdExpDiv = l_feqChangeOrderSpec.getChangeOrderExpirationDiv();
            log.debug("訂正注文内容.get訂正注文期限区分 = " + l_strChaOrdExpDiv);
            l_orderMgrResVal.validateOrderCondition(
                l_genInstitution.getInstitutionCode(),
                l_market.getMarketCode(),
                WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), "yyyyMMdd"),
                l_datChaOrdExpDate,
                l_strOrdCondType,
                l_feqExecutionConditionType,
                (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_strChaOrdExpDiv))
                ? true : false);

            // 1.33. validate訂正内容(FeqOrderUnit, double, double,
            //          String, String, Date, String, String, double, double)
            // [引数] 
            //  注文単位： 注文単位オブジェクト 
            //  訂正数量： getAfterChangeOriginalQuantity()の戻り値 
            //  訂正単価： getAfterChangePrice()の戻り値 
            //  訂正執行条件： get訂正執行条件()の戻り値
            //  訂正注文期限区分： get訂正注文期限区分()の戻り値
            //  訂正注文有効期限： get訂正注文有効期限()の戻り値 
            //  発注条件： get発注条件()の戻り値 
            //  訂正発注条件演算子： get訂正発注条件演算子()の戻り値 
            //  訂正発注条件単価： get訂正発注条件単価()の戻り値 
            //  訂正（W指値）訂正指値： get訂正（W指値）訂正指値()の戻り値 
            l_orderMgrResVal.validateChangeSpec(
                l_orderUnit,
                l_dblAftChaOriQuantity,
                l_dblAftChaPrice,
                new Integer(l_feqExecutionConditionType.intValue()).toString(),
                l_strChangeOrdExpDiv,
                l_datChaOrdExpDate,
                l_strOrdCondType,
                l_strChaOrdCondOperator,
                l_dblChaOrdCondPrice,
                l_dblChaWLimitPrice);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + "Error in validate訂正注文", l_ex);
            log.exiting(STR_METHOD_NAME);
            return new OrderValidationResult(
                ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo()));
        }
        catch (NotFoundException l_ex)
        {
            log.debug(STR_METHOD_NAME + "Error in validate訂正注文", l_ex);
            log.exiting(STR_METHOD_NAME);
            return new OrderValidationResult(
                ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));
        }
        catch (OrderValidationException l_ex)
        {
            log.debug(STR_METHOD_NAME + "Error in validate訂正注文", l_ex);
            log.exiting(STR_METHOD_NAME);
            return new OrderValidationResult(
                l_ex.getValidationResult().getProcessingResult());
        }

        log.exiting(STR_METHOD_NAME);
        // 1.34. OrderValidationResult(arg0 : ProcessingResult)
        return new OrderValidationResult(ProcessingResult.SUCCESS_RESULT);
    }

    /**
     * (validate取消注文)<BR>
     * （validateCancelOrderのオーバーライド）<BR>
     * 取消注文の発注審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株注文）validate取消注文」 参照<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     *
     * @@param l_orderSpec - (注文内容)<BR>
     * 注文内容オブジェクト<BR>
     *
     * @@return NewOrderValidationResult
     * @@roseuid 429B4F3A0118
     */
    public OrderValidationResult validateCancelOrder(
        SubAccount l_subAccount,
        CancelOrderSpec l_orderSpec)
    {
        final String STR_METHOD_NAME = "validateCancelOrder(SubAccount, CancelOrderSpec)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_orderSpec == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値がNULL");
        }

        SubAccountRow l_subAccountRow = (SubAccountRow)l_subAccount.getDataSourceObject();
        WEB3GentradeSubAccount l_genSubAccount = new WEB3GentradeSubAccount(l_subAccountRow);

        try
        {
            // 1.1. getInstance( )
            WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
                (WEB3FeqTypeOrderManagerReusableValidations)
                WEB3FeqTypeOrderManagerReusableValidations.getInstance();

            // 1.2. validate注文(補助口座)
            // [引数] 
            //  補助口座： 引数.補助口座 
            this.validateOrder(l_genSubAccount);

            // 1.3. getOrderId( )
            long l_lngOrderId = l_orderSpec.getOrderId();

            // 1.4. validate注文取消可能状態(long)
            // [引数] 
            //  注文ID： getOrderId()の戻り値 
            this.validateOrderCancelPossibleStatus(l_lngOrderId);

            // 1.5. get注文単位ByOrderId(long)
            // [引数] 
            //  注文ID： getOrderUnitId()の戻り値 
            WEB3FeqOrderUnit l_feqOrderUnit = (WEB3FeqOrderUnit)this.getOrderUnitByOrderId(l_lngOrderId);

            // 1.6. get外国株式銘柄(long)
            // [引数] 
            //  銘柄ID： 注文単位.銘柄ID 
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            FeqProductManager l_productManager =
                (FeqProductManager)l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY).getProductManager();

            FeqOrderUnitRow l_ordUnitRow = (FeqOrderUnitRow)l_feqOrderUnit.getDataSourceObject();
            WEB3FeqProduct l_feqProduct = (WEB3FeqProduct)l_productManager.getProduct(
                l_ordUnitRow.getProductId());
            if (l_feqProduct == null)
            {
                log.debug(STR_METHOD_NAME + "該当外国株式銘柄データなし。");
                log.exiting(STR_METHOD_NAME);
                return new OrderValidationResult(
                    ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));
            }

            // 1.7. getProductCode( )
            String l_strProductCode = l_feqProduct.getProductCode();
            log.debug("外国株式銘柄コード" + l_strProductCode);

            // 1.8. validate外株銘柄(証券会社, String)
            // [引数] 
            //  証券会社： 補助口座.getInstitutuin()の戻り値 
            //  銘柄コード： getProductCode()の戻り値 
            WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)l_subAccount.getInstitution();
            l_feqProduct = (WEB3FeqProduct)this.validateFeqProduct(l_institution, l_strProductCode);

            // 1.9. get市場( )
            WEB3GentradeMarket l_market = l_feqProduct.getMarket();

            // 1.10. validate市場(市場)
            // [引数] 
            //  市場： get市場()の戻り値 
            this.validateMarket(l_market);

            // 1.11. validate取扱可能市場(String, String, String)
            // [引数] 
            //  証券会社コード： 証券会社.getInstitutionCode()の戻り値 
            //  部店コード： 補助口座.get取引店().getBranchCode()の戻り値 
            //  市場コード： 市場.getMarketCode()の戻り値 
            this.validateHandlingPossibleMarket(
                l_institution.getInstitutionCode(),
                l_genSubAccount.getWeb3GenBranch().getBranchCode(),
                l_market.getMarketCode());

            // 1.12. validate取引銘柄(外国株式銘柄, 市場, boolean)
            // [引数] 
            //  外国株式銘柄： validate外株銘柄()の戻り値 
            //  市場： get市場()の戻り値 
            //  is買注文：　@注文単位.is買付()の戻り値 
            boolean l_blnIsBuy = l_feqOrderUnit.isBuy();
            log.debug("is買注文" + l_blnIsBuy);
            this.validateTradedProduct(
                l_feqProduct,
                l_market,
                l_blnIsBuy);

            // 1.13. validate顧客銘柄別取引停止(SubAccount, long, OrderTypeEnum)
            // [引数] 
            //  補助口座： 引数.補助口座 
            //  銘柄ID： 外国株式銘柄.銘柄ID 
            //  注文種別： 注文単位.注文種別 
            l_orderMgrResVal.validateAccountProductTradedStop(
                l_subAccount,
                l_feqProduct.getProductId(),
                l_feqOrderUnit.getOrderType());

            // 1.14. getConfirmedQuantity( )
            double l_dblConfirmedQuantity = l_feqOrderUnit.getConfirmedQuantity();
            log.debug("getConfirmedQuantity()" + l_dblConfirmedQuantity);

            // 1.15  (*) getConfirmedQuantity()の戻り値 != NaN の場合
            if (!Double.isNaN(l_dblConfirmedQuantity))
            {
                // 1.15.1. validate閉局後訂正取消受付可能(ProductTypeEnum)
                // [引数] 
                //  銘柄タイプ： ”外国株式” 
                WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
                    ProductTypeEnum.FOREIGN_EQUITY);
            }
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + "Error in validate取消注文", l_ex);
            log.exiting(STR_METHOD_NAME);
            return new OrderValidationResult(
                ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo()));
        }
        catch (NotFoundException l_ex)
        {
            log.debug(STR_METHOD_NAME + "Error in validate取消注文", l_ex);
            log.exiting(STR_METHOD_NAME);
            return new OrderValidationResult(
                ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));
        }

        log.exiting(STR_METHOD_NAME);
        // 1.16. OrderValidationResult(arg0 : ProcessingResult)
        return new OrderValidationResult(ProcessingResult.SUCCESS_RESULT);
    }

    /**
     * (validate外貨決済)<BR>
     * 顧客が指定した通貨で決済が可能かどうかをチェックする。<BR>
     * <BR>
     * 外国株式発注審査個別チェック.validate外貨決済()に委譲（deligate）する。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     *
     * @@param l_strCurrencyCode - (通貨コード)<BR>
     * 通貨コード<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42B6779102DE
     */
    public void validateFcSettle(
        WEB3GentradeSubAccount l_subAccount,
        String l_strCurrencyCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateFcSettle(WEB3GentradeSubAccount, String)";
        log.entering(STR_METHOD_NAME);

        WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3FeqTypeOrderManagerReusableValidations)
            WEB3FeqTypeOrderManagerReusableValidations.getInstance();
        l_orderMgrResVal.validateFcSettle(
            l_subAccount,
            l_strCurrencyCode);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate特定口座開設)<BR>
     * 特定口座のチェックを行う。<BR>
     * <BR>
     * 外国株式発注審査個別チェック.validate特定口座開設()に委譲<BR>
     * （deligate）する。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     *
     * @@param l_datBizDate - (発注日)<BR>
     * 発注日<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42B676630203
     */
    public void validateSpecialAccountEstablish(
        WEB3GentradeSubAccount l_subAccount,
        Date l_datBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateSpecialAccountEstablish(WEB3GentradeSubAccount, Date)";
        log.entering(STR_METHOD_NAME);

        WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3FeqTypeOrderManagerReusableValidations)
            WEB3FeqTypeOrderManagerReusableValidations.getInstance();
        l_orderMgrResVal.validateSpecialAccountEstablish(
            l_subAccount,
            l_datBizDate);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate約定日)<BR>
     * 約定日のチェックを行う。<BR>
     * <BR>
     * 外国株式発注審査個別チェック.validate約定日()に委譲（deligate）する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@param l_datExecDate - (約定日)<BR>
     * 約定日<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4292A2EC02D3
     */
    public void validateExecutionDate(
        WEB3FeqOrderUnit l_orderUnit,
        Date l_datExecDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateExecutionDate(WEB3FeqOrderUnit, Date)";
        log.entering(STR_METHOD_NAME);

        WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3FeqTypeOrderManagerReusableValidations)
            WEB3FeqTypeOrderManagerReusableValidations.getInstance();
        l_orderMgrResVal.validateExecutionDate(
            l_orderUnit,
            l_datExecDate);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate現地受渡日)<BR>
     * 現地受渡日をチェックする。<BR>
     * <BR>
     * 外国株式発注審査個別チェック.validate現地受渡日()<BR>
     * に委譲（deligate）する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@param l_datFDeliveryDate - (現地受渡日)<BR>
     * 現地受渡日<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42B66851038A
     */
    public void validateFDeliveryDate(
        WEB3FeqOrderUnit l_orderUnit,
        Date l_datFDeliveryDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateFDeliveryDate(WEB3FeqOrderUnit, Date)";
        log.entering(STR_METHOD_NAME);

        WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3FeqTypeOrderManagerReusableValidations)
            WEB3FeqTypeOrderManagerReusableValidations.getInstance();
        l_orderMgrResVal.validateFDeliveryDate(
            l_orderUnit,
            l_datFDeliveryDate);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate約定数量)<BR>
     * 約定数量をチェックする。<BR>
     * <BR>
     * 外国株式発注審査個別チェック.validate約定数量()に委譲（deligate）する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@param l_dblQuantity - (約定数量)<BR>
     * 約定数量（今回）<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4292A2EC02E3
     */
    public void validateExecutedQuantity(
        WEB3FeqOrderUnit l_orderUnit,
        double l_dblQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateExecutedQuantity(WEB3FeqOrderUnit, double)";
        log.entering(STR_METHOD_NAME);

        WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3FeqTypeOrderManagerReusableValidations)
            WEB3FeqTypeOrderManagerReusableValidations.getInstance();
        l_orderMgrResVal.validateExecutedQuantity(
            l_orderUnit,
            l_dblQuantity);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate約定単価)<BR>
     * 約定単価をチェックする。<BR>
     * <BR>
     * 外国株式発注審査個別チェック.validate約定単価()に委譲（deligate）する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@param l_dblPrice - (約定単価)<BR>
     * 約定単価<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42C39F0D0331
     */
    public void validateExecutedPrice(
        WEB3FeqOrderUnit l_orderUnit,
        double l_dblPrice) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateExecutedPrice(WEB3FeqOrderUnit, double)";
        log.entering(STR_METHOD_NAME);

        WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3FeqTypeOrderManagerReusableValidations)
            WEB3FeqTypeOrderManagerReusableValidations.getInstance();
        l_orderMgrResVal.validateExecutedPrice(
            l_orderUnit,
            l_dblPrice);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get注文単位ByOrderId)<BR>
     * 注文ＩＤに該当する注文単位を取得する。<BR>
     * <BR>
     * １）　@注文オブジェクト取得<BR>
     * 　@getOrder(注文ＩＤ)にて注文オブジェクトを取得する。<BR>
     * 　@注文ＩＤに該当する注文単位が存在しない場合は例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02011<BR>
     * <BR>
     * ２）　@注文単位取得<BR>
     * 　@注文.getOrderUnits()にて注文単位の配列を取得する。<BR>
     * 　@注文ＩＤに該当する注文単位が２件以上あった場合<BR>
     * 　@（配列の要素数が１でない場合）、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02012<BR>
     * <BR>
     * 　@取得した注文単位を返却する。<BR>
     * @@param l_lngOrderId - (注文ＩＤ)<BR>
     * 注文ＩＤ<BR>
     * @@return FeqOrderUnit
     * @@throws WEB3BaseException
     * @@roseuid 428BF9050248
     */
    public FeqOrderUnit getOrderUnitByOrderId(long l_lngOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderUnitByOrderId(long)";
        log.entering(STR_METHOD_NAME);

        // １）　@注文オブジェクト取得
        FeqOrder l_feqOrder = null;
        try
        {
            l_feqOrder = (FeqOrder)getOrder(l_lngOrderId);
        }
        catch (NotFoundException l_ex)
        {
            log.debug(STR_METHOD_NAME + "該当注文が存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02011,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                WEB3ErrorCatalog.BUSINESS_ERROR_02011.getErrorMessage(),
                l_ex);
        }
        if (l_feqOrder == null)
        {
            log.debug(STR_METHOD_NAME + "該当注文が存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02011,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // ２）　@注文単位取得
        OrderUnit[] l_orderUnits = l_feqOrder.getOrderUnits();
        if (l_orderUnits == null || l_orderUnits.length != 1)
        {
            log.debug(STR_METHOD_NAME + "該当注文単位要素数が１でない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02012,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return (FeqOrderUnit)l_orderUnits[0];
    }

    /**
     * (get有効注文単位By運用コード)<BR>
     * 運用コードに該当する注文単位を取得する。<BR>
     * <BR>
     * １）　@注文単位取得<BR>
     * 　@以下の条件にて注文単位行を取得する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@外株注文単位.発注日 =発注日 And<BR>
     * 　@外株注文単位.運用コード = 運用コード And<BR>
     * 　@外株注文単位.注文有効状態 = 1：OPEN And<BR>
     * 　@外株注文単位.証券会社コード = 管理者の証券会社コード()※<BR>
     * <BR>
     * 　@該当する行が存在しない場合は、該当注文なしの例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02086<BR>
     * <BR>
     * 　@※ 管理者の証券会社コード<BR>
     * 　@セッション情報より取得する。<BR>
     * 　@セッションより管理者が取得できなかった場合は、当該条件を指定しない。<BR>
     * <BR>
     * ２）　@注文単位取得<BR>
     * 　@toOrderUnit()にて注文単位オブジェクトを生成し返却する。<BR>
     * <BR>
     * 　@[toOrderUnit()に指定する引数]<BR>
     * 　@row：　@１）で取得した行オブジェクト<BR>
     * @@param l_datBizDate - (発注日)<BR>
     * 発注日<BR>
     * @@param l_strOrderEmpCode - (運用コード)<BR>
     * 運用コード<BR>
     * @@return FeqOrderUnit
     * @@throws WEB3BaseException
     * @@roseuid 428BFBE10083
     */
    public FeqOrderUnit getValidOrderUnitByOrderEmpCode(
        Date l_datBizDate,
        String l_strOrderEmpCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getValidOrderUnitByOrderEmpCode(Date, String)";
        log.entering(STR_METHOD_NAME);

        // １）　@注文単位取得
        FeqOrderUnit l_orderUnit = null;
        StringBuffer l_sbWhere = new StringBuffer();
        ArrayList l_alisQueryVars = new ArrayList();
        l_sbWhere.append(" biz_date = ? ");                 //発注日
        l_alisQueryVars.add(WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd")); //発注日
        l_sbWhere.append(" and order_emp_code = ? ");       //運用コード
        l_alisQueryVars.add(l_strOrderEmpCode);             //運用コード
        l_sbWhere.append(" and order_open_status = ? ");    //注文有効状態
        l_alisQueryVars.add(OrderOpenStatusEnum.OPEN);      //注文有効状態

        WEB3Administrator l_administrator;
        try
        {
            //セッションより管理者が取得
            l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        }
        catch (Exception l_ex)
        {
            l_administrator = null;
        }

        //セッションより管理者が取得できた場合
        if (l_administrator != null)
        {
            l_sbWhere.append(" and institution_code = ? ");                 //証券会社コード
            l_alisQueryVars.add(l_administrator.getInstitutionCode());      //証券会社コード
        }

        List l_lisRecords = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            Object[] l_objVars = new Object[l_alisQueryVars.size()];
            l_alisQueryVars.toArray(l_objVars);
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                FeqOrderUnitRow.TYPE,
                l_sbWhere.toString(),
                l_objVars);
        }
        catch (DataException l_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        // 該当する行が存在しない場合は、該当注文なしの例外をスローする。
        if (l_lisRecords == null || l_lisRecords.isEmpty())
        {
            log.debug("該当する注文IDデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02086,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (l_lisRecords.size() > 1)
        {
            log.debug("該当する注文IDデータは不整合ある。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // ２）　@注文単位取得
        l_orderUnit = (FeqOrderUnit)this.toOrderUnit((FeqOrderUnitRow) l_lisRecords.get(0));

        log.exiting(STR_METHOD_NAME);
        return l_orderUnit;
    }

    /**
     * (get注文単位By運用コード)<BR>
     * 運用コードに該当する注文単位を取得する。<BR>
     * <BR>
     * １）　@注文単位取得<BR>
     * 　@以下の条件にて注文単位行を取得する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@外株注文単位.発注日 =発注日 And<BR>
     * 　@外株注文単位.運用コード = 運用コード And<BR>
     * 　@外株注文単位.証券会社コード = 管理者の証券会社コード()※<BR>
     * <BR>
     * 　@該当する行が存在しない場合は、該当注文なしの例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02086<BR>
     * <BR>
     * 　@※ 管理者の証券会社コード<BR>
     * 　@セッション情報より取得する。<BR>
     * 　@セッションより管理者が取得できなかった場合は、当該条件を指定しない。<BR>
     * <BR>
     * ２）　@注文単位取得<BR>
     * 　@toOrderUnit()にて注文単位オブジェクトを生成し返却する。<BR>
     * <BR>
     * 　@[toOrderUnit()に指定する引数]<BR>
     * 　@row：　@１）で取得した行オブジェクト<BR>
     * @@param l_datBizDate - (発注日)<BR>
     * 発注日<BR>
     * @@param l_strOrderEmpCode - (運用コード)<BR>
     * 運用コード<BR>
     * @@return FeqOrderUnit
     * @@throws WEB3BaseException
     * @@roseuid 42AFD0E401C4
     */
    public FeqOrderUnit getOrderUnitByOrderEmpCode(
        Date l_datBizDate,
        String l_strOrderEmpCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderUnitByOrderEmpCode(Date, String)";
        log.entering(STR_METHOD_NAME);

        // １）　@注文単位取得
        FeqOrderUnit l_orderUnit = null;
        StringBuffer l_sbWhere = new StringBuffer();
        ArrayList l_alisQueryVars = new ArrayList();
        l_sbWhere.append(" biz_date = ? ");                                 //発注日
        l_alisQueryVars.add(WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd")); //発注日
        l_sbWhere.append(" and order_emp_code = ? ");                       //運用コード
        l_alisQueryVars.add(l_strOrderEmpCode);                             //運用コード

        WEB3Administrator l_administrator;
        try
        {
            //セッションより管理者が取得
            l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        }
        catch (Exception l_ex)
        {
            l_administrator = null;
        }

        //セッションより管理者が取得できた場合
        if (l_administrator != null)
        {
            l_sbWhere.append(" and institution_code = ? ");                 //証券会社コード
            l_alisQueryVars.add(l_administrator.getInstitutionCode());      //証券会社コード
        }

        List l_lisRecords = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            Object[] l_objVars = new Object[l_alisQueryVars.size()];
            l_alisQueryVars.toArray(l_objVars);
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                FeqOrderUnitRow.TYPE,
                l_sbWhere.toString(),
                l_objVars);
        }
        catch (DataException l_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        // 該当する行が存在しない場合は、該当注文なしの例外をスローする。
        if (l_lisRecords == null || l_lisRecords.isEmpty())
        {
            log.debug("該当する注文IDデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02086,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (l_lisRecords.size() > 1)
        {
            log.debug("該当する注文IDデータは不整合ある。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // ２）　@注文単位取得
        l_orderUnit = (FeqOrderUnit)this.toOrderUnit((FeqOrderUnitRow) l_lisRecords.get(0));

        log.exiting(STR_METHOD_NAME);
        return l_orderUnit;
    }

    /**
     * (get有効約定By約定番号)<BR>
     * 約定番号に該当する約定を取得する。<BR>
     * <BR>
     * １）　@約定取得<BR>
     * 　@以下の条件にて約定行を取得する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@外株約定.注文単位ＩＤ = 注文単位ＩＤ And<BR>
     * 　@外株約定.約定順番号 = 約定番号 And<BR>
     * 　@外株約定.削除フラグ = BooleanEnum.FALSE<BR>
     * <BR>
     * 　@該当する行が存在しない場合は例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_01321<BR>
     * <BR>
     * ２）　@約定取得<BR>
     * 　@toOrderExecution()にて約定オブジェクトを生成し返却する。<BR>
     * <BR>
     * 　@[toOrderExecution()に指定する引数]<BR>
     * 　@row：　@１）で取得した行オブジェクト<BR>
     * @@param l_lngOrderUnitＩd - (注文単位ＩＤ)<BR>
     * 注文単位ＩＤ<BR>
     * @@param l_intExecSerialNo - (約定通番)<BR>
     * 約定通番<BR>
     * @@return OrderExecution
     * @@throws WEB3BaseException
     * @@roseuid 4292DAF601AA
     */
    public OrderExecution getValidExecByExecNo(
        long l_lngOrderUnitId,
        int l_intExecSerialNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getValidExecByExecNo(long, int)";
        log.entering(STR_METHOD_NAME);

        // １）　@約定取得
        OrderExecution l_orderExec = null;
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" order_unit_id = ? ");            //注文単位ＩＤ
        l_sbWhere.append(" and exec_serial_no = ? ");       //約定通番
        l_sbWhere.append(" and delete_flag = ? ");          //削除フラグ

        Object[] l_objVars = {
            new Long(l_lngOrderUnitId),                     //注文単位ＩＤ
            new Long(l_intExecSerialNo),                    //約定通番
            BooleanEnum.FALSE};                             //削除フラグ

        List l_lisRecords = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                FeqOrderExecutionRow.TYPE,
                l_sbWhere.toString(),
                l_objVars);
        }
        catch (DataException l_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        // 該当する行が存在しない場合は例外をスローする。
        if (l_lisRecords == null || l_lisRecords.isEmpty())
        {
            log.debug("該当する注文単位ID、約定通番データがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01321,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (l_lisRecords.size() > 1)
        {
            log.debug("該当する注文単位ID、約定通番データは不整合ある。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // ２）　@約定取得
        l_orderExec = (OrderExecution)this.toOrderExecution(
            (FeqOrderExecutionRow) l_lisRecords.get(0));

        log.exiting(STR_METHOD_NAME);
        return l_orderExec;
    }

    /**
     * (get約定)<BR>
     * 株数，単価に該当する約定を取得する。<BR>
     * <BR>
     * １）　@約定取得<BR>
     * 　@以下の条件にて約定行を取得する。<BR>
     * 　@※約定日時 昇順, 約定順番号 昇順でソートする。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@外株約定.注文単位ＩＤ = 注文単位ＩＤ<BR>
     * 　@外株約定.約定数量 = 約定数量<BR>
     * 　@外株約定.約定単価 = 約定単価<BR>
     * 　@外株約定.削除フラグ = "0：FALSE"<BR>
     * <BR>
     * 　@該当する行が存在しない場合は例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_01321<BR>
     * <BR>
     * ２）　@約定取得<BR>
     * 　@toOrderExecution()にて約定オブジェクトを生成し返却する。<BR>
     * <BR>
     * 　@[toOrderExecution()に指定する引数]<BR>
     * 　@row：　@１）で取得した行オブジェクトの0番目の要素<BR>
     * @@param l_lngOrderUnitId - (注文単位ＩＤ)<BR>
     * 注文単位ＩＤ<BR>
     * @@param l_dblQuantity - (約定数量)<BR>
     * 約定数量<BR>
     * @@param l_dblPrice - (約定単価)<BR>
     * 約定単価<BR>
     * @@return OrderExecution
     * @@throws WEB3BaseException
     * @@roseuid 4292DBFD01E9
     */
    public OrderExecution getExecution(
        long l_lngOrderUnitId,
        double l_dblQuantity,
        double l_dblPrice) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExecution(long, double, double)";
        log.entering(STR_METHOD_NAME);

        // １）　@約定取得
        OrderExecution l_orderExec = null;
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" order_unit_id = ? ");            //注文単位ＩＤ
        l_sbWhere.append(" and exec_quantity = ? ");        //約定数量
        l_sbWhere.append(" and exec_price = ? ");           //約定単価
        l_sbWhere.append(" and delete_flag = ? ");          //削除フラグ

        // ソート条件
        String l_strOrderBy = " exec_timestamp asc, exec_serial_no asc ";

        Object[] l_objVars = {
            new Long(l_lngOrderUnitId),                     //注文単位ＩＤ
            new Double(l_dblQuantity),                      //約定数量
            new Double(l_dblPrice),                         //約定単価
            BooleanEnum.FALSE};                             //削除フラグ

        List l_lisRecords = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                FeqOrderExecutionRow.TYPE,
                l_sbWhere.toString(),
                l_strOrderBy,
                null,
                l_objVars);
        }
        catch (DataException l_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        // 該当する行が存在しない場合は例外をスローする。
        if (l_lisRecords == null || l_lisRecords.isEmpty())
        {
            log.debug("該当する注文単位IDデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01321,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // ２）　@約定取得
        l_orderExec = (OrderExecution)toOrderExecution((FeqOrderExecutionRow) l_lisRecords.get(0));

        log.exiting(STR_METHOD_NAME);
        return l_orderExec;
    }

    /**
     * (get計算用注文単価)<BR>
     * 計算用注文単価を取得する。<BR>
     * <BR>
     * １）引数.注文単価区分 == ”成行” の場合<BR>
     * <BR>
     *    時価を取得する。<BR>
     * <BR>
     *    外国株式プロダクトマネージャ.get時価()をコールする。<BR>
     * <BR>
     *    [引数]<BR>
     *    取引銘柄： 引数.取引銘柄<BR>
     * <BR>
     * ２）計算用注文単価を決定する。<BR>
     * <BR>
     * ２－１）引数.is買付 == true の場合<BR>
     * <BR>
     *    以下の中から、一番金額の大きいものとする。<BR>
     * <BR>
     *    引数.注文単価<BR>
     *    引数.訂正単価<BR>
     *    １）の取得時価<BR>
     * <BR>
     * ２－２）引数.is買付 == false の場合<BR>
     * <BR>
     *    引数.注文単価区分 == ”指値” の場合、引数.注文単価<BR>
     *    引数.注文単価区分 == ”成行” の場合、１）で取得した時価<BR>
     * <BR>
     *    とする。<BR>
     *
     * ３）概算金額計算方式の考慮<BR>
     * <BR>
     *    引数.is買付 == true and<BR>
     *    ２）で決定した計算用注文単価が１）の取得時価<BR>
     * <BR>
     *    の場合、処理を行う。<BR>
     *    ※計算式書「計算用注文単価」参照<BR>
     * <BR>
     * ４）値を返却する。<BR>
     * @@param l_feqProduct - (取引銘柄)<BR>
     * 外国株式取引銘柄オブジェクト
     *
     * @@param l_branch - (部店)<BR>
     * 部店オブジェクト
     *
     * @@param l_strOrderPriceDiv - (注文単価区分)<BR>
     * 注文単価区分
     *
     * @@param l_dblPrice - (注文単価)<BR>
     * 注文単価
     *
     * @@param l_dblChangePrice - (訂正単価)<BR>
     * 訂正単価
     *
     * @@param l_blnIsBuy - (is買付)<BR>
     * 買付注文かどうかを判断するフラグ<BR>
     * <BR>
     * true： 買付注文<BR>
     * false： 売付注文<BR>
     *
     * @@return double
     * @@roseuid 428C90420070
     */
    public double getUnitPrice(
        WEB3FeqTradedProduct l_feqProduct,
        WEB3GentradeBranch l_branch,
        String l_strOrderPriceDiv,
        double l_dblPrice,
        double l_dblChangePrice,
        boolean l_blnIsBuy) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getUnitPrice(WEB3FeqTradedProduct, WEB3GentradeBranch, String, "
            + "double, double, boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_feqProduct == null || l_branch == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値がNULL");
        }

        //１）引数.注文単価区分 == ”成行” の場合
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqProductManager l_productManager =
            (WEB3FeqProductManager)l_tradingModule.getProductManager();

        double l_dblCurrentPrice = 0.0D;
        if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_strOrderPriceDiv))
        {
            //時価を取得する。
            l_dblCurrentPrice = l_productManager.getCurrentPrice(l_feqProduct);
        }
        log.debug("時価 = " + l_dblCurrentPrice);

        // ２）計算用注文単価を決定する。
        // ２－１）引数.is買付 == true の場合
        double l_dblMaxPrice = 0.0D;
        if (l_blnIsBuy)
        {
            // 以下の中から、一番金額の大きいものとする。
            l_dblMaxPrice = Math.max(Math.max(l_dblPrice, l_dblChangePrice), l_dblCurrentPrice);
        }
        // ２－２）引数.is買付 == false の場合
        else
        {
            // 引数.注文単価区分 == ”指値” の場合、引数.注文単価
            // 引数.注文単価区分 == ”成行” の場合、１）で取得した時価
            l_dblMaxPrice = WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrderPriceDiv)
                ? l_dblPrice : l_dblCurrentPrice;
        }
        log.debug("一番金額の大きい = " + l_dblMaxPrice);

        // ３）概算金額計算方式の考慮
        // 引数.is買付 == true and ２）で決定した計算用注文単価が１）の取得時価
        // の場合、処理を行う。
        double l_dblReturn = l_dblMaxPrice;
        if (l_blnIsBuy && l_dblMaxPrice == l_dblCurrentPrice)
        {
            // ※計算式書「計算用注文単価」参照
            // 2.1.  計算方式取得
            // 会社部店商品行オブジェクト取得
            InstBranchProductRow l_instBraProRow = null;
            try
            {
                // 会社部店商品.部店ＩＤ = 部店.getBranchId() and
                // 会社部店商品.手数料商品コード = “外国株式”
                l_instBraProRow = InstBranchProductDao.findRowByPk(
                    l_branch.getBranchId(),
                    WEB3CommisionProductCodeDef.FOREIGN_EQITY.toString());

                if (l_instBraProRow == null)
                {
                    log.debug("該当する部店ＩＤ、手数料商品コードデータがありません。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
            catch(DataFindException l_ex)
            {
                log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataException l_ex)
            {
                log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            // get概算金額計算方式()
            int l_intEstPriceCalcForm = l_instBraProRow.getEstimatePriceCalcForm();
            log.debug("概算金額計算方式 = " + l_intEstPriceCalcForm);

            // 2.2.  計算方法@判定、計算単価返却
            //  1. 概算金額計算方式 = “割増拘束方式”の場合
            if (WEB3PremiumRestraintRateDef.PREMIUM_RESTRANT == l_intEstPriceCalcForm)
            {
                // １）基準値取得
                double l_dblBase = l_dblMaxPrice;
                log.debug("基準値 = " + l_dblBase);

                // ２）割増拘束率の反映
                l_dblReturn =
                    new BigDecimal(String.valueOf(l_dblBase))
                        .multiply(new BigDecimal(String.valueOf(l_instBraProRow.getPremiumRestraintRate())))
                        .doubleValue();
                log.debug("割増拘束率の反映 = " + l_dblReturn);
            }
            //  2. 概算金額計算方式 = “STOP高拘束方式”の場合
            else if (WEB3PremiumRestraintRateDef.STOP_QUANTITY_RESTRANT == l_intEstPriceCalcForm)
            {
                // １）STOP高算出基準値取得
                double l_dblStopHighPrice =
                    (WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone()) ?
                    l_feqProduct.getLastClosingPrice() :
                    l_dblMaxPrice;
                log.debug("STOP高算出基準値 = " + l_dblStopHighPrice);

                // 2) STOP高算出
                // （*2-1）外国株式銘柄取得
                WEB3FeqProduct l_product = (WEB3FeqProduct)l_feqProduct.getProduct();
                // （*2-2）制限値幅取得
                double l_dblLimitRange = l_productManager.getLimitRange(l_product, l_dblStopHighPrice);
                log.debug("制限値幅 = " + l_dblLimitRange);

                // （*2-3）刻み値取得
                double l_dblTickValue = l_productManager.getTickValue(l_product, l_dblStopHighPrice);
                log.debug("刻み値 = " + l_dblTickValue);

                // （*2-4）値幅基準値取得
                WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
                    (WEB3FeqTypeOrderManagerReusableValidations)
                    WEB3FeqTypeOrderManagerReusableValidations.getInstance();
                double l_dblBasePrice = l_orderMgrResVal.calcBasePrice(l_dblStopHighPrice, l_dblTickValue);
                log.debug("値幅基準値 = " + l_dblBasePrice);

                // （*2-5）値幅上限（STOP高）算出
                l_dblReturn = l_orderMgrResVal.calcRangeCap(
                    l_dblBasePrice,
                    l_dblLimitRange,
                    l_product);
                log.debug("値幅上限（STOP高） = " + l_dblReturn);
            }
        }
        log.debug("計算用注文単価 = " + l_dblReturn);

        log.exiting(STR_METHOD_NAME);
        return l_dblReturn;
    }

    /**
     * (update概算受渡代金)<BR>
     * 概算受渡代金再計算を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株注文）update概算受渡代金」参照。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@param l_datBaseDate - (基準日)<BR>
     * 基準日<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4292B16A015C
     */
    public void updateEstimatedPrice(
        WEB3FeqOrderUnit l_orderUnit,
        Date l_ExecDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateEstimatedPrice(WEB3FeqOrderUnit, Date)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値がNULL");
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);

        // 1.1. getOrderOpenStatus( )
        OrderOpenStatusEnum l_ordOpenStatue = l_orderUnit.getOrderOpenStatus();
        double l_dblNetAmount = 0.0D;
        double l_dblNetAmountFc = 0.0D;
        double l_dblConfirmedNetAmount = 0.0D;
        double l_dblConfirmedNetAmountFc = 0.0D;
        // 1.2. (*) 注文がクローズしている場合（getOrderOpenStatus() == "クローズ"）
        FeqOrderUnitRow l_orderUnitRow = (FeqOrderUnitRow)l_orderUnit.getDataSourceObject();
        if (OrderOpenStatusEnum.CLOSED.equals(l_ordOpenStatue) &&
            !l_orderUnit.isUnexecuted())
        {

            WEB3FeqFinTransactionManager l_finTransactionMgr =
                (WEB3FeqFinTransactionManager)l_tradingModule.getFinTransactionManager();

            // 1.2.1. get受渡代金合計（外貨）(外国株式注文単位)
            // [get受渡代金合計（外貨）()に指定する引数] 
            //  注文単位：　@注文単位 
            l_dblNetAmountFc = Math.abs(l_finTransactionMgr.getNetAmountFc(l_orderUnit));

            // 1.2.2. get受渡代金合計(外国株式注文単位)
            // [get受渡代金合計()に指定する引数] 
            //  注文単位：　@注文単位 
            l_dblNetAmount = Math.abs(l_finTransactionMgr.getNetAmount(l_orderUnit));

            FeqOrderUnitParams l_orderUnitParams = new FeqOrderUnitParams(l_orderUnitRow);
            // 概算受渡代金
            l_orderUnitParams.setEstimatedPrice(l_dblNetAmount);
            // 概算受渡代金（外貨）
            l_orderUnitParams.setFEstimatedPrice(l_dblNetAmountFc);
            // 市場から確認済みの概算受渡代金
            l_orderUnitParams.setConfirmedEstimatedPrice(l_dblNetAmount);
            // 市場から確認済みの概算受渡代金（外貨）
            l_orderUnitParams.setConfirmedFEstimatedPrice(l_dblNetAmountFc);
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

                // 1.2.3 doUpdateQuery(arg0 : String, arg1 : PrimaryKey, arg2 : Map)
                l_queryProcessor.doUpdateQuery(l_orderUnitParams);

                // 注文履歴（※注文履歴最終通番の履歴）を取得する。
                // 注文履歴最終通番を取得する。
                // 注文履歴Rowを取得する。
                String l_strWhere = " order_unit_id = ?  and order_action_serial_no = ? ";
                Object[] l_objVars = {
                    new Long(l_orderUnit.getOrderUnitId()),
                    new Integer(l_orderUnitRow.getLastOrderActionSerialNo())};
                List l_lisOrderActions = l_queryProcessor.doFindAllQuery(
                    FeqOrderActionRow.TYPE,
                    l_strWhere,
                    l_objVars);

                if (l_lisOrderActions == null || l_lisOrderActions.isEmpty())
                {
                    log.debug("該当する注文単位ID、注文履歴最終通番データがありません。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
                if (l_lisOrderActions.size() > 1)
                {
                    log.debug("該当する注文単位ID、注文履歴最終通番データは不整合ある。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }

                FeqOrderActionRow l_orderActionRow = (FeqOrderActionRow) l_lisOrderActions.get(0);
                FeqOrderActionParams l_orderActionParams = new FeqOrderActionParams(l_orderActionRow);
                
                // 概算受渡代金
                l_orderActionParams.setEstimatedPrice(l_dblNetAmount);
                // 概算受渡代金（外貨）
                l_orderActionParams.setFEstimatedPrice(l_dblNetAmountFc);
                // 市場から確認済みの概算受渡代金
                l_orderActionParams.setConfirmedEstimatedPrice(l_dblNetAmount);
                // 市場から確認済みの概算受渡代金（外貨）
                l_orderActionParams.setConfirmedFEstimatedPrice(l_dblNetAmountFc);
                
                //1.2.3 doUpdateQuery(arg0 : String, arg1 : PrimaryKey, arg2 : Map)                
                l_queryProcessor.doUpdateQuery(l_orderActionParams);
            }
            catch (DataException l_ex)
            {
                log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            // 1.2.4. return
            return;
        }

        // 1.3. get補助口座( )
        WEB3GentradeSubAccount l_subAccount = l_orderUnit.getSubAccount();

        // 1.4. getProduct( )
        WEB3FeqProduct l_product = (WEB3FeqProduct)l_orderUnit.getProduct();

        // 1.5. get市場( )
        WEB3GentradeMarket l_market = l_orderUnit.getMarket();

        // 1.6. getQuantity( )
        double l_dblQuantity = l_orderUnitRow.getQuantity();
        log.debug("注文数量 = " + l_dblQuantity);

        //getConfirmedQuantity( )
        double l_dblConfirmedQuantity = l_orderUnitRow.getConfirmedQuantity();

        //getConfirmedOrderPrice( )
        double l_dblConfirmedOrderPrice = l_orderUnitRow.getConfirmedOrderPrice();

        // 1.7. getExecutedQuantity( )
        double l_dblExecQuantity = l_orderUnitRow.getExecutedQuantity();
        log.debug("約定数量 = " + l_dblExecQuantity);

        // 1.8. get通貨( )
        WEB3GentradeCurrency l_genCurrency = l_orderUnit.getCurrency();

        // 1.9. is買付( )
        boolean l_blnIsBuy = l_orderUnit.isBuy();
        log.debug("is買付 = " + l_blnIsBuy);

        // get適用為替レート(外国株式注文単位)
        // トランザクションを取得する。
        // 注文単位：　@注文単位
        WEB3FeqFinTransactionManager l_finTransactionMgr =
            (WEB3FeqFinTransactionManager)l_tradingModule.getFinTransactionManager();
        Double l_fxRate = l_finTransactionMgr.getFxRate(l_orderUnit);

        // 1.10. get為替レート(boolean, boolean, double)
        // [get為替レート()に指定する引数] 
        //  is買付：　@is買付() 
        //  is約定計算：　@false 
        //  入力為替レート：　@get適用為替レートより取得した適用為替レート
        double l_dbReplaceRecord = l_fxRate.doubleValue();
        double l_dblRxRate = l_genCurrency.getExchangeRate(l_blnIsBuy, false, l_dbReplaceRecord);
        log.debug("為替レート = " + l_dblRxRate);

        // 1.11. get計算用注文単価(外国株式取引銘柄, 部店, String, double, double, boolean)
        // [引数]  
        //  取引銘柄： 注文単位.getProduct().get取引銘柄()の戻り値  
        //  部店： 補助口座.get取引店()の戻り値 
        //  注文単価区分：  
        // 　@（注文単位.is指値() == true）の場合、”指値” 
        // 　@以外、”成行” 
        //  注文単価： 
        //  　@注文単位.getQuantity( ) - 注文単位.getExecutedQuantity( ) < 0　@の場合、 
        //  　@　@　@注文単位．市場から確認済みの注文単価
        //  　@以外の場合、 
        //  　@　@　@注文単位．注文単価
        //  訂正単価： 注文単位.（W指値）訂正指値 
        //  is買付： is買付() 
        double l_dblWPrice = 0.0D;
        if ((l_dblQuantity - l_dblExecQuantity) < 0)
        {
            l_dblWPrice = l_dblConfirmedOrderPrice;
        }
        else
        {
            l_dblWPrice = l_orderUnitRow.getPrice();
        }
        log.debug("注文単位.注文単価 = " + l_dblWPrice);
        double l_dblCalcOrdPrice = this.getUnitPrice(
            l_product.getFeqTradedProduct(),
            l_subAccount.getWeb3GenBranch(),
            l_orderUnit.isLimitOrder()
                ? WEB3OrderPriceDivDef.LIMIT_PRICE
                : WEB3OrderPriceDivDef.MARKET_PRICE,
            l_dblWPrice,
            l_orderUnitRow.getWLimitPrice(),
            l_blnIsBuy);
        log.debug("計算用注文単価 = " + l_dblCalcOrdPrice);

        // 1.12. calc売買代金(double, double)
        // [calc売買代金()に指定する引数] 
        //  株数：　@ 
        //  　@注文単位.getQuantity( ) - 注文単位.getExecutedQuantity( ) < 0　@の場合、 
        //  　@　@　@注文単位.getConfirmedQuantity( ) - 注文単位.getExecutedQuantity( ) 
        //  　@以外の場合、 
        //  　@　@　@注文単位.getQuantity( ) - 注文単位.getExecutedQuantity( )
        //  単価：　@get計算用注文単価() 
        double l_dblWQuantity = 0.0D;
        if ((l_dblQuantity - l_dblExecQuantity) < 0)
        {
            l_dblWQuantity = l_dblConfirmedQuantity - l_dblExecQuantity;
        }
        else
        {
            l_dblWQuantity = l_dblQuantity - l_dblExecQuantity;
        }
        log.debug("数量 = " + l_dblQuantity);
        log.debug("約定済数量 = " + l_dblExecQuantity);
        WEB3FeqBizLogicProvider l_feqBizLogicProvider =
            (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();
        double l_dblUnExedAmount = l_feqBizLogicProvider.calcExecutionAmount(
            l_dblWQuantity,
            l_dblCalcOrdPrice);
        BigDecimal l_bdUnExedAmount = new BigDecimal(l_dblUnExedAmount);
        int l_intScale = l_genCurrency.getScale();
        l_bdUnExedAmount = l_bdUnExedAmount.setScale(l_intScale, BigDecimal.ROUND_HALF_EVEN);
        log.debug("未約定分の売買代金 = " + l_bdUnExedAmount.doubleValue());

        // 1.13. getExecutions( )
        OrderExecution[] l_ordExecutions = l_orderUnit.getExecutions();
        BigDecimal l_bdExedAmountSum = new BigDecimal("0");
        if (l_ordExecutions != null)
        {
            // 1.14. (*) 約定（getExecution()の戻り値）の数分LOOP処理
            for (int i = 0; i < l_ordExecutions.length; i++)
            {
                BigDecimal l_bdExedAmount = new BigDecimal("0");
                WEB3FeqOrderExecution l_orderExec = (WEB3FeqOrderExecution)l_ordExecutions[i];
                FeqOrderExecutionRow l_orderExecRow = (FeqOrderExecutionRow)l_orderExec.getDataSourceObject();
    
                // 1.14.1. is削除済( )
                boolean l_blnIsDeleted = l_orderExec.isDeleted();
    
                // 1.14.2. (*) 削除済みでない場合（is削除済() == false）
                if (!l_blnIsDeleted)
                {
                    // 1.14.2.1. getExecutionQuantity( )
                    double l_dblExecutionQuantity = l_orderExecRow.getExecQuantity();
                    log.debug("約定.約定数量 = " + l_dblExecutionQuantity);
    
                    // 1.14.2.2. getExecutionPrice( )
                    double l_dblExecPrice = l_orderExecRow.getExecPrice();
                    log.debug("約定.約定単価 = " + l_dblExecPrice);
    
                    // 1.14.2.3. calc売買代金(double, double)
                    // [calc売買代金()に指定する引数] 
                    //  株数：　@getExecutionQuantity() 
                    //  単価：　@getExecutionPrice() 
                    double l_dblExedAmount = l_feqBizLogicProvider.calcExecutionAmount(
                        l_dblExecutionQuantity,
                        l_dblExecPrice);
                    l_bdExedAmount = new BigDecimal(l_dblExedAmount);
                    l_bdExedAmount = l_bdExedAmount.setScale(l_intScale, BigDecimal.ROUND_HALF_EVEN);
                }
                log.debug("約定代金 = " + l_bdExedAmount.doubleValue());
                l_bdExedAmountSum = l_bdExedAmountSum.add(l_bdExedAmount);
            }
        }
        log.debug("約定済の売買代金 = " + l_bdExedAmountSum.doubleValue());

        // 1.15. calc外国株式金額(補助口座, 外国株式銘柄, 市場, Date, double, double,
        //           boolean, boolean, boolean, String)
        // [calc外国株式金額()に指定する引数] 
        //  補助口座：　@get補助口座() 
        //  外国株式銘柄：　@getProduct() 
        //  市場：　@get市場() 
        //  基準日：　@基準日 
        //  約定日：　@パラメータ．約定日
        //  売買代金：　@未約定分の売買代金＋約定済の売買代金合計値（SUM） 
        //  為替レート：　@get適用為替レートより取得した適用為替レート 
        //  is買付：　@is買付() 
        //  is約定計算：　@false 
        //  is指値：　@注文単位.is指値()の戻り値 
        //  注文チャネル：　@注文単位.初回注文の注文チャネル
        Date l_datBaseDate = WEB3DateUtility.getDate(l_orderUnit.getBizDate(), "yyyyMMdd");
        WEB3FeqAmountCalcResult l_result = l_feqBizLogicProvider.calcFeqAmount(
            l_subAccount,
            l_product,
            l_market,
            l_datBaseDate,
            l_ExecDate,
            l_bdUnExedAmount.add(l_bdExedAmountSum).doubleValue(),
            l_dblRxRate,
            l_blnIsBuy,
            false,
            l_orderUnit.isLimitOrder(),
            l_orderUnitRow.getOrderChanel());

        // get計算用注文単価(外国株式取引銘柄, 部店, String, double, double, boolean)
        // [get計算用注文単価()に指定する引数]
        // 　@取引銘柄 : 注文単位.getProduct( ).get取引銘柄( )
        // 　@部店 : 補助口座.get取引店( )
        // 　@注文単価区分 : 
        // 　@　@（注文単位.is指値() == true）の場合、”指値”
        // 　@　@以外、”成行”
        // 　@注文単価 : 注文単位.市場から確認済みの注文単価
        // 　@訂正単価 : 注文単位.（W指値）訂正指値
        // 　@is買付 : 注文単位.is買付( )
        log.debug("注文単位.市場から確認済みの注文単価 = " + l_dblConfirmedOrderPrice);
        double l_dblCalcConfirmOrdPrice = this.getUnitPrice(
                l_product.getFeqTradedProduct(), 
                l_subAccount.getWeb3GenBranch(), 
                l_orderUnit.isLimitOrder() 
                    ? WEB3OrderPriceDivDef.LIMIT_PRICE 
                    : WEB3OrderPriceDivDef.MARKET_PRICE, 
                l_dblConfirmedOrderPrice, 
                l_orderUnitRow.getWLimitPrice(), 
                l_blnIsBuy);
        log.debug("計算用注文単価（市場から確認済み） = " + l_dblCalcConfirmOrdPrice);
        
        // calc売買代金(double, double)
        // [calc売買代金()に指定する引数]
        // 　@株数 : 注文単位.getConfirmedQuantity( ) - 注文単位.getExecutedQuantity( ) 
        // 　@単価 : 計算用注文単価（市場から確認済み）
        double l_dblWConfirmedQuantity = l_dblConfirmedQuantity - l_dblExecQuantity;
        log.debug("市場から確認済みの数量 = " + l_dblConfirmedQuantity);
        log.debug("約定済数量 = " + l_dblExecQuantity);
        double l_dblConfimedUnExedAmount = l_feqBizLogicProvider.calcExecutionAmount(
                l_dblWConfirmedQuantity, 
                l_dblCalcConfirmOrdPrice);
        BigDecimal l_bdConfimedUnExedAmount = new BigDecimal(l_dblConfimedUnExedAmount);
        l_bdConfimedUnExedAmount = l_bdConfimedUnExedAmount.setScale(l_intScale, BigDecimal.ROUND_HALF_EVEN);
        log.debug("未約定分の売買代金（市場から確認済み） = " + l_bdConfimedUnExedAmount.doubleValue());
        
        // calc外国株式金額(補助口座, 外国株式銘柄, 市場, Date, Date, double, double, boolean, boolean, boolean, String)(
        // [calc外国株式金額()に指定する引数]
        // 　@補助口座 : get補助口座( )
        // 　@外国株式銘柄 : getProduct( )
        // 　@市場 : get市場( )
        // 　@基準日 : 基準日
        // 　@約定日 : パラメータ．約定日
        // 　@売買代金（外貨） : 未約定分の売買代金（市場から確認済み）＋約定済の売買代金合計値（SUM）
        // 　@為替レート : get適用為替レートより取得した適用為替レート 
        // 　@is買付 : is買付( )
        // 　@is約定計算 : false
        // 　@is指値 : 注文単位.is指値( )
        // 　@注文チャネル : 注文単位.初回注文の注文チャネル
        WEB3FeqAmountCalcResult l_confirmedResult = l_feqBizLogicProvider.calcFeqAmount(
            l_subAccount, 
            l_product, 
            l_market, 
            l_datBaseDate, 
            l_ExecDate, 
            l_bdConfimedUnExedAmount.add(l_bdExedAmountSum).doubleValue(), 
            l_dblRxRate, 
            l_blnIsBuy, 
            false, 
            l_orderUnit.isLimitOrder(), 
            l_orderUnitRow.getOrderChanel());
        
        // 1.16. doUpdateQuery(arg0 : String, arg1 : PrimaryKey, arg2 : Map)
        // get受渡代金
        l_dblNetAmount = l_result.getNetAmount();
        // get受渡代金（外貨）
        l_dblNetAmountFc = l_result.getNetAmountFc();
        // get受渡代金（市場から確認済み）
        l_dblConfirmedNetAmount = l_confirmedResult.getNetAmount();
        // get受渡代金（外貨）（市場から確認済み）
        l_dblConfirmedNetAmountFc = l_confirmedResult.getNetAmountFc();

        FeqOrderUnitParams l_orderUnitParams = new FeqOrderUnitParams(l_orderUnitRow);
        // 概算受渡代金
        l_orderUnitParams.setEstimatedPrice(l_dblNetAmount);
        // 概算受渡代金（外貨）
        l_orderUnitParams.setFEstimatedPrice(l_dblNetAmountFc);
        // 市場から確認済みの概算受渡代金
        l_orderUnitParams.setConfirmedEstimatedPrice(l_dblConfirmedNetAmount);
        // 市場から確認済みの概算受渡代金（外貨）
        l_orderUnitParams.setConfirmedFEstimatedPrice(l_dblConfirmedNetAmountFc);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_orderUnitParams);

            // 注文履歴（※注文履歴最終通番の履歴）を取得する。
            // 注文履歴最終通番を取得する。
            // 注文履歴Rowを取得する。
            String l_strWhere = " order_unit_id = ?  and order_action_serial_no = ? ";
            Object[] l_objVars = {
                new Long(l_orderUnit.getOrderUnitId()),
                new Integer(l_orderUnitRow.getLastOrderActionSerialNo())};
            List l_lisOrderActions = l_queryProcessor.doFindAllQuery(
                FeqOrderActionRow.TYPE,
                l_strWhere,
                l_objVars);

            if (l_lisOrderActions == null || l_lisOrderActions.isEmpty())
            {
                log.debug("該当する注文単位ID、注文履歴最終通番データがありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            if (l_lisOrderActions.size() > 1)
            {
                log.debug("該当する注文単位ID、注文履歴最終通番データは不整合ある。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            FeqOrderActionRow l_orderActionRow = (FeqOrderActionRow) l_lisOrderActions.get(0);
            FeqOrderActionParams l_orderActionParams = new FeqOrderActionParams(l_orderActionRow);
            // 概算受渡代金
            l_orderActionParams.setEstimatedPrice(l_dblNetAmount);
            // 概算受渡代金（外貨）
            l_orderActionParams.setFEstimatedPrice(l_dblNetAmountFc);
            // 市場から確認済みの概算受渡代金
            l_orderActionParams.setConfirmedEstimatedPrice(l_dblConfirmedNetAmount);
            // 市場から確認済みの概算受渡代金（外貨）
            l_orderActionParams.setConfirmedFEstimatedPrice(l_dblConfirmedNetAmountFc);
            l_queryProcessor.doUpdateQuery(l_orderActionParams);
        }
        catch (DataException l_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate注文訂正可能状態)<BR>
     * 訂正が可能な注文状態であるかどうかをチェックする。<BR>
     * <BR>
     * 外国株式発注審査個別チェック.validate注文訂正可能状態()に<BR>
     * 委譲（deligate）する。<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 注文ID<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4295FF7A02BE
     */
    public void validateOrderChangePossibleStatus(long l_lngOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderChangePossibleStatus(long)";
        log.entering(STR_METHOD_NAME);

        WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3FeqTypeOrderManagerReusableValidations)
            WEB3FeqTypeOrderManagerReusableValidations.getInstance();
        l_orderMgrResVal.validateOrderChangePossibleStatus(l_lngOrderId);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate注文取消可能状態)<BR>
     * 取消が可能な注文状態であるかどうかをチェックする。<BR>
     * <BR>
     * 外国株式発注審査個別チェック.validate注文取消可能状態()に<BR>
     * 委譲（deligate）する。<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 注文ID<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42A7A8850091
     */
    public void validateOrderCancelPossibleStatus(long l_lngOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderCancelPossibleStatus(long)";
        log.entering(STR_METHOD_NAME);

        WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3FeqTypeOrderManagerReusableValidations)
            WEB3FeqTypeOrderManagerReusableValidations.getInstance();
        l_orderMgrResVal.validateOrderCancelPossibleStatus(l_lngOrderId);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (is出来るまで注文単位)<BR>
     * 引数の注文単位が出来るまで注文かどうかを判定する。<BR>
     * <BR>
     * 引数.注文単位.初回注文の注文単位ID != null の場合は、true<BR>
     * 引数.注文単位.初回注文の注文単位ID == null の場合は、false<BR>
     * <BR>
     * を返却する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     *
     * @@return boolean
     * @@roseuid 42961128006C
     */
    public boolean isCarriedOrderUnit(FeqOrderUnit l_orderUnit)
    {
        final String STR_METHOD_NAME = "isCarriedOrderUnit(FeqOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値がNULL");
        }

        FeqOrderUnitRow l_row = (FeqOrderUnitRow)l_orderUnit.getDataSourceObject();
        boolean l_blnIsNull = l_row.getFirstOrderUnitIdIsNull();

        log.exiting(STR_METHOD_NAME);
        return (!l_blnIsNull) ? true : false;
    }

    /**
     * (get初回注文の注文単位)<BR>
     * 初回注文の注文単位オブジェクトを取得する。<BR>
     * <BR>
     * １）引数.注文単位.初回注文の注文単位ID == （null or 0） の場合<BR>
     * <BR>
     *    引数.注文単位を返却する。<BR>
     * <BR>
     * ２）引数.注文単位.初回注文の注文単位ID != （null or 0） の場合<BR>
     * <BR>
     * ２－１）外国株式注文マネージャ.getOrderUnit()をコールする。<BR>
     * <BR>
     *    [引数]<BR>
     *    注文単位ID： 引数.注文単位.初回注文の注文単位ID<BR>
     * <BR>
     * ２－２）取得した注文単位を返却する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     *
     * @@return FeqOrderUnit
     * @@roseuid 429612C50176
     */
    public FeqOrderUnit getFirstOrderUnit(FeqOrderUnit l_orderUnit)
    {
        final String STR_METHOD_NAME = "getFirstOrderUnit(FeqOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値がNULL");
        }

        FeqOrderUnitRow l_row = (FeqOrderUnitRow)l_orderUnit.getDataSourceObject();
        long l_lngFirstOrderUnitId = l_row.getFirstOrderUnitId();
        boolean l_blnIsNull = l_row.getFirstOrderUnitIdIsNull();
        // １）引数.注文単位.初回注文の注文単位ID == （null or 0） の場合
        if (l_blnIsNull || l_lngFirstOrderUnitId == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return l_orderUnit;
        }
        // ２）引数.注文単位.初回注文の注文単位ID != （null or 0） の場合
        else
        {
            FeqOrderUnit l_ordUnit = null;
            try
            {
                // ２－１）外国株式注文マネージャ.getOrderUnit()をコールする。
                l_ordUnit = (FeqOrderUnit)this.getOrderUnit(l_lngFirstOrderUnitId);
            }
            catch (NotFoundException l_ex)
            {
                log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            if (l_ordUnit == null)
            {
                log.debug(STR_METHOD_NAME + "該当注文単位が存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            log.exiting(STR_METHOD_NAME);
            // ２－２）取得した注文単位を返却する。
            return l_ordUnit;
        }
    }

    /**
     * (get執行条件)<BR>
     * 引数の執行条件より、WEB3の執行条件を取得し返却する。<BR>
     * <BR>
     * １）引数の執行条件（SONAR）＝”無条件” の場合<BR>
     * <BR>
     *    FeqExecutionConditionType.NONE（条件なし）を返す。<BR>
     * <BR>
     * ２）引数の執行条件（SONAR）＝”寄付” の場合<BR>
     * <BR>
     *    FeqExecutionConditionType.AT_MARKET_OPEN（寄り）を返す。<BR>
     * <BR>
     * ３）引数の執行条件（SONAR）＝”引け” の場合<BR>
     * <BR>
     *    FeqExecutionConditionType.AT_MARKET_CLOSE（引け）を返す。<BR>
     * <BR>
     * ４）引数の執行条件（SONAR）＝”出来ずば引成(不成)” の場合<BR>
     * <BR>
     *    FeqExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED<BR>
     * （不出来引け成行）を返す。<BR>
     * <BR>
     * ５）引数の執行条件が上記以外の場合<BR>
     * <BR>
     *    例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00019<BR>
     * @@param l_strExecutionConditionType - (執行条件)<BR>
     * SONARの執行条件<BR>
     *
     * @@return com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType
     * @@throws WEB3BaseException
     * @@roseuid 4296E5D801E8
     */
    public FeqExecutionConditionType getExecutionCondition(
        String l_strExecutionConditionType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExecutionCondition(String)";
        log.entering(STR_METHOD_NAME);

        FeqExecutionConditionType l_feqExecCondTypeReturn;
        // １）引数の執行条件（SONAR）＝”無条件” の場合
        if (WEB3SonarExecutionConditionDef.UNCONDITIONDNESS.equals(l_strExecutionConditionType))
        {
            l_feqExecCondTypeReturn = FeqExecutionConditionType.NONE;
        }
        // ２）引数の執行条件（SONAR）＝”寄付” の場合
        else if(WEB3SonarExecutionConditionDef.AT_MARKET_OPEN.equals(l_strExecutionConditionType))
        {
            l_feqExecCondTypeReturn = FeqExecutionConditionType.AT_MARKET_OPEN;
        }
        // ３）引数の執行条件（SONAR）＝”引け” の場合
        else if(WEB3SonarExecutionConditionDef.AT_MARKET_CLOSE.equals(l_strExecutionConditionType))
        {
            l_feqExecCondTypeReturn = FeqExecutionConditionType.AT_MARKET_CLOSE;
        }
        // ４）引数の執行条件（SONAR）＝”出来ずば引成(不成)” の場合
        else if(WEB3SonarExecutionConditionDef.NO_EXECUTED_MARKET_ORDER.equals(l_strExecutionConditionType))
        {
            l_feqExecCondTypeReturn = FeqExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED;
        }
        // ５）引数の執行条件が上記以外の場合
        else
        {
            log.debug(STR_METHOD_NAME + "引数の執行条件が上記以外の場合。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00019,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.debug(STR_METHOD_NAME + ".get執行条件 : " + l_feqExecCondTypeReturn.stringValue());
        log.exiting(STR_METHOD_NAME);
        return l_feqExecCondTypeReturn;
    }

    /**
     * (get執行条件（SONAR）)<BR>
     * 引数の執行条件より、SONARの執行条件を取得し返却する。<BR>
     * <BR>
     * １）引数の執行条件＝FeqExecutionConditionType.NONE（条件なし）<BR>
     *   の場合<BR>
     * <BR>
     *    ”無条件”を返す。<BR>
     * <BR>
     * ２）引数の執行条件＝FeqExecutionConditionType.AT_MARKET_OPEN<BR>
     *  （寄り） の場合<BR>
     * <BR>
     *    ”寄付”を返す。<BR>
     * <BR>
     * ３）引数の執行条件＝FeqExecutionConditionType.AT_MARKET_CLOSE<BR>
     *  （引け） の場合
     * <BR>
     *    ”引け”を返す。<BR>
     * <BR>
     * ４）引数の執行条件＝EqTypeExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED<BR>
     *  （不出来引け成行） の場合<BR>
     * <BR>
     *    ”出来ずば引成(不成)”を返す。<BR>
     * <BR>
     * ５）引数の執行条件が上記以外の場合<BR>
     * <BR>
     *    例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00019<BR>
     * @@param l_strExecutionConditionType - (執行条件)<BR>
     * WEB3の執行条件<BR>
     *
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 4296E826011D
     */
    public String getExecutionConditionTypeSonar(
        String l_strExecutionConditionType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExecutionConditionTypeSonar(String)";
        log.entering(STR_METHOD_NAME);

        String l_strReturn;
        // １）引数の執行条件＝FeqExecutionConditionType.NONE（条件なし）の場合
        if (new Integer(
            FeqExecutionConditionType.NONE.intValue()).toString().equals(
                l_strExecutionConditionType))
        {
            l_strReturn = WEB3SonarExecutionConditionDef.UNCONDITIONDNESS;
        }
        // ２）引数の執行条件＝FeqExecutionConditionType.AT_MARKET_OPEN（寄り） の場合
        else if (new Integer(
            FeqExecutionConditionType.AT_MARKET_OPEN.intValue()).toString().equals(
                l_strExecutionConditionType))
        {
            l_strReturn = WEB3SonarExecutionConditionDef.AT_MARKET_OPEN;
        }
        // ３）引数の執行条件＝FeqExecutionConditionType.AT_MARKET_CLOSE（引け） の場合
        else if (new Integer(
            FeqExecutionConditionType.AT_MARKET_CLOSE.intValue()).toString().equals(
                l_strExecutionConditionType))
        {
            l_strReturn = WEB3SonarExecutionConditionDef.AT_MARKET_CLOSE;
        }
        // ４）引数の執行条件＝EqTypeExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED（不出来引け成行） の場合
        else if (new Integer(
            FeqExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.intValue()).toString().equals(
                l_strExecutionConditionType))
        {
            l_strReturn = WEB3SonarExecutionConditionDef.NO_EXECUTED_MARKET_ORDER;
        }
        // ５）引数の執行条件が上記以外の場合
        else
        {
            log.debug(STR_METHOD_NAME + "引数の執行条件が上記以外の場合。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00019,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.debug(STR_METHOD_NAME + ".get執行条件（SONAR） : " + l_strReturn);
        log.exiting(STR_METHOD_NAME);
        return l_strReturn;
    }

    /**
     * (is内容通知済注文)<BR>
     * 発注済注文であるかを判定する。<BR>
     * 引数の注文単位が以下の条件に当てはまる場合true、<BR>
     * 以外はfalseを返却する。<BR>
     * <BR>
     * [発注済注文の条件]<BR>
     * 注文単位.執行条件 == 注文単位.市場から確認済の執行条件<BR>
     * 注文単位.指値 == 注文単位.市場から確認済の指値<BR>
     * 注文単位.注文数量 == 注文単位.市場から確認済の数量<BR>
     * @@param l_orderUnitRow - (注文単位行)<BR>
     * 注文単位行オブジェクト<BR>
     *
     * @@return boolean
     * @@roseuid 429D62EE009B
     */
    public boolean isNotifyEndOrder(FeqOrderUnitRow l_orderUnitRow)
    {
        final String STR_METHOD_NAME = "isNotifyEndOrder(FeqOrderUnitRow)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnitRow == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値がNULL");
        }

        boolean l_blnReturn =
            l_orderUnitRow.getExecutionConditionType().equals(
                l_orderUnitRow.getConfirmedExecConditionType()) &&
            l_orderUnitRow.getLimitPrice() == l_orderUnitRow.getConfirmedPrice() &&
            l_orderUnitRow.getQuantity() == l_orderUnitRow.getConfirmedQuantity();
        log.debug(STR_METHOD_NAME + ".is内容通知済注文 : " + l_blnReturn);

        log.exiting(STR_METHOD_NAME);
        return l_blnReturn;
    }

    /**
     * (validate顧客銘柄別取引停止)<BR>
     * 顧客銘柄別取引停止チェックを行う。<BR>
     * （* 外国株式発注審査チェック.validate顧客銘柄別取引停止()に委譲する。）<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     *
     * @@param l_lngProductId - (銘柄ID)<BR>
     * 銘柄ID<BR>
     *
     * @@param l_orderType - (注文種別)<BR>
     * 注文種別<BR>
     * @@throws WEB3BaseException
     * @@roseuid 429E879202DD
     */
    public void validateAccountProductTradedStop(
        SubAccount l_subAccount,
        long l_lngProductId,
        OrderTypeEnum l_orderType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateAccountProductTradedStop(SubAccount, long, OrderTypeEnum)";
        log.entering(STR_METHOD_NAME);

        WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3FeqTypeOrderManagerReusableValidations)
            WEB3FeqTypeOrderManagerReusableValidations.getInstance();
        l_orderMgrResVal.validateAccountProductTradedStop(
            l_subAccount,
            l_lngProductId,
            l_orderType);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate取扱可能市場)<BR>
     * 会社部店の取扱可能市場かをチェックする。<BR>
     * （* 外国株式発注審査チェック.validate取扱可能市場( )に委譲する。） <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     * 市場コード<BR>
     * @@throws WEB3BaseException
     * @@roseuid 429EEFE001B1
     */
    public void validateHandlingPossibleMarket(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strMarketCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateHandlingPossibleMarket(String, String, String)";
        log.entering(STR_METHOD_NAME);

        WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3FeqTypeOrderManagerReusableValidations)
            WEB3FeqTypeOrderManagerReusableValidations.getInstance();
        l_orderMgrResVal.validateHandlingPossibleMarket(
            l_strInstitutionCode,
            l_strBranchCode,
            l_strMarketCode);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get注文単位一覧)<BR>
     * （getOrderUnitsのオーバーロード）<BR>
     * 指定条件に一致する注文の注文単位オブジェクトの一覧を返却する。<BR>
     * <BR>
     * １）　@戻り値オブジェクトのインスタンスを生成する。<BR>
     * <BR>
     * ２）　@検索条件を追加する。<BR>
     * 　@２－１）　@パラメータ.検索条件文字列の先頭に、<BR>
     * 　@　@　@　@　@　@"account_id = ? and sub_account_id = ? and <BR>
     * 　@　@　@　@　@　@　@product_type = ?"を付加する。<BR>
     * <BR>
     * 　@２－２）　@パラメータ.検索条件データコンテナの先頭に、<BR>
     * 　@　@　@　@　@　@検索条件文字列先頭に付加したパラメータリストを追加する。<BR>
     * 　@　@　@　@　@　@※パラメータ.補助口座、及びパラメータ.銘柄タイプより設定する。<BR>
     * <BR>
     * ３）　@QueryProcessor.doFindAllQuery( )により、<BR>
     * 注文単位オブジェクトのListを取得する。<BR>
     * <BR>
     * 　@　@　@doFindAllQuery(外国株式注文単位Row.TYPE<BR>
     *                     ２－１）の検索条件文字列,<BR>
     *                     パラメータ.ソート条件,<BR>
     *                     null,<BR>
     *                     ２－２）の検索条件データコンテナ)<BR>
     * <BR>
     * 　@　@　@※該当データなしの場合、nullを返却する。<BR>
     * <BR>
     * ４）　@ArrayListを生成する。<BR>
     * <BR>
     * ５）　@３）の戻り値の要素(=FeqOrderUnitRow)数分以下の処理をLoopする。<BR>
     * 　@　@　@①@　@外国株式注文マネージャ.toOrderUnit((*1)外国株式注文単位Row)<BR>
     * メソッドをコールする。<BR>
     * 　@　@　@②　@①@の戻り値をArrayListに追加する。<BR>
     * <BR>
     * 　@　@　@(*1)外国株式注文単位Row・・・処理対象の要素を<BR>
     * FeqOrderUnitRowにキャストする。<BR>
     * <BR>
     * ６）　@ArrayListを返却する。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_productType - (銘柄タイプ)<BR>
     * 銘柄タイプ<BR>
     * (ProductTypeEnumにて定義)<BR>
     * @@param l_strQueryString - (検索条件文字列)<BR>
     * 検索条件文字列<BR>
     * @@param l_strQueryContainers - (検索条件データコンテナ)<BR>
     * 検索条件データコンテナ<BR>
     * @@param l_strSortCond - (ソート条件)<BR>
     * ソート条件<BR>
     * @@return ArrayList
     * @@throws WEB3BaseException
     * @@roseuid 42A3D70B00F6
     */
    public ArrayList getOrderUnits(
        SubAccount l_subAccount,
        ProductTypeEnum l_productType,
        String l_strQueryString,
        String[] l_strQueryContainers,
        String l_strSortCond) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderUnits(SubAccount, "
            + "ProductTypeEnum, String, String[], String)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値がNULL");
        }

        // １）　@戻り値オブジェクトのインスタンスを生成する。
        ArrayList l_alisReturn = new ArrayList();

        // ２）　@検索条件を追加する。
        //　@２－１）　@パラメータ.検索条件文字列の先頭に、
        //　@　@　@　@　@　@"account_id = ? and sub_account_id = ? and
        //　@　@　@　@　@　@　@product_type = ?"を付加する。
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" account_id = ? ");
        l_sbWhere.append(" and sub_account_id = ? ");
        l_sbWhere.append(" and product_type = ? ");
        if (l_strQueryString != null)
        {
            l_sbWhere.append(l_strQueryString);
        }

        //　@２－２）　@パラメータ.検索条件データコンテナの先頭に、
        // 　@　@　@　@　@　@検索条件文字列先頭に付加したパラメータリストを追加する。
        // 　@　@　@　@　@　@※パラメータ.補助口座、及びパラメータ.銘柄タイプより設定する。
        ArrayList l_alisVars = new ArrayList();
        l_alisVars.add(new Long(l_subAccount.getMainAccount().getAccountId()));
        l_alisVars.add(new Long(l_subAccount.getSubAccountId()));
        l_alisVars.add(l_productType);
        if (l_strQueryContainers != null)
        {
            for (int i = 0; i < l_strQueryContainers.length; i++)
            {
                l_alisVars.add(l_strQueryContainers[i]);
            }
        }

        List l_lisRecords = null;
        try
        {
            //３）　@QueryProcessor.doFindAllQuery( )により、注文単位オブジェクトのListを取得する。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            Object[] l_objVars = new Object[l_alisVars.size()];
            l_alisVars.toArray(l_objVars);
            //　@doFindAllQuery(外国株式注文単位Row.TYPE
            //                  ２－１）の検索条件文字列,
            //                  パラメータ.ソート条件,
            //                  null,
            //                  ２－２）の検索条件データコンテナ)
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                FeqOrderUnitRow.TYPE,
                l_sbWhere.toString(),
                l_strSortCond,
                null,
                l_objVars);
        }
        catch (DataException l_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //　@※該当データなしの場合、nullを返却する。
        if (l_lisRecords == null || l_lisRecords.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        //５）　@３）の戻り値の要素(=FeqOrderUnitRow)数分以下の処理をLoopする。
        for (int i = 0; i < l_lisRecords.size(); i++)
        {
            //　@①@　@外国株式注文マネージャ.toOrderUnit((*1)外国株式注文単位Row)メソッドをコールする。
            //　@(*1)外国株式注文単位Row・・・処理対象の要素をFeqOrderUnitRowにキャストする。
            FeqOrderUnitRow l_orderUnitRow = (FeqOrderUnitRow)l_lisRecords.get(i);
            WEB3FeqOrderUnit l_orderUnit = (WEB3FeqOrderUnit)this.toOrderUnit(l_orderUnitRow);
            //　@②　@①@の戻り値をArrayListに追加する。
            l_alisReturn.add(l_orderUnit);
        }

        log.exiting(STR_METHOD_NAME);
        return l_alisReturn;
    }

    /**
     * (remove繰越元注文単位)<BR>
     * 引数の注文単位オブジェクトのリストから、<BR>
     * 繰越元の注文単位オブジェクトを除去し、<BR>
     * 除去後のリストを返却する。<BR>
     * <BR>
     * １）　@パラメータ.注文単位一覧 == nullの場合、nullを返却して終了する。<BR>
     * <BR>
     * ２）　@除去対象の判定<BR>
     * <BR>
     * 　@以下、パラメータ.注文単位一覧の要素数分のLoop処理。<BR>
     * <BR>
     * 　@[外国株式注文マネージャ.is出来るまで注文単位<BR>
     * 　@(処理対象の注文単位) == falseの場合]<BR>
     * 　@(当日限り注文の場合)<BR>
     * 　@　@　@リストにそのまま残す。<BR>
     * <BR>
     * 　@[外国株式注文マネージャ.is出来るまで注文単位<BR>
     * 　@(処理対象の注文単位) == trueの場合]<BR>
     * 　@(出来るまで注文の場合)<BR>
     * 　@　@[初回注文の場合]<BR>
     * 　@　@(処理対象の注文単位.初回注文の注文単位ID == 0の場合)<BR>
     * 　@　@　@リスト中(パラメータ.注文単位一覧)を検索し、<BR>
     * <BR>
     * 　@　@　@　@　@処理対象の注文単位.注文単位ID ==<BR>
     * 　@　@　@　@　@　@リスト中の注文単位.初回注文の注文単位ID<BR>
     * <BR>
     * 　@　@　@となるデータが存在した場合は、自身を除去対象とする。<BR>
     * 　@　@　@※繰越後の注文が存在する為。<BR>
     *
     * 　@　@[繰越済注文の場合]<BR>
     * 　@　@(処理対象の注文単位.初回注文の注文単位ID != 0の場合)<BR>
     * 　@　@　@リスト中(パラメータ.注文単位一覧)を検索し、<BR>
     * <BR>
     * 　@　@　@　@　@処理対象の注文単位.初回注文の注文単位ID ==<BR>
     * 　@　@　@　@　@　@リスト中の注文単位.初回注文の注文単位ID<BR>
     * <BR>
     * 　@　@　@となるデータが存在した場合は、作成日を比較し、<BR>
     * 　@　@　@最新の注文単位以外を全て除去対象とする。<BR>
     * 　@　@　@※最新の繰越注文のみを表示する為。<BR>
     * <BR>
     * ３）　@リストからの除去対象と判定された繰越元の注文単位オブジェクトを、<BR>
     * 　@　@　@注文単位一覧から全て除去する。<BR>
     * 　@　@　@※パラメータ.注文単位一覧の並び順は顧客指定<BR>
     * 　@　@　@のソート条件によるため、<BR>
     * 　@　@　@　@除去は最後に纏めて行う必要がある。<BR>
     * <BR>
     * ４）　@除去済の注文単位一覧を返却する。<BR>
     * 　@※注文単位一覧の要素数が0になった場合はnullを返却する。<BR>
     * @@param l_orderUnits - (注文単位一覧)<BR>
     * 外国株式注文単位オブジェクトの配列<BR>
     * @@return webbroker3.feq.WEB3FeqOrderUnit[]
     * @@roseuid 42A3DB210106
     */
    public WEB3FeqOrderUnit[] removeCarryOverOriginOrder(WEB3FeqOrderUnit[] l_orderUnits)
    {
        final String STR_METHOD_NAME = "removeCarryOverOriginOrder(WEB3FeqOrderUnit[])";
        log.entering(STR_METHOD_NAME);

        // １）　@パラメータ.注文単位一覧 == nullの場合、nullを返却して終了する。
        if (l_orderUnits == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        int l_intCarryoverOrderUnitCount = 0;
        // is出来るまで注文単位の判定
        boolean[] l_blnIsCarriedOrderUnit = new boolean[l_orderUnits.length];

        // ２）　@除去対象の判定
        // 以下、パラメータ.注文単位一覧の要素数分のLoop処理。
        for (int i = 0; i < l_orderUnits.length; i++)
        {
            l_blnIsCarriedOrderUnit[i] = true;

            // [外国株式注文マネージャ.is出来るまで注文単位(処理対象の注文単位) == falseの場合]
            //  (当日限り注文の場合)
            if (!this.isCarriedOrderUnit(l_orderUnits[i]))
            {
                continue;
            }

            FeqOrderUnitRow l_orderUnitRow = (FeqOrderUnitRow)l_orderUnits[i].getDataSourceObject();
            // [外国株式注文マネージャ.is出来るまで注文単位(処理対象の注文単位) == trueの場合]
            //   (出来るまで注文の場合)
            // [初回注文の場合]
            if (l_orderUnitRow.getFirstOrderUnitId() == 0L)
            {
                for (int j = 0; j < l_orderUnits.length; j++)
                {
                    // 処理対象の注文単位.注文単位ID == リスト中の注文単位.初回注文の注文単位ID
                    FeqOrderUnitRow l_orderUnitRowj =
                        (FeqOrderUnitRow)l_orderUnits[j].getDataSourceObject();
                    if (l_orderUnitRow.getOrderUnitId() == l_orderUnitRowj.getFirstOrderUnitId())
                    {
                        l_blnIsCarriedOrderUnit[i] = false;
                        l_intCarryoverOrderUnitCount++;
                        break;
                    }
                }
            }
            // [繰越済注文の場合]
            else
            {
                for (int j = 0; j < l_orderUnits.length; j++)
                {
                    // 処理対象の注文単位.初回注文の注文単位ID == リスト中の注文単位.初回注文の注文単位ID
                    FeqOrderUnitRow l_orderUnitRowj = (FeqOrderUnitRow)l_orderUnits[j].getDataSourceObject();

                    if (l_orderUnitRow.getFirstOrderUnitId() == l_orderUnitRowj.getFirstOrderUnitId())
                    {
                        // 作成日を比較し、最新の注文単位以外を全て除去対象とする。
                        if (WEB3DateUtility.compareToSecond(
                            l_orderUnitRowj.getCreatedTimestamp(),
                            l_orderUnitRow.getCreatedTimestamp()) > 0)
                        {
                            l_blnIsCarriedOrderUnit[i] = false;
                            l_intCarryoverOrderUnitCount++;
                            break;
                        }
                    }
                }
            }
        }

        //　@※注文単位一覧の要素数が0になった場合はnullを返却する。
        WEB3FeqOrderUnit[] l_orderUnitReturn = null;
        // ３）　@リストからの除去対象と判定された繰越元の注文単位オブジェクトを、注文単位一覧から全て除去する。
        int l_intLength = l_orderUnits.length - l_intCarryoverOrderUnitCount;
        if (l_intLength > 0)
        {
            l_orderUnitReturn =
                new WEB3FeqOrderUnit[l_intLength];
            int j = 0;
            for (int i = 0; i < l_orderUnits.length; i++)
            {
                if (l_blnIsCarriedOrderUnit[i])
                {
                    l_orderUnitReturn[j] = l_orderUnits[i];
                    j++;
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_orderUnitReturn;
    }

    /**
     * (is繰越注文単位)<BR>
     * 注文繰越で登録された注文かどうかを判定する。<BR>
     * 「繰越注文」の場合はtrueを、「繰越注文」ではない場合はfalseを、<BR>
     * それぞれ返却する。<BR>
     * <BR>
     * １）　@引数の注文単位.初回注文の注文単位ID > 0の場合は、trueを返す。<BR>
     * 　@　@　@引数の注文単位.初回注文の注文単位ID＝<BR>
     * 　@　@　@（null or 0）の場合は、falseを返す。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 外国株式注文単位オブジェクト<BR>
     * @@return boolean
     * @@roseuid 42A414E600A8
     */
    public boolean isCarryOverOrderUnit(WEB3FeqOrderUnit l_orderUnit)
    {
        final String STR_METHOD_NAME = "isCarryOverOrderUnit(WEB3FeqOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値がNULL");
        }

        FeqOrderUnitRow l_row = (FeqOrderUnitRow)l_orderUnit.getDataSourceObject();
        long l_lngFirstOrderUnitId = l_row.getFirstOrderUnitId();

        log.exiting(STR_METHOD_NAME);
        return (l_lngFirstOrderUnitId > 0) ? true : false;
    }

    /**
     * (get出来終了対象注文単位)<BR>
     * 出来終了対象となる注文単位の一覧を取得する。<BR>
     * <BR>
     * １）　@DB検索<BR>
     * 　@以下の条件にて外株注文単位テーブルを検索する。<BR>
     * 　@検索条件に対応するパラメータ(口座IDや証券会社コードなど)が<BR>
     * 　@nullの場合、その条件は検索条件から除外する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@　@口座ID = パラメータ.口座ID And<BR>
     * 　@　@証券会社コード = パラメータ.証券会社コード And<BR>
     * 　@　@市場ID = パラメータ.市場コードに該当する市場ID And<BR>
     * 　@　@銘柄タイプ = ProductTypeEnum.外国株式 And<BR>
     * 　@　@発注日 = パラメータ.発注日 And<BR>
     * 　@　@(注文有効状態 = "オープン" or<BR>
     * 　@　@　@(注文有効状態 = "クローズ" And 約定数量 != (null or 0))) And<BR>
     * 　@　@出来終了処理日時 is null<BR>
     * <BR>
     * 　@該当データなしの場合、nullを返却する。<BR>
     * <BR>
     * ２）　@検索結果を返却する。<BR>
     * @@param l_lngAccountId - (口座ID)<BR>
     * 口座ID<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     * 市場コード<BR>
     * @@param l_datBizDate - (発注日)<BR>
     * 発注日<BR>
     * @@return webbroker3.feq.WEB3FeqOrderUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 42B6596401F6
     */
    public WEB3FeqOrderUnit[] getOrderExecEndObjectOrderUnit(
        Long l_lngAccountId,
        String l_strInstitutionCode,
        String l_strMarketCode,
        Date l_datBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderExecEndObjectOrderUnit(long, String, String, Date)";
        log.entering(STR_METHOD_NAME);

        // １）　@DB検索
        StringBuffer l_sbWhere = new StringBuffer();
        ArrayList l_alisObjs = new ArrayList();
        if (l_lngAccountId != null)
        {
            l_sbWhere.append(" account_id = ? ");
            l_alisObjs.add(l_lngAccountId);
        }

        if (l_strInstitutionCode != null)
        {
            if (l_sbWhere.length() != 0)
            {
                l_sbWhere.append(" and ");
            }
            l_sbWhere.append(" institution_code = ? ");
            l_alisObjs.add(l_strInstitutionCode);
        }

        if (l_strMarketCode != null)
        {
            if (l_strInstitutionCode == null)
            {
                log.debug(" パラメータ値証券会社コードがNULL ");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "パラメータ値証券会社コードがNULL");
            }
            MarketRow l_marketRow = null;
            try
            {
                // パラメータ.市場コードに該当する市場ID取得する
                l_marketRow = MarketDao.findRowByInstitutionCodeMarketCode(
                    l_strInstitutionCode, l_strMarketCode);
            }
            catch (DataFindException l_ex)
            {
                log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            if (l_marketRow == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            if (l_sbWhere.length() != 0)
            {
                l_sbWhere.append(" and ");
            }
            l_sbWhere.append(" market_id = ? ");
            long l_lngMarketId = l_marketRow.getMarketId();
            l_alisObjs.add(new Long(l_lngMarketId));
        }

        if (l_sbWhere.length() != 0)
        {
            l_sbWhere.append(" and ");
        }
        l_sbWhere.append(" product_type = ? ");
        l_alisObjs.add(ProductTypeEnum.FOREIGN_EQUITY);

        if (l_datBizDate != null)
        {
            l_sbWhere.append(" and biz_date = ? ");
            String l_datBizDates = WEB3DateUtility.formatDate(l_datBizDate,"yyyyMMdd");
            l_alisObjs.add(l_datBizDates);
        }

        l_sbWhere.append(" and (order_open_status = ? ");
        l_sbWhere.append(" or (order_open_status = ? ");
        l_sbWhere.append(" and (quantity != ? and quantity is not NULL))) ");
        l_sbWhere.append(" and exec_end_timestamp is NULL ");
        l_alisObjs.add(OrderOpenStatusEnum.OPEN);
        l_alisObjs.add(OrderOpenStatusEnum.CLOSED);
        l_alisObjs.add(new Integer(0));

        Object[] l_objVars = new Object[l_alisObjs.size()];
        l_alisObjs.toArray(l_objVars);
        List l_lisRecords = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                FeqOrderUnitRow.TYPE,
                l_sbWhere.toString(),
                null,
                l_objVars);
        }
        catch (DataException l_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //　@※該当データなしの場合、nullを返却する。
        if (l_lisRecords == null || l_lisRecords.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        // ２）　@検索結果を返却する。
        int l_intCnt = l_lisRecords.size();
        WEB3FeqOrderUnit[] l_returns = new WEB3FeqOrderUnit[l_intCnt];
        ArrayList l_alisReturn = new ArrayList();
        for (int i = 0; i < l_intCnt; i++)
        {
            FeqOrderUnitRow l_orderUnitRow = (FeqOrderUnitRow)l_lisRecords.get(i);
            WEB3FeqOrderUnit l_orderUnit = (WEB3FeqOrderUnit)this.toOrderUnit(l_orderUnitRow);
            l_alisReturn.add(l_orderUnit);
        }
        l_alisReturn.toArray(l_returns);

        log.exiting(STR_METHOD_NAME);
        return l_returns;
    }

    /**
     * (get繰越対象注文単位)<BR>
     * 出来終了対象となる注文単位の一覧を取得する。<BR>
     * <BR>
     * １）　@DB検索<BR>
     * 　@以下の条件にて外株注文単位テーブルを検索する。<BR>
     * 　@検索条件に対応するパラメータ(口座IDや証券会社コードなど)が<BR>
     * 　@nullの場合、その条件は検索条件から除外する。<BR>
     * 　@※検索結果は、受注日時 昇順でソートすること。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@　@口座ID = パラメータ.口座ID And<BR>
     * 　@　@証券会社コード = パラメータ.証券会社コード And<BR>
     * 　@　@銘柄タイプ = ProductTypeEnum.外国株式 And<BR>
     * 　@　@発注日 = 業務日付(*1) And<BR>
     * 　@　@注文有効状態 = "オープン" And<BR>
     * 　@　@注文失効日付 > 業務日付 And<BR>
     * 　@　@出来終了日時 is not null<BR>
     * <BR>
     * 　@該当データなしの場合、nullを返却する。<BR>
     * <BR>
     * ２）　@検索結果を返却する。<BR>
     * <BR>
     * (*1)GtlUtils.getTradingSystem().getBizDate()<BR>
     * @@param l_lngAccountId - (口座ID)<BR>
     * 口座ID<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@return webbroker3.feq.WEB3FeqOrderUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 42B8B0A30114
     */
    public WEB3FeqOrderUnit[] getCarryOverOrderUnit(
        Long l_lngAccountId,
        String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCarryOverOrderUnit(long, String)";
        log.entering(STR_METHOD_NAME);

        // １）　@DB検索
        StringBuffer l_sbWhere = new StringBuffer();
        ArrayList l_alisObjs = new ArrayList();
        if (l_lngAccountId != null)
        {
            l_sbWhere.append(" account_id = ? ");
            l_alisObjs.add(l_lngAccountId);
        }

        if (l_strInstitutionCode != null)
        {
            if (l_sbWhere.length() != 0)
            {
                l_sbWhere.append(" and ");
            }
            l_sbWhere.append(" institution_code = ? ");
            l_alisObjs.add(l_strInstitutionCode);
        }
        if (l_sbWhere.length() != 0)
        {

            l_sbWhere.append(" and product_type = ? ");
        }
        else
        {
            l_sbWhere.append("product_type = ? ");
        }
        
        l_sbWhere.append(" and biz_date = ? ");
        l_sbWhere.append(" and order_open_status = ? ");
        l_sbWhere.append(" and expiration_date > ? ");
        l_sbWhere.append(" and exec_end_timestamp is not NULL ");
        
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();

        l_alisObjs.add(ProductTypeEnum.FOREIGN_EQUITY);
        String l_strBizDate = WEB3DateUtility.formatDate(l_datBizDate,"yyyyMMdd");
        l_alisObjs.add(l_strBizDate);
        l_alisObjs.add(OrderOpenStatusEnum.OPEN);
        l_alisObjs.add(l_datBizDate);

        Object[] l_objVars = new Object[l_alisObjs.size()];
        l_alisObjs.toArray(l_objVars);
        
        List l_lisRecords = null;
        
        //検索結果は、受注日時 昇順でソートすること。
        String l_strOrderBy = "received_date_time";
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                FeqOrderUnitRow.TYPE,
                l_sbWhere.toString(),
                l_strOrderBy,
                null,
                l_objVars);
        }
        catch (DataException l_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //　@※該当データなしの場合、nullを返却する。
        if (l_lisRecords == null || l_lisRecords.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        // ２）　@検索結果を返却する。
        int l_intCnt = l_lisRecords.size();
        WEB3FeqOrderUnit[] l_returns = new WEB3FeqOrderUnit[l_intCnt];
        ArrayList l_alisReturn = new ArrayList();
        for (int i = 0; i < l_intCnt; i++)
        {
            FeqOrderUnitRow l_orderUnitRow = (FeqOrderUnitRow)l_lisRecords.get(i);
            WEB3FeqOrderUnit l_orderUnit = (WEB3FeqOrderUnit)toOrderUnit(l_orderUnitRow);
            l_alisReturn.add(l_orderUnit);
        }
        l_alisReturn.toArray(l_returns);

        log.exiting(STR_METHOD_NAME);
        return l_returns;
    }
    /**
     * (validate訂正可能市場 )<BR>
     * 訂正が可能な市場であるかどうかをチェックする。 <BR>
     * <BR>
     * 外国株式発注審査個別チェック.validate訂正可能市場()に委譲（deligate）する。<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * @@throws WEB3BaseException 
     */
    public void validateChangePossMarket(long l_lngOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateChangePossMarket(long l_lngOrderId)";
        log.entering(STR_METHOD_NAME);

        WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3FeqTypeOrderManagerReusableValidations)
            WEB3FeqTypeOrderManagerReusableValidations.getInstance();
        l_orderMgrResVal.validateChangePossMarket(l_lngOrderId);

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (訂正／取消エラー処理実行)<BR>
     * 該当注文が訂正／取消中で且つ全出来の場合、<BR>
     * 配当注文を訂正／取消エラーにする。
     * <BR>
     * シーケンス図<BR>
     * 「（外株注文）訂正／取消エラー処理実行」参照。<BR>
     * @@param l_lngOrderUnitId - (注文単位ID)<BR>
     * @@throws WEB3BaseException
     */
    public void executeChangeCancelOrderRejected(long l_lngOrderUnitId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "executeChangeCancelOrderRejected(long)";
        log.entering(STR_METHOD_NAME);
        
        FeqOrderUnit l_orderUnit = null;
        try
        {
            l_orderUnit = (FeqOrderUnit)this.getOrderUnit(l_lngOrderUnitId);
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        OrderStatusEnum l_orderStatus = l_orderUnit.getOrderStatus();
        if (OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderStatus)
            || OrderStatusEnum.MODIFYING.equals(l_orderStatus)
            || OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderStatus)
            || OrderStatusEnum.CANCELLING.equals(l_orderStatus))
        {
            if (l_orderUnit.isFullyExecuted())
            {
                long l_lngOrderId = l_orderUnit.getOrderId();
                MarketResponseMessage l_marketResponseMessage;
                if (OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderStatus)
                    || OrderStatusEnum.MODIFYING.equals(l_orderStatus))
                {
                    l_marketResponseMessage =
                        new DefaultChangeOrderRejectedMarketResponseMessage(l_lngOrderId);
                }
                else
                {
                    l_marketResponseMessage =
                        new DefaultCancelOrderRejectedMarketResponseMessage(l_lngOrderId);
                }
                FeqOrderManagerPersistenceEventInterceptor l_currentInterceptor =
                    this.getThreadLocalPersistenceEventInterceptor();
                FeqOrderManagerPersistenceEventInterceptor l_updateInterceptor = 
                    new WEB3FeqOrderAcceptUpdateInterceptor(l_marketResponseMessage);
                this.setThreadLocalPersistenceEventInterceptor(l_updateInterceptor);
                TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
                FeqMarketResponseReceiverCallbackService l_marketResponseReceiverCallbackService =
                    (FeqMarketResponseReceiverCallbackService)
                        l_tradingModule.getMarketAdapter().getMarketResponseReceiverCallbackService();
                l_marketResponseReceiverCallbackService.process(l_marketResponseMessage);
                this.setThreadLocalPersistenceEventInterceptor(l_currentInterceptor);
            }
        }
    }
      
    /* (non-Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager#toOrderUnit(com.fitechlabs.dbind.Row)
     */
    public OrderUnit toOrderUnit(Row l_row)
    {
        if(!(l_row instanceof FeqOrderUnitRow))
        {
            throw new IllegalArgumentException("Row is not of FeqOrderUnitRow type");
        }
        else
        {
            FeqOrderUnitRow l_our = (FeqOrderUnitRow)l_row;
            return new WEB3FeqOrderUnit(l_our);
        }
    }
    
    /* (non-Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager#toOrderAction(com.fitechlabs.dbind.Row)
     */
    public OrderAction toOrderAction(Row l_row)
    {
        if(!(l_row instanceof FeqOrderActionRow))
        {
            throw new IllegalArgumentException("Row is not of FeqOrderActionRow type");
        }
        else
        {
            FeqOrderActionRow l_our = (FeqOrderActionRow)l_row;
            return new WEB3FeqOrderAction(l_our);
        }
    }

    /* (non-Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager#toOrderExecution(com.fitechlabs.dbind.Row)
     */
    public OrderExecution toOrderExecution(Row l_row)
    {
        if(!(l_row instanceof FeqOrderExecutionRow))
        {
            throw new IllegalArgumentException("Row is not of FeqOrderExecutionRow type");
        }
        else
        {
            FeqOrderExecutionRow l_our = (FeqOrderExecutionRow)l_row;
            return new WEB3FeqOrderExecution(l_our);
        }
    }
    
    public Order getOrder(long l_lngOrderId)
        throws NotFoundException
    {
        try
        {
            FeqOrderRow l_orderRow = FeqOrderDao.findRowByPk(l_lngOrderId);
            if (l_orderRow == null)
            {
                throw new NotFoundException("FeqOrder not found with OrderId:" + l_lngOrderId);
            }
            return this.toOrder(l_orderRow);
        }
        catch (DataFindException dfe)
        {
            throw new NotFoundException("FeqOrder not found with OrderId:" + l_lngOrderId);    
        }
        catch (DataException l_dex)
        {
            String l_strMsg = "Error while during getOrder for FeqOrder with orderId:" + l_lngOrderId;           
            throw new RuntimeSystemException(l_strMsg, l_dex);            
        }        
    }

    public OrderUnit getOrderUnit(long l_lngOrderUnitId)
        throws NotFoundException
    {
        try
        {
            FeqOrderUnitRow l_orderUnitRow = FeqOrderUnitDao.findRowByPk(l_lngOrderUnitId);
            if (l_orderUnitRow == null)
            {
                throw new NotFoundException("FeqOrderUnit not found with OrderUnitId:" + l_lngOrderUnitId);
            }
            return this.toOrderUnit(l_orderUnitRow);
        }
        catch (DataFindException dfe)
        {
            throw new NotFoundException("FeqOrderUnit not found with OrderUnitId:" + l_lngOrderUnitId);    
        }
        catch (DataException l_dex)
        {
            String l_strMsg = "Error during getOrderUnit for FeqOrderUnit with orderUnitId:" + l_lngOrderUnitId;           
            throw new RuntimeSystemException(l_strMsg, l_dex);            
        }
    }
    
    public OrderAction getOrderAction(long l_lngOrderActionId)
        throws NotFoundException
    {        
        try
        {
            FeqOrderActionRow l_orderActinRow = FeqOrderActionDao.findRowByPk(l_lngOrderActionId);
            if (l_orderActinRow == null)
            {
                throw new NotFoundException("FeqOrderAction not found with OrderActionId:" + l_lngOrderActionId);
            }
            return this.toOrderAction(l_orderActinRow);
        }
        catch (DataFindException dfe)
        {
            throw new NotFoundException("FeqOrderAction not found with OrderActionId:" + l_lngOrderActionId);    
        }
        catch (DataException l_dex)
        {
            String l_strMsg = "Error while during getOrderAction for FeqOrderAction " +
                    "with orderActionId:" + l_lngOrderActionId;           
            throw new RuntimeException(l_strMsg, l_dex);            
        }
    }
    
    public OrderExecution getOrderExecution(long l_lngOrderExecutionId)
        throws NotFoundException
    {        
        try
        {
            FeqOrderExecutionRow l_orderExecutionRow = 
                FeqOrderExecutionDao.findRowByPk(l_lngOrderExecutionId);
            if (l_orderExecutionRow == null)
            {
                throw new NotFoundException("FeqOrderExecution not found " +
                    "with OrderExecutionId:" + l_lngOrderExecutionId);    
            }
            return this.toOrderExecution(l_orderExecutionRow);
        }
        catch (DataFindException dfe)
        {
            throw new NotFoundException("FeqOrderExecution not found " +
                    "with OrderExecutionId:" + l_lngOrderExecutionId);    
        }
        catch (DataException l_dex)
        {
            String l_strMsg = "Error while during getOrderExecution for FeqOrderExecution " +
                    "with orderExecutionId:" + l_lngOrderExecutionId;         
            throw new RuntimeException(l_strMsg, l_dex);            
        }
    }
    
    /**
     * (注文認証取消処理実行)<BR>
     * 該当注文が新規注文発注済もしくは訂正／取消済の場合、<BR> 
     * 該当注文を注文認証取消させる。<BR>
     * <BR>
     * シーケンス図<BR> 
     * 「（外株注文）注文認証取消処理実行」参照。<BR>
     * @@param  - (注文単位)<BR>
     * 注文単位オブジェクト
     */
    public void executeOrderAcceptCancel(WEB3FeqOrderUnit l_orderUnit)
    {
        final String STR_METHOD_NAME = "executeChangeCancelAcceptCancel(WEB3FeqOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        // 1.注文認証取消処理実行(外国株式注文単位)
        // 1.1 getOrderStatus( )
        OrderStatusEnum l_orderStatus = l_orderUnit.getOrderStatus();
        
        //注文が注文受付済もしくは訂正／取消済の場合（注文状態 == "発注済(新規注文)"
        //又は注文状態 == "発注済(訂正注文)"又は"発注済(取消注文)"）
        if (OrderStatusEnum.ORDERED.equals(l_orderStatus)
            || OrderStatusEnum.MODIFIED.equals(l_orderStatus)
            || OrderStatusEnum.CANCELLED.equals(l_orderStatus))
        {
            MarketResponseMessage l_marketResponseMessage = null;
            long l_lngOrderId = l_orderUnit.getOrderId();

            //注文が注文受付済の場合
            //DefaultNewOrderSentMarketResponseMessage(arg0 : long)
            if (OrderStatusEnum.ORDERED.equals(l_orderStatus))
            {
                l_marketResponseMessage = 
                    new DefaultNewOrderSentMarketResponseMessage(l_lngOrderId);
            }

            // 1.2.1 (*) 注文が訂正済の場合
            // 1.2.1.1 DefaultChangeOrderSentMarketResponseMessage(arg0 : long)
            else if (OrderStatusEnum.MODIFIED.equals(l_orderStatus))
            {
                l_marketResponseMessage = 
                    new DefaultChangeOrderSentMarketResponseMessage(l_lngOrderId);
            }
            
            // 1.2.2 (*) 注文が取消済の場合
            // 1.2.2.1 DefaultCancelOrderSentMarketResponseMessage(arg0 : long)
            else
            {
                l_marketResponseMessage = 
                    new DefaultCancelOrderSentMarketResponseMessage(l_lngOrderId);
            }
            
            // 1.2.3 getThreadLocalPersistenceEventInterceptor( )
            FeqOrderManagerPersistenceEventInterceptor l_currentInterceptor =
                this.getThreadLocalPersistenceEventInterceptor();
            
            // 1.2.4 外国株式注文受付更新イベントインタセプタ(MarketResponseMessage)
            FeqOrderManagerPersistenceEventInterceptor l_updateInterceptor = 
                new WEB3FeqOrderAcceptUpdateInterceptor(l_marketResponseMessage);
            
            // 1.2.5 setThreadLocalPersistenceEventInterceptor
            this.setThreadLocalPersistenceEventInterceptor(l_updateInterceptor);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            FeqMarketResponseReceiverCallbackService l_marketResponseReceiverCallbackService =
                (FeqMarketResponseReceiverCallbackService)
                    l_tradingModule.getMarketAdapter().getMarketResponseReceiverCallbackService();
            // 1.2.6 process(arg0 : NewOrderSentMarketResponseMessage)
            l_marketResponseReceiverCallbackService.process(l_marketResponseMessage);
            
            // 1.2.7 setThreadLocalPersistenceEventInterceptor(arg0 : FeqOrderManagerPersistenceEventInterceptor)
            this.setThreadLocalPersistenceEventInterceptor(l_currentInterceptor);
            
            log.exiting(STR_METHOD_NAME);
        }
    }
    
    /**
     * (update合計約定金額（円）)<BR>
     * 合計約定金額（円）の再計算を行なう。<BR>
     * <BR>
     * １）　@this.getOrderUnit( )をコールし、注文単位を取得する。<BR>
     * 　@　@　@［引数］<BR>
     * 　@　@　@　@注文単位ID：　@パラメータ.注文単位ID<BR>
     * <BR>
     * 　@　@　@※該当データなしの場合は、例外をスローする。<BR>
     * <BR>
     * ２）　@外国株式トランザクションマネージャ.getトランザクション( )をコールし、<BR>
     * 　@　@　@１）で取得した注文単位に該当するトランザクションを取得する。<BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@　@外国株式注文単位：　@１）で取得した注文単位<BR>
     * <BR>
     * 　@　@　@※該当データなしの場合は、例外をスローする。<BR>
     * <BR>
     * ３）　@外国株式トランザクションマネージャ.getトランザクション( )の戻り値の要素数分ループし、<BR>
     * 　@　@　@各要素毎に以下内容で計算を行い、計算結果の合計値を計算する。<BR>
     * <BR>
     * 　@　@　@○　@トランザクション.約定単価　@×　@トランザクション.約定数量　@×　@トランザクション.適用為替レート<BR>
     * <BR>
     * 　@　@　@※上記の計算結果は、小数点以下を通貨.円貨換算丸め方式に従って丸めを行う。<BR>
     * <BR>
     * ４）　@３）で取得した合計値で、注文単位テーブル.合計約定金額（円）を更新する。<BR>
     * <BR>
     * @@param l_orderUnitId - 注文単位ID<BR>
     * @@throws WEB3BaseException
     */
    public void updateExecutedAmountYen(long l_orderUnitId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcExecutedAmountYen(long l_orderUnitId)";
        log.entering(STR_METHOD_NAME);

        try 
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY);

            // １）　@this.getOrderUnit( )をコールし、注文単位を取得する。
            WEB3FeqOrderUnit l_feqOrderUnit =
                (WEB3FeqOrderUnit)this.getOrderUnit(l_orderUnitId);
            
            // ２）　@外国株式トランザクションマネージャ.getトランザクション( )をコールし、
            // １）で取得した注文単位に該当するトランザクションを取得する。
            WEB3FeqFinTransactionManager l_finTransactionManager = 
                (WEB3FeqFinTransactionManager)l_tradingModule.getFinTransactionManager();
            
            List l_lisTransaction = 
                l_finTransactionManager.getTransactions(l_feqOrderUnit);

            if (l_lisTransaction == null || l_lisTransaction.isEmpty())
            {
                log.debug("テーブルに該当するデータがありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME);
            }
            
            // ３）　@外国株式トランザクションマネージャ.getトランザクション( )の戻り値の要素数分ループし、
            // 各要素毎に以下内容で計算を行い、計算結果の合計値を計算する。
            WEB3FeqBizLogicProvider l_bizLogicProvider =
                (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();
            WEB3FeqProduct l_product = (WEB3FeqProduct)l_feqOrderUnit.getProduct();
            WEB3GentradeCurrency l_genCurrency = l_product.getCurrency();
            int l_intScale = l_genCurrency.getScale();
            String l_strChangeJpyRoundDiv = l_genCurrency.getChangeJpyRoundDiv();
            
            int l_intSize = l_lisTransaction.size();
            BigDecimal l_bdExecutedAmountTotal = new BigDecimal("0");
            for (int i = 0; i < l_intSize; i++)
            {
                FeqFinTransactionParams l_feqFinTransactionParams = 
                    (FeqFinTransactionParams)l_lisTransaction.get(i);
                double l_dbExecutedAmount = 
                    l_feqFinTransactionParams.getPrice() * l_feqFinTransactionParams.getQuantity();
                BigDecimal l_bdExecutedAmount = new BigDecimal(l_dbExecutedAmount + "");
                l_bdExecutedAmount = l_bdExecutedAmount.setScale(l_intScale, BigDecimal.ROUND_HALF_EVEN);
                double l_dbExecutedAmountYen =
                    l_bizLogicProvider.calcJPYAmount(
                        l_bdExecutedAmount.doubleValue(), 
                        l_feqFinTransactionParams.getFxRate(), 
                        l_strChangeJpyRoundDiv);
                l_bdExecutedAmountTotal = 
                    l_bdExecutedAmountTotal.add(new BigDecimal(l_dbExecutedAmountYen + ""));
            }
            
            // ４）　@３）で取得した合計値で、注文単位テーブル.合計約定金額（円）を更新する。
            FeqOrderUnitParams l_feqOrderUnitParams =
                new FeqOrderUnitParams((FeqOrderUnitRow)l_feqOrderUnit.getDataSourceObject());
            l_feqOrderUnitParams.setExecutedAmountYen(l_bdExecutedAmountTotal.doubleValue());
            l_queryProcesser.doUpdateQuery(l_feqOrderUnitParams);
        } 
        catch (NotFoundException l_ex) 
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);  
        } 
        catch (DataFindException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        } 
        catch (DataNetworkException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        } 
        catch (DataQueryException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (getネッティン対象注文単位)<BR>
     * ネッティング対象となる注文単位の一覧を取得する。<BR>
     * <BR>
     * １）　@注文単位検索<BR>
     * 以下の条件にて外株注文単位テーブルを検索する。<BR>
     * 検索条件に対応するパラメータ(口座ID、証券会社コード) がnullの場合、その条件は検索条件から除外する。<BR>
     * <BR>
     * [条件]<BR>
     * 口座ID = パラメータ.口座ID And <BR>
     * 証券会社コード = パラメータ.証券会社コード And <BR>
     * 銘柄タイプ = ProductTypeEnum.外国株式 And <BR>
     * 発注日 = パラメータ.発注日 And <BR>
     * (注文有効状態 = "クローズ" And 約定数量 != (null or 0)) <BR>
     * <BR>
     * 該当データなしの場合、nullを返却する。<BR>
     * <BR>
     * ２）　@検索結果を返却する。<BR>
     * <BR>
     * @@param l_lngAccountId-(口座ID)<BR>
     * 口座ID<BR>
     * @@param l_strInstitutionCode-(証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_datBizDate-(発注日)<BR>
     * 発注日<BR>
     * @@return webbroker3.feq.WEB3FeqOrderUnit[]
     * @@throws WEB3BaseException
     */
    public WEB3FeqOrderUnit[] getNettingOrderUnit(
        Long l_lngAccountId,
        String l_strInstitutionCode,
        Date l_datBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getNettingOrderUnit(Long, String, Date)";
        log.entering(STR_METHOD_NAME);

        StringBuffer l_sbWhere = new StringBuffer();
        List l_lisContainer = new ArrayList();
        List l_lisOrderUnitRows = null;
        List l_lisWEB3FeqOrderUnit = new ArrayList();
        WEB3FeqOrderUnit[] l_arrayWEB3FeqOrderUnit = null;

        // 検索条件に口座IDがnullの場合、その条件は検索条件から除外する。
        if (l_lngAccountId != null)
        {
            l_sbWhere.append("account_id = ?");
            l_lisContainer.add(l_lngAccountId);
        }

        // 検索条件に証券会社コードがnullの場合、その条件は検索条件から除外する。
        if (WEB3StringTypeUtility.isNotEmpty(l_strInstitutionCode))
        {
            if (l_sbWhere.length() != 0)
            {
                l_sbWhere.append(" and institution_code = ?");
            }
            else
            {
                l_sbWhere.append("institution_code = ?");
            }

            l_lisContainer.add(l_strInstitutionCode);
        }

        if (l_sbWhere.length() != 0)
        {
            l_sbWhere.append(" and product_type = ?");
        }
        else
        {
            l_sbWhere.append("product_type = ?");
        }

        l_sbWhere.append(" and biz_date = ?");
        l_sbWhere.append(" and order_open_status = ?");
        l_sbWhere.append(" and executed_quantity is not null");
        l_sbWhere.append(" and executed_quantity <> 0");

        l_lisContainer.add(ProductTypeEnum.FOREIGN_EQUITY);
        l_lisContainer.add(WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd"));
        l_lisContainer.add(OrderOpenStatusEnum.CLOSED);

        Object[] l_arrayContainer = l_lisContainer.toArray();

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisOrderUnitRows = l_queryProcessor.doFindAllQuery(
                FeqOrderUnitRow.TYPE,
                l_sbWhere.toString(),
                l_arrayContainer);
        }
        catch (DataException l_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // 該当データなしの場合、nullを返却する。
        if (l_lisOrderUnitRows == null || l_lisOrderUnitRows.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            l_arrayWEB3FeqOrderUnit = new WEB3FeqOrderUnit[l_lisOrderUnitRows.size()];

            for (int i = 0; i < l_lisOrderUnitRows.size(); i++)
            {
                FeqOrderUnitRow l_row = (FeqOrderUnitRow)l_lisOrderUnitRows.get(i);
                WEB3FeqOrderUnit l_feqOrderUnit = (WEB3FeqOrderUnit)this.toOrderUnit(l_row);
                l_lisWEB3FeqOrderUnit.add(l_feqOrderUnit);
            }

            l_lisWEB3FeqOrderUnit.toArray(l_arrayWEB3FeqOrderUnit);

            log.exiting(STR_METHOD_NAME);
            return l_arrayWEB3FeqOrderUnit;
        }
    }

    /**
     * (updateネッティング注文更新日付)<BR>
     * ネッティング注文の更新日付をupdateする。<BR>
     * <BR>
     * 　@１）　@this.getOrderUnit( )をコールし、注文単位を取得する。<BR>
     * 　@　@　@［引数］<BR>
     * 　@　@　@　@注文単位ID：　@パラメータ.注文単位ID<BR>
     * <BR>
     * 　@　@　@※該当データなしの場合は、例外をスローする。<BR>
     * <BR>
     * <BR>
     * 　@２－１）　@以下の条件に該当するネッティング注文の注文単位レコードをupdateする。<BR>
     * 　@　@<条件><BR>
     * 　@　@注文単位テーブル.注文単位ID = パラメータ.注文単位ID <BR>
     * <BR>
     * 　@　@<更新内容><BR>
     * 　@　@注文単位レコード.更新日付 = 現在日時<BR>
     * <BR>
     * 　@２－２）　@以下の条件に該当するネッティング注文の注文履歴の、最終履歴レコードの更新日付をupdateする。<BR>
     * <BR>
     * 　@　@<条件><BR>
     * 　@　@履歴テーブル.注文単位ID = パラメータ.注文単位ID　@かつ <BR>
     * 　@　@履歴テーブル.注文履歴番号 = １）で取得した注文単位.注文履歴最終通番 <BR>
     * <BR>
     * 　@　@<更新内容><BR>
     * 　@　@履歴レコード.更新日付 = 現在日時<BR>
     * <BR>
     * 　@２－３）　@以下の条件に該当する、出来終了注文の注文（ヘッダ）の更新日時をupdateする。<BR>
     * <BR>
     * 　@　@<条件><BR>
     * 　@　@注文（ヘッダ）テーブル.注文ID = １）で取得した注文単位.注文ID<BR>
     * <BR>
     * 　@　@<更新内容><BR>
     * 　@　@注文（ヘッダ）レコード.更新日付 = 現在日時<BR>
     * <BR>
     * @@param l_lngOrderUnitId-(注文単位ID)<BR>
     * 注文単位ID<BR>
     * @@throws WEB3BaseException
     */
    public void updateNettingOrderLastUpdatedTimestamp(Long l_lngOrderUnitId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateNettingOrderLastUpdatedTimestamp(Long)";
        log.entering(STR_METHOD_NAME);

        try
        {
            // １）this.getOrderUnit( )をコールし、注文単位を取得する。
            // ※該当データなしの場合は、例外をスローする。
            FeqOrderUnit l_feqOrderUnit = (FeqOrderUnit)this.getOrderUnit(l_lngOrderUnitId.longValue());

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            // ２－１）注文単位テーブル.注文単位ID = パラメータ.注文単位IDの条件に
            // 該当するネッティング注文の注文単位レコードをupdateする。
            PrimaryKey l_feqOrderUnitPK = new FeqOrderUnitPK(l_lngOrderUnitId.longValue());
            // 注文単位レコード.更新日付 = 現在日時
            Map l_mapNewValues = new HashMap();
            l_mapNewValues.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
            l_queryProcessor.doUpdateQuery(l_feqOrderUnitPK, l_mapNewValues);

            // ２－２）以下の条件に該当するネッティング注文の注文履歴の、最終履歴レコードの更新日付をupdateする。
            // 履歴テーブル.注文単位ID = パラメータ.注文単位ID　@かつ 
            // 履歴テーブル.注文履歴番号 = １）で取得した注文単位.注文履歴最終通番
            FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow)l_feqOrderUnit.getDataSourceObject();
            // 履歴レコード.更新日付 = 現在日時
            l_queryProcessor.doUpdateAllQuery(
                FeqOrderActionParams.TYPE,
                "order_unit_id = ? and order_action_serial_no = ?",
                new Object[]{l_lngOrderUnitId, new Integer(l_feqOrderUnitRow.getLastOrderActionSerialNo())},
                l_mapNewValues);

            // ２－３）注文（ヘッダ）テーブル.注文ID = １）で取得した注文単位.注文IDの条件に該当する
            // 出来終了注文の注文（ヘッダ）の更新日時をupdateする。
            long l_lngOrderId = l_feqOrderUnitRow.getOrderId();
            // 注文（ヘッダ）レコード.更新日付 = 現在日時
            PrimaryKey l_feqOrderPK = new FeqOrderPK(l_lngOrderId);
            l_queryProcessor.doUpdateQuery(l_feqOrderPK, l_mapNewValues);
        }
        catch (NotFoundException l_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02011,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文ＩＤに該当する注文単位が存在しません。",
                l_ex);
        }
        catch (DataException l_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
