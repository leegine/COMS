head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.34.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoUploadActionUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPOアップロード履歴明細作成サービス(WEB3IpoUploadActionUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 張威 (中訊) 新規作成
*/

package webbroker3.ipo.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.data.AdministratorUploadParams;
import webbroker3.ipo.message.WEB3IPOUploadHistoryUnit;

/**
 * (IPOアップロード履歴明細作成サービス)<BR>
 * IPOアップロード履歴明細作成サービスインタフェイス
 *                                                                
 * @@author 張威
 * @@version 1.0
 */
public interface WEB3IpoUploadActionUnitService extends Service 
{
    
    /**
     * (createアップロード履歴明細)<BR>
     * （管理者共通）アップロード行オブジェクトより、<BR>
     * IPOアップロード履歴明細オブジェクトを作成する。
     * @@throws WEB3BaseException
     * @@param l_administratorUploadParams - (アップロード履歴行)<BR>
     * アップロード履歴行オブジェクト<BR>
     * <BR>
     * ※　@（管理者共通）アップロードParamsクラスはDDLにて自動生成
     * @@return webbroker3.ipo.message.WEB3IpoUploadActionUnit
     * @@roseuid 40F227D40214
     */
    public WEB3IPOUploadHistoryUnit createUploadActionUnit(AdministratorUploadParams l_administratorUploadParams) throws WEB3BaseException;
}
@
