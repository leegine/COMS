head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.06.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinSelectRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �I�����C�������I����ʃ��N�G�X�g(WEB3AioCashinSelectRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 ���E (���u) �V�K�쐬        
                   2004/10/22 ���� (���u) ���r���[               
*/
package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�I�����C�������I����ʃ��N�G�X�g)<BR>
 * �I�����C�������I����ʃ��N�G�X�g�N���X<BR>
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3AioCashinSelectRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_cashin_select";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200409291947L;    
    /**
     * @@roseuid 4158EB32038D
     */
    public WEB3AioCashinSelectRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4158EB3203A1
     */
    public WEB3GenResponse createResponse() 
    {
     return new WEB3AioCashinSelectResponse(this);
    }
}
@
