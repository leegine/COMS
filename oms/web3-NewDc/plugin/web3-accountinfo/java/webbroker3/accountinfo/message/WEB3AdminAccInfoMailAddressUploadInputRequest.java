head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.09.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressUploadInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l��񃁁[���A�h���X�A�b�v���[�h���̓��N�G�X�g(WEB3AdminAccInfoMailAddressUploadInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/14 ���̖N (���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��҂��q�l��񃁁[���A�h���X�A�b�v���[�h���̓��N�G�X�g)<BR>
 * �Ǘ��҂��q�l��񃁁[���A�h���X�A�b�v���[�h���̓��N�G�X�g<BR>
 * 
 * @@author ���̖N(���u)
 * @@version 1.0
 */
public class WEB3AdminAccInfoMailAddressUploadInputRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mailAddressUploadInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603141715L;

    /**
     * @@roseuid 418F3857000F
     */
    public WEB3AdminAccInfoMailAddressUploadInputRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoMailAddressUploadInputResponse(this);
    }
}
@
