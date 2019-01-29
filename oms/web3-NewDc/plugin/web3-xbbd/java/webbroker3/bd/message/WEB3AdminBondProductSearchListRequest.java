head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.50.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondProductSearchListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者債券銘柄一覧検索リクエスト(WEB3AdminBondProductSearchListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 趙林鵬 (中訊) 新規作成
*/

package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.bd.define.WEB3BondProductSearchKeyItemDivDef;


/**
 * (管理者債券銘柄一覧検索リクエスト)<BR>
 * 管理者債券銘柄一覧検索リクエスト
 * 
 * @@author 趙林鵬
 * @@version 1.0
 */
public class WEB3AdminBondProductSearchListRequest extends WEB3GenRequest
{
    /**
     *　@ログユーティリティ<BR> 
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondProductSearchListRequest.class);
    
    /**
     *　@PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_product_search_list";

    /**
     *　@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200608171100L;
    
    /**
     * (要求ページ番号)<BR>
     * 要求ページ番号<BR>
     * 表示させたいページ位置を指定<BR>
     * ※先頭ページを"1"とする
     */
    public String pageIndex;
    
    /**
     * (ページ内表示行数)<BR>
     * ページ内表示行数<BR>
     * 一画面に最大100件（各社設定可能）
     */
    public String pageSize;
    
    /**
     * (ソートキー)<BR>
     * ソートキー
     */
    public WEB3BondSortKey[] sortKeys;
    
    /**
     * (検索条件)<BR>
     * 銘柄一覧検索の条件
     */
    public WEB3AdminBondProductListConditionInfo conditionInfo;
    
    /**
     * @@roseuid 44E3363E008C
     */
    public WEB3AdminBondProductSearchListRequest() 
    {
     
    }
    
    /**
     * 当クラスの整合性チェックを行う。<BR> 
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR> 
     * <BR>
     * １）ソートキーのチェック (デフォルトは発行日の昇順)<BR>
     * 　@１－１）ソートキー == nullの場合、<BR> 
     * 　@　@「ソートキーがnull」の例外をスローする。 <BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00231<BR>
     * <BR>
     * 　@１－２）ソートキーの要素数が0の場合、 <BR>
     * 　@　@「ソートキーの要素数が0」の例外をスローする。 <BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00232<BR>
     * <BR>
     * 　@１－３）ソートキー.キー項目に発行日、銘柄コード(WEB3)、償還日　@以外が存在した場合、 
     * <BR>
     * 　@　@　@「ソートキーのキー項目が未定義の値」の例外をスローする。 <BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00086<BR>
     * <BR>
     * ２）要求ページ番号チェック <BR>
     * 　@２－１）this.要求ページ番号 == nullであった場合、 <BR>
     * 　@　@　@　@「要求ページ番号がnull」の例外をスローする。 <BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00089<BR>
     * <BR>
     * 　@２－２）this.要求ページ番号が数字以外の値であった場合、 <BR>
     * 　@　@　@　@「要求ページ番号が数字以外」の例外をスローする。 <BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00090<BR>
     * <BR>
     * 　@２－３）this.要求ページ番号≦０であった場合、 <BR>
     * 　@　@　@　@「要求ページ番号が0以下」の例外をスローする。 <BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00616<BR>
     * <BR>
     * ３）ページ内表示行数チェック <BR>
     * 　@３－１）this.ページ内表示行数 == nullであった場合、 <BR>
     * 　@　@　@　@「ページ内表示行数がnull」の例外をスローする。 <BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02224<BR>
     * <BR>
     * 　@３－２）this.ページ内表示行数が数字以外の値であった場合、 <BR>
     * 　@　@　@　@「ページ内表示行数が数字以外」の例外をスローする。 <BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00092<BR>
     * <BR>
     * 　@３－３）this.ページ内表示行数≦０であった場合、 <BR>
     * 　@　@　@　@「ページ内表示行数が0以下」の例外をスローする。 <BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00617　@
     * 
     * @@throws WEB3BaseException　@　@
     * @@roseuid 44BC67E203A5
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）ソートキーのチェック (デフォルトは発行日の昇順)
        //１－１）ソートキー == nullの場合、
        //「ソートキーがnull」の例外をスローする。 
        //class:　@WEB3BusinessLayerException
        //tag:　@　@BUSINESS_ERROR_00231
        if (this.sortKeys == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーが未指定です。");
        }
        
        //１－２）ソートキーの要素数が0の場合、 
        //「ソートキーの要素数が0」の例外をスローする。 
        //class:　@WEB3BusinessLayerException
        //tag:　@　@BUSINESS_ERROR_00232
        if (this.sortKeys.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーの要素数が０です。");
        }
        
        //１－３）ソートキー.キー項目に発行日、銘柄コード(WEB3)、償還日　@以外が存在した場合、 
        //「ソートキーのキー項目が未定義の値」の例外をスローする。 
        //class:　@WEB3BusinessLayerException
        //tag:　@　@BUSINESS_ERROR_00086
        int l_intSortKeyLength = this.sortKeys.length;
        for(int i = 0; i< l_intSortKeyLength; i++)
        {
            if (!WEB3BondProductSearchKeyItemDivDef.ISSUE_DATE.equals(this.sortKeys[i].keyItem)
                && !WEB3BondProductSearchKeyItemDivDef.PRODUCT_CODE.equals(this.sortKeys[i].keyItem)
                && !WEB3BondProductSearchKeyItemDivDef.MATURITY_DATE.equals(this.sortKeys[i].keyItem))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "ソートキーのキー項目の値が存在しないコード値です。");
            }
        }
            
        //２）要求ページ番号チェック
        //２－１）this.要求ページ番号 == nullであった場合、 
        //「要求ページ番号がnull」の例外をスローする。 
        //class:　@WEB3BusinessLayerException
        //tag:　@　@BUSINESS_ERROR_00089
        if (this.pageIndex == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が未指定です。");
        }
        
        //２－２）this.要求ページ番号が数字以外の値であった場合、 
        //「要求ページ番号が数字以外」の例外をスローする。
        //class:　@WEB3BusinessLayerException
        //tag:　@　@BUSINESS_ERROR_00090
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が数字以外の値です。");
        }
        
        //２－３）this.要求ページ番号≦０であった場合、 
        //「要求ページ番号が0以下」の例外をスローする。 
        //class:　@WEB3BusinessLayerException
        //tag:　@　@BUSINESS_ERROR_00616
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号の値が0以下です。");
        }
        
        //３）ページ内表示行数チェック
        //３－１）this.ページ内表示行数 == nullであった場合、 
        //「ページ内表示行数がnull」の例外をスローする。 
        //class:　@WEB3BusinessLayerException
        //tag:　@　@BUSINESS_ERROR_02224
        if (this.pageSize == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02224,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数が未入力です。");
        }
        
        //３－２）this.ページ内表示行数が数字以外の値であった場合、 
        //「ページ内表示行数が数字以外」の例外をスローする。 
        //　@class:　@WEB3BusinessLayerException
        //tag:　@　@BUSINESS_ERROR_00092
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数が数字以外の値です。");
        }
        
        //３－３）this.ページ内表示行数≦０であった場合、 
        //「ページ内表示行数が0以下」の例外をスローする。 
        //class:　@WEB3BusinessLayerException
        //tag:　@　@BUSINESS_ERROR_00617
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数の値が0以下です。");
        }
        log.exiting(STR_METHOD_NAME);
    }
     
    /**
     * (createレスポンス)<BR>
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * <BR>
     * @@return レスポンスオブジェクト
     * @@roseuid 44BC67C702F8
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminBondProductSearchListResponse(this);
    }
    
}
@
