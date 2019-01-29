head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOrderNotifyNormalTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物注文通知正常処理一件TransactionCallback(WEB3FuturesOrderNotifyNormalTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/23 李志強(日本中訊) 新規作成
*/


package webbroker3.ifo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.ifo.data.HostFotypeOrderReceiptParams;
import webbroker3.ifo.data.HostFotypeOrderReceiptRow;
import webbroker3.ifo.service.delegate.WEB3FuturesOrderNotifyUnitService;
import webbroker3.util.WEB3LogUtility;


/**
 * （先物注文通知正常処理一件TransactionCallback）。<BR>
 * <BR>
 * トランザクション処理を実施する内部クラス。<BR>
 * @@author 李志強
 * @@version 1.0
 */
public class WEB3FuturesOrderNotifyNormalTransactionCallback implements TransactionCallback
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesOrderNotifyNormalTransactionCallback.class);

    /**
      * 先物OP注文通知キューRowオブジェクト。<BR>
      */
    private HostFotypeOrderReceiptRow hostFotypeOrderReceiptRow;

    /**
      * 先物OP注文通知キューParamsオブジェクト。<BR>
      */
    private HostFotypeOrderReceiptParams hostFotypeOrderReceiptParams;

    /**
     * コンストラクタ。<BR>
     * 引数で指定されたオブジェクトを、インスタンス変数にセットする。<BR>
     * @@params l_hostFotypeOrderReceiptRow - (先物OP注文通知キューRow)
     * @@params l_hostFotypeOrderReceiptParams - (先物OP注文通知キューParams)
     */
    public WEB3FuturesOrderNotifyNormalTransactionCallback(
        HostFotypeOrderReceiptRow l_hostFotypeOrderReceiptRow,
        HostFotypeOrderReceiptParams l_hostFotypeOrderReceiptParams)
    {
        hostFotypeOrderReceiptRow = l_hostFotypeOrderReceiptRow;
        hostFotypeOrderReceiptParams = l_hostFotypeOrderReceiptParams;
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

        HostFotypeOrderReceiptRow l_receiptRow = hostFotypeOrderReceiptRow;
        HostFotypeOrderReceiptParams l_notifyParams = hostFotypeOrderReceiptParams;

        //1.2.1 getInstitution(証券会社コード : 論理ビュー::java::lang::String)
        //  [getInstitution()に指定する引数]
        //  証券会社コード：　@先物OP注文通知キューレコード.証券会社コード
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        try
        {
            Institution l_institution = l_accountManager.getInstitution(
                l_receiptRow.getInstitutionCode());

            //1.2.2 getMainAccount(証券会社ID : long, 部店コード : 論理ビュー::java::lang::String, 口座コード : 論理ビュー::java::lang::String)
            //  [getMainAccount()に指定する引数]
            //  証券会社ID：　@getInstitution()の戻り値.getInstitutionId()
            //  部店コード：　@先物OP注文通知キューレコード.部店コード
            //  口座コード：　@先物OP注文通知キューレコード.顧客コード
            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_accountManager.getMainAccount(
                l_institution.getInstitutionId(),
                l_receiptRow.getBranchCode(),
                l_receiptRow.getAccountCode());

            //1.2.3 getSubAccount(補助口座タイプ : SubAccountTypeEnum)
            //  [getSubAccount()に指定する引数]
            //  補助口座タイプ：　@顧客.getOP取引口座タイプ()の戻り値
            SubAccount l_subAccount = l_mainAccount.getSubAccount(
                SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);

            //  先物注文通知UnitServiceImpl
            WEB3FuturesOrderNotifyUnitService l_orderNotifyUnitService =
                (WEB3FuturesOrderNotifyUnitService)Services.getService(WEB3FuturesOrderNotifyUnitService.class);

            //1.2.4(*2)分岐フロー
            //  (*2)分岐フロー
            //  新規建の場合(処理対象先物OP注文通知キューレコード.取引コード(SONAR) == "先物OP建")、処理実施。
            if (WEB3TransactionTypeSONARDef.OPEN_CONTRACT.equals(l_receiptRow.getSonarTradedCode()))
            {
                //1.2.4.1 notify新規建注文(先物OP注文通知キューParams, 補助口座)(先物注文通知UnitServiceImpl::notify新規建注文)
                //  [notify新規建注文()に指定する引数]
                //  先物OP注文通知キューParams：　@取得した処理対象先物OP注文通知キューテーブルの1レコード
                //  補助口座：　@顧客.getSubAccount()の戻り値
                l_orderNotifyUnitService.notifyOpenContractOrder(
                    l_notifyParams,
                    l_subAccount);
            }
            //1.2.5(*3)分岐フロー
            //  (*3)分岐フロー
            //  返済の場合(処理対象先物OP注文通知キューレコード.取引コード(SONAR) == "先物OP埋")、処理実施。
            else if (WEB3TransactionTypeSONARDef.SETTLE_CONTRACT.equals(l_receiptRow.getSonarTradedCode()))
            {
                //1.2.5.1 notify返済注文(先物OP注文通知キューParams, 補助口座)(先物注文通知UnitServiceImpl::notify返済注文)
                //  [notify返済注文()に指定する引数]
                //  先物OP注文通知キューParams：　@取得した処理対象先物OP注文通知キューテーブルの1レコード
                //  補助口座：　@顧客.getSubAccount()の戻り値
                l_orderNotifyUnitService.notifySettleContractOrder(
                    l_notifyParams,
                    l_subAccount);
            }
        }
        catch (NotFoundException l_exp)
        {
            ErrorInfo l_errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80005;
            l_errorInfo.setErrorClass(l_exp.getClass().getName());
            throw new DataCallbackException(
                l_exp.getMessage(),
                    new WEB3SystemLayerException(
                    l_errorInfo,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_exp.getMessage(),
                    l_exp));
        }
        catch (WEB3BaseException l_exp)
        {
            ErrorInfo l_errorInfo = l_exp.getErrorInfo();
            l_errorInfo.setErrorClass(l_exp.getClass().getName());
            throw new DataCallbackException(
                l_exp.getErrorMessage(),
                l_exp);
        }

        //1.2.6(*4)キューテーブルに処理区分セット
        //(*4)処理対象先物OP注文通知キューレコード.処理区分を以下の通りセットし更新する。
        //　@[更新内容]
        //  ”処理済”
        QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
        l_notifyParams.setStatus(WEB3StatusDef.DEALT);
        l_notifyParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        l_QueryProcessor.doUpdateQuery(l_notifyParams);

        log.exiting(STR_METHOD_NAME);

        return null;
    }
}

@
