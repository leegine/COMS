head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.09.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXAccOpenInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FX口座開設入力レスポンス(WEB3FXAccOpenInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/19 屈陽 (中訊) 新規作成
Revesion History : 2008/09/22 武波 (中訊) 仕様変更・モデル1013
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (FX口座開設入力レスポンス) <BR>
 * FX口座開設入力レスポンスクラス <BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3FXAccOpenInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "fx_acc_open_input";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171454L;

    /**
     * (メールアドレス) <BR>
     * WEB3取引用に登録済のメールアドレス <BR>
     */
    public String mailAddress;

    /**
     * (識別コード) <BR>
     * 電子鳩から閲覧履歴なしと判断された識別コード。 <BR>
     */
    public String[] requestCode;

    /**
     * (GFT口座開設フラグ)<BR>
     * GFT口座開設フラグ<BR>
     */
    public boolean gftAccOpenFlag;

    /**
     * @@roseuid 41E783E3032C
     */
    public WEB3FXAccOpenInputResponse()
    {
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3FXAccOpenInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}@
