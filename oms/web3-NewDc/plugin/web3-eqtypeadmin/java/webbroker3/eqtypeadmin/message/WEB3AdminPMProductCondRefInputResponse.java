head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPMProductCondRefInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�������������Ɖ�������̓��X�|���X(WEB3AdminPMProductCondRefInputResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE�������������Ɖ�������̓��X�|���X)<BR>
 * <BR>
 * �Ǘ��ҁE�������������Ɖ�������̓��X�|���X�N���X<BR>
 * <BR>
 * WEB3AdminPMProductCondRefInputResponse<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminPMProductCondRefInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_pm_product_cond_ref_input";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * �i�c�Ɠ��ꗗ�j<BR>
     * <BR>
     * �c�Ɠ��̈ꗗ<BR>
     * <BR>
     * �����c�Ɠ��A���c�Ɠ��A���X�c�Ɠ��̏��Ŋi�[<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * bizDateList<BR>
     * <BR>
     * Store in order of bizDate, nextBizDate and next2BizDate<BR>
     * <BR>
     */
    public Date[] bizDateList;

    /**
     * @@roseuid 41FA2E0E00FA
     */
    public WEB3AdminPMProductCondRefInputResponse()
    {

    }

    /**
     *
     * @@param l_request WEB3GenRequest
     */
    public WEB3AdminPMProductCondRefInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
