head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.00.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3SLRepayApplyCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��S�ۃ��[���ԍϐ\���������X�|���X(WEB3SLRepayApplyCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 �����q (���u) �V�K�쐬 �d�l�ύX�E���f��No.758
*/

package webbroker3.aio.message;

import java.util.Date;


/**
 * (�،��S�ۃ��[���ԍϐ\���������X�|���X)<BR>
 * �S�ۃ��[���ԍϊ������X�|���X�N���X<BR>
 *
 * @@author �����q
 * @@version 1.0
 */
public class WEB3SLRepayApplyCompleteResponse extends WEB3SLRepayApplyCommonResponse
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "sl_repay_apply_complete";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200709121505L;

    /**
     * (�X�V����)<BR>
     * DB�̍X�V����<BR>
     */
    public Date lastUpdatedTimestamp;

    /**
     * (����ID)<BR>
     * �������������̒���ID<BR>
     */
    public String orderId;

    /**
     * @@roseuid 46E890850377
     */
    public WEB3SLRepayApplyCompleteResponse(WEB3SLRepayApplyCompleteRequest l_request)
    {
        super(l_request);
    }
}
@
