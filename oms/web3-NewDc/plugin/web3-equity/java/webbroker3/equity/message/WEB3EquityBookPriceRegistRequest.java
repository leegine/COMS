head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityBookPriceRegistRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式簿価単価登録リクエスト(WEB3EquityBookPriceRegistRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/14 沢村　@仁士(SRA) 新規作成
*/

package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * （現物株式簿価単価登録リクエスト）。<BR>
 * <BR>
 * 現物株式簿価単価登録リクエストクラス<BR>
 */
public class WEB3EquityBookPriceRegistRequest extends WEB3GenRequest 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityBookPriceRegistRequest.class);
        
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200502141000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_book_price_regist";
    
    /**
     * (保有資産ID)<BR>
     * <BR>
     * 保有資産ID<BR>
     */
    public String assetId;
    
    /**
     * (変更後概算簿価単価)<BR>
     * <BR>
     * 変更後概算簿価単価<BR>
     */
    public String aftBookPrice;
    
    /**
     * @@roseuid 4206CF2F01D1<BR>
     */
    public WEB3EquityBookPriceRegistRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）保有資産IDチェック<BR>
     * 　@１－１）保有資産IDがnullの場合、<BR>
     * 　@　@「保有資産IDが未入力」の例外をスローする。<BR>
     * <BR>
     * ２）変更後概算簿価単価チェック<BR>
     * 　@２－１）変更後概算簿価単価がnullの場合、<BR>
     * 　@　@「変更後概算簿価単価が未入力」の例外をスローする。<BR>
     * <BR>
     * 　@２－２）変更後概算簿価単価が以下の条件に該当する場合、<BR>
     * 　@　@「変更後概算簿価単価が不正な値」の例外をスローする。<BR>
     * 　@　@　@・変更後概算簿価単価 != 数値<BR>
     * 　@　@　@・変更後概算簿価単価 <= 0<BR>
     * 　@　@　@・変更後概算簿価単価の桁数 > 8桁<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 41B65CD401C7<BR>
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        // 保有資産IDのチェック
        if (this.assetId == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01919,
                STR_METHOD_NAME,
                "保有資産IDが未入力です。");
        }
        
        // 変更後簿価単価チェック
        if (this.aftBookPrice == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01920,
                STR_METHOD_NAME,
                "変更後簿価単価が未入力です。");
        }
        
        // 数値チェック
        if (WEB3StringTypeUtility.isNumber(this.aftBookPrice))
        {
            // 桁数チェック
            if (this.aftBookPrice.length() > 8)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01920,
                    STR_METHOD_NAME,
                    "変更後簿価単価が8桁を超える値です。");
            }
            
            // 0以下チェック
            double l_dblBookValuePrice = Double.parseDouble(this.aftBookPrice);
            if (l_dblBookValuePrice < 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01920,
                    STR_METHOD_NAME,
                    "変更後簿価単価が0未満の値です。");
            }
        }
        else
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01920,
                STR_METHOD_NAME,
                "変更後簿価単価が数値以外の値です。");
        }
    }
    
    public WEB3GenResponse createResponse()
    {
        return new WEB3EquityBookPriceRegistResponse(this);
    }
}
@
