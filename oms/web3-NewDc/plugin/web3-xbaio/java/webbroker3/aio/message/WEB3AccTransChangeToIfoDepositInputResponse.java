head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.17.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AccTransChangeToIfoDepositInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証拠金への振替入力レスポンスクラス(WEB3AccTransChangeToIfoDepositInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 于美麗 (中訊) 新規作成
                   2004/10/25 周勇(中訊) レビュー
Revesion History : 2007/08/23 武波 (中訊) 仕様変更・モデル753
*/
package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (証拠金への振替入力レスポンス)<BR>
 * 証拠金への振替入力レスポンスクラス
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3AccTransChangeToIfoDepositInputResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "acc_trans_change_to_ifo_deposit_input";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200409291950L;        
    /**
     * (振替上限回数)<BR>
     * 1日の振替上限回数
     */
    public String changeCountUpper;
    
    /**
     * (振替回数)<BR>
     * 顧客の現時点での1日の振替回数
     */
    public String changeCount;
    
    /**
     * (振替可能額)<BR>
     * 顧客の現時点での振替可能額
     */
    public String changePossAmt;
    
    /**
     * (振替前証拠金)<BR>
     * 顧客の現時点での証拠金残高
     */
    public String preIfoDeposit;
    
    /**
     * (未入金額)<BR>
     * 顧客の現時点での未入金額
     */
    public String nonPayAmt;

    /**
     * デフォルトコンストラクタ
     * @@roseuid 4158E9B401DF
     */
    public WEB3AccTransChangeToIfoDepositInputResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AccTransChangeToIfoDepositInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}
@
