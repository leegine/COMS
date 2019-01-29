head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderAcceptExecutionNotifyUnitTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式注文受付出来通知一件TransactionCallback(WEB3FeqOrderAcceptExecutionNotifyUnitTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 齊珂 (中訊) 新規作成
                 : 2006/10/12 何文敏(中訊) モデル　@No.285対応
                   2006/10/17 徐大方(中訊) モデル　@No.287,292対応
                   2006/11/20 徐大方(中訊) モデル　@No.299対応
                   2006/12/14 齊珂 (中訊)  モデル　@No.311対応
                   2006/12/19 齊珂 (中訊)  モデル　@No.317対応
                   2006/12/19 齊珂 (中訊)  モデル　@No.321対応
                   2006/12/20 齊珂 (中訊)  モデル　@No.324対応
Revesion History : 2007/08/10 韓斌 (中訊)  モデル　@No.354対応
Revesion History : 2008/01/23 柴双紅(中訊) モデルNo.372
Revesion History : 2008/02/26 馮海濤(中訊) モデルNo.400
Revesion History : 2008/10/02 大澤(SRA) 【外国株式】仕様変更管理台帳（モデル）No.467 （ＤＢ更新仕様）No.095、096
Revesion History : 2010/03/05 武波 (中訊)【外国株式】仕様変更管理台帳（モデル）No.542
*/

package webbroker3.feq;

import java.sql.Timestamp;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TemporaryExecutionFlagDef;
import webbroker3.feq.data.HostFeqOrderExecNotifyParams;
import webbroker3.feq.define.WEB3FeqOrderExecRouteDivDef;
import webbroker3.feq.message.WEB3FeqOrderAcceptCancelUnit;
import webbroker3.feq.service.delegate.WEB3FeqExecutionNotifyUnitService;
import webbroker3.feq.service.delegate.WEB3FeqOrderAcceptUnitService;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.slebase.data.SleRcvdQParams;
import webbroker3.slebase.data.SleRcvdQRow;
import webbroker3.slebase.enums.SleRcvdqProcStatusEnum;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式注文受付出来通知一件TransactionCallback)<BR>
 * 管外国株式注文受付出来通知一件トランザクション処理を実施するクラス<BR>
 * 
 * @@author 齊珂
 * @@version 1.0
 */
