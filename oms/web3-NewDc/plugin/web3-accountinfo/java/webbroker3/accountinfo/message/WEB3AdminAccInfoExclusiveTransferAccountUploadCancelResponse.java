head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.10.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoExclusiveTransferAccountUploadCancelResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l����p�U�����������۰�ޒ��~ڽ��ݽ(WEB3AdminAccInfoExclusiveTransferAccountUploadCancelResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 �J�N���V (���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��҂��q�l����p�U�����������۰�ޒ��~ڽ��ݽ)<BR>
 * �Ǘ��҂��q�l����p�U�����������۰�ޒ��~ڽ��ݽ<BR>
 * @@author �J�N���V
 * @@version 1.0
 */
public class WEB3AdminAccInfoExclusiveTransferAccountUploadCancelResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_exclusiveTransferAccountUploadCancel";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082138L;

    /**
     * @@roseuid 418F386800CB
     */
    public WEB3AdminAccInfoExclusiveTransferAccountUploadCancelResponse()
    {

    }

    /**
     * (�Ǘ��҂��q�l����p�U�����������۰�ޒ��~ڽ��ݽ)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadCancelResponse
     * @@roseuid 415BCABC03CE
     */
    public WEB3AdminAccInfoExclusiveTransferAccountUploadCancelResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
