head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.50.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminPermGrpChangeInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ύX���̓��N�G�X�g(WEB3AdminMCAdminPermGrpChangeInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/18 ���z (���u) �V�K�쐬 
*/

package webbroker3.adminmc.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ύX���̓��N�G�X�g)<BR>
 * �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ύX���̓��N�G�X�g<BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AdminMCAdminPermGrpChangeInputRequest extends WEB3GenRequest 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_admin_perm_grp_change_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411181540L;
    
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMCAdminPermGrpChangeInputRequest.class);
    
    /**
     * (�������x���R�[�h)<BR>
     * �������x���R�[�h<BR>
     */
    public String permissionLevel;
    
    /**
     * @@roseuid 4198641C008C
     */
    public WEB3AdminMCAdminPermGrpChangeInputRequest() 
    {
         
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�������x���R�[�h�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :     BUSINESS_ERROR_01201          <BR>
     * �@@�P�|�Q�j�@@��������3byte�łȂ��ꍇ�A��O���X���[����B<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :     BUSINESS_ERROR_01202          <BR>
�@@   *   �P�|�R�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :     BUSINESS_ERROR_01203          <BR>
     * <BR>
     * @@roseuid 4177211102EE
     */
    public void validate() throws WEB3BaseException
    {
        String l_strMethodName = "validate()";
        log.entering(l_strMethodName);
        
        //�P�j�������x���R�[�h�̃`�F�b�N
        //�P�|�P�j�����͂̏ꍇ�A��O���X���[����B
        //       class:WEB3BusinessLayerException 
        //         tag:BUSINESS_ERROR_01201                
        if (this.permissionLevel == null || "".equals(this.permissionLevel))
        {
            log.error("�������x���R�[�h������");
            log.exiting(l_strMethodName);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01201,
                this.getClass().getName() + "." + l_strMethodName);                
        }        
        
        // �@@�P�|�Q�j�@@��������3byte�łȂ��ꍇ�A��O���X���[����B
        //                class :  WEB3BusinessLayerException 
        //                tag :     BUSINESS_ERROR_01202   
        if (WEB3StringTypeUtility.getByteLength(this.permissionLevel) != 3)
        {
            log.error(" ��������3byte�łȂ� .");
            log.exiting(l_strMethodName);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01202,
                this.getClass().getName() + "." + l_strMethodName);
        }   
        
        //    �P�|�R�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B
        //                 class :  WEB3BusinessLayerException 
        //                 tag :     BUSINESS_ERROR_01203          
        if (!WEB3StringTypeUtility.isNumber(this.permissionLevel))
        {
            log.error(" �����ȊO�̕������܂܂��.");
            log.exiting(l_strMethodName);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01203,
                this.getClass().getName() + "." + l_strMethodName);
        } 
        
        log.exiting(l_strMethodName);                
               
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4198641C00AB
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMCAdminPermGrpChangeInputResponse(this);
    }
}
@
