head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.38.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �؋����v�Z�v���O�C���̃v���O�C���N���X(WEB3IfoDepositPlugin.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/10/12 �R�c�@@��i(FLJ) �V�K�쐬
*/
package webbroker3.ifodeposit;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.ifodeposit.data.WEB3TPLibIfoDepositSessionDatabaseExtensions;
import webbroker3.ifodeposit.handler.WEB3IfoDepositCalcResultSaveHandler;
import webbroker3.ifodeposit.handler.WEB3IfoDepositTransitionReferenceHandler;
import webbroker3.ifodeposit.message.WEB3IfoDepositCalcResultSaveRequest;
import webbroker3.ifodeposit.message.WEB3IfoDepositCalcResultSaveResponse;
import webbroker3.ifodeposit.message.WEB3IfoDepositTransitionReferenceRequest;
import webbroker3.ifodeposit.message.WEB3IfoDepositTransitionReferenceResponse;
import webbroker3.ifodeposit.service.delegate.WEB3IfoDepositCalcResultCreatePerAccountService;
import webbroker3.ifodeposit.service.delegate.WEB3IfoDepositCalcResultSaveService;
import webbroker3.ifodeposit.service.delegate.WEB3IfoDepositTransitionReferenceService;
import webbroker3.ifodeposit.service.delegate.stdimpls.WEB3IfoDepositCalcResultCreatePerAccountServiceImpl;
import webbroker3.ifodeposit.service.delegate.stdimpls.WEB3IfoDepositCalcResultSaveServiceImpl;
import webbroker3.ifodeposit.service.delegate.stdimpls.WEB3IfoDepositTransitionReferenceServiceImpl;
import webbroker3.util.WEB3LogUtility;


/**
 * �؋����v�Z�v���O�C���̃v���O�C���N���X<br>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public class WEB3IfoDepositPlugin extends Plugin
{
    
    /**
     * ���O
     */
    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoDepositPlugin.class);

    /**
     * �R���X�g���N�^
     */
    public WEB3IfoDepositPlugin()
    {
    }

    /**
     * ���̃v���O�C�����v���O�C������Ƃ��Ɏg�p���郁�\�b�h
     */
    public static void plug() throws Exception
    {
        plug(WEB3IfoDepositPlugin.class);
    }

    /**
     * ���̃v���O�C�����v���O�C������Ƃ��ɏ�������郁�\�b�h
     */
    public static void onPlug() throws Exception
    {

        // Kernel�v���O�C�����v���O
        KernelPlugin.plug();
        
        //�f�[�^�x�[�X���v���O
        
        WEB3TPLibIfoDepositSessionDatabaseExtensions.plug();

        // ---------------------------------------------------------------------

        // �؋����v�Z�T�[�r�X��o�^
        Services.registerService(
            WEB3IfoDepositCalcService.class,
            new WEB3IfoDepositCalcServiceImpl());
        
        log.debug("WEB3IfoDepositCalcService registered.");

        // �؋����v�Z�T�[�r�X�C���^�[�Z�v�^��o�^
        Services.addInterceptor(
            WEB3IfoDepositCalcService.class,
            new WEB3IfoDepositCalcServiceInterceptor());

        log.debug("WEB3IfoDepositCalcServiceInterceptor added to WEB3IfoDepositCalcService");
        
        // TransactionalInterceptor��o�^
        Services.addInterceptor(
            WEB3IfoDepositCalcService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        log.debug("TransactionalInterceptor added to WEB3IfoDepositCalcService");

        // ---------------------------------------------------------------------

        // �؋������ڎQ�Ɖ�ʕ\���T�[�r�X��o�^
        Services.registerService(
            WEB3IfoDepositTransitionReferenceService.class,
            new WEB3IfoDepositTransitionReferenceServiceImpl());

        log.debug("WEB3IfoDepositTransitionReferenceService registered.");

        // �؋������ڎQ�Ɖ�ʕ\���T�[�r�X�C���^�[�Z�v�^��o�^
        Services.addInterceptor(
		    WEB3IfoDepositTransitionReferenceService.class,
            new WEB3IfoDepositTransitionReferenceServiceInterceptor());
          
        log.debug("WEB3IfoDepositTransitionReferenceServiceInterceptor added to WEB3IfoDepositTransitionReferenceService");
            
        // TransactionalInterceptor��o�^
        Services.addInterceptor(
            WEB3IfoDepositTransitionReferenceService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        log.debug("TransactionalInterceptor added to WEB3IfoDepositTransitionReferenceService");
        
        // ---------------------------------------------------------------------

        // �؋������ڎQ�Ɖ�ʕ\�����b�Z�[�W��o�^
        regClass(WEB3IfoDepositTransitionReferenceRequest.class);
        log.debug("WEB3IfoDepositTransitionReferenceRequest registered.");

        regClass(WEB3IfoDepositTransitionReferenceResponse.class);
        log.debug("WEB3IfoDepositTransitionReferenceResponse registered.");
        
        // �؋������ڎQ�Ɖ�ʕ\���n���h��
        regHandler(
            WEB3IfoDepositPlugin.class,
            WEB3IfoDepositTransitionReferenceRequest.class,
            WEB3IfoDepositTransitionReferenceHandler.class,
            "ifoDepositTransitionReferenceRequest");
      
        // ---------------------------------------------------------------------
        
        // �؋����v�Z���ʌڋq���쐬�T�[�r�X��o�^
        Services.registerService(
                WEB3IfoDepositCalcResultCreatePerAccountService.class,
            new WEB3IfoDepositCalcResultCreatePerAccountServiceImpl());

        log.debug("WEB3IfoDepositCalcResultCreatePerAccountService registered.");
        
        // �؋����v�Z���ʕۑ��T�[�r�X��o�^
        Services.registerService(
            WEB3IfoDepositCalcResultSaveService.class,
            new WEB3IfoDepositCalcResultSaveServiceImpl());

        log.debug("WEB3IfoDepositCalcResultSaveService registered.");
        
        // �؋����v�Z���ʌڋq���쐬�T�[�r�X�C���^�[�Z�v�^��o�^
        Services.addInterceptor(
                WEB3IfoDepositCalcResultCreatePerAccountService.class,
            new WEB3IfoDepositCalcResultCreatePerAccountServiceInterceptor());

        log.debug("WEB3IfoDepositCalcResultCreatePerAccountServiceInterceptor added to WEB3IfoDepositCalcResultCreatePerAccountService");
        
        // ---------------------------------------------------------------------
        
        // �؋����v�Z���ʕۑ����b�Z�[�W��o�^
        regClass(WEB3IfoDepositCalcResultSaveRequest.class);
        log.debug("WEB3IfoDepositCalcResultSaveRequest registered.");

        regClass(WEB3IfoDepositCalcResultSaveResponse.class);
        log.debug("WEB3IfoDepositCalcResultSaveResponse registered.");

        // �؋����v�Z���ʕۑ��n���h��
        regHandler(
            WEB3IfoDepositPlugin.class,
            WEB3IfoDepositCalcResultSaveRequest.class,
            WEB3IfoDepositCalcResultSaveHandler.class,
            "ifoDepositCalcResultSaveRequest");

        log.debug("WEB3IfoDepositCalcResultSaveHandler registered.");

        // ---------------------------------------------------------------------
        
        log.info("WEB3IfoDepositPlugin bootstrap succeeded.");

    }

}
@
