head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFaqListInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Җ⍇���Ǘ����⍇���ꗗ���̓��N�G�X�g(WEB3AdminFaqListInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/23 ����� (���u) �V�K�쐬
*/

package webbroker3.faq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��Җ⍇���Ǘ����⍇���ꗗ���̓��N�G�X�g)<BR>
 * �Ǘ��Җ⍇���Ǘ����⍇���ꗗ���̓��N�G�X�g<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AdminFaqListInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_faq_listInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412171304L;

    /**
     * @@roseuid 41C25C07038A
     */
    public WEB3AdminFaqListInputRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFaqListInputResponse(this);
    }
}
@
