head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.52.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointPremiumReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�i�ꗗ�T�[�r�XImpl(WEB3AdminPointPremiumReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 ���w��(���u) �V�K�쐬
*/
package webbroker3.point.service.delegate.stdimpls;

import java.util.ArrayList;

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
import webbroker3.point.message.WEB3AdminPointPremiumDetail;
import webbroker3.point.message.WEB3AdminPointPremiumReferenceRequest;
import webbroker3.point.message.WEB3AdminPointPremiumReferenceResponse;
import webbroker3.point.service.delegate.WEB3AdminPointPremiumReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i�i�ꗗ�T�[�r�XImpl)<BR>
 * �i�i�ꗗ�T�[�r�X�����N���X<BR>
 *
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AdminPointPremiumReferenceServiceImpl implements WEB3AdminPointPremiumReferenceService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPointPremiumReferenceServiceImpl.class);

    /**
     * �i�i�ꗗ�T�[�r�X�������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�i�i�ꗗ�j�ꗗ�\���f�[�^�擾�v �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3GenResponse
     * @@roseuid 4192D3D302AC
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);  
        
        if (!(l_request instanceof WEB3AdminPointPremiumReferenceRequest))
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        WEB3AdminPointPremiumReferenceRequest l_referenceRequest = 
            (WEB3AdminPointPremiumReferenceRequest)l_request;
            
        //1.1 validate( )
        l_referenceRequest.validate();
                
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
        l_admin.validateAuthority(WEB3TransactionCategoryDef.POINT_PREMIUM_MANAGE, false);
        
        //1.4 get�i�i(String, String)
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
            
        WEB3PointPremium[] l_premiums = l_manager.getPremiums(l_strInstitutionCode, l_referenceRequest.categoryNo);
        
        int l_intLength = 0;
        if (l_premiums != null)
        {
            l_intLength = l_premiums.length;
        }
        
        //1.5 ArrayList()
        ArrayList l_arrayList = new ArrayList();
        
        //1.6 �擾�����i�i�I�u�W�F�N�g����Loop        
        for (int i = 0; i < l_intLength; i++)
        {
            //1.6.1 �i�i��񖾍�()
            WEB3AdminPointPremiumDetail l_detail = new WEB3AdminPointPremiumDetail();
            
            //1.6.2 �v���p�e�B�Z�b�g
            //�i�i�ԍ�
            if (l_premiums[i] == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_detail.premiumNo = l_premiums[i].getPremiumNo();
            
            //�i�i�ԍ�
            l_detail.premiumName = l_premiums[i].getPremiumName();
            
            //�K�v�|�C���g
            l_detail.requiredPoint = new Long(l_premiums[i].getRequiredPoint()).toString();
            
            //�񋟊J�n����
            l_detail.startDate = l_premiums[i].getStartDateTime();
            
            //�񋟏I������
            l_detail.endDate = l_premiums[i].getEndDateTime();
            
            //1.6.3 add(arg0 : Object)
            l_arrayList.add(l_detail);  
        }
        
        //1.7 toArray()
        WEB3AdminPointPremiumDetail[] l_details = new WEB3AdminPointPremiumDetail[l_arrayList.size()];       
        l_arrayList.toArray(l_details);
        
        //1.8 createResponse()
        WEB3AdminPointPremiumReferenceResponse l_response = 
            (WEB3AdminPointPremiumReferenceResponse)l_request.createResponse();
            
        //1.9 �v���p�e�B�Z�b�g       
        if (l_response == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        l_response.premiumList = l_details;
                
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
}
@
