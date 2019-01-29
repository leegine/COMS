head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.22.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminOrderExecuteCountReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ғ�����茏���Ɖ�T�[�r�XImpl(WEB3AdminOrderExecuteCountReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/09/11 ����� (���u) �d�l�ύXNo.56�C��
                   2006/10/25 ���G�� (���u) ���f��079
                   2006/10/26 ���G�� (���u)�@@�c�a���C�A�E�g No.025
*/
package webbroker3.adminorderexecinquiry.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;

import webbroker3.adminorderexecinquiry.WEB3AdminProcessingObjectInfo;
import webbroker3.adminorderexecinquiry.data.OrderExecutedCountParams;
import webbroker3.adminorderexecinquiry.data.OrderExecutedCountRow;
import webbroker3.adminorderexecinquiry.define.WEB3AdminMonthlySumDivDef;
import webbroker3.adminorderexecinquiry.define.WEB3AdminOrderRootDspDivDef;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORExecutionNumberInputRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORExecutionNumberInputResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORExecutionNumberReferenceRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORExecutionNumberReferenceResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminOROrderDayNumberInfoUnit;
import webbroker3.adminorderexecinquiry.message.WEB3AdminOROrderRootNumberInfoUnit;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORProductMarketNumberInfoUnit;
import webbroker3.adminorderexecinquiry.service.delegate.WEB3AdminOrderExecuteCountReferenceService;
import webbroker3.bd.data.BondBranchConditionDao;
import webbroker3.bd.data.BondBranchConditionRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3EnforcementDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��Ғ�����茏���Ɖ�T�[�r�XImpl)<BR>
 * <BR>
 * �Ǘ��Ғ�����茏���Ɖ�T�[�r�X�����N���X<BR>
 * <BR>
 * WEB3AdminOrderExecuteCountReferenceServiceImpl<BR>
 * <BR>
 * @@author Rajesh Sharma
 * @@version 1.0
 */
public class WEB3AdminOrderExecuteCountReferenceServiceImpl
    extends WEB3ClientRequestService
    implements WEB3AdminOrderExecuteCountReferenceService
{
    /**
     * Log Variable
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminOrderExecuteCountReferenceServiceImpl.class);

    /**
     * @@roseuid 4212FCE20279
     */
    public WEB3AdminOrderExecuteCountReferenceServiceImpl()
    {
    }

    /**
     * ������茏���Ɖ�����s���B
     *
     * ���N�G�X�g�f�[�^�̌^�ɂ��A
     * �ȉ��̃��\�b�h���Ăѕ�����B
     *
     * ���Ǘ��ҁE������茏���Ɖ���̓��N�G�X�g�̏ꍇ
     * �@@this.get���͉��()���R�[������B
     *
     * ���Ǘ��ҁE������茏���Ɖ�N�G�X�g�̏ꍇ
     * �@@this.get�����Ɖ���()���R�[������B
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * Execute WEB3AdminOrderExecuteCountReferenceService process<BR>
     * <BR>
     * Call one of the following methods based on the type of the argument,
     * l_request.<<BR>
     * <BR>
     * ��If WEB3AdminORExecutionNumberInputRequest<BR>
     * �@@Call this.getInputScreen()<BR>
     * <BR>
     * ��If WEB3AdminORExecutionNumberReferenceRequest<BR>
     * �@@Call this.getReferenceScreen()<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 419ACFE603D9
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3GenResponse l_response = null;

        try
        {
            /*
             * Check for the condition input parameter if it is instance of InputRequest then call
             * getInputScreen() method, else if the instance of ReferenceRequest then call
             * getReferenceScreen() or else throw the exception.
             */
            if (l_request instanceof WEB3AdminORExecutionNumberInputRequest)
            {
                l_response =
                    this.getInputScreen((WEB3AdminORExecutionNumberInputRequest) l_request);
            } else if (l_request instanceof WEB3AdminORExecutionNumberReferenceRequest)
            {
                l_response =
                    this.getReferenceScreen((WEB3AdminORExecutionNumberReferenceRequest) l_request);
            } else
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "INPUT ���N�G�X�g NOT ������茏���Ɖ�N�G�X�g");
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
     * (get���͉��)<BR>
     * <BR>
     * ������茏���Ɖ���͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��Ғ�����茏���Ɖ�T�[�r�X)get���͉�ʁv<BR>
     * �Q��<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * getInputScreen<BR>
     * <BR>
     * Execute WEB3AdminOrderExecuteCountReferenceService input screen process<BR>
     * <BR>
     * Refer to the sequence diagram "(administrator: order execution count inquiry<BR>
     * service)getInputScreen"<BR>
     * <BR>
     * @@param l_request - �i���N�G�X�g�f�[�^�j<BR>
     * <BR>
     * �Ǘ��Ғ�����茏���Ɖ���̓��N�G�X�g�I�u�W�F�N�g<BR>
     * <BR>
     * ----<English>-------------<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminORExecutionNumberInputRequest object<BR>
     * <BR>
     * @@return WEB3AdminORExecutionNumberInputResponse
     * @@throws NotFoundException NotFoundException
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 419ACFE4005E
     */
    protected WEB3AdminORExecutionNumberInputResponse
        getInputScreen(WEB3AdminORExecutionNumberInputRequest l_request)
        throws NotFoundException, WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminORExecutionNumberInputRequest)";
        log.entering(STR_METHOD_NAME);

        final String UNIT_VALUE = "1";
        final String DATE_PATTERN = "yyyyMM";
        final int l_intMaxMonth = 12;
        WEB3Administrator l_administrator = null;
        Institution l_institution = null;
        WEB3AdminORExecutionNumberInputResponse l_response = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingSystem l_tradingSystem = l_finApp.getTradingSystem();
        Branch l_branch = null;
        BranchRow l_branchRow = null;
        BranchParams l_branchParams = null;
        ArrayList l_lisOrderBizDate = null;
        Date l_datBizDate = null;
        String[] l_strBizMonths = null;
        String l_strInstitutionCode = null;
        String l_strBranchCode = null;

        // Step 1.1 Acquire WEB3Administrator instance from loginInfo
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // Step 1.2 Check validateAuthority
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ORDER_COUNT_MANAGEMENT, false);

        // Step 1.3 Acquire institutionCode
        l_strInstitutionCode = l_administrator.getInstitutionCode();

        // Step 1.4 Acquire branchCode
        l_strBranchCode = l_administrator.getBranchCode();

        // Step 1.5 To get the branch Object
        WEB3GentradeAccountManager l_web3GentradeAccManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        l_institution = l_web3GentradeAccManager.getInstitution(l_strInstitutionCode);
        l_branch = l_web3GentradeAccManager.getBranch(l_institution, l_strBranchCode);
        l_branchRow = (BranchRow)l_branch.getDataSourceObject();
        l_branchParams = new BranchParams(l_branchRow);

        // Step 1.6 Acquire bizDate
        l_tradingSystem = l_finApp.getTradingSystem();
        l_datBizDate = l_tradingSystem.getBizDate();

        // Step 1.7 Acquire ArrayList to store the 12 months dates
        l_lisOrderBizDate = new ArrayList();

        // Step 1.8 Add year and month to ArrayList
        l_lisOrderBizDate.add(WEB3DateUtility.formatDate(l_datBizDate, DATE_PATTERN));
        Calendar l_currentDate = Calendar.getInstance();

        // Step 1.9 Loop process untill years and months of the past 12 months are set
        for (int i = 1; i <= l_intMaxMonth; i++)
        {
            // Step 1.9.1 The month of bizDate is subtracted by one
            l_currentDate.setTime(l_datBizDate);
            l_currentDate.add(Calendar.MONTH, (-i));

            // Step 1.9.2 Add the subtracted year and month to ArrayList
            l_lisOrderBizDate.add(
                WEB3DateUtility.formatDate(l_currentDate.getTime(), DATE_PATTERN));
        }

        // Step 1.10 Create an array of l_strBizMonth
        l_strBizMonths = new String[l_lisOrderBizDate.size()];
        l_strBizMonths = (String[])l_lisOrderBizDate.toArray(l_strBizMonths);

        // Step 1.11 �����X�ʏ����e�[�u���̌���
        BondBranchConditionRow l_bondBranchConditionRow = null;
        try
        {
            l_bondBranchConditionRow = BondBranchConditionDao.findRowByPk(
                l_branch.getBranchId());
        }
        catch (DataFindException l_dfex)
        {
        	String l_strMsg = "Data not found branchId = "
        			+ l_branch.getBranchId();
        	log.info(l_strMsg);
        }
        catch (DataQueryException l_dqex)
        {
            String l_strMsg = "Error while aquiring the Data ";
            log.error(l_strMsg, l_dqex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_dnex)
        {
            String l_strMsg = "Error while aquiring the Data ";
            log.error(l_strMsg, l_dnex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }

        // Step 1.12 Create response data
        l_response = (WEB3AdminORExecutionNumberInputResponse)l_request.createResponse();

        // Step 1.13 Property Set
        l_response.searchYmList = l_strBizMonths;

        /* If margin_sys_div or margin_gen_div is equal to def.ENFORCEMENT then margin flag is true
         * else false
         */
        if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchParams.margin_sys_div)
            || WEB3EnforcementDef.ENFORCEMENT.equals(l_branchParams.margin_gen_div))
        {
            l_response.marginFlag = true;
        }
        else
        {
            l_response.marginFlag = false;
        }
        if (UNIT_VALUE.equals(l_branchParams.mstk_div))
        {
            l_response.miniFlag = true;
        }
        if (UNIT_VALUE.equals(l_branchParams.fstk_div))
        {
            l_response.foreignFlag = true;
        }
        if (UNIT_VALUE.equals(l_branchParams.option_div))
        {
            l_response.optionFlag = true;
        }
        if (UNIT_VALUE.equals(l_branchParams.future_div))
        {
            l_response.futureFlag = true;
        }
        if (UNIT_VALUE.equals(l_branchParams.mf_div))
        {
            l_response.mutualFlag = true;
        }
        if (UNIT_VALUE.equals(l_branchParams.ruito_div))
        {
            l_response.ruitoFlag = true;
        }
        if (l_bondBranchConditionRow != null)
        {
            String l_strEnforceDiv = l_bondBranchConditionRow.getEnforceDiv();
            if (WEB3EnforcementDef.ENFORCEMENT.equals(l_strEnforceDiv))
            {
                l_response.bondFlag = true;
            }
            else
            {
                l_response.bondFlag = false;
            }
        }
        else
        {
            l_response.bondFlag = false;
        }

        // Step 1.14
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�Ɖ���)<BR>
     * <BR>
     * ������茏���Ɖ�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��Ғ�����茏���Ɖ�T�[�r�X)get�����Ɖ��ʁv<BR>
     * �Q��<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * getReferenceScreen<BR>
     * <BR>
     * Execute WEB3AdminOrderExecuteCountReferenceService process<BR>
     * <BR>
     * Refer to the sequence diagram "(administrator: order execution count inquiry
     * service)getCountReferenceScreen"<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * <BR>
     * �Ǘ��Ғ�����茏���Ɖ�N�G�X�g�I�u�W�F�N�g<BR>
     * <BR>
     * ---------<English>----------<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminORExecutionNumberReferenceRequest object<BR>
     * <BR>
     * @@return WEB3AdminORExecutionNumberReferenceResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws DataNetworkException DataNetworkException
     * @@throws DataQueryException DataQueryException
     * @@roseuid 419ACFE4035C
     */
    protected WEB3AdminORExecutionNumberReferenceResponse
        getReferenceScreen(WEB3AdminORExecutionNumberReferenceRequest l_request)
        throws WEB3BaseException, DataNetworkException, DataQueryException
    {
        final String STR_METHOD_NAME =
            "getReferenceScreen(WEB3AdminORExecutionNumberReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminORExecutionNumberReferenceResponse l_response = null;

        // Step 1.1 Check l_request
        l_request.validate();

        // Step 1.2 Acquire WEB3Administrator instance from loginInfo
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //Step 1.3 Check validateAuthority
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ORDER_COUNT_MANAGEMENT, false);

        // Step 1.4 Acquire institutionCode
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //Step 1.5 Create special l_strQueryCond
        String l_strCreateQueryString =
            createQueryString(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.summryDiv,
                null,
                l_request.dailySumYm,
                l_request.monthlySumDiv,
                l_request.sumProductTypeList);

        // Step 1.6 Create special l_strQueryCond
        String[] l_strCreateQueryDataContainer =
            createQueryDataContainer(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.summryDiv,
                null,
                l_request.dailySumYm,
                l_request.monthlySumDiv,
                l_request.sumProductTypeList);

        // Step 1.7 Create sortCond
        String l_strCreateSortCond = createSortCond();

        // Step 1.8 Acquire orderCount data
        List l_lisOrderCount =
            getOrderCountList(
                l_strCreateQueryString,
                l_strCreateQueryDataContainer,
                l_strCreateSortCond);

        // If null is returned from getOrderCountList then execption is thorwn
        if (l_lisOrderCount == null)
        {
            String l_strMsg = "No corresponding data";
            log.error(l_strMsg);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //Step 1.9 Create processingObjectInfo
        OrderExecutedCountParams[] l_orderExecutedCountParams = new OrderExecutedCountParams[l_lisOrderCount.size()];
        l_orderExecutedCountParams =
            (OrderExecutedCountParams[]) l_lisOrderCount.toArray(l_orderExecutedCountParams);

        //String l_strMarketDspDiv = null;
        WEB3AdminProcessingObjectInfo l_processingObjectInfo =
            createProcessingObjectInfo(
                l_orderExecutedCountParams,
                l_request.orderRootDspDiv,
                l_request.marketDspDiv);

        // Step 1.10  Create ArrayList
        ArrayList l_lisOrderRootNumberInfoUnit = new ArrayList();

        /* Step 1.11 Loop process for as many times as the number of the elements of
         * processingObjectInfo.orderBizDateList
         */
        int l_intBizDateListSize = l_processingObjectInfo.orderBizDateList.length;
        for (int j = 0; j < l_intBizDateListSize; j++)
        {

            String l_strOrderBizDate = null;
            l_strOrderBizDate = l_processingObjectInfo.orderBizDateList[j];

            // Step 1.11.1 Create l_strQueryCond
            l_strCreateQueryString =
                createQueryString(
                    l_strInstitutionCode,
                    l_request.branchCode,
                    l_request.summryDiv,
                    l_strOrderBizDate,
                    null,
                    null,
                    l_request.sumProductTypeList);

            // Step 1.11.2 Create l_strQueryCondDataContainer
            l_strCreateQueryDataContainer =
                createQueryDataContainer(
                    l_strInstitutionCode,
                    l_request.branchCode,
                    l_request.summryDiv,
                    l_strOrderBizDate,
                    null,
                    null,
                    l_request.sumProductTypeList);

            // Step 1.11.3 Create Sort Key condition
            l_strCreateSortCond = createSortCond();

            // Step 1.11.4 Acquire orderCount data
            List l_lisGetOrderCount =
                getOrderCountList(
                    l_strCreateQueryString,
                    l_strCreateQueryDataContainer,
                    l_strCreateSortCond);
            OrderExecutedCountParams[] l_orderList =
                new OrderExecutedCountParams[l_lisGetOrderCount.size()];
            l_orderList = (OrderExecutedCountParams[]) l_lisGetOrderCount.toArray(l_orderList);

            // Step 1.11.5 If orderRootDspDiv == Def.DETAIL
            WEB3AdminOROrderRootNumberInfoUnit[] l_adminOROrderRootNumberInfoUnits = null;
            if (WEB3AdminOrderRootDspDivDef.DETAIL.equals(l_request.orderRootDspDiv))
            {
                // Step 1.11.5.1 Create orderRootCountInfoList
                l_adminOROrderRootNumberInfoUnits =
                    createOrderRootCountInfoList(
                        l_orderList,
                        l_processingObjectInfo,
                        l_request.sumProductTypeList);
            }

            // Step 1.11.6 Acquire totalCount
            String[] l_strMarketCodes = l_processingObjectInfo.marketCodeList;
            List l_lisProductMarketTotalCountInfo =
                createProductMarketTotalCountInfoList(
                    l_orderList,
                    l_request.sumProductTypeList,
                    l_strMarketCodes);

            WEB3AdminORProductMarketNumberInfoUnit[] l_adminORProductMarketNumberInfoUnits =
                new WEB3AdminORProductMarketNumberInfoUnit[l_lisProductMarketTotalCountInfo.size()];

            l_adminORProductMarketNumberInfoUnits =
                (WEB3AdminORProductMarketNumberInfoUnit[]) l_lisProductMarketTotalCountInfo.toArray
                    (l_adminORProductMarketNumberInfoUnits);

            // Step 1.11.7 Create WEB3AdminOROrderDayNumberInfoUnit
            WEB3AdminOROrderDayNumberInfoUnit l_adminOROrderDayNumberInfoUnit = new WEB3AdminOROrderDayNumberInfoUnit();

            // Step 1.11.8 Add the obtained values above to the created object
            l_adminOROrderDayNumberInfoUnit.orderBizDate = l_strOrderBizDate;
            l_adminOROrderDayNumberInfoUnit.orderRootNumberInfoList =
                l_adminOROrderRootNumberInfoUnits;
            l_adminOROrderDayNumberInfoUnit.productMarketSumNumberInfoList =
                l_adminORProductMarketNumberInfoUnits;

            // Step 1.11.9 Add the OrderDayNumberInfoUnit to the list
            l_lisOrderRootNumberInfoUnit.add(l_adminOROrderDayNumberInfoUnit);
        }

        // Step 1.12 Create a array to store the list values
        WEB3AdminOROrderDayNumberInfoUnit[] l_adminOROrderDayNumberInfoUnits =
            new WEB3AdminOROrderDayNumberInfoUnit[l_lisOrderRootNumberInfoUnit.size()];
        l_adminOROrderDayNumberInfoUnits =
            (WEB3AdminOROrderDayNumberInfoUnit[]) l_lisOrderRootNumberInfoUnit.toArray(
                l_adminOROrderDayNumberInfoUnits);

        // Step 1.13 Create the response object
        l_response = (WEB3AdminORExecutionNumberReferenceResponse) l_request.createResponse();

        // Step 1.14 Set the obtained orderDayNumberInfoUnit array to the response object
        l_response.orderBizDateCountInfoList = l_adminOROrderDayNumberInfoUnits;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create��������������)<BR>
     * <BR>
     * ����������������쐬����B<BR>
     * <BR>
     * �P�j�ȉ��̏�����\������������������쐬����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h�@@����<BR>
     * �@@�@@���R�[�h�敪 = �p�����[�^.�W�v�敪<BR>
     * <BR>
     * �@@�������������� = " institution_code = ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and record_div = ? "<BR>
     * <BR>
     * �Q�j���X��������������������ɒǉ�����B<BR>
     * �@@�p�����[�^.���X�R�[�h�̗v�f����"?"��ǉ�����B <BR>
     * <BR>
     * �@@�������������� += " and branch_id in (?, ?,,,) " <BR>
     * <BR>
     * �R�j����������<BR>
     * �@@�R�|�P�j�p�����[�^.���ʏW�v�Ώ۔N�� != null�̏ꍇ�A<BR>
     * �@@�@@�ȉ��̏�������������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and biz_date like ? "<BR>
     * <BR>
     * �@@�R�|�Q�j�p�����[�^.���ʏW�v�Ώۋ敪 != null�̏ꍇ�A<BR>
     * �@@�@@�ȉ��̏�������������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�������������� += "and biz_date >= ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and biz_date < ? "<BR>
     * <BR>
     * �@@�R�|�R�j�p�����[�^.������ != null�̏ꍇ�A<BR>
     * �@@�@@�ȉ��̏�������������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�������������� += "and biz_date = ? "<BR>
     * <BR>
     * �S�j�p�����[�^.���i�敪�ꗗ != null�̏ꍇ�A<BR>
     * �@@�ȉ��̂̏�������������������ɒǉ�����B<BR>
     * �@@�����i�敪�ꗗ�̗v�f����"?"��ǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and product_div in (?, ?,,,)"<BR>
     * <BR>
     * �T�j�쐬�������������������ԋp����B<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * createQueryString<BR>
     * <BR>
     * Create l_strQueryCond<BR>
     * <BR>
     * 1)Create l_strQueryCond to show the following conditions<BR>
     * <BR>
     * �@@[Condition]<BR>
     * �@@�@@institution_code = l_strInstitutionCode and<BR>
     * �@@�@@record_div = l_summryDiv<BR>
     * <BR>
     * �@@l_strQueryCond = " institution_code = ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and record_div = ? "<BR>
     * <BR>
     * 2)Add branch conditions to l_strQueryCond<BR>
     * �@@Add "?" as many as elements of l_branchCode<BR>
     * <BR>
     * �@@l_strQueryCond += " and branch_id in (?, ?,,,) " <BR>
     * <BR>
     * 3)Order date conditions<BR>
     * �@@3-1)If l_dailySumYm != null<BR>
     * �@@�@@Add the following conditions to l_strQueryCond<BR>
     * <BR>
     * �@@l_strQueryCond += "and biz_date like ? "<BR>
     * <BR>
     * �@@3-2)If l_monthlySumDiv != null<BR>
     * �@@�@@Add the following conditions to l_strQueryCond<BR>
     * <BR>
     * �@@�@@l_strQueryCond += "and biz_date >= ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and biz_date < ? "<BR>
     * <BR> 3-3)If l_orderBizDate != null<BR>
     * �@@�@@Add the following conditions to l_strQueryCond<BR>
     * <BR>
     * �@@�@@l_strQueryCond += "and biz_date = ? "<BR>
     * <BR>
     * 4)If l_productTypeList != null<BR>
     * �@@Add the following conditions to l_strQueryCond<BR>
     * �@@��Add "?" as many as elements of l_productTypeList<BR>
     * <BR>
     * �@@l_strQueryCond += "and product_div in (?, ?,,,)"<BR>
     * <BR>
     * 5)Return the created l_strQueryCond<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * <BR>
     * �،���ЃR�[�h<BR>
     * <BR>
     * l_strInstitutionCode<BR>
     * <BR>
     * @@param l_branchCode - (���X�R�[�h)<BR>
     * <BR>
     * ���X�R�[�h�̔z��<BR>
     * <BR>
     * l_branchCode<BR>
     * <BR>
     * @@param l_summryDiv - (�W�v�敪)<BR>
     * <BR>
     * �W�v�敪<BR>
     * <BR>
     * 0�F�@@����<BR>
     * 1�F�@@����<BR>
     * <BR>
     * ---<English>-------<BR>
     * <BR>
     * l_summryDiv<BR>
     * <BR>
     * 0: Def.����<BR>
     * 1: Def.����<BR>
     * <BR>
     * @@param l_orderBizDate - (������)<BR>
     * <BR>
     * ������<BR>
     * <BR>
     * l_orderBizDate<BR>
     * <BR>
     * @@param l_dailySumYm - l_dailySumYm
     * (���ʏW�v�Ώ۔N��)<BR>
     * <BR>
     * ���ʏW�v�Ώ۔N��<BR>
     * <BR>
     * l_dailySumYm<BR>
     * <BR>
     * @@param l_monthlySumDiv - (���ʏW�v�Ώۋ敪)<BR>
     * <BR>
     * ���ʏW�v�Ώۋ敪<BR>
     * <BR>
     * 0�F�@@�ߋ�3����<BR>
     * 1�F�@@�ߋ�6����<BR>
     * 2�F�@@�ߋ�12����<BR>
     * <BR>
     * ---<English>------<BR>
     * <BR>
     * l_monthlySumDiv<BR>
     * <BR>
     * 0�F�@@�ߋ�3����<BR>
     * 1�F�@@�ߋ�6����<BR>
     * 2�F�@@�ߋ�12����<BR>
     * <BR>
     * @@param l_productTypeList - (���i�敪�ꗗ)<BR>
     * <BR>
     * ���i�敪�ꗗ<BR>
     * ���C�ӂ̈ȉ��̒l�̔z��<BR>
     * <BR>
     * 0�F�@@��������<BR>
     * 1�F�@@�M�p���<BR>
     * 2�F�@@�����~�j����<BR>
     * 3�F�@@�I�v�V����<BR>
     * 4�F�@@�敨<BR>
     * 5�F�@@���M<BR>
     * 6�F�@@����F<BR>
     * 7�F�@@MMF<BR>
     * 8�F�@@�O������<BR>
     * 9�F�@@��<BR>
     * <BR>
     * ---<English>------<BR>
     * <BR>
     * l_productTypeList<BR>
     * ��An array of the following arbitrary values<BR>
     * <BR>
     * 0�F�@@��������<BR>
     * 1�F�@@�M�p���<BR>
     * 2�F�@@�����~�j����<BR>
     * 3�F�@@�I�v�V����<BR>
     * 4�F�@@�敨<BR>
     * 5�F�@@���M<BR>
     * 6�F�@@����F<BR>
     * 7�F�@@MMF<BR>
     * 8�F�@@�O������<BR>
     * 9�F�@@��<BR>
     * <BR>
     * @@return java.lang.String
     * @@roseuid 419B2E38034C
     */
    protected String createQueryString(
        String l_strInstitutionCode,
        String[] l_branchCode,
        String l_summryDiv,
        String l_orderBizDate,
        String l_dailySumYm,
        String l_monthlySumDiv,
        String[] l_productTypeList)
    {
        final String STR_METHOD_NAME =
            "createQueryString(String, String[], String, String, String, String[])";
        log.entering(STR_METHOD_NAME);

        int l_intArraySize = 0;
        int l_intBuffLength = 0;
        StringBuffer l_sbBuffer = new StringBuffer();

        // Step 1.1 Create Query with following conditions
        l_sbBuffer.append("institution_code = ? and record_div = ?");

        // Step 1.2 Add branch conditions
        l_intArraySize = l_branchCode.length;
        l_sbBuffer.append(" and branch_code in (");
        for (int i = 0; i < l_intArraySize; i++)
        {
            l_sbBuffer.append("?, ");
        }
        l_intBuffLength = l_sbBuffer.length();

        //To remove the last two charachters ", " which is added at last in the for loop
        l_sbBuffer.delete(l_intBuffLength - 2, l_intBuffLength);
        l_sbBuffer.append(")");

        // Step 1.3 orderDate condition
        if (l_dailySumYm != null)
        {
            // Step 1.3.1 Add biz_date like conditions
            l_sbBuffer.append(" and biz_date like ?");
        }

        // Step 1.3.2 Add and biz_date >= ? and biz_date < conditions
        if (l_monthlySumDiv != null)
        {
            l_sbBuffer.append(" and biz_date >= ? and biz_date < ?");
        }

        // Step 1.3.3 Add biz_date conditions
        if (l_orderBizDate != null)
        {
            l_sbBuffer.append(" and biz_date = ?");
        }

        // Step 1.4 Checking l_productTypeList != null
        l_intArraySize = l_productTypeList.length;
        l_sbBuffer.append(" and product_div in (");
        for (int i = 0; i < l_intArraySize; i++)
        {
            l_sbBuffer.append("?, ");
        }
        l_intBuffLength = l_sbBuffer.length();
        l_sbBuffer.delete(l_intBuffLength - 2, l_intBuffLength);
        l_sbBuffer.append(")");

        // Step 1.5 Returning the query condition
        log.exiting(STR_METHOD_NAME);
        return l_sbBuffer.toString();
    }

    /**
     * (create���������f�[�^�R���e�i)<BR>
     * <BR>
     * ���������f�[�^�R���e�i���쐬����B<BR>
     * <BR>
     * �P�jArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�ȉ��̒l���ォ�珇�ɐ�������ArrayList��<BR>
     * �@@�@@�Z�b�g����B<BR>
     * �@@�@@�E�p�����[�^.�،���ЃR�[�h<BR>
     * �@@�@@�E�p�����[�^.�W�v�敪<BR>
     * <BR>
     * �R�j�p�����[�^.���X�R�[�h�̑S�v�f��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �S�j����������<BR>
     * �@@�S�|�P�j�p�����[�^.���ʏW�v�Ώ۔N�� != null�̏ꍇ�A<BR>
     * �@@�@@�p�����[�^.���ʏW�v�Ώ۔N�� + "%"��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@�S�|�Q�j�p�����[�^.���ʏW�v�Ώۋ敪 != null�̏ꍇ�A<BR>
     * �@@�@@�p�����[�^.���ʏW�v�Ώۋ敪�ɂ��A�Z�b�g����l�𕪊򂷂�B<BR>
     * <BR>
     * �@@�@@�@@�����̃Z�b�g<BR>
     * �@@�@@�@@["0�F�ߋ�3����"�̏ꍇ]<BR>
     * �@@�@@�@@�@@�Ɩ����t(*1)��3�����O�̔N��(YYYYMM)���Z�b�g�B<BR>
     * <BR>
     * �@@�@@�@@["1�F�ߋ�6����"�̏ꍇ]<BR>
     * �@@�@@�@@�@@�Ɩ����t(*1)��6�����O�̔N��(YYYYMM)���Z�b�g�B<BR>
     * <BR>
     * �@@�@@�@@["2�F�ߋ�12����"�̏ꍇ]<BR>
     * �@@�@@�@@�@@�Ɩ����t(*1)��12�����O�̔N��(YYYYMM)���Z�b�g�B<BR>
     * <BR>
     * �@@�@@�A����̃Z�b�g<BR>
     * �@@�@@�@@�E�Ɩ����t(*1)�̔N��(YYYYMM)���Z�b�g�B<BR>
     * <BR>
     * �@@4-3)�p�����[�^.������ != null�̏ꍇ�A<BR>
     * �@@�@@�p�����[�^.��������ArrayList�ɒǉ�����B<BR>
     * <BR>
     * 5)�p�����[�^.���i�敪�ꗗ != null�̏ꍇ�A<BR>
     * �@@�p�����[�^.���i�敪�ꗗ�̗v�f�S�Ă�ArrayList�ɒǉ�����B<BR>
     * <BR>
     * 6)�쐬����ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * <BR>
     * (*1)�Ɩ����t<BR>
     * �@@TradingSystem.getBizDate()�ɂĎ擾����B<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * createQueryDataContainer<BR>
     * <BR>
     * create queryDataContainer<BR>
     * <BR>
     * 1)Create ArrayList<BR>
     * <BR>
     * 2)Set the following values sequentially into the created ArrayList<BR>
     * �@@�@@�El_strInstitutionCode<BR>
     * �@@�@@�El_summryDiv<BR>
     * <BR>
     * 3)Add all elements of l_branchCode to ArrayList<BR>
     * <BR>
     * 4)order date conditions<BR>
     * �@@4-1)If l_dailySumYm != null<BR>
     * �@@�@@Add l_dailySumYm + "%" to ArrayList<BR>
     * <BR>
     * �@@4-2)If l_monthlySumDiv != null<BR>
     * �@@�@@Set a value based on l_monthlySumDiv<BR>
     * <BR>
     * �@@�@@�@@Set minimum<BR>
     * �@@�@@�@@If [0: Def.PAST_THREE_MONTH]<BR>
     * �@@�@@�@@�@@Set year and month 3 months ago(YYYYMM) of bizDate(*1)<BR>
     * <BR>
     * �@@�@@�@@If [1: Def.PAST_SIX_MONTH]<BR>
     * �@@�@@�@@�@@Set year and month 6 months ago(YYYYMM) of bizDate(*1)<BR>
     * <BR>
     * �@@�@@�@@If [2: Def.PAST_TWELVE_MONTH]<BR>
     * �@@�@@�@@�@@Set year and month 12 months ago(YYYYMM) of bizDate(*1)<BR>
     * <BR>
     * �@@�@@�ASet maximum<BR>
     * �@@�@@�@@�ESet year and month(YYYYMM) of bizDate(*1)<BR>
     * <BR>
     * �@@4-3)If l_orderBizDate != null<BR>
     * �@@�@@Add l_orderBizDate to ArrayList<BR>
     * <BR>
     * 5)If l_productTypeList != null<BR>
     * �@@Add all elements of l_productTypeList to ArrayList<BR>
     * <BR>
     * 6)Return return values of the created ArrayList.toArray()<BR>
     * <BR>
     * (*1)bizDate<BR>
     * �@@Acquired at TradingSystem.getBizDate()<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * <BR>
     * �،���ЃR�[�h<BR>
     * <BR>
     * l_strInstitutionCode<BR>
     * <BR>
     * @@param l_branchCode - (���X�R�[�h)<BR>
     * <BR>
     * ���X�R�[�h�̔z��<BR>
     * <BR>
     * l_branchCode<BR>
     * <BR>
     * @@param l_summryDiv - (�W�v�敪)<BR>
     * <BR>
     * �W�v�敪<BR>
     * <BR>
     * 0�F�@@����<BR>
     * 1�F�@@����<BR>
     * <BR>
     * -----<English>---------<BR>
     * <BR>
     * l_summryDiv<BR>
     * <BR>
     * 0: Def.����<BR>
     * 1: Def.����<BR>
     * <BR>
     *
     * @@param l_orderBizDate - (������)<BR>
     * <BR>
     * ������<BR>
     * <BR>
     * l_orderBizDate<BR>
     * <BR>
     *
     * @@param l_dailySumYm - (���ʏW�v�Ώ۔N��)<BR>
     * <BR>
     * ���ʏW�v�Ώ۔N��<BR>
     * <BR>
     * l_dailySumYm<BR>
     * <BR>
     * @@param l_monthlySumDiv - (���ʏW�v�Ώۋ敪)<BR>
     * <BR>
     * ���ʏW�v�Ώۋ敪<BR>
     * <BR>
     * 0�F�@@�ߋ�3����<BR>
     * 1�F�@@�ߋ�6����<BR>
     * 2�F�@@�ߋ�12����<BR>
     * <BR>
     * ----<English>--------<BR>
     * <BR>
     * l_monthlySumDiv<BR>
     * <BR>
     * 0: Def.�ߋ�3����<BR>
     * 1: Def.�ߋ�6����<BR>
     * 2: Def.�ߋ�12����<BR>
     * <BR>
     * @@param l_productTypeList - (���i�敪�ꗗ)<BR>
     * <BR>
     * ���i�敪�ꗗ<BR>
     * ���C�ӂ̈ȉ��̒l�̔z��<BR>
     * <BR>
     * 0�F�@@��������<BR>
     * 1�F�@@�M�p���<BR>
     * 2�F�@@�����~�j����<BR>
     * 3�F�@@�I�v�V����<BR>
     * 4�F�@@�敨<BR>
     * 5�F�@@���M<BR>
     * 6�F�@@����F<BR>
     * 7�F�@@MMF<BR>
     * 8�F�@@�O������<BR>
     * 9�F�@@��<BR>
     * <BR>
     * ---<English>------------<BR>
     * <BR>
     * l_productTypeList<BR>
     * ��An Array of the following arbitrary values<BR>
     * <BR>
     * 0�F�@@��������<BR>
     * 1�F�@@�M�p���<BR>
     * 2�F�@@�����~�j����<BR>
     * 3�F�@@�I�v�V����<BR>
     * 4�F�@@�敨<BR>
     * 5�F�@@���M<BR>
     * 6�F�@@����F<BR>
     * 7�F�@@MMF<BR>
     * 8�F�@@�O������<BR>
     * 9�F�@@��<BR>
     * <BR>
     * @@return String[]
     * @@roseuid 419B2E38035C
     */
    protected String[] createQueryDataContainer(
        String l_strInstitutionCode,
        String[] l_branchCode,
        String l_summryDiv,
        String l_orderBizDate,
        String l_dailySumYm,
        String l_monthlySumDiv,
        String[] l_productTypeList)
    {
        final String STR_METHOD_NAME =
            "createQueryDataContainer("
                + "String, String[], String, String, String, String, String[])";
        log.entering(STR_METHOD_NAME);
        final String DATE_PATTERN = "yyyyMM";
        final int INT_LAST_THREE = -03;
        final int INT_LAST_SIX = -06;
        final int INT_LAST_TWELVE = -12;
        ArrayList l_lisDataContainer = null;
        Date l_datBizDate = null;
        Calendar l_calCurrentDate = null;
        String[] l_strDataContainer = null;
        int l_intArraySize = 0;
        TradingSystem l_tradingSystem = null;
        FinApp l_finApp = null;

        // Step 1. Create ArrayList
        l_lisDataContainer = new ArrayList();

        // Step 2. Add InstitutionCode and SummaryDiv value
        l_lisDataContainer.add(l_strInstitutionCode);
        l_lisDataContainer.add(l_summryDiv);

        // Step 3. Add all elements of l_branchCode
        l_intArraySize = l_branchCode.length;
        for (int i = 0; i < l_intArraySize; i++)
        {
            l_lisDataContainer.add(l_branchCode[i]);
        }

        // Step 4. Order date condition for not null
        if (l_dailySumYm != null)
        {
            l_lisDataContainer.add(l_dailySumYm + "%");
        }

        // MonthlySumDiv condition for not null
        if (l_monthlySumDiv != null)
        {
            // Step 4.1 Set Minimum date
            l_finApp = (FinApp) Services.getService(FinApp.class);
            l_tradingSystem = l_finApp.getTradingSystem();
            l_datBizDate = l_tradingSystem.getBizDate();
            l_calCurrentDate = Calendar.getInstance();
            l_calCurrentDate.setTime(l_datBizDate);

            if (WEB3AdminMonthlySumDivDef.PAST_THREE_MONTH.equals(l_monthlySumDiv))
            {
                l_calCurrentDate.add(Calendar.MONTH, INT_LAST_THREE);
                l_lisDataContainer.add(
                    WEB3DateUtility.formatDate(l_calCurrentDate.getTime(), DATE_PATTERN));
            } else if (WEB3AdminMonthlySumDivDef.PAST_SIX_MONTH.equals(l_monthlySumDiv))
            {
                l_calCurrentDate.add(Calendar.MONTH, INT_LAST_SIX);
                l_lisDataContainer.add(
                    WEB3DateUtility.formatDate(l_calCurrentDate.getTime(), DATE_PATTERN));
            } else
            {
                l_calCurrentDate.add(Calendar.MONTH, INT_LAST_TWELVE);
                l_lisDataContainer.add(
                    WEB3DateUtility.formatDate(l_calCurrentDate.getTime(), DATE_PATTERN));
            }

            // Step 4.2 Set Maximum date
            l_lisDataContainer.add(WEB3DateUtility.formatDate(l_datBizDate, DATE_PATTERN));
        }

        //If orderBizdate is not null then add to the arraylist
        if (l_orderBizDate != null)
        {
            l_lisDataContainer.add(l_orderBizDate);
        }

        l_intArraySize = l_productTypeList.length;
        for (int i = 0; i < l_intArraySize; i++)
        {
            l_lisDataContainer.add(l_productTypeList[i]);
        }
        l_strDataContainer = new String[l_lisDataContainer.size()];
        l_lisDataContainer.toArray(l_strDataContainer);

        log.exiting(STR_METHOD_NAME);
        return l_strDataContainer;
    }

    /**
     * (create�\�[�g����)<BR>
     * <BR>
     * �\�[�g�������쐬����B<BR>
     * <BR>
     * �P�j�e�[�u���񕨗������A�ȉ��̃\�[�g������\���\�[�g������������쐬����B<BR>
     *
     * <BR>
     * �@@�@@���������e�[�u��.�������@@����<BR>
     * �@@�A���������e�[�u��.�����o�H�敪�@@����<BR>
     * �@@�B���������e�[�u��.���i�@@����<BR>
     * �@@�C���������e�[�u��.�s��R�[�h�@@����<BR>
     * <BR>
     * �Q�j�쐬�����\�[�g�����������ԋp����B<BR>
     * <BR>
     * ----<English>----------------<BR>
     * <BR>
     * createSortCond<BR>
     * <BR>
     * Create sortCond<BR>
     * <BR>
     * 1)Create sortCond showing the following sortCond from field names in the
     * table<BR>
     * <BR>
     * �@@�@@order_executed_count.biz_date�@@in ascending order<BR>
     * �@@�Aorder_executed_count.order_root_div�@@in ascending order<BR>
     * �@@�Border_executed_count.product_div�@@in ascending order<BR>
     * �@@�Corder_executed_count.market_code�@@in ascending order<BR>
     * <BR>
     * 2)Return the created sortCond<BR>
     * <BR>
     * @@return java.lang.String
     * @@roseuid 419B2E380361
     */
    protected String createSortCond()
    {
        final String STR_METHOD_NAME = "createSortCond()";
        log.entering(STR_METHOD_NAME);
        StringBuffer l_sbBuffer = new StringBuffer();
        String l_strSortCond = null;

        // Step 1. Create sortCond
        l_sbBuffer.append("biz_date asc, ");
        l_sbBuffer.append("order_root_div asc, ");
        l_sbBuffer.append("product_div asc, ");
        l_sbBuffer.append("market_code asc");
        l_strSortCond = l_sbBuffer.toString();

        // Step 2. Return the created sortCond
        log.exiting(STR_METHOD_NAME);
        return l_strSortCond;
    }

    /**
     * (get���������ꗗ)<BR>
     * <BR>
     * �����̏����ɊY�����钍������Params�̈ꗗ��ԋp����B<BR>
     * <BR>
     * �P�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@arg0�F�@@��������Row.TYPE<BR>
     * �@@�@@arg1�F�@@�p�����[�^.��������������<BR>
     * �@@�@@arg2�F�@@�p�����[�^.�\�[�g����<BR>
     * �@@�@@arg3�F�@@null<BR>
     * �@@�@@arg4�F�@@�p�����[�^.���������f�[�^�R���e�i<BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �R�j�Q�j�̌������ʂ�ԋp����B<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * getOrderCountList<BR>
     * <BR>
     * Return the list of orderCountList corresponding to the conditions of
     * arguments<BR>
     * <BR>
     * 1)Call QueryProcessor.doFindAllQuery() method<BR>
     * <BR>
     * �@@[Parameter set into doFindAllQuery()]<BR>
     * �@@�@@arg0: orderExecutedCountRow.TYPE<BR>
     * �@@�@@arg1: l_strQueryCond<BR>
     * �@@�@@arg2: l_strSortCond<BR>
     * �@@�@@arg3: null<BR>
     * �@@�@@arg4: l_strQueryCondDataContainer<BR>
     * <BR>
     * �@@Return null if there is no search result<BR>
     * <BR>
     * 3)Return the search result of 2)<BR>
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
     * @@param l_strSortCond - (�\�[�g����)<BR>
     * <BR>
     * �\�[�g����<BR>
     * <BR>
     * l_strSortCond<BR>
     * <BR>
     * @@return java.util.List
     * @@throws DataNetworkException DataNetworkException
     * @@throws DataQueryException DataQueryException
     * @@roseuid 419AD10702FE
     */
    protected List getOrderCountList(
        String l_strQueryCond,
        String[] l_strQueryCondDataContainer,
        String l_strSortCond)
        throws DataNetworkException, DataQueryException
    {
        final String STR_METHOD_NAME = "getOrderCountList(String, String[], String)";
        log.entering(STR_METHOD_NAME);

        QueryProcessor l_queryProcessor = null;
        List l_lisSearchResult = null;
        int l_intSearchResultLength = 0;

        l_queryProcessor = Processors.getDefaultProcessor();

        // Step 1Call QueryProcessor.doFindAllQuery() method
        l_lisSearchResult =
            l_queryProcessor.doFindAllQuery(
                OrderExecutedCountRow.TYPE,
                l_strQueryCond,
                l_strSortCond,
                null,
                l_strQueryCondDataContainer);

        // If return searchresult length is greater than zero then return the result
        l_intSearchResultLength = l_lisSearchResult.size();
        if (l_intSearchResultLength > 0)
        {
            log.exiting(STR_METHOD_NAME);
            return l_lisSearchResult;
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (create�����Ώۏ��)<BR>
     * <BR>
     * �����̒��������f�[�^���<BR>
     * �����Ώۏ��̔z����쐬���ĕԋp����B<BR>
     * <BR>
     * �P�j�������i�[���X�g����<BR>
     * �@@TreeMap�𐶐�����B<BR>
     * <BR>
     * �Q�j�����o�H�i�[���X�g����<BR>
     * �@@�p�����[�^.�����o�H�\���敪 == "�ڍ�"�̏ꍇ�A<BR>
     * �@@TreeMap�𐶐�����B<BR>
     * <BR>
     * �R�j�s��R�[�h�i�[���X�g����<BR>
     * �@@�p�����[�^.�s��\���敪 == "�ڍ�"�̏ꍇ�A<BR>
     * �@@TreeMap�𐶐�����B<BR>
     * <BR>
     * �S�j�����Ώۏ�񐶐�<BR>
     * �@@�����Ώۏ��C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �T�j�e�f�[�^�擾<BR>
     * �@@�p�����[�^.���������ꗗ�̗v�f�����A<BR>
     * �@@�ȉ��̏������J��Ԃ��B<BR>
     * �@@�T�|�P�j�������i�[���X�g.put()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@[put()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@key�F�@@�����Ώۂ̗v�f.������<BR>
     * �@@�@@�@@value�F�@@�����Ώۂ̗v�f.������<BR>
     * <BR>
     * �@@�T�|�Q�j�p�����[�^.�����o�H�\���敪 == "�ڍ�"�̏ꍇ�A<BR>
     * �@@�@@�����o�H�i�[���X�g.put()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@[put()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@key�F�@@�����Ώۂ̗v�f.�����o�H�敪<BR>
     * �@@�@@�@@value�F�@@�����Ώۂ̗v�f.�����o�H�敪<BR>
     * <BR>
     * �@@�T�|�R�j�p�����[�^.�s��\���敪 == "�ڍ�"�̏ꍇ�A<BR>
     * �@@�@@�s��R�[�h�i�[���X�g.put()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@[put()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@key�F�@@�����Ώۂ̗v�f.�s��R�[�h<BR>
     * �@@�@@�@@value�F�@@�����Ώۂ̗v�f.�s��R�[�h<BR>
     * <BR>
     * �R�j�v���p�e�B�Z�b�g<BR>
     * �@@�������������Ώۏ��C���X�^���X�Ɉȉ��̃v���p�e�B��<BR>
     * �@@�Z�b�g����B<BR>
     * <BR>
     * �@@�������ꗗ = �������i�[���X�g.values().toArray()�̖߂�l<BR>
     * �@@�����o�H�敪�ꗗ = �����o�H�i�[���X�g.values().toArray()�̖߂�l<BR>
     * �@@�s��R�[�h�ꗗ = �s��R�[�h�i�[���X�g.values().toArray()�̖߂�l<BR>
     * <BR>
     * �S�j�v���p�e�B�Z�b�g���������Ώۏ��C���X�^���X��ԋp����B<BR>
     * <BR>
     * -----<English>--------------<BR>
     * <BR>
     * createProcessingObjectInfo<BR>
     * <BR>
     * Create an Object of processingObjectInfo from the argument, l_orderNumberList<BR>
     *
     * <BR>
     * 1)Create a list to store orderBizDate<BR>
     * �@@Create TreeMap<BR>
     * <BR>
     * 2) Create TreeMap<BR>
     * <BR>
     * 3) Create TreeMap<BR>
     * <BR>
     * 4)Create processingObjectInfo<BR>
     * �@@Create an instance of processingObjectInfo<BR>
     * <BR>
     * 5)Acquire each data<BR>
     * �@@Loop the following process for as many times as the number of the elements of
     * l_orderNumberList<BR>
     * �@@5-1)Call orderBizDateList.put() method<BR>
     * <BR>
     * �@@�@@[Parameter set into put()]<BR>
     * �@@�@@�@@key: element to be processed.orderBizDate<BR>
     * �@@�@@�@@value: element to be processed.orderBizDate<BR>
     * <BR>
     * �@@5-2)If l_orderRootDspDiv == Def.DETAIL,<BR>
     * �@@�@@Call orderRootDivList.put() method<BR>
     * <BR>
     * �@@�@@[Parameter set into put()]<BR>
     * �@@�@@�@@key: element to be processed.orderRootDiv<BR>
     * �@@�@@�@@value: element to be processed.orderRootDiv<BR>
     * <BR>
     * �@@5-3)If l_marketDspDiv == Def.DETAIL,<BR>
     * �@@�@@Call marketCodeList.put() method<BR>
     * <BR>
     * �@@�@@[Parameter set into put()]<BR>
     * �@@�@@�@@key: element to be processed.marketCode<BR>
     * �@@�@@�@@value: element to be processed.marketCode<BR>
     * <BR>
     * 3)PropertySet<BR>
     * �@@Set the following properties into the created processingObjectInfo
     * instance<BR>
     * <BR>
     * �@@orderBizDateList = orderBizDateList.values().return value of toArray()<BR>
     * �@@orderRootDivList = orderRootDivList.values().return value of toArray()<BR>
     * �@@marketCodeList = marketCodeList.values().return value of toArray()<BR>
     * <BR>
     * 4)Return the instance of processingObjectInfo set into 'Property Set'<BR>
     * <BR>
     * @@param l_orderNumberList - (���������ꗗ)<BR>
     * <BR>
     * ��������Params�̔z��<BR>
     * <BR>
     * ----<English>--------<BR>
     * <BR>
     * l_orderNumberList<BR>
     * <BR>
     * An array of orderExecutedCountParams<BR>
     * <BR>
     * @@param l_orderRootDspDiv - (�����o�H�\���敪)<BR>
     * <BR>
     * �����o�H�\���敪<BR>
     * <BR>
     * 0�F�@@�ڍ�<BR>
     * 1�F�@@���v<BR>
     * <BR>
     * ----<English>--------<BR>
     * <BR>
     * l_orderRootDspDiv<BR>
     * <BR>
     * 0: Def.�ڍ�<BR>
     * 1: Def.���v<BR>
     * <BR>
     * @@param l_marketDspDiv - (�s��\���敪)<BR>
     * <BR>
     * �s��\���敪<BR>
     * <BR>
     * 0�F�@@�ڍ�<BR>
     * 1�F�@@���v<BR>
     * <BR>
     * ----<English>--------<BR>
     * <BR>
     * l_marketDspDiv<BR>
     * <BR>
     * 0: Def.�ڍ�<BR>
     * 1: Def.���v<BR>
     * <BR>
     *
     * @@return processingObjectInfo
     * @@roseuid 419C41D103A0
     */
    protected WEB3AdminProcessingObjectInfo createProcessingObjectInfo(
        OrderExecutedCountParams[] l_orderNumberList,
        String l_orderRootDspDiv,
        String l_marketDspDiv)
    {
        final String STR_METHOD_NAME =
            "createProcessingObjectInfo(" + "OrderExecutedCountParams[], String, String)";
        log.entering(STR_METHOD_NAME);

        TreeMap l_mapOrderBizDate = null;
        TreeMap l_mapOrderRootDiv = null;
        TreeMap l_mapMarketCode = null;
        WEB3AdminProcessingObjectInfo l_processingObjectInfo = null;
        String[] l_strMapOrders = null;
        int l_intArraySize = 0;

        // Step1. Create a list to Store orderBizDate
        l_mapOrderBizDate = new TreeMap();

        // Step2. Create a list to store orderRoot
        l_mapOrderRootDiv = new TreeMap();

        // Step3. Create a list to store marketCode
        l_mapMarketCode = new TreeMap();

        // Step4. Create processingObjectInfo
        l_processingObjectInfo = new WEB3AdminProcessingObjectInfo();

        // Step5. Acquire each data
        l_intArraySize = l_orderNumberList.length;
        for (int i = 0; i < l_intArraySize; i++)
        {
            // Step5.1 Add the bizDate to the list
            l_mapOrderBizDate.put(l_orderNumberList[i].biz_date, l_orderNumberList[i].biz_date);

            // Step5.2 orderRootDisp is equal to def.DETAIL
            if (WEB3AdminOrderRootDspDivDef.DETAIL.equals(l_orderRootDspDiv))
            {
                l_mapOrderRootDiv.put(
                    l_orderNumberList[i].order_root_div,
                    l_orderNumberList[i].order_root_div);
            }

            // Step5.3 marketDspDiv is equal to def.DETAIL
            if (WEB3AdminOrderRootDspDivDef.DETAIL.equals(l_marketDspDiv))
            {
                String l_strMarketCode;
                if (l_orderNumberList[i].market_code.matches("^[0-9]"))
                {
                    l_strMarketCode = "0" + l_orderNumberList[i].market_code;
                }
                else
                {
                    l_strMarketCode = l_orderNumberList[i].market_code;
                }
                l_mapMarketCode.put(
                    l_strMarketCode,
                    l_orderNumberList[i].market_code);
            }
        }

        // Step6. Set Property
        l_strMapOrders = new String[l_mapOrderBizDate.size()];
        l_processingObjectInfo.orderBizDateList =
            (String[]) (l_mapOrderBizDate.values()).toArray(l_strMapOrders);
        if (WEB3AdminOrderRootDspDivDef.DETAIL.equals(l_orderRootDspDiv))
        {
            l_strMapOrders = new String[l_mapOrderRootDiv.size()];
            l_processingObjectInfo.orderRootDivList =
                (String[]) (l_mapOrderRootDiv.values()).toArray(l_strMapOrders);
        } else
        {
            l_processingObjectInfo.orderRootDivList = null;
        }
        if (WEB3AdminOrderRootDspDivDef.DETAIL.equals(l_marketDspDiv))
        {
            l_strMapOrders = new String[l_mapMarketCode.size()];
            l_processingObjectInfo.marketCodeList =
                (String[]) (l_mapMarketCode.values()).toArray(l_strMapOrders);
        } else
        {
            l_processingObjectInfo.marketCodeList = null;
        }
        log.exiting(STR_METHOD_NAME);
        return l_processingObjectInfo;
    }

    /**s
     * (create�����o�H�ʌ������ꗗ)<BR>
     * <BR>
     * �����̒����o�H�敪���Ƃ̌������Z�o���A<BR>
     * �����o�H�ʌ������̔z��Ƃ��ĕԋp����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��Ғ�����茏���Ɖ�T�[�r�X)create�����o�H�ʌ������ꗗ�v<BR>
     * �Q��<BR>
     * <BR>
     * ----<English>----------<BR>
     * <BR>
     * createOrderRootCountInfoList<BR>
     * <BR>
     * Calculate the number of the count for each orderRootDiv and <BR>
     * return the array of WEB3AdminOROrderRootNumberInfoUnit<BR>
     * <BR>
     * Refer to the sequence diagram "(administrator: order execution count inquiry
     * service)createOrderRootCountInfoList"
     * <BR>
     * @@param l_orderNumberList - (���������ꗗ)<BR>
     * <BR>
     * ��������Params�̔z��<BR>
     * <BR>
     * ----<English>----------<BR>
     * <BR>
     * l_orderNumberList<BR>
     * <BR>
     * An array of orderExecutedCountParams<BR>
     * <BR>
     * @@param l_processingObjectInfo - l_processingObjectInfo
     * (�����Ώۏ��)<BR>
     * <BR>
     * �����Ώۏ��<BR>
     * <BR>
     * l_processingObjectInfo<BR>
     * <BR>
     * @@param l_productTypeList - (���i�敪�ꗗ)<BR>
     * <BR>
     * ���i�敪�ꗗ<BR>
     * ���C�ӂ̈ȉ��̒l�̔z��<BR>
     * <BR>
     * 0�F�@@��������<BR>
     * 1�F�@@�M�p���<BR>
     * 2�F�@@�����~�j����<BR>
     * 3�F�@@�I�v�V����<BR>
     * 4�F�@@�敨<BR>
     * 5�F�@@���M<BR>
     * 6�F�@@����F<BR>
     * 7�F�@@MMF<BR>
     * 8�F�@@�O������<BR>
     * 9�F�@@��<BR>
     * <BR>
     * ----<English>----------<BR>
     * <BR>
     * l_productTypeList<BR>
     * ��An array of the following arbitrary values<BR>
     * <BR>
     * 0: Def.EQUITY<BR>
     * 1: Def.MARGIN<BR>
     * 2: Def.MINI_STOCK<BR>
     * 3: Def.OPTION<BR>
     * 4: Def.FUTURE<BR>
     * 5: Def.MF<BR>
     * 6: Def.MIDIUM_TERM_GOV_FUND<BR>
     * 7: Def.MMF<BR>
     * 8: Def.FOREGIN_STOCK<BR>
     * 9: Def.BOND<BR>
     * <BR>
     * @@return WEB3AdminOROrderRootNumberInfoUnit[]
     * @@roseuid 419C114602C9
     */
    protected WEB3AdminOROrderRootNumberInfoUnit[] createOrderRootCountInfoList(
        OrderExecutedCountParams[] l_orderNumberList,
        WEB3AdminProcessingObjectInfo l_processingObjectInfo,
        String[] l_productTypeList)
    {
        final String STR_METHOD_NAME =
            "createOrderRootCountInfoList("
                + "OrderExecutedCountParams[], ProcessingObjectInfo, String[])";
        log.entering(STR_METHOD_NAME);

        if (l_orderNumberList == null || l_orderNumberList.length == 0)
        {
            return null;
        }

        // Step 1.3
        ArrayList l_lisOrderRootNumberInfoUnits = new ArrayList();

        // Step 1.4
        for (int i = 0; i < l_processingObjectInfo.orderRootDivList.length; i++)
        {
            // Step 1.4.1 Create the array list
            ArrayList l_lisOrderExecuteCountParamsList = new ArrayList();

            // Step 1.4.2
            for (int j = 0; j < l_orderNumberList.length; j++)
            {
                // Step 1.4.2.1
                String l_strProcessOrderRoot = l_processingObjectInfo.orderRootDivList[i];
                String l_strOrderRootDiv = l_orderNumberList[j].order_root_div;

                // if the order root dic is equal to the processing object order root div
                if (l_strProcessOrderRoot.equals(l_strOrderRootDiv))
                {
                    // Step 1.4.2.1.1
                    l_lisOrderExecuteCountParamsList.add(l_orderNumberList[j]);
                } else
                {
                    if (l_lisOrderExecuteCountParamsList.size() != 0)
                    {
                        break;
                    }
                }
            }

            //Step 1.4.4 Create productMarketTotalCount
            OrderExecutedCountParams[] l_orderExecParams = 
                (OrderExecutedCountParams[])l_lisOrderExecuteCountParamsList.toArray(
                    new OrderExecutedCountParams[0]
                    );

            String[] l_strMarketCodes = l_processingObjectInfo.marketCodeList;

            List l_lisProductMarket =
                createProductMarketTotalCountInfoList(
                    l_orderExecParams,
                    l_productTypeList,
                    l_strMarketCodes
                    );

            // Step 1.4.5 Create WEB3AdminOROrderRootNumberInfoUnit instance
            WEB3AdminOROrderRootNumberInfoUnit l_adminOrOrderRootNumber 
                = new WEB3AdminOROrderRootNumberInfoUnit();

            // Step 1.4.6
            l_adminOrOrderRootNumber.orderRootDiv = 
                l_processingObjectInfo.orderRootDivList[i];

            l_adminOrOrderRootNumber.productMarketNumberInfoList = 
                (WEB3AdminORProductMarketNumberInfoUnit[])l_lisProductMarket.toArray(
                    new WEB3AdminORProductMarketNumberInfoUnit[0]
                    );

            // Step 1.4.7 add to orderRootNumber to the orderRootNumber list
            l_lisOrderRootNumberInfoUnits.add(l_adminOrOrderRootNumber);
        }

        WEB3AdminOROrderRootNumberInfoUnit[] l_orderRootNumberInfoUnits =
        (WEB3AdminOROrderRootNumberInfoUnit[])l_lisOrderRootNumberInfoUnits.toArray(
            new WEB3AdminOROrderRootNumberInfoUnit[0]);

        log.exiting(STR_METHOD_NAME);
        return l_orderRootNumberInfoUnits;
    }

    /**
     * (create���i�s��ʍ��v�������ꗗ)<BR>
     * <BR>
     * �����̒�������Params���s��A�܂��͏��i���Ƃ�<BR>
     * ���v�������Z�o���A<BR>
     * ���i�s��ʌ������̔z��Ƃ��ĕԋp����B<BR>
     * <BR>
     * �P�j�ԋp�l�i�[���X�g����<BR>
     * �@@ArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�W�v�Ώۃ��X�g����<BR>
     * �@@ArrayList�𐶐����A�p�����[�^.��������Params�̑S�v�f���R�s�[����B<BR>
     * <BR>
     * �R�j���i�s��ʍ��v�����Z�o<BR>
     * �@@�R�|�P�j�p�����[�^.���i�敪�ꗗ�̗v�f�����ȉ��̏������J��Ԃ��B<BR>
     * �@@�@@�R�|�P�|�P�j�p�����[�^.�s��R�[�h�ꗗ�̗L���ɂ��A�����𕪊򂷂�B<BR>
     * �@@�@@�@@���W�v��������/��茏���̏����l��0�Ƃ���B<BR>
     * <BR>
     * �@@�@@�@@�@@[�p�����[�^.�s��R�[�h�ꗗ != null�̏ꍇ] // �s��ڍו\��<BR>
     * �@@�@@�@@�@@�@@�p�����[�^.�s��R�[�h�ꗗ�̗v�f�����ȉ��̏������J��Ԃ��B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�W�v�Ώۃ��X�g.size() != 0�̏ꍇ�A�W�v�Ώۃ��X�g�̓�<BR>
     * �@@�@@�@@�@@�@@�@@�@@�����Ώۂ̏��i�敪�A�s��R�[�h�ɊY������v�f��<BR>
     * �@@�@@�@@�@@�@@�@@�@@��������(�� + ��)�A��茏��(�� + ��)�����ꂼ��W�v����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@���W�v������A�����Ώۂ̏��i�敪�A�s��R�[�h��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�Y������v�f���W�v�Ώۃ��X�g����폜����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�A���i�s��ʌ������C���X�^���X�𐶐�����B<BR>
     * �@@�@@�@@�@@�@@�@@�B�A�ɂĐ��������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�W�v�Ώۏ��i�敪 = �����Ώۂ̏��i�敪<BR>
     * �@@�@@�@@�@@�@@�@@�@@�s��R�[�h = �����Ώۂ̎s��敪<BR>
     * �@@�@@�@@�@@�@@�@@�@@�������� = �W�v������������<BR>
     * �@@�@@�@@�@@�@@�@@�@@��茏�� = �W�v������茏��<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�C�ԋp�l�i�[���X�g�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B<BR>
     *
     * <BR>
     * �@@�@@�@@�@@[�p�����[�^.�s��R�[�h�ꗗ == null�̏ꍇ] // �s�ꍇ�v�\��<BR>
     * �@@�@@�@@�@@�@@�@@�W�v�Ώۃ��X�g.size() != 0, �W�v�Ώۃ��X�g�̓�<BR>
     * �@@�@@�@@�@@�@@�@@�@@�����Ώۂ̏��i�敪�ɊY������v�f��<BR>
     * �@@�@@�@@�@@�@@�@@�@@��������(�� + ��)�A��茏��(�� + ��)�����ꂼ��W�v����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@���W�v������A�����Ώۂ̏��i�敪��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�Y������v�f���W�v�Ώۃ��X�g����폜����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�A���i�s��ʌ������C���X�^���X�𐶐�����B<BR>
     * �@@�@@�@@�@@�@@�B�A�ɂĐ��������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@�@@�@@�@@�@@�W�v�Ώۏ��i�敪 = �����Ώۂ̏��i�敪<BR>
     * �@@�@@�@@�@@�@@�@@�s��R�[�h = null<BR>
     * �@@�@@�@@�@@�@@�@@�������� = �W�v������������<BR>
     * �@@�@@�@@�@@�@@�@@��茏�� = �W�v������茏��<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�C�ԋp�l�i�[���X�g�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B<BR>
     * <BR>
     * �S�j�ԋp�l�i�[���X�g.toArray()�̖߂�l��ԋp����B<BR>
     * <BR>
     * ------<English>---------------------------<BR>
     * <BR>
     * createProductMarketTotalCountInfoList<BR>
     * <BR>
     * Calculate the totalCount for each market or product from the argument
     * l_orderExecutedCountParams, and<BR>
     * return an array of productMarketTotalCountInfoList<BR>
     * <BR>
     * 1)Create a list to store return values<BR>
     * �@@Create ArrayList<BR>
     * <BR>
     * 2)Create a summary list<BR>
     * �@@Create ArrayList and copy all elements of l_orderExecutedCountParams<BR>
     * <BR>
     * 3)Calculate productMarketTotalCount<BR>
     * �@@3-1)Loop the following process for as many times as the number of the elements
     * of l_productTypeList<BR>
     * �@@�@@3-1-1)Go to either of the following processes according to the values of
     * l_marketCodeList<BR>
     * �@@�@@�@@��Let the default orerNumber/executeNumber 0.<BR>
     * <BR>
     * �@@�@@�@@�@@If [l_marketCodeList != null] // showing market details<BR>
     * �@@�@@�@@�@@�@@Loop the following process for as many times as the number of the
     * elements of l_marketCodeList<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@If summary list.size() != 0, calculate the total of the elements
     * of<BR>
     * orderNumber(buy and sell) and executeNumber(executeCount)<BR>
     * corresponding to productDiv and marketCode in the summary list<BR>
     * ��After the calculation, remove the elements concerned from the list
     * <BR>
     * �@@�@@�@@�@@�@@�@@�ACreate productMarketTotalCountInfo instance<BR>
     * �@@�@@�@@�@@�@@�@@�BSet the following properties into the instance created in �A<BR>
     * �@@�@@�@@�@@�@@�@@�@@sumProductType = productDiv to be processed<BR>
     * �@@�@@�@@�@@�@@�@@�@@marketCode = marketCode to be processed<BR>
     * �@@�@@�@@�@@�@@�@@�@@orderNumber = total orderNumber<BR>
     * �@@�@@�@@�@@�@@�@@�@@executeNumber = total executeNumber<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�CAdd the instance set into 'Property Set' to a list to store return
     * values<BR>
     * <BR>
     * �@@�@@�@@�@@If [l_marketCodeList == null] // showing market total<BR>
     * �@@�@@�@@�@@�@@�@@If summary list.size() != 0, calculate the total of the elements
     * of<BR>
     * orderNumber(buy and sell) and executeNumber(executeCount)<BR>
     * corresponding to productDiv and marketCode in summary list<BR>
     * �@@�@@�@@�@@�@@�@@�@@��Delete the elements corresponding to the productDiv from summary
     * list<BR>
     * after calculation<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�ACreate productMarketTotalCountInfo instance<BR>
     * �@@�@@�@@�@@�@@�BSet the following properties into the instance created in �A<BR>
     * �@@�@@�@@�@@�@@�@@sumProductType = productDiv to be processed<BR>
     * �@@�@@�@@�@@�@@�@@marketCode = null<BR>
     * �@@�@@�@@�@@�@@�@@orderNumber = total orderNumber<BR>
     * �@@�@@�@@�@@�@@�@@executeNumber = total executeNumber<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�CAdd the instance set into 'Property Set' into the list to store
     * return values<BR>
     * <BR>
     * 4)Return the list to store return values.return value of toArray()<BR>
     * <BR>
     * @@param l_orderNumberList - (���������ꗗ)<BR>
     * <BR>
     * ��������Params�̔z��<BR>
     * <BR>
     * ----<English>-------------------<BR>
     * <BR>
     * l_orderNumberList<BR>
     * <BR>
     * An array of orderExecutedCountParams<BR>
     * <BR>
     * @@param l_productTypeList - (���i�敪�ꗗ)<BR>
     * <BR>
     * ���i�敪�ꗗ<BR>
     * ���C�ӂ̈ȉ��̒l�̔z��<BR>
     * <BR>
     * 0�F�@@��������<BR>
     * 1�F�@@�M�p���<BR>
     * 2�F�@@�����~�j����<BR>
     * 3�F�@@�I�v�V����<BR>
     * 4�F�@@�敨<BR>
     * 5�F�@@���M<BR>
     * 6�F�@@����F<BR>
     * 7�F�@@MMF<BR>
     * 8�F�@@�O������<BR>
     * 9�F�@@��<BR>
     * <BR>
     * ----<English>-------------------<BR>
     * <BR>
     * l_productTypeList<BR>
     * ��An anrray of the following arbitrary values<BR>
     * <BR>
     * 0: Def.EQUITY<BR>
     * 1: Def.MARGIN<BR>
     * 2: Def.MINI_STOCK<BR>
     * 3: Def.OPTION<BR>
     * 4: Def.FUTURE<BR>
     * 5: Def.MF<BR>
     * 6: Def.MIDIUM_TERM_GOV_FUND<BR>
     * 7: Def.MMF<BR>
     * 8: Def.FOREGIN_STOCK<BR>
     * 9: Def.BOND<BR>
     * <BR>
     * @@param l_marketCodeList - (�s��R�[�h�ꗗ)<BR>
     * <BR>
     * �s��R�[�h�ꗗ<BR>
     * (�s��R�[�h��WEB3MarketCodeDef.java�ɂĒ�`)<BR>
     * <BR>
     * ----<English>-------------------<BR>
     * <BR>
     * l_marketCodeList<BR>
     * (marketCode is defined at WEB3MarketCodeDef.java)<BR>
     * <BR>
     * @@return List
     * @@roseuid 419B562B02CC
     */
    protected List createProductMarketTotalCountInfoList(
        OrderExecutedCountParams[] l_orderNumberList,
        String[] l_productTypeList,
        String[] l_marketCodeList)
    {
        final String STR_METHOD_NAME =
            "createProductMarketTotalCountInfoList("
                + "OrderExecutedCountParams[], String[], String[])";
        log.entering(STR_METHOD_NAME);

        ArrayList l_lisSummaryOrderNumbers = null;
        ArrayList l_lisProductMarketTotalCountInfoUnits = null;

        // Step 1 Create a arraylist to store the return values
        l_lisProductMarketTotalCountInfoUnits = new ArrayList();

        // 2) Create a summary list
        l_lisSummaryOrderNumbers = new ArrayList();
        l_lisSummaryOrderNumbers.addAll(Arrays.asList(l_orderNumberList));

        // 3)Calculate productMarketTotalCount
        // Step 3-1 Loop the following process for as many times as l_productTypeList
        
        // �s��ڍו\��
        if (l_marketCodeList != null && l_marketCodeList.length != 0)
        {
            for (int j = 0; j < l_productTypeList.length; j ++)
            {
                for (int i = 0; i < l_marketCodeList.length; i ++)
                {
                    WEB3AdminORProductMarketNumberInfoUnit l_unit =
                        getCountUnit(l_lisSummaryOrderNumbers,
                            l_productTypeList[j],
                            l_marketCodeList[i]
                            );
                    l_lisProductMarketTotalCountInfoUnits.add(l_unit);
                }
            }
        }
        // �s�ꍇ�v�\��
        else
        {
            for (int j = 0; j < l_productTypeList.length; j ++)
            {
                WEB3AdminORProductMarketNumberInfoUnit l_unit =
                    getCountUnit(l_lisSummaryOrderNumbers,
                        l_productTypeList[j]
                        );
                l_lisProductMarketTotalCountInfoUnits.add(l_unit);
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_lisProductMarketTotalCountInfoUnits;
    }
    
    /**
     * ���i�s��ʍ��v�������i�s��ڍו\���j���P���쐬����B
     * <br />
     * @@param orderExecCountList
     * @@param l_productType
     * @@param l_marketCode
     * @@return ���i�s��ʍ��v�������
     */
    private WEB3AdminORProductMarketNumberInfoUnit getCountUnit(
        ArrayList orderExecCountList,
        String l_productType, 
        String l_marketCode
        )
    {
        int l_orderCount = 0;
        int l_execCount = 0;
        
        for (Iterator iterator = orderExecCountList.iterator(); 
             iterator.hasNext(); )
        {
            OrderExecutedCountParams l_params = 
                (OrderExecutedCountParams)iterator.next();
            
            if (l_params.market_code.equals(l_marketCode) &&
                l_params.product_div.equals(l_productType))
            {
                l_orderCount = l_orderCount +  
                    l_params.buy_order_count + 
                    l_params.sell_order_count;
                l_execCount = l_execCount + 
                    l_params.execute_count;
            }
        }
        
        WEB3AdminORProductMarketNumberInfoUnit l_returnUnit = 
            new WEB3AdminORProductMarketNumberInfoUnit();
        
        l_returnUnit.marketCode = l_marketCode;
        l_returnUnit.sumProductType = l_productType;
        l_returnUnit.executeNumber = String.valueOf(l_execCount);
        l_returnUnit.orderNumber = String.valueOf(l_orderCount);
        
        return l_returnUnit;
    }

    /**
     * ���i�s��ʍ��v�������i�s�ꍇ�v�\���j���P���쐬����B
     * <br />
     * @@param orderExecCountList
     * @@param l_productType
     * @@param l_marketCode
     * @@return ���i�s��ʍ��v�������
     */
    private WEB3AdminORProductMarketNumberInfoUnit getCountUnit(
        ArrayList orderExecCountList,
        String l_productType 
        )
    {
        int l_orderCount = 0;
        int l_execCount = 0;
        
        for (Iterator iterator = orderExecCountList.iterator(); 
             iterator.hasNext(); )
        {
            OrderExecutedCountParams l_params = 
                (OrderExecutedCountParams)iterator.next();
            
            if (l_params.product_div.equals(l_productType))
            {
                l_orderCount = l_orderCount +  
                    l_params.buy_order_count + 
                    l_params.sell_order_count;
                l_execCount = l_execCount + 
                    l_params.execute_count;
            }
        }
        
        WEB3AdminORProductMarketNumberInfoUnit l_returnUnit = 
            new WEB3AdminORProductMarketNumberInfoUnit();
        
        l_returnUnit.marketCode = null;
        l_returnUnit.sumProductType = l_productType;
        l_returnUnit.orderNumber = String.valueOf(l_orderCount);
        l_returnUnit.executeNumber = String.valueOf(l_execCount);
        
        return l_returnUnit;
    }
}@
