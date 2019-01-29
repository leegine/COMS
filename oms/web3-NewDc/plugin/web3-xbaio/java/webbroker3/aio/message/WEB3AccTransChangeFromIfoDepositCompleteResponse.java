head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.18.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AccTransChangeFromIfoDepositCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証拠金から振替完了レスポンスクラス(WEB3AccTransChangeFromIfoDepositCompleteResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 于美麗 (中訊) 新規作成
                   2004/10/22 屈陽 (中訊) レビュー
*/
package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (証拠金から振替完了レスポンス)<BR>
 * 証拠金から振替完了レスポンスクラス<BR>
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3AccTransChangeFromIfoDepositCompleteResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "acc_trans_change_from_ifo_deposit_complete";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200409291950L;
    
    /**
     * (更新時間)<BR>
     * 注文を登録した時間<BR>
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * (注文ID)<BR>
     * 確認処理で取得した注文ID<BR>
     */
    public String orderId;
    
    /**
     * デフォルトコンストラクタ
     * @@roseuid 4158E8E201DD
     */
    public WEB3AccTransChangeFromIfoDepositCompleteResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AccTransChangeFromIfoDepositCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}
@
