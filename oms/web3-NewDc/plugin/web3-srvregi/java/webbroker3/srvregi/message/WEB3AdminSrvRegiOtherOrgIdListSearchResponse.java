head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.34.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdListSearchResponse.java;


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
File Name           : �T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ��������ڽ��ݽ(WEB3AdminSrvRegiOtherOrgIdListSearchResponse.java)
Revision History    : 2008/03/10 ���u��(���u) �V�K�쐬 ���f��No.338
*/

package webbroker3.srvregi.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ��������ڽ��ݽ)<BR>
 * �T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ��������ڽ��ݽ�N���X<BR>
 * <BR>
 * @@author ���u��
 * @@version 1.0
 */
public class WEB3AdminSrvRegiOtherOrgIdListSearchResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_otherorgid_list_search";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803101406L;

    /**
     * (�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪<BR>
     */
    public String[] serviceDiv;

    /**
     * (�T�[�r�X����)<BR>
     * �T�[�r�X����<BR>
     */
    public String[] serviceName;

    /**
     * (�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ��������ڽ��ݽ)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 47B9248701E2
     */
    public WEB3AdminSrvRegiOtherOrgIdListSearchResponse()
    {

    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     */
    public WEB3AdminSrvRegiOtherOrgIdListSearchResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
