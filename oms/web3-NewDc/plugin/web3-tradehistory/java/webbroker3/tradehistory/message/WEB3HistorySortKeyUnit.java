head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.02.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3HistorySortKeyUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取引履歴ソートキー(WEB3HistorySortKeyUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/25 範慧琴 (中訊) 新規作成
*/
package webbroker3.tradehistory.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.tradehistory.define.WEB3HistoryKeyItemDef;


/**
 * (取引履歴ソートキー)<BR>
 * 取引履歴ソートキークラス<BR>
 *                                                                
 * @@author 範慧琴
 * @@version 1.0
 */
public class WEB3HistorySortKeyUnit extends Message 
{

    /**         
     * ログ出力ユーティリティ。         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3HistorySortKeyUnit.class);    


    /**
     * (キー項目)<BR>
     * キー項目：”受渡日”、”約定日”<BR>
     */
    public String keyItem;
    
    /**
     * (昇順／降順)<BR>
     * 昇順／降順<BR>
     * <BR>
     * A：　@昇順<BR>
     * D：　@降順<BR>
     */
    public String ascDesc;
    
    /**
     * (取引履歴ソートキー)<BR>
     * コンストラクタ<BR>
     * @@return webbroker3.tradehistory.message.WEB3HistorySortKeyUnit
     * @@roseuid 41340C40010D
     */
    public WEB3HistorySortKeyUnit() 
    {
     
    }
    
    /**
     * 当クラスの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）this.キー項目＝nullの場合、<BR>
     * 　@　@「ソートキー.キー項目がnull」の例外をスローする。<BR>
     *      class         :  WEB3BusinessLayerException           <BR>
     *      tag            :  BUSINESS_ERROR_00085              <BR>
     * <BR>
     * ２）this.昇順／降順＝nullの場合、<BR>
     * 　@　@「ソートキー.昇順／降順がnull」の例外をスローする。<BR>
     *      class         :  WEB3BusinessLayerException           <BR>
     *      tag            :  BUSINESS_ERROR_00087              <BR>
     * <BR>
     * ３）this.昇順／降順が下記の項目以外の場合、<BR>
     * 　@　@「ソートキー.昇順／降順が未定義の値」の例外をスローする。<BR>
     * 　@　@　@・”A：昇順”<BR>
     * 　@　@　@・”D：降順”<BR>
     *      class         :  WEB3BusinessLayerException           <BR>
     *      tag            :  BUSINESS_ERROR_00088         <BR>
     * <BR>
     * ４）this.キー項目が下記の項目以外の場合、<BR>
     * 　@　@「ソートキー.キー項目が未定義の値」の例外をスローする。<BR>
     * 　@　@　@・”受渡日”<BR>
     * 　@　@　@・”約定日”<BR>
     *      class         :  WEB3BusinessLayerException           <BR>
     *      tag            :  BUSINESS_ERROR_00086              <BR>
     * <BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 41340C3A00DE
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()"; 
        
        log.entering(STR_METHOD_NAME);  
        
        //１）this.キー項目のチェック
        //     this.キー項目＝nullの場合、>
        //     「ソートキー.キー項目がnull」の例外をスローする。
        if(this.keyItem == null || "".equals(this.keyItem))
        {
            log.error(" 「ソートキー.キー項目がnull」の例外をスローする");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                            this.getClass().getName() + STR_METHOD_NAME);
        }
        
        // ２）this.昇順／降順のチェック
        //     this.昇順／降順＝nullの場合、<BR>
        // 　@  「ソートキー.昇順／降順がnull」の例外をスローする。<BR>         
        //
        if(this.ascDesc == null || "".equals(this.ascDesc))
        {
            log.error("「ソートキー.昇順／降順がnull」の例外をスローする");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                            this.getClass().getName() + STR_METHOD_NAME);
        }
        
        // ３）this.昇順／降順のチェック
        //     this.昇順／降順が下記の項目以外の場合、<BR>
        // 　@　@「ソートキー.昇順／降順が未定義の値」の例外をスローする。<BR>
        // 　@　@　@・”A：昇順”<BR>
        // 　@　@　@・”D：降順”<BR>         
        //
        if(!WEB3AscDescDef.ASC.equals(this.ascDesc) && !WEB3AscDescDef.DESC.equals(this.ascDesc))
        {
            log.error("「ソートキー.昇順／降順が未定義の値」の例外をスローする");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                            this.getClass().getName() + STR_METHOD_NAME);    
        }
        
        // ４）this.キー項目のチェック
        //     this.キー項目が下記の項目以外の場合、<BR>
        // 　@　@「ソートキー.キー項目が未定義の値」の例外をスローする。<BR>
        // 　@　@　@・”受渡日”<BR>
        // 　@　@　@・”約定日”<BR>
        //
        if(!WEB3HistoryKeyItemDef.DELIVERY_DATE.equals(this.keyItem) && !WEB3HistoryKeyItemDef.EXEC_DATE.equals(this.keyItem))
        {
            log.error("「ソートキー.キー項目が未定義の値」の例外をスローする");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                            this.getClass().getName() + STR_METHOD_NAME);    
        } 
         
        log.exiting(STR_METHOD_NAME);          
    }
}
@
