head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.15.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinSettleCancelRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����v�����~���N�G�X�g(WEB3AioCashinSettleCancelRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 ���� (���u) �V�K�쐬
                   2004/10/25 ���E(���u) ���r���[
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (�����v�����~���N�G�X�g)<BR>
 * �����v�����~���N�G�X�g�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3AioCashinSettleCancelRequest extends WEB3AioCashinSettleCommonRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_cashin_settle_cancel";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410101430L;    
    
    /**
     * @@roseuid 4158EB330103
     */
    public WEB3AioCashinSettleCancelRequest() 
    {
     
    }
    
    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * �����v�����~���X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158EB5E01FF
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AioCashinSettleCancelResponse(this);
    }
}
@