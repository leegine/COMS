head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式更新イベントインタセプタ(WEB3FeqUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/13  魏馨(中訊) 新規作成
                 : 2005/07/25  芦露(中訊) レビュー
                 : 2006/09/18  黄建(中訊) 仕様変更・モデル236  
*/

package webbroker3.feq;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.BatchedQuery;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.IllegalSessionStateException;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.data.AdministratorDao;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (外国株式更新イベントインタセプタ)<BR>
 * 外国株式更新イベントインタセプタ<BR>
 * 
 * @@ author 魏馨<BR> 
 * @@ version 1.0 <BR>
 */
public class WEB3FeqUpdateInterceptor implements FeqOrderManagerPersistenceEventInterceptor 
{
    
    /**
     * ThreadLocalより”LAST_UPDATER”の変数名。
     */
    private static final String LAST_UPDATER = "last_updater";
    
    /**
     * (ログユーティリティ)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqUpdateInterceptor.class);
        
    /**
     * @@roseuid 42D1CBA90177
     */
    public WEB3FeqUpdateInterceptor() 
    {
     
    }
    
    /**
     * (（注文履歴）更新値設定)<BR>
     * （mutateメソッドの実装） <BR>
     * 注文履歴Paramsに拡張項目(*)設定し返却する。 <BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。 <BR>
     * <br>
     * １）　@注文単位オブジェクト取得 <BR>
     * <BR>
     * 引数の注文履歴Params.注文ID、注文単位ＩＤに<BR>
     * 該当する注文単位オブジェクトを取得する。 <BR>
     * <BR>
     * ２）　@拡張項目セット <BR>
     * 　@パラメータ.注文履歴Paramsの拡張項目に、<BR>
     * 　@注文単位オブジェクトの同名項目から値をセットし、返却する。 <BR>
     * @@param l_updateType
     * @@param l_context
     * @@param l_feqOrderActionParams
     * @@return com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionParams
     * @@roseuid 428043C703A2
     */
    public FeqOrderActionParams mutate(
        OrderManagerPersistenceType l_updateType, 
        OrderManagerPersistenceContext l_context, 
        FeqOrderActionParams l_feqOrderActionParams) 
    {
        final String STR_METHOD_NAME = "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, FeqOrderActionParams)";
        log.entering(STR_METHOD_NAME);   
        
        if (l_feqOrderActionParams == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() +"."+ STR_METHOD_NAME, 
                "パラメータ値がNULL");
        }
        
        long l_lngOrderUnitId = l_feqOrderActionParams.getOrderUnitId();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqOrderManager l_orderManager = (WEB3FeqOrderManager) l_tradingMod.getOrderManager();
        FeqOrderUnit l_feqOrderUnit = null;
        
        try 
        {
            // 注文単位オブジェクト取得
            l_feqOrderUnit = (FeqOrderUnit) l_orderManager.getOrderUnit(l_lngOrderUnitId);
        } 
        catch (NotFoundException l_ex) 
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);  
        }
        FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow) l_feqOrderUnit.getDataSourceObject();
        
        //発注条件
        l_feqOrderActionParams.setOrderConditionType(l_feqOrderUnitRow.getOrderConditionType());
        
        //発注条件演算子
        l_feqOrderActionParams.setOrderCondOperator(l_feqOrderUnitRow.getOrderCondOperator());
        
        //逆指値基準値
        if(l_feqOrderUnitRow.getStopOrderPriceIsNull())
        {
            l_feqOrderActionParams.setStopOrderPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setStopOrderPrice(l_feqOrderUnitRow.getStopOrderPrice());   
        }
        
        //（W指値）訂正指値
        if (l_feqOrderUnitRow.getWLimitPriceIsNull())
        {
            l_feqOrderActionParams.setWLimitPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setWLimitPrice(l_feqOrderUnitRow.getWLimitPrice());
        }
        
        //注文失効日付
        l_feqOrderActionParams.setExpirationDate(l_feqOrderUnitRow.getExpirationDate());
        
        //概算受渡代金
        if (l_feqOrderUnitRow.getEstimatedPriceIsNull())
        {
            l_feqOrderActionParams.setEstimatedPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setEstimatedPrice(l_feqOrderUnitRow.getEstimatedPrice());
        }
        
        //概算受渡代金（外貨）
        if (l_feqOrderUnitRow.getFEstimatedPriceIsNull())
        {
            l_feqOrderActionParams.setFEstimatedPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setFEstimatedPrice(l_feqOrderUnitRow.getFEstimatedPrice());
        }
        
        //注文訂正・取消区分
        l_feqOrderActionParams.setModifyCancelType(l_feqOrderUnitRow.getModifyCancelType());
        
        //注文経路区分
        l_feqOrderActionParams.setOrderRootDiv(l_feqOrderUnitRow.getOrderRootDiv());
        
        //市場から確認済みの注文単価
        if (l_feqOrderUnitRow.getConfirmedOrderPriceIsNull())
        {
            l_feqOrderActionParams.setConfirmedOrderPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setConfirmedOrderPrice(l_feqOrderUnitRow.getConfirmedOrderPrice());
        }
        
        //市場から確認済みの概算受渡代金
        if (l_feqOrderUnitRow.getConfirmedEstimatedPriceIsNull())
        {
            l_feqOrderActionParams.setConfirmedEstimatedPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setConfirmedEstimatedPrice(l_feqOrderUnitRow.getConfirmedEstimatedPrice());
        }
        
        //市場から確認済みの概算受渡代金（外貨）
        if (l_feqOrderUnitRow.getConfirmedFEstimatedPriceIsNull())
        {
            l_feqOrderActionParams.setConfirmedFEstimatedPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setConfirmedFEstimatedPrice(l_feqOrderUnitRow.getConfirmedFEstimatedPrice());
        }
        
        //市場から確認済みの執行条件
        l_feqOrderActionParams.setConfirmedExecConditionType(l_feqOrderUnitRow.getConfirmedExecConditionType());
        
        //注文エラー理由コード
        l_feqOrderActionParams.setErrorReasonCode(l_feqOrderUnitRow.getErrorReasonCode());
        
        //更新者コード
        l_feqOrderActionParams.setLastUpdater(l_feqOrderUnitRow.getLastUpdater());
        
        log.exiting(STR_METHOD_NAME);
        
        return l_feqOrderActionParams;
    }
    
    /**
     * (（注文単位）更新値設定)<BR>
     * （mutateメソッドの実装） <BR>
     * 注文（ﾍｯﾀﾞ）テーブル更新<BR>
     * <BR>
     * this.（注文）更新値設定()をコールする。<BR>
     * <BR>
     * [（注文）更新値設定()に指定する引数]<BR>
     * 更新タイプ：　@更新タイプ<BR>
     * 注文ＩＤ：　@注文単位Params.注文ＩＤ<BR>
     * @@param l_updateType - (更新タイプ)<BR>
     * 更新タイプ<BR>
     * @@param l_context - (処理)<BR>
     * 処理<BR>
     * @@param l_feqOrderUnitParams - (注文単位行)<BR>
     * 注文単位行（：注文単位Params）<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams
     * @@roseuid 42A69080030D
     */
    public FeqOrderUnitParams mutate(OrderManagerPersistenceType l_updateType, 
        OrderManagerPersistenceContext l_context,
        FeqOrderUnitParams l_feqOrderUnitParams)  
    {
        final String STR_METHOD_NAME = "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, FeqOrderUnitParams)";
        log.entering(STR_METHOD_NAME);   
        
        if (l_feqOrderUnitParams == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");
        }
               
        try
        {
            //this.（注文）更新値設定()をコールする
            this.mutate(l_updateType, l_feqOrderUnitParams.order_id);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(), 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_feqOrderUnitParams;
    }
    
    /**
     * (（注文）更新値設定)<BR>
     * （mutateメソッドのoverload） <BR>
     * 注文（ﾍｯﾀﾞ）テーブル更新<BR>
     * <BR>
     * １）注文ＩＤに該当する注文行（：注文Params）を取得する。<BR>
     * <BR>
     * ２）取得した注文行（：注文Params）の更新者コードを以下の通り更新（DbUpdate）する。<BR>
     * <BR>
     * 　@（１）　@ThreadLocalより”LAST_UPDATER”属性の値を取得できる場合、<BR>
     * 　@　@　@取得した値をセットする。<BR>
     * 　@　@　@※取得できない場合は、以下の処理を行なう。<BR>
     * <BR>
     * 　@（２）　@ログインセキュリティサービス（OpLoginSecurityService）より口座ＩＤを取得する。<BR>
     * <BR>
     * 　@（３）　@口座ＩＤが取得できた場合、金融オブジェクトマネージャ.getTraderByLoginId()にて扱者を取得し<BR>
     * 　@　@　@以下の通り、更新者コードに値をセットする。<BR>
     * 　@　@　@－代理入力の場合（扱者 != null）、扱者.getTraderCode()<BR>
     * 　@　@　@－顧客入力の場合（扱者 == null）、口座ＩＤに該当す顧客<BR>
     * 　@　@　@.getAccountCode()<BR>
     * <BR>
     * 　@（４）　@口座ＩＤが取得できなかった場合、ログインＩＤを取得し <BR>
     * 　@　@　@取得したログインＩＤにて管理者テーブルを検索、該当行<BR>
     * 　@　@　@の管理者コードをセットする。<BR>
     * 　@　@　@※ログインIDが取得できなかった場合は、nullをセットする。<BR>
     * @@param l_updateType - (更新タイプ)<BR>
     * 更新タイプ<BR>
     * @@param l_lngOrderId - (注文ＩＤ)<BR>
     * 注文ＩＤ<BR>
     * @@roseuid 42A694850170
     */
    protected void mutate(OrderManagerPersistenceType l_updateType, long l_lngOrderId) throws WEB3BaseException 
    {
		final String STR_METHOD_NAME = "mutate(OrderManagerPersistenceType, long)";
		log.entering(STR_METHOD_NAME);   
                       
		OpLoginSecurityService l_opLoginSec = (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
		long l_lngAccountId = 0;
		//ログインＩＤを取得
		long l_lngLoginId = 0;

		FinApp l_finApp = null;
		QueryProcessor l_qp = null;
		FeqOrderRow l_feqOrderRow = null;
		FeqOrderParams l_feqOrderParams = null;
		Trader l_trader = null;
		String l_strAccountCode = null;

		try
		{
            l_qp = Processors.getDefaultProcessor();
            l_feqOrderRow = FeqOrderDao.findRowByOrderId(l_lngOrderId);
            l_feqOrderParams = new FeqOrderParams(l_feqOrderRow);
            
            //（１）　@ThreadLocalより”LAST_UPDATER”属性の値を取得できる場合、
            //取引時間コンテキストの取得
            String l_strLastUpdater = 
                (String) ThreadLocalSystemAttributesRegistry.getAttribute(LAST_UPDATER);
            if (!WEB3StringTypeUtility.isEmpty(l_strLastUpdater))
            {
                l_feqOrderParams.setLastUpdater(l_strLastUpdater);
                l_qp.doUpdateQuery(l_feqOrderParams);
            }
            else
            {
                try
                {
                    if (l_opLoginSec.isAccountIdSet())
                    //口座ＩＤが取得できた場合
                    {  
                        l_lngAccountId = l_opLoginSec.getAccountId();  
                        l_finApp = (FinApp) Services.getService(FinApp.class);
                        WEB3GentradeFinObjectManager l_gentradeFinObjectManager = (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
                    
                        try
                        {
                            l_lngLoginId = l_opLoginSec.getLoginId();
                            l_trader = l_gentradeFinObjectManager.getTraderByLoginId(l_lngLoginId);
                        }
                        catch (IllegalSessionStateException l_ex)
                        {
                            l_trader = null;
                        }
                        catch (NotFoundException l_ex)
                        {
                            l_trader = null;
                        }

                    
                        if (l_trader != null)
                        {
                            //代理入力の場合（扱者 != null）、扱者.getTraderCode()
                            l_feqOrderParams.setLastUpdater(l_trader.getTraderCode());
                        }
                        else
                        {
                            //顧客入力の場合（扱者 == null）、口座ＩＤに該当す顧客
                            try
                            {
                                l_strAccountCode = l_finApp.getAccountManager().getMainAccount(l_lngAccountId).getAccountCode();
                            }
                            catch (NotFoundException l_ex)
                            {
                                log.error(STR_METHOD_NAME, l_ex);
                                log.exiting(STR_METHOD_NAME);
                                throw new WEB3SystemLayerException(
                                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                                    this.getClass().getName() + "." + STR_METHOD_NAME,
                                    l_ex.getMessage(),
                                    l_ex);
                            }
                            l_feqOrderParams.setLastUpdater(l_strAccountCode);
                        }
                
                    }
                    //口座ＩＤが取得できなかった場合
                    else
                    {

                            l_lngLoginId = l_opLoginSec.getLoginId();
                            AdministratorRow l_AdministratorRow = null;
                            l_AdministratorRow = AdministratorDao.findRowByLoginId(l_lngLoginId);
                            if  (l_AdministratorRow == null)
                            {
                                l_feqOrderParams.setLastUpdater(null);
                            }
                            else
                            {
                                //管理者コードを取得
                                String l_strAdministratorCode = l_AdministratorRow.getAdministratorCode();
                                l_feqOrderParams.setLastUpdater(l_strAdministratorCode);
                            }
                    }


                    l_qp.doUpdateQuery(l_feqOrderParams);
                }
                catch (IllegalSessionStateException l_isse)
                {
                    l_feqOrderParams.setLastUpdater(null);
                    l_qp.doUpdateQuery(l_feqOrderParams);
                }
            }
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

    /* (non-Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderManagerPersistenceEventInterceptor#mutate(com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType, com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext, com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionParams)
     */
    public FeqOrderExecutionParams mutate(OrderManagerPersistenceType arg0, OrderManagerPersistenceContext arg1, FeqOrderExecutionParams arg2)
    {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderManagerPersistenceEventInterceptor#getQueryToExecute(com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType, java.lang.Class)
     */
    public BatchedQuery getQueryToExecute(OrderManagerPersistenceType arg0, Class arg1)
    {
        // TODO Auto-generated method stub
        return null;
    }
}
@
