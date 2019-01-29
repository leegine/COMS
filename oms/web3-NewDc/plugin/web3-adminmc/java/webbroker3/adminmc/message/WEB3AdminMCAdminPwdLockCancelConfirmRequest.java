head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.55.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminPwdLockCancelConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��j���[����Ǘ����߽ܰ��ۯ������m�Fظ���(WEB3AdminMCAdminPwdLockCancelConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/23  �� �� �@@ (���u) �V�K�쐬
*/

package webbroker3.adminmc.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҃��j���[����Ǘ����߽ܰ��ۯ������m�Fظ���)<BR>
 * �Ǘ��҃��j���[����Ǘ����߽ܰ��ۯ������m�Fظ���<BR>
 * @@author �����@@
 * @@version 1.0
 */

public class WEB3AdminMCAdminPwdLockCancelConfirmRequest extends WEB3GenRequest 
{
    /**         
     * ���O�o�̓��[�e�B���e�B�B         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMCAdminPwdLockCancelConfirmRequest.class); 
 
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_adminPwdLockCancelConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411171410L; 
    
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String branchCode;
    
    /**
     * (�Ǘ��҃R�[�h)<BR>
     * �Ǘ��҃R�[�h<BR>
     */
    public String administratorCode;
    
    /**
     * @@roseuid 41986421004E
     */
    public WEB3AdminMCAdminPwdLockCancelConfirmRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@���X�R�[�h�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00833          <BR>
     * <BR>
     * �Q�j�@@�Ǘ��҃R�[�h�̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_01215           <BR>
     * <BR>
     * @@roseuid 417DE37F0319
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";

        log.entering(STR_METHOD_NAME);
        //�P�j�@@���X�R�[�h�̃`�F�b�N<BR>
        //�@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
        //               class :  WEB3BusinessLayerException <BR>
        //               tag :    BUSINESS_ERROR_00833          <BR>
        if (this.branchCode == null)
        {
            log.error(" ���X�R�[�h������ .");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                            this.getClass().getName() + STR_METHOD_NAME);
            
        }  
                
        //�Q�j�@@�Ǘ��҃R�[�h�̃`�F�b�N<BR>
        //�@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
        //               class :  WEB3BusinessLayerException <BR>
        //               tag :    BUSINESS_ERROR_01215           <BR>    
        if (this.administratorCode == null)
        {
            log.error(" �Ǘ��҃R�[�h������ .");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01215,
                            this.getClass().getName() + STR_METHOD_NAME);
            
        } 
        
        log.exiting(STR_METHOD_NAME);  
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4198642100AB
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMCAdminPwdLockCancelConfirmResponse(this);
    }
}
@
