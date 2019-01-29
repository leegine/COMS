head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.29.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSonarCashTransForeignNormalTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : SONAR入出金（外貨）正常処理一件TransactionCallback(WEB3AioSonarCashTransForeignNormalTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/28 徐宏偉 (中訊) 新規作成
Revesion History : 2007/03/01 徐宏偉 (中訊) 実装の問題009
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
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
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderManager;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.data.HostForeignCashTransferParams;
import webbroker3.aio.data.HostForeignCashTransferRow;
import webbroker3.aio.service.delegate.WEB3AioCashTransferAcceptUnitService;
import webbroker3.aio.service.delegate.WEB3AioCashTransferCompleteUnitService;
import webbroker3.aio.service.delegate.WEB3AioSonarCashTransForeignUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AcceptDivDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3OrderDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.util.WEB3LogUtility;

/**
 * (SONAR入出金（外貨）正常処理一件TransactionCallback)<BR>
 * <BR>
 * トランザクション処理を実施する内部クラス。<BR>
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3AioSonarCashTransForeignNormalTransactionCallback 
    implements TransactionCallback
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioSonarCashTransForeignNormalTransactionCallback.class);

    /**
      * 外貨入出金テーブルRowオブジェクト。<BR>
      */
    private HostForeignCashTransferRow hostForeignCashTransferRow;

    /**
     * コンストラクタ。<BR>
     * 引数で指定されたオブジェクトを、インスタンス変数にセットする。<BR>
     * @@params l_hostForeignCashTransferRow - (外貨入出金テーブルRow)
     */
    public WEB3AioSonarCashTransForeignNormalTransactionCallback(
        HostForeignCashTransferRow l_hostForeignCashTransferRow)
    {
        hostForeignCashTransferRow = l_hostForeignCashTransferRow;
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

        // SONAR入出金（外貨）UnitServiceを取得する。
        WEB3AioSonarCashTransForeignUnitService
            l_aioSonarCashTransForeignUnitService =
                (WEB3AioSonarCashTransForeignUnitService)Services.getService(
                    WEB3AioSonarCashTransForeignUnitService.class);

        // 入出金受付UnitServiceを取得する。
        WEB3AioCashTransferAcceptUnitService
            l_aioCashTransferAcceptUnitService =
                (WEB3AioCashTransferAcceptUnitService)Services.getService(
                    WEB3AioCashTransferAcceptUnitService.class);

        //入出金完了UnitServiceを取得する。
        WEB3AioCashTransferCompleteUnitService
            l_aioCashTransferCompleteUnitService =
                (WEB3AioCashTransferCompleteUnitService)Services.getService(
                    WEB3AioCashTransferCompleteUnitService.class);

        //外貨入出金注文単位
        AioOrderUnit l_aioOrderUnit = null;

        HostForeignCashTransferRow l_hostForeignCashTransferRow = hostForeignCashTransferRow;
        HostForeignCashTransferParams l_hostForeignCashTransferParams =
            new HostForeignCashTransferParams(l_hostForeignCashTransferRow);

        //入出金金額を取得
        double l_dblAmount = l_hostForeignCashTransferParams.getAmount();
        log.debug("外貨入出金金額を取得:"+ l_dblAmount);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        //入出金注文マネージャクラスを取得する。
        WEB3AioOrderManager l_aioOrderManager =
            (WEB3AioOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();

        try
        {
            //１.２.１）外貨入出金Params.振替入出金額 > 0の場合
            if (l_dblAmount > 0)
            {
                //１.２.１.1）submit注文(外貨入出金Params)
                //新規注文の登録を行う。
                //[引数] 外貨入出金Params： 外貨入出金Paramsオブジェクト

                //注文IDを取得する
                long l_lngOrderId =
                    l_aioSonarCashTransForeignUnitService.submitOrder(
                        l_hostForeignCashTransferParams);

                //１.２.１.２）execute(AioOrderUnit, String, String)
                //入出金受付DB更新処理を行う。
                //[引数]
                //注文単位： 注文(submit注文()の戻り値).getOrderUnits()[0] の戻り値
                //エラーコード： "0000"（正常）
                //受付通知区分： "1"（受付完了）

                //外貨入出金注文単位オブジェクトを取得する
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

            //１.２.２）外貨入出金Params.振替入出金額 < 0 の場合
            if (l_dblAmount < 0)
            {
                //１.２.２.１）get注文単位(外貨入出金Params)
                //取消対象の注文単位を取得する。
                //[引数] 外貨入出金Params： 外貨入出金Paramsオブジェクト
                l_aioOrderUnit =
                    this.getOrderUnit(l_hostForeignCashTransferParams);

                //１.２.２.２）complete入出金取消(AioOrderUnit)
                //注文の取消処理を行う。
                //[引数] 注文単位： get注文単位()の戻り値
                l_aioCashTransferCompleteUnitService.completeCashTransferCancel(l_aioOrderUnit);
            }
        }
        catch(WEB3BaseException l_exp)
        {
            ErrorInfo l_errorInfo = l_exp.getErrorInfo();
            l_errorInfo.setErrorClass(l_exp.getClass().getName());
            log.exiting(STR_METHOD_NAME);
            throw new DataCallbackException(
                l_exp.getErrorMessage(),
                l_errorInfo);
        }

        HashMap l_map = new HashMap();
        
        //外貨入出金テーブルの処理区分の更新条件設定
        String l_strUpdateWhere = " rowid = ?";
        
        //外貨入出金テーブルの処理区分の更新値設定
        String[] l_strUpdateParams = {l_hostForeignCashTransferRow.getRowid()};
        l_map.put("status", WEB3StatusDef.DEALT);

        //クエリプロセッサを取得する。
        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

        //１.２.３)(*2) 外貨入出金テーブルの処理区分の更新
        // (*2)外貨入出金テーブル.処理区分に以下の値をセットして更新する。
        l_queryProcessor.doUpdateAllQuery(
            HostForeignCashTransferRow.TYPE,
            l_strUpdateWhere,
            l_strUpdateParams,
            l_map);

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (get注文単位)<BR>
     * 外貨入出金Paramsの内容に合致する注文単位を取得する。<BR> 
     * <BR>
     * １）証券会社オブジェクトを取得する。<BR> 
     * <BR>
     * 　@[コンストラクタに渡す引数] <BR>
     * 　@外貨入出金Params.証券会社コード <BR>
     * <BR>
     * ２）部店オブジェクトを取得する。<BR> 
     * <BR>
     * 　@[コンストラクタに渡す引数] <BR>
     * 　@証券会社オブジェクト <BR>
     * 　@外貨入出金Params.部店コード <BR>
     * <BR>
     * ３）顧客オブジェクトを取得する。<BR> 
     * <BR>
     * 　@[コンストラクタに渡す引数]<BR> 
     * 　@証券会社.証券会社ID <BR>
     * 　@部店.部店ID <BR>
     * 　@外貨入出金Params.顧客コード<BR> 
     * <BR>
     * ４）補助口座オブジェクトを取得する。<BR> 
     * <BR>
     * 　@[コンストラクタに渡す引数] <BR>
     * 　@1（預り金口座） <BR>
     * <BR>
     * ５）以下の条件で、取消対象となる注文単位を検索する。<BR> 
     * <BR>
     * 　@　@[検索条件] <BR>
     * 　@　@口座ID： 口座.口座ID <BR>
     * 　@　@補助口座ID： 補助口座.補助口座ID <BR>
     * 　@　@部店ID： 部店.部店ID <BR>
     * 　@　@注文種別： 外貨入出金Params.入出金区分='1'（出金）の場合、1001”出金注文”<BR> 
     * 　@　@　@　@　@　@　@　@　@外貨入出金Params.入出金区分='2'（入金）の場合、1002”入金注文”<BR> 
     * 　@　@注文状態： 6”発注失敗（新規注文）”、14”発注済（取消注文）”以外 <BR>
     * 　@　@注文数量： 外貨入出金Params.振替入出金額 * -1 <BR>
     * 　@　@受渡日： 外貨入出金Params.受渡日 <BR>
     * 　@　@通貨コード： 外貨入出金Params.通貨コード <BR>
     * <BR>
     * ６）取得した注文単位を返す。 <BR>
     * 　@　@※複数取得された場合は、注文単位.受注日時の一番古いものを返却する。<BR>
     * <BR>
     * @@param l_hostCashTransferForeignParams - (外貨入出金Params)<BR>
     * 外貨入出金Paramsオブジェクト
     * <BR>
     * @@return AioOrderUnit
     * @@throws WEB3BaseException
     */
    protected AioOrderUnit getOrderUnit(
        HostForeignCashTransferParams l_hostForeignCashTransferParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderUnit(HostCashTransferForeignParams)";
        log.entering(STR_METHOD_NAME);

        if (l_hostForeignCashTransferParams == null)
        {
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
       
        try
        {
            // 外貨入出金Paramsの内容に合致する注文単位を取得する。 
            // １）証券会社オブジェクトを取得する。 
            //   [コンストラクタに渡す引数] 
            //   外貨入出金Params.証券会社コード 
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            String l_strInstitutionCode = 
                l_hostForeignCashTransferParams.getInstitutionCode();
            Institution l_institution = 
                l_accountManager.getInstitution(l_strInstitutionCode);
            
            // ２）部店オブジェクトを取得する。 
            //   [コンストラクタに渡す引数] 
            //   証券会社オブジェクト 
            //   外貨入出金Params.部店コード 
            Branch l_branch =
                l_accountManager.getBranch(
                    l_institution, l_hostForeignCashTransferParams.getBranchCode());
            
            // ３）顧客オブジェクトを取得する。 
            //   [コンストラクタに渡す引数] 
            //   証券会社.証券会社ID 
            //   部店.部店ID 
            //   外貨入出金Params.顧客コード 
            MainAccount l_mainAccount =
                l_accountManager.getMainAccount(
                    l_institution.getInstitutionId(),
                    l_branch.getBranchId(),
                    l_hostForeignCashTransferParams.getAccountCode());
            
            // ４）補助口座オブジェクトを取得する。 
            //   [コンストラクタに渡す引数] 
            //   1（預り金口座） 
            SubAccount l_subAccount =
                l_mainAccount.getSubAccount(
                    SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            // ５）以下の条件で、取消対象となる注文単位を検索する。 
            //    [検索条件] 
            StringBuffer l_sbWhere = new StringBuffer();
            
            // 口座ID
            l_sbWhere.append(" account_id = ? ");
            
            //補助口座ID
            l_sbWhere.append(" and sub_account_id = ? ");
            
            //部店ID
            l_sbWhere.append(" and branch_id = ? ");
            
            //注文種別
            l_sbWhere.append(" and order_type = ? ");
            
            //注文状態
            l_sbWhere.append(" and order_status != ? ");
            
            //注文状態
            l_sbWhere.append(" and order_status != ? ");
            
            //注文数量
            l_sbWhere.append(" and quantity = ? ");
            
            //受渡日
            l_sbWhere.append(" and delivery_date = ? ");
            
            //通貨コード
            l_sbWhere.append(" and currency_code = ? ");

            // 口座ID： 口座.口座IDを取得する
            long l_lngAccountId =  l_mainAccount.getAccountId();
            
            //補助口座ID： 補助口座.補助口座ID
            long l_lngSubAccountId = l_subAccount.getSubAccountId();
            
            //部店ID： 部店.部店ID
            long l_lngBranchId = l_branch.getBranchId();
            
            //    注文種別： 外貨入出金Params.入出金区分='1'（出金）の場合、1001”出金注文” 
            //                  外貨入出金Params.入出金区分='2'（入金）の場合、1002”入金注文” 
            OrderTypeEnum l_orderType = null;
            if (WEB3OrderDivDef.CASHOUT.equals(l_hostForeignCashTransferParams.getOrderDiv()))
            {
                l_orderType = OrderTypeEnum.CASH_OUT;
            }
            else if (WEB3OrderDivDef.CASHIN.equals(l_hostForeignCashTransferParams.getOrderDiv()))
            {
                l_orderType = OrderTypeEnum.CASH_IN;
            }
            
            //    注文数量： 外貨入出金Params.振替入出金額 * -1 
            double l_dblQuantity = l_hostForeignCashTransferParams.getAmount() * -1;
            
            //    受渡日： 外貨入出金Params.受渡日 
            Timestamp l_tsDeliveryDate =
                l_hostForeignCashTransferParams.getCashTransDate();
            
            //    通貨コード： 外貨入出金Params.通貨コード 
            String l_strCurrencyCode = l_hostForeignCashTransferParams.getCurrencyCode();
            
            Object[] l_objValues =
            {
                new Long(l_lngAccountId),
                new Long(l_lngSubAccountId),
                new Long(l_lngBranchId),
                l_orderType,
                OrderStatusEnum.NOT_ORDERED, 
                OrderStatusEnum.CANCELLED, 
                new Double(l_dblQuantity),
                l_tsDeliveryDate,
                l_strCurrencyCode
            };

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //検索
            List l_lisAioOrderUnitRows =
                l_queryProcessor.doFindAllQuery(
                    AioOrderUnitRow.TYPE,
                    l_sbWhere.toString(),
                    "received_date_time asc",
                    null,
                    l_objValues);

            if (l_lisAioOrderUnitRows == null || l_lisAioOrderUnitRows.isEmpty())
            {
                log.debug("Error In 注文単位を検索する ");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            //注文マネージャクラスを取得する。
            AioOrderManager l_orderManager =
                (AioOrderManager)GtlUtils.getTradingModule(
                    ProductTypeEnum.AIO).getOrderManager();

            //注文単位オブジェクトを取得する
            AioOrderUnitRow l_orderUnitRow =
               (AioOrderUnitRow)l_lisAioOrderUnitRows.get(0);
            AioOrderUnit l_orderUnit =
                (AioOrderUnit)l_orderManager.toOrderUnit(l_orderUnitRow);

            //* ６）取得した注文単位を返す。 
            //*    ※複数取得された場合は、注文単位.受注日時の一番古いものを返却する。
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
}
@
