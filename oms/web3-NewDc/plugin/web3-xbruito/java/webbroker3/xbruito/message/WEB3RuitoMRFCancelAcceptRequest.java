head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoMRFCancelAcceptRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݐϓ���MRF�����t���N�G�X�g�N���X(WEB3RuitoMRFCancelAcceptRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 ���E (���u) �V�K�쐬
*/
package webbroker3.xbruito.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * �ݐϓ���MRF�����t���N�G�X�g�N���X<BR>
 */
public class WEB3RuitoMRFCancelAcceptRequest extends WEB3BackRequest
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "ruito_mrf_cancel_accept";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408031539L;      

    /**
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40922B9101C5
     */
    public WEB3RuitoMRFCancelAcceptRequest()
    {

    }

    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * �ݓ�MRF�����t���X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return webbroker3.common.message.WEB3BackResponse
     * @@roseuid 408624BB010F
     */
    public WEB3BackResponse createResponse()
    {
        return new WEB3RuitoMRFCancelAcceptResponse(this);
    }
}
@
