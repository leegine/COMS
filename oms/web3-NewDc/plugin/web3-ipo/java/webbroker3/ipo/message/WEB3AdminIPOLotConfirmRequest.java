head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.41.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOLotConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO抽選割当確認リクエスト(WEB3AdminIPOLotConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/20 鄭徳懇 (中訊) 新規作成
*/

package webbroker3.ipo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者IPO抽選割当確認リクエスト)<BR>
 *  管理者IPO抽選割当確認リクエストクラス<BR>
 * 
 * @@author 鄭徳懇
 * @@version 1.0
 */
public class WEB3AdminIPOLotConfirmRequest extends WEB3IPOLotCommonRequest 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIPOLotConfirmRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_IPO_lotConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200512192100L;
    
    /**
     * 部店コード
     */
    public String[] branchCode;
    
    /**
     * 割当総枠数量
     */
    public String allotTotalQuantity;
    
    /**
     * (割当上限数量)<BR>
     * 割当上限数量（１ループあたり）。
     */
    public String allotLimitQuantity;
    
    /**
     * 補欠割当総枠数量
     */
    public String waitingAllotTotalQuantity;
    
    /**
     * (補欠割当上限数量)<BR>
     * 補欠割当上限数量（１ループあたり）。
     */
    public String waitingAllotLimitQuantity;
    
    /**
     * @@roseuid 4112DAD60041
     */
    public WEB3AdminIPOLotConfirmRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）　@スーパークラスのvalidate()をコールする。 <BR>
     * <BR>
     * ２）　@割当総枠数量チェック<BR>
     * 　@　@　@this.割当総枠数量 != null　@の場合、以下のチェックをおこなう。<BR>
     * <BR>
     * 　@　@　@２－１）　@this.割当総枠数量 != 数値 の場合,例外をスロー<BR>
     *                 エラーメッセージ「割当総枠数量が数字以外の値です。」
     *                 の例外をスローする。<BR>
     *              class: WEB3BusinessLayerException<BR>
     *              tag:   BUSINESS_ERROR_02314<BR>
     * <BR>
     * ３）　@割当上限数量チェック<BR>
     * 　@　@　@this.割当上限数量 != null　@の場合、以下のチェックをおこなう。<BR>
     * <BR>
     * 　@　@　@３－１）　@this.割当上限数量 != 数値 の場合,例外をスロー<BR>
     *                 エラーメッセージ「割当上限数量が数字以外の値です。」<BR>
     *                 の例外をスローする。<BR>
     *              class: WEB3BusinessLayerException<BR>
     *              tag:   BUSINESS_ERROR_02315<BR>
     * <BR>
     * ４）　@補欠割当総枠数量チェック<BR>
     * 　@　@　@this.補欠割当総枠数量 != null　@の場合、以下のチェックをおこなう。<BR>
     * <BR>
     * 　@　@　@４－１）　@this.補欠割当総枠数量 != 数値 の場合,例外をスロー<BR>
     *                 エラーメッセージ「補欠割当総枠数量が数字以外の値です。」<BR>
     *                 の例外をスローする。<BR>
     *              class: WEB3BusinessLayerException<BR>
     *              tag:   BUSINESS_ERROR_02316<BR>
     * <BR>
     * ５）　@補欠割当上限数量チェック<BR>
     * 　@　@　@this.補欠割当上限数量 != null　@の場合、以下のチェックをおこなう。<BR>
     * <BR>
     * 　@　@　@５－１）　@this.補欠割当上限数量 != 数値 の場合,例外をスロー<BR>
     *                 エラーメッセージ「補欠割当上限数量が数字以外の値です。」<BR>
     *                 の例外をスローする。<BR>
     *              class: WEB3BusinessLayerException<BR>
     *              tag:   BUSINESS_ERROR_02317<BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）スーパークラスのvalidate()をコールする。
        super.validate();
        
        //２－１）　@this.割当総枠数量 != 数値 の場合,例外をスロー
        //   エラーメッセージ「割当総枠数量が数字以外の値です。」
        if (this.allotTotalQuantity != null && !WEB3StringTypeUtility.isNumber(this.allotTotalQuantity)) 
        {
            log.debug("割当総枠数量が数値以外です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02314, 
                getClass().getName() + "." + STR_METHOD_NAME,
                "割当総枠数量が数値以外です。");
        }
        
        //３－１）　@this.割当上限数量 != 数値 の場合,例外をスロー
        //  エラーメッセージ「割当上限数量が数字以外の値です。」
        if (this.allotLimitQuantity != null && !WEB3StringTypeUtility.isNumber(this.allotLimitQuantity)) 
        {
            log.debug("割当上限数量が数値以外です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02315,
                getClass().getName() + "." + STR_METHOD_NAME,
                "割当上限数量が数値以外です。");
        }
        
        //４－１） this.補欠割当総枠数量 != 数値 の場合,例外をスロー
        //       エラーメッセージ「補欠割当総枠数量が数字以外の値です。」
        if (this.waitingAllotTotalQuantity != null && !WEB3StringTypeUtility.isNumber(this.waitingAllotTotalQuantity)) 
        {
            log.debug("補欠割当総枠数量が数値以外です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02316,
                getClass().getName() + "." + STR_METHOD_NAME,
                "補欠割当総枠数量が数値以外です。");
        }
        
        //５－１）　@this.補欠割当上限数量 != 数値 の場合,例外をスロー
        //        エラーメッセージ「補欠割当上限数量が数字以外の値です。」
        if (this.waitingAllotLimitQuantity != null && !WEB3StringTypeUtility.isNumber(this.waitingAllotLimitQuantity)) 
        {
            log.debug("補欠割当上限数量が数値以外です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02317,
                getClass().getName() + "." + STR_METHOD_NAME,
                "補欠割当上限数量が数値以外です。");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112DAD60055
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminIPOLotConfirmResponse(this);
    }
}
@
