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
filename	WEB3EquityExecNotifyMainResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����o���ʒm���C�����X�|���X(WEB3EquityExecNotifyMainResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/01 �������F (SRA) �V�K�쐬
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3BackResponse;

/**
 * �i�����o���ʒm���C�����X�|���X�j�B<br>
 * <br>
 * �����o���ʒm���C�����X�|���X�N���X
 * @@author �������F
 * @@version 1.0
 */
public class WEB3EquityExecNotifyMainResponse extends WEB3BackResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_execNotifyMain";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412010000L;

    /**
     * �f�t�H���g�R���X�g���N�^<BR>
     */
    public WEB3EquityExecNotifyMainResponse()
    {
    }

    /**
     * (�R���X�g���N�^)<BR>
     * <BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3EquityExecNotifyMainResponse(WEB3EquityExecNotifyMainRequest l_request)
    {
        super(l_request);
    }
}
@
