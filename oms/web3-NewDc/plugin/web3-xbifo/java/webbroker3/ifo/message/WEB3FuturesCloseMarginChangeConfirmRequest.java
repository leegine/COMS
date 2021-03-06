head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.19.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesCloseMarginChangeConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物訂正返済確認リクエスト(WEB3FuturesCloseMarginChangeConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/20 李媛 (中訊) 新規作成
Revesion History : 2008/03/12 張騰宇　@仕様変更モデル825
*/

package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * 株価指数先物訂正返済確認リクエスト<BR>
 * 株価指数先物訂正返済確認リクエストクラス<BR>
 * 
 * @@author 李媛
 * @@version 1.0 
 */
public class WEB3FuturesCloseMarginChangeConfirmRequest extends WEB3FuturesCommonRequest 
{
    
    /**         
     * ログ出力ユーティリティ。         
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesCloseMarginChangeConfirmRequest.class);
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE ="futures_closeMarginChangeConfirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200407201038L;    
   
    /**
     * (注文ＩＤ)<BR>
     */
    public String id;
   
    /**
     * (返済建玉)<BR>
     */
    public WEB3FuturesOptionsCloseMarginContractUnit[] closeMarginContractUnits;
   
    /**
     * @@roseuid 40C0A8EA0399
     */
    public WEB3FuturesCloseMarginChangeConfirmRequest() 
    {
    
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FuturesCloseMarginChangeConfirmResponse(this);
    }
   
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で簡潔する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@スーパークラスのvalidateメソッドを呼び出す<BR>
     * <BR>
     * ２）　@ＩＤチェック<BR>
     * 　@this.ＩＤがnullの値であれば例外をスローする。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00080<BR>
     * <BR>
     * ３）　@返済建玉チェック<BR>
     * 　@this.返済建玉がnullの値または、要素数が０で<BR>
     * 　@あれば例外をスローする。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00178<BR>
     * <BR>
     * ４）　@注文数量チェック<BR>
     * 　@４−１）this.注文数量がnull以外の値でかつ、<BR>
     * 　@　@　@　@this.注文数量が数字以外の値であれば例外をスローする。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00075<BR>
     * 　@４−２）this.注文数量がnull以外の値でかつ、<BR>
     * 　@　@　@　@this.注文数量≦０であれば例外をスローする。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00076<BR>
     * <BR>
     * ５）　@返済建玉要素チェック<BR>
     *    返済建玉の要素数分以下のチェックを繰り返して行う。<BR>
     *    ５−１）返済建玉のvalidate()メソッドを呼び出す。<BR>
     * <BR>
     * ６）　@返済建玉総数量チェック<BR>
     *　@　@this.注文数量 = null and<BR>
     *  （すべての返済建玉の数量=0 or null） の場合、<BR>
     *  　@　@例外をスローする。<BR>
     *      class:WEB3BusinessLayerException<BR>
     *      tag:BUSINESS_ERROR_00285<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40696B060253
     */
    public void validate() throws WEB3BaseException 
    {
        log.entering("validate()");
        //１）　@スーパークラスのvalidateメソッドを呼び出す
        super.validate();
        log.debug("super.validate().....OK>>>>>");
        
        //２）　@ＩＤチェック
        //this.ＩＤがnullの値であれば例外をスローする。
        if(WEB3StringTypeUtility.isEmpty(this.id))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                getClass().getName() + ".validate()",
                "ＩＤがnullの値である。");
        }
        log.debug("ＩＤチェック.....OK>>>>>");
        
        //３）　@返済建玉チェック
        //this.返済建玉がnullの値または、要素数が０で
        //あれば例外をスローする。
        if(this.closeMarginContractUnits == null 
            || this.closeMarginContractUnits.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00178,            
                getClass().getName() + ".validate()",
                "返済建玉がnullの値または、要素数が０である場合のエラー");
        }
        log.debug("返済建玉チェック.....OK>>>>>");
        
        //４）　@注文数量チェック
        //４−１）this.注文数量がnull以外の値でかつ、
        // 　@　@　@ this.注文数量が数字以外の値であれば例外をスローする。
        if(WEB3StringTypeUtility.isNotEmpty(this.futOrderQuantity) 
            && !(WEB3StringTypeUtility.isNumber(this.futOrderQuantity)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00075,
                getClass().getName() + ".validate()",
                "注文数量が数字以外の値である。");
        }
        log.debug("注文数量チェック(1).....OK>>>>>");
        
        //４−２）this.注文数量がnull以外の値でかつ、<BR>
        // 　@　@　@　@this.注文数量≦０であれば例外をスローする。
        if(WEB3StringTypeUtility.isNotEmpty(this.futOrderQuantity) 
            && Long.parseLong(this.futOrderQuantity) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00076,
                getClass().getName() + ".validate()",
                "注文数量が0以下の値である。");
        }
        log.debug("注文数量チェック(2).....OK>>>>>");
        
        //返済建玉要素チェック
        // ６）　@返済建玉総数量チェック<BR>
        //　@　@this.注文数量 = null and<BR>
        //　@ （すべての返済建玉の数量=0 or null） の場合、<BR>
        // 　@　@例外をスローする。<BR>
        //     class:WEB3BusinessLayerException<BR>
        //     tag:BUSINESS_ERROR_00285<BR>
        int l_intContractUnitsLength = closeMarginContractUnits.length;
        int l_intQuantityCnt = 0;
        if (this.futOrderQuantity == null)
        {
            for (int i = 0; i < l_intContractUnitsLength; i++)
            {
                //５−１）返済建玉のvalidate()メソッドを呼び出す。
                closeMarginContractUnits[i].validate();
                
                if (closeMarginContractUnits[i].contractOrderQuantity == null
                    || Double.parseDouble(closeMarginContractUnits[i].contractOrderQuantity) == 0)
                {
                    l_intQuantityCnt += 1;
                }
            }
        }
        
        // すべての返済建玉の数量=0 or nullの場合、例外をスローする。
        if (l_intQuantityCnt == l_intContractUnitsLength)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00285,
                this.getClass().getName() + "validate",
                "すべての返済建玉の数量が０である");
        }
    }

    /**
     * (validateAT反対取引)<BR>
     * 反対取引指定時の、当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * （連続注文用のメソッド） <BR>
     * <BR>
     * <BR>
     * １）　@スーパークラスのvalidateメソッドを呼び出す <BR>
     * <BR>
     * ２）　@ＩＤチェック <BR>
     * 　@this.ＩＤ=null の場合、例外をスローする。 <BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag: BUSINESS_ERROR_00080<BR>
     * <BR>
     * ３）　@返済建玉チェック <BR>
     * 　@３−１）this.返済建玉=null の場合、 <BR>
     * 　@　@　@　@　@例外をスローする。 <BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag: BUSINESS_ERROR_00178<BR>
     * 　@３−２）this.返済建玉の要素数=0 の場合、 <BR>
     * 　@　@　@　@　@例外をスローする。 <BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag: BUSINESS_ERROR_00178<BR>
     * <BR>
     * ４）　@連続注文・注文条件チェック <BR>
     * 　@スーパークラスのvalidate連続注文メソッドをコールする。<BR>
     * @@throws WEB3BaseException
     */
    public void validateAtReverseOrder() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateAtReverseOrder()";
        log.entering(STR_METHOD_NAME);

        //１）　@スーパークラスのvalidateメソッドを呼び出す
        super.validate();

        //２）　@ＩＤチェック
        //this.ＩＤ=null の場合、例外をスローする。
        if (this.id == null)
        {
            log.debug("ＩＤが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ＩＤが未指定です。");
        }

        //３）　@返済建玉チェック
        //３−１）this.返済建玉=null の場合、例外をスローする。
        if (this.closeMarginContractUnits == null)
        {
            log.debug("返済建玉が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00178,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "返済建玉が未指定です。");
        }

        //３−２）this.返済建玉の要素数=0 の場合、例外をスローする
        if (this.closeMarginContractUnits.length == 0)
        {
            log.debug("返済建玉が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00178,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "返済建玉が未指定です。");
        }

        //４）　@連続注文・注文条件チェック
        //スーパークラスのvalidate連続注文メソッドをコールする
        super.validateSuccOrder();

        log.exiting(STR_METHOD_NAME);
    }
}
@