public class WEB3FeqOrderAcceptExecutionNotifyUnitTransactionCallback implements TransactionCallback
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderAcceptExecutionNotifyUnitTransactionCallback.class);
    
    /**
     * ThreadLocalより”LAST_UPDATER”の変数名。
     */
    private static final String LAST_UPDATER = "last_updater";
    
    /**
     * (注文単位)<BR>
     * 注文単位<BR>
     */
    private WEB3FeqOrderUnit orderUnit;
    
    /**
     * (外国株取引RCVD_Q)<BR>
     * 外国株取引RCVD_Q<BR>
     */
    private SleRcvdQParams sleRvcdQParams;
    
    /**
     * トランザクション処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外国株式注文受付出来通知一件トランザクション）process」参照。<BR>
     * <BR>
     * @@return Object
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     * @@roseuid 4214980A032E
     */
    public Object process() throws DataNetworkException, DataQueryException, DataCallbackException
    {
        final String STR_METHOD_NAME = "process()";
        log.entering(STR_METHOD_NAME);
       
        WEB3GentradeCurrency l_currency = null;
        double l_dblFxRate = 0D;
        boolean l_blnIsExec = false;
        
        HostFeqOrderExecNotifyParams l_hostFeqOrderExecNotifyParams = null;
        
        //1.1 setAttribute
        ThreadLocalSystemAttributesRegistry.setAttribute(LAST_UPDATER, 
        	this.sleRvcdQParams.getLastUpdater());
        
        QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
        
        //1.2 this.外国株取引RCVD_Q.get経路区分() == (0:出来通知 or 1:出来入力 or 2:約定結果一括入力)の場合
        String l_strRouteDiv = this.sleRvcdQParams.getRouteDiv();
        if (WEB3FeqOrderExecRouteDivDef.EXEC_NOTIFY.equals(l_strRouteDiv)||
        	WEB3FeqOrderExecRouteDivDef.EXEC_INPUT.equals(l_strRouteDiv)||
        	WEB3FeqOrderExecRouteDivDef.EXECUTED_RESULT_UPLOAD.equals(l_strRouteDiv))
        {
        	WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
        		(WEB3FeqTypeOrderManagerReusableValidations)
        		WEB3FeqTypeOrderManagerReusableValidations.getInstance();
        	
            try
			{
            	// validate当日為替レート()
            	l_blnIsExec = l_orderMgrResVal.validateDayExchange(this.orderUnit);
            	
            	//1.2.1.1 get通貨
				l_currency = this.orderUnit.getCurrency();
			}
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new DataCallbackException(
                    l_ex.getErrorMessage(),
                    l_ex);
            }
			
			//1.2.1.2is買付
			boolean l_blnIsBuy = this.orderUnit.isBuy();
            
			//1.2.1.3 get為替レート
            if (l_currency != null)
            {
            	l_dblFxRate = l_currency.getExchangeRate(l_blnIsBuy, l_blnIsExec , 0);
            }				
        	
        	//1.2.2 外株出来通知キューParamsを生成し、プロパティに値をセットする
            l_hostFeqOrderExecNotifyParams = new HostFeqOrderExecNotifyParams();
            
            l_hostFeqOrderExecNotifyParams.setInstitutionCode(this.orderUnit.getInstitutionCode());
            try
			{
				l_hostFeqOrderExecNotifyParams.setBranchCode(this.orderUnit.getBranchCode());
			}
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new DataCallbackException(
                    l_ex.getMessage(),
                    l_ex);
            }
            
			l_hostFeqOrderExecNotifyParams.setAccountCode(this.orderUnit.getAccountCode());
			
			String l_strOrderRequestNumber = 
				((FeqOrderUnitRow)this.orderUnit.getDataSourceObject()).getOrderRequestNumber();
			
		    l_hostFeqOrderExecNotifyParams.setOrderRequestNumber(l_strOrderRequestNumber);
			
			l_hostFeqOrderExecNotifyParams.setOrderEmpCode(this.orderUnit.getOrderEmpCode());
			
			if (!this.sleRvcdQParams.getExecQtyIsNull())
			{
				l_hostFeqOrderExecNotifyParams.setExecQuantity(this.sleRvcdQParams.getExecQty());
			}
	
			if (!this.sleRvcdQParams.getExecPriceIsNull())
			{
				l_hostFeqOrderExecNotifyParams.setExecPrice(this.sleRvcdQParams.getExecPrice());
			}
			
            Timestamp l_tisExecTimestamp = null;
            
            if (this.sleRvcdQParams.getExecTimestamp() == null)
            {
                l_tisExecTimestamp = GtlUtils.getSystemTimestamp();
            }
            else
            {
                l_tisExecTimestamp = this.sleRvcdQParams.getExecTimestamp();
            }
            
            l_hostFeqOrderExecNotifyParams.setExecTimestamp(l_tisExecTimestamp);
            
			l_hostFeqOrderExecNotifyParams.setOrderBizDate(
				WEB3DateUtility.getDate(this.orderUnit.getBizDate(), "yyyyMMdd"));
			
            if (this.sleRvcdQParams.getFDeliveryDate() == null)
            {
                if (l_tisExecTimestamp != null)
                {
                    WEB3GentradeBizDate l_bizDate = new WEB3GentradeBizDate(l_tisExecTimestamp);
                    try
                    {
                        Timestamp l_tisFDeliveryDate = l_bizDate.roll(3);
                        l_hostFeqOrderExecNotifyParams.setFDeliveryDate(l_tisFDeliveryDate);
                    }
                    catch (WEB3SystemLayerException l_ex)
                    {
                        log.error(l_ex.getErrorMessage(), l_ex);
                        log.exiting(STR_METHOD_NAME);
                        throw new DataCallbackException(
                            l_ex.getErrorMessage(),
                            l_ex);
                    }
                }
            }
            else
            {
                l_hostFeqOrderExecNotifyParams.setFDeliveryDate(this.sleRvcdQParams.getFDeliveryDate());
            }
            
			l_hostFeqOrderExecNotifyParams.setFxRate(new Double(l_dblFxRate));
            
            l_hostFeqOrderExecNotifyParams.setExecSerialNo(this.sleRvcdQParams.getExecSerialNo());

            //約定SEQ：　@this.外国株取引RCVD_Q.インデクス・ナンバー
            if (this.sleRvcdQParams.getRepliesIndexIsNull())
            {
                l_hostFeqOrderExecNotifyParams.setExecutionSeqNo(null);
            }
            else
            {
                l_hostFeqOrderExecNotifyParams.setExecutionSeqNo((int)(this.sleRvcdQParams.getRepliesIndex()));
            }

			//外国株式出来通知１件サービス
			WEB3FeqExecutionNotifyUnitService l_serivce = 
	        	(WEB3FeqExecutionNotifyUnitService) Services.getService(
	        		WEB3FeqExecutionNotifyUnitService.class);

            //notify約定(外国株式注文単位, 外株出来通知キューParams, 外国株取引RCVD_Q)
            //約定１件処理を行う。
            //[notify約定()に指定する引数]
            //　@注文単位：　@this.注文単位
            //　@外株出来通知キュー：　@生成した外株出来通知キューParams
            //　@外国株取引RCVD_Q： this.外国株取引RCVD_Q
			try
			{
				l_serivce.notifyOrder(
					this.orderUnit,
					l_hostFeqOrderExecNotifyParams,
                    this.sleRvcdQParams,
                    Boolean.valueOf(l_blnIsExec));
			}
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new DataCallbackException(
                    l_ex.getErrorMessage(),
                    l_ex);
            }
        }
        
        //1.3 this.外国株取引RCVD_Q.get経路区分() == (3:注文受付 or 4:注文受付取消認証 or 
        //5:注文受付結果一括入力)の場合
        if (WEB3FeqOrderExecRouteDivDef.ORDER_ACCEPT.equals(l_strRouteDiv)||
        	WEB3FeqOrderExecRouteDivDef.ORDER_ACCEPT_CANCEL_AUTHENTICATE.equals(l_strRouteDiv)||
        	WEB3FeqOrderExecRouteDivDef.ORDER_ACCEPT_RESULT_UPLOAD.equals(l_strRouteDiv))
        {
        	//1.3.1 外国株式注文受付取消情報
        	WEB3FeqOrderAcceptCancelUnit l_feqOrderAcceptCancelUnit = 
        		new WEB3FeqOrderAcceptCancelUnit();
        	
        	//1.3.2 生成した外国株式注文受付取消情報オブジェクトのプロパティに値をセットする
        	l_feqOrderAcceptCancelUnit.orderId = this.orderUnit.getOrderId() + "";
        	
        	l_feqOrderAcceptCancelUnit.aftChangeAcceptDiv = this.sleRvcdQParams.getAcceptDiv();
        	
        	//[notify注文受付()に指定する引数]
            //　@注文単位：　@this.注文単位
            //　@外国株式注文受付取消情報：　@生成した外国株式注文受付取消情報オブジェクト
            //　@外国株取引RCVD_Q： this.外国株取引RCVD_Q

        	WEB3FeqOrderAcceptUnitService l_feqOrderAcceptUnitService =
	        	(WEB3FeqOrderAcceptUnitService) Services.getService(
	        		WEB3FeqOrderAcceptUnitService.class);

        	try
			{
				l_feqOrderAcceptUnitService.notifyOrderAccept(
					this.orderUnit,
					l_feqOrderAcceptCancelUnit,
                    this.sleRvcdQParams);
			}
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new DataCallbackException(
                    l_ex.getErrorMessage(),
                    l_ex);
            }
        }
        
        // this.外国株取引RCVD_Q.get経路区分() == (0:出来通知 or 1:出来入力 or 2:約定結果一括入力)の場合
        if (WEB3FeqOrderExecRouteDivDef.EXEC_NOTIFY.equals(l_strRouteDiv) || 
        	WEB3FeqOrderExecRouteDivDef.EXEC_INPUT.equals(l_strRouteDiv) || 
        	WEB3FeqOrderExecRouteDivDef.EXECUTED_RESULT_UPLOAD.equals(l_strRouteDiv))
        {
        	FeqOrderUnit l_feqOrderUnit = null;
        	FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        	TradingModule l_tradingModule = 
        		l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        	WEB3FeqOrderManager l_orderManager = 
        		(WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        	try 
        	{
        		// 更新後の注文単位を再取得する。
        		l_feqOrderUnit = 
        			(FeqOrderUnit)l_orderManager.getOrderUnit(this.orderUnit.getOrderUnitId());
			} 
        	catch (NotFoundException l_ex) 
        	{
        		log.error("テーブルに該当するデータがありません。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new DataCallbackException(
                    l_ex.getMessage(),
                    l_ex);
			}
        	
        	FeqOrderUnitRow l_feqOrderUnitRow = 
        		(FeqOrderUnitRow)l_feqOrderUnit.getDataSourceObject();
        	FeqOrderUnitParams l_feqOrderUnitParams = new FeqOrderUnitParams(l_feqOrderUnitRow);

        	// validate当日為替レート( ) == falseの場合
        	if (l_blnIsExec == false)
        	{
        		// キューテーブルの処理区分、更新日付を更新する
        		// 更新日付：　@現在日時(＝GtlUtils.getSystemTimestamp())
        		// 処理区分：　@"約定処理中"
                this.sleRvcdQParams.setStatus(SleRcvdqProcStatusEnum.EXEC_PROCESSING);
            	this.sleRvcdQParams.setLastUpdatedTimestamp(
                	GtlUtils.getSystemTimestamp());
                
                l_queryProcesser.doUpdateQuery(this.sleRvcdQParams);
                
                // 外株注文単位テーブル.仮約定フラグを"1：仮約定"に更新する
                l_feqOrderUnitParams.setTemporaryExecutionFlag(WEB3TemporaryExecutionFlagDef.TEMPORARY_EXEC);
                l_queryProcesser.doUpdateQuery(l_feqOrderUnitParams);
                
                ThreadLocalSystemAttributesRegistry.setAttribute(LAST_UPDATER, null);
                log.exiting(STR_METHOD_NAME);
                return null;
        	}
        	
        	// キューテーブルの処理区分、更新日付を更新する
        	// 　@更新日付：　@現在日時(＝GtlUtils.getSystemTimestamp())
        	//　@処理区分：　@"処理済み"
            this.sleRvcdQParams.setStatus(SleRcvdqProcStatusEnum.PROCESSED);
        	this.sleRvcdQParams.setLastUpdatedTimestamp(
            	GtlUtils.getSystemTimestamp());
            l_queryProcesser.doUpdateQuery(this.sleRvcdQParams);
            
            // 外国株取引RCVD_Qテーブルを、以下の条件で読み込む
            // 運用コード = 注文単位.運用コード
            // 処理区分 = "7 : 約定処理中"
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" order_emp_code = ? ");
            l_sbWhere.append(" and status = ? ");
            
            Object[] l_objWhere = new Object[2];
            l_objWhere[0] = l_feqOrderUnitRow.getOrderEmpCode();
            l_objWhere[1] = SleRcvdqProcStatusEnum.EXEC_PROCESSING.intValue() + "";
            
            List l_listSearchResult = l_queryProcesser.doFindAllQuery(
            	SleRcvdQRow.TYPE, 
            	l_sbWhere.toString(), 
            	l_objWhere);
            
            // 注文単位テーブル.仮約定フラグを更新する
            // 外国株取引RCVD_Qテーブルの検索結果が0件の場合
            if (l_listSearchResult == null || l_listSearchResult.isEmpty())
            {
            	// 外株注文単位テーブル.仮約定フラグを"0：DEFAULT"に更新
                l_feqOrderUnitParams.setTemporaryExecutionFlag(WEB3TemporaryExecutionFlagDef.DEFAULT);
            }
            // 上記以外の場合
            else
            {
            	// 外株注文単位テーブル.仮約定フラグを"1：仮約定"に更新
                l_feqOrderUnitParams.setTemporaryExecutionFlag(WEB3TemporaryExecutionFlagDef.TEMPORARY_EXEC);
            }
            l_queryProcesser.doUpdateQuery(l_feqOrderUnitParams);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(LAST_UPDATER, null);
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        //1.4 キューテーブルの処理区分、更新日付を更新する
        this.sleRvcdQParams.setStatus(SleRcvdqProcStatusEnum.PROCESSED);
    	this.sleRvcdQParams.setLastUpdatedTimestamp(
        	GtlUtils.getSystemTimestamp());
        
        l_queryProcesser.doUpdateQuery(this.sleRvcdQParams);
        
        //1.5 setAttribute
        ThreadLocalSystemAttributesRegistry.setAttribute(LAST_UPDATER, null);
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (外国株式注文受付出来通知一件TransactionCallback)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * 引数で指定されたオブジェクトを、インスタンス変数にセットする。<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@param l_tradingRCVD_QParams - (外国株取引RCVD_Q)<BR>
     * 外国株取引RCVD_Q<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 4214980A032E
     */
    public WEB3FeqOrderAcceptExecutionNotifyUnitTransactionCallback(WEB3FeqOrderUnit l_orderUnit, 
    	SleRcvdQParams l_tradingRCVD_QParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
        	"WEB3FeqOrderAcceptExecutionNotifyUnitTransactionCallback" +
        		"(WEB3FeqOrderUnit, SleRcvdQParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null || l_tradingRCVD_QParams == null)
        {
            log.debug("パラメータNull出来ない");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータNull出来ない。");
        }
        this.orderUnit = l_orderUnit;
        this.sleRvcdQParams = l_tradingRCVD_QParams;
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
