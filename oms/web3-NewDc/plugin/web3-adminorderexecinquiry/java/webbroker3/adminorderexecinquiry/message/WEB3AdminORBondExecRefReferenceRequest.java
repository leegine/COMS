head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.46.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORBondExecRefReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券管理者注文約定照会検索表示リクエスト(WEB3AdminBondExecRefReferenceRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 何文敏(中訊) 新規作成    
Revesion History : 2007/07/9 劉立峰(中訊) 仕様変更モデルNo.100
Revesion History : 2007/09/26 武波(中訊) モデルNo.108
*/

package webbroker3.adminorderexecinquiry.message;

import webbroker3.adminorderexecinquiry.define.WEB3BondExecRefUnitKeyItemDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (債券管理者注文約定照会検索表示リクエスト)<BR>
 * 債券管理者注文約定照会検索表示リクエストクラス
 * @@author 何文敏(中訊)
 * @@version 1.0
 */
public class WEB3AdminORBondExecRefReferenceRequest extends WEB3GenRequest 
{
    
    /**
    * PTYPE<BR>
    */
   public static final String PTYPE = "admin_or_bond_exec_ref_reference";

   /**
    * SerialVersionUID<BR>
    */
   public static final long serialVersionUID = 200608171156L;
   
   private static WEB3LogUtility log =
       WEB3LogUtility.getInstance(WEB3AdminORBondExecRefReferenceRequest.class);
   
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
     * (債券管理者注文約定照会検索条件情報)<BR>
     * 債券管理者注文約定照会検索条件情報
     */
    public WEB3AdminORBondExecRefConditionInfo conditionInfo;
    
    /**
     * (ソートキー)<BR>
     * ソートキー
     */
    public WEB3AdminOROrderExecutionSortKeyUnit[] sortKeys;
    
    
    /**
     * (債券管理者注文約定照会検索表示リクエスト)<BR>
     * コンストラクタ。
     * @@roseuid 44B734170365
     */
    public  WEB3AdminORBondExecRefReferenceRequest() 
    {
     
    }
    
    /**
     * 当クラスの整合性チェックを行う。<BR> 
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR> 
     * <BR>
     * １）ソートキーのチェック(デフォルトは発注日の降順)<BR>
     *　@　@１－１）ソートキー == nullの場合、<BR> 
     * 　@　@「ソートキーがnull」の例外をスローする。<BR> 
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00231 <BR>
     * <BR>
     *　@　@１－２）ソートキーの要素数が0の場合、<BR> 
     * 　@　@「ソートキーの要素数が0」の例外をスローする。<BR> 
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_0023２ <BR>
     * <BR>
     *　@　@１－３）ソートキーの配列の個数分、<BR> 
     * 　@　@繰り返して以下のチェックを行う。<BR> 
     *　@　@　@１－３－１）ソートキー.validate()メソッドをコールする。<BR> 
     *　@　@　@１－３－２）ソートキー.キー項目に <BR>
     * 　@　@　@以下の項目名以外が存在した場合、<BR> 
     * 　@　@　@「ソートキーのキー項目が未定義の値」の例外をスローする。<BR> 
     * 　@　@　@　@・顧客コード<BR>
     * 　@　@　@　@・銘柄コード（WEB3）<BR>
     * 　@　@　@　@・注文種別<BR>
     * 　@　@　@　@・決済区分<BR>
     * 　@　@　@　@・受注日時<BR>
     * 　@　@　@　@・発注日<BR>
     * 　@　@　@　@・約定日<BR>
     * 　@　@　@　@・現地約定日<BR>
     * 　@　@　@　@・受渡日<BR>
     * 　@　@　@　@・現地受渡日<BR>
     * 　@　@　@　@・部店コード<BR>
     * 　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:　@　@BUSINESS_ERROR_00086 <BR>
     * <BR>
     * ２）要求ページ番号チェック<BR> 
     *　@　@２－１）this.要求ページ番号 == nullであった場合、<BR> 
     * 　@　@　@　@「要求ページ番号がnull」の例外をスローする。<BR>
     * 　@　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:　@　@BUSINESS_ERROR_00089 <BR> 
     * <BR>
     *　@　@２－２）this.要求ページ番号が数字以外の値であった場合、<BR> 
     * 　@　@　@　@「要求ページ番号が数字以外」の例外をスローする。 <BR>
     * 　@　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:　@　@BUSINESS_ERROR_00090<BR>
     * <BR>
     *　@　@２－３）this.要求ページ番号≦０であった場合、<BR> 
     * 　@　@　@　@「要求ページ番号が0以下」の例外をスローする。<BR>
     * 　@　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:　@　@BUSINESS_ERROR_00616<BR> 
     * <BR>
     * ３）ページ内表示行数チェック<BR> 
     *　@　@３－１）this.ページ内表示行数 == nullであった場合、<BR> 
     * 　@　@　@　@「ページ内表示行数がnull」の例外をスローする。<BR>
     * 　@　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:　@　@BUSINESS_ERROR_02224<BR> 
     * <BR>
     *　@　@３－２）this.ページ内表示行数が数字以外の値であった場合、<BR> 
     * 　@　@　@　@「ページ内表示行数が数字以外」の例外をスローする。<BR>
     * 　@　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:　@　@BUSINESS_ERROR_00092<BR>　@ 
     * <BR>　@ 
     *　@　@３－３）this.ページ内表示行数≦０であった場合、<BR> 
     * 　@　@　@　@「ページ内表示行数が0以下」の例外をスローする。<BR>
     * 　@　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:　@　@BUSINESS_ERROR_00617
     * @@roseuid 44B734DB0265
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）ソートキーのチェック(デフォルトは発注日の降順)
        //１－１）ソートキー == nullの場合、
        //「ソートキーがnull」の例外をスローする。
        //class: WEB3BusinessLayerException 
        //tag:   BUSINESS_ERROR_00231
        if(this.sortKeys == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーが未指定です。");
        }
        
        //１－２）ソートキーの要素数が0の場合、
        //「ソートキーの要素数が0」の例外をスローする。
        //class: WEB3BusinessLayerException 
        //tag:   BUSINESS_ERROR_00232
        if(this.sortKeys.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーの要素数が０です。");
        }
        
        //１－３）ソートキーの配列の個数分、 
        //繰り返して以下のチェックを行う。
        // １－３－１）ソートキー.validate()メソッドをコールする。 
        // １－３－２）ソートキー.キー項目に 
        // 　@以下の項目名以外が存在した場合、
        // 　@「ソートキーのキー項目が未定義の値」の例外をスローする。
        //   . 顧客コード
        // 　@. 銘柄コード（WEB3）
        // 　@. 注文種別
        // 　@. 決済区分
        // 　@. 受注日時
        // 　@. 発注日
        // 　@. 約定日
        // 　@. 現地約定日
        // 　@. 受渡日
        // 　@. 現地受渡日
        //   . 部店コード 
        // 　@　@　@class:　@WEB3BusinessLayerException
        // 　@　@　@tag:　@　@BUSINESS_ERROR_00086     
        int l_intSortKeysLength = 0;

        l_intSortKeysLength = this.sortKeys.length;


        for(int i = 0; i < l_intSortKeysLength; i++)
        {
            this.sortKeys[i].validate();
            if((!WEB3BondExecRefUnitKeyItemDivDef.ACCOUNT_CODE.equals(this.sortKeys[i].keyItem))
                &&(!WEB3BondExecRefUnitKeyItemDivDef.PRODUCT_CODE.equals(this.sortKeys[i].keyItem))
                &&(!WEB3BondExecRefUnitKeyItemDivDef.TRADING_TYPE.equals(this.sortKeys[i].keyItem))
                &&(!WEB3BondExecRefUnitKeyItemDivDef.SETTLE_DIV.equals(this.sortKeys[i].keyItem))
                &&(!WEB3BondExecRefUnitKeyItemDivDef.ACCEPT_ORDER_TIMESTAMP.equals(this.sortKeys[i].keyItem))
                &&(!WEB3BondExecRefUnitKeyItemDivDef.ORDER_BIZ_DATE.equals(this.sortKeys[i].keyItem))
                &&(!WEB3BondExecRefUnitKeyItemDivDef.DOMESTIC_EXECUTION_DATE.equals(this.sortKeys[i].keyItem))
                &&(!WEB3BondExecRefUnitKeyItemDivDef.FOREIGN_DELIVERY_DATE.equals(this.sortKeys[i].keyItem))
                &&!WEB3BondExecRefUnitKeyItemDivDef.FOREIGN_EXECUTION_DATE.equals(this.sortKeys[i].keyItem)
                &&!WEB3BondExecRefUnitKeyItemDivDef.DOMESTIC_DELIVERY_DATE.equals(this.sortKeys[i].keyItem)
                &&!WEB3BondExecRefUnitKeyItemDivDef.BRANCH_CODE.equals(this.sortKeys[i].keyItem)
                &&!WEB3BondExecRefUnitKeyItemDivDef.SONAR_TRADER_CODE.equals(this.sortKeys[i].keyItem))
                
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "ソートキーのキー項目の値が存在しないコード値です。");
            }
        }
        
        //２）要求ページ番号チェック 
        //　@　@２－１）this.要求ページ番号 == nullであった場合、 
        //   　@　@　@　@「要求ページ番号がnull」の例外をスローする。
        //class: WEB3BusinessLayerException 
        //tag:   BUSINESS_ERROR_00089
        if(this.pageIndex == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass() + "." + STR_METHOD_NAME,
                "要求ページ番号が未指定です。");
        }
        
        //this.要求ページ番号 != 数字 
        //「要求ページ番号が数字以外の値です。」の例外をスローする。
        //class: WEB3BusinessLayerException 
        //tag:   BUSINESS_ERROR_00090
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090, 
                this.getClass().getName() + STR_METHOD_NAME, 
                "要求ページ番号が数字以外の値です。");
        }
        
        //this.要求ページ番号 <= 0  
        //「要求ページ番号の値が0以下です。」の例外をスローする。
        //class: WEB3BusinessLayerException 
        //tag:   BUSINESS_ERROR_00616
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616, 
                this.getClass().getName() + STR_METHOD_NAME, 
                "ペ要求ページ番号の値が0以下です。");
        }
        
        //３）ページ内表示行数
        //this.ページ内表示行数 == null   
        //「ページ内表示行数の入力が不正です。」の例外をスローする。 
        //class: WEB3BusinessLayerException 
        //tag:   BUSINESS_ERROR_00091
        if (this.pageSize == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091, 
                this.getClass().getName() + STR_METHOD_NAME, 
                "ページ内表示行数の入力が不正です。");
        }
        
        //this.ページ内表示行数 != 数字
        //「ページ内表示行数が数字以外の値です。」の例外をスローする。
        //class: WEB3BusinessLayerException 
        //tag:   BUSINESS_ERROR_00092
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092, 
                this.getClass().getName() + STR_METHOD_NAME, 
                "ページ内表示行数が数字以外の値です。");
        }
        
        //this.ページ内表示行数 <= 0
        //「ページ内表示行数の値が0以下です。」の例外をスローする。
        //class: WEB3BusinessLayerException 
        //tag:   BUSINESS_ERROR_00617
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617, 
                this.getClass().getName() + STR_METHOD_NAME, 
                "ページ内表示行数の値が0以下です。");
        }
        
    }
    
    
    /**
     * (createレスポンス)<BR>
     * (createResponse実装)<BR>
     * <BR>
     * 債券管理者注文約定照会検索表示レスポンスを生成し返す
     * @@return WEB3GenResponse
     * @@roseuid 44DA82A3000C
     */
    public WEB3GenResponse createResponse() 
    {
    	return new WEB3AdminORBondExecRefReferenceResponse(this);
    }
}
@
