head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.08.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoPasswordResetInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���Ïؔԍ����Z�b�g���̓��X�|���X(WEB3AdminAccInfoPasswordResetInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/16 ����� (���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��҂��q�l���Ïؔԍ����Z�b�g���̓��X�|���X)<BR>
 * �Ǘ��҂��q�l���Ïؔԍ����Z�b�g���̓��X�|���X<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AdminAccInfoPasswordResetInputResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_passwordResetInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082105L;

    /**
     * @@roseuid 418F385F0280
     */
    public WEB3AdminAccInfoPasswordResetInputResponse()
    {

    }

    /**
     * (�Ǘ��҂��q�l���Ïؔԍ����Z�b�g���̓��X�|���X)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - (l_request)<BR>
     * ���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordResetInputResponse
     * @@roseuid 416656F901B1
     */
    public WEB3AdminAccInfoPasswordResetInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
