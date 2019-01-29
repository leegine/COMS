head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.22.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminAioSLProductRegistControlService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 担保銘柄登録制御サービス(WEB3AdminSLProductRegistControlService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/14 趙林鵬(中訊) 新規作成 モデルNo.760
Revision History : 2007/10/26 金傑(中訊)　@仕様変更モデルNo.816
*/

package webbroker3.aio;

import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.aio.data.SecurityProductRow;
import webbroker3.aio.message.WEB3SLProductInfoUnit;
import webbroker3.aio.message.WEB3SLProductSearchConditions;
import webbroker3.common.WEB3BaseException;

/**
 * (担保銘柄登録制御サービス)<BR>
 * 担保銘柄登録制御サービスインタフェイス<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */

public interface WEB3AdminAioSLProductRegistControlService extends Service
{
    /**
     * (insert担保銘柄情報)<BR>
     * 担保銘柄テーブルに担保銘柄情報をinsrtする。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_stockLoanProductInfo - (銘柄登録情報)<BR>
     * 担保銘柄登録情報オブジェクト<BR>
     * @@param l_strAdministratorCode - (管理者コード)<BR>
     * 管理者コード<BR>
     * @@throws WEB3BaseException
     * @@roseuid 46D7C5FC016C
     */
    public void insertSecurityProductInfo(
        String l_strInstitutionCode,
        WEB3SLProductInfoUnit l_stockLoanProductInfo,
        String l_strAdministratorCode) throws WEB3BaseException;

    /**
     * (validate担保銘柄同一期間)<BR>
     * 同一銘柄で、かつ同一期間内の銘柄が存在するかチェックを行う。<BR>
     * 存在する場合、例外をスローする。<BR>
     * @@param l_lisSecurityProductInfos - (担保銘柄情報)<BR>
     * 担保銘柄登録情報<BR>
     * @@param l_datTargetPeriodFrom - (適用期間from)<BR>
     * 適用期間from<BR>
     * @@param l_datTargetPeriodTo - (適用期間to)<BR>
     * 適用期間to<BR>
     * @@throws WEB3BaseException
     * @@roseuid 46DFBBC101AB
     */
    public void validateSecurityProductSameTerm(
        List l_lisSecurityProductInfos,
        Date l_datTargetPeriodFrom,
        Date l_datTargetPeriodTo)
        throws WEB3BaseException;

    /**
     * (get担保銘柄行)<BR>
     * 主キーから担保銘柄行を取得する。<BR>
     * @@param l_lngProductId - (銘柄ID)<BR>
     * 銘柄ID<BR>
     * @@param l_datTargetPeriodFrom - (適用期間from)<BR>
     * 適用期間from<BR>
     * @@return SecurityProductRow
     * @@throws WEB3BaseException
     * @@roseuid 46DFBBD301DA
     */
    public SecurityProductRow getSecurityProductRow(
        long l_lngProductId,
        Date l_datTargetPeriodFrom)
        throws WEB3BaseException;

    /**
     * (compare変更情報)<BR>
     * 変更前と変更後の状態を比較する。<BR>
     * @@param l_changeBeforeSecurityProductInfo - (変更前担保銘柄情報)<BR>
     * 変更前担保銘柄情報<BR>
     * @@param l_changeAfterSecurityProductInfo - (変更後担保銘柄情報)<BR>
     * 変更後担保銘柄情報<BR>
     * @@return int
     * @@throws WEB3BaseException
     * @@roseuid 46DFBBE60370
     */
    public int compareChangeInfo(
        SecurityProductRow l_changeBeforeSecurityProductInfo,
        WEB3SLProductInfoUnit l_changeAfterSecurityProductInfo)
        throws WEB3BaseException;

    /**
     * (update担保銘柄情報)<BR>
     * 主キーを更新条件に担保銘柄テーブルのレコードを更新する。<BR>
     * @@param l_searchKeyConditions - (検索キー情報)<BR>
     * 担保銘柄検索キー情報<BR>
     * @@param l_securityProductRow - (担保銘柄Row)<BR>
     * 担保銘柄Row<BR>
     * @@throws WEB3BaseException
     * @@roseuid 46DFBC010285
     */
    public void updateSecurityProductInfo(
        WEB3SLProductSearchConditions l_searchKeyConditions,
        SecurityProductRow l_securityProductRow)
        throws WEB3BaseException;

    /**
     * (delete担保銘柄情報)<BR>
     * 主キーを対象に担保銘柄テーブルのレコードを削除する。<BR>
     * @@param l_deleteObjectKey - (削除対象キー)<BR>
     * 削除レコード対象キー<BR>
     * @@throws WEB3BaseException
     * @@roseuid 46DFBC0A015D
     */
    public void deleteSecurityProductInfo(WEB3SLProductSearchConditions l_deleteObjectKey)
        throws WEB3BaseException;

    /**
     * (get担保銘柄情報)<BR>
     * 銘柄IDをキーに担保銘柄テーブルのレコードを取得し、返却する。<BR>
     * @@param l_lngProductId - (銘柄ID)<BR>
     * 銘柄ID<BR>
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 46DFBC110370
     */
    public List getSecurityProductInfo(long l_lngProductId)
        throws WEB3BaseException;

    /**
     * (is項目変更)<BR>
     * 項目が変更されているかを判別する。<BR>
     * @@param l_strChangeBeforeItem - (変更前項目)<BR>
     * 変更前項目<BR>
     * @@param l_strChangeAfterItem - (変更後項目)<BR>
     * 変更後項目<BR>
     * @@return boolean
     * @@roseuid 46DFBC1B0285
     */
    public boolean isItemChange(String l_strChangeBeforeItem, String l_strChangeAfterItem);
}
@
