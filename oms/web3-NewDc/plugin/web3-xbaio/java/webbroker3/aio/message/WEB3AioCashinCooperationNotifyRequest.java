head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.20.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinCooperationNotifyRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����A�g�ʒm���N�G�X�g(WEB3AioCashinCooperationNotifyRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/11 ����(���u) �V�K�쐬
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * �����A�g�ʒm���N�G�X�g<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3AioCashinCooperationNotifyRequest extends WEB3BackRequest 
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "aio_cashin_cooperation_notify";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200601111656L;

    /**
     * (�X���b�hNo) <BR>
     * �X���b�hNo
     */
    public Long threadNo;
    
    /**
     * @@roseuid 41E78FFE0242
     */
    public WEB3AioCashinCooperationNotifyRequest()
    {
        
    }
    
    /**
     * �icreateResponse�̎����j <BR>
     * <BR>
     * �����A�g�ʒm���X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B <BR>
     * @@return WEB3BackResponse
     * @@roseuid 40A4326A02DD
     */
    public WEB3BackResponse createResponse() 
    {
        return new WEB3AioCashinCooperationNotifyResponse(this);
    }
}
@
