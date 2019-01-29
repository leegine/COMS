head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.23.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminIfoExecuteReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ґ敨OP�������Ɖ�T�[�r�XImpl(WEB3AdminIfoExecuteReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
                 : 2006/08/21 �юu��(���u) ���f��No.062
                 : 2006/10/18 ������ (���u) ���f��No.077
                 : 2006/12/19 ������(���u) ���f��No.087
Revision History : 2007/07/01 �����F(���u) ���f��No.101 103
Revision History : 2009/03/04 ����(���u) ���f��No.133
*/
package webbroker3.adminorderexecinquiry.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderManager;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;

import webbroker3.adminorderexecinquiry.WEB3AdminOrderExecuteDataManager;
import webbroker3.adminorderexecinquiry.WEB3AdminProductExecutionInfo;
import webbroker3.adminorderexecinquiry.define.WEB3AdminKeyItemDef;
import webbroker3.adminorderexecinquiry.define.WEB3AdminProductDivDef;
import webbroker3.adminorderexecinquiry.define.WEB3AdminTaxTypeDef;
import webbroker3.adminorderexecinquiry.define.WEB3OptionProductTypeDef;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORDetailDispInfoUnit;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFutOpOrderExecutionRefInputRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFutOpOrderExecutionRefInputResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFutOpOrderExecutionRefReferenceRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFutOpOrderExecutionRefReferenceResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFutOpOrderExecutionRefUnit;
import webbroker3.adminorderexecinquiry.message.WEB3AdminOROrderExecutionRefCommonRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminOROrderExecutionSortKeyUnit;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORTradingProductUnit;
import webbroker3.adminorderexecinquiry.service.delegate.WEB3AdminIfoExecuteReferenceService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3OrderStatusDef;
import webbroker3.common.define.WEB3OrderexecutionEndTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranchIndexDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��Ґ敨OP�������Ɖ�T�[�r�XImpl)<BR>
 * <BR>
 * �Ǘ��Ґ敨OP�������Ɖ�T�[�r�X�����N���X<BR>
 * <BR>
 * WEB3AdminIfoExecuteReferenceServiceImpl class<BR>
 * <BR>
 * @@author Ambha and Anupama
 * @@version 1.0
 */
public class WEB3AdminIfoExecuteReferenceServiceImpl
    extends WEB3ClientRequestService
    implements WEB3AdminIfoExecuteReferenceService
{
    /**
     * Log Variable
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIfoExecuteReferenceServiceImpl.class);

    /**
     * @@roseuid 4212FB730150
     */
    public WEB3AdminIfoExecuteReferenceServiceImpl()
    {
    }

    /**
     * �敨OP�������Ɖ�����s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * �ȉ��̃��\�b�h���Ăѕ�����B<BR>
     * <BR>
     * ���Ǘ��ҁE�敨OP�������Ɖ���̓��N�G�X�g�̏ꍇ<BR>
     * �@@this.get���͉��()���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE�敨OP�������Ɖ�N�G�X�g�̏ꍇ<BR>
     * �@@this.get�Ɖ���()���R�[������B<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * Execute WEB3AdminIfoExecuteReferenceService process<BR>
     * <BR>
     * Call one of the following methods based on the type of the argument,
     * l_request.<<BR>
     * <BR>
     * ��If WEB3AdminORFutOpOrderExecutionRefInputRequest<BR>
     * �@@Call this.getInputScreen()<BR>
     * <BR>
     * ��If WEB3AdminORFutOpOrderExecutionRefReferenceRequest<BR>
     * �@@Call this.getReferenceScreen()<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 41AE9ABC0318
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        try
        {
            /*
             * If l_request is of type WEB3AdminORFutOpOrderExecutionRefInputRequest, call
             * getInputScreen(). If l_request is of type
             * WEB3AdminORFutOpOrderExecutionRefReferenceRequest,
             * call getReferenceScreen(). Other wise throw WEB3SystemLayerException.
             */
            if (l_request instanceof WEB3AdminORFutOpOrderExecutionRefInputRequest)
            {
                l_response =
                    this.getInputScreen((WEB3AdminORFutOpOrderExecutionRefInputRequest) l_request);

            } else if (l_request instanceof WEB3AdminORFutOpOrderExecutionRefReferenceRequest)
            {
                l_response =
                    this.getReferenceScreen(
                        (WEB3AdminORFutOpOrderExecutionRefReferenceRequest) l_request);

            } else
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "INPUT ���N�G�X�g NOT �Ǘ��ҁE�敨OP�������Ɖ�N�G�X�g");

            }
        } catch (NotFoundException l_notFoundExp)
        {
            log.error(l_notFoundExp.getMessage(), l_notFoundExp);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_notFoundExp.getMessage(),
                l_notFoundExp);

        } catch (DataNetworkException l_dataNetworkExp)
        {
            log.debug(l_dataNetworkExp.getMessage());
            String l_strMsg = "Error while aquiring the Data ";
            log.error(l_strMsg, l_dataNetworkExp);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dataNetworkExp.toString(),
                l_dataNetworkExp);

        } catch (DataFindException l_dataFindExp)
        {
            log.debug(l_dataFindExp.getMessage());
            String l_strMsg = "Error while aquiring the Data ";
            log.error(l_strMsg, l_dataFindExp);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dataFindExp.toString(),
                l_dataFindExp);

        } catch (DataQueryException l_dataQueryExp)
        {
            log.debug(l_dataQueryExp.getMessage());
            String l_strMsg = "Error while aquiring the Data ";
            log.error(l_strMsg, l_dataQueryExp);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dataQueryExp.toString(),
                l_dataQueryExp);

        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get���͉��)<BR>
     * <BR>
     * �敨OP�������Ɖ���͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��Ґ敨OP�������Ɖ�T�[�r�X)get���͉�ʁv<BR>
     * �Q��<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * getInputScreen<BR>
     * <BR>
     * Execute WEB3AdminIfoExecuteReferenceService input screen process<BR>
     * <BR>
     * Refer to the sequence diagram "(administrator: futures OP order execution
     * inquiry service)getInputScreen"<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * <BR>
     * �Ǘ��ҁE�敨OP�������Ɖ���̓��N�G�X�g�I�u�W�F�N�g<BR>
     * <BR>
     * ---------<English>----------<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminORFutOpOrderExecutionRefInputRequest object<BR>
     * <BR>
     * @@return
     * webbroker3.adminorderexecinquiry.message.WEB3AdminORFutOpOrderExecutionRefInputR
     * esponse
     * @@exception WEB3BaseException WEB3BaseException
     * @@exception NotFoundException NotFoundException
     * @@exception DataFindException DataFindException
     * @@exception DataQueryException DataQueryException
     * @@exception DataNetworkException DataNetworkException
     * @@roseuid 41AE9ABB00C6
     */
    protected WEB3AdminORFutOpOrderExecutionRefInputResponse getInputScreen(
        WEB3AdminORFutOpOrderExecutionRefInputRequest l_request)
        throws
            WEB3BaseException,
            NotFoundException,
            DataFindException,
            DataQueryException,
            DataNetworkException
    {
        final String STR_METHOD_NAME =
            "getInputScreen(WEB3AdminORFutOpOrderExecutionRefInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3Administrator l_administrator = null;
        String l_strInstitutionCode = null;
        String[] l_strBranchCodes = null;
        WEB3AdminORFutOpOrderExecutionRefInputResponse l_response = null;
        WEB3AdminOrderExecuteDataManager l_adminOrderExecDataManager = null;
        WEB3AdminProductExecutionInfo l_productExecutionInfo = null;
        WEB3GentradeBranchIndexDealtCond[] l_gentradeBranchIndexDealtConds = null;
        int l_intBranchIndexCnt = 0;
        String l_strProductCode = null;
        WEB3GentradeHandlingOrderCond l_gentradeHandlingOrderCond = null;
        List l_lisOrderBizDate = null;
        WEB3AdminORTradingProductUnit[] l_adminORTradProdUnits = null;
        String[] l_strOptionExecConds = null;
        String[] l_strOptionExpirationDateTypes = null;
        String[] l_strOptionOrderCondTypes = null;
        String[] l_strFutureExecConds = null;
        String[] l_strFutureExpirationDateTypes = null;
        String[] l_strFutureOrderCondTypes = null;
        Date[] l_datOrderBizDates = null;
        String[] l_strUnderlyingProdCodes = null;
        List l_lisUnderlyingProdCode = null;
        Date l_datBizDate = null;
        Date l_datPrevBizDate = null;
        Timestamp l_tsPrevBizDate = null;
        Date l_datOrderBizDate = null;
        boolean l_blnAlreadyExist = false;
        List l_lisHandlingPossible = null;
        int l_intFutureTotalRecordCnt = 0;

        // Step 1.1 Call validate()
        l_request.validate();

        // Step 1.2 Call getInstanceFromLoginInfo()
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // Step 1.3 Call validateAuthority(l_strTransactionCategory : String, l_isUpdate : boolean)
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.XBIFO_ORDER_EXEC_INQUIRY,
            false);

        // Step 1.4 Call getInstitutionCode()
        l_strInstitutionCode = l_administrator.getInstitutionCode();

        /* Step 1.5 Call getProductExecutionInfo(l_strInstitutionCode : String,
         * l_branchCodeList : String[])
         */
        l_strBranchCodes = l_request.branchCode;
        l_adminOrderExecDataManager = new WEB3AdminOrderExecuteDataManager();
        l_productExecutionInfo =
            l_adminOrderExecDataManager.getProductExecutionInfo(
                l_strInstitutionCode,
                l_strBranchCodes);

        // Step 1.6 Call createTradingProductList(l_productExecutionInfo : WEB3ProductExecutionInfo)
        l_adminORTradProdUnits = this.createTradingProductList(l_productExecutionInfo);

        // Step 1.7 Call getHandlingCondBranchIndex(l_strInstitutionCode: String)
        l_gentradeBranchIndexDealtConds =
            WEB3GentradeBranchIndexDealtCond.getHandlingCondBranchIndex(l_strInstitutionCode);

        // Step 1.8 Create instance of ArrayList
        l_lisOrderBizDate = new ArrayList();

        // Step 1.9 Add previous bizDate and systemTimeStamp to ArrayList
        l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
        WEB3GentradeBizDate l_dateCalc =
            new WEB3GentradeBizDate(new Timestamp(l_datBizDate.getTime()));
        l_datPrevBizDate = l_dateCalc.roll(-1);
        l_tsPrevBizDate = new Timestamp(l_datPrevBizDate.getTime());
        l_lisOrderBizDate.add(WEB3DateUtility.toDay(l_tsPrevBizDate));
        l_lisOrderBizDate.add(WEB3DateUtility.toDay(l_datBizDate));

        /* Step 1.10 Loop process for as many times as the number of elements
         * of return value of getHandlingCondBranchIndex()
         */
        l_intBranchIndexCnt = l_gentradeBranchIndexDealtConds.length;
        l_lisUnderlyingProdCode = new ArrayList();
        for (int i = 0; i < l_intBranchIndexCnt; i++)
        {
            // Step 1.10.1 Call resetProductCode(l_strProductCode : String).
            l_strProductCode = l_gentradeBranchIndexDealtConds[i].getUnderlyingProductCode();
            WEB3GentradeTradingTimeManagement.resetProductCode(l_strProductCode);

            // Prepare a list of unqiue underlyingProductCode and set it later to response
            l_blnAlreadyExist = l_lisUnderlyingProdCode.contains(l_strProductCode);

            // If underlyingProductCode not present in list, then add to a list.
            if (!l_blnAlreadyExist)
            {
                l_lisUnderlyingProdCode.add(l_strProductCode);
            }

            // Step 1.10.2 Call getOrderBizDate()
            l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

            // Step 1.10.3 Add orderBizDate to ArrayList if it is not included in ArrayList
            l_blnAlreadyExist = l_lisOrderBizDate.contains(l_datOrderBizDate);

            // If orderBizDate not present in list, then add to a list.
            if (!l_blnAlreadyExist)
            {
                l_lisOrderBizDate.add(l_datOrderBizDate);
            }
        }

        /* Step 1.11 If optionFlag = ENFORCEMENT  then get execCondList,
         * expirationDateTypeList, orderCondTypeList
         */
        if (l_productExecutionInfo.optionFlag)
        {
            // Step 1.11.1 Acquire WEB3GentradeHandlingOrderCond of option
            l_gentradeHandlingOrderCond =
                new WEB3GentradeHandlingOrderCond(
                    l_strInstitutionCode,
                    ProductTypeEnum.IFO,
                    WEB3FuturesOptionDivDef.OPTION,
                    WEB3MarginTradingDivDef.DEFAULT);

            // Step 1.11.2 Call getHandlingPossibleExecCond()
            l_strOptionExecConds = l_gentradeHandlingOrderCond.getHandlingPossibleExecCond();

            //�戵�\���������敪�ꗗ�擾
            l_strOptionExpirationDateTypes =
                l_gentradeHandlingOrderCond.getHandlingPossibleExpirationDateTypes();

            // Step 1.11.4 Call getHandlingPossibleOrderCond()
            l_strOptionOrderCondTypes = l_gentradeHandlingOrderCond.getHandlingPossibleOrderCond();
        }

        /* Step 1.12 If futureFlag = true then get execCondList, expirationDateTypeList,
         * orderCondTypeList
         */
        if (l_productExecutionInfo.futureFlag)
        {
            // Step 1.12.1Acquire WEB3GentradeHandlingOrderCond of futures
            l_gentradeHandlingOrderCond =
                new WEB3GentradeHandlingOrderCond(
                    l_strInstitutionCode,
                    ProductTypeEnum.IFO,
                    WEB3FuturesOptionDivDef.FUTURES,
                    WEB3MarginTradingDivDef.DEFAULT);

            // Step 1.12.2 Call getHandlingPossibleExecCond()
            l_strFutureExecConds = l_gentradeHandlingOrderCond.getHandlingPossibleExecCond();

            //�戵�\���������敪�ꗗ�擾
            l_strFutureExpirationDateTypes =
                l_gentradeHandlingOrderCond.getHandlingPossibleExpirationDateTypes();

            // Step 1.12.4 Call getHandlingPossibleOrderCond()
            l_strFutureOrderCondTypes = l_gentradeHandlingOrderCond.getHandlingPossibleOrderCond();
        }

        // Step 1.13 create response
        l_response = (WEB3AdminORFutOpOrderExecutionRefInputResponse) l_request.createResponse();

        // Step 1.14 set properties to response data
        l_datOrderBizDates = new Date[l_lisOrderBizDate.size()];
        l_datOrderBizDates = (Date[]) l_lisOrderBizDate.toArray(l_datOrderBizDates);
        l_response.orderBizDateList = l_datOrderBizDates;

        l_strUnderlyingProdCodes = new String[l_lisUnderlyingProdCode.size()];
        l_strUnderlyingProdCodes =
            (String[]) l_lisUnderlyingProdCode.toArray(l_strUnderlyingProdCodes);
        l_response.targetProductList = l_strUnderlyingProdCodes;
        
		l_response.orderRootList = new String[] {
			WEB3OrderRootDivDef.CALLCENTER,
			WEB3OrderRootDivDef.PC,
			WEB3OrderRootDivDef.SLINGSHOT,
			WEB3OrderRootDivDef.I_MODE,
			WEB3OrderRootDivDef.VODAFONE,
			WEB3OrderRootDivDef.AU,
            WEB3OrderRootDivDef.HOST,
            WEB3OrderRootDivDef.RICH_CLIENT
		};

        // If optionFlag = true, check the value of futureFlag
        if (l_productExecutionInfo.optionFlag)
        {
            /* Check the value of futureFlag when optionFlag is true. If both are true, then set
             * only unique values from acquired condition.
             */
            if (l_productExecutionInfo.futureFlag)
            {
                l_lisHandlingPossible = new ArrayList();

                /* If return of getHandlingPossibleExecCond() is exactly same for both futures and
                 * option, then set any one. Otherwise avoid duplicate elements
                 */
                l_blnAlreadyExist = Arrays.equals(l_strOptionExecConds, l_strFutureExecConds);
                if (l_blnAlreadyExist)
                {
                    l_response.execCondList = l_strOptionExecConds;
                } else
                {
                    l_lisHandlingPossible.addAll(Arrays.asList(l_strOptionExecConds));
                    l_intFutureTotalRecordCnt = l_strFutureExecConds.length;
                    for (int i = 0; i < l_intFutureTotalRecordCnt; i++)
                    {
                        /* If handlingPossibleExecCond is already added to a list from option, then
                         * do not add it from futures
                         */
                        l_blnAlreadyExist = l_lisHandlingPossible.contains(l_strFutureExecConds[i]);
                        if (!l_blnAlreadyExist)
                        {
                            l_lisHandlingPossible.add(l_strFutureExecConds[i]);
                        }
                    }
                    l_response.execCondList =
                        (String[]) l_lisHandlingPossible.toArray(l_strOptionExecConds);
                }

                /* If return of getHandlingPossibleExpirationDateType() is exactly same for both
                 * futures and option, then set any one. Otherwise avoid duplicate elements
                 */
                l_blnAlreadyExist =
                    Arrays.equals(l_strOptionExpirationDateTypes, l_strFutureExpirationDateTypes);
                if (l_blnAlreadyExist)
                {
                    l_response.expirationDateTypeList = l_strOptionExpirationDateTypes;
                } else
                {
                    l_lisHandlingPossible.clear();
                    l_lisHandlingPossible.addAll(Arrays.asList(l_strOptionExpirationDateTypes));
                    l_intFutureTotalRecordCnt = l_strFutureExpirationDateTypes.length;
                    for (int i = 0; i < l_intFutureTotalRecordCnt; i++)
                    {
                        /* If handlingPossibleExpirationDateType is already added to a list from
                         * option, then do not add it from futures
                         */
                        l_blnAlreadyExist =
                            l_lisHandlingPossible.contains(l_strFutureExpirationDateTypes[i]);
                        if (!l_blnAlreadyExist)
                        {
                            l_lisHandlingPossible.add(l_strFutureExpirationDateTypes[i]);
                        }
                    }
                    l_response.expirationDateTypeList =
                        (String[]) l_lisHandlingPossible.toArray(l_strOptionExpirationDateTypes);
                }

                /* If return of getHandlingPossibleOrderCond() is exactly same for both futures and
                 * option, then set any one. Otherwise avoid duplicate elements
                 */
                l_blnAlreadyExist =
                    Arrays.equals(l_strOptionOrderCondTypes, l_strFutureOrderCondTypes);
                if (l_blnAlreadyExist)
                {
                    l_response.orderCondTypeList = l_strOptionOrderCondTypes;
                } else
                {
                    l_lisHandlingPossible.clear();
                    l_lisHandlingPossible.addAll(Arrays.asList(l_strOptionOrderCondTypes));
                    l_intFutureTotalRecordCnt = l_strFutureOrderCondTypes.length;
                    for (int i = 0; i < l_intFutureTotalRecordCnt; i++)
                    {
                        /* If handlingPossibleOrderCond is already added to a list from
                         * option, then do not add it from futures
                         */
                        l_blnAlreadyExist =
                            l_lisHandlingPossible.contains(l_strFutureOrderCondTypes[i]);
                        if (!l_blnAlreadyExist)
                        {
                            l_lisHandlingPossible.add(l_strFutureOrderCondTypes[i]);
                        }
                    }
                    l_response.orderCondTypeList =
                        (String[]) l_lisHandlingPossible.toArray(l_strOptionOrderCondTypes);
                }

            // If optionFlag is true and futureFlag is false, then set directly return of option
            } else
            {
                l_response.execCondList = l_strOptionExecConds;
                l_response.expirationDateTypeList = l_strOptionExpirationDateTypes;
                l_response.orderCondTypeList = l_strOptionOrderCondTypes;
            }

         // If optionFlag is false and futureFlag is true, then set directly return of futures
        } else if (l_productExecutionInfo.futureFlag)
        {
            l_response.execCondList = l_strFutureExecConds;
            l_response.expirationDateTypeList = l_strFutureExpirationDateTypes;
            l_response.orderCondTypeList = l_strFutureOrderCondTypes;

        // Set null if it is unable to acquire it i.e when both optionFlag and futureFlag are false
        } else
        {
            l_response.execCondList = null;
            l_response.expirationDateTypeList = null;
            l_response.orderCondTypeList = null;
        }

        l_response.tradingProductList = l_adminORTradProdUnits;
        log.exiting(STR_METHOD_NAME);

        // Step 1.15 return response
        return l_response;

    }

    /**
     * (get�Ɖ���)<BR>
     * <BR>
     * �敨OP�������Ɖ�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��Ґ敨OP�������Ɖ�T�[�r�X)get�Ɖ��ʁv<BR>
     * �Q��<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * <BR>
     * �Ǘ��ҁE�敨OP�������Ɖ�N�G�X�g�I�u�W�F�N�g<BR>
     * <BR>
     * @@return
     * webbroker3.adminorderexecinquiry.message.WEB3AdminORFutOpOrderExecutionRefRefere
     * nceResponse
     * @@exception WEB3BaseException WEB3BaseException
     * @@exception DataFindException DataFindException
     * @@exception DataNetworkException DataNetworkException
     * @@exception DataQueryException DataQueryException
     * @@exception NotFoundException NotFoundException
     * @@roseuid 41AE9ABB00E5
     */
    protected WEB3AdminORFutOpOrderExecutionRefReferenceResponse getReferenceScreen(
        WEB3AdminORFutOpOrderExecutionRefReferenceRequest l_request)
        throws
            WEB3BaseException,
            DataFindException,
            DataNetworkException,
            DataQueryException,
            NotFoundException
    {
        final String STR_METHOD_NAME =
            "getReferenceScreen(WEB3AdminORFutOpOrderExecutionRefReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        int l_intPageIndex = 0;
        int l_intPageSize = 0;
        String l_strInstitutionCode = null;
        String l_strTargetProduct = null;
        String l_strDeliveryMonth = null;
        String l_strStrikePrice = null;
        String l_strOpProductType = null;
        String l_strQueryString = null;
        String l_strSortCond = null;
        String l_strQueryCond = null;
        String l_strOptionCarryoverEndType = null;
        String l_strFutureCarryoverEndType = null;
        String[] l_strBranchCodes = null;
        String l_strCommonQueryString = null;
        String[] l_strFutOpProductIds = null;
        String[] l_strQueryCondDataContainers = null;
        String[] l_strCommonQueryDataContainers = null;
        String[] l_strQueryDataContainers = null;
        IfoOrderUnit[] l_ifoOrderUnits = null;
        IfoOrderUnitParams[] l_ifoOrderUnitParams = null;
        WEB3AdminOrderExecuteDataManager l_adminOrderExecDataManager = null;
        WEB3AdminORFutOpOrderExecutionRefReferenceResponse l_response = null;
        WEB3AdminOROrderExecutionSortKeyUnit[] l_adminOROrderExecSortKeyUnits = null;
        WEB3GentradeInstitution l_gentradeInstitution = null;
        WEB3AdminORDetailDispInfoUnit[] l_adminORDetailDispInfoUnits = null;
        WEB3AdminORFutOpOrderExecutionRefUnit[] l_adminORFutOpOrderExecRefUnits = null;
        WEB3Administrator l_administrator = null;
        int l_intQueryCondDataContainerCnt = 0;
        int l_intCommonDataContainerCnt = 0;
        int l_intQueryCondContainerCnt = 0;
        FinApp l_finApp = null;
        IfoOrderManager l_ifoOrderManager = null;
        int l_intIfoOrderUnitCnt = 0;
        WEB3PageIndexInfo l_pageIndexInfo = null;

        l_finApp = (FinApp)Services.getService(FinApp.class);

        // Step 1.1 Call validate()
        l_request.validate();

        // Step 1.2 Call getInstanceFromLoginInfo()
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // Step 1.3 Call validateAuthority(l_strTransactionCategory : String, l_isUpdate : boolean)
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.XBIFO_ORDER_EXEC_INQUIRY,
            false);

        // Step 1.4 Call validateBranchPermission(l_strBranchCodes : String)
        l_strBranchCodes = l_request.branchCode;
        l_administrator.validateBranchPermission(l_strBranchCodes);

        // Step 1.5 Call getInstitutionCode()
        l_strInstitutionCode = l_administrator.getInstitutionCode();

        /* Step 1.6 Call createCommonQueryString(l_strInstitutionCode : String, l_request :
         * WEB3AdminOROrderExecutionRefCommonRequest)
         */
        l_adminOrderExecDataManager = new WEB3AdminOrderExecuteDataManager();
        l_strCommonQueryString =
            l_adminOrderExecDataManager.createCommonQueryString(
                l_strInstitutionCode,
                (WEB3AdminOROrderExecutionRefCommonRequest) l_request);

        l_strTargetProduct = l_request.targetProductCode;
        l_strDeliveryMonth = l_request.delivaryMonth;
        l_strStrikePrice = l_request.strikePrice;
        
        if (WEB3OptionProductTypeDef.PUT_OPTIONS.equals(l_request.opProductType))
        {
			int l_intOpProductType = IfoDerivativeTypeEnum.IntValues.PUT_OPTIONS;
			l_strOpProductType = String.valueOf(l_intOpProductType);

        }else if (WEB3OptionProductTypeDef.CALL_OPTIONS.equals(l_request.opProductType))
        {
			int l_intOpProductType = IfoDerivativeTypeEnum.IntValues.CALL_OPTIONS;
			l_strOpProductType = String.valueOf(l_intOpProductType);
        }

        /* Step 1.7 Call getFutOpProductIdList(l_strInstitutionCode : String, l_targetProduct :
         * String, l_deliveryMonth : String, l_strikePrice : String, l_opProductType : String)
         */
        try
        {
            l_strFutOpProductIds =
                this.getFutOpProductIdList(
                    l_strInstitutionCode,
                    l_strTargetProduct,
                    l_strDeliveryMonth,
                    l_strStrikePrice,
                    l_strOpProductType);

        // Step 1.8 Return response, if exception is thrown while acquiring productIds
        } catch (WEB3BusinessLayerException l_businessLayerexp)
        {

            // Step1.8.1 Create Response
            l_response =
                (WEB3AdminORFutOpOrderExecutionRefReferenceResponse) l_request.createResponse();

            // Step 1.8.2 Set initial values to response data
            l_response.opCarryoverEndType = null;
            l_response.fuCarryoverEndType = null;
            l_response.totalPages = "0";
            l_response.totalRecords = "0";
            l_response.pageIndex = "0";
            l_response.futOpeOrderExecutionRefList = null;

            // Step 1.8.3 Return response
            log.exiting(STR_METHOD_NAME);
            return l_response;

        }

        //create��������������(String[], �Ǘ��ҁE�敨OP�������Ɖ�N�G�X�g)
        l_strQueryString = 
            this.createQueryString(
                l_strFutOpProductIds, 
                l_request);

        /* Step 1.10 Call createCommonQueryDataContainer(l_strInstitutionCode : String, l_request :
         * WEB3AdminOROrderExecutionRefCommonRequest)
         */
        l_strCommonQueryDataContainers =
            l_adminOrderExecDataManager.createCommonQueryDataContainer(
                l_strInstitutionCode,
                (WEB3AdminOROrderExecutionRefCommonRequest) l_request);

        //create���������f�[�^�R���e�i(String[], �Ǘ��ҁE�敨OP�������Ɖ�N�G�X�g)
        l_strQueryDataContainers =
            this.createQueryDataContainer(
                l_strFutOpProductIds,
                l_request);

        // Step 1.12 Call createSortCond(l_sortKeys : WEB3AdminOROrderExecutionSortKeyUnit[])
        l_adminOROrderExecSortKeyUnits = l_request.sortKeys;
        l_strSortCond = this.createSortCond(l_adminOROrderExecSortKeyUnits);

        l_strQueryCond = l_strCommonQueryString.concat(l_strQueryString);
        l_intCommonDataContainerCnt = l_strCommonQueryDataContainers.length;
        l_intQueryCondContainerCnt = l_strQueryDataContainers.length;
        l_intQueryCondDataContainerCnt = l_intCommonDataContainerCnt + l_intQueryCondContainerCnt;
        l_strQueryCondDataContainers = new String[l_intQueryCondDataContainerCnt];

        System.arraycopy(
            l_strCommonQueryDataContainers,
            0,
            l_strQueryCondDataContainers,
            0,
            l_intCommonDataContainerCnt);
        System.arraycopy(
            l_strQueryDataContainers,
            0,
            l_strQueryCondDataContainers,
            l_intCommonDataContainerCnt,
            l_intQueryCondContainerCnt);

        /* Step 1.13 Call getOrderUnits(l_strQueryCond : String, l_strQueryCondDataContainer :
         * String[], l_strSortCond : String)
         */
        l_ifoOrderUnitParams =
            this.getOrderUnits(l_strQueryCond, l_strQueryCondDataContainers, l_strSortCond);

        // remove�J�z�������P��(�����P��Params�ꗗ : IfoOrderUnitParams[])
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
        l_ifoOrderUnitParams = l_orderManager.removeCarryOverOriginalOrderUnit(l_ifoOrderUnitParams);

        /* Step 1.15 If return of removeBeforeCarryoverOrderUnit() is not null then perform paging
         * operation, create futOpeOrderExecutionRefList and detailDispInfoList
         */
        if (l_ifoOrderUnitParams != null)
        {
            // Step 1.15.1 Extract only elements of lines to be displayed using WEB3PageIndexInfo
            l_intPageIndex = Integer.parseInt(l_request.pageIndex);
            l_intPageSize = Integer.parseInt(l_request.pageSize);
            l_pageIndexInfo =
                new WEB3PageIndexInfo(l_ifoOrderUnitParams, l_intPageIndex, l_intPageSize);

            l_ifoOrderUnitParams =
                (IfoOrderUnitParams[]) l_pageIndexInfo.getArrayReturned(IfoOrderUnitParams.class);

            // Convert IfoOrderUnitParams[] to IfoOrderUnit[]
            l_intIfoOrderUnitCnt = l_ifoOrderUnitParams.length;
            l_ifoOrderUnits = new IfoOrderUnit[l_intIfoOrderUnitCnt];
            l_ifoOrderManager =
                (IfoOrderManager) l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            for (int i = 0; i < l_intIfoOrderUnitCnt; i++)
            {
                l_ifoOrderUnits[i] =
                    (IfoOrderUnit) l_ifoOrderManager.toOrderUnit(l_ifoOrderUnitParams[i]);
            }

            /* Step 1.15.2 Call createFutOpOrderExecutionRefReferenceUnitList(
             * l_orderUnits : IfoOrderUnit[])
             */
            l_adminORFutOpOrderExecRefUnits =
                this.createFutOpOrderExecutionRefReferenceUnitList(l_ifoOrderUnits);

            /* Step  1.15.3 Call createExecuteDetailsInfoList(
             * l_orderExecutionRefReferenceUnitList : WEB3AdminOROrderExecutionRefCommon[])
             */
            l_adminORDetailDispInfoUnits =
                l_adminOrderExecDataManager.createExecuteDetailsInfoList(
                    l_adminORFutOpOrderExecRefUnits);

        }

        // Step 1.16 Call getInstitution()
        l_gentradeInstitution = (WEB3GentradeInstitution) l_administrator.getInstitution();

        //get�����J�z�����敪(�����^�C�v : ProductTypeEnum,
        //�敨�^�I�v�V�����敪 : String, �o���I���敪 : String)
        //[����]
        //�����^�C�v�F�@@ProductTypeEnum.�敨�I�v�V����
        //�敨�^�I�v�V�����敪�F�@@"�I�v�V����"
        //�o���I���敪�F�@@"DEFAULT"
        l_strOptionCarryoverEndType =
            l_gentradeInstitution.getCarryoverEndType(
                ProductTypeEnum.IFO,
                WEB3FuturesOptionDivDef.OPTION,
                WEB3OrderexecutionEndTypeDef.DEFAULT);

        //get�����J�z�����敪(�����^�C�v : ProductTypeEnum,
        //�敨�^�I�v�V�����敪 : String, �o���I���敪 : String)
        //[����]
        //�����^�C�v�F�@@ProductTypeEnum.�敨�I�v�V����
        //�敨�^�I�v�V�����敪�F�@@"�敨"
        //�o���I���敪�F�@@"DEFAULT"
        l_strFutureCarryoverEndType =
            l_gentradeInstitution.getCarryoverEndType(
                ProductTypeEnum.IFO,
                WEB3FuturesOptionDivDef.FUTURES,
                WEB3OrderexecutionEndTypeDef.DEFAULT);

        // Step 1.19 create response
        l_response =
            (WEB3AdminORFutOpOrderExecutionRefReferenceResponse) l_request.createResponse();

        /* Step 1.20  If return of removeBeforeCarryoverOrderUnit() != null then set page details,
         * futOpeOrderExecutionRefList and detailDispInfoList as obtained. Otherwise set
         * initial values for page details, futOpeOrderExecutionRefList and detailDispInfoList
         */
        if (l_ifoOrderUnitParams == null)
        {
            l_response.opCarryoverEndType = l_strOptionCarryoverEndType;
            l_response.fuCarryoverEndType = l_strFutureCarryoverEndType;
            l_response.totalPages = "0";
            l_response.totalRecords = "0";
            l_response.pageIndex = "0";
            l_response.detailDispInfoList = null;
            l_response.futOpeOrderExecutionRefList = null;

        } else
        {
            l_response.opCarryoverEndType = l_strOptionCarryoverEndType;
            l_response.fuCarryoverEndType = l_strFutureCarryoverEndType;
            l_response.totalPages =
                WEB3StringTypeUtility.formatNumber(l_pageIndexInfo.getTotalPages());
            l_response.totalRecords =
                WEB3StringTypeUtility.formatNumber(l_pageIndexInfo.getTotalRecords());
            l_response.pageIndex = l_request.pageIndex;
            l_response.detailDispInfoList = l_adminORDetailDispInfoUnits;
            l_response.futOpeOrderExecutionRefList = l_adminORFutOpOrderExecRefUnits;
        }

        log.exiting(STR_METHOD_NAME);

        // Step 1.21 return response
        return l_response;

    }

    /**
     * (create�戵���i�ꗗ)<BR>
     * <BR>
     * �����̏��i���{���ɂ��戵�\�ȏ��i�Ǝ���̑g�ݍ��킹���쐬���A<BR>
     * �ԋp����B<BR>
     * <BR>
     * �P�jArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�p�����[�^.���i���{���.�I�v�V�������{�t���O == true�̏ꍇ�A<BR>
     * �@@�I�v�V�����̏��i�敪�Ǝ���敪�̑g�ݍ��킹���쐬����B<BR>
     * �@@�����Ώۂ̎���敪(*1)�ɂ��āA�ȉ��̏������J��Ԃ��B<BR>
     * <BR>
     * �@@�Q�|�P�j�戵���i�C���X�^���X�𐶐�����B<BR>
     * �@@�Q�|�Q�j���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@���i�敪 = "�I�v�V����"<BR>
     * �@@�@@����敪 = �����Ώۂ̎���敪<BR>
     * �@@�Q�|�R�j��������ArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B<BR>
     * <BR>
     * �@@(*1)�I�v�V�����̏����Ώێ���敪<BR>
     * �@@�@@�E"�w���I�v�V�����V�K��������"<BR>
     * �@@�@@�E"�w���I�v�V�����V�K��������"<BR>
     * �@@�@@�E"�w���I�v�V���������ԍϒ���"<BR>
     * �@@�@@�E"�w���I�v�V���������ԍϒ���"<BR>
     * <BR>
     * �R�j�p�����[�^.���i���{���.�敨���{�t���O == true�̏ꍇ�A<BR>
     * �@@�敨�̏��i�敪�Ǝ���敪�̑g�ݍ��킹���쐬����B<BR>
     * �@@�����Ώۂ̎���敪(*2)�ɂ��āA�ȉ��̏������J��Ԃ��B<BR>
     * <BR>
     * �@@�R�|�P�j�戵���i�C���X�^���X�𐶐�����B<BR>
     * �@@�R�|�Q�j���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@���i�敪 = "�敨"<BR>
     * �@@�@@����敪 = �����Ώۂ̎���敪<BR>
     * �@@�R�|�R�j��������ArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B<BR>
     * <BR>
     * �@@(*2)�敨�̏����Ώێ���敪<BR>
     * �@@�@@�E"�w���敨�V�K��������"<BR>
     * �@@�@@�E"�w���敨�V�K��������"<BR>
     * �@@�@@�E"�w���敨�����ԍϒ���"<BR>
     * �@@�@@�E"�w���敨�����ԍϒ���"<BR>
     * <BR>
     * �S�j��������ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * <BR>
     * ---------<English>------------<BR>
     * <BR>
     * createTradingProductList<BR>
     * <BR>
     * Make a combination of a handling possible product and a handling possible
     * trading by the argument, l_productExecutionInfo, and return it.<BR>
     * <BR>
     * 1)Create ArrayList<BR>
     * <BR>
     * 2)If l_productExecutionInfo.optionFlag == true,<BR>
     * �@@Create a combination of productDiv of Def.OPTION and tradingType<BR>
     * �@@Loop the following process for tradingType(*1) to be processed<BR>
     * <BR>
     * �@@2-1)Create a WEB3AdminORTradingProductUnit instance<BR>
     * �@@2-2)Set the following properties into the created instance.<BR>
     * �@@�@@productDiv = Def.OPTION<BR>
     * �@@�@@tradingType = tradingType to be processed<BR>
     * �@@2-3)Add the instance set into 'Property set' into the created ArrayList.<BR>
     * <BR>
     * �@@(*1)The tradingType about option<BR>
     * �@@�@@�EDef.IDX_OPTIONS_BUY_TO_OPEN<BR>
     * �@@�@@�EDef.IDX_OPTIONS_SELL_TO_OPEN
     * <BR>
     * �@@�@@�EDef.IDX_OPTIONS_BUY_TO_CLOSE
     * <BR>
     * �@@�@@�EDef.IDX_OPTIONS_SELL_TO_CLOSE
     * <BR>
     * <BR>
     * 3)If l_productExecutionInfo.futureFlag == true<BR>
     * �@@Create a combination of productDiv of Def.FUTURE and tradingDiv<BR>
     * �@@Loop the following process for tradingType(*2) to be processed<BR>
     * <BR>
     * �@@3-1)Create a WEB3AdminORTradingProductUnit instance<BR>
     * �@@3-2)Set the following properties into the created instance.<BR>
     * �@@�@@productDiv = Def.FUTURE<BR>
     * �@@�@@tradingType = tradingDiv to be processed<BR>
     * �@@3-3)Add the instance set into 'Property set' into the created ArrayList.<BR>
     * <BR>
     * �@@(*2)The tradingType about futures<BR>
     * �@@�@@�EDef.IDX_FUTURES_BUY_TO_OPEN
     * <BR>
     * �@@�@@�EDef.IDX_FUTURES_SELL_TO_OPEN
     * <BR>
     * �@@�@@�EDef.IDX_FUTURES_BUY_TO_CLOSE
     * <BR>
     * �@@�@@�EDef.IDX_FUTURES_SELL_TO_CLOSE
     * <BR>
     * <BR>
     * 4)Return return values of the created ArrayList.toArray()<BR>
     * <BR>
     * @@param l_productExecutionInfo - (���i���{���)<BR>
     * <BR>
     * ���i���{���I�u�W�F�N�g<BR>
     * <BR>
     * l_productExecutionInfo<BR>
     * <BR>
     * @@return WEB3AdminORTradingProductUnit[]
     * @@roseuid 41C6C91A0343
     */
    protected WEB3AdminORTradingProductUnit[] createTradingProductList(
        WEB3AdminProductExecutionInfo l_productExecutionInfo)
    {
        final String STR_METHOD_NAME = "createTradingProductList(WEB3ProductExecutionInfo)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminORTradingProductUnit l_adminORTradProdUnit = null;
        WEB3AdminORTradingProductUnit[] l_adminORTradProdUnits = null;
        String[] l_strTradingTypes = null;
        int l_intTradingTypeCnt = 0;
        List l_lisAdminORTradProd = null;
        int l_intAdminORTradProdCnt = 0;

        // Step 1 Create ArrayList
        l_lisAdminORTradProd = new ArrayList();

        // Step 2 If optionFlag = true, create a combination of productDiv(OPTION) and tradingType
        if (l_productExecutionInfo.optionFlag)
        {
            l_strTradingTypes =
                new String[]
                    {
                        String.valueOf(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.intValue()),
                        String.valueOf(OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.intValue()),
                        String.valueOf(OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE.intValue()),
                        String.valueOf(OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE.intValue())
                    };
            l_intTradingTypeCnt = l_strTradingTypes.length;
            for (int i = 0; i < l_intTradingTypeCnt; i++)
            {
                l_adminORTradProdUnit = new WEB3AdminORTradingProductUnit();
                l_adminORTradProdUnit.productDiv = WEB3AdminProductDivDef.OPTION;
                l_adminORTradProdUnit.tradingType = l_strTradingTypes[i];
                l_lisAdminORTradProd.add(l_adminORTradProdUnit);
            }
        }

        // Step 3 If futureFlag = true, create a combination of productDiv(FUTURE) and tradingType
        if (l_productExecutionInfo.futureFlag)
        {
            l_strTradingTypes =
                new String[]
                    {
                        String.valueOf(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.intValue()),
                        String.valueOf(OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.intValue()),
                        String.valueOf(OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE.intValue()),
                        String.valueOf(OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE.intValue())
                    };
            l_intTradingTypeCnt = l_strTradingTypes.length;
            for (int i = 0; i < l_intTradingTypeCnt; i++)
            {
                l_adminORTradProdUnit = new WEB3AdminORTradingProductUnit();
                l_adminORTradProdUnit.productDiv = WEB3AdminProductDivDef.FUTURE;
                l_adminORTradProdUnit.tradingType = l_strTradingTypes[i];
                l_lisAdminORTradProd.add(l_adminORTradProdUnit);
            }
        }

        l_intAdminORTradProdCnt = l_lisAdminORTradProd.size();

        // Do ArrayList.toArray() if size is greater than zero
        if (l_intAdminORTradProdCnt > 0)
        {
            l_adminORTradProdUnits = new WEB3AdminORTradingProductUnit[l_intAdminORTradProdCnt];
            l_adminORTradProdUnits =
                (WEB3AdminORTradingProductUnit[]) l_lisAdminORTradProd.toArray(
                    l_adminORTradProdUnits);
        }

        log.exiting(STR_METHOD_NAME);

        // Step 4 return created ArrayList.toArray()
        return l_adminORTradProdUnits;

    }

    /**
     * (create��������������)<BR>
     * (createQueryString)<BR>
     * ����������������쐬����B<BR>
     * <BR>
     * �P�j��������������C���X�^���X(�FString)�𐶐�����B<BR>
     * <BR>
     * �Q�j�p�����[�^.����ID�ꗗ != null�̏ꍇ�A<BR>
     * �@@����ID����������������ɒǉ�����B<BR>
     * �@@�p�����[�^.����ID�ꗗ�̗v�f����"?"��ǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and product_id in (?,?,,,)"<BR>
     * <BR>
     * �@@�p�����[�^.����ID�ꗗ = null�@@���@@�p�����[�^.���N�G�X�g�f�[�^.�w����� != null�̏ꍇ�A<BR>
     * �@@�w����ʂ���������������ɒǉ�����B<BR>
     * �@@�������������� += "and product_id like ? "<BR>
     * <BR>
     * �R�j�p�����[�^.���N�G�X�g�f�[�^.���s���� != null�̏ꍇ�A<BR>
     * �@@���s��������������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and execution_condition_type = ? "<BR>
     * <BR>
     * �S�j�p�����[�^.���N�G�X�g�f�[�^.���������敪 != null�̏ꍇ�A<BR>
     * <BR>
     * �@@�������������� += "and nvl(org_order_condition_type,order_condition_type) = ? "<BR>  
     * <BR>
     * �@@���敨OP�����P�ʒ����P�ʃe�[�u��.�����������ɒl���ݒ肳��Ă���ꍇ�́A<BR>  
     * �@@�@@�@@���������������ƂɌ�������  <BR>
     * �@@�@@�����������ɒl���ݒ肳��Ă��Ȃ��ꍇ�́A  <BR>
     * �@@�@@�@@�敨OP�����P�ʃe�[�u��.�������������ƂɌ������� <BR>
     * <BR>
     * �T�j�p�����[�^.���N�G�X�g�f�[�^.���������敪 != null�̏ꍇ�A<BR>
     * �@@���������𔻕ʂ����������������������ɒǉ�����B<BR>
     * <BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.���������敪 == "��������"�̏ꍇ]<BR>
     * <BR>
     * �@@�������������� += "and first_order_unit_id is null<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@and evening_session_carryover_flag = ? " <BR>
     * <BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.���������敪 == "�o����܂Œ���"�̏ꍇ]<BR>
     * �@@�@@�������������� +=<BR>
     * �@@�@@"and ( first_order_unit_id in (select order_unit_id from ifo_order_unit <BR>
     * �@@�@@�@@�@@�@@�@@where first_order_unit_id is not null) or first_order_unit_id =?) "<BR>
     * <BR>
     * �@@�@@�����񒍕��D���񒍕��̒����P��ID��null�ɊY�����钍���P�� or ���񒍕�<BR>
     * <BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.���������敪 == "�[��܂Œ���"�̏ꍇ]<BR>
     * �@@�@@�������������� +=<BR>
     * �@@�@@" and (( first_order_unit_id is null and evening_session_carryover_flag = ? ) "<BR>
     * �@@�@@�@@�@@�@@�@@�@@or ( first_order_unit_id in (select order_unit_id from ifo_order_unit  <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@where first_order_unit_id is null <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@and evening_session_carryover_flag = ? ))) " <BR>
     * <BR>
     * �@@�@@�@@�[��O�J�z�O�F�@@�����P�ʁD���񒍕��̒����P��ID��null���A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�[��O�J�z�Ώۃt���O��"�[��O�J�z����" <BR>
     * �@@�@@�@@�[��O�J�z��F�@@���񒍕��D���񒍕��̒����P��ID��null���A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�[��O�J�z�Ώۃt���O��"�[��O�J�z����"�ɊY�����钍���P�� <BR>
     * <BR>
     * �U�j�p�����[�^.���N�G�X�g�f�[�^.������ԋ敪 != null�̏ꍇ�A <BR>
     * �@@������Ԃ𔻕ʂ����������������������ɒǉ�����B <BR>
     * <BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.������ԋ敪 == "�ꕔ����"�̏ꍇ] <BR>
     * �@@�@@�������������� += "and executed_quantity is not null" <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and executed_quantity != ? " <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and order_open_status = ? " <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and expiration_status = ? " <BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.������ԋ敪 == "�S������"�̏ꍇ] <BR>
     * �@@�@@�������������� += " and (executed_quantity is null " <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "or executed_quantity = ?) " <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and order_open_status = ? " <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and expiration_status = ? " <BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.������ԋ敪 == "����"�̏ꍇ] <BR>
     * �@@�@@�������������� += "and order_open_status = ? " <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and expiration_status = ? " <BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.������ԋ敪 == "�蓮����"�̏ꍇ] <BR>
     * �@@�@@�������������� += "and order_open_status = ? " <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and expiration_status = ? " <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and error_reason_code in (?, ?) " <BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.������ԋ敪 == "�J�z��"�̏ꍇ] <BR>
     * �@@�@@�������������� += "and first_order_unit_id > ? " <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and order_status = ? " <BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.������ԋ敪 == "�J�z���s"�̏ꍇ]  <BR>
     * �@@�@@�������������� += "and expiration_date >= ? " <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and order_open_status = ? " <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and expiration_status = ? " <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and error_reason_code not in( ?, ?) " <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and (first_order_unit_id is not null or <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@evening_session_carryover_flag = ?) " <BR>
     * �@@[��L�ȊO�̏ꍇ] <BR>
     * �@@�@@�������������� += "and order_status = ? " <BR>
     * <BR>
     * �V�j�쐬�������������������ԋp����B <BR>
     * <BR>
     * @@param l_productIdList - �i����ID�ꗗ�j<BR>
     * <BR>
     * ����ID�ꗗ<BR>
     * <BR>
     * @@param l_request - �i���N�G�X�g�f�[�^�j<BR>
     * <BR>
     * ���N�G�X�g�f�[�^<BR>
     * <BR>
     * @@return java.lang.String
     * @@throws WEB3BaseException
     * @@roseuid 41C6D7460297
     */
    protected String createQueryString(
        String[] l_productIdList, 
        WEB3AdminORFutOpOrderExecutionRefReferenceRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createQueryString(String[], " +
            "WEB3AdminORFutOpOrderExecutionRefReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        StringBuffer l_sbQueryCond = null;
        int l_intProdIdListCnt = 0;

        // Step 1 Create an instance of l_sbQueryCond
        l_sbQueryCond = new StringBuffer();

        // Step 2 If l_productIdList is not null then add all productIds to where condition
        if (l_productIdList != null)
        {
            l_intProdIdListCnt = l_productIdList.length;
            l_sbQueryCond.append(" and product_id in (");
            for (int i = 0; i < l_intProdIdListCnt; i++)
            {
                l_sbQueryCond.append("?, ");
            }
            l_sbQueryCond.replace(l_sbQueryCond.length() - 2, l_sbQueryCond.length() - 1, "");
            l_sbQueryCond.append(")");
        }

        //�p�����[�^.����ID�ꗗ = null�@@���@@�p�����[�^.���N�G�X�g�f�[�^.�w����� != null�̏ꍇ�A
        //�w����ʂ���������������ɒǉ�����B
        //�������������� += "and product_id like ? "
        if (l_productIdList == null && l_request.targetProductCode != null)
        {
            l_sbQueryCond.append("and product_id like ? ");
        }

        //�p�����[�^.���N�G�X�g�f�[�^.���s���� != null�̏ꍇ�A
        //���s��������������������ɒǉ�����B
        if (l_request.execCondType != null)
        {
            l_sbQueryCond.append(" and execution_condition_type = ?");
        }

        //�p�����[�^.���N�G�X�g�f�[�^.���������敪 != null�̏ꍇ
        if (l_request.orderCondType != null)
        {
            l_sbQueryCond.append(" and nvl(org_order_condition_type,order_condition_type) = ?");
        }

        //�T�j�p�����[�^.���N�G�X�g�f�[�^.���������敪 != null�̏ꍇ�A
        if (l_request.expirationDateType != null)
        {
            //[�p�����[�^.���N�G�X�g�f�[�^.���������敪 == "��������"�̏ꍇ]
            if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_request.expirationDateType))
            {
                l_sbQueryCond.append(" and first_order_unit_id is null ");
                l_sbQueryCond.append(" and evening_session_carryover_flag = ? ");
            }
            //[�p�����[�^.���N�G�X�g�f�[�^.���������敪 == "�o����܂Œ���"�̏ꍇ]
            else if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_request.expirationDateType))
            {
                l_sbQueryCond.append(" and ( first_order_unit_id in ");
                l_sbQueryCond.append(" (select order_unit_id from ifo_order_unit ");
                l_sbQueryCond.append(" where first_order_unit_id is not null) ");
                l_sbQueryCond.append(" or first_order_unit_id =?) ");
            }
            //[�p�����[�^.���N�G�X�g�f�[�^.���������敪 == "�[��܂Œ���"�̏ꍇ]
            else if (WEB3OrderExpirationDateTypeDef.EVENING_SESSION_ORDER.equals(l_request.expirationDateType))
            {
                l_sbQueryCond.append(" and (( first_order_unit_id is null ");
                l_sbQueryCond.append(" and evening_session_carryover_flag = ? ) ");
                l_sbQueryCond.append(" or ( first_order_unit_id in ");
                l_sbQueryCond.append(" (select order_unit_id from ifo_order_unit ");
                l_sbQueryCond.append(" where first_order_unit_id is null ");
                l_sbQueryCond.append(" and evening_session_carryover_flag = ? ))) ");
            }
        }

        //�U�j�p�����[�^.���N�G�X�g�f�[�^.������ԋ敪 != null�̏ꍇ�A
        if (l_request.orderState != null)
        {
            //[�p�����[�^.���N�G�X�g�f�[�^.������ԋ敪 == "�ꕔ����"�̏ꍇ]
            if (WEB3OrderStatusDef.PART_INAFFECTED.equals(l_request.orderState))
            {
                l_sbQueryCond.append(" and executed_quantity is not null ");
                l_sbQueryCond.append(" and executed_quantity != ? ");
                l_sbQueryCond.append(" and order_open_status = ? ");
                l_sbQueryCond.append(" and expiration_status = ? ");
            }
            //[�p�����[�^.���N�G�X�g�f�[�^.������ԋ敪 == "�S������"�̏ꍇ]
            else if (WEB3OrderStatusDef.FULL_INAFFECTED.equals(l_request.orderState))
            {
                l_sbQueryCond.append(" and (executed_quantity is null ");
                l_sbQueryCond.append(" or executed_quantity = ?) ");
                l_sbQueryCond.append(" and order_open_status = ? ");
                l_sbQueryCond.append(" and expiration_status = ? ");
            }
            //[�p�����[�^.���N�G�X�g�f�[�^.������ԋ敪 == "����"�̏ꍇ]
            else if (WEB3OrderStatusDef.CLOSED.equals(l_request.orderState))
            {
                l_sbQueryCond.append(" and order_open_status = ? ");
                l_sbQueryCond.append(" and expiration_status = ? ");
            }
            //[�p�����[�^.���N�G�X�g�f�[�^.������ԋ敪 == "�蓮����"�̏ꍇ]
            else if (WEB3OrderStatusDef.MANUAL_EXPIRED.equals(l_request.orderState))
            {
                l_sbQueryCond.append(" and order_open_status = ? ");
                l_sbQueryCond.append(" and expiration_status = ? ");
                l_sbQueryCond.append(" and error_reason_code in (?, ?) ");
            }
            //[�p�����[�^.���N�G�X�g�f�[�^.������ԋ敪 == "�J�z��"�̏ꍇ]
            else if (WEB3OrderStatusDef.TRANSFERED.equals(l_request.orderState))
            {
                l_sbQueryCond.append(" and first_order_unit_id > ? ");
                l_sbQueryCond.append(" and order_status = ? ");
            }
            //[�p�����[�^.���N�G�X�g�f�[�^.������ԋ敪 == "�J�z���s"�̏ꍇ]
            else if (WEB3OrderStatusDef.NOT_TRANSFERED.equals(l_request.orderState))
            {
                l_sbQueryCond.append(" and expiration_date >= ? ");
                l_sbQueryCond.append(" and order_open_status = ? ");
                l_sbQueryCond.append(" and expiration_status = ? ");
                l_sbQueryCond.append(" and error_reason_code not in( ?, ?) ");
                l_sbQueryCond.append(" and (first_order_unit_id is not null ");
                l_sbQueryCond.append(" or evening_session_carryover_flag = ?) ");
            }
            //[��L�ȊO�̏ꍇ]
            else
            {
                l_sbQueryCond.append(" and order_status = ? ");
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_sbQueryCond.toString();

    }

    /**
     * (create���������f�[�^�R���e�i)<BR>
     * (createQueryDataContainer) <BR>
     * ���������f�[�^�R���e�i���쐬����B<BR>
     * <BR>
     * �P�jArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�p�����[�^.����ID�ꗗ != null�̏ꍇ�A<BR>
     * �@@����ID�ꗗ�̑S�v�f�𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@�p�����[�^.����ID�ꗗ = null�@@���@@�p�����[�^.���N�G�X�g�f�[�^.�w����� != null�̏ꍇ�A<BR>
     * �@@'%'+�p�����[�^.���N�G�X�g�f�[�^.�w����ʂ̉�2�� �𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �R�j�p�����[�^.���N�G�X�g�f�[�^.���s���� != null�̏ꍇ�A <BR>
     * �@@���s�����𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@�����s�����́Athis.get���s����()�ɂĎ擾����B<BR>
     * <BR>
     * �@@[get���s����()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@���s�����F�@@�p�����[�^.���N�G�X�g�f�[�^.���s����<BR>
     * <BR>
     * �S�j�p�����[�^.���N�G�X�g�f�[�^.���������敪 != null�̏ꍇ�A<BR>
     * �@@�p�����[�^.���N�G�X�g�f�[�^.���������敪�𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR> 
     * �T�j�p�����[�^.���N�G�X�g�f�[�^.���������敪 != null�̏ꍇ�A <BR>
     * �@@���������𔻕ʂ�������𐶐�����ArrayList�ɒǉ�����B <BR>
     * <BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.���������敪 == "��������"�̏ꍇ] <BR>
     * �@@�@@�E"�[��O�J�z�Ȃ�"�i�[��O�J�z�Ώۃt���O�j <BR>
     * �@@ <BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^�D���������敪 == "�o����܂Œ���"�̏ꍇ] <BR>
     * �@@�@@�E0 <BR>
     * <BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.���������敪 == "�[��܂Œ���"�̏ꍇ] <BR>
     * �@@�@@�E"�[��O�J�z����"�i�[��O�J�z�Ώۃt���O�j <BR>
     * �@@�@@�E"�[��O�J�z����"�i�[��O�J�z�Ώۃt���O�j <BR>
     * <BR>
     * �U�j�p�����[�^.���N�G�X�g�f�[�^.������ԋ敪 != null�̏ꍇ�A <BR>
     * �@@������Ԃ𔻕ʂ���������ォ�珇�ɐ�������ArrayList�ɒǉ�����B <BR>
     * <BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.������ԋ敪 == "�ꕔ����"�̏ꍇ] <BR>
     * �@@�@@�E0 <BR>
     * �@@�@@�E"�N���[�Y"(�����L�����) <BR>
     * �@@�@@�E"�}�[�P�b�g����"(�����敪) <BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.������ԋ敪 == "�S������"�̏ꍇ] <BR>
     * �@@�@@�E0 <BR>
     * �@@�@@�E"�N���[�Y"(�����L�����) <BR>
     * �@@�@@�E"�}�[�P�b�g����"(�����敪) <BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.������ԋ敪 == "����"�̏ꍇ] <BR>
     * �@@�@@�E"�N���[�Y"(�����L�����) <BR>
     * �@@�@@�E"�I��"(�����敪) <BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.������ԋ敪 == "�蓮����"�̏ꍇ] <BR>
     * �@@�@@�E"�N���[�Y"(�����L�����) <BR>
     * �@@�@@�E"�}�[�P�b�g����"(�����敪) <BR>
     * �@@�@@�E"�����Ǘ��Ҏ蓮������"�i�����G���[���R�R�[�h�j <BR>
     * �@@�@@�E"�敨OP�Ǘ��Ҏ蓮������"�i�����G���[���R�R�[�h�j <BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.������ԋ敪 == "�J�z��"�̏ꍇ] <BR>
     * �@@�@@�E0 <BR>
     * �@@�@@�E"��t�ρi�V�K�����j"(�������) <BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.������ԋ敪 == "�J�z���s"�̏ꍇ]  <BR>
     * �@@�@@�E�Ɩ����t(*1) <BR>
     * �@@�@@�E"�N���[�Y"(�����L�����) <BR>
     * �@@�@@�E"�I��"(�����敪) <BR>
     * �@@�@@�E"����"(�����G���[���R�R�[�h) <BR>
     * �@@�@@�E"�g���K�[�����Ǘ��Ҏ蓮������"(�����G���[���R�R�[�h) <BR>
     * �@@�@@�E"�[��O�J�z����"�i�[��O�J�z�Ώۃt���O�j <BR>
     * �@@[��L�ȊO�̏ꍇ] <BR>
     * �@@�@@�p�����[�^.���N�G�X�g�f�[�^.������ԋ敪��ǉ�����B <BR>
     * <BR>
     * �V�j��������ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * <BR>
     * (*1)GtlUtils.getTradingSystem().getBizDate()<BR>
     * <BR>
     * @@param l_productIdList - �i����ID�ꗗ�j<BR>
     * <BR>
     * ����ID�ꗗ<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * <BR>
     * ���N�G�X�g�f�[�^<BR>
     * <BR>
     * @@return String[]
     * @@throws WEB3BaseException
     * @@roseuid 41D2582A013A
     */
    protected String[] createQueryDataContainer(
        String[] l_productIdList, 
        WEB3AdminORFutOpOrderExecutionRefReferenceRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createQueryDataContainer(" +
            "String[], WEB3AdminORFutOpOrderExecutionRefReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        String[] l_strProdIdExecCondTypes = null;
        int l_intIfoOrderExecCondType = 0;
        List l_lisProdIdExecCondType = null;

        // Step 1 Create ArrayList
        l_lisProdIdExecCondType = new ArrayList();

        // Step 2 If l_productIdList != null then add all elements to the ArrayList
        if (l_productIdList != null)
        {
            l_lisProdIdExecCondType.addAll(Arrays.asList(l_productIdList));
        }

        //�p�����[�^.����ID�ꗗ = null�@@���@@�p�����[�^.���N�G�X�g�f�[�^.�w����� != null�̏ꍇ�A
        //'%'+�p�����[�^.���N�G�X�g�f�[�^.�w����ʂ̉�2�� �𐶐�����ArrayList�ɒǉ�����B
        if (l_productIdList == null && l_request.targetProductCode != null)
        {
            String l_strValue = l_request.targetProductCode.substring(2, 4);
            l_lisProdIdExecCondType.add("%" + l_strValue);
        }

        // �p�����[�^.���N�G�X�g�f�[�^.���s���� != null�̏ꍇ
        if (l_request.execCondType != null)
        {
            l_intIfoOrderExecCondType = this.getExecCondType(l_request.execCondType).intValue();
            l_lisProdIdExecCondType.add(
                WEB3StringTypeUtility.formatNumber(l_intIfoOrderExecCondType));
        }

        //�p�����[�^.���N�G�X�g�f�[�^.���������敪 != null�̏ꍇ
        if (l_request.orderCondType != null)
        {
            l_lisProdIdExecCondType.add(l_request.orderCondType);
        }

        //�T�j�p�����[�^.���N�G�X�g�f�[�^.���������敪 != null�̏ꍇ
        if (l_request.expirationDateType != null)
        {
            //[�p�����[�^.���N�G�X�g�f�[�^.���������敪 == "��������"�̏ꍇ]
            if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_request.expirationDateType))
            {
                l_lisProdIdExecCondType.add(String.valueOf(BooleanEnum.FALSE.intValue()));
            }
            //[�p�����[�^.���N�G�X�g�f�[�^.���������敪 == "�o����܂Œ���"�̏ꍇ]
            else if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_request.expirationDateType))
            {
                l_lisProdIdExecCondType.add(String.valueOf(0));
            }
            //[�p�����[�^.���N�G�X�g�f�[�^.���������敪 == "�[��܂Œ���"�̏ꍇ]
            else if (WEB3OrderExpirationDateTypeDef.EVENING_SESSION_ORDER.equals(l_request.expirationDateType))
            {
                l_lisProdIdExecCondType.add(String.valueOf(BooleanEnum.TRUE.intValue()));
                l_lisProdIdExecCondType.add(String.valueOf(BooleanEnum.TRUE.intValue()));
            }
        }

        //�U�j�p�����[�^.���N�G�X�g�f�[�^.������ԋ敪 != null�̏ꍇ
        if (l_request.orderState != null)
        {
            //[�p�����[�^.���N�G�X�g�f�[�^.������ԋ敪 == "�ꕔ����"�̏ꍇ]
            if (WEB3OrderStatusDef.PART_INAFFECTED.equals(l_request.orderState))
            {
                l_lisProdIdExecCondType.add(String.valueOf(0));
                l_lisProdIdExecCondType.add(String.valueOf(OrderOpenStatusEnum.CLOSED.intValue()));
                l_lisProdIdExecCondType.add(String.valueOf(OrderExpirationStatusEnum.INVALIDATED_BY_MKT.intValue()));
            }
            //[�p�����[�^.���N�G�X�g�f�[�^.������ԋ敪 == "�S������"�̏ꍇ]
            else if (WEB3OrderStatusDef.FULL_INAFFECTED.equals(l_request.orderState))
            {
                l_lisProdIdExecCondType.add(String.valueOf(0));
                l_lisProdIdExecCondType.add(String.valueOf(OrderOpenStatusEnum.CLOSED.intValue()));
                l_lisProdIdExecCondType.add(String.valueOf(OrderExpirationStatusEnum.INVALIDATED_BY_MKT.intValue()));
            }
            //[�p�����[�^.���N�G�X�g�f�[�^.������ԋ敪 == "����"�̏ꍇ]
            else if (WEB3OrderStatusDef.CLOSED.equals(l_request.orderState))
            {
                l_lisProdIdExecCondType.add(String.valueOf(OrderOpenStatusEnum.CLOSED.intValue()));
                l_lisProdIdExecCondType.add(String.valueOf(OrderExpirationStatusEnum.EXPIRED.intValue()));
            }
            //[�p�����[�^.���N�G�X�g�f�[�^.������ԋ敪 == "�蓮����"�̏ꍇ]
            else if (WEB3OrderStatusDef.MANUAL_EXPIRED.equals(l_request.orderState))
            {
                l_lisProdIdExecCondType.add(String.valueOf(OrderOpenStatusEnum.CLOSED.intValue()));
                l_lisProdIdExecCondType.add(
                    String.valueOf(OrderExpirationStatusEnum.INVALIDATED_BY_MKT.intValue()));
                l_lisProdIdExecCondType.add(WEB3ErrorReasonCodeDef.EQUITY_ADMIN_MANUAL_EXPIRED);
                l_lisProdIdExecCondType.add(WEB3ErrorReasonCodeDef.FUTURE_OP_ADMIN_MANUAL_EXPIRED);
            }
            //[�p�����[�^.���N�G�X�g�f�[�^.������ԋ敪 == "�J�z��"�̏ꍇ]
            else if (WEB3OrderStatusDef.TRANSFERED.equals(l_request.orderState))
            {
                l_lisProdIdExecCondType.add(String.valueOf(0));
                l_lisProdIdExecCondType.add(String.valueOf(OrderStatusEnum.ACCEPTED.intValue()));
            }
            //[�p�����[�^.���N�G�X�g�f�[�^.������ԋ敪 == "�J�z���s"�̏ꍇ]
            else if (WEB3OrderStatusDef.NOT_TRANSFERED.equals(l_request.orderState))
            {
                Date l_datSystemTimestamp = GtlUtils.getTradingSystem().getBizDate();
                l_lisProdIdExecCondType.add(
                    WEB3DateUtility.formatDate(l_datSystemTimestamp, WEB3GentradeTimeDef.DATE_FORMAT_YMD));
                l_lisProdIdExecCondType.add(String.valueOf(OrderOpenStatusEnum.CLOSED.intValue()));
                l_lisProdIdExecCondType.add(String.valueOf(OrderExpirationStatusEnum.EXPIRED.intValue()));
                l_lisProdIdExecCondType.add(WEB3ErrorReasonCodeDef.NORMAL);
                l_lisProdIdExecCondType.add(WEB3ErrorReasonCodeDef.TRIGGER_ADMIN_MANUAL_EXPIRED);
                l_lisProdIdExecCondType.add(String.valueOf(BooleanEnum.TRUE.intValue()));
            }
            //[��L�ȊO�̏ꍇ]
            else
            {
                l_lisProdIdExecCondType.add(l_request.orderState);
            }
        }
        l_strProdIdExecCondTypes = new String[l_lisProdIdExecCondType.size()];
        l_strProdIdExecCondTypes =
            (String[]) l_lisProdIdExecCondType.toArray(l_strProdIdExecCondTypes);

        log.exiting(STR_METHOD_NAME);
        
        // Step 5 Return created ArrayList.toArray()
        return l_strProdIdExecCondTypes;

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
     * �@@�@@�E�u���X�R�[�h�v�@@���@@�敨OP�����P��.���XID<BR>
     * �@@�@@�E�u�ڋq�R�[�h�v�@@���@@�敨OP�����P��.����ID<BR>
     * �@@�@@�E�u���҃R�[�h�iSONAR�j�v�@@���@@�敨OP�����P�ʈ��҃R�[�h�iSONAR�j<BR>
     * �@@�@@�E�u�����R�[�h�v�@@���@@�敨OP�����P��.����ID<BR>
     * �@@�@@�E�u�s��R�[�h�v�@@���@@�敨OP�����P��.�s��ID<BR>
     * �@@�@@�E�u����敪�v�@@���@@�敨OP�����P��.�������<BR>
     * �@@�@@�E�u�������ԁv�@@���@@�敨OP�����P��.�󒍓���<BR>
     * �@@�@@�E�u�������v�@@���@@�敨OP�����P��.������<BR>
     * �@@�@@�E�u���s�����v�@@���@@�敨OP�����P��.���s����<BR>
     * �@@�@@�E�u���������v�@@���@@�敨OP�����P��.�����������t<BR>
     * �@@�@@�E�u���������v�@@���@@(*1)<BR>
     * �@@�@@�E�u��n���v�@@���@@�敨OP�����P��.��n��<BR>
     * <BR>
     * �@@�R�|�Q�j�\�[�g�L�[.�����^�~���ɑΉ�����擾����<BR>
     * �@@�@@(asc or desc)���\�[�g����������ɒǉ�����B<BR>
     * <BR>
     * �S�j�\�[�g���������ɁA�����P�ʃe�[�u��.�X�V���t�������w��ŕt��<BR>
     * <BR>
     * �T�j�쐬�����\�[�g�����������ԋp����B<BR>
     * <BR>
     * (*1) nvl�i�敨OP�����P�ʃe�[�u��.����������, �敨OP�����P�ʃe�[�u��.���������j<BR>
     *      ��ݒ肷��B<BR>  
     * �@@�@@�@@���敨OP�����P�ʃe�[�u��.�����������ɒl���ݒ肳��Ă���ꍇ�́A<BR>  
     * �@@�@@�@@�@@�@@�@@���������������ƂɃ\�[�g����  <BR>
     * �@@�@@�@@�@@�����������ɒl���ݒ肳��Ă��Ȃ��ꍇ�́A <BR> 
     * �@@�@@�@@�@@�@@�@@���������P�ʃe�[�u��.�������������ƂɃ\�[�g����  <BR>
     * <BR>
     * @@param l_sortKeys - (�\�[�g�L�[)<BR>
     * <BR>
     * �\�[�g�L�[<BR>
     * <BR>
     * @@return java.lang.String
     * @@roseuid 41C6D74602E5
     */
    protected String createSortCond(WEB3AdminOROrderExecutionSortKeyUnit[] l_sortKeys)
    {
        final String STR_METHOD_NAME = "createSortCond(WEB3AdminOROrderExecutionSortKeyUnit[])";
        log.entering(STR_METHOD_NAME);

        StringBuffer l_sbSortCond = null;
        int l_intSortKeysCnt = 0;

        // Step 2 Create sort condition string
        l_sbSortCond = new StringBuffer();

        // Step 3 Loop for as many elements of sortKeys
        l_intSortKeysCnt = l_sortKeys.length;
        for (int i = 0; i < l_intSortKeysCnt; i++)
        {
            // Add "," after each field
            if (i != 0)
            {
                l_sbSortCond.append(", ");
            }

            /* Step 3-1 Convert sortKeys.keyItem to the corresponding field names and add them
             * to the created sortCond string
             */
            if (WEB3AdminKeyItemDef.BRANCH_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_sbSortCond.append("ifo_order_unit.branch_id");
            } else if (WEB3AdminKeyItemDef.ACCOUNT_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_sbSortCond.append("substr(ifo_order_unit.account_id,9,6)");
            } else if (WEB3AdminKeyItemDef.SONAR_TRADER_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_sbSortCond.append("ifo_order_unit.sonar_trader_code");
            } else if (WEB3AdminKeyItemDef.PRODUCT_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_sbSortCond.append("ifo_order_unit.product_id");
            } else if (WEB3AdminKeyItemDef.MARKET_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_sbSortCond.append("ifo_order_unit.market_id");
            } else if (WEB3AdminKeyItemDef.TRADING_TYPE.equals(l_sortKeys[i].keyItem))
            {
                l_sbSortCond.append("ifo_order_unit.order_type");
            } else if (WEB3AdminKeyItemDef.ORDER_DATE.equals(l_sortKeys[i].keyItem))
            {
                l_sbSortCond.append("ifo_order_unit.received_date_time");
            } else if (WEB3AdminKeyItemDef.ORDER_BIZ_DATE.equals(l_sortKeys[i].keyItem))
            {
                l_sbSortCond.append("ifo_order_unit.biz_date");
            } else if (WEB3AdminKeyItemDef.EXEC_COND_TYPE.equals(l_sortKeys[i].keyItem))
            {
                l_sbSortCond.append("ifo_order_unit.execution_condition_type");
            } else if (WEB3AdminKeyItemDef.EXPIRATION_DATE.equals(l_sortKeys[i].keyItem))
            {
                l_sbSortCond.append("ifo_order_unit.expiration_date");
            } else if (WEB3AdminKeyItemDef.ORDER_COND_TYPE.equals(l_sortKeys[i].keyItem))
            {
                l_sbSortCond.append(
                    "nvl(ifo_order_unit.org_order_condition_type,"+
                    " ifo_order_unit.order_condition_type)");
            } else
            {
                l_sbSortCond.append("ifo_order_unit.delivery_date");
            }

            // Step 3-2 Add (asc or desc) to sort condition
            if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
            {
                l_sbSortCond.append(" desc");
            } else
            {
                l_sbSortCond.append(" asc");
            }

        }

        /* Step 4 Add ifo_order_unit.last_updated_timestamp to the
         * end of sortCond in ascending order
         */
        l_sbSortCond.append(", ifo_order_unit.last_updated_timestamp asc");

        log.exiting(STR_METHOD_NAME);

        // Step 5 Return created sort condition
        return l_sbSortCond.toString();

    }

    /**
     * (get�����P�ʈꗗ)<BR>
     * <BR>
     * �����̏����ɊY�����钍���P�ʂ̈ꗗ��ԋp����B<BR>
     * <BR>
     * �P�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@arg0�F�@@�敨OP�����P��Row.TYPE<BR>
     * �@@�@@arg1�F�@@�p�����[�^.��������������<BR>
     * �@@�@@arg2�F�@@�p�����[�^.�\�[�g����<BR>
     * �@@�@@arg3�F�@@null<BR>
     * �@@�@@arg4�F�@@�p�����[�^.���������f�[�^�R���e�i<BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �Q�j�������ʂ�ԋp����B<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * getOrderUnits<BR>
     * <BR>
     * Return the list of orderUnits corresponding to the conditions of arguments<BR>
     * <BR>
     * �P�jCall QueryProcessor.doFindAllQuery() method<BR>
     * <BR>
     * �@@[Parameter set into doFindAllQuery()]<BR>
     * �@@�@@arg0: l_ifoOrderUnitRow.TYPE<BR>
     * �@@�@@arg1: l_strQueryCond<BR>
     * �@@�@@arg2: l_strSortCond<BR>
     * �@@�@@arg3: null<BR>
     * �@@�@@arg4: l_strQueryCondDataContainer<BR>
     * <BR>
     * �@@Return null if there is no search result<BR>
     * <BR>
     * 2)Return the search result<BR>
     * <BR>
     * @@param l_strQueryCond - (��������������)<BR>
     * <BR>
     * ��������������<BR>
     * <BR>
     * l_strQueryCond<BR>
     * <BR>
     * @@param l_strQueryCondDataContainer - (���������f�[�^�R���e�i)<BR>
     * <BR>
     * ���������f�[�^�R���e�i<BR>
     * <BR>
     * l_strQueryCondDataContainer<BR>
     * <BR>
     *
     * @@param l_strSortCond - (�\�[�g����)<BR>
     * <BR>
     * �\�[�g����<BR>
     * <BR>
     * l_strSortCond<BR>
     * <BR>
     * @@return IfoOrderUnitParams[]
     * @@exception DataQueryException DataQueryException
     * @@exception DataNetworkException DataNetworkException
     * @@exception DataFindException DataFindException
     * @@roseuid 41C6D7460314
     */
    protected IfoOrderUnitParams[] getOrderUnits(
        String l_strQueryCond,
        String[] l_strQueryCondDataContainer,
        String l_strSortCond)
        throws DataFindException, DataNetworkException, DataQueryException
    {
        final String STR_METHOD_NAME = "getOrderUnits(String, String[], String)";
        log.entering(STR_METHOD_NAME);

        QueryProcessor l_queryProcessor = null;
        List l_lisSearchResult = null;
        IfoOrderUnitParams[] l_ifoOrderUnitParams = null;

        l_queryProcessor = Processors.getDefaultProcessor();

        // Step 1 Search ifo_order_unit table based on condition
        l_lisSearchResult =
            l_queryProcessor.doFindAllQuery(
                IfoOrderUnitRow.TYPE,
                l_strQueryCond,
                l_strSortCond,
                null,
                l_strQueryCondDataContainer);

        // Step 2 Return null if there is no search result. Otherwise return search result
        if (l_lisSearchResult.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;

        } else
        {
            l_ifoOrderUnitParams = new IfoOrderUnitParams[l_lisSearchResult.size()];
            l_lisSearchResult.toArray(l_ifoOrderUnitParams);

            log.exiting(STR_METHOD_NAME);
            return l_ifoOrderUnitParams;

        }
    }

    /**
     * (create�敨OP�������Ɖ�Unit�ꗗ)<BR>
     * <BR>
     * �����̒����P�ʈꗗ���A�敨OP�������Ɖ�Unit�̈ꗗ��
     * �쐬���A�ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��Ґ敨OP�������Ɖ�T�[�r�X)create�敨OP�������Ɖ�Unit�ꗗ�v<BR>
     * �Q��<BR>
     * <BR>
     * @@param l_orderUnits - (�����P�ʈꗗ)<BR>
     * <BR>
     * �敨OP�����P�ʂ̔z��<BR>
     * <BR>
     * @@throws NotFoundException NotFoundException
     * @@throws DataNetworkException DataNetworkException
     * @@throws WEB3BaseException
     * @@return WEB3AdminORFutOpOrderExecutionRefUnit[]
     * @@roseuid 41C6D7460334
     */
    protected WEB3AdminORFutOpOrderExecutionRefUnit[]
        createFutOpOrderExecutionRefReferenceUnitList(IfoOrderUnit[] l_orderUnits)
        throws
            NotFoundException,
            DataNetworkException,
            WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createFutOpOrderExecutionRefReferenceUnitList(IfoOrderUnit[])";
        final String DATE_FORMAT = "yyyyMMdd";

        log.entering(STR_METHOD_NAME);

        List l_lisOrderExecRefUnit = null;
        int l_intOrderUnitCnt = 0;
        long l_lngBranchId = 0L;
        long l_lngAccountId = 0L;
        WEB3GentradeAccountManager l_gentradeAccountManager = null;
        WEB3GentradeMainAccount l_gentradeMainAccount = null;
        FinApp l_finApp = null;
        IfoOrderUnitParams l_ifoOrderUnitParams = null;
        WEB3AdminOrderExecuteDataManager l_adminOrderExecDataManager = null;
        OrderTypeEnum l_orderType = null;
        IfoOrderExecutionConditionType l_ifoOrderExecCondType = null;
        WEB3AdminORFutOpOrderExecutionRefUnit l_adminORFutOpOrderExecRefUnit = null;
        WEB3AdminORFutOpOrderExecutionRefUnit[] l_adminORFutOpOrderExecRefUnits = null;
        String l_strProductDiv = null;
        boolean l_blnMarketOrder = false;
        String l_strOrderState = null;
        String l_strExecCondType = null;
        String l_strExecType = null;
        String l_strTradingType = null;
        Trader l_trader = null;
        Product l_product = null;
        SubAccountTypeEnum l_subAccountType = null;
        BigDecimal l_bdExecQuantity = null;
        BigDecimal l_bdExecAmountPrice = null;
        WEB3GentradeFinObjectManager l_gentradeFinObjectManager = null;

        // Step 1.1 Create ArrayList
        l_lisOrderExecRefUnit = new ArrayList();

        // Step 1.2 Loop process for as many times as the number of the elements of l_orderUnits
        l_intOrderUnitCnt = l_orderUnits.length;
        for (int i = 0; i < l_intOrderUnitCnt; i++)
        {
            l_ifoOrderUnitParams =
                new IfoOrderUnitParams((IfoOrderUnitRow)l_orderUnits[i].getDataSourceObject());

            // Step 1.2.1 Call getProduct()
            l_product = l_orderUnits[i].getProduct();

            // Step 1.2.2 Call isMarketOrder()
            l_blnMarketOrder = l_orderUnits[i].isMarketOrder();

            // Step 1.2.3 Call getBranchId()
            l_lngBranchId = l_orderUnits[i].getBranchId();

            l_finApp = (FinApp)Services.getService(FinApp.class);
            l_gentradeAccountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();

            // Step 1.2.4 Call getBranch(l_lngBranchId : long)
            Branch l_branch = l_gentradeAccountManager.getBranch(l_lngBranchId);

            // Step 1.2.5 Call getAccountId()
            l_lngAccountId = l_orderUnits[i].getAccountId();

            // Step 1.2.6 Call getMainAccount(l_lngAccountId : long)
            l_gentradeMainAccount =
                (WEB3GentradeMainAccount)l_gentradeAccountManager.getMainAccount(l_lngAccountId);

            // Step 1.2.7 Call getOpSubAccountType()
            l_subAccountType = l_gentradeMainAccount.getOpSubAccountType();

            // Step 1.2.9 Call getProductDiv(l_orderUnit : IfoOrderUnit)
            l_strProductDiv = this.getProductDiv(l_orderUnits[i]);

            l_adminOrderExecDataManager = new WEB3AdminOrderExecuteDataManager();

            // Step 1.2.10 Call getTradingType(l_orderType : OrderTypeEnum)
            l_orderType = l_ifoOrderUnitParams.order_type;
            l_strTradingType = l_adminOrderExecDataManager.getTradingType(l_orderType);

            // Step 1.2.11 Call getOrderStateDivPR(l_orderUnit : OrderUnit)
            l_strOrderState = l_adminOrderExecDataManager.getOrderStateDivPR(l_orderUnits[i]);

            // Step 1.2.12 Call getExecCondTypePR(l_execCondType : IfoOrderExecutionConditionType)
            l_ifoOrderExecCondType = l_ifoOrderUnitParams.execution_condition_type;
            l_strExecCondType = this.getExecCondTypePR(l_ifoOrderExecCondType);

            // Step 1.2.13 Call getExecTypeDivPR(l_orderUnit : OrderUnit)
            l_strExecType = l_adminOrderExecDataManager.getExecTypeDivPR(l_orderUnits[i]);

            // 1.2.14 get�v�w�l�p�L����ԋ敪(�����P�� : IfoOrderUnit)
            //�v�w�l�p�L����ԋ敪���擾����B
            //[����]
            //�����P�ʁF�@@�����P��
            String l_strWLimitEnableStatusDiv =
                WEB3IfoDataAdapter.getWLimitEnableStatusDiv(l_orderUnits[i]);

            //get���������敪(�����P�� : IfoOrderUnit)
            String l_strExpirationDateType =
                WEB3IfoDataAdapter.getExpirationDateType(l_orderUnits[i]);

            //get�[��O�J�z�Ώۃt���O�iPR�w�j(�����P�� : IfoOrderUnit)
            boolean l_blnEveningSessionCarryOverFlag =
                WEB3IfoDataAdapter.getEveningSessionCarryOverFlagPr(l_orderUnits[i]);

            // Step 1.2.15 Create WEB3AdminORFutOpOrderExecutionRefUnit instance
            l_adminORFutOpOrderExecRefUnit = new WEB3AdminORFutOpOrderExecutionRefUnit();

            // Step 1.2.16 Set properties to WEB3AdminORFutOpOrderExecutionRefUnit instance
            l_adminORFutOpOrderExecRefUnit.id =
                WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitParams.order_id);
            l_adminORFutOpOrderExecRefUnit.branchCode = l_branch.getBranchCode();
            l_adminORFutOpOrderExecRefUnit.accountCode = l_gentradeMainAccount.getDisplayAccountCode();
            //�ڋq���@@�@@�@@�@@�@@�@@�@@���@@getMainAccount()�̖߂�l.get�ڋq�\���� ()
            l_adminORFutOpOrderExecRefUnit.accountName = l_gentradeMainAccount.getDisplayAccountName();
            l_adminORFutOpOrderExecRefUnit.sonarTraderCode = l_ifoOrderUnitParams.sonar_trader_code;
            l_adminORFutOpOrderExecRefUnit.productDiv = l_strProductDiv;
            l_adminORFutOpOrderExecRefUnit.tradingType = l_strTradingType;
            l_adminORFutOpOrderExecRefUnit.orderQuantity =
                WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitParams.quantity);

            /* If return of isMarketOrder() = true then set orderPriceDiv = UNLIMIT_PRICE.
             * Otherwise set orderPriceDiv = LIMIT_PRICE
             */
            if (l_blnMarketOrder)
            {
                l_adminORFutOpOrderExecRefUnit.orderPriceDiv =
                    WEB3OrderPriceDivDef.MARKET_PRICE;
            }
            else
            {
                l_adminORFutOpOrderExecRefUnit.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
            }

            // If orderPriceDiv = LIMIT_PRICE then only set orderPrice
            if (WEB3OrderPriceDivDef.LIMIT_PRICE
                .equals(l_adminORFutOpOrderExecRefUnit.orderPriceDiv))
            {
                l_adminORFutOpOrderExecRefUnit.orderPrice =
                    WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitParams.limit_price.doubleValue());
            }

            l_adminORFutOpOrderExecRefUnit.estimatedPrice =
                WEB3StringTypeUtility.formatNumber(
                    l_ifoOrderUnitParams.estimated_price.doubleValue());
            l_adminORFutOpOrderExecRefUnit.orderChannel = l_ifoOrderUnitParams.order_chanel;
            l_adminORFutOpOrderExecRefUnit.orderState = l_strOrderState;
            l_adminORFutOpOrderExecRefUnit.orderDate = l_ifoOrderUnitParams.received_date_time;
            l_adminORFutOpOrderExecRefUnit.orderBizDate =
                WEB3DateUtility.getDate(l_ifoOrderUnitParams.biz_date, DATE_FORMAT);
            l_adminORFutOpOrderExecRefUnit.execCondType = l_strExecCondType;

            // get���������敪(�j��"�o����܂Œ���"�̏ꍇ�̂݁A
            //�����P��.�����������t���Z�b�g�B�ȊO�Anull���Z�b�g�B
            if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_strExpirationDateType))
            {
                l_adminORFutOpOrderExecRefUnit.expirationDate =
                    l_ifoOrderUnitParams.expiration_date;
            }
            else
            {
                l_adminORFutOpOrderExecRefUnit.expirationDate = null;
            }

            String l_strOrgOrderCondType = l_ifoOrderUnitParams.getOrgOrderConditionType();

            //�����P��.���������� == "�t�w�l"�̏ꍇ
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrgOrderCondType))
            {
                //���������敪 = �����P��.����������
                l_adminORFutOpOrderExecRefUnit.orderCondType = l_strOrgOrderCondType;

                //���������P���敪 = �����P��.���t�w�l��l�^�C�v
                l_adminORFutOpOrderExecRefUnit.orderCondPriceDiv =
                    l_ifoOrderUnitParams.getOrgStopPriceType();

                //���������P�� = �����P��.���t�w�l��l
                l_adminORFutOpOrderExecRefUnit.orderCondPrice =
                    WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitParams.getOrgStopOrderPrice());

                //�����������Z�q = �����P��.�������������Z�q
                l_adminORFutOpOrderExecRefUnit.condOperator =
                    l_ifoOrderUnitParams.getOrgOrderCondOperator();
            }

            //�����P��.���������� == "W�w�l"�̏ꍇ
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrgOrderCondType))
            {
                //���������敪 = �����P��.����������
                l_adminORFutOpOrderExecRefUnit.orderCondType = l_strOrgOrderCondType;

                //���������P���敪 = �����P��.���t�w�l��l�^�C�v
                l_adminORFutOpOrderExecRefUnit.orderCondPriceDiv =
                    l_ifoOrderUnitParams.getOrgStopPriceType();

                //���������P�� = �����P��.���t�w�l��l
                l_adminORFutOpOrderExecRefUnit.orderCondPrice =
                    WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitParams.getOrgStopOrderPrice());

                //�����������Z�q = �����P��.�������������Z�q
                l_adminORFutOpOrderExecRefUnit.condOperator =
                    l_ifoOrderUnitParams.getOrgOrderCondOperator();

                //�����P��.���iW�w�l�j�����w�l == 0�̏ꍇ
                if (l_ifoOrderUnitParams.getOrgWLimitPrice() == 0)
                {
                    l_adminORFutOpOrderExecRefUnit.wlimitOrderCondPrice = null;
                    l_adminORFutOpOrderExecRefUnit.wLimitOrderPriceDiv =
                        WEB3OrderPriceDivDef.MARKET_PRICE;
                }
                else
                {
                    l_adminORFutOpOrderExecRefUnit.wlimitOrderCondPrice =
                        WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitParams.getOrgWLimitPrice());
                    l_adminORFutOpOrderExecRefUnit.wLimitOrderPriceDiv =
                        WEB3OrderPriceDivDef.LIMIT_PRICE;
                }

                    //�v�w�l�p���s���� = this.get���s�����iPR�w�j(�����P��.���iW�w�l�j���s����)
                l_adminORFutOpOrderExecRefUnit.wlimitExecCondType =
                    this.getExecCondTypePR(l_ifoOrderUnitParams.getOrgWLimitExecCondType());
            }
            else
            {
                //���������敪 = �����P��.��������
                String l_strOrderConditionType =
                    l_ifoOrderUnitParams.getOrderConditionType();
                l_adminORFutOpOrderExecRefUnit.orderCondType = l_strOrderConditionType;

                //�����P��.�������� == "�t�w�l"�̏ꍇ
                if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderConditionType))
                {
                    //���������P���敪 = �����P��.�t�w�l��l�^�C�v
                    l_adminORFutOpOrderExecRefUnit.orderCondPriceDiv =
                        l_ifoOrderUnitParams.getStopPriceType();

                    //���������P�� = �����P��.�t�w�l��l
                    l_adminORFutOpOrderExecRefUnit.orderCondPrice =
                        WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitParams.getStopOrderPrice());

                    //�����������Z�q = �����P��.�����������Z�q
                    l_adminORFutOpOrderExecRefUnit.condOperator =
                        l_ifoOrderUnitParams.getOrderCondOperator();
                }

                //�����P��.�������� == "W�w�l"�̏ꍇ
                else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
                {
                    //���������P���敪 = �����P��.�t�w�l��l�^�C�v
                    l_adminORFutOpOrderExecRefUnit.orderCondPriceDiv =
                        l_ifoOrderUnitParams.getStopPriceType();

                    //���������P�� = �����P��.�t�w�l��l
                    l_adminORFutOpOrderExecRefUnit.orderCondPrice =
                        WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitParams.getStopOrderPrice());

                    //�����������Z�q = �����P��.�����������Z�q
                    l_adminORFutOpOrderExecRefUnit.condOperator =
                        l_ifoOrderUnitParams.getOrderCondOperator();

                    //�����P��.�iW�w�l�j�����w�l == 0�̏ꍇ
                    if (l_ifoOrderUnitParams.getWLimitPrice() == 0)
                    {
                        l_adminORFutOpOrderExecRefUnit.wlimitOrderCondPrice = null;
                        l_adminORFutOpOrderExecRefUnit.wLimitOrderPriceDiv =
                            WEB3OrderPriceDivDef.MARKET_PRICE;
                    }
                    else
                    {
                        l_adminORFutOpOrderExecRefUnit.wlimitOrderCondPrice =
                            WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitParams.getWLimitPrice());
                        l_adminORFutOpOrderExecRefUnit.wLimitOrderPriceDiv =
                            WEB3OrderPriceDivDef.LIMIT_PRICE;
                    }

                    //�v�w�l�p���s���� = this.get���s�����iPR�w�j(�����P��.�iW�w�l�j���s����)
                    l_adminORFutOpOrderExecRefUnit.wlimitExecCondType =
                        this.getExecCondTypePR(l_ifoOrderUnitParams.getWLimitExecCondType());
                }
            }

            // Set execQuantity and  execPrice only if isUnexecuted() = false
            if (!l_orderUnits[i].isUnexecuted())
            {
                l_adminORFutOpOrderExecRefUnit.execQuantity =
                    WEB3StringTypeUtility.formatNumber(
                        l_ifoOrderUnitParams.executed_quantity.doubleValue());
                IfoProductRow l_ifoProductRow =
                    (IfoProductRow)l_product.getDataSourceObject();
                // ��������擾�ׁ̈A����J�����_�R���e�L�X�g.�����R�[�h���Z�b�g
                WEB3GentradeTradingTimeManagement.resetProductCode(l_ifoProductRow.getUnderlyingProductCode());
                
                IfoTradedProduct l_ifoTradedProduct = 
                    (IfoTradedProduct)l_orderUnits[i].getTradedProduct();
                IfoTradedProductRow l_ifoTradedProductRow =
                    (IfoTradedProductRow)l_ifoTradedProduct.getDataSourceObject();
                    
                BigDecimal l_bdUnitSize = new BigDecimal(l_ifoTradedProductRow.getUnitSize());
                l_bdExecQuantity = new BigDecimal(l_ifoOrderUnitParams.getExecutedQuantity());
                l_bdExecAmountPrice = new BigDecimal(l_ifoOrderUnitParams.getExecutedAmount());
                // ���v�����z���w���搔�F�@@
                l_bdExecAmountPrice = l_bdExecAmountPrice.divide(l_bdUnitSize, BigDecimal.ROUND_HALF_UP);
                // �@@����萔��
                l_bdExecAmountPrice =
                    l_bdExecAmountPrice.divide(l_bdExecQuantity, BigDecimal.ROUND_HALF_UP);
                l_adminORFutOpOrderExecRefUnit.execPrice =
                    WEB3StringTypeUtility.formatNumber(l_bdExecAmountPrice.doubleValue());
            }

            // If trader_id != null, then set traderCode
            if (l_ifoOrderUnitParams.trader_id != null)
            {
                //1.2.8 getTrader(arg0 : long)
                l_gentradeFinObjectManager =
                    (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                l_trader = l_gentradeFinObjectManager.getTrader(l_orderUnits[i].getTraderId());
                l_adminORFutOpOrderExecRefUnit.traderCode = l_trader.getTraderCode();
            }

            l_adminORFutOpOrderExecRefUnit.execType = l_strExecType;
            l_adminORFutOpOrderExecRefUnit.changeCancelDiv =
                l_ifoOrderUnitParams.modify_cancel_type;
            l_adminORFutOpOrderExecRefUnit.orderRootDiv = l_ifoOrderUnitParams.order_root_div;
            l_adminORFutOpOrderExecRefUnit.deliveryDate = l_ifoOrderUnitParams.delivery_date;
            l_adminORFutOpOrderExecRefUnit.eveningSessionCarryoverFlag = l_blnEveningSessionCarryOverFlag;
            l_adminORFutOpOrderExecRefUnit.sessionType = l_ifoOrderUnitParams.getSessionType();
            //�����R�[�h ���@@getProduct()�̖߂�l.�����R�[�h
            l_adminORFutOpOrderExecRefUnit.productCode =
                ((IfoProductRow)l_product.getDataSourceObject()).getProductCode();
            l_adminORFutOpOrderExecRefUnit.productName = 
                ((IfoProductRow)l_product.getDataSourceObject()).getStandardName();

			l_gentradeFinObjectManager =
				(WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
			Market l_market = l_gentradeFinObjectManager.getMarket(l_ifoOrderUnitParams.getMarketId());
			l_adminORFutOpOrderExecRefUnit.marketCode = l_market.getMarketCode();

            /* If return of getOpSubAccountType() = EQUITY_SUB_ACCOUNT
             * then set taxType = OPTIONS_LONG_ACCOUNT
             */
            if (SubAccountTypeEnum.EQUITY_SUB_ACCOUNT.equals(l_subAccountType))
            {
                l_adminORFutOpOrderExecRefUnit.taxType = WEB3AdminTaxTypeDef.OPTIONS_LONG_ACCOUNT;
            }

            /* If return of getOpSubAccountType() = EQUITY_OPTIONS_SUB_ACCOUNT
             * then set taxType = FUTURES_OPTIONS_ACCOUNT
             */
            if (SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccountType))
            {
                l_adminORFutOpOrderExecRefUnit.taxType =
                    WEB3AdminTaxTypeDef.FUTURES_OPTIONS_ACCOUNT;
            }

            //�v�w�l�p�L����ԋ敪 = get�v�w�l�p�L����ԋ敪()�̖߂�l
            l_adminORFutOpOrderExecRefUnit.wlimitEnableStatusDiv = l_strWLimitEnableStatusDiv;


            // Step 1.2.17 Add WEB3AdminORFutOpOrderExecutionRefUnit to ArrayList
            l_lisOrderExecRefUnit.add(l_adminORFutOpOrderExecRefUnit);

        }

        // Step 1.3 Create an array of WEB3AdminORFutOpOrderExecutionRefUnit
        l_adminORFutOpOrderExecRefUnits =
            new WEB3AdminORFutOpOrderExecutionRefUnit[l_lisOrderExecRefUnit.size()];
        l_adminORFutOpOrderExecRefUnits =
            (WEB3AdminORFutOpOrderExecutionRefUnit[])l_lisOrderExecRefUnit.toArray(
                l_adminORFutOpOrderExecRefUnits);

        log.exiting(STR_METHOD_NAME);

        // Step 1.4  return array of WEB3AdminORFutOpOrderExecutionRefUnit
        return l_adminORFutOpOrderExecRefUnits;

    }

    /**
     * �����̒����P�ʂ��PR�w�Ŏg�p���鏤�i�敪��ԋp����B<BR>
     * <BR>
     * �P�j�ȉ��̏����ɂ�蕪�򂵁A�Ή����鏤�i�敪��ԋp����B<BR>
     * <BR>
     * �@@[�p�����[�^.�����P��.�敨�^�I�v�V�����敪 == "�I�v�V����"�̏ꍇ]<BR>
     * �@@�@@"�I�v�V����"��ԋp����B<BR>
     * <BR>
     * �@@[�p�����[�^.�����P��.�敨�^�I�v�V�����敪 == "�敨"�̏ꍇ]<BR>
     * �@@�@@"�敨"��ԋp����B<BR>
     * <BR>
     * -----<English>---------<BR>
     * <BR>
     * getProductDiv<BR>
     * <BR>
     * Return productDiv from the argument, l_orderUnit to use in PR layer<BR>
     * <BR>
     * 1)Return the proper productDiv according to the following cases<BR>
     * <BR>
     * �@@If [l_orderUnit.future_option_div == Def.OPTION]<BR>
     * �@@�@@Return Def.OPTION<BR>
     * <BR>
     * �@@If [l_orderUnit.future_option_div == Def.FUTURES]<BR>
     * �@@�@@Return Def.FUTURES<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * <BR>
     * �敨OP�����P�ʃI�u�W�F�N�g<BR>
     * <BR>
     * l_orderUnit<BR>
     * <BR>
     * futures option order unit object<BR>
     * <BR
     * @@return java.lang.String
     * @@roseuid 41C6D74603C0
     */
    protected String getProductDiv(IfoOrderUnit l_orderUnit)
    {
        final String STR_METHOD_NAME = "getProductDiv(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        IfoOrderUnitParams l_ifoOrderUnitParams = null;
        String l_strProductDiv = null;

        l_ifoOrderUnitParams =
            new IfoOrderUnitParams((IfoOrderUnitRow) l_orderUnit.getDataSourceObject());

        /* Step 1 If future_option_div = OPTION, return OPTION as productDiv.
         * If future_option_div = FUTURES, return FUTURES as productDiv
         */
        if (WEB3FuturesOptionDivDef.OPTION.equals(l_ifoOrderUnitParams.future_option_div))
        {
            l_strProductDiv = WEB3AdminProductDivDef.OPTION;

        } else if (WEB3FuturesOptionDivDef.FUTURES.equals(l_ifoOrderUnitParams.future_option_div))
        {
            l_strProductDiv = WEB3AdminProductDivDef.FUTURE;

        }

        log.exiting(STR_METHOD_NAME);
        return l_strProductDiv;

    }

    /**
     * �iget�敨OP����ID�ꗗ�j<BR>
     * <BR>
     * �����̏����ɊY������敨OP����.����ID�̈ꗗ��ԋp����B<BR>
     * <BR>
     * �P�j�ȉ��̃p�����[�^���S��null�̏ꍇ�́Anull��ԋp����B<BR>
     * �@@�E����<BR>
     * �@@�E�s�g���i<BR>
     * �@@�E�I�v�V�������i�敪<BR>
     * <BR>
     * �Q�jDB����<BR>
     * �@@�ȉ��̏����Ő敨OP�����e�[�u������������B<BR>
     * <BR>
     * �@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h ����<BR>
     * �@@�����Y�����R�[�h(*1) = �p�����[�^.�w����� ����<BR>
     * �@@����(*1) = �p�����[�^.���� ����<BR>
     * �@@�s�g���i(*1) = �p�����[�^.�s�g���i ����<BR>
     * �@@�敨�I�v�V�������i(*1) = �p�����[�^.�I�v�V�������i�敪<BR>
     * <BR>
     * �@@(*1)�Ή�����p�����[�^��null�̏ꍇ�́A���������ɉ����Ȃ��B<BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�A�u�Y���f�[�^�Ȃ��v��<BR>
     * �@@��O���X���[����B<BR>
     * <BR>
     *   class : WEB3BusinessLayerException<BR>
     *   tag : BUSINESS_ERROR_00398<BR>
     * <BR>
     * �R�j�������ʂ̑S�v�f�������ID���擾���A<BR>
     * �@@����ID�̔z��𐶐����ĕԋp����B<BR>
     * <BR>
     * ------<English>----------<BR>
     * <BR>
     * getFutOpProductIdList<BR>
     * <BR>
     * Return the list of futOpProductId.productId corresponding to the conditions of
     * arguments<BR>
     * <BR>
     * 1)If the following paramenters are all null, return null.<BR>
     * �@@�El_targetProduct<BR>
     * �@@�El_deliveryMonth<BR>
     * �@@�El_strikePrice<BR>
     * �@@�El_opProductType<BR>
     * <BR>
     * 2)DB search<BR>
     * �@@Search ifo_product with the following conditions<BR>
     * <BR>
     * �@@institution_code = l_strInstitutionCode and<BR>
     * �@@underlying_product_code(*1) = l_targetProduct<BR>
     * �@@month_of_delivery(*1) = l_deliveryMonth and<BR>
     * �@@strike_price(*1) = l_strikePrice<BR>
     * �@@derivative_type(*1) = l_opProductType<BR>
     * <BR>
     * �@@(*1)It is not added as a search condition if the parameter is null<BR>
     * <BR>
     * �@@If it is unable to acquire the search result,<BR>
     *   Throw the exception "No corresponding data"<BR>
     * <BR>
     *   class : WEB3BusinessLayerException<BR>
     *   tag : BUSINESS_ERROR_00398<BR>
     * <BR>
     * 3)Acquire productId from all elements of search results,<BR>
     * �@@create an array of productId and return it.<BR>
     * <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h<BR>
     * <BR>
     * �،���ЃR�[�h<BR>
     * <BR>
     * l_strInstitutionCode<BR>
     * <BR>
     * @@param l_targetProduct - �w�����<BR>
     * <BR>
     * �w�����<BR>
     * <BR>
     * l_targetProduct<BR>
     * <BR>
     * @@param l_deliveryMonth - �i�����j<BR>
     * <BR>
     * ����<BR>
     * <BR>
     * l_deliveryMonth<BR>
     * <BR>
     * @@param l_strikePrice - �s�g���i<BR>
     * <BR>
     * �s�g���i<BR>
     * <BR>
     * l_strikePrice<BR>
     * <BR>
     * @@param l_opProductType - �i�I�v�V�������i�敪�j<BR>
     * <BR>
     * �I�v�V�������i�敪<BR>
     * <BR>
     * l_opProductType<BR>
     * <BR>
     * @@return String[]
     * @@exception WEB3BusinessLayerException WEB3BusinessLayerException
     * @@exception DataFindException DataFindException
     * @@exception DataNetworkException DataNetworkException
     * @@exception DataQueryException DataQueryException
     * @@roseuid 41D243BB011B
     */
    protected String[] getFutOpProductIdList(
        String l_strInstitutionCode,
        String l_targetProduct,
        String l_deliveryMonth,
        String l_strikePrice,
        String l_opProductType)
        throws
            WEB3BusinessLayerException,
            DataFindException,
            DataNetworkException,
            DataQueryException
    {
        final String STR_METHOD_NAME =
            "getFutOpProductIdList(String, String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        String[] l_strProductIds = null;
        List l_lisSearchResult = null;
        QueryProcessor l_queryProcessor = null;
        IfoProductRow l_ifoProductRow = null;
        int l_intSearchResultCnt = 0;
        StringBuffer l_sbWhere = null;
        List l_lisObjWhere = null;

        /* Step 1 If l_deliveryMonth, l_strikePrice and l_opProductType are null
         *  then return null. Otherwise create a where condition and perform database search
         */
        if (l_deliveryMonth == null
            && l_strikePrice == null
            && l_opProductType == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;

        // Step 2 Search ifo_product based on condition
        } else
        {
            // Build Where clause.
            l_sbWhere = new StringBuffer(" institution_code = ?");

            l_lisObjWhere = new ArrayList();
            l_lisObjWhere.add(l_strInstitutionCode);

            // If l_targetProduct is not null then add to where condition
            if (l_targetProduct != null)
            {
                l_sbWhere.append(" and underlying_product_code = ?");
                l_lisObjWhere.add(l_targetProduct);
            }

            // If l_deliveryMonth is not null then add to where condition
            if (l_deliveryMonth != null)
            {
                l_sbWhere.append(" and month_of_delivery = ?");
                l_lisObjWhere.add(l_deliveryMonth);
            }

            // If l_strikePrice is not null then add to where condition
            if (l_strikePrice != null)
            {
                l_sbWhere.append(" and strike_price = ?");
                l_lisObjWhere.add(l_strikePrice);
            }

            // If l_opProductType is not null then add to where condition
            if (l_opProductType != null)
            {
                l_sbWhere.append(" and derivative_type = ?");
                l_lisObjWhere.add(l_opProductType);
            }

            l_queryProcessor = Processors.getDefaultProcessor();

            l_lisSearchResult =
                l_queryProcessor.doFindAllQuery(
                    IfoProductRow.TYPE,
                    l_sbWhere.toString(),
                    null,
                    l_lisObjWhere.toArray());

            l_intSearchResultCnt = l_lisSearchResult.size();

            /*  If it is not possible to acquire search result then throw
             * WEB3BusinessLayerException. Otherwise return the search result
             */
            if (l_intSearchResultCnt == 0)
            {
                String l_strMsg = "No corresponding data";
                log.error(l_strMsg);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_strMsg);

            } else
            {
                l_strProductIds = new String[l_intSearchResultCnt];

                for (int i = 0; i < l_intSearchResultCnt; i++)
                {
                    l_ifoProductRow = (IfoProductParams) l_lisSearchResult.get(i);
                    l_strProductIds[i] =
                        WEB3StringTypeUtility.formatNumber(l_ifoProductRow.getProductId());
                }

                log.exiting(STR_METHOD_NAME);

                // Step 3 Return array of productIds
                return l_strProductIds;
            }
        }
    }

    /**
     * �iget���s�����j<BR>
     * <BR>
     * �����̎��s�������AAP�w�Ŏg�p���鎷�s����<BR>
     * (IfoOrderExecutionConditionType)��ԋp����B<BR>
     * <BR>
     * �P�j�p�����[�^.���s�����ɂ�蕪�򂵁A�Ή�����l��ԋp����B<BR>
     * <BR>
     * �@@�p�����[�^.���s�������A<BR>
     * �@@["������"�̏ꍇ]<BR>
     * �@@�@@IfoOrderExecutionConditionType.�����Ȃ���ԋp����B<BR>
     * �@@["��t"�̏ꍇ]<BR>
     * �@@�@@IfoOrderExecutionConditionType.����ԋp����B<BR>
     * �@@["����"�̏ꍇ]<BR>
     * �@@�@@IfoOrderExecutionConditionType.������ԋp����B<BR>
     * �@@["�s�o���������s"�̏ꍇ]<BR>
     * �@@�@@IfoOrderExecutionConditionType.�s�o���������s��ԋp����B<BR>
     * <BR>
     * ------<English>------------<BR>
     * <BR>
     * getExecCondType<BR>
     * <BR>
     * Return execCondType used in AP layer(IfoOrderExecutionConditionType) from the
     * argument, l_execCondType<BR>
     * <BR>
     * 1)Go to one of the following processes according to l_execCondType, and return
     * the value<BR>
     * <BR>
     * �@@If [l_execCondTyp is Def.NO_CONDITION]<BR>
     * �@@�@@Return IfoOrderExecutionConditionType.Def.NONE<BR>
     * �@@If [l_execCondTyp is Def.AT_MARKET_OPEN]<BR>
     * �@@�@@Return IfoOrderExecutionConditionType.Def.AT_MARKET_OPEN<BR>
     * �@@If [l_execCondTyp is Def.AT_MARKET_CLOSE]<BR>
     * �@@�@@Return IfoOrderExecutionConditionType.Def.AT_MARKET_CLOSE<BR>
     * �@@If [l_execCondTyp is Def.AT_MARKET_CLOSE_NOT_EXECUTED]<BR>
     * �@@�@@Return IfoOrderExecutionConditionType.Def.AT_MARKET_CLOSE_NOT_EXECUTED<BR>
     * <BR>
     * @@param l_execCondType - ���s����<BR>
     * <BR>
     * ���s����<BR>
     * <BR>
     * l_execCondType<BR>
     * <BR>
     * @@return IfoOrderExecutionConditionType
     * @@roseuid 41D258A100AE
     */
    protected IfoOrderExecutionConditionType getExecCondType(String l_execCondType)
    {
        final String STR_METHOD_NAME = "getExecCondType(String)";
        log.entering(STR_METHOD_NAME);
        IfoOrderExecutionConditionType l_ifoOrderExecutionConditionType = null;

        // Step 1 Return IfoOrderExecutionConditionType based on the argument l_execCondType
        if (WEB3ExecutionConditionDef.NO_CONDITION.equals(l_execCondType))
        {
            l_ifoOrderExecutionConditionType = IfoOrderExecutionConditionType.NONE;

        } else if (WEB3ExecutionConditionDef.AT_MARKET_OPEN.equals(l_execCondType))
        {
            l_ifoOrderExecutionConditionType = IfoOrderExecutionConditionType.AT_MARKET_OPEN;

        } else if (WEB3ExecutionConditionDef.AT_MARKET_CLOSE.equals(l_execCondType))
        {
            l_ifoOrderExecutionConditionType = IfoOrderExecutionConditionType.AT_MARKET_CLOSE;

        } else
        {
            l_ifoOrderExecutionConditionType =
                IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED;
        }

        log.exiting(STR_METHOD_NAME);
        return l_ifoOrderExecutionConditionType;

    }

    /**
     * �iget���s�����iPR�w�j�j<BR>
     * <BR>
     * �����̎��s�������APR�w�Ŏg�p���鎷�s������ԋp����B<BR>
     * <BR>
     * �P�j�p�����[�^.���s�����ɂ�蕪�򂵁A�Ή�����l��ԋp����B<BR>
     * <BR>
     * �@@�p�����[�^.���s�������A<BR>
     * �@@[IfoOrderExecutionConditionType.�����Ȃ��̏ꍇ]<BR>
     * �@@�@@"������"��ԋp����B<BR>
     * �@@[IfoOrderExecutionConditionType.���̏ꍇ]<BR>
     * �@@�@@"��t"��ԋp����B<BR>
     * �@@[IfoOrderExecutionConditionType.�����̏ꍇ]<BR>
     * �@@�@@"����"��ԋp����B<BR>
     * �@@[IfoOrderExecutionConditionType.�s�o���������s�̏ꍇ]<BR>
     * �@@�@@"�s�o���������s"��ԋp����B<BR>
     * <BR>
     * ----<English>------<BR>
     * <BR>
     * getExecCondTypePR<BR>
     * <BR>
     * Return execCondType used in PR layer from the argument, l_execCondType<BR>
     * <BR>
     * 1)Go to one of the following processes according to l_execCondType, and return
     * the value<BR>
     * <BR>
     * �@@[l_execCondType is IfoOrderExecutionConditionType.Def.NONE]<BR>
     * �@@�@@Return Def.NO_CONDITION<BR>
     * �@@[l_execCondType is IfoOrderExecutionConditionType.Def.AT_MARKET_OPEN]<BR>
     * �@@�@@Return Def.AT_MARKET_OPEN<BR>
     * �@@[l_execCondType is IfoOrderExecutionConditionType.Def.AT_MARKET_CLOSE]<BR>
     * �@@�@@Return Def.AT_MARKET_CLOSE<BR>
     * �@@[l_execCondType
     * is IfoOrderExecutionConditionType.Def.AT_MARKET_CLOSE_NOT_EXECUTED]<BR>
     * �@@�@@Return Def.AT_MARKET_CLOSE_NOT_EXECUTED<BR>
     * <BR>
     * @@param l_execCondType - ���s����<BR>
     * (IfoOrderExecutionConditionType�ɂĒ�`)<BR>
     * <BR>
     * l_execCondType<BR>
     * (Defined at IfoOrderExecutionConditionType)<BR>
     * <BR>
     * @@return java.lang.String
     * @@roseuid 41D258A100BD
     */
    protected String getExecCondTypePR(IfoOrderExecutionConditionType l_execCondType)
    {
        final String STR_METHOD_NAME = "getExecCondTypePR(IfoOrderExecutionConditionType)";
        log.entering(STR_METHOD_NAME);
        String l_strExecCondTypePR = null;

        // Step 1 Return execCondTypePR based on the value of argumrnt l_execCondType
        if (IfoOrderExecutionConditionType.NONE.equals(l_execCondType))
        {
            l_strExecCondTypePR = WEB3ExecutionConditionDef.NO_CONDITION;

        } else if (IfoOrderExecutionConditionType.AT_MARKET_OPEN.equals(l_execCondType))
        {
            l_strExecCondTypePR = WEB3ExecutionConditionDef.AT_MARKET_OPEN;

        } else if (IfoOrderExecutionConditionType.AT_MARKET_CLOSE.equals(l_execCondType))
        {
            l_strExecCondTypePR = WEB3ExecutionConditionDef.AT_MARKET_CLOSE;

        } else if (
            IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.equals(l_execCondType))
        {
            l_strExecCondTypePR = WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED;

        }

        log.exiting(STR_METHOD_NAME);
        return l_strExecCondTypePR;

    }

}
@
