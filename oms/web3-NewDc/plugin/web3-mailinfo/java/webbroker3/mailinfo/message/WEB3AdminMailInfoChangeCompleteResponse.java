head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.14.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7844d7ef4f02f89;
filename	WEB3AdminMailInfoChangeCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���[�����ύX�������X�|���X(WEB3AdminMailInfoChangeCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  鰊](���u) �V�K�쐬
*/
package webbroker3.mailinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (���[�����ύX�������X�|���X)<BR>
 * ���[�����ύX�������X�|���X�N���X<BR>
 * 
 * @@author 鰊]
 * @@version 1.0
 */
public class WEB3AdminMailInfoChangeCompleteResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_MailInfo_changeComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151310L;

    /**
     * (���[�����ύX�������X�|���X)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 413C19AC0280
     */
    public WEB3AdminMailInfoChangeCompleteResponse()
    {

    }

    /**
     * (���[�����ύX�������X�|���X)<BR>
     * �f�t�H���g�R���X�g���N�^ �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminMailInfoChangeCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
