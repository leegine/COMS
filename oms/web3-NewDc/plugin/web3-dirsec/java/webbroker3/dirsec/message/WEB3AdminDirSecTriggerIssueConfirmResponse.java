head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.11.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecTriggerIssueConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�g���K�[���s�����m�F���X�|���X(WEB3AdminDirSecTriggerIssueConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/17 ���O(���u) �V�K�쐬 ���f��No.116�ANo.118
*/
package webbroker3.dirsec.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҁE�g���K�[���s�����m�F���X�|���X)<BR>
 * �Ǘ��ҁE�g���K�[���s�����m�F���X�|���X�N���X�B<BR>
 * <BR>
 * @@author ���O
 * @@version 1.0
 */
public class WEB3AdminDirSecTriggerIssueConfirmResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_dir_sec_trigger_issue_confirm";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200804171423L;

    /**
     * (�g���K�[���s���)<BR>
     * �u�g���K�[���s���e�[�u���v����擾�����g���K�[���sJOB���R�[�h��ێ�����B<BR>
     */
    public WEB3AdminDirSecTriggerIssueRecordDetail triggerIssueInfo;

    /**
     * (�x�����b�Z�[�W)<BR>
     * ���͂����l���K�؂łȂ������ꍇ�ɏo�͂��郁�b�Z�[�W��ێ��B<BR>
     */
    public String[] messageWarning;

    /**
     * @@roseuid 4806E05400EC
     */
    public WEB3AdminDirSecTriggerIssueConfirmResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g<BR>
     */
    public WEB3AdminDirSecTriggerIssueConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
