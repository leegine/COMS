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
filename	WEB3MstkBalanceReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式ミニ投資残高照会レスポンス(WEB3MstkBalanceReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 沢村　@仁士(SRA) 新規作成
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * （株式ミニ投資残高照会レスポンス）。<BR>
 * <BR>
 * 株式ミニ投資残高照会レスポンスクラス<BR>
 */
public class WEB3MstkBalanceReferenceResponse extends WEB3GenResponse 
{
        
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200502071000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mstk_balance_reference";

    /**
     * (銘柄一覧)<BR>
     * <BR>
     * 銘柄一覧<BR>
     */
    public WEB3MstkProductCodeNameUnit[] productCodeNames;
    
    /**
     * (残高照会明細)<BR>
     *<BR>
     * 検索条件に紐付いた残高情報の一覧<BR>
     * （株式ミニ投資残高照会明細の配列）<BR>
     */
    public WEB3MstkBalanceReferenceDetailUnit[] mstkBalanceReferenceDetail;
    
    /**
     * (総ページ数)<BR>
     * <BR>
     * 該当する全ページ数<BR>
     */
    public String totalPages;
    
    /**
     * (総レコード数)<BR>
     * <BR>
     * 該当する全データ数<BR>
     */
    public String totalRecords;
    
    /**
     * (表示ページ番号)<BR>
     * <BR>
     * 実際に表示するページ位置を指定<BR>
     * ※先頭ページを"1"とする<BR>
     */
    public String pageIndex;
    
    /**
     * @@roseuid 4206CC990396<BR>
     */
    public WEB3MstkBalanceReferenceResponse() 
    {
     
    }
    
    public WEB3MstkBalanceReferenceResponse(WEB3MstkBalanceReferenceRequest l_request)
    {
        super(l_request);
    }
}
@
