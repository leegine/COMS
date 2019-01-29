head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderCarryOverUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式注文繰越更新イベントインタセプタ(WEB3FeqOrderCarryOverUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/13  魏馨(中訊) 新規作成
                   2005/07/27 艾興　@(中訊) レビュー
*/

package webbroker3.feq;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccRegAccountDivDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.feq.service.delegate.WEB3FeqOrderEmpCodeManageService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式注文繰越更新イベントインタセプタ)<BR>
 * 外国株式注文繰越更新イベントインタセプタ<BR>
 * 
 * @@ author 魏馨<BR> 
 * @@ version 1.0 <BR>
 */
public class WEB3FeqOrderCarryOverUpdateInterceptor extends WEB3FeqUpdateInterceptor 
{
   
    /**
     * (ログユーティリティ)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderCarryOverUpdateInterceptor.class);
 
    /**
     * (繰越元注文単位行)<BR>
     * 繰越元の注文単位行オブジェクト<BR>
     */
    private FeqOrderUnitParams carryOverOriginUnitParams;
    
    /**
     * (金額計算結果)<BR>
     * 外国株式金額計算結果オブジェクト
     */
    private WEB3FeqAmountCalcResult amountCalcResult;
    
    /**
     * (計算単価)<BR>
     * 計算単価<BR>
     */
    private double roundPrice;
    
    /**
     * @@roseuid 42D0D8310148
     */
    public WEB3FeqOrderCarryOverUpdateInterceptor() 
    {
     
    }
    
    /**
     * (外国株式注文繰越更新イベントインタセプタ)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * 引数を自身のプロパティにセットする。<BR>
     * @@param l_carryOverOrderUnitParams - (繰越元注文単位行)<BR>
     * 繰越元の注文単位行オブジェクト<BR>
     * @@param l_amountCalcResult - (金額計算結果)<BR>
     * 外国株式金額計算結果オブジェクト<BR>
     * @@param l_dblPrice - (計算単価)<BR>
     * 計算単価<BR>
     * @@roseuid 42B8C0A40133
     */
    public WEB3FeqOrderCarryOverUpdateInterceptor(FeqOrderUnitParams l_carryOverOrderUnitParams,
        WEB3FeqAmountCalcResult l_amountCalcResult,
        double l_dblPrice) 
    {
        this.carryOverOriginUnitParams = l_carryOverOrderUnitParams;
        this.amountCalcResult = l_amountCalcResult;
        this.roundPrice = l_dblPrice;
    }
    
    /**
     * (（注文）更新値設定)<BR>
     * （mutateメソッドのオーバーロード） <BR>
     * 注文（ﾍｯﾀﾞ）テーブル更新<BR>
     * <BR>
     * １）　@注文（ﾍｯﾀﾞ）テーブル更新<BR>
     * 注文ＩＤに該当する注文行（：注文Params）を取得する。<BR>
     * 取得した注文行（：注文Params）を更新（DbUpdate）する。 <BR>
     * <BR>
     * 　@項目設定内容は、<BR>
     * 　@【xTrade】補足資料.DB更新\05.注文繰越\05.2.繰越後注文<BR>
     * 　@　@「外株注文繰越(繰越後)_外株注文（ヘッダ）仕様.xls」参照。<BR>
     * @@param l_lngOrderId - (注文ＩＤ)<BR>
     * 注文ＩＤ<BR>
     * @@roseuid 42C0E787008A
     */
    protected void mutate(long l_lngOrderId) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "mutate(long)";
        log.entering(STR_METHOD_NAME);
        
