head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.03.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �I�����C���������̓��N�G�X�g(WEB3AioCashinInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 ���E (���u) �V�K�쐬     
                   2004/10/22 ���� (���u) ���r���[                  
*/
package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (�I�����C���������̓��N�G�X�g)<BR>
 * �I�����C���������̓��N�G�X�g�N���X<BR>
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3AioCashinInputRequest extends WEB3AioCashinCommonRequest 
{
 
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "aio_cashin_input";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200409291950L; 
       
    /**
     * �f�t�H���g�R���X�g���N
     * @@roseuid 4158E9B700F3
     */
    public WEB3AioCashinInputRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4158E9B40347
     */
    public WEB3GenResponse createResponse() 
    {
     return new WEB3AioCashinInputResponse(this);
    }
}
@
