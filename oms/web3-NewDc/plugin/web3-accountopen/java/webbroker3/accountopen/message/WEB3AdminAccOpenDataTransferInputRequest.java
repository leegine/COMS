head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.07.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenDataTransferInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�݃f�[�^�ڊǓ��̓��N�G�X�g(WEB3AdminAccOpenDataTransferInputRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/18 �И���(���u) �V�K�쐬 ���f�� 180
*/
package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��Ҍ����J�݃f�[�^�ڊǓ��̓��N�G�X�g)<BR>
 * �Ǘ��Ҍ����J�݃f�[�^�ڊǓ��̓��N�G�X�g<BR>
 * <BR>
 * @@author �И���
 * @@version 1.0
 */
public class WEB3AdminAccOpenDataTransferInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_acc_open_data_transfer_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200908181003L;

    /**
     * @@roseuid 4A8A083402CE
     */
    public WEB3AdminAccOpenDataTransferInputRequest()
    {     
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g<BR>
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccOpenDataTransferInputResponse(this);
    }
}
@
