head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.51.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointCategoryRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �J�e�S���[�o�^�T�[�r�XImpl(WEB3AdminPointCategoryRegistServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 ���w��(���u) �V�K�쐬
*/
package webbroker3.point.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.point.WEB3PointCategory;
import webbroker3.point.WEB3PointProductManager;
import webbroker3.point.message.WEB3AdminPointCategoryRegistCompleteRequest;
import webbroker3.point.message.WEB3AdminPointCategoryRegistCompleteResponse;
import webbroker3.point.message.WEB3AdminPointCategoryRegistConfirmRequest;
import webbroker3.point.message.WEB3AdminPointCategoryRegistConfirmResponse;
import webbroker3.point.message.WEB3AdminPointCategoryRegistInputRequest;
import webbroker3.point.message.WEB3AdminPointCategoryRegistInputResponse;
import webbroker3.point.service.delegate.WEB3AdminPointCategoryRegistService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�J�e�S���[�o�^�T�[�r�XImpl)<BR>
 * �J�e�S���[�o�^�T�[�r�X�����N���X<BR>
 *
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AdminPointCategoryRegistServiceImpl implements WEB3AdminPointCategoryRegistService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPointCategoryRegistServiceImpl.class);

    /**
     * �J�e�S���[�o�^�T�[�r�X�������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Aget���͉��()�Avalidate�o�^()�܂���submit�o�^()���\�b�h<BR>���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3GenResponse
     * @@roseuid 419179760101
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);        
        
        if (l_request instanceof WEB3AdminPointCategoryRegistInputRequest)
        {
            log.debug("WEB3AdminPointCategoryRegistInputRequest");
            WEB3AdminPointCategoryRegistInputResponse l_response = 
                getInputScreen((WEB3AdminPointCategoryRegistInputRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminPointCategoryRegistConfirmRequest)
        {
            log.debug("WEB3AdminPointCategoryRegistConfirmRequest");
            WEB3AdminPointCategoryRegistConfirmResponse l_response = 
                validateRegist((WEB3AdminPointCategoryRegistConfirmRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;           
        }
        else if (l_request instanceof WEB3AdminPointCategoryRegistCompleteRequest)
        {
            log.debug("WEB3AdminPointCategoryRegistCompleteRequest");
            WEB3AdminPointCategoryRegistCompleteResponse l_response = 
                submitRegist((WEB3AdminPointCategoryRegistCompleteRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;           
        }
        else
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
    }
    
    /**
     * (get���͉��)<BR>
     * ���͉�ʂ̎擾���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�J�e�S���[�o�^�jget���͉�ʁv�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointCategoryRegistInputResponse
     * @@roseuid 41917A4F0046
     */
    protected WEB3AdminPointCategoryRegistInputResponse getInputScreen(WEB3AdminPointCategoryRegistInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminPointCategoryRegistInputRequest)";
        log.entering(STR_METHOD_NAME);  
        
        //1.1 getInstanceFrom���O�C�����()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        if (l_admin == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.2 validate����(String, boolean) 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.POINT_PREMIUM_MANAGE, true);
        
        //1.3 createResponse( )
        WEB3AdminPointCategoryRegistInputResponse l_response = 
            (WEB3AdminPointCategoryRegistInputResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (validate�o�^)<BR>
     * �o�^�̐R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�J�e�S���[�o�^�jvalidate�o�^�v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointCategoryRegistConfirmResponse
     * @@roseuid 4191A8B30159
     */
    protected WEB3AdminPointCategoryRegistConfirmResponse validateRegist(WEB3AdminPointCategoryRegistConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateRegist(WEB3AdminPointCategoryRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);  
        
        //1.1 getInstanceFrom���O�C�����()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        if (l_admin == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.2 validate����(String, boolean) 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.POINT_PREMIUM_MANAGE, true);
        
        //1.3 validate�J�e�S���[���e(String, String)
        //[����] 
        //�J�e�S���[���F ���N�G�X�g�f�[�^.�J�e�S���[�� 
        //�T�v�F ���N�G�X�g�f�[�^.�J�e�S���[�T�v 
        WEB3PointProductManager l_manager = (WEB3PointProductManager)
            Services.getService(WEB3PointProductManager.class);
        
        if (l_manager == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
           
        l_manager.validateCategorySpec(l_request.categoryName, l_request.categoryOutline);
                
        //1.4 createResponse( )
        WEB3AdminPointCategoryRegistConfirmResponse l_response = 
            (WEB3AdminPointCategoryRegistConfirmResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (submit�o�^)<BR>
     * �J�e�S���[�̓o�^���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�J�e�S���[�o�^�jsubmit�o�^�v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointCategoryRegistCompleteResponse
     * @@roseuid 41917ABC03D0
     */
    protected WEB3AdminPointCategoryRegistCompleteResponse submitRegist(WEB3AdminPointCategoryRegistCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitRegist(WEB3AdminPointCategoryRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME);  
        
        //1.1 getInstanceFrom���O�C�����()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        if (l_admin == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.2 validate����(String, boolean) 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.POINT_PREMIUM_MANAGE, true);
        
        //1.3 validate����p�X���[�h(String)
        l_admin.validateTradingPassword(l_request.password);
        
        //1.4 validate�J�e�S���[���e(String, String)
        //[����] 
        //�J�e�S���[���F ���N�G�X�g�f�[�^.�J�e�S���[�� 
        //�T�v�F ���N�G�X�g�f�[�^.�J�e�S���[�T�v 
        WEB3PointProductManager l_manager = (WEB3PointProductManager)
            Services.getService(WEB3PointProductManager.class);
        
        if (l_manager == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
           
        l_manager.validateCategorySpec(l_request.categoryName, l_request.categoryOutline);

        //1.5 �|�C���g�J�e�S���[(String, String, String)
        //[����] 
        //�،���ЃR�[�h�F �Ǘ���.get�،���ЃR�[�h()�̖߂�l 
        //�J�e�S���[���F ���N�G�X�g�f�[�^.�J�e�S���[�� 
        //�T�v�F ���N�G�X�g�f�[�^.�J�e�S���[�T�v 
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        WEB3PointCategory l_pointCategory = new WEB3PointCategory(l_strInstitutionCode, 
            l_request.categoryName, l_request.categoryOutline);
            
        //1.6 saveNew�J�e�S���[(�|�C���g�J�e�S���[, �Ǘ���)
        //[����] 
        //�J�e�S���[�F �|�C���g�J�e�S���[�I�u�W�F�N�g 
        //�Ǘ��ҁF �Ǘ��҃I�u�W�F�N�g 
        l_manager.saveNewCategory(l_pointCategory, l_admin);
        
        //1.7 createResponse( )
        WEB3AdminPointCategoryRegistCompleteResponse l_response = 
            (WEB3AdminPointCategoryRegistCompleteResponse)l_request.createResponse();
        
        return l_response;
    }
}
@
