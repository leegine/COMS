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
filename	WEB3EquityChangeCancelAcceptUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������������t�ꌏ�T�[�r�X�̎����N���X(WEB3EquityChangeCancelAcceptUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/22 Ḗ@@�� (���u) �V�K�쐬
                   2004/12/13 �����a��(SAR) �c�Č��Ή� �m��.�Q�S�X
                   2004/12/21 �����a��(SRA) JavaDoc�C��
                   2005/01/06 �����a��(SRA) JavaDoc�C��
                   2006/11/02 ������@@(���u)���f��No.1022
                   2006/11/29 ������@@(���u)���f��No.1062,No.1081
*/

package webbroker3.equity.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderSentMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultChangeOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultChangeOrderSentMarketResponseMessage;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AcceptStatusDef;
import webbroker3.common.define.WEB3HostOrderAcceptStatusDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityEstimatedPrice;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityOrderRejectPersistenceInterceptor;
import webbroker3.equity.data.HostEqtypeOrderAcceptParams;
import webbroker3.equity.define.WEB3EquityWlimitEnableStatusDivDef;
import webbroker3.equity.service.delegate.WEB3EquityChangeCancelAcceptUnitService;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�������������t�ꌏ�T�[�r�XImpl�j�B<br>
 * <br>
 * �������������t�ꌏ�T�[�r�X�̎����N���X�B<br>
 * <br>
 * �ꌏ���Ƃ̒��������t�������s���B
 * Plugin���Ɏ����g�����U�N�V����TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW)���w�肷��B</p>
 * @@author Ḗ@@��
 * @@version 1.0
 */
public class WEB3EquityChangeCancelAcceptUnitServiceImpl implements WEB3EquityChangeCancelAcceptUnitService
{

    /**
     * <p>�i���O�o�̓��[�e�B���e�B�j�B</p>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        WEB3EquityChangeCancelAcceptUnitServiceImpl.class);

    /**
     * @@roseuid 4140067200D4
     */
    public WEB3EquityChangeCancelAcceptUnitServiceImpl()
    {

    }

    /**
     * <p>�inotify���������t�j�B</p>
     * <p>�y����������t�L���[�e�[�u���z���������t�̃L���[�f�[�^�ꌏ<br>
     * �ɑ΂��鏈�����s���B<br>
     * <br>
     * �V�[�P���X�}<br>
     * �u�i�������������t�T�[�r�X�jprocess�v��<br>
     * notfy���������t()�̕������Q�ƁB</p>
     * @@param l_orderAcceptQueParams ����������t�L���[Params�B
     * @@throws WEB3BaseException
     * @@roseuid 4100F9C80032
     */
    public void notifyChangeCancelAccept(HostEqtypeOrderAcceptParams l_orderAcceptQueParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "notifyChangeCancelAccept";
        log.entering(STR_METHOD_NAME);
        if(l_orderAcceptQueParams == null)
        {
             throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 1.3.1.1 get�����P��(�،���ЃR�[�h : String, ���X�R�[�h : String, ���i�^�C�v : ProductTypeEnum, ���ʃR�[�h : String)
        log.debug("1.3.1.1 �����P�ʂ��擾����B");
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tm  = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_eqManager = (WEB3EquityOrderManager) l_tm.getOrderManager();
        String l_strInstitutionCode = l_orderAcceptQueParams.getInstitutionCode();
        String l_strBranchCode = l_orderAcceptQueParams.getBranchCode();
        String l_strRequestCode = l_orderAcceptQueParams.getOrderRequestNumber();
        OrderUnit l_orderUnit = l_eqManager.getOrderUnit(l_strInstitutionCode, l_strBranchCode, ProductTypeEnum.EQUITY, l_strRequestCode);

        //����������t�L���[Params.orderstatus
        String l_strOrderStatus = l_orderAcceptQueParams.getAcceptStatus();
        MarketAdapter l_adapter = l_tm.getMarketAdapter();
        MarketResponseReceiverCallbackService l_marketReServ = l_adapter.getMarketResponseReceiverCallbackService();
        try
        {
            OrderStatusEnum l_orderStatus = l_orderUnit.getOrderStatus();
            long l_lngOrderId = l_orderUnit.getOrderId();
            if(l_orderStatus.equals(OrderStatusEnum.MODIFIED)
                ||l_orderStatus.equals(OrderStatusEnum.NOT_MODIFIED)
                ||l_orderStatus.equals(OrderStatusEnum.CANCELLED)
                ||l_orderStatus.equals(OrderStatusEnum.NOT_CANCELLED))
            {
                //����������t�L���[�e�[�u���X�V
                log.debug("����������t�L���[�e�[�u��.�����敪��update����");
                Map l_mapChanges = new HashMap();
                l_mapChanges.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
                l_mapChanges.put("status", WEB3StatusDef.DEALT);
                Processors.getDefaultProcessor().doUpdateQuery(l_orderAcceptQueParams.
                    getPrimaryKey(), l_mapChanges);
                return;
            }
            //1.3.1.3 reset market code
            EqtypeOrderUnitParams l_params = new EqtypeOrderUnitParams((EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject());
            long l_lngMarketId = l_params.getMarketId();
            String l_strMarketCode = null;
            l_strMarketCode =
                l_finApp
                    .getFinObjectManager()
                    .getMarket(l_lngMarketId)
                    .getMarketCode();
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
            //1.3.1.4 �����������������t�C���^�Z�v�^(����������t�L���[Params : ����������t�L���[Params)
            WEB3EquityOrderRejectPersistenceInterceptor  l_interceptor =
                new WEB3EquityOrderRejectPersistenceInterceptor(l_orderAcceptQueParams);

            //�i����t���[�F�X�g�b�v���������̏ꍇ�i���P�j�j
            //�i���P�j�X�g�b�v���������̏ꍇ�́A�ȉ��̏����Ŕ���
            //
            //�E�擾���������P��.������ԁ�OrderStatusEnum.MODIFY_ACCEPTED�i��t�ρi�ύX�����j�j
            //�@@�@@����
            //�@@����������t�L���[Params.������t���ʁ�"�G���["
            //�@@�@@����
            //�@@�g�����������}�l�[�W��.is�X�g�b�v�����ؑ֒�()��true
            //
            //�E�擾���������P��.������ԁ�OrderStatusEnum.CANCEL_ACCEPTED�i��t�ρi��������j�j
            //�@@�@@����
            //�@@����������t�L���[Params.������t���ʁ�"�G���["
            //�@@�@@����
            //�@@�����f�[�^�A�_�v�^.get�v�w�l�p�L����ԋ敪��"���~�b�g�����L��"
            if ((OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderStatus)
                && WEB3HostOrderAcceptStatusDef.ORDER_ACCEPT_ERROR.equals(
                    l_orderAcceptQueParams.getAcceptStatus())
                && l_eqManager.isStopOrderSwitching((EqTypeOrderUnit)l_orderUnit))
                || (OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderStatus)
                && WEB3HostOrderAcceptStatusDef.ORDER_ACCEPT_ERROR.equals(
                    l_orderAcceptQueParams.getAcceptStatus())
                && WEB3EquityWlimitEnableStatusDivDef.LIMIT_ENABLE.equals(
                    WEB3EquityDataAdapter.getWLimitEnableStatusDiv((EqTypeOrderUnit)l_orderUnit))))
            {
                WEB3GentradeAccountManager l_accountManager =
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                //get�⏕����(����ID : long, �⏕����ID : long)
                //�⏕�������擾����B
                //[����]
                //�@@�擾���������P��.����ID
                //�@@�擾���������P��.�⏕����ID
                SubAccount l_subAccount = l_accountManager.getSubAccount(
                    l_orderUnit.getAccountId(),
                    l_orderUnit.getSubAccountId());

                //get�X�g�b�v�����������T�Z����v�Z����(EqtypeOrderUnit, SubAccount)
                //�Čv�Z�����T�Z����v�Z���ʂ��擾����B
                //[����]
                //�@@�擾���������P��
                //�@@�擾�����⏕����
                WEB3EquityEstimatedPrice l_equityEstimatedPrice =
                    l_eqManager.getStopOrderExpireEstimatedPrice(
                        (EqTypeOrderUnit)l_orderUnit,
                        l_subAccount);

                //set�T�Z����v�Z����(�T�Z����v�Z���� : �T�Z����v�Z����)
                //�Čv�Z�����T�Z����v�Z���ʂ��Z�b�g����B
                //[����]
                //�@@�擾�����T�Z����v�Z����
                l_interceptor.setEquityEstimatedPrice(l_equityEstimatedPrice);

            }
            //1.3.1.5 set ThreadlocalInterceptor
            l_eqManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);
            ProcessingResult l_result = null;
            //1.3.1.6 ����t���[�F�擾���������P��.������ԁ�OrderStatusEnum.MODIFY_ACCEPTED�i��t�ρi�ύX�����j�j�̏ꍇ�j
            if(OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderStatus))
            {

                //1.3.1.6.1����t���[�F����������t�L���[Params.������t���ʁ�"������t����"�̏ꍇ
                if(WEB3AcceptStatusDef.OVER.equals(l_strOrderStatus))
                {
                    //1.3.1.6.1.1DefaultChangeOrderSentMarketResponseMessage
                    DefaultChangeOrderSentMarketResponseMessage l_MarketMess = new DefaultChangeOrderSentMarketResponseMessage(l_lngOrderId);
                    //1.3.1.6.1.2 process(arg0 : ChangeOrderSentMarketResponseMessage)
                    l_result = l_marketReServ.process(l_MarketMess);
                    if (l_result.isFailedResult())
                    {
                        throw new WEB3BaseException(l_result.getErrorInfo(), STR_METHOD_NAME);
                    }
                }
                //1.3.1.6.2 ����t���[�F����������t�L���[Params.������t���ʁ�"�G���["�̏ꍇ
                else if(WEB3AcceptStatusDef.ERROR.equals(l_strOrderStatus))
                {
                    //1.3.6.2.1DefaultChangeOrderRejectedMarketResponseMessage
                    DefaultChangeOrderRejectedMarketResponseMessage l_mess1 = new DefaultChangeOrderRejectedMarketResponseMessage(l_lngOrderId);
                    //1.3.6.2.2 process
                    l_result = l_marketReServ.process(l_mess1);
                    if (l_result.isFailedResult())
                    {
                        throw new WEB3BaseException(l_result.getErrorInfo(), STR_METHOD_NAME);
                    }
					// 1.3.1.6.2.3 �]�͍Čv�Z(�⏕���� : �⏕����)
					log.debug("�]�͍Čv�Z���s��");
					WEB3GentradeSubAccount l_subAccount =
						(WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
							l_orderUnit.getAccountId(),
							l_orderUnit.getSubAccountId());
					WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
						(WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
					l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);
                }
            }
            //1.3.1.7����t���[�F�擾���������P��.������ԁ�OrderStatusEnum.CANCEL_ACCEPTED�i��t�ρi��������j�j�̏ꍇ
            else if(OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderStatus))
            {
                //1.3.1.7.1�i����t���[�F����������t�L���[Params.������t���ʁ�"������t����"�̏ꍇ�j
                if(WEB3AcceptStatusDef.OVER.equals(l_strOrderStatus))
                {
                    //1.3.1.7.1.1DefaultCancelOrderSentMarketResponseMessage
                    DefaultCancelOrderSentMarketResponseMessage l_MarketMess = new DefaultCancelOrderSentMarketResponseMessage(l_lngOrderId);
                    //1.3.1.7.1.2 process(arg0 : CancelOrderSentMarketResponseMessage)
                    l_result = l_marketReServ.process(l_MarketMess);
                    if (l_result.isFailedResult())
                    {
                        throw new WEB3BaseException(l_result.getErrorInfo(), STR_METHOD_NAME);
                    }
                }
                //1.3.1.7.2 ����t���[�F����������t�L���[Params.������t���ʁ�"�G���["�̏ꍇ
                else if(WEB3AcceptStatusDef.ERROR.equals(l_strOrderStatus))
                {
                    //1.3.7.2.1DefaultCancelOrderRejectedMarketResponseMessage
                    DefaultCancelOrderRejectedMarketResponseMessage l_mess1 = new DefaultCancelOrderRejectedMarketResponseMessage(l_lngOrderId);
                    //1.3.7.2.2 process
                    l_result = l_marketReServ.process(l_mess1);
                    if (l_result.isFailedResult())
                    {
                        throw new WEB3BaseException(l_result.getErrorInfo(), STR_METHOD_NAME);
                    }
					// 1.3.1.7.2.3 �]�͍Čv�Z(�⏕���� : �⏕����)
					log.debug("�]�͍Čv�Z���s��");
					WEB3GentradeSubAccount l_subAccount =
						(WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
							l_orderUnit.getAccountId(),
							l_orderUnit.getSubAccountId());
					WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
						(WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
					l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);
                }
            }
            //1.3.1.8�i����t���[�F�擾���������P��.������� != OrderStatusEnum.MODIFY_ACCEPTED(��t��(�ύX����)) �܂��� OrderStatusEnum.CANCELED_ACCEPTED
            else
            {
                throw new WEB3BusinessLayerException(
                     WEB3ErrorCatalog.BUSINESS_ERROR_00649,
                     this.getClass().getName() + "." + STR_METHOD_NAME
                     );
            }
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage());
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 l_ex.getMessage());
        }
        catch (NotFoundException l_ex)
        {
            log.debug(l_ex.getMessage());
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 l_ex.getMessage());
        }
        catch (DataNetworkException l_ex)
        {
            log.debug(l_ex.getMessage());
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 l_ex.getMessage());
        }
        catch (DataQueryException l_ex)
        {
            log.debug(l_ex.getMessage());
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 l_ex.getMessage());
        }

        //����������t�L���[�e�[�u���X�V
        log.debug("����������t�L���[�e�[�u��.�����敪��update����");
        Map l_mapChanges = new HashMap();
		l_mapChanges.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
        l_mapChanges.put("status", WEB3StatusDef.DEALT);
        try
        {
            Processors.getDefaultProcessor().doUpdateQuery(l_orderAcceptQueParams.
                getPrimaryKey(), l_mapChanges);
        }
        catch (DataNetworkException l_nex)
        {
            log.debug(l_nex.getMessage());
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 l_nex.getMessage());
        }
        catch (DataQueryException l_dex)
        {
            log.debug(l_dex.getMessage());
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 l_dex.getMessage());
        }
    }

    /**
     * (notify���������t���ԊO)<BR>
     * �y����������t�L���[�e�[�u���z���������t�̃L���[�f�[�^�ꌏ�ɑ΂��鏈�����s���B<BR>
     * �i������t���� == "�O���t���ԊO�G���["�̏ꍇ�j<BR>
     * @@param l_params - (����������t�L���[Params)<BR>
     * ����������t�L���[Params�B
     * @@throws WEB3BaseException
     */
    public void notifyChangeCancelAcceptOvertime(
        HostEqtypeOrderAcceptParams l_params)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "notifyChangeCancelAcceptOvertime(HostEqtypeOrderAcceptParams)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        EqTypeOrderUnit l_orderUnitBefore = l_orderManager.getOrderUnit(
            l_params.getInstitutionCode(),
            l_params.getBranchCode(),
            ProductTypeEnum.EQUITY,
            l_params.getOrderRequestNumber());
        
        WEB3EquityFrontOrderService l_frontOrderService =
            (WEB3EquityFrontOrderService)Services.getService(
                WEB3EquityFrontOrderService.class);
        
        if (OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderUnitBefore.getOrderStatus()))
        {
            EqtypeOrderUnitRow l_orderUnitRow =
                (EqtypeOrderUnitRow)l_orderUnitBefore.getDataSourceObject();
            EqtypeOrderUnitParams l_orderUnitParams =
                new EqtypeOrderUnitParams(l_orderUnitRow);
            String l_strOrderRev = l_orderUnitParams.getOrderRev();
            int l_intOrderRev = Integer.parseInt(l_strOrderRev);
            l_intOrderRev += 1;
            l_strOrderRev = Integer.toString(l_intOrderRev);
            int l_intFigureOfOrderRev = l_frontOrderService.getFigureOfOrderRev();
            int l_intLength = l_strOrderRev.length();
            if (l_intLength > l_intFigureOfOrderRev)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02185,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            int l_intSize = l_intFigureOfOrderRev - l_intLength;
            StringBuffer l_sbOrderRev = new StringBuffer();
            for (int i = 0;i < l_intSize;i++)
            {
                l_sbOrderRev.append("0");
            }
            l_sbOrderRev.append(l_strOrderRev);
            l_orderUnitParams.setOrderRev(l_sbOrderRev.toString());
            l_orderUnitParams.setLastUpdatedTimestamp(
                GtlUtils.getTradingSystem().getSystemTimestamp());
            EqTypeOrderUnit l_orderUnitAfter =
                (EqTypeOrderUnit)l_orderManager.toOrderUnit(l_orderUnitParams);
            
            l_orderManager.updateOrderData(l_orderUnitAfter, false);
            
            l_frontOrderService.updateHostEqtypeOrderAllAtAcceptOvertime(
                l_orderUnitAfter, l_orderUnitBefore, false);
        }
        else
        {
            l_frontOrderService.updateHostEqtypeOrderAllAtAcceptOvertime(
                null, l_orderUnitBefore, true);
        }
        
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        l_params.setStatus(WEB3StatusDef.DEALT);
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doUpdateQuery(l_params);
        }
        catch (DataException l_de)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
