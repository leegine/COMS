head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.51.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���\�����̓��N�G�X�g(WEB3AioCashoutInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 ���� (���u) �V�K�쐬
                   2004/10/25 ���E(���u) ���r���[
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�o���\�����̓��N�G�X�g)<BR>
 * �o���\�����̓��N�G�X�g�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3AioCashoutInputRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_cashout_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410101200L;
        
    /**
     * @@roseuid 4158EB620197
     */
    public WEB3AioCashoutInputRequest() 
    {
     
    } 

    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * �o���\�����̓��X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158EB6201A1
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AioCashoutInputResponse(this);
    }
}
@
