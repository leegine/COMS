head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.06.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenApplySearchInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�ݐ\���������̓��N�G�X�g(WEB3AdminAccOpenApplySearchInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/22 �A�C��(���u) �V�K�쐬
*/

package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��Ҍ����J�ݐ\���������̓��N�G�X�g)<BR>
 * �Ǘ��Ҍ����J�ݐ\���������̓��N�G�X�g<BR>
 *   
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminAccOpenApplySearchInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accOpen_applySearchInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412081619L;

    /**
     * @@roseuid 41B45E7B035B
     */
    public WEB3AdminAccOpenApplySearchInputRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccOpenApplySearchInputResponse(this);
    }
}
@
