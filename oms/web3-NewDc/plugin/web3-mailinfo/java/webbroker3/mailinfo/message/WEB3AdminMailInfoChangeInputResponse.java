head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.13.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7844d7ef4f02f89;
filename	WEB3AdminMailInfoChangeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���[�����ύX���͉�ʃ��X�|���X(WEB3AdminMailInfoChangeInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  鰊](���u) �V�K�쐬
*/

package webbroker3.mailinfo.message;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (���[�����ύX���͉�ʃ��X�|���X)<BR>
 * ���[�����ύX���͉�ʃ��X�|���X�N���X<BR>
 * 
 * @@author 鰊]
 * @@version 1.0
 */
public class WEB3AdminMailInfoChangeInputResponse extends WEB3AdminMailInfoCommonResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_MailInfo_changeInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151310L;

    /**
     * (���[�����ύX���͉�ʃ��X�|���X)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 414165B2015F
     */
    public WEB3AdminMailInfoChangeInputResponse()
    {

    }

    /**
     * (���[�����ύX���͉�ʃ��X�|���X)<BR>
     * �f�t�H���g�R���X�g���N�^ �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminMailInfoChangeInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
