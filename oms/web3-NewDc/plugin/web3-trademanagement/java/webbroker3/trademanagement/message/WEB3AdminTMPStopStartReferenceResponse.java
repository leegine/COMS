head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.22.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMPStopStartReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE���i�ʎ戵��~�ĊJ�Ɖ�X�|���X(WEB3AdminTMPStopStartReferenceResponse.java)
Author Name      : Daiwa Institute of Research
*/

package webbroker3.trademanagement.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�Ǘ��ҁE���i�ʎ戵��~�ĊJ�Ɖ�X�|���X�j<BR>
 * <BR>
 * * �Ǘ��ҁE�s��ʎ����~�ĊJ�ύX�������X�|���X�N���X<BR>
 * <BR>
 * WEB3AdminTMPStopStartReferenceResponse<BR>
 * <BR>
 * WEB3AdminTMPStopStartReferenceResponse class<BR>
 * <BR>
 * @@author Prabhu
 * @@version 1.0
 */
public class WEB3AdminTMPStopStartReferenceResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_tmp_stop_start_reference";

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
     * (���X�ʎ戵�󋵈ꗗ)
     * <BR>
     * ���X�ʎ戵�󋵈ꗗ<BR>
     */
    public WEB3AdminTMBranchTradingStatusUnit[] branchTradingStatusList;

    /**
     * @@roseuid 41DD3CDA03DA
     */
    public WEB3AdminTMPStopStartReferenceResponse()
    {

    }

    /**
     *
     * @@param l_request WEB3GenRequest
     */
    public WEB3AdminTMPStopStartReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@