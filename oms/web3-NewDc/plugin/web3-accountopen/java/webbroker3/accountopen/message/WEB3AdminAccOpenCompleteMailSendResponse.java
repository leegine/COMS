head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.06.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenCompleteMailSendResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�݊������[�����M���X�|���X(WEB3AdminAccOpenCompleteMailSendResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/14 ���w�� �V�K�쐬
*/

package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��Ҍ����J�݊������[�����M���X�|���X)<BR>
 * �Ǘ��Ҍ����J�݊������[�����M���X�|���X<BR>
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AdminAccOpenCompleteMailSendResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accOpen_completeMailSend";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412081608L;

    /**
     * @@roseuid 41B45E750290
     */
    public WEB3AdminAccOpenCompleteMailSendResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminAccOpenCompleteMailSendResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
