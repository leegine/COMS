head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.07.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyApplyConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信定時定額買付新規申込確認レスポンス(WEB3MutualFixedBuyApplyConfirmResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/26 徐宏偉 (中訊) 新規作成
Revesion History : 2007/10/25 孫洪江 (中訊) 仕様変更 モデルNo.585
*/
package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (投信定時定額買付新規申込確認レスポンス)<BR>
 * 投信定時定額買付新規申込確認レスポンス<BR>
 * 
 * @@author 徐宏偉(中訊)
 * @@version 1.0 
 */
public class WEB3MutualFixedBuyApplyConfirmResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mutual_fixed_buy_apply_confirm";
    
    /**
     * SerialVersionUID<BR>
     */   
    public final static long serialVersionUID = 200606261700L;
 
    /**
     * (外国証券口座開設要求フラグ)<BR>
     * 外国証券口座開設要求フラグ<BR>
     * <BR>
     * true:開設の必要あり <BR>
     * false:開設の必要なし<BR>
     */
    public boolean foreignSecAccOpenFlag;

    /**
     * (電子鳩チェック結果)<BR>
     * 電子鳩チェック結果<BR>
     * <BR>
     * 0：閲覧済　@1：閲覧未済　@2：閲覧未済(障害中)<BR>
     */
    public String batoCheckResult;

    /**
     * (目論見書閲覧未済銘柄コード一覧)<BR>
     * 目論見書閲覧未済銘柄コード一覧<BR>
     */
    public String[] noReadProductCodeList;

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3MutualFixedBuyApplyConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
   
    /**
     * (投信定時定額買付新規申込確認レスポンス)<BR>
     * デフォルトコンストラクタ<BR>
     */
    public WEB3MutualFixedBuyApplyConfirmResponse() 
    {     
    }    
}
@
