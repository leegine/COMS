head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.19.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCampaignIndiviListService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 個別顧客指定一覧サービス (WEB3AdminAccInfoCampaignIndiviListService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/01 李木子 (中訊) 新規作成
*/
package webbroker3.accountinfo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (個別顧客指定一覧サービス)<BR>
 * 個別顧客指定一覧サービス<BR>
 * <BR>
 * @@author 李木子<BR>
 * @@version 1.0<BR>
 */
public interface WEB3AdminAccInfoCampaignIndiviListService extends WEB3BusinessService 
{
    
    /**
     * 個別顧客指定指定一覧表示処理を実施する。<BR>
     * <BR>
     * 　@－get一覧画面()をコールする。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 45B0705A0158
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
