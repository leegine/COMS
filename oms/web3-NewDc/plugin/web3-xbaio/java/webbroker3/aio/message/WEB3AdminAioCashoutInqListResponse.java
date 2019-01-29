head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.09.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashoutInqListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金申込問合せ一覧レスポンスクラス(WEB3AdminAioCashoutInqListResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 韋念瓊 (中訊) 新規作成
                   2004/10/27 周勇(中訊) レビュー
                   2004/12/10 周勇 (中訊) 残対応
                   2006/07/31 徐大方 (中訊) 式樣變更 モデル604
                   2006/09/04 車進 (中訊) 式樣變更 モデルNo.629  
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (出金申込問合せ一覧レスポンス)<BR>
 * 出金申込問合せ一覧レスポンスクラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0 
 */

public class WEB3AdminAioCashoutInqListResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_cashout_inq_list";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200410131332L;   
        
    /**
     * (部店コード)<BR>
     * 画面にて入力された部店コード
     */
    public String[] branchCode;
    
    /**
     * (受渡日)<BR>
     * 画面にて入力された受渡日
     */
    public Date deliveryDate;
    
    /**
     * (注文受付区分)<BR>
     * 画面にて選択された注文受付区分<BR>
     * <BR>
     * 0： 受付未済<BR>
     * 1： 受付済<BR>
     * 2： 受付エラー<BR>
     * 3： 全て<BR>
     * <BR>
     */
    public String orderDiv;
    
    //===========remain zhou-yong NO.1 begin ========
    
    /**
     * (振込先区分)<BR>
     * 画面にて選択された振込先区分<BR>
     * <BR>
     * 0： ”全て”<BR> 
     * 1： ”郵貯”<BR> 
     * 2： ”その他”（郵貯以外） 
     */
    public String transferDiv;

    /**
     * (出金申込問合せ明細)<BR>
     *  出金申込問合せ明細
     */
    public WEB3AioCashoutInqUnit[] cashoutInqUnits;
    
    //===========remain zhou-yong NO.1 end ========  

                          
    /**
     * (表示ページ番号)
     */
    public String pageIndex;
    
    /**
     * (総ページ数)
     */
    public String totalPages;
    
    /**
     * (総レコード数)
     */
    public String totalRecords;
    
    /**
     * (管理者処理フラグ)<BR>
     * 画面に出金、取消ボタン、チェックボックスを表示するかどうかの区分<BR>
     * <BR>
     * 0： 一覧表示のみ<BR>
     * 1： 出金実施<BR>
     * 2： 取消実施<BR>
     * 3： 両方実施<BR>
     */
    public String adminProcessingDiv;
    
    /**
     * (入力区分)<BR>
     * <BR>
     * 0：全て<BR>
     * 1：顧客<BR>
     * 2：SONAR<BR>
     */
    public String inputDiv;  

    /**
     * (通貨コード)<BR>
     * 通貨コード
     */
    public String currencyCode;
    
    /**
     * @@roseuid 4158EB6602DD
     */
    public WEB3AdminAioCashoutInqListResponse(WEB3AdminAioCashoutInqListRequest l_request) 
    {
        super(l_request);
    }
}
@
