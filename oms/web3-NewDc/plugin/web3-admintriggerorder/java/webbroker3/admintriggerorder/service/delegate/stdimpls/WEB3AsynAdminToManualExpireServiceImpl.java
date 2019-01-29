head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AsynAdminToManualExpireServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （非同期）トリガー注文管理者・手動失効サービスImpl(WEB3AsynAdminToManualExpireServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/17　@余新敏(中訊) 新規作成
Revesion History : 2006/7/5 徐宏偉 (中訊) 仕様変更モデル072
Revesion History : 2006/08/24 肖志偉 (中訊) 仕様変更モデル068,086,087,090,092,DB更新仕様010,013,014,016,017
Revesion History : 2006/10/19 唐性峰 (中訊) モデル095
Revesion History : 2006/11/28　@周捷(中訊) 仕様変更モデルNo.116
Revesion History : 2006/11/20 黄建(中訊) モデル 098、099、108（更新仕様）No.018、019、020
Revesion History : 2006/11/30 齊珂(中訊) モデル 113、114（更新仕様）No.024
Revesion History : 2006/12/19　@徐大方(中訊) 仕様変更モデルNo.124               
Revesion History : 2006/01/30　@関博(中訊) 仕様変更モデルNo.126
*/

package webbroker3.admintriggerorder.service.delegate.stdimpls;

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
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderAction;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderManagerImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.admintriggerorder.WEB3AdminToDataManager;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseMainRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CloseNotifyTypeDef;
import webbroker3.common.define.WEB3DaemonTriggerStatusDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ExpirationReasonCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3OnlineServiceDiv;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.common.define.WEB3RunStatusDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.equity.WEB3EquityEstimatedPrice;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.data.HostEqtypeCloseOrderNotifyParams;
import webbroker3.equity.data.HostEqtypeOrderAllParams;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.equity.service.delegate.WEB3EquityReceiveCloseOrderUnitService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeOnlineRunStatus;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.DaemonTriggerParams;
import webbroker3.gentrade.data.DaemonTriggerRow;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.data.HostFotypeOrderAllParams;
import webbroker3.ifo.service.delegate.WEB3IfoCloseNotifyUnitService;
import webbroker3.ifo.service.delegate.WEB3IfoFrontOrderService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (（非同期）トリガー注文管理者・手動失効サービスImpl)<BR>
 * （WEB3AsynAdminToManualExpireServiceImpl）<BR>
 * （非同期）トリガー注文管理者・手動失効サービス実装クラス<BR>
 * <BR>
 * 失効処理を非同期で行う。<BR>
 * 
 * @@author 余新敏
 * @@version 1.0
 */
public class WEB3AsynAdminToManualExpireServiceImpl implements Runnable
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AsynAdminToManualExpireServiceImpl.class);
    
    /**
     * (リクエストデータ)<BR>
     * トリガー注文管理者・手動失効メインリクエストオブジェクト<BR>
     */
    public WEB3AdminToManualLapseMainRequest request;
    
    /**
     * @@roseuid 441932780138
     */
    public WEB3AsynAdminToManualExpireServiceImpl() 
    {
     
    }
    
    /**
     * (（非同期）トリガー注文管理者・手動失効サービスImpl)<BR>
     * デフォルトコンストラクタ。<BR>
     * <BR>
     * 引数をthis.リクエストデータにセットする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * トリガー注文管理者・手動失効メインリクエストオブジェクト<BR>
     * @@roseuid 440BCD74017F
     */
    public WEB3AsynAdminToManualExpireServiceImpl(WEB3AdminToManualLapseMainRequest l_request) 
    {
        this.request = l_request;
    }
    
    /**
     * （非同期）手動失効処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（（非同期）トリガー注文管理者・手動失効サービス）run」参照。<BR>
     * @@roseuid 440BC62A020C
     */
    public void run()
    {
        final String STR_METHOD_NAME = " run()";
        log.entering(STR_METHOD_NAME);
        
        // トリガー注文管理者・手動失効デーモントリガーTransactionCallback(long)
        WEB3AdminToManualExpireDaemonTriggerTransactionCallback l_daemonTriggerTransactionCallback =
            new WEB3AdminToManualExpireDaemonTriggerTransactionCallback(
                this.request.threadNo.longValue());
                    
        QueryProcessor l_queryProcessor = null;
        List l_lisDaemonTrigger = null;
        try
        {
            l_queryProcessor = Processors.getDefaultProcessor();
            l_lisDaemonTrigger = (List)l_queryProcessor.doTransaction(
                QueryProcessor.TX_JOIN_EXISTING,
                l_daemonTriggerTransactionCallback);
                
            //nullが返却された場合は、処理を終了する。
            if (l_lisDaemonTrigger == null)
            {
                log.debug("トリガー注文管理者・手動失効デーモントリガーの戻り値がnull。");
                log.exiting(STR_METHOD_NAME);
                return;
            }
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
        
        //1.1 トリガー注文管理者・手動失効オンライン実行結果TransactionCallback(
        //String, long, long)
        WEB3AdminToManualExpireOnlineRunStatusTransactionCallback l_runStatusTransactionCallback = 
            new WEB3AdminToManualExpireOnlineRunStatusTransactionCallback(
                this.request.institutionCode, 
                this.request.rangeFrom.longValue(), 
                this.request.rangeTo.longValue());
        WEB3GentradeOnlineRunStatus l_onlineRunStatus = null;
        
        try
        {
            //1.2 doTransaction(arg0 : int, arg1 : TransactionCallback)
            //トランザクション属性は、TX_CREATE_NEW。
            //※更新内容を他のプロセスから参照可能とする為。
            //　@正常終了時は、オンライン実行結果オブジェクトが
            //　@返却される。
            l_onlineRunStatus = (WEB3GentradeOnlineRunStatus) l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW, 
                l_runStatusTransactionCallback);
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
        
        //実行ステータス区分：　@"処理済"
        String l_strRunStatusDiv = WEB3RunStatusDivDef.DEALED;
        OrderUnit[] l_orderUnits = null;
        
        try
        {
            //1.3 get手動失効対象注文単位一覧(Long, 証券会社, String[], String[],  
            //String[],String[], String, String, Long, Long)
            //(*)引数設定仕様
            //注文ID：　@this.リクエストデータ.失効対象注文条件の同名項目
            //証券会社：　@this.リクエストデータ.証券会社コードに該当する証券会社
            //部店コード：　@this.リクエストデータ.失効対象注文条件の同名項目
            //条件注文種別一覧：　@this.リクエストデータ.失効対象注文条件の同名項目
            //商品区分一覧：　@this.リクエストデータ.失効対象注文条件の同名項目
            //市場コード一覧：　@this.リクエストデータ.失効対象注文条件の同名項目
            //銘柄コード：　@this.リクエストデータ.失効対象注文条件の同名項目
            //顧客コード：　@リクエストデータ.失効対象注文条件の同名項目
            //口座IDFrom：　@this.リクエストデータ.From口座ID
            //口座IDTo：　@this.リクエストデータ.To口座ID
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            Institution l_institution = l_accountManager.getInstitution(this.request.institutionCode);

            Long l_orderId = null;
            if (WEB3StringTypeUtility.isNotEmpty(this.request.lapseTargetOrderCondition.id))
            {
                l_orderId = Long.valueOf(this.request.lapseTargetOrderCondition.id);
            }
            
            l_orderUnits = WEB3AdminToDataManager.getManualExpireOrderUnits(
                l_orderId,
                l_institution,
                this.request.lapseTargetOrderCondition.branchCode,
                this.request.lapseTargetOrderCondition.triggerOrderTypeList,
                this.request.lapseTargetOrderCondition.productDivList,
                this.request.lapseTargetOrderCondition.marketList,
                this.request.lapseTargetOrderCondition.productCode,
                this.request.lapseTargetOrderCondition.accountCode,
                this.request.rangeFrom,
                this.request.rangeTo);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            l_strRunStatusDiv = WEB3RunStatusDivDef.ERROR;
        }
        
        //1.4 (*)get手動失効対象注文単位一覧()の戻り値の要素（=OrderUnit）数分Loop処理。
        //戻り値がnullの場合はLoop処理しない。
        if (l_orderUnits != null)
        {
            log.debug("口座IDレンジ（From～To） = [" + this.request.rangeFrom + "～" + this.request.rangeTo + "]");
            log.debug("***** 手動失効処理対象総件数:[" + l_orderUnits.length + "] *****");
            for (int i = 0; i < l_orderUnits.length; i++)
            {
                String l_strtradingTimeType = null;
                //Product l_product = l_orderUnits[i].getProduct();
                ProductTypeEnum l_productType = null;
                try
                {
                    //1.4.1 reset取引カレンダコンテキスト(String, 
                    //ProductTypeEnum, Long, Long, Long, String)
                    if (l_orderUnits[i] instanceof EqTypeOrderUnit)
                    {
                        l_strtradingTimeType = WEB3TradingTimeTypeDef.EQUITY;
                        EqtypeOrderUnitRow l_eqTypeOrderUnitRow = 
                            (EqtypeOrderUnitRow) l_orderUnits[i].getDataSourceObject();
                        l_productType = l_eqTypeOrderUnitRow.getProductType();
                        WEB3AdminToDataManager.resetTradingCalContext(
                            this.request.institutionCode,
                            l_productType,
                            new Long(l_eqTypeOrderUnitRow.getBranchId()),
                            new Long(l_eqTypeOrderUnitRow.getMarketId()),
                            null,
                            l_strtradingTimeType);
                    }
                    else if (l_orderUnits[i] instanceof IfoOrderUnit)
                    {
                        l_strtradingTimeType = WEB3TradingTimeTypeDef.INDEX_FUTURE_OP;
                        IfoOrderUnitRow l_ifoOrderUnitRow = 
                            (IfoOrderUnitRow) l_orderUnits[i].getDataSourceObject();
                        l_productType = ((IfoOrderUnit) l_orderUnits[i]).getProductType();
                        WEB3AdminToDataManager.resetTradingCalContext(
                            this.request.institutionCode,
                            l_productType,
                            new Long(l_ifoOrderUnitRow.getBranchId()),
                            new Long(l_ifoOrderUnitRow.getMarketId()),
                            new Long(l_ifoOrderUnitRow.getProductId()),
                            l_strtradingTimeType);
                    }
                
                    log.debug("***** 手動失効処理：" + (i + 1) + "件目 *****");
                    log.debug("処理時刻：[" + GtlUtils.getSystemTimestamp() + "]");
                    log.debug("注文ID：[" + l_orderUnits[i].getOrderId() + "]");
                    log.debug("口座ID：[" + l_orderUnits[i].getAccountId() + "]");
                    log.debug("注文種別：[" + l_orderUnits[i].getOrderType() + "]");
                    log.debug("銘柄タイプ：[" + l_productType + "]");
                    
                    //1.4.2 トリガー注文管理者・手動失効TransactionCallback(
                    //ProductTypeEnum, OrderUnit)
                    WEB3AdminToManualExpireTransactionCallback l_transactionCallback = 
                        new WEB3AdminToManualExpireTransactionCallback(
                            l_productType, 
                            l_orderUnits[i]);

                    //1.4.3 doTransaction(arg0 : int, arg1 : 
                    //TransactionCallback)例外がスローされた場合は、
                    //注文IDと銘柄タイプをERRORでログ出力し、
                    //次の要素へ処理を移行する。
                    l_queryProcessor.doTransaction(QueryProcessor.TX_CREATE_NEW, l_transactionCallback);
                }
                catch (Exception l_ex)
                {
                    log.error("注文ID:" + l_orderUnits[i].getOrderId());
                    log.error("銘柄タイプ:" + l_productType);
                    l_strRunStatusDiv = WEB3RunStatusDivDef.ERROR;
                    continue;
                }
            }
        }
        
        //1.5 (*)ThreadLocalのTIMESTAMP_TAGをnullでリセットする。
        //各テーブルの更新日付に現在時刻をセットする為。
        //※リセットしなければ、最後に失効した注文と同じ時刻が使用される。
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        
        try
        {
            //1.6 update実行ステータス区分(実行ステータス区分 : String)
            //(*)引数設定仕様（取得済みのオブジェクトに対してコール。）
            //実行ステータス区分：　@"処理済"
            //　@※失効処理で例外がスローされた場合は、"エラー"。
            l_onlineRunStatus.updateRunStatusDiv(l_strRunStatusDiv);
        
            //排他ロックしたデーモントリガーテーブルのレコードを"未稼動"でupdateする。
            DaemonTriggerParams l_daemonTriggerParams =
                new DaemonTriggerParams((DaemonTriggerRow) l_lisDaemonTrigger.get(0));
            
            //処理状態に"未稼動"をsetする。
            l_daemonTriggerParams.setTriggerStatus(WEB3DaemonTriggerStatusDef.THREAD_IDLE);
            //現在時刻をsetする。
            l_daemonTriggerParams.setTriggerDate(GtlUtils.getSystemTimestamp());
            
            WEB3DataAccessUtility.updateRow((DaemonTriggerRow)l_daemonTriggerParams);
        
        }
        catch (WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    new ErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }
        catch (DataException l_ex)
        {

            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                new ErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (トリガー注文管理者・手動失効TransactionCallback)<BR>
     * トリガー注文管理者・手動失効TransactionCallback<BR>
     * （トランザクション属性：TX_CREATE_NEW）<BR>
     * <BR>
     */
    public class WEB3AdminToManualExpireTransactionCallback implements TransactionCallback 
    {
        
        /**
         * (銘柄タイプ)<BR>
         * 銘柄タイプ<BR>
         */
        public ProductTypeEnum productType;
        
        /**
         * (注文単位)<BR>
         * 注文単位オブジェクト<BR>
         */
        public OrderUnit orderUnit;
        
        /**
         * @@roseuid 4419334C0128
         */
        public WEB3AdminToManualExpireTransactionCallback() 
        {
         
        }
        
        /**
         * (トリガー注文管理者・手動失効TransactionCallback)<BR>
         * コンストラクタ。<BR>
         * <BR>
         * 引数を自身の同名項目にセットする。<BR>
         * @@param l_productType - (銘柄タイプ)<BR>
         * 銘柄タイプ<BR>
         * @@param l_orderUnit - (注文単位)<BR>
         * 注文単位オブジェクト<BR>
         * @@roseuid 440C0FB90373
         */
        public WEB3AdminToManualExpireTransactionCallback(ProductTypeEnum l_productType, OrderUnit l_orderUnit) 
        {
            this.productType = l_productType;
            this.orderUnit = l_orderUnit;
        }
        
        /**
         * 手動失効処理を行う。<BR>
         * <BR>
         * シーケンス図<BR>
         * 「（トリガー注文管理者・手動失効TransactionCallback）process」参照。<BR>
         * @@return Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 440B949A0393
         */
        public Object process() throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = " process()";
            log.entering(STR_METHOD_NAME);

            MainAccount l_mainAccount = null;
            try
            {
                //1.1 getMainAccount(arg0 : long)
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                WEB3GentradeAccountManager l_accountManager =
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                l_mainAccount = l_accountManager.getMainAccount(this.orderUnit.getAccountId());

                //1.2 lock口座(証券会社コード : String, 部店コード : String,
                //口座コード : String)
                l_accountManager.lockAccount(
                    l_mainAccount.getInstitution().getInstitutionCode(),
                    l_mainAccount.getBranch().getBranchCode(),
                    l_mainAccount.getAccountCode());
            }
            catch (NotFoundException l_ex)
            {
                log.error("error in l_accountManager.getMainAccount", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
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

            //1.3 (*)(*)市場送信済のW指値注文かつ、市場開局中のW指値注文の場合
            //（this.注文単位.発注条件 == "W指値"&& 市場から確認済みの数量 != null
            //　@&& 取引時間管理.is市場開局時間帯() == true）
            String l_strOrderConditionType = null;
            boolean l_dblConfirmedQuantityIsNull = false;
            if (ProductTypeEnum.EQUITY.equals(this.productType))
            {
                EqtypeOrderUnitRow l_orderUnitRow =
                    (EqtypeOrderUnitRow)this.orderUnit.getDataSourceObject();
                l_strOrderConditionType = l_orderUnitRow.getOrderConditionType();
                l_dblConfirmedQuantityIsNull = l_orderUnitRow.getConfirmedQuantityIsNull();
            }
            else if (ProductTypeEnum.IFO.equals(this.productType))
            {
                IfoOrderUnitRow l_orderUnitRow =
                    (IfoOrderUnitRow)this.orderUnit.getDataSourceObject();
                l_strOrderConditionType = l_orderUnitRow.getOrderConditionType();
                l_dblConfirmedQuantityIsNull = l_orderUnitRow.getConfirmedQuantityIsNull();
            }
            boolean l_blnIsTradeOpenTimeZone = false;
            try
            {
                l_blnIsTradeOpenTimeZone = WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone();
            }
            catch (WEB3SystemLayerException l_ex)
            {
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType)
                && !l_dblConfirmedQuantityIsNull && l_blnIsTradeOpenTimeZone)
            {
                //1.3.1 invalidateストップ注文( )
                this.invalidateStopOrder();
            }
            else
            {
                //1.4 (*)上記以外の場合
                //1.4.1 invalidate注文( )
                this.invalidateOrder();
            }

            try
            {
                //1.8 getSubAccount(arg0 : long, arg1 : long)
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                WEB3GentradeAccountManager l_accountManager =
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                SubAccount l_subAccount = l_accountManager.getSubAccount(
                    this.orderUnit.getAccountId(),
                    this.orderUnit.getSubAccountId());

                //1.9 (*)証拠金口座以外（getSubAccount()の戻り値.補助口座タイプ !=
                //"証拠金口座"）の場合
                if (!SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType()))
                {
                    //1.9.1 余力再計算(補助口座 : 補助口座)
                    WEB3TPTradingPowerService l_tpTradingPowerService =
                        (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
                    l_tpTradingPowerService.reCalcTradingPower((WEB3GentradeSubAccount)l_subAccount);
                }
            }
            catch (NotFoundException l_ex)
            {
                log.error("error in l_accountManager.getSubAccount", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
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

            log.exiting(STR_METHOD_NAME);
            return null;
        }

        /**
         * (invalidate注文)<BR>
         * 注文失効処理（注文クローズ）を行う。<BR>
         * <BR>
         * シーケンス図 <BR>
         * 「（トリガー注文管理者・手動失効TransactionCallback）invalidate注文」参照。<BR>
         * @@throws DataNetworkException
         * @@throws DataQueryException
         */
        public void invalidateOrder() throws DataNetworkException, DataQueryException
        {
            final String STR_METHOD_NAME = " invalidateOrder()";
            log.entering(STR_METHOD_NAME);

            //1.1) getMainAccount
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();

            MainAccount l_mainAccount = null;
            try
            {
                l_mainAccount = l_accountManager.getMainAccount(this.orderUnit.getAccountId());
            }
            catch (NotFoundException l_ex)
            {
                log.error("error in l_accountManager.getMainAccount", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();

            //1.2)(*)Eqtype（this.銘柄タイプ == "株式"）の場合
            if (ProductTypeEnum.EQUITY.equals(this.productType))
            {
                EqtypeOrderUnitRow l_orderUnitRow =
                    (EqtypeOrderUnitRow)this.orderUnit.getDataSourceObject();

                //1.2.1)isキューレコード確認要()
                //1.2.2)(*)isキューレコード確認要()の戻り値 == trueの場合
                HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams = null;
                if (isHostRecordConfirmRequire())
                {
                    WEB3EquityFrontOrderService l_frontOrderService =
                        (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
                    try
                    {
                        //1.2.2.1 lock株式注文取引キュー
                        l_frontOrderService.lockHostEqtypeOrderAll((EqTypeOrderUnit)this.orderUnit);

                        //1.2.2.2 get株式注文取引キュー
                        l_hostEqtypeOrderAllParams =
                            l_frontOrderService.getHostEqtypeOrderAll((EqTypeOrderUnit)this.orderUnit);
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

                    //1.2.2.3 (*)未処理のキューレコードが取得できなかった場合
                    if (l_hostEqtypeOrderAllParams == null)
                    {
                        log.info("発注遅延注文に該当する未処理の注文キューが取得できませんでしたので、失効されません。"
                            + "銘柄タイプ:[" + this.productType + "] 注文ID：["
                            + this.orderUnit.getOrderId() + "] 識別コード:[" + l_orderUnitRow.getOrderRequestNumber() + "]");
                        return;
                    }
                }

                //1.2.3)(*)株式失効通知キューParamsのインスタンス生成、及びプロパティセット
                HostEqtypeCloseOrderNotifyParams l_hostEqtypeCloseOrderNotifyParams =
                    new HostEqtypeCloseOrderNotifyParams();

                //データコード
                l_hostEqtypeCloseOrderNotifyParams.setRequestCode(
                    WEB3HostRequestCodeDef.EQUITY_ORDER_CLOSE_NOTICE);

                //証券会社コード
                l_hostEqtypeCloseOrderNotifyParams.setInstitutionCode(l_mainAccountRow.getInstitutionCode());

                //部店コード
                l_hostEqtypeCloseOrderNotifyParams.setBranchCode(l_mainAccountRow.getBranchCode());

                //口座コード
                l_hostEqtypeCloseOrderNotifyParams.setAccountCode(l_mainAccountRow.getAccountCode());

                //扱者コード
                l_hostEqtypeCloseOrderNotifyParams.setTraderCode(null);

                //識別コード
                l_hostEqtypeCloseOrderNotifyParams.setOrderRequestNumber(
                    l_orderUnitRow.getOrderRequestNumber());

                //約定数量
                if (!l_orderUnitRow.getExecutedQuantityIsNull())
                {
                    l_hostEqtypeCloseOrderNotifyParams.setExecutedQuantity(
                        l_orderUnitRow.getExecutedQuantity());
                }

                //失効理由コード
                l_hostEqtypeCloseOrderNotifyParams.setReasonCode(
                    WEB3ExpirationReasonCodeDef.ORDER_CANCEL);

                //失効通知区分
                l_hostEqtypeCloseOrderNotifyParams.setCloseNotifyType(
                    WEB3CloseNotifyTypeDef.CLOSE);

                //エラーメッセージ
                l_hostEqtypeCloseOrderNotifyParams.setErrorMessage(
                    WEB3ErrorReasonCodeDef.TRIGGER_ADMIN_MANUAL_EXPIRED);

                //処理区分
                l_hostEqtypeCloseOrderNotifyParams.setStatus(WEB3StatusDef.NOT_DEAL);

                WEB3EquityReceiveCloseOrderUnitService l_unitService =
                    (WEB3EquityReceiveCloseOrderUnitService)Services.getService(
                        WEB3EquityReceiveCloseOrderUnitService.class);

                try
                {
                    //1.2.4) exec失効()
                    l_unitService.execCloseOrder(
                        l_hostEqtypeCloseOrderNotifyParams,
                        (EqTypeOrderUnit)this.orderUnit);
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

                //1.2.5 (*)株式注文取引キューテーブルのレコードを取得している場合は、updateを行う。
                //手動失効_株式注文取引キューテーブル.xls
                if (l_hostEqtypeOrderAllParams != null)
                {
                    //注文履歴番号
                    l_hostEqtypeOrderAllParams.setOrderActionSerialNo(
                        l_hostEqtypeOrderAllParams.getOrderActionSerialNo() + 1);

                    //処理区分（ステータス）
                    l_hostEqtypeOrderAllParams.setStatus(WEB3StatusDef.ADMIN_MANUAL_EXPIRED);

                    //更新日付
                    l_hostEqtypeOrderAllParams.setLastUpdatedTimestamp(
                        GtlUtils.getSystemTimestamp());

                    QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                    l_queryProcessor.doUpdateQuery(l_hostEqtypeOrderAllParams);
                }
            }

            //1.3) (*)Ifo（this.銘柄タイプ == "先物オプション"）の場合
            else if (ProductTypeEnum.IFO.equals(this.productType))
            {
                IfoOrderUnitRow l_orderUnitRow =
                    (IfoOrderUnitRow)this.orderUnit.getDataSourceObject();

                //1.3.1 isキューレコード確認要()
                //1.3.2 (*)isキューレコード確認要()の戻り値 == trueの場合
                HostFotypeOrderAllParams l_hostFotypeOrderAllParams = null;
                if (isHostRecordConfirmRequire())
                {
                    WEB3IfoFrontOrderService l_ifoFrontOrderService =
                        (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);
                    try
                    {
                        //1.3.2.1 lock先物OP注文取引キュー(注文単位 : IfoOrderUnit)
                        l_ifoFrontOrderService.lockHostFotypeOrderAll((IfoOrderUnit)this.orderUnit);

                        //1.3.2.2 get先物OP注文取引キュー(注文単位 : IfoOrderUnit)
                        l_hostFotypeOrderAllParams =
                            l_ifoFrontOrderService.getHostFotypeOrderAll((IfoOrderUnit)this.orderUnit);
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


                    if (l_hostFotypeOrderAllParams == null)
                    {
                        log.info("発注遅延注文に該当する未処理の注文取引キューが取得できませんでしたので、失効されません。"
                            + "銘柄タイプ:[" + this.productType + "] 注文ID：["
                            + this.orderUnit.getOrderId() + "] 識別コード:[" + l_orderUnitRow.getOrderRequestNumber() + "]");
                        return;
                    }

                }

                WEB3IfoCloseNotifyUnitService l_ifoCloseNotifyUnitService =
                    (WEB3IfoCloseNotifyUnitService)Services.getService(
                        WEB3IfoCloseNotifyUnitService.class);

                try
                {
                    //1.3.3)notify失効()
                    l_ifoCloseNotifyUnitService.notifyClose(
                        this.orderUnit,
                        l_orderUnitRow.getExecutedAmount(),
                        WEB3ExpirationReasonCodeDef.ORDER_CANCEL,
                        WEB3CloseNotifyTypeDef.CLOSE);
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

                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
                WEB3OptionOrderManagerImpl l_optionOrderMgr =
                    (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

                try
                {
                    IfoOrderUnit l_ifoOrderUnit =
                        (IfoOrderUnit)l_optionOrderMgr.getOrderUnit(
                             this.orderUnit.getOrderUnitId());

                    IfoOrderUnitParams l_ifoOrderUnitParams = new IfoOrderUnitParams(
                        (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject());
                    l_ifoOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.TRIGGER_ADMIN_MANUAL_EXPIRED);
                    l_ifoOrderUnit = (IfoOrderUnit)l_optionOrderMgr.toOrderUnit(l_ifoOrderUnitParams);

                    //1.3.4) update注文データ()
                    l_optionOrderMgr.updateOrderData(l_ifoOrderUnit, false);
                }
                catch (NotFoundException l_ex)
                {
                    log.error("error in l_ifoOrderUnit.getOrderUnit", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
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

                //1.3.5) (*)先物OP注文取引キューテーブルのレコードを取得している場合は、updateを行う。
                if (l_hostFotypeOrderAllParams != null)
                {
                    // 注文取引キューレコードのupdateを行う。
                    // 注文履歴番号
                    l_hostFotypeOrderAllParams.setOrderActionSerialNo(
                            l_hostFotypeOrderAllParams.getOrderActionSerialNo() + 1);
                    // 処理区分
                    l_hostFotypeOrderAllParams.setStatus(WEB3StatusDef.ADMIN_MANUAL_EXPIRED);
                    
                    //更新日付
                    l_hostFotypeOrderAllParams.setLastUpdatedTimestamp(
                        GtlUtils.getSystemTimestamp());

                    QueryProcessor l_processor = Processors.getDefaultProcessor();
                    l_processor.doUpdateQuery(l_hostFotypeOrderAllParams);
                }

                //1.3.6) reset取引カレンダコンテキスト
                try
                {
                    WEB3AdminToDataManager.resetTradingCalContext(
                        l_mainAccountRow.getInstitutionCode(),
                        this.productType,
                        new Long(l_orderUnitRow.getBranchId()),
                        new Long(l_orderUnitRow.getMarketId()),
                        new Long(l_orderUnitRow.getProductId()),
                        WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);
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
            }

            log.exiting(STR_METHOD_NAME);
        }

        /**
         * (invalidateストップ注文)<BR>
         * ストップ注文失効処理（発注条件クリア）を行う。<BR>
         * <BR>
         * シーケンス図 <BR>
         * 「（トリガー注文管理者・手動失効TransactionCallback）invalidateストップ注文」参照。<BR>
         */
        public void invalidateStopOrder()
        {
            final String STR_METHOD_NAME = " invalidateStopOrder()";
            log.entering(STR_METHOD_NAME);

            //1.1)getSubAccount()
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            SubAccount l_subAccount = null;
            try
            {
                l_subAccount = l_accountManager.getSubAccount(
                    this.orderUnit.getAccountId(),
                    this.orderUnit.getSubAccountId());
            }
            catch (NotFoundException l_ex)
            {
                log.error("error in l_accountManager.getSubAccount", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //1.2)(*)Eqtype（this.銘柄タイプ == "株式"）の場合
            if (ProductTypeEnum.EQUITY.equals(this.productType))
            {
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                WEB3EquityOrderManager l_equityOrderMgr = (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

                // notifyルールエンジンサーバ
                try
                {
                    EqTypeOrderUnit l_eqtypeOrderUnit =
                        (EqTypeOrderUnit)l_equityOrderMgr.getOrderUnit(
                             this.orderUnit.getOrderUnitId());
                    l_equityOrderMgr.notifyRLS(l_eqtypeOrderUnit, OrderManagerPersistenceContext.ORDER_EXPIRED);

                }
                catch (WEB3BusinessLayerException l_ex)
                {
                    log.debug("notifyルールエンジンサーバ()にて業務エラーがスロー", l_ex);
                }
                catch (NotFoundException l_ex)
                {
                    log.error("error in l_eqtypeOrderUnit.getOrderUnit", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
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

                try
                {
                    //getストップ注文失効時概算代金計算結果
                    //[引数] 
                    //注文単位：　@this.注文単位 
                    //補助口座：　@this.注文単位.getSubAccount
                    TradingModule l_tradingMod =
                        l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                    WEB3EquityOrderManager l_orderManager =
                        (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
                    
                    WEB3EquityEstimatedPrice l_estimatedPrice = 
                        l_orderManager.getStopOrderExpireEstimatedPrice(
                            (EqTypeOrderUnit)this.orderUnit, 
                            l_subAccount);
                    
                    EqTypeOrderUnit l_eqTypeOrderUnit = this.updateEqTypeOrderUnitRow(
                        (EqTypeOrderUnit)this.orderUnit,
                        l_estimatedPrice
                        );

                    //create注文履歴(注文単位 : EqTypeOrderUnit, イベントタイプ : OrderEventTypeEnum)
                    //[引数] 
                    //注文単位：　@クローンとして設定された注文単位更新値 
                    //イベントタイプ：　@"92：失効"
                    EqTypeOrderAction l_eqTypeOrderAction =
                        l_orderManager.createOrderAction(
                            l_eqTypeOrderUnit, OrderEventTypeEnum.EXPIRE);
                    
                    //update注文データ(注文単位 : EqTypeOrderUnit, 注文履歴 : EqTypeOrderAction)
                    //[引数] 
                    //注文単位：　@クローンとして設定された注文単位更新値 
                    //注文履歴：　@create注文履歴()の戻り値
                    l_orderManager.updateOrderData(l_eqTypeOrderUnit, l_eqTypeOrderAction);
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
            }

            //1.3) (*)Ifo（this.銘柄タイプ == "先物オプション"）の場合
            else if (ProductTypeEnum.IFO.equals(this.productType))
            {
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
                WEB3OptionOrderManagerImpl l_optionOrderMgr =
                    (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

                try
                {
                    // notifyルールエンジンサーバ
                    l_optionOrderMgr.notifyRLS(
                        (IfoOrderUnit)this.orderUnit, OrderManagerPersistenceContext.ORDER_EXPIRED);
                }
                catch (WEB3BusinessLayerException l_ex)
                {
                    log.debug("notifyルールエンジンサーバ()にて業務エラーがスロー", l_ex);
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

                IfoOrderUnitRow l_ifoOrderUnitRow =
                    (IfoOrderUnitRow)this.orderUnit.getDataSourceObject();

                //先物OP概算受渡代金計算結果
                WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult = null;

                TradingModule l_tradingMod =
                    l_finApp.getTradingModule(ProductTypeEnum.IFO);
                WEB3OptionOrderManagerImpl l_optionOrderManagerImpl =
                    (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

                try
                {
                    //1.3.2)(*)this.注文単位.注文種別 == "OP新規買建注文"の場合
                    if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_ifoOrderUnitRow.getOrderType()))
                    {
                        //getストップ注文失効時概算代金計算結果(注文単位 : IfoOrderUnit, 補助口座 : 補助口座)
                        //[引数]
                        //注文単位：　@this.注文単位
                        //補助口座：　@getSubAccount()の戻り値
                        l_ifoEstimateDeliveryAmountCalcResult =
                            l_optionOrderManagerImpl.getStopOrderExpireEstimatedPrice(
                                (IfoOrderUnit)this.orderUnit,
                                (WEB3GentradeSubAccount)l_subAccount);
                    }

                    IfoOrderUnit l_ifoOrderUnit = updateIfoOrderUnitRow(
                        (IfoOrderUnit)this.orderUnit,
                        l_ifoEstimateDeliveryAmountCalcResult);

                    //1.3.3) update注文データ()
                    l_optionOrderManagerImpl.updateOrderData(l_ifoOrderUnit, true);
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
            }
            log.exiting(STR_METHOD_NAME);
        }

        /**
         * (isキューレコード確認要)<BR>
         * キューレコードの確認（検索）が必要な注文かどうか <BR>
         * 判別する。<BR>
         * <BR>
         * １）　@this.注文単位の型をinstanceofにて判別し、<BR>
         * 　@以下のいずれかにキャストする。<BR>
         * 　@・株式注文単位<BR>
         * 　@・先物OP注文単位<BR>
         * <BR>
         *　@キャスト後、注文単位より以下の項目を取得する。<BR>
         * 　@・発注条件 <BR>
         * 　@・リクエストタイプ<BR>
         * 　@・市場から確認済の数量<BR>
         * <BR>
         * ２）　@キューレコードを確認する必要があるかどうか判別する。<BR>
         * <BR>
         * 　@[発注条件 == "逆指値"の場合]<BR>
         * 　@[this.銘柄タイプ == "株式"の場合]<BR>
         * 　@　@リクエストタイプ == "時価サーバ"であれば、<BR>
         * 　@　@trueを返却する。以外、falseを返却する。<BR>
         * 　@　@※場間に手動発注された場合の考慮。<BR>
         * <BR>
         * 　@[発注条件 == "W指値"の場合]<BR>
         * 　@　@市場から確認済みの数量 == nullであれば、<BR>
         * 　@　@trueを返却する。以外、falseを返却する。<BR>
         * 　@　@※市場未送信のW指値注文。<BR>
         * <BR>
         * 　@[上記以外]<BR>
         * 　@　@falseを返却する。<BR>
         * <BR>
         * @@return boolean
         */
        private boolean isHostRecordConfirmRequire()
        {
            final String STR_METHOD_NAME = " isHostRecordConfirmRequire()";
            log.entering(STR_METHOD_NAME);

            //１）　@this.注文単位の型をinstanceofにて判別し、
            //以下のいずれかにキャストする。
            //　@　@・株式注文単位
            //　@　@・先物OP注文単位
            String l_strOrderConditionType = null;
            String l_strRequestType = null;         
            boolean l_blnConfirmedQuantityIsNull = false;
            if (this.orderUnit instanceof EqTypeOrderUnit)
            {
                EqTypeOrderUnit l_eqtypeOrderUnit = (EqTypeOrderUnit)this.orderUnit;
                EqtypeOrderUnitRow l_eqtypeOrderUnitRow =
                    (EqtypeOrderUnitRow)l_eqtypeOrderUnit.getDataSourceObject();

                //　@キャスト後、注文単位より以下の項目を取得する。
                //　@　@・発注条件
                //　@　@・リクエストタイプ
                //　@　@・市場から確認済の数量
                l_strOrderConditionType = l_eqtypeOrderUnitRow.getOrderConditionType();
                l_strRequestType = l_eqtypeOrderUnitRow.getRequestType();            
                l_blnConfirmedQuantityIsNull =
                    l_eqtypeOrderUnitRow.getConfirmedQuantityIsNull();
            }
            else if (this.orderUnit instanceof IfoOrderUnit)
            {
                IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)this.orderUnit;
                IfoOrderUnitRow l_ifoOrderUnitRow =
                    (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();

                //　@キャスト後、注文単位より以下の項目を取得する。
                //　@　@・発注条件
                //　@　@・リクエストタイプ
                //　@　@・市場から確認済の数量
                l_strOrderConditionType = l_ifoOrderUnitRow.getOrderConditionType();
                l_strRequestType = l_ifoOrderUnitRow.getRequestType();              
                l_blnConfirmedQuantityIsNull =
                    l_ifoOrderUnitRow.getConfirmedQuantityIsNull();
            }

            //２）　@キューレコードを確認する必要があるかどうか判別する。
            //　@[発注条件 == "逆指値"の場合]
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderConditionType))
            {
                //リクエストタイプ == "時価サーバ"であれば
                //trueを返却する。以外、falseを返却する
                //※場間に手動発注された場合の考慮
                if (WEB3RequestTypeDef.QUOTE_SERVER.equals(l_strRequestType))
                {
                    log.exiting(STR_METHOD_NAME);
                    return true;
                }
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    return false;
                }
            }

            //　@[発注条件 == "W指値"の場合]
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
            {
                //　@　@市場から確認済みの数量 == nullであれば、
                //　@　@trueを返却する。以外、falseを返却する。
                //　@　@※市場未送信のW指値注文。
                if (l_blnConfirmedQuantityIsNull)
                {
                    log.exiting(STR_METHOD_NAME);
                    return true;
                }
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    return false;
                }
            }

            //　@[上記以外]
            //　@　@falseを返却する。
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        /**
         * DB更新<BR>
         * 「手動失効_株式注文単位テーブル.xls」<BR>
         * @@param l_orderUnit - (注文単位)<BR>
         * @@param l_estimatedDeliveryPrice - (概算代金計算結果)<BR>
         * @@return EqTypeOrderUnit
         */
        private EqTypeOrderUnit updateEqTypeOrderUnitRow(
            EqTypeOrderUnit l_orderUnit,
            WEB3EquityEstimatedPrice l_estimatedDeliveryPrice
            )
        {
            final String STR_METHOD_NAME =
                "updateOrderUnitRow(EqTypeOrderUnit, WEB3EquityEstimatedPrice)";
            log.entering(STR_METHOD_NAME);

            EqtypeOrderUnitRow l_eqTypeOrderUnitRow =
                (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            EqtypeOrderUnitParams l_eqTypeOrderUnitParams =
                new EqtypeOrderUnitParams(l_eqTypeOrderUnitRow);

            //注文履歴最終通番
            if (l_eqTypeOrderUnitParams.getLastOrderActionSerialNoIsSet())
            {
                int l_intLastOrderActionSerialNo = 
                    l_eqTypeOrderUnitParams.getLastOrderActionSerialNo();
                l_eqTypeOrderUnitParams.setLastOrderActionSerialNo(l_intLastOrderActionSerialNo + 1);
            }
            
            //発注条件
            String l_strOrderConditionType = l_eqTypeOrderUnitParams.getOrderConditionType();
            
            //元発注条件
            l_eqTypeOrderUnitParams.setOrgOrderConditionType(l_strOrderConditionType);
            
            l_eqTypeOrderUnitParams.setOrderConditionType(
                WEB3OrderingConditionDef.DEFAULT);

            //発注条件演算子
            String l_strOrderCondOperator = l_eqTypeOrderUnitParams.getOrderCondOperator();
            
            //元発注条件演算子
            l_eqTypeOrderUnitParams.setOrgOrderCondOperator(l_strOrderCondOperator);
            
            l_eqTypeOrderUnitParams.setOrderCondOperator(null);

            //元逆指値基準値
            if (l_eqTypeOrderUnitParams.getStopOrderPriceIsNull())
            {
                l_eqTypeOrderUnitParams.setOrgStopOrderPrice(null);
            }
            else
            {
                double l_dblStopOrderPrice = l_eqTypeOrderUnitParams.getStopOrderPrice();
                l_eqTypeOrderUnitParams.setOrgStopOrderPrice(l_dblStopOrderPrice);
            }

            //逆指値基準値
            l_eqTypeOrderUnitParams.setStopOrderPrice(null);

            //元（W指値）訂正指値
            if (l_eqTypeOrderUnitParams.getWLimitPriceIsNull())
            {
                l_eqTypeOrderUnitParams.setOrgWLimitPrice(null);
            }
            else
            {
                double l_dblWLimitPrice = l_eqTypeOrderUnitParams.getWLimitPrice();
                l_eqTypeOrderUnitParams.setOrgWLimitPrice(l_dblWLimitPrice);
            }
            
            //（W指値）訂正指値
            l_eqTypeOrderUnitParams.setWLimitPrice(null);
            
            //元（W指値）執行条件
            EqTypeExecutionConditionType l_exeType = l_eqTypeOrderUnitParams.getWLimitExecCondType(); 
            if (l_exeType == null)
            {
                l_eqTypeOrderUnitParams.setOrgWLimitExecCondType(null);
            }
            else
            {
                l_eqTypeOrderUnitParams.setOrgWLimitExecCondType(l_exeType);
            }
            
            //（W指値）執行条件
            l_eqTypeOrderUnitParams.setWLimitExecCondType(null);

            if (l_estimatedDeliveryPrice != null)
            {
                //注文単価
                double l_dblCalcUnitPrice =
                    l_estimatedDeliveryPrice.getCalcUnitPrice();
                l_eqTypeOrderUnitParams.setPrice(l_dblCalcUnitPrice); 
                
                //概算受渡代金
                double l_dblEstimateDeliveryAmount =
                    l_estimatedDeliveryPrice.getEstimateDeliveryAmount();
                l_eqTypeOrderUnitParams.setEstimatedPrice(l_dblEstimateDeliveryAmount);
                
                //市場から確認済みの注文単価
                l_eqTypeOrderUnitParams.setConfirmedOrderPrice(l_dblCalcUnitPrice);

                //市場から確認済みの概算受渡代金
                l_eqTypeOrderUnitParams.setConfirmedEstimatedPrice(l_dblEstimateDeliveryAmount);
            }

            //注文エラー理由コード
            l_eqTypeOrderUnitParams.setErrorReasonCode(
                WEB3ErrorReasonCodeDef.TRIGGER_ADMIN_MANUAL_EXPIRED);

            //リクエストタイプ
            l_eqTypeOrderUnitParams.setRequestType(WEB3RequestTypeDef.INVALIDATE);

            //更新日付
            l_eqTypeOrderUnitParams.setLastUpdatedTimestamp(
                GtlUtils.getSystemTimestamp());

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            EqTypeOrderManagerImpl l_orderManagerImpl =
                (EqTypeOrderManagerImpl)l_tradingModule.getOrderManager();

            EqTypeOrderUnit l_eqTypeOrderUnit =
                (EqTypeOrderUnit)l_orderManagerImpl.toOrderUnit(
                    l_eqTypeOrderUnitParams);

            log.exiting(STR_METHOD_NAME);
            return l_eqTypeOrderUnit;
        }

        /**
         * DB更新<BR>
         * 「手動失効_先物OP注文単位テーブル.xls」<BR>
         * @@param l_orderUnit - (注文単位)<BR>
         * @@param l_ifoEstimateDeliveryAmountCalcResult
         *  - (先物OP概算受渡代金計算結果)<BR>
         * @@return IfoOrderUnit
         */
        private IfoOrderUnit updateIfoOrderUnitRow(
            IfoOrderUnit l_orderUnit,
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult)
        {
            final String STR_METHOD_NAME =
                "updateOrderUnitRow(IfoOrderUnit, WEB3IfoEstimateDeliveryAmountCalcResult)";
            log.entering(STR_METHOD_NAME);

            IfoOrderUnitRow l_ifoOrderUnitRow =
                (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
            IfoOrderUnitParams l_ifoOrderUnitParams =
                new IfoOrderUnitParams(l_ifoOrderUnitRow);

            //注文履歴最終通番
            l_ifoOrderUnitParams.setLastOrderActionSerialNo(
                l_ifoOrderUnitParams.getLastOrderActionSerialNo() + 1);

            //発注条件
            String l_strOrderConditionType = l_ifoOrderUnitParams.getOrderConditionType();
            l_ifoOrderUnitParams.setOrderConditionType(
                WEB3OrderingConditionDef.DEFAULT);

            //発注条件演算子
            String l_strOrderCondOperator = l_ifoOrderUnitParams.getOrderCondOperator();
            l_ifoOrderUnitParams.setOrderCondOperator(null);

            //逆指値基準値タイプ
            String l_strStopPriceType = l_ifoOrderUnitParams.getStopPriceType();
            l_ifoOrderUnitParams.setStopPriceType(null);

            //元逆指値基準値
            if (!l_ifoOrderUnitParams.getStopOrderPriceIsNull())
            {
                l_ifoOrderUnitParams.setOrgStopOrderPrice(l_ifoOrderUnitParams.getStopOrderPrice());
            }
            else
            {
                l_ifoOrderUnitParams.setOrgStopOrderPrice(null);
            }

            //逆指値基準値
            l_ifoOrderUnitParams.setStopOrderPrice(null);

            //元（W指値）訂正指値
            if (!l_ifoOrderUnitParams.getWLimitPriceIsNull())
            {
                l_ifoOrderUnitParams.setOrgWLimitPrice(l_ifoOrderUnitParams.getWLimitPrice());
            }
            else
            {
                l_ifoOrderUnitParams.setOrgWLimitPrice(null);
            }

            //（W指値）訂正指値
            l_ifoOrderUnitParams.setWLimitPrice(null);

            //this.注文種別 == "OP新規買建注文"の場合
            if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_ifoOrderUnitParams.getOrderType()))
            {
                //注文単価
                double l_dblCalcUnitPrice =
                    l_ifoEstimateDeliveryAmountCalcResult.getCalcUnitPrice();
                l_ifoOrderUnitParams.setPrice(l_dblCalcUnitPrice);

                //概算受渡代金
                double l_dblEstimateDeliveryAmount =
                    l_ifoEstimateDeliveryAmountCalcResult.getEstimateDeliveryAmount();
                l_ifoOrderUnitParams.setEstimatedPrice(l_dblEstimateDeliveryAmount);

                //市場から確認済みの注文単価
                l_ifoOrderUnitParams.setConfirmedOrderPrice(l_dblCalcUnitPrice);

                //市場から確認済みの概算受渡代金
                l_ifoOrderUnitParams.setConfirmedEstimatedPrice(l_dblEstimateDeliveryAmount);
            }

            //注文エラー理由コード
            l_ifoOrderUnitParams.setErrorReasonCode(
                WEB3ErrorReasonCodeDef.TRIGGER_ADMIN_MANUAL_EXPIRED);

            //リクエストタイプ
            l_ifoOrderUnitParams.setRequestType(WEB3RequestTypeDef.INVALIDATE);

            //更新日付
            l_ifoOrderUnitParams.setLastUpdatedTimestamp(
                GtlUtils.getSystemTimestamp());

            //元発注条件
            l_ifoOrderUnitParams.setOrgOrderConditionType(l_strOrderConditionType);

            //元発注条件演算子
            l_ifoOrderUnitParams.setOrgOrderCondOperator(l_strOrderCondOperator);

            //元逆指値基準値タイプ
            l_ifoOrderUnitParams.setOrgStopPriceType(l_strStopPriceType);

            //元（W指値）執行条件
            l_ifoOrderUnitParams.setOrgWLimitExecCondType(
                l_ifoOrderUnitParams.getWLimitExecCondType());

            //（W指値）執行条件
            l_ifoOrderUnitParams.setWLimitExecCondType(null);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_optionOrderManagerImpl =
                (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

            IfoOrderUnit l_ifoOrderUnit =
                (IfoOrderUnit)l_optionOrderManagerImpl.toOrderUnit(
                    l_ifoOrderUnitParams);

            log.exiting(STR_METHOD_NAME);
            return l_ifoOrderUnit;

        }
    }

    /**
     * (トリガー注文管理者・手動失効オンライン実行結果TransactionCallback)<BR>
     * トリガー注文管理者・手動失効オンライン実行結果TransactionCallback<BR>
     * <BR>
     * 手動失効の処理開始／終了を<BR>
     * オンライン実行結果テーブルに記録する。<BR>
     */
    public class WEB3AdminToManualExpireOnlineRunStatusTransactionCallback implements TransactionCallback 
    {
        
        /**
         * (証券会社コード)<BR>
         * （institutionCode）<BR>
         * 証券会社コード<BR>
         */
        public String institutionCode;
        
        /**
         * (From口座ID)<BR>
         * （rangeFrom）<BR>
         * From口座ID<BR>
         */
        public long rangeFrom;
        
        /**
         * (To口座ID)<BR>
         * （rangeTo）<BR>
         * To口座ID<BR>
         */
        public long rangeTo;
        
        /**
         * @@roseuid 44193394005D
         */
        public WEB3AdminToManualExpireOnlineRunStatusTransactionCallback() 
        {
         
        }
        
        /**
         * (トリガー注文管理者・手動失効オンライン実行結果TransactionCallback)<BR>
         * コンストラクタ。<BR>
         * <BR>
         * 引数を自身の同名項目にセットする。<BR>
         * @@param l_strInstitutionCode - (証券会社コード)<BR>
         * 証券会社コード<BR>
         * @@param l_lngRangeFrom - (From口座ID)<BR>
         * From口座ID<BR>
         * @@param l_lngRangeTo - (To口座ID)<BR>
         * To口座ID<BR>
         * @@roseuid 440E825C00C3
         */
        public WEB3AdminToManualExpireOnlineRunStatusTransactionCallback(
            String l_strInstitutionCode, 
            long l_lngRangeFrom, 
            long l_lngRangeTo) 
        {
            this.institutionCode = l_strInstitutionCode;
            this.rangeFrom = l_lngRangeFrom;
            this.rangeTo = l_lngRangeTo;
        }
        
        /**
         * 手動失効処理開始をオンライン実行結果テーブルに記録する。<BR>
         * <BR>
         * １）　@オンライン実行結果.set処理中()メソッドをコールする。<BR>
         * 　@[set処理中()にセットするパラメータ]<BR>
         * 　@　@証券会社コード：　@this.証券会社コード<BR>
         * 　@　@銘柄タイプ：　@"その他"<BR>
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
         * @@roseuid 440E82F50111
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
                    ProductTypeEnum.OTHER, 
                    WEB3FuturesOptionDivDef.DEFAULT, 
                    WEB3OnlineServiceDiv.MANUAL_EXPIRE, 
                    this.rangeFrom, 
                    this.rangeTo);
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
            
            log.exiting(STR_METHOD_NAME);
            
            //２）　@set処理中()の戻り値を返却する。
            return l_onlineRunStatus;
        }
    }
    
    /**
     * (トリガー注文管理者・手動失効デーモントリガーTransactionCallback)<BR>
     * トリガー注文管理者・手動失効デーモントリガーTransactionCallback<BR>
     * （トランザクション属性：TX_JOIN_EXISTING）<BR>
     * <BR>
     */
    public class WEB3AdminToManualExpireDaemonTriggerTransactionCallback implements TransactionCallback 
    {
        
        /**
         * スレッドNo<BR>
         */
        public long threadNo;
        
        public WEB3AdminToManualExpireDaemonTriggerTransactionCallback() 
        {
         
        }
        
        /**
         * (トリガー注文管理者・手動失効デーモントリガーTransactionCallback)<BR>
         * コンストラクタ。<BR>
         * <BR>
         * 引数を自身の同名項目にセットする。<BR>
         * @@param l_lngThreadNo - (スレッドNo)<BR>
         * スレッドNo<BR>
         */
        public WEB3AdminToManualExpireDaemonTriggerTransactionCallback(
            long l_lngThreadNo) 
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
         * 　@　@スレッド番号 = パラメータ.スレッドNo<BR>
         * <BR>
         * ２）　@検索結果を返却する。<BR>
         * 　@※検索結果が取得できなかった場合、処理スレッドの<BR>
         * 　@占有ロックに失敗した旨をERRORでログに出力し、nullを返却する。<BR>
         * @@return java.lang.Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         */
        public Object process() throws DataNetworkException, DataQueryException, DataCallbackException 
        {
            final String STR_METHOD_NAME = " process()";
            log.entering(STR_METHOD_NAME);
            
            //１）　@DB検索
            //以下の条件に該当するデーモントリガーテーブルの
            //レコードを、"for update nowait"オプションで読み込む。
            List l_lisRows = null;

            try
            {
                String l_strWhere = " thread_no = ? ";
                String l_strCondition = " for update nowait ";
                Object l_bindVars[] = { new Long(this.threadNo) };
                QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
                l_lisRows = l_queryProcesser.doFindAllQuery(
                    DaemonTriggerRow.TYPE,
                    l_strWhere,
                    l_strCondition, 
                    l_bindVars);
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
        
            // 検索結果が取得できなかった場合、処理スレッドの
            // 占有ロックに失敗した旨をERRORでログに出力し、nullを返却する。
            if (l_lisRows.isEmpty())
            {
                log.error("処理スレッドの占有ロックに失敗しました。");
                log.exiting(STR_METHOD_NAME);
                return null;
            }

            log.exiting(STR_METHOD_NAME);
            return l_lisRows;
        }
    }
}
@
