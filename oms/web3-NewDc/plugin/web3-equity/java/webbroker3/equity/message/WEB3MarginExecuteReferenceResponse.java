head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginExecuteReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  信用取引注文約定照会レスポンスクラス(WEB3MarginExecuteReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/15 李松峰 (中訊) 新規作成
Revesion History : 2004/12/10 桑原 (SRA) 修正
*/
package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 * （信用取引注文約定照会レスポンス）。<br>
 * <br>
 * 信用取引注文約定照会レスポンスクラス
 * @@version 1.0
 */
public class WEB3MarginExecuteReferenceResponse extends WEB3GenResponse 
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_executeReference";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101617L;
    
    
    /**
     * (銘柄一覧)<BR>
     * <BR>
     * 検索条件表示用<BR>
     */
    public WEB3MarginProductCodeNameUnit[] productCodeNames;
    
    /**
     * (市場コード一覧)<BR>
     */
    public String[] marketList;
    
    /**
     * (発注日一覧)<BR>
     * 発注日一覧
     */
    public Date[] orderBizDateList;
      
    
    /**
     * (注文情報一覧)<BR>
     */
    public WEB3MarginExecuteGroup[] executeGroups;
    
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
     * (ID一覧)<BR>
     * 検索条件に該当する全注文ＩＤ<BR>
     * （ソートされた状態）<BR>
     * <BR>
     * ＰＣ版の場合設定<BR>
     */
    public String[] idList;
    
    /**
     * (取引終了警告文言を表示する市場コードの一覧)<BR>
     */
    public String[] messageSuspension;
    
    /**
     * @@roseuid 414048CA02FC
     */
    public WEB3MarginExecuteReferenceResponse() 
    {
     
    }
    
    /**
     * (コンストラクタ。)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3MarginExecuteReferenceResponse(WEB3MarginExecuteReferenceRequest l_request)
    {
        super(l_request);
    }
}
@
