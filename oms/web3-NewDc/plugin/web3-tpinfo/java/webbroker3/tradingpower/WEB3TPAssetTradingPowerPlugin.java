head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.13.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAssetTradingPowerPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���Y�]�͏���ʃv���O�C���̃v���O�C���N���X(EB3TPAssetTradingPowerPlugin.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/12/22 asano(SCS) �V�K�쐬
Revision History : 2008/10/09 ����(���u) ���f��No.312�ANo.313
*/
package webbroker3.tradingpower;

import webbroker3.tradingpower.handler.WEB3TPAdditionalGenerationHandler;
import webbroker3.tradingpower.handler.WEB3TPAfterRepayTransitionReferenceHandler;
import webbroker3.tradingpower.handler.WEB3TPAssetHandler;
import webbroker3.tradingpower.handler.WEB3TPAssetHistoryHandler;
import webbroker3.tradingpower.handler.WEB3TPDayTradeTradingPowerHandler;
import webbroker3.tradingpower.handler.WEB3TPEquityTradingPowerDetailHandler;
import webbroker3.tradingpower.handler.WEB3TPMarginTradingPowerDetailHandler;
import webbroker3.tradingpower.handler.WEB3TPPaymentAcceptHandler;
import webbroker3.tradingpower.handler.WEB3TPShortfallGenerationHandler;
import webbroker3.tradingpower.handler.WEB3TPTradingPowerHandler;
import webbroker3.tradingpower.handler.WEB3TPTransitionReferenceHandler;
import webbroker3.tradingpower.handler.WEB3TPTransitionReferenceUseQuoteHandler;
import webbroker3.tradingpower.message.WEB3TPAdditionalGenerationRequest;
import webbroker3.tradingpower.message.WEB3TPAdditionalGenerationResponse;
import webbroker3.tradingpower.message.WEB3TPAfterRepayTransitionReferenceRequest;
import webbroker3.tradingpower.message.WEB3TPAfterRepayTransitionReferenceResponse;
import webbroker3.tradingpower.message.WEB3TPAssetHistoryRequest;
import webbroker3.tradingpower.message.WEB3TPAssetHistoryResponse;
import webbroker3.tradingpower.message.WEB3TPAssetRequest;
import webbroker3.tradingpower.message.WEB3TPAssetResponse;
import webbroker3.tradingpower.message.WEB3TPDayTradeTradingPowerRequest;
import webbroker3.tradingpower.message.WEB3TPDayTradeTradingPowerResponse;
import webbroker3.tradingpower.message.WEB3TPEquityTradingPowerDetailRequest;
import webbroker3.tradingpower.message.WEB3TPEquityTradingPowerDetailResponse;
import webbroker3.tradingpower.message.WEB3TPMarginTradingPowerDetailRequest;
import webbroker3.tradingpower.message.WEB3TPMarginTradingPowerDetailResponse;
import webbroker3.tradingpower.message.WEB3TPPaymentAcceptRequest;
import webbroker3.tradingpower.message.WEB3TPPaymentAcceptResponse;
import webbroker3.tradingpower.message.WEB3TPShortfallGenerationRequest;
import webbroker3.tradingpower.message.WEB3TPShortfallGenerationResponse;
import webbroker3.tradingpower.message.WEB3TPTradingPowerRequest;
import webbroker3.tradingpower.message.WEB3TPTradingPowerResponse;
import webbroker3.tradingpower.message.WEB3TPTransitionReferenceRequest;
import webbroker3.tradingpower.message.WEB3TPTransitionReferenceResponse;
import webbroker3.tradingpower.message.WEB3TPTransitionReferenceUseQuoteRequest;
import webbroker3.tradingpower.message.WEB3TPTransitionReferenceUseQuoteResponse;
import webbroker3.tradingpower.service.delegate.WEB3TPAfterRepayTransitionReferenceService;
import webbroker3.tradingpower.service.delegate.WEB3TPAssetTradingPowerService;
import webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPAfterRepayTransitionReferenceServiceImpl;
import webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPAssetTradingPowerServiceImpl;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;

/**
 * ���Y�]�͏���ʕ\���v���O�C���̃v���O�C���N���X<br>
 * 
 * @@author asano(SCS)
 */
public class WEB3TPAssetTradingPowerPlugin extends Plugin
{
    
    /**
     * ���O
     */
    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPAssetTradingPowerPlugin.class);

    /**
     * (�f�o�b�Oison)
     */
    private static boolean DBG = log.ison();

    /**
     * �R���X�g���N�^
     */
    public WEB3TPAssetTradingPowerPlugin()
    {
    }

    /**
     * ���̃v���O�C�����v���O�C������Ƃ��Ɏg�p���郁�\�b�h
     */
    public static void plug() throws Exception
    {
        plug(WEB3TPAssetTradingPowerPlugin.class);
    }

    /**
     * ���̃v���O�C�����v���O�C������Ƃ��ɏ�������郁�\�b�h
     */
    public static void onPlug() throws Exception
    {

        // Kernel�v���O�C�����v���O
        KernelPlugin.plug();
        
        //DatabaseExtensions�v���O�C��
        WEB3TPLibPlugin.plug();
        //WEB3TPLibAccountDatabaseExtensions.plug();
        //WEB3TPLibMasterDatabaseExtensions.plug();

        // ---------------------------------------------------------------------

        // ���Y�]�͏���ʕ\���T�[�r�X��o�^
        Services.registerService(
            WEB3TPAssetTradingPowerService.class,
            new WEB3TPAssetTradingPowerServiceImpl());
        if( DBG )
        {
            log.debug("WEB3TPAssetTradingPowerService registered.");
        }

        // ���Y�]�͏���ʕ\���C���^�[�Z�v�^��o�^
        Services.addInterceptor(
        WEB3TPAssetTradingPowerService.class,
            new WEB3TPAssetTradingPowerServiceInterceptor());
        if( DBG )
        {
            log.debug("WEB3TPAssetTradingPowerServiceInterceptor added to WEB3TPAssetTradingPowerService");
        }

        // �ԍό�]�͏���ʃT�[�r�X��o�^
        Services.registerService(
                WEB3TPAfterRepayTransitionReferenceService.class,
                new WEB3TPAfterRepayTransitionReferenceServiceImpl());
        if(DBG)
        {
            log.debug("WEB3TPAfterRepayTransitionReferenceService registered.");
        }      

        // ���Y�]�͏���ʕ\���C���^�[�Z�v�^��o�^
        Services.addInterceptor(
                WEB3TPAfterRepayTransitionReferenceService.class,
            new WEB3TPAssetTradingPowerServiceInterceptor());
        if( DBG )
        {
            log.debug("WEB3TPAssetTradingPowerServiceInterceptor added to WEB3TPAssetTradingPowerService");
        }
        
        // �a�莑�Y��ʕ\�����b�Z�[�W��o�^
        regClass(WEB3TPAssetRequest.class);
        if( DBG )
        {
            log.debug("WEB3TPAssetRequest registered.");
        }
        regClass(WEB3TPAssetResponse.class);
        if( DBG )
        {

            log.debug("WEB3TPAssetResponse registered.");
        }
        
        // ����]�͉�ʕ\�����b�Z�[�W��o�^
        regClass(WEB3TPTradingPowerRequest.class);
        if( DBG )
        {
            log.debug("WEB3TPTradingPowerRequest registered.");
        }
        regClass(WEB3TPTradingPowerResponse.class);
        if( DBG )
        {
            log.debug("WEB3TPTradingPowerResponse registered.");
        }
        
        // ���v���������]�͎��Z��ʕ\�����b�Z�[�W��o�^
        regClass(WEB3TPDayTradeTradingPowerRequest.class);
        if( DBG )
        {
            log.debug("WEB3TPDayTradeTradingPowerRequest registered.");
        }
        regClass(WEB3TPDayTradeTradingPowerResponse.class);
        if( DBG )
        {
            log.debug("WEB3TPDayTradeTradingPowerResponse registered.");
        }
        
        // �]�͐��ډ�ʕ\�����b�Z�[�W��o�^
        regClass(WEB3TPTransitionReferenceRequest.class);
        if( DBG )
        {
            log.debug("WEB3TPTransitionReferenceRequest registered.");
        }
        regClass(WEB3TPTransitionReferenceResponse.class);
        if( DBG )
        {
            log.debug("WEB3TPTransitionReferenceResponse registered.");
        }
        
        // �o����t�󋵉�ʕ\�����b�Z�[�W��o�^
        regClass(WEB3TPPaymentAcceptRequest.class);
        if( DBG )
        {
            log.debug("WEB3TPPaymentAcceptRequest registered.");
        }
        regClass(WEB3TPPaymentAcceptResponse.class);
        if( DBG )
        {
            log.debug("WEB3TPPaymentAcceptResponse registered.");
        }
        
        // ���������t�]�͏ڍ׉�ʕ\�����b�Z�[�W��o�^
        regClass(WEB3TPEquityTradingPowerDetailRequest.class);
        if( DBG )
        {
            log.debug("WEB3TPEquityTradingPowerDetailRequest registered.");
        }
        regClass(WEB3TPEquityTradingPowerDetailResponse.class);
        if( DBG )
        {
            log.debug("WEB3TPEquityTradingPowerDetailResponse registered.");
        }
        
        // �M�p�V�K���]�͏ڍ׉�ʕ\�����b�Z�[�W��o�^
        regClass(WEB3TPMarginTradingPowerDetailRequest.class);
        if( DBG )
        {
            log.debug("WEB3TPMarginTradingPowerDetailRequest registered.");
        }
        regClass(WEB3TPMarginTradingPowerDetailResponse.class);
        if( DBG )
        {
            log.debug("WEB3TPMarginTradingPowerDetailResponse registered.");
        }

        // �]�͐���<�����v�Z>��ʕ\�����b�Z�[�W��o�^
        regClass(WEB3TPTransitionReferenceUseQuoteRequest.class);
        if( DBG )
        {
            log.debug("WEB3TPTransitionReferenceUseQuoteRequest registered.");
        }
        regClass(WEB3TPTransitionReferenceUseQuoteResponse.class);
        if( DBG )
        {
            log.debug("WEB3TPTransitionReferenceUseQuoteResponse registered.");
        }

        // (�ԍό�]��)�M�p����ԍϒ������N�G�X�g��o�^
        regClass(WEB3TPAfterRepayTransitionReferenceRequest.class);
        if( DBG )
        {
            log.debug("WEB3TPAfterRepayTransitionReferenceRequest registered.");
        }
        // (�ԍό�]��)�]�͐��ډ�ʕ\�����X�|���X��o�^
        regClass(WEB3TPAfterRepayTransitionReferenceResponse.class);
        if( DBG )
        {
            log.debug("WEB3TPAfterRepayTransitionReferenceResponse registered.");
        }
        
        //���Y�]���z�������N�G�X�g��o�^
        regClass(WEB3TPAssetHistoryRequest.class);
        if( DBG )
        {
            log.debug("WEB3TPAssetHistoryRequest registered.");
        }
        //���Y�]���z�������X�|���X
        regClass(WEB3TPAssetHistoryResponse.class);
        if( DBG )
        {
            log.debug("WEB3TPAssetHistoryResponse registered.");
        }

        //�s����������ʕ\�����N�G�X�g��o�^
        regClass(WEB3TPShortfallGenerationRequest.class);
        if( DBG )
        {
            log.debug("WEB3TPShortfallGenerationRequest registered.");
        }
        //�s����������ʕ\�����X�|���X��o�^
        regClass(WEB3TPShortfallGenerationResponse.class);
        if( DBG )
        {
            log.debug("WEB3TPShortfallGenerationResponse registered.");
        }

        //�Ǐؔ�����ʕ\�����N�G�X�g��o�^
        regClass(WEB3TPAdditionalGenerationRequest.class);
        if( DBG )
        {
            log.debug("WEB3TPAdditionalGenerationRequest registered.");
        }
        //�Ǐؔ�����ʕ\�����X�|���X��o�^
        regClass(WEB3TPAdditionalGenerationResponse.class);
        if( DBG )
        {
            log.debug("WEB3TPAdditionalGenerationResponse registered.");
        }
        
        // �a�莑�Y��ʕ\���n���h��
        regHandler(
            WEB3TPAssetTradingPowerPlugin.class,
            WEB3TPAssetRequest.class,
            WEB3TPAssetHandler.class,
            "assetRequest");
        if( DBG )
        {
            log.debug("WEB3TPAssetHandler registered.");
        }
        
        // ����]�͉�ʕ\���n���h��
        regHandler(
            WEB3TPAssetTradingPowerPlugin.class,
            WEB3TPTradingPowerRequest.class,
            WEB3TPTradingPowerHandler.class,
            "tradingPowerRequest");
        if( DBG )
        {
            log.debug("WEB3TPTradingPowerHandler registered.");
        }
        
        // ���v���������]�͎��Z��ʕ\���n���h��
        regHandler(
            WEB3TPAssetTradingPowerPlugin.class,
            WEB3TPDayTradeTradingPowerRequest.class,
            WEB3TPDayTradeTradingPowerHandler.class,
            "dayTradeTradingPowerRequest");
        if( DBG )
        {
            log.debug("WEB3TPDayTradeTradingPowerHandler registered.");
        }
                    
        // �]�͐��ډ�ʕ\���n���h��
        regHandler(
            WEB3TPAssetTradingPowerPlugin.class,
            WEB3TPTransitionReferenceRequest.class,
            WEB3TPTransitionReferenceHandler.class,
            "transitionReferenceRequest");
        if( DBG )
        {
            log.debug("WEB3TPTransitionReferenceHandler registered.");
        }
        
        // �o���󋵉�ʕ\���n���h��
        regHandler(
            WEB3TPAssetTradingPowerPlugin.class,
            WEB3TPPaymentAcceptRequest.class,
            WEB3TPPaymentAcceptHandler.class,
            "paymentAcceptRequest");
        if( DBG )
        {
            log.debug("WEB3TPPaymentAcceptHandler registered.");
        }
                            
        // ���������t�]�͏ڍ׉�ʕ\���n���h��
        regHandler(
            WEB3TPAssetTradingPowerPlugin.class,
            WEB3TPEquityTradingPowerDetailRequest.class,
            WEB3TPEquityTradingPowerDetailHandler.class,
            "equityTradingPowerDetailRequest");
        if( DBG )
        {
            log.debug("WEB3TPEquityTradingPowerDetailHandler registered.");
        }
                    
        // �M�p�V�K���]�͏ڍ׉�ʕ\���n���h��
        regHandler(
            WEB3TPAssetTradingPowerPlugin.class,
            WEB3TPMarginTradingPowerDetailRequest.class,
            WEB3TPMarginTradingPowerDetailHandler.class,
            "marginTradingPowerDetailRequest");
        if( DBG )
        {
            log.debug("WEB3TPMarginTradingPowerDetailHandler registered.");
        }
                
        // �]�͐���<�����v�Z>��ʕ\���n���h��
        regHandler(
            WEB3TPAssetTradingPowerPlugin.class,
            WEB3TPTransitionReferenceUseQuoteRequest.class,
            WEB3TPTransitionReferenceUseQuoteHandler.class, 
            "transitionReferenceUseQuoteRequest");
            
        if( DBG )
        {
            log.debug("WEB3TPTransitionReferenceUseQuoteHandler registered.");
            log.info("WEB3TPAssetTradingPowerPlugin bootstrap succeeded.");
        }

        // �ԍό�]�͏���ʕ\���n���h��)
        regHandler(
            WEB3TPAssetTradingPowerPlugin.class,
            WEB3TPAfterRepayTransitionReferenceRequest.class,
            WEB3TPAfterRepayTransitionReferenceHandler.class, 
            "getAfterRepayTransitionReference");
            
        if( DBG )
        {
            log.debug("WEB3TPAfterRepayTransitionReferenceHandler registered.");
            log.info("WEB3TPAssetTradingPowerPlugin bootstrap succeeded.");
        }
        
        //�icreate���Y�]���z������ʁj
        regHandler(
                WEB3TPAssetTradingPowerPlugin.class,
                WEB3TPAssetHistoryRequest.class,
                WEB3TPAssetHistoryHandler.class, 
                "createAssertHistory");
                
            if( DBG )
            {
                log.debug("WEB3TPAssetHistoryHandler registered.");
                log.info("WEB3TPAssetTradingPowerPlugin bootstrap succeeded.");
            }

        //�s����������ʕ\���n���h��
        regHandler(
            WEB3TPAssetTradingPowerPlugin.class,
            WEB3TPShortfallGenerationRequest.class,
            WEB3TPShortfallGenerationHandler.class,
            "createShortfallGenerationScreen");

        //�Ǐؔ�����ʕ\���n���h��
        regHandler(
            WEB3TPAssetTradingPowerPlugin.class,
            WEB3TPAdditionalGenerationRequest.class,
            WEB3TPAdditionalGenerationHandler.class,
            "createAdditionalGenerationScreen");
    }
}
@
