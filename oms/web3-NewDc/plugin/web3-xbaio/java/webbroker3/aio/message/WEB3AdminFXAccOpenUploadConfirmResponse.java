head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.55.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccOpenUploadConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・FX口座開設アップロード確認レスポンス(WEB3AdminFXAccOpenUploadConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/18 鄭徳懇(中訊) 新規作成
                 : 2006/03/09 情野（SRA） エラー口座一覧のシンボル名変更対応
Revesion History : 2008/09/12 劉仁和 (中訊) 仕様変更・モデル987,988
*/
package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・FX口座開設アップロード確認レスポンス)<BR>
 * 管理者・FX口座開設アップロード確認レスポンスクラス<BR>
 * 
 * @@author 鄭徳懇
 * @@version 1.0
 */
public class WEB3AdminFXAccOpenUploadConfirmResponse extends WEB3GenResponse 
{
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200602181050L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_fx_acc_open_upload_confirm";
    
    /**
     * (アップロード件数)<BR>
     * アップロード件数<BR>
     */
    public String uploadNumber;
    
    /**
     * (アップロードID)<BR>
     * アップロードID<BR>
     */
    public String uploadId;
    
    /**
     * (重複口座一覧)<BR>
     * 重複口座一覧<BR>
     * <BR>
     * ※重複登録しようとした利用者コードの配列<BR>
     */
    public String[] duplicateAccountList;
    
    /**
     * (エラー口座一覧)<BR>
     * エラー口座一覧<BR>
     * <BR>
     * ※登録エラーとなった利用者コードの配列<BR>
     */
    public String[] errorAccountList;
    
    /**
     * @@roseuid 43F49A700119
     */
    public WEB3AdminFXAccOpenUploadConfirmResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト<BR>
     */
    public WEB3AdminFXAccOpenUploadConfirmResponse(WEB3AdminFXAccOpenUploadConfirmRequest l_request)
    {
        super(l_request);
    }
}
@
