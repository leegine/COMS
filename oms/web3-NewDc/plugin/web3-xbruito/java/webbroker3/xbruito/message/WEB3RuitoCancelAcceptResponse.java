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
filename	WEB3RuitoCancelAcceptResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݐϓ��������t���X�|���X�N���X�N���X(WEB3RuitoCancelAcceptResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 ���E (���u) �V�K�쐬
*/
package webbroker3.xbruito.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * �ݐϓ��������t���X�|���X�N���X<BR>
 */
public class WEB3RuitoCancelAcceptResponse extends WEB3BackResponse
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "ruito_cancel_accept";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408031539L;  
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3RuitoCancelAcceptResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }    

    /**
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40922C750213
     */
    public WEB3RuitoCancelAcceptResponse()
    {

    }
}
@
