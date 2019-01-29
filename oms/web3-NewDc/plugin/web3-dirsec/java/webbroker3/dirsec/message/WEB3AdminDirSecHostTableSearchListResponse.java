head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.11.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecHostTableSearchListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  管理者・キューテーブル検索結果レスポンスクラス(WEB3AdminDirSecHostTableSearchListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/30  肖志偉(中訊) 新規作成
*/

package webbroker3.dirsec.message;

import webbroker3.common.message.WEB3GenRequest;


/**
 * (管理者・キューテーブル検索結果レスポンス)
 * 
 * @@author 肖志偉(中訊)
 * @@version 1.0
 */
public class WEB3AdminDirSecHostTableSearchListResponse extends WEB3AdminDirSecHostTableUpdateCommonResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_dirsec_host_table_search_list";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603291625L;
    
    /**
     * (総ページ数)<BR>
     * 総ページ数。
     */
    public String totalPages;
    
    /**
     * (総レコード数)<BR>
     * 総レコード数。
     */
    public String totalRecords;
    
    /**
     * (表示ページ番号)<BR>
     * 表示ページ番号。
     */
    public String pageIndex;
    
    /**
     * (キューテーブルレコード詳細)
     */
    public WEB3AdminDirSecHostTableDetail[] hostTableDetails;
    
    /**
     * @@roseuid 442A1C8701D4
     */
    public WEB3AdminDirSecHostTableSearchListResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminDirSecHostTableSearchListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
}
@
