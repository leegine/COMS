head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkBookPriceInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式ミニ投資簿価単価登録入力リクエスト(WEB3MstkBookPriceInputRequest.java)
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


/**
 * （株式ミニ投資簿価単価登録入力リクエスト）。<BR>
 * <BR>
 * 株式ミニ投資簿価単価登録入力リクエストクラス<BR>
 */
public class WEB3MstkBookPriceInputRequest extends WEB3GenRequest 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MstkBookPriceInputRequest.class);
        
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
     * @@roseuid 4206CF450154<BR>
     */
    public WEB3MstkBookPriceInputRequest() 
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
     * @@throws WEB3BaseException<BR>
     * @@roseuid 41C10FB70246<BR>
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
    }
    
    public WEB3GenResponse createResponse()
    {
        return new WEB3MstkBookPriceInputResponse(this);
    }
}
@
