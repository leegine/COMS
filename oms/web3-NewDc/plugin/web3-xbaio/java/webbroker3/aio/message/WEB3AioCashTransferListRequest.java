head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.01.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashTransferListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���o���ꗗ���N�G�X�g�N���X(WEB3AioCashTransferListRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 ���z (���u) �V�K�쐬
                   2004/10/22 ���� (���u) ���r���[  
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (���o���ꗗ���N�G�X�g)<BR>
 * ���o���ꗗ���N�G�X�g�N���X
 * 
 * @@author ���z(���u)
 * @@version 1.0 
 */
public class WEB3AioCashTransferListRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_cash_transfer_list";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410111652L;    
    /**
     * @@roseuid 4158E9B50294
     */
    public WEB3AioCashTransferListRequest() 
    {
     
    }
    
    /**
     *�icreateResponse�̎����j<BR>
     * <BR>
     * ���o���ꗗ���X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158E9B5029E
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AioCashTransferListResponse(this);
    }
}
@
