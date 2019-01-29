head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.52.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointCategoryChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �J�e�S���[�����T�[�r�XImpl(WEB3AdminPointCategoryChangeServiceImpl.java)
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
import webbroker3.point.message.WEB3AdminPointCategoryChangeCompleteRequest;
import webbroker3.point.message.WEB3AdminPointCategoryChangeCompleteResponse;
import webbroker3.point.message.WEB3AdminPointCategoryChangeConfirmRequest;
import webbroker3.point.message.WEB3AdminPointCategoryChangeConfirmResponse;
import webbroker3.point.message.WEB3AdminPointCategoryChangeInputRequest;
import webbroker3.point.message.WEB3AdminPointCategoryChangeInputResponse;
import webbroker3.point.service.delegate.WEB3AdminPointCategoryChangeService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�J�e�S���[�����T�[�r�XImpl)<BR>
 * �J�e�S���[�����T�[�r�X�����N���X<BR>
 *
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AdminPointCategoryChangeServiceImpl implements WEB3AdminPointCategoryChangeService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPointCategoryChangeServiceImpl.class);

    /**
     * �J�e�S���[�����T�[�r�X�������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Aget���͉��()�Avalidate����()�܂���<BR>submit����()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3GenResponse
     * @@roseuid 4191A5A001C6
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);        
        
        if (l_request instanceof WEB3AdminPointCategoryChangeInputRequest)
        {
            log.debug("WEB3AdminPointCategoryChangeInputRequest");
            WEB3AdminPointCategoryChangeInputResponse l_response = 
                getInputScreen((WEB3AdminPointCategoryChangeInputRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminPointCategoryChangeConfirmRequest)
        {
            log.debug("WEB3AdminPointCategoryChangeConfirmRequest");
            WEB3AdminPointCategoryChangeConfirmResponse l_response = 
                validateChange((WEB3AdminPointCategoryChangeConfirmRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;           
        }
        else if (l_request instanceof WEB3AdminPointCategoryChangeCompleteRequest)
        {
            log.debug("WEB3AdminPointCategoryChangeCompleteRequest");
            WEB3AdminPointCategoryChangeCompleteResponse l_response = 
                submitChange((WEB3AdminPointCategoryChangeCompleteRequest)l_request);
        
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
     * �u�i�J�e�S���[�����jget���͉�ʁv�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointCategoryChangeInputResponse
     * @@roseuid 4191AA060149
     */
    protected WEB3AdminPointCategoryChangeInputResponse getInputScreen(WEB3AdminPointCategoryChangeInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminPointCategoryChangeInputRequest)";
        log.entering(STR_METHOD_NAME);  
        
        //1.1  validate( )
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        if (l_admin == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.3 validate����(String, boolean) 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.POINT_PREMIUM_MANAGE, true);
        
        //1.4  get�J�e�S���[(String, String)
        //[����] 
        //�،���ЃR�[�h�F �Ǘ���.get�،���ЃR�[�h()�̖߂�l 
        //�J�e�S���[�ԍ��F ���N�G�X�g�f�[�^.�J�e�S���[�ԍ� 
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
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
            
        WEB3PointCategory l_pointCategory = l_manager.getCategory(
            l_strInstitutionCode, l_request.categoryNo);
            
        if (l_pointCategory == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.5 createResponse( )
        WEB3AdminPointCategoryChangeInputResponse l_response = 
            (WEB3AdminPointCategoryChangeInputResponse)l_request.createResponse();
        if (l_response == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        
        //1.6 �v���p�e�B�Z�b�g
        //�J�e�S���[��
        l_response.beforeCategoryName = l_pointCategory.getCategoryName();
        
        //�J�e�S���[�T�v
        l_response.beforeCategoryOutline = l_pointCategory.getCategoryOutline(); 
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (validate����)<BR>
     * �����̐R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�J�e�S���[�����jvalidate�����v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointCategoryChangeConfirmResponse
     * @@roseuid 4191A5A001C8
     */
    protected WEB3AdminPointCategoryChangeConfirmResponse validateChange(WEB3AdminPointCategoryChangeConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateChange(WEB3AdminPointCategoryChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);  
        
        //1.1  validate( )
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        if (l_admin == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.3 validate����(String, boolean) 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.POINT_PREMIUM_MANAGE, true);
        
        //1.4  get�J�e�S���[(String, String)
        //[����] 
        //�،���ЃR�[�h�F �Ǘ���.get�،���ЃR�[�h()�̖߂�l 
        //�J�e�S���[�ԍ��F ���N�G�X�g�f�[�^.�J�e�S���[�ԍ� 
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
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
            
        WEB3PointCategory l_pointCategory = l_manager.getCategory(
            l_strInstitutionCode, l_request.categoryNo);
        
        //1.5 validate�����J�e�S���[���e(String, String, �|�C���g�J�e�S���[)
        //[����] 
        //�J�e�S���[���F ���N�G�X�g�f�[�^.������J�e�S���[�� 
        //�T�v�F ���N�G�X�g�f�[�^.������J�e�S���[�T�v 
        //�J�e�S���[�F get�J�e�S���[()�̖߂�l 
        l_manager.validateChangeCategorySpec(l_request.afterCategoryName, 
            l_request.afterCategoryOutline, l_pointCategory);
                
        //1.6 createResponse()
        WEB3AdminPointCategoryChangeConfirmResponse l_response = 
            (WEB3AdminPointCategoryChangeConfirmResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (submit����)<BR>
     * �J�e�S���[�̒������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�J�e�S���[�����jsubmit�����v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointCategoryChangeCompleteResponse
     * @@roseuid 4191A5A001D6
     */
    protected WEB3AdminPointCategoryChangeCompleteResponse submitChange(WEB3AdminPointCategoryChangeCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitChange(WEB3AdminPointCategoryChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);  
        
        //1.1  validate( )
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        if (l_admin == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.3 validate����(String, boolean) 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.POINT_PREMIUM_MANAGE, true);
        
        //1.4 validate����p�X���[�h(String)
        l_admin.validateTradingPassword(l_request.password);
        
        //1.5 get�J�e�S���[(String, String)
        //[����]
        //�،���ЃR�[�h�F �Ǘ���.get�،���ЃR�[�h()�̖߂�l 
        //�J�e�S���[�ԍ��F ���N�G�X�g�f�[�^.�J�e�S���[�ԍ� 
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
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
            
        WEB3PointCategory l_pointCategory = l_manager.getCategory(
            l_strInstitutionCode, l_request.categoryNo);
        
        //1.6 validate�����J�e�S���[���e(String, String, �|�C���g�J�e�S���[)
        //[����] 
        //�J�e�S���[���F ���N�G�X�g�f�[�^.������J�e�S���[�� 
        //�T�v�F ���N�G�X�g�f�[�^.������J�e�S���[�T�v 
        //�J�e�S���[�F get�J�e�S���[()�̖߂�l 
        l_manager.validateChangeCategorySpec(l_request.afterCategoryName, 
            l_request.afterCategoryOutline, l_pointCategory);
                
        if (l_pointCategory == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.7 createForUpdateParams()
        l_pointCategory.createForUpdateParams();
        
        //1.8 set�J�e�S���[��(String)
        //[����] 
        //�J�e�S���[���F ���N�G�X�g�f�[�^.������J�e�S���[��
        l_pointCategory.setCategoryName(l_request.afterCategoryName);
        
        //1.9 set�J�e�S���[�T�v(String)
        //[����] 
        //�T�v�F ���N�G�X�g�f�[�^.������J�e�S���[�T�v 
        l_pointCategory.setCategoryOutline(l_request.afterCategoryOutline);
        
        //1.10 save�J�e�S���[(�|�C���g�J�e�S���[, �Ǘ���)
        //[����] 
        //�J�e�S���[�F �|�C���g�J�e�S���[�I�u�W�F�N�g 
        //�Ǘ��ҁF �Ǘ��҃I�u�W�F�N�g 
        l_manager.saveCategory(l_pointCategory, l_admin);
        
        //1.11 createResponse( )
        WEB3AdminPointCategoryChangeCompleteResponse l_response = 
            (WEB3AdminPointCategoryChangeCompleteResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
}
@
