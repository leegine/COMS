head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTListReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҋ����@@��t�{���ꗗ�Ɖ�X�|���X(WEB3AdminFPTListReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/29 ���g (���u) �V�K�쐬
Revision History : 2007/10/09 �����q (���u) ���f��No.003
*/

package webbroker3.docadmin.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҋ����@@��t�{���ꗗ�Ɖ�X�|���X)<BR>
 * �Ǘ��ҋ����@@��t�{���ꗗ�Ɖ�X�|���X�N���X<BR>
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AdminFPTListReferenceResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_fpt_list_reference";

    /**
     * SerialVersionUID<BR>
     */
    private static final long serialVersionUID = 200709291432L;

    /**
     * (�����@@��t�{���ꗗ)<BR>
     * �����@@��t�{���ꗗ<BR>
     */
    public WEB3FPTHistoryInfoUnit[] financialProductTradeList;

    /**
     * (�\���y�[�W�ԍ�)<BR>
     * �\���y�[�W�ԍ�<BR>
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
     * @@roseuid 46FDDD3702EE
     */
    public WEB3AdminFPTListReferenceResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminFPTListReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
