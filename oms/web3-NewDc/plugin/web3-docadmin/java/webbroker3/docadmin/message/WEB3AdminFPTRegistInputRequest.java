head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTRegistInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҋ����@@��t�{���o�^���̓��N�G�X�g(WEB3AdminFPTRegistInputRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/29 ���g (���u) �V�K�쐬
*/

package webbroker3.docadmin.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҋ����@@��t�{���o�^���̓��N�G�X�g)<BR>
 * �Ǘ��ҋ����@@��t�{���o�^���̓��N�G�X�g�N���X<BR>
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AdminFPTRegistInputRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_fpt_regist_input";

    /**
     * SerialVersionUID<BR>
     */
    private static final long serialVersionUID = 200709291417L;

    /**
     * @@roseuid 46FDDD3B0109
     */
    public WEB3AdminFPTRegistInputRequest()
    {

    }

    /**
     * (create���X�|���X)<BR>
     * (createResponse����)<BR>
     * <BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFPTRegistInputResponse(this);
    }
}
@
