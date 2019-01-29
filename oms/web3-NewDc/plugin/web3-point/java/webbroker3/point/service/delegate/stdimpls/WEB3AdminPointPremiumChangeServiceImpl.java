head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.51.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointPremiumChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�i�����T�[�r�XImpl(WEB3AdminPointPremiumChangeServiceImpl.java)
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
import webbroker3.point.message.WEB3AdminPointPremiumChangeCompleteRequest;
import webbroker3.point.message.WEB3AdminPointPremiumChangeCompleteResponse;
import webbroker3.point.message.WEB3AdminPointPremiumChangeConfirmRequest;
import webbroker3.point.message.WEB3AdminPointPremiumChangeConfirmResponse;
import webbroker3.point.message.WEB3AdminPointPremiumChangeInputRequest;
import webbroker3.point.message.WEB3AdminPointPremiumChangeInputResponse;
import webbroker3.point.service.delegate.WEB3AdminPointPremiumChangeService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i�i�����T�[�r�XImpl)<BR>
 * �i�i�����T�[�r�X�����N���X<BR>
 *
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AdminPointPremiumChangeServiceImpl implements WEB3AdminPointPremiumChangeService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPointPremiumChangeServiceImpl.class);

    /**
     * �i�i�����T�[�r�X�������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Aget���͉��()�Avalidate����()<BR>
     * �܂���submit����()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3GenResponse
     * @@roseuid 41933860001D
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);        
        
        if (l_request instanceof WEB3AdminPointPremiumChangeInputRequest)
        {
            log.debug("WEB3AdminPointPremiumChangeInputRequest");
            WEB3AdminPointPremiumChangeInputResponse l_response = 
                getInputScreen((WEB3AdminPointPremiumChangeInputRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminPointPremiumChangeConfirmRequest)
        {
            log.debug("WEB3AdminPointPremiumChangeConfirmRequest");
            WEB3AdminPointPremiumChangeConfirmResponse l_response = 
                validateChange((WEB3AdminPointPremiumChangeConfirmRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;           
        }
        else if (l_request instanceof WEB3AdminPointPremiumChangeCompleteRequest)
        {
            log.debug("WEB3AdminPointPremiumChangeCompleteRequest");
            WEB3AdminPointPremiumChangeCompleteResponse l_response = 
                submitChange((WEB3AdminPointPremiumChangeCompleteRequest)l_request);
        
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
     * ]���͉�ʂ̎擾���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�i�i�����jget���͉�ʁv�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointPremiumChangeInputResponse
     * @@roseuid 41933860001F
     */
    protected WEB3AdminPointPremiumChangeInputResponse getInputScreen(WEB3AdminPointPremiumChangeInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminPointPremiumChangeInputRequest)";
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
        
        //1.4 get�i�i(String, String)
        //[����] 
        //�،���ЃR�[�h�F �Ǘ���.get�،���ЃR�[�h()�̖߂�l 
        //�i�i�ԍ��F ���N�G�X�g�f�[�^.�i�i�ԍ� 

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
            
        WEB3PointPremium l_premium = l_manager.getPremium(l_strInstitutionCode, l_request.premiumNo);
        
        if (l_premium == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.5  createResponse()
        WEB3AdminPointPremiumChangeInputResponse l_response = 
            (WEB3AdminPointPremiumChangeInputResponse)l_request.createResponse();
        
        //1.6 �v���p�e�B�Z�b�g
        //�i�i��
        if (l_response == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        l_response.beforePremiumName = l_premium.getPremiumName();
        
        //�K�v�|�C���g
        l_response.beforeRequiredPoint = new Long(l_premium.getRequiredPoint()).toString();
        
        //�񋟊J�n����
        l_response.beforeStartDate = l_premium.getStartDateTime();
        
        //�񋟏I������
        l_response.beforeEndDate = l_premium.getEndDateTime();
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (validate����)<BR>
     * �����̐R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�i�i�����jvalidate�����v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointPremiumChangeConfirmResponse
     * @@roseuid 419338600021
     */
    protected WEB3AdminPointPremiumChangeConfirmResponse validateChange(WEB3AdminPointPremiumChangeConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateChange(WEB3AdminPointPremiumChangeConfirmRequest)";
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
        
        //1.4 get�i�i(String, String)
        //[����] 
        //�،���ЃR�[�h�F �Ǘ���.get�،���ЃR�[�h()�̖߂�l 
        //�i�i�ԍ��F ���N�G�X�g�f�[�^.�i�i�ԍ� 

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
            
        WEB3PointPremium l_premium = l_manager.getPremium(
            l_strInstitutionCode, l_request.premiumNo);
        
        //1.5 validate�����i�i���e(String, String, String, Date, Date, �|�C���g�i�i)
        //[����] 
        //�i�i�ԍ��F ���N�G�X�g�f�[�^.�i�i�ԍ� 
        //�i�i���F ���N�G�X�g�f�[�^.������i�i�� 
        //�K�v�|�C���g�F ���N�G�X�g�f�[�^.������K�v�|�C���g 
        //�񋟊J�n�����F ���N�G�X�g�f�[�^.������񋟊J�n���� 
        //�񋟏I�������F ���N�G�X�g�f�[�^.������񋟏I������ 
        //�i�i�F get�i�i()�̖߂�l 

        l_manager.validateChangePremiumSpec(l_request.premiumNo, 
            l_request.afterPremiumName, l_request.afterRequiredPoint, 
            l_request.afterStartDate, l_request.afterEndDate, l_premium);
                
        //1.6  createResponse()
        WEB3AdminPointPremiumChangeConfirmResponse l_response = 
            (WEB3AdminPointPremiumChangeConfirmResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (submit����)<BR>
     * �i�i�̒������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�i�i�����jsubmit�����v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointPremiumChangeCompleteResponse
     * @@roseuid 419338600023
     */
    protected WEB3AdminPointPremiumChangeCompleteResponse submitChange(WEB3AdminPointPremiumChangeCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitChange(WEB3AdminPointPremiumChangeCompleteRequest)";
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
        
        //1.5 get�i�i(String, String)
        //[����] 
        //�،���ЃR�[�h�F �Ǘ���.get�،���ЃR�[�h()�̖߂�l 
        //�i�i�ԍ��F ���N�G�X�g�f�[�^.�i�i�ԍ� 

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
            
        WEB3PointPremium l_premium = l_manager.getPremium(
            l_strInstitutionCode, l_request.premiumNo);
        
        //1.6 validate�����i�i���e(String, String, String, Date, Date, �|�C���g�i�i)
        //[����] 
        //�i�i�ԍ��F ���N�G�X�g�f�[�^.�i�i�ԍ� 
        //�i�i���F ���N�G�X�g�f�[�^.������i�i�� 
        //�K�v�|�C���g�F ���N�G�X�g�f�[�^.������K�v�|�C���g 
        //�񋟊J�n�����F ���N�G�X�g�f�[�^.������񋟊J�n���� 
        //�񋟏I�������F ���N�G�X�g�f�[�^.������񋟏I������ 
        //�i�i�F get�i�i()�̖߂�l 

        l_manager.validateChangePremiumSpec(l_request.premiumNo, 
            l_request.afterPremiumName, l_request.afterRequiredPoint, 
            l_request.afterStartDate, l_request.afterEndDate, l_premium);
        
        if (l_premium == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.7 createForUpdateParams( )
        l_premium.createForUpdateParams();
        
        //1.8 set�i�i��(String)
        //[����] 
        //�i�i���F ���N�G�X�g�f�[�^.������i�i�� 
        l_premium.setPremiumName(l_request.afterPremiumName);
        
        //1.9 set�K�v�|�C���g(long)
        //[����] 
        //�K�v�|�C���g�F ���N�G�X�g�f�[�^.������K�v�|�C���g        
        long l_lngAfterRequiredPoint = Long.parseLong(l_request.afterRequiredPoint);
        
        l_premium.setRequiredPoint(l_lngAfterRequiredPoint);
        
        //1.10 set�񋟊J�n����(Date)
        //[����] 
        //�񋟊J�n�����F ���N�G�X�g�f�[�^.������񋟊J�n���� 
        l_premium.setStartDateTime(l_request.afterStartDate);
        
        //1.11 set�񋟏I������(Date)
        //[����] 
        //�񋟏I�������F ���N�G�X�g�f�[�^.������񋟏I������ 
        l_premium.setEndDateTime(l_request.afterEndDate);
        
        //1.12 save�i�i(�|�C���g�i�i, �Ǘ���)
        //[����] 
        //�i�i�F �|�C���g�i�i�I�u�W�F�N�g 
        //�Ǘ��ҁF �Ǘ��҃I�u�W�F�N�g 
        l_manager.savePremium(l_premium, l_admin);
                
        //1.13  createResponse()
        WEB3AdminPointPremiumChangeCompleteResponse l_response = 
            (WEB3AdminPointPremiumChangeCompleteResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;     
    }
}
@
