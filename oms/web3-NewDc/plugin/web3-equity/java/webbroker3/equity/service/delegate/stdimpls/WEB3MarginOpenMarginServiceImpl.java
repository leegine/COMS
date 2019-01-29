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
filename	WEB3MarginOpenMarginServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����V�K���T�[�r�XImpl(WEB3MarginOpenMarginServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/28 ���Ō� (Sinocom) �V�K�쐬 
Revesion History : 2006/11/24 ������ (Sinocom)�@@���f��No.1001,No.1003,No.1025,No.1079
Revesion History : 2006/12/26 �����F (���u) ���f�� 1091
Revesion History : 2007/01/08 �đo�g (���u) ���f�� 1097
Revesion History : 2007/01/17 ������@@(���u)���f��No.1107
Revesion History : 2007/01/24 ������@@(���u)���f��No.1112
Revesion History : 2007/06/13 �����q�@@(���u)���f��No.1169
Revesion History : 2007/08/08 ���g(���u) �d�l�ύX���f��1192
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RepaymentDivDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityEstimatedContractPrice;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3MarginClientRequestService;
import webbroker3.equity.WEB3MarginNewOrderValidationResult;
import webbroker3.equity.WEB3MarginOpenContractOrderSpec;
import webbroker3.equity.WEB3MarginOpenMarginUpdateInterceptor;
import webbroker3.equity.define.WEB3MarginTradeTypeDef;
import webbroker3.equity.message.WEB3MarginCommissionInfoUnit;
import webbroker3.equity.message.WEB3MarginOpenMarginCompleteRequest;
import webbroker3.equity.message.WEB3MarginOpenMarginCompleteResponse;
import webbroker3.equity.message.WEB3MarginOpenMarginConfirmRequest;
import webbroker3.equity.message.WEB3MarginOpenMarginConfirmResponse;
import webbroker3.equity.service.delegate.WEB3MarginOpenMarginRequestAdapter;
import webbroker3.equity.service.delegate.WEB3MarginOpenMarginService;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.gentrade.WEB3GentradeBranchMarketRepayDealtCond;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondRow;

/** 
 * �i�M�p����V�K���T�[�r�XImpl�j�B<BR>
 * <BR>
 * �M�p����V�K���T�[�r�X�����N���X
 * @@author ���Ō�
 * @@version 1.0
 */
public class WEB3MarginOpenMarginServiceImpl extends WEB3MarginClientRequestService implements WEB3MarginOpenMarginService 
{

    /**
     * (���O�o�̓��[�e�B���e�B)�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginOpenMarginServiceImpl.class);
            
    /**
     * (�R���X�g���N�^)�B<BR>
     * @@roseuid 4140066F004D
     */
    public WEB3MarginOpenMarginServiceImpl() 
    {
    }
    
    /**
     * (execute)�B<BR>
     * <BR>
     * �M�p����V�K���T�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Avalidate����()���\�b�h�A<BR>
     * submit����()���\�b�h�̂����ꂩ���R�[������B<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40555CB4024B
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = getClass().getName() + "." + "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3MarginOpenMarginConfirmRequest)
        {
            l_response = this.validateOrder((WEB3MarginOpenMarginConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3MarginOpenMarginCompleteRequest)
        {
            l_response = this.submitOrder((WEB3MarginOpenMarginCompleteRequest) l_request);
        }
        else
        {
            log.error(STR_METHOD_NAME + "�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, STR_METHOD_NAME);
        }
        return l_response;
    }
    
    /**
     * (validate����)�B<BR>
     * <BR>
     * �M�p����V�K�������R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�M�p����V�K���T�[�r�X�jvalidate�����v�Q�ƁB<BR>
     * <BR>
     *  ��������I�u�W�F�N�g���擾�ł��Ȃ��ꍇ�́A<BR>
     * �u�w������͎w��s��ł̎戵�s�v�̗�O��throw<BR>
     *  class    : WEB3BusinessLayerException<BR>
     *  tag      : BUSINESS_ERROR_00638<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �M�p����V�K�������m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3MarginOpenMarginConfirmResponse
     * @@roseuid 4055636B0345
     */
    protected WEB3MarginOpenMarginConfirmResponse validateOrder(WEB3MarginOpenMarginConfirmRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrder";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        //���N�G�X�g.validate()
        l_request.validate();
        
        //�⏕�������擾
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //�㗝���͎҂��擾
        Trader l_trader = this.getTrader();
        
        //�M�p����V�K�����N�G�X�g�A�_�v�^ �𐶐�
        WEB3MarginOpenMarginRequestAdapter l_openMarginRequestAdapter = this.createRequestAdapter(l_request);
        
        //is�����F�@@�M�p����V�K�����N�G�X�g�A�_�v�^.is����( ) 
        boolean l_blnIsBuy = l_openMarginRequestAdapter.isLong();
        
        //�����R�[�h�F�@@���N�G�X�g.�����R�[�h 
        String l_strProductCode = l_request.productCode;

        //���ʁF�@@���N�G�X�g.�������� 
        double l_dblQuantity = Double.parseDouble(l_request.orderQuantity);

        // get�s��R�[�h( )
        l_request.marketCode = l_openMarginRequestAdapter.getMarketCode();

        //�s��R�[�h�F�@@���N�G�X�g.�s��R�[�h
        String l_strMarketCode = l_request.marketCode;

        // reset�s��R�[�h(�s��R�[�h : String)
        WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);

        //�w�l�F�@@get�P��()�̖߂�l
        double l_dblLimitPrice = l_openMarginRequestAdapter.getPrice();

        //���s�����F�@@�M�p����V�K�����N�G�X�g�A�_�v�^.get���s����( ) 
        EqTypeExecutionConditionType l_strExecutionCondition = l_openMarginRequestAdapter.getExecutionCondition();
        
        //�����������F�@@�M�p����V�K�����N�G�X�g�A�_�v�^.get�����L������()�̖߂�l
        //���h��������h�����̏ꍇ�Anull���Z�b�g�����B
        Date l_datExpirationDate = null;
        if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_request.expirationDateType))
        {
            l_datExpirationDate = null;
        }
        else if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_request.expirationDateType))
        {
            l_datExpirationDate = l_openMarginRequestAdapter.getExpirationDate();
        }
        //�ŋ敪�F�@@�M�p����V�K�����N�G�X�g�A�_�v�^.get�ŋ敪( ) 
        TaxTypeEnum l_taxType = l_openMarginRequestAdapter.getTaxType();

        //get�i�v�w�l�j���s����( )
        //�i�v�w�l�j���s�������擾����B
        //�@@�@@���N�G�X�g.���������敪�� �i�h�w��Ȃ��h�A�h�t�w�l�h�j�̏ꍇ�́Anull���Z�b�g�B
        //�@@���N�G�X�g.���������敪���hW�w�l�h�̏ꍇ�́A
        //�@@�@@�M�p����V�K�����N�G�X�g�A�_�v�^.get�i�v�w�l�j���s�����̖߂�l���Z�b�g�B
        EqTypeExecutionConditionType l_wLimitExecCondType = null;
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_request.orderCondType)
            || WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_wLimitExecCondType = null;
        }
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_wLimitExecCondType = l_openMarginRequestAdapter.getWLimitExecCondType();
        }

        //���������F�@@���N�G�X�g.���������敪 
        String l_strOrderCondType = l_request.orderCondType;
        
        //�����������Z�q�F 
        //���N�G�X�g.���������敪���h�w��Ȃ��h�̏ꍇ�́A0�B 
        //���N�G�X�g.���������敪���h�t�w�l�h�̏ꍇ�́A���N�G�X�g.�t�w�l�p�����������Z�q�B 
        //���N�G�X�g.���������敪���hW�w�l�h�̏ꍇ�́A���N�G�X�g.W�w�l�p�����������Z�q�B 
        //�t�w�l��l�F 
        //���N�G�X�g.���������敪���h�w��Ȃ��h�̏ꍇ�́A0�B 
        //���N�G�X�g.���������敪���h�t�w�l�h�̏ꍇ�́A���N�G�X�g.�t�w�l�p���������P���B 
        //���N�G�X�g.���������敪���hW�w�l�h�̏ꍇ�́A���N�G�X�g.W�w�l�p���������P���B
        //�iW�w�l�j�����w�l�F 
        //���N�G�X�g.���������敪���i�h�w��Ȃ��h�A�h�t�w�l�h�j�̏ꍇ�́A0�B 
        //���N�G�X�g.���������敪���hW�w�l�h�̏ꍇ�́A���N�G�X�g.W�w�l�p�����P��(*1)�B 
        //(*1)null�̏ꍇ�́A0���Z�b�g�B 
        String l_strOrderCondOperator = null;
        double l_dblStopBasePrice = 0D;
        double l_dblWLimitChangePrice = 0D;
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_request.orderCondType))
        {
            l_strOrderCondOperator = "0";
            l_dblStopBasePrice = 0D;
            l_dblWLimitChangePrice = 0D;
        }
        else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_strOrderCondOperator = l_request.stopOrderCondOperator;
            l_dblStopBasePrice = Double.parseDouble(l_request.stopOrderCondPrice);
            l_dblWLimitChangePrice = 0D;
        }
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_strOrderCondOperator = l_request.wlimitOrderCondOperator;
            l_dblStopBasePrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
            if (WEB3StringTypeUtility.isNumber(l_request.wLimitPrice))
            {
                l_dblWLimitChangePrice = Double.parseDouble(l_request.wLimitPrice);                
            }
        }
        
        //�ٍϋ敪�F�@@���N�G�X�g.�ٍ�.�ٍϋ敪 
        String l_strRepaymentDiv = l_request.repayment.repaymentDiv;
        
        //�ٍϊ����l�F�@@���N�G�X�g.�ٍ�.�ٍϊ���         
        double l_dblRepaymentNum = 0D;
        if (WEB3StringTypeUtility.isNumber(l_request.repayment.repaymentTimeLimit))
        {
            l_dblRepaymentNum = Double.parseDouble(l_request.repayment.repaymentTimeLimit);
        }
        
        //���񒍕��̒����P��ID�F 
        //���N�G�X�g.���������敪���h��������h�̏ꍇ�́Anull���Z�b�g�B 
        //���N�G�X�g.���������敪���h�o����܂Œ����h�̏ꍇ�́A0���Z�b�g�B
        Long l_lngFirstOrderUnitId = null;;
        if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_request.expirationDateType))
        {
            l_lngFirstOrderUnitId = new Long(0);
        }
        else if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_request.expirationDateType))
        {
            l_lngFirstOrderUnitId = null;
        }

        //create�V�K���������e
        WEB3MarginOpenContractOrderSpec l_openContractOrderSpec = 
            WEB3MarginOpenContractOrderSpec.createOpenMarginOrderSpec(
                l_trader, 
                l_blnIsBuy, 
                l_strProductCode, 
                l_strMarketCode, 
                l_dblQuantity, 
                l_dblLimitPrice,
                l_strExecutionCondition, 
                l_datExpirationDate, 
                l_taxType, 
                l_request.priceCondType,
                l_strOrderCondType, 
                l_strOrderCondOperator, 
                l_dblStopBasePrice, 
                l_dblWLimitChangePrice, 
                l_strRepaymentDiv, 
                l_dblRepaymentNum, 
                l_lngFirstOrderUnitId,
                l_wLimitExecCondType);
        
        //create�萔��()
        WEB3EquityBizLogicProvider l_bizLogicProvider =
            (WEB3EquityBizLogicProvider)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getBizLogicProvider();
		//        �⏕�����F�@@this.get�⏕����( ) 
		//        �s��R�[�h�F�@@���N�G�X�g.�s��R�[�h 
		//        �������F�@@�m�F���ivalidate�����j�̏ꍇ�A������ԊǗ�.get������(void)�B 
		//�@@�@@�@@        �������isubmit�����j�̏ꍇ�A���N�G�X�g.�m�F���������B 
		//        �����`���l���F�@@this.get���O�C���`���l��( ) 
		//        �M�p����敪�F�@@���N�G�X�g.�ٍ�.�ٍϋ敪 
		//        �ٍϊ����l�F�@@���N�G�X�g.�ٍ�.�ٍϊ��� 
		//        �����J�e�S���F�@@OrderCategEnum.�h�V�K�������h�iOPEN_MARGIN�j

        WEB3GentradeCommission l_commission = l_bizLogicProvider.createCommission(
            l_subAccount, 
            l_strMarketCode, 
            WEB3GentradeTradingTimeManagement.getOrderBizDate(), 
            this.getLoginChannel(), 
            l_strRepaymentDiv, 
            l_dblRepaymentNum, 
            OrderCategEnum.OPEN_MARGIN);
            
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
        
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        
        //validate�����R�[�h�i�M�p�j�F������~�����ǂ����̃`�F�b�N
        //�i�������\������ւ̑Ή��F�����w�l�`�F�b�N���O�Ɏ��s�v�j
		WEB3EquityProduct l_product =
            (WEB3EquityProduct)l_orderManager.validateProductCode(
            l_strProductCode, l_subAccount.getInstitution().getInstitutionCode(), l_strRepaymentDiv);
        
        //get�������
        //�،���ЁF�@@�⏕����.�،����ID�̏،���ЃI�u�W�F�N�g 
        //�����R�[�h�F�@@���N�G�X�g.�����R�[�h 
        //�s��R�[�h�F�@@���N�G�X�g.�s��R�[�h
        WEB3EquityTradedProduct l_tradedProduct = null;
        try
        {
            l_tradedProduct =
                (WEB3EquityTradedProduct)l_productManager.getTradedProduct(
                l_subAccount.getInstitution(), l_strProductCode, l_strMarketCode);    
        }
        catch (NotFoundException l_nfex)
        {
            //��������I�u�W�F�N�g���擾�ł��Ȃ��ꍇ�́A
            //�u�w������͎w��s��ł̎戵�s�v�̗�O��throw
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00638, STR_METHOD_NAME);
        }
        
        //calc�����������
        //�����͈ȉ��̒ʂ�ɐݒ肷��B
        //
        //�萔�� : �쐬�����萔���I�u�W�F�N�g
        //�w�l : �V�K���������e.getLimitPrice()
        //�iW�w�l�j�����w�l :�@@�V�K���������e.get�iW�w�l�j�����w�l()
        //�t�w�l��l : �V�K���������e.get�t�w�l��l()
        //���s���� : �V�K���������e.getExecConditionType()
        //�iW�w�l�j���s���� : �V�K���������e.get�iW�w�l�j���s����()
        //�l�i���� : �V�K���������e.get�l�i����()
        //�������� : �V�K���������e.get��������()
        //�m�F���擾���� :
        //�@@�m�F�̏ꍇ�Anull�i�Œ�j
        //�@@�����̏ꍇ�A���N�G�X�g.�m�F���P��
        //is�X�g�b�v�����L�� : false�i�Œ�j
        //is���� :�@@�M�p����V�K�����N�G�X�g�A�_�v�^.is����()
        //�⏕���� : this.get�⏕����()
        //������� : get�������()
        //���� : ���N�G�X�g.��������
        //��萔�� : 0�Œ�
        //���v�����z : 0�Œ�
        //isSkip���z�`�F�b�N : false�i�X�L�b�v���Ȃ��j�Œ�
        boolean l_blnIsSale = l_openMarginRequestAdapter.isShort();
        
        WEB3EquityEstimatedContractPrice l_dblContractAmountAtOrder =
            l_orderManager.calcContractAmountAtOrder(
                l_commission,
                l_openContractOrderSpec.getLimitPrice(),
                l_openContractOrderSpec.getWLimitPrice(),
                l_openContractOrderSpec.getStopOrderPrice(),
                l_openContractOrderSpec.getExecConditionType(),
                l_openContractOrderSpec.getWlimitExecCondType(),
                l_openContractOrderSpec.getPriceConditionType(),
                l_openContractOrderSpec.getOrderConditionType(),
                null,
                false,
                l_blnIsSale,
                l_subAccount,
                l_tradedProduct,
                l_dblQuantity,
                0,
                0,
                false);

        //set�v�Z�P��
        //�v�Z�P�����Z�b�g����B
        //�����͈ȉ��̒ʂ�ɃZ�b�g����B
        //�v�Z�P���F�@@calc�����������( )�̖߂�l�I�u�W�F�N�g.get�v�Z�P��( )
        l_openContractOrderSpec.setCalcUnitPrice(
            l_dblContractAmountAtOrder.getCalcUnitPrice());

        //set�����
        //�����͈ȉ��̒ʂ�ɐݒ肷��B
        //������F�@@calc�����������( )�̖߂�l�I�u�W�F�N�g.get�T�Z�����( )
        l_openContractOrderSpec.setContractAmount(
            l_dblContractAmountAtOrder.getEstimatedContractPrice());

        //validate�V�K������
        WEB3MarginNewOrderValidationResult l_newOrderValidationResult = 
            (WEB3MarginNewOrderValidationResult) l_orderManager.validateOpenContractOrder(l_subAccount, l_openContractOrderSpec);

        //throw�����R�����ʃG���[���(OrderValidationResult, �،����, String)
        //�����R�����ʁF�@@validate�V�K������()�̖߂�l
        //�،���ЁF�@@�⏕����.getInstitution()
        //�����R�[�h�F�@@�M�p�V�K���������e.getProductCode()
        l_orderManager.throwOrderValidationResultErrorInfo(
            l_newOrderValidationResult,
            l_subAccount.getInstitution(),
            l_openContractOrderSpec.getProductCode());

        //validate����]��
        WEB3TPTradingPowerResult l_tpResult =
            this.validateTradingPower(
                l_subAccount,
                l_openContractOrderSpec,
                false,
                l_commission,
                l_newOrderValidationResult);
        
        WEB3MarginOpenMarginConfirmResponse l_openMarginConfirmResponse =
            (WEB3MarginOpenMarginConfirmResponse)l_request.createResponse();
        
        //set�P��(�M�p����V�K�����N�G�X�g�A�_�v�^, WEB3GenResponse)
        //�����͈ȉ��̒ʂ�ɐݒ肷��B
        //�M�p����V�K�����N�G�X�g�A�_�v�^�F�@@�������������I�u�W�F�N�g
        //���X�|���X�F�����������X�|���X
        setPrice(l_openMarginRequestAdapter, l_openMarginConfirmResponse);

        try
        {
            //���X�|���X.�m�F���������F�@@������ԊǗ�.get������(void)�̖߂�l
            l_openMarginConfirmResponse.checkDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
            //���X�|���X.�T�Z��n����F�@@�M�p�V�K���������e.get�����( )�̖߂�l
            l_openMarginConfirmResponse.estimatedPrice =
                WEB3StringTypeUtility.formatNumber(l_openContractOrderSpec.getContractAmount());
        
            //���X�|���X.����I���x���s��R�[�h�ꗗ�F�@@������ԊǗ�.get�s��ǌx���s��( )�̖߂�l�z��
            String[] l_strTradeCloseMarkets =
                WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
                l_subAccount.getWeb3GenBranch(), ProductTypeEnum.EQUITY, l_strRepaymentDiv);
            l_openMarginConfirmResponse.messageSuspension = l_strTradeCloseMarkets;
        
            //���X�|���X.�������F�@@�g���v���_�N�g�}�l�[�W��.getProduct(�⏕����.�،���ЃR�[�h, ���N�G�X�g.�����R�[�h)
            //�Ŏ擾������������.������
            EqtypeProductRow l_eqtypeProductRow =
                (EqtypeProductRow)l_productManager.getProduct(
                l_subAccount.getInstitution(), l_strProductCode).getDataSourceObject();
            l_openMarginConfirmResponse.productName = l_eqtypeProductRow.getStandardName();
            
            //calc�ϑ��萔��
            l_bizLogicProvider.calcCommission(l_commission, l_subAccount);
            
            //���X�|���X.�萔�����F�@@(* ��L�ŕҏW�����M�p�萔�����I�u�W�F�N�g)
            WEB3MarginCommissionInfoUnit l_commissionInfoUnit = new WEB3MarginCommissionInfoUnit();
            //�M�p����萔�����.�萔���R�[�X  ���@@�萔��.get�萔���R�[�X�R�[�h
            //�M�p����萔�����.�萔��       ���@@�萔��.get�萔�����z( )
            //�M�p����萔�����.�萔�������    ���@@�����v�Z�T�[�r�X.calc�����( )
            l_commissionInfoUnit.commissionCourse = l_commission.getCommissionCourseDiv();
            l_commissionInfoUnit.commission = WEB3StringTypeUtility.formatNumber(l_commission.getCommission());
            l_commissionInfoUnit.commissionConsumptionTax = WEB3StringTypeUtility.formatNumber(
                l_bizLogicProvider.calcSalesTax(l_commission.getCommission(), l_commission.getOrderBizDate(), l_subAccount));
            
            l_openMarginConfirmResponse.commissionInfo = l_commissionInfoUnit;
            
            //���X�s��ٍϕʁj�戵�����𐶐�
            //�،���ЃR�[�h�F�@@�⏕����.�،���ЃR�[�h 
            //���X�R�[�h�F�@@get�⏕����().getMainAccount(),���X�R�[�h 
            //�s��R�[�h�F�@@���N�G�X�g.�s��R�[�h 
            //�ٍϋ敪�F�@@���N�G�X�g.�ٍ�.�ٍϋ敪 
            //�ٍϊ����l�F�@@���N�G�X�g.�ٍ�.�ٍϊ��� 

            WEB3GentradeBranchMarketRepayDealtCond l_branchMarketRepayDealtCond = 
                new WEB3GentradeBranchMarketRepayDealtCond(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_strMarketCode,
                    l_strRepaymentDiv,
                    l_dblRepaymentNum);
            BranchMarketRepayDealtCondRow l_branchMarketRepayDealtCondRow =
                (BranchMarketRepayDealtCondRow)l_branchMarketRepayDealtCond.getDataSourceObject();  
            
            //���X�|���X.�����F�@@
            //���N�G�X�g.����敪��"�V�K��������"�̏ꍇ�A�i���X�s��ٍϕʁj�戵����.���������B
            //���N�G�X�g.����敪��"�V�K��������"�̏ꍇ�A�i���X�s��ٍϕʁj�戵����.���������B
            String l_strInterestRates = null;
            if (WEB3MarginTradeTypeDef.OPEN_LONG_MARGIN.equals(l_request.tradingType))
            {
                //���N�G�X�g.����敪��"�V�K��������"�̏ꍇ�A�i���X�s��ٍϕʁj�戵����.���������B
                l_strInterestRates = WEB3StringTypeUtility.formatNumber(l_branchMarketRepayDealtCondRow.getBuyInterestRate());
            }
            else if (WEB3MarginTradeTypeDef.OPEN_SHORT_MARGIN.equals(l_request.tradingType))
            {
                //���N�G�X�g.����敪��"�V�K��������"�̏ꍇ�A�i���X�s��ٍϕʁj�戵����.���������B
                l_strInterestRates = WEB3StringTypeUtility.formatNumber(l_branchMarketRepayDealtCondRow.getSellInterestRate());
            }
            l_openMarginConfirmResponse.interestRates = l_strInterestRates;
            
            //���X�|���X.���Z����(**2)�F�@@�i���X�s��ٍϕʁj�戵����.�������o��Z����
            //(**2)���N�G�X�g.�ٍ�.�ٍϋ敪��"��ʐM�p"�A�����N�G�X�g.�ٍ�.�ٍϊ����l��"9999999"�i��ALL9�j�̏ꍇ�̂݃Z�b�g�B
            //�ȊO�Anull���Z�b�g�B
            String l_strClearUpTerm = null;
            
            if (WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(l_strRepaymentDiv)
                && 9999999 == l_dblRepaymentNum)
            {
                l_strClearUpTerm =WEB3StringTypeUtility.formatNumber(l_branchMarketRepayDealtCondRow.getContLiquidateTerm());
            }
            l_openMarginConfirmResponse.clearUpTerm = l_strClearUpTerm;
            
            //���X�|���X.�m�F���P���F�@@calc�����������()�̖߂�l.get�m�F���擾�P��()
            l_openMarginConfirmResponse.checkPrice =
                l_dblContractAmountAtOrder.getCheckGetCurrentPrice();
            
            //���X�|���X.����ID�F�@@�g�����������}�l�[�W��.createNewOrderId( )�̖߂�l
            l_openMarginConfirmResponse.orderId = "" + l_orderManager.createNewOrderId();
            
            //is�C���T�C�_�[�x���\��
            boolean l_boolIsInsider = l_orderManager.isInsiderMessageSuspension(l_subAccount, l_eqtypeProductRow.getProductId());

            //���X�|���X.�C���T�C�_�[�x���\���t���O
            l_openMarginConfirmResponse.insiderWarningFlag = l_boolIsInsider; 

            if (l_tpResult != null)
            {
                l_openMarginConfirmResponse.attentionObjectionType =
                    l_tpResult.getAttentionObjectionType();
            }

            //���X�|���X.�s��R�[�h
            l_openMarginConfirmResponse.marketCode = l_request.marketCode;

            //���X�|���X.�����L������
            l_openMarginConfirmResponse.expirationDate = l_openMarginRequestAdapter.getExpirationDate();

            log.exiting(STR_METHOD_NAME);
            return l_openMarginConfirmResponse;
            
        }
        catch (NotFoundException l_nfex)
        {            
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, STR_METHOD_NAME);
        }
        
    }
    
    /**
     * (submit����)�B<BR>
     * <BR>
     * �M�p����V�K�������o�^���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�M�p����V�K���T�[�r�X�jsubmit�����v�Q�ƁB<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �M�p����V�K�������������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3MarginOpenMarginCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 405563760103
     */
    protected WEB3MarginOpenMarginCompleteResponse submitOrder(WEB3MarginOpenMarginCompleteRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOrder";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        //���N�G�X�g.validate()
        l_request.validate();
        
        //�⏕�������擾
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //�㗝���͎҂��擾
        Trader l_trader = this.getTrader();
        
        //�M�p����V�K�����N�G�X�g�A�_�v�^ �𐶐�
        WEB3MarginOpenMarginRequestAdapter l_openMarginRequestAdapter = this.createRequestAdapter(l_request);
                
        //is�����F�@@�M�p����V�K�����N�G�X�g�A�_�v�^.is����( ) 
        boolean l_blnIsBuy = l_openMarginRequestAdapter.isLong();
        
        //�����R�[�h�F�@@���N�G�X�g.�����R�[�h 
        String l_strProductCode = l_request.productCode;

        //���ʁF�@@���N�G�X�g.�������� 
        double l_dblQuantity = Double.parseDouble(l_request.orderQuantity);

        // get�s��R�[�h( )
        l_request.marketCode = l_openMarginRequestAdapter.getMarketCode();

        //�s��R�[�h�F�@@���N�G�X�g.�s��R�[�h
        String l_strMarketCode = l_request.marketCode;

        // reset�s��R�[�h(�s��R�[�h : String)
        WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);

        //�w�l�F�@@get�P��()�̖߂�l
        double l_dblLimitPrice = l_openMarginRequestAdapter.getPrice();
        
        //get������(�m�F�������� : Date)
        if (l_request.checkDate == null)
        {
            l_request.checkDate =
                WEB3GentradeTradingTimeManagement.getOrderBizDate();
        }
        Date l_orderBizDate =
            WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);

        //���s�����F�@@�M�p����V�K�����N�G�X�g�A�_�v�^.get���s����( ) 
        EqTypeExecutionConditionType l_strExecutionCondition = l_openMarginRequestAdapter.getExecutionCondition();
        
        //�����������F�@@�M�p����V�K�����N�G�X�g�A�_�v�^.get�����L������()�̖߂�l
        //���h��������h�����̏ꍇ�Anull���Z�b�g�����B
        Date l_datExpirationDate = null;
        if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_request.expirationDateType))
        {
            l_datExpirationDate = null;
        }
        else if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_request.expirationDateType))
        {
            l_datExpirationDate = l_openMarginRequestAdapter.getExpirationDate();
        }
        //�ŋ敪�F�@@�M�p����V�K�����N�G�X�g�A�_�v�^.get�ŋ敪( ) 
        TaxTypeEnum l_taxType = l_openMarginRequestAdapter.getTaxType();

        //get�i�v�w�l�j���s����( )
        //�i�v�w�l�j���s�������擾����B
        //�@@�@@���N�G�X�g.���������敪�� �i�h�w��Ȃ��h�A�h�t�w�l�h�j�̏ꍇ�́Anull���Z�b�g�B
        //�@@���N�G�X�g.���������敪���hW�w�l�h�̏ꍇ�́A
        //�@@�@@�M�p����V�K�����N�G�X�g�A�_�v�^.get�i�v�w�l�j���s�����̖߂�l���Z�b�g�B
        EqTypeExecutionConditionType l_wLimitExecCondType = null;
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_request.orderCondType)
            || WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_wLimitExecCondType = null;
        }
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_wLimitExecCondType = l_openMarginRequestAdapter.getWLimitExecCondType();
        }

        //���������F�@@���N�G�X�g.���������敪 
        String l_strOrderCondType = l_request.orderCondType;
        
        //�����������Z�q�F 
        //���N�G�X�g.���������敪���h�w��Ȃ��h�̏ꍇ�́A0�B 
        //���N�G�X�g.���������敪���h�t�w�l�h�̏ꍇ�́A���N�G�X�g.�t�w�l�p�����������Z�q�B 
        //���N�G�X�g.���������敪���hW�w�l�h�̏ꍇ�́A���N�G�X�g.W�w�l�p�����������Z�q�B 
        //�t�w�l��l�F 
        //���N�G�X�g.���������敪���h�w��Ȃ��h�̏ꍇ�́A0�B 
        //���N�G�X�g.���������敪���h�t�w�l�h�̏ꍇ�́A���N�G�X�g.�t�w�l�p���������P���B 
        //���N�G�X�g.���������敪���hW�w�l�h�̏ꍇ�́A���N�G�X�g.W�w�l�p���������P���B
        //�iW�w�l�j�����w�l�F 
        //���N�G�X�g.���������敪���i�h�w��Ȃ��h�A�h�t�w�l�h�j�̏ꍇ�́A0�B 
        //���N�G�X�g.���������敪���hW�w�l�h�̏ꍇ�́A���N�G�X�g.W�w�l�p�����P��(*1)�B 
        String l_strOrderCondOperator = null;
        double l_dblStopBasePrice = 0D;
        double l_dblWLimitChangePrice = 0D;
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_request.orderCondType))
        {
            l_strOrderCondOperator = "0";
            l_dblStopBasePrice = 0D;
            l_dblWLimitChangePrice = 0D;
        }
        else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_strOrderCondOperator = l_request.stopOrderCondOperator;
            l_dblStopBasePrice = Double.parseDouble(l_request.stopOrderCondPrice);
            l_dblWLimitChangePrice = 0D;
        }
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_strOrderCondOperator = l_request.wlimitOrderCondOperator;
            l_dblStopBasePrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
            if (WEB3StringTypeUtility.isNumber(l_request.wLimitPrice))
            {
                l_dblWLimitChangePrice = Double.parseDouble(l_request.wLimitPrice);                
            }
        }
        
        //�ٍϋ敪�F�@@���N�G�X�g.�ٍ�.�ٍϋ敪 
        String l_strRepaymentDiv = l_request.repayment.repaymentDiv;
        
        //�ٍϊ����l�F�@@���N�G�X�g.�ٍ�.�ٍϊ���         
        double l_dblRepaymentNum = 0D;
        if (WEB3StringTypeUtility.isNumber(l_request.repayment.repaymentTimeLimit))
        {
            l_dblRepaymentNum = Double.parseDouble(l_request.repayment.repaymentTimeLimit);
        }
        
        //���񒍕��̒����P��ID�F 
        //���N�G�X�g.���������敪���h��������h�̏ꍇ�́Anull���Z�b�g�B 
        //���N�G�X�g.���������敪���h�o����܂Œ����h�̏ꍇ�́A0���Z�b�g�B
        Long l_lngFirstOrderUnitId = null;;
        if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_request.expirationDateType))
        {
            l_lngFirstOrderUnitId = new Long(0);
        }
        else if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_request.expirationDateType))
        {
            l_lngFirstOrderUnitId = null;
        }
        
        //create�V�K���������e
        WEB3MarginOpenContractOrderSpec l_openContractOrderSpec = 
            WEB3MarginOpenContractOrderSpec.createOpenMarginOrderSpec(
                l_trader, 
                l_blnIsBuy, 
                l_strProductCode, 
                l_strMarketCode, 
                l_dblQuantity, 
                l_dblLimitPrice, 
                l_strExecutionCondition, 
                l_datExpirationDate, 
                l_taxType, 
                l_request.priceCondType,
                l_strOrderCondType, 
                l_strOrderCondOperator, 
                l_dblStopBasePrice, 
                l_dblWLimitChangePrice, 
                l_strRepaymentDiv, 
                l_dblRepaymentNum, 
                l_lngFirstOrderUnitId,
                l_wLimitExecCondType);
        
        //create�萔��()
        WEB3EquityBizLogicProvider l_bizLogicProvider =
            (WEB3EquityBizLogicProvider)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getBizLogicProvider();
