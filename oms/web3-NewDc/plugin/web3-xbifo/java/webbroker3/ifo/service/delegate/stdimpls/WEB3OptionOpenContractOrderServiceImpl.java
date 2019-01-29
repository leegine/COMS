head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionOpenContractOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : OP�V�K�������T�[�r�XImpl(WEB3OptionOpenContractOrderServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/06/21 ������ (���u) �V�K�쐬
                 001: 2004/07/22 ���Ō� (���u) WEB3CommisionProductCodeDef��WEB3IfoCommissionProductCodeDef�������ւ���
                 002: 2004/08/01 ���Ō� (���u) �Ή��o�b�O WEB3_IFO_UT-000103�A104�A105
                 003: 2004/08/09 ���Ō� (Sinocom) �Ή�����:�yWEB3-XBIFO-A-CD-0082�z
                 004: 2004/08/10 ���Ō� (Sinocom) �Ή��o�b�O JPU00031
                 005: 2004/08/13 ������@@(���u) STBUG(IFO_ST-000079)��Ή�
                 006: 2004/08/15 ������@@(���u) STBUG(IFO_ST-000083)��Ή�
                 007: 2006/07/13 ������  (���u) ���f��No.466�A474�A522�Ή�
                 008: 2006/08/30 ������  (���u) ���f��No.551�Ή�
                 009: 2006/08/31 ������  (���u) ���f��No.552�Ή�
                 010: 2006/11/28 �����  (���u) ���f��No.573�Ή�
                 011: 2006/11/30 �����  (���u) ���f��No.584�Ή�
Revesion History    : 2007/06/11 �����F�@@(���u) ���f�� 662
Revesion History    : 2007/11/19 �����q�@@(���u) ���f�� 803
Revesion History    : 2008/04/10 �����F�@@(���u) ���f�� 848 869 875
Revesion History    : 2008/05/29 �g�E�N�|�@@(���u) ���f�� 887
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoOpenContractOrderSpec;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PayTypeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;

import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoOpenContractOrderSpec;
import webbroker3.ifo.WEB3IfoOpenContractUpdateInterceptor;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionClientRequestService;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.define.WEB3DivisionTypeDef;
import webbroker3.ifo.define.WEB3IfoContractTypeDef;
import webbroker3.ifo.define.WEB3IfoProductTypeDef;
import webbroker3.ifo.message.WEB3OptionsOpenMarginCompleteRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginCompleteResponse;
import webbroker3.ifo.message.WEB3OptionsOpenMarginConfirmRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginConfirmResponse;
import webbroker3.ifo.service.delegate.WEB3OptionOpenContractOrderRequestAdapter;
import webbroker3.ifo.service.delegate.WEB3OptionOpenContractOrderService;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;

/**
 * (OP�V�K�������T�[�r�XImpl)<BR>
 * <BR>
 * �����w���I�v�V�����V�K�������T�[�r�X�����N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3OptionOpenContractOrderServiceImpl extends WEB3OptionClientRequestService implements WEB3OptionOpenContractOrderService
{

    /**
      * Logger
      */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(
            WEB3OptionOpenContractOrderServiceImpl.class);

    /**
     * @@roseuid 40C0BAF1002E
     */
    public WEB3OptionOpenContractOrderServiceImpl()
    {

    }

    /**
     * (validate����)<BR>
     * <BR>
     * �����w���I�v�V�����̐V�K�������R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iOP�V�K���T�[�r�X�jvalidate�����v�Q�ƁB<BR>
     * <BR>
     * @@param l_request - �����w���I�v�V�����V�K�������m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3OptionsOpenMarginConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 404EF125015F
     */
    protected WEB3OptionsOpenMarginConfirmResponse validateOrder(WEB3OptionsOpenMarginConfirmRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrder(WEB3OptionsOpenMarginConfirmRequest l_request))";
        log.entering(STR_METHOD_NAME);

        //���N�G�X�g�f�[�^�̃`�F�b�N�����{����B
        l_request.validate();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        //3.get�⏕����()
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) getSubAccount();

        Trader l_trader = this.getTrader();
        //�擾�،����
        Institution l_institution = l_subAccount.getInstitution();

        //�����I�u�W�F�N�g���擾����B
        WEB3IfoProductManagerImpl l_ifoProductManagerImpl = 
            (WEB3IfoProductManagerImpl)l_finApp.getTradingModule(
                ProductTypeEnum.IFO).getProductManager();

        WEB3IfoProductImpl l_ifoProductImpl = null;
        if (l_request.opProductCode == null)
        {
            try
            {
                if (WEB3IfoProductTypeDef.CALL_OPTIONS.equals(l_request.opProductType))
                {                    
                    l_ifoProductImpl = l_ifoProductManagerImpl.getIfoProduct(
                        l_institution,
                        l_request.targetProductCode,
                        l_request.delivaryMonth,
                        IfoDerivativeTypeEnum.CALL_OPTIONS,
                        Double.parseDouble(l_request.strikePrice),
                        WEB3DivisionTypeDef.DIVISION_DEFAULT,
                        l_request.marketCode);                                               
                }
                else if (WEB3IfoProductTypeDef.PUT_OPTIONS.equals(l_request.opProductType))
                {                    
                    l_ifoProductImpl = l_ifoProductManagerImpl.getIfoProduct(
                        l_institution,
                        l_request.targetProductCode,
                        l_request.delivaryMonth,
                        IfoDerivativeTypeEnum.PUT_OPTIONS,
                        Double.parseDouble(l_request.strikePrice),
                        WEB3DivisionTypeDef.DIVISION_DEFAULT,
                        l_request.marketCode);                   
                }                
            }
            catch (NotFoundException l_nfex)
            {
                log.error(STR_METHOD_NAME, l_nfex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00735, 
                    STR_METHOD_NAME);
            }
        }        
        else
        {
            //�Y��������������݂��Ȃ��ꍇ�͗�O���X���[����B
            try
            {
                l_ifoProductImpl = l_ifoProductManagerImpl.getIfoProduct(
                    l_institution, 
                    l_request.opProductCode);                              
            }
            catch (NotFoundException l_nfex)
            {
                log.error(STR_METHOD_NAME, l_nfex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00301, 
                    STR_METHOD_NAME);
            }
        }

        //���������擾����B
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //create���N�G�X�g�A�_�v�^
        //[����] ���N�G�X�g�@@�F�@@����.���N�G�X�g�f�[�^
        WEB3OptionOpenContractOrderRequestAdapter l_requestAdapter = this.createRequestAdapter(l_request);

        //get�P��( )
        double l_dblLimitPrice = l_requestAdapter.getPrice();

        //�V�K���������e�I�u�W�F�N�g�𐶐�����B
        WEB3IfoOpenContractOrderSpec l_ifoOpenContractOrderSpec = null;
        IfoOrderExecutionConditionType l_orderExecutionConditionType = null;
        
        //���s�����F�@@
        //�敨OP�f�[�^�A�_�v�^.get���s����(���N�G�X�g�f�[�^.���s����)�̖߂�l
        l_orderExecutionConditionType = 
            WEB3IfoDataAdapter.getExecutionConditionType(l_request.execCondType);
        
        //is�����F�@@���N�G�X�g�f�[�^.���敪 == �h�����h�̏ꍇtrue�A�ȊOfalse 
        boolean l_blnIsBuyContract = false;
        if (WEB3IfoContractTypeDef.OPEN_BUY.equals(l_request.contractType))
        {
            l_blnIsBuyContract = true;
        }
        
        //(W�w�l)�����w�l�F�@@���N�G�X�g�f�[�^.W�w�l�p�����P�� 
        double l_dblWLimitPrice = 0D;
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType) &&
            WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_request.wLimitOrderPriceDiv))
        {
            //�w�l
            l_dblWLimitPrice = Double.parseDouble(l_request.wLimitPrice);
        }
        else 
        {
            l_dblWLimitPrice = 0D;
        }
        
        //�t�w�l��l�F 
        //�@@[���N�G�X�g�f�[�^.���������敪 == �h�t�w�l�h�̏ꍇ]
        //   ���N�G�X�g�f�[�^.�t�w�l�p���������P��
        //  [���N�G�X�g�f�[�^.���������敪 == �hW�w�l�h�̏ꍇ]
        //   ���N�G�X�g�f�[�^.W�w�l�p���������P��
        double l_dblStopOrderBasePrice = 0.0D;
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {   
            l_dblStopOrderBasePrice = Double.parseDouble(l_request.stopOrderCondPrice);           
        }
        else if(WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_dblStopOrderBasePrice = Double.parseDouble(l_request.wlimitOrderCondPrice);            
        }
        
        //�iW�w�l�j���s�����F�@@
        //�敨OP�f�[�^�A�_�v�^.get���s����(���N�G�X�g�f�[�^.W�w�l�p���s����)�̖߂�l
        IfoOrderExecutionConditionType l_wLimitExecCondType = 
            WEB3IfoDataAdapter.getExecutionConditionType(l_request.wlimitExecCondType);

        // �����������F�@@OP�����}�l�[�W��.get�����L������(���N�G�X�g�f�[�^.�����L������,
        // �敨OP����.�����R�[�h,���N�G�X�g�f�[�^.����s��,�h�I�v�V�����h)�̖߂�l
        // OP�����}�l�[�W��
        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl =
            (WEB3OptionOrderManagerImpl) l_finApp.getTradingModule(
                ProductTypeEnum.IFO).getOrderManager();
        // �敨OP����.�����R�[�h
        String l_strProductCode = l_ifoProductImpl.getProductCode();
        Date l_datExpirationDate = l_optionOrderManagerImpl.getExpirationDate(
            l_request.expirationDate,
            l_strProductCode,
            l_request.marketCode,
            WEB3FuturesOptionDivDef.OPTION);

        //���񒍕��̒����P��ID�F
        //�敨OP�f�[�^�A�_�v�^.get���񒍕��̒����P��ID(���N�G�X�g�f�[�^.���������敪)�̖߂�l
        Long l_lngFirstOrderUnitId =
            WEB3IfoDataAdapter.getFirstOrderUnitId(l_request.expirationDateType);
        
        //�[��O�J�z�Ώۃt���O�F�@@�敨OP�f�[�^�A�_�v�^.get�[��O�J�z�Ώۃt���O
        //(���N�G�X�g�f�[�^.���������敪, �⏕�����ɊY�����镔�X.���XID)�̖߂�l
        boolean l_blnEveningSessionCarryOverFlag =
            WEB3IfoDataAdapter.getEveningSessionCarryOverFlag(
                l_request.expirationDateType,
                l_subAccount.getMainAccount().getBranch().getBranchId());
                    
        l_ifoOpenContractOrderSpec =
            WEB3IfoOpenContractOrderSpec.createOpenContractOrderSpec(
                l_institution.getInstitutionCode(),
                (WEB3GentradeTrader) l_trader,
                l_blnIsBuyContract,
                l_request.marketCode,
                l_ifoProductImpl,
                Double.parseDouble(l_request.opOrderQuantity),
                l_dblLimitPrice,
                l_orderExecutionConditionType,
                l_datExpirationDate,
                l_request.orderCondType,
                l_dblStopOrderBasePrice,
                l_dblWLimitPrice,
                l_wLimitExecCondType,
                l_request.expirationDateType,
                l_lngFirstOrderUnitId,
                l_blnEveningSessionCarryOverFlag);
 
        //�敨OP�V�K�������̔����R�������{����B
        NewOrderValidationResult l_result = null;
        l_result = l_optionOrderManagerImpl.validateOpenContractOrder(
            l_subAccount, 
            l_ifoOpenContractOrderSpec);
        if (l_result.getProcessingResult().isFailedResult())
        {
            log.debug("ProcessingResult() = " + l_result.getProcessingResult());
            throw new WEB3BusinessLayerException(
                l_result.getProcessingResult().getErrorInfo(),
                STR_METHOD_NAME);
        }
        
        //�V�K���������e.getQuantity()���擾����B
        double l_dblQuantity = l_ifoOpenContractOrderSpec.getQuantity();
        if (Double.isNaN(l_dblQuantity))
        {
            l_dblQuantity = 0D;
        }
        
        //�萔���I�u�W�F�N�g�𐶐�����B
        WEB3GentradeCommission l_gentradeCommission = new WEB3GentradeCommission();
        //(*1)�萔���I�u�W�F�N�g�ɃA�N�Z�T���\�b�h���g�p���v���p�e�B���Z�b�g����B

        //�萔��.�����`���l�� = this.get���O�C���`���l��()
        l_gentradeCommission.setOrderChannel(this.getLoginChannel());

        //�萔��.�،���ЃR�[�h = �⏕����.get�،����().getInstitutionCode()
        l_gentradeCommission.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());        

        //�萔��.���XID = �⏕����.get����X().getBranchId()
        l_gentradeCommission.setBranchId(((WEB3GentradeSubAccount) l_subAccount).getWeb3GenBranch().getBranchId());        

        //�萔��.������ = ������ԊǗ�.get������()
        l_gentradeCommission.setOrderBizDate(new Timestamp(l_datBizDate.getTime()));

        //�萔��.����R�[�h(SONAR) = �h51�F���h
        l_gentradeCommission.setSonarTradedCode(WEB3TransactionTypeSONARDef.OPEN_CONTRACT);
        
        //�萔��.�萔�����i�R�[�h = �h51�F�����w��OP�h
        l_gentradeCommission.setCommissionProductCode(WEB3CommisionProductCodeDef.INDEX_OP);        
        
        //�萔��.�ٍϋ敪 = �h00�F���̑��h               
        l_gentradeCommission.setPayType(WEB3PayTypeDef.OTHER);      
        
        //�萔��.is�w�l = �V�K���������e.isLimitOrder()
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
            l_ifoTradedProductImpl = l_ifoProductManagerImpl.getIfoTradedProduct(
                l_subAccount.getInstitution(), 
                l_ifoProductImpl.getProductCode(), 
                l_request.marketCode);    
        }
        catch (NotFoundException l_nfex)
        {
            log.error(STR_METHOD_NAME, l_nfex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00301, 
                STR_METHOD_NAME);
        }

        //�T�Z��n����v�Z���ʃN���X�R���X�g���N�^�B
        //[calc�T�Z��n���()�Ɏw�肷�����] 
        //�萔���F�@@�萔���I�u�W�F�N�g 
        //�v�Z�P���F�@@�������e.getLimitPrice() 
        //�⏕�����F�@@�⏕�����I�u�W�F�N�g 
        //�敨OP��������F�@@�敨OP��������I�u�W�F�N�g 
        //���ʁF �V�K���������e.getQuantity() 
        //�����F 
        //  ���N�G�X�g�f�[�^.���敪 = �h���h�̏ꍇSideEnum.BUY 
        //  ���N�G�X�g�f�[�^.���敪 = �h���h�̏ꍇSideEnum.SELL 
        //is�ԍϒ����F�@@false 
        //isSkip���z�`�F�b�N�F�@@false 
        WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult =
            new WEB3IfoEstimateDeliveryAmountCalcResult();

        SideEnum l_side = null;
        if (WEB3IfoContractTypeDef.OPEN_BUY.equalsIgnoreCase(l_request.contractType))
        {
            l_side = SideEnum.BUY;
        }
        else
        {
            l_side = SideEnum.SELL;
        }

        //�T�Z��n������v�Z����B            
        l_ifoEstimateDeliveryAmountCalcResult =
            l_optionOrderManagerImpl.calcEstimateDeliveryAmount(
                l_gentradeCommission,
                l_ifoOpenContractOrderSpec.getLimitPrice(),
                l_subAccount,
                l_ifoTradedProductImpl,
                l_dblQuantity,
                l_side,
                false,
                false);

        WEB3GentradeCommission l_wGentradeCommission = new WEB3GentradeCommission();
        //�T�Z��n����v�Z���ʃN���X�R���X�g���N�^�B
        WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResultW = 
            new WEB3IfoEstimateDeliveryAmountCalcResult();
                  
        //�����A���AW�w�l�����̏ꍇ
        //(�V�K���������e.isBuyToOpenOrder() == true &&
        // �V�K���������e.��������() == "W�w�l")�������s���B
        if (l_ifoOpenContractOrderSpec.isBuyToOpenOrder() 
            && WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(
                l_ifoOpenContractOrderSpec.getOrderCond()))
        {
            //(*2)W�w�l�p�萔���I�u�W�F�N�g�Ɉȉ��̒ʂ�v���p�e�B���Z�b�g����
            //W�w�l�p�萔��.�����`���l�� = this.get���O�C���`���l��()
            l_wGentradeCommission.setOrderChannel(this.getLoginChannel());
            
            //W�w�l�p�萔��.�،���ЃR�[�h = �⏕����.get�،����().getInstitutionCode()
            l_wGentradeCommission.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());
            
            //W�w�l�p�萔��.���XID = �⏕����.get����X().getBranchId()
            l_wGentradeCommission.setBranchId(l_subAccount.getWeb3GenBranch().getBranchId());
            
            //W�w�l�p�萔��.������ = ������ԊǗ�.get������()
            l_wGentradeCommission.setOrderBizDate(
                new Timestamp(WEB3GentradeTradingTimeManagement.getOrderBizDate().getTime()));
            
            //W�w�l�p�萔��.����R�[�h(SONAR) = �h51�F���h
            l_wGentradeCommission.setSonarTradedCode(WEB3TransactionTypeSONARDef.OPEN_CONTRACT);

            //W�w�l�p�萔��.�萔�����i�R�[�h = �h51�F�����w��OP�h
            l_wGentradeCommission.setCommissionProductCode(WEB3CommisionProductCodeDef.INDEX_OP);        
            
            //W�w�l�p�萔��.�ٍϋ敪 = �h00�F���̑��h
            l_wGentradeCommission.setPayType(WEB3PayTypeDef.OTHER);
            
            //W�w�l�p�萔��.is�w�l = �V�K���������e.(W�w�l)�����w�l != 0�̏ꍇ�Atrue�B�ȊO�Afalse�B
            if (l_ifoOpenContractOrderSpec.getWLimitPriceChange() == 0 )
            {
                l_wGentradeCommission.setIsLimitPrice(false);                
            }
            else
            {
                l_wGentradeCommission.setIsLimitPrice(true);
            }
            
            //W�w�l�p�萔��.�����Y�����R�[�h = �敨OP����.get�����Y�����R�[�h()
            l_wGentradeCommission.setUnderlyingProductCode(
                l_ifoProductImpl.getUnderlyingProductCode());
        
            //W�w�l�p�萔��.���� = �V�K���������e.getQuantity()
            l_wGentradeCommission.setQuantity(l_dblQuantity);
            
            //W�w�l�������̊T�Z��n������v�Z����B
            //[calc�T�Z��n���()�Ɏw�肷�����]
            //�萔���F�@@W�w�l�p�萔���I�u�W�F�N�g
            //�v�Z�P���F�@@�V�K���������e.(W�w�l)�����w�l
            //�⏕�����F�@@�⏕�����I�u�W�F�N�g
            //�敨OP��������F�@@�敨OP��������I�u�W�F�N�g
            //���ʁF �V�K���������e.getQuantity()
            //�����F 
            //  ���N�G�X�g�f�[�^.���敪 = �h���h�̏ꍇSideEnum.BUY
            //  ���N�G�X�g�f�[�^.���敪 = �h���h�̏ꍇSideEnum.SELL
            //is�ԍϒ����F�@@false
            //isSkip���z�`�F�b�N�F�@@false
            l_ifoEstimateDeliveryAmountCalcResultW =
                l_optionOrderManagerImpl.calcEstimateDeliveryAmount(
                    l_wGentradeCommission,
                    l_ifoOpenContractOrderSpec.getWLimitPriceChange(),
                    l_subAccount,
                    l_ifoTradedProductImpl,
                    l_dblQuantity,
                    l_side,
                    false,
                    false);   
        }

        //(*3)�C���^�Z�v�^�I�u�W�F�N�g�̃v���p�e�B�Ɉȉ��̒l���Z�b�g����B
        WEB3IfoOpenContractUpdateInterceptor l_ifoOpenContractUpdateInterceptor = 
            new WEB3IfoOpenContractUpdateInterceptor(l_ifoOpenContractOrderSpec);
        
        WEB3IfoEstimateDeliveryAmountCalcResult l_calcResultForCheck = null;
        boolean l_blnIsWLimitCommission = false;
        //[�T�Z��n����Ǝ萔���̐ݒ�] 
        //(*)�����A���AW�w�l�����̏ꍇ�́A�ȉ��̔�����s��
        //�߂�l(*1)�Ɩ߂�l(*2)�̍S������������r���āA
        //��r���ʂ������ق��̖߂�l�̊T�Z��n����v�Z���ʃI�u�W�F�N�g���g�p����
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_ifoOpenContractOrderSpec.getOrderCond())
            && l_ifoOpenContractOrderSpec.isBuyToOpenOrder())
        {
            if (l_ifoEstimateDeliveryAmountCalcResult.getRestraintTurnover() >= l_ifoEstimateDeliveryAmountCalcResultW.getRestraintTurnover())
            {
                //�C���^�Z�v�^.�萔�� = �icalc�T�Z��n���()�����̎萔���I�u�W�F�N�g�j
                l_ifoOpenContractUpdateInterceptor.setCommision(l_gentradeCommission);
                //�C���^�Z�v�^.�T�Z��n����v�Z���� = �icalc�T�Z��n���()�̖߂�l�j
                l_ifoOpenContractUpdateInterceptor.setEstimateDeliveryAmountCalcResult(l_ifoEstimateDeliveryAmountCalcResult);
                
                l_calcResultForCheck = l_ifoEstimateDeliveryAmountCalcResult;
            }
            else
            {
                l_blnIsWLimitCommission = true;
                //�C���^�Z�v�^.�萔�� = �icalc�T�Z��n���()�����̎萔���I�u�W�F�N�g�j
                l_ifoOpenContractUpdateInterceptor.setCommision(l_wGentradeCommission);
                //�C���^�Z�v�^.�T�Z��n����v�Z���� = �icalc�T�Z��n���()�̖߂�l�j
                l_ifoOpenContractUpdateInterceptor.setEstimateDeliveryAmountCalcResult(l_ifoEstimateDeliveryAmountCalcResultW);
                l_calcResultForCheck = l_ifoEstimateDeliveryAmountCalcResultW;
            }
        }
        else
        {
            //�C���^�Z�v�^.�萔�� = �icalc�T�Z��n���()�����̎萔���I�u�W�F�N�g�j
            l_ifoOpenContractUpdateInterceptor.setCommision(l_gentradeCommission);
            //�C���^�Z�v�^.�T�Z��n����v�Z���� = �icalc�T�Z��n���()�̖߂�l�j
            l_ifoOpenContractUpdateInterceptor.setEstimateDeliveryAmountCalcResult(l_ifoEstimateDeliveryAmountCalcResult);
            l_calcResultForCheck = l_ifoEstimateDeliveryAmountCalcResult;
        }

        //�C���^�Z�v�^.�������� = ���N�G�X�g�f�[�^.���������敪
        l_ifoOpenContractUpdateInterceptor.setOrderCond(l_request.orderCondType);
        
        //�C���^�Z�v�^.�����������Z�q = ���N�G�X�g�f�[�^.�����������Z�q�@@�i*���������敪�ŋt�w�l/W�w�l�𔻒�j
        //�C���^�Z�v�^.�t�w�l��l�^�C�v = ���N�G�X�g�f�[�^.�v���~�A���^�����Y���i�@@�i*���������敪�ŋt�w�l/W�w�l�𔻒�j
        //�C���^�Z�v�^.�t�w�l��l = ���N�G�X�g�f�[�^.���������P���@@�i*���������敪�ŋt�w�l/W�w�l�𔻒�j        
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_ifoOpenContractUpdateInterceptor.setOrderCondOperator(l_request.wlimitOrderCondOperator);
            l_ifoOpenContractUpdateInterceptor.setStopOrderBasePriceType(l_request.wlimitPremium_underlyingAssets);
            l_ifoOpenContractUpdateInterceptor.setStopOrderBasePrice(Double.parseDouble(l_request.wlimitOrderCondPrice));            
        }
        else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_ifoOpenContractUpdateInterceptor.setOrderCondOperator(l_request.stopOrderCondOperator);
            l_ifoOpenContractUpdateInterceptor.setStopOrderBasePriceType(l_request.stopPremium_underlyingAssets);
            l_ifoOpenContractUpdateInterceptor.setStopOrderBasePrice(Double.parseDouble(l_request.stopOrderCondPrice));               
        }
        //�C���^�Z�v�^.(W�w�l)�����w�l = �V�K���������e.(W�w�l)�����w�l      
        l_ifoOpenContractUpdateInterceptor.setWLimitPriceChange(l_ifoOpenContractOrderSpec.getWLimitPriceChange());
        //�C���^�Z�v�^.����敪 = ������ԊǗ�.get����敪()
        l_ifoOpenContractUpdateInterceptor.setSessionType(WEB3GentradeTradingTimeManagement.getSessionType());

        //�@@����]�̓T�[�r�X.validate����]��()���R�[������B
        //�@@[����]
        //�@@�@@�⏕�����@@�F�@@�⏕����
        //�@@�@@�������e�C���^�Z�v�^[]�@@�F�@@�敨OP�V�K���X�V�C���^�Z�v�^��v�f�Ƃ����z��
        //�@@�@@�������e[]�@@�F�@@�V�K���������e��v�f�Ƃ����z��
        //�@@�@@�]�͍X�V�t���O�@@�F�@@false
        Object[] l_orderSpecIntercepter = {l_ifoOpenContractUpdateInterceptor};
        Object[] l_orderSpec = {l_ifoOpenContractOrderSpec};
        this.validateTradingPower(l_subAccount, l_orderSpecIntercepter, l_orderSpec, false);

        WEB3GentradeBranch l_branch = (WEB3GentradeBranch) l_subAccount.getMainAccount().getBranch();
        WEB3OptionsOpenMarginConfirmResponse l_response = 
            (WEB3OptionsOpenMarginConfirmResponse) l_request.createResponse();

        //�s��ǌx���w��
        String[] l_tradeCloseMarkets = 
            WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(l_branch, WEB3FuturesOptionDivDef.OPTION);

        //set�P��
        //�@@[�w�肷�����]
        //�@@OP�V�K���������N�G�X�g�A�_�v�^�@@�F�@@�������������I�u�W�F�N�g
        //�@@���X�|���X�@@�F�@@�����������X�|���X
        this.setPrice(l_requestAdapter, l_response);

        //(*4)���X�|���X�f�[�^�Ɉȉ��̒ʂ�v���p�e�B���Z�b�g����B
        //���X�|���X.����ID = OP�����}�l�[�W��.createNewOrderId()�̖߂�l
        l_response.orderId = "" + l_optionOrderManagerImpl.createNewOrderId();
        
        IfoProductRow l_ifoProductRow = (IfoProductRow) l_ifoProductImpl.getDataSourceObject();
        //���X�|���X.�����R�[�h = �敨OP����.�����R�[�h
        l_response.opProductCode = l_ifoProductImpl.getProductCode();
        //���X�|���X.������ = �敨OP����.������
        l_response.opProductName = l_ifoProductRow.getStandardName();
        
        //���X�|���X.�T�Z����� = (*)�T�Z��n����v�Z����.�T�Z��n���
        l_response.estimatedPrice = 
            WEB3StringTypeUtility.formatNumber(l_calcResultForCheck.getEstimateDeliveryAmount());

        //���X�|���X.�萔���R�[�X = (*)�T�Z��n����v�Z����.�萔���R�[�X
        l_response.commissionCourse = l_calcResultForCheck.getCommissionCourse();
        
        //���X�|���X.�萔�� = (*)�T�Z��n����v�Z����.�萔��
        l_response.commission = WEB3StringTypeUtility.formatNumber(l_calcResultForCheck.getCommission());
        
        //���X�|���X.�萔������� = (*)�T�Z��n����v�Z����.�萔�������
        l_response.commissionConsumptionTax = 
            WEB3StringTypeUtility.formatNumber(l_calcResultForCheck.getCommissionTax());
        
        //���X�|���X.�m�F���P�� = (*)�������擾���Ă���ꍇ�i�T�Z��n����̎萔���I�u�W�F�N�g.is�w�l() == false�̏ꍇ�j�A
        //�T�Z��n����v�Z����.�v�Z�P��
        //(*)�����A���AW�w�l�����̏ꍇ�́A�؋����E�]�̓`�F�b�N�Ɏg�p�����T�Z��n�v�Z���ʂ�p����
        if (l_blnIsWLimitCommission)
        {
            if (!l_wGentradeCommission.isLimitPrice())
            {
                l_response.checkPrice = WEB3StringTypeUtility.formatNumber(l_calcResultForCheck.getCalcUnitPrice());
            }
        }
        else
        {
            if (!l_gentradeCommission.isLimitPrice())
            {
                l_response.checkPrice = WEB3StringTypeUtility.formatNumber(l_calcResultForCheck.getCalcUnitPrice());
            }
        }
        
        //���X�|���X.�m�F�������� = get������()�̖߂�l
        l_response.checkDate = WEB3DateUtility.toDay(l_datBizDate);
        //���X�|���X.����I���x������ = get�s��ǌx���w��()�̖߂�l
        l_response.messageSuspension = l_tradeCloseMarkets;
        //���X�|���X.�����L������ = �V�K���������e.����������
        l_response.expirationDate = l_ifoOpenContractOrderSpec.getOrderExpDate();

        log.exiting(STR_METHOD_NAME);
        return l_response;

    }

    /**
     * (submit����)<BR>
     * <BR>
     * �����w���I�v�V�����V�K��������o�^����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iOP�V�K���T�[�r�X�jsubmit�����v�Q�ƁB<BR>
     * <BR>
     * <BR>
     * @@param l_request - �����w���I�v�V�����V�K�������������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3OptionsOpenMarginCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 404EF125017F
     */
    protected WEB3OptionsOpenMarginCompleteResponse submitOrder(WEB3OptionsOpenMarginCompleteRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOrder(WEB3OptionsOpenMarginCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        
        //���N�G�X�g�f�[�^�̃`�F�b�N�����{����B
        l_request.validate();

        //���������擾����B        
        //[get������()�Ɏw�肷�����] 
        //�m�F�������� = ���N�G�X�g�f�[�^.�m�F�������� 
        Date l_datBizDate = null;
        if (l_request.checkDate != null)
        {
            l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
        }

                
        //3.get�⏕����()
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) getSubAccount();

        Trader l_trader = this.getTrader();

        //create���N�G�X�g�A�_�v�^(WEB3GenRequest)
        WEB3OptionOpenContractOrderRequestAdapter l_requestAdapter = this.createRequestAdapter(l_request);

        //get�P��( )
        double l_dblLimitPrice = l_requestAdapter.getPrice();

        //�擾�،����
        Institution l_institution = l_subAccount.getInstitution();

        //�敨OP��������I�u�W�F�N�g���擾����B        
        //�����I�u�W�F�N�g���擾����B�Y��������������݂��Ȃ��ꍇ�͗�O���X���[����B        
        WEB3IfoProductManagerImpl l_ifoProductManagerImpl = 
            (WEB3IfoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();

        WEB3IfoProductImpl l_ifoProductImpl = null;
        
        IfoDerivativeTypeEnum l_derivativeTypeEnum = null;
        if (WEB3IfoProductTypeDef.CALL_OPTIONS.equals(l_request.opProductType))
        {
            l_derivativeTypeEnum = IfoDerivativeTypeEnum.CALL_OPTIONS;
        }
        else if (WEB3IfoProductTypeDef.PUT_OPTIONS.equals(l_request.opProductType))
        {
            l_derivativeTypeEnum = IfoDerivativeTypeEnum.PUT_OPTIONS;
        }
        double l_dblStrikePrice = 0.0D;
        if (l_request.strikePrice != null)
        {
            l_dblStrikePrice = Double.parseDouble(l_request.strikePrice);
        }
        //�����I�u�W�F�N�g���擾����B�Y��������������݂��Ȃ��ꍇ�͗�O���X���[����B
        //�،���ЁF�@@ 
        // �⏕�����Ɋ֘A����،���ЃI�u�W�F�N�g 
        //�����R�[�h�F 
        // ���N�G�X�g�f�[�^.�����R�[�h 
        try
        {
            if (l_request.opProductCode != null)
            {    
                l_ifoProductImpl = l_ifoProductManagerImpl.getIfoProduct(l_institution, l_request.opProductCode);                 
            }
            //�敨OP�v���_�N�g�}�l�[�W��.get����(�،���ЁA�����Y�����R�[�h�A�E�E�E)�̓��N�G�X�g�f�[�^.�����R�[�h==�̏ꍇ�A�R�[���B
            //�����I�u�W�F�N�g���擾����B 
            else
            {
                l_ifoProductImpl = 
                    l_ifoProductManagerImpl.getIfoProduct(
                    l_institution, 
                    l_request.targetProductCode, 
                    l_request.delivaryMonth, 
                    l_derivativeTypeEnum, 
                    l_dblStrikePrice,
                    WEB3DivisionTypeDef.DIVISION_DEFAULT,
                    l_request.marketCode);
            }
        }
        catch (NotFoundException l_nfex)
        {
            log.error(STR_METHOD_NAME, l_nfex);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00301, STR_METHOD_NAME);
        } 
        
        //�V�K���������e�I�u�W�F�N�g�𐶐�����B
        WEB3IfoOpenContractOrderSpec l_ifoOpenContractOrderSpec = null;
        IfoOrderExecutionConditionType l_orderExecutionConditionType = null;
        
        //���s�����F�@@
        //�敨OP�f�[�^�A�_�v�^.get���s����(���N�G�X�g�f�[�^.���s����)�̖߂�l 
        l_orderExecutionConditionType = 
            WEB3IfoDataAdapter.getExecutionConditionType(l_request.execCondType);
       
        //is�����F�@@���N�G�X�g�f�[�^.���敪 == �h�����h�̏ꍇtrue�A�ȊOfalse 
        boolean l_blnIsBuyContract = false;
        if (WEB3IfoContractTypeDef.OPEN_BUY.equals(l_request.contractType))
        {
            l_blnIsBuyContract = true;
        }

        double l_dblWLimitPrice = 0D;
        //(W�w�l)�����w�l�F�@@���N�G�X�g�f�[�^.W�w�l�p�����P�� 
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType)
            && WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_request.wLimitOrderPriceDiv))
        {
            //�w�l
            l_dblWLimitPrice = Double.parseDouble(l_request.wLimitPrice);
        }
        else 
        {
            l_dblWLimitPrice = 0D;
        }
        
        //�t�w�l��l�F 
        //�@@[���N�G�X�g�f�[�^.���������敪 == �h�t�w�l�h�̏ꍇ]
        //   ���N�G�X�g�f�[�^.�t�w�l�p���������P��
        //  [���N�G�X�g�f�[�^.���������敪 == �hW�w�l�h�̏ꍇ]
        //   ���N�G�X�g�f�[�^.W�w�l�p���������P��
        double l_dblStopOrderBasePrice = 0.0D;
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {   
            l_dblStopOrderBasePrice = Double.parseDouble(l_request.stopOrderCondPrice);            
        }
        else if(WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_dblStopOrderBasePrice = Double.parseDouble(l_request.wlimitOrderCondPrice);            
        }
        
        //�iW�w�l�j���s�����F�@@
        //�敨OP�f�[�^�A�_�v�^.get���s����(���N�G�X�g�f�[�^.W�w�l�p���s����)�̖߂�l
        IfoOrderExecutionConditionType l_wLimitExecCondType = 
            WEB3IfoDataAdapter.getExecutionConditionType(l_request.wlimitExecCondType);
        
        //���񒍕��̒����P��ID�F
        //�敨OP�f�[�^�A�_�v�^.get���񒍕��̒����P��ID(���N�G�X�g�f�[�^.���������敪)�̖߂�l
        Long l_lngFirstOrderUnitId =
            WEB3IfoDataAdapter.getFirstOrderUnitId(l_request.expirationDateType);

        //�[��O�J�z�Ώۃt���O�F�@@�敨OP�f�[�^�A�_�v�^.get�[��O�J�z�Ώۃt���O
        //(���N�G�X�g�f�[�^.���������敪, �⏕�����ɊY�����镔�X.���XID)�̖߂�l
        boolean l_blnEveningSessionCarryOverFlag =
            WEB3IfoDataAdapter.getEveningSessionCarryOverFlag(
                l_request.expirationDateType,
                l_subAccount.getMainAccount().getBranch().getBranchId());

        // �����������F�@@OP�����}�l�[�W��.get�����L������(���N�G�X�g�f�[�^.�����L������,
        // �敨OP����.�����R�[�h,���N�G�X�g�f�[�^.����s��,�h�I�v�V�����h)�̖߂�l
        // OP�����}�l�[�W��
        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl =
            (WEB3OptionOrderManagerImpl) l_finApp.getTradingModule(
                ProductTypeEnum.IFO).getOrderManager();
        // �敨OP����.�����R�[�h
        String l_strProductCode = l_ifoProductImpl.getProductCode();
        Date l_datExpirationDate = l_optionOrderManagerImpl.getExpirationDate(
            l_request.expirationDate,
            l_strProductCode,
            l_request.marketCode,
            WEB3FuturesOptionDivDef.OPTION);

        l_ifoOpenContractOrderSpec =
            WEB3IfoOpenContractOrderSpec.createOpenContractOrderSpec(
                l_institution.getInstitutionCode(),
                (WEB3GentradeTrader) l_trader,
                l_blnIsBuyContract,
                l_request.marketCode,
                l_ifoProductImpl,
                Double.parseDouble(l_request.opOrderQuantity),
                l_dblLimitPrice,
                l_orderExecutionConditionType,
                l_datExpirationDate,
                l_request.orderCondType,
                l_dblStopOrderBasePrice,
                l_dblWLimitPrice,
                l_wLimitExecCondType,
                l_request.expirationDateType,
                l_lngFirstOrderUnitId,
                l_blnEveningSessionCarryOverFlag);

        //�敨OP�V�K�������̔����R�������{����B        
        NewOrderValidationResult l_newOrderValidationResult = 
            l_optionOrderManagerImpl.validateOpenContractOrder(l_subAccount, l_ifoOpenContractOrderSpec);
        
        if (l_newOrderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("ProcessingResult() = " + l_newOrderValidationResult.getProcessingResult());
            throw new WEB3BaseException(
                l_newOrderValidationResult.getProcessingResult().getErrorInfo(),
                STR_METHOD_NAME);
        }
        
        //�V�K���������e.getQuantity()���擾����B
        double l_dblQuantity = l_ifoOpenContractOrderSpec.getQuantity();
        if (Double.isNaN(l_dblQuantity))
        {
            l_dblQuantity = 0D;
        }
        
        //�萔���I�u�W�F�N�g�𐶐�����B
        WEB3GentradeCommission l_gentradeCommission = new WEB3GentradeCommission();        
        //(*1)�萔���I�u�W�F�N�g�ɃA�N�Z�T���\�b�h���g�p���v���p�e�B���Z�b�g����B
        
        //�萔��.�����`���l�� = this.get���O�C���`���l��()
        l_gentradeCommission.setOrderChannel(this.getLoginChannel());
        
        //�萔��.�،���ЃR�[�h = �⏕����.get�،����().getInstitutionCode()
        l_gentradeCommission.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());
        
        //�萔��.���XID = �⏕����.get����X().getBranchId()
        l_gentradeCommission.setBranchId((l_subAccount).getWeb3GenBranch().getBranchId());
        
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        //�萔��.������ = ������ԊǗ�.get������()
        l_gentradeCommission.setOrderBizDate(new Timestamp(l_datOrderBizDate.getTime()));
        
        //�萔��.����R�[�h(SONAR) = �h51�F���h
        l_gentradeCommission.setSonarTradedCode(WEB3TransactionTypeSONARDef.OPEN_CONTRACT);
        
        //�萔��.�萔�����i�R�[�h = �h51�F�����w��OP�h
        l_gentradeCommission.setCommissionProductCode(WEB3CommisionProductCodeDef.INDEX_OP);
        
        //�萔��.�ٍϋ敪 = �h00�F���̑��h        
        l_gentradeCommission.setPayType(WEB3PayTypeDef.OTHER);

        //�萔��.is�w�l���Z�b�g����B
        //�@@1)���N�G�X�g�f�[�^.����ID == null�̏ꍇ�A
        //�@@ �V�K���������e.isLimitOrder()
        //�@@2)��L�ȊO�Athis.is�w�l(���N�G�X�g�f�[�^)�B
        if(l_request.orderId == null)
        {
            l_gentradeCommission.setIsLimitPrice(l_ifoOpenContractOrderSpec.isLimitOrder());   
        }
        else
        {
            l_gentradeCommission.setIsLimitPrice(this.isLimitPrice(l_request));
        }
        
        //�萔��.�����Y�����R�[�h = �敨OP����.get�����Y�����R�[�h()
        l_gentradeCommission.setUnderlyingProductCode(
            l_ifoProductImpl.getUnderlyingProductCode());
        
        //�萔��.���� = �V�K���������e.getQuantity()
        l_gentradeCommission.setQuantity(l_dblQuantity);
        
        WEB3IfoTradedProductImpl l_ifoTradedProductImpl = null;
        try
        {
            l_ifoTradedProductImpl = 
                l_ifoProductManagerImpl.getIfoTradedProduct(l_subAccount.getInstitution(), l_ifoProductImpl.getProductCode(), l_request.marketCode);    
        }
        catch (NotFoundException l_nfex)
        {
            log.error(STR_METHOD_NAME, l_nfex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00301, 
                STR_METHOD_NAME);
        } 
        
        //�T�Z��n����v�Z���ʃN���X�R���X�g���N�^�B
        WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult = 
            new WEB3IfoEstimateDeliveryAmountCalcResult();        

        SideEnum l_side = null;
        if (WEB3IfoContractTypeDef.OPEN_BUY.equalsIgnoreCase(l_request.contractType))
        {
            l_side = SideEnum.BUY;
        }
        else
        {
            l_side = SideEnum.SELL;
        }

        double l_dblPrice = 0D;
        double l_dblWLimitPriceTemp = 0D;
        if (l_request.wLimitPrice != null)
        {
            l_dblWLimitPriceTemp = Double.parseDouble(l_request.wLimitPrice);
        }
        if (l_request.orderId != null)
        {
            if (l_request.checkPrice != null)
            {
                l_dblPrice = Double.parseDouble(l_request.checkPrice); 
            }
            else
            {
                if (l_ifoOpenContractOrderSpec.isBuyToOpenOrder()
                    && l_ifoOpenContractOrderSpec.getLimitPrice() < l_dblWLimitPriceTemp)
                {
                    l_dblPrice = l_dblWLimitPriceTemp;
                }
                else
                {
                    l_dblPrice = l_ifoOpenContractOrderSpec.getLimitPrice();
                }
            }
        }
        else
        {
            l_dblPrice = l_ifoOpenContractOrderSpec.getLimitPrice(); 
        }

        //[calc�T�Z��n���()�Ɏw�肷�����]
        //�萔���F�@@�萔���I�u�W�F�N�g
        //�w�l�F
        //�@@[���N�G�X�g�f�[�^.����ID != null�̏ꍇ]
        //�@@�@@���N�G�X�g�f�[�^.�m�F���P�� != null�̏ꍇ�A���N�G�X�g�f�[�^.�m�F���P���B
        //�@@�@@���N�G�X�g�f�[�^.�m�F���P�� == null�̏ꍇ�A
        //�@@�@@�@@�������e.isBuyToOpenOrder() == true 
        //      and �������e.getLimitPrice() < ���N�G�X�g�f�[�^.W�w�l�p�����P��(*1) �Ȃ�΁A
        //�@@�@@�@@�@@���N�G�X�g�f�[�^.W�w�l�p�����P��(*1)���Z�b�g�B
        //�@@�@@�@@�ȊO�A�������e.getLimitPrice()���Z�b�g�B
        //�@@[��L�ȊO]�i���b�`�N���C�A���g�N���j
        //�@@�@@�������e.getLimitPrice()���Z�b�g�B
        //�@@(*1�@@W�w�l�p�����P�� == null�̏ꍇ�A�[�����Z�b�g�j
        //�⏕�����F�@@�⏕�����I�u�W�F�N�g
        //�敨OP��������F�@@�敨OP��������I�u�W�F�N�g
        //���ʁF �V�K���������e.getQuantity()
        //�����F
        //  ���N�G�X�g�f�[�^.���敪 = �h���h�̏ꍇSideEnum.BUY
        //  ���N�G�X�g�f�[�^.���敪 = �h���h�̏ꍇSideEnum.SELL
        //is�ԍϒ����F�@@false
        //isSkip���z�`�F�b�N�F�@@false
        l_ifoEstimateDeliveryAmountCalcResult =
            l_optionOrderManagerImpl.calcEstimateDeliveryAmount(
            l_gentradeCommission,
            l_dblPrice,
            l_subAccount,
            l_ifoTradedProductImpl,
            l_dblQuantity,
            l_side,
            false,
            false);

        //���N�G�X�g�f�[�^.�m�F���P��==null
        //W�w�l�p�萔���I�u�W�F�N�g�쐬��Acalc�T�Z��n���()���R�[�����鏈����ǉ��B
        //�萔���I�u�W�F�N�g�𐶐�����B
        WEB3GentradeCommission l_wLimitPriceCommission = new WEB3GentradeCommission();
        WEB3IfoEstimateDeliveryAmountCalcResult l_amountCalcResult = null;
        //���N�G�X�g�f�[�^.����ID==null&&����&&W�w�l�̏ꍇ�A
        if (l_request.orderId == null && l_ifoOpenContractOrderSpec.isBuyToOpenOrder() 
            && WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_ifoOpenContractOrderSpec.getOrderCond()))
        {
            //(*3)W�w�l�p�萔���I�u�W�F�N�g�Ɉȉ��̒ʂ�v���p�e�B���Z�b�g����
            //W�w�l�p�萔��.�����`���l�� = this.get���O�C���`���l��()
            l_wLimitPriceCommission.setOrderChannel(this.getLoginChannel());
            
            //W�w�l�p�萔��.�،���ЃR�[�h = �⏕����.get�،����().getInstitutionCode()
            l_wLimitPriceCommission.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());
            
            //W�w�l�p�萔��.���XID = �⏕����.get����X().getBranchId()
            l_wLimitPriceCommission.setBranchId((l_subAccount).getWeb3GenBranch().getBranchId());
            
            //W�w�l�p�萔��.������ = ������ԊǗ�.get������()
            l_wLimitPriceCommission.setOrderBizDate(new Timestamp(l_datOrderBizDate.getTime()));
            
            //W�w�l�p�萔��.����R�[�h(SONAR) = �h51�F���h
            l_wLimitPriceCommission.setSonarTradedCode(WEB3TransactionTypeSONARDef.OPEN_CONTRACT);
            
            //W�w�l�p�萔��.�萔�����i�R�[�h = �h51�F�����w��OP�h
            l_wLimitPriceCommission.setCommissionProductCode(WEB3CommisionProductCodeDef.INDEX_OP);
            
            //W�w�l�p�萔��.�ٍϋ敪 = �h00�F���̑��h        
            l_wLimitPriceCommission.setPayType(WEB3PayTypeDef.OTHER);
            
            //W�w�l�p�萔��.is�w�l���Z�b�g����B
            if (l_ifoOpenContractOrderSpec.getWLimitPriceChange() != 0)
            {
                l_wLimitPriceCommission.setIsLimitPrice(true);
            }
            else
            {
                l_wLimitPriceCommission.setIsLimitPrice(false);
            }
            
            //W�w�l�p�萔��.�����Y�����R�[�h = �敨OP����.get�����Y�����R�[�h()
            l_wLimitPriceCommission.setUnderlyingProductCode(
                l_ifoProductImpl.getUnderlyingProductCode());
        
            //W�w�l�p�萔��.���� = �V�K���������e.getQuantity()
            l_wLimitPriceCommission.setQuantity(l_dblQuantity);
            
            //W�w�l�������̊T�Z��n������v�Z����B 
            //[calc�T�Z��n���()�Ɏw�肷�����] 
            //�萔���F�@@W�w�l�p�萔���I�u�W�F�N�g 
            //�v�Z�P���F�@@�V�K���������e.(W�w�l)�����w�l 
            //�⏕�����F�@@�⏕�����I�u�W�F�N�g 
            //�敨OP��������F�@@�敨OP��������I�u�W�F�N�g 
            //���ʁF �V�K���������e.getQuantity() 
            //�����F  
            //�@@���N�G�X�g�f�[�^.���敪 = �h���h�̏ꍇSideEnum.BUY 
            //�@@���N�G�X�g�f�[�^.���敪 = �h���h�̏ꍇSideEnum.SELL 
            //is�ԍϒ����F�@@false 
            //isSkip���z�`�F�b�N�F�@@false                 
            l_amountCalcResult =
                l_optionOrderManagerImpl.calcEstimateDeliveryAmount(
                l_wLimitPriceCommission,
                l_ifoOpenContractOrderSpec.getWLimitPriceChange(),
                l_subAccount,
                l_ifoTradedProductImpl,
                l_dblQuantity,
                l_side,
                false,
                false);
            
        }
        
        //(*4)�C���^�Z�v�^�I�u�W�F�N�g�̃v���p�e�B�Ɉȉ��̒l���Z�b�g����B
        //�C���^�Z�v�^�𐶐�����B
        //[�R���X�g���N�^�̈���] 
        //�V�K���������e�F�@@create�V�K���������e()�Ő��������������e�B 
        WEB3IfoOpenContractUpdateInterceptor l_ifoOpenContractUpdateInterceptor = 
            new WEB3IfoOpenContractUpdateInterceptor(l_ifoOpenContractOrderSpec);
        
        //�m�T�Z��n����Ǝ萔���̐ݒ�n
        //(*)���N�G�X�g�f�[�^.����ID==null�A���A�����A���AW�w�l�����̏ꍇ�A�ȉ�������s���B
        //�߂�l(*1)�Ɩ߂�l(*2)�̍S������������r���āA
        //��r���ʂ̍����ق��̖߂�l�̊T�Z��n����v�Z�I�u�W�F�N�g���g�p����B
        //�C���^�Z�v�^.�萔�� = �icalc�T�Z��n���()�����̎萔���I�u�W�F�N�g�j
        if (l_request.orderId == null && l_ifoOpenContractOrderSpec.isBuyToOpenOrder() 
            && WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_ifoOpenContractOrderSpec.getOrderCond()))
        {
           if (l_amountCalcResult.getRestraintTurnover() > l_ifoEstimateDeliveryAmountCalcResult.getRestraintTurnover())
           {
               l_ifoOpenContractUpdateInterceptor.setEstimateDeliveryAmountCalcResult(l_amountCalcResult);
               l_ifoOpenContractUpdateInterceptor.setCommision(l_wLimitPriceCommission);
           }
           else
           {
               l_ifoOpenContractUpdateInterceptor.setEstimateDeliveryAmountCalcResult(l_ifoEstimateDeliveryAmountCalcResult);
               l_ifoOpenContractUpdateInterceptor.setCommision(l_gentradeCommission);
           }
        }
        else
        {
            l_ifoOpenContractUpdateInterceptor.setEstimateDeliveryAmountCalcResult(l_ifoEstimateDeliveryAmountCalcResult);
            l_ifoOpenContractUpdateInterceptor.setCommision(l_gentradeCommission);
        }
        
        //�C���^�Z�v�^.�������� = ���N�G�X�g�f�[�^.���������敪
        l_ifoOpenContractUpdateInterceptor.setOrderCond(l_request.orderCondType);
        
        //�C���^�Z�v�^.�����������Z�q = ���N�G�X�g�f�[�^.�����������Z�q�@@�i*���������敪�ŋt�w�l/W�w�l�𔻒�j
        //�C���^�Z�v�^.�t�w�l��l�^�C�v = ���N�G�X�g�f�[�^.�v���~�A���^�����Y���i�@@�i*���������敪�ŋt�w�l/W�w�l�𔻒�j
        //�C���^�Z�v�^.�t�w�l��l = ���N�G�X�g�f�[�^.���������P���@@�i*���������敪�ŋt�w�l/W�w�l�𔻒�j
        double l_dblStopOrderCondPrice = 0D;
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_dblStopOrderCondPrice = Double.parseDouble(l_request.stopOrderCondPrice);
        }
        
        double l_dblwLimitOrderCondPrice = 0D;
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_dblwLimitOrderCondPrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
        }
        
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_ifoOpenContractUpdateInterceptor.setOrderCondOperator(l_request.stopOrderCondOperator);
            l_ifoOpenContractUpdateInterceptor.setStopOrderBasePriceType(l_request.stopPremium_underlyingAssets);
            l_ifoOpenContractUpdateInterceptor.setStopOrderBasePrice(l_dblStopOrderCondPrice);         
        }
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_ifoOpenContractUpdateInterceptor.setOrderCondOperator(l_request.wlimitOrderCondOperator);
            l_ifoOpenContractUpdateInterceptor.setStopOrderBasePriceType(l_request.wlimitPremium_underlyingAssets);
            l_ifoOpenContractUpdateInterceptor.setStopOrderBasePrice(l_dblwLimitOrderCondPrice);  
        }
      
        //�C���^�Z�v�^.(W�w�l)�����w�l = �V�K���������e.(W�w�l)�����w�l
        l_ifoOpenContractUpdateInterceptor.setWLimitPriceChange(l_ifoOpenContractOrderSpec.getWLimitPriceChange());
        //�C���^�Z�v�^.����敪 = ������ԊǗ�.get����敪()
        l_ifoOpenContractUpdateInterceptor.setSessionType(WEB3GentradeTradingTimeManagement.getSessionType());

        //�@@����]�̓T�[�r�X.validate����]��()���R�[������B
        //�@@[����]
        //�@@�@@�⏕�����@@�F�@@�⏕����
        //�@@�@@�������e�C���^�Z�v�^[]�@@�F�@@�敨OP�V�K���X�V�C���^�Z�v�^��v�f�Ƃ����z��
        //�@@�@@�������e[]�@@�F�@@�V�K���������e��v�f�Ƃ����z��
        //�@@�@@�]�͍X�V�t���O�@@�F�@@true
        Object[] l_orderSpecIntercepter = {l_ifoOpenContractUpdateInterceptor};
        Object[] l_orderSpec = {l_ifoOpenContractOrderSpec};
        this.validateTradingPower(l_subAccount, l_orderSpecIntercepter, l_orderSpec, true);

        //�C���^�Z�v�^���Z�b�g����B
        l_optionOrderManagerImpl.setThreadLocalPersistenceEventInterceptor(l_ifoOpenContractUpdateInterceptor);

        //�V�K�������o�^�������s���B
        //arg2�i�����h�c�j�F 
        //���N�G�X�g�f�[�^.����ID!=null�̏ꍇ�A���N�G�X�g�f�[�^.����ID��ݒ�B 
        //���N�G�X�g�f�[�^.����ID==null�̏ꍇ�AOP�����}�l�[�W��.createNewOrderId()�̖߂�l��ݒ�B 
        long l_lngOrderId = 0;
        if (l_request.orderId != null)
        {
            l_lngOrderId = Long.parseLong(l_request.orderId);
        }
        else
        {
            l_lngOrderId = l_optionOrderManagerImpl.createNewOrderId();
        }

        //OP�V�K�������o�^�������s���B
        //�@@[�w�肷�����]
        //�@@�⏕�����@@�F�@@�⏕�����I�u�W�F�N�g
        //�@@�V�K���������e�@@�F�@@create�V�K���������e()�Ő��������V�K���������e
        //�@@����ID�@@�F�@@���N�G�X�g�f�[�^.����ID != null�̏ꍇ�A���N�G�X�g�f�[�^.����ID
        //�@@�@@�@@�@@�@@�@@�@@���N�G�X�g�f�[�^.����ID == null�̏ꍇ�AOP�����}�l�[�W��.createNewOrderId()�̖߂�l
        //�@@����p�X���[�h�@@�F�@@���N�G�X�g�f�[�^.�Ïؔԍ�
        //�@@�V�K�����N�G�X�g�A�_�v�^�@@�F�@@create���N�G�X�g�A�_�v�^()�Ő�������OP�V�K�����N�G�X�g�A�_�v�^�I�u�W�F�N�g
        //�@@�敨OP�T�Z��n����v�Z���ʁ@@�F�@@calc�T�Z��n���()�̖߂�l
        this.submitOpenContractOrder(
            (WEB3GentradeSubAccount)l_subAccount,
            l_ifoOpenContractOrderSpec,
            l_lngOrderId,
            l_request.password,
            l_requestAdapter,
            l_ifoEstimateDeliveryAmountCalcResult);

        //(*5)���X�|���X�f�[�^�Ɉȉ��̒ʂ�v���p�e�B���Z�b�g����B
        WEB3OptionsOpenMarginCompleteResponse l_response =
            (WEB3OptionsOpenMarginCompleteResponse) l_request.createResponse();

        //���X�|���X.�X�V���� = ���ݓ���(GtlUtils.getSystemTimestamp())
        l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
        //���N�G�X�g�f�[�^.����ID!=null�̏ꍇ�A���N�G�X�g�f�[�^.����ID��ݒ�B
        //���N�G�X�g�f�[�^.����ID==null�̏ꍇ�AOP�����}�l�[�W��.createNewOrderId()�̖߂�l��ݒ�B
        l_response.orderActionId = "" + l_lngOrderId;

       log.exiting(STR_METHOD_NAME);
       return l_response;
    }

    /**
     * �����w���I�v�V���������T�[�r�X���������{����B<BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Avalidate()�����܂��́Asubmit����()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 404EF2AC004D
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3OptionsOpenMarginConfirmRequest)
        {
            l_response = this.validateOrder((WEB3OptionsOpenMarginConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3OptionsOpenMarginCompleteRequest)
        {         
            l_response = this.submitOrder((WEB3OptionsOpenMarginCompleteRequest) l_request);
        }
        else
        {
            log.error(STR_METHOD_NAME + "�p�����[�^�^�C�v�s���B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;        
    }
    
    /**
     * (is�w�l)<BR>
     * �w�l�������ǂ������ʂ���B<BR>
     * �w�l�����̏ꍇ�́Atrue�B�ȊO�Afalse��ԋp����B<BR> 
     * ���񃊃b�`�N���C�A���g�i���N�G�X�g�f�[�^.����ID != null�j�̏ꍇ<BR>
     * �@@�̂ݎg�p���邱�ƁB<BR> 
     * <BR>
     * �ȉ��A����.���N�G�X�g�f�[�^�̒l���g�p���Ĕ��ʂ��s���B<BR>
     * <BR>
     * �m�F���P�� == null�̏ꍇ�Atrue��ԋp����B<BR>
     * �ȊO�Afalse��ԋp����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �����w���I�v�V�����V�K�������������N�G�X�g�I�u�W�F�N�g<BR>
     * @@return boolean
     * @@throws WEB3BaseException 
     */
    protected boolean isLimitPrice(
        WEB3OptionsOpenMarginCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =  
            "isLimitPrice(WEB3OptionsOpenMarginCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null )
        {
            log.debug("���N�G�X�g�f�[�^ = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "���N�G�X�g�f�[�^ = null�B");
        }

        if (l_request.checkPrice == null)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (validate����]��)<BR>
     * �؋����E�]�͂̃`�F�b�N���s���B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�iOP�V�K���T�[�r�X�jvalidate����]�́v���Q�ƁB<BR>
     * =============================================== <BR>
     * �V�[�P���X�} : �iOP�V�K���T�[�r�X�jvalidate����]�� <BR>
     * ��̈ʒu     : ����]�͌���.is����t���O( )==false�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@throw�F����]�̓G���[<BR>
     * class        : WEB3BusinessLayerException<BR>
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

        WEB3TPTradingPowerService l_tradingPowerService =
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        //�@@[����]
        //�@@�⏕�����@@�F�@@����.�⏕����
        //�@@�������e�C���^�Z�v�^[]�@@�F�@@����.�������e�C���^�Z�v�^[]
        //�@@�������e[]�@@�F�@@����.�������e[]
        //�@@������ʁ@@�F
        //�@@�@@�@@����.�������e[0].isBuyToOpenOrder() == true�̏ꍇ�A"OP�V�K����"
        //�@@�@@�@@�ȊO�̏ꍇ�A"OP�V�K����"
        //�@@�]�͍X�V�t���O�@@�F�@@����.�]�͍X�V�t���O
        OrderTypeEnum l_orderTypeEnum = null;
        WEB3IfoOpenContractOrderSpec l_ifoOpenContractOrderSpec =
            (WEB3IfoOpenContractOrderSpec)l_objOrderSpecs[0];
        if (l_ifoOpenContractOrderSpec.isBuyToOpenOrder())
        {
            l_orderTypeEnum = OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN;
        }
        else
        {
            l_orderTypeEnum = OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN;
        }

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
     * �I�v�V�����V�K�������̒����o�^���s���B <BR>
     * <BR>
     * �������̏ڍׂ̓V�[�P���X�}�u�iOP�V�K���T�[�r�X�jsubmit�����v�Q��<BR>
      * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@param l_ifoOpenContractOrderSpec - (�V�K���������e)<BR>
     * �V�K���������e�I�u�W�F�N�g�B<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * ����ID�B<BR>
     * @@param l_strTradingPassword - (����p�X���[�h)<BR>
     * ����p�X���[�h�B<BR>
     * @@param l_requestAdapter - (�V�K�����N�G�X�g�A�_�v�^)<BR>
     * OP�V�K�����N�G�X�g�A�_�v�^�I�u�W�F�N�g�B<BR>
     * @@param l_estimateDeliveryAmountCalcResult - (�敨OP�T�Z��n����v�Z����)<BR>
     * �敨OP�T�Z��n����v�Z���ʁB<BR>
     * @@throws WEB3BaseException
     */
    protected void submitOpenContractOrder(
        WEB3GentradeSubAccount l_subAccount,
        IfoOpenContractOrderSpec l_ifoOpenContractOrderSpec,
        long l_lngOrderId,
        String l_strTradingPassword,
        WEB3OptionOpenContractOrderRequestAdapter l_requestAdapter,
        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOpenContractOrder(WEB3GentradeSubAccount, " +
            "IfoOpenContractOrderSpec, long, String, WEB3OptionOpenContractOrderRequestAdapter, " +
            "WEB3IfoEstimateDeliveryAmountCalcResult)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl =
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();

        //�@@[����]
        //�@@�⏕�����@@�F�@@����.�⏕�����I�u�W�F�N�g
        //�@@�V�K���������e�@@�F�@@����.�V�K���������e
        //�@@�����h�c�@@�F�@@����.����ID
        //�@@����p�X���[�h�@@�F�@@����.����p�X���[�h
        //�@@isSkip�����R���@@�F�@@true�i�Œ�j
        OrderSubmissionResult l_orderResult = 
            l_optionOrderManagerImpl.submitOpenContractOrder(
                l_subAccount,
                l_ifoOpenContractOrderSpec,
                l_lngOrderId,
                l_strTradingPassword,
                true);

        if (l_orderResult.getProcessingResult().isFailedResult())
        {
            log.debug("ProcessingResult() = " + l_orderResult.getProcessingResult());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_orderResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create���N�G�X�g�A�_�v�^)<BR>
     * ���N�G�X�g�A�_�v�^�̃C���X�^���X�𐶐�����B <BR>
     * <BR>
     * �������̏ڍׂ̓V�[�P���X�} <BR>
     * �u�iOP�V�K���T�[�r�X�jvalidate�����^�iOP�V�K���T�[�r�X�jsubmit�����v�Q��<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g�I�u�W�F�N�g�B<BR>
     * @@return WEB3OptionOpenContractOrderRequestAdapter
     * @@throws WEB3BaseException
     */
    protected WEB3OptionOpenContractOrderRequestAdapter createRequestAdapter(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createRequestAdapter(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        //���N�G�X�g�A�_�v�^���쐬����B
        //���N�G�X�g �F ����.���N�G�X�g�f�[�^
        WEB3OptionOpenContractOrderRequestAdapter l_adapter =
            WEB3OptionOpenContractOrderRequestAdapter.create(l_request);

        log.exiting(STR_METHOD_NAME);
        return l_adapter;
    }

    /**
     * (set�P��)<BR>
     * ����������return������B�i�J�������j<BR>
     * @@param l_adapter - (OP�V�K���������N�G�X�g�A�_�v�^)<BR>
     * OP�V�K���������N�G�X�g�A�_�v�^�I�u�W�F�N�g�B<BR>
     * @@param l_response - (���X�|���X)<BR>
     * ���X�|���X�I�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     */
    protected void setPrice(WEB3OptionOpenContractOrderRequestAdapter l_adapter, WEB3GenResponse l_response)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "setPrice(WEB3OptionOpenContractOrderRequestAdapter, WEB3GenResponse)";
        log.entering(STR_METHOD_NAME);

        //����������return����B�i�J�������j
        log.exiting(STR_METHOD_NAME);
        return;
    }
}@
