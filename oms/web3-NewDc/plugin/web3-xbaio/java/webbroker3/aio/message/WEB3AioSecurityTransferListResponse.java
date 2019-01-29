head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.14.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecurityTransferListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券振替一覧レスポンス(WEB3AioSecurityTransferListResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/07 屈陽 (中訊) 新規作成   
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (証券振替一覧レスポンス)<BR>
 * 証券振替一覧レスポンスクラス
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AioSecurityTransferListResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_security_transfer_list";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412070931L; 
    
    /**
     * (預り証券一覧)<BR>
     * 預り証券明細の配列
     */
    public WEB3AioSecurityTransferUnit[] securityTransfer;
    
    /**
     * (表示ページ番号)<BR>
     * 実際に表示するページ番号
     */
    public String pageIndex;
    
    /**
     * (総ページ数)<BR>
     * 総ページ数
     */
    public String totalPages;
    
    /**
     * (総レコード数)<BR>
     * 総レコード数
     */
    public String totalRecords;
    
    /**
     * (銘柄一覧)<BR>
     * 検索用条件のプルダウンメニューに使用する保有銘柄明細の配列
     */
    public WEB3AioSecurityTransferProductCodeNameUnit[] productCodeNames;
    
    /**
     * @@roseuid 41B031A100CB
     */
    public WEB3AioSecurityTransferListResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AioSecurityTransferListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }   
}
@
