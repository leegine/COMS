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
filename	WEB3IfoChangeCancelOrderAcceptUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP���������tUnitService�����N���X(WEB3IfoChangeCancelOrderAcceptUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/8 䈋� (���u) �V�K�쐬  
Revesion History : 2006/7/14 �����F�i���u�j���f��No.504,521�Ή�
Revesion History : 2006/11/28 ������i���u�j���f��No.575�Ή�
Revesion History : 2006/12/20 ���� (���u) ���f��No.613
Revesion History : 2007/01/29 ꎉ� (���u) ���f��No.609
*/
package webbroker3.ifo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderSentMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultChangeOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultChangeOrderSentMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostOrderAcceptStatusDef;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3IfoChangeCancelAcceptedUpdateInterceptor;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.service.delegate.WEB3IfoChangeCancelOrderAcceptUnitService;
import webbroker3.ifo.service.delegate.WEB3IfoFrontOrderService;
import webbroker3.ifo.data.HostFotypeOrderAcceptParams;
import webbroker3.ifo.define.WEB3IfoWLimitEnableStatusDivDef;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�敨OP���������tUnitServiceImpl)<BR>
 * �敨OP���������tUnitService�����N���X<BR>
 * <BR>
 * Plugin���Ɏ����g�����U�N�V����<BR>
 * TransactionalInterceptor<BR>
 * (TransactionalInterceptor.TX_CREATE_NEW)���w�肷��B<BR>
 */
