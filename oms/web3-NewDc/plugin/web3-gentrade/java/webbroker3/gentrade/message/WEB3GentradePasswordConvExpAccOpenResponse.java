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
filename	WEB3GentradePasswordConvExpAccOpenResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�݌����q �����p�X���[�h�ϊ������X�|���X(WEB3GentradePasswordConvExpAccOpenResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/24 ����(�r�q�`) �V�K�쐬
*/
package webbroker3.gentrade.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * �����J�݌����q �����p�X���[�h�ϊ����X�|���X
 */
public class WEB3GentradePasswordConvExpAccOpenResponse extends WEB3BackResponse
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "gentrade_password_conv_exp_accopen";
   
    /**
     * SerialVersionUID
     */
    public static final long serialVersionUID = 200505091415L;
   
    /**
     * �R���X�g���N�^<br />
     * @@param WEB3BackRequest - ���N�G�X�g�f�[�^<br />
     * <br />
     * @@return WEB3GentradePasswordConvExpAccOpenResponse
     * @@roseuid 423670A9017C
     */
    public WEB3GentradePasswordConvExpAccOpenResponse(WEB3BackRequest l_request) 
    {
        super(l_request);
    }
}
@
