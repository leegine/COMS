head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityChangeCancelNotifyMainResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������������ʒm���C�����X�|���X(WEB3EquityChangeCancelNotifyMainResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/06 �����a�� (SRA) �V�K�쐬
                   2004/12/21 �����a��(SRA) JavaDoc�C��
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3BackResponse;

/**
 * �i������������ʒm���C�����X�|���X�j�B<br>
 * <br>
 * ������������ʒm���C�����X�|���X�N���X
 * @@author �����a��
 * @@version 1.0
 */
public class WEB3EquityChangeCancelNotifyMainResponse extends WEB3BackResponse
{

    /**
     * <p>�i�|�������t�B�b�N�^�C�v�j�B</p>
     */
    public static final String PTYPE = "equity_changeCancelNotifyMain";

    /**
     * <p>�i�V���A���o�[�W����UID�j�B</p>
     */
    public static final long serialVersionUID = 200412060000L;

    /**
     * <p>�i������������ʒm���C�����X�|���X�j�B</p>
     * <p>�R���X�g���N�^�B</p>
     */
    public WEB3EquityChangeCancelNotifyMainResponse()
    {
    }

    /**
     * <p>�i������������ʒm���C�����X�|���X�j�B</p>
     * <p>�R���X�g���N�^�B</p>
     * @@param l_request ������������ʒm���C�����N�G�X�g
     */
    public WEB3EquityChangeCancelNotifyMainResponse(WEB3EquityChangeCancelNotifyMainRequest l_request)
    {
        super(l_request);
    }
}
@
