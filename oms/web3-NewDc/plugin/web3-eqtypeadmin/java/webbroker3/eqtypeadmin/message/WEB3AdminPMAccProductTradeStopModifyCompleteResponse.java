head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPMAccProductTradeStopModifyCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�ڋq�����ʎ����~�ύX�������X�|���X
                        (WEB3AdminPMAccProductTradeStopModifyCompleteResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�Ǘ��ҁE�ڋq�����ʎ����~�ύX�������X�|���X�j<BR>
 * <BR>
 * �Ǘ��ҁE�ڋq�����ʎ����~�ύX�������X�|���X�N���X<BR>
 * <BR>
 * WEB3AdminPMAccProductTradeStopModifyCompleteResponse<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminPMAccProductTradeStopModifyCompleteResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_pm_acc_product_trade_stop_modify_complete";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
    * �i���ݓ����j<BR>
    * <BR>
    * ���ݓ���<BR>
    * <BR>
    * currentDate<BR>
    * <BR>
    */
    public Date currentDate;

    /**
     * �i�ڋq�����ʎ����~���j
     * �ڋq�����ʎ����~���
     * ----<English>--------------------
     * accProductTradeStopInfo
     */
    public WEB3AdminPMAccProductTradeStopInfoUnit accProductTradeStopInfo;

    /**
     * @@roseuid 41FD94160000
     */
    public WEB3AdminPMAccProductTradeStopModifyCompleteResponse()
    {

    }

    /**
     * @@roseuid 41FD94160000
     * @@param l_request l_request
     */
    public WEB3AdminPMAccProductTradeStopModifyCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
