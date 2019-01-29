head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.40.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqExecutionNotifyUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式出来通知１件サービスImpl(WEB3FeqExecutionNotifyUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 齊珂 (中訊) 新規作成   
Revesion History : 2006/10/12 何文敏(中訊) モデル　@No.286対応
Revesion History : 2006/10/17 徐大方(中訊) モデル No.288対応
Revesion History : 2006/12/14 齊珂 (中訊) モデル No.311対応
Revesion History : 2006/12/15 齊珂 (中訊) モデル No.312対応
Revesion History : 2006/12/19 齊珂 (中訊) モデル No.319対応  ＤＢ更新仕様 078      
Revesion History : 2006/12/20 齊珂 (中訊) モデル No.323対応
Revesion History : 2007/01/09 周捷 (中訊) モデル No.329対応
Revesion History : 2007/04/02 齊珂 (中訊) モデル No.349対応
Revesion History : 2007/08/08 韓斌 (中訊) モデル No.354対応 ＤＢ更新仕様087
Revesion History : 2008/01/23 柴双紅(中訊) モデルNo.372
Revesion History : 2008/10/02 大澤(SRA) 【外国株式】仕様変更管理台帳（モデル）No.468
Revesion History : 2010/01/25 張騰宇 (中訊) 仕様変更モデル536 539
Revesion History : 2010/02/22 武波 (中訊) 実装の問題No.033
Revesion History : 2010/03/05 武波 (中訊)【外国株式】仕様変更管理台帳（モデル）No.541
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.feq.WEB3FeqFinTransactionManager;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.WEB3FeqPositionManager;
import webbroker3.feq.data.HostFeqOrderExecNotifyParams;
import webbroker3.feq.define.WEB3FeqLocalSystemAttributesDef;
import webbroker3.feq.define.WEB3FeqOrderExecRouteDivDef;
import webbroker3.feq.service.delegate.WEB3FeqExecutionNotifyUnitService;
import webbroker3.feq.service.delegate.WEB3FeqOrderAndExecutionUpdateService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.slebase.data.SleRcvdQParams;
import webbroker3.slebase.enums.SleRcvdqProcStatusEnum;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式出来通知１件サービスImpl)<BR>
 * 外国株式出来通知１件サービス実装クラス<BR>
 *   
 * @@author 齊珂
 * @@version 1.0
 */
