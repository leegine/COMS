head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginCloseMarginInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  信用取引返済注文入力リクエストクラス(WEB3MarginCloseMarginInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/16 李松峰 (中訊) 新規作成
*/
package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.util.WEB3LogUtility;

/**
 * （信用取引返済注文入力リクエスト）。<br>
 * <br>
 * 信用取引返済注文入力リクエストクラス
 * @@version 1.0
 */
public class WEB3MarginCloseMarginInputRequest extends WEB3GenRequest 
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
           WEB3LogUtility.getInstance(WEB3MarginCloseMarginInputRequest.class);    
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_closeMarginInput";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101800L;        
    /**
     * (建株ID)<BR>
     */
    public String[] id;
    
    /**
     * (ソートキー)<BR>
     * 対象項目：建日、損益<BR>
     */
    public WEB3MarginSortKey[] sortKeys;
    
    /**
     * (注文株数)<BR>
     * 一括返済単位の合計注文株数<BR>
     */
    public String orderQuantity;
    
    /**
     * @@roseuid 4140485A0002
     */
    public WEB3MarginCloseMarginInputRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@ＩＤチェック<BR>
     * 　@１－１）this.ＩＤ＝nullであった場合、「IDがnull」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00080<BR>
     * <BR>
     * 　@１－２）this.ＩＤ.要素数＝０であった場合、<BR>
     * 　@　@　@　@　@「IDの要素数が0」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00080<BR>
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
     * 　@　@２－３－２）信用取引ソートキー.キー項目が以下の項目以外の値<BR>
     * 　@　@　@　@　@　@　@　@であった場合、<BR>
     * 　@　@　@　@　@　@　@　@「ソートキー.キー項目が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@　@　@　@・”建日”<BR>
     * 　@　@　@　@　@　@　@・”建単価”<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00615<BR>
     * <BR>
     * ３）　@注文株数チェック<BR>
     * 　@　@this.注文株数≠null、かつ this.注文株数≦０であった場合、<BR>
     * 　@　@「注文株数が0以下」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00126<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 408665480224
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME="validate()";
        log.entering(STR_METHOD_NAME);
        // １）　@ＩＤチェック<BR>
        //   * 　@１－１）this.ＩＤ＝nullであった場合、「IDがnull」の例外をスローする。<BR>
        //   *   class: WEB3BusinessLayerException<BR>
        //   *   tag:   BUSINESS_ERROR_00080<BR>
        //
        if (id == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00080,STR_METHOD_NAME);
        }
        // 　@１－２）this.ＩＤ.要素数＝０であった場合、<BR>
        //     * 　@　@　@　@　@「IDの要素数が0」の例外をスローする。<BR>
        //     *   class: WEB3BusinessLayerException<BR>
        //     *   tag:   BUSINESS_ERROR_00080<BR>
        //
        if (id.length == 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00080,STR_METHOD_NAME);
        }
        // ２）　@信用取引ソートキーチェック<BR>
        //    * 　@２－１）this.信用取引ソートキー＝nullであった場合、<BR>
        //    * 　@　@　@　@　@「ソートキーがnull」の例外をスローする。<BR>
        //    *   class: WEB3BusinessLayerException<BR>
        //    *   tag:   BUSINESS_ERROR_00231<BR>
        //
        if (sortKeys == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00231,STR_METHOD_NAME);
        }
        // 　@２－２）this.信用取引ソートキー≠null　@かつ、<BR>
        //    * 　@　@　@　@this.信用取引ソートキー.要素数＝０であった場合、<BR>
        //    * 　@　@　@　@「ソートキー.要素数が0」の例外をスローする。<BR>
        //    *   class: WEB3BusinessLayerException<BR>
        //    *   tag:   BUSINESS_ERROR_00232<BR>
        //
        if (sortKeys != null && sortKeys.length == 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00232,STR_METHOD_NAME); 
        }
        // 　@２－３）this.信用取引ソートキーの全要素について以下のチェックを行う。<BR>
        //     * 　@　@２－３－１）ソートキー.validate()をコールする。<BR>
        //     * <BR>
        //     * 　@　@２－３－２）信用取引ソートキー.キー項目が以下の項目以外の値<BR>
        //     * 　@　@　@　@　@　@　@　@であった場合、<BR>
        //     * 　@　@　@　@　@　@　@　@「ソートキー.キー項目が未定義の値」の例外をスローする。<BR>
        //     * 　@　@　@　@　@　@　@・”建日”<BR>
        //     * 　@　@　@　@　@　@　@・”建単価”<BR>
        //     *   class: WEB3BusinessLayerException<BR>
        //     *   tag:   BUSINESS_ERROR_00086<BR>
        //
        for (int i = 0; i < sortKeys.length;i++)
        {
            sortKeys[i].validate();   
            if (!WEB3EquityKeyItemDef.OPEN_DATE.equals(sortKeys[i].keyItem) && !WEB3EquityKeyItemDef.CONTRACT_PRICE.equals(sortKeys[i].keyItem)) 
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00086,STR_METHOD_NAME); 
            }
        }
        /* ３）　@注文株数チェック<BR>
            * 　@　@this.注文株数≠null、かつ this.注文株数≦０であった場合、<BR>
            * 　@　@「注文株数が0以下」の例外をスローする。<BR>
            *   class: WEB3BusinessLayerException<BR>
            *   tag:   BUSINESS_ERROR_00126<BR>
        */
        if (orderQuantity != null && Long.parseLong(orderQuantity) <= 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00126,STR_METHOD_NAME); 
        }
        log.exiting(STR_METHOD_NAME);
    }
    /**
     * @@return WEB3GenResponse<BR>
     * @@roseuid 4140485A002A
     */
    public WEB3GenResponse createResponse() 
    {
     return new WEB3MarginCloseMarginInputResponse(this);
    }
}
@
