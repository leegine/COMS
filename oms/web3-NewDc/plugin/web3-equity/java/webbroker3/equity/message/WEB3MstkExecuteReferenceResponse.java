head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkExecuteReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式ミニ投資注文約定照会レスポンス(WEB3MstkExecuteReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 彭巍(中訊) 新規作成
                   2004/12/10 桑原 (SRA) 修正
                   2005/01/05 岡村 (SRA) JavaDoc修正
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * （株式ミニ投資注文約定照会レスポンス）。<BR>
 * <BR>
 * 株式ミニ投資注文約定照会レスポンスクラス
 * @@author 彭巍
 * @@version 1.0 
 */
public class WEB3MstkExecuteReferenceResponse extends WEB3GenResponse 
{
    
    /**
     * （PTYPE）。
     */
    public final static  String PTYPE = "mstk_executeReference";
        
    /**
     * （SerialVersionUID）。
     */
    public final static long serialVersionUID = 200410101054L;    
    
    /**
     * （銘柄一覧）。
     */
    public WEB3MstkProductCodeNameUnit[] productCodeNames;
    
    /**
     * （注文情報一覧）。<BR>
     * <BR>
     * 検索条件に紐付いた注文約定情報の一覧<BR>
     * （株式ミニ投資注文約定明細の配列）
     */
    public WEB3MstkExecuteGroup[] executeGroups;
    
    /**
     * （総ページ数)<BR>
     * <BR>
     * 該当する全ページ数
     */
    public String totalPages;
    
    /**
     * （総レコード数）。<BR>
     * <BR>
     * 該当する全データ数
     */
    public String totalRecords;
    
    /**
     * （表示ページ番号）。<BR>
     * <BR>
     * 実際に表示するページ位置を指定<BR>
     * ※先頭ページを"1"とする
     */
    public String pageIndex;
    
    /**
     * （取引終了警告）。<BR>
     * <BR>
     * true：警告文を表示する　@　@false：警告文を表示しない
     */
    public boolean messageSuspensionFlag;
    
    /**
     * （株式ミニ投資注文約定照会レスポンス）。<BR>
     * <BR>
     * コンストラクタ
     */
	public WEB3MstkExecuteReferenceResponse() 
	{

	}

    /**
     * （株式ミニ投資注文約定照会レスポンス）。<BR>
     * <BR>
     * コンストラクタ
     * @@param l_request 株式ミニ投資注文約定照会リクエスト
     */
    public WEB3MstkExecuteReferenceResponse(WEB3MstkExecuteReferenceRequest l_request) 
    {
        
        super(l_request);
         
    }
}
@
