head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.13.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAioOrderUnitTableSearchResultResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�����P�ʃe�[�u���������ʃ��X�|���X(WEB3AdminDirSecAioOrderUnitTableSearchResultResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/19  ����� (���u) �V�K�쐬
Revesion History : 2007/10/31  �Ӑ� (���u) ���f��No.113
Revesion History : 2008/07/15  ���� (���u) ���f��No.130
*/

package webbroker3.dirsec.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE�����P�ʃe�[�u���������ʃ��X�|���X)<BR>
 * �Ǘ��ҁE�����P�ʃe�[�u���������ʃ��X�|���X�N���X�B<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AdminDirSecAioOrderUnitTableSearchResultResponse extends WEB3GenResponse
{
	/**
	 * PTYPE
	 */
	public static final String PTYPE = "admin_dirsec_aio_order_unit_table_search_result";

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 200607192121L;

	/**
	 * (����ID)<BR>
	 * ����ID<BR>
	 */
	public String accountId;

	/**
	 * (���XID)<BR>
	 * ���XID<BR>
	 */
	public String branchId;

	/**
	 * (����ID)<BR>
	 * ����ID<BR>
	 */
	public String orderId;

	/**
	 * (�����^�C�v)<BR>
	 * �����^�C�v<BR>
	 */
	public String productType;

	/**
	 * (�������)<BR>
	 * �������<BR>
	 */
	public String orderStatus;

	/**
	 * (�����L�����)<BR>
	 * �����L�����<BR>
	 */
	public String orderOpenStatus;

	/**
	 * (���ʃR�[�h)<BR>
	 * ���ʃR�[�h<BR>
	 */
	public String requestNumber;

    /**
     * (�����)<BR>
     * �����<BR>
     */
	public String execStatus;

    /**
     * (������)<BR>
     * ������<BR>
     */
    public String orderBizDate;

	/**
	 * @@roseuid 44BE20C000BB
	 */
	public WEB3AdminDirSecAioOrderUnitTableSearchResultResponse()
	{

	}

	/**
	 * �R���X�g���N�^�B<BR>
	 * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
	 * 
	 * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
	 */
	public WEB3AdminDirSecAioOrderUnitTableSearchResultResponse(WEB3GenRequest l_request)
	{
		super(l_request);
	}
}
@
