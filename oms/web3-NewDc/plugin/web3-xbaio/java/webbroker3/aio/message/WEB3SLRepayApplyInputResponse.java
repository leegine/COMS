head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.17.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3SLRepayApplyInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��S�ۃ��[���ԍϐ\�����̓��X�|���X(WEB3SLRepayApplyInputResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 �����q (���u) �V�K�쐬 �d�l�ύX�E���f��No.758
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (�،��S�ۃ��[���ԍϐ\�����̓��X�|���X)<BR>
 * �S�ۃ��[���ԍϓ��̓��X�|���X�N���X<BR>
 *
 * @@author �����q
 * @@version 1.0
 */
public class WEB3SLRepayApplyInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "sl_repay_apply_input";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200709121510L;

    /**
     * (�ԍϗ\���)<BR>
     * �iT+1�`T+4)�̕ԍϗ\����̃��X�g<BR>
     */
    public Date[] repayScheduledDateList;

    /**
     * (�ԍω\�z)<BR>
     * �ڋq�́iT+1�`T+4)�̕ԍω\�z�̃��X�g<BR>
     */
    public String[] repayableAmtList;

    /**
     * @@roseuid 46E89085029C
     */
    public WEB3SLRepayApplyInputResponse(WEB3SLRepayApplyInputRequest l_request)
    {
        super(l_request);
    }
}
@
