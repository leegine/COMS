head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.20.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMLStopStartChgConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE���O�C����~�ĊJ�ύX�m�F���X�|���X(WEB3AdminTMLStopStartChgConfirmResponse.java)
Author Name      : Daiwa Institute of Research
*/

package webbroker3.trademanagement.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�Ǘ��ҁE���O�C����~�ĊJ�ύX�m�F���X�|���X�j<BR>
 * <BR>
 * WEB3AdminTMLStopStartChgConfirmResponse<BR>
 * <BR>
 * WEB3AdminTMLStopStartChgConfirmResponse class<BR>
 * <BR>
 * @@author Prabhu
 * @@version 1.0
 */
public class WEB3AdminTMLStopStartChgConfirmResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_tml_stop_start_chg_confirm";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200501071345L;

    /**
     * �i���ݓ����j<BR>
     * <BR>
     * ���ݓ���<BR>
     * <BR>
     * currentDate<BR>
     */
    public Date currentDate;

    /**
     * (���O�C�����󋵈ꗗ)
     * <BR>
     * ���O�C�����󋵈ꗗ<BR>
     */
    public WEB3AdminTMLoginPermissionStatusUnit[] loginPermissionStatusList;

    /**
     * @@roseuid 41DD39FE005F
     */
    public WEB3AdminTMLStopStartChgConfirmResponse()
    {

    }

    /**
     *
     * @@param l_request WEB3GenRequest
     */
    public WEB3AdminTMLStopStartChgConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}@
