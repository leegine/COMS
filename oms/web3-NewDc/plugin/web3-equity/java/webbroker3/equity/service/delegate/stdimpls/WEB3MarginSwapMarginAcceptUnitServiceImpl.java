head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginSwapMarginAcceptUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����������n��t�ꌏ�T�[�r�X����(WEB3MarginSwapMarginAcceptUnitServiceImpl.java)
Author Name      : 2004/10/8 Ḗ@@��(���u) �V�K�쐬
Revesion History : 2004/12/13 �����a��(SAR) �c�Č��Ή� �m��.�Q�T�X���m��.�R�T�T
                   2004/01/06 �����a��(SRA) JavaDoc�C��
*/

package webbroker3.equity.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderSentMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderRejectedMarketResponseMessage;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AcceptStatusDef;
import webbroker3.common.define.WEB3HostOrderAcceptStatusDef;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3MarginSwapMarginAcceptInterceptor;
import webbroker3.equity.data.HostEqtypeSwapAcceptParams;
import webbroker3.equity.service.delegate.WEB3MarginSwapMarginAcceptUnitService;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationEqTypeOrderUpdateService;
import webbroker3.util.WEB3LogUtility;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import java.util.Map;
import com.fitechlabs.xtrade.kernel.data.Processors;
import java.util.HashMap;
import webbroker3.common.define.WEB3StatusDef;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;

/**
 * �i�M�p����������n��t�ꌏ�T�[�r�XImpl�j�B<BR>
 * <BR>
 * �M�p����������n��t�ꌏ�T�[�r�X�����N���X
 * @@author �@@��
 * @@version 1.0
 */
public class WEB3MarginSwapMarginAcceptUnitServiceImpl implements WEB3MarginSwapMarginAcceptUnitService
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3MarginSwapMarginAcceptUnitServiceImpl.class);

    /**
     * @@roseuid 41419C070008
     */
    public WEB3MarginSwapMarginAcceptUnitServiceImpl()
    {

    }

    /**
     * (notify�������n��t)<BR>
     *�y�������n��t�L���[�e�[�u���z�L���[�f�[�^�ꌏ�ɑ΂��鏈�����s���B
     * @@param l_swapMarginAcceptQueParams - (�������n��t�L���[Params)<BR>
     * @@roseuid 41010FA900A1
     */
    public void notifySwapMarginAccept(HostEqtypeSwapAcceptParams l_hostEqtypeSwapAcceptParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "notifySwapMarginAccept";
        log.entering(STR_METHOD_NAME);
        if (l_hostEqtypeSwapAcceptParams == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.3.1.1get�����P��(�،���ЃR�[�h : String, ���X�R�[�h : String, ���i�^�C�v : ProductTypeEnum, ���ʃR�[�h : String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tm = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        String l_strInstitutionCode = l_hostEqtypeSwapAcceptParams.getInstitutionCode();
        String l_strBranchCode = l_hostEqtypeSwapAcceptParams.getBranchCode();
        String l_strRequestCode = l_hostEqtypeSwapAcceptParams.getOrderRequestNumber();
        WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager) l_tm.getOrderManager();
        OrderUnit l_orderUnit = l_orderManager.getOrderUnit(l_strInstitutionCode, l_strBranchCode, ProductTypeEnum.EQUITY, l_strRequestCode);
		//�⏕�����̎擾
		WEB3GentradeSubAccount l_subAccount;
		try{
			l_subAccount =(WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
					l_orderUnit.getAccountId(),
					l_orderUnit.getSubAccountId());
		}
		catch (NotFoundException l_nfe)
		{
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80005,
				this.getClass().getName() + STR_METHOD_NAME,
				l_nfe.getMessage(), l_nfe);
		}

        //1.3.1.2get orderUnits
        OrderStatusEnum l_orderStatus = l_orderUnit.getOrderStatus();
        if (OrderStatusEnum.CANCELLED.equals(l_orderStatus) || OrderStatusEnum.NOT_CANCELLED.equals(l_orderStatus))
        {
            //�ꌏ���������ֈڂ�.start
            try{
                QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
                Map l_mapChanges = new HashMap();
                log.debug("�����ΏۃL���[���R�[�h��update����");
                l_mapChanges.put("status", WEB3StatusDef.DEALT);
                l_mapChanges.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
                l_QueryProcessor.doUpdateQuery(l_hostEqtypeSwapAcceptParams.getPrimaryKey(),
                                               l_mapChanges);
                //.end
                log.exiting(STR_METHOD_NAME);
            }
            catch (DataNetworkException l_dne)
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dne.getMessage(),
                    l_dne);
            }
            catch (DataQueryException l_dne)
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dne.getMessage(),
                    l_dne);
            }
            return;
        }
        long l_lngOrderId = l_orderUnit.getOrderId();
        //1.3.1.3���n��t�C���^�Z�v�^(�������n��t�L���[Params : �������n��t�L���[Params)
        WEB3MarginSwapMarginAcceptInterceptor l_interceptor = new WEB3MarginSwapMarginAcceptInterceptor(l_hostEqtypeSwapAcceptParams);
        //1.3.1.4 setThreadLocalPersistenceEventInterceptor
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);
        //1.3.1.5)�V�K�����̏ꍇ
        if (OrderStatusEnum.ACCEPTED.equals(l_orderStatus))
        {
            //1.3.1.5.1������t�����̏ꍇ
            if (WEB3HostOrderAcceptStatusDef.ORDER_ACCEPT_COMPLETE.equals(l_hostEqtypeSwapAcceptParams.accept_status))
            {
                //1.3.1.5.1.1DefaultNewOrderAcceptMarketResponseMessage(����ID : long)
                DefaultNewOrderAcceptedMarketResponseMessage l_message = new DefaultNewOrderAcceptedMarketResponseMessage(l_lngOrderId);
                //1.3.1.5.1.2 process
                ProcessingResult l_result = l_tm.getMarketAdapter().getMarketResponseReceiverCallbackService().process(l_message);
                if (l_result.isFailedResult())
                {
                    WEB3BaseException l_baseException = new WEB3BaseException(l_result.getErrorInfo(), this.getClass().getName() + "." + STR_METHOD_NAME, l_result.getErrorInfo().getErrorMessage());
                    log.error(STR_METHOD_NAME, l_baseException);
                    throw l_baseException;
                }
            }
            //1.3.1.5.2������t�G���[�̏ꍇ
            if (WEB3HostOrderAcceptStatusDef.ORDER_ACCEPT_ERROR.equals(l_hostEqtypeSwapAcceptParams.accept_status))
            {
                //1.3.1.5.2.1DefaultNewOrderRejectedMarketResponseMessage(����ID : long)
                DefaultNewOrderRejectedMarketResponseMessage l_message = new DefaultNewOrderRejectedMarketResponseMessage(l_lngOrderId);
                //1.3.1.5.2.2 process
                ProcessingResult l_result = l_tm.getMarketAdapter().getMarketResponseReceiverCallbackService().process(l_message);
                
                if (l_orderManager.isReserveOrderConfirmRequire((EqTypeOrderUnit)l_orderUnit))
                {
                    WEB3ToSuccReservationEqTypeOrderUpdateService l_updateService =
                        (WEB3ToSuccReservationEqTypeOrderUpdateService)Services.getService(
                            WEB3ToSuccReservationEqTypeOrderUpdateService.class);
                    l_updateService.invalidateAllOrderUnit(l_orderUnit.getOrderId());
                }
                
                //�]�͍Čv�Z(�⏕���� : �⏕����)
				log.debug("�]�͍Čv�Z���s��");
				WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
					(WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
				l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);
                if (l_result.isFailedResult())
                {
                    WEB3BaseException l_baseException = new WEB3BaseException(l_result.getErrorInfo(), this.getClass().getName() + "." + STR_METHOD_NAME, l_result.getErrorInfo().getErrorMessage());
                    log.error(STR_METHOD_NAME, l_baseException);
                    throw l_baseException;
                }
            }
        }
        //��������̏ꍇ
        else if (OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderStatus))
        {
            //1.3.1.6.1������t�����̏ꍇ
            if (WEB3AcceptStatusDef.OVER.equals(l_hostEqtypeSwapAcceptParams.accept_status))
            {
                //1.3.1.6.1.1DefaultCancelOrderSentMarketResponseMessage(����ID : long)
                DefaultCancelOrderSentMarketResponseMessage l_message = new DefaultCancelOrderSentMarketResponseMessage(l_lngOrderId);
                //1.3.1.6.1.2 process
                ProcessingResult l_result = l_tm.getMarketAdapter().getMarketResponseReceiverCallbackService().process(l_message);
                if (l_result.isFailedResult())
                {
                    WEB3BaseException l_baseException = new WEB3BaseException(l_result.getErrorInfo(), this.getClass().getName() + "." + STR_METHOD_NAME, l_result.getErrorInfo().getErrorMessage());
                    log.error(STR_METHOD_NAME, l_baseException);
                    throw l_baseException;
                }
            }
            //1.3.1.6.2������t�G���[�̏ꍇ
            if (WEB3AcceptStatusDef.ERROR.equals(l_hostEqtypeSwapAcceptParams.accept_status))
            {
                //1.3.1.6.2.1DefaultCancelOrderRejectedMarketResponseMessage(����ID : long)
                DefaultCancelOrderRejectedMarketResponseMessage l_message = new DefaultCancelOrderRejectedMarketResponseMessage(l_lngOrderId);
                //1.3.1.6.2.2 process
                ProcessingResult l_result = l_tm.getMarketAdapter().getMarketResponseReceiverCallbackService().process(l_message);
				//�]�͍Čv�Z(�⏕���� : �⏕����)
				log.debug("�]�͍Čv�Z���s��");
				WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
					(WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
				l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);
                if (l_result.isFailedResult())
                {
                    WEB3BaseException l_baseException = new WEB3BaseException(l_result.getErrorInfo(), this.getClass().getName() + "." + STR_METHOD_NAME, l_result.getErrorInfo().getErrorMessage());
                    log.error(STR_METHOD_NAME, l_baseException);
                    throw l_baseException;
                }
            }
        }
        //�ꌏ���������ֈڂ�.start
        try{
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            Map l_mapChanges = new HashMap();
            log.debug("�����ΏۃL���[���R�[�h��update����");
            l_mapChanges.put("status", WEB3StatusDef.DEALT);
            l_mapChanges.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
            l_QueryProcessor.doUpdateQuery(l_hostEqtypeSwapAcceptParams.getPrimaryKey(),
                                           l_mapChanges);
            //.end
            log.exiting(STR_METHOD_NAME);
        }
        catch (DataNetworkException l_dne)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        catch (DataQueryException l_dne)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }

    }
}
@
