head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.14.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecTriggerIssueRecordDetail.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[���s���R�[�h�ڍ�(WEB3AdminDirSecTriggerIssueRecordDetail.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/17 ���O(���u) �V�K�쐬 ���f��No.116
*/
package webbroker3.dirsec.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�g���K�[���s���R�[�h�ڍ�)<BR>
 * �g���K�[���s�@@�\�E�e��ʂŕ\�������l��ێ�����N���X�B<BR>
 * <BR>
 * @@author ���O
 * @@version 1.0
 */
public class WEB3AdminDirSecTriggerIssueRecordDetail extends Message
{

    /**
     * (�V�F������)<BR>
     * �V�F�����̂�ێ��B<BR>
     * �g���K�[���s���e�[�u���F�u�V�F�����́v����擾<BR>
     */
    public String shellName;

    /**
     * (�g���K�[����)<BR>
     * �g���K�[���̂�ێ��B<BR>
     * �g���K�[���s���e�[�u���F�u�g���K�[���́v����擾<BR>
     */
    public String triggerName;

    /**
     * (�Ĕ��s�\�t���O)<BR>
     * �Ĕ��s�\�t���O��ێ��B<BR>
     * �g���K�[���s���e�[�u���F�u�Ĕ��s�\�t���O�v���擾<BR>
     */
    public String reissuePossibleFlag;

    /**
     * (���[�U�[�f�[�^)<BR>
     * ���[�U�[�f�[�^��ێ��B<BR>
     * �g���K�[���s���e�[�u���F�u���[�U�[�f�[�^�v����擾<BR>
     */
    public String userData;

    /**
     * (�f�[�^�R�[�h)<BR>
     * �f�[�^�R�[�h��ێ��B<BR>
     * �g���K�[���s���e�[�u���F�u�f�[�^�R�[�h�v����擾<BR>
     */
    public String dataCode;

    /**
     * @@roseuid 4806E05303DA
     */
    public WEB3AdminDirSecTriggerIssueRecordDetail()
    {

    }
}
@
