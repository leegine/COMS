head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.36.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondDomesticRecruitLimitManageCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҍ��������X�ʉ���g�Ǘ����ʃ��X�|���X(WEB3AdminBondDomesticRecruitLimitManageCommonResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 ���g (���u) �V�K�쐬 �d�l�ύX�E���f��No.215
*/
package webbroker3.bd.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҍ��������X�ʉ���g�Ǘ����ʃ��X�|���X)<BR>
 * �Ǘ��ҍ��������X�ʉ���g�Ǘ����ʃ��X�|���X<BR>
 * <BR>
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AdminBondDomesticRecruitLimitManageCommonResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_domestic_recruit_limit_manage_common";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200707231842L;

    /**
     * (���������X�ʉ���g���)<BR>
     * ���������X�ʉ���g���<BR>
     * <BR>
     * ���������X�ʉ���g���̃��X�g<BR>
     */
    public WEB3BondDomesticBranchRecruitLimitInfo[] bondDomesticBranchRecruitLimitInfo;

    /**
     * @@roseuid 46A473FE0186
     */
    public WEB3AdminBondDomesticRecruitLimitManageCommonResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminBondDomesticRecruitLimitManageCommonResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
