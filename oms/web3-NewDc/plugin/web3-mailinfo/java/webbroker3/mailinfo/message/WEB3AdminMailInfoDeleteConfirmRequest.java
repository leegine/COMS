head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.15.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7844d7ef4f02f89;
filename	WEB3AdminMailInfoDeleteConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���[�����폜�m�F���N�G�X�g(WEB3AdminMailInfoDeleteConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  鰊](���u) �V�K�쐬
*/
package webbroker3.mailinfo.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (���[�����폜�m�F���N�G�X�g)<BR>
 * ���[�����폜�m�F���N�G�X�g�N���X<BR>
 * 
 * @@author 鰊]
 * @@version 1.0
 */
public class WEB3AdminMailInfoDeleteConfirmRequest extends WEB3AdminMailInfoDeleteCommonRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_MailInfo_deleteConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151310L;

    /**
     * @@roseuid 416F1DCF029F
     */
    public WEB3AdminMailInfoDeleteConfirmRequest()
    {

    }

    /**
     * (create���X�|���X)<BR>
     * ���[�����폜�m�F���X�|���X�I�u�W�F�N�g��ԋp����B<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 413C0FA20157
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminMailInfoDeleteConfirmResponse(this);
    }
}
@
