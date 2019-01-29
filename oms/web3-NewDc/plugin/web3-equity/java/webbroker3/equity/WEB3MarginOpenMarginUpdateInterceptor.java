head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginOpenMarginUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用新規建更新インタセプタ(WEB3MarginOpenMarginUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/20 水落 (SRA) 新規作成
Revesion History : 2006/08/01 李俊 (中訊) ＤＢ更新仕様163
Revesion History : 2006/11/02 柴双紅 (中訊) ＤＢ更新仕様No.170,174
Revesion History : 2007/04/26 謝旋 (中訊) ＤＢ更新仕様No.199
*/

package webbroker3.equity;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeContractOpenOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ForcedExpireType;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3ShortSellOrderFlagDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.equity.define.WEB3MarginBaseNumber;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.util.WEB3LogUtility;

/**
 * （信用新規建更新インタセプタ）。<BR>
 * <BR>
 * 新規建注文登録時の、DB更新仕様カスタマイズ用のクラス。<BR>
 * （EqTypeOrderManagerPersistenceEventInterceptorの実装）<BR>
 * <BR>
 * 以下のサービスから利用される。<BR>
 * ・「信用取引新規建サービス」<BR>
 * ・「信用取引注文通知サービス」<BR>
 * ・「注文繰越サービス」<BR>
 * ・「連続注文発注サービス」
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3MarginOpenMarginUpdateInterceptor extends WEB3MarginUpdateEventInterceptor 
{
    /**
     * (ログユーティリティ)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginOpenMarginUpdateInterceptor.class);
    
    /**
     * (信用新規建注文内容)<BR>
     * 信用新規建注文内容オブジェクト。<BR>
     */
    private WEB3MarginOpenContractOrderSpec openContractOrderSpec;
    
    /**
     * (手数料)<BR>
     * 手数料オブジェクト。<BR>
     * （手数料No等の設定に使用）<BR>
     */
    private WEB3GentradeCommission commission;
    
    /**
     * (初回注文の注文チャネル。)<BR>
     * 初回注文の注文チャネル。<BR>
     */
    private String orderChanel;
    
    /**
     * (注文経路区分。)<BR>
     * 注文経路区分。<BR>
     */
    private String orderRootDiv;
    
    /**
     * (識別コード。)<BR>
     * 識別コード。 <BR>
     * 信用注文通知サービスから利用する場合のみセットして使用。以外、nullをセット。<BR>
     */
    private String orderRequestNumber = null;
    
    /**
     * (受注日時。)<BR>
     * 受注日時。 <BR>
     * 信用注文通知サービス、連続注文発注サービスから利用する場合のみセットして使用。<BR>
     * 以外、nullをセット。<BR>
     */
    private Date receivedDateTime = null;
    
    /**
     * (受渡日。)<BR>
     * 受渡日。 <BR>
     * 信用注文通知サービスから利用する場合のみセットして使用。以外、nullをセット。<BR>
     */
    private Date deliveryDate = null;
    
    /**
     * (空売り規制対象フラグ。)<BR>
     * 空売り規制対象フラグ。<BR>
     */
    private boolean shortSellingRestraint;
    
    /**
     * (繰越元の注文単位オブジェクト。)<BR>
     * 繰越元の注文単位オブジェクト。 <BR>
     * 注文繰越サービスから利用する場合のみセットして使用。以外、nullをセット。<BR>
     */
    private EqTypeContractOpenOrderUnit carryoverOrderUnit = null;
    
    /**
     * (発注経路区分。)<BR>
     * 信用注文通知サービスから利用する場合のみセットして使用。以外、nullをセット。<BR>
     */
    private String submitOrderRouteDiv;
    
    /**
     * (更新値設定)<BR>
     * （mutateメソッドの実装）<BR>
     * 注文単位Paramsに拡張項目(*)設定し返却する。<BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     * (*) xTradeマニュアル「更新内容のカスタマイズ」参照。<BR>
     * <BR>
     * １） 必須プロパティ設定チェック<BR>
     * <BR>
     * 　@当オブジェクトのプロパティのうち、<BR>
     * 　@信用新規建注文内容、手数料、初回注文の注文チャネル、注文経路区分の<BR>
     * 　@いずれか１つでもnullの場合は、<BR>
     * 　@パラメータ.注文単位Paramsを返却し、処理を終了する。<BR>
     * <BR>
     * ２） 拡張項目セット<BR>
     * <BR>
     * 　@各プロパティから、<BR>
     * 　@パラメータ.注文単位Paramsの拡張項目に値をセットし、返却する。<BR>
     * <BR>
     * 　@更新内容は、以下のDB設定論理を参照。<BR>
     * 　@・「信用新規建_株式注文単位テーブル.xls」<BR>
     * 　@・「信用取引注文通知_株式注文単位テーブル.xls」の<BR>
     * 　@　@「（信用取引注文通知）［新規建］株式注文単位テーブル」シート<BR>
     * 　@・「信用注文繰越_株式注文単位テーブル.xls」の<BR>
     * 　@　@「（信用注文繰越[繰越(新規建)]）注文単位テーブル」シート<BR>
     * <BR>
     * 　@「発注日」「受渡日」「受注日時」「識別コード」「リクエストタイプ」「発注経路区分」の設定仕様は、<BR>
     * 　@以下の通りに分岐する。<BR>
     * 　@-----------------------------------------------------------------------<BR>
     * 　@発注日       ：this.get注文経路区分( )＝"HOST"の場合のみ、現在日時のYYYYMMDDをセット。<BR>
     * 　@　@　@　@　@　@　@　@　@　@※this.get注文経路区分( )≠"HOST"の場合は、xTrade標準実装のままとする。<BR>
     * 　@受渡日       ：this.get受渡日( )≠nullの場合のみ、this.受渡日プロパティをセット。<BR>
     * 　@　@　@　@　@　@　@　@　@　@※this.get受渡日( )＝nullの場合は、xTrade標準実装のままとする。<BR>
     * 　@受注日時　@　@：this.繰越元注文単位＝nullの場合：<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@・this.get受注日時( )＝nullの場合は、GtlUtils.getSystemTimestamp( )。<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@・this.get受注日時( )≠nullの場合は、this.受注日時プロパティをセット。<BR>
     * 　@　@　@　@　@　@　@　@　@　@this.繰越元注文単位≠nullの場合：<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@・this.繰越元注文単位の同項目をセット。<BR>
     * 　@識別コード　@　@：this.get識別コード( )＝nullの場合は、<BR>
     * 　@　@　@　@　@　@　@　@　@　@注文識別コード採番サービス.get新規識別コード( )。<BR>
     * 　@　@　@　@　@　@　@　@　@　@this.get識別コード( )≠nullの場合は、this.識別コードプロパティをセット。<BR>
     * 　@リクエストタイプ：this.繰越元注文単位＝nullの場合は、信用新規建注文内容.get発注条件( )。<BR>
     * 　@　@　@　@　@　@　@　@　@　@this.繰越元注文単位≠nullの場合は、<BR>
     * 　@　@　@　@　@　@　@　@　@　@this.繰越元注文単位の内容より判定してセット。<BR>
     * 　@　@　@　@　@　@　@　@　@　@（* 判定方法@は上記DB設定論理「信用注文繰越_株式注文単位テーブル.xls」を<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@参照のこと）<BR>
     * 　@発注経路区分：this.発注経路区分＝nullの場合は、株式発注サービス.get発注経路区分( )。<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@this.発注経路区分≠nullの場合は、this.発注経路区分プロパティをセット。<BR>
     * 　@-----------------------------------------------------------------------<BR>
     * <BR>
     * @@param l_updateType - 更新タイプ<BR>
     * INSERTまたは、UPDATE。<BR>
     * EqTypeOrderManagerPersistenceTypeにて定数定義。<BR>
     * <BR>
     * @@param l_process - 処理<BR>
     * （EqTypeOrderManagerPersistenceContextにて定数定義）<BR>
     * <BR>
     * @@param l_orderUnitParams - 注文単位Params<BR>
     * 株式注文単位が保持する項目のパラメータクラス。<BR>
     * @@return EqtypeOrderUnitParams
     * @@roseuid 40B18F0B01C4
     */
    public EqtypeOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType, 
        OrderManagerPersistenceContext l_process, 
        EqtypeOrderUnitParams l_orderUnitParams) 
    {
        final String STR_METHOD_NAME = "WEB3MarginOpenMarginUpdateInterceptor:mutate()";
        log.entering(STR_METHOD_NAME);

        //１） 必須プロパティ設定チェック
        if (this.openContractOrderSpec == null
                || this.commission == null
                || this.orderChanel == null
                || this.orderRootDiv == null)
        {
            return l_orderUnitParams;
        }
        
        //２） 拡張項目セット
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        if (carryoverOrderUnit != null)
        {
            log.debug("this.繰越元注文単位プロパティ≠nullの場合●開始");
            // this.繰越元注文単位プロパティ≠nullの場合
            //  「信用注文繰越_株式注文単位テーブル.xls」の<BR>
            //  「（信用注文繰越[繰越(新規建)]）注文単位テーブル」シートを参照
            EqtypeOrderUnitRow l_orderUnitRow =
                (EqtypeOrderUnitRow)carryoverOrderUnit.getDataSourceObject();

            //値段条件
            l_orderUnitParams.setPriceConditionType(
                openContractOrderSpec.getPriceConditionType());
            
            //発注条件
            l_orderUnitParams.setOrderConditionType(l_orderUnitRow.getOrderConditionType());
            
            //発注条件演算子
            l_orderUnitParams.setOrderCondOperator(
                l_orderUnitRow.getOrderCondOperator());
            //逆指値基準値
            if (l_orderUnitRow.getStopOrderPriceIsNull())
            {
                l_orderUnitParams.setStopOrderPrice(null);
            }
            else
            {
                l_orderUnitParams.setStopOrderPrice(
                    l_orderUnitRow.getStopOrderPrice());
            }
            //（W指値）訂正指値
            if (l_orderUnitRow.getWLimitPriceIsNull())
            {
                l_orderUnitParams.setWLimitPrice(null);
            }
            else
            {
                l_orderUnitParams.setWLimitPrice(
                    l_orderUnitRow.getWLimitPrice());
            }

            //税区分（現引現渡）
            l_orderUnitParams.setSwapTaxType(TaxTypeEnum.UNDEFINED);

            //弁済区分
            l_orderUnitParams.setRepaymentType(
                openContractOrderSpec.getRepaymentType());

            //弁済期限値
            l_orderUnitParams.setRepaymentNum(
                (int)openContractOrderSpec.getRepaymentNum());
            
            //弁済区分（SONAR）
            l_orderUnitParams.setSonarRepaymentType(commission.getPayType());

            //初回注文の注文チャネル
            l_orderUnitParams.setOrderChanel(orderChanel);
            
            //受注日時
            l_orderUnitParams.setReceivedDateTime(
                l_orderUnitRow.getReceivedDateTime());

            //識別コード
            WEB3HostReqOrderNumberManageService l_WEB3HostReqOrderNumberManageService =
               (WEB3HostReqOrderNumberManageService)Services.getService(WEB3HostReqOrderNumberManageService.class);            
            MainAccount l_mainAccount = null;
            String l_orderRequestNumber= null;
            try 
            {
                l_mainAccount = l_finApp.getAccountManager().getMainAccount(l_orderUnitRow.getAccountId());
                l_orderRequestNumber = l_WEB3HostReqOrderNumberManageService.getNewNumber(
                    l_mainAccount.getInstitution().getInstitutionCode(),
                    l_mainAccount.getBranch().getBranchCode(),
                    ProductTypeEnum.EQUITY);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (NotFoundException l_nfe)
            {
                log.error(STR_METHOD_NAME, l_nfe);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_nfe.getMessage(),
                    l_nfe);
            }
            l_orderUnitParams.setOrderRequestNumber(l_orderRequestNumber);

            //伝票No
            int l_intLenth = l_orderRequestNumber.length();
            l_orderUnitParams.setVoucherNo(WEB3MarginBaseNumber.BaseNumber + 
                l_orderRequestNumber.substring(l_intLenth - 3,l_intLenth));
            
            //初回注文の手数料No
            l_orderUnitParams.setCommTblNo(commission.getCommissionNo());
            
            //初回注文の手数料No枝番
            l_orderUnitParams.setCommTblSubNo(commission.getCommissionRevNo());

            //扱者コード（SONAR）
            MainAccountRow l_mainAccountRow =
                (MainAccountRow)l_mainAccount.getDataSourceObject();
            l_orderUnitParams.setSonarTraderCode(l_mainAccountRow.getSonarTraderCode());
                  
            //注文単価
            l_orderUnitParams.setPrice(openContractOrderSpec.getCalcUnitPrice());
            
            //概算受渡代金
            l_orderUnitParams.setEstimatedPrice(
                openContractOrderSpec.getContractAmount());
            
            //譲渡益金額
            l_orderUnitParams.setCapitalGain(0D);
            
            //譲渡益税額
            l_orderUnitParams.setCapitalGainTax(0D);

            //取引コード（SONAR）
            l_orderUnitParams.setSonarTradedCode(commission.getSonarTradedCode());

            //市場コード（SONAR）
            try
            {
                WEB3GentradeFinObjectManager l_objectManager =
                    (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                Market l_market = l_objectManager.getMarket(l_orderUnitRow.getMarketId());
                MarketRow l_marketRow = (MarketRow)l_market.getDataSourceObject();
                l_orderUnitParams.setSonarMarketCode(l_marketRow.getSonarMarketCode());
            }
            catch (NotFoundException l_nfe)
            {
                log.error(STR_METHOD_NAME, l_nfe);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
            
            //手数料商品コード
            l_orderUnitParams.setCommProductCode(commission.getCommissionProductCode());
            
            //空売り規制対象フラグ
            if (this.shortSellingRestraint)
            {
                l_orderUnitParams.setShortSellOrderFlag(
                    WEB3ShortSellOrderFlagDef.PRICE_RESTRAINT);
            }
            else
            {
                l_orderUnitParams.setShortSellOrderFlag(
                    WEB3ShortSellOrderFlagDef.EXCEPT_OBJECT);                
            }

            //注文訂正・取消区分
            l_orderUnitParams.setModifyCancelType(
                WEB3ModifyCancelTypeDef.INITIAL_VALUE);

            //注文経路区分
            l_orderUnitParams.setOrderRootDiv(this.orderRootDiv);

            //発注経路区分
            if (this.submitOrderRouteDiv == null)
            {
                WEB3EquityFrontOrderService l_frontOrderService =
                    (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
                WEB3GentradeAccountManager l_accountManager =
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                WEB3EquityProductManager l_productManager =
                    (WEB3EquityProductManager)l_tradingModule.getProductManager();
                String l_strSubmitOrderRouteDiv = null;
                try
                {
                    Branch l_branch = l_accountManager.getBranch(l_orderUnitParams.getBranchId());
                    EqTypeTradedProduct l_tradedProduct =
                        (EqTypeTradedProduct)l_productManager.getTradedProduct(
                            l_orderUnitParams.getProductId(),
                            l_orderUnitParams.getMarketId());
                    l_strSubmitOrderRouteDiv =
                        l_frontOrderService.getSubmitOrderRouteDiv(
                            l_tradedProduct,
                            l_branch.getInstitution().getInstitutionCode(),
                            l_orderUnitParams.getSonarTradedCode());
                }
                catch (NotFoundException l_nfe)
                {
                    log.error(l_nfe.getMessage(), l_nfe);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                        this.getClass().getName() + STR_METHOD_NAME, 
                        l_nfe.getMessage(), l_nfe);
                }
                catch (WEB3BaseException l_wbe)
                {
                    log.error(l_wbe.getMessage(), l_wbe);
                    throw new WEB3BaseRuntimeException(
                        l_wbe.getErrorInfo(), 
                        this.getClass().getName() + STR_METHOD_NAME, 
                        l_wbe.getMessage(), l_wbe);
                }
                l_orderUnitParams.setSubmitOrderRouteDiv(l_strSubmitOrderRouteDiv);
            }
            else
            {
                l_orderUnitParams.setSubmitOrderRouteDiv(this.submitOrderRouteDiv);
            }
            
            //市場から確認済みの注文単価
            l_orderUnitParams.setConfirmedOrderPrice(null);                
            
            //市場から確認済みの概算受渡代金
            l_orderUnitParams.setConfirmedEstimatedPrice(null);
            
            //市場から確認済みの執行条件
            l_orderUnitParams.setConfirmedExecConditionType(null);
            
            //市場から確認済みの値段条件
            l_orderUnitParams.setConfirmedPriceConditionType(null);
            
            //決済順序区分
            l_orderUnitParams.setClosingOrderType(null);
            
            //注文エラー理由コード
            l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
            
            //リクエストタイプ
            String l_strOrderConditionType = l_orderUnitParams.getOrderConditionType();
            if (WEB3OrderingConditionDef.DEFAULT.equals(l_strOrderConditionType))
            {
				l_orderUnitParams.setRequestType(l_orderUnitRow.getRequestType());
            }
            else
            {
				l_orderUnitParams.setRequestType(WEB3RequestTypeDef.DEFAULT);
            }

            //注文Rev.
            l_orderUnitParams.setOrderRev("00");
                
            //市場から確認済みの注文Rev.
            l_orderUnitParams.setConfirmedOrderRev("00");
            
            //初回注文の注文単位ＩＤ
            l_orderUnitParams.setFirstOrderUnitId(
                openContractOrderSpec.getFirstOrderUnitId());
                
            // 元注文数量
            l_orderUnitParams.setOriginalQuantity(l_orderUnitParams.getQuantity());
            
            //元発注条件
            l_orderUnitParams.setOrgOrderConditionType(
                l_orderUnitRow.getOrgOrderConditionType());
            
            //元発注条件演算子
            l_orderUnitParams.setOrgOrderCondOperator(
                l_orderUnitRow.getOrgOrderCondOperator());
            
            //元逆指値基準値
            if (l_orderUnitRow.getOrgStopOrderPriceIsNull())
            {
                l_orderUnitParams.setOrgStopOrderPrice(null);
            }
            else 
            {
                l_orderUnitParams.setOrgStopOrderPrice(
                    l_orderUnitRow.getOrgStopOrderPrice());
            }
            //元（W指値）訂正指値
            if (l_orderUnitRow.getOrgWLimitPriceIsNull())
            {
                l_orderUnitParams.setOrgWLimitPrice(null);
            }
            else
            {
                l_orderUnitParams.setOrgWLimitPrice(
                    l_orderUnitRow.getOrgWLimitPrice());
            }
            //元（W指値）執行条件
            l_orderUnitParams.setOrgWLimitExecCondType(
                l_orderUnitRow.getOrgWLimitExecCondType());
            //（W指値）執行条件
            l_orderUnitParams.setWLimitExecCondType(
                l_orderUnitRow.getWLimitExecCondType());
            //（W指値）切替前指値
            if (l_orderUnitRow.getWLimitBeforeLimitPriceIsNull())
            {
                l_orderUnitParams.setWLimitBeforeLimitPrice(null);
            }
            else
            {
                l_orderUnitParams.setWLimitBeforeLimitPrice(
                    l_orderUnitRow.getWLimitBeforeLimitPrice());
            }
            //（W指値）切替前執行条件
            l_orderUnitParams.setWLimitBeforeExecCondType(
                    l_orderUnitRow.getWLimitBeforeExecCondType());

            //発注遅延フラグ
            l_orderUnitParams.setSubmitOrderDelayFlag(
                l_orderUnitRow.getSubmitOrderDelayFlag());

            log.debug("this.繰越元注文単位プロパティ≠nullの場合●終了");
        }
        else
        {
            log.debug("this.繰越元注文単位プロパティ＝nullの場合●開始");
            // this.繰越元注文単位プロパティ＝nullの場合
            //  「信用新規建_株式注文単位テーブル.xls」<BR>
            //  「信用取引注文通知_株式注文単位テーブル.xls」の<BR>
            //  「（信用取引注文通知）［新規建］株式注文単位テーブル」シート<BR>            
            
            //値段条件
            l_orderUnitParams.setPriceConditionType(openContractOrderSpec.getPriceConditionType());

            //発注条件
            l_orderUnitParams.setOrderConditionType(openContractOrderSpec.getOrderConditionType());

            //発注条件演算子
            String l_strOrderConditionType = l_orderUnitParams.getOrderConditionType();
            if (WEB3OrderingConditionDef.DEFAULT.equals(l_strOrderConditionType))
            {
                l_orderUnitParams.setOrderCondOperator(null);
                l_orderUnitParams.setStopOrderPrice(null);
            }
            else
            {
                l_orderUnitParams.setOrderCondOperator(openContractOrderSpec.getOrderCondOperator());
                l_orderUnitParams.setStopOrderPrice(openContractOrderSpec.getStopOrderPrice());    
            }
            
            //（W指値）訂正指値
            if (WEB3OrderingConditionDef.DEFAULT.equals(l_strOrderConditionType)
                || WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderConditionType))
            {
                l_orderUnitParams.setWLimitPrice(null);
            }
            else
            {
                l_orderUnitParams.setWLimitPrice(openContractOrderSpec.getWLimitPrice());
            }

            //受渡日
            if (this.getDeliveryDate() != null)
            {
                l_orderUnitParams.setDeliveryDate(this.getDeliveryDate());
            }
            
            //税区分（現引現渡）
            l_orderUnitParams.setSwapTaxType(TaxTypeEnum.UNDEFINED);

            //弁済区分
            l_orderUnitParams.setRepaymentType(openContractOrderSpec.getRepaymentType());

            //弁済期限値
            l_orderUnitParams.setRepaymentNum((int)openContractOrderSpec.getRepaymentNum());
            
            //弁済区分（SONAR）
            l_orderUnitParams.setSonarRepaymentType(commission.getPayType());

            //発注日    
            if (this.orderRootDiv.equals(WEB3OrderRootDivDef.HOST))
            {
                l_orderUnitParams.setBizDate(
                    GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat()
                        .format(GtlUtils.getSystemTimestamp()));
            }

            //初回注文の注文チャネル
            l_orderUnitParams.setOrderChanel(this.orderChanel);
            
            //受注日時
            if (this.getReceivedDateTime() == null)
            {
                Timestamp l_timeStamp = GtlUtils.getTradingSystem().getSystemTimestamp();
                l_orderUnitParams.setReceivedDateTime(l_timeStamp);
            }
            else
            {
                l_orderUnitParams.setReceivedDateTime(this.receivedDateTime);
            }
            
            //識別コード
            WEB3HostReqOrderNumberManageService l_WEB3HostReqOrderNumberManageService=
            (WEB3HostReqOrderNumberManageService)Services.getService(WEB3HostReqOrderNumberManageService.class);            
            MainAccount l_mainAccount = null;
            String l_orderRequestNumber= null;
            try 
            {
                l_mainAccount = l_finApp.getAccountManager().getMainAccount(l_orderUnitParams.getAccountId());
                l_orderRequestNumber = l_WEB3HostReqOrderNumberManageService.getNewNumber(
                    l_mainAccount.getInstitution().getInstitutionCode(),
                    l_mainAccount.getBranch().getBranchCode(),
                    ProductTypeEnum.EQUITY);
            }
            catch(WEB3BaseException l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_ex.getMessage(), l_ex);
            }
            catch(NotFoundException l_nfe)
            {
                log.error(STR_METHOD_NAME, l_nfe);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_nfe.getMessage(), l_nfe);
            }           
            if (this.getOrderRequestNumber() == null)
            {             
                l_orderUnitParams.setOrderRequestNumber(l_orderRequestNumber);
            }
            else
            {
                l_orderUnitParams.setOrderRequestNumber(this.orderRequestNumber);
            }

            //伝票No
            l_orderRequestNumber = l_orderUnitParams.getOrderRequestNumber();
            int l_intLenth = l_orderRequestNumber.length();
            l_orderUnitParams.setVoucherNo(WEB3MarginBaseNumber.BaseNumber + 
                l_orderRequestNumber.substring(l_intLenth - 3,l_intLenth));
            
            //初回注文の手数料No
            l_orderUnitParams.setCommTblNo(commission.getCommissionNo());
            
            //初回注文の手数料No枝番
            l_orderUnitParams.setCommTblSubNo(commission.getCommissionRevNo());

            //扱者コード（SONAR）
            MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
            l_orderUnitParams.setSonarTraderCode(l_mainAccountRow.getSonarTraderCode());
            
            //注文単価
            l_orderUnitParams.setPrice(openContractOrderSpec.getCalcUnitPrice());
            
            //概算受渡代金
            l_orderUnitParams.setEstimatedPrice(openContractOrderSpec.getContractAmount());
            
            //譲渡益金額
            double l_dblCapitalGain = 0D;
            l_orderUnitParams.setCapitalGain(l_dblCapitalGain);
            
            //譲渡益税額
            double l_dblCapitalGainTax = 0D;
            l_orderUnitParams.setCapitalGainTax(l_dblCapitalGainTax);

            //取引コード（SONAR）
            l_orderUnitParams.setSonarTradedCode(commission.getSonarTradedCode());

            //市場コード（SONAR）
            try
            {
                WEB3GentradeFinObjectManager l_objectManager = (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                Market l_market = l_objectManager.getMarket(l_orderUnitParams.getMarketId());
                MarketRow l_marketRow = (MarketRow)l_market.getDataSourceObject();
                l_orderUnitParams.setSonarMarketCode(l_marketRow.getSonarMarketCode());
            }
            catch (NotFoundException l_nfe)
            {
                log.error(STR_METHOD_NAME, l_nfe);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_nfe.getMessage(), l_nfe);
            }
            
            //手数料商品コード
            l_orderUnitParams.setCommProductCode(commission.getCommissionProductCode());

            //空売規制対象フラグ
            if (this.shortSellingRestraint)
            {
                l_orderUnitParams.setShortSellOrderFlag(WEB3ShortSellOrderFlagDef.PRICE_RESTRAINT);
            }
            else
            {
                l_orderUnitParams.setShortSellOrderFlag(WEB3ShortSellOrderFlagDef.EXCEPT_OBJECT);                
            }
            
            //注文訂正・取消区分
            l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);

            //注文経路区分
            l_orderUnitParams.setOrderRootDiv(this.orderRootDiv);

            //発注経路区分
            if (this.submitOrderRouteDiv == null)
            {
                WEB3EquityFrontOrderService l_frontOrderService =
                    (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
                WEB3GentradeAccountManager l_accountManager =
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                WEB3EquityProductManager l_productManager =
                    (WEB3EquityProductManager)l_tradingModule.getProductManager();
                String l_strSubmitOrderRouteDiv = null;
                try
                {
                    Branch l_branch = l_accountManager.getBranch(l_orderUnitParams.getBranchId());
                    EqTypeTradedProduct l_tradedProduct =
                        (EqTypeTradedProduct)l_productManager.getTradedProduct(
                            l_orderUnitParams.getProductId(),
                            l_orderUnitParams.getMarketId());
                    l_strSubmitOrderRouteDiv =
                        l_frontOrderService.getSubmitOrderRouteDiv(
                            l_tradedProduct,
                            l_branch.getInstitution().getInstitutionCode(),
                            l_orderUnitParams.getSonarTradedCode());
                }
                catch (NotFoundException l_nfe)
                {
                    log.error(l_nfe.getMessage(), l_nfe);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                        this.getClass().getName() + STR_METHOD_NAME, 
                        l_nfe.getMessage(), l_nfe);
                }
                catch (WEB3BaseException l_wbe)
                {
                    log.error(l_wbe.getMessage(), l_wbe);
                    throw new WEB3BaseRuntimeException(
                        l_wbe.getErrorInfo(), 
                        this.getClass().getName() + STR_METHOD_NAME, 
                        l_wbe.getMessage(), l_wbe);
                }
                l_orderUnitParams.setSubmitOrderRouteDiv(l_strSubmitOrderRouteDiv);
            }
            else
            {
                l_orderUnitParams.setSubmitOrderRouteDiv(this.submitOrderRouteDiv);
            }
            
            //市場から確認済みの注文単価
            l_orderUnitParams.setConfirmedOrderPrice(null);
            
            //市場から確認済みの概算受渡代金
            l_orderUnitParams.setConfirmedEstimatedPrice(null);
            
            //市場から確認済みの執行条件
            l_orderUnitParams.setConfirmedExecConditionType(null);
            
            //市場から確認済みの値段条件
            l_orderUnitParams.setConfirmedPriceConditionType(null);
            
            //決済順序区分
            l_orderUnitParams.setClosingOrderType(null);
            
            //注文エラー理由コード
            l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
            
            //リクエストタイプ
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(openContractOrderSpec.getOrderConditionType())
                    || WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(openContractOrderSpec.getOrderConditionType()))
            {
                l_orderUnitParams.setRequestType(WEB3OrderingConditionDef.DEFAULT); 
            }
            else
            {
                l_orderUnitParams.setRequestType(null); 
            }

            //注文Rev.
            l_orderUnitParams.setOrderRev("00");
                
            //市場から確認済みの注文Rev.
            l_orderUnitParams.setConfirmedOrderRev("00");
            
            //初回注文の注文単位ＩＤ
            l_orderUnitParams.setFirstOrderUnitId(openContractOrderSpec.getFirstOrderUnitId() );
            
            // 元注文数量
            l_orderUnitParams.setOriginalQuantity(l_orderUnitParams.getQuantity());

            //（W指値）執行条件
            //信用新規建注文内容.get（W指値）執行条件
            //ただし、発注条件＝（0：DEFAULT（条件指定なし）、1：逆指値）の場合は、null。
            if (WEB3OrderingConditionDef.DEFAULT.equals(l_orderUnitParams.getOrderConditionType())
                || WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitParams.getOrderConditionType()))
            {
                l_orderUnitParams.setWLimitExecCondType(null);
            }
            else
            {
                l_orderUnitParams.setWLimitExecCondType(this.openContractOrderSpec.getWlimitExecCondType());
            }

            //発注遅延フラグ
            //0：遅延なし（0：DEFAULT）
            l_orderUnitParams.setSubmitOrderDelayFlag(BooleanEnum.FALSE);
            
            //強制決済理由区分
            l_orderUnitParams.setForcedSettleReasonType(null);
            
            //承認状態区分
            l_orderUnitParams.setApproveStatusType(null);
            
            //承認者コード
            l_orderUnitParams.setApproverCode(null);
            
            //承認／非承認日時
            l_orderUnitParams.setApproveTimestamp(null);
            
            //保証金維持率
            l_orderUnitParams.setMarginMaintenanceRate(null);
            
            //追証発生日
            l_orderUnitParams.setAdditionalMarginDate(null);
            
            //追証経過日数
            l_orderUnitParams.setAdditionalMarginAccruedDays(null);

            //強制失効区分
            //0：オープン（0：DEFAULT）
            l_orderUnitParams.setForcedExpireType(WEB3ForcedExpireType.OPENING);

            log.debug("this.繰越元注文単位プロパティ＝nullの場合●終了");
        }

        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }
    
    /**
     * (信用新規建更新インタセプタ)<BR>
     * コンストラクタ。<BR>
     * 引数に指定されたオブジェクトを、同名のプロパティに設定する。<BR>
     * <BR>
     * プロパティ「識別コード」「受注日時」「受渡日」「繰越元注文単位」には、nullをセットする。<BR>
     * @@param l_openContractOrderSpec - 信用新規建注文内容オブジェクト。<BR>
     * @@param l_commission - 手数料オブジェクト。<BR>
     * @@param l_strOrderChanel - 初回注文の注文チャネル。<BR>
     * @@param l_strOrderRootDiv - 注文経路区分。<BR>
     * @@param l_strShortSellOrderFlag - 空売り規制対象フラグ。<BR>
     * @@return EB3MarginOpenMarginUpdateInterceptor
     * @@roseuid 40B18F0B01E3
     */
    public WEB3MarginOpenMarginUpdateInterceptor(
        WEB3MarginOpenContractOrderSpec l_openContractOrderSpec, 
        WEB3GentradeCommission l_commission, 
        String l_strOrderChanel, 
        String l_strOrderRootDiv,
        boolean l_shortSellOrderFlag) 
    {
        final String STR_METHOD_NAME = "WEB3MarginOpenMarginUpdateInterceptor:WEB3MarginOpenMarginUpdateInterceptor()";
        log.entering(STR_METHOD_NAME);
        
        //引数に指定されたオブジェクトを、同名のプロパティに設定する。
        this.openContractOrderSpec = l_openContractOrderSpec;
        this.commission = l_commission;
        this.orderChanel = l_strOrderChanel;
        this.orderRootDiv = l_strOrderRootDiv;
        this.shortSellingRestraint = l_shortSellOrderFlag;
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get識別コード)<BR>
     * 識別コードを取得する。<BR>
     * @@return String
     * @@roseuid 40F37CBF0388
     */
    public String getOrderRequestNumber() 
    {
        return this.orderRequestNumber;
    }
    
    /**
     * (set識別コード)<BR>
     * 識別コードをセットする。<BR>
     * @@param l_strOrderRequestNumber - 識別コード<BR>
     * @@roseuid 40F37CD402AD
     */
    public void setOrderRequestNumber(String l_strOrderRequestNumber) 
    {
        this.orderRequestNumber = l_strOrderRequestNumber;
    }
    
    /**
     * (get受注日時)<BR>
     * 受注日時を取得する。<BR>
     * @@return Date
     * @@roseuid 40F50B9300EC
     */
    public Date getReceivedDateTime() 
    {
        return this.receivedDateTime;
    }
    
    /**
     * (set受注日時)<BR>
     * 受注日時をセットする。<BR>
     * @@param 受注日時 - 受注日時
     * @@roseuid 40F50BAB03CA
     */
    public void setReceivedDateTime(Date l_datReceivedDateTime) 
    {
        this.receivedDateTime = l_datReceivedDateTime;
    }
    
    /**
     * (get受渡日)<BR>
     * 受渡日を取得する。<BR>
     * @@return Date
     * @@roseuid XXXXXXXXXXX
     */
    public Date getDeliveryDate() 
    {
        return this.deliveryDate;
    }
    
    /**
     * (set受渡日)<BR>
     * 受渡日をセットする。<BR>
     * @@param 受渡日 - 受渡日
     * @@roseuid XXXXXXXXXXX
     */
    public void setDeliveryDate(Date l_datDeliveryDate) 
    {
        this.deliveryDate = l_datDeliveryDate;
    }
    
    /**
     * (get繰越元注文単位)<BR>
     * プロパティ「繰越元注文単位」を取得する。<BR>
     * @@return EqTypeContractOpenOrderUnit
     * @@roseuid 4112F34D0381
     */
    public EqTypeContractOpenOrderUnit getCarryoverOrderUnit() 
    {
        return this.carryoverOrderUnit;
    }
    
    /**
     * (set繰越元注文単位)<BR>
     * 引数を、プロパティ「繰越元注文単位」にセットする。<BR>
     * @@param l_carryoverOrderUnit - 繰越元注文単位。<BR>
     * @@roseuid 41088F2E026A
     */
    public void setCarryoverOrderUnit(EqTypeContractOpenOrderUnit l_carryoverOrderUnit) 
    {
        this.carryoverOrderUnit = l_carryoverOrderUnit;
    }
    
    /**
     * (get発注経路区分)<BR>
     * 発注経路区分を取得する。
     * @@return 発注経路区分
     */
    public String getSubmitOrderRouteDiv()
    {
        return this.submitOrderRouteDiv;
    }
    
    /**
     * (set発注経路区分)<BR>
     * 発注経路区分をセットする。
     * @@param l_submitOrderRouteDiv - (発注経路区分)<BR>
     * 発注経路区分
     */
    public void setSubmitOrderRouteDiv(String l_submitOrderRouteDiv)
    {
        this.submitOrderRouteDiv = l_submitOrderRouteDiv;
    }
}
@
