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
filename	WEB3AdminFPTRegistCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҋ����@@��t�{���o�^�������X�|���X(WEB3AdminFPTRegistCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/29 ���g (���u) �V�K�쐬
*/

package webbroker3.docadmin.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҋ����@@��t�{���o�^�������X�|���X)<BR>
 * �Ǘ��ҋ����@@��t�{���o�^�������X�|���X�N���X<BR>
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AdminFPTRegistCompleteResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_fpt_regist_complete";

    /**
     * SerialVersionUID<BR>
     */
    private static final long serialVersionUID = 200709291427L;

    /**
     * @@roseuid 46FDDD3A0399
     */
    public WEB3AdminFPTRegistCompleteResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminFPTRegistCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
