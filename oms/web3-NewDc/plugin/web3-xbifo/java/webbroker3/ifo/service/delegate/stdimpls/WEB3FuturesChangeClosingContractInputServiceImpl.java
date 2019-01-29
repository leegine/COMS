head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesChangeClosingContractInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�����ԍϓ��̓T�[�r�X����(WEB3FuturesChangeClosingContractInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/23 ���Q (���u) �V�K�쐬
                   2004/07/26 ���Q (���u) Interface��ύX���邽�߁A�\�[�X��ύX���܂����B
                   2006/07/28 �юu�� (���u) �d�l�ύXNo.489,534
                   2006/09/22 �s�p  (���u)   ���f��No.557
                   2006/10/13 ������@@(���u)   ���f��No.566
Revesion History : 2007/06/21 �����F(���u) ���f��708
Revesion History : 2007/07/24 �Ј���(���u) ���f��777
Revesion History : 2007/11/20 ���n�m (���u) �d�l�ύX ���f��800
Revesion History : 2007/11/28 ���n�m (���u) �d�l�ύX Java�\�[�X�i��{�݌v�ƍ����Ă��Ȃ������jNo.005
Revesion History : 2008/03/13 �����F(���u) ���f��834 837 838 857
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
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
import webbroker3.ifo.WEB3FuturesClientRequestService;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoOrderManagerReusableValidations;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.define.WEB3IfoTradeTypeDef;
import webbroker3.ifo.message.WEB3FuturesCloseMarginChangeInputRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginChangeInputResponse;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.service.delegate.WEB3FuturesChangeClosingContractInputService;
import webbroker3.quoteadaptor.RealType;
import webbroker3.quoteadaptor.WEB3IfoQuoteData;
import webbroker3.quoteadaptor.WEB3QuoteDataSupplierService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�敨�����ԍϓ��̓T�[�r�XImpl)<BR>
 * �����w���敨�����ԍϓ��̓T�[�r�X�����N���X<BR>
 *
 * @@author ���Q
 * @@version 1.0
 */
public class WEB3FuturesChangeClosingContractInputServiceImpl extends WEB3FuturesClientRequestService implements WEB3FuturesChangeClosingContractInputService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3FuturesChangeClosingContractInputServiceImpl.class);

    public WEB3FuturesChangeClosingContractInputServiceImpl()
    {

    }

    /**
     * �����w���敨�����ԍϓ��̓T�[�r�X���������{����B<BR>
     * <BR>
     * this.get�����ԍϓ��͉��()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3FuturesCloseMarginChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A8BC3301CB
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME= "execute(WEB3GenRequest l_inRequest)";
        log.entering(STR_METHOD_NAME);

        if (!(l_request instanceof WEB3FuturesCloseMarginChangeInputRequest))
        {
            log.debug("�p�����[�^�^�C�v�s��");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3FuturesCloseMarginChangeInputRequest l_FRequest =
            (WEB3FuturesCloseMarginChangeInputRequest)l_request;

        WEB3FuturesCloseMarginChangeInputResponse l_response =
            this.getCloseChangeInputScreen(l_FRequest);

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�����ԍϓ��͉��)<BR>
     * �����w���敨�̒����ԍϓ��͉�ʕ\���T�[�r�X�����{����B <BR>
     * <BR>
     * �u�i�敨�����ԍϓ��́j���͉�ʕ\���f�[�^�擾�v�Q�ƁB<BR>
     * @@param l_FRequest - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3FuturesCloseMarginChangeInputResponse
     * @@throws WEB3BaseException
     */
    protected WEB3FuturesCloseMarginChangeInputResponse getCloseChangeInputScreen(
        WEB3FuturesCloseMarginChangeInputRequest l_FRequest) throws WEB3BaseException
    {
        final String STR_METHOD_NAME= "getCloseChangeInputScreen(WEB3FuturesCloseMarginChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //1.1 validate()
            l_FRequest.validate();

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3FuturesOrderManagerImpl l_orderManager =
                (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();

            IfoOrderUnit l_orderUnit = getChangeOrderUnit(l_FRequest);
            IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();

            //1.4 get�⏕����()
            WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) this.getSubAccount();

            //1.5 validate����(�⏕����, String) String = �敨�^�I�v�V�����敪
            l_orderManager.validateOrder(l_subAccount, WEB3FuturesOptionDivDef.FUTURES);

            WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations = null;
            l_ifoOrderManagerReusableValidations =
                (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations.getInstance();

            //validate���������\(IfoOrderUnit)
            validateOrderForChangeability(l_orderUnit);

            //1.8 validate�s��h�c(long) long = �s��h�c
            long l_lngMarketId = l_ifoOrderUnitRow.getMarketId();
            Market l_market = l_ifoOrderManagerReusableValidations.validateMarketID(l_lngMarketId);

            //1.9 validate�����h�c(long) long = �����h�c
            long l_lngProductId = l_ifoOrderUnitRow.getProductId();
            WEB3IfoProductImpl l_productImpl =
                l_ifoOrderManagerReusableValidations.validateProductID(l_lngProductId);

            //is�����F
            //�����P��.getSide() = �h���h�̏ꍇ�A�hfalse�h
            //�����P��.getSide() = �h���h�̏ꍇ�A�htrue�h
            boolean l_blnContract = false;
            if (SideEnum.BUY.equals(l_orderUnit.getSide()))
            {
                l_blnContract = false;
            }
            else if (SideEnum.SELL.equals(l_orderUnit.getSide()))
            {
                l_blnContract = true;
            }

            //1.14 validate�������(�敨OP����, �s��, boolean, boolean)
            WEB3IfoTradedProductImpl l_tradedProduct =
                l_ifoOrderManagerReusableValidations.validateTradedProduct(
                    l_productImpl,
                    (WEB3GentradeMarket)l_market,
                    l_blnContract,
                    false);

            //validate�ǌ���������t�\(�����^�C�v : ProductTypeEnum,
            //  �敨�^�I�v�V�����敪 : String, ���X : ���X, ����敪 : String, ������ : Date)
            //[�w�肷�����]
            //�����^�C�v�F�@@�����P��.�����^�C�v
            //�敨�^�I�v�V�����敪�F�@@�����P��.�敨�^�I�v�V�����敪
            //���X�F�@@�⏕����.get����X()
            //����敪�F�@@�����P��.����敪
            //�������F�@@�����P��.������
            WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
                l_ifoOrderUnitRow.getProductType(),
                l_ifoOrderUnitRow.getFutureOptionDiv(),
                l_subAccount.getWeb3GenBranch(),
                l_ifoOrderUnitRow.getSessionType(),
                WEB3DateUtility.getDate(l_ifoOrderUnitRow.getBizDate(), "yyyyMMdd"));

            //1.16 getQuote(TradedProduct, RealType)
            WEB3QuoteDataSupplierService  l_quoteSupplier =
                (WEB3QuoteDataSupplierService )l_tradingModule.getQuoteDataSupplierService();
            WEB3GentradeMainAccount l_gentradeMainAccount =
                (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
            WEB3IfoQuoteData l_ifoQuoteData = null;
            if (l_gentradeMainAccount.isRealCustomer())
            {
                l_ifoQuoteData = l_quoteSupplier.getIfoQuote(l_tradedProduct,RealType.REAL);
            }
            else
            {
                l_ifoQuoteData = l_quoteSupplier.getIfoQuote(l_tradedProduct,RealType.DELAY);
            }

            //1.17 ����(���ݒl)�̎擾
            double l_dblCurrentPrice = l_ifoQuoteData.getCurrentPrice();

            //1.18 ��������̎擾
            Timestamp l_tsCurrentPriceTime = l_ifoQuoteData.getCurrentPriceTime();

            //1.19 getChange()
            double l_dblChange = l_ifoQuoteData.getChange();

            //1.20 �戵�\��������
            Institution l_institution = l_subAccount.getInstitution();
            String l_strInstitutionCode = l_institution.getInstitutionCode();
            WEB3GentradeHandlingOrderCond l_gentradeHandlingOrderCond =
                new WEB3GentradeHandlingOrderCond(
                    l_strInstitutionCode,
                    ProductTypeEnum.IFO,
                    WEB3FuturesOptionDivDef.FUTURES,
                    WEB3MarginTradingDivDef.DEFAULT);

            //1.21 �戵�\�����P���敪���擾����B
            String[] l_strHandlingPossibleOrderPriceDivs = null;
            l_strHandlingPossibleOrderPriceDivs =
                l_gentradeHandlingOrderCond.getHandlingPossibleOrderPriceDiv(false, l_blnContract);

            //1.22 �戵�\���s�����擾
            String[] l_strHandlingPossibleExecConds =
                l_gentradeHandlingOrderCond.getHandlingPossibleExecCond();

            //1.23 ���s�����ꗗ���擾����B
            l_strHandlingPossibleExecConds = 
                l_orderManager.getHandlingPossibleExecConds(
                    l_strHandlingPossibleOrderPriceDivs, l_strHandlingPossibleExecConds);

            //1.24 �v�w�l�p�̎��s�����ꗗ���擾����B 
            String[] l_strWLimitExecutionConditionTypeList = 
                WEB3IfoDataAdapter.getWLimitExecutionConditionTypeList(
                    l_strHandlingPossibleExecConds,
                    (IfoOrderUnit) l_orderUnit);
            
            //1.25 �戵�\���������敪���擾����B
            String[] l_strHandlingPossibleExpirationDateTypes =
                l_gentradeHandlingOrderCond.getHandlingPossibleExpirationDateType();

            //1.26 �戵�\���������擾( )
            String[] l_strHandlingPossibleOrderConds =
                l_gentradeHandlingOrderCond.getHandlingPossibleOrderCond();

            //set����ŏI��(����ŏI�� : Date)
            //����ŏI�����Z�b�g����B  
            //�m�����n  
            //�敨OP�������.getLastTradingDate()�̖߂�l
            l_gentradeHandlingOrderCond.setTradingEndDate(l_tradedProduct.getLastTradingDate());

            //is�o����܂Œ����P�ʁi�����P�ʁj
            boolean l_blnIsCarriedOrderUnit =
                l_orderManager.isCarriedOrderUnit((IfoOrderUnit)l_orderUnit);

            //is�o����܂Œ����P�ʁi�����P�ʁj==True��
            Date l_datOrderUntilDeadLineStartDay = null;
            Date l_datOrderUntilDeadLineEndDay = null;
            Date[] l_datExpirationDateHolidays = null;
            if (l_blnIsCarriedOrderUnit)
            {
                //get�o����܂Œ����ŏI��<����ŏI���l��>(������������ : Date)
                //�����ŏI�����l�������o����܂Œ����ŏI�����擾����B
                //[����]
                //�o����܂Œ���from���t�F�@@OP�����}�l�[�W��.get���񒍕��̒����P��(�����P��).������
                WEB3FuturesOrderManagerImpl l_futuresOrderManager =
                    (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();
                IfoOrderUnit l_ifoOrderUnit =
                    l_futuresOrderManager.getFirstOrderUnit((IfoOrderUnit)l_orderUnit);
                IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
                String l_strBizDate = l_orderUnitRow.getBizDate();
                Date l_datFrom = WEB3DateUtility.getDate(l_strBizDate, "yyyyMMdd");
                l_datOrderUntilDeadLineEndDay =
                    l_gentradeHandlingOrderCond.getOrderUntilDeadLineEndDayTradingEndDateConsidering(l_datFrom);

                //1.28.2 get�o����܂Œ����J�n��()
                //�����ŏI�����l�������o����܂Œ����J�n�����擾����B
                //[����]
                //�o����܂Œ���from���t�F�@@null
                //�o����܂Œ���to���t�F�@@get�o����܂Œ����ŏI��<����ŏI���l��>()�̖߂�l
                l_datOrderUntilDeadLineStartDay = l_gentradeHandlingOrderCond.getOrderUntilDeadLineStartDay(null,l_datOrderUntilDeadLineEndDay);

                //1.28.3 �o����܂Œ����̗L�������܂ł̂��ׂĂ̋x���i�j���j��z��ŕԋp����B
                l_datExpirationDateHolidays = l_gentradeHandlingOrderCond.getExpirationDateHoliday(l_datOrderUntilDeadLineStartDay);
            }

            //1.29 get�s��ǌx���w��(���X, String)
            WEB3GentradeBranch l_gentradeBranch =
                (WEB3GentradeBranch)l_gentradeMainAccount.getBranch();
            String[] l_strTradeCloseSuspensions =
                WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(
                    l_gentradeBranch,
                    WEB3FuturesOptionDivDef.FUTURES);

            //create���ʖ���ByOrder(IfoOrderUnit)
            WEB3FuturesOptionsContractUnit[] l_contractUnits = createContractUnitByOrder(l_orderUnit);

            //1.31 get�v�w�l�p�L����ԋ敪()
            String l_strWLimitEnableStatusDiv = 
                WEB3IfoDataAdapter.getWLimitEnableStatusDiv((IfoOrderUnit)l_orderUnit);

            //is�[��܂Œ���(IfoOrderUnit)
            boolean l_blnIsEveningSessionOrder = l_orderManager.isEveningSessionOrder((IfoOrderUnit)l_orderUnit);

            //1.32 createResponse()
            WEB3FuturesCloseMarginChangeInputResponse l_response =
                (WEB3FuturesCloseMarginChangeInputResponse)l_FRequest.createResponse();

            //1.33 �v���p�e�B�Z�b�g
            //���X�|���X.�����P���敪�ꗗ���戵�\�����P���敪�擾�̖߂�l
            l_response.orderPriceDivList = l_strHandlingPossibleOrderPriceDivs;
            
            String[] l_strExecutionConditions =new String[1];
            l_strExecutionConditions[0]=WEB3ExecutionConditionDef.NO_CONDITION;
            //���X�|���X.���s�����ꗗ
            if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_ifoOrderUnitRow.getOrderConditionType()))
            {
                l_response.execCondList = l_strWLimitExecutionConditionTypeList;
            }
            else if(l_ifoOrderUnitRow.getFirstOrderUnitIdIsNull()
                && !l_blnIsEveningSessionOrder
                && !WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_ifoOrderUnitRow.getOrderConditionType()))
            {
                l_response.execCondList = l_strHandlingPossibleExecConds;
            }
            else
            {
                l_response.execCondList = l_strExecutionConditions;
            }
 
            //���X�|���X.���������敪�ꗗ���戵�\���������敪�擾�̖߂�l
            l_response.expirationDateTypeList = l_strHandlingPossibleExpirationDateTypes;

            //���X�|���X.�L�������J�n����get�o����܂Œ����J�n���̖߂�l
            l_response.expirationStartDate = WEB3DateUtility.toDay(l_datOrderUntilDeadLineStartDay);

            //���X�|���X.�L�������ŏI����get�o����܂Œ����ŏI��<����ŏI���l��>�̖߂�l
            l_response.expirationEndDate = WEB3DateUtility.toDay(l_datOrderUntilDeadLineEndDay);

            //���X�|���X.�L���������j���ꗗ��get�����������j���ꗗ�̖߂�l
            l_response.holidayList = l_datExpirationDateHolidays;

            //���X�|���X.���������敪�ꗗ���戵�\���������擾�̖߂�l
            l_response.orderCondTypeList = l_strHandlingPossibleOrderConds;

            //���X�|���X.�v�w�l�p���s�����ꗗ��get�v�w�l�p���s�����ꗗ�i)�̖߂�l
            l_response.wlimitExecCondList = l_strWLimitExecutionConditionTypeList;

            //���X�|���X.����敪 = �����P��.����敪
            l_response.sessionType = l_ifoOrderUnitRow.getSessionType();
                
            //���X�|���X.����敪���i�ȉ��̂Ƃ���j
            //�����P��.�������="604"�i�敨�����ԍϒ����i���ԍρj�j => "5"
            //�����P��.�������="603"�i�敨�����ԍϒ����i���ԍρj�j => "6"
            if(OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE.equals(l_orderUnit.getOrderType()))
            {
                l_response.tradingType = WEB3IfoTradeTypeDef.CLOSE_SELL_CONTRACT;
            }
            if(OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE.equals(l_orderUnit.getOrderType()))
            {
                l_response.tradingType = WEB3IfoTradeTypeDef.CLOSE_BUY_CONTRACT;
            }

            //���X�|���X.����s�ꁁ�����P��.�s��R�[�h(SONAR)
            l_response.marketCode = l_ifoOrderUnitRow.getSonarMarketCode();

            //���X�|���X.�w����ʁ��敨OP����.�����Y�����R�[�h
            l_response.targetProductCode = l_productImpl.getUnderlyingProductCode();

            //���X�|���X.�������敨OP����.����
            IfoProductRow l_ifoProductRow = (IfoProductRow)l_productImpl.getDataSourceObject();
            l_response.delivaryMonth = l_ifoProductRow.getMonthOfDelivery();

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

            //���X�|���X.�������(�������\����)��getCurrentPriceTime()�̖߂�l
            l_response.currentPriceTime = l_tsCurrentPriceTime;

            //���X�|���X.���Ϗ����������P�ʁE���Ϗ���
            l_response.closingOrder = l_ifoOrderUnitRow.getClosingOrder();

            //���X�|���X.���ʖ��ׁ�create���ʖ���ByOrder�̖߂�l
            l_response.contractUnits = l_contractUnits;

            //���X�|���X.�������ʁ������P��.��������
            //���X�|���X.�������ʁ�if�����P��.���Ϗ��� != "0"(�����_��)�̏ꍇ�̂݁A�����P��.�������ʂ��Z�b�g
            double l_dblQuantity = l_ifoOrderUnitRow.getQuantity();
            if(Double.isNaN(l_dblQuantity))
            {
                l_dblQuantity = 0;
            }
            if (!WEB3ClosingOrderDef.RANDOM.equals(l_ifoOrderUnitRow.getClosingOrder()))
            {
                l_response.futOrderQuantity =WEB3StringTypeUtility.formatNumber(l_dblQuantity);
            }

            //���X�|���X.�����P���敪��(�����P��.isMarketOrder == true)�̏ꍇ"���s"�A �ȊO"�w�l"
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
            
            //���X�|���X.���s�������敨OP�f�[�^�A�_�v�^.get���s�����iPR�w�j(�����P��.���s����)
            l_response.execCondType = 
                WEB3IfoDataAdapter.getExecutionCondByPr(l_ifoOrderUnitRow.getExecutionConditionType());

            //���X�|���X.���������敪���敨OP�f�[�^�A�_�v�^.get���������敪(�����P��)()�̖߂�l
            //���X�|���X.�����L���������敨OP�f�[�^�A�_�v�^.get���������敪(�����P��)()�̖߂�l��
            //�o����܂Œ����̏ꍇ�̂݁A�����P��.�������������Z�b�g�B
            //�ȊO�̏ꍇ�Anull���Z�b�g
            String l_strExpirationDateType =
                WEB3IfoDataAdapter.getExpirationDateType((IfoOrderUnit)l_orderUnit);
            l_response.expirationDateType = l_strExpirationDateType;
            if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_strExpirationDateType))
            {
                l_response.expirationDate = l_ifoOrderUnitRow.getExpirationDate();
            }
            else
            {
                l_response.expirationDate = null;
            }

            //���X�|���X.���������敪�������P��.��������
            l_response.orderCondType = l_ifoOrderUnitRow.getOrderConditionType();

            //�������P��.�����������P�i�t�w�l�j�̏ꍇ��
            if(WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_ifoOrderUnitRow.getOrderConditionType()))
            {
                //���X�|���X.�t�w�l�p���������P���������P��.�t�w�l��l
                l_response.stopOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getStopOrderPrice());

                //���X�|���X.�t�w�l�p�����������Z�q�������P��.�����������Z�q
                l_response.stopOrderCondOperator = l_ifoOrderUnitRow.getOrderCondOperator();
            }
            //�������P��.�����������Q�iW�w�l�j�̏ꍇ��
            else if(WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_ifoOrderUnitRow.getOrderConditionType()))
            {
                //���X�|���X.W�w�l�p���������P���������P��.�t�w�l��l
                l_response.wlimitOrderCondPrice  = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getStopOrderPrice());

                //���X�|���X.W�w�l�p�����������Z�q�������P��.�����������Z�q
                l_response.wlimitOrderCondOperator = l_ifoOrderUnitRow.getOrderCondOperator();

                // ���X�|���X.W�w�l�p�����P���敪��if �����P��.(W�w�l)�����w�l��0 then "0"(���s) else "1"(�w�l)
                // ���X�|���X.W�w�l�p�����P����if �����P��.(W�w�l)�����w�l != "0"(�w�l)�̏ꍇ�̂݁A�����P��.(W�w�l)�����w�l���Z�b�g
                if(l_ifoOrderUnitRow.getWLimitPrice() == 0)
                {
                    l_response.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                    l_response.wLimitPrice = null;
                }
                else
                {
                    l_response.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                    l_response.wLimitPrice = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getWLimitPrice());
                }
                
                //���X�|���X.W�w�l�p���s�������敨OP�f�[�^�A�_�v�^.get���s�����iPR�w�j(�����P��.�iW�w�l�j���s����)
                l_response.wlimitExecCondType = WEB3IfoDataAdapter.getExecutionCondByPr(l_ifoOrderUnitRow.getWLimitExecCondType());
            }

            //���X�|���X.W�w�l�p�L����ԋ敪���敨OP�f�[�^�A�_�v�^.get�v�w�l�p�L����ԋ敪()�̖߂�l
            l_response.wlimitEnableStatusDiv = l_strWLimitEnableStatusDiv;
            
            //���X�|���X.W�w�l�p�֑ؑO�����P�����敨OP�f�[�^�A�_�v�^.get�v�w�l�p�֑ؑO�����P��(�����P��)
            l_response.wlimitBefChgLimitPrice = WEB3IfoDataAdapter.getWLimitBefSwitchPrice((IfoOrderUnit)l_orderUnit);
            
            //���X�|���X.W�w�l�p�֑ؑO���s�������敨OP�f�[�^�A�_�v�^.get�v�w�l�p�֑ؑO���s����(�����P��)
            l_response.wlimitBefChgExecCondType = WEB3IfoDataAdapter.getWLimitBefSwitchExecCondType((IfoOrderUnit)l_orderUnit);
            
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
            
            //���X�|���X.�T�Z���ϑ��v�������P��.�T�Z��n����i�T�Z���ϑ��v�j
            l_response.estimatedSettleIncome = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getEstimatedPrice());

            //���X�|���X.����I���x��������get�s��ǌx���w���̖߂�l
            l_response.messageSuspension = l_strTradeCloseSuspensions;

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (NotFoundException l_ex)
        {
            log.error("�f�[�^�s�����G���[", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (get�����Ώے����P��)<BR>
     * �����Ώۂ̒����P�ʃI�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �������̏ڍׂ̓V�[�P���X�}<BR>
     * �u�敨�����ԍϓ���/�i�敨�����ԍϓ��́j���͉�ʕ\���f�[�^�擾�v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * @@return IfoOrderUnit
     * @@throws WEB3BaseException
     */
    protected IfoOrderUnit getChangeOrderUnit(WEB3FuturesCloseMarginChangeInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME= "getChangeOrderUnit(WEB3FuturesCloseMarginChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3FuturesOrderManagerImpl l_orderManager =
            (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();
        long l_lngID = Long.parseLong(l_request.id);

        //getOrderUnits(����ID : long)
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngID);

        log.exiting(STR_METHOD_NAME);
        return (IfoOrderUnit)l_orderUnits[0];
    }

    /**
     * (validate���������\)<BR>
     * �����Ώے������A�����\�ȏ�Ԃ��ǂ������`�F�b�N����B <BR>
     * <BR>
     * �������̏ڍׂ̓V�[�P���X�}<BR>
     * �u�敨�����ԍϓ���/�i�敨�����ԍϓ��́j���͉�ʕ\���f�[�^�擾�v�Q��<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �敨OP�����P�ʃI�u�W�F�N�g�B<BR>
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
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                l_ex.getValidationResult().getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (create���ʖ���ByOrder)<BR>
     * �����̒����P�ʂɊ֘A���錚�ʖ��ׂ̈ꗗ���쐬����B <BR>
     * <BR>
     * �������̏ڍׂ̓V�[�P���X�}<BR>
     * �u�敨�����ԍϓ���/�i�敨�����ԍϓ��́j���͉�ʕ\���f�[�^�擾�v�Q��<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �敨OP�����P�ʃI�u�W�F�N�g�B<BR>
     * @@return WEB3FuturesOptionsContractUnit[]
     * @@throws WEB3BaseException
     */
    protected WEB3FuturesOptionsContractUnit[] createContractUnitByOrder(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME= "createContractUnitByOrder(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3FuturesOrderManagerImpl l_orderManager =
            (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();

        //create���ʖ���ByOrder(long)
        WEB3FuturesOptionsContractUnit[] l_contractUnits =
            l_orderManager.createContractUnitByOrder(l_orderUnit.getOrderId());

        log.exiting(STR_METHOD_NAME);
        return l_contractUnits;
    }
}
@
