head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToEquityOrderRefInpResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・株式注文照会入力レスポンス(WEB3AdminToEquityOrderRefInpResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/03　@魏新(中訊) 新規作成
*/

package webbroker3.admintriggerorder.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (トリガー注文管理者・株式注文照会入力レスポンス)<BR>
 * 
 * @@author 魏新<BR>
 * @@version 1.0<BR>
 */
public class WEB3AdminToEquityOrderRefInpResponse extends WEB3GenResponse
{       
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_to_equity_order_ref_lnp";
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603031700L;

    /**
     * (市場一覧)<BR>
     * 取扱可能な市場の配列 <BR>
     * <BR>
     */
    public String[] marketList;

    /**
     * (条件注文種別一覧)<BR>
     * 条件注の種別一覧の配列 <BR>
     * <BR>
     * 4：　@逆指値注文<BR>
     * 5：　@W指値注文<BR>
     * <BR>
     */
    public String[] triggerOrderTypeList;

    /**
     * (発注日一覧)<BR>
     * 発注日の配列 <BR>
     * <BR>
     */
    public Date[] orderBizDateList;
    
    /**
     * コンストラクタ<BR>
     * @@roseuid 43F1B3C700AB
     */
    public WEB3AdminToEquityOrderRefInpResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminToEquityOrderRefInpResponse(WEB3AdminToEquityOrderRefInpRequest l_request)
    {
        super(l_request);
    } 
}
@
