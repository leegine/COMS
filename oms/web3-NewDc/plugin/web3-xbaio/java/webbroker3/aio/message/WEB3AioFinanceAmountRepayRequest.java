head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.57.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioFinanceAmountRepayRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Z���z�ԍσ��N�G�X�g(WEB3AioFinanceAmountRepayRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/20 ������ (���u) �V�K�쐬                                     
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;


/**
 * (�Z���z�ԍσ��N�G�X�g)<BR>
 * �Z���z�ԍσ��N�G�X�g�N���X<BR>
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3AioFinanceAmountRepayRequest extends WEB3BackRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_finance_amount_repay";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200609202050L;
    
    /**
     * @@roseuid 4510F52E02BF
     */
    public WEB3AioFinanceAmountRepayRequest() 
    {
     
    }
    
    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>  
     *<BR>  
     * @@return WEB3BackResponse  
     */ 
    public WEB3BackResponse createResponse()
    {
        return new WEB3AioFinanceAmountRepayResponse(this);
    }
}
@
