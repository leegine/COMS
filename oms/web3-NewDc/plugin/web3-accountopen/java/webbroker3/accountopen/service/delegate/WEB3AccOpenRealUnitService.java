head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.45.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenRealUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 即日口座開設UnitService(WEB3AccOpenRealUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/11/27 何文敏(中訊) 新規作成 仕様変更 モデル No.113
*/
package webbroker3.accountopen.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.accountopen.data.HostAccOpenAcceptParams;
import webbroker3.common.WEB3BaseException;

/**
 * (即日口座開設UnitService)<BR>
 * 即日口座開設UnitService１件サービスインタフェイス<BR>
 * <BR>
 * @@author 何文敏
 * @@version 1.0
 */
public interface WEB3AccOpenRealUnitService extends Service
{   
    /**
     * 即日口座開設１件処理を実施する。<BR>
     * <BR>
     * @@param l_accOpenAcceptParams - 口座開設伝票登録受付キュー
     */
    public String process(
        HostAccOpenAcceptParams l_accOpenAcceptParams) throws WEB3BaseException;
}
@
