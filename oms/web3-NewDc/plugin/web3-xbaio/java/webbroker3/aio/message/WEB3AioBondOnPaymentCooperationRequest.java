head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.02.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioBondOnPaymentCooperationRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���o���A�g���N�G�X�g(WEB3AioBondOnPaymentCooperationRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/17 ���G�� (���u) �V�K�쐬
*/
package webbroker3.aio.message;

import webbroker3.common.message.WEB3BackResponse;

/**
 * (���o���A�g���N�G�X�g)<BR>
 * ���o���A�g���N�G�X�g�N���X<BR>
 * <BR>
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3AioBondOnPaymentCooperationRequest
    extends WEB3AioOnPaymentCooperationRequest
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
    public WEB3AioBondOnPaymentCooperationRequest()
    {

    }

    /**
     *�icreateResponse�̎����j<BR>
     * <BR>
     * ���o���A�g���X�|���X�I�u�W�F�N�g��ԋp����B<BR>
     * @@return WEB3BackResponse
     */
    public WEB3BackResponse createResponse()
    {
        return new WEB3AioBondOnPaymentCooperationResponse(this);
    }
}
@
