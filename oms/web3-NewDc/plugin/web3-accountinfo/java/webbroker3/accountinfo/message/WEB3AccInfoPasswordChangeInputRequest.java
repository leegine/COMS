head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.06.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoPasswordChangeInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l���Ïؔԍ��ύX���̓��N�G�X�g(WEB3AccInfoPasswordChangeInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/12 ����� (���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (���q�l���Ïؔԍ��ύX���̓��N�G�X�g)<BR>
 * ���q�l���Ïؔԍ��ύX���̓��N�G�X�g<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AccInfoPasswordChangeInputRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accInfo_passwordChangeInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082151L;

    /**
     * @@roseuid 418F39F70148
     */
    public WEB3AccInfoPasswordChangeInputRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AccInfoPasswordChangeInputResponse(this);
    }
}
@
