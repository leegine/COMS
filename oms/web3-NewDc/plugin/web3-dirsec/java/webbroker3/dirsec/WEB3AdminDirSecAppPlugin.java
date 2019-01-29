head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : Webbroker3-AdminDirsec �v���O�C��(WEB3AdminDirsecAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/30 ���� (���u) �V�K�쐬
Revesion History : 2006/07/24 ���� (���u) ����̍X�E���f��008-010
Revesion History : 2006/08/11 ���� (���u) ����̍X�E���f��013
Revesion History : 2007/01/17  �Ӑ� (���u) �d�l�ύX���f��No.017-21
Revesion History : 2007/02/17  ���؎q (���u) �����̖��No.002-004
Revesion History : 2007/06/20 ���G�� (���u) �ڋq�������o�^�`�[�X�e�[�^�X�X�V�Ή�
Revesion History : 2008/05/04 �đo�g (���u) ����̍X�E���f��117
Revesion History : 2008/04/30 �đo�g (���u) ����̍X�E���f��116
Revesion History : 2008/07/22 �k�v�u (���u) ����̍X�E���f��132
*/

package webbroker3.dirsec;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.dirsec.data.WEB3DirsecMasterDatabaseExtensions;
import webbroker3.dirsec.data.WEB3DirsecSessionDatabaseExtensions;
import webbroker3.dirsec.handler.WEB3AdminDirSecAPMngForcedStartHandler;
import webbroker3.dirsec.handler.WEB3AdminDirSecBatoTroubleFlagUpdateHandler;
import webbroker3.dirsec.handler.WEB3AdminDirSecAccRegVoucherStatUpdHandler;
import webbroker3.dirsec.handler.WEB3AdminDirSecAioOrderUnitTableUpdateHandler;
import webbroker3.dirsec.handler.WEB3AdminDirSecDaemonTriggerTableUpdateHandler;
import webbroker3.dirsec.handler.WEB3AdminDirSecHostTableStatusUpdateHandler;
import webbroker3.dirsec.handler.WEB3AdminDirSecTriggerIssueHandler;
import webbroker3.dirsec.handler.WEB3AdminDirSecUploadTableEndDateUpdateHandler;
import webbroker3.dirsec.handler.WEB3AdminFrontOrderRouteChangeHandler;
import webbroker3.dirsec.handler.WEB3AdminFrontSwitchOrderRouteHandler;
import webbroker3.dirsec.handler.WEB3FrontOrderRouteChangeFormSelectHandler;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartCmpRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartCmpResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartCnfRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartCnfResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartInpRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartInpResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngListRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngListResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherSearchInpRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherSearchInpResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherSearchResRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherSearchResResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherStatUpdCompRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherStatUpdCompResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherStatUpdConfRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherStatUpdConfResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueCompleteResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueConfirmResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueInputRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueInputResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueListRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueListResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableSearchInputRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableSearchResultRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecDaemonTriggerTableSearchInputRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecDaemonTriggerTableSearchResultRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecDaemonTriggerTableUpdateCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecDaemonTriggerTableUpdateConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableReferenceRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableReferenceResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableSearchInputRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableSearchInputResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableSearchListRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableSearchListResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableStatusCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableStatusCompleteResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableStatusConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableStatusConfirmResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecUploadTableListRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecUploadTableListResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecUploadTableUpdateCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecUploadTableUpdateCompleteResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecUploadTableUpdateConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecUploadTableUpdateConfirmResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingCompleteResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingConfirmResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingListRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingListResponse;
import webbroker3.dirsec.message.WEB3AdminFrontChangeProcessSelectRequest;
import webbroker3.dirsec.message.WEB3AdminFrontChangeProcessSelectResponse;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeCompleteResponse;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeConfirmResponse;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeSelectRequest;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeSelectResponse;
import webbroker3.dirsec.message.WEB3AdminSwitchOrderRouteCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminSwitchOrderRouteCompleteResponse;
import webbroker3.dirsec.message.WEB3AdminSwitchOrderRouteConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminSwitchOrderRouteConfirmResponse;
import webbroker3.dirsec.message.WEB3AdminSwitchOrderRouteSelectRequest;
import webbroker3.dirsec.message.WEB3AdminSwitchOrderRouteSelectResponse;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecAPMngForcedStartService;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecAccRegVoucherStatUpdService;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecAioOrderUnitTableUpdateService;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecBatoTroubleFlagUpdateService;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecDaemonTriggerTableUpdateService;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecHostTableStatusUpdateService;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecTriggerIssueService;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecUploadTableEndDateUpdateService;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecFrontOrderCommonService;
import webbroker3.dirsec.service.delegate.WEB3AdminFrontOrderRouteChangeService;
import webbroker3.dirsec.service.delegate.WEB3AdminSwitchOrderRouteService;
import webbroker3.dirsec.service.delegate.WEB3FrontOrderRouteChangeFormSelectService;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecAPMngForcedStartServiceImpl;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecDaemonTriggerTableUpdateServiceImpl;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecHostTableStatusUpdateServiceImpl;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecTriggerIssueServiceImpl;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecUploadTableEndDateUpdateServiceImpl;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecFrontOrderCommonServiceImpl;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminFrontOrderRouteChangeServiceImpl;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminSwitchOrderRouteServiceImpl;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3FrontOrderRouteChangeFormSelectServiceImpl;
import webbroker3.mqgateway.WEB3MQGatewayInterceptor;
import webbroker3.util.WEB3LogUtility;

/**
 * Webbroker3-AdminDirsec �v���O�C���N���X�B
 *                                                                
 * @@author ����
 * @@version 1.0
 */
public class WEB3AdminDirSecAppPlugin extends Plugin
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminDirSecAppPlugin.class);

    /**
     * �R���X�g���N�^�B
     */
    public WEB3AdminDirSecAppPlugin()
    {

    }

    /**
     * �v���O�C���G���g���[�|�C���g�B
     */
    public static void plug() throws Exception
    {
        String STR_METHOD_NAME = " plug()";
        log.entering(STR_METHOD_NAME);

        plug(WEB3AdminDirSecAppPlugin.class);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �v���O�C�������B
     */
    public static void onPlug() throws Exception
    {
        String STR_METHOD_NAME = " onPlug()";
        log.entering(STR_METHOD_NAME);

        // ���̃v���O�C������ɓǂݍ��ޕK�v�̂���v���O�C���̎w��B
        // install the system plugins that we need
        KernelPlugin.plug();

        //DatabaseExtensions �̃v���O�C������ ----------------------
        WEB3DirsecSessionDatabaseExtensions.plug();
        WEB3DirsecMasterDatabaseExtensions.plug();

        // Service �̓o�^���� ----------------------

        //�Ǘ��҃L���[�e�[�u���X�e�[�^�X�X�V�T�[�r�X
        Services.registerService(WEB3AdminDirSecHostTableStatusUpdateService.class, 
            new WEB3AdminDirSecHostTableStatusUpdateServiceImpl());

        //�Ǘ��҃f�[�����g���K�[�e�[�u���X�e�[�^�X�X�V�T�[�r�X
        Services.registerService(WEB3AdminDirSecDaemonTriggerTableUpdateService.class, 
            new WEB3AdminDirSecDaemonTriggerTableUpdateServiceImpl());

        // �Ǘ��Ғ����P�ʃe�[�u��������ԍX�V�T�[�r�X
        Services.registerService(WEB3AdminDirSecAioOrderUnitTableUpdateService.class, 
            new WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl());

        //�Ǘ��҃A�b�v���[�h�e�[�u���I�������X�V�T�[�r�X
        Services.registerService(WEB3AdminDirSecUploadTableEndDateUpdateService.class, 
            new WEB3AdminDirSecUploadTableEndDateUpdateServiceImpl());

        // �Ǘ��Ҕ����o�H�ؑ֏��������I���T�[�r�X
        Services.registerService(WEB3FrontOrderRouteChangeFormSelectService.class,
            new WEB3FrontOrderRouteChangeFormSelectServiceImpl());

        // �Ǘ��Ҕ����o�H�ؑփT�[�r�X
        Services.registerService(WEB3AdminFrontOrderRouteChangeService.class,
            new WEB3AdminFrontOrderRouteChangeServiceImpl());

        // �Ǘ��Ҕ�����ؑփT�[�r�X
        Services.registerService(WEB3AdminSwitchOrderRouteService.class,
            new WEB3AdminSwitchOrderRouteServiceImpl());

        // �Ǘ��҃t�����g�����Ǘ����ʃT�[�r�X
        Services.registerService(WEB3AdminDirSecFrontOrderCommonService.class,
            new WEB3AdminDirSecFrontOrderCommonServiceImpl());

        //�Ǘ��Ҍڋq���o�^�`�[�X�e�[�^�X�X�V�T�[�r�X
        Services.registerService(WEB3AdminDirSecAccRegVoucherStatUpdService.class,
            new WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl());

        //�Ǘ��ғd�q����Q�t���O�X�V�T�[�r�X
        Services.registerService(WEB3AdminDirSecBatoTroubleFlagUpdateService.class,
            new WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl());

        //�g���K�[���s�����T�[�r�X
        Services.registerService(WEB3AdminDirSecTriggerIssueService.class,
            new WEB3AdminDirSecTriggerIssueServiceImpl());
        
        //�Ǘ��҉��菈�������N���T�[�r�X
        Services.registerService(WEB3AdminDirSecAPMngForcedStartService.class,
            new WEB3AdminDirSecAPMngForcedStartServiceImpl());

        // Service �� Interceptor �ݒ菈�� ----------------------
        // �����g�����U�N�V�����ݒ� 

        //�Ǘ��҃L���[�e�[�u���X�e�[�^�X�X�V�T�[�r�X
        Services.addInterceptor(
            WEB3AdminDirSecHostTableStatusUpdateService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��҃f�[�����g���K�[�e�[�u���X�e�[�^�X�X�V�T�[�r�X
        Services.addInterceptor(WEB3AdminDirSecDaemonTriggerTableUpdateService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �Ǘ��Ғ����P�ʃe�[�u��������ԍX�V�T�[�r�X
        Services.addInterceptor(WEB3AdminDirSecAioOrderUnitTableUpdateService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��҃A�b�v���[�h�e�[�u���I�������X�V�T�[�r�X
        Services.addInterceptor(WEB3AdminDirSecUploadTableEndDateUpdateService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �Ǘ��Ҕ����o�H�ؑ֏��������I���T�[�r�X
        Services.addInterceptor(WEB3FrontOrderRouteChangeFormSelectService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        // �Ǘ��Ҕ����o�H�ؑփT�[�r�X
        Services.addInterceptor(WEB3AdminFrontOrderRouteChangeService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
            
        // �Ǘ��Ҕ�����ؑփT�[�r�X
        Services.addInterceptor(WEB3AdminSwitchOrderRouteService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
            
        // �Ǘ��҃t�����g�����Ǘ����ʃT�[�r�X
        Services.addInterceptor(WEB3AdminDirSecFrontOrderCommonService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));  

        //�Ǘ��Ҍڋq���o�^�`�[�X�e�[�^�X�X�V�T�[�r�X
        Services.addInterceptor(WEB3AdminDirSecAccRegVoucherStatUpdService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //�Ǘ��ғd�q����Q�t���O�X�V�T�[�r�X
        Services.addInterceptor(WEB3AdminDirSecBatoTroubleFlagUpdateService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��҉��菈�������N���T�[�r�X
        Services.addInterceptor(WEB3AdminDirSecAPMngForcedStartService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // MQGateway�C���^�Z�v�^�̐ݒ�
        //�g���K�[���s�����T�[�r�X
        Services.addInterceptor(
            WEB3AdminDirSecTriggerIssueService.class,
            new WEB3MQGatewayInterceptor());

        // �Ǘ��Ҕ����o�H�ؑփT�[�r�X
        Services.addInterceptor(
            WEB3AdminFrontOrderRouteChangeService.class,
            new WEB3MQGatewayInterceptor());

        // Service.execute()�Ăяo���O���  ----------------------
        // �����J�n�����Ə����I�����������O�o�͂���悤�ɐݒ肷��

        //�Ǘ��҃L���[�e�[�u���X�e�[�^�X�X�V�T�[�r�X
        Services.addInterceptor(
            WEB3AdminDirSecHostTableStatusUpdateService.class, 
            new WEB3LogSysTimeInterceptor());

        //�Ǘ��҃f�[�����g���K�[�e�[�u���X�e�[�^�X�X�V�T�[�r�X
        Services.addInterceptor(
            WEB3AdminDirSecDaemonTriggerTableUpdateService.class, 
            new WEB3LogSysTimeInterceptor());

        // �Ǘ��Ғ����P�ʃe�[�u��������ԍX�V�T�[�r�X
        Services.addInterceptor(
            WEB3AdminDirSecAioOrderUnitTableUpdateService.class, 
            new WEB3LogSysTimeInterceptor());

        //�Ǘ��҃A�b�v���[�h�e�[�u���I�������X�V�T�[�r�X
        Services.addInterceptor(
            WEB3AdminDirSecUploadTableEndDateUpdateService.class, 
            new WEB3LogSysTimeInterceptor());

        // �Ǘ��Ҕ����o�H�ؑ֏��������I���T�[�r�X
        Services.addInterceptor(WEB3FrontOrderRouteChangeFormSelectService.class,
            new WEB3LogSysTimeInterceptor());

        // �Ǘ��Ҕ����o�H�ؑփT�[�r�X
        Services.addInterceptor(WEB3AdminFrontOrderRouteChangeService.class,
            new WEB3LogSysTimeInterceptor());
            
        // �Ǘ��҃t�����g�����Ǘ����ʃT�[�r�X
        Services.addInterceptor(WEB3AdminDirSecFrontOrderCommonService.class,
            new WEB3LogSysTimeInterceptor());

        // �Ǘ��Ҕ�����ؑփT�[�r�X
        Services.addInterceptor(WEB3AdminSwitchOrderRouteService.class,
            new WEB3AdminDirSecFrontServiceInterceptor());

        //�Ǘ��Ҍڋq���o�^�`�[�X�e�[�^�X�X�V�T�[�r�X
        Services.addInterceptor(WEB3AdminDirSecAccRegVoucherStatUpdService.class,
            new WEB3LogSysTimeInterceptor());

        //�Ǘ��ғd�q����Q�t���O�X�V�T�[�r�X
        Services.addInterceptor(WEB3AdminDirSecBatoTroubleFlagUpdateService.class,
            new WEB3LogSysTimeInterceptor());

        //�g���K�[���s�����T�[�r�X
        Services.addInterceptor(WEB3AdminDirSecTriggerIssueService.class,
            new WEB3LogSysTimeInterceptor());

        //�Ǘ��҉��菈�������N���T�[�r�X
        Services.addInterceptor(WEB3AdminDirSecAPMngForcedStartService.class,
            new WEB3LogSysTimeInterceptor());

        // Message �̓o�^���� ----------------------

        //�Ǘ��ҁE�L���[�e�[�u���ꗗ���N�G�X�g
        regClass(WEB3AdminDirSecHostTableReferenceRequest.class);

        //�Ǘ��ҁE�L���[�e�[�u���ꗗ���X�|���X 
        regClass(WEB3AdminDirSecHostTableReferenceResponse.class);

        //�Ǘ��ҁE�L���[�e�[�u���������̓��N�G�X�g
        regClass(WEB3AdminDirSecHostTableSearchInputRequest.class);

        //�Ǘ��ҁE�L���[�e�[�u���������̓��X�|���X
        regClass(WEB3AdminDirSecHostTableSearchInputResponse.class);

        //�Ǘ��ҁE�L���[�e�[�u���������ʃ��N�G�X�g
        regClass(WEB3AdminDirSecHostTableSearchListRequest.class);

        //�Ǘ��ҁE�L���[�e�[�u���������ʃ��X�|���X
        regClass(WEB3AdminDirSecHostTableSearchListResponse.class);

        //�Ǘ��ҁE�L���[�e�[�u���X�e�[�^�X�X�V�m�F���N�G�X�g 
        regClass(WEB3AdminDirSecHostTableStatusConfirmRequest.class);

        //�Ǘ��ҁE�L���[�e�[�u���X�e�[�^�X�X�V�m�F���X�|���X
        regClass(WEB3AdminDirSecHostTableStatusConfirmResponse.class);

        //�Ǘ��ҁE�L���[�e�[�u���X�e�[�^�X�X�V�������N�G�X�g
        regClass(WEB3AdminDirSecHostTableStatusCompleteRequest.class);

        //�Ǘ��ҁE�L���[�e�[�u���X�e�[�^�X�X�V�������X�|���X
        regClass(WEB3AdminDirSecHostTableStatusCompleteResponse.class);

        //�Ǘ��ҁE�f�[�����g���K�[�e�[�u���������̓��N�G�X�g
        regClass(WEB3AdminDirSecDaemonTriggerTableSearchInputRequest.class);

        //�Ǘ��ҁE�f�[�����g���K�[�e�[�u���������̓��X�|���X
        regClass(WEB3AdminDirSecDaemonTriggerTableSearchInputRequest.class);

        //�Ǘ��ҁE�f�[�����g���K�[�e�[�u���������ʃ��N�G�X�g
        regClass(WEB3AdminDirSecDaemonTriggerTableSearchResultRequest.class);

        //�Ǘ��ҁE�f�[�����g���K�[�e�[�u���������ʃ��X�|���X
        regClass(WEB3AdminDirSecDaemonTriggerTableSearchResultRequest.class);

        //�Ǘ��ҁE�f�[�����g���K�[�e�[�u���X�e�[�^�X�X�V�m�F���N�G�X�g
        regClass(WEB3AdminDirSecDaemonTriggerTableUpdateConfirmRequest.class);

        //�Ǘ��ҁE�f�[�����g���K�[�e�[�u���X�e�[�^�X�X�V�m�F���X�|���X
        regClass(WEB3AdminDirSecDaemonTriggerTableUpdateConfirmRequest.class);

        //�Ǘ��ҁE�f�[�����g���K�[�e�[�u���X�e�[�^�X�X�V�������N�G�X�g
        regClass(WEB3AdminDirSecDaemonTriggerTableUpdateCompleteRequest.class);

        //�Ǘ��ҁE�f�[�����g���K�[�e�[�u���X�e�[�^�X�X�V�������X�|���X
        regClass(WEB3AdminDirSecDaemonTriggerTableUpdateCompleteRequest.class);

        //�����P�ʃe�[�u���������̓��N�G�X�g
        regClass(WEB3AdminDirSecAioOrderUnitTableSearchInputRequest.class);

        //�����P�ʃe�[�u���������̓��X�|���X
        regClass(WEB3AdminDirSecAioOrderUnitTableSearchInputRequest.class);

        //�����P�ʃe�[�u���������ʃ��N�G�X�g
        regClass(WEB3AdminDirSecAioOrderUnitTableSearchResultRequest.class);

        //�����P�ʃe�[�u���������ʃ��X�|���X
        regClass(WEB3AdminDirSecAioOrderUnitTableSearchResultRequest.class);

        //�����P�ʃe�[�u��������ԍX�V�m�F���N�G�X�g
        regClass(WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequest.class);

        //�����P�ʃe�[�u��������ԍX�V�m�F���X�|���X
        regClass(WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequest.class);

        //�����P�ʃe�[�u��������ԍX�V�������N�G�X�g
        regClass(WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequest.class);

        //�����P�ʃe�[�u��������ԍX�V�������X�|���X
        regClass(WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequest.class);

        //�A�b�v���[�h�e�[�u�����R�[�h�ꗗ���N�G�X�g 
        regClass(WEB3AdminDirSecUploadTableListRequest.class);

        //�A�b�v���[�h�e�[�u�����R�[�h�ꗗ���X�|���X 
        regClass(WEB3AdminDirSecUploadTableListResponse.class);

        //�A�b�v���[�h�e�[�u���I�������X�V�m�F���N�G�X�g 
        regClass(WEB3AdminDirSecUploadTableUpdateConfirmRequest.class);

        //�A�b�v���[�h�e�[�u���I�������X�V�m�F���X�|���X 
        regClass(WEB3AdminDirSecUploadTableUpdateConfirmResponse.class);

        //�A�b�v���[�h�e�[�u���I�������X�V�������N�G�X�g 
        regClass(WEB3AdminDirSecUploadTableUpdateCompleteRequest.class);

        //�A�b�v���[�h�e�[�u���I�������X�V�������X�|���X 
        regClass(WEB3AdminDirSecUploadTableUpdateCompleteResponse.class);
        
        //�Ǘ��ҁE�g���K�[���s�����������N�G�X�g �@@
        regClass(WEB3AdminDirSecTriggerIssueCompleteRequest.class);

        //�Ǘ��ҁE�g���K�[���s�����������X�|���X 
        regClass(WEB3AdminDirSecTriggerIssueCompleteResponse.class);

        //�Ǘ��ҁE�g���K�[���s�����m�F���N�G�X�g 
        regClass(WEB3AdminDirSecTriggerIssueConfirmRequest.class);

         //�Ǘ��ҁE�g���K�[���s�����m�F���X�|���X �@@
        regClass(WEB3AdminDirSecTriggerIssueConfirmResponse.class);

        //�Ǘ��ҁE�g���K�[���s�������̓��N�G�X�g �@@
        regClass(WEB3AdminDirSecTriggerIssueInputRequest.class);

        //�Ǘ��ҁE�g���K�[���s�������̓��X�|���X �@@
        regClass(WEB3AdminDirSecTriggerIssueInputResponse.class);

        //�Ǘ��ҁE�g���K�[���s�����ꗗ���N�G�X�g 
         regClass(WEB3AdminDirSecTriggerIssueListRequest.class);

        //�Ǘ��ҁE�g���K�[���s�����ꗗ���X�|���X �@@
        regClass(WEB3AdminDirSecTriggerIssueListResponse.class);

        // �Ǘ��ҁE�����o�H�ؑ֏����I�����N�G�X�g
        regClass(WEB3AdminFrontChangeProcessSelectRequest.class);
        regClass(WEB3AdminFrontChangeProcessSelectResponse.class);

        // �Ǘ��ҁE�����o�H�֑ؑI�����N�G�X�g�E���X�|���X
        regClass(WEB3AdminFrontRouteChangeSelectRequest.class);
        regClass(WEB3AdminFrontRouteChangeSelectResponse.class);

        // �Ǘ��ҁE�����o�H�ؑ֊m�F���N�G�X�g�E���X�|���X
        regClass(WEB3AdminFrontRouteChangeConfirmRequest.class);
        regClass(WEB3AdminFrontRouteChangeConfirmResponse.class);

        // �Ǘ��ҁE�����o�H�ؑ֊������N�G�X�g�E���X�|���X
        regClass(WEB3AdminFrontRouteChangeCompleteRequest.class);
        regClass(WEB3AdminFrontRouteChangeCompleteResponse.class);

        // �Ǘ��ҁE������֑ؑI�����N�G�X�g�E���X�|���X
        regClass(WEB3AdminSwitchOrderRouteSelectRequest.class);
        regClass(WEB3AdminSwitchOrderRouteSelectResponse.class);

        // �Ǘ��ҁE������ؑ֊m�F���N�G�X�g�E���X�|���X
        regClass(WEB3AdminSwitchOrderRouteConfirmRequest.class);
        regClass(WEB3AdminSwitchOrderRouteConfirmResponse.class);

        // �Ǘ��ҁE������ؑ֊������N�G�X�g�E���X�|���X
        regClass(WEB3AdminSwitchOrderRouteCompleteRequest.class);
        regClass(WEB3AdminSwitchOrderRouteCompleteResponse.class);

        //�Ǘ��ҁE�ڋq���o�^�`�[�������̓��N�G�X�g�E���X�|���X
        regClass(WEB3AdminDirSecAccRegVoucherSearchInpRequest.class);
        regClass(WEB3AdminDirSecAccRegVoucherSearchInpResponse.class);

        //�Ǘ��ҁE�ڋq���o�^�`�[�������ʃ��N�G�X�g�E���X�|���X
        regClass(WEB3AdminDirSecAccRegVoucherSearchResRequest.class);
        regClass(WEB3AdminDirSecAccRegVoucherSearchResResponse.class);

        //�Ǘ��ҁE�ڋq���o�^�`�[�X�e�[�^�X�X�V�������N�G�X�g�E���X�|���X
        regClass(WEB3AdminDirSecAccRegVoucherStatUpdCompRequest.class);
        regClass(WEB3AdminDirSecAccRegVoucherStatUpdCompResponse.class);

        //�Ǘ��ҁE�ڋq���o�^�`�[�X�e�[�^�X�X�V�m�F���N�G�X�g�E���X�|���X
        regClass(WEB3AdminDirSecAccRegVoucherStatUpdConfRequest.class);
        regClass(WEB3AdminDirSecAccRegVoucherStatUpdConfResponse.class);

        //�Ǘ��ҁE�ғ��󋵈ꗗ���N�G�X�g
        regClass(WEB3AdminDirSecWorkingListRequest.class);
        //�Ǘ��ҁE�ғ��󋵈ꗗ���X�|���X
        regClass(WEB3AdminDirSecWorkingListResponse.class);

        //�Ǘ��ҁE�ғ��󋵕ύX�m�F���N�G�X�g
        regClass(WEB3AdminDirSecWorkingConfirmRequest.class);
        //�Ǘ��ҁE�ғ��󋵕ύX�m�F���X�|���X
        regClass(WEB3AdminDirSecWorkingConfirmResponse.class);

        //�Ǘ��ҁE�ғ��󋵕ύX�������N�G�X�g
        regClass(WEB3AdminDirSecWorkingCompleteRequest.class);
        //�Ǘ��ҁE�ғ��󋵕ύX�������X�|���X
        regClass(WEB3AdminDirSecWorkingCompleteResponse.class);

        //�Ǘ��ҁE���菈�������N���������N�G�X�g
        regClass(WEB3AdminDirSecAPMngForcedStartCmpRequest.class);
        //�Ǘ��ҁE���菈�������N���������X�|���X
        regClass(WEB3AdminDirSecAPMngForcedStartCmpResponse.class);
        
        //�Ǘ��ҁE���菈�������N���m�F���N�G�X�g
        regClass(WEB3AdminDirSecAPMngForcedStartCnfRequest.class);
        //�Ǘ��ҁE���菈�������N���m�F���X�|���X
        regClass(WEB3AdminDirSecAPMngForcedStartCnfResponse.class);

        //�Ǘ��ҁE���菈�������N�����̓��N�G�X�g
        regClass(WEB3AdminDirSecAPMngForcedStartInpRequest.class);
        //�Ǘ��ҁE���菈�������N�����̓��X�|���X
        regClass(WEB3AdminDirSecAPMngForcedStartInpResponse.class);

        //�Ǘ��ҁE���菈���ꗗ���N�G�X�g
        regClass(WEB3AdminDirSecAPMngListRequest.class);
        //�Ǘ��ҁE���菈���ꗗ���X�|���X
        regClass(WEB3AdminDirSecAPMngListResponse.class);

        //
        //Handler �̓o�^���� ----------------------

        //�Ǘ��҃L���[�e�[�u���X�e�[�^�X�X�V�n���h���̓o�^
        regHandler(
            WEB3AdminDirSecAppPlugin.class,    
            WEB3AdminDirSecHostTableReferenceRequest.class, 
            WEB3AdminDirSecHostTableStatusUpdateHandler.class, 
            "getHostTableList");

        //�Ǘ��҃L���[�e�[�u���X�e�[�^�X�X�V�n���h���̓o�^        
        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminDirSecHostTableSearchInputRequest.class, 
            WEB3AdminDirSecHostTableStatusUpdateHandler.class, 
            "getQueryConditionInputScreen");

        //�Ǘ��҃L���[�e�[�u���X�e�[�^�X�X�V�n���h���̓o�^        
        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminDirSecHostTableSearchListRequest.class, 
            WEB3AdminDirSecHostTableStatusUpdateHandler.class, 
            "getQueryResultList");

        //�Ǘ��҃L���[�e�[�u���X�e�[�^�X�X�V�n���h���̓o�^        
        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminDirSecHostTableStatusConfirmRequest.class, 
            WEB3AdminDirSecHostTableStatusUpdateHandler.class, 
            "getUpdateConfirmScreen");

        //�Ǘ��҃L���[�e�[�u���X�e�[�^�X�X�V�n���h���̓o�^        
        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminDirSecHostTableStatusCompleteRequest.class, 
            WEB3AdminDirSecHostTableStatusUpdateHandler.class, 
            "getUpdateCompleteScreen");

        //�Ǘ��҃f�[�����g���K�[�e�[�u���X�e�[�^�X�X�V�n���h��
        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminDirSecDaemonTriggerTableSearchInputRequest.class, 
            WEB3AdminDirSecDaemonTriggerTableUpdateHandler.class, 
            "searchConditionInput");

        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminDirSecDaemonTriggerTableSearchResultRequest.class, 
            WEB3AdminDirSecDaemonTriggerTableUpdateHandler.class, 
            "searchResult");

        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminDirSecDaemonTriggerTableUpdateConfirmRequest.class, 
            WEB3AdminDirSecDaemonTriggerTableUpdateHandler.class, 
            "updateConfirm");
        
        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminDirSecDaemonTriggerTableUpdateCompleteRequest.class, 
            WEB3AdminDirSecDaemonTriggerTableUpdateHandler.class, 
            "updateComplete");
        
        //�Ǘ��Ғ����P�ʃe�[�u��������ԍX�V�n���h��
        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminDirSecAioOrderUnitTableSearchInputRequest.class, 
            WEB3AdminDirSecAioOrderUnitTableUpdateHandler.class, 
            "getSearchScreen");
        
        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminDirSecAioOrderUnitTableSearchResultRequest.class, 
            WEB3AdminDirSecAioOrderUnitTableUpdateHandler.class, 
            "getSearchResultScreen");
        
        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequest.class, 
            WEB3AdminDirSecAioOrderUnitTableUpdateHandler.class, 
            "getUpdateConfirmScreen");
        
        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequest.class, 
            WEB3AdminDirSecAioOrderUnitTableUpdateHandler.class, 
            "getUpdateCompleteScreen");
        
        //�Ǘ��҃A�b�v���[�h�e�[�u���I�������X�V�n���h��
        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminDirSecUploadTableListRequest.class, 
            WEB3AdminDirSecUploadTableEndDateUpdateHandler.class, 
            "getListScreen");
        
        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminDirSecUploadTableUpdateConfirmRequest.class, 
            WEB3AdminDirSecUploadTableEndDateUpdateHandler.class, 
            "getUpdateConfirmScreen");
        
        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminDirSecUploadTableUpdateCompleteRequest.class, 
            WEB3AdminDirSecUploadTableEndDateUpdateHandler.class, 
            "getUpdateCompleteScreen");

        // �Ǘ��Ҕ����o�H�ؑ֕����I���n���h��
        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminFrontChangeProcessSelectRequest.class,
            WEB3FrontOrderRouteChangeFormSelectHandler.class,
            "getSelectScreen");
            
        // �Ǘ��Ҕ����o�H�ؑփn���h��
        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminFrontRouteChangeSelectRequest.class,
            WEB3AdminFrontOrderRouteChangeHandler.class,
            "getSelectScreen");
            
        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminFrontRouteChangeConfirmRequest.class,
            WEB3AdminFrontOrderRouteChangeHandler.class,
            "validateChange");

        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminFrontRouteChangeCompleteRequest.class,
            WEB3AdminFrontOrderRouteChangeHandler.class,
            "submitChange");

        // �Ǘ��Ҕ�����ؑփn���h��
        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminSwitchOrderRouteSelectRequest.class,
            WEB3AdminFrontSwitchOrderRouteHandler.class,
            "getSelectScreen");
            
        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminSwitchOrderRouteConfirmRequest.class,
            WEB3AdminFrontSwitchOrderRouteHandler.class,
            "validateOrderRouteChange");

        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminSwitchOrderRouteCompleteRequest.class,
            WEB3AdminFrontSwitchOrderRouteHandler.class,
            "submitOrderRouteChange");

        //�Ǘ��Ҍڋq���o�^�`�[�X�e�[�^�X�X�V�n���h��
        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminDirSecAccRegVoucherSearchInpRequest.class,
            WEB3AdminDirSecAccRegVoucherStatUpdHandler.class,
            "getSearchScreen");

        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminDirSecAccRegVoucherSearchResRequest.class,
            WEB3AdminDirSecAccRegVoucherStatUpdHandler.class,
            "getSearchResultScreen");

        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminDirSecAccRegVoucherStatUpdConfRequest.class,
            WEB3AdminDirSecAccRegVoucherStatUpdHandler.class,
            "getUpdateConfirmScreen");

        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminDirSecAccRegVoucherStatUpdCompRequest.class,
            WEB3AdminDirSecAccRegVoucherStatUpdHandler.class,
            "getUpdateCompleteScreen");

        regHandler(
            WEB3AdminDirSecAppPlugin.class,    
            WEB3AdminDirSecWorkingListRequest.class, 
            WEB3AdminDirSecBatoTroubleFlagUpdateHandler.class, 
            "getListScreen");

        regHandler(
            WEB3AdminDirSecAppPlugin.class,    
            WEB3AdminDirSecWorkingConfirmRequest.class, 
            WEB3AdminDirSecBatoTroubleFlagUpdateHandler.class, 
            "validateChangeConfirmScreen");

        regHandler(
            WEB3AdminDirSecAppPlugin.class,    
            WEB3AdminDirSecWorkingCompleteRequest.class, 
            WEB3AdminDirSecBatoTroubleFlagUpdateHandler.class, 
            "submitChangeCompleteScreen");

        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminDirSecTriggerIssueListRequest.class,
            WEB3AdminDirSecTriggerIssueHandler.class,
            "getTriggerIssueListScreenDisplay");

        regHandler(
            WEB3AdminDirSecAppPlugin.class,    
            WEB3AdminDirSecTriggerIssueInputRequest.class,
            WEB3AdminDirSecTriggerIssueHandler.class, 
            "getTriggerIssueInputScreenDisplay");

        regHandler(
            WEB3AdminDirSecAppPlugin.class,    
            WEB3AdminDirSecTriggerIssueConfirmRequest.class, 
            WEB3AdminDirSecTriggerIssueHandler.class, 
            "validateTriggerIssueConfirmScreenDisplay");

        regHandler(
            WEB3AdminDirSecAppPlugin.class,    
            WEB3AdminDirSecTriggerIssueCompleteRequest.class, 
            WEB3AdminDirSecTriggerIssueHandler.class, 
            "submitTriggerIssueCompleteScreenDisplay");

        //�Ǘ��҉��菈�������N���n���h��
        regHandler(
                WEB3AdminDirSecAppPlugin.class,    
                WEB3AdminDirSecAPMngListRequest.class, 
                WEB3AdminDirSecAPMngForcedStartHandler.class, 
                "getAPMngList");

        regHandler(
                WEB3AdminDirSecAppPlugin.class,    
                WEB3AdminDirSecAPMngForcedStartInpRequest.class, 
                WEB3AdminDirSecAPMngForcedStartHandler.class, 
                "getAPMngForcedStartInp");

        regHandler(
                WEB3AdminDirSecAppPlugin.class,    
                WEB3AdminDirSecAPMngForcedStartCnfRequest.class, 
                WEB3AdminDirSecAPMngForcedStartHandler.class, 
                "validateAPMngForcedStartCnf");

        regHandler(
                WEB3AdminDirSecAppPlugin.class,    
                WEB3AdminDirSecAPMngForcedStartCmpRequest.class, 
                WEB3AdminDirSecAPMngForcedStartHandler.class, 
                "submitAPMngForcedStartCmp");

        log.exiting(STR_METHOD_NAME);
    }
}
@
