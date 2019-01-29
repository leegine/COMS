head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.51.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointPremiumDeleteServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�i�폜�T�[�r�XImpl(WEB3AdminPointPremiumDeleteServiceImpl.java)
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
import webbroker3.point.WEB3PointApplyManager;
import webbroker3.point.WEB3PointPremium;
import webbroker3.point.WEB3PointProductManager;
import webbroker3.point.data.PointPremiumMasterRow;
import webbroker3.point.message.WEB3AdminPointPremiumDeleteCompleteRequest;
import webbroker3.point.message.WEB3AdminPointPremiumDeleteCompleteResponse;
import webbroker3.point.message.WEB3AdminPointPremiumDeleteConfirmRequest;
import webbroker3.point.message.WEB3AdminPointPremiumDeleteConfirmResponse;
import webbroker3.point.service.delegate.WEB3AdminPointPremiumDeleteService;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i�i�폜�T�[�r�XImpl)<BR>
 * �i�i�폜�T�[�r�X�����N���X<BR>
 *
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AdminPointPremiumDeleteServiceImpl implements WEB3AdminPointPremiumDeleteService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPointPremiumDeleteServiceImpl.class);

    /**
     * �i�i�폜�T�[�r�X�������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Avalidate�폜()�܂���submit�폜()<BR>
     * ���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3GenResponse
     * @@roseuid 4193451F0369
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);        
        
        if (l_request instanceof WEB3AdminPointPremiumDeleteConfirmRequest)
        {
            log.debug("WEB3AdminPointPremiumDeleteConfirmRequest");
            WEB3AdminPointPremiumDeleteConfirmResponse l_response = 
                validateDelete((WEB3AdminPointPremiumDeleteConfirmRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminPointPremiumDeleteCompleteRequest)
        {
            log.debug("WEB3AdminPointPremiumDeleteCompleteRequest");
            WEB3AdminPointPremiumDeleteCompleteResponse l_response = 
                submitDelete((WEB3AdminPointPremiumDeleteCompleteRequest)l_request);
        
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
     * �u�i�i�i�폜�jvalidate�폜�v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointPremiumDeleteConfirmResponse
     * @@roseuid 4193451F036B
     */
    protected WEB3AdminPointPremiumDeleteConfirmResponse validateDelete(WEB3AdminPointPremiumDeleteConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateDelete(WEB3AdminPointPremiumDeleteConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
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
        
        //1.4 get�\������(String, String)
        //[����] 
        //�،���ЃR�[�h�F �Ǘ���.get�،���ЃR�[�h()�̖߂�l 
        //�i�i�ԍ��F ���N�G�X�g�f�[�^.�i�i�ԍ� 
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        WEB3PointApplyManager l_applyManager = (WEB3PointApplyManager) 
            Services.getService(WEB3PointApplyManager.class);
            
        if (l_applyManager == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
            
        long l_lngApplyNumber = l_applyManager.getApplyNumber(
            l_strInstitutionCode, l_request.premiumNo);
        
        //1.5 get�i�i(String, String)
        //[����] 
        //�،���ЃR�[�h�F �Ǘ���.get�،���ЃR�[�h()�̖߂�l 
        //�i�i�ԍ��F ���N�G�X�g�f�[�^.�i�i�ԍ� 
        WEB3PointProductManager l_productManager = (WEB3PointProductManager)
            Services.getService(WEB3PointProductManager.class);
            
        if (l_productManager == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
            
        WEB3PointPremium l_premium = l_productManager.getPremium(l_strInstitutionCode, l_request.premiumNo);
        
        if (l_premium == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.6 createResponse()
        WEB3AdminPointPremiumDeleteConfirmResponse l_response = 
            (WEB3AdminPointPremiumDeleteConfirmResponse)l_request.createResponse();
        if (l_response == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.7 �v���p�e�B�Z�b�g
        //�\������
        l_response.applyCount = new Long(l_lngApplyNumber).toString();
        
        //�i�i��
        l_response.premiumName = l_premium.getPremiumName();
        
        //�K�v�|�C���g
        l_response.requiredPoint = new Long(l_premium.getRequiredPoint()).toString();
        
        //�񋟊J�n����
        l_response.startDate = l_premium.getStartDateTime();
        
        //�񋟏I������
        l_response.endDate = l_premium.getEndDateTime();
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (submit�폜)<BR>
     * �i�i�̍폜���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�i�i�폜�jsubmit�폜�v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointPremiumDeleteCompleteResponse
     * @@roseuid 4193451F036D
     */
    protected WEB3AdminPointPremiumDeleteCompleteResponse submitDelete(WEB3AdminPointPremiumDeleteCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitDelete(WEB3AdminPointPremiumDeleteCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
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
        
        WEB3PointProductManager l_productManager = (WEB3PointProductManager)
            Services.getService(WEB3PointProductManager.class);
            
        if (l_productManager == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
            
        WEB3PointPremium l_premium = l_productManager.getPremium(l_strInstitutionCode, l_request.premiumNo);
        
        if (l_premium == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.6  deleteRow(Row)
        //[����] 
        //�i�i�s�F �|�C���g�i�i.getDataSourceObject()�̖߂�l 
        PointPremiumMasterRow l_row = (PointPremiumMasterRow)l_premium.getDataSourceObject();
        try
        {
            WEB3DataAccessUtility.deleteRow(l_row);//DataQueryException, DataNetworkException
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
        
        //1.7 createResponse()
        WEB3AdminPointPremiumDeleteCompleteResponse l_response = 
            (WEB3AdminPointPremiumDeleteCompleteResponse)l_request.createResponse();       
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
}
@
