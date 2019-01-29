head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.54.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformHostReqOrderNumberManageService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 連絡管理識別コード採番サービス(WEB3InformHostReqOrderNumberManageService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/24 艾興 (中訊) 新規作成
*/
package webbroker3.inform.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;


/**
 * (連絡管理識別コード採番サービス)<BR>
 * 連絡管理識別コード採番サービスインターフェイス
 */
public interface WEB3InformHostReqOrderNumberManageService extends Service 
{
    
    /**
     * (get新規識別コード)<BR>
     * 各種連絡の識別コードを自動採番する。
     * @@param l_strInstitutionCode - (証券会社コード)
     * 証券会社コード
     * 
     * @@param l_strInformDiv - (連絡種別)
     * 連絡種別
     * 
     * @@return String
     * @@roseuid 41BD561400A1
     */
    public String getNewOrderRequestCode(String l_strInstitutionCode, String l_strInformDiv) throws WEB3BaseException;
}
@
