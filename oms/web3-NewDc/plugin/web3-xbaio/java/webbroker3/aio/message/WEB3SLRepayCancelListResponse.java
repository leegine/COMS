head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.06.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3SLRepayCancelListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��S�ۃ��[���ԍψꗗ���X�|���X(WEB3SLRepayCancelListResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 �����q (���u) �V�K�쐬 �d�l�ύX�E���f��No.758
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (�،��S�ۃ��[���ԍψꗗ���X�|���X)<BR>
 * �S�ۃ��[���ԍψꗗ���X�|���X�N���X<BR>
 *
 * @@author �����q
 * @@version 1.0
 */
public class WEB3SLRepayCancelListResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "sl_repay_cancel_list";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200709121512L;

    /**
     * (�،��S�ۃ��[���ԍϖ���)<BR>
     * �،��S�ۃ��[���ԍϑΏۂƂȂ钍���̃��X�g<BR>
     */
    public WEB3SLRepayUnit[] stockLoanRepayUnits;

    /**
     * @@roseuid 46E890860079
     */
    public WEB3SLRepayCancelListResponse(WEB3SLRepayCancelListRequest l_request)
    {
        super(l_request);
    }
}
@
