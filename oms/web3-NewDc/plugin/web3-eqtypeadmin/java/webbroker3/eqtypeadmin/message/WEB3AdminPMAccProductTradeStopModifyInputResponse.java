head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPMAccProductTradeStopModifyInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�ڋq�����ʎ����~�ύX���̓��X�|���X (WEB3AdminPMAccProductTradeStopModifyInputResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE�ڋq�����ʎ����~�ύX���̓��X�|���X)<BR>
 * <BR>
 * �Ǘ��ҁE�ڋq�����ʎ����~�ύX���̓��X�|���X�N���X<BR>
 * <BR>
 * WEB3AdminPMAccProductTradeStopModifyInputResponse<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminPMAccProductTradeStopModifyInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_pm_acc_product_trade_stop_modify_input";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * �i�ڋq�����ʎ����~���j
     * �ڋq�����ʎ����~���
     * ----<English>--------------------
     * accProductTradeStopInfo
     */
    public WEB3AdminPMAccProductTradeStopInfoUnit accProductTradeStopInfo;

    /**
     * @@roseuid 41FD940D030D
     */
    public WEB3AdminPMAccProductTradeStopModifyInputResponse()
    {

    }

    /**
     * @@roseuid 41FD94070000
     * @@param l_request WEB3GenRequest
     */
    public WEB3AdminPMAccProductTradeStopModifyInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
