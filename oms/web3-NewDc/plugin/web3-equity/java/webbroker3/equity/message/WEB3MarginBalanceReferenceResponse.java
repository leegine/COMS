head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginBalanceReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引残高照会レスポンス(WEB3MarginBalanceReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 沢村　@仁士(SRA) 新規作成
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * （信用取引残高照会レスポンス）。<BR>
 * <BR>
 * 信用取引残高照会レスポンスクラス<BR>
 */
public class WEB3MarginBalanceReferenceResponse extends WEB3GenResponse 
{

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200502071000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "margin_balance_reference";

    /**
     * (銘柄一覧)<BR>
     * <BR>
     * 現物株式銘柄コード名称の配列<BR>
     */
    public WEB3MarginProductCodeNameUnit[] productCodeNames;
    
    /**
     * (市場コード一覧)<BR>
     * <BR>
     * 市場コードの配列<BR>
     */
    public String[] marketList;
    
    /**
     * (残高照会明細)<BR>
     * <BR>
     * 現物株式残高照会明細の配列<BR>
     */
    public WEB3MarginBalanceReferenceDetailUnit[] balanceReference;
    
    /**
     * (表示ページ番号)<BR>
     * <BR>
     * 実際に表示するページ位置を指定<BR>
     * ※先頭ページを"1"とする<BR>
     */
    public String pageIndex = "0";

    /**
     * (総ページ数)<BR>
     */
    public String totalPages = "0";

    /**
     * (総レコード数)<BR>
     */
    public String totalRecords = "0";
    
    /**
     * @@roseuid 4206CDBB02BB<BR>
     */
    public WEB3MarginBalanceReferenceResponse() 
    {
     
    }
    
    public WEB3MarginBalanceReferenceResponse(WEB3MarginBalanceReferenceRequest l_request)
    {
        super(l_request);
    }
}
@
