head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.04.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyApplyInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信定時定額買付新規申込入力レスポンス (WEB3MutualFixedBuyApplyInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/26 徐宏偉 (中訊) 新規作成
*/
package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 *（投信定時定額買付新規申込入力レスポンス）<BR>
 * 投信定時定額買付新規申込入力レスポンス<BR>
 * 
 * @@author 徐宏偉 (中訊) 
 * @@version 1.0 
 */
public class WEB3MutualFixedBuyApplyInputResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mutual_fixed_buy_apply_input";
    
    /**
     * SerialVersionUID<BR>
     */ 
    public final static long serialVersionUID = 200606261701L;
  
    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     */
    public String accountCode;

    /**
     * (顧客名（カナ）)<BR>
     * 顧客名（カナ）<BR>
     */   
    public String accountNameKana;

    /**
     * (顧客名（漢字）)<BR>
     * 顧客名（漢字）<BR>
     */   
    public String accountName;
  
    /**
     * (郵便番号)<BR>
     * 郵便番号<BR>
     */   
    public String zipCode;
 
    /**
     * (住所１)<BR>
     * 住所１<BR>
     */   
    public String address1;
 
    /**
     * (住所２)<BR>
     * 住所２<BR>
     */   
    public String address2;
 
    /**
     * (住所３)<BR>
     * 住所３<BR>
     */   
    public String address3;
   
    /**
     * (投信定時定額買付条件一覧)<BR>
     * 投信定時定額買付条件一覧<BR>
     */   
    public WEB3MutualFixedBuyConditionUnit[] conditionList;
  
    /**
     * (投信銘柄カテゴリー一覧)<BR>
     * 投信銘柄カテゴリー一覧<BR>
     */   
    public WEB3MutualProductCategoryUnit[] categoryList;
 
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3MutualFixedBuyApplyInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
   
    /**
     * (投信定時定額買付新規申込入力レスポンス)<BR>
     * デフォルトコンストラクタ<BR>
     */
    public WEB3MutualFixedBuyApplyInputResponse() 
    {     
    }    
}
@
