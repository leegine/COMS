head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.40.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqSendQueueRecoveryServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式SENDキューリカバリサービスImpl(WEB3AdminFeqSendQueueRecoveryServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 徐大方 (中訊) 新規作成
Revesion History : 2008/08/11 武波 (中訊) ＤＢ更新仕様No.093
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.message.WEB3AdminFeqSendQueueRecoveryCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqSendQueueRecoveryCompleteResponse;
import webbroker3.feq.service.delegate.WEB3AdminFeqSendQueueRecoveryService;
import webbroker3.feq.service.delegate.WEB3FeqMailSenderService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.slebase.data.SleSendQParams;
import webbroker3.slebase.data.SleSendQRow;
import webbroker3.slebase.enums.SleSendqOpTypeEnum;
import webbroker3.slebase.enums.SleSendqProcStatusEnum;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者外国株式SENDキューリカバリサービスImpl)<BR>
 * 管理者外国株式SENDキューリカバリサービス実装クラ<BR>
 * 
 * @@author 徐大方
 * @@version 1.0
 */
public class WEB3AdminFeqSendQueueRecoveryServiceImpl implements WEB3AdminFeqSendQueueRecoveryService
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqSendQueueRecoveryServiceImpl.class);
        
    /**
     * @@roseuid 42D0CED203D8
     */
    public WEB3AdminFeqSendQueueRecoveryServiceImpl() 
    {
     
    }
    
    /**
     * 管理者外国株式SENDキューリカバリ処理を実施する。<BR>
     * <BR>
     * submit更新()メソッドをコールする。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4214980A032E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest) ";
        log.entering(STR_METHOD_NAME);
        
        
        if (l_request == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストが未指定(null)です。");
        }
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminFeqSendQueueRecoveryCompleteRequest)
        {
            //submit更新()
            l_response = 
                this.submitUpdate((WEB3AdminFeqSendQueueRecoveryCompleteRequest)l_request);
        }
        else
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * 管理者外国株式SENDキューリカバリ処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者外国株式SENDキューリカバリサービス)submit更新」参照<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminFeqSendQueueRecoveryCompleteResponse
     * @@throws WEB3BaseException 
     * @@roseuid 4214980A032E
     */
    protected WEB3AdminFeqSendQueueRecoveryCompleteResponse submitUpdate(
        WEB3AdminFeqSendQueueRecoveryCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitUpdate(WEB3AdminFeqSendQueueRecoveryCompleteRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        //リクエストデータの整合性をチェックする。
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報()
        //ログイン情報より管理者インスタンスを取得する。
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();       
        
        //1.3 validate権限()
        //管理者権限チェックを行う。 
        //[validate権限()に指定する引数] 
        //機@能カテゴリコード：　@機@能カテゴリコード.外株（注文約定管理） 
        //is更新：　@true(更新あり) 
        //※機@能カテゴリコードは、DBレイアウト「管理者権限テーブル.xls#（補足資料）機@能カテゴリコード一覧」参照。
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, true);//WEB3BaseException
        
        //1.4 ArrayList()
        //エラー情報を設定するリストを生成する。
        List l_lisErrorInfo = new ArrayList();
        
        //1.5 更新件数を0で初期化する
        int l_intIncreaseNum = 0;
        
        //1.6 (*) リクエスト.キューID一覧の件数分、処理を繰り返す。
        int l_intQueueIdLength = l_request.queueIdList.length;
        for (int i = 0; i < l_intQueueIdLength; i ++)
        {
            //1.6.1 管理者外国株式SENDキューリカバリTransactionCallback(String)
            //管理者外国株式SENDキューリカバリTransactionCallbackオブジェクトを生成する。 
            //[コンストラクタに指定する引数] 
            //キューID：　@リクエスト.キューID一覧[i]
            WEB3AdminFeqSendQueueRecoveryTransactionCallback l_transactionCallback = 
                new WEB3AdminFeqSendQueueRecoveryTransactionCallback(l_request.queueIdList[i]);
            
            //1.6.2 doTransaction(int, TransactionCallback)
            //DBトランザクション処理を実施する。 
            //[doTransaction()に指定する引数] 
            //トランザクション属性：　@TX_CREATE_NEW 
            //TransactionCallback：　@生成した管理者外国株式SENDキューリカバリTransactionCallbackオブジェクト
            boolean l_blnCallBackExp = false;
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_queryProcessor.doTransaction(
               		QueryProcessor.TX_CREATE_NEW,
                    l_transactionCallback);
            }
            //1.6.3 doTransaction()がDataCallbackExceptionをスローする場合
            catch(DataCallbackException l_ex)
            {
                l_lisErrorInfo.add(l_ex.getMessage());
                l_blnCallBackExp = true;
            }
            catch (DataNetworkException l_dataNetworkException)
            {
                log.error("DBへのアクセスに失敗しました。", l_dataNetworkException);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_dataNetworkException.getMessage(),
                    l_dataNetworkException);
            }
            catch (DataQueryException l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            if (!l_blnCallBackExp)
            {
                //1.6.4 doTransaction()が正常終了した場合
                //1.6.4.1 更新件数をインクリメントする
                l_intIncreaseNum ++;            
            }
        }
        
        //1.7 エラー情報のリスト.size() > 0 の場合、エラー情報を取得する
        //1.7.1 toArray(Object[])
        //エラー情報のリストの内容を、Stringの配列に設定する。 
        //[toArray()に指定する引数] 
        //配列：　@エラー情報（生成したStringの配列）
        String[] l_strErrorInfo = null;
        if (l_lisErrorInfo.size() > 0)
        {
            l_strErrorInfo = new String[l_lisErrorInfo.size()];
            l_lisErrorInfo.toArray(l_strErrorInfo);
        }
        
        //1.8 createResponse()
        WEB3AdminFeqSendQueueRecoveryCompleteResponse l_response = 
            (WEB3AdminFeqSendQueueRecoveryCompleteResponse)l_request.createResponse();
        
        //1.9 プロパティセット
        l_response.errorInfomations = l_strErrorInfo;    
        l_response.updateNumber = l_intIncreaseNum + "";    
      
        log.exiting(STR_METHOD_NAME);
        //1.10 return レスポンスデータ
        return l_response;
    }
    
    /**
     * (管理者外国株式SENDキューリカバリTransactionCallback)<BR>
     * トランザクション処理を実施する内部クラス<BR>
     * （トランザクション属性：TransactionalInterceptor.TX_CREATE_NEW）<BR>
     * 
     * @@roseuid 4214980A032E
     */
    public class WEB3AdminFeqSendQueueRecoveryTransactionCallback implements TransactionCallback
    {
        /**
         * ログ出力ユーティリティ。
         */
        private WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AdminFeqSendQueueRecoveryTransactionCallback.class);
        
        /**
         * (キューID)<BR>
         * キューID<BR>
         */
        private String queueId;
        
        /**
         * コンストラクタ。<BR>
         * <BR>
         * 引数で指定されたオブジェクトを、インスタンス変数にセットする。<BR>
         * @@param l_strQuereId - (キューID)<BR>
         * キューID<BR>
         * @@roseuid 42D0CED203D8
         */
        public WEB3AdminFeqSendQueueRecoveryTransactionCallback(String l_strQueueId) 
        {
            this.queueId = l_strQueueId;
        }
       
        /**
         * トランザクション処理を実施する。<BR>
         * <BR>
         * シーケンス図<BR>
         * 「（管理者外国株式SENDキューリカバリトランザクション）process」参照<BR>
         * <BR>
         * @@return Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 4214980A032E
         */
        public Object process() throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = " process() ";
            log.entering(STR_METHOD_NAME);
            
            //1.1 条件に該当する(外国株取引)SEND_Qテーブルのレコードを取得する
            String l_strWhere = " queue_id = ? ";
            String l_strCondition = " for update ";

            Object l_bindVars[] ={new Long(this.queueId)};
            
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            List l_lisRows = l_queryProcesser.doFindAllQuery(
                SleSendQRow.TYPE,
                l_strWhere, 
                l_strCondition, 
                l_bindVars);
            DataCallbackException l_DataCallbackException = null;
            //1.2 レコードが取得できなかった場合、DataCallbackExceptionをスローする
            if (l_lisRows == null || l_lisRows.isEmpty())
            {
                log.exiting(STR_METHOD_NAME);
                l_DataCallbackException = 
                    new DataCallbackException(
                        this.queueId + ":レコードが存在しません",
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                throw l_DataCallbackException;
            }
            
            SleSendQRow l_sleSendQRow = (SleSendQRow)l_lisRows.get(0);
            
            //1.3 取得した(外国株取引)SEND_QテーブルParams.get電子メール送信日時() != null 場合、
            //DataCallbackExceptionをスローする
            SleSendQParams l_sleSendQParams = new SleSendQParams(l_sleSendQRow);
            if (l_sleSendQParams.getSendProcessDateTime() != null)
            {
                log.exiting(STR_METHOD_NAME);
                l_DataCallbackException = 
                    new DataCallbackException(
                        l_sleSendQParams.getOrderEmpCode() + ":メール送信濟み",
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                throw l_DataCallbackException;
            }
            
            //1.4 getOrderUnit
            //外国株式注文単位オブジェクトを取得する。 
            //[getOrderUnit()に指定する引数] 
            //注文単位ID： (外国株取引)SEND_QテーブルParams.get注文単位ID()の戻り値
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = 
                l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            WEB3FeqOrderManager l_orderMgr = 
                (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
            WEB3FeqOrderUnit l_feqOrderUnit = null;
            try
            {
                l_feqOrderUnit = 
                    (WEB3FeqOrderUnit)l_orderMgr.getOrderUnit(l_sleSendQParams.getOrderUnitId());
            }
            catch (NotFoundException l_ex)
            {
                log.error("テーブルに該当するデータがありません。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            } 
            
            //1.5 get市場
            //市場オブジェクトを取得する。
            WEB3GentradeMarket l_market = null;
            try
            {
                l_market = l_feqOrderUnit.getMarket();
            }
            catch (WEB3BaseException l_ex)
            {
                log.error("テーブルに該当するデータがありません。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            //1.6 isシステム連動
            //1.7 isシステム連動()の戻り値 == true 場合、DataCallbackExceptionをスローする
            if (l_market.isSystemInterLock())
            {
                log.exiting(STR_METHOD_NAME);
                l_DataCallbackException = 
                    new DataCallbackException(
                        l_sleSendQParams.getOrderEmpCode() + ":" + l_sleSendQParams.getMarketCode() + ":市場連動中",
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                throw l_DataCallbackException;
            }
            
            //1.8 getAttribute
            //スレッドローカルより、取引時間コンテキストを取得する。 
            //[getAttribute()に指定する引数] 
            //属性名：　@WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH
            WEB3GentradeTradingClendarContext l_tradingClendarContext =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            
            //1.9 set部店コード
            //部店コードを取得する。 
            //[get部店コード()に指定する引数] 
            //部店コード：　@(外国株取引)SEND_QテーブルParams.get部店コード()
            l_tradingClendarContext.setBranchCode(l_sleSendQParams.getBranchCode());
            
            //1.10 set市場コード
            //市場コードを取得する。 
            //[get市場コード()に指定する引数] 
            //市場コード：　@(外国株取引)SEND_QテーブルParams.get市場コード()
            l_tradingClendarContext.setMarketCode(l_sleSendQParams.getMarketCode());
            
            //1.11 setTimestamp
            //現在日時をセットする。
            try
            {
                WEB3GentradeTradingTimeManagement.setTimestamp();
            }
            catch (WEB3SystemLayerException l_ex)
            {
                log.error("予期しないシステムエラーが発生しました。", l_ex);
                throw new WEB3BaseRuntimeException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            WEB3FeqMailSenderService l_mailSenderService = 
                (WEB3FeqMailSenderService)Services.getService(WEB3FeqMailSenderService.class);
            
            try
            {
	            //1.12 (外国株取引)SEND_QテーブルParams.getオペレータタイプ()の戻り値 == "0：新規" 場合
	            if (SleSendqOpTypeEnum.NEW_ORDER.equals(l_sleSendQParams.getOpType()))
	            {
	                //1.12.1 create新規注文Mail
	                //新規注文メールを生成する。 
	                //[create新規注文Mail()に指定する引数] 
	                //注文単位： 取得した外国株式注文単位
	                l_mailSenderService.createNewOrderMail(l_feqOrderUnit);
	            }
	            //1.13 (外国株取引)SEND_QテーブルParams.getオペレータタイプ()の戻り値 == "2：取消" 場合
	            else if (SleSendqOpTypeEnum.CANCEL_ORDER.equals(l_sleSendQParams.getOpType()))
	            {
	                //1.13.1 create取消注文Mail
	                //取消注文メールを生成する。 
	                //[create取消注文Mail()に指定する引数] 
	                //注文単位： 取得した外国株式注文単位
	                l_mailSenderService.createCancelOrderMail(l_feqOrderUnit);
	            }
	            //1.14 (外国株取引)SEND_QテーブルParams.getオペレータタイプ()の戻り値 == "1：訂正" 場合
	            else if (SleSendqOpTypeEnum.CHANGE_ORDER.equals(l_sleSendQParams.getOpType()))
	            {
	                //1.14.1 create訂正注文Mail
	                //訂正注文メールを生成する。 
	                //[create訂正注文Mail()に指定する引数] 
	                //注文単位： 取得した外国株式注文単位 
	                l_mailSenderService.createChangeOrderMail(l_feqOrderUnit);
	            }
            }
            //1.15 メール送信処理が例外をスローする場合、DataCallbackExceptionをスローする
            catch (WEB3BaseException l_ex)
            {
                l_DataCallbackException =
                    new DataCallbackException(
                        l_sleSendQParams.getOrderEmpCode() + ":" + l_ex.getErrorInfo().getErrorMessage(),
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                throw l_DataCallbackException;
            }              
            
            //1.16 (外国株取引)SEND_Qテーブルを更新する
            l_sleSendQParams.setSendProcessDateTime(GtlUtils.getSystemTimestamp());
            l_sleSendQParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_sleSendQParams.setStatus(SleSendqProcStatusEnum.PROCESSED);
            l_sleSendQParams.setConfirmedBySleRcvdQ(BooleanEnum.TRUE);
            try 
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_queryProcessor.doUpdateQuery(l_sleSendQParams);
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
            
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}
@
