head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.46.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORBondExecRefReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券管理者注文約定照会検索表示レスポンス(WEB3AdminBondExecRefReferenceResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 何文敏(中訊) 新規作成   
*/

package webbroker3.adminorderexecinquiry.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (債券管理者注文約定照会検索表示レスポンス)<BR>
 * 債券管理者注文約定照会検索表示レスポンスクラス<BR>
 *   
 * @@author 何文敏(中訊)
 * @@version 1.0
 */
public class WEB3AdminORBondExecRefReferenceResponse extends WEB3GenResponse
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_or_bond_exec_ref_reference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200608091104L;
    /**
     * (表示ページ番号)<BR>
     * 表示ページ番号
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
     * (債券管理者注文約定照会行一覧)<BR>
     * 債券管理者注文約定照会行一覧
     */
    public WEB3AdminORBondExecRefUnit[] orderList;
    
    /**
     * 債券管理者注文約定照会検索表示レスポンス<BR>
     * コンストラクタ。
     * @@roseuid 44B738A70138
     */
    public  WEB3AdminORBondExecRefReferenceResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminORBondExecRefReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
