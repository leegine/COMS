head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.42.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualCancelAcceptUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�����t�P���T�[�r�X�����N���X(WEB3MutualCancelAcceptUnitServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 ������ (���u) �V�K�쐬
                   2004/08/23 ��O�� (���u) ���r���[    
                   2004/12/11 ������ (���u) �c�Ή�
*/

package webbroker3.mf.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.mf.WEB3MutualFundAcceptConfirmInterceptor;
import webbroker3.mf.WEB3MutualFundOrderManager;
import webbroker3.mf.service.delegate.WEB3MutualCancelAcceptUnitService;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3LogUtility;

/**
 * (���M�����tUnitServiceImpl)<BR>
 * ���M�����t�P���T�[�r�X�����N���X<BR>
 * <BR>
 * ��������P�����Ƃ̎�t���������{����B<BR>
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3MutualCancelAcceptUnitServiceImpl
    implements WEB3MutualCancelAcceptUnitService
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualCancelAcceptUnitServiceImpl.class);

    /**
     * (notify�����t����)<BR>
     * ���M�����t���������������Ȃ��B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i���M�����t�jnotify�����t�����v�Q�ƁB <BR>
     *  <BR>
     * @@param l_mutualFundOrderUnit - ���M�����P��
     * @@param l_mutualFundAcceptConfirmInterceptor - ���M��t�m��C���^�Z�v�^
     * @@roseuid 40C40EFA012B
     */
    public void notifyCancelAcceptComplete(
        MutualFundOrderUnit l_mutualFundOrderUnit,
        WEB3MutualFundAcceptConfirmInterceptor l_mutualFundAcceptConfirmInterceptor)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "notifyCancelAcceptComplete(MutualFundOrderUnit l_mutualFundOrderUnit,"
                + " WEB3MutualFundAcceptConfirmInterceptor "
                + " l_mutualFundAcceptConfirmInterceptor)";
        log.entering(STR_METHOD_NAME);

        if (l_mutualFundOrderUnit == null
            || l_mutualFundAcceptConfirmInterceptor == null)
        {
            log.debug(" __parameter_error__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // �P�j�@@�g�����M�����}�l�[�W��.setThreadLocalPersistenceEventInterceptor()
        //  �g�����M�����}�l�[�W���̎擾
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundOrderManager l_orderManager =
            (WEB3MutualFundOrderManager) l_finApp.getTradingModule(
                  ProductTypeEnum.MUTUAL_FUND).getOrderManager();

        //setThreadLocalPersistenceEventInterceptor()���R�[��
        l_orderManager.setThreadLocalPersistenceEventInterceptor(
            l_mutualFundAcceptConfirmInterceptor);

        // �Q�j�@@MutualFundMarketResponseReceiverCallbackService���擾����
        MarketAdapter l_marketAdapter =
            l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND).getMarketAdapter();
        MutualFundMarketResponseReceiverCallbackService l_marketCallbackService =
            (MutualFundMarketResponseReceiverCallbackService) l_marketAdapter.getMarketResponseReceiverCallbackService();

        // �R�j�@@DefaultCancelOrderAcceptedMarketResponseMessage �𐶐�
        DefaultCancelOrderAcceptedMarketResponseMessage l_defaultMarketResponseMessage =
            new DefaultCancelOrderAcceptedMarketResponseMessage(
                l_mutualFundOrderUnit.getOrderId());
     
        // �S�j�@@MutualFundMarketResponseReceiverCallbackService.process()���R�[��
        ProcessingResult l_processingResylt = l_marketCallbackService.process(
                                                   l_defaultMarketResponseMessage);
        
        // �T�j�@@process()���\�b�h�̖߂�l.isFailedResult()�̒l��true�̏ꍇ
        if (l_processingResylt.isFailedResult())
        {
            log.debug("������t�������s�ł���");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00395,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "������t�������s�ł���");   
        }   
        
        
        // �U�j�@@�]�͎c���X�V�������s���B 
        // �|�g���A�J�E���g�}�l�[�W��.get�⏕����()����⏕�����I�u�W�F�N�g���擾����B 
        // [get�⏕�����ɓn���p�����^] 
        // �@@����ID�F����.���M�����P��.get����ID()�̖߂�l 
        // �@@�⏕����ID�F����.���M�����P��.get�⏕����ID()�̖߂�l
        WEB3GentradeAccountManager l_genAccountManager = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        WEB3GentradeSubAccount l_subAccount = null;
        try
        {
            l_subAccount = 
                (WEB3GentradeSubAccount) l_genAccountManager.getSubAccount(
                        l_mutualFundOrderUnit.getAccountId(),
                        l_mutualFundOrderUnit.getSubAccountId()
                        );   
        }
        catch(NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���");
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }
        // �|�]�͍Čv�Z�T�[�r�X���擾���A�]�͍Čv�Z()���R�[������B 
        // [�]�͍Čv�Z�ɓn���p�����^] 
        // �@@�⏕�����F�擾�����⏕�����I�u�W�F�N�g
        WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService = 
            (WEB3TPTradingPowerReCalcService) Services.getService(
                WEB3TPTradingPowerReCalcService.class);
		l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);
        
        log.exiting(STR_METHOD_NAME);           
    }

    /**
     * (notify�����t���s)<BR>
     * ���M�����t���s�����������Ȃ��B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i���M�����t�jnotify�����t���s�v�Q�ƁB <BR>
     * @@param l_mutualFundOrderUnit - ���M�����P��
     * @@param l_mutualFundAcceptConfirmInterceptor - ���M��t�m��C���^�Z�v�^
     * @@roseuid 40C40EFA012E
     */
    public void notifyCancelAcceptFail(
        MutualFundOrderUnit l_mutualFundOrderUnit,
        WEB3MutualFundAcceptConfirmInterceptor l_mutualFundAcceptConfirmInterceptor) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "notifyCancelAcceptFail(MutualFundOrderUnit l_mutualFundOrderUnit,"
                + " WEB3MutualFundAcceptConfirmInterceptor "
                + " l_mutualFundAcceptConfirmInterceptor)";
        log.entering(STR_METHOD_NAME);

        if (l_mutualFundOrderUnit == null
            || l_mutualFundAcceptConfirmInterceptor == null)
        {
            log.debug(" __parameter_error__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // �P�j�@@�g�����M�����}�l�[�W��.setThreadLocalPersistenceEventInterceptor()
        //  �g�����M�����}�l�[�W���̎擾
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundOrderManager l_orderManager =
            (WEB3MutualFundOrderManager) l_finApp.getTradingModule(
                   ProductTypeEnum.MUTUAL_FUND).getOrderManager();
                
        //setThreadLocalPersistenceEventInterceptor()���R�[��
        l_orderManager.setThreadLocalPersistenceEventInterceptor(
            l_mutualFundAcceptConfirmInterceptor);
            
        // �Q�j�@@MutualFundMarketResponseReceiverCallbackService���擾����
        MarketAdapter l_marketAdapter =
            l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND).getMarketAdapter();
        MutualFundMarketResponseReceiverCallbackService l_marketCallbackService =
            (MutualFundMarketResponseReceiverCallbackService) l_marketAdapter.getMarketResponseReceiverCallbackService();
                
        // �R�j�@@DefaultCancelOrderRejectedMarketResponseMessage �𐶐�
        DefaultCancelOrderRejectedMarketResponseMessage l_defaultMarketResponseMessage =
            new DefaultCancelOrderRejectedMarketResponseMessage(
                l_mutualFundOrderUnit.getOrderId());
                
        // �S�j�@@MutualFundMarketResponseReceiverCallbackService.process()���R�[��
        ProcessingResult l_processResult = l_marketCallbackService.process(
            l_defaultMarketResponseMessage);
        // �T�j�@@process()���\�b�h�̖߂�l.isFailedResult()�̒l��true�̏ꍇ
        if (l_processResult.isFailedResult())
        {
            log.debug("������t�������s�ł���");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00395,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "������t�������s�ł���");   
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
