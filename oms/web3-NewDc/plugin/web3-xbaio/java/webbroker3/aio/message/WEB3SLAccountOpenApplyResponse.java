head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.07.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3SLAccountOpenApplyResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : SL�����J�ݐ\�����X�|���X(WEB3SLAccountOpenApplyResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/07 �����q (���u) �V�K�쐬 �d�l�ύX�E���f��No.754
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (SL�����J�ݐ\�����X�|���X)<BR>
 * SL�����J�ݐ\�����X�|���X�N���X<BR>
 *
 * @@author �����q
 * @@version 1.0
 */

public class WEB3SLAccountOpenApplyResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "sl_account_open_apply";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200709071029L;

    /**
     * (�ڋq��{���)<BR>
     * �ڋq��{���<BR>
     */
    public WEB3SLAccountBaseInfoUnit accountBaseInfo;

    /**
     * @@roseuid 46E0BE47031C
     */
    public WEB3SLAccountOpenApplyResponse(WEB3SLAccountOpenApplyRequest l_request)
    {
        super(l_request);
    }
}
@
