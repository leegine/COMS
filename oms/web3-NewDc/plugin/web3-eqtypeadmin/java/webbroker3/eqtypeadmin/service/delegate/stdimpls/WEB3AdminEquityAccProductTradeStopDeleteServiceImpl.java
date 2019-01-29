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
filename	WEB3AdminEquityAccProductTradeStopDeleteServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者顧客銘柄別取引停止削除サービス実装クラス」Impl 
                        (WEB3AdminEquityAccProductTradeStopDeleteServiceImpl.java)
Author Name      : Daiwa Institute of Research
*/

package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AccountProductOrderStopParams;

import webbroker3.eqtypeadmin.WEB3AdminPMEquityDataManager;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopDeleteCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopDeleteCompleteResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopDeleteConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopDeleteConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopInfoUnit;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAccProductTradeStopDeleteService;

import webbroker3.equity.WEB3EquityProductManager;

/**
 * （管理者顧客銘柄別取引停止削除サービス実装クラス」）<BR>
 * <BR>
 * 管理者顧客銘柄別取引停止削除サービス実装クラス<BR>
 * <BR>
 ** WEB3AdminEquityAccProductTradeStopDeleteServiceImpl class<BR>
 * <BR>
 * WEB3AdminEquityAccProductTradeStopDeleteServiceImpl<BR>
 * <BR>
 * WEB3AdminEquityAccProductTradeStopDeleteServiceImpl class<BR>
 * @@author Shruti and Rajesh Sharma
 * @@version 1.0
 */
