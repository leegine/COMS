head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AsynAdminEquityManualExpireServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （非同期）管理者・株式手動失効サービスImpl(WEB3AsynAdminEquityManualExpireServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/5/30 呉艶飛 (中訊) 新規作成
                   2006/06/08　@郭英(中訊) 仕様変更 モデル109
*/

package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3CloseNotifyTypeDef;
import webbroker3.common.define.WEB3DaemonTriggerStatusDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ExpirationReasonCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3OnlineServiceDiv;
import webbroker3.common.define.WEB3RunStatusDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.eqtypeadmin.WEB3AdminPMEquityDataManager;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseMainRequest;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.data.HostEqtypeCloseOrderNotifyParams;
import webbroker3.equity.data.HostEqtypeOrderAllParams;
import webbroker3.equity.data.HostEqtypeOrderAllRow;
import webbroker3.equity.data.HostEqtypeSwapParams;
import webbroker3.equity.data.HostEqtypeSwapRow;
import webbroker3.equity.service.delegate.WEB3EquityReceiveCloseOrderUnitService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOnlineRunStatus;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.DaemonTriggerParams;
import webbroker3.gentrade.data.DaemonTriggerRow;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (（非同期）管理者・株式手動失効サービスImpl)<BR>
 *（WEB3AsynAdminEquityManualExpireServiceImpl）<BR>
 *（非同期）管理者・株式手動失効サービス実装クラス<BR>
 * <BR>
 * 失効処理を非同期で行う。<BR>
 * <BR>
 * @@author 呉艶飛
 * @@version 1.0
 */

public class WEB3AsynAdminEquityManualExpireServiceImpl implements Runnable 
{
    /**
     * ログユーティリティ<BR>
     */
     private static WEB3LogUtility log = 
         WEB3LogUtility.getInstance(WEB3AsynAdminEquityManualExpireServiceImpl.class);
     
    /**
     * (リクエストデータ)<BR>
     * 管理者・株式手動失効メインリクエストオブジェクト<BR>
     */
    public WEB3AdminEquityManualLapseMainRequest request;
    
    /**
     * @@roseuid 447ACD830157
     */
    public WEB3AsynAdminEquityManualExpireServiceImpl() 
    {
     
    }
    
    /**
     * (（非同期）管理者・株式手動失効サービスImpl)<BR>
     * デフォルトコンストラクタ。<BR>
     * <BR>
     * 引数をthis.リクエストデータにセットする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・株式手動失効メインリクエストオブジェクト
     * @@return WEB3AsynAdminEquityManualExpireServiceImpl
     * @@roseuid 44696851025E
     */
    public WEB3AsynAdminEquityManualExpireServiceImpl(WEB3AdminEquityManualLapseMainRequest l_request) 
    {
        this.request = l_request;
    }
    
    /**
     * （非同期）手動失効処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（（非同期）管理者株式手動失効サービス）run」参照。<BR>
     * @@roseuid 44693CE60111
     */
    public void run() 
    {

        final String STR_METHOD_NAME = "run()";
        log.entering(STR_METHOD_NAME);
  
        //実行ステータス区分：　@"処理済"
        String l_strRunStatusDiv = WEB3RunStatusDivDef.DEALED;        
        WEB3GentradeOnlineRunStatus l_onlineRunStatus = null;    
        List l_lisDaemonTrigger = null;
        try
        {
            //1.1管理者・株式手動失効デーモントリガーTransactionCallbackを生成する。 
            WEB3AdminEquityManualExpireDaemonTriggerTransactionCallback l_triggerTransactionCallBack 
                = new WEB3AdminEquityManualExpireDaemonTriggerTransactionCallback(request.threadNo.longValue()); 
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor(); 
            
            //1.2トランザクションを開始する。
            //[引数] 
            //arg0：　@TX_JOIN_EXISTING 
            //arg1：　@生成したTransactionCallback
            l_lisDaemonTrigger = (List)l_queryProcessor.doTransaction(
                QueryProcessor.TX_JOIN_EXISTING, 
                l_triggerTransactionCallBack);
            
            //トランザクション属性は、TX_JOIN_EXISTING。
            //※レコードのロックを本スレッド中有効とする為。nullが返却された場合は、処理を終了する。
            if (l_lisDaemonTrigger == null)
            {
                log.exiting(STR_METHOD_NAME);
                return;
            }

            //1.3管理者・株式手動失効オンライン実行結果TransactionCallbackを生成する。
            //[引数] 
            //証券会社コード：　@this.リクエストデータ.証券会社コード 
            //口座IDFrom：　@this.リクエストデータ.From口座ID 
            //口座IDTo：　@this.リクエストデータ.To口座ID
            WEB3AdminEquityManualExpireOnlineRunStatusTransactionCallback l_statusTransactionCallBack = 
                new WEB3AdminEquityManualExpireOnlineRunStatusTransactionCallback(
                    request.institutionCode, request.accountIdFrom.longValue(), request.accountIdTo.longValue());
            
            //1.4トランザクションを開始する。 
            l_onlineRunStatus = (WEB3GentradeOnlineRunStatus)l_queryProcessor.doTransaction(QueryProcessor.TX_CREATE_NEW, l_statusTransactionCallBack);
        }
        catch (DataCallbackException l_dataCallbackException)
        {
            log.error("DBへのアクセスに失敗しました。", l_dataCallbackException);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dataCallbackException.getMessage(),
                l_dataCallbackException);
        }
        catch (DataQueryException l_dataQueryException)
        {
            log.error("DBへのアクセスに失敗しました。", l_dataQueryException);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dataQueryException.getMessage(),
                l_dataQueryException);
        }
        catch (DataNetworkException l_dataNetworkException)
        {
            log.error("DBへのアクセスに失敗しました。", l_dataNetworkException);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dataNetworkException.getMessage(),
                l_dataNetworkException);
        }
             
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
           
        EqtypeOrderUnitRow[] l_orderUnitRows = null;           
        try
        {
            WEB3GentradeInstitution l_institution = 
                (WEB3GentradeInstitution)l_accountManager.getInstitution(this.request.institutionCode);
           
            //1.5手動失効対象となる注文単位の一覧を取得する。
            //(*)引数設定仕様
            //証券会社：　@this.リクエストデータ.証券会社コードに該当する証券会社
            //部店コード：　@this.リクエストデータ.失効対象注文条件の同名項目
            //銘柄コード：　@this.リクエストデータ.失効対象注文条件の同名項目
            //市場コード一覧：　@this.リクエストデータ.失効対象注文条件の同名項目
            //取引区分一覧：　@this.リクエストデータ.失効対象注文条件の同名項目
            //弁済区分一覧：　@this.リクエストデータ.失効対象注文条件の同名項目
            //顧客コード：　@リクエストデータ.失効対象注文条件の同名項目
            //口座IDFrom：　@this.リクエストデータ.From口座ID
            //口座IDTo：　@this.リクエストデータ.To口座ID
            l_orderUnitRows = WEB3AdminPMEquityDataManager.getManualExpireOrderUnits(
                l_institution,
                this.request.equityLapseTargetOrderCondition.branchCode,
                this.request.equityLapseTargetOrderCondition.productCode,
                this.request.equityLapseTargetOrderCondition.marketList,
                this.request.equityLapseTargetOrderCondition.tradingTypeList,
                this.request.equityLapseTargetOrderCondition.repaymentList,
                this.request.equityLapseTargetOrderCondition.accountCode,
                this.request.accountIdFrom,
                this.request.accountIdTo);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            l_strRunStatusDiv = WEB3RunStatusDivDef.ERROR;
        }
       
        //1.6(*)get手動失効対象注文単位一覧()の戻り値の要素（=EqtypeOrderUnitRow）数分Loop処理
        if (l_orderUnitRows != null)
        {
            for (int i = 0; i < l_orderUnitRows.length; i++)
            {
                EqtypeOrderUnitRow l_eqtypeOrderUnitRow = l_orderUnitRows[i]; 
                String l_strtradingTimeType = null;
               
                if (OrderCategEnum.SWAP_MARGIN.equals(l_eqtypeOrderUnitRow.getOrderCateg()))
                {
                    l_strtradingTimeType = WEB3TradingTimeTypeDef.SWAP;
                }
                else
                {
                    l_strtradingTimeType = WEB3TradingTimeTypeDef.EQUITY;
                }
                                                        
                try
                {
                    //1.6.1取引カレンダコンテキストの値を、注文単位のデータで更新する。 
                    //(*)引数設定仕様
                    //証券会社コード：　@this.リクエストデータ.証券会社コード
                    //部店ID：　@処理対象の要素.部店ID
                    //市場ID：　@処理対象の要素.市場ID
                    //受付時間区分：　@
                    //　@[処理対象の要素.注文カテゴリ == "現引現渡注文"の場合]
                    //　@　@"現引・現渡"をセット。
                    //　@[上記以外]
                    //　@　@"株式・信用"をセット。 
                    WEB3AdminPMEquityDataManager.resetTradingCalContext(
                        this.request.institutionCode,
                        new Long(l_eqtypeOrderUnitRow.getBranchId()),
                        new Long(l_eqtypeOrderUnitRow.getMarketId()),
                        l_strtradingTimeType);
                   
                    //1.6.2管理者・株式手動失効TransactionCallbackを生成する。
                    WEB3AdminEquityManualExpireTransactionCallback l_expireTransactionCallback = 
                        new WEB3AdminEquityManualExpireTransactionCallback(l_eqtypeOrderUnitRow);
                   
                    //1.6.3トランザクションを開始する。
                    QueryProcessor l_queryProcessor = Processors.getDefaultProcessor(); 
                    l_queryProcessor.doTransaction(QueryProcessor.TX_CREATE_NEW, l_expireTransactionCallback);
                }
                //例外がスローされた場合は、注文IDをERRORでログ出力し、次の要素へ処理を移行する。
                catch (Exception l_ex)
                {
                    log.error("注文ID:" + l_eqtypeOrderUnitRow.getOrderId());
                    l_strRunStatusDiv = WEB3RunStatusDivDef.ERROR;
                    continue;
                }
            }                                                             
        }
    
        //1.7 (*)ThreadLocalのTIMESTAMP_TAGをnullでリセットする。
        //各テーブルの更新日付に現在時刻をセットする為。
        //※リセットしなければ、最後に失効した注文と同じ時刻が使用される。
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        try
        {   
            //1.8 update実行ステータス区分(実行ステータス区分 : String)
            //(*)引数設定仕様（取得済みのオブジェクトに対してコール。）
            //実行ステータス区分：　@"処理済"
            //　@※失効処理で例外がスローされた場合は、"エラー"。
            l_onlineRunStatus.updateRunStatusDiv(l_strRunStatusDiv);
            
            //1.9 (*)排他ロックしたデーモントリガーテーブルのレコードを"未稼働"でupdateする。
            DaemonTriggerParams l_daemonTriggerParams =
                new DaemonTriggerParams((DaemonTriggerRow) l_lisDaemonTrigger.get(0));
            //更新仕様は、「株式手動失効_デーモントリガーテーブル.xls」＃
            //「（手動失効）[終了時]デーモントリガーテーブル」シート参照。
            //処理状態に"未稼動"をsetする。
            l_daemonTriggerParams.setTriggerStatus(WEB3DaemonTriggerStatusDef.THREAD_IDLE);
            //現在時刻をsetする。
            l_daemonTriggerParams.setTriggerDate(GtlUtils.getSystemTimestamp());
            
            WEB3DataAccessUtility.updateRow(l_daemonTriggerParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorInfo().getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (DataException l_ex)
        {
        	log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                new ErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (管理者・株式手動失効TransactionCallback)<BR>
     * 管理者・株式手動失効TransactionCallback<BR>
     * （トランザクション属性：TX_CREATE_NEW）<BR>
     */
    public class WEB3AdminEquityManualExpireTransactionCallback implements TransactionCallback 
    {
        
        /**
         * (注文単位Row)<BR>
         * 注文単位Rowオブジェクト
         */
        public EqtypeOrderUnitRow eqtypeOrderUnitRow;
        
        /**
         * @@roseuid 447ACD830196
         */
        public WEB3AdminEquityManualExpireTransactionCallback() 
        {
         
        }
        
        /**
         * (管理者・株式手動失効TransactionCallback)<BR>
         * コンストラクタ。<BR>
         * <BR>
         * 引数を自身の同名項目にセットする。<BR>
         * @@param l_eqtypeOrderUnitRow - (注文単位Row)<BR>
         * 注文単位Rowオブジェクト
         * @@return 
         * WEB3AsynAdminEquityManualExpireServiceImpl.WEB3AdminEquityManualExpireTransactionCallback
         * @@roseuid 44693E570399
         */
        public WEB3AdminEquityManualExpireTransactionCallback(EqtypeOrderUnitRow l_eqtypeOrderUnitRow) 
        {
            this.eqtypeOrderUnitRow = l_eqtypeOrderUnitRow;
        }
        
        /**
         * 手動失効処理を行う。<BR>
         * <BR>
         * シーケンス図<BR>
         * 「（管理者株式手動失効TransactionCallback）process」参照。<BR>
         * @@return java.lang.Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 44693E5703B8
         */
        public Object process() throws DataNetworkException, DataQueryException, DataCallbackException 
        {
            final String STR_METHOD_NAME = " process()";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                WEB3GentradeAccountManager l_accountManager =
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                
                //1.1顧客を取得する。 
                //[引数] 
                //arg0：　@this.注文単位Row.口座ID
                WEB3GentradeMainAccount l_mainAccount = 
                    (WEB3GentradeMainAccount)l_accountManager.getMainAccount(this.eqtypeOrderUnitRow.getAccountId());
                
                MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
                //1.2口座をロックする。
                //[引数] 
                //証券会社コード：　@getMainAccount()の戻り値.証券会社コード 
                //部店コード：　@getMainAccount()の戻り値.部店コード 
                //口座コード：　@getMainAccount()の戻り値.口座コード
                l_accountManager.lockAccount(
                    l_mainAccountRow.getInstitutionCode(), 
                    l_mainAccountRow.getBranchCode(), 
                    l_mainAccountRow.getAccountCode());
                
                List l_listRecords = new ArrayList();
                
                StringBuffer l_sbWhere = new StringBuffer();
                l_sbWhere.append("institution_code = ? ");
                l_sbWhere.append("and branch_code = ? ");
                l_sbWhere.append("and account_code = ? ");
                l_sbWhere.append("and order_request_number = ? ");
                l_sbWhere.append("and status= ?");
                
                Object[] l_objWhere = {
                    l_mainAccountRow.getInstitutionCode(), 
                    l_mainAccountRow.getBranchCode(), 
                    l_mainAccountRow.getAccountCode(),
                    this.eqtypeOrderUnitRow.getOrderRequestNumber(),
                    WEB3StatusDef.NOT_DEAL};
                
                String l_strCondition = " for update nowait ";
                
                QueryProcessor l_queryProcessor = null;
                l_queryProcessor = Processors.getDefaultProcessor();
                
                //1.3現引現渡注文（this.注文単位Row.注文カテゴリ == "現引現渡注文"）の場合
                if (OrderCategEnum.SWAP_MARGIN.equals(this.eqtypeOrderUnitRow.getOrderCateg()))
                {
                    //1.3.1(*)現引現渡キューテーブルの該当レコードをロックする。
                    //(*)以下の条件にて現引現渡キューテーブルを
                    //行ロックオプション（"for update nowait"）で読み込む。
                    //証券会社コード　@＝　@getMainAccount()の戻り値.証券会社コード
                    //部店コード　@＝　@getMainAccount()の戻り値.部店コード
                    //口座コード　@＝　@getMainAccount()の戻り値.口座コード
                    //識別コード　@＝　@this.注文単位Row.識別コード
                    //処理区分    　@＝　@"未処理"                    
                    l_listRecords = l_queryProcessor.doFindAllQuery(
                        HostEqtypeSwapRow.TYPE, l_sbWhere.toString(), l_strCondition, l_objWhere);
                }
                //1.4(*)上記以外の場合
                else
                {
                    //1.4.1(*)株式注文取引キューテーブルの該当レコードをロックする。
                    //(*)以下の条件にて株式注文取引キューテーブルを
                    //行ロックオプション（"for update nowait"）で読み込む。
                    //証券会社コード　@＝　@getMainAccount()の戻り値.証券会社コード
                    //部店コード　@＝　@getMainAccount()の戻り値.部店コード
                    //口座コード　@＝　@getMainAccount()の戻り値.口座コード
                    //識別コード　@＝　@this.注文単位Row.識別コード
                    //処理区分（ステータス）　@＝　@"未処理"
                    l_listRecords = l_queryProcessor.doFindAllQuery(
                        HostEqtypeOrderAllRow.TYPE, l_sbWhere.toString(), l_strCondition, l_objWhere);
                }
                
                //1.5(*)キューレコードが取得できなかった場合
                if (l_listRecords.isEmpty())
                {
                    //1.5.1(*)"該当キューレコードなし"の文言と注文IDをデバッグログに出力し、処理を終了する。
                    log.debug("該当キューレコードなし");
                    log.debug("注文ID = " + this.eqtypeOrderUnitRow.getOrderId());
                    log.exiting(STR_METHOD_NAME);
                    return null;
                }
                
                //(*)1.6株式失効通知キューParamsを生成する。
                HostEqtypeCloseOrderNotifyParams l_orderNotityParams = new HostEqtypeCloseOrderNotifyParams();
                //(*)株式失効通知キューParamsインスタンスを生成し、以下のプロパティをセットする。
                //データコード  ＝　@"AI813"（株式失効通知）
                l_orderNotityParams.setRequestCode(WEB3HostRequestCodeDef.EQUITY_ORDER_CLOSE_NOTICE);
                //証券会社コード ＝　@getMainAccount()の戻り値.証券会社コード
                l_orderNotityParams.setInstitutionCode(l_mainAccountRow.getInstitutionCode());
                //部店コード       ＝　@getMainAccount()の戻り値.部店コード
                l_orderNotityParams.setBranchCode(l_mainAccountRow.getBranchCode());
                //口座コード       ＝　@getMainAccount()の戻り値.口座コード
                l_orderNotityParams.setAccountCode(l_mainAccountRow.getAccountCode());                
                //扱者コード       ＝　@null（固定）
                l_orderNotityParams.setTraderCode(null);
                //識別コード       ＝　@this.注文単位Row.識別コード
                l_orderNotityParams.setOrderRequestNumber(this.eqtypeOrderUnitRow.getOrderRequestNumber());
                //約定数量        ＝　@this.注文単位Row.約定数量
                l_orderNotityParams.setExecutedQuantity(this.eqtypeOrderUnitRow.getExecutedQuantity());
                //失効理由コード ＝　@"原注文破棄"
                l_orderNotityParams.setReasonCode(WEB3ExpirationReasonCodeDef.ORDER_CANCEL);
                //失効通知区分  ＝　@"失効"
                l_orderNotityParams.setCloseNotifyType(WEB3CloseNotifyTypeDef.CLOSE);
                //エラーメッセージ    ＝　@"株管理者失効済"
                //　@※エラーメッセージは、注文単位.注文エラー理由コードに記録される。
                l_orderNotityParams.setErrorMessage(WEB3ErrorReasonCodeDef.EQUITY_ADMIN_MANUAL_EXPIRED);
                //処理区分        ＝　@"未処理"
                l_orderNotityParams.setStatus(WEB3StatusDef.NOT_DEAL);
                
                WEB3EquityReceiveCloseOrderUnitService l_orderUnitService = 
                    (WEB3EquityReceiveCloseOrderUnitService)Services.getService(WEB3EquityReceiveCloseOrderUnitService.class);
                //1.7失効処理を行う。
                //[引数]
                //株式失効通知キューParams：　@生成したキューParams
                //注文単位：　@拡張株式注文マネージャ.toOrderUnit(this.注文単位Row)
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                WEB3EquityOrderManager l_orderMgr = 
                    (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
                EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderMgr.toOrderUnit(this.eqtypeOrderUnitRow);
                
                l_orderUnitService.execCloseOrder(l_orderNotityParams, l_orderUnit);
                
                //1.8補助口座を取得する。
                //[引数]
                //arg0：　@this.注文単位Row.口座ID
                //arg1：　@this.注文単位Row.補助口座ID
                WEB3GentradeSubAccount l_subAccount = 
                    (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                    this.eqtypeOrderUnitRow.getAccountId(), 
                    this.eqtypeOrderUnitRow.getSubAccountId());
                
                //1.9余力再計算を行う。
                //[引数]
                //補助口座：　@getSubAccount()の戻り値
                WEB3TPTradingPowerService l_tpTradingPowerReCalcService =
                    (WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);
                l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);
                
                //(*)1.10取得したキューレコードをupdateする。
                //(*)取得した株式注文取引キューまたは、現引現渡キューを
                //updateする。
                //更新内容については、DB更新仕様
                //株式手動失効_株式注文取引キューテーブル.xls」
                //「株式手動失効_現引現渡キューテーブル.xls」
                //参照。
                if (OrderCategEnum.SWAP_MARGIN.equals(this.eqtypeOrderUnitRow.getOrderCateg()))
                {
                    for (int i = 0; i < l_listRecords.size(); i++)
                    {
                        HostEqtypeSwapRow l_swapRow = (HostEqtypeSwapRow)l_listRecords.get(i);
                        HostEqtypeSwapParams l_swapParams = new HostEqtypeSwapParams(l_swapRow);
                        
                        //注文履歴番号 = （既存値） + 1
                        l_swapParams.setOrderActionSerialNo(l_swapRow.getOrderActionSerialNo() + 1);
                        //処理区分 = 4：管理者手動失効済
                        l_swapParams.setStatus(WEB3StatusDef.ADMIN_MANUAL_EXPIRED);
                        //更新日付 = 現在時刻
                        l_swapParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                        l_queryProcessor.doUpdateQuery(l_swapParams);
                    }

                }
                else
                {
                    for (int i = 0; i < l_listRecords.size(); i++)
                    {
                        HostEqtypeOrderAllRow l_orderAllRow = (HostEqtypeOrderAllRow)l_listRecords.get(i);
                        HostEqtypeOrderAllParams l_orderAllParams = new HostEqtypeOrderAllParams(l_orderAllRow);
                        
                        //注文履歴番号 = （既存値） + 1
                        l_orderAllParams.setOrderActionSerialNo(l_orderAllRow.getOrderActionSerialNo() + 1);
                        //処理区分 = 4：管理者手動失効済
                        l_orderAllParams.setStatus(WEB3StatusDef.ADMIN_MANUAL_EXPIRED);
                        //更新日付 = 現在時刻
                        l_orderAllParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                        l_queryProcessor.doUpdateQuery(l_orderAllParams);
                    }
                }
            }
            catch (NotFoundException l_dqex)
            {

                //例外をスローする
                log.error("DBへのアクセスに失敗しました。", l_dqex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dqex.getMessage(),
                    l_dqex);
            }
            catch (WEB3BaseException l_dqex)
            {
                //例外をスローする
                log.error( getClass().getName() + "." + STR_METHOD_NAME ,l_dqex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    l_dqex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dqex.getMessage(),
                    l_dqex);
            }
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
    
    /**
     * (管理者・株式手動失効オンライン実行結果TransactionCallback)<BR>
     * 管理者・株式手動失効オンライン実行結果TransactionCallback<BR>
     * <BR>
     * 手動失効の処理開始／終了を<BR>
     * オンライン実行結果テーブルに記録する。<BR>
     */
    public class WEB3AdminEquityManualExpireOnlineRunStatusTransactionCallback implements TransactionCallback 
    {
        
        /**
         * (証券会社コード)<BR>
         * 証券会社コード
         */
        public String institutionCode;
        
        /**
         * (From口座ID)<BR>
         * From口座ID
         */
        public long accountIdFrom;
        
        /**
         * (To口座ID)<BR>
         * To口座ID
         */
        public long accountIdTo;
        
        /**
         * @@roseuid 447ACD8301E4
         */
        public WEB3AdminEquityManualExpireOnlineRunStatusTransactionCallback() 
        {
         
        }
        
        /**
         * (管理者・株式手動失効オンライン実行結果TransactionCallback)<BR>
         * コンストラクタ。<BR>
         * <BR>
         * 引数を自身の同名項目にセットする。<BR>
         * @@param l_strInstitutionCode - (証券会社コード)<BR>
         * 証券会社コード
         * @@param l_lngAccountIdFrom - (From口座ID)<BR>
         * From口座ID
         * @@param l_lngAccountIdTo - (To口座ID)<BR>
         * To口座ID
         * @@return 
         * WEB3AsynAdminEquityManualExpireServiceImpl.WEB3AdminEquityManualExpireOnlineRunStatusTransactionCallback
         * @@roseuid 44693F0300BA
         */
        public WEB3AdminEquityManualExpireOnlineRunStatusTransactionCallback(
            String l_strInstitutionCode, 
            long l_lngAccountIdFrom, 
            long l_lngAccountIdTo) 
        {
            this.institutionCode = l_strInstitutionCode;
            this.accountIdFrom = l_lngAccountIdFrom;
            this.accountIdTo = l_lngAccountIdTo;
        }
        
        /**
         * オンライン実行結果テーブルの該当レコードを<BR>
         * "処理中"でupdateする。<BR>
         * <BR>
         * １）　@オンライン実行結果.set処理中()メソッドをコールする。<BR>
         * 　@[set処理中()にセットするパラメータ]<BR>
         * 　@　@証券会社コード：　@this.証券会社コード<BR>
         * 　@　@銘柄タイプ：　@"株式"<BR>
         * 　@　@先物／オプション区分：　@"DEFAULT"<BR>
         * 　@　@オンラインサービス区分：　@"手動失効"<BR>
         * 　@　@From口座ID：　@this.From口座ID<BR>
         * 　@　@To口座ID：　@this.To口座ID<BR>
         * <BR>
         * ２）　@set処理中()の戻り値を返却する。<BR>
         * @@return java.lang.Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 44693F0300D9
         */
        public Object process() throws DataNetworkException, DataQueryException, DataCallbackException 
        {
            final String STR_METHOD_NAME = " process()";
            log.entering(STR_METHOD_NAME);
            
            //１）　@オンライン実行結果.set処理中()メソッドをコールする。
            WEB3GentradeOnlineRunStatus l_onlineRunStatus = null;
            try
            {
                l_onlineRunStatus = WEB3GentradeOnlineRunStatus.setDealing(
                    this.institutionCode, 
                    ProductTypeEnum.EQUITY, 
                    WEB3FuturesOptionDivDef.DEFAULT, 
                    WEB3OnlineServiceDiv.MANUAL_EXPIRE, 
                    this.accountIdFrom, 
                    this.accountIdTo);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
                        
            //２）　@set処理中()の戻り値を返却する。
            log.exiting(STR_METHOD_NAME);
            return l_onlineRunStatus;
        }
    }
    
    /**
     * (管理者・株式手動失効デーモントリガーTransactionCallback)<BR>
     * 管理者・株式手動失効デーモントリガーTransactionCallback<BR>
     * （トランザクション属性：TX_JOIN_EXISTING）<BR>
     */
    public class WEB3AdminEquityManualExpireDaemonTriggerTransactionCallback implements TransactionCallback 
    {
        
        /**
         * (スレッドNo)<BR>
         * スレッドNo
         */
        public long threadNo;
        
        /**
         * @@roseuid 447ACD830222
         */
        public WEB3AdminEquityManualExpireDaemonTriggerTransactionCallback() 
        {
         
        }
        
        /**
         * (管理者・株式手動失効デーモントリガーTransactionCallback)<BR>
         * コンストラクタ。<BR>
         * <BR>
         * 引数を自身の同名項目にセットする。<BR>
         * @@param l_lngThreadNo - (スレッドNo)<BR>
         * スレッドNo
         * @@return 
         * WEB3AsynAdminEquityManualExpireServiceImpl.WEB3AdminEquityManualExpireD
         * aemonTriggerTransactionCallback
         * @@roseuid 44744A1901B1
         */
        public WEB3AdminEquityManualExpireDaemonTriggerTransactionCallback(long l_lngThreadNo) 
        {
            this.threadNo = l_lngThreadNo;
        }
        
        /**
         * this.スレッドNoに該当するデーモントリガーテーブルの<BR>
         * レコードをロックする。<BR>
         * <BR>
         * １）　@DB検索<BR>
         * 　@以下の条件に該当するデーモントリガーテーブルの<BR>
         * 　@レコードを、"for update nowait"オプションで読み込む。<BR>
         * <BR>
         * 　@[条件]<BR>
         * 　@　@スレッド番号 = this.スレッドNo<BR>
         * <BR>
         * ２）　@検索結果を返却する。<BR>
         * 　@※検索結果が取得できなかった場合、処理スレッドの<BR>
         * 　@占有ロックに失敗した旨をERRORでログに出力し、nullを返却する。<BR>
         * @@return java.lang.Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 44744A860106
         */
        public Object process() throws DataNetworkException, DataQueryException, DataCallbackException 
        {
            //１）　@DB検索
            //以下の条件に該当するデーモントリガーテーブルの
            //レコードを、"for update nowait"オプションで読み込む。
            final String STR_METHOD_NAME = " process()";
            log.entering(STR_METHOD_NAME);

            String l_strWhere = " thread_no = ? ";
            String l_strCondition = " for update nowait ";
            Object l_bindVars[] = { new Long(threadNo) };
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            List l_lisRows = l_queryProcesser.doFindAllQuery(
                DaemonTriggerRow.TYPE,
                l_strWhere,
                l_strCondition, 
                l_bindVars);
            //２）　@検索結果を返却する。
            //検索結果が取得できなかった場合、処理スレッドの<BR>
            // 占有ロックに失敗した旨をERRORでログに出力し、nullを返却する。
            if (l_lisRows.isEmpty())
            {
                log.error("処理スレッドの占有ロックに失敗した");
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            else
            {            
                log.exiting(STR_METHOD_NAME);
                return l_lisRows;
            }

        }
    }
}
@