public class WEB3IfoChangeCancelOrderAcceptUnitServiceImpl
    implements WEB3IfoChangeCancelOrderAcceptUnitService
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoChangeCancelOrderAcceptUnitServiceImpl.class);

    /**
     * @@roseuid 41AD654601A5
     */
    public WEB3IfoChangeCancelOrderAcceptUnitServiceImpl()
    {

    }

    /**
     * (notify���������t)<BR>
     * ���������t�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨OP���������t�jnotify���������t�v �Q��<BR>
     * @@param l_hostFotypeOrderAcceptParams - ������t�L���[Params�I�u�W�F�N�g
     * @@throws WEB3BaseException
     * @@roseuid 4190CAC0017E
     */
    public void notifyChangeCancelOrderAccept(HostFotypeOrderAcceptParams l_hostFotypeOrderAcceptParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "notifyChangeCancelOrderAccept(HostFotypeOrderAcceptParams)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

        IfoMarketResponseReceiverCallbackService l_marketResponseReceiverCallbackService =
            (IfoMarketResponseReceiverCallbackService)l_tradingModule.getMarketAdapter().getMarketResponseReceiverCallbackService();

        String l_strInstitutionCode = l_hostFotypeOrderAcceptParams.getInstitutionCode();
        String l_strBranchCode = l_hostFotypeOrderAcceptParams.getBranchCode();
        String l_strRequstCode = l_hostFotypeOrderAcceptParams.getOrderRequestNumber();

        IfoOrderUnit l_orderUnit = null;

        try
        {
            //1.1get�����P��
            l_orderUnit =
                (IfoOrderUnit)l_orderManager.getOrderUnit(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    ProductTypeEnum.IFO,
                    l_strRequstCode);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                getClass().getName() + STR_METHOD_NAME);
        }

        //1.2getOrderUnits
        long l_lngOrderId = l_orderUnit.getOrderId();
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);
        OrderUnit l_orderUnitNew = l_orderUnits[0];
        if (OrderStatusEnum.MODIFIED.equals(l_orderUnitNew.getOrderStatus())
            || OrderStatusEnum.NOT_MODIFIED.equals(l_orderUnitNew.getOrderStatus())
            || OrderStatusEnum.CANCELLED.equals(l_orderUnitNew.getOrderStatus())
            || OrderStatusEnum.NOT_CANCELLED.equals(l_orderUnitNew.getOrderStatus()))
        {
            return;
        }

        //1.3�敨OP���������t�X�V�C���^�Z�v�^(String)(
        WEB3IfoChangeCancelAcceptedUpdateInterceptor l_ifoChangeCancelAcceptedUpdateInterceptor
            = new WEB3IfoChangeCancelAcceptedUpdateInterceptor(l_hostFotypeOrderAcceptParams.getErrorMessage());

        //get�⏕����(����ID : long, �⏕����ID : long)
        //�⏕�������擾����B
        //[����]
        //����ID�F�@@�����P��.����ID
        //�⏕����ID�F�@@�����P��.�⏕����ID
        WEB3GentradeSubAccount l_subAccount = null;
        try
        {
            l_subAccount = (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
                l_orderUnit.getAccountId(),
                l_orderUnit.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�X�g�b�v���������ƂȂ钍���̏ꍇ
        //�@@�@@��t�G���[ ����
        //������� == "��t�ρi�ύX�����j" ����
        //OP�����}�l�[�W��.is�X�g�b�v�����ؑ֒�(�����P��) == true�̏ꍇ
        //�@@�A��t�G���[ ����
        //������� == "��t�ρi��������j" ����
        //�敨OP�f�[�^�A�_�v�^.getW�w�l�p�L����ԋ敪(�����P��) == "���~�b�g�����L��"�̏ꍇ
        if ((WEB3HostOrderAcceptStatusDef.ORDER_ACCEPT_ERROR.equals(l_hostFotypeOrderAcceptParams.getAcceptStatus())
            && OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderUnit.getOrderStatus())
            && l_orderManager.isStopOrderSwitching(l_orderUnit))
            || (WEB3HostOrderAcceptStatusDef.ORDER_ACCEPT_ERROR.equals(l_hostFotypeOrderAcceptParams.getAcceptStatus())
                && OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderUnit.getOrderStatus())
                && WEB3IfoWLimitEnableStatusDivDef.LIMIT_ENABLE.equals(
                    WEB3IfoDataAdapter.getWLimitEnableStatusDiv(l_orderUnit))))
        {
            WEB3IfoProductManagerImpl l_productManager =
                (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();
            IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
            long l_lngProductId = l_ifoOrderUnitRow.getProductId();

            //reset�����R�[�h(�����R�[�h : String)
            //[����]
            //�@@�����R�[�h�F�@@�����P��.����ID�ɊY������敨OP����.get�����Y�����R�[�h()
            try
            {
                WEB3IfoProductImpl l_ifoProduct =
                    (WEB3IfoProductImpl)l_productManager.getProduct(l_lngProductId);
                WEB3GentradeTradingTimeManagement.resetProductCode(l_ifoProduct.getUnderlyingProductCode());
            }
            catch (NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //get�X�g�b�v�����������T�Z����v�Z����(IfoOrderUnit, �⏕����)
            //�X�g�b�v�����������̊T�Z����v�Z���ʂ��擾����B
            //[����]
            //�����P�ʁF�@@�����P��
            //�⏕�����F�@@�⏕����
            WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult =
                l_orderManager.getStopOrderExpireEstimatedPrice(l_orderUnit, l_subAccount);

            //set�T�Z��n����v�Z����(�敨OP�T�Z��n����v�Z����)
            //�T�Z��n����v�Z���ʂ��Z�b�g����B
            //[����]
            //�T�Z��n����v�Z���ʁF�@@get�X�g�b�v�����������T�Z����v�Z����()�̖߂�l
            l_ifoChangeCancelAcceptedUpdateInterceptor.setEstimateDeliveryAmountCalcResult(
                l_estimateDeliveryAmountCalcResult);
        }

        //1.4setThreadLocalPersistenceEventInterceptor
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_ifoChangeCancelAcceptedUpdateInterceptor);        

        //1.5 (*1)������t�����̏ꍇ�̂ݎ��{
        // (*1) ����t���[
        // ������� == OrderStatusEnum.MODIFY_ACCEPTED
        // && ��t�����̏ꍇ�̂ݎ��{�B
        if (OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderUnitNew.getOrderStatus()) 
            && WEB3HostOrderAcceptStatusDef.ORDER_ACCEPT_COMPLETE.equals(
                l_hostFotypeOrderAcceptParams.getAcceptStatus()))
        {
            DefaultChangeOrderSentMarketResponseMessage l_message = 
                new DefaultChangeOrderSentMarketResponseMessage(l_lngOrderId);
            l_marketResponseReceiverCallbackService.process(l_message);
        }

        //1.6 (*2)������t�G���[�̏ꍇ�̂ݎ��{
        // (*2) ����t���[
        // ������� == OrderStatusEnum.MODIFY_ACCEPTED
        // && ��t�G���[�̏ꍇ�̂ݎ��{
        if (OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderUnitNew.getOrderStatus()) 
            && WEB3HostOrderAcceptStatusDef.ORDER_ACCEPT_ERROR.equals(
                l_hostFotypeOrderAcceptParams.getAcceptStatus()))
        {
            DefaultChangeOrderRejectedMarketResponseMessage l_message = 
                new DefaultChangeOrderRejectedMarketResponseMessage(l_lngOrderId);
            l_marketResponseReceiverCallbackService.process(l_message);
        }

        //1.7 (*3)�����t�����̏ꍇ�̂ݎ��{
        // (*3) ����t���[
        // ������� == OrderStatusEnum.CANCEL_ACCEPTED
        // && ��t�����̏ꍇ�̂ݎ��{
        if (OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderUnitNew.getOrderStatus()) 
            && WEB3HostOrderAcceptStatusDef.ORDER_ACCEPT_COMPLETE.equals(l_hostFotypeOrderAcceptParams.getAcceptStatus()))
        {
            DefaultCancelOrderSentMarketResponseMessage l_message = 
                new DefaultCancelOrderSentMarketResponseMessage(l_lngOrderId);
            l_marketResponseReceiverCallbackService.process(l_message);
        }

        //1.8 (*4)�����t�G���[�̏ꍇ�̂ݎ��{
        // (*4) ����t���[
        // ������� == OrderStatusEnum.CANCEL_ACCEPTED
        // && ��t�G���[�̏ꍇ�̂ݎ��{
        if (OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderUnitNew.getOrderStatus()) 
            && WEB3HostOrderAcceptStatusDef.ORDER_ACCEPT_ERROR.equals(
                l_hostFotypeOrderAcceptParams.getAcceptStatus()))
        {
            DefaultCancelOrderRejectedMarketResponseMessage l_message = 
                new DefaultCancelOrderRejectedMarketResponseMessage(l_lngOrderId);
            l_marketResponseReceiverCallbackService.process(l_message);
        }
        
        //1.9 �]�͍Čv�Z(�⏕���� : �⏕����)
        // �⏕�����̃^�C�v != 7�i�؋��������j 
        // && ��t�G���[ �̏ꍇ�̂ݎ��{
        if (!SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType())
            && WEB3HostOrderAcceptStatusDef.ORDER_ACCEPT_ERROR.equals(
                l_hostFotypeOrderAcceptParams.getAcceptStatus()))
        {
			WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
				(WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
			l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);
        }        

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (notify���������t���ԊO)<BR>
     * ���������t���ԊO�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨OP���������t�jnotify���������t���ԊO�v �Q��<BR>
     * @@param l_hostFotypeOrderAcceptParams - (������t�L���[Params)<BR>
     * ������t�L���[Params�I�u�W�F�N�g
     * @@throws WEB3BaseException
     */
    public void notifyChangeCancelOrderAcceptOvertime(
        HostFotypeOrderAcceptParams l_hostFotypeOrderAcceptParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "notifyChangeCancelOrderAcceptOvertime(HostFotypeOrderAcceptParams)";
        log.entering(STR_METHOD_NAME);

        if (l_hostFotypeOrderAcceptParams == null)
        {
            log.debug(STR_METHOD_NAME + "�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        IfoOrderUnit l_ifoUpdateOrderUnit = null;
        boolean l_blnIsCancel = true;

        //1.1 get�����P��(String, String, ProductTypeEnum, String)
        //�L���[���R�[�h�ɊY�����钍���P�ʂ��擾����B
        //�@@[����]
        //�@@�،���ЃR�[�h�@@�F������t�L���[.�،���ЃR�[�h
        //�@@���X�R�[�h�@@�F������t�L���[.���X�R�[�h
        //�@@���i�^�C�v�@@�F�h�敨�I�v�V�����h
        //�@@���ʃR�[�h�@@�F������t�L���[.���ʃR�[�h
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =l_finApp.getTradingModule(ProductTypeEnum.IFO);

        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

        //�敨OP�����T�[�r�X
        WEB3IfoFrontOrderService l_ifoFrontOrderService =
            (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);

        IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_optionOrderManagerImpl.getOrderUnit(
            l_hostFotypeOrderAcceptParams.getInstitutionCode(),
            l_hostFotypeOrderAcceptParams.getBranchCode(),
            ProductTypeEnum.IFO,
            l_hostFotypeOrderAcceptParams.getOrderRequestNumber());

        OrderStatusEnum l_orderStatus = l_orderUnit.getOrderStatus();

        //1.2 ����t���[�F
        // �擾���������P��.������� == OrderStatusEnum.MODIFY_ACCEPTED(��t��(�ύX����))�̏ꍇ�̂�
        if (OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderStatus))
        {
            l_blnIsCancel = false;

            //1.2.1 update�p�̒����P�ʃI�u�W�F�N�gclone�𐶐�����
            IfoOrderUnitRow l_ifoUpdateOrderUnitRow =
                (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

            String l_strOrderRev = l_ifoUpdateOrderUnitRow.getOrderRev();

            IfoOrderUnitParams l_params = new IfoOrderUnitParams(l_ifoUpdateOrderUnitRow);

            String l_strNextOrderRev = l_ifoFrontOrderService.getNextOrderRev(l_strOrderRev);

            //�v���p�e�B�̃Z�b�g
            l_params.setOrderRev(l_strNextOrderRev);
            l_params.setLastUpdatedTimestamp(GtlUtils.getTradingSystem().getSystemTimestamp());

            l_ifoUpdateOrderUnit = (IfoOrderUnit)l_optionOrderManagerImpl.toOrderUnit(l_params);

            //1.2.2 update�����f�[�^(IfoOrderUnit, boolean)
            //[����]
            //�����P�ʁF�@@�쐬���������P�ʂ�clone�I�u�W�F�N�g
            //is�����쐬�F�@@false�i�������쐬���Ȃ��j
            l_optionOrderManagerImpl.updateOrderData(l_ifoUpdateOrderUnit, false);
        }

        //1.3 update�敨OP��������L���[AT��t���ԊO(IfoOrderUnit, IfoOrderUnit, boolean)
        l_ifoFrontOrderService.updateHostFotypeOrderAllAtAcceptOvertime(
            l_ifoUpdateOrderUnit,
            l_orderUnit,
            l_blnIsCancel);

        log.exiting(STR_METHOD_NAME);
    }
}
@
