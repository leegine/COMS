head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.33.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiUploadStateResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者顧客データUL状況照会レスポンス(WEB3AdminSrvRegiUploadStateResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 鄭海良(中訊) 新規作成
*/

package webbroker3.srvregi.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (サービス利用管理者顧客データUL状況照会レスポンス)<BR>
 * サービス利用管理者顧客データUL状況照会レスポンス　@クラス<BR>
 *  
 * @@author 鄭海良
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiUploadStateResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_uploadState";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151639L;
    
    /**
     * (処理状態区分)
     */
    public String uploadState;
    
    /**
     * (処理件数)
     */
    public String endCount;
    
    /**
     * (開始日時)
     */
    public Date startDate;
    
    /**
     * (終了日時)
     */
    public Date endDate;
    
    /**
     * (エラーコード)
     */
    public String errorCode;
    
    /**
     * (サービス利用管理者顧客データUL状況照会レスポンス)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 411AC9AD02AD
     */
    public WEB3AdminSrvRegiUploadStateResponse() 
    {
     
    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminSrvRegiUploadStateResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }    
}
@
