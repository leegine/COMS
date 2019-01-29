head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.30.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AccTransChangeAcceptUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �U�֎�tUnitServiceImpl(WEB3AccTransChangeAcceptUnitServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 ������ (���u) �V�K�쐬
                   2004/10/22 �����(���u) ���r���[  
                   2004/12/09 ���E (���u) �c�Ή�                                     
*/

package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import webbroker3.aio.WEB3AioCashTransAcceptUpdateInterceptor;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.service.delegate.WEB3AccTransChangeAcceptUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AcceptDivDef;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�U�֎�tUnitServiceImpl)<BR>
 * �U�֎�tUnitService�����N���X<BR>
 * <BR>
 * Plugin���Ɏ����g�����U�N�V����<BR>
 * TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW)<BR>
 * ���w�肷��B<BR>
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3AccTransChangeAcceptUnitServiceImpl
    implements WEB3AccTransChangeAcceptUnitService
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AccTransChangeAcceptUnitServiceImpl.class);

    /**
     * �U�֐�����tDB�X�V�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�U�֐�����t�j�U�֐�����tDB�X�V�v  �Q��<BR>
     * @@param l_orderUnit - (�����P�ʃI�u�W�F�N�g)<BR>
     * @@param l_strErrorCode - (�G���[�R�[�h)<BR>
     * @@param l_strAcceptDiv - (��t�ʒm�敪)<BR>
     * @@throws WEB3BaseException
     * @@roseuid 413C20F2037B
     */
    public void execute(
        AioOrderUnit l_orderUnit,
        String l_strErrorCode,
        String l_strAcceptDiv)
            throws WEB3BaseException
    {
        String STR_METHOD_NAME = "execute(" 
            + "AioOrderUnit l_orderUnit,"
            + "String l_strErrorCode," 
            + "String l_strAcceptDiv)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug(" __parameter_error__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1) �C���^�Z�v�^�𐶐�����B 
        // [�R���X�g���N�^�̈���] 
        // �G���[�R�[�h�F�@@����.�G���[�R�[�h 
        WEB3AioCashTransAcceptUpdateInterceptor l_updateInterceptor = 
            new WEB3AioCashTransAcceptUpdateInterceptor(l_strErrorCode);
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3AioOrderManager l_orderManager = 
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager(); 
                    
        // 2) �C���^�Z�v�^���Z�b�g����B 
        // [����] 
        // ���o����t�X�V�C���^�Z�v�^�F�@@�i�����������o����t�X�V�C���^�Z�v�^�j
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_updateInterceptor);
        
        MarketAdapter l_marketAdapter = 
            l_finApp.getTradingModule(ProductTypeEnum.AIO).getMarketAdapter();
        
        AioMarketResponseReceiverCallbackService l_marketService =
            (AioMarketResponseReceiverCallbackService)l_marketAdapter.getMarketResponseReceiverCallbackService();
         
        // 3) ��t�����̏ꍇ
        if(WEB3AcceptDivDef.CASH_TRANS_SERVICE_COMPLETE.equals(l_strAcceptDiv))
        {
            // 3- 1 ) ��t���ʁi��t�����j�I�u�W�F�N�g�𐶐�����B 
            // [����] 
            // �����h�c�F ����.�����P��.����ID
            DefaultNewOrderAcceptedMarketResponseMessage l_newOrderMessage = 
                new DefaultNewOrderAcceptedMarketResponseMessage(
                    l_orderUnit.getOrderId());
            
            // 3 - 2) ��t�����𒍕��ɍX�V����B 
            // [����] 
            // ��t���ʁF �i����������t���ʃI�u�W�F�N�g�j                
            l_marketService.process(l_newOrderMessage);
        }
        else
        {
            // 4) ��t�G���[�̏ꍇ
            if(WEB3AcceptDivDef.ERROR.equals(l_strAcceptDiv))
            {
                // 4- 1 ) ��t���ʁi��t�G���[�j�I�u�W�F�N�g�𐶐�����B 
                // [����] 
                // �����h�c�F ����.�����P��.����ID
                DefaultNewOrderRejectedMarketResponseMessage l_newOrderMessage = 
                    new DefaultNewOrderRejectedMarketResponseMessage(
                        l_orderUnit.getOrderId());
            
                // 4 - 2) ��t�G���[�𒍕��ɍX�V����B 
                // [����] 
                // ��t���ʁF �i����������t���ʃI�u�W�F�N�g�j                
                l_marketService.process(l_newOrderMessage);
                
                // 4 - 3) �⏕�����I�u�W�F�N�g���擾����B 
                // �m�����n 
                // �����P�ʁF ����.�����P�� 
                SubAccount l_subAccount = this.getSubAccount(l_orderUnit);
                
                if(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT.equals(
                    l_subAccount.getSubAccountType()))
                {
                    //=========remain zhou-yong NO.1 begin ===========
                    
                    // 1.4.4) �]�͍Čv�Z(�⏕���� : �⏕����)
                    //�A�C�e���̒�`
                    ///�]�͂̍X�V���s���B
                    //[����] 
                    //�⏕�����F get�⏕����()�̖߂�l 
					WEB3TPTradingPowerReCalcService l_service =
                        (WEB3TPTradingPowerReCalcService) Services.getService(
					WEB3TPTradingPowerReCalcService.class);
                    
                    WEB3GentradeSubAccount l_gentradeSubAccount = 
                        (WEB3GentradeSubAccount)l_subAccount;
                    
                    l_service.reCalcTradingPower(l_gentradeSubAccount);

                    //=========remain zhou-yong NO.1 end ===========
                    
                }
            }
        }
        
        log.exiting(STR_METHOD_NAME); 
    }

    /**
     * (get�⏕����)<BR>
     * �����P�ʂ���A�⏕�����I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �P�j�⏕�����I�u�W�F�N�g���擾����B<BR>
     * <BR>
     *    �g���A�J�E���g�}�l�[�W��.get�⏕����()<BR>
     * <BR>
     *    �m�����n<BR>
     *    ����ID�F ����.�����P��.����ID<BR>
     *    �⏕����ID�F ����.�����P��.�⏕����ID<BR>
     * <BR>
     * �Q�j�擾�����⏕�����I�u�W�F�N�g��ԋp����B<BR>
     * @@param l_orderUnit - (�����P�ʃI�u�W�F�N�g)
     * @@return SubAccount
     * @@throws WEB3BaseException
     * @@roseuid 413D12B9014F
     */
    protected SubAccount getSubAccount(AioOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getSubAccount(AioOrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug(" __parameter_error__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // �P�j�⏕�����I�u�W�F�N�g���擾����B 
        // �g���A�J�E���g�}�l�[�W��.get�⏕����() 
        // �m�����n 
        // ����ID�F ����.�����P��.����ID 
        // �⏕����ID�F ����.�����P��.�⏕����ID
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accManage = l_finApp.getAccountManager();
        
        SubAccount l_subAccount = null;
        try
        {
            l_subAccount = l_accManage.getSubAccount(
                l_orderUnit.getAccountId(),
                l_orderUnit.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("__an unexpected error__when "
                     + " getSubAccount",l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }
        
        // �Q�j�擾�����⏕�����I�u�W�F�N�g��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_subAccount;
    }
}
@
