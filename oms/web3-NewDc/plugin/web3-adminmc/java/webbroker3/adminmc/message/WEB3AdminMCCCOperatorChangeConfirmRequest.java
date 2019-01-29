head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.56.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCCCOperatorChangeConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��j���[����CC���ڰ��ύX�m�Fظ���(WEB3AdminMCCCOperatorChangeConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/29 �͌d�� (���u) �V�K�쐬
*/

package webbroker3.adminmc.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҃��j���[����CC���ڰ��ύX�m�Fظ���)<BR>
 * �Ǘ��҃��j���[����CC���ڰ��ύX�m�Fظ���<BR>
 * 
 * @@author �͌d��
 * @@version 1.0
 */
public class WEB3AdminMCCCOperatorChangeConfirmRequest extends WEB3GenRequest 
{
    /**         
     * ���O�o�̓��[�e�B���e�B�B         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMCCCOperatorChangeConfirmRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_CCOperatorChangeConfirm";

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
     * @@roseuid 419864240196
     */
    public WEB3AdminMCCCOperatorChangeConfirmRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@CC�I�y���[�^�o�^���̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@CC�I�y���[�^�o�^���.validate()���R�[������B<BR>
     * <BR>
     * @@roseuid 417E1F4A0008
     */
    public void validate() throws  WEB3BaseException 
    {
        final  String  STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        this.ccOperatorRegistUnit.validate();
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4198642401C5
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMCCCOperatorChangeConfirmResponse(this);
    }
}
@
