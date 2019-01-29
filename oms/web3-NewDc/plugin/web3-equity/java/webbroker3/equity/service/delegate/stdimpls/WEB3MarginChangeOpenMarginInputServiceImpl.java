head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginChangeOpenMarginInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p��������V�K�����̓T�[�r�XImpl(WEB3MarginChangeOpenMarginInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 Ḗ@@��(���u) �V�K�쐬 
                   2006/11/24 ����(���u) ���f��No.1009
                   2007/01/11 ��іQ (���u) ���f�� No.1083
*/

package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

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
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.define.WEB3TaxTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityProductQuote;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3MarginClientRequestService;
import webbroker3.equity.define.WEB3MarginTradeTypeDef;
import webbroker3.equity.message.WEB3MarginOpenMarginChangeInputRequest;
import webbroker3.equity.message.WEB3MarginOpenMarginChangeInputResponse;
import webbroker3.equity.message.WEB3MarginRepaymentUnit;
import webbroker3.equity.service.delegate.WEB3MarginChangeOpenMarginInputService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �i�M�p��������V�K�����̓T�[�r�XImpl�j�B<BR>
 * <BR>
 * �M�p��������V�K�����̓T�[�r�X�����N���X
 * @@author �@@��
 * @@version 1.0
 */
public class WEB3MarginChangeOpenMarginInputServiceImpl extends WEB3MarginClientRequestService implements WEB3MarginChangeOpenMarginInputService 
{
    
    /**
     * (���O�o�̓��[�e�B���e�B)�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginChangeOpenMarginInputServiceImpl.class);
        
    /**
     * (�R���X�g���N�^)�B
     * @@roseuid 414006710096
     */
    public WEB3MarginChangeOpenMarginInputServiceImpl() 
    {
    }
    
    /**
     * (execute)�B<BR>
     * <BR>
     * �M�p��������V�K�����̓T�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^���u�M�p��������V�K�����̓��N�G�X�g�v�^�ɕϊ����Aget����<BR>
     * �V�K�����͉��()���\�b�h���R�[������B<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 407CAAE0002B
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String METHOD_NAME = "execute";
        if (l_request == null)
        {
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               "WEB3MarginChangeOpenMarginInputServiceImpl" + "." + METHOD_NAME);
        }
        if (l_request instanceof WEB3MarginOpenMarginChangeInputRequest)
        {
            WEB3MarginOpenMarginChangeInputRequest l_request0 = (WEB3MarginOpenMarginChangeInputRequest)l_request;
            return this.getOpenMarginChangeInputScreen(l_request0);
        }
        else
        {
            throw new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + METHOD_NAME);          
        }        
    }
    
    /**
     * (get�����V�K�����͉��)�B<BR>
     * <BR>
     * �M�p����̒����V�K�����͉�ʕ\���T�[�r�X�����{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�M�p��������V�K�����̓T�[�r�X�jget�����V�K�����͉�ʂP�v<BR>
     * �u�i�M�p��������V�K�����̓T�[�r�X�jget�����V�K�����͉�ʂQ�v<BR>
     * �Q�ƁB<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.margin.message.WEB3MarginOpenMarginChangeInputResponse
     * @@roseuid 41046DA50010
     */
    protected WEB3MarginOpenMarginChangeInputResponse getOpenMarginChangeInputScreen(WEB3MarginOpenMarginChangeInputRequest l_request) throws WEB3BaseException
    {
        final String METHOD_NAME = "getOpenMarginChangeInputScreen";
        log.entering(METHOD_NAME);
        if (l_request == null)
        {
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               "WEB3MarginChangeOpenMarginInputServiceImpl" + "." + METHOD_NAME);
        }
        //1.1validate
        l_request.validate();
        //1.2get�⏕����()
        WEB3GentradeSubAccount l_subAcc = this.getSubAccount();
        //1.3get order
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tm = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);     
        WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager) l_tm.getOrderManager();
        
        EqTypeOrderUnit l_orderUnit = this.getChangeOrderUnit(l_request);
        
        //1.6reset�s��R�[�h(�s��R�[�h : String)
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        long l_lngMarketId = l_orderUnitRow.getMarketId();
        Market l_market = null;
        try 
        {
            l_market = l_finApp.getFinObjectManager().getMarket(l_lngMarketId);
        } 
        catch (NotFoundException l_ex) 
        {
            log.error(METHOD_NAME,l_ex);
            throw new WEB3BaseException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                 this.getClass().getName() + "." + METHOD_NAME);    
        }
        String l_strMarketCode = l_market.getMarketCode();
        WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
        
        this.validateOrderForChangeability(l_orderUnit);
        
        String l_strRepaymentType = l_orderUnitRow.getRepaymentType();
        //1.7validate�M�p����
        l_orderManager.validateMarginOrder((WEB3GentradeSubAccount) l_subAcc,l_strRepaymentType);
        //1.8 getTaxType
        TaxTypeEnum  l_taxType = l_orderUnitRow.getTaxType();
        //1.9getDeliveryDate
        Timestamp l_deliveryDate = l_orderUnitRow.getDeliveryDate();
        //1.10validate��������J�݁i�M�p�j(�⏕���� : �⏕����, �ŋ敪 : TaxTypeEnum, ��n�� : Date)
        l_orderManager.validateMarginSpecialAccountOpen(
            (WEB3GentradeSubAccount) l_subAcc,
            l_taxType,
            l_deliveryDate);            
        //1.11validate�s��R�[�h(�s��R�[�h : String, �،���ЃR�[�h : String)
        Institution l_institution = l_subAcc.getInstitution();
        String l_strInstitutionCode = l_institution.getInstitutionCode();
        //1.12get product
        WEB3EquityProduct l_product = (WEB3EquityProduct)l_orderUnit.getProduct();
        //1.13 get side 
        SideEnum l_side = l_orderUnit.getSide();
        //1.15validate�������
        Branch l_branch = l_subAcc.getMainAccount().getBranch();
        boolean l_blnShort = false;
        if (SideEnum.BUY.equals(l_side))
        {
            l_blnShort =false;    
        }
        else if (SideEnum.SELL.equals(l_side))
        {
            l_blnShort = true;
        }
        WEB3EquityTradedProduct l_tradeProduct = l_orderManager.validateTradedProductForMarginTrading(
            l_subAcc,
            l_product,
            (WEB3GentradeMarket) l_market,
            (WEB3GentradeBranch) l_branch,
            l_strRepaymentType,
            OrderCategEnum.OPEN_MARGIN,
            l_blnShort,
            false);
        //1.16validate�戵�\�s��
        //�ٍϊ����l�F�@@�����P��.�ٍϊ����l
        int l_intRPN = l_orderUnitRow.getRepaymentNum();
        l_orderManager.validateHandlingMarket(
            (WEB3GentradeBranch) l_branch,
            l_tradeProduct,
            l_strMarketCode,
            l_strRepaymentType,
            l_intRPN);
            
        //1.17is�C���T�C�_�[�x���\��
        boolean l_boolIsInsider = l_orderManager.isInsiderMessageSuspension(l_subAcc, l_orderUnitRow.getProductId());
        
        //1.18validate�ڋq�����ʎ����~
        l_orderManager.validateAccountProductOrderStop(l_subAcc, l_orderUnitRow.getProductId(), l_orderUnitRow.getOrderType());
        
		//1.17������ԊǗ�.get������ �� �����P��.������
		Date l_bizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
		Date l_orderBizDate =
			WEB3DateUtility.getDate(
				l_orderUnitRow.getBizDate(),
				WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        
		if (WEB3DateUtility.compare(l_bizDate, l_orderBizDate) > 0)
		{
			//1.17.1validate�ǌ���������t�\(�����^�C�v : ProductTypeEnum)
			WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(ProductTypeEnum.EQUITY);
		}
        
        //get�M�p�����\�z
        double l_dblOpenMarginPossiblePrice = this.getMarginTradingPower(l_subAcc);
        
        log.debug("�M�p�V�K���\�z = " + l_dblOpenMarginPossiblePrice);
        
        //1.19�i�M�p��������V�K�����̓T�[�r�X�jget�����V�K�����͉�ʂQ�i�Q�Ɓj
        //1.2get�s��ǌx���s��
        String[] l_strCloseMarket = WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
            (WEB3GentradeBranch) l_branch,
            ProductTypeEnum.EQUITY,
            l_strRepaymentType);
        //1.3�戵�\��������
        WEB3GentradeHandlingOrderCond l_orderCond = new WEB3GentradeHandlingOrderCond(
            l_strInstitutionCode,
            ProductTypeEnum.EQUITY,
            WEB3FuturesOptionDivDef.DEFAULT,
            WEB3MarginTradingDivDef.DEFAULT,
            l_strMarketCode);

        //�����P���敪�ꗗ
        String[] l_strOrderPriceDivList = l_orderManager.getOrderPriceDivs(l_branch, l_tradeProduct);
        
        //�l�i�����敪�ꗗ
        String[] l_strPriceCondList = l_orderCond.getHandlingPriceCond(); 
        
        //1.4�戵�\���s�����擾( )
        String[] l_strExecConds = l_orderCond.getHandlingPossibleExecCond();

        // get�v�w�l�p���s�����ꗗ(String[], EqTypeOrderUnit)
        //�v�w�l�p�̎��s�����ꗗ���擾����B
        //[get�v�w�l�p���s�����ꗗ()�Ɏw�肷�����]
        //���s�����ꗗ�F�@@�戵�\���s�����擾()�̖߂�l 
        //�����P�ʁF�@@�����P��
        String[] l_strWLimitExecutionConditionTypeList =
            WEB3EquityDataAdapter.getWLimitExecutionConditionTypeList(
                l_strExecConds,
                l_orderUnit);

        //1.5is�o����܂Œ����戵�\( )
        boolean l_blnIsOrderCond = l_orderCond.isOrderUntilDeadLinePossibleHandling();
        //1.6is�o����܂Œ����P��(�����P��)
        boolean l_blnIsCarriedOrderUnit = l_orderManager.isCarriedOrderUnit((EqTypeOrderUnit)l_orderUnit);
        //1.7*1)����t���[
        //�o����܂Œ����戵�\(�戵�\��������.is�o����܂Œ����戵�\( ) == true)�A
        //���� �����Ώے������o����܂Œ����ł���iis�o����܂Œ����P��( )==true�j�̏�
        Date l_datStart = null;
        Date l_datEnd = null;
        Date[] l_datHoliday = null;
        Date l_datBizDate = null;
        if (l_blnIsOrderCond && l_blnIsCarriedOrderUnit )
        {
            l_datBizDate = this.getCarriedOrderFromDate(l_orderUnit);
			//1.7.2get�o����܂Œ����ŏI��(�o����܂Œ���from���t : Date)
			l_datEnd = l_orderCond.getOrderUntilDeadLineEndDay(l_datBizDate);			
            //1.7.3get�o����܂Œ����J�n��(void)
            l_datStart = l_orderCond.getOrderUntilDeadLineStartDay(null,l_datEnd);
            //1.7.4get�����������j���ꗗ(void)
            l_datHoliday = l_orderCond.getExpirationDateHoliday(l_datStart);
        }

        // �g���v���_�N�g�}�l�[�W���擾
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
            
        // �������̎擾
        WEB3EquityProductQuote l_productQuote =
            l_productManager.getDisplayEquityProductQuote(l_tradeProduct, l_subAcc);
        
        // �����敪
        String l_strQuoteTypeDiv = l_productQuote.getQuoteTypeDiv();
        // ����
        double l_dblCurrentPrice = l_productQuote.getQuote();
        // �O����
        double l_dblChange = l_productQuote.getComparedPreviousDay();
        // �������\����
        Timestamp l_timeStamp = l_productQuote.getQuoteTime();
        
        //1.12 create response
        WEB3MarginOpenMarginChangeInputResponse l_response = (WEB3MarginOpenMarginChangeInputResponse) l_request.createResponse();
        //1.13 �v���p�e�C�Z�b�g
        //�M�p��������V�K���������̓��X�|���X�ɉ��L�̒ʂ�v���p�e�B���Z�b�g����B
        //�����P���敪�ꗗ�F�g�����������}�l�[�W��.get�����P���敪�ꗗ()�̖߂�lList���Z�b�g
        l_response.orderPriceDivList = l_strOrderPriceDivList;
        
        //�l�i�����敪�ꗗ
        //�o����܂Œ����̏ꍇ��
        EqtypeOrderUnitRow l_orderUnitRow1 = (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
        if (l_blnIsCarriedOrderUnit || !WEB3OrderingConditionDef.DEFAULT.equals(l_orderUnitRow1.getOrderConditionType()))
        {
            String[] l_strPriceCondListOfCarriedOrderUnit = { WEB3PriceConditionDef.DEFAULT }; 
            l_strPriceCondList = l_strPriceCondListOfCarriedOrderUnit;
        }
        l_response.priceCondList = l_strPriceCondList;
        
        //���s�����ꗗ�F�@@�����\�Ȍ��̂�(**1)���Z�b�g
        //(**1)�@@�����Ώے������o����܂Œ����Ŗ��́A�t�w�l�����ł���ꍇ�A"������"�݂̂��Z�b�g�B
        if (l_blnIsCarriedOrderUnit ||
			WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            String[] l_strCond = { WEB3ExecutionConditionDef.NO_CONDITION };
            l_response.execCondList = l_strCond;    
        }
        //W�w�l�����ꍇ
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            l_response.execCondList = l_strWLimitExecutionConditionTypeList;
        }
        //�ȊO�A�戵�\��������.�戵�\���s�����擾( )�̖߂�l�z����Z�b�g
        else
        {
           l_response.execCondList = l_strExecConds;   
        }
        
        //�v�w�l�p���s�����ꗗ�F�@@get�v�w�l�p���s�����ꗗ�̖߂�l�z����Z�b�g
        l_response.wlimitExecCondList = l_strWLimitExecutionConditionTypeList;

        if (l_blnIsOrderCond && l_blnIsCarriedOrderUnit)
        {
            //�L�������J�n���F�@@(**2)�戵�\��������.get�o����܂Œ����J�n��( )�̖߂�l
            l_response.expirationStartDate = l_datStart;
            //�L�������ŏI���F�@@(**2)�戵�\��������.get�o����܂Œ����ŏI��( )�̖߂�l
            l_response.expirationEndDate = l_datEnd;
            //�L���������j���ꗗ�F�@@(**2)�戵�\��������.get�����������j���ꗗ( )�̖߂�l�z��
            l_response.holidayList = l_datHoliday;
        }
        else
        {
            l_response.expirationStartDate = null;
            l_response.expirationEndDate = null;
            l_response.holidayList = null;
        }
        //����I���x���s��R�[�h�ꗗ�F�@@������ԊǗ�.get�s��ǌx���s��( )�̖߂�l�z��
        l_response.messageSuspension = l_strCloseMarket;
        //is�C���T�C�_�[�x���\��
        l_response.insiderWarningFlag = l_boolIsInsider; 

        //��񍀖ڂ̐ݒ�d�l�͈ȉ��̒ʂ�B
        //���ݒl�F�@@�擾�������������������.get���ݒl()�̖߂�l���Z�b�g
        l_response.boardCurrentPrice = l_productQuote.getBoardCurrentPrice();

        //���ݒl�����F�@@�擾�������������������.get���ݒl����()�̖߂�l���Z�b�g
        l_response.boardCurrentPriceTime = l_productQuote.getBoardCurrentPriceTime();

        //���ݒl�敪�F�@@�擾�������������������.get���ݒl�敪()�̖߂�l���Z�b�g
        l_response.boardCurrentPriceDiv = l_productQuote.getBoardCurrentPriceDiv();

        //���ݒl�O����F�@@�擾�������������������.get���ݒl�O����()�̖߂�l���Z�b�g
        l_response.boardChange = l_productQuote.getBoardChange();

        //�o�����F�@@�擾�������������������.get�o����()�̖߂�l���Z�b�g
        l_response.volume = l_productQuote.getVolume();

        //�o���������F�@@�擾�������������������.get�o��������()�̖߂�l���Z�b�g
        l_response.volumeTime = l_productQuote.getVolumeTime();

        //���C�z�l�^�C�g���敪�F�@@�擾�������������������.get���C�z�l�^�C�g���敪()�̖߂�l���Z�b�g
        l_response.askPriceTitle = l_productQuote.getAskPriceTitle();

        //���C�z�l�F�@@�擾�������������������.get���C�z�l()�̖߂�l���Z�b�g
        l_response.askPrice = l_productQuote.getAskPrice();

        //���C�z�l�����F�@@�擾�������������������.get���C�z�l����()�̖߂�l���Z�b�g
        l_response.askPriceTime = l_productQuote.getAskPriceTime();

        //���C�z�l�^�C�g���敪�F�@@�擾�������������������.get���C�z�l�^�C�g���敪()�̖߂�l���Z�b�g
        l_response.bidPriceTitle = l_productQuote.getBidPriceTitle();

        //���C�z�l�F�@@�擾�������������������.get���C�z�l()�̖߂�l���Z�b�g
        l_response.bidPrice = l_productQuote.getBidPrice();

        //���C�z�l�����F�@@�擾�������������������.get���C�z�l����()�̖߂�l���Z�b�g
        l_response.bidPriceTime = l_productQuote.getBidPriceTime();

        //��l�i�F�@@�擾�������������������.get��l�i()�̖߂�l���Z�b�g
        l_response.basePrice = l_productQuote.getBasePrice();

        //�V�K���\�z�F�@@calc�M�p�V�K���\�z()�̖߂�l
        l_response.marginTradingPower = WEB3StringTypeUtility.formatNumber(l_dblOpenMarginPossiblePrice);
        //������������validate�����R�[�h�i�M�p�j( )�̖߂�l�ɂĎ擾
        //�����R�[�h�F�@@��������.�����R�[�h
        l_response.productCode = l_product.getProductCode();
        //�������F�@@��������.������
        EqtypeProductRow l_productRow = (EqtypeProductRow) l_product.getDataSourceObject();
        l_response.productName = l_productRow.getStandardName();
        //�s��R�[�h�F�@@�s��.�s��R�[�h
        l_response.marketCode = l_strMarketCode;
        //���s���validate�s��R�[�h( )�̖߂�l�ɂĎ擾
        //�����敪�F�@@
        //�i�����P��.getTaxType() == TaxTypeEnum.��ʁj�̏ꍇ�A�h��ʁh
        //�i�����P��.getTaxType() == TaxTypeEnum.����C�܂��́@@TaxTypeEnum.���芎���򒥎��j�̏ꍇ�A�h����h
        if (TaxTypeEnum.NORMAL.equals(l_taxType))
        {
            l_response.taxType = WEB3TaxTypeDef.NORMAL;           
        }
        else if (TaxTypeEnum.SPECIAL.equals(l_taxType)||TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxType))
        {
            l_response.taxType = WEB3TaxTypeDef.SPECIAL; 
        }
        //����敪�F�@@
        //�i�����P��.getOrderType() == OrderTypeEnum.�V�K���������j�̏ꍇ�A�V�K��������
        //�i�����P��.getOrderType() == OrderTypeEnum.�V�K���������j�̏ꍇ�A�V�K��������
        OrderTypeEnum l_orderTypeEnum = l_orderUnit.getOrderType();
        if (OrderTypeEnum.MARGIN_LONG.equals(l_orderTypeEnum))
        {
            l_response.tradingType = WEB3MarginTradeTypeDef.OPEN_LONG_MARGIN;
        }
        else if (OrderTypeEnum.MARGIN_SHORT.equals(l_orderTypeEnum))
        {
            l_response.tradingType = WEB3MarginTradeTypeDef.OPEN_SHORT_MARGIN;
        }
        //�ٍρF
        //�M�p����ٍ�.�ٍϋ敪�F�@@�����P��.�ٍϋ敪
        WEB3MarginRepaymentUnit l_repayment = new WEB3MarginRepaymentUnit();
        l_repayment.repaymentDiv = l_orderUnitRow1.getRepaymentType();
        //�M�p����ٍ�.�ٍϊ����l�F�@@�����P��.�ٍϊ����l
        l_repayment.repaymentTimeLimit =  String.valueOf(l_orderUnitRow.getRepaymentNum());
        l_response.repayment = l_repayment;       
        //���������F�@@�����P��.getQuantity( )�̖߂�l
        double l_dblQuantity = l_orderUnitRow1.getQuantity();
        l_response.orderQuantity = WEB3StringTypeUtility.formatNumber(l_dblQuantity);
        //���o�������F�@@�����P��.getExecutedQuantity( )�̖߂�l
        if (!l_orderUnitRow1.getExecutedQuantityIsNull())
        {
			double l_dblExeQuantity = l_orderUnitRow1.getExecutedQuantity();
			l_response.partContQuantity = WEB3StringTypeUtility.formatNumber(l_dblExeQuantity);
        
        }
        //�����P���敪�F�@@�i�����P��.isMarketOrder() == true�j�̏ꍇ�h���s�h�A�ȊO�h�w�l�h�B
        if (l_orderUnit.isMarketOrder())
        {
            l_response.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
            l_response.limitPrice = null;
        }
        else 
        {
            l_response.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
            // �����P���F�����P��.getLimitPrice( )�̖߂�l
            double l_dblLimitPrice = l_orderUnitRow1.getLimitPrice();
            l_response.limitPrice = WEB3StringTypeUtility.formatNumber(l_dblLimitPrice);            
        }
        //�l�i�����F  �����P��.�l�i����
		l_response.priceCondType = l_orderUnitRow1.getPriceConditionType();
        //���s�����F�@@�����P��.���s����
        if (l_orderUnitRow1.getExecutionConditionType() == null)
        {
            l_response.execCondType = null;
        }
        else
        {
            //�g�������}�l�[�W��.get���s����(SONAR)�̖߂�l
            l_response.execCondType = l_orderManager.getExecutionConditionTypeSonar(l_orderUnitRow1.getExecutionConditionType());
        }
        //���������敪�F�iis�o����܂Œ����P��( ) == false�j�̏ꍇ�h��������h�A�ȊO�h�o����܂Œ����h�B
        if (! l_blnIsCarriedOrderUnit)
        {
            l_response.expirationDateType = WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
            l_response.expirationDate = null;   
        }
        else
        {
            l_response.expirationDateType = WEB3OrderExpirationDateTypeDef.CARRIED_ORDER;   
            //�����L�������F�����P��.�����������t
            l_response.expirationDate = WEB3DateUtility.toDay(l_orderUnitRow1.getExpirationDate());
        }
        //���������敪�F�����P��.��������
        l_response.orderCondType = l_orderUnitRow1.getOrderConditionType();
        //�������P��.�������� == �h�t�w�l�h�̏ꍇ�����ȊO�̏ꍇ�A���L���ڂ�null���Z�b�g����B
        // �t�w�l�p���������P���F�@@�����P��.�t�w�l��l
        // �t�w�l�p�����������Z�q�F�@@�����P��.�����������Z�q
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitRow1.getOrderConditionType()))
        {
            double l_dblStopOrderPrice = l_orderUnitRow1.getStopOrderPrice();
            if(Double.isNaN(l_dblStopOrderPrice))
            {
                l_dblStopOrderPrice = 0;
            }
            l_response.stopOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_dblStopOrderPrice); 
            l_response.stopOrderCondOperator = l_orderUnitRow.getOrderCondOperator(); 
        }
        else 
        {
            l_response.stopOrderCondPrice = null; 
            l_response.stopOrderCondOperator = null;             
        }
        //�������P��.�������� == �hW�w�l�h�̏ꍇ�����ȊO�̏ꍇ�A���L���ڂ�null���Z�b�g����B
        //W�w�l�p���������P���F�@@�����P��.�t�w�l��l
        //W�w�l�p�����������Z�q�F�����P��.�����������Z�q
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow1.getOrderConditionType()))
        {
            double l_dblStopOrderPrice = l_orderUnitRow1.getStopOrderPrice();
            l_response.wlimitOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_dblStopOrderPrice); 
            l_response.wlimitOrderCondOperator = l_orderUnitRow.getOrderCondOperator();
            //W�w�l�p�����P���敪�F�i�����P��.�iW�w�l�j�����w�l == 0�j�̏ꍇ�h���s�h�A�ȊO�h�w�l�h
            double l_dblWLimitPrice = l_orderUnitRow1.getWLimitPrice();
            if(l_dblWLimitPrice == 0)
            {
                l_response.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                l_response.wLimitPrice = null;    
            }
            else 
            {
                l_response.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                //W�w�l�p�����P���F�@@�����P��.�iW�w�l�j�����w�l
                l_response.wLimitPrice = WEB3StringTypeUtility.formatNumber(l_dblWLimitPrice);     
            }

            //�v�w�l�p���s�����F�����f�[�^�A�_�v�^.get���s�����iSONAR�j(�����P��.�iW�w�l�j���s����)
            l_response.wlimitExecCondType =
                l_orderManager.getExecutionConditionTypeSonar(l_orderUnitRow1.getWLimitExecCondType());

        }
        else 
        {
            l_response.wlimitOrderCondPrice = null; 
            l_response.wlimitOrderCondOperator = null; 
            l_response.wLimitOrderPriceDiv = null;
            l_response.wLimitPrice = null;   
            //�v�w�l�p���s����: null
            l_response.wlimitExecCondType = null;
        }    

        //�v�w�l�p�L����ԋ敪�F�����f�[�^�A�_�v�^.get�v�w�l�p�L����ԋ敪(�����P��)
        l_response.wlimitEnableStatusDiv = WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_orderUnit);

        //�v�w�l�p�֑ؑO�����P���F�����f�[�^�A�_�v�^.get�v�w�l�p�֑ؑO�����P��(�����P��)
        l_response.wlimitBefChgLimitPrice = WEB3EquityDataAdapter.getWLimitBefSwitchPrice(l_orderUnit);

        //�v�w�l�p�֑ؑO���s�����F�����f�[�^�A�_�v�^.get�v�w�l�p�֑ؑO���s����(�����P��)
        l_response.wlimitBefChgExecCondType =
            WEB3EquityDataAdapter.getWLimitBefSwitchExecCondType(l_orderUnit);

        //�����������敪�F�����P��.����������
        l_response.orgOrderCondType = l_orderUnitRow.getOrgOrderConditionType();

        //�����������P���F�����P��.���t�w�l��l
        if (!l_orderUnitRow.getOrgStopOrderPriceIsNull())
        {
            l_response.orgOrderCondPrice =
                WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getOrgStopOrderPrice());
        }

        //�������������Z�q�F�����P��.�������������Z�q
        l_response.orgOrderCondOperator = l_orderUnitRow.getOrgOrderCondOperator();

        String l_strOrgWlimitOrderPriceDiv = WEB3EquityDataAdapter.getOrgWLimitOrderPriceDiv(l_orderUnit);

        //���v�w�l�p�����P���敪�F�����f�[�^�A�_�v�^.get���v�w�l�p�����P���敪(�����P��)
        l_response.orgWlimitOrderPriceDiv = l_strOrgWlimitOrderPriceDiv;

        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrgWlimitOrderPriceDiv))
        {
            //���v�w�l�p�����P���F�����f�[�^�A�_�v�^.get���v�w�l�p�����P��(�����P��)
            l_response.orgWlimitPrice = WEB3EquityDataAdapter.getOrgWLimitOrderPrice(l_orderUnit);
        }

        //���v�w�l�p���s�����F�����f�[�^�A�_�v�^.get���v�w�l�p���s����(�����P��)
        l_response.orgWlimitExecCondType = WEB3EquityDataAdapter.getOrgWLimitExecCondType(l_orderUnit);

        //�T�Z��n����F�����P��.�T�Z��n���
        double l_dblEstimatePrice = l_orderUnitRow1.getEstimatedPrice();
        if (l_orderUnitRow1.getEstimatedPriceIsNull())
        {
            l_response.estimatedPrice = null;
        }
        else
        {
            l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(l_dblEstimatePrice);
        }

        // �����敪
        l_response.currentPriceDiv = l_strQuoteTypeDiv;
        //����(���ݒl)�F
        l_response.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblCurrentPrice);
        //�O����F
        l_response.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_dblChange);
        //�������(�������\����)�F
        l_response.currentPriceTime = l_timeStamp;       
        return l_response;
    }
    
    /**
     * (get�����Ώے����P��)<BR>
     * �����Ώۂ̒����P�ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �g�����������}�l�[�W��.getOrderUnits(���N�G�X�g�f�[�^.ID)�Ŏ擾���������P�ʃI�u�W�F�N�g�̂����A<BR>
     * �ŏ��̗v�f��ԋp����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B
     * @@return EqTypeOrderUnit
     * @@throws WEB3BaseException
     */
    protected EqTypeOrderUnit getChangeOrderUnit(
        WEB3MarginOpenMarginChangeInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getChangeOrderUnit(WEB3MarginOpenMarginChangeInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(Long.parseLong(l_request.id));
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        
        log.exiting(STR_METHOD_NAME);
        return l_orderUnit;
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
    protected void validateOrderForChangeability(
        EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderForChangeability(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        l_orderManager.validateOrderForChangeability(l_orderUnit.getOrder());
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get�V�K���\�z)<BR>
     * �V�K���\�z���擾����B<BR>
     * <BR>
     * ����]�̓T�[�r�X.get�M�p�V�K���\�z(�⏕����)���R�[������B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B
     * @@return double
     * @@throws WEB3BaseException
     */
    protected double getMarginTradingPower(WEB3GentradeSubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMarginTradingPower(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPTradingPowerService l_tradingPowerService =
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        double l_dblOpenMarginPossiblePrice =
            l_tradingPowerService.getMarginTradingPower(l_subAccount);
        
        log.exiting(STR_METHOD_NAME);
        return l_dblOpenMarginPossiblePrice;
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
}
@
