head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.15.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecTriggerIssueListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�g���K�[���s�����ꗗ���X�|���X(WEB3AdminDirSecTriggerIssueListResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/17 ���O(���u) �V�K�쐬 ���f��No.116�ANo.118
*/
package webbroker3.dirsec.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҁE�g���K�[���s�����ꗗ���X�|���X)<BR>
 * �Ǘ��ҁE�g���K�[���s�����ꗗ���X�|���X�N���X�B<BR>
 * <BR>
 * @@author ���O
 * @@version 1.0
 */
public class WEB3AdminDirSecTriggerIssueListResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_dir_sec_trigger_issue_list";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200804171506L;

    /**
     * (�g���K�[���s���ꗗ)<BR>
     * �u�g���K�[���s���e�[�u���v����擾�����g���K�[���sJOB���R�[�h��ێ�����<BR>
     */
    public WEB3AdminDirSecTriggerIssueRecordDetail[] triggerIssueInfo;

    /**
     * (���y�[�W��)<BR>
     * ���y�[�W���B<BR>
     */
    public String totalPages;

    /**
     * (�����R�[�h��)<BR>
     * �����R�[�h���B<BR>
     */
    public String totalRecords;

    /**
     * (�\���y�[�W�ԍ�)<BR>
     * �\���y�[�W�ԍ��B<BR>
     */
    public String pageIndex;

    /**
     * @@roseuid 4806E054007F
     */
    public WEB3AdminDirSecTriggerIssueListResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g<BR>
     */
    public WEB3AdminDirSecTriggerIssueListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
