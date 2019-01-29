head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.54.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminMenuSubMenuDisplayRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��j���[����T�u���j���[�\�����N�G�X�g(WEB3AdminMCAdminMenuSubMenuDisplayRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/23  �� �� �@@ (���u) �V�K�쐬
*/

package webbroker3.adminmc.message;

import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��҃��j���[����T�u���j���[�\�����N�G�X�g)<BR>
 * �Ǘ��҃��j���[����T�u���j���[�\�����N�G�X�g<BR>
 * @@author �����@@
 * @@version 1.0
 */
 
public class WEB3AdminMCAdminMenuSubMenuDisplayRequest extends WEB3GenRequest 
{
    /**         
     * ���O�o�̓��[�e�B���e�B�B         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMCAdminMenuSubMenuDisplayRequest.class); 

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_adminMenuSubMenuDisplay";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411171410L;
    
    /**
     * (�@@�\�J�e�S���R�[�h�ꗗ)<BR>
     * �@@�\�J�e�S���R�[�h�ꗗ<BR>
     * <BR>
     */
    public String[] transactionCategoryList;
    
    /**
     * @@roseuid 4198641A0177
     */
    public WEB3AdminMCAdminMenuSubMenuDisplayRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�@@�\�J�e�S���R�[�h�ꗗ�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�C�z��̃T�C�Y��0�̏ꍇ�A��O���X���[����B<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :   BUSINESS_ERROR_01214            <BR>
     * <BR>
     * @@roseuid 41773E510119
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";

        log.entering(STR_METHOD_NAME);
        // �P�j�@@�@@�\�J�e�S���R�[�h�ꗗ�̃`�F�b�N<BR>
        // �@@�P�|�P�j�@@�����͂̏ꍇ�C�z��̃T�C�Y��0�̏ꍇ�A��O���X���[����B<BR>
        //                class :  WEB3BusinessLayerException <BR>
        //                tag :   BUSINESS_ERROR_01214            <BR> 
         
        
        
        if (this.transactionCategoryList == null)
        {
            log.error(" �@@�\�J�e�S���R�[�h�ꗗ�����͂̏ꍇ .");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01214,
                            this.getClass().getName() + STR_METHOD_NAME);
            
        } 
        int l_intListCount = this.transactionCategoryList.length;
        
        if(l_intListCount == 0)
        {
            log.error(" �@@�\�J�e�S���R�[�h�ꗗ�z��̃T�C�Y��0�̏ꍇ .");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01214,
                            this.getClass().getName() + STR_METHOD_NAME);
            
        }         
        log.exiting(STR_METHOD_NAME);      
           
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4198641A0196
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMCAdminMenuSubMenuDisplayResponse(this);
    }
}
@
