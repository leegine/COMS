head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.44.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3StopQuoteServiceBroadcastRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �N���X�^���̑S�ẴT�[�o��Ŏ����T�[�r�X���~���邽�߂̃��b�Z�[�W(WEB3StopQuoteServiceBroadcastRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/05/18 �R�c�@@��i(FLJ) �V�K�쐬
*/
package webbroker3.quoteadaptor.stdimpls.message;

import com.fitechlabs.xtrade.kernel.message.Request;

/**
 * �N���X�^���̑S�ẴT�[�o��Ŏ����T�[�r�X���~���邽�߂̃��b�Z�[�W
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public class WEB3StopQuoteServiceBroadcastRequest extends Request
{

    /**
     * PTYPE�v���p�e�B�i���̃��b�Z�[�W�̎�ނ�\���B�j
     * {@@value} 
     */
    public static final String PTYPE = "stop_quote_service_broadcast";

    /**
     * ��~���鎞���T�[�r�X��ID��\���v���p�e�B
     */
    public String service_id;

    /**
     * �R���X�g���N�^
     */
    public WEB3StopQuoteServiceBroadcastRequest()
    {
    }

}
@
