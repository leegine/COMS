head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.56.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminChangeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright          : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name          : (�Ǘ��҃��j���[����Ǘ��ҕύX���̓��X�|���X(WEB3AdminMCAdminChangeInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/24 ���q (���u) �V�K�쐬
*/

package webbroker3.adminmc.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��҃��j���[����Ǘ��ҕύX���̓��X�|���X)<BR>
 * �Ǘ��҃��j���[����Ǘ��ҕύX���̓��X�|���X<BR>
 * @@author ���q
 * @@version 1.0
 */
public class WEB3AdminMCAdminChangeInputResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_adminChangeInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411171410L;
    
    /**
     * (�������x���R�[�h�ꗗ)<BR>
     * �������x���R�[�h�ꗗ<BR>
     */
    public String[] permissionLevelList;
    
    /**
     * (�������x�����ꗗ)<BR>
     * �������x�����ꗗ<BR>
     * <BR>
     * ���������x���R�[�h�ɑΉ����閼�̈ꗗ<BR>
     * <BR>
     */
    public String[] permissionLevelNameList;
    
    /**
     * (�Ǘ��҃��j���[����Ǘ��ҕύX���̓��X�|���X)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminChangeInputResponse
     * @@roseuid 4175E070007F
     */
    public WEB3AdminMCAdminChangeInputResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
