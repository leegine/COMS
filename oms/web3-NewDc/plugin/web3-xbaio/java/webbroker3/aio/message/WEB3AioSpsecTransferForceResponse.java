head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.57.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSpsecTransferForceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��������U�֋������X�|���X(WEB3AioSpsecTransferForceResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/04 ��O�� (���u) �V�K�쐬   
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;


/**
 * (��������U�֋������X�|���X)<BR>
 * ��������U�֋������X�|���X�N���X  
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3AioSpsecTransferForceResponse extends WEB3BackResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_spsec_transfer_force";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200502041626L; 
    
    /**
     * @@roseuid 41B045C4005D
     */
    public WEB3AioSpsecTransferForceResponse() 
    {
     
    }
    
    /**
     * (�R���X�g���N�^)<BR>
	 * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AioSpsecTransferForceResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }   
}
@