        QueryProcessor l_qp = null;
        FeqOrderParams l_feqOrderParams = null;
        try 
        {
            l_qp = Processors.getDefaultProcessor();             
            FeqOrderRow l_feqOrderRow = FeqOrderDao.findRowByOrderId(l_lngOrderId);
            l_feqOrderParams = new FeqOrderParams(l_feqOrderRow);
            //更新者コード = 繰越元の注文の同項目
            l_feqOrderParams.setLastUpdater(this.carryOverOriginUnitParams.getLastUpdater());
            //注文（ﾍｯﾀﾞ）テーブル更新
            l_qp.doUpdateQuery(l_feqOrderParams);
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
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
     * (（注文単位）更新値設定)<BR>
     * （mutateメソッドの実装） <BR>
     * <BR>
     * １）　@注文（ﾍｯﾀﾞ）テーブル更新<BR>
     * 　@[パラメータ.処理 == ORDER_EXPIREDの場合]<BR>
     * 　@　@super.mutate(OrderManagerPersistenceType, 
     * <BR>　@　@　@　@OrderManagerPersistenceContext, FeqOrderUnitParams)<BR>
     * 　@　@をコールする。<BR>
     * <BR>
     * 　@[上記以外の場合]<BR>
     * 　@　@this.mutate()をコールする。<BR>
     * <BR>
     * 　@　@[mutate()に指定する引数]<BR>
     * 　@　@　@注文ID：　@パラメータ.注文単位行.注文ID<BR>
     * <BR>
     * ２）　@注文単位テーブル更新<BR>
     * 　@注文単位Paramsに拡張項目(*)設定し返却する。 <BR>
     * 　@項目設定内容は、<BR>
     * 　@[パラメータ.処理 == ORDER_EXPIREDの場合]<BR>
     * 　@　@【xTrade】補足資料.DB更新\05.注文繰越\05.1.繰越元注文<BR>
     * 　@　@　@「外株注文繰越(繰越元)_外株注文単位仕様.xls」参照。<BR>
     * <BR>
     * 　@[上記以外の場合]<BR>
     * 　@　@【xTrade】補足資料.DB更新\05.注文繰越\05.2.繰越後注文<BR>
     * 　@　@　@「外株注文繰越(繰越後)_外株注文単位仕様.xls」参照。<BR>
     * 　@　@<BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。 <BR>
     * @@param l_updateType - (更新タイプ)<BR>
     * 更新タイプ<BR>
     * @@param l_context - (処理)<BR>
     * 処理<BR>
     * @@param l_feqOrderUnitParams - (注文単位行)<BR>
     * 注文単位行（：注文単位Params）<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams
     * @@roseuid 42B8C13701EF
     */
    public FeqOrderUnitParams mutate(OrderManagerPersistenceType l_updateType, 
        OrderManagerPersistenceContext l_context,
        FeqOrderUnitParams l_feqOrderUnitParams) 
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, FeqOrderUnitParams)";
        log.entering(STR_METHOD_NAME);
     
        if (l_feqOrderUnitParams == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータ値がNULL");
        }
                
        long l_lngOrderId = l_feqOrderUnitParams.getOrderId();       
        
