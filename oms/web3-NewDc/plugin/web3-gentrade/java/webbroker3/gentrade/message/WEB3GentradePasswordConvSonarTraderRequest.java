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
filename	WEB3GentradePasswordConvSonarTraderRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �l�b�g�g���[�h�V�X�e���J����
File Name        : ���ԃe�[�u���i���ҏ��j�Ïؔԍ��ǉ����N�G�X�g(WEB3GentradePasswordConvSonarTraderRequest)
Author Name      : Daiwa Institute of Research
Revision History : 2007/08/22 ��c(�r�q�`) �V�K�쐬
*/
package webbroker3.gentrade.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * ���ԃe�[�u���i���ҏ��j�Ïؔԍ��ǉ����N�G�X�g
 */
public class WEB3GentradePasswordConvSonarTraderRequest extends WEB3BackRequest
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "gentrade_password_conv_sonar_trader";
   
    /**
     * SerialVersionUID
     */
    public static final long serialVersionUID = 200708221700L;
   
    /**
     * �f�t�H���g�R���X�g���N�^
     */
    public WEB3GentradePasswordConvSonarTraderRequest() 
    {
    }
   
    /**
     * ���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��ԋp����B<br />
     * @@return WEB3GenResponse<br />
     */
    public WEB3BackResponse createResponse() 
    {
        return new WEB3GentradePasswordConvSonarTraderResponse(this);
    }
}
@
