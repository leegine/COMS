head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.30.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdDownloadResponse.java;


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
File Name           : �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖ��޳�۰��ڽ��ݽ(WEB3AdminSrvRegiOtherOrgIdDownloadResponse.java)
Revision History    : 2008/03/10 ���u��(���u) �V�K�쐬 ���f��No.338
*/

package webbroker3.srvregi.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖ��޳�۰��ڽ��ݽ)<BR>
 * �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖ��޳�۰��ڽ��ݽ�N���X<BR>
 * <BR>
 * @@author ���u��
 * @@version 1.0
 */
public class WEB3AdminSrvRegiOtherOrgIdDownloadResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_otherorgid_download";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803101402L;

    /**
     * (�_�E�����[�h�t�@@�C��)<BR>
     * �_�E�����[�h�t�@@�C��<BR>
     */
    public String[] lines;

    /**
     * (���ݓ���)<BR>
     * ���ݓ���<BR>
     */
    public Date currentDate;

    /**
     * (�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖ��޳�۰��ڽ��ݽ)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 47B8D6AC0151
     */
    public WEB3AdminSrvRegiOtherOrgIdDownloadResponse()
    {

    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     */
    public WEB3AdminSrvRegiOtherOrgIdDownloadResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
