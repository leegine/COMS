head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.40.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3RemarkCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 摘要コード  定数定義インタフェイス(WEB3RemarkCodeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/23　@彭巍 (SRA) 新規作成
Revesion History : 2007/01/31　@栄イ(中訊) 入出金仕様変更管理台帳・ＤＢレイアウトNo127
*/
package webbroker3.common.define;

/**
 * 摘要コード　@定数定義インタフェイス
 *
 * @@author 彭巍(SRA)
 * @@version 1.0
 */
public interface WEB3RemarkCodeDef
{
    /**
     * 01:信用保証金   
     */
    public static final String MARGIN_GUARANTEE = "01";

    /**
     *  72:株先証拠金　@
     */
    public static final String STOCK_FUTURES_MARGIN = "72";
    
    /**
     *  86:為替保証金
     */
    public static final String EXCHANGE_GUARANTEE = "86";
    
    /**
     *  02：（発）委託保証金
     */
    public static final String BIZ_CONSIGN_GUARANTEE = "02";
    
    /**
     *   03：端株整理  
     */
    public static final String STOCK_REF = "03";

    /**
     * 06：名義書換料    
     */
    public static final String NAME_TRANSFER = "06";

    /**
     *  07：（保）口座管理料
     */
    public static final String SAFE_ACCOUNT_MANAGE = "07";

    /**
     *  08：（外国）口座管理料  
     */
    public static final String FOREIGN_ACCOUNT_MANAGE = "08";

    /**
     *   09：（金）口座管理料    
     */
    public static final String CASH_ACCOUNT_MANAGE = "09";

    /**
     *  10：（債券先物）委託保証金  
     */
    public static final String BOND_FUTURE_CONSIGN_GUARANTEE = "10";
    
    /**
     *  11：（株式先物）委託保証金     
     */
    public static final String EQUITY_FUTURE_CONSIGN_GUARANTEE = "11";
    
    /**
     *  14：（オプション）委託保証金
     */
    public static final String OPTION_CONSIGN_GUARANTEE = "14";
    
    /**
     *  19：（株券オプション）委託保証金 
     */
    public static final String STOCK_BOND_OPTION_CONSIGN_GUARANTEE = "19";

    /**
     *  24：銀行振込手数料
     */
    public static final String BANK_TRANSFER_COMMISSIONFEE = "24";

    /**
     *  42：スーパーG（継続）月曜       
     */
    public static final String SUPER_MARKET_G_CONTINUE_MONDAY = "42";

    /**
     *  43：スーパーG（継続）火曜 
     */
    public static final String SUPER_MARKET_G_CONTINUE_TUESDAY = "43";

    /**
     *  44：スーパーG（継続）水曜 
     */
    public static final String SUPER_MARKET_G_CONTINUE_WEDNESDAY = "44";
    
    /**
     *  45：スーパーG（継続）木曜
     */
    public static final String SUPER_MARKET_G_CONTINUE_THURSDAY = "45";
    
    /**
     *  46：スーパーG（継続）金曜  
     */
    public static final String SUPER_MARKET_G_CONTINUE_FRIDAY = "46";
    
    /**
     *  47：金貯蓄 1ヶ月
     */
    public static final String SAVING_ONE_MONTH = "47";
    
    /**
     *  48：金貯蓄 3ヶ月    
     */
    public static final String SAVING_THREE_MONTHS = "48";
    
    /**
     *  49：金貯蓄 6ヶ月     
     */
    public static final String SAVING_SIX_MONTHS = "49";
    
    /**
     *  50：金貯蓄 1年 
     */
    public static final String SAVING_ONE_YEAR = "50";
    
    /**
     *  52：中期国債ファ@ンド 
     */
    public static final String MEDIUM_TERM_NATIONAL_BONDS_FUNDS = "52";
    
    /**
     *   53：（累投）口座管理料 
     */
    public static final String RUITO_ACCOUNT_MANAGE = "53";
    
    /**
     *   54：中期国債ファ@ンドキャッシング 
     */
    public static final String MEDIUM_TERM_NATIONAL_BONDS_FUNDS_CASHING = "54";
    
    /**
     *  55：MMFキャッシング
     */
    public static final String MMF_CASHING = "55";  
    
    /**
     *  71：（先物オプション 東証）
     */
    public static final String FUTURES_OPTION_TOKYO_STOCK_EXCHANGE = "71";
        
    /**
     *  73：（先物オプション 名証） 
     */
    public static final String FUTURES_OPTION_NAGOYA_STOCK_EXCHANGE = "73";
        
    /**
     *  74：（先物オプション 利益払出）
     */
    public static final String FUTURES_OPTION_PROFIT_PAY = "74";
    
    /**
     *  93：その他預り金
     */
    public static final String DEFAULT_FROM_DEPOSIT = "93";

    /**
     *  97：外貨振替
     */
    public static final String FOREIGN_TRANSFER = "97";
    
    /**
     * 98：摘要入力内容 継続03 
     */
    public static final String REMARK_INPUT_REQUEST_CONTINUE_THREE = "98";
    
    /**
     *  99：BLANK
     */
    public static final String BLANK = "99";
}
@
