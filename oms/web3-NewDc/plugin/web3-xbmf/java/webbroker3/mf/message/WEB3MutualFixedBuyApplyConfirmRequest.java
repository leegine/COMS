head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.02.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyApplyConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信定時定額買付新規申込確認リクエスト(WEB3MutualFixedBuyApplyConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/26 徐宏偉 (中訊) 新規作成
*/
package webbroker3.mf.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (投信定時定額買付新規申込確認リクエスト) <BR>
 * 投信定時定額買付新規申込確認リクエスト <BR>
 * 
 * @@author 徐宏偉(中訊)
 * @@version 1.0 
 */
public class WEB3MutualFixedBuyApplyConfirmRequest 
    extends WEB3MutualFixedBuyCommonRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mutual_fixed_buy_apply_confirm";
    
    /**
     * SerialVersionUID<BR>
     */   
    public final static long serialVersionUID = 200606261700L;

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFixedBuyApplyConfirmRequest.class);

    /**
     * (投信定時定額買付新規申込確認リクエスト)<BR>
     * デフォルトコンストラクタ<BR>
     */   
    public WEB3MutualFixedBuyApplyConfirmRequest()
    {
    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>  
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>  
     * <BR>
     * １）　@スーパークラスのvalidateメソッドを呼び出す。<BR> 
     * <BR>
     * ２)　@投信定時定額買付共通情報一覧の要素数分繰り返してチェックを行う。<BR> 
     * 　@２－１）買付金額（月々）== 0 の場合、例外をスローする。<BR> 
     * 　@　@　@　@　@　@　@（買付金額入力エラー）<BR>
     * 　@　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_02475 <BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {   
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);    
         
        //１）　@スーパークラスのvalidateメソッドを呼び出す。
        super.validate();
        
        // ２)　@投信定時定額買付共通情報一覧の要素数分繰り返してチェックを行う。       
        //　@２－１）買付金額（月々）== 0 の場合、例外をスローする。      
        int l_intcommonListLength = this.commonList.length; 
        for (int i = 0; i < l_intcommonListLength; i++)
        {
        	String l_strMonthlyBuyAmount = this.commonList[i].monthlyBuyAmount;
        	double l_dblMonthlyBuyAmount = Double.parseDouble(l_strMonthlyBuyAmount);
            if (l_dblMonthlyBuyAmount == 0)
            {
                log.debug("買付金額（月々）== 0 の場合、例外をスローする。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
	                WEB3ErrorCatalog.BUSINESS_ERROR_02475,
	                this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        log.exiting(STR_METHOD_NAME);  
    }
   
    /**
     *（createResponseの実装）<BR>
     * <BR>
     * 投信定時定額買付新規申込確認レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MutualFixedBuyApplyConfirmResponse(this);
    }
}
@
