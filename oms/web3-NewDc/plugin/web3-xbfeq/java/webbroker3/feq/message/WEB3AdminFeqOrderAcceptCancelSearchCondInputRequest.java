head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.26.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqOrderAcceptCancelSearchCondInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO������������t����F�،����������̓��N�G�X�g(WEB3AdminFeqOrderAcceptCancelSearchCondInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 �s�p (���u) �V�K�쐬
                 : 2005/08/02 ���U (���u) ���r���[
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҊO������������t����F�،����������̓��N�G�X�g)<BR>
 * �Ǘ��ҊO������������t����F�،����������̓��N�G�X�g�N���X
 *   
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3AdminFeqOrderAcceptCancelSearchCondInputRequest extends WEB3GenRequest 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_feq_orderAcceptSearchCondInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121526L;
        
    /**
     * @@roseuid 42CE39FC003E
     */
    public WEB3AdminFeqOrderAcceptCancelSearchCondInputRequest() 
    {
     
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFeqOrderAcceptCancelSearchCondInputResponse(this);
    }

}
@
