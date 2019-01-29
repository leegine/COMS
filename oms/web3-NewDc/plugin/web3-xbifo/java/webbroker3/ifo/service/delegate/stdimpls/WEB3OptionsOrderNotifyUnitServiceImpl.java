head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsOrderNotifyUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP注文通知UnitServiceImpl(WEB3OptionsOrderNotifyUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/02 凌建平 (中訊) 新規作成
                 : 2006/08/03 肖志偉 (中訊) 仕様変更　@モデル540
Revesion History : 2007/06/08 孫洪江 (中訊) 仕様変更モデルNo.646 716
Revesion History : 2007/07/02 孟亜南 (中訊) 仕様変更　@モデル770
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PayTypeDef;
import webbroker3.common.define.WEB3SonarExecutionConditionDef;
import webbroker3.common.define.WEB3TradeTypeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.ifo.service.delegate.WEB3OptionsOrderNotifyUnitService;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoOpenContractOrderNotifyUpdateInterceptor;
import webbroker3.ifo.WEB3IfoOpenContractOrderSpec;
import webbroker3.ifo.WEB3IfoPositionManagerImpl;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3IfoSettleContractOrderNotifyUpdateInterceptor;
import webbroker3.ifo.WEB3IfoSettleContractOrderSpec;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.data.HostFotypeOrderReceiptParams;
import webbroker3.ifo.define.WEB3IfoOrderTypeDef;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;

/**
 * (OP注文通知UnitServiceImpl)<BR>
 * 株価指数オプション注文通知１件サービス実装クラス<BR>
 * <BR>
 * 注文１件ごとの注文通知処理を実施する。<BR>
 * <BR>
 * Plugin時に自動トランザクションTransactionalInterceptor<BR>
 * (TransactionalInterceptor.TX_CREATE_NEW)を指定する。<BR>
 * @@author  : 凌建平
 * @@version : 1.0
 */
public class WEB3OptionsOrderNotifyUnitServiceImpl implements WEB3OptionsOrderNotifyUnitService 
{
   
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionsOrderNotifyUnitServiceImpl.class);
   
    /**
     * @@roseuid 41AAE8450251
     */
    public WEB3OptionsOrderNotifyUnitServiceImpl() 
    {
     
    }
   
    /**
     * (notify新規建注文)<BR>
     * 新規建注文通知処理を実施する。 <BR>
     * <BR>
     * シーケンス図<BR>
     * 「(OP注文通知)notify新規建注文」参照。<BR>
     *<BR>
     *<BR>
     * (*)証拠金口座開設チェック<BR>
     * 売建指定で証拠金口座未開設の場合<BR>
     * (補助口座.補助口座タイプ != ”株式オプション取引口座(先物証拠金)” &&<BR>
     * 先物OP注文通知キューParams.売買区分 == ”売付”)<BR>
     * 「証拠金口座未開設」の例外をthrowする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_01294<BR>
     * <BR>
     * @@param l_hostFotypeOrderReceiptParams - (先物OP注文通知キューParams)<BR>
     * 【先物OP注文通知キューテーブル】の1-Rowを表現するクラス<BR>
     * @@param l_subAccount - 補助口座オブジェクト
     * @@roseuid 4163A24E03A6
     */
    public void notifyOpenContractOrder(
        HostFotypeOrderReceiptParams l_hostFotypeOrderReceiptParams, 
        SubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "notifyOpenContractOrder()";
        log.entering(STR_METHOD_NAME);
    
        if ((l_hostFotypeOrderReceiptParams == null) || (l_subAccount == null))
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
    
        //1.1  (*)証拠金口座開設チェック
        //1.1.1 (*)証拠金口座開設チェック
        //  売建指定で証拠金口座未開設の場合
        //  (補助口座.補助口座タイプ != ”株式オプション取引口座(先物証拠金)” &&
        //  先物OP注文通知キューParams.売買区分 == ”売付”)
        //  「証拠金口座未開設」の例外をthrowする。
        if (!SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType()) 
            && WEB3TradeTypeDef.SELL.equals(l_hostFotypeOrderReceiptParams.getTradeType()))
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01294, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        try
        {
            //1.2 get市場BySONAR(String, String)(拡張金融オブジェクトマネージャ::get市場BySONAR)
            //  [get市場()に指定する引数] 
            //  証券会社コード：　@引数.先物OP注文通知キューParams.証券会社コード 
            //  市場コード(SONAR)：　@引数.先物OP注文通知キューParams.市場コード(SONAR) 
            WEB3GentradeFinObjectManager l_objectManager = 
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_market = l_objectManager.getMarketBySONAR(
                l_hostFotypeOrderReceiptParams.getInstitutionCode(),
                l_hostFotypeOrderReceiptParams.getSonarMarketCode());
    
            //1.3 getInstitution(証券会社コード : 論理ビュー::java::lang::String)
            //  [getInstitution()に指定する引数] 
            //  証券会社コード：　@先物OP注文通知キューParams.証券会社コード
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            Institution l_institution = l_accountManager.getInstitution(l_hostFotypeOrderReceiptParams.getInstitutionCode());

            //1.4 get銘柄(Institution, String)(先物OPプロダクトマネージャ::get銘柄)
            //  [get銘柄()に指定する引数] 
            //  証券会社：　@取得した証券会社オブジェクト 
            //  銘柄コード：　@先物OP注文通知キューParams.銘柄コード
            WEB3IfoProductManagerImpl l_productManagerImpl = 
                (WEB3IfoProductManagerImpl)l_tradingMod.getProductManager();
            WEB3IfoProductImpl l_product = l_productManagerImpl.getIfoProduct(
                l_institution,
                l_hostFotypeOrderReceiptParams.getProductCode());

            //1.5  get発注日( )(取引時間管理::get発注日)
            Date l_datOrder = WEB3GentradeTradingTimeManagement.getOrderBizDate();

            //1.6 getAP層執行条件(String)(OP注文通知UnitServiceImpl::getAP層執行条件)
            //  [get執行条件()に指定する引数] 
            //  執行条件(SONAR)：　@先物OP注文通知キューParams.執行条件
            IfoOrderExecutionConditionType l_execCondType = 
                getAPOrderExecCondType(l_hostFotypeOrderReceiptParams.getExecutionCondition());

            //1.7 create新規建注文内容(String, 扱者, boolean, String, 先物OP銘柄, double, double, IfoOrderExecutionConditionType, Date, String, double)
            //  (新規建注文内容::create新規建注文内容)
            //  [create新規建注文内容()に指定する引数] 
            //  証券会社コード = 先物OP注文通知キューParams.証券会社コード 
            //  扱者 = null 
            //  is買建 = 先物OP注文通知キューParams.売買区分 == ”買付”の場合、true。以外、false。 
            //  市場コード = 拡張金融オブジェクトマネージャ.get市場BySONAR()の戻り値.getMarketCode() 
            //  銘柄 = 先物OPプロダクトマネージャ.get銘柄()の戻り値 
            //  数量 = 先物OP注文通知キューParams.数量 
            //  指値 = 先物OP注文通知キューParams.指値 
            //  執行条件 = this.getAP層執行条件()の戻り値 
            //  注文失効日 = 取引時間管理.get発注日()の戻り値 
            //  発注条件 = (*)”0： DEFAULT（条件指定なし）” 
            //  (W指値)訂正指値 = 0 
            //  (*)注文通知で登録する注文の発注条件は「条件指定なし」固定
            //  注文期限区分 = ”当日限り”
            //  初回注文の注文単位ID = null
            //  夕場前繰越対象フラグ = false(夕場前繰越なし)
            boolean l_blnTradeType = false;
            if (WEB3TradeTypeDef.BUY.equals(l_hostFotypeOrderReceiptParams.getTradeType()))
            {
                l_blnTradeType = true;
            }
            WEB3IfoOpenContractOrderSpec l_orderSpec = WEB3IfoOpenContractOrderSpec.createOpenContractOrderSpec(
                l_hostFotypeOrderReceiptParams.getInstitutionCode(),
                null,
                l_blnTradeType,
                l_market.getMarketCode(),
                l_product,
                l_hostFotypeOrderReceiptParams.getQuantity(),
                l_hostFotypeOrderReceiptParams.getLimitPrice(),
                l_execCondType,
                l_datOrder,
                WEB3OrderingConditionDef.DEFAULT,
                0D,
                0D,
                null,
                WEB3OrderExpirationDateTypeDef.DAY_LIMIT,
                null,
                false);
                
            //1.8 手数料()(手数料::手数料)
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
 
            //1.9  (*1)プロパティセット
            //  (*1)手数料オブジェクトに以下の通りプロパティをセットする。
            //  手数料.注文チャネル      = 先物OP注文通知キューParams.注文チャネル
            //  手数料.証券会社コード = 先物OP注文通知キューParams.証券会社コード
            //  手数料.部店ID        = 補助口座.get取引店().getBranchId()
            //  手数料.発注日     = 取引時間管理.get発注日()
            //  手数料.取引コード(SONAR)    = ”51：建”
            //  手数料.手数料商品コード    = ”51：株価指数OP”
            //  手数料.弁済区分        = ”00：その他”
            //  手数料.is指値        = 新規建注文内容.isLimitOrder()
            //  手数料.原資産銘柄コード = 先物OP銘柄.get原資産銘柄コード()
            //  手数料.数量 = 新規建注文内容.getQuantity()
            l_commission.setOrderChannel(l_hostFotypeOrderReceiptParams.getChannel());
            l_commission.setInstitutionCode(l_hostFotypeOrderReceiptParams.getInstitutionCode());
            l_commission.setBranchId(l_subAccount.getMainAccount().getBranch().getBranchId());
            l_commission.setOrderBizDate(new Timestamp(l_datOrder.getTime()));
            l_commission.setSonarTradedCode(WEB3TransactionTypeSONARDef.OPEN_CONTRACT);
            l_commission.setCommissionProductCode(WEB3CommisionProductCodeDef.INDEX_OP);
            l_commission.setPayType(WEB3PayTypeDef.OTHER);
            l_commission.setIsLimitPrice(l_orderSpec.isLimitOrder());
            
            l_commission.setUnderlyingProductCode(
                l_product.getUnderlyingProductCode());
            
            l_commission.setQuantity(l_orderSpec.getQuantity());
            
            //1.10 get取引銘柄(Institution, String, String)(先物OPプロダクトマネージャ::get取引銘柄)
            //  [get取引銘柄()に指定する引数] 
            //  証券会社：　@取得した証券会社オブジェクト 
            //  銘柄コード：　@先物OP注文通知キューParams.銘柄コード 
            //  市場コード：　@拡張金融オブジェクトマネージャ.get市場BySONAR()の戻り値.getMarketCode()
            WEB3IfoTradedProductImpl l_tradeProduct = l_productManagerImpl.getIfoTradedProduct(
                l_institution,
                l_hostFotypeOrderReceiptParams.getProductCode(),
                l_market.getMarketCode());

            //1.11 calc概算受渡代金(手数料, double, SubAccount, 先物OP取引銘柄, double, SideEnum, boolean, boolean)(OP注文マネージャ::calc概算受渡代金)
            //  [calc概算受渡代金()に指定する引数] 
            //  手数料：　@生成した手数料オブジェクト 
            //  計算単価：　@先物OP注文通知キューParams.指値 
            //  補助口座：　@補助口座オブジェクト 
            //  先物OP取引銘柄：　@取得した先物OP取引銘柄オブジェクト 
            //  数量： 新規建注文内容.getQuantity() 
            //  売買： 
            //　@先物OP注文通知キューParams.売買区分 = ”買付”の場合SideEnum.BUY 
            //　@先物OP注文通知キューParams.売買区分 = ”売付”の場合SideEnum.SELL 
            //  is返済注文：　@false 
            //  isSkip金額チェック：　@true(スキップする) 
            WEB3OptionOrderManagerImpl l_orderManager = 
                (WEB3OptionOrderManagerImpl) l_tradingMod.getOrderManager();
            SideEnum l_dealing = null;
            if (WEB3TradeTypeDef.BUY.equals(l_hostFotypeOrderReceiptParams.getTradeType()))
            {
                l_dealing = SideEnum.BUY;
            }
            else if (WEB3TradeTypeDef.SELL.equals(l_hostFotypeOrderReceiptParams.getTradeType()))
            {
                l_dealing = SideEnum.SELL;
            }
            WEB3IfoEstimateDeliveryAmountCalcResult l_deliveryAmount = 
                l_orderManager.calcEstimateDeliveryAmount(
                    l_commission,
                    l_hostFotypeOrderReceiptParams.getLimitPrice(),
                    l_subAccount,
                    l_tradeProduct,
                    l_orderSpec.getQuantity(),
                    l_dealing,
                    false,
                    true);

            //1.12  先物OP新規建注文通知更新インタセプタ(新規建注文内容)(先物OP新規建注文通知更新インタセプタ::先物OP新規建注文通知更新インタセプタ)
            WEB3IfoOpenContractOrderNotifyUpdateInterceptor l_interceptor = 
                new WEB3IfoOpenContractOrderNotifyUpdateInterceptor(l_orderSpec);

            //1.13  (*2)プロパティセット
            //(*2)インタセプタオブジェクトのプロパティに以下の値をセットする。
            //  インタセプタ.手数料          = （calc概算受渡代金()引数の手数料オブジェクト）
            //  インタセプタ.概算受渡代金計算結果   = （calc概算受渡代金()の戻り値）
            //  インタセプタ.発注条件     = ”0： DEFAULT（条件指定なし）”
            //  インタセプタ.発注条件演算子      = null
            //  インタセプタ.逆指値基準値タイプ        = null
            //  インタセプタ.逆指値基準値       = 0
            //  インタセプタ.(W指値)訂正指値        = 0
            //  インタセプタ.識別コード        = 先物OP注文通知キューParams.識別コード
            //  インタセプタ.受注日時     = 先物OP注文通知キューParams.受注日時
            //  インタセプタ.発注日          = 取引時間管理.get発注日()の戻り値
            //  インタセプタ.受渡日            = 取引銘柄.get受渡日()の戻り値
            //  インタセプタ.立会区分        = 取引時間管理.get立会区分()の戻り値
            l_interceptor.setCommision(l_commission);
            l_interceptor.setEstimateDeliveryAmountCalcResult(l_deliveryAmount);
            l_interceptor.setOrderCond(WEB3OrderingConditionDef.DEFAULT);
            l_interceptor.setOrderCondOperator(null);
            l_interceptor.setStopOrderBasePriceType(null);
            l_interceptor.setStopOrderBasePrice(0);
            l_interceptor.setWLimitPriceChange(0);
            l_interceptor.setOrderRequestNumber(l_hostFotypeOrderReceiptParams.getOrderRequestNumber());
            l_interceptor.setReceivedDateTime(l_hostFotypeOrderReceiptParams.getCreateDatetime());
            l_interceptor.setBizDate(l_datOrder);
            l_interceptor.setDeliveryDate(l_tradeProduct.getDailyDeliveryDate());
            String l_strSessionType = WEB3GentradeTradingTimeManagement.getSessionType();
            l_interceptor.setSessionType(l_strSessionType);

            //1.14 setThreadLocalPersistenceEventInterceptor(先物OP新規建注文通知更新インタセプタ : IfoOrderManagerPersistenceEventInterceptor)
            l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);  

            //1.15 createNewOrderId()(OP注文マネージャ::createNewOrderId)
            long l_lngOrderId = l_orderManager.createNewOrderId();

            //1.16 setBusinessTimestamp( )(取引時間管理::setBusinessTimestamp)
            WEB3GentradeTradingTimeManagement.setBusinessTimestamp();

            //1.17 submitOpenContractOrder(補助口座 : SubAccount, 新規建注文内容 : IfoOpenContractOrderSpec, 注文ID : long, 取引パスワード : String, isSkip発注審査 : boolean)
            //  [submitOpenContractOrder()に指定する引数] 
            //  arg0（補助口座）：　@補助口座 
            //  arg1（新規建注文内容）：　@create新規建注文内容()で生成した新規建注文内容 
            //  arg2（注文ＩＤ）：　@OP注文マネージャ.createNewOrderId() 
            //  arg3（取引パスワード）：　@補助口座.getMainAccount().getTradingPassword() 
            //  arg4（isSkip発注審査）：　@true 
            OrderSubmissionResult l_result = l_orderManager.submitOpenContractOrder(
                l_subAccount,
                l_orderSpec,
                l_lngOrderId,
                l_subAccount.getMainAccount().getTradingPassword(),
                true);
            if (l_result.getProcessingResult().isFailedResult())
            {
                log.error(l_result.getProcessingResult().toString());
                throw new WEB3BusinessLayerException(
                    l_result.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //1.18 余力再計算(補助口座 : 補助口座)
            //  [引数] 
            //  補助口座： 補助口座オブジェクト 
			WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
				(WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
            //補助口座の補助口座タイプ != 7（証拠金口座）の場合、実施
            if (!SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType()))
            {
				l_tpTradingPowerReCalcService.reCalcTradingPower((WEB3GentradeSubAccount)l_subAccount);
            }
        }
        catch (NotFoundException l_nfex)
        {
            log.error("DBへのアクセスに失敗しました。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfex.getMessage(), l_nfex);
        } 
        catch (WEB3BaseException l_baseException)
        {
            log.error(l_baseException.getErrorMessage(), l_baseException);
            throw new WEB3SystemLayerException(
                l_baseException.getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME);
        }
    }
       
    /**
     * (notify返済注文)<BR>
     * 返済注文通知処理を実施する。 <BR>
     * <BR>
     * シーケンス図<BR>
     * 「(OP注文通知)notify返済注文」参照。<BR>
     * @@param l_hostFotypeOrderReceiptParams - (先物OP注文通知キューParams)<BR>
     * 【先物OP注文通知キューテーブル】の1-Rowを表現するクラス<BR>
     * @@param l_subAccount - 補助口座オブジェクト
     * @@roseuid 4163A2580377
     */
    public void notifySettleContractOrder(
        HostFotypeOrderReceiptParams l_hostFotypeOrderReceiptParams, 
        SubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "notifySettleContractOrder()";
        log.entering(STR_METHOD_NAME);
    
        if ((l_hostFotypeOrderReceiptParams == null) || (l_subAccount == null))
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
    
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        try
        {
            //1.1 get市場BySONAR(String, String)(拡張金融オブジェクトマネージャ::get市場BySONAR)
            //  [get市場()に指定する引数] 
            //  証券会社コード：　@引数.先物OP注文通知キューParams.証券会社コード 
            //  市場コード(SONAR)：　@引数.先物OP注文通知キューParams.市場コード(SONAR) 
            WEB3GentradeFinObjectManager l_objectManager = 
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_market = l_objectManager.getMarketBySONAR(
                l_hostFotypeOrderReceiptParams.getInstitutionCode(),
                l_hostFotypeOrderReceiptParams.getSonarMarketCode());
    
            //1.2 getInstitution(証券会社コード : 論理ビュー::java::lang::String)
            //  [getInstitution()に指定する引数] 
            //  証券会社コード：　@先物OP注文通知キューParams.証券会社コード
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            Institution l_institution = l_accountManager.getInstitution(l_hostFotypeOrderReceiptParams.getInstitutionCode());

            //1.3 get銘柄(Institution, String)(先物OPプロダクトマネージャ::get銘柄)
            //  [get銘柄()に指定する引数] 
            //  証券会社：　@取得した証券会社オブジェクト 
            //  銘柄コード：　@先物OP注文通知キューParams.銘柄コード
            WEB3IfoProductManagerImpl l_productManagerImpl = 
                (WEB3IfoProductManagerImpl)l_tradingMod.getProductManager();
            WEB3IfoProductImpl l_product = l_productManagerImpl.getIfoProduct(
                l_institution,
                l_hostFotypeOrderReceiptParams.getProductCode());

            //1.4 create返済建玉一覧(補助口座, ContractTypeEnum, long, long)(先物OPポジションマネージャ::create返済建玉一覧)
            //  [create返済建玉一覧()に指定する引数] 
            //  補助口座：　@引数.補助口座 
            //  建区分：　@ 
            //　@引数.先物OP注文通知キューParams売買区分 == ”買付”の場合、”買建” 
            //　@以外、”売建” 
            //  市場ID：　@拡張金融オブジェクトマネージャ.get市場BySONAR()の戻り値.getMarketId() 
            //  銘柄ID：　@先物OPプロダクトマネージャ.get銘柄()の戻り値.getProductId() 
            WEB3IfoPositionManagerImpl l_positionManager =
                (WEB3IfoPositionManagerImpl)l_tradingMod.getPositionManager();
            ContractTypeEnum l_contractType = null;
            if (WEB3TradeTypeDef.BUY.equals(l_hostFotypeOrderReceiptParams.getTradeType()))
            {
                l_contractType = ContractTypeEnum.LONG;
            }
            else
            {
                l_contractType = ContractTypeEnum.SHORT;
            }
            WEB3FuturesOptionsCloseMarginContractUnit[] l_contractUnit = 
            l_positionManager.createSettleContracts(
                (WEB3GentradeSubAccount)l_subAccount,
                l_contractType,
                l_market.getMarketId(),
                l_product.getProductId());
                
            //1.5 create返済建玉エントリ(long, double, 返済建玉[])(OP注文マネージャ::create返済建玉エントリ)
            //  [create返済建玉エントリ()に指定する引数] 
            //  注文単位ID：　@0(新規注文) 
            //  注文数量：　@引数.先物OP注文通知キューParams.数量 
            //  返済建玉：　@先物OPポジションマネージャ.create返済建玉一覧()の戻り値
            WEB3OptionOrderManagerImpl l_orderManager = 
                (WEB3OptionOrderManagerImpl) l_tradingMod.getOrderManager();
            SettleContractEntry[] l_contractEntry = l_orderManager.createSettleContractEntry(
                Long.parseLong(WEB3IfoOrderTypeDef.NEW_ORDER),
                l_hostFotypeOrderReceiptParams.getQuantity(),
                l_contractUnit);

            //1.6 getAP層執行条件(String)(OP注文通知UnitServiceImpl::getAP層執行条件)
            //  [get執行条件()に指定する引数] 
            //  執行条件(SONAR)：　@先物OP注文通知キューParams.執行条件
            IfoOrderExecutionConditionType l_execCondType = 
                getAPOrderExecCondType(l_hostFotypeOrderReceiptParams.getExecutionCondition());

            //1.7  get発注日( )(取引時間管理::get発注日)
            Date l_datOrder = WEB3GentradeTradingTimeManagement.getOrderBizDate();

            //1.8  create返済注文内容(String, 扱者, double, IfoOrderExecutionConditionType, Date, SettleContractOrderEntry[], String, double)(返済注文内容::create返済注文内容)
            //  [create返済注文内容()に指定する引数] 
            //  証券会社コード = 先物OP注文通知キューParams.証券会社コード 
            //  扱者 = null 
            //  指値 = 先物OP注文通知キューParams.指値 
            //  執行条件 = this.getAP層執行条件()の戻り値 
            //  注文失効日 = 取引時間管理.get発注日()の戻り値 
            //  返済建玉エントﾘ = OP注文マネージャ.create返済建玉エントリ()の戻り値 
            //  発注条件 = (*)”0： DEFAULT（条件指定なし）” 
            //  (W指値)訂正指値 = 0 
            //  (*)注文通知で登録する注文の発注条件は「条件指定なし」固定
            //  注文期限区分 = ”当日限り”
            //  初回注文の注文単位ID = null
            //  夕場前繰越対象フラグ = false(夕場前繰越なし)
            WEB3IfoSettleContractOrderSpec l_orderSpec = WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
                l_hostFotypeOrderReceiptParams.getInstitutionCode(),
                null,
                l_hostFotypeOrderReceiptParams.getLimitPrice(),
                l_execCondType,
                l_datOrder,
                l_contractEntry,
                WEB3OrderingConditionDef.DEFAULT,
                0D,
                0D,
                null,
                WEB3OrderExpirationDateTypeDef.DAY_LIMIT,
                null,
                false);
                
            //1.9 手数料()(手数料::手数料)
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
 
            //1.10  (*1)プロパティセット
            //  (*1)手数料オブジェクトに以下の通りプロパティをセットする。
            //  手数料.注文チャネル      = 先物OP注文通知キューParams.注文チャネル
            //  手数料.証券会社コード = 先物OP注文通知キューParams.証券会社コード
            //  手数料.部店ID        = 補助口座.get取引店().getBranchId()
            //  手数料.発注日     = 取引時間管理.get発注日()
            //  手数料.取引コード(SONAR)    = ”52：返済”
            //  手数料.手数料商品コード    = ”51：株価指数OP”
            //  手数料.弁済区分        = ”00：その他”
            //  手数料.is指値        = 新規建注文内容.isLimitOrder()
            //  手数料.原資産銘柄コード = 先物OP銘柄.get原資産銘柄コード()
            //  手数料.日計り区分 = OP注文マネージャ.get日計り区分（）の戻り値(*)
            //  手数料.数量 = 返済注文内容.getTotalQuantity()
            l_commission.setOrderChannel(l_hostFotypeOrderReceiptParams.getChannel());
            l_commission.setInstitutionCode(l_hostFotypeOrderReceiptParams.getInstitutionCode());
            l_commission.setBranchId(l_subAccount.getMainAccount().getBranch().getBranchId());
            l_commission.setOrderBizDate(new Timestamp(l_datOrder.getTime()));
            l_commission.setSonarTradedCode(WEB3TransactionTypeSONARDef.SETTLE_CONTRACT);
            l_commission.setCommissionProductCode(WEB3CommisionProductCodeDef.INDEX_OP);
            l_commission.setPayType(WEB3PayTypeDef.OTHER);
            l_commission.setIsLimitPrice(l_orderSpec.isLimitOrder());
            
            l_commission.setUnderlyingProductCode(
                l_product.getUnderlyingProductCode());
            
            // (*)引数設定:create返済建玉エントリ()の戻り値
            l_commission.setDayTradeType(l_orderManager.getDayTradeType(l_contractEntry));
            
            l_commission.setQuantity(l_orderSpec.getTotalQuantity());
            
            //1.11 get取引銘柄(Institution, String, String)(先物OPプロダクトマネージャ::get取引銘柄)
            //  [get取引銘柄()に指定する引数] 
            //  証券会社：　@取得した証券会社オブジェクト 
            //  銘柄コード：　@先物OP注文通知キューParams.銘柄コード 
            //  市場コード：　@拡張金融オブジェクトマネージャ.get市場BySONAR()の戻り値.getMarketCode()
            WEB3IfoTradedProductImpl l_tradeProduct = l_productManagerImpl.getIfoTradedProduct(
                l_institution,
                l_hostFotypeOrderReceiptParams.getProductCode(),
                l_market.getMarketCode());

            //1.12 calc概算受渡代金(手数料, double, SubAccount, 先物OP取引銘柄, double, SideEnum, boolean, boolean)(OP注文マネージャ::calc概算受渡代金)
            //  [calc概算受渡代金()に指定する引数] 
            //  手数料：　@生成した手数料オブジェクト 
            //  計算単価：　@先物OP注文通知キューParams.指値 
            //  補助口座：　@補助口座オブジェクト 
            //  先物OP取引銘柄：　@取得した先物OP取引銘柄オブジェクト 
            //  数量： 返済注文内容.getTotalQuantity() 
            //  売買： 
            //　@先物OP注文通知キューParams.売買区分 = ”買付”の場合SideEnum.SELL 
            //　@先物OP注文通知キューParams.売買区分 = ”売付”の場合SideEnum.BUY 
            //  is返済注文：　@true 
            //  isSkip金額チェック：　@true(スキップする) 
            SideEnum l_dealing = null;
            if (WEB3TradeTypeDef.BUY.equals(l_hostFotypeOrderReceiptParams.getTradeType()))
            {
                l_dealing = SideEnum.SELL;
            }
            else if (WEB3TradeTypeDef.SELL.equals(l_hostFotypeOrderReceiptParams.getTradeType()))
            {
                l_dealing = SideEnum.BUY;
            }
            WEB3IfoEstimateDeliveryAmountCalcResult l_deliveryAmount = 
                l_orderManager.calcEstimateDeliveryAmount(
                    l_commission,
                    l_hostFotypeOrderReceiptParams.getLimitPrice(),
                    l_subAccount,
                    l_tradeProduct,
                    l_orderSpec.getTotalQuantity(),
                    l_dealing,
                    true,
                    true);
                
            //1.13  先物OP返済注文通知更新インタセプタ(返済注文内容)
            WEB3IfoSettleContractOrderNotifyUpdateInterceptor l_interceptor = 
                new WEB3IfoSettleContractOrderNotifyUpdateInterceptor(l_orderSpec); 

            //1.14  (*3)プロパティセット
            //(*3)インタセプタオブジェクトのプロパティに以下の値をセットする。
            //  インタセプタ.手数料          = （calc概算受渡代金()引数の手数料オブジェクト）
            //  インタセプタ.概算受渡代金計算結果   = （calc概算受渡代金()の戻り値）
            //  インタセプタ.発注条件     = ”0： DEFAULT（条件指定なし）”
            //  インタセプタ.発注条件演算子      = null
            //  インタセプタ.逆指値基準値タイプ        = null
            //  インタセプタ.逆指値基準値       = 0
            //  インタセプタ.(W指値)訂正指値        = 0
            //  インタセプタ.決済順序     = ”建日順”
            //  インタセプタ.識別コード        = 先物OP注文通知キューParams.識別コード
            //  インタセプタ.受注日時     = 先物OP注文通知キューParams.受注日時
            //  インタセプタ.発注日          = 取引時間管理.get発注日()の戻り値
            //  インタセプタ.受渡日            = 取引銘柄.get受渡日()の戻り値
            //  インタセプタ.立会区分        = 取引時間管理.get立会区分()の戻り値
            l_interceptor.setCommision(l_commission);
            l_interceptor.setEstimateDeliveryAmountCalcResult(l_deliveryAmount);
            l_interceptor.setOrderCond(WEB3OrderingConditionDef.DEFAULT);
            l_interceptor.setOrderCondOperator(null);
            l_interceptor.setStopOrderBasePriceType(null);
            l_interceptor.setStopOrderBasePrice(0);
            l_interceptor.setWLimitPriceChange(0);
            l_interceptor.setClosingOrder(WEB3ClosingOrderDef.OPEN_DATE);
            l_interceptor.setOrderRequestNumber(l_hostFotypeOrderReceiptParams.getOrderRequestNumber());
            l_interceptor.setReceivedDateTime(l_hostFotypeOrderReceiptParams.getCreateDatetime());
            l_interceptor.setBizDate(l_datOrder);

            //インタセプタ.受渡日            = 取引銘柄.get受渡日()の戻り値
            l_interceptor.setDeliveryDate(l_tradeProduct.getDailyDeliveryDate());

            //   インタセプタ.立会区分        = 取引時間管理.get立会区分()の戻り値
            String l_strSessionType = WEB3GentradeTradingTimeManagement.getSessionType();
            l_interceptor.setSessionType(l_strSessionType);

            //1.15 setThreadLocalPersistenceEventInterceptor(先物OP新規建注文通知更新インタセプタ : IfoOrderManagerPersistenceEventInterceptor)
            l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);  

            //1.16 createNewOrderId()(OP注文マネージャ::createNewOrderId)
            long l_lngOrderId = l_orderManager.createNewOrderId();

            //1.17 setBusinessTimestamp( )(取引時間管理::setBusinessTimestamp)
            WEB3GentradeTradingTimeManagement.setBusinessTimestamp();

            //1.18  submitSettleContractOrder(補助口座 : SubAccount, 返済注文内容 : IfoSettleContractOrderSpec, 注文ID : long, 取引パスワード : String, isSkip発注審査 : boolean)
            //  [submitSettleContractOrder()に指定する引数] 
            //  arg0（補助口座）：　@補助口座 
            //  arg1（返済注文内容）：　@create返済注文内容()で生成した返済注文内容 
            //  arg2（注文ＩＤ）：　@OP注文マネージャ.createNewOrderId() 
            //  arg3（取引パスワード）：　@補助口座.getMainAccount().getTradingPassword() 
            //  arg4（isSkip発注審査）：　@true 
            OrderSubmissionResult l_result = l_orderManager.submitSettleContractOrder(
                l_subAccount,
                l_orderSpec,
                l_lngOrderId,
                l_subAccount.getMainAccount().getTradingPassword(),
                true);
            if (l_result.getProcessingResult().isFailedResult())
            {
                log.error(l_result.getProcessingResult().toString());
                throw new WEB3BusinessLayerException(
                    l_result.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            //1.19 余力再計算(補助口座 : 補助口座)
            //  [引数] 
            //  補助口座： 補助口座オブジェクト 
			WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
				(WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
            //補助口座の補助口座タイプ != 7（証拠金口座）の場合、実施
            if (!SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType()))
            {
				l_tpTradingPowerReCalcService.reCalcTradingPower((WEB3GentradeSubAccount)l_subAccount);
            }
        }
        catch (NotFoundException l_nfex)
        {
            log.error("DBへのアクセスに失敗しました。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfex.getMessage(), l_nfex);
        } 
        catch (WEB3BaseException l_baseException)
        {
            log.error(l_baseException.getErrorMessage(), l_baseException);
            throw new WEB3SystemLayerException(
                l_baseException.getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME);
        }
    }
       
    /**
     * (getAP層執行条件)<BR>
     * 引数の執行条件(SONAR)より、<BR>
     * AP層用の執行条件を取得し返却する。 <BR>
     * <BR>
     * ・引数.執行条件(SONAR) == "1"(無条件(当日限り))の場合、<BR>
     * IfoOrderExecutionConditionType.NONE（条件なし）を返す。<BR>
     * <BR>
     * ・引数.執行条件(SONAR) == "3"(寄付)の場合、<BR>
     * IfoOrderExecutionConditionType.AT_MARKET_OPEN（寄り）を返す。<BR>
     * <BR>
     * ・引数.執行条件(SONAR) == "4"(引け)の場合、<BR>
     * IfoOrderExecutionConditionType.AT_MARKET_CLOSE（引け）を返す。<BR>
     * <BR>
     * ・引数.執行条件(SONAR) == "7"(出来ずば引成(不成))の場合、<BR>
     * IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED<BR>
     * （不出来引け成行）を返す。<BR>
     * <BR>
     * ・引数.執行条件(SONAR)が上記以外の場合は、例外をthrowする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_00127<BR>
     * 
     * @@param l_strExecCondTypeSonar - (執行条件(SONAR))<BR>
     * SONARの執行条件。 <BR>
     * <BR>
     * "1"：　@無条件 <BR>
     * "3"：　@寄付 <BR>
     * "4"：　@引け <BR>
     * "7"：　@出来ずば引成(不成) <BR>
     * @@return IfoOrderExecutionConditionType
     * @@roseuid 4174DC20006C
     */
    protected IfoOrderExecutionConditionType getAPOrderExecCondType(String l_strExecCondTypeSonar) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "notifySettleContractOrder()";
        log.entering(STR_METHOD_NAME);
        if (WEB3SonarExecutionConditionDef.UNCONDITIONDNESS.equals(l_strExecCondTypeSonar))
        {
            return IfoOrderExecutionConditionType.NONE;
        }
        else if (WEB3SonarExecutionConditionDef.AT_MARKET_OPEN.equals(l_strExecCondTypeSonar))
        {
            return IfoOrderExecutionConditionType.AT_MARKET_OPEN;
        }
        else if (WEB3SonarExecutionConditionDef.AT_MARKET_CLOSE.equals(l_strExecCondTypeSonar))
        {
            return IfoOrderExecutionConditionType.AT_MARKET_CLOSE;
        }
        else if (WEB3SonarExecutionConditionDef.NO_EXECUTED_MARKET_ORDER.equals(l_strExecCondTypeSonar))
        {
            return IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00127, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
    }
}
@
