head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AsynAdminIfoManualExpireServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （非同期）管理者・先物OP手動失効サービスImpl(WEB3AsynAdminIfoManualExpireServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/01/30　@謝旋(中訊) 新規作成
Revision History : 2007/04/05　@謝旋(中訊) 【DEOS】受入テスト障害連絡（U03004）
*/

package webbroker3.ifoadmin.service.delegate.stdimpls;

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
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoOrderManagerImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3CloseNotifyTypeDef;
import webbroker3.common.define.WEB3DaemonTriggerStatusDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ExpirationReasonCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OnlineServiceDiv;
import webbroker3.common.define.WEB3RunStatusDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOnlineRunStatus;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.DaemonTriggerParams;
import webbroker3.gentrade.data.DaemonTriggerRow;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.data.HostFotypeOrderAllParams;
import webbroker3.ifo.service.delegate.WEB3IfoCloseNotifyUnitService;
import webbroker3.ifo.service.delegate.WEB3IfoFrontOrderService;
import webbroker3.ifoadmin.WEB3AdminIfoDataManager;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseMainRequest;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (（非同期）管理者・先物OP手動失効サービスImpl)<BR>
 *（WEB3AsynAdminIfoManualExpireServiceImpl）<BR>
 *（非同期）管理者・先物OP手動失効サービス実装クラス<BR>
 * <BR>
 * 失効処理を非同期で行う。<BR>
 * <BR>
 * @@author 謝旋
 * @@version 1.0
 */

public class WEB3AsynAdminIfoManualExpireServiceImpl implements Runnable
{
    /**
     * ログユーティリティ<BR>
     */
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3AsynAdminIfoManualExpireServiceImpl.class);

    /**
     * (リクエストデータ)<BR>
     * （非同期）管理者・先物OP手動失効メインリクエストオブジェクト<BR>
     */
    public WEB3AdminIfoManualLapseMainRequest request;

    /**
     * @@roseuid 447ACD830157
     */
    public WEB3AsynAdminIfoManualExpireServiceImpl()
    {
     
    }

    /**
     * (（非同期）管理者・先物OP手動失効サービスImpl)<BR>
     * デフォルトコンストラクタ。<BR>
     * <BR>
     * 引数をthis.リクエストデータにセットする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * （非同期）管理者・先物OP手動失効メインリクエストオブジェクト
     * @@return WEB3AsynAdminIfoManualExpireServiceImpl
     * @@roseuid 44696851025E
     */
    public WEB3AsynAdminIfoManualExpireServiceImpl(WEB3AdminIfoManualLapseMainRequest l_request)
    {
        this.request = l_request;
    }

    /**
     * （非同期）手動失効処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（（非同期）管理者先物OP手動失効サービス）run」参照。<BR>
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
            // （非同期）管理者・先物OP手動失効デーモントリガーTransactionCallbackを生成する。
            WEB3AdminIfoManualExpireDaemonTriggerTransactionCallback l_triggerTransactionCallBack = 
                new WEB3AdminIfoManualExpireDaemonTriggerTransactionCallback(request.threadNo.longValue());

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            // トランザクションを開始する。
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

            // （非同期）管理者・先物OP手動失効オンライン実行結果TransactionCallbackを生成する。
            //[引数] 
            //証券会社コード：　@this.リクエストデータ.証券会社コード 
            //口座IDFrom：　@this.リクエストデータ.From口座ID 
            //口座IDTo：　@this.リクエストデータ.To口座ID
            WEB3AdminIfoManualExpireOnlineRunStatusTransactionCallback l_statusTransactionCallBack =
                new WEB3AdminIfoManualExpireOnlineRunStatusTransactionCallback(
                    request.institutionCode, request.accountIdFrom.longValue(), request.accountIdTo.longValue());
            
            // トランザクションを開始する。 
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

        IfoOrderUnitRow[] l_orderUnitRows = null;
        try
        {
            WEB3GentradeInstitution l_institution =
                (WEB3GentradeInstitution)l_accountManager.getInstitution(this.request.institutionCode);

            // get銘柄(証券会社, String, String, String, String, String)
            WEB3IfoProductImpl l_ifoProductImpl = WEB3AdminIfoDataManager.getProduct(l_institution,
                request.ifoLapseTargetOrderCondition.fuOpDiv,
                request.ifoLapseTargetOrderCondition.targetProductCode,
                request.ifoLapseTargetOrderCondition.delivaryMonth,
                request.ifoLapseTargetOrderCondition.strikePrice,
                request.ifoLapseTargetOrderCondition.opProductType);

            String l_strProductId = null;
            if (l_ifoProductImpl != null) 
            {
                l_strProductId = Long.toString(l_ifoProductImpl.getProductId());
            }

            // 手動失効対象となる注文単位の一覧を取得する。
            //(*)引数設定仕様
            //証券会社：　@this.リクエストデータ.証券会社コードに該当する証券会社
            //部店コード：　@this.リクエストデータ.失効対象注文条件の同名項目
            //銘柄ID：　@get銘柄()のの戻り値.銘柄ID　@※銘柄が取得できた場合
            //取引区分一覧：　@this.リクエストデータ.失効対象注文条件の同名項目
            //顧客コード：　@リクエストデータ.失効対象注文条件の同名項目
            //口座IDFrom：　@this.リクエストデータ.From口座ID
            //口座IDTo：　@this.リクエストデータ.To口座ID
            l_orderUnitRows = WEB3AdminIfoDataManager.getManualExpireOrderUnits(
                l_institution,
                this.request.ifoLapseTargetOrderCondition.branchCode,
                l_strProductId,
                this.request.ifoLapseTargetOrderCondition.tradingTypeList,
                this.request.ifoLapseTargetOrderCondition.accountCode,
                this.request.accountIdFrom,
                this.request.accountIdTo);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            l_strRunStatusDiv = WEB3RunStatusDivDef.ERROR;
        }

        // (*)get手動失効対象注文単位一覧()の戻り値の要素（=IfoOrderUnitRow）数分Loop処理
        if (l_orderUnitRows != null)
        {
            for (int i = 0; i < l_orderUnitRows.length; i++)
            {
                IfoOrderUnitRow l_ifoOrderUnitRow = l_orderUnitRows[i];

                try
                {
                    // 取引カレンダコンテキストの値を、注文単位のデータで更新する。
                    //(*)引数設定仕様
                    //証券会社コード：　@this.リクエストデータ.証券会社コード
                    //部店ID：　@処理対象の要素.部店ID
                    //市場ID：　@処理対象の要素.銘柄ID
                    WEB3AdminIfoDataManager.resetTradingCalContext(
                        this.request.institutionCode,
                        new Long(l_ifoOrderUnitRow.getBranchId()),
                        new Long(l_ifoOrderUnitRow.getProductId()));

                    // 管理者・先物OP手動失効TransactionCallbackを生成する。 
                    WEB3AdminIfoManualExpireTransactionCallback l_expireTransactionCallback = 
                        new WEB3AdminIfoManualExpireTransactionCallback(l_ifoOrderUnitRow);

                    // トランザクションを開始する。
                    QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                    l_queryProcessor.doTransaction(QueryProcessor.TX_CREATE_NEW, l_expireTransactionCallback);
                }
                //例外がスローされた場合は、注文IDをERRORでログ出力し、次の要素へ処理を移行する。
                catch (Exception l_ex)
                {
                    log.error("注文ID:" + l_ifoOrderUnitRow.getOrderId());
                    l_strRunStatusDiv = WEB3RunStatusDivDef.ERROR;
                    continue;
                }
            }                                                             
        }
    
        // (*)ThreadLocalのTIMESTAMP_TAGをnullでリセットする。
        //各テーブルの更新日付に現在時刻をセットする為。
        //※リセットしなければ、最後に失効した注文と同じ時刻が使用される。
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        try
        {   
            // update実行ステータス区分(実行ステータス区分 : String)
            //(*)引数設定仕様（取得済みのオブジェクトに対してコール。）
            //実行ステータス区分：　@"処理済"
            //　@※失効処理で例外がスローされた場合は、"エラー"。
            l_onlineRunStatus.updateRunStatusDiv(l_strRunStatusDiv);
            
            // (*)排他ロックしたデーモントリガーテーブルのレコードを"未稼働"でupdateする。
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
     * (（非同期）管理者・先物OP手動失効TransactionCallback)<BR>
     * （非同期）管理者・先物OP手動失効TransactionCallback<BR>
     * （トランザクション属性：TX_CREATE_NEW）<BR>
     */
    public class WEB3AdminIfoManualExpireTransactionCallback implements TransactionCallback 
    {
        /**
         * (注文単位Row)<BR>
         * 注文単位Rowオブジェクト
         */
        public IfoOrderUnitRow ifoOrderUnitRow;
        
        /**
         * @@roseuid 447ACD830196
         */
        public WEB3AdminIfoManualExpireTransactionCallback() 
        {
         
        }
        
        /**
         * (（非同期）管理者・先物OP手動失効TransactionCallback)<BR>
         * コンストラクタ。<BR>
         * <BR>
         * 引数を自身の同名項目にセットする。<BR>
         * @@param l_eqtypeOrderUnitRow - (注文単位Row)<BR>
         * 注文単位Rowオブジェクト
         * @@return 
         * WEB3AsynAdminIfoManualExpireServiceImpl.WEB3AdminIfoManualExpireTransactionCallback
         * @@roseuid 44693E570399
         */
        public WEB3AdminIfoManualExpireTransactionCallback(IfoOrderUnitRow l_ifoOrderUnitRow) 
        {
            this.ifoOrderUnitRow = l_ifoOrderUnitRow;
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
                
                //顧客を取得する。 
                //[引数] 
                //arg0：　@this.注文単位Row.口座ID
                WEB3GentradeMainAccount l_mainAccount = 
                    (WEB3GentradeMainAccount)l_accountManager.getMainAccount(this.ifoOrderUnitRow.getAccountId());
                
                MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
                //口座をロックする。
                //[引数] 
                //証券会社コード：　@getMainAccount()の戻り値.証券会社コード 
                //部店コード：　@getMainAccount()の戻り値.部店コード 
                //口座コード：　@getMainAccount()の戻り値.口座コード
                l_accountManager.lockAccount(
                    l_mainAccountRow.getInstitutionCode(), 
                    l_mainAccountRow.getBranchCode(), 
                    l_mainAccountRow.getAccountCode());
                
                // lock先物OP注文取引キュー(IfoOrderUnit)
                WEB3IfoFrontOrderService l_ifoFrontOrderService = 
                    (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);
                
                IfoOrderManagerImpl l_ifoOrderManagerImpl = 
                    (IfoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
                IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_ifoOrderManagerImpl.toOrderUnit(ifoOrderUnitRow);
                l_ifoFrontOrderService.lockHostFotypeOrderAll(l_ifoOrderUnit);
                
                // get先物OP注文取引キュー(IfoOrderUnit)
                HostFotypeOrderAllParams l_hostFotypeOrderAllParams = 
                    l_ifoFrontOrderService.getHostFotypeOrderAll(l_ifoOrderUnit);
                
                // (*)キューレコードが取得できなかった場合
                if (l_hostFotypeOrderAllParams == null )
                {
                    // (*)"該当キューレコードなし"の文言と注文IDをデバッグログに出力し、処理を終了する。
                    log.debug("該当キューレコードなし");
                    log.debug("注文ID = " + this.ifoOrderUnitRow.getOrderId());
                    log.exiting(STR_METHOD_NAME);
                    return null;
                }
                
                //(*) notify失効(OrderUnit, double, String, String)
                WEB3IfoCloseNotifyUnitService l_ifoCloseNotifyUnitService = 
                    (WEB3IfoCloseNotifyUnitService)Services.getService(WEB3IfoCloseNotifyUnitService.class);
                l_ifoCloseNotifyUnitService.notifyClose(l_ifoOrderUnit,
                    l_ifoOrderUnit.getExecutedQuantity(),
                    WEB3ExpirationReasonCodeDef.ORDER_CANCEL,
                    WEB3CloseNotifyTypeDef.CLOSE);
                
                // reset取引カレンダコンテキスト(String, Long, Long)
                WEB3AdminIfoDataManager.resetTradingCalContext(l_mainAccount.getInstitution().getInstitutionCode(),
                    new Long(ifoOrderUnitRow.getBranchId()),
                    new Long(ifoOrderUnitRow.getProductId()));

                // update注文データ(IfoOrderUnit, boolean)
                l_ifoOrderUnit = (IfoOrderUnit)l_ifoOrderManagerImpl.getOrderUnit(ifoOrderUnitRow.getOrderUnitId());

                IfoOrderUnitRow l_ifoOrderUnitRowCopy = (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
                IfoOrderUnitParams l_ifoOrderUnitParamsCopy = new IfoOrderUnitParams(l_ifoOrderUnitRowCopy);
                l_ifoOrderUnitParamsCopy.setErrorReasonCode(WEB3ErrorReasonCodeDef.FUTURE_OP_ADMIN_MANUAL_EXPIRED);

                IfoOrderUnit l_ifoOrderUnitCopy = (IfoOrderUnit)l_ifoOrderManagerImpl.toOrderUnit(l_ifoOrderUnitParamsCopy);

                WEB3OptionOrderManagerImpl l_orderManager = 
                    (WEB3OptionOrderManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager(); 
                l_orderManager.updateOrderData(l_ifoOrderUnitCopy, false);
                
                // getSubAccount(arg0 : long, arg1 : long)
                //補助口座を取得する
                WEB3GentradeSubAccount l_subAccount = 
                    (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                        ifoOrderUnitRow.getAccountId(), 
                        ifoOrderUnitRow.getSubAccountId());
                
                // (*)証拠金口座以外（getSubAccount()の戻り値.補助口座タイプ != "証拠金口座"）の場合
                SubAccountTypeEnum l_subAccountTypeEnum = l_subAccount.getSubAccountType();
                if (!SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccountTypeEnum)) 
                {
                    //余力再計算(補助口座 : 補助口座)
                    WEB3TPTradingPowerService l_tpTradingPowerService =
                        (WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);
                    l_tpTradingPowerService.reCalcTradingPower(l_subAccount);
                }
                
                //(*) 取得したOP注文取引キューをupdateする。
                //更新内容については、DB更新仕様
                //「先物OP手動失効_先物OP注文取引キューテーブル.xls」
                //参照。
                QueryProcessor l_queryProcessor = null;
                l_queryProcessor = Processors.getDefaultProcessor();
                l_hostFotypeOrderAllParams.setOrderActionSerialNo(l_hostFotypeOrderAllParams.getOrderActionSerialNo() + 1);
                l_hostFotypeOrderAllParams.setStatus(WEB3StatusDef.ADMIN_MANUAL_EXPIRED);
                l_hostFotypeOrderAllParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                l_queryProcessor.doUpdateQuery(l_hostFotypeOrderAllParams);
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
     * (（非同期）管理者・先物OP手動失効オンライン実行結果TransactionCallback)<BR>
     * （非同期）管理者・先物OP手動失効オンライン実行結果TransactionCallback<BR>
     * <BR>
     * 手動失効の処理開始／終了を<BR>
     * オンライン実行結果テーブルに記録する。<BR>
     */
    public class WEB3AdminIfoManualExpireOnlineRunStatusTransactionCallback implements TransactionCallback 
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
        public WEB3AdminIfoManualExpireOnlineRunStatusTransactionCallback() 
        {
         
        }
        
        /**
         * (（非同期）管理者・先物OP手動失効オンライン実行結果TransactionCallback)<BR>
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
         * WEB3AsynAdminIfoManualExpireServiceImpl.WEB3AdminIfoManualExpireOnlineRunStatusTransactionCallback
         * @@roseuid 44693F0300BA
         */
        public WEB3AdminIfoManualExpireOnlineRunStatusTransactionCallback(
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
         * 　@　@銘柄タイプ：　@"先物OP"<BR>
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
                    ProductTypeEnum.IFO, 
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
     * (（非同期）管理者・先物OP手動失効デーモントリガーTransactionCallback)<BR>
     * （非同期）管理者・先物OP手動失効デーモントリガーTransactionCallback<BR>
     * （トランザクション属性：TX_JOIN_EXISTING）<BR>
     */
    public class WEB3AdminIfoManualExpireDaemonTriggerTransactionCallback implements TransactionCallback 
    {
        
        /**
         * (スレッドNo)<BR>
         * スレッドNo
         */
        public long threadNo;
        
        /**
         * @@roseuid 447ACD830222
         */
        public WEB3AdminIfoManualExpireDaemonTriggerTransactionCallback() 
        {
         
        }
        
        /**
         * (（非同期）管理者・先物OP手動失効デーモントリガーTransactionCallback)<BR>
         * コンストラクタ。<BR>
         * <BR>
         * 引数を自身の同名項目にセットする。<BR>
         * @@param l_lngThreadNo - (スレッドNo)<BR>
         * スレッドNo
         * @@return 
         * WEB3AsynAdminIfoManualExpireServiceImpl.WEB3AdminIfoManualExpireD
         * aemonTriggerTransactionCallback
         * @@roseuid 44744A1901B1
         */
        public WEB3AdminIfoManualExpireDaemonTriggerTransactionCallback(long l_lngThreadNo) 
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
