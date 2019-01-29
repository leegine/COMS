head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFrontSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・通知履歴参照ソートキークラス (WEB3AdminFrontSortKey.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/27  王明明 (中訊) 仕様変更モデルNo.120
*/

package webbroker3.eqtypeadmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.eqtypeadmin.define.WEB3AdminFrontMarketKeyItemDef;
import webbroker3.util.WEB3LogUtility;

/**
 * 通知履歴参照ソートキークラス<BR>
 */
public class WEB3AdminFrontSortKey extends Message 
{
 
	/**
	 * ログ出力ユーティリティ。
	 */
	private static WEB3LogUtility log =
		WEB3LogUtility.getInstance(WEB3AdminFrontSortKey.class);

   /**
    * キー項目<BR>
    */
   public String keyItem;
   
   /**
    * 昇順／降順<BR>
    * <BR>
    * A：　@昇順<BR>
    * D：　@降順<BR>
    */
   public String ascDesc;
   
   /**
    * コンストラクタ<BR>
    * @@roseuid 42D1F5140285
    */
   public WEB3AdminFrontSortKey() 
   {
    
   }
   
   /**
    * 当クラスの整合性チェックを行う。<BR>  
    * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>  
    * <BR>
    * １）this.キー項目 != nullの場合、以下のチェックを行う。<BR> 
    * 　@１－１）this.キー項目に以下の項目以外が設定されている場合、<BR>  
    * 　@　@　@　@　@「ソートキー.キー項目が未定義の値」の例外をスローする。<BR> 
    * 　@　@　@　@　@　@・通知受信日時 <BR>
    * 　@　@　@　@　@　@・データ種別コード <BR>
    * 　@　@　@　@　@　@・口座コード <BR>
    * 　@　@　@　@　@　@・部店コード <BR>
    * 　@　@　@　@　@　@・銘柄コード <BR>
    * 　@　@　@　@　@　@・仮想サーバNo<BR>
    * <BR>
    * 　@　@　@　@class :　@WEB3BusinessLayerException<BR>
    * 　@　@　@　@tag 　@:　@BUSINESS_ERROR_00086<BR>
    * <BR>
    * @@throws WEB3BusinessLayerException
    */
   public void validate() throws WEB3BusinessLayerException
   {
		final String STR_METHOD_NAME = ".validate()";
		log.entering(STR_METHOD_NAME);

        // １）this.キー項目 != nullの場合
        if (this.keyItem != null)
        {
            // １－１）this.キー項目に以下の項目以外が設定されている場合
            if (!(WEB3AdminFrontMarketKeyItemDef.CREATED_TIMESTAMP).equals(this.keyItem)
                &&!(WEB3AdminFrontMarketKeyItemDef.DATA_CLASS_CODE).equals(this.keyItem)
                &&!(WEB3AdminFrontMarketKeyItemDef.ACCOUNT_CODE).equals(this.keyItem)
                &&!(WEB3AdminFrontMarketKeyItemDef.BRANCH_CODE).equals(this.keyItem)
                &&!(WEB3AdminFrontMarketKeyItemDef.PRODUCT_CODE).equals(this.keyItem)
                &&!(WEB3AdminFrontMarketKeyItemDef.VIRTUAL_SERVER_NUMBER).equals(this.keyItem))
            {
                log.error("「ソートキー.キー項目が未定義の値」の例外をスローする");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "ソートキーのキー項目の値が存在しないコード値です。");
            }
        }
        
        log.exiting(STR_METHOD_NAME);
   }
}
@
