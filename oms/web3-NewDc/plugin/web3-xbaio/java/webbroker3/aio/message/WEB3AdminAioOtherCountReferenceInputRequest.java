head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.11.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioOtherCountReferenceInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���̑������Ɖ���̓��N�G�X�g(WEB3AdminAioOtherCountReferenceInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/11 ��O��(���u) �V�K�쐬
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (���̑������Ɖ���̓��N�G�X�g)<BR>
 * ���̑������Ɖ���̓��N�G�X�g�N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3AdminAioOtherCountReferenceInputRequest extends WEB3GenRequest 
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "admin_aio_other_count_reference_input";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200507110934L;
        
    /**
     * @@roseuid 423552AB0000
     */
    public WEB3AdminAioOtherCountReferenceInputRequest() 
    {
     
    }
    
    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * ���̑������Ɖ���̓��X�|���X�I�u�W�F�N�g��ԋp����B
     * @@return WEB3GenResponse
     * @@roseuid 41E7904C00FA
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminAioOtherCountReferenceInputResponse(this);
    }
}
@
