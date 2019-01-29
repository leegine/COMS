head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.47.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioTransUnitDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3AioTransUnitDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/14 王蘭芬 (中訊) 新規作成
                 : 2006/11/07 何文敏 (中訊) 仕様変更 No.679
Revesion History : 2008/09/22 王志葵 (中訊) 仕様変更 No.1000
*/
package webbroker3.aio.define;

/**
 * 入出金明細メッセージの取引　@定数定義インタフェイス
 *                                                                     
 * @@author 王蘭芬
 * @@version 1.0
 */
public interface WEB3AioTransUnitDef
{
    /**
     * 0： 出金  
     */
    public static final String CASH_OUT = "0";
    
    /**
     * 1： 会費出金 
     */
    public static final String DUES_CASH_OUT = "1";

    /**
     * 2： 情報料出金 
     */
    public static final String INFO_CASN_OUT = "2";

    /**
     * 3： 入金 
     */
    public static final String CASH_IN = "3";

    /**
     * 4： 入金連絡
     */
    public static final String CASH_IN_CONTACT = "4";

    /**
     * 5： 先物OP証拠金へ出金
     */
    public static final String FUTURE_OP_MARGIN_CASHOUT = "5";
    
    /**
     * 6： 先物OP証拠金から入金
     */
    public static final String FUTURE_OP_MARGIN_TO_CASHIN = "6";
    
    /**
     * 7： 信用保証金へ出金
     */
    public static final String MARGIN_GUARANTEE_CASHOUT = "7";
    
    /**
     * 8： 信用保証金から入金
     */
    public static final String MARGIN_GUARANTEE_TO_CASHIN = "8";
    
    /**
     * 9： FX保証金へ出金
     */
    public static final String FX_CASHOUT = "9";
    
    /**
     * 10： FX保証金から入金
     */
    public static final String FX_TO_CASHIN = "10";
    
    /**
     * 11： 中国株式口座へ出金
     */
    public static final String MIDIUM_TERM_GOV_EQUITY_ACCOUNT_CASHOUT = "11";
    
    /**
     * 12： 中国株式口座から入金
     */
    public static final String MIDIUM_TERM_GOV_EQUITY_ACCOUNT_TO_CASHIN = "12";
    
    /**
     * 13： その他出金
     */
    public static final String OTHER_CASHOUT = "13";
    
    /**
     * 14： その他入金
     */
    public static final String OTHER_CASHIN = "14";
    
    /**
     * 15:  担保ローン返済
     */
    public static final String SECURITY_LOAN_REPAY = "15";

    /**
     * 16:  （CFD口座へ出金）
     */
    public static final String CFD_ACCOUNT_CASHOUT = "16";

    /**
     * 17:  （CFD口座から入金）
     */
    public static final String CFD_ACCOUNT_TO_CASHIN = "17";
}

@
