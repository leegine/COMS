head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.22.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMMStopStartChgConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�s��ʎ����~�ĊJ�ύX�������N�G�X�g(WEB3AdminTMMStopStartChgConfirmResponse.java)
Author Name      : Daiwa Institute of Research
*/

package webbroker3.trademanagement.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �Ǘ��ҁE�s��ʎ����~�ĊJ�ύX�������N�G�X�g<BR>
 * <BR>
 * WEB3AdminTMMStopStartChgConfirmResponse<BR>
 * <BR>
 * WEB3AdminTMMStopStartChgConfirmResponse class<BR>
 * <BR>
 * @@author Prabhu
 * @@version 1.0
 */
public class WEB3AdminTMMStopStartChgConfirmResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_tmm_stop_start_chg_confirm";

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
     * (�s��ʎ���󋵈ꗗ)
     * <BR>
     * �s��ʎ���󋵈ꗗ<BR>
     */
    public WEB3AdminTMMarketTradingStatusUnit[] marketTradingStatusList;

    /**
     * @@roseuid 41DD3C0B0001
     */
    public WEB3AdminTMMStopStartChgConfirmResponse()
    {

    }

    /**
     *
     * @@param l_request WEB3GenRequest
     */
    public WEB3AdminTMMStopStartChgConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@