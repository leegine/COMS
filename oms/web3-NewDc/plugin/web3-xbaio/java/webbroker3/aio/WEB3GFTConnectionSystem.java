head	1.10;
access;
symbols;
locks; strict;
comment	@// @;


1.10
date	2011.05.23.05.28.12;	author zhang-tengyu;	state Exp;
branches;
next	1.9;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8bc4dd9f06c4bb9;
filename	WEB3GFTConnectionSystem.java;

1.9
date	2011.04.13.07.07.46;	author zhang-tengyu;	state Exp;
branches;
next	1.8;
deltatype	text;
kopt	kv;
permissions	666;
commitid	44c4da54bc2629c;
filename	WEB3GFTConnectionSystem.java;

1.8
date	2011.04.13.06.41.44;	author zhang-tengyu;	state Exp;
branches;
next	1.7;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004da545a74eac;
filename	WEB3GFTConnectionSystem.java;

1.7
date	2011.04.08.05.39.28;	author zhang-tengyu;	state Exp;
branches;
next	1.6;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6704d9e9f90185d;
filename	WEB3GFTConnectionSystem.java;

1.6
date	2011.04.08.05.00.23;	author zhang-tengyu;	state Exp;
branches;
next	1.5;
deltatype	text;
kopt	kv;
permissions	666;
commitid	61c4d9e96677a73;
filename	WEB3GFTConnectionSystem.java;

1.5
date	2011.04.08.04.56.02;	author zhang-tengyu;	state Exp;
branches;
next	1.4;
deltatype	text;
kopt	kv;
permissions	666;
commitid	61c4d9e9562771e;
filename	WEB3GFTConnectionSystem.java;

1.4
date	2011.03.30.08.23.13;	author zhang-tengyu;	state Exp;
branches;
next	1.3;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4044d92e871567e;
filename	WEB3GFTConnectionSystem.java;

1.3
date	2011.03.30.07.00.22;	author zhang-tengyu;	state Exp;
branches;
next	1.2;
deltatype	text;
kopt	kv;
permissions	666;
commitid	3ac4d92d5051712;
filename	WEB3GFTConnectionSystem.java;

1.2
date	2011.03.28.06.21.47;	author zhang-tengyu;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	3f04d9028fa5d46;
filename	WEB3GFTConnectionSystem.java;

1.1
date	2011.03.16.02.33.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3GFTConnectionSystem.java;


desc
@@


