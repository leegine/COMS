head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.17.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioInputOutputHistoryListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出庫履歴レスポンス(WEB3AioInputOutputHistoryListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/18 艾興 (中訊) 新規作成
*/
package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (入出庫履歴レスポンス)<BR>
 * 入出庫履歴レスポンスクラス
 * @@author 艾興
 * @@version 1.0
 */
public class WEB3AioInputOutputHistoryListResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_inputOutputHistoryList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200501271304L;

    /**
     * (表示期間（自）)<BR>
     * 表示期間（自）
     */
    public Date displayStartDate;
    
    /**
     * (表示期間（至）)<BR>
     * 表示期間（至）
     */
    public Date displayEndDate;
    
    /**
     * (入出庫履歴一覧)<BR>
     * 入出庫履歴明細の配列
     */
    public WEB3AioHistoryUnit[] HistoryUnits;
    
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
     * @@roseuid 41EC84F8031C
     */
    public WEB3AioInputOutputHistoryListResponse() 
    {
     
    }
   
    /**
     * @@roseuid 41EC84F8031C
     */
    public WEB3AioInputOutputHistoryListResponse(WEB3GenRequest l_request) 
    {
        super(l_request);        
     
    } 
    
}
@
