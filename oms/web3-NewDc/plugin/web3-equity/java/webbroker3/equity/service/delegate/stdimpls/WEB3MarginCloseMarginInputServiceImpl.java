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
filename	WEB3MarginCloseMarginInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����ԍϓ��̓T�[�r�XImpl(WEB3MarginCloseMarginInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/08 ������ (���u) �V�K�쐬
                 : 2006/11/27 �đo�g(���u) ���f��1005
                 : 2007/01/11 ��іQ (���u) ���f�� No.1083
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3TaxTypeDef;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityProductQuote;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3MarginClientRequestService;
import webbroker3.equity.WEB3MarginContractUnitContractPriceComparator;
import webbroker3.equity.WEB3MarginContractUnitOpenDateComparator;
import webbroker3.equity.WEB3MarginContractUnitFirstOpenDateComparator;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.equity.define.WEB3MarginTradeTypeDef;
import webbroker3.equity.message.WEB3MarginCloseMarginInputRequest;
import webbroker3.equity.message.WEB3MarginCloseMarginInputResponse;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.equity.message.WEB3MarginRepaymentUnit;
import webbroker3.equity.message.WEB3MarginSortKey;
import webbroker3.equity.WEB3MarginTempContractUnit;
import webbroker3.equity.service.delegate.WEB3MarginCloseMarginInputService;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

/**
 * �i�M�p����ԍϓ��̓T�[�r�XImpl�j�B<BR>
 * <BR>
 * �M�p����ԍϓ��̓T�[�r�X�����N���X�B
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginCloseMarginInputServiceImpl extends WEB3MarginClientRequestService implements WEB3MarginCloseMarginInputService
{
    /**
     * (���O�o�̓��[�e�B���e�B)�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3MarginCloseMarginInputServiceImpl.class);

    /**
     * (execute)�B<BR>
     * <BR>
     * �M�p����̕ԍϓ��͉�ʕ\���T�[�r�X�����{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^���u�M�p����ԍϒ������̓��N�G�X�g�v�^�ɕϊ����A<BR>
     * get�ԍϓ��͉��()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4105D75B0341
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;
        if (l_request == null)
        {
            log.error(STR_METHOD_NAME + "�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        if (l_request instanceof WEB3MarginCloseMarginInputRequest) //validate����
        {
            l_response = getCloseMarginInputScreen((WEB3MarginCloseMarginInputRequest) l_request);
        }
        else
        {
            log.error(STR_METHOD_NAME + "�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (get�ԍϓ��͉��)�B<BR>
     * <BR>
     * �M�p����̕ԍϓ��͉�ʕ\���T�[�r�X�����{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�M�p����ԍϓ��̓T�[�r�X�jget�ԍϓ��͉�ʂP�v<BR>
     * �u�i�M�p����ԍϓ��̓T�[�r�X�jget�ԍϓ��͉�ʂQ�v<BR>
     * �u�i�M�p����ԍϓ��̓T�[�r�X�jget�ԍϓ��͉�ʂR�v<BR>
     * �Q�ƁB<BR>
     *  ========================================================<BR>
     *   �V�[�P���X�}(�u�i�M�p����ԍϓ��̓T�[�r�X�jget�ԍϓ��͉�ʂQ�v) : (25*)(*) ����t���[�@@���ώc���`�F�b�N<BR>   
     *   (*) ����t���[<BR>   
     *   ���ω\�c���`�F�b�N<BR>   
     *   ������������ʂ����͂��ꂽ<BR>   
     *   �i���N�G�X�g�f�[�^.�������� != null�j�ꍇ�A���ω\�c���`�F�b�N���s���B<BR>   
     *<BR>   
     *   �|�������������ω\�c�����傫��<BR>   
     *   �i���������i�c���j�i�FBigDecimal�j > 0�j�ꍇ�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00657<BR>
     *   ==========================================================<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return WEB3MarginCloseMarginInputResponse
     * @@roseuid 4105D70303DE
     */
    protected WEB3MarginCloseMarginInputResponse getCloseMarginInputScreen(WEB3MarginCloseMarginInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getCloseMarginInputScreen(WEB3MarginCloseMarginInputRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3MarginCloseMarginInputResponse l_response = null;
        try
        {
            //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
            l_request.validate();
            //�⏕�����I�u�W�F�N�g���擾����B
            WEB3GentradeSubAccount l_subAccount = this.getSubAccount();

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
            WEB3EquityProductManager l_productManager = (WEB3EquityProductManager) l_tradingModule.getProductManager();

            //�����I�u�W�F�N�g���擾����B
            WEB3EquityContract l_contract = this.getContract(l_request);

            //�s��R�[�h�F�@@get����().getMarketId()�ɊY������s��.�s��R�[�h
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(l_contract.getMarketId());

            //�s��R�[�h��ThreadLocal�ɃZ�b�g�������B
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_market.getMarketCode());

            //�M�p������������ʃ`�F�b�N�����{����B
            EqtypeContractRow l_contractRow = (EqtypeContractRow) l_contract.getDataSourceObject();
            l_orderManager.validateMarginOrder(l_subAccount, l_contractRow.getRepaymentType());

            //����������擾����B
            WEB3EquityTradedProduct l_tradedProduct = null;
            try
            {
                l_tradedProduct = (WEB3EquityTradedProduct) l_contract.getTradedProduct();
            }
            catch (RuntimeSystemException l_rse)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01966,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "����ID=[" + Long.toString(l_contract.getContractId()) + "]�̌����ɕR�t�������������",
                    l_rse);
            }

            //��n�����擾����B
            Date l_datDeliveryDate = l_tradedProduct.getDailyDeliveryDate();

            //��������J�݃`�F�b�N�����{����B
            l_orderManager.validateMarginSpecialAccountOpen(l_subAccount, l_contractRow.getTaxType(), l_datDeliveryDate);

            //�s��I�u�W�F�N�g���擾����B
            WEB3GentradeMarket l_gentradeMarket = (WEB3GentradeMarket) l_tradedProduct.getMarket();
            //�s��`�F�b�N�����{����B
            WEB3GentradeMarket l_market1 = (WEB3GentradeMarket) l_orderManager.validateMarket(l_gentradeMarket.getMarketCode(), l_subAccount.getInstitution().getInstitutionCode());

            //�����I�u�W�F�N�g���擾����B
            WEB3EquityProduct l_product = (WEB3EquityProduct) l_contract.getProduct();

            //�������ǂ����𔻕ʂ���B
            boolean l_isShort = l_contract.isShort();
            
            String l_strRepaymentType= l_contractRow.getRepaymentType();
            //�����R�[�h�̑��݃`�F�b�N�����{����B���݂���ꍇ�͖����I�u�W�F�N�g��ԋp����B
            WEB3EquityProduct l_equityProduct =
                (WEB3EquityProduct) l_orderManager.validateProductCode(l_product.getProductCode(), l_subAccount.getInstitution().getInstitutionCode(), l_contractRow.getRepaymentType());

            //��������̃`�F�b�N���s���B
            WEB3EquityTradedProduct l_equityTradedProduct =
                l_orderManager.validateTradedProductForMarginTrading(
                    l_subAccount,
                    l_equityProduct,
                    l_market1,
                    l_subAccount.getWeb3GenBranch(),
                    l_strRepaymentType,
                    OrderCategEnum.CLOSE_MARGIN,
                    l_isShort);

            //�戵�\�s�ꂩ���`�F�b�N����B
            l_orderManager.validateHandlingMarket(l_subAccount.getWeb3GenBranch(), l_equityTradedProduct, l_market.getMarketCode(), l_contractRow.getRepaymentType(), l_contractRow.getRepaymentNum());

            //�C���T�C�_�[�`�F�b�N
            boolean l_boolIsInsider = l_orderManager.isInsiderMessageSuspension(l_subAccount, l_product.getProductId());
            
			OrderTypeEnum l_orderTypeEnum = null;
            //validate�ڋq�����ʎ����~
            if ( l_isShort == false )
            {
            	//�����ԍϒ������Z�b�g
				l_orderTypeEnum = OrderTypeEnum.CLOSE_MARGIN_LONG;
            	
            }
            else
            {
            	//�����ԍϒ������Z�b�g
				l_orderTypeEnum = OrderTypeEnum.CLOSE_MARGIN_SHORT;
            }
                       
			l_orderManager.validateAccountProductOrderStop(
				l_subAccount, l_product.getProductId(), l_orderTypeEnum);
            
            //�������擾����B
            double l_dblCurrentPrice = l_productManager.getCurrentPrice(l_tradedProduct);

            WEB3MarginContractUnit[] l_unitTemp =
                this.createMarginContractUnitList(l_request, l_dblCurrentPrice);
            
            this.sortMarginContractUnitList(l_unitTemp, l_request);

            List l_lstContractUnit = new ArrayList();
            //�M�p�����������Temp[]����A�M�p�����������[]���쐬����B
            for (int i = 0; i < l_unitTemp.length; i++)
            {
                WEB3MarginContractUnit l_contractUnit = new WEB3MarginContractUnit();
                
                l_contractUnit.id = l_unitTemp[i].id;
                l_contractUnit.openDate = l_unitTemp[i].openDate;
                l_contractUnit.contractPrice = l_unitTemp[i].contractPrice;
                l_contractUnit.contractQuantity = l_unitTemp[i].contractQuantity;
                l_contractUnit.contractExecPrice = l_unitTemp[i].contractExecPrice;
                l_contractUnit.appraisalProfitLoss = l_unitTemp[i].appraisalProfitLoss;
                l_contractUnit.orderQuantity = l_unitTemp[i].orderQuantity;
                l_contractUnit.partContQuantity = l_unitTemp[i].partContQuantity;
                l_contractUnit.settlePriority = l_unitTemp[i].settlePriority;
                
                l_lstContractUnit.add(l_contractUnit);
            }
            WEB3MarginContractUnit[] l_unit =
                new WEB3MarginContractUnit[l_unitTemp.length];
            l_lstContractUnit.toArray(l_unit);
           
            //(*) ����t���[������������ʂ����͂��ꂽ�i���N�G�X�g�f�[�^.�������� != null�j�ꍇ
            //�������������Ϗ��ʏ��ɁA�e�����Ɋ��蓖�Ă�B
            BigDecimal l_bdQuantity = null;
            if (l_request.orderQuantity != null)
            {
                //�����ݒ�d�l�́A�V�[�P���X�̃m�[�g�A���J�[�Q�ƁB
                l_bdQuantity = new BigDecimal(l_request.orderQuantity);
            }
            //��������[]�i��������List.toArray()�̖߂�l�j�e�v�f����LOOP����
            for (int i = 0; i < l_unit.length; i++)
            {

                //�M�p����������׃I�u�W�F�N�g�̃v���p�e�B�ɒl���Z�b�g����B
                //�@@�i���N�G�X�g�f�[�^.�������� == null�j�̏ꍇ�A��������[index].������
                if (l_request.orderQuantity == null)
                {
                    l_unit[i].orderQuantity = l_unit[i].contractQuantity;
                }
                //�@@�i���N�G�X�g�f�[�^.�������� != null�j && �i���������i�c���j == 0�j�̏ꍇ�A0
                else if (l_request.orderQuantity != null && (l_bdQuantity.longValue() <= 0))
                {
                    l_unit[i].orderQuantity = "0";
                }
                //�@@�i���N�G�X�g�f�[�^.�������� != null�j && �i���������i�c���j >= ��������[index].�������j�̏ꍇ�A��������[index].������
                else if (l_request.orderQuantity != null && l_bdQuantity.compareTo(new BigDecimal(l_unit[i].contractQuantity)) >= 0)
                {
                    l_unit[i].orderQuantity = l_unit[i].contractQuantity;
                }
                //�@@�i���N�G�X�g�f�[�^.�������� != null�j && �i���������i�c���j < ��������[index].�������j�̏ꍇ�A���������i�c���j
                else if (l_request.orderQuantity != null && l_bdQuantity.compareTo(new BigDecimal(l_unit[i].contractQuantity)) < 0)
                {
                    l_unit[i].orderQuantity = "" + l_bdQuantity.longValue();
                }
                l_unit[i].settlePriority = "" + (i + 1);
                
                if (l_request.orderQuantity != null)
                {
					l_bdQuantity = l_bdQuantity.subtract(new BigDecimal(l_unit[i].contractQuantity));
                }
            }

            //���ω\�c���`�F�b�N
            //������������ʂ����͂��ꂽ�i���N�G�X�g�f�[�^.�������� != null�j�ꍇ�A���ω\�c���`�F�b�N���s���B
            if (l_request.orderQuantity != null)
            {
                //�������������ω\�c�����傫���i���������i�c���j�i�FBigDecimal�j > 0�j�ꍇ�A��O���X���[����B
                if (l_bdQuantity.doubleValue() > 0)
                {
                    log.error("�������ʂ��ԍω\�c�����ʂ𒴂��̃G���[�B");

                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00299, this.getClass().getName() + STR_METHOD_NAME);
                }
            }
            //�����͈ȉ��̒ʂ�ݒ肷��B
            String[] l_strCloseMarket = WEB3GentradeTradingTimeManagement.getTradeCloseMarket(l_subAccount.getWeb3GenBranch(), ProductTypeEnum.EQUITY, l_contractRow.getRepaymentType());

            //�戵�\��������
            WEB3GentradeHandlingOrderCond l_orderCond = new WEB3GentradeHandlingOrderCond(
                l_subAccount.getInstitution().getInstitutionCode(),
                ProductTypeEnum.EQUITY,
                WEB3FuturesOptionDivDef.DEFAULT,
                WEB3MarginTradingDivDef.DEFAULT,
                l_market.getMarketCode());

            //�����P���敪�ꗗ
            String[] l_strOrderPriceDivList = l_orderManager.getOrderPriceDivs(l_subAccount.getWeb3GenBranch(), l_tradedProduct);
            
            //�l�i�����敪�ꗗ
            String[] l_strPriceCondList = l_orderCond.getHandlingPriceCond(); 


            //�戵�\���s�������擾����B
            String[] l_strExecCond = l_orderCond.getHandlingPossibleExecCond();

            //�戵�\���������敪���擾����B
            String[] l_strExpirationDateType = l_orderCond.getHandlingPossibleExpirationDateType();

            //�戵�\�����������擾����B
            String[] l_strPossibleOrderCond = l_orderCond.getHandlingPossibleOrderCond();

            //get�v�w�l�p���s�����ꗗ(String[], String[])
            //���s�����ꗗ�F�@@�戵�\��������.�戵�\���s�����擾( )�̖߂�l
            //���������ꗗ�F�@@�戵�\��������.�戵�\���������擾( )�̖߂�l
            String[] l_strWLimitExecutionConditionTypeList =
                WEB3EquityDataAdapter.getWLimitExecutionConditionTypeList(l_strExecCond, l_strPossibleOrderCond);

            //�o���܂Œ������戵�\�ł��邩�𔻒肷��B
            boolean l_blnHanding = l_orderCond.isOrderUntilDeadLinePossibleHandling();

            //(*1)����t���[
            //�o����܂Œ����戵�\�̏ꍇ�̂݁A�ȉ��̏��������{����B
            //(�戵�\��������.is�o����܂Œ����戵�\( ) == true)

            //�����J�n��
            Date l_datStartDay = null;

            //�����ŏI��
            Date l_datEndDay = null;

            //�����������̏j���ꗗ
            Date[] l_datDateHolidays = null;

            if (l_blnHanding)
            {
                //�o����܂Œ����J�n�����擾����B
                l_datStartDay = l_orderCond.getOrderUntilDeadLineStartDay();
                //�o����܂Œ����ŏI�����擾����B
                l_datEndDay = l_orderCond.getOrderUntilDeadLineEndDay();
                //�o����܂Œ����������̏j���ꗗ���擾����B
                l_datDateHolidays = l_orderCond.getExpirationDateHoliday();
            }

            //���������擾����  
            WEB3EquityProductQuote l_productQuote = l_productManager.getDisplayEquityProductQuote(l_tradedProduct, l_subAccount);

            // �����敪���擾����B
            String l_strQuoteTypeDiv = l_productQuote.getQuoteTypeDiv();
            
            //�����i���ݒl�j���擾����B
            double l_dblCurrentPrice1 = l_productQuote.getQuote();
            
            //�O������擾����B
            double l_dblChange = l_productQuote.getComparedPreviousDay();

            //������ԁi�������\���ԁj���擾����B
            Timestamp l_timCurrentPriceTime = l_productQuote.getQuoteTime();

            //���X�|���X���쐬����B
            l_response = (WEB3MarginCloseMarginInputResponse)l_request.createResponse();
            
			//�����P���敪�ꗗ
			l_response.orderPriceDivList = l_strOrderPriceDivList;
			
			//�l�i�����敪�ꗗ
			l_response.priceCondList = l_strPriceCondList;     

            //�M�p����ԍϒ������̓��X�|���X�ɉ��L�̒ʂ�v���p�e�B���Z�b�g����B
            //���s�����ꗗ�F�@@�戵�\��������.�戵�\���s�����擾( )�̖߂�l�z��
            l_response.execCondList = l_strExecCond;

            //�v�w�l�p���s�����ꗗ:�����f�[�^�A�_�v�^.get�v�w�l�p���s�����ꗗ
            l_response.wlimitExecCondList = l_strWLimitExecutionConditionTypeList;

            //�L�������J�n���F�@@(**1)�戵�\��������.get�o����܂Œ����J�n��( )�̖߂�l
            l_response.expirationStartDate = WEB3DateUtility.toDay(l_datStartDay);

            //�L�������ŏI���F�@@(**1)�戵�\��������.get�o����܂Œ����ŏI��( )�̖߂�l
            l_response.expirationEndDate = WEB3DateUtility.toDay(l_datEndDay);

            //�L���������j���ꗗ�F�@@(**1)�戵�\��������.get�����������j���ꗗ( )�̖߂�l�z��
            l_response.holidayList = l_datDateHolidays;

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

            //�����R�[�h�F�@@��������.�����R�[�h
            l_response.productCode = l_equityProduct.getProductCode();

            //�������F�@@��������.�������@@
            EqtypeProductRow l_productRow = (EqtypeProductRow) l_equityProduct.getDataSourceObject();
            l_response.productName = l_productRow.getStandardName();

            //�s��R�[�h�F�@@�s��.�s��R�[�h
            l_response.marketCode = l_market1.getMarketCode();

            //�����敪�F
            String l_strTaxType = null;
            //�i����.�ŋ敪 == TaxTypeEnum.��ʁj�̏ꍇ�A�h��ʁh
            //�i����.�ŋ敪 == TaxTypeEnum.����C�܂��́@@TaxTypeEnum.���芎���򒥎��j�̏ꍇ�A�h����h
            if (TaxTypeEnum.NORMAL.equals(l_contractRow.getTaxType()))
            {
                l_strTaxType = WEB3TaxTypeDef.NORMAL;
            }
            else if (TaxTypeEnum.SPECIAL.equals(l_contractRow.getTaxType()) || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_contractRow.getTaxType()))
            {
                l_strTaxType = WEB3TaxTypeDef.SPECIAL;
            }
            l_response.taxType = l_strTaxType;

            //����敪�F
            //�@@�i����.isLong() == true�j�̏ꍇ�A�����ԍϒ����i���ԍρj
            //�i����.isLong() == false�j�̏ꍇ�A�����ԍϒ����i���ԍρj
            String l_strTradeType = null;
            if (l_contract.isLong())
            {
                l_strTradeType = WEB3MarginTradeTypeDef.CLOSE_SELL_MARGIN;
            }
            else
            {
                l_strTradeType = WEB3MarginTradeTypeDef.CLOSE_BUY_MARGIN;
            }
            l_response.tradingType = l_strTradeType;

            WEB3MarginRepaymentUnit l_repaymentUnit = new WEB3MarginRepaymentUnit();
            l_repaymentUnit.repaymentDiv = l_strRepaymentType;
            l_repaymentUnit.repaymentTimeLimit =  "" + l_contractRow.getRepaymentNum();
            //�M�p����ٍ�.�ٍϋ敪�F�@@����.�ٍϋ敪            
            //�M�p����ٍ�.�ٍϊ����l�F�@@����.�ٍϊ����l
            l_response.repayment = l_repaymentUnit;

            //���������敪�ꗗ�F�@@�戵�\���������敪�擾( )�̖߂�l
            l_response.expirationDateTypeList = l_strExpirationDateType;

            //���������敪�ꗗ�F�@@�戵�\���������擾( )�̖߂�l
            l_response.orderCondTypeList = l_strPossibleOrderCond;

            // �����敪
            l_response.currentPriceDiv = l_strQuoteTypeDiv;
            
            //����(���ݒl)�F
            l_response.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblCurrentPrice1);

            //�O����F
            l_response.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_dblChange);

            //�������(�������\����)�F
            l_response.currentPriceTime = l_timCurrentPriceTime;

            //�������׈ꗗ�F�@@��������[]�i��������List.toArray()�̖߂�l�j
            l_response.contractUnits = l_unit;

        }
        catch (DataFindException l_dfe)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_dfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_dfe.getMessage(), l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_dqe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_dqe.getMessage(), l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME, l_dne.getMessage(), l_dne);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get����)<BR>
     * �������擾���ԋp����B<BR>
     * <BR>
     * �����|�W�V�����}�l�[�W��.get����()���R�[�����A<BR>
     * �߂�l��ԋp����B<BR>
     * <BR>
     * [get����()�Ɏw�肷�����]<BR>
     * ����ID�F�@@���N�G�X�g�f�[�^.ID[0]<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �M�p����ԍϒ������̓��N�G�X�g�I�u�W�F�N�g�B
     * @@return WEB3EquityContract
     * @@throws WEB3BaseException
     */
    protected WEB3EquityContract getContract(
        WEB3MarginCloseMarginInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getContract(WEB3MarginCloseMarginInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
        WEB3EquityContract l_contract = null;
        try
        {
            l_contract =
                (WEB3EquityContract)l_positionManager.getContract(
                    Long.parseLong(l_request.id[0]));
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_contract;
    }
    
    /**
     * (create�������׈ꗗ)<BR>
     * ���N�G�X�g�f�[�^���M�p����������ׂ̈ꗗ��<BR>
     * �쐬����B<BR>
     * <BR>
     * �P�j�@@ArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�p�����[�^.���N�G�X�g�f�[�^.ID�̗v�f�����A<BR>
     * �@@�ȉ��̏��������{����B<BR>
     * �@@�Q�|�P�j�@@�����|�W�V�����}�l�[�W��.get����()�ɂ�<BR>
     * �@@�@@�������擾����B<BR>
     * <BR>
     * �@@�@@[get����()�Ɏw�肷�����]<BR>
     * �@@�@@�@@����ID�F�@@�����Ώۂ̗v�f<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�M�p�����������Temp�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �@@�Q�|�R�j�@@���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@ID�F�@@����.����ID<BR>
     * �@@�@@���� = ����.����<BR>
     * �@@�@@���P�� = ����.���P��<BR>
     * �@@�@@������ = �����ϊ���(*1)<BR>
     * �@@�@@����� = ����.get�����(�����ϊ���)<BR>
     * �@@�@@�]�����v =<BR>
     * �@@�@@�@@����.get�]�����v�i�������o��l���j(�p�����[�^.����, �����ϊ���)<BR>
     * �@@�@@�������� = 0�@@�������\�b�h�̊O�ŃZ�b�g�B<BR>
     * �@@�@@���o������ = null<BR>
     * �@@�@@�������� = ����.��������<BR>
     * �@@�@@<BR>
     * �@@�@@(*1)����.������ - ���b�N������(*2)<BR>
     * �@@�@@(*2)����.getLockedQuantity()�ɂĎ擾�B<BR>
     * <BR>
     * �@@�Q�|�S�j�@@ArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B<BR>
     * <BR>
     * �R�j�@@ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * @@param l_request - (�N�G�X�g�f�[�^)<BR>
     * �M�p����ԍϒ������̓��N�G�X�g�I�u�W�F�N�g�B
     * @@param l_dblCurrentPrice - (����)<BR>
     * �����B
     * @@return WEB3MarginContractUnit[]
     * @@throws WEB3BaseException
     */
    protected WEB3MarginContractUnit[] createMarginContractUnitList(
        WEB3MarginCloseMarginInputRequest l_request,
        double l_dblCurrentPrice)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createMarginContractUnitList(WEB3MarginCloseMarginInputRequest, double)";
        log.entering(STR_METHOD_NAME);
        
        List l_lstContractUnit = new ArrayList();
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
        for (int i = 0;i < l_request.id.length;i++)
        {
            WEB3EquityContract l_contract = null;
            try
            {
                l_contract =
                    (WEB3EquityContract)l_positionManager.getContract(
                        Long.parseLong(l_request.id[i]));
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
            
            WEB3MarginTempContractUnit l_contractUnit = new WEB3MarginTempContractUnit();
            EqtypeContractRow l_contractRow = (EqtypeContractRow)l_contract.getDataSourceObject();

            l_contractUnit.id = Long.toString(l_contract.getContractId());
            l_contractUnit.openDate = WEB3DateUtility.toDay(l_contract.getOpenDate());
            l_contractUnit.contractPrice =
                WEB3StringTypeUtility.formatNumber(l_contract.getContractPrice());
            double l_dblContractQuantity =
                l_contract.getQuantity() - l_contract.getLockedQuantity();
            l_contractUnit.contractQuantity =
                WEB3StringTypeUtility.formatNumber(l_dblContractQuantity);
            l_contractUnit.contractExecPrice =
                WEB3StringTypeUtility.formatNumber(
                    l_contract.getContractAmount(l_dblContractQuantity));
            double l_dblAppraisalProfitLoss =
                l_contract.getAppraisalProfitOrLossExpenses(
                    l_dblCurrentPrice,
                    l_dblContractQuantity);
            l_contractUnit.appraisalProfitLoss =
                WEB3StringTypeUtility.formatNumber(l_dblAppraisalProfitLoss);
            l_contractUnit.orderQuantity = "0";
            l_contractUnit.partContQuantity = null;
            l_contractUnit.firstOpenDate = WEB3DateUtility.toDay(l_contractRow.getFirstOpenDate());
            l_lstContractUnit.add(l_contractUnit);
        }
        
        WEB3MarginTempContractUnit[] l_contractUnits =
            new WEB3MarginTempContractUnit[l_lstContractUnit.size()];
        l_lstContractUnit.toArray(l_contractUnits);
        
        log.exiting(STR_METHOD_NAME);
        return l_contractUnits;
    }
    
    /**
     * (sort�������׈ꗗ)<BR>
     * �����̌������׈ꗗ���\�[�g����B<BR>
     * <BR>
     * �������e�́A<BR>
     * �V�[�P���X�}<BR>
     * �u�i�M�p����ԍϓ��̓T�[�r�X�jget�ԍϓ��͉�ʂQ�v��<BR>
     * WEB3ArraysUtility.sort()�̃m�[�g�A���J�[���Q�ƁB<BR>
     * @@param l_contractUnits - (�������׈ꗗ)<BR>
     * �M�p����������ׂ̔z��B�i���ۂ̌^�F�M�p�����������Temp�^�j
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �M�p����ԍϒ������̓��N�G�X�g�I�u�W�F�N�g�B
     * @@throws WEB3BaseException
     */
    protected void sortMarginContractUnitList(
        WEB3MarginContractUnit[] l_contractUnits,
        WEB3MarginCloseMarginInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " sortMarginContractUnitList(WEB3MarginContractUnit[], WEB3MarginCloseMarginInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        int l_intSortKeyCnt = l_request.sortKeys.length;
        int index = 0;
        List l_lstComparators = new ArrayList();
            
        for (int i = 0; i < l_intSortKeyCnt; i++)
        {
            WEB3MarginSortKey l_sortKey = l_request.sortKeys[index];
                
            if (WEB3EquityKeyItemDef.OPEN_DATE.equals(l_sortKey.keyItem))
            {
                l_lstComparators.add(
                    new WEB3MarginContractUnitOpenDateComparator(l_sortKey.ascDesc));
                l_lstComparators.add(
                    new WEB3MarginContractUnitFirstOpenDateComparator(WEB3AscDescDef.ASC));
                l_intSortKeyCnt++;
                i++;
            }
            else if (WEB3EquityKeyItemDef.CONTRACT_PRICE.equals(l_sortKey.keyItem))
            {
                l_lstComparators.add(
                    new WEB3MarginContractUnitContractPriceComparator(l_sortKey.ascDesc));
            }
            index++;
        }
        Comparator[] l_comparators = new Comparator[l_intSortKeyCnt];
        l_lstComparators.toArray(l_comparators);
        
        WEB3ArraysUtility.sort(l_contractUnits, l_comparators);
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
