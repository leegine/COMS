head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.46.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3EquityWLimitManualConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����W�w�l�����蓮�ؑ֊m�F���N�G�X�g(WEB3EquityWLimitManualConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/11/20�@@ꎉ�(���u) �V�K�쐬 �i���f���jNo.180
*/

package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (����W�w�l�����蓮�ؑ֊m�F���N�G�X�g)<BR>
 *
 * @@author ꎉ�
 * @@version 1.0
 */
public class WEB3EquityWLimitManualConfirmRequest extends WEB3EquityManualConfirmRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_w_limit_manual_confirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200611221148L;

    /**
     * �R���X�g���N�^<BR>
     */
    public WEB3EquityWLimitManualConfirmRequest()
    {

    }

    /**
     * (createResponse�̎���)<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158EB6402B2
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3EquityManualConfirmResponse();
    }
}
@
