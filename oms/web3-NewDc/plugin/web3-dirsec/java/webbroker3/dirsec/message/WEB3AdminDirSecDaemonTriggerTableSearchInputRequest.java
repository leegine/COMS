head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.10.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecDaemonTriggerTableSearchInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�f�[�����g���K�[�e�[�u���������̓��N�G�X�g(WEB3AdminDirSecDaemonTriggerTableSearchInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/19  ꎉ� (���u) �V�K�쐬
*/

package webbroker3.dirsec.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE�f�[�����g���K�[�e�[�u���������̓��N�G�X�g)<BR>
 * �Ǘ��ҁE�f�[�����g���K�[�e�[�u���������̓��N�G�X�g�N���X�B<BR>
 * 
 * @@author ꎉ�(���u)
 * @@version 1.0
 */

public class WEB3AdminDirSecDaemonTriggerTableSearchInputRequest extends
	WEB3GenRequest
{
	/**
	 * PTYPE<BR>
	 */
	public static final String PTYPE = 
		"admin_dirsec_daemon_trigger_table_search_input";

	/**
	 * SerialVersionUID<BR>
	 */
	public static final long serialVersionUID = 200607192050L;
	
	/**
	 * @@roseuid 44BE20C2004E
	 */	
	public WEB3AdminDirSecDaemonTriggerTableSearchInputRequest()
	{

	}
	
	/**
	 * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
	 * <BR>
	 * 
	 * @@return ���X�|���X�I�u�W�F�N�g
	 */	
	public WEB3GenResponse createResponse()
	{
		return new WEB3AdminDirSecDaemonTriggerTableSearchInputResponse(this);
	}
}
@
