head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.20.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashinNoticeChangeConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����ʒm�����m�F���N�G�X�g�N���X(WEB3AdminAioCashinNoticeChangeConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/21 ��O�� (���u) �V�K�쐬
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (�����ʒm�����m�F���N�G�X�g)<BR>
 * �����ʒm�����m�F���N�G�X�g�N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0 
 */

public class WEB3AdminAioCashinNoticeChangeConfirmRequest extends WEB3AdminAioCashinChangeCommonRequest 
{

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_cashin_notice_change_confirm";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200410131411L;    
        
    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * �����ʒm�����m�F���X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158EB620327
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminAioCashinNoticeChangeConfirmResponse(this);
    }
}
@
