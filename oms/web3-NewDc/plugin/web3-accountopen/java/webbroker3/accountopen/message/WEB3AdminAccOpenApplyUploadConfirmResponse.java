head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.03.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenApplyUploadConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�ݐ\��UL�m�F���X�|���X(WEB3AdminAccOpenApplyUploadConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/11/21 ���g (���u) �V�K�쐬 ���f�� No.147
*/

package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��Ҍ����J�ݐ\��UL�m�F���X�|���X)<BR>
 * �Ǘ��Ҍ����J�ݐ\��UL�m�F���X�|���X<BR>
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AdminAccOpenApplyUploadConfirmResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_acc_open_apply_upload_confirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200711211757L;

    /**
     * (�A�b�v���[�hID)<BR>
     * �A�b�v���[�hID<BR>
     */
    public String uploadID;

    /**
     * (�A�b�v���[�h����)<BR>
     * �A�b�v���[�h����<BR>
     */
    public String uploadNumber;

    /**
     * @@roseuid 4743EF5202B7
     */
    public WEB3AdminAccOpenApplyUploadConfirmResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminAccOpenApplyUploadConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
