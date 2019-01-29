head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.57.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioVirtualAccCashinULConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : バーチャル口座入金UL確認レスポンス(WEB3AdminAioVirtualAccCashinULConfirmResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/09 李小健 (中訊) 新規作成
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (バーチャル口座入金UL確認レスポンス)<BR>
 * バーチャル口座入金UL確認レスポンスクラス<BR>
 * 
 * @@author 李小健(中訊)
 * @@version 1.0 
 */
 
public class WEB3AdminAioVirtualAccCashinULConfirmResponse extends WEB3GenResponse 
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "admin_aio_virtual_acc_cashin_ul_confirm";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200605091454L;

    /**
     * (アップロードID) <BR>
     * アップロードID<BR>
     */
    public String uploadID;
    
    /**
     * (アップロード件数) <BR>
     * アップロード件数<BR>
     */
    public String uploadNumber;
    
    /**
     * (ヘッダーレコード件数) <BR>
     * ヘッダーレコード件数<BR>
     */
    public String headerNumber;

    /**
     * (データレコード件数) <BR>
     * データレコード件数<BR>
     */
    public String dataNumber;
   
    /**
     * (トレーラーレコード件数) <BR>
     * トレーラーレコード件数<BR>
     */  
    public String trailerNumber;
    
    /**
     * (エンドレコード件数) <BR>
     * エンドレコード件数<BR>
     */
    public String endNumber;

    /**
     * (読み飛ばしたレコード件数) <BR>
     * 読み飛ばしたレコード件数<BR>
     */
    public String skipOverNumber;
        
    /**
     * @@roseuid 4158EB64017C
     */
    public WEB3AdminAioVirtualAccCashinULConfirmResponse()
    {
    }
                
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3AdminAioVirtualAccCashinULConfirmResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
          
}
@
