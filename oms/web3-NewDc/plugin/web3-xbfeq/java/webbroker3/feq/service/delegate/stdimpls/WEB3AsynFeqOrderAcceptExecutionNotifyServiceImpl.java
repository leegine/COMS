head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.39.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AsynFeqOrderAcceptExecutionNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 非同期対応外国株式注文受付出来通知サービスImpl(WEB3AsynFeqOrderAcceptExecutionNotifyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 齊珂 (中訊) 新規作成
                   2006/11/24 徐大方 (中訊) モデルNo.307
                   2006/12/14 齊珂 (中訊) モデルNo.311
Revesion History : 2007/07/04 柴双紅(中訊) モデルNo.351
Revesion History : 2008/10/02 大澤(SRA) 【外国株式】仕様変更管理台帳（モデル）No.467
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.feq.WEB3FeqOrderAcceptExecutionNotifyUnitTransactionCallback;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.WEB3FeqTypeOrderManagerReusableValidations;
import webbroker3.feq.define.WEB3FeqOrderExecRouteDivDef;
import webbroker3.feq.message.WEB3FeqOrderAcceptExecNotifyRequest;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.gentrade.data.DaemonTriggerRow;
import webbroker3.slebase.data.SleRcvdQParams;
import webbroker3.slebase.data.SleRcvdQRow;
import webbroker3.slebase.enums.SleRcvdqProcStatusEnum;
import webbroker3.util.WEB3LogUtility;

/**
 * (非同期対応外国株式注文受付出来通知サービスImpl)<BR>
 * 外国株式注文受付出来通知非同期サービス実装クラス<BR>
 * 
 * @@author 齊珂
 * @@version 1.0
 */
public class WEB3AsynFeqOrderAcceptExecutionNotifyServiceImpl implements Runnable
{
    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AsynFeqOrderAcceptExecutionNotifyServiceImpl.class);
    
    /**
     * (外国株式注文受付出来通知リクエスト)<BR>
     * 外国株式注文受付出来通知リクエスト<BR>
     */
    private WEB3FeqOrderAcceptExecNotifyRequest orderAcceptExecNotifyRequest;
    
    /**
     * (非同期対応外国株式注文受付出来通知サービスImpl)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * <BR>
     * 引数で指定されたオブジェクトを、インスタンス変数にセットする。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@roseuid 4214980A032E
     */
    WEB3AsynFeqOrderAcceptExecutionNotifyServiceImpl(WEB3FeqOrderAcceptExecNotifyRequest l_request)
    {
        this.orderAcceptExecNotifyRequest = l_request;
    }
    
    /**
     * 非同期処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外国株式注文受付出来通知サービス）非同期処理」参照。<BR>
     * <BR>
     * @@roseuid 4214980A032E
     */
    public void run()
    {
        final String STR_METHOD_NAME = " run()";
        log.entering(STR_METHOD_NAME);

    	//非同期対応外国株式注文受付出来通知TransactionCallbackインスタンスを生成する。
        WEB3AsynFeqOrderAcceptExecutionNotifyTransactionCallback 
	        l_orderAcceptExecutionNotifyTransactionCallback = null;
        
        if (this.orderAcceptExecNotifyRequest != null)
        {    	
    	    l_orderAcceptExecutionNotifyTransactionCallback = 
    		    new WEB3AsynFeqOrderAcceptExecutionNotifyTransactionCallback(
    		    	this.orderAcceptExecNotifyRequest.fromAccountId,
    		    	this.orderAcceptExecNotifyRequest.toAccountId,
    		       	this.orderAcceptExecNotifyRequest.threadNo);
        }
        
        try
		{
	        //getDefaultProcessor()
	        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
	        
	        //doTransaction(arg0 : int, arg1 : TransactionCallback)
			l_queryProcessor.doTransaction(
			    QueryProcessor.TX_CREATE_NEW,
			    l_orderAcceptExecutionNotifyTransactionCallback);
		}
        catch (DataNetworkException l_dataNetworkException)
        {
            log.error("DBへのアクセスに失敗しました。", l_dataNetworkException);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dataNetworkException.getMessage(),
                l_dataNetworkException);
        }
        catch (DataQueryException l_dataQueryException)
        {
            log.error("DBへのアクセスに失敗しました。", l_dataQueryException);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dataQueryException.getMessage(),
                l_dataQueryException);
        }
        
        try
        {
            //スレッドを開放する
            WEB3GentradeDaemonTriggerManager l_triggerManager = 
            	new WEB3GentradeDaemonTriggerManager();
            l_triggerManager.releaseThread(
            	this.orderAcceptExecNotifyRequest.threadNo.longValue());
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (非同期対応外国株式注文受付出来通知TransactionCallback)<BR>
     * トランザクション処理を実施する内部クラス<BR>
     * <BR>
     * （トランザクション属性：TransactionalInterceptor.TX_CREATE_NEW）<BR>
     * 
     * @@roseuid 4214980A032E
     */
    public class WEB3AsynFeqOrderAcceptExecutionNotifyTransactionCallback implements TransactionCallback
    {
        /**
         * (From口座ID)<BR>
         * From口座ID<BR>
         */
        private Long fromAccountId;
        
        /**
         * (To口座ID)<BR>
         * To口座ID<BR>
         */
        private Long toAccountId;
        
        /**
         * (Thread番号)<BR>
         * Thread番号<BR>
         */
        private Long threadNo;
        
        /**
         * (非同期対応外国株式注文受付出来通知TransactionCallback)<BR>
         * コンストラクタ。<BR>
         * <BR>
         * 引数で指定されたオブジェクトを、インスタンス変数にセットする。<BR>
         * <BR>
         * @@param l_lngFromAccountId - (From口座ID)<BR>
         * From口座ID<BR>
         * @@param l_lngToAccountId - (To口座ID)<BR>
         * To口座ID<BR>
         * @@param l_lngThreadNo - (Thread番号)<BR>
         * Thread番号<BR>
         * @@roseuid 4214980A032E
         */
        WEB3AsynFeqOrderAcceptExecutionNotifyTransactionCallback(
            Long l_lngFromAccountId, 
            Long l_lngToAccountId, 
            Long l_lngThreadNo)
        {
            final String STR_METHOD_NAME = " WEB3AsynFeqOrderAcceptExecutionNotifyTransactionCallback" +
            		"(Long, Long, Long)";
            log.entering(STR_METHOD_NAME);
            
        	//From口座ID
            this.fromAccountId = l_lngFromAccountId;
            
            //To口座ID
            this.toAccountId = l_lngToAccountId;
            
            //Thread番号
            this.threadNo = l_lngThreadNo;
            
            log.exiting(STR_METHOD_NAME);
        }
        
        /**
         * トランザクション処理を実施する。<BR>
         * <BR>
         * シーケンス図<BR>
         * 「（非同期対応外国株式注文受付出来通知トランザクション）process」参照。<BR>
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

            if(lockThread(this.threadNo.longValue())==false)
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            
            //外国株取引RCVD_Qテーブル読み込み
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" ( status = ? or status = ? ) ");
            l_sbWhere.append(" and ( account_id >= ? and account_id <= ? ) ");

            Object[] l_objWhere = new Object[4];
            l_objWhere[0] = SleRcvdqProcStatusEnum.TODO.intValue() + "";
            l_objWhere[1] = SleRcvdqProcStatusEnum.EXEC_PROCESSING.intValue() + "";
            l_objWhere[2] = this.fromAccountId + "";
            l_objWhere[3] = this.toAccountId + "";

            String l_strConditon = "replies_index asc";

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisSearchResult = l_queryProcessor.doFindAllQuery(
            	SleRcvdQRow.TYPE,
                l_sbWhere.toString(),
                l_strConditon,
                null,
                l_objWhere);
            
            // 取得したキューテーブルのレコード数分、Loop
            FeqOrderUnit l_orderUnit = null;
            SleRcvdQParams l_params = null;
            
            int l_intNum = 0;
            if (l_lisSearchResult != null && !l_lisSearchResult.isEmpty())
            {
                l_intNum = l_lisSearchResult.size();
            }

            
            boolean l_blnDayExchange = false;
            String l_strRouteDiv = null;
            for (int i = 0; i < l_intNum; i++)
            {
            	SleRcvdQRow l_row = (SleRcvdQRow) l_lisSearchResult.get(i);
            	l_params = new SleRcvdQParams(l_row);
            	l_strRouteDiv = l_params.getRouteDiv();
            	
                try
                {
					FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
					WEB3FeqOrderManager l_orderManager =
					    (WEB3FeqOrderManager) l_finApp.getTradingModule(
					    ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
					
					//get注文単位ByOrderId
					l_orderUnit = l_orderManager.getOrderUnitByOrderId(
						new Long(l_params.getInternalRef()).longValue());
					
					// validate当日為替レート
					WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
						(WEB3FeqTypeOrderManagerReusableValidations)
							WEB3FeqTypeOrderManagerReusableValidations.getInstance();
					l_blnDayExchange = 
						l_orderMgrResVal.validateDayExchange((WEB3FeqOrderUnit)l_orderUnit);

					// 当日為替レート未登録でかつ、約定処理中の約定キューの場合
					//  外国株取引RCVD_Q.処理区分 == "約定処理中"かつ
					//  validate当日為替レート( ) == falseかつ
					//  （外国株取引RCVD_Q.経路区分 == "出来通知" or "出来入力" or "約定結果一括入力"）の場合
					if (!l_blnDayExchange && 
						SleRcvdqProcStatusEnum.EXEC_PROCESSING.equals(l_params.getStatus()) && 
						(WEB3FeqOrderExecRouteDivDef.EXEC_NOTIFY.equals(l_strRouteDiv) || 
						WEB3FeqOrderExecRouteDivDef.EXEC_INPUT.equals(l_strRouteDiv) || 
						WEB3FeqOrderExecRouteDivDef.EXECUTED_RESULT_UPLOAD.equals(l_strRouteDiv)))
					{
						continue;
					}
					
					//外国株式注文受付出来通知一件TransactionCallbackインスタンスを生成する。 
					WEB3FeqOrderAcceptExecutionNotifyUnitTransactionCallback l_transactionCallback =
					    new WEB3FeqOrderAcceptExecutionNotifyUnitTransactionCallback(
					    	(WEB3FeqOrderUnit)l_orderUnit,
					        l_params);
					  
					// doTransaction()
					l_queryProcessor.doTransaction(
					    QueryProcessor.TX_CREATE_NEW,
					    l_transactionCallback);
                }
                //シーケンス中で例外がスローされた場合
                catch (Exception l_ex)
                {
                    if (!(WEB3ErrorCatalog.BUSINESS_ERROR_01975.getErrorMessage().equals(l_ex.getMessage())))
                    {
                        log.error(l_ex.getMessage(), l_ex);
                        l_params.setStatus(SleRcvdqProcStatusEnum.SKIP_PROCESSING_ERROR);
                        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                        l_queryProcessor.doUpdateQuery(l_params);
                    }
                }
            }
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        /**
         * (ロックスレッド)<BR>
         * Threadをロックする。<BR>
         * デーモントリガーテーブルを以下の条件で、かつ"for update nowait"<BR>
         * で読み込み、レコードをロックする。<BR>
         * [検索条件]<BR>
         * 　@スレッド番号＝引数.Thread番号<BR>
         * <BR>
         * 該当するレコードが存在しない場合、例外が発生した場合はfalseを返す。<BR>
         * それ以外はtrueを返す。<BR>
         * <BR>
         * @@param l_lngThreadNo - (Thread番号)<BR>
         * Thread番号<BR>
         * @@return boolean
         * @@roseuid 4214980A032E
         */
        private boolean lockThread(long l_lngThreadNo)
        {
            final String STR_METHOD_NAME = "lockThread(long)";
            log.entering(STR_METHOD_NAME);
            boolean l_blnResult = false;
            try
            {
                String l_strWhere = " thread_no = ? ";
                String l_strCondition = " for update nowait ";

                Object l_bindVars[] ={new Long(l_lngThreadNo)};
                
                QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
                List l_lisRows = l_queryProcesser.doFindAllQuery(
                    DaemonTriggerRow.TYPE,
                    l_strWhere, 
                    l_strCondition, 
                    l_bindVars);
                if (l_lisRows != null && !l_lisRows.isEmpty())
                {
                    l_blnResult = true;
                }
                else
                {
                    l_blnResult = false;
                }
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                l_blnResult = false;
            }
            log.exiting(STR_METHOD_NAME);
            return l_blnResult;
        }
    }
}
@
