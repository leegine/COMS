head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.24.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqProductInformationUpdateDetailsInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式銘柄情報更新明細入力レスポンス(WEB3AdminFeqProductInformationUpdateDetailsInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/27 鄭海良(中訊) 新規作成
                 : 2005/08/02 郭英(中訊) レビュー
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者外国株式銘柄情報更新明細入力レスポンス)<BR>
 * 管理者外国株式銘柄情報更新明細入力レスポンスクラス
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminFeqProductInformationUpdateDetailsInputResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_feq_productInformationUpdateDetailsInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121526L;
        
    /**
     * (銘柄コード)<BR>
     * 銘柄コード
     */
    public String productCode;
    
    /**
     * (銘柄名（カナ）)<BR>
     * 銘柄名（カナ）
     */
    public String productNameKana;
    
    /**
     * (銘柄名（漢字）)<BR>
     * 銘柄名（漢字）
     */
    public String productNameKanji;
    
    /**
     * (市場コード)<BR>
     * 市場コード<BR>
     * <BR>
     * N1：香港<BR>
     * N2：深セン<BR>
     * X1：上海
     */
    public String marketCode;
    
    /**
     * (買付停止区分)<BR>
     * 買付停止区分<BR>
     * <BR>
     * 0：買付可能<BR>
     * 1：買付停止
     */
    public String buyStopDiv;
    
    /**
     * (売付停止区分)<BR>
     * 売付停止区分<BR>
     * <BR>
     * 0：売付可能<BR>
     * 1：売付停止
     */
    public String sellStopDiv;
    
    /**
     * (現地銘柄コード)<BR>
     * 現地銘柄コード
     */
    public String localProductCode;
    
    /**
     * (買付単位)<BR>
     * 買付単位
     */
    public String buyUnit;
    
    /**
     * (最低買付単位)<BR>
     * 最低買付単位
     */
    public String minBuyUnit;
    
    /**
     * (売付単位)<BR>
     * 売付単位
     */
    public String sellUnit;
    
    /**
     * (最低売付単位)<BR>
     * 最低売付単位
     */
    public String minSellUnit;
    
    /**
     * @@roseuid 42CE39FA01A5
     */
    public WEB3AdminFeqProductInformationUpdateDetailsInputResponse() 
    {
     
    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminFeqProductInformationUpdateDetailsInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }    

}
@
