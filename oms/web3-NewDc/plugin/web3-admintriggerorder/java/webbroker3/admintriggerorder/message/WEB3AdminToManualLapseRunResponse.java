head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToManualLapseRunResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・手動失効処理起動レスポンス(WEB3AdminToManualLapseRunResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/20　@余新敏(中訊) 新規作成
*/

package webbroker3.admintriggerorder.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (トリガー注文管理者・手動失効処理起動レスポンス)<BR>
 * トリガー注文管理者・手動失効処理起動レスポンス<BR>
 * 
 * @@author 余新敏
 * @@version 1.0
 */
public class WEB3AdminToManualLapseRunResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_to_manual_lapse_run";
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603161700L;

    /**
     * (現在時刻)<BR>
     * 現在時刻<BR>
     */
    public Date currentTime;
    
    /**
     * @@roseuid 44192EEC0138
     */
    public WEB3AdminToManualLapseRunResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminToManualLapseRunResponse(WEB3AdminToManualLapseRunRequest l_request)
    {
        super(l_request);
    } 
}
@
