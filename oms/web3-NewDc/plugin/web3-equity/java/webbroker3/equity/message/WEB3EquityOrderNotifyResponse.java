head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderNotifyResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������������ʒm���X�|���X(WEB3EquityOrderNotifyResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 �R�w�� (���u) �V�K�쐬
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3BackResponse;

/**
 * �i�������������ʒm���X�|���X�j�B<br>
 * <br>
 * ��������������M�����@@���X�|���X�f�[�^�N���X
 * @@version 1.0
 */
public class WEB3EquityOrderNotifyResponse extends WEB3BackResponse
{

    public static final String PTYPE = "equity_order_notify";
    public static final long serialVersionUID = 200402041600L;
    
    /**
     * @@roseuid 40B40AF300DA
     */
    public WEB3EquityOrderNotifyResponse()
    {

    }
    
    /**
     * @@roseuid 40A094A503C8
     */
    public WEB3EquityOrderNotifyResponse(WEB3EquityOrderNotifyRequest l_request)
    {
        super(l_request);
    }
}
@
