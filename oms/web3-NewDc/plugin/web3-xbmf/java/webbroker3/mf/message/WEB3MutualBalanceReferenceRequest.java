head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.04.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBalanceReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信残高照会リクエストクラス(WEB3MutualBalanceReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/06 于美麗 (中訊) 新規作成
Revesion History : 2006/03/08 鈴木 (SRA) 仕様変更（モデル）：403
Revesion History : 2007/02/03 張騰宇 (中訊) 仕様変更・モデル536
*/

package webbroker3.mf.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mf.define.WEB3MFSortkeyItemDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (投信残高照会リクエスト)<BR>
 * 投信残高照会リクエストクラス
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3MutualBalanceReferenceRequest extends WEB3GenRequest 
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualBalanceReferenceRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mutual_balance_reference";
   
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412281618L;

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
    * 1ページ内に表示させたい行数を指定
    */
   public String pageSize;
   
   /**
    * (投信ソートキー)<BR>
    * 投信ソートキー<BR>
    * <BR>
    * 対象項目:”口座”、”評価額”、”評価損益”、”注文受付締切時間”、
    * 　@　@　@　@ ”銘柄ID”
    */
   public WEB3MutualSortKey[] sortKeys;

    /**
     * (投信･外貨MMF表示区分)<BR>
     * 投信･外貨MMF表示区分 <BR>
     * <BR>
     * 表示対象の銘柄を、投信･外貨MMFで切り替えるための区分 <BR>
     * <BR>
     * 0:投信のみ <BR>
     * 1:外貨MMFのみ <BR>
     * 2:両方 <BR>
     * <BR>
     * ※nullの場合、「0:投信のみ」とする<BR>
     */
    public String mutualFrgnMmfDisplayDiv;

   /**
    * @@roseuid 41D13CC2000F
    */
   public WEB3MutualBalanceReferenceRequest() 
   {
    
   }
   
   /**
    * (validate)<BR>
    * 当リクエストデータの整合性チェックを行う。<BR>
    * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
    * <BR>
    * １）　@要求ページ番号チェック<BR>
    * 　@１－１）　@this.要求ページ番号==nullの場合、例外をスローする。<BR>
    *       class: WEB3BusinessLayerException<BR>
    *       tag:   BUSINESS_ERROR_00089 <BR>
    * 　@１－２）　@this.要求ページ番号が数字以外の場合、例外をスローする。<BR>
    *       class: WEB3BusinessLayerException<BR>
    *       tag:   BUSINESS_ERROR_00090 <BR>
    * <BR>
    * ２）　@ページ内表示行数チェック<BR>
    * 　@２－１）　@this.ページ内表示行数==nullの場合、例外をスローする。<BR>
    *       class: WEB3BusinessLayerException<BR>
    *       tag:   BUSINESS_ERROR_00091 <BR>
    * 　@２－２）　@this.ページ内表示行数が数字以外の場合、例外をスローする。<BR>
    *       class: WEB3BusinessLayerException<BR>
    *       tag:   BUSINESS_ERROR_00092 <BR>
    * <BR>
    * ３)　@投信ソートキーチェック<BR>
    * 　@３－１)　@this.投信ソートキー==nullの場合、例外をスローする。<BR>
    *             class: WEB3BusinessLayerException<BR>
    *             tag:   BUSINESS_ERROR_00231 <BR>
    * 　@３－２)　@this.投信ソートキーの要素数==0の場合、例外をスローする。<BR>
    *             class: WEB3BusinessLayerException<BR>
    *             tag:   BUSINESS_ERROR_00232 <BR>
    * 　@３－３)　@this.投信ソートキーの要素数分繰り返してチェックを行う。<BR>
    * 　@　@３－３－１）　@キー項目==nullの場合、例外をスローする。<BR>
    *             class: WEB3BusinessLayerException<BR>
    *             tag:   BUSINESS_ERROR_00085 <BR>
    * 　@　@３－３－２）　@キー項目が以下の値のいずれかではない場合、例外をスローする。<BR>
    * 　@　@　@　@　@　@　@　@　@・投信残高照会明細.口座区分<BR>
    * 　@　@　@　@　@　@　@　@　@・投信残高照会明細.評価額<BR>
    * 　@　@　@　@　@　@　@　@　@・投信残高照会明細.評価損益<BR>
    * 　@　@　@　@　@　@　@　@　@・投信残高照会明細.注文受付締切時間<BR>
    *　@　@　@　@　@　@　@　@　@ ・投信残高照会明細.銘柄ID<BR>
    *             class: WEB3BusinessLayerException<BR>
    *             tag:   BUSINESS_ERROR_00086 <BR>
    * <BR>
    * 　@　@３－３－３）　@昇順／降順==nullの場合、例外をスローする。<BR>
    *                class: WEB3BusinessLayerException<BR>
    *                tag:BUSINESS_ERROR_00087<BR>
    * 　@　@３－３－４）　@昇順／降順が以下の値のいずれかではない場合、例外をスローする。
    * <BR>
    * 　@　@　@　@　@　@　@　@・”昇順”<BR>
    * 　@　@　@　@　@　@　@　@・”降順”<BR>
    *                class: WEB3BusinessLayerException<BR>
    *                tag:BUSINESS_ERROR_00088
    * @@throws WEB3BaseException
    * @@roseuid 41AD8AD902B0
    */
   public void validate() throws WEB3BaseException 
   {
       final String STR_METHOD_NAME = "validate()";
       log.entering(STR_METHOD_NAME);
       
       // １）　@要求ページ番号チェック
       // 　@１－１）　@this.要求ページ番号==nullの場合、例外をスローする。
       if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
       {
           log.debug("要求ページ番号が未指定です。");
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00089,
               getClass().getName() + "." + STR_METHOD_NAME,
               "要求ページ番号が未指定です。");    
       }
       
       // 　@１－２）　@this.要求ページ番号が数字以外の場合、例外をスローする。
       if (!WEB3StringTypeUtility.isNumber(this.pageIndex))
       {
           log.debug("要求ページ番号が数字以外の値です。");
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog. BUSINESS_ERROR_00090,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "要求ページ番号が数字以外の値である。" +
               "要求ページ番号 = " + this.pageIndex);
       }

       // ２）　@ページ内表示行数チェック
       // 　@２－１）　@this.ページ内表示行数==nullの場合、例外をスローする。
       if (WEB3StringTypeUtility.isEmpty(this.pageSize))
       {
           log.debug("ページ内表示行数が未指定です。");
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00091 ,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "ページ内表示行数が未指定です。");
       }
       
       // 　@２－２）　@this.ページ内表示行数が数字以外の場合、例外をスローする。
       if (!WEB3StringTypeUtility.isNumber(this.pageSize))
       {
           log.debug("ページ内表示行数が数字以外の値です。");
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog. BUSINESS_ERROR_00092,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "ページ内表示行数が数字以外の値です。" +
               "ページ内表示行数 = " + this.pageSize);
       }

       // ３)　@投信ソートキーチェック
       // 　@３－１)　@this.投信ソートキー==nullの場合、例外をスローする。
       if (this.sortKeys == null)
       {
           log.debug("ソートキーが未指定です。");
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00231,
               getClass().getName() + "." + STR_METHOD_NAME,
               "ソートキーが未指定です。"); 
       }
       
       // 　@３－２)　@this.投信ソートキーの要素数==0の場合、例外をスローする。
       if (this.sortKeys.length == 0)
       {
           log.debug("ソートキーの要素数が０です。");
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00232,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "ソートキーの要素数が０です。");
       }      
       
       // 　@３－３)　@this.投信ソートキーの要素数分繰り返してチェックを行う。
       for (int i=0; i < this.sortKeys.length; i++)
       {
           WEB3MutualSortKey l_mutualSortKey = this.sortKeys[i];
          
           //３－３－１）　@キー項目がnullの値であれば例外をスローする。
           if (WEB3StringTypeUtility.isEmpty(l_mutualSortKey.keyItem))
           {
               log.debug("ソートキーのキー項目が未指定です。");
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                   this.getClass().getName() + "." + STR_METHOD_NAME,
                   "ソートキーのキー項目が未指定です。"); 
           }
           
		   // ３－３－２）　@キー項目が以下の値のいずれかではない場合、例外をスローする。
		   // 　@　@・投信残高照会明細.口座区分
		   // 　@　@・投信残高照会明細.評価額
		   // 　@　@・投信残高照会明細.評価損益
		   // 　@　@・投信残高照会明細.注文受付締切時間
           //　@　@ ・投信残高照会明細.銘柄ID
           if (!((l_mutualSortKey.keyItem).equals(
                   WEB3MFSortkeyItemDef.TAX_TYPE) || 
               (l_mutualSortKey.keyItem).equals(
                   WEB3MFSortkeyItemDef.MARKET_VALUE) || 
               (l_mutualSortKey.keyItem).equals(
                   WEB3MFSortkeyItemDef.APPRAISAL_PROFIT_LOSS) ||
               (l_mutualSortKey.keyItem).equals(
                   WEB3MFSortkeyItemDef.ORDER_CLOSE_TIME)||
               (l_mutualSortKey.keyItem).equals(WEB3MFSortkeyItemDef.MUTUAL_PRODUCT_ID)))
           {
               log.debug("ソートキーのキー項目の値が存在しないコード値です。");
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                   this.getClass().getName() + "." + STR_METHOD_NAME,
                   "キー項目が”口座区分”、”評価額”、”評価損益”、”注文受付締切時間”、" +
                   "”銘柄ID”以外の値である" +
                   "キー項目 = " + l_mutualSortKey.keyItem);     
           }
           
           // 　@　@３－３－３）　@昇順／降順==nullの場合、例外をスローする。
           if(WEB3StringTypeUtility.isEmpty(l_mutualSortKey.ascDesc))
           {
               log.debug("昇順／降順が未指定です。");
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                   this.getClass().getName() + "." + STR_METHOD_NAME,
                   "昇順／降順が未指定です。");  
           }
           
           // 　@　@３－３－４）　@昇順／降順が以下の値のいずれかではない場合、例外をスローする。
           // 　@　@　@　@・”昇順” 
           // 　@　@　@　@・”降順” 
           if((!WEB3AscDescDef.ASC.equals(l_mutualSortKey.ascDesc))
                   && (!WEB3AscDescDef.DESC.equals(l_mutualSortKey.ascDesc)))
           {
               log.debug("昇順／降順が”A：昇順”、”D：降順”以外の値です。");
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                   this.getClass().getName() + "." + STR_METHOD_NAME,
                   "昇順／降順が”A：昇順”、”D：降順”以外の値です。" +
                   "昇順／降順の値 = " + l_mutualSortKey.ascDesc); 
           }
      }
      log.exiting(STR_METHOD_NAME);
   }
   
   /**
    * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
    *<BR>
    * @@return レスポンスオブジェクト
    */
   public WEB3GenResponse createResponse() 
   {
       return new WEB3MutualBalanceReferenceResponse(this);
   }
}
@
