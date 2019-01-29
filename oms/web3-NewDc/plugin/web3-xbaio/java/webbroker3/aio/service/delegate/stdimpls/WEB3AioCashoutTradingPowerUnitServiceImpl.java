head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.28.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutTradingPowerUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金余力チェックUnitServiceImpl(WEB3AioCashoutTradingPowerUnitServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/19 黄建 (中訊) 新規作成
                   2004/10/25 周勇(中訊) レビュー
                   2004/12/09 周勇 (中訊) 残対応
                   2006/02/21 韋念瓊 (中訊) 仕様変更・モデルNo.498
                   2006/08/28 車進 (中訊) 仕様変更・モデルNo.630、645
                   2006/11/15 徐宏偉 (中訊)仕様変更・モデルNo.684
                   2006/12/22 山田 (SCS)仕様変更・モデルNo.687
Revesion History : 2009/12/11 車進 (中訊) 仕様変更・モデルNo.1252、No.1253、No.1254 No.1257 No.1258 ＤＢ更新仕様No.232、No.233
Revesion History : 2010/01/15 武波 (中訊) 仕様変更・モデルNo.1259
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import webbroker3.aio.WEB3AioCashTransAcceptUpdateInterceptor;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.data.HostPaymentOrderDao;
import webbroker3.aio.data.HostPaymentOrderRow;
import webbroker3.aio.data.HostPaymentOrderPK;
import webbroker3.aio.data.HostPaymentOrderParams;
import webbroker3.aio.define.WEB3AioProcessFlagDef;
import webbroker3.aio.marketadaptor.WEB3AioMarketRequestSenderServiceImpl;
import webbroker3.aio.service.delegate.WEB3AioCashoutTradingPowerUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AioHostStatusDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3CashoutTodayDepositTransferDivDef;
import webbroker3.common.define.WEB3DbCurrentPriceCheckDivDef;
import webbroker3.common.define.WEB3DepositAutoTransferStopRegistDivDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.system.tune.affinity.WEB3DescendRacCtxService;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.tradingpoweradmin.data.DepositAutotransferStopRow;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (出金余力チェックUnitServiceImpl)<BR>
 * 出金余力チェックUnitService実装クラス<BR>
 * <BR>
 * Plugin時に自動トランザクション<BR>
 * TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW)<BR>
 * を指定する。<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3AioCashoutTradingPowerUnitServiceImpl 
    implements WEB3AioCashoutTradingPowerUnitService 
{
    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioCashoutTradingPowerUnitServiceImpl.class);  
    
    /**
     * 注文の余力チェック処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（出金余力チェック）余力チェック処理」 参照<BR>
     * <BR>
     * @@param l_hostPaymentOrderParams - (出金請求注文キューの行オブジェクト)
     * @@param l_strProcessFlag - (処理フラグ)
     * @@param l_blnTriggerIssueFlag - (トリガ発行フラグ)
     * @@param l_strDbCurrentPriceCheckDiv - (DB時価余力チェック区分)
     * @@throws WEB3BaseException
     * @@roseuid 412B0319039D
     */
    public void execute(HostPaymentOrderParams l_hostPaymentOrderParams,
        String l_strProcessFlag, boolean l_blnTriggerIssueFlag, String l_strDbCurrentPriceCheckDiv) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "execute(HostPaymentOrderParams, String, boolean, String)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 get注文単位(String, String, String, String, SubAccountTypeEnum)
        //注文単位オブジェクトを取得する。 
        //[引数] 
        //証券会社コード： 引数.出金請求注文キューParams.証券会社コード 
        //部店コード： 引数.出金請求注文キューParams.部店コード 
        //顧客コード： 引数.出金請求注文キューParams.顧客コード 
        //識別コード： 引数.出金請求注文キューParams.識別コード 
        //補助口座タイプ： 1（預り金口座） 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3AioOrderManager l_aioOrderManager =
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();

        AccountManager l_accMgr = l_finApp.getAccountManager();

        //証券会社コード： 引数.出金請求注文キューParams.証券会社コード
        String l_strInstitutionCode = l_hostPaymentOrderParams.getInstitutionCode();

        //部店コード： 引数.出金請求注文キューParams.部店コード 
        String l_strBranchCode = l_hostPaymentOrderParams.getBranchCode();
 
        //顧客コード： 引数.出金請求注文キューParams.顧客コード 
        String l_strAccountCode = l_hostPaymentOrderParams.getAccountCode();
 
        //識別コード： 引数.出金請求注文キューParams.識別コード 
        String l_strOrderRequestNumber = l_hostPaymentOrderParams.getOrderRequestNumber();
        
        //注文単位オブジェクトを取得する
        AioOrderUnit l_aioOrderUnit = null;
        
        try
        {
          l_aioOrderUnit = 
                 l_aioOrderManager.getOrderUnit(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode, 
                    l_strOrderRequestNumber,
                    SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        }
        catch (NotFoundException l_ex)
        {
            log.error("注文単位を取得できません。識別コード  = " + l_strOrderRequestNumber);         
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        long l_accountId = l_aioOrderUnit.getAccountId();

        WEB3DescendRacCtxService l_ctx_serv = (WEB3DescendRacCtxService) Services.
            getService(WEB3DescendRacCtxService.class);
        if (l_ctx_serv != null)
        {
            l_ctx_serv.setAccountIdCtx(l_accountId);
        }

        //口座
        WEB3GentradeMainAccount l_mainAccount = null;

        //補助口座
        try
        {
            l_mainAccount = (WEB3GentradeMainAccount)l_accMgr.getMainAccount(l_accountId);
        }
        catch (NotFoundException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //is信用口座開設         引数  弁済区分：　@0(指定なし)
        boolean l_blnIsMarginAccountEstablished =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //is出金に伴う当日保証金振替実施   注文単位： 取得した注文単位.部店ID
        boolean l_blnIsPaymentTodayDepositTransferEnforce =
            this.isPaymentTodayDepositTransferEnforce(l_aioOrderUnit.getBranchId());

        //1.2 引数.処理フラグ = '0'（全件データ処理） の場合
        if (WEB3AioProcessFlagDef.ALL_DATE_PROCESS.equals(l_strProcessFlag))
        {
            //1.2.1 DB時価余力チェック区分 = 0：未実施 とする。
            l_strDbCurrentPriceCheckDiv = WEB3DbCurrentPriceCheckDivDef.NOT_ENFORCEMENT;

            //1.2.2 validate出金余力(AioOrderUnit, String)

            //出金余力のチェックを行う
            //[引数]
            //注文単位： 取得した注文単位
            //DB時価余力チェック区分： パラメータ.DB時価余力チェック区分
            boolean l_blnIsCashoutTp =
                this.validateCashoutTradingPower(l_aioOrderUnit, l_strDbCurrentPriceCheckDiv);
            
            //validate出金余力()の戻り値 = false の場合
            if (!l_blnIsCashoutTp)
            {
                //1.2.3 注文取消処理(AioOrderUnit)

                //注文の取消処理を行う。
                //[引数] 
                //注文単位： 取得した注文単位 
                this.orderCancelProcess(l_aioOrderUnit);
            }
        }
        //引数.処理フラグ != '0'（全件データ処理） の場合
        else
        {
            //処理判定フラグ = true
            boolean l_blnCheckFlag = true;
            
            //トリガ発行フラグ = true の場合
            if (l_blnTriggerIssueFlag)
            {
                //is出金に伴う当日保証金振替実施()の戻る値 = true 且つ is信用口座開設()の戻る値 = true
                //且つ 処理フラグ = '2'（翌日振込分データ処理）の場合
                if (WEB3AioProcessFlagDef.NEXT_DATE_TRANSFER_PROCESS.equals(l_strProcessFlag)
                    && l_blnIsMarginAccountEstablished && l_blnIsPaymentTodayDepositTransferEnforce)
                {
                    //get翌日不足金(補助口座  : 補助口座)
                    //[引数] 
                    //補助口座：補助口座<株式取引口座(保証金)>オブジェクトを取得する。
                    WEB3TPTradingPowerService l_web3TPTradingPowerService =
                        (WEB3TPTradingPowerService) Services.getService(
                            WEB3TPTradingPowerService.class);

                    //補助口座
                    WEB3GentradeSubAccount l_subAccount = null;
                    try
                    {
                        l_subAccount = 
                            (WEB3GentradeSubAccount)l_mainAccount.getSubAccount(
                                SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
                    }
                    catch (NotFoundException l_ex)
                    {
                        log.error(STR_METHOD_NAME,l_ex);
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                            getClass().getName() + "." + STR_METHOD_NAME,
                            l_ex.getMessage(),
                            l_ex);
                    }

                    //保証金自動振替停止チェックを行う
                    //保証金自動振替停止テーブルを以下の条件で検索する。
                    //[検索条件]
                    // 保証金自動振替停止テーブル.口座ID  =　@顧客マスタ.口座ID
                    //保証金自動振替停止テーブル.登録区分  = 1「登録」
                    // システム日付（当日）  between　@保証金自動振替停止テーブル.停止期間（自）
                    //and保証金自動振替停止テーブル.停止期間（至）
                    final String l_strWhere =
                        " account_id = ?"
                        + " and regist_div = ?"
                        + " and  ? between autotransfer_stop_start and autotransfer_stop_end ";

                    final Object[] l_bindVars =
                    {
                        new Long(l_mainAccount.getAccountId()),
                        WEB3DepositAutoTransferStopRegistDivDef.DEFAULT,
                        WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp())
                    };
                    double l_dblNextBizDateShortfall = 0D;
                    try
                    {
                        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                        List l_depositAutotransferStopRows = l_queryProcessor.doFindAllQuery(
                            DepositAutotransferStopRow.TYPE,
                            l_strWhere,
                            l_bindVars);
                        //レコード件数 > 0の場合
                        if(l_depositAutotransferStopRows.size() > 0)
                        {
                            l_dblNextBizDateShortfall = 0.0D;
                        }
                        else
                        {
                            l_dblNextBizDateShortfall =
                                l_web3TPTradingPowerService.getNextBizDateShortfall(l_subAccount);
                        }
                    }
                    catch (DataQueryException l_ex)
                    {
                        log.error(STR_METHOD_NAME,l_ex);
                        log.exiting(STR_METHOD_NAME);

                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_ex.getMessage(),
                            l_ex);
                    }
                    catch (DataNetworkException l_ex)
                    {
                        log.error(STR_METHOD_NAME,l_ex);
                        log.exiting(STR_METHOD_NAME);

                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_ex.getMessage(),
                            l_ex);
                    }
                    
                    //出金額 = 取得した注文単位.注文数量 - 翌日不足金
                    double l_dblPaymentAmount = l_aioOrderUnit.getQuantity() - l_dblNextBizDateShortfall;

                    //出金額 <= 0 の場合
                    if (l_dblPaymentAmount <= 0)
                    {
                        //注文の取消処理を行う。
                        //[引数] 
                        //注文単位： 取得した注文単位 
                        this.orderCancelProcess(l_aioOrderUnit);

                        if (l_ctx_serv != null)
                        {
                            l_ctx_serv.clearAccountIdCtx();
                        }
                        log.exiting(STR_METHOD_NAME);
                        return;
                    }

                    //0 < 出金額 < 注文単位.注文数量 の場合
                    else if (l_dblPaymentAmount < l_aioOrderUnit.getQuantity())
                    {
                        //注文単位テーブル.注文数量、出金請求注文キューテーブル.出金額に引数.出金額をセットする。
                        this.updatePayMentData(
                            l_strInstitutionCode,
                            l_strBranchCode,
                            l_strAccountCode, 
                            l_strOrderRequestNumber,
                            l_aioOrderUnit.getOrderUnitId(),
                            l_dblPaymentAmount);
                    }
                }
                //上記以外の場合
                else
                {
                    //戻り値 = false の場合、処理判定フラグ = false とする。
                    //validate出金余力(AioOrderUnit, String)
                    //出金余力のチェックを行う。
                    //[引数] 
                    //注文単位： 取得した注文単位
                    //DB時価余力チェック区分： パラメータ.DB時価余力チェック区分
                    l_blnCheckFlag =
                        this.validateCashoutTradingPower(l_aioOrderUnit, l_strDbCurrentPriceCheckDiv);

                    //処理判定フラグ = false の場合
                    if (!l_blnCheckFlag)
                    {
                        //注文取消処理(AioOrderUnit)(出金余力チェックUnitServiceImpl::注文取消処理)
                        //注文の取消処理を行う。
                        //[引数] 
                        //注文単位： 取得した注文単位 
                        this.orderCancelProcess(l_aioOrderUnit);
                    }
                }
            }
            //処理判定フラグ = true の場合、実施
            if (l_blnCheckFlag)
            {
                //update処理区分(String, String, String, String, long)
                //出金請求注文キューテーブルを更新する。 

                //[引数] 
                //証券会社コード： 引数.出金請求注文キューParams.証券会社コード 
                //部店コード： 引数.出金請求注文キューParams.部店コード 
                //顧客コード： 引数.出金請求注文キューParams.顧客コード 
                //識別コード： 引数.出金請求注文キューParams.識別コード 
                //注文単位ID： 取得した注文単位.注文単位ID 
                WEB3AioMarketRequestSenderServiceImpl l_aioMarketSenderService = 
                    (WEB3AioMarketRequestSenderServiceImpl) GtlUtils.getTradingModule(
                        ProductTypeEnum.AIO).getMarketAdapter().getMarketRequestSenderServce();

                l_aioMarketSenderService.updateStatus(
                    l_hostPaymentOrderParams.getInstitutionCode(),
                    l_hostPaymentOrderParams.getBranchCode(), 
                    l_hostPaymentOrderParams.getAccountCode(), 
                    l_hostPaymentOrderParams.getOrderRequestNumber(), 
                    l_aioOrderUnit.getOrderUnitId()
                    );
            }
        }
        if (l_ctx_serv != null)
        {
            l_ctx_serv.clearAccountIdCtx();
        }        
        log.exiting(STR_METHOD_NAME);        
    }

    /**
     * (注文取消処理)<BR>
     * 注文の取消処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（出金余力チェック）注文取消処理」 参照<BR>
     * 
     * @@param l_hostPaymentOrderParams - (出金請求注文キューの行オブジェクト)
     * @@param l_strFlag - (処理フラグ)
     * @@roseuid 412B031300CE
     */
    protected void orderCancelProcess(AioOrderUnit l_orderUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "orderCancelProcess(AioOrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);        
        }
        
        //１.１）入出金受付更新インタセプタを生成する。 
        //[引数] 
        //エラーコード： "0001" 
        WEB3AioCashTransAcceptUpdateInterceptor l_aioCashAcceptUpdInterceptor =
            new WEB3AioCashTransAcceptUpdateInterceptor(WEB3ErrorReasonCodeDef.PRICE_RANGE_ERROR);
        
        //1.2) setThreadLocalPersistenceEventInterceptor(入出金受付更新インタセプタ)
        //[引数] 
        //入出金受付更新インタセプタ：　@生成した入出金受付更新インタセプタ 
        
        //AIO注文マネージャを取得する 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3AioOrderManager l_aioOrderManager =
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();     
        l_aioOrderManager.setThreadLocalPersistenceEventInterceptor(
                l_aioCashAcceptUpdInterceptor);
        
        //1.3) DefaultNewOrderRejectedMarketResponseMessage(注文ID : long)
        //受付結果（受付エラー）オブジェクトを生成する。 
        //[引数] 
        //注文ID： 引数.注文単位.注文ID 
        DefaultNewOrderRejectedMarketResponseMessage l_defaultResponseMessage = 
            new DefaultNewOrderRejectedMarketResponseMessage(
                l_orderUnit.getOrderId());
        
        //1.4) process(受付結果 : NewOrderRejectedMarketResponseMessage)
        //受付エラーを注文に更新する。
        //[引数] 
        //受付結果： （生成した受付結果（エラー）オブジェクト） 
        MarketAdapter l_marketAdapter =
            GtlUtils.getTradingModule(ProductTypeEnum.AIO).getMarketAdapter();
        AioMarketResponseReceiverCallbackService l_marketCallbackService = 
            (AioMarketResponseReceiverCallbackService)
                l_marketAdapter.getMarketResponseReceiverCallbackService();
        
        ProcessingResult l_processResult = 
            l_marketCallbackService.process(l_defaultResponseMessage);
        
        if (l_processResult.isFailedResult())
        {
            log.debug("受付エラー状態に注文を更新失敗である");
            throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00395,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "受付エラー状態に注文を更新失敗");   
        }

        //1.5) get補助口座()
        //補助口座オブジェクトを取得する。 
        //[引数] 
        //口座ID： 引数.注文単位.口座ID 
        //補助口座ID： 引数.注文単位.補助口座ID 
        
        //拡張アカウントマネージャ取得する
        WEB3GentradeAccountManager l_accMgr = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
        //補助口座
        SubAccount l_subAccount = null;
        try
        {
            //補助口座オブジェクトを取得する。 
            l_subAccount = 
                l_accMgr.getSubAccount(
                    l_orderUnit.getAccountId(),  
                    l_orderUnit.getSubAccountId());
            
        }
        catch (NotFoundException l_ex)
        {
            log.error("補助口座オブジェクトを取得, 顧客オブジェクトを取得", l_ex);
            log.exiting(STR_METHOD_NAME);  
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }         

        //1.6) 余力再計算(補助口座 : 補助口座)
        //余力の更新をする。 
        //[引数] 
        //補助口座：　@補助口座オブジェクト
        WEB3TPTradingPowerReCalcService l_service =
            (WEB3TPTradingPowerReCalcService) Services.getService(
                WEB3TPTradingPowerReCalcService.class);
        WEB3GentradeSubAccount l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;
        
        l_service.reCalcTradingPower(l_gentradeSubAccount);
        
        //1.7)             
        //１）出金請求注文キューParamsインスタンスを生成する。 
        HostPaymentOrderParams l_params = new HostPaymentOrderParams();
        //２）出金請求注文キューParamsにプロパティをセットする。 
        //データコード request_code        出金注文：GI801
        l_params.setRequestCode(WEB3HostRequestCodeDef.AIO_CASH_OUT_REQUEST_ORDER);
        l_params.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());
        l_params.setBranchCode(l_subAccount.getMainAccount().getBranch().getBranchCode());
        l_params.setAccountCode(l_subAccount.getMainAccount().getAccountCode());
        AioOrderUnitRow l_orderUnitRow = 
            (AioOrderUnitRow)l_orderUnit.getDataSourceObject();
        l_params.setOrderRequestNumber(l_orderUnitRow.getOrderRequestNumber());

        try
        {
            log.debug("出金請求注文キューParams = " + 
                    HostPaymentOrderDao.findRowByPk(
                            (HostPaymentOrderPK)l_params.getPrimaryKey()));

            WEB3DataAccessUtility.deleteRow(l_params);
        }
        catch (DataQueryException l_ex)
        {
            log.error("Error In 該当する出金請求注文キューテーブルのレコードを削除する。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex
            );
        }
        catch (DataNetworkException l_ex)
        {
            log.error("Error In 該当する出金請求注文キューテーブルのレコードを削除する。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex
            );
        }
    }
    
    /**
     * (validate出金余力)<BR>
     * 出金余力のチェックを行う。<BR>
     * <BR>
     * １）引数.DB時価余力チェック区分 = 0：未実施 の場合<BR>
     * <BR>
     * 　@１-１）出金可能額を取得する。<BR>
     * 　@  　@ 取引余力サービス.get出金可能額～0補正無し～<BR>
     * <BR>
     * 　@　@   [引数]<BR>
     *   　@　@ 補助口座： 注文単位.補助口座IDから取得される補助口座オブジェクト<BR>
     *    　@　@受渡日： 注文単位.受渡日<BR>
     * <BR>
     * ２）引数.DB時価余力チェック区分 = 1：実施 の場合<BR>
     * <BR>
     * 　@２-１）出金可能額を取得する。<BR>
     * 　@  　@ 取引余力サービス.get出金可能額<DB時価>～0補正無し～<BR>
     * <BR>
     * 　@　@   [引数]<BR>
     *   　@　@ 補助口座： 注文単位.補助口座IDから取得される補助口座オブジェクト<BR>
     *    　@　@受渡日： 注文単位.受渡日<BR>
     * <BR>
     * ３）get出金可能額()の戻り値 < 0 の場合、false を返す。<BR>
     * <BR>
     * ４）注文単位.発注日 = 当日営業日 かつ<BR>
     * 　@　@注文単位.受渡日 = 翌日営業日 かつ<BR>
     * 　@　@is出金に伴う当日保証金振替実施　@=　@true の場合<BR>
     * <BR>
     * 　@４-１）当日保証金引出余力を取得する。<BR>
     * 　@　@　@取引余力サービス.get当日保証金引出余力<BR>
     * <BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@　@補助口座： 注文単位.補助口座IDから取得される補助口座オブジェクト<BR>
     * 　@　@　@　@DB時価余力チェック区分： 引数.DB時価余力チェック区分<BR>
     * <BR>
     * 　@４-２）get当日保証金引出余力()の戻り値 < 0 の場合、false を返す。<BR>
     * <BR>
     * ５）上記以外の場合、true を返す。<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト
     * @@param l_strDbCurrentPriceCheckDiv - (DB時価余力チェック区分)<BR>
     * DB時価余力チェック区分
     * @@return boolean
     * @@throws WEB3BaseException
     */
    protected boolean validateCashoutTradingPower(
        AioOrderUnit l_orderUnit,
        String l_strDbCurrentPriceCheckDiv) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "validateCashoutTradingPower(AioOrderUnit, String)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        //拡張アカウントマネージャ取得する
        WEB3GentradeAccountManager l_accMgr = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
        //補助口座
        SubAccount l_subAccount = null;
        try
        {
            //補助口座オブジェクトを取得する。 
            l_subAccount = 
                l_accMgr.getSubAccount(
                    l_orderUnit.getAccountId(),  
                    l_orderUnit.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("補助口座オブジェクトを取得", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //取引余力サービスを取得する
        WEB3TPTradingPowerService l_tPTradingPowerService =
            (WEB3TPTradingPowerService)Services.getService(
                WEB3TPTradingPowerService.class);
        double l_dblCashoutPossiblePrice = 0;
        // １）引数.DB時価余力チェック区分 = 0：未実施 の場合    
        if (WEB3DbCurrentPriceCheckDivDef.NOT_ENFORCEMENT.equals(l_strDbCurrentPriceCheckDiv))
        {
            // 　@１-１）出金可能額を取得する
            // 　@  　@ 取引余力サービス.get出金可能額～0補正無し～                          
            // 　@　@   [引数]
            //   　@　@ 補助口座： 注文単位.補助口座IDから取得される補助口座オブジェクト
            //    　@　@受渡日： 注文単位.受渡日
            l_dblCashoutPossiblePrice =
                l_tPTradingPowerService.getPaymentTradingPowerForCheck(
                    (WEB3GentradeSubAccount)l_subAccount,
                    l_orderUnit.getDeliveryDate());
        }
        else if (WEB3DbCurrentPriceCheckDivDef.ENFORCEMENT.equals(l_strDbCurrentPriceCheckDiv))
        {
            // ２）引数.DB時価余力チェック区分 = 1：実施 の場合                                            
            // 　@２-１）出金可能額を取得する
            // 　@  　@ 取引余力サービス.get出金可能額<DB時価>～0補正無し～                                      
            // 　@　@   [引数]
            //   　@　@ 補助口座： 注文単位.補助口座IDから取得される補助口座オブジェクト
            //    　@　@受渡日： 注文単位.受渡日
            l_dblCashoutPossiblePrice =
                l_tPTradingPowerService.getPaymentTradingPowerDBQuote(
                    (WEB3GentradeSubAccount)l_subAccount,
                    l_orderUnit.getDeliveryDate());
        }
        else
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //３）get出金可能額()の戻り値 < 0 の場合、false を返す。
        if(l_dblCashoutPossiblePrice < 0)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //４）注文単位.発注日 = 当日営業日 かつ
        //注文単位.受渡日 = 翌日営業日 かつ
        //is出金に伴う当日保証金振替実施　@=　@true の場合
        //is出金に伴う当日保証金振替実施   注文単位： 取得した注文単位.部店ID
        Timestamp l_tsBaseDate = GtlUtils.getSystemTimestamp();
        WEB3GentradeBizDate l_genBizDate = new WEB3GentradeBizDate(l_tsBaseDate);
        Timestamp l_tsDate = l_genBizDate.roll(0);
        Timestamp l_tsDateNext = l_genBizDate.roll(1);
        AioOrderUnitRow l_aioOrderUnitRow = (AioOrderUnitRow)l_orderUnit.getDataSourceObject();
        boolean l_blnIsPaymentTodayDepositTransferEnforce =
            this.isPaymentTodayDepositTransferEnforce(l_orderUnit.getBranchId());
        if (WEB3DateUtility.compareToDay(WEB3DateUtility.getDate(
                l_aioOrderUnitRow.getBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD),
                l_tsDate) == 0
            && WEB3DateUtility.compare(l_aioOrderUnitRow.getDeliveryDate(), l_tsDateNext) == 0
            && l_blnIsPaymentTodayDepositTransferEnforce)
        {
            //４-１）当日保証金引出余力を取得する。
            //取引余力サービス.get当日保証金引出余力
            //補助口座： 注文単位.補助口座IDから取得される補助口座オブジェクト
            //DB時価余力チェック区分： 引数.DB時価余力チェック区分
            double l_dblTodayDepositWithdrawTradingPower =
                l_tPTradingPowerService.getTodayDepositWithdrawTradingPower(
                    (WEB3GentradeSubAccount)l_subAccount, l_strDbCurrentPriceCheckDiv);

            //４-２）get当日保証金引出余力()の戻り値 < 0 の場合、false を返す。
            if (l_dblTodayDepositWithdrawTradingPower < 0)
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }
        //５）上記以外の場合、true を返す。
        log.exiting(STR_METHOD_NAME);
        return true;
    } 

    /**
     * (update出金データ)<BR>
     * 注文単位テーブル.注文数量、<BR>
     * 出金請求注文キューテーブル.出金額に引数.出金額をセットする。<BR>
     * 更新内容は「update出金データ_DB更新仕様」を参照。<BR>
     * <BR>
     * １）注文単位テーブルの更新<BR>
     * <BR>
     * 　@１－１）以下の条件で、注文単位テーブルからレコードを取得する。<BR>
     * 　@　@[検索条件]<BR>
     * 　@　@注文単位ID： 引数.注文単位ID<BR>
     * <BR>
     * 　@１－２）注文数量に引数.出金額を、更新日付にシステムタイムスタンプをセットする。<BR>
     * <BR>
     * 　@１－３）該当レコードを更新する。<BR>
     * <BR>
     * ２）出金請求注文キューテーブルの更新<BR>
     * <BR>
     * 　@２－１）以下の条件で、出金請求注文キューテーブルからレコードを取得する。<BR>
     * <BR>
     * 　@　@[検索条件]<BR>
     * 　@　@データコード： "GI801"<BR>
     * 　@　@証券会社コード： 引数.証券会社コード<BR>
     * 　@　@部店コード： 引数.部店コード<BR>
     * 　@　@顧客コード： 引数.顧客コード<BR>
     * 　@　@識別コード： 引数.識別コード<BR>
     * 　@　@処理区分： '3'（未処理）<BR>
     * <BR>
     * 　@２－２）出金額に引数.出金額をセットする。<BR>
     * <BR>
     * 　@２－３）該当レコードを更新する。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード
     * @@param l_strBranceCode - (部店コード)<BR>
     * 部店コード
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード
     * @@param l_strOrderRequestNumber - (識別コード)<BR>
     * 識別コード
     * @@param l_lngOrderUnitId - (注文単位ID)<BR>
     * 注文単位ID
     * @@param l_dblCashOutAmt - (出金額)<BR>
     * 出金額
     * @@throws WEB3BaseException
     */
    protected void updatePayMentData(String l_strInstitutionCode, String l_strBranceCode,
        String l_strAccountCode, String l_strOrderRequestNumber, long l_lngOrderUnitId,
        double l_dblCashOutAmt) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "updatePayMentData(String, String, String, String, long, double)";
        log.entering(STR_METHOD_NAME);

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //注文単位テーブルの更新
            //以下の条件で、注文単位テーブルからレコードを取得する。 
            //[検索条件] 
            //注文単位ID： 引数.注文単位ID 
            AioOrderUnitRow l_orderUnitRow = 
                AioOrderUnitDao.findRowByPk(l_lngOrderUnitId);
            log.debug("注文単位テーブルRow := " + l_orderUnitRow);

            AioOrderUnitParams l_orderUnitParams = new AioOrderUnitParams(l_orderUnitRow);
            //注文数量に引数.出金額を、更新日付にシステムタイムスタンプをセットする。 
            l_orderUnitParams.setQuantity(l_dblCashOutAmt);
            l_orderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            //該当レコードを更新する。
            l_queryProcessor.doUpdateQuery(l_orderUnitParams);
            log.debug("updated 注文単位テーブルRow := " + l_orderUnitParams);

            //以下の条件で、出金請求注文キューテーブルからレコードを取得する。
            //[検索条件]
            //データコード： "GI801"
            //証券会社コード： 引数.証券会社コード
            //部店コード： 引数.部店コード
            //顧客コード： 引数.顧客コード
            //識別コード： 引数.識別コード
            //処理区分： '3'（未処理）
            String l_strQueryString =
                " request_code = ? and institution_code = ? and branch_code = ? " +
                " and account_code = ? and order_request_number = ? and status = ? ";
            Object[] l_objQueryDataContainer =  new Object[] { 
                WEB3HostRequestCodeDef.AIO_CASH_OUT_REQUEST_ORDER,
                l_strInstitutionCode,
                l_strBranceCode, 
                l_strAccountCode,
                l_strOrderRequestNumber, 
                WEB3AioHostStatusDef.NOT_DEAL};

            List l_lisHostPaymentOrderRows = l_queryProcessor.doFindAllQuery(
                HostPaymentOrderRow.TYPE,
                l_strQueryString,
                l_objQueryDataContainer);

            if (l_lisHostPaymentOrderRows.size() == 1)
            {
                HostPaymentOrderRow l_hostPaymentOrderRow = 
                    (HostPaymentOrderRow) l_lisHostPaymentOrderRows.get(0);
                HostPaymentOrderParams l_hostPaymentOrderParams = 
                    new HostPaymentOrderParams(l_hostPaymentOrderRow);

                //出金額に引数.出金額をセットする。
                l_hostPaymentOrderParams.setPaymentAmount((int)l_dblCashOutAmt);

                //該当レコードを更新する。
                l_queryProcessor.doUpdateQuery(l_hostPaymentOrderParams);
            }

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            //拡張アカウントマネージャ取得する
            WEB3GentradeAccountManager l_accMgr = 
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();

            //補助口座
            SubAccount l_subAccount = null;
            try
            {
                //補助口座オブジェクトを取得する。 
                l_subAccount =
                    l_accMgr.getSubAccount(
                        l_orderUnitParams.getAccountId(),
                        SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            }
            catch (NotFoundException l_ex)
            {
                log.error("補助口座オブジェクトを取得", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            //余力再計算をコール
            WEB3TPTradingPowerReCalcService l_service =
                (WEB3TPTradingPowerReCalcService) Services.getService(
                    WEB3TPTradingPowerReCalcService.class);
            WEB3GentradeSubAccount l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;

            l_service.reCalcTradingPower(l_gentradeSubAccount);
        }
        catch (DataFindException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
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
     * (is出金に伴う当日保証金振替実施)<BR>
     * 出金に伴う当日保証金振替実施かどうかの判断を行う。<BR>
     * <BR>
     * １）　@DB検索<BR>
     * 　@部店プリファ@レンステーブルを以下の条件で検索する。<BR>
     * <BR>
     * 　@[条件] <BR>
     * 　@　@部店ID = 引数.注文単位.部店ID And<BR>
     * 　@　@プリファ@レンス名 = プリファ@レンス名.出金に伴う当日保証金振替実施区分 And<BR>
     * 　@　@プリファ@レンス名の連番 = "1"<BR>
     * <BR>
     * <BR>
     * ２）検索結果.プリファ@レンスの値が 0： DEFAULT の場合、falseを返却する。<BR>
     * 　@　@1： EXECUTE の場合、trueを返却する。<BR>
     * 　@　@該当データなしの場合、falseを返却する。<BR>
     * <BR>
     * @@param l_lngBranchId - (部店ID)<BR>
     * 部店ID
     * @@return boolean
     * @@throws WEB3BaseException
     */
    protected boolean isPaymentTodayDepositTransferEnforce(long l_lngBranchId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "isPaymentTodayDepositTransferEnforce(long)";
        log.entering(STR_METHOD_NAME);

        //部店プリファ@レンステーブルを以下の条件で検索する。
        //[条件]
        //部店ID = 引数.部店ID And
        //プリファ@レンス名 = プリファ@レンス名.出金に伴う当日保証金振替実施区分 And
        //プリファ@レンス名の連番 = "1"
        BranchPreferencesRow l_branchPreferencesRow = null;
        try
        {
            l_branchPreferencesRow =
                BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                    l_lngBranchId,
                    WEB3BranchPreferencesNameDef.CASHOUT_TODAY_DEPOSIT_TRANSFER_DIV,
                    1);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //該当データなしの場合、falseを返却する。
        if (l_branchPreferencesRow == null)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            String l_strPreferencesValue = l_branchPreferencesRow.getValue();
            //検索結果.プリファ@レンスの値が 0： DEFAULT の場合、falseを返却する。
            if (WEB3CashoutTodayDepositTransferDivDef.DEFAULT.equals(l_strPreferencesValue))
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }
            //1： EXECUTE の場合、trueを返却する。
            else if (WEB3CashoutTodayDepositTransferDivDef.EXECUTE.equals(l_strPreferencesValue))
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
    }
}
@
