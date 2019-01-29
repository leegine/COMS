head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.32.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSonarCashTransNormalTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : SONAR入出金正常処理一件TransactionCallback(WEB3AioSonarCashTransNormalTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/04/20 李志強(日本中訊) 新規作成
Revesion History : 2006/02/24 玉岡(SRA) 本番障害H00109対応
Revesion History : 2006/7/24 周捷(中訊) 仕様変更モデル 607
Revesion History : 2007/02/07 徐宏偉(中訊) 仕様変更モデル 696
Revesion History : 2007/02/28 長瀬 亜紀(SCS) 仕様変更モデル 709
Revesion History : 2007/03/19 長瀬 亜紀(SCS) 仕様変更モデル 717
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderManager;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.data.HostCashTransferParams;
import webbroker3.aio.data.HostCashTransferRow;
import webbroker3.aio.service.delegate.WEB3AioCashTransferAcceptUnitService;
import webbroker3.aio.service.delegate.WEB3AioCashTransferCompleteUnitService;
import webbroker3.aio.service.delegate.WEB3AioSonarCashTransUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AcceptDivDef;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3OrderDivDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3PaymentAutomaticDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3LogUtility;


/**
 * （入金受付正常処理一件TransactionCallback）。<BR>
 * <BR>
 * トランザクション処理を実施する内部クラス。<BR>
 * @@author 李志強
 * @@version 1.0
 */
public class WEB3AioSonarCashTransNormalTransactionCallback implements TransactionCallback
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSonarCashTransNormalTransactionCallback.class);

    /**
      * 入出金テーブルRowオブジェクト。<BR>
      */
    private HostCashTransferRow hostCashTransferRow;


    /**
     * コンストラクタ。<BR>
     * 引数で指定されたオブジェクトを、インスタンス変数にセットする。<BR>
     * @@params l_hostCashTransOrderAcceptParams - (入出金テーブルRow)
     */
    public WEB3AioSonarCashTransNormalTransactionCallback(
        HostCashTransferRow l_hostCashTransferRow)
    {
        hostCashTransferRow = l_hostCashTransferRow;
    }

    /**
     * トランザクション処理を実施する。<BR>
     * @@return Object
     * @@throws DataQueryException, DataNetworkException, DataCallbackException
     */
    public Object process()
        throws DataQueryException, DataNetworkException, DataCallbackException
    {
        final String STR_METHOD_NAME = "process()";
        log.entering(STR_METHOD_NAME);

        // SONAR入出金UnitServiceを取得する。
        WEB3AioSonarCashTransUnitService
            l_aioSonarCashTransUnitService =
                (WEB3AioSonarCashTransUnitService) Services.getService(
                    WEB3AioSonarCashTransUnitService.class);

        // 入出金受付UnitServiceを取得する。
        WEB3AioCashTransferAcceptUnitService
            l_aioCashTransferAcceptUnitService =
                (WEB3AioCashTransferAcceptUnitService) Services.getService(
                    WEB3AioCashTransferAcceptUnitService.class);

        //入出金完了UnitServiceを取得する。
        WEB3AioCashTransferCompleteUnitService
            l_aioCashTransferCompleteUnitService =
                (WEB3AioCashTransferCompleteUnitService) Services.getService(
                    WEB3AioCashTransferCompleteUnitService.class);

        //入出金注文単位
        AioOrderUnit l_aioOrderUnit = null;

        HostCashTransferRow l_hostCashTransferRow = hostCashTransferRow;
        HostCashTransferParams l_hostCashTransferParams =
            new HostCashTransferParams(l_hostCashTransferRow);

        //入出金金額を取得
        long l_lngAmount = l_hostCashTransferParams.getAmount();
        log.debug("入出金金額を取得:"+ l_lngAmount);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        //入出金注文マネージャクラスを取得する。
        WEB3AioOrderManager l_aioOrderManager =
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
        //拡張アカウントマネージャ取得する
        WEB3GentradeAccountManager l_accMgr =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();

        try
        {
            //１.２.１）入出金Params.入出金金額 > 0 and is重複注文()の戻り値 == false の場合
            if (l_lngAmount > 0 && !isDuplicateOrder(l_hostCashTransferParams))
            {

                //１.２.１.1）submit注文(入出金Params)
                //新規注文の登録を行う。
                //[引数] 入出金Params： 入出金Paramsオブジェクト

                //注文IDを取得する
                long l_lngOrderId =
                    l_aioSonarCashTransUnitService.submitOrder(
                        l_hostCashTransferParams);

                //１.２.１.２）execute(AioOrderUnit, String, String)
                //入出金受付DB更新処理を行う。
                //[引数]
                //注文単位： 注文(submit注文()の戻り値).getOrderUnits()[0] の戻り値
                //エラーコード： "0000"（正常）
                //受付通知区分： "1"（受付完了）

                //入出金注文単位オブジェクトを取得する
                //===================NotFoundException=================
                l_aioOrderUnit =
                    (AioOrderUnit)l_aioOrderManager.getOrderUnits(l_lngOrderId)[0];

                //入出金受付DB更新処理を行う
                l_aioCashTransferAcceptUnitService.execute(
                    l_aioOrderUnit,
                    WEB3ErrorReasonCodeDef.NORMAL,
                    WEB3AcceptDivDef.CASH_TRANS_SERVICE_COMPLETE);

                //１.２.１.３）complete入出金(AioOrderUnit)
                //入出金完了処理に伴う注文データの更新とトランザクションデータの生成を行う。
                //[引数]
                //注文単位： 注文(submit注文()の戻り値).getOrderUnits()[0] の戻り値
                l_aioCashTransferCompleteUnitService.completeCashTransfer(l_aioOrderUnit);
            }

            //１.２.２）入出金Params.入出金金額 < 0 の場合
            if (l_lngAmount < 0)
            {
                //１.２.２.１）get注文単位(入出金Params)
                //取消対象の注文単位を取得する。
                //[引数] 入出金Params： 入出金Paramsオブジェクト
                l_aioOrderUnit =
                    this.getOrderUnit(l_hostCashTransferParams);

                //１.２.２.２）complete入出金取消(AioOrderUnit)
                //注文の取消処理を行う。
                //[引数] 注文単位： get注文単位()の戻り値
                l_aioCashTransferCompleteUnitService.completeCashTransferCancel(l_aioOrderUnit);
            }

            //１.２.３） InstitutionImpl(InstitutionRow)
            //証券会社インスタンスを生成する。
            //[引数]
            //証券会社コード： 入出金テーブル.証券会社コード
            //===============NotfoundException===================
            Institution l_institution =
                l_accMgr.getInstitution(
                    l_hostCashTransferParams.getInstitutionCode());

            //１.２.４)MainAccountImpl(MainAccountRow)
            //顧客インスタンスを生成する。
            //[引数]
            //証券会社ID： 証券会社.getInstitutionId()の戻り値
            //部店コード： 入出金テーブル.部店コード
            //顧客コード： 入出金テーブル.顧客コード
            //===============NotfoundException===================

            MainAccount l_mainAccount =
                l_accMgr.getMainAccount(
                    l_institution.getInstitutionId(),
                    l_hostCashTransferParams.getBranchCode(),
                    l_hostCashTransferParams.getAccountCode());

            //=========remain zhou-yong 2004/12/7 NO.1 begin ===========
            //1.2.5) 余力再計算(補助口座 : 補助口座)
            //アイテムの定義
            //余力の更新をする。
            //[引数]
            //補助口座：　@補助口座オブジェクト
			WEB3TPTradingPowerReCalcService l_service =
                (WEB3TPTradingPowerReCalcService) Services.getService(
			WEB3TPTradingPowerReCalcService.class);
            SubAccount l_subAccount =
                l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

            WEB3GentradeSubAccount l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;

            l_service.reCalcTradingPower(l_gentradeSubAccount);

        }
        catch(WEB3BaseException l_exp)
        {
            ErrorInfo l_errorInfo = l_exp.getErrorInfo();
            l_errorInfo.setErrorClass(l_exp.getClass().getName());
            throw new DataCallbackException(
                l_exp.getErrorMessage(),
                l_errorInfo);
        }
        catch (NotFoundException l_ex)
        {
            ErrorInfo l_errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80005;
            l_errorInfo.setErrorClass(l_ex.getClass().getName());
            throw new DataCallbackException(
                l_ex.getMessage(),
                l_errorInfo);
        }

        HashMap l_map = new HashMap();
        //入出金テーブルの処理区分の更新条件設定
        String l_strUpdateWhere =  " rowid = ? ";
        //入出金テーブルの処理区分の更新値設定
        String[] l_updateParams = {l_hostCashTransferRow.getRowid()};

        l_map.put("status", WEB3StatusDef.DEALT);

        //クエリプロセッサを取得する。
        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

        //１.２.７)メッセージ (*2) 入出金テーブルの処理区分の更新
        // (*2)入出金テーブル.処理区分に以下の値をセットして更新する。
        l_queryProcessor.doUpdateAllQuery(
            HostCashTransferRow.TYPE,
            l_strUpdateWhere,
            l_updateParams,
            l_map);

        log.exiting(STR_METHOD_NAME);

        return null;
    }

    /**
     * (get注文単位)<BR>
     * 入出金Paramsの内容に合致する注文単位を取得する。<BR>
     * <BR>
     * １）証券会社オブジェクトを取得する。<BR>
     * <BR>
     *   [コンストラクタに渡す引数]<BR>
     *   入出金Params.証券会社コード<BR>
     * <BR>
     * ２）部店オブジェクトを取得する。<BR>
     * <BR>
     *   [コンストラクタに渡す引数]<BR>
     *   証券会社オブジェクト<BR>
     *   入出金Params.部店コード<BR>
     * <BR>
     * ３）顧客オブジェクトを取得する。<BR>
     * <BR>
     *   [コンストラクタに渡す引数]<BR>
     *   証券会社.証券会社ID<BR>
     *   部店.部店ID<BR>
     *   入出金Params.顧客コード<BR>
     * <BR>
     * ４）補助口座オブジェクトを取得する。<BR>
     * <BR>
     *   [コンストラクタに渡す引数]<BR>
     *   1（預り金口座）<BR>
     * <BR>
     * ５）以下の条件で、取消対象となる注文単位を検索する。<BR>
     * <BR>
     *    [検索条件]<BR>
     *    口座ID： 口座.口座ID<BR>
     *    補助口座ID： 補助口座.補助口座ID<BR>
     *    部店ID： 部店.部店ID<BR>
     *    注文種別： 入出金Params.区分='1'（出金）の場合、1001<BR>
     *                  入出金Params.区分='2'（入金）の場合、1002<BR>
     *    注文状態： 6”発注失敗（新規注文）”、14”発注済（取消注文）”以外<BR>
     *    数量： 入出金Params.金額 * -1<BR>
     *    入出金Params.区分='1'（出金）の場合、受渡日： 入出金Params.入出金日<BR>
     *    入出金Params.区分='2'（入金）の場合、振替予定日の日付： <BR>
     *        入出金Params.入出金日の前営業日以降の日付<BR>
     *           (TO_CHAR(振替予定日,YYYYMMDD)>TO_CHAR(入出金Params.入出金日の前営業日,YYYYMMDD))、
     *        且つ、<BR>
     *        入出金Params.入出金日(*1)以前の日付<BR>
     *           (TO_CHAR(振替予定日,YYYYMMDD)≦TO_CHAR(入出金Params.入出金日(*1),YYYYMMDD))<BR>
     * <BR>
     *    (*1)入出金Params.入出金日が非営業日の場合、翌営業日をセット<BR>
     * <BR>
     * ６）取得した注文単位を返す。<BR>
     *    ※複数取得された場合は、注文単位.受注日時(received_date_time)の一番古いものを返却する。<BR>
     * <BR>
     * @@param l_hostCashTransferParams - (入出金Params)<BR>
     * 入出金Paramsオブジェクト<BR>
     * @@return AioOrderUnit
     * @@roseuid 41417583006F
     */
    protected AioOrderUnit getOrderUnit(HostCashTransferParams l_hostCashTransferParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
             "getOrderUnit(HostCashTransferParams l_hostCashTransferParams)";
        log.entering(STR_METHOD_NAME);

        if (l_hostCashTransferParams == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            //アカウントマネージャ取得する
            AccountManager l_accountManager = GtlUtils.getAccountManager();

            //１）証券会社オブジェクトを取得する。
            Institution l_institution =
                l_accountManager.getInstitution(
                    l_hostCashTransferParams.getInstitutionCode());

            //２）部店オブジェクトを取得する。
            Branch l_branch =
                l_accountManager.getBranch(
                    l_institution, l_hostCashTransferParams.getBranchCode());

            //３）顧客オブジェクトを取得する。
            MainAccount l_mainAccount =
                l_accountManager.getMainAccount(
                    l_institution.getInstitutionId(),
                    l_branch.getBranchId(),
                    l_hostCashTransferParams.getAccountCode());


            //４）補助口座オブジェクトを取得する。
            SubAccount l_subAccount =
                l_mainAccount.getSubAccount(
                    SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

            //５）以下の条件で、取消対象となる注文単位を検索する。
            StringBuffer l_strWhere = new StringBuffer();
            l_strWhere.append(" account_id = ?");
            l_strWhere.append(" and sub_account_id = ?");
            l_strWhere.append(" and branch_id = ?");
            l_strWhere.append(" and order_type = ?");
            //注文状態： 6”発注失敗（新規注文）”、14”発注済（取消注文）”以外
            l_strWhere.append(" and order_status != ? ");
            l_strWhere.append(" and order_status != ? ");
            l_strWhere.append(" and quantity = ?");
            if (WEB3OrderDivDef.CASHOUT.equals(l_hostCashTransferParams.getOrderDiv()))
            {
                //入出金Params.区分='1'（出金）の場合、受渡日： 入出金Params.入出金日
                l_strWhere.append(" and delivery_date = ? ");
            }
            else if (WEB3OrderDivDef.CASHIN.equals(l_hostCashTransferParams.getOrderDiv()))
            {
                //入出金Params.区分='2'（入金）の場合、振替予定日の日付：
                //入出金Params.入出金日の前営業日以降の日付
                //(TO_CHAR(振替予定日,YYYYMMDD)>TO_CHAR(入出金Params.入出金日の前営業日,YYYYMMDD))、且つ、
                //入出金Params.入出金日(*1)以前の日付
                //(TO_CHAR(振替予定日,YYYYMMDD)≦TO_CHAR(入出金Params.入出金日(*1),YYYYMMDD))
                l_strWhere.append(" and (TO_CHAR(est_transfer_date,'YYYYMMDD') > TO_CHAR(?,'YYYYMMDD') and TO_CHAR(est_transfer_date,'YYYYMMDD') <= TO_CHAR(?,'YYYYMMDD')) ");
            }

            List l_lisValue = new ArrayList();
            
            // 口座ID： 口座.口座IDを取得する
            l_lisValue.add(new Long(l_subAccount.getAccountId()));
            //補助口座ID： 補助口座.補助口座ID
            l_lisValue.add(new Long(l_subAccount.getSubAccountId()));
            //部店ID： 部店.部店ID
            l_lisValue.add(new Long(l_branch.getBranchId()));
            //注文種別： 入出金Params.区分='1'（出金）の場合、1001（出金注文）をセットする。
            if ((WEB3OrderDivDef.CASHOUT).equals((l_hostCashTransferParams.getOrderDiv())))
            {
                l_lisValue.add(OrderTypeEnum.CASH_OUT);
            }
            //注文種別： 入出金Params.区分='2'（入金）の場合、1002（入金注文）をセットする。
            else if ((WEB3OrderDivDef.CASHIN).equals((l_hostCashTransferParams.getOrderDiv())))
            {
                l_lisValue.add(OrderTypeEnum.CASH_IN);
            }
            //注文状態： 6”発注失敗（新規注文）”、14”発注済（取消注文）”以外
            l_lisValue.add(OrderStatusEnum.NOT_ORDERED);
            l_lisValue.add(OrderStatusEnum.CANCELLED);
            //数量を取得する
            l_lisValue.add(new Long(l_hostCashTransferParams.getAmount() * -1));
            //原入出金日： 入出金Params.入出金日を取得する
            Timestamp l_tsDate = l_hostCashTransferParams.getCashTransDate();
            //入出金Params.区分='1'（出金）の場合、受渡日： 入出金Params.入出金日を取得する
            if (WEB3OrderDivDef.CASHOUT.equals(l_hostCashTransferParams.getOrderDiv()))
            {
                l_lisValue.add(l_tsDate);
            }
            //入出金Params.区分='2'（入金）の場合、
            else if (WEB3OrderDivDef.CASHIN.equals(l_hostCashTransferParams.getOrderDiv()))
            {
                //振替予定日： 入出金Params.入出金日の前営業日を取得する
                l_lisValue.add(new WEB3GentradeBizDate(l_tsDate).roll(-1));
                //振替予定日： 入出金Params.入出金日(*1)を取得する
                //(*1)入出金Params.入出金日が非営業日の場合、翌営業日をセット
                String l_strCashTransDate = 
                    WEB3GentradeTradingTimeManagement.getBizDateType(l_tsDate);
                if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strCashTransDate))
                {
                    l_lisValue.add(new WEB3GentradeBizDate(l_tsDate).roll(1));
                }
                else
                {
                    l_lisValue.add(l_tsDate);
                }
            }

            Object[] l_objMutualFrgncalWhere = new Object[l_lisValue.size()];
            l_lisValue.toArray(l_objMutualFrgncalWhere);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //検索
            List l_lisAioOrderUnitRows =
                l_queryProcessor.doFindAllQuery(
                    AioOrderUnitRow.TYPE,
                    l_strWhere.toString(),
                    " received_date_time ",
                    null,
                    l_objMutualFrgncalWhere);

            if (l_lisAioOrderUnitRows == null || l_lisAioOrderUnitRows.isEmpty())
            {
                log.debug("Error In 注文単位を検索する ");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //注文マネージャクラスを取得する。
            AioOrderManager l_orderManager =
                (AioOrderManager) GtlUtils.getTradingModule(
                    ProductTypeEnum.AIO).getOrderManager();

            //注文単位オブジェクトを取得する
            AioOrderUnitRow l_orderUnitRow =
               (AioOrderUnitRow) l_lisAioOrderUnitRows.get(0);
            AioOrderUnit l_orderUnit =
                (AioOrderUnit) l_orderManager.toOrderUnit(l_orderUnitRow);

            //６）取得した注文単位を返す。
            log.exiting(STR_METHOD_NAME);
            return l_orderUnit ;
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataException l_ex)
        {
            log.error("__DataException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (is重複注文)<BR>
     * 入出金Paramsの内容に合致する注文単位があるかどうかを判定する。<BR>
     * <BR>
     * １）証券会社テーブルより、引数.入出金Params.証券会社コードをキーとして検索し、 <BR>
     *　@本部一括自動送金実施="1"の場合、falseを返却する。 <BR>
     *    <BR>
     * ２）引数.入出金Params.入出金区分="2"（入金）or<BR>
     * 　@引数.入出金Params.データコード="FI811"（本部一括）の場合、falseを返却する。<BR>
     * <BR>
     * ３）以下の条件と一致する入出金Paramsを検索する。<BR>
     * <BR>
     *　@[検索条件]<BR>
     *　@データコード： "GI811"（個別）<BR>
     *　@証券会社コード： 引数.入出金Params.証券会社コード<BR>
     *　@部店コード： 引数.入出金Params.部店コード<BR>
     *　@顧客コード： 引数.入出金Params.顧客コード<BR>
     *　@入出金区分： 入出金Params.入出金区分<BR>
     *　@入出金金額： 入出金Params.入出金金額<BR>
     *　@入出金日： 入出金Params.入出金日<BR>
     *　@処理区分： "1"（処理済）<BR>
     * <BR>
     *　@一致する入出金Paramsが取得できた場合、falseを返却する。<BR>
     * <BR>
     * ４）証券会社オブジェクトを取得する。<BR>
     * <BR>
     *　@[コンストラクタに渡す引数]<BR>
     *　@引数.入出金Params.証券会社コード<BR>
     * <BR>
     * ５）部店オブジェクトを取得する。<BR>
     * <BR>
     *　@[コンストラクタに渡す引数]<BR>
     *　@証券会社オブジェクト<BR>
     *　@引数.入出金Params.部店コード<BR>
     * <BR>
     * ６）顧客オブジェクトを取得する。<BR>
     * <BR>
     *　@[コンストラクタに渡す引数]<BR>
     *　@証券会社.証券会社ID<BR>
     *　@部店.部店ID<BR>
     *　@引数.入出金Params.顧客コード<BR>
     * <BR>
     * ７）補助口座オブジェクトを取得する。<BR>
     * <BR>
     *　@[コンストラクタに渡す引数]<BR>
     *　@1（預り金口座）<BR>
     * <BR>
     * ８）以下の条件と一致する注文単位を検索する。<BR>
     * <BR>
     *　@[検索条件]<BR>
     *　@口座ID： 口座.口座ID<BR>
     *　@補助口座ID： 補助口座.補助口座ID<BR>
     *　@部店ID： 部店.部店ID<BR>
     *　@注文種別： OrderTypeEnum.出金<BR>
     *　@注文状態： 1”受付済（新規注文）”、6”発注失敗（新規注文）”、14”発注済（取消注文）”以外 <BR>
     *　@数量： 引数.入出金Params.入出金金額<BR>
     *　@受渡日： 引数.入出金Params.入出金日<BR>
     *　@注文経路区分： 9”ホスト”以外<BR>
     * 　@出金申込区分： null<BR>
     * <BR>
     * ９）条件と一致する注文単位が存在する場合は、trueを返却する。<BR>
     *　@存在しない場合は、falseを返却する。
     * <BR>
     * @@param l_hostCashTransferParams - (入出金Params)<BR>
     * 入出金Paramsオブジェクト<BR>
     * @@return boolean
     * @@roseuid 41417583006F
     */
    protected boolean isDuplicateOrder(HostCashTransferParams l_hostCashTransferParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
             "isDuplicateOrder(HostCashTransferParams l_hostCashTransferParams)";
        log.entering(STR_METHOD_NAME);

        if (l_hostCashTransferParams == null)
        {
            log.debug("パラメータがnull");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        boolean l_blnOrderFlag = false;

        //１）証券会社テーブルより、引数.入出金Params.証券会社コードをキーとして検索し、
        //本部一括自動送金実施="1"の場合、falseを返却する。
        try
        {
        	InstitutionRow l_institutionRow = 
        		InstitutionDao.findRowByInstitutionCode(l_hostCashTransferParams.getInstitutionCode());
        	if (l_institutionRow != null)
        	{
	        	InstitutionParams l_institutionParams = new InstitutionParams(l_institutionRow);
	        	if (WEB3PaymentAutomaticDef.ENFORCEMENT.equals(l_institutionParams.getPaymentAutomatic()))
	        	{
	        		return false;
	        	}
        	}
        }
        catch (DataException l_ex)
        {
            log.error("__DataException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //2）引数.入出金Params.入出金区分="2"（入金）or
        //    引数.入出金Params.データコード="FI811"（本部一括）の場合、falseを返却する。
        //※上記以外の場合のみ後続の処理を行う。
        if (WEB3OrderDivDef.CASHOUT.equals(l_hostCashTransferParams.getOrderDiv()) &&
        		!WEB3HostRequestCodeDef.AIO_ALL_HEADQUARTERS.
        			equals(l_hostCashTransferParams.getRequestCode()))
        {
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

                //3）以下の条件と一致する入出金Paramsを検索する。
                //   [検索条件]
                //   データコード： "GI811"（個別）
                //   証券会社コード： 引数.入出金Params.証券会社コード
                //   部店コード： 引数.入出金Params.部店コード
                //   顧客コード： 引数.入出金Params.顧客コード
                //   入出金区分： 入出金Params.入出金区分
                //   入出金金額： 入出金Params.入出金金額
                //   入出金日： 入出金Params.入出金日
                //   処理区分： "1"（処理済）
                StringBuffer l_strHostCashTransWhere = new StringBuffer();
                l_strHostCashTransWhere.append(" request_code = ?");
                l_strHostCashTransWhere.append(" and institution_code = ?");
                l_strHostCashTransWhere.append(" and branch_code = ?");
                l_strHostCashTransWhere.append(" and account_code = ?");
                l_strHostCashTransWhere.append(" and order_div = ? ");
                l_strHostCashTransWhere.append(" and amount = ? ");
                l_strHostCashTransWhere.append(" and cash_trans_date = ? ");
                l_strHostCashTransWhere.append(" and status = ?");

                Object[] l_objHostCashTransWhere =
                {
                   	WEB3HostRequestCodeDef.AIO_INDIVIDUAL,
                   	l_hostCashTransferParams.institution_code,
                   	l_hostCashTransferParams.branch_code,
                   	l_hostCashTransferParams.account_code,
                   	l_hostCashTransferParams.order_div,
                   	l_hostCashTransferParams.amount,
                   	l_hostCashTransferParams.cash_trans_date,
                   	WEB3StatusDef.DEALT
                };

                //検索
                List l_lisHostCashTransferRows =
                    l_queryProcessor.doFindAllQuery(
                        HostCashTransferRow.TYPE,
                        l_strHostCashTransWhere.toString(),
                        null,
                        null,
                        l_objHostCashTransWhere);
                
                if (l_lisHostCashTransferRows == null || l_lisHostCashTransferRows.isEmpty())
                {
                    //アカウントマネージャ取得する
                    AccountManager l_accountManager = GtlUtils.getAccountManager();

                    //4）証券会社オブジェクトを取得する。
                    Institution l_institution =
                        l_accountManager.getInstitution(
                            l_hostCashTransferParams.getInstitutionCode());

                    //5）部店オブジェクトを取得する。
                    Branch l_branch =
                        l_accountManager.getBranch(
                            l_institution, l_hostCashTransferParams.getBranchCode());

                    //6）顧客オブジェクトを取得する。
                    MainAccount l_mainAccount =
                        l_accountManager.getMainAccount(
                            l_institution.getInstitutionId(),
                            l_branch.getBranchId(),
                            l_hostCashTransferParams.getAccountCode());


                    //7）補助口座オブジェクトを取得する。
                    SubAccount l_subAccount =
                        l_mainAccount.getSubAccount(
                            SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

                    //8）以下の条件と一致する注文単位を検索する。
                    StringBuffer l_strAioOrderUnitWhere = new StringBuffer();
                    l_strAioOrderUnitWhere.append(" account_id = ?");
                    l_strAioOrderUnitWhere.append(" and sub_account_id = ?");
                    l_strAioOrderUnitWhere.append(" and branch_id = ?");
                    l_strAioOrderUnitWhere.append(" and order_type = ?");
                    //注文状態： 1”受付済（新規注文）”、6”発注失敗（新規注文）”、
                    //          14”発注済（取消注文）”以外
                    l_strAioOrderUnitWhere.append(" and order_status != ? ");
                    l_strAioOrderUnitWhere.append(" and order_status != ? ");
                    l_strAioOrderUnitWhere.append(" and order_status != ? ");
                    l_strAioOrderUnitWhere.append(" and quantity = ?");
                    l_strAioOrderUnitWhere.append(" and delivery_date = ? ");
                    l_strAioOrderUnitWhere.append(" and order_root_div != ? ");
                    l_strAioOrderUnitWhere.append(" and payment_application_div is null ");

                    // 口座ID： 口座.口座IDを取得する
                    long l_lngAccountId =  l_subAccount.getAccountId();
                    //補助口座ID： 補助口座.補助口座ID
                    long l_lngSubAccountId = l_subAccount.getSubAccountId();
                    //部店ID： 部店.部店ID
                    long l_lngBranchId = l_branch.getBranchId();
                    //注文種別： OrderTypeEnum.出金
                    OrderTypeEnum l_orderType =  OrderTypeEnum.CASH_OUT;
                    //数量を取得する
                    long l_lngQuantity = l_hostCashTransferParams.getAmount();
                    //受渡日を取得する： 引数.入出金Params.受渡日
                    Timestamp l_tsDeliveryDate =
                        l_hostCashTransferParams.getCashTransDate();
                    Object[] l_objAioOrderUnitWhere =
                    {
                        new Long(l_lngAccountId),
                        new Long(l_lngSubAccountId),
                        new Long(l_lngBranchId),
                        l_orderType,
                        OrderStatusEnum.ACCEPTED, 
                        OrderStatusEnum.NOT_ORDERED, 
                        OrderStatusEnum.CANCELLED, 
                        new Long(l_lngQuantity),
                        l_tsDeliveryDate,
                        WEB3OrderRootDivDef.HOST
                    };

                    //検索
                    List l_lisAioOrderUnitRows =
                        l_queryProcessor.doFindAllQuery(
                            AioOrderUnitRow.TYPE,
                            l_strAioOrderUnitWhere.toString(),
                            null,
                            null,
                            l_objAioOrderUnitWhere);

                    //9）条件と一致する注文単位が存在する場合は、trueを返却する。
                    //存在しない場合は、falseを返却する。
                    if (l_lisAioOrderUnitRows.size() > 0)
                    {
                        log.debug("重複注文あり");
                        l_blnOrderFlag = true;
                    }
                }
            }
            catch (NotFoundException l_ex)
            {
                log.error("__NotFoundException__", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataException l_ex)
            {
                log.error("__DataException__", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_blnOrderFlag;
    }
}




@
