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
filename	WEB3MarginContractReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引建株照会レスポンス(WEB3MarginContractReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/16 凌建平 (中訊) 新規作成
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * （信用取引建株照会レスポンス）。<br>
 * <br>
 * 信用取引建株照会レスポンスクラス
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3MarginContractReferenceResponse extends WEB3GenResponse 
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_contractReference";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101802L;      
    /**
     * (銘柄一覧)<BR>
     * 信用取引銘柄コード名称の一覧
     */
    public WEB3MarginProductCodeNameUnit[] productCodeNames;
    
    /**
     * (市場コードの一覧)
     */
    public String[] marketList;
    
    /**
     * (総ページ数)
     */
    public String totalPages;
    
    /**
     * (総レコード数)
     */
    public String totalRecords;
    
    /**
     * (表示ページ番号)<BR>
     * 実際に表示するページ位置を指定<BR>
     * ※先頭ページを"1"とする<BR>
     */
    public String pageIndex;
    
    /**
     * (建株照会明細一覧)<BR>
     * 信用取引建株照会明細の一覧
     */
    public WEB3MarginContractReferenceUnit[] contractReferenceUnits;
    
    /**
     * @@roseuid 41403F6F02F8
     */
    public WEB3MarginContractReferenceResponse() 
    {
     
    }
    
    /**
     * (コンストラクタ。)
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3MarginContractReferenceResponse(WEB3MarginContractReferenceRequest l_request)
    {
        super(l_request);
    }
}
@
