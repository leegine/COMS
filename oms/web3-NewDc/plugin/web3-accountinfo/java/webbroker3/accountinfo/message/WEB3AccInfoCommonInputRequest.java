head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.05.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoCommonInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l��񋤒ʓ��̓��N�G�X�g(WEB3AccInfoCommonInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 ����� (���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (���q�l��񋤒ʓ��̓��N�G�X�g)<BR>
 * ���q�l��񋤒ʓ��̓��N�G�X�g<BR>
 * <BR>
 * ���ȉ��̉�ʂŋ��ʂɎg�p����B<BR>
 * �@@�|���[���A�h���X�ύX���͏���<BR>
 * �@@�|�ē����[���z�M�ݒ���́^�m�F����<BR>
 * �@@�|���^����胁�[���z�M�ݒ�ύX���́^�m�F����<BR>
 * <BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AccInfoCommonInputRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accInfo_commonInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082163L;

    /**
     * @@roseuid 418F39EF0290
     */
    public WEB3AccInfoCommonInputRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AccInfoCommonInputResponse(this);
    }
}
@
