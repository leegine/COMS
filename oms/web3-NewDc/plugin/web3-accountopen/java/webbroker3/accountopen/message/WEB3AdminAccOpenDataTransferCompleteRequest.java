head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.03.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenDataTransferCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�݃f�[�^�ڊǊ������N�G�X�g(WEB3AdminAccOpenDataTransferCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/18 �И���(���u) �V�K�쐬 ���f�� No.180  188
*/
package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��Ҍ����J�݃f�[�^�ڊǊ������N�G�X�g)<BR>
 * �Ǘ��Ҍ����J�݃f�[�^�ڊǊ������N�G�X�g<BR>
 * <BR>
 * @@author �И���
 * @@version 1.0
 */
public class WEB3AdminAccOpenDataTransferCompleteRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_acc_open_data_transfer_complete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200908181001L;

    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�<BR>
     */
    public String password;

    /**
     * @@roseuid 4A8A08340251
     */
    public WEB3AdminAccOpenDataTransferCompleteRequest()
    {
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g<BR>
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccOpenDataTransferCompleteResponse(this);
    }
}
@
