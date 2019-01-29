head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.07.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoAccountConnectionResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 顧客連絡レスポンス(WEB3PvInfoAccountConnectionResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
Revesion History : 2004/10/20 李弘毅(中訊) 作成
*/
package webbroker3.pvinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (顧客連絡レスポンス)<BR>
 * 顧客連絡レスポンスクラス<BR>
 * @@author 王亞洲
 * @@version 1.00
 */
public class WEB3PvInfoAccountConnectionResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "PvInfo_accountConnection";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410181349L;
    
    /**
     * (顧客名)<BR>
     * 顧客名<BR>
     */
    public String accountName;
    
    /**
     * (総ページ数)<BR>
     * 総ページ数<BR>
     */
    public String totalPages;
    
    /**
     * (総レコード数)<BR>
     * 総レコード数<BR>
     */
    public String totalRecords;
    
    /**
     * (表示ページ番号)<BR>
     * 表示ページ番号<BR>
     */
    public String pageIndex;
    
    /**
     * (表示メッセージ情報一覧)<BR>
     * 表示メッセージ情報一覧<BR>
     */
    public WEB3PvInfoDisplayMessageUnit[] displayMessageUnits;
    
    /**
     * デフォルトコンストラクタ 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     * <BR>
     * @@param l_request リクエストオブジェクト
     */    
    public WEB3PvInfoAccountConnectionResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
