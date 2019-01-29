head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.35.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecurityTransferNotifyUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��U�֒ʒmUnitService�����N���X(WEB3AioSecurityTransferNotifyUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/13 �����(���u) �V�K�쐬
*/
package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;

import webbroker3.aio.WEB3AioCashTransAcceptUpdateInterceptor;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.service.delegate.WEB3AioSecurityTransferNotifyUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AcceptDivDef;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�،��U�֒ʒmUnitServiceImpl)<BR>
 * �،��U�֒ʒmUnitService�����N���X<BR>
 * <BR>
 * Plugin���Ɏ����g�����U�N�V����TransactionalInterceptor<BR>
 * (TransactionalInterceptor.TX_CREATE_NEW)���w�肷��B
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AioSecurityTransferNotifyUnitServiceImpl implements WEB3AioSecurityTransferNotifyUnitService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSecurityTransferNotifyUnitServiceImpl.class);
    
    /**
     * @@roseuid 41B031750290
     */
    public WEB3AioSecurityTransferNotifyUnitServiceImpl() 
    {
     
    }
    
    /**
     * �،��U�֒ʒm�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�،��U�֒ʒm�j�،��U�֒ʒm�v �Q��
     * @@param l_aioOrderUnit - �����P�ʃI�u�W�F�N�g�̔z��
     * @@param l_errorCode - �G���[�R�[�h
     * @@param l_acceptNotifyDiv - ��t�ʒm�敪
     * @@throws WEB3BaseException
     * @@roseuid 4157934300CC
     */
    public void execute(AioOrderUnit[] l_aioOrderUnit, String l_errorCode, String l_acceptNotifyDiv) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "execute(AioOrderUnit[] l_aioOrderUnit, String l_errorCode, String l_acceptNotifyDiv)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 ���o����t�X�V�C���^�Z�v�^(String)
        WEB3AioCashTransAcceptUpdateInterceptor l_updateInterceptor = 
            new WEB3AioCashTransAcceptUpdateInterceptor(l_errorCode);
        
        //1.2 setThreadLocalPersistenceEventInterceptor(AioOrderManagerPersistenceEventInterceptor)
        WEB3AioOrderManager l_orderManager = 
            (WEB3AioOrderManager)GtlUtils.getTradingModule(ProductTypeEnum.AIO).getOrderManager();
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_updateInterceptor);
        
        //1.3 �����P�ʖ���Loop����
        for (int i = 0; i < l_aioOrderUnit.length; i++)
        {
            long l_lngOrderId = l_aioOrderUnit[i].getOrderId();
            
            //1.3.1 (*1) ��t�����̏ꍇ
            if (WEB3AcceptDivDef.CASH_TRANS_SERVICE_COMPLETE.equals(l_acceptNotifyDiv))
            {
                //1.3.1.1 DefaultNewOrderAcceptedMarketResponseMessage
                // [����] 
                // �����h�c�F �����P��.����ID
                DefaultNewOrderAcceptedMarketResponseMessage l_newOrderAcceptedMarketResponseMessage = 
                    new DefaultNewOrderAcceptedMarketResponseMessage(l_lngOrderId);
                
                //1.3.1.2  process(MarketResponseMessage)
                // ��t������Ԃɒ������X�V����B
                //[����] 
                // ��t���ʁF �i����������t���ʃI�u�W�F�N�g�j 
                MarketAdapter l_marketAdapter =
                    GtlUtils.getTradingModule(
                        ProductTypeEnum.AIO).getMarketAdapter();
                AioMarketResponseReceiverCallbackService l_marketCallbackService = 
                    (AioMarketResponseReceiverCallbackService)
                        l_marketAdapter.getMarketResponseReceiverCallbackService();
                
                ProcessingResult l_processResult = 
                    l_marketCallbackService.process(l_newOrderAcceptedMarketResponseMessage);
                if (l_processResult.isFailedResult())
                {
                    log.debug("��t������Ԃɒ������X�V���s�ł���");
                    throw new WEB3BusinessLayerException(
                       WEB3ErrorCatalog.BUSINESS_ERROR_00395,
                       this.getClass().getName() + "." + STR_METHOD_NAME,
                       "��t������Ԃɒ������X�V���s");   
                }
            }
            //1.3.2 (*2) ��t�G���[�̏ꍇ
            else if (WEB3AcceptDivDef.ERROR.equals(l_acceptNotifyDiv))
            {
                // 1.3.2.1 DefaultNewOrderRejectedMarketResponseMessage(long)
                DefaultNewOrderRejectedMarketResponseMessage l_newOrderRejectedMarketResponseMessage = 
                    new DefaultNewOrderRejectedMarketResponseMessage(l_lngOrderId);
                
                //1.3.1.2  process(MarketResponseMessage)
                // ��t�G���[��Ԃɒ������X�V����B
                //[����] 
                // ��t���ʁF �i����������t���ʁi�G���[�j�I�u�W�F�N�g�j
                MarketAdapter l_marketAdapter =
                    GtlUtils.getTradingModule(
                        ProductTypeEnum.AIO).getMarketAdapter();
                AioMarketResponseReceiverCallbackService l_marketCallbackService = 
                    (AioMarketResponseReceiverCallbackService)
                        l_marketAdapter.getMarketResponseReceiverCallbackService();
                
                ProcessingResult l_processResult = 
                    l_marketCallbackService.process(l_newOrderRejectedMarketResponseMessage);
                if (l_processResult.isFailedResult())
                {
                    log.debug("��t�G���[��Ԃɒ������X�V���s�ł���");
                    throw new WEB3BusinessLayerException(
                       WEB3ErrorCatalog.BUSINESS_ERROR_00395,
                       this.getClass().getName() + "." + STR_METHOD_NAME,
                       "��t�G���[��Ԃɒ������X�V���s");   
                }
            }
        }
        
        //1.4  (*3) ��t�G���[�̏ꍇ
        if (WEB3AcceptDivDef.ERROR.equals(l_acceptNotifyDiv))
        {
            AccountManager l_accountManager = GtlUtils.getAccountManager();

            try 
            {
                // 1.4.1 get�⏕����(, )
                // [����] 
                // ����ID�F ����.�����P��[0].����ID 
                // �⏕����ID�F ����.�����P��[0].�⏕����ID
                WEB3GentradeSubAccount l_subAccount = 
                    (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                        l_aioOrderUnit[0].getAccountId(), 
                        l_aioOrderUnit[0].getSubAccountId());
                
                // 1.4.2 �]�͍Čv�Z(�⏕���� : �⏕����)
				WEB3TPTradingPowerReCalcService l_tradingPowerService = 
                    (WEB3TPTradingPowerReCalcService)Services.getService(WEB3TPTradingPowerReCalcService.class);
                l_tradingPowerService.reCalcTradingPower(l_subAccount);
            } 
            catch (NotFoundException l_ex) 
            {
                log.error("get�⏕�������s�ł���", l_ex);
                throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                   this.getClass().getName() + "." + STR_METHOD_NAME,
                   l_ex.getMessage(),
                   l_ex);   
            }
        }
    }
}
@
