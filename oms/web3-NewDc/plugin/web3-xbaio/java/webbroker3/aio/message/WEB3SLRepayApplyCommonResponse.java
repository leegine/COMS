head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.09.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3SLRepayApplyCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��S�ۃ��[���ԍϐ\�����ʃ��X�|���X(WEB3SLRepayApplyCommonResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 �����q (���u) �V�K�쐬 �d�l�ύX�E���f��No.758
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (�،��S�ۃ��[���ԍϐ\�����ʃ��X�|���X)<BR>
 * �S�ۃ��[���ԍϋ��ʃ��X�|���X�N���X<BR>
 *
 * @@author �����q
 * @@version 1.0
 */
public class WEB3SLRepayApplyCommonResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "sl_repay_apply_common";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200709121522L;

    /**
     * (�ԍϗ\���)<BR>
     * ��ʂɂđI�����ꂽ�ԍϗ\���<BR>
     */
    public Date repayScheduledDate;

    /**
     * (�ԍϊz)<BR>
     * ��ʂɂē��͂��ꂽ�ԍϊz<BR>
     */
    public String repayAmt;

    /**
     * @@roseuid 46E89086000C
     */
    public WEB3SLRepayApplyCommonResponse(WEB3SLRepayApplyCommonRequest l_request)
    {
        super(l_request);
    }
}
@
