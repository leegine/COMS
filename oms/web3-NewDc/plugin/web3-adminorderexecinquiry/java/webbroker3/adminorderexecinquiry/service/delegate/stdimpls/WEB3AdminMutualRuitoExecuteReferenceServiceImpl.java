head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.22.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMutualRuitoExecuteReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ғ��M�ݓ��������Ɖ�T�[�r�XImpl(WEB3AdminMutualRuitoExecuteReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/09/19 ���� (���u) �d�l�ύX�E���f��061�A070�A073
Revision History : 2006/12/19 ������(���u) ���f��No.087
Revision History : 2007/01/12 ���� (���u) �d�l�ύX�E���f��089
Revesion History : 2007/02/26 �����(���u)�d�l�ύX���f��No.093
*/
package webbroker3.adminorderexecinquiry.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderManager;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoOrderManager;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoProductManager;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoProductRow;

import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.define.WEB3SystemHandlingDivDef;
import webbroker3.common.define.WEB3TaxTypeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderUnitIntroduceDiv;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;

import webbroker3.mf.WEB3MutualFundOrderManager;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualFundTradingTimeManagement;
import webbroker3.mf.define.WEB3MFAccountDivDef;
import webbroker3.mf.define.WEB3MutualFrgnMmfDisplayDivDef;

import webbroker3.adminorderexecinquiry.WEB3AdminOrderExecuteDataManager;
import webbroker3.adminorderexecinquiry.WEB3AdminProductExecutionInfo;
import webbroker3.adminorderexecinquiry.define.WEB3AdminKeyItemDef;
import webbroker3.adminorderexecinquiry.define.WEB3AdminProductDivDef;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORMutualRuitoOrderExecutionRefInputRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORMutualRuitoOrderExecutionRefInputResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORMutualRuitoOrderExecutionRefReferenceRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORMutualRuitoOrderExecutionRefReferenceResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORMutualRuitoOrderExecutionRefUnit;
import webbroker3.adminorderexecinquiry.message.WEB3AdminOROrderExecutionRefCommonRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminOROrderExecutionSortKeyUnit;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORProductNameSetUnit;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORTradingProductUnit;
import webbroker3.adminorderexecinquiry.service.delegate.WEB3AdminMutualRuitoExecuteReferenceService;

/**
 * (�Ǘ��ғ��M�ݓ��������Ɖ�T�[�r�XImpl)<BR>
 * <BR>
 * �Ǘ��ғ��M�ݓ��������Ɖ�T�[�r�X�����N���X<BR>
 * <BR>
 * WEB3AdminMutualRuitoExecuteReferenceServiceImpl<BR>
 * <BR>
 * @@author Vijay Kumar
 * @@version 1.0
 */
public class WEB3AdminMutualRuitoExecuteReferenceServiceImpl
    extends WEB3ClientRequestService
    implements WEB3AdminMutualRuitoExecuteReferenceService
{
    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMutualRuitoExecuteReferenceServiceImpl.class);

    /**
     * @@roseuid 4212FBE60315
     */
    public WEB3AdminMutualRuitoExecuteReferenceServiceImpl()
    {
    }

    /**
     * ���M�ݓ��������Ɖ�����s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * �ȉ��̃��\�b�h���Ăѕ�����B<BR>
     * <BR>
     * ���Ǘ��ҁE���M�ݓ��������Ɖ���̓��N�G�X�g�̏ꍇ<BR>
     * �@@this.get���͉��()���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE���M�ݓ��������Ɖ�N�G�X�g�̏ꍇ<BR>
     * �@@this.get�Ɖ���()���R�[������B<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * WEB3AdminMutualRuitoExecuteReferenceService process<BR>
     * <BR>
     * Call one of the following methods based on the type of the argument,
     * l_request.<<BR>
     * <BR>
     * ��If WEB3AdminORMutualRuitoOrderExecutionRefInputRequest<BR>
     * �@@Call this.getInputScreen()<BR>
     * <BR>
     * ��If WEB3AdminORMutualRuitoOrderExecutionRefReferenceRequest<BR>
     * �@@Call this.getReferenceScreen()<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 41AE9E830308
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        try
        {
            if (l_request instanceof WEB3AdminORMutualRuitoOrderExecutionRefInputRequest)
            {
                l_response =
                    this.getInputScreen(
                        (WEB3AdminORMutualRuitoOrderExecutionRefInputRequest) l_request);
            } else if (
                l_request instanceof WEB3AdminORMutualRuitoOrderExecutionRefReferenceRequest)
            {
                l_response =
                    this.getReferenceScreen(
                        (WEB3AdminORMutualRuitoOrderExecutionRefReferenceRequest) l_request);
            } else
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "INPUT ���N�G�X�g NOT �Ǘ��ҁE���M�ݓ��������Ɖ�N�G�X�g");
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
     * ���M�ݓ��������Ɖ���͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��ғ��M�ݓ��������Ɖ�T�[�r�X)get���͉�ʁv<BR>
     * �Q��<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * getInputScreen<BR>
     * <BR>
     * Execute WEB3AdminMutualRuitoExecuteReferenceService input screen process<BR>
     * <BR>
     * Refer to the sequence diagram "(administrator: mutual ruito order execution
     * inquiry service)getInputScreen"<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * <BR>
     * �Ǘ��ҁE���M�ݓ��������Ɖ���̓��N�G�X�g�I�u�W�F�N�g<BR>
     * <BR>
     * ---------<English>----------<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminORMutualRuitoOrderExecutionRefInputRequest object<BR>
     * <BR>
     * @@return
     * webbroker3.adminorderexecinquiry.message.WEB3AdminORMutualRuitoOrderExecutionRef
     * InputResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws DataQueryException DataQueryException
     * @@throws DataNetworkException DataNetworkException
     * @@throws IllegalStateException IllegalStateException
     * @@throws WEB3BusinessLayerException WEB3BusinessLayerException
     * @@throws WEB3BusinessLayerException WEB3BusinessLayerException
     * @@throws WEB3SystemLayerException WEB3SystemLayerException
     * @@throws NotFoundException NotFoundException
     * @@roseuid 41AE9E830327
     */
    protected WEB3AdminORMutualRuitoOrderExecutionRefInputResponse getInputScreen
        (WEB3AdminORMutualRuitoOrderExecutionRefInputRequest l_request)
        throws
            WEB3BaseException,
            DataQueryException,
            DataNetworkException,
            IllegalStateException,
            WEB3BusinessLayerException,
            WEB3SystemLayerException,
            NotFoundException
    {
        final String STR_METHOD_NAME =
            "getInputScreen(WEB3AdminORMutualRuitoOrderExecutionRefInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminORMutualRuitoOrderExecutionRefInputResponse l_response = null;
        WEB3Administrator l_web3Administrator = null;
        WEB3AdminOrderExecuteDataManager l_webAdminOrderExecuteDataManager =
            new WEB3AdminOrderExecuteDataManager();
        WEB3AdminORTradingProductUnit[] l_tradingProductUnits = null;
        WEB3AdminProductExecutionInfo l_web3ProductExecutionInfo = null;
        WEB3AdminORProductNameSetUnit[] l_web3AdminORProductNameSetUnits = null;
        WEB3GentradeBizDate l_gentradeBizDate = null;
        String l_strInstitutionCode = null;
        String[] l_branchCodes = null;
        Date l_datOrderBizDate = null;
        Date[] l_orderBizDates = null;
        ArrayList l_lisOrderBizDate = null;

        // Step 1.1, The l_request object is validated
        l_request.validate();

        // Step  1.2, Acquires administator instance from login infomation
        l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // Step1.3, Checks transactionCategory i.e validates it.
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.XBMF_XBRUITO_ORDER_EXEC_INQUIRY,
            false);

        // Step 1.4, Acquires institutionCode.
        l_strInstitutionCode = l_web3Administrator.getInstitutionCode();

        // Step 1.5, Acquire productExecutionInfo about a handling possible branch
        l_branchCodes = l_request.branchCode;
        l_web3ProductExecutionInfo =
            l_webAdminOrderExecuteDataManager.getProductExecutionInfo(
                l_strInstitutionCode,
                l_branchCodes);

        // Step 1.6,Create a list of handling porssible products
        l_tradingProductUnits = this.createTradingProductList(l_web3ProductExecutionInfo);

        // Step 1,7, creating an arraylist
        l_lisOrderBizDate = new ArrayList();

        // Step 1.8, adding the bizdate to the arraylist
		Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
		l_gentradeBizDate = new WEB3GentradeBizDate(new Timestamp(l_datBizDate.getTime()));
		Timestamp l_tsPreBizdate = l_gentradeBizDate.roll(-1);
        l_lisOrderBizDate.add(WEB3DateUtility.toDay(l_tsPreBizdate));
        l_lisOrderBizDate.add(WEB3DateUtility.toDay(l_datBizDate));

        // Step 1.9,if following condition is true then execute Steps 1.9.1 to 1.9.9
        if (l_web3ProductExecutionInfo.ruitoFlag)
        {
            // Step 1.9.1,Set tradingTimeType again
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(
                WEB3TradingTimeTypeDef.MIDIUM_TERM_GOV_FUND);

            // Step 1.9.2,Acquire orderBizDate
            l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

            // Step 1.9.3, Adding acquired orderBizDate to ArrayList if it is not included in List
            if (!l_lisOrderBizDate.contains(l_datOrderBizDate))
            {
                l_lisOrderBizDate.add(l_datOrderBizDate);
            }

            // Step 1.9.4, Set tradingTimeType again
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.MMF_SET);

            // Step 1.9.5, Acquire the OrderBizDate
            l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

            // Step 1.9.6 Adding acquired orderBizDate to ArrayList if it is not included in List
            if (!l_lisOrderBizDate.contains(l_datOrderBizDate))
            {
                l_lisOrderBizDate.add(l_datOrderBizDate);
            }

            // Step 1.9.7, Set tradingTimeType again
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(
                WEB3TradingTimeTypeDef.MMF_SET_CANCEL);

            // Step 1.9.8, Acquire the OrederBizDate
            l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

            // Step 1.9.9 Add the acquired orderBizDate to ArrayList if it is not included in List
            if (!l_lisOrderBizDate.contains(l_datOrderBizDate))
            {
                l_lisOrderBizDate.add(l_datOrderBizDate);
            }
        }

        // Step 1.10,if the following condition is true execute steps 1.10.1 to 1.10.3
        if (l_web3ProductExecutionInfo.mutualFlag)
        {
            // Step 1.10.1, Set tradingTimeType again
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(
                WEB3TradingTimeTypeDef.MUTUAL_FUND);

            // Step 1.10.2,Acquire mfOrderBizDate
            l_datOrderBizDate = WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate();

            // Step 1.10.3,adding acquired orderBizDate to ArrayList if it is not included in List
            if (!l_lisOrderBizDate.contains(l_datOrderBizDate))
            {
                l_lisOrderBizDate.add(l_datOrderBizDate);
            }
        }

        // Step 1.11, call createMutualRuitoProductName()
        l_web3AdminORProductNameSetUnits =
            (WEB3AdminORProductNameSetUnit[]) this.createMutualRuitoProductName(
                l_strInstitutionCode,
                l_web3ProductExecutionInfo);

        // Step 1.12, Create response data
        l_response =
            (WEB3AdminORMutualRuitoOrderExecutionRefInputResponse) l_request.createResponse();

        // Step 1.13,Set the property to the reponse object
        l_orderBizDates = new Date[l_lisOrderBizDate.size()];
        l_orderBizDates = (Date[]) l_lisOrderBizDate.toArray(l_orderBizDates);
        l_response.orderBizDateList = l_orderBizDates;
        l_response.productNameSetList = l_web3AdminORProductNameSetUnits;
        l_response.tradingProductList = l_tradingProductUnits;
        l_response.orderRootList = new String[] {
            WEB3OrderRootDivDef.CALLCENTER,
            WEB3OrderRootDivDef.PC,
            WEB3OrderRootDivDef.SLINGSHOT,
            WEB3OrderRootDivDef.I_MODE,
            WEB3OrderRootDivDef.VODAFONE,
            WEB3OrderRootDivDef.AU,
            WEB3OrderRootDivDef.HOST
        };
        
        // Step 1.14, Return the repsonse.
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�Ɖ���)<BR>
     * <BR>
     * ���M�ݓ��������Ɖ�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��ғ��M�ݓ��������Ɖ�T�[�r�X)get�Ɖ��ʁv<BR>
     * �Q��<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * getReferenceScreen<BR>
     * <BR>
     * Execute WEB3AdminMutualRuitoExecuteReferenceService process<BR>
     * <BR>
     * Refer to the sequence diagram "(administrator: mutual ruito order execution
     * inquiry service)getReferenceScreen"<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * <BR>
     * �Ǘ��ҁE���M�ݓ��������Ɖ�N�G�X�g�I�u�W�F�N�g<BR>
     * <BR>
     * ---------<English>----------<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminORMutualRuitoOrderExecutionRefReferenceRequest object<BR>
     * <BR>
     * @@return WEB3AdminORMutualRuitoOrderExecutionRefReferenceResponse
     * webbroker3.adminorderexecinquiry.message.WEB3AdminORMutualRuitoOrderExecutionRef
     * ReferenceResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws DataQueryException DataQueryException
     * @@throws DataNetworkException DataNetworkException
     * @@throws IllegalStateException IllegalStateException
     * @@throws NotFoundException NotFoundException
     *      * @@roseuid 41AE9E830347
     */
    protected WEB3AdminORMutualRuitoOrderExecutionRefReferenceResponse getReferenceScreen
        (WEB3AdminORMutualRuitoOrderExecutionRefReferenceRequest l_request)
        throws
            WEB3BaseException,
            DataQueryException,
            DataNetworkException,
            IllegalStateException,
            NotFoundException
    {
        final String STR_METHOD_NAME =
            "getReferenceScreen(WEB3AdminORMutualRuitoOrderExecutionRefReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminORMutualRuitoOrderExecutionRefReferenceResponse l_response = null;
        WEB3Administrator l_administrator = null;
        WEB3AdminOrderExecuteDataManager l_orderExecuteDataManager = null;
        WEB3AdminOROrderExecutionSortKeyUnit[] l_sortKeyUnits = null;
        WEB3AdminORMutualRuitoOrderExecutionRefUnit[] l_orMutualRuitoOrderExecutionRefUnits = null;
        OrderUnit[] l_orderUnits = null;
        WEB3PageIndexInfo l_pageIndexInfo = null;
        String l_strInstitutionCode = null;
        String l_strProductId = null;
        String l_strTaxType = null;
        String l_strDeliveryDiv = null;
        String l_strQueryCond = null;
        String l_strProductDiv = null;
        String l_strQuery = null;
        String l_strCommonQuery = null;
        String l_strSortCond = null;
        ArrayList l_lisOrderUnits = null;
        String[] l_strBranchCodes = null;
        String[] l_strQueryCondDataContainers = null;
        String[] l_strQueryDataContainers = null;
        String[] l_strCommonQueryDataContainers = null;
        int l_intCommonDataContainer = 0;
        int l_intQueryDataContainer = 0;
        int l_intDataContainer = 0;
        int l_intPageIndex = 0;
        int l_intPageSize = 0;

        // Step 1.1 Check l_request
        l_request.validate();

        //���M�E�O��MMF�\���敪
        //��null�̏ꍇ�A�u2:�����v�Ƃ���
        if (l_request.mutualFrgnMmfDisplayDiv == null)
        {
            l_request.mutualFrgnMmfDisplayDiv =
                WEB3MutualFrgnMmfDisplayDivDef.BOTH;
        }

        // Step 1.2 Acquire WEB3Administrator object from loginInfo
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // Step 1.3 Check validateAuthority
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.XBMF_XBRUITO_ORDER_EXEC_INQUIRY,
            false);

        // Step 1.4 Check validateBranchPermission
        l_strBranchCodes = l_request.branchCode;
        l_administrator.validateBranchPermission(l_strBranchCodes);

        // Step 1.5 Acquire institutionCode
        l_strInstitutionCode = l_administrator.getInstitutionCode();
        l_orderExecuteDataManager = new WEB3AdminOrderExecuteDataManager();

        // Step 1.6 Create commonQueryString
        l_strCommonQuery =
            l_orderExecuteDataManager.createCommonQueryString(
                l_strInstitutionCode,
                (WEB3AdminOROrderExecutionRefCommonRequest) l_request);

        l_strProductId = l_request.productId;
        l_strTaxType = l_request.taxType;
        l_strDeliveryDiv = l_request.deliveryDiv;

        // Step 1.7 Create l_strQueryCond special to xbmf
        l_strQuery = this.createQueryString(
            l_strProductId,
            l_strTaxType,
            l_strDeliveryDiv,
            l_request.productDiv,
            l_request.mutualFrgnMmfDisplayDiv);

        // Step 1.8 Create commonQueryDataContainer
        l_strCommonQueryDataContainers =
            l_orderExecuteDataManager.createCommonQueryDataContainer(
                l_strInstitutionCode,
                (WEB3AdminOROrderExecutionRefCommonRequest) l_request);

        // Step 1.9 Create QueryDataContainer special to xbmf
        l_strQueryDataContainers =
            this.createQueryDataContainer(
                l_strProductId,
                l_strTaxType,
                l_strDeliveryDiv,
                l_request.productDiv,
                l_request.mutualFrgnMmfDisplayDiv);

        // Step 1.10 Create sortCond
        l_sortKeyUnits = l_request.sortKeys;
        l_strSortCond = this.createSortCond(l_sortKeyUnits);

        // Step 1.11Acquire orderUnits corresponding to conditions
        l_strProductDiv = l_request.productDiv;
        l_strQueryCond = l_strCommonQuery + l_strQuery;

        l_intCommonDataContainer = l_strCommonQueryDataContainers.length;
        l_intQueryDataContainer = l_strQueryDataContainers.length;
        l_intDataContainer = l_intCommonDataContainer + l_intQueryDataContainer;
        l_strQueryCondDataContainers = new String[l_intDataContainer];
        System.arraycopy(
            l_strCommonQueryDataContainers,
            0,
            l_strQueryCondDataContainers,
            0,
            l_intCommonDataContainer);

        System.arraycopy(
            l_strQueryDataContainers,
            0,
            l_strQueryCondDataContainers,
            l_intCommonDataContainer,
            l_intQueryDataContainer);

        l_lisOrderUnits =
            this.getOrderUnits(
                l_strProductDiv,
                l_strQueryCond,
                l_strQueryCondDataContainers,
                l_strSortCond);

        // Step 1.12 If return value of getOrderUnits() != null, execute the following process
        if (l_lisOrderUnits != null)
        {
            // Step 1.12.1Extract only elements of lines to be displayed
            l_intPageIndex = Integer.parseInt(l_request.pageIndex);
            l_intPageSize = Integer.parseInt(l_request.pageSize);
            l_pageIndexInfo = new WEB3PageIndexInfo(l_lisOrderUnits, l_intPageIndex, l_intPageSize);
            l_orderUnits = (OrderUnit[]) l_pageIndexInfo.getArrayReturned(OrderUnit.class);

            // Step 1.12.2 Create mutualRuitoExecutionRefReferenceUnitList
            l_orMutualRuitoOrderExecutionRefUnits =
                this.createMutualRuitoExecutionRefReferenceUnitList(l_orderUnits);
        }

        // Step 1.13 Create response data
        l_response =
            (WEB3AdminORMutualRuitoOrderExecutionRefReferenceResponse) l_request.createResponse();

        // Step 1.14 Property Set
        if (l_lisOrderUnits == null)
        {
            l_response.totalPages = "0";
            l_response.totalRecords = "0";
            l_response.pageIndex = "0";
            l_response.mutualRuitoOrderExecutionRefList = null;
        } else
        {
            l_response.totalPages = WEB3StringTypeUtility.formatNumber(
                l_pageIndexInfo.getTotalPages());
            l_response.totalRecords = WEB3StringTypeUtility.formatNumber(
                l_pageIndexInfo.getTotalRecords());
            l_response.pageIndex = l_request.pageIndex;
            l_response.mutualRuitoOrderExecutionRefList = l_orMutualRuitoOrderExecutionRefUnits;
        }

        // Step 1.15 Return the response
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create�戵���i�ꗗ)<BR>
     * <BR>
     * �����̏��i���{���ɂ��戵�\�ȏ��i�Ǝ���̑g�ݍ��킹���쐬���A<BR>
     * �ԋp����B<B  R>
     * <BR>
     * �P�jArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�p�����[�^.���i���{���.���M���{�t���O == true�̏ꍇ�A<BR>
     * �@@���M�̏��i�敪�Ǝ���敪�̑g�ݍ��킹���쐬����B<BR>
     * �@@�����Ώۂ̎���敪(*1)�ɂ��āA�ȉ��̏������J��Ԃ��B<BR>
     * <BR>
     * �@@�Q�|�P�j�戵���i�C���X�^���X�𐶐�����B<BR>
     * �@@�Q�|�Q�j���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@���i�敪 = "���M"<BR>
     * �@@�@@����敪 = �����Ώۂ̎���敪<BR>
     * �@@�Q�|�R�j��������ArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B<BR>
     * <BR>
     * �@@(*1)���M�̏����Ώێ���敪<BR>
     * �@@�@@�E"�����M��������"<BR>
     * �@@�@@�E"�����M��������"<BR>
     * �@@�@@�E"�����M���抷����"<BR>
     * �@@�@@�E"�����M����W����"<BR>
     * <BR>
     * �R�j�p�����[�^.���i���{���.�ݓ����{�t���O == true�̏ꍇ�A<BR>
     * �@@�ݓ��̏��i�敪�Ǝ���敪�̑g�ݍ��킹���쐬����B<BR>
     * �@@�����Ώۂ̎���敪(*2)�ɂ��āA�ȉ��̏������J��Ԃ��B<BR>
     * <BR>
     * �@@�R�|�P�j�戵���i�C���X�^���X�𐶐�����B<BR>
     * �@@�R�|�Q�j���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@���i�敪 = "�ݓ�"<BR>
     * �@@�@@����敪 = �����Ώۂ̎���敪<BR>
     * �@@�R�|�R�j��������ArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B<BR>
     * <BR>
     * �@@(*2)�ݓ��̏����Ώێ���敪<BR>
     * �@@�@@�E"�ݓ�������"<BR>
     * �@@�@@�E"�ݓ�������"<BR>
     * <BR>
     * �S�j��������ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * <BR>
     * ---------<English>------------<BR>
     * <BR>
     * createTradingProductList<BR>
     * <BR>
     * Make a combination of a handling possible product and a trading by the argument,
     * l_productExecutionInfo, and return it.<BR>
     * <BR>
     * 1)Create ArrayList<BR>
     * <BR>
     * 2)If l_productExecutionInfo.mfFlag == true<BR>
     * �@@Create a combination of productDiv of Def.MF and tradingDiv<BR>
     * �@@Loop the following process for tradingDiv(*1) to be processed<BR>
     * <BR>
     * �@@2-1)Create a WEB3AdminORTradingProductUnit instance<BR>
     * �@@2-2)Set the following properties into the created instance.<BR>
     * �@@�@@productDiv = Def.MF<BR>
     * �@@�@@tradingDiv = tradingDiv to be processed<BR>
     * �@@2-3)Add the instance set into 'Property set' into the created ArrayList.<BR>
     * <BR>
     * �@@(*1)The tradingDiv about mutual funds<BR>
     * �@@�@@�EDef.MF_BUY<BR>
     * �@@�@@�EDef.MF_SELL<BR>
     * <BR>
     * 3)If l_productExecutionInfo.ruitoFlag == true<BR>
     * �@@Create a combonation of productDiv of Def.RUITO and tradingDiv<BR>
     * �@@Loop the following process for tradingDiv(*2) to be processed<BR>
     * <BR>
     * �@@3-1)Create a WEB3AdminORTradingProductUnit instance<BR>
     * �@@3-2)Set the following properties into the created instance.<BR>
     * �@@�@@productDiv = Def.RUITO<BR>
     * �@@�@@tradingDiv = tradingDiv to be processed<BR>
     * �@@3-3)Add the instance set into 'Property set' into the created ArrayList.<BR>
     * <BR>
     * �@@(*2)The tradingDiv about ruito<BR>
     * �@@�@@�EDef.RUITO_BUY<BR>
     * �@@�@@�EDef.RUITO_SELL<BR>
     * <BR>
     * 4)Return return values of the created ArrayList.toArray()<BR>
     * <BR>
     * @@param l_productExecutionInfo - l_productExecutionInfo
     * (���i���{���)<BR>
     * <BR>
     * ���i���{���I�u�W�F�N�g<BR>
     * <BR>
     * l_productExecutionInfo<BR>
     * <BR>
     * @@return WEB3AdminORTradingProductUnit[]
     * @@roseuid 41D001F600D3
     */
    protected WEB3AdminORTradingProductUnit[] createTradingProductList
        (WEB3AdminProductExecutionInfo l_productExecutionInfo)
    {
        final String STR_METHOD_NAME =
            "createTradingProductList(WEB3ProductExecutionInfo)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminORTradingProductUnit l_web3AdminORTradingProductUnit = null;
        WEB3AdminORTradingProductUnit[] l_tradingProductUnits = null;

        // Step 1, creating an arraylist
        ArrayList l_lisTradingProductUnits = new ArrayList();

        // Step 2 If l_productExecutionInfo.mfFlag == true
        if (l_productExecutionInfo.mutualFlag)
        {
            // Step 2-1 Create a WEB3AdminORTradingProductUnit instance
            l_web3AdminORTradingProductUnit = new WEB3AdminORTradingProductUnit();

            // Step 2-2 Set the following properties into the created instance
            l_web3AdminORTradingProductUnit.productDiv = WEB3AdminProductDivDef.MF;
            l_web3AdminORTradingProductUnit.tradingType = String.valueOf(OrderTypeEnum.MF_BUY.intValue());

            // Step 2-3 Add the instance set into 'Property set' into the created ArrayList
            l_lisTradingProductUnits.add(l_web3AdminORTradingProductUnit);
            
			// Step 2-1 Create a WEB3AdminORTradingProductUnit instance
			l_web3AdminORTradingProductUnit = new WEB3AdminORTradingProductUnit();

            // Step 2-2 Set the following properties into the created instance
			l_web3AdminORTradingProductUnit.productDiv = WEB3AdminProductDivDef.MF;
            l_web3AdminORTradingProductUnit.tradingType = String.valueOf(OrderTypeEnum.MF_SELL.intValue());

            // Step 2-3 Add the instance set into 'Property set' into the created ArrayList
            l_lisTradingProductUnits.add(l_web3AdminORTradingProductUnit);
                        
            // Step 2-1 Create a WEB3AdminORTradingProductUnit instance
            l_web3AdminORTradingProductUnit = new WEB3AdminORTradingProductUnit();

            // Step 2-2 Set the following properties into the created instance
            l_web3AdminORTradingProductUnit.productDiv = WEB3AdminProductDivDef.MF;
            l_web3AdminORTradingProductUnit.tradingType = String.valueOf(OrderTypeEnum.MF_SWITCHING.intValue());

            // Step 2-3 Add the instance set into 'Property set' into the created ArrayList
            l_lisTradingProductUnits.add(l_web3AdminORTradingProductUnit);
            
            // Step 2-1 Create a WEB3AdminORTradingProductUnit instance
            l_web3AdminORTradingProductUnit = new WEB3AdminORTradingProductUnit();

            // Step 2-2 Set the following properties into the created instance
            l_web3AdminORTradingProductUnit.productDiv = WEB3AdminProductDivDef.MF;
            l_web3AdminORTradingProductUnit.tradingType = String.valueOf(OrderTypeEnum.MF_RECRUIT.intValue());

            // Step 2-3 Add the instance set into 'Property set' into the created ArrayList
            l_lisTradingProductUnits.add(l_web3AdminORTradingProductUnit);
        }

        // Step 3 If l_productExecutionInfo.ruitoFlag == true
        if (l_productExecutionInfo.ruitoFlag)
        {
            // Step 3-1 Create a WEB3AdminORTradingProductUnit instance
            l_web3AdminORTradingProductUnit = new WEB3AdminORTradingProductUnit();

            // Step 3-2 Set the following properties into the created instance
            l_web3AdminORTradingProductUnit.productDiv = WEB3AdminProductDivDef.RUITO;
            l_web3AdminORTradingProductUnit.tradingType = String.valueOf(OrderTypeEnum.RUITO_BUY.intValue());

            // Step 3-3 Add the instance set into 'Property set' into the created ArrayList
            l_lisTradingProductUnits.add(l_web3AdminORTradingProductUnit);
            
			// Step 3-1 Create a WEB3AdminORTradingProductUnit instance
			l_web3AdminORTradingProductUnit = new WEB3AdminORTradingProductUnit();

            // Step 3-2 Set the following properties into the created instance
			l_web3AdminORTradingProductUnit.productDiv = WEB3AdminProductDivDef.RUITO;
            l_web3AdminORTradingProductUnit.tradingType = String.valueOf(OrderTypeEnum.RUITO_SELL.intValue());

            // Step 3-3 Add the instance set into 'Property set' into the created ArrayList
            l_lisTradingProductUnits.add(l_web3AdminORTradingProductUnit);
        }

        // Step 4, Return return values of the created ArrayList.toArray()
        l_tradingProductUnits = new WEB3AdminORTradingProductUnit[l_lisTradingProductUnits.size()];
        l_tradingProductUnits =
            (WEB3AdminORTradingProductUnit[]) l_lisTradingProductUnits.toArray(
                l_tradingProductUnits);

        log.exiting(STR_METHOD_NAME);
        return l_tradingProductUnits;
    }

    /**
     * �icreate���M�ݓ�����ID���́j<BR>
     * <BR>
     * �،���Ђ���舵���Ă��铊�M�E�ݓ��̖���ID���̂�<BR>
     * �ꗗ���쐬���A�ԋp����B<BR>
     * <BR>
     * �P�j�@@ArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�p�����[�^.���i���{���.���M���{�t���O == true�̏ꍇ�A<BR>
     * �@@�ȉ��̏������s���B<BR>
     * <BR>
     * �@@�Q�|�P�j���M�����e�[�u������A�ȉ��̏����ɊY������<BR>
     * �@@�@@���M����Params�����ID�̏����Ŏ擾����B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h ����<BR>
     * �@@�@@�@@�V�X�e���戵�敪 = "WEBBROKER�V�Ŏ�舵��"<BR>
     * <BR>
     * �@@�Q�|�Q�j�擾�ł����ꍇ�A�擾�������M����Params�̗v�f�����A<BR>
     * �@@�@@�ȉ��̏������J��Ԃ��B<BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�P�j�Ǘ��Ғ������Ɖ����ID���̃C���X�^���X��<BR>
     * �@@�@@�@@��������B<BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�Q�j���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@���������C���X�^���X.����ID = ���M����Params.����ID<BR>
     * �@@�@@�@@���������C���X�^���X.������ = ���M����Params.������<BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�R�j�v���p�e�B�Z�b�g�����C���X�^���X��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �R�j�@@�p�����[�^.���i���{���.�ݓ����{�t���O == true�̏ꍇ�A<BR>
     * �@@�ȉ��̏������s���B<BR>
     * <BR>
     * �@@�R�|�P�j�ݓ������e�[�u������A�p�����[�^.�،���ЃR�[�h��<BR>
     * �@@�@@�Y������ݓ�����Params�����ID�̏����Ŏ擾����B<BR>
     * <BR>
     * �@@�R�|�Q�j�擾�����ݓ�����Params�̗v�f�����A<BR>
     * �@@�@@�ȉ��̏������J��Ԃ��B<BR>
     * <BR>
     * �@@�@@�R�|�Q�|�P�j�Ǘ��Ғ������Ɖ����ID���̃C���X�^���X��<BR>
     * �@@�@@�@@��������B<BR>
     * <BR>
     * �@@�@@�R�|�Q�|�Q�j���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@���������C���X�^���X.����ID = �ݓ�����Params.����ID<BR>
     * �@@�@@�@@���������C���X�^���X.������ = �ݓ�����Params.������<BR>
     * <BR>
     * �@@�@@�R�|�Q�|�R�j�v���p�e�B�Z�b�g�����C���X�^���X��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �S�jArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * <BR>
     * ------<English>----------<BR>
     * <BR>
     * createMutualRuitoProductName<BR>
     * <BR>
     * Create a list of product names of mutual and ruito dealt by an institution and
     * return it.<BR>
     * <BR>
     * 1)Create ArrayList<BR>
     * <BR>
     * 2)If l_productExecutionInfo.mutualFlag == true<BR>
     * �@@Execute the following process.<BR>
     * <BR>
     * �@@2-1)Acquire mutualFundProductParams corresponding to the following
     * conditions<BR>
     *        from mutual_fund_product table in the ascending order of productId<BR>
     * <BR>
     * �@@�@@[Condition]<BR>
     * �@@�@@�@@institution_code = l_strInstitutionCode and<BR>
     * �@@�@@�@@system_handling_div = Def.WEBBROKER�V_TREAT_IT_IN<BR>
     * <BR>
     * �@@2-2)If they are acquired, loop the following process for as many times as the
     * number<BR>
     *         of the elements of the acquired mutualFundProductParams<BR>
     * <BR>
     * �@@�@@2-2-1)Create an instance of WEB3AdminORProductNameSetUnit<BR>
     * <BR>
     * �@@�@@2-2-2)Set the following properties to the created instance<BR>
     * <BR>
     * �@@�@@�@@created instance.producrId = mutualFundProductParams.product_id<BR>
     * �@@�@@�@@created instance.productName = mutualFundProductParams.standard_name<BR>
     * <BR>
     * �@@�@@2-2-3)Add the instance set in 'Property Set' to ArrayList<BR>
     * <BR>
     * 3)If l_productExecutionInfo.ruitoFlag == true<BR>
     * �@@Execute the following process.<BR>
     * <BR>
     * �@@3-1)Acquire ruitoProductParams corresponding to l_strInstitutionCode from<BR>
     *         ruito_product table in the ascending order of productId<BR>
     * <BR>
     * �@@3-2)Loop the following process for as many times as the number of the elements
     * of<BR>
     *         the acquired ruitoProductParams<BR>
     * <BR>
     * �@@�@@3-2-1)Create an instance of WEB3AdminORProductNameSetUnit<BR>
     * <BR>
     * �@@�@@3-2-2)Set the following properties to the created instance<BR>
     * <BR>
     * �@@�@@�@@created instance.producrId = ruitoProductParams.product_id<BR>
     * �@@�@@�@@created instance.productName = ruitoProductParams.standard_name<BR>
     * <BR>
     * �@@�@@3-2-3)Add the instance set in 'Property Set' to ArrayList<BR>
     * <BR>
     * 4)Return return values of ArrayList.toArray()<BR>
     * <BR>
     * @@param l_strInstitutionCode - �i�،���ЃR�[�h�j<BR>
     * <BR>
     * �،���ЃR�[�h<BR>
     * <BR>
     * l_strInstitutionCode<BR>
     * <BR>
     * @@param l_productExecutionInfo - �i���i���{���j<BR>
     * <BR>
     * ���i���{���I�u�W�F�N�g<BR>
     * <BR>
     * l_productExecutionInfo<BR>
     * <BR>
     * l_productExecutionInfo object<BR>
     * <BR>
     * @@throws DataQueryException DataQueryException
     * @@throws DataNetworkException DataNetworkException
     * @@throws IllegalStateException IllegalStateException
     * @@throws IllegalStateException IllegalStateException
     * @@throws WEB3BusinessLayerException WEB3BusinessLayerException
     * @@return WEB3AdminORProductNameSetUnit[]
     * @@roseuid 41D2688E02C1
     */

    protected WEB3AdminORProductNameSetUnit[] createMutualRuitoProductName(
        String l_strInstitutionCode,
        WEB3AdminProductExecutionInfo l_productExecutionInfo)
        throws DataQueryException,
            DataNetworkException,
            IllegalStateException,
            WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME =
            "createMutualRuitoProductName(String, WEB3ProductExecutionInfo )";
        log.entering(STR_METHOD_NAME);

        List l_lisSearchResult = null;
        MutualFundProductParams l_mutualFundProductParam = null;
        RuitoProductParams l_ruitoProductParam = null;
        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        WEB3AdminORProductNameSetUnit l_web3AdminORProductNameSetUnit = null;
        WEB3AdminORProductNameSetUnit[] l_productNameSetUnits = null;
        ArrayList l_lisProductNameSetUnit = null;
        String l_strWhere = null;
        Object[] l_objWhere = null;
        int l_intCount = 0;
        String l_strSortCond = "product_id asc";

        // Step 1, creating an arraylist
        l_lisProductNameSetUnit = new ArrayList();

        // Step 2, If l_productExecutionInfo == true, Execute the following process.
        if (l_productExecutionInfo.mutualFlag)
        {
            // Step 2-1 Creating the where clause & storing it in a Stirng variable
            l_strWhere = " institution_code = ? and system_handling_div = ?";

            // Creating an arrray of objects to store the where clause fields in it
            l_objWhere = new Object[]
                {l_strInstitutionCode, WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN};

            l_lisSearchResult =
                l_queryProcessor.doFindAllQuery(
                    MutualFundProductRow.TYPE,
                    l_strWhere,
                    l_strSortCond,
                    null,
                    l_objWhere);
            l_intCount = l_lisSearchResult.size();

            // Step 2-2,loop through the elements of the acquired mutualFundProductParams
            for (int i = 0; i < l_intCount; i++)
            {
                // Step 2-2-1, Create an instance of WEB3AdminORProductNameSetUnit
                l_mutualFundProductParam = new MutualFundProductParams();
                l_mutualFundProductParam = (MutualFundProductParams) l_lisSearchResult.get(i);

                // Step 2-2-2, Set the following properties to the created instance
                l_web3AdminORProductNameSetUnit = new WEB3AdminORProductNameSetUnit();
                l_web3AdminORProductNameSetUnit.productId =
                    WEB3StringTypeUtility.formatNumber(l_mutualFundProductParam.product_id);
                l_web3AdminORProductNameSetUnit.productName =
                    l_mutualFundProductParam.standard_name;

                // Step 2-2-3, Add the created instance to ArrayList
                l_lisProductNameSetUnit.add(l_web3AdminORProductNameSetUnit);
            }
        }

        // Step 3, If l_productExecutionInfo == true,Execute the following process
        if (l_productExecutionInfo.ruitoFlag)
        {
            l_strWhere = "institution_code = ? ";

            // Creating an arrray of objects to store the where clause fields in it
            l_objWhere = new Object[] {l_strInstitutionCode};

            /* Step 3-1,Acquire ruitoProductParams corresponding to l_strInstitutionCode from
                  ruito_product table in the ascending order of productId   */
            l_lisSearchResult =
                l_queryProcessor.doFindAllQuery(
                    RuitoProductRow.TYPE,
                    l_strWhere,
                    l_strSortCond,
                    null,
                    l_objWhere);

            l_intCount = l_lisSearchResult.size();

            // Step 3-2, Loop  through the number of elements of acquired ruitoProductParams
            for (int i = 0; i < l_intCount; i++)
            {
                // Step 3-2-1,Create an instance of WEB3AdminORProductNameSetUnit
                l_ruitoProductParam = new RuitoProductParams();
                l_ruitoProductParam = (RuitoProductParams) l_lisSearchResult.get(i);

                // Step 3-2-2, Set the following properties to the created instance
                l_web3AdminORProductNameSetUnit = new WEB3AdminORProductNameSetUnit();
                l_web3AdminORProductNameSetUnit.productId =
                    WEB3StringTypeUtility.formatNumber(l_ruitoProductParam.product_id);
                l_web3AdminORProductNameSetUnit.productName = l_ruitoProductParam.standard_name;

                // Step 3-2-3, Add the instance set in 'Property Set' to ArrayList
                l_lisProductNameSetUnit.add(l_web3AdminORProductNameSetUnit);
            }
        }

        // Step 4 ,Return return values of ArrayList.toArray()
        if (l_lisProductNameSetUnit.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        l_productNameSetUnits = new WEB3AdminORProductNameSetUnit[l_lisProductNameSetUnit.size()];
        l_productNameSetUnits =
            (WEB3AdminORProductNameSetUnit[]) l_lisProductNameSetUnit.toArray(
                l_productNameSetUnits);
        log.exiting(STR_METHOD_NAME);
        return l_productNameSetUnits;
    }

    /**
     * (create��������������)<BR>
     * ����������������쐬����B<BR>
     * <BR>
     * �P�j��������������C���X�^���X(�FString)�𐶐�����B<BR>
     * <BR>
     * �Q�j�p�����[�^.����ID != null�̏ꍇ�A<BR>
     * �@@����ID����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and product_id = ? "<BR>
     * <BR>
     * �R�j�p�����[�^.�����敪 != null�̏ꍇ�A<BR>
     * �@@�ŋ敪����������������ɒǉ�����B<BR>
     * <BR>
     * �@@[�p�����[�^.�����敪 == "���"�̏ꍇ]<BR>
     * �@@�@@�������������� += "and tax_type = ? "<BR>
     * �@@[�p�����[�^.�����敪 == "����"�̏ꍇ]<BR>
     * �@@�@@�������������� += "and tax_type in (?, ?) "<BR>
     * <BR>
     * �S�j�p�����[�^.��n���@@ != null�̏ꍇ�A<BR>
     * �@@��n���@@����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and payment_method = ? "<BR>
     * <BR>
     * -------- ���M�Ǝ��̏��� --------<BR>
     * <BR>
     * �T) �p�����[�^.���i�敪��"���M"�̏ꍇ<BR>
     * <BR>
     *   5-1�j �p�����[�^.���M��O��MMF�\���敪 == "���M�̂�"�̏ꍇ�A<BR>
     *   �@@     ���M�^�C�v����������������ɒǉ�����B<BR>
     * <BR>
     *  �@@     �������������� += "and fund_type <> ? "<BR>
     * <BR>
     *   5-2�j �p�����[�^.���M��O��MMF�\���敪 == "�O��MMF�̂�"�̏ꍇ<BR>
     *          ���M�^�C�v����������������ɒǉ�����B<BR>
     * <BR>
     *  �@@     �������������� += "and fund_type = ? "<BR>
     * <BR>
     * --------------------------------<BR>
     * <BR>
     * �U�j�쐬�������������������ԋp����B<BR>
     * <BR>
     * -------<English>----------------------<BR>
     * <BR>
     * createQueryString<BR>
     * <BR>
     * create the condition string for search<BR>
     * <BR>
     * 1)Create an instance of l_strQueryCond<BR>
     * <BR>
     * 2)If l_strProductId != null<BR>
     * �@@Add l_strProductId to l_strQueryCond<BR>
     * <BR>
     * �@@l_strQueryCond += "and product_id = ? "<BR>
     * <BR>
     * 3)If l_taxType != null<BR>
     * �@@Add l_taxType to l_strQueryCond<BR>
     * <BR>
     * �@@If [l_taxType == Def.NORMAL]<BR>
     * �@@�@@l_strQueryCond += "and tax_type = ? "<BR>
     * �@@If [l_taxType == Df.SPECIAL]<BR>
     * �@@�@@l_strQueryCond += "and tax_type in (?, ?) "<BR>
     * <BR>
     * 4)If l_deliveryDiv != null<BR>
     * �@@Add l_deliveryDiv to l_strQueryCond<BR>
     * <BR>
     * �@@l_strQueryCond += "and payment_method = ? "<BR>
     * <BR>
     * 5)Return the created l_strQueryCond<BR>
     * <BR>
     * @@param l_strProductId - ����ID<BR>
     * <BR>
     * ����ID<BR>
     * <BR>
     * l_strProductId<BR>
     * <BR>
     * @@param l_strTaxType - �����敪<BR>
     * <BR>
     * �����敪<BR>
     * <BR>
     * l_strTaxType<BR>
     * <BR>
     * @@param l_strDeliveryDiv - ��n���@@<BR>
     * <BR>
     * ��n���@@<BR>
     * <BR>
     * l_strDeliveryDiv<BR>
     * <BR>
     * @@param l_strProductType - ���i�敪<BR>
     * <BR>
     * ���i�敪<BR>
     * <BR>
     * l_strProductType<BR>
     * <BR>
     * @@param l_strMutualFrgnMmfDisplayDiv - ���M��O��MMF�\���敪<BR>
     * <BR>
     * ���M��O��MMF�\���敪<BR>
     * <BR>
     * �\���Ώۂ̖������A���M��O��MMF�Ő؂�ւ��邽�߂̋敪<BR>
     * <BR>
     * 0:���M�̂�<BR>
     * 1:�O��MMF�̂�<BR>
     * 2:����<BR>
     * <BR>
     * ��null�̏ꍇ�A�u2:�����v�Ƃ���<BR>
     * <BR>
     * l_strMutualFrgnMmfDisplayDiv<BR>
     * <BR>
     * @@return java.lang.String
     * @@roseuid 41D001F60112
     */
    protected String createQueryString(
        String l_strProductId,
        String l_strTaxType,
        String l_strDeliveryDiv,
        String l_strProductType,
        String l_strMutualFrgnMmfDisplayDiv)
    {
        final String STR_METHOD_NAME = "createQueryString(String, String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        String l_strQuery = null;

        // Step 1Create an instance of l_strQueryCond
        StringBuffer l_sbQueryCond = new StringBuffer("");

        // Step 2 If l_strProductId != null
        if (l_strProductId != null)
        {
            l_sbQueryCond.append(" and product_id = ? ");
        }

        // Step 3 If l_taxType != null
        if (l_strTaxType != null)
        {
            if (WEB3TaxTypeDef.NORMAL.equals(l_strTaxType))
            {
                l_sbQueryCond.append(" and tax_type = ? ");
            }
            if (WEB3TaxTypeDef.SPECIAL.equals(l_strTaxType))
            {
                l_sbQueryCond.append(" and tax_type in (?, ?) ");
            }
        }

        // Step 4 If l_deliveryDiv != null
        if (l_strDeliveryDiv != null)
        {
            l_sbQueryCond.append(" and payment_method = ? ");
        }

        //5) �p�����[�^.���i�敪��"���M"�̏ꍇ
        if (WEB3AdminProductDivDef.MF.equals(l_strProductType))
        {
            //5-1�j �p�����[�^.���M��O��MMF�\���敪 == "���M�̂�"�̏ꍇ�A
            //���M�^�C�v����������������ɒǉ�����B
            //�������������� += "and fund_type <> ? "
            if (WEB3MutualFrgnMmfDisplayDivDef.MUTUAL_FUND.equals(l_strMutualFrgnMmfDisplayDiv))
            {
                l_sbQueryCond.append(" and fund_type <> ? ");
            }
            //  5-2�j �p�����[�^.���M��O��MMF�\���敪 == "�O��MMF�̂�"�̏ꍇ
            //���M�^�C�v����������������ɒǉ�����B
            //�������������� += "and fund_type = ? "
            else if (WEB3MutualFrgnMmfDisplayDivDef.FRGN_MMF.equals(l_strMutualFrgnMmfDisplayDiv))
            {
                l_sbQueryCond.append(" and fund_type = ? ");
            }
        }

        l_strQuery = l_sbQueryCond.toString();
        // Step  5 Return the created l_strQueryCond
        log.exiting(STR_METHOD_NAME);
        return l_strQuery;
    }

    /**
     * �icreate���������f�[�^�R���e�i�j<BR>
     * <BR>
     * ���������f�[�^�R���e�i���쐬����B<BR>
     * <BR>
     * �P�jArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�p�����[�^.����ID != null�̏ꍇ�A<BR>
     * �@@����ID�𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �R�j�p�����[�^.�����敪 != null�̏ꍇ�A<BR>
     * �@@�ȉ��̏����ɂ��A�ŋ敪�𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@[�p�����[�^.�����敪 == "���"�̏ꍇ]<BR>
     * �@@�@@�ȉ��̒l��ǉ�<BR>
     * �@@�@@�ETaxTypeEnum.���<BR>
     * <BR>
     * �@@[�p�����[�^.�����敪 == "����"�̏ꍇ]<BR>
     * �@@�@@�ȉ��̒l��ǉ�<BR>
     * �@@�@@�ETaxTypeEnum.����<BR>
     * �@@�@@�ETaxTypeEnum.���肩���򒥎�<BR>
     * <BR>
     * �S�j�p�����[�^.��n���@@ != null�̏ꍇ�A<BR>
     * �@@�p�����[�^.��n���@@�𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * -------- ���M�Ǝ��̏��� --------<BR>
     * <BR>
     * �T) �p�����[�^.���i�敪��"���M"�̏ꍇ<BR>
     * <BR>
     * 5-1) �p�����[�^.���M��O��MMF�\���敪��"���M�̂�"�܂���"�O��MMF�̂�"�̏ꍇ�A��������ArrayList�Ɉȉ���ǉ�����B<BR>
     *     �EMutualFundTypeEnum.�O��MMF<BR>
     * <BR>
     * --------------------------------<BR>
     * <BR>
     * �U�j��������ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * <BR>
     * -----<English>--------------------------<BR>
     * <BR>
     * createQueryDataContainer<BR>
     * <BR>
     * create queryDataContainer<BR>
     * <BR>
     * 1)Create ArrayList<BR>
     * <BR>
     * 2)If l_strProductId != null<BR>
     * �@@Add l_strProductId to the created ArrayList<BR>
     * <BR>
     * 2)If l_taxType != null<BR>
     * �@@Add l_taxType to the created ArrayList according to the following
     * conditions<BR>
     * <BR>
     * �@@If [l_taxType == Def.NORMAL]<BR>
     * �@@�@@Add the following value<BR>
     * �@@�@@�ETaxTypeEnum.Def.NORMAL<BR>
     * <BR>
     * �@@If [l_taxType == Def.SPECIAL]<BR>
     * �@@�@@Add the following value<BR>
     * �@@�@@�ETaxTypeEnum.Def.SPECIAL<BR>
     * �@@�@@�ETaxTypeEnum.Def.SPECIAL_WITHHOLD<BR>
     * <BR>
     * 4)If l_deliveryDiv != null<BR>
     * �@@Add l_deliveryDiv to the created ArrayList<BR>
     * <BR>
     * 5)Return the return value of the created ArrayList.toArray()<BR>
     * <BR>
     * @@param l_strProductId - ����ID<BR>
     * <BR>
     * ����ID<BR>
     * <BR>
     * l_strProductId<BR>
     * <BR>
     * @@param l_strTaxType - �i�����敪�j<BR>
     * <BR>
     * �����敪<BR>
     * <BR>
     * l_strTaxType<BR>
     * <BR>
     * @@param l_strDeliveryDiv - �i��n���@@�j<BR>
     * <BR>
     * ��n���@@<BR>
     * <BR>
     * l_strDeliveryDiv<BR>
     * <BR>
     * @@param l_strProductType - ���i�敪<BR>
     * <BR>
     * ���i�敪<BR>
     * <BR>
     * l_strProductType<BR>
     * <BR>
     * @@param l_strMutualFrgnMmfDisplayDiv - ���M��O��MMF�\���敪<BR>
     * <BR>
     * ���M��O��MMF�\���敪<BR>
     * <BR>
     * l_strMutualFrgnMmfDisplayDiv<BR>
     * <BR>
     * @@return String[]
     * @@roseuid 41D001F60131
     */
    protected String[] createQueryDataContainer(
        String l_strProductId,
        String l_strTaxType,
        String l_strDeliveryDiv,
        String l_strProductType,
        String l_strMutualFrgnMmfDisplayDiv)
    {
        final String STR_METHOD_NAME = "createQueryDataContainer(String , String , String , String , String)";
        log.entering(STR_METHOD_NAME);

        String[] l_strDataContainers = null;

        // Step 1 Create ArrayList
        ArrayList l_lisDataContainer = new ArrayList();

        // Step 2 If l_strProductId != null
        if (l_strProductId != null)
        {
            l_lisDataContainer.add(l_strProductId);
        }

        // Step 3 If l_taxType != null
        if (l_strTaxType != null)
        {
            if (WEB3TaxTypeDef.NORMAL.equals(l_strTaxType))
            {
                l_lisDataContainer.add(
                    WEB3StringTypeUtility.formatNumber(TaxTypeEnum.IntValues.NORMAL));
            }
            if (WEB3TaxTypeDef.SPECIAL.equals(l_strTaxType))
            {
                l_lisDataContainer.add(
                    WEB3StringTypeUtility.formatNumber(TaxTypeEnum.IntValues.SPECIAL));
                l_lisDataContainer.add(
                    WEB3StringTypeUtility.formatNumber(TaxTypeEnum.IntValues.SPECIAL_WITHHOLD));
            }
        }

        // Step 4 If l_deliveryDiv != null
        if (l_strDeliveryDiv != null)
        {
            l_lisDataContainer.add(l_strDeliveryDiv);
        }

        //5) �p�����[�^.���i�敪��"���M"�̏ꍇ
        if (WEB3AdminProductDivDef.MF.equals(l_strProductType))
        {
            //5-1) �p�����[�^.���M��O��MMF�\���敪��"���M�̂�"�܂���"�O��MMF�̂�"�̏ꍇ�A��������ArrayList�Ɉȉ���ǉ�����B
            //  �EMutualFundTypeEnum.�O��MMF
            if (WEB3MutualFrgnMmfDisplayDivDef.MUTUAL_FUND.equals(l_strMutualFrgnMmfDisplayDiv)
                || WEB3MutualFrgnMmfDisplayDivDef.FRGN_MMF.equals(l_strMutualFrgnMmfDisplayDiv))
            {
                l_lisDataContainer.add(MutualFundTypeEnum.FOREIGN_MMF.intValue() + "");
            }
        }

        l_strDataContainers = new String[l_lisDataContainer.size()];
        l_strDataContainers = (String[]) l_lisDataContainer.toArray(l_strDataContainers);

        // Step 5 Return the return value of the created ArrayList.toArray()
        log.exiting(STR_METHOD_NAME);
        return l_strDataContainers;
    }

    /**
     * (create�\�[�g����)<BR>
     * <BR>
     * �\�[�g�������쐬����B<BR>
     * <BR>
     * �P�j�p�����[�^.�\�[�g�L�[ == null�̏ꍇ�A<BR>
     * �@@�@@"����ID ����"�̃\�[�g������ԋp����B<BR>
     * <BR>
     * �Q�j�\�[�g����������(�FString)���쐬����B<BR>
     * <BR>
     * �R�j�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B<BR>
     * �@@�R�|�P�j�\�[�g�L�[.�L�[���ڂ�Ή�����񕨗����ɕϊ����A<BR>
     * �@@�@@�쐬�����\�[�g����������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�E�u���X�R�[�h�v�@@���@@���M�����P��.���XID<BR>
     * �@@�@@�E�u�ڋq�R�[�h�v�@@���@@���M�����P��.����ID<BR>
     * �@@�@@�E�u�����R�[�h�v�@@���@@���M�����P��.����ID<BR>
     * �@@�@@�E�u����敪�v�@@���@@���M�����P��.�������<BR>
     * �@@�@@�E�u�������ԁv�@@���@@���M�����P��.�󒍓���<BR>
     * �@@�@@�E�u�������v�@@���@@���M�����P��.������<BR>
     * �@@�@@�E�u��n���v�@@���@@���M�����P��.��n��<BR>
     * �@@�@@�E�u���҃R�[�h(SONAR)�v�@@���@@�����P��.���҃R�[�h(SONAR)<BR>
     * <BR>
     * �@@�R�|�Q�j�\�[�g�L�[.�����^�~���ɑΉ�����擾����<BR>
     * �@@�@@(asc or desc)���\�[�g����������ɒǉ�����B<BR>
     * <BR>
     * �S�j�\�[�g���������ɁA�����P�ʃe�[�u��.�X�V���t�������w��ŕt��<BR>
     * <BR>
     * �T�j�쐬�����\�[�g�����������ԋp����B<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * createSortCond<BR>
     * <BR>
     * Create sortCondition<BR>
     * 1)Create sortCond(:String)<BR>
     * <BR>
     * 2)Loop the following process for as many times as the number of the elements of
     * sortKeys<BR>
     * �@@2-1)Convert sortKeys.ketItem to the corresponding field names, and<BR>
     * �@@�@@ add them to the created sortCond string<BR>
     * <BR>
     * �@@�@@�EbranchCode�@@���@@mutual_fund_order_unit.branch_id<BR>
     * �@@�@@�EaccountCode�@@���@@mutual_fund_order_unit.account_id<BR>
     * �@@�@@�EproductCode�@@���@@mutual_fund_order_unit.product_id<BR>
     * �@@�@@�EtradingDiv�@@���@@mutual_fund_order_unit.order_type<BR>
     * �@@�@@�EorderDate�@@���@@mutual_fund_order_unit.received_date_time<BR>
     * �@@�@@�EorderBizDate�@@���@@mutual_fund_order_unit.biz_date<BR>
     * �@@�@@�EdeliveryDate�@@���@@mutual_fund_order_unit.delivery_date<BR>
     * <BR>
     * �@@2-2)Acquired order by sortKeys.ascDesc<BR>
     * �@@�@@Add (asc or desc) to sortCond string<BR>
     * <BR>
     * 3)Add order_unit.last_updated_timestamp to the end of sortCond in ascending
     * order<BR>
     * <BR>
     * 4)Return the created sortCond string<BR>
     * <BR>
     * @@param l_sortKeys - (�\�[�g�L�[)<BR>
     * <BR>
     * �\�[�g�L�[<BR>
     * <BR>
     * l_sortKeys<BR>
     * <BR>
     * @@return java.lang.String
     * @@roseuid 41D001F60150
     */
    protected String createSortCond(WEB3AdminOROrderExecutionSortKeyUnit[] l_sortKeys)
    {
        final String STR_METHOD_NAME =
            "createSortCond(WEB3AdminOROrderExecutionSortKeyUnit[])";
        log.entering(STR_METHOD_NAME);

        StringBuffer l_sbSortCond = null;
        String l_strSortCond = null;
        int l_intSortKeysCnt = l_sortKeys.length;

        // Step 2 Create sortCond
        l_sbSortCond = new StringBuffer("");

        // Step 3 Loop the following process for as many times as the number of the elements
        for (int i = 0; i < l_intSortKeysCnt; i++)
        {
            /* If sortCondList is not empty(""), add ","(comma) to sortCondList
                before processing. */
            if (!l_sbSortCond.toString().equals(""))
            {
                l_sbSortCond.append(", ");
            }

            // Step 3.1Add conditions to created sortCondList based on the values of keyItem
            if (l_sortKeys[i].keyItem.equals(WEB3AdminKeyItemDef.BRANCH_CODE))
            {
                l_sbSortCond.append("branch_id");
            }
            if (l_sortKeys[i].keyItem.equals(WEB3AdminKeyItemDef.ACCOUNT_CODE))
            {
                l_sbSortCond.append("substr(account_id,9,6)");
            }
            if (l_sortKeys[i].keyItem.equals(WEB3AdminKeyItemDef.PRODUCT_CODE))
            {
                l_sbSortCond.append("product_id");
            }
            if (l_sortKeys[i].keyItem.equals(WEB3AdminKeyItemDef.TRADING_TYPE))
            {
                l_sbSortCond.append("order_type");
            }
            if (l_sortKeys[i].keyItem.equals(WEB3AdminKeyItemDef.ORDER_DATE))
            {
                l_sbSortCond.append("received_date_time");
            }
            if (l_sortKeys[i].keyItem.equals(WEB3AdminKeyItemDef.ORDER_BIZ_DATE))
            {
                l_sbSortCond.append("biz_date");
            }
            if (l_sortKeys[i].keyItem.equals(WEB3AdminKeyItemDef.DELIVERY_DATE))
            {
                l_sbSortCond.append("delivery_date");
            }
            if (l_sortKeys[i].keyItem.equals(WEB3AdminKeyItemDef.SONAR_TRADER_CODE))
            {
                l_sbSortCond.append("sonar_trader_code");
            }

            // Step 3.2: Add ascDesc to sortCondList according to the value of sortKeys.ascDesc
            if (l_sortKeys[i].ascDesc.equals(WEB3AscDescDef.ASC))
            {
                l_sbSortCond.append(" asc");
            }
            if (l_sortKeys[i].ascDesc.equals(WEB3AscDescDef.DESC))
            {
                l_sbSortCond.append(" desc");
            }
        }

        // Step 4 Add info_order_unit.last_updated_timestamp to the end of sortCond in ascending
        l_sbSortCond.append(" , last_updated_timestamp asc");
        l_strSortCond = l_sbSortCond.toString();
        log.exiting(STR_METHOD_NAME);
        return l_strSortCond;
    }

    /**
     * �iget�����P�ʈꗗ�j<BR>
     * <BR>
     * �����̏��i�敪�ɊY�����钍���P��Params�̈ꗗ��ԋp����B<BR>
     * �����i�敪��null�̏ꍇ�́A���M�E�ݓ������̒����P�ʂ��擾����B<BR>
     * <BR>
     * �P�jArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�p�����[�^.���i�敪 == null�̏ꍇ�A<BR>
     * �@@�ȉ��̏������s���B<BR>
     * <BR>
     * �@@�Q�|�P�j���M�����P��Params���擾����B<BR>
     * �@@�@@QueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@arg0�F�@@���M�����P��Row.TYPE<BR>
     * �@@�@@�@@arg1�F�@@�p�����[�^.��������������<BR>
     * �@@�@@�@@arg2�F�@@�p�����[�^.�\�[�g����<BR>
     * �@@�@@�@@arg3�F�@@null<BR>
     * �@@�@@�@@arg4�F�@@�p�����[�^.���������f�[�^�R���e�i<BR>
     * <BR>
     * �@@�@@�������ʂ��擾�ł����ꍇ�A�������ʂ�ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@�Q�|�Q�j�ݓ������P��Params���擾����B<BR>
     * �@@�@@QueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@arg0�F�@@�ݓ������P��Row.TYPE<BR>
     * �@@�@@�@@arg1�F�@@�p�����[�^.��������������<BR>
     * �@@�@@�@@arg2�F�@@�p�����[�^.�\�[�g����<BR>
     * �@@�@@�@@arg3�F�@@null<BR>
     * �@@�@@�@@arg4�F�@@�p�����[�^.���������f�[�^�R���e�i<BR>
     * <BR>
     * �@@�@@�������ʂ��擾�ł����ꍇ�A�������ʂ�ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �R�j�p�����[�^.���i�敪 != null�̏ꍇ�A���i�敪�ɊY������<BR>
     * �@@�����P�ʂ��擾����B<BR>
     * �@@QueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@arg0�F�@@<BR>
     * �@@�@@�@@[�p�����[�^.���i�敪 == "���M"�̏ꍇ]<BR>
     * �@@�@@�@@�@@���M�����P��Row.TYPE���Z�b�g����B<BR>
     * �@@�@@�@@[�p�����[�^.���i�敪 == "�ݓ�"�̏ꍇ]<BR>
     * �@@�@@�@@�@@�ݓ������P��Row.TYPE���Z�b�g����B<BR>
     * �@@�@@arg1�F�@@�p�����[�^.��������������<BR>
     * �@@�@@arg2�F�@@�p�����[�^.�\�[�g����<BR>
     * �@@�@@arg3�F�@@null<BR>
     * �@@�@@arg4�F�@@�p�����[�^.���������f�[�^�R���e�i<BR>
     * <BR>
     * �@@�@@�������ʂ��擾�ł����ꍇ�A�������ʂ�ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �S�jArrayList��ԋp����B<BR>
     * �@@��ArrayList�̗v�f����0�̏ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * -----<English>----------------<BR>
     * <BR>
     * getOrderUnits<BR>
     * <BR>
     * Return the list of orderUnits corresponding to the argument, l_productDiv<BR>
     * ��If l_productDiv is null, acquire l_orderUnit of both mutual and ruito<BR>
     * <BR>
     * �P�jCreate ArrayList<BR>
     * <BR>
     * 2)If l_productDiv == null<BR>
     * �@@execute the following processes<BR>
     * <BR>
     * �@@2-1)Acquire mutualFundOrderUnitParams<BR>
     * �@@�@@Call QueryProcessor.doFindAllQuery() method<BR>
     * <BR>
     * �@@�@@[Parameter set into doFindAllQuery()]<BR>
     * �@@�@@arg0: l_mutualFundOrderUnitRow.TYPE<BR>
     * �@@�@@arg1: l_strQueryCond<BR>
     * �@@�@@arg2: l_strSortCond<BR>
     * �@@�@@arg3: null<BR>
     * �@@�@@arg4: l_strQueryCondDataContainer<BR>
     * <BR>
     * �@@�@@If it is able to acquire the search result, convert result to MutualFundOrderUnit then
     *     add it to ArrayList<BR>
     * <BR>
     * �@@2-2)Acquire ruitoOrderUnitParams<BR>
     * �@@�@@Call QueryProcessor.doFindAllQuery() method<BR>
     * <BR>
     * �@@�@@[Parameter set into doFindAllQuery()]<BR>
     * �@@  arg0: l_ruitoOrderUnitRow.TYPE<BR>
     * �@@�@@arg1: l_strQueryCond<BR>
     * �@@�@@arg2: l_strSortCond<BR>
     * �@@  arg3: null<BR>
     * �@@�@@arg4: l_strQueryCondDataContainer<BR>
     * <BR>
     * �@@�@@If it is able to acquire the search result, convert result to RuitoOrderUnit then
     *     add it to ArrayList<BR>
     * <BR>
     * 3)If l_productDiv != null,<BR>
     * �@@acquire l_orderUnit corresponding to l_productDiv<BR>
     * �@@Call QueryProcessor.doFindAllQuery() method<BR>
     * <BR>
     * �@@[Parameter set into doFindAllQuery()]<BR>
     * �@@�@@arg0:<BR>
     * �@@�@@�@@[l_productDiv == Def.MF]<BR>
     * �@@�@@�@@�@@Set mutualFundOrderUnitRow.TYPE<BR>
     * �@@�@@�@@[l_productDiv == Def.RUITO]<BR>
     * �@@�@@�@@�@@Set ruitoOrderUnitRow.TYPE<BR>
     * �@@�@@arg1: l_strQueryCond<BR>
     * �@@�@@arg2: l_strSortCond<BR>
     * �@@�@@arg3: null<BR>
     * �@@�@@arg4: l_strQueryCondDataContainer<BR>
     * <BR>
     * �@@�@@If it is able to acquire the search result, convert result to
     *     either MutualOrderUnit or RuitoOrderUnit depends upon l_productDiv then
     *     add it to ArrayList<BR>
     * <BR>
     * 4)Return ArrayList<BR>
     * �@@��If the number of the elements of ArrayList is 0, set null<BR>
     * <BR>
     * @@param l_strProductDiv - ���i�敪<BR>
     * <BR>
     * ���i�敪<BR>
     * <BR>
     * l_strProductDiv<BR>
     * <BR>
     * @@param l_strQueryCond - (��������������)<BR>
     * <BR>
     * ��������������<BR>
     * <BR>
     * l_strQueryCond<BR>
     * <BR>
     * @@param l_strQueryCondDataContainers - (���������f�[�^�R���e�i)<BR>
     * <BR>
     * ���������f�[�^�R���e�i<BR>
     * <BR>
     * l_strQueryCondDataContainer<BR>
     * <BR>
     * @@param l_strSortCond - l_strSortCond
     * (�\�[�g����)<BR>
     * <BR>
     * �\�[�g����<BR>
     * <BR>
     * l_strSortCond<BR>
     * <BR>
     * @@throws DataQueryException DataQueryException
     * @@throws DataNetworkException DataNetworkException
     * @@throws IllegalStateException IllegalStateException
     * @@return ArrayList
     * @@roseuid 41D001F6018F
     */
    protected ArrayList getOrderUnits(
        String l_strProductDiv,
        String l_strQueryCond,
        String[] l_strQueryCondDataContainers,
        String l_strSortCond)
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        final String STR_METHOD_NAME = "getOrderUnits(String, String, String[], String)";
        log.entering(STR_METHOD_NAME);

        List l_lisMutual = null;
        List l_lisRuito = null;
        MutualFundOrderManager l_mutualOrderManager = null;
        MutualFundOrderUnit l_mutualOrderUnit = null;
        RuitoOrderManager l_ruitoOrderManager = null;
        RuitoOrderUnit l_ruitoOrderUnit = null;
        int l_intCount = 0;

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        // Step �PCreate ArrayList
        ArrayList l_lisOrderUnit = new ArrayList();

        // Step 2 If l_productDiv == null
        if (l_strProductDiv == null)
        {
            // Step 2-1Acquire mutualFundOrderUnitParams
            l_lisMutual =
                l_queryProcessor.doFindAllQuery(
                    MutualFundOrderUnitRow.TYPE,
                    l_strQueryCond,
                    l_strSortCond,
                    null,
                    l_strQueryCondDataContainers);
            l_mutualOrderManager =
                (MutualFundOrderManager) l_finApp
                    .getTradingModule(ProductTypeEnum.MUTUAL_FUND)
                    .getOrderManager();
            l_intCount = l_lisMutual.size();
            for (int i = 0; i < l_intCount; i++)
            {
                l_mutualOrderUnit =
                    (MutualFundOrderUnit) l_mutualOrderManager.toOrderUnit(
                        (MutualFundOrderUnitParams) l_lisMutual.get(i));
                l_lisOrderUnit.add(l_mutualOrderUnit);
            }

			l_queryProcessor = Processors.getDefaultProcessor();
            // Step 2-2 Acquire ruitoOrderUnitParams
            l_lisRuito =
                l_queryProcessor.doFindAllQuery(
                    RuitoOrderUnitRow.TYPE,
                    l_strQueryCond,
                    l_strSortCond,
                    null,
                    l_strQueryCondDataContainers);
            l_ruitoOrderManager =
                (RuitoOrderManager) l_finApp
                    .getTradingModule(ProductTypeEnum.RUITO)
                    .getOrderManager();
            l_intCount = l_lisRuito.size();
            for (int i = 0; i < l_intCount; i++)
            {
                l_ruitoOrderUnit =
                    (RuitoOrderUnit) l_ruitoOrderManager.toOrderUnit(
                        (RuitoOrderUnitParams) l_lisRuito.get(i));
                l_lisOrderUnit.add(l_ruitoOrderUnit);
            }
        } else
        {
            if (WEB3AdminProductDivDef.MF.equals(l_strProductDiv))
            {
                l_lisMutual =
                    l_queryProcessor.doFindAllQuery(
                        MutualFundOrderUnitRow.TYPE,
                        l_strQueryCond,
                        l_strSortCond,
                        null,
                        l_strQueryCondDataContainers);
                l_mutualOrderManager =
                    (MutualFundOrderManager) l_finApp
                        .getTradingModule(ProductTypeEnum.MUTUAL_FUND)
                        .getOrderManager();
                l_intCount = l_lisMutual.size();
                for (int i = 0; i < l_intCount; i++)
                {
                    l_mutualOrderUnit =
                        (MutualFundOrderUnit) l_mutualOrderManager.toOrderUnit(
                            (MutualFundOrderUnitParams) l_lisMutual.get(i));
                    l_lisOrderUnit.add(l_mutualOrderUnit);
                }
            }
            if (WEB3AdminProductDivDef.RUITO.equals(l_strProductDiv))
            {
                l_lisRuito =
                    l_queryProcessor.doFindAllQuery(
                        RuitoOrderUnitRow.TYPE,
                        l_strQueryCond,
                        l_strSortCond,
                        null,
                        l_strQueryCondDataContainers);
                l_ruitoOrderManager =
                    (RuitoOrderManager) l_finApp
                        .getTradingModule(ProductTypeEnum.RUITO)
                        .getOrderManager();
                l_intCount = l_lisRuito.size();
                for (int i = 0; i < l_intCount; i++)
                {
                    l_ruitoOrderUnit =
                        (RuitoOrderUnit) l_ruitoOrderManager.toOrderUnit(
                            (RuitoOrderUnitParams) l_lisRuito.get(i));
                    l_lisOrderUnit.add(l_ruitoOrderUnit);
                }
            }
        }

        // Step 4 Return ArrayList
        log.exiting(STR_METHOD_NAME);
        if (l_lisOrderUnit.size() == 0)
        {
            return null;
        }
        return l_lisOrderUnit;
    }

    /**
     * �����̒����P�ʈꗗ���A���M�ݓ��������Ɖ�Unit�̈ꗗ��
     * �쐬���A�ԋp����B
     *
     * �V�[�P���X�}
     * �u(�Ǘ��ғ��M�ݓ��������Ɖ�T�[�r�X)create���M�ݓ��������Ɖ�Unit�ꗗ�v
     * �Q��
     * <BR>
     * ----<English>-----------<BR>
     * <BR>
     * createMutualRuitoExecutionRefReferenceUnitList<BR>
     * <BR>
     * Create munualRuitoOrderExecutionRefList from the argument, l_orderUnits and
     * return it.
     * <BR>
     * Refer to the sequence diagram "(administrator: mutual ruito order execution
     * inquiry service)createMutualRuitoExecutionRefReferenceUnitList"
     * <BR>
     * @@param l_orderUnits - (�����P�ʈꗗ)<BR>
     * <BR>
     * �敨OP�����P�ʂ̔z��<BR>
     * <BR>
     * l_orderUnits<BR>
     * <BR>
     * An array of futures option order unit<BR>
     * <BR>
     * @@throws NotFoundException NotFoundException
     * @@throws WEB3BaseException WEB3BaseException
     * @@return WEB3AdminORMutualRuitoOrderExecutionRefUnit[]
     * @@roseuid 41D001F6019E
     */
    protected WEB3AdminORMutualRuitoOrderExecutionRefUnit[]
        createMutualRuitoExecutionRefReferenceUnitList(OrderUnit[] l_orderUnits)
        throws NotFoundException, WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createMutualRuitoExecutionRefReferenceUnitList(OrderUnit[])";
        log.entering(STR_METHOD_NAME);

        final String STR_DATE_FORMAT = "yyyyMMdd";
        WEB3GentradeFinObjectManager l_gentradeFinObjectManager = null;
        WEB3GentradeAccountManager l_genTradeAccountManager = null;
        Branch l_branch = null;
        WEB3GentradeMainAccount l_mainAccount = null;
        Trader l_trader = null;
        WEB3AdminOrderExecuteDataManager l_orderExecuteDataManager = null;
        WEB3AdminORMutualRuitoOrderExecutionRefUnit l_orMutualRuitoOrderExecutionRefUnit = null;
        WEB3AdminORMutualRuitoOrderExecutionRefUnit[] l_orMutualRuitoOrderExecutionRefUnits = null;
        MutualFundOrderUnit l_mutualFundOrderUnit = null;
        RuitoOrderUnit l_ruitoOrderUnit = null;
        WEB3MutualFundProduct l_mfProduct = null;
        WEB3MutualFundProductManager l_mfProductManager = null;
        TradingModule l_tradingModule = null;
        MutualFundOrderUnitParams l_mutualFundOrderUnitParam = null;
        RuitoOrderUnitParams l_ruitoOrderUnitParam = null;
        OrderTypeEnum l_orderTypeEnum = null;
        RuitoProductManager l_ruitoProductManager = null;
        RuitoProduct l_ruitoProduct = null;
        TaxTypeEnum l_taxTypeEnum = null;
        Date l_orderBizDate = null;
        ArrayList l_lisMutualRuitoOrderExecutionRefUnit = null;
        String l_strTradingType = null;
        String l_strOrderExecute = null;
        long l_lngBranchId = 0L;
        long l_lngMainAccount = 0L;
        long l_lngTraderId = 0L;
        int l_intCount = 0;

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        l_genTradeAccountManager = (WEB3GentradeAccountManager) l_finApp.getAccountManager();

        l_orderExecuteDataManager = new WEB3AdminOrderExecuteDataManager();

        l_gentradeFinObjectManager = (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();

        // Step 1.1 Create ArrayList
        l_lisMutualRuitoOrderExecutionRefUnit = new ArrayList();
        l_intCount = l_orderUnits.length;

        // Step 1.2 Loop process for as many times as the number of the elements of l_orderUnits
        for (int i = 0; i < l_intCount; i++)
        {
            l_lngBranchId = l_orderUnits[i].getBranchId();

            // Step 1.2.1 Acquire l_branch
            l_branch = l_genTradeAccountManager.getBranch(l_lngBranchId);

            // Step 1.2.2 Acquire l_account
            l_lngMainAccount = l_orderUnits[i].getAccountId();
            l_mainAccount =
                (WEB3GentradeMainAccount)l_genTradeAccountManager.getMainAccount(l_lngMainAccount);

            // Step 1.2.5 Acquire tradingType
            l_orderTypeEnum = l_orderUnits[i].getOrderType();
            l_strTradingType = l_orderExecuteDataManager.getTradingType(l_orderTypeEnum);

            // Step 1.2.6 Acquire orderStatus
            l_strOrderExecute = l_orderExecuteDataManager.getOrderStateDivPR(l_orderUnits[i]);

            //1.2.7.�����P�ʏЉ�敪(�����P��ID : long, �����^�C�v : long)
			//�����P�ʏЉ�敪�I�u�W�F�N�g���擾����B 
			//[����] 
			//�����P��ID:�����P��.�����P��ID 
			//�����^�C�v:�����P��.get����.�����^�C�v
            WEB3GentradeOrderUnitIntroduceDiv l_orderDiv = null;
            String l_strIntroduceDiv = "";
            String l_strIntroduceCode = "";
            try
            {
                l_orderDiv = 
                	new WEB3GentradeOrderUnitIntroduceDiv(l_orderUnits[i].getOrderUnitId(),
            			l_orderUnits[i].getProduct().getProductType());
                
                //1.2.8.get�Љ�敪( )
                l_strIntroduceDiv = l_orderDiv.getIntroduceBranchDiv();
                
                //1.2.9.get�Љ�X�R�[�h( )
                l_strIntroduceCode = l_orderDiv.getIntroduceBranchCode();
            }
            catch(WEB3SystemLayerException l_ex)
            {
                l_strIntroduceDiv = null;
                l_strIntroduceCode = null;
            }
            
            // Step 1.2.10 Create WEB3AdminORMutualRuitoOrderExecutionRefUnit instance
            l_orMutualRuitoOrderExecutionRefUnit =
                new WEB3AdminORMutualRuitoOrderExecutionRefUnit();

            // Step 1.2.11 Property Set
            l_orMutualRuitoOrderExecutionRefUnit.id =
                WEB3StringTypeUtility.formatNumber(l_orderUnits[i].getOrderId());
            l_orMutualRuitoOrderExecutionRefUnit.branchCode = l_branch.getBranchCode();
            l_orMutualRuitoOrderExecutionRefUnit.accountCode = l_mainAccount.getDisplayAccountCode();
            //�ڋq��       ���@@getMainAccount()�̖߂�l.get�ڋq�\���� ()
            l_orMutualRuitoOrderExecutionRefUnit.accountName =
                l_mainAccount.getDisplayAccountName();
            l_orMutualRuitoOrderExecutionRefUnit.tradingType = l_strTradingType;
            l_orMutualRuitoOrderExecutionRefUnit.orderQuantity =
                WEB3StringTypeUtility.formatNumber(l_orderUnits[i].getQuantity());
            l_orMutualRuitoOrderExecutionRefUnit.orderState = l_strOrderExecute;
            l_orMutualRuitoOrderExecutionRefUnit.deliveryDate = l_orderUnits[i].getDeliveryDate();
            l_orMutualRuitoOrderExecutionRefUnit.introduceStoreDiv = l_strIntroduceDiv;
            l_orMutualRuitoOrderExecutionRefUnit.introduceStoreCode = l_strIntroduceCode;


            l_lngTraderId = l_orderUnits[i].getTraderId();

            // Step 1.2.12 Type of orderUnit to be processed == MutualFundOrderUnit
            if (l_orderUnits[i] instanceof MutualFundOrderUnit)
            {
                // Step 1.2.12.1 Acquire mutualFundProduct
                l_mutualFundOrderUnit = (MutualFundOrderUnit) l_orderUnits[i];
                l_mutualFundOrderUnitParam =
                  (MutualFundOrderUnitParams) l_mutualFundOrderUnit.getDataSourceObject();

                l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
                l_mfProductManager = (WEB3MutualFundProductManager) l_tradingModule.getProductManager();
                l_mfProduct =
                    (WEB3MutualFundProduct) l_mfProductManager.getProduct(
                        l_mutualFundOrderUnitParam.getProductId());
                        
				MutualFundProductParams l_mutualFundProductParams =
					(MutualFundProductParams)l_mfProduct.getDataSourceObject();
					
                // Step 1.2.9.2 Property Set
                l_orMutualRuitoOrderExecutionRefUnit.productDiv = WEB3AdminProductDivDef.MF;
                l_orMutualRuitoOrderExecutionRefUnit.estimatedPrice =
                    WEB3StringTypeUtility.formatNumber(
                    l_mutualFundOrderUnitParam.getEstimatedPrice());
                l_orMutualRuitoOrderExecutionRefUnit.orderChannel =
                    l_mutualFundOrderUnitParam.getOrderChanel();
                l_orMutualRuitoOrderExecutionRefUnit.orderDate =
                    l_mutualFundOrderUnitParam.getReceivedDateTime();
                l_orderBizDate =
                    WEB3DateUtility.getDate(
                        l_mutualFundOrderUnitParam.getBizDate(),
                        STR_DATE_FORMAT);
                l_orMutualRuitoOrderExecutionRefUnit.orderBizDate = l_orderBizDate;
                l_orMutualRuitoOrderExecutionRefUnit.orderRootDiv =
                    l_mutualFundOrderUnitParam.getOrderRootDiv();
                l_taxTypeEnum = l_mutualFundOrderUnitParam.getTaxType();
                if (TaxTypeEnum.NORMAL.equals(l_taxTypeEnum))
                {
                    l_orMutualRuitoOrderExecutionRefUnit.taxType =
                        WEB3MFAccountDivDef.NORMAL;
                }

                // Checks if l_taxTypeEnum = 2 or l_taxTypeEnum = 3
                if (TaxTypeEnum.SPECIAL.equals(l_taxTypeEnum)
                    || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxTypeEnum))
                {
                    l_orMutualRuitoOrderExecutionRefUnit.taxType =
                        WEB3MFAccountDivDef.SPECIAL;
                }

                if (TaxTypeEnum.UNDEFINED.equals(l_taxTypeEnum))
                {
                    l_orMutualRuitoOrderExecutionRefUnit.taxType =
                        WEB3MFAccountDivDef.OTHER;
                }
                l_orMutualRuitoOrderExecutionRefUnit.settleDiv =
                    l_mutualFundOrderUnitParam.getSettlementDiv();
                l_orMutualRuitoOrderExecutionRefUnit.productId = 
                	WEB3StringTypeUtility.formatNumber(l_mutualFundProductParams.getProductId());
                l_orMutualRuitoOrderExecutionRefUnit.productName = l_mutualFundProductParams.getStandardName();
				if(l_mutualFundOrderUnitParam.getFundSellDiv() == null)
				{
					if(l_mutualFundOrderUnitParam.getQuantityType().equals(QuantityTypeEnum.QUANTITY))
					{
						l_orMutualRuitoOrderExecutionRefUnit.sellDiv =
							WEB3SellDivDef.COUNT_DESIGNATE;
					}
					else if(l_mutualFundOrderUnitParam.getQuantityType().equals(QuantityTypeEnum.AMOUNT))
					{
						l_orMutualRuitoOrderExecutionRefUnit.sellDiv =
							WEB3SellDivDef.MONEY_DESIGNATE;
					}
				}
				else
				{
                	l_orMutualRuitoOrderExecutionRefUnit.sellDiv =
                    	l_mutualFundOrderUnitParam.getFundSellDiv();
				}
                l_orMutualRuitoOrderExecutionRefUnit.deliveryDiv =
                    l_mutualFundOrderUnitParam.getPaymentMethod();
                l_orMutualRuitoOrderExecutionRefUnit.executionTimestamp =
                    l_mutualFundOrderUnitParam.getExecDate();
                if (l_mutualFundOrderUnitParam.trader_id != null)
                {
                    l_trader = l_gentradeFinObjectManager.getTrader(l_lngTraderId);
                    l_orMutualRuitoOrderExecutionRefUnit.traderCode = l_trader.getTraderCode();
                }
                l_orMutualRuitoOrderExecutionRefUnit.sonarTraderCode = 
                	l_mutualFundOrderUnitParam.getSonarTraderCode();

                l_orMutualRuitoOrderExecutionRefUnit.sellBuyDiv =
                    l_mutualFundOrderUnitParam.getRequestDiv();

                WEB3MutualFundOrderManager l_orderManager =
                    (WEB3MutualFundOrderManager)l_finApp.getTradingModule(
                        ProductTypeEnum.MUTUAL_FUND).getOrderManager();

                //�������ʋ敪���g�����M�����}�l�[�W��.get�������ʋ敪( ���M�����P�� )
                l_orMutualRuitoOrderExecutionRefUnit.mutualOrderQuantityType =
                    l_orderManager.getOrderQuantityDiv(l_mutualFundOrderUnit);

                //�T�Z��n����ʉ݃R�[�h���g�����M�����}�l�[�W��.get�T�Z��n����ʉ݃R�[�h( ���M�����P�� )
                l_orMutualRuitoOrderExecutionRefUnit.estimatedPriceCurrencyCode =
                    l_orderManager.getEstimateDeliveryAmountCurrencyCode(l_mutualFundOrderUnit);

                //�O��MMF�t���O�����M�����P��.get���M�^�C�v()��"�O��MMF"�̏ꍇ�Atrue
                //����ȊO�̏ꍇ�Afalse
                MutualFundTypeEnum l_mutualFundTypeEnum =
                    ((MutualFundOrderUnitRow)l_mutualFundOrderUnit.getDataSourceObject()).getFundType();
                if (MutualFundTypeEnum.FOREIGN_MMF.equals(l_mutualFundTypeEnum))
                {
                    l_orMutualRuitoOrderExecutionRefUnit.frgnMmfFlag = true;
                }
                else
                {
                    l_orMutualRuitoOrderExecutionRefUnit.frgnMmfFlag = false;
                }
            }

            // Step 1.2.13 Type of orderUnit to be processed == RuitoOrderUnit
            if (l_orderUnits[i] instanceof RuitoOrderUnit)
            {
                // Step 1.2.13.1 Acquire ruito product
                l_ruitoOrderUnit = (RuitoOrderUnit) l_orderUnits[i];
                l_ruitoOrderUnitParam = (RuitoOrderUnitParams)
                         l_ruitoOrderUnit.getDataSourceObject();
                l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.RUITO);
                l_ruitoProductManager = (RuitoProductManager) l_tradingModule.getProductManager();
                l_ruitoProduct =
                    (RuitoProduct) l_ruitoProductManager.getProduct(
                        l_ruitoOrderUnitParam.getProductId());

                // Step 1.2.13.2 Property Set
                l_orMutualRuitoOrderExecutionRefUnit.productDiv = WEB3AdminProductDivDef.RUITO;
                l_orMutualRuitoOrderExecutionRefUnit.orderChannel =
                    l_ruitoOrderUnitParam.getOrderChanel();
                l_orMutualRuitoOrderExecutionRefUnit.orderDate =
                    l_ruitoOrderUnitParam.getReceivedDateTime();
                l_orderBizDate =
                    WEB3DateUtility.getDate(l_ruitoOrderUnitParam.getBizDate(), STR_DATE_FORMAT);
                l_orMutualRuitoOrderExecutionRefUnit.orderBizDate = l_orderBizDate;
                l_orMutualRuitoOrderExecutionRefUnit.orderRootDiv =
                    l_ruitoOrderUnitParam.getOrderRootDiv();
                l_orMutualRuitoOrderExecutionRefUnit.productId =
                	WEB3StringTypeUtility.formatNumber(l_ruitoProduct.getProductId());
                l_orMutualRuitoOrderExecutionRefUnit.productName = l_ruitoProduct.getStandardName();
				if(l_ruitoOrderUnitParam.getGpSellDiv() == null)
				{
					if(l_ruitoOrderUnitParam.getQuantityType().equals(QuantityTypeEnum.QUANTITY))
					{
						l_orMutualRuitoOrderExecutionRefUnit.sellDiv =
							WEB3SellDivDef.COUNT_DESIGNATE;
					}
					else if(l_ruitoOrderUnitParam.getQuantityType().equals(QuantityTypeEnum.AMOUNT))
					{
						l_orMutualRuitoOrderExecutionRefUnit.sellDiv =
							WEB3SellDivDef.MONEY_DESIGNATE;
					}
				}
				else
				{
                	l_orMutualRuitoOrderExecutionRefUnit.sellDiv = l_ruitoOrderUnitParam.getGpSellDiv();
				}
                l_orMutualRuitoOrderExecutionRefUnit.deliveryDiv =
                    l_ruitoOrderUnitParam.getPaymentMethod();
                if (l_ruitoOrderUnitParam.trader_id != null)
                {
                    l_trader = l_gentradeFinObjectManager.getTrader(l_lngTraderId);
                    l_orMutualRuitoOrderExecutionRefUnit.traderCode = l_trader.getTraderCode();
                }
                l_orMutualRuitoOrderExecutionRefUnit.sonarTraderCode = 
                	l_ruitoOrderUnitParam.getSonarTraderCode();
            }

            // Step 1.2.11 Add WEB3AdminORMutualRuitoOrderExecutionRefUnit to ArrayList
            l_lisMutualRuitoOrderExecutionRefUnit.add(l_orMutualRuitoOrderExecutionRefUnit);
        }

        // Step 1.3 Create an array of WEB3AdminORMutualRuitoOrderExecutionRefUnit
        l_orMutualRuitoOrderExecutionRefUnits =
            new WEB3AdminORMutualRuitoOrderExecutionRefUnit[l_lisMutualRuitoOrderExecutionRefUnit
                .size()];
        l_orMutualRuitoOrderExecutionRefUnits =
            (
                WEB3AdminORMutualRuitoOrderExecutionRefUnit[]) l_lisMutualRuitoOrderExecutionRefUnit
                    .toArray(
                l_orMutualRuitoOrderExecutionRefUnits);
        log.exiting(STR_METHOD_NAME);
        return l_orMutualRuitoOrderExecutionRefUnits;
    }
}@
