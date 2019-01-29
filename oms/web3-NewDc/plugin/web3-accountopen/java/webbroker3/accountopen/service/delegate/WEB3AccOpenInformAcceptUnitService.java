head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.44.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenInformAcceptUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 各種連絡受付UnitService(WEB3AccOpenInformAcceptUnitService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/05/28 柴双紅 (中訊) 新規作成 モデル No.123, No.127, No.135
Revision History : 2007/06/12 柴双紅 (中訊) モデル No.141
*/

package webbroker3.accountopen.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.accountopen.data.HostAccOpenAcceptParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.inform.data.VariousInformParams;

/**
 * (各種連絡受付UnitService)<BR>
 * 各種連絡受付１件サービスインタフェイス<BR>
 * <BR>
 * @@author 柴双紅
 * @@version 1.0
 */
public interface WEB3AccOpenInformAcceptUnitService extends Service
{
    /**
     * (notify各種連絡受付)<BR>
     * 各種連絡受付1件処理を実施する。<BR>
     * <BR>
     * @@param l_hostAccOpenAcceptParams - (受付キューParams)<BR>
     * 口座開設伝票登録受付キューParams<BR>
     * @@param l_variousInformParams - (各種連絡Params)<BR>
     * 各種連絡Params<BR>
     * @@param l_strProcessDiv - (処理区分)<BR>
     * 処理区分<BR>
     * <BR>
     * 1： 処理済<BR>
     * 9： エラー<BR>
     * @@throws WEB3BaseException
     */
    public void notifyInformAccept(
        HostAccOpenAcceptParams l_hostAccOpenAcceptParams,
        VariousInformParams l_variousInformParams,
        String l_strProcessDiv) throws WEB3BaseException;
}
@
