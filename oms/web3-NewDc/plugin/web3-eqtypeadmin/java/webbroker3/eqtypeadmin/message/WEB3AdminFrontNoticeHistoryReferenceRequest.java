head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFrontNoticeHistoryReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・通知履歴参照リクエストクラス (WEB3AdminFrontNoticeHistoryReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/27  王明明 (中訊) 仕様変更モデルNo.119
Revision History : 2007/02/27  王明明 (中訊) 仕様変更モデルNo.121
*/

package webbroker3.eqtypeadmin.message;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * 管理者・通知履歴参照リクエストクラス<BR>
 */
public class WEB3AdminFrontNoticeHistoryReferenceRequest extends WEB3GenRequest 
{
   
	/**
	 * PTYPE<BR>
	 */
	public final static String PTYPE = "admin_front_Notice_History_Reference";

	/**
	 * ログ出力ユーティリティ。
	 */
	private static WEB3LogUtility log =
		WEB3LogUtility.getInstance(WEB3AdminFrontNoticeHistoryReferenceRequest.class);

   /**
    * 市場コード<BR>
    */
   public String convertMarketCode;
   
   /**
    * データ種別コード<BR>
    */
   public String dataClassCode;
   
   /**
    * 部店コード<BR>
    */
   public String branchCode;
   
   /**
    * 仮想サーバNo<BR>
    */
   public String virtualServerNumber;
   
   /**
    * 口座(顧客)コード<BR>
    */
   public String accountCode;
   
   /**
    * 銘柄コード<BR>
    */
   public String productCode;
   
   /**
    * 通知受信日付<BR>
    */
   public String createdTimestamp;
   
   /**
    * 通知受信時刻From<BR>
    * （HHMMSS）<BR>
    */
   public String createdTimestampFrom;
   
   /**
    * 通知受信時刻To<BR>
    * （HHMMSS）<BR>
    */
   public String createdTimestampTo;
   
   /**
    * ページ内表示行数<BR>
    */
   public String pageSize;
   
   /**
    * 表示ページ番号<BR>
    */
   public String pageIndex;

   /**
    * ソートキー<BR>
    */
   public WEB3AdminFrontSortKey sortKeys[];
   
   /**
    * 銘柄タイプ<BR>
    */
   public String productType;
   
   /**
    * @@roseuid 42FB2C1400E9
    */
   public WEB3AdminFrontNoticeHistoryReferenceRequest() 
   {
    
   }
   
   /**
    * 当リクエストデータの整合性チェックを行う。<BR>
    * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
    * <BR>
    * １）市場コードチェック<BR>
    * 　@１－１）this.市場コード == <BR>
    * nullの場合、「市場コードがnull」の例外をスローする。<BR>
    * <BR>
    * ２）銘柄タイプチェック<BR>
    * 　@　@２－１）this.銘柄タイプ == nullの場合、「銘柄タイプがnull」の例外をスローする。<BR>
    *  　@　@class:WEB3BusinessLayerException<BR>
    *　@　@  tag:BUSINESS_ERROR_01109<BR>
    * 　@　@２－２）this.銘柄タイプ != 1:株式、かつ、this.銘柄タイプ != 6:先物オプションの場合。<BR>
    * 　@　@　@　@「銘柄タイプエラー」の例外をスローする<BR>
    *  　@　@class:WEB3BusinessLayerException<BR>
    *　@　@  tag:BUSINESS_ERROR_02395<BR>
    * <BR>
    * ３）部店コード一覧チェック<BR>
    *    this.部店コード一覧 != nullの場合、要素数分、以下のチェックを行う。<BR>
    * 　@　@３－1）this.部店コードが以下の条件に該当する場合、<BR>
    * 　@　@　@「部店コードエラー」の例外をスローする。<BR>
    * 　@　@　@　@・this.部店コード != 数値<BR>
    * 　@　@　@　@・this.部店コード.length != 3<BR>
    * <BR>
    * ４）顧客コードチェック<BR>
    * 　@this.顧客コード != nullの場合、以下のチェックを行う。<BR>
    * 　@４－１）this.顧客コードが以下の条件に該当する場合、<BR>
    * 　@　@　@　@　@「顧客コードエラー」の例外をスローする。<BR>
    * 　@　@　@　@　@　@・this.顧客コード != 数値<BR>
    * 　@　@　@　@　@　@・this.顧客コード.length != 6<BR>
    * <BR>
    * ５）銘柄コードチェック<BR>
    * 　@this.銘柄コード != nullの場合、以下のチェックを行う。<BR>
    * 　@５－１）this.銘柄コードが以下の条件に該当する場合、<BR>
    * 　@　@　@　@　@「銘柄コードエラー」の例外をスローする。<BR>
    * 　@　@　@　@　@　@・this.銘柄コード != 数値<BR>
    * 　@　@　@　@　@　@・this.銘柄タイプが1:株式の場合、this.銘柄コード.length != 5<BR>
    * 　@　@　@　@　@　@・this.銘柄タイプが6:先物オプションの場合、<BR>
    * 　@　@　@　@　@　@　@　@　@this.銘柄コード.length != 9<BR>
    * <BR>
    * ６）通知受信日付チェック<BR>
    * 　@６－１）this.通知受信日付 == <BR>
    * nullの場合、「通知受信日付がnull」の例外をスローする。<BR>
    * 　@<BR>
    * ７）通知受信時刻Fromチェック<BR>
    * 　@this.通知受信時刻From != nullの場合、以下のチェックを行う。<BR>
    * 　@７－１）this.通知受信時刻Fromが以下の条件に該当する場合、<BR>
    * 　@　@　@　@　@「通知受信時刻Fromエラー」の例外をスローする。<BR>
    * 　@　@　@　@　@　@・this.通知受信時刻From != 数値<BR>
    * 　@　@　@　@　@　@・this.通知受信時刻From.length != 6<BR>
    * <BR>
    * ８）通知受信時刻Toチェック<BR>
    * 　@this.通知受信時刻To != nullの場合、以下のチェックを行う。<BR>
    * 　@８－１）this.通知受信時刻Toが以下の条件に該当する場合、<BR>
    * 　@　@　@　@　@「通知受信時刻Toエラー」の例外をスローする。<BR>
    * 　@　@　@　@　@　@・this.通知受信時刻To != 数値<BR>
    * 　@　@　@　@　@　@・this.通知受信時刻To.length != 6<BR>
    * <BR>
    * ９）ソートキーチェック <BR>
    * 　@９－１）this.ソートキーの要素数分以下の処理を繰り返す。 <BR>
    * 　@　@９－１－１）ソートキー.validate()をコールする。 <BR>
    * <BR>
    * １０）要求ページ番号チェック <BR>
    * 　@１０－１）this.要求ページ番号 == nullであった場合、 <BR>
    * 　@　@　@　@「要求ページ番号がnull」の例外をスローする。 <BR>*
    * <BR>
    * 　@１０－２）this.要求ページ番号が数字以外の値であった場合、 <BR>
    * 　@　@　@　@「要求ページ番号が数字以外」の例外をスローする。 <BR>
    * <BR>
    * 　@１０－３）this.要求ページ番号 <= 0であった場合、 <BR>
    * 　@　@　@　@「要求ページ番号が0以下」の例外をスローする。 <BR>
    * <BR>
    * １１）ページ内表示行数チェック <BR>
    * 　@１１－１）this.ページ内表示行数 == nullであった場合、 <BR>
    * 　@　@　@　@「ページ内表示行数がnull」の例外をスローする。 <BR>
    * <BR>
    * 　@１１－２）this.ページ内表示行数が数字以外の値であった場合、 <BR>
    * 　@　@　@　@「ページ内表示行数が数字以外」の例外をスローする。 <BR>
    * 　@ <BR>
    * 　@１１－３）this.ページ内表示行数 <= 0であった場合、 <BR>
    * 　@　@　@　@「ページ内表示行数が0以下」の例外をスローする。<BR>
    * @@roseuid 42D1F2DD00EE
    */

   public void validate() throws WEB3BusinessLayerException
   {
		final String STR_METHOD_NAME = "validate()";
		log.entering(STR_METHOD_NAME);

		final int length_check0 = 0;
		final int length_check3 = 3;
		final int length_check5 = 5;
		final int length_check6 = 6;
        final int length_check9 = 9;

		int branchCode_length = WEB3StringTypeUtility.getByteLength(this.branchCode);
		int accountCode_length = WEB3StringTypeUtility.getByteLength(this.accountCode);
		int productCode_length = WEB3StringTypeUtility.getByteLength(this.productCode);
		int createdTimestampFrom_length = WEB3StringTypeUtility.getByteLength(this.createdTimestampFrom);
		int createdTimestampTo_length = WEB3StringTypeUtility.getByteLength(this.createdTimestampTo);
		int pageIndex_length = WEB3StringTypeUtility.getByteLength(this.pageIndex);
		int pageSize_length = WEB3StringTypeUtility.getByteLength(this.pageSize);
        int pageIndex_math = 0;
        int pageSize_math = 0;
        int l_intSortkeysLength  = 0;
       
		// １）「市場コードがnull」の例外をスローする
		if (this.convertMarketCode == null)
		{
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00443,
				this.getClass().getName() + "." + STR_METHOD_NAME);
		}

        // ２）銘柄タイプチェック。
        // ２－１）this.銘柄タイプ == nullの場合、「銘柄タイプがnull」の例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.productType))
        {
            throw new WEB3BusinessLayerException (
                WEB3ErrorCatalog.BUSINESS_ERROR_01109,
                this.getClass().getName() + "." + STR_METHOD_NAME);
            
        }
        //２－２）this.銘柄タイプ != 1:株式、かつ、this.銘柄タイプ != 6:先物オプションの場合、
        //「銘柄タイプエラー」の例外をスローする。
        if (!(Integer.toString(ProductTypeEnum.EQUITY.intValue()).equals(this.productType))
            &&!(Integer.toString(ProductTypeEnum.IFO.intValue()).equals(this.productType)))
        {
            throw new WEB3BusinessLayerException (
                WEB3ErrorCatalog.BUSINESS_ERROR_02395,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
		// ３）「部店コードがnull」以外のときチェック
		if (this.branchCode != null)
		{
			// 「部店コードエラー」の例外をスローする(数値以外)
			if (!WEB3StringTypeUtility.isNumber(branchCode))
			{
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_01729,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
			
			// 「部店コードエラー」の例外をスローする(長さ違反)
			if (branchCode_length != length_check3)
			{
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_00834,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
		}

		// ４）「口座(顧客)コードがnull」以外のときチェック
		if (this.accountCode != null)
		{
			// 「口座(顧客)コードエラー」の例外をスローする(数値以外)
			if (!WEB3StringTypeUtility.isNumber(accountCode))
			{
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_01043,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
				
			// 「口座(顧客)コードエラー」の例外をスローする(長さ違反)
			if (accountCode_length != length_check6)
			{
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_00836,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
		}

		// ５）「銘柄コードがnull」以外のときチェック
		if (this.productCode != null)
		{
			// 「銘柄コードエラー」の例外をスローする(数値以外)
			if (!WEB3StringTypeUtility.isNumber(productCode))
			{
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_00815,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
					
			// 「銘柄コードエラー」の例外をスローする(長さ違反)
            //  this.銘柄タイプが1:株式の場合、this.銘柄コード.length != 5
            if (Integer.toString(ProductTypeEnum.EQUITY.intValue()).equals(this.productType))
            {
                if (productCode_length != length_check5)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00439,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
            //this.銘柄タイプが6:先物オプションの場合、this.銘柄コード.length != 9
            if (Integer.toString(ProductTypeEnum.IFO.intValue()).equals(this.productType))
            {
                if (productCode_length != length_check9)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00439,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
		
		}

		// ６）「通知受信日付がnull」の例外をスローする
		if (this.createdTimestamp == null)
		{
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02217,
				this.getClass().getName() + "." + STR_METHOD_NAME);
		}

		// ７）「通知受信時刻Fromがnull」以外のときチェック
		if (this.createdTimestampFrom != null)
		{
			// 「通知受信時刻From」の例外をスローする(数値以外)
			if (!WEB3StringTypeUtility.isNumber(createdTimestampFrom))
			{
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_02218,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
						
			// 「通知受信時刻From」の例外をスローする(長さ違反)
			if (createdTimestampFrom_length != length_check6)
			{
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_02218,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
		}

		// ８）「通知受信時刻Toがnull」以外のときチェック
		if (this.createdTimestampTo != null)
		{
			// 「通知受信時刻To」の例外をスローする(数値以外)
			if (!WEB3StringTypeUtility.isNumber(createdTimestampTo))
			{
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_02219,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
							
			// 「通知受信時刻To」の例外をスローする(長さ違反)
			if (createdTimestampTo_length != length_check6)
			{
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_02219,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
		}

        //９）ソートキーチェック  
        //　@９－１）this.ソートキーの要素数分以下の処理を繰り返す。  
        //　@　@９－１－１）ソートキー.validate()をコールする。
        if (this.sortKeys != null)
        {
            for (int i = 0; i < sortKeys.length; i++)
            {
                WEB3AdminFrontSortKey l_adminFrontSortKey = sortKeys[i];
                l_adminFrontSortKey.validate();
            }
        }

		// １０）「要求ページ番号がnull」以外のときチェック
		if (this.pageIndex != null)
		{
			// 「要求ページ番号エラー」の例外をスローする(数値以外)
			if (!WEB3StringTypeUtility.isNumber(pageIndex))
			{
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_00090,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
            // int型に変換
            pageIndex_math = Integer.parseInt(pageIndex);
				
			// 「要求ページ番号エラー」の例外をスローする(長さ0以下の時)
			if (pageIndex_math <= length_check0)
			{
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_00616,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
		}
        // nullエラー
        else{
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

		// １１）「ページ内表示行数がnull」以外のときチェック
		if (this.pageSize != null)
		{
			// 「ページ内表示行数エラー」の例外をスローする(数値以外)
			if (!WEB3StringTypeUtility.isNumber(pageSize))
			{
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_00092,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
            // int型に変換
            pageSize_math = Integer.parseInt(pageSize);                    

			// 「ページ内表示行数エラー」の例外をスローする(長さ0以下の時)
			if (pageSize_math <= length_check0)
			{
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_00617,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
		}
       // nullエラー
       else{
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00091,
               this.getClass().getName() + "." + STR_METHOD_NAME);
       }

		log.exiting(STR_METHOD_NAME);
    }

    /* (非 Javadoc)
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public WEB3GenResponse createResponse() {
       // TODO 自動生成されたメソッド・スタブ
       return new WEB3AdminFrontNoticeHistoryReferenceResponse(this);
    }
}
@
