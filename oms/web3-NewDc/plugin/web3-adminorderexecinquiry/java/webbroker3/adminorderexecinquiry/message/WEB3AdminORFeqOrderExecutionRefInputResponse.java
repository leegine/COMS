head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.44.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORFeqOrderExecutionRefInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・外国株式注文約定照会入力レスポンス(WEB3AdminORFeqOrderExecutionRefInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/22 鄭海良(中訊) 新規作成
                 : 2005/08/02 郭英(中訊) レビュー
*/

package webbroker3.adminorderexecinquiry.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・外国株式注文約定照会入力レスポンス)<BR>
 * 管理者・外国株式注文約定照会入力レスポンスクラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminORFeqOrderExecutionRefInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_Feq_ORFeqOrderExecutionRefInput";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200507130937L;
    
    /**
     * (市場コード一覧)<BR>
     * 市場コードの配列<BR>
     */
    public String[] marketList;
    
    /**
     * (執行条件一覧)<BR>
     * 執行条件の配列<BR>
     */
    public String[] execCondTypeList = null;
    
    /**
     * (注文期限区分一覧)<BR>
     * 注文期限区分の配列<BR>
     */
    public String[] expirationDateTypeList = null;
    
    /**
     * (発注条件一覧)<BR>
     * 発注条件の配列<BR>
     */
    public String[] orderCondTypeList = null;
    
    /**
     * (注文経路区分一覧)<BR>
     * 注文経路区分の一覧<BR>
     */
    public String[] orderRootDivList;
    
    /**
     * (発注日一覧)<BR>
     * 発注日の配列<BR>
     */
    public Date[] orderBizDateList;
    
    /**
     * (取扱商品一覧)<BR>
     */
    public WEB3AdminORTradingProductUnit[] tradingProductList;
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminORFeqOrderExecutionRefInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
}
@
