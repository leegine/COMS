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
filename	WEB3EquityChangeCancelAcceptResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������������t���X�|���X(WEB3EquityChangeCancelAcceptResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/14 ����� (���u) �V�K�쐬
*/
package webbroker3.equity.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * �i�������������t���X�|���X�j�B<BR>
 * <BR>
 * �������������t���X�|���X�N���X�B
 * @@version 1.0
 */
public class WEB3EquityChangeCancelAcceptResponse extends WEB3BackResponse
{
    /**
     * �|�������t�B�b�N�^�C�v�B<BR>
     */
    public static final String PTYPE = "equity_change_cancel_accept";

    /**
     * �^�O���B<BR>
     */
    public static final String TAGNAME = "response";

    /**
     * �V���A���o�[�W����UID�B<BR>
     */
    public static final long serialVersionUID = 200402241330L;

    /**
     * �R���X�g���N�^�B<BR>
     */
    public WEB3EquityChangeCancelAcceptResponse()
    {
    }

    /**
     * �R���X�g���N�^�B<BR>
     *
     * @@param request ���N�G�X�g�N���X
     */
    public WEB3EquityChangeCancelAcceptResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }
}
@
