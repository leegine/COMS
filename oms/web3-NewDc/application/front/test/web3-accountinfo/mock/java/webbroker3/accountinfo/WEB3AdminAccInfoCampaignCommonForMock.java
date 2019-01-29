head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.37.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5404d9c264d0465;
filename	WEB3AdminAccInfoCampaignCommonForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 手数料割引キャンペーン共通クラスForMock(WEB3AdminAccInfoCampaignCommonForMock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/07 徐宏偉 (中訊) 新規作成
*/
package webbroker3.accountinfo;

import webbroker3.accountinfo.message.WEB3AccInfoCampaignInfo;
import webbroker3.accountinfo.message.WEB3AccInfoCampaignSearchCondition;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * 手数料割引キャンペーン共通クラスForMock
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignCommonForMock extends WEB3AdminAccInfoCampaignCommon
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminAccInfoCampaignCommonForMock.class);

    /**
     * (deleteキャンペーン条件(Mock))<BR>
     * 手数料割引キャンペーン条件マスタレコードの削除を行う。<BR>
     * @@param l_strCampaignConditionId - 手数料割引キャンペーン条件ID<BR>
     * <BR>
     * @@param l_strUpdaterCode - 更新者コード<BR>
     * @@throws WEB3BaseException<BR>
     */
    public void deleteCampaignCondition(String l_strCampaignConditionId, String l_strUpdaterCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "deleteCampaignCondition(String, String)-->ForMock";
        log.entering(STR_METHOD_NAME);
        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
            "deleteCampaignCondition",
            new Class[] {String.class, String.class},
            new Object[]{l_strCampaignConditionId, l_strUpdaterCode});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
            "deleteCampaignCondition",  new Class[] {String.class, String.class}))
        {
            //2）MockFor --〉 WEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "deleteCampaignCondition",
                new Class[] {String.class, String.class}).asWEB3BaseException();

            //3)MockFor --〉 asVoid
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "deleteCampaignCondition",
                new Class[] {String.class, String.class}).asVoid();

            return;
        }

        log.exiting(STR_METHOD_NAME);
        super.deleteCampaignCondition(l_strCampaignConditionId, l_strUpdaterCode);
    }

    /**
     * (getキャンペーン一覧(Mock))<BR>
     * 手数料割引キャンペーンレコードの取得を行う。<BR>
     * @@param l_request - リクエストデータオブジェクト<BR>
     * <BR>
     * @@param l_strInstitutionCode - 証券会社コード<BR>
     * @@param l_strRegistTypes - (登録タイプ)<BR>
     * 登録タイプの配列<BR>
     * <BR>
     * 0： 口座開設条件指定<BR>
     * 1：個別顧客指定<BR>
     * 2：強制個別顧客指定<BR>
     * @@return WEB3AccInfoCampainInfo[]
     */
    public WEB3AccInfoCampaignInfo[] getCampaignList(WEB3GenRequest l_request, String l_strInstitutionCode,
            String[] l_strRegistTypes) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCampaignList(WEB3GenRequest, String, String[])-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
            "getCampaignList",
            new Class[] {WEB3GenRequest.class, String.class, String[].class},
            new Object[]{l_request, l_strInstitutionCode, l_strRegistTypes});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
            "getCampaignList",
            new Class[] {WEB3GenRequest.class, String.class, String[].class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "getCampaignList",
                new Class[] {WEB3GenRequest.class, String.class, String[].class}).asWEB3BaseException();

            //3)MockFor --〉 asObject
            log.exiting(STR_METHOD_NAME);
            return (WEB3AccInfoCampaignInfo[])TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "getCampaignList",
                new Class[] {WEB3GenRequest.class, String.class, String[].class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getCampaignList(l_request, l_strInstitutionCode, l_strRegistTypes);
    }

    /**
     * (getキャンペーン条件(Mock))<BR>
     * @@param l_strCampaignId - 手数料割引キャンペーン条件ID<BR>
     * <BR>
     * @@return WEB3AccInfoCampainInfo
     */
    public WEB3AccInfoCampaignInfo getCampaignCondition(String l_strCampaignId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCampaignItemMasterList(List)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
            "getCampaignCondition",
            new Class[] {String.class},
            new Object[]{l_strCampaignId});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
            "getCampaignCondition",
            new Class[] {String.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "getCampaignCondition",
                new Class[] {String.class}).asWEB3BaseException();

            //3)MockFor --〉 asObject
            log.exiting(STR_METHOD_NAME);
            return (WEB3AccInfoCampaignInfo)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "getCampaignCondition",
                new Class[] {String.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getCampaignCondition(l_strCampaignId);
    }

    /**
     * (get重複キャンペーン条件(Mock))<BR>
     * 手数料割引キャンペーンレコードの取得を行う。<BR>
     * @@param l_sameSearchCondition - 重複検索条件<BR>
     * @@return WEB3AccInfoCampainInfo[]
     */
    public WEB3AccInfoCampaignInfo[] getSameCampaignCondition(
            WEB3AdminAccInfoCampaignSearchCondition l_sameSearchCondition) throws WEB3BaseException
    {
        final String STR_METHOD_NAME
            = "getSameCampaignCondition(WEB3AdminAccInfoCampaignSearchCondition)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
            "getSameCampaignCondition",
            new Class[] {WEB3AdminAccInfoCampaignSearchCondition.class},
            new Object[]{l_sameSearchCondition});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
            "getSameCampaignCondition",
            new Class[] {WEB3AdminAccInfoCampaignSearchCondition.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "getSameCampaignCondition",
                new Class[] {WEB3AdminAccInfoCampaignSearchCondition.class}).asWEB3BaseException();

            //3)MockFor --〉 asObject
            log.exiting(STR_METHOD_NAME);
            return (WEB3AccInfoCampaignInfo[])TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "getSameCampaignCondition",
                new Class[] {WEB3AdminAccInfoCampaignSearchCondition.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getSameCampaignCondition(l_sameSearchCondition);
    }

    /**
     * (get総レコード件数(Mock))<BR>
     * 総レコード件数値を返す。<BR>
     * @@param l_campaignSearchCondition - (キャンペーン検索項目)<BR>
     * 手数料割引キャンペーン検索条件オブジェクト<BR>
     * @@param l_strInstitutionCode - 証券会社コード<BR>
     * @@param l_strRegistTypes - 登録タイプ<BR>
     * @@return int
     */
    public int getAllRecordCount(WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition,
            String l_strInstitutionCode, String[] l_strRegistTypes) throws WEB3BaseException
    {
        final String STR_METHOD_NAME
            = "getAllRecordCount(WEB3AccInfoCampaignSearchCondition, String, String[])-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
            "getAllRecordCount",
            new Class[] {WEB3AccInfoCampaignSearchCondition.class, String.class, String[].class},
            new Object[]{l_campaignSearchCondition, l_strInstitutionCode, l_strRegistTypes});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
            "getAllRecordCount",
            new Class[] {WEB3AccInfoCampaignSearchCondition.class, String.class, String[].class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "getAllRecordCount",
                new Class[] {WEB3AccInfoCampaignSearchCondition.class, String.class, String[].class}).asWEB3BaseException();

            //3)MockFor --〉 asInt
            log.exiting(STR_METHOD_NAME);
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "getAllRecordCount",
                new Class[] {WEB3AccInfoCampaignSearchCondition.class, String.class, String[].class}).asInt();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getAllRecordCount(l_campaignSearchCondition, l_strInstitutionCode, l_strRegistTypes);
    }

    /**
     * (insertキャンペーン条件(Mock))<BR>
     * @@param l_registInfo - (登録情報)<BR>
     * 手数料割引キャンペーン条件情報オブジェクト<BR>
     * <BR>
     * @@param l_strUpdaterCode - 更新者コード<BR>
     */
    public void insertCampaignCondition(WEB3AccInfoCampaignInfo l_registInfo, String l_strUpdaterCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME
            = "insertCampaignCondition(WEB3AccInfoCampaignInfo, String)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
            "insertCampaignCondition",
            new Class[] {WEB3AccInfoCampaignInfo.class, String.class},
            new Object[]{l_registInfo, l_strUpdaterCode});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
            "insertCampaignCondition",
            new Class[] {WEB3AccInfoCampaignInfo.class, String.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "insertCampaignCondition",
                new Class[] {WEB3AccInfoCampaignInfo.class, String.class}).asWEB3BaseException();

            //3)MockFor --〉 asVoid
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "insertCampaignCondition",
                new Class[] {WEB3AccInfoCampaignInfo.class, String.class}).asVoid();

            return;
        }

        log.exiting(STR_METHOD_NAME);
        super.insertCampaignCondition(l_registInfo, l_strUpdaterCode);
    }

    /**
     * (is変更情報(Mock))<BR>
     * @@param l_changeAfterInfo - (変更後情報)<BR>
     * 手数料割引キャンペーン条件情報オブジェクト<BR>
     * @@return Boolean
     */
    public boolean isChangeInfo(WEB3AccInfoCampaignInfo l_changeAfterInfo)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isChangeInfo(WEB3AccInfoCampaignInfo)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
            "isChangeInfo",
            new Class[] {WEB3AccInfoCampaignInfo.class},
            new Object[]{l_changeAfterInfo});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
            "isChangeInfo",
            new Class[] {WEB3AccInfoCampaignInfo.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "isChangeInfo",
                new Class[] {WEB3AccInfoCampaignInfo.class}).asWEB3BaseException();

            //3)MockFor --〉 asBoolean
            log.exiting(STR_METHOD_NAME);
            return (boolean)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "isChangeInfo",
                new Class[] {WEB3AccInfoCampaignInfo.class}).asBoolean();

        }

        log.exiting(STR_METHOD_NAME);
        return super.isChangeInfo(l_changeAfterInfo);
    }

    /**
     * (updateキャンペーン条件)<BR>
     * @@param l_changeAfterInfo - (変更後情報)<BR>
     * 手数料割引キャンペーン条件情報オブジェクト<BR>
     * <BR>
     * @@param l_strUpdaterCode - 更新者コード<BR>
     */
    public void updateCampaignCondition(WEB3AccInfoCampaignInfo l_changeAfterInfo, String l_strUpdaterCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateCampaignCondition(WEB3AccInfoCampaignInfo, String)";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
            "updateCampaignCondition",
            new Class[] {WEB3AccInfoCampaignInfo.class, String.class},
            new Object[]{l_changeAfterInfo, l_strUpdaterCode});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
            "updateCampaignCondition",
            new Class[] {WEB3AccInfoCampaignInfo.class, String.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "updateCampaignCondition",
                new Class[] {WEB3AccInfoCampaignInfo.class, String.class}).asWEB3BaseException();

            //3)MockFor --〉 asVoid
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "updateCampaignCondition",
                new Class[] {WEB3AccInfoCampaignInfo.class, String.class}).asVoid();

            return;
        }

        log.exiting(STR_METHOD_NAME);
        super.updateCampaignCondition(l_changeAfterInfo, l_strUpdaterCode);
    }

    /**
     * (validate対象期間(Mock))<BR>
     * 修正対象のキャンペーンが現在、キャンペーン期間中かどうかチェックを行う。<BR>
     * @@param l_changeAfterInfo - (変更後情報)<BR>
     * 手数料割引キャンペーン条件情報オブジェクト<BR>
     * @@return String
     */
    public String validateTargetPeriod(WEB3AccInfoCampaignInfo l_changeAfterInfo,String updateFlag)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateTargetPeriod(WEB3AccInfoCampaignInfo)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
            "validateTargetPeriod",
            new Class[] {WEB3AccInfoCampaignInfo.class,String.class},
            new Object[]{l_changeAfterInfo,updateFlag});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
            "validateTargetPeriod",
            new Class[] {WEB3AccInfoCampaignInfo.class,String.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "validateTargetPeriod",
                new Class[] {WEB3AccInfoCampaignInfo.class,String.class}).asWEB3BaseException();

            //3)MockFor --〉 asObject
            log.exiting(STR_METHOD_NAME);
            return (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "validateTargetPeriod",
                new Class[] {WEB3AccInfoCampaignInfo.class,String.class}).asObject();

        }

        log.exiting(STR_METHOD_NAME);
        return super.validateTargetPeriod(l_changeAfterInfo,updateFlag);
    }

    /**
     * （スーパークラスに自身のインスタンスを登録する。）。<BR>
     * <BR>
     * （プラグイン初期化時にコールされる）<BR>
     * <BR>
     * ---<BR>
     * super.setInstance(this);<BR>
     * ---
     */
    public void register()
    {
        log.debug("プラグイン初期化時にコールされるregister");
        super.setInstance(this);
    }
}
@
