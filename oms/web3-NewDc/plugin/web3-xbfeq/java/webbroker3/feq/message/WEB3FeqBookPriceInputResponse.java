head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.32.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqBookPriceInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式簿価単価登録入力レスポンス(WEB3FeqBookPriceInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 黄建 (中訊) 新規作成
                 : 2005/08/01 郭英(中訊) レビュー   
*/

package webbroker3.feq.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (外国株式簿価単価登録入力レスポンス)<BR>
 * 外国株式簿価単価登録入力レスポンスクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FeqBookPriceInputResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "feq_bookPriceInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121500L;
    
    /**
     * (保有資産ID)<BR>
     * 保有資産ID<BR>
     */
    public String assetId;
    
    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     */
    public String productCode;
    
    /**
     * (現地銘柄コード)<BR>
     * 現地銘柄コード<BR>
     */
    public String localProductCode;
    
    /**
     * (銘柄名)<BR>
     * 銘柄名<BR>
     */
    public String productName;
    
    /**
     * (口座区分)<BR>
     * 0：一般　@1：特定<BR>
     */
    public String taxType;
    
    /**
     * (残高株数)<BR>
     * 残高株数<BR>
     */
    public String balanceQuantity;
    
    /**
     * (売付可能株数)<BR>
     * 売付可能株数<BR>
     */
    public String sellPossQuantity;
    
    /**
     * (注文中株数)<BR>
     * 注文中株数<BR>
     */
    public String orderedQuantity;
    
    /**
     * (売付不能株数)<BR>
     * 売付不能株数<BR>
     */
    public String sellImpossQuantity;
    
    /**
     * (入力簿価単価)<BR>
     * 入力簿価単価<BR>
     * <BR>
     * ※前回入力した値を表示。<BR>
     * 　@未入力の場合はnullをセット。<BR>
     */
    public String inputBookPrice = null;
    
    /**
     * (簿価単価入力日時)<BR>
     * 簿価単価入力日時<BR>
     * <BR>
     * ※前回入力した日時を表示。<BR>
     * 　@未入力の場合はnullをセット。<BR>
     */
    public Date bookPriceInputDate = null;
    
    /**
     * (概算簿価単価)<BR>
     * 概算簿価単価<BR>
     */
    public String estimatedBookPrice = null;
    
    /**
     * @@roseuid 42CE3A05000F
     */
    public WEB3FeqBookPriceInputResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3FeqBookPriceInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}
@
