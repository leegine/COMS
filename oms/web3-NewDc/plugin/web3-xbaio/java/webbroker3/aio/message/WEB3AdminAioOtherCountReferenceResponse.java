head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.16.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioOtherCountReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���̑������Ɖ�X�|���X(WEB3AdminAioOtherCountReferenceResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/11 ��O��(���u) �V�K�쐬
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (���̑������Ɖ�X�|���X)<BR>
 * ���̑������Ɖ�X�|���X�N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3AdminAioOtherCountReferenceResponse extends WEB3GenResponse 
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "admin_aio_other_count_reference";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200503171454L;

    /**
     * (���̑������Ɖ���ꗗ)<BR>
     * ���̑������Ɖ���ꗗ
     */
    public WEB3AioOtherCountReferenceUnit otherCountReferenceUnits;
    
    /**
     * @@roseuid 423552AB00FA
     */
    public WEB3AdminAioOtherCountReferenceResponse() 
    {
     
    }
    
    /**
     * (�R���X�g���N�^)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminAioOtherCountReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
