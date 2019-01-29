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
filename	WEB3FuturesOpenContractServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �����w���敨�V�K�������T�[�r�X�����N���X(WEB3FuturesOpenMarginServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/07/21 ������ (���u) �V�K�쐬
                    : 2006/07/28 �юu�� (���u) �d�l�ύX�@@���f��483 
Revesion History    : 2007/06/21 �����F (���u)�d�l�ύX ���f��680
Revesion History    : 2007/11/19 ��іQ (���u)�d�l�ύX ���f��808
Revesion History    : 2008/03/13 �����F(���u) ���f��832 861 865
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoOpenContractOrderSpec;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PayTypeDef;
import webbroker3.common.define.WEB3StopBasePriceTypeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3FuturesClientRequestService;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoOpenContractOrderSpec;
import webbroker3.ifo.WEB3IfoOpenContractUpdateInterceptor;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.define.WEB3DivisionTypeDef;
import webbroker3.ifo.define.WEB3IfoContractTypeDef;
import webbroker3.ifo.message.WEB3FuturesOpenMarginCompleteRequest;
import webbroker3.ifo.message.WEB3FuturesOpenMarginCompleteResponse;
import webbroker3.ifo.message.WEB3FuturesOpenMarginConfirmRequest;
import webbroker3.ifo.message.WEB3FuturesOpenMarginConfirmResponse;
import webbroker3.ifo.service.delegate.WEB3FuturesOpenContractRequestAdapter;
import webbroker3.ifo.service.delegate.WEB3FuturesOpenContractService;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�敨�V�K���T�[�r�XImpl)<BR>
 * �����w���敨�V�K�������T�[�r�X�����N���X<BR>
 * 
 * @@author ������
 * @@version 1.0
 */
public class WEB3FuturesOpenContractServiceImpl extends WEB3FuturesClientRequestService implements WEB3FuturesOpenContractService
{

    /**
      * Logger
      */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesOpenContractServiceImpl.class);

    /**
     * @@roseuid 40F7A2C5007D
     */
    public WEB3FuturesOpenContractServiceImpl()
    {

    }

    /**
     * (validate����)<BR>
     * �����w���敨�̐V�K�������R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨�V�K���T�[�r�X�jvalidate�����v�Q�ƁB<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �����w���敨�V�K�������m�F���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3FuturesOpenMarginConfirmResponse
     * @@roseuid 40A84B3C037D
     */
    protected WEB3FuturesOpenMarginConfirmResponse validateOrder(WEB3FuturesOpenMarginConfirmRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrder(WEB3FuturesOpenMarginConfirmRequest l_request))";
        log.entering(STR_METHOD_NAME);

        //���N�G�X�g�f�[�^�̃`�F�b�N�����{����B
        l_request.validate();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        //3.get�⏕����()
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) getSubAccount();
        //get�㗝���͎�
        Trader l_trader = this.getTrader();
        
        //�擾�،����
        Institution l_institution = l_subAccount.getInstitution();
        
        //�����I�u�W�F�N�g���擾����B
        WEB3IfoProductManagerImpl l_ifoProductManagerImpl = 
            (WEB3IfoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();
        WEB3IfoProductImpl l_ifoProductImpl = null;
        try
        {
            if (l_request.futProductCode == null)
            {
                //[get����()�Ɏw�肷�����] 
                //�،���ЁF�@@ 
                //  �⏕�����Ɋ֘A����،���ЃI�u�W�F�N�g 
                //�����Y�����R�[�h�F�@@ 
                //  ���N�G�X�g�f�[�^.�����Y�����R�[�h 
                //�����F�@@ 
                //  ���N�G�X�g�f�[�^.���� 
                //�敨�I�v�V�������i�F�@@�h�敨�h 
                //�s�g���i�F�@@0 
                //�����F�@@000�FDEFAULT 
                //�Ώێs��R�[�h�F�@@ 
                //  ���N�G�X�g�f�[�^.�s��R�[�h 
                l_ifoProductImpl =
                    l_ifoProductManagerImpl.getIfoProduct(
                        l_institution,
                        l_request.targetProductCode,
                        l_request.delivaryMonth,
                        IfoDerivativeTypeEnum.FUTURES,
                        0,
                        WEB3DivisionTypeDef.DIVISION_DEFAULT,
                        l_request.marketCode);
            }
            else            
            {
                //�Y��������������݂��Ȃ��ꍇ�͗�O���X���[����B 
                //[get����()�Ɏw�肷�����] 
                //�،���ЁF�@@ 
                //  �⏕�����Ɋ֘A����،���ЃI�u�W�F�N�g 
                //�����R�[�h�F 
                //  ���N�G�X�g�f�[�^.�����R�[�h    
                l_ifoProductImpl = 
                    l_ifoProductManagerImpl.getIfoProduct(l_institution, l_request.futProductCode);
            }
     
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME,l_nfe);
            //�Y��������������݂��Ȃ��ꍇ�͗�O���X���[����B
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00301, 
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //���������擾����B
        Date l_datCheckDate = WEB3DateUtility.toDay(WEB3GentradeTradingTimeManagement.getOrderBizDate());

        //create���N�G�X�g�A�_�v�^(WEB3GenRequest)
        WEB3FuturesOpenContractRequestAdapter l_adapter = createRequestAdapter(l_request);

        //get�P��( )
        double l_dblLimitPrice = l_adapter.getPrice();

        //�V�K���������e�I�u�W�F�N�g�𐶐�����B
        WEB3IfoOpenContractOrderSpec l_ifoOpenContractOrderSpec = null;
        
        //���s�����F�@@�敨OP�f�[�^�A�_�v�^.get���s����(���N�G�X�g�f�[�^.���s����)�̖߂�l
        IfoOrderExecutionConditionType l_exectionContractType = 
            WEB3IfoDataAdapter.getExecutionConditionType(l_request.execCondType);

        //is�����F�@@���N�G�X�g�f�[�^.���敪 == �h�����h�̏ꍇtrue�A�ȊOfalse
        boolean l_blnContractType = false;
        if (WEB3IfoContractTypeDef.OPEN_BUY.equals(l_request.contractType))
        {
            l_blnContractType = true;
        }

        //(W�w�l)�����w�l�F�@@���N�G�X�g�f�[�^.W�w�l�p�����P��
        double l_dblWLimitPrice = 0D;
        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_request.wLimitOrderPriceDiv)
            && WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            //�w�l
            l_dblWLimitPrice = Double.parseDouble(l_request.wLimitPrice);
        }
        else
        {
            l_dblWLimitPrice = 0D;
        }
        
        //�t�w�l��l�F
        double l_dblStopOrderBasePrice = 0D;
        
        //[���N�G�X�g�f�[�^.���������敪 == �h�t�w�l�h�̏ꍇ
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            //���N�G�X�g�f�[�^.�t�w�l�p���������P��
            l_dblStopOrderBasePrice = Double.parseDouble(l_request.stopOrderCondPrice);
        }
        
        //�@@[���N�G�X�g�f�[�^.���������敪 == �hW�w�l�h�̏ꍇ]
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            //���N�G�X�g�f�[�^.W�w�l�p���������P��
            l_dblStopOrderBasePrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
        }
        
        //�iW�w�l�j���s�����F�@@
        //  �敨OP�f�[�^�A�_�v�^.get���s����(���N�G�X�g�f�[�^.W�w�l�p���s����)�̖߂�l
        IfoOrderExecutionConditionType l_executionConditionType = 
            WEB3IfoDataAdapter.getExecutionConditionType(l_request.wlimitExecCondType);

        //���񒍕��̒����P��ID�F
        //�@@�敨OP�f�[�^�A�_�v�^.get���񒍕��̒����P��ID(���N�G�X�g�f�[�^.���������敪)�̖߂�l
        Long l_lngFirstOrderUnitId = WEB3IfoDataAdapter.getFirstOrderUnitId(l_request.expirationDateType);

        //�[��O�J�z�Ώۃt���O�F
        //�@@�敨OP�f�[�^�A�_�v�^.get�[��O�J�z�Ώۃt���O(���N�G�X�g�f�[�^.���������敪, ���X.���XID)�̖߂�l
        boolean l_blnEveningSessionCarryOverFlag = WEB3IfoDataAdapter.getEveningSessionCarryOverFlag(
            l_request.expirationDateType, l_subAccount.getWeb3GenBranch().getBranchId());

        //OP�����}�l�[�W��
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

        //�����������F�@@OP�����}�l�[�W��.get�����L������(
        //���N�G�X�g�f�[�^.�����L������,�敨OP����.�����R�[�h,���N�G�X�g�f�[�^.����s��,�h�敨�h)�̖߂�l
        Date l_datExpirationDate = l_orderManager.getExpirationDate(
            l_request.expirationDate,
            l_ifoProductImpl.getProductCode(),
            l_request.marketCode,
            WEB3FuturesOptionDivDef.FUTURES);

        l_ifoOpenContractOrderSpec =
            WEB3IfoOpenContractOrderSpec.createOpenContractOrderSpec(
                l_institution.getInstitutionCode(),
                (WEB3GentradeTrader) l_trader,
                l_blnContractType,
                l_request.marketCode,
                l_ifoProductImpl,
                Double.parseDouble(l_request.futOrderQuantity),
                l_dblLimitPrice,
                l_exectionContractType,
                l_datExpirationDate,
                l_request.orderCondType,
                l_dblStopOrderBasePrice,
                l_dblWLimitPrice,
                l_executionConditionType,
                l_request.expirationDateType,
                l_lngFirstOrderUnitId,
                l_blnEveningSessionCarryOverFlag);

        WEB3FuturesOrderManagerImpl l_futuresOrderManagerImpl = 
            (WEB3FuturesOrderManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
        
        //[validate�敨�V�K������()�Ɏw�肷�����] 
        //arg0�i�⏕�����j�F�@@�⏕���� 
        //arg1�i�V�K���������e�j�F�@@create�V�K���������e()�ɂč쐬�����V�K���������e 
        NewOrderValidationResult l_result;
        l_result = 
            l_futuresOrderManagerImpl.validateFuturesOpenContractOrder(
                l_subAccount, 
                l_ifoOpenContractOrderSpec);
        
        if (l_result.getProcessingResult().isFailedResult())
        {
            log.debug("ProcessingResult() = " + l_result.getProcessingResult());
            throw new WEB3BaseException(
                l_result.getProcessingResult().getErrorInfo(), 
                STR_METHOD_NAME);
        }
        
        double l_dblQuantity = l_ifoOpenContractOrderSpec.getQuantity();
        if (Double.isNaN(l_dblQuantity))
        {
            l_dblQuantity = 0D;
        }
            
        //�萔���I�u�W�F�N�g�𐶐�����B
        WEB3GentradeCommission l_gentradeCommission = new WEB3GentradeCommission();

        //�萔��.�����`���l�� = this.get���O�C���`���l��()
        l_gentradeCommission.setOrderChannel(this.getLoginChannel());

        //�萔��.�،���ЃR�[�h = �⏕����.get�،����().getInstitutionCode()
        l_gentradeCommission.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());

        //�萔��.���XID = �⏕����.get����X().getBranchId()
        l_gentradeCommission.setBranchId(l_subAccount.getWeb3GenBranch().getBranchId());

        //�萔��.������ = ������ԊǗ�.get������()
        l_gentradeCommission.setOrderBizDate(new Timestamp(l_datCheckDate.getTime()));

        //�萔��.����R�[�h(SONAR) = �h51�F���h
        l_gentradeCommission.setSonarTradedCode(WEB3TransactionTypeSONARDef.OPEN_CONTRACT);

        //�萔��.�萔�����i�R�[�h = �h50�F�敨�h
        l_gentradeCommission.setCommissionProductCode(WEB3CommisionProductCodeDef.INDEX_FUTURES);
        
        //�萔��.�ٍϋ敪 = �h00�F���̑��h
        l_gentradeCommission.setPayType(WEB3PayTypeDef.OTHER);

        //�萔��.is�w�l 
        l_gentradeCommission.setIsLimitPrice(l_ifoOpenContractOrderSpec.isLimitOrder());
        
        //�萔��.�����Y�����R�[�h = �敨OP����.get�����Y�����R�[�h()
        l_gentradeCommission.setUnderlyingProductCode(
            l_ifoProductImpl.getUnderlyingProductCode());
        
        //�萔��.���� = �V�K���������e.getQuantity()
        l_gentradeCommission.setQuantity(l_dblQuantity);
        
        //��������I�u�W�F�N�g���擾����B
        WEB3IfoTradedProductImpl l_ifoTradedProductImpl = null; 
        try
        {
            l_ifoTradedProductImpl = 
                l_ifoProductManagerImpl.getIfoTradedProduct(
                    l_subAccount.getInstitution(), 
                    l_ifoProductImpl.getProductCode(), 
                    l_request.marketCode);
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            //�Y��������������݂��Ȃ��ꍇ�͗�O���X���[����B
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00301, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //�T�Z��n����v�Z���ʃN���X�R���X�g���N�^�B
        WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult = 
            new WEB3IfoEstimateDeliveryAmountCalcResult();
        //[calc�����()�Ɏw�肷�����] 
        //�萔���F�@@�萔���I�u�W�F�N�g 
        //�v�Z�P���F�@@�V�K���������e.getLimitPrice()
        //�⏕�����F�@@�⏕�����I�u�W�F�N�g 
        //�敨OP��������F�@@�敨OP��������I�u�W�F�N�g 
        //���ʁF �V�K���������e.getQuantity() 
        //isSkip���z�`�F�b�N�F�@@false 
        l_ifoEstimateDeliveryAmountCalcResult = 
            l_futuresOrderManagerImpl.calcEstimatePrice(
                l_gentradeCommission, 
                l_ifoOpenContractOrderSpec.getLimitPrice(), 
                l_subAccount, 
                l_ifoTradedProductImpl, 
                l_dblQuantity, 
                false);
       
        //��OP�V�K���X�V�C���^�Z�v�^
        WEB3IfoOpenContractUpdateInterceptor l_ifoOpenContractUpdateInterceptor = 
            new WEB3IfoOpenContractUpdateInterceptor((WEB3IfoOpenContractOrderSpec) l_ifoOpenContractOrderSpec);
        //(*2)�C���^�Z�v�^�I�u�W�F�N�g�̃v���p�e�B�Ɉȉ��̒l���Z�b�g����B
        //�C���^�Z�v�^.�萔�� = �icalc�����()�����̎萔���I�u�W�F�N�g�j
        l_ifoOpenContractUpdateInterceptor.setCommision(l_gentradeCommission);
        
        //�C���^�Z�v�^.�T�Z��n����v�Z���� = �icalc�����()�̖߂�l�j
        l_ifoOpenContractUpdateInterceptor.setEstimateDeliveryAmountCalcResult(
            l_ifoEstimateDeliveryAmountCalcResult);
        
        //�C���^�Z�v�^.�������� = ���N�G�X�g�f�[�^.���������敪
        l_ifoOpenContractUpdateInterceptor.setOrderCond(l_request.orderCondType);
        
        //�C���^�Z�v�^.�t�w�l��l�^�C�v = 0(�����Y����) �i*���������敪�ŋt�w�l/W�w�l�𔻒�j
        l_ifoOpenContractUpdateInterceptor.setStopOrderBasePriceType(WEB3StopBasePriceTypeDef.DEFAULT); 
        
        //�C���^�Z�v�^.�����������Z�q = ���N�G�X�g�f�[�^.�����������Z�q�@@�i*���������敪�ŋt�w�l/W�w�l�𔻒�j
        //�C���^�Z�v�^.�t�w�l��l = ���N�G�X�g�f�[�^.���������P���@@�i*���������敪�ŋt�w�l/W�w�l�𔻒�j
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            //���������敪���t�w�l�̏ꍇ
            
            //�C���^�Z�v�^.�����������Z�q
            l_ifoOpenContractUpdateInterceptor.setOrderCondOperator(l_request.stopOrderCondOperator);
                                      
            //�C���^�Z�v�^.�t�w�l��l
            double l_dblStopOrderCondPrice = 0D;
            if (WEB3StringTypeUtility.isNumber(l_request.stopOrderCondPrice))
            {
                l_dblStopOrderCondPrice = Double.parseDouble(l_request.stopOrderCondPrice);
            }
            l_ifoOpenContractUpdateInterceptor.setStopOrderBasePrice(l_dblStopOrderCondPrice);
                                    
        }
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            //���������敪��W�w�l�̏ꍇ
            
            //�C���^�Z�v�^.�����������Z�q
            l_ifoOpenContractUpdateInterceptor.setOrderCondOperator(l_request.wlimitOrderCondOperator);
            //�C���^�Z�v�^.�t�w�l��l
            double l_dblwlimitOrderCondPrice = 0D;
            if (WEB3StringTypeUtility.isNumber(l_request.wlimitOrderCondPrice))
            {
                l_dblwlimitOrderCondPrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
            }
            l_ifoOpenContractUpdateInterceptor.setStopOrderBasePrice(l_dblwlimitOrderCondPrice);            
        }
        
        //�C���^�Z�v�^.(W�w�l)�����w�l = �V�K���������e.(W�w�l)�����w�l
        l_ifoOpenContractUpdateInterceptor.setWLimitPriceChange(l_ifoOpenContractOrderSpec.getWLimitPriceChange());

        //�C���^�Z�v�^.����敪 = ������ԊǗ�.get����敪
        l_ifoOpenContractUpdateInterceptor.setSessionType(WEB3GentradeTradingTimeManagement.getSessionType());

        //validate����]��
        //�@@�@@�⏕���� �F �⏕����
        //�@@�@@�������e�C���^�Z�v�^[] �F �敨OP�V�K���X�V�C���^�Z�v�^��v�f�Ƃ����z��
        //�@@�@@�������e[] �F �V�K���������e��v�f�Ƃ����z��
        //�@@�@@�]�͍X�V�t���O �F false     
        Object[] l_objOrderSpecInterceptors = new Object[]{l_ifoOpenContractUpdateInterceptor};
        Object[] l_objOrderSpecs = new Object[]{l_ifoOpenContractOrderSpec};
        validateTradingPower(l_subAccount, l_objOrderSpecInterceptors, l_objOrderSpecs, false);
        
        WEB3GentradeBranch l_branch = (WEB3GentradeBranch) l_subAccount.getMainAccount().getBranch();
        WEB3FuturesOpenMarginConfirmResponse l_response = 
            (WEB3FuturesOpenMarginConfirmResponse) l_request.createResponse();

        //�s��ǌx���w��
        String[] l_tradeCloseMarkets = 
            WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(l_branch, WEB3FuturesOptionDivDef.FUTURES);

        //set�P��
        //�敨�V�K���������N�G�X�g�A�_�v�^ �F �������������I�u�W�F�N�g   
        //���X�|���X �F �����������X�|���X
        setPrice(l_adapter, l_response);

        //���X�|���X�f�[�^�Ɉȉ��̒ʂ�v���p�e�B���Z�b�g��
        //���X�|���X.����ID = �敨�����}�l�[�W��.createNewOrderId()�̖߂�l
        l_response.orderId = "" + l_futuresOrderManagerImpl.createNewOrderId();
        
        IfoProductRow l_ifoProductRow = (IfoProductRow) l_ifoProductImpl.getDataSourceObject();
        //���X�|���X.�����R�[�h = �敨OP����.�����R�[�h
        l_response.futProductCode = l_ifoProductImpl.getProductCode();
        //���X�|���X.������ = �敨OP����.������
        l_response.futProductName = l_ifoProductRow.getStandardName();

        //���X�|���X.�T�Z����� = �T�Z��n����v�Z����.�T�Z��n���
        l_response.estimatedContractPrice = 
            WEB3StringTypeUtility.formatNumber(l_ifoEstimateDeliveryAmountCalcResult.getEstimateDeliveryAmount());
        
        //���X�|���X.�萔���R�[�X = �T�Z��n����v�Z����.�萔���R�[�X
        l_response.commissionCourse = l_ifoEstimateDeliveryAmountCalcResult.getCommissionCourse();
        
        //���X�|���X.�萔�� = �T�Z��n����v�Z����.�萔��
        l_response.commission = 
            WEB3StringTypeUtility.formatNumber(l_ifoEstimateDeliveryAmountCalcResult.getCommission());
        
        //���X�|���X.�萔������� = �T�Z��n����v�Z����.�萔�������
        l_response.commissionConsumptionTax = 
            WEB3StringTypeUtility.formatNumber(l_ifoEstimateDeliveryAmountCalcResult.getCommissionTax());
        
        //���X�|���X.�m�F���P�� = �T�Z��n����v�Z����.�v�Z�P��        
        l_response.checkPrice = 
            WEB3StringTypeUtility.formatNumber(l_ifoEstimateDeliveryAmountCalcResult.getCalcUnitPrice());
        
        //���X�|���X.�m�F�������� = get������()�̖߂�l
        l_response.checkDate = l_datCheckDate;
        
        //���X�|���X.����I���x������ = get�s��ǌx���w��()�̖߂�l
        l_response.messageSuspension = l_tradeCloseMarkets;

        //���X�|���X.�����L������ = �V�K���������e.����������
        l_response.expirationDate = l_ifoOpenContractOrderSpec.getOrderExpDate();

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (submit����)<BR>
     * �����w���敨�V�K��������o�^����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨�V�K���T�[�r�X�jsubmit�����v�Q�ƁB<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �����w���敨�V�K�������������N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3FuturesOpenMarginCompleteResponse
     * @@roseuid 40A84B3C039C
     */
    protected WEB3FuturesOpenMarginCompleteResponse submitOrder(WEB3FuturesOpenMarginCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOrder(WEB3OptionsOpenMarginCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        
        //���N�G�X�g�f�[�^�̃`�F�b�N�����{����B
        l_request.validate();

        //���������擾����B
        //���N�G�X�g�f�[�^.�m�F��������!=null�̏ꍇ�A�R�[���B
        Date l_datBizDate = null;
        if (l_request.checkDate != null)
        {
            l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
        }
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        //3.get�⏕����()
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) getSubAccount();
        
        //get�㗝���͎�
        Trader l_trader = this.getTrader();

        //create���N�G�X�g�A�_�v�^(WEB3GenRequest)
        WEB3FuturesOpenContractRequestAdapter l_adapter = createRequestAdapter(l_request);

        //get�P��( )
        double l_dblLimitPrice = l_adapter.getPrice();

        //�擾�،����
        Institution l_institution = l_subAccount.getInstitution();

        //�敨OP��������I�u�W�F�N�g���擾����B
        WEB3IfoProductManagerImpl l_ifoProductManagerImpl = 
            (WEB3IfoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();

        WEB3IfoProductImpl l_ifoProductImpl = null;

        //�����I�u�W�F�N�g���擾����B�Y��������������݂��Ȃ��ꍇ�͗�O���X���[����B
        try
        {
            //���N�G�X�g�f�[�^.�����R�[�h!=null�̏ꍇ�A�R�[���B
            if (l_request.futProductCode != null)
            {
                //�،���ЁF�@@    
                //�⏕�����Ɋ֘A����،���ЃI�u�W�F�N�g 
                //�����R�[�h�F 
                // ���N�G�X�g�f�[�^.�����R�[�h 
                l_ifoProductImpl = l_ifoProductManagerImpl.getIfoProduct(l_institution, l_request.futProductCode);
            }
            //���N�G�X�g�f�[�^.�����R�[�h==null�̏ꍇ�A�R�[���B
            else
            {
                //�����I�u�W�F�N�g���擾����B
                //�Y��������������݂��Ȃ��ꍇ�͗�O���X���[����B
                //[get����()�Ɏw�肷�����]
                //�،���ЁF�⏕�����Ɋ֘A����،���ЃI�u�W�F�N�g
                //�����Y�����R�[�h�F���N�G�X�g�f�[�^.�w�����
                //�����F���N�G�X�g�f�[�^.����
                //�敨�I�v�V�������i�F�@@�h�敨�h
                //�s�g���i�F�@@0
                //�����F�@@000�FDEFAULT
                //�Ώێs��R�[�h�F���N�G�X�g�f�[�^.����s��
                l_ifoProductImpl = l_ifoProductManagerImpl.getIfoProduct(
                    l_institution,
                    l_request.targetProductCode,
                    l_request.delivaryMonth,
                    IfoDerivativeTypeEnum.FUTURES,
                    0,
                    WEB3DivisionTypeDef.DIVISION_DEFAULT,
                    l_request.marketCode);
            }
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            //�Y��������������݂��Ȃ��ꍇ�͗�O���X���[����B
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00301, this.getClass().getName() + STR_METHOD_NAME);
        }

        //�V�K���������e�I�u�W�F�N�g�𐶐�����B
        WEB3IfoOpenContractOrderSpec l_ifoOpenContractOrderSpec = null;

        //���s�����F�@@�敨OP�f�[�^�A�_�v�^.get���s����(���N�G�X�g�f�[�^.���s����)�̖߂�l
        IfoOrderExecutionConditionType l_exectionContractType = 
            WEB3IfoDataAdapter.getExecutionConditionType(l_request.execCondType);

        //is�����F�@@���N�G�X�g�f�[�^.���敪 == �h�����h�̏ꍇtrue�A�ȊOfalse
        boolean l_blnContractType = false;
        if (WEB3IfoContractTypeDef.OPEN_BUY.equals(l_request.contractType))
        {
            l_blnContractType = true;
        }

        //(W�w�l)�����w�l�F�@@���N�G�X�g�f�[�^.W�w�l�p�����P��
        double l_dblWLimitPrice = 0D;
        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_request.wLimitOrderPriceDiv)
            && WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            //�w�l
            l_dblWLimitPrice = Double.parseDouble(l_request.wLimitPrice);
        }
        else
        {
            l_dblWLimitPrice = 0D;
        }

        //�t�w�l��l�F
        double l_dblStopOrderBasePrice = 0D;
        
        //[���N�G�X�g�f�[�^.���������敪 == �h�t�w�l�h�̏ꍇ
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            //���N�G�X�g�f�[�^.�t�w�l�p���������P��
            l_dblStopOrderBasePrice = Double.parseDouble(l_request.stopOrderCondPrice);
        }
        
        //�@@[���N�G�X�g�f�[�^.���������敪 == �hW�w�l�h�̏ꍇ]
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            //���N�G�X�g�f�[�^.W�w�l�p���������P��
            l_dblStopOrderBasePrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
        }
        
        //�iW�w�l�j���s�����F�@@
        //  �敨OP�f�[�^�A�_�v�^.get���s����(���N�G�X�g�f�[�^.W�w�l�p���s����)�̖߂�l
        IfoOrderExecutionConditionType l_executionConditionType = 
            WEB3IfoDataAdapter.getExecutionConditionType(l_request.wlimitExecCondType);

        //���񒍕��̒����P��ID�F
        //�@@�敨OP�f�[�^�A�_�v�^.get���񒍕��̒����P��ID(���N�G�X�g�f�[�^.���������敪)�̖߂�l
        Long l_lngFirstOrderUnitId = WEB3IfoDataAdapter.getFirstOrderUnitId(l_request.expirationDateType);

        //�[��O�J�z�Ώۃt���O�F
        //�@@�敨OP�f�[�^�A�_�v�^.get�[��O�J�z�Ώۃt���O(���N�G�X�g�f�[�^.���������敪, ���X.���XID)�̖߂�l
        boolean l_blnEveningSessionCarryOverFlag = WEB3IfoDataAdapter.getEveningSessionCarryOverFlag(
            l_request.expirationDateType, l_subAccount.getWeb3GenBranch().getBranchId());

        //OP�����}�l�[�W��
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

        //�����������F�@@OP�����}�l�[�W��.get�����L������(
        //���N�G�X�g�f�[�^.�����L������,�敨OP����.�����R�[�h,���N�G�X�g�f�[�^.����s��,�h�敨�h)�̖߂�l
        Date l_datExpirationDate = l_orderManager.getExpirationDate(
            l_request.expirationDate,
            l_ifoProductImpl.getProductCode(),
            l_request.marketCode,
            WEB3FuturesOptionDivDef.FUTURES);

        l_ifoOpenContractOrderSpec =
            WEB3IfoOpenContractOrderSpec.createOpenContractOrderSpec(
                l_institution.getInstitutionCode(),
                (WEB3GentradeTrader) l_trader,
                l_blnContractType,
                l_request.marketCode,
                l_ifoProductImpl,
                Double.parseDouble(l_request.futOrderQuantity),
                l_dblLimitPrice,
                l_exectionContractType,
                l_datExpirationDate,
                l_request.orderCondType,
                l_dblStopOrderBasePrice,
                l_dblWLimitPrice,
                l_executionConditionType,
                l_request.expirationDateType,
                l_lngFirstOrderUnitId,
                l_blnEveningSessionCarryOverFlag);

        //�敨OP�V�K�������̔����R�������{����B
        WEB3FuturesOrderManagerImpl l_futuresManagerImpl = 
            (WEB3FuturesOrderManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
        //[validate�敨�V�K������()�Ɏw�肷�����] 
        //arg0�i�⏕�����j�F�@@�⏕���� 
        //arg1�i�V�K���������e�j�F�@@create�V�K���������e()�ɂč쐬�����V�K���������e 
        NewOrderValidationResult l_newOrderValidationResult = 
            l_futuresManagerImpl.validateFuturesOpenContractOrder(l_subAccount, l_ifoOpenContractOrderSpec);
        if (l_newOrderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("ProcessingResult() = " + l_newOrderValidationResult.getProcessingResult());
            throw new WEB3BaseException(
                l_newOrderValidationResult.getProcessingResult().getErrorInfo(), 
                STR_METHOD_NAME);
        }
        
        double l_dblQuantity = l_ifoOpenContractOrderSpec.getQuantity();
        if (Double.isNaN(l_dblQuantity))
        {
            l_dblQuantity = 0D;
        }
        
        //�萔���I�u�W�F�N�g�𐶐�����B
        WEB3GentradeCommission l_gentradeCommission = new WEB3GentradeCommission();
        //�萔���I�u�W�F�N�g�ɃA�N�Z�T���\�b�h���g�p���v���p�e�B���Z�b�g����B
        //�萔��.�����`���l�� = this.get���O�C���`���l��()
        l_gentradeCommission.setOrderChannel(this.getLoginChannel());
        
        //�萔��.�،���ЃR�[�h = �⏕����.get�،����().getInstitutionCode()
        l_gentradeCommission.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());
        
        //�萔��.���XID = �⏕����.get����X().getBranchId()
        l_gentradeCommission.setBranchId((l_subAccount).getWeb3GenBranch().getBranchId());
        
        //�萔��.������ = ������ԊǗ�.get������()
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        l_gentradeCommission.setOrderBizDate(new Timestamp(l_datOrderBizDate.getTime()));
        
        //�萔��.����R�[�h(SONAR) = �h51�F���h
        l_gentradeCommission.setSonarTradedCode(WEB3TransactionTypeSONARDef.OPEN_CONTRACT);
        
        //�萔��.�萔�����i�R�[�h = �h50�F�敨�h
        l_gentradeCommission.setCommissionProductCode(WEB3CommisionProductCodeDef.INDEX_FUTURES);
        
        //�萔��.�ٍϋ敪 = �h00�F���̑��h
        l_gentradeCommission.setPayType(WEB3PayTypeDef.OTHER);

        //�萔��.is�w�l
        boolean l_blnIsLimitPrice = l_ifoOpenContractOrderSpec.isLimitOrder();
        l_gentradeCommission.setIsLimitPrice(l_blnIsLimitPrice);
        
        //�萔��.�����Y�����R�[�h = �敨OP����.get�����Y�����R�[�h()
        l_gentradeCommission.setUnderlyingProductCode(
            l_ifoProductImpl.getUnderlyingProductCode());
        
        //�萔��.���� = �V�K���������e.getQuantity()
        l_gentradeCommission.setQuantity(l_dblQuantity);
        
        //��������I�u�W�F�N�g���擾����B
        WEB3IfoTradedProductImpl l_ifoTradedProductImpl = null;
        try
        {
            l_ifoTradedProductImpl = 
                l_ifoProductManagerImpl.getIfoTradedProduct(
                    l_subAccount.getInstitution(), 
                    l_ifoProductImpl.getProductCode(), 
                    l_request.marketCode);
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            //�Y��������������݂��Ȃ��ꍇ�͗�O���X���[����B
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00301, this.getClass().getName() + STR_METHOD_NAME);
        }
        //�T�Z��n����v�Z���ʃN���X�R���X�g���N�^�B
        WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResuil = 
            new WEB3IfoEstimateDeliveryAmountCalcResult();
                
        //[calc�����()�Ɏw�肷�����] 
        //�萔���F�@@�萔���I�u�W�F�N�g 
        //�v�Z�P���F
        //�@@ ���N�G�X�g�f�[�^.�m�F���P��!=null�̏ꍇ�A���N�G�X�g�f�[�^.�m�F���P���B
        //   ���N�G�X�g�f�[�^.�m�F���P��==null�̏ꍇ�A�V�K���������e.getLimitPrice()
        //  �i*1�@@���N�G�X�g�f�[�^.�����P��==null�̏ꍇ�̓[�����Z�b�g�B�j
        //�⏕�����F�@@�⏕�����I�u�W�F�N�g 
        //�敨OP��������F�@@�敨OP��������I�u�W�F�N�g 
        //���ʁF �V�K���������e.getQuantity() 
        //isSkip���z�`�F�b�N�F�@@false 
        double l_dblCheckPrice = 0D;
        if (l_request.checkPrice != null)
        {
            l_dblCheckPrice = Double.parseDouble(l_request.checkPrice);
        }
        else
        {
            l_dblCheckPrice = l_ifoOpenContractOrderSpec.getLimitPrice();
        }

        l_ifoEstimateDeliveryAmountCalcResuil = 
            l_futuresManagerImpl.calcEstimatePrice(
                l_gentradeCommission, 
                l_dblCheckPrice, 
                l_subAccount, 
                l_ifoTradedProductImpl, 
                l_dblQuantity, 
                false);
        
        //�C���^�Z�v�^�𐶐�����B
        WEB3IfoOpenContractUpdateInterceptor l_ifoOpenContractUpdateInterceptor = 
            new WEB3IfoOpenContractUpdateInterceptor(l_ifoOpenContractOrderSpec);
        //�C���^�Z�v�^.�萔�� = �icalc�����()�����̎萔���I�u�W�F�N�g�j
        l_ifoOpenContractUpdateInterceptor.setCommision(l_gentradeCommission);
        
        //�C���^�Z�v�^.�T�Z��n����v�Z���� = �icalc�����()�̖߂�l�j
        l_ifoOpenContractUpdateInterceptor.setEstimateDeliveryAmountCalcResult(l_ifoEstimateDeliveryAmountCalcResuil);
        
        //�C���^�Z�v�^.�������� = ���N�G�X�g�f�[�^.���������敪        
        l_ifoOpenContractUpdateInterceptor.setOrderCond(l_request.orderCondType);
                
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            //���������敪���t�w�l�̏ꍇ
            
            //�C���^�Z�v�^.�����������Z�q
            l_ifoOpenContractUpdateInterceptor.setOrderCondOperator(l_request.stopOrderCondOperator);
            //�C���^�Z�v�^.�t�w�l��l�^�C�v                 
            l_ifoOpenContractUpdateInterceptor.setStopOrderBasePriceType(WEB3StopBasePriceTypeDef.DEFAULT);            
            //�C���^�Z�v�^.�t�w�l��l
            double l_dblStopOrderCondPrice = 0D;
            if (WEB3StringTypeUtility.isNumber(l_request.stopOrderCondPrice))
            {
                l_dblStopOrderCondPrice = Double.parseDouble(l_request.stopOrderCondPrice);
            }
            l_ifoOpenContractUpdateInterceptor.setStopOrderBasePrice(l_dblStopOrderCondPrice);
                                    
        }
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            //���������敪��W�w�l�̏ꍇ
            
            //�C���^�Z�v�^.�����������Z�q
            l_ifoOpenContractUpdateInterceptor.setOrderCondOperator(l_request.wlimitOrderCondOperator);
            //�C���^�Z�v�^.�t�w�l��l�^�C�v                 
            l_ifoOpenContractUpdateInterceptor.setStopOrderBasePriceType(WEB3StopBasePriceTypeDef.DEFAULT);
            //�C���^�Z�v�^.�t�w�l��l
            double l_dblwlimitOrderCondPrice = 0D;
            if (WEB3StringTypeUtility.isNumber(l_request.wlimitOrderCondPrice))
            {
                l_dblwlimitOrderCondPrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
            }
            l_ifoOpenContractUpdateInterceptor.setStopOrderBasePrice(l_dblwlimitOrderCondPrice);            
        }
        
        //�C���^�Z�v�^.(W�w�l)�����w�l = �V�K���������e.(W�w�l)�����w�l
        double l_dblWLimitPriceChange = l_ifoOpenContractOrderSpec.getWLimitPriceChange();
        if (Double.isNaN(l_dblWLimitPriceChange))
        {
            l_dblWLimitPriceChange = 0D;
        }
        l_ifoOpenContractUpdateInterceptor.setWLimitPriceChange(l_dblWLimitPriceChange);

        //�C���^�Z�v�^.����敪 = ������ԊǗ�.get����敪
        l_ifoOpenContractUpdateInterceptor.setSessionType(WEB3GentradeTradingTimeManagement.getSessionType());

        //validate����]��(SubAccount, Object[], Object[], boolean)
        //�@@�@@�⏕���� �F �⏕����
        //�@@�@@�������e�C���^�Z�v�^[] �F �敨OP�V�K���X�V�C���^�Z�v�^��v�f�Ƃ����z��
        //�@@�@@�������e[] �F �V�K���������e��v�f�Ƃ����z��
        //�@@�@@�]�͍X�V�t���O �F false
        Object[] l_objOrderSpecInterceptors = new Object[]{l_ifoOpenContractUpdateInterceptor};
        Object[] l_objOrderSpecs = new Object[]{l_ifoOpenContractOrderSpec};
        validateTradingPower(l_subAccount, l_objOrderSpecInterceptors, l_objOrderSpecs, false);

        //�C���^�Z�v�^���Z�b�g����B
        l_futuresManagerImpl.setThreadLocalPersistenceEventInterceptor(l_ifoOpenContractUpdateInterceptor);

        //submit�V�K������
        //�@@�⏕���� �F �⏕�����I�u�W�F�N�g
        //�@@�V�K���������e �F create�V�K���������e()�Ő��������V�K���������e
        //�@@����ID �F ���N�G�X�g�f�[�^.����ID != null�̏ꍇ�A���N�G�X�g�f�[�^.����ID
        //�@@�@@�@@�@@�@@�@@���N�G�X�g�f�[�^.����ID == null�̏ꍇ�A�敨�����}�l�[�W��.createNewOrderId()�̖߂�l
        //�@@����p�X���[�h �F ���N�G�X�g�f�[�^.�Ïؔԍ�
        //�@@�V�K�����N�G�X�g�A�_�v�^ �F create���N�G�X�g�A�_�v�^()�Ő��������敨�V�K�����N�G�X�g�A�_�v�^�I�u�W�F�N�g
        //�@@�敨OP�T�Z��n����v�Z���� �F calc�T�Z�����()�̖߂�l
        long l_lngOrderId = 0;
        if (l_request.orderId != null)
        {
            l_lngOrderId = Long.parseLong(l_request.orderId);
        }
        else
        {
            l_lngOrderId = l_futuresManagerImpl.createNewOrderId();
        }
        submitOpenContractOrder(
            (WEB3GentradeSubAccount)l_subAccount,
            l_ifoOpenContractOrderSpec,
            l_lngOrderId,
            l_request.password,
            l_adapter,
            l_ifoEstimateDeliveryAmountCalcResuil);

        //�C���^�Z�v�^�Ƀv���p�e�B���Z�b�g����B
        WEB3FuturesOpenMarginCompleteResponse l_response = 
            (WEB3FuturesOpenMarginCompleteResponse) l_request.createResponse();

        //���X�|���X.�X�V���� = ���ݓ���(GtlUtils.getSystemTimestamp())
        l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();

        //���X�|���X.���ʔԍ� =
        //�@@���N�G�X�g�f�[�^.����ID==null�̏ꍇ�A�敨�����}�l�[�W��.createNewOrderID()�̖߂�l�B
        //  ���N�G�X�g�f�[�^.����ID!=null�̏ꍇ�A���N�G�X�g�f�[�^.����ID
        l_response.orderActionId = "" + l_lngOrderId;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * �����w���敨�V�K�������T�[�r�X���������{����B<BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Avalidate()�����܂��́A<BR>
     * submit����()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A84B3C03AC
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3FuturesOpenMarginConfirmRequest)
        {
            l_response = this.validateOrder((WEB3FuturesOpenMarginConfirmRequest) l_request);
            log.exiting(STR_METHOD_NAME);
        }
        else if (l_request instanceof WEB3FuturesOpenMarginCompleteRequest)
        {
            l_response = this.submitOrder((WEB3FuturesOpenMarginCompleteRequest) l_request);
            log.exiting(STR_METHOD_NAME);
        }
        else
        {
            log.error(STR_METHOD_NAME + "�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, STR_METHOD_NAME);
        }
        return l_response;

    }

    /**
     * (validate����]��)<BR>
     * �؋����̃`�F�b�N���s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�敨�V�K���T�[�r�X�jvalidate����]�́v���Q�ƁB<BR>
     * =============================================== <BR>
     * �V�[�P���X�} : (�敨�V�K���T�[�r�X)validate����]�� <BR>
     * ��̈ʒu     : ����]�͌���.is����t���O( )==false�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@throw�F����]�̓G���[ <BR>
     * class        : WEB3BusinessLayerException <BR>
     * tag          : BUSINESS_ERROR_01306 <BR>
     * =============================================== <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@param l_objOrderSpecInterceptors - (�������e�C���^�Z�v�^)<BR>
     * �������e�C���^�Z�v�^�B<BR>
     * @@param l_objOrderSpecs - (�������e)<BR>
     * �������e�B<BR>
     * @@param l_blnUpdateFlg - (�]�͍X�V�t���O)<BR>
     * �]�͍X�V�t���O�B <BR>
     * <BR>
     * true �F �]�͍Čv�Z���������{����  <BR>
     * false �F �]�͍Čv�Z���������{���Ȃ�<BR>
     * @@return WEB3TPTradingPowerResult
     * @@throws WEB3BaseException
     */
    protected WEB3TPTradingPowerResult validateTradingPower(
        SubAccount l_subAccount,
        Object[] l_objOrderSpecInterceptors,
        Object[] l_objOrderSpecs,
        boolean l_blnUpdateFlg) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateTradingPower(SubAccount, Object[], Object[], boolean)";
        log.entering(STR_METHOD_NAME);

        OrderTypeEnum l_orderTypeEnum = null;

        WEB3TPTradingPowerService l_tradingPowerService =
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);

        //�؋����E�]�̓`�F�b�N���s���B
        //������� �F
        if (((WEB3IfoOpenContractOrderSpec)l_objOrderSpecs[0]).isBuyToOpenOrder())
        {
            //����.�������e[0].isBuyToOpenOrder() == true�̏ꍇ�A"�敨�V�K����"
            l_orderTypeEnum = OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN;
        }
        else
        {
            //�ȊO�̏ꍇ�A"�敨�V�K����"
            l_orderTypeEnum = OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN;
        }

        //�؋����E�]�̓`�F�b�N���s���B
        //�@@[����]
        //�@@�⏕���� �F ����.�⏕����
        //�@@�������e�C���^�Z�v�^[] �F ����.�������e�C���^�Z�v�^[]
        //�@@�������e[] �F ����.�������e[]
        //�@@������� �F
        //�@@�@@�@@����.�������e[0].isBuyToOpenOrder() == true�̏ꍇ�A"�敨�V�K����"
        //�@@�@@�@@�ȊO�̏ꍇ�A"�敨�V�K����"
        //�@@�]�͍X�V�t���O �F ����.�]�͍X�V�t���O
        WEB3TPTradingPowerResult l_tradingPowerResult = l_tradingPowerService.validateTradingPower(
            (WEB3GentradeSubAccount)l_subAccount,
            l_objOrderSpecInterceptors,
            l_objOrderSpecs,
            l_orderTypeEnum,
            l_blnUpdateFlg);

        if (!l_tradingPowerResult.isResultFlg())
        {
            log.debug("����]�̓`�F�b�N�G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����]�̓`�F�b�N�G���[�B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_tradingPowerResult;
    }

    /**
     * (submit�V�K������)<BR>
     * �敨�V�K�������̒����o�^���s���B <BR>
     * <BR>
     * �������̏ڍׂ̓V�[�P���X�}�u�i�敨�V�K���T�[�r�X�jsubmit�����v�Q��<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@param l_ifoOpenContractOrderSpec - (�V�K���������e)<BR>
     * �V�K���������e�I�u�W�F�N�g�B<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * ����ID�B<BR>
     * @@param l_strTradingPassword - (����p�X���[�h)<BR>
     * ����p�X���[�h�B<BR>
     * @@param l_requestAdapter - (�V�K�����N�G�X�g�A�_�v�^)<BR>
     * �敨�V�K�����N�G�X�g�A�_�v�^�I�u�W�F�N�g�B<BR>
     * @@param l_estimateDeliveryAmountCalcResult - (�敨OP�T�Z��n����v�Z����)<BR>
     * �敨OP�T�Z��n����v�Z���ʁB<BR>
     * @@throws WEB3BaseException
     */
    protected void submitOpenContractOrder(
        WEB3GentradeSubAccount l_subAccount,
        IfoOpenContractOrderSpec l_ifoOpenContractOrderSpec,
        long l_lngOrderId,
        String l_strTradingPassword,
        WEB3FuturesOpenContractRequestAdapter l_requestAdapter,
        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOpenContractOrder(WEB3GentradeSubAccount, " +
            "IfoOpenContractOrderSpec, long, String, WEB3FuturesOpenContractRequestAdapter, " +
            "WEB3IfoEstimateDeliveryAmountCalcResult)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3FuturesOrderManagerImpl l_futuresManagerImpl =
            (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();

        //�⏕���� �F ����.�⏕�����I�u�W�F�N�g
        //�V�K���������e �F ����.�V�K���������e
        //����ID �F ����.����ID
        //����p�X���[�h �F ����.����p�X���[�h
        //isSkip�����R�� �F true�i�Œ�j
        OrderSubmissionResult l_submissionResult =
            l_futuresManagerImpl.submitOpenContractOrder(
            l_subAccount,
            l_ifoOpenContractOrderSpec,
            l_lngOrderId,
            l_strTradingPassword,
            true);

        if (l_submissionResult.getProcessingResult().isFailedResult())
        {
            //��O���X���[����
            log.debug("ProcessingResult() = " + l_submissionResult.getProcessingResult());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_submissionResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create���N�G�X�g�A�_�v�^)<BR>
     * ���N�G�X�g�A�_�v�^�̃C���X�^���X�𐶐�����B <BR>
     * <BR>
     * �������̏ڍׂ̓V�[�P���X�} <BR>
     * �u�i�敨�V�K���T�[�r�X�jvalidate�����^�i�敨�V�K���T�[�r�X�jsubmit�����v�Q��<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g�I�u�W�F�N�g�B<BR>
     * @@return WEB3FuturesOpenContractRequestAdapter
     * @@throws WEB3BaseException
     */
    protected WEB3FuturesOpenContractRequestAdapter createRequestAdapter(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createRequestAdapter(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        //���N�G�X�g�A�_�v�^���쐬����B
        //���N�G�X�g �F ����.���N�G�X�g�f�[�^
        WEB3FuturesOpenContractRequestAdapter l_adapter =
            WEB3FuturesOpenContractRequestAdapter.create(l_request);

        log.exiting(STR_METHOD_NAME);
        return l_adapter;
    }

    /**
     * (set�P��)<BR>
     * ����������return����B�i�J�������j <BR>
     * @@param l_adapter - (�敨�V�K���������N�G�X�g�A�_�v�^)<BR>
     * �敨�V�K���������N�G�X�g�A�_�v�^�I�u�W�F�N�g�B<BR>
     * @@param l_response - (���X�|���X)<BR>
     * ���X�|���X�I�u�W�F�N�g�B<BR>
     */
    protected void setPrice(WEB3FuturesOpenContractRequestAdapter l_adapter, WEB3GenResponse l_response)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "setPrice(WEB3FuturesOpenContractRequestAdapter, WEB3GenResponse)";
        log.entering(STR_METHOD_NAME);

        //����������return����B�i�J�������j
        log.exiting(STR_METHOD_NAME);
        return;
    }
}
@
