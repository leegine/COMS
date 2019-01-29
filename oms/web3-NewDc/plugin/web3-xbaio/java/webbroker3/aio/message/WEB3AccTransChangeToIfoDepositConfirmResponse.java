head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.09.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AccTransChangeToIfoDepositConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証拠金への振替確認レスポンスクラス(WEB3AccTransChangeToIfoDepositConfirmResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 于美麗 (中訊) 新規作成
                   2004/10/22 屈陽 (中訊) レビュー
Revesion History : 2007/08/23 武波 (中訊) 仕様変更・モデル753
*/
package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (証拠金への振替確認レスポンス)<BR>
 * 証拠金への振替確認レスポンスクラス
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3AccTransChangeToIfoDepositConfirmResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "acc_trans_change_to_ifo_deposit_confirm";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200409291950L;        
    
    /**
     * (振替後振替可能額)<BR>
     * 顧客の振替後の振替可能額
     */
    public String aftChangePossAmt;
    
    /**
     * (振替前証拠金)<BR>
     * 顧客の振替前の証拠金残高
     */
    public String preIfoDeposit;
    
    /**
     * (振替後証拠金)<BR>
     * 顧客の振替後の証拠金残高
     */
    public String aftIfoDeposit;
    
    /**
     * (振替後未入金額)<BR>
     * 顧客の振替後の証拠金不足額
     */
    public String aftNonPayAmt;

    /**
     * (確認時発注日)
     */
    public Date checkDate;
    
    /**
     * (注文ID)<BR>
     * 確認処理で取得した注文ID
     */
    public String orderId;
    
    /**
     * デフォルトコンストラクタ
     * @@roseuid 4158E99200D1
     */
    public WEB3AccTransChangeToIfoDepositConfirmResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AccTransChangeToIfoDepositConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}
@
