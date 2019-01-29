head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityAccProductTradeStopRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者顧客銘柄別取引停止登録サービス実装クラス(WEB3AdminEquityAccProductTradeStopRegistServiceImpl.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3EnforcementDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.eqtypeadmin.WEB3AdminPMEquityDataManager;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopInfoUnit;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopRegistCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopRegistCompleteResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopRegistConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopRegistConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopRegistInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopRegistInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccTradeStopInfoUnit;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAccProductTradeStopRegistService;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AccountProductOrderStopParams;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
/**
 * （管理者顧客銘柄別取引停止登録サービス実装クラス）<BR>
 * <BR>
 * 管理者顧客銘柄別取引停止登録サービス実装クラス<BR>
 * <BR>
 * ----<English>--------------------<BR>
 * <BR>
 * WEB3AdminEquityAccProductTradeStopRegistServiceImpl class<BR>
 * <BR>
 * @@author Umadevi
 * @@version 1.0
 */
public class WEB3AdminEquityAccProductTradeStopRegistServiceImpl
    extends WEB3ClientRequestService
    implements WEB3AdminEquityAccProductTradeStopRegistService
{
    /**
     * Log Variable
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityAccProductTradeStopRegistServiceImpl.class);
    /**
     * @@roseuid 41FD95FA001F
     */
    public WEB3AdminEquityAccProductTradeStopRegistServiceImpl()
    {

    }

    /**
     * 顧客銘柄別取引停止登録処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、<BR>
     * 以下のメソッドを呼び分ける。<BR>
     * <BR>
     * ○管理者・顧客銘柄別取引停止登録入力リクエストの場合<BR>
     * 　@this.get入力画面()をコールする。<BR>
     * ○管理者・顧客銘柄別取引停止登録確認リクエストの場合<BR>
     * 　@this.validate登録()をコールする。<BR>
     * ○管理者・顧客銘柄別取引停止登録完了リクエストの場合<BR>
     * 　@this.submit登録()をコールする。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Execute WEB3AdminEquityAccProductTradeStopRegistService process<BR>
     * <BR>
     * Call one of the following methods based on the type of l_request<BR>
     * <BR>
     * ○If WEB3AdminPMAccProductTradeStopRegistInputRequest<BR>
     * 　@Call this.getInputScreen<BR>
     * ○If WEB3AdminPMAccProductTradeStopRegistConfirmRequest<BR>
     * 　@Call this.validateRegist<BR>
     * ○If WEB3AdminPMAccProductTradeStopRegistCompleteRequest<BR>
     * 　@Call this.submitRegist<BR>
     * <BR>
     * @@param l_request - （リクエスト）<BR>
     * <BR>
     * リクエスト<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 4198176D0288
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;
        try
        {
            if (l_request instanceof WEB3AdminPMAccProductTradeStopRegistInputRequest)
            {
                l_response =
                    getInputScreen((WEB3AdminPMAccProductTradeStopRegistInputRequest) l_request);
            } else if (l_request instanceof WEB3AdminPMAccProductTradeStopRegistConfirmRequest)
            {
                l_response =
                    validateRegist((WEB3AdminPMAccProductTradeStopRegistConfirmRequest) l_request);
            } else if (l_request instanceof WEB3AdminPMAccProductTradeStopRegistCompleteRequest)
            {
                l_response =
                    submitRegist((WEB3AdminPMAccProductTradeStopRegistCompleteRequest) l_request);
            } else
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "INPUT リクエスト NOT 管理者顧客銘柄別取引停止登録サービスリクエスト");
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
     * （get入力画面）<BR>
     * <BR>
     * 顧客銘柄別取引停止登録入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者顧客銘柄別取引停止登録サービス)get入力画面」<BR>
     * 参照<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * (getInputScreen)<BR>
     * BR>
     * Execute WEB3AdminEquityAccProductTradeStopRegistService input screen process<BR>
     * <BR>
     * Refer to the sequence diagram  "(administrator; account product trade stop
     * regist service)getInputScreen". <BR>
     * <BR>
     * @@param l_request - （リクエストデータ）<BR>
     * <BR>
     * 管理者・顧客銘柄別取引停止登録入力リクエストオブジェクト<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminPMAccProductTradeStopRegistInputRequest object<BR>
     * <BR>
     * @@return eqtypeadmin.message.WEB3AdminPMAccProductTradeStopRegistInputResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws DataQueryException DataQueryException
     * @@throws NotFoundException NotFoundException
     * @@roseuid 419815B303DF
     */
    protected WEB3AdminPMAccProductTradeStopRegistInputResponse getInputScreen
        (WEB3AdminPMAccProductTradeStopRegistInputRequest l_request)
        throws WEB3BaseException, DataQueryException, NotFoundException
    {
        final String STR_METHOD_NAME =
            "getInputScreen(WEB3AdminPMAccProductTradeStopRegistInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3Administrator l_administrator = null;
        WEB3GentradeAccountManager l_accManager = null;
        WEB3AdminPMAccTradeStopInfoUnit l_tradeStopInfoUnit = null;
        WEB3AdminPMAccTradeStopInfoUnit[] l_arrTradeStopInfoUnit
            = new WEB3AdminPMAccTradeStopInfoUnit[1];
        WEB3AdminPMAccProductTradeStopRegistInputResponse l_response = null;
        Branch l_branch = null;
        ArrayList l_arrListOrderType = null;
        ArrayList l_arrListTradeStopInfoUnit = null;
        String[] l_strBranchCodeList = null;
        String l_strBranchCode = null;
        String l_strInstitutionCode = null;
        String l_strMarginSysDiv = null;
        String l_strMarginGenDiv = null;
        String l_strMstkDiv = null;
        boolean l_isUpdate = false;
        int l_intOrderTypeCount = 0;

        // 1.1 Call validate()
        l_request.validate();

        //1.2 Call getInstanceFromLoginInfo( )
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //1.3 Call validateAuthority(l_strTransactionCategory : String, l_isUpdate : boolean)
        l_isUpdate = true;
        l_administrator.validateAuthority
            (WEB3TransactionCategoryDef.EQTYPE_ACCOUNT_PRODUCT_STOP , l_isUpdate);

        //1.4 Call validateBranchPermission(l_strBranchCode : String[])
        l_strBranchCodeList = l_request.branchCodeList;
        l_administrator.validateBranchPermission(l_strBranchCodeList);

        //1.5 Call getInstitutionCode( )
        l_strInstitutionCode = l_administrator.getInstitutionCode();

        //1.6 Call getBranchCode( )
        l_strBranchCode = l_administrator.getBranchCode();

        //1.7 Call getBranch(l_strInstitutionCode : String, l_strBranchCode : String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        l_accManager = (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        // Use Institution object instead of  l_strInstitutionCode
        l_branch = l_accManager.getWeb3GenBranch(l_strInstitutionCode, l_strBranchCode);
        //1.8 Create an ArrayList()
        l_arrListOrderType = new ArrayList();

        // 1.9 Looping
        l_arrListOrderType.add(OrderTypeEnum.EQUITY_BUY);
        l_arrListOrderType.add(OrderTypeEnum.EQUITY_SELL);

        BranchParams l_params = (BranchParams) l_branch.getDataSourceObject();
        l_strMarginSysDiv = l_params.margin_sys_div;
        l_strMarginGenDiv = l_params.margin_gen_div;
        l_strMstkDiv = l_params.mstk_div;
        //To check if any of l_strMarginSysDiv, l_strMarginGenDiv is 1
        if ((l_strMarginSysDiv.equals(WEB3EnforcementDef.ENFORCEMENT))
            || (l_strMarginGenDiv.equals(WEB3EnforcementDef.ENFORCEMENT)))
        {
            l_arrListOrderType.add(OrderTypeEnum.MARGIN_LONG);
            l_arrListOrderType.add(OrderTypeEnum.MARGIN_SHORT);
            l_arrListOrderType.add(OrderTypeEnum.CLOSE_MARGIN_LONG);
            l_arrListOrderType.add(OrderTypeEnum.CLOSE_MARGIN_SHORT);
            l_arrListOrderType.add(OrderTypeEnum.SWAP_MARGIN_LONG);
            l_arrListOrderType.add(OrderTypeEnum.SWAP_MARGIN_SHORT);
        } 
        if (l_strMstkDiv.equals(WEB3EnforcementDef.ENFORCEMENT))
        {
            l_arrListOrderType.add(OrderTypeEnum.MINI_STOCK_BUY);
            l_arrListOrderType.add(OrderTypeEnum.MINI_STOCK_SELL);
        }
        //9.1 Create an instance of WEB3AdminPMAccTradeStopInfoUnit
        l_intOrderTypeCount = l_arrListOrderType.size();
        l_arrListTradeStopInfoUnit = new ArrayList();

        for (int i = 0; i < l_intOrderTypeCount; i++)
        {
            l_tradeStopInfoUnit = new WEB3AdminPMAccTradeStopInfoUnit();
            l_tradeStopInfoUnit.orderType =
                String.valueOf(((OrderTypeEnum)l_arrListOrderType.get(i)).intValue());
            l_arrListTradeStopInfoUnit.add(l_tradeStopInfoUnit);
        }
         
        // 1.10 Call toArray
        l_arrTradeStopInfoUnit = 
         (WEB3AdminPMAccTradeStopInfoUnit[]) l_arrListTradeStopInfoUnit.toArray(new WEB3AdminPMAccTradeStopInfoUnit[0]);

        // 1.11 Call createResponse()
        l_response = (WEB3AdminPMAccProductTradeStopRegistInputResponse) l_request.createResponse();
        l_response.accTradeStopInfoList = l_arrTradeStopInfoUnit;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * （validate登録）<BR>
     * <BR>
     * 顧客銘柄別取引停止登録確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者顧客銘柄別取引停止登録サービス)validate登録」<BR>
     * 参照<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * (validateRegist)<BR>
     * <BR>
     * Execute WEB3AdminEquityAccProductTradeStopRegistService validate process<BR>
     * <BR>
     * Refer to the sequence diagram "(administrator; account product trade stop regist
     * service)validateRegist". <BR>
     * <BR>
     * @@param l_request - （リクエストデータ）<BR>
     * <BR>
     * 管理者・顧客銘柄別取引停止登録確認リクエストオブジェクト<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminPMAccProductTradeStopRegistConfirmRequest object<BR>
     * <BR>
     * @@return eqtypeadmin.message.WEB3AdminPMAccProductTradeStopRegistConfirmResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws DataQueryException DataQueryException
     * @@roseuid 419815B40017
     */
    protected WEB3AdminPMAccProductTradeStopRegistConfirmResponse
        validateRegist(WEB3AdminPMAccProductTradeStopRegistConfirmRequest l_request)
        throws WEB3BaseException, DataQueryException//, NotFoundException
    {
        final String STR_METHOD_NAME =
            "validateRegist(WEB3AdminPMAccProductTradeStopRegistConfirmRequest)";
        final String DATE_FORMAT = "yyyyMMdd";
        log.entering(STR_METHOD_NAME);

        WEB3Administrator l_administrator = null;
        WEB3GentradeAccountManager l_accManager = null;
        WEB3EquityProductManager l_equityProductManager = null;
        WEB3AdminPMAccProductTradeStopRegistConfirmResponse l_response = null;
        AccountProductOrderStopParams l_accProductOrderStopParams = null;
        EqTypeProduct l_eqTypeProduct = null;
        WEB3GentradeMainAccount l_mainAccount = null;
        String l_strBranchCode = null;
        String l_strInstitutionCode = null;
        String l_strAccountCode = null;
        String l_productCode = null;
        String l_strExpirationStartDate = null;
        Date l_dtExpirationStartDate = null;
        long l_lngAccountId = 0;
        long l_lngProductId = 0;
        long l_lngBranchId = 0;
        Institution l_institution = null;
        Branch l_branch = null;
        boolean l_isUpdate = false;

        //1.1 Call validate()
        l_request.validate();

        //1.2 Call getInstanceFromLoginInfo( )
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // 1.3 Call validateAuthority(l_strTransactionCategory : String, l_isUpdate : boolean)
        l_isUpdate = true;
        l_administrator.validateAuthority
            (WEB3TransactionCategoryDef.EQTYPE_ACCOUNT_PRODUCT_STOP, l_isUpdate);

        // 1.4 Call getInstitutionCode( )
        l_strInstitutionCode = l_administrator.getInstitutionCode();
        l_institution = l_administrator.getInstitution();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        l_accManager = (WEB3GentradeAccountManager) l_finApp.getAccountManager();

        l_strBranchCode = l_request.accProductTradeStopInfo.branchCode;

        // 1.5 Call getBranch()
        try
        {
            // Use Institution object instead of l_strinstitution
            l_branch = l_accManager.getWeb3GenBranch(l_strInstitutionCode, l_strBranchCode);
        } 
        catch (NotFoundException nf_ex)
        {
            String l_strMsg = "No Data for the branch";
            log.error(l_strMsg, nf_ex);
              throw new WEB3BusinessLayerException(
              WEB3ErrorCatalog.BUSINESS_ERROR_01386,
              this.getClass().getName() + "." + STR_METHOD_NAME,
              nf_ex.toString(),
              nf_ex);
        }
        l_strAccountCode = l_request.accProductTradeStopInfo.accountCode;

        // 1.6 Call getMainAccount()
        try
        {
            l_mainAccount = l_accManager.getMainAccount(l_strInstitutionCode , l_strBranchCode , l_strAccountCode);
        } 
        catch (WEB3SystemLayerException web3sl_ex)
        {
            String l_strMsg = "No Data for the main account";
            log.error(l_strMsg, web3sl_ex);
              throw new WEB3SystemLayerException(
              WEB3ErrorCatalog.BUSINESS_ERROR_01387,
              this.getClass().getName() + "." + STR_METHOD_NAME,
              web3sl_ex.toString(),
              web3sl_ex);
        }
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        l_equityProductManager = (WEB3EquityProductManager) l_tradingModule.getProductManager();
        l_productCode = l_request.accProductTradeStopInfo.productCode;
        // 1.7 Product Code not null
        if (l_productCode != null)
        {
            try
            {
                // 1.7.1 Call getInstitution()
                l_institution = l_accManager.getInstitution(l_strInstitutionCode);
                // 1.7.2 Call getProduct().
                l_eqTypeProduct  = l_equityProductManager.getProduct
                    (l_institution , l_productCode);
                l_lngProductId = l_eqTypeProduct.getProductId();
            } 
            catch (NotFoundException nf_ex)
            {
                String l_strMsg = "No Data for the product";
                log.error(l_strMsg, nf_ex);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00391,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    nf_ex.toString(),
                    nf_ex);
            }
        }
        l_lngBranchId = l_branch.getBranchId();
        l_lngAccountId = l_mainAccount.getAccountId();
        l_strExpirationStartDate = l_request.accProductTradeStopInfo.expirationStartDate;
        l_dtExpirationStartDate = WEB3DateUtility.getDate(l_strExpirationStartDate , DATE_FORMAT);

        // 1.8 Call getAccountProductOrderStop()
        // Use Institution object instead of l_strInstitutionCode
        l_accProductOrderStopParams = l_accManager.getAccountProductOrderStop
                (l_strInstitutionCode ,l_lngBranchId ,l_lngAccountId ,l_lngProductId ,l_dtExpirationStartDate);
        if (l_accProductOrderStopParams != null)
        {
            String l_strMsg = "overlapped data error(accountProductTradeStopInfo)";
            log.error(l_strMsg);
              throw new WEB3BusinessLayerException(
              WEB3ErrorCatalog.BUSINESS_ERROR_01976,
              this.getClass().getName() + "." + STR_METHOD_NAME,
              l_strMsg);
        }

        // 1.9 Property Set
        l_request.accProductTradeStopInfo.accountName = l_mainAccount.getDisplayAccountName();
        // 1.9 Property Set
        if (l_productCode == null)
        {
            l_request.accProductTradeStopInfo.productName = null;
        } else
        {
            l_request.accProductTradeStopInfo.productName = ((EqtypeProductRow)l_eqTypeProduct.getDataSourceObject()).getStandardName();
        }

        //1.10 Call createResponse()
        l_response = (WEB3AdminPMAccProductTradeStopRegistConfirmResponse)l_request.createResponse();

        //1.11 Set the response data
        l_response.accProductTradeStopInfo = l_request.accProductTradeStopInfo;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * （submit登録）<BR>
     * <BR>
     * 顧客銘柄別取引停止登録完了処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者顧客銘柄別取引停止登録サービス)submit登録」<BR>
     * 参照<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * (submitRegist)<BR>
     * <BR>
     * Execute WEB3AdminEquityAccProductTradeStopRegistService submit process<BR>
     * <BR>
     * Refer to the sequence diagram"(administrator; account product trade stop regist
     * service)submitRegist". <BR>
     * <BR>
     * @@param l_request - （リクエストデータ）<BR>
     * <BR>
     * 管理者・顧客銘柄別取引停止登録完了リクエストオブジェクト<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminPMAccProductTradeStopRegistCompleteRequest object<BR>
     * <BR>
     * @@return eqtypeadmin.message.WEB3AdminPMAccProductTradeStopRegistCompleteResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws NotFoundException NotFoundException
     * @@roseuid 419815B40036
     */
    protected WEB3AdminPMAccProductTradeStopRegistCompleteResponse submitRegist(
        WEB3AdminPMAccProductTradeStopRegistCompleteRequest l_request)
        throws WEB3BaseException , NotFoundException
    {
        final String STR_METHOD_NAME =
            "submitRegist(WEB3AdminPMAccProductTradeStopRegistCompleteRequest)";
        final String DATE_FORMAT = "yyyyMMdd";
        log.entering(STR_METHOD_NAME);

        WEB3Administrator l_administrator = null;
        WEB3GentradeAccountManager l_accManager = null;
        WEB3EquityProductManager l_equityProductManager = null;
        WEB3AdminPMAccProductTradeStopInfoUnit l_accProductTradeStopInfo = null;
        WEB3AdminPMAccProductTradeStopRegistCompleteResponse l_response = null;
        Institution l_institution = null;
        Branch l_branch = null;
        MainAccount l_mainAccount = null;
        EqTypeProduct l_equityProduct = null;
        AccountProductOrderStopParams l_accProductOrderStopParams = null;
        Date l_dtExpirationStartDate = null;
        String l_strPassword = null;
        String l_strInstitutionCode = null;
        String l_strBranchCode = null;
        String l_strAccountCode = null;
        String l_strProductCode = null;
        String l_strExpirationStartDate = null;
        String l_strAdministratorCode = null;
        Timestamp l_currentDate = null;
        long l_lngBranchId = 0;
        long l_lngAccountId = 0;
        long l_lngProductId = 0;
        boolean l_isUpdate = false;

        //1.1 Call validate()
        l_request.validate();

        //1.2 Call getInstanceFromLoginInfo( )
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //1.3 ValidateAuthority(l_strTransactionCategory : String, l_isUpdate : boolean)
        l_isUpdate = true;
        l_administrator.validateAuthority
            (WEB3TransactionCategoryDef.EQTYPE_ACCOUNT_PRODUCT_STOP, l_isUpdate);

        l_strPassword = l_request.password;
        // 1.4 ValidateTradingPassword()
        l_administrator.validateTradingPassword(l_strPassword);

        //1.5 Call getInstitutionCode( )
        l_strInstitutionCode = l_administrator.getInstitutionCode();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        l_accManager = (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        l_institution = l_administrator.getInstitution();
        l_strBranchCode = l_request.accProductTradeStopInfo.branchCode;

        //1.6 Call getBranch()
        // Use Institution object instead of l_strInstitution
        try
        {
            l_branch = l_accManager.getWeb3GenBranch(l_strInstitutionCode , l_strBranchCode);
        } 
        catch (NotFoundException nf_ex)
        {
            String l_strMsg = "No Data for the branch";
            log.error(l_strMsg, nf_ex);
              throw new WEB3BusinessLayerException(
              WEB3ErrorCatalog.BUSINESS_ERROR_01386,
              this.getClass().getName() + "." + STR_METHOD_NAME,
              nf_ex.toString(),
              nf_ex);
        }
        l_strAccountCode = l_request.accProductTradeStopInfo.accountCode;

        //1.7 Call getMainAccount()
        // Use Institution object instead of l_strInstitution
        try
        {
            l_mainAccount = l_accManager.getMainAccount(l_strInstitutionCode,l_strBranchCode,l_strAccountCode);
        } 
        catch (WEB3SystemLayerException web3sl_ex)
        {
            String l_strMsg = "No Data for the main account";
            log.error(l_strMsg, web3sl_ex);
              throw new WEB3BusinessLayerException(
              WEB3ErrorCatalog.BUSINESS_ERROR_01387,
              this.getClass().getName() + "." + STR_METHOD_NAME,
              web3sl_ex.toString(),
              web3sl_ex);
        }
        l_strProductCode = l_request.accProductTradeStopInfo.productCode;
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        l_equityProductManager = (WEB3EquityProductManager) l_tradingModule.getProductManager();

        // 1.8 If productCode is input
        if (l_strProductCode != null)
        {
            try
            {
                //1.8.1 Call getInstitution()
                l_institution = l_accManager.getInstitution(l_strInstitutionCode);
                //1.8.2 Call getProduct()
                l_equityProduct = l_equityProductManager.getProduct
                    (l_institution , l_strProductCode);
                l_lngProductId = l_equityProduct.getProductId();
            } catch (NotFoundException nf_ex)
            {
                String l_strMsg = "No Data for the product";
                log.error(l_strMsg, nf_ex);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00391,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    nf_ex.toString(),
                    nf_ex);
            }
        }
        l_lngBranchId = l_branch.getBranchId();
        l_lngAccountId = l_mainAccount.getAccountId();
        l_strExpirationStartDate = l_request.accProductTradeStopInfo.expirationStartDate;
        l_dtExpirationStartDate = WEB3DateUtility.getDate(l_strExpirationStartDate , DATE_FORMAT);
        // 1.9 Call getAccountProductOrderStop()
        l_accProductOrderStopParams = l_accManager.getAccountProductOrderStop
            (l_strInstitutionCode , l_lngBranchId , l_lngAccountId , l_lngProductId , l_dtExpirationStartDate);
        if (l_accProductOrderStopParams != null)
        {
            String l_strMsg = "overlapped data error(accountProductTradeStopInfo)";
            log.error(l_strMsg);
              throw new WEB3BusinessLayerException(
              WEB3ErrorCatalog.BUSINESS_ERROR_01976,
              this.getClass().getName() + "." + STR_METHOD_NAME,
              l_strMsg);
        }

        //1.10 Call getAdministratorCode()
        l_strAdministratorCode = l_administrator.getAdministratorCode();

        //1.11 Arguments for the method createAccountProductOrderStopParams
        l_accProductTradeStopInfo = l_request.accProductTradeStopInfo;
        l_lngBranchId = l_branch.getBranchId();
        l_lngAccountId = l_mainAccount.getAccountId();

        //1.11 CreateAccountProductOrderStopParams ()
        AccountProductOrderStopParams l_accProdOrderStopParams = createAccountProductOrderStopParams
        (l_accProductTradeStopInfo , l_strInstitutionCode , l_lngBranchId
                , l_lngAccountId , l_lngProductId , l_strAdministratorCode);

        //1.12 InsertAccountProductOrderStop()
         l_accManager.insertAccountProductOrderStop(l_accProdOrderStopParams);
        l_response = (WEB3AdminPMAccProductTradeStopRegistCompleteResponse)l_request.createResponse();

        //1.14 Property Set
        l_currentDate =
            (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        l_response.currentDate = l_currentDate;
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * （create顧客銘柄別取引停止Params）<BR>
     * <BR>
     * 引数の情報より顧客銘柄別取引停止Paramsを作成する。<BR>
     * <BR>
     * １）顧客銘柄別取引停止Paramsインスタンスを生成し、<BR>
     * 　@全ての項目をnullで初期化する。<BR>
     * <BR>
     * ２）生成したインスタンスに以下のプロパティをセットする。<BR>
     * 　@証券会社コード = パラメータ.証券会社コード<BR>
     * 　@部店ID = パラメータ.部店ID<BR>
     * 　@口座ID = パラメータ.口座ID<BR>
     * 　@銘柄ID = パラメータ.銘柄ID<BR>
     * 　@適用開始年月日 = パラメータ.顧客銘柄別取引停止情報.有効期限From<BR>
     * 　@適用終了年月日 = パラメータ.顧客銘柄別取引停止情報.有効期限To<BR>
     * 　@取引停止理由 = パラメータ.顧客銘柄別取引停止情報.取引停止理由<BR>
     * 　@更新者コード = パラメータ.更新者コード<BR>
     * 　@作成日付 = (*1)<BR>
     * 　@更新日付 = (*1)<BR>
     * <BR>
     * ３）生成したインスタンスに取引停止情報をセットする。<BR>
     * 　@顧客銘柄別取引停止情報.顧客取引停止情報一覧の<BR>
     * 　@要素(=顧客取引停止情報)数分以下の処理を繰り返す。<BR>
     * 　@３－１）商品管理データマネージャ.set取引停止区分()をコールする。<BR>
     * <BR>
     * 　@　@　@　@[set取引停止区分()にセットするパラメータ]<BR>
     * 　@　@　@　@　@注文種別：　@処理対象の顧客取引停止情報.注文種別<BR>
     * 　@　@　@　@　@顧客銘柄別取引停止Params：　@生成したインスタンス<BR>
     * 　@　@　@　@　@取引停止区分：　@処理対象の顧客取引停止情報.取引停止区分<BR>
     * <BR>
     * ４）プロパティセットしたインスタンスを返却する。<BR>
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
     * 1)Create an accountProductOrderStopParams instance and initialize all items with
     * null<BR>
     * <BR>
     * 2)Set the following properties to the created instance<BR>
     * 　@institution_code = parameter.institutionCode<BR>
     * 　@branch_id = parameter.branchId<BR>
     * 　@account_id =parameter.accountId<BR>
     * 　@product_id = parameter.productId<BR>
     * 　@apply_start_date =
     * parameter.WEB3AdminPMAccProductTradeStopInfoUnit.expirationStartDate<BR>
     * 　@apply_end_date =
     * parameter.WEB3AdminPMAccProductTradeStopInfoUnit.expirationEndDateBR>
     * 　@stop_trade_reason =
     * parameter.WEB3AdminPMAccProductTradeStopInfoUnit.reason<BR>
     * 　@last_updater = parameter.lastUpdater<BR>
     * 　@created_timestamp = timeStamp(*1)<BR>
     * 　@last_updated_timestamp = timeStamp(*1)<BR>
     * <BR>
     * 3)Set accProductTradeStopInfo to the created instance.<BR>
     * 　@Loop the following process for as many times as elements of
     * WEB3AdminPMAccProductTradeStopInfoUnit.accTradeStopInfoList
     * (=WEB3AdminPMAccTradeStopInfoUnit)<BR>
     * 　@3-1)Call WEB3AdminPMEquityDataManager.setTradeStopDiv()<BR>
     * <BR>
     * 　@　@　@　@[parameter set into setTradeStopDiv()]<BR>
     * 　@　@　@　@　@l_orderType: WEB3AdminPMAccProductTradeStopInfoUnit for the
     * process.orderType<BR>
     * 　@　@　@　@　@l_accountProductOrderStopParams: the created instance<BR>
     * 　@　@　@　@　@l_tradeStopDiv: WEB3AdminPMAccProductTradeStopInfoUnit for the
     * process.tradeStopDiv<BR>
     * <BR>
     * 4)Return the instance set in 'Property Set'<BR>
     * <BR>
     * (*1)<BR>
     * timeStamp acquired at <BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManageme
     * nt.TIMESTAMP_TAG)<BR>
     * <BR>
     * @@param l_accProductTradeStopInfo - （顧客銘柄別取引停止情報）<BR>
     * <BR>
     * 顧客銘柄別取引停止情報オブジェクト<BR>
     * <BR>
     * --------<English>------------------<BR>
     * <BR>
     * l_accProductTradeStopInfo<BR>
     * <BR>
     * WEB3AdminPMAccProductTradeStopInfoUnit object<BR>
     * <BR>
     *
     * @@param l_strInstitutionCode - （証券会社コード）<BR>
     * <BR>
     * l_strInstitutionCode<BR>
     * <BR>
     * @@param l_lngBranchId - （部店ID）<BR>
     * <BR>
     * l_lngBranchId<BR>
     * <BR>
     * @@param l_lngAccountId - （口座ID）<BR>
     * <BR>
     * l_lngAccountId<BR>
     * <BR>
     * @@param l_lngProductId - （銘柄ID）<BR>
     * <BR>
     * l_lngProductId<BR>
     * <BR>
     * @@param l_strLastUpdater - （更新者コード）<BR>
     * <BR>
     * l_strLastUpdater<BR>
     * <BR>
     * @@return 顧客銘柄別取引停止Params
     * @@roseuid 4198421D0353
     */
    protected AccountProductOrderStopParams createAccountProductOrderStopParams(
        WEB3AdminPMAccProductTradeStopInfoUnit l_accProductTradeStopInfo,
        String l_strInstitutionCode,
        long l_lngBranchId,
        long l_lngAccountId,
        long l_lngProductId,
        String l_strLastUpdater)
    {
        final String STR_METHOD_NAME =
            "createAccountProductOrderStopParams"
            + "(WEB3AdminPMAccProductTradeStopInfoUnit,String,long,long,long,String)";
        final String DATE_FORMAT = "yyyyMMdd";
        log.entering(STR_METHOD_NAME);
        AccountProductOrderStopParams l_accProdOrderStopParams = null;

        //1) Create an accountProductOrderStopParams instance and initialize all items with null
        l_accProdOrderStopParams = new AccountProductOrderStopParams();
        //l_accProdOrderStopParams.markAllValuesAsSet();
        WEB3AdminPMEquityDataManager l_adminPMEquityDataManager = null;
        OrderTypeEnum l_orderType = null;
        l_accProdOrderStopParams.account_id = 0;
        l_accProdOrderStopParams.apply_end_date = null;
        l_accProdOrderStopParams.apply_start_date = null;
        l_accProdOrderStopParams.branch_id = 0;
        l_accProdOrderStopParams.created_timestamp = null;
        l_accProdOrderStopParams.institution_code = null;
        l_accProdOrderStopParams.last_updated_timestamp = null;
        l_accProdOrderStopParams.last_updater = null;
        l_accProdOrderStopParams.product_id = 0;
        l_accProdOrderStopParams.stop_div_buy_mini_stock = null;
        l_accProdOrderStopParams.stop_div_long_close_margin = null;
        l_accProdOrderStopParams.stop_div_long_swap_margin = null;
        l_accProdOrderStopParams.stop_div_sell_mini_stock = null;
        l_accProdOrderStopParams.stop_trade_div_long_margin = null;
        l_accProdOrderStopParams.stop_trade_div_sell_cash = null;
        l_accProdOrderStopParams.stop_trade_div_short_margin = null;
        l_accProdOrderStopParams.stop_trade_reason = null;
        int l_intaccTradeStopInfoListLength = 0;
        Date l_dtExpirationStartDate = null;
        Date l_dtExpirationEndDate = null;
        Timestamp l_createdTimeStamp = null;
        Timestamp l_tsStartDate = null;
        Timestamp l_tsEndDate = null;
        String l_strExpirationStartDate = null;
        String l_strExpirationEndDate = null;
        String l_tradeStopDiv = null;

        // 2) Set the following properties to the created instance
        l_accProdOrderStopParams.setInstitutionCode(l_strInstitutionCode);
        l_accProdOrderStopParams.setBranchId(l_lngBranchId);
        l_accProdOrderStopParams.setAccountId(l_lngAccountId);
        l_accProdOrderStopParams.setProductId(l_lngProductId);
        l_strExpirationStartDate = l_accProductTradeStopInfo.expirationStartDate;
        l_dtExpirationStartDate = WEB3DateUtility.getDate(l_strExpirationStartDate , DATE_FORMAT);
        l_tsStartDate = new Timestamp(l_dtExpirationStartDate.getTime());
        l_accProdOrderStopParams.setApplyStartDate(l_tsStartDate);
        l_strExpirationEndDate = l_accProductTradeStopInfo.expirationEndDate;
        l_dtExpirationEndDate = WEB3DateUtility.getDate(l_strExpirationEndDate , DATE_FORMAT);
        l_tsEndDate = new Timestamp(l_dtExpirationEndDate.getTime());
        l_accProdOrderStopParams.setApplyEndDate(l_tsEndDate);
        l_accProdOrderStopParams.setStopTradeReason(l_accProductTradeStopInfo.reason);
        l_accProdOrderStopParams.setLastUpdater(l_strLastUpdater);
        l_createdTimeStamp = (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute
                                        (WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        l_accProdOrderStopParams.setCreatedTimestamp(l_createdTimeStamp);
        l_accProdOrderStopParams.setLastUpdatedTimestamp(l_createdTimeStamp);
        l_adminPMEquityDataManager = new WEB3AdminPMEquityDataManager();
        l_intaccTradeStopInfoListLength = l_accProductTradeStopInfo.accTradeStopInfoList.length;
        // 3) Set accProductTradeStopInfo to the created instance
        for (int i = 0; i < l_intaccTradeStopInfoListLength; i++)
        {
            String l_strOrderType = null;
            l_strOrderType = l_accProductTradeStopInfo.accTradeStopInfoList[i].orderType;
            if (l_strOrderType.equals(String.valueOf(OrderTypeEnum.EQUITY_BUY.intValue())))
            {
                l_orderType = OrderTypeEnum.EQUITY_BUY;
            } else if (l_strOrderType.equals(String.valueOf(OrderTypeEnum.EQUITY_SELL.intValue())))
            {
                l_orderType = OrderTypeEnum.EQUITY_SELL;
            } else if (l_strOrderType.equals(String.valueOf(OrderTypeEnum.MARGIN_LONG.intValue())))
            {
                l_orderType = OrderTypeEnum.MARGIN_LONG;
            } else if (l_strOrderType.equals(String.valueOf(OrderTypeEnum.MARGIN_SHORT.intValue())))
            {
                l_orderType = OrderTypeEnum.MARGIN_SHORT;
            } else if (l_strOrderType.equals(String.valueOf(OrderTypeEnum.CLOSE_MARGIN_LONG.intValue())))
            {
                l_orderType = OrderTypeEnum.CLOSE_MARGIN_LONG;
            } else if (l_strOrderType.equals(String.valueOf(OrderTypeEnum.CLOSE_MARGIN_SHORT.intValue())))
            {
                l_orderType = OrderTypeEnum.CLOSE_MARGIN_SHORT;
            } else if (l_strOrderType.equals(String.valueOf(OrderTypeEnum.SWAP_MARGIN_SHORT.intValue())))
            {
                l_orderType = OrderTypeEnum.SWAP_MARGIN_SHORT;
            } else if (l_strOrderType.equals(String.valueOf(OrderTypeEnum.SWAP_MARGIN_LONG.intValue())))
            {
                l_orderType = OrderTypeEnum.SWAP_MARGIN_LONG;
            } else if (l_strOrderType.equals(String.valueOf(OrderTypeEnum.MINI_STOCK_BUY.intValue())))
            {
                l_orderType = OrderTypeEnum.MINI_STOCK_BUY;
            } else
            {
                l_orderType = OrderTypeEnum.MINI_STOCK_SELL;
            }
            l_tradeStopDiv = l_accProductTradeStopInfo.accTradeStopInfoList[i].tradeStopDiv;
            l_adminPMEquityDataManager.setTradeStopDiv
            (l_orderType , l_accProdOrderStopParams , l_tradeStopDiv);
        }
        log.exiting(STR_METHOD_NAME);

        // 4) Return the instance set in 'Property Set'
        return l_accProdOrderStopParams;
    }
}
@
