head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.15.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7844d7ef4f02f89;
filename	WEB3AdminMailInfoReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���[�����ꗗ���X�|���X(WEB3AdminMailInfoReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  鰊](���u) �V�K�쐬
*/
package webbroker3.mailinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (���[�����ꗗ���X�|���X)<BR>
 * ���[�����ꗗ���X�|���X�N���X<BR>
 * 
 * @@author 鰊]
 * @@version 1.0
 */
public class WEB3AdminMailInfoReferenceResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_MailInfo_reference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151310L;

    /**
     * (���[�����ꗗ����)<BR>
     */
    public WEB3AdminMailInfoGroup[] mailInfoList;

    /**
     * (���y�[�W��)<BR>
     */
    public String totalPages;

    /**
     * (�����R�[�h��)<BR>
     */
    public String totalRecords;

    /**
     * (�\���y�[�W�ԍ�)<BR>
     * ���ۂɕ\������y�[�W�ʒu���w��@@���擪�y�[�W��"1"�Ƃ���<BR>
     */
    public String pageIndex;    

    /**
     * (���[�����ꗗ���X�|���X)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 413C0D9403D8
     */
    public WEB3AdminMailInfoReferenceResponse()
    {

    }
    
    /**
     * (���[�����ꗗ���X�|���X)<BR>
     * �f�t�H���g�R���X�g���N�^ �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminMailInfoReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
