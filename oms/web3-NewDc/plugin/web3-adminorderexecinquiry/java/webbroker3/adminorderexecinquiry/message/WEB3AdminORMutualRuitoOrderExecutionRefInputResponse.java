head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.46.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORMutualRuitoOrderExecutionRefInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・投信累投注文約定照会入力レスポンス(WEB3AdminORMutualRuitoOrderExecutionRefInputResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.adminorderexecinquiry.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・投信累投注文約定照会入力レスポンス)<BR>
 * <BR>
 * 管理者・投信累投注文約定照会入力レスポンスクラス<BR>
 * <BR>
 * WEB3AdminORMutualRuitoOrderExecutionRefInputResponse<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminORMutualRuitoOrderExecutionRefInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_or_mutual_ruito_order_execution_ref_input";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * (発注日一覧)<BR>
     * <BR>
     * 発注日の配列<BR>
     * <BR>
     * An array of orderBizDate<BR>
     * <BR>
     */
    public Date[] orderBizDateList;

    /**
     * (注文経路区分一覧)<BR>
     * <BR>
     * 注文経路区分の一覧<BR>
     */
    public String[] orderRootList = null;
    
    /**
     * （取扱商品一覧）<BR>
     * <BR>
     */
    public WEB3AdminORTradingProductUnit[] tradingProductList;

    /**
     * （銘柄一覧）<BR>
     * <BR>
     */
    public WEB3AdminORProductNameSetUnit[] productNameSetList;

    /**
     * @@roseuid 4212FC140259
     */
    public WEB3AdminORMutualRuitoOrderExecutionRefInputResponse()
    {

    }

    /**
     *
     * @@param l_request WEB3GenRequest
     */
    public WEB3AdminORMutualRuitoOrderExecutionRefInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
