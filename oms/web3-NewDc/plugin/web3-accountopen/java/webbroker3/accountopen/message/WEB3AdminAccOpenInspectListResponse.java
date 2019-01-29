head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.00.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenInspectListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座開設審査一覧レスポンス(WEB3AdminAccOpenInspectListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/16 張秋穎　@(中訊) 新規作成
*/

package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者口座開設審査一覧レスポンス)<BR>
 * 管理者口座開設審査一覧レスポンス
 * 
 * @@author 張秋穎
 * @@version 1.0
 */
public class WEB3AdminAccOpenInspectListResponse extends WEB3GenResponse 
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_AccOpen_inspectList";
    
    /**
     * SerialVersionUID
     */
    public static final long serialVersionUID = 200606151150L;

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
     * (表示ページ番号)<BR>
     * 表示ページ番号
     */
    public String pageIndex;
    
    /**
     * (口座開設審査情報)<BR>
     */
    public WEB3AccOpenInspectInfo[] accopenInspectList;
    
    /**
     * @@roseuid 44912C1101D4
     */
    public WEB3AdminAccOpenInspectListResponse() 
    {
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminAccOpenInspectListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
