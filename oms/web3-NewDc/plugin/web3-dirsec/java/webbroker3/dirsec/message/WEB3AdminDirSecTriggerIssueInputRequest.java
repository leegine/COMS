head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.09.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecTriggerIssueInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�g���K�[���s�������̓��N�G�X�g(WEB3AdminDirSecTriggerIssueInputRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/17 ���O(���u) �V�K�쐬 ���f��No.116�ANo.118
*/
package webbroker3.dirsec.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҁE�g���K�[���s�������̓��N�G�X�g)<BR>
 * �Ǘ��ҁE�g���K�[���s�������̓��N�G�X�g�N���X�B<BR>
 * <BR>
 * @@author ���O
 * @@version 1.0
 */
public class WEB3AdminDirSecTriggerIssueInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_dir_sec_trigger_issue_input";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200804171448L;

    /**
     * @@roseuid 4806E05400CD
     */
    public WEB3AdminDirSecTriggerIssueInputRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminDirSecTriggerIssueInputResponse(this);
    }
}
@
