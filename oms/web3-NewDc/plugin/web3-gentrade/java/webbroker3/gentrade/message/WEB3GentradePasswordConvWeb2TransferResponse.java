head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradePasswordConvWeb2TransferResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB2�Ïؔԍ��ڊǃ��X�|���X(WEB3GentradePasswordConvWeb2TransferResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/24 ����(�r�q�`) �V�K�쐬
*/
package webbroker3.gentrade.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * WEB2�Ïؔԍ��ڊǃ��X�|���X
 */
public class WEB3GentradePasswordConvWeb2TransferResponse extends WEB3BackResponse
{
	/**
	 * PTYPE
	 */
	public static final String PTYPE = "gentrade_password_conv_web2trans";
   
	/**
	 * SerialVersionUID
	 */
	public static final long serialVersionUID = 200503241415L;
   

	/**
	 * �f�t�H���g�R���X�g���N�^
	 */
	public WEB3GentradePasswordConvWeb2TransferResponse() 
	{
	}

	/**
	 * �R���X�g���N�^<br />
	 * @@param WEB3BackRequest - ���N�G�X�g�f�[�^<br />
	 * <br />
	 * @@return webbroker3.gentrade.message.WEB3GentradePasswordConvMainAccountResponse<br />
	 * @@roseuid 423670A9017C
	 */
	public WEB3GentradePasswordConvWeb2TransferResponse(WEB3BackRequest l_request) 
	{
		super(l_request);
	}
}
@
