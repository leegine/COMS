head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.58.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenApplyUploadInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�ݐ\��UL���̓��X�|���X(WEB3AdminAccOpenApplyUploadInputResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/11/21 ���g (���u) �V�K�쐬 ���f�� No.147
*/

package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��Ҍ����J�ݐ\��UL���̓��X�|���X)<BR>
 * �Ǘ��Ҍ����J�ݐ\��UL���̓��X�|���X<BR>
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AdminAccOpenApplyUploadInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_acc_open_apply_upload_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200711220910L;

    /**
     * (�A�b�v���[�h�����ꗗ)<BR>
     * �A�b�v���[�h�����ꗗ<BR>
     */
    public WEB3AccOpenUploadHistoryUnit uploadHistoryList;

    /**
     * @@roseuid 4743EF530056
     */
    public WEB3AdminAccOpenApplyUploadInputResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminAccOpenApplyUploadInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@