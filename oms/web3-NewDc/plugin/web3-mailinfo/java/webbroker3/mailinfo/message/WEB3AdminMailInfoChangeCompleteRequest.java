head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.14.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7844d7ef4f02f89;
filename	WEB3AdminMailInfoChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���[�����ύX�������N�G�X�g(WEB3AdminMailInfoChangeCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  鰊](���u) �V�K�쐬
*/
package webbroker3.mailinfo.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (���[�����ύX�������N�G�X�g)<BR>
 * ���[�����ύX�������N�G�X�g�N���X<BR>
 * 
 * @@author 鰊]
 * @@version 1.0
 */
public class WEB3AdminMailInfoChangeCompleteRequest extends WEB3AdminMailInfoCommonRequest
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
     * (�Ïؔԍ�)<BR>
     */
    public String password;

    /**
     * @@roseuid 416F1DCE001F
     */
    public WEB3AdminMailInfoChangeCompleteRequest()
    {

    }

    /**
     * (create���X�|���X)<BR>
     * ���[�����ύX�������X�|���X�I�u�W�F�N�g��ԋp����B<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 413C1326035B
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminMailInfoChangeCompleteResponse();
    }
}
@
