head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.13.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3SLRepayCancelConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��S�ۃ��[���ԍώ���m�F���X�|���X(WEB3SLRepayCancelConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 �����q (���u) �V�K�쐬 �d�l�ύX�E���f��No.758
*/

package webbroker3.aio.message;


/**
 * (�،��S�ۃ��[���ԍώ���m�F���X�|���X)<BR>
 * �S�ۃ��[���ԍώ���m�F���X�|���X�N���X<BR>
 *
 * @@author �����q
 * @@version 1.0
 */
public class WEB3SLRepayCancelConfirmResponse extends WEB3SLRepayCancelCommonResponse
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "sl_repay_cancel_confirm";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200709121511L;

    /**
     * (�ԍω\�z)<BR>
     * �ڋq�̌��݂̕ԍω\�z<BR>
     */
    public String repayableAmt;

    /**
     * @@roseuid 46E890860106
     */
    public WEB3SLRepayCancelConfirmResponse(WEB3SLRepayCancelConfirmRequest l_request)
    {
        super(l_request);
    }
}
@
