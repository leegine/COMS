head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.52.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCCCOperatorDeleteCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��j���[����CC���ڰ��폜����ظ���(WEB3AdminMCCCOperatorDeleteCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/23 �͌d�� (���u) �V�K�쐬
*/

package webbroker3.adminmc.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҃��j���[����CC���ڰ��폜����ظ���)<BR>
 * �Ǘ��҃��j���[����CC���ڰ��폜����ظ���<BR>
 * 
 * @@author �͌d��
 * @@version 1.0
 */
public class WEB3AdminMCCCOperatorDeleteCompleteRequest extends WEB3GenRequest 
{
    /**         
     * ���O�o�̓��[�e�B���e�B�B         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMCCCOperatorDeleteCompleteRequest.class);    

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_CCOperatorDeleteComplete";

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
     * (�I�y���[�^�R�[�h)<BR>
     * �I�y���[�^�R�[�h<BR>
     */
    public String operatorCode;
    
    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�<BR>
     * <BR>
     */
    public String password;
    
    /**
     * @@roseuid 4198642501C5
     */
    public WEB3AdminMCCCOperatorDeleteCompleteRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@���X�R�[�h�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00833            <BR>
     * <BR>
     * �Q�j�@@�I�y���[�^�R�[�h�̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag : BUSINESS_ERROR_01192             <BR>
     * <BR>
     * @@roseuid 417E1FF202D6
     */
    public void validate() throws  WEB3BaseException 
    {
        final  String  STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@���X�R�[�h�̃`�F�b�N
        // �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (this.branchCode == null || "".equals(this.branchCode))
        {
            log.error("�u���X�R�[�h�������͂ł���v�̗�O���X���[����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + STR_METHOD_NAME);            
        }

        // �Q�j�@@�I�y���[�^�R�[�h�̃`�F�b�N
        // �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (this.operatorCode == null || "".equals(this.operatorCode))
        {
            log.error("�u�I�y���[�^�R�[�h�������͂ł���v�̗�O���X���[����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01192,
                this.getClass().getName() + STR_METHOD_NAME);            
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4198642501F4
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMCCCOperatorDeleteCompleteResponse(this);
    }
}
@