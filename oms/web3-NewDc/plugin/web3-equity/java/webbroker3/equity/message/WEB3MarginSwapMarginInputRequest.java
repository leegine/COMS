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
filename	WEB3MarginSwapMarginInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引現引現渡注文入力リクエスト(WEB3MarginSwapMarginInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/17 凌建平 (中訊) 新規作成
*/

package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.define.WEB3EquityKeyItemDef;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * （信用取引現引現渡注文入力リクエスト）。<br>
 * <br>
 * 信用取引現引現渡注文入力リクエストクラス
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3MarginSwapMarginInputRequest extends WEB3GenRequest 
{
    /**
     * (ログ出力ユーティリティ。)
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3MarginSwapMarginInputRequest.class);

    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_swapMarginInput";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101617L;
    
    /**
     * (ID)<BR>
     * 建株ID
     */
    public String[] id;
    
    /**
     * (信用取引ソートキー)<BR>
     * 対象項目：建日、損益
     */
    public WEB3MarginSortKey[] sortKeys;
    
    /**
     * (注文株数)<BR>
     * 一括返済単位の合計注文株数
     */
    public String orderQuantity;
    
    /**
     * @@roseuid 41404255022D
     */
    public WEB3MarginSwapMarginInputRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@ＩＤチェック<BR>
     * 　@１－１）this.ＩＤ＝nullであった場合、「IDがnull」の例外をスローする。<BR>
     * 　@１－２）this.ＩＤ.要素数＝０であった場合、<BR>
     *          「IDの要素数が0」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00282<BR>
     * <BR>
     * ２）　@信用取引ソートキーチェック<BR>
     * 　@２－１）this.信用取引ソートキー＝nullであった場合、<BR>
     * 　@　@　@　@　@「ソートキーがnull」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00231<BR>
     * <BR>
     * 　@２－２）this.信用取引ソートキー≠null　@かつ、<BR>
     * 　@　@　@　@this.信用取引ソートキー.要素数＝０であった場合、<BR>
     * 　@　@　@　@「ソートキー.要素数が0」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00232<BR>
     * <BR>
     * 　@２－３）this.信用取引ソートキーの全要素について以下のチェックを行う。<BR>
     * 　@　@２－３－１）ソートキー.validate()をコールする。<BR>
     * <BR>
     * 　@　@２－３－２）信用取引ソートキー.キー項目が以下の項目以外の<BR>
     * 　@　@　@　@　@　@　@　@　@値であった場合、<BR>
     * 　@　@　@　@　@　@　@　@　@「ソートキー.キー項目が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@　@　@　@・”建日”<BR>
     * 　@　@　@　@　@　@　@・”建単価”<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00086<BR>
     * <BR>
     * ３）　@注文株数チェック<BR>
     * 　@　@this.注文株数≠null、かつ this.注文株数≦０であった場合、<BR>
     * 　@　@「注文株数が0以下」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00126<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40866B810338
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "WEB3MarginSwapMarginInputRequest: validate()";
        log.entering(STR_METHOD_NAME);
        
        log.debug("ＩＤチェック:");
        // １）　@ＩＤチェック
        if (this.id == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00080,
            this.getClass().getName() + "validate");
        }
        
        int l_intIDLength = this.id.length;
        if (l_intIDLength == 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00282,
            this.getClass().getName() + "validate");
        }
        
        log.debug("信用取引ソートキーチェック:");
        // ２）　@信用取引ソートキーチェック
        if (this.sortKeys == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00231,
            this.getClass().getName() + "validate");
        }

        int l_intSortKeysLength = this.sortKeys.length;
        if (l_intSortKeysLength == 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00232,
            this.getClass().getName() + "validate");
        }

        for (int i = 0; i < l_intSortKeysLength; i++)
        {
            sortKeys[i].validate();
            
            if (!WEB3EquityKeyItemDef.OPEN_DATE.equals(this.sortKeys[i].keyItem)
                    && !WEB3EquityKeyItemDef.CONTRACT_PRICE.equals(this.sortKeys[i].keyItem))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                this.getClass().getName() + "validate");
            }
        }

        log.debug("注文株数チェック:");
        // ３）　@注文株数チェック
        if (this.orderQuantity != null && !WEB3StringTypeUtility.isNumber(this.orderQuantity))
        {
            //例外
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00901,
            this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (this.orderQuantity != null && Long.parseLong(this.orderQuantity) <= 0)
        {
            //例外
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00902,
            this.getClass().getName() + "validate");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41404255024B
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MarginSwapMarginInputResponse(this);
    }
}
@
