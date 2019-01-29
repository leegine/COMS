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
filename	WEB3MarginChangeCloseMarginInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (�M�p��������ԍϓ��̓T�[�r�XImpl)
                 : �M�p��������ԍϓ��̓T�[�r�X�����N���X
                 : (WEB3MarginChangeCloseMarginInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/28 li-songfeng (���u) �V�K�쐬
Revesion History : 2006/11/27 ��іQ (���u) ���f�� No.1021
Revesion History : 2006/12/14 ������@@(���u)�@@���f��No.1083
Revesion History : 2007/06/05 �����q�@@(���u)�@@���f��No.1166
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

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
import webbroker3.equity.define.WEB3EquityWlimitOrderPriceDivDef;
import webbroker3.equity.message.WEB3MarginCloseMarginChangeInputRequest;
import webbroker3.equity.message.WEB3MarginCloseMarginChangeInputResponse;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.equity.message.WEB3MarginRepaymentUnit;
import webbroker3.equity.service.delegate.WEB3MarginChangeCloseMarginInputService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �i�M�p��������ԍϓ��̓T�[�r�XImpl�j�B<BR>
 * <BR>
 * �M�p��������ԍϓ��̓T�[�r�X�����N���X�B
 * @@version 1.0
 */
public class WEB3MarginChangeCloseMarginInputServiceImpl extends WEB3MarginClientRequestService implements WEB3MarginChangeCloseMarginInputService 
{
    /**
     * (���O�o�̓��[�e�B���e�B)�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3MarginChangeCloseMarginInputServiceImpl.class);
 
    /**
     * (�R���X�g���N�^)�B<BR>
     * @@roseuid 41400671023B
     */
    public WEB3MarginChangeCloseMarginInputServiceImpl()
    {
    }
    
    /**
     * (execute)�B<BR>
     * <BR>
     * �M�p��������ԍϓ��̓T�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^���u�M�p��������ԍϓ��̓��N�G�X�g�v�^�ɕϊ����A<BR>
     * get�����ԍϓ��͉��()���\�b�h���R�[������B<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 407CB28501E0
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = getClass().getName() + "execute(WEB3GenRequest l_request)";
        WEB3GenResponse l_response = null;
        log.entering(STR_METHOD_NAME);
        if (l_request instanceof WEB3MarginCloseMarginChangeInputRequest)
        {
            l_response = this.getCloseMarginChangeInputScreen((WEB3MarginCloseMarginChangeInputRequest) l_request);
        }
        else
        {
            log.error(STR_METHOD_NAME + "�p�����[�^�^�C�v�s��.");
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, this.getClass().getName() + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    /**
     * (get�����ԍϓ��͉��)�B<BR>
     * <BR>
     * �M�p����̒����ԍϓ��͉�ʕ\���T�[�r�X�����{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�M�p��������ԍϓ��̓T�[�r�X�jget�����ԍϓ��͉�ʂP�v<BR>
     * �u�i�M�p��������ԍϓ��̓T�[�r�X�jget�����ԍϓ��͉�ʂQ�v<BR>
     * �Q�ƁB<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3MarginCloseMarginChangeInputResponse
     * @@roseuid 4104B3B00274
     */
    protected WEB3MarginCloseMarginChangeInputResponse getCloseMarginChangeInputScreen(
        WEB3MarginCloseMarginChangeInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = getClass().getName() + "getCloseMarginChangeInputScreen(WEB3MarginCloseMarginChangeInputRequest)";
        try
        {
            l_request.validate();           
            SubAccount l_subAccount = this.getSubAccount();

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_orderUnit = this.getChangeOrderUnit(l_request);
            
            //�s��R�[�h��ThreadLocal�ɃZ�b�g�������B
            EqtypeOrderUnitRow l_orderUnitRow=(EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            long l_marketId = l_orderUnitRow.getMarketId();
            Market l_market =(Market)l_finApp.getFinObjectManager().getMarket(l_marketId);
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_market.getMarketCode());
            
            //���������\�ȏ�Ԃ����`�F�b�N����B   
            this.validateOrderForChangeability(l_orderUnit);

            //�V�[�P���X�}
            // �u�i�M�p�����jvalidate�M�p�����v
            //  �ڋq�ʎ����~�`�F�b�N
            //  �M�p���{��Ѓ`�F�b�N
            //  �E�M�p�q�`�F�b�N
            //  �E��t���ԃ`�F�b�N
            //  �E�V�X�e�������~�`�F�b�N
            //�M�p������������ʃ`�F�b�N�����{����B
            //�����͈ȉ��̒ʂ�w�肷��B
            //�⏕�����F�@@�iget�⏕����()�̖߂�l�j
            //�ٍϋ敪�F�@@�igetOrderUnits()�Ŏ擾���������Ώے����P��.�ٍϋ敪�j
            l_orderManager.validateMarginOrder((WEB3GentradeSubAccount)l_subAccount,l_orderUnitRow.getRepaymentType());
            TaxTypeEnum l_taxTypeEnum = l_orderUnit.getTaxType();
            Date l_DeliveryDate = l_orderUnit.getDeliveryDate();
            //��������J�݃`�F�b�N�����{����B
            l_orderManager.validateMarginSpecialAccountOpen((WEB3GentradeSubAccount)l_subAccount,l_taxTypeEnum,l_DeliveryDate);
            
            Product l_product = l_orderUnit.getProduct();
            SideEnum l_sideEnum = l_orderUnit.getSide();
            //validate��������i�M�p�j(�������� : ��������, �s�� : �s��, ���X : ���X, �ٍϋ敪 : String, �����J�e�S�� : OrderCategEnum, is���� : boolean)
            //����\�����`�F�b�N
            //�����ݒ�d�l�́A�V�[�P���X�̃m�[�g�A���J�[�Q�ƁB
            //��������        ���@@validate�����R�[�h�i�M�p�j()�̖߂�l
            //�s��      ���@@validate�s��R�[�h()�̖߂�l
            //�����J�e�S��  ���@@OrderCategEnum.�ԍϒ����iCLOSE_MARGIN�j
            //is����        ���@@(*)
            //is���������`�F�b�N�@@���@@false�i��������~�`�F�b�N�����Ȃ��j
            //(*)getSide()�̖߂�l�ɂ�蕪�򂷂�B
            //[getSide()�̖߂�l�@@���@@SideEnum.����(BUY)�̏ꍇ]
            //  is����       ���@@true
            //[getSide()�̖߂�l�@@���@@SideEnum.����(SELL)�̏ꍇ]
            //  is����       ���@@false
            boolean l_isShort;
            if (SideEnum.BUY.equals(l_sideEnum))
            {
                l_isShort = true;
            }
            else
            {
                l_isShort = false;
            }
            WEB3EquityTradedProduct l_validatedTradedProduct = 
                (WEB3EquityTradedProduct)l_orderManager.validateTradedProductForMarginTrading(
                    l_subAccount,
                    (WEB3EquityProduct)l_product,
                    (WEB3GentradeMarket)l_market,
                    (WEB3GentradeBranch)l_subAccount.getMainAccount().getBranch(),
                    l_orderUnitRow.getRepaymentType(),
                    OrderCategEnum.CLOSE_MARGIN,l_isShort,false);
                        
            //validate�戵�\�s��i�M�p�j(���X : ���X, ������� : �������, �s��R�[�h : String, �ٍϋ敪 :
            //�i�M�p)����\�s��`�F�b�N(����)�i�`�r�c�`�p
            //��Е��X���A�M�p����̎w��ٍϋ敪�E�ٍϊ�����
            //�戵�\�s�ꂩ���`�F�b�N����B
            //�����͈ȉ��̒ʂ�w�肷��B
            //���X�F�@@�⏕����.get����X()
            //��������F�@@validate��������i�M�p�j()�̖߂�l
            //�s��R�[�h�F�@@validate�s��R�[�h()�̖߂�l.getMarketCode()
            //�ٍϋ敪�F�@@�����P��.�ٍϋ敪
            //�ٍϊ����l�F�@@�����P��.�ٍϊ����l
            
            l_orderManager.validateHandlingMarket((WEB3GentradeBranch)l_subAccount.getMainAccount().getBranch(),
                l_validatedTradedProduct,
                l_market.getMarketCode(),
                l_orderUnitRow.getRepaymentType(),
                (double)l_orderUnitRow.getRepaymentNum());
            

            //is�C���T�C�_�[�x���\��
            boolean l_boolIsInsider = l_orderManager.isInsiderMessageSuspension(
                l_subAccount, l_orderUnitRow.getProductId());
            
            //validate�ڋq�����ʎ����~
            l_orderManager.validateAccountProductOrderStop(
                l_subAccount, l_orderUnitRow.getProductId(), l_orderUnitRow.getOrderType());
            
			//(*)����t���[
			//�����������̔����������ݓ������Z�o�������������O�̏ꍇ�̂ݎ��{�B
			//����
			//������ԊǗ�.get������ �� �����P��.������
			//validate�ǌ���������t�\(�����^�C�v : ProductTypeEnum)
			//���@@������ԊǗ�.get������ �� �����P��.������ �̏ꍇ�̂݃R�[������B
			//������t��~���ԂłȂ����`�F�b�N����B
			//�i�s��ǁ`�o���I���܂ł͒����s�j
			Date l_bizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            
			Date l_orderBizDate = 
				WEB3DateUtility.getDate(
					l_orderUnitRow.getBizDate(), 
					WEB3GentradeTimeDef.DATE_FORMAT_YMD);
                    
			if (WEB3DateUtility.compare(l_bizDate, l_orderBizDate) > 0)
			{
				WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(l_product.getProductType());   
			}
            //get�s��ǌx���s��(���X : ���X, �����^�C�v : ProductTypeEnum, �M�p����敪 : String)
            //�����͈ȉ��̒ʂ�ݒ肷��B
            //���X�F�@@�⏕����.get����X()
            //�����^�C�v�F�@@ProductTypeEnum.�h�����h
            //�M�p����敪�F�@@�����P��.�ٍϋ敪
            WEB3GentradeBranch l_gentradeBranch = (WEB3GentradeBranch)l_subAccount.getMainAccount().getBranch();
            String[] l_tradeCloseMarket = WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
                l_gentradeBranch,
                ProductTypeEnum.EQUITY,
                l_orderUnitRow.getRepaymentType());
            //�戵�\��������(�،���ЃR�[�h : String, �����^�C�v : ProductTypeEnum, 
            //�敨�^�I�v�V�����敪 : String, �M�p����敪 : String)
            //��Ђ��Ƃ̎戵�\�����������擾����B
            //�M�p����敪�F�@@�hDEFAULT�h(�Œ�)
            //�����͈ȉ��̒ʂ�ݒ肷��B
            //�،���ЃR�[�h�F�@@�⏕����.�،���ЃR�[�h
            //�����^�C�v�F�@@�h�����h
            //�敨�^�I�v�V�����敪�F�@@�hDEFAULT�h
            //�M�p����敪�F�@@�hDEFAULT�h(�Œ�)
            WEB3GentradeHandlingOrderCond l_gentradeHandlingOrderCond= 
                new WEB3GentradeHandlingOrderCond(
                l_subAccount.getInstitution().getInstitutionCode(),
                ProductTypeEnum.EQUITY,
                WEB3FuturesOptionDivDef.DEFAULT,
                WEB3MarginTradingDivDef.DEFAULT,
                l_market.getMarketCode());
            //�戵�\���s�������擾����B
            String[] l_exeCondiotions = l_gentradeHandlingOrderCond.getHandlingPossibleExecCond();

            //get�v�w�l�p���s�����ꗗ(String[], EqTypeOrderUnit)
            //�v�w�l�p�̎��s�����ꗗ���擾����B
            //[get�v�w�l�p���s�����ꗗ()�Ɏw�肷�����]
            //���s�����ꗗ�F�@@�戵�\���s�����擾()�̖߂�l
            //�����P�ʁF�@@�����P��
            String[] l_strWLimitExecCondLists =
                WEB3EquityDataAdapter.getWLimitExecutionConditionTypeList(
                    l_exeCondiotions,
                    l_orderUnit);

            //�o���܂Œ������戵�\�ł��邩�𔻒肷��B
            //is�o����܂Œ����戵�\( )
            boolean l_isOrderUntilDeadLinePossibleHandling = l_gentradeHandlingOrderCond.isOrderUntilDeadLinePossibleHandling();
            //is�o����܂Œ����P��(�����P��)
            boolean l_isCarriedOrderUnit = l_orderManager.isCarriedOrderUnit((EqTypeOrderUnit)l_orderUnit);
            //����t���[
            //�o����܂Œ����戵�\(�戵�\��������.is�o����܂Œ����戵�\( ) == true)�A
            //���� �����Ώے������o����܂Œ����ł���iis�o����܂Œ����P��( )==true�j�̏ꍇ
            //�̂݁A�ȉ��̏��������{����B
            Date l_datStartDay = null;
            Date l_datEndDay = null;
            Date[] l_datExpirationDate = null;
            if (l_isOrderUntilDeadLinePossibleHandling && l_isCarriedOrderUnit)
            {
                Date l_datBizDate = this.getCarriedOrderFromDate(l_orderUnit);
				l_datEndDay = l_gentradeHandlingOrderCond.getOrderUntilDeadLineEndDay(l_datBizDate);
                l_datStartDay = l_gentradeHandlingOrderCond.getOrderUntilDeadLineStartDay(null,l_datEndDay);
                l_datExpirationDate = l_gentradeHandlingOrderCond.getExpirationDateHoliday(l_datStartDay);    
                                
            }

            // �g���v���_�N�g�}�l�[�W���擾
            WEB3EquityProductManager l_productManager =
                (WEB3EquityProductManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
            
            // �������̎擾
            WEB3EquityProductQuote l_productQuote =
                l_productManager.getDisplayEquityProductQuote(
                    (EqTypeTradedProduct)l_validatedTradedProduct,
                    (WEB3GentradeSubAccount)l_subAccount);
        
            // �����敪
            String l_strQuoteTypeDiv = l_productQuote.getQuoteTypeDiv();
            // ����
            double l_dblCurrentPrice = l_productQuote.getQuote();
            // �O����
            double l_dblChange = l_productQuote.getComparedPreviousDay();
            // �������\����
            Timestamp l_tsCurrentPriceTime = l_productQuote.getQuoteTime();
            
            WEB3MarginContractUnit[] l_marginContractUnits =
                this.createContractUnitByOrder(l_orderUnit);
            WEB3MarginCloseMarginChangeInputResponse l_response = (WEB3MarginCloseMarginChangeInputResponse)l_request.createResponse();
            //�M�p��������ԍϒ������̓��X�|���X�ɉ��L�̒ʂ�v���p�e�B���Z�b�g����B
            
            //�����P���敪�ꗗ
            String[] l_orderPriceDivList = l_orderManager.getOrderPriceDivs(
                ((WEB3GentradeSubAccount)l_subAccount).getWeb3GenBranch(),
                l_validatedTradedProduct);
            l_response.orderPriceDivList = l_orderPriceDivList;
            
            //�l�i�����敪�ꗗ
            String[] l_strPriceCondList;
            if (l_isCarriedOrderUnit || !WEB3OrderingConditionDef.DEFAULT.equals(l_orderUnitRow.getOrderConditionType()))
            {
                String[] l_strPriceCondListOfCarriedOrderUnit = { WEB3PriceConditionDef.DEFAULT }; 
                l_strPriceCondList = l_strPriceCondListOfCarriedOrderUnit;
            }
            else
            {
                l_strPriceCondList = l_gentradeHandlingOrderCond.getHandlingPriceCond();
            }
            l_response.priceCondList = l_strPriceCondList;
            
            //���s�����ꗗ�F�@@�����\�Ȍ��̂�(**1)���Z�b�g
            //(**1)�@@�����Ώے������o����܂Œ������́A�t�w�l�����ł���ꍇ�A"������"�݂̂��Z�b�g�B
            //�ȊO�A�戵�\��������.�戵�\���s�����擾( )�̖߂�l�z����Z�b�g�B
            if (l_isCarriedOrderUnit ||
				WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType())) 
            {
            	String[] l_strNoCondition = {WEB3ExecutionConditionDef.NO_CONDITION};
                l_response.execCondList = l_strNoCondition;
            }
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
            {
                l_response.execCondList = l_strWLimitExecCondLists;
            }
            else
            {
                l_response.execCondList = l_exeCondiotions;
            }

            //W�w�l�p���s�����ꗗ�F�@@getW�w�l�p���s�����ꗗ( )�̖߂�l
            l_response.wlimitExecCondList = l_strWLimitExecCondLists;

            //�L�������J�n���F�@@(**2)�戵�\��������.get�o����܂Œ����J�n��( )�̖߂�l
            //�L�������ŏI���F�@@(**2)�戵�\��������.get�o����܂Œ����ŏI��( )�̖߂�l
            //�L���������j���ꗗ�F�@@(**2)�戵�\��������.get�����������j���ꗗ( )�̖߂�l�z��
            //(**2)�@@�o����܂Œ����戵�\(�戵�\��������.is�o����܂Œ����戵�\( ) == true)�A
            //        ���@@�����Ώے������o����܂Œ����ł���i�g�����������}�l�[�W��.is�o����܂Œ����P��( )==true�j�̏ꍇ�̂ݐݒ�B
            //        �ȊO�Anull��ݒ�B
            l_response.expirationStartDate = l_datStartDay;
            l_response.expirationEndDate = l_datEndDay;
            l_response.holidayList = l_datExpirationDate;
            //����I���x���s��R�[�h�ꗗ�F�@@������ԊǗ�.get�s��ǌx���s��( )�̖߂�l�z��
            l_response.messageSuspension = l_tradeCloseMarket;
            
            //�C���T�C�_�[�x���\���t���O
            l_response.insiderWarningFlag = l_boolIsInsider;
            
            //�����R�[�h�F�@@��������.�����R�[�h
            EqtypeProductRow l_validateProductRow =(EqtypeProductRow) 
                l_product.getDataSourceObject();
            l_response.productCode = l_validateProductRow.getProductCode();            
            //�������F�@@��������.�������@@
            //      ������������validate�����R�[�h�i�M�p�j( )�̖߂�l�ɂĎ擾
            l_response.productName = l_validateProductRow.getStandardName();
            //�s��R�[�h�F�@@�s��.�s��R�[�h
            //�@@�@@            ���s���validate�s��R�[�h( )�̖߂�l�ɂĎ擾
            l_response.marketCode = l_market.getMarketCode();
            //�����敪�F�@@
            //  �i�����P��.getTaxType() == TaxTypeEnum.��ʁj�̏ꍇ�A�h��ʁh
            //  �i�����P��.getTaxType() == TaxTypeEnum.����C�܂��́@@TaxTypeEnum.���芎���򒥎��j�̏ꍇ�A�h����h
            if (TaxTypeEnum.NORMAL.equals(l_orderUnit.getTaxType()))
            {
                l_response.taxType = WEB3TaxTypeDef.NORMAL;
            }
            if (TaxTypeEnum.SPECIAL.equals(l_orderUnit.getTaxType()) || 
                TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_orderUnit.getTaxType()))
            {
                l_response.taxType = WEB3TaxTypeDef.SPECIAL;
            }
            //����敪�F�@@
            //�i�����P��.getOrderType() == OrderTypeEnum.�����ԍϒ����i���ԍρj�j�̏ꍇ�A�����ԍϒ����i���ԍρj
            //  �i�����P��.getOrderType() == OrderTypeEnum.�����ԍϒ����i���ԍρj�j�̏ꍇ�A�����ԍϒ����i���ԍρj
            if (OrderTypeEnum.CLOSE_MARGIN_LONG.equals(l_orderUnit.getOrderType()))
            {
                l_response.tradingType = String.valueOf(OrderTypeEnum.CLOSE_MARGIN_LONG.intValue());
            }
            if (OrderTypeEnum.CLOSE_MARGIN_SHORT.equals(l_orderUnit.getOrderType()))
            {
                l_response.tradingType = String.valueOf(OrderTypeEnum.CLOSE_MARGIN_SHORT.intValue());
            }
            //�ٍρF
            //  �M�p����ٍ�.�ٍϋ敪�F�@@�����P��.�ٍϋ敪
            //  �M�p����ٍ�.�ٍϊ����l�F�@@�����P��.�ٍϊ����l
            WEB3MarginRepaymentUnit l_repayment = new WEB3MarginRepaymentUnit();
            l_repayment.repaymentDiv = l_orderUnitRow.getRepaymentType();
            l_repayment.repaymentTimeLimit = String.valueOf(l_orderUnitRow.getRepaymentNum());
            
            l_response.repayment = l_repayment;
            
            //���Ϗ����敪�F�@@�����P��.���Ϗ����敪
            l_response.closingOrder = l_orderUnitRow.getClosingOrderType();
            //�������׈ꗗ�F�@@create��������ByOrder( )�̖߂�l
            l_response.contractUnits = l_marginContractUnits;
            //���X�|���X.�������� �ɂ́A�����Ώۂ̒����P��.���Ϗ����敪=="�����_��"�ȊO�̏ꍇ�̂݁A
            //�����P��.getQuantity()���Z�b�g����B�i�ȊO�́Anull���Z�b�g����j
            if (!WEB3ClosingOrderDef.RANDOM.equals(l_orderUnitRow.getClosingOrderType()))
            {
                l_response.orderQuantity = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getQuantity());                
            }
            else
            {
                l_response.orderQuantity = null;
            }

            //���o�������F�@@�����P��.getExecutedQuantity( )�̖߂�l
            if(l_orderUnitRow.getExecutedQuantity() == 0)
            {
                l_response.partContQuantity = null;
            }
            else
            {
                l_response.partContQuantity = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getExecutedQuantity());
            }
            
			//�����P���敪�F�@@�i�����P��.isMarketOrder() == true�j�̏ꍇ�h���s�h�A�ȊO�h�w�l�h�B
			if (l_orderUnit.isMarketOrder()) 
			{
				l_response.orderPriceDiv =  WEB3OrderPriceDivDef.MARKET_PRICE;
				l_response.limitPrice = null;
			}
			else
			{
				l_response.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
				//�����P���F�@@�����P��.getLimitPrice( )�̖߂�l
				l_response.limitPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getLimitPrice());
			}    
            
            //�l�i�����F�@@�����P��.�l�i����
            l_response.priceCondType = l_orderUnitRow.getPriceConditionType();
            
            //���s�����F�@@�����P��.���s����
            l_response.execCondType = l_orderManager.getExecutionConditionTypeSonar(l_orderUnitRow.getExecutionConditionType());
            //���������敪�F�@@�iis�o����܂Œ����P��( ) == false�j�̏ꍇ�h��������h�A�ȊO�h�o����܂Œ����h
            if (!l_isCarriedOrderUnit)
            {
                l_response.expirationDateType = WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
            }
            else
            { 
                l_response.expirationDateType = WEB3OrderExpirationDateTypeDef.CARRIED_ORDER;
            }
            //�����L�������F�@@�����P��.�����������t
            if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_response.expirationDateType))
            {
                l_response.expirationDate = null;
            }
            else
            {
                l_response.expirationDate = WEB3DateUtility.toDay(l_orderUnitRow.getExpirationDate());
            }
            //���������敪�F�@@�����P��.��������
            l_response.orderCondType = l_orderUnitRow.getOrderConditionType();
            //�������P��.�������� == �h�t�w�l�h�̏ꍇ�����ȊO�̏ꍇ�A���L���ڂ�null���Z�b�g����B
            //          �t�w�l�p���������P���F�@@�����P��.�t�w�l��l
            //          �t�w�l�p�����������Z�q�F�@@�����P��.�����������Z�q
            if (!WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(
                l_orderUnitRow.getOrderConditionType()))
            {
                l_response.stopOrderCondPrice = null;
                l_response.stopOrderCondOperator = null;
            }
            else
            {
                l_response.stopOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());
                l_response.stopOrderCondOperator = l_orderUnitRow.getOrderCondOperator();         
            }
            //�������P��.�������� == �hW�w�l�h�̏ꍇ�����ȊO�̏ꍇ�A���L���ڂ�null���Z�b�g����B 
            //  W�w�l�p���������P���F�@@�����P��.�t�w�l��l
            //  W�w�l�p�����������Z�q�F�@@�����P��.�����������Z�q
            if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(
                l_orderUnitRow.getOrderConditionType()))
            {
                l_response.wlimitOrderCondPrice = null;
                l_response.wlimitOrderCondOperator = null;
                l_response.wLimitOrderPriceDiv = null;
                l_response.wLimitPrice = null;
                l_response.wlimitExecCondType = null;
            }
            else
            {
                l_response.wlimitOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());
                l_response.wlimitOrderCondOperator = l_orderUnitRow.getOrderCondOperator();
                //W�w�l�p�����P���敪�F�@@�i�����P��.�iW�w�l�j�����w�l == 0�j�̏ꍇ�h���s�h�A�ȊO�h�w�l�h�B
                //  W�w�l�p�����P���F�@@�����P��.�iW�w�l�j�����w�l
                if (l_orderUnitRow.getWLimitPrice() == 0)
                {
                    l_response.wLimitOrderPriceDiv = 
                        WEB3EquityWlimitOrderPriceDivDef.WLIMIT_ORDER_PRICE_DIV_MARKET_PRICE;
                    l_response.wLimitPrice = null;
                }
                else
                {
                    l_response.wLimitOrderPriceDiv = 
                        WEB3EquityWlimitOrderPriceDivDef.WLIMIT_ORDER_PRICE_DIV_LIMIT_PRICE;
                    l_response.wLimitPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getWLimitPrice());
                }

                //W�w�l�p���s�����F�@@�g�����������}�l�[�W��.get���s�����iSONAR�j(�����P��.�iW�w�l�j���s�����j�̖߂�l
                l_response.wlimitExecCondType =
                    l_orderManager.getExecutionConditionTypeSonar(l_orderUnitRow.getWLimitExecCondType());
            }

            //W�w�l�p�L����ԋ敪�F�@@�����f�[�^�A�_�v�^.getW�w�l�p�L����ԋ敪(�����P��)�̖߂�l
            l_response.wlimitEnableStatusDiv =
                WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_orderUnit);

            //W�w�l�p�֑ؑO�����P���F�@@�����f�[�^�A�_�v�^.getW�w�l�p�֑ؑO�����P��(�����P��)�̖߂�l
            l_response.wlimitBefChgLimitPrice =
                WEB3EquityDataAdapter.getWLimitBefSwitchPrice(l_orderUnit);

            //W�w�l�p�֑ؑO���s�����F�@@�����f�[�^�A�_�v�^.getW�w�l�p�֑ؑO���s����(�����P��)�̖߂�l
            l_response.wlimitBefChgExecCondType =
                WEB3EquityDataAdapter.getWLimitBefSwitchExecCondType(l_orderUnit);

            //�����������敪�F�@@�����P��.����������
            l_response.orgOrderCondType = l_orderUnitRow.getOrgOrderConditionType();

            //�����������P���F�@@�����P��.���t�w�l��l
            if (!l_orderUnitRow.getOrgStopOrderPriceIsNull())
            {
                l_response.orgOrderCondPrice =
                    WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getOrgStopOrderPrice());
            }

            //�������������Z�q�F�@@�����P��.�������������Z�q
            l_response.orgOrderCondOperator = l_orderUnitRow.getOrgOrderCondOperator();

            //��W�w�l�p�����P���敪�F�@@�����f�[�^�A�_�v�^.get��W�w�l�p�����P���敪�i�����P�ʁj�̖߂�l
            String l_strOrgWlimitOrderPriceDiv =
                WEB3EquityDataAdapter.getOrgWLimitOrderPriceDiv(l_orderUnit);
            l_response.orgWlimitOrderPriceDiv = l_strOrgWlimitOrderPriceDiv;

            //��W�w�l�p�����P���F�@@��W�w�l�p�����P���敪 ==
            //"�w�l"�̏ꍇ�̂݁A�����f�[�^�A�_�v�^.get��W�w�l�p�����P���i�����P�ʁj�̖߂�l���Z�b�g�B
            if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrgWlimitOrderPriceDiv))
            {
                l_response.orgWlimitPrice =
                    WEB3EquityDataAdapter.getOrgWLimitOrderPrice(l_orderUnit);
            }

            //��W�w�l�p���s�����F�@@�����f�[�^�A�_�v�^.get��W�w�l�p���s�����i�����P�ʁj�̖߂�l
            l_response.orgWlimitExecCondType =
                WEB3EquityDataAdapter.getOrgWLimitExecCondType(l_orderUnit);

            //�T�Z��n����F�@@�����P��.�T�Z��n���
            l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getEstimatedPrice());
            //�������ϗ��R
            l_response.forcedSettleReason = l_orderUnitRow.getForcedSettleReasonType();
            // �����敪
            l_response.currentPriceDiv = l_strQuoteTypeDiv;
            //����(���ݒl)�F
            l_response.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblCurrentPrice);
            //�O����F
            l_response.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_dblChange);
            //�������(�������\����)�F
            l_response.currentPriceTime = l_tsCurrentPriceTime;
            
            //��񍀖ڂ̐ݒ�d�l�́A�ȉ��̒ʂ�B
            //�@@���ݒl�F�@@�擾�������������������.get���ݒl()�̖߂�l���Z�b�g
            l_response.boardCurrentPrice = l_productQuote.getBoardCurrentPrice();

            //�@@���ݒl�����F�@@�擾�������������������.get���ݒl����()�̖߂�l���Z�b�g
            l_response.boardCurrentPriceTime = l_productQuote.getBoardCurrentPriceTime();

            //�@@���ݒl�敪�F�@@�擾�������������������.get���ݒl�敪()�̖߂�l���Z�b�g
            l_response.boardCurrentPriceDiv = l_productQuote.getBoardCurrentPriceDiv();

            //�@@���ݒl�O����F�@@�擾�������������������.get���ݒl�O����()�̖߂�l���Z�b�g
            l_response.boardChange = l_productQuote.getBoardChange();

            //�@@�o�����F�@@�擾�������������������.get�o����()�̖߂�l���Z�b�g
            l_response.volume = l_productQuote.getVolume();

            //�@@�o���������F�@@�擾�������������������.get�o��������()�̖߂�l���Z�b�g
            l_response.volumeTime = l_productQuote.getVolumeTime();

            //�@@���C�z�l�^�C�g���敪�F�@@�擾�������������������.get���C�z�l�^�C�g���敪()�̖߂�l���Z�b�g
            l_response.askPriceTitle = l_productQuote.getAskPriceTitle();

            //�@@���C�z�l�F�@@�擾�������������������.get���C�z�l()�̖߂�l���Z�b�g
            l_response.askPrice = l_productQuote.getAskPrice();

            //�@@���C�z�l�����F�@@�擾�������������������.get���C�z�l����()�̖߂�l���Z�b�g
            l_response.askPriceTime = l_productQuote.getAskPriceTime();

            //�@@���C�z�l�^�C�g���敪�F�@@�擾�������������������.get���C�z�l�^�C�g���敪()�̖߂�l���Z�b�g
            l_response.bidPriceTitle = l_productQuote.getBidPriceTitle();

            //�@@���C�z�l�F�@@�擾�������������������.get���C�z�l()�̖߂�l���Z�b�g
            l_response.bidPrice = l_productQuote.getBidPrice();

            //�@@���C�z�l�����F�@@�擾�������������������.get���C�z�l����()�̖߂�l���Z�b�g
            l_response.bidPriceTime = l_productQuote.getBidPriceTime();

            //�@@��l�i�F�@@�擾�������������������.get��l�i()�̖߂�l���Z�b�g
            l_response.basePrice = l_productQuote.getBasePrice();

            //(**1)�@@�����Ώے������o����܂Œ����ł���ꍇ�A"������"�݂̂��Z�b�g�B�ȊO�A�戵�\��������.�戵�\���s�����擾( )�̖߂�l�z����Z�b�g�B
            //(**2)�@@�o����܂Œ����戵�\(�戵�\��������.is�o����܂Œ����戵�\( ) == true)�A
            //        ���@@�����Ώے������o����܂Œ����ł���i�g�����������}�l�[�W��.is�o����܂Œ����P��( )==true�j�̏ꍇ�̂ݐݒ�
            //        �ȊO�Anull��ݒ�B
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
        WEB3MarginCloseMarginChangeInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getChangeOrderUnit(WEB3MarginCloseMarginChangeInputRequest)";
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
     * (create��������ByOrder)<BR>
     * �����̒����P�ʂɊ֘A����M�p����������ׂ�<BR>
     * �ꗗ���쐬����B<BR>
     * <BR>
     * �g�����������}�l�[�W��.create����ByOrder(�����P��.�����P��ID)��<BR>
     * delegate����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B
     * @@return WEB3MarginContractUnit[]
     * @@throws WEB3BaseException
     */
    protected WEB3MarginContractUnit[] createContractUnitByOrder(
        EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createContractUnitByOrder(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        WEB3MarginContractUnit[] l_marginContractUnits =
            l_orderManager.createContractUnitByOrder(
                l_orderUnit.getOrderUnitId());
        
        log.exiting(STR_METHOD_NAME);
        return l_marginContractUnits;
    }
}
@
