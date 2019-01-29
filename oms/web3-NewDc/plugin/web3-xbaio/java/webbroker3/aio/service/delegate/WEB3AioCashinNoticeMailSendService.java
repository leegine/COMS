head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.18.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCashinNoticeMailSendService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金連絡メール送信サービスインターフェイス(WEB3AioCashinNoticeMailSendService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 周勇 (中訊) 新規作成
                   2004/10/22 黄建 (中訊) レビュー    
*/
package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.aio.data.DepositInformParams;
import webbroker3.common.WEB3BaseException;


/**
 * (入金連絡メール送信サービス)<BR>
 * 入金連絡メール送信サービスインターフェイス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0 
 */
public interface WEB3AioCashinNoticeMailSendService extends Service 
{
    
    /**
     * (createメール)<BR>
     * 入金連絡メール行をDBに登録する。
     * @@param l_depositInformParams - (入金連絡行)<BR>
     * 入金連絡行オブジェクト
     * @@roseuid 40FE5E9303DF
     */
    public void createMail(DepositInformParams l_depositInformParams, String l_strEmailAddress)
        throws WEB3BaseException;;
}@