        //パラメータ.処理 == ORDER_EXPIREDの場合
        if(OrderManagerPersistenceContext.ORDER_EXPIRED.equals(l_context))
        {
            //引数.更新タイプ != nullの場合、注文（ﾍｯﾀﾞ）テーブル更新を行う
            if (l_updateType != null)
            {
                //１）　@注文（ﾍｯﾀﾞ）テーブル更新
                super.mutate(l_updateType, l_context, l_feqOrderUnitParams);
                l_feqOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
            }
        } 
        //上記以外の場合
        else
        {
            //引数.更新タイプ != nullの場合、注文（ﾍｯﾀﾞ）テーブル更新を行う
            if (l_updateType != null)
            {
                try
                {
                    //１）　@注文（ﾍｯﾀﾞ）テーブル更新
                    this.mutate(l_lngOrderId);
                }
                catch (WEB3BaseException l_ex)
                {
                    log.error(STR_METHOD_NAME, l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        l_ex.getErrorInfo(), 
                        this.getClass().getName() + STR_METHOD_NAME, 
                        l_ex.getMessage(),
                        l_ex);
                }
            }
            
            //証券会社コード = 繰越元注文単位.証券会社コード
            l_feqOrderUnitParams.setInstitutionCode(this.carryOverOriginUnitParams.getInstitutionCode());
            //発注条件 = 繰越元注文単位の同項目
            l_feqOrderUnitParams.setOrderConditionType(this.carryOverOriginUnitParams.getOrderConditionType());
            //発注条件演算子 = 繰越元注文単位の同項目
            l_feqOrderUnitParams.setOrderCondOperator(this.carryOverOriginUnitParams.getOrderCondOperator());
            
            //逆指値基準値 = 繰越元注文単位の同項目
            if (this.carryOverOriginUnitParams.getStopOrderPriceIsNull())
            {
                l_feqOrderUnitParams.setStopOrderPrice(null);
            }
            else
            {
                l_feqOrderUnitParams.setStopOrderPrice(this.carryOverOriginUnitParams.getStopOrderPrice());
            }
            
            //（W指値）訂正指値 = 繰越元注文単位の同項目
            if (this.carryOverOriginUnitParams.getWLimitPriceIsNull())
            {
                l_feqOrderUnitParams.setWLimitPrice(null);
            }
            else
            {
                l_feqOrderUnitParams.setWLimitPrice(this.carryOverOriginUnitParams.getWLimitPrice());
            }
            
            //決済区分 = 繰越元注文単位の同項目            
            l_feqOrderUnitParams.setSettleDiv(this.carryOverOriginUnitParams.getSettleDiv());
            //初回注文の注文チャネル = 繰越元注文単位の同項目
            l_feqOrderUnitParams.setOrderChanel(this.carryOverOriginUnitParams.getOrderChanel());
            //受注日時 = 繰越元注文単位の同項目
            l_feqOrderUnitParams.setReceivedDateTime(this.carryOverOriginUnitParams.getReceivedDateTime());
            //注文識別コード採番サービス
            WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService=
                    (WEB3HostReqOrderNumberManageService)Services.getService(
                        WEB3HostReqOrderNumberManageService.class);
                        
            String l_strInstCode = this.carryOverOriginUnitParams.getInstitutionCode();
            
            long l_lngBranchId = this.carryOverOriginUnitParams.getBranchId();
            
            WEB3GentradeBranch l_branch = null;
            String l_strBranchCode = null;       
            String l_strOrderRequestNumber = null;                
            try
            {
                l_branch = new WEB3GentradeBranch(l_lngBranchId);
                l_strBranchCode = l_branch.getBranchCode();
                  
                l_strOrderRequestNumber = l_hostReqOrderNumberManageService.
                    getNewNumber(l_strInstCode, l_strBranchCode, ProductTypeEnum.FOREIGN_EQUITY);
                
            }
            catch (DataFindException l_ex)
            {
                log.error("DBへのアクセスに失敗しました: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    l_ex.getErrorInfo(), 
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_ex.getMessage(),
                    l_ex);
            }
            
            if(l_strOrderRequestNumber == null || l_strOrderRequestNumber.length() < 3)
            {
                log.error("識別コード不整合。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            //識別コード = 注文識別コード採番サービス.get新規識別コード()
            l_feqOrderUnitParams.setOrderRequestNumber(l_strOrderRequestNumber);                                                                
            
            //伝票No = "9"(WebBroker)＋識別コードの下3桁
            int l_intLength = l_strOrderRequestNumber.length();
            l_feqOrderUnitParams.setVoucherNo("9" + l_strOrderRequestNumber.substring(l_intLength - 3, l_intLength));
            //初回注文の手数料No = 金額計算結果.get手数料No()（* 手数料計算時に使用した値）
            l_feqOrderUnitParams.setCommTblNo(this.amountCalcResult.getCommissionNumber());
            //初回注文の手数料No枝番 = 金額計算結果.get手数料No枝番()（* 手数料計算時に使用した値）            
            l_feqOrderUnitParams.setCommTblSubNo(this.amountCalcResult.getCommissionBranchNumber());
            //扱者コード（SONAR） = 繰越元注文単位の同項目 
            l_feqOrderUnitParams.setSonarTraderCode(this.carryOverOriginUnitParams.getSonarTraderCode());            
            //注文単価 = インタセプタ.get計算単価()（* 概算受渡代金算出に使用した単価）            
            l_feqOrderUnitParams.setPrice(this.getRoundPrice());                                        
            //概算受渡代金 = 金額計算結果.get受渡代金()
            l_feqOrderUnitParams.setEstimatedPrice(this.amountCalcResult.getNetAmount());
            //概算受渡代金（外貨）
            l_feqOrderUnitParams.setFEstimatedPrice(this.amountCalcResult.getNetAmountFc());
            //取引コード（SONAR）=  11：普通株式
            l_feqOrderUnitParams.setSonarTradedCode(WEB3TransactionTypeSONARDef.MARKET_TRADING);
            //市場コード（SONAR）= 繰越元注文単位の同項目
            l_feqOrderUnitParams.setSonarMarketCode(this.carryOverOriginUnitParams.getSonarMarketCode());
            //手数料商品コード = 繰越元注文単位の同項目
            l_feqOrderUnitParams.setCommProductCode(this.carryOverOriginUnitParams.getCommProductCode());
            //譲渡益金額 = 0
            l_feqOrderUnitParams.setCapitalGain(0);
            //譲渡益税額 = 0
            l_feqOrderUnitParams.setCapitalGainTax(0);
            //注文訂正・取消区分 = 0:初期値
            l_feqOrderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
            //注文経路区分 = 繰越元注文単位の同項目
            l_feqOrderUnitParams.setOrderRootDiv(this.carryOverOriginUnitParams.getOrderRootDiv());
            //市場から確認済みの注文単価 = null
            l_feqOrderUnitParams.setConfirmedOrderPrice(null);
            //市場から確認済みの概算受渡代金 = null
            l_feqOrderUnitParams.setConfirmedEstimatedPrice(null);
            //市場から確認済みの執行条件 = null
            l_feqOrderUnitParams.setConfirmedExecConditionType(null);
            //注文エラー理由コード = 0000：正常
            l_feqOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
            //ファ@クター
            l_feqOrderUnitParams.setFactor(" ");
            
            //運用コード = 外国株式運用コード採番サービス.get新規運用コード()
            WEB3FeqOrderEmpCodeManageService l_service =
                     (WEB3FeqOrderEmpCodeManageService)Services.getService(
                         WEB3FeqOrderEmpCodeManageService.class);
            
            Date l_datBizDate = null;
            try
            {
                l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    l_ex.getErrorInfo(), 
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_ex.getMessage(),
                    l_ex);            
            }  
            WEB3GentradeMarket l_market = null;
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            try
            {
                l_market = (WEB3GentradeMarket) l_finObjectManager.getMarket(this.carryOverOriginUnitParams.getMarketId());
            }
            catch (NotFoundException l_nfe)
            {
                log.error(STR_METHOD_NAME, l_nfe);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                   this.getClass().getName() + "." + STR_METHOD_NAME,
                   l_nfe.getMessage(),
                   l_nfe);
            }                                                         
            try
            {
                l_feqOrderUnitParams.setOrderEmpCode(l_service.getNewEmpCode(l_market, l_datBizDate));
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    l_ex.getErrorInfo(), 
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_ex.getMessage(),
                    l_ex);            
            }            
            
