head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.08.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenDataTransferInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�݃f�[�^�ڊǓ��̓��X�|���X(WEB3AdminAccOpenDataTransferInputResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/18 �И���(���u) �V�K�쐬 ���f�� 180
*/
package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��Ҍ����J�݃f�[�^�ڊǓ��̓��X�|���X)<BR>
 * �Ǘ��Ҍ����J�݃f�[�^�ڊǓ��̓��X�|���X<BR>
 * <BR>
 * @@author �И���
 * @@version 1.0
 */
public class WEB3AdminAccOpenDataTransferInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_acc_open_data_transfer_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200908181004L;

    /**
     * @@roseuid 4A8A0834030D
     */
    public WEB3AdminAccOpenDataTransferInputResponse()
    {
    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminAccOpenDataTransferInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
