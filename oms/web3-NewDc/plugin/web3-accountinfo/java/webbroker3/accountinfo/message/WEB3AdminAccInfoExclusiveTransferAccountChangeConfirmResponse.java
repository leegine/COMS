head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.06.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoExclusiveTransferAccountChangeConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l����p�U��������ύX�m�Fڽ��ݽ(WEB3AdminAccInfoExclusiveTransferAccountChangeConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 �J�N���V (���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��҂��q�l����p�U��������ύX�m�Fڽ��ݽ)<BR>
 * �Ǘ��҂��q�l����p�U��������ύX�m�Fڽ��ݽ<BR>
 * @@author �J�N���V
 * @@version 1.0
 */
public class WEB3AdminAccInfoExclusiveTransferAccountChangeConfirmResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_exclusiveTransferAccountChangeConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082140L;

    /**
     * @@roseuid 418F386700CB
     */
    public WEB3AdminAccInfoExclusiveTransferAccountChangeConfirmResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminAccInfoExclusiveTransferAccountChangeConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
