head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.34.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse.java;


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
File Name           : �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ޒ��~ڽ��ݽ(WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse.java)
Revision History    : 2008/03/10 ���u��(���u) �V�K�쐬 ���f��No.338
*/

package webbroker3.srvregi.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ޒ��~ڽ��ݽ)<BR>
 * �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ޒ��~ڽ��ݽ�N���X<BR>
 * <BR>
 * @@author ���u��
 * @@version 1.0
 */
public class WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_otherorgid_upload_cancel";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803101408L;

    /**
     * (�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ޒ��~ڽ��ݽ)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 47B8D7EE0370
     */
    public WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse()
    {

    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     */
    public WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
