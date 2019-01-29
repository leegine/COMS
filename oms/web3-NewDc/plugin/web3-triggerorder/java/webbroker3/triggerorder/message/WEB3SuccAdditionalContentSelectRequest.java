head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.41.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccAdditionalContentSelectRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 追加内容選択リクエスト(WEB3SuccAdditionalContentSelectRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/17 呉艶飛(中訊) 新規作成
*/

package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3CommodityDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (追加内容選択リクエスト)<BR>
 * 追加内容選択リクエストクラス<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3SuccAdditionalContentSelectRequest extends WEB3GenRequest 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SuccAdditionalContentSelectRequest.class);
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200510111000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_additionalContentSelect";

    /**
     * (（親注文）注文ID)<BR>
     * （親注文）注文ID。<BR>
     */
    public String parentOrderId;
    
    /**
     * (商品区分)<BR>
     * 親注文の商品区分<BR>
     * <BR>
     * 1：　@現物株式<BR>
     * 2：　@信用取引<BR>
     * 3：　@先物<BR>
     * 4：　@オプション<BR>
     */
    public String commodityType;
    
    /**
     * @@roseuid 4348960402EE
     */
    public WEB3SuccAdditionalContentSelectRequest() 
    {
     
    }
    
    /**
     * 当クラスの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@（親注文）注文IDのチェック<BR>
     * 　@１－１）　@this.（親注文）注文IDが未入力の場合、<BR>
     * 　@　@「注文IDが未入力」の例外をスローする。<BR>
     *  　@    class: WEB3BusinessLayerException <BR>
     *  　@    tag:   BUSINESS_ERROR_02258 <BR>
     * <BR>
     * ２）　@商品区分のチェック<BR>
     * 　@２－１）　@this.商品区分が未入力の場合、<BR>
     * 　@　@「商品区分が未入力」の例外をスローする。<BR>
     *  　@    class: WEB3BusinessLayerException <BR>
     *  　@    tag:   BUSINESS_ERROR_02182<BR>
     * <BR>
     * 　@２－２）　@this.商品区分が以下の値以外の場合、<BR>
     * 　@　@「未定義の商品区分が存在」の例外をスローする。<BR>
     *  　@    class: WEB3BusinessLayerException <BR>
     *  　@    tag:   BUSINESS_ERROR_01068<BR>
     * 　@　@　@"現物株式"<BR>
     * 　@　@　@"信用取引"<BR>
     * 　@　@　@"先物"<BR>
     * 　@　@　@"オプション"<BR>
     * @@roseuid 4327F9B201C4
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME =" validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@（親注文）注文IDのチェック
        //　@１－１）　@this.（親注文）注文IDが未入力の場合、
        // 　@　@「注文IDが未入力」の例外をスローする
        if (WEB3StringTypeUtility.isEmpty(this.parentOrderId))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02258,
                getClass().getName() + STR_METHOD_NAME,
                "（親注文）注文IDが未指定です。");
        }
        
        //２）　@商品区分のチェック
        // 　@２－１）　@this.商品区分が未入力の場合、
        // 　@　@「商品区分が未入力」の例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.commodityType))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02182,
                getClass().getName() + STR_METHOD_NAME,
                " 商品区分が未指定です。");
        }
        //２－２）　@this.商品区分が以下の値以外の場合、
        // 　@　@「未定義の商品区分が存在」の例外をスローする。
        if (!WEB3CommodityDivDef.EQUITY.equals(this.commodityType)
            && !WEB3CommodityDivDef.MARGIN.equals(this.commodityType)
            && !WEB3CommodityDivDef.FUTURE.equals(this.commodityType)
            && !WEB3CommodityDivDef.OPTION.equals(this.commodityType))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01068,
                getClass().getName() + STR_METHOD_NAME,
                "商品区分が存在しないコード値です。");
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * レスポンスデータを作成する。<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 40602AEA033F
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SuccAdditionalContentSelectResponse(this);
    }
}
@
