head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.45.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenVoucherRegAcceptUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設伝票登録受付UnitService(WEB3AccOpenVoucherRegAcceptUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/16 鄭海良(中訊) 新規作成
*/

package webbroker3.accountopen.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.accountopen.data.HostAccOpenAcceptParams;
import webbroker3.common.WEB3BaseException;

/**
 * (口座開設伝票登録受付UnitService)<BR>
 * 口座開設伝票登録受付１件サービスインタフェイス<BR>
 *   
 * @@author 鄭海良
 * @@version 1.0
 */
public interface WEB3AccOpenVoucherRegAcceptUnitService extends Service 
{
    
    /**
     * (notify伝票登録受付)<BR>
     * 口座開設伝票登録受付１件処理を実施し、処理結果（処理済／エラー）を<BR>
     * 返却する。<BR>
     * @@param l_accOpenAcceptParams - 口座開設伝票登録受付キュー<BR>
     * <BR>
     * ※　@口座開設伝票登録受付キューParamsクラスは、DDLより自動生成する。<BR>
     * 
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 41B169B402DE
     */
    public String notifyVoucherRegAccept(HostAccOpenAcceptParams l_accOpenAcceptParams) throws WEB3BaseException;
}
@
