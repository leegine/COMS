head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginSwapMarginServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引現引現渡サービス実装(WEB3MarginSwapMarginServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/20 凌建平 (中訊) 新規作成
                   2004/12/10 森川   (SRA)  残案件対応
                   2005/01/06 岡村   (SRA)  JavaDoc修正
Revesion History : 2007/12/10 趙林鵬 (中訊) 仕様変更モデルNo.1240
Revesion History : 2008/10/06 劉剣 (中訊) モデルNo.1323
*/

package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3MarginClientRequestService;
import webbroker3.equity.WEB3MarginSwapContractOrderSpec;
import webbroker3.equity.WEB3MarginSwapMarginUpdateInterceptor;
import webbroker3.equity.message.WEB3MarginCloseMarginContractUnit;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.equity.message.WEB3MarginSwapMarginCompleteRequest;
import webbroker3.equity.message.WEB3MarginSwapMarginCompleteResponse;
import webbroker3.equity.message.WEB3MarginSwapMarginConfirmRequest;
import webbroker3.equity.message.WEB3MarginSwapMarginConfirmResponse;
import webbroker3.equity.service.delegate.WEB3MarginSwapMarginRequestAdapter;
import webbroker3.equity.service.delegate.WEB3MarginSwapMarginService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondRow;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.tradingpower.define.WEB3TPTradingPowerErrorDivDef;
import webbroker3.tradingpower.define.WEB3TPResultAttentionObjectionTypeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * （信用取引現引現渡サービスImpl）。<BR>
 * <BR>
 * 信用取引現引現渡サービス実装クラス
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3MarginSwapMarginServiceImpl extends WEB3MarginClientRequestService implements WEB3MarginSwapMarginService 
{
    /**
     * (ログ出力ユーティリティ)。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginSwapMarginServiceImpl.class); 
    
    /**
     * (コンストラクタ)。<BR>
     * @@roseuid 4140066D01F9
     */
    public WEB3MarginSwapMarginServiceImpl() 
    {
    }
    
    /**
     * (execute)。<BR>
     * <BR>
     * 信用取引現引現渡サービス処理を実施する。<BR>
     * リクエストデータの型により、validate注文()メソッド、<BR>
     * submit注文()メソッドのいずれかをコールする。<BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 405692590048
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = getClass().getName() + "." + "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3MarginSwapMarginConfirmRequest)
        {
            //this.validateOrder((WEB3MarginSwapMarginConfirmRequest) l_request);
            l_response = this.validateOrder((WEB3MarginSwapMarginConfirmRequest) l_request);
            log.exiting(STR_METHOD_NAME);
        }
        else if (l_request instanceof WEB3MarginSwapMarginCompleteRequest)
        {
            //this.submitOrder((WEB3MarginSwapMarginCompleteRequest) l_request);
            l_response = this.submitOrder((WEB3MarginSwapMarginCompleteRequest) l_request);
            log.exiting(STR_METHOD_NAME);
        }
        else
        {
            log.error(STR_METHOD_NAME + "パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        //return l_request.createResponse();
        return l_response;
    }
    
    /**
     * (validate注文)。<BR>
     * <BR>
     * 信用取引現引現渡発注審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（信用取引現引現渡サービス）validate注文１」及び<BR>
     * 「（信用取引現引現渡サービス）validate注文２」参照。<BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3MarginSwapMarginConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 405692B502F7
     */
    protected WEB3MarginSwapMarginConfirmResponse validateOrder(
        WEB3MarginSwapMarginConfirmRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateOrder(WEB3MarginSwapMarginConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {   
            log.error("parameter is null type");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                   this.getClass().getName() + "." + STR_METHOD_NAME);                 
        }
        
        log.debug("シーケンス図「（信用取引現引現渡サービス）validate注文１」参照");
        //★★★★★ 信用取引現引現渡 / （信用取引現引現渡サービス）validate注文１ ★★★★★
        //2 信用取引現引現渡注文確認リクエストで、validate()を調用
        l_request.validate();
        
        //3 get補助口座(SubAccountTypeEnum)
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();        
                
        //4 信用取引現引現渡サービスImplで、get代理入力者()を取得するを調用
        Trader l_trader = this.getTrader();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        try
        {
            log.debug("======== validateOrder：信用現引現渡注文内容作成 開始 ========");
            
            WEB3MarginSwapMarginRequestAdapter l_requestAdapter =
                this.createRequestAdapter(l_request);
            
            WEB3EquityContract l_contract = l_requestAdapter.getContract();
            EqtypeContractRow l_eqtypeContractRow =
                (EqtypeContractRow)l_contract.getDataSourceObject();
            
            WEB3GentradeFinObjectManager l_objectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_market = l_objectManager.getMarket(l_contract.getMarketId());
            String l_strMarketCode = l_market.getMarketCode();
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
            
            EqTypeSettleContractOrderEntry[] l_eqTypeSettleContractOrderEntrys =
                this.createClosingContractEntry(
                    l_request.closeMarginContractUnits,
                    l_requestAdapter);
            
            TaxTypeEnum l_taxType = l_eqtypeContractRow.getTaxType();
            TaxTypeEnum l_swapTaxType = l_requestAdapter.getSwapTaxType();
            
            WEB3MarginSwapContractOrderSpec l_contractOrderSpec =
                WEB3MarginSwapContractOrderSpec.createSwapMarginOrderSpec(
                    l_trader,
                    l_eqTypeSettleContractOrderEntrys,
                    l_request.closingOrder,
                    l_taxType,
                    l_swapTaxType);
            
            log.debug("======== validateOrder：信用現引現渡注文内容作成 終了 ========");
            
            this.validateSwapContractOrder(l_subAccount,l_contractOrderSpec, l_requestAdapter);

            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

            //取引銘柄の取得
            WEB3EquityTradedProduct l_equityTradedProduct =
                (WEB3EquityTradedProduct)l_contract.getTradedProduct();

            //validate機@構預託同意(補助口座)
            //補助口座：　@get補助口座()の戻り値
            l_orderManager.validateMechanismDepositAgree(l_subAccount);

            double l_totalQuantity = l_contractOrderSpec.getTotalQuantity();
            double l_dblSwapPrice = this.getEstimatedSwapPrice(
                l_eqTypeSettleContractOrderEntrys,
                l_totalQuantity,
                l_requestAdapter);
            
            double l_dblCapitalGain;
            double l_dblCapitalGainTax;
            if (ContractTypeEnum.SHORT.equals(l_eqtypeContractRow.getContractType()))
            {
                WEB3EquityBizLogicProvider l_logicProvide = 
                    (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
                
                //譲渡損益の算出
                l_dblCapitalGain = l_logicProvide.calcCapitaGain(
                    l_dblSwapPrice,
                    l_totalQuantity,
                    l_eqtypeContractRow.getProductId(),
                    l_subAccount,
                    l_contractOrderSpec.getSwapTaxType());
                
                //取引銘柄の取得、受渡日の取得
                Timestamp l_tsDailyDeliveryDate =
                    new Timestamp(l_equityTradedProduct.getDailyDeliveryDate().getTime());
                    
                //譲渡益税の算出
                WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
                TaxTypeEnum l_deliveryDateTaxType = l_mainAccount.getDeliveryDateTaxType(l_tsDailyDeliveryDate);
                l_dblCapitalGainTax = l_logicProvide.calcCapitalGainTax(
                    l_subAccount, l_contractOrderSpec.getSwapTaxType(), l_dblCapitalGain, l_tsDailyDeliveryDate, l_deliveryDateTaxType);
            }
            else
            {
                l_dblCapitalGain = 0.0;
                l_dblCapitalGainTax = 0.0;
            }
            
            WEB3TPTradingPowerResult l_tpResult = this.validateTradingPower(
                l_subAccount,
                l_contractOrderSpec,
                (WEB3EquityTradedProduct)l_contract.getTradedProduct(),
                l_requestAdapter,
                l_dblSwapPrice,
                l_dblCapitalGain,
                l_dblCapitalGainTax,
                false);

            log.debug("シーケンス図「（信用取引現引現渡サービス）validate注文２」参照");
            //★★★★★ 信用取引現引現渡 / （信用取引現引現渡サービス）validate注文２ ★★★★★
            //3 信用取引現引現渡注文確認リクエストで、createResponse()を調用
            WEB3MarginSwapMarginConfirmResponse l_response = (WEB3MarginSwapMarginConfirmResponse)l_request.createResponse();
            
            //4 拡張プロダクトマネージャで、get取引銘柄(証券会社, String, String)を調用
            //引数は以下の通りに設定する。 
            //  証券会社：　@補助口座.証券会社ID に該当する証券会社オブジェクト 
            //  銘柄コード：　@サービス内で取得した建株.銘柄IDの株式銘柄オブジェクト.銘柄コード 
            //  市場コード：　@サービス内で取得した建株.市場IDの市場オブジェクト.市場コード
            WEB3EquityProductManager l_productManager = (WEB3EquityProductManager)l_tradingModule.getProductManager();
            //証券会社を取得
            WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)l_subAccount.getInstitution();
            //銘柄コードを取得
            WEB3EquityProduct l_product = (WEB3EquityProduct) l_contract.getProduct();
            String l_strProductCode = l_product.getProductCode();
            EqTypeTradedProduct l_tradeProduct = l_productManager.getTradedProduct(
                l_institution,
                l_strProductCode,
                l_strMarketCode); 

            //5 拡張プロダクトマネージャで、get時価(EqTypeTradedProduct)を調用            
            double l_dblCurrentPrice = l_productManager.getCurrentPrice(l_tradeProduct);
            
            WEB3MarginContractUnit[] l_contractUnits =
                this.createMarginContractUnitList(
                    l_eqTypeSettleContractOrderEntrys,
                    l_dblCurrentPrice,
                    l_requestAdapter);
            
            //17 取引時間管理で、get市場閉局警告市場(部店, ProductTypeEnum, String)を調用
            //引数は以下の通りに設定する。 
            //  部店：　@get補助口座()の戻り値.get取引店() 
            //  銘柄タイプ：　@ProductTypeEnum.”株式” 
            //  信用取引区分：　@サービス中で取得した建株.弁済区分
            EqtypeContractRow l_contractRow = (EqtypeContractRow)l_contract.getDataSourceObject();
            String[] l_strTradeCloseMarket = WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
                (WEB3GentradeBranch)l_subAccount.getMainAccount().getBranch(),
                ProductTypeEnum.EQUITY,
                l_contractRow.getRepaymentType());
            
            //18 拡張株式注文マネージャで、createNewOrderId()を調用
            long l_lngNewOrderId = l_orderManager.createNewOrderId();
            
            log.debug("============ validateOrder：プロパティ設定 ============");
            //19 プロパティ設定
            // 確認時発注日
            l_response.checkDate = WEB3DateUtility.toDay(WEB3GentradeTradingTimeManagement.getOrderBizDate());
            // 概算受渡代金
            l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(l_dblSwapPrice);
            // 取引終了警告市場コード一覧
            l_response.messageSuspension = l_strTradeCloseMarket;
            // 建株明細一覧
            l_response.contractUnits = l_contractUnits;
            // 注文ID
            l_response.orderId = Long.toString(l_lngNewOrderId);
            
            //インサイダー警告表示フラグ
            l_response.insiderWarningFlag = l_orderManager.isInsiderMessageSuspension(
                l_subAccount, l_eqtypeContractRow.getProductId());
                
            //レスポンス.注意文言表示区分
            //レスポンス.預り金不足額
            if (l_tpResult != null)
            {
                l_response.attentionObjectionType = l_tpResult.getAttentionObjectionType();
                if (WEB3TPResultAttentionObjectionTypeDef.LACK_ACC_BAL_ATTENTION.equals(
                    l_tpResult.getAttentionObjectionType()))
                {
                    l_response.accountBalanceInsufficiency =
                        WEB3StringTypeUtility.formatNumber(l_tpResult.getLackAccountBalance());
                }
            }
            log.exiting(STR_METHOD_NAME);
            
            return l_response;
        }
        catch (NotFoundException l_nfe)
        {
            log.error(l_nfe.getMessage(),l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_nfe.getMessage(), l_nfe);
        }
    }
    
    /**
     * (submit注文)。<BR>
     * <BR>
     * 信用取引現引現渡注文登録を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（信用取引現引現渡サービス）submit注文」参照。<BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3MarginSwapMarginCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 405692BD0077
     */
    protected WEB3MarginSwapMarginCompleteResponse submitOrder(
        WEB3MarginSwapMarginCompleteRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "submitOrder(WEB3MarginSwapMarginCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {   
            log.error("parameter is null type");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                   this.getClass().getName() + "." + STR_METHOD_NAME);                 
        }
        
        log.debug("シーケンス図「（信用取引現引現渡サービス）submit注文」参照");
        //2 信用取引現引現渡注文完了リクエストで、validate()を調用
        l_request.validate();
        
        //3 get補助口座()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();        
                
        //4 信用取引現引現渡サービスImplで、get代理入力者()を取得するを調用
        Trader l_trader = this.getTrader();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        try
        {
            log.debug("======== submitOrder：信用現引現渡注文内容作成 開始 ========");
            
            WEB3MarginSwapMarginRequestAdapter l_requestAdapter =
                this.createRequestAdapter(l_request);
            
            WEB3EquityContract l_contract = l_requestAdapter.getContract();
            EqtypeContractRow l_eqtypeContractRow =
                (EqtypeContractRow)l_contract.getDataSourceObject();
            
            WEB3GentradeFinObjectManager l_objectManager = (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_market = l_objectManager.getMarket(l_contract.getMarketId());
            String l_strMarketCode = l_market.getMarketCode();
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
            
            EqTypeSettleContractOrderEntry[] l_eqTypeSettleContractOrderEntrys =
                this.createClosingContractEntry(
                    l_request.closeMarginContractUnits,
                    l_requestAdapter);
              
            TaxTypeEnum l_taxType = l_eqtypeContractRow.getTaxType();
            TaxTypeEnum l_swapTaxType = l_requestAdapter.getSwapTaxType();
            
            WEB3MarginSwapContractOrderSpec l_contractOrderSpec = WEB3MarginSwapContractOrderSpec.createSwapMarginOrderSpec(
                l_trader,
                l_eqTypeSettleContractOrderEntrys,
                l_request.closingOrder,
                l_taxType,
                l_swapTaxType);
            
            log.debug("======== submitOrder：信用現引現渡注文内容作成 終了 ========");
            
            if (l_request.checkDate == null)
            {
                l_request.checkDate =
                    WEB3GentradeTradingTimeManagement.getOrderBizDate();
            }
            WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
            
            this.validateSwapContractOrder(l_subAccount,l_contractOrderSpec, l_requestAdapter);

            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

            //validate機@構預託同意(補助口座)
            //補助口座：　@get補助口座()の戻り値
            l_orderManager.validateMechanismDepositAgree(l_subAccount);

            double l_totalQuantity = l_contractOrderSpec.getTotalQuantity();
            double l_dblSwapPrice = this.getEstimatedSwapPrice(
                l_eqTypeSettleContractOrderEntrys,
                l_totalQuantity,
                l_requestAdapter);
            
            //12 （分岐フロー：建株.建区分＝”売建”（＝現渡注文）の場合のみ）
            double l_dblCalcCapitaGain = 0;
            double l_dblCalcCapitalGainTax = 0;
            WEB3EquityBizLogicProvider l_logicProvide = (WEB3EquityBizLogicProvider)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getBizLogicProvider();
            if(ContractTypeEnum.SHORT.equals(l_eqtypeContractRow.getContractType()))
            {
                //13 calc譲渡損益(double, double, long, SubAccount, TaxTypeEnum)(株式計算サービス::calc譲渡損益)
                //引数は以下の通りに設定する。
                //  金額：　@calc概算受渡代金（現引現渡）( ) 
                //  売数量：　@信用現引現渡注文内容.getTotalQuantity( ) 
                //  銘柄ID：　@株式ポジションマネージャ.get建株( )で取得した建株.銘柄ID 
                //  補助口座：　@this.get補助口座( ) 
                //  税区分：　@信用現引現渡注文内容.get税区分（現引現渡）( ) 
                l_dblCalcCapitaGain = l_logicProvide.calcCapitaGain(
                    l_dblSwapPrice,
                    l_contractOrderSpec.getTotalQuantity(),
                    l_contract.getProduct().getProductId(),
                    l_subAccount,
                    l_contractOrderSpec.getSwapTaxType());

                //14 getTradedProduct()
                TradedProduct l_tradedProduct = l_contract.getTradedProduct();
                
                //15 calc譲渡益税(TaxTypeEnum, double, Timestamp)(株式計算サービス::calc譲渡益税)
                //引数は以下の通りに設定する。
                //  l_subAccount 
                //  税区分：　@信用現引現渡注文内容.get税区分（現引現渡）( ) 
                //  金額：　@株式計算サービス.calc譲渡損益( ) 
                //  基準日：　@建株.getTradedProduct( )で取得した取引銘柄.getDailyDeliveryDate( )（＝受渡日） 
                //　@顧客税区分：　@顧客.get受渡日税区分( )
                Timestamp l_tsDailyDeliveryDate =
                    new Timestamp(l_tradedProduct.getDailyDeliveryDate().getTime());
                WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
                TaxTypeEnum l_deliveryDateTaxType = l_mainAccount.getDeliveryDateTaxType(l_tsDailyDeliveryDate);
                
                l_dblCalcCapitalGainTax = l_logicProvide.calcCapitalGainTax(
                    l_subAccount,
                    l_contractOrderSpec.getSwapTaxType(),
                    l_dblCalcCapitaGain,
                    l_tsDailyDeliveryDate,
                    l_deliveryDateTaxType);
            }
            
            this.validateTradingPower(
                l_subAccount,
                l_contractOrderSpec,
                (WEB3EquityTradedProduct)l_contract.getTradedProduct(),
                l_requestAdapter,
                l_dblSwapPrice,
                l_dblCalcCapitaGain,
                l_dblCalcCapitalGainTax,
                true);
            
            log.debug("================ データベースを更新●開始 ================");
            if (l_request.orderId == null)
            {
                l_request.orderId =
                    String.valueOf(l_orderManager.createNewOrderId());
            }
            this.submitSwapContractOrder(
                l_subAccount,
                l_contractOrderSpec,
                Long.parseLong(l_request.orderId),
                l_request.password,
                l_requestAdapter,
                l_dblSwapPrice,
                l_dblCalcCapitaGain,
                l_dblCalcCapitalGainTax);
            
            log.debug("================ データベースを更新●終了 ================");
           
            //20 信用取引現引現渡注文完了リクエストで、createResponse()をコール
            WEB3MarginSwapMarginCompleteResponse l_response = (WEB3MarginSwapMarginCompleteResponse)l_request.createResponse();

            log.debug("========== submitOrder：22 プロパティセット ==========");
            l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
            l_response.orderActionId = l_request.orderId;
            l_response.insiderWarningFlag =
                l_orderManager.isInsiderMessageSuspension(
                    l_subAccount,
                    l_contract.getProduct().getProductId());
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (NotFoundException l_nfe)
        {
            log.error(l_nfe.getMessage(),l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_nfe.getMessage(), l_nfe);
        }
    }
    
    /**
     * (createリクエストアダプタ)<BR>
     * リクエストアダプタのインスタンスを生成する。<BR>
     * <BR>
     * 信用取引現引現渡リクエストアダプタ.create(引数のリクエスト)をコールする。<BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエストデータ。
     * @@return WEB3MarginSwapMarginRequestAdapter
     */
    protected WEB3MarginSwapMarginRequestAdapter createRequestAdapter(WEB3GenRequest l_request)
    {
        final String STR_METHOD_NAME = "createRequestAdapter(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MarginSwapMarginRequestAdapter l_requestAdaptor =
            WEB3MarginSwapMarginRequestAdapter.create(l_request);
        
        log.exiting(STR_METHOD_NAME);
        return l_requestAdaptor;
    }
    
    /**
     * (create決済建株エントリ)<BR>
     * 決済建株エントリを作成する。<BR>
     * <BR>
     * 拡張株式注文マネージャ.create決済建株エントリ()をコールする。<BR>
     * <BR>
     * [create決済建株エントリ()に指定する引数]<BR>
     * 注文単位ID：　@0(新規注文)<BR>
     * 注文株数：　@パラメータ.リクエストアダプタ.get注文株数()戻り値<BR>
     * 決済建株明細一覧[]：　@パラメータ.決済建株明細一覧[]<BR>
     * @@param l_closeMarginContractUnits - (決済建株明細一覧)<BR>
     * 信用取引決済建株オブジェクトの配列。<BR>
     * （リクエストデータ）
     * @@param l_requestAdaptor - (リクエストアダプタ)<BR>
     * 信用取引現引現渡リクエストアダプタオブジェクト。
     * @@return EqTypeSettleContractOrderEntry[]
     * @@throws WEB3BaseException
     */
    protected EqTypeSettleContractOrderEntry[] createClosingContractEntry(
        WEB3MarginCloseMarginContractUnit[] l_closeMarginContractUnits,
        WEB3MarginSwapMarginRequestAdapter l_requestAdaptor)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createClosingContractEntry(WEB3MarginCloseMarginContractUnit[], WEB3MarginSwapMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        EqTypeSettleContractOrderEntry[] l_settleContractOrderEntrys =
            l_orderManager.createClosingContractEntry(
                0L,
                l_requestAdaptor.getOrderQuantity(),
                l_closeMarginContractUnits);
        
        log.exiting(STR_METHOD_NAME);
        return l_settleContractOrderEntrys;
    }
    
    /**
     * (get概算受渡代金（現引現渡）)<BR>
     * 概算受渡代金（現引現渡）を取得する。<BR>
     * <BR>
     * 拡張株式注文マネージャ.calc概算受渡代金（現引現渡）()をコールする。<BR>
     * <BR>
     * [概算受渡代金（現引現渡）()にセットするパラメータ]<BR>
     * 　@決済建株エントリ： 　@パラメータの同項目<BR>
     * 　@数量：　@パラメータの同項目<BR>
     * 　@注文単位：　@null<BR>
     * @@param l_entrys - (決済建株エントリ)<BR>
     * 決済建株エントリの配列。
     * @@param l_dblQuantity - (数量)<BR>
     * 数量。
     * @@param l_requestAdaptor - (リクエストアダプタ)<BR>
     * 信用取引現引現渡リクエストアダプタ。
     * @@return double
     * @@throws WEB3BaseException
     */
    protected double getEstimatedSwapPrice(
        EqTypeSettleContractOrderEntry[] l_entrys,
        double l_dblQuantity,
        WEB3MarginSwapMarginRequestAdapter l_requestAdaptor)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getEstimatedSwapPrice(EqtypeSettleContractOrderEntry[], double, WEB3MarginSwapMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        double l_dblEstimatedSwapPrice =
            l_orderManager.calcEstimatedSwapPrice(
                l_entrys,
                l_dblQuantity,
                null);
        
        log.exiting(STR_METHOD_NAME);
        return l_dblEstimatedSwapPrice;
    }
    
    /**
     * (create建株明細一覧)<BR>
     * 決済建株エントリより信用取引建株明細の一覧を<BR>
     * 作成する。<BR>
     * <BR>
     * １）　@ArrayListを生成する。<BR>
     * <BR>
     * ２）　@パラメータ.決済建株エントリの要素数分、<BR>
     * 　@以下の処理を実施する。<BR>
     * 　@２－１）　@株式ポジションマネージャ.get建株()にて<BR>
     * 　@　@建株を取得する。<BR>
     * <BR>
     * 　@　@[get建株()に指定する引数]<BR>
     * 　@　@　@建株ID：　@処理対象の要素.getContractId()<BR>
     * <BR>
     * 　@２－２）　@信用取引建株明細インスタンスを生成する。<BR>
     * <BR>
     * 　@２－３）　@生成したインスタンスに以下のプロパティをセットする。<BR>
     * 　@　@ID：　@建株.建株ID<BR>
     * 　@　@建日 = 建株.建日<BR>
     * 　@　@建単価 = 建株.建単価<BR>
     * 　@　@建株数 = 建株.建株数<BR>
     * 　@　@建代金 = 建株.get建代金(注文株数(*1))<BR>
     * 　@　@評価損益 =<BR>
     * 　@　@　@建株.get評価損益（建株諸経費考慮）(パラメータ.計算単価, 注文株数)<BR>
     * 　@　@注文株数 = 注文株数<BR>
     * 　@　@内出来数量 = null<BR>
     * 　@　@決済順位 = index + 1<BR>
     * <BR>
     * 　@　@(*1)注文株数・・・処理対象の要素.getQuantity()<BR>
     * 　@　@<BR>
     * 　@２－４）　@ArrayListにプロパティセットしたインスタンスを追加する。<BR>
     * <BR>
     * ３）　@ArrayList.toArray()の戻り値を返却する。<BR>
     * @@param l_settleContractOrderEntrys - (決済建株エントリ)<BR>
     * 決済建株エントリの配列。
     * @@param l_dblUnitPrice - (計算単価)<BR>
     * 計算単価。
     * @@param l_requestAdaptor - (リクエストアダプタ)<BR>
     * 信用取引現引現渡リクエストアダプタオブジェクト。
     * @@return WEB3MarginContractUnit[]
     * @@throws WEB3BaseException
     */
    protected WEB3MarginContractUnit[] createMarginContractUnitList(
        EqTypeSettleContractOrderEntry[] l_settleContractOrderEntrys,
        double l_dblUnitPrice,
        WEB3MarginSwapMarginRequestAdapter l_requestAdaptor)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createMarginContractUnitList(EqTypeSettleContractOrderEntry[], double, WEB3MarginSwapMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        List l_lstContractUnit = new ArrayList();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
        int l_intOrderEntryLength = 0;
        if (l_settleContractOrderEntrys != null)
        {
            l_intOrderEntryLength = l_settleContractOrderEntrys.length;
        }
        for (int i = 0; i < l_intOrderEntryLength; i++)
        {
            WEB3EquityContract l_equityContract = null;
            try
            {
                l_equityContract =
                    (WEB3EquityContract)l_positionManager.getContract(l_settleContractOrderEntrys[i].getContractId());
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
            
            WEB3MarginContractUnit l_contractUnit = new WEB3MarginContractUnit();
            l_contractUnit.id = Long.toString(l_equityContract.getContractId());
            l_contractUnit.openDate = WEB3DateUtility.toDay(l_equityContract.getOpenDate());
            double l_dblContractPrice = l_equityContract.getContractPrice();
            if (Double.isNaN(l_dblContractPrice))
            {
                l_dblContractPrice = 0D;
            }
            l_contractUnit.contractPrice = WEB3StringTypeUtility.formatNumber(l_dblContractPrice);
            double l_dblContractQuantity = l_equityContract.getQuantity();
            if (Double.isNaN(l_dblContractQuantity))
            {
                l_dblContractPrice = 0D;
            }
            l_contractUnit.contractQuantity = WEB3StringTypeUtility.formatNumber(l_dblContractQuantity);
            double l_dblQuantity = l_settleContractOrderEntrys[i].getQuantity();
            if (Double.isNaN(l_dblQuantity))
            {
                l_dblQuantity = 0D;
            }
            l_contractUnit.contractExecPrice = WEB3StringTypeUtility.formatNumber(
                l_equityContract.getContractAmount(l_dblQuantity));
            l_contractUnit.appraisalProfitLoss =
                WEB3StringTypeUtility.formatNumber(
                    l_equityContract.getAppraisalProfitOrLossExpenses(
                        l_dblUnitPrice, l_dblQuantity));
            l_contractUnit.orderQuantity = WEB3StringTypeUtility.formatNumber(l_dblQuantity);
            l_contractUnit.partContQuantity = null;
            l_contractUnit.settlePriority = Integer.toString(i + 1);
            
            l_lstContractUnit.add(l_contractUnit);
        }
        
        WEB3MarginContractUnit[] l_contractUnits =
            new WEB3MarginContractUnit[l_lstContractUnit.size()];
        l_lstContractUnit.toArray(l_contractUnits);
        
        log.exiting(STR_METHOD_NAME);
        return l_contractUnits;
    }
    
    /**
     * (validate現引現渡注文)<BR>
     * 信用取引現引現渡注文発注審査を行う。<BR>
     * <BR>
     * 拡張株式注文マネージャ.validate現引現渡注文()<BR>
     * をコールする。<BR>
     * <BR>
     * [validate現引現渡注文()に指定する引数]<BR>
     * 　@補助口座：　@パラメータ.補助口座<BR>
     * 　@信用現引現渡注文内容：　@パラメータ.信用現引現渡注文内容<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。
     * @@param l_orderSpec - (信用現引現渡注文内容)<BR>
     * 信用現引現渡注文内容オブジェクト。
     * @@param l_requestAdaptor - (リクエストアダプタ)<BR>
     * 信用取引現引現渡リクエストアダプタオブジェクト。
     * @@throws WEB3BaseException
     */
    protected void validateSwapContractOrder(
        WEB3GentradeSubAccount l_subAccount,
        WEB3MarginSwapContractOrderSpec l_orderSpec,
        WEB3MarginSwapMarginRequestAdapter l_requestAdaptor)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateSwapContractOrder(WEB3GentradeSubAccount, WEB3MarginSwapContractOrderSpec, WEB3MarginSwapMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        EqTypeNewOrderValidationResult l_result =
            l_orderManager.validateSwapContractOrder(l_subAccount, l_orderSpec);
        if (l_result.getProcessingResult().isFailedResult())
        {
            log.debug("ProcessingResult() = " + l_result.getProcessingResult());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_result.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (submit現引現渡注文)<BR>
     * 信用取引現引現渡注文を登録する。<BR>
     * <BR>
     * 拡張株式注文マネージャ.submitSwapContractOrder(<BR>
     * 補助口座, 信用現引現渡注文内容, 注文ID, 取引パスワード, true（＝発注審査をスキップする）)をコールする。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。
     * @@param l_orderSpec - (信用現引現渡注文内容)<BR>
     * 信用現引現渡注文内容オブジェクト。
     * @@param l_lngOrderId - (注文ID)<BR>
     * 注文ID。
     * @@param l_strTradingPassword - (取引パスワード)<BR>
     * 取引パスワード。
     * @@param l_requestAdaptor - (現引現渡リクエストアダプタ)<BR>
     * 信用取引現引現渡リクエストアダプタオブジェクト。
     * @@param l_dblEstimatedPrice - (概算受渡代金)<BR>
     * 概算受渡代金。
     * @@param l_dblCapitalGain - (譲渡益金額)<BR>
     * 譲渡益金額。
     * @@param l_dblCapitalGainTax - (譲渡益税額)<BR>
     * 譲渡益税額。
     * @@throws WEB3BaseException
     */
    protected void submitSwapContractOrder(
        WEB3GentradeSubAccount l_subAccount,
        WEB3MarginSwapContractOrderSpec l_orderSpec,
        long l_lngOrderId,
        String l_strTradingPassword,
        WEB3MarginSwapMarginRequestAdapter l_requestAdaptor,
        double l_dblEstimatedPrice,
        double l_dblCapitalGain,
        double l_dblCapitalGainTax)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitSwapContractOrder(WEB3GentradeSubAccount, WEB3MarginSwapContractOrderSpec, long, String, WEB3MarginSwapMarginRequestAdapter, double, double, double)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        EqTypeOrderSubmissionResult l_result =
            l_orderManager.submitSwapContractOrder(
                l_subAccount,
                l_orderSpec,
                l_lngOrderId,
                l_strTradingPassword,
                true);
        if (l_result.getProcessingResult().isFailedResult())
        {
            log.debug("ProcessingResult() = " + l_result.getProcessingResult());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_result.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate取引余力)<BR>
     * 取引余力をチェックし、取引余力結果オブジェクトを返却する。<BR>
     * シーケンス図「（信用取引現引現渡サービス）validate取引余力」を参照。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。
     * @@param l_orderSpec - (信用現引現渡注文内容)<BR>
     * 信用現引現渡注文内容オブジェクト。
     * @@param l_tradedProduct - (取引銘柄)<BR>
     * 取引銘柄オブジェクト。
     * @@param l_requestAdaptor - (現引現渡リクエストアダプタ)<BR>
     * 信用取引現引現渡リクエストアダプタオブジェクト。
     * @@param l_dblEstimatedPrice - (概算受渡代金)<BR>
     * 概算受渡代金。
     * @@param l_dblCapitalGain - (譲渡益金額)<BR>
     * 譲渡益金額。
     * @@param l_dblCapitalGainTax - (譲渡益税額)<BR>
     * 譲渡益税額。
     * @@param l_blnUpdateFlg - (余力更新フラグ)<BR>
     * 余力更新フラグ。<BR>
     * （false：　@確認時、true：　@完了時）
     * @@return WEB3TPTradingPowerResult
     * @@throws WEB3BaseException
     */
    protected WEB3TPTradingPowerResult validateTradingPower(
        WEB3GentradeSubAccount l_subAccount,
        WEB3MarginSwapContractOrderSpec l_orderSpec,
        WEB3EquityTradedProduct l_tradedProduct,
        WEB3MarginSwapMarginRequestAdapter l_requestAdaptor,
        double l_dblEstimatedPrice,
        double l_dblCapitalGain,
        double l_dblCapitalGainTax,
        boolean l_blnUpdateFlg)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateTradingPower(WEB3GentradeSubAccount, WEB3MarginSwapContractOrderSpec, WEB3EquityTradedProduct, WEB3MarginSwapMarginRequestAdapter, double, double, double, boolean)";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityContract l_contract = l_requestAdaptor.getContract();
        EqtypeContractRow l_contractRow =
            (EqtypeContractRow)l_contract.getDataSourceObject();
        BranchMarketRepayDealtCondRow l_BranchMarketRepayDealtCondRow
            = (BranchMarketRepayDealtCondRow)l_contract.getBranchMarketRepayDealtCond().getDataSourceObject();
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
        String l_orderRootDiv =
            l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV);
        
        WEB3MarginSwapMarginUpdateInterceptor l_interceptor = new WEB3MarginSwapMarginUpdateInterceptor(
            l_orderSpec,
            l_BranchMarketRepayDealtCondRow.getSonarRepaymentType(),
            l_dblEstimatedPrice,
            l_contractRow.getRepaymentType(),
            l_contractRow.getRepaymentNum(),
            l_dblCapitalGain,
            l_dblCapitalGainTax,
            this.getLoginChannel(),
            l_orderRootDiv);
        
        Object[] l_interceptors = { l_interceptor };
        Object[] l_orderSpecs = { l_orderSpec };
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        WEB3TPTradingPowerService l_tradingPowerService
            = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        WEB3TPTradingPowerResult l_tpResult =
            l_tradingPowerService.validateTradingPower(
                l_subAccount,
                l_interceptors,
                l_orderSpecs, 
                l_contract.isLong() ? OrderTypeEnum.SWAP_MARGIN_LONG : OrderTypeEnum.SWAP_MARGIN_SHORT,
                l_blnUpdateFlg);
        if (l_tpResult.isResultFlg() == false)
        {
            // 二階建チェックエラーの場合
            if (l_tpResult.getTpErrorInfo().tradinPowerErrorDiv.equals(WEB3TPTradingPowerErrorDivDef.MARGIN_SEC_ERROR))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01928,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            // 二階建チェックエラー以外の場合(預り金不足)
            else
            {
                // 現引注文の場合
                if (l_contract.isLong() == true)
                {
                    String l_strLackAccountBalanceInfo =
                        l_orderManager.getLackAccountBalanceInfoBuy(l_tpResult);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01929,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_strLackAccountBalanceInfo);
                }
                // 現渡注文の場合
                else
                {
                    String l_strLackAccountBalanceInfo =
                        l_orderManager.getLackAccountBalanceInfoSell(
                            l_tpResult,
                            l_orderSpec.getTotalQuantity());
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01930,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_strLackAccountBalanceInfo);
                }
            }
        }
        
        if (l_blnUpdateFlg)
        {
            l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_tpResult;
    }
}
@
