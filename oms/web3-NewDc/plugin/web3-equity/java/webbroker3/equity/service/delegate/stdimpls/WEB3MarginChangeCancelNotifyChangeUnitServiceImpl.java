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
filename	WEB3MarginChangeCancelNotifyChangeUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p�����������ʒm�����ꌏ�T�[�r�XImpl(WEB3MarginChangeCancelNotifyChangeUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 䈋� (���u) �V�K�쐬
                   2004/12/17 �������F(SRA) �c�Č��Ή��ɂ��C��
                   2005/01/06 �����a��(SRA) JavaDoc�C��
                   2006/11/27 �����F(���u) �i���f���jNo.1032 1046
                   2006/11/28 ��іQ (���u) ���f�� No.1066 1078
*/
package webbroker3.equity.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultChangeOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultChangeOrderRejectedMarketResponseMessage;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CanmodReceiptTypeDef;
import webbroker3.common.define.WEB3ModifiedResultDef;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityEstimatedContractPrice;
import webbroker3.equity.WEB3EquityEstimatedPrice;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityRealizedProfitAndLossPrice;
import webbroker3.equity.WEB3EquityReceiveChangeInterceptor;
import webbroker3.equity.WEB3EquityReceiveChangeSpec;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.data.HostEqtypeOrderClmdReceiptParams;
import webbroker3.equity.service.delegate.WEB3EquityChangeCancelNotifyDataAdapter;
import webbroker3.equity.service.delegate.WEB3EquityExecutedMailSenderService;
import webbroker3.equity.service.delegate.WEB3MarginChangeCancelNotifyChangeUnitService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p�����������ʒm�����ꌏ�T�[�r�XImpl�j�B<BR>
 * <BR>
 * �M�p�����������ʒm�����ꌏ�T�[�r�X�����N���X
 * @@author 䈋�
 * @@version 1.0
 */
public class WEB3MarginChangeCancelNotifyChangeUnitServiceImpl
    implements WEB3MarginChangeCancelNotifyChangeUnitService
{
    /**
      * (���O�o�̓��[�e�B���e�B�B)
      */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MarginChangeCancelNotifyChangeUnitServiceImpl.class);

    /**
     * @@roseuid 4140067002C5
     */
    public WEB3MarginChangeCancelNotifyChangeUnitServiceImpl()
    {

    }

    /**
     * (notify����)<BR>
     * ���������ʒm���������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�M�p�����������ʒm�����ꌏ�T�[�r�X�jnotify�����v�Q��<BR>
     * @@param l_params - (������������ʒm�L���[Params)<BR>
     * ������������ʒm�L���[Params�I�u�W�F�N�g�B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B<BR>
     * @@roseuid 40F3DA5702CD
     */
    public void notifyChange(
        HostEqtypeOrderClmdReceiptParams l_params,
        EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "notifyChange(HostEqtypeOrderClmdReceiptParams, EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
        try
        {
            l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
        }
        catch (NotFoundException l_ne)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ne.getMessage(),
                l_ne);
        }
        
        //1.1. validate�������ʃR�[�h()
        validateChangeResultCode(l_params);
        
        //1.2. create()
        WEB3EquityChangeCancelNotifyDataAdapter l_dataAdapter =
            WEB3EquityChangeCancelNotifyDataAdapter.create(l_params);
        
        //1.3. ���������ʒm���e()
        WEB3EquityReceiveChangeSpec l_receiveChangeSpec =
            new WEB3EquityReceiveChangeSpec();
        
        //1.4.get���s����()
        EqTypeExecutionConditionType l_conditionType =
            l_dataAdapter.getExecCondType();
        
        //1.5. set�����㎷�s����()
        l_receiveChangeSpec.setChangeAfterExecCondType(l_conditionType);
        
        //1.6. get�l�i����()
        String l_strPriceConditionType = l_dataAdapter.getPriceConditionType();
        
        //1.7. set������l�i����()
        l_receiveChangeSpec.setChangeAfterPriceConditionType(l_strPriceConditionType);
        
        l_receiveChangeSpec.setChangeAfterOrderRev(l_params.getModifiedOrderRev());
        
        //1.8. create�萔��()
        WEB3EquityBizLogicProvider l_bizLogicProvider =
            (WEB3EquityBizLogicProvider)l_tradingMod.getBizLogicProvider();
        WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingMod.getPositionManager();
        WEB3GentradeCommission l_commission =
            l_bizLogicProvider.createCommission(l_orderUnit.getOrderId());

        // is�X�g�b�v�����L��(EqTypeOrderUnit)
        boolean l_blnIsStopOrderValid = false;
        if (l_orderManager.isStopOrderSwitching(l_orderUnit) 
            && WEB3CanmodReceiptTypeDef.CHANGED.equals(l_params.getCanmodReceiptType()))
        {
            l_blnIsStopOrderValid = true;
        }
        else
        {
            l_blnIsStopOrderValid = l_orderManager.isStopOrderValid(l_orderUnit);
        }
    
        //1.11. getInstitution()
        Institution l_institution = null;
        try
        {
            l_institution = l_accMgr.getInstitution(l_params.getInstitutionCode());
        }
        catch (NotFoundException l_ne)
        {
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
            this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.12. getInstitutionId()
        long l_institutionId = l_institution.getInstitutionId();
        
        MainAccount l_mainAccount = null;
        WEB3GentradeSubAccount l_subAccount = null;
        try
        {
            //1.13. getMainAccount()
            l_mainAccount = l_accMgr.getMainAccount(
                l_institutionId,
                l_params.getBranchCode(),
                l_params.getAccountCode());
            //1.14. getSubAccount()
            l_subAccount =
                (WEB3GentradeSubAccount)l_mainAccount.getSubAccount(
                    SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
        }
        catch (NotFoundException l_ne)
        {
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
            this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //is������(EqTypeOrderUnit)
        boolean l_isSellOrder = l_orderManager.isSellOrder(l_orderUnit);

        //1.16. getTradedProduct()
        WEB3EquityTradedProduct l_tradedProduct =
            (WEB3EquityTradedProduct)l_orderUnit.getTradedProduct();

        //1.18. �V�K�������̏ꍇ
        WEB3EquityEstimatedPrice l_equityEstimatedPrice = null;
        WEB3EquityEstimatedContractPrice l_equityEstimatedContractPrice = null;
        if (OrderCategEnum.OPEN_MARGIN.equals(l_orderUnit.getOrderCateg()))
        {
            //�����������̏ꍇ
            if (l_orderManager.isStopOrderSwitching(l_orderUnit)
                && WEB3CanmodReceiptTypeDef.CHANGE_FAILED.equals(l_params.getCanmodReceiptType()))
            {
                //get�X�g�b�v�����������T�Z����v�Z����(EqtypeOrderUnit, SubAccount)
                l_equityEstimatedPrice =
                    l_orderManager.getStopOrderExpireEstimatedPrice(
                        l_orderUnit,
                        l_subAccount);
            }

            if (l_equityEstimatedPrice == null)
            {
                //calc�����������
                //�萔���F�@@create�萔��()�̖߂�l
                //�w�l�F�@@������������ʒm�L���[Params.������w�l
                //�iW�w�l�j�����w�l�F�@@�����P��.�i�v�w�l�j�����w�l
                //�t�w�l��l�F�@@�����P��.�t�w�l��l
                //���s�����F�@@������������ʒm�f�[�^�A�_�v�^.get���s����()�̖߂�l
                //�iW�w�l�j���s�����F�@@�����P��.�i�v�w�l�j���s����
                //�l�i�����F�@@������������ʒm�f�[�^�A�_�v�^.get�l�i����()�̖߂�l
                //���������F�@@�����P��.��������
                //�m�F���擾�����F�@@null�i�Œ�j
                //is�X�g�b�v�����L���F
                //�@@[�g�����������}�l�[�W��.is�X�g�b�v�����ؑ֒�() == true���A
                //�@@�ʒm�L���[.��������ʒm�敪 == "��������"�̏ꍇ]
                //�@@�@@true�i�Œ�j
                //�@@[��L�ȊO�̏ꍇ]
                //�@@�@@is�X�g�b�v�����L��()�̖߂�l
                //is�����F�@@is������()�̖߂�l
                //�⏕�����F�@@getSubAccount()�̖߂�l
                //��������F�@@getTradedProduct()�̖߂�l
                //�����F�@@�ʒm�L���[Params.�����㐔��
                //��萔�ʁF�@@�����P��.��萔��
                //���v�����z�F�@@�����P��.���v�����z
                //isSkip���z�`�F�b�N�F�@@true(�X�L�b�v����)
                l_equityEstimatedContractPrice =
                    l_orderManager.calcContractAmountAtOrder(
                        l_commission,
                        l_params.getModifiedLimitPrice(),
                        l_orderUnitRow.getWLimitPrice(),
                        l_orderUnitRow.getStopOrderPrice(),
                        l_dataAdapter.getExecCondType(),
                        l_orderUnitRow.getWLimitExecCondType(),
                        l_dataAdapter.getPriceConditionType(),
                        l_orderUnitRow.getOrderConditionType(),
                        null,
                        l_blnIsStopOrderValid,
                        l_isSellOrder,
                        (SubAccount)l_subAccount,
                        (EqTypeTradedProduct)l_tradedProduct,
                        l_params.getModifiedQuantity(),
                        l_orderUnitRow.getExecutedQuantity(),
                        l_orderUnitRow.getExecutedAmount(),
                        true);
                // set�����P��(double)
                l_receiveChangeSpec.setLimitPrice(l_equityEstimatedContractPrice.getCalcUnitPrice());

                //set�T�Z��n���()
                l_receiveChangeSpec.setEstimateDeliveryAmount(
                    l_equityEstimatedContractPrice.getEstimateDeliveryAmount());
            }
            else
            {
                //set�����P��(double)
                l_receiveChangeSpec.setLimitPrice(l_equityEstimatedPrice.getCalcUnitPrice());

                //set�T�Z��n���()
                l_receiveChangeSpec.setEstimateDeliveryAmount(
                    l_equityEstimatedPrice.getEstimateDeliveryAmount());
            }
        }
        //1.19. �ԍϒ����̏ꍇ
        else if (OrderCategEnum.CLOSE_MARGIN.equals(l_orderUnit.getOrderCateg()))
        {
            //1.19.1. adjust�ԍώw����()
            l_positionManager.adjustClosingContractSpecInfo(
                l_mainAccount.getAccountId(),
                l_subAccount.getSubAccountId(),
                l_orderUnit.getOrderId(),
                l_orderUnit.getOrderUnitId(),
                l_orderUnit.getQuantity(),
                l_params.getModifiedQuantity());
            
            //create���ό����G���g��()
            EqTypeSettleContractOrderEntry[] l_entry =
                l_positionManager.createCloseMarginContractEntry(l_orderUnit.getOrderUnitId());

            //setIs�w�l(is�w�l : boolean)
            if (l_params.getModifiedLimitPrice() != 0)
            {
                l_commission.setIsLimitPrice(true);
            }
            else
            {
                l_commission.setIsLimitPrice(false);
            }

            //calc�T�Z���ϑ��v���()
            WEB3EquityRealizedProfitAndLossPrice l_profitAndLossPrice = 
                l_orderManager.calcEstimatedRealizedProfitAndLossAmount(
                    l_commission,
                    l_params.getModifiedLimitPrice(),
                    l_subAccount,
                    l_tradedProduct,
                    l_entry,
                    l_params.getModifiedQuantity(),
                    l_orderUnit,
                    0D,
                    0D,
                    true);            
            
            //set�����P��(double)
            l_receiveChangeSpec.setLimitPrice(l_profitAndLossPrice.getCalcUnitPrice());
            
            //1.19.5. set�T�Z��n���()
            l_receiveChangeSpec.setEstimateDeliveryAmount(
                l_profitAndLossPrice.getEstimatedRealizedProfitAndLossAmount());               
        }
        
        //1.20. �������������ʒm�C���^�Z�v�^()
        WEB3EquityReceiveChangeInterceptor l_interceptor =
            new WEB3EquityReceiveChangeInterceptor(l_params);
        
        //1.21. set���������ʒm���e()
        l_interceptor.setEquityChangeNotifySpec(l_receiveChangeSpec);
        
        //1.22. setThreadLocalPersistenceEventInterceptor()
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);
        
        //1.23. getMarketResponseReceiverCallbackService()
        MarketAdapter l_marketAdapter = l_tradingMod.getMarketAdapter();
        MarketResponseReceiverCallbackService l_marketCallbackService =
            l_marketAdapter.getMarketResponseReceiverCallbackService();
        
        //1.24. setBusinessTimestamp()
        WEB3GentradeTradingTimeManagement.setBusinessTimestamp();
        
        //1.25. ���������̏ꍇ
        if (WEB3CanmodReceiptTypeDef.CHANGED.equals(l_params.getCanmodReceiptType()))
        {
            //1.25.1. DefaultChangeOrderAcceptedMarketResponseMessage()
            DefaultChangeOrderAcceptedMarketResponseMessage l_message =
                new DefaultChangeOrderAcceptedMarketResponseMessage(l_orderUnit.getOrderId());
            //1.25.2 process()
            ProcessingResult l_result = l_marketCallbackService.process(l_message);
            if (l_result.isFailedResult())
            {
                throw new WEB3BaseException(
                    l_result.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_result.getErrorInfo().getErrorMessage());
            }
            
            EqTypeOrderUnit l_newOrderUnit = null;
            try
            {
                l_newOrderUnit =
                    (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
            }
            catch (NotFoundException l_ne)
            {
                throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
	        //1.25.3 isFullyExecuted()
	        boolean l_isFullyExe = l_newOrderUnit.isFullyExecuted();
	        
	        //1.25.4 ������ɑS�����ƂȂ����ꍇ
	        if (l_isFullyExe)
	        {
	            WEB3EquityExecutedMailSenderService l_mailSenderService =
	                (WEB3EquityExecutedMailSenderService)Services.getService(
	                    WEB3EquityExecutedMailSenderService.class);
	            l_mailSenderService.sendMailProcess(l_newOrderUnit, null);
                
                try
                {
                    l_orderManager.notifyRLS(
                        l_newOrderUnit,
                        OrderManagerPersistenceContext.FILL_ORDER);
                }
                catch (WEB3BusinessLayerException l_ex)
                {
                    log.debug("notify���[���G���W���T�[�o()�ɂċƖ��G���[���X���[���ꂽ�ꍇ�A�����[���o�b�N���Ȃ��B");
                }
	        }
        }
        //1.26. �������s�̏ꍇ
        else if(WEB3CanmodReceiptTypeDef.CHANGE_FAILED.equals(l_params.getCanmodReceiptType()))
        {
            //1.26.1. DefaultChangeOrderRejectedMarketResponseMessage()
            DefaultChangeOrderRejectedMarketResponseMessage l_message =
                new DefaultChangeOrderRejectedMarketResponseMessage(l_orderUnit.getOrderId());
            //1.26.2. process()
            ProcessingResult l_result = l_marketCallbackService.process(l_message);
            if (l_result.isFailedResult())
            {
                throw new WEB3BaseException(
                    l_result.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_result.getErrorInfo().getErrorMessage());
            }
        }

        
        //1.27. �]�͍Čv�Z
        WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
            (WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
        l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�������ʃR�[�h)<BR>
     * �������ɂ�����A�y��������ʒm�L���[�e�[�u���z�́u�������ʃR�[�h�v<BR>
     *    �̒l�̐������`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@�R�[�h�l���h0?�h�i���������ɊY���j�̏ꍇ�A<BR>
     *    �ȉ��̒����P�[�X�̂����ꂩ��<BR>�Y�����邩�`�F�b�N����B<BR>
     * �@@�@@�@@������ɂ��Y�����Ȃ��ꍇ�́A��O��throw����B<BR>
     * �@@�@@�@@�@@�@@�@@02�F�S�������i���o���Ȃ��j<BR>
     * �@@�@@�@@�@@�@@�@@03�F�S�������i���o������j<BR>
     * �@@�@@�@@�@@�@@�@@05�F�ꕔ����<BR>
     * �@@�@@�@@�@@�@@�@@08�F�ꕔ�����s�\�i���o���Ȃ��j<BR>
     * �@@�@@�@@�@@�@@�@@09�F�ꕔ�����s�\�i���o������j<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00129<BR>
     * @@param l_eqtypeOrderClmdReceiptParams - (������������ʒm�L���[Params)<BR>
     * @@roseuid 40F3DFF80062
     */
    protected void validateChangeResultCode(HostEqtypeOrderClmdReceiptParams l_eqtypeOrderClmdReceiptParams)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateChangeResultCode(HostEqtypeOrderClmdReceiptParams l_eqtypeOrderClmdReceiptParams)";
        log.entering(STR_METHOD_NAME);
        String l_modifyResult =l_eqtypeOrderClmdReceiptParams.getModifiedResult();
        if((l_modifyResult.length() == 2) && l_modifyResult.startsWith("0"))
        {      
            if (WEB3ModifiedResultDef.ALL_CHANGED_NO_EXECUTED.equals(l_modifyResult)
                || WEB3ModifiedResultDef.ALL_CHANGED_PARTIALLY_EXECUTED.equals(l_modifyResult)
                || WEB3ModifiedResultDef.PARTIALLY_CHANGED.equals(l_modifyResult)
                || WEB3ModifiedResultDef.PARTIALLY_NOT_CHANGED_NO_EXECUTED.equals(l_modifyResult)
                || WEB3ModifiedResultDef.PARTIALLY_NOT_CHANGED_PARTIALLY_EXECUTED.equals(l_modifyResult))
            {
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00129,
                        STR_METHOD_NAME);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
