head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.00.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoExclusiveTransferAccountUploadInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l����p�U�����������۰�ޓ���ظ���(WEB3AdminAccInfoExclusiveTransferAccountUploadInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 �J�N���V (���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��҂��q�l����p�U�����������۰�ޓ���ظ���)<BR>
 * �Ǘ��҂��q�l����p�U�����������۰�ޓ���ظ���<BR>
 * @@author �J�N���V
 * @@version 1.0
 */
public class WEB3AdminAccInfoExclusiveTransferAccountUploadInputRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_exclusiveTransferAccountUploadInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082135L;

    /**
     * @@roseuid 418F3868001F
     */
    public WEB3AdminAccInfoExclusiveTransferAccountUploadInputRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoExclusiveTransferAccountUploadInputResponse(this);
    }
}
@
