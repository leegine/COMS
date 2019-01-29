head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.12.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashoutInqCompleteCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金申込問合せ完了共通レスポンスクラス(WEB3AdminAioCashoutInqCompleteCommonResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 韋念瓊 (中訊) 新規作成
                   2004/10/27 周勇(中訊) レビュー
                   2004/12/10 周勇 (中訊) 残対応
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (出金申込問合せ完了共通レスポンス)<BR>
 * 出金申込問合せ完了共通レスポンスクラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0 
 */

public class WEB3AdminAioCashoutInqCompleteCommonResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_cashout_inq_complete_common";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200410131338L;
        
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
    
    //===========remain zhou-yong NO.1 end ========  
    
    /**
     * @@roseuid 4158EB6403D5
     */
    public WEB3AdminAioCashoutInqCompleteCommonResponse(WEB3AdminAioCashoutInqCommonRequest l_request) 
    {
        super(l_request);
    }
    
    
}
@
