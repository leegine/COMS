head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.08.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioOnPaymentCooperationResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���A�g���X�|���X (WEB3AioOnPaymentCooperationReponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/16 ���r (���u) �V�K�쐬
*/
package webbroker3.aio.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (�o���A�g���X�|���X)<BR>
 * �o���A�g���X�|���X�N���X
 * @@author ���r
 * @@version 1.0
 */
public class WEB3AioOnPaymentCooperationResponse  extends WEB3BackResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_on_payment_cooperation";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200511161604L;
    
    /**
     * @@roseuid 41EC84F8031C
     */
    public WEB3AioOnPaymentCooperationResponse() 
    {
     
    }   

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */    
    public WEB3AioOnPaymentCooperationResponse(WEB3BackRequest l_request) 
    {
        super(l_request); 
    } 

}
@
