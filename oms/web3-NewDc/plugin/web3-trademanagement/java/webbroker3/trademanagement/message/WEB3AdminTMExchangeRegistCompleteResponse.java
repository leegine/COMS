head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.22.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMExchangeRegistCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�ב֓o�^�������X�|���X (WEB3AdminTMExchangeRegistCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/10 ���G�� (���u) �V�K�쐬
*/
package webbroker3.trademanagement.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE�ב֓o�^�������X�|���X)<BR>
 * �Ǘ��ҁE�ב֓o�^�������X�|���X�N���X<BR>
 * <BR>
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3AdminTMExchangeRegistCompleteResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_tm_exchange_regist_complete";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200610101425L;

    /**
     * �R���X�g���N�^
     */
    public WEB3AdminTMExchangeRegistCompleteResponse()
    {

    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminTMExchangeRegistCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
