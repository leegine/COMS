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
filename	WEB3RuitoCancelCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݐϓ�������������X�|���X�N���X(WEB3RuitoCancelCompleteResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 ���E (���u) �V�K�쐬
*/
package webbroker3.xbruito.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �ݐϓ�������������X�|���X�N���X<BR>
 */
public class WEB3RuitoCancelCompleteResponse extends WEB3GenResponse
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "ruito_cancel_complete";
    
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
    protected WEB3RuitoCancelCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }    

    /**
     * ���ʔԍ�<BR>
     */
    public String orderActionId;

    /**
     * �X�V����<BR>
     */
    public Date lastUpdatedTimestamp;

    /**
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40922C630138
     */
    public WEB3RuitoCancelCompleteResponse()
    {

    }
}
@
