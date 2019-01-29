head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.48.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminChangeInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright          : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name          : (�Ǘ��҃��j���[����Ǘ��ҕύX���̓��N�G�X�g(WEB3AdminMCAdminChangeInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/24 ���q (���u) �V�K�쐬
*/

package webbroker3.adminmc.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҃��j���[����Ǘ��ҕύX���̓��N�G�X�g)<BR>
 * �Ǘ��҃��j���[����Ǘ��ҕύX���̓��N�G�X�g<BR>
 * @@author ���q
 * @@version 1.0
 */
public class WEB3AdminMCAdminChangeInputRequest extends WEB3GenRequest 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private  static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminChangeInputRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_adminChangeInput";

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
     * @@roseuid 41986417032C
     */
    public WEB3AdminMCAdminChangeInputRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@���X�R�[�h�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00833           <BR>
     * <BR>
     * �Q�j�@@�Ǘ��҃R�[�h�̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_01215           <BR>
     * <BR>
     * @@roseuid 417CB5F4026B
     */
    public void validate() throws WEB3BaseException
    {
         final  String  STR_METHOD_NAME = " validate()";
         log.entering(STR_METHOD_NAME);
       
         //�P�j ���X�R�[�h�̃`�F�b�N
         //�����͂̏ꍇ�A��O���X���[����B
         if (this.branchCode == null || "".equals(this.branchCode))
         {
             //��O
             log.error("�u���X�R�[�h�����́v�̗�O���X���[����B");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                                                                   this.getClass().getName() + STR_METHOD_NAME);
         }
         //�Q�j �Ǘ��҃R�[�h�̃`�F�b�N
         //�����͂̏ꍇ�A��O���X���[����B
         if (this.administratorCode == null || "".equals(this.administratorCode))
         {
             //��O
             log.error("�u�Ǘ��҃R�[�h�����́v�̗�O���X���[����B");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01215,
                                                                   this.getClass().getName() + STR_METHOD_NAME);
         }
         
         log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4198641703A9
     */
    public WEB3GenResponse createResponse() 
    {
         return new WEB3AdminMCAdminChangeInputResponse(this);
    }
}
@
