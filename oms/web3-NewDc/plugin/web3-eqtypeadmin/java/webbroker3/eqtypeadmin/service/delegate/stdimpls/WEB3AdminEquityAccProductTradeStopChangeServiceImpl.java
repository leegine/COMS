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
filename	WEB3AdminEquityAccProductTradeStopChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者顧客銘柄別取引停止変更サービス実装クラスImpl 
                        (WEB3AdminEquityAccProductTradeStopChangeServiceImpl)
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
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
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopInfoUnit;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopModifyCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopModifyCompleteResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopModifyConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopModifyConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopModifyInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopModifyInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccTradeStopInfoUnit;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAccProductTradeStopChangeService;

import webbroker3.equity.WEB3EquityProductManager;

/**
 * （管理者顧客銘柄別取引停止変更サービス実装クラス）<BR>
 * <BR>
 * 管理者顧客銘柄別取引停止変更サービス実装クラス<BR>
 * <BR>
 * WEB3AdminEquityAccProductTradeStopChangeServiceImpl class<BR>
 * <BR>
 * WEB3AdminEquityAccProductTradeStopChangeServiceImpl<BR>
 * <BR>
 * WEB3AdminEquityAccProductTradeStopChangeServiceImpl class<BR>
 * @@author Shruti
 * @@version 1.0
 */
