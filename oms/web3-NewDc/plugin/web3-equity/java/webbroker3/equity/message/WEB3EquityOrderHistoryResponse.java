head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderHistoryResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��������������藚�����X�|���X(WEB3EquityOrderHistoryResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/18 �����F (���u) �V�K�쐬
*/
package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * �i��������������藚�����X�|���X�j�B<BR>
 * <BR>
 * ��������������藚�������@@���X�|���X�f�[�^�N���X
 * @@version 1.0
 */
public class WEB3EquityOrderHistoryResponse extends WEB3GenResponse
{

    /**
     * (���������ꗗ)<BR>
     * ��������������藚�𖾍׃I�u�W�F�N�g�ꗗ<BR>
     */
    public webbroker3.equity.message.WEB3EquityChangeCancelHistoryGroup[] changeCancelHistoryGroups;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_order_history";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200405211058L;

    /**
     * @@roseuid 40A288B4006D
     */
    public WEB3EquityOrderHistoryResponse(WEB3EquityOrderHistoryRequest l_request)
    {
        super(l_request);
    }
    
    /**
     * @@roseuid 40A288B4006D
     */
    public WEB3EquityOrderHistoryResponse()
    {
    }    
}
@
