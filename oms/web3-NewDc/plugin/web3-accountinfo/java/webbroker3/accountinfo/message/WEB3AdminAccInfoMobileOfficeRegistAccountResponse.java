head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.11.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMobileOfficeRegistAccountResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ÇÒ¨qlîñgÑÔEÎ±æîñÏX\ÚqÚ½ÎßÝ½(WEB3AdminAccInfoMobileOfficeRegistAccountResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 JN°V (u) VKì¬
                   2006/12/18 ü· (u) fNo.153
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (ÇÒ¨qlîñgÑÔEÎ±æîñÏX\ÚqÚ½ÎßÝ½)<BR>
 * ÇÒ¨qlîñgÑÔEÎ±æîñÏX\ÚqÚ½ÎßÝ½<BR>
 * @@author JN°V
 * @@version 1.0 
 */
public class WEB3AdminAccInfoMobileOfficeRegistAccountResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mobileOfficeRegistAccount";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082112L;

    /**
     * (y[W)<BR>
     * y[W<BR>
     */
    public String totalPages;

    /**
     * (R[h)<BR>
     * R[h<BR>
     */
    public String totalRecords;

    /**
     * (\¦y[WÔ)<BR>
     * \¦y[WÔ<BR>
     */
    public String pageIndex;

    /**
     * (ÏXO\Úqê)<BR>
     */
    public WEB3AccInfoMobileOfficeChangeAccount[] beforeChangeAccountList;

    /**
     * (ÏXã\Úqê)<BR>
     */
    public WEB3AccInfoMobileOfficeChangeAccount[] afterChangeAccountList;

    /**
     * (ÇÒ¨qlîñgÑÔEÎ±æîñÏX\Úqâ¹Ú½ÎßÝ½)<BR>
     * RXgN^<BR>
     * @@param l_request - (l_request)<BR>
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistAccountResponse
     * @@roseuid 414981B802DA
     */
    public WEB3AdminAccInfoMobileOfficeRegistAccountResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
