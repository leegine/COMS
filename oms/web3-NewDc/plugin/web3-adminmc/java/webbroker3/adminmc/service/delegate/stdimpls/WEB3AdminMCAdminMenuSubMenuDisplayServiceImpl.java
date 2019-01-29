head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.27.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminMenuSubMenuDisplayServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��j���[����T�u���j���[�\���T�[�r�XImpl(WEB3AdminMCAdminMenuSubMenuDisplayServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/23  �� �� �@@ (���u) �V�K�쐬
*/

package webbroker3.adminmc.service.delegate.stdimpls;


import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;

import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminMenuSubMenuDisplayService;
import webbroker3.adminmc.message.WEB3AdminMCAdminMenuSubMenuDisplayRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminMenuSubMenuDisplayResponse;
import webbroker3.adminmc.message.WEB3AdminMCTransactionCategoryUnit;


/**
 * (�Ǘ��҃��j���[����T�u���j���[�\���T�[�r�XImpl)<BR>
 * �Ǘ��҃��j���[����T�u���j���[�\���T�[�r�X�����N���X<BR>
 * @@author �����@@
 * @@version 1.0
 */

public class WEB3AdminMCAdminMenuSubMenuDisplayServiceImpl implements WEB3AdminMCAdminMenuSubMenuDisplayService 
{
    
    /**
     *���O�o�̓��[�e�B���e�B�B
     */
    private WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminMenuSubMenuDisplayServiceImpl.class);
    
    /**
     * @@roseuid 419864100222
     */
    public WEB3AdminMCAdminMenuSubMenuDisplayServiceImpl() 
    {
     
    }
    
    /**
     * �Ǘ��Ҍ����O���[�v�ꗗ�^�ڍ׏��������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҃��j���[����T�u���j���[�\�����N�G�X�g�̏ꍇ <BR>
     * �@@�|get�T�u���j���[()���R�[������B <BR>
     * <BR>
     * <BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4177693D02DE
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3AdminMCAdminMenuSubMenuDisplayRequest)
        {
            l_response = this.getSubMenu((WEB3AdminMCAdminMenuSubMenuDisplayRequest)l_request);
        
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                                      WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                                       STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�T�u���j���[)<BR>
     * �T�u���j���[�ŗ��p�ł���@@�\�J�e�S���R�[�h�ꗗ��ԋp����B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҃��j���[����i�T�u���j���[�\���jget�T�u���j���[�v�Q�ƁB <BR>
     * <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����T�u���j���[�\�����N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminMenuSubMenuDisplayResponse
     * @@roseuid 417768F00232
     */
    protected WEB3AdminMCAdminMenuSubMenuDisplayResponse getSubMenu(WEB3AdminMCAdminMenuSubMenuDisplayRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSubMenu(WEB3AdminMCAdminMenuSubMenuDisplayRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        l_request.validate();
        //1.2 getInstanceFrom���O�C�����()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        //1.3 get�������x��()
        String l_adminPermissionLevel = l_administartor.getPermissionLevel();
        //1.4 get�،���ЃR�[�h()
        String l_adminInstitutionCode = l_administartor.getInstitutionCode();
        //1.5 create�����\�@@�\�J�e�S���ꗗ        
        WEB3AdminMCAdminPermUnitCreateServiceImpl l_adminPermUnitCreateServiceImpl = new WEB3AdminMCAdminPermUnitCreateServiceImpl();      
        WEB3AdminMCTransactionCategoryUnit[] l_strQueryDataContainers 
            = l_adminPermUnitCreateServiceImpl.createOperatePossibleTransactionCategoryUnit(l_adminInstitutionCode, l_adminPermissionLevel, l_request.transactionCategoryList);     
        //1.6 �Ǘ��҃��j���[����T�u���j���[�\�����X�|���X(WEB3GenRequest)
        WEB3AdminMCAdminMenuSubMenuDisplayResponse l_response = (WEB3AdminMCAdminMenuSubMenuDisplayResponse)l_request.createResponse();
        //1.7 �v���p�e�B�Z�b�g
        l_response.transactionCategoryUnits = l_strQueryDataContainers;

        log.exiting(STR_METHOD_NAME);  
        return l_response;

    }
}
@
