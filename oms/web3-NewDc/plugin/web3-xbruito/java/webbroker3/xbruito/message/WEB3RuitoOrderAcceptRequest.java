head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoOrderAcceptRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݐϓ���������t���N�G�X�g�N���X(WEB3RuitoOrderAcceptRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 ���E (���u) �V�K�쐬
*/
package webbroker3.xbruito.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * �ݐϓ���������t���N�G�X�g�N���X<BR>
 */
public class WEB3RuitoOrderAcceptRequest extends WEB3BackRequest
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "ruito_order_accept";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408031539L;      

    /**
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40922C7C033C
     */
    public WEB3RuitoOrderAcceptRequest()
    {

    }

    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * �ݓ�������t���X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return webbroker3.common.message.WEB3BackResponse
     * @@roseuid 4086251E02C4
     */
    public WEB3BackResponse createResponse()
    {
        return new WEB3RuitoOrderAcceptResponse(this);
    }
}
@