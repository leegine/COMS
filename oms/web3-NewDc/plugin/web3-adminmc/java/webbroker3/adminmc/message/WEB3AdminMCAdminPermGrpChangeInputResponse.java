head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.53.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminPermGrpChangeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ύX���̓��X�|���X(WEB3AdminMCAdminPermGrpChangeInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/18 ���z (���u) �V�K�쐬 
*/

package webbroker3.adminmc.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ύX���̓��X�|���X)<BR>
 * �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ύX���̓��X�|���X<BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AdminMCAdminPermGrpChangeInputResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_admin_perm_grp_change_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411181548L;
    
    /**
     * (�����\�@@�\�J�e�S���ꗗ)<BR>
     * �����\�@@�\�J�e�S���ꗗ<BR>
     */
    public WEB3AdminMCTransactionCategoryUnit[] transactionCategoryUnits;
    
    /**
     * (�Ǘ��҃^�C�v���)<BR>
     * �Ǘ��҃^�C�v���<BR>
     */
    public WEB3AdminMCAdminTypeUnit adminTypeUnit;
    
    /**
     * (�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ύX���̓��X�|���X)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpChangeInputResponse
     * @@roseuid 4175E007037D
     */
    public WEB3AdminMCAdminPermGrpChangeInputResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
