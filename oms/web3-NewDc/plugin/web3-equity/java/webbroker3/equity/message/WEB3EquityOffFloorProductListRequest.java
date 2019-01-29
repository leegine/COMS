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
filename	WEB3EquityOffFloorProductListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 立会外分売銘柄一覧リクエスト(WEB3EquityOffFloorProductListRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 岡村和明(SRA) 新規作成
*/
package webbroker3.equity.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.util.WEB3LogUtility;

/**
 * （立会外分売銘柄一覧リクエスト）。<BR>
 * <BR>
 * 立会外分売銘柄一覧要求　@リクエストデータクラス
 * @@author 岡村和明(SRA) 
 * @@version 1.0
 */
public class WEB3EquityOffFloorProductListRequest extends WEB3GenRequest
{
    /**
     * （ログ出力ユーティリティ）。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3EquityOffFloorProductListRequest.class);
        
    /**
     * （serialVersionUID）。
     */
    public static final long serialVersionUID = 200412171000L;

    /**
     * （PTYPE）。
     */
    public static final String PTYPE = "equity_off_floor_product_list";
    
    /**
     * （ソートキー）。<BR>
     * <BR>
     * 現物株式ソートキーの一覧<BR>
     * <BR>
     * 対象項目：銘柄コード、市場コード、受付開始日時、受付終了日時
     */
    public WEB3EquitySortKey[] sortKeys;

    /**
     * （デフォルトコンストラクタ）。
     */
    public WEB3EquityOffFloorProductListRequest()
    {
    }
    
    /**
     * （create立会外分売銘柄一覧レスポンス）。
     * 立会外分売銘柄一覧レスポンスを生成する。
     * @@return WEB3EquityCommonInputResponse
     * @@roseuid 4063B6D90191
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3EquityOffFloorProductListResponse(this);
    }

    /**
     * （validate）。<BR>
     * <BR>
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）ソートキーのチェック<BR>
     * 　@１－１）this.ソートキー＝nullであった場合<BR>
     * 　@　@　@　@「ソートキーがnull」の例外をスローする。<BR>
     * <BR>
     * 　@１－２）this.ソートキー.要素数＝０だった場合<BR>
     * 　@　@　@　@「ソートキー.要素数が0」の例外をスローする。<BR>
     * <BR>
     * 　@１－３）this.ソートキーの全要素に対して<BR>
     * 　@　@　@　@下記のチェックを行う。<BR>
     * 　@　@１－３－１）ソートキー.validate()をコールする。<BR>
     * <BR>
     * 　@　@１－３－２）ソートキーの配列の個数分、繰り返してチェックを行う。<BR>
     * 　@　@　@　@　@　@　@　@　@以下の項目名以外が存在した場合、例外とする。<BR>
     * 　@　@　@　@・銘柄コード<BR>
     * 　@　@　@　@・市場コード<BR>
     * 　@　@　@　@・受付開始日時<BR>
     * 　@　@　@　@・受付終了日時
     * @@throws WEB3BusinessLayerException
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        log.debug("ソートキーのチェック");
        if(this.sortKeys == null)
        {
            // ソートキーがnullの場合
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + ".validate()");
        }
        
        int l_intSortKeysCount = this.sortKeys.length;
        
        if(l_intSortKeysCount == 0)
        {
            // ソートキー.要素数＝０だった場合
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + ".validate()");
        }
        
        for(int i = 0 ; i < l_intSortKeysCount ; i++)
        {
            this.sortKeys[i].validate();
            
            if(!(WEB3EquityKeyItemDef.PRODUCTCODE.equals(this.sortKeys[i].keyItem)
                || WEB3EquityKeyItemDef.TRADEMARKET.equals(this.sortKeys[i].keyItem)
                || WEB3EquityKeyItemDef.ORDER_START_DATE_TIME.equals(this.sortKeys[i].keyItem)
                || WEB3EquityKeyItemDef.ORDER_END_DATE_TIME.equals(this.sortKeys[i].keyItem)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01698,
                    this.getClass().getName() + ".validate()");
            }
            
        }
        
        
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
