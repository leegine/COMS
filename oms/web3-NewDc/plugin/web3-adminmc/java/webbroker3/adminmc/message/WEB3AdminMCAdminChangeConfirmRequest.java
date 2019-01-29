head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.51.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminChangeConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright          : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name          : (�Ǘ��҃��j���[����Ǘ��ҕύX�m�F���N�G�X�g(WEB3AdminMCAdminChangeConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/24 ���q (���u) �V�K�쐬
*/

package webbroker3.adminmc.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.WEB3BaseException;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҃��j���[����Ǘ��ҕύX�m�F���N�G�X�g)<BR>
 * �Ǘ��҃��j���[����Ǘ��ҕύX�m�F���N�G�X�g<BR>
 * @@author ���q
 * @@version 1.0
 */
public class WEB3AdminMCAdminChangeConfirmRequest extends WEB3GenRequest 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private  static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminChangeConfirmRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_adminChangeConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411171410L;
        
    /**
     * (�Ǘ��ғo�^���)<BR>
     * �Ǘ��ғo�^���<BR>
     */
    public WEB3AdminMCAdminRegistUnit adminRegistUnit;
    
    /**
     * @@roseuid 4198641701A5
     */
    public WEB3AdminMCAdminChangeConfirmRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�Ǘ��ғo�^���̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�Ǘ��ғo�^���.validate()���R�[������B<BR>
     * <BR>
     * <BR>
     * @@roseuid 417CB5C702A9
     */
    public void validate() throws WEB3BaseException
    {
         final  String  STR_METHOD_NAME = " validate()";
         log.entering(STR_METHOD_NAME);
         
         adminRegistUnit.validate();
         
         log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4198641701C5
     */
    public WEB3GenResponse createResponse() 
    {
         return new WEB3AdminMCAdminChangeConfirmResponse(this);
    }
}
@
