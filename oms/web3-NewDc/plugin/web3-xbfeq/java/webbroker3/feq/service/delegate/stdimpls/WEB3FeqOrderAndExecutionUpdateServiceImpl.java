head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.40.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderAndExecutionUpdateServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������o���^���X�V�T�[�r�XImpl(WEB3FeqOrderAndExecutionUpdateServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/19 ������(���u) �V�K�쐬
*/
package webbroker3.feq.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultUndoOrderFillMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.market.messages.DefaultFeqOrderFillMarketResponseMessage;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.feq.WEB3FeqExecuteCancelUpdateInterceptor;
import webbroker3.feq.WEB3FeqExecuteUpdateInterceptor;
import webbroker3.feq.WEB3FeqOrderExecution;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqPositionManager;
import webbroker3.feq.data.HostFeqOrderExecNotifyParams;
import webbroker3.feq.service.delegate.WEB3FeqOrderAndExecutionUpdateService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O�������o���^���X�V�T�[�r�XImpl) <BR>
 * �O�������o���^���X�V�T�[�r�X�����N���X <BR>
 * TransactionalInterceptor.TX_JOIN_EXISTING <BR>
 * <BR>
 * @@ author ������ <BR>
 * @@ version 1.0<BR>
 */
public class WEB3FeqOrderAndExecutionUpdateServiceImpl implements WEB3FeqOrderAndExecutionUpdateService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FeqOrderAndExecutionUpdateServiceImpl.class);
    
    /**
     * @@roseuid 42CE39F50167
     */
    public WEB3FeqOrderAndExecutionUpdateServiceImpl() 
    {
     
    }
    
    /**
     * (update�����) <BR>
     * �o���^���X�V�������s�� <BR>
     *  <BR>
     * �V�[�P���X�} <BR>
     * �u�i�o���^��苤�ʁjupdate�����v�Q�ƁB <BR>
     * @@param l_hostFeqOrderExecNotifyParams - (�O���o���ʒm�L���[) <BR>
     * �O���o���ʒm�L���[�s�I�u�W�F�N�g <BR>
     *  <BR>
     * ���O���o���ʒm�L���[Params��DDL��莩����������B <BR>
     * @@throws WEB3BaseException
     * @@roseuid 428AFC1C02F0
     */
    public void updateExecuteUnit(HostFeqOrderExecNotifyParams l_hostFeqOrderExecNotifyParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateExecuteUnit(HostFeqOrderExecNotifyParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_hostFeqOrderExecNotifyParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqOrderManager l_orderManger = (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        
        //1.1�X�V�C�x���g�C���^�Z�v�^�𐶐�����B 
        WEB3FeqExecuteUpdateInterceptor l_updateInterceptor = new WEB3FeqExecuteUpdateInterceptor(l_hostFeqOrderExecNotifyParams);
        
        //1.2�X�V�C�x���g�C���^�Z�v�^�𒍕��}�l�[�W���ɃZ�b�g����B 
        l_orderManger.setThreadLocalPersistenceEventInterceptor(l_updateInterceptor);
        
        //�������F�@@�O���o���ʒm�L���[Params.������ 
        Date l_datExecTime = l_hostFeqOrderExecNotifyParams.getOrderBizDate();
        log.debug("������ = " + l_datExecTime);
        
        GtlUtils.truncateDate(l_datExecTime);
        String l_strEmpCode = l_hostFeqOrderExecNotifyParams.getOrderEmpCode();
        log.debug("l_strEmpCode = " + l_strEmpCode);
        
        //1.3�^�p�R�[�h�ɊY�����钍���P�ʂ��擾����B  
        FeqOrderUnit l_feqOrderUnit = l_orderManger.getValidOrderUnitByOrderEmpCode(l_datExecTime, l_strEmpCode);
        
        //1.4���R�[���o�b�N���b�Z�[�W�𐶐�����B  
        DefaultFeqOrderFillMarketResponseMessage l_marketResponseMessage = 
            new DefaultFeqOrderFillMarketResponseMessage(
                l_feqOrderUnit.getOrderId(), 
                l_feqOrderUnit.getOrderUnitId(),
                l_hostFeqOrderExecNotifyParams.getExecQuantity(), 
                l_hostFeqOrderExecNotifyParams.getExecPrice(),
                l_hostFeqOrderExecNotifyParams.getFxRate());
        
        log.debug("�����h�c = " + l_marketResponseMessage.getOrderId());
        log.debug("�����P�ʂh�c = " + l_marketResponseMessage.getOrderUnitId());
        log.debug("��芔�� = " + l_marketResponseMessage.getFillQuantity());
        log.debug("���P�� = " + l_marketResponseMessage.getFillPrice());
        log.debug("�בփ��[�g = " + l_marketResponseMessage.getFxRate());
        
        l_updateInterceptor.setFeqOrderUnit(l_feqOrderUnit);
        l_updateInterceptor.setExecQuantity(
            l_hostFeqOrderExecNotifyParams.getExecQuantity());
        l_updateInterceptor.setExecPrice(
            l_hostFeqOrderExecNotifyParams.getExecPrice());
        l_updateInterceptor.setFxRate(
            l_hostFeqOrderExecNotifyParams.getFxRate());
        
        //1.5�w��̓��e�ŁA�������X�V����B 
        FeqMarketResponseReceiverCallbackService l_marketResponseReceiverCallbackService =
            (FeqMarketResponseReceiverCallbackService)l_tradingModule.getMarketAdapter().getMarketResponseReceiverCallbackService();
        l_marketResponseReceiverCallbackService.process(l_marketResponseMessage);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (update�����) <BR>
     * �o���^������X�V�������s�� <BR>
     *  <BR>
     * �V�[�P���X�} <BR>
     * �u�i�o���^��苤�ʁjupdate������v�Q�ƁB <BR>
     * @@param l_hostFeqOrderExecNotifyParams - (�O���o���ʒm�L���[) <BR>
     * �O���o���ʒm�L���[�s�I�u�W�F�N�g <BR>
     * �i���h�c�w��̏ꍇnull�j <BR>
     *  <BR>
     * ���O���o���ʒm�L���[Params��DDL��莩����������B <BR>
     * 
     * @@param l_lngExecuteId - (���h�c) <BR>
     * �i�O���o���ʒm�L���[�w��̏ꍇnull�j
     * @@throws WEB3BaseException
     * @@roseuid 428B156201D7
     */
    public void updateExecuteCancel(
        HostFeqOrderExecNotifyParams l_hostFeqOrderExecNotifyParams, 
        long l_lngExecuteId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateExecuteUnit()";
        log.entering(STR_METHOD_NAME);
        
        if (l_lngExecuteId == 0 && l_hostFeqOrderExecNotifyParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqOrderManager l_orderManger = (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        WEB3FeqPositionManager l_positionManager = (WEB3FeqPositionManager)l_tradingModule.getPositionManager();
        WEB3FeqOrderExecution l_orderExcution  = null;
        
        //1.1���h�c�̓��͂�����ꍇ�i���h�c != 0�j
        long l_lngOrderId = 0;
        long l_lngOrderExecutionId = 0;
        try
        {
            if (l_lngExecuteId != 0)
            {
                //1.1.1���I�u�W�F�N�g���擾����B 
                l_orderExcution = (WEB3FeqOrderExecution)l_orderManger.getOrderExecution(l_lngExecuteId);
                
                //1.1.1�����h�c���擾����B
                FeqOrderExecutionRow l_orderExecutionRow = (FeqOrderExecutionRow)l_orderExcution.getDataSourceObject();
                
                l_lngOrderId = l_orderExecutionRow.getOrderId();
               
                //1.1.1���h�c���擾����B
                l_lngOrderExecutionId = l_orderExecutionRow.getOrderExecutionId();
            }
            else
            {
                //1.2.1�^�p�R�[�h�ɊY�����钍���P�ʂ��擾����B 
                Date l_datExecTime = l_hostFeqOrderExecNotifyParams.getExecTimestamp();
                GtlUtils.truncateDate(l_datExecTime);
                log.debug("l_datExecTime = " + l_datExecTime);
                
                String l_strEmpCode = l_hostFeqOrderExecNotifyParams.getOrderEmpCode();
                log.debug("�^�p�R�[�h = " + l_strEmpCode);
                
                FeqOrderUnit l_feqOrderUnit = l_orderManger.getValidOrderUnitByOrderEmpCode(l_datExecTime, l_strEmpCode);
                
                //1.2.2�����擾����B 
                l_orderExcution = 
                    (WEB3FeqOrderExecution)l_orderManger.getExecution(
                        l_feqOrderUnit.getOrderUnitId(), 
                        l_hostFeqOrderExecNotifyParams.getExecQuantity(), 
                        l_hostFeqOrderExecNotifyParams.getExecPrice());
                FeqOrderExecutionRow l_orderExecutionRow = (FeqOrderExecutionRow)l_orderExcution.getDataSourceObject();
                
                //1.2.3�����h�c���擾����B
                l_lngOrderId = l_orderExecutionRow.getOrderId();
                
                //1.2.4���h�c���擾����B
                l_lngOrderExecutionId = l_orderExecutionRow.getOrderExecutionId();
            }
            //1.3�X�V�C�x���g�C���^�Z�v�^�𐶐�����B 
            WEB3FeqExecuteCancelUpdateInterceptor l_updateInterceptor = new WEB3FeqExecuteCancelUpdateInterceptor(l_orderExcution);
            
            //1.4�X�V�C�x���g�C���^�Z�v�^�𒍕��}�l�[�W���ɃZ�b�g����B
            l_orderManger.setThreadLocalPersistenceEventInterceptor(l_updateInterceptor);
            
            //1.5�X�V�C�x���g�C���^�Z�v�^���|�W�V�����}�l�[�W���ɃZ�b�g����B
            l_positionManager.setThreadLocalPersistenceEventInterceptor(l_updateInterceptor);
            
            log.debug("�����h�c = " + l_lngOrderId);
            log.debug("���h�c = " + l_lngOrderExecutionId);
            
            //1.6������R�[���o�b�N���b�Z�[�W�𐶐�����B
            DefaultUndoOrderFillMarketResponseMessage l_marketResponseMessage = 
                new DefaultUndoOrderFillMarketResponseMessage(l_lngOrderId, l_lngOrderExecutionId);
            
            //1.7�w��̓��e�ŁA�������X�V����B
            FeqOrderUnit l_orderunit =
                (FeqOrderUnit)l_orderManger.getOrderUnit(l_orderExcution.getOrderUnitId());
            l_updateInterceptor.setFeqOrderUnit(l_orderunit);
            
            FeqMarketResponseReceiverCallbackService l_marketResponseReceiverCallbackService =
                (FeqMarketResponseReceiverCallbackService)l_tradingModule.getMarketAdapter().getMarketResponseReceiverCallbackService();
            l_marketResponseReceiverCallbackService.process(l_marketResponseMessage);
            log.exiting(STR_METHOD_NAME);
        }
        catch (NotFoundException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() +  "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }     
    }
}
@
