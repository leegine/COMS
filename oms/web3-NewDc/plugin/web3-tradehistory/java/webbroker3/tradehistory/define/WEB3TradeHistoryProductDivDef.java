head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TradeHistoryProductDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取引履歴一覧リクエスト(WEB3TradeHistoryProductDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/25  温 顕 法@(中訊) 新規作成
                   2006/10/19  張騰宇 (中訊) モデル 057
*/
package webbroker3.tradehistory.define;

/**
 * 商品区分 定数定義インタフェイス
 * 
 * @@author 温 顕 法@
 * @@version 1.0
 */
public interface WEB3TradeHistoryProductDivDef
{

    /**
     *A：　@全商品
     */
    public final static String ALL_PRODUCT = "A";

    /**
     *B：　@現物・信用
     */
    public final static String EQUITY_MARGIN = "B";

    /**
     *C：　@現物
     */
    public final static String EQUITY = "C";
    
    /**
     *D：　@信用
     */
    public final static String MARGIN = "D";

    /**
     * E：　@先物・オプション
     */
    public final static String FUTURES_OPTION = "E";
        
    /** 
     * F：　@先物
     */
    public final static String FUTURES = "F";
            
    /** 
     * G：　@オプション
     */     
    public final static String OPTION = "G";  
      
    /**
     * H：　@投信・累投
     */
    public final static String MUTUAL_FUND_RUITO = "H" ;
    
    /**
     * I：　@入出金<BR>
     */
    public final static String AIO = "I" ; 
    
    /**
     * J：　@譲渡益税<BR>
     */
    public final static String CPITAL_GAIN_TAX = "J" ;
    
    /**
     * K：　@外国株式<BR>
     */
    public final static String FOREIGN = "K" ;

    /**
     * L：　@債券<BR>
     */
    public final static String BOND = "L";
}
@
