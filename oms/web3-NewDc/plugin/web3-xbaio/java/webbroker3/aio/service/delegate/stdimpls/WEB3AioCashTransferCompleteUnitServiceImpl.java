head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.27.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashTransferCompleteUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���o������UnitService�����N���X(WEB3AioCashTransferCompleteUnitServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/19 ���E (���u) �V�K�쐬  
                   2004/10/26 ���� (���u) ���r���[    
*/
package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.GenFinTransactionDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.GenFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.GenFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderManager;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioFinTransactionDao;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.market.messages.DefaultAioTransferDoneMarketResponseMessage;
import webbroker3.aio.WEB3AioCashTransUpdateInterceptor;
import webbroker3.aio.WEB3AioCashoutCancelUpdateInterceptor;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.service.delegate.WEB3AioCashTransferCompleteUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;

/**
 * (���o������UnitServiceImpl)<BR>
 * ���o������UnitService�����N���X<BR>
 * <BR>
 * Plugin���Ɏ����g�����U�N�V����<BR>
 * TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW)<BR>
 * ���w�肷��B<BR>
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3AioCashTransferCompleteUnitServiceImpl implements WEB3AioCashTransferCompleteUnitService 
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashTransferCompleteUnitServiceImpl.class);
    
    /**
     * (complete���o��)<BR>
     * ���o�����ʂł̒����f�[�^�̍X�V�ƃg�����U�N�V�����f�[�^�̐������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���o����t�jcomplete���o���v �Q��<BR>
     * @@param l_orderUnit - (�����P�ʃI�u�W�F�N�g)
     * @@roseuid 40FE4D7701C3
     */
    public void completeCashTransfer(AioOrderUnit l_orderUnit) 
    {
        final String STR_METHOD_NAME = "completeCashTransfer(AioOrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 1.1)���o���X�V�C���^�Z�v�^�C���X�^���X�𐶐�����B 
        WEB3AioCashTransUpdateInterceptor l_aioCashTransUpdateInterceptor = 
            new WEB3AioCashTransUpdateInterceptor();
        
        // 1.2)�C���^�Z�v�^���X���b�h�ɃZ�b�g����B 
        //[����] 
        //  ���o���X�V�C���^�Z�v�^�F ���������C���^�Z�v�^
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AioOrderManager l_aioOrderManager =
            (AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
        
        l_aioOrderManager.setThreadLocalPersistenceEventInterceptor(
            l_aioCashTransUpdateInterceptor);
        
        // 1.3)���o�����s���b�Z�[�W�C���X�^���X�𐶐�����B
        //[����] 
        //�����h�c�F ����.����ID 
        //�^�C���X�^���v�F �V�X�e���^�C���X�^���v
        DefaultAioTransferDoneMarketResponseMessage l_defaultMessage = 
            new DefaultAioTransferDoneMarketResponseMessage(
            l_orderUnit.getOrderId(), 
            GtlUtils.getSystemTimestamp());
         
        // 1.4)���o�����ʂ𒍕��ɍX�V����B
        //[����] 
        //���o�����ʁF �����������o�����s���b�Z�[�W�I�u�W�F�N�g 
        MarketAdapter l_marketAdapter = l_finApp.getTradingModule(ProductTypeEnum.AIO).getMarketAdapter();
        
        AioMarketResponseReceiverCallbackService l_marketResponseReceiverCallbackService =
            (AioMarketResponseReceiverCallbackService)l_marketAdapter.getMarketResponseReceiverCallbackService();
           
        l_marketResponseReceiverCallbackService.process(l_defaultMessage);
        
        
        log.exiting(STR_METHOD_NAME);
     
    }
    
    /**
     * (complete���o�����)<BR>
     * ���o�����ʁi����j�ł̒����f�[�^�ƃg�����U�N�V�����f�[�^�̍X�V���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���o����t�jcomplete���o������v �Q��<BR>
     * @@param l_orderUnit - (�����P�ʃI�u�W�F�N�g)
     * @@roseuid 4105D62B01B5
     */
    public void completeCashTransferCancel(AioOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "completeCashTransferCancel(AioOrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1.1)�o������X�V�C���^�Z�v�^�C���X�^���X�𐶐�����B
        WEB3AioCashoutCancelUpdateInterceptor l_aioCashoutCancelUpdateInterceptor = 
            new WEB3AioCashoutCancelUpdateInterceptor();
        
        // 1.2)�C���^�Z�v�^���X���b�h�ɃZ�b�g����B
        //[����] 
        //�o������X�V�C���^�Z�v�^�F ���������C���^�Z�v�^
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3AioOrderManager l_aioOrderManager =
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
        
        l_aioOrderManager.setThreadLocalPersistenceEventInterceptor(l_aioCashoutCancelUpdateInterceptor);
        
        // 1.3)���o��������b�Z�[�W�C���X�^���X�𐶐�����B
        //[����] 
        //�����h�c�F ����.�����P��.����ID
        long l_lngOrderId = l_orderUnit.getOrderId();
        DefaultCancelOrderAcceptedMarketResponseMessage l_responseMessage = 
            new DefaultCancelOrderAcceptedMarketResponseMessage(l_lngOrderId);
        
        // 1.4)������ʂ𒍕��ɍX�V����B
        //[����] 
        //������ʁF �����������o��������b�Z�[�W�I�u�W�F�N�g
        MarketAdapter l_marketAdapter = l_finApp.getTradingModule(ProductTypeEnum.AIO).getMarketAdapter();
        
        AioMarketResponseReceiverCallbackService l_marketResponseReceiverCallbackService =
            (AioMarketResponseReceiverCallbackService)l_marketAdapter.getMarketResponseReceiverCallbackService();
        
        l_marketResponseReceiverCallbackService.process(l_responseMessage);
        
        //(*) �����P�ʂ���A�g�����U�N�V�����i������薾�ׁj�A�g�����U�N�V�����i�ڋq���薾�ׁj
        //���擾���āA���R�[�h�̍X�V���s���B
        //�X�V���e�́A
        //�uSONAR���o���i����j_�g�����U�N�V�����i������薾�ׁj.xls�v
        //�uSONAR���o���i����j_�g�����U�N�V�����i�ڋq���薾�ׁj.xls�v���Q��
        long l_lngAccountId = l_orderUnit.getAccountId();
        long l_lngAubAccountId = l_orderUnit.getSubAccountId();
        long l_lngProductId = l_orderUnit.getProduct().getProductId();
        long l_lngOrderUnitId = l_orderUnit.getOrderUnitId();
        
        
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            //  update �g�����U�N�V�����i������薾��) 
            AioFinTransactionRow l_aioFinTransactionRow = 
                AioFinTransactionDao.findRowByAccountIdSubAccountIdProductIdOrderUnitId(
                    l_lngAccountId, l_lngAubAccountId, l_lngProductId, l_lngOrderUnitId);
            
            // new params 
            AioFinTransactionParams l_aioFinTransactionParams = 
                new AioFinTransactionParams(l_aioFinTransactionRow);
            
            // set update values
            l_aioFinTransactionParams.setDeleteFlag(BooleanEnum.TRUE);
            l_aioFinTransactionParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            // do update
            l_queryProcessor.doUpdateQuery(l_aioFinTransactionParams);
            
            
            //update ��ݻ޸��݁i�ڋq���薾�ׁj
            long l_lngFinTransactionId = l_aioFinTransactionRow.getFinTransactionId();
            
            GenFinTransactionRow l_genFinTransactionRow = 
                GenFinTransactionDao.findRowByPk(l_lngFinTransactionId);
            
            // new params 
            GenFinTransactionParams l_genFinTransactionParams = 
                new GenFinTransactionParams(l_genFinTransactionRow);
            
            // set update values
            l_genFinTransactionParams.setDeleteFlag(BooleanEnum.TRUE);
            l_genFinTransactionParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            // do update
            l_queryProcessor.doUpdateQuery(l_genFinTransactionParams);
            
        }
         catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
}@
