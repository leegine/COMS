head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.03.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoPasswordResetResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���Ïؔԍ����Z�b�g���X�|���X(WEB3AdminAccInfoPasswordResetResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/16 ����� (���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��҂��q�l���Ïؔԍ����Z�b�g���X�|���X)<BR>
 * �Ǘ��҂��q�l���Ïؔԍ����Z�b�g���X�|���X<BR>
 * 
 * @@author �����
 * @@version 1.0 
 */
public class WEB3AdminAccInfoPasswordResetResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_infoPasswordReset";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082104L;

    /**
     * @@roseuid 418F385B029F
     */
    public WEB3AdminAccInfoPasswordResetResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminAccInfoPasswordResetResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
