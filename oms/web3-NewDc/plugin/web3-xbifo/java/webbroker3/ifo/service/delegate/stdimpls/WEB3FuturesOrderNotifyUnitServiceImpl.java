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
filename	WEB3FuturesOrderNotifyUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨�����ʒmUnitServiceImpl(WEB3FuturesOrderNotifyUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/02 ������ (���u) �V�K�쐬
                 : 2006/08/03 �юu�� (���u) �d�l�ύX�@@���f��540 
Revesion History : 2007/06/21 ���@@�� (���u) �d�l�ύX�@@���f��715 
Revesion History : 2007/07/02 �Ј��� (���u) �d�l�ύX�@@���f��770 
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PayTypeDef;
import webbroker3.common.define.WEB3SonarExecutionConditionDef;
import webbroker3.common.define.WEB3TradeTypeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.ifo.service.delegate.WEB3FuturesOrderNotifyUnitService;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoOpenContractOrderNotifyUpdateInterceptor;
import webbroker3.ifo.WEB3IfoOpenContractOrderSpec;
import webbroker3.ifo.WEB3IfoPositionManagerImpl;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3IfoSettleContractOrderNotifyUpdateInterceptor;
import webbroker3.ifo.WEB3IfoSettleContractOrderSpec;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.data.HostFotypeOrderReceiptParams;
import webbroker3.ifo.define.WEB3IfoOrderTypeDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;


import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;

/**
 * (�敨�����ʒmUnitServiceImpl)<BR>
 * �����w���敨�����ʒm�P���T�[�r�X�����N���X<BR>
 * <BR>
 * �����P�����Ƃ̒����ʒm���������{����B<BR>
 * <BR>
 * Plugin���Ɏ����g�����U�N�V����<BR>
 * TransactionalInterceptor<BR>
 * (TransactionalInterceptor.TX_CREATE_NEW)���w�肷��B<BR>
 * @@author  : ������
 * @@version : 1.0
 */
public class WEB3FuturesOrderNotifyUnitServiceImpl implements WEB3FuturesOrderNotifyUnitService 
{
   
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesOrderNotifyUnitServiceImpl.class);
   
    /**
     * @@roseuid 41AD654503C8
     */
    public WEB3FuturesOrderNotifyUnitServiceImpl() 
    {
    
    }
   
    /**
     * (notify�V�K������)<BR>
     * �V�K�������ʒm���������{����B <BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�敨�����ʒm)notify�V�K�������v�Q�ƁB<BR>
     * @@param l_hostFotypeOrderReceiptParams - (�敨OP�����ʒm�L���[Params)<BR>
     * �y�敨OP�����ʒm�L���[�e�[�u���z��1-Row��\������N���X<BR>
     * @@param l_subAccount - �⏕�����I�u�W�F�N�g
     * @@roseuid 4174F7AB00A8
     */
    public void notifyOpenContractOrder(
        HostFotypeOrderReceiptParams l_hostFotypeOrderReceiptParams, 
        SubAccount l_subAccount) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "notifyOpenContractOrder()";
        log.entering(STR_METHOD_NAME);
    
        if ((l_hostFotypeOrderReceiptParams == null) || (l_subAccount == null))
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
    
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        try
        {
            //1.1 get�s��BySONAR(String, String)(�g�����Z�I�u�W�F�N�g�}�l�[�W��::get�s��BySONAR)
            //  [get�s��()�Ɏw�肷�����] 
            //  �،���ЃR�[�h�F�@@����.�敨OP�����ʒm�L���[Params.�،���ЃR�[�h 
            //  �s��R�[�h(SONAR)�F�@@����.�敨OP�����ʒm�L���[Params.�s��R�[�h(SONAR) 
            WEB3GentradeFinObjectManager l_objectManager = 
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_market = l_objectManager.getMarketBySONAR(
                l_hostFotypeOrderReceiptParams.getInstitutionCode(),
                l_hostFotypeOrderReceiptParams.getSonarMarketCode());
    
            //1.2 getInstitution(�،���ЃR�[�h : �_���r���[::java::lang::String)
            //  [getInstitution()�Ɏw�肷�����] 
            //  �،���ЃR�[�h�F�@@�敨OP�����ʒm�L���[Params.�،���ЃR�[�h
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            Institution l_institution = l_accountManager.getInstitution(l_hostFotypeOrderReceiptParams.getInstitutionCode());

            //1.3 get����(Institution, String)(�敨OP�v���_�N�g�}�l�[�W��::get����)
            //  [get����()�Ɏw�肷�����] 
            //  �،���ЁF�@@�擾�����،���ЃI�u�W�F�N�g 
            //  �����R�[�h�F�@@�敨OP�����ʒm�L���[Params.�����R�[�h
            WEB3IfoProductManagerImpl l_productManagerImpl = 
                (WEB3IfoProductManagerImpl)l_tradingMod.getProductManager();
            WEB3IfoProductImpl l_product = l_productManagerImpl.getIfoProduct(
                l_institution,
                l_hostFotypeOrderReceiptParams.getProductCode());

            //1.4  get������( )(������ԊǗ�::get������)
            Date l_datOrder = WEB3GentradeTradingTimeManagement.getOrderBizDate();

            //1.5 getAP�w���s����(String)(OP�����ʒmUnitServiceImpl::getAP�w���s����)
            //  [get���s����()�Ɏw�肷�����] 
            //  ���s����(SONAR)�F�@@�敨OP�����ʒm�L���[Params.���s����
            IfoOrderExecutionConditionType l_execCondType = 
                getAPOrderExecCondType(l_hostFotypeOrderReceiptParams.getExecutionCondition());

            //1.7 create�V�K���������e(String, ����, boolean, String, �敨OP����, double, double, IfoOrderExecutionConditionType, Date, String, double)
            //  (�V�K���������e::create�V�K���������e)
            //  [create�V�K���������e()�Ɏw�肷�����] 
            //  �،���ЃR�[�h = �敨OP�����ʒm�L���[Params.�،���ЃR�[�h 
            //  ���� = null 
            //  is���� = �敨OP�����ʒm�L���[Params.�����敪 == �h���t�h�̏ꍇ�Atrue�B�ȊO�Afalse�B 
            //  �s��R�[�h = �g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��BySONAR()�̖߂�l.getMarketCode() 
            //  ���� = �敨OP�v���_�N�g�}�l�[�W��.get����()�̖߂�l 
            //  ���� = �敨OP�����ʒm�L���[Params.���� 
            //  �w�l = �敨OP�����ʒm�L���[Params.�w�l 
            //  ���s���� = this.getAP�w���s����()�̖߂�l 
            //  ���������� = ������ԊǗ�.get������()�̖߂�l 
            //  �������� = (*)�h0�F DEFAULT�i�����w��Ȃ��j�h 
            //  (W�w�l)�����w�l = 0 
            //  (*)�����ʒm�œo�^���钍���̔��������́u�����w��Ȃ��v�Œ�
            //  ���������敪 = �h��������h
            //  ���񒍕��̒����P��ID = null
            //  �[��O�J�z�Ώۃt���O = false�i�[��O�J�z�Ȃ��j
            boolean l_blnTradeType = false;
            if (WEB3TradeTypeDef.BUY.equals(l_hostFotypeOrderReceiptParams.getTradeType()))
            {
                l_blnTradeType = true;
            }
            WEB3IfoOpenContractOrderSpec l_orderSpec = WEB3IfoOpenContractOrderSpec.createOpenContractOrderSpec(
                l_hostFotypeOrderReceiptParams.getInstitutionCode(),
                null,
                l_blnTradeType,
                l_market.getMarketCode(),
                l_product,
                l_hostFotypeOrderReceiptParams.getQuantity(),
                l_hostFotypeOrderReceiptParams.getLimitPrice(),
                l_execCondType,
                l_datOrder,
                WEB3OrderingConditionDef.DEFAULT,
                0D,
                0D,
                null,
                WEB3OrderExpirationDateTypeDef.DAY_LIMIT,
                null,
                false);

            //1.7 �萔��()(�萔��::�萔��)
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
 
            //1.8  (*1)�v���p�e�B�Z�b�g
            //  (*1)�萔���I�u�W�F�N�g�Ɉȉ��̒ʂ�v���p�e�B���Z�b�g����B
            //  �萔��.�����`���l��      = �敨OP�����ʒm�L���[Params.�����`���l��
            //  �萔��.�،���ЃR�[�h = �敨OP�����ʒm�L���[Params.�،���ЃR�[�h
            //  �萔��.���XID        = �⏕����.get����X().getBranchId()
            //  �萔��.������     = ������ԊǗ�.get������()
            //  �萔��.����R�[�h(SONAR)    = �h51�F���h
            //  �萔��.�萔�����i�R�[�h    = �h50�F�����w���敨�h
            //  �萔��.�ٍϋ敪        = �h00�F���̑��h
            //  �萔��.�����Y�����R�[�h = �敨OP����.get�����Y�����R�[�h()
            //  �萔��.���� = �V�K���������e.getQuantity()
            //	�萔��.is�w�l = �V�K���������e.isLimitOrder()
            l_commission.setOrderChannel(l_hostFotypeOrderReceiptParams.getChannel());
            l_commission.setInstitutionCode(l_hostFotypeOrderReceiptParams.getInstitutionCode());
            l_commission.setBranchId(l_subAccount.getMainAccount().getBranch().getBranchId());
            l_commission.setOrderBizDate(new Timestamp(l_datOrder.getTime()));
            l_commission.setSonarTradedCode(WEB3TransactionTypeSONARDef.OPEN_CONTRACT);
            l_commission.setCommissionProductCode(WEB3CommisionProductCodeDef.INDEX_FUTURES);
            l_commission.setPayType(WEB3PayTypeDef.OTHER);
            l_commission.setUnderlyingProductCode(l_product.getUnderlyingProductCode());
            l_commission.setQuantity(l_orderSpec.getQuantity());
			l_commission.setIsLimitPrice(l_orderSpec.isLimitOrder());
            
            //1.9 get�������(Institution, String, String)(�敨OP�v���_�N�g�}�l�[�W��::get�������)
            //  [get�������()�Ɏw�肷�����] 
            //  �،���ЁF�@@�擾�����،���ЃI�u�W�F�N�g 
            //  �����R�[�h�F�@@�敨OP�����ʒm�L���[Params.�����R�[�h 
            //  �s��R�[�h�F�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��BySONAR()�̖߂�l.getMarketCode()
            WEB3IfoTradedProductImpl l_tradeProduct = l_productManagerImpl.getIfoTradedProduct(
                l_institution,
                l_hostFotypeOrderReceiptParams.getProductCode(),
                l_market.getMarketCode());

            //1.10 calc�T�Z�����(�萔��, double, SubAccount, �敨OP�������, double, boolean)
            //  [calc�����()�Ɏw�肷�����]
            //  �萔���F�@@�萔���I�u�W�F�N�g
            //  �v�Z�P���F�@@�敨OP�����ʒm�L���[Params.�w�l
            //  �⏕�����F�@@�⏕�����I�u�W�F�N�g
            //  �敨OP��������F�@@�擾�����敨OP��������I�u�W�F�N�g
            //  ���ʁF �V�K���������e.getQuantity()
            //  isSkip���z�`�F�b�N�F�@@true(�X�L�b�v����)
            WEB3FuturesOrderManagerImpl l_orderManager = 
                (WEB3FuturesOrderManagerImpl) l_tradingMod.getOrderManager();
            WEB3IfoEstimateDeliveryAmountCalcResult l_deliveryAmount = 
                l_orderManager.calcEstimatePrice(
                    l_commission,
                    l_hostFotypeOrderReceiptParams.getLimitPrice(),
                    (WEB3GentradeSubAccount)l_subAccount,
                    l_tradeProduct,
                    l_orderSpec.getQuantity(),
                    true);

            //1.11  �敨OP�V�K�������ʒm�X�V�C���^�Z�v�^(�V�K���������e)(�敨OP�V�K�������ʒm�X�V�C���^�Z�v�^::�敨OP�V�K�������ʒm�X�V�C���^�Z�v�^)
            WEB3IfoOpenContractOrderNotifyUpdateInterceptor l_interceptor = 
                new WEB3IfoOpenContractOrderNotifyUpdateInterceptor(l_orderSpec); 

            //1.12  (*2)�v���p�e�B�Z�b�g
            //(*2)�C���^�Z�v�^�I�u�W�F�N�g�̃v���p�e�B�Ɉȉ��̒l���Z�b�g����B
            //  �C���^�Z�v�^.�萔��          = �icalc�T�Z��n���()�����̎萔���I�u�W�F�N�g�j
            //  �C���^�Z�v�^.�T�Z��n����v�Z����   = �icalc�T�Z��n���()�̖߂�l�j
            //  �C���^�Z�v�^.��������     = �h0�F DEFAULT�i�����w��Ȃ��j�h
            //  �C���^�Z�v�^.�����������Z�q      = null
            //  �C���^�Z�v�^.�t�w�l��l�^�C�v        = null
            //  �C���^�Z�v�^.�t�w�l��l       = 0
            //  �C���^�Z�v�^.(W�w�l)�����w�l        = 0
            //  �C���^�Z�v�^.���ʃR�[�h        = �敨OP�����ʒm�L���[Params.���ʃR�[�h
            //  �C���^�Z�v�^.�󒍓���     = �敨OP�����ʒm�L���[Params.�󒍓���
            //  �C���^�Z�v�^.������          = ������ԊǗ�.get������()�̖߂�l
            //  �C���^�Z�v�^.��n��            = �������.get��n��()�̖߂�l
            //  �C���^�Z�v�^.����敪         = ������ԊǗ�.get����敪()�̖߂�l�j
            l_interceptor.setCommision(l_commission);
            l_interceptor.setEstimateDeliveryAmountCalcResult(l_deliveryAmount);
            l_interceptor.setOrderCond(WEB3OrderingConditionDef.DEFAULT);
            l_interceptor.setOrderCondOperator(null);
            l_interceptor.setStopOrderBasePriceType(null);
            l_interceptor.setStopOrderBasePrice(0);
            l_interceptor.setWLimitPriceChange(0);
            l_interceptor.setOrderRequestNumber(l_hostFotypeOrderReceiptParams.getOrderRequestNumber());
            l_interceptor.setReceivedDateTime(l_hostFotypeOrderReceiptParams.getCreateDatetime());
            l_interceptor.setBizDate(l_datOrder);
            l_interceptor.setDeliveryDate(l_tradeProduct.getDailyDeliveryDate());
            l_interceptor.setSessionType(WEB3GentradeTradingTimeManagement.getSessionType());

            //1.13 setThreadLocalPersistenceEventInterceptor(�敨OP�V�K�������ʒm�X�V�C���^�Z�v�^ : IfoOrderManagerPersistenceEventInterceptor)
            l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);  

            //1.14 createNewOrderId()(OP�����}�l�[�W��::createNewOrderId)
            long l_lngOrderId = l_orderManager.createNewOrderId();

            //1.15  setBusinessTimestamp( )(������ԊǗ�::setBusinessTimestamp)
            WEB3GentradeTradingTimeManagement.setBusinessTimestamp();

            //1.16  submitOpenContractOrder(�⏕���� : SubAccount, �V�K���������e : IfoOpenContractOrderSpec, ����ID : long, ����p�X���[�h : String, isSkip�����R�� : boolean)
            //  [submitOpenContractOrder()�Ɏw�肷�����] 
            //  arg0�i�⏕�����j�F�@@�⏕���� 
            //  arg1�i�V�K���������e�j�F�@@create�V�K���������e()�Ő��������V�K���������e 
            //  arg2�i�����h�c�j�F�@@OP�����}�l�[�W��.createNewOrderId() 
            //  arg3�i����p�X���[�h�j�F�@@�⏕����.getMainAccount().getTradingPassword() 
            //  arg4�iisSkip�����R���j�F�@@true 
            OrderSubmissionResult l_result = l_orderManager.submitOpenContractOrder(
                l_subAccount,
                l_orderSpec,
                l_lngOrderId,
                l_subAccount.getMainAccount().getTradingPassword(),
                true);
            if (l_result.getProcessingResult().isFailedResult())
            {
                log.error(l_result.getProcessingResult().toString());
                throw new WEB3BusinessLayerException(
                    l_result.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }
        catch (NotFoundException l_nfex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfex.getMessage(), l_nfex);
        } 
        catch (WEB3BaseException l_baseException)
        {
            log.debug(l_baseException.getErrorMessage(), l_baseException);
        }
    }
   
    /**
     * (notify�ԍϒ���)<BR>
     * �ԍϒ����ʒm���������{����B <BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�敨�����ʒm)notify�ԍϒ����v�Q�ƁB<BR>
     * @@param l_hostFotypeOrderReceiptParams - (�敨OP�����ʒm�L���[Params)<BR>
     * �y�敨OP�����ʒm�L���[�e�[�u���z��1-Row��\������N���X<BR>
     * @@param l_subAccount - �⏕�����I�u�W�F�N�g
     * @@roseuid 4174F7AB00C7
     */
    public void notifySettleContractOrder(
        HostFotypeOrderReceiptParams l_hostFotypeOrderReceiptParams, 
        SubAccount l_subAccount) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "notifySettleContractOrder()";
        log.entering(STR_METHOD_NAME);
    
        if ((l_hostFotypeOrderReceiptParams == null) || (l_subAccount == null))
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
    
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        try
        {
            //1.1 get�s��BySONAR(String, String)(�g�����Z�I�u�W�F�N�g�}�l�[�W��::get�s��BySONAR)
            //  [get�s��()�Ɏw�肷�����] 
            //  �،���ЃR�[�h�F�@@����.�敨OP�����ʒm�L���[Params.�،���ЃR�[�h 
            //  �s��R�[�h(SONAR)�F�@@����.�敨OP�����ʒm�L���[Params.�s��R�[�h(SONAR) 
            WEB3GentradeFinObjectManager l_objectManager = 
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_market = l_objectManager.getMarketBySONAR(
                l_hostFotypeOrderReceiptParams.getInstitutionCode(),
                l_hostFotypeOrderReceiptParams.getSonarMarketCode());
    
            //1.2 getInstitution(�،���ЃR�[�h : �_���r���[::java::lang::String)
            //  [getInstitution()�Ɏw�肷�����] 
            //  �،���ЃR�[�h�F�@@�敨OP�����ʒm�L���[Params.�،���ЃR�[�h
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            Institution l_institution = l_accountManager.getInstitution(l_hostFotypeOrderReceiptParams.getInstitutionCode());

            //1.3 get����(Institution, String)(�敨OP�v���_�N�g�}�l�[�W��::get����)
            //  [get����()�Ɏw�肷�����] 
            //  �،���ЁF�@@�擾�����،���ЃI�u�W�F�N�g 
            //  �����R�[�h�F�@@�敨OP�����ʒm�L���[Params.�����R�[�h
            WEB3IfoProductManagerImpl l_productManagerImpl = 
                (WEB3IfoProductManagerImpl)l_tradingMod.getProductManager();
            WEB3IfoProductImpl l_product = l_productManagerImpl.getIfoProduct(
                l_institution,
                l_hostFotypeOrderReceiptParams.getProductCode());

            //1.4 create�ԍό��ʈꗗ(�⏕����, ContractTypeEnum, long, long)(�敨OP�|�W�V�����}�l�[�W��::create�ԍό��ʈꗗ)
            //  [create�ԍό��ʈꗗ()�Ɏw�肷�����] 
            //  �⏕�����F�@@����.�⏕���� 
            //  ���敪�F�@@ 
            //�@@����.�敨OP�����ʒm�L���[Params�����敪 == �h���t�h�̏ꍇ�A�h�����h 
            //�@@�ȊO�A�h�����h 
            //  �s��ID�F�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��BySONAR()�̖߂�l.getMarketId() 
            //  ����ID�F�@@�敨OP�v���_�N�g�}�l�[�W��.get����()�̖߂�l.getProductId() 
            WEB3IfoPositionManagerImpl l_positionManager =
                (WEB3IfoPositionManagerImpl)l_tradingMod.getPositionManager();
            ContractTypeEnum l_contractType = null;
            if (WEB3TradeTypeDef.BUY.equals(l_hostFotypeOrderReceiptParams.getTradeType()))
            {
                l_contractType = ContractTypeEnum.LONG;
            }
            else
            {
                l_contractType = ContractTypeEnum.SHORT;
            }
            WEB3FuturesOptionsCloseMarginContractUnit[] l_contractUnit = 
            l_positionManager.createSettleContracts(
                (WEB3GentradeSubAccount)l_subAccount,
                l_contractType,
                l_market.getMarketId(),
                l_product.getProductId());
                
            //1.5 create�ԍό��ʃG���g��(long, double, �ԍό���[])(OP�����}�l�[�W��::create�ԍό��ʃG���g��)
            //  [create�ԍό��ʃG���g��()�Ɏw�肷�����] 
            //  �����P��ID�F�@@0(�V�K����) 
            //  �������ʁF�@@����.�敨OP�����ʒm�L���[Params.���� 
            //  �ԍό��ʁF�@@�敨OP�|�W�V�����}�l�[�W��.create�ԍό��ʈꗗ()�̖߂�l
            WEB3FuturesOrderManagerImpl l_orderManager = 
                (WEB3FuturesOrderManagerImpl) l_tradingMod.getOrderManager();
            SettleContractEntry[] l_contractEntry = l_orderManager.createSettleContractEntry(
                Long.parseLong(WEB3IfoOrderTypeDef.NEW_ORDER),
                l_hostFotypeOrderReceiptParams.getQuantity(),
                l_contractUnit);

            //1.6 getAP�w���s����(String)(OP�����ʒmUnitServiceImpl::getAP�w���s����)
            //  [get���s����()�Ɏw�肷�����] 
            //  ���s����(SONAR)�F�@@�敨OP�����ʒm�L���[Params.���s����
            IfoOrderExecutionConditionType l_execCondType = 
                getAPOrderExecCondType(l_hostFotypeOrderReceiptParams.getExecutionCondition());

            //1.7  get������( )(������ԊǗ�::get������)
            Date l_datOrder = WEB3GentradeTradingTimeManagement.getOrderBizDate();

            //1.8  create�ԍϒ������e(String, ����, double, IfoOrderExecutionConditionType, Date, SettleContractOrderEntry[], String, double)(�ԍϒ������e::create�ԍϒ������e)
            //  [create�ԍϒ������e()�Ɏw�肷�����] 
            //  �،���ЃR�[�h = �敨OP�����ʒm�L���[Params.�،���ЃR�[�h 
            //  ���� = null 
            //  �w�l = �敨OP�����ʒm�L���[Params.�w�l 
            //  ���s���� = this.getAP�w���s����()�̖߂�l 
            //  ���������� = ������ԊǗ�.get������()�̖߂�l 
            //  �ԍό��ʃG���g� = OP�����}�l�[�W��.create�ԍό��ʃG���g��()�̖߂�l 
            //  �������� = (*)�h0�F DEFAULT�i�����w��Ȃ��j�h 
            //  (W�w�l)�����w�l = 0 
            //  (*)�����ʒm�œo�^���钍���̔��������́u�����w��Ȃ��v�Œ�
            //  ���������敪 = �h��������h
            //  ���񒍕��̒����P��ID = null
            //  �[��O�J�z�Ώۃt���O = false�i�[��O�J�z�Ȃ��j
            WEB3IfoSettleContractOrderSpec l_orderSpec = WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
                l_hostFotypeOrderReceiptParams.getInstitutionCode(),
                null,
                l_hostFotypeOrderReceiptParams.getLimitPrice(),
                l_execCondType,
                l_datOrder,
                l_contractEntry,
                WEB3OrderingConditionDef.DEFAULT,
                0D,
                0D,
                null,
				WEB3OrderExpirationDateTypeDef.DAY_LIMIT,
                null,
                false);

            //1.9 �萔��()(�萔��::�萔��)
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
 
            //1.10  (*1)�v���p�e�B�Z�b�g
            //  (*1)�萔���I�u�W�F�N�g�Ɉȉ��̒ʂ�v���p�e�B���Z�b�g����B
            //  �萔��.�����`���l��      = �敨OP�����ʒm�L���[Params.�����`���l��
            //  �萔��.�،���ЃR�[�h = �敨OP�����ʒm�L���[Params.�،���ЃR�[�h
            //  �萔��.���XID        = �⏕����.get����X().getBranchId()
            //  �萔��.������     = ������ԊǗ�.get������()
            //  �萔��.����R�[�h(SONAR)    = �h52�F�ԍρh
            //  �萔��.�萔�����i�R�[�h    = �h50�F�����w���敨�h
            //  �萔��.�ٍϋ敪        = �h00�F���̑��h
            //  �萔��.�����Y�����R�[�h = �敨OP����.get�����Y�����R�[�h()
            //  �萔��.���v��敪 = OP�����}�l�[�W��.get���v��敪�i�j�̖߂�l(*)
            //  �萔��.���� = �ԍϒ������e.getTotalQuantity()
            //  �萔��.is�w�l = �ԍϒ������e.isLimitOrder()
            l_commission.setOrderChannel(l_hostFotypeOrderReceiptParams.getChannel());
            l_commission.setInstitutionCode(l_hostFotypeOrderReceiptParams.getInstitutionCode());
            l_commission.setBranchId(l_subAccount.getMainAccount().getBranch().getBranchId());
            l_commission.setOrderBizDate(new Timestamp(l_datOrder.getTime()));
            l_commission.setSonarTradedCode(WEB3TransactionTypeSONARDef.SETTLE_CONTRACT);
            l_commission.setCommissionProductCode(WEB3CommisionProductCodeDef.INDEX_FUTURES);
            l_commission.setPayType(WEB3PayTypeDef.OTHER);
            
            l_commission.setUnderlyingProductCode(
                l_product.getUnderlyingProductCode());

            l_commission.setDayTradeType(l_orderManager.getDayTradeType(l_contractEntry));
            
            l_commission.setQuantity(l_orderSpec.getTotalQuantity());
            
			l_commission.setIsLimitPrice(l_orderSpec.isLimitOrder());
            
            //1.11 get�������(Institution, String, String)(�敨OP�v���_�N�g�}�l�[�W��::get�������)
            //  [get�������()�Ɏw�肷�����] 
            //  �،���ЁF�@@�擾�����،���ЃI�u�W�F�N�g 
            //  �����R�[�h�F�@@�敨OP�����ʒm�L���[Params.�����R�[�h 
            //  �s��R�[�h�F�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��BySONAR()�̖߂�l.getMarketCode()
            WEB3IfoTradedProductImpl l_tradeProduct = l_productManagerImpl.getIfoTradedProduct(
                l_institution,
                l_hostFotypeOrderReceiptParams.getProductCode(),
                l_market.getMarketCode());

            //1.12 calc�T�Z���ϑ��v(�萔��, double, SubAccount, �敨OP�������, SettleContractEntry[], double, SideEnum, boolean)(�敨�����}�l�[�W��::calc�T�Z���ϑ��v)
            //  [calc�T�Z���ϑ��v()�Ɏw�肷�����] 
            //  �萔���F�@@�萔���I�u�W�F�N�g 
            //  �w�l�F�@@�敨OP�����ʒm�L���[Params.�w�l 
            //  �⏕�����F�@@�⏕�����I�u�W�F�N�g 
            //  �敨OP��������F�@@�敨OP��������I�u�W�F�N�g 
            //  �ԍό��ʃG���g��[]�F create�ԍό��ʃG���g���̖߂�l 
            //  ���ʁF �ԍϒ������e.getTotalQuantity() 
            //  �����F 
            //�@@�敨OP�����ʒm�L���[Params.�����敪 = �h���t�h�̏ꍇSideEnum.BUY 
            //�@@�敨OP�����ʒm�L���[Params.�����敪 = �h���t�h�̏ꍇSideEnum.SELL 
            //  isSkip���z�`�F�b�N�F�@@true(�X�L�b�v����) 
            SideEnum l_dealing = null;
            if (WEB3TradeTypeDef.BUY.equals(l_hostFotypeOrderReceiptParams.getTradeType()))
            {
                l_dealing = SideEnum.BUY;
            }
            else if (WEB3TradeTypeDef.SELL.equals(l_hostFotypeOrderReceiptParams.getTradeType()))
            {
                l_dealing = SideEnum.SELL;
            }
            WEB3IfoEstimateDeliveryAmountCalcResult l_deliveryAmount = 
                l_orderManager.calcEstimateSettlementIncome(
                    l_commission,
                    l_hostFotypeOrderReceiptParams.getLimitPrice(),
                    (WEB3GentradeSubAccount)l_subAccount,
                    l_tradeProduct,
                    l_contractEntry,
                    l_orderSpec.getTotalQuantity(),
                    l_dealing,
                    true);

            //1.13  �敨OP�ԍϒ����ʒm�X�V�C���^�Z�v�^(�ԍϒ������e)
            WEB3IfoSettleContractOrderNotifyUpdateInterceptor l_interceptor = 
                new WEB3IfoSettleContractOrderNotifyUpdateInterceptor(l_orderSpec); 

            //1.14  (*2)�v���p�e�B�Z�b�g
            //(*2)�C���^�Z�v�^�I�u�W�F�N�g�̃v���p�e�B�Ɉȉ��̒l���Z�b�g����B
            //  �C���^�Z�v�^.�萔��          = �icalc�T�Z��n���()�����̎萔���I�u�W�F�N�g�j
            //  �C���^�Z�v�^.�T�Z��n����v�Z����   = �icalc�T�Z��n���()�̖߂�l�j
            //  �C���^�Z�v�^.��������     = �h0�F DEFAULT�i�����w��Ȃ��j�h
            //  �C���^�Z�v�^.�����������Z�q      = null
            //  �C���^�Z�v�^.�t�w�l��l�^�C�v        = null
            //  �C���^�Z�v�^.�t�w�l��l       = 0
            //  �C���^�Z�v�^.(W�w�l)�����w�l        = 0
            //  �C���^�Z�v�^.���Ϗ���     = �h�������h
            //  �C���^�Z�v�^.���ʃR�[�h        = �敨OP�����ʒm�L���[Params.���ʃR�[�h
            //  �C���^�Z�v�^.�󒍓���     = �敨OP�����ʒm�L���[Params.�󒍓���
            //  �C���^�Z�v�^.������          = ������ԊǗ�.get������()�̖߂�l
            //  �C���^�Z�v�^.��n��            = �������.get��n��()�̖߂�l
            //  �C���^�Z�v�^.����敪        = ������ԊǗ�.get����敪()�̖߂�l
            l_interceptor.setCommision(l_commission);
            l_interceptor.setEstimateDeliveryAmountCalcResult(l_deliveryAmount);
            l_interceptor.setOrderCond(WEB3OrderingConditionDef.DEFAULT);
            l_interceptor.setOrderCondOperator(null);
            l_interceptor.setStopOrderBasePriceType(null);
            l_interceptor.setStopOrderBasePrice(0);
            l_interceptor.setWLimitPriceChange(0);
            l_interceptor.setClosingOrder(WEB3ClosingOrderDef.OPEN_DATE);
            l_interceptor.setOrderRequestNumber(l_hostFotypeOrderReceiptParams.getOrderRequestNumber());
            l_interceptor.setReceivedDateTime(l_hostFotypeOrderReceiptParams.getCreateDatetime());
            l_interceptor.setBizDate(l_datOrder);
            //�C���^�Z�v�^.��n��            = �������.get��n��()�̖߂�l
            l_interceptor.setDeliveryDate(l_tradeProduct.getDailyDeliveryDate());
            l_interceptor.setSessionType(WEB3GentradeTradingTimeManagement.getSessionType());
            //1.15 setThreadLocalPersistenceEventInterceptor(�敨OP�V�K�������ʒm�X�V�C���^�Z�v�^ : IfoOrderManagerPersistenceEventInterceptor)
            l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);  

            //1.16 createNewOrderId()(OP�����}�l�[�W��::createNewOrderId)
            long l_lngOrderId = l_orderManager.createNewOrderId();

            //1.17  setBusinessTimestamp( )(������ԊǗ�::setBusinessTimestamp)
            WEB3GentradeTradingTimeManagement.setBusinessTimestamp();

            //1.18  submitSettleContractOrder(�⏕���� : SubAccount, �ԍϒ������e : IfoSettleContractOrderSpec, ����ID : long, ����p�X���[�h : String, isSkip�����R�� : boolean)
            //  [submitSettleContractOrder()�Ɏw�肷�����] 
            //  arg0�i�⏕�����j�F�@@�⏕���� 
            //  arg1�i�ԍϒ������e�j�F�@@create�ԍϒ������e()�Ő��������ԍϒ������e 
            //  arg2�i�����h�c�j�F�@@OP�����}�l�[�W��.createNewOrderId() 
            //  arg3�i����p�X���[�h�j�F�@@�⏕����.getMainAccount().getTradingPassword() 
            //  arg4�iisSkip�����R���j�F�@@true 
            OrderSubmissionResult l_result = l_orderManager.submitSettleContractOrder(
                l_subAccount,
                l_orderSpec,
                l_lngOrderId,
                l_subAccount.getMainAccount().getTradingPassword(),
                true);
            if (l_result.getProcessingResult().isFailedResult())
            {
                log.error(l_result.getProcessingResult().toString());
                throw new WEB3BusinessLayerException(
                    l_result.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }
        catch (NotFoundException l_nfex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfex.getMessage(), l_nfex);
        } 
        catch (WEB3BaseException l_baseException)
        {
            log.error(l_baseException.getErrorMessage(), l_baseException);
            throw new WEB3SystemLayerException(
                l_baseException.getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME);
        }
    }
   
    /**
     * (getAP�w���s����)<BR>
     * �����̎��s����(SONAR)���AAP�w�p�̎��s�������擾���ԋp����B <BR>
     * �E����.���s����(SONAR) == "1"(������(��������))�̏ꍇ�A<BR>
     * IfoOrderExecutionConditionType.NONE�i�����Ȃ��j��Ԃ��B<BR>
     * <BR>
     * �E����.���s����(SONAR) == "3"(��t)�̏ꍇ�A<BR>
     * IfoOrderExecutionConditionType.AT_MARKET_OPEN�i���j��Ԃ��B<BR>
     * <BR>
     * �E����.���s����(SONAR) == "4"(����)�̏ꍇ�A<BR>
     * IfoOrderExecutionConditionType.AT_MARKET_CLOSE�i�����j��Ԃ��B<BR>
     * <BR>
     * �E����.���s����(SONAR) == "7"(�o�����Έ���(�s��))�̏ꍇ�A<BR>
     * IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED<BR>
     * �i�s�o���������s�j��Ԃ��B<BR>
     * <BR>
     * �E����.���s����(SONAR)����L�ȊO�̏ꍇ�́A��O��throw����B<BR>
     * @@param l_strExecCondTypeSonar - (���s����(SONAR))<BR>
     * SONAR�̎��s�����B <BR>
     * <BR>
     * "1"�F�@@������ <BR>
     * "3"�F�@@��t <BR>
     * "4"�F�@@���� <BR>
     * "7"�F�@@�o�����Έ���(�s��) <BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType
     * @@roseuid 4174F7AB00D7
     */
    protected IfoOrderExecutionConditionType getAPOrderExecCondType(String l_strExecCondTypeSonar) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAPOrderExecCondType()";
        log.entering(STR_METHOD_NAME);
        if (WEB3SonarExecutionConditionDef.UNCONDITIONDNESS.equals(l_strExecCondTypeSonar))
        {
            return IfoOrderExecutionConditionType.NONE;
        }
        else if (WEB3SonarExecutionConditionDef.AT_MARKET_OPEN.equals(l_strExecCondTypeSonar))
        {
            return IfoOrderExecutionConditionType.AT_MARKET_OPEN;
        }
        else if (WEB3SonarExecutionConditionDef.AT_MARKET_CLOSE.equals(l_strExecCondTypeSonar))
        {
            return IfoOrderExecutionConditionType.AT_MARKET_CLOSE;
        }
        else if (WEB3SonarExecutionConditionDef.NO_EXECUTED_MARKET_ORDER.equals(l_strExecCondTypeSonar))
        {
            return IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00127, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
    }
}
@
