head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.36.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdListSearchRequest.java;


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
File Name           : �T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ��������ظ���(WEB3AdminSrvRegiOtherOrgIdListSearchRequest.java)
Revision History    : 2008/03/10 ���u��(���u) �V�K�쐬 ���f��No.338
*/

package webbroker3.srvregi.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ��������ظ���)<BR>
 * �T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ��������ظ��ăN���X<BR>
 * <BR>
 * @@author ���u��
 * @@version 1.0
 */
public class WEB3AdminSrvRegiOtherOrgIdListSearchRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_otherorgid_list_search";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803101405L;

    /**
     * (���ꏈ���敪)<BR>
     * ���ꏈ���敪<BR>
     */
    public String specialProcessDiv;

    /**
     * @@roseuid 47D111330169
     */
    public WEB3AdminSrvRegiOtherOrgIdListSearchRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminSrvRegiOtherOrgIdListSearchResponse(this);
    }
}
@
