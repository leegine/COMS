head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.10.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashoutInqConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金申込問合せ出金確認レスポンスクラス(WEB3AdminAioCashoutInqConfirmResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 韋念瓊 (中訊) 新規作成
                   2004/10/27 周勇(中訊) レビュー
*/

package webbroker3.aio.message;


/**
 * (出金申込問合せ出金確認レスポンス)<BR>
 * 出金申込問合せ出金確認レスポンスクラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0 
 */

public class WEB3AdminAioCashoutInqConfirmResponse extends WEB3AdminAioCashoutInqConfirmCommonResponse 
{

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_cashout_inq_confirm";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200409291950L; 
        
    /**
     * @@roseuid 4158EB65032C
     */
    public WEB3AdminAioCashoutInqConfirmResponse(WEB3AdminAioCashoutInqConfirmRequest l_request) 
    {
        super(l_request);
    }
}
@
