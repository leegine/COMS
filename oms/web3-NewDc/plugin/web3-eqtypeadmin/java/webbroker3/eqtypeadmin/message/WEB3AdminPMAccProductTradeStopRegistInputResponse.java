head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPMAccProductTradeStopRegistInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�ڋq�����ʎ����~�o�^���̓��X�|���X (WEB3AdminPMAccProductTradeStopRegistInputResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE�ڋq�����ʎ����~�o�^���̓��X�|���X)<BR>
 * <BR>
 * �Ǘ��ҁE�ڋq�����ʎ����~�o�^���̓��X�|���X�N���X<BR>
 * <BR>
 * WEB3AdminPMAccProductTradeStopRegistInputResponse<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminPMAccProductTradeStopRegistInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_pm_acc_product_trade_stop_regist_input";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * ----<English>--------------------
     * accTradeStopInfoList
     */
    public WEB3AdminPMAccTradeStopInfoUnit[] accTradeStopInfoList;

    /**
     * @@roseuid 41FD93B2000F
     */
    public WEB3AdminPMAccProductTradeStopRegistInputResponse()
    {

    }

    /**
     * @@roseuid 41FD94070000
     * @@param l_request WEB3GenRequest
     */
    public WEB3AdminPMAccProductTradeStopRegistInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}@
