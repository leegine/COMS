head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.14.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAioOrderUnitTableSearchInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�����P�ʃe�[�u���������̓��N�G�X�g(WEB3AdminDirSecAioOrderUnitTableSearchInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/19  ����� (���u) �V�K�쐬
*/

package webbroker3.dirsec.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE�����P�ʃe�[�u���������̓��N�G�X�g)<BR>
 * �Ǘ��ҁE���͒P�ʃe�[�u���������̓��N�G�X�g�N���X�B<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AdminDirSecAioOrderUnitTableSearchInputRequest extends WEB3GenRequest
{
	/**
	 * PTYPE
	 */
	public static final String PTYPE = "admin_dirsec_aio_order_unit_table_search_input";

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 200607192121L;

	/**
	 * @@roseuid 44BE20C0033C
	 */
	public WEB3AdminDirSecAioOrderUnitTableSearchInputRequest()
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
		return new WEB3AdminDirSecAioOrderUnitTableSearchInputResponse(this);
	}
}
@
