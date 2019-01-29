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
filename	WEB3FuturesChangeOpenContractServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�����V�K���T�[�r�X�����N���X(WEB3FuturesChangeOpenContractServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/23 ���� (���u) �V�K�쐬
              001: 2004/9/7 ���� (���u) BUG-381 ��Ή�
              002: 2006/07/31 �ęԍg (���u) �d�l�ύX�@@���f��492
                 : 2006/08/10 �юu�� (���u) �d�l�ύX�@@���f��545
Revesion History : 2007/06/22 ���^�] (���u) �d�l�ύX�@@���f��706 751
Revesion History : 2007/11/20 ��іQ (���u)�d�l�ύX ���f��809
Revesion History : 2007/11/28 ��іQ (���u)Java�\�[�X�i��{�݌v�ƍ����Ă��Ȃ������j009
Revesion History : 2008/03/17 �����F(���u) ���f��855
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoOrderImpl;

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
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3StopBasePriceTypeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3FuturesClientRequestService;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoOpenContractChangeSpec;
import webbroker3.ifo.WEB3IfoOpenContractChangeUpdateInterceptor;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.message.WEB3FuturesOpenMarginChangeCompleteRequest;
import webbroker3.ifo.message.WEB3FuturesOpenMarginChangeCompleteResponse;
import webbroker3.ifo.message.WEB3FuturesOpenMarginChangeConfirmRequest;
import webbroker3.ifo.message.WEB3FuturesOpenMarginChangeConfirmResponse;
import webbroker3.ifo.service.delegate.WEB3FuturesChangeOpenContractService;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationIfoOrderUpdateService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�敨�����V�K���T�[�r�XImpl)<BR>
 * �����w���敨�����V�K���T�[�r�X�����N���X<BR>
 * @@author ����
 * @@version 1.0
 */
public class WEB3FuturesChangeOpenContractServiceImpl extends WEB3FuturesClientRequestService implements WEB3FuturesChangeOpenContractService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesChangeOpenContractServiceImpl.class);
    
    /**
     * @@roseuid 40F7A2D000CB
     */
    public WEB3FuturesChangeOpenContractServiceImpl() 
    {
     
    }
    
    /**
     * �����w���敨�����V�K���T�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Avalidate����()�܂��́A<BR>
     * submit����()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A8A4F30368
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";

        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3FuturesOpenMarginChangeConfirmRequest)
        {
            //���N�G�X�g�f�[�^�̋�ۃf�[�^�^��
            //�u�����w���敨�����V�K���m�F���N�G�X�g�v�̏ꍇ
                l_response = validateOrder(
                    (WEB3FuturesOpenMarginChangeConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3FuturesOpenMarginChangeCompleteRequest)
        {
            //���N�G�X�g�f�[�^�̋�ۃf�[�^�^��
            //�u�����w���敨�����������ڍ׃��N�G�X�g�v�̏ꍇ
            l_response = submitOrder(
                (WEB3FuturesOpenMarginChangeCompleteRequest)l_request);
        }
        else
        {
            log.error("�p�����[�^�^�C�v�s���B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (validate����)<BR>
     * �����w���敨�̒����V�K�������R�����s���B<BR>
     * <BR>
     * �u�i�敨�����V�K���T�[�r�X�jvalidate�����v�Q�ƁB<BR>
     * <BR>
     * =============================================== <BR>
     * �V�[�P���X�} : �i�敨�����V�K���T�[�r�X�jvalidate�����Q <BR>
     * ��̈ʒu     : 1.12 �߂�l�̎���]�͌���.����t���O == false �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@��O���X���[����B <BR>
     * class        : WEB3BusinessLayerException <BR>
     * tag          : BUSINESS_ERROR_01306 <BR>
     * =============================================== <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �����w���敨�����V�K���m�F���N�G�X�g<BR>
     * @@return WEB3FuturesOpenMarginChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A8A4F30387
     */
    protected WEB3FuturesOpenMarginChangeConfirmResponse validateOrder(
        WEB3FuturesOpenMarginChangeConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateOrder(WEB3FuturesOpenMarginChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //1.validate           
            l_request.validate();
            
            //2.������ԊǗ�����get������          
            Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            Date l_datOrderBizDateDay = WEB3DateUtility.toDay(l_datOrderBizDate);
            log.debug("������ԊǗ�����get������: " + l_datOrderBizDate);         
            
            //3.IfoOrderImpl     
            TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.IFO);      
            WEB3FuturesOrderManagerImpl l_orderMgr = 
                (WEB3FuturesOrderManagerImpl) l_tradingModule.getOrderManager();
                
            long l_lngOrderId = Long.parseLong(l_request.id);
            IfoOrderImpl l_ifoOrderImpl = (IfoOrderImpl) l_orderMgr.getOrder(l_lngOrderId); 
            log.debug("l_lngOrderId = " + l_lngOrderId);     
                  
            //4.getOrderUnits           
            OrderUnit[] l_orderUnits = null;
            l_orderUnits = l_ifoOrderImpl.getOrderUnits();
            
            if (l_orderUnits.length==0)
            {
                log.error("�Y���f�[�^�Ȃ�");
                throw new WEB3BaseException(
                       WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                       this.getClass().getName() + "." + STR_METHOD_NAME); 
            } 
                
            //5.create�V�K���������e(long, long, double, double, IfoOrderExecutionConditionType, 
            //                      Date, Date, String, String, String, double, double, 
            //                      IfoOrderExecutionConditionType, String, String, boolean)
            double l_dblQuantity = 0D;
            if (WEB3StringTypeUtility.isNumber(l_request.futOrderQuantity))
            {
                l_dblQuantity = Double.parseDouble(l_request.futOrderQuantity);
            }
            
            double l_dblLimitPrice = 0D;
            boolean l_blnIsLimitPrice ;
            if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_request.orderPriceDiv))
            {
                //���s
                l_dblLimitPrice = 0D;
                l_blnIsLimitPrice = false ;

            }
            else
            {
                //�w�l
                l_dblLimitPrice = Double.parseDouble(l_request.limitPrice);
                l_blnIsLimitPrice = true ;
            }             
                    
            long l_lngOrderUnitId = l_orderUnits[0].getOrderUnitId();
            log.debug("l_lngOrderUnitId = " + l_lngOrderUnitId);
            
            //�������s�����F�@@�敨OP�f�[�^�A�_�v�^.get���s����(���N�G�X�g�f�[�^.���s����) 
            IfoOrderExecutionConditionType l_changeExecCondType = 
                WEB3IfoDataAdapter.getExecutionConditionType(l_request.execCondType);      

            //OP�����}�l�[�W��
            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

            IfoOrderUnitRow l_ifoOrderUnitRow =
                (IfoOrderUnitRow)l_orderUnits[0].getDataSourceObject();

            //�敨OP����
            //(*1)�敨OP�v���_�N�g�}�l�[�W��.getProduct(�����P��.����ID)�ɂĎ擾
            WEB3IfoProductManagerImpl l_productMgr =
                (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();

            WEB3IfoProductImpl l_ifoProductImpl =
                (WEB3IfoProductImpl)l_productMgr.getProduct(l_ifoOrderUnitRow.getProductId());

            //�s��
            Market l_market = l_ifoProductImpl.getPrimaryMarket();

            //�����������F
            //���N�G�X�g�f�[�^.�����L������ != null�̏ꍇ�AOP�����}�l�[�W��.get�����L������(
            //���N�G�X�g�f�[�^.�����L������,�敨OP����(*1).�����R�[�h,�s��.getMarketCode(),�h�敨�h)�̖߂�l
            Date l_datChangeExpirationDate = null;
            if (l_request.expirationDate == null)
            {
                l_datChangeExpirationDate = l_datOrderBizDateDay;
            }
            else
            {
                l_datChangeExpirationDate = l_orderManager.getExpirationDate(
                    l_request.expirationDate,
                    l_ifoProductImpl.getProductCode(),
                    l_market.getMarketCode(),
                    WEB3FuturesOptionDivDef.FUTURES);
            }

            // �����������Z�q�F�@@ 
            //�@@   ���N�G�X�g�f�[�^.���������敪 == �h�t�w�l�h�̏ꍇ�A���N�G�X�g�f�[�^.�t�w�l�p�����������Z�q  
            //�@@   ���N�G�X�g�f�[�^.���������敪 == �hW�w�l�h�̏ꍇ�A���N�G�X�g�f�[�^.W�w�l�p�����������Z�q 
            // �t�w�l��l�F�@@ 
            //     ���N�G�X�g�f�[�^.���������敪 == �h�t�w�l�h�̏ꍇ�A���N�G�X�g�f�[�^.�t�w�l�p���������P��  
            //     ���N�G�X�g�f�[�^.���������敪 == �hW�w�l�h�̏ꍇ�A���N�G�X�g�f�[�^.W�w�l�p���������P�� 
            String l_strOrderCondOperator = null;
            double l_dblStopOrderPrice = 0D;
            if(WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_strOrderCondOperator = l_request.stopOrderCondOperator;
                l_dblStopOrderPrice = Double.parseDouble(l_request.stopOrderCondPrice);
            }
            else if(WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_strOrderCondOperator = l_request.wlimitOrderCondOperator;
                l_dblStopOrderPrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
            }

            //�iW�w�l�j�����w�l�F�@@���N�G�X�g�f�[�^.W�w�l�p�����P��
            double l_dblWLimitPrice = 0.0D;
            if (l_request.wLimitPrice != null)
            {
                l_dblWLimitPrice = Double.parseDouble(l_request.wLimitPrice);
            }

            //�iW�w�l�j���s�����F�@@�敨OP�f�[�^�A�_�v�^.get���s����(���N�G�X�g�f�[�^.�v�w�l�p���s����)
            IfoOrderExecutionConditionType l_wLimitExecCondType = 
                WEB3IfoDataAdapter.getExecutionConditionType(l_request.wlimitExecCondType);
            //�iW�w�l�j�L����ԋ敪�F�@@���N�G�X�g�f�[�^.�v�w�l�p�L����ԋ敪  
            String l_strWLimitEnableStatusDiv = l_request.wlimitEnableStatusDiv;
            // �����㒍�������敪�F�@@���N�G�X�g�f�[�^.���������敪
            String l_strExpirationDateType = l_request.expirationDateType;

            //�[��O�J�z�Ώۃt���O�F
            //�敨OP�f�[�^�A�_�v�^.get�[��O�J�z�Ώۃt���O(���N�G�X�g�f�[�^.���������敪, �����P��.���XID)�̖߂�l
            boolean l_blnEveningSessionCarryOverFlag = WEB3IfoDataAdapter.getEveningSessionCarryOverFlag(
                l_request.expirationDateType, l_ifoOrderUnitRow.getBranchId());

            WEB3IfoOpenContractChangeSpec l_web3IfoOpenContractChangeSpec = 
                WEB3IfoOpenContractChangeSpec.createIfoOpenContractChangeSpec(
                    l_lngOrderId, 
                    l_lngOrderUnitId,
                    l_dblQuantity,
                    l_dblLimitPrice,
                    l_changeExecCondType, 
                    l_datChangeExpirationDate,
                    l_datOrderBizDateDay,
                    l_request.orderCondType,
                    l_strOrderCondOperator,
                    WEB3StopBasePriceTypeDef.DEFAULT,
                    l_dblStopOrderPrice,
                    l_dblWLimitPrice,
                    l_wLimitExecCondType,
                    l_strWLimitEnableStatusDiv,
                    l_strExpirationDateType,
                    l_blnEveningSessionCarryOverFlag);
                
            //6.get�⏕����( )       
            WEB3GentradeSubAccount l_subAccount  = (WEB3GentradeSubAccount) this.getSubAccount();
                
            //7.validate�V�K����������
            log.debug("l_subAccount = " + l_subAccount);
            log.debug("l_web3IfoOpenContractChangeSpec = " + l_web3IfoOpenContractChangeSpec);
              
            OrderValidationResult l_orderValidationResult = l_orderMgr.validateFuturesChangeOrder(l_subAccount, l_web3IfoOpenContractChangeSpec);
            if(l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("ProcessingResult() = " + l_orderValidationResult.getProcessingResult());
                throw new WEB3BusinessLayerException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);         
            }        
            
            //8.getDataSourceObject          
            IfoOrderUnitRow l_orderUnitRow =
                (IfoOrderUnitRow)l_orderUnits[0].getDataSourceObject();
            log.debug("l_orderUnitRow = " + l_orderUnitRow);
            
            //9.getTradedProduct       
            WEB3IfoTradedProductImpl l_ifoTradedProduct =
                (WEB3IfoTradedProductImpl)l_productMgr.getTradedProduct(l_orderUnitRow.getProductId(), l_orderUnitRow.getMarketId());
            log.debug("l_ifoTradedProduct = " + l_ifoTradedProduct);     
            
            //10.�������ʂ��擾����B           
            double l_dblChangeQuantity  = 
                l_web3IfoOpenContractChangeSpec.getAfterChangeOriginalQuantity();
            log.debug("getAfterChangeOriginalQuantity = " + l_dblChangeQuantity);
            
            //11.���v��萔�ʂ��擾����(getExecutedQuantity)     
            double l_dblExecutedQuantity = l_orderUnits[0].getExecutedQuantity();
            log.debug("getExecutedQuantity = " + l_dblExecutedQuantity);
            
            //12.���v�����z���擾����(getExecutedAmount)            
            double l_dblExecutedAmount = l_orderUnits[0].getExecutedAmount();
            log.debug("getExecutedAmount = " + l_dblExecutedAmount);
            
            if (Double.isNaN(l_dblExecutedQuantity))
            {
                l_dblExecutedQuantity = 0D;            
            }
            
            if (Double.isNaN(l_dblExecutedAmount))
            {
                l_dblExecutedAmount = 0D;     
            }
            
            //13.�萔��           
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            log.debug("l_commission = " + l_commission);
            
            //14.(*1)�v���p�e�B�Z�b�g 
            //�����`���l�����Z�b�g����B            
            l_commission.setOrderChannel(this.getLoginChannel());
            
            //�،���ЃR�[�h���Z�b�g����B           
            l_commission.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());
            
            //���XID���Z�b�g����B                   
            l_commission.setBranchId(l_subAccount.getMainAccount().getBranch().getBranchId());
            log.debug("���XID���Z�b�g����>> " + l_commission.getBranchId());
            
            //���������Z�b�g����B            
            l_commission.setOrderBizDate(new Timestamp(l_datOrderBizDate.getTime()));
            
            //����R�[�h�iSONAR�j���Z�b�g����B           
            l_commission.setSonarTradedCode(WEB3TransactionTypeSONARDef.OPEN_CONTRACT);
            log.debug("����R�[�h���Z�b�g����>> " + l_commission.getSonarTradedCode());
            
            //�萔�����i�R�[�h���Z�b�g����B                    
            l_commission.setCommissionProductCode(WEB3CommisionProductCodeDef.INDEX_FUTURES);
            log.debug("setCommissionProductCode =" + l_commission.getCommissionProductCode());
            
            //�ٍϋ敪���Z�b�g����B                            
            l_commission.setPayType(WEB3PayTypeDef.OTHER); 
            log.debug("setPayType =" + l_commission.getPayType());
            
            //�����������`���l�����Z�b�g����B          
            l_commission.setOrgOrderChannel(l_orderUnitRow.getOrderChanel());
            log.debug("setOrgOrderChannel =" + l_commission.getOrgOrderChannel());
            
            //�������萔��No���Z�b�g����B           
            l_commission.setOrgCommissionNo(l_orderUnitRow.getCommTblNo());
            log.debug("setOrgCommissionNo = " + l_commission.getOrgCommissionNo());
            
            //�������萔��No�}�Ԃ��Z�b�g����B           
            l_commission.setOrgCommissionRevNo(l_orderUnitRow.getCommTblSubNo());
            log.debug("setOrgCommissionRevNo = " + l_commission.getOrgCommissionRevNo());              
                        
            //�萔��.is�w�l���Z�b�g����B           
            l_commission.setIsLimitPrice(l_blnIsLimitPrice);
            log.debug("setIsLimitPrice = " + l_commission.isLimitPrice());
            
            //�萔��.�����Y�����R�[�h = �敨OP����.get�����Y�����R�[�h()
            l_commission.setUnderlyingProductCode(
                ((WEB3IfoProductImpl)l_ifoTradedProduct.getProduct()).getUnderlyingProductCode());
            
            //�萔��.���� = �V�K���������e.getAfterChangeOriginalQuantity()
            l_commission.setQuantity(l_dblChangeQuantity);
            
            //15.calc�������T�Z�����
            log.debug("calc�������T�Z�����");
            log.debug("�萔�� = " + l_commission);
            log.debug("�w�l = " + (l_request.limitPrice));
            log.debug("�⏕���� = " + l_subAccount);
            log.debug("�敨OP������� = " + l_ifoTradedProduct);
            log.debug("���� = " + l_dblChangeQuantity);
            log.debug("��萔�� = " + l_dblExecutedQuantity);
            log.debug("���v�����z = " + l_dblExecutedAmount);
            log.debug("isSkip���z�`�F�b�N = false " );            
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult = new WEB3IfoEstimateDeliveryAmountCalcResult();
            
            l_estimateDeliveryAmountCalcResult = 
                l_orderMgr.calcChangeEstimatePrice(
                    l_commission,
                    l_dblLimitPrice,
                    l_subAccount,
                    l_ifoTradedProduct,
                    l_dblChangeQuantity,
                    l_dblExecutedQuantity,
                    l_dblExecutedAmount,
                    false);  
                    
            log.debug("l_web3IfoOpenContractChangeSpec.getOrderCond() = " + l_web3IfoOpenContractChangeSpec.getOrderCond());
            Trader l_trader = this.getTrader();

            WEB3IfoOpenContractChangeUpdateInterceptor l_openContractChangeUpdateInterceptor = new WEB3IfoOpenContractChangeUpdateInterceptor(l_web3IfoOpenContractChangeSpec);
            
            //16. 
            l_openContractChangeUpdateInterceptor.setCommision(l_commission);
            l_openContractChangeUpdateInterceptor.setEstimateDeliveryAmountCalcResult(l_estimateDeliveryAmountCalcResult);
            l_openContractChangeUpdateInterceptor.setOrderCond(l_request.orderCondType);

            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_openContractChangeUpdateInterceptor.setOrderCondOperator(l_request.stopOrderCondOperator);
                l_openContractChangeUpdateInterceptor.setStopOrderBasePriceType(WEB3StopBasePriceTypeDef.DEFAULT);
                l_openContractChangeUpdateInterceptor.setStopOrderBasePrice(Double.parseDouble(l_request.stopOrderCondPrice));
            }
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_openContractChangeUpdateInterceptor.setOrderCondOperator(l_request.wlimitOrderCondOperator);
                l_openContractChangeUpdateInterceptor.setStopOrderBasePriceType(WEB3StopBasePriceTypeDef.DEFAULT);
                l_openContractChangeUpdateInterceptor.setStopOrderBasePrice(Double.parseDouble(l_request.wlimitOrderCondPrice));
            }
            l_openContractChangeUpdateInterceptor.setWLimitPriceChange(l_web3IfoOpenContractChangeSpec.getWLimitPriceChange());
            
            long l_lngTraderId = 0;
            if (l_trader != null)
            {
                l_lngTraderId = l_trader.getTraderId();
            }
            l_openContractChangeUpdateInterceptor.setTraderId(l_lngTraderId);

			OpLoginSecurityService l_opLoginSec =
				(OpLoginSecurityService) Services.getService(
					OpLoginSecurityService.class);
			String l_strOrderRootDiv =
				l_opLoginSec.getSessionProperty(
					WEB3SessionAttributeDef.ORDER_ROOT_DIV);

			l_openContractChangeUpdateInterceptor.setOrderRootDiv(l_strOrderRootDiv);
                          
            //17validate����]��
            WEB3TPTradingPowerService l_tradingPowerService = 
                (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
            Object[] l_interceptorObject = new Object[1];
            l_interceptorObject[0] = l_openContractChangeUpdateInterceptor;
            Object[] l_specObject = new Object[1];
            l_specObject[0] = l_web3IfoOpenContractChangeSpec;
            
            WEB3TPTradingPowerResult l_result = 
                l_tradingPowerService.validateTradingPower
                    (l_subAccount,
                    l_interceptorObject,
                    l_specObject,
                    l_orderUnitRow.getOrderType(),
                    false);
            if (!l_result.isResultFlg())
            {
                log.error("����]�̓`�F�b�N�G���[");
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                    STR_METHOD_NAME);
            }
            
            //18.WEB3GenResponse          
            WEB3FuturesOpenMarginChangeConfirmResponse l_response = 
                (WEB3FuturesOpenMarginChangeConfirmResponse)l_request.createResponse();    
                
            //19.get�v�Z�P��           
            double l_dblCalcUnitPrice = l_estimateDeliveryAmountCalcResult.getCalcUnitPrice();                  
            
            //20.get�T�Z��n���           
            double l_dblEstimateDeliveryAmount = l_estimateDeliveryAmountCalcResult.getEstimateDeliveryAmount();
            log.debug("get�T�Z��n��� = " + l_dblEstimateDeliveryAmount);   
            
            //21.get�s��ǌx���w��(���X, String)
            WEB3GentradeBranch l_branch = (WEB3GentradeBranch)l_subAccount.getMainAccount().getBranch();
            String[] l_strTradeCloseSuspension = null;
            l_strTradeCloseSuspension = WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(
                l_branch,
                WEB3FuturesOptionDivDef.FUTURES);
                
            //22.(*2)�v���p�e�B�Z�b�g         
            l_response.estimatedContractPrice = WEB3StringTypeUtility.formatNumber(l_dblEstimateDeliveryAmount);
            l_response.messageSuspension = l_strTradeCloseSuspension;
            l_response.checkPrice = WEB3StringTypeUtility.formatNumber(l_dblCalcUnitPrice);
            l_response.checkDate =  l_datOrderBizDateDay;
            l_response.commission = WEB3StringTypeUtility.formatNumber(l_estimateDeliveryAmountCalcResult.getCommission());
            l_response.commissionConsumptionTax = WEB3StringTypeUtility.formatNumber(l_estimateDeliveryAmountCalcResult.getCommissionTax());
            l_response.commissionCourse = l_estimateDeliveryAmountCalcResult.getCommissionCourse();
            l_response.partExecQuantity = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getExecutedQuantity());
            //���X�|���X.�����L������ = �V�K���������e.����������
            l_response.expirationDate = l_web3IfoOpenContractChangeSpec.getChangeExpirationDate();

            log.debug("l_response.estimatedContractPrice = " + l_response.estimatedContractPrice);
            log.debug("l_response.messageSuspension = " + l_response.messageSuspension);
            log.debug("l_response.checkPrice = " + l_response.checkPrice);
            log.debug("l_response.checkDate = " + l_response.checkDate);
                    
            log.exiting(STR_METHOD_NAME);        
            return l_response;
             
        }  
        catch (NotFoundException l_ex)
        {            
            log.error("�e�[�u���ɊY������f�[�^������܂���B",l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);               
            
        }

    }
    
    /**
     * (submit����)<BR>
     * �����w���敨�̒����V�K��������o�^����B<BR>
     * <BR>
     * �u�i�敨�����V�K���T�[�r�X�jsubmit�����v�Q�ƁB<BR>
     * <BR>
     * =============================================== <BR>
     * �V�[�P���X�} : �i�敨�����V�K���T�[�r�X�jsubmit�����Q<BR>
     * ��̈ʒu     : 1.13 �߂�l�̎���]�͌���.����t���O == false �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@��O���X���[����B <BR>
     * class        : WEB3BusinessLayerException <BR>
     * tag          : BUSINESS_ERROR_01306 <BR>
     * =============================================== <BR>
     * <BR>
     * @@param l_request - �����w���敨�����V�K���������N�G�X�g<BR>
     * @@return WEB3FuturesOpenMarginChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A8A4F30397
     */
    protected WEB3FuturesOpenMarginChangeCompleteResponse submitOrder(
        WEB3FuturesOpenMarginChangeCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "submitOrder(WEB3FuturesOpenMarginChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);      
        
        try
        {
            //1.validate           
            l_request.validate();
            
            //2.������ԊǗ�����get������
            if (l_request.checkDate == null)
            {
                l_request.checkDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            }
            Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
            Date l_datOrderBizDateNew = WEB3DateUtility.toDay(l_datOrderBizDate); 
            log.debug("������ԊǗ�����get������: " + l_datOrderBizDate);         
            
            
            //3.IfoOrderImpl     
            TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.IFO);   
            WEB3FuturesOrderManagerImpl l_orderMgr = 
                (WEB3FuturesOrderManagerImpl) l_tradingModule.getOrderManager();
                
            long l_lngOrderId = Long.parseLong(l_request.id);
            IfoOrderImpl l_ifoOrderImpl = (IfoOrderImpl) l_orderMgr.getOrder(l_lngOrderId); 
            log.debug("l_lngOrderId = " + l_lngOrderId);     
                  
            //4.getOrderUnits           
            OrderUnit[] l_orderUnits = null;
            l_orderUnits = l_ifoOrderImpl.getOrderUnits();
            
            //5.create�V�K���������e(long, long, double, double, IfoOrderExecutionConditionType, 
            //                      Date, Date, String, String, String, double, double, 
            //                      IfoOrderExecutionConditionType, String, String, boolean)
            double l_dblQuantity = 0D;
            if (WEB3StringTypeUtility.isNumber(l_request.futOrderQuantity))
            {
                l_dblQuantity = Double.parseDouble(l_request.futOrderQuantity);
            }
            
            double l_dblLimitPrice = 0D;
            boolean l_blnIsLimitPrice ;
            if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_request.orderPriceDiv))
            {
                //���s
                l_dblLimitPrice = 0D;
                l_blnIsLimitPrice=false;
            }
            else
            {
                //�w�l
                l_dblLimitPrice = Double.parseDouble(l_request.limitPrice);
                l_blnIsLimitPrice=true;
            }   
            
            long l_lngOrderUnitId = l_orderUnits[0].getOrderUnitId();
            log.debug("l_lngOrderUnitId = " + l_lngOrderUnitId);
                        
            //�������s�����F�@@�敨OP�f�[�^�A�_�v�^.get���s����(���N�G�X�g�f�[�^.���s����) 
            IfoOrderExecutionConditionType l_changeExecCondType = 
                WEB3IfoDataAdapter.getExecutionConditionType(l_request.execCondType);  

            //OP�����}�l�[�W��
            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

            IfoOrderUnitRow l_ifoOrderUnitRow =
                (IfoOrderUnitRow)l_orderUnits[0].getDataSourceObject();

            //�敨OP����
            //(*1)�敨OP�v���_�N�g�}�l�[�W��.getProduct(�����P��.����ID)�ɂĎ擾
            WEB3IfoProductManagerImpl l_productMgr =
                (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();

            WEB3IfoProductImpl l_ifoProductImpl =
                (WEB3IfoProductImpl)l_productMgr.getProduct(l_ifoOrderUnitRow.getProductId());

            Market l_market = l_ifoProductImpl.getPrimaryMarket();

            //�����������F
            //���N�G�X�g�f�[�^.�����L������ != null�̏ꍇ�AOP�����}�l�[�W��.get�����L������(
            //���N�G�X�g�f�[�^.�����L������,�敨OP����(*1).�����R�[�h,�s��.getMarketCode(),�h�敨�h)�̖߂�l
            //���N�G�X�g�f�[�^.�����L������ == null�̏ꍇ�Aget������()�̖߂�l
            Date l_datChangeExpirationDate = null;
            if (l_request.expirationDate == null)
            {
                l_datChangeExpirationDate = l_datOrderBizDateNew;
            }
            else
            {
                l_datChangeExpirationDate = l_orderManager.getExpirationDate(
                    l_request.expirationDate,
                    l_ifoProductImpl.getProductCode(),
                    l_market.getMarketCode(),
                    WEB3FuturesOptionDivDef.FUTURES);
            }
            
            // �����������Z�q�F�@@ 
            //�@@   ���N�G�X�g�f�[�^.���������敪 == �h�t�w�l�h�̏ꍇ�A���N�G�X�g�f�[�^.�t�w�l�p�����������Z�q  
            //�@@   ���N�G�X�g�f�[�^.���������敪 == �hW�w�l�h�̏ꍇ�A���N�G�X�g�f�[�^.W�w�l�p�����������Z�q 
            // �t�w�l��l�F�@@ 
            //     ���N�G�X�g�f�[�^.���������敪 == �h�t�w�l�h�̏ꍇ�A���N�G�X�g�f�[�^.�t�w�l�p���������P��  
            //     ���N�G�X�g�f�[�^.���������敪 == �hW�w�l�h�̏ꍇ�A���N�G�X�g�f�[�^.W�w�l�p���������P�� 
            String l_strOrderCondOperator = null;
            double l_dblStopOrderPrice = 0D;
            if(WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_strOrderCondOperator = l_request.stopOrderCondOperator;
                l_dblStopOrderPrice = Double.parseDouble(l_request.stopOrderCondPrice);
            }
            else if(WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_strOrderCondOperator = l_request.wlimitOrderCondOperator;
                l_dblStopOrderPrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
            }

            //�iW�w�l�j�����w�l�F�@@���N�G�X�g�f�[�^.W�w�l�p�����P��
            double l_dblWLimitPrice = 0.0D;
            if (l_request.wLimitPrice != null)
            {
                l_dblWLimitPrice = Double.parseDouble(l_request.wLimitPrice);
            }

            //�iW�w�l�j���s�����F�@@�敨OP�f�[�^�A�_�v�^.get���s����(���N�G�X�g�f�[�^.�v�w�l�p���s����)
            IfoOrderExecutionConditionType l_wLimitExecCondType = 
                WEB3IfoDataAdapter.getExecutionConditionType(l_request.wlimitExecCondType);
            //�iW�w�l�j�L����ԋ敪�F�@@���N�G�X�g�f�[�^.�v�w�l�p�L����ԋ敪  
            String l_strWLimitEnableStatusDiv = l_request.wlimitEnableStatusDiv;
            // �����㒍�������敪�F�@@���N�G�X�g�f�[�^.���������敪
            String l_strExpirationDateType = l_request.expirationDateType;

            //�[��O�J�z�Ώۃt���O�F
            //�敨OP�f�[�^�A�_�v�^.get�[��O�J�z�Ώۃt���O(���N�G�X�g�f�[�^.���������敪, �����P��.���XID)�̖߂�l
            boolean l_blnEveningSessionCarryOverFlag = WEB3IfoDataAdapter.getEveningSessionCarryOverFlag(
                l_request.expirationDateType, l_ifoOrderUnitRow.getBranchId());

            WEB3IfoOpenContractChangeSpec l_web3IfoOpenContractChangeSpec = 
                WEB3IfoOpenContractChangeSpec.createIfoOpenContractChangeSpec(
                    l_lngOrderId, 
                    l_lngOrderUnitId,
                    l_dblQuantity,
                    l_dblLimitPrice,
                    l_changeExecCondType, 
                    l_datChangeExpirationDate,
                    l_datOrderBizDateNew,
                    l_request.orderCondType,
                    l_strOrderCondOperator,
                    WEB3StopBasePriceTypeDef.DEFAULT,
                    l_dblStopOrderPrice,
                    l_dblWLimitPrice,
                    l_wLimitExecCondType,
                    l_strWLimitEnableStatusDiv,
                    l_strExpirationDateType,
                    l_blnEveningSessionCarryOverFlag);
                              
            //6.get�⏕����( )       
            WEB3GentradeSubAccount l_subAccount  = (WEB3GentradeSubAccount) this.getSubAccount();
                
            //7.validate�V�K����������
            log.debug("l_subAccount = " + l_subAccount);
            log.debug("l_web3IfoOpenContractChangeSpec = " + l_web3IfoOpenContractChangeSpec);
            OrderValidationResult l_orderValidationResult = l_orderMgr.validateFuturesChangeOrder(l_subAccount, l_web3IfoOpenContractChangeSpec);
            if(l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("ProcessingResult() = " + l_orderValidationResult.getProcessingResult());
                throw new WEB3BusinessLayerException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);              
            }                   
                        
            //8.getDataSourceObject          
            IfoOrderUnitRow l_orderUnitRow =
                (IfoOrderUnitRow)l_orderUnits[0].getDataSourceObject();
            log.debug("l_orderUnitRow = " + l_orderUnitRow);
            
            //9.getTradedProduct       
            WEB3IfoTradedProductImpl l_ifoTradedProduct =
                (WEB3IfoTradedProductImpl)l_productMgr.getTradedProduct(l_orderUnitRow.getProductId(), l_orderUnitRow.getMarketId());
            log.debug("l_ifoTradedProduct = " + l_ifoTradedProduct);     
            
            //10.�������ʂ��擾����B           
            double l_dblChangeQuantity  = 
                l_web3IfoOpenContractChangeSpec.getAfterChangeOriginalQuantity();
            log.debug("getAfterChangeOriginalQuantity = " + l_dblChangeQuantity);
            
            //11.���v��萔�ʂ��擾����(getExecutedQuantity)     
            double l_dblExecutedQuantity = l_orderUnits[0].getExecutedQuantity();
            log.debug("getExecutedQuantity = " + l_dblExecutedQuantity);
            
            //12.���v�����z���擾����(getExecutedAmount)            
            double l_dblExecutedAmount = l_orderUnits[0].getExecutedAmount();
            log.debug("getExecutedAmount = " + l_dblExecutedAmount);
            
            if (Double.isNaN(l_dblExecutedQuantity))
            {
                l_dblExecutedQuantity = 0D;            
            }
            
            if (Double.isNaN(l_dblExecutedAmount))
            {
                l_dblExecutedAmount = 0D;     
            }
            
            //13.�萔��           
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            log.debug("l_commission = " + l_commission);
            
            //14.(*1)�v���p�e�B�Z�b�g 
            //�����`���l�����Z�b�g����B            
            l_commission.setOrderChannel(this.getLoginChannel());
            
            //�،���ЃR�[�h���Z�b�g����B           
            l_commission.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());
            
            //���XID���Z�b�g����B                   
            l_commission.setBranchId(l_subAccount.getMainAccount().getBranch().getBranchId());
            log.debug("���XID���Z�b�g����>> " + l_commission.getBranchId());
            
            //���������Z�b�g����B
            Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            l_commission.setOrderBizDate(new Timestamp(l_datBizDate.getTime()));
            
            //����R�[�h�iSONAR�j���Z�b�g����B           
            l_commission.setSonarTradedCode(WEB3TransactionTypeSONARDef.OPEN_CONTRACT);
            log.debug("����R�[�h���Z�b�g����>> " + l_commission.getSonarTradedCode());
            
            //�萔�����i�R�[�h���Z�b�g����B                    
            l_commission.setCommissionProductCode(WEB3CommisionProductCodeDef.INDEX_FUTURES);
            log.debug("setCommissionProductCode =" + l_commission.getCommissionProductCode());
            
            //�ٍϋ敪���Z�b�g����B                            
            l_commission.setPayType(WEB3PayTypeDef.OTHER); 
            log.debug("setPayType =" + l_commission.getPayType());
            
            //�����������`���l�����Z�b�g����B          
            l_commission.setOrgOrderChannel(l_orderUnitRow.getOrderChanel());
            log.debug("setOrgOrderChannel =" + l_commission.getOrgOrderChannel());
            
            //�������萔��No���Z�b�g����B           
            l_commission.setOrgCommissionNo(l_orderUnitRow.getCommTblNo());
            log.debug("setOrgCommissionNo = " + l_commission.getOrgCommissionNo());
            
            //�������萔��No�}�Ԃ��Z�b�g����B           
            l_commission.setOrgCommissionRevNo(l_orderUnitRow.getCommTblSubNo());
            log.debug("setOrgCommissionRevNo = " + l_commission.getOrgCommissionRevNo());              
                        
            //�萔��.is�w�l���Z�b�g����B
            l_commission.setIsLimitPrice(l_blnIsLimitPrice);
            log.debug("setIsLimitPrice = " + l_commission.isLimitPrice());
            
            //�萔��.�����Y�����R�[�h = �敨OP����.get�����Y�����R�[�h()
            l_commission.setUnderlyingProductCode(
                ((WEB3IfoProductImpl)l_ifoTradedProduct.getProduct()).getUnderlyingProductCode());
            
            //�萔��.���� = �V�K���������e.getAfterChangeOriginalQuantity()
            l_commission.setQuantity(l_dblChangeQuantity);
            
            //15.calc�������T�Z�����
            log.debug("�萔�� = " + l_commission);
            log.debug("�m�F���P�� = " + (l_request.checkPrice));
            log.debug("�⏕���� = " + l_subAccount);
            log.debug("�敨OP������� = " + l_ifoTradedProduct);
            log.debug("���� = " + l_dblChangeQuantity);
            log.debug("��萔�� = " + l_dblExecutedQuantity);
            log.debug("���v�����z = " + l_dblExecutedAmount);          
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult = new WEB3IfoEstimateDeliveryAmountCalcResult();
            log.debug("l_web3IfoOpenContractChangeSpec.getOrderCond() = " + l_web3IfoOpenContractChangeSpec.getOrderCond());                    

            //�������T�Z��������v�Z����B
            // [calc�T�Z�����()�Ɏw�肷�����]
            //   �萔���F�@@�萔���I�u�W�F�N�g
            //   �w�l�F�@@
            //     ���N�G�X�g�f�[�^.�m�F���P��!=null�̏ꍇ�A���N�G�X�g�f�[�^.�m�F���P��
            //     ���N�G�X�g�f�[�^.�m�F���P��==null�̏ꍇ�A���N�G�X�g�f�[�^.�����P��(*1)
            //    �i*1�@@���N�G�X�g�f�[�^.�����P��==null�̏ꍇ�̓[�����Z�b�g�B�j
            //   �⏕�����F�@@�⏕�����I�u�W�F�N�g
            //   �敨OP��������F�@@�敨OP��������I�u�W�F�N�g
            //   ���ʁF �V�K���������e.getQuantity() 
            //   ��萔�ʁF getExecutedQuantity()�̖߂�l
            //   ���v�����z�F getExecuterAmount()�̖߂�l
            //   isSkip���z�`�F�b�N�F�@@false
            double l_dblCheckPrice = 0D;
            if (l_request.checkPrice != null)
            {
                l_dblCheckPrice = Double.parseDouble(l_request.checkPrice);
            }
            else
            {
                l_dblCheckPrice = l_dblLimitPrice;
            }

            l_estimateDeliveryAmountCalcResult = 
                l_orderMgr.calcChangeEstimatePrice(
                    l_commission,
                    l_dblCheckPrice,
                    l_subAccount,
                    l_ifoTradedProduct,
                    l_dblChangeQuantity,
                    l_dblExecutedQuantity,
                    l_dblExecutedAmount,
                    false);  

            //get�㗝���͎�( )
            Trader l_trader = this.getTrader();                        


            //16.�敨OP�V�K�������X�V�C���^�Z�v�^(�V�K���������e)
            WEB3IfoOpenContractChangeUpdateInterceptor l_interceptor = 
                new WEB3IfoOpenContractChangeUpdateInterceptor(l_web3IfoOpenContractChangeSpec);
            
             //17. (*2) �v���p�e�B�Z�b�g
            l_interceptor.setCommision(l_commission);
            l_interceptor.setEstimateDeliveryAmountCalcResult(l_estimateDeliveryAmountCalcResult);
            l_interceptor.setOrderCond(l_request.orderCondType);           

            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_interceptor.setOrderCondOperator(l_request.stopOrderCondOperator);
                l_interceptor.setStopOrderBasePrice(Double.parseDouble(l_request.stopOrderCondPrice));
                l_interceptor.setStopOrderBasePriceType(WEB3StopBasePriceTypeDef.DEFAULT);
                l_interceptor.setWLimitPriceChange(0);
            }
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_interceptor.setOrderCondOperator(l_request.wlimitOrderCondOperator);
                l_interceptor.setStopOrderBasePrice(Double.parseDouble(l_request.wlimitOrderCondPrice));
                l_interceptor.setStopOrderBasePriceType(WEB3StopBasePriceTypeDef.DEFAULT);
                l_interceptor.setWLimitPriceChange(l_dblWLimitPrice);                 
            }
            
            long l_lngTraderId = 0;
            if (l_trader != null)
            {
                l_lngTraderId = l_trader.getTraderId();
            }
            l_interceptor.setTraderId(l_lngTraderId);    
            
			OpLoginSecurityService l_opLoginSec =
				(OpLoginSecurityService) Services.getService(
					OpLoginSecurityService.class);
			String l_strOrderRootDiv =
				l_opLoginSec.getSessionProperty(
					WEB3SessionAttributeDef.ORDER_ROOT_DIV);

			l_interceptor.setOrderRootDiv(l_strOrderRootDiv);

            WEB3TPTradingPowerService l_tradingPowerService = 
                (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
            Object[] l_interceptorObject = new Object[1];
            l_interceptorObject[0] = l_interceptor;
            Object[] l_specObject = new Object[1];
            l_specObject[0] = l_web3IfoOpenContractChangeSpec;
            
            WEB3TPTradingPowerResult l_result = 
                l_tradingPowerService.validateTradingPower
                (l_subAccount,
                l_interceptorObject,
                l_specObject,
                l_orderUnitRow.getOrderType(),
                false);
            if (!l_result.isResultFlg())
            {
                log.error("����]�̓`�F�b�N�G���[");
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                    STR_METHOD_NAME);
            }
               
            //18.setThreadLocalPersistenceEventInterceptor            
            l_orderMgr.setThreadLocalPersistenceEventInterceptor(l_interceptor);
            
            //19.submitChangeOrder(�⏕���� : SubAccount, �V�K���������e : ChangeOrderSpec, ����p�X���[�h : String, isSkip�����R��(=true) : boolean)
            OrderSubmissionResult l_orderSubmissionResult = l_orderMgr.submitChangeOrder(
                l_subAccount,
                l_web3IfoOpenContractChangeSpec,
                l_request.password,
                true);
                
            if (l_orderSubmissionResult.getProcessingResult().isFailedResult())
            {
                log.debug("ProcessingResult() = " + l_orderSubmissionResult.getProcessingResult());
                throw new WEB3BusinessLayerException(
                    l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME); 
            }

            //is�\�񒍕��m�F�v(IfoOrderUnit)
            IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_orderUnits[0];
            boolean l_blnIsReserveOrderExist = l_orderMgr.isReserveOrderExist(l_ifoOrderUnit);

            //�\�񒍕��m�F�v�iis�\�񒍕��m�F�v() == true�j�̏ꍇ
            List l_lisGetOpenReserveIfoOrderUnits = null;
            if (l_blnIsReserveOrderExist)
            {
                //get�L���\�񒍕��P�ʈꗗ(�e�����̒���ID : long)
                WEB3ToSuccReservationIfoOrderUpdateService l_ifoOrderUpdateService =
                    (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                        WEB3ToSuccReservationIfoOrderUpdateService.class);
                l_lisGetOpenReserveIfoOrderUnits =
                    l_ifoOrderUpdateService.getOpenReserveIfoOrderUnits(l_ifoOrderUnit.getOrderId());
            }

            //20.WEB3GenResponse(WEB3GenRequest)
            WEB3FuturesOpenMarginChangeCompleteResponse l_response = (WEB3FuturesOpenMarginChangeCompleteResponse) l_request.createResponse();
            
            //21.(*3) �v���p�e�B�Z�b�g 
            l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
            l_response.orderActionId = l_request.id;
            if (l_lisGetOpenReserveIfoOrderUnits != null)
            {
                l_response.succSettingFlag = true;
            }
            else
            {
                l_response.succSettingFlag = false;
            }

            log.debug("l_response.lastUpdatedTimestamp = " + l_response.lastUpdatedTimestamp);
            log.debug("l_response.orderActionId = " + l_response.orderActionId);                     

            log.exiting(STR_METHOD_NAME);
            return l_response;           
        }
        catch (NotFoundException l_ex)
        {            
            log.error("�e�[�u���ɊY������f�[�^������܂���B",l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);               
        }
    }
}
@
