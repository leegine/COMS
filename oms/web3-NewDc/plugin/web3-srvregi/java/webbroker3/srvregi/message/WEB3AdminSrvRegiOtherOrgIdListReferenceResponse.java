head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.30.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdListReferenceResponse.java;


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
File Name           : �T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�ڽ��ݽ(WEB3AdminSrvRegiOtherOrgIdListReferenceResponse.java)
Revision History    : 2008/03/10 ���u��(���u) �V�K�쐬 ���f��No.338
*/

package webbroker3.srvregi.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�ڽ��ݽ)<BR>
 * �T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�ڽ��ݽ�N���X<BR>
 * <BR>
 * @@author ���u��
 * @@version 1.0
 */
public class WEB3AdminSrvRegiOtherOrgIdListReferenceResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_otherorgId_list_reference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803101404L;

    /**
     * (�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ��)<BR>
     * �T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ��<BR>
     */
    public WEB3AdminSrvRegiOtherOrgIdGroup[] otherOrgIdList;

    /**
     * (�\���y�[�W�ԍ�)<BR>
     * �\���y�[�W�ԍ�<BR>
     * <BR>
     * ���ۂɕ\������y�[�W�ʒu���w��@@���擪�y�[�W��"1"�Ƃ���<BR>
     */
    public String pageIndex;

    /**
     * (���y�[�W��)<BR>
     * ���y�[�W��<BR>
     */
    public String totalPages;

    /**
     * (�����R�[�h��)<BR>
     * �����R�[�h��<BR>
     */
    public String totalRecords;

    /**
     * (�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�ڽ��ݽ)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 47B8D11C01D3
     */
    public WEB3AdminSrvRegiOtherOrgIdListReferenceResponse()
    {

    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     */
    public WEB3AdminSrvRegiOtherOrgIdListReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
