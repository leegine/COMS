head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOrderManagerImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨�����}�l�[�W���N���X(WEB3FuturesOrderManagerImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/8/05 ������ (���u) �V�K�쐬
Revesion History : 2006/7/27 �юu�� (���u) �d�l�ύX ���f��No.483,486,489,492
Revesion History : 2006/8/16 �юu�� (���u) �d�l�ύX ���f��No.546
Revesion History : 2006/9/19 �s�p (���u) �d�l�ύX ���f��No.554
Revesion History : 2006/10/06 �s�p (���u) �d�l�ύX ���f��No.563
Revesion History : 2007/01/25 ��іQ (���u) �d�l�ύX ���f��No.619,No.620.No.621,No.622,No,624
Revesion History : 2007/06/11 ��іQ (���u) ���f��No.699�C732
Revesion History : 2007/06/21 ��іQ (���u) ���f��No.678�C707
Revesion History : 2008/03/12 ����(���u) ���f�� 836,847
Revesion History : 2008/08/18 ���z(���u) IFO�����_�Ή�
*/
package webbroker3.ifo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoContractImpl;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoContractOpenOrderUnitImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�敨�����}�l�[�W��)<BR>
 * �敨�����}�l�[�W���N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3FuturesOrderManagerImpl extends WEB3OptionOrderManagerImpl
{
    /**
    * ���O���[�e�B���e�B<BR>
    */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesOrderManagerImpl.class);

    /**
     * @@roseuid 40F7B156037A
     */
    public WEB3FuturesOrderManagerImpl()
    {

    }

    /**
     * (calc�T�Z���ϑ��v)<BR>
     * �T�Z���ϑ��v���Z�o���ĕԋp����B<BR>
     * <BR>
     * this.calc�T�Z���ϑ��v()�ɏ������Ϗ��idelegate�j����B<BR>
     * <BR>
     * [����] �萔���F�@@�p�����[�^.�萔��<BR>
     * �w�l�F�@@�p�����[�^.�w�l<BR>
     * �⏕�����F�@@�p�����[�^.�⏕����<BR>
     * �敨OP��������F�@@�p�����[�^.�敨OP�������<BR>
     * �ԍό��ʃG���g��[]�F�@@�p�����[�^.�ԍό��ʃG���g��[]<BR>
     * ���ʁF�@@�p�����[�^.����<BR>
     * �����F�@@�p�����[�^.����<BR>
     * isSkip���z�`�F�b�N�F�@@�p�����[�^.isSkip���z�`�F�b�N<BR>
     * �敨OP���ʁF�@@null <BR>
     * <BR>
     * @@param l_commission - (�萔��)<BR>
     * @@param l_dblLimitPrice - �w�l<BR>
     * <BR>
     * 0���w�肳�ꂽ�ꍇ�́A�������v�Z�P���Ƃ��ė��p����B<BR>
     *
     * @@param l_subAccount - (�⏕����)<BR>
     * @@param l_futuresOptionTradedProduct - �敨OP�������<BR>
     * @@param l_settleContractEntry[] - (�ԍό��ʃG���g��[])<BR>
     * @@param l_dblQuantity - ����<BR>
     * @@param l_dealing - ����<BR>
     * �@@SideEnum.BUY�i���j<BR>
     * �@@SideEnum.SELL�i���j<BR>
     *
     * @@param l_blnIsSkipPriceCheck - (isSkip���z�`�F�b�N)<BR>
     * �v�Z���ʂ̑���ɂ��āA�Ó����`�F�b�N���X�L�b�v���邩�̃t���O�B<BR>
     * <BR>
     * �`�F�b�N���s���ꍇ��false�A�`�F�b�N���s��Ȃ��i�X�L�b�v����j�ꍇ��true���w�肷��B<BR>
     *
     * @@return webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult
     * @@throws WEB3BaseException
     * @@roseuid 40A31F10000C
     */
    public WEB3IfoEstimateDeliveryAmountCalcResult calcEstimateSettlementIncome(
        WEB3GentradeCommission l_commission,
        double l_dblLimitPrice,
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoTradedProductImpl l_futuresOptionTradedProduct,
        SettleContractEntry[] l_settleContractEntry,
        double l_dblQuantity,
        SideEnum l_dealing,
        boolean l_blnIsSkipPriceCheck)throws WEB3BaseException
    {
        return this.calcEstimateSettlementIncome(
            l_commission,
            l_dblLimitPrice,
            l_subAccount,
            l_futuresOptionTradedProduct,
            l_settleContractEntry,
            l_dblQuantity,
            l_dealing,
            l_blnIsSkipPriceCheck,
            null);
    }

    /**
     * (calc�T�Z���ϑ��v)<BR>
     * �T�Z���ϑ��v���Z�o���ĕԋp����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�敨�����jcalc�T�Z���ϑ��v�v�Q�ƁB<BR>
     * <BR>
     * @@param l_commission - (�萔��)<BR>
     * @@param l_dblLimitPrice - �w�l<BR>
     * <BR>
     * 0���w�肳�ꂽ�ꍇ�́A�������v�Z�P���Ƃ��ė��p����B<BR>
     *
     * @@param l_subAccount - (�⏕����)<BR>
     * @@param l_futuresOptionTradedProduct - �敨OP�������<BR>
     * @@param l_settleContractEntry[] - (�ԍό��ʃG���g��[])<BR>
     * @@param l_dblQuantity - ����<BR>
     * @@param l_dealing - ����<BR>
     * �@@SideEnum.BUY�i���j<BR>
     * �@@SideEnum.SELL�i���j<BR>
     *
     * @@param l_blnIsSkipPriceCheck - (isSkip���z�`�F�b�N)<BR>
     * �v�Z���ʂ̑���ɂ��āA�Ó����`�F�b�N���X�L�b�v���邩�̃t���O�B<BR>
     * <BR>
     * �`�F�b�N���s���ꍇ��false�A�`�F�b�N���s��Ȃ��i�X�L�b�v����j�ꍇ��true���w�肷��B<BR>
     * @@param l_ifoContractImpl - �敨OP����<BR>
     *
     * @@return webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult
     * @@roseuid 40A31F10000C
     */
    public WEB3IfoEstimateDeliveryAmountCalcResult calcEstimateSettlementIncome(
        WEB3GentradeCommission l_commission,
        double l_dblLimitPrice,
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoTradedProductImpl l_futuresOptionTradedProduct,
        SettleContractEntry[] l_settleContractEntry,
        double l_dblQuantity,
        SideEnum l_dealing,
        boolean l_blnIsSkipPriceCheck,
        WEB3IfoContractImpl l_ifoContractImpl)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "calcEstimateSettlementIncome(WEB3GentradeCommission, double, WEB3GentradeSubAccount, " 
          + "WEB3IfoTradedProductImpl, SettleContractEntry[], double, SideEnum, boolean, WEB3IfoContractImpl)";
        log.entering(STR_METHOD_NAME);
        
        //�߂�l�̊T�Z��n����v�Z���ʃI�u�W�F�N�g�𐶐�����B
        WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult = new WEB3IfoEstimateDeliveryAmountCalcResult();
        
        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

            //�敨OP�����R���ʃ`�F�b�N�I�u�W�F�N�g���쐬����
            WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations = (WEB3IfoOrderManagerReusableValidations) WEB3IfoOrderManagerReusableValidations.getInstance();

            //���s���v�Z�P��
            double l_dblmakeOrderCalcUnitPrice = 0D;
            
            WEB3IfoProductManagerImpl l_productManager = (WEB3IfoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();
            
            if (l_dblLimitPrice == 0D)
            {
                l_dblmakeOrderCalcUnitPrice = l_productManager.getCurrentPrice(l_futuresOptionTradedProduct);
                //�v�Z�P���Ɏ�����ݒ�
                l_ifoEstimateDeliveryAmountCalcResult.setCalcUnitPrice(l_dblmakeOrderCalcUnitPrice);
            }
            else
            {
                l_dblmakeOrderCalcUnitPrice = l_dblLimitPrice;
                //�v�Z�P���Ɏw�l��ݒ�
                l_ifoEstimateDeliveryAmountCalcResult.setCalcUnitPrice(l_dblLimitPrice);
            }

            //�v�Z�T�[�r�X�I�u�W�F�N�g���쐬����
            WEB3IfoBizLogicProvider l_ifoBizLogicProvider = null;
            l_ifoBizLogicProvider = (WEB3IfoBizLogicProvider) l_finApp.getTradingModule(ProductTypeEnum.IFO).getBizLogicProvider();

            //����������v�Z����               
            double l_dblCalcTurnOver = 0D;
            l_dblCalcTurnOver =
                l_ifoBizLogicProvider.calcTurnOver(
                    l_dblQuantity,
                    l_ifoEstimateDeliveryAmountCalcResult.getCalcUnitPrice(),
                    l_futuresOptionTradedProduct);
            l_ifoEstimateDeliveryAmountCalcResult.setRestraintTurnover(l_dblCalcTurnOver);
            
            //�萔���I�u�W�F�N�g�ɏ��o��v�Z�p������Z�b�g����B
            l_commission.setExpensesCalcAmount(l_dblCalcTurnOver);
            
            //isSkip���z�`�F�b�N == false�̏ꍇ�̂݁A����\������z�`�F�b�N���s��
            if (!l_blnIsSkipPriceCheck)
            {
                MainAccountRow l_mainAccountRow = (MainAccountRow) l_subAccount.getMainAccount().getDataSourceObject();
                l_ifoOrderManagerReusableValidations.validateOrderMaxAmount(
                    l_subAccount.getWeb3GenBranch(),
                    l_commission.getExpensesCalcAmount(),                
                    l_mainAccountRow.getAccountType(),
                    WEB3FuturesOptionDivDef.FUTURES);
            }
            
            //���������擾����B
            Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

            long l_lngContractId = 0L;
            double l_dblContractQuantity = 0D;
            double l_dblContractExecutedAmount = 0D;
            BigDecimal l_bdContractExecutedAmount = new BigDecimal("0");

            double l_dblCommission = 0D;
            BigDecimal l_bdCommission = new BigDecimal("0");

            double l_dblCommissionConsumptionTax = 0D;
            BigDecimal l_bdCommissionConsumptionTax = new BigDecimal("0");

            WEB3IfoContractImpl l_ifoContract = l_ifoContractImpl;
            
            //�ԍό��ʃG���g���̗v�f������LOOP����
            for (int i = 0; i < l_settleContractEntry.length; i++)
            {
                //�ԍϐ��ʂ̎擾
                l_dblContractQuantity = l_settleContractEntry[i].getQuantity();
                
                // ���Ύ���ȊO�i�p�����[�^.���� == null�j�̏ꍇ
                if (l_ifoContractImpl == null)
                {
                    //����ID�̎擾
                    l_lngContractId = l_settleContractEntry[i].getContractId();
                    //���ʃI�u�W�F�N�g�̎擾
                    l_ifoContract = new WEB3IfoContractImpl(l_lngContractId);
                }

                //��������̎擾
                l_bdContractExecutedAmount =
                    l_bdContractExecutedAmount.add(
                        new BigDecimal(l_ifoContract.getContractExecutedAmount(l_dblContractQuantity) + ""));
                l_dblContractExecutedAmount = l_bdContractExecutedAmount.doubleValue();

                //���萔���̎擾
                BigDecimal l_bdContractCommission =
                    new BigDecimal(l_ifoContract.getContractCommission(l_dblContractQuantity) + "");
                l_bdCommission = l_bdCommission.add(l_bdContractCommission);
                l_dblCommission = l_bdCommission.doubleValue();

                //���萔������ł̎擾
                BigDecimal l_bdContractCommissionConsumptionTax =
                    new BigDecimal(l_ifoContract.getContractCommissionConsumptionTax(l_dblContractQuantity) + "");
                l_bdCommissionConsumptionTax = l_bdCommissionConsumptionTax.add(l_bdContractCommissionConsumptionTax);
                l_dblCommissionConsumptionTax = l_bdCommissionConsumptionTax.doubleValue();
            }      
            log.debug("������� = " + l_dblContractExecutedAmount);
            log.debug("���萔�� = " + l_dblCommission);
            log.debug("���萔������� = " + l_dblCommissionConsumptionTax);
            
            //����̎�����̊T�Z��n������Z�o����
            double l_dblDeliveryAmountNow = 0D;
            double l_dblCommissionFeeTaxNow = 0D;
            
            SideEnum l_sideEnum = SideEnum.SELL.equals(l_dealing) ? SideEnum.BUY : SideEnum.SELL;
            
            //���������̈ϑ��萔�����Z�o����B
			l_ifoBizLogicProvider.calcCommission(l_commission, l_subAccount);
            //���������̈ϑ��萔���ɂ��������ł��Z�o����
            l_dblCommissionFeeTaxNow = l_ifoBizLogicProvider.calcSalesTax(l_commission.getCommission(), l_commission.getOrderBizDate(), l_subAccount);
            
            //���������̊T�Z��n������Z�o����
            l_dblDeliveryAmountNow = l_ifoBizLogicProvider.calcDeliveryAmount(l_sideEnum, l_dblCalcTurnOver, l_commission.getCommission(), l_dblCommissionFeeTaxNow);
            log.debug("����̎�����̊T�Z��n��� = " + l_dblDeliveryAmountNow);
            BigDecimal l_bdDeliveryAmountNow = new BigDecimal(l_dblDeliveryAmountNow + "");
            
            //�����̎�n������Z�o����            
            double l_dblContractDeliveryAmount = 0D;
            l_dblContractDeliveryAmount = l_ifoBizLogicProvider.calcDeliveryAmount(l_dealing, l_dblContractExecutedAmount, l_dblCommission, l_dblCommissionConsumptionTax);
            log.debug("�����̎�n��� = " + l_dblContractDeliveryAmount);
            BigDecimal l_bdContractDeliveryAmount = new BigDecimal(l_dblContractDeliveryAmount + "");
            
            //�������ԍς̏ꍇ 
            if (SideEnum.BUY.equals(l_dealing))
            {
                l_ifoEstimateDeliveryAmountCalcResult.setEstimateDeliveryAmount(
                    l_bdDeliveryAmountNow.subtract(l_bdContractDeliveryAmount).doubleValue());
            }
            //�������ԍς̏ꍇ
            else
            {
                l_ifoEstimateDeliveryAmountCalcResult.setEstimateDeliveryAmount(
                    l_bdContractDeliveryAmount.subtract(l_bdDeliveryAmountNow).doubleValue());
            }
            
            //�萔���R�[�X���Z�b�g����      
            l_ifoEstimateDeliveryAmountCalcResult.setCommissionCourse(l_commission.getCommissionCourseDiv());
            
            //�萔�����Z�b�g����
            l_ifoEstimateDeliveryAmountCalcResult.setCommission(l_commission.getCommission());
            
            //�萔������ł��Z�b�g����
            l_ifoEstimateDeliveryAmountCalcResult.setCommissionTax(l_dblCommissionFeeTaxNow);
            
            log.exiting(STR_METHOD_NAME);
            return l_ifoEstimateDeliveryAmountCalcResult;
        }
        catch (DataQueryException l_dqe)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_dne)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, this.getClass().getName() + STR_METHOD_NAME);
        }

    }

    /**
     * (calc�������T�Z���ϑ��v)<BR>
     *�����������̊T�Z���ϑ��v���Z�o���ĕԋp����B<BR>
     *�V�[�P���X�}�u�i�敨�����jcalc�������T�Z���ϑ��v�v�Q�ƁB<BR>
     * @@param l_commission - (�萔��)<BR>
     * @@param l_dblLimitPrice - �w�l<BR>
     * <BR>
     * 0���w�肳�ꂽ�ꍇ�́A�������v�Z�P���Ƃ��ė��p����B<BR>
     * 
     * @@param l_subAccount - (�⏕����)<BR>
     * @@param l_futuresOptionTradedProduct - �敨OP�������<BR>
     * @@param l_settleContractEntry[] - (�ԍό��ʃG���g��[])<BR>
     * @@param l_dblQuantity - (����)<BR>
     * ������������<BR>
     * 
     * @@param l_dealing - ����<BR>
     * �@@SideEnum.BUY�i���j<BR>
     * �@@SideEnum.SELL�i���j<BR>
     * 
     * @@param l_dblExecQuantity - �����P��.��萔��<BR>
     * 
     * @@param l_lngOrderUnitId - �����P��.�����P��ID<BR>
     * 
     * @@param l_blnIsSkipPriceCheck - (isSkip���z�`�F�b�N)<BR>
     * �v�Z���ʂ̑���ɂ��āA�Ó����`�F�b�N���X�L�b�v���邩�̃t���O�B<BR>
     * <BR>
     * �`�F�b�N���s���ꍇ��false�A�`�F�b�N���s��Ȃ��i�X�L�b�v����j�ꍇ��true���w�肷��B<BR>
     * 
     * @@return webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult
     * @@roseuid 40A31F3800B8
     */
    public WEB3IfoEstimateDeliveryAmountCalcResult calcChangeEstimateSettlementIncome(
        WEB3GentradeCommission l_commission,
        double l_dblLimitPrice,
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoTradedProductImpl l_futuresOptionTradedProduct,
        SettleContractEntry[] l_settleContractEntry,
        double l_dblQuantity,
        SideEnum l_dealing,
        double l_dblExecQuantity,
        long l_lngOrderUnitId,
        boolean l_blnIsSkipPriceCheck)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcChangeEstimateSettlementIncome(WEB3GentradeCommission, double, WEB3GentradeSubAccount, " 
            + "WEB3IfoTradedProductImpl, SettleContractEntry[], double, SideEnum, double, long, boolean";
        log.entering(STR_METHOD_NAME);

        //�߂�l�̊T�Z��n����v�Z���ʃI�u�W�F�N�g�𐶐�����B
        WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult = new WEB3IfoEstimateDeliveryAmountCalcResult();
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3IfoProductManagerImpl l_productMgr = (WEB3IfoProductManagerImpl) l_tradingModule.getProductManager();
        
        try{
            //�v�Z�T�[�r�X�I�u�W�F�N�g���쐬����
            WEB3IfoBizLogicProvider l_ifoBizLogicProvider = null;
            l_ifoBizLogicProvider = (WEB3IfoBizLogicProvider) l_finApp.getTradingModule(ProductTypeEnum.IFO).getBizLogicProvider();
        
            //�敨OP�����R���ʃ`�F�b�N�I�u�W�F�N�g���쐬����
            WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations = null;
            l_ifoOrderManagerReusableValidations =
                 (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations.getInstance();

        
            double l_dblCurrentPrice = 0;
            //�i�萔��.is�w�l() == false�j�̏ꍇ�̂ݎ��{����
            if (l_dblLimitPrice == 0D)
            {
                l_dblCurrentPrice = l_productMgr.getCurrentPrice(l_futuresOptionTradedProduct);
                l_ifoEstimateDeliveryAmountCalcResult.setCalcUnitPrice(l_dblCurrentPrice);
            }
            else
            {
                l_ifoEstimateDeliveryAmountCalcResult.setCalcUnitPrice(l_dblLimitPrice);
            }
        
            //����������v�Z����B
            double l_dblTurnOver = 0;
            l_dblTurnOver = l_ifoBizLogicProvider.calcTurnOver((l_dblQuantity - l_dblExecQuantity),
                    l_ifoEstimateDeliveryAmountCalcResult.getCalcUnitPrice(),
                    l_futuresOptionTradedProduct);
                    
            log.debug("������� = " + l_dblTurnOver); 
            BigDecimal l_bdTurnOver = new BigDecimal(l_dblTurnOver + "");
 
            //�����P�ʂ��擾����B
            IfoOrderUnit l_orderUnit = null;
            l_orderUnit = (IfoOrderUnit)this.getOrderUnit(l_lngOrderUnitId);
            IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        
            //���o��v�Z�p����F�@@������� + �����P��.���v�����z
            BigDecimal l_bdExecutedAmount = new BigDecimal(l_orderUnitRow.getExecutedAmount() + "");
            double l_dblsetExpensesCalcAmount = l_bdTurnOver.add(l_bdExecutedAmount).doubleValue();
            log.debug("���o��v�Z�p��� = " + l_dblsetExpensesCalcAmount);
            l_commission.setExpensesCalcAmount(l_dblsetExpensesCalcAmount);
        
            //isSkip���z�`�F�b�N == false�̏ꍇ�̂݁A����\������z�`�F�b�N���s��
            if (!l_blnIsSkipPriceCheck)
            {
                MainAccountRow l_mainAccountRow = (MainAccountRow) l_subAccount.getMainAccount().getDataSourceObject();
                l_ifoOrderManagerReusableValidations.validateOrderMaxAmount(
                        l_subAccount.getWeb3GenBranch(),
                        l_commission.getExpensesCalcAmount(),
                        l_mainAccountRow.getAccountType(),
                        WEB3FuturesOptionDivDef.FUTURES);
            } 
        
            //���������擾����B
            Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

            WEB3IfoContractImpl l_contractImpl = null;
            long l_lngContractId = 0L;
            double l_dblContractQuantity = 0D;
            double l_dblContractExecutedAmount = 0D;
            BigDecimal l_bdContractExecutedAmount = new BigDecimal("0");
            double l_dblCommission = 0D;
            BigDecimal l_bdCommission = new BigDecimal("0");
            double l_dblCommissionConsumptionTax = 0D;
            BigDecimal l_bdCommissionConsumptionTax = new BigDecimal("0");
            
            //�ԍό��ʃG���g���̗v�f������LOOP����
            for (int i = 0; i < l_settleContractEntry.length; i++)
            {
                //�ԍϐ��ʂ̎擾
                l_dblContractQuantity = l_settleContractEntry[i].getQuantity();
                
                //����ID�̎擾
                l_lngContractId = l_settleContractEntry[i].getContractId();
                
                //���ʃI�u�W�F�N�g�̎擾
                l_contractImpl = new WEB3IfoContractImpl(l_lngContractId);

                //��������̎擾
                l_bdContractExecutedAmount =
                    l_bdContractExecutedAmount.add(
                        new BigDecimal(l_contractImpl.getContractExecutedAmount(l_dblContractQuantity) + ""));
                l_dblContractExecutedAmount = l_bdContractExecutedAmount.doubleValue();

                //���萔���̎擾
                BigDecimal l_bdContractCommission =
                    new BigDecimal(l_contractImpl.getContractCommission(l_dblContractQuantity, l_lngOrderUnitId) + "");
                l_bdCommission = l_bdCommission.add(l_bdContractCommission);
                l_dblCommission = l_bdCommission.doubleValue();

                //���萔������ł̎擾
                BigDecimal l_bdContractCommissionConsumptionTax =
                    new BigDecimal(l_contractImpl.getContractCommissionConsumptionTax(l_dblContractQuantity, l_lngOrderUnitId) + "");
                l_bdCommissionConsumptionTax = l_bdCommissionConsumptionTax.add(l_bdContractCommissionConsumptionTax);
                l_dblCommissionConsumptionTax = l_bdCommissionConsumptionTax.doubleValue();
            }
            log.debug("������� = " + l_dblContractExecutedAmount);
            log.debug("���萔�� = " + l_dblCommission);
            log.debug("���萔������� = " + l_dblCommissionConsumptionTax);
            
            //����̎�����̊T�Z��n������Z�o����
            double l_dblDeliveryAmountNow = 0D;
            double l_dblCommissionFeeTaxNow = 0D;
            
            SideEnum l_sideEnum = SideEnum.SELL.equals(l_dealing) ? SideEnum.BUY : SideEnum.SELL;
            
            //���������̈ϑ��萔�����Z�o����
            l_ifoBizLogicProvider.calcCommission(l_commission, l_subAccount);
            //���������̈ϑ��萔���ɂ��������ł��Z�o����
            l_dblCommissionFeeTaxNow = l_ifoBizLogicProvider.calcSalesTax(l_commission.getCommission(), l_commission.getOrderBizDate(), l_subAccount);
            
            //���������̊T�Z��n������Z�o����
            l_dblDeliveryAmountNow = l_ifoBizLogicProvider.calcDeliveryAmount(l_sideEnum, l_dblsetExpensesCalcAmount, l_commission.getCommission(), l_dblCommissionFeeTaxNow);            
            BigDecimal l_bdDeliveryAmountNow = new BigDecimal(l_dblDeliveryAmountNow + "");
            log.debug("����̎�����̊T�Z��n��� = " + l_dblDeliveryAmountNow);
            
            //�����̎�n������Z�o����            
            double l_dblContractDeliveryAmount = 0D;
            l_dblContractDeliveryAmount = l_ifoBizLogicProvider.calcDeliveryAmount(l_dealing, l_dblContractExecutedAmount, l_dblCommission, l_dblCommissionConsumptionTax);
            BigDecimal l_bdContractDeliveryAmount = new BigDecimal(l_dblContractDeliveryAmount + "");
            log.debug("�����̎�n��� = " + l_dblContractDeliveryAmount);
        
            //�������ԍς̏ꍇ
            if (SideEnum.BUY.equals(l_dealing))
            {
                //�T�Z���ϑ��v = ���������̊T�Z��n���(**1) �| �����̎�n���(**2)
                l_ifoEstimateDeliveryAmountCalcResult.setEstimateDeliveryAmount(
                    l_bdDeliveryAmountNow.subtract(l_bdContractDeliveryAmount).doubleValue());
            }
            //�������ԍς̏ꍇ
            else
            {
				//�T�Z���ϑ��v = �����̎�n���(**2) �| ���������̊T�Z��n���(**1)
                l_ifoEstimateDeliveryAmountCalcResult.setEstimateDeliveryAmount(
                    l_bdContractDeliveryAmount.subtract(l_bdDeliveryAmountNow).doubleValue());
            }

            //�萔���R�[�X���Z�b�g����
            l_ifoEstimateDeliveryAmountCalcResult.setCommissionCourse(l_commission.getCommissionCourseDiv());
            
            //�萔�����Z�b�g����
            l_ifoEstimateDeliveryAmountCalcResult.setCommission(l_commission.getCommission());
            
            //�萔������ł��Z�b�g����
            l_ifoEstimateDeliveryAmountCalcResult.setCommissionTax(l_dblCommissionFeeTaxNow);
            
            log.exiting(STR_METHOD_NAME);
            return l_ifoEstimateDeliveryAmountCalcResult;
        }
        catch (NotFoundException l_nfex)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_dne)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_dqe)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, this.getClass().getName() + STR_METHOD_NAME);
        }
    }

    /**
     * (validate�敨�V�K������)<BR>
     * ���V�K�����������R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨�����jvalidate�V�K�������v�Q�ƁB<BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@param l_openContractOrderSpec - �V�K���������e<BR>
     * @@param l_ifoOrderUnit - �����P��<BR>
     * @@return NewOrderValidationResult
     * @@roseuid 40AB13340083
     */
    public NewOrderValidationResult validateFuturesOpenContractOrder(
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoOpenContractOrderSpec l_openContractOrderSpec,
        IfoOrderUnit l_ifoOrderUnit)
    {
        final String STR_METHOD_NAME = "validateFuturesOpenContractOrder(WEB3GentradeSubAccount, WEB3IfoOpenContractOrderSpec, IfoOrderUnit)";

        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_openContractOrderSpec == null)
        {
            //��O���X���[����
            log.error(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            //throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
            return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80017));     
        }
        NewOrderValidationResult l_newOrderValidationResult = null;
        try
        {
        //�敨OP�����R���ʃ`�F�b�N�I�u�W�F�N�g���쐬����
        WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations = (WEB3IfoOrderManagerReusableValidations) WEB3IfoOrderManagerReusableValidations.getInstance();

        //�������ʃ`�F�b�N�����{����B
        this.validateOrder(l_subAccount, WEB3FuturesOptionDivDef.FUTURES);

        //���͎s��R�[�h�̎擾
        String l_strMarketCode = l_openContractOrderSpec.getMarketCode();

        String l_strInstitutionCode = l_openContractOrderSpec.getInstitutionCode();

        //�s��R�[�h�̃`�F�b�N�����{����B 
        WEB3GentradeMarket l_market = (WEB3GentradeMarket) l_ifoOrderManagerReusableValidations.validateMarket(l_strMarketCode, l_strInstitutionCode);

        //�����R�[�h���擾����B
        String l_strProductCode = l_openContractOrderSpec.getProductCode();

        //�����̃`�F�b�N���s���A�����I�u�W�F�N�g��ԋp����
        WEB3IfoProductImpl l_ifoProductImpl = l_ifoOrderManagerReusableValidations.validateProductCode(l_strProductCode, l_strInstitutionCode);

        //�������𔻒肷��B
        boolean l_isBuyToOpenOrder = l_openContractOrderSpec.isBuyToOpenOrder();

        //��������̃`�F�b�N���s���A�敨OP��������I�u�W�F�N�g��ԋp����B    
        WEB3IfoTradedProductImpl l_ifoTradedProductImpl = l_ifoOrderManagerReusableValidations.validateTradedProduct(l_ifoProductImpl, l_market, l_isBuyToOpenOrder, true);

        //�w��ڋq�̎���X�ł��镔�X�I�u�W�F�N�g���擾����B 
        //���X�I�u�W�F�N�g���擾����
        WEB3GentradeBranch l_branch = null;
        l_branch = (WEB3GentradeBranch) l_subAccount.getMainAccount().getBranch();

        //���͎w�����戵�\���𔻒肷��B 
        l_ifoOrderManagerReusableValidations.validateHandlingIndex(l_branch.getBranchCode(), l_ifoTradedProductImpl);

        //���s���ǂ����𔻒肷��B
        boolean l_blnMarketOrder = l_openContractOrderSpec.isMarketOrder();

        //�������������擾����B
        Date l_datOrderExpDate = l_openContractOrderSpec.getOrderExpDate();

        //�����������擾����B
        String l_strOrderCond = l_openContractOrderSpec.getOrderCond();

        //���s�������擾����B
        IfoOrderExecutionConditionType l_conditionType = l_openContractOrderSpec.getExecutionConditionType();

		//���������敪���擾����B
		String l_strExpirationDateType = l_openContractOrderSpec.getExpirationDateType();
		
		//���������̃`�F�b�N���s��
		Date l_datFirstOrderBizDate = null;
		//�����J�z�̏ꍇ
		if((l_openContractOrderSpec.getFirstOrderUnitId() != null) &&
			(l_openContractOrderSpec.getFirstOrderUnitId().longValue() > 0))
		{
			IfoOrderUnit l_orderUnit = null;
			try
			{
				l_orderUnit = (IfoOrderUnit)getOrderUnit(
				l_openContractOrderSpec.getFirstOrderUnitId().longValue());
			}
			catch (NotFoundException l_nfe)
			{
				return new NewOrderValidationResult(
					ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));     
			}
			IfoOrderUnitRow l_orderUnitRow =
				(IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
			l_datFirstOrderBizDate = WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(),"yyyyMMdd");
		}

		//validate��������		
        l_ifoOrderManagerReusableValidations.validateOrderCond(
            l_subAccount,
            0,
            l_blnMarketOrder,
            l_ifoTradedProductImpl,
            true,
            l_isBuyToOpenOrder,
            l_datFirstOrderBizDate,
            l_datOrderExpDate,
            l_strOrderCond,
            l_conditionType,
			l_strExpirationDateType,
            l_openContractOrderSpec.getFirstOrderUnitId());

        //���ʂ̃`�F�b�N���s���B 
        if (l_ifoOrderUnit == null)
        {
            double l_dblQuantity = l_openContractOrderSpec.getQuantity();
            l_ifoOrderManagerReusableValidations.validateQuantity(l_subAccount, l_ifoTradedProductImpl, l_dblQuantity, l_isBuyToOpenOrder, true);
        }

        //�w�l���擾����B
        double l_dblLimitPrice = l_openContractOrderSpec.getLimitPrice();
        //�w�l�̃`�F�b�N���s���B
        l_ifoOrderManagerReusableValidations.validateOrderUnitPrice(l_dblLimitPrice, l_ifoTradedProductImpl, l_subAccount);

        //W�w�l�̒����w�l���擾����B
        double l_dblWLimitPriceChange = l_openContractOrderSpec.getWLimitPriceChange();

        //W�w�l�̎��s�������擾����B
        IfoOrderExecutionConditionType l_wLimitExecCondType = l_openContractOrderSpec.getWLimitExecCondType();
        
        //�t�w�l��l���擾����B
        double l_dblStopOrderPrice = l_openContractOrderSpec.getStopOrderPrice();
        
        String l_strWLimitPriceChange = 
            WEB3StringTypeUtility.formatNumber(l_dblWLimitPriceChange);
        
        //validateW�w�l����()
        l_ifoOrderManagerReusableValidations.validateWLimitPriceOrder(
            l_subAccount,
            0,
            l_dblLimitPrice,
            l_strOrderCond,
            l_dblStopOrderPrice,
            l_strWLimitPriceChange,
            l_wLimitExecCondType,
            null,
            l_ifoTradedProductImpl,
            true,
            l_isBuyToOpenOrder);
        
        ProcessingResult l_processingResult = null;
        l_processingResult = ProcessingResult.newSuccessResultInstance();
        l_newOrderValidationResult = new NewOrderValidationResult(l_processingResult);
               
        }
        catch (WEB3BaseException l_webx)
        {
            return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(l_webx.getErrorInfo()));
        }
        
		log.exiting(STR_METHOD_NAME);
        
        return l_newOrderValidationResult;
    }

    /**
     * (validate�敨�ԍϒ���)<BR>
     * �敨�ԍϒ��������R�����s���B<BR>
     * �ivalidateFuturesSettleContractOrder�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * this.validate�敨�ԍϒ���()�ɏ������Ϗ��idelegate�j����B<BR>
     * <BR>
     * [����] �⏕�����F�@@�p�����[�^.�⏕����<BR>
     * �ԍϒ������e�F�@@�p�����[�^.�ԍϒ������e<BR>
     * ���ʁF�@@null <BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@param l_settleContractOrderSpec - �ԍϒ������e�I�u�W�F�N�g<BR>
     * @@return NewOrderValidationResult
     */
    public NewOrderValidationResult validateFuturesSettleContractOrder(
        WEB3GentradeSubAccount l_subAccount, WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec)
    {
        return this.validateFuturesSettleContractOrder(l_subAccount, l_settleContractOrderSpec, null);
    }

    /**
     * (validate�敨�ԍϒ���)<BR>
     * �敨�ԍϒ��������R��<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨�����jvalidate�ԍϒ����v�Q�ƁB<BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@param l_settleContractOrderSpec - �ԍϒ������e�I�u�W�F�N�g<BR>
     * @@param l_ifoContractImpl - �敨OP����<BR>
     * @@return NewOrderValidationResult
     * @@roseuid 40AB133400A2
     */
    public NewOrderValidationResult validateFuturesSettleContractOrder(
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec,
        WEB3IfoContractImpl l_ifoContractImpl)
    {
        final String STR_METHOD_NAME = "validateFuturesSettleContractOrder(" +
            "WEB3GentradeSubAccount, WEB3IfoSettleContractOrderSpec, WEB3IfoContractImpl)";

        log.entering(STR_METHOD_NAME);
        if (l_subAccount == null || l_settleContractOrderSpec == null)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80017));     
        }

        ProcessingResult l_processingResult = null;

        //�V�K�������R�����ʃI�u�W�F�N�g
        NewOrderValidationResult l_newOrderValidationResult = null;

        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

            //�敨OP�����R���ʃ`�F�b�N�I�u�W�F�N�g���쐬����
            WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations = null;
            l_ifoOrderManagerReusableValidations = (WEB3IfoOrderManagerReusableValidations) WEB3IfoOrderManagerReusableValidations.getInstance();

            //�������ʃ`�F�b�N�����{����
            this.validateOrder(l_subAccount, WEB3FuturesOptionDivDef.FUTURES);

            // ���Ύ���ȊO�i�p�����[�^.���� == null�j�̏ꍇ
            if (l_ifoContractImpl == null)
            {
                //�ԍϒ������e�Ɋ֘A����ԍό��ʃG���g���̔z����擾����
                SettleContractEntry l_settleContractEntry = null;
                l_settleContractEntry = l_settleContractOrderSpec.getSettleContractEntries()[0];

                //����ID���擾
                long l_lngContractID = 0L;
                l_lngContractID = l_settleContractEntry.getContractId();

                //����ID�ɊY�����錚�ʃI�u�W�F�N�g���擾����
                l_ifoContractImpl = new WEB3IfoContractImpl(l_lngContractID);
            }

            //�s��ID���擾
            long l_lngMarketId = 0L;
            l_lngMarketId = l_ifoContractImpl.getMarketId();

            //�s��I�u�W�F�N�g���擾
            Market l_market = null;
            l_market = l_finApp.getFinObjectManager().getMarket(l_lngMarketId); //throw NotFoundException

            //�،���ЃR�[�h���擾
            String l_strInstitutionCode = null;
            l_strInstitutionCode = l_settleContractOrderSpec.getInstitutionCode();

            //�s��R�[�h�̃`�F�b�N�����{����
            l_market = l_ifoOrderManagerReusableValidations.validateMarket(l_market.getMarketCode(), l_strInstitutionCode);

            //�敨OP�����I�u�W�F�N�g���擾����
            WEB3IfoProductImpl l_ifoProductImpl = null;
            l_ifoProductImpl = (WEB3IfoProductImpl) l_ifoContractImpl.getProduct();

            //�����R�[�h���擾����
            String l_strProductCode = null;
            l_strProductCode = l_ifoProductImpl.getProductCode();

            //�����̃`�F�b�N���s���A�����I�u�W�F�N�g��ԋp����            
            l_ifoProductImpl = l_ifoOrderManagerReusableValidations.validateProductCode(l_strProductCode, l_strInstitutionCode);

            //�������𔻒肷��
            boolean l_blnIsBuyContract = false;
            l_blnIsBuyContract = l_ifoContractImpl.isLong();

            //��������̃`�F�b�N���s���A�敨OP��������I�u�W�F�N�g��ԋp����
            WEB3IfoTradedProductImpl l_ifoTradedProductImpl = null;
            l_ifoTradedProductImpl = l_ifoOrderManagerReusableValidations.validateTradedProduct(l_ifoProductImpl, (WEB3GentradeMarket) l_market, l_blnIsBuyContract, false);

            //���X�R�[�h���擾
            String l_strBranchCode = null;
            l_strBranchCode = l_subAccount.getWeb3GenBranch().getBranchCode();

            //���͎w�����戵�\���𔻒肷��
            l_ifoOrderManagerReusableValidations.validateHandlingIndex(l_strBranchCode, l_ifoTradedProductImpl);

            //���s���ǂ����𔻒肷��
            boolean l_blnIsMatketOrder = false;
            l_blnIsMatketOrder = l_settleContractOrderSpec.isMarketOrder();

            //�������������擾����
            Date l_datOrderExpDate = null;
            l_datOrderExpDate = l_settleContractOrderSpec.getOrderExpDate();

            //�����������擾����
            String l_strOrderCond = null;
            l_strOrderCond = l_settleContractOrderSpec.getOrderCond();

            //���s�������擾����
            IfoOrderExecutionConditionType l_executionConditionType = null;
            l_executionConditionType = l_settleContractOrderSpec.getExecutionConditionType();

			//���������敪���擾����
			String l_strExpirationDateType = null;
			l_strExpirationDateType = l_settleContractOrderSpec.getExpirationDateType();
			
			Date l_datFirstOrderBizDate = null;
			//�����J�z�̏ꍇ
			if((l_settleContractOrderSpec.getFirstOrderUnitId() != null) &&
				(l_settleContractOrderSpec.getFirstOrderUnitId().longValue() > 0))
			{
				IfoOrderUnit l_orderUnit = null;
				l_orderUnit = (IfoOrderUnit)getOrderUnit(
					l_settleContractOrderSpec.getFirstOrderUnitId().longValue());
				IfoOrderUnitRow l_orderUnitRow =
					(IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
				l_datFirstOrderBizDate = WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(),"yyyyMMdd");
			}	
			
            //���������̃`�F�b�N���s��
            l_ifoOrderManagerReusableValidations.validateOrderCond(
                l_subAccount,
                0,
                l_blnIsMatketOrder,
                l_ifoTradedProductImpl,
                false,
                l_blnIsBuyContract,
                l_datFirstOrderBizDate,
                l_datOrderExpDate,
                l_strOrderCond,
                l_executionConditionType,
				l_strExpirationDateType,
                l_settleContractOrderSpec.getFirstOrderUnitId());

            //���ʂ��擾
            double l_dblTotalQuantity = 0D;
            l_dblTotalQuantity = l_settleContractOrderSpec.getTotalQuantity();

            //���ʂ̃`�F�b�N���s��
            l_ifoOrderManagerReusableValidations.validateQuantity(l_subAccount, l_ifoTradedProductImpl, l_dblTotalQuantity, l_blnIsBuyContract, false);

            //�w�l���擾����
            double l_dblLimitPrice = 0D;
            l_dblLimitPrice = l_settleContractOrderSpec.getLimitPrice();

            //�w�l�̃`�F�b�N���s��
            l_ifoOrderManagerReusableValidations.validateOrderUnitPrice(l_dblLimitPrice, l_ifoTradedProductImpl, l_subAccount);

            //W�w�l�̒����w�l���擾����
            double l_dblWLimitPriceChange = 0D;
            l_dblWLimitPriceChange = l_settleContractOrderSpec.getWLimitPriceChange();

            //�t�w�l��l���擾����B
            double l_dblStopOrderPrice = l_settleContractOrderSpec.getStopOrderPrice();
            
            //W�w�l�̎��s�������擾����B
            IfoOrderExecutionConditionType l_wLimitExecCondType = l_settleContractOrderSpec.getWLimitExecCondType();
            
            String l_strWLimitPriceChange = 
                WEB3StringTypeUtility.formatNumber(l_dblWLimitPriceChange);
            
            //validateW�w�l����()
            l_ifoOrderManagerReusableValidations.validateWLimitPriceOrder(
                l_subAccount,
                0,
                l_dblLimitPrice,
                l_strOrderCond,
                l_dblStopOrderPrice,
                l_strWLimitPriceChange,
                l_wLimitExecCondType,
                null,
                l_ifoTradedProductImpl,
                false,
                l_ifoContractImpl.isShort());
            
            l_processingResult = ProcessingResult.newSuccessResultInstance();

            l_newOrderValidationResult = new NewOrderValidationResult(l_processingResult);
        }
        catch (DataQueryException l_dqex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_dqex);
            return new NewOrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003));  
        }
        catch (DataNetworkException l_dnex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_dnex);
            return new NewOrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003));  
        }
        catch (NotFoundException l_nfex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_nfex);
            return new NewOrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005));  
        }
        catch (WEB3BaseException l_webx)
        {
            return new NewOrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    l_webx.getErrorInfo()));
        }
        log.exiting(STR_METHOD_NAME);

        return l_newOrderValidationResult;
    }

    /**
     * (validate�敨�ԍϒ�������)<BR>
     * this.validate�敨�ԍϒ�������()�ɏ������Ϗ��idelegate�j����B<BR>
     * <BR>
     * [validate�敨�ԍϒ�������()�Ɏw�肷�����]<BR>
     * �⏕�����F�@@�p�����[�^.�⏕����<BR>
     * �敨�ԍϒ������e�F�@@�p�����[�^.�ԍϒ������e<BR>
     * isSkip�x���󋵃`�F�b�N�F�@@false�i�Œ�j<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_changeSettleContractOrderSpec - (�ԍϒ������e)<BR>
     * �ԍϒ������e�I�u�W�F�N�g<BR>
     * @@return OrderValidationResult
     */
    public OrderValidationResult validateFuturesChangeSettleContractOrder(
        WEB3GentradeSubAccount l_subAccount, 
        WEB3IfoChangeSettleContractOrderSpec l_changeSettleContractOrderSpec)
    {
        final String STR_METHOD_NAME =
            "validateFuturesChangeSettleContractOrder(l_subAccount,l_changeSettleContractOrderSpec)";
        log.entering(STR_METHOD_NAME);

        //this.validate�敨�ԍϒ�������()�ɏ������Ϗ��idelegate�j����B
        OrderValidationResult l_orderValidationResult =
            this.validateFuturesChangeSettleContractOrder(
                l_subAccount,
                l_changeSettleContractOrderSpec,
                false);

        log.exiting(STR_METHOD_NAME);
        return l_orderValidationResult;
    }

    /**
     * (validate�敨�ԍϒ�������)<BR>
     * �敨�ԍϒ������������R��<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨�����jvalidate�ԍϒ��������v�Q�ƁB<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_changeSettleContractOrderSpec - (�ԍϒ������e)<BR>
     * �ԍϒ������e�I�u�W�F�N�g<BR>
     * @@param l_blnIsSkipDelayStatusCheck - (isSkip�x���󋵃`�F�b�N)<BR>
     * isSkip�x���󋵃`�F�b�N<BR>
     * @@return OrderValidationResult
     * @@roseuid 40AB133400C2
     */
    public OrderValidationResult validateFuturesChangeSettleContractOrder(
        WEB3GentradeSubAccount l_subAccount, 
        WEB3IfoChangeSettleContractOrderSpec l_changeSettleContractOrderSpec,
        boolean l_blnIsSkipDelayStatusCheck)
    {
        final String STR_METHOD_NAME = "validateFuturesChangeSettleContractOrder" +
            "(l_subAccount, l_changeSettleContractOrderSpec, l_blnIsSkipDelayStatusCheck)";

        log.entering(STR_METHOD_NAME);
        if (l_subAccount == null || l_changeSettleContractOrderSpec == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //��O���X���[����
            return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80017)); 
        }

        //�����`�F�b�N���ʃI�u�W�F�N�g
        OrderValidationResult l_orderValidationResult = null;

        //�敨OP�����R���ʃ`�F�b�N�I�u�W�F�N�g���쐬����
        WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations = null;
        l_ifoOrderManagerReusableValidations = (WEB3IfoOrderManagerReusableValidations) WEB3IfoOrderManagerReusableValidations.getInstance();

        try
        {
            //�������ʃ`�F�b�N�����{����
            this.validateOrder(l_subAccount, WEB3FuturesOptionDivDef.FUTURES);

            //����ID���擾
            long l_lngOrderID = 0L;
            l_lngOrderID = l_changeSettleContractOrderSpec.getOrderId();
            log.debug("�����h�c���擾 = " + l_lngOrderID);
            //�����I�u�W�F�N�g���擾
            Order l_order = null;
            l_order = this.getOrder(l_lngOrderID); //throw NotFoundException

            //�������̃X�e�C�^�X�������\�ȏ�Ԃ��𔻒肷��
            //[validate���������\���()�Ɏw�肷�����] 
            // �����F�@@�i�����h�c�ɊY�����钍���I�u�W�F�N�g�j 
            // isSkip�x���󋵃`�F�b�N�F������isSkip�x���󋵃`�F�b�N
            l_ifoOrderManagerReusableValidations.validateOrderForChangeability(
                l_order,
                l_blnIsSkipDelayStatusCheck);

            //�����P��ID���擾
            long l_lngOrderUnitID = 0L;
            l_lngOrderUnitID = l_changeSettleContractOrderSpec.getOrderUnitId();

            //�����P�ʃI�u�W�F�N�g���擾
            OrderUnit l_orderUnit = null;
            l_orderUnit = this.getOrderUnit(l_lngOrderUnitID); //throw NotFoundException

            //�����P��Row�I�u�W�F�N�g���擾
            IfoOrderUnitRow l_ifoOrderUnitRow = null;
            l_ifoOrderUnitRow = (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();

            //�敨OP�����I�u�W�F�N�g
            WEB3IfoProductImpl l_ifoProductImpl = null;

            //�����̃`�F�b�N���s���A�����I�u�W�F�N�g��ԋp����
            l_ifoProductImpl = l_ifoOrderManagerReusableValidations.validateProductID(l_ifoOrderUnitRow.getProductId());

            //�s��I�u�W�F�N�g
            WEB3GentradeMarket l_market = null;
            //�s��̃`�F�b�N
            l_market = (WEB3GentradeMarket) l_ifoOrderManagerReusableValidations.validateMarketID(l_ifoOrderUnitRow.getMarketId());

            //�ԍϒ��������ʃG���g���Ɋ֘A����ԍό��ʃG���g���̔z����擾����
            SettleContractEntry l_settleContractEntry = null;
            l_settleContractEntry = l_changeSettleContractOrderSpec.getAfterChangeSettleContractEntries()[0];

            //����ID���擾
            long l_lngContractID = 0L;
            l_lngContractID = l_settleContractEntry.getContractId();

            //���ʂh�c�ɊY�����錚�ʃI�u�W�F�N�g���擾����
            IfoContractImpl l_ifoContractImpl = null;
            //throw DataQueryException,DataNetworkException
            l_ifoContractImpl = new IfoContractImpl(l_lngContractID);

            //�������̋敪���擾
            boolean l_blnIsBuyContract = false;
            l_blnIsBuyContract = l_ifoContractImpl.isLong();

            //��������̃`�F�b�N���s���A�敨OP��������I�u�W�F�N�g��ԋp����
            WEB3IfoTradedProductImpl l_ifoTradedProductImpl = null;
            l_ifoTradedProductImpl = l_ifoOrderManagerReusableValidations.validateTradedProduct(l_ifoProductImpl, l_market, l_blnIsBuyContract, false);

            //���͎w�����戵�\���𔻒肷��
            l_ifoOrderManagerReusableValidations.validateHandlingIndex(l_subAccount.getWeb3GenBranch().getBranchCode(), l_ifoTradedProductImpl);

            //is���s���擾����
            boolean l_blnIsAfterChangePriceMarket = false;
            l_blnIsAfterChangePriceMarket = l_changeSettleContractOrderSpec.isAfterChangePriceMarket();

            //�������������擾����
            Date l_datChangeExpirationDate = null;
            log.debug( "���������� =" + l_datChangeExpirationDate);
            l_datChangeExpirationDate = l_changeSettleContractOrderSpec.getChangeExpirationDate();

            //�����������擾����
            String l_strOrderCond = null;
            l_strOrderCond = l_changeSettleContractOrderSpec.getOrderCond();

            //�������s�������擾����
            IfoOrderExecutionConditionType l_changeExecCondType = null;
            l_changeExecCondType = l_changeSettleContractOrderSpec.getChangeExecCondType();

            //���������敪���擾����B
            String l_strExpirationDateType=null;
			l_strExpirationDateType = l_changeSettleContractOrderSpec.getExpirationDateType();
			
            //�敨�����}�l�[�W��.get���񒍕��̒����P��(�����P��).���������擾����
            IfoOrderUnitRow l_firstOrderUnitRow =
                (IfoOrderUnitRow)this.getFirstOrderUnit((IfoOrderUnit)l_orderUnit).getDataSourceObject();
            Date l_bizDate = WEB3DateUtility.getDate(l_firstOrderUnitRow.getBizDate(),"yyyyMMdd");
	
            //���������̃`�F�b�N���s��
            l_ifoOrderManagerReusableValidations.validateOrderCond(
                l_subAccount,
                l_lngOrderUnitID,
                l_blnIsAfterChangePriceMarket,
                l_ifoTradedProductImpl,
                false,
                l_blnIsBuyContract,
				l_bizDate,
                l_datChangeExpirationDate,
                l_strOrderCond,
                l_changeExecCondType,
				l_strExpirationDateType,
                new Long(l_firstOrderUnitRow.getOrderUnitId()));

            //������̐��ʂ��擾����
            double l_dblAfterChangeTotalQuantity = 0D;
            l_dblAfterChangeTotalQuantity = l_changeSettleContractOrderSpec.getAfterChangeTotalQuantity();

            //���ʂ�0�܂��́A�}�C�i�X�l�łȂ����Ƃ̃`�F�b�N���s��
            //throw OrderValidationException
            l_ifoOrderManagerReusableValidations.validateQuantity(l_dblAfterChangeTotalQuantity);

            //������w�l���擾����
            double l_dblAfterChangePrice = 0D;
            l_dblAfterChangePrice = l_changeSettleContractOrderSpec.getAfterChangePrice();

            //�w�l�̃`�F�b�N���s��
            l_ifoOrderManagerReusableValidations.validateOrderUnitPrice(l_dblAfterChangePrice, l_ifoTradedProductImpl, l_subAccount);

            //validate�ǌ���������t�\
            //�����^�C�v�F�@@�����P��.�����^�C�v
            //�敨�^�I�v�V�����敪�F�@@�����P��.�敨�^�I�v�V�����敪
            //���X�F�@@�⏕����.get�戵�X()
            //����敪�F�@@�����P��.����敪
            //�������F�@@�����P��.������
            Date l_datBizDate = WEB3DateUtility.getDate(
                l_ifoOrderUnitRow.getBizDate(), "yyyyMMdd");

            WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
                l_ifoOrderUnitRow.getProductType(),
                l_ifoOrderUnitRow.getFutureOptionDiv(),
                l_subAccount.getWeb3GenBranch(),
                l_ifoOrderUnitRow.getSessionType(),
                l_datBizDate);

            //������̔����������Z�q���擾����
            String l_strOrderOperatorAfterChange = null;
            l_strOrderOperatorAfterChange = l_changeSettleContractOrderSpec.getOrderCondOperator();

            //������̋t�w�l��l�^�C�v���擾����
            String l_strStopOrderBasePriceTypeAfterChange = null;
            l_strStopOrderBasePriceTypeAfterChange = l_changeSettleContractOrderSpec.getStopOrderBasePriceType();

            //������̋t�w�l��l���擾����
            double l_dblStopOrderBasePriceAfterChange = 0D;

            l_dblStopOrderBasePriceAfterChange = l_changeSettleContractOrderSpec.getStopOrderBasePrice();
            
            //������́iW�w�l�j�����w�l���擾����B
            double l_dblWStopPriceAfterChange = l_changeSettleContractOrderSpec.getWLimitPriceChange();
            
            //������̎��������擾����
            Date l_datExpirationDateAfterChange = null;
            l_datExpirationDateAfterChange = l_changeSettleContractOrderSpec.getChangeExpirationDate();

            //������́iW�w�l�j���s�������擾����B
            IfoOrderExecutionConditionType l_wLimitExecCondTypeAfterChange = 
                l_changeSettleContractOrderSpec.getWLimitExecCondType();            
            
            String l_strWLimitPriceChange = 
                WEB3StringTypeUtility.formatNumber(l_dblWStopPriceAfterChange);
            
            //validateW�w�l����()
            l_ifoOrderManagerReusableValidations.validateWLimitPriceOrder(
                l_subAccount,
                l_lngOrderUnitID,
                l_dblAfterChangePrice,
                l_strOrderCond,
                l_dblStopOrderBasePriceAfterChange,
                l_strWLimitPriceChange,
                l_wLimitExecCondTypeAfterChange,
                l_changeSettleContractOrderSpec.getWLimitEnableStatusDiv(),
                l_ifoTradedProductImpl,
                false,
                l_ifoContractImpl.isShort());
            
            //������̒��������敪���擾����
            String l_strAfterExpirationDateType = l_changeSettleContractOrderSpec.getExpirationDateType();
			
			//������̓��󐔗ʂ��擾����B
			SettleContractEntry[] l_modifiedSettleContractEntries=l_changeSettleContractOrderSpec.getAfterChangeSettleContractEntries();	
            
            //�������͒l���Ó��ł��邩�`�F�b�N����
            l_ifoOrderManagerReusableValidations.validateOrderChangeSpec(
                l_orderUnit,
                l_dblAfterChangeTotalQuantity,
                l_dblAfterChangePrice,
                l_changeExecCondType,
                l_strOrderCond,
                l_strOrderOperatorAfterChange,
                l_strStopOrderBasePriceTypeAfterChange,
                l_dblStopOrderBasePriceAfterChange,
                l_dblWStopPriceAfterChange,
                l_wLimitExecCondTypeAfterChange,
                l_datExpirationDateAfterChange,
                l_strAfterExpirationDateType,
				l_modifiedSettleContractEntries);

            //validate����������Rev���(IfoOrderUnit, double, double, IfoOrderExecutionConditionType)  
            //[validate����������Rev���()�Ɏw�肷�����] 
            //�����P�ʁF�@@�������i�������j�̒����P�ʃI�u�W�F�N�g 
            //�������ʁF�@@getAfterChangeTotalQuantiy() 
            //�����w�l�F�@@getAfterChangePrice() 
            //�������s�����F�@@get�������s����()
            l_ifoOrderManagerReusableValidations.validateChangeOrderRevLimit(
                (IfoOrderUnit)l_orderUnit,
                l_dblAfterChangeTotalQuantity,
                l_dblAfterChangePrice,
                l_changeExecCondType);

            l_orderValidationResult = new OrderValidationResult(
                ProcessingResult.newSuccessResultInstance());
        }
        catch (NotFoundException l_nfex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_nfex);
            //��O���X���[����
            return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80003));
        }
        catch (DataQueryException l_dqex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_dqex);
            //��O���X���[����
            return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80003));
        }
        catch (DataNetworkException l_dnex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_dnex);
            //��O���X���[����
            return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80003));
        }
        catch (OrderValidationException l_ovex)
        {
            log.error(STR_METHOD_NAME, l_ovex);
            return l_ovex.getValidationResult();      
        }
        catch (WEB3BaseException l_webx)
        {
            //��O���X���[����
            return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(l_webx.getErrorInfo()));
        }
        log.exiting(STR_METHOD_NAME);

        return l_orderValidationResult;
    }

    /**
     * (validate�敨�V�K����������)<BR>
     * this.validate�敨�V�K����������()�ɏ������Ϗ��idelegate�j����B<BR>
     * <BR>
     * [validate�敨�V�K����������()�Ɏw�肷�����]<BR>
     * �⏕�����F�@@�p�����[�^.�⏕����<BR>
     * �敨�����������e�F�@@�p�����[�^.�V�K���������e<BR>
     * isSkip�x���󋵃`�F�b�N�F�@@false�i�Œ�j<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_ifoOpenContractChangeSpec - (�V�K���������e)<BR>
     * �V�K���������e�I�u�W�F�N�g<BR>
     * @@return OrderValidationResult
     */
    public OrderValidationResult validateFuturesChangeOrder(
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoOpenContractChangeSpec l_ifoOpenContractChangeSpec)
    {
        final String STR_METHOD_NAME = 
            "validateFuturesChangeOrder(l_subAccount, l_ifoOpenContractChangeSpec)";
        log.entering(STR_METHOD_NAME);

        //this.validate�敨�V�K����������()�ɏ������Ϗ��idelegate�j����B
        OrderValidationResult l_orderValidationResult =
            this.validateFuturesChangeOrder(
                l_subAccount,
                l_ifoOpenContractChangeSpec,
                false);

        log.exiting(STR_METHOD_NAME);
        return l_orderValidationResult;
    }

    /**
     * (validate�敨�V�K����������)<BR>
     * �敨�V�K���������������R��<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨�����jvalidate�V�K�����������v�Q�ƁB<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_ifoOpenContractChangeSpec - (�V�K���������e)<BR>
     * �V�K���������e�I�u�W�F�N�g<BR>
     * @@param l_blnIsSkipDelayStatusCheck - (isSkip�x���󋵃`�F�b�N)<BR>
     * isSkip�x���󋵃`�F�b�N<BR>
     * @@return OrderValidationResult
     * @@roseuid 40AB133400E1
     */
    public OrderValidationResult validateFuturesChangeOrder(
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoOpenContractChangeSpec l_ifoOpenContractChangeSpec,
        boolean l_blnIsSkipDelayStatusCheck)
    {
        final String STR_METHOD_NAME =
            "validateFuturesChangeOrder(l_subAccount, l_ifoOpenContractChangeSpec, l_blnIsSkipDelayStatusCheck)";
        log.entering(STR_METHOD_NAME);
        if (l_subAccount == null || l_ifoOpenContractChangeSpec == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //��O���X���[����
            return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80017)); 
        }

        //�����`�F�b�N���ʃI�u�W�F�N�g
        OrderValidationResult l_orderValidationResult = null;

        //�敨OP�����R���ʃ`�F�b�N�I�u�W�F�N�g�𐶐�
        WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations = null;

        l_ifoOrderManagerReusableValidations = (WEB3IfoOrderManagerReusableValidations) WEB3IfoOrderManagerReusableValidations.getInstance();

        try
        {
            //�����`�F�b�N���s��
            this.validateOrder(l_subAccount, WEB3FuturesOptionDivDef.FUTURES);

            //����ID���擾
            long l_lngOrderID = 0L;
            l_lngOrderID = l_ifoOpenContractChangeSpec.getOrderId();

            //�����I�u�W�F�N�g���擾
            Order l_order = null;
            l_order = this.getOrder(l_lngOrderID); //Throw NotFoundException

            //�������̃X�e�C�^�X�������\�ȏ�Ԃ��𔻒肷��B
            //[validate���������\���()�Ɏw�肷�����] 
            // �����F�@@�i�����h�c�ɊY�����钍���I�u�W�F�N�g�j 
            // isSkip�x���󋵃`�F�b�N�F������isSkip�x���󋵃`�F�b�N
            l_ifoOrderManagerReusableValidations.validateOrderForChangeability(
                l_order,
                l_blnIsSkipDelayStatusCheck);

            //�����P��ID���擾
            long l_lngOrderUnitID = 0L;
            l_lngOrderUnitID = l_ifoOpenContractChangeSpec.getOrderUnitId();

            //�����P�ʃI�u�W�F�N�g���擾
            OrderUnit l_orderUnit = null;
            l_orderUnit = this.getOrderUnit(l_lngOrderUnitID);

            //�����P��Row�I�u�W�F�N�g���擾
            IfoOrderUnitRow l_ifoOrderUnitRow = null;
            l_ifoOrderUnitRow = (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();

            //�敨OP�����I�u�W�F�N�g
            WEB3IfoProductImpl l_ifoProductImpl = null;
            //�����̃`�F�b�N
            l_ifoProductImpl = l_ifoOrderManagerReusableValidations.validateProductID(l_ifoOrderUnitRow.getProductId());

            //�s��I�u�W�F�N�g
            WEB3GentradeMarket l_market = null;
            //�s��̃`�F�b�N
            l_market = (WEB3GentradeMarket) l_ifoOrderManagerReusableValidations.validateMarketID(l_ifoOrderUnitRow.getMarketId());

            IfoContractOpenOrderUnitImpl l_ifoContractOpenOrderUnitImpl = null;
            l_ifoContractOpenOrderUnitImpl = new IfoContractOpenOrderUnitImpl(l_lngOrderUnitID);

            //�����敪���擾
            boolean l_blnIsBuyContract = false;
            if (SideEnum.BUY.equals(l_ifoContractOpenOrderUnitImpl.getSide()))
            {
                l_blnIsBuyContract = true;
            }
            else
            {
                l_blnIsBuyContract = false;
            }

            //��������I�u�W�F�N�g���擾
            WEB3IfoTradedProductImpl l_ifoTradedProductImpl = null;

            //��������̃`�F�b�N
            l_ifoTradedProductImpl = l_ifoOrderManagerReusableValidations.validateTradedProduct(l_ifoProductImpl, l_market, l_blnIsBuyContract, true);

            //���͎w�����戵�\���𔻒肷��
            l_ifoOrderManagerReusableValidations.validateHandlingIndex(l_subAccount.getWeb3GenBranch().getBranchCode(), l_ifoTradedProductImpl);

            //is���s���擾����
            boolean l_blnIsAfterChangePriceMarket = false;
            l_blnIsAfterChangePriceMarket = l_ifoOpenContractChangeSpec.isAfterChangePriceMarket();

            //�������������擾����
            Date l_datChangeExpirationDate = null;
            l_datChangeExpirationDate = l_ifoOpenContractChangeSpec.getChangeExpirationDate();

            //�����������擾����
            String l_strOrderCond = null;
            l_strOrderCond = l_ifoOpenContractChangeSpec.getOrderCond();

            //�������s�������擾����
            IfoOrderExecutionConditionType l_changeExecCondType = null;
            l_changeExecCondType = l_ifoOpenContractChangeSpec.getChangeExecCondType();

            //���������敪���擾����
			String l_strExpirationDateType = null;
			l_strExpirationDateType = l_ifoOpenContractChangeSpec.getExpirationDateType();
			
            //�敨�����}�l�[�W��.get���񒍕��̒����P��(�����P��).���������擾����
            IfoOrderUnitRow l_firstOrderUnitRow =
                (IfoOrderUnitRow)this.getFirstOrderUnit((IfoOrderUnit)l_orderUnit).getDataSourceObject();
            Date l_bizDate = WEB3DateUtility.getDate(l_firstOrderUnitRow.getBizDate(),"yyyyMMdd");
	
            //���������̃`�F�b�N���s��
            l_ifoOrderManagerReusableValidations.validateOrderCond(
                l_subAccount,
                l_lngOrderUnitID,
                l_blnIsAfterChangePriceMarket,
                l_ifoTradedProductImpl,
                true,
                l_blnIsBuyContract,
                l_bizDate,
                l_datChangeExpirationDate,
                l_strOrderCond,
                l_changeExecCondType,
				l_strExpirationDateType,
                new Long(l_firstOrderUnitRow.getOrderUnitId()));

            //�����㐔�ʂ��擾����
            double l_dblAfterChangeOriginalQuantity = 0D;
            l_dblAfterChangeOriginalQuantity = l_ifoOpenContractChangeSpec.getAfterChangeOriginalQuantity();

            //���ʂ�0�܂��́A�}�C�i�X�l�łȂ����Ƃ̃`�F�b�N���s��
            //Throw OrderValidationException
            l_ifoOrderManagerReusableValidations.validateQuantity(l_dblAfterChangeOriginalQuantity);

            //������w�l���擾����
            double l_dblAfterChangePrice = 0D;
            l_dblAfterChangePrice = l_ifoOpenContractChangeSpec.getAfterChangePrice();

            //�w�l�̃`�F�b�N���s��
            l_ifoOrderManagerReusableValidations.validateOrderUnitPrice(l_dblAfterChangePrice, l_ifoTradedProductImpl, l_subAccount);

            //validate�ǌ���������t�\
            //�����^�C�v�F�@@�����P��.�����^�C�v
            //�敨�^�I�v�V�����敪�F�@@�����P��.�敨�^�I�v�V�����敪
            //���X�F�@@�⏕����.get�戵�X()
            //����敪�F�@@�����P��.����敪
            //�������F�@@�����P��.������
            Date l_datBizDate = WEB3DateUtility.getDate(
                l_ifoOrderUnitRow.getBizDate(), "yyyyMMdd");

            WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
                l_ifoOrderUnitRow.getProductType(),
                l_ifoOrderUnitRow.getFutureOptionDiv(),
                l_subAccount.getWeb3GenBranch(),
                l_ifoOrderUnitRow.getSessionType(),
                l_datBizDate);

            //������̔����������Z�q���擾����
            String l_strOrderOperatorAfterChange = null;
            l_strOrderOperatorAfterChange = l_ifoOpenContractChangeSpec.getOrderCondOperator();

            //������̋t�w�l��l�^�C�v���擾����
            String l_strStopOrderBasePriceTypeAfterChange = null;
            l_strStopOrderBasePriceTypeAfterChange = l_ifoOpenContractChangeSpec.getStopOrderBasePriceType();

            //������̋t�w�l��l���擾����
            double l_dblStopOrderBasePriceAfterChange = 0D;
            l_dblStopOrderBasePriceAfterChange = l_ifoOpenContractChangeSpec.getStopOrderBasePrice();
            
            //������́iW�w�l�j�����w�l���擾����B
            double l_dblWStopPriceAfterChange = l_ifoOpenContractChangeSpec.getWLimitPriceChange();

            //������̎��������擾����
            Date l_datExpirationDateAfterChange = null;
            l_datExpirationDateAfterChange = l_ifoOpenContractChangeSpec.getChangeExpirationDate();

            //W�w�l�̎��s�������擾����B
            IfoOrderExecutionConditionType l_wLimitExecCondType = l_ifoOpenContractChangeSpec.getWLimitExecCondType();            
            
            //is����
            boolean l_blnIsBuyToOpenOrder = false;
            if (SideEnum.BUY.equals(l_orderUnit.getSide()))
            {
                l_blnIsBuyToOpenOrder = true;
            }
            
            String l_strWLimitPriceChange = 
                WEB3StringTypeUtility.formatNumber(l_dblWStopPriceAfterChange);
            
            //validateW�w�l����()
            l_ifoOrderManagerReusableValidations.validateWLimitPriceOrder(
                l_subAccount,
                l_lngOrderUnitID,
                l_dblAfterChangePrice,
                l_strOrderCond,
                l_dblStopOrderBasePriceAfterChange,
                l_strWLimitPriceChange,
                l_wLimitExecCondType,
                l_ifoOpenContractChangeSpec.getWLimitEnableStatusDiv(),
                l_ifoTradedProductImpl,
                true,
                l_blnIsBuyToOpenOrder);
            
            //������̒��������敪���擾����
            String l_strAfterExpirationDateType = l_ifoOpenContractChangeSpec.getExpirationDateType();

			//������̒����ԍό��ʃG���g�����擾����B
			SettleContractEntry[] l_modifiedSettleContractEntries = null;

            //�������͒l���Ó��ł��邩�`�F�b�N����
            l_ifoOrderManagerReusableValidations.validateOrderChangeSpec(
                l_orderUnit,
                l_dblAfterChangeOriginalQuantity,
                l_dblAfterChangePrice,
                l_changeExecCondType,
                l_strOrderCond,
                l_strOrderOperatorAfterChange,
                l_strStopOrderBasePriceTypeAfterChange,
                l_dblStopOrderBasePriceAfterChange,
                l_dblWStopPriceAfterChange,
                l_wLimitExecCondType,
                l_datExpirationDateAfterChange,
                l_strAfterExpirationDateType,
				l_modifiedSettleContractEntries);

            //validate����������Rev���(IfoOrderUnit, double, double, IfoOrderExecutionConditionType)
            //[validate����������Rev���()�Ɏw�肷�����] 
            // �����P�ʁF�@@�������i�������j�̒����P�ʃI�u�W�F�N�g 
            // �������ʁF�@@getAfterChangeOriginalQuantity() 
            // �����w�l�F�@@getAfterChangePrice() 
            // �������s�����F�@@get�������s����()
            l_ifoOrderManagerReusableValidations.validateChangeOrderRevLimit(
                (IfoOrderUnit)l_orderUnit,
                l_dblAfterChangeOriginalQuantity,
                l_dblAfterChangePrice,
                l_changeExecCondType);

            l_orderValidationResult = new OrderValidationResult(
                ProcessingResult.newSuccessResultInstance());
        }
        catch (DataNetworkException l_dnex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_dnex);
            //��O���X���[����
            //throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME);
            return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80003)); 
        }
        catch (DataQueryException l_dqex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_dqex);
            //��O���X���[����
            //throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME);
            return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80003)); 
        }
        catch (NotFoundException l_nfex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_nfex);
            //��O���X���[����
            //throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME);
            return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80003)); 
        }
        catch (WEB3BaseException l_webx)
        {
            //��O���X���[����
            return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(l_webx.getErrorInfo()));
        }

        catch (OrderValidationException  l_ovx)
        {
            log.error(STR_METHOD_NAME, l_ovx);            
            return l_ovx.getValidationResult();
        }
        log.exiting(STR_METHOD_NAME);
        return l_orderValidationResult;
    }

    /**
     * (validate�������)<BR>
     * �ivalidateCancelOrder�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * ��������R��<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨�����jvalidate��������v�Q�ƁB<BR>
     * @@param l_subAccount - �⏕�����I�u�W�F�N�g<BR>
     * @@param l_cancelOrderSpec - ����������e<BR>
     * @@return OrderValidationResult
     * @@roseuid 40AB13340100
     */
    public OrderValidationResult validateFuturesCancelOrder(WEB3GentradeSubAccount l_subAccount, CancelOrderSpec l_cancelOrderSpec) 
    {
        final String STR_METHOD_NAME = "validateFuturesCancelOrder(l_subAccount,l_cancelOrderSpec)";
        log.entering(STR_METHOD_NAME);
        if (l_subAccount == null || l_cancelOrderSpec == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //��O���X���[����
            return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80017)); 
        }
        //�����`�F�b�N���ʃI�u�W�F�N�g
        OrderValidationResult l_orderValidationResult = null;

        //�敨OP�����R���ʃ`�F�b�N�I�u�W�F�N�g�𐶐�
        WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations = null;

        l_ifoOrderManagerReusableValidations = (WEB3IfoOrderManagerReusableValidations) WEB3IfoOrderManagerReusableValidations.getInstance();

        try
        {
            //�����`�F�b�N���s��
            this.validateOrder(l_subAccount, WEB3FuturesOptionDivDef.FUTURES);

            //����ID���擾
            long l_lngOrderID = 0L;
            l_lngOrderID = l_cancelOrderSpec.getOrderId();

            //�����I�u�W�F�N�g���擾
            Order l_order = null;
            l_order = this.getOrder(l_lngOrderID); //Throw NotFoundException

            //��������\��Ԃ��`�F�b�N
            //Throw OrderValidationException
            l_ifoOrderManagerReusableValidations.validateOrderForCancellation(l_order);

            //�����P�ʃI�u�W�F�N�g
            OrderUnit l_orderUnit = null;
            l_orderUnit = l_order.getOrderUnits()[0];

            //�����P��ROW�I�u�W�F�N�g
            IfoOrderUnitRow l_ifoOrderUnitRow = null;
            l_ifoOrderUnitRow = (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();
            
            //�s��̃`�F�b�N���s���A�s��I�u�W�F�N�g��ԋp����B
            l_ifoOrderManagerReusableValidations.validateMarketID(l_ifoOrderUnitRow.getMarketId());

            //validate�ǌ���������t�\
            //�����^�C�v�F�@@�����P��.�����^�C�v
            //�敨�^�I�v�V�����敪�F�@@�����P��.�敨�^�I�v�V�����敪
            //���X�F�@@�⏕����.get�戵�X()
            //����敪�F�@@�����P��.����敪
            //�������F�@@�����P��.������
            Date l_datBizDate = WEB3DateUtility.getDate(
                l_ifoOrderUnitRow.getBizDate(), "yyyyMMdd");

            WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
                l_ifoOrderUnitRow.getProductType(),
                l_ifoOrderUnitRow.getFutureOptionDiv(),
                l_subAccount.getWeb3GenBranch(),
                l_ifoOrderUnitRow.getSessionType(),
                l_datBizDate);

            l_orderValidationResult = new OrderValidationResult(ProcessingResult.newSuccessResultInstance());

        }
        catch (NotFoundException l_nfex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_nfex);
            //��O���X���[����
            return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80003)); 
        }
        catch (OrderValidationException l_ovex)
        {
            log.error(STR_METHOD_NAME, l_ovex);
            return l_ovex.getValidationResult(); 
        }
        catch (WEB3BaseException l_webx)
        {
            //��O���X���[����
            return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(l_webx.getErrorInfo()));
        }
        log.exiting(STR_METHOD_NAME);

        return l_orderValidationResult;
    }

    /**
     * (calc�T�Z�����)<BR>
     * �T�Z�����(�萔�����܂܂Ȃ�)���v�Z�����ʂ�ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}�u(�敨����)calc�T�Z������v�Q��<BR>
     * <BR>
     * @@param l_commission - (�萔��)<BR>
     * @@param l_dblLimitPrice - �w�l<BR>
     * <BR>
     * 0���w�肳�ꂽ�ꍇ�́A�������v�Z�P���Ƃ��ė��p����B<BR>
     * 
     * @@param l_subAccount - (�⏕����)<BR>
     * @@param l_futuresOptionTradedProduct - �敨OP�������<BR>
     * @@param l_dblQuantity - ����<BR>
     * @@param l_blnIsSkipPriceCheck - (isSkip���z�`�F�b�N)<BR>
     * �v�Z���ʂ̑���ɂ��āA�Ó����`�F�b�N���X�L�b�v���邩�̃t���O�B<BR>
     * <BR>
     * �`�F�b�N���s���ꍇ��false�A�`�F�b�N���s��Ȃ��i�X�L�b�v����j�ꍇ��true���w�肷��B<BR>
     * 
     * @@return webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult
     * @@roseuid 40B1B1BB0085
     */
    public WEB3IfoEstimateDeliveryAmountCalcResult calcEstimatePrice(
        WEB3GentradeCommission l_commission,
        double l_dblLimitPrice,
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoTradedProductImpl l_futuresOptionTradedProduct,
        double l_dblQuantity,
        boolean l_blnIsSkipPriceCheck)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "calcEstimatePrice(WEB3GentradeCommission, double, WEB3GentradeSubAccount, " 
        + "WEB3IfoTradedProductImpl, double, boolean)";
        log.entering(STR_METHOD_NAME);
        
        //�߂�l�̊T�Z��n����v�Z���ʃI�u�W�F�N�g�𐶐�����B
        WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult = new WEB3IfoEstimateDeliveryAmountCalcResult();
        
    try {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
            //���XID���擾����
            long l_lngBranchID = l_commission.getBranchId();

            //���X�I�u�W�F�N�g���擾����
            WEB3GentradeBranch l_branch = (WEB3GentradeBranch) l_finApp.getAccountManager().getBranch(l_lngBranchID);

            //�v�Z�T�[�r�X�I�u�W�F�N�g���쐬����
            WEB3IfoBizLogicProvider l_ifoBizLogicProvider = null;
            l_ifoBizLogicProvider = (WEB3IfoBizLogicProvider) l_finApp.getTradingModule(ProductTypeEnum.IFO).getBizLogicProvider();

            //�敨OP�����R���ʃ`�F�b�N�I�u�W�F�N�g���쐬����
            WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations = (WEB3IfoOrderManagerReusableValidations) WEB3IfoOrderManagerReusableValidations.getInstance();
            
            double l_dblCalcUnitPrice = 0D;
            String l_strProductCode = l_commission.getCommissionProductCode();
            WEB3IfoProductManagerImpl l_productManager = (WEB3IfoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();
            double l_dblcalcRestraintTurnover = 0D;
            
            if (l_dblLimitPrice == 0)
            {
                l_dblCalcUnitPrice = l_productManager.getCurrentPrice(l_futuresOptionTradedProduct);
                l_ifoEstimateDeliveryAmountCalcResult.setCalcUnitPrice(l_dblCalcUnitPrice);
            }
            else
            {
                l_ifoEstimateDeliveryAmountCalcResult.setCalcUnitPrice(l_dblLimitPrice);
            }

			l_dblcalcRestraintTurnover =
			l_ifoBizLogicProvider.calcRestraintTurnOver(l_dblQuantity,
																	l_ifoEstimateDeliveryAmountCalcResult.getCalcUnitPrice(),
																	l_lngBranchID,
																	l_strProductCode,
																	l_commission.isLimitPrice(),
																	l_futuresOptionTradedProduct);

            l_ifoEstimateDeliveryAmountCalcResult.setRestraintTurnover(l_dblcalcRestraintTurnover);
            MainAccountRow l_mainAccountRow = (MainAccountRow) l_subAccount.getMainAccount().getDataSourceObject();
            
            //isSkip���z�`�F�b�N == false�̏ꍇ�̂݁A����\������z�`�F�b�N���s��
            if (!l_blnIsSkipPriceCheck)
            {
                l_ifoOrderManagerReusableValidations.validateOrderMaxAmount(
                    l_branch,
                    l_dblcalcRestraintTurnover,
                    l_mainAccountRow.getAccountType(),
                    WEB3FuturesOptionDivDef.FUTURES);
            }
            
            //�T�Z��n������Z�b�g���� 
            l_ifoEstimateDeliveryAmountCalcResult.setEstimateDeliveryAmount(l_dblcalcRestraintTurnover);
            l_commission.setExpensesCalcAmount(l_dblcalcRestraintTurnover);
            
            //�ϑ��萔�����Z�o����
            l_ifoBizLogicProvider.calcCommission(l_commission,l_subAccount);
            
            //�ϑ��萔���ɂ��������ł��Z�o����
            double l_dblSalesTax = l_ifoBizLogicProvider.calcSalesTax(l_commission.getCommission(),l_commission.getOrderBizDate(),l_subAccount);
            
            //�萔���R�[�X���Z�b�g����
            l_ifoEstimateDeliveryAmountCalcResult.setCommissionCourse(l_commission.getCommissionCourseDiv());
            
            //�萔�����Z�b�g����
            l_ifoEstimateDeliveryAmountCalcResult.setCommission(l_commission.getCommission());
            
            //�萔������ł��Z�b�g����
            l_ifoEstimateDeliveryAmountCalcResult.setCommissionTax(l_dblSalesTax);
            
            return l_ifoEstimateDeliveryAmountCalcResult;
        }
        catch (NotFoundException l_nfex)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME);
        }
    }

    /**
     * (calc�������T�Z�����)<BR>
     *�����������̊T�Z�����(�萔�����܂܂Ȃ�)���Z�o���ĕԋp����B<BR>
     *�V�[�P���X�}�u(�敨����)calc�������T�Z������v�Q�ƁB<BR>
     * @@param l_commission - (�萔��)<BR>
     * @@param l_dblLimitPrice - �w�l<BR>
     * <BR>
     * 0���w�肳�ꂽ�ꍇ�́A�������v�Z�P���Ƃ��ė��p����B<BR>
     * 
     * @@param l_subAccount - (�⏕����)<BR>
     * @@param l_futuresOptionTradedProduct - �敨OP�������<BR>
     * @@param l_dblQuantity - (����)<BR>
     * ������������<BR>
     * 
     * @@param l_dblExecQuantity -��萔��
     *  �����P��.��萔��<BR>
     * @@param l_dblSumTransferredAssetBookValue -���v�����z 
     * �����P��.���v�����z<BR>
     * @@param l_blnIsSkipPriceCheck - (isSkip���z�`�F�b�N)<BR>
     * �v�Z���ʂ̑���ɂ��āA�Ó����`�F�b�N���X�L�b�v���邩�̃t���O�B<BR>
     * <BR>
     * �`�F�b�N���s���ꍇ��false�A�`�F�b�N���s��Ȃ��i�X�L�b�v����j�ꍇ��true���w�肷��B<BR>
     * 
     * @@return webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult
     * @@roseuid 40C5AF5C0010
     */
    public WEB3IfoEstimateDeliveryAmountCalcResult calcChangeEstimatePrice(
        WEB3GentradeCommission l_commission,
        double l_dblLimitPrice,
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoTradedProductImpl l_futuresOptionTradedProduct,
        double l_dblQuantity,
        double l_dblExecQuantity,
        double l_dblSumTransferredAssetBookValue,
        boolean l_blnIsSkipPriceCheck)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "calcChangeEstimatePrice(WEB3GentradeCommission, double, WEB3GentradeSubAccount, " 
        + "WEB3IfoTradedProductImpl, double, double, double, boolean)";
        log.entering(STR_METHOD_NAME);

        BigDecimal l_bdSumTransferredAssetBookValue = new BigDecimal(l_dblSumTransferredAssetBookValue + "");

        //�߂�l�̊T�Z��n����v�Z���ʃI�u�W�F�N�g�𐶐�����B
        WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult = new WEB3IfoEstimateDeliveryAmountCalcResult();
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3IfoProductManagerImpl l_productMgr = (WEB3IfoProductManagerImpl) l_tradingModule.getProductManager();
        
        //�v�Z�T�[�r�X�I�u�W�F�N�g���쐬����
        WEB3IfoBizLogicProvider l_ifoBizLogicProvider = null;
        l_ifoBizLogicProvider = (WEB3IfoBizLogicProvider) l_finApp.getTradingModule(ProductTypeEnum.IFO).getBizLogicProvider();
        
        //�敨OP�����R���ʃ`�F�b�N�I�u�W�F�N�g���쐬����
        WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations = null;
        l_ifoOrderManagerReusableValidations =
             (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations.getInstance();

        //���X�h�c���擾����B
        long l_lngBranchId = l_commission.getBranchId();
        
        if (l_dblLimitPrice == 0)
        {
			double l_dblCurrentPrice = l_productMgr.getCurrentPrice(l_futuresOptionTradedProduct);
			l_ifoEstimateDeliveryAmountCalcResult.setCalcUnitPrice(l_dblCurrentPrice);
        }
        else
        {
			l_ifoEstimateDeliveryAmountCalcResult.setCalcUnitPrice(l_dblLimitPrice);
        }
        
                
        //�萔�����i�R�[�h���擾����B
        String l_strCommissionProductCode = l_commission.getCommissionProductCode();
        
        //�S���������
        double l_dblRestraintTurnOver = 0;
        //�S������������v�Z����B
        l_dblRestraintTurnOver = l_ifoBizLogicProvider.calcRestraintTurnOver((l_dblQuantity - l_dblExecQuantity),
                    l_ifoEstimateDeliveryAmountCalcResult.getCalcUnitPrice(),
                    l_lngBranchId,
                    l_strCommissionProductCode,
                    l_commission.isLimitPrice(),
                    l_futuresOptionTradedProduct);
        
        BigDecimal l_bdRestraintTurnOver = new BigDecimal(l_dblRestraintTurnOver + "");

        //�߂�l�I�u�W�F�N�g�ɍS������������Z�b�g����B
        log.debug("�S��������� = " + l_dblRestraintTurnOver);
        l_ifoEstimateDeliveryAmountCalcResult.setRestraintTurnover(l_dblRestraintTurnOver);
        
        //�萔���I�u�W�F�N�g�ɏ��o��v�Z�p������Z�b�g����B   
        //���o��v�Z�p����F�S��������� + ����.���v�����z
        log.debug("���o��v�Z�p��� = " + (l_bdRestraintTurnOver.add(l_bdSumTransferredAssetBookValue).doubleValue()));
        l_commission.setExpensesCalcAmount(l_bdRestraintTurnOver.add(l_bdSumTransferredAssetBookValue).doubleValue());  
        
        //�߂�l�I�u�W�F�N�g�ɊT�Z��n������Z�b�g����B
        l_ifoEstimateDeliveryAmountCalcResult.setEstimateDeliveryAmount(l_commission.getExpensesCalcAmount());
         
        //isSkip���z�`�F�b�N == false�̏ꍇ�̂݁A����\������z�`�F�b�N���s��
        if (!l_blnIsSkipPriceCheck)
        {
            MainAccountRow l_mainAccountRow = (MainAccountRow) l_subAccount.getMainAccount().getDataSourceObject();
            l_ifoOrderManagerReusableValidations.validateOrderMaxAmount(
                    l_subAccount.getWeb3GenBranch(),
                    l_commission.getExpensesCalcAmount(),
                    l_mainAccountRow.getAccountType(),
                    WEB3FuturesOptionDivDef.FUTURES);
        }
        
        //�ϑ��萔�����Z�o����B
        l_ifoBizLogicProvider.calcCommission(l_commission, l_subAccount);
        
        //�ϑ��萔���ɂ��������ł��Z�o����B
        double l_dblSalesTax = l_ifoBizLogicProvider.calcSalesTax(l_commission.getCommission(),l_commission.getOrderBizDate(),l_subAccount);
        
        //�萔���R�[�X���Z�b�g����B
        l_ifoEstimateDeliveryAmountCalcResult.setCommissionCourse(l_commission.getCommissionCourseDiv());
        
        //�萔�����Z�b�g����B
        log.debug("�萔�����z = " + l_commission.getCommission());
        l_ifoEstimateDeliveryAmountCalcResult.setCommission(l_commission.getCommission());
        
        //�萔������ł��Z�b�g����B
        log.debug("�萔������� = " + l_dblSalesTax);
        l_ifoEstimateDeliveryAmountCalcResult.setCommissionTax(l_dblSalesTax);
        
        //�T�Z��n����v�Z���ʃI�u�W�F�N�g��ԋp����B
        return l_ifoEstimateDeliveryAmountCalcResult;
    }
    
    /**
     * (update�敨�T�Z��n���)<BR>
     * �敨�̊T�Z��n������X�V����B<BR>
     * �P�j�@@�����P�ʃe�[�u���̉��L���ڂ��X�V����B<BR>
     *  �E�T�Z��n���<BR>
     *  �E�����P��<BR>
     *  �E�s�ꂩ��m�F�ς݂̊T�Z��n���<BR>
     *  �E�s�ꂩ��m�F�ς݂̒����P��<BR>
     * �Q�j�@@���������e�[�u���̊T�Z��n������X�V����B<BR>
     *  �V�[�P���X�}�u�i�敨�����jupdate�T�Z��n����v�Q�ƁB<BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g
     * @@return void
     * @@throws WEB3BaseException
     * @@roseuid 40A459EC0365
     */
    public void updateFuturesEstimateDeliveryAmount(OrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateFuturesEstimateDeliveryAmount(l_orderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }

        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
            // �T�Z��n���
            double l_dblEstimateDeliveryAmount = 0D;
            // �����P��
            double l_dblPrice = 0D;
            
            log.debug("�����P��.getOrderOpenStatus() = " + l_orderUnit.getOrderOpenStatus());
            log.debug("�����P��.isFullyExecuted() = " + l_orderUnit.isFullyExecuted());
            log.debug("�����P��.�����敪 = " + l_orderUnit.getExpirationStatus());
            
            //(*1)�S���o���A�܂��́A�����ς̏ꍇ(����.�����P��.isFullyExecuted() == true || ����.�����P��.�����敪 == "�}�[�P�b�g����")
            if ((l_orderUnit.isFullyExecuted()) || 
                OrderExpirationStatusEnum.INVALIDATED_BY_MKT.equals(l_orderUnit.getExpirationStatus()))
            {
                log.debug("�S���o���A�܂��́A�����ς̏ꍇ");
                //1.1.1 get��n���z���v(�����P�� : IfoOrderUnit)
                // �X�V�l�̐ݒ�
                //  �T�Z��n��� = get��n���z���v()
                //  �����P�� = �����P��.�����P���i�����l�̂܂܁j
                l_dblEstimateDeliveryAmount = super.getNetAmount((IfoOrderUnit)l_orderUnit);
                l_dblPrice = l_orderUnitRow.getPrice();
                log.debug("l_dblEstimateDeliveryAmount = " + l_dblEstimateDeliveryAmount);
                log.debug("l_dblPrice = " + l_dblPrice);
            }            
            //(*2)�S���o���A�܂��́A�����ψȊO�̏ꍇ((*1)�ȊO)
            else
            {
                log.debug("�S���o���A�܂��́A�����ψȊO�̏ꍇ");
                //1.2.1 create�萔��(�����P��ID : long)
                // �萔���I�u�W�F�N�g�𐶐�����B
                // [�����̐ݒ�]
                // �����P��ID�F�@@����.�����P��.�����P��ID
                WEB3IfoBizLogicProvider l_bizLogicProvider = (WEB3IfoBizLogicProvider)l_tradingMod.getBizLogicProvider();
                WEB3GentradeCommission l_commission = l_bizLogicProvider.createCommission(l_orderUnit.getOrderUnitId());
    
                //1.2.2 (*)�V�K���̏ꍇ(����.�����P��.�����J�e�S�� == "�敨�V�K������")
                WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(
                            l_orderUnit.getAccountId(),
                            l_orderUnit.getSubAccountId());
                if (OrderCategEnum.IDX_FUTURES_OPEN.equals(l_orderUnit.getOrderCateg()))
                {
                    //1.2.2.1 calc�������T�Z�����()
                    //[����]
                    //  �萔���F create�萔��()�̖߂�l
                    //  �w�l�F �����P��.�w�l
                    //  �⏕�����F �����P��.�⏕����ID����擾�����⏕�����I�u�W�F�N�g
                    //  �敨OP��������F �����P��.getTradedProduct()�̖߂�l
                    //  ���ʁF �����P��.����
                    //  ��萔�ʁF �����P��.��萔��
                    //  ���v�����z�F �����P��.���v�����z
                    //  isSkip���z�`�F�b�N�F true(�X�L�b�v����)
                    WEB3IfoEstimateDeliveryAmountCalcResult l_result = calcChangeEstimatePrice(
                        l_commission,
                        l_orderUnitRow.getLimitPrice(),
                        l_subAccount,
                        (WEB3IfoTradedProductImpl)l_orderUnit.getTradedProduct(),
                        l_orderUnitRow.getQuantity(),
                        l_orderUnitRow.getExecutedQuantity(),
                        l_orderUnitRow.getExecutedAmount(),
                        true);
                         
                    //�X�V�l�̐ݒ�
                    //�T�Z��n��� = calc�������T�Z�����()�̖߂�l�̊T�Z��n����v�Z����.�T�Z��n���
                    //�����P�� = calc�������T�Z�����()�̖߂�l�̊T�Z��n����v�Z����.�v�Z�P��
                    l_dblEstimateDeliveryAmount = l_result.getEstimateDeliveryAmount();
                    l_dblPrice = l_result.getCalcUnitPrice();
                }
                //1.2.3 (*)�ԍς̏ꍇ(����.�����P��.�����J�e�S�� == "�敨�ԍϒ���")
                else if (OrderCategEnum.IDX_FUTURES_CLOSE.equals(l_orderUnit.getOrderCateg()))
                {
                    //1.2.3.1 create�ԍό��ʃG���g��(�����P��ID : long)
                    WEB3IfoPositionManagerImpl l_positionManager = 
                        (WEB3IfoPositionManagerImpl)l_finApp.getTradingModule(
                            ProductTypeEnum.IFO).getPositionManager();
                    SettleContractEntry[] l_contractEntry = l_positionManager.createSettleContractEntry(l_orderUnit.getOrderUnitId());
                     
                    //1.2.3.2 calc�������T�Z���ϑ��v(�萔��, double, SubAccount, �敨OP�������, SettleContractEntry[], double, doubl
                    //[����]
                    //  �萔���F create�萔��()�̖߂�l
                    //  �w�l�F �����P��.�w�l
                    //  �⏕�����F �����P��.�⏕����ID����擾�����⏕�����I�u�W�F�N�g
                    //  �敨OP��������F �����P��.getTradedProduct()�̖߂�l
                    //  �ԍό��ʃG���g���F�@@create�ԍό��ʃG���g��()�̖߂�l
                    //  ���ʁF �����P��.����
                    //  ��萔�ʁF �����P��.��萔��
                    //  �����P��ID�F�@@�����P��.�����P��ID
                    //  isSkip���z�`�F�b�N�F true�i�X�L�b�v����j
                    //  �����F
                    //    �����P��.getSide() = �h���h�̏ꍇ�A�h���h
                    //    �����P��.getSide() = �h���h�̏ꍇ�A�h���h
                    SideEnum l_dealing = null;
                    if (SideEnum.BUY.equals(l_orderUnit.getSide()))
                    {
                        l_dealing = SideEnum.SELL;
                    }
                    else if (SideEnum.SELL.equals(l_orderUnit.getSide()))
                    {
                        l_dealing = SideEnum.BUY;
                    }
                    WEB3IfoEstimateDeliveryAmountCalcResult l_result = calcChangeEstimateSettlementIncome(
                        l_commission,
                        l_orderUnitRow.getLimitPrice(),
                        l_subAccount,
                        (WEB3IfoTradedProductImpl)l_orderUnit.getTradedProduct(),
                        l_contractEntry,
                        l_orderUnitRow.getQuantity(),
                        l_dealing,
                        l_orderUnitRow.getExecutedQuantity(),
                        l_orderUnitRow.getOrderUnitId(),
                        true);

                    //�X�V�l�̐ݒ�
                    //�T�Z��n��� = calc�������T�Z���ϑ��v()�̖߂�l�̊T�Z��n����v�Z����.�T�Z��n���
                    //�����P��          = calc�������T�Z���ϑ��v()�̖߂�l�̊T�Z��n����v�Z����.�v�Z�P��                     
                    l_dblEstimateDeliveryAmount = l_result.getEstimateDeliveryAmount();
                    l_dblPrice = l_result.getCalcUnitPrice();
                }
                
                log.debug("l_dblEstimateDeliveryAmount = " + l_dblEstimateDeliveryAmount);
                log.debug("l_dblPrice = " + l_dblPrice);
            }
            
            //1.3 (*)�����P�ʃe�[�u���̍X�V
            //�ȉ��̏����Œ����P�ʃe�[�u�����������A�擾�����s�����L�X�V���e�ōX�V����B
            //[����]
            //�����P�ʃe�[�u��.�����P��ID =�@@�����P��.�����P��ID 
            //[�X�V���e]
            //�����P�ʃe�[�u��.�����P�� = ��L�Őݒ肵�������P��
            //�����P�ʃe�[�u��.�s�ꂩ��m�F�ς݂̒����P�� = ��L�Őݒ肵�������P��
            //�����P�ʃe�[�u��.�T�Z��n��� = ��L�Őݒ肵���T�Z��n���
            //�����P�ʃe�[�u��.�s�ꂩ��m�F�ς݂̊T�Z��n��� = ��L�Őݒ肵���T�Z��n���
            OrderUnit l_orderUnitSch = getOrderUnit(l_orderUnit.getOrderUnitId());
            IfoOrderUnitRow l_orderUnitRowSch = (IfoOrderUnitRow)l_orderUnitSch.getDataSourceObject();
            IfoOrderUnitParams l_orderUnitParam = new IfoOrderUnitParams(l_orderUnitRowSch);

            l_orderUnitParam.setPrice(l_dblPrice);
            l_orderUnitParam.setConfirmedOrderPrice(l_dblPrice);
            l_orderUnitParam.setEstimatedPrice(l_dblEstimateDeliveryAmount);
            l_orderUnitParam.setConfirmedEstimatedPrice(l_dblEstimateDeliveryAmount);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();    
            l_queryProcessor.doUpdateQuery(l_orderUnitParam);
            log.debug("�����P�ʃe�[�u���̍X�V " + l_orderUnit.getOrderUnitId());
            
            //1.4 (*)���������e�[�u���̍X�V
            //�ȉ��̏����Œ��������e�[�u�����������A�擾�����s�����L�X�V���e�ōX�V����B
            //[����]
            //���������e�[�u��.�����P��ID�@@    =�@@�����P��.�����P��ID and 
            //���������e�[�u��.��������ԍ��@@=�@@�����P��.���������ŏI�ʔ� 
            //[�X�V���e]
            //���������e�[�u��.�T�Z��n����@@= ��L�Őݒ肵���T�Z��n���
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" order_unit_id = ? ");            
            l_sbWhere.append(" and order_action_serial_no = ? ");
    
            Object[] l_objWhere = { 
                String.valueOf(l_orderUnit.getOrderUnitId()),
                String.valueOf(l_orderUnitRow.getLastOrderActionSerialNo())};

            List l_lisRecords = l_queryProcessor.doFindAllQuery(
                IfoOrderActionRow.TYPE,
                l_sbWhere.toString(),
                null,
                "FOR UPDATE",
                l_objWhere);
            if (l_lisRecords.size() > 0)
            {
                IfoOrderActionRow l_actionRow = (IfoOrderActionRow)l_lisRecords.get(0);
                IfoOrderActionParams l_actionParams = new IfoOrderActionParams(l_actionRow);
                l_actionParams.setEstimatedPrice(l_dblEstimateDeliveryAmount);

                l_queryProcessor.doUpdateQuery(l_actionParams);
                log.debug("�����P�ʃe�[�u���̍X�V " + l_actionRow.getOrderActionId());
            }
        }
        catch (DataQueryException l_dqe)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dqe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        catch (NotFoundException l_nfe)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�敨�V�K������)<BR>
     * �敨�V�K�����������R�����s���B<BR>
     * <BR>
     * this.validate�敨�V�K������()�ɏ������Ϗ��idelegate�j����B<BR>
     * <BR>
     * [validate�敨�V�K������()�Ɏw�肷�����]<BR>
     * �@@�⏕�����F�@@�p�����[�^.�⏕����<BR>
     * �@@�V�K���������e�F�@@�p�����[�^.�V�K���������e<BR>
     * �@@�����P�ʁF�@@null<BR>
     * @@param l_subAccount - �⏕����
     * @@param l_openContractOrderSpec - �V�K���������e
     * @@return NewOrderValidationResult
     */
    public NewOrderValidationResult validateFuturesOpenContractOrder(
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoOpenContractOrderSpec l_openContractOrderSpec)
    {
        final String STR_METHOD_NAME =
            "validateFuturesOpenContractOrder(WEB3GentradeSubAccount, WEB3IfoOpenContractOrderSpec)";
        log.entering(STR_METHOD_NAME);
        
        NewOrderValidationResult l_result =
            this.validateFuturesOpenContractOrder(
                l_subAccount,
                l_openContractOrderSpec,
                null);
        
        log.exiting(STR_METHOD_NAME);
        return l_result;
    }
}
@
