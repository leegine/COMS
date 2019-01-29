head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionChangeOpenContractInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : OP�����V�K�����̓T�[�r�XImpl(WEB3OptionChangeOpenContractInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/17 ���o�� (���u) �V�K�쐬
              001: 2004/07/22 ���Ō� (���u) WEB3OrderPriceDivDef��WEB3IfoOrderPriceDivDef�������ւ���
              002: 2004/07/23 ���Ō� (���u) OrderTypeEnum��WEB3IfoTradingDivDef�������ւ���
              003: 2004/07/26 ���Ō� (���u) �Ή��o�b�O IFO_UT-000020 execute()���C��
              004: 2004/07/26 ���Ō� (���u) �Ή��o�b�O IFO_UT-000023 execute()���C��
              005: 2004/07/26 ���Ō� (���u) �Ή��o�b�O IFO_UT-000024 execute()���C��
              006: 2004/07/30 ���Ō� (���u) �Ή��o�b�O IFO_UT-000077 execute()���C��
              007: 2004/08/04 ���Ō� (���u) �Ή��o�b�O IFO_UT-000110 execute()���C��
              008: 2004/08/04 ���Ō� (���u) �Ή��o�b�O BUG60
              009: 2004/08/13 ������@@(���u) STBUG(IFO_ST-000079)��Ή�
              010: 2004/08/14 ���Ō� �Ή� �y�����w���I�v�V�����z�\�[�X�R�[�h�`�F�b�N�w�E����(JP)20040802
              011: 2004/08/13 ������@@(���u) STBUG(IFO_ST-000083)��Ή�
              012: 2006/7/13 ���G�� (���u) �d�l�ύX�@@���f��469
              013: 2006/07/28 ������  (���u)   ���f��No.534�Ή�
              014: 2006/10/13 ������@@(���u)   ���f��No.566�Ή�
Revesion History : 2007/06/08 �Ј��� (���u) �d�l�ύX���f��No.696 No.666
Revesion History : 2007/06/21 �Ј��� (���u) �d�l�ύX���f��No.712
Revesion History : 2007/07/24 �Ј��� (���u) ���f��777
Revesion History : 2007/11/20 �g�E�N�| (���u) ���f��793
Revesion History : 2008/04/10 �����F (���u) ���f��851
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
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
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoOrderManagerReusableValidations;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3OptionClientRequestService;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.define.WEB3IfoProductTypeDef;
import webbroker3.ifo.define.WEB3IfoTradeTypeDef;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeInputRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeInputResponse;
import webbroker3.ifo.service.delegate.WEB3OptionChangeOpenContractInputService;
import webbroker3.quoteadaptor.RealType;
import webbroker3.quoteadaptor.WEB3IfoQuoteData;
import webbroker3.quoteadaptor.WEB3QuoteDataSupplierService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (OP�����V�K�����̓T�[�r�XImpl)<BR>
 * <BR>
 * �����w���I�v�V���������V�K�����̓T�[�r�X�����N���X<BR>
 *
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3OptionChangeOpenContractInputServiceImpl
    extends WEB3OptionClientRequestService
    implements WEB3OptionChangeOpenContractInputService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3OptionChangeOpenContractInputServiceImpl.class);

    /**
     * @@roseuid 40C0BAF2031C
     */
    public WEB3OptionChangeOpenContractInputServiceImpl()
    {

    }

    /**
     * �����w���I�v�V���������V�K�����̓T�[�r�X���������{����B<BR>
     * <BR>
     * this.create���͉�ʂ��R�[������B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3OptionsOpenMarginChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 407A4F8C000F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3OptionsOpenMarginChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.error("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        if (!(l_request instanceof WEB3OptionsOpenMarginChangeInputRequest))
        {
            log.error("�p�����[�^�̃^�C�v���s��");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�̃^�C�v���s��");
        }

        WEB3GenResponse l_response =
            this.createInputScreen((WEB3OptionsOpenMarginChangeInputRequest)l_request);

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�����Ώے����P��)<BR>
     * get�����Ώے����P��<BR>
     * <BR>
     * �����Ώۂ̒����P�ʂ��擾����B<BR>
     * <BR>
     * �������̏ڍׂ̓V�[�P���X�}�u�iOP�����V�K�����́j���͉�ʕ\���f�[�^�擾�v�Q��<BR>
     * @@param l_request - (�����w���I�v�V���������V�K�����͉�ʃ��N�G�X�g)<BR>
     * �����w���I�v�V���������V�K�����͉�ʃ��N�G�X�g
     * @@return IfoOrderUnit
     * @@throws WEB3BaseException
     */
    protected IfoOrderUnit getChangeOrderUnit(WEB3OptionsOpenMarginChangeInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getChangeOrderUnit(WEB3OptionsOpenMarginChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(
                ProductTypeEnum.IFO).getOrderManager();

        // �����Ώۂ̒����P�ʂ��擾����B
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(Long.parseLong(l_request.id));
        IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderUnits[0];

        log.exiting(STR_METHOD_NAME);
        return l_orderUnit;
    }

    /**
     * (validate���������\)<BR>
     * validate���������\<BR>
     * <BR>
     * �����Ώے������A�����\�ȏ�Ԃ��ǂ������`�F�b�N����B<BR>
     * <BR>
     * �������̏ڍׂ̓V�[�P���X�}�u�iOP�����V�K�����́j���͉�ʕ\���f�[�^�擾�v�Q��<BR>
     * @@param l_orderUnit - (IfoOrderUnit)<BR>
     * IfoOrderUnit<BR>
     * @@throws WEB3BaseException
     */
    protected void validateOrderForChangeability(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderForChangeability(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        WEB3IfoOrderManagerReusableValidations l_orderManagerReusableValidations =
            (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations.getInstance();

        try
        {
            l_orderManagerReusableValidations.validateOrderForChangeability(l_orderUnit.getOrder());
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
     * (create���͉��)<BR>
     * ���͉�ʕ\������<BR>
     * <BR>
     * �V�[�P���X�}�u�iOP�����V�K�����́j���͉�ʕ\���f�[�^�擾�v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3OptionsOpenMarginChangeInputResponse
     * @@throws WEB3BaseException
     */
    protected WEB3OptionsOpenMarginChangeInputResponse createInputScreen(
        WEB3OptionsOpenMarginChangeInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createInputScreen(WEB3OptionsOpenMarginChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        //1.validate
        l_request.validate();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(
                ProductTypeEnum.IFO).getOrderManager();

        // get�����Ώے����P��(�����w���I�v�V���������V�K�����͉�ʃ��N�G�X�g)
        IfoOrderUnit l_orderUnit = this.getChangeOrderUnit(l_request);

        IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

        //4.get�⏕����( )
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)getSubAccount();

        //5.validate����(�⏕����, String)
        l_orderManager.validateOrder(l_subAccount, WEB3FuturesOptionDivDef.OPTION);

        //6.getInstance
        WEB3IfoOrderManagerReusableValidations l_orderManagerReusableValidations =
            (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations.getInstance();

        //1.7validate�戵�\�V�K������(SubAccount, boolean)
        l_orderManagerReusableValidations.validateHandlingOpenContractOrder(
            l_subAccount, SideEnum.BUY.equals(l_orderUnit.getSide()));

        // validate���������\(IfoOrderUnit)
        this.validateOrderForChangeability(l_orderUnit);

        //8.validate�s��h�c(long)
        Market l_market = l_orderManagerReusableValidations.validateMarketID(l_orderUnitRow.getMarketId());

        //9.validate�����h�c(long)
        WEB3IfoProductImpl l_productImpl =
            l_orderManagerReusableValidations.validateProductID(l_orderUnitRow.getProductId());

        //10.validate�������(�敨OP����, �s��, boolean, boolean)
        boolean l_blnContract = false;
        if (SideEnum.BUY.equals(l_orderUnit.getSide()))
        {
            l_blnContract = true;
        }

        IfoTradedProduct l_tradedProduct = null;
        l_tradedProduct =
            (IfoTradedProduct)l_orderManagerReusableValidations.validateTradedProduct(
                l_productImpl, (WEB3GentradeMarket)l_market, l_blnContract, true);

        //validate�ǌ���������t�\(�����^�C�v : ProductTypeEnum,
        //�敨�^�I�v�V�����敪 : String, ���X : ���X, ����敪 : String, ������ : Date)
        ProductTypeEnum l_productType = l_orderUnitRow.getProductType();

        //�敨�^�I�v�V�����敪
        String l_strFutureOptionDiv = l_orderUnitRow.getFutureOptionDiv();

        //���X
        WEB3GentradeBranch l_gentradeBranch = (WEB3GentradeBranch)l_subAccount.getWeb3GenBranch();

        //����敪
        String l_strSessionType = l_orderUnitRow.getSessionType();

        Date l_datOrderBizDate =
            WEB3DateUtility.getDate(
                l_orderUnitRow.getBizDate(),
                WEB3GentradeTimeDef.DATE_FORMAT_YMD);

        WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
            l_productType,
            l_strFutureOptionDiv,
            l_gentradeBranch,
            l_strSessionType,
            l_datOrderBizDate);

        //12. getQuote(TradedProduct, RealType)
        WEB3QuoteDataSupplierService  l_quoteSupplier =
            (WEB3QuoteDataSupplierService)l_finApp.getTradingModule(
                ProductTypeEnum.IFO).getQuoteDataSupplierService();
        WEB3IfoQuoteData l_ifoQuoteData = null;
        WEB3GentradeMainAccount l_gentradeMainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        // RealType
        RealType l_realType = null;
        if (l_gentradeMainAccount.isRealCustomer())
        {
            l_realType = RealType.REAL;
        }
        else
        {
            l_realType = RealType.DELAY;
        }

        try
        {
            l_ifoQuoteData = l_quoteSupplier.getIfoQuote(l_tradedProduct, l_realType);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //13.getCurrentPrice( )
        double l_dblCurrentPrice = l_ifoQuoteData.getCurrentPrice();

        //14.getCurrentPriceTime( )
        Timestamp l_currentPriceTime = l_ifoQuoteData.getCurrentPriceTime();

        //15.getChange( )
        double l_dblGetChange = l_ifoQuoteData.getChange();

        //16.�戵�\��������
        Institution l_institution = l_subAccount.getInstitution();
        String l_strInstitutionCode = l_institution.getInstitutionCode();
        WEB3GentradeHandlingOrderCond l_gentradeHandlingOrderCond =
            new WEB3GentradeHandlingOrderCond(
                l_strInstitutionCode,
                l_orderUnitRow.getProductType(),
                WEB3FuturesOptionDivDef.OPTION,
                WEB3MarginTradingDivDef.DEFAULT);

        //17.�戵�\�����P���敪�擾(boolean,boolean)
        String[] l_strHandlingPossibleOrderPriceDiv = null;
        if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_orderUnitRow.getOrderType()))
        {
            l_strHandlingPossibleOrderPriceDiv =
                l_gentradeHandlingOrderCond.getHandlingPossibleOrderPriceDiv(true, true);
        }
        if (OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.equals(l_orderUnitRow.getOrderType()))
        {
            l_strHandlingPossibleOrderPriceDiv =
                l_gentradeHandlingOrderCond.getHandlingPossibleOrderPriceDiv(true, false);
        }

        //18.�戵�\���s�����擾( )
        String[] l_strHandlingPossibleExecCond = l_gentradeHandlingOrderCond.getHandlingPossibleExecCond();
        l_strHandlingPossibleExecCond =
            l_orderManager.getHandlingPossibleExecConds(
                l_strHandlingPossibleOrderPriceDiv, l_strHandlingPossibleExecCond);

        //get�v�w�l�p���s�����ꗗ(String[])
        String[] l_strWLimitExecutionConditionTypeLists =
            WEB3IfoDataAdapter.getWLimitExecutionConditionTypeList(
                l_strHandlingPossibleExecCond,
                l_orderUnit);

        //19.�戵�\���������敪�擾( )
        String[] l_strHandlingPossibleExpirationDateType =
            l_gentradeHandlingOrderCond.getHandlingPossibleExpirationDateType();

        //20.�戵�\���������擾( )
        String[] l_strHandlingPossibleOrderCond = l_gentradeHandlingOrderCond.getHandlingPossibleOrderCond();

        //set����ŏI��(Date)
        l_gentradeHandlingOrderCond.setTradingEndDate(l_tradedProduct.getLastTradingDate());

        //is�o����܂Œ����P�ʁi�����P�ʁj
        boolean l_blnIsCarriedOrderUnit =
            l_orderManager.isCarriedOrderUnit(l_orderUnit);

        //22. (*1)
        WEB3OptionsOpenMarginChangeInputResponse l_response = null;
        Date l_datOrderUntilDeadLineStartDay = null;
        Date l_datOrderUntilDeadLineEndDayTradingEndDate = null;
        Date[] l_datExpirationDateHoliday = null;
        if (l_blnIsCarriedOrderUnit)
        {
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_optionOrderManager =
                (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
            IfoOrderUnit l_ifoOrderUnit =
                l_optionOrderManager.getFirstOrderUnit((IfoOrderUnit)l_orderUnit);
            IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
            String l_strBizDate = l_ifoOrderUnitRow.getBizDate();
            Date l_datFrom = WEB3DateUtility.getDate(l_strBizDate, "yyyyMMdd");
            // get�o����܂Œ����ŏI��<����ŏI���l��>(Date)
            l_datOrderUntilDeadLineEndDayTradingEndDate =
                l_gentradeHandlingOrderCond.getOrderUntilDeadLineEndDayTradingEndDateConsidering(l_datFrom);

            //get�o����܂Œ����J�n��(Date, Date)
            l_datOrderUntilDeadLineStartDay =
                l_gentradeHandlingOrderCond.getOrderUntilDeadLineStartDay(
                    null, l_datOrderUntilDeadLineEndDayTradingEndDate);


            //25. get�����������j���ꗗ( )
            l_datExpirationDateHoliday =
                l_gentradeHandlingOrderCond.getExpirationDateHoliday(l_datOrderUntilDeadLineStartDay);
        }

        //26.getMainAccount(����ID : long)
        //27.get���X(String, String)
        Branch l_branch = l_gentradeMainAccount.getBranch();

        //28.get�s��ǌx���w��(���X, String)
        String[] l_strTradeCloseSuspension =
             WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(
                (WEB3GentradeBranch)l_branch,
                WEB3FuturesOptionDivDef.OPTION);

        //29.get�v�w�l�p�L����ԋ敪(IfoOrderUnit)
        String l_strWlimitEnableStatusDiv = WEB3IfoDataAdapter.getWLimitEnableStatusDiv(l_orderUnit);

        //30.get�v�w�l�p�֑ؑO�����P��(IfoOrderUnit)
        String l_strWlimitBefChgLimitPrice = WEB3IfoDataAdapter.getWLimitBefSwitchPrice(l_orderUnit);

        //31.get�v�w�l�p�֑ؑO���s����(IfoOrderUnit)
        String l_strWLimitBefChgExecCondType = WEB3IfoDataAdapter.getWLimitBefSwitchExecCondType(l_orderUnit);

        //is�[��܂Œ���
        boolean l_blnEveningSessionOrder = l_orderManager.isEveningSessionOrder(l_orderUnit);

        //32.createResponse( )
        l_response = (WEB3OptionsOpenMarginChangeInputResponse)l_request.createResponse();

        //33.�v���p�e�B�Z�b�g
        //���X�|���X.�����P���敪�ꗗ���戵�\�����P���敪�擾�̖߂�l
        l_response.orderPriceDivList = l_strHandlingPossibleOrderPriceDiv;

        //���X�|���X.���s�����ꗗ��get���s�����ꗗ()�̖߂�l
        //�i�������A�����������o����܂Œ����A���͗[��܂Œ����A���͋t�w�l�����̏ꍇ�A
        //�h�������h���Z�b�g�BW�w�l�����̏ꍇ�Aget�v�w�l�p���s�����ꗗ�i)�̖߂�l���Z�b�g�B�j
        String[] l_strExecutionConditions = new String[1];
        l_strExecutionConditions[0] = WEB3ExecutionConditionDef.NO_CONDITION;
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            l_response.execCondList = l_strWLimitExecutionConditionTypeLists;
        }
        else if (l_orderUnitRow.getFirstOrderUnitIdIsNull()
            && !l_blnEveningSessionOrder
            && !WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            l_response.execCondList = l_strHandlingPossibleExecCond;
        }
        else
        {
            l_response.execCondList = l_strExecutionConditions;
        }

        //���X�|���X.���������敪�ꗗ���戵�\���������敪�擾�̖߂�l
        l_response.expirationDateTypeList = l_strHandlingPossibleExpirationDateType;

        //���X�|���X.�L�������J�n����get�o����܂Œ����J�n���̖߂�l
        l_response.expirationStartDate = WEB3DateUtility.toDay(l_datOrderUntilDeadLineStartDay);

        //���X�|���X�D�L�������ŏI���@@���@@get�o����܂Œ����ŏI��<����ŏI���l��>�̕Ԃ�l
        l_response.expirationEndDate = WEB3DateUtility.toDay(l_datOrderUntilDeadLineEndDayTradingEndDate);

        //���X�|���X.�L���������j���ꗗ��get�����������j���ꗗ�̖߂�l
        l_response.holidayList = l_datExpirationDateHoliday;

        //���X�|���X.���������敪�ꗗ���戵�\���������擾�̖߂�l
        l_response.orderCondTypeList = l_strHandlingPossibleOrderCond;

        //���X�|���X.�v�w�l�p���s�����ꗗ��get�v�w�l�p���s�����ꗗ�i)�̖߂�l
        l_response.wlimitExecCondList = l_strWLimitExecutionConditionTypeLists;

        //���X�|���X.����敪 = �����P��.get����敪()
        l_response.sessionType = l_orderUnitRow.getSessionType();

        //���X�|���X.����萔�ʁ������P��.��萔��
        double l_dblExecutedQuantity = l_orderUnitRow.getExecutedQuantity();
        if (Double.isNaN(l_dblExecutedQuantity))
        {
            l_dblExecutedQuantity = 0D;
        }
        l_response.partExecQuantity = WEB3StringTypeUtility.formatNumber(l_dblExecutedQuantity);

        //���X�|���X.����敪���i�ȉ��̂Ƃ���j
        //�����P�ʁE�������="605"�iOP�V�K���������j => "3"
        //�����P�ʁE�������="606"�iOP�V�K���������j => "4"
        if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_orderUnitRow.getOrderType()))
        {
            l_response.tradingType = WEB3IfoTradeTypeDef.OPEN_LONG_CONTRACT;
        }
        else if (OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.equals(l_orderUnitRow.getOrderType()))
        {
            l_response.tradingType = WEB3IfoTradeTypeDef.OPEN_SHORT_CONTRACT;
        }

        //���X�|���X.����s�ꁁ�����P��.�s��R�[�h(SONAR)
        l_response.marketCode = l_orderUnitRow.getSonarMarketCode();

        //���X�|���X.�w����ʁ��敨OP����.�����Y�����R�[�h
        l_response.targetProductCode = l_productImpl.getUnderlyingProductCode();

        //���X�|���X.�������敨OP����.����
        l_response.delivaryMonth = l_productImpl.getMonthOfDelivery();

        //���X�|���X.�I�v�V�������i�敪��(�ȉ��̂Ƃ���)
        //�敨OP����.�敨�I�v�V�������i="2"�i�R�[���I�v�V�����j => "C"
        //�敨OP����.�敨�I�v�V�������i="3"�i�v�b�g�I�v�V�����j => "P"
        if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_productImpl.getDerivativeType()))
        {
            l_response.opProductType = WEB3IfoProductTypeDef.CALL_OPTIONS;
        }
        else if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_productImpl.getDerivativeType()))
        {
            l_response.opProductType = WEB3IfoProductTypeDef.PUT_OPTIONS;
        }

        //���X�|���X.�s�g���i���敨OP����.�s�g���i
        double l_dblStrikePrice = l_productImpl.getStrikePrice();
        if (Double.isNaN(l_dblStrikePrice))
        {
            l_dblStrikePrice = 0D;
        }
        l_response.strikePrice = WEB3StringTypeUtility.formatNumber(l_dblStrikePrice);

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

        //���X�|���X.�O���䁁getChange()�̖߂�l
        l_response.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_dblGetChange);

        //���X�|���X.�������(�������\����)��getCurrentPriceTime�̖߂�l
        l_response.currentPriceTime = l_currentPriceTime;

        //���X�|��.�������ʁ������P��.��������
        double l_dblQuantity = l_orderUnitRow.getQuantity();
        if (Double.isNaN(l_dblQuantity))
        {
            l_dblQuantity = 0;
        }
        l_response.opOrderQuantity = WEB3StringTypeUtility.formatNumber(l_dblQuantity);

        //���X�|���X.�����P���敪���i�����P��.isMarketOrder() == true�j�̏ꍇ "���s"�A�ȊO "�w�l"
        if (l_orderUnit.isMarketOrder())
        {
            l_response.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
        }
        else
        {
            l_response.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
        }

        //���X�|���X.�����P���������P��.�w�l != 0�̏ꍇ�A�����P��.�w�l
        if (l_orderUnitRow.getLimitPrice() == 0)
        {
            l_response.limitPrice = null;
        }
        else
        {
            l_response.limitPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getLimitPrice());
        }

        //���s�������敨OP�f�[�^�A�_�v�^.get���s�����iPR�w�j(�����P��.���s����)
        l_response.execCondType = WEB3IfoDataAdapter.getExecutionCondByPr(
            l_orderUnitRow.getExecutionConditionType());

        //���X�|���X.���������敪���敨OP�f�[�^�A�_�v�^.get���������敪(�����P��)�̖߂�l
        String l_strExpirationDateType = WEB3IfoDataAdapter.getExpirationDateType((IfoOrderUnit)l_orderUnit);
        l_response.expirationDateType = l_strExpirationDateType;

        //���X�|���X.�����L���������敨OP�f�[�^�A�_�v�^.get���������敪(�����P��)�̖߂�l
        //��"�o����܂Œ���"�̏ꍇ�̂݁A�����P��.�������������Z�b�g�B�ȊO�̏ꍇ�Anull���Z�b�g�B
        if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_strExpirationDateType))
        {
            l_response.expirationDate = WEB3DateUtility.toDay(l_orderUnitRow.getExpirationDate());
        }
        else
        {
            l_response.expirationDate = null;
        }

        //���X�|���X.���������敪�������P��.��������
        l_response.orderCondType = l_orderUnitRow.getOrderConditionType();

        //�������P��.�����������P�i�t�w�l�j�̏ꍇ��
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            //���X�|���X.�t�w�l�p�v���~�A��/�����Y���i�������P��.�t�w�l��l�^�C�v
            l_response.stopPremium_underlyingAssets = l_orderUnitRow.getStopPriceType();

            //���X�|���X.�t�w�l�p���������P���������P��.�t�w�l��l
            l_response.stopOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());

            //���X�|���X.�t�w�l�p�����������Z�q�������P��.�����������Z�q
            l_response.stopOrderCondOperator = l_orderUnitRow.getOrderCondOperator();
        }
        //�������P��.�����������Q�iW�w�l�j�̏ꍇ��
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            //���X�|���X.W�w�l�p�v���~�A��/�����Y���i�������P��.�t�w�l��l�^�C�v
            l_response.wlimitPremium_underlyingAssets = l_orderUnitRow.getStopPriceType();

            //���X�|���X.W�w�l�p���������P���������P��.�t�w�l��l
            l_response.wlimitOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());

            //���X�|���X.W�w�l�p�����������Z�q�������P��.�����������Z�q
            l_response.wlimitOrderCondOperator = l_orderUnitRow.getOrderCondOperator();

            //���X�|���X.W�w�l�p�����P���敪��if �����P��.(W�w�l)�����w�l��0 then "0"(���s) else "1"(�w�l)
            //���X�|���X.W�w�l�p�����P���������P��.(W�w�l)�����w�l
            if (l_orderUnitRow.getWLimitPrice() == 0)
            {
                l_response.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                l_response.wLimitPrice = null;
            }
            else
            {
                l_response.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                l_response.wLimitPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getWLimitPrice());
            }

            //���X�|���X.W�w�l�p���s�������敨OP�f�[�^�A�_�v�^.get���s�����iPR�w�j(�����P��.�iW�w�l)���s����)
            l_response.wlimitExecCondType = WEB3IfoDataAdapter.getExecutionCondByPr(
                l_orderUnitRow.getWLimitExecCondType());
        }

        //���X�|���X.W�w�l�p�L����ԋ敪���敨OP�f�[�^�A�_�v�^.get�v�w�l�p�L����ԋ敪(�j�̖߂�l
        l_response.wlimitEnableStatusDiv = l_strWlimitEnableStatusDiv;

        //���X�|���X.W�w�l�p�֑ؑO�����P�����敨OP�f�[�^�A�_�v�^.get�v�w�l�p�֑ؑO�����P��()�̖߂�l
        l_response.wlimitBefChgLimitPrice = l_strWlimitBefChgLimitPrice;

        //���X�|���X.W�w�l�p�֑ؑO���s�������敨OP�f�[�^�A�_�v�^.get�v�w�l�p�֑ؑO���s����()�̖߂�l
        l_response.wlimitBefChgExecCondType = l_strWLimitBefChgExecCondType;

        //���X�|���X.�����������敪�������P��.����������
        l_response.orgOrderCondType = l_orderUnitRow.getOrgOrderConditionType();

        //���X�|���X.���v���~�A���^�����Y���i�������P��.���t�w�l��l�^�C�v
        l_response.orgPremium_underlyingAssets = l_orderUnitRow.getOrgStopPriceType();

        //���X�|���X.�����������P���������P��.���t�w�l��l
        if (!l_orderUnitRow.getOrgStopOrderPriceIsNull())
        {
            l_response.orgOrderCondPrice = WEB3StringTypeUtility.formatNumber(
                l_orderUnitRow.getOrgStopOrderPrice());
        }

        //���X�|���X.�������������Z�q�������P��.�������������Z�q
        l_response.orgCondOperator = l_orderUnitRow.getOrgOrderCondOperator();

        //���X�|���X.���v�w�l�p�����P���敪���敨OP�f�[�^�A�_�v�^.get��W�w�l�p�����P���敪(�����P��)
        l_response.orgWLimitOrderPriceDiv = WEB3IfoDataAdapter.getOrgWLimitOrderPriceDiv(l_orderUnit);

        //���X�|���X.���v�w�l�p�����P�������v�w�l�p�����P���敪��"�w�l"��
        //�ꍇ�̂݁A�敨OP�f�[�^�A�_�v�^.get���v�w�l�p�����P��(�����P��)
        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_response.orgWLimitOrderPriceDiv))
        {
            l_response.orgWLimitPrice = WEB3IfoDataAdapter.getOrgWLimitOrderPrice(l_orderUnit);
        }

        //���X�|���X.���v�w�l�p���s�������敨OP�f�[�^�A�_�v�^.get��W�w�l�p���s����(�����P��)
        l_response.orgWlimitExecCondType = WEB3IfoDataAdapter.getOrgWLimitExecCondType(l_orderUnit);

        //���X�|���X.�T�Z��n����������P��.�T�Z��n���
        l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getEstimatedPrice());

        //���X�|���X.����I���x��������get�s��ǌx���w���̖߂�l
        l_response.messageSuspension = l_strTradeCloseSuspension;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
