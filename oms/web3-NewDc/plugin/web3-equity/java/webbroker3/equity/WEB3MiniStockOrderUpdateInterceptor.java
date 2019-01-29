head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MiniStockOrderUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式ミニ投資注文更新インタセプタ(WEB3MiniStockOrderUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/14 カク寛新 (中訊) 新規作成
                   2004/12/10 水落 (SRA) 残案件対応
                   2004/12/29 岡村 (SRA) JavaDoc修正
                   2006/07/19 李俊 (中訊) ＤＢ更新仕様158
                   2006/11/14 唐性峰 (中訊)　@モデルNo.1026
*/

package webbroker3.equity;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.equity.define.WEB3MarginBaseNumber;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式ミニ投資注文更新インタセプタ）。<BR>
 * <BR>
 * 株式ミニ投資注文更新インタセプタクラス
 * @@author カク寛新
 * @@version 1.0
 */
public class WEB3MiniStockOrderUpdateInterceptor extends WEB3MarginUpdateEventInterceptor 
{
    /**
     * （ログユーティリティ）。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MiniStockOrderUpdateInterceptor.class);
    
    /**
     * （ミニ株注文内容）。<BR>
     * <BR>
     * ミニ株注文内容オブジェクト
     */
    private WEB3NewMiniStockOrderSpec mstkOrderSpec;
    
    /**
     * （手数料）。<BR>
     * <BR>
     * 手数料オブジェクト
     */
    private WEB3GentradeCommission commission;
    
    /**
     * （概算受渡代金計算結果）。<BR>
     * <BR>
     * 概算受渡代金計算結果オブジェクト
     */
    private WEB3EquityEstimatedDeliveryPrice estimatedDeliveryPrice;
    
    /**
     * （株式ミニ投資注文更新インタセプタ）。<BR>
     * <BR>
     * コンストラクタ
     */
    public WEB3MiniStockOrderUpdateInterceptor() 
    {
     
    }
    
    /**
     * （株式ミニ投資注文更新インタセプタ）。<BR>
     * <BR>
     * コンストラクタ。<BR>
     * 引数に指定されたオブジェクト／値を、同名のプロパティに設定する。
     * @@param l_mstkOrderSpec （ミニ株注文内容）<BR>
     * ミニ株注文内容オブジェクト
     * @@param l_commission （手数料）<BR>
     * 手数料オブジェクト
     * @@param l_estimatedDeliveryPrice （概算受渡代金計算結果）<BR>
     * 概算受渡代金計算結果
     */
    public WEB3MiniStockOrderUpdateInterceptor(
        WEB3NewMiniStockOrderSpec l_mstkOrderSpec, 
        WEB3GentradeCommission l_commission, 
        WEB3EquityEstimatedDeliveryPrice l_estimatedDeliveryPrice) 
    {
        
        //引数に指定されたオブジェクト／値を、同名のプロパティに設定する
        this.mstkOrderSpec = l_mstkOrderSpec;
        
        //ミニ株注文内容オブジェクト
        this.commission = l_commission;
        
        //手数料オブジェクト
        this.estimatedDeliveryPrice = l_estimatedDeliveryPrice;
     
    }
    
    /**
     * （更新値設定）。<BR>
     * <BR>
     * （mutateメソッドの実装）<BR>
     * 注文単位Paramsに拡張項目(*)設定し返却する。<BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     * (*) xTradeマニュアル「更新内容のカスタマイズ」参照。<BR>
     * <BR>
     * １） 拡張項目セット<BR>
     * 　@プロパティから、パラメータ.注文単位Paramsの拡張項目に値をセットし、返却する。<BR>
     * <BR>
     * 更新内容はDB設定論理「ミニ株注文_株式注文単位テーブル.xls」参照。<BR>
     * @@param l_mutate - (更新タイプ)<BR>
     * INSERTまたは、UPDATE。<BR>
     * <BR>
     * EqTypeOrderManagerPersistenceTypeにて定数定義。<BR>
     * <BR>
     * @@param l_context （処理）<BR>
     * （EqTypeOrderManagerPersistenceContextにて定数定義）
     * @@param l_eqtypeOrderUnitParams （注文単位Params）<BR>
     * 株式注文単位が保持する項目のパラメータクラス。
     * @@return EqtypeOrderUnitParams
     */
    public EqtypeOrderUnitParams mutate(
        OrderManagerPersistenceType l_mutate, 
        OrderManagerPersistenceContext l_context, 
        EqtypeOrderUnitParams l_eqtypeOrderUnitParams) 
    {
        
        final String STR_METHOD_NAME = 
            " mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, qtypeOrderUnitParams)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        
        //値段条件
        l_eqtypeOrderUnitParams.setPriceConditionType(WEB3PriceConditionDef.DEFAULT);
        //発注条件
        l_eqtypeOrderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.DEFAULT);
        //発注条件演算子
        l_eqtypeOrderUnitParams.setOrderCondOperator(null);
        //逆指値基準値
        l_eqtypeOrderUnitParams.setStopOrderPrice(null);
        //（W指値）訂正指値
        l_eqtypeOrderUnitParams.setWLimitPrice(null);
        //税区分（現引現渡）
        l_eqtypeOrderUnitParams.setSwapTaxType(TaxTypeEnum.UNDEFINED);
        //弁済区分
        l_eqtypeOrderUnitParams.setRepaymentType(null);
        //弁済期限値
        l_eqtypeOrderUnitParams.setRepaymentNum(null);
        //弁済区分（SONAR）
        l_eqtypeOrderUnitParams.setSonarRepaymentType(null);
        //初回注文の注文チャネル
        l_eqtypeOrderUnitParams.setOrderChanel(commission.getOrderChannel());
        //受注日時
        l_eqtypeOrderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());
        //識別コード
        WEB3HostReqOrderNumberManageService l_WEB3HostReqOrderNumberManageService=
        (WEB3HostReqOrderNumberManageService)Services.getService(WEB3HostReqOrderNumberManageService.class);            
        MainAccount l_mainAccount ;
        String l_orderRequestNumber;
        try 
        {
            l_mainAccount = l_finApp.getAccountManager().getMainAccount(l_eqtypeOrderUnitParams.getAccountId());
            l_orderRequestNumber = l_WEB3HostReqOrderNumberManageService.getNewNumber(
                l_mainAccount.getInstitution().getInstitutionCode(),
                l_mainAccount.getBranch().getBranchCode(),
                ProductTypeEnum.EQUITY);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(l_ex.getErrorInfo(),this.getClass().getName()+  "." + STR_METHOD_NAME);
        }
        catch(NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80006,this.getClass().getName()+  "." + STR_METHOD_NAME);
        }           
        l_eqtypeOrderUnitParams.setVoucherNo(WEB3MarginBaseNumber.BaseNumber + l_orderRequestNumber.substring(l_orderRequestNumber.length() - 3, l_orderRequestNumber.length()));
        log.debug("伝票No:" + l_eqtypeOrderUnitParams.getVoucherNo());
        //初回注文の手数料No  
        l_eqtypeOrderUnitParams.setCommTblNo(commission.getCommissionNo());
        //初回注文の手数料No枝番
        l_eqtypeOrderUnitParams.setCommTblSubNo(commission.getCommissionRevNo());
        //扱者コード（SONAR）
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
        l_eqtypeOrderUnitParams.setSonarTraderCode(l_mainAccountRow.getSonarTraderCode());
        //注文単価
        l_eqtypeOrderUnitParams.setPrice(estimatedDeliveryPrice.getCalcUnitPrice());
        //識別コード
        l_eqtypeOrderUnitParams.setOrderRequestNumber(l_orderRequestNumber);
        //概算受渡代金
        l_eqtypeOrderUnitParams.setEstimatedPrice(estimatedDeliveryPrice.getEstimateDeliveryAmount());
        //譲渡益金額
        l_eqtypeOrderUnitParams.setCapitalGain(null);
        //譲渡益税額
        l_eqtypeOrderUnitParams.setCapitalGainTax(null);
        //取引コード（SONAR）
        String l_strSonarTradedCode = commission.getSonarTradedCode();
        l_eqtypeOrderUnitParams.setSonarTradedCode(l_strSonarTradedCode);
        //市場コード（SONAR）
        try
        {
            Market l_market = l_finApp.getFinObjectManager().getMarket(l_eqtypeOrderUnitParams.getMarketId());
            MarketRow l_marketRow = (MarketRow)l_market.getDataSourceObject();
            l_eqtypeOrderUnitParams.setSonarMarketCode(l_marketRow.getSonarMarketCode());
        }
        catch (NotFoundException l_notFoundExp)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,STR_METHOD_NAME);
        }
        //手数料商品コード
        l_eqtypeOrderUnitParams.setCommProductCode(commission.getCommissionProductCode());
        //注文訂正・取消区分
        l_eqtypeOrderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
        //注文経路区分
        OpLoginSecurityService l_securityService =
            (OpLoginSecurityService)Services.getService(
                OpLoginSecurityService.class);
        String l_strOrderRootDiv =
            l_securityService.getSessionProperty(
                WEB3SessionAttributeDef.ORDER_ROOT_DIV);
        l_eqtypeOrderUnitParams.setOrderRootDiv(l_strOrderRootDiv);
        //市場から確認済みの注文単価
        l_eqtypeOrderUnitParams.setConfirmedOrderPrice(null);
        //市場から確認済みの概算受渡代金
        l_eqtypeOrderUnitParams.setConfirmedEstimatedPrice(null);
        //市場から確認済みの執行条件
        l_eqtypeOrderUnitParams.setConfirmedExecConditionType(null);
        //市場から確認済みの値段条件
        l_eqtypeOrderUnitParams.setConfirmedPriceConditionType(null);
        //決済順序区分
        l_eqtypeOrderUnitParams.setClosingOrderType(null);
        //注文エラー理由コード
        l_eqtypeOrderUnitParams.setErrorReasonCode("0000");
        //リクエストタイプ
        l_eqtypeOrderUnitParams.setRequestType(null);
        //初回注文の注文単位ＩＤ
        l_eqtypeOrderUnitParams.setFirstOrderUnitId(null);
        
        //元発注条件
        l_eqtypeOrderUnitParams.setOrgOrderConditionType(null);
        //元発注条件演算子
        l_eqtypeOrderUnitParams.setOrgOrderCondOperator(null);
        //元逆指値基準値
        l_eqtypeOrderUnitParams.setOrgStopOrderPrice(null);
        //元（W指値）訂正指値
        l_eqtypeOrderUnitParams.setOrgWLimitPrice(null);
        //元（W指値）執行条件
        l_eqtypeOrderUnitParams.setOrgWLimitExecCondType(null);
        //（W指値）執行条件
        l_eqtypeOrderUnitParams.setWLimitExecCondType(null);
        //（W指値）切替前指値
        l_eqtypeOrderUnitParams.setWLimitBeforeLimitPrice(null);
        //（W指値）切替前執行条件
        l_eqtypeOrderUnitParams.setWLimitBeforeExecCondType(null);
        
        log.exiting(STR_METHOD_NAME);
        return l_eqtypeOrderUnitParams;
        
    }
}
@
