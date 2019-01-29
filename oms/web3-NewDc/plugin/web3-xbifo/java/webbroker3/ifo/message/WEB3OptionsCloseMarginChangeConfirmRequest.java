head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.19.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsCloseMarginChangeConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション訂正返済確認リクエスト(WEB3OptionsCloseMarginChangeConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 李海波 (中訊) 新規作成
              001: 2004/08/05 王暁傑  対応バグ WEB3_IFO_UT-000118
Revesion History : 2008/03/12 金傑(中訊) モデル 826
*/

package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (株価指数オプション訂正返済確認リクエスト)<BR>
 * 株価指数オプション訂正返済確認リクエストクラス<BR>
 * @@author 李海波
 * @@version 1.0 
 */
public class WEB3OptionsCloseMarginChangeConfirmRequest extends WEB3OptionsCommonRequest 
{
    
    /**         
     * ログ出力ユーティリティ。         
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3OptionsCloseMarginChangeConfirmRequest.class);
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE ="options_closeMarginChangeConfirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406141518L;    
   
    /**
     * 注文ＩＤ<BR>
     */
    public String id;
   
    /**
     * (返済建玉)<BR>
     */
    public WEB3FuturesOptionsCloseMarginContractUnit[] closeMarginContractUnits;
   
    /**
     * @@roseuid 40C0A8EA0399
     */
    public WEB3OptionsCloseMarginChangeConfirmRequest() 
    {
    
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3OptionsCloseMarginChangeConfirmResponse(this);
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
     * 　@４－１）this.注文数量がnull以外の値でかつ、<BR>
     * 　@　@　@　@this.注文数量が数字以外の値であれば例外をスローする。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00075<BR>
     * 　@４－２）this.注文数量がnull以外の値でかつ、<BR>
     * 　@　@　@　@this.注文数量≦０であれば例外をスローする。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00076<BR>
     * <BR>
     * ５）　@返済建玉要素チェック<BR>
     *    返済建玉の要素数分以下のチェックを繰り返して行う。<BR>
     *    ５－１）返済建玉のvalidate()メソッドを呼び出す。<BR>
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
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        log.debug("スーパークラスのvalidateメソッドを呼び出す");
        super.validate();

        //ＩＤチェック
        if(WEB3StringTypeUtility.isEmpty(this.id))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                getClass().getName() + "validate",
                "ＩＤがnullの値である。");
        }

        //返済建玉チェック
        if(this.closeMarginContractUnits == null 
            || this.closeMarginContractUnits.length == 0)
        {
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00178,            
                getClass().getName() + "validate",
                "返済建玉がnullの値または、要素数が０である場合のエラー");
        }

        //注文数量チェック(４－１)
        if(WEB3StringTypeUtility.isNotEmpty(this.opOrderQuantity) 
            && !(WEB3StringTypeUtility.isNumber(this.opOrderQuantity)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00075,
                getClass().getName() + "validate",
                "注文数量が数字以外の値である。");
        }

        //注文数量チェック(４－２)
        if(WEB3StringTypeUtility.isNotEmpty(this.opOrderQuantity) 
            && Long.parseLong(this.opOrderQuantity) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00076,
                getClass().getName() + "validate",
                "注文数量が0以下の値である。");
        }

        //返済建玉要素チェック
        // ６）　@返済建玉総数量チェック<BR>
        //　@　@this.注文数量 = null and<BR>
        //　@ （すべての返済建玉の数量=0 or null） の場合、<BR>
        // 　@　@例外をスローする。<BR>
        //     class:WEB3BusinessLayerException<BR>
        //     tag:BUSINESS_ERROR_00285<BR>
        int l_intContractUnitsLength = closeMarginContractUnits.length;
        int l_intQuantityCnt = 0;
        if (this.opOrderQuantity == null)
        {
            for (int i = 0; i < l_intContractUnitsLength; i++)
            {
                //５－１）返済建玉のvalidate()メソッドを呼び出す。
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

        log.exiting(STR_METHOD_NAME);                
    }
    
    /**
     * (validateAT反対取引) <BR>
     * 反対取引指定時の、当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * （連続注文用のメソッド） <BR>
     * １）　@スーパークラスのvalidateメソッドを呼び出す<BR>
     * <BR>
     * ２）　@ＩＤチェック<BR>
     * 　@this.ＩＤ=null の場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:   BUSINESS_ERROR_00600<BR>
     * ３）　@返済建玉チェック<BR>
     * 　@３－１）this.返済建玉=null の場合、<BR>
     * 　@　@例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:   BUSINESS_ERROR_00178<BR>
     * 　@３－２）this.返済建玉の要素数=0 の場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:   BUSINESS_ERROR_00178<BR>
     * <BR>
     * ４）　@連続注文・注文条件チェック<BR>
     * 　@スーパークラスのvalidate連続注文メソッドをコールする。<BR> 
     *
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validateATReserveOrder()throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateATReserveOrder()";
        log.entering(STR_METHOD_NAME);
        
        // １）　@スーパークラスのvalidateメソッドを呼び出す
        super.validate();
        
        // ２）　@ＩＤチェック
        // this.ＩＤ=null の場合、例外をスローする。
        if (this.id == null)
        {
            // 例外をスローする。
            log.debug("注文IDが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文IDが未指定です。");
        }
        
        // ３）　@返済建玉チェック
        // ３－１）this.返済建玉=null の場合、
        if (this.closeMarginContractUnits == null)
        {
            // 例外をスローする。
            log.debug("返済建玉が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00178,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "返済建玉が未指定です。");
        }
        // ３－２）this.返済建玉の要素数=0 の場合、
        if (this.closeMarginContractUnits.length == 0)
        {
            // 例外をスローする。
            log.debug("返済建玉が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00178,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "返済建玉が未指定です。");
        }
        
        // ４）　@連続注文・注文条件チェック
        // スーパークラスのvalidate連続注文メソッドをコールする。
        super.validateSuccOrder();
        log.exiting(STR_METHOD_NAME);
    }
}
@
