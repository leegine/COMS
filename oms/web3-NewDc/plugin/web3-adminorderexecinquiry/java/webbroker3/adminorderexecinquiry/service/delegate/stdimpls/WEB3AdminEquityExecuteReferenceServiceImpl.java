head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.23.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminEquityExecuteReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҋ����������Ɖ�T�[�r�X�����N���X(WEB3AdminEquityExecuteReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/08/21 �юu��(���u) ���f��No.062
Revision History : 2006/08/30 �����F(���u) ���f�� 057
Revision History : 2006/08/30 ���r(���u) ���f�� 071
Revision History : 2006/11/20 ����(���u) ���f�� 081
Revision History : 2006/12/19 �����F (���u) �d�l�ύX�E���f��087
Revision History : 2007/02/15 ���� (���u) �d�l�ύX�E���f��091
Revision History : 2007/06/05 �đo�g (���u) �d�l�ύX�E���f��099
Revision History : 2007/10/09 �����q (���u) �d�l�ύX�E���f��107
Revision History : 2008/01/24 �g�E�N�| (���u) �d�l�ύX�E���f��110�A111�A113
*/
package webbroker3.adminorderexecinquiry.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.fitechlabs.dbind.ListPage;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderManager;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProductManager;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3Toolkit;

import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranchMarketDealtCond;
import webbroker3.gentrade.WEB3GentradeBranchMarketPTSDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RepaymentDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.adminorderexecinquiry.WEB3AdminOrderExecuteDataManager;
import webbroker3.adminorderexecinquiry.WEB3AdminProductExecutionInfo;
import webbroker3.adminorderexecinquiry.define.WEB3AdminKeyItemDef;
import webbroker3.adminorderexecinquiry.define.WEB3AdminProductDivDef;
import webbroker3.adminorderexecinquiry.define.WEB3AdminTradingTypeDef;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORDetailDispInfoUnit;
import webbroker3.adminorderexecinquiry.message.WEB3AdminOREquityOrderExecutionRefInputRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminOREquityOrderExecutionRefInputResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminOREquityOrderExecutionRefReferenceRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminOREquityOrderExecutionRefReferenceResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminOREquityOrderExecutionRefUnit;
import webbroker3.adminorderexecinquiry.message.WEB3AdminOROrderExecutionRefCommonRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminOROrderExecutionSortKeyUnit;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORTradingProductUnit;
import webbroker3.adminorderexecinquiry.service.delegate.WEB3AdminEquityExecuteReferenceService;

/**
 * (�Ǘ��Ҋ����������Ɖ�T�[�r�XImpl)<BR>
 * <BR>
 * �Ǘ��Ҋ����������Ɖ�T�[�r�X�����N���X<BR>
 * <BR>
 * WEB3AdminEquityExecuteReferenceServiceImpl<BR>
 * <BR>
 * @@author Umadevi and WaniShree
 * @@version 1.0
 */
public class WEB3AdminEquityExecuteReferenceServiceImpl
    extends WEB3ClientRequestService
    implements WEB3AdminEquityExecuteReferenceService
{
    /**
     * Log Variable
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityExecuteReferenceServiceImpl.class);

    /**
     * @@roseuid 4212FAC5021B
     */
    public WEB3AdminEquityExecuteReferenceServiceImpl()
    {

    }

    /**
     * �����������Ɖ�����s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * �ȉ��̃��\�b�h���Ăѕ�����B<BR>
     * <BR>
     * ���Ǘ��ҁE�����������Ɖ���̓��N�G�X�g�̏ꍇ<BR>
     * �@@this.get���͉��()���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE�����������Ɖ�N�G�X�g�̏ꍇ<BR>
     * �@@this.get�Ɖ���()���R�[������B<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * Execute WEB3AdminEquityExecuteReferenceService process<BR>
     * <BR>
     * Call one of the following methods based on the type of the argument,
     * l_request.<<BR>
     * <BR>
     * ��If WEB3AdminOREquityOrderExecutionRefInputRequest<BR>
     * �@@Call this.getInputScreen()<BR>
     * <BR>
     * ��If WEB3AdminOREquityOrderExecutionRefReferenceRequest<BR>
     * �@@Call this.getReferenceScreen()<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 41A5C0900298
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3GenResponse l_response = null;
        try
        {
            if (l_request instanceof WEB3AdminOREquityOrderExecutionRefInputRequest)
            {
                l_response =
                    getInputScreen((WEB3AdminOREquityOrderExecutionRefInputRequest) l_request);
            } else if (l_request instanceof WEB3AdminOREquityOrderExecutionRefReferenceRequest)
            {
                l_response =
                    getReferenceScreen(
                        (WEB3AdminOREquityOrderExecutionRefReferenceRequest) l_request);
            } else
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "INPUT ���N�G�X�g NOT �Ǘ��ҁE�����������Ɖ�N�G�X�g");
            }
        } catch (NotFoundException l_notFoundException)
        {
            log.error(l_notFoundException.getMessage(), l_notFoundException);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_notFoundException.getMessage(),
                l_notFoundException);
        } catch (DataQueryException l_dqex)
        {
            String l_strMsg = "Error while aquiring the Data ";
            log.error(l_strMsg, l_dqex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqex.toString(),
                l_dqex);

        } catch (DataNetworkException l_dnex)
        {
            String l_strMsg = "Error while aquiring the Data ";
            log.error(l_strMsg, l_dnex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dnex.toString(),
                l_dnex);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get���͉��)<BR>
     * <BR>
     * �����������Ɖ���͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��Ҋ����������Ɖ�T�[�r�X)get���͉�ʁv<BR>
     * �Q��<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * getInputScreen<BR>
     * <BR>
     * Execute WEB3AdminEquityExecuteReferenceService input screen process<BR>
     * <BR>
     * Refer to the sequence diagram "(administrator: equity order execution inquiry
     * service)getInputScreen"<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * <BR>
     * �Ǘ��ҁE�����������Ɖ���̓��N�G�X�g�N���X<BR>
     * <BR>
     * ---------<English>----------<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminOREquityOrderExecutionRefInputRequest<BR>
     * <BR>
     * @@return WEB3AdminOREquityOrderExecutionRefInputResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws NotFoundException NotFoundException
     * @@throws DataNetworkException DataNetworkException
     * @@throws WEB3SystemLayerException WEB3SystemLayerException
     * @@throws DataQueryException DataQueryException
     * @@throws DataFindException DataFindException
     * @@roseuid 41A5C08F0150
     */
    protected WEB3AdminOREquityOrderExecutionRefInputResponse
        getInputScreen(WEB3AdminOREquityOrderExecutionRefInputRequest l_request)
        throws WEB3BaseException, DataFindException, WEB3SystemLayerException, DataQueryException,
        DataNetworkException, NotFoundException
    {
        final String STR_METHOD_NAME =
            "getInputScreen(WEB3AdminOREquityOrderExecutionRefInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3Administrator l_administrator = null;
        WEB3AdminOrderExecuteDataManager l_adminOrderExecuteDataManager = null;
        WEB3AdminProductExecutionInfo l_productExecutionInfo = null;
        WEB3GentradeHandlingOrderCond l_gentradeHandlingOrderCond = null;
        WEB3AdminOREquityOrderExecutionRefInputResponse l_response = null;
        WEB3GentradeBizDate l_gentradeBizDate = null;
        WEB3AdminORTradingProductUnit[] l_arrAdminORTradingProductUnit = null;
        ArrayList l_listOrderBizDate = null;
        String l_strInstitutionCode = null;
        String[] l_arrBranchCodeList = null;
        ArrayList l_listRepaymentDivList = null;
        String[] l_arrRepaymentDivList = null;
        String[] l_arrHandlingPriceConds = null;
        String[] l_arrExecConds = null;
        String[] l_arrExpirationDateTypes = null;
        String[] l_arrHandlingPossibleMarkets = null;
        String[] l_arrOrderConds = null;
        Date[] l_arrOrderBizDate = null;
        boolean l_isMarginSysFlag = false;
        boolean l_isMarginGenFlag = false;
        Timestamp l_tsPrevBizDate = null;
        int l_intOrderBizDateSize = 0;

        // 1.1 Call validate()
        l_request.validate();

        // 1.2 Call getInstanceFromLoginInfo( )
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // 1.3 Call validateAuthority()
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.EQTYPE_ORDER_EXEC_INQUIRY,
            false);

        // 1.4 Call getInstitutionCode( )
        l_strInstitutionCode = l_administrator.getInstitutionCode();

        // 1.5 Call getProductExecutionInfo()
        l_arrBranchCodeList = l_request.branchCode;
        l_adminOrderExecuteDataManager = new WEB3AdminOrderExecuteDataManager();

        l_productExecutionInfo =
            l_adminOrderExecuteDataManager.getProductExecutionInfo(
                l_strInstitutionCode,
                l_arrBranchCodeList);

        l_isMarginSysFlag = l_productExecutionInfo.marginSysFlag;
        l_isMarginGenFlag = l_productExecutionInfo.marginGenFlag;
        l_listRepaymentDivList = new ArrayList();

        boolean l_blnForcedSettleFlag = false;
        // 1.6 If a margin enforcement company
        if (l_isMarginSysFlag || l_isMarginGenFlag)
        {
            // 1.6.1 Create a repayment list
            if (l_isMarginSysFlag && l_isMarginGenFlag)
            {
                l_listRepaymentDivList.add(WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS);
                l_listRepaymentDivList.add(WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN);
            } else if (l_isMarginSysFlag)
            {
                l_listRepaymentDivList.add(WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS);
            } else
            {
                l_listRepaymentDivList.add(WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN);
            }

            // get�،����( )
            // �،���ЃI�u�W�F�N�g���擾����B
            Institution l_institution = l_administrator.getInstitution();

            // is�M�p�������ώ��{( )
            // �M�p�������ώ��{��Ђł��邩�𔻒肷��B
            l_blnForcedSettleFlag =
                ((WEB3GentradeInstitution)l_institution).isForcedSettleOrder();
        }
        l_arrRepaymentDivList = new String[l_listRepaymentDivList.size()];
        l_listRepaymentDivList.toArray(l_arrRepaymentDivList);

        // 1.7 Call createTradingProductList()
        l_arrAdminORTradingProductUnit = this.createTradingProductList(l_productExecutionInfo);

        // 1.8 Call getHandlingPossibleMarket()
        l_arrHandlingPossibleMarkets =
            this.getHandlingPossibleMarket(
                l_strInstitutionCode,
                ProductTypeEnum.EQUITY);

        // 1.9 Create AarryList
        l_listOrderBizDate = new ArrayList();

        // 1.10 Add the previous bizDate of systemTimeStamp and the systemTimeStamp to ArrayList
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
        l_gentradeBizDate = new WEB3GentradeBizDate(new Timestamp(l_datBizDate.getTime()));
        l_tsPrevBizDate = l_gentradeBizDate.roll(-1);
        l_listOrderBizDate.add(WEB3DateUtility.toDay(l_tsPrevBizDate));
        l_listOrderBizDate.add(WEB3DateUtility.toDay(l_datBizDate));
        l_listOrderBizDate.add(WEB3DateUtility.toDay(l_gentradeBizDate.roll(1)));

        // 1.12 Create WEB3GentradeHandlingOrderCond object
        l_gentradeHandlingOrderCond =
            new WEB3GentradeHandlingOrderCond(
                l_strInstitutionCode,
                ProductTypeEnum.EQUITY,
                WEB3FuturesOptionDivDef.DEFAULT,
                WEB3MarginTradingDivDef.DEFAULT);

        // 1.13 Call getHandlingPriceCond()
        l_arrHandlingPriceConds = l_gentradeHandlingOrderCond.getHandlingPriceCond();

        // 1.14 Call getHandlingPossibleExecCond()
        l_arrExecConds = l_gentradeHandlingOrderCond.getHandlingPossibleExecCond();

        // 1.15 Call getHandlingPossibleExpirationDateType()
        l_arrExpirationDateTypes =
            l_gentradeHandlingOrderCond.getHandlingPossibleExpirationDateType();

        // 1.16 Call getHandlingPossibleOrderCond()
        l_arrOrderConds = l_gentradeHandlingOrderCond.getHandlingPossibleOrderCond();

        // 1.17 Call createResponse()
        l_response = (WEB3AdminOREquityOrderExecutionRefInputResponse) l_request.createResponse();

        // 1.18 Property Set
        l_intOrderBizDateSize = l_listOrderBizDate.size();
        l_arrOrderBizDate = new Date[l_intOrderBizDateSize];
        l_listOrderBizDate.toArray(l_arrOrderBizDate);
        l_response.orderBizDateList = l_arrOrderBizDate;
        l_response.marketCodeList = l_arrHandlingPossibleMarkets;
        l_response.repaymentList = l_arrRepaymentDivList;
        l_response.priceCondList = l_arrHandlingPriceConds;
        l_response.execCondList = l_arrExecConds;
        l_response.expirationDateTypeList = l_arrExpirationDateTypes;
        l_response.orderCondTypeList = l_arrOrderConds;
        l_response.orderRootList = new String[] {
            WEB3OrderRootDivDef.CALLCENTER,
            WEB3OrderRootDivDef.PC,
            WEB3OrderRootDivDef.SLINGSHOT,
            WEB3OrderRootDivDef.I_MODE,
            WEB3OrderRootDivDef.VODAFONE,
            WEB3OrderRootDivDef.AU,
            WEB3OrderRootDivDef.HOST,
            WEB3OrderRootDivDef.RICH_CLIENT,
            WEB3OrderRootDivDef.IVR,
            WEB3OrderRootDivDef.FORCED_SETTLE
        };
        l_response.tradingProductList = l_arrAdminORTradingProductUnit;

        // �������ώ��{�t���O�@@���@@�M�p���{���(*1)�̏ꍇ��is�M�p�������ώ��{()�̖߂�l���Z�b�g�B
        // �ȊO�Afalse���Z�b�g�B
        if (l_isMarginSysFlag || l_isMarginGenFlag)
        {
            l_response.forcedSettleEnforcementFlag = l_blnForcedSettleFlag;
        }
        else
        {
            l_response.forcedSettleEnforcementFlag = false;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�Ɖ���)<BR>
     * <BR>
     * �����������Ɖ�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��Ҋ����������Ɖ�T�[�r�X)get�Ɖ��ʁv<BR>
     * �Q��<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * <BR>
     * �Ǘ��ҁE�����������Ɖ�N�G�X�g�N���X<BR>
     * <BR>
     * @@return WEB3AdminOREquityOrderExecutionRefReferenceResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws NotFoundException NotFoundException
     * @@throws DataQueryException DataQueryException
     * @@throws DataNetworkException DataNetworkException
     * @@throws IllegalStateException IllegalStateException
     * @@roseuid 41A5C08F016F
     */
    protected WEB3AdminOREquityOrderExecutionRefReferenceResponse
    getReferenceScreen(WEB3AdminOREquityOrderExecutionRefReferenceRequest l_request)
        throws
            WEB3BaseException,
            NotFoundException,
            DataQueryException,
            DataNetworkException,
            IllegalStateException
    {
        final String STR_METHOD_NAME =
            "getReferenceScreen(WEB3AdminOREquityOrderExecutionRefReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3Administrator l_administrator = null;
        WEB3AdminOrderExecuteDataManager l_adminOrderExecuteDataManager = null;
        WEB3AdminOREquityOrderExecutionRefReferenceResponse l_response = null;
        WEB3GentradeInstitution l_gentradeInstitution = null;
        Institution l_institution = null;
        WEB3AdminOROrderExecutionSortKeyUnit[] l_arrSortKeys = null;
        WEB3AdminOREquityOrderExecutionRefUnit[] l_arrOrderExecutionRefUnit = null;
        WEB3AdminORDetailDispInfoUnit[] l_arrAdminORDetailDispInfoUnit = null;
        EqtypeOrderUnitParams[] l_arrEqtypeOrderUnitParams = null;
        EqTypeOrderUnit[] l_arrOrderUnits = null;
        // EqtypeOrderUnitParams[] l_arrEqtypeOrderUnit = null;
        String[] l_arrStrBranchCode = null;
        String[] l_arrStrBranchCodeList = null;
        String[] l_arrStrQueryDataContainer = null;
        String[] l_arrStrQueryCondDataContainer = null;
        String l_strInstitutionCode = null;
        String l_strProductCode = null;
        String l_strMarketCode = null;
        String l_strTaxType = null;
        String l_strRepaymentDiv = null;
        String l_strPriceCondType = null;
        String l_strexecCondType = null;
        String l_strQueryString = null;
        String l_strSortCond = null;
        String l_strQueryCond = null;
        String l_strCommonQueryCond = null;
        String l_strFuturesOptionDiv = null;
        String l_strCarryOverEndType = null;
        String l_strOrderCondType = null;
        ArrayList l_listPageDisp = new ArrayList();
        int l_intCommonQueryDataContainerlng = 0;
        int l_intEqtypeOrderUnitParamsLen = 0;
        int l_intQueryDataContainerlng = 0;
        int l_intSizeQueryLength = 0;
        // int l_intPageIndex = 0;
        // int l_intPageSize = 0;
        // WEB3PageIndexInfo l_pageIndexInfo = null;

        // 1.1 Call validate()
        l_request.validate();

        // 1.2 Call getInstanceFromLoginInfo()
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // 1.3 Call validateAuthority()
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.EQTYPE_ORDER_EXEC_INQUIRY,
            false);

        // 1.4 Call validateBranchPermission()
        l_arrStrBranchCode = l_request.branchCode;
        l_administrator.validateBranchPermission(l_arrStrBranchCode);

        // 1.5 Call getInstitutionCode()
        l_strInstitutionCode = l_administrator.getInstitutionCode();

        // 1.6 Call createCommonQueryString()
        l_adminOrderExecuteDataManager = new WEB3AdminOrderExecuteDataManager();
        l_strCommonQueryCond =
        l_adminOrderExecuteDataManager.createCommonQueryString(
                l_strInstitutionCode,
                (WEB3AdminOROrderExecutionRefCommonRequest) l_request);

        l_strProductCode = l_request.productCode;
        l_strMarketCode = l_request.marketCode;
        l_strTaxType = l_request.taxType;
        l_strRepaymentDiv = l_request.repaymentDiv;
        l_strPriceCondType = l_request.priceCondType;
        l_strexecCondType = l_request.execCondType;
        l_strOrderCondType = l_request.orderCondType;
        boolean l_blnForcedSettleFlag = l_request.forcedSettleFlag;
        // 1.7 Call createQueryString()
        l_strQueryString =
            this.createQueryString(
                l_strProductCode,
                l_strMarketCode,
                l_strTaxType,
                l_strRepaymentDiv,
                l_strPriceCondType,
                l_strexecCondType,
                l_strOrderCondType,
                l_blnForcedSettleFlag);

        // 1.8 Call createCommonQueryDataContainer()
        l_arrStrBranchCodeList =
        l_adminOrderExecuteDataManager.createCommonQueryDataContainer(
                l_strInstitutionCode,
                (WEB3AdminOROrderExecutionRefCommonRequest) l_request);

        // 1.9 Call createQueryDataContainer()
        l_arrStrQueryDataContainer =
            this.createQueryDataContainer(
                l_strInstitutionCode,
                l_strProductCode,
                l_strMarketCode,
                l_strTaxType,
                l_strRepaymentDiv,
                l_strPriceCondType,
                l_strexecCondType,
                l_strOrderCondType);

        // 1.10 Call createSortCond()
        l_arrSortKeys = l_request.sortKeys;
        l_strSortCond = createSortCond(l_arrSortKeys);

        // 1.11 Call getOrderUnits()
        l_strQueryCond = l_strCommonQueryCond + l_strQueryString;
        l_intCommonQueryDataContainerlng = l_arrStrBranchCodeList.length;
        l_intQueryDataContainerlng = l_arrStrQueryDataContainer.length;
        l_intSizeQueryLength = l_intCommonQueryDataContainerlng + l_intQueryDataContainerlng;
        l_arrStrQueryCondDataContainer = new String[l_intSizeQueryLength];
        System.arraycopy(l_arrStrBranchCodeList, 0, l_arrStrQueryCondDataContainer, 0,
            l_intCommonQueryDataContainerlng);
        System.arraycopy(l_arrStrQueryDataContainer, 0, l_arrStrQueryCondDataContainer,
            l_intCommonQueryDataContainerlng, l_intQueryDataContainerlng);
        
        // get list page
        ListPage l_lisRecords =
            getOrderUnits(
                    l_strQueryCond,
                    l_arrStrQueryCondDataContainer,
                    l_strSortCond,
                    Integer.parseInt(l_request.pageSize),
                    Integer.parseInt(l_request.pageIndex) - 1);
        
        if (!l_lisRecords.isEmpty())
        {
            l_intEqtypeOrderUnitParamsLen = l_lisRecords.size();
            l_arrEqtypeOrderUnitParams = new EqtypeOrderUnitParams[l_intEqtypeOrderUnitParamsLen];
            l_lisRecords.toArray(l_arrEqtypeOrderUnitParams);
        }
        
        // 1.13 If l_arrEqtypeOrderUnitParams != null
        if (l_arrEqtypeOrderUnitParams != null)
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            EqTypeOrderManager l_equityOrderManager =
                (EqTypeOrderManager)l_tradingModule.getOrderManager();
            for (int i = 0; i < l_intEqtypeOrderUnitParamsLen; i++)
            {
                EqTypeOrderUnit l_eqTypeOrderUnit = (EqTypeOrderUnit)
                l_equityOrderManager.toOrderUnit(l_arrEqtypeOrderUnitParams[i]);
                l_listPageDisp.add(l_eqTypeOrderUnit);
            }
            l_arrOrderUnits = new EqTypeOrderUnit[l_listPageDisp.size()];
            l_listPageDisp.toArray(l_arrOrderUnits);

            // 1.13.2 Call createEquityOrderExecutionRefReferenceUnitList()
            l_arrOrderExecutionRefUnit =
                createEquityOrderExecutionRefReferenceUnitList(l_arrOrderUnits);

            // 1.13.3 Call createExecuteDetailsInfoList()
            l_arrAdminORDetailDispInfoUnit =
            l_adminOrderExecuteDataManager.createExecuteDetailsInfoList(l_arrOrderExecutionRefUnit);
        }

        // 1.14 Call getInstitution()
        l_institution = l_administrator.getInstitution();

        // 1.15 Call getCarryoverEndType()
        l_strFuturesOptionDiv = WEB3FuturesOptionDivDef.DEFAULT;
        l_gentradeInstitution = (WEB3GentradeInstitution) l_institution;
        l_strCarryOverEndType =
            l_gentradeInstitution.getCarryoverEndType(ProductTypeEnum.EQUITY, l_strFuturesOptionDiv);

        // 1.16 Call createResponse()
        l_response =
            (WEB3AdminOREquityOrderExecutionRefReferenceResponse) l_request.createResponse();

        // 1.17 Property set
        if (l_arrEqtypeOrderUnitParams == null)
        {
            l_response.carryoverEndType = l_strCarryOverEndType;
            l_response.totalPages = "0";
            l_response.totalRecords = "0";
            l_response.pageIndex = "0";
            l_response.detailDispInfoList = null;
            l_response.equityOrderExecutionRefList = null;
        } else
        {
            l_response.carryoverEndType = l_strCarryOverEndType;
            l_response.totalPages = WEB3StringTypeUtility.formatNumber
                (l_lisRecords.totalPages());
            l_response.totalRecords = WEB3StringTypeUtility.formatNumber
                (l_lisRecords.totalSize());

            l_response.pageIndex = l_request.pageIndex;
            l_response.detailDispInfoList = l_arrAdminORDetailDispInfoUnit;
            l_response.equityOrderExecutionRefList = l_arrOrderExecutionRefUnit;
        }
        
        log.debug(STR_METHOD_NAME + " : TotalRecords[" + l_response.totalRecords + "]");
        log.debug(STR_METHOD_NAME + " : TotalPages[" + l_response.totalPages + "]");
        log.debug(STR_METHOD_NAME + " : PageIndex[" + l_response.pageIndex + "]");

        // 1.18 Return
        log.exiting(STR_METHOD_NAME);
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
     * �Q�j���������̏��i�敪�Ǝ���敪�̑g�ݍ��킹���쐬����B<BR>
     * �@@�����Ώۂ̎���敪(*1)�ɂ��āA�ȉ��̏������J��Ԃ��B<BR>
     * �@@�Q�|�P�j�戵���i�C���X�^���X�𐶐�����B<BR>
     * �@@�Q�|�Q�j���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@���i�敪 = "��������"<BR>
     * �@@�@@����敪 = �����Ώۂ̎���敪<BR>
     * �@@�Q�|�R�j��������ArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B<BR>
     * <BR>
     * �@@(*1)���������̏����Ώێ���敪<BR>
     * �@@�@@�E"�������t����"<BR>
     * �@@�@@�E"�������t����"<BR>
     * �@@�@@�E"����O����"<BR>
     * <BR>
     * �R�j�p�����[�^.���i���{���.���x�M�p���{�t���O == true�܂���<BR>
     * �@@�p�����[�^.���i���{���.��ʐM�p���{�t���O == true�̏ꍇ�A<BR>
     * �@@�M�p����̏��i�敪�Ǝ���敪�̑g�ݍ��킹���쐬����B<BR>
     * �@@�����Ώۂ̎���敪(*2)�ɂ��āA�ȉ��̏������J��Ԃ��B<BR>
     * �@@�R�|�P�j�戵���i�C���X�^���X�𐶐�����B<BR>
     * �@@�R�|�Q�j���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@���i�敪 = "�M�p���"<BR>
     * �@@�@@����敪 = �����Ώۂ̎���敪<BR>
     * �@@�R�|�R�j��������ArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B<BR>
     * <BR>
     * �@@(*2)�M�p����̏����Ώێ���敪<BR>
     * �@@�@@�E"�V�K��������"<BR>
     * �@@�@@�E"�V�K��������"<BR>
     * �@@�@@�E"�����ԍϒ���"<BR>
     * �@@�@@�E"�����ԍϒ���"<BR>
     * �@@�@@�E"��������"<BR>
     * �@@�@@�E"���n����"<BR>
     * <BR>
     * �S�j�p�����[�^.���i���{���.�~�j�����{�t���O == true�̏ꍇ�A<BR>
     * �@@�����~�j�����̏��i�敪�Ǝ���敪�̑g�ݍ��킹���쐬����B<BR>
     * �@@�����Ώۂ̎���敪(*3)�ɂ��āA�ȉ��̏������J��Ԃ��B<BR>
     * �@@�S�|�P�j�戵���i�C���X�^���X�𐶐�����B<BR>
     * �@@�S�|�Q�j���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@���i�敪 = "�����~�j����"<BR>
     * �@@�@@����敪 = �����Ώۂ̎���敪<BR>
     * �@@�S�|�R�j��������ArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B<BR>
     * <BR>
     * �@@(*3)�����~�j�����̏����Ώێ���敪<BR>
     * �@@�@@�E"�~�j�����t����"<BR>
     * �@@�@@�E"�~�j�����t����"<BR>
     * <BR>
     * �T�j��������ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * createTradingProductList<BR>
     * <BR>
     * Make a combination of a handling possible product and a handling possible
     * trading by the argument, l_productExecutionInfo, and return it.<BR>
     * <BR>
     * 1)Create ArrayList<BR>
     * <BR>
     * 2)Create a combination of productDiv and tradingDiv about Def.EQUITY<BR>
     * �@@Loop the following process for tradingDiv(*1) to be processed<BR>
     * �@@2-1)Create a WEB3AdminORTradingProductUnit instance<BR>
     * �@@2-2)Set the following properties into the created instance.<BR>
     * �@@�@@productDiv = Def.EQUITY<BR>
     * �@@�@@tradingDiv = tradingDiv to be processed<BR>
     * �@@2-3)Add the instance set into 'Property set' into the created ArrayList.<BR>
     * <BR>
     * �@@(*1)The tradingDiv about equity<BR>
     * �@@�@@�EDef.EQUITY_BUY<BR>
     * �@@�@@�EDef.EQUITY_SELL<BR>
     * �@@�@@�EDef.SALES_OUTSIDE_MARKET<BR>
     * <BR>
     * 3)If l_productExecutionInfo.marginSysFlag == true, or<BR>
     * �@@l_productExecutionInfo.marginGenFlag == true,<BR>
     * �@@Create a combination of productDiv and tradingDiv of Def.MARGIN<BR>
     * �@@Loop the following process for tradingDiv(*2) to be processed<BR>
     * �@@3-1)Create a WEB3AdminORTradingProductUnit instance<BR>
     * �@@3-2)Set the following properties into the created instance.<BR>
     * �@@�@@productDiv = Def.MARGIN<BR>
     * �@@�@@tradingDiv = tradingDiv to be processed<BR>
     * �@@3-3)Add the instance set into 'Property set' into the created ArrayList.<BR>
     * <BR>
     * �@@(*2)The tradingDiv about margin<BR>
     * �@@�@@�EDef.EQUITY_BUY<BR>
     * �@@�@@�EDef.EQUITY_SELL<BR>
     * �@@�@@�EDef.CLOSE_MARGIN_LONG<BR>
     * �@@�@@�EDef.CLOSE_MARGIN_SHORT<BR>
     * �@@�@@�EDef.SWAP_MARGIN_LONG<BR>
     * �@@�@@�EDef.SWAP_MARGIN_SHORT<BR>
     * <BR>
     * 4)If l_productExecutionInfo.miniFlag == true,<BR>
     * �@@Create a combination of productDiv and tradingDiv of Def.MINI_STOCK<BR>
     * �@@Loop the following process for tradingDiv(*3) to be processed<BR>
     * �@@4-1)Create a WEB3AdminORTradingProductUnit instance<BR>
     * �@@4-2)Set the following properties into the created instance.<BR>
     * �@@�@@productDiv = Def.MINI_STOCK<BR>
     * �@@�@@tradingDiv = tradingDiv to be processed<BR>
     * �@@4-3)Add the instance set into 'Property set' into the created ArrayList.<BR>
     * <BR>
     * �@@(*3)The tradingDiv about mini stock<BR>
     * �@@�@@�EDef.MINI_STOCK_BUY<BR>
     * �@@�@@�EDef.MINI_STOCK_SELL<BR>
     * <BR>
     * 5)Return return values of the created ArrayList.toArray()<BR>
     * <BR>
     * @@param l_productExecutionInfo - (���i���{���)<BR>
     * <BR>
     * ���i���{���I�u�W�F�N�g<BR>
     * <BR>
     * l_productExecutionInfo<BR>
     * <BR>
     * @@return WEB3AdminORTradingProductUnit[]
     * @@roseuid 41AC616200FA
     */
    protected WEB3AdminORTradingProductUnit[]
        createTradingProductList(WEB3AdminProductExecutionInfo l_productExecutionInfo)
    {
        final String STR_METHOD_NAME = "createTradingProductList(WEB3ProductExecutionInfo)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminORTradingProductUnit l_adminORTradingProductUnit = null;
        WEB3AdminORTradingProductUnit[] l_arrAdminORTradingProductUnit = null;
        ArrayList l_listAdminORTradingProductUnit = null;
        boolean l_isMarginSysFlag = false;
        boolean l_isMarginGenFlag = false;
        boolean l_isMiniFlag = false;
        int l_intAdminORTradingProductUnitLength = 0;

        // 1) Create ArrayList
        l_listAdminORTradingProductUnit = new ArrayList();

        // 2) Create a combination of productDiv and tradingDiv about Def.EQUITY
        l_adminORTradingProductUnit = new WEB3AdminORTradingProductUnit();
        l_adminORTradingProductUnit.productDiv = WEB3AdminProductDivDef.EQUITY;
        l_adminORTradingProductUnit.tradingType = String.valueOf(OrderTypeEnum.EQUITY_BUY.intValue());
        l_listAdminORTradingProductUnit.add(l_adminORTradingProductUnit);
        l_adminORTradingProductUnit = new WEB3AdminORTradingProductUnit();
        l_adminORTradingProductUnit.productDiv = WEB3AdminProductDivDef.EQUITY;
        l_adminORTradingProductUnit.tradingType = String.valueOf(OrderTypeEnum.EQUITY_SELL.intValue());
        l_listAdminORTradingProductUnit.add(l_adminORTradingProductUnit);
        l_adminORTradingProductUnit = new WEB3AdminORTradingProductUnit();
        l_adminORTradingProductUnit.productDiv = WEB3AdminProductDivDef.EQUITY;
        l_adminORTradingProductUnit.tradingType = WEB3AdminTradingTypeDef.SALES_OUTSIDE_MARKET;
        l_listAdminORTradingProductUnit.add(l_adminORTradingProductUnit);

        l_isMarginSysFlag = l_productExecutionInfo.marginSysFlag;
        l_isMarginGenFlag = l_productExecutionInfo.marginGenFlag;
        l_isMiniFlag = l_productExecutionInfo.miniFlag;

        /* 3) If l_productExecutionInfo.marginSysFlag == true or
            l_productExecutionInfo.marginGenFlag == true */
        if (l_isMarginSysFlag || l_isMarginGenFlag)
        {
            l_adminORTradingProductUnit = new WEB3AdminORTradingProductUnit();
            l_adminORTradingProductUnit.productDiv = WEB3AdminProductDivDef.MARGIN;
            l_adminORTradingProductUnit.tradingType = String.valueOf(OrderTypeEnum.MARGIN_LONG.intValue());
            l_listAdminORTradingProductUnit.add(l_adminORTradingProductUnit);
            l_adminORTradingProductUnit = new WEB3AdminORTradingProductUnit();
            l_adminORTradingProductUnit.productDiv = WEB3AdminProductDivDef.MARGIN;
            l_adminORTradingProductUnit.tradingType = String.valueOf(OrderTypeEnum.MARGIN_SHORT.intValue());
            l_listAdminORTradingProductUnit.add(l_adminORTradingProductUnit);
            l_adminORTradingProductUnit = new WEB3AdminORTradingProductUnit();
            l_adminORTradingProductUnit.productDiv = WEB3AdminProductDivDef.MARGIN;
            l_adminORTradingProductUnit.tradingType = String.valueOf(OrderTypeEnum.CLOSE_MARGIN_LONG.intValue());
            l_listAdminORTradingProductUnit.add(l_adminORTradingProductUnit);
            l_adminORTradingProductUnit = new WEB3AdminORTradingProductUnit();
            l_adminORTradingProductUnit.productDiv = WEB3AdminProductDivDef.MARGIN;
            l_adminORTradingProductUnit.tradingType = String.valueOf(OrderTypeEnum.CLOSE_MARGIN_SHORT.intValue());
            l_listAdminORTradingProductUnit.add(l_adminORTradingProductUnit);
            l_adminORTradingProductUnit = new WEB3AdminORTradingProductUnit();
            l_adminORTradingProductUnit.productDiv = WEB3AdminProductDivDef.MARGIN;
            l_adminORTradingProductUnit.tradingType = String.valueOf(OrderTypeEnum.SWAP_MARGIN_LONG.intValue());
            l_listAdminORTradingProductUnit.add(l_adminORTradingProductUnit);
            l_adminORTradingProductUnit = new WEB3AdminORTradingProductUnit();
            l_adminORTradingProductUnit.productDiv = WEB3AdminProductDivDef.MARGIN;
            l_adminORTradingProductUnit.tradingType = String.valueOf(OrderTypeEnum.SWAP_MARGIN_SHORT.intValue());
            l_listAdminORTradingProductUnit.add(l_adminORTradingProductUnit);
        }

        // 4)If l_productExecutionInfo.miniFlag == true
        if (l_isMiniFlag)
        {
            l_adminORTradingProductUnit = new WEB3AdminORTradingProductUnit();
            l_adminORTradingProductUnit.productDiv = WEB3AdminProductDivDef.MINI_STOCK;
            l_adminORTradingProductUnit.tradingType = String.valueOf(OrderTypeEnum.MINI_STOCK_BUY.intValue());
            l_listAdminORTradingProductUnit.add(l_adminORTradingProductUnit);
            l_adminORTradingProductUnit = new WEB3AdminORTradingProductUnit();
            l_adminORTradingProductUnit.productDiv = WEB3AdminProductDivDef.MINI_STOCK;
            l_adminORTradingProductUnit.tradingType = String.valueOf(OrderTypeEnum.MINI_STOCK_SELL.intValue());
            l_listAdminORTradingProductUnit.add(l_adminORTradingProductUnit);
        }

        l_intAdminORTradingProductUnitLength = l_listAdminORTradingProductUnit.size();
        l_arrAdminORTradingProductUnit =
            new WEB3AdminORTradingProductUnit[l_intAdminORTradingProductUnitLength];

        l_listAdminORTradingProductUnit.toArray(l_arrAdminORTradingProductUnit);

        // 5)Return return values of the created ArrayList.toArray()
        log.exiting(STR_METHOD_NAME);
        return l_arrAdminORTradingProductUnit;
    }

    /**
     * (create��������������)<BR>
     * <BR>
     * ����������������쐬����B<BR>
     * <BR>
     * �P�j��������������C���X�^���X(�FString)�𐶐�����B<BR>
     * <BR>
     * �Q�j�p�����[�^.�����R�[�h != null�̏ꍇ�A<BR>
     * �@@����ID����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and product_id = ? "<BR>
     * <BR>
     * �R�j�p�����[�^.�s��R�[�h != null�̏ꍇ�A<BR>
     * �@@�s��ID����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and market_id = ? "<BR>
     * <BR>
     * �S�j�p�����[�^.�����敪 != null�̏ꍇ�A<BR>
     * �@@�ŋ敪����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and (swap_tax_type = ? or tax_type = ?)" <BR>
     * <BR>
     * �T�j�p�����[�^.�ٍϋ敪 != null�̏ꍇ�A<BR>
     * �@@�ٍϋ敪����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and repayment_type = ? "<BR>
     * <BR>
     * �U�j�p�����[�^.�l�i���� != null�̏ꍇ�A<BR>
     * �@@�l�i��������������������ɒǉ�����B<BR>
     *      <BR>
     * �@@�������������� += "and price_condition_type = ? "<BR>
     * <BR>
     * �V�j�p�����[�^.���s���� != null�̏ꍇ�A<BR>
     * �@@���s��������������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and execution_condition_type = ? "<BR>
     * <BR>
     * �W�j�p�����[�^.���������敪 != null�̏ꍇ�A<BR>  
     * <BR>
     * �@@�������������� += "and nvl(org_order_condition_type,order_condition_type) = ? "<BR>  
     * <BR>
     * �@@�����������P�ʃe�[�u��.�����������ɒl���ݒ肳��Ă���ꍇ�́A<BR>  
     * �@@�@@�@@���������������ƂɌ�������  <BR>
     * �@@�@@�����������ɒl���ݒ肳��Ă��Ȃ��ꍇ�́A  <BR>
     * �@@�@@�@@���������P�ʃe�[�u��.�������������ƂɌ������� <BR>
     * <BR>
     * �X�j�p�����[�^.�������ϒ����t���O == true�̏ꍇ�A<BR>
     * �@@�������ϒ����ɍi���������������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and forced_settle_reason_type is not null "<BR>
     * <BR>
     * �P�O�j�쐬�������������������ԋp����B<BR>
     * <BR>
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * <BR>
     * �����R�[�h<BR>
     * <BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * <BR>
     * �s��R�[�h<BR>
     * <BR>
     * @@param l_taxType - (�����敪)<BR>
     * <BR>
     * �����敪<BR>
     * <BR>
     * @@param l_repaymentDiv - (�ٍϋ敪)<BR>
     * <BR>
     * �ٍϋ敪<BR>
     * <BR>
     * @@param l_priceCondType - (�l�i����)<BR>
     * <BR>
     * �l�i����<BR>
     * <BR>
     * @@param l_execCondType - (���s����)<BR>
     * <BR>
     * ���s����<BR>
     * <BR>
     * @@param l_strOrderCondType - (���������敪)<BR>
     * <BR>
     * ���������敪<BR>
     * <BR>
     * @@param l_blnForcedSettleFlag - (�������ϒ����t���O)<BR>
     * <BR>
     * �������ϒ����t���O<BR>
     * <BR>
     * @@return java.lang.String
     * @@roseuid 41A5D5D0031D
     */
    protected String createQueryString(
        String l_strProductCode,
        String l_strMarketCode,
        String l_taxType,
        String l_repaymentDiv,
        String l_priceCondType,
        String l_execCondType,
        String l_strOrderCondType,
        boolean l_blnForcedSettleFlag)
    {
        final String STR_METHOD_NAME =
            "createQueryString(String, String, String, String, String, String, String, boolean)";
        log.entering(STR_METHOD_NAME);

        StringBuffer l_sbQueryString = new StringBuffer();
        String l_strQueryCond = null;

        // Checks productCode is not null
        if (l_strProductCode != null)
        {
            l_sbQueryString.append(" and product_id = ? ");
        }

        // Checks l_strMarketCode is not null
        if (l_strMarketCode != null)
        {
            l_sbQueryString.append(" and market_id = ? ");
        }

        // Checks l_taxType is not null
        if (l_taxType != null)
        {
            l_sbQueryString.append(" and (swap_tax_type = ? or tax_type = ?) ");
        }

        // Checks l_repaymentDiv is not null
        if (l_repaymentDiv != null)
        {
            l_sbQueryString.append(" and repayment_type = ? ");
        }

        // Checks l_priceCondType is not null
        if (l_priceCondType != null)
        {
            l_sbQueryString.append(" and price_condition_type = ? ");
        }

        // Checks l_execCondType is not null
        if (l_execCondType != null)
        {
            l_sbQueryString.append(" and execution_condition_type = ? ");
        }
        
        //Checks l_orderConditionDiv is not null
        if (l_strOrderCondType != null)
        {
            l_sbQueryString.append(" and nvl(org_order_condition_type,order_condition_type) = ? ");
        }

        // �X�j�p�����[�^.�������ϒ����t���O == true�̏ꍇ�A
        if (l_blnForcedSettleFlag)
        {
            // �������ϒ����ɍi���������������������ɒǉ�����B
            // �������������� += "and forced_settle_reason_type is not null " 
            l_sbQueryString.append(" and forced_settle_reason_type is not null ");
        }

        l_strQueryCond = l_sbQueryString.toString();
        log.exiting(STR_METHOD_NAME);
        return l_strQueryCond;
    }

    /**
     * (create���������f�[�^�R���e�i)<BR>
     * <BR>
     * ���������f�[�^�R���e�i���쐬����B<BR>
     * <BR>
     * �P�jArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�p�����[�^.�����R�[�h != null�̏ꍇ�A<BR>
     * �@@����ID�𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@������ID�́A�g�������v���_�N�g�}�l�[�W��.<BR>
     * �@@getProduct()�ɂĎ擾������������.����ID���Z�b�g�B<BR>
     * <BR>
     * �@@[getProduct()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�،���ЁF�@@�g���A�J�E���g�}�l�[�W��.<BR>
     * �@@�@@�@@getInstitution(�p�����[�^.�،���ЃR�[�h)�ɂĎ擾�����،����<BR>
     * �@@�@@�����R�[�h�F�@@�p�����[�^.�����R�[�h<BR>
     * <BR>
     * �R�j�p�����[�^.�s��R�[�h != null�̏ꍇ�A<BR>
     * �@@�s��ID�𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@���s��ID�́A�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��()�ɂ�<BR>
     * �@@�@@�擾�����s��.�s��ID���Z�b�g�B<BR>
     * <BR>
     * �@@�@@[get�s��()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h<BR>
     * �@@�@@�@@�s��R�[�h�F�@@�p�����[�^.�s��R�[�h<BR>
     * <BR>
     * �S�j�p�����[�^.�����敪 != null�̏ꍇ�A<BR> 
     * �@@�ȉ��̐ŋ敪�𐶐�����ArrayList�ɒǉ�����B<BR> 
     * �@@�E�����f�[�^�A�_�v�^.getAP�����敪(�p�����[�^.�����敪)<BR> 
     * �@@�E�����f�[�^�A�_�v�^.getAP�����敪(�p�����[�^.�����敪)<BR>
     * <BR>
     * �T�j�p�����[�^.�ٍϋ敪 != null�̏ꍇ�A<BR>
     * �@@�p�����[�^.�ٍϋ敪�𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �U�j�p�����[�^.�l�i���� != null�̏ꍇ�A<BR>
     * �@@�p�����[�^.�l�i�����𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �V�j�p�����[�^.���s���� != null�̏ꍇ�A<BR>
     * �@@���s�����𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@�����s�����́Athis.get���s����()�ɂĎ擾����B<BR>
     * <BR>
     * �@@[get���s����()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@���s�����F�@@�p�����[�^.���s����<BR>
     * <BR>
     * �W�j�p�����[�^.���������敪 != null�̏ꍇ�A<BR>
     *  �@@�p�����[�^.���������敪�𐶐�����ArrayList�ɒǉ�����B<BR> 
     *  <BR>
     * �X�j��������ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h<BR>
     * <BR>
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * <BR>
     * �����R�[�h<BR>
     * <BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * <BR>
     * �s��R�[�h<BR>
     * <BR>
     * @@param l_taxType - (�����敪)<BR>
     * <BR>
     * �����敪<BR>
     * <BR>
     * @@param l_repaymentDiv - (�ٍϋ敪)<BR>
     * <BR>
     * �ٍϋ敪<BR>
     * <BR>
     * @@param l_priceCondType - (�l�i����)<BR>
     * <BR>
     * �l�i����<BR>
     * <BR>
     * @@param l_execCondType - (���s����)<BR>
     * <BR>
     * ���s����<BR>
     * <BR>
     * @@param l_strOrderCondType - (���������敪)<BR>
     * <BR>
     * ���������敪<BR>
     * <BR>
     * @@return String[]
     * @@throws NotFoundException NotFoundException
     * @@roseuid 41A5D5D0033D
     */
    protected String[] createQueryDataContainer(
        String l_strInstitutionCode,
        String l_strProductCode,
        String l_strMarketCode,
        String l_taxType,
        String l_repaymentDiv,
        String l_priceCondType,
        String l_execCondType,
        String l_strOrderCondType)
        throws NotFoundException
    {
        final String STR_METHOD_NAME =
            "createQueryDataContainer(String, String, String, String, String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        ArrayList l_listQueryData = null;
        EqTypeProduct l_eqTypeProduct = null;
        WEB3GentradeAccountManager l_gentradeAccountManager = null;
        WEB3GentradeFinObjectManager l_gentradeFinObjectManager = null;
        EqTypeExecutionConditionType l_execCondTypeList = null;
        Institution l_institution = null;
        String[] l_arrStrQueryData = null;
        long l_lngProductId = 0L;
        long l_lngMarketId = 0L;

        // 1) Create ArrayList
        l_listQueryData = new ArrayList();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        EqTypeProductManager l_productManager =
            (EqTypeProductManager)l_tradingModule.getProductManager();
        l_gentradeAccountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        l_institution = l_gentradeAccountManager.getInstitution(l_strInstitutionCode);

        // 2) If l_strProductCode is not null
        if (l_strProductCode != null)
        {
            l_eqTypeProduct =
                (EqTypeProduct)l_productManager.getProduct(l_institution, l_strProductCode);
            l_lngProductId = l_eqTypeProduct.getProductId();
            l_listQueryData.add(WEB3StringTypeUtility.formatNumber(l_lngProductId));
        }

        // 3) If  l_strMarketCode is not null
        l_gentradeFinObjectManager =
            (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
        if (l_strMarketCode != null)
        {
            Market l_market = l_gentradeFinObjectManager.getMarket(l_institution, l_strMarketCode);
            l_lngMarketId = l_market.getMarketId();
            l_listQueryData.add(WEB3StringTypeUtility.formatNumber(l_lngMarketId));
        }

        // 4) If l_taxType is not null
        if (l_taxType != null)
        {
            l_listQueryData.add(WEB3EquityDataAdapter.getApTaxType(l_taxType));
            l_listQueryData.add(WEB3EquityDataAdapter.getApTaxType(l_taxType));
        }

        // 5) If l_repaymentDiv is not null
        if (l_repaymentDiv != null)
        {
            l_listQueryData.add(l_repaymentDiv);
        }

        // 6) If l_priceCondType is not null
        if (l_priceCondType != null)
        {
            l_listQueryData.add(l_priceCondType);
        }

        // 7) If l_execCondType is not null
        if (l_execCondType != null)
        {
            l_execCondTypeList = this.getExecCondType(l_execCondType);

            l_listQueryData.add(WEB3StringTypeUtility.formatNumber(l_execCondTypeList.intValue()));
        }
        
        // 8) If l_orderConditionDiv is not null
        if (l_strOrderCondType != null)
        {
            l_listQueryData.add(l_strOrderCondType);
        }
        
        l_arrStrQueryData = new String[l_listQueryData.size()];
        l_arrStrQueryData = (String[]) l_listQueryData.toArray(l_arrStrQueryData);

        // 9) Return the return value of the created ArrayList.toArray()
        log.exiting(STR_METHOD_NAME);
        return l_arrStrQueryData;
    }

    /**
     * (create�\�[�g����)<BR>
     * <BR>
     * �\�[�g�������쐬����B<BR>
     * <BR>
     * �P�j�\�[�g����������(�FString)���쐬����B<BR>
     * <BR>
     * �Q�j�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B<BR>
     * �@@�Q�|�P�j�\�[�g�L�[.�L�[���ڂ�Ή�����񕨗����ɕϊ����A<BR>
     * �@@�@@�쐬�����\�[�g����������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�E�u���X�R�[�h�v�@@���@@���������P��.���XID<BR>
     * �@@�@@�E�u�ڋq�R�[�h�v�@@���@@���������P��.����ID<BR>
     * �@@�@@�E�u���҃R�[�h�iSONAR�j�v�@@���@@���������P��.���҃R�[�h�iSONAR�j<BR>
     * �@@�@@�E�u�����R�[�h�v�@@���@@���������P��.����ID<BR>
     * �@@�@@�E�u�s��R�[�h�v�@@���@@���������P��.�s��ID<BR>
     * �@@�@@�E�u�����敪�v�@@���@@���������P��.�ŋ敪<BR>
     * �@@�@@�E�u����敪�v�@@���@@���������P��.�������<BR>
     * �@@�@@�E�u�������ԁv�@@���@@���������P��.�󒍓���<BR>
     * �@@�@@�E�u�������v�@@���@@���������P��.������<BR>
     * �@@�@@�E�u�ٍϋ敪�v�@@���@@���������P��.�ٍϋ敪<BR>
     * �@@�@@�E�u�l�i�����v�@@���@@���������P��.�l�i����<BR>
     * �@@�@@�E�u���s�����v�@@���@@���������P��.���s����<BR>
     * �@@�@@�E�u���������v�@@���@@���������P��.�����������t<BR>
     * �@@�@@�E�u���������v�@@���@@(*1) <BR>
     * �@@�@@�E�u��n���v�@@���@@���������P��.��n��<BR>
     * <BR>
     * �@@�Q�|�Q�j�\�[�g�L�[.�����^�~���ɑΉ�����擾����<BR>
     * �@@�@@(asc or desc)���\�[�g����������ɒǉ�����B<BR>
     * <BR>
     * �R�j�\�[�g���������ɁA�����P�ʃe�[�u��.�X�V���t�������w��ŕt��<BR>
     * <BR>
     * �S�j�쐬�����\�[�g�����������ԋp����B<BR>
     * <BR>
     * (*1) nvl�i���������P�ʃe�[�u��.����������, ���������P�ʃe�[�u��.���������j��ݒ肷��B<BR>  
     * �@@�@@�@@�����������P�ʃe�[�u��.�����������ɒl���ݒ肳��Ă���ꍇ�́A <BR> 
     * �@@�@@�@@�@@�@@�@@���������������ƂɃ\�[�g����  <BR>
     * �@@�@@�@@�@@�����������ɒl���ݒ肳��Ă��Ȃ��ꍇ�́A  <BR>
     * �@@�@@�@@�@@�@@�@@���������P�ʃe�[�u��.�������������ƂɃ\�[�g���� <BR> 
     * <BR>
     * @@param l_sortKeys - (�\�[�g�L�[)<BR>
     * <BR>
     * �\�[�g�L�[<BR>
     * <BR>
     * @@return java.lang.String
     * @@roseuid 41A5D5D0034C
     */
    protected String createSortCond(WEB3AdminOROrderExecutionSortKeyUnit[] l_sortKeys)
    {
        final String STR_METHOD_NAME = "createSortCond(WEB3AdminOROrderExecutionSortKeyUnit[])";
        log.entering(STR_METHOD_NAME);
        int l_intSortKeysCnt = 0;
        StringBuffer l_sbSortCond = new StringBuffer();
        String l_strSortCond = null;

        // 3) Loop the following process for as many times as the number of the elements of sortKeys
            l_intSortKeysCnt = l_sortKeys.length;
            for (int i = 0; i < l_intSortKeysCnt; i++)
            {
                if (WEB3AdminKeyItemDef.BRANCH_CODE.equals(l_sortKeys[i].keyItem))
                {
                    l_sbSortCond.append("eqtype_order_unit.branch_id ");
                } else if (WEB3AdminKeyItemDef.ACCOUNT_CODE.equals(l_sortKeys[i].keyItem))
                {
                    l_sbSortCond.append("substr(eqtype_order_unit.account_id, 9, 6) ");
                } else if (WEB3AdminKeyItemDef.SONAR_TRADER_CODE.equals(l_sortKeys[i].keyItem))
                {
                    l_sbSortCond.append("eqtype_order_unit.sonar_trader_code ");
                } else if (WEB3AdminKeyItemDef.PRODUCT_CODE.equals(l_sortKeys[i].keyItem))
                {
                    l_sbSortCond.append("eqtype_order_unit.product_id ");
                } else if (WEB3AdminKeyItemDef.MARKET_CODE.equals(l_sortKeys[i].keyItem))
                {
                    l_sbSortCond.append("eqtype_order_unit.market_id ");
                } else if (WEB3AdminKeyItemDef.TAX_TYPE.equals(l_sortKeys[i].keyItem))
                {
                    l_sbSortCond.append("eqtype_order_unit.tax_type ");
                } else if (WEB3AdminKeyItemDef.TRADING_TYPE.equals(l_sortKeys[i].keyItem))
                {
                    l_sbSortCond.append("eqtype_order_unit.order_type ");
                } else if (WEB3AdminKeyItemDef.ORDER_DATE.equals(l_sortKeys[i].keyItem))
                {
                    l_sbSortCond.append("eqtype_order_unit.received_date_time ");
                } else if (WEB3AdminKeyItemDef.ORDER_BIZ_DATE.equals(l_sortKeys[i].keyItem))
                {
                    l_sbSortCond.append("eqtype_order_unit.biz_date ");
                } else if (WEB3AdminKeyItemDef.REPAYMENT_DIV.equals(l_sortKeys[i].keyItem))
                {
                    l_sbSortCond.append("eqtype_order_unit.repayment_type ");
                } else if (WEB3AdminKeyItemDef.PRICE_COND_TYPE.equals(l_sortKeys[i].keyItem))
                {
                    l_sbSortCond.append("eqtype_order_unit.price_condition_type ");
                } else if (WEB3AdminKeyItemDef.EXEC_COND_TYPE.equals(l_sortKeys[i].keyItem))
                {
                    l_sbSortCond.append("eqtype_order_unit.execution_condition_type ");
                } else if (WEB3AdminKeyItemDef.EXPIRATION_DATE.equals(l_sortKeys[i].keyItem))
                {
                    l_sbSortCond.append("eqtype_order_unit.expiration_date ");
                } else if (WEB3AdminKeyItemDef.ORDER_COND_TYPE.equals(l_sortKeys[i].keyItem))
                {
                    l_sbSortCond.append(
                        "nvl(eqtype_order_unit.org_order_condition_type,"+ 
                        " eqtype_order_unit.order_condition_type) ");
                } else
                {
                    l_sbSortCond.append("eqtype_order_unit.delivery_date ");
                }

                // 3-2 Add (asc or desc) to sortCond
                if (l_sortKeys[i].ascDesc.equals(WEB3AscDescDef.DESC))
                {
                    l_sbSortCond.append("desc,");
                } else
                {
                    l_sbSortCond.append("asc,");
                }
            }

            // 4) Add info_order_unit.last_updated_timestamp to the end of sortCond in ascending
        l_sbSortCond.append("last_updated_timestamp asc");
        l_strSortCond = l_sbSortCond.toString();

        // 5) Return the created sortCond
        log.exiting(STR_METHOD_NAME);
        return l_strSortCond;
    }

    /**
     * (get�����P�ʈꗗ)<BR>
     * <BR>
     * �����̏����ɊY�����钍���P�ʂ̈ꗗ��ԋp����B<BR>
     * <BR>
     * �P�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@arg0�F�@@���������P��Row.TYPE<BR>
     * �@@�@@arg1�F�@@�p�����[�^.��������������<BR>
     * �@@�@@arg2�F�@@�p�����[�^.�\�[�g����<BR>
     * �@@�@@arg3�F�@@null<BR>
     * �@@�@@arg4�F�@@�p�����[�^.���������f�[�^�R���e�i<BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �R�j�������ʂ�ԋp����B<BR>
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
     * �@@�@@arg0: l_eqtypeOrderUnitRow.TYPE<BR>
     * �@@�@@arg1: l_strQueryCond<BR>
     * �@@�@@arg2: l_strSortCond<BR>
     * �@@�@@arg3: null<BR>
     * �@@�@@arg4: l_strQueryCondDataContainer<BR>
     * <BR>
     * �@@Return null if there is no search result<BR>
     * <BR>
     * 3)Return the search result<BR>
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
     * @@param pageSize - (Page size)<BR>
     * <BR>
     * Page Size<BR>
     * <BR>
     * pageSize<BR>
     * <BR>
     * @@param pageIndex - (Page index)<BR>
     * <BR>
     * Page Index<BR>
     * <BR>
     * pageIndex<BR>
     * <BR>
     * @@return ListPage
     * @@throws DataQueryException DataQueryException
     * @@throws DataNetworkException DataNetworkException
     * @@throws IllegalStateException IllegalStateException
     * @@roseuid 41A5D7430115
     */
    protected ListPage getOrderUnits(
            String l_strQueryCond,
            String[] l_strQueryCondDataContainer,
            String l_strSortCond,
            int pageSize,
            int pageIndex)
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        final String STR_METHOD_NAME = "getOrderUnits(String,String[],String,int,int)";
        log.entering(STR_METHOD_NAME);
        
        /*
        EqtypeOrderUnitParams[] l_arrEqtypeOrderUnitParams = null;
        ListPage l_lisRecords = null;
        int l_intEqtypeOrderUnitParamsLength = 0;
        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        */

        // �P�jCall QueryProcessor.doFindAllQuery() method
        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        ListPage l_lisRecords =
            l_queryProcessor.doFindAllQuery(
                EqtypeOrderUnitRow.TYPE,
                l_strQueryCond,
                l_strSortCond,
                null,
                l_strQueryCondDataContainer,
                pageSize,
                pageIndex
                );
        //l_intEqtypeOrderUnitParamsLength = l_lisRecords.size();
        log.exiting(STR_METHOD_NAME);
        return  l_lisRecords;
        
    }

    /**
     * (create�����������Ɖ�Unit�ꗗ)<BR>
     * <BR>
     * �����̒����P�ʈꗗ���A�����������Ɖ�Unit�̈ꗗ��<BR>
     * �쐬���A�ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��Ҋ����������Ɖ�T�[�r�X)create�����������Ɖ�Unit�ꗗ�v<BR>
     * �Q��<BR>
     * <BR>
     * @@param l_orderUnits - (�����P�ʈꗗ)<BR>
     * <BR>
     * ���������P�ʂ̔z��<BR>
     * <BR>
     * @@return WEB3AdminOREquityOrderExecutionRefUnit[]
     * @@throws NotFoundException NotFoundException
     * @@throws WEB3BaseException
     * @@roseuid 41B3E5C00346
     */
    protected WEB3AdminOREquityOrderExecutionRefUnit[]
        createEquityOrderExecutionRefReferenceUnitList(EqTypeOrderUnit[] l_orderUnits)
        throws NotFoundException, WEB3BaseException
    {
        final String DATE_FORMAT = "yyyyMMdd";
        final String STR_METHOD_NAME =
            "createEquityOrderExecutionRefReferenceUnitList(EqTypeOrderUnit[])";
        log.entering(STR_METHOD_NAME);

        // 1.1 ArrayList()
        ArrayList l_listOrderExecutionRefUnit = new ArrayList();
        WEB3GentradeAccountManager l_gentradeAccountManager = null;
        WEB3AdminOrderExecuteDataManager l_adminOrderExecuteDataManager = null;
        WEB3AdminOREquityOrderExecutionRefUnit l_adminOREquityOrderExecutionRefUnit = null;
        WEB3AdminOREquityOrderExecutionRefUnit[] l_arrAdminOREquityOrderExecutionRefUnit = null;
        Market l_market = null;
        WEB3GentradeMainAccount l_mainAccount = null;
        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = null;
        EqTypeProduct l_product = null;
        Branch l_branch = null;
        Trader l_trader = null;
        Date l_dtBizDate = null;
        String l_strProductDiv = null;
        String l_strTradingType = null;
        String l_strOrderStateDivPR = null;
        String l_strExecTypeDivPR = null;
        String l_strTaxType = null;
        String l_strBizdate = null;
        String l_strExecCondTypePR = null;
        String l_strExecPrice = null;
        BigDecimal l_bigExecutedAmount = null;
        BigDecimal l_bigExecutedQuantity = null;
        int l_intOrderUnitsCount = 0;
        boolean l_isMarketOrder = false;
        boolean l_isUnexecuted = false;
        boolean l_isFirstOrderUnitIdNull = false;
        long l_lngBranchId = 0;
        long l_lngAccountId = 0;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        l_gentradeAccountManager = (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        WEB3GentradeFinObjectManager l_gentradeFinObjectManager =
            (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
        l_adminOrderExecuteDataManager = new WEB3AdminOrderExecuteDataManager();

        // 1.2 Loop for as many times as the number of the elements of l_orderUnits
        l_intOrderUnitsCount = l_orderUnits.length;

        for (int i = 0; i < l_intOrderUnitsCount; i++)
        {
            l_eqtypeOrderUnitParams = (EqtypeOrderUnitParams) l_orderUnits[i].getDataSourceObject();

            // 1.2.1Call getProduct()
            l_product = (EqTypeProduct) l_orderUnits[i].getProduct();

            // 1.2.2 Call isMarketOrder()
            l_isMarketOrder = l_orderUnits[i].isMarketOrder();

            // 1.2.3 Call getBranchId()
            l_lngBranchId = l_orderUnits[i].getBranchId();

            // 1.2.4 Call getBranch()
            l_branch = l_gentradeAccountManager.getBranch(l_lngBranchId);

            // 1.2.5 Call getAccountId()
            l_lngAccountId = l_orderUnits[i].getAccountId();

            // 1.2.6 Call getMainAccount()
            l_mainAccount =
                (WEB3GentradeMainAccount)l_gentradeAccountManager.getMainAccount(l_lngAccountId);

            // 1.2.7 Call getMarket
            l_market =
            l_gentradeFinObjectManager.getMarket(l_eqtypeOrderUnitParams.market_id.longValue());

            // 1.2.8 Call getProductDiv()
            l_strProductDiv = getProductDiv(l_orderUnits[i]);

            // 1.2.9 Call getTradingType()
            l_strTradingType =
                l_adminOrderExecuteDataManager.getTradingType(l_orderUnits[i].getOrderType());

            // 1.2.10 Call getOrderStateDivPR()
            l_strOrderStateDivPR =
                l_adminOrderExecuteDataManager.getOrderStateDivPR(l_orderUnits[i]);

            // 1.2.11 Call getExecCondTypePR() Closed
            if (!l_strProductDiv.equals(WEB3AdminProductDivDef.MINI_STOCK))
            {
	            l_strExecCondTypePR =
	                getExecCondTypePR(l_eqtypeOrderUnitParams.execution_condition_type);
            }

            // 1.2.12 Call getExecTypeDivPR()
            l_strExecTypeDivPR = l_adminOrderExecuteDataManager.getExecTypeDivPR(l_orderUnits[i]);

            //get�v�w�l�p�L����ԋ敪(�����P�� : EqTypeOrderUnit)
            String l_strWLimitEnableStatusDiv =
                WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_orderUnits[i]);

            // 1.2.13 Call getTaxType()
            l_strTaxType = getTaxType(l_orderUnits[i]);

            // 1.2.14 Call Create WEB3AdminOREquityOrderExecutionRefUnit
            l_adminOREquityOrderExecutionRefUnit = new WEB3AdminOREquityOrderExecutionRefUnit();

            /* 1.2.15 Property Set
                Common part */
            l_adminOREquityOrderExecutionRefUnit.id =
                WEB3StringTypeUtility.formatNumber(l_eqtypeOrderUnitParams.getOrderId());
            l_adminOREquityOrderExecutionRefUnit.branchCode = l_branch.getBranchCode();
            l_adminOREquityOrderExecutionRefUnit.accountCode = l_mainAccount.getDisplayAccountCode();
            //�ڋq���@@�@@�@@�@@�@@�@@�@@���@@getMainAccount()�̖߂�l.get�ڋq�\����()
            l_adminOREquityOrderExecutionRefUnit.accountName = l_mainAccount.getDisplayAccountName();
            l_adminOREquityOrderExecutionRefUnit.sonarTraderCode =
                l_eqtypeOrderUnitParams.getSonarTraderCode();
            l_adminOREquityOrderExecutionRefUnit.productDiv = l_strProductDiv;
            if (l_eqtypeOrderUnitParams.getSonarTradedCode().
                equals(WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET))
            {
                l_adminOREquityOrderExecutionRefUnit.tradingType =
                    WEB3AdminTradingTypeDef.SALES_OUTSIDE_MARKET;
            } else
            {
                l_adminOREquityOrderExecutionRefUnit.tradingType = l_strTradingType;
            }
            l_adminOREquityOrderExecutionRefUnit.orderQuantity =
                WEB3StringTypeUtility.formatNumber(l_eqtypeOrderUnitParams.getQuantity());
            if (l_isMarketOrder)
            {
                l_adminOREquityOrderExecutionRefUnit.orderPriceDiv =
                    WEB3OrderPriceDivDef.MARKET_PRICE;
            } else
            {
                l_adminOREquityOrderExecutionRefUnit.orderPriceDiv =
                    WEB3OrderPriceDivDef.LIMIT_PRICE;
                l_adminOREquityOrderExecutionRefUnit.orderPrice =
                    WEB3StringTypeUtility.formatNumber(l_eqtypeOrderUnitParams.getLimitPrice());
            }
            l_adminOREquityOrderExecutionRefUnit.estimatedPrice =
            WEB3StringTypeUtility.formatNumber(l_eqtypeOrderUnitParams.getEstimatedPrice());
            l_adminOREquityOrderExecutionRefUnit.orderChannel =
                l_eqtypeOrderUnitParams.getOrderChanel();
            l_adminOREquityOrderExecutionRefUnit.orderState = l_strOrderStateDivPR;
            l_adminOREquityOrderExecutionRefUnit.orderDate =
            l_eqtypeOrderUnitParams.getReceivedDateTime();
            l_strBizdate = l_eqtypeOrderUnitParams.getBizDate();
            l_dtBizDate = WEB3DateUtility.getDate(l_strBizdate, DATE_FORMAT);
            l_adminOREquityOrderExecutionRefUnit.orderBizDate = l_dtBizDate;
            l_adminOREquityOrderExecutionRefUnit.execCondType = l_strExecCondTypePR;
            l_isFirstOrderUnitIdNull = l_eqtypeOrderUnitParams.getFirstOrderUnitIdIsNull();
            if (!l_isFirstOrderUnitIdNull)
            {
                l_adminOREquityOrderExecutionRefUnit.expirationDate =
                l_eqtypeOrderUnitParams.getExpirationDate();
            }
            
            if (l_strProductDiv.equals(WEB3AdminProductDivDef.MINI_STOCK))
            {
                l_adminOREquityOrderExecutionRefUnit.orderCondType = null;
            }
            else
            {
	            String l_strOrgOrderCondType = l_eqtypeOrderUnitParams.getOrgOrderConditionType();
	            
                //�����P��.���������� == "�t�w�l"�̏ꍇ
	            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrgOrderCondType))
	            {
                    //���������敪 = �����P��.���������� 
                    l_adminOREquityOrderExecutionRefUnit.orderCondType = l_strOrgOrderCondType;
	                
                    //���������P�� = �����P��.���t�w�l��l 
                    l_adminOREquityOrderExecutionRefUnit.orderCondPrice =
                        WEB3StringTypeUtility.formatNumber(l_eqtypeOrderUnitParams.getOrgStopOrderPrice());
	                
                    //�����������Z�q = �����P��.�������������Z�q
                    l_adminOREquityOrderExecutionRefUnit.condOperator =
                        l_eqtypeOrderUnitParams.getOrgOrderCondOperator();
	            }
                
                //�����P��.���������� == "W�w�l"�̏ꍇ
                else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrgOrderCondType))
	            {
                    //���������敪 = �����P��.���������� 
                    l_adminOREquityOrderExecutionRefUnit.orderCondType = l_strOrgOrderCondType;
                    
                    //���������P�� = �����P��.���t�w�l��l 
                    l_adminOREquityOrderExecutionRefUnit.orderCondPrice =
                        WEB3StringTypeUtility.formatNumber(l_eqtypeOrderUnitParams.getOrgStopOrderPrice());
                    
                    //�����������Z�q = �����P��.�������������Z�q
                    l_adminOREquityOrderExecutionRefUnit.condOperator =
                        l_eqtypeOrderUnitParams.getOrgOrderCondOperator();
                
                    //�v�w�l�p�����P���敪 = get���v�w�l�p�����P���敪(�����P��)�̖߂�l���Z�b�g�B
                    String l_strOrgWLimitOrderPriceDiv =
                        WEB3EquityDataAdapter.getOrgWLimitOrderPriceDiv(l_orderUnits[i]);
                    l_adminOREquityOrderExecutionRefUnit.wLimitOrderPriceDiv = l_strOrgWLimitOrderPriceDiv;
                    
                    //�v�w�l�p�����P�� = get���v�w�l�p�����P���敪(�����P��)���w�l�̏ꍇ�A
                    //get���v�w�l�p�����P��(�����P��)�̖߂�l���Z�b�g�B
                    if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrgWLimitOrderPriceDiv))
                    {
                        String l_strOrgWLimitOrderPrice =
                            WEB3EquityDataAdapter.getOrgWLimitOrderPrice(l_orderUnits[i]);
                        l_adminOREquityOrderExecutionRefUnit.wlimitOrderCondPrice = l_strOrgWLimitOrderPrice;
                    }
                    
                    //W�w�l�p���s���� = get���v�w�l�p���s����(�����P��)�̖߂�l���Z�b�g�B
                    String l_strOrgWLimitExecCondType =
                        WEB3EquityDataAdapter.getOrgWLimitExecCondType(l_orderUnits[i]);
                    l_adminOREquityOrderExecutionRefUnit.wlimitExecCondType = l_strOrgWLimitExecCondType;
	            }
                else 
                {
                    //���������敪 = �����P��.��������
                    String l_strOrderConditionType = 
                        l_eqtypeOrderUnitParams.getOrderConditionType();
                    l_adminOREquityOrderExecutionRefUnit.orderCondType = l_strOrderConditionType;
                        
                    //�����P��.�������� == "�t�w�l"�̏ꍇ
                    if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderConditionType))
                    {
                        //���������P�� = �����P��.�t�w�l��l 
                        l_adminOREquityOrderExecutionRefUnit.orderCondPrice = 
                            WEB3StringTypeUtility.formatNumber(l_eqtypeOrderUnitParams.getStopOrderPrice()); 
                        
                        //�����������Z�q = �����P��.�����������Z�q
                        l_adminOREquityOrderExecutionRefUnit.condOperator = 
                            l_eqtypeOrderUnitParams.getOrderCondOperator();
                    }
                    
                    //�����P��.�������� == "W�w�l"�̏ꍇ
                    else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
                    {
                        //���������P�� = �����P��.�t�w�l��l 
                        l_adminOREquityOrderExecutionRefUnit.orderCondPrice = 
                            WEB3StringTypeUtility.formatNumber(l_eqtypeOrderUnitParams.getStopOrderPrice()); 
                        
                        //�����������Z�q = �����P��.�����������Z�q
                        l_adminOREquityOrderExecutionRefUnit.condOperator = 
                            l_eqtypeOrderUnitParams.getOrderCondOperator();
                        
                        //�v�w�l�p�����P���敪 = get�v�w�l�p�����P���敪(�����P��)�̖߂�l���Z�b�g�B
                        String l_strWLimitOrderPriceDiv =
                            WEB3EquityDataAdapter.getWLimitOrderPriceDiv(l_orderUnits[i]);
                        l_adminOREquityOrderExecutionRefUnit.wLimitOrderPriceDiv = l_strWLimitOrderPriceDiv;
                        
                        //�v�w�l�p�����P�� = get�v�w�l�p�����P���敪(�����P��)���w�l�̏ꍇ�A
                        //get�v�w�l�p�����P��(�����P��)�̖߂�l���Z�b�g�B
                        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strWLimitOrderPriceDiv))
                        {
                            String l_strWLimitOrderPrice =
                                WEB3EquityDataAdapter.getWLimitOrderPrice(l_orderUnits[i]);
                            l_adminOREquityOrderExecutionRefUnit.wlimitOrderCondPrice = l_strWLimitOrderPrice;
                        }
                        
                        //W�w�l�p���s���� = get�v�w�l�p���s����(�����P��)�̖߂�l���Z�b�g�B
                        String l_strWLimitExecCondType =
                            WEB3EquityDataAdapter.getWLimitExecCondType(l_orderUnits[i]);
                        l_adminOREquityOrderExecutionRefUnit.wlimitExecCondType = l_strWLimitExecCondType;
                    }
                }
            }
            
            l_isUnexecuted = l_orderUnits[i].isUnexecuted();
            if (!l_isUnexecuted)
            {
                EqtypeOrderUnitRow l_orderUnitRow =
                    (EqtypeOrderUnitRow)l_orderUnits[i].getDataSourceObject();
                l_adminOREquityOrderExecutionRefUnit.execQuantity =
                    WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getExecutedQuantity());
                l_bigExecutedAmount = new BigDecimal(l_orderUnitRow.getExecutedAmount());
                l_bigExecutedQuantity = new BigDecimal(l_orderUnitRow.getExecutedQuantity());
                double l_dblExecPrice = l_bigExecutedAmount.divide
                    (l_bigExecutedQuantity, BigDecimal.ROUND_HALF_UP).doubleValue();
                l_strExecPrice = WEB3StringTypeUtility.formatNumber(l_dblExecPrice);
                l_adminOREquityOrderExecutionRefUnit.execPrice = l_strExecPrice;
            }
            if (l_eqtypeOrderUnitParams.trader_id != null)
            {
                l_trader = l_gentradeFinObjectManager.getTrader(l_orderUnits[i].getTraderId());
                l_adminOREquityOrderExecutionRefUnit.traderCode = l_trader.getTraderCode();
            }
            l_adminOREquityOrderExecutionRefUnit.execType = l_strExecTypeDivPR;
            l_adminOREquityOrderExecutionRefUnit.changeCancelDiv =
                l_eqtypeOrderUnitParams.getModifyCancelType();
            l_adminOREquityOrderExecutionRefUnit.orderRootDiv =
                l_eqtypeOrderUnitParams.getOrderRootDiv();
            l_adminOREquityOrderExecutionRefUnit.deliveryDate =
                l_eqtypeOrderUnitParams.getDeliveryDate();
            l_adminOREquityOrderExecutionRefUnit.productCode =
                l_product.getProductCode();
            //�������@@�@@�@@�@@�@@�@@�@@���@@getProduct()�̖߂�l.������
            l_adminOREquityOrderExecutionRefUnit.productName =
                ((EqtypeProductRow)l_product.getDataSourceObject()).getStandardName();
            l_adminOREquityOrderExecutionRefUnit.marketCode = l_market.getMarketCode();
            l_adminOREquityOrderExecutionRefUnit.repaymentDiv =
                l_eqtypeOrderUnitParams.getRepaymentType();
            
            if (l_strProductDiv.equals(WEB3AdminProductDivDef.MINI_STOCK))
            {
	            l_adminOREquityOrderExecutionRefUnit.taxType = null;
	            l_adminOREquityOrderExecutionRefUnit.priceCondType = null;
            }
            else
            {
	            l_adminOREquityOrderExecutionRefUnit.taxType = l_strTaxType;
	            l_adminOREquityOrderExecutionRefUnit.priceCondType =
	                l_eqtypeOrderUnitParams.getPriceConditionType();
            }

            //�v�w�l�p�L����ԋ敪 = get�v�w�l�p�L����ԋ敪()�̖߂�l
            l_adminOREquityOrderExecutionRefUnit.wlimitEnableStatusDiv = l_strWLimitEnableStatusDiv;

            //�������ϗ��R   ���@@�����P��.�������ϗ��R�敪
            l_adminOREquityOrderExecutionRefUnit.forcedSettleReason =
                l_eqtypeOrderUnitParams.getForcedSettleReasonType();

            //���������敪  ���@@�����P��.���������敪
            l_adminOREquityOrderExecutionRefUnit.forcedLapseDiv =
                l_eqtypeOrderUnitParams.getForcedExpireType();

            //PTS�s��łȂ��ꍇ�igetMarket()�̖߂�l.isPTS�s��() == false�j
            if (!((WEB3GentradeMarket)l_market).isPTSMarket())
            {
                //�o�����͉\�t���Ofalse���Z�b�g
                l_adminOREquityOrderExecutionRefUnit.inputExecFlag = false;

                //�o������\�t���Ofalse���Z�b�g
                l_adminOREquityOrderExecutionRefUnit.cancelExecFlag = false;
            }
            else
            {
                //�S�����i�����P��.isFullyExecuted() == true�j�̏ꍇ
                if (l_orderUnits[i].isFullyExecuted())
                {
                    //�o�����͉\�t���Ofalse���Z�b�g
                    l_adminOREquityOrderExecutionRefUnit.inputExecFlag = false;
                }
                //��L�ȊO�i�����E�ꕔ���j�̏ꍇ
                else
                {
                    //�o�����͉\�t���Otrue���Z�b�g
                    l_adminOREquityOrderExecutionRefUnit.inputExecFlag = true;
                }

                //�����i�����P��.isUnexecuted() == true�j�̏ꍇ
                if (l_orderUnits[i].isUnexecuted())
                {
                    //�o������\�t���Ofalse���Z�b�g
                    l_adminOREquityOrderExecutionRefUnit.cancelExecFlag = false;
                }
                //��L�ȊO�i�ꕔ���E�S�����j�̏ꍇ
                else
                {
                    //�o������\�t���Otrue���Z�b�g
                    l_adminOREquityOrderExecutionRefUnit.cancelExecFlag = true;
                }
            }

            // 1.2.16 Add to ArrayList
            l_listOrderExecutionRefUnit.add(l_adminOREquityOrderExecutionRefUnit);
        }

        // 1.3 Call toArray()
        l_arrAdminOREquityOrderExecutionRefUnit = new WEB3AdminOREquityOrderExecutionRefUnit
            [l_listOrderExecutionRefUnit.size()];
        l_listOrderExecutionRefUnit.toArray(l_arrAdminOREquityOrderExecutionRefUnit);
        log.exiting(STR_METHOD_NAME);
        return l_arrAdminOREquityOrderExecutionRefUnit;
    }

    /**
     * (remove�J�z�������P��)<BR>
     * <BR>
     * �����̒����P�ʈꗗ����J�z���̒����P�ʂ���������B<BR>
     * <BR>
     * �P�j�p�����[�^.�����P�ʈꗗ == null�̏ꍇ�A<BR>
     * �@@null��ԋp���ďI������B<BR>
     * <BR>
     * �Q�j�����Ώۂ̔���<BR>
     * �@@�ȉ��A�p�����[�^.�����P�ʈꗗ�̗v�f������Loop�����B <BR>
     * <BR>
     * �@@[�����P��.���񒍕��̒����P��ID == null�̏ꍇ] <BR>
     * �@@(�������蒍���̏ꍇ) <BR>
     * �@@�@@���X�g�ɂ��̂܂܎c���B <BR>
     * <BR>
     * �@@[�����P��.���񒍕��̒����P��ID != null�̏ꍇ] <BR>
     * �@@(�o����܂Œ����̏ꍇ) <BR>
     * �@@�@@[���񒍕��̏ꍇ] <BR>
     * �@@�@@(�����P��.���񒍕��̒����P��ID == 0�̏ꍇ) <BR>
     * �@@�@@�@@���X�g��(�p�����[�^.�����P�ʈꗗ)���������A <BR>
     * �@@�@@�@@�@@�@@�����P��.�����P��ID == ���X�g���̒����P��.���񒍕��̒����P��ID <BR>
     * �@@�@@�@@�ƂȂ�f�[�^�����݂����ꍇ�́A���g�������ΏۂƂ���B <BR>
     * �@@�@@�@@���J�z��̒��������݂���ׁB <BR>
     * <BR>
     * �@@[�J�z�ϒ����̏ꍇ] <BR>
     * �@@(�����P��.���񒍕��̒����P��ID != 0�̏ꍇ) <BR>
     * �@@�@@���X�g��(�p�����[�^.�����P�ʈꗗ)���������A <BR>
     * �@@�@@�@@�@@�����P��.���񒍕��̒����P��ID == ���X�g���̒����P��.���񒍕��̒����P��ID
     * <BR>
     * �@@�@@�ƂȂ�f�[�^�����݂����ꍇ�́A�쐬�����r���A�ŐV�̒����P�ʈȊO��S�ď�����
     * �ۂƂ���B <BR>
     * �@@�@@���ŐV�̌J�z�����݂̂�\������ׁB <BR>
     * <BR>
     * �Q�j���X�g����̏����ΏۂƔ��肳�ꂽ�J�z���̒����P�ʃI�u�W�F�N�g���A�����P�ʈꗗ
     * ����S�ď�������B <BR>
     * �@@�@@�@@���p�����[�^.�����P�ʃI�u�W�F�N�g�̕��я��͌ڋq�w��̃\�[�g�����ɂ�邽��
     * �A�����͍Ō�ɓZ�߂čs���K�v������B <BR>
     * <BR>
     * �R�j�����ς̒����P�ʈꗗ��ԋp����B <BR>
     * �@@�������P�ʈꗗ�̗v�f����0�ɂȂ����ꍇ��NULL��ԋp����B <BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * removeBeforeCarryoverOrderUnit<BR>
     * <BR>
     * Remove l_eqtypeOrderUnit from agument l_orderUnits<BR>
     * <BR>
     * 1)If l_orderUnits == null<BR>
     * �@@return null<BR>
     * <BR>
     * 2)Check if it should be removed<BR>
     * �@@The following is the loop process as many times as the number of the elements
     * of orderUnits<BR>
     * <BR>
     * �@@If [l_orderUnit.first_order_unit_id == null] <BR>
     * �@@�@@Leave orderUnit on the list<BR>
     * <BR>
     * �@@If[l_orderUnit.first_order_unit_id != null]<BR>
     * �@@�@@If [first order]<BR>
     * �@@�@@(If l_orderUnit.first_order_unit_id == 0) <BR>
     * �@@�@@�@@Search in the list which is l_orderUnits, and<BR>
     * �@@�@@�@@Remove itself if there is l_orderUnit.order_unit_id ==
     * l_orderUnit.first_order_unit_id in the list<BR>
     * �@@�@@�@@<BR>
     * �@@�@@�@@��It's because there is an order after the carryover<BR>
     * <BR>
     * �@@If [carryovered order] <BR>
     * �@@(If l_orderUnit.first_order_unit_id != 0) <BR>
     * �@@�@@Search in the list which is l_orderUnits, and<BR>
     * �@@�@@Compare created dates and remove all except the latest orderUnit<BR>
     * if l_orderUnit.first_order_unit_id == l_orderUnit.first_order_unit_id in the
     * list <BR>
     * �@@�@@��It's because the latest carryoverOrder is only shown<BR>
     * <BR>
     * 2)Remove all l_orderUnit to be carryovered from the l_orderUnits<BR>
     * �@@�@@�@@��It is necessary to remove them at the end because the order of
     * l_orderUnit depends on the sortCond specified by an account<BR>
     * <BR>
     * 3)Return the orderUnits after removing<BR>
     * �@@��Return NULL if the number of the elements of orderUnits is 0.<BR>
     * <BR>
     * @@param l_orderUnits - (�����P�ʈꗗ)<BR>
     * <BR>
     * ���������P��Params�̔z��<BR>
     * <BR>
     * l_orderUnits<BR>
     * <BR>
     * An array of equityOrderUnitParams<BR>
     * <BR>
     * @@return EqTypeOrderUnitParams[]
     * @@roseuid 41B404C903C3
     */
    protected EqtypeOrderUnitParams[]
        removeBeforeCarryoverOrderUnit(EqtypeOrderUnitParams[] l_orderUnits)
    {
        final String STR_METHOD_NAME =
          "removeBeforeCarryoverOrderUnit(EqtypeOrderUnitParams[])";
        log.entering(STR_METHOD_NAME);
        EqtypeOrderUnitParams[] l_arrEqtypeOrderUnitParams = null;
        int l_intorderUnitsCount = 0;
        long l_lngFirstOrderUnitId = 0L;
        long l_lngFirstOrderUnitId1 = 0L;
        long l_lngOrderUnitId = 0L;
        boolean[] l_blnCarryoverOrderUnit = null;
        boolean l_isEmpty = false;
        int l_intCarryoverOrderUnitCount = 0;
        Timestamp l_tsCreatedTimeStamp = null;
        Timestamp l_tsCreatedTimeStamp1 = null;

        // 1) If l_orderUnits == null
        if (l_orderUnits == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        l_intorderUnitsCount = l_orderUnits.length;
        l_blnCarryoverOrderUnit = new boolean[l_orderUnits.length];

        // Loop till the length of order_unit
        for (int i = 0; i < l_intorderUnitsCount; i++)
        {
            l_blnCarryoverOrderUnit[i] = true;

            // If l_orderUnit.first_order_unit_id != null
            if (l_orderUnits[i].first_order_unit_id != null)
            {
                // If l_orderUnit.first_order_unit_id == 0
                l_lngFirstOrderUnitId = l_orderUnits[i].getFirstOrderUnitId();
                if (l_lngFirstOrderUnitId == 0L)
                {
                    for (int j = 0; j < l_intorderUnitsCount; j++)
                    {
                        EqtypeOrderUnitRow l_orderUnitRow1 = l_orderUnits[j];
                        l_lngOrderUnitId = l_orderUnits[i].getOrderUnitId();
                        l_lngFirstOrderUnitId1 = l_orderUnitRow1.getFirstOrderUnitId();
                        if (l_lngOrderUnitId
                            == l_lngFirstOrderUnitId1)
                        {
                            l_blnCarryoverOrderUnit[i] = false;
                            l_intCarryoverOrderUnitCount++;
                            break;
                        }
                    }
                } else
                {
                    for (int j = 0; j < l_intorderUnitsCount; j++)
                    {
                        EqtypeOrderUnitRow l_orderUnitRow1 = l_orderUnits[j];
                        l_lngFirstOrderUnitId = l_orderUnits[i].getFirstOrderUnitId();
                        l_lngFirstOrderUnitId1 = l_orderUnitRow1.getFirstOrderUnitId();
                        if (l_lngFirstOrderUnitId == l_lngFirstOrderUnitId1)
                        {
                            l_tsCreatedTimeStamp1 = l_orderUnitRow1.getCreatedTimestamp();
                            l_tsCreatedTimeStamp = l_orderUnits[i].getCreatedTimestamp();
                            if (l_tsCreatedTimeStamp1.compareTo
                            (l_tsCreatedTimeStamp) > 0)
                            {
                                l_blnCarryoverOrderUnit[i] = false;
                                l_intCarryoverOrderUnitCount++;
                                break;
                            }
                        }
                    }
                }
            }
        }
        ArrayList l_listEqtypeOrderUnitParams = new ArrayList();
        for (int i = 0; i < l_intorderUnitsCount; i++)
        {
            if (l_blnCarryoverOrderUnit[i])
            {
                l_listEqtypeOrderUnitParams.add(l_orderUnits[i]);
            }
        }

        // Return NULL if the number of the elements of orderUnits is 0
        l_isEmpty = l_listEqtypeOrderUnitParams.isEmpty();
        if (l_isEmpty)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        l_arrEqtypeOrderUnitParams = new EqtypeOrderUnitParams[l_listEqtypeOrderUnitParams.size()];
        l_listEqtypeOrderUnitParams.toArray(l_arrEqtypeOrderUnitParams);
        log.exiting(STR_METHOD_NAME);
        return l_arrEqtypeOrderUnitParams;
    }

    /**
     * (get���i�敪)<BR>
     * <BR>
     * �����̒����P�ʂ��PR�w�Ŏg�p���鏤�i�敪��ԋp����B<BR>
     * <BR>
     * �P�j�ȉ��̏����ɂ�蕪�򂵁A�Ή����鏤�i�敪��ԋp����B<BR>
     * <BR>
     * �@@[�p�����[�^.�����P��.�����J�e�S�� == "��������"�̏ꍇ]<BR>
     * �@@�@@[�p�����[�^.�����P��.������� == <BR>
     * �@@�@@�@@("�����~�j��������" or "�����~�j��������")�̏ꍇ]<BR>
     * �@@�@@�@@"�����~�j����"��ԋp����B<BR>
     * <BR>
     * �@@�@@[�p�����[�^.�����P��.������� ==<BR>
     * �@@�@@�@@("����������" or "����������")�̏ꍇ]<BR>
     * �@@�@@�@@"��������"��ԋp����B<BR>
     * <BR>
     * �@@[�p�����[�^.�����P��.�����J�e�S�� ==<BR>
     * �@@�@@("�V�K������" or "�ԍϒ���" or "�������n����")�̏ꍇ]<BR>
     * �@@�@@"�M�p���"��ԋp����B<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * getProductDiv<BR>
     * <BR>
     * Return productDiv from the argument, l_orderUnit to use in PR layer<BR>
     * <BR>
     * 1)Return the proper productDiv according to the following cases<BR>
     * <BR>
     * �@@If [l_orderUnit.order_categ == Def.ASSET]<BR>
     * �@@�@@If [l_orderUnit.order_type == <BR>
     * �@@�@@�@@(Def.MINI_STOCK_BUY or Def.MINI_STOCK_SELL]<BR>
     * �@@�@@�@@Return Def.MINI_STOCK<BR>
     * <BR>
     * �@@�@@If [l_orderUnit.order_type ==<BR>
     * �@@�@@�@@(Def.EQUITY_BUY or Def.EQUITY_SELL)]<BR>
     * �@@�@@�@@Return Def.EQUITY<BR>
     * <BR>
     * �@@If [l_orderUnit.order_categ ==<BR>
     * �@@�@@(Def.OPEN_MARGIN or Def.CLOSE_MARGIN or Def.SWAP_MARGIN]<BR>
     * �@@�@@Return Def.MARGIN<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * <BR>
     * ���������P�ʃI�u�W�F�N�g<BR>
     * <BR>
     * l_orderUnit<BR>
     * <BR>
     * equity order unit object<BR>
     * <BR>
     * @@return java.lang.String
     * @@roseuid 41B41F1F0068
     */
    protected String getProductDiv(EqTypeOrderUnit l_orderUnit)
    {
        final String STR_METHOD_NAME = "getProductDiv()";
        log.entering(STR_METHOD_NAME);

        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = null;
        String l_strWEB3AdminProductDivDef = null;
        l_eqtypeOrderUnitParams = (EqtypeOrderUnitParams) l_orderUnit.getDataSourceObject();

        // Return the proper productDiv according to the following cases
        if (l_eqtypeOrderUnitParams.order_categ.equals(OrderCategEnum.ASSET))
        {
            if ((l_eqtypeOrderUnitParams.order_type.equals(OrderTypeEnum.MINI_STOCK_BUY))
                || (l_eqtypeOrderUnitParams
                    .order_type
                    .equals(OrderTypeEnum.MINI_STOCK_SELL)))
            {
                l_strWEB3AdminProductDivDef = WEB3AdminProductDivDef.MINI_STOCK;
            }
            if ((l_eqtypeOrderUnitParams.order_type.equals(OrderTypeEnum.
                            EQUITY_BUY))
             || (l_eqtypeOrderUnitParams.order_type.equals(OrderTypeEnum.
                            EQUITY_SELL)))
            {
                l_strWEB3AdminProductDivDef = WEB3AdminProductDivDef.EQUITY;
            }
        }
        if ((l_eqtypeOrderUnitParams.order_categ.equals(OrderCategEnum.OPEN_MARGIN))
         || (l_eqtypeOrderUnitParams.order_categ.equals(OrderCategEnum.CLOSE_MARGIN))
         || (l_eqtypeOrderUnitParams.order_categ.equals(OrderCategEnum.SWAP_MARGIN)))
        {
            l_strWEB3AdminProductDivDef = WEB3AdminProductDivDef.MARGIN;
        }
        log.exiting(STR_METHOD_NAME);
        return l_strWEB3AdminProductDivDef;
    }

    /**
     * (get�����敪)<BR>
     * <BR>
     * �����̐ŋ敪��PR�w�Ŏg�p��������敪�ɕϊ����ĕԋp����B<BR>
     * <BR>
     * �P�j�����f�[�^�A�_�v�^.get�����敪(�����P��.�ŋ敪)��ԋp����B<BR>
     * <BR>
     * @@param l_eqtypeOrderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * <BR>
     * �ŋ敪<BR>
     * (EqTypeOrderUnit�ɂĒ�`)<BR>
     * <BR>
     * l_eqtypeOrderUnit<BR>
     * (Defined at EqTypeOrderUnit)<BR>
     * <BR>
     * @@return java.lang.String
     * @@roseuid 41B421F500F4
     */
    protected String getTaxType(EqTypeOrderUnit l_eqtypeOrderUnit)
    {
        final String STR_METHOD_NAME = "getTaxType()";
        log.entering(STR_METHOD_NAME);

        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = null;
        String l_strTaxTypeEnum = null;
        l_eqtypeOrderUnitParams = (EqtypeOrderUnitParams) l_eqtypeOrderUnit.getDataSourceObject();
        
        l_strTaxTypeEnum = WEB3EquityDataAdapter.getTaxType(l_eqtypeOrderUnitParams.getTaxType());

        log.exiting(STR_METHOD_NAME);
        return l_strTaxTypeEnum;
    }

    /**
     * �iget���s�����j<BR>
     * <BR>
     * �����̎��s�������AAP�w�Ŏg�p���鎷�s����<BR>
     * (EqTypeExecutionConditionType)��ԋp����B<BR>
     * <BR>
     * �P�j�p�����[�^.���s�����ɂ�蕪�򂵁A�Ή�����l��ԋp����B<BR>
     * <BR>
     * �@@�p�����[�^.���s�������A<BR>
     * �@@["������"�̏ꍇ]<BR>
     * �@@�@@EqTypeExecutionConditionType.�����Ȃ���ԋp����B<BR>
     * �@@["��t"�̏ꍇ]<BR>
     * �@@�@@EqTypeExecutionConditionType.����ԋp����B<BR>
     * �@@["����"�̏ꍇ]<BR>
     * �@@�@@EqTypeExecutionConditionType.������ԋp����B<BR>
     * �@@["�s�o���������s"�̏ꍇ]<BR>
     * �@@�@@EqTypeExecutionConditionType.�s�o���������s��ԋp����B<BR>
     * <BR>
     * ------<English>------------<BR>
     * <BR>
     * getExecCondType<BR>
     * <BR>
     * Return execCondType used in AP layer(EqTypeExecutionConditionType) from the
     * argument, l_execCondType<BR>
     * <BR>
     * 1)Go to one of the following processes according to l_execCondType, and return
     * the value<BR>
     * <BR>
     * �@@If [l_execCondTyp is Def.NO_CONDITION]<BR>
     * �@@�@@Return EqTypeExecutionConditionType.Def.NONE<BR>
     * �@@If [l_execCondTyp is Def.AT_MARKET_OPEN]<BR>
     * �@@�@@Return EqTypeExecutionConditionType.Def.AT_MARKET_OPEN<BR>
     * �@@If [l_execCondTyp is Def.AT_MARKET_CLOSE]<BR>
     * �@@�@@Return EqTypeExecutionConditionType.Def.AT_MARKET_CLOSE<BR>
     * �@@If [l_execCondTyp is Def.AT_MARKET_CLOSE_NOT_EXECUTED]<BR>
     * �@@�@@Return EqTypeExecutionConditionType.Def.AT_MARKET_CLOSE_NOT_EXECUTED<BR>
     * <BR>
     * @@param l_execCondType - ���s����<BR>
     * <BR>
     * ���s����<BR>
     * <BR>
     * l_execCondType<BR>
     * <BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType
     * @@roseuid 41D2552E0253
     */
    protected EqTypeExecutionConditionType getExecCondType(String l_execCondType)
    {
        final String STR_METHOD_NAME = "getExecCondType(String)";
        log.entering(STR_METHOD_NAME);
        EqTypeExecutionConditionType l_eqTypeExecutionConditionType = null;

        // According to l_execCondType,  return the value
        if (l_execCondType.equals(WEB3ExecutionConditionDef.NO_CONDITION))
        {
            l_eqTypeExecutionConditionType = EqTypeExecutionConditionType.NONE;
        }
        else if (l_execCondType.equals(WEB3ExecutionConditionDef.AT_MARKET_OPEN))
        {
            l_eqTypeExecutionConditionType = EqTypeExecutionConditionType.AT_MARKET_OPEN;
        }
        else if (l_execCondType.equals(WEB3ExecutionConditionDef.AT_MARKET_CLOSE))
        {
            l_eqTypeExecutionConditionType = EqTypeExecutionConditionType.AT_MARKET_CLOSE;
        }
        else if (l_execCondType.equals(WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED))
        {
            l_eqTypeExecutionConditionType =
                EqTypeExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED;
        }
        log.exiting(STR_METHOD_NAME);
        return l_eqTypeExecutionConditionType;
    }

    /**
     * �iget���s�����iPR�w�j�j<BR>
     * <BR>
     * �����̎��s�������APR�w�Ŏg�p���鎷�s������ԋp����B<BR>
     * <BR>
     * �P�j�p�����[�^.���s�����ɂ�蕪�򂵁A�Ή�����l��ԋp����B<BR>
     * <BR>
     * �@@�p�����[�^.���s�������A<BR>
     * �@@[EqTypeExecutionConditionType.�����Ȃ��̏ꍇ]<BR>
     * �@@�@@"������"��ԋp����B<BR>
     * �@@[EqTypeExecutionConditionType.���̏ꍇ]<BR>
     * �@@�@@"��t"��ԋp����B<BR>
     * �@@[EqTypeExecutionConditionType.�����̏ꍇ]<BR>
     * �@@�@@"����"��ԋp����B<BR>
     * �@@[EqTypeExecutionConditionType.�s�o���������s�̏ꍇ]<BR>
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
     * �@@If [l_execCondType is EqTypeExecutionConditionType.Def.NONE]<BR>
     * �@@�@@Return Def.NO_CONDITION<BR>
     * �@@If [l_execCondType isEqTypeExecutionConditionType.Def.AT_MARKET_OPEN]<BR>
     * �@@�@@Return Def.AT_MARKET_OPEN<BR>
     * �@@If [l_execCondType isEqTypeExecutionConditionType.Def.AT_MARKET_CLOSE]<BR>
     * �@@�@@Return Def.AT_MARKET_CLOSE<BR>
     * �@@If [l_execCondType
     * isEqTypeExecutionConditionType.Def.AT_MARKET_CLOSE_NOT_EXECUTED]<BR>
     * �@@�@@Return Def.AT_MARKET_CLOSE_NOT_EXECUTED<BR>
     * <BR>
     * @@param l_execCondType - �i���s�����j<BR>
     * <BR>
     * ���s����<BR>
     * (EqTypeExecutionConditionTypeEnum�ɂĒ�`)<BR>
     * <BR>
     * -----<English>-----------------------<BR>
     * <BR>
     * l_execCondType<BR>
     * (Defined at EqTypeExecutionConditionTypeEnum)<BR>
     * <BR>
     * @@return java.lang.String
     * @@roseuid 41D2552E0255
     */
    protected String getExecCondTypePR(EqTypeExecutionConditionType l_execCondType)
    {
        final String STR_METHOD_NAME = "getExecCondTypePR(EqTypeExecutionConditionType)";
        log.entering(STR_METHOD_NAME);
        String  l_strExecCondTypePR  = null;

        // According to l_execCondType, return the value
        if (l_execCondType.equals(EqTypeExecutionConditionType.NONE))
        {
            l_strExecCondTypePR = WEB3ExecutionConditionDef.NO_CONDITION;
        }
        else if (l_execCondType.equals(EqTypeExecutionConditionType.AT_MARKET_OPEN))
        {
            l_strExecCondTypePR = WEB3ExecutionConditionDef.AT_MARKET_OPEN;
        }
        else if (l_execCondType.equals(EqTypeExecutionConditionType.AT_MARKET_CLOSE))
        {
            l_strExecCondTypePR = WEB3ExecutionConditionDef.AT_MARKET_CLOSE;
        }
        else if (l_execCondType.equals(EqTypeExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED))
        {
            l_strExecCondTypePR = WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED;
        }
        log.exiting(STR_METHOD_NAME);
        return l_strExecCondTypePR;
    }

    /**
     * (get�戵�\�s��)<BR>
     * �戵�\�s����擾����B<BR>
     * <BR>
     * �P�j(���X�s���)�戵����.get�戵�\�s��()���R�[�����A�s��R�[�h�̔z����擾����B<BR>
     * <BR>
     * �@@�@@[(���X�s���)�戵����.get�戵�\�s��()�ɃZ�b�g�������]<BR>
     * �@@�@@�،���ЃR�[�h�F�@@����.�،���ЃR�[�h<BR>
     * �@@�@@�����^�C�v�F�@@"����"<BR>
     * <BR>
     * �Q�j(���X�s��ʁEPTS)�戵����.get�戵�\�s��()���R�[�����A<BR>
     * �@@�@@�s��R�[�h�̔z����擾����B<BR>
     * <BR>
     * �@@�@@[(���X�s��ʁEPTS)�戵����.get�戵�\�s��()�ɃZ�b�g�������]<BR>
     * �@@�@@�،���ЃR�[�h�F�@@����.�،���ЃR�[�h<BR>
     * �@@�@@�����^�C�v�F�@@"����"<BR>
     * <BR>
     * �R�j�P�j�̌��ʂƁ@@�Q�j�̌��ʂ��s��R�[�h�����Ń}�[�W����B<BR>
     * <BR>
     * �S�j�擾�����s��R�[�h�̔z���ԋp����B<BR>
     * @@param l_strInstitutionCode -- (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_productType -- (�����^�C�v)<BR>
     * �����^�C�v<BR>
     * @@return String[]
     * @@throws WEB3BaseException
     */
    protected String[] getHandlingPossibleMarket(
        String l_strInstitutionCode,
        ProductTypeEnum l_productType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getHandlingPossibleMarket(String, ProductTypeEnum)";
        log.entering(STR_METHOD_NAME);

        //�P�j(���X�s���)�戵����.get�戵�\�s��()���R�[�����A�s��R�[�h�̔z����擾����B
        String[] l_strHandlingPossibleMarkets =
            WEB3GentradeBranchMarketDealtCond.getHandlingPossibleMarket(
                l_strInstitutionCode, ProductTypeEnum.EQUITY);

        //�Q�j(���X�s��ʁEPTS)�戵����.get�戵�\�s��()���R�[�����A�s��R�[�h�̔z����擾����B
        String[] l_strPTSHandlingPossibleMarkets =
            WEB3GentradeBranchMarketPTSDealtCond.getHandlingPossibleMarket(
                l_strInstitutionCode, ProductTypeEnum.EQUITY);

        //�P�j�̌��ʂƁ@@�Q�j�̌��ʂ��s��R�[�h�����Ń}�[�W����B
        String[] l_strMergeHandlingPossibleMarkets =
            this.mergeAndSort(l_strHandlingPossibleMarkets, l_strPTSHandlingPossibleMarkets);

        //�擾�����s��R�[�h�̔z���ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_strMergeHandlingPossibleMarkets;
    }

    /**
     * ��̔z�����̔z��ɍ������āA�����\�[�g�ŕԋp����
     * @@param l_strArraySrc
     * @@param l_strDest
     * @@return String[]
     */
    private String[] mergeAndSort(String[] l_strArraySrcs, String[] l_strDests)
    {
        final String STR_METHOD_NAME = "mergeAndSort(String[], String[])";
        log.entering(STR_METHOD_NAME);

         Object[] l_objMergeArrays =
             WEB3Toolkit.toUnique(WEB3Toolkit.merge(l_strArraySrcs, l_strDests));

         if (l_objMergeArrays == null || l_objMergeArrays.length == 0)
         {
             return null;
         }

         String[] l_strResults = new String[l_objMergeArrays.length];

         for (int i = 0; i < l_objMergeArrays.length; i++)
         {
             l_strResults[i] = (String)l_objMergeArrays[i];
         }

         String l_strTemp = null;
         for (int i = 0; i < l_strResults.length; i++)
         {
             for (int j = i + 1; j < l_strResults.length; j++)
             {
                 if (Integer.parseInt(l_strResults[i]) > Integer.parseInt(l_strResults[j]))
                 {
                     l_strTemp = l_strResults[j];
                     l_strResults[j] = l_strResults[i];
                     l_strResults[i] = l_strTemp;
                 }
             }
         }

         log.exiting(STR_METHOD_NAME);
         return l_strResults;
    }
}
@
