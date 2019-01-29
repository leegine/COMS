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
filename	WEB3IfoOrderAcceptUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP������tUnitService�����N���X(WEB3IfoOrderAcceptUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/8 䈋� (���u) �V�K�쐬  
Revesion History : 2006/7/13 ������i���u�j���f��No.503�Ή�
Revesion History : 2007/01/25 ���� (���u) �d�l�ύX ���f��605�A608
Revesion History : 2008/03/17 �����F (���u)�d�l�ύX ���f��833
*/
package webbroker3.ifo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostOrderAcceptStatusDef;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3IfoAcceptedUpdateInterceptor;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.service.delegate.WEB3IfoFrontOrderService;
import webbroker3.ifo.service.delegate.WEB3IfoOrderAcceptUnitService;
import webbroker3.ifo.data.HostFotypeOrderAcceptParams;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationIfoOrderUpdateService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�敨OP������tUnitServiceImpl)<BR>
 * �敨OP������tUnitService�����N���X<BR>
 * <BR>
 * Plugin���Ɏ����g�����U�N�V����<BR>
 * TransactionalInterceptor<BR>
 * (TransactionalInterceptor.TX_CREATE_NEW)���w�肷��B<BR>
 */
public class WEB3IfoOrderAcceptUnitServiceImpl
    implements WEB3IfoOrderAcceptUnitService
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoOrderAcceptUnitServiceImpl.class);

    /**
     * @@roseuid 41AD65460109
     */
    public WEB3IfoOrderAcceptUnitServiceImpl()
    {

    }

    /**
     * (notify������t)<BR>
     * ������t�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨OP������t�jnotify������t�v �Q��<BR>
     * @@param l_hostFotypeOrderAcceptParams - ������t�L���[Params�I�u�W�F�N�g
     * @@roseuid 4190C47100C3
     */
    public void notifyOrderAccept(HostFotypeOrderAcceptParams l_hostFotypeOrderAcceptParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "notifyOrderAccept(HostFotypeOrderAcceptParams)";
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
        AccountManager l_accountManager = l_finApp.getAccountManager();
        WEB3GentradeSubAccount l_subAccount = null;
        
        try
        {
            //1.1get�����P��
            l_orderUnit = (IfoOrderUnit)l_orderManager.getOrderUnit(
                l_strInstitutionCode,
                l_strBranchCode,
                ProductTypeEnum.IFO,
                l_strRequstCode);

            l_subAccount = (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                l_orderUnit.getAccountId(),l_orderUnit.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                getClass().getName() + STR_METHOD_NAME);
        }

        //1.2�敨OP������t�X�V�C���^�Z�v�^
        WEB3IfoAcceptedUpdateInterceptor l_ifoAcceptedUndateInterceptor =
            new WEB3IfoAcceptedUpdateInterceptor(
                l_hostFotypeOrderAcceptParams.getErrorMessage(),
                l_hostFotypeOrderAcceptParams.getSubmitOrderRouteDiv());

        //1.3setThreadLocalPersistenceEventInterceptor
        l_orderManager.setThreadLocalPersistenceEventInterceptor(
            l_ifoAcceptedUndateInterceptor);
        long l_lngOrderId = l_orderUnit.getOrderId();

        //1.4��t�����̏ꍇ
        if (WEB3HostOrderAcceptStatusDef.ORDER_ACCEPT_COMPLETE.equals(
            l_hostFotypeOrderAcceptParams.getAcceptStatus()))
        {
            //1.4.1 DefaultNewOrderAcceptedMarketResponseMessage(long)(
            DefaultNewOrderAcceptedMarketResponseMessage l_defaultNewOrderAcceptedMarketResponseMessage =
                new DefaultNewOrderAcceptedMarketResponseMessage(l_lngOrderId);
            
            //1.4.2 process(��t���� : NewOrderAcceptedMarketResponseMessage)
            ProcessingResult l_prcessResult = 
                l_marketResponseReceiverCallbackService.process(
                    l_defaultNewOrderAcceptedMarketResponseMessage);
            if (l_prcessResult.isFailedResult())
            {
                log.error("l_prcessResult.isFailedResult()");
                throw new WEB3BusinessLayerException(
                    l_prcessResult.getErrorInfo(),
                    STR_METHOD_NAME);
            }   

        }
        else
        {
            //1.5.1 DefaultNewOrderRejectedMarketResponseMessage
            DefaultNewOrderRejectedMarketResponseMessage l_defaultNewOrderRejectedMarketResponseMessage = 
                new DefaultNewOrderRejectedMarketResponseMessage(l_lngOrderId);

            //1.5.2 process(��t���� : NewOrderRejectedMarketResponseMessage)
            ProcessingResult l_prcessResult = 
                l_marketResponseReceiverCallbackService.process(
                    l_defaultNewOrderRejectedMarketResponseMessage);
            if (l_prcessResult.isFailedResult())
            {
                log.error("l_prcessResult.isFailedResult()");
                throw new WEB3BusinessLayerException(
                    l_prcessResult.getErrorInfo(),
                    STR_METHOD_NAME);
            }   

            //is�\�񒍕��m�F�v(IfoOrderUnit)
            boolean l_blnIsReserveOrderExist = l_orderManager.isReserveOrderExist(l_orderUnit);
            //�\�񒍕��m�F�v�iis�\�񒍕��m�F�v() == true�j�̏ꍇ
            if (l_blnIsReserveOrderExist)
            {
                WEB3ToSuccReservationIfoOrderUpdateService l_ifoOrderUpdateService =
                    (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                        WEB3ToSuccReservationIfoOrderUpdateService.class);
                // invalidateAll�\�񒍕��P��(�e�����̒���ID : long)
                l_ifoOrderUpdateService.invalidateAllOrderUnit(l_orderUnit.getOrderId());
            }
            
            //1.5.4 �]�͍Čv�Z(�⏕���� : �⏕����)
            //  [����] 
            //  �⏕�����F get�⏕����()�̖߂�l 
			WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
				(WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
            //�⏕�����̕⏕�����^�C�v != 7�i�؋��������j�̏ꍇ�A���{
            if (!SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType()))
            {
				l_tpTradingPowerReCalcService.reCalcTradingPower((WEB3GentradeSubAccount)l_subAccount);
            }
            
            IfoOrderUnit l_ifoOrderUnit = null;
            try
            {
                l_ifoOrderUnit = (IfoOrderUnit)l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
            } 
            catch (NotFoundException l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    getClass().getName() + STR_METHOD_NAME);
            }                 
            try
            {
                //reset�����R�[�h
                String l_strProductCode =
                    ((IfoProductRow)l_orderUnit.getProduct().getDataSourceObject()).getUnderlyingProductCode();
                WEB3GentradeTradingTimeManagement.resetProductCode(l_strProductCode);
                
                //1.5.5 notify���[���G���W���T�[�o(IfoOrderUnit, OrderManagerPersistenceContext)
                l_orderManager.notifyRLS(l_ifoOrderUnit, OrderManagerPersistenceContext.ORDER_REJECTED_BY_MKT );
            }
            //1.5.6 notify���[���G���W���T�[�o()�ɂċƖ��G���[���X���[���ꂽ�ꍇ
            catch (WEB3BusinessLayerException l_ex)
            {
                log.debug("notify���[���G���W���T�[�o()�ɂċƖ��G���[���X���[���ꂽ�ꍇ�A�����[���o�b�N���Ȃ��B");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (notify��t���ԊO)<BR>
     * ��t���ԊO�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨OP������t�jnotify��t���ԊO�v �Q��<BR>
     * @@param l_hostFotypeOrderAcceptParams - ������t�L���[Params�I�u�W�F�N�g
     */
    public void notifyOrderAcceptOvertime(
        HostFotypeOrderAcceptParams l_hostFotypeOrderAcceptParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "notifyOrderAcceptOvertime(HostFotypeOrderAcceptParams)";
        log.entering(STR_METHOD_NAME);

        if (l_hostFotypeOrderAcceptParams == null)
        {
            log.debug(STR_METHOD_NAME + "�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

        //get�����P��(String, String, ProductTypeEnum, String)
        //[����]
        //�،���ЃR�[�h�@@�F������t�L���[.�،���ЃR�[�h
        //���X�R�[�h�@@�F������t�L���[.���X�R�[�h
        //���i�^�C�v�@@�F�h�敨�I�v�V�����h
        //���ʃR�[�h�@@�F������t�L���[.���ʃR�[�h
        IfoOrderUnit l_ifoOrderUnit =
            (IfoOrderUnit)l_orderManager.getOrderUnit(
                l_hostFotypeOrderAcceptParams.getInstitutionCode(),
                l_hostFotypeOrderAcceptParams.getBranchCode(),
                ProductTypeEnum.IFO,
                l_hostFotypeOrderAcceptParams.getOrderRequestNumber());

        //update�p�̒����P�ʃI�u�W�F�N�gclone�𐶐�����B
        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
        IfoOrderUnitParams l_ifoOrderUnitParams = new IfoOrderUnitParams(l_ifoOrderUnitRow);

        WEB3IfoFrontOrderService l_service =
            (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);

        //����Rev�F�@@�敨OP�����T�[�r�X.getNext����Rev�i�����P��.����Rev.�j
        l_ifoOrderUnitParams.setOrderRev(
            l_service.getNextOrderRev(l_ifoOrderUnitRow.getOrderRev()));

        //�s�ꂩ��m�F�ς̒���Rev�F�@@�敨OP�����T�[�r�X.getNext����Rev�i
        //  �����P��.�s�ꂩ��m�F�ς݂̒���Rev.�j
        l_ifoOrderUnitParams.setConfirmedOrderRev(
            l_service.getNextOrderRev(l_ifoOrderUnitRow.getConfirmedOrderRev()));

        //�X�V���t�F�@@GtlUtils.getTradingSystem().getSystemTimestamp()�i���ݓ����j
        l_ifoOrderUnitParams.setLastUpdatedTimestamp(
            GtlUtils.getTradingSystem().getSystemTimestamp());

        IfoOrderUnit l_ifoOrderUnitClone =
            (IfoOrderUnit)l_orderManager.toOrderUnit(l_ifoOrderUnitParams);

        //update�����f�[�^(IfoOrderUnit, boolean)
        //[����]
        // �����P�ʁF�@@�쐬���������P�ʂ�clone�I�u�W�F�N�g
        // is�����쐬�F�@@false�i�������쐬���Ȃ��j
        l_orderManager.updateOrderData(l_ifoOrderUnitClone, false);

        //update�敨OP��������L���[AT��t���ԊO(IfoOrderUnit, IfoOrderUnit, boolean)
        //[����]
        // �����P�ʁi�X�V��j�F�@@�쐬���������P�ʂ�clone�I�u�W�F�N�g
        // �����P�ʁi�X�V�O�j�F�@@OP�����}�l�[�W��.get�����P��()�Ŏ擾���������P�ʃI�u�W�F�N�g
        // is����F�@@false�i����ȊO�j
        l_service.updateHostFotypeOrderAllAtAcceptOvertime(l_ifoOrderUnitClone, l_ifoOrderUnit, false);

        log.exiting(STR_METHOD_NAME);
    }
}
@
