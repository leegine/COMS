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
filename	WEB3RuitoSellListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݓ����ꗗ���N�G�X�g�N���X(WEB3RuitoSellListRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 ���E (���u) �V�K�쐬
*/
package webbroker3.xbruito.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �ݓ����ꗗ���N�G�X�g<BR>
 */
public class WEB3RuitoSellListRequest extends WEB3GenRequest
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "ruito_sell_list";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408031539L;      

    /**
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40762C49020D
     */
    public WEB3RuitoSellListRequest()
    {

    }

    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * �ݓ����\�ꗗ�Ɖ�X�|���X���쐬����<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 40762AA30038
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3RuitoSellListResponse(this);
    }
}
@
