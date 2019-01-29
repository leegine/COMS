head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : Webbroker3-AdminTriggerOrder �v���O�C���N���X(WEB3AdminToAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/15 ��� (���u) �V�K�쐬 
                 : 2006/03/21 杊��](���u) �g���K�[�����Ǘ��ҁE�蓮����
                 : 2006/04/05 �A����(���u) �g���K�[�����Ǘ��ҁE�戵��~�ꗗ
                 : 2006/04/05 �A����(���u) �g���K�[�����Ǘ��ҁE�戵��~�폜
                 : 2006/04/05 �A����(���u) �g���K�[�����Ǘ��ҁE�戵��~�o�^
                 : 2006/04/05 �A����(���u) �g���K�[�����Ǘ��ҁE�戵��~����
                 : 2006/04/05 �A����(���u) �g���K�[�����Ǘ��ҁE�戵��~�ύX  
                 : 2006/07/10 ���@@�r(���u) �d�l�ύX���f��077     
*/
package webbroker3.admintriggerorder;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.admintriggerorder.data.WEB3AdminTriggerorderSessionDatabaseExtensions;
import webbroker3.admintriggerorder.handler.WEB3AdminToEquityOrderReferenceHandler;
import webbroker3.admintriggerorder.handler.WEB3AdminToIfoOrderReferenceHandler;
import webbroker3.admintriggerorder.handler.WEB3AdminToManualExpireHandler;
import webbroker3.admintriggerorder.handler.WEB3AdminToManualExpireMainHandler;
import webbroker3.admintriggerorder.handler.WEB3AdminToTradeStopChangeHandler;
import webbroker3.admintriggerorder.handler.WEB3AdminToTradeStopDeleteHandler;
import webbroker3.admintriggerorder.handler.WEB3AdminToTradeStopInputHandler;
import webbroker3.admintriggerorder.handler.WEB3AdminToTradeStopListHandler;
import webbroker3.admintriggerorder.handler.WEB3AdminToTradeStopRegistHandler;
import webbroker3.admintriggerorder.message.WEB3AdminToEquityOrderRefInpRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToEquityOrderRefInpResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToEquityOrderRefRefRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToEquityOrderRefRefResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToIfoOrderRefInpRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToIfoOrderRefInpResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToIfoOrderRefRefRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToIfoOrderRefRefResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseConfirmRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseConfirmResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseInputRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseInputResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseMainRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseMainResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseRunRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseRunResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseStatusRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseStatusResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToOrderRefRefCommonRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopDelCompleteRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopDelCompleteResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopDelConfirmRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopDelConfirmResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopInputRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopInputResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopListRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopListResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopRegCompleteRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopRegCompleteResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopRegConfirmRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopRegConfirmResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopUpdCompleteRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopUpdCompleteResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopUpdConfirmRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopUpdConfirmResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopUpdInputRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopUpdInputResponse;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToEquityOrderReferenceService;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToIfoOrderReferenceService;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToManualExpireMainService;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToManualExpireService;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToTradeStopChangeService;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToTradeStopDeleteService;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToTradeStopInputService;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToTradeStopListService;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToTradeStopRegistService;
import webbroker3.admintriggerorder.service.delegate.stdimpls.WEB3AdminToEquityOrderReferenceServiceImpl;
import webbroker3.admintriggerorder.service.delegate.stdimpls.WEB3AdminToIfoOrderReferenceServiceImpl;
import webbroker3.admintriggerorder.service.delegate.stdimpls.WEB3AdminToManualExpireMainServiceImpl;
import webbroker3.admintriggerorder.service.delegate.stdimpls.WEB3AdminToManualExpireServiceImpl;
import webbroker3.admintriggerorder.service.delegate.stdimpls.WEB3AdminToTradeStopChangeServiceImpl;
import webbroker3.admintriggerorder.service.delegate.stdimpls.WEB3AdminToTradeStopDeleteServiceImpl;
import webbroker3.admintriggerorder.service.delegate.stdimpls.WEB3AdminToTradeStopInputServiceImpl;
import webbroker3.admintriggerorder.service.delegate.stdimpls.WEB3AdminToTradeStopListServiceImpl;
import webbroker3.admintriggerorder.service.delegate.stdimpls.WEB3AdminToTradeStopRegistServiceImpl;
import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.util.WEB3LogUtility;

/**
 * Webbroker3-AdminTriggerOrder �v���O�C���N���X<BR>
 *   
 * @@author ���
 * @@version 1.0
 */
public class WEB3AdminToAppPlugin extends Plugin
{
    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToAppPlugin.class);

    /**
     * �R���X�g���N�^�B
     */
    public WEB3AdminToAppPlugin()
    {
        
    }

    /**
     * �v���O�C���G���g���[�|�C���g�B
     */
    public static void plug() throws Exception
    {
        final String METHOD_NAME = " plug()";
        log.entering(METHOD_NAME);
        
        plug(WEB3AdminToAppPlugin.class);
        
        log.exiting(METHOD_NAME);
    }
    
    /**
     * �v���O�C�������B
     */
    public static void onPlug() throws Exception 
    {
        final String METHOD_NAME = " onPlug()";
        log.entering(METHOD_NAME);
        
        //���̃v���O�C������ɓǂݍ��ޕK�v�̂���v���O�C���̎w��B
        //install the system plugins that we need
        KernelPlugin.plug();
        
        // DatabaseExtensions �̃v���O�C������ ----------------------
        // WEB3AdminTriggerorderSessionDatabaseExtensions ���v���O�C��
        WEB3AdminTriggerorderSessionDatabaseExtensions.plug();
        
        //Service �̓o�^
        //�g���K�[�����Ǘ��ҁE�敨OP�����Ɖ�T�[�r�X
        Services.registerService(
            WEB3AdminToIfoOrderReferenceService.class,
            new WEB3AdminToIfoOrderReferenceServiceImpl());
        
        //�g���K�[�����Ǘ��ҁE���������Ɖ�T�[�r�X
        Services.registerService(
                WEB3AdminToEquityOrderReferenceService.class,
            new WEB3AdminToEquityOrderReferenceServiceImpl());
        
        //�g���K�[�����Ǘ��ҁE�蓮�����T�[�r�X
        Services.registerService(
            WEB3AdminToManualExpireService.class,
            new WEB3AdminToManualExpireServiceImpl());
        
        //�g���K�[�����Ǘ��ҁE�戵��~�ꗗ�T�[�r�X
        Services.registerService(
            WEB3AdminToTradeStopListService.class,
            new WEB3AdminToTradeStopListServiceImpl());
        
        //�g���K�[�����Ǘ��ҁE�戵��~�ύX�T�[�r�X
        Services.registerService(
            WEB3AdminToTradeStopChangeService.class,
            new WEB3AdminToTradeStopChangeServiceImpl());
        
        //�g���K�[�����Ǘ��ҁE�戵��~�폜�T�[�r�X
        Services.registerService(
            WEB3AdminToTradeStopDeleteService.class,
            new WEB3AdminToTradeStopDeleteServiceImpl());
        
        //�g���K�[�����Ǘ��ҁE�戵��~���̓T�[�r�X
        Services.registerService(
            WEB3AdminToTradeStopInputService.class,
            new WEB3AdminToTradeStopInputServiceImpl());
        
        //�g���K�[�����Ǘ��ҁE�戵��~�o�^�T�[�r�X
        Services.registerService(
            WEB3AdminToTradeStopRegistService.class,
            new WEB3AdminToTradeStopRegistServiceImpl());
        
        //�g���K�[�����Ǘ��ҁE�蓮�������C���T�[�r�X
        Services.registerService(
            WEB3AdminToManualExpireMainService.class,
            new WEB3AdminToManualExpireMainServiceImpl());
        
        // Service.execute()�Ăяo���O���  ----------------------
        // �����J�n�����Ə����I�����������O�o�͂���悤�ɐݒ肷��

        //�g���K�[�����Ǘ��ҁE�敨OP�����Ɖ�T�[�r�X�C���^�Z�v�^ 
        Services.addInterceptor(
            WEB3AdminToIfoOrderReferenceService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�g���K�[�����Ǘ��ҁE���������Ɖ�T�[�r�X�C���^�Z�v�^ 
        Services.addInterceptor(
                WEB3AdminToEquityOrderReferenceService.class,
                new WEB3LogSysTimeInterceptor());
        
        //�g���K�[�����Ǘ��ҁE�蓮�����T�[�r�X
        Services.addInterceptor(
            WEB3AdminToManualExpireService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�g���K�[�����Ǘ��ҁE�戵��~�ꗗ�T�[�r�X
        Services.addInterceptor(
            WEB3AdminToTradeStopListService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�g���K�[�����Ǘ��ҁE�戵��~�ύX�T�[�r�X
        Services.addInterceptor(
            WEB3AdminToTradeStopChangeService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�g���K�[�����Ǘ��ҁE�戵��~�폜�T�[�r�X
        Services.addInterceptor(
            WEB3AdminToTradeStopDeleteService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�g���K�[�����Ǘ��ҁE�戵��~���̓T�[�r�X
        Services.addInterceptor(
            WEB3AdminToTradeStopInputService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�g���K�[�����Ǘ��ҁE�戵��~�o�^�T�[�r�X
        Services.addInterceptor(
            WEB3AdminToTradeStopRegistService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�g���K�[�����Ǘ��ҁE�蓮�������C���T�[�r�X
        Services.addInterceptor(
            WEB3AdminToManualExpireMainService.class,
            new WEB3LogSysTimeInterceptor());
        
        //Service �� ServiceInterceptor ��ݒ肷�� ----------------------
        //�ݒ���e�m�F�T�[�r�X
        Services.addInterceptor(
            WEB3AdminToIfoOrderReferenceService.class,
            new WEB3AdminToIfoOrderReferenceServiceInterceptor());   
        
        //Service �� ServiceInterceptor ��ݒ肷�� ----------------------
        //�ݒ���e�m�F�T�[�r�X
        Services.addInterceptor(
                WEB3AdminToEquityOrderReferenceService.class,
            new WEB3AdminToEquityOrderReferenceServiceInterceptor());   
        
        //�g���K�[�����Ǘ��ҁE�蓮�����T�[�r�X
        Services.addInterceptor(
            WEB3AdminToManualExpireService.class,
            new WEB3AdminToManualExpireServiceInterceptor());   
        
        //�g���K�[�����Ǘ��ҁE�戵��~�ꗗ�T�[�r�X
        Services.addInterceptor(
            WEB3AdminToTradeStopListService.class,
            new WEB3AdminToTradeStopServiceInterceptor());
        
        //�g���K�[�����Ǘ��ҁE�戵��~�ύX�T�[�r�X
        Services.addInterceptor(
            WEB3AdminToTradeStopChangeService.class,
            new WEB3AdminToTradeStopServiceInterceptor());
        
        //�g���K�[�����Ǘ��ҁE�戵��~�폜�T�[�r�X
        Services.addInterceptor(
            WEB3AdminToTradeStopDeleteService.class,
            new WEB3AdminToTradeStopServiceInterceptor());
        
        //�g���K�[�����Ǘ��ҁE�戵��~���̓T�[�r�X
        Services.addInterceptor(
            WEB3AdminToTradeStopInputService.class,
            new WEB3AdminToTradeStopServiceInterceptor());
        
        //�g���K�[�����Ǘ��ҁE�戵��~�o�^�T�[�r�X
        Services.addInterceptor(
            WEB3AdminToTradeStopRegistService.class,
            new WEB3AdminToTradeStopServiceInterceptor());
        
        // Service �� Interceptor �ݒ菈�� ----------------------
        // �����g�����U�N�V�����ݒ�
        //�ݒ���e�m�F�T�[�r�X
        Services.addInterceptor(
            WEB3AdminToIfoOrderReferenceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        // �����g�����U�N�V�����ݒ�
        //�ݒ���e�m�F�T�[�r�X
        Services.addInterceptor(
                WEB3AdminToEquityOrderReferenceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        // �����g�����U�N�V�����ݒ�
        //�g���K�[�����Ǘ��ҁE�蓮�����T�[�r�X
        Services.addInterceptor(
            WEB3AdminToManualExpireService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�g���K�[�����Ǘ��ҁE�戵��~�ꗗ�T�[�r�X
        Services.addInterceptor(
            WEB3AdminToTradeStopListService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�g���K�[�����Ǘ��ҁE�戵��~�ύX�T�[�r�X
        Services.addInterceptor(
            WEB3AdminToTradeStopChangeService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�g���K�[�����Ǘ��ҁE�戵��~�폜�T�[�r�X
        Services.addInterceptor(
            WEB3AdminToTradeStopDeleteService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //���K�[�����Ǘ��ҁE�戵��~���̓T�[�r�X
        Services.addInterceptor(
            WEB3AdminToTradeStopInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�g���K�[�����Ǘ��ҁE�戵��~�o�^�T�[�r�X
        Services.addInterceptor(
            WEB3AdminToTradeStopRegistService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�g���K�[�����Ǘ��ҁE�蓮�������C���T�[�r�X        
        Services.addInterceptor(
            WEB3AdminToManualExpireMainService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
    
        //Message �̓o�^���� ----------------------
        //�g���K�[�����Ǘ��ҁE�敨OP�����Ɖ�N�G�X�g
        regClass(WEB3AdminToIfoOrderRefRefRequest.class);
                
        //�g���K�[�����Ǘ��ҁE�敨OP�����Ɖ�X�|���X
        regClass(WEB3AdminToIfoOrderRefRefResponse.class);
        
        //�g���K�[�����Ǘ��ҁE�敨OP�����Ɖ���̓��N�G�X�g
        regClass(WEB3AdminToIfoOrderRefInpRequest.class);
        
        //�g���K�[�����Ǘ��ҁE�敨OP�����Ɖ���̓��X�|���X
        regClass(WEB3AdminToIfoOrderRefInpResponse.class);
        
        //�g���K�[�����Ǘ��ҁE�����Ɖ�ʃ��N�G�X�g
        regClass(WEB3AdminToOrderRefRefCommonRequest.class);
        
        //�g���K�[�����Ǘ��ҁE���������Ɖ���̓��N�G�X�g
        regClass(WEB3AdminToEquityOrderRefInpRequest.class);
        
        //�g���K�[�����Ǘ��ҁE���������Ɖ���̓��X�|���X
        regClass(WEB3AdminToEquityOrderRefInpResponse.class);
        
        //�g���K�[�����Ǘ��ҁE���������Ɖ�N�G�X�g
        regClass(WEB3AdminToEquityOrderRefRefRequest.class);
        
        //�g���K�[�����Ǘ��ҁE���������Ɖ�X�|���X
        regClass(WEB3AdminToEquityOrderRefRefResponse.class);
        
        //�g���K�[�����Ǘ��ҁE�蓮�����m�F���N�G�X�g
        regClass(WEB3AdminToManualLapseConfirmRequest.class);
        
        //�g���K�[�����Ǘ��ҁE�蓮�����m�F���X�|���X
        regClass(WEB3AdminToManualLapseConfirmResponse.class);
        
        //�g���K�[�����Ǘ��ҁE�蓮�������̓��N�G�X�g
        regClass(WEB3AdminToManualLapseInputRequest.class);
        
        //�g���K�[�����Ǘ��ҁE�蓮�������̓��X�|���X
        regClass(WEB3AdminToManualLapseInputResponse.class);
        
        //�g���K�[�����Ǘ��ҁE�蓮�������C�����N�G�X�g
        regClass(WEB3AdminToManualLapseMainRequest.class);
        
        //�g���K�[�����Ǘ��ҁE�蓮�������C�����X�|���X
        regClass(WEB3AdminToManualLapseMainResponse.class);
        
        //�g���K�[�����Ǘ��ҁE�蓮���������N�����N�G�X�g
        regClass(WEB3AdminToManualLapseRunRequest.class);
        
        //�g���K�[�����Ǘ��ҁE�蓮���������N�����X�|���X
        regClass(WEB3AdminToManualLapseRunResponse.class);
        
        //�g���K�[�����Ǘ��ҁE�蓮���������X�e�[�^�X�m�F���N�G�X�g
        regClass(WEB3AdminToManualLapseStatusRequest.class);
        
        //�g���K�[�����Ǘ��ҁE�蓮���������X�e�[�^�X�m�F���X�|���X
        regClass(WEB3AdminToManualLapseStatusResponse.class);
        
        //�g���K�[�����Ǘ��ҁE�戵��~�폜�������N�G�X�g
        regClass(WEB3AdminToTradeStopDelCompleteRequest.class);
        
        //�g���K�[�����Ǘ��ҁE�戵��~�폜�������X�|���X
        regClass(WEB3AdminToTradeStopDelCompleteResponse.class);
        
        //�g���K�[�����Ǘ��ҁE�戵��~�폜�m�F���N�G�X�g
        regClass(WEB3AdminToTradeStopDelConfirmRequest.class);
        
        //�g���K�[�����Ǘ��ҁE�戵��~�폜�m�F���X�|���X
        regClass(WEB3AdminToTradeStopDelConfirmResponse.class);
        
        //�g���K�[�����Ǘ��ҁE�戵��~���̓��N�G�X�g
        regClass(WEB3AdminToTradeStopInputRequest.class);
        
        //�g���K�[�����Ǘ��ҁE�戵��~���̓��X�|���X
        regClass(WEB3AdminToTradeStopInputResponse.class);
        
        //�g���K�[�����Ǘ��ҁE�戵��~�ꗗ���N�G�X�g
        regClass(WEB3AdminToTradeStopListRequest.class);
        
        //�g���K�[�����Ǘ��ҁE�戵��~�ꗗ���X�|���X
        regClass(WEB3AdminToTradeStopListResponse.class);
        
        //�g���K�[�����Ǘ��ҁE�戵��~�o�^�������N�G�X�g
        regClass(WEB3AdminToTradeStopRegCompleteRequest.class);
        
        //�g���K�[�����Ǘ��ҁE�戵��~�o�^�������X�|���X
        regClass(WEB3AdminToTradeStopRegCompleteResponse.class);
        
        //�g���K�[�����Ǘ��ҁE�戵��~�o�^�m�F���N�G�X�g
        regClass(WEB3AdminToTradeStopRegConfirmRequest.class);
        
        //�g���K�[�����Ǘ��ҁE�戵��~�o�^�m�F���X�|���X
        regClass(WEB3AdminToTradeStopRegConfirmResponse.class);
        
        //�g���K�[�����Ǘ��ҁE�戵��~�ύX�������N�G�X�g
        regClass(WEB3AdminToTradeStopUpdCompleteRequest.class);
        
        //�g���K�[�����Ǘ��ҁE�戵��~�ύX�������X�|���X
        regClass(WEB3AdminToTradeStopUpdCompleteResponse.class);
        
        //�g���K�[�����Ǘ��ҁE�戵��~�ύX�m�F���N�G�X�g
        regClass(WEB3AdminToTradeStopUpdConfirmRequest.class);
        
        //�g���K�[�����Ǘ��ҁE�戵��~�ύX�m�F���X�|���X
        regClass(WEB3AdminToTradeStopUpdConfirmResponse.class);
        
        //�g���K�[�����Ǘ��ҁE�戵��~�ύX���̓��N�G�X�g
        regClass(WEB3AdminToTradeStopUpdInputRequest.class);
        
        //�g���K�[�����Ǘ��ҁE�戵��~�ύX���̓��X�|���X
        regClass(WEB3AdminToTradeStopUpdInputResponse.class);
        
        //Handler �̓o�^����
        //�g���K�[�����Ǘ��ҁE�敨OP�����Ɖ�n���h��
        regHandler(
            WEB3AdminToAppPlugin.class,
            WEB3AdminToIfoOrderRefInpRequest.class,
            WEB3AdminToIfoOrderReferenceHandler.class,
            "getInputScreen");
        
        regHandler(
            WEB3AdminToAppPlugin.class,
            WEB3AdminToIfoOrderRefRefRequest.class,
            WEB3AdminToIfoOrderReferenceHandler.class,
            "getReferenceScreen");
       
        //Handler �̓o�^����
        //�g���K�[�����Ǘ��ҁE���������Ɖ�T�[�r�X�n���h��
        regHandler(
                WEB3AdminToAppPlugin.class,
                WEB3AdminToEquityOrderRefInpRequest.class,
                WEB3AdminToEquityOrderReferenceHandler.class,
                "getInputScreen");
            
            regHandler(
                WEB3AdminToAppPlugin.class,
                WEB3AdminToEquityOrderRefRefRequest.class,
                WEB3AdminToEquityOrderReferenceHandler.class,
                "getReferenceScreen");

        //Handler �̓o�^����
        //�g���K�[�����Ǘ��ҁE�蓮�����n���h��
            regHandler(
                WEB3AdminToAppPlugin.class,
                WEB3AdminToManualLapseInputRequest.class,
                WEB3AdminToManualExpireHandler.class,
                "getInputScreen");

            regHandler(
                WEB3AdminToAppPlugin.class,
                WEB3AdminToManualLapseConfirmRequest.class,
                WEB3AdminToManualExpireHandler.class,
                "confirmManualExpire");

            regHandler(
                WEB3AdminToAppPlugin.class,
                WEB3AdminToManualLapseRunRequest.class,
                WEB3AdminToManualExpireHandler.class,
                "runManualExpire");

            regHandler(
                WEB3AdminToAppPlugin.class,
                WEB3AdminToManualLapseStatusRequest.class,
                WEB3AdminToManualExpireHandler.class,
                "confirmStatus");

            //�g���K�[�����Ǘ��ҁE�蓮�������C���n���h��
            regHandler(
                WEB3AdminToAppPlugin.class,
                WEB3AdminToManualLapseMainRequest.class,
                WEB3AdminToManualExpireMainHandler.class,
                "manualExpireRequest");
            
            //�g���K�[�����Ǘ��ҁE�戵��~�ꗗ�n���h��
            regHandler(
                WEB3AdminToAppPlugin.class,
                WEB3AdminToTradeStopListRequest.class,
                WEB3AdminToTradeStopListHandler.class,
                "getListScreen");
            
            //�g���K�[�����Ǘ��ҁE�戵��~�ύX�n���h��
            regHandler(
                WEB3AdminToAppPlugin.class,
                WEB3AdminToTradeStopUpdInputRequest.class,
                WEB3AdminToTradeStopChangeHandler.class,
                "getInputScreen");
            
            regHandler(
                WEB3AdminToAppPlugin.class,
                WEB3AdminToTradeStopUpdConfirmRequest.class,
                WEB3AdminToTradeStopChangeHandler.class,
                "confirmChange");
            
            regHandler(
                WEB3AdminToAppPlugin.class,
                WEB3AdminToTradeStopUpdCompleteRequest.class,
                WEB3AdminToTradeStopChangeHandler.class,
                "completeChange");
            
            //�g���K�[�����Ǘ��ҁE�戵��~�폜�n���h��
            regHandler(
                WEB3AdminToAppPlugin.class,
                WEB3AdminToTradeStopDelConfirmRequest.class,
                WEB3AdminToTradeStopDeleteHandler.class,
                "confirmDelete");
            
            regHandler(
                WEB3AdminToAppPlugin.class,
                WEB3AdminToTradeStopDelCompleteRequest.class,
                WEB3AdminToTradeStopDeleteHandler.class,
                "completeDelete");
            
            //�g���K�[�����Ǘ��ҁE�戵��~���̓n���h��
            regHandler(
                WEB3AdminToAppPlugin.class,
                WEB3AdminToTradeStopInputRequest.class,
                WEB3AdminToTradeStopInputHandler.class,
                "getInputScreen");
            
            //�g���K�[�����Ǘ��ҁE�戵��~�o�^�n���h��
            regHandler(
                WEB3AdminToAppPlugin.class,
                WEB3AdminToTradeStopRegConfirmRequest.class,
                WEB3AdminToTradeStopRegistHandler.class,
                "confirmRegist");
            
            //�g���K�[�����Ǘ��ҁE�戵��~�o�^�n���h��
            regHandler(
                WEB3AdminToAppPlugin.class,
                WEB3AdminToTradeStopRegCompleteRequest.class,
                WEB3AdminToTradeStopRegistHandler.class,
                "completeRegist");
            
        log.exiting(METHOD_NAME);
    }
}
@
