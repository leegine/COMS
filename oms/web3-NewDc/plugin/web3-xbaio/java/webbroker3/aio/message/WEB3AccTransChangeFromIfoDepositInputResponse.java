head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.53.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AccTransChangeFromIfoDepositInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証拠金から振替入力レスポンスクラス(WEB3AccTransChangeFromIfoDepositInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 于美麗 (中訊) 新規作成
                   2004/10/22 屈陽 (中訊) レビュー
Revesion History : 2007/08/23 武波 (中訊) 仕様変更・モデル753
*/
package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (証拠金から振替入力レスポンス)<BR>
 * 証拠金から振替入力レスポンスクラス<BR>
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3AccTransChangeFromIfoDepositInputResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "acc_trans_change_from_ifo_deposit_input";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200409291950L;
        
    /**
     * (振替上限回数)<BR>
     * 1日の振替上限回数<BR>
     */
    public String changeCountUpper;
    
    /**
     * (振替回数)<BR>
     * 顧客の現時点での1日の振替回数<BR>
     */
    public String changeCount;
    
    /**
     * (振替可能額)<BR>
     * 顧客の現時点での振替可能額<BR>
     * <BR>
     */
    public String changePossAmt;
    
    /**
     * (証拠金残高)<BR>
     * 顧客の現時点での証拠金残高<BR>
     */
    public String ifoDepositBal;
    
    /**
     * (お預かり金残高)<BR>
     * 顧客の現時点での預り金残高<BR>
     */
    public String depositBal;

    /**
     * デフォルトコンストラクタ
     * @@roseuid 4158E8E30170
     */
    public WEB3AccTransChangeFromIfoDepositInputResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AccTransChangeFromIfoDepositInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}
@
