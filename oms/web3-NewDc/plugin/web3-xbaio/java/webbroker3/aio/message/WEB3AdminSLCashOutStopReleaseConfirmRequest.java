head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.01.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminSLCashOutStopReleaseConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��S�ۃ��[���o����~�����m�F���N�G�X�g(WEB3AdminSLCashOutStopReleaseConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 ���^�] (���u) �V�K�쐬 �d�l�ύX���f��764 ���f��772
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�،��S�ۃ��[���o����~�����m�F���N�G�X�g)<BR>
 * �،��S�ۃ��[���o����~�����m�F���N�G�X�g�N���X<BR>
 *
 * @@author ���^�]
 * @@version 1.0
 */
public class WEB3AdminSLCashOutStopReleaseConfirmRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_sl_cash_out_stop_release_confirm";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200709131600L;

    /**
     * (����ID)<BR>
     */
    public long accountId;

    public WEB3AdminSLCashOutStopReleaseConfirmRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminSLCashOutStopReleaseConfirmResponse(this);
    }
}
@
