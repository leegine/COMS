head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionChangeClosingContractInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V���������ԍϓ��̓T�[�r�X����(WEB3OptionChangeClosingContractInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 ���C�g (���u) �V�K�쐬
              001: 2004/07/23 ���Ō� (���u) OrderTypeEnum��WEB3IfoTradingDivDef�������ւ���
              002: 2004/07/28 ���Ō� (���u) �����擾�C���^�t�F�[�X�̎擾���@@���C��
              003: 2004/07/30 ���Ō� �Ή��o�b�O WEB3_IFO_UT-000085
              004: 2004/08/13 ������@@(���u) STBUG(IFO_ST-000079)��Ή�
              005: 2004/08/13 li-qiang�@@(���u) STBUG(IFO_ST-000103)��Ή�
              006: 2004/08/14 ���Ō� �Ή� �y�����w���I�v�V�����z�\�[�X�R�[�h�`�F�b�N�w�E����(JP)20040802 No014
              007: 2004/08/15 ������@@(���u) STBUG(IFO_ST-000083)��Ή�
              008: 2006/07/14 ���@@�r�@@(���u)�@@ �d�l�ύX�@@���f��476,491
              009: 2006/07/28 ������  (���u)   ���f��No.534�Ή�    
              010: 2006/09/22 �s�p  (���u)   ���f��No.557
              011: 2006/10/13 ������@@(���u)   ���f��No.566
Revesion History : 2007/06/08 �Ј��� (���u) �d�l�ύX���f��No.697 No.658 
Revesion History : 2007/06/21 �Ј��� (���u) �d�l�ύX���f��No.712
Revesion History : 2007/07/24 �Ј��� (���u) ���f��777
Revesion History : 2007/11/20 �g�E�N�| (���u) ���f��795
Revesion History : 2008/04/10 �����F (���u) ���f��864
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;

import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoOrderManagerReusableValidations;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionClientRequestService;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.define.WEB3IfoProductTypeDef;
import webbroker3.ifo.define.WEB3IfoTradeTypeDef;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.message.WEB3OptionsCloseMarginChangeInputRequest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginChangeInputResponse;
import webbroker3.ifo.service.delegate.WEB3OptionChangeClosingContractInputService;
import webbroker3.quoteadaptor.RealType;
import webbroker3.quoteadaptor.WEB3IfoQuoteData;
import webbroker3.quoteadaptor.WEB3QuoteDataSupplierService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (OP�����ԍϓ��̓T�[�r�XImpl)<BR>
 * �����w���I�v�V���������ԍϓ��̓T�[�r�X�����N���X<BR>
 *
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3OptionChangeClosingContractInputServiceImpl extends WEB3OptionClientRequestService
    implements WEB3OptionChangeClosingContractInputService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        WEB3OptionChangeClosingContractInputServiceImpl.class);

    /**
     * @@roseuid 40C0BAF00271
     */
    public WEB3OptionChangeClosingContractInputServiceImpl()
    {

    }

    /**
     * �����w���I�v�V���������ԍϓ��̓T�[�r�X���������{����B<BR>
     * <BR>
     * this.get�����ԍϓ��͉��()���\�b�h���R�[������B<BR>
     * @@param l_inRequest - ���N�G�X�g
     * @@return webbroker3.ifo.message.WEB3OptionsCloseMarginChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 407A47F7007C
     */
    public WEB3GenResponse execute(WEB3GenRequest l_inRequest) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_inRequest)";
        log.entering(STR_METHOD_NAME);

        if (!(l_inRequest instanceof WEB3OptionsCloseMarginChangeInputRequest))
        {
            log.debug("�p�����[�^�^�C�v�s��");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                STR_METHOD_NAME);
        }

        WEB3OptionsCloseMarginChangeInputRequest l_request =
            (WEB3OptionsCloseMarginChangeInputRequest)l_inRequest;
        WEB3OptionsCloseMarginChangeInputResponse l_response =
            this.getCloseChangeInputScreen(l_request);

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�����ԍϓ��͉��)<BR>
     * �����w��OP�̒����ԍϓ��͉�ʕ\���T�[�r�X�����{����B<BR>
     * <BR>
     * �u�iOP�����ԍϓ��́j���͉�ʕ\���f�[�^�擾�v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3OptionsCloseMarginChangeInputResponse
     * @@throws WEB3BaseException
     */
    public WEB3OptionsCloseMarginChangeInputResponse getCloseChangeInputScreen(
        WEB3OptionsCloseMarginChangeInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCloseChangeInputScreen(WEB3OptionsCloseMarginChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //1.1 validate()
            l_request.validate();

            //1.2 getOrder(����ID : long)
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();

            //get�����Ώے����P��
            IfoOrderUnit l_ifoOrderUnit = this.getChangeOrderUnit(l_request);
            IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();

            //1.4 get�⏕����( )
            SubAccount l_subAccount = this.getSubAccount();

            //1.5 validate����(�⏕����, String) String = �敨�^�I�v�V�����敪
            l_orderManager.validateOrder(l_subAccount, WEB3FuturesOptionDivDef.OPTION);

            //1.6 getInstance()
            WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations = null;
            l_ifoOrderManagerReusableValidations =
                (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations.getInstance();

            //validate���������\
            this.validateOrderForChangeability(l_ifoOrderUnit);

            //1.8 validate�s��h�c(long) long = �s��h�c
            long l_lngMarketId = l_ifoOrderUnitRow.getMarketId();
            Market l_market = l_ifoOrderManagerReusableValidations.validateMarketID(l_lngMarketId);

            //1.9 validate�����h�c(long) long = �����h�c
            long l_lngProductId = l_ifoOrderUnitRow.getProductId();
            WEB3IfoProductImpl l_productImpl = l_ifoOrderManagerReusableValidations.validateProductID(l_lngProductId);
            IfoProductRow l_ifoProductRow = (IfoProductRow)l_productImpl.getDataSourceObject();

            boolean l_blnBuySell = false;
            //is����
            SideEnum l_sideEnum = l_ifoOrderUnit.getSide();
            if (SideEnum.BUY.equals(l_sideEnum))
            {
                //�����P��.getSide() = �h���h�̏ꍇ�A�hfalse�h
                l_blnBuySell = false;
            }
            else if (SideEnum.SELL.equals(l_sideEnum))
            {
                //�����P��.getSide() = �h���h�̏ꍇ�A�htrue�h
                l_blnBuySell = true;
            }

            //1.14 validate�������(�敨OP����, �s��, boolean, boolean)
            WEB3IfoTradedProductImpl l_tradedProduct =
                l_ifoOrderManagerReusableValidations.validateTradedProduct(
                    l_productImpl,
                    (WEB3GentradeMarket)l_market,
                    l_blnBuySell,
                    false);

            //validate�ǌ���������t�\(�����^�C�v : ProductTypeEnum, 
            //�敨�^�I�v�V�����敪 : String, ���X : ���X, ����敪 : String, ������ : Date)
            ProductTypeEnum l_productType = l_ifoOrderUnitRow.getProductType();

            //�敨�^�I�v�V�����敪
            String l_strFutureOptionDiv = l_ifoOrderUnitRow.getFutureOptionDiv();

            WEB3GentradeSubAccount l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;
            //���X
            WEB3GentradeBranch l_branch = (WEB3GentradeBranch) l_gentradeSubAccount.getWeb3GenBranch();

            //����敪
            String l_strSessionType = l_ifoOrderUnitRow.getSessionType();

            //������
            String l_strBizDateOrder = l_ifoOrderUnitRow.getBizDate();

            Date l_datBizDate = WEB3DateUtility.getDate(l_strBizDateOrder, "yyyyMMdd");

            WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
                l_productType,
                l_strFutureOptionDiv,
                l_branch,
                l_strSessionType,
                l_datBizDate);

            //1.16 getQuote(TradedProduct, RealType)
            FinApp finApp = (FinApp) Services.getService(FinApp.class);
            WEB3QuoteDataSupplierService  l_quoteSupplier =
                (WEB3QuoteDataSupplierService ) finApp.getTradingModule(
                    ProductTypeEnum.IFO).getQuoteDataSupplierService();
            WEB3IfoQuoteData l_ifoQuoteData = null;
            if (((WEB3GentradeMainAccount)l_subAccount.getMainAccount()).isRealCustomer())
            {
                l_ifoQuoteData = l_quoteSupplier.getIfoQuote((IfoTradedProduct)l_tradedProduct, RealType.REAL);
            }
            else
            {
                l_ifoQuoteData = l_quoteSupplier.getIfoQuote((IfoTradedProduct)l_tradedProduct, RealType.DELAY);
            }

            //1.17 getCurrentPrice( )(WEB3IfoQuoteDataImpl::getCurrentPrice)
            double l_dblCurrentPrice = l_ifoQuoteData.getCurrentPrice();

            //1.18  getCurrentPriceTime( )(WEB3IfoQuoteDataImpl::getCurrentPriceTime)
            Timestamp l_tsCurrentPriceTime = l_ifoQuoteData.getCurrentPriceTime();

            //1.19 getChange( )(WEB3IfoQuoteDataImpl::getChange)
            double l_dblChange = l_ifoQuoteData.getChange();

            //1.20 �戵�\��������(String, ProductTypeEnum, String, String)
            Institution l_institution = l_subAccount.getInstitution();
            String l_strInstitutionCode = l_institution.getInstitutionCode();
            WEB3GentradeHandlingOrderCond l_gentradeHandlingOrderCond = null;
            l_gentradeHandlingOrderCond = new WEB3GentradeHandlingOrderCond(
                l_strInstitutionCode,
                l_ifoOrderUnitRow.getProductType(),
                l_ifoOrderUnitRow.getFutureOptionDiv(),
                WEB3MarginTradingDivDef.DEFAULT);

            //1.21 �戵�\�����P���敪�擾(boolean,boolean)
            String[] l_strHandlingPossibleOrderPriceDiv =
                l_gentradeHandlingOrderCond.getHandlingPossibleOrderPriceDiv(false, l_blnBuySell);

            //1.22 �戵�\���s�����擾( )
            String[] l_strHandlingPossibleExecCond = l_gentradeHandlingOrderCond.getHandlingPossibleExecCond();

            //1.23 get���s�����ꗗ(String[], String[])
            //[����]
            // �����P���敪�ꗗ�F�戵�\��������.�戵�\�����P���敪�擾()
            // ���s�����ꗗ�F�戵�\��������.�戵�\���s�����擾()
            l_strHandlingPossibleExecCond = 
                l_orderManager.getHandlingPossibleExecConds(l_strHandlingPossibleOrderPriceDiv, l_strHandlingPossibleExecCond);

            //1.24 getW�w�l�p���s�����ꗗ(String[])
            //[����]
            // get���s�����ꗗ()�̖߂�l
            //�����P�ʁF�@@�����P��
            String[] l_strWLimitExecutionConditionTypes =
                WEB3IfoDataAdapter.getWLimitExecutionConditionTypeList(
                    l_strHandlingPossibleExecCond,
                    l_ifoOrderUnit);
            
            //1.25 �戵�\���������敪�擾( )
            String[] l_strHandlingPossibleExpirationDateType =
                l_gentradeHandlingOrderCond.getHandlingPossibleExpirationDateType();

            //1.26 �戵�\���������擾( )
            String[] l_strHandlingPossibleOrderCond = l_gentradeHandlingOrderCond.getHandlingPossibleOrderCond();

            //set����ŏI��(Date)
            l_gentradeHandlingOrderCond.setTradingEndDate(l_tradedProduct.getLastTradingDate());

            //is�o����܂Œ����P�ʁi�����P�ʁj
            boolean l_blnIsCarriedOrderUnit =
                l_orderManager.isCarriedOrderUnit(l_ifoOrderUnit);
            WEB3OptionsCloseMarginChangeInputResponse l_response = null;

            //1.28 (*1)
            Date[] l_datHoliday = null;
            Date l_datLineEndDay = null;
            Date l_datStartDay = null;
            if (l_blnIsCarriedOrderUnit)
            {

                //1.28.1  get�o����܂Œ����ŏI��(�o����܂Œ���from���t : Date)
                //  �o����܂Œ����\�ȍŏI���t���擾����B
                //[����]
                IfoOrderUnitRow l_orderUnitRow =
                    (IfoOrderUnitRow)l_orderManager.getFirstOrderUnit(l_ifoOrderUnit).getDataSourceObject();
                String l_strBizDate = l_orderUnitRow.getBizDate();
                Date l_datFrom = WEB3DateUtility.getDate(l_strBizDate, "yyyyMMdd");
                l_datLineEndDay =
                    l_gentradeHandlingOrderCond.getOrderUntilDeadLineEndDayTradingEndDateConsidering(l_datFrom);

                //1.28.2 get�o����܂Œ����J�n��()
                l_datStartDay = l_gentradeHandlingOrderCond.getOrderUntilDeadLineStartDay(null,l_datLineEndDay);

                //1.26.3 �o����܂Œ����̗L�������܂ł̂��ׂĂ̋x���i�j���j��z��ŕԋp����B
                l_datHoliday = l_gentradeHandlingOrderCond.getExpirationDateHoliday(l_datStartDay);
            }

            //1.29 get�s��ǌx���w��(���X, String)
            WEB3GentradeBranch l_gentradeBranch = null;
            l_gentradeBranch =  (WEB3GentradeBranch)l_subAccount.getMainAccount().getBranch();
            String[] l_strWEB3GentradeTradingTimeManagement =
                WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(
                    l_gentradeBranch,
                    l_ifoOrderUnitRow.getFutureOptionDiv());

            //create���ʖ���ByOrder(IfoOrderUnit)
            WEB3FuturesOptionsContractUnit[] l_contractUnits =
                this.createContractUnitByOrder(l_ifoOrderUnit);

            //1.31 get�v�w�l�p�L����ԋ敪(�����P�� : IfoOrderUnit)
            String l_strWlimitEnableStatusDiv =
                WEB3IfoDataAdapter.getWLimitEnableStatusDiv(l_ifoOrderUnit);

            //1.32 get�v�w�l�p�֑ؑO�����P��(�����P�� : IfoOrderUnit)
            String l_strWlimitBefChgLimitPrice =
                WEB3IfoDataAdapter.getWLimitBefSwitchPrice(l_ifoOrderUnit);

            //1.33 get�v�w�l�p�֑ؑO���s����(�����P�� : IfoOrderUnit)
            String l_strWLimitBefChgExecCondType =
                WEB3IfoDataAdapter.getWLimitBefSwitchExecCondType(l_ifoOrderUnit);

            //is�[��܂Œ���
            boolean l_blnEveningSessionOrder = l_orderManager.isEveningSessionOrder(l_ifoOrderUnit);

            //1.34 createResponse( )
            l_response = (WEB3OptionsCloseMarginChangeInputResponse)l_request.createResponse();

            //1.35 �v���p�e�B�Z�b�g
            // ���X�|���X.�����P���敪�ꗗ���戵�\�����P���敪�擾�̖߂�l
            l_response.orderPriceDivList = l_strHandlingPossibleOrderPriceDiv;

            //���X�|���X.���s�����ꗗ��get���s�����ꗗ()�̖߂�l
            //�i�������A�����������o����܂Œ����A���͗[��܂Œ����A���͋t�w�l�����̏ꍇ�A
            //�h�������h���Z�b�g�BW�w�l�����̏ꍇ�Aget�v�w�l�p���s�����ꗗ�i)�̖߂�l���Z�b�g�B�j
            String[] l_strExecutionConditions =new String[1];
            l_strExecutionConditions[0]=WEB3ExecutionConditionDef.NO_CONDITION;

            if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_ifoOrderUnitRow.getOrderConditionType()))
            {
                l_response.execCondList = l_strWLimitExecutionConditionTypes;

            }
            else if (l_ifoOrderUnitRow.getFirstOrderUnitIdIsNull()
                && !l_blnEveningSessionOrder
                && !WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_ifoOrderUnitRow.getOrderConditionType()))
            {
                l_response.execCondList = l_strHandlingPossibleExecCond;
            }
            else
            {
                l_response.execCondList = l_strExecutionConditions;
            }

            // ���X�|���X.���������敪�ꗗ���戵�\���������敪�擾�̖߂�l
            l_response.expirationDateTypeList = l_strHandlingPossibleExpirationDateType;

            // ���X�|���X.�L�������J�n����get�o����܂Œ����J�n���̖߂�l
            l_response.expirationStartDate    = WEB3DateUtility.toDay(l_datStartDay);

            // ���X�|���X.�L�������ŏI����get�o����܂Œ����ŏI��<����ŏI���l��>�̕Ԃ�l
            l_response.expirationEndDate = WEB3DateUtility.toDay(l_datLineEndDay);

            // ���X�|���X.�L���������j���ꗗ��get�����������j���ꗗ�̖߂�l
            l_response.holidayList            = l_datHoliday;

            // ���X�|���X.���������敪�ꗗ���戵�\���������擾�̖߂�l
            l_response.orderCondTypeList      = l_strHandlingPossibleOrderCond;

            //���X�|���X.W�w�l�p���s�����ꗗ��getW�w�l�p���s�����ꗗ()�̖߂�l
            l_response.wlimitExecCondList = l_strWLimitExecutionConditionTypes;

            //���X�|���X.����敪 = �����P��.get����敪()
            l_response.sessionType = l_ifoOrderUnitRow.getSessionType();

            // ���X�|���X.����敪���i�ȉ��̂Ƃ���j
            //    �����P��.�������="608"�iOP�����ԍϒ����i���ԍρj�j => "5"
            //    �����P��.�������="607"�iOP�����ԍϒ����i���ԍρj�j => "6"
            if (OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE.equals(l_ifoOrderUnitRow.getOrderType()))
            {
                l_response.tradingType = WEB3IfoTradeTypeDef.CLOSE_SELL_CONTRACT;
            }
            else if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE.equals(l_ifoOrderUnitRow.getOrderType()))
            {
                l_response.tradingType = WEB3IfoTradeTypeDef.CLOSE_BUY_CONTRACT;
            }

            // ���X�|���X.����s�ꁁ�����P��.�s��R�[�h(SONAR)
            l_response.marketCode        = l_ifoOrderUnitRow.getSonarMarketCode();

            // ���X�|���X.�w����ʁ��敨OP����.�����Y�����R�[�h
            l_response.targetProductCode = l_productImpl.getUnderlyingProductCode();

            // ���X�|���X.�������敨OP����.����
            l_response.delivaryMonth     = l_ifoProductRow.getMonthOfDelivery();

            // ���X�|���X.�I�v�V�������i�敪���i�ȉ��̂Ƃ���j
            //    �敨OP����.�敨�I�v�V�������i="2"�i�R�[���I�v�V�����j => "C"
            //    �敨OP����.�敨�I�v�V�������i="3"�i�v�b�g�I�v�V�����j => "P"
            if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_ifoProductRow.getDerivativeType()))
            {
                l_response.opProductType = WEB3IfoProductTypeDef.CALL_OPTIONS;
            }
            else if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_ifoProductRow.getDerivativeType()))
            {
                l_response.opProductType = WEB3IfoProductTypeDef.PUT_OPTIONS;
            }

            // ���X�|���X.�s�g���i���敨OP����.�s�g���i
            double l_dblStrikePrice = l_ifoProductRow.getStrikePrice();
            if (Double.isNaN(l_dblStrikePrice))
            {
                l_dblStrikePrice = 0D;
            }
            l_response.strikePrice     = WEB3StringTypeUtility.formatNumber(l_dblStrikePrice);

            //getCurrentPrice�̕Ԃ�l
            //������0�̏ꍇ�Anull��ݒ肷��
            if (l_dblCurrentPrice == 0D)
            {
                l_response.currentPrice = null;
            }
            else
            {
                //������0�łȂ��ꍇ�A�擾�������ݒl��ݒ肷��
                l_response.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblCurrentPrice);
            }

            // ���X�|���X.�O���䁁getChange()�̖߂�l
            l_response.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_dblChange);

            // ���X�|���X.�������(�������\����)��getCurrentPriceTime()�̖߂�l
            l_response.currentPriceTime = l_tsCurrentPriceTime;

            // ���X�|���X.���Ϗ����������P��.���Ϗ���
            l_response.closingOrder    = l_ifoOrderUnitRow.getClosingOrder();

            // ���X�|���X.���ʖ��ׁ�create���ʖ���ByOrder�̖߂�l
            l_response.contractUnits = l_contractUnits;

            // ���X�|���X.�������ʁ�if�����P��.���Ϗ��� != "0"(�����_��)�̏ꍇ�̂݁A�����P��.�������ʂ��Z�b�g
            double l_dblQuantity = l_ifoOrderUnitRow.getQuantity();
            if (Double.isNaN(l_dblQuantity))
            {
                l_dblQuantity = 0D;
            }
            if (!WEB3ClosingOrderDef.RANDOM.equals(l_ifoOrderUnitRow.getClosingOrder()))
            {
                l_response.opOrderQuantity = WEB3StringTypeUtility.formatNumber(l_dblQuantity);
            }

            // ���X�|���X.�����P���敪��(�����P��.isMarketOrder()����true)�̏ꍇ "���s"�A�ȊO "�w�l"
            l_response.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
            if (l_ifoOrderUnit.isMarketOrder())
            {
                l_response.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
            }
            // ���X�|���X.�����P���������P��.�w�l
            if (l_ifoOrderUnitRow.getLimitPrice() == 0)
            {
                l_response.limitPrice   = null;
            }
            else
            {
                l_response.limitPrice   = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getLimitPrice());
            }

            //���X�|���X.���s�������i�ȉ��̂Ƃ���j
            //���X�|���X.���s�������敨OP�f�[�^�A�_�v�^.get���s�����iPR�w�j(�����P��.���s����)�̖߂�l
            l_response.execCondType = 
                WEB3IfoDataAdapter.getExecutionCondByPr(l_ifoOrderUnitRow.getExecutionConditionType());

            //���X�|���X.���������敪���敨OP�f�[�^�A�_�v�^.get���������敪(�����P��)�̖߂�l
            String l_strExpirationDateType = WEB3IfoDataAdapter.getExpirationDateType(l_ifoOrderUnit);
            l_response.expirationDateType = l_strExpirationDateType;

            //���X�|���X.�����L���������敨OP�f�[�^�A�_�v�^.get���������敪(�����P��)�̖߂�l
            //��"�o����܂Œ���"�̏ꍇ�̂݁A�����P��.�������������Z�b�g�B�ȊO�̏ꍇ�Anull���Z�b�g�B
            if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_strExpirationDateType))
            {
                l_response.expirationDate = WEB3DateUtility.toDay(l_ifoOrderUnitRow.getExpirationDate());
            }
            else
            {
                l_response.expirationDate = null;
            }


            // ���X�|���X.���������敪�������P��.��������
            l_response.orderCondType  = l_ifoOrderUnitRow.getOrderConditionType();

            //�������P��.�����������P�i�t�w�l�j�̏ꍇ��
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_response.orderCondType))
            {
                //  ���X�|���X.�t�w�l�p�v���~�A��/�����Y���i�������P��.�t�w�l��l�^�C�v
                l_response.stopPremium_underlyingAssets = l_ifoOrderUnitRow.getStopPriceType();

                //  ���X�|���X.�t�w�l�p���������P���������P��.�t�w�l��l
                l_response.stopOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getStopOrderPrice());

                //  ���X�|���X.�t�w�l�p�����������Z�q�������P��.�����������Z�q
                l_response.stopOrderCondOperator       = l_ifoOrderUnitRow.getOrderCondOperator();
            }
            //�������P��.�����������Q�iW�w�l�j�̏ꍇ��
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_response.orderCondType))
            {
                //  ���X�|���X.W�w�l�p�v���~�A��/�����Y���i�������P��.�t�w�l��l�^�C�v
                l_response.wlimitPremium_underlyingAssets = l_ifoOrderUnitRow.getStopPriceType();

                //  ���X�|���X.W�w�l�p���������P���������P��.�t�w�l��l
                l_response.wlimitOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getStopOrderPrice());

                //  ���X�|���X.W�w�l�p�����������Z�q�������P��.�����������Z�q
                l_response.wlimitOrderCondOperator = l_ifoOrderUnitRow.getOrderCondOperator();

                // ���X�|���X.W�w�l�p�����P���敪��if �����P��.(W�w�l)�����w�l��0 then "0"(���s) else "1"(�w�l)
                // ���X�|���X.W�w�l�p�����P����if �����P��.(W�w�l)�����w�l != "0"(�w�l)�̏ꍇ�̂݁A�����P��.(W�w�l)�����w�l���Z�b�g
                if (l_ifoOrderUnitRow.getWLimitPrice() == 0)
                {
                    l_response.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                    l_response.wLimitPrice = null;
                }
                else
                {
                    l_response.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                    l_response.wLimitPrice = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getWLimitPrice());
                }

                //���X�|���X.W�w�l�p���s�������敨OP�f�[�^�A�_�v�^.get���s�����iPR�w�j(�����P��.�iW�w�l)���s����)
                l_response.wlimitExecCondType = WEB3IfoDataAdapter.getExecutionCondByPr(
                    l_ifoOrderUnitRow.getWLimitExecCondType());
            }
            
            // ���X�|���X.W�w�l�p�L����ԋ敪��get�v�w�l�p�L����ԋ敪()�̖߂�l
            l_response.wlimitEnableStatusDiv = l_strWlimitEnableStatusDiv;
            
            //���X�|���X.W�w�l�p�֑ؑO�����P����get�v�w�l�p�֑ؑO�����P��()�̖߂�l
            l_response.wlimitBefChgLimitPrice = l_strWlimitBefChgLimitPrice;
            
            //���X�|���X.W�w�l�p�֑ؑO���s������get�v�w�l�p�֑ؑO���s����()�̖߂�l
            l_response.wlimitBefChgExecCondType = l_strWLimitBefChgExecCondType;
            
            //���X�|���X.�����������敪�������P��.����������
            l_response.orgOrderCondType = l_ifoOrderUnitRow.getOrgOrderConditionType();
            
            //���X�|���X.���v���~�A���^�����Y���i�������P��.���t�w�l��l�^�C�v
            l_response.orgPremium_underlyingAssets = 
                l_ifoOrderUnitRow.getOrgStopPriceType();

            //���X�|���X.�����������P���������P��.���t�w�l��l
            if (l_ifoOrderUnitRow.getOrgStopOrderPriceIsNull())
            {
                l_response.orgOrderCondPrice = null;
            }
            else
            {
                l_response.orgOrderCondPrice = WEB3StringTypeUtility.formatNumber(
                    l_ifoOrderUnitRow.getOrgStopOrderPrice());
            }
            //���X�|���X.�������������Z�q�������P��.�������������Z�q
            l_response.orgCondOperator = l_ifoOrderUnitRow.getOrgOrderCondOperator();
            
            //���X�|���X.���v�w�l�p�����P���敪���敨OP�f�[�^�A�_�v�^.get��W�w�l�p�����P���敪(�����P��)
            l_response.orgWLimitOrderPriceDiv = 
                WEB3IfoDataAdapter.getOrgWLimitOrderPriceDiv(l_ifoOrderUnit);
            //���X�|���X.���v�w�l�p�����P�������v�w�l�p�����P���敪��"�w�l"�̏ꍇ�̂݁A
            //�敨OP�f�[�^�A�_�v�^.get���v�w�l�p�����P���i�����P�ʁj�̕Ԃ�l
            if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_response.orgWLimitOrderPriceDiv))
            {
                l_response.orgWLimitPrice = 
                    WEB3IfoDataAdapter.getOrgWLimitOrderPrice(l_ifoOrderUnit);
            }
            //���X�|���X.���v�w�l�p���s�������敨OP�f�[�^�A�_�v�^.get��W�w�l�p���s����(�����P��)
            l_response.orgWlimitExecCondType = 
                WEB3IfoDataAdapter.getOrgWLimitExecCondType(l_ifoOrderUnit);
            // ���X�|���X.�T�Z��n����������P��.�T�Z��n���
            l_response.estimatedPrice = 
                WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getEstimatedPrice());

            // ���X�|���X.����I���x��������get�s��ǌx���w���̖߂�l
            l_response.messageSuspension = l_strWEB3GentradeTradingTimeManagement;

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (NotFoundException l_ex)
        {
            log.error("�f�[�^�s�����G���[", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (get�����Ώے����P��)<BR>
     * �����Ώۂ̒����P�ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �������̏ڍׂ̓V�[�P���X�}<BR>
     * �u�I�v�V���������ԍϓ���/�iOP�����ԍϓ��́j���͉�ʕ\���f�[�^�擾�v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * @@return IfoOrderUnit
     * @@throws WEB3BaseException
     */
    protected IfoOrderUnit getChangeOrderUnit(WEB3OptionsCloseMarginChangeInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getChangeOrderUnit(WEB3OptionsCloseMarginChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();

        // getOrderUnits
        long l_lngID = Long.parseLong(l_request.id);
        OrderUnit[] l_ifoOrderUnits = l_orderManager.getOrderUnits(l_lngID);

        log.exiting(STR_METHOD_NAME);
        return (IfoOrderUnit)l_ifoOrderUnits[0];
    }

    /**
     * (validate���������\)<BR>
     * �����Ώے������A�����\�ȏ�Ԃ��ǂ������`�F�b�N����B<BR>
     * <BR>
     * �������̏ڍׂ̓V�[�P���X�}<BR>
     * �u�I�v�V���������ԍϓ���/�iOP�����ԍϓ��́j���͉�ʕ\���f�[�^�擾�v�Q��<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �敨OP�����P�ʃI�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     */
    protected void validateOrderForChangeability(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderForChangeability(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        //getInstance
        WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations =
            (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations.getInstance();

        //validate���������\���()
        try
        {
            l_ifoOrderManagerReusableValidations.validateOrderForChangeability(l_orderUnit.getOrder());
        }
        catch (OrderValidationException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                l_ex.getValidationResult().getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create���ʖ���ByOrder)<BR>
     * �����̒����P�ʂɊ֘A���錚�ʖ��ׂ̈ꗗ���쐬����B<BR>
     * <BR>
     * �������̏ڍׂ̓V�[�P���X�}<BR>
     * �u�I�v�V���������ԍϓ���/�iOP�����ԍϓ��́j���͉�ʕ\���f�[�^�擾�v�Q��<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �敨OP�����P�ʃI�u�W�F�N�g�B<BR>
     * @@return WEB3FuturesOptionsContractUnit[]
     * @@throws WEB3BaseException
     */
    protected WEB3FuturesOptionsContractUnit[] createContractUnitByOrder(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createContractUnitByOrder(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

        //create���ʖ���ByOrder(long)
        WEB3FuturesOptionsContractUnit[] l_contractUnits =
            l_orderManager.createContractUnitByOrder(l_orderUnit.getOrderId());

        log.exiting(STR_METHOD_NAME);
        return l_contractUnits;
    }
}
@
