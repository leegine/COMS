head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.51.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdUploadUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞUnitService(WEB3AdminSrvRegiOtherOrgIdUploadUnitService.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/03/10 柴双紅(中訊) 新規作成 モデルNo.337
*/

package webbroker3.srvregi.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;


/**
 * (サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞUnitService)<BR>
 * サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞUnitService　@インターフェイス<BR>
 * <BR>
 * @@author 柴双紅
 * @@version 1.0
 */
public interface WEB3AdminSrvRegiOtherOrgIdUploadUnitService extends Service
{

    /**
     * (insert外部連携情報)<BR>
     * insert外部連携情報処理を行う。<BR>
     * @@param l_lngSequenceNumber - (通番)<BR>
     * 通番<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * サービス区分（必須）<BR>
     * @@param l_strId - (ID)<BR>
     * ID<BR>
     * @@param l_strPassword - (パスワード)<BR>
     * パスワード<BR>
     * @@param l_strStatus - (ステータス)<BR>
     * ステータス<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47C3D6AE024B
     */
    public void insertOtherOrgInfo(
        long l_lngSequenceNumber,
        String l_strSrvDiv,
        String l_strId,
        String l_strPassword,
        String l_strStatus,
        String l_strInstitutionCode) throws WEB3BaseException;

    /**
     * (update外部連携情報)<BR>
     * update外部連携情報処理を行う。<BR>
     * @@param l_lngSequenceNumber - (通番)<BR>
     * 通番<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * サービス区分（必須）<BR>
     * @@param l_strStatus - (ステータス)<BR>
     * ステータス<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47C3D6AE025A
     */
    public void updateOtherOrgInfo(
        long l_lngSequenceNumber,
        String l_strSrvDiv,
        String l_strStatus) throws WEB3BaseException;
}
@
