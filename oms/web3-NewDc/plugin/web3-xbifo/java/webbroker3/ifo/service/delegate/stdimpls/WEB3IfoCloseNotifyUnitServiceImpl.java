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
filename	WEB3IfoCloseNotifyUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               /**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�����ʒmUnitServiceImpl(WEB3IfoCloseNotifyUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/19 Ḗ@@�� (���u) �V�K�쐬
              001: 2004/08/13 ������@@(���u) STBUG(IFO_ST-000079)��Ή�
              002: 2006/07/27 ������ (���u) ���f��No.505�Ή�
              003: 2006/11/29 ����(���u) �d�l�ύX���f��No.578,586
Revesion History : 2008/03/17 �����F (���u)�d�l�ύX ���f��833
*/

package webbroker3.ifo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultOrderInvalidatedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultUndoOrderInvalidatedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CloseNotifyTypeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoCloseNotifyUpdateInterceptor;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.service.delegate.WEB3IfoCloseNotifyUnitService;
import webbroker3.ifo.service.delegate.WEB3IfoExecutedMailSendService;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationIfoOrderUpdateService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�敨OP�����ʒmUnitServiceImpl)<BR>
 * �敨OP�����ʒm�P���T�[�r�X�����N���X<BR>
 * <BR>
 * �P�����Ƃ̎����ʒm���������{����B<BR>
 * <BR>
 * Plugin���Ɏ����g�����U�N�V����TransactionalInterceptor<BR>
 * (TransactionalInterceptor.TX_JOIN_EXISTING)���w�肷��B<BR>
 * @@author Ḗ@@��
 * @@version 1.0
 */
public class WEB3IfoCloseNotifyUnitServiceImpl implements WEB3IfoCloseNotifyUnitService
{
    /**
     * Logger
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IfoCloseNotifyUnitServiceImpl.class);
    /**
     * @@roseuid 40C0752E03A9
     */
    public WEB3IfoCloseNotifyUnitServiceImpl()
    {

    }
    FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
    //�擾MarkerAdapter
    TradingModule l_tradingMod =
        l_finApp.getTradingModule(ProductTypeEnum.IFO);

    /**
     * (notify����)<BR>
     * �������������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iOP�����ʒm�jnotify�����v�Q�ƁB<BR>
     * <BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g
     * @@param l_dblExecutionQuantity - ��萔��
     * @@param l_strCloseReasonCode - �������R�R�[�h
     * @@param l_strCloseNotifyType - �����ʒm�敪<BR>
     * @@throws WEB3BaseException
     * @@roseuid 408C987301AE
     */
    public String notifyClose(OrderUnit l_orderUnit, double l_dblExecutionQuantity, String l_strCloseReasonCode, String l_strCloseNotifyType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "notifyClose(OrderUnit l_orderUnit, double l_dblExecutionQuantity, String l_strCloseReasonCode, String l_strCloseNotifyType)";
        log.entering(STR_METHOD_NAME);

        if((l_orderUnit == null) ||l_strCloseReasonCode == null || l_strCloseNotifyType == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".notifyClose");
        }

        if(l_strCloseReasonCode.length() < 0 || l_strCloseNotifyType.length() < 0 )
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".notifyClose");
        }

        WEB3OptionOrderManagerImpl l_orderManager = (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();
		//1.1�����P�ʂ��Ď擾����B�i�������b�N���������ꍇ���l���j
		OrderUnit l_reOrderUnit = l_orderUnit;
		try
		{
			l_reOrderUnit =  l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
		}
		catch(NotFoundException l_nfe)
		{
			log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_nfe);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80006,
				this.getClass().getName() + STR_METHOD_NAME,
				l_nfe.getMessage(),
				l_nfe);
		}
		
        if(l_dblExecutionQuantity < 0)
        {
            log.error("��萔��<0");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() +  ".notifyClose");
        }

        MarketAdapter l_marketAdapter = l_tradingMod.getMarketAdapter();      
        
        //1.2�敨OP�����ʒm�X�V�C���^�Z�v�^( )
        WEB3IfoCloseNotifyUpdateInterceptor l_closeNotifyUpdateINterceptor = 
            new WEB3IfoCloseNotifyUpdateInterceptor();

        //set�����ʒm�敪(String)
        //�����ʒm�敪�F�@@�p�����[�^.�����ʒm�敪
        l_closeNotifyUpdateINterceptor.setCloseNotifyType(l_strCloseNotifyType);

        //1.3setThreadLocalPersistenceEventInterceptor(arg0 : IfoOrderManagerPersistenceEventInterceptor
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_closeNotifyUpdateINterceptor);
      
        //get IfoMarketResponseReceiverCallbackServiceImpl Object
        IfoMarketResponseReceiverCallbackService l_marketResponseReceiveCallbackService =
            (IfoMarketResponseReceiverCallbackService) l_marketAdapter.getMarketResponseReceiverCallbackService();
        
        //get WEB3IfoExecutedMailSendServiceImpl Object
        WEB3IfoExecutedMailSendService l_executedMailSendService =
            (WEB3IfoExecutedMailSendService)Services.getService(WEB3IfoExecutedMailSendService.class);
        
        //1.4: ��萔�ʂ��擾����
        double l_dblExecutedQuantity = l_reOrderUnit.getExecutedQuantity();
        if (Double.isNaN(l_dblExecutedQuantity))
        {
            l_dblExecutedQuantity = 0D;
        }
        log.debug("��萔�ʂ��擾:" + l_dblExecutedQuantity);

        //1.5: �����h�c���擾����
        long l_lngOrderId = l_reOrderUnit.getOrderId();
        log.debug(" �����h�c���擾:" + l_lngOrderId);

        try
        {
            //1.6:(*1)����(�o���҂����ʂ���)�̏ꍇ�̂ݎ��{
            ProcessingResult l_processingResult = null;
            if((WEB3CloseNotifyTypeDef.CLOSE.equals(l_strCloseNotifyType)) &&
                (l_dblExecutionQuantity > l_dblExecutedQuantity))
            {
                //1.6.1:update��������(OrderUnit, OrderExpirationStatusEnum)
                updateCloseOrder(l_reOrderUnit,OrderExpirationStatusEnum.EXPIRING); 
                //1.6.2:return     
                return WEB3StatusDef.DEALING;
            }
            
            //1.7: (*2) �����i�o���҂����ʂȂ��j�̏ꍇ�̂ݎ��{
            if (WEB3CloseNotifyTypeDef.CLOSE.equals(l_strCloseNotifyType))
            {
                //�����I�u�W�F�N�g�𐶐�����
                //1.7.1:DefaultOrderInvalidatedMarketResponseMessage(����ID : long)
                DefaultOrderInvalidatedMarketResponseMessage
                    l_orderInvalidatedMarketResponseMessage = new DefaultOrderInvalidatedMarketResponseMessage(l_lngOrderId);

                // �����𒍕��ɍX�V����
                //1.7.2: process(���� : OrderInvalidatedMarketResponseMessage)
                l_processingResult = l_marketResponseReceiveCallbackService.process(l_orderInvalidatedMarketResponseMessage);
                
                if (l_processingResult.isFailedResult())
                {
                    log.error("�����𒍕��ɍX�V����fail");
                    throw new WEB3BaseException(
                        l_processingResult.getErrorInfo(),
                        STR_METHOD_NAME);
                }
                
				l_reOrderUnit = l_orderManager.getOrderUnit(l_reOrderUnit.getOrderUnitId());
                
				
				//1.7.3:�i*2-1�j�o������̏ꍇ�̂ݎ��{
                //�i*2-1�j����t���[  �����P��.isPartiallyExecuted()=true�̏ꍇ���{����B
                //sendMailProcess(OrderUnit, String)
				if(l_reOrderUnit.isPartiallyExecuted())
				{
					l_executedMailSendService.sendMailProcess(l_reOrderUnit,null);
				}
                //1.7.4: ��胁�[�����M�e�[�u���Ɏ������[���s��}������
                //sendMailProcess(OrderUnit, String)
                l_executedMailSendService.sendMailProcess(l_reOrderUnit,l_strCloseReasonCode);                

                //is�\�񒍕��m�F�v(IfoOrderUnit)
                boolean l_blnIsReserveOrderExist = l_orderManager.isReserveOrderExist((IfoOrderUnit)l_reOrderUnit);
                //�\�񒍕��m�F�v�iis�\�񒍕��m�F�v() == true�j�̏ꍇ
                if (l_blnIsReserveOrderExist)
                {
                    WEB3ToSuccReservationIfoOrderUpdateService l_ifoOrderUpdateService =
                        (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                            WEB3ToSuccReservationIfoOrderUpdateService.class);
                    // invalidateAll�\�񒍕��P��(�e�����̒���ID : long)
                    l_ifoOrderUpdateService.invalidateAllOrderUnit(l_lngOrderId);
                }

                try
                {
                    //1.7.5:notify���[���G���W���T�[�o(IfoOrderUnit, OrderManagerPersistenceContext)
                    l_orderManager.notifyRLS((IfoOrderUnit) l_reOrderUnit, OrderManagerPersistenceContext.ORDER_INVALIDATED_BY_MKT);
                }
                //1.7.6 notify���[���G���W���T�[�o()�ɂċƖ��G���[���X���[���ꂽ�ꍇ
                catch (WEB3BusinessLayerException l_ex)
                {
                    log.debug("notify���[���G���W���T�[�o()�ɂċƖ��G���[���X���[���ꂽ�ꍇ�A�����[���o�b�N���Ȃ��B");
                }
            }
            //1.8: (*3) ��������̏ꍇ�̂ݎ��{
            else if(WEB3CloseNotifyTypeDef.CLOSE_CANCEL.equals(l_strCloseNotifyType))
            {
                //1.8.1: DefaultUndoOrderInvalidatedMarketResponseMessage(����ID : long)
                DefaultUndoOrderInvalidatedMarketResponseMessage l_UndoOrderInvalidatedMarketResponseMessage =
                    new DefaultUndoOrderInvalidatedMarketResponseMessage(l_lngOrderId);

                //1.8.2: process(������� : UndoOrderInvalidatedMarketResponseMessage)
                l_processingResult = l_marketResponseReceiveCallbackService.process(l_UndoOrderInvalidatedMarketResponseMessage);
                if (l_processingResult.isFailedResult())
                {
                    throw new WEB3BaseException(l_processingResult.getErrorInfo(),STR_METHOD_NAME);
                }

                // �������[�����h�����h�ōX�V����
				l_reOrderUnit = l_orderManager.getOrderUnit(l_reOrderUnit.getOrderUnitId());
                //1.8.3:undoSendMail(OrderUnit)
                l_executedMailSendService.undoSendMail(l_reOrderUnit);                 
            }
            
            //1.9:(*4)�ꕔ�����A�܂��́A��������̏ꍇ
            if (l_dblExecutionQuantity > 0 || WEB3CloseNotifyTypeDef.CLOSE_CANCEL.equals(l_strCloseNotifyType))
            {
				l_reOrderUnit = l_orderManager.getOrderUnit(l_reOrderUnit.getOrderUnitId());
                IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_reOrderUnit.getDataSourceObject();
                //1.9.1:update�T�Z��n���(�����P�� : OrderUnit)
                if (WEB3FuturesOptionDivDef.OPTION.equals(l_orderUnitRow.getFutureOptionDiv()))
                {
                    WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = 
                        (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();
                    l_optionOrderManagerImpl.updateEstimateDeliveryAmount(l_reOrderUnit);
                }
                //1.9.2:update�敨�T�Z��n���(�����P�� : OrderUnit)
                else if (WEB3FuturesOptionDivDef.FUTURES.equals(l_orderUnitRow.getFutureOptionDiv()))
                {
                    WEB3FuturesOrderManagerImpl l_futuresOrderManagerImpl = 
                        (WEB3FuturesOrderManagerImpl)l_tradingMod.getOrderManager();
                    l_futuresOrderManagerImpl.updateFuturesEstimateDeliveryAmount(l_reOrderUnit);
                }
            }
            
            //�擾�ڋq(MainAccount)
            WEB3GentradeSubAccount l_subAccount = null;
            long l_lngMainAccountId = l_reOrderUnit.getAccountId();

            //�擾�⏕���� 
            l_subAccount = (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
                l_lngMainAccountId, l_reOrderUnit.getSubAccountId());            

            //1.10 (*5)����t���[
            //�����P��.�⏕����ID�ɊY������⏕�����̃^�C�v != 7�i�؋��������j
            //�̏ꍇ�A���������{����B        
            if (!SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType()))
            {
                //1.10.1 �]�͍Čv�Z(�⏕���� : �⏕����)
				WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
					(WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
				l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);
            }         

            //1.11:
            return WEB3StatusDef.DEALT;
        }   
        catch (NotFoundException l_nfex)
        {
            log.error(STR_METHOD_NAME, l_nfex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + ".notifyClose"); 
        }
    }

    /**
     * (update��������)<BR>
     * �����P�ʂ̎����敪���X�V����B<BR>
     * <BR>
     *     �ȉ��̏����ɊY�����钍���P�ʃ��R�[�h��update����B<BR> 
     *�@@    [����] <BR>
     *�@@�@@    �����P�ʃe�[�u��.�����P��ID = �p�����[�^.�����P��.�����P��ID <BR>
     *<BR>
     *�@@    [�X�V���e] <BR>
     *�@@�@@    �����P�ʃ��R�[�h.�����敪 = �p�����[�^.�����敪<BR>
     *�@@�@@    �����P�ʃ��R�[�h.�X�V���t = ���ݓ��� <BR>
     *  DB�X�V�d�l<BR>
     * �u�����ʒm_���������P�ʃe�[�u��.xls�v��
     * �uOP�����ʒm_�����P�ʃe�[�u�� DB�X�V�d�l [������]�v�V�[�g�Q�ƁB     
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g<BR>
     * @@param l_orderExpirationStatus - �����敪<BR>
     */
    protected void updateCloseOrder(OrderUnit l_orderUnit,
    OrderExpirationStatusEnum l_orderExpirationStatus) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateCloseOrder(OrderUnit l_orderUnit," +
            "OrderExpirationStatusEnum l_orderExpirationStatus)";
        log.entering(STR_METHOD_NAME);
        IfoOrderUnitRow l_orderUnitRow = null;
        try
        {
            l_orderUnitRow = 
                (IfoOrderUnitRow)l_tradingMod.getOrderManager().
                    getOrderUnit(l_orderUnit.getOrderUnitId()).getDataSourceObject();
            IfoOrderUnitParams l_orderUnitParams = new IfoOrderUnitParams(l_orderUnitRow);
            l_orderUnitParams.setExpirationStatus(l_orderExpirationStatus);           
            l_orderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            QueryProcessor l_queryProcessor = null;
            l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_orderUnitParams);
        }
        catch(NotFoundException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                STR_METHOD_NAME);             
        }
        catch(DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME);             
        
        }
        catch (DataFindException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME);             
        }
        catch (DataQueryException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME);             

        }
        log.exiting(STR_METHOD_NAME);

    }

    


}
@
