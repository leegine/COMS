head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.53.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCCCOperatorRegistConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��j���[����CC���ڰ��o�^�m�Fظ���(WEB3AdminMCCCOperatorRegistConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/24 �͌d�� (���u) �V�K�쐬
*/


package webbroker3.adminmc.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҃��j���[����CC���ڰ��o�^�m�Fظ���)<BR>
 * �Ǘ��҃��j���[����CC���ڰ��o�^�m�Fظ���<BR>
 * 
 * @@author �͌d��
 * @@version 1.0
 */
public class WEB3AdminMCCCOperatorRegistConfirmRequest extends WEB3GenRequest 
{
    /**         
     * ���O�o�̓��[�e�B���e�B�B         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMCCCOperatorRegistConfirmRequest.class); 

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_CCOperatorRegistConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411171410L; 
    
    /**
     * (CC�I�y���[�^�o�^���)<BR>
     * CC�I�y���[�^�o�^���<BR>
     */
    public WEB3AdminMCCCOperatorRegistUnit ccOperatorRegistUnit;
    
    /**
     * @@roseuid 41986428035B
     */
    public WEB3AdminMCCCOperatorRegistConfirmRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@CC�I�y���[�^�o�^���̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@CC�I�y���[�^�o�^���.validate()���R�[������B<BR>
     *   �P�|�Q�j�@@CC�I�y���[�^�o�^���.validate�p�X���[�h()���R�[������B<BR>
     * <BR>
     * @@roseuid 417E1D4602A8
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        this.ccOperatorRegistUnit.validate();
        this.ccOperatorRegistUnit.validatePassword();
        
        log.exiting(STR_METHOD_NAME);             
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4198642803A9
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMCCCOperatorRegistConfirmResponse(this);
    }
}
@
