head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradePasswordConvMainAccountRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ڋq�}�X�^���ԃe�[�u���Ïؔԍ��X�V���N�G�X�g(WEB3GentradePasswordConvMainAccountRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/24 ����(�r�q�`) �V�K�쐬
*/
package webbroker3.gentrade.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * �ڋq�}�X�^���ԃe�[�u���Ïؔԍ��X�V���N�G�X�g�N���X
 */
public class WEB3GentradePasswordConvMainAccountRequest extends WEB3BackRequest
{
	/**
	 * PTYPE
	 */
	public static final String PTYPE = "gentrade_password_conv_mainacc";
   
	/**
	 * SerialVersionUID
	 */
	public static final long serialVersionUID = 200503241415L;
   

	/**
	 * �f�t�H���g�R���X�g���N�^
	 */
	public WEB3GentradePasswordConvMainAccountRequest() 
	{
	}
   
	/**
	 * ���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��ԋp����B<br />
	 * @@return WEB3BackResponse<br />
	 * @@roseuid 42366FBD03A6<br />
	 */
	public WEB3BackResponse createResponse() 
	{
		return new WEB3GentradePasswordConvMainAccountResponse(this);
	}
}
@
