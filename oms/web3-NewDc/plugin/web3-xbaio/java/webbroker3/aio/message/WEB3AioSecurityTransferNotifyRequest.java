head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.59.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecurityTransferNotifyRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��U�֒ʒm���N�G�X�g(WEB3AioSecurityTransferNotifyRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/07 ���z (���u) �V�K�쐬   
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;


/**
 * (�،��U�֒ʒm���N�G�X�g)<BR>
 * �،��U�֒ʒm���N�G�X�g�N���X
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AioSecurityTransferNotifyRequest extends WEB3BackRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_security_transfer_notify";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410111111L;
    
    /**
     * @@roseuid 41B045C302FD
     */
    public WEB3AioSecurityTransferNotifyRequest() 
    {
     
    }

    /**
     *�icreateResponse�̎����j<BR>
     * <BR>
     * �،��U�ֈꗗ���X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     */
    public WEB3BackResponse createResponse() 
    {
        return new WEB3AioSecurityTransferNotifyResponse(this);
    }
}
@