head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.51.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminPermGrpListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ꗗ���X�|���X(WEB3AdminMCAdminPermGrpListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/18  �Ɍ��t (���u) �V�K�쐬
*/
package webbroker3.adminmc.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ꗗ���X�|���X)<BR>
 * �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ꗗ���X�|���X<BR>
 * 
 * @@author �Ɍ��t
 * @@version 1.0
 */
public class WEB3AdminMCAdminPermGrpListResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_adminPermGrpList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411171410L;
    
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
     * (�\���y�[�W�ԍ�)<BR>
     * �\���y�[�W�ԍ�<BR>
     */
    public String pageIndex;
    
    /**
     * (�Ǘ��҃^�C�v���ꗗ)<BR>
     * �Ǘ��҃^�C�v���ꗗ<BR>
     */
    public WEB3AdminMCAdminTypeUnit[] adminTypeUnits;
    
    /**
     * (�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ꗗ���X�|���X)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpListResponse
     * @@roseuid 4175DFAB0198
     */
    public WEB3AdminMCAdminPermGrpListResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
