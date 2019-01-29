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
filename	WEB3AdminEquityProductCondSettingServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҋ������������ݒ�T�[�r�XImpl(WEB3AdminEquityProductCondSettingServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/06/17�@@�g�E�N�|�i���u�j�d�l�ύX�@@���f��207�A208�A209
*/
package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateCalcParameterDef;
import webbroker3.common.define.WEB3EnforcementDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;

import webbroker3.eqtypeadmin.WEB3AdminPMEquityDataManager;
import webbroker3.eqtypeadmin.data.EqtypeProductConditionDao;
import webbroker3.eqtypeadmin.data.EqtypeProductConditionParams;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityDataTypeDef;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityDeleteFlgDef;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityLargeItemDivDef;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityLastUpdaterDef;
import webbroker3.eqtypeadmin.define.WEB3AdminEquitySmallItemDivDef;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondConfCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondConfCompleteResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondConfConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondConfConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondConfInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondConfInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondConfigUnit;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondSettingUpdateInfo;
import webbroker3.eqtypeadmin.message.WEB3AdminPMUpdateInfo;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityProductCondSettingService;

import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;

/**
 * �i�Ǘ��Ҋ������������ݒ�T�[�r�X�����j<BR>
 * <BR>
 * �Ǘ��Ҋ������������ݒ�T�[�r�X�����N���X<BR>
 * <BR>
 * ----<English>--------------------<BR>
 * <BR>
 * WEB3AdminPMProductCondConfCompleteImpl class<BR>
 * <BR>
 * @@author Babu Prasad
 * @@version 1.0
 */
public class WEB3AdminEquityProductCondSettingServiceImpl
    extends WEB3ClientRequestService
    implements WEB3AdminEquityProductCondSettingService
{

    /**
     * Log Variable
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityProductCondSettingServiceImpl.class);

    /**
     * @@roseuid 41FD9027034B
     */
    public WEB3AdminEquityProductCondSettingServiceImpl()
    {

    }

    /**
     * �������������ݒ菈�����s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��ȉ��̃��\�b�h��<BR>
     * �Ăѕ�����B<BR>
     * <BR>
     * ���Ǘ��ҁE�������������ݒ���̓��N�G�X�g�̏ꍇ<BR>
     * �@@this.get�����ݒ���͉��()���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE�������������ݒ�m�F���N�G�X�g�̏ꍇ<BR>
     * �@@this.validate�����ݒ�()���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE�������������ݒ芮�����N�G�X�g�̏ꍇ<BR>
     * �@@this.submit�����ݒ�()���R�[������B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Execute WEB3AdminEquityProductCondSettingService process<BR>
     * <BR>
     * Call one of the following methods based on the type of the argument,
     * l_request.<BR>
     * <BR>
     * ��If WEB3AdminPMProductCondConfInputRequest<BR>
     * �@@Call this.getConditionSettingInputScreen()<BR>
     * <BR>
     * ��If WEB3AdminPMProductCondConfConfirmRequest<BR>
     * �@@Call this.validateConditionSetting<BR>
     * <BR>
     * ��If WEB3AdminPMProductCondConfCompleteRequest<BR>
     * �@@Call this.submitConditionSetting<BR>
     * <BR>
     * @@param l_request - �i���N�G�X�g�f�[�^�j<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 417F911401C2
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        try
        {
            // If WEB3AdminPMProductCondConfInputRequest
            if (l_request instanceof WEB3AdminPMProductCondConfInputRequest)
            {
                l_response =
                    getConditionSettingInputScreen(
                        (WEB3AdminPMProductCondConfInputRequest) l_request);
                // If WEB3AdminPMProductCondConfConfirmRequest
            } else if (l_request instanceof WEB3AdminPMProductCondConfConfirmRequest)
            {
                l_response =
                    validateConditionSetting((WEB3AdminPMProductCondConfConfirmRequest) l_request);
                // If WEB3AdminPMProductCondConfCompleteRequest
            } else if (l_request instanceof WEB3AdminPMProductCondConfCompleteRequest)
            {
                l_response =
                    submitConditionSetting((WEB3AdminPMProductCondConfCompleteRequest) l_request);
            } else
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "INPUT ���N�G�X�g NOT �������������ݒ胊�N�G�X�g");
            }
        } catch (NotFoundException l_notFoundException)
        {
            log.error(l_notFoundException.getMessage(), l_notFoundException);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_notFoundException.getMessage(),
                l_notFoundException);
        } catch (DataNetworkException l_dnEx)
        {
            log.debug(l_dnEx.getMessage());
            String l_strMsg = "Error while aquiring the Data ";
            log.error(l_strMsg, l_dnEx);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dnEx.toString(),
                l_dnEx);
        } catch (DataQueryException l_dqEx)
        {
            log.debug(l_dqEx.getMessage());
            String l_strMsg = "Error while aquiring the Data ";
            log.error(l_strMsg, l_dqEx);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqEx.toString(),
                l_dqEx);
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * �iget�����ݒ���͉�ʁj<BR>
     * <BR>
     * �������������ݒ���͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��Ҋ������������ݒ�T�[�r�X)get�����ݒ���͉�ʁv<BR>
     * �Q��<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * getConditionSettingInputScreen<BR>
     * <BR>
     * Execute WEB3AdminEquityProductCondSettingService input screen process<BR>
     * <BR>
     * Refer to the sequence diagram "(administrator: equity product condition setting
     * service)getConditionSettingInputScreen"<BR>
     * <BR>
     * @@param l_request - �i���N�G�X�g�f�[�^�j<BR>
     * <BR>
     * �Ǘ��ҁE�������������ݒ���̓��N�G�X�g�I�u�W�F�N�g<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminPMProductCondConfInputRequest object<BR>
     * <BR>
     * @@return eqtypeadmin.message.WEB3AdminPMProductCondConfInputResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws NotFoundException NotFoundException
     * @@throws DataNetworkException DataNetworkException
     * @@throws DataQueryException DataQueryException
     * @@roseuid 417F916F03D4
     */
    protected WEB3AdminPMProductCondConfInputResponse getConditionSettingInputScreen(
        WEB3AdminPMProductCondConfInputRequest l_request)
        throws WEB3BaseException, NotFoundException, DataNetworkException, DataQueryException
    {
        final String STR_METHOD_NAME =
            "getConditionSettingInputScreen(WEB3AdminPMProductCondConfInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3Administrator l_administrator = null;
        WEB3GentradeAccountManager l_accManager = null;
        WEB3EquityProductManager l_equityProductManager = null;
        WEB3EquityProduct l_equityProduct = null;
        WEB3AdminPMProductCondConfInputResponse l_response = null;
        WEB3GentradeBizDate l_gentradeBizDate = null;
        WEB3AdminPMProductCondConfigUnit[] l_tradingRegulationList = null;
        WEB3AdminPMProductCondConfigUnit[] l_basicInfoList = null;
        WEB3AdminPMProductCondConfigUnit[] l_stockMarginInfoList = null;
        WEB3AdminPMProductCondConfigUnit[] l_depositRateList = null;
        WEB3AdminPMProductCondConfigUnit[] l_substituteSecurityInfoList = null;
        WEB3AdminPMProductCondConfigUnit[] l_priceInfoList = null;
        TradingSystem l_tradingSystem = null;
        ProductManager l_productManager = null;
        Product l_product = null;
        Institution l_institution = null;
        Branch l_branch = null;
        ProductParams l_productParams = null;
        ProductRow l_productRow = null;
        String l_strBranchCode = null;
        String l_strProductCode = null;
        String l_strInstitutionCode = null;
        String l_strMstkDiv = null;
        String l_strLargeItemDiv = null;
        String[] l_strMarketCodeList = null;
        HashMap l_productCondConfParamsList = null;
        HashMap l_hmTradedProductToday = null;
        HashMap l_hmTradedProductNextDay = null;
        HashMap l_hmTradedProductTwoDaysLater = null;
        Timestamp l_tsCurrentDate = null;
        Date l_datBizDate = null;
        Timestamp l_tsBizDate = null;
        Timestamp l_tsNextBizDate = null;
        Timestamp l_tsNext2BizDate = null;
        long l_lngProductId = 0L;
        int l_intLargeItemDivSize = 0;
        String l_strMarginSysDiv = null;
        String l_strMarginGenDiv = null;
        boolean l_isUpdate = false;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        // Step 1.1
        l_request.validate();

        // Step 1.2
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // Step 1.3
        l_isUpdate = true;
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.EQTYPE_PRODUCT_SETTING, l_isUpdate);

        // Step 1.4
        l_institution = l_administrator.getInstitution();

        // Step 1.5
        l_strBranchCode = l_administrator.getBranchCode();

        // Step 1.6
        l_accManager = (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        // Used l_institution instead of l_strInstitutionCode
        l_branch = l_accManager.getBranch(l_institution, l_strBranchCode);

        // Step 1.7
        l_equityProductManager = (WEB3EquityProductManager) l_tradingModule.getProductManager();
        l_strProductCode = l_request.productCode;
        try
        {
            l_equityProduct =
                (WEB3EquityProduct) l_equityProductManager.getProduct(
                    l_institution,
                    l_strProductCode);
        } catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage());
            String l_strMsg = "product code error(no corresponding product)";
            log.error(l_strMsg, l_ex);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00391,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        EqtypeProductRow l_eqtypeProductRow =
            (EqtypeProductRow)l_equityProduct.getDataSourceObject();
        log.debug("***** ����ID:[" + l_equityProduct.getProductId()
            + "] �����R�[�h:[" + l_equityProduct.getProductCode()
            + "] ������:[" + l_eqtypeProductRow.getStandardName() + "] *****");

        // Step 1.8
        l_lngProductId = l_equityProduct.getProductId();
        l_productManager = (ProductManager) l_tradingModule.getProductManager();
        l_product = (Product) l_productManager.getProduct(l_lngProductId);
        l_productRow = ProductDao.findRowByPk(l_product.getProductId());
        l_productParams = new ProductParams(l_productRow);

        // Step 1.9
        l_strInstitutionCode = l_institution.getInstitutionCode();
        l_hmTradedProductToday =
            l_equityProductManager.getEqtypeTradedProducts(
                l_lngProductId,
                l_strInstitutionCode,
                WEB3BizDateCalcParameterDef.DAY_BIZ_DATE);

        // Step 1.10
        l_hmTradedProductNextDay =
            l_equityProductManager.getEqtypeTradedProducts(
                l_lngProductId,
                l_strInstitutionCode,
                WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);

        // Step 1.11
        l_strMstkDiv = ((BranchParams) l_branch.getDataSourceObject()).getMstkDiv();
        if ((WEB3EnforcementDef.ENFORCEMENT).equals(l_strMstkDiv))
        {
            // Step 1.11.1
            l_hmTradedProductTwoDaysLater =
                l_equityProductManager.getEqtypeTradedProducts(
                    l_lngProductId,
                    l_strInstitutionCode,
                    WEB3BizDateCalcParameterDef.NEXT_TWO_BIZ_DATE);
        }

        // Step 1.12
        l_strMarketCodeList =
            this.createListedMarketCodeList(l_hmTradedProductToday, l_hmTradedProductNextDay);

        // Step 1.13
        l_productCondConfParamsList =
            this.getProductCondConfParamsList(l_strInstitutionCode, l_strProductCode);

        // Step 1.14
        l_intLargeItemDivSize = l_request.largeItemList.length;
        for (int i = 0; i < l_intLargeItemDivSize; i++)
        {
            // Step 1.14.1
            l_strLargeItemDiv = l_request.largeItemList[i];
            if ((WEB3AdminEquityLargeItemDivDef.TRADING_REGULATION).equals(l_strLargeItemDiv))
            {
                // Step 1.14.1.1
                l_tradingRegulationList =
                    this.createTradingRegulationList(
                        (WEB3GentradeBranch) l_branch,
                        l_strMarketCodeList,
                        l_equityProduct,
                        l_hmTradedProductToday,
                        l_hmTradedProductNextDay,
                        l_hmTradedProductTwoDaysLater,
                        l_productCondConfParamsList);
            }

            // Step 1.14.2
            if ((WEB3AdminEquityLargeItemDivDef.BASIC_INFO).equals(l_strLargeItemDiv))
            {
                // Step 1.14.2.1
                l_basicInfoList =
                    this.createBasicInfoList(
                        (WEB3GentradeBranch) l_branch,
                        l_strMarketCodeList,
                        l_productParams,
                        l_equityProduct,
                        l_hmTradedProductToday,
                        l_hmTradedProductNextDay,
                        l_hmTradedProductTwoDaysLater,
                        l_productCondConfParamsList);
            }

            // Step 1.14.3
            if ((WEB3AdminEquityLargeItemDivDef.MARGIN_PRODUCT_INFO).equals(l_strLargeItemDiv))
            {
                // Step 1.14.3.1
                l_stockMarginInfoList =
                    this.createStockMarginInfoList(
                        (WEB3GentradeBranch) l_branch,
                        l_strMarketCodeList,
                        l_hmTradedProductToday,
                        l_hmTradedProductNextDay,
                        l_productCondConfParamsList);
            }

            // Step 1.14.4
            if ((WEB3AdminEquityLargeItemDivDef.DEPOSIT_RATE).equals(l_strLargeItemDiv))
            {
                // Step 1.14.4.1
                l_depositRateList =
                    this.createDepositRateList(
                        l_strMarketCodeList,
                        l_hmTradedProductToday,
                        l_hmTradedProductNextDay,
                        l_productCondConfParamsList);
            }

            // Step 1.14.5
            if ((WEB3AdminEquityLargeItemDivDef.SUBSTITUTE_SECURITY_INFO)
                .equals(l_strLargeItemDiv))
            {
                // Step 1.14.5.1

                l_substituteSecurityInfoList =
                    this.createSubstituteSecurityInfoList(
                        l_branch,
                        l_productParams,
                        l_productCondConfParamsList);
            }

            // Step 1.14.6
            if ((WEB3AdminEquityLargeItemDivDef.PRICE_INFO).equals(l_strLargeItemDiv))
            {
                // Step 1.14.6.1
                l_priceInfoList =
                    this.createPriceInfoList(
                        (WEB3GentradeBranch) l_branch,
                        l_strMarketCodeList,
                        l_productParams,
                        l_hmTradedProductToday,
                        l_hmTradedProductNextDay,
                        l_productCondConfParamsList);
            }
        }

        // Step 1.15
        l_response = (WEB3AdminPMProductCondConfInputResponse) l_request.createResponse();

        // Step 1.16
        l_tsCurrentDate =
            (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        l_tradingSystem = l_finApp.getTradingSystem();
        l_datBizDate = l_tradingSystem.getBizDate();
        l_tsBizDate = new Timestamp(l_datBizDate.getTime());
        l_gentradeBizDate = new WEB3GentradeBizDate(l_tsBizDate);
        l_tsNextBizDate = l_gentradeBizDate.roll(1);
        l_tsNext2BizDate = l_gentradeBizDate.roll(2);

        l_strMarginSysDiv = ((BranchParams) l_branch.getDataSourceObject()).getMarginSysDiv();
        l_strMarginGenDiv = ((BranchParams) l_branch.getDataSourceObject()).getMarginGenDiv();
        l_strMstkDiv = null;
        l_strMstkDiv = ((BranchParams) l_branch.getDataSourceObject()).getMstkDiv();

        l_response.currentDate = l_tsCurrentDate;
        l_response.bizDate = l_tsBizDate;
        l_response.nextBizDate = l_tsNextBizDate;
        l_response.next2BizDate = l_tsNext2BizDate;
        l_response.productCode = l_equityProduct.getProductCode();
        l_response.productName = l_eqtypeProductRow.getStandardName();
        l_response.listingCodeList = l_strMarketCodeList;

        if (WEB3EnforcementDef.ENFORCEMENT.equals(l_strMarginSysDiv))
        {
            l_response.marketMarginFlag = true;
        } else
        {
            l_response.marketMarginFlag = false;
        }
        if (WEB3EnforcementDef.ENFORCEMENT.equals(l_strMarginGenDiv))
        {
            l_response.institutionMarginFlag = true;
        } else
        {
            l_response.institutionMarginFlag = false;
        }
        if (WEB3EnforcementDef.ENFORCEMENT.equals(l_strMstkDiv))
        {
            l_response.miniFlag = true;
        } else
        {
            l_response.miniFlag = false;
        }

        l_response.tradingRegulationList = l_tradingRegulationList;
        l_response.basicInfoList = l_basicInfoList;
        l_response.stockMarginInfoList = l_stockMarginInfoList;
        l_response.depositRateList = l_depositRateList;
        l_response.substituteSecurityInfoList = l_substituteSecurityInfoList;
        l_response.priceInfoList = l_priceInfoList;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * �ivalidate�����ݒ�j<BR>
     * <BR>
     * �������������ݒ�m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��Ҋ������������ݒ�T�[�r�X)validate�����ݒ�v<BR>
     * �Q��<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * validateConditionSetting<BR>
     * <BR>
     * Execute WEB3AdminEquityProductCondSettingService validate process<BR>
     * <BR>
     * Refer to the sequence diagram "(administrator: equity product condition setting
     * service)validateConditionSetting"<BR>
     * <BR>
     * @@param l_request - �i���N�G�X�g�f�[�^�j<BR>
     * <BR>
     * �Ǘ��ҁE�������������ݒ�m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminPMProductCondConfConfirmRequest object<BR>
     * <BR>
     * @@return eqtypeadmin.message.WEB3AdminPMProductCondConfConfirmResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws NotFoundException NotFoundException
     * @@throws DataNetworkException DataNetworkException
     * @@throws DataQueryException DataQueryException
     * @@roseuid 417F9170000C
     */
    protected WEB3AdminPMProductCondConfConfirmResponse validateConditionSetting(
        WEB3AdminPMProductCondConfConfirmRequest l_request)
        throws WEB3BaseException, NotFoundException, DataNetworkException, DataQueryException
    {
        final String STR_METHOD_NAME =
            "validateConditionSetting(WEB3AdminPMProductCondConfConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3Administrator l_administrator = null;
        WEB3GentradeAccountManager l_accManager = null;
        WEB3EquityProductManager l_equityProductManager = null;
        WEB3EquityProduct l_equityProduct = null;
        WEB3AdminPMProductCondConfConfirmResponse l_response = null;
        EqtypeTradedProductParams l_tradedProductToday = null;
        EqtypeTradedProductParams l_tradedProductNextDay = null;
        EqtypeTradedProductParams l_tradedProductTwoDaysLater = null;
        EqtypeProductConditionParams l_eqtypeProductConditionParams = null;
        EqtypeTradedProductParams[] l_eqtypeTradedProductParams = null;
        ProductManager l_productManager = null;
        Product l_product = null;
        Institution l_institution = null;
        Branch l_branch = null;
        ProductParams l_productParams = null;
        ProductRow l_productRow = null;
        String l_strBranchCode = null;
        String l_strProductCode = null;
        String l_strInstitutionCode = null;
        String l_strMstkDiv = null;
        String l_strSmallItemDiv = null;
        String l_strMarketCode = null;
        String l_strHashKey = null;
//        String[] l_strMarketCodeList = null;
        HashMap l_productCondConfParamsList = null;
        HashMap l_hmTradedProductToday = null;
        HashMap l_hmTradedProductNextDay = null;
        HashMap l_hmTradedProductTwoDaysLater = null;
        List l_lisParams = null;
        Timestamp l_tsCurrentDate = null;
        long l_lngProductId = 0L;
        int l_intSize = 0;
        boolean l_isUpdate = true;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        // Step 1.1
        l_request.validate();

        // Step 1.2
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // Step 1.3
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.EQTYPE_PRODUCT_SETTING, l_isUpdate);

        // Step 1.4
        l_institution = l_administrator.getInstitution();

        // Step 1.5
        l_strBranchCode = l_administrator.getBranchCode();

        // Step 1.6
        l_accManager = (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        // Used l_institution instead of l_strInstitutionCode
        l_branch = l_accManager.getBranch(l_institution, l_strBranchCode);

        // Step 1.7
        l_equityProductManager = (WEB3EquityProductManager) l_tradingModule.getProductManager();
        l_strProductCode = l_request.productCode;
        try
        {
            l_equityProduct =
                (WEB3EquityProduct) l_equityProductManager.getProduct(
                    l_institution,
                    l_strProductCode);
        } catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage());
            String l_strMsg = "product code error(no corresponding product)";
            log.error(l_strMsg, l_ex);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00391,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        EqtypeProductRow l_eqtypeProductRow =
            (EqtypeProductRow)l_equityProduct.getDataSourceObject();
        log.debug("***** ����ID:[" + l_equityProduct.getProductId()
            + "] �����R�[�h:[" + l_equityProduct.getProductCode()
            + "] ������:[" + l_eqtypeProductRow.getStandardName() + "] *****");
            
        // Step 1.8
        l_lngProductId = l_equityProduct.getProductId();
        l_productManager = (ProductManager) l_tradingModule.getProductManager();
        l_product = (Product) l_productManager.getProduct(l_lngProductId);
        l_productRow = ProductDao.findRowByPk(l_product.getProductId());
        l_productParams = new ProductParams(l_productRow);

        // Step 1.9
        l_strInstitutionCode = l_institution.getInstitutionCode();
        l_hmTradedProductToday =
            l_equityProductManager.getEqtypeTradedProducts(
                l_lngProductId,
                l_strInstitutionCode,
                WEB3BizDateCalcParameterDef.DAY_BIZ_DATE);

        // Step 1.10
        l_hmTradedProductNextDay =
            l_equityProductManager.getEqtypeTradedProducts(
                l_lngProductId,
                l_strInstitutionCode,
                WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);

        // Step 1.11
        l_strMstkDiv = ((BranchParams) l_branch.getDataSourceObject()).getMstkDiv();
        if ((WEB3EnforcementDef.ENFORCEMENT).equals(l_strMstkDiv))
        {
            // Step 1.11.1
            l_hmTradedProductTwoDaysLater =
                l_equityProductManager.getEqtypeTradedProducts(
                    l_lngProductId,
                    l_strInstitutionCode,
                    WEB3BizDateCalcParameterDef.NEXT_TWO_BIZ_DATE);
        }

        // Step 1.12
        // The return value of createListedMarketCodeList() method is never used,
        // so, commented below two lines
        // l_strMarketCodeList =
        // this.createListedMarketCodeList(l_hmTradedProductToday, l_hmTradedProductNextDay);

        // Step 1.13
        l_productCondConfParamsList =
            this.getProductCondConfParamsList(l_strInstitutionCode, l_strProductCode);

        // Step 1.14
        if (l_request.tradingRegulationList != null)
        {
            // Step 1.14.1
            l_intSize = l_request.tradingRegulationList.length;
            for (int i = 0; i < l_intSize; i++)
            {
                // Step 1.14.1.1
                l_strMarketCode = l_request.tradingRegulationList[i].marketCode;
                l_strSmallItemDiv = l_request.tradingRegulationList[i].smallItemDiv;
                l_strHashKey = this.getKey(l_strMarketCode, l_strSmallItemDiv);
                l_eqtypeProductConditionParams =
                    (EqtypeProductConditionParams) l_productCondConfParamsList.get(l_strHashKey);
                if ((WEB3AdminEquitySmallItemDivDef.BUY_MINI_STOCK_STOP).equals(l_strSmallItemDiv)
                    || (WEB3AdminEquitySmallItemDivDef.SELL_MINI_STOCK_STOP).equals(l_strSmallItemDiv))
                {
                    // Step 1.14.1.1.1
                    if (l_hmTradedProductNextDay != null)
                    {
                        l_tradedProductNextDay =
                            (EqtypeTradedProductParams) l_hmTradedProductNextDay.get(
                                l_strMarketCode);
                    } else
                    {
                        l_tradedProductNextDay = null;
                    }
                    
                    if (l_hmTradedProductTwoDaysLater != null)
                    {
                        l_tradedProductTwoDaysLater =
                            (EqtypeTradedProductParams) l_hmTradedProductTwoDaysLater.get(
                                l_strMarketCode);
                    } else
                    {
                        l_tradedProductTwoDaysLater = null;
                    }
                    
                    if (l_tradedProductNextDay != null
                        && l_tradedProductNextDay.getMiniStockCanDealt().intValue() == 0)
                    {
                        l_tradedProductNextDay = null;
                    }
                    
                    if (l_tradedProductTwoDaysLater != null
                        && l_tradedProductTwoDaysLater.getMiniStockCanDealt().intValue() == 0)
                    {
                        l_tradedProductTwoDaysLater = null;
                    }
                    
                    this.setLatestData(
                        l_request.tradingRegulationList[i],
                        null,
                        l_equityProduct,
                        l_tradedProductNextDay,
                        l_tradedProductTwoDaysLater,
                        l_eqtypeProductConditionParams);
                // Step 1.14.1.2
                } else
                {
                    // Step 1.14.1.2.1
                    if (l_hmTradedProductToday != null)
                    {
                        l_tradedProductToday =
                            (EqtypeTradedProductParams) l_hmTradedProductToday.get(l_strMarketCode);
                    } else
                    {
                        l_tradedProductToday = null;
                    }
                    if (l_hmTradedProductNextDay != null)
                    {
                        l_tradedProductNextDay =
                            (EqtypeTradedProductParams) l_hmTradedProductNextDay.get(
                                l_strMarketCode);
                    } else
                    {
                        l_tradedProductNextDay = null;
                    }
                    this.setLatestData(
                        l_request.tradingRegulationList[i],
                        null,
                        l_equityProduct,
                        l_tradedProductToday,
                        l_tradedProductNextDay,
                        l_eqtypeProductConditionParams);
                }
            }
        }

        // Step 1.15
        if (l_request.basicInfoList != null)
        {
            // Step 1.15.1
            l_intSize = l_request.basicInfoList.length;
            for (int i = 0; i < l_intSize; i++)
            {
                l_strSmallItemDiv = l_request.basicInfoList[i].smallItemDiv;
                l_strMarketCode = l_request.basicInfoList[i].marketCode;
                l_strHashKey = this.getKey(l_strMarketCode, l_strSmallItemDiv);
                l_eqtypeProductConditionParams =
                    (EqtypeProductConditionParams) l_productCondConfParamsList.get(l_strHashKey);

                // Step 1.15.1.1
                if ((WEB3AdminEquitySmallItemDivDef.MINI_STOCK_MARKET).equals(l_strSmallItemDiv))
                {
                    if (l_hmTradedProductNextDay != null)
                    {
                        l_lisParams = new ArrayList(l_hmTradedProductNextDay.values());
                        l_eqtypeTradedProductParams =
                            new EqtypeTradedProductParams[l_lisParams.size()];
                        l_eqtypeTradedProductParams = (EqtypeTradedProductParams[])
                            l_lisParams.toArray(l_eqtypeTradedProductParams);
                        // Step 1.15.1.1.1
                        l_tradedProductNextDay = l_equityProductManager.
                            getMiniStockTradedProduct(l_eqtypeTradedProductParams);
                    } else
                    {
                        l_tradedProductNextDay = null;
                    }

                    if (l_hmTradedProductTwoDaysLater != null)
                    {
                        l_lisParams = new ArrayList(l_hmTradedProductTwoDaysLater.values());
                        l_eqtypeTradedProductParams =
                            new EqtypeTradedProductParams[l_lisParams.size()];
                        l_eqtypeTradedProductParams = (EqtypeTradedProductParams[])
                            l_lisParams.toArray(l_eqtypeTradedProductParams);
                        // Step 1.15.1.1.2
                        l_tradedProductTwoDaysLater = l_equityProductManager.
                            getMiniStockTradedProduct(l_eqtypeTradedProductParams);
                    } else
                    {
                        l_tradedProductTwoDaysLater = null;
                    }

                    // Step 1.15.1.1.3
                    this.setLatestData(
                        l_request.basicInfoList[i],
                        null,
                        null,
                        l_tradedProductNextDay,
                        l_tradedProductTwoDaysLater,
                        l_eqtypeProductConditionParams);
    
                    // �~�j����戵�`�F�b�N
                    if (l_hmTradedProductNextDay != null)
                    {
	                    if (l_request.basicInfoList[i].bizDateRegistData == null)
	                    {
	                        l_request.basicInfoList[i].bizDateRegistData = String.valueOf(BooleanEnum.FALSE.intValue());
	                    }
	                    if (l_request.basicInfoList[i].aftBizDateRegistData == null)
	                    {
	                        l_request.basicInfoList[i].aftBizDateRegistData = String.valueOf(BooleanEnum.FALSE.intValue());
	                    }
                    }
                
                    if (l_hmTradedProductTwoDaysLater != null)
                    {
	                    if (l_request.basicInfoList[i].nextBizDateRegistData == null)
	                    {
	                        l_request.basicInfoList[i].nextBizDateRegistData = String.valueOf(BooleanEnum.FALSE.intValue());
	                    }
	                    if (l_request.basicInfoList[i].aftNextBizDateRegistData == null)
	                    {
	                        l_request.basicInfoList[i].aftNextBizDateRegistData = String.valueOf(BooleanEnum.FALSE.intValue());
	                    }
                    }
                
                    // Step 1.15.1.2
                } else if ((WEB3AdminEquitySmallItemDivDef.LOT_SIZE).equals(l_strSmallItemDiv))
                {
                    l_tradedProductToday = null;
                    l_tradedProductNextDay = null;
                    if (l_hmTradedProductToday != null)
                    {
                        l_lisParams = new ArrayList(l_hmTradedProductToday.values());
                        l_tradedProductToday = (EqtypeTradedProductParams) l_lisParams.get(0);
                    }
                    if (l_hmTradedProductNextDay != null)
                    {
                        l_lisParams = new ArrayList(l_hmTradedProductNextDay.values());
                        l_tradedProductNextDay = (EqtypeTradedProductParams) l_lisParams.get(0);
                    }
                    // Step 1.15.1.2.1
                    this.setLatestData(
                        l_request.basicInfoList[i],
                        null,
                        null,
                        l_tradedProductToday,
                        l_tradedProductNextDay,
                        l_eqtypeProductConditionParams);
                // Step 1.15.1.3
                } else
                {
                    if (l_hmTradedProductToday != null)
                    {
                        l_tradedProductToday =
                            (EqtypeTradedProductParams) l_hmTradedProductToday.get(l_strMarketCode);
                    } else
                    {
                        l_tradedProductToday = null;
                    }
                    if (l_hmTradedProductNextDay != null)
                    {
                        l_tradedProductNextDay =
                            (EqtypeTradedProductParams) l_hmTradedProductNextDay.get(
                                l_strMarketCode);
                    } else
                    {
                        l_tradedProductNextDay = null;
                    }

                    // Step 1.15.1.3.1
                    this.setLatestData(
                        l_request.basicInfoList[i],
                        l_productParams,
                        l_equityProduct,
                        l_tradedProductToday,
                        l_tradedProductNextDay,
                        l_eqtypeProductConditionParams);
                }
            }
        }

        // validateConditionSetting2
        // Step 1.2
        if (l_request.stockMarginInfoList != null)
        {
            l_intSize = l_request.stockMarginInfoList.length;
            // Step 1.2.1
            for (int i = 0; i < l_intSize; i++)
            {
                // Step 1.2.1.1
                l_strSmallItemDiv = l_request.stockMarginInfoList[i].smallItemDiv;
                l_strMarketCode = l_request.stockMarginInfoList[i].marketCode;
                l_strHashKey = this.getKey(l_strMarketCode, l_strSmallItemDiv);
                l_eqtypeProductConditionParams =
                    (EqtypeProductConditionParams) l_productCondConfParamsList.get(l_strHashKey);
                if (l_hmTradedProductToday != null)
                {
                    l_tradedProductToday =
                        (EqtypeTradedProductParams) l_hmTradedProductToday.get(l_strMarketCode);
                } else
                {
                    l_tradedProductToday = null;
                }
                if (l_hmTradedProductNextDay != null)
                {
                    l_tradedProductNextDay =
                        (EqtypeTradedProductParams) l_hmTradedProductNextDay.get(l_strMarketCode);
                } else
                {
                    l_tradedProductNextDay = null;
                }
                this.setLatestData(
                    l_request.stockMarginInfoList[i],
                    null,
                    null,
                    l_tradedProductToday,
                    l_tradedProductNextDay,
                    l_eqtypeProductConditionParams);
            }
        }

        // Step 1.3
        if (l_request.depositRateList != null)
        {
            l_intSize = l_request.depositRateList.length;
            // Step 1.3.1
            for (int i = 0; i < l_intSize; i++)
            {
                // Step 1.3.1.1
                l_strSmallItemDiv = l_request.depositRateList[i].smallItemDiv;
                l_strMarketCode = l_request.depositRateList[i].marketCode;
                l_strHashKey = this.getKey(l_strMarketCode, l_strSmallItemDiv);
                l_eqtypeProductConditionParams =
                    (EqtypeProductConditionParams) l_productCondConfParamsList.get(l_strHashKey);
                if (l_hmTradedProductToday != null)
                {
                    l_tradedProductToday =
                        (EqtypeTradedProductParams) l_hmTradedProductToday.get(l_strMarketCode);
                } else
                {
                    l_tradedProductToday = null;
                }
                if (l_hmTradedProductNextDay != null)
                {
                    l_tradedProductNextDay =
                        (EqtypeTradedProductParams) l_hmTradedProductNextDay.get(l_strMarketCode);
                } else
                {
                    l_tradedProductNextDay = null;
                }
                this.setLatestData(
                    l_request.depositRateList[i],
                    null,
                    null,
                    l_tradedProductToday,
                    l_tradedProductNextDay,
                    l_eqtypeProductConditionParams);
            }
        }

        // Step 1.4
        if (l_request.substituteSecurityInfoList != null)
        {
            l_intSize = l_request.substituteSecurityInfoList.length;
            // Step 1.4.1
            for (int i = 0; i < l_intSize; i++)
            {
                // Step 1.4.1.1
                l_strSmallItemDiv = l_request.substituteSecurityInfoList[i].smallItemDiv;
                l_strMarketCode = l_request.substituteSecurityInfoList[i].marketCode;
                l_strHashKey = this.getKey(l_strMarketCode, l_strSmallItemDiv);
                l_eqtypeProductConditionParams =
                    (EqtypeProductConditionParams) l_productCondConfParamsList.get(l_strHashKey);
                l_tradedProductToday = null;
                l_tradedProductNextDay = null;
                this.setLatestData(
                    l_request.substituteSecurityInfoList[i],
                    l_productParams,
                    null,
                    l_tradedProductToday,
                    l_tradedProductNextDay,
                    l_eqtypeProductConditionParams);
            }
        }

        // Step 1.5
        if (l_request.priceInfoList != null)
        {
            l_intSize = l_request.priceInfoList.length;
            // Step 1.5.1
            for (int i = 0; i < l_intSize; i++)
            {
                // Step 1.5.1.1
                l_strSmallItemDiv = l_request.priceInfoList[i].smallItemDiv;
                l_strMarketCode = l_request.priceInfoList[i].marketCode;
                l_strHashKey = this.getKey(l_strMarketCode, l_strSmallItemDiv);
                l_eqtypeProductConditionParams =
                    (EqtypeProductConditionParams) l_productCondConfParamsList.get(l_strHashKey);
                if (l_hmTradedProductToday != null)
                {
                    l_tradedProductToday =
                        (EqtypeTradedProductParams) l_hmTradedProductToday.get(l_strMarketCode);
                } else
                {
                    l_tradedProductToday = null;
                }
                if (l_hmTradedProductNextDay != null)
                {
                    l_tradedProductNextDay =
                        (EqtypeTradedProductParams) l_hmTradedProductNextDay.get(l_strMarketCode);
                } else
                {
                    l_tradedProductNextDay = null;
                }
                this.setLatestData(
                    l_request.priceInfoList[i],
                    l_productParams,
                    null,
                    l_tradedProductToday,
                    l_tradedProductNextDay,
                    l_eqtypeProductConditionParams);
            }
        }

        // Step 1.6
        l_response = (WEB3AdminPMProductCondConfConfirmResponse) l_request.createResponse();

        // Step 1.7
        l_tsCurrentDate =
            (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        l_response.currentDate = l_tsCurrentDate;
        l_response.tradingRegulationList = l_request.tradingRegulationList;
        l_response.basicInfoList = l_request.basicInfoList;
        l_response.stockMarginInfoList = l_request.stockMarginInfoList;
        l_response.depositRateList = l_request.depositRateList;
        l_response.substituteSecurityInfoList = l_request.substituteSecurityInfoList;
        l_response.priceInfoList = l_request.priceInfoList;

        log.exiting(STR_METHOD_NAME);

        // Step 1.8
        return l_response;
    }

    /**
     * �isubmit�����ݒ�j<BR>
     * �������������ݒ芮���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��Ҋ������������ݒ�T�[�r�X)submit�����ݒ�v<BR>
     * �Q��<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * submitConditionSetting<BR>
     * <BR>
     * Execute WEB3AdminEquityProductCondSettingService submit process<BR>
     * <BR>
     * Refer to the sequence diagram "(administrator: equity product condition setting
     * service)submitConditionSetting"<BR>
     * <BR>
     * @@param l_request - �i���N�G�X�g�f�[�^�j<BR>
     * <BR>
     * �Ǘ��ҁE�������������ݒ芮�����N�G�X�g�I�u�W�F�N�g<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminPMProductCondConfCompleteRequest object<BR>
     * <BR>
     * @@return eqtypeadmin.message.WEB3AdminPMProductCondConfCompleteResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws NotFoundException NotFoundException
     * @@throws DataNetworkException DataNetworkException
     * @@throws DataQueryException DataQueryException
     * @@roseuid 417F9170001B
     */
    protected WEB3AdminPMProductCondConfCompleteResponse submitConditionSetting(
        WEB3AdminPMProductCondConfCompleteRequest l_request)
        throws WEB3BaseException, NotFoundException, DataNetworkException, DataQueryException
    {
        final String STR_METHOD_NAME =
            "submitConditionSetting(" + "WEB3AdminPMProductCondConfCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        String l_strPassword = l_request.password;
        String l_strBranchCode = null;
        String l_strAdministratorCode = null;
        String l_strProductCode = l_request.productCode;
        long l_lngProductId = 0;
        int l_intProductCondConfigUnitInfoCnt = 0;
        String l_strMstkDiv = null;
        int l_intTradedProductUpdateInfoCnt = 0;
        boolean l_isUpdate = false;
        String l_strKey = null;
        String l_strMarketCode = null;
        String l_strInstitutionCode = null;
        String l_strSmallItemDiv = null;
        String l_strBizDate = null;
        int l_intEquityProductConditionCnt = 0;

        WEB3Administrator l_administrator = null;
        Institution l_institution = null;
        WEB3GentradeAccountManager l_genTradeAccountManager = null;
        WEB3EquityProductManager l_equityProductManager = null;
        ProductManager l_productManager = null;
        WEB3EquityProduct l_equityProduct = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        Branch l_branch = null;
        BranchParams l_branchParams = null;
        WEB3AdminPMProductCondConfigUnit[] l_adminProductCondConfigUnitList = null;
        WEB3AdminPMProductCondConfigUnit l_productCondConfigUnit = null;
        WEB3AdminPMProductCondSettingUpdateInfo l_productCondSettingUpdateInfo = null;
        Product l_product = null;
        ProductParams l_productParams = null;
        ProductRow l_productRow = null;
        EqtypeTradedProductParams l_tradedProductToday = null;
        EqtypeTradedProductParams l_tradedProductNextDay = null;
        EqtypeTradedProductParams l_tradedProductTwoDay = null;
        EqtypeTradedProductParams[] l_eqtypeTradedProductParams = null;
        EqtypeProductConditionParams l_eqtypeProductConditionParams = null;
        HashMap l_eqityProductConditionParams = null;
        HashMap l_hmTradedProductToday = null;
        HashMap l_hmTradedProductNextDay = null;
        HashMap l_hmTradedProductTwoDaysLater = null;
        WEB3AdminPMProductCondConfigUnit l_productConditionInfo = null;
        WEB3AdminPMEquityDataManager l_equityDataManager = new WEB3AdminPMEquityDataManager();
        WEB3AdminPMProductCondConfCompleteResponse l_response = null;
        WEB3AdminPMUpdateInfo l_productUpdateInfo = null;
        WEB3AdminPMUpdateInfo l_equityProductUpdateInfo = null;
        HashMap l_hmChange = null;
        ArrayList l_lisEqtypeProductConditionParams = null;
        List l_lisParams = null;
        WEB3AdminPMUpdateInfo[] l_tradedProductNextUpdateInfoList = null;
        Timestamp l_currentTime = null;
        long l_lngTradedProductId = 0L;
        HashMap l_hmChangeMap = null;
        WEB3AdminPMUpdateInfo[] l_tradedProductUpdateInfoList = null;
        WEB3AdminPMUpdateInfo[] l_tradedProductNext2UpdateInfoList = null;
        l_genTradeAccountManager = (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        l_equityProductManager = (WEB3EquityProductManager) l_tradingModule.getProductManager();

        // Step 1.1
        l_request.validate();

        // Step 1.2
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // Step 1.3
        l_isUpdate = true;
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.EQTYPE_PRODUCT_SETTING, l_isUpdate);

        // Step 1.4
        l_administrator.validateTradingPassword(l_strPassword);

        // Step 1.5
        l_institution = l_administrator.getInstitution();

        // Step 1.6
        l_strBranchCode = l_administrator.getBranchCode();

        // Step 1.7
        l_strAdministratorCode = l_administrator.getAdministratorCode();

        /* 
          * Step 1.8
          */
        l_branch = l_genTradeAccountManager.getBranch(l_institution, l_strBranchCode);
        l_branchParams = (BranchParams) l_branch.getDataSourceObject();

        // Step 1.9
        try
        {
            l_equityProduct =
                (WEB3EquityProduct) l_equityProductManager.getProduct(
                    l_institution,
                    l_strProductCode);

        } catch (NotFoundException l_nExp)
        {
            String l_strErrorMsg = "No data for the product";
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00391,
                STR_METHOD_NAME,
                l_strErrorMsg);
        }
        EqtypeProductRow l_eqtypeProductRow =
            (EqtypeProductRow)l_equityProduct.getDataSourceObject();
        log.debug("***** ����ID:[" + l_equityProduct.getProductId()
            + "] �����R�[�h:[" + l_equityProduct.getProductCode()
            + "] ������:[" + l_eqtypeProductRow.getStandardName() + "] *****");
            
        // Step 1.10
        l_lngProductId = l_equityProduct.getProductId();
        l_productManager = l_tradingModule.getProductManager();
        l_product = (Product) l_productManager.getProduct(l_lngProductId);
        l_productRow = ProductDao.findRowByPk(l_product.getProductId());
        l_productParams = new ProductParams(l_productRow);

        // Step 1.11
        l_strInstitutionCode = l_institution.getInstitutionCode();

        l_hmTradedProductToday =
            l_equityProductManager.getEqtypeTradedProducts(
                l_lngProductId,
                l_strInstitutionCode,
                WEB3BizDateCalcParameterDef.DAY_BIZ_DATE);

        // Step 1.12
        l_hmTradedProductNextDay =
            l_equityProductManager.getEqtypeTradedProducts(
                l_lngProductId,
                l_strInstitutionCode,
                WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);

        // Step 1.13
        l_strMstkDiv = l_branchParams.getMstkDiv();

        // Checks mini stock is eaual to company ENFORNCEMENT
        if (WEB3EnforcementDef.ENFORCEMENT.equals(l_strMstkDiv))
        {
            //Step 1.13.1
            l_hmTradedProductTwoDaysLater =
                l_equityProductManager.getEqtypeTradedProducts(
                    l_lngProductId,
                    l_strInstitutionCode,
                    WEB3BizDateCalcParameterDef.NEXT_TWO_BIZ_DATE);
        }

        // Step 1.14
        l_eqityProductConditionParams =
            this.getProductCondConfParamsList(l_strInstitutionCode, l_strProductCode);

        // Step 1.15
        l_productCondSettingUpdateInfo = new WEB3AdminPMProductCondSettingUpdateInfo();
        l_productCondSettingUpdateInfo.tradeProductUpdateInfo = new HashMap();
        l_productCondSettingUpdateInfo.tradeProductNextUpdateInfo = new HashMap();
        l_productCondSettingUpdateInfo.tradeProductNext2UpdateInfo = new HashMap();
        l_productCondSettingUpdateInfo.scheduleUpdateInfo = new ArrayList();

        // Step 1.16
        //l_tradingRegulationList is not null
        if (l_request.tradingRegulationList != null)
        {
            // Step 1.16.1
            l_intProductCondConfigUnitInfoCnt = l_request.tradingRegulationList.length;
            for (int i = 0; i < l_intProductCondConfigUnitInfoCnt; i++)
            {
                l_productCondConfigUnit = l_request.tradingRegulationList[i];
                l_strMarketCode = l_productCondConfigUnit.marketCode;
                l_strSmallItemDiv = l_productCondConfigUnit.smallItemDiv;

                l_strKey = this.getKey(l_strMarketCode, l_strSmallItemDiv);
                l_eqtypeProductConditionParams =
                    (EqtypeProductConditionParams) l_eqityProductConditionParams.get(l_strKey);

                // Step 1.16.1.1
                // Check smallItemDiv is equal to Buy_MINI_STOCK or SELL_MINI_STOCK_STOP
                if ((WEB3AdminEquitySmallItemDivDef.BUY_MINI_STOCK_STOP.equals(l_strSmallItemDiv))
                    || (WEB3AdminEquitySmallItemDivDef.SELL_MINI_STOCK_STOP.equals(
                    l_strSmallItemDiv)))
                {

                    // Step 1.16.1.1.1
                    if (l_hmTradedProductNextDay != null)
                    {
                        l_tradedProductToday =
                            (EqtypeTradedProductParams) l_hmTradedProductNextDay.get(
                                l_strMarketCode);
                    } else
                    {
                        l_tradedProductToday = null;
                    }

                    // (*1)
                    if (l_tradedProductToday == null
                        || l_tradedProductToday.getMiniStockCanDealt().intValue() == 0)
                    {
                        l_tradedProductToday = null;
                    }

                    if (l_hmTradedProductTwoDaysLater != null)
                    {
                        l_tradedProductNextDay =
                            (EqtypeTradedProductParams) l_hmTradedProductTwoDaysLater.get(
                                l_strMarketCode);
                    } else
                    {
                        l_tradedProductNextDay = null;
                    }

                    if (l_tradedProductNextDay == null
                        || l_tradedProductNextDay.getMiniStockCanDealt().intValue() == 0)
                    {
                        l_tradedProductNextDay = null;
                    }

                    this.setLatestData(
                        l_productCondConfigUnit,
                        null,
                        l_equityProduct,
                        l_tradedProductToday,
                        l_tradedProductNextDay,
                        l_eqtypeProductConditionParams);

                    // Step 1.16.1.1.2
                    this.setMiniStockUpdateInfo(
                        l_productCondSettingUpdateInfo,
                        l_productCondConfigUnit,
                        l_tradedProductToday,
                        l_tradedProductNextDay,
                        l_strAdministratorCode);
                } else
                {
                    if (l_hmTradedProductToday != null)
                    {
                        l_tradedProductToday =
                            (EqtypeTradedProductParams) l_hmTradedProductToday.get(l_strMarketCode);
                    } else
                    {
                        l_tradedProductToday = null;
                    }
                    if (l_hmTradedProductNextDay != null)
                    {
                        l_tradedProductNextDay =
                            (EqtypeTradedProductParams) l_hmTradedProductNextDay.get(
                                l_strMarketCode);
                    } else
                    {
                        l_tradedProductNextDay = null;
                    }

                    // Step 1.16.1.2.1
                    this.setLatestData(
                        l_productCondConfigUnit,
                        null,
                        l_equityProduct,
                        l_tradedProductToday,
                        l_tradedProductNextDay,
                        l_eqtypeProductConditionParams);

                    // Step 1.16.1.2.2
                    this.setProductUpdateInfo(
                        l_productCondSettingUpdateInfo,
                        l_productCondConfigUnit,
                        l_equityProduct,
                        l_tradedProductToday,
                        l_tradedProductNextDay,
                        l_strAdministratorCode);
                }

                // Step 1.16.1.3
                this.setScheduleUpdatedInfo(
                    l_productCondSettingUpdateInfo,
                    l_productCondConfigUnit,
                    l_equityProduct,
                    l_eqtypeProductConditionParams,
                    l_strAdministratorCode);
            }
        }

        // Step 1.17
        if (l_request.basicInfoList != null)
        {
            l_intProductCondConfigUnitInfoCnt = l_request.basicInfoList.length;

            // Step 1.17.1
            for (int j = 0; j < l_intProductCondConfigUnitInfoCnt; j++)
            {
                l_productConditionInfo = l_request.basicInfoList[j];
                l_strSmallItemDiv = l_productConditionInfo.smallItemDiv;
                l_strMarketCode = l_productConditionInfo.marketCode;

                l_strKey = this.getKey(l_strMarketCode, l_strSmallItemDiv);
                l_eqtypeProductConditionParams =
                    (EqtypeProductConditionParams) l_eqityProductConditionParams.get(l_strKey);

                // Step 1.17.1.1
                // Check smallItemDiv to Mini stock market value
                if (WEB3AdminEquitySmallItemDivDef.MINI_STOCK_MARKET.equals(l_strSmallItemDiv))
                {
                    if (l_hmTradedProductNextDay != null)
                    {
                        l_lisParams = new ArrayList(l_hmTradedProductNextDay.values());
                        l_eqtypeTradedProductParams =
                            new EqtypeTradedProductParams[l_lisParams.size()];
                        l_eqtypeTradedProductParams = (EqtypeTradedProductParams[])
                            l_lisParams.toArray(l_eqtypeTradedProductParams);
                        // Step 1.17.1.1.1
                        l_tradedProductNextDay = l_equityProductManager.
                            getMiniStockTradedProduct(l_eqtypeTradedProductParams);
                    } else
                    {
                        l_tradedProductNextDay = null;
                    }

                    if (l_hmTradedProductTwoDaysLater != null)
                    {
                        l_lisParams = new ArrayList(l_hmTradedProductTwoDaysLater.values());
                        l_eqtypeTradedProductParams =
                            new EqtypeTradedProductParams[l_lisParams.size()];
                        l_eqtypeTradedProductParams = (EqtypeTradedProductParams[])
                            l_lisParams.toArray(l_eqtypeTradedProductParams);
                        // Step 1.17.1.1.2
                        l_tradedProductTwoDay = l_equityProductManager.
                            getMiniStockTradedProduct(l_eqtypeTradedProductParams);
                    } else
                    {
                        l_tradedProductTwoDay = null;
                    }

                    // Step 1.17.1.1.3
                    this.setLatestData(
                        l_productConditionInfo,
                        null,
                        null,
                        l_tradedProductNextDay,
                        l_tradedProductTwoDay,
                        l_eqtypeProductConditionParams);
    
                    // �~�j����戵�`�F�b�N
                    if (l_hmTradedProductNextDay != null)
                    {
	                    if (l_productConditionInfo.bizDateRegistData == null)
	                    {
	                        l_productConditionInfo.bizDateRegistData = String.valueOf(BooleanEnum.FALSE.intValue());
	                    }
	                    if (l_productConditionInfo.aftBizDateRegistData == null)
                        {
	                        l_productConditionInfo.aftBizDateRegistData = String.valueOf(BooleanEnum.FALSE.intValue());
                        }
                    }
                
                    if (l_hmTradedProductTwoDaysLater != null)
                    {
	                    if (l_productConditionInfo.nextBizDateRegistData == null)
	                    {
	                        l_productConditionInfo.nextBizDateRegistData = String.valueOf(BooleanEnum.FALSE.intValue());
	                    }
	                    if (l_productConditionInfo.aftNextBizDateRegistData == null)
                        {
	                        l_productConditionInfo.aftNextBizDateRegistData = String.valueOf(BooleanEnum.FALSE.intValue());
                        }
                    }
                
                    // Step 1.17.1.1.4
                    this.setMiniStockMarketUpdateInfo(
                        l_productCondSettingUpdateInfo,
                        l_productConditionInfo,
                        l_hmTradedProductNextDay,
                        l_hmTradedProductTwoDaysLater,
                        l_strAdministratorCode);

                    // Step 1.17.1.2
                } else if (WEB3AdminEquitySmallItemDivDef.LOT_SIZE.equals(l_strSmallItemDiv))
                {
                    // Step 1.17.1.2.1
                    if (l_hmTradedProductToday != null)
                    {
                        l_lisParams = new ArrayList(l_hmTradedProductToday.values());
                        l_tradedProductToday = (EqtypeTradedProductParams) l_lisParams.get(0);
                    } else
                    {
                        l_tradedProductToday = null;
                    }

                    if (l_hmTradedProductNextDay != null)
                    {
                        l_lisParams = new ArrayList(l_hmTradedProductNextDay.values());
                        l_tradedProductNextDay = (EqtypeTradedProductParams) l_lisParams.get(0);
                    } else
                    {
                        l_tradedProductNextDay = null;
                    }
                    this.setLatestData(
                        l_productConditionInfo,
                        null,
                        null,
                        l_tradedProductToday,
                        l_tradedProductNextDay,
                        l_eqtypeProductConditionParams);

                    // Step 1.17.1.2.2
                    this.setProductUpdateInfo(
                        l_productCondSettingUpdateInfo,
                        l_productConditionInfo,
                        l_hmTradedProductToday,
                        l_hmTradedProductNextDay,
                        l_strAdministratorCode);

                    // Step 1.17.1.3
                } else
                {
                    // Step 1.17.1.3.1
                    if (l_hmTradedProductToday != null)
                    {
                        l_tradedProductToday =
                            (EqtypeTradedProductParams) l_hmTradedProductToday.get(l_strMarketCode);
                    } else
                    {
                        l_tradedProductToday = null;
                    }
                    if (l_hmTradedProductNextDay != null)
                    {
                        l_tradedProductNextDay =
                            (EqtypeTradedProductParams) l_hmTradedProductNextDay.get(
                                l_strMarketCode);
                    } else
                    {
                        l_tradedProductNextDay = null;
                    }

                    this.setLatestData(
                        l_productConditionInfo,
						l_productParams,
                        l_equityProduct,
                        l_tradedProductToday,
                        l_tradedProductNextDay,
                        l_eqtypeProductConditionParams);

                    // Step 1.17.1.3.2
                    this.setProductUpdateInfo(
                        l_productCondSettingUpdateInfo,
                        l_productConditionInfo,
                        l_equityProduct,
                        l_tradedProductToday,
                        l_tradedProductNextDay,
                        l_strAdministratorCode);

                }
                // Step 1.17.1.4
                this.setScheduleUpdatedInfo(
                    l_productCondSettingUpdateInfo,
                    l_productConditionInfo,
                    l_equityProduct,
                    l_eqtypeProductConditionParams,
                    l_strAdministratorCode);
            }

        }
        // SumbitConditionChange 2
        // Step 1.2

        if (l_request.stockMarginInfoList != null)
        {
            l_adminProductCondConfigUnitList = l_request.stockMarginInfoList;
            l_intProductCondConfigUnitInfoCnt = l_adminProductCondConfigUnitList.length;

            //  Step 1.2.1
            for (int i = 0; i < l_intProductCondConfigUnitInfoCnt; i++)
            {
                l_productConditionInfo = l_adminProductCondConfigUnitList[i];
                l_strSmallItemDiv = l_productConditionInfo.smallItemDiv;
                l_strMarketCode = l_productConditionInfo.marketCode;
                l_strKey = this.getKey(l_strMarketCode, l_strSmallItemDiv);

                if (l_hmTradedProductToday != null)
                {
                    l_tradedProductToday =
                        (EqtypeTradedProductParams) l_hmTradedProductToday.get(l_strMarketCode);
                } else
                {
                    l_tradedProductToday = null;
                }
                if (l_hmTradedProductNextDay != null)
                {
                    l_tradedProductNextDay =
                        (EqtypeTradedProductParams) l_hmTradedProductNextDay.get(l_strMarketCode);
                } else
                {
                    l_tradedProductNextDay = null;
                }
                l_eqtypeProductConditionParams =
                    (EqtypeProductConditionParams) l_eqityProductConditionParams.get(l_strKey);

                // Step 1.2.1.1
                this.setLatestData(
                    l_productConditionInfo,
                    null,
                    null,
                    l_tradedProductToday,
                    l_tradedProductNextDay,
                    l_eqtypeProductConditionParams);

            // Step 1.2.1.2
            this.setProductUpdateInfo(
                l_productCondSettingUpdateInfo,
                l_productConditionInfo,
                l_equityProduct,
                l_tradedProductToday,
                l_tradedProductNextDay,
                l_strAdministratorCode);

            // Step 1.2.1.3
            this.setScheduleUpdatedInfo(
                l_productCondSettingUpdateInfo,
                l_productConditionInfo,
                l_equityProduct,
                l_eqtypeProductConditionParams,
                l_strAdministratorCode);
            }
        }

        // Step 1.3
        if (l_request.depositRateList != null)
        {
            l_adminProductCondConfigUnitList = l_request.depositRateList;
            l_intProductCondConfigUnitInfoCnt = l_adminProductCondConfigUnitList.length;

            // Step 1.3.1
            for (int i = 0; i < l_intProductCondConfigUnitInfoCnt; i++)
            {
                l_productConditionInfo = l_adminProductCondConfigUnitList[i];
                l_strMarketCode = l_productConditionInfo.marketCode;
                l_strSmallItemDiv = l_productConditionInfo.smallItemDiv;
                l_strKey = this.getKey(l_strMarketCode, l_strSmallItemDiv);

                if (l_hmTradedProductToday != null)
                {
                    l_tradedProductToday =
                        (EqtypeTradedProductParams) l_hmTradedProductToday.get(l_strMarketCode);
                } else
                {
                    l_tradedProductToday = null;
                }
                if (l_hmTradedProductNextDay != null)
                {
                    l_tradedProductNextDay =
                        (EqtypeTradedProductParams) l_hmTradedProductNextDay.get(l_strMarketCode);
                } else
                {
                    l_tradedProductNextDay = null;
                }
                l_eqtypeProductConditionParams =
                    (EqtypeProductConditionParams) l_eqityProductConditionParams.get(l_strKey);

                // Step 1.3.1.1
                this.setLatestData(
                    l_productConditionInfo,
                    null,
                    null,
                    l_tradedProductToday,
                    l_tradedProductNextDay,
                    l_eqtypeProductConditionParams);

                // Step 1.3.1.2
                this.setProductUpdateInfo(
                    l_productCondSettingUpdateInfo,
                    l_productConditionInfo,
                    l_equityProduct,
                    l_tradedProductToday,
                    l_tradedProductNextDay,
                    l_strAdministratorCode);

            // Step 1.3.1.3
            this.setScheduleUpdatedInfo(
                l_productCondSettingUpdateInfo,
                l_productConditionInfo,
                l_equityProduct,
                l_eqtypeProductConditionParams,
                l_strAdministratorCode);
            }
        }

        // Step 1.4
        // Check subsituteSecurityInfo List
        if (l_request.substituteSecurityInfoList != null)
        {
            l_adminProductCondConfigUnitList = l_request.substituteSecurityInfoList;
            l_intProductCondConfigUnitInfoCnt = l_adminProductCondConfigUnitList.length;

            // Step 1.4.1
            for (int i = 0; i < l_intProductCondConfigUnitInfoCnt; i++)
            {
                l_productConditionInfo = l_adminProductCondConfigUnitList[i];
                l_strMarketCode = l_productConditionInfo.marketCode;
                l_strSmallItemDiv = l_productConditionInfo.smallItemDiv;
                l_strKey = this.getKey(l_strMarketCode, l_strSmallItemDiv);

                if (l_hmTradedProductToday != null)
                {
                    l_tradedProductToday =
                        (EqtypeTradedProductParams) l_hmTradedProductToday.get(l_strMarketCode);
                } else
                {
                    l_tradedProductToday = null;
                }
                if (l_hmTradedProductNextDay != null)
                {
                    l_tradedProductNextDay =
                        (EqtypeTradedProductParams) l_hmTradedProductNextDay.get(l_strMarketCode);
                } else
                {
                    l_tradedProductNextDay = null;
                }
                l_eqtypeProductConditionParams =
                    (EqtypeProductConditionParams) l_eqityProductConditionParams.get(l_strKey);

                // Step 1.4.1.1
                this.setLatestData(
                    l_productConditionInfo,
                    l_productParams,
                    null,
                    null,
                    null,
                    l_eqtypeProductConditionParams);

                // Step 1.4.1.2
                if (WEB3AdminEquitySmallItemDivDef.ESTIMATION_PRICE.equals(l_strSmallItemDiv))
                {
                    continue;
                }

                // Step 1.4.1.3
                this.setProductUpdateInfo(
                    l_productCondSettingUpdateInfo,
                    l_productConditionInfo,
                    l_equityProduct,
                    l_tradedProductToday,
                    l_tradedProductNextDay,
                    l_strAdministratorCode);

                // Step 1.4.1.4
                this.setScheduleUpdatedInfo(
                    l_productCondSettingUpdateInfo,
                    l_productConditionInfo,
                    l_equityProduct,
                    l_eqtypeProductConditionParams,
                    l_strAdministratorCode);
            }
        }

        // Step 1.5
        if (l_request.priceInfoList != null)
        {
            l_adminProductCondConfigUnitList = l_request.priceInfoList;
            l_intProductCondConfigUnitInfoCnt = l_adminProductCondConfigUnitList.length;

            // Step 1.5.1
            for (int i = 0; i < l_intProductCondConfigUnitInfoCnt; i++)
            {
                l_productConditionInfo = l_adminProductCondConfigUnitList[i];
                l_strMarketCode = l_productConditionInfo.marketCode;
                l_strSmallItemDiv = l_productConditionInfo.smallItemDiv;
                l_strKey = this.getKey(l_strMarketCode, l_strSmallItemDiv);

                if (l_hmTradedProductToday != null)
                {
                    l_tradedProductToday =
                        (EqtypeTradedProductParams) l_hmTradedProductToday.get(l_strMarketCode);
                } else
                {
                    l_tradedProductToday = null;
                }
                if (l_hmTradedProductNextDay != null)
                {
                    l_tradedProductNextDay =
                        (EqtypeTradedProductParams) l_hmTradedProductNextDay.get(l_strMarketCode);
                } else
                {
                    l_tradedProductNextDay = null;
                }
                l_eqtypeProductConditionParams =
                    (EqtypeProductConditionParams) l_eqityProductConditionParams.get(l_strKey);

                // Step 1.5.1.1
                this.setLatestData(
                    l_productConditionInfo,
                    l_productParams,
                    null,
                    l_tradedProductToday,
                    l_tradedProductNextDay,
                    l_eqtypeProductConditionParams);

                // Step 1.5.1.2
                this.setProductUpdateInfo(
                    l_productCondSettingUpdateInfo,
                    l_productConditionInfo,
                    l_equityProduct,
                    l_tradedProductToday,
                    l_tradedProductNextDay,
                    l_strAdministratorCode);

                // Step 1.5.1.3
                this.setScheduleUpdatedInfo(
                    l_productCondSettingUpdateInfo,
                    l_productConditionInfo,
                    l_equityProduct,
                    l_eqtypeProductConditionParams,
                    l_strAdministratorCode);
            }
        }

        // Step 1.6
        l_productUpdateInfo = l_productCondSettingUpdateInfo.productUpdateInfo;
        if (l_productUpdateInfo != null)
        {
            // Step 1.6.1
            l_lngProductId = l_productUpdateInfo.id;
            l_hmChange = l_productUpdateInfo.updateRowAndValue;
            l_equityProductManager.updateProduct(l_lngProductId, l_hmChange);
        }

        // Step 1.7
        l_equityProductUpdateInfo = l_productCondSettingUpdateInfo.equityProductUpdateInfo;
        if (l_equityProductUpdateInfo != null)
        {
            // Step 1.7.1
            l_lngProductId = l_equityProductUpdateInfo.id;
            l_hmChange = l_equityProductUpdateInfo.updateRowAndValue;
            l_equityProductManager.updateEqtypeProduct(l_lngProductId, l_hmChange);
        }

        // Step 1.8
        l_lisParams =
            new ArrayList(l_productCondSettingUpdateInfo.tradeProductUpdateInfo.values());
        l_tradedProductUpdateInfoList = new WEB3AdminPMUpdateInfo[l_lisParams.size()];
        l_tradedProductUpdateInfoList =
            (WEB3AdminPMUpdateInfo[]) l_lisParams.toArray(l_tradedProductUpdateInfoList);

        l_intTradedProductUpdateInfoCnt = l_tradedProductUpdateInfoList.length;

        for (int i = 0; i < l_intTradedProductUpdateInfoCnt; i++)
        {
            l_lngTradedProductId = l_tradedProductUpdateInfoList[i].id;
            l_hmChangeMap = l_tradedProductUpdateInfoList[i].updateRowAndValue;
            l_equityProductManager.updateEqtypeTradedProduct(l_lngTradedProductId, l_hmChangeMap);
        }

        // Step 1.9
        l_lisParams =
            new ArrayList(l_productCondSettingUpdateInfo.tradeProductNextUpdateInfo.values());
        l_tradedProductNextUpdateInfoList = new WEB3AdminPMUpdateInfo[l_lisParams.size()];
        l_tradedProductNextUpdateInfoList =
            (WEB3AdminPMUpdateInfo[]) l_lisParams.toArray(l_tradedProductNextUpdateInfoList);
        l_intTradedProductUpdateInfoCnt = l_tradedProductNextUpdateInfoList.length;

        for (int i = 0; i < l_intTradedProductUpdateInfoCnt; i++)
        {
            l_lngTradedProductId = l_tradedProductNextUpdateInfoList[i].id;
            l_strBizDate = l_tradedProductNextUpdateInfoList[i].expirationDate;
            l_hmChangeMap = l_tradedProductNextUpdateInfoList[i].updateRowAndValue;

            l_equityProductManager.updateEqtypeTradedProductUpdq(
                l_lngTradedProductId, l_strBizDate, l_hmChangeMap);
        }

        // Step 1.10
        l_lisParams =
            new ArrayList(l_productCondSettingUpdateInfo.tradeProductNext2UpdateInfo.values());
        l_tradedProductNext2UpdateInfoList = new WEB3AdminPMUpdateInfo[l_lisParams.size()];
        l_tradedProductNext2UpdateInfoList =
            (WEB3AdminPMUpdateInfo[]) l_lisParams.toArray(l_tradedProductNext2UpdateInfoList);

        l_intTradedProductUpdateInfoCnt = l_tradedProductNext2UpdateInfoList.length;

        for (int i = 0; i < l_intTradedProductUpdateInfoCnt; i++)
        {
            l_lngTradedProductId = l_tradedProductNext2UpdateInfoList[i].id;
            l_strBizDate = l_tradedProductNext2UpdateInfoList[i].expirationDate;
            l_hmChangeMap = l_tradedProductNext2UpdateInfoList[i].updateRowAndValue;
            l_equityProductManager.updateEqtypeTradedProductUpdq(
                l_lngTradedProductId, l_strBizDate, l_hmChangeMap);
        }

        // Step 1.11
        l_lisEqtypeProductConditionParams = l_productCondSettingUpdateInfo.scheduleUpdateInfo;
        l_intEquityProductConditionCnt = l_lisEqtypeProductConditionParams.size();

        for (int i = 0; i < l_intEquityProductConditionCnt; i++)
        {
            EqtypeProductConditionParams l_aftEqtypeProductConditionParams =
                (EqtypeProductConditionParams) l_lisEqtypeProductConditionParams.get(i);
            l_strMarketCode = l_aftEqtypeProductConditionParams.market_code;
            l_strSmallItemDiv = l_aftEqtypeProductConditionParams.small_item_div;
            l_strKey = this.getKey(l_strMarketCode, l_strSmallItemDiv);

            // Step 1.11.1
            l_eqtypeProductConditionParams =
                (EqtypeProductConditionParams) l_eqityProductConditionParams.get(l_strKey);

            // step 1.11.2
            if (l_eqtypeProductConditionParams == null)
            {
                // Step 1.11.2.1
                l_equityDataManager.insertEqtypeProductCondition(l_aftEqtypeProductConditionParams);

            // Step 1.11.3
            } else
            {
                // Step 1.11.3.1
                l_equityDataManager.updateEqtypeProductCondition(l_aftEqtypeProductConditionParams);
            }

        }

        // Step 1.12
        l_response = (WEB3AdminPMProductCondConfCompleteResponse) l_request.createResponse();

        l_currentTime =
            (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);

        // Step 1.13
        l_response.currentDate = l_currentTime;
        l_response.tradingRegulationList = l_request.tradingRegulationList;
        l_response.stockMarginInfoList = l_request.stockMarginInfoList;
        l_response.basicInfoList = l_request.basicInfoList;
        l_response.depositRateList = l_request.depositRateList;
        l_response.substituteSecurityInfoList = l_request.substituteSecurityInfoList;
        l_response.priceInfoList = l_request.priceInfoList;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * �iget�������������ݒ�Params�ꗗ�j<BR>
     * <BR>
     * �������������ݒ�Params�̈ꗗ���擾����B<BR>
     * ���s��R�[�h + �����ڋ敪���L�[�Ƃ���HashMap��ԋp����B<BR>
     * <BR>
     * �P�j�g�������v���_�N�g�}�l�[�W��.<BR>
     * �@@get�������������ݒ�Params�ꗗ()���R�[������B<BR>
     * <BR>
     * �@@[get�������������ݒ�Params�ꗗ()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h<BR>
     * �@@�@@�����R�[�h�F�@@�p�����[�^.�����R�[�h<BR>
     * <BR>
     * �@@��L���\�b�h��null���ԋp���ꂽ�ꍇ�́A���HashMap�C���X�^���X��<BR>
     * �@@�ԋp����B<BR>
     * <BR>
     * �Q�jHashMap�𐶐�����B<BR>
     * <BR>
     * �R�j�P�j�̖߂�l(=�������������ݒ�Params)�̐���<BR>
     * �@@�ȉ��̏������J��Ԃ��B<BR>
     * <BR>
     * �@@�R�|�P�j��������HashMap.put()���\�b�h���R�[�����A<BR>
     * �@@�@@�@@�@@�������������ݒ�Params���i�[����B<BR>
     * <BR>
     * �@@�@@�@@�@@[put()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@key�F�@@this.getKey(������������Params.�s��R�[�h,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@������������Params.�����ڋ敪)<BR>
     * �@@�@@�@@�@@�@@value�F�@@������������Params<BR>
     * <BR>
     * �S�j�R�j�ɂăf�[�^���Z�b�g����HashMap��ԋp����B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * getProductCondConfParamsList
     * <BR>
     * Acquire the list of eqtypeProductConditionParams<BR>
     * ��Return HashMap whose key is marketCode + smallItemDiv<BR>
     * <BR>
     * 1)Call WEB3EquityProductManeger.<BR>
     * �@@getProductCondConfParamsList()<BR>
     * <BR>
     * �@@[parameter set into getProductCondConfParamsList()]<BR>
     * �@@�@@l_strInstitutionCode:�@@parameter.institutionCode<BR>
     * �@@�@@l_strProductCode:�@@parameter.productCode<BR>
     * <BR>
     * �@@If null is returned in the method above, return an empty HashMap instance<BR>
     * <BR>
     * 2)Create HashMap<BR>
     * <BR>
     * 3)Loop for as many times as return values at 1)(=eqtypeProductConditionParams)
     * <BR>
     * �@@3-1)Call created HashMap.put() method, and<BR>
     * �@@�@@�@@�@@store eqtypeProductConditionParams<BR>
     * <BR>
     * �@@�@@�@@�@@[parameter set into put()]<BR>
     * �@@�@@�@@�@@�@@key: l_eqtypeProductConditionParams.market_code+ <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@l_eqtypeProductConditionParams.small_item_div<BR>
     * �@@�@@�@@�@@�@@value: l_eqtypeProductConditionParams<BR>
     * <BR>
     * 4)Return HashMap with the data set at 3)<BR>
     * <BR>
     * @@param l_strInstitutionCode - �i�،���ЃR�[�h�j<BR>
     * <BR>
     * l_strInstitutionCode<BR>
     * <BR>
     * @@param l_strProductCode - �i�����R�[�h�j<BR>
     * <BR>
     * l_strProductCode<BR>
     * <BR>
     * @@return java.util.HashMap
     * @@throws DataNetworkException DataNetworkException
     * @@throws DataQueryException DataQueryException
     * @@roseuid 41B6E89303CE
     */
    protected HashMap getProductCondConfParamsList(
        String l_strInstitutionCode,
        String l_strProductCode)
        throws DataNetworkException, DataQueryException
    {
        final String STR_METHOD_NAME = "getProductCondConfParamsList(String, String)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminPMEquityDataManager l_adminPMEquityDataManager = null;
        EqtypeProductConditionParams l_eqtypeProductConditionParams = null;
        List l_lstProductCondParams = null;
        Map l_mapProductConditionParams = null;
        String l_strKey = null;
        String l_strMarketCode = null;
        String l_strSmallItemDiv = null;
        int l_lstCount = 0;

        l_adminPMEquityDataManager = new WEB3AdminPMEquityDataManager();

        // 1)Call WEB3AdminPMEquityDataManager.getEqtypeProductConditionParamsList()
        l_lstProductCondParams =
            l_adminPMEquityDataManager.getEqtypeProductConditionParamsList(
                l_strInstitutionCode,
                l_strProductCode);

        // 2)Create HashMap
        l_mapProductConditionParams = new HashMap();

        // If null is returned in the method above, return an empty HashMap instance
        if (l_lstProductCondParams == null)
        {
            return (HashMap) l_mapProductConditionParams;
        }

        l_lstCount = l_lstProductCondParams.size();
        // 3)Loop for as many times as return values at 1)(=eqtypeProductConditionParams)
        for (int i = 0; i < l_lstCount; i++)
        {
            // 3-1)Call created HashMap.put() method, and store eqtypeProductConditionParams
            l_eqtypeProductConditionParams =
                (EqtypeProductConditionParams) l_lstProductCondParams.get(i);
            l_strMarketCode = l_eqtypeProductConditionParams.getMarketCode();
            l_strSmallItemDiv = l_eqtypeProductConditionParams.getSmallItemDiv();
            l_strKey = this.getKey(l_strMarketCode, l_strSmallItemDiv);
            l_mapProductConditionParams.put(l_strKey, l_eqtypeProductConditionParams);
        }

        log.exiting(STR_METHOD_NAME);

        // 4)Return HashMap with the data set at 3)
        return (HashMap) l_mapProductConditionParams;
    }

    /**
     * �icreate����K���ꗗ�j<BR>
     * <BR>
     * ����K�����̈ꗗ���쐬����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��Ҋ������������ݒ�T�[�r�X)create����K���ꗗ�v�Q��<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * createTradingRegulationList<BR>
     * <BR>
     * Create tradingRegulation list<BR>
     * <BR>
     * Refer to the sequence diagram "(administrator: equity product condition setting
     * service)createTradingRegulationList"<BR>
     * <BR>
     * @@param l_branch - �i���X�I�u�W�F�N�g�j<BR>
     * <BR>
     * l_branch<BR>
     * <BR>
     *
     * @@param l_strMarketCodeList - �i���s��R�[�h�ꗗ�j<BR>
     * <BR>
     * l_strMarketCodeList<BR>
     * <BR>
     * @@param l_equityProduct - �i���������j<BR>
     * <BR>
     * ���������I�u�W�F�N�g<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * l_equityProduct<BR>
     * <BR>
     * l_equityProduct object<BR>
     * <BR>
     * @@param l_tradedProductsToday - �i��������ꗗ(����)�j<BR>
     * <BR>
     * l_tradedProductsToday<BR>
     * <BR>
     * @@param l_tradedProductsNextDay - �i��������ꗗ(����)�j<BR>
     * <BR>
     * l_tradedProductsNextDay<BR>
     * <BR>
     * @@param l_tradedProductsTwoDaysLater - �i��������ꗗ(���X��)�j<BR>
     * <BR>
     * ���~�j�����{��Ђ̏ꍇ�Z�b�g�B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * l_tradedProductsTwoDaysLater<BR>
     * <BR>
     * ��Set in case of a mini stock enforcement company<BR>
     * <BR>
     *
     *
     * @@param l_eqtypeProductConditionParamsList - �i�������������ݒ�Params�ꗗ�j<BR>
     * <BR>
     * ������������Params�̈ꗗ<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * l_eqtypeProductConditionParamsList<BR>
     * <BR>
     * @@return l_adminPMProductCondConfigUnitList WEB3AdminPMProductCondConfigUnit[]
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws NotFoundException NotFoundException
     * @@throws DataFindException DataFindException
     * @@throws DataQueryException DataQueryException
     * @@throws DataNetworkException DataNetworkException
     * @@roseuid 41822DD102B8
     */
    protected WEB3AdminPMProductCondConfigUnit[] createTradingRegulationList(
        WEB3GentradeBranch l_branch,
        String[] l_strMarketCodeList,
        WEB3EquityProduct l_equityProduct,
        HashMap l_tradedProductsToday,
        HashMap l_tradedProductsNextDay,
        HashMap l_tradedProductsTwoDaysLater,
        HashMap l_eqtypeProductConditionParamsList)
        throws DataFindException, DataQueryException,
        DataNetworkException, WEB3BaseException, NotFoundException
    {
        final String STR_METHOD_NAME =
            "createTradingRegulationList(WEB3GentradeBranch, String[]"
                + "WEB3EquityProduct, HashMap, HashMap, HashMap, HashMap)";
        log.entering(STR_METHOD_NAME);

        EqtypeProductConditionParams l_eqtypeProductConditionParams = null;
        WEB3AdminPMProductCondConfigUnit l_adminPMProductCondConfigUnit = null;
        WEB3AdminPMProductCondConfigUnit[] l_adminPMProductCondConfigUnitList = null;
        Market l_market = null;
        EqtypeTradedProductParams l_tradedProductToday = null;
        EqtypeTradedProductParams l_tradedProductTwoDaysLater = null;
        EqtypeTradedProductParams l_tradedProductNextDay = null;
        final String L_STR_MARKET_CODE = WEB3MarketCodeDef.DEFAULT;
        String l_strMarketCode = null;
        String l_strMarketCode1 = null;
        String l_strSmallItemDiv = null;
        final String L_STR_LARGE_ITEM_DIV = WEB3AdminEquityLargeItemDivDef.TRADING_REGULATION;
        final String L_STR_SMALL_ITEM_DIV = WEB3AdminEquitySmallItemDivDef.TRADE_STOP;
        final String L_STR_SMALL_ITEM_SYS = WEB3AdminEquitySmallItemDivDef.MARGIN_SYS_TRADE_STOP;
        final String L_STR_SMALL_ITEM_GEN = WEB3AdminEquitySmallItemDivDef.MARGIN_GEN_TRADE_STOP;
        String l_strHashKey = null;
        String l_strMarginSysDiv = null;
        String l_strMarginGenDiv = null;
        String l_strMiniStockExecutionDiv = null;
        String[] l_strMarketCodeList1 = null;
        List l_lisAdminPMProductCondConfigUnit = null;
        List l_lisSmallItemDiv = null;
        int l_intMarketSize = 0;
        int l_intSmallItemDivSize = 0;
        int l_intRegulationSize = 0;
        long l_lngMarketId = 0L;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        // Step 1.1
        l_lisAdminPMProductCondConfigUnit = new ArrayList();

        // Value of HashKey to get the value in HashMap
        l_strHashKey = this.getKey(L_STR_MARKET_CODE, L_STR_SMALL_ITEM_DIV);
        l_eqtypeProductConditionParams =
            (EqtypeProductConditionParams) l_eqtypeProductConditionParamsList.get(l_strHashKey);

        // Step1.2
        // Get AdminPMProductCondConfigUnit
        l_adminPMProductCondConfigUnit =
            createProductCondConfigUnit(
                L_STR_MARKET_CODE,
                L_STR_LARGE_ITEM_DIV,
                L_STR_SMALL_ITEM_DIV,
                null,
                l_equityProduct,
                null,
                null,
                l_eqtypeProductConditionParams);

        //Step1.3
        // Add AdminPMProductCondConfigUnit to List
        l_lisAdminPMProductCondConfigUnit.add(l_adminPMProductCondConfigUnit);

        // Get the value of MarginSysDiv
        l_strMarginSysDiv = ((BranchParams) l_branch.getDataSourceObject()).getMarginSysDiv();

        // Step1.4
        // Check for MarginSysDiv
        if ((WEB3EnforcementDef.ENFORCEMENT).equals(l_strMarginSysDiv))
        {
            // Value for SmallItemDiv
            // Value of HashKey to get the value in HashMap
            l_strHashKey = this.getKey(L_STR_MARKET_CODE, L_STR_SMALL_ITEM_SYS);
            l_eqtypeProductConditionParams =
                (EqtypeProductConditionParams) l_eqtypeProductConditionParamsList.get(l_strHashKey);

            // Get AdminPMProductCondConfigUnit
            l_adminPMProductCondConfigUnit =
                createProductCondConfigUnit(
                    L_STR_MARKET_CODE,
                    L_STR_LARGE_ITEM_DIV,
                    L_STR_SMALL_ITEM_SYS,
                    null,
                    l_equityProduct,
                    null,
                    null,
                    l_eqtypeProductConditionParams);

            // Add AdminPMProductCondConfigUnit to List
            l_lisAdminPMProductCondConfigUnit.add(l_adminPMProductCondConfigUnit);
        }

        // Get the value of MarginGenDiv
        l_strMarginGenDiv = ((BranchParams) l_branch.getDataSourceObject()).getMarginGenDiv();

        // Step1.5
        // Check for MarginGenDiv
        if ((WEB3EnforcementDef.ENFORCEMENT).equals(l_strMarginGenDiv))
        {
            // Value of HashKey to get the value in HashMap
            l_strHashKey = this.getKey(L_STR_MARKET_CODE, L_STR_SMALL_ITEM_GEN);
            l_eqtypeProductConditionParams =
                (EqtypeProductConditionParams) l_eqtypeProductConditionParamsList.get(l_strHashKey);

            // Get AdminPMProductCondConfigUnit
            l_adminPMProductCondConfigUnit =
                createProductCondConfigUnit(
                    L_STR_MARKET_CODE,
                    L_STR_LARGE_ITEM_DIV,
                    L_STR_SMALL_ITEM_GEN,
                    null,
                    l_equityProduct,
                    null,
                    null,
                    l_eqtypeProductConditionParams);

            // Add AdminPMProductCondConfigUnit to List
            l_lisAdminPMProductCondConfigUnit.add(l_adminPMProductCondConfigUnit);
        }

        // Step 1.6
        l_lisSmallItemDiv = new ArrayList();
        l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.BUY_CASH_STOP);
        l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.SELL_CASH_STOP);

        if (WEB3EnforcementDef.ENFORCEMENT.equals(l_strMarginSysDiv))
        {
            l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.LONG_MARGIN_SYS_STOP);
            l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.SHORT_MARGIN_SYS_STOP);
            l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.LONG_CLOSE_MARGIN_SYS_STOP);
            l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.SHORT_CLOSE_MARGIN_SYS_STOP);
            l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.LONG_SWAP_MARGIN_SYS_STOP);
            l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.SHORT_SWAP_MARGIN_SYS_STOP);
        }
        if (WEB3EnforcementDef.ENFORCEMENT.equals(l_strMarginGenDiv))
        {
            l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.LONG_MARGIN_GEN_STOP);
            l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.SHORT_MARGIN_GEN_STOP);
            l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.LONG_CLOSE_MARGIN_GEN_STOP);
            l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.SHORT_CLOSE_MARGIN_GEN_STOP);
            l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.LONG_SWAP_MARGIN_GEN_STOP);
            l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.SHORT_SWAP_MARGIN_GEN_STOP);
        }

        l_intMarketSize = l_strMarketCodeList.length;
        l_intSmallItemDivSize = l_lisSmallItemDiv.size();
        for (int i = 0; i < l_intMarketSize; i++)
        {
            l_strMarketCode = l_strMarketCodeList[i];
            if (l_tradedProductsToday != null)
            {
                l_tradedProductToday =
                    (EqtypeTradedProductParams) l_tradedProductsToday.get(l_strMarketCode);
            } else
            {
                l_tradedProductToday = null;
            }

            if (l_tradedProductsNextDay != null)
            {
                l_tradedProductNextDay =
                    (EqtypeTradedProductParams) l_tradedProductsNextDay.get(l_strMarketCode);
            } else
            {
                l_tradedProductNextDay = null;
            }
            for (int j = 0; j < l_intSmallItemDivSize; j++)
            {
                l_strSmallItemDiv = (String) l_lisSmallItemDiv.get(j);

                // Value of HashKey to get the value in HashMap
                l_strHashKey = this.getKey(l_strMarketCode, l_strSmallItemDiv);
                l_eqtypeProductConditionParams =
                    (EqtypeProductConditionParams) l_eqtypeProductConditionParamsList.get(
                        l_strHashKey);

                // Get AdminPMProductCondConfigUnit
                l_adminPMProductCondConfigUnit =
                    createProductCondConfigUnit(
                        l_strMarketCode,
                        L_STR_LARGE_ITEM_DIV,
                        l_strSmallItemDiv,
                        null,
                        l_equityProduct,
                        l_tradedProductToday,
                        l_tradedProductNextDay,
                        l_eqtypeProductConditionParams);

                // Add AdminPMProductCondConfigUnit to List
                l_lisAdminPMProductCondConfigUnit.add(l_adminPMProductCondConfigUnit);
            }
        }

        // Step 1.7
        l_lisSmallItemDiv = new ArrayList();
        l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.BUY_SPOT_MARKET_ORD_DES_STOP);
        l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.SELL_SPOT_MARKET_ORD_DES_STOP);

        if (WEB3EnforcementDef.ENFORCEMENT.equals(l_strMarginSysDiv))
        {
            l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.LONG_MS_MARKET_ORD_DES_STOP);
            l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.SHORT_MS_MARKET_ORD_DES_STOP);
            l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.LONG_CMS_MARKET_ORD_DES_STOP);
            l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.SHORT_CMS_MARKET_ORD_DES_STOP);
        }
        if (WEB3EnforcementDef.ENFORCEMENT.equals(l_strMarginGenDiv))
        {
            l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.LONG_MG_MARKET_ORD_DES_STOP);
            l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.SHORT_MG_MARKET_ORD_DES_STOP);
            l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.LONG_CMG_MARKET_ORD_DES_STOP);
            l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.SHORT_CMG_MARKET_ORD_DES_STOP);
        }

        l_intSmallItemDivSize = l_lisSmallItemDiv.size();
        for (int i = 0; i < l_intMarketSize; i++)
        {
            l_strMarketCode = l_strMarketCodeList[i];
            if (l_tradedProductsToday != null)
            {
                l_tradedProductToday =
                    (EqtypeTradedProductParams) l_tradedProductsToday.get(l_strMarketCode);
            } else
            {
                l_tradedProductToday = null;
            }

            if (l_tradedProductsNextDay != null)
            {
                l_tradedProductNextDay =
                    (EqtypeTradedProductParams) l_tradedProductsNextDay.get(l_strMarketCode);
            } else
            {
                l_tradedProductNextDay = null;
            }
            for (int j = 0; j < l_intSmallItemDivSize; j++)
            {
                l_strSmallItemDiv = (String) l_lisSmallItemDiv.get(j);
                // Value of HashKey to get the value in HashMap
                l_strHashKey = this.getKey(l_strMarketCode, l_strSmallItemDiv);
                l_eqtypeProductConditionParams =
                    (EqtypeProductConditionParams) l_eqtypeProductConditionParamsList.get(
                        l_strHashKey);
                // Get AdminPMProductCondConfigUnit
                l_adminPMProductCondConfigUnit =
                    createProductCondConfigUnit(
                        l_strMarketCode,
                        L_STR_LARGE_ITEM_DIV,
                        l_strSmallItemDiv,
                        null,
                        l_equityProduct,
                        l_tradedProductToday,
                        l_tradedProductNextDay,
                        l_eqtypeProductConditionParams);
                // Add AdminPMProductCondConfigUnit to List
                l_lisAdminPMProductCondConfigUnit.add(l_adminPMProductCondConfigUnit);
            }
        }

        l_strMiniStockExecutionDiv = ((BranchParams) l_branch.getDataSourceObject()).getMstkDiv();

        // Step 1.8
        if ((WEB3EnforcementDef.ENFORCEMENT).equals(l_strMiniStockExecutionDiv))
        {
            // Step 1.8.1
            l_strMarketCodeList1 =
                createListedMarketCodeList(l_tradedProductsNextDay,
                    l_tradedProductsTwoDaysLater);
            l_intMarketSize = l_strMarketCodeList1.length;

            // Step 1.8.2
            for (int i = 0; i < l_intMarketSize; i++)
            {
                l_strMarketCode = l_strMarketCodeList1[i];

                if (l_tradedProductsNextDay != null)
                {
                    l_tradedProductNextDay =
                        (EqtypeTradedProductParams) l_tradedProductsNextDay.get(l_strMarketCode);
                } else
                {
                    l_tradedProductNextDay = null;
                }
                if (l_tradedProductsTwoDaysLater != null)
                {
                    l_tradedProductTwoDaysLater =
                        (EqtypeTradedProductParams) l_tradedProductsTwoDaysLater.get(
                            l_strMarketCode);
                } else
                {
                    l_tradedProductTwoDaysLater = null;
                }

                if ((l_tradedProductNextDay != null
                    && l_tradedProductNextDay.getMiniStockCanDealt().intValue() == 1)
                    || (l_tradedProductTwoDaysLater != null
                        && l_tradedProductTwoDaysLater.getMiniStockCanDealt().intValue() == 1))
                {
                    l_lisSmallItemDiv = new ArrayList();
                    l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.BUY_MINI_STOCK_STOP);
                    l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.SELL_MINI_STOCK_STOP);
                    l_intSmallItemDivSize = l_lisSmallItemDiv.size();

                    if (l_tradedProductNextDay != null
                        && l_tradedProductNextDay.getMiniStockCanDealt().intValue() == 1)
                    {
                        l_lngMarketId = l_tradedProductNextDay.getMarketId();
                        l_market = l_finApp.getFinObjectManager().getMarket(l_lngMarketId);
                        l_strMarketCode1 = l_market.getMarketCode();
                    } else
                    {
                        l_lngMarketId = l_tradedProductTwoDaysLater.getMarketId();
                        l_market = l_finApp.getFinObjectManager().getMarket(l_lngMarketId);
                        l_strMarketCode1 = l_market.getMarketCode();
                    }
                    if (l_tradedProductTwoDaysLater != null
                        && l_tradedProductTwoDaysLater.getMiniStockCanDealt().intValue() == 0)
                    {
                        l_tradedProductTwoDaysLater = null;
                    }
                    if (l_tradedProductNextDay != null
                        && l_tradedProductNextDay.getMiniStockCanDealt().intValue() == 0)
                    {
                        l_tradedProductNextDay = null;
                    }
                    for (int j = 0; j < l_intSmallItemDivSize; j++)
                    {
                        l_strSmallItemDiv = (String) l_lisSmallItemDiv.get(j);
                        // Value of HashKey to get the value in HashMap
                        l_strHashKey = this.getKey(l_strMarketCode, l_strSmallItemDiv);
                        l_eqtypeProductConditionParams =
                            (EqtypeProductConditionParams) l_eqtypeProductConditionParamsList.get(
                                l_strHashKey);
                        // Get AdminPMProductCondConfigUnit
                        l_adminPMProductCondConfigUnit =
                            createProductCondConfigUnit(
                                l_strMarketCode1,
                                L_STR_LARGE_ITEM_DIV,
                                l_strSmallItemDiv,
                                null,
                                l_equityProduct,
                                l_tradedProductNextDay,
                                l_tradedProductTwoDaysLater,
                                l_eqtypeProductConditionParams);
                        // Add AdminPMProductCondConfigUnit to List
                        l_lisAdminPMProductCondConfigUnit.add(l_adminPMProductCondConfigUnit);
                    }
                }
            }
        }

        l_intRegulationSize = l_lisAdminPMProductCondConfigUnit.size();
        l_adminPMProductCondConfigUnitList =
            new WEB3AdminPMProductCondConfigUnit[l_intRegulationSize];
        for (int i = 0; i < l_intRegulationSize; i++)
        {
            l_adminPMProductCondConfigUnitList[i] =
                (WEB3AdminPMProductCondConfigUnit) l_lisAdminPMProductCondConfigUnit.get(i);
        }
        log.exiting(STR_METHOD_NAME);

        return l_adminPMProductCondConfigUnitList;
    }

    /**
     * �icreate��{���ꗗ�j<BR>
     * <BR>
     * ��{���̈ꗗ���쐬����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��Ҋ������������ݒ�T�[�r�X)create��{���ꗗ�v�Q��<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * createBasicInfoList<BR>
     * <BR>
     * Create basicInfo list<BR>
     * <BR>
     * Refer to the sequence diagram "(administrator: equity product condition setting
     * service)createBasicInfoList"<BR>
     * <BR>
     * @@param l_branch - �i���X�I�u�W�F�N�g�j<BR>
     * <BR>
     * l_branch<BR>
     * <BR>
     *
     * @@param l_strMarketCodeList - �i���s��R�[�h�ꗗ�j<BR>
     * <BR>
     * l_strMarketCodeList<BR>
     * <BR>
     * @@param l_equityProduct - �i���������j<BR>
     * <BR>
     * ���������I�u�W�F�N�g<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * l_equityProduct<BR>
     * <BR>
     * l_equityProduct object<BR>
     * <BR>
     * @@param l_tradedProductsToday - �i��������ꗗ(����)�j<BR>
     * <BR>
     * l_tradedProductsToday<BR>
     * <BR>
     * @@param l_tradedProductsNextDay - �i��������ꗗ(����)�j<BR>
     * <BR>
     * l_tradedProductsNextDay<BR>
     * <BR>
     * @@param l_tradedProductsTwoDaysLater - �i��������ꗗ(���X��)�j<BR>
     * <BR>
     * ���~�j�����{��Ђ̏ꍇ�Z�b�g�B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * l_tradedProductsTwoDaysLater<BR>
     * <BR>
     * ��Set in case of a mini stock enforcement company<BR>
     * <BR>
     *
     *
     * @@param l_eqtypeProductConditionParamsList - �i�������������ݒ�Params�ꗗ�j<BR>
     * <BR>
     * ������������Params�̈ꗗ<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * l_eqtypeProductConditionParamsList<BR>
     * <BR>
     * @@return l_adminPMProductCondConfigUnitList
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws NotFoundException NotFoundException
     * @@throws DataFindException DataFindException
     * @@throws DataQueryException DataQueryException
     * @@throws DataNetworkException DataNetworkException
     * @@roseuid 41822FCE0018
     */
    protected WEB3AdminPMProductCondConfigUnit[] createBasicInfoList(
        WEB3GentradeBranch l_branch,
        String[] l_strMarketCodeList,
        ProductParams l_productParams,
        WEB3EquityProduct l_equityProduct,
        HashMap l_tradedProductsToday,
        HashMap l_tradedProductsNextDay,
        HashMap l_tradedProductsTwoDaysLater,
        HashMap l_eqtypeProductConditionParamsList)
        throws DataFindException, DataQueryException,
        DataNetworkException, WEB3BaseException, NotFoundException
    {
        final String STR_METHOD_NAME =
            "createBasicInfoList(WEB3GentradeBranch, String[], WEB3EquityProduct, "
                + "HashMap, HashMap, HashMap, HashMap)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminPMProductCondConfigUnit l_adminPMProductCondConfigUnit = null;
        WEB3EquityProductManager l_equityProductManager = null;
        String l_strHashKey = null;
        String l_strMstkDiv = null;
        String l_strMarketCode = null;
        String l_strSmallItemDiv = null;

        List l_lisAdminPMProductCondConfigUnit = null;
        List l_lisSmallItemDiv = null;
        List l_lisTradedProductToday = null;
        List l_lisTradedProductNextDay = null;
        List l_lisTradedProductsTwoDaysLater = null;

        EqtypeProductConditionParams l_eqtypeProductConditionParams = null;
        WEB3AdminPMProductCondConfigUnit[] l_adminPMProductCondConfigUnitList = null;
        EqtypeTradedProductParams l_tradedProductToday = null;
        EqtypeTradedProductParams l_tradedProductNextDay = null;
        EqtypeTradedProductParams l_tradedProductTwoDaysLater = null;
        EqtypeTradedProductParams[] l_tradedProductTodayList = null;
        EqtypeTradedProductParams[] l_tradedProductNextDayList = null;
        EqtypeTradedProductParams[] l_tradedProduct2DaysLaterList = null;

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        // Get the Default market code
        l_strHashKey = this.getKey(WEB3MarketCodeDef.DEFAULT, WEB3AdminEquitySmallItemDivDef.STANDARD_NAME);
        l_eqtypeProductConditionParams =
            (EqtypeProductConditionParams) l_eqtypeProductConditionParamsList.get(l_strHashKey);

        // Step 1.1
        l_lisAdminPMProductCondConfigUnit = new ArrayList();

        // Step 1.2
        // Get AdminPMProductCondConfigUnit
        l_adminPMProductCondConfigUnit =
            createProductCondConfigUnit(
                WEB3MarketCodeDef.DEFAULT,
                WEB3AdminEquityLargeItemDivDef.BASIC_INFO,
                WEB3AdminEquitySmallItemDivDef.STANDARD_NAME,
                null,
                l_equityProduct,
                null,
                null,
                l_eqtypeProductConditionParams);

        // Step 1.3
        // Add AdminPMProductCondConfigUnit to List
        l_lisAdminPMProductCondConfigUnit.add(l_adminPMProductCondConfigUnit);

		//��{���(�D��s��)�p��HashKey���쐬
		l_strHashKey = this.getKey(WEB3MarketCodeDef.DEFAULT, WEB3AdminEquitySmallItemDivDef.PRIMARY_MARKET);
		l_eqtypeProductConditionParams =
			(EqtypeProductConditionParams) l_eqtypeProductConditionParamsList.get(l_strHashKey);
        
		// ��{���(�D��s��)���擾
		l_adminPMProductCondConfigUnit =
			createProductCondConfigUnit(
				WEB3MarketCodeDef.DEFAULT,
				WEB3AdminEquityLargeItemDivDef.BASIC_INFO,
				WEB3AdminEquitySmallItemDivDef.PRIMARY_MARKET,
				l_productParams,
				null,
				null,
				null,
				l_eqtypeProductConditionParams);
				
		// �擾������{���(�D��s��)��List�ɒǉ�����B
		l_lisAdminPMProductCondConfigUnit.add(l_adminPMProductCondConfigUnit);

		//��{���(��������戵)�p��HashKey���쐬
		l_strHashKey = this.getKey(WEB3MarketCodeDef.DEFAULT, WEB3AdminEquitySmallItemDivDef.CAPITAL_GAIN_TAX_DEALING);
		l_eqtypeProductConditionParams =
			(EqtypeProductConditionParams) l_eqtypeProductConditionParamsList.get(l_strHashKey);
        
		// ��{���(��������戵)���擾
		l_adminPMProductCondConfigUnit =
			createProductCondConfigUnit(
				WEB3MarketCodeDef.DEFAULT,
				WEB3AdminEquityLargeItemDivDef.BASIC_INFO,
				WEB3AdminEquitySmallItemDivDef.CAPITAL_GAIN_TAX_DEALING,
				null,
				l_equityProduct,
				null,
				null,
				l_eqtypeProductConditionParams);
				
		// �擾������{���(��������戵)��List�ɒǉ�����B
		l_lisAdminPMProductCondConfigUnit.add(l_adminPMProductCondConfigUnit);


        // Value of HashKey to get the value in HashMap
        l_strHashKey = this.getKey(WEB3MarketCodeDef.DEFAULT, WEB3AdminEquitySmallItemDivDef.LOT_SIZE);
        l_eqtypeProductConditionParams =
            (EqtypeProductConditionParams) l_eqtypeProductConditionParamsList.get(l_strHashKey);

        if (l_tradedProductsToday != null)
        {
            l_lisTradedProductToday = new ArrayList(l_tradedProductsToday.values());
            l_tradedProductTodayList = new EqtypeTradedProductParams[l_tradedProductsToday.size()];
            l_tradedProductTodayList = (EqtypeTradedProductParams[])
                l_lisTradedProductToday.toArray(l_tradedProductTodayList);
            l_tradedProductToday = l_tradedProductTodayList[0];
        } else
        {
            l_tradedProductToday = null;
        }
        if (l_tradedProductsNextDay != null)
        {
            l_lisTradedProductNextDay = new ArrayList(l_tradedProductsNextDay.values());
            l_tradedProductNextDayList =
                new EqtypeTradedProductParams[l_tradedProductsNextDay.size()];
            l_tradedProductNextDayList = (EqtypeTradedProductParams[])
                l_lisTradedProductNextDay.toArray(l_tradedProductNextDayList);
            l_tradedProductNextDay = l_tradedProductNextDayList[0];
        } else
        {
            l_tradedProductNextDay = null;
        }

        // Step 1.4
        // Get AdminPMProductCondConfigUnit
        l_adminPMProductCondConfigUnit =
            createProductCondConfigUnit(
                WEB3MarketCodeDef.DEFAULT,
                WEB3AdminEquityLargeItemDivDef.BASIC_INFO,
                WEB3AdminEquitySmallItemDivDef.LOT_SIZE,
                null,
                null,
                l_tradedProductToday,
                l_tradedProductNextDay,
                l_eqtypeProductConditionParams);

        // Step 1.5
        // Add AdminPMProductCondConfigUnit to List
        l_lisAdminPMProductCondConfigUnit.add(l_adminPMProductCondConfigUnit);

        l_lisSmallItemDiv = new ArrayList();
        l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.COMPULSIVE_LIMITED_UNIT);
        l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.OPEN_OTC_DIV);
        l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.LIST_TYPE);
        l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.TODAY_DEP_FUND_REG);

        // Step 1.6
		for (int k = 0; k < l_lisSmallItemDiv.size(); k++)
		{
			l_strSmallItemDiv = (String) l_lisSmallItemDiv.get(k);
            // Step 1.6.1
			for (int i = 0; i < l_strMarketCodeList.length; i++)
			{
				l_strMarketCode = l_strMarketCodeList[i];
                if (l_tradedProductsToday != null)
                {
                    l_tradedProductToday =
                        (EqtypeTradedProductParams) l_tradedProductsToday.get(l_strMarketCode);
                } else
                {
                    l_tradedProductToday = null;
                }

                if (l_tradedProductsNextDay != null)
                {
                    l_tradedProductNextDay =
                        (EqtypeTradedProductParams) l_tradedProductsNextDay.get(l_strMarketCode);
                } else
                {
                    l_tradedProductNextDay = null;
                }

                // Value of HashKey to get the value in HashMap
                l_strHashKey = this.getKey(l_strMarketCode, l_strSmallItemDiv);
                l_eqtypeProductConditionParams =
                    (EqtypeProductConditionParams) l_eqtypeProductConditionParamsList.get(
                        l_strHashKey);

                // Step 1.6.1.1
                // Get AdminPMProductCondConfigUnit
                l_adminPMProductCondConfigUnit =
                    createProductCondConfigUnit(
                        l_strMarketCode,
                        WEB3AdminEquityLargeItemDivDef.BASIC_INFO,
                        (String) l_lisSmallItemDiv.get(k),
                        null,
                        null,
                        l_tradedProductToday,
                        l_tradedProductNextDay,
                        l_eqtypeProductConditionParams);
                // Step 1.6.1.2
                // Add AdminPMProductCondConfigUnit to List
                l_lisAdminPMProductCondConfigUnit.add(l_adminPMProductCondConfigUnit);
            }
        }

        // Step 1.7
        // Get the value of MstkDiv
        l_strMstkDiv = ((BranchParams) l_branch.getDataSourceObject()).getMstkDiv();
        if ((WEB3EnforcementDef.ENFORCEMENT).equals(l_strMstkDiv))
        {
            if (l_tradedProductsNextDay != null || l_tradedProductsTwoDaysLater != null)
            {
	            l_equityProductManager =
	                (WEB3EquityProductManager) l_tradingModule.getProductManager();
	            // Step 1.7.1
	            if (l_tradedProductsNextDay != null)
	            {
	                l_lisTradedProductNextDay = new ArrayList(l_tradedProductsNextDay.values());
	                l_tradedProductNextDayList =
	                    new EqtypeTradedProductParams[l_lisTradedProductNextDay.size()];
	                l_tradedProductNextDayList = (EqtypeTradedProductParams[])
	                    l_lisTradedProductNextDay.toArray(l_tradedProductNextDayList);
	                l_tradedProductNextDay =
	                    l_equityProductManager.getMiniStockTradedProduct(l_tradedProductNextDayList);
	            } else
	            {
	                l_tradedProductNextDay = null;
	            }
	
	            // Step 1.7.2
	            if (l_tradedProductsTwoDaysLater != null)
	            {
	                l_lisTradedProductsTwoDaysLater =
	                    new ArrayList(l_tradedProductsTwoDaysLater.values());
	                l_tradedProduct2DaysLaterList =
	                    new EqtypeTradedProductParams[l_lisTradedProductsTwoDaysLater.size()];
	                l_tradedProduct2DaysLaterList = (EqtypeTradedProductParams[])
	                    l_lisTradedProductsTwoDaysLater.toArray(l_tradedProduct2DaysLaterList);
	                l_tradedProductTwoDaysLater =
	                    l_equityProductManager.getMiniStockTradedProduct(l_tradedProduct2DaysLaterList);
	            } else
	            {
	                l_tradedProductTwoDaysLater = null;
	            }
	
	            // Value of HashKey to get the value in HashMap
	            l_strHashKey = this.getKey(WEB3MarketCodeDef.DEFAULT,
	                WEB3AdminEquitySmallItemDivDef.MINI_STOCK_MARKET);
	            l_eqtypeProductConditionParams =
	                (EqtypeProductConditionParams) l_eqtypeProductConditionParamsList.get(l_strHashKey);
	
	            // Step 1.7.3
	            // Get AdminPMProductCondConfigUnit
	            l_adminPMProductCondConfigUnit =
	                createProductCondConfigUnit(
	                    WEB3MarketCodeDef.DEFAULT,
	                    WEB3AdminEquityLargeItemDivDef.BASIC_INFO,
	                    WEB3AdminEquitySmallItemDivDef.MINI_STOCK_MARKET,
	                    null,
	                    null,
	                    l_tradedProductNextDay,
	                    l_tradedProductTwoDaysLater,
	                    l_eqtypeProductConditionParams);
	
                // �~�j����戵�`�F�b�N
                if (l_tradedProductsNextDay != null &&
                    l_adminPMProductCondConfigUnit.bizDateRegistData == null)
                {
                    l_adminPMProductCondConfigUnit.bizDateRegistData = String.valueOf(BooleanEnum.FALSE.intValue());
                }
                
                if (l_tradedProductsTwoDaysLater != null &&
                    l_adminPMProductCondConfigUnit.nextBizDateRegistData == null)
                {
                    l_adminPMProductCondConfigUnit.nextBizDateRegistData = String.valueOf(BooleanEnum.FALSE.intValue());
                }
                
	            // Step 1.7.4
	            // Add AdminPMProductCondConfigUnit to List
	            l_lisAdminPMProductCondConfigUnit.add(l_adminPMProductCondConfigUnit);
            }
        }

        // Step 1.8
        l_adminPMProductCondConfigUnitList =
            new WEB3AdminPMProductCondConfigUnit[l_lisAdminPMProductCondConfigUnit.size()];
        l_adminPMProductCondConfigUnitList = (WEB3AdminPMProductCondConfigUnit[])
            l_lisAdminPMProductCondConfigUnit.toArray(l_adminPMProductCondConfigUnitList);

        log.exiting(STR_METHOD_NAME);
        return l_adminPMProductCondConfigUnitList;
    }

    /**
     * �icreate�M�p�������ꗗ�j<BR>
     * <BR>
     * �M�p�������̈ꗗ���쐬����B<BR>
     * <BR>
     * �u(�Ǘ��Ҋ������������ݒ�T�[�r�X)create�M�p�������ꗗ�v�Q��<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * createBasicInfoList<BR>
     * <BR>
     * Create stockMarginInfo list<BR>
     * <BR>
     * Refer to the sequence diagram "(administrator: equity product condition setting
     * service)createStockMarginInfoList"<BR>
     * <BR>
     * @@param l_branch - �i���X�I�u�W�F�N�g�j<BR>
     * <BR>
     * l_branch<BR>
     * <BR>
     * @@param l_strMarketCodeList - �i���s��R�[�h�ꗗ�j<BR>
     * <BR>
     * l_strMarketCodeList<BR>
     * <BR>
     * @@param l_tradedProductsToday - �i��������ꗗ(����)�j<BR>
     * <BR>
     * l_tradedProductsToday<BR>
     * <BR>
     * @@param l_tradedProductsNextDay - �i��������ꗗ(����)�j<BR>
     * <BR>
     * l_tradedProductsNextDay<BR>
     * <BR>
     * @@param l_eqtypeProductConditionParamsList - �i�������������ݒ�Params�ꗗ�j<BR>
     * <BR>
     * ������������Params�̈ꗗ<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * l_eqtypeProductConditionParamsList<BR>
     * <BR>
     * @@return WEB3AdminPMProductCondConfigUnit[]
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws NotFoundException NotFoundException
     * @@throws DataFindException DataFindException
     * @@throws DataQueryException DataQueryException
     * @@throws DataNetworkException DataNetworkException
     * @@roseuid 41822FE401FD
     */
    protected WEB3AdminPMProductCondConfigUnit[] createStockMarginInfoList(
        WEB3GentradeBranch l_branch,
        String[] l_strMarketCodeList,
        HashMap l_tradedProductsToday,
        HashMap l_tradedProductsNextDay,
        HashMap l_eqtypeProductConditionParamsList)
        throws DataFindException, DataQueryException,
        DataNetworkException, WEB3BaseException, NotFoundException
    {
        final String STR_METHOD_NAME =
            "createStockMarginInfoList(WEB3GentradeBranch, String[], String[], "
                + "HashMap, HashMap, HashMap)";
        log.entering(STR_METHOD_NAME);

        final String L_STR_LARGE_ITEM_DIV = WEB3AdminEquityLargeItemDivDef.MARGIN_PRODUCT_INFO;
        ArrayList l_lisProductCondConfigUnit = null;
        BranchParams l_branchParams = null;
        ArrayList l_lisSmallItemDiv = new ArrayList();
        int l_intMarketCodeCnt = 0;
        String l_strMarginSysDiv = null;
        String l_strMarginGenDiv = null;
        Iterator l_itrSmallItemDiv = null;
        String l_strSmallItemDiv = null;
        String l_strMarketCode = null;

        EqtypeTradedProductParams l_tradedProductNextDay = null;
        EqtypeTradedProductParams l_tradedProductToday = null;
        EqtypeProductConditionParams l_eqtypeProductConditionsParams = null;
        String l_strKey = null;
        WEB3AdminPMProductCondConfigUnit l_adminPMProductCondConfigUnit = null;
        WEB3AdminPMProductCondConfigUnit[] l_productCondConfigUnitList = null;

        // Step 1.1
        l_lisProductCondConfigUnit = new ArrayList();

        l_intMarketCodeCnt = l_strMarketCodeList.length;
        l_branchParams = (BranchParams) l_branch.getDataSourceObject();
        l_strMarginSysDiv = l_branchParams.getMarginSysDiv();
        l_strMarginGenDiv = l_branchParams.getMarginGenDiv();

        if (WEB3EnforcementDef.ENFORCEMENT.equals(l_strMarginSysDiv))
        {
            l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.MARGIN_SYS_PRODUCT_TYPE);
        }
        if (WEB3EnforcementDef.ENFORCEMENT.equals(l_strMarginGenDiv))
        {
            l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.MARGIN_GEN_PRODUCT_TYPE);
        }

        // Step 1.2
		l_itrSmallItemDiv = (Iterator)l_lisSmallItemDiv.iterator();

		while (l_itrSmallItemDiv.hasNext())
		{
			l_strSmallItemDiv = l_itrSmallItemDiv.next().toString();

            // Step 1.2.1
			for (int i = 0; i < l_intMarketCodeCnt; i++)
			{
				l_strMarketCode = l_strMarketCodeList[i];
				if (l_tradedProductsToday != null)
				{
					l_tradedProductToday =
						(EqtypeTradedProductParams) l_tradedProductsToday.get(l_strMarketCode);
				} else
				{
					l_tradedProductToday = null;
				}

				if (l_tradedProductsNextDay != null)
				{
					l_tradedProductNextDay =
						(EqtypeTradedProductParams) l_tradedProductsNextDay.get(l_strMarketCode);
				} else
				{
					l_tradedProductNextDay = null;
				}

                l_strKey = this.getKey(l_strMarketCode, l_strSmallItemDiv);
                l_eqtypeProductConditionsParams =
                    (EqtypeProductConditionParams) l_eqtypeProductConditionParamsList.get(l_strKey);

                // Step 1.2.1.1
                l_adminPMProductCondConfigUnit =
                    this.createProductCondConfigUnit(
                        l_strMarketCode,
                        L_STR_LARGE_ITEM_DIV,
                        l_strSmallItemDiv,
                        null,
                        null,
                        l_tradedProductToday,
                        l_tradedProductNextDay,
                        l_eqtypeProductConditionsParams);

                // Step 1.2.1.2
                l_lisProductCondConfigUnit.add(l_adminPMProductCondConfigUnit);

            }
        }

        // Step 1.3
        l_productCondConfigUnitList =
            new WEB3AdminPMProductCondConfigUnit[l_lisProductCondConfigUnit.size()];
        l_productCondConfigUnitList =
            (WEB3AdminPMProductCondConfigUnit[])
                l_lisProductCondConfigUnit.toArray(l_productCondConfigUnitList);
        log.exiting(STR_METHOD_NAME);

        return l_productCondConfigUnitList;
    }

    /**
     * �icreate�ϑ��ۏ؋����ꗗ�j<BR>
     * <BR>
     * �ϑ��ۏ؋������̈ꗗ���쐬����B<BR>
     * <BR>
     * �u(�Ǘ��Ҋ������������ݒ�T�[�r�X)create�ϑ��ۏ؋����ꗗ�v�Q��<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * createDepositRateList<BR>
     * <BR>
     * Create depositRate list<BR>
     * <BR>
     * Refer to the sequence diagram "(administrator: equity product condition setting
     * service)createDepositRateList"<BR>
     * <BR>
     * @@param l_strMarketCodeList - �i���s��R�[�h�ꗗ�j<BR>
     * <BR>
     * l_strMarketCodeList<BR>
     * <BR>
     * @@param l_tradedProductsToday - �i��������ꗗ(����)�j<BR>
     * <BR>
     * l_tradedProductsToday<BR>
     * <BR>
     * @@param l_tradedProductsNextDay - �i��������ꗗ(����)�j<BR>
     * <BR>
     * l_tradedProductsNextDay<BR>
     * <BR>
     * @@param l_eqtypeProductConditionParamsList - �i�������������ݒ�Params�ꗗ�j<BR>
     * <BR>
     * ������������Params�̈ꗗ<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * l_eqtypeProductConditionParamsList<BR>
     * <BR>
     * @@return WEB3AdminPMProductCondConfigUnit[]
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws NotFoundException NotFoundException
     * @@throws DataFindException DataFindException
     * @@throws DataQueryException DataQueryException
     * @@throws DataNetworkException DataNetworkException
     * @@roseuid 41822FF90028
     */
    protected WEB3AdminPMProductCondConfigUnit[] createDepositRateList(
        String[] l_strMarketCodeList,
        HashMap l_tradedProductsToday,
        HashMap l_tradedProductsNextDay,
        HashMap l_eqtypeProductConditionParamsList)
        throws DataFindException, DataQueryException,
        DataNetworkException, WEB3BaseException, NotFoundException
    {
        final String STR_METHOD_NAME = "createDepositRateList(String[], HashMap, HashMap, HashMap)";
        log.entering(STR_METHOD_NAME);

        ArrayList l_arrAdminPMProductCondConfigUnit = null;
        ArrayList l_arrSmallItemDiv = new ArrayList();
        int l_intMarketCodeCnt = 0;
        Iterator l_itrSmallItemDiv = null;
        String l_strSmallItemDiv = null;
        String l_strMarketCode = null;
        String l_strKey = null;
        String l_strLargeItemDiv = WEB3AdminEquityLargeItemDivDef.DEPOSIT_RATE;
        EqtypeTradedProductParams l_tradedProductToday = null;
        EqtypeTradedProductParams l_tradedProductNextDay = null;
        EqtypeProductConditionParams l_eqtypeProductConditionParams = null;
        WEB3AdminPMProductCondConfigUnit l_adminPMProductCondConfigUnit = null;
        WEB3AdminPMProductCondConfigUnit[] l_productConditionInfo = null;

        // Step 1.1
        l_arrAdminPMProductCondConfigUnit = new ArrayList();

        l_arrSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.LONG_MARGIN_DEPOSIT_RATE);
        l_arrSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.SHORT_MARGIN_DEPOSIT_RATE);

        l_intMarketCodeCnt = l_strMarketCodeList.length;

		l_itrSmallItemDiv = l_arrSmallItemDiv.iterator();

        // Step 1.2
		while (l_itrSmallItemDiv.hasNext())
		{
			l_strSmallItemDiv = l_itrSmallItemDiv.next().toString();

            // Step 1.2.1
			for (int i = 0; i < l_intMarketCodeCnt; i++)
			{
				l_strMarketCode = l_strMarketCodeList[i];
				if (l_tradedProductsToday != null)
				{
					l_tradedProductToday =
						(EqtypeTradedProductParams) l_tradedProductsToday.get(l_strMarketCode);
				} else
				{
					l_tradedProductToday = null;
				}

				if (l_tradedProductsNextDay != null)
				{
					l_tradedProductNextDay =
						(EqtypeTradedProductParams) l_tradedProductsNextDay.get(l_strMarketCode);
				} else
				{
					l_tradedProductNextDay = null;
				}

                l_strKey = this.getKey(l_strMarketCode, l_strSmallItemDiv);
                l_eqtypeProductConditionParams =
                    (EqtypeProductConditionParams) l_eqtypeProductConditionParamsList.get(l_strKey);
                // Step 1.2.1.1
                l_adminPMProductCondConfigUnit =
                    this.createProductCondConfigUnit(
                        l_strMarketCode,
                        l_strLargeItemDiv,
                        l_strSmallItemDiv,
                        null,
                        null,
                        l_tradedProductToday,
                        l_tradedProductNextDay,
                        l_eqtypeProductConditionParams);
                // Step 1.2.1.2
                l_arrAdminPMProductCondConfigUnit.add(l_adminPMProductCondConfigUnit);
            }
        }

        l_arrSmallItemDiv = new ArrayList();

        // Step 1.3
        l_arrSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.LONG_CASH_MARGIN_DEPOSIT_RATE);
        l_arrSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.SHORT_CASH_MARGIN_DEPOSIT_RATE);

		l_itrSmallItemDiv = l_arrSmallItemDiv.iterator();

		while (l_itrSmallItemDiv.hasNext())
		{
			l_strSmallItemDiv = l_itrSmallItemDiv.next().toString();

            // Step 1.3.1
			for (int j = 0; j < l_intMarketCodeCnt; j++)
			{
				l_strMarketCode = l_strMarketCodeList[j];

				if (l_tradedProductsToday != null)
				{
					l_tradedProductToday =
						(EqtypeTradedProductParams) l_tradedProductsToday.get(l_strMarketCode);
				} else
				{
					l_tradedProductToday = null;
				}
				if (l_tradedProductsNextDay != null)
				{
					l_tradedProductNextDay =
						(EqtypeTradedProductParams) l_tradedProductsNextDay.get(l_strMarketCode);
				} else
				{
					l_tradedProductNextDay = null;
				}
                
	            l_strKey = this.getKey(l_strMarketCode, l_strSmallItemDiv);
	            l_eqtypeProductConditionParams =
	                (EqtypeProductConditionParams) l_eqtypeProductConditionParamsList.get(l_strKey);
                // Step 1.3.1.1
                l_adminPMProductCondConfigUnit =
                    this.createProductCondConfigUnit(
                        l_strMarketCode,
                        l_strLargeItemDiv,
                        l_strSmallItemDiv,
                        null,
                        null,
                        l_tradedProductToday,
                        l_tradedProductNextDay,
                        l_eqtypeProductConditionParams);
                // Step 1.3.1.2
                l_arrAdminPMProductCondConfigUnit.add(l_adminPMProductCondConfigUnit);
            }
        }
        // Step 1.4
        l_productConditionInfo =
            new WEB3AdminPMProductCondConfigUnit[l_arrAdminPMProductCondConfigUnit.size()];
        l_productConditionInfo =
            (WEB3AdminPMProductCondConfigUnit[]) l_arrAdminPMProductCondConfigUnit.toArray(
                l_productConditionInfo);

        log.exiting(STR_METHOD_NAME);
        return l_productConditionInfo;
    }

    /**
     * �icreate��p�L���،����ꗗ�j<BR>
     * <BR>
     * ��p�L���،����̈ꗗ���쐬����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��Ҋ������������ݒ�T�[�r�X)create��p�L���،����ꗗ�v�Q��<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * createSubstituteSecurityInfoList<BR>
     * <BR>
     * Create substituteSecurityInfo list<BR>
     * <BR>
     * Refer to the sequence diagram "(administrator: equity product condition setting
     * service)createSubstituteSecurityInfoList"<BR>
     * <BR>
     * @@param l_branch Branch
     * @@param l_productParams - �i����Params�I�u�W�F�N�g�j<BR>
     * <BR>
     * l_productParams<BR>
     * <BR>
     * @@param l_eqtypeProductConditionParamsList - �i�������������ݒ�Params�ꗗ�j<BR>
     * <BR>
     * ������������Params�̈ꗗ<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * l_eqtypeProductConditionParamsList<BR>
     * @@return WEB3AdminPMProductCondConfigUnit[]
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws NotFoundException NotFoundException
     * @@throws DataFindException DataFindException
     * @@throws DataQueryException DataQueryException
     * @@throws DataNetworkException DataNetworkException
     * <BR>
     * @@roseuid 4182301201FD
     */
    protected WEB3AdminPMProductCondConfigUnit[] createSubstituteSecurityInfoList(
        Branch l_branch,
        ProductParams l_productParams,
        HashMap l_eqtypeProductConditionParamsList)
        throws DataFindException, DataQueryException,
        DataNetworkException, WEB3BaseException, NotFoundException
    {
        final String STR_METHOD_NAME = "createSubstituteSecurityInfoList("
                                                                + "Branch, Product, HashMap)";
        log.entering(STR_METHOD_NAME);

        final String L_STR_MARKET_CODE = WEB3MarketCodeDef.DEFAULT;
        final String L_STR_SMALL_ITEM_DIV_MARGIN = WEB3AdminEquitySmallItemDivDef.MARGIN_RATIO;
        final String L_STR_LARGE_ITEM_DIV = WEB3AdminEquityLargeItemDivDef.SUBSTITUTE_SECURITY_INFO;
        final String L_STR_SMALL_ITEM_DIV_SECURITIES =
            WEB3AdminEquitySmallItemDivDef.SECURITIES_ESTIMATION_RATIO;
        final String L_STR_SMALL_ITEM_DIV_ESTIMATION =
            WEB3AdminEquitySmallItemDivDef.ESTIMATION_PRICE;

        ArrayList l_lisPMProductCondConfigUnit = null;
        String l_strkey = null;
        String l_strMarginSysDiv = null;
        String l_strMarginGenDiv = null;
        boolean l_blnInstitutionStockEvaluation = false;

        WEB3EquityProduct l_equityProduct = null;
        EqtypeTradedProductParams l_tradedProductNextDay = null;
        EqtypeTradedProductParams l_tradedProductToday = null;
        EqtypeProductConditionParams l_eqtypeProductConditionsParams = null;
        WEB3AdminPMProductCondConfigUnit l_adminPMProductCondConfigUnit = null;
        WEB3GentradeInstitution l_gentradeInsitution =
            (WEB3GentradeInstitution) l_branch.getInstitution();
        WEB3AdminPMProductCondConfigUnit[] l_productCondConfigUnit = null;
        BranchParams l_branchParams = null;

        // step 1.1
        l_lisPMProductCondConfigUnit = new ArrayList();
        l_branchParams = (BranchParams) l_branch.getDataSourceObject();

        // step1.2
        l_strMarginSysDiv = l_branchParams.getMarginSysDiv();
        l_strMarginGenDiv = l_branchParams.getMarginGenDiv();

        if (WEB3EnforcementDef.ENFORCEMENT.equals(l_strMarginSysDiv)
            || WEB3EnforcementDef.ENFORCEMENT.equals(l_strMarginGenDiv))
        {
            l_strkey = this.getKey(L_STR_MARKET_CODE, L_STR_SMALL_ITEM_DIV_MARGIN);
            l_eqtypeProductConditionsParams =
                (EqtypeProductConditionParams) l_eqtypeProductConditionParamsList.get(l_strkey);

            // Step 1.2.1
            l_adminPMProductCondConfigUnit =
                createProductCondConfigUnit(
                    L_STR_MARKET_CODE,
                    L_STR_LARGE_ITEM_DIV,
                    L_STR_SMALL_ITEM_DIV_MARGIN,
                    l_productParams,
                    null,
                    null,
                    null,
                    l_eqtypeProductConditionsParams);

            // Step 1.2.2
            l_lisPMProductCondConfigUnit.add(l_adminPMProductCondConfigUnit);
        }

        // Step1.3
        l_blnInstitutionStockEvaluation = l_gentradeInsitution.isInstitutionStockEvaluation();

        // if a securities enforcement company
        if (l_blnInstitutionStockEvaluation)
        {
            l_strkey = this.getKey(L_STR_MARKET_CODE, L_STR_SMALL_ITEM_DIV_SECURITIES);
            l_eqtypeProductConditionsParams =
                (EqtypeProductConditionParams) l_eqtypeProductConditionParamsList.get(l_strkey);

            // Step 1.3.1
            l_adminPMProductCondConfigUnit =
                createProductCondConfigUnit(
                    L_STR_MARKET_CODE,
                    L_STR_LARGE_ITEM_DIV,
                    L_STR_SMALL_ITEM_DIV_SECURITIES,
                    l_productParams,
                    null,
                    null,
                    null,
                    l_eqtypeProductConditionsParams);

            // Step 1.3.2
            l_lisPMProductCondConfigUnit.add(l_adminPMProductCondConfigUnit);

        }

        // Step 1.4
        if ((WEB3EnforcementDef.ENFORCEMENT.equals(l_strMarginSysDiv)
            || WEB3EnforcementDef.ENFORCEMENT.equals(l_strMarginGenDiv))
                || l_blnInstitutionStockEvaluation)
        {
            l_strkey = this.getKey(L_STR_MARKET_CODE, L_STR_SMALL_ITEM_DIV_ESTIMATION);
            l_eqtypeProductConditionsParams =
                (EqtypeProductConditionParams) l_eqtypeProductConditionParamsList.get(l_strkey);

            // Step 1.4.1
            l_adminPMProductCondConfigUnit =
                createProductCondConfigUnit(
                    L_STR_MARKET_CODE,
                    L_STR_LARGE_ITEM_DIV,
                    L_STR_SMALL_ITEM_DIV_ESTIMATION,
                    l_productParams,
                    l_equityProduct,
                    l_tradedProductToday,
                    l_tradedProductNextDay,
                    l_eqtypeProductConditionsParams);

            // Step 1.4.2
            l_lisPMProductCondConfigUnit.add(l_adminPMProductCondConfigUnit);
        }

        // Step 1.5
        l_productCondConfigUnit =
            new WEB3AdminPMProductCondConfigUnit[l_lisPMProductCondConfigUnit.size()];
        l_productCondConfigUnit =
            (WEB3AdminPMProductCondConfigUnit[]) l_lisPMProductCondConfigUnit.toArray(
                l_productCondConfigUnit);

        log.exiting(STR_METHOD_NAME);
        return l_productCondConfigUnit;
    }

    /**
     * �icreate�l�i���ꗗ�j<BR>
     * <BR>
     * �l�i���̈ꗗ���쐬����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��Ҋ������������ݒ�T�[�r�X)create�l�i���ꗗ�v�Q��<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * createPriceInfoList<BR>
     * <BR>
     * Create priceInfo list<BR>
     * <BR>
     * Refer to the sequence diagram "(administrator: equity product condition setting
     * service)createPriceInfoList"<BR>
     * <BR>
     * @@param l_branch - �i���X�I�u�W�F�N�g�j<BR>
     * <BR>
     * l_branch<BR>
     * <BR>
     * @@param l_strMarketCodeList - �i���s��R�[�h�ꗗ�j<BR>
     * <BR>
     * l_strMarketCodeList<BR>
     * <BR>
     *
     * @@param l_productParams - �i����Params�I�u�W�F�N�g�j<BR>
     * <BR>
     * l_productParams<BR>
     * <BR>
     * @@param l_tradedProductsToday - �i��������ꗗ(����)�j<BR>
     * <BR>
     * l_tradedProductsToday<BR>
     * <BR>
     * @@param l_tradedProductsNextDay - �i��������ꗗ(����)�j<BR>
     * <BR>
     * l_tradedProductsNextDay<BR>
     * <BR>
     * @@param l_eqtypeProductConditionParamsList - �i�������������ݒ�Params�ꗗ�j<BR>
     * <BR>
     * ������������Params�̈ꗗ<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * l_eqtypeProductConditionParamsList<BR>
     * <BR>
     * @@return WEB3AdminPMProductCondConfigUnit[]
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws NotFoundException NotFoundException
     * @@throws DataFindException DataFindException
     * @@throws DataQueryException DataQueryException
     * @@throws DataNetworkException DataNetworkException
     * @@roseuid 4182304F00A5
     */
    protected WEB3AdminPMProductCondConfigUnit[] createPriceInfoList(
        WEB3GentradeBranch l_branch,
        String[] l_strMarketCodeList,
        ProductParams l_productParams,
        HashMap l_tradedProductsToday,
        HashMap l_tradedProductsNextDay,
        HashMap l_eqtypeProductConditionParamsList)
        throws DataFindException, DataQueryException,
        DataNetworkException, WEB3BaseException, NotFoundException
    {
        final String STR_METHOD_NAME =
            "createPriceInfoList(WEB3GentradeBranch, String[]"
                + "Product, HashMap, HashMap HashMap, HashMap)";
        log.entering(STR_METHOD_NAME);

        final String L_STR_MARKETCODE_DEFAULT = WEB3MarketCodeDef.DEFAULT;
        final String L_STR_SMALL_ITEM_DIV_ESTIMATION =
            WEB3AdminEquitySmallItemDivDef.ESTIMATION_PRICE;

        ArrayList l_lisAdminPMProductCondConfigUnit = null;
        String l_strMarginSysDiv = null;
        String l_strMarginGenDiv = null;
        String l_strkey = null;
        int l_intMarketCodeCnt = 0;
        ArrayList l_lisSmallItemDiv = new ArrayList();
        Iterator l_itrSmallItemDiv = null;
        String l_strSmallItemDiv = null;
        String l_strMarketCode = null;
        boolean l_blnStockEvaluation = false;

        BranchParams l_branchParams = null;
        WEB3GentradeInstitution l_genTradeInstitution = null;
        EqtypeTradedProductParams l_tradedProductNextDay = null;
        EqtypeTradedProductParams l_tradedProductToday = null;
        EqtypeProductConditionParams l_eqtypeProductConditionsParams = null;
        WEB3AdminPMProductCondConfigUnit l_adminPMProductCondConfigUnit = null;
        WEB3AdminPMProductCondConfigUnit[] l_productCondConfigUnit = null;

        // Step 1.1
        l_lisAdminPMProductCondConfigUnit = new ArrayList();

        // Step 1.2
        l_branchParams = (BranchParams) l_branch.getDataSourceObject();
        l_strMarginSysDiv = l_branchParams.getMarginSysDiv();
        l_strMarginGenDiv = l_branchParams.getMarginGenDiv();
        l_genTradeInstitution = (WEB3GentradeInstitution) l_branch.getInstitution();
        l_blnStockEvaluation = l_genTradeInstitution.isInstitutionStockEvaluation();
        //l_institution
        /*
         * If a margin enforcement  company  or securities  estimation enforcement
         * company
         */
        if (WEB3EnforcementDef.ENFORCEMENT.equals(l_strMarginSysDiv)
            || WEB3EnforcementDef.ENFORCEMENT.equals(l_strMarginGenDiv)
            || l_blnStockEvaluation)
        {

            l_strkey = this.getKey(L_STR_MARKETCODE_DEFAULT, L_STR_SMALL_ITEM_DIV_ESTIMATION);
            l_eqtypeProductConditionsParams =
                (EqtypeProductConditionParams) l_eqtypeProductConditionParamsList.get(l_strkey);

            // Step 1.2.1
            l_adminPMProductCondConfigUnit =
                this.createProductCondConfigUnit(
                    L_STR_MARKETCODE_DEFAULT,
                    WEB3AdminEquityLargeItemDivDef.SUBSTITUTE_SECURITY_INFO,
                    L_STR_SMALL_ITEM_DIV_ESTIMATION,
                    l_productParams,
                    null,
                    null,
                    null,
                    l_eqtypeProductConditionsParams);

            // Step 1.2.2
            l_lisAdminPMProductCondConfigUnit.add(l_adminPMProductCondConfigUnit);
        }

        // Step 1.3
        l_intMarketCodeCnt = l_strMarketCodeList.length;
        l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.LAST_CLOSING_PRICE);
        l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.BASE_PRICE);
        l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.PRICE_RANGE_TYPE);
        l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.PRICE_RANGE_UNIT_TYPE);
        l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.LOW_COMPULSIVE_PRICE_RANGE);
        l_lisSmallItemDiv.add(WEB3AdminEquitySmallItemDivDef.HIGH_COMPULSIVE_PRICE_RANGE);


        /**
         * Loop process  for as many  times  as parameter.marketCodeList
         *
         */
        for (int i = 0; i < l_intMarketCodeCnt; i++)
        {
	        l_itrSmallItemDiv = l_lisSmallItemDiv.iterator();
            l_strMarketCode = l_strMarketCodeList[i];
            if (l_tradedProductsToday != null)
            {
                l_tradedProductToday =
                    (EqtypeTradedProductParams) l_tradedProductsToday.get(l_strMarketCode);
            } else
            {
                l_tradedProductToday = null;
            }
            if (l_tradedProductsNextDay != null)
            {
                l_tradedProductNextDay =
                    (EqtypeTradedProductParams) l_tradedProductsNextDay.get(l_strMarketCode);
            } else
            {
                l_tradedProductNextDay = null;
            }

            // Step 1.3.1
            while (l_itrSmallItemDiv.hasNext())
            {
                l_strSmallItemDiv = l_itrSmallItemDiv.next().toString();

                l_strkey = this.getKey(l_strMarketCode, l_strSmallItemDiv);
                l_eqtypeProductConditionsParams =
                    (EqtypeProductConditionParams) l_eqtypeProductConditionParamsList.get(l_strkey);

                // Step 1.3.1.1
                l_adminPMProductCondConfigUnit =
                    this.createProductCondConfigUnit(
                        l_strMarketCode,
                        WEB3AdminEquityLargeItemDivDef.PRICE_INFO,
                        l_strSmallItemDiv,
                        null,
                        null,
                        l_tradedProductToday,
                        l_tradedProductNextDay,
                        l_eqtypeProductConditionsParams);

                // Step 1.3.1.2
                l_lisAdminPMProductCondConfigUnit.add(l_adminPMProductCondConfigUnit);
            }

        }

        //Step 1.4
        l_productCondConfigUnit =
            new WEB3AdminPMProductCondConfigUnit[l_lisAdminPMProductCondConfigUnit.size()];
        l_productCondConfigUnit =
            (WEB3AdminPMProductCondConfigUnit[]) l_lisAdminPMProductCondConfigUnit.toArray(
                l_productCondConfigUnit);

        log.exiting(STR_METHOD_NAME);
        return l_productCondConfigUnit;
    }

    /**
     * �icreate���s��R�[�h�ꗗ�j<BR>
     * <BR>
     * ��������ꂵ�Ă���s��R�[�h�̈ꗗ���쐬����B<BR>
     * ���s��R�[�h�͏����ŕԋp�����B<BR>
     * <BR>
     * �P�j��������ꗗ(����)�̎s��R�[�h�ꗗ���擾����<BR>
     * �@@�p�����[�^.��������ꗗ(����).keySet().toArray()���\�b�h���R�[������B<BR>
     * <BR>
     * �Q�j��������ꗗ(����)�̎s��R�[�h�ꗗ���擾����B<BR>
     * �@@�p�����[�^.��������ꗗ(����).keySet().toArray()���\�b�h���R�[������B<BR>
     * <BR>
     * �R�jTreeMap�𐶐�����B<BR>
     * <BR>
     * �S�j��������TreeMap.put()���\�b�h���R�[�����A�P�j�̖߂�l��S�Ċi�[����B<BR>
     * �@@���P�j�̖߂�l�̐���Loop����B<BR>
     * <BR>
     * �@@[put()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@key�F�@@new Integer(�P�j�̖߂�l[index])<BR>
     * �@@�@@obj�F�@@�P�j�̖߂�l[index]<BR>
     * <BR>
     * �T�j��������TreeMap.put()���\�b�h���R�[�����A�Q�j�̖߂�l��S�Ċi�[����B<BR>
     * �@@���Q�j�̖߂�l�̐���Loop����B<BR>
     * <BR>
     * �@@[put()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@key�F�@@new Integer(�Q�j�̖߂�l[index])<BR>
     * �@@�@@obj�F�@@�Q�j�̖߂�l[index]<BR>
     * <BR>
     * �U�j��������TreeMap.values.toArray()�̖߂�l��ԋp����B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Create marketCodeList listed with product listed<BR>
     * ��marketCode is returned in ascending order<BR>
     * <BR>
     * 1)Acquire marketCodeList of tradedProductsToday<BR>
     * �@@Call parameter.l_tradedProductsToday.keySet().toArray() method<BR>
     * <BR>
     * 2)Acquire marketCodeList of tradedProductsNextDay<BR>
     * Call parameter.l_tradedProductsNextDay.keySet().toArray() method<BR>
     * <BR>
     * 3)Carete TreeMap<BR>
     * <BR>
     * 4)Call the createdTreeMap.put() method, store all return values of 1)<BR>
     * �@@��Loop for as many times as return values of 1)<BR>
     * <BR>
     * �@@[parameter set into put()]<BR>
     * �@@�@@key: return value [index] of new Integer(�P�j<BR>
     * �@@�@@obj: return value [index] of 1)<BR>
     * <BR>
     * 5) Call the created TreeMap.put() method, and store all return values of 2)<BR>
     * �@@��Loop for as many times as return values of 2)<BR>
     * <BR>
     * �@@[parameter set into put()]<BR>
     * �@@�@@key: return value [index] of new Integer(�Q)<BR>
     * �@@�@@obj: return value [index] of 2)<BR>
     * <BR>
     * 6)Return return values of the created TreeMap.values.toArray()<BR>
     * <BR>
     * @@param l_tradedProductsToday - �i��������ꗗ(����)�j<BR>
     * <BR>
     * l_tradedProductsToday<BR>
     * <BR>
     * @@param l_tradedProductsNextDay - �i��������ꗗ(����)�j<BR>
     * <BR>
     * l_tradedProductsNextDay<BR>
     * <BR>
     * @@return String[]
     * @@roseuid 4185A8320189
     */
    protected String[] createListedMarketCodeList(
        HashMap l_tradedProductsToday,
        HashMap l_tradedProductsNextDay)
    {
        final String STR_METHOD_NAME = "createListedMarketCodeList(HashMap, HashMap)";
        log.entering(STR_METHOD_NAME);

        String[] l_strMarketCodeList = null;
        List l_lisMarketCode = null;
        String[] l_strKeyTodayList = null;
        String[] l_strKeyNextDayList = null;
        Map l_tmMarketCodeList = null;
        int l_intKeyTodaySize = 0;
        int l_intKeyNextDaySize = 0;

        if (l_tradedProductsToday != null)
        {
            l_strKeyTodayList = new String[l_tradedProductsToday.size()];
            /*
             * 1)Acquire marketCodeList of tradedProductsToday
             * �@@Call parameter.l_tradedProductsToday.keySet().toArray() method
             */
            l_strKeyTodayList =
                (String[]) (l_tradedProductsToday.keySet()).toArray(l_strKeyTodayList);
            l_intKeyTodaySize = l_strKeyTodayList.length;
        }

        if (l_tradedProductsNextDay != null)
        {
            l_strKeyNextDayList = new String[l_tradedProductsNextDay.size()];
            /*
             * 2)Acquire marketCodeList of tradedProductsNextDay<BR>
             * Call parameter.l_tradedProductsNextDay.keySet().toArray() method<BR>
             */
            l_strKeyNextDayList =
                (String[]) (l_tradedProductsNextDay.keySet()).toArray(l_strKeyNextDayList);
            l_intKeyNextDaySize = l_strKeyNextDayList.length;
        }

        // 3)Create TreeMap
        l_tmMarketCodeList = new TreeMap();

        /*
         * 4)Call the createdTreeMap.put() method, store all return values of 1)
         * �@@��Loop for as many times as return values of 1)
         * �@@[parameter set into put()]
         * �@@�@@key: return value [index] of new Integer(�P�j
         * �@@�@@obj: return value [index] of 1)
         */
        for (int i = 0; i < l_intKeyTodaySize; i++)
        {
            l_tmMarketCodeList.put(new Integer(l_strKeyTodayList[i]), l_strKeyTodayList[i]);
        }

        /*
         * 5) Call the created TreeMap.put() method, and store all return values of 2)
         * �@@��Loop for as many times as return values of 2)
         * �@@[parameter set into put()]
         * �@@�@@key: return value [index] of new Integer(�Q)
         * �@@�@@obj: return value [index] of 2)
         */
        for (int i = 0; i < l_intKeyNextDaySize; i++)
        {
            l_tmMarketCodeList.put(new Integer(l_strKeyNextDayList[i]), l_strKeyNextDayList[i]);
        }
        l_lisMarketCode = new ArrayList(l_tmMarketCodeList.values());
        l_strMarketCodeList = new String[l_lisMarketCode.size()];
        l_strMarketCodeList = (String[]) l_lisMarketCode.toArray(l_strMarketCodeList);

        log.exiting(STR_METHOD_NAME);

        // 6)Return return values of the created TreeMap.values.toArray()
        return l_strMarketCodeList;
    }

    /**
     * �icreate���������ݒ���j<BR>
     * <BR>
     * ����K�������쐬����B<BR>
     * <BR>
     * �P�j���������ݒ���C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j�e�����ڋ敪�ŋ��ʂ̃v���p�e�B���Z�b�g����B<BR>
     * �@@���������ݒ���.�區�ڋ敪 = �p�����[�^.�區�ڋ敪<BR>
     * �@@���������ݒ���.�����ڋ敪 = �p�����[�^.�����ڋ敪<BR>
     * �@@���������ݒ���.�s��R�[�h = �p�����[�^.�s��R�[�h<BR>
     * �@@<BR>
     * �R�jthis.set�ŐV�f�[�^()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[set�ŐV�f�[�^()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@���������ݒ���F�@@�Q�j�ɂăv���p�e�B�Z�b�g�������������ݒ���<BR>
     * �@@�@@����Params�F�@@�p�����[�^.����Params<BR>
     * �@@�@@���������F�@@�p�����[�^.��������<BR>
     * �@@�@@�������(����)�F�@@�p�����[�^.�������(����)<BR>
     * �@@�@@�������(����)�F�@@�p�����[�^.�������(����)<BR>
     * �@@�@@�������������ݒ�Params�F�@@�p�����[�^.�������������ݒ�Params<BR>
     * <BR>
     * �S�j�v���p�e�B�Z�b�g�������������ݒ����ԋp����B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * createProductCondConfigUnit<BR>
     * <BR>
     * Create trade regulation infomation<BR>
     * <BR>
     * 1)Create WEB3AdminPMProductCondConfigUnit instance<BR>
     * <BR>
     * 2)Set the common properties in each smallItemDiv<BR>
     * �@@l_productConditionInfo.largeItemDiv = parameter.largeItemDiv<BR>
     * �@@l_productConditionInfo.smallItemDiv = parameter.smallItemDiv<BR>
     * �@@l_productConditionInfo.marketCode = parameter.marketCode<BR>
     * �@@<BR>
     * 3)Call this.setLatestData()<BR>
     * <BR>
     * �@@[parameter set into setLatestData()]<BR>
     * �@@�@@l_productConditionInfo: productConditionInfoset in 'Property Set' at 2)<BR>
     * �@@�@@l_productParams: parameter.productParams<BR>
     * �@@�@@l_equityProduct: parameter.equityProduct<BR>
     * �@@�@@l_tradedProductsToday: parameter.tradedProductsToday<BR>
     * �@@�@@l_tradedProductsNextDay: parameter.tradedProductsNextDay<BR>
     * �@@�@@l_eqtypeProductConditionParams: parameter.eqtypeProductConditionParams<BR>
     * <BR>
     * 4) Return WEB3AdminPMProductCondConfigUnit set in 'Property Set'<BR>
     * <BR>
     * @@param l_strMarketCode - �i�s��R�[�h�j<BR>
     * <BR>
     * 0�F�@@���̑�(�s�ꋤ��)<BR>
     * 1�F�@@����<BR>
     * 2�F�@@���<BR>
     * 3�F�@@���É�<BR>
     * 6�F�@@����<BR>
     * 8�F�@@�D�y<BR>
     * 9�F�@@NNM<BR>
     * 10�F�@@JASDAQ<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * marketCode<BR>
     * 0: Def.DEFAULT(common to all markets)<BR>
     * 1: Def.TOKYO<BR>
     * 2: Def.OSAKA<BR>
     * 3: Def.NAGOYA<BR>
     * 6: Def.FUKUOKA<BR>
     * 8: Def.SAPPORO<BR>
     * 9: Def.NNM<BR>
     * 10: Def.JASDAQ<BR>
     * <BR>
     *
     *
     * @@param l_largeItemDiv - �i�區�ڋ敪�j<BR>
     * <BR>
     * �區�ڋ敪<BR>
     * <BR>
     * ����`�l�ɂ��Ă�DB���C�A�E�g<BR>
     * �@@�u�������������ݒ�e�[�u���d�l.xls�v�Q��<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * largeItemDiv<BR>
     * <BR>
     * ��Refer to the DB layout "eqtype_product_condition_table.xls" about defined
     * values<BR>
     * <BR>
     * @@param l_strSmallItemDivList - �i�����ڋ敪�ꗗ�j<BR>
     * <BR>
     * �������ڋ敪�̔C�ӂ̒l��v�f�Ƃ���z��<BR>
     * <BR>
     * ����`�l�ɂ��Ă�DB���C�A�E�g<BR>
     * �@@�u�������������ݒ�e�[�u���d�l.xls�v�Q��<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * smallItemDivList<BR>
     * <BR>
     * ��An array with elements of arbitrary values of smallItemDiv<BR>
     * <BR>
     * ��Refer to the DB layout "eqtype_product_condition_table.xls" about defined
     * values<BR>
     * <BR>
     * @@param l_productParams ProductParams
     * @@param l_equityProduct - �i���������j<BR>
     * <BR>
     * ���������I�u�W�F�N�g<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * l_equityProduct<BR>
     * <BR>
     * l_equityProduct object<BR>
     * <BR>
     * @@param l_tradedProductsToday - �i��������ꗗ(����)�j<BR>
     * <BR>
     * ��������ꗗ(����)<BR>
     * <BR>
     * l_tradedProductsToday<BR>
     * <BR>
     * @@param l_tradedProductsTwoDaysLater - �i��������ꗗ(���X��)�j<BR>
     * <BR>
     * ��������ꗗ(���X��)<BR>
     * <BR>
     * l_tradedProductsTwoDaysLater<BR>
     * <BR>
     * @@param l_eqtypeProductConditionParams - �i�������������ݒ�Params�j<BR>
     * <BR>
     * ������������Params<BR>
     * <BR>
     * l_eqtypeProductConditionParams<BR>
     * <BR>
     *
     * @@return eqtypeadmin.message.WEB3AdminPMProductCondConfigUnit
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws NotFoundException NotFoundException
     * @@throws DataFindException DataFindException
     * @@throws DataQueryException DataQueryException
     * @@throws DataNetworkException DataNetworkException
     * @@roseuid 41861DA40034
     */
    protected WEB3AdminPMProductCondConfigUnit createProductCondConfigUnit(
        String l_strMarketCode,
        String l_largeItemDiv,
        String l_strSmallItemDivList,
        ProductParams l_productParams,
        WEB3EquityProduct l_equityProduct,
        EqtypeTradedProductParams l_tradedProductsToday,
        EqtypeTradedProductParams l_tradedProductsTwoDaysLater,
        EqtypeProductConditionParams l_eqtypeProductConditionParams)
        throws DataFindException, DataQueryException,
        DataNetworkException, WEB3BaseException, NotFoundException
    {
        final String STR_METHOD_NAME =
            "createProductCondConfigUnit(String, String, String, Product, WEB3EquityProduct, "
                + "EqtypeTradedProductParams, EqtypeTradedProductParams, "
                + "EqtypeProductConditionParams)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminPMProductCondConfigUnit l_productConditionInfo = null;

        // 1)Create WEB3AdminPMProductCondConfigUnit instance
        l_productConditionInfo = new WEB3AdminPMProductCondConfigUnit();

        /*
         * 2)Set the common properties in each smallItemDiv
         * �@@l_productConditionInfo.largeItemDiv = parameter.largeItemDiv<BR>
         * �@@l_productConditionInfo.smallItemDiv = parameter.smallItemDiv<BR>
         * �@@l_productConditionInfo.marketCode = parameter.marketCode<BR>
         */
        l_productConditionInfo.marketCode = l_strMarketCode;
        l_productConditionInfo.largeItemDiv = l_largeItemDiv;
        l_productConditionInfo.smallItemDiv = l_strSmallItemDivList;
        this.setLatestData(l_productConditionInfo,
            l_productParams,
            l_equityProduct,
            l_tradedProductsToday,
            l_tradedProductsTwoDaysLater,
            l_eqtypeProductConditionParams);

        log.exiting(STR_METHOD_NAME);

        return l_productConditionInfo;
    }

    /**
     * �iset�ŐV�f�[�^�j<BR>
     * <BR>
     * �����̖��������ݒ����DB�ŐV�f�[�^��ݒ肷��B<BR>
     * <BR>
     * �P�j�s�ꋤ�ʐݒ�̏ꍇ�A<BR>
     * �@@(�p�����[�^.���������ݒ���.�����ڋ敪 == <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@("��������������~" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"���x�M�p���������~" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"��ʐM�p���������~" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"������" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"��p�|��" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"��p�]���P��" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"�a����،��]���|��"))<BR>
     * �@@�ȉ��̏������s���B<BR>
     * �@@�P�|�P�j�p�����[�^.���������ݒ���.�����ڋ敪�ɂ�蕪�򂵁A<BR>
     * �@@�@@�@@�@@�@@�ŐV�f�[�^���Z�b�g����B<BR>
     * <BR>
     * �@@�p�����[�^.���������ݒ���.�����ڋ敪���A<BR>
     * �@@["��������������~"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.���������ݒ���.�o�^�l(����) =
     * �p�����[�^.��������.��������������~<BR>
     * <BR>
     * �@@["���x�M�p���������~"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.���������ݒ���.�o�^�l(����) =
     * �p�����[�^.��������.���x�M�p���������~<BR>
     * <BR>
     * �@@["��ʐM�p���������~"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.���������ݒ���.�o�^�l(����) =
     * �p�����[�^.��������.��ʐM�p���������~<BR>
     * <BR>
     * �@@["������"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.���������ݒ���.�o�^�l(����) = �p�����[�^.��������.������<BR>
     * <BR>
     * �@@["��p�|��"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.���������ݒ���.�o�^�l(����) = �p�����[�^.����Params.��p�|��<BR>
     * <BR>
     * �@@["��p�]���P��"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.���������ݒ���.�o�^�l(����) = �p�����[�^.����Params.�]���P��<BR>
     * <BR>
     * �@@["�a����،��]���|��"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.���������ݒ���.�o�^�l(����) =
     * �p�����[�^.����Params.�a����،��]���|��<BR>
     * <BR>
     * �Q�j�P�j�ȊO�̏ꍇ�A�ȉ��̏������s���B<BR>
     * �@@�Q�|�P�j�p�����[�^.�������(����) != null�̏ꍇ�A�ȉ��̏��������{����B<BR>
     *     �@@�@@���p�����[�^.�������(����) == null�̏ꍇ�A�ȉ��̃v���p�e�B��null���Z�b�g����B<BR>
     *�@@�@@�@@�@@�@@�E�p�����[�^.���������ݒ���.�o�^�l(����)<BR>
     *�@@�@@�@@�@@�@@�E�p�����[�^.���������ݒ���.�ύX��o�^�l(����)<BR>
     * �@@�@@�Q�|�P�|�P�j���i�Ǘ�(����)�f�[�^�}�l�[�W��.get��������o�^�l()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@[get��������o�^�l()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@��������F�@@�p�����[�^.�������(����)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�����ڋ敪�F�@@�p�����[�^.���������ݒ���.�����ڋ敪<BR>
     * <BR>
     * �@@�@@�Q�|�P�|�Q�j�o�^�l(����)���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�@@�p�����[�^.���������ݒ���.�o�^�l(����) = �Q�|�P�|�P�j�̖߂�l<BR>
     * <BR>
     * �@@�Q�|�Q�j�p�����[�^.�������(����) != null�̏ꍇ�A�ȉ��̏��������{����B<BR>
     *    �@@�@@���p�����[�^.�������(����) == null�̏ꍇ�A�ȉ��̃v���p�e�B��null���Z�b�g����B<BR>
     *�@@�@@�@@�@@�E�p�����[�^.���������ݒ���.�o�^�l(����)<BR>
     *�@@�@@�@@�@@�E�p�����[�^.���������ݒ���.�ύX��o�^�l(����)<BR>
     * �@@�@@�Q�|�Q�|�P�j���i�Ǘ�(����)�f�[�^�}�l�[�W��.get��������o�^�l()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@[get��������o�^�l()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@��������F�@@�p�����[�^.�������(����)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�����ڋ敪�F�@@�p�����[�^.���������ݒ���.�����ڋ敪<BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�Q�j�o�^�l(����)���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�@@�p�����[�^.���������ݒ���.�o�^�l(����) = �Q�|�Q�|�P�j�̖߂�l<BR>
     * <BR>
     * �R�j�p�����[�^.�������������ݒ�Params != null�̏ꍇ�A<BR>
     * �@@�\��̃Z�b�g���s���B<BR>
     * <BR>
     * �@@�p�����[�^.���������ݒ���.�o�^�l(�\��) =<BR>
     * �@@�@@�p�����[�^.�������������ݒ�Params.�ݒ�f�[�^(�\��)<BR>
     * �@@�p�����[�^.���������ݒ���.�K�p����From =<BR>
     * �@@�@@�p�����[�^.�������������ݒ�Params.�K�p����From<BR>
     * �@@�p�����[�^.���������ݒ���.�K�p����To =<BR>
     * �@@�@@�p�����[�^.�������������ݒ�Params.�K�p����To<BR>
     * �@@�p�����[�^.���������ݒ���.�X�V�҃R�[�h = <BR>
     * �@@�@@�p�����[�^.�������������ݒ�Params.�X�V�҃R�[�h<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * setLatestData<BR>
     * <BR>
     * Set the latest data into the argument, l_productConditionInfo.<BR>
     * <BR>
     * 1) (parameter.productConditionInfo.smallItemDiv == <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@("Def.TRADE_STOP" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"Def.MARGIN_SYS_TRADE_STOP" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"Def.MARGIN_GEN_TRADE_STOP" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"Def.STANDARD_NAME" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"Def.MARGIN_RATIO" or<BR>
     *
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"Def.ESTIMATION_PRICE" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"Def.SECURITIES_ESTIMATION_RATIO"))<BR>
     * �@@execute the following process.<BR>
     * �@@1-1)Set the latest data according to the conditions of<BR>
     * parameter.l_productConditionInfo.smallItemDiv<BR>
     * <BR>
     * �@@If parameter.l_productConditionInfo.smallItemDiv is<BR>
     * �@@["Def.TRADE_STOP"]<BR>
     * �@@�@@parameter.l_productConditionInfo.bizDateRegistData =
     * parameter.l_equityProduct.trade_stop<BR>
     * <BR>
     * �@@["Def.MARGIN_SYS_TRADE_STOP"]<BR>
     * �@@�@@parameter.l_productConditionInfo.bizDateRegistData =
     * parameter.l_equityProduct.margin_sys_trade_stop<BR>
     * <BR>
     * �@@["Def.MARGIN_GEN_TRADE_STOP"]<BR>
     * �@@�@@parameter.l_productConditionInfo.bizDateRegistData =
     * parameter.l_equityProduct.margin_gen_trade_stop<BR>
     * <BR>
     * �@@["Def.STANDARD_NAME"]<BR>
     * �@@�@@parameter.l_productConditionInfo.bizDateRegistData =
     * parameter.l_equityProduct.standard_name<BR>
     * <BR>
     * �@@["Def.MARGIN_RATIO"]<BR>
     * �@@�@@parameter.l_productConditionInfo.bizDateRegistData =
     * parameter.l_productParams.margin_ratio<BR>
     * <BR>
     * �@@["Def.ESTIMATION_PRICE"]<BR>
     * �@@�@@parameter.l_productConditionInfo.bizDateRegistData =
     * parameter.l_productParams.estimation_price   <BR>
     * <BR>
     * �@@["Def.SECURITIES_ESTIMATION_RATIO"]<BR>
     * �@@�@@parameter.l_productConditionInfo.bizDateRegistData =
     * parameter.l_productParams.securities_estimation_ratio<BR>
     * <BR>
     * 2) Process the followings for other cases<BR>
     * �@@2-1)Execute the following process if parameter.l_tradedProductToday != null<BR>
     * �@@�@@2-1-1)Call WEB3AdminPMEquityDataManager.getTradedProductRegistrationValue()
     * method<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@[parameter set into getTradedProductRegistrationValue()]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@l_tradedProduct: parameter.tradedProductToday<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@l_strSmallItemDiv:
     * parameter.l_productConditionInfo.smallItemDiv<BR>
     * <BR>
     * �@@�@@2-1-2)Set bizDateRegistData<BR>
     * <BR>
     * �@@�@@�@@�@@parameter.l_productConditionInfo.bizDateRegistData = return value of
     * 2-1-1)<BR>
     * <BR>
     * �@@2-2)Execute the following process if parameter.l_tradedProductNextDay !=
     * null<BR>
     * �@@�@@2-2-1)Call WEB3AdminPMEquityDataManager.getTradedProductRegistrationValue()
     * method<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@[parameter set into getTradedProductRegistrationValue()]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@l_tradedProduct: parameter.tradedProductNextDay<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@l_strSmallItemDiv:
     * parameter.l_productConditionInfo.smallItemDiv<BR>
     * <BR>
     * �@@�@@2-2-2)Set nextBizDateRegistData<BR>
     * <BR>
     * �@@�@@�@@�@@parameter.l_productConditionInfo.nextBizDateRegistData = return value of
     * 2-2-1)<BR>
     * <BR>
     * 3)If parameter.eqtypeProductConditionParams != null<BR>
     * �@@Set schedule<BR>
     * <BR>
     * �@@parameter.l_productConditionInfo.scheduleRegistData =<BR>
     * �@@�@@parameter.l_eqtypeProductConditionParams.data_plan<BR>
     * �@@parameter.l_productConditionInfo.applyStartDate =<BR>
     * �@@�@@parameter.l_eqtypeProductConditionParams.term_from<BR>
     * �@@parameter.l_productConditionInfo.applyEndDate =<BR>
     * �@@�@@parameter.l_eqtypeProductConditionParams.term_to<BR>
     * <BR>
     * @@param l_productConditionInfo - �i���������ݒ���j<BR>
     * <BR>
     * l_productConditionInfo<BR>
     * <BR>
     * @@param l_productParams - �i����Params�j<BR>
     * <BR>
     * �����I�u�W�F�N�g<BR>
     * <BR>
     * l_product<BR>
     * <BR>
     * @@param l_equityProduct - �i���������j<BR>
     * <BR>
     * ���������I�u�W�F�N�g<BR>
     * <BR>
     * l_equityProduct<BR>
     * <BR>
     *
     * @@param l_tradedProductToday - �i�������(����)�j<BR>
     * <BR>
     * �������(����)<BR>
     * <BR>
     * l_tradedProductToday<BR>
     * <BR>
     * @@param l_tradedProductNextDay - �i�������(����)�j<BR>
     * <BR>
     * �������(����)<BR>
     * <BR>
     * l_tradedProductNextDay<BR>
     * <BR>
     * @@param l_eqtypeProductConditionParams - �i�������������ݒ�Params�j<BR>
     * <BR>
     * ������������Params<BR>
     * <BR>
     * l_eqtypeProductConditionParams<BR>
     * <BR>
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws NotFoundException NotFoundException
     * @@throws DataFindException DataFindException
     * @@throws DataQueryException DataQueryException
     * @@throws DataNetworkException DataNetworkException
     * @@roseuid 4187897102BD
     */
    protected void setLatestData(
        WEB3AdminPMProductCondConfigUnit l_productConditionInfo,
        ProductParams l_productParams,
        WEB3EquityProduct l_equityProduct,
        EqtypeTradedProductParams l_tradedProductToday,
        EqtypeTradedProductParams l_tradedProductNextDay,
        EqtypeProductConditionParams l_eqtypeProductConditionParams)
        throws WEB3BaseException, NotFoundException, DataFindException,
        DataQueryException, DataNetworkException
    {
        final String STR_METHOD_NAME =
            "setLatestData(WEB3AdminPMProductCondConfigUnit, Product, WEB3EquityProduct"
                + "EqtypeTradedProductParams, EqtypeTradedProductParams, "
                + "EqtypeProductConditionParams)";
        log.entering(STR_METHOD_NAME);

        final String L_DATE_FORMAT = "yyyyMMdd";
        String l_strSmallItemDiv = null;
        String l_strBizDateRegistData = null;
        WEB3AdminPMEquityDataManager l_adminPMEquityDataManager = null;
        EqtypeProductParams l_eqtypeProductParams = null;
        l_strSmallItemDiv = l_productConditionInfo.smallItemDiv;

        // Extra if condition to avoid NullPointerException
        if (l_equityProduct != null)
        {
            l_eqtypeProductParams = (EqtypeProductParams) l_equityProduct.getDataSourceObject();
            if ((WEB3AdminEquitySmallItemDivDef.TRADE_STOP).equals(l_strSmallItemDiv))
            {
                if (l_eqtypeProductParams.getTradeStopIsNull())
                {
	                l_productConditionInfo.bizDateRegistData = null;
                }
                else
                {
	                l_productConditionInfo.bizDateRegistData =
	                    String.valueOf(l_eqtypeProductParams.getTradeStop());
                }
             } else if (
                (WEB3AdminEquitySmallItemDivDef.MARGIN_SYS_TRADE_STOP).equals(l_strSmallItemDiv))
             {
                if (l_eqtypeProductParams.getMarginSysTradeStopIsNull())
                {
                    l_productConditionInfo.bizDateRegistData = null;
                }
                else
                {
	                l_productConditionInfo.bizDateRegistData =
	                    String.valueOf(l_eqtypeProductParams.getMarginSysTradeStop());
                }
             } else if (
                (WEB3AdminEquitySmallItemDivDef.MARGIN_GEN_TRADE_STOP).equals(l_strSmallItemDiv))
             {
                if (l_eqtypeProductParams.getMarginGenTradeStopIsNull())
                {
                    l_productConditionInfo.bizDateRegistData = null;
                }
                else
                {
                    l_productConditionInfo.bizDateRegistData =
                        String.valueOf(l_eqtypeProductParams.getMarginGenTradeStop());
                }
             } else if ((WEB3AdminEquitySmallItemDivDef.STANDARD_NAME).equals(l_strSmallItemDiv))
             {
                    l_productConditionInfo.bizDateRegistData =
                        l_eqtypeProductParams.getStandardName();
             } else if ((WEB3AdminEquitySmallItemDivDef.CAPITAL_GAIN_TAX_DEALING).equals(l_strSmallItemDiv))
             {
				if (l_eqtypeProductParams.getCapitalGainTaxDealingsRegIsNull())
				{
					l_productConditionInfo.bizDateRegistData = null;
				}
				else
				{
					l_productConditionInfo.bizDateRegistData =
						String.valueOf(l_eqtypeProductParams.getCapitalGainTaxDealingsReg());
				}

             }
             
        }
        if (l_productParams != null)
        {
			if ((WEB3AdminEquitySmallItemDivDef.PRIMARY_MARKET).equals(l_strSmallItemDiv))
			{
				if (l_productParams.getPrimaryMarketIdIsNull()
					 || l_productParams.getPrimaryMarketId() == 0 )
				{
					l_productConditionInfo.bizDateRegistData = null;
				}else
				{
					FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
					WEB3GentradeFinObjectManager l_finObjectManager =
						(WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
					WEB3GentradeMarket l_market = 
						(WEB3GentradeMarket)l_finObjectManager.getMarket(l_productParams.getPrimaryMarketId());
						
					l_productConditionInfo.bizDateRegistData = l_market.getMarketCode();
				}
				
			} else if ((WEB3AdminEquitySmallItemDivDef.MARGIN_RATIO).equals(l_strSmallItemDiv))
            {
                l_productConditionInfo.bizDateRegistData =
                    WEB3StringTypeUtility.formatNumber(l_productParams.getMarginRatio());
            } else if ((WEB3AdminEquitySmallItemDivDef.ESTIMATION_PRICE).equals(l_strSmallItemDiv))
            {
                l_productConditionInfo.bizDateRegistData =
                    WEB3StringTypeUtility.formatNumber(l_productParams.getEstimationPrice());
            } else if (
                (WEB3AdminEquitySmallItemDivDef.SECURITIES_ESTIMATION_RATIO).equals(
                    l_strSmallItemDiv))
            {
                l_productConditionInfo.bizDateRegistData =
                    WEB3StringTypeUtility.formatNumber(l_productParams.getSecuritiesEstimationRatio());
            }
        }

        if ((!WEB3AdminEquitySmallItemDivDef.TRADE_STOP.equals(l_strSmallItemDiv))
            && (!WEB3AdminEquitySmallItemDivDef.MARGIN_SYS_TRADE_STOP.equals(l_strSmallItemDiv))
            && (!WEB3AdminEquitySmallItemDivDef.MARGIN_GEN_TRADE_STOP.equals(l_strSmallItemDiv))
            && (!WEB3AdminEquitySmallItemDivDef.STANDARD_NAME.equals(l_strSmallItemDiv))
			&& (!WEB3AdminEquitySmallItemDivDef.PRIMARY_MARKET.equals(l_strSmallItemDiv))
			&& (!WEB3AdminEquitySmallItemDivDef.CAPITAL_GAIN_TAX_DEALING.equals(l_strSmallItemDiv))
            && (!WEB3AdminEquitySmallItemDivDef.MARGIN_RATIO.equals(l_strSmallItemDiv))
            && (!WEB3AdminEquitySmallItemDivDef.ESTIMATION_PRICE.equals(l_strSmallItemDiv))
            && (!WEB3AdminEquitySmallItemDivDef.SECURITIES_ESTIMATION_RATIO.equals(
                l_strSmallItemDiv)))
        {
            l_adminPMEquityDataManager = new WEB3AdminPMEquityDataManager();

            // 2-1)Execute the following process if parameter.l_tradedProductToday != null
            if (l_tradedProductToday != null)
            {
                // 2-1-1)Call WEB3AdminPMEquityDataManager.getTradedProductRegistrationValue()
                l_strBizDateRegistData =
                    l_adminPMEquityDataManager.getTradedProductRegistrationValue(
                        l_tradedProductToday,
                        l_strSmallItemDiv);
                // 2-1-2)Set bizDateRegistData
                l_productConditionInfo.bizDateRegistData = l_strBizDateRegistData;
            }
            else
            {
                l_productConditionInfo.bizDateRegistData = null;
                if (!l_productConditionInfo.smallItemDiv.equals(WEB3AdminEquitySmallItemDivDef.MINI_STOCK_MARKET))
                {
	                l_productConditionInfo.aftBizDateRegistData = null;
                }
            }

            // 2-2)Execute the following process if parameter.l_tradedProductNextDay != null
            if (l_tradedProductNextDay != null)
            {
                // 2-2-1)Call WEB3AdminPMEquityDataManager.getTradedProductRegistrationValue()
                l_strBizDateRegistData =
                    l_adminPMEquityDataManager.getTradedProductRegistrationValue(
                        l_tradedProductNextDay,
                        l_strSmallItemDiv);
                // 2-2-2)Set nextBizDateRegistData
                l_productConditionInfo.nextBizDateRegistData = l_strBizDateRegistData;
            }
            else
            {
                l_productConditionInfo.nextBizDateRegistData = null;
                if (!l_productConditionInfo.smallItemDiv.equals(WEB3AdminEquitySmallItemDivDef.MINI_STOCK_MARKET))
                {
	                l_productConditionInfo.aftNextBizDateRegistData = null;
                }
            }
        }

        // 3)If parameter.eqtypeProductConditionParams != null
        if (l_eqtypeProductConditionParams != null)
        {
            // Set schedule
            // �p�����[�^.���������ݒ���.�����ڋ敪 == "�D��s��"�̏ꍇ
            if (l_productConditionInfo.smallItemDiv.equals(WEB3AdminEquitySmallItemDivDef.PRIMARY_MARKET))
            {
            	if (l_eqtypeProductConditionParams.getDataPlan() != null)
            	{
	            	FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
	            	WEB3GentradeFinObjectManager l_finObjManger =
	            		(WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
	            	
	            	Market l_market = l_finObjManger.getMarket(Long.parseLong(l_eqtypeProductConditionParams.getDataPlan()));
					l_productConditionInfo.scheduleRegistData =
						l_market.getMarketCode();
            	}
            	else
            	{
            		l_productConditionInfo.scheduleRegistData = null;
            	}
            }
            else
            {
				l_productConditionInfo.scheduleRegistData =
					l_eqtypeProductConditionParams.getDataPlan();

            }
            l_productConditionInfo.lastUpdater =
				l_eqtypeProductConditionParams.getLastUpdater();
            if (l_eqtypeProductConditionParams.getTermFrom() == null)
            {
                l_productConditionInfo.applyStartDate = null;
            }
            else
            {
	            l_productConditionInfo.applyStartDate =
	                WEB3DateUtility.formatDate(l_eqtypeProductConditionParams.getTermFrom(), L_DATE_FORMAT);
            }
            if (l_eqtypeProductConditionParams.getTermTo() == null)
            {
                l_productConditionInfo.applyEndDate = null;
            }
            else
            {
	            l_productConditionInfo.applyEndDate =
	                WEB3DateUtility.formatDate(l_eqtypeProductConditionParams.getTermTo(), L_DATE_FORMAT);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �iset�����X�V���j<BR>
     * <BR>
     * �����̖��������ݒ�X�V���ɖ����X�V����ݒ肷��B<BR>
     * <BR>
     * �P�j�����Ώۗ񖼂��擾����B<BR>
     * �@@�@@this.get��()���R�[������B<BR>
     * <BR>
     * �@@�@@[get��()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�����ڋ敪�F�@@�p�����[�^.���������ݒ���.�����ڋ敪<BR>
     * <BR>
     * �Q�j�����f�[�^�X�V�`�F�b�N<BR>
     * �@@�p�����[�^.���������ݒ���.�o�^�l(����) !=<BR>
     * �@@�@@�p�����[�^.���������ݒ���.�ύX��o�^�l(����)�̏ꍇ�A<BR>
     * �@@�ȉ��̏��������{����B<BR>
     * <BR>
     * �@@�Q�|�P�j�X�V�Ώۂ������e�[�u���̏ꍇ<BR>
     * �@@�@@(�p�����[�^.���������ݒ���.�����ڋ敪 == <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@("��p�|��" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"��p�]���P��" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"�a����،��]���|��"))<BR>
     * �@@�@@�@@�ȉ��̏������s���B<BR>
     * �@@�@@�Q�|�P�|�P�j�p�����[�^.���������ݒ�X�V���.�����X�V��� == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�X�V���C���X�^���X�𐶐����A�ȉ��̃v���p�e�B�Z�b�g��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�s���A�p�����[�^.���������ݒ�X�V���.�����X�V���ɃZ�b�g����B<B
     * R>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�X�V���.ID = �p�����[�^.����.����ID<BR>
     * <BR>
     * �@@�@@�Q�|�P�|�Q�j�o�^�l���Z�b�g����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�p�����[�^.���������ݒ�X�V���.�����X�V���.�X�V��and�l.put()��
     * �\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@[put()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@key�F�@@�P�j�̖߂�l<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@value�F (*2)<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@(*2)[�p�����[�^.���������ݒ���.�����ڋ敪 == "�D��s��"�̏ꍇ] <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��()�̖߂�l.�s��ID���Z�b�g�B <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@���p�����[�^.���������ݒ���.�ύX��o�^�l(����) == null�̏ꍇ��null���Z�b�g�B <BR>
     *<BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@[get�s��()�ɃZ�b�g����p�����[�^] <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�،���ЃR�[�h�F�@@�p�����[�^.��������.�،���ЃR�[�h <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�s��R�[�h�F�@@�p�����[�^.���������ݒ���.�ύX��o�^�l(����) <BR>
     *�@@�@@�@@�@@�@@�@@�@@ [��L�ȊO�̏ꍇ] <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�p�����[�^.���������ݒ���.�ύX��o�^�l(����) <BR>
     * <BR>
     * �@@�@@�Q�|�P�|�R�j�X�V�҃R�[�h�A�X�V���t���Z�b�g����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�p�����[�^.���������ݒ�X�V���.�����X�V���.�X�V��and�l.put()��
     * �\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@[put()�ɃZ�b�g����p�����[�^(�X�V�҃R�[�h)]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@key�F�@@"�X�V�҃R�[�h"(last_updater)<BR><BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@value�F�@@�p�����[�^.�X�V�҃R�[�h<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@[put()�ɃZ�b�g����p�����[�^(�X�V���t)]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@key�F�@@"�X�V���t"(last_updated_timestamp)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@value�F�@@���ݎ���(*1)<BR>
     * <BR>
     * �@@�Q�|�Q�j�X�V�Ώۂ����������e�[�u���̏ꍇ�A<BR>
     * �@@�@@(�p�����[�^.���������ݒ���.�����ڋ敪 == <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@("��������������~" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"���x�M�p���������~" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"��ʐM�p���������~" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"������"))<BR>
     * �@@�@@�@@�ȉ��̏������s���B<BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�P�j�p�����[�^.���������ݒ�X�V���.���������X�V��� ==
     * null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�X�V���C���X�^���X�𐶐����A�ȉ��̃v���p�e�B�Z�b�g��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�s���A�p�����[�^.���������ݒ�X�V���.���������X�V���ɃZ�b�g����
     * �B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�X�V���.ID = �p�����[�^.��������.����ID<BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�Q�j�o�^�l���Z�b�g����B<BR>
     * �@@�@@�@@�@@�@@�p�����[�^.���������ݒ�X�V���.���������X�V���.�X�V��and�l.put()���\
     * �b�h���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@[put()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@key�F�@@�P�j�̖߂�l<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@value�F�@@�p�����[�^.���������ݒ���.�ύX��o�^�l(����)<BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�R�j�X�V�҃R�[�h�A�X�V���t���Z�b�g����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�p�����[�^.���������ݒ�X�V���.���������X�V���.�X�V��and�l.put
     * ()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@[put()�ɃZ�b�g����p�����[�^(�X�V�҃R�[�h)]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@key�F�@@"�X�V�҃R�[�h"(last_updater)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@value�F�@@�p�����[�^.�X�V�҃R�[�h<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@[put()�ɃZ�b�g����p�����[�^(�X�V���t)]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@key�F�@@"�X�V���t"(last_updated_timestamp)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@value�F�@@���ݎ���(*1)<BR>
     * <BR>
     * �@@�Q�|�R�j�Q�|�P�j�A�Q�|�Q�j�ȊO�̏ꍇ<BR>
     * �@@�@@�@@(�X�V�Ώۂ���������e�[�u���̏ꍇ)<BR>
     * �@@�@@�@@�ȉ��̏������s���B<BR>
     * �@@�@@�Q�|�R�|�P�j�p�����[�^.�������(����) != null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@���i�Ǘ�(����)�f�[�^�}�l�[�W��.set��������o�^�l()���\�b�h���R�[��
     * ����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@[set��������o�^�l()���\�b�h�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@��������X�V���F�@@�p�����[�^.���������ݒ�X�V���.��������X�V
     * ���<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@��������F�@@�p�����[�^.�������(����)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����ڋ敪�F�@@�p�����[�^.���������ݒ���.�����ڋ敪<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�s��R�[�h�F�@@�p�����[�^.���������ݒ���.�s��R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�X�V�񖼁F�@@�P�j�̖߂�l<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�ύX��o�^�l�F�@@�p�����[�^.���������ݒ���.�ύX��o�^�l(����)<B
     * R>
     * �@@�@@�@@�@@�@@�@@�@@�@@�X�V�҃R�[�h�F�@@�p�����[�^.�X�V�҃R�[�h<BR>
     * <BR>
     * �R�j�����f�[�^�X�V�`�F�b�N<BR>
     * �@@�p�����[�^.���������ݒ���.�o�^�l(����) !=<BR>
     * �@@�@@�p�����[�^.���������ݒ���.�ύX��o�^�l(����)�@@����<BR>
     * �@@�@@�p�����[�^.�������(����) != null�̏ꍇ�A<BR>
     * �@@�ȉ��̏��������{����B<BR>
     * <BR>
     * �@@�R�|�P�j���i�Ǘ�(����)�f�[�^�}�l�[�W��.set��������o�^�l()���\�b�h���R�[������
     * �B<BR>
     * <BR>
     * �@@�@@�@@[set��������o�^�l()���\�b�h�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@��������X�V���F�@@�p�����[�^.���������ݒ�X�V���.�������(����)�X�V��
     * ��<BR>
     * �@@�@@�@@�@@��������F�@@�p�����[�^.�������(����)<BR>
     * �@@�@@�@@�@@�����ڋ敪�F�@@�p�����[�^.���������ݒ���.�����ڋ敪<BR>
     * �@@�@@�@@�@@�s��R�[�h�F�@@�p�����[�^.���������ݒ���.�s��R�[�h<BR>
     * �@@�@@�@@�@@�X�V�񖼁F�@@�P�j�̖߂�l<BR>
     * �@@�@@�@@�@@�ύX��o�^�l�F�@@�p�����[�^.���������ݒ���.�ύX��o�^�l(����)<BR>
     * �@@�@@�@@�@@�X�V�҃R�[�h�F�@@�p�����[�^.�X�V�҃R�[�h<BR>
     * <BR>
     * (*1)���ݎ����E�E�E<BR>
     * �@@ThreadLocalSystemAttributesRegistry.getAttribute(������ԊǗ�.TIMESTAMP_TAG)<B
     * R>
     * �ɂĎ擾�������ݎ���<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * setProductUpdateInfo<BR>
     * <BR>
     * Set productUpdateInfo into the argument, l_productConditionUpdateInfo<BR>
     * <BR>
     * 1)Acquire row to be processed<BR>
     * �@@�@@Call this.getRow()<BR>
     * <BR>
     * �@@�@@[parameter set to getROW()]<BR>
     * �@@�@@�@@l_strSmallItemDiv: parameter.l_productConditionInfo.smallItemDiv<BR>
     * <BR>
     * 2)today data update check<BR>
     * �@@If parameter.l_productConditionInfo.bizDateRegistData !=<BR>
     * �@@�@@parameter.l_productConditionInfo.aftBizDateRegistData,<BR>
     * �@@Execute the following process<BR>
     * <BR>
     * �@@2-1)If the product table is updated,<BR>
     * �@@�@@(parameter.l_productConditionInfo.smallItemDiv == <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@("Def.MARGIN_RATIO" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"Def.ESTIMATION_PRICE" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"Def.SECURITIES_ESTIMATION_RATIO"))<BR>
     * �@@�@@�@@execute the following processes<BR>
     * �@@�@@2-1-1)If parameter.l_productConditionUpdateInfo.l_productUpdateInfo ==
     * null,<BR>
     * �@@�@@�@@�@@�@@�@@�@@Create UpdateInfo instance, set the following properties,<BR>
     * �@@�@@�@@�@@�@@�@@�@@and set them into
     * parameter.l_productCondSettingUpdateInfo.productUpdateInfo<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@UpdateInfo.id = parameter.l_product.product_id<BR>
     * <BR>
     * �@@�@@2-1-2)Set regist data<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@Call
     * parameter.l_productCondSettingUpdateInfo.l_productUpdateInfo.updateInfoRowAndVal
     * ue.put() method<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@[parameter set into put()]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@key: return value of 1)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@value:
     * parameter.l_productConditionInfo.aftBizDateRegistData<BR>
     * <BR>
     * �@@�@@2-1-3)Set lastUpdater�AlastUpdatedTimestamp<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@Call
     * parameter.l_productCondSettingUpdateInfo.productUpdateInfo.updateRowAndValue.put
     * () method<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@[parameter(administratorCode) set into put()]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@key: "lastUpdater"(last_updater)<BR><BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@value: parameter.lastUpdater<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@[parameter(lastUpdatedTimestamp) set into put()]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@key: "lastUpdatedTimestamp"(last_updated_timestamp)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@value: timeStamp(*1)<BR>
     * <BR>
     * �@@2-2)If eqtypeProduct table is updated,<BR>
     * �@@�@@(parameter.l_productConditionInfo.smallItemDiv == <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@("Def.TRADE_STOP" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"Def.MARGIN_SYS_TRADE_STOP" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"Def.MARGIN_GEN_TRADE_STOP" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@""))<BR>
     * �@@�@@�@@Execute the following process<BR>
     * <BR>
     * �@@�@@2-2-1)If parameter.l_productCondSettingUpdateInfo.l_productUpdateInfo ==
     * null<BR>
     * �@@�@@�@@�@@�@@�@@�@@Create UpdateInfo instance, set the following properties,<BR>
     * �@@�@@�@@�@@�@@�@@�@@and set them into
     * parameter.l_productCondSettingUpdateInfo.l_productUpdateInfo<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@UpdateInfo.id = parameter.equityProduct.productI<BR>
     * <BR>
     * �@@�@@2-2-2)Set regist data<BR>
     * �@@�@@�@@�@@�@@Call
     * parameter.l_productCondSettingUpdateInfo.productUpdateInfo.updateRowAndValue.put
     * () method<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@[parameter set into put()]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@key: return value of 1)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@value:
     * parameter.l_productConditionInfo.aftBizDateRegistData<BR>
     * <BR>
     * �@@�@@2-2-3)Set lastUpdater�AlastUpdatedTimestamp<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@Call
     * parameter.l_productCondSettingUpdateInfo.productUpdateInfo.updateRowAndValue.put
     * () method<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@[parameter(lastUpdater) set into put()]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@key: "lastUpdater"(last_updater)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@value: parameterlastUpdater<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@[parameter(lastUpdatedTimestamp) set into put()]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@key: "lastUpdatedTimestamp"(last_updated_timestamp)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@value: timeStamp(*1)<BR>
     * <BR>
     * �@@For other case exception 2-3)2-1) and 2-2)<BR>
     * �@@�@@�@@(If eqtypeTradedProduct table is updated)<BR>
     * �@@Execute the following process<BR>
     * �@@�@@2-3-1)If parameter.tradedProductToday != null<BR>
     * �@@�@@�@@�@@�@@�@@�@@Call
     * WEB3AdminPMEquityDataManager.setTradedProductRegistrationValue() method<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@[parameter set into setTradedProductRegistrationValue()
     * method]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@l_tradeProductUpdateInfo:
     * parameter.l_productCondSettingUpdateInfo.l_productUpdateInfo<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@l_tradedProduct: parameter.tradedProductToday<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@l_strSmallItemDiv:
     * parameter.l_productConditionInfo.smallItemDiv<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@l_strMarketCode: parameter.l_productConditionInfo.marketCode<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@l_updateRow: return value of 1)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@l_aftBizDateRegistData:
     * parameter.l_productConditionInfo.aftBizDateRegistData<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@l_lastUpdater: parameter.lastUpdater<BR>
     * <BR>
     * 3)Next day date update check<BR>
     * �@@If parameter.l_productConditionInfo.nextBizDateRegistData !=<BR>
     * �@@�@@parameter.l_productConditionInfo.aftNextBizDateRegistData�@@and<BR>
     * �@@�@@parameter.l_tradedProductNextDay != null,<BR>
     * �@@Execute the following process<BR>
     * <BR>
     * �@@3-1)Call WEB3AdminPMEquityDataManager.setTradedProductRegistrationValue()
     * method<BR>
     * <BR>
     * �@@�@@�@@[parameter set into setTradedProductRegistrationValue() method]<BR>
     * �@@�@@�@@�@@l_tradeProductUpdateInfo:
     * parameter.ProductCondSettingUpdateInfo.tradeProductNextUpdateInfo<BR>
     * �@@�@@�@@�@@l_tradedProduct: parameter.tradedProductNextDay<BR>
     * �@@�@@�@@�@@l_strSmallItemDiv: parameter.l_productConditionInfo.smallItemDiv<BR>
     * �@@�@@�@@�@@l_strMarketCode: parameter.l_productConditionInfo.marketCode<BR>
     * �@@�@@�@@�@@l_updateRow: return value of 1)<BR>
     * �@@�@@�@@�@@l_aftBizDateRegistData:
     * parameter.l_productConditionInfo.aftNextBizDateRegistData<BR>
     * �@@�@@�@@�@@l_astUpdater: parameter.lastUpdater<BR>
     * <BR>
     * (*1)timeStamp�E�E�E<BR>
     * �@@timeStamp acquired at
     * ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManageme
     * nt.TIMESTAMP_TAG)<BR>
     * <BR>
     * @@param l_productCondSettingUpdateInfo - ���������ݒ�X�V���<BR>
     * <BR>
     * ���������ݒ�X�V���I�u�W�F�N�g<BR>
     * <BR>
     * l_productCondSettingUpdateInfo object<BR>
     * <BR>
     * @@param l_productConditionInfo - �i���������ݒ���j<BR>
     * <BR>
     * l_productConditionInfo<BR>
     * <BR>
     * @@param l_equityProduct - ��������<BR>
     * <BR>
     * ���������I�u�W�F�N�g<BR>
     * <BR>
     * l_equityProduct object<BR>
     * <BR>
     *
     * @@param l_tradedProductToday - �i�������(����)�j<BR>
     * <BR>
     * l_tradedProductToday<BR>
     * <BR>
     * @@param l_tradedProductNextDay - �i�������(����)�j<BR>
     * <BR>
     * l_tradedProductNextDay<BR>
     * <BR>
     * @@param l_lastUpdater - �i�X�V�҃R�[�h�j<BR>
     * <BR>
     * l_lastUpdater<BR>
     * <BR>
     * @@roseuid 418A049B02BB
     */
    protected void setProductUpdateInfo(
        WEB3AdminPMProductCondSettingUpdateInfo l_productCondSettingUpdateInfo,
        WEB3AdminPMProductCondConfigUnit l_productConditionInfo,
        WEB3EquityProduct l_equityProduct,
        EqtypeTradedProductParams l_tradedProductToday,
        EqtypeTradedProductParams l_tradedProductNextDay,
        String l_lastUpdater) throws NotFoundException
    {
        final String STR_METHOD_NAME =
            "setProductUpdateInfo(ProductCondSettingUpdateInfo, WEB3AdminPMProductCondConfigUnit, "
                + "WEB3EquityProduct, EqtypeTradedProductParams, "
                + "EqtypeTradedProductParams, String)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminPMEquityDataManager l_equityDataManager = null;
        String l_strColumn = null;
        String l_strSmallItemDiv = null;
        String l_strBizDateRegistData = null;
        String l_strAftBizDateRegistData = null;
        String l_strMarketCode = null;
        String l_strNextBizDateRegistData = null;
        String l_strAftNextBizDateRegistData = null;
        Timestamp l_tsLastUpdateTime = null;
        WEB3AdminPMUpdateInfo l_updateInfo = null;
        l_strSmallItemDiv = l_productConditionInfo.smallItemDiv;

        // 1)Acquire row to be processed
        l_strColumn = this.getColumnName(l_strSmallItemDiv);
        l_strBizDateRegistData = l_productConditionInfo.bizDateRegistData;
        l_strAftBizDateRegistData = l_productConditionInfo.aftBizDateRegistData;
        l_tsLastUpdateTime =
            (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        l_equityDataManager = new WEB3AdminPMEquityDataManager();

        /*
         * 2)today data update check<BR>
         * �@@If parameter.l_productConditionInfo.bizDateRegistData !=
         * �@@�@@parameter.l_productConditionInfo.aftBizDateRegistData,
         */
        if (isChange(l_strBizDateRegistData, l_strAftBizDateRegistData))
        {
            /*
             * �@@2-1)If the product table is updated,<BR>
             * �@@�@@(parameter.l_productConditionInfo.smallItemDiv ==
             * �@@�@@("Def.PRIMARY_MARKET" or
             *     "Def.MARGIN_RATIO" or
             * �@@�@@"Def.ESTIMATION_PRICE" or
             * �@@�@@"Def.SECURITIES_ESTIMATION_RATIO"))
             */
            if ((WEB3AdminEquitySmallItemDivDef.PRIMARY_MARKET).equals(l_strSmallItemDiv)
            	||(WEB3AdminEquitySmallItemDivDef.MARGIN_RATIO).equals(l_strSmallItemDiv)
                || (WEB3AdminEquitySmallItemDivDef.ESTIMATION_PRICE).equals(l_strSmallItemDiv)
                || (WEB3AdminEquitySmallItemDivDef.SECURITIES_ESTIMATION_RATIO).equals(
                    l_strSmallItemDiv))
            {
                // 2-1-1)If parameter.l_productCondSettingUpdateInfo.l_productUpdateInfo == null
                if (l_productCondSettingUpdateInfo.productUpdateInfo == null)
                {
                    // Create UpdateInfo instance, set the following properties
                    l_updateInfo = new WEB3AdminPMUpdateInfo();
                    l_updateInfo.id = l_equityProduct.getProductId();

                    l_productCondSettingUpdateInfo.productUpdateInfo = l_updateInfo;
                }
                // 2-1-2)Set regist data
				//�p�����[�^.���������ݒ���.�����ڋ敪 == "�D��s��"�̏ꍇ
				if ((WEB3AdminEquitySmallItemDivDef.PRIMARY_MARKET).equals(l_strSmallItemDiv))
				{
					//�p�����[�^.���������ݒ���.�ύX��o�^�l(����) == null�̏ꍇ
					if (l_productConditionInfo.aftBizDateRegistData == null)
					{
						l_productCondSettingUpdateInfo.productUpdateInfo.updateRowAndValue.put(
							l_strColumn,
							null);
					//�p�����[�^.���������ݒ���.�ύX��o�^�l(����) != null�̏ꍇ
					}else
					{
						FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
						WEB3GentradeFinObjectManager l_gentradeFinObjManager = 
							(WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
						Market l_market = l_gentradeFinObjManager.getMarket(
							l_equityProduct.getInstitution().getInstitutionCode(), 
							l_productConditionInfo.aftBizDateRegistData
							);
	
						l_productCondSettingUpdateInfo.productUpdateInfo.updateRowAndValue.put(
						l_strColumn,
						String.valueOf(l_market.getMarketId()));
					}
				//��L�ȊO�̏ꍇ					
				}else{
	                l_productCondSettingUpdateInfo.productUpdateInfo.updateRowAndValue.put(
	                    l_strColumn,
	                    l_productConditionInfo.aftBizDateRegistData);
				}
                // 2-1-3)Set lastUpdater�AlastUpdatedTimestamp
                l_productCondSettingUpdateInfo.productUpdateInfo.
                    updateRowAndValue.put(WEB3AdminEquityLastUpdaterDef.LAST_UPDATER,
                        l_lastUpdater);
                l_productCondSettingUpdateInfo.productUpdateInfo.
                    updateRowAndValue.put(WEB3AdminEquityLastUpdaterDef.LAST_UPDATED_TIMESTAMP,
                        l_tsLastUpdateTime);

                /*
                 * 2-2)If eqtypeProduct table is updated,<BR>
                 * (parameter.l_productConditionInfo.smallItemDiv ==
                 * ("Def.TRADE_STOP" or
                 * "Def.MARGIN_SYS_TRADE_STOP" or
                 * "Def.MARGIN_GEN_TRADE_STOP" or
                 * "Def.STANDARD_NAME"
                 * "Def.CAPITAL_GAIN_TAX_DEALING"))
                 */
            } else if (
                WEB3AdminEquitySmallItemDivDef.TRADE_STOP.equals(l_strSmallItemDiv)
                    || WEB3AdminEquitySmallItemDivDef.MARGIN_SYS_TRADE_STOP.equals(
                        l_strSmallItemDiv)
                    || WEB3AdminEquitySmallItemDivDef.MARGIN_GEN_TRADE_STOP.equals(
                        l_strSmallItemDiv)
                    || WEB3AdminEquitySmallItemDivDef.STANDARD_NAME.equals(
                        l_strSmallItemDiv)
					|| WEB3AdminEquitySmallItemDivDef.CAPITAL_GAIN_TAX_DEALING.equals(
						l_strSmallItemDiv))            {
                // Changed  productUpdateInfo to equityProductUpdateInfo
                // 2-2-1)If parameter.l_productCondSettingUpdateInfo.l_productUpdateInfo == null
                if (l_productCondSettingUpdateInfo.equityProductUpdateInfo == null)
                {
                    // Create UpdateInfo instance, set the following properties
                    l_updateInfo = new WEB3AdminPMUpdateInfo();
                    l_updateInfo.id = l_equityProduct.getProductId();
                    l_productCondSettingUpdateInfo.equityProductUpdateInfo = l_updateInfo;
                }

                // 2-2-2)Set regist data
                l_productCondSettingUpdateInfo.equityProductUpdateInfo.updateRowAndValue.put(
                    l_strColumn,
                    l_productConditionInfo.aftBizDateRegistData);

                // 2-2-3)Set lastUpdater�AlastUpdatedTimestamp
                l_productCondSettingUpdateInfo.equityProductUpdateInfo.
                    updateRowAndValue.put(WEB3AdminEquityLastUpdaterDef.LAST_UPDATER,
                        l_lastUpdater);
                l_productCondSettingUpdateInfo.equityProductUpdateInfo.
                    updateRowAndValue.put(WEB3AdminEquityLastUpdaterDef.LAST_UPDATED_TIMESTAMP,
                        l_tsLastUpdateTime);

                /*
                 * For other case exception 2-3)2-1) and 2-2)
                 * (If eqtypeTradedProduct table is updated)
                 */
            } else
            {
                // 2-3-1)If parameter.tradedProductToday != null
                if (l_tradedProductToday != null)
                {
                    l_strMarketCode = l_productConditionInfo.marketCode;
                    l_strAftBizDateRegistData = l_productConditionInfo.aftBizDateRegistData;
                    l_equityDataManager.setTradedProductRegistrationValue(
                        l_productCondSettingUpdateInfo.tradeProductUpdateInfo,
                        l_tradedProductToday,
                        l_strSmallItemDiv,
                        l_strMarketCode,
                        l_strColumn,
                        l_strAftBizDateRegistData,
                        l_lastUpdater);
                }
            }
        }

        /*
         * 3)Next day date update check
         * �@@If parameter.l_productConditionInfo.nextBizDateRegistData !=
         * �@@�@@parameter.l_productConditionInfo.aftNextBizDateRegistData�@@and
         * �@@�@@parameter.l_tradedProductNextDay != null,
         */
        l_strNextBizDateRegistData = l_productConditionInfo.nextBizDateRegistData;
        l_strAftNextBizDateRegistData = l_productConditionInfo.aftNextBizDateRegistData;
        if(l_tradedProductNextDay != null
            && isChange(l_strNextBizDateRegistData, l_strAftNextBizDateRegistData))
        {
            // 3-1)Call WEB3AdminPMEquityDataManager.setTradedProductRegistrationValue()
            l_strMarketCode = l_productConditionInfo.marketCode;
            l_equityDataManager.setTradedProductRegistrationValue(
                l_productCondSettingUpdateInfo.tradeProductNextUpdateInfo,
                l_tradedProductNextDay,
                l_strSmallItemDiv,
                l_strMarketCode,
                l_strColumn,
                l_strAftNextBizDateRegistData,
                l_lastUpdater);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �iget�񖼁j<BR>
     * <BR>
     * �����̏����ڋ敪�ɊY������񕨗�����ԋp����B<BR>
     * <BR>
     * �P�j�p�����[�^.�����ڋ敪���A<BR>
     * �@@["������������~"�̏ꍇ]<BR>
     * �@@�@@�� = ���������e�[�u��.������������~<BR>
     * <BR>
     * �@@["���x�M�p���������~"�̏ꍇ]<BR>
     * �@@�@@�� = ���������e�[�u��.���x�M�p���������~<BR>
     * <BR>
     * �@@["��ʐM�p���������~"�̏ꍇ]<BR>
     * �@@�@@�� = ���������e�[�u��.��ʐM�p���������~<BR>
     * <BR>
     * �@@["��������~"�̏ꍇ]<BR>
     * �@@�@@�� = ������������e�[�u��.��������~<BR>
     * <BR>
     * �@@["��������~"�̏ꍇ]<BR>
     * �@@�@@�� = ������������e�[�u��.��������~<BR>
     * <BR>
     * �@@["�����x�M�p��~"�̏ꍇ]<BR>
     * �@@�@@�� = ������������e�[�u��.�����x�M�p��~<BR>
     * <BR>
     * �@@["�����x�M�p��~"�̏ꍇ]<BR>
     * �@@�@@�� = ������������e�[�u��.�����x�M�p��~<BR>
     * <BR>
     * �@@["�����ԍϐ��x�M�p��~"�̏ꍇ]<BR>
     * �@@�@@�� = ������������e�[�u��.�����ԍ�(���ԍ�)���x�M�p��~<BR>
     * <BR>
     * �@@["�����ԍϐ��x�M�p��~"�̏ꍇ]<BR>
     * �@@�@@�� = ������������e�[�u��.�����ԍ�(���ԍ�)���x�M�p��~<BR>
     * <BR>
     * �@@["�������x�M�p��~"�̏ꍇ]<BR>
     * �@@�@@�� = ������������e�[�u��.�������x�M�p��~<BR>
     * <BR>
     * �@@["���n���x�M�p��~"�̏ꍇ]<BR>
     * �@@�@@�� = ������������e�[�u��.���n���x�M�p��~<BR>
     * <BR>
     * �@@["����ʐM�p��~"�̏ꍇ]<BR>
     * �@@�@@�� = ������������e�[�u��.����ʐM�p��~<BR>
     * <BR>
     * �@@["����ʐM�p��~"�̏ꍇ]<BR>
     * �@@�@@�� = ������������e�[�u��.����ʐM�p��~<BR>
     * <BR>
     * �@@["�����ԍψ�ʐM�p��~"�̏ꍇ]<BR>
     * �@@�@@�� = ������������e�[�u��.�����ԍ�(���ԍ�)��ʐM�p��~<BR>
     * <BR>
     * �@@["�����ԍψ�ʐM�p��~"�̏ꍇ]<BR>
     * �@@�@@�� = ������������e�[�u��.�����ԍ�(���ԍ�)��ʐM�p��~<BR>
     * <BR>
     * �@@["������ʐM�p��~"�̏ꍇ]<BR>
     * �@@�@@�� = ������������e�[�u��.������ʐM�p��~<BR>
     * <BR>
     * �@@["���n��ʐM�p��~"�̏ꍇ]<BR>
     * �@@�@@�� = ������������e�[�u��.���n��ʐM�p��~<BR>
     * <BR>
     * �@@["��������~(���s)"�̏ꍇ]<BR>
     * �@@�@@�� = ������������e�[�u��.���������s�w���~<BR>
     * <BR>
     * �@@["��������~(���s)"�̏ꍇ]<BR>
     * �@@�@@�� = ������������e�[�u��.���������s�w���~<BR>
     * <BR>
     * �@@["�����x�M�p��~(���s)"�̏ꍇ]<BR>
     * �@@�@@�� = ������������e�[�u��.�����x�M�p���s�w���~<BR>
     * <BR>
     * �@@["�����x�M�p��~(���s)"�̏ꍇ]<BR>
     * �@@�@@�� = ������������e�[�u��.�����x�M�p���s�w���~<BR>
     * <BR>
     * �@@["�����ԍϐ��x�M�p��~(���s)"�̏ꍇ]<BR>
     * �@@�@@�� = ������������e�[�u��.�����ԍ�(���ԍ�)���x�M�p���s�w���~<BR>
     * <BR>
     * �@@["�����ԍϐ��x�M�p��~(���s)"�̏ꍇ]<BR>
     * �@@�@@�� = ������������e�[�u��.�����ԍ�(���ԍ�)���x�M�p���s�w���~<BR>
     * <BR>
     * �@@["����ʐM�p��~(���s)"�̏ꍇ]<BR>
     * �@@�@@�� = ������������e�[�u��.����ʐM�p���s�w���~<BR>
     * <BR>
     * �@@["����ʐM�p��~(���s)"�̏ꍇ]<BR>
     * �@@�@@�� = ������������e�[�u��.����ʐM�p���s�w���~<BR>
     * <BR>
     * �@@["�����ԍψ�ʐM�p��~(���s)"�̏ꍇ]<BR>
     * �@@�@@�� = ������������e�[�u��.�����ԍ�(���ԍ�)��ʐM�p���s�w���~<BR>
     * <BR>
     * �@@["�����ԍψ�ʐM�p��~(���s)"�̏ꍇ]<BR>
     * �@@�@@�� = ������������e�[�u��.�����ԍ�(���ԍ�)��ʐM�p���s�w���~<BR>
     * <BR>
     * �@@["���~�j����~"�̏ꍇ]<BR>
     * �@@�@@�� = ������������e�[�u��.���~�j����~<BR>
     * <BR>
     * �@@["���~�j����~"�̏ꍇ]<BR>
     * �@@�@@�� = ������������e�[�u��.���~�j����~<BR>
     * <BR>
     * �@@["������"�̏ꍇ]<BR>
     * �@@�@@�� = ���������e�[�u��.������<BR>
     * <BR>
     *	 ["�D��s��"�̏ꍇ]<BR> 
     *�@@�@@ �� = �����e�[�u��.�D��s��ID<BR> 
     * <BR>
     *�@@ ["��������戵"�̏ꍇ]<BR> 
     *�@@�@@ �� = ���������e�[�u��.��������戵�K��<BR> 
     * <BR>
     * �@@["�����P��"�̏ꍇ]<BR>
     * �@@�@@�� = ������������e�[�u��.�����P��<BR>
     * <BR>
     * �@@["�������"�̏ꍇ]<BR>
     * �@@�@@�� = ������������e�[�u��.�X�����J�敪<BR>
     * <BR>
     * �@@["���敪"�̏ꍇ]<BR>
     * �@@�@@�� = ������������e�[�u��.���敪<BR>
     * <BR>
     * �@@["���������K��"�̏ꍇ]<BR>
     * �@@�@@�� = ������������e�[�u��.�������������t���O<BR>
     * <BR>
     * �@@["�����~�j�����s��"�̏ꍇ]<BR>
     * �@@�@@�� = ������������e�[�u��.�~�j���戵<BR>
     * <BR>
     * �@@["���x�M�p�����敪"�̏ꍇ]<BR>
     * �@@�@@�� = ������������e�[�u��.���x�M�p�����敪<BR>
     * <BR>
     * �@@["��ʐM�p�����敪"�̏ꍇ]<BR>
     * �@@�@@�� = ������������e�[�u��.��ʐM�p�����敪<BR>
     * <BR>
     * �@@["���ۏ؋���"�̏ꍇ]<BR>
     * �@@�@@�� = ������������e�[�u��.���ۏ؋���<BR>
     * <BR>
     * �@@["���ۏ؋���"�̏ꍇ]<BR>
     * �@@�@@�� = ������������e�[�u��.���ۏ؋���<BR>
     * <BR>
     * �@@["�������ۏ؋���"�̏ꍇ]<BR>
     * �@@�@@�� = ������������e�[�u��.�������ۏ؋���<BR>
     * <BR>
     * �@@["�������ۏ؋���"�̏ꍇ]<BR>
     * �@@�@@�� = ������������e�[�u��.�������ۏ؋���<BR>
     * <BR>
     * �@@["��p�|��"�̏ꍇ]<BR>
     * �@@�@@�� = �����e�[�u��.��p�|��<BR>
     * <BR>
     * �@@["��p�]���P��"�̏ꍇ]<BR>
     * �@@�@@�� = �����e�[�u��.�]���P��<BR>
     * <BR>
     * �@@["�a����،��]���|��"�̏ꍇ]<BR>
     * �@@�@@�� = �����e�[�u��.�a����،��]���|��<BR>
     * <BR>
     * �@@["��l(�I�l)"�̏ꍇ]<BR>
     * �@@�@@�� = ������������e�[�u��.��l(�I�l)<BR>
     *  <BR>
     * �@@["��l"�̏ꍇ]<BR>
     * �@@�@@�� = ������������e�[�u��.��l<BR>
     * <BR>
     * �@@["�l���`�F�b�N"�̏ꍇ]<BR>
     * �@@�@@�� = ������������e�[�u��.�l���`�F�b�N�敪<BR>
     * <BR>
     * �@@["�����l��(�l���敪)"�̏ꍇ]<BR>
     * �@@�@@�� = ������������e�[�u��.�l���敪<BR>
     * <BR>
     * �@@["�����l��(����)"�̏ꍇ]<BR>
     * �@@�@@�� = ������������e�[�u��.�����l��(�����l)<BR>
     * <BR>
     * �@@["�����l��(���)"�̏ꍇ]<BR>
     * �@@�@@�� = ������������e�[�u��.�����l��(����l)<BR>
     * <BR>
     * �@@["�������x�P��"�̏ꍇ]<BR>
     * �@@�@@�� = ������������e�[�u��.�������x�P��<BR>
     * <BR>
     * �Q�j�擾�����񕨗�����ԋp����B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * getRow<BR>
     * <BR>
     * Return a field name corresponding to the argument, l_strSmallItemDiv<BR>
     * <BR>
     * �P�jIf parameter.smallItemDiv is<BR>
     * �@@["Def.TRADE_STOP"]<BR>
     * �@@�@@row = eqtype_product table.trade_stop<BR>
     * <BR>
     * �@@["Def.MARGIN_SYS_TRADE_STOP"]<BR>
     * �@@�@@row = eqtype_product table.margin_sys_trade_stop<BR>
     * <BR>
     * �@@["Def.MARGIN_GEN_TRADE_STOP"]<BR>
     * �@@�@@row = eqtype_product table.margin_gen_trade_stop<BR>
     * <BR>
     * �@@["Def.BUY_CASH_STOP"]<BR>
     * �@@�@@row = eqtype_traded_product table.buy_cash_stop<BR>
     * <BR>
     * �@@["Def.SELL_CASH_STOP"]<BR>
     * �@@�@@row = eqtype_traded_product table.sell_cash_stop<BR>
     * <BR>
     * �@@["Def.LONG_MARGIN_SYS_STOP"]<BR>
     * �@@�@@row = eqtype_traded_product table.long_margin_sys_stop<BR>
     * <BR>
     * �@@["Def.SHORT_MARGIN_SYS_STOP"]<BR>
     * �@@�@@row = eqtype_traded_product table.short_margin_sys_stop<BR>
     * <BR>
     * �@@["Def.LONG_CLOSE_MARGIN_SYS_STOP"]<BR>
     * �@@�@@row = eqtype_traded_product table.long_cms_market_ord_des_stop<BR>
     * <BR>
     * �@@["Def.SHORT_CLOSE_MARGIN_SYS_STOP"]<BR>
     * �@@�@@row = eqtype_traded_product table.short_close_margin_sys_stop<BR>
     * <BR>
     * �@@["Def.LONG_SWAP_MARGIN_SYS_STOP"]<BR>
     * �@@�@@row = eqtype_traded_product table.long_swap_margin_sys_stop<BR>
     * <BR>
     * �@@["Def.SHORT_SWAP_MARGIN_SYS_STOP"]<BR>
     * �@@�@@row = eqtype_traded_product table.short_swap_margin_sys_stop<BR>
     * <BR>
     * �@@["Def.LONG_MARGIN_GEN_STOP"]<BR>
     * �@@�@@row = eqtype_traded_product table.long_margin_gen_stop<BR>
     * <BR>
     * �@@["Def.SHORT_MARGIN_GEN_STOP"]<BR>
     * �@@�@@row = eqtype_traded_product table.short_margin_gen_stop<BR>
     * <BR>
     * �@@["Def.LONG_CLOSE_MARGIN_GEN_STOP"]<BR>
     * �@@�@@row = eqtype_traded_product table.long_close_margin_gen_stop<BR>
     * <BR>
     * �@@["Def.SHORT_CLOSE_MARGIN_GEN_STOP"]<BR>
     * �@@�@@row = eqtype_traded_product table.short_close_margin_gen_stop<BR>
     * <BR>
     * �@@["Def.LONG_SWAP_MARGIN_GEN_STOP"]<BR>
     * �@@�@@row = eqtype_traded_product table.long_swap_margin_gen_stop<BR>
     * <BR>
     * �@@["Def.SHORT_SWAP_MARGIN_GEN_STOP"]<BR>
     * �@@�@@row = eqtype_traded_product table.short_swap_margin_gen_stop<BR>
     * <BR>
     * �@@["Def.BUY_SPOT_MARKET_ORD_DES_STOP"]<BR>
     * �@@�@@row = eqtype_traded_product table.buy_spot_market_ord_des_stop<BR>
     * <BR>
     * �@@["Def.SELL_SPOT_MARKET_ORD_DES_STOP"]<BR>
     * �@@�@@row = eqtype_traded_product table.sell_spot_market_ord_des_stop<BR>
     * <BR>
     * �@@["Def.LONG_MS_MARKET_ORD_DES_STOP"]<BR>
     * �@@�@@row = eqtype_traded_product table.long_ms_market_ord_des_stop<BR>
     * <BR>
     * �@@["Def.SHORT_MS_MARKET_ORD_DES_STOP"]<BR>
     * �@@�@@row = eqtype_traded_product table.short_ms_market_ord_des_stop<BR>
     * <BR>
     * �@@["Def.LONG_CMS_MARKET_ORD_DES_STOP"]<BR>
     * �@@�@@row = eqtype_traded_product table.long_cms_market_ord_des_stop<BR>
     * <BR>
     * �@@["Def.SHORT_CMS_MARKET_ORD_DES_STOP"]<BR>
     * �@@�@@row = eqtype_traded_product table.short_cms_market_ord_des_stop<BR>
     * <BR>
     * �@@["Def.LONG_MG_MARKET_ORD_DES_STOP"]<BR>
     * �@@�@@row = eqtype_traded_product table.long_mg_market_ord_des_stop<BR>
     * <BR>
     * �@@["Def.SHORT_MG_MARKET_ORD_DES_STOP"]<BR>
     * �@@�@@row = eqtype_traded_product table.short_mg_market_ord_des_stop<BR>
     * <BR>
     * �@@["Def.LONG_CMG_MARKET_ORD_DES_STOP"]<BR>
     * �@@�@@row = eqtype_traded_product table.long_cmg_market_ord_des_stop<BR>
     * <BR>
     * �@@["Def.SHORT_CMG_MARKET_ORD_DES_STOP"]<BR>
     * �@@�@@row = eqtype_traded_product table.short_cmg_market_ord_des_stop<BR>
     * <BR>
     * �@@["Def.BUY_MINI_STOCK_STOP"]<BR>
     * �@@�@@row = eqtype_traded_product table.buy_mini_stock_stop<BR>
     * <BR>
     * �@@["Def.SELL_MINI_STOCK_STOP"]<BR>
     * �@@�@@row = eqtype_traded_product table.sell_mini_stock_stop<BR>
     * <BR>
     * �@@["Def.STANDARD_NAME"]<BR>
     * �@@�@@row = eqtype_traded_product table.standard_name<BR>
     * <BR>
     * �@@["Def.LOT_SIZE"]<BR>
     * �@@�@@row = eqtype_traded_product table.lot_size<BR>
     * <BR>
     * �@@["Def.OPEN_OTC_DIV"]<BR>
     * �@@�@@row = eqtype_traded_product table.open_otc_div<BR>
     * <BR>
     * �@@["Def.LIST_TYPE"]<BR>
     * �@@�@@row = eqtype_traded_product table.list_type<BR>
     * <BR>
     * �@@["Def.TODAY_DEP_FUND_REG"]<BR>
     * �@@�@@row = eqtype_traded_product table.today_dep_fund_reg<BR>
     * <BR>
     * �@@["Def.MINI_STOCK_MARKET"]<BR>
     * �@@�@@row = eqtype_traded_product table.mini_stock_can_dealt<BR>
     * <BR>
     * �@@["Def.MARGIN_SYS_PRODUCT_TYPE"]<BR>
     * �@@�@@row = eqtype_traded_product table.margin_sys_product_type<BR>
     * <BR>
     * �@@["Def.MARGIN_GEN_PRODUCT_TYPE"]<BR>
     * �@@�@@row = eqtype_traded_product tablemargin_gen_product_type<BR>
     * <BR>
     * �@@["Def.LONG_MARGIN_DEPOSIT_RATE"]<BR>
     * �@@�@@row = eqtype_traded_product table.long_margin_deposit_rate<BR>
     * <BR>
     * �@@["Def.SHORT_MARGIN_DEPOSIT_RATE"]<BR>
     * �@@�@@row = eqtype_traded_product table.short_margin_deposit_rate<BR>
     * <BR>
     * �@@["Def.LONG_CASH_MARGIN_DEPOSIT_RATE"]<BR>
     * �@@�@@row = eqtype_traded_product table.long_cash_margin_deposit_rate<BR>
     * <BR>
     * �@@["Def.SHORT_CASH_MARGIN_DEPOSIT_RATE"]<BR>
     * �@@�@@row = eqtype_traded_product table.short_cash_margin_deposit_rate<BR>
     * <BR>
     * �@@["Def.MARGIN_RATIO"]<BR>
     * �@@�@@row = product table.margin_ratio<BR>
     * <BR>
     * �@@["Def.ESTIMATION_PRICE"]<BR>
     * �@@�@@row = product table.estimation_price<BR>
     * <BR>
     * �@@["Def.SECURITIES_ESTIMATION_RATIO"]<BR>
     * �@@�@@row = product table.<BR>
     * <BR>
     * �@@["Def.LAST_CLOSING_PRICE"]<BR>
     * �@@�@@row = eqtype_traded_product table.last_closing_price<BR>
     * <BR>
     * �@@["Def.BASE_PRICE"]<BR>
     * �@@�@@row = eqtype_traded_product table.base_price<BR>
     * <BR>
     * �@@["Def.PRICE_RANGE_TYPE"]<BR>
     * �@@�@@row = eqtype_traded_product table.price_range_type<BR>
     * <BR>
     * �@@["Def.PRICE_RANGE_UNIT_TYPE"]<BR>
     * �@@�@@Row = eqtype_traded_product table.price_range_unit_type<BR>
     * <BR>
     * �@@["Def.LOW_COMPULSIVE_PRICE_RANGE"]<BR>
     * �@@�@@row = eqtype_traded_product table.low_compulsive_price_range<BR>
     * <BR>
     * �@@["Def.HIGH_COMPULSIVE_PRICE_RANGE"]<BR>
     * �@@�@@row = eqtype_traded_product table.high_compulsive_price_range<BR>
     * <BR>
     * �@@["Def.COMPULSIVE_LIMITED_UNIT"]<BR>
     * �@@�@@row = eqtype_traded_product table.compulsive_limited_unit<BR>
     * <BR>
     * 2�jReturn the acquired field name<BR>
     * <BR>
     * @@param l_strSmallItemDiv - �i�����ڋ敪�j<BR>
     * <BR>
     * ����`�l�ɂ��Ă�DB���C�A�E�g<BR>
     * �@@�u�������������ݒ�e�[�u���d�l.xls�v�Q��<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * l_strSmallItemDiv<BR>
     * <BR>
     * ��Refer to the DB layout "eqtype_product_condition_table.xls" about defined
     * values<BR>
     * <BR>
     *
     * @@return java.lang.String
     * @@roseuid 418A26B10014
     */
    protected String getColumnName(String l_strSmallItemDiv)
    {
        final String STR_METHOD_NAME = "getRow(String)";
        log.entering(STR_METHOD_NAME);

        String l_strColumnName = null;
        // If parameter.smallItemDiv is["Def.TRADE_STOP"]
        if (WEB3AdminEquitySmallItemDivDef.TRADE_STOP.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "trade_stop";
        // If parameter.smallItemDiv is["Def.MARGIN_SYS_TRADE_STOP"]
        } else if (WEB3AdminEquitySmallItemDivDef.MARGIN_SYS_TRADE_STOP.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "margin_sys_trade_stop";
        // If parameter.smallItemDiv is["Def.MARGIN_SYS_TRADE_STOP"]
        } else if (WEB3AdminEquitySmallItemDivDef.MARGIN_GEN_TRADE_STOP.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "margin_gen_trade_stop";
        // If parameter.smallItemDiv is["Def.BUY_CASH_STOP"]
        } else if (WEB3AdminEquitySmallItemDivDef.BUY_CASH_STOP.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "buy_cash_stop";
        // If parameter.smallItemDiv is["Def.SELL_CASH_STOP"]
        } else if (WEB3AdminEquitySmallItemDivDef.SELL_CASH_STOP.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "sell_cash_stop";
        // If parameter.smallItemDiv is["Def.LONG_MARGIN_SYS_STOP"]
        } else if (WEB3AdminEquitySmallItemDivDef.LONG_MARGIN_SYS_STOP.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "long_margin_sys_stop";
        //  If parameter.smallItemDiv is["Def.SHORT_MARGIN_SYS_STOP"]
        } else if (WEB3AdminEquitySmallItemDivDef.SHORT_MARGIN_SYS_STOP.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "short_margin_sys_stop";
        // If parameter.smallItemDiv is["Def.LONG_CLOSE_MARGIN_SYS_STOP"]
        } else if (
            WEB3AdminEquitySmallItemDivDef.LONG_CLOSE_MARGIN_SYS_STOP.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "long_close_margin_sys_stop";
        // If parameter.smallItemDiv is["Def.SHORT_CLOSE_MARGIN_SYS_STOP"]
        } else if (
            WEB3AdminEquitySmallItemDivDef.SHORT_CLOSE_MARGIN_SYS_STOP.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "short_close_margin_sys_stop";
        // If parameter.smallItemDiv is["Def.LONG_SWAP_MARGIN_SYS_STOP"]
        } else if (
            WEB3AdminEquitySmallItemDivDef.LONG_SWAP_MARGIN_SYS_STOP.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "long_swap_margin_sys_stop";
        // If parameter.smallItemDiv is["Def.SHORT_SWAP_MARGIN_SYS_STOP"]
        } else if (
            WEB3AdminEquitySmallItemDivDef.SHORT_SWAP_MARGIN_SYS_STOP.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "short_swap_margin_sys_stop";
        // If parameter.smallItemDiv is["Def.LONG_MARGIN_GEN_STOP"]
        } else if (
            WEB3AdminEquitySmallItemDivDef.LONG_MARGIN_GEN_STOP.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "long_margin_gen_stop";
        // If parameter.smallItemDiv is["Def.SHORT_MARGIN_GEN_STOP"]
        } else if (
            WEB3AdminEquitySmallItemDivDef.SHORT_MARGIN_GEN_STOP.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "short_margin_gen_stop";
        // If parameter.smallItemDiv is["Def.LONG_CLOSE_MARGIN_GEN_STOP"]
        } else if (
            WEB3AdminEquitySmallItemDivDef.LONG_CLOSE_MARGIN_GEN_STOP.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "long_close_margin_gen_stop";
        // If parameter.smallItemDiv is["Def.SHORT_CLOSE_MARGIN_GEN_STOP"]
        } else if (
            WEB3AdminEquitySmallItemDivDef.SHORT_CLOSE_MARGIN_GEN_STOP.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "short_close_margin_gen_stop";
        // If parameter.smallItemDiv is["Def.LONG_SWAP_MARGIN_GEN_STOP"]
        } else if (
            WEB3AdminEquitySmallItemDivDef.LONG_SWAP_MARGIN_GEN_STOP.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "long_swap_margin_gen_stop";
        // If parameter.smallItemDiv is["Def.SHORT_SWAP_MARGIN_GEN_STOP"]
        } else if (
            WEB3AdminEquitySmallItemDivDef.SHORT_SWAP_MARGIN_GEN_STOP.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "short_swap_margin_gen_stop";
        // If parameter.smallItemDiv is["Def.BUY_SPOT_MARKET_ORD_DES_STOP"]
        } else if (
            WEB3AdminEquitySmallItemDivDef.BUY_SPOT_MARKET_ORD_DES_STOP.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "buy_spot_market_ord_des_stop";
        // If parameter.smallItemDiv is["Def.SELL_SPOT_MARKET_ORD_DES_STOP"]
        } else if (
            WEB3AdminEquitySmallItemDivDef.SELL_SPOT_MARKET_ORD_DES_STOP.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "sell_spot_market_ord_des_stop";
        // If parameter.smallItemDiv is["Def.LONG_MS_MARKET_ORD_DES_STOP"]
        } else if (
            WEB3AdminEquitySmallItemDivDef.LONG_MS_MARKET_ORD_DES_STOP.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "long_ms_market_ord_des_stop";
        // If parameter.smallItemDiv is["Def.SHORT_MS_MARKET_ORD_DES_STOP"]
        } else if (
            WEB3AdminEquitySmallItemDivDef.SHORT_MS_MARKET_ORD_DES_STOP.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "short_ms_market_ord_des_stop";
        // If parameter.smallItemDiv is["Def.LONG_CMS_MARKET_ORD_DES_STOP"]
        } else if (
            WEB3AdminEquitySmallItemDivDef.LONG_CMS_MARKET_ORD_DES_STOP.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "long_cms_market_ord_des_stop";
        // If parameter.smallItemDiv is["Def.SHORT_CMS_MARKET_ORD_DES_STOP"]
        } else if (
            WEB3AdminEquitySmallItemDivDef.SHORT_CMS_MARKET_ORD_DES_STOP.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "short_cms_market_ord_des_stop";
        // If parameter.smallItemDiv is["Def.LONG_MG_MARKET_ORD_DES_STOP"]
        } else if (
            WEB3AdminEquitySmallItemDivDef.LONG_MG_MARKET_ORD_DES_STOP.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "long_mg_market_ord_des_stop";
        //  If parameter.smallItemDiv is["Def.SHORT_MG_MARKET_ORD_DES_STOP"]
        } else if (
            WEB3AdminEquitySmallItemDivDef.SHORT_MG_MARKET_ORD_DES_STOP.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "short_mg_market_ord_des_stop";
        // If parameter.smallItemDiv is["Def.LONG_CMG_MARKET_ORD_DES_STOP"]
        } else if (
            WEB3AdminEquitySmallItemDivDef.LONG_CMG_MARKET_ORD_DES_STOP.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "long_cmg_market_ord_des_stop";
        // If parameter.smallItemDiv is["Def.SHORT_CMG_MARKET_ORD_DES_STOP"]
        } else if (
            WEB3AdminEquitySmallItemDivDef.SHORT_CMG_MARKET_ORD_DES_STOP.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "short_cmg_market_ord_des_stop";
        // If parameter.smallItemDiv is["Def.BUY_MINI_STOCK_STOP"]
        } else if (WEB3AdminEquitySmallItemDivDef.BUY_MINI_STOCK_STOP.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "buy_mini_stock_stop";
        // If parameter.smallItemDiv is["Def.SELL_MINI_STOCK_STOP"]
        } else if (WEB3AdminEquitySmallItemDivDef.SELL_MINI_STOCK_STOP.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "sell_mini_stock_stop";
        // If parameter.smallItemDiv is["Def.STANDARD_NAME"]
        } else if (WEB3AdminEquitySmallItemDivDef.STANDARD_NAME.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "standard_name";
        //parameter.smallItemDiv = �D��s��
		} else if (WEB3AdminEquitySmallItemDivDef.PRIMARY_MARKET.equals(l_strSmallItemDiv))
		{
			l_strColumnName = "primary_market_id";
		//parameter.smallItemDiv = ��������戵
		} else if (WEB3AdminEquitySmallItemDivDef.CAPITAL_GAIN_TAX_DEALING.equals(l_strSmallItemDiv))
		{
			l_strColumnName = "capital_gain_tax_dealings_reg";
        // If parameter.smallItemDiv is["Def.LOT_SIZE"]
        } else if (WEB3AdminEquitySmallItemDivDef.LOT_SIZE.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "lot_size";
        // If parameter.smallItemDiv is["Def.OPEN_OTC_DIV"]
        } else if (WEB3AdminEquitySmallItemDivDef.OPEN_OTC_DIV.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "open_otc_div";
        // If parameter.smallItemDiv is["Def.LIST_TYPE"]
        } else if (WEB3AdminEquitySmallItemDivDef.LIST_TYPE.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "list_type";
        // If parameter.smallItemDiv is["Def.TODAY_DEP_FUND_REG"]
        } else if (WEB3AdminEquitySmallItemDivDef.TODAY_DEP_FUND_REG.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "today_dep_fund_reg";
        // If parameter.smallItemDiv is["Def.MINI_STOCK_MARKET"]
        } else if (WEB3AdminEquitySmallItemDivDef.MINI_STOCK_MARKET.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "mini_stock_can_dealt";
        // If parameter.smallItemDiv is["Def.MARGIN_SYS_PRODUCT_TYPE"]
        } else if (WEB3AdminEquitySmallItemDivDef.MARGIN_SYS_PRODUCT_TYPE.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "margin_sys_product_type";
        // If parameter.smallItemDiv is["Def.MARGIN_GEN_PRODUCT_TYPE"]
        } else if (WEB3AdminEquitySmallItemDivDef.MARGIN_GEN_PRODUCT_TYPE.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "margin_gen_product_type";
        // If parameter.smallItemDiv is["Def.LONG_MARGIN_DEPOSIT_RATE"]
        } else if (
            WEB3AdminEquitySmallItemDivDef.LONG_MARGIN_DEPOSIT_RATE.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "long_margin_deposit_rate";
        // If parameter.smallItemDiv is["Def.SHORT_MARGIN_DEPOSIT_RATE"]
        } else if (
            WEB3AdminEquitySmallItemDivDef.SHORT_MARGIN_DEPOSIT_RATE.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "short_margin_deposit_rate";
        // If parameter.smallItemDiv is["Def.LONG_CASH_MARGIN_DEPOSIT_RATE"]
        } else if (
            WEB3AdminEquitySmallItemDivDef.LONG_CASH_MARGIN_DEPOSIT_RATE.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "long_cash_margin_deposit_rate";
        // If parameter.smallItemDiv is["Def.SHORT_CASH_MARGIN_DEPOSIT_RATE"]
        } else if (WEB3AdminEquitySmallItemDivDef
            .SHORT_CASH_MARGIN_DEPOSIT_RATE
            .equals(l_strSmallItemDiv))
        {
            l_strColumnName = "short_cash_margin_deposit_rate";
        // If parameter.smallItemDiv is["Def.MARGIN_RATIO"]
        } else if (WEB3AdminEquitySmallItemDivDef.MARGIN_RATIO.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "margin_ratio";
        // If parameter.smallItemDiv is["Def.ESTIMATION_PRICE"]
        } else if (WEB3AdminEquitySmallItemDivDef.ESTIMATION_PRICE.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "estimation_price";
        // If parameter.smallItemDiv is["Def.SECURITIES_ESTIMATION_RATIO"]
        } else if (
            WEB3AdminEquitySmallItemDivDef.SECURITIES_ESTIMATION_RATIO.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "securities_estimation_ratio";
        // If parameter.smallItemDiv is["Def.LAST_CLOSING_PRICE"]
        } else if (WEB3AdminEquitySmallItemDivDef.LAST_CLOSING_PRICE.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "last_closing_price";
        // If parameter.smallItemDiv is["Def.BASE_PRICE"]
        } else if (WEB3AdminEquitySmallItemDivDef.BASE_PRICE.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "base_price";
        // If parameter.smallItemDiv is["Def.PRICE_RANGE_TYPE"]
        } else if (WEB3AdminEquitySmallItemDivDef.PRICE_RANGE_TYPE.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "price_range_type";
        // If parameter.smallItemDiv is["Def.PRICE_RANGE_UNIT_TYPE"]
        } else if (WEB3AdminEquitySmallItemDivDef.PRICE_RANGE_UNIT_TYPE.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "price_range_unit_type";
        // If parameter.smallItemDiv is["Def.LOW_COMPULSIVE_PRICE_RANGE"]
        } else if (
            WEB3AdminEquitySmallItemDivDef.LOW_COMPULSIVE_PRICE_RANGE.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "low_compulsive_price_range";
        // If parameter.smallItemDiv is["Def.HIGH_COMPULSIVE_PRICE_RANGE"]
        } else if (
            WEB3AdminEquitySmallItemDivDef.HIGH_COMPULSIVE_PRICE_RANGE.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "high_compulsive_price_range";
        // If parameter.smallItemDiv is["Def.COMPULSIVE_LIMITED_UNIT"]
        } else if (WEB3AdminEquitySmallItemDivDef.COMPULSIVE_LIMITED_UNIT.equals(l_strSmallItemDiv))
        {
            l_strColumnName = "compulsive_limited_unit";
        }

        log.exiting(STR_METHOD_NAME);
        return l_strColumnName;
    }

    /**
     * �iset�\��X�V���j<BR>
     * <BR>
     * �����̖��������ݒ�X�V���ɗ\��X�V����ݒ肷��B<BR>
     * <BR>
     * �P�j�X�V���s�����ǂ����̃`�F�b�N<BR>
     * �@@�ȉ��̏����̂��Âꂩ�ɊY������ꍇ�́A�ȍ~�̏������s���B<BR>
     * �@@���Y�����Ȃ��ꍇ�́A�����ŏI������B<BR>
     * �@@�@@�@@�p�����[�^.���������ݒ���.�o�^�l(����) !=<BR>
     * �@@�@@�@@�@@�p�����[�^.���������ݒ���.�ύX��o�^�l(����)<BR>
     * �@@�@@�A�p�����[�^.���������ݒ���.�o�^�l(����) !=<BR>
     * �@@�@@�@@�@@�p�����[�^.���������ݒ���.�ύX��o�^�l(����)<BR>
     * �@@�@@�B�p�����[�^.���������ݒ���.�o�^�l(�\��) !=<BR>
     * �@@�@@�@@�@@�p�����[�^.���������ݒ���.�ύX��o�^�l(�\��)<BR>
     * �@@�@@�C�p�����[�^.���������ݒ���.�K�p����From !=<BR>
     * �@@�@@�@@�@@�p�����[�^.���������ݒ���.�ύX��K�p����From<BR>
     * �@@�@@�D�p�����[�^.���������ݒ���.�K�p����To !=<BR>
     * �@@�@@�@@�@@�p�����[�^.���������ݒ���.�ύX��K�p����To<BR>
     * <BR>
     * �Q�j�X�V�f�[�^��ݒ肷��B<BR>
     * �@@[�p�����[�^.���������ݒ�Params == null�̏ꍇ]<BR>
     * �@@�@@�@@���������ݒ�Params���쐬����B<BR>
     * �@@�@@�Athis.get��()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@[get��()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�����ڋ敪�F�@@�p�����[�^.���������ݒ���.�����ڋ敪<BR>
     * �@@�@@�B�쐬�������������ݒ�Params�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@�@@�������������ݒ�ID = �V�K�̔Ԃ����������������ݒ�ID(*1)<BR>
     * �@@�@@�@@�،���ЃR�[�h     = �p�����[�^.��������.�،���ЃR�[�h<BR>
     * �@@�@@�@@�����R�[�h         = �p�����[�^.��������.�����R�[�h<BR>
     * �@@�@@�@@����ID             = �p�����[�^.��������.����ID<BR>
     * �@@�@@�@@�s��R�[�h         = �p�����[�^.���������ݒ���.�s��R�[�h<BR>
     * �@@�@@�@@�s��ID             =
     * �p�����[�^.���������ݒ���.�s��R�[�h�ɊY������s��ID<BR>
     * �@@�@@�@@�@@���p�����[�^.���������ݒ���.�s��R�[�h ==
     * "���̑�"�̏ꍇ�A0���Z�b�g�B<BR>
     * �@@�@@�@@�ݒ�Ώۗ�       = get��()�̖߂�l<BR>
     * �@@�@@�@@�區�ڋ敪         = �p�����[�^.���������ݒ���.�區�ڋ敪<BR>
     * �@@�@@�@@�����ڋ敪         = �p�����[�^.���������ݒ���.�����ڋ敪<BR>
     * �@@�@@�@@�ݒ�f�[�^(����)   = (*2)<BR>
     * �@@�@@�@@�ݒ�f�[�^(����)   = (*2)<BR>
     * �@@�@@�@@�ݒ�f�[�^(�\��)   = (*2)<BR>
     * �@@�@@�@@�K�p����From       = �p�����[�^.���������ݒ���.�ύX��K�p����From<BR>
     * �@@�@@�@@�@@���p�����[�^.���������ݒ���.�K�p����From !=<BR>
     * �@@�@@�@@�@@���p�����[�^.���������ݒ���.�ύX��K�p����From�̏ꍇ�̂݃Z�b�g�B<BR>
     * �@@�@@�@@�K�p����To         = �p�����[�^.���������ݒ���.�ύX��K�p����To<BR>
     * �@@�@@�@@�@@���p�����[�^.���������ݒ���.�K�p����To !=<BR>
     * �@@�@@�@@�@@���p�����[�^.���������ݒ���.�ύX��K�p����To�̏ꍇ�̂݃Z�b�g�B<BR>
     * �@@�@@�@@�폜�t���O         = "0:���폜"<BR>
     * �@@�@@�@@�X�V�҃R�[�h     = �p�����[�^.�X�V�҃R�[�h<BR>
     * �@@�@@�@@�쐬���t         = ���ݎ���(*1)<BR>
     * �@@�@@�@@�X�V���t         = ���ݎ���(*1)<BR>
     * �@@�@@�Cthis.set�X�V�e�[�u�����()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@[set�X�V�e�[�u�����()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�������������ݒ�Params�F�@@�B�ɂăv���p�e�B�Z�b�g�����������������ݒ�Para
     * ms<BR>
     * �@@<BR>
     * �@@�@@(*1)�V�K�̔Ԃ����������������ݒ�ID<BR>
     * �@@�@@�@@�������������ݒ�DAO.newPkValue()�ɂĎ擾����ID<BR>
     * <BR>
     * �@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@�@@this.clone���������ݒ�Params()���R�[������B<BR>
     * �@@�@@�@@�@@[clone���������ݒ�Params()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@���������ݒ�Params�F�@@�p�����[�^.���������ݒ�Params<BR>
     * �@@�@@�A�@@�̖߂�l�ɑ΂��āA�ȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@�@@�ݒ�f�[�^(����)   = (*2)<BR>
     * �@@�@@�@@�ݒ�f�[�^(����)   = (*2)<BR>
     * �@@�@@�@@�ݒ�f�[�^(�\��)   = (*2)<BR>
     * �@@�@@�@@�K�p����From       = �p�����[�^.���������ݒ���.�ύX��K�p����From<BR>
     * �@@�@@�@@�@@���p�����[�^.���������ݒ���.�K�p����From !=<BR>
     * �@@�@@�@@�@@���p�����[�^.���������ݒ���.�ύX��K�p����From�̏ꍇ�̂݃Z�b�g�B<BR>
     * �@@�@@�@@�K�p����To         = �p�����[�^.���������ݒ���.�ύX��K�p����To<BR>
     * �@@�@@�@@�@@���p�����[�^.���������ݒ���.�K�p����To !=<BR>
     * �@@�@@�@@�@@���p�����[�^.���������ݒ���.�ύX��K�p����To�̏ꍇ�̂݃Z�b�g�B<BR>
     * �@@�@@�@@�X�V�҃R�[�h     = �p�����[�^.�X�V�҃R�[�h<BR>
     * �@@�@@�@@�X�V���t         = ���ݎ���(*1)<BR>
     * <BR>
     * �R�j�p�����[�^.���������ݒ�X�V���.add()���R�[������B<BR>
     * <BR>
     * �@@[add()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@obj�F�@@�Q�j�ɂčX�V�f�[�^���Z�b�g�����������������ݒ�Params<BR>
     * <BR>
     * (*1)���ݎ����E�E�E<BR>
     * �@@ThreadLocalSystemAttributesRegistry.getAttribute(������ԊǗ�.TIMESTAMP_TAG)<B
     * R>
     * �ɂĎ擾�������ݎ���<BR>
     * <BR>
     * (*2)�ݒ�f�[�^(�����A�����A�\��)�E�E�E<BR>
     * �@@�@@���p�����[�^.���������ݒ���.�o�^�l(*) !=<BR>
     * �@@�@@���p�����[�^.���������ݒ���.�ύX��o�^�l(*)�̏ꍇ�̂݃Z�b�g�B<BR>
     * <BR>
     * �@@�@@[�p�����[�^.���������ݒ���.�����ڋ敪 == "�D��s��"�̏ꍇ]<BR>
     * �@@�@@�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��()�̖߂�l.�s��ID���Z�b�g�B<BR>
     * �@@�@@�@@���p�����[�^.���������ݒ���.�ύX��o�^�l(*) == null�̏ꍇ��null���Z�b�g�B<BR>
     * <BR>
     * �@@�@@�@@[get�s��()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�،���ЃR�[�h�F�@@�p�����[�^.��������.�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@�s��R�[�h�F�@@�p�����[�^.���������ݒ���.�ύX��o�^�l(*)<BR>
     * <BR>
     * �@@�@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@�@@�p�����[�^.���������ݒ���.�ύX��o�^�l(*)<BR>
     * <BR>
     * �@@�@@�ύX��o�^�l(*)�́A<BR>
     * �@@�@@�@@�E�ύX��o�^�l(����)<BR>
     * �@@�@@�@@�E�ύX��o�^�l(����)<BR>
     * �@@�@@�@@�E�ύX��o�^�l(�\��)���ꂼ�ꂪ�ΏہB<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * setScheduleUpdatedInfo<BR>
     * <BR>
     * Set scheduleUpdateInfo into the argument, ProductCondSettingUpdateInfo<BR>
     * <BR>
     * �P�jCheck whether update is executed or not<BR>
     * �@@Execute the following process if it meets with either of the following
     * conditions<BR>
     * �@@��End here if it does not<BR>
     * �@@�@@�@@parameter.l_productConditionInfo.bizDateRegistData !=<BR>
     * �@@�@@�@@�@@parameter.l_productConditionInfo.aftBizDateRegistData<BR>
     * �@@�@@�Aparameter.l_productConditionInfo.nextBizDateRegistData !=<BR>
     * �@@�@@�@@�@@parameter.l_productConditionInfo.aftNextBizDateRegistData<BR>
     * �@@�@@�Bparameter.l_productConditionInfo.scheduleRegistData !=<BR>
     * �@@�@@�@@�@@parameter.l_productConditionInfo.aftScheduleRegistData<BR>
     * �@@�@@�Cparameter.l_productConditionInfo.applyStartDate !=<BR>
     * �@@�@@�@@�@@parameter.l_productConditionInfo.aftApplyStartDate<BR>
     * �@@�@@�Dparameter.l_productConditionInfo.applyEndDate !=<BR>
     * �@@�@@�@@�@@parameter.l_productConditionInfo.aftApplyEndDate<BR>
     * <BR>
     * �Q�jSet updateData<BR>
     * �@@[If parameter.eqtypeProductConditionParams == null]<BR>
     * �@@�@@�@@Create eqtypeProductConditionParams<BR>
     * �@@�@@�ACall this.getRow()<BR>
     * <BR>
     * �@@�@@�@@[parameter set to getRow()]<BR>
     * �@@�@@�@@�@@smallItemDiv: parameter.l_productConditionInfo.smallItemDiv<BR>
     * �@@�@@�B Set the following properties to  the created
     * eqtypeProductConditionParams<BR>
     * �@@�@@�@@eqtype_product_condition_id = newly counted
     * eqtype_product_condition_id(*1)<BR>
     * �@@�@@�@@institution_code     = parameter.l_equityProduct.institutionCode<BR>
     * �@@�@@�@@product_code         = parameter.l_equityProduct.productCode<BR>
     * �@@�@@�@@product_id             = parameter.l_equityProduct.productId<BR>
     * �@@�@@�@@market_code         = parameter.l_productConditionInfo.marketCode<BR>
     * �@@�@@�@@market_id             = parameter.l_productConditionInfo.marketId
     * corresponding to marketCode<BR>
     * �@@�@@�@@�@@��parameter.l_productConditionInfo.marketCode == Set 0 if
     * "Def.DEFAULT"<BR>
     * �@@�@@�@@column_name       = return value of getROW()<BR>
     * �@@�@@�@@large_item_div         = parameter.l_productConditionInfo.largeItemDiv<BR>
     * �@@�@@�@@small_item_div         = parameter.l_productConditionInfo.smallItemDiv<BR>
     * �@@�@@�@@data_today   = parameter.l_productConditionInfo.aftBizDateRegistData<BR>
     * �@@�@@�@@data_next_day   =
     * parameter.l_productConditionInfo.aftNextBizDateRegistData<BR>
     * �@@�@@�@@data_plan   = parameter.l_productConditionInfo.aftScheduleRegistData<BR>
     * �@@�@@�@@term_from       = parameter.l_productConditionInfo.aftApplyStartDate<BR>
     * �@@�@@�@@term_to         = parameter.l_productConditionInfo.aftApplyEndDate<BR>
     * �@@�@@�@@delete_flg         = "0: Def.DELETE_NO "<BR>
     * �@@�@@�@@last_updater     = parameter.lastUpdater<BR>
     * �@@�@@�@@created_timestamp         = timeStamp(*1)<BR>
     * �@@�@@�@@last_updated_timestamp         = timeStam(*1)<BR>
     * �@@�@@�CCall this.setUpdateTableInfo()<BR>
     * <BR>
     * �@@�@@�@@[parameter set into setUpdateTableInfo()]<BR>
     * �@@�@@�@@�@@l_eqtypeProductConditionParams: eqtypeProductConditionParams set in
     * 'Property Set' in �B<BR>
     * �@@<BR>
     * �@@�@@(*1)newly counted  eqtype_product_condition_id<BR>
     * �@@�@@�@@ID acquired at eqtypeProductConditionDAO.newPkValue()<BR>
     * <BR>
     * �@@[For other cases]<BR>
     * �@@�@@�@@Call this.cloneEqtypeProductConditionParams()<BR>
     * �@@�@@�@@�@@[parameter set into cloneEqtypeProductConditionParams()]<BR>
     * �@@�@@�@@�@@�@@l_eqtypeProductConditionParams:
     * parameter.eqtypeProductConditionParams<BR>
     * �@@�@@Set the following properties for the return values of �A�@@<BR>
     * �@@�@@�@@data_today   = parameter.l_productConditionInfo.bizDateRegistData<BR>
     * �@@�@@�@@data_next_day   =
     * parameter.l_productConditionInfo.aftNextBizDateRegistData<BR>
     * �@@�@@�@@data_plan   = parameter.l_productConditionInfo.aftScheduleRegistData<BR>
     * �@@�@@�@@term_from       = parameter.l_productConditionInfo.aftApplyStartDate<BR>
     * �@@�@@�@@term_to         = parameterl_productConditionInfo.aftApplyEndDate<BR>
     * �@@�@@�@@last_updater     = parameter.lastUpdater<BR>
     * �@@�@@�@@last_updated_timestamp         = timeStamp(*1)<BR>
     * <BR>
     * 3)Call parameter.l_productConditionInfo.add()<BR>
     * <BR>
     * �@@[parameter set into add()]<BR>
     * �@@�@@obj: eqtypeProductConditionParams that sets updateData at 2)<BR>
     * <BR>
     * (*1)timeStamp�E�E�E<BR>
     * �@@timeStamp acquired at
     * ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManageme
     * nt.TIMESTAMP_TAG)<BR>
     * <BR>
     * @@param l_productCondSettingUpdateInfo - �i���������ݒ�X�V���j<BR>
     * <BR>
     * ���������ݒ�X�V���I�u�W�F�N�g<BR>
     * <BR>
     * l_productCondSettingUpdateInfo<BR>
     * <BR>
     *
     * @@param l_productConditionInfo - �i���������ݒ���j<BR>
     * <BR>
     * ���������ݒ���<BR>
     * <BR>
     * l_productConditionInfo<BR>
     * <BR>
     * @@param l_equityProduct - �i���������I�u�W�F�N�g�j<BR>
     * <BR>
     * ���������I�u�W�F�N�g<BR>
     * <BR>
     * l_equityProduct<BR>
     * <BR>
     *
     * @@param l_eqtypeProductConditionParams - �i�������������ݒ�Params�j<BR>
     * <BR>
     * ������������Params<BR>
     * <BR>
     * l_eqtypeProductConditionParams<BR>
     * <BR>
     * @@param l_lastUpdater - �i�X�V�҃R�[�h�j<BR>
     * <BR>
     * �X�V�҃R�[�h<BR>
     * <BR>
     * l_lastUpdater<BR>
     * <BR>
     * @@throws DataNetworkException DataNetworkException
     * @@throws DataQueryException DataQueryException
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 418A2710017A
     */
    protected void setScheduleUpdatedInfo(
        WEB3AdminPMProductCondSettingUpdateInfo l_productCondSettingUpdateInfo,
        WEB3AdminPMProductCondConfigUnit l_productConditionInfo,
        WEB3EquityProduct l_equityProduct,
        EqtypeProductConditionParams l_eqtypeProductConditionParams,
        String l_lastUpdater)
        throws DataNetworkException, DataQueryException, WEB3BaseException, NotFoundException
    {
        final String STR_METHOD_NAME =
            "setScheduleUpdatedInfo(ProductCondSettingUpdateInfo, "
                + "WEB3AdminPMProductCondConfigUnit, "
                + "WEB3EquityProduct, EqtypeProductConditionParams, String)";
        log.entering(STR_METHOD_NAME);

        final String L_DATE_FORMAT = "yyyyMMdd";
        final String L_EMPTY_STRING = "";
        final String L_HYPEN = "-";
        String l_strBizDateRegistData = null;
        String l_strAftBizDateRegistData = null;
        String l_strNextBizDateRegistData = null;
        String l_strAftNextBizDateRegistData = null;
        String l_strScheduleRegistData = null;
        String l_strAftScheduleRegistData = null;
        String l_strApplyStartDate = null;
        String l_strAftApplyStartDate = null;
        String l_strApplyEndDate = null;
        String l_strAftApplyEndDate = null;
        String l_strRow = null;
        String l_strSmallItemDiv = null;
        Date l_startDate = null;
        Date l_endDate = null;
        Timestamp l_tsDate = null;

        l_strBizDateRegistData = l_productConditionInfo.bizDateRegistData;
        l_strAftBizDateRegistData = l_productConditionInfo.aftBizDateRegistData;
        l_strNextBizDateRegistData = l_productConditionInfo.nextBizDateRegistData;
        l_strAftNextBizDateRegistData = l_productConditionInfo.aftNextBizDateRegistData;
        l_strScheduleRegistData = l_productConditionInfo.scheduleRegistData;
        l_strAftScheduleRegistData = l_productConditionInfo.aftScheduleRegistData;
        l_strApplyStartDate = l_productConditionInfo.applyStartDate;
        l_strAftApplyStartDate = l_productConditionInfo.aftApplyStartDate;
        l_strApplyEndDate = l_productConditionInfo.applyEndDate;
        l_strAftApplyEndDate = l_productConditionInfo.aftApplyEndDate;
        l_strSmallItemDiv = l_productConditionInfo.smallItemDiv;

        l_tsDate =
            (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);

        if (l_strApplyStartDate != null && l_strApplyStartDate.length() > 8)
        {
            l_strApplyStartDate = l_strApplyStartDate.replaceAll(L_HYPEN, L_EMPTY_STRING);
            l_strApplyStartDate = l_strApplyStartDate.substring(0, 8);
        }

        if (l_strApplyEndDate != null && l_strApplyEndDate.length() > 8)
        {
            l_strApplyEndDate = l_strApplyEndDate.replaceAll(L_HYPEN, L_EMPTY_STRING);
            l_strApplyEndDate = l_strApplyEndDate.substring(0, 8);
        }

        /*
         * �P�jCheck whether update is executed or not<BR>
         * �@@Execute the following process if it meets with either of the following
         * conditions<BR>
         * �@@��End here if it does not<BR>
         * �@@�@@�@@parameter.l_productConditionInfo.bizDateRegistData !=<BR>
         * �@@�@@�@@�@@parameter.l_productConditionInfo.aftBizDateRegistData<BR>
         * �@@�@@�Aparameter.l_productConditionInfo.nextBizDateRegistData !=<BR>
         * �@@�@@�@@�@@parameter.l_productConditionInfo.aftNextBizDateRegistData<BR>
         * �@@�@@�Bparameter.l_productConditionInfo.scheduleRegistData !=<BR>
         * �@@�@@�@@�@@parameter.l_productConditionInfo.aftScheduleRegistData<BR>
         * �@@�@@�Cparameter.l_productConditionInfo.applyStartDate !=<BR>
         * �@@�@@�@@�@@parameter.l_productConditionInfo.aftApplyStartDate<BR>
         * �@@�@@�Dparameter.l_productConditionInfo.applyEndDate !=<BR>
         * �@@�@@�@@�@@parameter.l_productConditionInfo.aftApplyEndDate<BR>
         */
        if (isChange(l_strBizDateRegistData, l_strAftBizDateRegistData)
            || isChange(l_strNextBizDateRegistData, l_strAftNextBizDateRegistData)
            || isChange(l_strScheduleRegistData, l_strAftScheduleRegistData)
            || isChange(l_strApplyStartDate, l_strAftApplyStartDate)
            || isChange(l_strApplyEndDate, l_strAftApplyEndDate))
        {

            /*
             * �Q�jSet updateData
             * [If parameter.eqtypeProductConditionParams == null]
             */
            if (l_eqtypeProductConditionParams == null)
            {
                // �@@Create eqtypeProductConditionParams
                l_eqtypeProductConditionParams = new EqtypeProductConditionParams();

                //�ACall this.getRow()
                l_strRow = this.getColumnName(l_strSmallItemDiv);

                // �B Set the following properties to  the created
                l_eqtypeProductConditionParams.setEqtypeProductConditionId(
                    EqtypeProductConditionDao.newPkValue());
                String l_strInstitutionCode = l_equityProduct.getInstitution().getInstitutionCode();
                l_eqtypeProductConditionParams.setInstitutionCode(l_strInstitutionCode);
                l_eqtypeProductConditionParams.setProductCode(l_equityProduct.getProductCode());
                l_eqtypeProductConditionParams.setProductId(l_equityProduct.getProductId());
                l_eqtypeProductConditionParams.setMarketCode(l_productConditionInfo.marketCode);
                
                if (!l_productConditionInfo.marketCode.equals(WEB3MarketCodeDef.DEFAULT))
                {
	                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
	                WEB3GentradeFinObjectManager l_finObjectManager =
	                    (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
	                WEB3GentradeMarket l_market = null;
	                try
	                {
	                    l_market =
	                        (WEB3GentradeMarket)l_finObjectManager.getMarket(l_strInstitutionCode, l_productConditionInfo.marketCode);
	                }
	                catch (NotFoundException l_ex)
	                {
	                    throw new WEB3SystemLayerException(
	                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
	                        this.getClass().getName() + "." + STR_METHOD_NAME,
	                        "�،���ЃR�[�h:[" + l_strInstitutionCode +
	                        "] �s��R�[�h:[" + l_productConditionInfo.marketCode +
	                        "] �ɊY������s��I�u�W�F�N�g���擾�ł��܂���B",
	                        l_ex);
	                }
	                l_eqtypeProductConditionParams.setMarketId(l_market.getMarketId());
                }
                else
                {
                    l_eqtypeProductConditionParams.setMarketId(0);
                }
                l_eqtypeProductConditionParams.setColumnName(l_strRow);
                l_eqtypeProductConditionParams.setLargeItemDiv(l_productConditionInfo.largeItemDiv);
                l_eqtypeProductConditionParams.setSmallItemDiv(l_productConditionInfo.smallItemDiv);
                
                //�K�p����From���ύX���ꂽ�ꍇ�̂݃Z�b�g����B
                if (isChange(l_strApplyStartDate, l_strAftApplyStartDate))
                {
                    l_startDate =
                        WEB3DateUtility.getDate(
                            l_productConditionInfo.aftApplyStartDate,
                            L_DATE_FORMAT);
                    if (l_startDate != null)
                    {
    	                l_eqtypeProductConditionParams.setTermFrom(new Timestamp(l_startDate.getTime()));
                    }
                    else
                    {
                        l_eqtypeProductConditionParams.setTermFrom(null);
                    }
                }

                //�K�p����To���ύX���ꂽ�ꍇ�̂݃Z�b�g����B
                if (isChange(l_strApplyEndDate, l_strAftApplyEndDate))
                {
                    l_endDate =
                        WEB3DateUtility.getDate(l_productConditionInfo.aftApplyEndDate, L_DATE_FORMAT);
                    if (l_endDate != null)
                    {
    	                l_eqtypeProductConditionParams.setTermTo(new Timestamp(l_endDate.getTime()));
                    }
                    else
                    {
                        l_eqtypeProductConditionParams.setTermTo(null);
                    }
                }

                l_eqtypeProductConditionParams.setDeleteFlg(WEB3AdminEquityDeleteFlgDef.NOT_DELETE);
                l_eqtypeProductConditionParams.setLastUpdater(l_lastUpdater);
                l_eqtypeProductConditionParams.setCreatedTimestamp(l_tsDate);
                l_eqtypeProductConditionParams.setLastUpdatedTimestamp(l_tsDate);

                // �CCall this.setUpdateTableInfo()
                this.setUpdateTableInfo(l_eqtypeProductConditionParams);
                // [For other cases]
            } else
            {
				// Call this.cloneEqtypeProductConditionParams()
				l_eqtypeProductConditionParams =
					this.cloneEqtypeProductConditionParams(l_eqtypeProductConditionParams);

                //�K�p����From���ύX���ꂽ�ꍇ�̂݃Z�b�g����B
                if (isChange(l_strApplyStartDate, l_strAftApplyStartDate))
                {
                    l_startDate =
                        WEB3DateUtility.getDate(
                            l_productConditionInfo.aftApplyStartDate,
                            L_DATE_FORMAT);
                    if (l_startDate != null)
                    {
                        l_eqtypeProductConditionParams.setTermFrom(new Timestamp(l_startDate.getTime()));
                    }
                    else
                    {
                        l_eqtypeProductConditionParams.setTermFrom(null);
                    }
                }
                
                //�K�p����To���ύX���ꂽ�ꍇ�̂݃Z�b�g����B
                if (isChange(l_strApplyEndDate, l_strAftApplyEndDate))
                {
                    l_endDate =
                        WEB3DateUtility.getDate(l_productConditionInfo.aftApplyEndDate, L_DATE_FORMAT);
                    if (l_endDate != null)
                    {
                        l_eqtypeProductConditionParams.setTermTo(new Timestamp(l_endDate.getTime()));
                    }
                    else
                    {
                        l_eqtypeProductConditionParams.setTermTo(null);
                    }
                }
                
                l_eqtypeProductConditionParams.setLastUpdater(l_lastUpdater);
                l_eqtypeProductConditionParams.setLastUpdatedTimestamp(l_tsDate);
            }

			//�ݒ�f�[�^(����)�E�ݒ�f�[�^(����)�E�ݒ�f�[�^(�\��)�̃Z�b�g
			//[�p�����[�^.���������ݒ���.�����ڋ敪 == "�D��s��"�̏ꍇ] 
			if (WEB3AdminEquitySmallItemDivDef.PRIMARY_MARKET.equals(l_productConditionInfo.smallItemDiv))
			{
				FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
				WEB3GentradeFinObjectManager l_gentradeFinObjManager = 
					(WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
					
				//�ݒ�f�[�^(����)���ύX���ꂽ�ꍇ�̂݃Z�b�g����B
                if (isChange(l_strBizDateRegistData, l_strAftBizDateRegistData))
                {
    				if (l_productConditionInfo.aftBizDateRegistData == null)
    				{
    					l_eqtypeProductConditionParams.setDataToday(null);
    							
    				}else
    				{
    					Market l_marketToday = l_gentradeFinObjManager.getMarket(
    						l_equityProduct.getInstitution().getInstitutionCode(), 
    						l_productConditionInfo.aftBizDateRegistData
    						);
    							
    					l_eqtypeProductConditionParams.setDataToday(
    						String.valueOf(l_marketToday.getMarketId()));
    				}
                }
				//�ݒ�f�[�^(����)���ύX���ꂽ�ꍇ�̂݃Z�b�g����B
                if (isChange(l_strNextBizDateRegistData, l_strAftNextBizDateRegistData))
                {
    				if (l_productConditionInfo.aftNextBizDateRegistData == null)					
    				{
    					l_eqtypeProductConditionParams.setDataNextDay(null);
    				}else
    				{
    					Market l_marketNextDay = l_gentradeFinObjManager.getMarket(
    						l_equityProduct.getInstitution().getInstitutionCode(), 
    						l_productConditionInfo.aftNextBizDateRegistData
    						);
    							
    					l_eqtypeProductConditionParams.setDataNextDay(
    						String.valueOf(l_marketNextDay.getMarketId()));
    
    				}
                }
				//�ݒ�f�[�^(�\��)���ύX���ꂽ�ꍇ�̂݃Z�b�g����B
                if (isChange(l_strScheduleRegistData, l_strAftScheduleRegistData))
                {
    				if (l_productConditionInfo.aftScheduleRegistData == null)
    				{
    					l_eqtypeProductConditionParams.setDataPlan(null);
    				}else
    				{
    					Market l_marketSchedule = l_gentradeFinObjManager.getMarket(
    						l_equityProduct.getInstitution().getInstitutionCode(), 
    						l_productConditionInfo.aftScheduleRegistData
    						);
    
    					l_eqtypeProductConditionParams.setDataPlan(
    						String.valueOf(l_marketSchedule.getMarketId()));
    				}					
                }

			}else
			{
                //�ݒ�f�[�^(����)���ύX���ꂽ�ꍇ�̂݃Z�b�g����B
                if (isChange(l_strBizDateRegistData, l_strAftBizDateRegistData))
                {
    				if (l_productConditionInfo.aftBizDateRegistData == null)
    				{
    					l_eqtypeProductConditionParams.setDataToday(null);
    				}else
    				{
    					l_eqtypeProductConditionParams.setDataToday(
    					l_productConditionInfo.aftBizDateRegistData);
    				}
                }                    

                //�ݒ�f�[�^(����)���ύX���ꂽ�ꍇ�̂݃Z�b�g����B
                if (isChange(l_strNextBizDateRegistData, l_strAftNextBizDateRegistData))
                {
    				if (l_productConditionInfo.aftNextBizDateRegistData == null)
    				{
    					l_eqtypeProductConditionParams.setDataNextDay(null);
    				}else
    				{
    					l_eqtypeProductConditionParams.setDataNextDay(
    					l_productConditionInfo.aftNextBizDateRegistData);
    				}
                }
			
                //�ݒ�f�[�^(�\��)���ύX���ꂽ�ꍇ�̂݃Z�b�g����B
                if (isChange(l_strScheduleRegistData, l_strAftScheduleRegistData))
                {
    				if (l_productConditionInfo.aftScheduleRegistData == null)
    				{
    					l_eqtypeProductConditionParams.setDataPlan(null);
    				}else
    				{
    					l_eqtypeProductConditionParams.setDataPlan(
    					l_productConditionInfo.aftScheduleRegistData);
    				}
                }
			}

            // 3)Call parameter.l_productConditionInfo.add()
            l_productCondSettingUpdateInfo.scheduleUpdateInfo.add(l_eqtypeProductConditionParams);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �iset�X�V�e�[�u�����j<BR>
     * <BR>
     * �����̊������������ݒ�Params�ɍX�V�e�[�u�������Z�b�g����B<BR>
     * <BR>
     * �P�j�Ώۃe�[�u�����̃Z�b�g<BR>
     * �@@�p�����[�^.�������������ݒ�Params.�����ڋ敪���A<BR>
     * �@@["�D��s��" or "��p�|��" or "��p�]���P��" or "�a����،��]���|��"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������������ݒ�Params.�ݒ�Ώۃe�[�u���� = "product"<BR>
     * <BR>
     * �@@["��������������~" or "���x�M�p���������~" or<BR>
     * �@@ "��ʐM�p���������~" or "������" or "��������戵"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������������ݒ�Params.�ݒ�Ώۃe�[�u���� = "eqtype_product"<BR>
     * <BR>
     * �@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������������ݒ�Params.�ݒ�Ώۃe�[�u���� =
     * "eqtype_traded_product"<BR>
     * <BR>
     * �Q�j�ݒ�Ώۃf�[�^�^�̃Z�b�g<BR>
     * <BR>
     * �@@�p�����[�^.�������������ݒ�Params.�����ڋ敪���A<BR>
     * �@@["���敪" or "�X�����J�敪" or <BR>
     * �@@ "���x�M�p�����敪" or "��ʐM�p�����敪" or<BR>
     * �@@ "�����~�j�����s��" or "�l���`�F�b�N" or "�l���敪" or "������"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������������ݒ�Params.�ݒ�Ώۃf�[�^�^ = "VARCHAR2"<BR>
     * <BR>
     * �@@["��p�|��" or "��p�]���P��" or "�a����،��]���|��" or<BR>
     * �@@ "�����P��" or "���ۏ؋���" or "���ۏ؋���" or<BR>
     * �@@ "�������ۏ؋���" or "�������ۏ؋���" or "��l(�I�l)"<BR>
     * �@@ "��l" or "�����l��(���)" or "�����l��(����)"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������������ݒ�Params.�ݒ�Ώۃf�[�^�^ = "DECIMAL"<BR>
     * <BR>
     * �@@[��L�ȊO]<BR>
     * �@@�@@�p�����[�^.�������������ݒ�Params.�ݒ�Ώۃf�[�^�^ = "NUMBER"<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * setUpdateTableInfo<BR>
     * <BR>
     * Set updateTableInfo into the argument, eqtypeProductConditionParams<BR>
     * <BR>
     * 1)Set table_name<BR>
     * �@@If parameter.l_eqtypeProductConditionParams.smallItemDiv is<BR>
     * �@@["Def.MARGIN_RATIO" or "Def.ESTIMATION_PRICE" or
     * "Def.SECURITIES_ESTIMATION_RATIO"]<BR>
     * �@@�@@parameter.l_eqtypeProductConditionParams.table_name = "product"<BR>
     * <BR>
     * �@@ ["Def.TRADE_STOP" or "DEf.MARGIN_SYS_TRADE_STOP" or<BR>
     * �@@ "Def.MARGIN_GEN_TRADE_STOP" or "Def.STANDARD_NAME"]<BR>
     * �@@�@@parameter.l_eqtypeProductConditionParams.table_name = "eqtype_product"<BR>
     * <BR>
     * �@@ [For other cases]<BR>
     * �@@�@@parameter.l_eqtypeProductConditionParams.table_name =
     * "eqtype_traded_product"<BR>
     * <BR>
     * 2)Set data_type<BR>
     * <BR>
     * �@@If parameter.l_eqtypeProductConditionParams.small_item_div is<BR>
     * �@@["Def.LIST_TYPE" or "Def.�X�����J�敪" or <BR>
     * �@@ "Def.MARGIN_SYS_PRODUCT_TYPE" or "Def.MARGIN_GEN_PRODUCT_TYPE" or<BR>
     * �@@ "Def.MINI_STOCK_MARKET" or "Def.PRICE_RANGE_TYPE" or
     * "Def.PRICE_RANGE_UNIT_TYPE"]<BR>
     * �@@�@@parameter.l_eqtypeProductConditionParams.data_type = "Def.VARCHAR2"<BR>
     * <BR>
     * �@@["Def.MARGIN_RATIO" or "Def.ESTIMATION_PRICE" or
     * "Def.SECURITIES_ESTIMATION_RATIO" or<BR>
     * �@@ "Def.LOT_SIZE" or "Def.LONG_MARGIN_DEPOSIT_RATE" or
     * "Def.SHORT_MARGIN_DEPOSIT_RATE" or<BR>
     * �@@ "Def.LONG_CASH_MARGIN_DEPOSIT_RATE" or "Def.SHORT_CASH_MARGIN_DEPOSIT_RATE"
     * or<BR>
     * �@@ "Def.HIGH_COMPULSIVE_PRICE_RANGE" or "Def.LOW_COMPULSIVE_PRICE_RANGE"]<BR>
     * �@@�@@parameter.l_eqtypeProductConditionParams.data_type = "Def.DECIMAL"<BR>
     * <BR>
     * �@@[For other cases]<BR>
     * �@@�@@parameter.l_eqtypeProductConditionParams.data_type = "Def.NUMBER"<BR>
     * <BR>
     * @@param l_eqtypeProductConditionParams - �i�������������ݒ�Params�j<BR>
     * <BR>
     * ������������Params<BR>
     * <BR>
     * l_eqtypeProductConditionParams<BR>
     * <BR>
     * @@roseuid 418B0DE401FD
     */
    protected void setUpdateTableInfo(EqtypeProductConditionParams l_eqtypeProductConditionParams)
    {
        final String STR_METHOD_NAME = "setUpdateTableInfo(EqtypeProductConditionParams)";
        log.entering(STR_METHOD_NAME);

        String l_strSmallItemdiv = null;
        l_strSmallItemdiv = l_eqtypeProductConditionParams.small_item_div;

        /*
         * 1)Set table_name
         * �@@If parameter.l_eqtypeProductConditionParams.smallItemDiv is
         * �@@["Def.MARGIN_RATIO" or "Def.ESTIMATION_PRICE" or
         * "Def.SECURITIES_ESTIMATION_RATIO"]
         */
        if ((WEB3AdminEquitySmallItemDivDef.PRIMARY_MARKET).equals(l_strSmallItemdiv)
        	||(WEB3AdminEquitySmallItemDivDef.MARGIN_RATIO).equals(l_strSmallItemdiv)
            || (WEB3AdminEquitySmallItemDivDef.ESTIMATION_PRICE).equals(l_strSmallItemdiv)
            || (WEB3AdminEquitySmallItemDivDef.SECURITIES_ESTIMATION_RATIO).equals(
            l_strSmallItemdiv))
        {
            l_eqtypeProductConditionParams.setTableName("product");
            /*
             * �@@ ["Def.TRADE_STOP" or "DEf.MARGIN_SYS_TRADE_STOP" or
             * �@@ "Def.MARGIN_GEN_TRADE_STOP" or "Def.STANDARD_NAME"]
             */
        } else if ((WEB3AdminEquitySmallItemDivDef.TRADE_STOP).equals(l_strSmallItemdiv)
                || (WEB3AdminEquitySmallItemDivDef.MARGIN_SYS_TRADE_STOP).equals(l_strSmallItemdiv)
                || (WEB3AdminEquitySmallItemDivDef.MARGIN_GEN_TRADE_STOP).equals(l_strSmallItemdiv)
                || (WEB3AdminEquitySmallItemDivDef.STANDARD_NAME).equals(l_strSmallItemdiv)
				|| (WEB3AdminEquitySmallItemDivDef.COMPULSIVE_PRICE_RANGE).equals(l_strSmallItemdiv)
				|| (WEB3AdminEquitySmallItemDivDef.CAPITAL_GAIN_TAX_DEALING).equals(l_strSmallItemdiv))
        {
            l_eqtypeProductConditionParams.setTableName("eqtype_product");
            // [For other cases]
        } else
        {
            l_eqtypeProductConditionParams.setTableName("eqtype_traded_product");
        }

        /*
         * 2)Set data_type
         * �@@If parameter.l_eqtypeProductConditionParams.small_item_div is
         * �@@["Def.LIST_TYPE" or "Def.OPEN_OTC_DIV" or
         * �@@ "Def.MARGIN_SYS_PRODUCT_TYPE" or "Def.MARGIN_GEN_PRODUCT_TYPE" or
         * �@@ "Def.MINI_STOCK_MARKET" or "Def.PRICE_RANGE_TYPE" or
         * "Def.PRICE_RANGE_UNIT_TYPE"]
         */
        if ((WEB3AdminEquitySmallItemDivDef.LIST_TYPE).equals(l_strSmallItemdiv)
            || (WEB3AdminEquitySmallItemDivDef.OPEN_OTC_DIV).equals(l_strSmallItemdiv)
            || (WEB3AdminEquitySmallItemDivDef.MARGIN_SYS_PRODUCT_TYPE).equals(l_strSmallItemdiv)
            || (WEB3AdminEquitySmallItemDivDef.MARGIN_GEN_PRODUCT_TYPE).equals(l_strSmallItemdiv)
            || (WEB3AdminEquitySmallItemDivDef.MINI_STOCK_MARKET).equals(l_strSmallItemdiv)
            || (WEB3AdminEquitySmallItemDivDef.PRICE_RANGE_TYPE).equals(l_strSmallItemdiv)
            || (WEB3AdminEquitySmallItemDivDef.PRICE_RANGE_UNIT_TYPE).equals(l_strSmallItemdiv)
            || (WEB3AdminEquitySmallItemDivDef.STANDARD_NAME).equals(l_strSmallItemdiv))
        {
            l_eqtypeProductConditionParams.data_type = WEB3AdminEquityDataTypeDef.VARCHAR2;

            /*
             * "Def.MARGIN_RATIO" or "Def.ESTIMATION_PRICE"
             * "Def.SECURITIES_ESTIMATION_RATIO" or
             * "Def.LOT_SIZE" or "Def.LONG_MARGIN_DEPOSIT_RATE" or
             * "Def.SHORT_MARGIN_DEPOSIT_RATE" or
             * "Def.LONG_CASH_MARGIN_DEPOSIT_RATE" or
             * "Def.SHORT_CASH_MARGIN_DEPOSIT_RATE" or
             * "Def.HIGH_COMPULSIVE_PRICE_RANGE" or
             * "Def.LOW_COMPULSIVE_PRICE_RANGE" or
             * "Def.BASE_PRICE"
             */
        } else if (
            (WEB3AdminEquitySmallItemDivDef.MARGIN_RATIO).equals(l_strSmallItemdiv)
                || (WEB3AdminEquitySmallItemDivDef.ESTIMATION_PRICE).equals(l_strSmallItemdiv)
                || (WEB3AdminEquitySmallItemDivDef.SECURITIES_ESTIMATION_RATIO).equals(
                    l_strSmallItemdiv)
                || (WEB3AdminEquitySmallItemDivDef.LOT_SIZE).equals(l_strSmallItemdiv)
                || (WEB3AdminEquitySmallItemDivDef.LONG_MARGIN_DEPOSIT_RATE).equals(
                    l_strSmallItemdiv)
                || (WEB3AdminEquitySmallItemDivDef.SHORT_MARGIN_DEPOSIT_RATE).equals(
                    l_strSmallItemdiv)
                || (WEB3AdminEquitySmallItemDivDef.LONG_CASH_MARGIN_DEPOSIT_RATE).equals(
                    l_strSmallItemdiv)
                || (WEB3AdminEquitySmallItemDivDef.SHORT_CASH_MARGIN_DEPOSIT_RATE).equals(
                    l_strSmallItemdiv)
                || (WEB3AdminEquitySmallItemDivDef.HIGH_COMPULSIVE_PRICE_RANGE).equals(
                    l_strSmallItemdiv)
                || (WEB3AdminEquitySmallItemDivDef.LOW_COMPULSIVE_PRICE_RANGE).equals(
                    l_strSmallItemdiv)
	            || (WEB3AdminEquitySmallItemDivDef.LAST_CLOSING_PRICE).equals(
                    l_strSmallItemdiv)
                || (WEB3AdminEquitySmallItemDivDef.BASE_PRICE).equals(
                    l_strSmallItemdiv))
        {
            l_eqtypeProductConditionParams.data_type = WEB3AdminEquityDataTypeDef.DECIMAL;
        // [For other cases]
        } else
        {
            l_eqtypeProductConditionParams.data_type = WEB3AdminEquityDataTypeDef.NUMBER;
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �iclone�������������ݒ�Params�j<BR>
     * <BR>
     * �����̊������������ݒ�Params�̓��e���R�s�[����<BR>
     * �ʃC���X�^���X��ԋp����B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * cloneEqtypeProductConditionParams<BR>
     * <BR>
     * Return another instance that copies the content of the argument,
     * l_eqtypeProductConditionParams<BR>
     * <BR>
     * @@param l_eqtypeProductConditionParams - �i�������������ݒ�Params�j<BR>
     * <BR>
     * ������������Params<BR>
     * <BR>
     * l_eqtypeProductConditionParams<BR>
     * <BR>
     *
     * @@return �������������ݒ�Params
     * @@roseuid 418B0E3F0047
     */
    protected EqtypeProductConditionParams cloneEqtypeProductConditionParams(
        EqtypeProductConditionParams l_eqtypeProductConditionParams)
    {
        final String STR_METHOD_NAME =
            "cloneEqtypeProductConditionParams(EqtypeProductConditionParams)";
        log.entering(STR_METHOD_NAME);

        EqtypeProductConditionParams l_cloneEqtypeProductConditionParams = null;
        l_cloneEqtypeProductConditionParams = new EqtypeProductConditionParams();

        /*
         * Return another instance that copies the content of the argument,
         * l_eqtypeProductConditionParams
         */
        l_cloneEqtypeProductConditionParams.setColumnName(
            l_eqtypeProductConditionParams.getColumnName());
        l_cloneEqtypeProductConditionParams.setCreatedTimestamp(
            l_eqtypeProductConditionParams.getCreatedTimestamp());
        l_cloneEqtypeProductConditionParams.setDataNextDay(
            l_eqtypeProductConditionParams.getDataNextDay());
        l_cloneEqtypeProductConditionParams.setDataPlan(
            l_eqtypeProductConditionParams.getDataPlan());
        l_cloneEqtypeProductConditionParams.setDataToday(
            l_eqtypeProductConditionParams.getDataToday());
        l_cloneEqtypeProductConditionParams.setDataType(
            l_eqtypeProductConditionParams.getDataType());
        l_cloneEqtypeProductConditionParams.setDeleteFlg(
            l_eqtypeProductConditionParams.getDeleteFlg());
        l_cloneEqtypeProductConditionParams.setEqtypeProductConditionId(
            l_eqtypeProductConditionParams.getEqtypeProductConditionId());
        l_cloneEqtypeProductConditionParams.setInstitutionCode(
            l_eqtypeProductConditionParams.getInstitutionCode());
        l_cloneEqtypeProductConditionParams.setLargeItemDiv(
            l_eqtypeProductConditionParams.getLargeItemDiv());
        l_cloneEqtypeProductConditionParams.setLastUpdatedTimestamp(
            l_eqtypeProductConditionParams.getLastUpdatedTimestamp());
        l_cloneEqtypeProductConditionParams.setLastUpdater(
            l_eqtypeProductConditionParams.getLastUpdater());
        l_cloneEqtypeProductConditionParams.setMarketCode(
            l_eqtypeProductConditionParams.getMarketCode());
        l_cloneEqtypeProductConditionParams.setMarketId(
            l_eqtypeProductConditionParams.getMarketId());
        l_cloneEqtypeProductConditionParams.setProductCode(
            l_eqtypeProductConditionParams.getProductCode());
        l_cloneEqtypeProductConditionParams.setProductId(
            l_eqtypeProductConditionParams.getProductId());
        l_cloneEqtypeProductConditionParams.setSmallItemDiv(
            l_eqtypeProductConditionParams.getSmallItemDiv());
        l_cloneEqtypeProductConditionParams.setTableName(
            l_eqtypeProductConditionParams.getTableName());
        l_cloneEqtypeProductConditionParams.setTermFrom(
            l_eqtypeProductConditionParams.getTermFrom());
        l_cloneEqtypeProductConditionParams.setTermTo(
            l_eqtypeProductConditionParams.getTermTo());

        log.exiting(STR_METHOD_NAME);
        return l_cloneEqtypeProductConditionParams;
    }

    /**
     * �iset�����~�j�����s��X�V���j<BR>
     * <BR>
     * �����̖��������ݒ�X�V���Ɋ����~�j�����s��X�V����ݒ肷��B<BR>
     * <BR>
     * �P�j�����Ώۗ񖼂��擾����B<BR>
     * �@@�@@this.get��()���R�[������B<BR>
     * <BR>
     * �@@�@@[get��()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�����ڋ敪�F�@@�p�����[�^.���������ݒ���.�����ڋ敪<BR>
     * <BR>
     * �Q�j�����f�[�^�X�V�`�F�b�N<BR>
     * �@@�p�����[�^.���������ݒ���.�o�^�l(����) !=<BR>
     * �@@�@@�p�����[�^.���������ݒ���.�ύX��o�^�l(����)�@@����<BR>
     * �@@�@@�p�����[�^.�������(����)�ꗗ != null�̏ꍇ�A<BR>
     * �@@�ȉ��̏��������{����B<BR>
     * <BR>
     * �y�p�����[�^.���������ݒ���.�ύX��o�^�l(����) != "��戵"�̏ꍇ�z<BR>
     * �@@�Q�|�P�j�ύX�O�̃~�j���s��̎�������Ƀ~�j���戵�s��ݒ肷��B<BR>
     * <BR>
     * �@@�@@�Q�|�P�|�P�j�~�j���戵�s��ݒ肷�����������擾����B<BR>
     * �@@�@@�@@�@@�@@�@@�p�����[�^.��������ꗗ(����).get()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@[get()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@�@@�@@key�F�@@�p�����[�^.���������ݒ�ݒ���.�o�^�l(����)<BR>
     * <BR>
     * �@@�@@�Q�|�P�|�Q�j�~�j���戵�\��ݒ肷�����������擾����B<BR>
     * �@@�@@�@@�@@�@@�@@�p�����[�^.��������ꗗ(����).get()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@[get()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@�@@�@@key�F�@@�p�����[�^.���������ݒ�ݒ���.�ύX��o�^�l(����)<BR>
     * <BR>
     * �@@�@@�Q�|�P�|�R�j�Q�|�P�|�P�j�̖߂�l != null�̏ꍇ�@@����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�Q�|�P�|�Q�j�̖߂�l != null�̏ꍇ�A�~�j���戵�s��ݒ肷��B<BR>
     * �@@�@@�@@�@@�@@�@@�@@���i�Ǘ�(����)�f�[�^�}�l�[�W��.set��������o�^�l()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@[set��������o�^�l()���\�b�h�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@��������X�V���F�@@�p�����[�^.���������ݒ�X�V���.�������(����)�X�V���<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@��������F�@@�Q�|�P�|�P�j�̖߂�l<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����ڋ敪�F�@@�p�����[�^.���������ݒ���.�����ڋ敪<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�s��R�[�h�F�@@�p�����[�^.���������ݒ���.�o�^�l(����)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�X�V�񖼁F�@@�P�j�̖߂�l<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�ύX��o�^�l�F�@@"�~�j������舵���Ă��Ȃ�"<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�X�V�҃R�[�h�F�@@�p�����[�^.�X�V�҃R�[�h<BR>
     * <BR>
     * �@@�Q�|�Q�j�ύX��̃~�j���s��̎�������Ƀ~�j���戵�\��ݒ肷��B<BR>
     * �@@�@@�@@[�Q�|�P�|�Q�j�̖߂�l != null�̏ꍇ]<BR>
     * �@@�@@�@@�@@���i�Ǘ�(����)�f�[�^�}�l�[�W��.set��������o�^�l()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@[set��������o�^�l()���\�b�h�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@��������X�V���F�@@�p�����[�^.���������ݒ�X�V���.�������(����)�X�V���<BR>
     * �@@�@@�@@�@@�@@��������F�@@�Q�|�P�|�Q�j�̖߂�l<BR>
     * �@@�@@�@@�@@�@@�����ڋ敪�F�@@�p�����[�^.���������ݒ���.�����ڋ敪<BR>
     * �@@�@@�@@�@@�@@�s��R�[�h�F�@@�p�����[�^.���������ݒ���.�ύX��o�^�l(����)<BR>
     * �@@�@@�@@�@@�@@�X�V�񖼁F�@@�P�j�̖߂�l<BR>
     * �@@�@@�@@�@@�@@�ύX��o�^�l�F�@@"�~�j���戵"<BR>
     * �@@�@@�@@�@@�@@�X�V�҃R�[�h�F�@@�p�����[�^.�X�V�҃R�[�h<BR>
     * <BR>
     * �@@�@@�@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@�@@(�Ώێ���������擾�ł��Ȃ������ꍇ)<BR>
     * �@@�@@�@@�@@�p�����[�^.���������ݒ���.�ύX��o�^�l(����) =<BR>
     * �@@�@@�@@�@@�@@�p�����[�^.���������ݒ���.�o�^�l(����)�Ƃ���B<BR>
     * <BR>
     * �y�p�����[�^.���������ݒ���.�ύX��o�^�l(����) == "��戵"�̏ꍇ�z<BR>
     * �@@�Q�|�R�j�p�����[�^.��������ꗗ(����).values.toArray()�̗v�f�����A�ȉ��̏������J��Ԃ��B<BR>
     * �@@�@@�Q�|�R�|�P�j�����Ώۂ̎������.�~�j���戵 == "�~�j������舵���Ă��Ȃ�"�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@���̗v�f�֏������ڍs����B(continue;)<BR>
     * <BR>
     * �@@�@@�Q�|�R�|�Q�j���i�Ǘ�(����)�f�[�^�}�l�[�W��.set��������o�^�l()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@[set��������o�^�l()���\�b�h�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@��������X�V���F�@@�p�����[�^.���������ݒ�X�V���.�������(����)�X�V���<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@��������F�@@�����Ώۂ̎������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����ڋ敪�F�@@�p�����[�^.���������ݒ���.�����ڋ敪<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�s��R�[�h�F�@@�����Ώۂ̎������.�s��ID�ɊY������s��R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�X�V�񖼁F�@@�P�j�̖߂�l<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�ύX��o�^�l�F�@@"�~�j������舵���Ă��Ȃ�"<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�X�V�҃R�[�h�F�@@�p�����[�^.�X�V�҃R�[�h<BR>
     * <BR>
     * �R�j���X���f�[�^�X�V�`�F�b�N<BR>
     * �@@�p�����[�^.���������ݒ���.�o�^�l(���X��) !=<BR>
     * �@@�@@�p�����[�^.���������ݒ���.�ύX��o�^�l(���X��)�@@����<BR>
     * �@@�@@�p�����[�^.��������ꗗ(���X��) != null�̏ꍇ�A<BR>
     * �@@�ȉ��̏��������{����B<BR>
     * <BR>
     * �y�p�����[�^.���������ݒ���.�ύX��o�^�l(����) != "��戵"�̏ꍇ�z<BR>
     * �@@�R�|�P�j�ύX�O�̃~�j���s��̎�������Ƀ~�j���戵�s��ݒ肷��B<BR>
     * �@@�@@�R�|�P�|�P�j�~�j���戵�s��ݒ肷�����������擾����B<BR>
     * �@@�@@�@@�@@�@@�@@�p�����[�^.��������ꗗ(����).get()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@[get()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@�@@�@@key�F�@@�p�����[�^.���������ݒ�ݒ���.�o�^�l(���X��)<BR>
     * <BR>
     * �@@�@@�R�|�P�|�Q�j�~�j���戵�\��ݒ肷�����������擾����B<BR>
     * �@@�@@�@@�@@�@@�@@�p�����[�^.��������ꗗ(���X��).get()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@[get()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@�@@�@@key�F�@@�p�����[�^.���������ݒ�ݒ���.�ύX��o�^�l(����)<BR>
     * <BR>
     * �@@�@@�R�|�P�|�R�j�R�|�P�|�P�j�̖߂�l != null�̏ꍇ�@@����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�R�|�P�|�Q�j�̖߂�l != null�̏ꍇ�A�~�j���戵�s��ݒ肷��B<BR>
     * �@@�@@�@@�@@�@@�@@�@@���i�Ǘ�(����)�f�[�^�}�l�[�W��.set��������o�^�l()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@[set��������o�^�l()���\�b�h�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@��������X�V���F�@@�p�����[�^.���������ݒ�X�V���.�������(���X��)�X�V���<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@��������F�@@�R�|�P�|�P�j�̖߂�l<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����ڋ敪�F�@@�p�����[�^.���������ݒ���.�����ڋ敪<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�s��R�[�h�F�@@�p�����[�^.���������ݒ���.�o�^�l(����)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�X�V�񖼁F�@@�P�j�̖߂�l<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�ύX��o�^�l�F�@@"�~�j������舵���Ă��Ȃ�"<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�X�V�҃R�[�h�F�@@�p�����[�^.�X�V�҃R�[�h<BR>
     * <BR>
     * �@@�R�|�Q�j�ύX��̃~�j���s��̎�������Ƀ~�j���戵�\��ݒ肷��B<BR>
     * �@@�@@�@@[�R�|�P�|�Q�j�̖߂�l != null�̏ꍇ]<BR>
     * �@@�@@�@@�@@���i�Ǘ�(����)�f�[�^�}�l�[�W��.set��������o�^�l()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@[set��������o�^�l()���\�b�h�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@��������X�V���F�@@�p�����[�^.���������ݒ�X�V���.�������(���X��)�X�V���<BR>
     * �@@�@@�@@�@@�@@��������F�@@�R�|�P�|�Q�j�̖߂�l<BR>
     * �@@�@@�@@�@@�@@�����ڋ敪�F�@@�p�����[�^.���������ݒ���.�����ڋ敪<BR>
     * �@@�@@�@@�@@�@@�s��R�[�h�F�@@�p�����[�^.���������ݒ���.�ύX��o�^�l(����)<BR>
     * �@@�@@�@@�@@�@@�X�V�񖼁F�@@�P�j�̖߂�l<BR>
     * �@@�@@�@@�@@�@@�ύX��o�^�l�F�@@"�~�j���戵"<BR>
     * �@@�@@�@@�@@�@@�X�V�҃R�[�h�F�@@�p�����[�^.�X�V�҃R�[�h<BR>
     * <BR>
     * �@@�@@�@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@�@@(�Ώێ���������擾�ł��Ȃ������ꍇ)<BR>
     * �@@�@@�@@�@@�p�����[�^.���������ݒ���.�ύX��o�^�l(����) =<BR>
     * �@@�@@�@@�@@�@@�p�����[�^.���������ݒ���.�o�^�l(����)�Ƃ���B<BR>
     * <BR>
     * �y�p�����[�^.���������ݒ���.�ύX��o�^�l(����) == "��戵"�̏ꍇ�z<BR>
     * �@@�Q�|�R�j�p�����[�^.��������ꗗ(���X��).values.toArray()�̗v�f�����A�ȉ��̏������J��Ԃ��B<BR>
     * �@@�@@�Q�|�R�|�P�j�����Ώۂ̎������.�~�j���戵 == "�~�j������舵���Ă��Ȃ�"�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@���̗v�f�֏������ڍs����B(continue;)<BR>
     * <BR>
     * �@@�@@�Q�|�R�|�Q�j���i�Ǘ�(����)�f�[�^�}�l�[�W��.set��������o�^�l()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@[set��������o�^�l()���\�b�h�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@��������X�V���F�@@�p�����[�^.���������ݒ�X�V���.�������(���X��)�X�V���<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@��������F�@@�����Ώۂ̎������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����ڋ敪�F�@@�p�����[�^.���������ݒ���.�����ڋ敪<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�s��R�[�h�F�@@�����Ώۂ̎������.�s��ID�ɊY������s��R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�X�V�񖼁F�@@�P�j�̖߂�l<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�ύX��o�^�l�F�@@"�~�j������舵���Ă��Ȃ�"<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�X�V�҃R�[�h�F�@@�p�����[�^.�X�V�҃R�[�h<BR>
     * <BR>
     * (*1)���ݎ����E�E�E<BR>
     * �@@ThreadLocalSystemAttributesRegistry.getAttribute(������ԊǗ�.TIMESTAMP_TAG)<BR>
     * �ɂĎ擾�������ݎ���<BR>
     * <BR>
     * @@param l_productConditionUpdateInfo - �i���������ݒ�X�V���j<BR>
     * <BR>
     * ���������ݒ�X�V���I�u�W�F�N�g<BR>
     * <BR>
     * l_productConditionUpdateInfo<BR>
     * <BR>
     * @@param l_productConditionInfo - �i���������ݒ���j<BR>
     * <BR>
     * ���������ݒ���<BR>
     * <BR>
     * l_productConditionInfo<BR>
     * <BR>
     * @@param l_tradedProductsNextDay - �i��������ꗗ(����)�j<BR>
     * <BR>
     * ��������ꗗ(����)<BR>
     * <BR>
     * l_tradedProductsNextDay<BR>
     * <BR>
     * @@param l_tradedProductsTwoDaysLater - �i��������ꗗ(���X��)�j<BR>
     * <BR>
     * ��������ꗗ(���X��)<BR>
     * <BR>
     * l_tradedProductsTwoDaysLater<BR>
     * <BR>
     *
     * @@param l_lastUpdater - �i�X�V�҃R�[�h�j<BR>
     * <BR>
     * �X�V�҃R�[�h<BR>
     * <BR>
     * l_lastUpdater<BR>
     * <BR>
     * @@roseuid 418ED1AA013A
     */
    protected void setMiniStockMarketUpdateInfo(
        WEB3AdminPMProductCondSettingUpdateInfo l_productConditionUpdateInfo,
        WEB3AdminPMProductCondConfigUnit l_productConditionInfo,
        HashMap l_tradedProductsNextDay,
        HashMap l_tradedProductsTwoDaysLater,
        String l_lastUpdater) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "setMiniStockMarketUpdateInfo(WEB3AdminPMProductCondSettingUpdateInfo, "
                + "WEB3AdminPMProductCondConfigUnit, HashMap, HashMap, String)";
        log.entering(STR_METHOD_NAME);

        String l_strSmallItemDiv = l_productConditionInfo.smallItemDiv;
        String l_strColumn = null;
        String l_strBizDateRegistData = null;
        String l_strAftBizDateRegistData = null;
        String l_strMarketCode = null;
        String l_strNextBizDateRegistData = null;
        String l_strAftNextBizDateRegistData = null;

        WEB3AdminPMEquityDataManager l_adminPMEquityDataManager =
            new WEB3AdminPMEquityDataManager();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        EqtypeTradedProductParams l_tradedProductMiniStockBizDate = null;
        EqtypeTradedProductParams l_tradedProductMiniStockAftBizDate = null;

        // 1)
        l_strColumn = this.getColumnName(l_strSmallItemDiv);

        /* 2) Checks biz date with after biz date and
         * tradedproductsNextDay Hashmap is not null
         */
        l_strBizDateRegistData = l_productConditionInfo.bizDateRegistData;
        l_strAftBizDateRegistData = l_productConditionInfo.aftBizDateRegistData;

        if (isChange(l_strBizDateRegistData, l_strAftBizDateRegistData)
            && l_tradedProductsNextDay != null)
        {
            // �p�����[�^.���������ݒ���.�ύX��o�^�l(����) != "��戵"�̏ꍇ
            if (!String.valueOf(BooleanEnum.FALSE.intValue()).equals(l_productConditionInfo.aftBizDateRegistData))
            {
	            /* 2-1) Set 'impossible mini stock dealing' into tradedProduct of mini stock
	              * market before changing
	              */
	            // 2-1-1)
	            l_tradedProductMiniStockBizDate =
	                (EqtypeTradedProductParams) l_tradedProductsNextDay.get(l_strBizDateRegistData);
	
	            // 2-1-2)
	            l_tradedProductMiniStockAftBizDate =
	                (EqtypeTradedProductParams) l_tradedProductsNextDay.get(l_strAftBizDateRegistData);
	
	            /* 2-1-3
	             * If return values of 2-1-1) != null and<BR>
	             * the return value of 2-1-2) != null,  set "FALSE"
	             */
	            if (l_tradedProductMiniStockBizDate != null
	                && l_tradedProductMiniStockAftBizDate != null)
	            {
	                l_strMarketCode = l_productConditionInfo.bizDateRegistData;
	                l_adminPMEquityDataManager.setTradedProductRegistrationValue(
	                    l_productConditionUpdateInfo.tradeProductNextUpdateInfo,
	                    l_tradedProductMiniStockBizDate,
	                    l_strSmallItemDiv,
	                    l_strMarketCode,
	                    l_strColumn,
	                    String.valueOf(BooleanEnum.FALSE.intValue()),
	                    l_lastUpdater);
	
	            }
	            /*
	             * 2-2)
	             */
	            if (l_tradedProductMiniStockAftBizDate != null)
	            {
	                l_strMarketCode = l_productConditionInfo.aftBizDateRegistData;
	                l_adminPMEquityDataManager.setTradedProductRegistrationValue(
	                    l_productConditionUpdateInfo.tradeProductNextUpdateInfo,
	                    l_tradedProductMiniStockAftBizDate,
	                    l_strSmallItemDiv,
	                    l_strMarketCode,
	                    l_strColumn,
	                    String.valueOf(BooleanEnum.TRUE.intValue()),
	                    l_lastUpdater);
	            }
            }
            else
            {
                EqtypeTradedProductParams[] l_eqtypeTradedProductParamsList =
                    (EqtypeTradedProductParams[])l_tradedProductsNextDay.values().toArray(new EqtypeTradedProductParams[0]);
                
                for (int i = 0; i < l_eqtypeTradedProductParamsList.length; i++)
                {
                    // �����Ώۂ̎������.�~�j���戵 == "�~�j������舵���Ă��Ȃ�"�̏ꍇ
                    if (BooleanEnum.FALSE.equals(l_eqtypeTradedProductParamsList[i].getMiniStockCanDealt()))
                    {
                        continue;
                    }
                    else
                    {
                        try
                        {
	                        l_strMarketCode =
	                            l_finObjectManager.getMarket(l_eqtypeTradedProductParamsList[i].getMarketId()).getMarketCode();
                        } catch (NotFoundException l_ex)
                        {
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                                this.getClass().getName() + "." + STR_METHOD_NAME,
                                "�s��ID:[" + l_eqtypeTradedProductParamsList[i].getMarketId() + "]�ɊY������s�ꂪ�擾�ł��܂���B",
                                l_ex);
                        }
                        
                        // �����Ώۂ̎��������"�~�j������舵���Ă��Ȃ�"��ݒ肷��B
                        l_adminPMEquityDataManager.setTradedProductRegistrationValue(
                            l_productConditionUpdateInfo.tradeProductNextUpdateInfo,
                            l_eqtypeTradedProductParamsList[i],
                            l_productConditionInfo.smallItemDiv,
                            l_strMarketCode,
                            l_strColumn,
                            String.valueOf(BooleanEnum.FALSE.intValue()),
                            l_lastUpdater);
                    }
                }
            }
        } else
        {
            l_productConditionInfo.aftBizDateRegistData = l_productConditionInfo.bizDateRegistData;
        }

        // 3)
        l_strNextBizDateRegistData = l_productConditionInfo.nextBizDateRegistData;
        l_strAftNextBizDateRegistData = l_productConditionInfo.aftNextBizDateRegistData;

        if (isChange(l_strNextBizDateRegistData, l_strAftNextBizDateRegistData)
            && l_tradedProductsTwoDaysLater != null)
        {
            // �p�����[�^.���������ݒ���.�ύX��o�^�l(����) != "��戵"�̏ꍇ
            if (!String.valueOf(BooleanEnum.FALSE.intValue()).equals(l_productConditionInfo.aftNextBizDateRegistData))
            {
	            /*3-1)Set 'impossible mini stock dealing' into tradedProduct of mini stock
	              * market before changing
	              */
	            // 3-1-1)
	            l_tradedProductMiniStockBizDate =
	                (EqtypeTradedProductParams) l_tradedProductsTwoDaysLater.get(
	                    l_strNextBizDateRegistData);
	
	            // 3-1-2)
	            l_tradedProductMiniStockAftBizDate =
	                (EqtypeTradedProductParams) l_tradedProductsTwoDaysLater.get(
	                    l_strAftNextBizDateRegistData);
	
	            /* 3-1-3
	             * If return values of 3-1-1) != null and<BR>
	             * the return value of 3-1-2) != null,  set "FALSE"
	             */
	            if (l_tradedProductMiniStockBizDate != null
	                && l_tradedProductMiniStockAftBizDate != null)
	            {
	                l_strMarketCode = l_productConditionInfo.nextBizDateRegistData;
	                // Set false in java doc it is specified only 6 arguments but the
	                // method takes 7 argument Please clarify
	                l_adminPMEquityDataManager.setTradedProductRegistrationValue(
	                    l_productConditionUpdateInfo.tradeProductNext2UpdateInfo,
	                    l_tradedProductMiniStockBizDate,
	                    l_strSmallItemDiv,
	                    l_strMarketCode,
	                    l_strColumn,
                        String.valueOf(BooleanEnum.FALSE.intValue()),
	                    l_lastUpdater);
	            }
	
	            /*
	             * 3-2)
	             */
	            if (l_tradedProductMiniStockAftBizDate != null)
	            {
	                l_strMarketCode = l_productConditionInfo.aftNextBizDateRegistData;
	                l_adminPMEquityDataManager.setTradedProductRegistrationValue(
	                    l_productConditionUpdateInfo.tradeProductNext2UpdateInfo,
	                    l_tradedProductMiniStockAftBizDate,
	                    l_strSmallItemDiv,
	                    l_strMarketCode,
	                    l_strColumn,
                        String.valueOf(BooleanEnum.TRUE.intValue()),
	                    l_lastUpdater);
	            }
            }
            else
            {
                EqtypeTradedProductParams[] l_eqtypeTradedProductParamsList =
                    (EqtypeTradedProductParams[])l_tradedProductsTwoDaysLater.values().toArray(new EqtypeTradedProductParams[0]);
                
                for (int i = 0; i < l_eqtypeTradedProductParamsList.length; i++)
                {
                    // �����Ώۂ̎������.�~�j���戵 == "�~�j������舵���Ă��Ȃ�"�̏ꍇ
                    if (BooleanEnum.FALSE.equals(l_eqtypeTradedProductParamsList[i].getMiniStockCanDealt()))
                    {
                        continue;
                    }
                    else
                    {
                        try
                        {
                            l_strMarketCode =
                                l_finObjectManager.getMarket(l_eqtypeTradedProductParamsList[i].getMarketId()).getMarketCode();
                        } catch (NotFoundException l_ex)
                        {
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                                this.getClass().getName() + "." + STR_METHOD_NAME,
                                "�s��ID:[" + l_eqtypeTradedProductParamsList[i].getMarketId() + "]�ɊY������s�ꂪ�擾�ł��܂���B",
                                l_ex);
                        }
                        
                        // �����Ώۂ̎��������"�~�j������舵���Ă��Ȃ�"��ݒ肷��B
                        l_adminPMEquityDataManager.setTradedProductRegistrationValue(
                            l_productConditionUpdateInfo.tradeProductNext2UpdateInfo,
                            l_eqtypeTradedProductParamsList[i],
                            l_productConditionInfo.smallItemDiv,
                            l_strMarketCode,
                            l_strColumn,
                            String.valueOf(BooleanEnum.FALSE.intValue()),
                            l_lastUpdater);
                    }
                }
            }
        } else
        {
            l_productConditionInfo.aftNextBizDateRegistData =
                l_productConditionInfo.nextBizDateRegistData;
        }

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * set�����X�V���<BR>
     * <BR>
     * set�����X�V���̃I�[�o�[���[�h<BR>
     * <BR>
     * �����̖��������ݒ�X�V���ɖ����X�V���(�S�s�ꕪ)��ݒ肷��B<BR>
     * ��1���ڂɐݒ肳�ꂽ�o�^�l�����s��̎�������S�Ăɔ��f������B<BR>
     * <BR>
     * �P�j�����Ώۗ񖼂��擾����B<BR>
     * �@@�@@this.get��()���R�[������B<BR>
     * <BR>
     * �@@�@@[get��()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�����ڋ敪�F�@@�p�����[�^.���������ݒ���.�����ڋ敪<BR>
     * <BR>
     * �Q�j�����f�[�^�X�V�`�F�b�N<BR>
     * �@@�p�����[�^.���������ݒ���.�o�^�l(����) !=<BR>
     * �@@�@@�p�����[�^.���������ݒ���.�ύX��o�^�l(����) ����<BR>
     * �@@�@@�p�����[�^.��������ꗗ(����) != null�̏ꍇ�A<BR>
     * �@@�ȉ��̏��������{����B<BR>
     * <BR>
     * �@@�@@�Q�|�P�j�p�����[�^.��������ꗗ(����).values.toArray()���\�b�h���R�[�����A<B
     * R>
     * �@@�@@�@@�@@�@@�@@��������̔z����擾����B<BR>
     * �@@�@@�Q�|�Q�j�Q�|�P�j�̖߂�l�̗v�f(=�������)�����A�ȉ��̏������J��Ԃ��B<BR>
     * �@@�@@�@@�Q�|�Q�|�P�j���i�Ǘ�(����)�f�[�^�}�l�[�W��.set��������o�^�l()���\�b�h���R
     * �[������B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@[set��������o�^�l()���\�b�h�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@��������X�V���F�@@�p�����[�^.���������ݒ�X�V���.��������X�V
     * ���<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@��������F�@@�����Ώۂ̎������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����ڋ敪�F�@@�p�����[�^.���������ݒ���.�����ڋ敪<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�s��R�[�h�F�@@�����Ώۂ̎������.�s��R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�X�V�񖼁F�@@�P�j�̖߂�l<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�ύX��o�^�l�F�@@�p�����[�^.���������ݒ���.�ύX��o�^�l(����)<B
     * R>
     * �@@�@@�@@�@@�@@�@@�@@�@@�X�V�҃R�[�h�F�@@�p�����[�^.�X�V�҃R�[�h<BR>
     * <BR>
     * �R�j�����f�[�^�X�V�`�F�b�N<BR>
     * �@@�p�����[�^.���������ݒ���.�o�^�l(����) !=<BR>
     * �@@�@@�p�����[�^.���������ݒ���.�ύX��o�^�l(����)�@@����<BR>
     * �@@�@@�p�����[�^.�������(����) != null�̏ꍇ�A<BR>
     * �@@�ȉ��̏��������{����B<BR>
     * <BR>
     * �@@�@@�R�|�P�j�p�����[�^.��������ꗗ(����).values.toArray()���\�b�h���R�[�����A<B
     * R>
     * �@@�@@�@@�@@�@@�@@��������̔z����擾����B<BR>
     * �@@�@@�R�|�Q�j�R�|�P�j�̖߂�l�̗v�f(=�������)�����A�ȉ��̏������J��Ԃ��B<BR>
     * �@@�@@�@@�R�|�Q�|�P�j���i�Ǘ�(����)�f�[�^�}�l�[�W��.set��������o�^�l()���\�b�h���R
     * �[������B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@[set��������o�^�l()���\�b�h�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@��������X�V���F�@@�p�����[�^.���������ݒ�X�V���.�������(
     * ����)�X�V���<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@��������F�@@�����Ώۂ̎������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�����ڋ敪�F�@@�p�����[�^.���������ݒ���.�����ڋ敪<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�s��R�[�h�F�@@�����Ώۂ̎������.�s��R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�X�V�񖼁F�@@�P�j�̖߂�l<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�ύX��o�^�l�F�@@�p�����[�^.���������ݒ���.�ύX��o�^�l(����)
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�X�V�҃R�[�h�F�@@�p�����[�^.�X�V�҃R�[�h<BR>
     * <BR>
     * (*1)���ݎ����E�E�E<BR>
     * �@@ThreadLocalSystemAttributesRegistry.getAttribute(������ԊǗ�.TIMESTAMP_TAG)<B
     * R>
     * �ɂĎ擾�������ݎ���<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * setProductUpdateInfo<BR>
     * <BR>
     * Set productUpdateInfo into l_productConditionUpdateInfo<BR>
     * ��Reflect registData set in 1 item to all tradedProduct in listed markets<BR>
     * <BR>
     * 1)Acquire Row to be processed<BR>
     * �@@�@@Call this.getRow()<BR>
     * <BR>
     * �@@�@@[parameter set into getRow()]<BR>
     * �@@�@@�@@smallItemDiv: parameterl_productConditionInfo.smallItemDiv<BR>
     * <BR>
     * 2)If parameter.l_productConditionInfo.bizDateRegistData !=<BR>
     * �@@�@@parameter.l_productConditionInfo.aftBizDateRegistData and<BR>
     * �@@�@@parameter.tradedProductsToday != null,<BR>
     * �@@Execute the following process<BR>
     * <BR>
     * �@@�@@2-1)Call parameter.l_tradedProductsToday.values.toArray() method, and<BR>
     * �@@�@@�@@�@@�@@�@@acquire an array of tradedProduct<BR>
     * �@@�@@2-2)Loop for as many times as elements(=tradedProduct) of return values of
     * 2-1)<BR>
     * �@@�@@�@@2-2-1)Call
     * WEB3AdminPMEquityDataManager.setTradedProductRegistrationValue() method<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@[parameter set into setTradedProductRegistrationValue()
     * method]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@l_tradeProductUpdateInfo:
     * parameter.l_productCondSettingUpdateInfo.tradeProductUpdateInfo<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@l_tradedProduct: tradedProduct to be processed<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@l_strSmallItemDiv:
     * parameter.l_productConditionInfo.smallItemDiv<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@l_strMarketCode: tradedProduct to be processed.marketCode<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@l_updateRow: return value of 1)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@l_aftBizDateRegistData:
     * parameter.l_productConditionInfo.aftBizDateRegistData<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@l_lastUpdater: parameter.LastUpdater<BR>
     * <BR>
     * 3)If parameter.l_productConditionInfo.nextBizDateRegistData !=<BR>
     * �@@�@@parameter.l_productConditionInfo.aftNextBizDateRegistData and<BR>
     * �@@�@@parameter.tradedProductNextDay != null<BR>
     * �@@Execute the following process<BR>
     * <BR>
     * �@@�@@3-1)Call parameter.tradedProductsNextDay.values.toArray()method, and<BR>
     * �@@�@@�@@�@@�@@�@@acuire an array of tradedProduct<BR>
     * �@@�@@3-2)Loop for as many times as elements(=tradedProduct) of return values of
     * 3-1)<BR>
     * �@@�@@�@@3-2-1)Call
     * WEB3AdminPMEquityDataManager.setTradedProductRegistrationValue() method<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@[parameter set into setTradedProductRegistrationValue()
     * method]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@l_tradeProductUpdateInfo:
     * parameter.l_productCondSettingUpdateInfo.tradeProductNextUpdateInfo<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@l_tradedProduct: tradedProduct to be processed<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@l_strSmallItemDiv:
     * parameter.l_productConditionInfo.smallItemDiv<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@l_strMarketCode: tradedProduct to be processed.marketCode<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@l_updateRow: return value of 1)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@  l_aftBizDateRegistData:
     * parameter.l_productConditionInfo.aftNextBizDateRegistData<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@l_lastUpdater: parameter.lastUpdater<BR>
     * <BR>
     * (*1)timeStamp�E�E�E<BR>
     * �@@timeStamp acquired at
     * ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManageme
     * nt.TIMESTAMP_TAG)<BR>
     * <BR>
     * @@param l_productConditionUpdateInfo - �i���������ݒ�X�V���j<BR>
     * <BR>
     * ���������ݒ�X�V���I�u�W�F�N�g<BR>
     * <BR>
     * l_productConditionUpdateInfo<BR>
     * <BR>
     * @@param l_productConditionInfo - �i���������ݒ���j<BR>
     * <BR>
     * ���������ݒ���<BR>
     * <BR>
     * l_productConditionInfo<BR>
     * <BR>
     * @@param l_tradedProductsToday - �i��������ꗗ(����)�j<BR>
     * <BR>
     * ��������ꗗ(����)<BR>
     * <BR>
     * l_tradedProductsToday<BR>
     * <BR>
     *
     * @@param l_tradedProductsNextDay - �i��������ꗗ(����)�j<BR>
     * <BR>
     * ��������ꗗ(����)<BR>
     * <BR>
     * l_tradedProductsNextDay<BR>
     * <BR>
     *
     * @@param l_lastUpdater - �i�X�V�҃R�[�h�j<BR>
     * <BR>
     * �X�V�҃R�[�h<BR>
     * <BR>
     * l_lastUpdater<BR>
     * <BR>
     * @@throws NotFoundException NotFoundException
     * @@roseuid 4192E45103C1
     */
    protected void setProductUpdateInfo(
        WEB3AdminPMProductCondSettingUpdateInfo l_productConditionUpdateInfo,
        WEB3AdminPMProductCondConfigUnit l_productConditionInfo,
        HashMap l_tradedProductsToday,
        HashMap l_tradedProductsNextDay,
        String l_lastUpdater) throws NotFoundException
    {
        final String STR_METHOD_NAME =
            "setProductUpdateInfo(ProductCondSettingUpdateInfo, WEB3AdminPMProductCondConfigUnit"
                + ", WEB3EquityTradedProduct, WEB3EquityTradedProduct, String)";
        log.entering(STR_METHOD_NAME);

        Market l_market = null;
        String l_strBizDateRegistData = l_productConditionInfo.bizDateRegistData;
        String l_strNextBizDateRegistData = l_productConditionInfo.nextBizDateRegistData;
        String l_strAftBizDateRegistData = l_productConditionInfo.aftBizDateRegistData;
        String l_strAftNextBizDateRegistData = l_productConditionInfo.aftNextBizDateRegistData;
        String l_strMarketCode = null;
        List l_lisParams = null;
        int l_arrTradedProductsTodayCnt = 0;
        int l_arrTradedProductsNextDayCnt = 0;
        long l_lngMarketId = 0L;
        WEB3AdminPMEquityDataManager l_adminPMEquityDataManager = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        l_adminPMEquityDataManager = new WEB3AdminPMEquityDataManager();

        // Step 1
        String l_updateRpw = this.getColumnName(l_productConditionInfo.smallItemDiv);

        /*
         * Step 2
         * If parameter.l_productConditionInfo.bizDateRegistData !=
         * �@@�@@parameter.l_productConditionInfo.aftBizDateRegistData,
         * and parameter.l_tradedProductsToday.
         */
        if (isChange(l_strBizDateRegistData, l_strAftBizDateRegistData)
            && l_tradedProductsToday != null)
        {
            // 2-1
            l_lisParams = new ArrayList(l_tradedProductsToday.values());
            EqtypeTradedProductParams[] l_tradedProductsTodayList =
                new EqtypeTradedProductParams[l_lisParams.size()];
            l_tradedProductsTodayList =
                (EqtypeTradedProductParams[]) l_lisParams.toArray(l_tradedProductsTodayList);
            l_arrTradedProductsTodayCnt = l_tradedProductsTodayList.length;

            // 2-2 Loop for as many times as elements.
            for (int i = 0; i < l_arrTradedProductsTodayCnt; i++)
            {
                l_lngMarketId = l_tradedProductsTodayList[i].getMarketId();
                l_market = l_finApp.getFinObjectManager().getMarket(l_lngMarketId);
                l_strMarketCode = l_market.getMarketCode();
                l_adminPMEquityDataManager.setTradedProductRegistrationValue(
                    l_productConditionUpdateInfo.tradeProductUpdateInfo,
                    l_tradedProductsTodayList[i],
                    l_productConditionInfo.smallItemDiv,
                    l_strMarketCode,
                    l_updateRpw,
                    l_productConditionInfo.aftBizDateRegistData,
                    l_lastUpdater);
            }
        }

        /*
         * Step 3
         * If parameter.l_productConditionInfo.nextBizDateRegistData !=
         * �@@�@@parameter.l_productConditionInfo.aftNextBizDateRegistData,
         * and parameter.l_tradedProductsNextDay != null
         */
        if (isChange(l_strNextBizDateRegistData, l_strAftNextBizDateRegistData)
            && l_tradedProductsNextDay != null)
        {
            // 3-1
            l_lisParams = new ArrayList(l_tradedProductsNextDay.values());
            EqtypeTradedProductParams[] l_tradedProductsNextDayList =
                new EqtypeTradedProductParams[l_lisParams.size()];
            l_tradedProductsNextDayList =
                (EqtypeTradedProductParams[]) l_lisParams.toArray(l_tradedProductsNextDayList);
            l_arrTradedProductsNextDayCnt = l_tradedProductsNextDayList.length;

            // 3-2 Loop for as many times as elements.
            for (int i = 0; i < l_arrTradedProductsNextDayCnt; i++)
            {
                l_lngMarketId = l_tradedProductsNextDayList[i].getMarketId();
                l_market = l_finApp.getFinObjectManager().getMarket(l_lngMarketId);
                l_strMarketCode = l_market.getMarketCode();
                l_adminPMEquityDataManager.setTradedProductRegistrationValue(
                    l_productConditionUpdateInfo.tradeProductNextUpdateInfo,
                    l_tradedProductsNextDayList[i],
                    l_productConditionInfo.smallItemDiv,
                    l_strMarketCode,
                    l_updateRpw,
                    l_productConditionInfo.aftNextBizDateRegistData,
                    l_lastUpdater);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �iset�~�j���X�V���j<BR>
     * <BR>
     * �����̖��������ݒ�X�V���Ƀ~�j���X�V����ݒ肷��B<BR>
     * <BR>
     * �P�j�����Ώۗ񖼂��擾����B<BR>
     * �@@�@@this.get��()���R�[������B<BR>
     * <BR>
     * �@@�@@[get��()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�����ڋ敪�F�@@�p�����[�^.���������ݒ���.�����ڋ敪<BR>
     * <BR>
     * �Q�j�����f�[�^�X�V�`�F�b�N<BR>
     * �@@�p�����[�^.���������ݒ���.�o�^�l(����) !=<BR>
     * �@@�@@�p�����[�^.���������ݒ���.�ύX��o�^�l(����)�̏ꍇ�@@����<BR>
     * �@@�p�����[�^.�������(����)�̏ꍇ�A<BR>
     * �@@�ȉ��̏��������{����B<BR>
     * <BR>
     * �@@�@@�Q�|�P�j���i�Ǘ�(����)�f�[�^�}�l�[�W��.set��������o�^�l()���\�b�h���R�[����
     * ��B<BR>
     * <BR>
     * �@@�@@�@@�@@[set��������o�^�l()���\�b�h�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@��������X�V���F�@@�p�����[�^.���������ݒ�X�V���.�������(����)�X�V
     * ���<BR>
     * �@@�@@�@@�@@�@@��������F�@@�p�����[�^.�������(����)<BR>
     * �@@�@@�@@�@@�@@�����ڋ敪�F�@@�p�����[�^.���������ݒ���.�����ڋ敪<BR>
     * �@@�@@�@@�@@�@@�s��R�[�h�F�@@�p�����[�^.���������ݒ���.�s��R�[�h<BR>
     * �@@�@@�@@�@@�@@�X�V�񖼁F�@@�P�j�̖߂�l<BR>
     * �@@�@@�@@�@@�@@�ύX��o�^�l�F�@@�p�����[�^.���������ݒ���.�ύX��o�^�l(����)<BR>
     * �@@�@@�@@�@@�@@�X�V�҃R�[�h�F�@@�p�����[�^.�X�V�҃R�[�h<BR>
     * <BR>
     * �R�j�����f�[�^�X�V�`�F�b�N<BR>
     * �@@�p�����[�^.���������ݒ���.�o�^�l(����) !=<BR>
     * �@@�@@�p�����[�^.���������ݒ���.�ύX��o�^�l(����)�@@����<BR>
     * �@@�@@�p�����[�^.�������(���X��) != null�̏ꍇ�A<BR>
     * �@@�ȉ��̏��������{����B<BR>
     * <BR>
     * �@@�R�|�P�j���i�Ǘ�(����)�f�[�^�}�l�[�W��.set��������o�^�l()���\�b�h���R�[������
     * �B<BR>
     * <BR>
     * �@@�@@�@@[set��������o�^�l()���\�b�h�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@��������X�V���F�@@�p�����[�^.���������ݒ�X�V���.�������(���X��)�X�V
     * ���<BR>
     * �@@�@@�@@�@@��������F�@@�p�����[�^.�������(���X��)<BR>
     * �@@�@@�@@�@@�����ڋ敪�F�@@�p�����[�^.���������ݒ���.�����ڋ敪<BR>
     * �@@�@@�@@�@@�s��R�[�h�F�@@�p�����[�^.���������ݒ���.�s��R�[�h<BR>
     * �@@�@@�@@�@@�X�V�񖼁F�@@�P�j�̖߂�l<BR>
     * �@@�@@�@@�@@�ύX��o�^�l�F�@@�p�����[�^.���������ݒ���.�ύX��o�^�l(����)<BR>
     * �@@�@@�@@�@@�X�V�҃R�[�h�F�@@�p�����[�^.�X�V�҃R�[�h<BR>
     * <BR>
     * (*1)���ݎ����E�E�E<BR>
     * �@@ThreadLocalSystemAttributesRegistry.getAttribute(������ԊǗ�.TIMESTAMP_TAG)<B
     * R>
     * �ɂĎ擾�������ݎ���<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * setMiniStockUpdateInfo<BR>
     * <BR>
     * Set miniStockUpdateInfo to the argument, l_productConditionUpdateInfo<BR>
     * <BR>
     * 1)Acquire row name to be processed<BR>
     * �@@�@@Call this.getRow()<BR>
     * <BR>
     * �@@�@@[parameter set into getRow()]<BR>
     * �@@�@@�@@l_strSmallItemDiv: parameter.l_productConditionInfo.smallItemDiv<BR>
     * <BR>
     * 2)If parameter.l_productConditionInfo.bizDateRegistData !=<BR>
     * �@@�@@parameter.l_productConditionInfo.aftBizDateRegistData, and<BR>
     * �@@�@@parameter.tradedProductsNextDay<BR>
     * �@@Execute the following process<BR>
     * <BR>
     * �@@�@@2-1)Call WEB3AdminPMEquityDataManager.setTradedProductRegistrationValue()
     * method<BR>
     * <BR>
     * �@@�@@�@@�@@[parameter set into setTradedProductRegistrationValue() method]<BR>
     * �@@�@@�@@�@@�@@l_tradeProductUpdateInfo:�@@parameter.l_productCondSettingUpdateInfo.tr
     * adeProductNextUpdateInfo<BR>
     * �@@�@@�@@�@@�@@l_tradedProduct:�@@parameter.l_tradedProductsNextDay<BR>
     * �@@�@@�@@�@@�@@l_strSmallItemDiv:�@@parameter.l_productConditionInfo.smallItemDiv<BR>
     * �@@�@@�@@�@@�@@l_strMarketCode: �@@parameter.l_productConditionInfo.marketCode<BR>
     * �@@�@@�@@�@@�@@l_updateRpw: return value of 1)<BR>
     * �@@�@@�@@�@@�@@l_aftBizDateRegistDate:�@@parameter.l_productConditionInfo.aftBizDateRe
     * gistData<BR>
     * �@@�@@�@@�@@�@@l_lastUpdater: parameter.lastUpdater<BR>
     * <BR>
     * 3)If parameter.l_productConditionInfo.bizDateRegistData !=<BR>
     * �@@�@@parameter.l_productConditionInfo.aftNextBizDateRegistData, and<BR>
     * �@@�@@parameter.l_tradedProductTwoDaysLater != null<BR>
     * �@@execute the following process<BR>
     * <BR>
     * �@@3-1)Call WEB3AdminPMEquityDataManager.setTradedProductRegistrationValue()
     * method<BR>
     * <BR>
     * �@@�@@�@@[parameter set into setTradedProductRegistrationValue() method]<BR>
     * �@@�@@�@@�@@l_tradeProductUpdateInfo:�@@parameter.l_productConditionInfo.tradedProduc
     * tsTwoDaysLater<BR>
     * �@@�@@�@@�@@l_tradedProduct:�@@parameter.tradedProductTwoDaysLater<BR>
     * �@@�@@�@@�@@l_strSmallItemDiv:�@@parameter.l_productConditionInfo.smallItemDiv<BR>
     * �@@�@@�@@�@@l_strMarketCode:�@@parameter.l_productConditionInfo.marketCode<BR>
     * �@@�@@�@@�@@l_updateRpw: return value of 1)<BR>
     * �@@�@@�@@�@@l_aftBizDateRegistDate:�@@parameter.l_productConditionInfo.aftNextBizDate
     * RegistData<BR>
     * �@@�@@�@@�@@l_lastUpdater: parameter.lastUpdater<BR>
     * <BR>
     * (*1)timeStamp�E�E�E<BR>
     * �@@timeStamp acquired at
     * ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManageme
     * nt.TIMESTAMP_TAG)<BR>
     * <BR>
     * @@param l_productConditionUpdateInfo - �i���������ݒ�X�V���j<BR>
     * <BR>
     * ���������ݒ�X�V���I�u�W�F�N�g<BR>
     * <BR>
     * l_productConditionUpdateInfo<BR>
     * <BR>
     *
     * @@param l_productConditionInfo - �i���������ݒ���j<BR>
     * <BR>
     * ���������ݒ���<BR>
     * <BR>
     * l_productConditionInfo<BR>
     * <BR>
     * @@param l_tradedProductsNextDay - �i��������ꗗ(����)�j<BR>
     * <BR>
     * ��������ꗗ(����)<BR>
     * <BR>
     * l_tradedProductsNextDay<BR>
     * <BR>
     *
     * @@param l_tradedProductsTwoDaysLater - �i��������ꗗ(���X��)�j<BR>
     * <BR>
     * ��������ꗗ(���X��)<BR>
     * <BR>
     * l_tradedProductsTwoDaysLater<BR>
     * <BR>
     *
     * @@param l_lastUpdater - �i�X�V�҃R�[�h�j<BR>
     * <BR>
     * �X�V�҃R�[�h<BR>
     * <BR>
     * l_lastUpdater<BR>
     * <BR>
     * @@roseuid 419367E0029A
     */
    protected void setMiniStockUpdateInfo(
        WEB3AdminPMProductCondSettingUpdateInfo l_productConditionUpdateInfo,
        WEB3AdminPMProductCondConfigUnit l_productConditionInfo,
        EqtypeTradedProductParams l_tradedProductsNextDay,
        EqtypeTradedProductParams l_tradedProductsTwoDaysLater,
        String l_lastUpdater)
    {
        final String STR_METHOD_NAME =
            "setMiniStockUpdateInfo(ProductCondSettingUpdateInfo, WEB3AdminPMProductCondConfigUnit"
                + ", WEB3EquityTradedProduct, WEB3EquityTradedProduct, String)";
        log.entering(STR_METHOD_NAME);

        String l_strBizDateRegistData = l_productConditionInfo.bizDateRegistData;
        String l_strAftBizDateRegistData = l_productConditionInfo.aftBizDateRegistData;
        String l_strAftNextBizDateRegistData = l_productConditionInfo.aftNextBizDateRegistData;
        WEB3AdminPMEquityDataManager l_adminPMEquityDataManager = null;
        l_adminPMEquityDataManager = new WEB3AdminPMEquityDataManager();

        // Step 1
        String l_updateRpw = this.getColumnName(l_productConditionInfo.smallItemDiv);

        /*
         * Step 2
         * If parameter.l_productConditionInfo.bizDateRegistData !=
         * �@@�@@parameter.l_productConditionInfo.aftBizDateRegistData,
         * and parameter.tradedProductsNextDay
         */
        if (isChange(l_strBizDateRegistData, l_strAftBizDateRegistData)
            && l_tradedProductsNextDay != null)
        {
            l_adminPMEquityDataManager.setTradedProductRegistrationValue(
                l_productConditionUpdateInfo.tradeProductNextUpdateInfo,
                l_tradedProductsNextDay,
                l_productConditionInfo.smallItemDiv,
                l_productConditionInfo.marketCode,
                l_updateRpw,
                l_productConditionInfo.aftBizDateRegistData,
                l_lastUpdater);
        }

        /*
         * Step 3
         * If parameter.l_productConditionInfo.bizDateRegistData !=
         * �@@�@@parameter.l_productConditionInfo.aftNextBizDateRegistData,
         * and parameter.l_tradedProductTwoDaysLater != null
         */
        if (isChange(l_strBizDateRegistData, l_strAftNextBizDateRegistData)
            && l_tradedProductsTwoDaysLater != null)
        {
            l_adminPMEquityDataManager.setTradedProductRegistrationValue(
                l_productConditionUpdateInfo.tradeProductNext2UpdateInfo,
                l_tradedProductsTwoDaysLater,
                l_productConditionInfo.smallItemDiv,
                l_productConditionInfo.marketCode,
                l_updateRpw,
                l_productConditionInfo.aftNextBizDateRegistData,
                l_lastUpdater);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �ύX���͂����邩�ǂ����𔻕ʂ���B<BR>
     * �ύX����Ă���ꍇ�́Atrue���B<BR>
     * �ȊO�Afalse��ԋp����B<BR>
     * <BR>
     * @@param l_strBefore (�ύX�O�̒l)<BR>
     * @@param l_strAfter (�ύX��̒l)<BR>
     * @@return boolean<BR>
     */
    private boolean isChange(String l_strBefore, String l_strAfter)
    {
        final String STR_METHOD_NAME = "isChange(String, String)";
        log.entering(STR_METHOD_NAME);
        
        boolean l_blnResult = false;
        if (l_strBefore != null && !l_strBefore.equals(l_strAfter))
        {
            l_blnResult = true;
        }
        else if (l_strAfter != null && !l_strAfter.equals(l_strBefore))
        {
            l_blnResult = true;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_blnResult;
    }

    /**
     * �n�b�V���L�[���擾����B<BR>
     * ������.�s��R�[�h���P���������ꍇ�ɂ́A���Ɂh0�h��t������B<BR>
     * <BR>
     * �P�j�@@����.�s��R�[�h.length() > 1 �ꍇ<BR>
     * �@@�@@�@@�@@����.�s��R�[�h + ����.�����ڋ敪 = Key<BR>
     * <BR>
     * �Q�j�@@��L�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�h0�h + ����.�s��R�[�h + ����.�����ڋ敪 = Key<BR>
     * <BR>
     * �R�j�@@�P�j�܂��͂Q�j�Ŏ擾����Key��return����B<BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * @@param l_strSmallItemDiv - (�����ڋ敪)<BR>
     * @@return String
     */
    protected String getKey(String l_strMarketCode, String l_strSmallItemDiv)
    {
    	final String STR_METHOD_NAME = "getKey(String, String)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@����.�s��R�[�h.length() > 1 �ꍇ
        if (l_strMarketCode.length() > 1)
        {
        	log.exiting(STR_METHOD_NAME);
        	return l_strMarketCode + l_strSmallItemDiv;
        }
        // ��L�ȊO�̏ꍇ
        else
        {
        	log.exiting(STR_METHOD_NAME);
        	return "0" + l_strMarketCode + l_strSmallItemDiv;
        }
    }
}
@
