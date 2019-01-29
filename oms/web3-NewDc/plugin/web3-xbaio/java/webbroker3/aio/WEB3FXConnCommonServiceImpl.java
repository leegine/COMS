head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.03.24.02.17.16;	author che-jin;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5584d8aa9ab6999;
filename	WEB3FXConnCommonServiceImpl.java;

1.1
date	2011.03.16.02.36.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FXConnCommonServiceImpl.java;


desc
@@


1.2
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 接続共通Impl(WEB3FXConnCommonServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/06/25 柴双紅 (中訊) 新規作成 モデル1173,1180,1182,1188
Revision History : 2009/08/14 柴双紅 (中訊) モデル1190
Revision History : 2009/09/16 張騰宇 (中訊) モデル1204 1025 1219 1223 1224 1227 1232
Revision History : 2009/10/14 張騰宇 (中訊) モデル1239
*/

package webbroker3.aio;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;

import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.message.WEB3FXAccInformationUnit;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.message.WEB3FXGftResultNoticeTelegramUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3GftTransStatusCourseDivDef;
import webbroker3.gentrade.data.SoapConnectPrefRpcDao;
import webbroker3.gentrade.data.SoapConnectPrefRpcParams;
import webbroker3.gentrade.data.SoapConnectPrefRpcRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (接続共通mpl)<BR>
 * 接続共通Impl<BR>
 *
 * @@author 柴双紅
 * @@version 1.0
 */
public class WEB3FXConnCommonServiceImpl implements WEB3FXConnCommonService
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXConnCommonServiceImpl.class);

    /**
     * (send外部接続依頼メッセージ)<BR>
     * 外部接続依頼メッセージの送付を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「send外部接続依頼メッセージ」参照<BR>
     * <BR>
     * @@param l_compFxConditionParams - (会社別FXシステム条件)<BR>
     * 会社別FXシステム条件<BR>
     * @@param l_compFxConditionParams - (GFT依頼電文明細)<BR>
     * GFT依頼電文明細<BR>
     * @@return WEB3ExtConnection
     * @@throws WEB3BaseException
     */
    public WEB3ExtConnection sendExtConnAskingMessage(
        CompFxConditionParams l_compFxConditionParams,
        WEB3FXGftAskingTelegramUnit l_fXGftAskingTelegramUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "sendExtConnAskingMessage(CompFxConditionParams, WEB3FXGftAskingTelegramUnit)";
        log.entering(STR_METHOD_NAME);

        //get外部接続インスタンス(外部接続システムコード : String)
        //[引数]
        // 外部接続システムコード： 会社別FXシステム条件Paramsから取得した外部接続システムコード
        //処理区分： 引数.GFT依頼電文明細.処理区分
        WEB3ExtConnection l_extConnection =
            WEB3FXExtConnectionManager.getExtConnectionInstance(
                l_compFxConditionParams.getExtConnectSystemCode(), l_fXGftAskingTelegramUnit.gftOperationDiv);

        //以下の条件で、外部システムSOAPプリファ@レンス（RPC形式）からレコードを取得
        //[条件]
        //  部店ID = 引数.GFT依頼電文明細から会社コードと引数.会社別FXシステム条件.部店コードを利用して、部店IDを取得する
        //  接続区分 = 引数.会社別FXシステム条件Params.FXシステムコード
        SoapConnectPrefRpcRow l_soapConnectPrefRpcRow;
        try
        {
            BranchRow l_branchRow =
                BranchDao.findRowByInstitutionCodeBranchCode(
                    l_fXGftAskingTelegramUnit.institutionCode,
                    l_compFxConditionParams.getBranchCode());
            l_soapConnectPrefRpcRow =
                SoapConnectPrefRpcDao.findRowByPk(
                    l_branchRow.getBranchId(),
                    l_compFxConditionParams.getFxSystemCode());
        }
        catch (DataFindException l_ex)
        {
            //※レコードが取得できなかった場合は例外をスローする。
            log.error("外部システムSOAPプリファ@レンス（RPC形式）のレコードが取得できません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03075,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        SoapConnectPrefRpcParams l_soapConnectPrefRpcParams =
            new SoapConnectPrefRpcParams(l_soapConnectPrefRpcRow);
        //setSOAP接続用プロキシ(外部システムSOAPプリファ@レンス（RPC形式）Params)
        this.setSOAPConnectionProxy(l_soapConnectPrefRpcParams);

        //sendMessage(Message, 外部システムSOAPプリファ@レンス（RPC形式）Params, 補助口座)
        //[引数]
        //電文メッセージ：引数.GFT依頼電文明細
        //外部システムSOAPプリファ@レンス：1.3）で取得した外部システムSOAPプリファ@レンス（RPC形式）params
        l_extConnection.sendMessage(
            l_fXGftAskingTelegramUnit,
            l_soapConnectPrefRpcParams);

        //外部接続インスタンスを返却する
        log.exiting(STR_METHOD_NAME);
        return l_extConnection;
    }

    /**
     * (SOAP接続用プロキシ)<BR>
     * SOAP接続用プロキシ設定を行う。<BR>
     * <BR>
     * 1）引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.エンドポイント名を<BR>
     * 　@　@分割し、接続先情報を取得する。<BR>
     * 　@getEndpointName().split(arg0 : String)<BR>
     * 　@　@[引数]<BR>
     * 　@　@arg0： ";"<BR>
     * <BR>
     * 2）プロキシ設定を行う。<BR>
     * 　@＜外部システムSOAP接続プリファ@レンス（RPC形式）params.エンドポイントの分割数 == 3の場合＞<BR>
     * 　@　@＜＜分割データの3件目の文字列に"https"が含まれる場合＞＞<BR>
     * 　@　@下記の通りプロパティセットを行う。<BR>
     * 　@　@（1）key   : "https.proxyHost"<BR>
     * 　@　@　@　@value : 1）で取得した接続先情報の配列第1要素<BR>
     * 　@　@（2）key   : "https.proxyPort"<BR>
     * 　@　@　@　@value : 1）で取得した接続先情報の配列第2要素<BR>
     * 　@　@＜＜上記の条件以外＞＞<BR>
     * 　@　@下記の通りプロパティセットを行う。<BR>
     * 　@　@（1）key   : "http.proxyHost"<BR>
     * 　@　@　@　@value : 1）で取得した接続先情報の配列第1要素<BR>
     * 　@　@（2）key   : "http.proxyPort"<BR>
     * 　@　@　@　@value : 1）で取得した接続先情報の配列第2要素<BR>
     * 　@　@　@　@value : 1）で取得した接続先情報の配列第2要素<BR>
     * 　@＜外部システムSOAP接続プリファ@レンス（RPC形式）params.エンドポイントの分割数 == 1の場合＞<BR>
     * 　@　@処理なし<BR>
     * 　@＜上記の条件以外＞<BR>
     * 　@　@例外をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@: BUSINESS_ERROR_02398<BR>
     * <BR>
     * @@param l_rpcParams - (外部システムSOAPプリファ@レンス（RPC形式）)<BR>
     * 外部システムSOAPプリファ@レンス（RPC形式）<BR>
     * @@throws WEB3BaseException
     */
    public void setSOAPConnectionProxy(SoapConnectPrefRpcParams l_rpcParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "setSOAPConnectionProxy(SoapConnectPrefRpcParams)";
        log.entering(STR_METHOD_NAME);

        //引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.エンドポイント名を
        //  分割し、接続先情報を取得する。
        // getEndpointName().split(arg0 : String)
        //　@[引数]
        //　@arg0： ";"
        String[] l_strEndpointNames = l_rpcParams.getEndpointName().split(";");

        //＜外部システムSOAP接続プリファ@レンス（RPC形式）params.エンドポイントの分割数 == 3の場合＞
        if (l_strEndpointNames.length == 3)
        {
            //＜＜分割データの3件目の文字列に"https"が含まれる場合＞＞
            if(l_strEndpointNames[2].trim().indexOf("https") >= 0)
            {
                //（1）key   : "https.proxyHost"
                // 　@  value : 1）で取得した接続先情報の配列第1要素
                System.setProperty("https.proxyHost", l_strEndpointNames[0].trim());

                //（2）key   : "https.proxyPort"
                // 　@  value : 1）で取得した接続先情報の配列第2要素
                System.setProperty("https.proxyPort", l_strEndpointNames[1].trim());
            }
            else
            {
                //＜＜上記の条件以外＞＞
                // 下記の通りプロパティセットを行う。
                //（1）key   : "http.proxyHost"
                // 　@  value : 1）で取得した接続先情報の配列第1要素
                System.setProperty("http.proxyHost", l_strEndpointNames[0].trim());

                //（2）key   : "http.proxyPort"
                // 　@  value : 1）で取得した接続先情報の配列第2要素
                System.setProperty("http.proxyPort", l_strEndpointNames[1].trim());
            }
        }
        else if (l_strEndpointNames.length == 1)
        {
            //＜外部システムSOAP接続プリファ@レンス（RPC形式）params.エンドポイントの分割数 == 1の場合＞
            // 処理なし
        }
        else
        {
            //＜上記の条件以外＞
            //例外をスローする。
            log.debug("外部システム接続エラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02398,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "外部システム接続エラー。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (createGFT結果通知電文明細)<BR>
     * GFT結果通知電文明細を作成する。 <BR>
     * <BR>
     * １）GFT結果通知電文明細の項目は、引数.GFT依頼電文明細の同項目の値をセットする。 <BR>
     * 　@同項目がnullならばnullがセットされるような仕組みでOK。<BR>
     * <BR>
     * ２）GFT→DIR送信日時に、現在日時をセットする。 <BR>
     * 　@Timestamp l_tsSystemTimestamp = new Timestamp(new Date().getTime()); <BR>
     * <BR>
     * ３）受付結果に、引数.受付結果コードをセットする。 <BR>
     * <BR>
     * ４）口座開設の場合（FX口座情報一覧 != null）、 <BR>
     * 　@　@４－１）GFT結果通知電文明細.為替保証金口座情報一覧 = 引数.FX口座情報一覧 <BR>
     * <BR>
     * 　@　@４－２）FX口座情報一覧の要素数分以下を実施する。 <BR>
     * 　@　@　@４－２－１）引数.FX口座情報一覧[n].コース区分==1の場合 <BR>
     * 　@　@　@　@為替保証金口座番号（1万通貨）に 引数.FX口座情報一覧[n].口座番号をセットする。 <BR>
     * 　@　@　@４－２－２引数.FX口座情報一覧[n].コース区分==2の場合 <BR>
     * 　@　@　@　@為替保証金口座番号（10万通貨）に 引数.FX口座情報一覧[n].口座番号をセットする。 <BR>
     * <BR>
     * ５）生成したGFT結果通知電文明細を返却する。 <BR>
     * @@param l_fXGftAskingTelegramUnit - (GFT依頼電文明細)<BR>
     * GFT依頼電文明細<BR>
     * @@param l_fXAccInformationUnits - (FX口座情報一 覧)<BR>
     * FX口座情報一覧<BR>
     * @@param l_strResultCode - (受付結果コード)<BR>
     * 受付結果コード<BR>
     * @@return WEB3FXGftResultNoticeTelegramUnit
     */
    public WEB3FXGftResultNoticeTelegramUnit createGftResultNoticeTelegramUnit(
        WEB3FXGftAskingTelegramUnit l_fXGftAskingTelegramUnit,
        WEB3FXAccInformationUnit[] l_fXAccInformationUnits,
        String l_strResultCode)
    {
        final String STR_METHOD_NAME = "createGftResultNoticeTelegramUnit(WEB3FXGftAskingTelegramUnit, WEB3FXAccInformationUnit[], String)";
        log.entering(STR_METHOD_NAME);
        
        WEB3FXGftResultNoticeTelegramUnit l_fXGftResultNoticeTelegramUnit =
            new WEB3FXGftResultNoticeTelegramUnit();
        //GFT結果通知電文明細を作成する。
        //GFT結果通知電文明細の項目は、引数.GFT依頼電文明細の同項目の値をセットする。
        String l_strOperationDiv = l_fXGftAskingTelegramUnit.gftOperationDiv;
        //　@DIR→GFT送信日時
        l_fXGftResultNoticeTelegramUnit.dirSendTime = l_fXGftAskingTelegramUnit.dirSendTime;
        //　@処理区分
        l_fXGftResultNoticeTelegramUnit.gftOperationDiv = l_strOperationDiv;
        //　@初期ログインID
        l_fXGftResultNoticeTelegramUnit.fxFirstLoginId = l_fXGftAskingTelegramUnit.fxFirstLoginId;
        //　@担当区分
        l_fXGftResultNoticeTelegramUnit.groupName = l_fXGftAskingTelegramUnit.groupName;
        //　@会社コード
        l_fXGftResultNoticeTelegramUnit.institutionCode = l_fXGftAskingTelegramUnit.institutionCode;
        //　@WOLFセッションキー
        l_fXGftResultNoticeTelegramUnit.wolfSession = l_fXGftAskingTelegramUnit.wolfSession;
        //　@アプリケーションID
        l_fXGftResultNoticeTelegramUnit.wolfAid = l_fXGftAskingTelegramUnit.wolfAid;
        //　@再生成サービスID
        l_fXGftResultNoticeTelegramUnit.regetServiceId = l_fXGftAskingTelegramUnit.regetServiceId;
        //　@SSID
        l_fXGftResultNoticeTelegramUnit.wolfSsid = l_fXGftAskingTelegramUnit.wolfSsid;
        //　@部店コード
        l_fXGftResultNoticeTelegramUnit.branchCode = l_fXGftAskingTelegramUnit.branchCode;
        //　@顧客コード
        l_fXGftResultNoticeTelegramUnit.accountCode = l_fXGftAskingTelegramUnit.accountCode;
        //　@識別コード
        l_fXGftResultNoticeTelegramUnit.requestNumber = l_fXGftAskingTelegramUnit.requestNumber;
        //為替保証金口座番号
        l_fXGftResultNoticeTelegramUnit.fxAccountCode = l_fXGftAskingTelegramUnit.fxAccountCode;
        //入出金額
        l_fXGftResultNoticeTelegramUnit.cashinoutAmt = l_fXGftAskingTelegramUnit.cashinoutAmt;
        //受渡日 
        l_fXGftResultNoticeTelegramUnit.deliveryDate = l_fXGftAskingTelegramUnit.deliveryDate;
        //　@名前（姓）
        l_fXGftResultNoticeTelegramUnit.fxLastName = l_fXGftAskingTelegramUnit.fxLastName;
        //名前（名）
        l_fXGftResultNoticeTelegramUnit.fxFirstName = l_fXGftAskingTelegramUnit.fxFirstName;
        //　@メールアドレス
        l_fXGftResultNoticeTelegramUnit.fxMailAddress = l_fXGftAskingTelegramUnit.fxMailAddress;
        //　@初期パスワード
        l_fXGftResultNoticeTelegramUnit.fxFirstPassword = l_fXGftAskingTelegramUnit.fxFirstPassword;
        //会社コード
        l_fXGftResultNoticeTelegramUnit.institutionCode = l_fXGftAskingTelegramUnit.institutionCode;
        //ハッシュ値
        l_fXGftResultNoticeTelegramUnit.hashValue = l_fXGftAskingTelegramUnit.hashValue;

        //２）GFT→DIR送信日時に、現在日時をセットする。 
        //　@Timestamp l_tsSystemTimestamp = new Timestamp(new Date().getTime()); 
        Timestamp l_tsSystemTimestamp = new Timestamp(new Date().getTime()); 
        l_fXGftResultNoticeTelegramUnit.gftSendTime =
            WEB3DateUtility.formatDate(l_tsSystemTimestamp, 
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS);

        //３）受付結果に、引数.受付結果コードをセットする。
        l_fXGftResultNoticeTelegramUnit.resultCode = l_strResultCode;

        //４）口座開設の場合（FX口座情報一覧 != null 且つ FX口座情報一覧.length != 0）
        if (l_fXAccInformationUnits != null && l_fXAccInformationUnits.length != 0)
        {
            //　@　@４－１）GFT結果通知電文明細.為替保証金口座情報一覧 = 引数.FX口座情報一覧
            l_fXGftResultNoticeTelegramUnit.fxAccInformationList = l_fXAccInformationUnits;
            //　@　@４－２）FX口座情報一覧の要素数分以下を実施する。
            int l_intLength = l_fXAccInformationUnits.length;
            for (int i = 0; i < l_intLength; i++)
            {
                WEB3FXAccInformationUnit l_fXAccInformationUnit = l_fXAccInformationUnits[i];
                //　@　@　@４－２－１）引数.FX口座情報一覧[n].コース区分==1の場合
                if (WEB3GftTransStatusCourseDivDef.ONE_COSE.equals(l_fXAccInformationUnit.fxCourseDiv))
                {
                    //　@　@　@　@為替保証金口座番号（1万通貨）に 引数.FX口座情報一覧[n].口座番号をセットする。
                    l_fXGftResultNoticeTelegramUnit.gftAcc1 = l_fXAccInformationUnit.fxAccountCode;
                }
                //　@　@　@４－２－２引数.FX口座情報一覧[n].コース区分==2の場合
                if (WEB3GftTransStatusCourseDivDef.TEN_COSE.equals(l_fXAccInformationUnit.fxCourseDiv))
                {
                    //　@　@　@　@為替保証金口座番号（10万通貨）に 引数.FX口座情報一覧[n].口座番号をセットする。
                    l_fXGftResultNoticeTelegramUnit.gftAcc2 = l_fXAccInformationUnit.fxAccountCode;
                }
            }
        }
        //５）生成したGFT結果通知電文明細を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_fXGftResultNoticeTelegramUnit;
    }
}
@


1.1
log
@*** empty log message ***
@
text
@a165 4
     * 　@　@（3）key   : "weblogic.webservice.transport.https.proxy.host"<BR>
     * 　@　@　@　@value : 1）で取得した接続先情報の配列第1要素<BR>
     * 　@　@（4）key   : "weblogic.webservice.transport.https.proxy.port"<BR>
     * 　@　@　@　@value : 1）で取得した接続先情報の配列第2要素<BR>
a171 3
     * 　@　@（3）key   : "weblogic.webservice.transport.http.proxy.host"<BR>
     * 　@　@　@　@value : 1）で取得した接続先情報の配列第1要素<BR>
     * 　@　@（4）key   : "weblogic.webservice.transport.http.proxy.port"<BR>
a209 10

                //（3）key   : "weblogic.webservice.transport.https.proxy.host"
                // 　@  value : 1）で取得した接続先情報の配列第1要素
                System.setProperty("weblogic.webservice.transport.https.proxy.host",
                    l_strEndpointNames[0].trim());

                //（4）key   : "weblogic.webservice.transport.https.proxy.port"
                //　@　@ 　@  value : 1）で取得した接続先情報の配列第2要素
                System.setProperty("weblogic.webservice.transport.https.proxy.port",
                    l_strEndpointNames[1].trim());
a221 10

                //（3）key   : "weblogic.webservice.transport.http.proxy.host"
                // 　@  value : 1）で取得した接続先情報の配列第1要素
                System.setProperty("weblogic.webservice.transport.http.proxy.host",
                    l_strEndpointNames[0].trim());

                //（4）key   : "weblogic.webservice.transport.http.proxy.port"
                // 　@  value : 1）で取得した接続先情報の配列第2要素
                System.setProperty("weblogic.webservice.transport.http.proxy.port",
                    l_strEndpointNames[1].trim());
@

