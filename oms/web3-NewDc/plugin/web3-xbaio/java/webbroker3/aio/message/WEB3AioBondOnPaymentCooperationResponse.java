head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.51.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioBondOnPaymentCooperationResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���o���A�g���X�|���X(WEB3AioBondOnPaymentCooperationResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/17 ���G�� (���u) �V�K�쐬
*/
package webbroker3.aio.message;

import webbroker3.common.message.WEB3BackRequest;

/**
 * (���o���A�g���X�|���X)<BR>
 * ���o���A�g���X�|���X�N���X<BR>
 * <BR>
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3AioBondOnPaymentCooperationResponse
    extends WEB3AioOnPaymentCooperationResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_bond_on_payment_cooperation";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200610170915L;

    /**
     * �R���X�g���N�^
     */
    public WEB3AioBondOnPaymentCooperationResponse()
    {

    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AioBondOnPaymentCooperationResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }
}
@
