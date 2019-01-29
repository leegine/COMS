head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.07.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3AdminPvInfoConditionListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・表示設定一覧レスポンス(WEB3AdminPvInfoConditionListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
Revesion History : 2004/10/25 李丁銀(中訊) 作成
Revesion History : 2005/12/8 譚漢江(中訊) 仕様変更No.059修正
*/
package webbroker3.pvinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者・表示設定一覧レスポンス)<BR>
 * 管理者・表示設定一覧レスポンスクラス<BR>
 * @@author 王亞洲
 * @@version 1.00
 */
public class WEB3AdminPvInfoConditionListResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_PvInfo_conditionList";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410181349L;
    
    /**
     * (表示条件情報一覧)<BR>
     */
    public WEB3PvInfoDisplayConditionUnit[] displayConditionUnits;
    
    /**
     * (表示内容情報一覧)<BR>
     */
    public WEB3PvInfoDisplayContentsUnit[] displayContentsUnits;
    
    /**
     * (総ページ数)<BR>
     */
    public String totalPages;

    /**
     * (総レコード数)<BR>
     */
    public String totalRecords;

    /**
     * (表示ページ番号)<BR>
     */
    public String pageIndex;

    /**
     * デフォルトコンストラクタ 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     * <BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3AdminPvInfoConditionListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
