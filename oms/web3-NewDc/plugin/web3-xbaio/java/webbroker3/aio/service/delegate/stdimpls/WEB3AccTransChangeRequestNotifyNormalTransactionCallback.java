head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.34.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AccTransChangeRequestNotifyNormalTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 振替請求通知正常処理一件TransactionCallback(WEB3AccTransChangeRequestNotifyNormalTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/04/05 李志強(日本中訊) 新規作成
*/


package webbroker3.aio.service.delegate.stdimpls;

import java.util.Hashtable;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;

import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.data.HostTransferReceiptParams;
import webbroker3.aio.data.HostTransferReceiptRow;
import webbroker3.aio.service.delegate.WEB3AccTransChangeAcceptUnitService;
import webbroker3.aio.service.delegate.WEB3AccTransChangeCompleteUnitService;
import webbroker3.aio.service.delegate.WEB3AccTransChangeRequestNotifyUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AcceptDivDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3LogUtility;


/**
 * （振替請求通知正常処理一件TransactionCallback）。<BR>
 * <BR>
 * トランザクション処理を実施する内部クラス。<BR>
 * @@author 李志強
 * @@version 1.0
 */
public class WEB3AccTransChangeRequestNotifyNormalTransactionCallback implements TransactionCallback
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccTransChangeRequestNotifyNormalTransactionCallback.class);

    /**
      * 振替入力通知キューParamsオブジェクト。<BR>
      */
    private HostTransferReceiptParams hostTransferReceiptParams;

    /**
      * 注文種別オブジェクト。<BR>
      */
    private OrderTypeEnum orderType;

    /**
     * コンストラクタ。<BR>
     * 引数で指定されたオブジェクトを、インスタンス変数にセットする。<BR>
     * @@params l_hostTransferReceiptParams - (振替入力通知キューParams)
     * @@params l_orderType - (注文種別)
     */
    public WEB3AccTransChangeRequestNotifyNormalTransactionCallback(
        HostTransferReceiptParams l_hostTransferReceiptParams,
        OrderTypeEnum l_orderType)
    {
        hostTransferReceiptParams = l_hostTransferReceiptParams;
        orderType = l_orderType;
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

        HostTransferReceiptParams l_hostTransferReceiptParams = hostTransferReceiptParams;

        OrderTypeEnum l_orderType = orderType;
        try
        {
            //1.2.2)
            if (OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderType) ||
                OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE.equals(l_orderType) ||
                OrderTypeEnum.TO_OTHER_TRANSFER.equals(l_orderType) ||
                OrderTypeEnum.FROM_OTHER_TRANSFER.equals(l_orderType))
            {
                AssetTransferTypeEnum l_assetTransferType = null;
                //　@・注文種別が以下のいずれかの場合、2（出金）
                //1011(為替保証金振替注文(預かり金から為替保証金))
                //1017(その他振替注文(預かり金からX))
                if (OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderType) ||
                    OrderTypeEnum.TO_OTHER_TRANSFER.equals(l_orderType))
                {
                    l_assetTransferType = AssetTransferTypeEnum.CASH_OUT;
                }
                //・注文種別が以下のいずれかの場合、1（入金）
                //1012(為替保証金振替注文(為替保証金から預かり金))
                //1018(その他振替注文(Xから預かり金))
                else if (OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE.equals(l_orderType) ||
                        OrderTypeEnum.FROM_OTHER_TRANSFER.equals(l_orderType))
                {
                    l_assetTransferType = AssetTransferTypeEnum.CASH_IN;
                }
                // 1.2.2.1) 新規為替保証金振替注文の登録を行う。
                // [引数]
                // 振替入力通知キューParams： 振替入力通知キューParamsオブジェクト
                // 注文種別： get注文種別()の戻り値
                // 振替タイプ：
                //  ・注文種別が以下のいずれかの場合、2（出金）
                //      1011(為替保証金振替注文(預かり金から為替保証金))
                //      1017(その他振替注文(預かり金からX))
                //  ・注文種別が以下のいずれかの場合、1（入金）
                //      1012(為替保証金振替注文(為替保証金から預かり金))
                //      1018(その他振替注文(Xから預かり金))
                this.createOrder(
                    l_hostTransferReceiptParams,
                    l_orderType,
                    l_assetTransferType);
            }
            //1.2.3
            else
            {
                //1.2.3.1 新規入金注文の登録を行う。
                //振替入力通知キューParams： 振替入力通知キューParamsオブジェクト
                //注文種別： get注文種別()の戻り値
                //振替タイプ： 1（入金）
                this.createOrder(
                    l_hostTransferReceiptParams,
                    l_orderType,
                    AssetTransferTypeEnum.CASH_IN);

                //1.2.3.2 新規出金注文の登録を行う。
                //[引数]
                //振替入力通知キューParams： 振替入力通知キューParamsオブジェクト
                //注文種別： get注文種別()の戻り値
                //振替タイプ： 2（出金）
                this.createOrder(
                    l_hostTransferReceiptParams,
                    l_orderType,
                    AssetTransferTypeEnum.CASH_OUT);
            }
        }
        catch(WEB3BaseException l_exp)
        {
            ErrorInfo l_errorInfo = l_exp.getErrorInfo();
            l_errorInfo.setErrorClass(l_exp.getClass().getName());
            throw new DataCallbackException(
                l_exp.getErrorMessage(),
                l_errorInfo);
        }


        // 処理対象の振替入力通知テーブル.処理区分を設定用
        String l_strUpdateWhere = " rowid = ? ";

        String[] l_strArrayUpdateParams = {
            l_hostTransferReceiptParams.getRowid()
        };

        Map l_map = new Hashtable();

        // 処理対象の振替入力通知テーブル.処理区分を設定用
        l_map.put("status", WEB3StatusDef.DEALT);

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        // 1.2.4　@処理対象の取消受付キューレコード.処理区分を設定”
        l_queryProcessor.doUpdateAllQuery(
            HostTransferReceiptRow.TYPE,
            l_strUpdateWhere,
            l_strArrayUpdateParams,
            l_map);

        log.exiting(STR_METHOD_NAME);

        return null;
    }

    /**
     * (create注文)<BR>
     * 振替注文を生成する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（振替請求通知）create注文」 参照<BR>
     * @@param l_hostTransferReceiptParams - (振替入力通知キューParamsオブジェクト)<BR>
     * @@param l_orderType - (注文種別)<BR>
     * @@param l_changeType - (振替タイプ)<BR>
     * @@throws WEB3BaseException
     * @@roseuid 413D3C370036
     */
    protected void createOrder(
        HostTransferReceiptParams l_hostTransferReceiptParams,
        OrderTypeEnum l_orderType,
        AssetTransferTypeEnum l_changeType)
            throws WEB3BaseException
    {
        String STR_METHOD_NAME = "createOrder(" +
            "HostTransferReceiptParams l_hostTransferReceiptParams," +
            "OrderTypeEnum l_orderType," +
            "AssetTransferTypeEnum l_changeType)";

        log.entering(STR_METHOD_NAME);

        // 1) 新規注文の登録を行う。
        // [引数]
        // 振替入力通知キューParams： 振替入力通知キューParamsオブジェクト
        // 注文種別： 引数.注文種別
        // 振替タイプ： 引数.振替タイプ
        WEB3AccTransChangeRequestNotifyUnitService l_notifyUnitService =
            (WEB3AccTransChangeRequestNotifyUnitService)Services.getService(
                WEB3AccTransChangeRequestNotifyUnitService.class);
        long l_lngOrderID = l_notifyUnitService.submitOrder(
                l_hostTransferReceiptParams,
                l_orderType,
                l_changeType);
        log.debug("l_lngOrderID = " + l_lngOrderID);
        // 2) 注文単位を取得する。
        // (*配列の1番目の要素を取得する）
        // [引数]
        // 注文ID： submit注文()の戻り値
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3AioOrderManager l_orderManager =
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderID);
        AioOrderUnit l_orderUnit = (AioOrderUnit) l_orderUnits[0];

        // 3）振替受付DB更新処理を行う。
        // [引数]
        // 注文単位： 注文単位オブジェクト
        // エラーコード： "0000"（正常）
        // 受付通知区分： "1"（受付完了）
        WEB3AccTransChangeAcceptUnitService l_acceptUnitService =
            (WEB3AccTransChangeAcceptUnitService) Services.getService(
                WEB3AccTransChangeAcceptUnitService.class);

        l_acceptUnitService.execute(
            l_orderUnit,
            WEB3ErrorReasonCodeDef.NORMAL,
            WEB3AcceptDivDef.CASH_TRANS_SERVICE_COMPLETE);

        // 4) 振替完了処理に伴う注文データの更新とトランザクションデータの生成を行う。
        // [引数]
        // 注文単位： 注文単位オブジェクト
        WEB3AccTransChangeCompleteUnitService l_completeUnitService =
            (WEB3AccTransChangeCompleteUnitService) Services.getService(
            WEB3AccTransChangeCompleteUnitService.class);
        l_completeUnitService.completeChange(l_orderUnit);

        // 5) 補助口座オブジェクトを取得する。
        // ［引数］
        // 口座ID： 注文単位.口座ID
        // 補助口座ID： 注文単位.補助口座ID
        WEB3GentradeAccountManager l_accManage =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();

        SubAccount l_subAccount = null;
        try
        {
            l_subAccount = l_accManage.getSubAccount(
                l_orderUnit.getAccountId(),
                l_orderUnit.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("補助口座オブジェクトを取得する:",l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }

        if(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT.equals(
            l_subAccount.getSubAccountType()))
        {
            //=========remain zhou-yong NO.1 begin ===========

            // 6) 余力再計算(補助口座 : 補助口座)
            //アイテムの定義
            //余力の更新を行う。
            //[引数]
            //補助口座： get補助口座()の戻り値
			WEB3TPTradingPowerReCalcService l_service =
                (WEB3TPTradingPowerReCalcService) Services.getService(
			WEB3TPTradingPowerReCalcService.class);

            WEB3GentradeSubAccount l_gentradeSubAccount =
                (WEB3GentradeSubAccount)l_subAccount;

            l_service.reCalcTradingPower(l_gentradeSubAccount);

            //=========remain zhou-yong NO.1 end ===========

        }
        log.exiting(STR_METHOD_NAME);
    }
}

@
