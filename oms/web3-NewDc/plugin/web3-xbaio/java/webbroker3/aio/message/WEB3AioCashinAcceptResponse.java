head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.03.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinAcceptResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������t���X�|���X(WEB3AioCashinAcceptResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 ���� (���u) �V�K�쐬
                   2004/10/25 ���E(���u) ���r���[
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (������t���X�|���X)<BR>
 * (������t���X�|���X�N���X)<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3AioCashinAcceptResponse extends WEB3BackResponse 
{

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "aio_cashin_accept";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200410101518L;      
     
    /**
     * @@roseuid 4158E9B6017D
     */
    public WEB3AioCashinAcceptResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AioCashinAcceptResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }  
}
@