public class WEB3AdminEquityAccProductTradeStopDeleteServiceImpl
    extends WEB3ClientRequestService
    implements WEB3AdminEquityAccProductTradeStopDeleteService
{
    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityAccProductTradeStopDeleteServiceImpl.class);

    /**
     * @@roseuid 41FD94C4000F
     */
    public WEB3AdminEquityAccProductTradeStopDeleteServiceImpl()
    {

    }

    /**
     * 顧客銘柄別取引停止削除処理を行う。<BR>
     * <BR>
     * ○管理者・顧客銘柄別取引停止削除確認リクエストの場合<BR>
     * 　@this.validate削除()をコールする。<BR>
     * ○管理者・顧客銘柄別取引停止削除完了リクエストの場合<BR>
     * 　@this.submit削除()をコールする。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Execute WEB3AdminEquityAccProductTradeStopDeleteService process<BR>
     * <BR>
     * ○If WEB3AdminPMAccProductTradeStopDeleteConfirmRequest<BR>
     * 　@Call this.validateDelete()<BR>
     * <BR>
     * ○If WEB3AdminPMAccProductTradeStopDeleteCompleteRequest<BR>
     * 　@Call this.submitDelete()<BR>
     * <BR>
     * @@param l_request - （リクエスト）<BR>
     * <BR>
     * リクエスト<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 419985F600C2
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
                     "WEB3AdminEquityAccProductTradeStopDeleteServiceImpl.execute()";
        log.entering(STR_METHOD_NAME);
        WEB3GenResponse l_response = null;

        try
        {
            if (l_request instanceof WEB3AdminPMAccProductTradeStopDeleteConfirmRequest)
            {
                l_response = this.validateDelete
                    ((WEB3AdminPMAccProductTradeStopDeleteConfirmRequest ) l_request);
            } else if (l_request instanceof WEB3AdminPMAccProductTradeStopDeleteCompleteRequest)
            {
                l_response = this.submitDelete((WEB3AdminPMAccProductTradeStopDeleteCompleteRequest)
                                              l_request);
            } else
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "INPUT リクエスト NOT 顧客銘柄別取引停止削除リクエスト");
            }
        } catch (DataQueryException l_exce)
        {
            log.debug(l_exce.getMessage());
            String l_strMsg = "Error while aquiring the Data ";
            log.error(l_strMsg, l_exce);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_exce.toString(),
                l_exce);
        } catch (DataNetworkException l_exce)
        {
            log.debug(l_exce.getMessage());
            String l_strMsg = "Error while aquiring the Data ";
            log.error(l_strMsg, l_exce);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_exce.toString(),
                l_exce);
        } catch (NotFoundException l_exce)
        {
            log.debug(l_exce.getMessage());
            String l_strMsg = "Error while aquiring the Data ";
            log.error(l_strMsg, l_exce);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_exce.toString(),
                l_exce);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * （validate削除）<BR>
     * <BR>
     * 顧客銘柄別取引停止削除確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者顧客銘柄別取引停止削除サービス)validate削除」
     * 参照<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * (validateDelete)<BR>
     * <BR>
     * Execute WEB3AdminPMAccProductTradeStopDeleteService validate process<BR>
     * <BR>
     * Refer to the sequence diagram "(administrator; account product trade stop delete
     * service) validateDelete".<BR>
     * <BR>
     * @@param l_request - （リクエストデータ）<BR>
     * <BR>
     * 管理者・顧客銘柄別取引停止削除確認レスポンスオブジェクト<BR>
     * <BR>
     * --------------<English>--------<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminPMAccProductTradeStopDeleteConfirmResponse object<BR>
     * <BR>
     * @@return eqtypeadmin.message.WEB3AdminPMAccProductTradeStopDeleteConfirmResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws NotFoundException NotFoundException
     * @@throws DataFindException DataFindException
     * @@throws DataNetworkException DataNetworkException
     * @@throws DataQueryException DataQueryException
     * @@roseuid 419986180333
     */
    protected WEB3AdminPMAccProductTradeStopDeleteConfirmResponse validateDelete
        (WEB3AdminPMAccProductTradeStopDeleteConfirmRequest l_request)
        throws WEB3BaseException, DataFindException, DataNetworkException, DataQueryException,
            NotFoundException
    {
        final String STR_METHOD_NAME =
            "validateDelete(WEB3AdminPMAccProductTradeStopDeleteConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminPMAccProductTradeStopDeleteConfirmResponse l_response = null;
        WEB3Administrator l_web3Administrator = null;
        WEB3GentradeAccountManager l_web3GentradeAccManager = null;
        WEB3EquityProductManager l_equityProductManager = null;
        AccountProductOrderStopParams l_accountPrdctOrderStopParams = null;
        WEB3AdminPMAccProductTradeStopInfoUnit l_web3AdminPMAccProductTradeStopInfoUnit = null;
        WEB3AdminPMEquityDataManager l_web3AdminPMEquityDataManager =
            new WEB3AdminPMEquityDataManager();
        String l_strBranchCode = null;
        String l_strInstitutionCode = null;
        String l_strProductCode = null;
        String[] l_strBranchCodes = new String[1];
        String l_strAccountCode = null;
        Institution l_institution = null;
        Branch l_branch = null;
        MainAccount l_account = null;
        EqTypeProduct l_eqtypeProduct = null;
        long l_lngBranchId = 0;
        long l_lngAccountId = 0;
        long l_lngProductId = 0;
        Date l_datExpirationStartDate = null;

        // Step 1.1, The l_request object is validated
        l_request.validate();

        // Step  1.2, Aquires administator instance from login infomation
        l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // Step1.3, Checks transactionCategory i.e validates it.
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.EQTYPE_ACCOUNT_PRODUCT_STOP,
            true);

        //Step 1.4, Checks  validateBranchPermission i.e validates it.
        l_strBranchCode = l_request.branchCode;
        l_strBranchCodes[0] = l_strBranchCode;
        l_web3Administrator.validateBranchPermission(l_strBranchCodes);

        // Step 1.5, Aquires institutionCode.
        l_strInstitutionCode = l_web3Administrator.getInstitutionCode();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        l_web3GentradeAccManager = (WEB3GentradeAccountManager) l_finApp.getAccountManager();

        l_institution = l_web3GentradeAccManager.getInstitution(l_strInstitutionCode);

        // Step 1.6,Acquire l_branch
        try
        {
            l_branch = l_web3GentradeAccManager.getBranch(l_institution, l_strBranchCode);
        } catch (Exception l_ex)
        {
            String l_strMsg = "No data for the branch";
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01386,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strMsg);
        }

        // Step 1.7,Acquire l_mainAccount
        l_strAccountCode = l_request.accountCode;
        try
        {
            l_account =
                l_web3GentradeAccManager.getMainAccount(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
        } catch (Exception l_ex)
        {
            String l_strMsg = "No data for the main account";
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01387,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strMsg);
        }

        // Step 1.8,  if product code is input
        l_strProductCode = l_request.productCode;

        // Checks, if the productCode is not null,then execute Steps 1.8.1 and 1.8.2
        if (l_strProductCode != null)
        {
            /* Step 1.8.1, Acquire l_institution from GentradeAccManager
            l_institution Code is acquired in step 1.5 above
            l_institution = l_web3GentradeAccManager.getInstitution(l_strInstitutionCode);*/

            // Step 1.8.2, Acquires the productCode from WEB3EquityProductManager
            try
            {
                TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                l_equityProductManager =
                (WEB3EquityProductManager) l_tradingModule.getProductManager();
                l_eqtypeProduct =
                l_equityProductManager.getProduct(l_institution, l_strProductCode);
            } catch (Exception l_ex)
            {
                String l_strMsg = "No data for the product";
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00391,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_strMsg);
            }
        }

        // Step 1.9, Acquire accountProductTradeStopParams,
        l_lngBranchId = l_branch.getBranchId();
        l_lngAccountId = l_account.getAccountId();

        // Checks if product code is null, then set ProductId = 0,else Other cases
        if (l_request.productCode == null)
        {
            l_lngProductId = 0L;
        } else
        {
            // Other cases
            l_lngProductId = l_eqtypeProduct.getProductId();
        }
            l_datExpirationStartDate =
            WEB3DateUtility.getDate(l_request.expirationStartDate, "yyyyMMdd");
            l_accountPrdctOrderStopParams =
            l_web3GentradeAccManager.getAccountProductOrderStop(
                l_strInstitutionCode, l_lngBranchId, l_lngAccountId, l_lngProductId,
                l_datExpirationStartDate);

        if (l_accountPrdctOrderStopParams == null)
        {
            String l_strMsg = "No account product trade stop data";
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01977,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strMsg);
        }

        // Step 1.10, Creates data of account_product_order_stop.
        l_web3AdminPMAccProductTradeStopInfoUnit =
            l_web3AdminPMEquityDataManager.createAccProductTradeStopInfoUnit
                (l_accountPrdctOrderStopParams);

        // Step 1.11, Create the response data
        l_response =
            (WEB3AdminPMAccProductTradeStopDeleteConfirmResponse) l_request.createResponse();

        //Step 1.12. Set the Property to the reponse object
        l_response.accProductTradeStopInfo = l_web3AdminPMAccProductTradeStopInfoUnit;

        // Step 1.13, return the l_response
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * （submit削除）<BR>
     * <BR>
     * 顧客銘柄別取引停止削除完了処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者顧客銘柄別取引停止削除サービス)submit削除」<BR>
     * 参照<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * (submitDelete)<BR>
     * <BR>
     * Execute WEB3AdminPMAccProductTradeStopDeleteService submit process<BR>
     * <BR>
     * Refer to the sequence diagram "(administrator; account product trade stop delete
     * service) submitDelete". <BR>
     * @@param l_request - （リクエストデータ）<BR>
     * <BR>
     * 管理者・顧客銘柄別取引停止削除完了レスポンスオブジェクト<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminPMAccProductTradeStopDeleteCompleteResponse object<BR>
     * <BR>
     * @@return eqtypeadmin.message.WEB3AdminPMAccProductTradeStopDeleteCompleteResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws NotFoundException NotFoundException
     * @@roseuid 419986180352
     */
    protected WEB3AdminPMAccProductTradeStopDeleteCompleteResponse
        submitDelete(WEB3AdminPMAccProductTradeStopDeleteCompleteRequest l_request)
        throws WEB3BaseException, NotFoundException
    {
        final String STR_METHOD_NAME =
            "submitDelete(WEB3AdminPMAccProductTradeStopDeleteCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminPMAccProductTradeStopDeleteCompleteResponse l_response = null;
        WEB3Administrator l_web3Administrator = null;
        WEB3GentradeAccountManager l_web3GentradeAccManager = null;
        WEB3EquityProductManager l_equityProductManager = null;
        AccountProductOrderStopParams l_accountProductTradeStopParams = null;

        Institution l_institution = null;
        Branch l_branch = null;
        MainAccount l_account = null;
        EqTypeProduct l_eqtypeProduct = null;
        String l_strPassword = null;
        String l_strInstitutionCode = null;
        String l_strBranchCode = null;
        String l_strAccountCode = null;
        String l_strProductCode = null;
        Date l_datExpirationStartDate;
        long l_lngBranchId = 0L;
        long l_lngAccountId = 0L;
        long l_lngProductId = 0L;

        // Step 1.1, The l_request object is validated
        l_request.validate();

        // Step  1.2, Aquires administator instance from login infomation
        l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // Step1.3, Checks transactionCategory i.e validates it.
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.EQTYPE_ACCOUNT_PRODUCT_STOP,
            true);

        //Step 1.4 Check password
        l_strPassword = l_request.password;
        l_web3Administrator.validateTradingPassword(l_strPassword);

        //Step 1.5 Acquire institutionCode
        l_strInstitutionCode = l_web3Administrator.getInstitutionCode();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        l_web3GentradeAccManager = (WEB3GentradeAccountManager) l_finApp.getAccountManager();

        l_institution = l_web3GentradeAccManager.getInstitution(l_strInstitutionCode);

        // Step 1.6,Acquire l_branch
        try
        {
			l_strBranchCode = l_request.accProductTradeStopInfo.branchCode;
            l_branch = l_web3GentradeAccManager.getBranch(l_institution, l_strBranchCode);
        } catch (NotFoundException l_ex)
        {
            String l_strMsg = "No data for the branch";
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01386,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strMsg);
        }
        // Step 1.7,Acquire l_mainAccount
        l_strAccountCode = l_request.accProductTradeStopInfo.accountCode;

        try
        {
            l_account =
                l_web3GentradeAccManager.getMainAccount(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
        } catch (Exception l_ex)
        {
            String l_strMsg = "No data for the main account";
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01387,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strMsg);
        }

        // Step 1.8,  if product code is input
        l_strProductCode = l_request.accProductTradeStopInfo.productCode;

        // Checks, if the productCode is not null,then execute Steps 1.8.1 and 1.8.2
        if (l_strProductCode != null)
        {
            /* Step 1.8.1, Acquire l_institution from GentradeAccManager
            l_institution Code is acquired in step 1.5 above.
            l_institution = l_web3GentradeAccManager.getInstitution(l_strInstitutionCode);*/

            // Step 1.8.2, Acquire l_equityProduct
            try
            {
                TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                l_equityProductManager =
                    (WEB3EquityProductManager) l_tradingModule.getProductManager();
                l_eqtypeProduct =
                l_equityProductManager.getProduct(l_institution, l_strProductCode);
            } catch (Exception l_ex)
            {
                String l_strMsg = "No data for the product";
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00391,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_strMsg);
            }
        }

        // Step 1.9, Acquire accountProductTradeStopParams,
        l_lngBranchId = l_branch.getBranchId();
        l_lngAccountId = l_account.getAccountId();

        // Checks if product code is null, then set ProductId = 0,else Other cases
        if (l_strProductCode == null)
        {
            l_lngProductId = 0;
        } else
        {
            // Other cases
            l_lngProductId = l_eqtypeProduct.getProductId();
        }

           l_datExpirationStartDate =
            WEB3DateUtility.getDate(l_request.accProductTradeStopInfo.expirationStartDate,
                                 "yyyyMMdd");
           l_accountProductTradeStopParams =
                l_web3GentradeAccManager.getAccountProductOrderStop(
                    l_strInstitutionCode,
                    l_lngBranchId,
                    l_lngAccountId,
                    l_lngProductId,
                    l_datExpirationStartDate);

        if (l_accountProductTradeStopParams == null)
        {
            String l_strMsg = "No account product trade stop data";
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01977,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strMsg);
        }

        // Step 1.10 Delete data of account_product_order_stop.
        l_web3GentradeAccManager.deleteAccountProductOrderStop(l_accountProductTradeStopParams);

        // Step 1.11, Create the response data
        l_response =
          (WEB3AdminPMAccProductTradeStopDeleteCompleteResponse) l_request.createResponse();

        //Step 1.12 Add data to the response
        l_response.currentDate = (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
        WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);

        //Step 1.13
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
