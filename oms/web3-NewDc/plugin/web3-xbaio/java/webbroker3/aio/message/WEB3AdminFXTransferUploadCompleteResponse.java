head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.01.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXTransferUploadCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・FX振替注文アップロード完了レスポンス(WEB3AdminFXTransferUploadCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/28 鄭徳懇(中訊) 新規作成
*/
package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・FX振替注文アップロード完了レスポンス)<BR>
 * 管理者・FX振替注文アップロード完了レスポンスクラス<BR>
 * 
 * @@author 鄭徳懇
 * @@version 1.0
 */
public class WEB3AdminFXTransferUploadCompleteResponse extends WEB3GenResponse 
{

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200602221850L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_fx_transfer_upload_complete";
    
    /**
     * (アップロード件数)<BR>
     * アップロード件数<BR>
     */
    public String uploadNumber;
    
    /**
     * (エラー注文一覧)<BR>
     * エラー注文一覧<BR>
     */
    public WEB3AdminFXErrorOrderUnit[] errorOrderList;
    
    /**
     * @@roseuid 43FC1AE40213
     */
    public WEB3AdminFXTransferUploadCompleteResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト<BR>
     */
    public WEB3AdminFXTransferUploadCompleteResponse(WEB3AdminFXTransferUploadCompleteRequest l_request)
    {
        super(l_request);
    }
}
@
