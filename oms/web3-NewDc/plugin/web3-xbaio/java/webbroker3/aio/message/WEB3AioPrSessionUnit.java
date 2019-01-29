head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.56.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioPrSessionUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ＰＲ層保持情報クラス(WEB3AioPrSessionUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/29 周勇 (中訊) 新規作成
*/

package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (ＰＲ層保持情報クラス)<BR>
 * ＰＲ層保持情報クラスクラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0 
 */
public class WEB3AioPrSessionUnit extends Message
{
    /**
     * （セッションキー）<BR>
     *  セッションキー 
     *  (画面表示なし)
     */
    public String wolfSession;
    
    /**
     * (アプリケーションID )<BR>
     * アプリケーションID 
     * （画面表示なし）
     */
    public String wolfAid;
    
    /**
     * (再生成サービスID)<BR>
     * 再生成サービスID
     * （画面表示なし)
     */
    public String regetServiceId;
    
    /**
     * (SSID)<BR>
     * SSID
     * （画面表示なし）
     */
    public String wolfSsid;
    
    /**
     * (表示区分)<BR>
     * 表示区分
     */
    public String displayDiv;
    
    /**
     * （ＰＲ層保持情報クラス）<BR>
     * コンストラクタ<BR>
     * @@return WEB3AioPrSessionUnit
     * @@roseuid 4158EB620327
     */
    public WEB3AioPrSessionUnit() 
    {

    }
}
@
