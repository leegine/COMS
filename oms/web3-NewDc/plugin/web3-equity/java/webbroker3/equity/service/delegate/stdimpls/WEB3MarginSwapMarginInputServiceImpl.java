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
filename	WEB3MarginSwapMarginInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����������n���̓T�[�r�X����(WEB3MarginSwapMarginInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/20 ������ (���u) �V�K�쐬
                 : 2006/12/14 ������@@(���u)�@@���f��No.1083
*/

package webbroker3.equity.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityContract;
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
import webbroker3.equity.WEB3MarginTempContractUnit;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.equity.define.WEB3MarginTradeTypeDef;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.equity.message.WEB3MarginRepaymentUnit;
import webbroker3.equity.message.WEB3MarginSortKey;
import webbroker3.equity.message.WEB3MarginSwapMarginInputRequest;
import webbroker3.equity.message.WEB3MarginSwapMarginInputResponse;
import webbroker3.equity.service.delegate.WEB3MarginSwapMarginInputService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeEquityMarginDivDef;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �i�M�p����������n���̓T�[�r�XImpl�j�B<BR>
 * <BR>
 * �M�p����������n���̓T�[�r�X�����N���X�B
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginSwapMarginInputServiceImpl extends WEB3MarginClientRequestService implements WEB3MarginSwapMarginInputService 
{
    /**
     * (���O�o�̓��[�e�B���e�B)�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginSwapMarginInputServiceImpl.class); 
    
    /**
     * (�R���X�g���N�^)�B
     * @@roseuid 4140066E0010
     */
    public WEB3MarginSwapMarginInputServiceImpl() 
    {
    }
    
    /**
     * (execute)�B<BR>
     * <BR>
     * �M�p����������n���̓T�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^���u�M�p����������n�������̓��N�G�X�g�v�^�ɕϊ����A<BR>
     * get�������n���͉��()���\�b�h���R�[������B<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 407CA5B103D4
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = getClass().getName() + "." + "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3MarginSwapMarginInputRequest)
        {
            //this.validateOrder((WEB3MarginSwapMarginInputRequest) l_request);
            l_response = this.getSwapMarginInputScreen((WEB3MarginSwapMarginInputRequest) l_request);
            log.exiting(STR_METHOD_NAME);
        }
        else
        {
            log.error(STR_METHOD_NAME + "�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        //return l_request.createResponse();
        return l_response;
    }
    
    /**
     * (get�������n���͉��)�B<BR>
     * <BR>
     * �M�p����������n���͉�ʕ\���T�[�r�X�����{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�M�p����������n���̓T�[�r�X�jget�������n���͉�ʂP�v<BR>
     * �u�i�M�p����������n���̓T�[�r�X�jget�������n���͉�ʂQ�v<BR>
     * �u�i�M�p����������n���̓T�[�r�X�jget�������n���͉�ʂR�v<BR>
     * �Q�ƁB<BR> 
     * <BR>
     *  ========================================================<BR>
     *   �V�[�P���X�}(�u�i�M�p����������n���̓T�[�r�X�jget�������n���͉�ʂQ�v) <BR>
     * �@@�@@: (25*)(*) ����t���[�@@���ώc���`�F�b�N<BR>   
     *   (*) ����t���[<BR>
     *   ���ω\�c���`�F�b�N<BR>
     *   ������������ʂ����͂��ꂽ<BR>
�@@�@@ *    �i���N�G�X�g�f�[�^.�������� != null�j�ꍇ�A���ω\�c���`�F�b�N���s���B<BR>
     *    �|�������������ω\�c�����傫��<BR>
�@@�@@ *   �i���������i�c���j�i�FBigDecimal�j > 0�j�ꍇ�A��O���X���[����B<BR>   
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00657<BR>
     *   ==========================================================<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return WEB3MarginSwapMarginInputResponse<BR>
     * @@roseuid 41070977009B
     */
    protected WEB3MarginSwapMarginInputResponse getSwapMarginInputScreen(
        WEB3MarginSwapMarginInputRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getSwapMarginInputScreen(WEB3MarginSwapMarginInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {   
            log.error("parameter is null type");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                   this.getClass().getName() + "." + STR_METHOD_NAME);                 
        }
        
        log.debug("�i�M�p����������n���̓T�[�r�X�jget�������n���͉�ʂP�i�Q�Ɓj");
        // �M�p����������n���� / �i�M�p����������n���̓T�[�r�X�jget�������n���͉�ʂP�i�Q�Ɓj
        //2 �M�p����ԍϒ������̓��N�G�X�g�ŁAvalidate()���R�[��
        l_request.validate();
        
        //3 �M�p����������n���̓T�[�r�XImpl�ŁAget�⏕����() 
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        try
        {
            WEB3EquityContract l_contract = this.getContract(l_request);

            //5 ������ԊǗ��ŁAreset�s��R�[�h(String)�𒲗p
            //�����͈ȉ��̒ʂ�ɐݒ肷��B 
            //  �s��R�[�h�F�@@get����(����ID)�Ŏ擾��������.�s��ID�̎s��I�u�W�F�N�g.�s��R�[�h
            //WEB3GentradeMarket l_gentradeMarket = new WEB3GentradeMarket(l_contract.getMarketId());
            WEB3GentradeFinObjectManager l_objectManager = (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_getMarket = l_objectManager.getMarket(l_contract.getMarketId());
            String l_strMarketCode = l_getMarket.getMarketCode();
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
        
            //6 validate�M�p����(�⏕����, String)(�g�����������}�l�[�W��::validate�M�p����)
            //�����͈ȉ��̒ʂ�w�肷��B 
            //  �⏕�����F�@@�iget�⏕����()�̖߂�l�j 
            //  �ٍϋ敪�F�@@�iget����()�Ŏ擾��������.�ٍϋ敪�j 
            WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqtypeContractRow l_eqtypeContractRow = (EqtypeContractRow)l_contract.getDataSourceObject();
            // �ٍϋ敪
            String l_strRepaymentType = l_eqtypeContractRow.getRepaymentType();
            l_orderManager.validateMarginOrder(l_subAccount,l_strRepaymentType);
        
            //7 ����������擾,�����ŁAgetTradedProduct()
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
            
            //8 getDailyDeliveryDate( )(�������::getDailyDeliveryDate)
            Date l_datDeliveryDate = l_tradedProduct.getDailyDeliveryDate();
            
            //9 validate��������J�݁i�M�p�j(�⏕����, TaxTypeEnum, Date)(�g�����������}�l�[�W��::validate��������J�݁i�M�p�j)
            //�����͈ȉ��̒ʂ�w�肷��B 
            //  �⏕�����F�@@�iget�⏕����()�̖߂�l�j 
            //  �ŋ敪�F�@@����.�ŋ敪 
            //  ��n���F�@@�igetDailyDeliveryDate()�̖߂�l�j
            l_orderManager.validateMarginSpecialAccountOpen(
                l_subAccount,
                l_eqtypeContractRow.getTaxType(),
                l_datDeliveryDate);
            
            //10 ��������ŁAgetMarket()(�������::getMarket)
            WEB3GentradeMarket l_market = (WEB3GentradeMarket)l_tradedProduct.getMarket();
            
            //11 �����ŁAgetProduct()
            Product l_product = l_contract.getProduct();
            
            //12 �����ŁAisShort()
            boolean l_blnShort = l_contract.isShort();
            
            //13 validate�����R�[�h�i�M�p�j(java.lang.String, String, String)(�g�����������}�l�[�W��::validate�����R�[�h�i�M�p�j)
            //�����͈ȉ��̒ʂ�w�肷��B 
            //  �����R�[�h�F�@@getProduct()�̖߂�l.getProductCode() 
            //  �،���ЃR�[�h�F�@@�⏕����.�،���ЃR�[�h 
            //  �ٍϋ敪�F�@@����.�ٍϋ敪 
            EqtypeProductRow l_productRow = (EqtypeProductRow)l_product.getDataSourceObject();
            WEB3EquityProduct l_equityProduct = (WEB3EquityProduct)l_orderManager.validateProductCode(
                l_productRow.getProductCode(),
                l_subAccount.getInstitution().getInstitutionCode(),
                l_strRepaymentType);
            
            //14 validate��������i�M�p�j(��������, �s��, ���X, String, OrderCategEnum, boolean)(�g�����������}�l�[�W��::validate��������i�M�p�j)
            //�����͈ȉ��̒ʂ�w�肷��B 
            //  ���������F�@@validate�����R�[�h�i�M�p�j()�̖߂�l 
            //  �s��F�@@getMarket()�̖߂�l 
            //  ���X�F�@@�⏕����.get����X() 
            //  �ٍϋ敪�F�@@����.�ٍϋ敪 
            //  �����J�e�S���F�@@OrderCategEnum.�ԍϒ����iCLOSE_MARGIN�j�Œ� 
            //  is�����F�@@isShort()�̖߂�l
            WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
            WEB3EquityTradedProduct l_marginTrading =  (WEB3EquityTradedProduct)l_orderManager.validateTradedProductForMarginTrading(
                l_subAccount,
                l_equityProduct,
                l_market,
                l_branch,
                l_strRepaymentType,
                OrderCategEnum.SWAP_MARGIN,
                l_blnShort);

            //15 validate�戵�\�s��i�M�p�j(���X, �������, String, String, double)(�g�����������}�l�[�W��::validate�戵�\�s��i�M�p�j)
            //�����͈ȉ��̒ʂ�w�肷��B 
            //  ���X�F�@@�⏕����.get����X() 
            //  ��������F�@@validate��������i�M�p�j()�̖߂�l 
            //  �s��R�[�h�F�@@getMarket().getMarketCode() 
            //  �ٍϋ敪�F�@@����.�ٍϋ敪 
            //  �ٍϊ����l�F�@@����.�ٍϊ����l 
            l_orderManager.validateHandlingMarket(
                l_branch,
                l_marginTrading,
                l_market.getMarketCode().toString(),
                l_strRepaymentType,
                l_eqtypeContractRow.getRepaymentNum());
            
            //�ڋq�����ʎ����~
            l_orderManager.validateAccountProductOrderStop(
                l_subAccount, l_equityProduct.getProductId(),
                l_blnShort ? OrderTypeEnum.SWAP_MARGIN_SHORT : OrderTypeEnum.SWAP_MARGIN_LONG);
        
            //16 �C���T�C�_�[�`�F�b�N(���݌v�̂��߁A��Ή�)
            //is�C���T�C�_�[�x���\��
            boolean l_boolIsInsider = l_orderManager.isInsiderMessageSuspension(l_subAccount, l_product.getProductId());
        
            log.debug("�i�M�p����������n���̓T�[�r�X�jget�������n���͉�ʂQ�i�Q�Ɓj");
            // �i�M�p����������n���̓T�[�r�X�jget�������n���͉�ʂQ�i�Q�Ɓj
        
            //3 get����(EqTypeTradedProduct)(�g���v���_�N�g�}�l�[�W��::get����)
            //�����͈ȉ��̒ʂ�w�肷��B 
            //  ��������F�@@�igetTradedProduct()�̖߂�l�j 
            WEB3EquityProductManager l_productManager = (WEB3EquityProductManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
            double l_dblCurrentPrice = l_productManager.getCurrentPrice((EqTypeTradedProduct)l_tradedProduct);
            
            WEB3MarginContractUnit[] l_unitTemp =
                this.createMarginContractUnitList(l_request, l_dblCurrentPrice);
            
            this.sortMarginContractUnitList(l_unitTemp, l_request);

            List l_lstContractUnits = new ArrayList(); 
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
                
                l_lstContractUnits.add(l_contractUnit);
            }
            WEB3MarginContractUnit[] l_contractUnits =
                new WEB3MarginContractUnit[l_unitTemp.length];
            l_lstContractUnits.toArray(l_contractUnits);

            //19 ����t���[
            BigDecimal l_bdOrderQuantity = null;
            if (l_request.orderQuantity != null)
            {
                //20 ��������(�c��):igDecimal(arg0�i=���N�G�X�g�f�[�^.���������j : String)
                l_bdOrderQuantity = new BigDecimal(l_request.orderQuantity);
            }
            
            //21 ��������[]�i��������List.toArray()�̖߂�l�j�e�v�f����LOOP����
            //�i���������i�c���j
            int l_intRequestDataLth = l_contractUnits.length;
            for (int i=0; i<l_intRequestDataLth; i++)
            {
                //24 (*2) �v���p�e�B�Z�b�g
                //�i���N�G�X�g�f�[�^.�������� == null�j�̏ꍇ�A��������[index].������
                if (l_request.orderQuantity == null)
                {
                    //��������
                    l_contractUnits[i].orderQuantity = l_contractUnits[i].contractQuantity;
                }
                else
                {
                    //�i���N�G�X�g�f�[�^.�������� != null�j && �i���������i�c���j == 0�j�̏ꍇ�A0
                    if (l_bdOrderQuantity.doubleValue() <= 0)
                    {
                        //��������
                        l_contractUnits[i].orderQuantity = String.valueOf(0);
                    }
                    // �i���N�G�X�g�f�[�^.�������� != null�j && �i���������i�c���j >= ��������[index].�������j�̏ꍇ�A��������[index].������
                    else if (l_bdOrderQuantity.doubleValue() >= Double.parseDouble(l_contractUnits[i].contractQuantity))
                    {
                        //��������
                        l_contractUnits[i].orderQuantity = l_contractUnits[i].contractQuantity;
                    }
                    // �i���N�G�X�g�f�[�^.�������� != null�j && �i���������i�c���j < ��������[index].�������j�̏ꍇ�A���������i�c���j
                    else
                    {
                        //��������
                        l_contractUnits[i].orderQuantity = "" + l_bdOrderQuantity;
                    }
                }

                // ���Ϗ���
                l_contractUnits[i].settlePriority = String.valueOf(i + 1);

                //22 ����t���[
                if (l_request.orderQuantity != null)
                {
                    //23 ��������(�c��):subtract(arg0�i=��������[index].�������j : BigDecimal)
                    l_bdOrderQuantity = l_bdOrderQuantity.subtract(new BigDecimal(l_contractUnits[i].contractQuantity));
                }
            }

            //25 ����t���[�@@���ώc���`�F�b�N
            //   ������������ʂ����͂��ꂽ<BR>
            //      �i���N�G�X�g�f�[�^.�������� != null�j�ꍇ�A���ω\�c���`�F�b�N���s���B<BR>
            //   �|�������������ω\�c�����傫��<BR>
            //      �i���������i�c���j�i�FBigDecimal�j > 0�j�ꍇ�A��O���X���[����B<BR>   
            //          class: WEB3BusinessLayerException<BR>
            //          tag:   BUSINESS_ERROR_00657<BR>
            if (l_request.orderQuantity != null)
            {
                if (l_bdOrderQuantity.doubleValue() > 0)
                {
                    log.error("�������ʂ����ω\�c�����ʂ𒴂��̃G���[�B");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00657,
                        this.getClass().getName() + STR_METHOD_NAME);
                }

            }

            log.debug("�i�M�p����������n���̓T�[�r�X�jget�������n���͉�ʂR�i�Q�Ɓj");
            // �i�M�p����������n���̓T�[�r�X�jget�������n���͉�ʂR�i�Q�Ɓj
            // get�����\�z
            Double l_dblSwapLongTradingPower =
                this.getActualReceiptTradingPower(l_subAccount, l_contract);
            
            //4 get�s��ǌx���s��(���X, ProductTypeEnum, String)(������ԊǗ�::get�s��ǌx���s��)
            //�����͈ȉ��̒ʂ�ݒ肷��B 
            //  ���X�F�@@�⏕����.get����X() 
            //  �����^�C�v�F�@@ProductTypeEnum."����" 
            //  �M�p����敪�F�@@����.�ٍϋ敪 
            String[] l_strTradeCloseMarket = WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
                l_branch,
                ProductTypeEnum.EQUITY,
                l_strRepaymentType);
            
            // ���������擾����B
            WEB3EquityProductQuote l_productQuote =
                l_productManager.getDisplayEquityProductQuote((EqTypeTradedProduct)l_tradedProduct, l_subAccount);
            
            // �����敪���擾����
            String l_strQuoteTypeDiv = l_productQuote.getQuoteTypeDiv();
            
            //  �����i���ݒl�j���擾����
            double l_dblQuote = l_productQuote.getQuote();
            
            //  �O������擾����
            double l_dblChange = l_productQuote.getComparedPreviousDay();
            
            //  �������(�������\����)���擾����
            Timestamp l_currentPriceTime = l_productQuote.getQuoteTime();
            
            //9 is��������J��(�⏕����, String)(�ڋq::is��������J��)
            //�����͈ȉ��̒ʂ�w�肷��B 
            //  �⏕�����F�@@this.get�⏕����( ) 
            //  �ŋ敪�^�C�v�F�@@"1�F��������"
            WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
            boolean l_blnSpecialAccount = l_account.isSpecialAccountEstablished(l_subAccount,WEB3GentradeEquityMarginDivDef.EQUITY);
            
            //10 createResponse( )(�M�p����������n�������̓��N�G�X�g::createResponse)
            WEB3MarginSwapMarginInputResponse l_response = (WEB3MarginSwapMarginInputResponse)l_request.createResponse();
            
            log.debug("�i�M�p����������n���̓T�[�r�X�j�v���p�e�B�Z�b�g");
            //11  �v���p�e�B�Z�b�g 
            //�����\�z�F�@@calc�M�p�����\�z()�̖߂�l
            if (l_dblSwapLongTradingPower != null)
            {
                l_response.swapLongTradingPower =
                    WEB3StringTypeUtility.formatNumber(l_dblSwapLongTradingPower.doubleValue());
            }
            
            //�����R�[�h�F�@@��������.�����R�[�h
            l_response.productCode = l_equityProduct.getProductCode();
            
            //�������F�@@��������.�������@@
            //              ������������validate�����R�[�h�i�M�p�j( )�̖߂�l�ɂĎ擾
            EqtypeProductRow l_equityProductRow = (EqtypeProductRow) l_equityProduct.getDataSourceObject();
            l_response.productName = l_equityProductRow.getStandardName();
            
            //�s��R�[�h�F�@@�s��.�s��R�[�h
            //�@@�@@            ���s���getMarket( )�ɂĎ擾
            l_response.marketCode = l_market.getMarketCode();
            
            //�����敪�F�@@
            //  �i����.�ŋ敪 == TaxTypeEnum.��ʁj�̏ꍇ�A"���"
            //  �i����.�ŋ敪 == TaxTypeEnum.����C�܂��́@@TaxTypeEnum.���芎���򒥎��j�̏ꍇ�A"����"
            if (TaxTypeEnum.NORMAL.equals(l_eqtypeContractRow.getTaxType()))
            {
                String l_strNormal = WEB3TaxTypeSpecialDef.NORMAL;
                l_response.taxType = l_strNormal;
            }
            else if (TaxTypeEnum.SPECIAL.equals(l_eqtypeContractRow.getTaxType())
                || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_eqtypeContractRow.getTaxType()))
            {
                String l_strSpecial = WEB3TaxTypeSpecialDef.SPECIAL;
                l_response.taxType = l_strSpecial;
            }

            //����敪�F�@@
            //  �i����.isLong() == true�j�̏ꍇ�A����
            //  �i����.isLong() == false�j�̏ꍇ�A���n
            if (l_contract.isLong() == true)
            {
                l_response.tradingType = WEB3MarginTradeTypeDef.GENBIKI_ORDER;
            }
            else
            {
                l_response.tradingType = WEB3MarginTradeTypeDef.GENWADASI_ORDER;
            }

            //�ٍρF
            //  �M�p����ٍ�.�ٍϋ敪�F�@@����.�ٍϋ敪
            //  �M�p����ٍ�.�ٍϊ����l�F�@@����.�ٍϊ����l
            WEB3MarginRepaymentUnit l_repayment = new WEB3MarginRepaymentUnit();
            l_repayment.repaymentDiv = l_strRepaymentType;
            l_repayment.repaymentTimeLimit = String.valueOf(l_eqtypeContractRow.getRepaymentNum());
            l_response.repayment = l_repayment;

            //�����挻�n�������敪�ꗗ�F
            //  �E�i����.�ŋ敪 == TaxTypeEnum.��� && ����.isLong() == true �����j�̏ꍇ�A"���"
            //    �����������̏ꍇ�A��ʌ����̌������������ۗ̕L���Y�ɍ�������邱�Ƃ͂ł��Ȃ��B
            //  �E��L�ȊO�̏ꍇ�A
            //     �iis��������J��() == false�j�̏ꍇ�A"���"
            //     �iis��������J��() == true�j�̏ꍇ�A�o"���"�C"����"�p
            if (TaxTypeEnum.NORMAL.equals(l_eqtypeContractRow.getTaxType())
                    && l_contract.isLong() == true)
            {
                String[] l_strNormal = {WEB3TaxTypeSpecialDef.NORMAL};
                l_response.swapTaxTypeList = l_strNormal;
            }
            else if (l_blnSpecialAccount == false)
            {
                String[] l_strNormal = {WEB3TaxTypeSpecialDef.NORMAL};
                l_response.swapTaxTypeList = l_strNormal;
            }
            else if(l_blnSpecialAccount == true)
            {
                String[] l_strNormalSpecial = {
                    WEB3TaxTypeSpecialDef.NORMAL,
                    WEB3TaxTypeSpecialDef.SPECIAL};
                l_response.swapTaxTypeList = l_strNormalSpecial;
            }
            
            //�����敪
            l_response.currentPriceDiv = l_strQuoteTypeDiv;
            //����(���ݒl)�F
            l_response.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblQuote);
            
            //�O����F
            l_response.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_dblChange);
            
            //�������(�������\����)�F
            l_response.currentPriceTime = l_currentPriceTime;
            
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

            //�������׈ꗗ�F�@@��������[]�i��������List.toArray()�̖߂�l�j
            l_response.contractUnits = l_contractUnits;
            
            //����I���x���s��R�[�h�ꗗ�F�@@������ԊǗ�.get�s��ǌx���s��( )�̖߂�l�z��
            l_response.messageSuspension = l_strTradeCloseMarket;
            
            //�C���T�C�_�[�x���\���t���O
            l_response.insiderWarningFlag = l_boolIsInsider;
            
            return l_response;
        }
        catch (NotFoundException l_nfe)
        {
            log.error(l_nfe.getMessage(),l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_nfe.getMessage(), l_nfe);
        }
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
     * �M�p����������n�������̓��N�G�X�g�I�u�W�F�N�g�B
     * @@return WEB3EquityContract
     * @@throws WEB3BaseException
     */
    protected WEB3EquityContract getContract(
        WEB3MarginSwapMarginInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getContract(WEB3MarginSwapMarginInputRequest)";
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
     * �M�p����������n�������̓��N�G�X�g�I�u�W�F�N�g�B
     * @@param l_dblCurrentPrice - (����)<BR>
     * �����B
     * @@return WEB3MarginContractUnit[]
     * @@throws WEB3BaseException
     */
    protected WEB3MarginContractUnit[] createMarginContractUnitList(
        WEB3MarginSwapMarginInputRequest l_request,
        double l_dblCurrentPrice)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createMarginContractUnitList(WEB3MarginSwapMarginInputRequest, double)";
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
            l_contractUnit.firstOpenDate = l_contractRow.getFirstOpenDate();
            
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
     * �u�i�M�p����������n���̓T�[�r�X�jget�������n���͉�ʂQ�v��<BR>
     * WEB3ArraysUtility.sort()�̃m�[�g�A���J�[���Q�ƁB<BR>
     * @@param l_contractUnits - (�������׈ꗗ)<BR>
     * �M�p����������ׂ̔z��B�i���ۂ̌^�F�M�p�����������Temp�^�j
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �M�p����������n�������̓��N�G�X�g�I�u�W�F�N�g�B
     * @@throws WEB3BaseException
     */
    protected void sortMarginContractUnitList(
        WEB3MarginContractUnit[] l_contractUnits,
        WEB3MarginSwapMarginInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " sortMarginContractUnitList(WEB3MarginContractUnit[], WEB3MarginSwapMarginInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        int l_intSortKeyCnt = l_request.sortKeys.length;
        int index = 0;
        List l_lstComparators = new ArrayList();
            
        for (int i = 0;i < l_intSortKeyCnt;i++)
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
    
    /**
     * (get�����\�z)<BR>
     * �����\�z���擾����B<BR>
     * <BR>
     * �P�j�@@�����̌���.isLong()==false�inot�����j�̏ꍇ�́Anull��ԋp����B<BR>
     * �@@�@@�@@�ȊO�A�ȉ��̏������s���B<BR>
     * <BR>
     * �Q�j�@@����]�̓T�[�r�X.get�M�p�����\�z(�����̕⏕����)�̖߂�l��ԋp����B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B
     * @@param l_contract - (����)<BR>
     * �����I�u�W�F�N�g�B
     * @@return Double
     * @@throws WEB3BaseException
     */
    protected Double getActualReceiptTradingPower(
        WEB3GentradeSubAccount l_subAccount,
        WEB3EquityContract l_contract)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getActualReceiptTradingPower(WEB3GentradeSubAccount, WEB3EquityContract)";
        log.entering(STR_METHOD_NAME);
        
        if (!l_contract.isLong())
        {
            return null;
        }
        
        WEB3TPTradingPowerService l_tradingPowerService
                = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        double l_dblSwapLongTradingPower =
            l_tradingPowerService.getActualReceiptTradingPower(l_subAccount);
        
        log.exiting(STR_METHOD_NAME);
        return new Double(l_dblSwapLongTradingPower);
    }
}
@
