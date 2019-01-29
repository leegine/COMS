head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.48.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCCCOperatorChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��j���[����CC���ڰ��ύX����ظ���(WEB3AdminMCCCOperatorChangeCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/29 �͌d�� (���u) �V�K�쐬
*/

package webbroker3.adminmc.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҃��j���[����CC���ڰ��ύX����ظ���)<BR>
 * �Ǘ��҃��j���[����CC���ڰ��ύX����ظ���<BR>
 * 
 * @@author �͌d��
 * @@version 1.0
 */
public class WEB3AdminMCCCOperatorChangeCompleteRequest extends WEB3GenRequest 
{
    /**         
     * ���O�o�̓��[�e�B���e�B�B         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMCCCOperatorChangeCompleteRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_CCOperatorChangeComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411171410L; 
    
    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�<BR>
     * <BR>
     */
    public String password;
    
    /**
     * (CC�I�y���[�^�o�^���)<BR>
     * CC�I�y���[�^�o�^���<BR>
     */
    public WEB3AdminMCCCOperatorRegistUnit ccOperatorRegistUnit;
    
    /**
     * @@roseuid 4198642302BF
     */
    public WEB3AdminMCCCOperatorChangeCompleteRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@CC�I�y���[�^�o�^���̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@CC�I�y���[�^�o�^���.validate()���R�[������B<BR>
     * <BR>
     * @@roseuid 417E1F0E016F
     */
    public void validate() throws  WEB3BaseException  
    {
        final  String  STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        // �P�j�@@CC�I�y���[�^�o�^���̃`�F�b�N
        // �@@�P�|�P�j�@@CC�I�y���[�^�o�^���.validate()���R�[������B       
        this.ccOperatorRegistUnit.validate();        
        
        log.exiting(STR_METHOD_NAME);          
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41986423033C
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMCCCOperatorChangeCompleteResponse(this);
    }
}
@
