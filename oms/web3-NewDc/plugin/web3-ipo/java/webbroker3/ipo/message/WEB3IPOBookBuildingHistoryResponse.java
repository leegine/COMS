head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.36.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOBookBuildingHistoryResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  IPOブックビルディング申告履歴レスポンスクラス(WEB3IPOBookBuildingHistoryResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 劉江涛(中訊) 新規作成
*/
package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * IPOブックビルディング申告履歴レスポンスクラス
 * @@author 劉江涛(中訊)
 * @@version 1.0
 */
public class WEB3IPOBookBuildingHistoryResponse extends WEB3GenResponse 
{
    /**
      * PTYPE<BR>
      */
    public static final String PTYPE = "IPO_bookBuildingHistory";

    /**
      * SerialVersionUID<BR>
      */
    public static final long serialVersionUID = 200408101038L;
    /**
     * 銘柄コード
     */
    public String productCode;
    
    /**
     * 銘柄名
     */
    public String productName;
    
    /**
     * ブックビルディング申告履歴一覧
     */
    public WEB3IPODemandHistoryUnit[] bookBuildingHistoryList;
    
    /**
     * @@roseuid 4112E4E1011A
     */
    public WEB3IPOBookBuildingHistoryResponse() 
    {
     
    }
    
    /**
     * (IPOブックビルディング申告履歴レスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。
     * @@param l_request - リクエストオブジェクト
     * @@roseuid 40DC052A00AC
     */
    public WEB3IPOBookBuildingHistoryResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
