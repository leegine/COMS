head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.11.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAioOrderUnitTableSearchInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�����P�ʃe�[�u���������̓��X�|���X(WEB3AdminDirSecAioOrderUnitTableSearchInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/19  ����� (���u) �V�K�쐬
*/

package webbroker3.dirsec.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE�����P�ʃe�[�u���������̓��X�|���X)<BR>
 * �Ǘ��ҁE�����P�ʃe�[�u���������̓��X�|���X�N���X�B<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AdminDirSecAioOrderUnitTableSearchInputResponse extends WEB3GenResponse
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
	 * @@roseuid 44BE20C002CE
	 */
	public WEB3AdminDirSecAioOrderUnitTableSearchInputResponse()
	{

	}

	/**
	 * �R���X�g���N�^�B<BR>
	 * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
	 * 
	 * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
	 */
	public WEB3AdminDirSecAioOrderUnitTableSearchInputResponse(WEB3GenRequest l_request)
	{
		super(l_request);
	}
}
@