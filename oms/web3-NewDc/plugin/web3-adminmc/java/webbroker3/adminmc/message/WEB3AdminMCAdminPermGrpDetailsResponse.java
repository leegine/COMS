head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.51.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminPermGrpDetailsResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ڍ׃��X�|���X(WEB3AdminMCAdminPermGrpDetailsResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/18  �Ɍ��t (���u) �V�K�쐬
*/

package webbroker3.adminmc.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ڍ׃��X�|���X)<BR>
 * �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ڍ׃��X�|���X<BR>
 * 
 * @@author �Ɍ��t
 * @@version 1.0
 */
public class WEB3AdminMCAdminPermGrpDetailsResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_adminPermGrpDetails";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411171410L;
    
    /**
     * (�Ǘ��҃^�C�v���)<BR>
     * �Ǘ��҃^�C�v���<BR>
     */
    public WEB3AdminMCAdminTypeUnit adminTypeUnit;
    
    /**
     * (�����\�@@�\�J�e�S���ꗗ)<BR>
     * �����\�@@�\�J�e�S���ꗗ<BR>
     */
    public WEB3AdminMCTransactionCategoryUnit[] transactionCategoryUnits;
    
    /**
     * (�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ڍ׃��X�|���X)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDetailsResponse
     * @@roseuid 4175DFC8038C
     */
    public WEB3AdminMCAdminPermGrpDetailsResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