1.10
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : GFT接続システム(WEB3GFTConnectionSystem.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/06/25 柴双紅 (中訊) 新規作成 モデル1170,1181,1184,1189
Revision History : 2009/09/16 孟亞南 (中訊) 仕様変更・モデル1202,1203,1211,1214,1217,1225
Revision History : 2009/10/14 張騰宇 (中訊) 仕様変更・モデル1238,1241
*/

package webbroker3.aio;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.message.Message;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.gftforex.soap_api.AuthToken;
import com.gftforex.soap_api.CommandAddAccount;
import com.gftforex.soap_api.CommandBase;
import com.gftforex.soap_api.CommandCreateUser;
import com.gftforex.soap_api.CommandDeposit;
import com.gftforex.soap_api.CommandLookupUser;
import com.gftforex.soap_api.CommandWithdraw;
import com.gftforex.soap_api.CreateAccountInfo;
import com.gftforex.soap_api.CreateUserInfo;
import com.gftforex.soap_api.InternalErrorException;
import com.gftforex.soap_api.LookupAccountInfo;
import com.gftforex.soap_api.LookupUserInfo;
import com.gftforex.soap_api.RejectedCommand;
import com.gftforex.soap_api.ResultInfoAddAccount;
import com.gftforex.soap_api.ResultInfoBase;
import com.gftforex.soap_api.ResultInfoCreateUser;
import com.gftforex.soap_api.ResultInfoLookupUser;
import com.gftforex.soap_api.SendSyncRequestResponse;
import com.gftforex.soap_api.UserPersonalInfo;
import com.gftforex.soap_api.UserSystemInfo;

import com.gftforex.soap_api.AdministrativeAPI;
import com.gftforex.soap_api.AdministrativeAPIPort;
import com.gftforex.soap_api.AuthorizationFailedException;

import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.define.WEB3AdminAioGftOperationDivDef;
import webbroker3.aio.define.WEB3AioAcceptResultCodeDef;
import webbroker3.aio.define.WEB3AioHashAlgorithmDef;
import webbroker3.aio.define.WEB3AioTransferDetailMessageDef;
import webbroker3.aio.handler.WEB3FXSOAPMsgHandler;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FxSystemDivDef;
import webbroker3.common.define.WEB3GftSoapResultCodeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.data.SoapConnectPrefRpcParams;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (GFT接続システム)<BR>
 * GFT接続システム実装クラス<BR>
 *
 * @@author 柴双紅
 * @@version 1.0
 */
public class WEB3GFTConnectionSystem extends WEB3FXExtSystemCommon
    implements WEB3ExtConnection 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GFTConnectionSystem.class);

    /**
     * コンストラクタ。<BR>
     */
    public WEB3GFTConnectionSystem()
    {
        this.connectionResultDetails = new HashMap();
    }

    /**
     * GFT接続のシステムへ依頼電文の送付を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「sendMessage(GFT)」を参照する<BR>
     * <BR>
     * @@param l_message - (GFT依頼電文明細)<BR>
     * GFT依頼電文明細<BR>
     * @@param l_prefRpcParams - (外部システムSOAPプリファ@レンス（RPC形式)<BR>
     * 外部システムSOAPプリファ@レンス（RPC形式<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@throws WEB3BaseException
     */
    public void sendMessage(
        Message l_message,
        SoapConnectPrefRpcParams l_prefRpcParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "sendMessage(Message, SoapConnectPrefRpcParams)";
        log.entering(STR_METHOD_NAME);

        //GFT依頼電文明細.処理区分 != 07：振替可能額の場合
        WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit = (WEB3FXGftAskingTelegramUnit)l_message;
        if (!WEB3AdminAioGftOperationDivDef.TRANSFER_ABLE_AMT.equals(l_fxGftAskingTelegramUnit.gftOperationDiv))
        {
        	try
        	{
                this.validateConnect(l_prefRpcParams);
        	}
        	catch(WEB3BaseException l_ex)
        	{
        	    this.connectionResultDetails.put(RESULT_CODE, WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_00000199);
                this.connectionResultDetails.put(CONNECT_RESULT, WEB3GftSoapResultCodeDef.SOAP_CONN_CONFIRM_ERROR);
                return;
        	}
        }
        //this.getGMTtime()をコールする
        String l_strGMT = this.getGMTtime();

        //this.getHash値()をコールする
        //[引数]
        //世界標準時間：取得した世界標準時間
        //ログイン：引数.外部システムSOAP接続プリファ@レンス（RPC形式）.ポートタイプ名
        //シークレットキー：引数.外部システムSOAP接続プリファ@レンス（RPC形式）.オペレーション名
        String l_strHashValue = this.getHashValue(l_strGMT, l_prefRpcParams.getPortTypeName(), l_prefRpcParams.getOperationName());

        String l_strReturnValue = null;
        String l_strReturnDetailValue = null;
        String l_strSoapGFTResultCode;
        Holder<RejectedCommand> l_rejectedCommand = new Holder<RejectedCommand>();
        Holder<ResultInfoBase> l_sendSyncRequestResult = new Holder<ResultInfoBase>();
        try
        {
            //this.sendSOAPメッセージ()をコールする。
            //[引数]
            //　@GFT依頼電文明細：引数.GFT電文メッセージ
            //　@外部システムSOAP接続プリファ@レンス（RPC形式）：
            //　@　@　@引数.外部システムSOAPプリファ@レンス（RPC形式）
            //　@世界標準時間：１）で取得した世界標準時間
            //　@ハッシュ値：２）で取得したハッシュ値
            this.sendSoapMessage(
                    (WEB3FXGftAskingTelegramUnit)l_message,
                    l_prefRpcParams,
                    l_strGMT,
                    l_strHashValue,
                    l_rejectedCommand,
                    l_sendSyncRequestResult);
            
            // ※sendSOAPメッセージ()の戻り値.getRejectedCommand() != nullの場合、
            // 受付結果コード：sendSOAPメッセージ()の戻り値.getRejectedCommand().getMajorErrorCode()
            // 受付結果詳細コード：sendSOAPメッセージ()の戻り値.getRejectedCommand().getMinorErrorCode()
            if (l_rejectedCommand.value != null)
            {
                l_strReturnValue = l_rejectedCommand.value.getMajorErrorCode() + "";

                if (l_rejectedCommand.value.getMinorErrorCode() != null)
                {
                   l_strReturnDetailValue = l_rejectedCommand.value.getMinorErrorCode().intValue() + "";
                }
                else
                {
                    l_strReturnDetailValue = null;
                }
            }
            // ※sendSOAPメッセージ()の戻り値.getRejectedCommand() == nullの場合、
            // 受付結果コード：sendSOAPメッセージ()の戻り値.getSendSyncRequestResult().getMajorStatusCode()
            // 受付結果詳細コード：sendSOAPメッセージ()の戻り値.getSendSyncRequestResult().getMinorStatusCode()
            else
            {
                l_strReturnValue = l_sendSyncRequestResult.value.getMajorStatusCode() + "";
               
                if (l_sendSyncRequestResult.value.getMinorStatusCode() != null)
                {
                    l_strReturnDetailValue = l_sendSyncRequestResult.value.getMinorStatusCode().intValue() + "";
                }
                else
                {
                    l_strReturnDetailValue = null;
                }
            }
            //getSOAPGFT受付結果コード(受付結果コード : String, 受付結果詳細コード : String)
            l_strSoapGFTResultCode = this.getSoapAcceptResultCode(l_strReturnValue, l_strReturnDetailValue);
        }
        catch (WEB3BaseException l_ex)
        {
            //”外部システム接続エラー”の例外をスローした場合は、
            if (WEB3ErrorCatalog.BUSINESS_ERROR_02398.equals(l_ex.getErrorInfo()))
            {
                //”GFT接続エラー”(00000990)、
                l_strSoapGFTResultCode = WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_00000990;
                // ”接続エラー（システムエラー）”(9991)
                l_strReturnValue = WEB3GftSoapResultCodeDef.CONNECT_ERROR;
            }
            //”通信エラー”の例外をスローした場合は、
            else if (WEB3ErrorCatalog.BUSINESS_ERROR_01802.equals(l_ex.getErrorInfo()))
            {
                //”GFTシステム起因エラー”(00000199)、
                l_strSoapGFTResultCode = WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_00000199;
                //”GFTインターナルエラー（システムエラー）”(9993)
                l_strReturnValue = WEB3GftSoapResultCodeDef.GFT_INTERNAL_ERROR;
            }
            //”ハッシュ認証エラー”の例外をスローした場合は、
            else if (WEB3ErrorCatalog.BUSINESS_ERROR_90224.equals(l_ex.getErrorInfo()))
            {
                //”ハッシュ値エラー”(00000909)、
                l_strSoapGFTResultCode = WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_00000909;
                // ”ハッシュ認証エラー”(9994)
                l_strReturnValue = WEB3GftSoapResultCodeDef.HASH_AUTH_ERROR;
            }
            else
            {
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        //resultCodeを設定する
        //this.接続結果明細には、対象を追加する。
        //　@key:　@外部接続.RESULT_CODE
        //　@value:　@getSOAPGFT受付結果コード()の戻り値
        this.connectionResultDetails.put(RESULT_CODE, l_strSoapGFTResultCode);

        //connectResultを設定する
        //this.接続結果明細には、対象を追加する。
        //　@key:　@外部接続.CONNECT_RESULT
        //　@value:　@sendSOAPメッセージ()の戻り値の受付結果コード ※
        //  ※受付結果詳細コードに値がある場合は、これを受付結果コードとする
        if (l_strReturnDetailValue != null  && !WEB3GftSoapResultCodeDef.NO_ERROR_CODE.equals(l_strReturnDetailValue))
        {
            this.connectionResultDetails.put(CONNECT_RESULT, l_strReturnDetailValue);
        }
        else
        {
            this.connectionResultDetails.put(CONNECT_RESULT, l_strReturnValue);
        }
        
        WEB3FXDataControlService l_fxDataControlService =
            (WEB3FXDataControlService)Services.getService(WEB3FXDataControlService.class);
        //get同時開設FXシステムコード(証券会社コード : String)
        String l_strValue = l_fxDataControlService.getSameTimeFxSystemCode(l_fxGftAskingTelegramUnit.institutionCode);
        
        //get会社別FXシステム条件(証券会社コード : String, 部店コード : String, FXシステムコード : String)
        //[引数]
        //証券会社コード：引数.GFT依頼電文明細.証券会社コード
        //部店コード：引数.GFT依頼電文明細.証券部店コード
        //FXシステムコード：引数.外部システムSOAPプリファ@レンス（RPC形式）.接続区分
        CompFxConditionParams l_compFxConditionParams;
        try
        {
            l_compFxConditionParams = l_fxDataControlService.getCompFxCondition(
                l_fxGftAskingTelegramUnit.institutionCode,
                l_fxGftAskingTelegramUnit.branchCode,
                l_prefRpcParams.getConnectDiv());
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        
        //受付結果コード = 00000000(処理完了)の場合
        if (WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000.equals(l_strSoapGFTResultCode))
        {
            //引数.GFT依頼電文明細.処理区分 = 01：口座開設の場合
            if (WEB3AdminAioGftOperationDivDef.ACCOUNT_OPEN.equals(l_fxGftAskingTelegramUnit.gftOperationDiv))
            {
                ResultInfoCreateUser l_resultInfoCreateUser =
                    (ResultInfoCreateUser)l_sendSyncRequestResult.value;
                if (l_resultInfoCreateUser != null && l_resultInfoCreateUser.getAccountIds() != null)
                {
                    //同時開設するFXシステムコード != nullの場合
                    if (l_strValue != null)
                    {
                        //fx_acc_01を設定する
                        this.connectionResultDetails.put(FX_ACC_01, l_resultInfoCreateUser.getAccountIds().get(0));
                        //fx_acc_10を設定する
                        this.connectionResultDetails.put(FX_ACC_10, l_resultInfoCreateUser.getAccountIds().get(1));
                        //cfd_accを設定する
                        this.connectionResultDetails.put(CFD_ACC, l_resultInfoCreateUser.getAccountIds().get(2));
                    }
                    else
                    {
                        //同時開設するFXシステムコード = nullの場合
                        //取得した会社別FXシステム条件Params.FXシステム区分がnullだった場合
                        if (l_compFxConditionParams.getFxSystemDiv() == null)
                        {
                            //fx_acc_01を設定する
                            this.connectionResultDetails.put(FX_ACC_01, l_resultInfoCreateUser.getAccountIds().get(0));
                            //fx_acc_10を設定する
                            this.connectionResultDetails.put(FX_ACC_10, l_resultInfoCreateUser.getAccountIds().get(1));
                        }
                        //取得した会社別FXシステム条件Params.FXシステム区分==”2”だった場合
                        else if (WEB3FxSystemDivDef.CFD_SYSTEM.equals(l_compFxConditionParams.getFxSystemDiv()))
                        {
                            //cfd_accを設定する
                            this.connectionResultDetails.put(CFD_ACC, l_resultInfoCreateUser.getAccountIds().get(0));
                        }
                    }
                }
            }
            //引数.GFT依頼電文明細.処理区分 = 03：口座追加の場合
            else if (WEB3AdminAioGftOperationDivDef.ADD_ACCOUNT.equals(l_fxGftAskingTelegramUnit.gftOperationDiv))
            {
                ResultInfoAddAccount l_resultInfoAddAccount =
                    (ResultInfoAddAccount)l_sendSyncRequestResult.value;
                if (l_resultInfoAddAccount != null && l_resultInfoAddAccount.getAccountId() != null)
                {
                    //取得した会社別FXシステム条件Params.FXシステム区分がnullだった場合
                    if (l_compFxConditionParams.getFxSystemDiv() == null)
                    {
                        //fx_acc_01を設定する
                        this.connectionResultDetails.put(FX_ACC_01, l_resultInfoAddAccount.getAccountId().get(0));
                        //fx_acc_10を設定する
                        this.connectionResultDetails.put(FX_ACC_10, l_resultInfoAddAccount.getAccountId().get(1));
                    }
                    //取得した会社別FXシステム条件Params.FXシステム区分==”2”だった場合
                    else if (WEB3FxSystemDivDef.CFD_SYSTEM.equals(l_compFxConditionParams.getFxSystemDiv()))
                    {
                        //cfd_accを設定する
                        this.connectionResultDetails.put(CFD_ACC, l_resultInfoAddAccount.getAccountId().get(0));
                    }
                }
            }
            //引数.GFT依頼電文明細.処理区分 = 07：振替可能額の場合
            else if (WEB3AdminAioGftOperationDivDef.TRANSFER_ABLE_AMT.equals(l_fxGftAskingTelegramUnit.gftOperationDiv))
            {
                //SendSyncRequestResponseから、LookupAccountInfoをループで取得する
                ResultInfoBase l_resultInfoBase = l_sendSyncRequestResult.value;
                ResultInfoLookupUser l_resultInfoLookupUser = (ResultInfoLookupUser)l_resultInfoBase;
                if (l_resultInfoLookupUser != null)
                {
                    LookupUserInfo l_lookupUserInfo = l_resultInfoLookupUser.getUserInfo();
                    List<LookupAccountInfo> l_lookUpAccInfos = l_lookupUserInfo.getAccounts();
                    if (l_lookUpAccInfos != null && l_lookUpAccInfos.size() > 0)
                    {
                        HashMap l_hmAmount = new HashMap();
                        String l_strAccountId;
                        for (int i = 0; i < l_lookUpAccInfos.size(); i++)
                        {
                            l_strAccountId = l_lookUpAccInfos.get(i).getAccountId() + "";
                            l_hmAmount.put(l_strAccountId, WEB3StringTypeUtility.formatNumber(l_lookUpAccInfos.get(i).getWithdrawableAmount()));
                        }
                        this.connectionResultDetails.put(AMOUNT, l_hmAmount);
                    }
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**<BR>
     * GFT接続システムの結果通知から、指定した項目名のValueを取得する。<BR>
     * <BR>
     * this.接続結果明細から、指定した項目名はKeyとして、Valueを返却する。<BR>
     * <BR>
     * @@param l_strName - (GFT項目名)<BR>
     * GFT電文の項目名<BR>
     * @@return Object
     */
    public Object getResult(String l_strName)
    {
        final String STR_METHOD_NAME = "getResult(String)";
        log.entering(STR_METHOD_NAME);

        Object l_result = null;
        //this.接続結果明細から、指定した項目名はKeyとして、Valueを返却する
        if (this.connectionResultDetails != null)
        {
            l_result = this.connectionResultDetails.get(l_strName);
        }

        log.exiting(STR_METHOD_NAME);
        return l_result;
    }

    /**
     * 世界標準時間を取得する<BR>
     * <BR>
     * １）java.util.Dateのインスタンスを生成する。<BR>
     * 　@TimeZoneに"GMT+0000"の型を設定<BR>
     * 　@SimpleDateFormatに"yyyyMMddHHmmssSSS"の型を設定<BR>
     * <BR>
     * ２）Stringの型で世界標準時間を返却する。<BR>
     * <BR>
     * @@return String
     */
    protected String getGMTtime()
    {
        final String STR_METHOD_NAME = "getGMTtime()";
        log.entering(STR_METHOD_NAME);

        //java.util.Dateのインスタンスを生成する
        Date l_date = new Date();

        //TimeZoneに"GMT+0000"の型を設定
        TimeZone l_timeZone = TimeZone.getTimeZone("GMT+0000");

        //SimpleDateFormatに"yyyyMMddHHmmssSSS"の型を設定
        SimpleDateFormat l_simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        l_simpleDateFormat.setTimeZone(l_timeZone);
        String l_strResultTime = l_simpleDateFormat.format(l_date);

        //Stringの型で世界標準時間を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_strResultTime;
    }
    
    /**
     * (validate接続可能)<BR>
     * SOAP接続確認を行う。<BR>
     * <BR>
     * 1）引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.エンドポイント名を分割し、接続先情報を取得する。<BR>
     * 　@ getEndpointName().split(arg0 : String)<BR>
     * 　@　@[引数]<BR>
     * 　@　@arg0： ";"<BR>
     * 2）プロキシ設定を行う。 <BR>
     * ＜外部システムSOAP接続プリファ@レンス（RPC形式）params.エンドポイントの分割数 == 3の場合＞ <BR>
     * 　@＜＜分割データの3件目の文字列に"https"が含まれる場合＞＞ <BR>
     * 　@　@ 下記の通りプロパティセットを行う。 <BR>
     * 　@　@（1）key : "https.proxyHost" <BR>
     * 　@　@ 　@ value : 1）で取得した接続先情報の配列第1要素 <BR>
     * 　@　@（2）key : "https.proxyPort" <BR>
     * 　@　@ 　@ value : 1）で取得した接続先情報の配列第2要素 <BR>
     * 　@＜＜上記の条件以外＞＞ <BR>
     * 　@　@ 下記の通りプロパティセットを行う。 <BR>
     * 　@　@（1）key : "http.proxyHost" <BR>
     * 　@　@ 　@ value : 1）で取得した接続先情報の配列第1要素 <BR>
     * 　@　@（2）key : "http.proxyPort" <BR>
     * 　@　@ 　@ value : 1）で取得した接続先情報の配列第2要素 <BR>
     * ＜外部システムSOAP接続プリファ@レンス（RPC形式）params.エンドポイントの分割数 == 1の場合＞ <BR>
     * 　@ 処理なし <BR>
     * ＜上記の条件以外＞ <BR>
     * 　@ 例外をスローする。 <BR>
     * <BR>
     * 3）サービス実装クラスのインスタンスを生成する。 <BR>
     * AdministrativeAPI_Impl() <BR>
     * <BR>
     * 4）サービススタブを生成する。 <BR>
     * service.getAdministrativeAPIPort(arg0 : String) <BR>
     * [引数] <BR>
     * arg0 : 引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.サービス名<BR> 
     * <BR>
     * 5）サービススタブにエンドポイントを設定する。 <BR>
     * _setProperty(arg0 : String, arg1 : Object) <BR>
     * [引数] <BR>
     * arg0： "javax.xml.rpc.service.endpoint.address" <BR>
     * arg1： －外部システムSOAP接続プリファ@レンス（RPC形式）params.エンドポイントの分割数 == 3の場合 <BR>
     * 1）で取得した接続先情報の配列第3要素 <BR>
     * －外部システムSOAP接続プリファ@レンス（RPC形式）params.エンドポイントの分割数 == 1の場合 <BR>
     * 1）で取得した接続先情報の配列第1要素 <BR>
     * <BR>
     * 6）サービススタブに接続タイムアウト時間をセットする。 <BR>
     * _setProperty(arg0 : String, arg1 : Object) <BR>
     * [引数] <BR>
     * <BR>
     * 7）サービススタブより、isOK()をコールする。 <BR>
     * adminAPI_Stub.isOk() <BR>
     * <BR>
     * 8) isOK()の戻り値がfalseの場合、"通信エラー"をスローする。 <BR>
     * <BR>
     * @@param l_prefRpcParams - (外部システムSOAPプリファ@レンス（RPC形式)params)<BR>
     * 外部システムSOAPプリファ@レンス（RPC形式)<BR>
     * @@throws WEB3BaseException
     */
    protected void validateConnect(SoapConnectPrefRpcParams l_rpcParams) 
        throws WEB3BaseException{

      String STR_METHOD_NAME = 
        "validateConnect(SoapConnectPrefRpcParams)";
      log.entering(STR_METHOD_NAME);

      boolean l_checkFlg = false;      
      
      
      // 1）引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.エンドポイント名を分割し、接続先情報を取得する。
      // 　@ getEndpointName().split(arg0 : String)
      // 　@　@[引数]
      // 　@　@arg0： ";"
      String l_urlArr[] = l_rpcParams.getEndpointName().split(";");
      String l_protocolStr = "";
      
      String l_logMessage = "";
      
      // 2）プロキシ設定を行う。
      //　@＜外部システムSOAP接続プリファ@レンス（RPC形式）params.エンドポイントの分割数 == 3の場合＞
      //   　@＜＜分割データの3件目の文字列に"https"が含まれる場合＞＞
      // 　@　@ 下記の通りプロパティセットを行う。
      // 　@　@（1）key   : "https.proxyHost"
      // 　@　@ 　@  value : 1）で取得した接続先情報の配列第1要素
      // 　@　@（2）key   : "https.proxyPort"
      // 　@　@ 　@  value : 1）で取得した接続先情報の配列第2要素
      //   　@＜＜上記の条件以外＞＞
      // 　@　@ 下記の通りプロパティセットを行う。
      // 　@　@（1）key   : "http.proxyHost"
      // 　@　@ 　@  value : 1）で取得した接続先情報の配列第1要素
      // 　@　@（2）key   : "http.proxyPort"
      // 　@　@ 　@  value : 1）で取得した接続先情報の配列第2要素
      //　@＜外部システムSOAP接続プリファ@レンス（RPC形式）params.エンドポイントの分割数 == 1の場合＞
      //　@　@処理なし
      //  ＜上記の条件以外＞
      //　@　@例外をスローする。
      if(l_urlArr.length == 3)
      {
      
        l_protocolStr = l_urlArr[2];
        
          if(l_protocolStr.trim().indexOf("https") >= 0)
          {
            System.setProperty("https.proxyHost", l_urlArr[0].trim());
            System.setProperty("https.proxyPort", l_urlArr[1].trim());
            l_logMessage = 
                "\nhttps.proxyHost (" + l_urlArr[0] + ")" +
                "\nhttps.proxyPort (" + l_urlArr[1] + ")" ;
          }
          else
          {
            System.setProperty("http.proxyHost", l_urlArr[0].trim());
            System.setProperty("http.proxyPort", l_urlArr[1].trim());
            l_logMessage = 
                "\nhttp.proxyHost (" + l_urlArr[0] + ")" +
                "\nhttp.proxyPort (" + l_urlArr[1] + ")";
          }
      
          log.debug(l_logMessage);
          
      }
      else if (l_urlArr.length == 1)
      {
      }
      else
      {
          l_logMessage = 
              "外部システムSOAP接続プリファ@レンス(RPC形式).エンドポイント名" + 
              "のセクション数が、異なっています。\n" +
              "「proxy-host;proxy-port;protocol」でセットしてください。";
          throw new WEB3BusinessLayerException(
              WEB3ErrorCatalog.BUSINESS_ERROR_02398,
              this.getClass().getName() + "." + STR_METHOD_NAME,
              l_logMessage
              );
      }

        // 3）サービス実装クラスのインスタンスを生成する。
        //    AdministrativeAPI_Impl()
    	  AdministrativeAPI service = new AdministrativeAPI();
        
        // 4）サービススタブを生成する。
        //    service.getAdministrativeAPIPort(arg0 : String)
        //     [引数]
        //      arg0 : 引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.サービス名
    	  AdministrativeAPIPort adminAPI_Stub = 
              (AdministrativeAPIPort) service.getAdministrativeAPIPort(l_rpcParams.service_name);
        
        
        // 5）サービススタブにエンドポイントを設定する。
        String l_strParameter = null;
        if (l_urlArr.length == 3)
        {
            l_strParameter = l_urlArr[2];
        }
        else if (l_urlArr.length == 1)
        {
            l_strParameter = l_urlArr[0];
        }
        BindingProvider bp = (BindingProvider)adminAPI_Stub;
        
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, l_strParameter);

        // 6）接続タイムアウト時間をセットする。
        //    setProperty(arg0 : String, arg1 : Object)
        //     [引数] 
        //1 minute for connection ((BindingProvider)
        bp.getRequestContext().put("com.sun.xml.internal.ws.connect.timeout", l_rpcParams.response_timeout);
        //3 minutos for request ((BindingProvider)
        bp.getRequestContext().put("com.sun.xml.internal.ws.request.timeout", l_rpcParams.response_timeout);

        // 7）サービススタブより、isOK()をコールする。
        //    adminAPI_Stub.isOk()
        l_checkFlg = adminAPI_Stub.isOk();
        

      // 8) isOK()の戻り値がfalseの場合、"通信エラー"をスローする。
      if ( l_checkFlg ) {
          log.debug("\n" + "isOK() チェックフラグ = true");
          log.exiting(STR_METHOD_NAME);
      }else{
          log.debug("\n" + "isOK() チェックフラグ = false");
          log.exiting(STR_METHOD_NAME);
          // 通信エラー
          throw new WEB3BusinessLayerException(
              WEB3ErrorCatalog.BUSINESS_ERROR_01802,
              this.getClass().getName() + STR_METHOD_NAME);
      }
      
    }

    /**
     * (getHash値) <BR>
     * SOAP接続に引き渡すためのハッシュ値を取得する。 <BR>
     * <BR>
     * １）　@64バイトの領域を確保する。<BR>
     * ２）　@以下のように文字列を連結する。 <BR>
     *       引数.世界標準時間＋引数.ログイン <BR>
     * ３）　@上記の結果を16進数バイト配列に変換し、１）で確保した領域に格納する。 <BR>
     * ４）　@上記の結果に対してSHA_1を用いてハッシュ計算する。 <BR>
     * ５）　@引数.シークレットキーをBASE64でデコードし、バイト配列に格納する。 <BR>
     * ６）　@上記結果に４）の結果を足す。 <BR>
     * ７）　@上記の結果に対してSHA_1を用いてハッシュ計算する。 <BR>
     * ８）　@上記の結果をBASE64でエンコードし、返却する。 <BR>
     * <BR>
     * @@param l_worldTime 世界標準時間
     * @@param l_login ログイン
     * @@param l_secretKey シークレットキー 
     * @@return String
     * @@throws WEB3BaseException
     */
    protected String getHashValue(
            String l_worldTime,
            String l_login, 
            String l_secretKey) throws WEB3BaseException {
        
        String STR_METHOD_NAME = 
            "getHashValue(String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        // １）　@64バイトの領域を確保する。 
        byte l_timeLoginByte[] = new byte[64];
        byte l_timeLoginByte64[] = new byte[64];
        
        // ２）　@以下のように文字列を連結する。 
        //     　@引数.世界標準時間＋引数.ログイン 
        String l_timeLogin = l_worldTime.concat(l_login);
        
        log.debug("\n" + "getHashValue() - 連結文字列（世界標準時間+ログイン）：" + l_timeLogin);
        
        // ３）　@上記の結果を16進数バイト配列に変換し、１）で確保した領域に格納する。 
        l_timeLoginByte = l_timeLogin.getBytes();
        
        for(int i=0;i<64;i++){
            if(i<l_timeLoginByte.length){
                l_timeLoginByte64[i] = l_timeLoginByte[i];
            }
        }
        
        // ４）　@上記の結果に対してSHA_1を用いてハッシュ計算する。 
        MessageDigest l_messageDigest = null;
        try
        {
            // SHA_1を指定
            l_messageDigest = MessageDigest.getInstance(WEB3AioHashAlgorithmDef.SHA_1);
        }
        catch(NoSuchAlgorithmException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //MessageDigestオブジェクト.update()をコール
        l_messageDigest.update(l_timeLoginByte64);
        
        //MessageDigestオブジェクト.digest()をコール
        byte[] l_byteResult = l_messageDigest.digest();
        
        // ５）　@引数.シークレットキーをBASE64でデコードし、バイト配列に格納する。
        BASE64Decoder l_decoder = new BASE64Decoder();
        byte[] l_decodedSecretKey = new byte[64];
        
        try
        {
            l_decodedSecretKey = l_decoder.decodeBuffer(l_secretKey);
        }
        catch (IOException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        // ６）　@上記結果に４）の結果を足す。 
        for(int i=0;i<l_byteResult.length;i++){
            l_decodedSecretKey[i] += l_byteResult[i];
        }
        
        // ７）　@上記の結果に対してSHA_1を用いてハッシュ計算する。
        //MessageDigestオブジェクト.update()をコール
        l_messageDigest.update(l_decodedSecretKey);
        
        //MessageDigestオブジェクト.digest()をコール
        byte[] l_hashResult = l_messageDigest.digest();
        
        // ８）　@上記の結果をBASE64でエンコードし、返却する。
        BASE64Encoder l_encoder = new BASE64Encoder();
        
        String l_hashResult64 = l_encoder.encode(l_hashResult);
        
        log.exiting(STR_METHOD_NAME);
              
        return l_hashResult64;
    }

    /**
     * (sendSOAPメッセージ) <BR>
     * SOAP接続を行う。<BR>
     * <BR>
     * 1）証券会社IDを取得する。<BR>
     * <BR>
     * 2）AuthTokenインスタンスを生成する。<BR>
     * <BR>
     * 　@　@　@※生成したAuthTokenインスタンスの項目を以下の通りセットする。<BR>
     * 　@　@　@ログイン：引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.ポートタイプ名<BR>
     * 　@　@　@タイムスタンプ：引数.世界標準時間<BR>
     * 　@　@　@ハッシュ：引数.ハッシュ値<BR>
     * <BR>
     * 3）リクエストデータを生成する。<BR>
     * <BR>
     * 　@　@＜引数.電文明細.処理区分 == "01"(新規開設)の場合＞<BR>
     * 　@　@　@　@　@（1）UserPersonalInfoインスタンスを生成する。<BR>
     * 　@　@　@　@　@　@　@※生成したUserPersonalInfoインスタンスの項目を以下の通りセットする。<BR>
     * 　@　@　@　@　@　@　@名前(姓)：引数.GFT依頼電文明細.名前(姓)<BR>
     * 　@　@　@　@　@　@　@メールアドレス：引数.GFT依頼電文明細.メールアドレス<BR>
     * 　@　@　@　@　@（2）UserSystemInfoインスタンスを生成する。<BR>
     * 　@　@　@　@　@　@　@※生成したUserSystemInfoインスタンスの項目を以下の通りセットする。<BR>
     * 　@　@　@　@　@　@　@FXログインID：引数.GFT依頼電文明細.初期ログインID<BR>
     * 　@　@　@　@　@　@　@パスワード：引数.GFT依頼電文明細.パスワード<BR>
     * 　@　@　@　@　@（3）引数.外部システムSOAP接続プリファ@レンス(RPC形式)params.パラメータタイプリストに設定されている<BR>
     * 　@　@　@　@　@　@　@各アカウントテンプレートをインスタンスへセットする。<BR>
     * 　@　@　@　@　@　@　@下記処理をコロン区切りで指定されている回数実施する。<BR>
     * 　@　@　@　@　@　@　@（3-1）CreateAccountInfoインスタンスを生成する。<BR>
     * 　@　@　@　@　@　@　@　@　@　@※生成したCreateAccountInfoインスタンスの項目を以下の通りセットする。<BR>
     * 　@　@　@　@　@　@　@　@　@　@アカウントテンプレートID：引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.パラメータタイプリスト.第n項目<BR>
     * 　@　@　@　@　@（4）CreateAccountInfoのオブジェクト配列を生成し、<BR>
     * 　@　@　@　@　@　@　@(3)で生成したCreateAccountInfoインスタンスを格納する。<BR>
     * 　@　@　@　@　@（5）CreateUserInfoインスタンスを生成する。<BR>
     * 　@　@　@　@　@　@　@CreateUserInfo(arg0：String, arg1：UserPersonalInfo, arg2：UserSystemInfo, arg3：CreateAccountInfo[])<BR>
     * 　@　@　@　@　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@　@　@　@arg0：引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.パラメータリスト<BR>
     * 　@　@　@　@　@　@　@　@　@arg1：（1）で生成したUserPersonalInfoインスタンス<BR>
     * 　@　@　@　@　@　@　@　@　@arg2：（2）で生成したUserSystemInfoインスタンス<BR>
     * 　@　@　@　@　@　@　@　@　@arg3：（5）で生成したCreateAccountInfo[]インスタンス<BR>
     * 　@　@　@　@　@（6）CommandCreateUserインスタンスを生成する。<BR>
     * 　@　@　@　@　@　@　@CommandCreateUser(arg0 : String, arg1 : CreateUserInfo)<BR>
     * 　@　@　@　@　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@　@　@　@arg0： 1）で取得した証券会社ID + 引数.GFT依頼電文明細.識別コード<BR>
     * 　@　@　@　@　@　@　@　@　@arg1： 生成したCreateUserInfoインスタンス<BR>
     * 　@　@　@　@　@（7）SendSyncRequestインスタンスを生成する。<BR>
     * 　@　@　@　@　@　@　@SendSyncRequest(arg0 : CommandBase, arg1 : AuthToken)<BR>
     * 　@　@　@　@　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@　@　@　@arg0：（6）で生成したCommandCreateUserインスタンス<BR>
     * 　@　@　@　@　@　@　@　@　@arg1： 2)で生成したAuthTokenインスタンス<BR>
     * <BR>
     * 　@　@＜引数.電文明細.処理区分 == "03"(追加開設)の場合＞<BR>
     * 　@　@　@　@　@（1）引数.外部システムSOAP接続プリファ@レンス(RPC形式)params.パラメータタイプリストに設定されている<BR>
     * 　@　@　@　@　@　@　@各アカウントテンプレートをインスタンスへセットする。<BR>
     * 　@　@　@　@　@　@　@下記処理をコロン区切りで指定されている回数実施する。<BR>
     * 　@　@　@　@　@　@　@（3-1）CreateAccountInfoインスタンスを生成する。<BR>
     * 　@　@　@　@　@　@　@　@　@※生成したCreateAccountInfoインスタンスの項目を以下の通りセットする。<BR>
     * 　@　@　@　@　@　@　@　@　@アカウントテンプレートID：引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.パラメータタイプリスト.第n項目<BR>
     * 　@　@　@　@　@（2）CreateAccountInfoのオブジェクト配列を生成し、<BR>
     * 　@　@　@　@　@　@　@(1)で生成したCreateAccountInfoインスタンスを格納する。<BR>
     * 　@　@　@　@　@（3）CommandAddAccountインスタンスを生成する。<BR>
     * 　@　@　@　@　@　@　@CommandAddAccount(arg0 : String, arg1 : String, arg2 : CreateAccountInfo[])<BR>
     * 　@　@　@　@　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@　@　@　@arg0： 1）で取得した証券会社ID + 引数.GFT依頼電文明細.識別コード<BR>
     * 　@　@　@　@　@　@　@　@　@arg1： 引数.GFT依頼電文明細.初期ログインID<BR>
     * 　@　@　@　@　@　@　@　@　@arg2： (2)で生成したCreateAccountInfo[]インスタンス<BR>
     * 　@　@　@　@　@（4）SendSyncRequestインスタンスを生成する。<BR>
     * 　@　@　@　@　@　@　@SendSyncRequest(arg0 : CommandBase, arg1 : AuthToken)<BR>
     * 　@　@　@　@　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@　@　@　@arg0：（3）で生成したCommandAddAccountインスタンス<BR>
     * 　@　@　@　@　@　@　@　@　@arg1： 2)で生成したAuthTokenインスタンス<BR>
     * <BR>
     * 　@　@＜引数.電文明細.処理区分 == "04：出金"の場合(FXへの振替の場合）＞<BR>
     * 　@　@　@（1）CommandDepositインスタンスを生成する。<BR>
     * 　@　@　@　@　@CommandDeposit(arg0 : String, arg1 : long, arg2 : double, arg3 : String)<BR>
     * 　@　@　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@　@arg0： 1）で取得した証券会社ID + 引数.GFT依頼電文明細.識別コード<BR>
     * 　@　@　@　@　@　@　@arg1： 引数.GFT依頼電文明細.為替保証金口座番号<BR>
     * 　@　@　@　@　@　@　@arg2： 引数.GFT依頼電文明細.入出金額<BR>
     * 　@　@　@　@　@　@　@arg3： "JPY"<BR>
     * 　@　@　@（2）SendSyncRequestインスタンスを生成する。<BR>
     * 　@　@　@　@　@SendSyncRequest(arg0 : CommandBase, arg1 : AuthToken)<BR>
     * 　@　@　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@　@arg0： （1）で生成したCommandDepositインスタンス<BR>
     * 　@　@　@　@　@　@　@arg1： 2)で生成したAuthTokenインスタンス<BR>
     * <BR>
     * 　@　@＜引数.電文明細.処理区分 == "02：入金"の場合(FXから振替の場合）＞<BR>
     * 　@　@　@（1）CommandWithdrawインスタンスを生成する。<BR>
     * 　@　@　@　@　@CommandWithdraw(arg0 : String, arg1 : long, arg2 : double, arg3 : String)<BR>
     * 　@　@　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@　@arg0： 1）で取得した証券会社ID + 引数.GFT依頼電文明細.識別コード<BR>
     * 　@　@　@　@　@　@　@arg1： 引数.GFT依頼電文明細.為替保証金口座番号<BR>
     * 　@　@　@　@　@　@　@arg2： 引数.GFT依頼電文明細.入出金額<BR>
     * 　@　@　@　@　@　@　@arg3： "JPY"<BR>
     * 　@　@　@（2）SendSyncRequestインスタンスを生成する。<BR>
     * 　@　@　@　@　@SendSyncRequest(arg0 : CommandBase, arg1 : AuthToken)<BR>
     * 　@　@　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@　@arg0： （1）で生成したCommandWithdrawインスタンス<BR>
     * 　@　@　@　@　@　@　@arg1： 2)で生成したAuthTokenインスタンス<BR>
     * <BR>
     * 　@　@＜引数.電文明細.処理区分 == "07：振替可能額"の場合＞<BR>
     * 　@　@　@（1）CommandLookupUserインスタンスを生成する。<BR>
     * 　@　@　@　@　@CommandLookupUser(arg0 : String, arg1 : String)<BR>
     * 　@　@　@　@　@　@　@arg0： 1）で取得した証券会社ID + 引数.GFT依頼電文明細.識別コード<BR>
     * 　@　@　@　@　@　@　@arg1： 引数.GFT依頼電文明細.初期ログインID<BR>
     * 　@　@　@（2）SendSyncRequestインスタンスを生成する。<BR>
     * 　@　@　@　@　@SendSyncRequest(arg0 : CommandBase, arg1 : AuthToken)<BR>
     * 　@　@　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@　@arg0： （1）で生成したCommandLookupUserインスタンス<BR>
     * 　@　@　@　@　@　@　@arg1： 生成したAuthTokenインスタンス<BR>
     * <BR>
     * 4）サービス実装クラスを生成する。<BR>
     * <BR>
     * 　@ AdministrativeAPI_Impl()<BR>
     * <BR>
     * 5）サービススタブを生成する。<BR>
     * <BR>
     * 　@　@service.getAdministrativeAPIPort(arg0 : String)<BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@　@arg0 : 引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.サービス名<BR>
     * <BR>
     * 6）サービススタブにエンドポイントを設定する。<BR>
     * 　@　@_setProperty(arg0 : String, arg1 : Object)<BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@　@arg0： "javax.xml.rpc.service.endpoint.address"<BR>
     * 　@　@　@　@arg1： －引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.エンドポイント名の分割数 == 3の場合<BR>
     * 引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.エンドポイント名の配列第3要素<BR>
     * －引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.エンドポイント名の分割数 == 1の場合<BR>
     * 引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.エンドポイント名の配列第1要素<BR>
     * <BR>
     * 　@　@　@　@　@　@※上記の条件以外の場合、例外をスローする。<BR>
     * 　@　@　@　@class：　@WEB3BusinessLayerException
     * 　@　@　@　@tag:　@　@　@BUSINESS_ERROR_02398<BR>
     * <BR>
     * 7）接続タイムアウト時間をセットする。<BR>
     * <BR>
     * 　@　@_setProperty(arg0 : String, arg1 : Object)<BR>
     * 　@　@　@[引数]<BR>
     * <BR>
     * 8）SOAPメッセージハンドラをセットする。 
     * 
     * 　@　@(1)Web サービス ポートの修飾名を格納するオブジェクトを作成 
     * 　@　@　@　@QName( arg0 : l_rpcParams.target_namespace_name, arg1 : l_rpcParams.service_name) 
     * 　@　@　@　@　@　@[引数] 
     * 　@　@　@　@　@　@　@arg0： 引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.ターゲットネームスペース名 
     * 　@　@　@　@　@　@　@arg1： 引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.サービス名 
     * <BR>
     * 　@　@(2)HandlerRegistry オブジェクトを作成 
     * <BR>
     * 　@　@(3)HandlerInfoに設定するconfig(：Map)データの作成 
     * <BR>
     * 　@　@　@　@[put()に指定する引数] 
     * 　@　@　@　@　@key："BranchId" 
     * 　@　@　@　@　@value：引数.外部システムSOAP接続プリファ@レンス（RPC形式）params. 部店ID 
     * 　@　@　@　@[put()に指定する引数] 
     * 　@　@　@　@　@key："ConnectDiv" 
     * 　@　@　@　@　@value：引数.外部システムSOAP接続プリファ@レンス（RPC形式）params. 接続区分 
     * <BR>
     * 　@　@(4)SOAPメッセージのハンドラ チェーン(：ArrayList)生成 
     * <BR>
     * 　@　@　@　@[add()に指定する引数] 
     * 　@　@　@　@　@new HandlerInfo( arg0 : WEB3FXSOAPLogHandler.class, arg1 : map, arg2 : null ) 
     * 　@　@　@　@　@　@[引数] 
     * 　@　@　@　@　@　@　@arg0： WEB3FXSOAPLogHandler.class 
     * 　@　@　@　@　@　@　@arg1： (3)で生成したMapオブジェクト 
     * 　@　@　@　@　@　@　@arg2： null 
     * <BR>
     * 　@　@(5)ハンドラ チェーンを登録 
     * 　@　@　@　@setHandlerChain( arg0 : portName, arg1 : handlerList ) 
     * 　@　@　@　@　@　@[引数] 
     * 　@　@　@　@　@　@　@arg0： (1)で生成したオブジェクト 
     * 　@　@　@　@　@　@　@arg1： (4)で生成したArrayList 
     * <BR>
     * 9）メッセージ送受信<BR>
     * <BR>
     * 　@　@regSoap_Stub.sendSyncRequest(arg0 : SendSyncRequest)<BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@　@arg0 : 3）で生成したリクエストデータ<BR>
     * <BR>
     * 10）9）で受信したSOAP接続レスポンスデータを返却する。<BR>
     * <BR>
     * @@param l_fxGftAskingTelegramUnit GFT依頼電文明細
     * @@param l_rpcParams 外部システムSOAP接続プリファ@レンス（RPC形式）params
     * @@param l_worldTime 世界標準時間
     * @@param l_hashValue ハッシュ値
     * @@return SendSyncRequestResponse
     * @@throws WEB3BaseException
     */
    protected void sendSoapMessage(
            WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit,
            SoapConnectPrefRpcParams l_rpcParams,
            String l_worldTime,
            String l_hashValue,
            Holder<RejectedCommand> l_rejectedCommand,
            Holder<ResultInfoBase> l_sendSyncRequestResult) throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "sendSoapMessage(WEB3FXGftAskingTelegramUnit, " +
            "SoapConnectPrefRpcParams, String, String)";
        log.entering(STR_METHOD_NAME);

        //拡張アカウントマネージャ取得する
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        //1）証券会社IDを取得する。
        String l_strInstitutionId = null;
        try{
            Institution l_Instituion = l_accountManager.getInstitution(
                l_fxGftAskingTelegramUnit.institutionCode);
            l_strInstitutionId = String.valueOf(l_Instituion.getInstitutionId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // ログインを取得
        String l_login = l_rpcParams.port_type_name;
        
        // 2）AuthTokenインスタンスを生成する。
        //    ※生成したAuthTokenインスタンスの項目を以下の通りセットする。
        //  　@　@ログイン：引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.ポートタイプ名
        //  　@　@タイムスタンプ：引数.世界標準時間
        //　@　@　@ハッシュ：引数.ハッシュ値
        AuthToken l_token = new AuthToken();
        l_token.setLogin(l_login);
        l_token.setTimestamp(l_worldTime);
        l_token.setHash(l_hashValue);
        
        // コマンドID作成（ 1）で取得した証券会社ID + 引数.GFT依頼電文明細.識別コード）
        String l_commandId = 
            l_strInstitutionId.concat(l_fxGftAskingTelegramUnit.requestNumber);
        
        CommandBase l_commandBase = new CommandBase();
        
        // 3）リクエストデータを生成する。
        // ＜引数.電文明細.処理区分 == "01"(新規開設)の場合＞
        if(WEB3AdminAioGftOperationDivDef.ACCOUNT_OPEN.equals(
            l_fxGftAskingTelegramUnit.gftOperationDiv))
        {
            
            // （1）UserPersonalInfoインスタンスを生成する。
            //      ※生成したUserPersonalInfoインスタンスの項目を以下の通りセットする。
            //        名前(姓)：引数.GFT依頼電文明細.名前(姓)
            //        メールアドレス：引数.GFT依頼電文明細.メールアドレス
            UserPersonalInfo l_userPersonalInfo = new UserPersonalInfo();
            l_userPersonalInfo.setLastName(l_fxGftAskingTelegramUnit.fxLastName);
            l_userPersonalInfo.setEmail(l_fxGftAskingTelegramUnit.fxMailAddress);
            
            // （2）UserSystemInfoインスタンスを生成する。
            //      ※生成したUserSystemInfoインスタンスの項目を以下の通りセットする。
            //        FXログインID：引数.GFT依頼電文明細.初期ログインID
            //        パスワード：引数.GFT依頼電文明細.パスワード
            UserSystemInfo l_userSystemInfo = new UserSystemInfo();
            l_userSystemInfo.setLogin(l_fxGftAskingTelegramUnit.fxFirstLoginId);
            l_userSystemInfo.setPassword(l_fxGftAskingTelegramUnit.fxFirstPassword);
            
            // （3）引数.外部システムSOAP接続プリファ@レンス(RPC形式)params.パラメータタイプリストに設定されている
            //各アカウントテンプレートをインスタンスへセットする。
            ArrayList l_lisCreateAccountInfos = new ArrayList();
            String l_strParameterTypeList = l_rpcParams.getParameterTypeList();
            String[] l_strParameterTypes = null;
            int l_intParameterTypesLength = 0;
            if (!WEB3StringTypeUtility.isEmpty(l_strParameterTypeList))
            {
                l_strParameterTypes =
                    l_strParameterTypeList.split(":");
                l_intParameterTypesLength = l_strParameterTypes.length;
            }

            //下記処理をコロン区切りで指定されている回数実施する。
            //（3-1）CreateAccountInfoインスタンスを生成する。
            //※生成したCreateAccountInfoインスタンスの項目を以下の通りセットする。
            //アカウントテンプレートID：
            //引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.パラメータタイプリスト.第n項目
            for (int i = 0; i< l_intParameterTypesLength; i++)
            {
                CreateAccountInfo l_createAccountInfo = new CreateAccountInfo();
                l_createAccountInfo.setAccountTemplateId(
                    l_strParameterTypes[i]);
                l_lisCreateAccountInfos.add(l_createAccountInfo);
            }

            // （4）CreateAccountInfoのオブジェクト配列を生成し
            //(3)で生成したCreateAccountInfoインスタンスを格納する。
            CreateAccountInfo[] l_createAccountInfos =
                new CreateAccountInfo[l_intParameterTypesLength];
            l_lisCreateAccountInfos.toArray(l_createAccountInfos);
            
            // （5）CreateUserInfoインスタンスを生成する。
            //      CreateUserInfo(arg0：String, arg1：UserPersonalInfo, arg2：UserSystemInfo, arg3：CreateAccountInfo[])
            //       [引数]
            //        arg0：引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.パラメータリスト
            //        arg1：（1）で生成したUserPersonalInfoインスタンス
            //        arg2：（2）で生成したUserSystemInfoインスタンス
            //        arg3：（5）で生成したCreateAccountInfo[]インスタンス
            CreateUserInfo l_createUserInfo = new CreateUserInfo();
            l_createUserInfo.setUserTemplateId(l_rpcParams.parameter_list);
            l_createUserInfo.setUserPersonalInfo(l_userPersonalInfo);
            l_createUserInfo.setUserSystemInfo(l_userSystemInfo);
            l_createUserInfo.setAccounts(l_lisCreateAccountInfos);
                    
            // （6）CommandCreateUserインスタンスを生成する。
            //      CommandCreateUser(arg0 : String, arg1 : CreateUserInfo)
            // 　@    [引数] 
            //  　@    arg0： 1）で取得した証券会社ID + 引数.GFT依頼電文明細.識別コード
            //  　@    arg1： 生成したCreateUserInfoインスタンス
            CommandCreateUser l_commandCreateUser = new CommandCreateUser();
            l_commandCreateUser.setCommandID(l_commandId);
            l_commandCreateUser.setUserInfo(l_createUserInfo);
            
            // （7）SendSyncRequestインスタンスを生成する。
            //      SendSyncRequest(arg0 : CommandBase, arg1 : AuthToken)
            // 　@    [引数]
            //  　@    arg0： （6）で生成したCommandCreateUserインスタンス
            //　@　@    arg1： 2)で生成したAuthTokenインスタンス
            l_commandBase = l_commandCreateUser;
            
            // debug
            log.debug("\n" + "*****CreateUserパラメータ*****" +
                      "\n" + "コマンドID(会社ID+識別コード)：" + l_commandId +
                      "\n" + "コマンドID：" + l_commandCreateUser.getCommandID() + 
                      "\n" + "名前(姓)：" + l_userPersonalInfo.getLastName() +
                      "\n" + "メールアドレス：" + l_userPersonalInfo.getEmail() +
                      "\n" + "FXログインID：" + l_fxGftAskingTelegramUnit.fxFirstLoginId +
                      "\n" + "Password：" + l_fxGftAskingTelegramUnit.fxFirstPassword);
            
        }

        //＜引数.電文明細.処理区分 == "03"(追加開設)の場合＞
        else if (WEB3AdminAioGftOperationDivDef.ADD_ACCOUNT.equals(
            l_fxGftAskingTelegramUnit.gftOperationDiv))
        {
            //（1）引数.外部システムSOAP接続プリファ@レンス(RPC形式)params.パラメータタイプリストに設定されている
            //各アカウントテンプレートをインスタンスへセットする。
            ArrayList l_lisCreateAccountInfos = new ArrayList();
            String l_strParameterTypeList = l_rpcParams.getParameterTypeList();
            String[] l_strParameterTypes = null;
            int l_intParameterTypesLength = 0;
            if (!WEB3StringTypeUtility.isEmpty(l_strParameterTypeList))
            {
                l_strParameterTypes =
                    l_strParameterTypeList.split(":");
                l_intParameterTypesLength = l_strParameterTypes.length;
            }

            //下記処理をコロン区切りで指定されている回数実施する。
            //（3-1）CreateAccountInfoインスタンスを生成する。
            //※生成したCreateAccountInfoインスタンスの項目を以下の通りセットする。
            //アカウントテンプレートID：
            //引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.パラメータタイプリスト.第n項目
            for (int i = 0; i< l_intParameterTypesLength; i++)
            {
                CreateAccountInfo l_createAccountInfo = new CreateAccountInfo();
                l_createAccountInfo.setAccountTemplateId(
                    l_strParameterTypes[i]);
                l_lisCreateAccountInfos.add(l_createAccountInfo);
            }

            //（2）CreateAccountInfoのオブジェクト配列を生成し、
            //(1)で生成したCreateAccountInfoインスタンスを格納する。
            CreateAccountInfo[] l_createAccountInfos =
                new CreateAccountInfo[l_intParameterTypesLength];
            l_lisCreateAccountInfos.toArray(l_createAccountInfos);

            //（3）CommandAddAccountインスタンスを生成する。
            //CommandAddAccount(arg0 : String, arg1 : String, arg2 : CreateAccountInfoCreateAccountInfo)
            //[引数]
            //arg0： 1）で取得した証券会社ID + 引数.GFT依頼電文明細.識別コード
            //arg1： 引数.GFT依頼電文明細.初期ログインID
            //arg2： (2)で生成したCreateAccountInfo[]インスタンス
            CommandAddAccount l_commandAddAccount = new CommandAddAccount();
            l_commandAddAccount.setCommandID(l_commandId);
            l_commandAddAccount.setLogin(l_fxGftAskingTelegramUnit.fxFirstLoginId);
            l_commandAddAccount.setAccount(l_lisCreateAccountInfos);

            //（4）SendSyncRequestインスタンスを生成する。
            //SendSyncRequest(arg0 : CommandBase, arg1 : AuthToken)
            //[引数]
            //arg0：（3）で生成したCommandAddAccountインスタンス
            //arg1： 2)で生成したAuthTokenインスタンス
            l_commandBase = l_commandAddAccount;
        }

        // ＜引数.電文明細.処理区分 == "04：出金"の場合(FXへの振替の場合）＞
        else if(WEB3AdminAioGftOperationDivDef.CASH_OUT.equals(
            l_fxGftAskingTelegramUnit.gftOperationDiv))
        {
            
            // 為替保証金口座番号を取得
            long l_accountId = Long.parseLong(l_fxGftAskingTelegramUnit.fxAccountCode);
            
            // 入出金額を取得
            double l_amount = Double.parseDouble(l_fxGftAskingTelegramUnit.cashinoutAmt);

            // （1）CommandDepositインスタンスを生成する。
            //      CommandDeposit(arg0 : String, arg1 : long, arg2 : double, arg3 : String)
            // 　@    [引数] 
            //      　@arg0： 1）で取得した証券会社ID  + 引数.GFT依頼電文明細.識別コード
            //  　@  　@arg1： 引数.GFT依頼電文明細.為替保証金口座番号
            //　@　@  　@arg2： 引数.GFT依頼電文明細.入出金額
            //　@　@  　@arg3： "JPY"
            CommandDeposit l_commandDeposit = new CommandDeposit();
            //l_commandId
            l_commandDeposit.setCommandID(l_commandId);
            //l_accountId
            l_commandDeposit.setAccountID(l_accountId);
            //l_amount
            l_commandDeposit.setAmount(l_amount);
            //"JPY"
            l_commandDeposit.setCurrency("JPY");
            
            // （2）SendSyncRequestインスタンスを生成する。
            //      SendSyncRequest(arg0 : CommandBase, arg1 : AuthToken)
            // 　@    [引数]
            //  　@    arg0： （1）で生成したCommandDepositインスタンス
            //　@　@    arg1： 2)で生成したAuthTokenインスタンス
            l_commandBase = l_commandDeposit;
            
            // debug
            log.debug("\n" + "*****Depositパラメータ*****" +
                      "\n" + "コマンドID(会社ID+識別コード)：" + l_commandId + 
                      "\n" + "コマンドID：" + l_commandDeposit.getCommandID() +
                      "\n" + "為替保証金口座番号：" + l_commandDeposit.getAccountID() +
                      "\n" + "入出金額：" + l_commandDeposit.getAmount() +
                      "\n" + "通貨：" + l_commandDeposit.getCurrency());
            
        }
        
        // ＜引数.電文明細.処理区分 == "02：入金"の場合(FXから振替の場合）＞
        else if(WEB3AdminAioGftOperationDivDef.CASH_IN.equals(
            l_fxGftAskingTelegramUnit.gftOperationDiv))
        {
            
            // 為替保証金口座番号を取得
            long l_accountId = Long.parseLong(l_fxGftAskingTelegramUnit.fxAccountCode);
            
            // 入出金額を取得
            double l_amount = Double.parseDouble(l_fxGftAskingTelegramUnit.cashinoutAmt);
            
            // （1）CommandWithdrawインスタンスを生成する。
            //      CommandWithdraw(arg0 : String, arg1 : long, arg2 : double, arg3 : String)
            // 　@    [引数] 
            //  　@    arg0： 1）で取得した証券会社ID + 引数.GFT依頼電文明細.識別コード
            //  　@    arg1： 引数.GFT依頼電文明細.為替保証金口座番号
            //　@　@    arg2： 引数.GFT依頼電文明細.入出金額
            //　@　@    arg3： "JPY"
            CommandWithdraw l_commandWithdraw = new CommandWithdraw();
            //l_commandId
            l_commandWithdraw.setCommandID(l_commandId);
            //l_accountId
            l_commandWithdraw.setAccountID(l_accountId);
            //l_amount
            l_commandWithdraw.setAmount(l_amount);
            //"JPY"
            l_commandWithdraw.setCurrency("JPY");
            
            // （2）SendSyncRequestインスタンスを生成する。
            //      SendSyncRequest(arg0 : CommandBase, arg1 : AuthToken)
            // 　@    [引数]
            //  　@    arg0： （1）で生成したCommandWithdrawインスタンス
            //　@　@    arg1： 2)で生成したAuthTokenインスタンス
            l_commandBase = l_commandWithdraw;
            
            // debug
            log.debug("\n" + "*****Withdrawパラメータ*****" +
                      "\n" + "コマンドID(会社ID+識別コード)：" + l_commandId +
                      "\n" + "コマンドID：" + l_commandWithdraw.getCommandID() + 
                      "\n" + "為替保証金口座番号：" + l_commandWithdraw.getAccountID() +
                      "\n" + "入出金額：" + l_commandWithdraw.getAmount() +
                      "\n" + "通貨：" + l_commandWithdraw.getCurrency());
            
        }
        //＜引数.電文明細.処理区分 == "07：振替可能額"の場合＞
        else if (WEB3AdminAioGftOperationDivDef.TRANSFER_ABLE_AMT.equals(
            l_fxGftAskingTelegramUnit.gftOperationDiv))
        {
            //（1）CommandLookupUserインスタンスを生成する。
            //CommandLookupUser(arg0 : String, arg1 : String)
            //arg0： 1）で取得した証券会社ID + 引数.GFT依頼電文明細.識別コード
            //arg1： 引数.GFT依頼電文明細.初期ログインID
            CommandLookupUser l_commandLookupUser = new CommandLookupUser();
            l_commandLookupUser.setCommandID(l_commandId);
            l_commandLookupUser.setLogin(l_fxGftAskingTelegramUnit.fxFirstLoginId);

            //2）SendSyncRequestインスタンスを生成する。
            //SendSyncRequest(arg0 : CommandBase, arg1 : AuthToken)
            //arg0： （1）で生成したCommandLookupUserインスタンス
            //arg1： 生成したAuthTokenインスタンス
            l_commandBase = l_commandLookupUser;
        }

        SendSyncRequestResponse l_sendSyncRequestResponse = null;
        
        try {
            
            // 4）サービス実装クラスを生成する。
            //    AdministrativeAPI_Impl()
            AdministrativeAPI l_service = new AdministrativeAPI();
            
            // 5）サービススタブを生成する。
            //    service.getAdministrativeAPIPort(arg0 : String)
            //     [引数]
            //      arg0 : 引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.サービス名
            AdministrativeAPIPort l_apiPort = l_service.getAdministrativeAPIPort(l_rpcParams.service_name);

            //6）サービススタブにエンドポイントを設定する。
            //_setProperty(arg0 : String, arg1 : Object)
            //[引数]
            //arg0： "javax.xml.rpc.service.endpoint.address"
            //arg1：
            //－引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.エンドポイント名の分割数 == 3の場合
            //引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.エンドポイント名の配列第3要素
            //－引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.エンドポイント名の分割数 == 1の場合
            //引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.エンドポイント名の配列第1要素
            String[] l_strEndpointNames = l_rpcParams.getEndpointName().split(";");
            int l_intEndpointNamesLength = l_strEndpointNames.length;
            String l_strParameter = null;
            if (l_intEndpointNamesLength == 3)
            {
                l_strParameter = l_strEndpointNames[2];
            }
            else if (l_intEndpointNamesLength == 1)
            {
                l_strParameter = l_strEndpointNames[0];
            }
            //※上記の条件以外の場合、例外をスローする。
            else
            {
                log.debug("外部システム接続エラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02398,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "外部システム接続エラー。"
                    );
            }
            BindingProvider l_bp = (BindingProvider)l_apiPort;
            l_bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, l_strParameter);

            // 7）接続タイムアウト時間をセットする。
            l_bp.getRequestContext().put("com.sun.xml.internal.ws.connect.timeout", l_rpcParams.response_timeout);
            l_bp.getRequestContext().put("com.sun.xml.internal.ws.request.timeout", l_rpcParams.response_timeout);

            ThreadLocalSystemAttributesRegistry.setAttribute("branch_id", String.valueOf(l_rpcParams.branch_id));
            ThreadLocalSystemAttributesRegistry.setAttribute("connect_div", String.valueOf(l_rpcParams.connect_div));
            
            Binding l_bd = l_bp.getBinding();
            List l_lisHandlerChain = l_bd.getHandlerChain();
            WEB3FXSOAPMsgHandler l_handler = new WEB3FXSOAPMsgHandler();
            l_lisHandlerChain.add(l_handler);
            l_bd.setHandlerChain(l_lisHandlerChain);
            
            // 9）メッセージ送受信
            //    regSoap_Stub.sendSyncRequest(arg0 : SendSyncRequest)
            //     [引数]
            //      arg0 : 2）で生成したリクエストデータ
            l_apiPort.sendSyncRequest(l_commandBase, l_token, l_rejectedCommand, l_sendSyncRequestResult);
        } catch (AuthorizationFailedException l_ex){
            
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            // ハッシュ認証エラー
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_90224,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);            
          } catch (InternalErrorException l_ex) {
            
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            // 致命的なシステムエラー
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);

        }
        
        log.exiting(STR_METHOD_NAME);
        
        // 10）9）で受信したSOAP接続レスポンスデータを返却する。
    }

    /**
     * (getSOAPGFT受付結果コード) <BR>
     * 結果通知電文にセットする受付結果コードを取得する。 <BR>
     * <BR>
     * １）引数.GFT受付結果コードの値により、対応する受付結果コードをセットする。<BR>
     * ・引数.GFT受付結果コード == ”100”（Done.） の場合、<BR>
     * 　@受付結果コード.”00000000”（処理完了）<BR>
     * ・引数.GFT受付結果コード == ”200”（Missed parameter）の場合、<BR>
     * 　@受付結果コード.”00000601”（依頼電文の書式エラー（必須項目未入力）） <BR> 
     * ・引数.GFT受付結果コード == ”201”（Wrong parameter value）の場合、<BR>
     * 　@(a).引数.GFT受付結果詳細コード == ”2014”（Amount is more than the real withdrawable cash）であれば、<BR>
     * 　@　@受付結果コード.”00000204”（ユーザーの証拠金口座残高不足によるエラー）<BR>
     * 　@(b).引数.GFT受付結果詳細コード == ”2015”（Duplicate login）の場合、<BR>
     * 　@　@受付結果コード.”00000801”（二重処理エラー）<BR>
     * 　@(c).上記(a)・(b)以外の場合は、<BR>
     * 　@　@受付結果コード.”00000602”（依頼電文の書式エラー（不正な文字の使用））<BR>
     * ・引数.GFT受付結果コード == ”202”（Restriction violation）の場合、<BR>
     * 　@受付結果コード.”00000299”（上記以外でユーザーに起因するエラー）<BR> 
     * ・引数.GFT受付結果コード == ”251”（Duplicate command registration）の場合、<BR>
     * 　@受付結果コード.”00000609”（上記以外で電文書式に起因するエラー）<BR>
     * ・引数.GFT受付結果コード == ”252”（Entity does not exist）の場合、<BR>
     * 　@(a).引数.GFT受付結果詳細コード == ”2523”（Account）の場合、<BR>
     * 　@　@受付結果コード.”00000501”（該当する為替保証金口座が存在しない）<BR>
     * 　@(b).上記(a)以外の場合は、<BR>
     * 　@　@受付結果コード.”00000602”（依頼電文の書式エラー（不正な文字の使用））<BR>
     * ・引数.GFT受付結果コード == ”253”（Restriction violation）の場合、<BR>
     * 　@受付結果コード.”00000299”（上記以外でユーザーに起因するエラー）<BR>
     * ・引数.GFT受付結果コード == ”300”（Internal service error. Please, contact support.）の場合、<BR>
     * 　@受付結果コード.”00000199”（上記以外でホストシステムに起因するエラー）<BR>
     * ・引数.GFT受付結果コード == ”350”（Internal service error. Please, contact support.）の場合、<BR>
     * 　@受付結果コード.”00000199”（上記以外でホストシステムに起因するエラー）<BR>
     * ・上記以外の場合、受付結果コード.”00000901”（上記、及び下記以外のエラー）<BR>
     * ２）受付結果コードを返却する。<BR>
     * <BR>
     * @@param l_acceptResultCode GFT受付結果コード
     * @@param l_acceptResultDetailCode GFT受付結果詳細コード
     * @@return String
     */
    protected String getSoapAcceptResultCode(String l_acceptResultCode,
                                           String l_acceptResultDetailCode)
    {

        String STR_METHOD_NAME = "getSoapAcceptResultCode(String, String)";
        log.entering(STR_METHOD_NAME);
        
        String l_soapAcceptResultCode = null;
        
        // １）引数.GFT受付結果コードの値により、対応する受付結果コードをセットする。
        // ・引数.GFT受付結果コード == ”100”（Done.） の場合、
        // 　@受付結果コード.”00000000”（処理完了）
        if (WEB3GftSoapResultCodeDef.NORMAL.equals(l_acceptResultCode))
        {
            l_soapAcceptResultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000;
        }
        // ・引数.GFT受付結果コード == ”200”（Missed parameter）の場合、
        // 　@受付結果コード.”00000601”（依頼電文の書式エラー（必須項目未入力）） 
        else if (WEB3GftSoapResultCodeDef.MISSED_PARAMETER.equals(l_acceptResultCode))
        {
            l_soapAcceptResultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000601;
        }
        // ・引数.GFT受付結果コード == ”201”（Wrong parameter value）の場合、
        // 　@(a).引数.GFT受付結果詳細コード == ”2014”（Amount is more than the real withdrawable cash）であれば、
        // 　@　@受付結果コード.”00000204”（ユーザーの証拠金口座残高不足によるエラー）
        // 　@(b).引数.GFT受付結果詳細コード == ”2015”（Duplicate login）の場合、
        // 　@　@受付結果コード.”00000801”（二重処理エラー）
        // 　@(c).上記(a)・(b)以外の場合は、
        // 　@　@受付結果コード.”00000602”（依頼電文の書式エラー（不正な文字の使用））
        else if (WEB3GftSoapResultCodeDef.WRONG_PARAMETER_VALUE.equals(l_acceptResultCode))
        {
            if (WEB3GftSoapResultCodeDef.LACK_OF_BALANCE.equals(l_acceptResultDetailCode))
            {
                l_soapAcceptResultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000204;
            }
            else if (WEB3GftSoapResultCodeDef.DUPLICATE_LOGIN.equals(l_acceptResultDetailCode))
            {
                l_soapAcceptResultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000801;
            }
            else
            {
                l_soapAcceptResultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000602;
            }
        }
        // ・引数.GFT受付結果コード == ”202”（Restriction violation）の場合、
        // 　@受付結果コード.”00000299”（上記以外でユーザーに起因するエラー）
        else if (WEB3GftSoapResultCodeDef.RESTRICTION_VIORATION.equals(l_acceptResultCode))
        {
            l_soapAcceptResultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000299;
        }
        // ・引数.GFT受付結果コード == ”251”（Duplicate command registration）の場合、
        // 　@受付結果コード.”00000609”（上記以外で電文書式に起因するエラー）
        else if (WEB3GftSoapResultCodeDef.DUPLICATE_COMMAND_ERROR.equals(l_acceptResultCode))
        {
            l_soapAcceptResultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000609;
        }
        // ・引数.GFT受付結果コード == ”252”（Entity does not exist）の場合、
        // 　@(a).引数.GFT受付結果詳細コード == ”2523”（Account）の場合、
        // 　@　@受付結果コード.”00000501”（該当する為替保証金口座が存在しない）
        // 　@(b).上記(a)以外の場合は、<BR>
        // 　@　@受付結果コード.”00000602”（依頼電文の書式エラー（不正な文字の使用））
        else if (WEB3GftSoapResultCodeDef.NO_ENTITY_ERROR.equals(l_acceptResultCode))
        {
            if (WEB3GftSoapResultCodeDef.ACCOUNT_ERROR_OF_ENTITY.equals(l_acceptResultDetailCode))
            {
                l_soapAcceptResultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000501;
            }
            else
            {
                l_soapAcceptResultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000602;
            }
        }
        // ・引数.GFT受付結果コード == ”253”（Restriction violation）の場合、
        // 　@受付結果コード.”00000299”（上記以外でユーザーに起因するエラー）
        else if (WEB3GftSoapResultCodeDef.RESTRICTION_ERROR.equals(l_acceptResultCode))
        {
            l_soapAcceptResultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000299;
        }
        // ・引数.GFT受付結果コード == ”300”（Internal service error. Please, contact support.）の場合、
        // 　@受付結果コード.”00000199”（上記以外でホストシステムに起因するエラー）
        else if (WEB3GftSoapResultCodeDef.INTERNAL_SERVICE_ERROR.equals(l_acceptResultCode))
        {
            l_soapAcceptResultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000199;
        }
        // ・引数.GFT受付結果コード == ”350”（Internal service error. Please, contact support.）の場合、
        // 　@受付結果コード.”00000199”（上記以外でホストシステムに起因するエラー）
        else if (WEB3GftSoapResultCodeDef.SERVICE_ERROR.equals(l_acceptResultCode))
        {
            l_soapAcceptResultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000199;
        }
        // ・上記以外の場合、受付結果コード.”00000901”（上記、及び下記以外のエラー）
        else
        {
            l_soapAcceptResultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000901;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        // ２）受付結果コードを返却する。
        return l_soapAcceptResultCode;
    }
}
@


1.9
log
@*** empty log message ***
@
text
@d178 8
a185 1
                l_strReturnDetailValue = l_rejectedCommand.value.getMinorErrorCode() + "";
d194 8
a201 2
                l_strReturnDetailValue = l_sendSyncRequestResult.value.getMinorStatusCode() + "";
               
@


1.8
log
@*** empty log message ***
@
text
@a58 1
import com.sun.xml.internal.ws.client.BindingProviderProperties;
@


1.7
log
@*** empty log message ***
@
text
@d594 1
a594 1
        bp.getRequestContext().put("com.sun.xml.ws.connect.timeout", l_rpcParams.response_timeout);
d596 1
a596 1
        bp.getRequestContext().put("com.sun.xml.ws.request.timeout", l_rpcParams.response_timeout);
d1278 2
a1279 2
            l_bp.getRequestContext().put(BindingProviderProperties.CONNECT_TIMEOUT, l_rpcParams.response_timeout);
            l_bp.getRequestContext().put(BindingProviderProperties.REQUEST_TIMEOUT, l_rpcParams.response_timeout);
@


1.6
log
@*** empty log message ***
@
text
@d177 1
a177 2
                l_strReturnValue = 
                    Integer.toString(l_rejectedCommand.value.getMajorErrorCode());
d179 1
a179 2
                l_strReturnDetailValue = 
                    Integer.toString(l_rejectedCommand.value.getMinorErrorCode());
d186 1
a186 2
                l_strReturnValue = 
                    Integer.toString(l_sendSyncRequestResult.value.getMajorStatusCode());
d188 1
a188 2
                l_strReturnDetailValue = 
                    Integer.toString(l_sendSyncRequestResult.value.getMinorStatusCode());
@


1.5
log
@*** empty log message ***
@
text
@a149 1
//        SendSyncRequestResponse l_sendSyncRequestResponse = null;
d921 1
a921 1
    protected SendSyncRequestResponse sendSoapMessage(
a1324 1
        return l_sendSyncRequestResponse;  
@


1.4
log
@*** empty log message ***
@
text
@d150 1
a150 1
        SendSyncRequestResponse l_sendSyncRequestResponse = null;
d176 1
a176 1
            if (l_sendSyncRequestResponse.getRejectedCommand() != null)
d179 1
a179 1
                    Integer.toString(l_sendSyncRequestResponse.getRejectedCommand().getMajorErrorCode());
d182 1
a182 1
                    Integer.toString(l_sendSyncRequestResponse.getRejectedCommand().getMinorErrorCode());
d190 1
a190 1
                    Integer.toString(l_sendSyncRequestResponse.getSendSyncRequestResult().getMajorStatusCode());
d193 1
a193 1
                    Integer.toString(l_sendSyncRequestResponse.getSendSyncRequestResult().getMinorStatusCode());
@


1.3
log
@*** empty log message ***
@
text
@d59 1
d1283 2
a1284 6
            //    setProperty(arg0 : String, arg1 : Object)
            //     [引数]
            //1 minute for connection ((BindingProvider)
            l_bp.getRequestContext().put("com.sun.xml.ws.connect.timeout", l_rpcParams.response_timeout);
            //3 minutos for request ((BindingProvider)
            l_bp.getRequestContext().put("com.sun.xml.ws.request.timeout", l_rpcParams.response_timeout);
@


1.2
log
@*** empty log message ***
@
text
@a448 4
     * 　@　@（3）key : "weblogic.webservice.transport.https.proxy.host" <BR>
     * 　@　@ 　@ value : 1）で取得した接続先情報の配列第1要素 <BR>
     * 　@　@（4）key : "weblogic.webservice.transport.https.proxy.port" <BR>
     * 　@　@ 　@ value : 1）で取得した接続先情報の配列第2要素 <BR>
a454 4
     * 　@　@（3）key : "weblogic.webservice.transport.http.proxy.host" <BR>
     * 　@　@ 　@ value : 1）で取得した接続先情報の配列第1要素 <BR>
     * 　@　@（4）key : "weblogic.webservice.transport.http.proxy.port"<BR>
     * 　@　@ 　@ value : 1）で取得した接続先情報の配列第2要素 <BR>
a479 2
     * arg0： "weblogic.webservice.rpc.timeoutsecs" <BR>
     * arg1： 引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.接続タイムアウト時間 <BR>
a516 4
      // 　@　@（3）key   : "weblogic.webservice.transport.https.proxy.host"
      // 　@　@ 　@  value : 1）で取得した接続先情報の配列第1要素
      // 　@　@（4）key   : "weblogic.webservice.transport.https.proxy.port"
      // 　@　@ 　@  value : 1）で取得した接続先情報の配列第2要素
a522 4
      // 　@　@（3）key   : "weblogic.webservice.transport.http.proxy.host"
      // 　@　@ 　@  value : 1）で取得した接続先情報の配列第1要素
      // 　@　@（4）key   : "weblogic.webservice.transport.http.proxy.port"
      // 　@　@ 　@  value : 1）で取得した接続先情報の配列第2要素
a566 2
      
//      System.setProperty("weblogic.webservice.UseWebLogicURLStreamHandler", "true");
a596 2
        //      arg0： "weblogic.webservice.rpc.timeoutsecs" 
        //      arg1： 引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.接続タイムアウト時間 
a601 3
        // debug
//        log.debug("\n" + "validateConnect() - adminAPI_Stub._getProperty() : " + adminAPI_Stub._getProperty("weblogic.webservice.rpc.timeoutsecs").toString());
        
a870 2
     * 　@　@　@　@arg0： "weblogic.webservice.rpc.timeoutsecs"<BR>
     * 　@　@　@　@arg1： 引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.接続タイムアウト時間<BR>
a1233 1
//        System.setProperty("weblogic.webservice.UseWebLogicURLStreamHandler", "true");
d1283 1
a1283 3
            //     [引数] 
            //      arg0： "weblogic.webservice.rpc.timeoutsecs" 
            //      arg1： 引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.接続タイムアウト時間 
@


1.1
log
@*** empty log message ***
@
text
@a12 1
import java.rmi.RemoteException;
d22 3
a24 4
import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.handler.HandlerInfo;
import javax.xml.rpc.handler.HandlerRegistry;
d31 1
d35 24
a58 24
import com.gftforex.soap.api.AuthToken;
import com.gftforex.soap.api.CommandAddAccount;
import com.gftforex.soap.api.CommandCreateUser;
import com.gftforex.soap.api.CommandDeposit;
import com.gftforex.soap.api.CommandLookupUser;
import com.gftforex.soap.api.CommandWithdraw;
import com.gftforex.soap.api.CreateAccountInfo;
import com.gftforex.soap.api.CreateUserInfo;
import com.gftforex.soap.api.LookupAccountInfo;
import com.gftforex.soap.api.LookupUserInfo;
import com.gftforex.soap.api.ResultInfoAddAccount;
import com.gftforex.soap.api.ResultInfoBase;
import com.gftforex.soap.api.ResultInfoCreateUser;
import com.gftforex.soap.api.ResultInfoLookupUser;
import com.gftforex.soap.api.SendSyncRequest;
import com.gftforex.soap.api.SendSyncRequestResponse;
import com.gftforex.soap.api.UserPersonalInfo;
import com.gftforex.soap.api.UserSystemInfo;

import esupport.client.AdministrativeAPI;
import esupport.client.AdministrativeAPIPort_Stub;
import esupport.client.AdministrativeAPI_Impl;
import esupport.client.AuthorizationFailedException;
import esupport.client.InternalErrorException;
d153 2
d164 7
a170 5
            l_sendSyncRequestResponse = this.sendSoapMessage(
                (WEB3FXGftAskingTelegramUnit)l_message,
                l_prefRpcParams,
                l_strGMT,
                l_strHashValue);
d293 1
a293 1
                    (ResultInfoCreateUser)l_sendSyncRequestResponse.getSendSyncRequestResult();
d300 1
a300 1
                        this.connectionResultDetails.put(FX_ACC_01, new Long(l_resultInfoCreateUser.getAccountIds()[0]));
d302 1
a302 1
                        this.connectionResultDetails.put(FX_ACC_10, new Long(l_resultInfoCreateUser.getAccountIds()[1]));
d304 1
a304 1
                        this.connectionResultDetails.put(CFD_ACC, new Long(l_resultInfoCreateUser.getAccountIds()[2]));
d313 1
a313 1
                            this.connectionResultDetails.put(FX_ACC_01, new Long(l_resultInfoCreateUser.getAccountIds()[0]));
d315 1
a315 1
                            this.connectionResultDetails.put(FX_ACC_10, new Long(l_resultInfoCreateUser.getAccountIds()[1]));
d321 1
a321 1
                            this.connectionResultDetails.put(CFD_ACC, new Long(l_resultInfoCreateUser.getAccountIds()[0]));
d330 1
a330 1
                    (ResultInfoAddAccount)l_sendSyncRequestResponse.getSendSyncRequestResult();
d337 1
a337 1
                        this.connectionResultDetails.put(FX_ACC_01, new Long(l_resultInfoAddAccount.getAccountId()[0]));
d339 1
a339 1
                        this.connectionResultDetails.put(FX_ACC_10, new Long(l_resultInfoAddAccount.getAccountId()[1]));
d345 1
a345 1
                        this.connectionResultDetails.put(CFD_ACC, new Long(l_resultInfoAddAccount.getAccountId()[0]));
d353 1
a353 1
                ResultInfoBase l_resultInfoBase = l_sendSyncRequestResponse.getSendSyncRequestResult();
d358 2
a359 2
                    LookupAccountInfo[] l_lookUpAccInfos = l_lookupUserInfo.getAccounts();
                    if (l_lookUpAccInfos != null && l_lookUpAccInfos.length > 0)
d363 1
a363 1
                        for (int i = 0; i < l_lookUpAccInfos.length; i++)
d365 2
a366 2
                            l_strAccountId = l_lookUpAccInfos[i].getAccountId() + "";
                            l_hmAmount.put(l_strAccountId, WEB3StringTypeUtility.formatNumber(l_lookUpAccInfos[i].getWithdrawableAmount()));
a553 2
            System.setProperty("weblogic.webservice.transport.https.proxy.host", l_urlArr[0].trim());
            System.setProperty("weblogic.webservice.transport.https.proxy.port", l_urlArr[1].trim());
d556 1
a556 3
                "\nhttps.proxyPort (" + l_urlArr[1] + ")" +
                "\nweblogic.webservice.transport.https.proxy.host (" + l_urlArr[0] + ")" +
                "\nweblogic.webservice.transport.https.proxy.port (" + l_urlArr[1] + ")";
a561 2
            System.setProperty("weblogic.webservice.transport.http.proxy.host", l_urlArr[0].trim());
            System.setProperty("weblogic.webservice.transport.http.proxy.port", l_urlArr[1].trim());
d564 1
a564 3
                "\nhttp.proxyPort (" + l_urlArr[1] + ")" +
                "\nweblogic.webservice.transport.http.proxy.host (" + l_urlArr[0] + ")" +
                "\nweblogic.webservice.transport.http.proxy.port (" + l_urlArr[1] + ")";
a587 2
      try{
        
d590 1
a590 1
        AdministrativeAPI service = new AdministrativeAPI_Impl();
d596 2
a597 2
        AdministrativeAPIPort_Stub adminAPI_Stub = 
            (AdministrativeAPIPort_Stub) service.getAdministrativeAPIPort(l_rpcParams.service_name);
d610 3
a612 4

        adminAPI_Stub._setProperty(
            "javax.xml.rpc.service.endpoint.address",
            l_strParameter);
d619 4
a622 3
        adminAPI_Stub._setProperty("weblogic.webservice.rpc.timeoutsecs", l_rpcParams.response_timeout);
        

d625 1
a625 1
        log.debug("\n" + "validateConnect() - adminAPI_Stub._getProperty() : " + adminAPI_Stub._getProperty("weblogic.webservice.rpc.timeoutsecs").toString());
a630 34
      } catch (RemoteException l_ex) {
        
        log.error(l_ex.getMessage(), l_ex);
        log.exiting(STR_METHOD_NAME);
        // 致命的なシステムエラー
        throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02398,
            this.getClass().getName() + "." + STR_METHOD_NAME,
            l_ex.getMessage(),
            l_ex);
          
      } catch (ServiceException l_ex) {
        
        log.error(l_ex.getMessage(), l_ex);
        log.exiting(STR_METHOD_NAME);
        // 致命的なシステムエラー
        throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80002,
            this.getClass().getName() + "." + STR_METHOD_NAME,
            l_ex.getMessage(),
            l_ex);
        
      } catch (IOException l_ex) {
        
        log.error(l_ex.getMessage(), l_ex);
        log.exiting(STR_METHOD_NAME);
        // 致命的なシステムエラー
        throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80002,
            this.getClass().getName() + "." + STR_METHOD_NAME,
            l_ex.getMessage(),
            l_ex);
        
    } 
d952 3
a954 1
            String l_hashValue) throws WEB3BaseException
d1001 1
a1001 1
        SendSyncRequest l_sendSyncRequest = null;
d1064 5
a1068 6
            CreateUserInfo l_createUserInfo = 
                new CreateUserInfo(
                    l_rpcParams.parameter_list,
                    l_userPersonalInfo,
                    l_userSystemInfo,
                    l_createAccountInfos);
d1075 3
a1077 2
            CommandCreateUser l_commandCreateUser = 
                new CommandCreateUser(l_commandId,l_createUserInfo);
d1084 1
a1084 2
            l_sendSyncRequest = 
                new SendSyncRequest(l_commandCreateUser,l_token);
d1139 4
a1142 5
            CommandAddAccount l_commandAddAccount =
                new CommandAddAccount(
                    l_commandId,
                    l_fxGftAskingTelegramUnit.fxFirstLoginId,
                    l_createAccountInfos);
d1149 1
a1149 2
            l_sendSyncRequest =
                new SendSyncRequest(l_commandAddAccount, l_token);
d1170 9
a1178 2
            CommandDeposit l_commandDeposit = 
                new CommandDeposit(l_commandId,l_accountId,l_amount,"JPY");
d1185 1
a1185 2
            l_sendSyncRequest = 
                new SendSyncRequest(l_commandDeposit,l_token);
d1215 9
a1223 2
            CommandWithdraw l_commandWithdraw = 
                new CommandWithdraw(l_commandId,l_accountId,l_amount,"JPY");
d1230 1
a1230 2
            l_sendSyncRequest = 
                new SendSyncRequest(l_commandWithdraw,l_token);
d1249 3
a1251 2
            CommandLookupUser l_commandLookupUser =
                new CommandLookupUser(l_commandId, l_fxGftAskingTelegramUnit.fxFirstLoginId);
d1257 1
a1257 2
            l_sendSyncRequest =
                new SendSyncRequest(l_commandLookupUser, l_token);
d1267 1
a1267 1
            AdministrativeAPI service = new AdministrativeAPI_Impl();
d1273 1
a1273 2
            AdministrativeAPIPort_Stub adminAPI_Stub = 
            (AdministrativeAPIPort_Stub) service.getAdministrativeAPIPort(l_rpcParams.service_name);
d1306 2
a1307 3
            adminAPI_Stub._setProperty(
                "javax.xml.rpc.service.endpoint.address",
                l_strParameter);
d1314 13
a1326 29
            adminAPI_Stub._setProperty("weblogic.webservice.rpc.timeoutsecs", l_rpcParams.response_timeout);
            
            // 8）SOAPメッセージハンドラをセットする。 
            //    (1)Web サービス ポートの修飾名を格納するオブジェクトを作成 
            QName portName = new QName( l_rpcParams.target_namespace_name, l_rpcParams.service_name);

            //    (2)HandlerRegistry オブジェクトを作成 
            HandlerRegistry registry = service.getHandlerRegistry();
            
            //    (3)HandlerInfoに設定するconfig(：Map)データの作成 
            //　@　@　@　@[put()に指定する引数] 
            //　@　@　@　@　@key："BranchId" 
            //　@　@　@　@　@value：引数.外部システムSOAP接続プリファ@レンス（RPC形式）params. 部店ID
            //　@　@　@　@[put()に指定する引数] 
            //　@　@　@　@　@key："ConnectDiv" 
            //　@　@　@　@　@value：引数.外部システムSOAP接続プリファ@レンス（RPC形式）params. 接続区分 
            HashMap map = new HashMap();
            
            String branch_id_str=String.valueOf(l_rpcParams.branch_id);
            
            map.put("BranchId",branch_id_str);
            map.put("ConnectDiv",l_rpcParams.connect_div);

            //    (4)SOAPメッセージのハンドラ チェーン(：ArrayList)生成 
            List handlerList = new ArrayList();
            handlerList.add( new HandlerInfo( WEB3FXSOAPMsgHandler.class, map, null ) );

            //　@　@(5)ハンドラ チェーンを登録
            registry.setHandlerChain( portName, handlerList );          
d1332 1
a1332 3
            l_sendSyncRequestResponse = 
                adminAPI_Stub.sendSyncRequest(l_sendSyncRequest);
            
d1342 2
a1343 36
                l_ex);
            
        } catch (InternalErrorException l_ex){
            
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            // 通信エラー
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01802,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
            
          } catch (RemoteException l_ex) {
            
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            // 外部システム接続エラー
            throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02398,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
              
          } catch (ServiceException l_ex) {
            
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            // 致命的なシステムエラー
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
            
          } catch (IOException l_ex) {
@

