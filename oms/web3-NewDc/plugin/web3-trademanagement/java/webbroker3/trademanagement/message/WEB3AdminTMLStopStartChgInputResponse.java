head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.20.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMLStopStartChgInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE���O�C����~�ĊJ�ύX���̓��X�|���X(WEB3AdminTMLStopStartChgInputResponse.java)
Author Name      : Daiwa Institute of Research
*/

package webbroker3.trademanagement.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�Ǘ��ҁE���O�C����~�ĊJ�ύX���̓��X�|���X�j<BR>
 * <BR>
 * �Ǘ��ҁE���O�C����~�ĊJ�ύX���̓��X�|���X�N���X<BR>
 * <BR>
 * WEB3AdminTMLStopStartChgInputResponse<BR>
 * <BR>
 * WEB3AdminTMLStopStartChgInputResponse class<BR>
 * @@author Prabhu
 * @@version 1.0
 */
public class WEB3AdminTMLStopStartChgInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_tml_stop_start_chg_input";

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
     * @@roseuid 41DD39F1030E
     */
    public WEB3AdminTMLStopStartChgInputResponse()
    {

    }

    /**
     *
     * @@param l_request WEB3GenRequest
     */
    public WEB3AdminTMLStopStartChgInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}@
