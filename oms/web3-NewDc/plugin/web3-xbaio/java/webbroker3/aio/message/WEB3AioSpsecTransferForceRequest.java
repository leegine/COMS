head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.02.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSpsecTransferForceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��������U�֋������N�G�X�g(WEB3AioSpsecTransferForceRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/04 ��O�� (���u) �V�K�쐬   
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;


/**
 * (��������U�֋������N�G�X�g)<BR>
 * ��������U�֋������N�G�X�g�N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3AioSpsecTransferForceRequest extends WEB3BackRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_spsec_transfer_force";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200502041628L;
    
    /**
     * @@roseuid 41B045C30399
     */
    public WEB3AioSpsecTransferForceRequest() 
    {
     
    }

    /**
     *�icreateResponse�̎����j<BR>
     * <BR>
     * ��������U�֋������X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     */
    public WEB3BackResponse createResponse() 
    {
        return new WEB3AioSpsecTransferForceResponse(this);
    }
}
@