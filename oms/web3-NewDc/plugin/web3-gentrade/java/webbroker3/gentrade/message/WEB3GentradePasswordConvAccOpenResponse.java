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
filename	WEB3GentradePasswordConvAccOpenResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�� �Ïؔԍ����������X�|���X(WEB3BacktradePasswordConvAccOpenResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/24 ����(�r�q�`) �V�K�쐬
*/
package webbroker3.gentrade.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * �����J�� �Ïؔԍ����������X�|���X
 */
public class WEB3GentradePasswordConvAccOpenResponse extends WEB3BackResponse
{
	/**
	 * PTYPE
	 */
	public static final String PTYPE = "gentrade_password_conv_accopen";
   
	/**
	 * SerialVersionUID
	 */
	public static final long serialVersionUID = 200503241415L;
   
	/**
	 * �R���X�g���N�^<br />
	 * @@param WEB3BackRequest - ���N�G�X�g�f�[�^<br />
	 * <br />
	 * @@return webbroker3.Backtrade.message.WEB3GentradePasswordConvAccOpenResponse
	 * @@roseuid 423670A9017C
	 */
	public WEB3GentradePasswordConvAccOpenResponse(WEB3BackRequest l_request) 
	{
		super(l_request);
	}
}
@