//        �⏕�����F�@@this.get�⏕����( ) 
//        �s��R�[�h�F�@@���N�G�X�g.�s��R�[�h 
//        �������F�@@�m�F���ivalidate�����j�̏ꍇ�A������ԊǗ�.get������(void)�B 
//�@@�@@�@@        �������isubmit�����j�̏ꍇ�A���N�G�X�g.�m�F���������B 
//        �����`���l���F�@@this.get���O�C���`���l��( ) 
//        �M�p����敪�F�@@���N�G�X�g.�ٍ�.�ٍϋ敪 
//        �ٍϊ����l�F�@@���N�G�X�g.�ٍ�.�ٍϊ��� 
//        �����J�e�S���F�@@OrderCategEnum.�h�V�K�������h�iOPEN_MARGIN�j

        WEB3GentradeCommission l_commission = l_bizLogicProvider.createCommission(
            l_subAccount, 
            l_strMarketCode, 
		    l_orderBizDate, 
            this.getLoginChannel(), 
            l_strRepaymentDiv, 
            l_dblRepaymentNum, 
            OrderCategEnum.OPEN_MARGIN);
            
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
        
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        
        //get�������               
        //�،���ЁF�@@�⏕����.�،����ID�̏،���ЃI�u�W�F�N�g 
        //�����R�[�h�F�@@���N�G�X�g.�����R�[�h 
        //�s��R�[�h�F�@@���N�G�X�g.�s��R�[�h
        WEB3EquityTradedProduct l_tradedProduct = null;
        try
        {
            l_tradedProduct =
                (WEB3EquityTradedProduct)l_productManager.getTradedProduct(
                l_subAccount.getInstitution(), l_strProductCode, l_strMarketCode);    
        }
        catch (NotFoundException l_nfex)
        {
            //��������I�u�W�F�N�g���擾�ł��Ȃ��ꍇ�́A
            //�u�w������͎w��s��ł̎戵�s�v�̗�O��throw
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00638, STR_METHOD_NAME);
        }
        
        OrderTypeEnum l_orderType = null;
        if (l_openMarginRequestAdapter.isLong())
        {
            l_orderType = OrderTypeEnum.MARGIN_LONG;
        }
        else
        {
            l_orderType = OrderTypeEnum.MARGIN_SHORT;
        } 
        
        //calc�����������
        //�����͈ȉ��̒ʂ�ɐݒ肷��B
        //
        //�萔�� : �쐬�����萔���I�u�W�F�N�g
        //�w�l : �V�K���������e.getLimitPrice()
        //�iW�w�l�j�����w�l :�@@�V�K���������e.get�iW�w�l�j�����w�l()
        //�t�w�l��l : �V�K���������e.get�t�w�l��l()
        //���s���� : �V�K���������e.getExecConditionType()
        //�iW�w�l�j���s���� : �V�K���������e.get�iW�w�l�j���s����()
        //�l�i���� : �V�K���������e.get�l�i����()
        //�������� : �V�K���������e.get��������()
        //�m�F���擾���� :
        //�@@�m�F�̏ꍇ�Anull�i�Œ�j
        //�@@�����̏ꍇ�A���N�G�X�g.�m�F���P��
        //is�X�g�b�v�����L�� : false�i�Œ�j
        //is���� :�@@�M�p����V�K�����N�G�X�g�A�_�v�^.is����()
        //�⏕���� : this.get�⏕����()
        //������� : get�������()
        //���� : ���N�G�X�g.��������
        //��萔�� : 0�Œ�
        //���v�����z : 0�Œ�
        //isSkip���z�`�F�b�N : false�i�X�L�b�v���Ȃ��j�Œ�
        boolean l_blnIsSale = l_openMarginRequestAdapter.isShort();
        
        WEB3EquityEstimatedContractPrice l_dblContractAmountAtOrder =
            l_orderManager.calcContractAmountAtOrder(
                l_commission,
                l_openContractOrderSpec.getLimitPrice(),
                l_openContractOrderSpec.getWLimitPrice(),
                l_openContractOrderSpec.getStopOrderPrice(),
                l_openContractOrderSpec.getExecConditionType(),
                l_openContractOrderSpec.getWlimitExecCondType(),
                l_openContractOrderSpec.getPriceConditionType(),
                l_openContractOrderSpec.getOrderConditionType(),
                l_request.checkPrice,
                false,
                l_blnIsSale,
                l_subAccount,
                l_tradedProduct,
                l_dblQuantity,
                0,
                0,
                false);

        //set�v�Z�P��
        //�v�Z�P�����Z�b�g����B
        //�����͈ȉ��̒ʂ�ɃZ�b�g����B
        //�v�Z�P���F�@@calc�����������( )�̖߂�l�I�u�W�F�N�g.get�v�Z�P��( )
        l_openContractOrderSpec.setCalcUnitPrice(
            l_dblContractAmountAtOrder.getCalcUnitPrice());

        //set�����
        //�����͈ȉ��̒ʂ�ɐݒ肷��B
        //������F�@@calc�����������( )�̖߂�l�I�u�W�F�N�g.get�T�Z�����( )
        l_openContractOrderSpec.setContractAmount(
            l_dblContractAmountAtOrder.getEstimatedContractPrice());

        // validate�V�K������
        WEB3MarginNewOrderValidationResult l_newOrderValidationResult = 
            (WEB3MarginNewOrderValidationResult) l_orderManager.validateOpenContractOrder(l_subAccount, l_openContractOrderSpec);

        //throw�����R�����ʃG���[���(OrderValidationResult, �،����, String)
        //�����R�����ʁF�@@validate�V�K������()�̖߂�l
        //�،���ЁF�@@�⏕����.getInstitution()
        //�����R�[�h�F�@@�M�p�V�K���������e.getProductCode()
        l_orderManager.throwOrderValidationResultErrorInfo(
            l_newOrderValidationResult,
            l_subAccount.getInstitution(),
            l_openContractOrderSpec.getProductCode());

        //calc�ϑ��萔��
        l_bizLogicProvider.calcCommission(l_commission, l_subAccount);

        //validate����]��
        this.validateTradingPower(
            l_subAccount,
            l_openContractOrderSpec,
            true,
            l_commission,
            l_newOrderValidationResult);
        
        //submit�V�K������
        if (l_request.orderId == null)
        {
            l_request.orderId =
                String.valueOf(l_orderManager.createNewOrderId());
        }
        this.submitOpenContractOrder(
            l_subAccount,
            l_openContractOrderSpec,
            Long.parseLong(l_request.orderId),
            l_request.password,
            l_openMarginRequestAdapter);
        
        WEB3MarginOpenMarginCompleteResponse l_openMarginCompleteResponse =
            (WEB3MarginOpenMarginCompleteResponse)l_request.createResponse();
        
        //is�C���T�C�_�[�x���\��
        WEB3EquityProduct l_product = null;
        try
        {
            l_product =
                (WEB3EquityProduct)l_productManager.getProduct(
                    l_subAccount.getInstitution(),
                    l_strProductCode);
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        boolean l_boolIsInsider =
            l_orderManager.isInsiderMessageSuspension(
                l_subAccount,
                l_product.getProductId());
        
        //���X�|���X.�X�V����
        l_openMarginCompleteResponse.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
        
        //���X�|���X.���ʔԍ�
        l_openMarginCompleteResponse.orderActionId = l_request.orderId;
        
        //���X�|���X.�C���T�C�_�[�x���\���t���O
        l_openMarginCompleteResponse.insiderWarningFlag = l_boolIsInsider; 
        
        //���X�|���X.�s��R�[�h
        l_openMarginCompleteResponse.marketCode = l_request.marketCode;

        //���X�|���X.�����L������
        l_openMarginCompleteResponse.expirationDate = l_openMarginRequestAdapter.getExpirationDate();

        log.exiting(STR_METHOD_NAME);
        return l_openMarginCompleteResponse;
    }
    
    /**
     * (create���N�G�X�g�A�_�v�^)<BR>
     * ���N�G�X�g�A�_�v�^�̃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �M�p����V�K�����N�G�X�g�A�_�v�^.create(�����̃��N�G�X�g)���R�[������B<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g�f�[�^�B
     * @@return WEB3MarginOpenMarginRequestAdapter
     */
    protected WEB3MarginOpenMarginRequestAdapter createRequestAdapter(WEB3GenRequest l_request)
    {
        final String STR_METHOD_NAME = "createRequestAdapter(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MarginOpenMarginRequestAdapter l_requestAdaptor =
            WEB3MarginOpenMarginRequestAdapter.create(l_request);
        
        log.exiting(STR_METHOD_NAME);
        return l_requestAdaptor;
    }
    
    /**
     * (validate����]��)<BR>
     * ����]�͂��`�F�b�N����B<BR>
     * �V�[�P���X�}�u�i�M�p����V�K���T�[�r�X�jvalidate����]�́v���Q�ƁB<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B
     * @@param l_orderSpec - (�M�p�V�K���������e)<BR>
     * �M�p�V�K���������e�I�u�W�F�N�g�B
     * @@param l_blnUpdateFlg - (�]�͍X�V�t���O)<BR>
     * �]�͍X�V�t���O�B<BR>
     * �ifalse�F�@@�m�F���Atrue�F�@@�������j
     * @@param l_commission - (�萔��)<BR>
     * �萔���I�u�W�F�N�g�B
     * @@param l_validationResult - (�����R������)<BR>
     * �M�p�V�K���V�K���������R�����ʃI�u�W�F�N�g�B
     * @@return WEB3TPTradingPowerResult
     * @@throws WEB3BaseException
     */
    protected WEB3TPTradingPowerResult validateTradingPower(
        WEB3GentradeSubAccount l_subAccount,
        WEB3MarginOpenContractOrderSpec l_orderSpec,
        boolean l_blnUpdateFlg,
        WEB3GentradeCommission l_commission,
        WEB3MarginNewOrderValidationResult l_validationResult)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateTradingPower(WEB3GentradeSubAccount, WEB3MarginOpenContractOrderSpec, boolean, WEB3GentradeCommission, WEB3MarginNewOrderValidationResult)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        
        boolean l_bIsShortSellRegulationTarget =
            l_validationResult.getShortSellingRestraint();
        
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        String l_strOrderRootDiv =
            l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV);     
        WEB3MarginOpenMarginUpdateInterceptor l_interceptor =
            new WEB3MarginOpenMarginUpdateInterceptor(
                l_orderSpec,
                l_commission,
                this.getLoginChannel(),
                l_strOrderRootDiv,
                l_bIsShortSellRegulationTarget);
        
        Object[] l_orderSpecIntercepters = { l_interceptor };
        Object[] l_orderSpecs = { l_orderSpec };
        OrderTypeEnum l_orderType;
        if (l_orderSpec.isLongOrder())
        {
            l_orderType = OrderTypeEnum.MARGIN_LONG;
        }
        else
        {
            l_orderType = OrderTypeEnum.MARGIN_SHORT;
        }
        WEB3TPTradingPowerService l_tradingPowerService
            = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        WEB3TPTradingPowerResult l_tpResult =
            l_tradingPowerService.validateTradingPower(
                l_subAccount,
                l_orderSpecIntercepters,
                l_orderSpecs,
                l_orderType,
                l_blnUpdateFlg);
        if (l_tpResult.isResultFlg() == false)
        {
            l_orderManager.throwTpErrorInfo(l_tpResult, l_orderType);
        }
        
        if (l_blnUpdateFlg)
        {
            l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_tpResult;
    }
    
    /**
     * (submit�V�K������)<BR>
     * �M�p�V�K��������o�^����B<BR>
     * <BR>
     * �g�����������}�l�[�W��.submitOpenContractOrder(<BR>
     * �⏕����, �M�p�V�K�����e, ����ID, ����p�X���[�h, true�i�������R�����X�L�b�v����j)���R�[������B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B
     * @@param l_orderSpec - (�M�p�V�K���������e)<BR>
     * �M�p�V�K���������e�I�u�W�F�N�g�B
     * @@param l_lngOrderId - (����ID)<BR>
     * �\�񒍕��̒���ID�B
     * @@param l_strTradingPassword - (����p�X���[�h)<BR>
     * ����p�X���[�h�B
     * @@param l_requestAdaptor - (�V�K�����N�G�X�g�A�_�v�^)<BR>
     * �M�p����V�K�����N�G�X�g�A�_�v�^�I�u�W�F�N�g�B
     * @@throws WEB3BaseException
     */
    protected void submitOpenContractOrder(
        WEB3GentradeSubAccount l_subAccount,
        WEB3MarginOpenContractOrderSpec l_orderSpec,
        long l_lngOrderId,
        String l_strTradingPassword,
        WEB3MarginOpenMarginRequestAdapter l_requestAdaptor)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOpenContractOrder(WEB3GentradeSubAccount, WEB3MarginOpenContractOrderSpec, long, String, WEB3MarginOpenMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        EqTypeOrderSubmissionResult l_result =
            l_orderManager.submitOpenContractOrder(
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
     * (set�P��)<BR>
     * ���������Ƀ��^�[������B<BR>
     * <BR>
     * @@param l_requestAdapter - (�M�p����V�K�����N�G�X�g�A�_�v�^)<BR>
     * �M�p����V�K�����N�G�X�g�A�_�v�^�B<BR>
     * @@param l_response - (���X�|���X)<BR>
     * ���X�|���X<BR>
     * @@throws WEB3BaseException
     */
    protected void setPrice(
        WEB3MarginOpenMarginRequestAdapter l_requestAdapter,
        WEB3GenResponse l_response)
        throws WEB3BaseException
    {
        
    }
}
@
