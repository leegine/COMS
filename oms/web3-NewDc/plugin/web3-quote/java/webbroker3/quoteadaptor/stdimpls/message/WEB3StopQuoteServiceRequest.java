head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.45.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3StopQuoteServiceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����T�[�r�X���~���邽�߂̃��b�Z�[�W(WEB3StopQuoteServiceRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/05/18 �R�c�@@��i(FLJ) �V�K�쐬
*/
package webbroker3.quoteadaptor.stdimpls.message;

import com.fitechlabs.xtrade.kernel.message.Request;

/**
 * �����T�[�r�X���~���邽�߂̃��b�Z�[�W
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public class WEB3StopQuoteServiceRequest extends Request
{

    /**
     * PTYPE�v���p�e�B�i���̃��b�Z�[�W�̎�ނ�\���B�j
     * {@@value} 
     */
    public static final String PTYPE = "stop_quote_service";

    /**
     * ��~���鎞���T�[�r�X��ID��\���v���p�e�B
     */
    public String service_id;

    /**
     * �R���X�g���N�^
     */
    public WEB3StopQuoteServiceRequest()
    {
    }

}
@
