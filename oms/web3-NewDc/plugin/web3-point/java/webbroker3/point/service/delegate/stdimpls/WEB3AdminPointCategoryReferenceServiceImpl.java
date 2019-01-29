head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.52.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointCategoryReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �J�e�S���[�ꗗ�T�[�r�XImpl(WEB3AdminPointCategoryReferenceServiceImpl.java)
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
import webbroker3.point.WEB3PointCategory;
import webbroker3.point.WEB3PointProductManager;
import webbroker3.point.message.WEB3AdminPointCategoryReferenceRequest;
import webbroker3.point.message.WEB3AdminPointCategoryReferenceResponse;
import webbroker3.point.message.WEB3AdminPointCategoryUnit;
import webbroker3.point.service.delegate.WEB3AdminPointCategoryReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�J�e�S���[�ꗗ�T�[�r�XImpl)<BR>
 * �J�e�S���[�ꗗ�T�[�r�X�����N���X<BR>
 *
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AdminPointCategoryReferenceServiceImpl implements WEB3AdminPointCategoryReferenceService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPointCategoryReferenceServiceImpl.class);

    /**
     * �J�e�S���[�ꗗ�T�[�r�X�������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�J�e�S���[�ꗗ�j�ꗗ�\���f�[�^�擾�v �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3GenResponse
     * @@roseuid 41877C33032C
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);  
        
        if (!(l_request instanceof WEB3AdminPointCategoryReferenceRequest))
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
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
        l_admin.validateAuthority(WEB3TransactionCategoryDef.POINT_PREMIUM_MANAGE, false);
        
        //1.3 get�J�e�S���[(String)
        //�،���ЃR�[�h�F �Ǘ���.get�،���ЃR�[�h()�̖߂�l 
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
        
        WEB3PointCategory[] l_pointCategorys = l_manager.getCategories(l_strInstitutionCode);

        //1.4 ArrayList()
        ArrayList l_arrayList = new ArrayList();
        
        int l_intLength = 0;
                
        if (l_pointCategorys != null )
        {
            l_intLength = l_pointCategorys.length;
        }
        
        //1.5 �擾�����J�e�S���[�I�u�W�F�N�g����Loop 
        for(int i = 0; i < l_intLength; i++)
        {
        
            //1.5.1 �J�e�S���[����()
            WEB3AdminPointCategoryUnit l_pointCategoryUnit = new WEB3AdminPointCategoryUnit();
            
            //1.5.2 �v���p�e�B�Z�b�g
            //�J�e�S���[��
            l_pointCategoryUnit.categoryName = l_pointCategorys[i].getCategoryName();
            
            //�J�e�S���[�ԍ�
            l_pointCategoryUnit.categoryNo = new Long(l_pointCategorys[i].getCategoryNo()).toString();
            
            //�J�e�S���[�T�v
            l_pointCategoryUnit.categoryOutline = l_pointCategorys[i].getCategoryOutline();
            
            //1.5.3  add(arg0 : Object)
            l_arrayList.add(l_pointCategoryUnit);
        }
        
        //1.6 toArray()       
        WEB3AdminPointCategoryUnit[] l_pointCategoryUnit = new WEB3AdminPointCategoryUnit[l_arrayList.size()];
        l_arrayList.toArray(l_pointCategoryUnit);
        
        //1.7 createResponse()
        WEB3AdminPointCategoryReferenceRequest l_referenceRequest = 
            (WEB3AdminPointCategoryReferenceRequest)l_request;
        
        WEB3AdminPointCategoryReferenceResponse l_response = 
            (WEB3AdminPointCategoryReferenceResponse)l_referenceRequest.createResponse();
        if (l_response == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //(*2)�v���p�e�B�Z�b�g
        l_response.categoryList = l_pointCategoryUnit;
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
}
@
