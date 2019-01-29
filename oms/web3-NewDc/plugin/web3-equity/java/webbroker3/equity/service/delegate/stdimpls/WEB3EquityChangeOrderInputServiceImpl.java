head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityChangeOrderInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������������������̓T�[�r�XImpl(WEB3EquityChangeOrderInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/13 ���j (���u) �V�K�쐬
                   2006/07/04 ���� (���u) �d�l�ύX�Ǘ�No.937
                   2006/08/29 �ęԍg (���u) �d�l�ύX���f��970
                   2006/11/06 ������ (���u) �d�l�ύX���f��1002
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BuySellTypeDef;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityClientRequestService;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityProductQuote;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3EquityTradingModule;
import webbroker3.equity.message.WEB3EquityChangeInputRequest;
import webbroker3.equity.message.WEB3EquityChangeInputResponse;
import webbroker3.equity.service.delegate.WEB3EquityChangeOrderInputService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �i�������������������̓T�[�r�XImpl�j�B<BR>
 * <BR>
 * �������������������̓T�[�r�X�����N���X
 * @@version 1.0
 */
public class WEB3EquityChangeOrderInputServiceImpl
    extends WEB3EquityClientRequestService
    implements WEB3EquityChangeOrderInputService
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityChangeOrderInputServiceImpl.class);

    /**
     * @@roseuid 40A076E601E7
     */
    public WEB3EquityChangeOrderInputServiceImpl()
    {

    }

    /**
     * (execute)<BR>
     * �������������������̓T�[�r�X�����{����B<BR>
     * <BR>
     * this.get�������͉��()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response =
            this.getChangeInputScreen(
                (WEB3EquityChangeInputRequest)l_request);
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�������͉��)�B<BR>
     * �������������������͉�ʕ\�����������{����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�������������������́jget�������͉�ʁv�Q�ƁB<BR>
     * @@param l_chgInRequest - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B
     * @@return WEB3EquityChangeInputResponse
     * @@throws WEB3BaseException
     */
    protected WEB3EquityChangeInputResponse getChangeInputScreen(
        WEB3EquityChangeInputRequest l_chgInRequest)
        throws WEB3BaseException
    {
         
        final String STR_METHOD_NAME = "getChangeOrderInputScreen(WEB3EquityChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3EquityTradingModule l_tradingModule
            = (WEB3EquityTradingModule) l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager
            = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
        WEB3GentradeFinObjectManager l_finObjectManager
            = (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();

        //--------------------
        //���N�G�X�g.validate
        //--------------------
        l_chgInRequest.validate();

        //--------------------
        //get�����Ώے����P��
        //--------------------
        EqTypeOrderUnit l_orderUnit = this.getChangeOrderUnit(l_chgInRequest);
        EqtypeOrderUnitRow l_orderUnitRow
            = (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();

        //--------------------
        //reset�s��R�[�h
        //--------------------
        WEB3GentradeMarket l_market = null;
        try {
            l_market = (WEB3GentradeMarket) l_finObjectManager
                .getMarket(l_orderUnitRow.getMarketId());
        }
        catch (NotFoundException l_nfe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�s�ꂪ������܂���B�s��ID->[" + l_orderUnitRow.getMarketId() + "]");
        }
        WEB3GentradeTradingTimeManagement.resetMarketCode(l_market.getMarketCode());

        //--------------------
        //������t�\�����`�F�b�N����
        //--------------------
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //--------------------
        //�����`�F�b�N�̐���
        //--------------------
        WEB3GentradeOrderValidator l_gentradeOrderValidator
            = (WEB3GentradeOrderValidator) GtlUtils.getCommonOrderValidator();

        //--------------------
        //get�⏕����
        //--------------------
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        log.debug("get�⏕�����i�⏕����ID=[" + l_subAccount.getSubAccountId() + "]�j");

        //--------------------
        //validate����\�ڋq
        //--------------------
        OrderValidationResult l_validationResult
            = l_gentradeOrderValidator.validateSubAccountForTrading(l_subAccount);
        if (l_validationResult.getProcessingResult().isFailedResult() )
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //--------------------
        //get�s��ǌx���s��
        //--------------------
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
        String[] l_tradeCloseMarkets = WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
            l_branch,
            ProductTypeEnum.EQUITY,
            WEB3MarginTradingDivDef.DEFAULT);

        //--------------------
        //get���t�\�z
        //--------------------
        Double l_dblEquityTradingPower = this.getEquityTradingPower(l_orderUnit, l_subAccount);

        EqTypeProduct l_product = (EqTypeProduct)l_orderUnit.getProduct();
        String l_strProductCode = l_product.getProductCode();
        String l_strInstitutionCode = l_market.getInstitution().getInstitutionCode();
        
        //--------------------
        //validate�������
        //--------------------
        WEB3EquityTradedProduct l_tradedProduct
            = (WEB3EquityTradedProduct) l_orderManager.validateTradedProduct(
                l_product, l_market);
        
        //--------------------
        //validate�戵�\�s��
        //--------------------
        l_orderManager.validateHandlingMarket(l_branch, l_tradedProduct);
        
        //--------------------
        //get�����P���敪
        //--------------------
        String[] l_orderPriceDivList = null;
        l_orderPriceDivList = l_orderManager.getOrderPriceDivs(l_branch, l_tradedProduct);

        //--------------------
        //�戵�\���������̐���
        //--------------------
        WEB3GentradeHandlingOrderCond l_gentradeHandingOrderCond    = null;
        String l_strMarketCode = l_market.getMarketCode();

        l_gentradeHandingOrderCond = new WEB3GentradeHandlingOrderCond(
            l_strInstitutionCode,
            ProductTypeEnum.EQUITY,
            WEB3FuturesOptionDivDef.DEFAULT,
            WEB3MarginTradingDivDef.DEFAULT,
            l_strMarketCode);

        //--------------------
        //is�o����܂Œ����P��
        //--------------------
        boolean l_blnIsCarriedOrder
            = l_orderManager.isCarriedOrderUnit(l_orderUnit);

        //--------------------
        //is�o����܂Œ����戵�\
        //--------------------
        boolean l_blnIsHandingCarriedOrderPossible = false;
        if (l_blnIsCarriedOrder)
        {
            l_blnIsHandingCarriedOrderPossible
                = l_gentradeHandingOrderCond.isOrderUntilDeadLinePossibleHandling();
        }

        //--------------------
        //����t���[�F�o����܂Œ����戵�\ ���� is�o����܂Œ����P�ʂ̏ꍇ
        //--------------------
        Date l_datOrderUntilDeadLineStartDate       = null;     //�o����܂Œ����J�n��
        Date l_datOrderUntilDeadLineEndDate         = null;     //�o����܂Œ����I����
        Date[] l_datOrderUntilDeadLineHolidayList   = null;     //�����������j���ꗗ
        if (l_blnIsCarriedOrder && l_blnIsHandingCarriedOrderPossible)
        {
            // get�o����܂Œ���from���t
            Date l_datFirstOrderUnitBizDate =
                this.getCarriedOrderFromDate(l_orderUnit);
            // get�o����܂Œ����ŏI��
			l_datOrderUntilDeadLineEndDate = l_gentradeHandingOrderCond.
				getOrderUntilDeadLineEndDay(l_datFirstOrderUnitBizDate);
			// get�o����܂Œ����J�n��
            l_datOrderUntilDeadLineStartDate = l_gentradeHandingOrderCond
                .getOrderUntilDeadLineStartDay(null, l_datOrderUntilDeadLineEndDate);
            // get�����������j���ꗗ
            l_datOrderUntilDeadLineHolidayList = l_gentradeHandingOrderCond
                .getExpirationDateHoliday(l_datOrderUntilDeadLineStartDate);
        }

        //--------------------
        //validate���������\
        //--------------------
        this.validateOrderForChangeability(l_orderUnit);

        //--------------------
        //is�C���T�C�_�[�x���\��
        //--------------------
        long l_lngProductId = l_orderUnit.getProduct().getProductId();
        boolean l_blnViewInsiderWarningMessage
            = l_orderManager.isInsiderMessageSuspension(l_subAccount, l_lngProductId);

        //--------------------
        //validate�ڋq�����ʎ����~
        //--------------------
        l_orderManager.validateAccountProductOrderStop(
            l_subAccount, l_lngProductId, l_orderUnit.getOrderType());
        
		//--------------------
		//validate�ǌ���������t�\
		//������ԊǗ��Dget������ �� �����P�ʁD�������̏ꍇ�̂݃R�[������B
		//--------------------    
		Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
		Date l_datOrderBizDate = 
			WEB3DateUtility.getDate(
				l_orderUnitRow.getBizDate(), 
				WEB3GentradeTimeDef.DATE_FORMAT_YMD);

		if (WEB3DateUtility.compare(l_datBizDate, l_datOrderBizDate) > 0)
		{
			WEB3GentradeTradingTimeManagement
				.validateTradeCloseChangeOrCancel(ProductTypeEnum.EQUITY);
		}

        //--------------------
        //�戵�\���s�����擾
        //--------------------
        String[] l_handingPossibleExecCond = null;
        l_handingPossibleExecCond = l_gentradeHandingOrderCond.getHandlingPossibleExecCond();
        
        //--------------------
        //�戵�\�l�i�����擾
        //--------------------
        String[] l_handingPossiblePriceCond = null;
        l_handingPossiblePriceCond = l_gentradeHandingOrderCond.getHandlingPriceCond();
        
        //get�v�w�l�p���s�����ꗗ(String[], EqTypeOrderUnit)
        //�v�w�l�p�̎��s�����ꗗ���擾����B  
        //[get�v�w�l�p���s�����ꗗ()�Ɏw�肷�����]  
        //���s�����ꗗ�F�@@�戵�\���s�����擾()�̖߂�l  
        //�����P�ʁF�@@�����P��
        String[] l_strGetWLimitExecutionConditionTypeList =
            WEB3EquityDataAdapter.getWLimitExecutionConditionTypeList(
                l_handingPossibleExecCond,
                l_orderUnit);

        //--------------------
        //get�T�Z�뉿�P��
        //--------------------
        String l_strEstimatedBookPrice =
            this.getEstimatedBookPrice(l_orderUnit, l_subAccount);
        
        //--------------------
        //�������擾
        //--------------------
        double l_dblCurrentPrice = 0;
        Timestamp l_tsCurrentPriceTime = null;
        double l_dblChange = 0;
        String l_strCurrentPriceDiv = null;
        
        String l_strBoardCurrentPrice = null;                      
        Timestamp l_tsBoardCurrentPriceTime = null;               
        String l_strBoardCurrentPriceDiv = null;                   
        String l_strBoardChange = null;                      
        String l_strVolume = null;                              
        Timestamp l_tsVolumeTime = null;                          
        String l_strAskPriceTitle = null;                          
        String l_strAskPrice = null;                            
        Timestamp l_tsAskPriceTime = null;                         
        String l_strBidPriceTitle = null;                    
        String l_strbBidPrice = null;        
        Timestamp l_tsBidPriceTime = null;            
        String l_strBasePrice = null;            
        
        //�����A���ݒl�����A�O������擾
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
        WEB3EquityProductQuote l_productQuote =
            l_productManager.getDisplayEquityProductQuote(
                l_tradedProduct,
                l_subAccount);
        l_dblCurrentPrice = l_productQuote.getQuote();
        l_tsCurrentPriceTime = l_productQuote.getQuoteTime();
        l_dblChange = l_productQuote.getComparedPreviousDay();
        l_strCurrentPriceDiv = l_productQuote.getQuoteTypeDiv();
        l_strBoardCurrentPrice = l_productQuote.getBoardCurrentPrice(); 
        l_tsBoardCurrentPriceTime = l_productQuote.getBoardCurrentPriceTime(); 
        l_strBoardCurrentPriceDiv = l_productQuote.getBoardCurrentPriceDiv(); 
        l_strBoardChange = l_productQuote.getBoardChange(); 
        l_strVolume = l_productQuote.getVolume();  
        l_tsVolumeTime = l_productQuote.getVolumeTime();  
        l_strAskPriceTitle = l_productQuote.getAskPriceTitle(); 
        l_strAskPrice = l_productQuote.getAskPrice(); 
        l_tsAskPriceTime = l_productQuote.getAskPriceTime(); 
        l_strBidPriceTitle = l_productQuote.getBidPriceTitle();
        l_strbBidPrice = l_productQuote.getBidPrice();  
        l_tsBidPriceTime = l_productQuote.getBidPriceTime(); 
        l_strBasePrice = l_productQuote.getBasePrice(); 
        
        //--------------------
        //createResponse
        //--------------------
        WEB3EquityChangeInputResponse l_response
            = (WEB3EquityChangeInputResponse) l_chgInRequest.createResponse();
        
        //--------------------
        //�v���p�e�B�Z�b�g
        //--------------------
        
        //�������������͋��ʃ��X�|���X��
        
        l_response.orderPriceDivList = l_orderPriceDivList;
        if (!l_blnIsCarriedOrder &&
            WEB3OrderingConditionDef.DEFAULT.equals(l_orderUnitRow.getOrderConditionType()))
        {
            l_response.priceCondList = l_handingPossiblePriceCond;
        }
        else
        {
            l_response.priceCondList = new String[]{WEB3PriceConditionDef.DEFAULT};
        }

        //(*1)
        //�����Ώے������t�w�l�����̏ꍇ�A�u1:�����Ȃ��v���Z�b�g�B
        //�����Ώے�����W�w�l�����̏ꍇ�Aget�v�w�l�p���s�����ꗗ�i)�̖߂�l���Z�b�g�B
        //�����Ώے������o����܂Œ���(*2)�̏ꍇ�A�g�����������}�l�[�W��.get���s����
        //�iSONAR�j(�����P��.���s����)���Z�b�g�B�i���̎��s�����ւ̒����͕s�B�j
        //�����Ώے������o����܂Œ���(*2)�̏ꍇ�A�戵�\��������.�戵�\���s�����擾( )�̖߂�lList���Z�b�g�B

        //(*2)
        //�����Ώے������o����܂Œ������ǂ����̔�����@@�F
        //�g�����������}�l�[�W��.is�o����܂Œ����P��(�����P��)��true�̏ꍇ�́A�o����܂Œ����B
        //false�̏ꍇ�́A�o����܂Œ����ł͂Ȃ��B

        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(
        	l_orderUnitRow.getOrderConditionType()))
        {
            //���s�����ꗗ�F�@@�����\�Ȍ��̂�(*1)���Z�b�g�B
			l_response.execCondList = 
				new String[]{
					WEB3ExecutionConditionDef.NO_CONDITION};
        }
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(
            l_orderUnitRow.getOrderConditionType()))
        {
            //���s�����ꗗ�F�@@�����\�Ȍ��̂�(*1)���Z�b�g�B
            l_response.execCondList = l_strGetWLimitExecutionConditionTypeList;
        }
        else if (l_blnIsCarriedOrder)
        {
            //���s�����ꗗ�F�@@�����\�Ȍ��̂�(*1)���Z�b�g�B
            String[] l_wkExecCond = {
                l_orderManager.getExecutionConditionTypeSonar(
                l_orderUnitRow.getExecutionConditionType())};
            l_response.execCondList = l_wkExecCond;
        }
        else
        {
            //���s�����ꗗ�F�@@�����\�Ȍ��̂�(*1)���Z�b�g�B
            l_response.execCondList = l_handingPossibleExecCond;
        }

        //W�w�l�p���s�����ꗗ�F�@@getW�w�l�p���s�����ꗗ()�̖߂�l���Z�b�g�B
        l_response.wlimitExecCondList = l_strGetWLimitExecutionConditionTypeList;

        if (l_blnIsCarriedOrder && l_blnIsHandingCarriedOrderPossible)
        {

            l_response.expirationStartDate = l_datOrderUntilDeadLineStartDate;
            l_response.expirationEndDate   = l_datOrderUntilDeadLineEndDate;
            l_response.holidayList         = l_datOrderUntilDeadLineHolidayList;
        }
        else if (l_blnIsCarriedOrder && !l_blnIsHandingCarriedOrderPossible)
        { 
            l_response.expirationStartDate = l_orderUnitRow.getExpirationDate();
            l_response.expirationEndDate   = l_orderUnitRow.getExpirationDate();
            l_response.holidayList         = null;
        }
        else
        {
            l_response.expirationStartDate = null;
            l_response.expirationEndDate   = null;
            l_response.holidayList         = null;
        }
        
        l_response.messageSuspension   = l_tradeCloseMarkets;
        l_response.insiderWarningFlag  = l_blnViewInsiderWarningMessage;

        l_response.currentPriceDiv     = l_strCurrentPriceDiv;
        l_response.currentPrice        = WEB3StringTypeUtility.formatNumber(l_dblCurrentPrice);
        l_response.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_dblChange);
        l_response.currentPriceTime    = l_tsCurrentPriceTime;
        
        //���ݒl�F�@@�擾�������������������.get���ݒl( )�̖߂�l���Z�b�g
        l_response.boardCurrentPrice = l_strBoardCurrentPrice;
        
        //���ݒl�����F�@@�擾�������������������.get���ݒl����( )�̖߂�l���Z�b�g
        l_response.boardCurrentPriceTime = l_tsBoardCurrentPriceTime; 
        
        //���ݒl�敪�F�@@�擾�������������������.get���ݒl�敪( )�̖߂�l���Z�b�g           
        l_response.boardCurrentPriceDiv = l_strBoardCurrentPriceDiv; 
        
        //���ݒl�O����F�@@�擾�������������������.get���ݒl�O����( )�̖߂�l���Z�b�g
        l_response.boardChange = l_strBoardChange;
        
        //�o�����F�@@�擾�������������������.get�o����( )�̖߂�l���Z�b�g
        l_response.volume = l_strVolume;
        
        //�o���������F�@@�擾�������������������.get�o��������( )�̖߂�l���Z�b�g
        l_response.volumeTime = l_tsVolumeTime;
        
        //���C�z�l�^�C�g���敪�F�@@�擾�������������������.get���C�z�l�^�C�g���敪( )�̖߂�l���Z�b�g
        l_response.askPriceTitle = l_strAskPriceTitle;
        
        //���C�z�l�F�@@�擾�������������������.get���C�z�l( )�̖߂�l���Z�b�g
        l_response.askPrice = l_strAskPrice; 
        
        //���C�z�l�����F�@@�擾�������������������.get���C�z�l����( )�̖߂�l���Z�b�g
        l_response.askPriceTime = l_tsAskPriceTime;
        
        //���C�z�l�^�C�g���敪�F�@@�擾�������������������.get���C�z�l�^�C�g���敪( )�̖߂�l���Z�b�g
        l_response.bidPriceTitle = l_strBidPriceTitle;
        
        //���C�z�l�F�@@�擾�������������������.get���C�z�l( )�̖߂�l���Z�b�g
        l_response.bidPrice = l_strbBidPrice;
        
        //���C�z�l�����F�@@�擾�������������������.get���C�z�l����( )�̖߂�l���Z�b�g
        l_response.bidPriceTime = l_tsBidPriceTime;
        
        //��l�i�F�@@�擾�������������������.get��l�i( )�̖߂�l���Z�b�g
        l_response.basePrice = l_strBasePrice;

        //���������������������̓��X�|���X��
        
        //���t�\�z�̐ݒ�
        if (l_dblEquityTradingPower != null)
        {
            l_response.tradingPower =
                WEB3StringTypeUtility.formatNumber(l_dblEquityTradingPower.doubleValue());
        }
        else
        {
            l_response.tradingPower = null;
        }
        l_response.productCode      = l_strProductCode;
        EqtypeProductRow l_productRow = (EqtypeProductRow)l_tradedProduct.getProduct().getDataSourceObject();
        l_response.productName = l_productRow.getStandardName();
        l_response.marketCode       = l_strMarketCode;

        //�ŋ敪�̐ݒ�
        l_response.taxType = WEB3EquityDataAdapter.getTaxType(l_orderUnit.getTaxType());
        
        //�����敪�̐ݒ�
        if (OrderTypeEnum.EQUITY_BUY.equals(l_orderUnit.getOrderType()))
        {
            l_response.dealingType = WEB3BuySellTypeDef.BUY;
        }
        else if  (OrderTypeEnum.EQUITY_SELL.equals(l_orderUnit.getOrderType()))
        {
            l_response.dealingType = WEB3BuySellTypeDef.SELL;
        }
        else{
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        l_response.orderQuantity    = WEB3StringTypeUtility.formatNumber(l_orderUnit.getQuantity());
        l_response.partContQuantity = WEB3StringTypeUtility.formatNumber(l_orderUnit.getExecutedQuantity());

        //�T�Z�뉿�P���̐ݒ�
        l_response.estimatedBookPrice = l_strEstimatedBookPrice;
        
        //�����P���敪�̐ݒ�
        if (l_orderUnit.isMarketOrder())
        {
            l_response.orderPriceDiv    = WEB3OrderPriceDivDef.MARKET_PRICE; 
        }
        else
        {
            l_response.orderPriceDiv    = WEB3OrderPriceDivDef.LIMIT_PRICE; 
        }
        
        if (!l_orderUnit.isMarketOrder())
        {
            l_response.limitPrice   = WEB3StringTypeUtility.formatNumber(l_orderUnit.getLimitPrice());
        }
        l_response.priceCondType    = l_orderUnitRow.getPriceConditionType();
        l_response.execCondType     = l_orderManager
            .getExecutionConditionTypeSonar(l_orderUnitRow.getExecutionConditionType());
        
        if (l_blnIsCarriedOrder)
        { 
            l_response.expirationDateType = WEB3OrderExpirationDateTypeDef.CARRIED_ORDER;
            l_response.expirationDate     = WEB3DateUtility.toDay(l_orderUnitRow.getExpirationDate());
        }
        else
        {
            l_response.expirationDateType = WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
            l_response.expirationDate     = null;
        }
        
        String l_strOrderCondType = l_orderUnitRow.getOrderConditionType();
        l_response.orderCondType                    = l_strOrderCondType;
        
        //�t�w�l�̏ꍇ�̂݃Z�b�g
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderCondType))
        {
            l_response.stopOrderCondPrice       = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());
            l_response.stopOrderCondOperator    = l_orderUnitRow.getOrderCondOperator();
        }
        
        //�v�w�l�̏ꍇ�̂݃Z�b�g
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderCondType))
        {
            l_response.wlimitOrderCondPrice     = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());
            l_response.wlimitOrderCondOperator  = l_orderUnitRow.getOrderCondOperator();

            //W�w�l�p���s�����F�@@�����P��.����������2�iW�w�l�j�̏ꍇ�̂݁A�g�����������}�l�[�W��.get���s����
            //�iSONAR�j(�����P��.�iW�w�l�j���s�����j�̖߂�l�B
            l_response.wlimitExecCondType =
                l_orderManager.getExecutionConditionTypeSonar(
                    l_orderUnitRow.getWLimitExecCondType());

            double l_dblWLimitPrice = l_orderUnitRow.getWLimitPrice();
            if (Double.isNaN(l_dblWLimitPrice) || l_dblWLimitPrice == 0)
            {
                l_response.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                l_response.wLimitPrice = null;
            }
            else
            {
                l_response.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                l_response.wLimitPrice = WEB3StringTypeUtility.formatNumber(l_dblWLimitPrice);
            }
        }

        //W�w�l�p�L����ԋ敪�F�@@�����f�[�^�A�_�v�^.getW�w�l�p�L����ԋ敪(�����P��)�̖߂�l�B
        l_response.wlimitEnableStatusDiv =
            WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_orderUnit);

        //W�w�l�p�֑ؑO�����P���F�@@�����f�[�^�A�_�v�^.getW�w�l�p�֑ؑO�����P��(�����P��)�̖߂�l�B
        l_response.wlimitBefChgLimitPrice =
            WEB3EquityDataAdapter.getWLimitBefSwitchPrice(l_orderUnit);

        //W�w�l�p�֑ؑO���s�����F�@@�����f�[�^�A�_�v�^.getW�w�l�p�֑ؑO���s����(�����P��)�̖߂�l�B
        l_response.wlimitBefChgExecCondType =
            WEB3EquityDataAdapter.getWLimitBefSwitchExecCondType(l_orderUnit);

        //�����������敪�F�@@�����P��.����������
        l_response.orgOrderCondType = l_orderUnitRow.getOrgOrderConditionType();

        //�����������P���F�@@�����P��.���t�w�l��l
        if (l_orderUnitRow.getOrgStopOrderPriceIsNull())
        {
            l_response.orgOrderCondPrice = null;
        }
        else
        {
            l_response.orgOrderCondPrice =
                WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getOrgStopOrderPrice());
        }

        //�������������Z�q�F�@@�����P��.�������������Z�q
        l_response.orgOrderCondOperator = l_orderUnitRow.getOrgOrderCondOperator();

        //��W�w�l�p�����P���敪�F�@@�����f�[�^�A�_�v�^.get��W�w�l�p�����P���敪�i�����P�ʁj�̖߂�l�B
        l_response.orgWlimitOrderPriceDiv =
            WEB3EquityDataAdapter.getOrgWLimitOrderPriceDiv(l_orderUnit);

        //��W�w�l�p�����P���F�@@��W�w�l�p�����P���敪��"�w�l"�̏ꍇ�̂݁A
        //�����f�[�^�A�_�v�^.get��W�w�l�p�����P���i�����P�ʁj�̖߂�l�B
        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_response.orgWlimitOrderPriceDiv))
        {
            l_response.orgWlimitPrice =
                WEB3EquityDataAdapter.getOrgWLimitOrderPrice(l_orderUnit);
        }

        //��W�w�l�p���s�����F�@@�����f�[�^�A�_�v�^.get��W�w�l�p���s�����i�����P�ʁj�̖߂�l�B
        l_response.orgWlimitExecCondType =
            WEB3EquityDataAdapter.getOrgWLimitExecCondType(l_orderUnit);

        //�T�Z��n����F�@@�����P��.�T�Z��n���
        l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getEstimatedPrice());

        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�����Ώے����P��)<BR>
     * �����Ώۂ̒����P�ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �g�����������}�l�[�W��.getOrderUnits(���N�G�X�g.ID)�Ŏ擾���������P�ʃI�u�W�F�N�g�̂����A<BR>
     * �ŏ��̗v�f��ԋp����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B
     * @@return EqTypeOrderUnit
     * @@throws WEB3BaseException
     */
    protected EqTypeOrderUnit getChangeOrderUnit(
        WEB3EquityChangeInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getChangeOrderUnit(WEB3EquityChangeInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        long l_lngOrderId = Long.parseLong(l_request.id);
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        
        log.exiting(STR_METHOD_NAME);
        return l_orderUnit;
    }
    
    /**
     * (get���t�\�z)<BR>
     * ���t�\�z���擾����B<BR>
     * <BR>
     * �P�j�@@�����̒����P��.getSide()��"��"�̏ꍇ�́Anull��ԋp����B<BR>
     * �@@�@@�@@�ȊO�A�ȉ��̏������s���B<BR>
     * <BR>
     * �Q�j�@@����]�̓T�[�r�X.get�������t�\�z(�����̕⏕����)��delegate����B<BR>
     * @@param l_orderUnit - (���������P��)<BR>
     * ���������P�ʃI�u�W�F�N�g�B
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B
     * @@return Double
     * @@throws WEB3BaseException
     */
    protected Double getEquityTradingPower(
        EqTypeOrderUnit l_orderUnit,
        WEB3GentradeSubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getEquityTradingPower(EqTypeOrderUnit, WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (!SideEnum.BUY.equals(l_orderUnit.getSide()))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            WEB3TPTradingPowerService l_tradingPowerService =
                (WEB3TPTradingPowerService)Services.getService(
                    WEB3TPTradingPowerService.class);
            double l_dblEquityTradingPower =
                l_tradingPowerService.getEquityTradingPower(l_subAccount);
            
            log.exiting(STR_METHOD_NAME);
            return new Double(l_dblEquityTradingPower);
        }
    }
    
    /**
     * (get�o����܂Œ���from���t)<BR>
     * �����L�������擾�Ɏg�p����A�o����܂Œ���from���t��ԋp����B<BR>
     * <BR>
     * �P�j�@@�g�����������}�l�[�W��.get���񒍕��̒����P��(�����̒����P��)���R�[������B<BR>
     * <BR>
     * �Q�j�@@�擾���������P��.��������ԋp����B<BR>
     * @@param l_orderUnit - (���������P��)<BR>
     * ���������P�ʃI�u�W�F�N�g�B
     * @@return Date
     * @@throws WEB3BaseException
     */
    protected Date getCarriedOrderFromDate(EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCarriedOrderFromDate(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        EqTypeOrderUnit l_firstOrderUnit =
            l_orderManager.getFirstOrderUnit(l_orderUnit);
        EqtypeOrderUnitRow l_firstOrderUnitRow =
            (EqtypeOrderUnitRow)l_firstOrderUnit.getDataSourceObject();
        Date l_datBizDate = null;
        try
        {
            l_datBizDate =
                GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().parse(
                    l_firstOrderUnitRow.getBizDate());
        }
        catch (ParseException l_pex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_pex.getMessage(),
                l_pex);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_datBizDate;
    }
    
    /**
     * (validate���������\)<BR>
     * �����Ώے������A�����\�ȏ�Ԃ��ǂ������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�����̒����P��.getOrder()�ɂ��A�����I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �Q�j�@@�g�����������}�l�[�W��.validate���������\���(�擾���������I�u�W�F�N�g)��delegate����B<BR>
     * @@param l_orderUnit - (���������P��)<BR>
     * ���������P�ʃI�u�W�F�N�g�B
     * @@throws WEB3BaseException
     */
    protected void validateOrderForChangeability(EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderForChangeability(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        l_orderManager.validateOrderForChangeability(l_orderUnit.getOrder());
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get�T�Z�뉿�P��)<BR>
     * �T�Z�뉿�P����ԋp����B<BR>
     * <BR>
     * �P�j�@@�����̒����P��.getSide()��"��"�̏ꍇ�́Anull��ԋp����B<BR>
     * �@@�@@�@@�ȊO�A�ȉ��̏������s���B<BR>
     * <BR>
     * �Q�j�@@�����v�Z�T�[�r�X.calc�T�Z�뉿�P��(�����̒����P��.����ID, �����̕⏕����,<BR>
     * �@@�@@�@@�����̒����P��.�ŋ敪)���R�[�����A<BR>
     * �@@�@@�@@�߂�l��ԋp����B<BR>
     * @@param l_orderUnit - (���������P��)<BR>
     * ���������P�ʃI�u�W�F�N�g�B
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B
     * @@return String
     * @@throws WEB3BaseException
     */
    protected String getEstimatedBookPrice(
        EqTypeOrderUnit l_orderUnit,
        WEB3GentradeSubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getEstimatedBookPrice(EqTypeOrderUnit, WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (!SideEnum.SELL.equals(l_orderUnit.getSide()))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityBizLogicProvider l_bizLogicProvider =
                (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
            double l_dblEstimatedBookPrice =
                l_bizLogicProvider.calcEstimatedBookPrice(
                    l_orderUnit.getProduct().getProductId(),
                    l_subAccount,
                    l_orderUnit.getTaxType());
            String l_strEstimatedBookPrice =
                WEB3StringTypeUtility.formatNumber(l_dblEstimatedBookPrice);
            
            log.exiting(STR_METHOD_NAME);
            return l_strEstimatedBookPrice;
        }
    }
}
@
