head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.30.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AccTransChangeCompleteUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �U�֊���UnitServiceImpl(WEB3AccTransChangeCompleteUnitServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 ������ (���u) �V�K�쐬
                   2004/10/22 �����(���u) ���r���[                                       
*/

package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.market.messages.DefaultAioTransferDoneMarketResponseMessage;

import webbroker3.aio.WEB3AioCashTransUpdateInterceptor;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.service.delegate.WEB3AccTransChangeCompleteUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�U�֊���UnitServiceImpl)<BR>
 * �U�֊���UnitService�����N���X<BR>
 * <BR>
 * Plugin���Ɏ����g�����U�N�V����<BR>
 * TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW)���w�肷��B<BR>
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3AccTransChangeCompleteUnitServiceImpl
    implements WEB3AccTransChangeCompleteUnitService
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AccTransChangeCompleteUnitServiceImpl.class);

    /**
     * (complete�U��)<BR>
     * �U�֌��ʂł̒����f�[�^�̍X�V�ƃg�����U�N�V�����f�[�^�̐������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�U�֐�����t�jcomplete�U�ցv �Q��<BR>
     * @@param l_orderUnit - (�����P�ʃI�u�W�F�N�g)
     * @@throws WEB3BaseException
     * @@roseuid 413C2261033D
     */
    public void completeChange(AioOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "completeChange(AioOrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug(" __parameter_error__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1) ���o���X�V�C���^�Z�v�^�C���X�^���X�𐶐�����
        WEB3AioCashTransUpdateInterceptor l_updateInterceptor = 
            new WEB3AioCashTransUpdateInterceptor();
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3AioOrderManager l_orderManager = 
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager(); 
                   
        // 2) �C���^�Z�v�^���X���b�h�ɃZ�b�g����B 
        // [����] 
        // ���o���X�V�C���^�Z�v�^�F ���������C���^�Z�v�^
        l_orderManager.setThreadLocalPersistenceEventInterceptor(
            l_updateInterceptor);
        
        // 3) ���o�����s���b�Z�[�W�C���X�^���X�𐶐�����B 
        // [����] 
        // �����h�c�F ����.�����P��.����ID 
        // �^�C���X�^���v�F �V�X�e���^�C���X�^���v    
        DefaultAioTransferDoneMarketResponseMessage l_defaultMessage = 
            new DefaultAioTransferDoneMarketResponseMessage(
            l_orderUnit.getOrderId(), GtlUtils.getSystemTimestamp());
            
        // 4) ���o�����ʂ𒍕��ɍX�V����B 
        // [����] 
        // ���o�����ʁF �����������o�����s���b�Z�[�W�I�u�W�F�N�g 
        MarketAdapter l_marketAdapter = 
            l_finApp.getTradingModule(ProductTypeEnum.AIO).getMarketAdapter();
        
        AioMarketResponseReceiverCallbackService l_marketService =
            (AioMarketResponseReceiverCallbackService)l_marketAdapter.getMarketResponseReceiverCallbackService();
        
        l_marketService.process(l_defaultMessage);
        log.exiting(STR_METHOD_NAME);
    }
}
@
