head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.09.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecDaemonTriggerTableSearchResultResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�f�[�����g���K�[�e�[�u���������ʃ��X�|���X(WEB3AdminDirSecDaemonTriggerTableSearchResultResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/19  ꎉ� (���u) �V�K�쐬
*/

package webbroker3.dirsec.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * <�Ǘ��ҁE�f�[�����g���K�[�e�[�u���������ʃ��X�|���X><BR>
 * �Ǘ��ҁE�f�[�����g���K�[�e�[�u���������ʃ��X�|���X�N���X<BR>
 * 
 * @@author ꎉ�(���u)
 * @@version 1.0
 */

public class WEB3AdminDirSecDaemonTriggerTableSearchResultResponse extends WEB3GenResponse
{
	/**
	 * PTYPE<BR>
	 */
	public static final String PTYPE = 
		"admin_dirsec_daemon_trigger_table_search_result";

	/**
	 * SerialVersionUID<BR>
	 */
	public static final long serialVersionUID = 200607192050L;
	
	/**
	 * �R���X�g���N�^�B<BR>
	 * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
	 * 
	 * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
	 */
	public WEB3AdminDirSecDaemonTriggerTableSearchResultResponse(
		WEB3GenRequest l_request)
	{
		super(l_request);
	}

	/**
	 * (�����^�C�v)<BR>
	 * �����^�C�v<BR>
	 */
	public String triggerType;

	/**
	 * (���ʃR�[�h)<BR>
	 * ���ʃR�[�h<BR>
	 */
	public String orderRequestNumber;

	/**
	 * (�ڋq�R�[�h�i���j)<BR>
	 * �ڋq�R�[�h�i���j<BR>
	 */
	public String rangeFrom;

	/**
	 * (�ڋq�R�[�h�i���j)<BR>
	 * �ڋq�R�[�h�i���j<BR>
	 */
	public String rangeTo;

	/**
	 * (�X�e�[�^�X)<BR>
	 * �X�e�[�^�X<BR>
	 */
	public String triggerStatus;

	/**
	 * (�ŏI��������)<BR>
	 * �ŏI��������<BR>
	 */
	public Date triggerDate;

}
@
