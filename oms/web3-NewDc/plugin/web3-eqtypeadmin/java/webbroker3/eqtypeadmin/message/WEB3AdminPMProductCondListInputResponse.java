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
filename	WEB3AdminPMProductCondListInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・株式銘柄条件予定一覧入力レスポンス(WEB3AdminPMProductCondListInputResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・株式銘柄条件予定一覧入力レスポンス)<BR>
 * <BR>
 * 管理者・株式銘柄条件予定一覧入力レスポンスクラス<BR>
 * <BR>
 * WEB3AdminPMProductCondListInputResponse<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminPMProductCondListInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_pm_product_cond_list_input";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * （現在日時）<BR>
     * <BR>
     * 現在日時<BR>
     * <BR>
     * currentDate<BR>
     * <BR>
     */
    public Date currentDate;

    /**
     * （項目区分情報一覧）<BR>
     * <BR>
     * 項目区分情報一覧
     * <BR>
     * itemInfoList<BR>
     * <BR>
     */
    public WEB3AdminPMItemInfoUnit[] itemInfoList;

    /**
     * @@roseuid 41FD92D200BB
     */
    public WEB3AdminPMProductCondListInputResponse()
    {

    }

    /**
     *
     * @@param l_request WEB3GenRequest
     */
    public WEB3AdminPMProductCondListInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