public class WEB3FeqExecutionNotifyUnitServiceImpl implements WEB3FeqExecutionNotifyUnitService
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqExecutionNotifyUnitServiceImpl.class);
    
	public WEB3FeqExecutionNotifyUnitServiceImpl()
	{

	}
  
    /**
     * (notify約定)<BR>
     * 約定処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     *「（外国株式出来通知サービス）出来通知１件処理」参照。<BR>
     * ========================================================<BR>
     *  シーケンス図(「（外国株式出来通知サービス）出来通知１件処理」)<BR>
     * 　@　@:  1.4 メッセージ (*) 出来終了後（is出来終了() == true）の場合、<BR> 
     * 　@　@「出来終了処理済みなので、出来不可です」例外をスローする。<BR> 
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:   BUSINESS_ERROR_02162<BR>
     * ========================================================<BR>
     * @@param l_feqOrderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@param l_hostFeqOrderExecNotifyParams - (外株出来通知キュー)<BR>
     * 外株出来通知キュー<BR>
     * @@param l_sleRvcdQParams - (外国株取引RCVD_Q)<BR>
     * 外国株取引RCVD_Q<BR>
     * @@param l_todayLoginFlag - (当日為替登録フラグ)<BR>
     * 当日為替登録フラグ<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 429FEB91034C
     */
    public void notifyOrder(
    	WEB3FeqOrderUnit l_feqOrderUnit, 
    	HostFeqOrderExecNotifyParams l_hostFeqOrderExecNotifyParams,
    	SleRcvdQParams l_sleRvcdQParams,
        Boolean l_todayLoginFlag)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " notifyOrder(WEB3FeqOrderUnit, " +
        		"HostFeqOrderExecNotifyParams, SleRcvdQParams, boolean) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_feqOrderUnit == null || l_hostFeqOrderExecNotifyParams == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータ値不正。");
        }
        
		FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
		WEB3FeqOrderManager l_orderManager =
		    (WEB3FeqOrderManager) l_finApp.getTradingModule(
		    ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
		
        WEB3FeqPositionManager l_positionManager =
		    (WEB3FeqPositionManager) l_finApp.getTradingModule(
		    ProductTypeEnum.FOREIGN_EQUITY).getPositionManager();

		WEB3FeqOrderUnit l_orderUnit = null;
        try
		{
        	//1.1 getOrderUnit
        	log.debug("feqOrderUnitId==" + l_feqOrderUnit.getOrderUnitId());
        	l_orderUnit = (WEB3FeqOrderUnit) l_orderManager.getOrderUnit(l_feqOrderUnit.getOrderUnitId());
		}
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.2 外国株取引RCVD_Q.入力経路区分が0（：出来通知）の場合
        String l_strFeqInputRouteDiv = l_sleRvcdQParams.getRouteDiv();
        if (WEB3FeqOrderExecRouteDivDef.EXEC_NOTIFY.equals(l_strFeqInputRouteDiv) &&
            SleRcvdqProcStatusEnum.TODO.equals(l_sleRvcdQParams.getStatus()))
        {
            //validate注文状態(外国株式注文単位,外株出来通知キューParams)
            this.validateOrderStatus(l_orderUnit, l_hostFeqOrderExecNotifyParams);
        }

        //1.5 is出来終了
        if (l_orderUnit.isExecEnd())
        {
            log.debug("出来終了処理済みなので、出来不可です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02162,
                getClass().getName() + STR_METHOD_NAME,
                "出来終了処理済みなので、出来不可です。");
        }
        
        // 外国株取引RCVD_Q.処理区分 == "未処理"の場合
        if (SleRcvdqProcStatusEnum.TODO.equals(l_sleRvcdQParams.getStatus()))
        {
        	WEB3FeqOrderUnit l_updatedOrderUnit = null;
        	
            //1.7 validate約定日
            l_orderManager.validateExecutionDate(l_orderUnit, 
            	l_hostFeqOrderExecNotifyParams.getExecTimestamp());
            
            //1.8 validate約定数量
            l_orderManager.validateExecutedQuantity(l_orderUnit,
            	l_hostFeqOrderExecNotifyParams.getExecQuantity());
            
            // 外国株取引RCVD_Q．入力経路区分が0（：出来通知）以外の場合
            if (!WEB3FeqOrderExecRouteDivDef.EXEC_NOTIFY.equals(l_strFeqInputRouteDiv))
            {
                //validate約定単価
                l_orderManager.validateExecutedPrice(l_orderUnit,
                	l_hostFeqOrderExecNotifyParams.getExecPrice());
            }
            
            //1.10 validate現地受渡日(外国株式注文単位, Date)
            l_orderManager.validateFDeliveryDate(l_orderUnit,
                l_hostFeqOrderExecNotifyParams.getFDeliveryDate());
            
            //1.11 ThreadLocalSystemAttributesRegistryに属性を設定する
            ThreadLocalSystemAttributesRegistry.setAttribute(
            	WEB3FeqLocalSystemAttributesDef.FEQ_ORDER_EXEC_ROUTE_DIV,
                l_strFeqInputRouteDiv);
            
            //1.12 update約定情報
            WEB3FeqOrderAndExecutionUpdateService l_orderAndExecutionUpdateService = 
            	(WEB3FeqOrderAndExecutionUpdateService) Services.getService(
            		WEB3FeqOrderAndExecutionUpdateService.class);
            
            l_orderAndExecutionUpdateService.updateExecuteUnit(l_hostFeqOrderExecNotifyParams);
            
            //1.13 updateトランザクション
            l_positionManager.updateTransaction(l_orderUnit.getOrderUnitId(), false);
                        
            try
    		{
            	//更新後の注文単位を取得する
            	l_updatedOrderUnit =
            		(WEB3FeqOrderUnit)l_orderManager.getOrderUnit(l_feqOrderUnit.getOrderUnitId());
    		}
            catch (NotFoundException l_ex)
            {
                log.error("テーブルに該当するデータがありません。", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            //1.16  update概算受渡代金
            l_orderManager.updateEstimatedPrice(l_updatedOrderUnit,
            	l_hostFeqOrderExecNotifyParams.getExecTimestamp());

            //  外国株取引RCVD_Q．入力経路区分が0（：出来通知）以外の場合
            if (!WEB3FeqOrderExecRouteDivDef.EXEC_NOTIFY.equals(l_strFeqInputRouteDiv))
            {
                //訂正／取消エラー処理実行(long)
                //注文が訂正／取消中でかつ全出来の場合、注文を訂正／取消エラーの状態に更新する。 
                //[訂正／取消エラー処理実行()に指定する引数] 
                //　@注文単位ID：　@取得した更新後の注文単位.getOrderUnitId()の戻り値
                l_orderManager.executeChangeCancelOrderRejected(l_updatedOrderUnit.getOrderUnitId());
            }
        }

        // 外国株取引RCVD_Q.処理区分 == "約定処理中"
        if (SleRcvdqProcStatusEnum.EXEC_PROCESSING.equals(l_sleRvcdQParams.getStatus()))
        {
            try 
            {
                QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
                
                // 注文単位テーブルに紐づく約定テーブルを全て取得する
                StringBuffer l_sbWhere = new StringBuffer();
                l_sbWhere.append(" order_unit_id = ? ");
                Object[] l_objWhere = new Object[1];
                l_objWhere[0] = l_feqOrderUnit.getOrderUnitId() + "";
                
                List l_lisFeqOrderExecution = l_queryProcesser.doFindAllQuery(
                        FeqOrderExecutionRow.TYPE, 
                        l_sbWhere.toString(), 
                        l_objWhere);
                
                if (l_lisFeqOrderExecution == null || l_lisFeqOrderExecution.isEmpty())
                {
                    log.debug("テーブルに該当するデータがありません。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                            this.getClass().getName() + STR_METHOD_NAME);
                }
                // getトランザクション
                WEB3FeqFinTransactionManager l_feqFinTransactionManager = 
                    (WEB3FeqFinTransactionManager)l_finApp.getTradingModule(
                        ProductTypeEnum.FOREIGN_EQUITY).getFinTransactionManager();
                
                List l_lisFeqFinTransaction = 
                    l_feqFinTransactionManager.getTransactions(l_orderUnit);
                
                if (l_lisFeqFinTransaction == null || l_lisFeqFinTransaction.isEmpty())
                {
                    log.debug("テーブルに該当するデータがありません。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                            this.getClass().getName() + STR_METHOD_NAME);
                }
                
                // 取得した約定テーブル及び、トランザクションテーブル全てを更新する
                int l_intSize = l_lisFeqOrderExecution.size();
                for (int i = 0; i < l_intSize; i++)
                {
                    FeqOrderExecutionRow l_feqOrderExecutionRow = 
                        (FeqOrderExecutionRow)l_lisFeqOrderExecution.get(i);
                    FeqOrderExecutionParams l_feqOrderExecutionParams = 
                        new FeqOrderExecutionParams(l_feqOrderExecutionRow);
                    
                    // 為替レート：　@外株出来通知キューParams.為替レートで更新
                    l_feqOrderExecutionParams.setFxRate(l_hostFeqOrderExecNotifyParams.getFxRate());
                    l_queryProcesser.doUpdateQuery(l_feqOrderExecutionParams);
                }
                
                l_intSize = l_lisFeqFinTransaction.size();
                for (int i = 0; i < l_intSize; i++)
                {
                    FeqFinTransactionParams l_feqFinTransactionParams = 
                        (FeqFinTransactionParams)l_lisFeqFinTransaction.get(i);
                    
                    // 適用為替レート：　@外株出来通知キューParams.為替レートで更新
                    l_feqFinTransactionParams.setFxRate(l_hostFeqOrderExecNotifyParams.getFxRate());
                    l_queryProcesser.doUpdateQuery(l_feqFinTransactionParams);
                }
                
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

            boolean l_blnIsDayTradeAdoption = false;
            boolean l_blnIsDayTradeMarket = false;
            try
            {
                WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                WEB3GentradeMainAccount l_mainAccount =
                    (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_feqOrderUnit.getAccountId());
                FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow)l_feqOrderUnit.getDataSourceObject();
                WEB3GentradeMarket l_market =
                    (WEB3GentradeMarket)(l_finApp.getFinObjectManager().getMarket(l_feqOrderUnitRow.getMarketId()));
                WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)l_mainAccount.getInstitution();
                l_blnIsDayTradeAdoption = l_institution.isDayTradeAdoption();
                l_blnIsDayTradeMarket = l_market.isDayTradeMarket();
            }
            catch (NotFoundException l_ex)
            {
                log.error("テーブルに該当するデータがありません。", l_ex);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //is日計り取引採用() == true 且つ　@is日計り市場() == true　@且つ　@買付注文（注文単位.is買付() == true）の場合
            if (l_blnIsDayTradeAdoption && l_blnIsDayTradeMarket && l_orderUnit.isBuy())
            {
                //update保有資産(外国株式注文単位)
                l_positionManager.updateAsset(l_orderUnit);
            }

            // updateトランザクション
            l_positionManager.updateTransaction(l_orderUnit.getOrderUnitId(), true);
            
            // update概算受渡代金
            l_orderManager.updateEstimatedPrice(
                l_orderUnit, 
                l_hostFeqOrderExecNotifyParams.getExecTimestamp());
            
            // update合計約定金額（円）
            l_orderManager.updateExecutedAmountYen(l_orderUnit.getOrderUnitId());
        }

        //余力再計算
        WEB3TPTradingPowerReCalcService l_tradingPowerReCalcService =
            (WEB3TPTradingPowerReCalcService)Services.getService(
                WEB3TPTradingPowerReCalcService.class);
        l_tradingPowerReCalcService.reCalcTradingPower(l_orderUnit.getSubAccount());

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate注文状態)<BR>
     * 対象注文が、約定／約定取消による更新を行って良い状態であるかチェックする。 <BR>
     * <BR>
     * （チェック内容）<BR>
     * １．引数.注文単位.市場から確認済の数量==nullの場合、<BR>
     * <BR>
     * 　@「該当注文は受付未済／変更の受付済／発注中の状態」の例外をthrowする。<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_01975<BR>
     * <BR>
     * ２．（引数.注文単位.約定SEQ  !=　@null）　@かつ<BR>
     * 　@　@（引数.注文単位.約定SEQ ＞＝ 外株出来通知キューParams.約定SEQ）の場合、<BR>
     * <BR>
     * 　@　@「約定データ重複」の例外をthrowする。<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02891<BR>
     * <BR>
     * @@param l_feqOrderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@param l_hostFeqOrderExecNotifyParams - (外株出来通知キューParams)<BR>
     * 外株出来通知キューParams<BR>
     * @@throws WEB3BaseException
     * @@roseuid 429FEB91034C
     */
    public void validateOrderStatus(
        WEB3FeqOrderUnit l_feqOrderUnit,
        HostFeqOrderExecNotifyParams l_hostFeqOrderExecNotifyParams)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateOrderStatus(WEB3FeqOrderUnit, HostFeqOrderExecNotifyParams)";
        log.entering(STR_METHOD_NAME);

        if (l_feqOrderUnit == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータ値不正。");
        }

    	FeqOrderUnitRow l_feqOrderUnitRow =
    		(FeqOrderUnitRow)l_feqOrderUnit.getDataSourceObject();

    	if (l_feqOrderUnitRow.getConfirmedQuantityIsNull())
    	{
            log.debug("該当注文は受付未済／変更の受付済／発注中の状態。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01975,
                this.getClass().getName() + STR_METHOD_NAME,
                "該当注文は受付未済／変更の受付済／発注中の状態。");
        }

        //２．（引数.注文単位.約定SEQ  !=　@null）　@かつ
        //　@（引数.注文単位.約定SEQ ＞＝ 外株出来通知キューParams.約定SEQ）の場合、
        //「約定データ重複」の例外をthrowする。
        if (l_feqOrderUnit.getExecutionSeqNo() != null
            && Integer.parseInt(l_feqOrderUnit.getExecutionSeqNo()) >=
                l_hostFeqOrderExecNotifyParams.getExecutionSeqNo())
        {
            log.debug("約定データ重複");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02891,
                this.getClass().getName() + STR_METHOD_NAME,
                "約定データ重複");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
