head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.42.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3EquityWLimitManualCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����W�w�l�����蓮�ؑ֊������N�G�X�g(WEB3EquityWLimitManualCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/11/20�@@ꎉ�(���u) �V�K�쐬 �i���f���jNo.180
*/

package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (����W�w�l�����蓮�ؑ֊������N�G�X�g)<BR>
 *
 * @@author ꎉ�
 * @@version 1.0
 */
public class WEB3EquityWLimitManualCompleteRequest extends WEB3EquityManualCompleteRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_w_limit_manual_complete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200611221347L;

    /**
     * �R���X�g���N�^<BR>
     */
    public WEB3EquityWLimitManualCompleteRequest()
    {

    }

    /**
     * (createResponse�̎���)<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3EquityManualCompleteResponse();
    }
}
@
