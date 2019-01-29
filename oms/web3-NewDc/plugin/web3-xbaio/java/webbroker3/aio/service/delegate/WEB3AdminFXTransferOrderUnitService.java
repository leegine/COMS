head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.13.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminFXTransferOrderUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FX振替注文UnitService(WEB3AdminFXTransferOrderUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/23 鄭徳懇(中訊) 新規作成
*/
package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.aio.WEB3AdminFXTransferOrderUploadCsv;

/**
 * (FX振替注文UnitService)<BR>
 * FX振替注文UnitServiceインターフェース<BR>
 * 
 * @@author 鄭徳懇
 * @@version 1.0
 */
public interface WEB3AdminFXTransferOrderUnitService extends Service 
{
    
    /**
     * 振替注文処理を行う。 <BR>
     * @@param l_fxTransferOrderUploadCsv - (FX振替注文アップロードCSV)<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@param l_strAdministratorCode - (管理者コード)<BR>
     * @@param l_institution - (証券会社)<BR>
     * @@param l_strPassword - (暗証番号)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 43FBEDC00109
     */
    public WEB3GenResponse execute(
        WEB3AdminFXTransferOrderUploadCsv l_fxTransferOrderUploadCsv,
        int l_intLineNumber, 
        String l_strAdministratorCode,
        Institution l_institution,
        String l_strPassword) throws WEB3BaseException;
}
@
