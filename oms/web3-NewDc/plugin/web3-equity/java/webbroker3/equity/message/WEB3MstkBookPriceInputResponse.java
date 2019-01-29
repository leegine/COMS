head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkBookPriceInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式ミニ投資簿価単価登録入力レスポンス(WEB3MstkBookPriceInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/14 沢村　@仁士(SRA) 新規作成
*/

package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;


/**
 * （株式ミニ投資簿価単価登録入力レスポンス）。<BR>
 * <BR>
 * 株式ミニ投資簿価単価登録入力レスポンスクラス<BR>
 */
public class WEB3MstkBookPriceInputResponse extends WEB3GenResponse 
{
        
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200502141000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mstk_book_price_input";
    
    /**
     * (保有資産ID)<BR>
     * <BR>
     * 保有資産ID<BR>
     */
    public String assetId;
    
    /**
     * (銘柄コード)<BR>
     * <BR>
     * 銘柄コード<BR>
     */
    public String productCode;
    
    /**
     * (銘柄名)<BR>
     * <BR>
     * 銘柄名<BR>
     */
    public String productName;
    
    /**
     * (口座区分)<BR>
     * <BR>
     * 0：一般　@1：特定<BR>
     */
    public String taxType;
    
    /**
     * (残高株数)<BR>
     * <BR>
     * 残高株数<BR>
     */
    public String balanceQuantity;
    
    /**
     * (入力簿価単価)<BR>
     * <BR>
     * 入力簿価単価<BR>
     * <BR>
     * ※前回入力した値を表示。<BR>
     * 　@未入力の場合はnullをセット。<BR>
     */
    public String inputBookPrice = null;
    
    /**
     * (簿価単価入力日時)<BR>
     * <BR>
     * 簿価単価入力日時<BR>
     * <BR>
     * ※前回入力した日時を表示。<BR>
     * 　@未入力の場合はnullをセット。<BR>
     */
    public Date bookPriceInputDate = null;
    
    /**
     * (概算簿価単価)<BR>
     * <BR>
     * 概算簿価単価<BR>
     */
    public String estimatedBookPrice = null;
    
    /**
     * @@roseuid 4206CF45029C<BR>
     */
    public WEB3MstkBookPriceInputResponse() 
    {
     
    }
    
    public WEB3MstkBookPriceInputResponse(WEB3MstkBookPriceInputRequest l_request)
    {
        super(l_request);
    }
}
@