public class WEB3AdminEquityAccProductTradeStopChangeServiceImpl
    extends WEB3ClientRequestService
    implements WEB3AdminEquityAccProductTradeStopChangeService
{
    /**
      * Log Variable.<BR>
      */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityAccProductTradeStopChangeServiceImpl.class);

    /**
     * @@roseuid 41FD96640290
     */
    public WEB3AdminEquityAccProductTradeStopChangeServiceImpl()
    {

    }

    /**
     * 顧客銘柄別取引停止変更処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、<BR>
     * 以下のメソッドを呼び分ける。<BR>
     * <BR>
     * ○管理者・顧客銘柄別取引停止変更入力リクエストの場合<BR>
     * 　@this.get入力画面()をコールする。<BR>
     * ○管理者・顧客銘柄別取引停止変更確認リクエストの場合<BR>
     * 　@this.validate変更()をコールする。<BR>
     * ○管理者・顧客銘柄別取引停止変更完了リクエストの場合<BR>
     * 　@this.submit変更()をコールする。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Execute WEB3AdminEquityAccProductTradeStopChangeService<BR>
     * <BR>
     * Call one of the following methods based on the type of l_request<BR>
     * <BR>
     * ○If WEB3AdminPMAccProductTradeStopModifyInputRequest<BR>
     * 　@Call this.getInputScreen<BR>
     * ○If WEB3AdminPMAccProductTradeStopModifyConfirmRequest　@<BR>
     * 　@Call this.validateChange<BR>
     * ○If  WEB3AdminPMAccProductTradeStopModifyCompleteRequest<BR>
     * 　@Call this.submitChange<BR>
     * <BR>
     * @@param l_request - （リクエスト）<BR>
     * <BR>
     * リクエスト<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 419969DE0147
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        try
        {
            if (l_request instanceof WEB3AdminPMAccProductTradeStopModifyInputRequest)
            {
                l_response =
                    this.getInputScreen(
                        (WEB3AdminPMAccProductTradeStopModifyInputRequest) l_request);

            } else if (l_request instanceof WEB3AdminPMAccProductTradeStopModifyConfirmRequest)
            {
                l_response =
                    this.validateChange(
                        (WEB3AdminPMAccProductTradeStopModifyConfirmRequest) l_request);

            } else if (l_request instanceof WEB3AdminPMAccProductTradeStopModifyCompleteRequest)
            {
                l_response =
                    this.submitChange(
                        (WEB3AdminPMAccProductTradeStopModifyCompleteRequest) l_request);

            } else
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "INPUT リクエスト NOT 管理者顧客銘柄別取引停止変更サービスリクエスト");
            }
        } catch (NotFoundException l_nfe)
        {
            log.debug(l_nfe.getMessage());
            String l_strMsg = "Error while aquiring the Data ";
            log.error(l_strMsg, l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.toString(),
                l_nfe);
        } catch (DataNetworkException l_dne)
        {
            log.debug(l_dne.getMessage());
            String l_strMsg = "Error while defining the Data ";
            log.error(l_strMsg, l_dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dne.toString(),
                l_dne);
        } catch (DataQueryException l_qe)
        {
            log.debug(l_qe.getMessage());
            String l_strMsg = "Error while defining the Data ";
            log.error(l_strMsg, l_qe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_qe.toString(),
                l_qe);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * （get入力画面）<BR>
     * 顧客銘柄別取引停止変更入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者顧客銘柄別取引停止変更サービス)get入力画面」<BR>
     * 参照<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * (getInputScreen)<BR>
     * <BR>
     * Execute WEB3AdminEquityAccProductTradeStopChangeService input screen process<BR>
     * <BR>
     * Refer to the sequence diagram "(administrator; account product trade stop change
     * service)getInputScreen". <BR>
     * <BR>
     * @@param l_request - （リクエストデータ）<BR>
     * <BR>
     * 管理者・顧客銘柄別取引停止変更入力リクエストオブジェクト<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminPMAccProductTradeStopModifyInputRequest object<BR>
     * <BR>
     * @@return eqtypeadmin.message.WEB3AdminPMAccProductTradeStopModifyInputResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws NotFoundException NotFoundException
     * @@throws DataFindException DataFindException
     * @@throws DataNetworkException DataNetworkException
     * @@throws DataQueryException DataQueryException
     * @@roseuid 419969D3037A
     */
    protected WEB3AdminPMAccProductTradeStopModifyInputResponse
        getInputScreen(WEB3AdminPMAccProductTradeStopModifyInputRequest l_request)
        throws
            WEB3BaseException,
            NotFoundException,
            DataFindException,
            DataNetworkException,
            DataQueryException
    {
        final String STR_METHOD_NAME =
            "getInputScreen(WEB3AdminPMAccProductTradeStopModifyInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminPMAccProductTradeStopModifyInputResponse l_response = null;
        WEB3GentradeAccountManager l_web3GentradeAccManager = null;
        WEB3Administrator l_web3Administrator = null;
        WEB3EquityProductManager l_equityProductManager = null;
        AccountProductOrderStopParams l_accountPrdctOrderStopParams = null;
        WEB3AdminPMEquityDataManager l_web3AdminPMEquityDataManager =
            new WEB3AdminPMEquityDataManager();
        WEB3AdminPMAccProductTradeStopInfoUnit l_web3AdminPMAccProductTradeStopInfoUnit = null;
        String[] l_strBranchCodes = new String[1];
        String l_strBranchCode = null;
        Institution l_institution = null;
        String l_strProductCode = null;
        String l_strInstitutionCode = null;
        String l_strAccountCode = null;
        Date l_datExpirationStartDate = null;
        Branch l_branch = null;
        MainAccount l_account = null;
        EqTypeProduct l_eqtypeProduct = null;
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

        // Step 1.4, Checks  validateBranchPermission i.e validates it.
        l_strBranchCode = l_request.branchCode;
        l_strBranchCodes[0] = l_strBranchCode;
        l_web3Administrator.validateBranchPermission(l_strBranchCodes);
        //}

        // Step 1.5, Aquires institutionCode.
        l_strInstitutionCode = l_web3Administrator.getInstitutionCode();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        l_web3GentradeAccManager = (WEB3GentradeAccountManager) l_finApp.getAccountManager();

        // Step 1.6,Acquire l_branch
        try
        {
            l_branch = l_web3GentradeAccManager.getWeb3GenBranch(l_strInstitutionCode, l_strBranchCode);
        } 
        catch (NotFoundException nf_ex)
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
        } 
        catch (WEB3SystemLayerException web3sl_ex)
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
            // Step 1.8.1, Acquire the institution code from WEB3GentradeAccManager
            l_institution = l_web3GentradeAccManager.getInstitution(l_strInstitutionCode);

            // Step 1.8.2, Acquires the productCode from WEB3EquityProductManager
            try
            {
                TradingModule l_tradingModule =
                 l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                 l_equityProductManager =
                 (WEB3EquityProductManager) l_tradingModule.getProductManager();
                l_eqtypeProduct = l_equityProductManager.getProduct(l_institution, l_strProductCode);
                l_lngProductId = l_eqtypeProduct.getProductId();
            } 
            catch (NotFoundException nf_ex)
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
        l_datExpirationStartDate = WEB3DateUtility.getDate(l_request.expirationStartDate, "yyyyMMdd");
        l_accountPrdctOrderStopParams =
            l_web3GentradeAccManager.getAccountProductOrderStop(
                l_strInstitutionCode,
                l_lngBranchId,
                l_lngAccountId,
                l_lngProductId,
                l_datExpirationStartDate);

        if (l_accountPrdctOrderStopParams == null)
        {
            String l_strMsg = "No account product trade stop data";
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01977,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strMsg);
        }

        // Step 1.10, calls the createAccProductTradeStopInfoUnit
        l_web3AdminPMAccProductTradeStopInfoUnit = new WEB3AdminPMAccProductTradeStopInfoUnit();

        l_web3AdminPMAccProductTradeStopInfoUnit =
            l_web3AdminPMEquityDataManager.createAccProductTradeStopInfoUnit(
                l_accountPrdctOrderStopParams);

        // Step 1.11, Create the response data
        l_response = (WEB3AdminPMAccProductTradeStopModifyInputResponse) l_request.createResponse();

        //Step 1.12. Set the Property to the reponse object
        l_response.accProductTradeStopInfo = l_web3AdminPMAccProductTradeStopInfoUnit;

        log.exiting(STR_METHOD_NAME);

        // Step 1.13, return the l_response
        return l_response;

    }

    /**
     * （validate変更）<BR>
     * 顧客銘柄別取引停止変更確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者顧客銘柄別取引停止変更サービス)validate変更」<BR>
     * 参照<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * (validateChange)<BR>
     * <BR>
     * Execute WEB3AdminEquityAccProductTradeStopChangeService validate process<BR>
     * <BR>
     * Refer to the sequence diagram "(administrator; account product trade stop change
     * service)validateChange". <BR>
     * <BR>
     * @@param l_request - （リクエストデータ）<BR>
     * <BR>
     * 管理者・顧客銘柄別取引停止変更確認リクエストオブジェクト<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminPMAccProductTradeStopModifyConfirmRequest object<BR>
     * <BR>
     * @@return eqtypeadmin.message.WEB3AdminPMAccProductTradeStopModifyConfirmResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws NotFoundException NotFoundException
     * @@roseuid 419969D30399
     */
    protected WEB3AdminPMAccProductTradeStopModifyConfirmResponse
        validateChange(WEB3AdminPMAccProductTradeStopModifyConfirmRequest l_request)
        throws WEB3BaseException, NotFoundException
    {
        final String STR_METHOD_NAME =
            "validateChange(WEB3AdminPMAccProductTradeStopModifyConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminPMAccProductTradeStopModifyConfirmResponse l_response = null;
        WEB3Administrator l_web3Administrator = null;
        WEB3GentradeAccountManager l_web3GentradeAccManager = null;
        WEB3AdminPMAccProductTradeStopInfoUnit l_web3AdminPMAccProductTradeStopInfoUnit = null;
        WEB3EquityProductManager l_equityProductManager = null;
        AccountProductOrderStopParams l_accountPrdctOrderStopParams = null;
        Institution l_institution = null;
        Branch l_branch = null;
        MainAccount l_account = null;
        EqTypeProduct l_eqtypeProduct = null;
        String l_strInstitutionCode = null;
        String l_strBranchCode = null;
        String l_strAccountCode = null;
        String l_strProductCode = null;
        Date l_datExpirationStartDate = null;
        long l_lngBranchId = 0;
        long l_lngAccountId = 0;
        long l_lngProductId = 0;

        l_web3AdminPMAccProductTradeStopInfoUnit = l_request.accProductTradeStopInfo;

        // Step 1.1, The l_request object is validated
        l_request.validate();

        // Step  1.2, Aquires administator instance from login infomation
        l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // Step1.3, Checks transactionCategory i.e validates it.
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.EQTYPE_ACCOUNT_PRODUCT_STOP,
            true);

        // Step 1.4, Aquires institutionCode.
        l_strInstitutionCode = l_web3Administrator.getInstitutionCode();

        // Step 1.5,Acquire l_branch
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        l_web3GentradeAccManager = (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        l_strBranchCode = l_web3AdminPMAccProductTradeStopInfoUnit.branchCode;
        try
        {
            l_branch = l_web3GentradeAccManager.getWeb3GenBranch(l_strInstitutionCode, l_strBranchCode);
        } 
        catch (NotFoundException l_ex)
        {
            String l_strMsg = "No data for the branch";
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01386,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strMsg);
        }

        // Step 1.6,Acquire l_mainAccount
        l_strAccountCode = l_web3AdminPMAccProductTradeStopInfoUnit.accountCode;
        try
        {
            l_account =
                l_web3GentradeAccManager.getMainAccount(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
        } 
        catch (WEB3SystemLayerException web3sl_ex)
        {
            String l_strMsg = "No data for the main account";
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01387,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strMsg);
        }

        // Step 1.7,  if product code is input
        l_strProductCode = l_web3AdminPMAccProductTradeStopInfoUnit.productCode;

        // Checks, if the productCode is not null,then execute Steps 1.7.1 and 1.7.2
        if (l_strProductCode != null)
        {
            try
            {
                
            // Step 1.7.1, Acquire the institution code from WEB3GentradeAccManager
            l_institution = l_web3GentradeAccManager.getInstitution(l_strInstitutionCode);

            // Step 1.7.2, Acquires the productCode 

                TradingModule l_tradingModule =
                 l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                 l_equityProductManager =
                 (WEB3EquityProductManager) l_tradingModule.getProductManager();
                l_eqtypeProduct =
                    l_equityProductManager.getProduct(l_institution, l_strProductCode);
                l_lngProductId = l_eqtypeProduct.getProductId();
            } 
            catch (NotFoundException l_ex)
            {
                String l_strMsg = "No data for the product";
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00391,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_strMsg);
            }
        }

        // Step 1.8 Acquire accountProductTradeStopParams
        l_lngBranchId = l_branch.getBranchId();
        l_lngAccountId = l_account.getAccountId();

        l_datExpirationStartDate = 
            WEB3DateUtility.getDate(l_web3AdminPMAccProductTradeStopInfoUnit.expirationStartDate,"yyyyMMdd");
        l_accountPrdctOrderStopParams =
            l_web3GentradeAccManager.getAccountProductOrderStop(
                l_strInstitutionCode,
                l_lngBranchId,
                l_lngAccountId,
                l_lngProductId,
                l_datExpirationStartDate);

        if (l_accountPrdctOrderStopParams == null)
        {
            String l_strMsg = "No account product trade stop data";
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01977,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strMsg);
        }

        // Step 1.9, Set the latest data adn calls the method setLatestData()
        setLatestData(l_web3AdminPMAccProductTradeStopInfoUnit, l_accountPrdctOrderStopParams);

        // Step 1.10,Create the response data
        l_response = (WEB3AdminPMAccProductTradeStopModifyConfirmResponse) l_request.createResponse();

        // Step 1.11, Set the Property to the reponse object
        l_response.accProductTradeStopInfo = l_web3AdminPMAccProductTradeStopInfoUnit;

        log.exiting(STR_METHOD_NAME);

        // Step 1.12, return the reponse object
        return l_response;
    }

    /**
     * （submit変更）<BR>
     * 顧客銘柄別取引停止変更完了処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者顧客銘柄別取引停止変更サービス)submit変更」<BR>
     * 参照<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * (submitChange)<BR>
     * <BR>
     * Execute WEB3AdminEquityAccProductTradeStopChangeService submit process<BR>
     * <BR>
     * Refer to the sequence diagram "(administrator; account product trade stop change
     * service)submitChange". <BR>
     * <BR>
     * @@param l_request - （リクエストデータ）<BR>
     * <BR>
     * 管理者・顧客銘柄別取引停止変更完了リクエストオブジェクト<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminPMAccProductTradeStopModifyCompleteRequest object<BR>
     * <BR>
     * @@return eqtypeadmin.message.WEB3AdminPMAccProductTradeStopModifyCompleteResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws NotFoundException NotFoundException
     * @@roseuid 419969D303B8
     */
    protected WEB3AdminPMAccProductTradeStopModifyCompleteResponse
        submitChange(WEB3AdminPMAccProductTradeStopModifyCompleteRequest l_request)
        throws WEB3BaseException, NotFoundException
    {

        final String STR_METHOD_NAME =
            "submitChange(WEB3AdminPMAccProductTradeStopModifyCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminPMAccProductTradeStopModifyCompleteResponse l_response = null;
        WEB3Administrator l_web3Administrator = null;
        WEB3GentradeAccountManager l_web3GentradeAccManager = null;
        WEB3AdminPMAccProductTradeStopInfoUnit l_web3AdminPMAccProductTradeStopInfoUnit = null;
        WEB3EquityProductManager l_equityProductManager = null;
        WEB3AdminPMAccTradeStopInfoUnit[] l_tradeStopInfoUnits = null;
        AccountProductOrderStopParams l_accountPrdctOrderStopParams = null;
        Institution l_institution = null;
        Branch l_branch = null;
        MainAccount l_account = null;
        EqTypeProduct l_eqtypeProduct = null;
        String l_strInstitutionCode = null;
        String l_strBranchCode = null;
        String l_strAccountCode = null;
        String l_strProductCode = null;
        Date l_datExpirationStartDate = null;
        String l_strAftTradeStopDiv = null;
        String l_strPassword = null;
        String l_strTradeStopDiv = null;
        String l_strExpirationEndDate = null;
        String l_strAftChgExpirationEndDate = null;
        String l_strReason = null;
        String l_strAftChgReason = null;
        String l_strLastUpdater = null;
        long l_lngBranchId = 0L;
        long l_lngAccountId = 0L;
        long l_lngProductId = 0L;
        int l_intTradeStopInfoLength = 0;

        l_web3AdminPMAccProductTradeStopInfoUnit = l_request.accProductTradeStopInfo;

        //Step 1.1, The l_request object is validated
        l_request.validate();

        // Step  1.2, Aquires administator instance from login infomation
        l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // Step1.3, Checks transactionCategory i.e validates it.
        l_web3Administrator.validateAuthority(WEB3TransactionCategoryDef.EQTYPE_ACCOUNT_PRODUCT_STOP,true);

        // Step 1.4,Checks the password
        l_strPassword = l_request.password;
        l_web3Administrator.validateTradingPassword(l_strPassword);

        // Step 1.5, Aquires institutionCode.
        l_strInstitutionCode = l_web3Administrator.getInstitutionCode();

        // Step 1.6,Acquire l_branch
        l_strBranchCode = l_web3AdminPMAccProductTradeStopInfoUnit.branchCode;

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        l_web3GentradeAccManager = (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        try
        {
            l_branch = l_web3GentradeAccManager.getWeb3GenBranch(l_strInstitutionCode, l_strBranchCode);
        } 
        catch (NotFoundException l_ex)
        {
            String l_strMsg = "No data for the branch";
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01386,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strMsg);
        }

        // Step 1.7,Acquire l_mainAccount
        l_strAccountCode = l_web3AdminPMAccProductTradeStopInfoUnit.accountCode;
        try
        {
            l_account =
                l_web3GentradeAccManager.getMainAccount(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);

            // Checks if l_Account is null, then throw WEB3BusinessLayerException
        } 
        catch (WEB3SystemLayerException web3sl_ex)
        {
            String l_strMsg = "No data for the main account";
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01387,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strMsg);
        }

        // Step 1.8,  if product code is input
        l_strProductCode = l_web3AdminPMAccProductTradeStopInfoUnit.productCode;

        // Checks, if the productCode is not null,then execute Steps 1.8.1 and 1.8.2
        if (l_strProductCode != null)
        {
            try
            {
            
            // Step 1.8.1, Acquire the institution code from WEB3GentradeAccManager
            l_institution = l_web3GentradeAccManager.getInstitution(l_strInstitutionCode);

            // Step 1.8.2, Acquires the productCode from WEB3EquityProductManager

                TradingModule l_tradingModule =
                 l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                 l_equityProductManager =
                 (WEB3EquityProductManager) l_tradingModule.getProductManager();
                l_eqtypeProduct =
                    l_equityProductManager.getProduct(l_institution, l_strProductCode);
                l_lngProductId = l_eqtypeProduct.getProductId();
            } 
            catch (NotFoundException l_ex)
            {
                String l_strMsg = "No data for the product";
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00391,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_strMsg);
            }
        }

        // Step 1.9, Acquire accountProductTradeStopParams
        l_lngBranchId = l_branch.getBranchId();
        l_lngAccountId = l_account.getAccountId();
        l_datExpirationStartDate =
            WEB3DateUtility.getDate(l_web3AdminPMAccProductTradeStopInfoUnit.expirationStartDate,"yyyyMMdd");
        l_accountPrdctOrderStopParams =
            l_web3GentradeAccManager.getAccountProductOrderStop(
                l_strInstitutionCode,
                l_lngBranchId,
                l_lngAccountId,
                l_lngProductId,
                l_datExpirationStartDate);

        // checks if l_accountPrdctOrderStopParams is null, then throw exception XXX17
        if (l_accountPrdctOrderStopParams == null)
        {
            String l_strMsg = "No account product trade stop data ";
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01977,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strMsg);
        }

        // Step 1.10, Set the latest data adn calls the method setLatestData()
        setLatestData(l_web3AdminPMAccProductTradeStopInfoUnit, l_accountPrdctOrderStopParams);

        // Step 1.11, Checking if the following conditions are true
        l_strExpirationEndDate = l_web3AdminPMAccProductTradeStopInfoUnit.expirationEndDate;
        l_strAftChgExpirationEndDate = l_web3AdminPMAccProductTradeStopInfoUnit.aftChangeExpirationEndDate;
        l_strReason = l_web3AdminPMAccProductTradeStopInfoUnit.reason;
        l_strAftChgReason = l_web3AdminPMAccProductTradeStopInfoUnit.aftChangeReason;

        // Checks if the following condtions are true,then execute Steps 1.11.1, 1.11.2 & 1.11.3        
        l_tradeStopInfoUnits = l_web3AdminPMAccProductTradeStopInfoUnit.accTradeStopInfoList;
        l_intTradeStopInfoLength = l_tradeStopInfoUnits.length;

        // Check for as many times as the elements in the list accTradeStopInfoList
        boolean l_blisTradeStopDiv = false;
        for (int i = 0; i < l_intTradeStopInfoLength; i++)
        {

            l_strTradeStopDiv = l_tradeStopInfoUnits[i].tradeStopDiv;
            l_strAftTradeStopDiv = l_tradeStopInfoUnits[i].aftTradeStopDiv;
                
            // Checks if l_TradeStopDiv is not equal to l_AftTradeStopDiv
            if (!(l_strTradeStopDiv.equals(l_strAftTradeStopDiv)))
            {
                l_blisTradeStopDiv = true;
                break;
            }
            
        }       
        
        boolean l_blIsReasonChanged = false;
        if (l_strReason == null && l_strAftChgReason == null)
        {
            l_blIsReasonChanged = false;
        }
        else if (l_strReason == null && l_strAftChgReason != null)
        {
            l_blIsReasonChanged = true;
        }
        else if (!(l_strReason.equals(l_strAftChgReason)))
        {
            l_blIsReasonChanged = true;
        }
               
        if ((!(l_strExpirationEndDate.equals(l_strAftChgExpirationEndDate)))
            || l_blIsReasonChanged
            || l_blisTradeStopDiv)
        {
 
            //Step 1.11.1
            l_strLastUpdater = l_web3Administrator.getAdministratorCode();

            /*
             * Step 1.11.2,Acquire accountProductTradeStopParams
             *calls createAccountProductOrderStopParams()
             */
            l_accountPrdctOrderStopParams =
                createAccountProductOrderStopParams(
                    l_web3AdminPMAccProductTradeStopInfoUnit,
                    l_accountPrdctOrderStopParams,
                    l_strLastUpdater);

            // Step 1.11.3, call Update account_product_order_stop
            l_web3GentradeAccManager.updateAccountProductOrderStop(
                l_accountPrdctOrderStopParams);

        }

        // Step 1.12, Create the response data
        l_response = (WEB3AdminPMAccProductTradeStopModifyCompleteResponse) l_request.createResponse();

        //Step 1.13, set the properties
        l_response.currentDate =
            (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        l_response.accProductTradeStopInfo = l_web3AdminPMAccProductTradeStopInfoUnit;

        log.exiting(STR_METHOD_NAME);

        // Step 1.14, returns the response object
        return l_response;
    }

    /**
     * （set最新データ）<BR>
     * <BR>
     * 引数の顧客銘柄別取引停止情報に最新DBデータを<BR>
     * セットする。<BR>
     * <BR>
     * １）パラメータ.顧客銘柄別取引停止情報に以下のプロパティをセットする。<BR>
     * 　@有効期限To = パラメータ.顧客銘柄別取引停止Params.適用終了年月日<BR>
     * 　@理由 = パラメータ.顧客銘柄別取引停止Params.取引停止理由<BR>
     * <BR>
     * ２）パラメータ.顧客銘柄別取引停止情報.顧客取引停止情報一覧の<BR>
     * 　@要素数分以下の処理を繰り返す。<BR>
     * 　@２－１）商品管理(株式)データマネージャ.get取引停止区分()を<BR>
     * 　@　@　@　@　@コールする。<BR>
     * <BR>
     * 　@　@　@　@　@[get取引停止区分()にセットするパラメータ]<BR>
     * 　@　@　@　@　@　@注文種別：　@処理対象の顧客取引停止情報.注文種別<BR>
     * 　@　@　@　@　@　@顧客銘柄別取引停止Params：　@パラメータ.顧客銘柄別取引停止Params<BR>
     * <BR>
     * 　@２－２）取引停止区分をプロパティにセットする。<BR>
     * <BR>
     * 　@　@　@　@処理対象の顧客取引停止情報.取引停止区分 = ２－１）の戻り値<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * (setLatestData)<BR>
     * <BR>
     * Set the latest DB data to the argument, l_accProductTradeStopInfo <BR>
     * <BR>
     * 1)Set the following properties to
     * parameter.WEB3AdminPMAccProductTradeStopInfoUnit<BR>
     * 　@ expirationEndDate = parameter.accountProductOrderStopParams.apply_end_dateBR>
     * 　@ reason = parameter.accountProductOrderStopParams.stop_trade_reason<BR>
     * <BR>
     * 2)Loop process for as mant times as elements of
     * parameter.WEB3AdminPMAccProductTradeStopInfoUnit.accTradeStopInfoList <BR>
     * 　@2-1)Call WEB3AdminPMEquityDataManager.getTradeStopDiv()<BR>
     * <BR>
     * 　@　@　@　@　@[parameter set in getTradeStopDiv()]<BR>
     * 　@　@　@　@　@　@l_orderType: WEB3AdminPMAccProductTradeStopInfoUnit to be
     * processed.orderType<BR>
     * 　@　@　@　@　@　@l_accountProductOrderStopParams:
     * parameter.accountProductOrderStopParams<BR>
     * <BR>
     * 　@2-2)Set tradeStopDic to the property<BR>
     * <BR>
     * 　@　@　@　@WEB3AdminPMAccProductTradeStopInfoUnit to be processed.tradeStopDiv =
     * return value of 2-1）<BR>
     * <BR>
     * @@param l_accountProductTradeStopInfo - （顧客銘柄別取引停止情報）<BR>
     * <BR>
     * 顧客銘柄別取引停止情報<BR>
     * <BR>
     * l_accountProductTradeStopInfo<BR>
     * <BR>
     * WEB3AdminPMAccProductTradeStopInfoUnit object<BR>
     * <BR>
     * @@param l_accountProductOrderStopParams - （顧客銘柄別取引停止Params）<BR>
     * <BR>
     * 顧客銘柄別取引停止Params<BR>
     * <BR>
     * l_accountProductOrderStopParams<BR>
     * <BR>
     * @@roseuid 41997B2E03DF
     */
    protected void setLatestData(
        WEB3AdminPMAccProductTradeStopInfoUnit l_accountProductTradeStopInfo,
        AccountProductOrderStopParams l_accountProductOrderStopParams)
    {
        final String STR_METHOD_NAME =
            "setLatestData("
                + "WEB3AdminPMAccProductTradeStopInfoUnit l_accountProductTradeStopInfo,"
                + "AccountProductOrderStopParams l_accountProductOrderStopParams)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminPMAccTradeStopInfoUnit[] l_tradeStopinfos = null;
        WEB3AdminPMEquityDataManager l_web3AdminPMEquityDataManager =
            new WEB3AdminPMEquityDataManager();
        OrderTypeEnum l_orderTypeEnum = null;
        String l_strOrderType = null;
        String l_strTradeStopDiv = null;
        int l_intTradeStopLength = 0;

        // Step 1
        l_accountProductTradeStopInfo.expirationEndDate = 
            WEB3DateUtility.formatDate( l_accountProductOrderStopParams.apply_end_date,"yyyyMMdd");
        l_accountProductTradeStopInfo.reason = l_accountProductOrderStopParams.stop_trade_reason;

        // Step 2.1
        l_tradeStopinfos = l_accountProductTradeStopInfo.accTradeStopInfoList;
        l_intTradeStopLength = l_tradeStopinfos.length;

        for (int i = 0; i < l_intTradeStopLength; i++)
        {
            l_strOrderType = l_tradeStopinfos[i].orderType;

            if (l_strOrderType.equals(String.valueOf(OrderTypeEnum.EQUITY_BUY.intValue())))
             {
                 l_orderTypeEnum = OrderTypeEnum.EQUITY_BUY;
             } else if (l_strOrderType.equals(String.valueOf(OrderTypeEnum.EQUITY_SELL.intValue())))
             {
                 l_orderTypeEnum = OrderTypeEnum.EQUITY_SELL;
             } else if (l_strOrderType.equals(String.valueOf(OrderTypeEnum.MARGIN_LONG.intValue())))
             {
                 l_orderTypeEnum = OrderTypeEnum.MARGIN_LONG;
             } else if (l_strOrderType.equals(String.valueOf(OrderTypeEnum.MARGIN_SHORT.intValue())))
             {
                 l_orderTypeEnum = OrderTypeEnum.MARGIN_SHORT;
             } else if (l_strOrderType.equals(String.valueOf(OrderTypeEnum.CLOSE_MARGIN_LONG.intValue())))
             {
                 l_orderTypeEnum = OrderTypeEnum.CLOSE_MARGIN_LONG;
             } else if (l_strOrderType.equals(String.valueOf(OrderTypeEnum.CLOSE_MARGIN_SHORT.intValue())))
             {
                 l_orderTypeEnum = OrderTypeEnum.CLOSE_MARGIN_SHORT;
             } else if (l_strOrderType.equals(String.valueOf(OrderTypeEnum.SWAP_MARGIN_SHORT.intValue())))
             {
                 l_orderTypeEnum = OrderTypeEnum.SWAP_MARGIN_SHORT;
             } else if (l_strOrderType.equals(String.valueOf(OrderTypeEnum.SWAP_MARGIN_LONG.intValue())))
             {
                 l_orderTypeEnum = OrderTypeEnum.SWAP_MARGIN_LONG;
             } else if (l_strOrderType.equals(String.valueOf(OrderTypeEnum.MINI_STOCK_BUY.intValue())))
             {
                 l_orderTypeEnum = OrderTypeEnum.MINI_STOCK_BUY;
             } else
             {
                 l_orderTypeEnum = OrderTypeEnum.MINI_STOCK_SELL;
             }

            l_strTradeStopDiv =
                l_web3AdminPMEquityDataManager.getTradeStopDiv(
                    l_orderTypeEnum,
                    l_accountProductOrderStopParams);
            
            // Step 2.2
            l_tradeStopinfos[i].tradeStopDiv = l_strTradeStopDiv;
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （create顧客銘柄別取引停止Params）<BR>
     * <BR>
     * 引数の情報より顧客銘柄別取引停止Paramsを作成する。<BR>
     * <BR>
     * １）顧客銘柄別取引停止Paramsインスタンスを生成する。<BR>
     * <BR>
     * ２）生成したインスタンスにパラメータ.顧客銘柄別取引停止Paramsの<BR>
     * 　@内容をコピーする。<BR>
     * <BR>
     * ３）生成したインスタンスに更新情報をセットする。<BR>
     * 　@適用終了年月日 = パラメータ.顧客銘柄別取引停止情報.変更後有効期限To<BR>
     * 　@取引停止理由 = パラメータ.顧客銘柄別取引停止情報.変更後理由<BR>
     * 　@更新者コード = パラメータ.更新者コード<BR>
     * 　@更新日付 = 現在時刻(*1)<BR>
     * <BR>
     * ４）生成したインスタンスに変更後取引停止情報をセットする。<BR>
     * 　@パラメータ.顧客銘柄別取引停止情報.顧客取引停止情報一覧の<BR>
     * 　@要素(=顧客取引停止情報)数分以下の処理を繰り返す。<BR>
     * 　@３－１）商品管理データマネージャ.set取引停止区分()をコールする。<BR>
     * <BR>
     * 　@　@　@　@[set取引停止区分()にセットするパラメータ]<BR>
     * 　@　@　@　@　@注文種別：　@処理対象の顧客取引停止情報.注文種別<BR>
     * 　@　@　@　@　@顧客銘柄別取引停止Params：　@生成したインスタンス<BR>
     * 　@　@　@　@　@取引停止区分：　@処理対象の顧客取引停止情報.変更後取引停止区分<BR>
     * <BR>
     * ５）プロパティセットしたインスタンスを返却する。<BR>
     * <BR>
     * (*1)<BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute(取引時間管理.TIMESTAMP_TAG)
     * にて取得した現在時刻<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * (createAccountProductOrderStopParams)<BR>
     * <BR>
     * Create accountProductOrderStopParams from information about arguments.<BR>
     * <BR>
     * 1)Create accountProductOrderStopParams instance<BR>
     * <BR>
     * 2)Copy the content of parameter.accountProductOrderStopParams to the created
     * instance. <BR>
     * <BR>
     * 3)Set the updated information into the created instance.<BR>
     * 　@apply_end_date =
     * parameter.WEB3AdminPMAccProductTradeStopInfoUnit.expirationEndDate<BR>
     * 　@stop_trade_reason =
     * parameter.WEB3AdminPMAccProductTradeStopInfoUnit.aftChangeReason<BR>
     * 　@last_updater = parameter.lastUpdater<BR>
     * 　@last_updated_timestamp = timeStamp(*1)<BR>
     * <BR>
     * 4)Set trade stop information after change into the created instance<BR>
     * 　@Loop process for as many times as elements(=accProductTradeStopInfo) of
     * parameter.WEB3AdminPMAccProductTradeStopInfoUnit.WEB3AdminPMAccProductTradeStopI
     * nfoUnit<BR>
     * 　@3-1)Call WEB3AdminPMEquityDataManager.setTradeStopDiv()<BR>
     * <BR>
     * 　@　@　@　@[parameter set in setTradeStopDiv()]<BR>
     * 　@　@　@　@　@l_orderType: WEB3AdminPMAccProductTradeStopInfoUnit for the
     * process.orderType<BR>
     * 　@　@　@　@　@l_accountProductOrderStopParams: created instance<BR>
     * 　@　@　@　@　@l_tradeStopDiv: WEB3AdminPMAccProductTradeStopInfoUnit for the
     * process.aftTradeStopDiv<BR>
     * <BR>
     * 5)Return the instance set in 'Property Set'<BR>
     * <BR>
     * (*1)<BR>
     * timeStamp acquired at <BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManageme
     * nt.TIMESTAMP_TAG)<BR>
     * <BR>
     * @@param l_accountProductTradeStopInfo - （顧客銘柄別取引停止情報）<BR>
     * <BR>
     * 顧客銘柄別取引停止情報オブジェクト<BR>
     * <BR>
     * ------<English>-----------<BR>
     * <BR>
     * l_accountProductTradeStopInfo<BR>
     * <BR>
     * WEB3AdminPMAccProductTradeStopInfoUnit object<BR>
     * <BR>
     * @@param l_accountProductOrderStopParams - （顧客銘柄別取引停止Params）<BR>
     * <BR>
     * 顧客銘柄別取引停止Params<BR>
     * <BR>
     * l_accountProductOrderStopParams<BR>
     * <BR>
     * @@param l_strLastUpdater - 更新者コード<BR>
     * <BR>
     * l_strLastUpdater<BR>
     * <BR>
     * @@return 顧客銘柄別取引停止Params
     * @@roseuid 4199807D0372
     */
    protected AccountProductOrderStopParams createAccountProductOrderStopParams(
        WEB3AdminPMAccProductTradeStopInfoUnit l_accountProductTradeStopInfo,
        AccountProductOrderStopParams l_accountProductOrderStopParams,
        String l_strLastUpdater)
    {
        final String STR_METHOD_NAME =
            "WEB3AdminTMProductStopStartChangeServiceImpl.createAccountProductOrderStopParams("
                + "WEB3AdminPMAccProductTradeStopInfoUnit l_accountProductTradeStopInfo,"
                + " AccountProductOrderStopParams l_accountProductOrderStopParams,"
                + " String l_strLastUpdater)";
        log.entering(STR_METHOD_NAME);

        String l_strTradeStopDiv = null;
        Timestamp l_tsLastUpdatedTimestamp = null;
        WEB3AdminPMAccTradeStopInfoUnit[] l_tradeStopinfos = null;
        String l_strOrderType = null;
        WEB3AdminPMEquityDataManager l_web3AdminPMEquityDataManager =
            new WEB3AdminPMEquityDataManager();
        OrderTypeEnum l_orderTypeEnum = null;
        int l_actProdOrdStopLength = 0;

        //Step 1, Create accountProductOrderStopParams instance
        AccountProductOrderStopParams l_accountProductOrderStop = null;

        //Step 2, Copy content of parameter.accountProductOrderStopParams to the created instance
        l_accountProductOrderStop = l_accountProductOrderStopParams;

        //Step 3, Set the updated information into the created instance
        
        l_accountProductOrderStop.apply_end_date = new Timestamp(
         (WEB3DateUtility.getDate(l_accountProductTradeStopInfo.aftChangeExpirationEndDate,"yyyyMMdd")).getTime());
        l_accountProductOrderStop.stop_trade_reason = l_accountProductTradeStopInfo.aftChangeReason;
        l_tsLastUpdatedTimestamp =
            (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        l_accountProductOrderStop.last_updated_timestamp = l_tsLastUpdatedTimestamp;
        l_accountProductOrderStop.last_updater = l_strLastUpdater;
        l_actProdOrdStopLength = l_accountProductTradeStopInfo.accTradeStopInfoList.length;
        l_tradeStopinfos = l_accountProductTradeStopInfo.accTradeStopInfoList;
        for (int i = 0; i < l_actProdOrdStopLength; i++)
        {
            l_strOrderType = l_tradeStopinfos[i].orderType;
            if (l_strOrderType.equals(String.valueOf(OrderTypeEnum.EQUITY_BUY.intValue())))
            {
                l_orderTypeEnum = OrderTypeEnum.EQUITY_BUY;
            } else if (l_strOrderType.equals(String.valueOf(OrderTypeEnum.EQUITY_SELL.intValue())))
            {
                l_orderTypeEnum = OrderTypeEnum.EQUITY_SELL;
            } else if (l_strOrderType.equals(String.valueOf(OrderTypeEnum.MARGIN_LONG.intValue())))
            {
                l_orderTypeEnum = OrderTypeEnum.MARGIN_LONG;
            } else if (l_strOrderType.equals(String.valueOf(OrderTypeEnum.MARGIN_SHORT.intValue())))
            {
                l_orderTypeEnum = OrderTypeEnum.MARGIN_SHORT;
            } else if (l_strOrderType.equals(String.valueOf(OrderTypeEnum.CLOSE_MARGIN_LONG.intValue())))
            {
                l_orderTypeEnum = OrderTypeEnum.CLOSE_MARGIN_LONG;
            } else if (l_strOrderType.equals(String.valueOf(OrderTypeEnum.CLOSE_MARGIN_SHORT.intValue())))
            {
                l_orderTypeEnum = OrderTypeEnum.CLOSE_MARGIN_SHORT;
            } else if (l_strOrderType.equals(String.valueOf(OrderTypeEnum.SWAP_MARGIN_SHORT.intValue())))
            {
                l_orderTypeEnum = OrderTypeEnum.SWAP_MARGIN_SHORT;
            } else if (l_strOrderType.equals(String.valueOf(OrderTypeEnum.SWAP_MARGIN_LONG.intValue())))
            {
                l_orderTypeEnum = OrderTypeEnum.SWAP_MARGIN_LONG;
            } else if (l_strOrderType.equals(String.valueOf(OrderTypeEnum.MINI_STOCK_BUY.intValue())))
            {
                l_orderTypeEnum = OrderTypeEnum.MINI_STOCK_BUY;
            } else
            {
                l_orderTypeEnum = OrderTypeEnum.MINI_STOCK_SELL;
            }

            l_strTradeStopDiv = l_tradeStopinfos[i].aftTradeStopDiv;

            l_web3AdminPMEquityDataManager.setTradeStopDiv(
                l_orderTypeEnum,
                l_accountProductOrderStopParams,
                l_strTradeStopDiv);

        }
        log.exiting(STR_METHOD_NAME);
        return l_accountProductOrderStop;
    }

}
@
