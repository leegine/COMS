head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.19.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMExchangeRegistInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�ב֓o�^���̓��N�G�X�g(WEB3AdminTMExchangeRegistInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/10 ���G�� (���u) �V�K�쐬
*/
package webbroker3.trademanagement.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE�ב֓o�^���̓��N�G�X�g)<BR>
 * �Ǘ��ҁE�ב֓o�^���̓��N�G�X�g�N���X<BR>
 * <BR>
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3AdminTMExchangeRegistInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_tm_exchange_regist_input";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200610101425L;

    /**
     * �R���X�g���N�^
     */
    public WEB3AdminTMExchangeRegistInputRequest()
    {

    }

    /**
     * ���X�|���X�f�[�^���쐬����B<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminTMExchangeRegistInputResponse(this);
    }
}
@
