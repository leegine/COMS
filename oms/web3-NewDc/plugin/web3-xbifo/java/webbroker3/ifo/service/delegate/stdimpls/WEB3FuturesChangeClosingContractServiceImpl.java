head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesChangeClosingContractServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�����ԍσT�[�r�X����(WEB3FuturesChangeClosingContractServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/26 ���Q (���u) �V�K�쐬
                 : 2006/08/01 �юu�� (���u) �d�l�ύXNo.489
                 : 2006/08/10 �юu�� (���u) �d�l�ύXNo.545                  
Revesion History : 2007/06/21 �����F(���u) ���f��709 742
Revesion History : 2007/11/20 ��іQ (���u)�d�l�ύX ���f��811
Revesion History : 2007/11/28 ��іQ (���u)Java�\�[�X�i��{�݌v�ƍ����Ă��Ȃ������j007
Revesion History : 2008/03/17 �����F(���u) ���f��855
Revesion History : 2008/08/18 ���z(���u) IFO�����_�Ή�
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoOrderImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ClosingOrderDef;
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
import webbroker3.ifo.WEB3IfoChangeSettleContractOrderSpec;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3IfoProductQuote;
import webbroker3.ifo.WEB3IfoSettleContractChangeUpdateInterceptor;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.message.WEB3FuturesCloseMarginChangeCompleteRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginChangeCompleteResponse;
import webbroker3.ifo.message.WEB3FuturesCloseMarginChangeConfirmRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginChangeConfirmResponse;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.service.delegate.WEB3FuturesChangeClosingContractService;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationIfoOrderUpdateService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�敨�����ԍσT�[�r�XImpl)<BR>
 * �����w���敨�����ԍσT�[�r�X�����N���X
 *
 * @@author �@@���Q
 * @@version 1.0
 */
public class WEB3FuturesChangeClosingContractServiceImpl extends WEB3FuturesClientRequestService implements WEB3FuturesChangeClosingContractService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3FuturesChangeClosingContractServiceImpl.class);

    /**
     * @@roseuid 40F7A2CF0177
     */
    public WEB3FuturesChangeClosingContractServiceImpl()
    {

    }

    /**
     * �����w���敨�����ԍσT�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Avalidate����()�܂��́A<BR>
     * submit����()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A8B0BD01DE
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request instanceof WEB3FuturesCloseMarginChangeConfirmRequest)
        {
            WEB3FuturesCloseMarginChangeConfirmRequest l_futureConfirmRequest =
                (WEB3FuturesCloseMarginChangeConfirmRequest)l_request;
            WEB3FuturesCloseMarginChangeConfirmResponse l_futurethisConfirmResponse =
                this.validateOrder(l_futureConfirmRequest);
            log.exiting(STR_METHOD_NAME);
            return l_futurethisConfirmResponse;
        }
        else if (l_request instanceof WEB3FuturesCloseMarginChangeCompleteRequest)
        {
            WEB3FuturesCloseMarginChangeCompleteRequest l_futuresCompleteRequest =
                (WEB3FuturesCloseMarginChangeCompleteRequest)l_request;
            WEB3FuturesCloseMarginChangeCompleteResponse l_futuresCompleteResponse =
                this.submitOrder(l_futuresCompleteRequest);
            log.exiting(STR_METHOD_NAME);
            return l_futuresCompleteResponse;
        }
        else
        {
            log.error(STR_METHOD_NAME + " __Error[���͒l���s���ł�]__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                STR_METHOD_NAME);
        }
    }

    /**
     * (validate����)<BR>
     * �����w���敨�̒����ԍϔ����R�����s���B<BR>
     * <BR>
     * �u�i�敨�����ԍσT�[�r�X�jvalidate�����v�Q�ƁB<BR>
     * ==========================================================<BR>
     * �V�[�P���X�}(�u�i�敨�����ԍσT�[�r�X�jvalidate�����v): �P.4 getOrderUnits( )<BR>
     * �����P��.getDateSourceObject().getClosingOrder() == 0�i�����_���j<BR>
     * and (���N�G�X�g�f�[�^.�������� == 0 or ���N�G�X�g�f�[�^.�������� == null)<BR>
     * �̏ꍇ�A��O���X���[����B<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:   BUSINESS_ERROR_00180<BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)�����w���敨�����ԍϊm�F���N�G�X�g<BR>
     * @@return WEB3FuturesCloseMarginChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A8B0BD01FD
     */
    protected WEB3FuturesCloseMarginChangeConfirmResponse validateOrder(WEB3FuturesCloseMarginChangeConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrder(WEB3FuturesCloseMarginChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //1.1 ���N�G�X�g�f�[�^�̐��������`�F�b�N����B
            l_request.validate();

            //1.2 �����I�u�W�F�N�g���擾����B
            long l_lngID = Long.parseLong(l_request.id);
            IfoOrderImpl l_ifoOrderImpl = new IfoOrderImpl(l_lngID);

            //1.3 �����P�ʂ��擾����B �i���Y���\�b�h�Ŏ擾�����z���0�Ԗڂ̗v�f�j
            OrderUnit[] l_orderUnits = l_ifoOrderImpl.getOrderUnits();
            OrderUnit l_orderUnit = l_orderUnits[0];
            IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();

            //�������ʂ��擾����B            
            double l_dblOrderQuantity = 0D;
            //�����P��.getDataSourceObject().getClosingOrder()!=0 and ���N�G�X�g�f�[�^.��������==null or 0
            //��O���X�[������B
            if (!WEB3ClosingOrderDef.RANDOM.equals(l_ifoOrderUnitRow.getClosingOrder()))
            {
                if (l_request.futOrderQuantity == null || "0".equals(l_request.futOrderQuantity))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00180,
                        STR_METHOD_NAME);
                }
                l_dblOrderQuantity = Double.parseDouble(l_request.futOrderQuantity);
            }

            //1.4 ���Ϗ��ʂŃ\�[�g���A�ԍό��ʃG���g���̔z����쐬����B
            //  [create�ԍό��ʃG���g��()�Ɏw�肷�����]
            //  �����P��ID�F �����P��.getOrderUnitId()
            //  �������ʁF ���N�G�X�g�f�[�^.��������
            //  �ԍό���[]�F ���N�G�X�g�f�[�^.�ԍό���[]
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3FuturesOrderManagerImpl l_orderManager =
                (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            //�����P��ID���擾����B
            long l_lngOrderUnitId  = l_orderUnit.getOrderUnitId();
            SettleContractEntry[] l_eqOrderEntry =
                l_orderManager.createSettleContractEntry(
                    l_lngOrderUnitId,
                    l_dblOrderQuantity,
                    l_request.closeMarginContractUnits);

            //1.5 ���������擾����B
            Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

            //OP�����}�l�[�W��
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_optionOrderManagerImpl =
                (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

            //�敨OP����
            //(*1)�敨OP�v���_�N�g�}�l�[�W��.getProduct(�����P��.����ID)�ɂĎ擾
            WEB3IfoProductManagerImpl l_productMgr =
                (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();

            WEB3IfoProductImpl l_ifoProductImpl = null;
            try
            {
                l_ifoProductImpl =
                    (WEB3IfoProductImpl)l_productMgr.getProduct(l_ifoOrderUnitRow.getProductId());
            }
            catch (NotFoundException l_ex)
            {
                log.error(STR_METHOD_NAME,l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //�s��
            Market l_market = l_ifoProductImpl.getPrimaryMarket();

            //�����������F
            //�@@���N�G�X�g�f�[�^.�����L������ != null�̏ꍇ�AOP�����}�l�[�W��.get�����L������(
            //���N�G�X�g�f�[�^.�����L������,�敨OP����(*1).�����R�[�h,�s��.getMarketCode(),�h�敨�h)�̖߂�l
            //���N�G�X�g�f�[�^.�����L������ == null�̏ꍇ�Aget������()�̖߂�l
            Date l_datChangeExpirationDate = null;
            if (l_request.expirationDate != null)
            {
                l_datChangeExpirationDate = l_optionOrderManagerImpl.getExpirationDate(
                    l_request.expirationDate,
                    l_ifoProductImpl.getProductCode(),
                    l_market.getMarketCode(),
                    WEB3FuturesOptionDivDef.FUTURES);
            }
            else 
            {
                l_datChangeExpirationDate = WEB3DateUtility.toDay(l_datOrderBizDate);
            }
            
            //�����������Z�q
            String l_strOrderCondOperator = null;
            
            //�t�w�l��l
            double l_dblStopOrderPrice = 0D;
            
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_strOrderCondOperator = l_request.stopOrderCondOperator;
                l_dblStopOrderPrice = Double.parseDouble(l_request.stopOrderCondPrice);
            }
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_strOrderCondOperator = l_request.wlimitOrderCondOperator;
                l_dblStopOrderPrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
            }
            
            //�����P�����擾����B            
            double l_dblLimitPrice = 0D;
            boolean l_blnIsLimitPrice ;
            if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_request.orderPriceDiv))
            {
                //���s
                l_dblLimitPrice = 0D;
                l_blnIsLimitPrice = false;
            }
            else
            {
                //�w�l
                l_dblLimitPrice = Double.parseDouble(l_request.limitPrice);
                l_blnIsLimitPrice = true;
            }
            
            //�������s����
            IfoOrderExecutionConditionType l_ifoOrderExecutionConditionType = 
                WEB3IfoDataAdapter.getExecutionConditionType(l_request.execCondType);
            
            //�iW�w�l�j�����w�l�F�@@���N�G�X�g�f�[�^.W�w�l�p�����P�� �B
            double l_dblWLimitPrice = 0D;
           if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_request.wLimitOrderPriceDiv)
                && WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {                
                l_dblWLimitPrice = Double.parseDouble(l_request.wLimitPrice);
            }
            else
            {
                l_dblWLimitPrice = 0D;
            } 
            
            //(W�w�l)���s����
            IfoOrderExecutionConditionType l_wLimitExecCondType =
                WEB3IfoDataAdapter.getExecutionConditionType(l_request.wlimitExecCondType);
            
            //�[��O�J�z�Ώۃt���O�F�@@�敨OP�f�[�^�A�_�v�^.get�[��O
            //�J�z�Ώۃt���O(���N�G�X�g�f�[�^.���������敪, �����P��.���XID)�̖߂�l
            boolean l_blnEveningSessionCarryOverFlag =
                WEB3IfoDataAdapter.getEveningSessionCarryOverFlag(
                    l_request.expirationDateType,
                    l_ifoOrderUnitRow.getBranchId());
            //1.6 �ԍϒ������e���쐬����B 
            WEB3IfoChangeSettleContractOrderSpec l_ifoOrderSpec = 
                WEB3IfoChangeSettleContractOrderSpec.createIfoChangeSettleContractOrderSpec(
                    l_ifoOrderUnitRow.getOrderId(),
                    l_ifoOrderUnitRow.getOrderUnitId(),
                    l_dblLimitPrice,
                    l_eqOrderEntry,
                    l_ifoOrderExecutionConditionType,
                    l_datChangeExpirationDate,
                    l_datOrderBizDate,
                    l_request.orderCondType,
                    l_strOrderCondOperator,
                    WEB3StopBasePriceTypeDef.DEFAULT,
                    l_dblStopOrderPrice,
                    l_dblWLimitPrice,
                    l_wLimitExecCondType,
                    l_request.wlimitEnableStatusDiv,
                    l_request.expirationDateType,
                    l_blnEveningSessionCarryOverFlag);
                       
            //1.7 �⏕�������擾����B
            WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)this.getSubAccount();

            //1.8 �敨�ԍϒ��������̔����R�������{����B
            OrderValidationResult l_result =
                l_orderManager.validateFuturesChangeSettleContractOrder(l_subAccount, l_ifoOrderSpec);
            if (l_result.getProcessingResult().isFailedResult())
            {
                log.debug("ProcessingResult() = " + l_result.getProcessingResult());
                throw new WEB3BusinessLayerException(
                    l_result.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //1.9 ���ʂ̍��v���ʂ��擾����B
            double l_dblTotalQuantity = l_ifoOrderSpec.getAfterChangeTotalQuantity();
            log.debug("���ʂ̍��v���ʂ��擾���܂����B" + l_dblTotalQuantity);

            //1.10 ���ʃI�u�W�F�N�g���擾����B
            long l_lngCloseMarginContractUnits = Long.parseLong(l_request.closeMarginContractUnits[0].id);
            WEB3IfoContractImpl l_ifoContractImpl = new WEB3IfoContractImpl(l_lngCloseMarginContractUnits);

            //1.11 �敨OP��������I�u�W�F�N�g���擾����B
            WEB3IfoTradedProductImpl l_tradedProduct = (WEB3IfoTradedProductImpl)l_ifoContractImpl.getTradedProduct();
            log.debug("19. �敨OP��������I�u�W�F�N�g���擾���܂����B����ID = " + l_tradedProduct.getTradedProductId());

            //1.12�@@���������擾����
            WEB3IfoProductQuote l_currentInfo = l_tradedProduct.getCurrentInfo(null);

            //1.13 �萔���I�u�W�F�N�g�𐶐�����B
            WEB3GentradeCommission l_gentradeCommission = new WEB3GentradeCommission();

            //1.14 �v���p�e�B�Z�b�g(*1)
            SubAccountRow l_subAccountRow =(SubAccountRow) l_subAccount.getDataSourceObject() ;
            //�萔��.�����`���l�����Z�b�g
            l_gentradeCommission.setOrderChannel(this.getLoginChannel());
            log.debug("�萔��.�����`���l�� = " + this.getLoginChannel());
            //�萔��.�،���ЃR�[�g���Z�b�g����B
            String l_strInstitutionCode = l_subAccountRow.getInstitutionCode();
            l_gentradeCommission.setInstitutionCode(l_strInstitutionCode);
            log.debug("�萔��.�،���ЃR�[�g = " + l_strInstitutionCode);
            //�萔��.���XID���Z�b�g����B
            l_gentradeCommission.setBranchId(l_subAccountRow.getBranchId());
            log.debug("�萔��.���XID = " + l_subAccountRow.getBranchId());
            //�萔��.���������Z�b�g����B
            l_gentradeCommission.setOrderBizDate(new Timestamp(l_datOrderBizDate.getTime()));
            log.debug("�萔��.������ = " + l_datOrderBizDate);
            //�萔��.����R�[�h�iSONAR�j���Z�b�g����B
            l_gentradeCommission.setSonarTradedCode(WEB3TransactionTypeSONARDef.SETTLE_CONTRACT);
            log.debug("�萔��.����R�[�h�iSONAR�j = " + WEB3TransactionTypeSONARDef.SETTLE_CONTRACT);
            //�萔��.�萔�����i�R�[�h���Z�b�g����B
            l_gentradeCommission.setCommissionProductCode(WEB3CommisionProductCodeDef.INDEX_FUTURES);
            log.debug("�萔��.�萔�����i�R�[�h = " + WEB3CommisionProductCodeDef.INDEX_FUTURES);
            //�萔��.�ٍϋ敪���Z�b�g����B
            l_gentradeCommission.setPayType(WEB3PayTypeDef.OTHER);
            log.debug("�萔��.�ٍϋ敪 = " + WEB3PayTypeDef.OTHER);
            //�萔��.�������`���l�����Z�b�g����B
            l_gentradeCommission.setOrgOrderChannel(l_ifoOrderUnitRow.getOrderChanel());
            log.debug("�萔��.�������`���l�� = " + l_ifoOrderUnitRow.getOrderChanel());
            //�萔��.�������萔��NO���Z�b�g����B
            l_gentradeCommission.setOrgCommissionNo(l_ifoOrderUnitRow.getCommTblNo());
            log.debug("�萔��.�������萔��NO = " + l_ifoOrderUnitRow.getCommTblNo());
            //�萔��.�������萔��NO.�}�Ԃ��Z�b�g����B
            l_gentradeCommission.setOrgCommissionRevNo(l_ifoOrderUnitRow.getCommTblSubNo());
            log.debug("�萔��.�������萔��NO.�}�� = " + l_ifoOrderUnitRow.getCommTblSubNo());
            //�萔��.is�w�l���Z�b�g����B
            l_gentradeCommission.setIsLimitPrice(l_blnIsLimitPrice);
            log.debug("�萔��.is�w�l = " + l_gentradeCommission.isLimitPrice());
            
            //�萔��.�����Y�����R�[�h = ����.get����().get�����Y�����R�[�h(*2)
            l_gentradeCommission.setUnderlyingProductCode(
                ((WEB3IfoProductImpl)l_ifoContractImpl.getProduct()).getUnderlyingProductCode());
            
            //�萔��.���v��敪 = �����P��.���v��敪
            l_gentradeCommission.setDayTradeType(l_ifoOrderUnitRow.getDayTradeType());
            
            //�萔��.���� = �ԍϒ������e.getAfterChangeTotalQuantity()
            l_gentradeCommission.setQuantity(l_dblTotalQuantity);
            
            
            //1.15 �����iSideEnum��`�l�j���擾����B
            SideEnum l_side = null;
            if (SideEnum.BUY.equals(l_orderUnit.getSide()))
            {
                l_side = SideEnum.SELL;
            }
            else if (SideEnum.SELL.equals(l_orderUnit.getSide()))
            {
                l_side = SideEnum.BUY;
            }
            log.debug("�����iSideEnum��`�l�j���擾���܂����B=" + l_side);

            //1.16 ���v��萔�ʂ��擾����B
            double l_dblExecutedQuantity = l_orderUnit.getExecutedQuantity();
            if(Double.isNaN(l_dblExecutedQuantity))
            {
                l_dblExecutedQuantity = 0;
            }
            
            //1.17 calc�������T�Z���ϑ��v(�萔��, double, SubAccount, �敨OP�������, SettleContractEntry[], double, SideEnum, double, long, boolean)
            //  [calc�������T�Z���ϑ��v()�Ɏw�肷�����]
            //  �萔���F�@@�萔���I�u�W�F�N�g
            //  �w�l�F�@@���N�G�X�g�f�[�^.�����P��
            //  �⏕�����F�@@�⏕�����I�u�W�F�N�g
            //  �敨OP��������F�@@�敨OP��������I�u�W�F�N�g
            //  �ԍό��ʃG���g��[]�F  create�ԍό��ʃG���g���̖߂�l
            //  ���ʁF �ԍϒ������e.getAfterChangeTotalQuantity()
            //  �����F �i�ȉ��̂Ƃ���j
            //      �����P��.getSide() = �h���h�̏ꍇ�A�h���h
            //      �����P��.getSide() = �h���h�̏ꍇ�A�h���h
            //  ��萔�ʁF�@@�����P��.getExecutedQuantity()
            //  �����P��ID�F �����P��.getOrderUnitId()
            //  isSkip���z�`�F�b�N�F  false
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoResult = null;
            l_ifoResult = l_orderManager.calcChangeEstimateSettlementIncome(
                l_gentradeCommission,
                l_dblLimitPrice,
                l_subAccount,                   
                l_tradedProduct,
                l_eqOrderEntry,
                l_dblTotalQuantity,
                l_side,
                l_dblExecutedQuantity,
                l_lngOrderUnitId,
                false);

            //1.18 �i�敨�����ԍσT�[�r�X�jvalidate�����Q�i�Q�Ɓj
            //1.2 ���X�|���X�f�[�^�𐶐�����B
            WEB3FuturesCloseMarginChangeConfirmResponse l_response =
                (WEB3FuturesCloseMarginChangeConfirmResponse)l_request.createResponse();
            
            //1.3 �ԍό��ʃG���g���̗v�f��LOOP
            WEB3FuturesOptionsContractUnit l_contractUnit = null;
            WEB3IfoContractImpl l_ifoContract = null;
            ArrayList l_list = new ArrayList();
            for (int i = 0; i < l_eqOrderEntry.length; i++)
            {
                //1.3.1 ���ʖ��׃I�u�W�F�N�g�𐶐�����B
                l_contractUnit = new WEB3FuturesOptionsContractUnit();

                //1.3.2 ����ID���擾����B
                long l_lngContractId = l_eqOrderEntry[i].getContractId();

                //1.3.3 �ԍϐ��ʂ��擾����B
                double l_dblQuantity = l_eqOrderEntry[i].getQuantity();
                if(Double.isNaN(l_dblQuantity))
                {
                    l_dblQuantity = 0;
                }
                
                //1.3.4 �敨OP����(long)
                l_ifoContract = new WEB3IfoContractImpl(l_lngContractId);

                //1.3.5 ���N�������擾����B
                Date l_datOpenDate = l_ifoContract.getOpenDate();

                //1.3.6 ���P�����擾����B
                double l_dblContractPrice = l_ifoContract.getContractPrice();
                if(Double.isNaN(l_dblContractPrice))
                {
                    l_dblContractPrice = 0;
                }
                
                //1.3.7 ����������擾����B
                double l_dblContractExecutedAmount = l_ifoContract.getContractExecutedAmount(l_dblQuantity);
                if(Double.isNaN(l_dblContractExecutedAmount))
                {
                    l_dblContractExecutedAmount = 0;
                }
                
                //���萔�����擾����
                BigDecimal l_bdContractCommission =
                    new BigDecimal(l_ifoContract.getContractCommission(l_dblQuantity, l_lngOrderUnitId) + "");
                BigDecimal l_bdContractCommissionConsumptionTax =
                    new BigDecimal(l_ifoContract.getContractCommissionConsumptionTax(l_dblQuantity, l_lngOrderUnitId) + "");
                l_bdContractCommission = l_bdContractCommission.add(l_bdContractCommissionConsumptionTax);
                double l_dblContractCommission = l_bdContractCommission.doubleValue();

                //�������擾����
                double l_dblPrice = 0D;
                if(l_currentInfo != null)
                {
                    l_dblPrice = l_currentInfo.getCurrentPrice();
                }

                //1.3.8 �]�����v���擾����
                double l_dblIncome = l_ifoContract.getEvaluateIncome(l_dblPrice,l_dblQuantity);
                BigDecimal l_bdIncome = new BigDecimal(l_dblIncome + "");

                //1.3.9 getQuantity()
                double l_dblGetQuantity = l_ifoContract.getQuantity();
                if(Double.isNaN(l_dblGetQuantity))
                {
                    l_dblGetQuantity = 0;
                }
                
                //1.3.10 get�ԍϖ��ϐ���(long)(�敨OP����::get�ԍϖ��ϐ���)
                //  [����] 
                //  �����P��ID�F �����P��.�����P��ID 
                double l_dblClosingExecuteContractCnt = l_ifoContract.getClosingExecuteContractCnt(l_orderUnit.getOrderUnitId());

                //1.3.11 (*1)�v���p�e�B�Z�b�g
                l_contractUnit.id                    = String.valueOf(l_lngContractId);
                l_contractUnit.openDate              = WEB3DateUtility.toDay(l_datOpenDate);
                l_contractUnit.contractPrice         = WEB3StringTypeUtility.formatNumber(l_dblContractPrice);
                l_contractUnit.contractQuantity      = WEB3StringTypeUtility.formatNumber(l_dblGetQuantity);
                l_contractUnit.contractExecPrice     = WEB3StringTypeUtility.formatNumber(l_dblContractExecutedAmount);
                l_contractUnit.contractCommission    = WEB3StringTypeUtility.formatNumber(l_dblContractCommission);
                l_contractUnit.income                = WEB3StringTypeUtility.formatNumber(l_dblIncome);
                l_contractUnit.incomeCost            = WEB3StringTypeUtility.formatNumber(l_bdIncome.subtract(l_bdContractCommission).doubleValue());
                l_contractUnit.contractOrderQuantity = WEB3StringTypeUtility.formatNumber(l_dblQuantity);
                l_contractUnit.contractExecQuantity  = WEB3StringTypeUtility.formatNumber(l_dblClosingExecuteContractCnt);
                l_contractUnit.settlePriority        = String.valueOf(i + 1);
                l_contractUnit.sessionType           =
                    ((IfoContractRow)l_ifoContract.getDataSourceObject()).getSessionType();

                l_list.add(l_contractUnit);
            }
            WEB3FuturesOptionsContractUnit[] l_contractUnits = (WEB3FuturesOptionsContractUnit[])
            l_list.toArray(new WEB3FuturesOptionsContractUnit[l_list.size()]);

            //1.4 �s��Ǌԋ߂̎w����z��Ŏ擾����B
            WEB3GentradeBranch l_gentradeBranch =
                (WEB3GentradeBranch)l_subAccount.getMainAccount().getBranch();
            String[] l_strTradeCloseSuspensions =
                WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(
                    l_gentradeBranch,
                    WEB3FuturesOptionDivDef.FUTURES);

            //1.5 �T�Z��n������ڂɃZ�b�g����Ă���T�Z���ϑ��v���擾����B
            double l_EstimateDeliveryAmount = l_ifoResult.getEstimateDeliveryAmount();

            //1.6 get�v�Z�P��( )(�敨OP�T�Z��n����v�Z����::get�v�Z�P��)
            double l_dblCalcUnitPrice = l_ifoResult.getCalcUnitPrice();
            
            //1.7 get�萔���R�[�X( )(�敨OP�T�Z��n����v�Z����::get�萔���R�[�X)
            String l_strCommissionCourse = l_ifoResult.getCommissionCourse();

            //1.8 get�萔��( )(�敨OP�T�Z��n����v�Z����::get�萔��)
            double l_dblCommission = l_ifoResult.getCommission();

            //1.9 get�萔�������( )(�敨OP�T�Z��n����v�Z����::get�萔�������)
            double l_dblCommissionTax = l_ifoResult.getCommissionTax();

            //1.10 (*2)�v���p�e�B�Z�b�g
            l_response.contractUnits = l_contractUnits;
            l_response.estimatedSettleIncome = WEB3StringTypeUtility.formatNumber(l_EstimateDeliveryAmount);
            l_response.commissionCourse = l_strCommissionCourse;
            l_response.commission = WEB3StringTypeUtility.formatNumber(l_dblCommission);
            l_response.commissionConsumptionTax = WEB3StringTypeUtility.formatNumber(l_dblCommissionTax);
            l_response.messageSuspension = l_strTradeCloseSuspensions;
            l_response.checkPrice = WEB3StringTypeUtility.formatNumber(l_dblCalcUnitPrice);
            l_response.checkDate = WEB3DateUtility.toDay(l_datOrderBizDate);
            //���X�|���X.�����L������ = �ԍϒ������e.����������
            l_response.expirationDate = l_ifoOrderSpec.getChangeExpirationDate();

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
    }

    /**
     * (submit����)<BR>
     * �����w���敨�̒����ԍϒ�����o�^����B<BR>
     * <BR>
     * �u�i�敨�����ԍσT�[�r�X�jsubmit�����v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �����w���敨�����ԍϊ������N�G�X�g
     * @@return WEB3FuturesCloseMarginChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A8B0BD021C
     */
    protected WEB3FuturesCloseMarginChangeCompleteResponse submitOrder(WEB3FuturesCloseMarginChangeCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME= "submitOrder(WEB3FuturesCloseMarginChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.debug("�V�[�P���X�}�u�i�敨�����ԍσT�[�r�X�jsubmit�����P�v�Q��");
        try
        {
            //1.1 ���N�G�X�g�f�[�^�̐��������`�F�b�N����B
            log.debug("1. ���N�G�X�g�f�[�^�̐��������`�F�b�N����B");
            l_request.validate();

            //1.2 �����I�u�W�F�N�g���擾����B
            long l_lngID = Long.parseLong(l_request.id);
            IfoOrderImpl l_ifoOrderImpl = new IfoOrderImpl(l_lngID);
            log.debug("2.�����I�u�W�F�N�g���擾���܂����B����ID = " + l_lngID);

            //1.3 �����P�ʂ��擾����B ���Y���\�b�h�Ŏ擾�����z���0�Ԗڂ̗v�f
            OrderUnit[] l_orderUnits = l_ifoOrderImpl.getOrderUnits();
            OrderUnit l_orderUnit = l_orderUnits[0];
            IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();
            log.debug("3.�����P�ʂ��擾���܂����B�����P��ID = " + l_orderUnits[0].getOrderUnitId());

            //1.4 ���Ϗ��ʂŃ\�[�g���A�ԍό��ʃG���g���̔z����쐬����B
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3FuturesOrderManagerImpl l_orderManager = null;
            l_orderManager =
                (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            //�����h�c
            long l_lngGetOrderId = l_orderUnit.getOrderId();
            //�����P�ʂh�c
            long l_lngGetOrderUnitId = l_orderUnit.getOrderUnitId();
            //��������
            double l_dblOrderQuantity = 0D;
            if (l_request.futOrderQuantity != null)
            {
                l_dblOrderQuantity = Double.parseDouble(l_request.futOrderQuantity);
            }
            //�����P�����擾����B            
            double l_dblLimitPrice = 0D;
            boolean l_blnIsLimitPrice ;
            if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_request.orderPriceDiv))
            {
                //���s
                l_dblLimitPrice = 0D;
                l_blnIsLimitPrice = false;
            }
            else
            {
                //�w�l
                l_dblLimitPrice = Double.parseDouble(l_request.limitPrice);
                l_blnIsLimitPrice = true;
            } 
            SettleContractEntry[] l_eqOrderEntry =
                l_orderManager.createSettleContractEntry(
                    l_lngGetOrderUnitId,                      //�����P��ID = �i�擾���������P��.getOrderUnitId()�j
                    l_dblOrderQuantity,                       //�������� = ���N�G�X�g�f�[�^.��������
                    l_request.closeMarginContractUnits);      //����ID[] = ���N�G�X�g�f�[�^.�ԍό���[]
            
            //1.5 ���������擾����B
            Date l_datOrderBizDate = null;
            if (l_request.checkDate == null)
            {
                l_request.checkDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            }
            l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
            
            //�������s����
            IfoOrderExecutionConditionType l_ifoOrderExecutionConditionType = 
                WEB3IfoDataAdapter.getExecutionConditionType(l_request.execCondType);

            //OP�����}�l�[�W��
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderManagerImpl =
                (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

            //�敨OP����
            //(*1)�敨OP�v���_�N�g�}�l�[�W��.getProduct(�����P��.����ID)�ɂĎ擾
            WEB3IfoProductManagerImpl l_productMgr =
                (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();

            WEB3IfoProductImpl l_ifoProductImpl = null;
            try
            {
                l_ifoProductImpl =
                    (WEB3IfoProductImpl)l_productMgr.getProduct(l_ifoOrderUnitRow.getProductId());
            }
            catch (NotFoundException l_ex)
            {
                log.error(STR_METHOD_NAME,l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //�s��
            Market l_market = l_ifoProductImpl.getPrimaryMarket();

            //�����������F
            //���N�G�X�g�f�[�^.�����L������ != null�̏ꍇ�AOP�����}�l�[�W��.get�����L������(
            //���N�G�X�g�f�[�^.�����L������,�敨OP����(*1).�����R�[�h,�s��.getMarketCode(),�h�敨�h)�̖߂�l
            //���N�G�X�g�f�[�^.�����L������ == null�̏ꍇ�Aget������()�̖߂�l
            Date l_datChangeExpirationDate = null;
            if (l_request.expirationDate != null)
            {
                l_datChangeExpirationDate = l_orderManagerImpl.getExpirationDate(
                    l_request.expirationDate,
                    l_ifoProductImpl.getProductCode(),
                    l_market.getMarketCode(),
                    WEB3FuturesOptionDivDef.FUTURES);
            }
            else 
            {
                l_datChangeExpirationDate = WEB3DateUtility.toDay(l_datOrderBizDate);
            }
            
            //�����������Z�q
            String l_strOrderCondOperator = null;
            
            //�t�w�l��l
            double l_dblStopOrderPrice = 0D;
            
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_strOrderCondOperator = l_request.stopOrderCondOperator;
                l_dblStopOrderPrice = Double.parseDouble(l_request.stopOrderCondPrice);
            }
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_strOrderCondOperator = l_request.wlimitOrderCondOperator;
                l_dblStopOrderPrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
            }
            
            //�iW�w�l�j�����w�l�F�@@���N�G�X�g�f�[�^.W�w�l�p�����P�� �B
            double l_dblWLimitPrice = 0D;
           if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_request.wLimitOrderPriceDiv)
                && WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {                
                l_dblWLimitPrice = Double.parseDouble(l_request.wLimitPrice);
            }
            else
            {
                l_dblWLimitPrice = 0D;
            }
            
            //(W�w�l)���s����
            IfoOrderExecutionConditionType l_wLimitExecCondType =
                WEB3IfoDataAdapter.getExecutionConditionType(l_request.wlimitExecCondType);
            
            //�[��O�J�z�Ώۃt���O�F�@@�敨OP�f�[�^�A�_�v�^.get�[��O
            //�J�z�Ώۃt���O(���N�G�X�g�f�[�^.���������敪, �����P��.���XID)�̖߂�l
            boolean l_blnEveningSessionCarryOverFlag =
                WEB3IfoDataAdapter.getEveningSessionCarryOverFlag(
                    l_request.expirationDateType,
                    l_ifoOrderUnitRow.getBranchId());
            //1.6 �ԍϒ������e���쐬����B 
            WEB3IfoChangeSettleContractOrderSpec l_ifoOrderSpec = 
                WEB3IfoChangeSettleContractOrderSpec.createIfoChangeSettleContractOrderSpec(
                    l_lngGetOrderId,
                    l_lngGetOrderUnitId,
                    l_dblLimitPrice,
                    l_eqOrderEntry,
                    l_ifoOrderExecutionConditionType,
                    l_datChangeExpirationDate,
                    l_datOrderBizDate,
                    l_request.orderCondType,
                    l_strOrderCondOperator,
                    WEB3StopBasePriceTypeDef.DEFAULT,
                    l_dblStopOrderPrice,
                    l_dblWLimitPrice,
                    l_wLimitExecCondType,
                    l_request.wlimitEnableStatusDiv,
                    l_request.expirationDateType,
                    l_blnEveningSessionCarryOverFlag);

            //1.7 �⏕�������擾����B
            WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)this.getSubAccount();

            //1.8 �敨�ԍϒ��������̔����R�������{����B
            OrderValidationResult l_result =
                l_orderManager.validateFuturesChangeSettleContractOrder(
                    l_subAccount,
                    l_ifoOrderSpec);
            if (l_result.getProcessingResult().isFailedResult())
            {
                log.debug("ProcessingResult() = " + l_result.getProcessingResult());
                throw new WEB3BusinessLayerException(
                    l_result.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //1.9 ���ʂ̍��v���ʂ��擾����B
            double l_dblTotalQuantity = l_ifoOrderSpec.getAfterChangeTotalQuantity();
            if(Double.isNaN(l_dblTotalQuantity))
            {
                l_dblTotalQuantity = 0;
            }
            log.debug("9. ���ʂ̍��v���ʂ��擾���܂����B���ʂ̍��v���� = " + l_dblTotalQuantity);

            //1.10 ���ʃI�u�W�F�N�g���擾����
            long l_lngCloseMarginContractUnits = Long.parseLong(l_request.closeMarginContractUnits[0].id);
            WEB3IfoContractImpl l_ifoContractImpl = new WEB3IfoContractImpl(l_lngCloseMarginContractUnits);
            log.debug("10. ���ʃI�u�W�F�N�g���擾���܂����B����ID = " + l_lngCloseMarginContractUnits);

            //1.11 �敨OP��������I�u�W�F�N�g���擾����B
            WEB3IfoTradedProductImpl l_tradedProduct = (WEB3IfoTradedProductImpl)l_ifoContractImpl.getTradedProduct();
            log.debug("11. �敨OP��������I�u�W�F�N�g���擾���܂����B����ID = " + l_tradedProduct.getTradedProductId());

            //1.12 �萔���I�u�W�F�N�g�𐶐�����B
            WEB3GentradeCommission l_gentradeCommission = new WEB3GentradeCommission();
            log.debug("12. �萔���I�u�W�F�N�g�𐶐����܂����B�B");

            //1.13 �v���p�e�B�Z�b�g(*1)
            SubAccountRow l_subAccountRow =(SubAccountRow) l_subAccount.getDataSourceObject() ;
            log.debug("13. �萔���I�u�W�F�N�g�ɃA�N�Z�T���\�b�h���g�p���v���p�e�B���Z�b�g����B");
            //�萔��.�����`���l�����Z�b�g
            l_gentradeCommission.setOrderChannel(this.getLoginChannel());
            log.debug("�萔��.�����`���l�� = " + this.getLoginChannel());
            //�萔��.�،���ЃR�[�g���Z�b�g����B
            String l_strInstitutionCode = String.valueOf(l_subAccountRow.getInstitutionCode());
            l_gentradeCommission.setInstitutionCode(l_strInstitutionCode);
            log.debug("�萔��.�،���ЃR�[�g = " + l_strInstitutionCode);
            //�萔��.���XID���Z�b�g����B
            l_gentradeCommission.setBranchId(l_subAccountRow.getBranchId());
            log.debug("�萔��.���XID = " + l_subAccountRow.getBranchId());
            //�萔��.���������Z�b�g����B
            Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            l_gentradeCommission.setOrderBizDate(new Timestamp(l_datBizDate.getTime()));
            log.debug("�萔��.������ = " + l_datOrderBizDate);
            //�萔��.����R�[�h�iSONAR�j���Z�b�g����B
            l_gentradeCommission.setSonarTradedCode(WEB3TransactionTypeSONARDef.SETTLE_CONTRACT);
            log.debug("�萔��.����R�[�h�iSONAR�j = " + WEB3TransactionTypeSONARDef.SETTLE_CONTRACT);
            //�萔��.�萔�����i�R�[�h���Z�b�g����B
            l_gentradeCommission.setCommissionProductCode(WEB3CommisionProductCodeDef.INDEX_FUTURES);
            log.debug("�萔��.�萔�����i�R�[�h = " + WEB3CommisionProductCodeDef.INDEX_FUTURES);
            //�萔��.�ٍϋ敪���Z�b�g����B
            l_gentradeCommission.setPayType(WEB3PayTypeDef.OTHER);
            log.debug("�萔��.�ٍϋ敪 = " + WEB3PayTypeDef.OTHER);
            //�萔��.�������`���l�����Z�b�g����B
            l_gentradeCommission.setOrgOrderChannel(l_ifoOrderUnitRow.getOrderChanel());
            log.debug("�萔��.�������`���l�� = " + l_ifoOrderUnitRow.getOrderChanel());
            //�萔��.�������萔��NO���Z�b�g����B
            l_gentradeCommission.setOrgCommissionNo(l_ifoOrderUnitRow.getCommTblNo());
            log.debug("�萔��.�������萔��NO = " + l_ifoOrderUnitRow.getCommTblNo());
            //�萔��.�������萔��NO.�}�Ԃ��Z�b�g����B
            l_gentradeCommission.setOrgCommissionRevNo(l_ifoOrderUnitRow.getCommTblSubNo());
            log.debug("�萔��.�������萔��NO.�}�� = " + l_ifoOrderUnitRow.getCommTblSubNo());
            //�萔��.is�w�l���Z�b�g����B
            l_gentradeCommission.setIsLimitPrice(l_blnIsLimitPrice);
            log.debug("�萔��.is�w�l = " + l_gentradeCommission.isLimitPrice());
            
            //�萔��.�����Y�����R�[�h = ����.get����().get�����Y�����R�[�h(*2)
            l_gentradeCommission.setUnderlyingProductCode(
                ((WEB3IfoProductImpl)l_ifoContractImpl.getProduct()).getUnderlyingProductCode());

            //�萔��.���v��敪 = �����P��.���v��敪
            l_gentradeCommission.setDayTradeType(l_ifoOrderUnitRow.getDayTradeType());

            //�萔��.���� = �ԍϒ������e.getAfterChangeTotalQuantity()
            l_gentradeCommission.setQuantity(l_dblTotalQuantity);
            
            //1.14 �����iSideEnum��`�l�j���擾����B
            SideEnum l_side = null;
            if (SideEnum.BUY.equals(l_orderUnit.getSide()))
            {
                l_side = SideEnum.SELL;
            }
            else if (SideEnum.SELL.equals(l_orderUnit.getSide()))
            {
                l_side = SideEnum.BUY;
            }
            log.debug("�����iSideEnum��`�l�j���擾���܂����B" + l_side);

            //1.15 ���v��萔�ʂ��擾����B
            double l_dblExecutedQuantity = l_orderUnit.getExecutedQuantity();
            if(Double.isNaN(l_dblExecutedQuantity))
            {
                l_dblExecutedQuantity = 0;
            }
            log.debug("���v��萔�ʂ��擾���܂����B" + l_dblExecutedQuantity);

            //1.16 �������̊T�Z���ϑ��v���v�Z����B
            //  [calc�������T�Z���ϑ��v()�Ɏw�肷�����]
            //  �萔���F�@@�萔���I�u�W�F�N�g
            //  �w�l�F�@@
            //     ���N�G�X�g�f�[�^.�m�F���P��!=null�̏ꍇ�A���N�G�X�g�f�[�^.�m�F���P��
            //     ���N�G�X�g�f�[�^.�m�F���P��==null�̏ꍇ�A���N�G�X�g�f�[�^.�����P��(*1)
            //    �i*1�@@���N�G�X�g�f�[�^.�����P��==null�̏ꍇ�̓[�����Z�b�g�B�j
            //  �⏕�����F�@@�⏕�����I�u�W�F�N�g
            //  �敨OP��������F�@@�敨OP��������I�u�W�F�N�g
            //  �ԍό��ʃG���g��[]�F   create�ԍό��ʃG���g���̖߂�l
            //  ���ʁF �ԍϒ������e.getAfterChangeTotalQuantity()
            //  �����F �i�ȉ��̂Ƃ���j
            //      �����P��.getSide() = �h���h�̏ꍇ�A�h���h
            //      �����P��.getSide() = �h���h�̏ꍇ�A�h���h
            //  ��萔�ʁF�@@�����P��.getExecutedQuantity()
            //  �����P��ID�F �����P��.getOrderUnitId()
            //  isSkip���z�`�F�b�N�F  false
            //�m�F���P��
            double l_dblCheckPrice = 0D;
            if (l_request.checkPrice != null)
            {
                l_dblCheckPrice = Double.parseDouble(l_request.checkPrice);
            }
            else
            {
                l_dblCheckPrice = l_dblLimitPrice;
            }
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoResult = l_orderManager.calcChangeEstimateSettlementIncome(
                l_gentradeCommission,
                l_dblCheckPrice,
                l_subAccount,
                l_tradedProduct,
                l_eqOrderEntry,
                l_dblTotalQuantity, 
                l_side,                         
                l_dblExecutedQuantity,
                l_lngGetOrderUnitId,
                false);                            


            //1.3 �敨OP�ԍϒ����X�V�C���^�Z�v�^(�ԍϒ������e)
            WEB3IfoSettleContractChangeUpdateInterceptor l_ifoUpdateInterceptor =
                new WEB3IfoSettleContractChangeUpdateInterceptor(l_ifoOrderSpec);

            //1.4  (*1) �v���p�e�B�Z�b�g
            //�C���^�Z�v�^.�萔��=�萔���I�u�W�F�N�g���Z�b�g����B
            l_ifoUpdateInterceptor.setCommision(l_gentradeCommission);
            //�C���^�Z�v�^.�T�Z��n����v�Z����
            l_ifoUpdateInterceptor.setEstimateDeliveryAmountCalcResult(l_ifoResult);
            //�C���^�Z�v�^.��������
            l_ifoUpdateInterceptor.setOrderCond(l_request.orderCondType);
            //�C���^�Z�v�^.�����������Z�q
            //�C���^�Z�v�^.�t�w�l��l
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_ifoUpdateInterceptor.setOrderCondOperator(l_request.stopOrderCondOperator);
                l_ifoUpdateInterceptor.setStopOrderBasePrice(Double.parseDouble(l_request.stopOrderCondPrice));
            }
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_ifoUpdateInterceptor.setOrderCondOperator(l_request.wlimitOrderCondOperator);
                l_ifoUpdateInterceptor.setStopOrderBasePrice(Double.parseDouble(l_request.wlimitOrderCondPrice));
            }
            //�C���^�Z�v�^.�t�w�l��l�^�C�v
            l_ifoUpdateInterceptor.setStopOrderBasePriceType(WEB3StopBasePriceTypeDef.DEFAULT);
            //�C���^�Z�v�^.�iW�w�l�j�����w�l
            l_ifoUpdateInterceptor.setWLimitPriceChange(l_ifoOrderSpec.getWLimitPriceChange());
            //�C���^�Z�v�^.�����ID
            Trader l_trader = this.getTrader();   
            long l_lngTraderId = 0;
            if (l_trader != null)
            {
                l_lngTraderId = l_trader.getTraderId();
            }
            l_ifoUpdateInterceptor.setTraderId(l_lngTraderId);
            
            //�C���^�Z�v�^.�����o�H�敪
			OpLoginSecurityService l_opLoginSec =
				(OpLoginSecurityService) Services.getService(
					OpLoginSecurityService.class);
			String l_strOrderRootDiv =
				l_opLoginSec.getSessionProperty(
					WEB3SessionAttributeDef.ORDER_ROOT_DIV);

			l_ifoUpdateInterceptor.setOrderRootDiv(l_strOrderRootDiv);

            //1.5 �C���^�Z�v�^���Z�b�g����B
            l_orderManager.setThreadLocalPersistenceEventInterceptor(l_ifoUpdateInterceptor);

            //1.6 �ԍϒ��������X�V�������s���B
            OrderSubmissionResult l_submissionResult = l_orderManager.submitChangeSettleContractOrder(
                l_subAccount, 
                l_ifoOrderSpec, 
                l_request.password, 
                true);
            if (l_submissionResult.getProcessingResult().isFailedResult())
            {
                log.debug("ProcessingResult() = " + l_submissionResult.getProcessingResult());
                throw new WEB3BusinessLayerException(
                    l_submissionResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //is�\�񒍕��m�F�v(IfoOrderUnit)
            IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_orderUnit;
            boolean l_blnIsReserveOrderExist = l_orderManager.isReserveOrderExist(l_ifoOrderUnit);

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

            //1.7 ���X�|���X�f�[�^�̐���
            WEB3GenResponse l_genResponse = l_request.createResponse();
            WEB3FuturesCloseMarginChangeCompleteResponse l_response = (WEB3FuturesCloseMarginChangeCompleteResponse)l_genResponse;

            //1.8 (*2) �v���p�e�B�Z�b�g
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

            log.debug("l_response.lastUpdateTimestamp = " + l_response.lastUpdatedTimestamp);
            log.debug("l_response.orderActionId = "+ l_response.orderActionId);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
    }
}
@
