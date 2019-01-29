head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.45.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenFinInstitutionSearchService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設金融機@関検索サービス(WEB3AccOpenFinInstitutionSearchService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 張学剛 (中訊) 新規作成
*/
package webbroker3.accountopen.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (口座開設金融機@関検索サービス)<BR>
 * 口座開設金融機@関検索サービスインタフェイス<BR>
 * @@author 張学剛
 * @@version 1.0 
 */
public interface WEB3AccOpenFinInstitutionSearchService extends WEB3BusinessService 
{
    
    /**
     * 金融機@関検索処理を実施する。
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41B165350232
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
