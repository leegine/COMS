head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.18.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMLoginStopStartChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者ログイン停止再開変更サービス実装クラス(WEB3AdminTMLoginStopStartChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research
*/

package webbroker3.trademanagement.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;

import webbroker3.util.WEB3LogUtility;

import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.trademanagement.message.WEB3AdminTMLStopStartChgCompleteRequest;
import webbroker3.trademanagement.message.WEB3AdminTMLStopStartChgCompleteResponse;
import webbroker3.trademanagement.message.WEB3AdminTMLStopStartChgConfirmRequest;
import webbroker3.trademanagement.message.WEB3AdminTMLStopStartChgConfirmResponse;
import webbroker3.trademanagement.message.WEB3AdminTMLStopStartChgInputRequest;
import webbroker3.trademanagement.message.WEB3AdminTMLStopStartChgInputResponse;
import webbroker3.trademanagement.message.WEB3AdminTMLoginPermissionStatusUnit;
import webbroker3.trademanagement.service.delegate.WEB3AdminTMLoginStopStartChangeService;

/**
 * (管理者ログイン停止再開変更サービス実装クラス)<BR>
 * <BR>
 * WEB3AdminTMLoginStopStartChangeServiceImpl<BR>
 * <BR>
 * @@author Babu Prasad
 * @@version 1.0
 */
public class WEB3AdminTMLoginStopStartChangeServiceImpl
    extends WEB3ClientRequestService
    implements WEB3AdminTMLoginStopStartChangeService
{

    /**
     * Log Variable
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMLoginStopStartChangeServiceImpl.class);

    /**
     * @@roseuid 41DD3DB803AB
     */
    public WEB3AdminTMLoginStopStartChangeServiceImpl()
    {

    }

    /**
     * ログイン停止再開変更処理を行う。<BR>
     * <BR>
     * 引数のリクエストデータの型により、<BR>
     * 以下のメソッドを呼び分ける。<BR>
     * <BR>
     * ○管理者・ログイン停止再開変更入力リクエストの場合<BR>
     * 　@this.get変更入力画面()をコールする。<BR>
     * <BR>
     * ○管理者・ログイン停止再開変更確認リクエストの場合<BR>
     * 　@this.validate変更()をコールする。<BR>
     * <BR>
     * ○管理者・ログイン停止再開変更完了リクエストの場合<BR>
     * 　@this.submit変更()をコールする。<BR>
     * <BR>
     * ----<English>--------------<BR>
     * <BR>
     * Execute WEB3AdminTMLoginStopStartChangeService<BR>
     * <BR>
     * Call the following methods and divided them according to the type of the
     * argument, l_request.<BR>
     * <BR>
     * ○If WEB3AdminTMLStopStartChgInputRequest.<BR>
     *       Call this.getChangeInputScreen()<BR>
     * <BR>
     * ○If WEB3AdminTMLStopStartChgConfirmRequest.<BR>
     *       Call this.validateChange()<BR>
     * <BR>
     * ○If WEB3AdminTMLStopStartChgCompleteRequest<BR>
     *       Call this.submitChange() .<BR>
     * <BR>
     * @@param l_request - リクエスト<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 417745700350
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        try
        {
            if (l_request instanceof WEB3AdminTMLStopStartChgInputRequest)
            {
                l_response = getChangeInputScreen(
                                    (WEB3AdminTMLStopStartChgInputRequest) l_request);
            } else if (l_request instanceof WEB3AdminTMLStopStartChgConfirmRequest)
            {
                l_response = validateChange((WEB3AdminTMLStopStartChgConfirmRequest) l_request);
            } else if (l_request instanceof WEB3AdminTMLStopStartChgCompleteRequest)
            {
                l_response = submitChange((WEB3AdminTMLStopStartChgCompleteRequest) l_request);
            } else
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "INPUT リクエスト NOT 管理者ログイン停止再開変更サービスリクエスト");
            }
        } catch (DataQueryException l_dataQueryException)
        {
            String l_strMsg = "Error while aquiring the Data ";
            log.error(l_strMsg, l_dataQueryException);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dataQueryException.toString(),
                l_dataQueryException);
        } catch (DataNetworkException l_dataNetworkException)
        {
            String l_strMsg = "Error while aquiring the Data ";
            log.error(l_strMsg, l_dataNetworkException);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dataNetworkException.toString(),
                l_dataNetworkException);
        } catch (NotFoundException l_notFoundException)
        {
            log.error(l_notFoundException.getMessage(), l_notFoundException);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_notFoundException.getMessage(),
                l_notFoundException);
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (get変更入力画面)<BR>
     * <BR>
     * ログイン停止再開変更入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者ログイン停止再開変更サービス)get変更入力画面」<BR>
     * 参照<BR>
     * <BR>
     * ----<English>----------<BR>
     * <BR>
     * getChangeInputScreen<BR>
     * <BR>
     * Execute WEB3AdminTMLoginStopStartChangeService change input screen process. <BR>
     * <BR>
     * Refer to the sequence diagram "(administrator login stop start change service)
     * getChangeInputScreen"<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * <BR>
     * 管理者・ログイン停止再開変更入力リクエストオブジェクト<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminTMLStopStartChgInputRequest object<BR>
     * <BR>
     * @@return webbroker3.trademnagement.message.WEB3AdminTMLStopStartChgInputResponse
     * @@throws WEB3SystemLayerException WEB3SystemLayerException
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws NotFoundException NotFoundException
     * @@roseuid 417745990237
     */
    protected WEB3AdminTMLStopStartChgInputResponse getChangeInputScreen(
        WEB3AdminTMLStopStartChgInputRequest l_request)
        throws WEB3SystemLayerException, WEB3BaseException, NotFoundException
    {
        final String STR_METHOD_NAME =
            "getChangeInputScreen(WEB3AdminTMLStopStartChgInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3Administrator l_administrator = null;
        WEB3GentradeAccountManager l_accManager = null;
        WEB3AdminTMLoginPermissionStatusUnit l_loginStatusUnit = null;
        WEB3AdminTMLoginPermissionStatusUnit[] l_arrLoginStatusUnit = null;
        WEB3AdminTMLStopStartChgInputResponse l_response = null;
        Institution l_institution = null;
        Branch l_branch = null;
        String[] l_strBranchCode = null;
        String l_strTransactionCategory = null;
        ArrayList l_arrList = null;
        Timestamp l_currentDate = null;
        int l_intBranchCount = 0;
        int l_intLoginStatusCount = 0;
        boolean l_isUpdate = false;

        //1.1 Call validate()
        l_request.validate();
        //1.2 Call getInstanceFromLoginInfo()
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        l_strTransactionCategory = WEB3TransactionCategoryDef.TRADE_MANAGEMENT_LOGIN;
        //1.3 Call validateAuthority
        l_administrator.validateAuthority(l_strTransactionCategory, l_isUpdate);

        l_strBranchCode = l_request.branchCode;
        //1.4 Call validateBranchPermission()
        l_administrator.validateBranchPermission(l_strBranchCode);
        //1.5 Call getInstitution()
        l_institution = l_administrator.getInstitution(); //Extra
        l_intBranchCount = l_strBranchCode.length;
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        l_accManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        //1.6 ArrayList
        l_arrList = new ArrayList();

        for (int i = 0; i < l_intBranchCount; i++)
        {
            // 1.7.1 getBranch()
            //used l_institution instead of l_strInstitutionCode
            l_branch = l_accManager.getBranch(l_institution, l_strBranchCode[i]);
            // 1.7.2 Call WEB3AdminTMLoginPermissionStatusUnit()
            l_loginStatusUnit = new WEB3AdminTMLoginPermissionStatusUnit();
            //1.7.3 PropertySet()
            l_loginStatusUnit.branchCode = l_branch.getBranchCode();
            l_loginStatusUnit.branchName = l_branch.getBranchName();
            l_loginStatusUnit.loginPermissionDiv =
                ((BranchRow) l_branch.getDataSourceObject()).getLoginStopDiv();
            l_loginStatusUnit.afterLoginPermissionDiv = null;
            // 1.7.4 add(Object)
            l_arrList.add(l_loginStatusUnit);
        }


        // 1.8 toArray()
        l_arrLoginStatusUnit = (WEB3AdminTMLoginPermissionStatusUnit[]) l_arrList.toArray(new WEB3AdminTMLoginPermissionStatusUnit[0]);
        
        // Call createResponse()
        l_response = (WEB3AdminTMLStopStartChgInputResponse) l_request.createResponse();
        // 1.9 PropertySet()
        l_currentDate = (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
                                        WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        l_response.currentDate = l_currentDate;
        l_response.loginPermissionStatusList = l_arrLoginStatusUnit;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate変更)<BR>
     * <BR>
     * ログイン停止再開変更確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者ログイン停止再開変更サービス)validate変更」<BR>
     * 参照<BR>
     * <BR>
     * ----<English>---------------<BR>
     * <BR>
     * validateChange<BR>
     * <BR>
     * Execute WEB3AdminTMLoginStopStartChangeService validate process.<BR>
     * <BR>
     * Refer to the sequence diagram"(administrator login stop start change service)
     * validateChange"<BR>
     * <BR>
     * @@param l_request - 管理者・ログイン停止再開変更確認リクエストオブジェクト<BR>
     * <BR>
     * WEB3AdminTMLStopStartChgConfirmRequest object<BR>
     * <BR>
     * @@return webbroker3.trademnagement.message.WEB3AdminTMLStopStartChgConfirmResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws NotFoundException NotFoundException
     * @@roseuid 417745990247
     */
    protected WEB3AdminTMLStopStartChgConfirmResponse validateChange(
        WEB3AdminTMLStopStartChgConfirmRequest l_request)
        throws WEB3BaseException, NotFoundException
    {
        final String STR_METHOD_NAME = "validateChange(WEB3AdminTMLStopStartChgConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3Administrator l_administrator = null;
        WEB3GentradeAccountManager l_accManager = null;
        WEB3AdminTMLoginPermissionStatusUnit[] l_arrLoginStatusUnit = null;
        WEB3AdminTMLStopStartChgConfirmResponse l_response = null;
        Institution l_institution = null;
        Branch l_branch = null;
        String l_strTransactionCategory = null;
        Timestamp l_currentDate = null;
        int l_intBranchCount = 0;
        boolean l_isUpdate = false;
        // 1.1 Call validate()
        l_request.validate();
        // 1.2 Call getInstanceFromLoginInfo()
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        l_strTransactionCategory = WEB3TransactionCategoryDef.TRADE_MANAGEMENT_LOGIN;
        l_isUpdate = true;
        //1.3 Call validateAuthority()
        l_administrator.validateAuthority(l_strTransactionCategory, l_isUpdate);
        //1.4 Call getInstitution()
        l_institution = l_administrator.getInstitution(); //Extra
        l_arrLoginStatusUnit = l_request.loginPermissionStatusList;
        l_intBranchCount = l_arrLoginStatusUnit.length;
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        l_accManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        //1.5 Loop processing for as many times as l_request. loginPermissionStatusList
        for (int i = 0; i < l_intBranchCount; i++)
        {
            // 1.5.1 Call getBranch()
            //used l_institution instead of l_strInstitutionCode
            l_branch = l_accManager.getBranch(l_institution, l_arrLoginStatusUnit[i].branchCode);
            // 1.5.2 The latest DB data set
            l_arrLoginStatusUnit[i].loginPermissionDiv =
                ((BranchRow) l_branch.getDataSourceObject()).getLoginStopDiv();
        }
        // 1.6 CreateResponse()
        l_response = (WEB3AdminTMLStopStartChgConfirmResponse) l_request.createResponse();
        // 1.7 (*)Property Set
        l_currentDate = (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
                                        WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);

        l_response.currentDate = l_currentDate;
        l_response.loginPermissionStatusList = l_arrLoginStatusUnit;

        log.exiting(STR_METHOD_NAME);
        //1.8 return
        return l_response;
    }

    /**
     * (submit変更)<BR>
     * ログイン停止再開変更完了処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者ログイン停止再開変更サービス)submit変更」<BR>
     * 参照<BR>
     * <BR>
     * -----<English>---------------------<BR>
     * <BR>
     * submitChange<BR>
     * <BR>
     * Execute WEB3AdminTMLoginStopStartChangeService submit process.<BR>
     * <BR>
     * Refer to the sequence diagram "(administrator login stop start changeService)
     * submitChange".<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * <BR>
     * 管理者・ログイン停止再開変更完了リクエストオブジェクト<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminTMLStopStartChgCompleteRequest object<BR>
     * <BR>
     * @@return webbroker3.trademnagement.message.WEB3AdminTMLStopStartChgCompleteResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws NotFoundException NotFoundException
     * @@throws DataQueryException DataQueryException
     * @@throws DataNetworkException DataNetworkException
     * @@roseuid 417745990266
     */
    protected WEB3AdminTMLStopStartChgCompleteResponse submitChange(
        WEB3AdminTMLStopStartChgCompleteRequest l_request)
        throws
            WEB3BaseException,
            NotFoundException,
            DataQueryException,
            DataNetworkException
    {
        final String STR_METHOD_NAME = "submitChange(WEB3AdminTMLStopStartChgCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3Administrator l_administrator = null;
        WEB3GentradeAccountManager l_accManager = null;
        WEB3AdminTMLoginPermissionStatusUnit[] l_arrLoginStatusUnit = null;
        WEB3AdminTMLStopStartChgCompleteResponse l_response = null;
        WEB3GentradeBranch l_gentradeBranch = null;
        Institution l_institution = null;
        Branch l_branch = null;
        BranchRow l_branchRow = null;
        BranchParams l_branchParams = null;
        String l_strTransactionCategory = null;
        Timestamp l_currentDate = null;
        int l_intBranchCount = 0;
        boolean l_isUpdate = false;
        //1.1 Call validate()
        l_request.validate();
        // 1.2 Call getInstanceFromLoginInfo()
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        l_strTransactionCategory = WEB3TransactionCategoryDef.TRADE_MANAGEMENT_LOGIN;
        l_isUpdate = true;
        //1.3 Call validateAuthority()
        l_administrator.validateAuthority(l_strTransactionCategory, l_isUpdate);
        //1.4 Call validateTradingPassword()
        l_administrator.validateTradingPassword(l_request.password);
        //1.5 Call getInstitution()
        l_institution = l_administrator.getInstitution(); //Extra
        l_arrLoginStatusUnit = l_request.loginPermissionStatusList;
        l_intBranchCount = l_arrLoginStatusUnit.length;
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        l_accManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        l_currentDate =
            (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        // 1.6 Loop processing for as many times as l_request.loginPermissionStatusList
        for (int i = 0; i < l_intBranchCount; i++)
        {
            // 1.6.1 Call getBranch()
            //used l_institution instead of l_strInstitutionCode
            l_branch = l_accManager.getBranch(l_institution, l_arrLoginStatusUnit[i].branchCode);
            //1.6.2 Property Set
            l_arrLoginStatusUnit[i].loginPermissionDiv =
                ((BranchRow) l_branch.getDataSourceObject()).getLoginStopDiv();
            //1.6.3 Divergence flow
            if (!((l_arrLoginStatusUnit[i].loginPermissionDiv)
                .equals(l_arrLoginStatusUnit[i].afterLoginPermissionDiv)))
            {
                l_gentradeBranch =
                    new WEB3GentradeBranch(l_institution, l_arrLoginStatusUnit[i].branchCode);
                //1.6.3.1 Call getDataSourceObject()
                l_branchRow = (BranchRow) l_gentradeBranch.getDataSourceObject();
                //1.6.3.2 部店Paramsを作成する。
                l_branchParams = new BranchParams(l_branchRow);
                //1.6.3.3 Set afterLoginPermissionDiv
                l_branchParams.setLoginStopDiv(l_arrLoginStatusUnit[i].afterLoginPermissionDiv);
                l_branchParams.setLastUpdater(l_administrator.getAdministratorCode());
                l_branchParams.setLastUpdatedTimestamp(l_currentDate);
                //1.6.3.4 Call updateBranch()
                this.updateBranch(l_branchParams);
            }
        }
        //1.7 Call createResponse()
        l_response = (WEB3AdminTMLStopStartChgCompleteResponse) l_request.createResponse();
        //1.8 Property Set
        l_response.currentDate = l_currentDate;
        l_response.loginPermissionStatusList = l_arrLoginStatusUnit;

        log.exiting(STR_METHOD_NAME);
        //1.9 return
        return l_response;
    }

    /**
     * (update部店)<BR>
     * <BR>
     * 引数の部店Paramsで部店テーブルを更新する。<BR>
     * <BR>
     * １）QueryProcessor.doUpdateQuery()メソッドを<BR>
     * 　@コールする。<BR>
     * <BR>
     * 　@[doUpdateQuery()にセットするパラメータ]<BR>
     * 　@　@arg0：　@パラメータ.部店Params<BR>
     * <BR>
     * ----<English>----------------<BR>
     * <BR>
     * Update the branch table with the argument,  branchParams.<BR>
     * <BR>
     * １）Call the QueryProcessor.doUpdateQuery() method.<BR>
     * <BR>
     * 　@[Parameter set in doUpdateQuery()]<BR>
     * 　@　@arg0：　@Parameter.branchParams<BR>
     * <BR>
     * <BR>
     * @@param l_branchParams - (部店Params)<BR>
     * <BR>
     * l_branchParams<BR>
     * <BR>
     * @@throws DataNetworkException DataNetworkException
     * @@throws DataQueryException DataQueryException
     * @@roseuid 41947A87014A
     */
    protected void updateBranch(BranchParams l_branchParams)
        throws DataNetworkException, DataQueryException
    {
        final String STR_METHOD_NAME = "updateBranch(BranchParams)";
        log.entering(STR_METHOD_NAME);

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        l_queryProcessor.doUpdateQuery(l_branchParams);

        log.exiting(STR_METHOD_NAME);
    }
}
@