            //顧客区分 = ”一般”
            l_feqOrderUnitParams.setAccountDiv(WEB3AccRegAccountDivDef.GENERAL);
            //出来終了処理日時 = null
            l_feqOrderUnitParams.setExecEndTimestamp(null);
            //初回注文の注文単位ＩＤ
            //繰越元注文単位.初回注文の注文単位ＩＤ = 0 の場合、繰越元注文単位.注文単位ＩＤ
            //それ以外の場合、繰越元注文単位.初回注文の注文単位ＩＤ
            if (this.carryOverOriginUnitParams.getFirstOrderUnitId() == 0)
            {
                l_feqOrderUnitParams.setFirstOrderUnitId(this.carryOverOriginUnitParams.getOrderUnitId());
            }
            else
            {
                l_feqOrderUnitParams.setFirstOrderUnitId(this.carryOverOriginUnitParams.getFirstOrderUnitId());
            }
            //更新者コード = 繰越元注文単位の同項目
            l_feqOrderUnitParams.setLastUpdater(this.carryOverOriginUnitParams.getLastUpdater());
            
        }        
        log.exiting(STR_METHOD_NAME);      
        return l_feqOrderUnitParams;
    }
    
    /**
     * (get繰越元注文単位行)<BR>
     * this.繰越元注文単位行を返却する。<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams
     * @@roseuid 42BA691B02B8
     */
    public FeqOrderUnitParams getCarryOverOriginOrderUnitParams() 
    {
        return this.carryOverOriginUnitParams;
    }
    
    /**
     * (set繰越元注文単位行)<BR>
     * 引数の繰越元注文単位行を自身のプロパティにセットする。<BR>
     * @@param l_feqOrderUnitParams - (繰越元注文単位行)<BR>
     * 繰越元の注文単位行オブジェクト<BR>
     * @@roseuid 42BA68C40335
     */
    public void setCarryOverOriginOrderUnitParams(FeqOrderUnitParams l_feqOrderUnitParams) 
    {
        this.carryOverOriginUnitParams = l_feqOrderUnitParams;
    }
    
    /**
     * (get金額計算結果)<BR>
     * this.金額計算結果を返却する。<BR>
     * @@return webbroker3.feq.WEB3FeqAmountCalcResult
     * @@roseuid 42BA6C990180
     */
    public WEB3FeqAmountCalcResult getAmountCalcResult() 
    {
        return this.amountCalcResult;
    }
    
    /**
     * (set金額計算結果)<BR>
     * 金額計算結果を自身のプロパティにセットする。<BR>
     * @@param l_amountCalcResult - (金額計算結果)<BR>
     * 外国株式金額計算結果オブジェクト<BR>
     * @@roseuid 42BA6CCB03D1
     */
    public void setAmountCalcResult(WEB3FeqAmountCalcResult l_amountCalcResult) 
    {
        this.amountCalcResult = l_amountCalcResult;
    }
    
    /**
     * (get計算単価)<BR>
     * this.計算単価を返却する。<BR>
     * @@return double
     * @@roseuid 42BA6DCC02A8
     */
    public double getRoundPrice() 
    {
        return this.roundPrice;
    }
    
    /**
     * (set計算単価)<BR>
     * 引数の計算単価を自身のプロパティにセットする。<BR>
     * @@param l_dblRoundPrice - (計算単価)<BR>
     * 計算単価<BR>
     * @@roseuid 42BA6E0802B8
     */
    public void setRoundPrice(double l_dblRoundPrice) 
    {
        this.roundPrice = l_dblRoundPrice;
    }
}
@
