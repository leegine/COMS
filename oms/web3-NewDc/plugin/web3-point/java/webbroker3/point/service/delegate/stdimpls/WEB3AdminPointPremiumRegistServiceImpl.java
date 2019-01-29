head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.52.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointPremiumRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�i�o�^�T�[�r�XImpl(WEB3AdminPointPremiumRegistServiceImpl.java)
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
import webbroker3.point.WEB3PointPremium;
import webbroker3.point.WEB3PointProductManager;
import webbroker3.point.message.WEB3AdminPointPremiumRegistCompleteRequest;
import webbroker3.point.message.WEB3AdminPointPremiumRegistCompleteResponse;
import webbroker3.point.message.WEB3AdminPointPremiumRegistConfirmRequest;
import webbroker3.point.message.WEB3AdminPointPremiumRegistConfirmResponse;
import webbroker3.point.message.WEB3AdminPointPremiumRegistInputRequest;
import webbroker3.point.message.WEB3AdminPointPremiumRegistInputResponse;
import webbroker3.point.service.delegate.WEB3AdminPointPremiumRegistService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i�i�o�^�T�[�r�XImpl)<BR>
 * �i�i�o�^�T�[�r�X�����N���X<BR>
 *
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AdminPointPremiumRegistServiceImpl implements WEB3AdminPointPremiumRegistService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPointPremiumRegistServiceImpl.class);

    /**
     * �i�i�o�^�T�[�r�X�������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Aget���͉��()�Avalidate�o�^()<BR>
     * �܂���submit�o�^()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3GenResponse
     * @@roseuid 4192E9C10185
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);        
        
        if (l_request instanceof WEB3AdminPointPremiumRegistInputRequest)
        {
            log.debug("WEB3AdminPointPremiumRegistInputRequest");
            WEB3AdminPointPremiumRegistInputResponse l_response = 
                getInputScreen((WEB3AdminPointPremiumRegistInputRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminPointPremiumRegistConfirmRequest)
        {
            log.debug("WEB3AdminPointPremiumRegistConfirmRequest");
            WEB3AdminPointPremiumRegistConfirmResponse l_response = 
                validateRegist((WEB3AdminPointPremiumRegistConfirmRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;           
        }
        else if (l_request instanceof WEB3AdminPointPremiumRegistCompleteRequest)
        {
            log.debug("WEB3AdminPointPremiumRegistCompleteRequest");
            WEB3AdminPointPremiumRegistCompleteResponse l_response = 
                submitRegist((WEB3AdminPointPremiumRegistCompleteRequest)l_request);
        
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
     * �u�i�i�i�o�^�jget���͉�ʁv�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointPremiumRegistInputResponse
     * @@roseuid 4192E9C1026F
     */
    protected WEB3AdminPointPremiumRegistInputResponse getInputScreen(WEB3AdminPointPremiumRegistInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminPointPremiumRegistInputRequest)";
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
    
        //1.3 createResponse()
        WEB3AdminPointPremiumRegistInputResponse l_response = 
            (WEB3AdminPointPremiumRegistInputResponse)l_request.createResponse();
            
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (validate�o�^)<BR>
     * �o�^�̐R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�i�i�o�^�jvalidate�o�^�v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointPremiumRegistConfirmResponse
     * @@roseuid 4192E9C10398
     */
    protected WEB3AdminPointPremiumRegistConfirmResponse validateRegist(WEB3AdminPointPremiumRegistConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateRegist(WEB3AdminPointPremiumRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
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
    
        //1.4 validate�d���i�i�ԍ�(String, String)
        WEB3PointProductManager l_manager = 
            (WEB3PointProductManager) Services.getService(WEB3PointProductManager.class);
        
        if (l_manager == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        l_manager.validateDupliPremiumNo(l_admin.getInstitutionCode(), l_request.premiumNo);    
    
        //1.5 validate�i�i���e(String, String, String, Date, Date)
        //[����] 
        //�i�i�ԍ��F ���N�G�X�g�f�[�^.�i�i�ԍ� 
        //�i�i���F ���N�G�X�g�f�[�^.�i�i�� 
        //�K�v�|�C���g�F ���N�G�X�g�f�[�^.�K�v�|�C���g 
        //�񋟊J�n�����F ���N�G�X�g�f�[�^.�񋟊J�n���� 
        //�񋟏I�������F ���N�G�X�g�f�[�^.�񋟏I������ 
        l_manager.validatePremiumSpec(l_request.premiumNo, 
            l_request.premiumName, l_request.requiredPoint, 
            l_request.startDate, l_request.endDate);
                
        //1.6 createResponse()
        WEB3AdminPointPremiumRegistConfirmResponse l_response = 
            (WEB3AdminPointPremiumRegistConfirmResponse)l_request.createResponse();
            
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (submit�o�^)<BR>
     * �o�^���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�i�i�o�^�jsubmit�o�^�v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointPremiumRegistCompleteResponse
     * @@roseuid 4192E9C200C9
     */
    protected WEB3AdminPointPremiumRegistCompleteResponse submitRegist(WEB3AdminPointPremiumRegistCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitRegist(WEB3AdminPointPremiumRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
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
        
        //1.5 validate�d���i�i�ԍ�(String, String)
        WEB3PointProductManager l_manager = 
            (WEB3PointProductManager) Services.getService(WEB3PointProductManager.class);
        
        if (l_manager == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        l_manager.validateDupliPremiumNo(l_admin.getInstitutionCode(), l_request.premiumNo);
        
        //1.6  validate�i�i���e(String, String, String, Date, Date)
        //[����] 
        //�i�i�ԍ��F ���N�G�X�g�f�[�^.�i�i�ԍ� 
        //�i�i���F ���N�G�X�g�f�[�^.�i�i�� 
        //�K�v�|�C���g�F ���N�G�X�g�f�[�^.�K�v�|�C���g 
        //�񋟊J�n�����F ���N�G�X�g�f�[�^.�񋟊J�n���� 
        //�񋟏I�������F ���N�G�X�g�f�[�^.�񋟏I������ 
        l_manager.validatePremiumSpec(l_request.premiumNo, 
            l_request.premiumName, l_request.requiredPoint, 
            l_request.startDate, l_request.endDate);

        //1.7 �|�C���g�i�i(String, long, String, String, long, Date, Date)
        //[����] 
        //�،���ЃR�[�h�F �Ǘ���.get�،���ЃR�[�h()�̖߂�l 
        //�J�e�S���[�ԍ��F ���N�G�X�g�f�[�^.�J�e�S���[�ԍ� 
        //�i�i�ԍ��F ���N�G�X�g�f�[�^.�i�i�ԍ� 
        //�i�i���F ���N�G�X�g�f�[�^.�i�i�� 
        //�K�v�|�C���g�F ���N�G�X�g�f�[�^.�K�v�|�C���g 
        //�񋟊J�n�����F ���N�G�X�g�f�[�^.�񋟊J�n���� 
        //�񋟏I�������F ���N�G�X�g�f�[�^.�񋟏I������         
        long l_lngCategoryNo = Long.parseLong(l_request.categoryNo);     
        
        long l_lngRequiredPoint = Long.parseLong(l_request.requiredPoint);

        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        WEB3PointPremium l_pointPremium = new WEB3PointPremium(
            l_strInstitutionCode, l_lngCategoryNo, l_request.premiumNo, 
            l_request.premiumName, l_lngRequiredPoint, l_request.startDate, l_request.endDate);
        
        //1.8 saveNew�i�i(�|�C���g�i�i, �Ǘ���)
        //[����] 
        //�i�i�F �|�C���g�i�i�I�u�W�F�N�g 
        //�Ǘ��ҁF �Ǘ��҃I�u�W�F�N�g 
        l_manager.saveNewPremium(l_pointPremium, l_admin);
                
        //1.9 createResponse()
        WEB3AdminPointPremiumRegistCompleteResponse l_response = 
            (WEB3AdminPointPremiumRegistCompleteResponse)l_request.createResponse();
            
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
}
@
