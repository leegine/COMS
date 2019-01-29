head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.35.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashTransferAcceptUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���o����tUnitService�����N���X(WEB3AioCashTransferAcceptUnitServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/19 ���E (���u) �V�K�쐬  
                   2004/10/26 ���� (���u) ���r���[    
                   2004/12/09 ���E (���u) �c�Ή�
*/
package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderManager;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import webbroker3.aio.WEB3AioCashTransAcceptUpdateInterceptor;
import webbroker3.aio.service.delegate.WEB3AioCashTransferAcceptUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AcceptDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3LogUtility;

/**
 * (���o����tUnitServiceImpl)<BR>
 * ���o����tUnitService�����N���X<BR>
 * <BR>
 * Plugin���Ɏ����g�����U�N�V����<BR>
 * TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW)<BR>
 * ���w�肷��B<BR>
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3AioCashTransferAcceptUnitServiceImpl implements WEB3AioCashTransferAcceptUnitService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashTransferAcceptUnitServiceImpl.class);
    
    /**
     * ���o����tDB�X�V�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���o����t�j���o����tDB�X�V�v  �Q��<BR>
     * @@param c - (�����P�ʃI�u�W�F�N�g)
     * @@param l_strErrorCode - (�G���[�R�[�h)
     * @@param l_strAcceptNoticeDiv - (��t�ʒm�敪)
     * @@roseuid 40FF5EDE0196
     */
    public void execute(AioOrderUnit l_orderUnit, String l_strErrorCode, String l_strAcceptNoticeDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "executeAioOrderUnit l_orderUnit, " +
                "String l_strErrorCode," +
                " String l_strAcceptNoticeDiv()";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1.1)�C���^�Z�v�^�𐶐�����B 
        //[�R���X�g���N�^�̈���]
        //�G���[�R�[�h�F�@@����.�G���[�R�[�h
        WEB3AioCashTransAcceptUpdateInterceptor l_aioCashTransAcceptUpdateInterceptor =
            new WEB3AioCashTransAcceptUpdateInterceptor(l_strErrorCode);
        
        // 1.2)�C���^�Z�v�^���Z�b�g����B
        //[����] 
        //���o����t�X�V�C���^�Z�v�^�F�@@�i�����������o����t�X�V�C���^�Z�v�^�j
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AioOrderManager l_aioOrderManager =
            (AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
        
        l_aioOrderManager.setThreadLocalPersistenceEventInterceptor(
            l_aioCashTransAcceptUpdateInterceptor);
        
        // 1.3)��t�����̏ꍇ
        //(*1) ����.��t�ʒm�敪 = "1"�i��t�����j�̏ꍇ
        if(WEB3AcceptDivDef.CASH_TRANS_SERVICE_COMPLETE.equals(l_strAcceptNoticeDiv))
        {
            // 1.3.1)��t���ʁi��t�����j�I�u�W�F�N�g�𐶐�����B
            //[����] 
            //�����h�c�F ����.����ID
            DefaultNewOrderAcceptedMarketResponseMessage l_newOrderMessage = 
                new DefaultNewOrderAcceptedMarketResponseMessage(
                    l_orderUnit.getOrderId());
            
            // 1.3.2)��t�����𒍕��ɍX�V����B
            //[����] 
            //��t���ʁF �i����������t���ʃI�u�W�F�N�g)
            MarketAdapter l_marketAdapter = l_finApp.getTradingModule(ProductTypeEnum.AIO).getMarketAdapter();
            
            AioMarketResponseReceiverCallbackService l_marketResponseReceiverCallbackService =
                (AioMarketResponseReceiverCallbackService)l_marketAdapter.getMarketResponseReceiverCallbackService();
            
            l_marketResponseReceiverCallbackService.process(l_newOrderMessage);
        }
        
        // 1.4)��t�G���[�̏ꍇ
        //(*2) ����.��t�ʒm�敪 = "2"�i��t�G���[�j�̏ꍇ
        if(WEB3AcceptDivDef.ERROR.equals(l_strAcceptNoticeDiv))
        {
            // 1.4.1)��t���ʁi��t�G���[�j�I�u�W�F�N�g�𐶐�����B
            //[����] 
            //�����h�c�F ����.����ID 
            DefaultNewOrderRejectedMarketResponseMessage l_defaultResponseMessage = 
                new DefaultNewOrderRejectedMarketResponseMessage(
                    l_orderUnit.getOrderId());
            
            // 1.4.2)��t�G���[�𒍕��ɍX�V����B
            //[����] 
            //��t���ʁF �i����������t���ʁi�G���[�j�I�u�W�F�N�g�j
            MarketAdapter l_marketAdapter = l_finApp.getTradingModule(ProductTypeEnum.AIO).getMarketAdapter();
            
            AioMarketResponseReceiverCallbackService l_marketResponseReceiverCallbackService =
                (AioMarketResponseReceiverCallbackService)l_marketAdapter.getMarketResponseReceiverCallbackService();
            
            l_marketResponseReceiverCallbackService.process(l_defaultResponseMessage);
            
            
            //===========remain zhou-yong NO.1 begin=========
            
            //�]�͍Čv�Z(�⏕���� : �⏕����)
            //�A�C�e���̒�`
            //�]�͂̍X�V������B
            //[����] 
            //�⏕�����F ����.�⏕����ID����擾�����⏕�����I�u�W�F�N�g
			WEB3TPTradingPowerReCalcService l_service =
                (WEB3TPTradingPowerReCalcService) Services.getService(
			WEB3TPTradingPowerReCalcService.class);
            
            WEB3GentradeAccountManager l_accManage = 
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
                
            WEB3GentradeSubAccount l_gentradeSubAccount = null;
            try
            {
                l_gentradeSubAccount = (WEB3GentradeSubAccount)l_accManage.getSubAccount(
                    l_orderUnit.getAccountId(),
                    l_orderUnit.getSubAccountId());
            }
            catch (NotFoundException l_ex)
            {                
                log.error("___NotFoundException___" , l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            l_service.reCalcTradingPower(l_gentradeSubAccount);
            
            //===========remain zhou-yong NO.1 end=========            

        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
