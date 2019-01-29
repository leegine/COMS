head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.56.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointUploadInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : アップロード入力レスポンス(WEB3AdminPointUploadInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 鄭海良(中訊) 新規作成
*/
package webbroker3.point.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (アップロード入力レスポンス)<BR>
 * アップロード入力レスポンスクラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminPointUploadInputResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_uploadInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412290011L;
    
    /**
     * (アップロード処理状態区分)<BR>
     * 前回アップロード処理状態区分<BR>
     * <BR>
     * 0：　@アップロード待ち<BR>
     * 1：　@アップロード中<BR>
     * 2：　@アップロード済<BR>
     */
    public String uploadState;
    
    /**
     * (アップロード処理件数)<BR>
     * 前回アップロード処理件数<BR>
     */
    public String uploadCount;
    
    /**
     * (アップロード開始日時)<BR>
     * 前回アップロード開始日時<BR>
     */
    public Date uploadStartDate;
    
    /**
     * (アップロード終了日時)<BR>
     * 前回アップロード終了日時<BR>
     */
    public Date uploadEndDate;
    
    /**
     * (アップロードエラー番号)<BR>
     * 前回アップロードエラー番号<BR>
     * <BR>
     * ※ ErrorMessageテーブルのPK<BR>
     */
    public String uploadErrorNo;
    
    /**
     * @@roseuid 41D1254F01E4
     */
    public WEB3AdminPointUploadInputResponse() 
    {
     
    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminPointUploadInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
