head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTUploadConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҋ����@@��t�{���A�b�v���[�h�m�F���X�|���X(WEB3AdminFPTUploadConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/07 ���g (���u) �V�K�쐬 ���f�� No.013
*/

package webbroker3.docadmin.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҋ����@@��t�{���A�b�v���[�h�m�F���X�|���X)<BR>
 * �Ǘ��ҋ����@@��t�{���A�b�v���[�h�m�F���X�|���X<BR>
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AdminFPTUploadConfirmResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_fpt_upload_confirm";

    /**
     * SerialVersionUID<BR>
     */
    private static final long serialVersionUID = 200712071148L;

    /**
     * (�A�b�v���[�h����)<BR>
     * �A�b�v���[�h����<BR>
     */
    public String uploadNumber;

    /**
     * (�A�b�v���[�hID)<BR>
     * �A�b�v���[�hID<BR>
     */
    public String uploadId;

    /**
     * @@roseuid 4758B278030D
     */
    public WEB3AdminFPTUploadConfirmResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminFPTUploadConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
