head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.26.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3HashAuthenticationAcceptLoginResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �n�b�V���F�؃��O�C�����X�|���X�N���X(WEB3HashAuthenticationAcceptLoginResponse.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2006/06/20 �h�C(���u)
*/

package webbroker3.login.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ServiceImpState;

/**
 * (�n�b�V���F�؃��O�C�����X�|���X)<BR>
 * �n�b�V���F�؃��O�C�����X�|���X�N���X<BR>
 * <BR> 
 * @@author      �h�C(���u)
 * @@version     1.00
 */
public class WEB3HashAuthenticationAcceptLoginResponse extends WEB3GenResponse
{
	/**
	 * TAGNAME
	 */
	public static final String TAGNAME = "response";

	/**
	 * PTYPE
	 */
	public static final String PTYPE = "web3_hash_auth_login";

	/**
	 * SerialVersionUID
	 */
	public final static long serialVersionUID = 200602201800L;

	/**
	 * (xTrade�Z�b�V����ID)<BR>
	 */
	public String xTradeSessionID;

	/**
	 * (�T�[�r�X���{���)<BR>
	 */
	public WEB3ServiceImpState serviceImpState;

	/**
	 * (��Џ��)<BR>
	 */
	public WEB3InstitutionInfo institutionInfo;

	/**
	 * (�ڋq���)<BR>
	 */
	public WEB3AcceptInfo acceptInfo;

	/**
	 * (�擪���ID)<BR>
	 * ���[�U�w��̐擪��ʂ�\��ID<BR>
	 */
	public String topPageID;

	/**
	 * (���X���)<BR>
	 */
	public WEB3BranchInfo branchInfo;

	/**
	 * �f�t�H���g�R���X�g���N�^�B<BR>
	 * @@roseuid 403EF12C017D
	 */
	public WEB3HashAuthenticationAcceptLoginResponse()
	{

	}

	/**
	 * �R���X�g���N�^�B<BR>
	 * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g����Ƀ��X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
	 * @@param l_request
	 * @@roseuid 403EF19C015E
	 */
	public WEB3HashAuthenticationAcceptLoginResponse(WEB3GenRequest l_request)
	{
		super(l_request);
	}
}
@
