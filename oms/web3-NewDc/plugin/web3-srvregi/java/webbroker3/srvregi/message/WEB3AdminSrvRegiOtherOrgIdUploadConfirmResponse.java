head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.34.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
Author Name         : Daiwa Institute of Research
File Name           : �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ފm�Fڽ��ݽ(WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse.java)
Revision History    : 2008/03/10 ���u��(���u) �V�K�쐬 ���f��No.338
*/

package webbroker3.srvregi.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ފm�Fڽ��ݽ)<BR>
 * �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ފm�Fڽ��ݽ�N���X<BR>
 * <BR>
 * @@author ���u��
 * @@version 1.0
 */
public class WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_otherorgid_upload_confirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803101412L;

    /**
     * (�A�b�v���[�h����)<BR>
     * �A�b�v���[�h����<BR>
     */
    public String lineCount;

    /**
     * (�A�b�v���[�hID)<BR>
     * �A�b�v���[�hID<BR>
     */
    public String uploadId;

    /**
     * (�A�b�v���[�h�敪)<BR>
     * �A�b�v���[�h�敪<BR>
     */
    public String uploadDiv;

    /**
     * (�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ފm�Fڽ��ݽ)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 47B8D7FF0228
     */
    public WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse()
    {

    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     */
    public WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
