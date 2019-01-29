head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.26.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3HashAuthenticationAcceptLoginRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �n�b�V���F�؃��O�C�����N�G�X�g�N���X(WEB3HashAuthenticationAcceptLoginRequest.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2006/06/20 �h�C(���u)
*/

package webbroker3.login.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�n�b�V���F�؃��O�C�����N�G�X�g)<BR>
 * �n�b�V���F�؃��O�C�����N�G�X�g�N���X<BR>
 *<BR> 
 * @@author      �h�C(���u)
 * @@version     1.00
 */
public class WEB3HashAuthenticationAcceptLoginRequest extends WEB3GenRequest 
{
	/**
	 * TAGNAME<BR>
	 */
	public static final String TAGNAME = "request";

	/**
	 * PTYPE<BR>
	 */
	public static final String PTYPE   = "web3_hash_auth_login";

	/**
	 * SerialVersionUID<BR>
	 */
	public final static long serialVersionUID = 200606201800L;

	/**
	 * (��ЃR�[�h)<BR>
	 */
	public String institutionCode;

	/**
	 * (���X�R�[�h)<BR>
	 */
	public String branchCode;

	/**
	 * (�ڋq�R�[�h)<BR>
	 * �ڋq�R�[�h�i���O�C�����̌����ԍ��j<BR>
	 */
	public String acceptCode;

	/**
	 * (xTrade���[�U��)<BR>
	 * ���͂��ꂽ�ڋq�R�[�h��xTrade���O�C�����[�U���ɕϊ������l<BR>
	 */
	public String xTradeUsername;

	/**
	 * (�����`���l��)<BR>
	 * �����`���l��<BR>
	 */
	public String orderChannel;

	/**
	 * (�����o�H�敪)<BR>
	 * �����o�H�敪<BR>
	 */
	public String orderRootDiv;

	/**
	 * (�ڋqID)<BR>
	 * Login����accountID<BR>
	 */
	public String account_id;

	/**
	 * (IP�A�h���X)<BR>
	 */
	public String ipAddress;

	/**
	 * (�F�ؗp�n�b�V���l)<BR>
	 * (SHA1�R�[�h):  40���̕����E������<BR>
	 */
	public String hstr;

	/**
	 * (GUID)<BR>
	 * �B��̕W���̕�����@@�@@�@@32���̉p����<BR>
	 */
	public String guid;

	/**
	 * (�쐬����)<BR>
	 * (����������b):  �t�H�[�}�b�g�FYYYYMMDDHHNNSS<BR>
	 */
	public Date createDate;

	/**
	 * �f�t�H���g�R���X�g���N�^�B<BR>
	 * @@roseuid 403EF0E80267
	 */
	public WEB3HashAuthenticationAcceptLoginRequest() 
	{

	}

	/**
	 * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
	 * <BR>
	 * @@return ���X�|���X�I�u�W�F�N�g
	 * @@roseuid 403EF0440277
	 */
	public WEB3GenResponse createResponse()
	{
		return new WEB3HashAuthenticationAcceptLoginResponse(this);
	}
}
@
