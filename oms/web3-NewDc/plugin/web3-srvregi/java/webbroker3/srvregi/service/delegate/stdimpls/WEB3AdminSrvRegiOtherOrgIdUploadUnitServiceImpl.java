head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.48.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdUploadUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞUnitServiceImpl(WEB3AdminSrvRegiOtherOrgIdUploadUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/10 武波 (中訊) 新規作成 モデル337
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.srvregi.WEB3SrvRegiOtherOrgInfoAdmin;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiOtherOrgIdUploadUnitService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiOtherOrgService;
import webbroker3.util.WEB3LogUtility;

/**
 * (サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞUnitServiceImpl)<BR>
 * サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞUnitService　@実装クラス<BR>
 * <BR>
 * Plugin時に自動トランザクションTransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW)を指定する。<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AdminSrvRegiOtherOrgIdUploadUnitServiceImpl implements WEB3AdminSrvRegiOtherOrgIdUploadUnitService
{

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiOtherOrgIdUploadUnitServiceImpl.class);

    /**
     * @@roseuid 47D1112F036D
     */
    public WEB3AdminSrvRegiOtherOrgIdUploadUnitServiceImpl()
    {

    }

    /**
     * (insert外部連携情報)<BR>
     * 外部連携情報管理テーブルにinsert処理を行う。<BR>
     * <BR>
     * <BR>
     * シーケンス図「（サービス利用）外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ・insert外部連携情報」参照<BR>
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
     * @@roseuid 47C3D65B01F9
     */
    public void insertOtherOrgInfo(
        long l_lngSequenceNumber,
        String l_strSrvDiv,
        String l_strId,
        String l_strPassword,
        String l_strStatus,
        String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "insertOtherOrgInfo(long, String, String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //createNew外部連携情報管理(long, String)
        //通番：  引数.通番
        //サービス区分：  引数.サービス区分
        WEB3SrvRegiOtherOrgInfoAdmin l_srvRegiOtherOrgInfoAdmin =
            WEB3SrvRegiOtherOrgInfoAdmin.createNewOtherOrgInfoAdmin(l_lngSequenceNumber, l_strSrvDiv);

        //setID(String)
        l_srvRegiOtherOrgInfoAdmin.setId(l_strId);

        //setパスワード(String)
        //パスワード：  引数.パスワード
        l_srvRegiOtherOrgInfoAdmin.setPassword(l_strPassword);

        //setステータス(String)
        //ステータス：  引数.ステータス
        l_srvRegiOtherOrgInfoAdmin.setStatus(l_strStatus);

        //set証券会社コード(String)
        //証券会社コード：  引数.証券会社コード
        l_srvRegiOtherOrgInfoAdmin.setInstitutionCode(l_strInstitutionCode);

        //saveNew外部連携情報管理( )
        //更新内容は下記を参照。
        //【ｘTrade】補足資料.DB更新
        //「サービス利用管理者・外部連携ID照会アップロード_外部連携情報管理テーブル仕様.xls」
        //「ID登録」シート
        l_srvRegiOtherOrgInfoAdmin.saveNewOtherOrgInfoAdmin();

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (update外部連携情報)<BR>
     * 外部連携情報管理テーブルにupdate処理を行う。<BR>
     * <BR>
     * <BR>
     * シーケンス図「（サービス利用）外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ・update外部連携情報」参照<BR>
     * <BR>
     * ========================================================<BR>
     * シーケンス図 （サービス利用）外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ・update外部連携情報: <BR>
     * 　@　@　@　@　@1.1 get外部連携情報(long, String, boolean)<BR>
     * 　@　@　@　@　@　@　@　@返却値がnullの場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_03019<BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_lngSequenceNumber - (通番)<BR>
     * 通番<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * サービス区分（必須）<BR>
     * @@param l_strStatus - (ステータス)<BR>
     * ステータス<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47C3D65B0209
     */
    public void updateOtherOrgInfo(
        long l_lngSequenceNumber,
        String l_strSrvDiv,
        String l_strStatus) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateOtherOrgInfo(long, String, String)";
        log.entering(STR_METHOD_NAME);

        //get外部連携情報(long, String, boolean)
        //通番：  引数.通番
        //サービス区分：  引数.サービス区分
        //is行ロック：  true
        WEB3SrvRegiOtherOrgService l_srvRegiOtherOrgService =
            (WEB3SrvRegiOtherOrgService)Services.getService(WEB3SrvRegiOtherOrgService.class);
        WEB3SrvRegiOtherOrgInfoAdmin l_srvRegiOtherOrgInfoAdmin =
            l_srvRegiOtherOrgService.getOtherOrgInfo(l_lngSequenceNumber, l_strSrvDiv, true);

        if (l_srvRegiOtherOrgInfoAdmin == null)
        {
            log.debug("外部連携情報を取得できません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03019,
                getClass().getName() + "." + STR_METHOD_NAME,
                "外部連携情報を取得できません。");
        }

        // isステータス変更可能(String)
        //ステータス：  引数.ステータス
        boolean l_blnIsStatusChangeable = l_srvRegiOtherOrgInfoAdmin.isStatusChangeable(l_strStatus);

        //(*1) isステータス変更可能()=true の場合、更新（Update）処理を実施
        if (l_blnIsStatusChangeable)
        {
            //setステータス(String)
            //ステータス：  引数.ステータス
            l_srvRegiOtherOrgInfoAdmin.setStatus(l_strStatus);

            //save外部連携情報管理( )
            //更新内容は下記を参照。
            //【ｘTrade】補足資料.DB更新
            //「サービス利用管理者・外部連携ID照会アップロード_外部連携情報管理テーブル仕様.xls」
            //「ステータス変更」シート
            l_srvRegiOtherOrgInfoAdmin.saveOtherOrgInfoAdmin();
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
