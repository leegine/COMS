head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.43.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORFeqOrderExecutionRefReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・外国株式注文約定照会レスポンス(WEB3AdminORFeqOrderExecutionRefReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/22 鄭海良(中訊) 新規作成
                 : 2005/08/02 郭英(中訊) レビュー
*/

package webbroker3.adminorderexecinquiry.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・外国株式注文約定照会レスポンス)<BR>
 * 管理者・外国株式注文約定照会レスポンスクラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminORFeqOrderExecutionRefReferenceResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_Feq_ORFeqOrderExecutionRefReference";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200507130937L;
    
    /**
     * (出来終了／注文繰越処理区分)<BR>
     * 出来終了／注文繰越処理区分<BR>
     * <BR>
     * 0：　@出来終了済<BR>
     * 1：　@注文繰越処理済<BR>
     * 2：　@注文繰越処理中<BR>
     * 9：　@注文繰越処理エラー<BR>
     */
    public String carryoverEndType = null;
    
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
     * (詳細画面情報一覧)<BR>
     */
    public WEB3AdminORDetailDispInfoUnit detailDispInfoList[];
    
    /**
     * (管理者外国株式注文約定照会Unit一覧)<BR>
     */
    public WEB3AdminORFeqOrderExecutionRefUnit feqOrderExecuteList[];
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminORFeqOrderExecutionRefReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
}
@
