head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.43.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORFutOpOrderExecutionRefInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・先物OP注文約定照会入力レスポンス (WEB3AdminORFutOpOrderExecutionRefInputResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.adminorderexecinquiry.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・先物OP注文約定照会入力レスポンス)<BR>
 * <BR>
 * 管理者・先物OP注文約定照会入力レスポンスクラス<BR>
 * <BR>
 * WEB3AdminORFutOpOrderExecutionRefInputResponse<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminORFutOpOrderExecutionRefInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_or_fut_op_order_execution_ref_input";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * (発注日一覧)<BR>
     * <BR>
     * 発注日の配列<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * An array of orderBizDate<BR>
     * <BR>
     */
    public Date[] orderBizDateList;

    /**
     * (指数種別一覧)<BR>
     * <BR>
     * 指数種別の配列<BR>
     * <BR>
     * An array of targetProduct<BR>
     * <BR>
     */
    public String[] targetProductList;

    /**
     * (執行条件一覧)<BR>
     * <BR>
     * 執行条件の配列<BR>
     * <BR>
     * An array of execCond<BR>
     * <BR>
     */
    public String[] execCondList = null;

    /**
     * (注文期限区分一覧)<BR>
     * <BR>
     * 注文期限区分の配列<BR>
     * <BR>
     * An array of expirationDateType<BR>
     * <BR>
     */
    public String[] expirationDateTypeList = null;

    /**
     * (発注条件一覧)<BR>
     * <BR>
     * 発注条件の配列<BR>
     * <BR>
     * An array of orderCondType<BR>
     * <BR>
     */
    public String[] orderCondTypeList = null;

	/**
	 * (注文経路一覧)<BR>
	 * <BR>
	 * 注文経路一覧の配列<BR>
	 * <BR>
	 */
	public String[] orderRootList = null;

    /**
     * （取扱商品一覧）<BR>
     * <BR>
     */
    public WEB3AdminORTradingProductUnit[] tradingProductList;

    /**
     * @@roseuid 4212FBAB0111
     */
    public WEB3AdminORFutOpOrderExecutionRefInputResponse()
    {

    }

    /**
     * @@param l_request l_request
     * @@roseuid 41FD94070000
     */
    public WEB3AdminORFutOpOrderExecutionRefInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
