head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.12.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinNoticeConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����A���m�F���N�G�X�g(WEB3AioCashinNoticeConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 ���� (���u) �V�K�쐬
                   2004/10/25 ���E(���u) ���r���[
*/
package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (�����A���m�F���N�G�X�g)<BR>
 * �����A���m�F���N�G�X�g�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3AioCashinNoticeConfirmRequest extends WEB3AioCashinNoticeCommonRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_cashin_notice_confirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410101530L;    
       
    /**
     * @@roseuid 4158E9B80144
     */
    public WEB3AioCashinNoticeConfirmRequest() 
    {
     
    }
    
    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * �����A���m�F���X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158EB5E01FF
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AioCashinNoticeConfirmResponse(this);
    }
}
@
