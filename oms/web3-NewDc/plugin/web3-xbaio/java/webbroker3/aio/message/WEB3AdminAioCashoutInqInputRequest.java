head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.14.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashoutInqInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���\���⍇�����̓��N�G�X�g�N���X(WEB3AdminAioCashoutInqInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 ��O�� (���u) �V�K�쐬
                   2004/10/27 ���E(���u) ���r���[
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�o���\���⍇�����̓��N�G�X�g)<BR>
 * �o���\���⍇�����̓��N�G�X�g�N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0 
 */

public class WEB3AdminAioCashoutInqInputRequest extends WEB3GenRequest 
{

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_cashout_inq_input";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200410131423L;     
    /**
     * @@roseuid 4158EB6503B8
     */
    public WEB3AdminAioCashoutInqInputRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4158EB6503C2
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminAioCashoutInqInputResponse(this);
    }
}
@
