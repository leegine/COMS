head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.55.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3SLAccountOpenResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : SL�����J�݃��X�|���X (WEB3SLAccountOpenResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/07 �����q (���u) �V�K�쐬 �d�l�ύX�E���f��No.754
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (SL�����J�݃��X�|���X)<BR>
 * SL�����J�݃��X�|���X�N���X<BR>
 *
 * @@author �����q
 * @@version 1.0
 */
public class WEB3SLAccountOpenResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "sl_account_open";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200709071047L;

    /**
     * (�X�g�b�N���[�������ԍ�)<BR>
     * �X�g�b�N���[�������ԍ�<BR>
     */
    public String stockLoanAccount;

    /**
     * (�O���ڑ�URL)<BR>
     * �O���ڑ�URL<BR>
     */
    public String url;

    /**
     * (�ڋq��{���)<BR>
     * �ڋq��{���<BR>
     */
    public WEB3SLAccountBaseInfoUnit accountBaseInfo;

    /**
     * @@param l_request - SL�����J�݃��N�G�X�g
     * @@roseuid 46E0BE47031C
     */
    public WEB3SLAccountOpenResponse(WEB3SLAccountOpenRequest l_request)
    {
        super(l_request);
    }
}
@
