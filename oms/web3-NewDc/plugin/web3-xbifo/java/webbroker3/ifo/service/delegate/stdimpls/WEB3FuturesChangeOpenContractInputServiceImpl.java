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
filename	WEB3FuturesChangeOpenContractInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�����V�K�����̓T�[�r�X�����N���X(WEB3FuturesChangeOpenContractInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/26 ���� (���u) �V�K�쐬
                 : 2006/07/31 �юu�� (���u) �d�l�ύX ���f��No.492,534
                 : 2006/09/22 �s�p  (���u)   ���f��No.557
                 : 2006/10/13 ������@@(���u)   ���f��No.566
Revesion History : 2007/06/21 ���^�]  (���u) �d�l�ύX���f��No.705
Revesion History : 2007/07/24 �Ј��� (���u) ���f��777
Revesion History : 2007/11/20 ���n�m (���u) �d�l�ύX ���f��798
Revesion History : 2007/11/28 ���n�m (���u) �d�l�ύX Java�\�[�X�i��{�݌v�ƍ����Ă��Ȃ������jNo.008
Revesion History : 2008/03/13 �����F(���u) ���f��834 835
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
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
import webbroker3.quoteadaptor.RealType;
import webbroker3.quoteadaptor.WEB3IfoQuoteData;
import webbroker3.quoteadaptor.WEB3QuoteDataSupplierService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.ifo.WEB3FuturesClientRequestService;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoOrderManagerReusableValidations;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.message.WEB3FuturesOpenMarginChangeInputRequest;
import webbroker3.ifo.message.WEB3FuturesOpenMarginChangeInputResponse;
import webbroker3.ifo.service.delegate.WEB3FuturesChangeOpenContractInputService;


/**
 * (�敨�����V�K�����̓T�[�r�XImpl)<BR>
 * �����w���敨�����V�K�����̓T�[�r�X�����N���X
 * @@author ����
 * @@version 1.0
 */
public class WEB3FuturesChangeOpenContractInputServiceImpl extends WEB3FuturesClientRequestService implements WEB3FuturesChangeOpenContractInputService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesChangeOpenContractInputServiceImpl.class);

    /**
     * @@roseuid 40F7A2CF02EE
     */
    public WEB3FuturesChangeOpenContractInputServiceImpl()
    {

    }

    /**
     * �����w���敨�����V�K�����̓T�[�r�X���������{����B<BR> 
     * <BR>
     * this.create���͉�ʂ��R�[������B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A8ABD402D0
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (!(l_request instanceof WEB3FuturesOpenMarginChangeInputRequest))
        {
            log.debug("�p�����[�^�̃^�C�v���s��");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }

        WEB3FuturesOpenMarginChangeInputRequest l_openMarginChangeInputRequest =
            (WEB3FuturesOpenMarginChangeInputRequest)l_request;

        WEB3FuturesOpenMarginChangeInputResponse l_response = this.createInputScreen(l_openMarginChangeInputRequest);

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�����Ώے����P��)<BR>
     * get�����Ώے����P�� <BR>
     * <BR>
     * �����Ώۂ̒����P�ʂ��擾����B<BR> 
     * <BR>
     * �������̏ڍׂ̓V�[�P���X�}�u�i�敨�����V�K�����́j���͉�ʕ\���f�[�^�擾�v�Q��<BR>
     * @@param l_request - (�����w���敨�����V�K�����͉�ʃ��N�G�X�g)<BR>
     * �����w���敨�����V�K�����͉�ʃ��N�G�X�g<BR>
     * @@return IfoOrderUnit
     * @@throws WEB3BaseException
     */
    protected IfoOrderUnit getChangeOrderUnit(WEB3FuturesOpenMarginChangeInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME= "getChangeOrderUnit(WEB3FuturesOpenMarginChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        WEB3FuturesOrderManagerImpl l_orderMgr =
            (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();

        //getOrderUnits(����ID : long)
        //����ID�F ���N�G�X�g�f�[�^.ID
        OrderUnit[] l_orderUnits = l_orderMgr.getOrderUnits(Long.parseLong(l_request.id));
        OrderUnit l_orderUnit = null;

        if (l_orderUnits.length == 0)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�e�[�u���ɊY������f�[�^������܂���B");
        }
        else
        {
            l_orderUnit = l_orderUnits[0];
        }

        log.exiting(STR_METHOD_NAME);
        return (IfoOrderUnit)l_orderUnit;
    }
    
    /**
     * (validate���������\)<BR>
     * validate���������\ <BR>
     * <BR>
     * �����Ώے������A�����\�ȏ�Ԃ��ǂ������`�F�b�N����B <BR>
     * <BR>
     * �������̏ڍׂ̓V�[�P���X�}�u�i�敨�����V�K�����́j���͉�ʕ\���f�[�^�擾�v�Q��<BR>
     * @@param l_orderUnit - (IfoOrderUnit)<BR>
     * @@throws WEB3BaseException
     */
    protected void validateOrderForChangeability(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME= "validateOrderForChangeability(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        try
        {
            //getInstance()
            WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations =
                (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations.getInstance();
    
            //validate���������\���(Order)
            //���� �F �����P��.getOrder()�̖߂�l
            l_ifoOrderManagerReusableValidations.validateOrderForChangeability(l_orderUnit.getOrder());
    
            log.exiting(STR_METHOD_NAME);
        }
        catch (OrderValidationException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                l_ex.getValidationResult().getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (create���͉��)<BR>
     * ���͉�ʕ\������ <BR>
     * <BR>
     * �V�[�P���X�}�u�i�敨�����V�K�����́j���͉�ʕ\���f�[�^�擾�v�Q��<BR>
     * @@param l_request0 - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3FuturesOpenMarginChangeInputResponse
     * @@throws WEB3BaseException
     */
    protected WEB3FuturesOpenMarginChangeInputResponse createInputScreen(
        WEB3FuturesOpenMarginChangeInputRequest l_request0) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createInputScreen(WEB3FuturesOpenMarginChangeInputRequest)";
        log.entering(STR_METHOD_NAME);
        try
        {
            // validate
            l_request0.validate();

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

            WEB3FuturesOrderManagerImpl l_orderMgr =
                (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();

            //get�����Ώے����P��(�����w���敨�����V�K�����͉�ʃ��N�G�X�g)
            IfoOrderUnit l_orderUnit = getChangeOrderUnit(l_request0);
            
            IfoOrderUnitRow l_ifoOrderUnitRow =
                (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

            // get�⏕����( )
            WEB3GentradeSubAccount l_subAccount = null;
            l_subAccount = (WEB3GentradeSubAccount)this.getSubAccount();

            // validate����(�⏕����, String)
            l_orderMgr.validateOrder(l_subAccount, WEB3FuturesOptionDivDef.FUTURES);

            // getInstance
            WEB3IfoOrderManagerReusableValidations l_orderManagerReusableValidations =
                (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations.getInstance();

            //validate���������\(IfoOrderUnit)
            //IfoOrderUnit�F�@@�擾����IfoOrderUnit
            validateOrderForChangeability(l_orderUnit);

            // validate�s��h�c(long)
            WEB3GentradeMarket l_market =
                (WEB3GentradeMarket)l_orderManagerReusableValidations.validateMarketID(l_ifoOrderUnitRow.getMarketId());

            // validate�����h�c(long)
            WEB3IfoProductImpl l_productImpl =
                l_orderManagerReusableValidations.validateProductID(l_ifoOrderUnitRow.getProductId());

            // validate�������(�敨OP����, �s��, boolean, boolean)
            boolean l_blnIsBuyToOpenOrder = false;
            if (SideEnum.BUY.equals(l_orderUnit.getSide()))
            {
                l_blnIsBuyToOpenOrder = true;
            }

            WEB3IfoTradedProductImpl l_tradedProduct =
                l_orderManagerReusableValidations.validateTradedProduct(
                    l_productImpl,
                    l_market,
                    l_blnIsBuyToOpenOrder,
                    true);

            //validate�ǌ���������t�\(�����^�C�v : ProductTypeEnum,
            //�敨�^�I�v�V�����敪 : String, ���X : ���X, ����敪 : String, ������ : Date)
            Date l_datOrderBizDate =
                WEB3DateUtility.getDate(
                    l_ifoOrderUnitRow.getBizDate(),
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD);

            WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
                l_ifoOrderUnitRow.getProductType(),
                l_ifoOrderUnitRow.getFutureOptionDiv(),
                l_subAccount.getWeb3GenBranch(),
                l_ifoOrderUnitRow.getSessionType(),
                l_datOrderBizDate);

            // getQuote(TradedProduct, RealType)
            WEB3QuoteDataSupplierService  l_quoteSupplier =
                (WEB3QuoteDataSupplierService ) l_tradingModule.getQuoteDataSupplierService();

            WEB3GentradeMainAccount l_gentradeMainAccount =
                (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
            WEB3IfoQuoteData l_ifoQuoteData = null;

            if (l_gentradeMainAccount.isRealCustomer())
            {
                l_ifoQuoteData = l_quoteSupplier.getIfoQuote(l_tradedProduct,RealType.REAL);
            }
            else
            {
                //NotFoundException
                l_ifoQuoteData = l_quoteSupplier.getIfoQuote(l_tradedProduct,RealType.DELAY);
            }

            // getCurrentPrice( )
            double l_dblCurrentPrice = l_ifoQuoteData.getCurrentPrice();


            // getCurrentPriceTime( )
            Timestamp l_tsCurrentPriceTime = l_ifoQuoteData.getCurrentPriceTime();

            // getChange( )
            double l_dblChange = l_ifoQuoteData.getChange();


            // �戵�\��������
            String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();

            WEB3GentradeHandlingOrderCond l_gentradeHandlingOrderCond =
                new WEB3GentradeHandlingOrderCond(
                    l_strInstitutionCode,
                    ProductTypeEnum.IFO,
                    WEB3FuturesOptionDivDef.FUTURES,
                    WEB3MarginTradingDivDef.DEFAULT);

            // �戵�\�����P���敪�擾(boolean,boolean)
            String[] l_strHandlingPossibleOrderPriceDiv = null;
            if(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.equals(l_orderUnit.getOrderType()))
            {
                l_strHandlingPossibleOrderPriceDiv = l_gentradeHandlingOrderCond.getHandlingPossibleOrderPriceDiv(true,true);
            }
                if(OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.equals(l_orderUnit.getOrderType()))
            {
                l_strHandlingPossibleOrderPriceDiv = l_gentradeHandlingOrderCond.getHandlingPossibleOrderPriceDiv(true,false);
            }
            // �戵�\���s�����擾( )
            String[] l_strHandlingPossibleExecCond = l_gentradeHandlingOrderCond.getHandlingPossibleExecCond();
            
            // ���s�����ꗗ�擾()
            l_strHandlingPossibleExecCond = 
                l_orderMgr.getHandlingPossibleExecConds
                (l_strHandlingPossibleOrderPriceDiv,l_strHandlingPossibleExecCond);

            // W�w�l�p�̎��s�����ꗗ�擾()
            String[] l_strWLimitExecutionConditionTypeList = 
                WEB3IfoDataAdapter.getWLimitExecutionConditionTypeList(
                    l_strHandlingPossibleExecCond,
                    (IfoOrderUnit) l_orderUnit);            
            
            // �戵�\���������敪�擾( )
            String[] l_strHandlingPossibleExpirationDateType =
                l_gentradeHandlingOrderCond.getHandlingPossibleExpirationDateType();

            // �戵�\���������擾( )
            String[] l_strHandlingPossibleOrderCond = null;
            l_strHandlingPossibleOrderCond = l_gentradeHandlingOrderCond.getHandlingPossibleOrderCond();

            //set����ŏI��(����ŏI�� : Date)
            //����ŏI�����Z�b�g����B
            //�m�����n
            //�敨OP�������.getLastTradingDate()�̖߂�l
            l_gentradeHandlingOrderCond.setTradingEndDate(l_tradedProduct.getLastTradingDate());

            //is�o����܂Œ����P�ʁi�����P�ʁj
            boolean l_blnIsCarriedOrderUnit =
                l_orderMgr.isCarriedOrderUnit((IfoOrderUnit)l_orderUnit);

            // (*1)
            WEB3FuturesOpenMarginChangeInputResponse l_response = null;
            Date l_datOrderUntilDeadLineStartDay = null;
            Date l_datOrderUntilDeadLineEndDay = null;
            Date[] l_datExpirationDateHoliday = null;
            if (l_blnIsCarriedOrderUnit)
            {
                //get�o����܂Œ����ŏI��<����ŏI���l��>(������������ : Date)
                //�����ŏI�����l�������o����܂Œ����ŏI�����擾����B
                //[����]
                //�o����܂Œ����J�n���F�@@OP�����}�l�[�W��.get���񒍕��̒����P��(�����P��).������
                WEB3FuturesOrderManagerImpl l_futuresOrderManager =
                    (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();
                IfoOrderUnit l_ifoOrderUnit =
                    l_futuresOrderManager.getFirstOrderUnit((IfoOrderUnit)l_orderUnit);
                IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
                String l_strBizDate = l_orderUnitRow.getBizDate();
                Date l_datFrom = WEB3DateUtility.getDate(l_strBizDate, "yyyyMMdd");
                l_datOrderUntilDeadLineEndDay =
                    l_gentradeHandlingOrderCond.getOrderUntilDeadLineEndDayTradingEndDateConsidering(l_datFrom);

                //get�o����܂Œ����J�n��(�o����܂Œ���from���t : Date, �o����܂Œ���to���t : Date)
                //�����ŏI�����l�������o����܂Œ����J�n�����擾����B
                //[����]
                //�o����܂Œ���from���t�F�@@null
                //�o����܂Œ���to���t�F�@@get�o����܂Œ����ŏI��<����ŏI���l��>()�̖߂�l
                l_datOrderUntilDeadLineStartDay = l_gentradeHandlingOrderCond.getOrderUntilDeadLineStartDay(null,l_datOrderUntilDeadLineEndDay);


                // get�����������j���ꗗ( )
                l_datExpirationDateHoliday = l_gentradeHandlingOrderCond.getExpirationDateHoliday(l_datOrderUntilDeadLineStartDay);
            }

            WEB3GentradeBranch l_branch = (WEB3GentradeBranch)l_gentradeMainAccount.getBranch();

            // get�s��ǌx���w��(���X, String)
            String[] l_strTradeCloseSuspension =
                WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(
                    l_branch,
                    WEB3FuturesOptionDivDef.FUTURES);

            // W�w�l�̗L����ԋ敪�擾
            String l_strWLimitEnableStatusDiv = 
                WEB3IfoDataAdapter.getWLimitEnableStatusDiv((IfoOrderUnit)l_orderUnit);
            
            // W�w�l�֑̐ؑO�����P���擾
            String l_strWLimitBefSwitchPrice = 
                WEB3IfoDataAdapter.getWLimitBefSwitchPrice((IfoOrderUnit)l_orderUnit);
            
            // W�w�l�֑̐ؑO���s�����擾
            String l_strWLimitBefSwitchExecCondType = 
                WEB3IfoDataAdapter.getWLimitBefSwitchExecCondType((IfoOrderUnit)l_orderUnit);

            //is�[��܂Œ���(IfoOrderUnit)
            boolean l_blnIsEveningSessionOrder = l_orderMgr.isEveningSessionOrder((IfoOrderUnit)l_orderUnit);

            // createResponse( )
            l_response = (WEB3FuturesOpenMarginChangeInputResponse)l_request0.createResponse();

            // �v���p�e�B�Z�b�g
            //���X�|���X.�����P���敪�ꗗ���戵�\�����P���敪�擾�̖߂�l
            l_response.orderPriceDivList = l_strHandlingPossibleOrderPriceDiv;

            //���X�|���X.���s�����ꗗ��get���s�����ꗗ()�̖߂�l
            //(�������A�����������o����܂Œ����A���͗[��܂Œ���(is�[��܂Œ���()�̖߂�l == true)�A
            //���͋t�w�l�����̏ꍇ�A�h�P�F�������h���Z�b�g�BW�w�l�����̏ꍇ�Aget�v�w�l�p���s�����ꗗ�i)�̖߂�l���Z�b�g�B�j
            String[] l_strExecutionConditions =new String[1];
            l_strExecutionConditions[0]=WEB3ExecutionConditionDef.NO_CONDITION;

            if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_ifoOrderUnitRow.getOrderConditionType()))
            {
               l_response.execCondList = l_strWLimitExecutionConditionTypeList;
            }
            else if (l_ifoOrderUnitRow.getFirstOrderUnitIdIsNull()
                && !WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_ifoOrderUnitRow.getOrderConditionType())
                && !l_blnIsEveningSessionOrder)
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

            //���X�|���X.�L�������ŏI����get�o����܂Œ����ŏI��<����ŏI���l��>�̖߂�l
            l_response.expirationEndDate = WEB3DateUtility.toDay(l_datOrderUntilDeadLineEndDay);

            //���X�|���X.�L���������j���ꗗ��get�����������j���ꗗ�̖߂�l
            l_response.holidayList = l_datExpirationDateHoliday;

            //���X�|���X.���������敪�ꗗ���戵�\���������擾�̖߂�l
            l_response.orderCondTypeList = l_strHandlingPossibleOrderCond;

            //���X�|���X.W�w�l�p���s�����ꗗ��getW�w�l�p���s�����ꗗ()�̖߂�l
            l_response.wlimitExecCondList = l_strWLimitExecutionConditionTypeList;

            //���X�|���X.����敪�������P��.����敪
            l_response.sessionType = l_ifoOrderUnitRow.getSessionType();

            //���X�|���X.����敪���i�ȉ��̂Ƃ���j
            //�����P�ʁE�������="601"�i�敨�V�K���������j => "3"
            //�����P�ʁE�������="602"�i�敨�V�K���������j => "4"
            if (OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.equals(l_ifoOrderUnitRow.getOrderType()))
            {
                l_response.tradingType = String.valueOf(OrderTypeEnum.MARGIN_LONG.intValue());
            }
            else if (OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.equals(l_ifoOrderUnitRow.getOrderType()))
            {
                l_response.tradingType = String.valueOf(OrderTypeEnum.MARGIN_SHORT.intValue());
            }

            //���X�|���X.����s�ꁁ�����P��.�s��R�[�h(SONAR)
            l_response.marketCode = l_ifoOrderUnitRow.getSonarMarketCode();

            //���X�|���X.�w����ʁ��敨OP����.�����Y�����R�[�h
            l_response.targetProductCode = l_productImpl.getUnderlyingProductCode();

            //���X�|���X.�������敨OP����.����
            l_response.delivaryMonth = l_productImpl.getMonthOfDelivery();

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
            l_response.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_dblChange);

            //���X�|���X.�������(�������\����)��getCurrentPriceTime�̖߂�l
            l_response.currentPriceTime = l_tsCurrentPriceTime;

            //���X�|��.�������ʁ������P��.��������
            double l_dblQuantity = l_ifoOrderUnitRow.getQuantity();
            if (Double.isNaN(l_dblQuantity))
            {
                l_dblQuantity = 0D;
            }
            l_response.futOrderQuantity = WEB3StringTypeUtility.formatNumber(l_dblQuantity);

            //���X�|���X.����萔�ʁ������P��.��萔��
            double l_dblExecutedQuantity = l_ifoOrderUnitRow.getExecutedQuantity();
            if (Double.isNaN(l_dblExecutedQuantity))
            {
                l_dblExecutedQuantity = 0D;
            }

            l_response.partExecQuantity = WEB3StringTypeUtility.formatNumber(l_dblExecutedQuantity);


            //���X�|���X.�����P���敪��(�����P��.isMarketOrder == true)�̏ꍇ"���s"�A�ȊO"�w�l"
            if (l_orderUnit.isMarketOrder())
            {
                l_response.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
            }
            else
            {
                l_response.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
            }
            //���X�|���X.�����P���������P��.�w�l
            if (l_ifoOrderUnitRow.getLimitPrice() == 0)
            {
                l_response.limitPrice = null;
            }
            else
            {
                l_response.limitPrice = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getLimitPrice());
            }
            
            //���X�|���X.���s�������敨OP�f�[�^�A�_�v�^.get���s����(PR�w)(�����P��.���s����)�̖߂�l
            l_response.execCondType = 
                WEB3IfoDataAdapter.getExecutionCondByPr(l_ifoOrderUnitRow.getExecutionConditionType());
            
            //���X�|���X.���������敪���敨OP�f�[�^�A�_�v�^.get���������敪(�����P��)�̖߂�l
            String l_strExpirationDateType = WEB3IfoDataAdapter.getExpirationDateType((IfoOrderUnit)l_orderUnit);
            l_response.expirationDateType = l_strExpirationDateType;

            //���X�|���X.�����L���������敨OP�f�[�^�A�_�v�^.get���������敪(�����P��)�̖߂�l��
            //"�o����܂Œ���"�̏ꍇ�̂݁A�����P��.�������������Z�b�g�B�ȊO�̏ꍇ�Anull���Z�b�g�B
            if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_strExpirationDateType))
            {
                l_response.expirationDate = WEB3DateUtility.toDay(l_ifoOrderUnitRow.getExpirationDate());
            }
            else
            {
                l_response.expirationDate = null;
            }

            //���X�|���X.���������敪�������P��.��������
            l_response.orderCondType = l_ifoOrderUnitRow.getOrderConditionType();

            //�������P��.�����������P�i�t�w�l�j�̏ꍇ��
            //���X�|���X.�t�w�l�p�v���~�A��/�����Y���i�������P��.�t�w�l��l�^�C�v
            //���X�|���X.�t�w�l�p���������P���������P��.�t�w�l��l
            //���X�|���X.�t�w�l�p�����������Z�q�������P��.�����������Z�q
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_ifoOrderUnitRow.getOrderConditionType()))
            {
                double l_dblStopOrderPrice = l_ifoOrderUnitRow.getStopOrderPrice();
                if (Double.isNaN(l_dblStopOrderPrice))
                {
                    l_dblStopOrderPrice = 0D;
                }

                l_response.stopOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_dblStopOrderPrice);
                l_response.stopOrderCondOperator = l_ifoOrderUnitRow.getOrderCondOperator();
            }
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_ifoOrderUnitRow.getOrderConditionType()))
            {
                //�������P��.�����������Q�iW�w�l�j�̏ꍇ��
                //���X�|���X.W�w�l�p�v���~�A��/�����Y���i�������P��.�t�w�l��l�^�C�v
                //���X�|���X.W�w�l�p���������P���������P��.�t�w�l��l
                //���X�|���X.W�w�l�p�����������Z�q�������P��.�����������Z�q
                //���X�|���X.W�w�l�p�����P���敪��if �����P��.(W�w�l)�����w�l��0 then "0"(���s) else "1"(�w�l)
                //���X�|���X.W�w�l�p�����P���������P��.(W�w�l)�����w�l
                double l_dblStopOrderPrice = l_ifoOrderUnitRow.getStopOrderPrice();
                if (Double.isNaN(l_dblStopOrderPrice))
                {
                    l_dblStopOrderPrice = 0D;
                }
                l_response.wlimitOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_dblStopOrderPrice);
                l_response.wlimitOrderCondOperator = l_ifoOrderUnitRow.getOrderCondOperator();

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
                
                //���X�|���X.�v�w�l�p���s���� = �敨OP�f�[�^�A�_�v�^.get���s�����iPR�w�j(�����P��.�iW�w�l�j���s����)
                l_response.wlimitExecCondType = 
                    WEB3IfoDataAdapter.getExecutionCondByPr(l_ifoOrderUnitRow.getWLimitExecCondType());
            }

            //���X�|���X.W�w�l�p�L����ԋ敪���敨OP�f�[�^�A�_�v�^.get�v�w�l�p�L����ԋ敪()�̖߂�l
            l_response.wlimitEnableStatusDiv = l_strWLimitEnableStatusDiv;
            
            //���X�|���X.W�w�l�p�֑ؑO�����P�����敨OP�f�[�^�A�_�v�^.get�v�w�l�p�֑ؑO�����P��(�����P��)
            l_response.wlimitBefChgLimitPrice = l_strWLimitBefSwitchPrice;
            
            //���X�|���X.W�w�l�p�֑ؑO���s�������敨OP�f�[�^�A�_�v�^.get�v�w�l�p�֑ؑO���s����(�����P��)
            l_response.wlimitBefChgExecCondType = l_strWLimitBefSwitchExecCondType;
            
            //���X�|���X.�����������敪�������P��.����������
            l_response.orgOrderCondType = l_ifoOrderUnitRow.getOrgOrderConditionType();
            
            //���X�|���X.�����������P���������P��.���t�w�l��l
            if (!l_ifoOrderUnitRow.getOrgStopOrderPriceIsNull())
            {
                l_response.orgOrderCondPrice = 
                    WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getOrgStopOrderPrice());
            }
            
            //���X�|���X.�������������Z�q�������P��.�������������Z�q
            l_response.orgCondOperator = l_ifoOrderUnitRow.getOrgOrderCondOperator();
            
            //���X�|���X.���v�w�l�p�����P���敪���敨OP�f�[�^�A�_�v�^.get��W�w�l�p�����P���敪(�����P��)
            String l_strOrgWLimitOrderPriceDiv = WEB3IfoDataAdapter.getOrgWLimitOrderPriceDiv((IfoOrderUnit)l_orderUnit);
            l_response.orgWLimitOrderPriceDiv = l_strOrgWLimitOrderPriceDiv;
            
            //���X�|���X.���v�w�l�p�����P�������v�w�l�p�����P���敪��"�w�l"�̏ꍇ�̂݁A�敨OP�f�[�^�A�_�v�^.get���v�w�l�p�����P��(�����P��)
            if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrgWLimitOrderPriceDiv))
            {
                l_response.orgWLimitPrice = WEB3IfoDataAdapter.getOrgWLimitOrderPrice((IfoOrderUnit)l_orderUnit);
            }
            
            //���X�|���X.���v�w�l�p���s�������敨OP�f�[�^�A�_�v�^.get��W�w�l�p���s����(�����P��)
            l_response.orgWlimitExecCondType = WEB3IfoDataAdapter.getOrgWLimitExecCondType((IfoOrderUnit)l_orderUnit);
            
            //���X�|���X.�T�Z������������P��.�T�Z��n���
            double l_dblEstimatedPrice = l_ifoOrderUnitRow.getEstimatedPrice();
            if (Double.isNaN(l_dblEstimatedPrice))
            {
                l_dblEstimatedPrice = 0D;
            }
            l_response.estimatedContractPrice = WEB3StringTypeUtility.formatNumber(l_dblEstimatedPrice);
            //���X�|���X.����I���x��������get�s��ǌx���w���̖߂�l
            l_response.messageSuspension = l_strTradeCloseSuspension;

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
}
@
