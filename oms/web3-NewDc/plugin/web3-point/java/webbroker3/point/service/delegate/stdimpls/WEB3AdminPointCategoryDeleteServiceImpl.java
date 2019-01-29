head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.52.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointCategoryDeleteServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �J�e�S���[�폜�T�[�r�XImpl(WEB3AdminPointCategoryDeleteServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 ���w��(���u) �V�K�쐬
*/
package webbroker3.point.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.point.WEB3PointCategory;
import webbroker3.point.WEB3PointProductManager;
import webbroker3.point.data.PointCategoryMasterRow;
import webbroker3.point.message.WEB3AdminPointCategoryDeleteCompleteRequest;
import webbroker3.point.message.WEB3AdminPointCategoryDeleteCompleteResponse;
import webbroker3.point.message.WEB3AdminPointCategoryDeleteConfirmRequest;
import webbroker3.point.message.WEB3AdminPointCategoryDeleteConfirmResponse;
import webbroker3.point.service.delegate.WEB3AdminPointCategoryDeleteService;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�J�e�S���[�폜�T�[�r�XImpl)<BR>
 * �J�e�S���[�폜�T�[�r�X�����N���X<BR>
 *
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AdminPointCategoryDeleteServiceImpl implements WEB3AdminPointCategoryDeleteService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPointCategoryDeleteServiceImpl.class);

    /**
     * �J�e�S���[�폜�T�[�r�X�������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Avalidate�폜()�܂���submit�폜()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3GenResponse
     * @@roseuid 4191BCC40028
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);        
        
        if (l_request instanceof WEB3AdminPointCategoryDeleteConfirmRequest)
        {
            log.debug("WEB3AdminPointCategoryDeleteConfirmRequest");
            WEB3AdminPointCategoryDeleteConfirmResponse l_response = 
                validateDelete((WEB3AdminPointCategoryDeleteConfirmRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminPointCategoryDeleteCompleteRequest)
        {
            log.debug("WEB3AdminPointCategoryDeleteCompleteRequest");
            WEB3AdminPointCategoryDeleteCompleteResponse l_response = 
                submitDelete((WEB3AdminPointCategoryDeleteCompleteRequest)l_request);
        
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
     * (validate�폜)<BR>
     * �폜�̐R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�J�e�S���[�폜�jvalidate�폜�v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointCategoryDeleteConfirmResponse
     * @@roseuid 4191BCC4002C
     */
    protected WEB3AdminPointCategoryDeleteConfirmResponse validateDelete(WEB3AdminPointCategoryDeleteConfirmRequest l_request) throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = " validateDelete(WEB3AdminPointCategoryDeleteConfirmRequest)";
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
        
        //1.4 validate�戵�i�i(String, String)
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
        
        //[����] 
        //�،���ЃR�[�h�F �Ǘ���.get�،���ЃR�[�h()�̖߂�l 
        //�J�e�S���[�ԍ��F ���N�G�X�g�f�[�^.�J�e�S���[�ԍ�     
        l_manager.validateHandingPremium(l_strInstitutionCode, l_request.categoryNo);
        
        //1.5 get�J�e�S���[(String, String)
        //[����] 
        //�،���ЃR�[�h�F �Ǘ���.get�،���ЃR�[�h()�̖߂�l 
        //�J�e�S���[�ԍ��F ���N�G�X�g�f�[�^.�J�e�S���[�ԍ� 
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
        
        //1.6 createResponse( )
        WEB3AdminPointCategoryDeleteConfirmResponse l_response = 
            (WEB3AdminPointCategoryDeleteConfirmResponse)l_request.createResponse();
        
        //1.7 �v���p�e�B�Z�b�g
        //get�J�e�S���[��
        l_response.categoryName = l_pointCategory.getCategoryName();
        
        //get�J�e�S���[�T�v
        l_response.categoryOutline = l_pointCategory.getCategoryOutline();      
                
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (submit�폜)<BR>
     * �J�e�S���[�̍폜���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�J�e�S���[�폜�jsubmit�폜�v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointCategoryDeleteCompleteResponse
     * @@roseuid 4191BCC4002E
     */
    protected WEB3AdminPointCategoryDeleteCompleteResponse submitDelete(WEB3AdminPointCategoryDeleteCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitDelete(WEB3AdminPointCategoryDeleteCompleteRequest)";
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
        
        //1.5 validate�戵�i�i(String, String)
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
        
        //[����] 
        //�،���ЃR�[�h�F �Ǘ���.get�،���ЃR�[�h()�̖߂�l 
        //�J�e�S���[�ԍ��F ���N�G�X�g�f�[�^.�J�e�S���[�ԍ� 
        l_manager.validateHandingPremium(l_strInstitutionCode, l_request.categoryNo);
        
        //1.6 get�J�e�S���[(String, String)
        //[����]
        //�،���ЃR�[�h�F �Ǘ���.get�،���ЃR�[�h()�̖߂�l 
        //�J�e�S���[�ԍ��F ���N�G�X�g�f�[�^.�J�e�S���[�ԍ� 
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
        
        //1.7 deleteRow(Row)
        //[����] 
        //�J�e�S���[�s�F �|�C���g�J�e�S���[.getDataSourceObject()�̖߂�l 
        try
        {
            WEB3DataAccessUtility.deleteRow((PointCategoryMasterRow)l_pointCategory.getDataSourceObject());//DataQueryException, DataNetworkException
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.8 createResponse()
        WEB3AdminPointCategoryDeleteCompleteResponse l_response = 
            (WEB3AdminPointCategoryDeleteCompleteResponse)l_request.createResponse();   
                
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
}
@
