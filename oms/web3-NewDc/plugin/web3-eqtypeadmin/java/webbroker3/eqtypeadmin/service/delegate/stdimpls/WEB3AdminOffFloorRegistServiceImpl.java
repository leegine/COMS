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
filename	WEB3AdminOffFloorRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (�Ǘ��җ���O���������V�K�o�^�T�[�r�XImpl)(WEB3AdminOffFloorRegistServiceImpl.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;

import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchMarketDealtCond;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.data.OffFloorOrderProductParams;

import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorRegistCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorRegistCompleteResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorRegistConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorRegistConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorRegistInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorRegistInputResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminOffFloorRegistService;

/**
 * �i�Ǘ��җ���O���������V�K�o�^�T�[�r�XImpl�j<BR>
 * <BR>
 * �Ǘ��җ���O���������V�K�o�^�T�[�r�X�����N���X<BR>
 * <BR>
 * WEB3AdminOffFloorRegistServiceImpl<BR>
 * <BR>
 * @@author Saravanan Vijay and Rajesh
 * @@version 1.0
 */
public class WEB3AdminOffFloorRegistServiceImpl
    extends WEB3ClientRequestService
    implements WEB3AdminOffFloorRegistService
{

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminOffFloorRegistServiceImpl.class);
    /**
     * @@roseuid 421AE54D01C8
     */
    public WEB3AdminOffFloorRegistServiceImpl()
    {

    }

    /**
     * �Ǘ��җ���O���������V�K�o�^�T�[�r�X���������{����B<BR>
     * <BR>
     * Execute WEB3AdminOffFloorRegistService<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 41BD56120098
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        try
        {
            if (l_request instanceof WEB3AdminOffFloorRegistInputRequest)
            {
                l_response = this.getInputScreen((WEB3AdminOffFloorRegistInputRequest) l_request);

            } else if (l_request instanceof WEB3AdminOffFloorRegistCompleteRequest)
            {
                l_response = this.submitRegist((WEB3AdminOffFloorRegistCompleteRequest) l_request);
            } else if (l_request instanceof WEB3AdminOffFloorRegistConfirmRequest)
            {
                l_response = this.validateRegist((WEB3AdminOffFloorRegistConfirmRequest) l_request);
            } else
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "INPUT ���N�G�X�g NOT �Ǘ��җ���O���������V�K�o�^���N�G�X�g");
            }
        } catch (NotFoundException l_notFoundException)
        {
            log.error(l_notFoundException.getMessage(), l_notFoundException);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_notFoundException.getMessage(),
                l_notFoundException);
        } catch (DataFindException l_dataException)
        {
            log.error(l_dataException.getMessage(), l_dataException);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dataException.getMessage(),
                l_dataException);
        } catch (DataNetworkException l_dataException)
        {
            log.error(l_dataException.getMessage(), l_dataException);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dataException.getMessage(),
                l_dataException);
        } catch (DataQueryException l_dataException)
        {
            log.error(l_dataException.getMessage(), l_dataException);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dataException.getMessage(),
                l_dataException);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;

    }

    /**
     * (get���͉��)<BR>
     * <BR>
     * �Ǘ��җ���O�����̖����V�K�o�^��ʕ\���T�[�r�X�����{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�Ǘ��җ���O���������V�K�o�^�jget���͉�ʁv�Q�ƁB<BR>
     * <BR>
     * ------<English>------------------<BR>
     * <BR>
     * getInputScreen<BR>
     * <BR>
     * Executue WEB3AdminOffFloorRegistService screen process<BR>
     * <BR>
     * Refer to the sequence diagram "�iadministrator: off floor
     * regist�jgetInputScreen"<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * <BR>
     * ���N�G�X�g�f�[�^<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminOffFloorRegistInputResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws NotFoundException NotFoundException
     * @@roseuid 41BD5546022E
     */
    protected WEB3AdminOffFloorRegistInputResponse getInputScreen(
        WEB3AdminOffFloorRegistInputRequest l_request)
        throws WEB3BaseException, NotFoundException
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminOffFloorRegistInputRequest)";
        log.entering(STR_METHOD_NAME);

        String l_strTransactionCategory = WEB3TransactionCategoryDef.EQTYPE_OFF_FLOOR_ORDER;
        // 1.1 Acquire WEB3Administrator object from loginInfo
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //1.2 Check validateAuthority
        l_admin.validateAuthority(l_strTransactionCategory, true);

        //1.3 Judge if an administrator has a permission to deal all branches
        //1.4 Acquire l_institution corresponding an administrator belongs to
        //1.5 l_isAllBranchPermission == true
        Institution l_institution = l_admin.getInstitution();
        String[] l_strHandlingPossibleMarkets = null;
        if (l_admin.isAllBranchsPermission())
        {
            //1.5.1 Acquire a list of handling possible markets in the institution
            l_strHandlingPossibleMarkets =
                WEB3GentradeBranchMarketDealtCond.getHandlingPossibleMarket(
                    l_institution.getInstitutionCode(),
                    ProductTypeEnum.EQUITY);
        } else
        {
            //1.6.1 Acquire branchCode of branch an administrator belongs to
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();

            //1.6.2 Acquire l_branch of a corresponding administrator
            /* Parameter for the getBranch should contain Institution
                in WEB3AdminEquityAccProductTradeStopDeleteServiceImpl
             */
            WEB3GentradeBranch l_gentradeBranch = 
                (WEB3GentradeBranch) l_accountManager.getBranch(
                    l_institution, l_admin.getBranchCode()
                    );

            //1.6.3 Acquire a list of handling possible markets of the branch
            l_strHandlingPossibleMarkets =
                WEB3GentradeBranchMarketDealtCond.getHandlingPossibleMarket(
                    l_gentradeBranch,
                    ProductTypeEnum.EQUITY);
        }
        
        // �戵�s��Ȃ��iBUSINESS_ERROR_01365�j
        if (l_strHandlingPossibleMarkets == null || 
            l_strHandlingPossibleMarkets.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01365,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.7 Create WEB3AdminOffFloorRegistInputResponse
        WEB3AdminOffFloorRegistInputResponse l_response = 
            (WEB3AdminOffFloorRegistInputResponse) l_request.createResponse();

        //1.8 Set response data into Property Set
        l_response.marketList = l_strHandlingPossibleMarkets;

        InstitutionParams l_institutionParams = 
            (InstitutionParams) l_institution.getDataSourceObject();

        String l_strDate = WEB3DateUtility.formatDate(
            GtlUtils.getSystemTimestamp(), "yyyyMMdd"
            );
        String l_strTime = l_institutionParams.off_floor_order_start_hhmmss;
        // Check for l_strTime is null
        if (l_strTime == null)
        {
            l_strTime = "170000";
        }
        l_response.orderStartDatetime = 
            WEB3DateUtility.getDate((l_strDate + l_strTime), "yyyyMMddHHmmss");
        l_response.orderEndTime = l_institutionParams.off_floor_order_end_hhmmss;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�V�K�o�^)<BR>
     * <BR>
     * �Ǘ��җ���O�����̖����V�K�o�^�m�F�T�[�r�X�����{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�Ǘ��җ���O���������V�K�o�^�jvalidate�V�K�o�^�v�Q�ƁB<BR>
     * <BR>
     * ------<English>----------------<BR>
     * <BR>
     * validateRegist<BR>
     * <BR>
     * Execute WEB3AdminOffFloorRegistService validate process<BR>
     * <BR>
     * Refer to the sequence diagram "�iadministrator: off floor product
     * regist�jvalidateRegist"<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * <BR>
     * ���N�G�X�g�f�[�^<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws NotFoundException NotFoundException
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminOffFloorRegistConfirmResponse
     * @@roseuid 41BD55AF0069
     */
    protected WEB3AdminOffFloorRegistConfirmResponse validateRegist(
        WEB3AdminOffFloorRegistConfirmRequest l_request)
        throws WEB3BaseException, NotFoundException
    {
        final String STR_METHOD_NAME = "validateRegist(WEB3AdminOffFloorRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManger = 
            (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
        WEB3EquityProductManager l_equityProductManger = 
            (WEB3EquityProductManager) l_tradingModule.getProductManager();

        // 1.1 To call validate()
        l_request.validate();

        // 1.2 To call getInstanceFromLoginInfo()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        // 1.3 To call validateAuthority()
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.EQTYPE_OFF_FLOOR_ORDER, true);

        // 1.4 To call getInstitution()
        Institution l_institution = l_admin.getInstitution();

        // 1.5 To call validateOrderStartDatetime()
        this.validateOrderStartDatetime(
            l_institution, l_request.orderStartDatetime);

        // 1.6 To call isAllBranchsPermission()
        // 1.7 To call getBranchCode()
        String l_strBranchCode = null;
        if (!l_admin.isAllBranchsPermission())
        {
            l_strBranchCode = l_admin.getBranchCode();
        }

        WEB3GentradeTradingTimeManagement.resetMarketCode(l_request.marketCode);

        // 1.8 To call validateOffloorOrderProduct()
        l_orderManger.validateOffFloorOrderProduct(
        l_admin.getInstitutionCode(),
            l_strBranchCode,
            l_request.productCode,
            l_request.marketCode,
            l_request.maxApplyQuantity
            );

        // 1.9 To call getOrderEndDatetime()
        Date l_datOrderEndDatetime = this.getOrderEndDatetime(
            l_institution, l_request.orderStartDatetime
            );

        // 1.10 To call getOffFloorOrderProduct()
        OffFloorOrderProductParams l_params = 
            l_equityProductManger.getOffFloorOrderProduct(
                l_admin.getInstitutionCode(),
                l_request.productCode,
                l_request.marketCode,
                l_datOrderEndDatetime
                );

        // 1.11
        // To check l_params is not null
        if (l_params != null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01461,
                this.getClass().getName());
        }

        // 1.12 To call getProduct()
        EqTypeProduct l_product = l_equityProductManger.getProduct(
            l_institution, l_request.productCode);

        // 1.13 To Create WEB3AdminOffFloorRegistConfirmResponse
        WEB3AdminOffFloorRegistConfirmResponse l_response = 
            (WEB3AdminOffFloorRegistConfirmResponse) l_request.createResponse();

        // 1.14 set property
        l_response.productName = 
            ((EqtypeProductRow)l_product.getDataSourceObject()).getStandardName();
        l_response.orderEndDatetime = l_datOrderEndDatetime;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�V�K�o�^)<BR>
     * <BR>
     * �Ǘ��җ���O�����̖����V�K�o�^�����T�[�r�X�����{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�Ǘ��җ���O���������V�K�o�^�jsubmit�V�K�o�^�v�Q�ƁB<BR>
     * <BR>
     * -----<English>------------<BR>
     * <BR>
     * submitRegist<BR>
     * <BR>
     * Execute WEB3AdminOffFloorRegistService submit process<BR>
     * <BR>
     * Refer to the sequence diagram "�iadministrator: off floor
     * regist�jsubmitRegist"BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * <BR>
     * ���N�G�X�g�f�[�^<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminOffFloorRegistCompleteResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws DataNetworkException DataNetworkException
     * @@throws DataQueryException DataQueryException
     * @@roseuid 41BD55DF0309
     */
    protected WEB3AdminOffFloorRegistCompleteResponse submitRegist(
        WEB3AdminOffFloorRegistCompleteRequest l_request)
        throws WEB3BaseException, DataNetworkException, DataQueryException
    {
        final String STR_METHOD_NAME = "submitRegist(WEB3AdminOffFloorRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //Step 1.1 Check the property of l_request
        l_request.validate();

        //Step 1.2 Acquire WEB3Administrator object from loginInfo
        WEB3Administrator l_administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();

        //Step 1.3 Check validateAuthority
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.EQTYPE_ACCOUNT_PRODUCT_STOP, true);

        //Step 1.4 Check tradingPassword
        l_administrator.validateTradingPassword(l_request.password);

        //Step 1.5 Acquire l_institution
        Institution l_institution = l_administrator.getInstitution();

        //Step 1.6 Check orderStartDatetime
        this.validateOrderStartDatetime(l_institution, l_request.orderStartDatetime);

        //Step 1.7 Judge if an administrator has permission to deal all branches
        //Step 1.8 Acquire branchCode
        //Step 1.9 Check offFloorProduct
        String l_strBranchCode = l_administrator.getBranchCode();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(
            ProductTypeEnum.EQUITY
            );
        WEB3EquityOrderManager l_equityOrderManager = 
            (WEB3EquityOrderManager) l_tradingModule.getOrderManager();

        if (l_administrator.isAllBranchsPermission())
        {
            l_strBranchCode = null;
        }
        WEB3GentradeTradingTimeManagement.resetMarketCode(l_request.marketCode);
        l_equityOrderManager.validateOffFloorOrderProduct(
            l_institution.getInstitutionCode(),
            l_strBranchCode,
            l_request.productCode,
            l_request.marketCode,
            l_request.maxApplyQuantity
            );

        //step 1.10 Acquire orderEndDatetime
        Date l_datEndDateTime = getOrderEndDatetime(
            l_institution, l_request.orderStartDatetime
            );

        // Step1.11 Acquire off_floor_order_product data (also: check overlapping)
        WEB3EquityProductManager l_equityProductManager = 
            (WEB3EquityProductManager) l_tradingModule.getProductManager();

        OffFloorOrderProductParams l_offFloorOrderProductParams =
            l_equityProductManager.getOffFloorOrderProduct(
                l_institution.getInstitutionCode(),
                l_request.productCode,
                l_request.marketCode,
                l_datEndDateTime
                );

        //Step 1.12 Divergence flow: If overlapping offFloorProduct data is registered
        if (l_offFloorOrderProductParams != null)
        {
            //Step 1.12.1 Throw the exception
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01461,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // Step 1.13 Register one record with off_floor_order_product table
        insertOffFloorOrderProduct(
            l_request, 
            l_institution.getInstitutionCode(), 
            l_datEndDateTime
            );

        // Step 1.14 Create WEB3AdminOffFloorRegistCompleteResponse
        WEB3AdminOffFloorRegistCompleteResponse l_response = 
            (WEB3AdminOffFloorRegistCompleteResponse) l_request.createResponse();

        // Step 1.15 Set response data into 'Property Set'
        l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();

        // Step 1.16 returnt the l_response object
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get��t�I������)<BR>
     * <BR>
     * ��t�I���������擾����B<BR>
     * <BR>
     * �P�j�@@YYYYMMDD�����߂�B<BR>
     * <BR>
     * �@@�@@�@@�����̎�t�J�n�����̗��c�Ɠ���YYYYMMDD���A��t�I��������YYYYMMDD�Ƃ���B<B
     * R>
     * <BR>
     * �Q�j�@@HHMMSS�����߂�B<BR>
     * <BR>
     * �@@�@@�@@�����̏،����.����O������t�I������ ���AHHMMSS�Ƃ���B<BR>
     * <BR>
     * �R�j�@@�P�j�y�тQ�j��Date�I�u�W�F�N�g�𐶐����A��t�I�������Ƃ��ĕԋp����B<BR>
     * <BR>
     * -----<English>------------------<BR>
     * <BR>
     * getOrderEndDatetime<BR>
     * <BR>
     * Acquire orderEndDatetime<BR>
     * <BR>
     * 1)Acquire YYYYMMDD<BR>
     * <BR>
     * �@@�@@�@@Assume that YYYYMMDD of next bizDate of l_orderStartDatetime is YYYYMMDD
     * of orderEndDatetime<BR>
     * <BR>
     * 2)Acquire HHMMSS<BR>
     * <BR>
     * �@@�@@�@@Assume that l_institution.orderEndDatetime is HHMMSS<BR>
     * <BR>
     * 3) CreateDate object at 1) and 2), and return as orderEndDatetime<BR>
     * <BR>
     * @@param l_institution - (�،����)<BR>
     * <BR>
     * �،���ЃI�u�W�F�N�g�B<BR>
     * <BR>
     * l_institution<BR>
     * <BR>
     * @@param l_orderStartDatetime - (��t�J�n����)<BR>
     * <BR>
     * ��t�J�n�����B<BR>
     * <BR>
     * l_orderStartDatetime<BR>
     * <BR>
     * @@return Date
     * @@throws WEB3SystemLayerException WEB3SystemLayerException
     * @@roseuid 41BEA9C20297
    */
    protected Date getOrderEndDatetime(Institution l_institution, Date l_orderStartDatetime)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getOrderEndDatetime(Institution, Date)";
        log.entering(STR_METHOD_NAME);

        Timestamp l_timestamp = new Timestamp(l_orderStartDatetime.getTime());
        WEB3GentradeBizDate l_gentradeBizDate = new WEB3GentradeBizDate(l_timestamp);
        l_timestamp = l_gentradeBizDate.roll(1);
        Date l_nextBizDate = new Date(l_timestamp.getTime());

        // 1 Acquire YYYYMMDD of nextBizDate
        // 2. Acquire HHMMSS of nextBizDate
        InstitutionParams l_institutionParams = 
            (InstitutionParams)l_institution.getDataSourceObject();
        String l_strDate = WEB3DateUtility.formatDate(l_nextBizDate, "yyyyMMdd");
        String l_strTime = l_institutionParams.off_floor_order_end_hhmmss;
        // Check for l_strTime is null
        if (l_strTime == null)
        {
            l_strTime = "170000";
        }

        log.exiting(STR_METHOD_NAME);

        // 3. Create Date object
        return WEB3DateUtility.getDate((l_strDate + l_strTime), "yyyyMMddHHmmss");
    }

    /**
     * (insert����O��������)<BR>
     * <BR>
     * �y����O���������e�[�u���z�ցA <BR>
     * �����Ŏw�肳�ꂽ����O�����������R�[�h��o�^����B <BR>
     * <BR>
     * �P�j�@@�y����O���������e�[�u���z�ɁA�ȉ��̂P���R�[�h��o�^����B <BR>
     * <BR>
     * �@@�@@���v���p�e�B�Z�b�g�� <BR>
     * �@@�@@�@@�،���ЃR�[�h = �����̏،���ЃR�[�h<BR>
     * �@@�@@�@@�����R�[�h = �����̃��N�G�X�g�f�[�^.�����R�[�h<BR>
     * �@@�@@�@@�s��R�[�h = �����̃��N�G�X�g�f�[�^.�s��R�[�h<BR>
     * �@@�@@�@@��t�J�n���� = �����̃��N�G�X�g�f�[�^.��t�J�n����<BR>
     * �@@�@@�@@��t�I������ = �����̎�t�I������<BR>
     * �@@�@@�@@�\��������� = �����̃��N�G�X�g�f�[�^.�\���������<BR>
     * �@@�@@�@@�������i = �����̃��N�G�X�g�f�[�^.�������i<BR>
     * �@@�@@�@@���{���O�c�Ɠ��I�l = null<BR>
     * �@@�@@�@@��n�� = null<BR>
     * �@@�@@�@@�쐬���t = GtlUtils.getSystemTimestamp( ) <BR>
     * �@@�@@�@@�X�V���t = GtlUtils.getSystemTimestamp( ) <BR>
     * <BR>
     * �Q�j�@@return����B <BR>
     * <BR>
     * -----<English>---------------------------<BR>
     * <BR>
     * insertOffFloorOrderProduct<BR>
     * <BR>
     * Register an off floor order product specified with an argument with
     * off_floor_order_product table<BR>
     * <BR>
     * 1)Register the following one record with off_floor_order_product table<BR>
     * <BR>
     * �@@�@@��Property Set�� <BR>
     * �@@�@@�@@institution_code = l_strInstitutionCode<BR>
     * �@@�@@�@@product_code = l_request.productCodeBR>
     * �@@�@@�@@market_code = l_request.marketCode<BR>
     * �@@�@@�@@order_start_datetime_ = l_request.orderStartDatetime<BR>
     * �@@�@@�@@order_end_datetime = l_orderEndDatetime<BR>
     * �@@�@@�@@max_apply_quantity = l_request.maxApplyQuantity<BR>
     * �@@�@@�@@off_floor_order_price = l_request.offFloorPrice<BR>
     * �@@�@@�@@last_closing_price = null<BR>
     * �@@�@@�@@daily_delivery_date = null<BR>
     * �@@�@@�@@created_timestamp = GtlUtils.getSystemTimestamp( ) <BR>
     * �@@�@@�@@last_updated_timestamp = GtlUtils.getSystemTimestamp( ) <BR>
     * <BR>
     * 2)return<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�I�u�W�F�N�g�B<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * <BR>
     * �،���ЃR�[�h�B<BR>
     * <BR>
     * l_strInstitutionCode<BR>
     * <BR>
     * @@param l_orderEndDatetime - (��t�I������)<BR>
     * @@throws DataNetworkException DataNetworkException
     * @@throws DataQueryException DataQueryException
     * <BR>
     * ��t�I�������B<BR>
     * <BR>
     * l_orderEndDatetime<BR>
     * <BR>
     * @@roseuid 41BFD9BB00E4
    */
    protected void insertOffFloorOrderProduct(
        WEB3AdminOffFloorRegistCompleteRequest l_request,
        String l_strInstitutionCode,
        Date l_orderEndDatetime)
        throws DataNetworkException, DataQueryException
    {
        final String STR_METHOD_NAME =
            "insertOffFloorOrderProduct(WEB3AdminOffFloorRegistCompleteRequest, String, Date)";
        log.entering(STR_METHOD_NAME);

        Double l_dblMaxApplyQuantity = null;
        if (l_request.maxApplyQuantity != null)
        {
            l_dblMaxApplyQuantity = new Double(l_request.maxApplyQuantity);
        }
        
        Double l_dblOffFloorOrderPrice = null;
        if (l_request.offFloorOrderPrice != null)
        {
            l_dblOffFloorOrderPrice = new Double(l_request.offFloorOrderPrice);
        }

        OffFloorOrderProductParams l_offFloorOrderProductParams = new OffFloorOrderProductParams();

        //Step 1 Register the following one record with off_floor_order_product table
        l_offFloorOrderProductParams.setInstitutionCode(l_strInstitutionCode);
        l_offFloorOrderProductParams.setProductCode(l_request.productCode);
        l_offFloorOrderProductParams.setMarketCode(l_request.marketCode);
        l_offFloorOrderProductParams.setOrderStartDatetime(l_request.orderStartDatetime);
        l_offFloorOrderProductParams.setOrderEndDatetime(l_orderEndDatetime);
        l_offFloorOrderProductParams.setMaxApplyQuantity(l_dblMaxApplyQuantity);
        l_offFloorOrderProductParams.setOffFloorOrderPrice(l_dblOffFloorOrderPrice);
        l_offFloorOrderProductParams.setLastClosingPrice(null);
        l_offFloorOrderProductParams.setDailyDeliveryDate(null);
        l_offFloorOrderProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_offFloorOrderProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        WEB3DataAccessUtility.insertRow(l_offFloorOrderProductParams);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate��t�J�n����)<BR>
     * <BR>
     * �w�肳�ꂽ��t�J�n�������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�ߋ��̓������w�肳��Ă��Ȃ����ǂ����`�F�b�N����B<BR>
     * <BR>
     * �@@�@@�@@�����̎�t�J�n���� < ���ݓ���(*1) �̏ꍇ�́A<BR>
     * �@@�@@�@@�u����O���������E��t�J�n�����ɂ́A�ߋ������w��s�v�̗�O��throw����B<
     * BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01459<BR>
     * <BR>
     * �@@�@@(*1)���ݓ����FGtlUtils.getSystemTimestamp( )�Ŏ擾�B<BR>
     * <BR>
     *�Q�j�@@�����̎�t�J�n�����Ƃ��ċ��e����邩�ǂ����`�F�b�N����B<BR>
     * <BR>
     * �@@�@@�@@�����̎�t�J�n������HHMMSS < �����̏،����.����O������t�J�n����
     * �̏ꍇ�́A<BR>
     * �@@�@@�@@�u����O���������E��t�J�n�����ɂ́A�،����.����O������t�J�n�����O��
     * �@@�@@�@@�w��s�v�̗�O��throw����B<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01460<BR>
     * <BR>
     * �R�j�@@return����B<BR>
     * <BR>
     * -----<English>-------------<BR>
     * <BR>
     * validateOrderStartDatetime<BR>
     * <BR>
     * Check a specified orderStartDatetime<BR>
     * <BR>
     * 1)Check if a past datetime is specified.<BR>
     * <BR>
     * �@@�@@�@@If l_orderStartDatetime < currentTime(*1)<BR>
     * �@@�@@�@@Throw the exception "It is unable to set past datetime to
     * l_offFloorProduct�El_orderStartDatetime"<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01459<BR>
     * <BR>
     * �@@�@@(*1)currentTime: Acquired at GtlUtils.getSystemTimestamp( )<BR>
     * <BR>
     * 2)Check if it is possibble to be off floor orderStartDatetime<BR>
     * <BR>
     * �@@�@@�@@If HHMMSS of l_orderStartDatetime < l_institution.orderStartDatetime<BR>
     * �@@�@@�@@Throw the exception "It is unable to set the time before
     * l_institution.orderStartDatetime to l_offFloorProduct�El_orderStartDatetime<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01460<BR>
     * <BR>
     * 3)return<BR>
     * <BR>
     * @@param l_institution - (�،����)<BR>
     * <BR>
     * �،���ЃI�u�W�F�N�g�B<BR>
     * <BR>
     * l_institution<BR>
     * <BR>
     * @@param l_orderStartDatetime - (��t�J�n����)<BR>
     * <BR>
     * ��t�J�n�����B<BR>
     * <BR>
     * l_orderStartDatetime<BR>
     * <BR>
     * @@throws WEB3BusinessLayerException WEB3BusinessLayerException
     * @@roseuid 41C119ED0308
    */
    protected void validateOrderStartDatetime(Institution l_institution, Date l_orderStartDatetime)
        throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validateOrderStartDatetime(Institution, Date)";
        log.entering(STR_METHOD_NAME);

        // 1 To Check if a past datetime is specified
        if (l_orderStartDatetime.before(GtlUtils.getSystemTimestamp()))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01459,
                this.getClass().getName());
        }

        InstitutionParams l_institutionParams = 
            (InstitutionParams) l_institution.getDataSourceObject();

        String l_strTime1 = WEB3DateUtility.formatDate(l_orderStartDatetime, "HHmmss");
        String l_strTime2 = l_institutionParams.off_floor_order_start_hhmmss;
        // Check for l_strTime is null
        if (l_strTime2 == null)
        {
            l_strTime2 = "170000";
        }

        // 2 Check if it is possibble to be off floor orderStartDatetime
        if (l_strTime1.compareTo(l_strTime2) < 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01460,
                this.getClass().getName());
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
