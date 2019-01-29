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
filename	WEB3AdminToIfoOrderRefInpResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・先物OP注文照会入力レスポンス(WEB3AdminToIfoOrderRefInpResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/16　@余新敏(中訊) 新規作成
*/

package webbroker3.admintriggerorder.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (トリガー注文管理者・先物OP注文照会入力レスポンス)<BR>
 * 
 * @@author 余新敏
 * @@version 1.0
 */
public class WEB3AdminToIfoOrderRefInpResponse extends WEB3GenResponse
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_to_ifo_order_ref_inp";
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200602141850L;
    
    /**
     * (発注日一覧)<BR>
     * 発注日の配列<BR>
     */
    public Date[] orderBizDateList;
    
    /**
     * (条件注文種別一覧)<BR>
     * 条件注文種別の配列<BR>
     */
    public String[] triggerOrderTypeList;

    /**
     * (指数種別一覧)<BR>
     * 指数種別の配列<BR>
     */
    public String[] targetProductList;    
    
    /**
     * コンストラクタ<BR>
     * @@roseuid 43F1B3C80399
     */
    public WEB3AdminToIfoOrderRefInpResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminToIfoOrderRefInpResponse(WEB3AdminToIfoOrderRefInpRequest l_request)
    {
        super(l_request);
    } 
}
@
