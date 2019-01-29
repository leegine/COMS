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
filename	WEB3MarginSwapMarginServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����������n�T�[�r�X����(WEB3MarginSwapMarginServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/20 ������ (���u) �V�K�쐬
                   2004/12/10 �X��   (SRA)  �c�Č��Ή�
                   2005/01/06 ����   (SRA)  JavaDoc�C��
Revesion History : 2007/12/10 ��іQ (���u) �d�l�ύX���f��No.1240
Revesion History : 2008/10/06 ���� (���u) ���f��No.1323
*/

package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3MarginClientRequestService;
import webbroker3.equity.WEB3MarginSwapContractOrderSpec;
import webbroker3.equity.WEB3MarginSwapMarginUpdateInterceptor;
import webbroker3.equity.message.WEB3MarginCloseMarginContractUnit;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.equity.message.WEB3MarginSwapMarginCompleteRequest;
import webbroker3.equity.message.WEB3MarginSwapMarginCompleteResponse;
import webbroker3.equity.message.WEB3MarginSwapMarginConfirmRequest;
import webbroker3.equity.message.WEB3MarginSwapMarginConfirmResponse;
import webbroker3.equity.service.delegate.WEB3MarginSwapMarginRequestAdapter;
import webbroker3.equity.service.delegate.WEB3MarginSwapMarginService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondRow;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.tradingpower.define.WEB3TPTradingPowerErrorDivDef;
import webbroker3.tradingpower.define.WEB3TPResultAttentionObjectionTypeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �i�M�p����������n�T�[�r�XImpl�j�B<BR>
 * <BR>
 * �M�p����������n�T�[�r�X�����N���X
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginSwapMarginServiceImpl extends WEB3MarginClientRequestService implements WEB3MarginSwapMarginService 
{
    /**
     * (���O�o�̓��[�e�B���e�B)�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginSwapMarginServiceImpl.class); 
    
    /**
     * (�R���X�g���N�^)�B<BR>
     * @@roseuid 4140066D01F9
     */
    public WEB3MarginSwapMarginServiceImpl() 
    {
    }
    
    /**
     * (execute)�B<BR>
     * <BR>
     * �M�p����������n�T�[�r�X���������{����B<BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Avalidate����()���\�b�h�A<BR>
     * submit����()���\�b�h�̂����ꂩ���R�[������B<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 405692590048
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = getClass().getName() + "." + "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3MarginSwapMarginConfirmRequest)
        {
            //this.validateOrder((WEB3MarginSwapMarginConfirmRequest) l_request);
            l_response = this.validateOrder((WEB3MarginSwapMarginConfirmRequest) l_request);
            log.exiting(STR_METHOD_NAME);
        }
        else if (l_request instanceof WEB3MarginSwapMarginCompleteRequest)
        {
            //this.submitOrder((WEB3MarginSwapMarginCompleteRequest) l_request);
            l_response = this.submitOrder((WEB3MarginSwapMarginCompleteRequest) l_request);
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
        
        log.exiting(STR_METHOD_NAME);
        //return l_request.createResponse();
        return l_response;
    }
    
    /**
     * (validate����)�B<BR>
     * <BR>
     * �M�p����������n�����R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�M�p����������n�T�[�r�X�jvalidate�����P�v�y��<BR>
     * �u�i�M�p����������n�T�[�r�X�jvalidate�����Q�v�Q�ƁB<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3MarginSwapMarginConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 405692B502F7
     */
    protected WEB3MarginSwapMarginConfirmResponse validateOrder(
        WEB3MarginSwapMarginConfirmRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateOrder(WEB3MarginSwapMarginConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {   
            log.error("parameter is null type");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                   this.getClass().getName() + "." + STR_METHOD_NAME);                 
        }
        
        log.debug("�V�[�P���X�}�u�i�M�p����������n�T�[�r�X�jvalidate�����P�v�Q��");
        //���������� �M�p����������n / �i�M�p����������n�T�[�r�X�jvalidate�����P ����������
        //2 �M�p����������n�����m�F���N�G�X�g�ŁAvalidate()�𒲗p
        l_request.validate();
        
        //3 get�⏕����(SubAccountTypeEnum)
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();        
                
        //4 �M�p����������n�T�[�r�XImpl�ŁAget�㗝���͎�()���擾����𒲗p
        Trader l_trader = this.getTrader();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        try
        {
            log.debug("======== validateOrder�F�M�p�������n�������e�쐬 �J�n ========");
            
            WEB3MarginSwapMarginRequestAdapter l_requestAdapter =
                this.createRequestAdapter(l_request);
            
            WEB3EquityContract l_contract = l_requestAdapter.getContract();
            EqtypeContractRow l_eqtypeContractRow =
                (EqtypeContractRow)l_contract.getDataSourceObject();
            
            WEB3GentradeFinObjectManager l_objectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_market = l_objectManager.getMarket(l_contract.getMarketId());
            String l_strMarketCode = l_market.getMarketCode();
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
            
            EqTypeSettleContractOrderEntry[] l_eqTypeSettleContractOrderEntrys =
                this.createClosingContractEntry(
                    l_request.closeMarginContractUnits,
                    l_requestAdapter);
            
            TaxTypeEnum l_taxType = l_eqtypeContractRow.getTaxType();
            TaxTypeEnum l_swapTaxType = l_requestAdapter.getSwapTaxType();
            
            WEB3MarginSwapContractOrderSpec l_contractOrderSpec =
                WEB3MarginSwapContractOrderSpec.createSwapMarginOrderSpec(
                    l_trader,
                    l_eqTypeSettleContractOrderEntrys,
                    l_request.closingOrder,
                    l_taxType,
                    l_swapTaxType);
            
            log.debug("======== validateOrder�F�M�p�������n�������e�쐬 �I�� ========");
            
            this.validateSwapContractOrder(l_subAccount,l_contractOrderSpec, l_requestAdapter);

            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

            //��������̎擾
            WEB3EquityTradedProduct l_equityTradedProduct =
                (WEB3EquityTradedProduct)l_contract.getTradedProduct();

            //validate�@@�\�a������(�⏕����)
            //�⏕�����F�@@get�⏕����()�̖߂�l
            l_orderManager.validateMechanismDepositAgree(l_subAccount);

            double l_totalQuantity = l_contractOrderSpec.getTotalQuantity();
            double l_dblSwapPrice = this.getEstimatedSwapPrice(
                l_eqTypeSettleContractOrderEntrys,
                l_totalQuantity,
                l_requestAdapter);
            
            double l_dblCapitalGain;
            double l_dblCapitalGainTax;
            if (ContractTypeEnum.SHORT.equals(l_eqtypeContractRow.getContractType()))
            {
                WEB3EquityBizLogicProvider l_logicProvide = 
                    (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
                
                //���n���v�̎Z�o
                l_dblCapitalGain = l_logicProvide.calcCapitaGain(
                    l_dblSwapPrice,
                    l_totalQuantity,
                    l_eqtypeContractRow.getProductId(),
                    l_subAccount,
                    l_contractOrderSpec.getSwapTaxType());
                
                //��������̎擾�A��n���̎擾
                Timestamp l_tsDailyDeliveryDate =
                    new Timestamp(l_equityTradedProduct.getDailyDeliveryDate().getTime());
                    
                //���n�v�ł̎Z�o
                WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
                TaxTypeEnum l_deliveryDateTaxType = l_mainAccount.getDeliveryDateTaxType(l_tsDailyDeliveryDate);
                l_dblCapitalGainTax = l_logicProvide.calcCapitalGainTax(
                    l_subAccount, l_contractOrderSpec.getSwapTaxType(), l_dblCapitalGain, l_tsDailyDeliveryDate, l_deliveryDateTaxType);
            }
            else
            {
                l_dblCapitalGain = 0.0;
                l_dblCapitalGainTax = 0.0;
            }
            
            WEB3TPTradingPowerResult l_tpResult = this.validateTradingPower(
                l_subAccount,
                l_contractOrderSpec,
                (WEB3EquityTradedProduct)l_contract.getTradedProduct(),
                l_requestAdapter,
                l_dblSwapPrice,
                l_dblCapitalGain,
                l_dblCapitalGainTax,
                false);

            log.debug("�V�[�P���X�}�u�i�M�p����������n�T�[�r�X�jvalidate�����Q�v�Q��");
            //���������� �M�p����������n / �i�M�p����������n�T�[�r�X�jvalidate�����Q ����������
            //3 �M�p����������n�����m�F���N�G�X�g�ŁAcreateResponse()�𒲗p
            WEB3MarginSwapMarginConfirmResponse l_response = (WEB3MarginSwapMarginConfirmResponse)l_request.createResponse();
            
            //4 �g���v���_�N�g�}�l�[�W���ŁAget�������(�،����, String, String)�𒲗p
            //�����͈ȉ��̒ʂ�ɐݒ肷��B 
            //  �،���ЁF�@@�⏕����.�،����ID �ɊY������،���ЃI�u�W�F�N�g 
            //  �����R�[�h�F�@@�T�[�r�X���Ŏ擾��������.����ID�̊��������I�u�W�F�N�g.�����R�[�h 
            //  �s��R�[�h�F�@@�T�[�r�X���Ŏ擾��������.�s��ID�̎s��I�u�W�F�N�g.�s��R�[�h
            WEB3EquityProductManager l_productManager = (WEB3EquityProductManager)l_tradingModule.getProductManager();
            //�،���Ђ��擾
            WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)l_subAccount.getInstitution();
            //�����R�[�h���擾
            WEB3EquityProduct l_product = (WEB3EquityProduct) l_contract.getProduct();
            String l_strProductCode = l_product.getProductCode();
            EqTypeTradedProduct l_tradeProduct = l_productManager.getTradedProduct(
                l_institution,
                l_strProductCode,
                l_strMarketCode); 

            //5 �g���v���_�N�g�}�l�[�W���ŁAget����(EqTypeTradedProduct)�𒲗p            
            double l_dblCurrentPrice = l_productManager.getCurrentPrice(l_tradeProduct);
            
            WEB3MarginContractUnit[] l_contractUnits =
                this.createMarginContractUnitList(
                    l_eqTypeSettleContractOrderEntrys,
                    l_dblCurrentPrice,
                    l_requestAdapter);
            
            //17 ������ԊǗ��ŁAget�s��ǌx���s��(���X, ProductTypeEnum, String)�𒲗p
            //�����͈ȉ��̒ʂ�ɐݒ肷��B 
            //  ���X�F�@@get�⏕����()�̖߂�l.get����X() 
            //  �����^�C�v�F�@@ProductTypeEnum.�h�����h 
            //  �M�p����敪�F�@@�T�[�r�X���Ŏ擾��������.�ٍϋ敪
            EqtypeContractRow l_contractRow = (EqtypeContractRow)l_contract.getDataSourceObject();
            String[] l_strTradeCloseMarket = WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
                (WEB3GentradeBranch)l_subAccount.getMainAccount().getBranch(),
                ProductTypeEnum.EQUITY,
                l_contractRow.getRepaymentType());
            
            //18 �g�����������}�l�[�W���ŁAcreateNewOrderId()�𒲗p
            long l_lngNewOrderId = l_orderManager.createNewOrderId();
            
            log.debug("============ validateOrder�F�v���p�e�B�ݒ� ============");
            //19 �v���p�e�B�ݒ�
            // �m�F��������
            l_response.checkDate = WEB3DateUtility.toDay(WEB3GentradeTradingTimeManagement.getOrderBizDate());
            // �T�Z��n���
            l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(l_dblSwapPrice);
            // ����I���x���s��R�[�h�ꗗ
            l_response.messageSuspension = l_strTradeCloseMarket;
            // �������׈ꗗ
            l_response.contractUnits = l_contractUnits;
            // ����ID
            l_response.orderId = Long.toString(l_lngNewOrderId);
            
            //�C���T�C�_�[�x���\���t���O
            l_response.insiderWarningFlag = l_orderManager.isInsiderMessageSuspension(
                l_subAccount, l_eqtypeContractRow.getProductId());
                
            //���X�|���X.���ӕ����\���敪
            //���X�|���X.�a����s���z
            if (l_tpResult != null)
            {
                l_response.attentionObjectionType = l_tpResult.getAttentionObjectionType();
                if (WEB3TPResultAttentionObjectionTypeDef.LACK_ACC_BAL_ATTENTION.equals(
                    l_tpResult.getAttentionObjectionType()))
                {
                    l_response.accountBalanceInsufficiency =
                        WEB3StringTypeUtility.formatNumber(l_tpResult.getLackAccountBalance());
                }
            }
            log.exiting(STR_METHOD_NAME);
            
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
     * (submit����)�B<BR>
     * <BR>
     * �M�p����������n�����o�^���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�M�p����������n�T�[�r�X�jsubmit�����v�Q�ƁB<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3MarginSwapMarginCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 405692BD0077
     */
    protected WEB3MarginSwapMarginCompleteResponse submitOrder(
        WEB3MarginSwapMarginCompleteRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "submitOrder(WEB3MarginSwapMarginCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {   
            log.error("parameter is null type");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                   this.getClass().getName() + "." + STR_METHOD_NAME);                 
        }
        
        log.debug("�V�[�P���X�}�u�i�M�p����������n�T�[�r�X�jsubmit�����v�Q��");
        //2 �M�p����������n�����������N�G�X�g�ŁAvalidate()�𒲗p
        l_request.validate();
        
        //3 get�⏕����()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();        
                
        //4 �M�p����������n�T�[�r�XImpl�ŁAget�㗝���͎�()���擾����𒲗p
        Trader l_trader = this.getTrader();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        try
        {
            log.debug("======== submitOrder�F�M�p�������n�������e�쐬 �J�n ========");
            
            WEB3MarginSwapMarginRequestAdapter l_requestAdapter =
                this.createRequestAdapter(l_request);
            
            WEB3EquityContract l_contract = l_requestAdapter.getContract();
            EqtypeContractRow l_eqtypeContractRow =
                (EqtypeContractRow)l_contract.getDataSourceObject();
            
            WEB3GentradeFinObjectManager l_objectManager = (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_market = l_objectManager.getMarket(l_contract.getMarketId());
            String l_strMarketCode = l_market.getMarketCode();
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
            
            EqTypeSettleContractOrderEntry[] l_eqTypeSettleContractOrderEntrys =
                this.createClosingContractEntry(
                    l_request.closeMarginContractUnits,
                    l_requestAdapter);
              
            TaxTypeEnum l_taxType = l_eqtypeContractRow.getTaxType();
            TaxTypeEnum l_swapTaxType = l_requestAdapter.getSwapTaxType();
            
            WEB3MarginSwapContractOrderSpec l_contractOrderSpec = WEB3MarginSwapContractOrderSpec.createSwapMarginOrderSpec(
                l_trader,
                l_eqTypeSettleContractOrderEntrys,
                l_request.closingOrder,
                l_taxType,
                l_swapTaxType);
            
            log.debug("======== submitOrder�F�M�p�������n�������e�쐬 �I�� ========");
            
            if (l_request.checkDate == null)
            {
                l_request.checkDate =
                    WEB3GentradeTradingTimeManagement.getOrderBizDate();
            }
            WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
            
            this.validateSwapContractOrder(l_subAccount,l_contractOrderSpec, l_requestAdapter);

            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

            //validate�@@�\�a������(�⏕����)
            //�⏕�����F�@@get�⏕����()�̖߂�l
            l_orderManager.validateMechanismDepositAgree(l_subAccount);

            double l_totalQuantity = l_contractOrderSpec.getTotalQuantity();
            double l_dblSwapPrice = this.getEstimatedSwapPrice(
                l_eqTypeSettleContractOrderEntrys,
                l_totalQuantity,
                l_requestAdapter);
            
            //12 �i����t���[�F����.���敪���h�����h�i�����n�����j�̏ꍇ�̂݁j
            double l_dblCalcCapitaGain = 0;
            double l_dblCalcCapitalGainTax = 0;
            WEB3EquityBizLogicProvider l_logicProvide = (WEB3EquityBizLogicProvider)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getBizLogicProvider();
            if(ContractTypeEnum.SHORT.equals(l_eqtypeContractRow.getContractType()))
            {
                //13 calc���n���v(double, double, long, SubAccount, TaxTypeEnum)(�����v�Z�T�[�r�X::calc���n���v)
                //�����͈ȉ��̒ʂ�ɐݒ肷��B
                //  ���z�F�@@calc�T�Z��n����i�������n�j( ) 
                //  �����ʁF�@@�M�p�������n�������e.getTotalQuantity( ) 
                //  ����ID�F�@@�����|�W�V�����}�l�[�W��.get����( )�Ŏ擾��������.����ID 
                //  �⏕�����F�@@this.get�⏕����( ) 
                //  �ŋ敪�F�@@�M�p�������n�������e.get�ŋ敪�i�������n�j( ) 
                l_dblCalcCapitaGain = l_logicProvide.calcCapitaGain(
                    l_dblSwapPrice,
                    l_contractOrderSpec.getTotalQuantity(),
                    l_contract.getProduct().getProductId(),
                    l_subAccount,
                    l_contractOrderSpec.getSwapTaxType());

                //14 getTradedProduct()
                TradedProduct l_tradedProduct = l_contract.getTradedProduct();
                
                //15 calc���n�v��(TaxTypeEnum, double, Timestamp)(�����v�Z�T�[�r�X::calc���n�v��)
                //�����͈ȉ��̒ʂ�ɐݒ肷��B
                //  l_subAccount 
                //  �ŋ敪�F�@@�M�p�������n�������e.get�ŋ敪�i�������n�j( ) 
                //  ���z�F�@@�����v�Z�T�[�r�X.calc���n���v( ) 
                //  ����F�@@����.getTradedProduct( )�Ŏ擾�����������.getDailyDeliveryDate( )�i����n���j 
                //�@@�ڋq�ŋ敪�F�@@�ڋq.get��n���ŋ敪( )
                Timestamp l_tsDailyDeliveryDate =
                    new Timestamp(l_tradedProduct.getDailyDeliveryDate().getTime());
                WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
                TaxTypeEnum l_deliveryDateTaxType = l_mainAccount.getDeliveryDateTaxType(l_tsDailyDeliveryDate);
                
                l_dblCalcCapitalGainTax = l_logicProvide.calcCapitalGainTax(
                    l_subAccount,
                    l_contractOrderSpec.getSwapTaxType(),
                    l_dblCalcCapitaGain,
                    l_tsDailyDeliveryDate,
                    l_deliveryDateTaxType);
            }
            
            this.validateTradingPower(
                l_subAccount,
                l_contractOrderSpec,
                (WEB3EquityTradedProduct)l_contract.getTradedProduct(),
                l_requestAdapter,
                l_dblSwapPrice,
                l_dblCalcCapitaGain,
                l_dblCalcCapitalGainTax,
                true);
            
            log.debug("================ �f�[�^�x�[�X���X�V���J�n ================");
            if (l_request.orderId == null)
            {
                l_request.orderId =
                    String.valueOf(l_orderManager.createNewOrderId());
            }
            this.submitSwapContractOrder(
                l_subAccount,
                l_contractOrderSpec,
                Long.parseLong(l_request.orderId),
                l_request.password,
                l_requestAdapter,
                l_dblSwapPrice,
                l_dblCalcCapitaGain,
                l_dblCalcCapitalGainTax);
            
            log.debug("================ �f�[�^�x�[�X���X�V���I�� ================");
           
            //20 �M�p����������n�����������N�G�X�g�ŁAcreateResponse()���R�[��
            WEB3MarginSwapMarginCompleteResponse l_response = (WEB3MarginSwapMarginCompleteResponse)l_request.createResponse();

            log.debug("========== submitOrder�F22 �v���p�e�B�Z�b�g ==========");
            l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
            l_response.orderActionId = l_request.orderId;
            l_response.insiderWarningFlag =
                l_orderManager.isInsiderMessageSuspension(
                    l_subAccount,
                    l_contract.getProduct().getProductId());
            
            log.exiting(STR_METHOD_NAME);
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
     * (create���N�G�X�g�A�_�v�^)<BR>
     * ���N�G�X�g�A�_�v�^�̃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �M�p����������n���N�G�X�g�A�_�v�^.create(�����̃��N�G�X�g)���R�[������B<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g�f�[�^�B
     * @@return WEB3MarginSwapMarginRequestAdapter
     */
    protected WEB3MarginSwapMarginRequestAdapter createRequestAdapter(WEB3GenRequest l_request)
    {
        final String STR_METHOD_NAME = "createRequestAdapter(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MarginSwapMarginRequestAdapter l_requestAdaptor =
            WEB3MarginSwapMarginRequestAdapter.create(l_request);
        
        log.exiting(STR_METHOD_NAME);
        return l_requestAdaptor;
    }
    
    /**
     * (create���ό����G���g��)<BR>
     * ���ό����G���g�����쐬����B<BR>
     * <BR>
     * �g�����������}�l�[�W��.create���ό����G���g��()���R�[������B<BR>
     * <BR>
     * [create���ό����G���g��()�Ɏw�肷�����]<BR>
     * �����P��ID�F�@@0(�V�K����)<BR>
     * ���������F�@@�p�����[�^.���N�G�X�g�A�_�v�^.get��������()�߂�l<BR>
     * ���ό������׈ꗗ[]�F�@@�p�����[�^.���ό������׈ꗗ[]<BR>
     * @@param l_closeMarginContractUnits - (���ό������׈ꗗ)<BR>
     * �M�p������ό����I�u�W�F�N�g�̔z��B<BR>
     * �i���N�G�X�g�f�[�^�j
     * @@param l_requestAdaptor - (���N�G�X�g�A�_�v�^)<BR>
     * �M�p����������n���N�G�X�g�A�_�v�^�I�u�W�F�N�g�B
     * @@return EqTypeSettleContractOrderEntry[]
     * @@throws WEB3BaseException
     */
    protected EqTypeSettleContractOrderEntry[] createClosingContractEntry(
        WEB3MarginCloseMarginContractUnit[] l_closeMarginContractUnits,
        WEB3MarginSwapMarginRequestAdapter l_requestAdaptor)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createClosingContractEntry(WEB3MarginCloseMarginContractUnit[], WEB3MarginSwapMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        EqTypeSettleContractOrderEntry[] l_settleContractOrderEntrys =
            l_orderManager.createClosingContractEntry(
                0L,
                l_requestAdaptor.getOrderQuantity(),
                l_closeMarginContractUnits);
        
        log.exiting(STR_METHOD_NAME);
        return l_settleContractOrderEntrys;
    }
    
    /**
     * (get�T�Z��n����i�������n�j)<BR>
     * �T�Z��n����i�������n�j���擾����B<BR>
     * <BR>
     * �g�����������}�l�[�W��.calc�T�Z��n����i�������n�j()���R�[������B<BR>
     * <BR>
     * [�T�Z��n����i�������n�j()�ɃZ�b�g����p�����[�^]<BR>
     * �@@���ό����G���g���F �@@�p�����[�^�̓�����<BR>
     * �@@���ʁF�@@�p�����[�^�̓�����<BR>
     * �@@�����P�ʁF�@@null<BR>
     * @@param l_entrys - (���ό����G���g��)<BR>
     * ���ό����G���g���̔z��B
     * @@param l_dblQuantity - (����)<BR>
     * ���ʁB
     * @@param l_requestAdaptor - (���N�G�X�g�A�_�v�^)<BR>
     * �M�p����������n���N�G�X�g�A�_�v�^�B
     * @@return double
     * @@throws WEB3BaseException
     */
    protected double getEstimatedSwapPrice(
        EqTypeSettleContractOrderEntry[] l_entrys,
        double l_dblQuantity,
        WEB3MarginSwapMarginRequestAdapter l_requestAdaptor)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getEstimatedSwapPrice(EqtypeSettleContractOrderEntry[], double, WEB3MarginSwapMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        double l_dblEstimatedSwapPrice =
            l_orderManager.calcEstimatedSwapPrice(
                l_entrys,
                l_dblQuantity,
                null);
        
        log.exiting(STR_METHOD_NAME);
        return l_dblEstimatedSwapPrice;
    }
    
    /**
     * (create�������׈ꗗ)<BR>
     * ���ό����G���g�����M�p����������ׂ̈ꗗ��<BR>
     * �쐬����B<BR>
     * <BR>
     * �P�j�@@ArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�p�����[�^.���ό����G���g���̗v�f�����A<BR>
     * �@@�ȉ��̏��������{����B<BR>
     * �@@�Q�|�P�j�@@�����|�W�V�����}�l�[�W��.get����()�ɂ�<BR>
     * �@@�@@�������擾����B<BR>
     * <BR>
     * �@@�@@[get����()�Ɏw�肷�����]<BR>
     * �@@�@@�@@����ID�F�@@�����Ώۂ̗v�f.getContractId()<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�M�p����������׃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �@@�Q�|�R�j�@@���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@ID�F�@@����.����ID<BR>
     * �@@�@@���� = ����.����<BR>
     * �@@�@@���P�� = ����.���P��<BR>
     * �@@�@@������ = ����.������<BR>
     * �@@�@@����� = ����.get�����(��������(*1))<BR>
     * �@@�@@�]�����v =<BR>
     * �@@�@@�@@����.get�]�����v�i�������o��l���j(�p�����[�^.�v�Z�P��, ��������)<BR>
     * �@@�@@�������� = ��������<BR>
     * �@@�@@���o������ = null<BR>
     * �@@�@@���Ϗ��� = index + 1<BR>
     * <BR>
     * �@@�@@(*1)���������E�E�E�����Ώۂ̗v�f.getQuantity()<BR>
     * �@@�@@<BR>
     * �@@�Q�|�S�j�@@ArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B<BR>
     * <BR>
     * �R�j�@@ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * @@param l_settleContractOrderEntrys - (���ό����G���g��)<BR>
     * ���ό����G���g���̔z��B
     * @@param l_dblUnitPrice - (�v�Z�P��)<BR>
     * �v�Z�P���B
     * @@param l_requestAdaptor - (���N�G�X�g�A�_�v�^)<BR>
     * �M�p����������n���N�G�X�g�A�_�v�^�I�u�W�F�N�g�B
     * @@return WEB3MarginContractUnit[]
     * @@throws WEB3BaseException
     */
    protected WEB3MarginContractUnit[] createMarginContractUnitList(
        EqTypeSettleContractOrderEntry[] l_settleContractOrderEntrys,
        double l_dblUnitPrice,
        WEB3MarginSwapMarginRequestAdapter l_requestAdaptor)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createMarginContractUnitList(EqTypeSettleContractOrderEntry[], double, WEB3MarginSwapMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        List l_lstContractUnit = new ArrayList();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
        int l_intOrderEntryLength = 0;
        if (l_settleContractOrderEntrys != null)
        {
            l_intOrderEntryLength = l_settleContractOrderEntrys.length;
        }
        for (int i = 0; i < l_intOrderEntryLength; i++)
        {
            WEB3EquityContract l_equityContract = null;
            try
            {
                l_equityContract =
                    (WEB3EquityContract)l_positionManager.getContract(l_settleContractOrderEntrys[i].getContractId());
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
            
            WEB3MarginContractUnit l_contractUnit = new WEB3MarginContractUnit();
            l_contractUnit.id = Long.toString(l_equityContract.getContractId());
            l_contractUnit.openDate = WEB3DateUtility.toDay(l_equityContract.getOpenDate());
            double l_dblContractPrice = l_equityContract.getContractPrice();
            if (Double.isNaN(l_dblContractPrice))
            {
                l_dblContractPrice = 0D;
            }
            l_contractUnit.contractPrice = WEB3StringTypeUtility.formatNumber(l_dblContractPrice);
            double l_dblContractQuantity = l_equityContract.getQuantity();
            if (Double.isNaN(l_dblContractQuantity))
            {
                l_dblContractPrice = 0D;
            }
            l_contractUnit.contractQuantity = WEB3StringTypeUtility.formatNumber(l_dblContractQuantity);
            double l_dblQuantity = l_settleContractOrderEntrys[i].getQuantity();
            if (Double.isNaN(l_dblQuantity))
            {
                l_dblQuantity = 0D;
            }
            l_contractUnit.contractExecPrice = WEB3StringTypeUtility.formatNumber(
                l_equityContract.getContractAmount(l_dblQuantity));
            l_contractUnit.appraisalProfitLoss =
                WEB3StringTypeUtility.formatNumber(
                    l_equityContract.getAppraisalProfitOrLossExpenses(
                        l_dblUnitPrice, l_dblQuantity));
            l_contractUnit.orderQuantity = WEB3StringTypeUtility.formatNumber(l_dblQuantity);
            l_contractUnit.partContQuantity = null;
            l_contractUnit.settlePriority = Integer.toString(i + 1);
            
            l_lstContractUnit.add(l_contractUnit);
        }
        
        WEB3MarginContractUnit[] l_contractUnits =
            new WEB3MarginContractUnit[l_lstContractUnit.size()];
        l_lstContractUnit.toArray(l_contractUnits);
        
        log.exiting(STR_METHOD_NAME);
        return l_contractUnits;
    }
    
    /**
     * (validate�������n����)<BR>
     * �M�p����������n���������R�����s���B<BR>
     * <BR>
     * �g�����������}�l�[�W��.validate�������n����()<BR>
     * ���R�[������B<BR>
     * <BR>
     * [validate�������n����()�Ɏw�肷�����]<BR>
     * �@@�⏕�����F�@@�p�����[�^.�⏕����<BR>
     * �@@�M�p�������n�������e�F�@@�p�����[�^.�M�p�������n�������e<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B
     * @@param l_orderSpec - (�M�p�������n�������e)<BR>
     * �M�p�������n�������e�I�u�W�F�N�g�B
     * @@param l_requestAdaptor - (���N�G�X�g�A�_�v�^)<BR>
     * �M�p����������n���N�G�X�g�A�_�v�^�I�u�W�F�N�g�B
     * @@throws WEB3BaseException
     */
    protected void validateSwapContractOrder(
        WEB3GentradeSubAccount l_subAccount,
        WEB3MarginSwapContractOrderSpec l_orderSpec,
        WEB3MarginSwapMarginRequestAdapter l_requestAdaptor)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateSwapContractOrder(WEB3GentradeSubAccount, WEB3MarginSwapContractOrderSpec, WEB3MarginSwapMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        EqTypeNewOrderValidationResult l_result =
            l_orderManager.validateSwapContractOrder(l_subAccount, l_orderSpec);
        if (l_result.getProcessingResult().isFailedResult())
        {
            log.debug("ProcessingResult() = " + l_result.getProcessingResult());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_result.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (submit�������n����)<BR>
     * �M�p����������n������o�^����B<BR>
     * <BR>
     * �g�����������}�l�[�W��.submitSwapContractOrder(<BR>
     * �⏕����, �M�p�������n�������e, ����ID, ����p�X���[�h, true�i�������R�����X�L�b�v����j)���R�[������B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B
     * @@param l_orderSpec - (�M�p�������n�������e)<BR>
     * �M�p�������n�������e�I�u�W�F�N�g�B
     * @@param l_lngOrderId - (����ID)<BR>
     * ����ID�B
     * @@param l_strTradingPassword - (����p�X���[�h)<BR>
     * ����p�X���[�h�B
     * @@param l_requestAdaptor - (�������n���N�G�X�g�A�_�v�^)<BR>
     * �M�p����������n���N�G�X�g�A�_�v�^�I�u�W�F�N�g�B
     * @@param l_dblEstimatedPrice - (�T�Z��n���)<BR>
     * �T�Z��n����B
     * @@param l_dblCapitalGain - (���n�v���z)<BR>
     * ���n�v���z�B
     * @@param l_dblCapitalGainTax - (���n�v�Ŋz)<BR>
     * ���n�v�Ŋz�B
     * @@throws WEB3BaseException
     */
    protected void submitSwapContractOrder(
        WEB3GentradeSubAccount l_subAccount,
        WEB3MarginSwapContractOrderSpec l_orderSpec,
        long l_lngOrderId,
        String l_strTradingPassword,
        WEB3MarginSwapMarginRequestAdapter l_requestAdaptor,
        double l_dblEstimatedPrice,
        double l_dblCapitalGain,
        double l_dblCapitalGainTax)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitSwapContractOrder(WEB3GentradeSubAccount, WEB3MarginSwapContractOrderSpec, long, String, WEB3MarginSwapMarginRequestAdapter, double, double, double)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        EqTypeOrderSubmissionResult l_result =
            l_orderManager.submitSwapContractOrder(
                l_subAccount,
                l_orderSpec,
                l_lngOrderId,
                l_strTradingPassword,
                true);
        if (l_result.getProcessingResult().isFailedResult())
        {
            log.debug("ProcessingResult() = " + l_result.getProcessingResult());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_result.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate����]��)<BR>
     * ����]�͂��`�F�b�N���A����]�͌��ʃI�u�W�F�N�g��ԋp����B<BR>
     * �V�[�P���X�}�u�i�M�p����������n�T�[�r�X�jvalidate����]�́v���Q�ƁB<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B
     * @@param l_orderSpec - (�M�p�������n�������e)<BR>
     * �M�p�������n�������e�I�u�W�F�N�g�B
     * @@param l_tradedProduct - (�������)<BR>
     * ��������I�u�W�F�N�g�B
     * @@param l_requestAdaptor - (�������n���N�G�X�g�A�_�v�^)<BR>
     * �M�p����������n���N�G�X�g�A�_�v�^�I�u�W�F�N�g�B
     * @@param l_dblEstimatedPrice - (�T�Z��n���)<BR>
     * �T�Z��n����B
     * @@param l_dblCapitalGain - (���n�v���z)<BR>
     * ���n�v���z�B
     * @@param l_dblCapitalGainTax - (���n�v�Ŋz)<BR>
     * ���n�v�Ŋz�B
     * @@param l_blnUpdateFlg - (�]�͍X�V�t���O)<BR>
     * �]�͍X�V�t���O�B<BR>
     * �ifalse�F�@@�m�F���Atrue�F�@@�������j
     * @@return WEB3TPTradingPowerResult
     * @@throws WEB3BaseException
     */
    protected WEB3TPTradingPowerResult validateTradingPower(
        WEB3GentradeSubAccount l_subAccount,
        WEB3MarginSwapContractOrderSpec l_orderSpec,
        WEB3EquityTradedProduct l_tradedProduct,
        WEB3MarginSwapMarginRequestAdapter l_requestAdaptor,
        double l_dblEstimatedPrice,
        double l_dblCapitalGain,
        double l_dblCapitalGainTax,
        boolean l_blnUpdateFlg)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateTradingPower(WEB3GentradeSubAccount, WEB3MarginSwapContractOrderSpec, WEB3EquityTradedProduct, WEB3MarginSwapMarginRequestAdapter, double, double, double, boolean)";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityContract l_contract = l_requestAdaptor.getContract();
        EqtypeContractRow l_contractRow =
            (EqtypeContractRow)l_contract.getDataSourceObject();
        BranchMarketRepayDealtCondRow l_BranchMarketRepayDealtCondRow
            = (BranchMarketRepayDealtCondRow)l_contract.getBranchMarketRepayDealtCond().getDataSourceObject();
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
        String l_orderRootDiv =
            l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV);
        
        WEB3MarginSwapMarginUpdateInterceptor l_interceptor = new WEB3MarginSwapMarginUpdateInterceptor(
            l_orderSpec,
            l_BranchMarketRepayDealtCondRow.getSonarRepaymentType(),
            l_dblEstimatedPrice,
            l_contractRow.getRepaymentType(),
            l_contractRow.getRepaymentNum(),
            l_dblCapitalGain,
            l_dblCapitalGainTax,
            this.getLoginChannel(),
            l_orderRootDiv);
        
        Object[] l_interceptors = { l_interceptor };
        Object[] l_orderSpecs = { l_orderSpec };
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        WEB3TPTradingPowerService l_tradingPowerService
            = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        WEB3TPTradingPowerResult l_tpResult =
            l_tradingPowerService.validateTradingPower(
                l_subAccount,
                l_interceptors,
                l_orderSpecs, 
                l_contract.isLong() ? OrderTypeEnum.SWAP_MARGIN_LONG : OrderTypeEnum.SWAP_MARGIN_SHORT,
                l_blnUpdateFlg);
        if (l_tpResult.isResultFlg() == false)
        {
            // ��K���`�F�b�N�G���[�̏ꍇ
            if (l_tpResult.getTpErrorInfo().tradinPowerErrorDiv.equals(WEB3TPTradingPowerErrorDivDef.MARGIN_SEC_ERROR))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01928,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            // ��K���`�F�b�N�G���[�ȊO�̏ꍇ(�a����s��)
            else
            {
                // ���������̏ꍇ
                if (l_contract.isLong() == true)
                {
                    String l_strLackAccountBalanceInfo =
                        l_orderManager.getLackAccountBalanceInfoBuy(l_tpResult);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01929,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_strLackAccountBalanceInfo);
                }
                // ���n�����̏ꍇ
                else
                {
                    String l_strLackAccountBalanceInfo =
                        l_orderManager.getLackAccountBalanceInfoSell(
                            l_tpResult,
                            l_orderSpec.getTotalQuantity());
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01930,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_strLackAccountBalanceInfo);
                }
            }
        }
        
        if (l_blnUpdateFlg)
        {
            l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_tpResult;
    }
}
@
