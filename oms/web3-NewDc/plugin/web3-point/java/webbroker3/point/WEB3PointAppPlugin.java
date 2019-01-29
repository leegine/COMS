head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.01.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : Webbroker3-�|�C���g�V�X�e�� �v���O�C��(WEB3PointAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/13 ���o�� (���u) �V�K�쐬
*/
package webbroker3.point;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.point.data.WEB3PointMasterDatabaseExtensions;
import webbroker3.point.handler.WEB3AdminPointCategoryChangeHandler;
import webbroker3.point.handler.WEB3AdminPointCategoryDeleteHandler;
import webbroker3.point.handler.WEB3AdminPointCategoryReferenceHandler;
import webbroker3.point.handler.WEB3AdminPointCategoryRegistHandler;
import webbroker3.point.handler.WEB3AdminPointExchangeApplyAcceptHandler;
import webbroker3.point.handler.WEB3AdminPointManageByCustomerHandler;
import webbroker3.point.handler.WEB3AdminPointPackageAdjustHandler;
import webbroker3.point.handler.WEB3AdminPointPremiumChangeHandler;
import webbroker3.point.handler.WEB3AdminPointPremiumDeleteHandler;
import webbroker3.point.handler.WEB3AdminPointPremiumReferenceHandler;
import webbroker3.point.handler.WEB3AdminPointPremiumRegistHandler;
import webbroker3.point.handler.WEB3AdminPointTradeBonusPlanReferenceHandler;
import webbroker3.point.handler.WEB3PointCommissionInfoReferenceHandler;
import webbroker3.point.handler.WEB3PointExchangeApplyHandler;
import webbroker3.point.handler.WEB3PointTradeBonusPlanReferenceHandler;
import webbroker3.point.message.WEB3AdminPointAdjustCompleteRequest;
import webbroker3.point.message.WEB3AdminPointAdjustCompleteResponse;
import webbroker3.point.message.WEB3AdminPointAdjustConfirmRequest;
import webbroker3.point.message.WEB3AdminPointAdjustConfirmResponse;
import webbroker3.point.message.WEB3AdminPointAdjustInputRequest;
import webbroker3.point.message.WEB3AdminPointAdjustInputResponse;
import webbroker3.point.message.WEB3AdminPointCategoryChangeCommonRequest;
import webbroker3.point.message.WEB3AdminPointCategoryChangeCompleteRequest;
import webbroker3.point.message.WEB3AdminPointCategoryChangeCompleteResponse;
import webbroker3.point.message.WEB3AdminPointCategoryChangeConfirmRequest;
import webbroker3.point.message.WEB3AdminPointCategoryChangeConfirmResponse;
import webbroker3.point.message.WEB3AdminPointCategoryChangeInputRequest;
import webbroker3.point.message.WEB3AdminPointCategoryChangeInputResponse;
import webbroker3.point.message.WEB3AdminPointCategoryDeleteCompleteRequest;
import webbroker3.point.message.WEB3AdminPointCategoryDeleteCompleteResponse;
import webbroker3.point.message.WEB3AdminPointCategoryDeleteConfirmRequest;
import webbroker3.point.message.WEB3AdminPointCategoryDeleteConfirmResponse;
import webbroker3.point.message.WEB3AdminPointCategoryReferenceRequest;
import webbroker3.point.message.WEB3AdminPointCategoryReferenceResponse;
import webbroker3.point.message.WEB3AdminPointCategoryRegistCommonRequest;
import webbroker3.point.message.WEB3AdminPointCategoryRegistCompleteRequest;
import webbroker3.point.message.WEB3AdminPointCategoryRegistCompleteResponse;
import webbroker3.point.message.WEB3AdminPointCategoryRegistConfirmRequest;
import webbroker3.point.message.WEB3AdminPointCategoryRegistConfirmResponse;
import webbroker3.point.message.WEB3AdminPointCategoryRegistInputRequest;
import webbroker3.point.message.WEB3AdminPointCategoryRegistInputResponse;
import webbroker3.point.message.WEB3AdminPointExchangeAcceptRequest;
import webbroker3.point.message.WEB3AdminPointExchangeAcceptResponse;
import webbroker3.point.message.WEB3AdminPointExchangeCancelCompleteRequest;
import webbroker3.point.message.WEB3AdminPointExchangeCancelCompleteResponse;
import webbroker3.point.message.WEB3AdminPointExchangeCancelConfirmRequest;
import webbroker3.point.message.WEB3AdminPointExchangeCancelConfirmResponse;
import webbroker3.point.message.WEB3AdminPointExchangeCancelReleaseCompleteRequest;
import webbroker3.point.message.WEB3AdminPointExchangeCancelReleaseCompleteResponse;
import webbroker3.point.message.WEB3AdminPointExchangeCancelReleaseConfirmRequest;
import webbroker3.point.message.WEB3AdminPointExchangeCancelReleaseConfirmResponse;
import webbroker3.point.message.WEB3AdminPointExchangeCommonRequest;
import webbroker3.point.message.WEB3AdminPointExchangeCompleteCommonRequest;
import webbroker3.point.message.WEB3AdminPointExchangeStateReferenceRequest;
import webbroker3.point.message.WEB3AdminPointExchangeStateReferenceResponse;
import webbroker3.point.message.WEB3AdminPointHistoryReferenceRequest;
import webbroker3.point.message.WEB3AdminPointHistoryReferenceResponse;
import webbroker3.point.message.WEB3AdminPointManageCommonRequest;
import webbroker3.point.message.WEB3AdminPointManageDisplayRequest;
import webbroker3.point.message.WEB3AdminPointManageDisplayResponse;
import webbroker3.point.message.WEB3AdminPointPremiumChangeCommonRequest;
import webbroker3.point.message.WEB3AdminPointPremiumChangeCompleteRequest;
import webbroker3.point.message.WEB3AdminPointPremiumChangeCompleteResponse;
import webbroker3.point.message.WEB3AdminPointPremiumChangeConfirmRequest;
import webbroker3.point.message.WEB3AdminPointPremiumChangeConfirmResponse;
import webbroker3.point.message.WEB3AdminPointPremiumChangeInputRequest;
import webbroker3.point.message.WEB3AdminPointPremiumChangeInputResponse;
import webbroker3.point.message.WEB3AdminPointPremiumDeleteCompleteRequest;
import webbroker3.point.message.WEB3AdminPointPremiumDeleteCompleteResponse;
import webbroker3.point.message.WEB3AdminPointPremiumDeleteConfirmRequest;
import webbroker3.point.message.WEB3AdminPointPremiumDeleteConfirmResponse;
import webbroker3.point.message.WEB3AdminPointPremiumReferenceRequest;
import webbroker3.point.message.WEB3AdminPointPremiumReferenceResponse;
import webbroker3.point.message.WEB3AdminPointPremiumRegistCommonRequest;
import webbroker3.point.message.WEB3AdminPointPremiumRegistCompleteRequest;
import webbroker3.point.message.WEB3AdminPointPremiumRegistCompleteResponse;
import webbroker3.point.message.WEB3AdminPointPremiumRegistConfirmRequest;
import webbroker3.point.message.WEB3AdminPointPremiumRegistConfirmResponse;
import webbroker3.point.message.WEB3AdminPointPremiumRegistInputRequest;
import webbroker3.point.message.WEB3AdminPointPremiumRegistInputResponse;
import webbroker3.point.message.WEB3AdminPointTradeBonusPlanReferenceRequest;
import webbroker3.point.message.WEB3AdminPointTradeBonusPlanReferenceResponse;
import webbroker3.point.message.WEB3AdminPointUploadCancelRequest;
import webbroker3.point.message.WEB3AdminPointUploadCancelResponse;
import webbroker3.point.message.WEB3AdminPointUploadCompleteRequest;
import webbroker3.point.message.WEB3AdminPointUploadCompleteResponse;
import webbroker3.point.message.WEB3AdminPointUploadConfirmRequest;
import webbroker3.point.message.WEB3AdminPointUploadConfirmResponse;
import webbroker3.point.message.WEB3AdminPointUploadInputRequest;
import webbroker3.point.message.WEB3AdminPointUploadInputResponse;
import webbroker3.point.message.WEB3PointApplyCommonRequest;
import webbroker3.point.message.WEB3PointApplyCompleteRequest;
import webbroker3.point.message.WEB3PointApplyCompleteResponse;
import webbroker3.point.message.WEB3PointApplyConfirmRequest;
import webbroker3.point.message.WEB3PointApplyConfirmResponse;
import webbroker3.point.message.WEB3PointApplyInputRequest;
import webbroker3.point.message.WEB3PointApplyInputResponse;
import webbroker3.point.message.WEB3PointApplyReferenceRequest;
import webbroker3.point.message.WEB3PointApplyReferenceResponse;
import webbroker3.point.message.WEB3PointCommissionInfoReferenceRequest;
import webbroker3.point.message.WEB3PointCommissionInfoReferenceResponse;
import webbroker3.point.message.WEB3PointTradeBonusPlanReferenceRequest;
import webbroker3.point.message.WEB3PointTradeBonusPlanReferenceResponse;
import webbroker3.point.service.delegate.WEB3AdminPointCategoryChangeService;
import webbroker3.point.service.delegate.WEB3AdminPointCategoryDeleteService;
import webbroker3.point.service.delegate.WEB3AdminPointCategoryReferenceService;
import webbroker3.point.service.delegate.WEB3AdminPointCategoryRegistService;
import webbroker3.point.service.delegate.WEB3AdminPointExchangeApplyAcceptService;
import webbroker3.point.service.delegate.WEB3AdminPointManageByCustomerService;
import webbroker3.point.service.delegate.WEB3AdminPointPackageAdjustService;
import webbroker3.point.service.delegate.WEB3AdminPointPremiumChangeService;
import webbroker3.point.service.delegate.WEB3AdminPointPremiumDeleteService;
import webbroker3.point.service.delegate.WEB3AdminPointPremiumReferenceService;
import webbroker3.point.service.delegate.WEB3AdminPointPremiumRegistService;
import webbroker3.point.service.delegate.WEB3AdminPointTradeBonusPlanReferenceService;
import webbroker3.point.service.delegate.WEB3PointCommissionInfoReferenceService;
import webbroker3.point.service.delegate.WEB3PointExchangeApplyService;
import webbroker3.point.service.delegate.WEB3PointTradeBonusPlanReferenceService;
import webbroker3.point.service.delegate.stdimpls.WEB3AdminPointCategoryChangeServiceImpl;
import webbroker3.point.service.delegate.stdimpls.WEB3AdminPointCategoryDeleteServiceImpl;
import webbroker3.point.service.delegate.stdimpls.WEB3AdminPointCategoryReferenceServiceImpl;
import webbroker3.point.service.delegate.stdimpls.WEB3AdminPointCategoryRegistServiceImpl;
import webbroker3.point.service.delegate.stdimpls.WEB3AdminPointExchangeApplyAcceptServiceImpl;
import webbroker3.point.service.delegate.stdimpls.WEB3AdminPointManageByCustomerServiceImpl;
import webbroker3.point.service.delegate.stdimpls.WEB3AdminPointPackageAdjustServiceImpl;
import webbroker3.point.service.delegate.stdimpls.WEB3AdminPointPremiumChangeServiceImpl;
import webbroker3.point.service.delegate.stdimpls.WEB3AdminPointPremiumDeleteServiceImpl;
import webbroker3.point.service.delegate.stdimpls.WEB3AdminPointPremiumReferenceServiceImpl;
import webbroker3.point.service.delegate.stdimpls.WEB3AdminPointPremiumRegistServiceImpl;
import webbroker3.point.service.delegate.stdimpls.WEB3AdminPointTradeBonusPlanReferenceServiceImpl;
import webbroker3.point.service.delegate.stdimpls.WEB3PointCommissionInfoReferenceServiceImpl;
import webbroker3.point.service.delegate.stdimpls.WEB3PointExchangeApplyServiceImpl;
import webbroker3.point.service.delegate.stdimpls.WEB3PointTradeBonusPlanReferenceServiceImpl;
import webbroker3.util.WEB3LogUtility;

/**
 * Webbroker3-�|�C���g�V�X�e�� �v���O�C���N���X�B
 *                                                                
 * @@author ���o��
 * @@version 1.0
 */
public final class WEB3PointAppPlugin extends Plugin
{
    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3PointAppPlugin.class);
    
    /**
     * �R���X�g���N�^�B
     */
    public WEB3PointAppPlugin()
    {
        String STR_METHOD_NAME = " WEB3PointAppPlugin()";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �v���O�C���G���g���[�|�C���g�B
     */
    public static void plug() throws Exception
    {
        String STR_METHOD_NAME = " plug()";
        log.entering(STR_METHOD_NAME);

        plug(WEB3PointAppPlugin.class);

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

        // DatabaseExtensions �̃v���O�C������ ----------------------
        WEB3PointMasterDatabaseExtensions.plug();

        // Service �̓o�^���� ----------------------
        //�|�C���g���i�}�l�[�W��
        Services.registerService(
            WEB3PointProductManager.class, 
            new WEB3PointProductManagerImpl());
        
        //�|�C���g�\���}�l�[�W��
        Services.registerService(
            WEB3PointApplyManager.class,
            new WEB3PointApplyManagerImpl());
        
        //�|�C���g�����\���T�[�r�X
        Services.registerService(
            WEB3PointExchangeApplyService.class,
            new WEB3PointExchangeApplyServiceImpl());
        
        //�J�e�S���[�ꗗ�T�[�r�X
        Services.registerService(
            WEB3AdminPointCategoryReferenceService.class,
            new WEB3AdminPointCategoryReferenceServiceImpl());
        
        //�J�e�S���[�폜�T�[�r�X
        Services.registerService(
            WEB3AdminPointCategoryDeleteService.class,
            new WEB3AdminPointCategoryDeleteServiceImpl());
        
        //�J�e�S���[�����T�[�r�X
        Services.registerService(
            WEB3AdminPointCategoryChangeService.class,
            new WEB3AdminPointCategoryChangeServiceImpl());
        
        //�J�e�S���[�o�^�T�[�r�X
        Services.registerService(
            WEB3AdminPointCategoryRegistService.class, 
            new WEB3AdminPointCategoryRegistServiceImpl());
        
        //�|�C���g�ꊇ�����T�[�r�X
        Services.registerService(
            WEB3AdminPointPackageAdjustService.class, 
            new WEB3AdminPointPackageAdjustServiceImpl());
        
        //�|�C���g������t�T�[�r�X
        Services.registerService(
            WEB3AdminPointExchangeApplyAcceptService.class, 
            new WEB3AdminPointExchangeApplyAcceptServiceImpl());
        
        //�i�i�ꗗ�T�[�r�X
        Services.registerService(
            WEB3AdminPointPremiumReferenceService.class,
            new WEB3AdminPointPremiumReferenceServiceImpl());
        
        //�i�i�폜�T�[�r�X
        Services.registerService(
            WEB3AdminPointPremiumDeleteService.class,
            new WEB3AdminPointPremiumDeleteServiceImpl());
            
        //�i�i�����T�[�r�X
        Services.registerService(
            WEB3AdminPointPremiumChangeService.class,
            new WEB3AdminPointPremiumChangeServiceImpl());
            
        //�i�i�o�^�T�[�r�X
        Services.registerService(
            WEB3AdminPointPremiumRegistService.class,
            new WEB3AdminPointPremiumRegistServiceImpl());
            
        //�ڋq�ʃ|�C���g�Ǘ��T�[�r�X
        Services.registerService(
            WEB3AdminPointManageByCustomerService.class,
            new WEB3AdminPointManageByCustomerServiceImpl());                 
            
        //�����萔���������Ɖ�T�[�r�X
        Services.registerService(
            WEB3PointCommissionInfoReferenceService.class,
            new WEB3PointCommissionInfoReferenceServiceImpl());                 
            
        //�g���[�h�{�[�i�X�v�����Ɖ�T�[�r�X
        Services.registerService(
            WEB3PointTradeBonusPlanReferenceService.class,
            new WEB3PointTradeBonusPlanReferenceServiceImpl());                 
            
        //�Ǘ��҃g���[�h�{�[�i�X�v�����Ɖ�T�[�r�X
        Services.registerService(
            WEB3AdminPointTradeBonusPlanReferenceService.class,
            new WEB3AdminPointTradeBonusPlanReferenceServiceImpl()); 
        
        //Service �� Interceptor �ݒ菈�� ----------------------
        //�����g�����U�N�V�����ݒ�       
        //�|�C���g�����\���T�[�r�X
        Services.addInterceptor(
            WEB3PointExchangeApplyService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�J�e�S���[�ꗗ�T�[�r�X
        Services.addInterceptor(
            WEB3AdminPointCategoryReferenceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�J�e�S���[�폜�T�[�r�X
        Services.addInterceptor(
            WEB3AdminPointCategoryDeleteService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�J�e�S���[�����T�[�r�X
        Services.addInterceptor(
            WEB3AdminPointCategoryChangeService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�J�e�S���[�o�^�T�[�r�X
        Services.addInterceptor(
            WEB3AdminPointCategoryRegistService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�|�C���g�ꊇ�����T�[�r�X
        Services.addInterceptor(
            WEB3AdminPointPackageAdjustService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�|�C���g������t�T�[�r�X
        Services.addInterceptor(
            WEB3AdminPointExchangeApplyAcceptService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�i�i�ꗗ�T�[�r�X
        Services.addInterceptor(
            WEB3AdminPointPremiumReferenceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�i�i�폜�T�[�r�X
        Services.addInterceptor(
            WEB3AdminPointPremiumDeleteService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
            
        //�i�i�����T�[�r�X
        Services.addInterceptor(
            WEB3AdminPointPremiumChangeService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
            
        //�i�i�o�^�T�[�r�X
        Services.addInterceptor(
            WEB3AdminPointPremiumRegistService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
            
        //�ڋq�ʃ|�C���g�Ǘ��T�[�r�X
        Services.addInterceptor(
            WEB3AdminPointManageByCustomerService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));    
            
        //�����萔���������Ɖ�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3PointCommissionInfoReferenceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));    
            
        //�g���[�h�{�[�i�X�v�����Ɖ�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3PointTradeBonusPlanReferenceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));    
                    
        // Service.execute()�Ăяo���O���  ----------------------
        // �����J�n�����Ə����I�����������O�o�͂���悤�ɐݒ肷��
        Services.addInterceptor(
            WEB3PointExchangeApplyService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�J�e�S���[�ꗗ�T�[�r�X
        Services.addInterceptor(
            WEB3AdminPointCategoryReferenceService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�J�e�S���[�폜�T�[�r�X
        Services.addInterceptor(
            WEB3AdminPointCategoryDeleteService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�J�e�S���[�����T�[�r�X
        Services.addInterceptor(
            WEB3AdminPointCategoryChangeService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�J�e�S���[�o�^�T�[�r�X
        Services.addInterceptor(
            WEB3AdminPointCategoryRegistService.class, 
            new WEB3LogSysTimeInterceptor());
        
        //�|�C���g�ꊇ�����T�[�r�X
        Services.addInterceptor(
            WEB3AdminPointPackageAdjustService.class, 
            new WEB3LogSysTimeInterceptor());
        
        //�|�C���g������t�T�[�r�X
        Services.addInterceptor(
            WEB3AdminPointExchangeApplyAcceptService.class, 
            new WEB3LogSysTimeInterceptor());
        
        //�i�i�ꗗ�T�[�r�X
        Services.addInterceptor(
            WEB3AdminPointPremiumReferenceService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�i�i�폜�T�[�r�X
        Services.addInterceptor(
            WEB3AdminPointPremiumDeleteService.class,
            new WEB3LogSysTimeInterceptor());
            
        //�i�i�����T�[�r�X
        Services.addInterceptor(
            WEB3AdminPointPremiumChangeService.class,
            new WEB3LogSysTimeInterceptor());
            
        //�i�i�o�^�T�[�r�X
        Services.addInterceptor(
            WEB3AdminPointPremiumRegistService.class,
            new WEB3LogSysTimeInterceptor());
            
        //�ڋq�ʃ|�C���g�Ǘ��T�[�r�X
        Services.addInterceptor(
            WEB3AdminPointManageByCustomerService.class,
            new WEB3LogSysTimeInterceptor()); 
            
        //�����萔���������Ɖ�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3PointCommissionInfoReferenceService.class,
            new WEB3LogSysTimeInterceptor());
            
        //�g���[�h�{�[�i�X�v�����Ɖ�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3PointTradeBonusPlanReferenceService.class,
            new WEB3LogSysTimeInterceptor());
        
        
        // Service.execute()�Ăяo���O���  ----------------------
        // �����J�n�����Ə����I�����������O�o�͂���悤�ɐݒ肷��
        //�|�C���g�����\���T�[�r�X
        Services.addInterceptor(
            WEB3PointExchangeApplyService.class, 
            new WEB3PointExchangeApplyServiceInterceptor());
        
        //�|�C���g�ꊇ�����T�[�r�X
        Services.addInterceptor(
            WEB3AdminPointPackageAdjustService.class,
            new WEB3AdminPointPackageAdjustServiceInterceptor());
            
        //�����萔���������Ɖ�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3PointCommissionInfoReferenceService.class,
            new WEB3PointCommissionInfoReferenceServiceInterceptor());
            
        //�g���[�h�{�[�i�X�v�����Ɖ�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3PointTradeBonusPlanReferenceService.class,
            new WEB3PointTradeBonusPlanReferenceServiceInterceptor());
            
        // Message �̓o�^���� ----------------------
        //�|�C���g�T�[�r�X��ʃ��N�G�X�g
        regClass(WEB3PointApplyReferenceRequest.class);
        //�|�C���g�T�[�r�X��ʃ��X�|���X
        regClass(WEB3PointApplyReferenceResponse.class);
        
        //�|�C���g�\���m�F���N�G�X�g
        regClass(WEB3PointApplyConfirmRequest.class);
        //�|�C���g�\���m�F���X�|���X
        regClass(WEB3PointApplyConfirmResponse.class);
        
        //�|�C���g�\���������N�G�X�g
        regClass(WEB3PointApplyCompleteRequest.class);
        //�|�C���g�\���������X�|���X
        regClass(WEB3PointApplyCompleteResponse.class);
        
        //�|�C���g�\�����ʃ��N�G�X�g
        regClass(WEB3PointApplyCommonRequest.class);
        
        //�|�C���g�\���I�����N�G�X�g
        regClass(WEB3PointApplyInputRequest.class);
        //�|�C���g�\���I�����X�|���X
        regClass(WEB3PointApplyInputResponse.class);
        
        //�A�b�v���[�h�m�F���N�G�X�g
        regClass(WEB3AdminPointUploadConfirmRequest.class);
        //�A�b�v���[�h�m�F���X�|���X
        regClass(WEB3AdminPointUploadConfirmResponse.class);
        
        //�A�b�v���[�h�������N�G�X�g
        regClass(WEB3AdminPointUploadCompleteRequest.class);
        //�A�b�v���[�h�������X�|���X
        regClass(WEB3AdminPointUploadCompleteResponse.class);
        
        //�A�b�v���[�h���~���N�G�X�g
        regClass(WEB3AdminPointUploadCancelRequest.class);
        //�A�b�v���[�h���~���X�|���X
        regClass(WEB3AdminPointUploadCancelResponse.class);
        
        //�A�b�v���[�h���̓��N�G�X�g
        regClass(WEB3AdminPointUploadInputRequest.class);
        //�A�b�v���[�h���̓��X�|���X
        regClass(WEB3AdminPointUploadInputResponse.class);
        
        //�J�e�S���[�ꗗ���N�G�X�g
        regClass(WEB3AdminPointCategoryReferenceRequest.class);
        //�J�e�S���[�ꗗ���X�|���X
        regClass(WEB3AdminPointCategoryReferenceResponse.class);
        
        //�J�e�S���[�폜�m�F���N�G�X�g
        regClass(WEB3AdminPointCategoryDeleteConfirmRequest.class);
        //�J�e�S���[�폜�m�F���X�|���X
        regClass(WEB3AdminPointCategoryDeleteConfirmResponse.class);
        
        //�J�e�S���[�폜�������N�G�X�g
        regClass(WEB3AdminPointCategoryDeleteCompleteRequest.class);
        //�J�e�S���[�폜�������X�|���X
        regClass(WEB3AdminPointCategoryDeleteCompleteResponse.class);
        
        //�J�e�S���[�����m�F���N�G�X�g
        regClass(WEB3AdminPointCategoryChangeConfirmRequest.class);
        //�J�e�S���[�����m�F���X�|���X
        regClass(WEB3AdminPointCategoryChangeConfirmResponse.class);
        
        //�J�e�S���[�����������N�G�X�g
        regClass(WEB3AdminPointCategoryChangeCompleteRequest.class);
        //�J�e�S���[�����������X�|���X
        regClass(WEB3AdminPointCategoryChangeCompleteResponse.class);
        
        //�J�e�S���[�������ʃ��N�G�X�g
        regClass(WEB3AdminPointCategoryChangeCommonRequest.class);
        
        //�J�e�S���[�������̓��N�G�X�g
        regClass(WEB3AdminPointCategoryChangeInputRequest.class);
        //�J�e�S���[�������̓��X�|���X
        regClass(WEB3AdminPointCategoryChangeInputResponse.class);
        
        //�J�e�S���[�o�^�m�F���N�G�X�g
        regClass(WEB3AdminPointCategoryRegistConfirmRequest.class);
        //�J�e�S���[�o�^�m�F���X�|���X
        regClass(WEB3AdminPointCategoryRegistConfirmResponse.class);
        
        //�J�e�S���[�o�^�������N�G�X�g
        regClass(WEB3AdminPointCategoryRegistCompleteRequest.class);
        //�J�e�S���[�o�^�������X�|���X
        regClass(WEB3AdminPointCategoryRegistCompleteResponse.class);
        
        //�J�e�S���[�o�^���ʃ��N�G�X�g
        regClass(WEB3AdminPointCategoryRegistCommonRequest.class);
        
        //�J�e�S���[�o�^���̓��N�G�X�g
        regClass(WEB3AdminPointCategoryRegistInputRequest.class);
        //�J�e�S���[�o�^���̓��X�|���X
        regClass(WEB3AdminPointCategoryRegistInputResponse.class);
        
        //�|�C���g�Ǘ���ʃ��N�G�X�g
        regClass(WEB3AdminPointManageDisplayRequest.class);
        //�|�C���g�Ǘ���ʃ��X�|���X
        regClass(WEB3AdminPointManageDisplayResponse.class);
        
        //�|�C���g�Ǘ����ʃ��N�G�X�g
        regClass(WEB3AdminPointManageCommonRequest.class);
        
        //�|�C���g�����ꗗ���N�G�X�g
        regClass(WEB3AdminPointExchangeStateReferenceRequest.class);
        //�|�C���g�����ꗗ���X�|���X
        regClass(WEB3AdminPointExchangeStateReferenceResponse.class);
        
        //�|�C���g�����������ʃ��N�G�X�g
        regClass(WEB3AdminPointExchangeCompleteCommonRequest.class);
        
        //�|�C���g�������ʃ��N�G�X�g
        regClass(WEB3AdminPointExchangeCommonRequest.class);
        
        //�|�C���g������������m�F���N�G�X�g
        regClass(WEB3AdminPointExchangeCancelReleaseConfirmRequest.class);
        //�|�C���g������������m�F���X�|���X
        regClass(WEB3AdminPointExchangeCancelReleaseConfirmResponse.class);
        
        //�|�C���g������������������N�G�X�g
        regClass(WEB3AdminPointExchangeCancelReleaseCompleteRequest.class);
        //�|�C���g������������������X�|���X
        regClass(WEB3AdminPointExchangeCancelReleaseCompleteResponse.class);
        
        //�|�C���g��������m�F���N�G�X�g
        regClass(WEB3AdminPointExchangeCancelConfirmRequest.class);
        //�|�C���g��������m�F���X�|���X
        regClass(WEB3AdminPointExchangeCancelConfirmResponse.class);
        
        //�|�C���g��������������N�G�X�g
        regClass(WEB3AdminPointExchangeCancelCompleteRequest.class);
        //�|�C���g��������������X�|���X
        regClass(WEB3AdminPointExchangeCancelCompleteResponse.class);
        
        //�|�C���g������t���N�G�X�g
        regClass(WEB3AdminPointExchangeAcceptRequest.class);
        //�|�C���g������t���X�|���X
        regClass(WEB3AdminPointExchangeAcceptResponse.class);
        
        //�|�C���g�����m�F���N�G�X�g
        regClass(WEB3AdminPointAdjustConfirmRequest.class);
        //�|�C���g�����m�F���X�|���X
        regClass(WEB3AdminPointAdjustConfirmResponse.class);
        
        //�|�C���g�����������N�G�X�g
        regClass(WEB3AdminPointAdjustCompleteRequest.class);
        //�|�C���g�����������X�|���X
        regClass(WEB3AdminPointAdjustCompleteResponse.class);
        
        //�|�C���g�������̓��N�G�X�g
        regClass(WEB3AdminPointAdjustInputRequest.class);
        //�|�C���g�������̓��X�|���X
        regClass(WEB3AdminPointAdjustInputResponse.class);
        
        //�|�C���g�����Ɖ�N�G�X�g
        regClass(WEB3AdminPointHistoryReferenceRequest.class);
        //�|�C���g�����Ɖ�X�|���X
        regClass(WEB3AdminPointHistoryReferenceResponse.class);
        
        //�i�i�ꗗ���N�G�X�g
        regClass(WEB3AdminPointPremiumReferenceRequest.class);
        //�i�i�ꗗ���X�|���X
        regClass(WEB3AdminPointPremiumReferenceResponse.class);
        
        //�i�i�폜�m�F���N�G�X�g
        regClass(WEB3AdminPointPremiumDeleteConfirmRequest.class);
        //�i�i�폜�m�F���X�|���X
        regClass(WEB3AdminPointPremiumDeleteConfirmResponse.class);
        
        //�i�i�폜�������N�G�X�g
        regClass(WEB3AdminPointPremiumDeleteCompleteRequest.class);
        //�i�i�폜�������X�|���X
        regClass(WEB3AdminPointPremiumDeleteCompleteResponse.class);
        
        //�i�i�����m�F���N�G�X�g
        regClass(WEB3AdminPointPremiumChangeConfirmRequest.class);
        //�i�i�����m�F���X�|���X
        regClass(WEB3AdminPointPremiumChangeConfirmResponse.class);
        
        //�i�i�����������N�G�X�g
        regClass(WEB3AdminPointPremiumChangeCompleteRequest.class);
        //�i�i�����������X�|���X
        regClass(WEB3AdminPointPremiumChangeCompleteResponse.class);
        
        //�i�i�������ʃ��N�G�X�g
        regClass(WEB3AdminPointPremiumChangeCommonRequest.class);
        
        //�i�i�������̓��N�G�X�g
        regClass(WEB3AdminPointPremiumChangeInputRequest.class);
        //�i�i�������̓��X�|���X
        regClass(WEB3AdminPointPremiumChangeInputResponse.class);
        
        //�i�i�o�^�m�F���N�G�X�g
        regClass(WEB3AdminPointPremiumRegistConfirmRequest.class);
        //�i�i�o�^�m�F���X�|���X
        regClass(WEB3AdminPointPremiumRegistConfirmResponse.class);
        
        //�i�i�o�^�������N�G�X�g
        regClass(WEB3AdminPointPremiumRegistCompleteRequest.class);
        //�i�i�o�^�������X�|���X
        regClass(WEB3AdminPointPremiumRegistCompleteResponse.class);
        
        //�i�i�o�^���ʃ��N�G�X�g
        regClass(WEB3AdminPointPremiumRegistCommonRequest.class);
        
        //�i�i�o�^���̓��N�G�X�g
        regClass(WEB3AdminPointPremiumRegistInputRequest.class);
        //�i�i�o�^���̓��X�|���X
        regClass(WEB3AdminPointPremiumRegistInputResponse.class);
        
        //�g���[�h�{�[�i�X�v�����Ɖ�N�G�X�g  
        regClass(WEB3PointTradeBonusPlanReferenceRequest.class);
        //�g���[�h�{�[�i�X�v�����Ɖ�X�|���X  
        regClass(WEB3PointTradeBonusPlanReferenceResponse.class);
        
        //�����萔���������Ɖ�N�G�X�g    
        regClass(WEB3PointCommissionInfoReferenceRequest.class);
        //�����萔���������Ɖ�X�|���X    
        regClass(WEB3PointCommissionInfoReferenceResponse.class);

        //�Ǘ��҃g���[�h�{�[�i�X�v�����Ɖ�N�G�X�g
        regClass(WEB3AdminPointTradeBonusPlanReferenceRequest.class);
        //�Ǘ��҃g���[�h�{�[�i�X�v�����Ɖ�X�|���X
        regClass(WEB3AdminPointTradeBonusPlanReferenceResponse.class);
        
        //Handler �̓o�^���� ----------------------
        //�|�C���g�����\�� �p�n���h���[�̓o�^
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3PointApplyReferenceRequest.class,
            WEB3PointExchangeApplyHandler.class,
            "serviceScreenDisplay");
            
        //�|�C���g�����\�� �p�n���h���[�̓o�^
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3PointApplyInputRequest.class,
            WEB3PointExchangeApplyHandler.class,
            "selectScreenDisplay");
            
        //�|�C���g�����\�� �p�n���h���[�̓o�^
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3PointApplyConfirmRequest.class,
            WEB3PointExchangeApplyHandler.class,
            "applyConfirm");
            
        //�|�C���g�����\�� �p�n���h���[�̓o�^
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3PointApplyCompleteRequest.class,
            WEB3PointExchangeApplyHandler.class,
            "applyComplete");
            
        //�J�e�S���[�Ǘ� �p�n���h���[�̓o�^
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointCategoryReferenceRequest.class,
            WEB3AdminPointCategoryReferenceHandler.class,
            "categoryReferenceRequest");
            
        //�J�e�S���[�폜 �p�n���h���[�̓o�^
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointCategoryDeleteConfirmRequest.class,
            WEB3AdminPointCategoryDeleteHandler.class,
            "deleteConfirm");
            
        //�J�e�S���[�폜 �p�n���h���[�̓o�^
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointCategoryDeleteCompleteRequest.class,
            WEB3AdminPointCategoryDeleteHandler.class,
            "deleteComplete");
            
        //�J�e�S���[���� �p�n���h���[�̓o�^
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointCategoryChangeInputRequest.class,
            WEB3AdminPointCategoryChangeHandler.class,
            "inputScreenDisplay");
            
        //�J�e�S���[���� �p�n���h���[�̓o�^
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointCategoryChangeConfirmRequest.class,
            WEB3AdminPointCategoryChangeHandler.class,
            "changeConfirm");
            
        //�J�e�S���[���� �p�n���h���[�̓o�^
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointCategoryChangeCompleteRequest.class,
            WEB3AdminPointCategoryChangeHandler.class,
            "changeComplete");
            
        //�J�e�S���[�o�^ �p�n���h���[�̓o�^
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointCategoryRegistInputRequest.class,
            WEB3AdminPointCategoryRegistHandler.class,
            "inputScreenDisplay");
            
        //�J�e�S���[�o�^ �p�n���h���[�̓o�^
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointCategoryRegistConfirmRequest.class,
            WEB3AdminPointCategoryRegistHandler.class,
            "registConfirm");
            
        //�J�e�S���[�o�^ �p�n���h���[�̓o�^
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointCategoryRegistCompleteRequest.class,
            WEB3AdminPointCategoryRegistHandler.class,
            "registComplete");
            
        //�|�C���g�ꊇ���� �p�n���h���[�̓o�^
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointUploadInputRequest.class,
            WEB3AdminPointPackageAdjustHandler.class,
            "inputScreenDisplay");
            
        //�|�C���g�ꊇ���� �p�n���h���[�̓o�^
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointUploadConfirmRequest.class,
            WEB3AdminPointPackageAdjustHandler.class,
            "uploadConfirm");
            
        //�|�C���g�ꊇ���� �p�n���h���[�̓o�^
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointUploadCompleteRequest.class,
            WEB3AdminPointPackageAdjustHandler.class,
            "uploadComplete");
            
        //�|�C���g�ꊇ���� �p�n���h���[�̓o�^
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointUploadCancelRequest.class,
            WEB3AdminPointPackageAdjustHandler.class,
            "uploadStop");
            
        //�|�C���g�����\����t �p�n���h���[�̓o�^
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointExchangeStateReferenceRequest.class,
            WEB3AdminPointExchangeApplyAcceptHandler.class,
            "listScreenDisplay");
            
        //�|�C���g�����\����t �p�n���h���[�̓o�^
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointExchangeAcceptRequest.class,
            WEB3AdminPointExchangeApplyAcceptHandler.class,
            "acceptComplete");  
            
        //�|�C���g�����\����t �p�n���h���[�̓o�^
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointExchangeCancelConfirmRequest.class,
            WEB3AdminPointExchangeApplyAcceptHandler.class,
            "cancelConfirm");   
            
        //�|�C���g�����\����t �p�n���h���[�̓o�^
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointExchangeCancelCompleteRequest.class,
            WEB3AdminPointExchangeApplyAcceptHandler.class,
            "cancelComplete");  
            
        //�|�C���g�����\����t �p�n���h���[�̓o�^
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointExchangeCancelReleaseConfirmRequest.class,
            WEB3AdminPointExchangeApplyAcceptHandler.class,
            "cancelReleaseConfirm");    
            
        //�|�C���g�����\����t �p�n���h���[�̓o�^
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointExchangeCancelReleaseCompleteRequest.class,
            WEB3AdminPointExchangeApplyAcceptHandler.class,
            "cancelReleaseComplete");            
            
        //�i�i�Ǘ� �p�n���h���[�̓o�^
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointPremiumReferenceRequest.class,
            WEB3AdminPointPremiumReferenceHandler.class,
            "premiumReferenceRequest");
            
        //�i�i�폜 �p�n���h���[�̓o�^
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointPremiumDeleteConfirmRequest.class,
            WEB3AdminPointPremiumDeleteHandler.class,
            "deleteConfirm");   
            
        //�i�i�폜 �p�n���h���[�̓o�^
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointPremiumDeleteCompleteRequest.class,
            WEB3AdminPointPremiumDeleteHandler.class,
            "deleteComplete"); 
            
        //�i�i���� �p�n���h���[�̓o�^
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointPremiumChangeInputRequest.class,
            WEB3AdminPointPremiumChangeHandler.class,
            "inputScreenDisplay");   
            
        //�i�i���� �p�n���h���[�̓o�^
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointPremiumChangeConfirmRequest.class,
            WEB3AdminPointPremiumChangeHandler.class,
            "changeConfirm");   
            
        //�i�i���� �p�n���h���[�̓o�^
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointPremiumChangeCompleteRequest.class,
            WEB3AdminPointPremiumChangeHandler.class,
            "changeComplete");  
            
        //�i�i�o�^ �p�n���h���[�̓o�^
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointPremiumRegistInputRequest.class,
            WEB3AdminPointPremiumRegistHandler.class,
            "inputScreenDisplay");    
            
        //�i�i�o�^ �p�n���h���[�̓o�^
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointPremiumRegistConfirmRequest.class,
            WEB3AdminPointPremiumRegistHandler.class,
            "registConfirm");    
            
        //�i�i�o�^ �p�n���h���[�̓o�^
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointPremiumRegistCompleteRequest.class,
            WEB3AdminPointPremiumRegistHandler.class,
            "registComplete"); 
            
        //�ڋq�ʃ|�C���g�Ǘ� �p�n���h���[�̓o�^
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointManageDisplayRequest.class,
            WEB3AdminPointManageByCustomerHandler.class,
            "manageScreenDisplay");  
            
        //�ڋq�ʃ|�C���g�Ǘ� �p�n���h���[�̓o�^
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointAdjustInputRequest.class,
            WEB3AdminPointManageByCustomerHandler.class,
            "adjustInput");     
            
        //�ڋq�ʃ|�C���g�Ǘ� �p�n���h���[�̓o�^
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointAdjustConfirmRequest.class,
            WEB3AdminPointManageByCustomerHandler.class,
            "adjustConfirm"); 
            
        //�ڋq�ʃ|�C���g�Ǘ� �p�n���h���[�̓o�^
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointAdjustCompleteRequest.class,
            WEB3AdminPointManageByCustomerHandler.class,
            "adjustComplete");      
            
        //�ڋq�ʃ|�C���g�Ǘ� �p�n���h���[�̓o�^
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointHistoryReferenceRequest.class,
            WEB3AdminPointManageByCustomerHandler.class,
            "historyReference");                               
        
        //�g���[�h�{�[�i�X�v�����Ɖ� �p�n���h���[�̓o�^
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3PointTradeBonusPlanReferenceRequest.class,
            WEB3PointTradeBonusPlanReferenceHandler.class,
            "tradeBonusPlanReference"); 
        
        //�����萔���������Ɖ� �p�n���h���[�̓o�^
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3PointCommissionInfoReferenceRequest.class,
            WEB3PointCommissionInfoReferenceHandler.class,
            "commissionInfoReference"); 
        
        //�Ǘ��҃g���[�h�{�[�i�X�v�����Ɖ� �p�n���h���[�̓o�^
        regHandler(
            WEB3PointAppPlugin.class,
            WEB3AdminPointTradeBonusPlanReferenceRequest.class,
            WEB3AdminPointTradeBonusPlanReferenceHandler.class,
            "getReferenceScreen"); 
        
        log.exiting(STR_METHOD_NAME);
    }
    
}
@
