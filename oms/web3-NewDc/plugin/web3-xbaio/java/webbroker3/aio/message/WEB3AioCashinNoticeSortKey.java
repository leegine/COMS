head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.10.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinNoticeSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金通知ソートキークラス(WEB3AioCashinNoticeSortKey)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/21 韋念瓊 (中訊) 新規作成
                 : 2006/08/23  車進(中訊) 仕様変更 モデル 614
*/

package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;


/**
 * 入金通知ソートキークラス<BR>
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3AioCashinNoticeSortKey extends Message 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinNoticeSortKey.class);    
    
    /**
     * (ソートキー)<BR>
     */
    public String keyItem;
    
    /**
     * (昇順／降順)<BR>
     * A:昇順　@D:降順<BR>
     */
    public String ascDesc;
    
    /**
     * (部店コード)<BR>
     * 部店コード（列名）<BR>
     */
    private String BRANCH_CODE = "branch_code";
    
    /**
     * (顧客コード)<BR>
     * 顧客コード（列名）<BR>
     */
    private String ACCOUNT_CODE = "account_code";
    
    /**
     * (勘定日)<BR>
     * 勘定日（列名）<BR>
     */
    private String SETTLEMENT_DATE = "deposit_data_account_date";
    
    /**
     * (振込依頼人コード)<BR>
     * 振込依頼人コード（列名）<BR>
     */
    private String CLIENT_CODE = "deposit_data_trans_person_code";
    
    /**
     * (銀行コード)<BR>
     * 銀行コード（列名）<BR>
     */
    private String BANK_CODE = "bank_code";    
    
    /**
     * (支店コード)<BR>
     * 支店コード（列名）<BR>
     */
    private String BANK_BRANCH_CODE = "bank_branch_code";
    
    /**
     * (処理区分)<BR>
     * 処理区分（列名）<BR>
     */
    private String TRANSACTION_DIV = "status";
    
    /**
     * (処理日時)<BR>
     * 処理日時（列名）<BR>
     */
    private String TRANSACTION_DATE = "last_updated_timestamp";
    
    /**
     * (通貨コード )<BR>
     * 通貨コード（列名）<BR>
     */
    private String CURRENCY_CODE = "currency_code";
    
    /**
     * コンストラクタ。 <BR>
     * <BR>
     *１）this.ソートキーに引数.キー項目をセットする。<BR>
     *２）this.昇順／降順に引数.昇順／降順をセットする。<BR>
     * @@param String  - (キー項目)
     * @@param String  - (昇順／降順)
     * @@roseuid 40A9B8B4003A
     */
    public WEB3AioCashinNoticeSortKey()
    {
       
    }   
    
    /**
     * ソートキーがnullでない場合 <BR>
     * <BR>
     * this.部店コード <BR>
     * this.顧客コード <BR>
     * this.勘定日<BR>
     * this.振込依頼人コード <BR>
     * this.銀行名 <BR>
     * this.支店名 <BR>
     * this.処理区分 <BR>
     * this.処理日時 <BR>
     * this.通貨コード <BR>
     * のいづれにも等しくない場合 <BR>
     * <BR>
     * 「キー項目に項目名以外の値が存在エラー」 <BR>
     * 例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00086<BR>
     * 
     * @@throws WEB3BaseException
     * @@roseuid 40A9B8B4003A
     */
    public void validate() throws WEB3BaseException 
    {        
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //ソートキーがnullでない場合 

        //this.部店コード 
        //this.顧客コード 
        //this.勘定日コード 
        //this.振込依頼人コード 
        //this.銀行名 
        //this.指定名 
        //this.処理区分 
        //this.処理日時 
        //this.通貨コード
        //のいづれにも等しくない場合 

        //「キー項目に項目名以外の値が存在エラー」 
        //例外をスローする。 

        if (this.keyItem != null)
        {
            log.debug("キー項目 = " + this.keyItem);
            
            if (!this.BRANCH_CODE.equals(this.keyItem) &&
                !this.ACCOUNT_CODE.equals(this.keyItem) && 
                !this.SETTLEMENT_DATE.equals(this.keyItem) && 
                !this.CLIENT_CODE.equals(this.keyItem) &&
                !this.BANK_CODE.equals(this.keyItem) &&
                !this.BANK_BRANCH_CODE.equals(this.keyItem) &&
                !this.TRANSACTION_DIV.equals(this.keyItem) && 
                !this.TRANSACTION_DATE.equals(this.keyItem) &&
                !this.CURRENCY_CODE.equals(this.keyItem))
            {
                log.debug("キー項目に項目名以外の値が存在エラー");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "キー項目に項目名以外の値が存在エラー");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     *ソートキーに<BR>
     *昇順／降順指定を付加しソート文字列を作成、返却する。<BR>
     *<BR>
     *１）ソートキーに<BR>
     *昇順／降順が無指定または昇順／降順がWEB3AscDescDef.ASCと等しい場合は" asc",<BR>
     *WEB3AscDescDef.DSCと等しい場合 " desc"を付加する。<BR>
     *<BR>
     *２）作成したソート条件文字列を返す。<BR>
     *※ソートキーがnullの場合はnullを返す。<BR>
     * @@return String
     * @@roseuid 40A9B8B4003A
     */
    public String createSortKeySpec() 
    {        
        final String STR_METHOD_NAME = "createSortKeySpec()";
        log.entering(STR_METHOD_NAME);
        
        String l_strSortKeySpec = null;
        
        //１）ソートキーに
        //昇順／降順が無指定または昇順／降順がWEB3AscDescDef.ASCと等しい場合は" asc",
        //WEB3AscDescDef.DSCと等しい場合 " desc"を付加する。
        
        if (this.keyItem != null)
        {
            if (this.ascDesc == null || WEB3AscDescDef.ASC.equals(this.ascDesc))
            {
                l_strSortKeySpec = this.keyItem + " asc";
            }
            else
            {
                l_strSortKeySpec = this.keyItem  + " desc";
            }
        }

        //２）　@作成したソート条件文字列を返す。 
        //※ソートキーがnullの場合はnullを返す。。
        
        log.exiting(STR_METHOD_NAME);
        return l_strSortKeySpec;
    }
}
@
