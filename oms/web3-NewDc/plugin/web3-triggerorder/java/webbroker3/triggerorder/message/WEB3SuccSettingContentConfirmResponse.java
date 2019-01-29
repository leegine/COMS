head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.46.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccSettingContentConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 設定内容確認レスポンス(WEB3SuccSettingContentConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/12 鄭海良(中訊) 新規作成
*/

package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (設定内容確認レスポンス)<BR>
 * 設定内容確認レスポンスクラス<BR>
 *   
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3SuccSettingContentConfirmResponse extends WEB3GenResponse 
{
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200510111000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_settingContentConfirm";
    
    /**
     * (注文明細)<BR>
     * 連続注文設定対象の明細<BR>
     */
    public WEB3SuccOrderUnit orderUnit;
    
    /**
     * @@roseuid 434896040251
     */
    public WEB3SuccSettingContentConfirmResponse() 
    {
     
    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3SuccSettingContentConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }     
}
@
