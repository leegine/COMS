head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.33.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdUploadInputRequest.java;


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
File Name           : �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ޓ���ظ���(WEB3AdminSrvRegiOtherOrgIdUploadInputRequest.java)
Revision History    : 2008/03/10 ���u��(���u) �V�K�쐬 ���f��No.338
*/

package webbroker3.srvregi.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ޓ���ظ���)<BR>
 * �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ޓ���ظ��ăN���X<BR>
 * <BR>
 * @@author ���u��
 * @@version 1.0
 */
public class WEB3AdminSrvRegiOtherOrgIdUploadInputRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_otherorgid_upload_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803101413L;

    /**
     * (�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ޓ���ظ���)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 47B8D6F90211
     */
    public WEB3AdminSrvRegiOtherOrgIdUploadInputRequest()
    {

    }

    /**
     * (create���X�|���X)<BR>
     * �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ޓ��̓��X�|���X�𐶐����ĕԋp����B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 47B8D707013B
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminSrvRegiOtherOrgIdUploadInputResponse(this);
    }
}
@
