head	1.3;
access;
symbols;
locks; strict;
comment	@// @;


1.3
date	2011.03.30.08.23.40;	author zhang-tengyu;	state Exp;
branches;
next	1.2;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1944d92e88c56d7;
filename	WEB3FXDataControlServiceImpl.java;

1.2
date	2011.03.28.06.22.41;	author zhang-tengyu;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	3f04d9029305df6;
filename	WEB3FXDataControlServiceImpl.java;

1.1
date	2011.03.16.02.27.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FXDataControlServiceImpl.java;


desc
@@


1.3
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FXデータ制御サービスImpl(WEB3FXDataControlServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/20 周勇 (中訊) 新規作成
                 : 2006/02/08 鄭徳懇 (中訊) 仕様変更・モデル469、471
                                          仕様変更・DB更新仕様 071、072、073、074
                 : 2006/02/24 黄浩澎 (中訊) 仕様変更・モデル446、459、465、502
                 : 2006/04/25 肖志偉 (中訊) 仕様変更・モデル534、536
                                          仕様変更・DB更新仕様 081
                 : 2006/05/08 周捷 (中訊) 仕様変更・モデル550  
                 : 2006/05/10 周捷 (中訊) 仕様変更・モデル549、557、559
                 : 2006/05/12 郭英 (中訊) 仕様変更・モデル571 
                 : 2006/05/15 郭英 (中訊) 仕様変更・DB更新仕様 086、087、088 
                 : 2006/05/16 周捷 (中訊) 仕様変更・モデル583            
                 : 2006/06/05 鈴木 (SCS) 仕様変更 No.590・DB更新仕様 091
                 : 2006/07/12 韋念瓊 (中訊) 仕様変更 モデル598・DB更新仕様092、094
                 : 2006/08/04 鈴木 (SCS) 仕様変更 No.609・DB更新仕様 096,097
                 : 2006/08/23 鈴木 (SCS) 仕様変更・モデル631
                 : 2006/10/16 何文敏 (中訊) 仕様変更・モデル657
Revesion History : 2008/04/08 周墨洋 (中訊) 仕様変更・モデル838,841,843
                 : 2008/05/08 佐藤 (SCS) 仕様変更・モデル838
                 : 2008/05/23 三島 (SCS) 
Revesion History : 2008/05/20 柴双紅 (中訊) 仕様変更・モデル852,856,867,872,873,875
　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@DB更新仕様161,162,163,164,165,166,167,168,169
Revesion History : 2008/06/17 柴双紅 (中訊) 仕様変更・モデル898 DB更新仕様171,172
Revesion History : 2008/06/23 柴双紅 (中訊) 仕様変更・モデル904,905,906
Revesion History : 2008/07/02 馮海濤 (中訊) 仕様変更・モデル908
Revesion History : 2008/09/08 王志葵 (中訊) 仕様変更・モデル973,981,983
Revesion History : 2008/10/02 竹村 (SCS) 仕様変更・モデル1046
Revesion History : 2008/10/07 王志葵 (中訊) 仕様変更・モデル990,1002,1010,1021,1025,1026
                                                      1027,1028,1029,1030,1031,1032
                                                      1038,1050,1053,1055,1060,1062
                                                      1065,1066,1072,1073,1076
                                       ＤＢ更新仕様・モデル178,179,181,182,183,184,185
                                                      186,187189,190,191,192,193
                                                      195,196,197,199,200
Revesion History : 2008/10/28 大嶋 (SCS) 仕様変更・モデル
Revesion History : 2008/11/14 佐藤 (SCS) 仕様変更・モデル1084
Revesion History : 2008/12/16 大嶋 (SCS) 仕様変更・モデル1088,1089
Revesion History : 2009/01/22 吉原 (SCS) 仕様変更・モデル1092,1093,1094
Revesion History : 2009/03/12 柴双紅 (中訊) 仕様変更・モデル1108,1150 ＤＢ更新仕様208
Revesion History : 2009/03/19 車進 (中訊) 仕様変更・モデル1124、1135、1136、1157
                                         ＤＢ更新仕様214、220、223
Revesion History : 2009/04/20 車進 (中訊) 仕様変更・モデル1161
Revesion History : 2009/04/22 車進 (中訊) ＤＢ更新仕様228
Revesion History : 2009/05/31 柴双紅 (中訊) ＤＢ更新仕様229
Revesion History : 2009/06/26 武波 (中訊) 仕様変更・モデル1171
Revesion History : 2009/08/25 張騰宇 (中訊) 仕様変更・モデル1194
Revesion History : 2009/09/16 孟亞南 (中訊) 仕様変更・モデル1214
*/
package webbroker3.aio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.SocketTimeoutException;
import java.rmi.RemoteException;
import java.security.KeyManagementException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.net.ssl.SSLSocketFactory;
import javax.xml.namespace.QName;
import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;


import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.gftforex.soap_api.ResultInfoAddAccount;
import com.gftforex.soap_api.ResultInfoCreateUser;
import com.sun.xml.internal.ws.client.BindingProviderProperties;
import com.sun.xml.internal.ws.developer.JAXWSProperties;

import fx.client.EntryReceiptIn;
import fx.client.EntryReceiptOut;
import fx.client.WebService;
import fx.client.WebServiceSoap;

import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.CompFxConditionRow;
import webbroker3.aio.data.FxAccountCodeParams;
import webbroker3.aio.data.FxAccountCodeRow;
import webbroker3.aio.data.FxAccountParams;
import webbroker3.aio.data.FxAccountRow;
import webbroker3.aio.data.FxTransferMasterDao;
import webbroker3.aio.data.FxTransferMasterParams;
import webbroker3.aio.data.FxTransferMasterRow;
import webbroker3.aio.data.FxUnnecessaryExplanationParams;
import webbroker3.aio.data.FxUnnecessaryExplanationRow;
import webbroker3.aio.data.GftAccountOpenStatusParams;
import webbroker3.aio.data.GftAccountOpenStatusRow;
import webbroker3.aio.data.GftMessageParams;
import webbroker3.aio.data.GftMessageRow;
import webbroker3.aio.data.GftTransferStatusParams;
import webbroker3.aio.data.GftTransferStatusRow;
import webbroker3.aio.define.WEB3AdminAioGftOperationDivDef;
import webbroker3.aio.define.WEB3AdminFXUploadNoteOneDef;
import webbroker3.aio.define.WEB3AioAcceptResultCodeDef;
import webbroker3.aio.define.WEB3AioCashInOutAmountDivDef;
import webbroker3.aio.define.WEB3AioFxAccountOpenDivDef;
import webbroker3.aio.define.WEB3AioQuestionAnswerDef;
import webbroker3.aio.define.WEB3KamokuCdDef;
import webbroker3.aio.handler.WEB3FXSOAPMsgHandler;
import webbroker3.aio.message.WEB3FXAccInformationUnit;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.message.WEB3FXGftResultNoticeTelegramUnit;
import webbroker3.aio.message.WEB3FXTradeAgreementUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountOpenStatusDivDef;
import webbroker3.common.define.WEB3AioAccountDivDef;
import webbroker3.common.define.WEB3AccountOpenDef;
import webbroker3.common.define.WEB3DeliveryDateDivDef;
import webbroker3.common.define.WEB3EffectiveDivDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ExtConnectSystemCodeDef;
import webbroker3.common.define.WEB3FxSystemDivDef;
import webbroker3.common.define.WEB3FxTransStatusOperationDivDef;
import webbroker3.common.define.WEB3GftErrorReasonCodeDef;
import webbroker3.common.define.WEB3GftMessageDivDef;
import webbroker3.common.define.WEB3GftMessageOperationDef;
import webbroker3.common.define.WEB3GftTransStatusCourseDivDef;
import webbroker3.common.define.WEB3InstitutionPreferencesNameDef;
import webbroker3.common.define.WEB3MrfAccOpenDivDef;
import webbroker3.common.define.WEB3QuestionDivDef;
import webbroker3.common.define.WEB3SendRcvDivDef;
import webbroker3.common.define.WEB3SoapResultCodeDef;
import webbroker3.common.define.WEB3TransferStatusDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.gentrade.data.InstitutionPreferencesDao;
import webbroker3.gentrade.data.InstitutionPreferencesRow;
import webbroker3.gentrade.data.QuestionAnswerParams;
import webbroker3.gentrade.data.QuestionAnswerRow;
import webbroker3.gentrade.data.QuestionParams;
import webbroker3.gentrade.data.QuestionRow;
import webbroker3.gentrade.data.SoapConnectPrefRpcParams;
import webbroker3.gentrade.data.SoapMessageParams;
import webbroker3.gentrade.define.WEB3GentradeBatoCheckResultDef;
import webbroker3.gentrade.message.WEB3GentradeProspectusResult;
import webbroker3.gentrade.service.delegate.WEB3GentradeBatoClientService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (FXデータ制御サービスImpl) <BR>
 * FXデータ制御サービス実装クラス。 <BR>
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3FXDataControlServiceImpl implements WEB3FXDataControlService
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXDataControlServiceImpl.class);
    
    private SSLSocketFactory sslSocketFactory;
    
    /**
     * @@roseuid 41E767E400DA
     */
    public WEB3FXDataControlServiceImpl()
    {
    }

    /**
     * (getFX顧客) <BR>
     * 引数の証券会社コード、部店コード、FXシステムコード、顧客コードに <BR>
     *      該当するFX顧客Pa ramsを取得する。 <BR>
     * <BR>
     * １）DB検索 <BR>
     * FX顧客テーブル(fx_account)を以下の条件で検索する。 <BR>
     * <BR>
     * [条件] <BR>
     * 証券会社コード = 引数.証券会社コード <BR>
     * 部店コード = 引数.部店コード <BR>
     * FXシステムコード = 引数.FXシステムコード <BR>
     * 顧客コード = 引数.顧客コード <BR>
     * <BR>
     * ２）検索結果のFX顧客Paramsを返却する。 <BR>
     * 
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strFxSystemCode - FXシステムコード
     * @@param l_strAccountCode - 顧客コード
     * @@return webbroker3.aio.data.FxAccountParams
     * @@throws WEB3BaseException
     * @@throws NotFoundException
     * @@roseuid 41C0F587002B
     */
    public FxAccountParams getFXAccount(String l_strInstitutionCode,
        String l_strBranchCode, String l_strFxSystemCode,
        String l_strAccountCode) throws WEB3BaseException, NotFoundException 
    {
        String STR_METHOD_NAME ="getFXAccount(String l_strInstitutionCode, String l_strBranchCode, " +
            "String l_strFxSystemCode, String l_strAccountCode)";
        log.entering(STR_METHOD_NAME);
        
        //１）DB検索                       
        //　@FX顧客テーブル(fx_account)を以下の条件で検索する。 
        //　@[条件]                         
        //　@証券会社コード = 引数.証券会社コード 
        //　@部店コード = 引数.部店コード   
        //　@FXシステムコード = 引数.FXシステムコード 
        //　@顧客コード = 引数.顧客コード
        
        String l_strWhere = null;
        if (l_strAccountCode != null && l_strAccountCode.length() == 6)
        {
            l_strWhere = "institution_code = ? and branch_code = ?" +
                " and fx_system_code = ? and substr(account_code, 0, 6) = ? ";
        }
        else
        {
            l_strWhere = "institution_code = ? and branch_code = ?" +
                " and fx_system_code = ? and account_code = ? ";
        }
        
        Object[] l_objValue = {l_strInstitutionCode, l_strBranchCode, l_strFxSystemCode,
            l_strAccountCode};
        
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    FxAccountRow.TYPE,
                    l_strWhere,
                    null,
                    l_objValue);
        }
        catch (DataException l_ex)
        {
            log.debug("__DataException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //２）検索結果のFX顧客Paramsを返却する。
        if(l_lisRows.size() == 0)
        {
            throw new NotFoundException("FX顧客テーブルが取得できませんでした");
        }
        
        if(l_lisRows.size() > 1)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "FX顧客テーブルに複数件");
        }
        
        
        FxAccountParams l_fxAccountParams = (FxAccountParams)l_lisRows.get(0);
        
        log.exiting(STR_METHOD_NAME);
        return l_fxAccountParams;
    }

    /**
     * (getFX顧客) <BR>
     * 引数の証券会社コード、部店コード、顧客コードに該当するFX顧客Paramsを取得する。 <BR>
     * １）FXシステムコード取得 <BR>
     * this.get会社別FXシステム条件()をコールする。 <BR>
     * <BR>
     * [引数の設定] <BR>
     * 証券会社コード： 引数.証券会社コード <BR>
     * 部店コード： 引数.部店コード <BR>
     * <BR>
     * FXシステムコード = get会社別FXシステム条件()の戻り値.FXシステムコード <BR>
     * <BR>
     * ２）DB検索 <BR>
     * FX顧客テーブル(fx_account)を以下の条件で検索する。 <BR>
     * <BR>
     * [条件] <BR>
     * 証券会社コード = 引数.証券会社コード <BR>
     * 部店コード = 引数.部店コード <BR>
     * FXシステムコード = １）にて取得したFXシステムコード <BR>
     * 顧客コード = 引数.顧客コード <BR>
     * <BR>※ 顧客コード <BR>
     * （引数.顧客コード.length() == 6）の場合は、最初の6byteのみ比較する。) <BR>
     * <BR>
     * ３）検索結果を返却する。 <BR>
     * 
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strAccountCode - 顧客コード
     * @@return webbroker3.aio.data.FxAccountParams
     * @@throws WEB3BaseException
     * @@throws NotFoundException
     * @@roseuid 41BD87A00335
     */
    public FxAccountParams getFXAccount(String l_strInstitutionCode,
        String l_strBranchCode, String l_strAccountCode) 
        throws WEB3BaseException, NotFoundException 
    {
        String STR_METHOD_NAME ="getFXAccount(String l_strInstitutionCode, " +
            "String l_strBranchCode, String l_strAccountCode)";
        log.entering(STR_METHOD_NAME);
        
        if(l_strAccountCode == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）FXシステムコード取得 
        //　@this.get会社別FXシステム条件()をコールする。 
        //　@[引数の設定] 
        //　@証券会社コード：　@引数.証券会社コード 
        //　@部店コード：　@引数.部店コード 
        CompFxConditionParams l_compFxConditionParams = null;
        try
        {
            l_compFxConditionParams = 
                this.getCompFxCondition(l_strInstitutionCode, l_strBranchCode);
            
        }
        catch(NotFoundException l_ex)
        {
            log.debug("__NotFoundException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex); 
        }
        
        //　@FXシステムコード = get会社別FXシステム条件()の戻り値.FXシステムコード
        String l_strFxSystemCode = l_compFxConditionParams.getFxSystemCode();
        
        //２）DB検索 
        //　@FX顧客テーブル(fx_account)を以下の条件で検索する。
        //　@[条件] 
        //　@証券会社コード = 引数.証券会社コード 
        //　@部店コード = 引数.部店コード 
        //　@FXシステムコード = １）にて取得したFXシステムコード 
        //　@顧客コード = 引数.顧客コード
        //※　@顧客コード 
        //　@　@（引数.顧客コード.length() == 6）の場合は、最初の6byteのみ比較する。)
        String l_strWhere = null;
        if(l_strAccountCode.length() == 6)
        {
            l_strWhere = "institution_code = ? and branch_code = ?" +
                " and fx_system_code = ? and substr(account_code ,0 ,6) = ? ";
        }
        else
        {
            l_strWhere = "institution_code = ? and branch_code = ?" +
                " and fx_system_code = ? and account_code = ? ";
        }
        
        Object[] l_objValue = {l_strInstitutionCode, l_strBranchCode, l_strFxSystemCode,
            l_strAccountCode};
        
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    FxAccountRow.TYPE,
                    l_strWhere,
                    null,
                    l_objValue);
        }
        catch (DataException l_ex)
        {
            log.debug("__DataException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        if(l_lisRows.size() == 0)
        {
            throw new NotFoundException("FX顧客テーブルが取得できませんでした");
        }
        
        if(l_lisRows.size() > 1)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "FX顧客テーブルに複数件");
        }
        
        //３）検索結果を返却する。        
        FxAccountParams l_fxAccountParams = (FxAccountParams)l_lisRows.get(0);
        
        log.exiting(STR_METHOD_NAME);
        return l_fxAccountParams;
    }

    /**
     * (getFX顧客) <BR>
     * 引数の条件に該当するFX顧客Paramsの一覧を返却する。 <BR>
     * <BR>
     * １）QueryProcessor.doFindAllQuery()メソッドをコールする。 <BR>
     * <BR>
     * [doFindAllQuery()にセットするパラメータ] <BR>
     * rowType： FX顧客Row.TYPE <BR>
     * where： パラメータ.検索条件文字列 <BR>
     * orderBy： パラメータ.ソート条件 <BR>
     * conditions： null <BR>
     * bindVars： パラメータ.検索条件データコンテナ <BR>
     * <BR>
     * 検索結果が取得できなかった場合、nullを返却する。 <BR>
     * <BR>
     * ２）検索結果を返却する。 <BR>
     * 
     * @@param l_strQueryString - 検索条件文字列
     * @@param l_queryContainer - 検索条件データコンテナ
     * @@param l_strSortCond - ソート条件
     * @@return webbroker3.aio.data.FxAccountParams[]
     * @@throws WEB3BaseException
     * @@roseuid 41BECC190291
     */
    public FxAccountParams[] getFXAccounts(String l_strQueryString,
        String[] l_queryContainer, String l_strSortCond) throws WEB3BaseException
    {
        String STR_METHOD_NAME ="getFXAccounts(String l_strQueryString, " +
            "String[] l_queryContainer, String l_strSortCond)";
        log.entering(STR_METHOD_NAME);
        
        //１）QueryProcessor.doFindAllQuery()メソッドをコールする。
        //　@[doFindAllQuery()にセットするパラメータ] 
        //　@　@rowType：　@FX顧客Row.TYPE 
        //　@　@where：　@パラメータ.検索条件文字列 
        //　@　@orderBy：　@パラメータ.ソート条件 
        //　@　@conditions：　@null 
        //　@　@bindVars：　@パラメータ.検索条件データコンテナ 
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    FxAccountRow.TYPE,
                    l_strQueryString,
                    l_strSortCond,
                    null,
                    l_queryContainer);
        }
        catch (DataException l_ex)
        {
            log.debug("__DataException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //　@検索結果が取得できなかった場合、nullを返却する。 
        if(l_lisRows.size() == 0)
        {
            return null;
        }
        
        //２）検索結果を返却する。        
        FxAccountParams[] l_fxAccountParams = new FxAccountParams[l_lisRows.size()]; 
        l_lisRows.toArray(l_fxAccountParams);

        log.exiting(STR_METHOD_NAME);
        return l_fxAccountParams;
    }

    /**
     * (getFX口座開設区分)<BR>
     * FX口座開設区分を取得する。<BR>
     * <BR>
     * １）引数.更新後口座開設状況区分の値により、以下のFX口座開設区分を戻す。<BR>
     * <BR>
     * ・引数.更新後口座開設状況区分＝"開設済"の場合<BR>
     * <BR>
     * 　@"1:開設済"<BR>
     * ・引数.更新後口座開設状況区分＝"抹消"の場合<BR>
     * 　@"2:抹消"<BR>
     * ・引数.更新後口座開設状況区分＝"振替停止"の場合<BR>
     * 　@"3:振替停止"<BR>
     * <BR>
     * ２）FX口座開設区分を返却する。<BR>
     * <BR>
     * @@param l_strUpdatedAccOpenDiv - (更新後口座開設状況区分)<BR>
     * 更新後口座開設状況区分<BR>
     * @@return String
     * @@roseuid 41BECC190291
     */
    public String getFXAccountOpenDiv(String l_strUpdatedAccOpenDiv)
    {
        String STR_METHOD_NAME ="getFXAccountOpenDiv(String)";
        log.entering(STR_METHOD_NAME);

        String l_strFXAccOpenDiv = null;
        //引数.更新後口座開設状況区分＝"開設済"の場合
        //"1:開設済"
        if (WEB3AioAccountDivDef.OPEN_COMPLETE.equals(l_strUpdatedAccOpenDiv))
        {
        	l_strFXAccOpenDiv = WEB3AccountOpenDef.OPEN;
        }
        //引数.更新後口座開設状況区分＝"抹消"の場合
        //"2:抹消"
        else if (WEB3AioAccountDivDef.DELETE.equals(l_strUpdatedAccOpenDiv))
        {
        	l_strFXAccOpenDiv = WEB3AccountOpenDef.DELETED;
        }
        //引数.更新後口座開設状況区分＝"振替停止"の場合
        //"3:振替停止"
        else if (WEB3AioAccountDivDef.TRANSFER_STOP.equals(l_strUpdatedAccOpenDiv))
        {
        	l_strFXAccOpenDiv = WEB3AccountOpenDef.TRANSFER_STOP;
        }

        log.exiting(STR_METHOD_NAME);
        return l_strFXAccOpenDiv;
    }

    /**
     * (getFX口座番号) <BR>
     * 引数の条件に該当するFX口座番号を取得する。 <BR>
     * <BR>
     * １）FXシステムコード取得 <BR>
     * this.get会社別FXシステム条件()をコールする。 <BR>
     * <BR>
     * [引数の設定] <BR>
     * 証券会社コード： 引数.証券会社コード <BR>
     * 部店コード： 引数.部店コード <BR>
     * <BR>
     * FXシステムコード = get会社別FXシステム条件()の戻り値.FXシステムコード <BR>
     * <BR>
     * ２）DB検索 <BR>
     * FX口座番号テーブル(fx_account_code)を <BR>
     * 以下の条件で検索する。 <BR>
     * <BR>
     * [条件] <BR>
     * 証券会社コード = 引数.証券会社コード <BR>
     * 部店コード = 引数.部店コード <BR>
     * FXシステムコード = １）にて取得したFXシステムコード <BR>
     * 顧客コード = 引数.顧客コード <BR>
     * FXコース区分 = 引数.コース区分 <BR>
     * <BR>※ 顧客コード <BR>
     * （引数.顧客コード.length() == 6）の場合は、最初の6byteのみ比較する。) <BR>
     * <BR>
     * ３）検索結果を返却する。 <BR>
     * 
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strAccountCode - 顧客コード
     * @@param l_strCourseDiv - コース区分
     * 0： DEFAULT 1： 1万通貨コース 2： 10万通貨コース
     * @@return webbroker3.aio.data.FxAccountCodeParams
     * @@throws WEB3BaseException
     * @@throws NotFoundException
     * @@roseuid 41C93457009D
     */
    public FxAccountCodeParams getFXAccountCode(String l_strInstitutionCode,
        String l_strBranchCode, String l_strAccountCode, String l_strCourseDiv)
        throws WEB3BaseException, NotFoundException  
    {
        
        String STR_METHOD_NAME ="getFXAccountCode(String l_strInstitutionCode, " +
            "String l_strBranchCode, String l_strAccountCode, String l_strCourseDiv)";
        log.entering(STR_METHOD_NAME);
        
        if(l_strAccountCode == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）FXシステムコード取得 
        //　@this.get会社別FXシステム条件()をコールする。
        //　@[引数の設定] 
        //　@証券会社コード：　@引数.証券会社コード 
        //　@部店コード：　@引数.部店コード 
        CompFxConditionParams l_compFxConditionParams = null;
        try
        {
            l_compFxConditionParams = 
                this.getCompFxCondition(l_strInstitutionCode, l_strBranchCode);
            
        }
        catch(NotFoundException l_ex)
        {
            log.debug("__NotFoundException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex); 
        }
        
        //　@FXシステムコード = get会社別FXシステム条件()の戻り値.FXシステムコード 
        String l_strFxSystemCode = l_compFxConditionParams.getFxSystemCode();
        
        //２）DB検索 
        //　@FX口座番号テーブル(fx_account_code)を 
        //　@以下の条件で検索する。 
        //　@[条件] 
        //　@証券会社コード = 引数.証券会社コード 
        //　@部店コード = 引数.部店コード 
        //　@FXシステムコード = １）にて取得したFXシステムコード 
        //　@顧客コード = 引数.顧客コード 
        //　@FXコース区分 = 引数.コース区分
        //　@※　@顧客コード 
        //　@　@（引数.顧客コード.length() == 6）の場合は、最初の6byteのみ比較する。)
        String l_strWhere = null;
        if(l_strAccountCode.length() == 6)
        {
            l_strWhere = "institution_code = ? and branch_code = ?" +
                " and fx_system_code = ? and substr(account_code ,0 ,6) = ? and fx_course_div = ?";
        }
        else
        {
            l_strWhere = "institution_code = ? and branch_code = ?" +
                " and fx_system_code = ? and account_code = ? and fx_course_div = ?";
        }
        
        Object[] l_objValue = {l_strInstitutionCode, l_strBranchCode, l_strFxSystemCode,
            l_strAccountCode, l_strCourseDiv};
        
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    FxAccountCodeRow.TYPE,
                    l_strWhere,
                    null,
                    l_objValue);
        }
        catch (DataException l_ex)
        {
            log.debug("__DataException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if(l_lisRows.size() == 0)
        {
            throw new NotFoundException("FX口座番号テーブルが取得できませんでした");
        }
        
        if(l_lisRows.size() > 1)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "FX口座番号テーブルに複数件");
        }
        
        //３）検索結果を返却する。
        FxAccountCodeParams l_fxAccountCodeParams = (FxAccountCodeParams)l_lisRows.get(0);
        
        log.exiting(STR_METHOD_NAME);
        return l_fxAccountCodeParams;
    }

    /**
     * (getFX口座番号) <BR>
     * 引数の条件に該当するFX口座番号の一覧を取得する。 <BR>
     * <BR>
     * １）FXシステムコード取得 <BR>
     * this.get会社別FXシステム条件()をコールする。 <BR>
     * <BR>
     * [引数の設定] <BR>
     * 証券会社コード： 引数.証券会社コード <BR>
     * 部店コード： 引数.部店コード <BR>
     * <BR>
     * FXシステムコード = get会社別FXシステム条件()の戻り値.FXシステムコード <BR>
     * <BR>
     * ２）DB検索 <BR>
     * FX口座番号テーブル(fx_account_code)を以下の条件で検索する。 <BR>
     * <BR>
     * [条件] <BR>
     * 証券会社コード = 引数.証券会社コード <BR>
     * 部店コード = 引数.部店コード <BR>
     * FXシステムコード = １）にて取得したFXシステムコード <BR>
     * 顧客コード = 引数.顧客コード <BR>
     * <BR>※ 顧客コード <BR>
     * （引数.顧客コード.length() == 6）の場合は、最初の6byteのみ比較する。) <BR>
     * <BR>
     * ３）検索結果のFX口座番号Paramsの配列をFXコース区分の昇順で <BR>
     * ソートし、返却する。 <BR>
     * 
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strAccountCode - 顧客コード
     * @@return webbroker3.aio.data.FxAccountCodeParams[]
     * @@throws WEB3BaseException
     * @@roseuid 41C7EE70029A
     */
    public FxAccountCodeParams[] getFXAccountCodes(String l_strInstitutionCode,
        String l_strBranchCode, String l_strAccountCode) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME ="getFXAccountCodes(String l_strInstitutionCode, " +
            "String l_strBranchCode, String l_strAccountCode)";
        log.entering(STR_METHOD_NAME);
        
        if(l_strAccountCode == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）FXシステムコード取得 
        //　@this.get会社別FXシステム条件()をコールする。
        //　@[引数の設定] 
        //　@証券会社コード：　@引数.証券会社コード 
        //　@部店コード：　@引数.部店コード
        CompFxConditionParams l_compFxConditionParams = null;
        try
        {
            l_compFxConditionParams = 
                this.getCompFxCondition(l_strInstitutionCode, l_strBranchCode);
            
        }
        catch(NotFoundException l_ex)
        {
            log.debug("__NotFoundExcepiton__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);    
        }
        
        //　@FXシステムコード = get会社別FXシステム条件()の戻り値.FXシステムコード 
        String l_strFxSystemCode = l_compFxConditionParams.getFxSystemCode();
        
        //２）DB検索 
        //　@FX口座番号テーブル(fx_account_code)を 
        //　@以下の条件で検索する。
        //　@[条件] 
        //　@証券会社コード = 引数.証券会社コード 
        //　@部店コード = 引数.部店コード 
        //　@FXシステムコード = １）にて取得したFXシステムコード 
        //　@顧客コード = 引数.顧客コード
        //　@※　@顧客コード 
        //　@　@（引数.顧客コード.length() == 6）の場合は、最初の6byteのみ比較する。)
        String l_strWhere = null;
        if(l_strAccountCode.length() == 6)
        {
            l_strWhere = "institution_code = ? and branch_code = ?" +
                " and fx_system_code = ? and substr(account_code ,0 ,6) = ?";
        }
        else
        {
            l_strWhere = "institution_code = ? and branch_code = ?" +
                " and fx_system_code = ? and account_code = ?";
        }
        
        Object[] l_objValue = {l_strInstitutionCode, l_strBranchCode, l_strFxSystemCode,
            l_strAccountCode};

        //３）検索結果のFX口座番号Paramsの配列をFXコース区分の昇順で 
        //　@ソートし、返却する。
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    FxAccountCodeRow.TYPE,
                    l_strWhere,
                    "fx_course_div asc",
                    null,
                    l_objValue);
        }
        catch (DataException l_ex)
        {
            log.debug("__DataException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        FxAccountCodeParams[] l_fxAccountCodeParams = null;
        if(l_lisRows.size() != 0)
        {
            l_fxAccountCodeParams = 
                new FxAccountCodeParams[l_lisRows.size()]; 
            l_lisRows.toArray(l_fxAccountCodeParams);
            
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_fxAccountCodeParams;
    }

    /**
     * (getFX口座番号)<BR>
     * 引数の条件に該当するFX口座番号の一覧を取得する。<BR>
     * <BR>
     * １）DB検索<BR>
     * 　@FX口座番号テーブル(fx_account_code)を<BR>
     * 　@以下の条件で検索する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@証券会社コード = 引数.証券会社コード<BR>
     * 　@部店コード = 引数.部店コード<BR>
     * 　@FXシステムコード = 引数.FXシステムコード<BR>
     * 　@顧客コード = 引数.顧客コード<BR>
     * 　@FXコース区分 = 引数.コース区分<BR>
     * <BR>
     * 　@※　@顧客コード<BR>
     * 　@　@（引数.顧客コード.length() == 6）の場合は、最初の6byteのみ比較する。)<BR>
     * <BR>
     * ２）検索結果を返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード<BR>
     * @@param l_strCourseDiv - (コース区分)<BR>
     * コース区分<BR>
     * @@param l_strFxSystemCode - (FXシステムコード)<BR>
     * FXシステムコード<BR>
     * @@return FxAccountCodeParams
     * @@throws WEB3BaseException, NotFoundException
     */
    public FxAccountCodeParams getFXAccountCode(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strCourseDiv,
        String l_strFxSystemCode) throws WEB3BaseException, NotFoundException
    {
        final String STR_METHOD_NAME = "getFXAccountCode(String, String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        if (l_strAccountCode == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //DB検索
        //[条件]
        //　@証券会社コード = 引数.証券会社コード
        StringBuffer l_sbSql = new StringBuffer();
        List l_lisSqlValues = new ArrayList();
        l_sbSql.append(" institution_code = ? ");
        l_lisSqlValues.add(l_strInstitutionCode);

        //　@部店コード = 引数.部店コード
        l_sbSql.append(" and branch_code = ? ");
        l_lisSqlValues.add(l_strBranchCode);

        //　@FXシステムコード = 引数.FXシステムコード
        l_sbSql.append(" and fx_system_code = ? ");
        l_lisSqlValues.add(l_strFxSystemCode);

        //　@顧客コード = 引数.顧客コード
        // （引数.顧客コード.length() == 6）の場合は、最初の6byteのみ比較する。) 
        if (l_strAccountCode.length() == 6)
        {
            l_sbSql.append(" and substr(account_code, 0, 6) = ? ");
        }
        else
        {
            l_sbSql.append(" and account_code = ? ");
        }
        l_lisSqlValues.add(l_strAccountCode);

        //　@FXコース区分 = 引数.コース区分
        l_sbSql.append(" and fx_course_div = ? ");
        l_lisSqlValues.add(l_strCourseDiv);

        List l_lisResults;
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_lisResults = l_processor.doFindAllQuery(
                FxAccountCodeRow.TYPE,
                l_sbSql.toString(),
                l_lisSqlValues.toArray());
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

        if (l_lisResults.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new NotFoundException("FX口座番号テーブルが取得できませんでした");
        }

        log.exiting(STR_METHOD_NAME);
        return new FxAccountCodeParams((FxAccountCodeRow)l_lisResults.get(0));
    }

    
    /**
     * (get会社別FXシステム条件) <BR>
     * 引数の証券会社コード、部店コードに一致する会社別FXシステム条件Paramsを返却する。 <BR>
     * <BR>
     * １）DB検索 <BR>
     * 会社別FXシステム条件テーブル(comp_fx_condition)を以下の条件で検索する。 <BR>
     * <BR>
     * [条件] <BR>
     * 証券会社コード = 引数.証券会社コード <BR>
     * 部店コード = 引数.部店コード <BR>
     * <BR>
     * ２）検索結果の会社別FXシステム条件Paramsを返却する。 <BR>
     * 
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@return webbroker3.aio.data.CompFxConditionParams
     * @@throws WEB3BaseException
     * @@throws NotFoundException
     * @@roseuid 41BD88400114
     */
    public CompFxConditionParams getCompFxCondition(
        String l_strInstitutionCode, String l_strBranchCode)
            throws WEB3BaseException, NotFoundException
    {
        String STR_METHOD_NAME ="getCompFxCondition( " +
            "String l_strInstitutionCode, String l_strBranchCode)";
        log.entering(STR_METHOD_NAME);
        
        //１）DB検索 
        //　@会社別FXシステム条件テーブル(comp_fx_condition)を以下の条件で検索する。
        //　@[条件] 
        //　@証券会社コード = 引数.証券会社コード 
        //　@部店コード = 引数.部店コード 
        String l_strWhere = "institution_code = ? and branch_code = ? ";

    
        Object[] l_objValue = {l_strInstitutionCode, l_strBranchCode};
        
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    CompFxConditionRow.TYPE,
                    l_strWhere,
                    null,
                    l_objValue);
        }
        catch (DataException l_ex)
        {
            log.debug("__DataException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        if(l_lisRows.size() == 0)
        {
            throw new NotFoundException("会社別FXシステム条件テーブルが取得できませんでした");
        }
        
        if(l_lisRows.size() > 1)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "会社別FXシステム条件テーブルに複数件");
        }

        //２）検索結果の会社別FXシステム条件Paramsを返却する。
        CompFxConditionParams l_compFxConditionParams  = (CompFxConditionParams)l_lisRows.get(0);
        
        log.exiting(STR_METHOD_NAME);
        return l_compFxConditionParams;
    }

    /**
     * (get質問) <BR>
     * 引数の証券会社コード、部店コードに一致する <BR>
     * 質問Paramsを返却する。 <BR>
     * <BR>
     * １）DB検索 <BR>
     * 質問テーブル(question)を以下の条件で検索する。 <BR>
     * <BR>
     * <引数.FXシステムコードがnull以外の場合><BR>
     * 　@[条件]<BR>
     * 　@証券会社コード = 引数.証券会社コード<BR>
     * 　@部店コード = 引数.部店コード<BR>
     * 　@質問区分 =  引数.FXシステムコード<BR>
     * <BR>
     * <引数.FXシステムコードがnullの場合><BR>
     * 　@[条件]<BR>
     * 　@証券会社コード = 引数.証券会社コード<BR>
     * 　@部店コード = 引数.部店コード<BR>
     * 　@質問区分 =  0001 (為替保証金)<BR>
     * <BR>
     * ２）検索結果を「質問番号」項目の昇順でソートし、返却する。 <BR>
     * ※検索結果が取得できなかった場合、nullを返却する。 <BR>
     * <BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strFxSystemCode - FXシステムコード
     * @@return QuestionParams[]
     * @@throws WEB3BaseException
     * @@roseuid 41C94EC1008E
     */
    public QuestionParams[] getQuestions(String l_strInstitutionCode, String l_strBranchCode,
        String l_strFxSystemCode) throws WEB3BaseException
    {
        String STR_METHOD_NAME ="getQuestions(String l_strInstitutionCode, String l_strBranchCode, "
            + "String l_strFxSystemCode)";
        log.entering(STR_METHOD_NAME);
        
        //１）DB検索 
        //<引数.FXシステムコードがnull以外の場合>
        //　@[条件]
        //　@証券会社コード = 引数.証券会社コード
        //　@部店コード = 引数.部店コード
        //　@質問区分 =  引数.FXシステムコード
        //
        //<引数.FXシステムコードがnullの場合>
        //　@[条件]
        //　@証券会社コード = 引数.証券会社コード
        //　@部店コード = 引数.部店コード
        //　@質問区分 =  0001 (為替保証金)
        String l_strWhere = "institution_code = ? and branch_code = ? and question_div = ?";

        String l_strQuestionDiv = l_strFxSystemCode;
        if (l_strFxSystemCode == null)
        {
            l_strQuestionDiv = WEB3QuestionDivDef.FX;
        }

        Object[] l_objValue = {l_strInstitutionCode, l_strBranchCode, l_strQuestionDiv};
        
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    QuestionRow.TYPE,
                    l_strWhere,
                    "to_number(question_no) asc",
                    null,
                    l_objValue);
        }
        catch (DataException l_ex)
        {
            log.debug("__DataException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //２）検索結果を「質問番号」項目の昇順でソートし、返却する。 
        //　@※検索結果が取得できなかった場合、nullを返却する。
        if(l_lisRows.size() == 0)
        {
            return null;
        }
        
        QuestionParams[] l_questionParams = new QuestionParams[l_lisRows.size()];
        l_lisRows.toArray(l_questionParams);

        log.exiting(STR_METHOD_NAME);
        return l_questionParams;
    }

    /**
     * (get質問回答) <BR>
     * 引数の証券会社コード、部店コード、識別コードに一致する <BR>
     * 質問回答Paramsを返却する。 <BR>
     * <BR>
     * １）DB検索 <BR>
     * 質問回答テーブル(question_answer)を以下の条件で検索する。 <BR>
     * <BR>
     * [条件] <BR>
     * 証券会社コード = 引数.証券会社コード <BR>
     * 部店コード = 引数.部店コード <BR>
     * 質問区分 = 引数.質問区分<BR>
     * 識別コード = 引数.識別コード <BR>
     * <BR>
     * ２）検索結果を「質問番号」項目を数値型の昇順でソートし、返却する。<BR>
     * ※検索結果が取得できなかった場合、nullを返却する。 <BR>
     * 
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strQuestionDiv - 質問区分
     * @@param l_strRequestNumber - 識別コード
     * @@return QuestionAnswerParams[]
     * @@throws WEB3BaseException
     * @@roseuid 41BE3FF701C1
     */
    public QuestionAnswerParams[] getQuestionAnswers(
        String l_strInstitutionCode, String l_strBranchCode,
        String l_strQuestionDiv, String l_strRequestNumber) throws  WEB3BaseException
    {
        String STR_METHOD_NAME ="getQuestionAnswers(String l_strInstitutionCode, " +
            "String l_strBranchCode, String l_strQuestionDiv, String l_strRequestNumber)";
        log.entering(STR_METHOD_NAME);
        
        //１）DB検索 
        //　@質問回答テーブル(question_answer)を以下の条件で検索する。
        //　@[条件] 
        //　@証券会社コード = 引数.証券会社コード 
        //　@部店コード = 引数.部店コード 
        //  質問区分 = 引数.質問区分
        //　@識別コード = 引数.識別コード 
        String l_strWhere = "institution_code = ? and branch_code = ? " +
            "and question_div = ? and order_request_number = ?";
        
        Object[] l_objValue = {l_strInstitutionCode, l_strBranchCode, l_strQuestionDiv, l_strRequestNumber};
        
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    QuestionAnswerRow.TYPE,
                    l_strWhere,
                    "cast(question_no as integer) asc",
                    null,
                    l_objValue);
        }
        catch (DataException l_ex)
        {
            log.debug("__DataException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //２）検索結果を「質問番号」項目を数値型の昇順でソートし、返却する。
        //　@※検索結果が取得できなかった場合、nullを返却する。
        if(l_lisRows.size() == 0)
        {
            return null;
        }
        
        QuestionAnswerParams[] l_questionAnswerParams = 
            new QuestionAnswerParams[l_lisRows.size()];
        l_lisRows.toArray(l_questionAnswerParams);

        log.exiting(STR_METHOD_NAME);
        return l_questionAnswerParams;
    }

    /**
     * (getGFT口座開設状況) <BR>
     * 引数の証券会社コード、部店コード、識別コードに <BR>
     * 該当するGFT口座開設状況Paramsを返却する。 <BR>
     * <BR>
     * １）DB検索 <BR>
     * 以下の条件で、GFT口座開設状況テーブル(gft_account_open_status)を検索する。 <BR>
     * <BR>
     * 証券会社コード = 引数.証券会社コード <BR>
     * 部店コード = 引数.部店コード <BR>
     * 識別コード = 引数.識別コード <BR>
     * <BR>
     * 検索結果が取得できなかった場合、nullを返却する。 <BR>
     * <BR>
     * ２）検索結果を返却する。 <BR>
     * 
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strRequestNumber - 識別コード
     * @@return webbroker3.aio.data.GftAccountOpenStatusParams
     * @@throws WEB3BaseException
     * @@roseuid 41BE4E4C0396
     */
    public GftAccountOpenStatusParams getGFTAccountOpenStatus(
        String l_strInstitutionCode, String l_strBranchCode,
        String l_strRequestNumber) throws WEB3BaseException
    {
        String STR_METHOD_NAME ="getGFTAccountOpenStatus(String l_strInstitutionCode, " +
            "String l_strBranchCode, String l_strRequestNumber)";
        log.entering(STR_METHOD_NAME);
        
        //１）DB検索 
        //　@以下の条件で、GFT口座開設状況テーブル(gft_account_open_status) 
        //　@を検索する。 
        //　@証券会社コード = 引数.証券会社コード 
        //　@部店コード = 引数.部店コード 
        //　@識別コード = 引数.識別コード
        String l_strWhere = "institution_code = ? and branch_code = ? and order_request_number = ?";
    
        Object[] l_objValue = {l_strInstitutionCode, l_strBranchCode, l_strRequestNumber};
        
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    GftAccountOpenStatusRow.TYPE,
                    l_strWhere,
                    null,
                    l_objValue);
        }
        catch (DataException l_ex)
        {
            log.debug("__DataException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    
        //　@検索結果が取得できなかった場合、nullを返却する。
        if(l_lisRows.size() == 0)
        {
            return null;
        }
        
        if(l_lisRows.size() > 1)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "GFT口座開設状況テーブルに複数件");
        }
        
        //２）検索結果を返却する。
        GftAccountOpenStatusParams l_gftAccountOpenStatusParams = 
            (GftAccountOpenStatusParams)l_lisRows.get(0);
        
        log.exiting(STR_METHOD_NAME);
        return l_gftAccountOpenStatusParams;
    }

    /**
     * (getGFT口座開設状況) <BR>
     * 引数の条件に該当するGFT口座開設状況Paramsの <BR>
     * 一覧を返却する。 <BR>
     * <BR>
     * １）QueryProcessor.doFindAllQuery()メソッドをコールする。 <BR>
     * <BR>
     * [doFindAllQuery()にセットするパラメータ] <BR>
     * rowType： GFT口座開設状況Row.TYPE <BR>
     * where： パラメータ.検索条件文字列 <BR>
     * orderBy： パラメータ.ソート条件 <BR>
     * conditions： null <BR>
     * bindVars： パラメータ.検索条件データコンテナ <BR>
     * <BR>
     * 検索結果が取得できなかった場合、nullを返却する。 <BR>
     * <BR>
     * ２）検索結果を返却する。 <BR>
     * 
     * @@param l_strQueryString - 検索条件文字列
     * @@param l_queryContainer - 検索条件データコンテナ
     * @@param l_strSortCond - ソート条件
     * @@return webbroker3.aio.data.GftAccountOpenStatusParams[]
     * @@throws WEB3BaseException
     * @@roseuid 41BD89FD0233
     */
    public GftAccountOpenStatusParams[] getGFTAccountOpenStatuses(
        String l_strQueryString, String[] l_queryContainer, String l_strSortCond)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME ="getGFTAccountOpenStatuses(String l_strQueryString," +
            " String[] l_queryContainer, String l_strSortCond)";
        log.entering(STR_METHOD_NAME);
        
        //１）QueryProcessor.doFindAllQuery()メソッドをコールする。
        //　@[doFindAllQuery()にセットするパラメータ] 
        //　@　@rowType：　@GFT口座開設状況Row.TYPE 
        //　@　@where：　@パラメータ.検索条件文字列 
        //　@　@orderBy：　@パラメータ.ソート条件 
        //　@　@conditions：　@null 
        //　@　@bindVars：　@パラメータ.検索条件データコンテナ 
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    GftAccountOpenStatusRow.TYPE,
                    l_strQueryString,
                    l_strSortCond,
                    null,
                    l_queryContainer);
        }
        catch (DataException l_ex)
        {
            log.debug("__DataException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }   
        
        GftAccountOpenStatusParams[] l_gftAccountOpenStatusParams = null;
        //　@検索結果が取得できなかった場合、要素数が0の配列が返却する。
        if(l_lisRows.size() == 0)
        {
            l_gftAccountOpenStatusParams = new GftAccountOpenStatusParams[0];
            return l_gftAccountOpenStatusParams;
        }
       
        //２）検索結果を返却する。
        l_gftAccountOpenStatusParams = 
            new GftAccountOpenStatusParams[l_lisRows.size()];
        
        l_lisRows.toArray(l_gftAccountOpenStatusParams);
        
        log.exiting(STR_METHOD_NAME);
        return l_gftAccountOpenStatusParams;
    }

    /**
     * (getGFT振替状況) <BR>
     * 引数の証券会社コード、部店コード、識別コードに <BR>
     * 該当するGFT振替状況Paramsを返却する。 <BR>
     * <BR>
     * １）DB検索 <BR>
     * 以下の条件で、GFT振替状況テーブル(gft_transfer_status) <BR>
     * を検索する。 <BR>
     * <BR>
     * 証券会社コード = 引数.証券会社コード <BR>
     * 部店コード = 引数.部店コード <BR>
     * 識別コード = 引数.識別コード <BR>
     * <BR>
     * 検索結果が取得できなかった場合、nullを返却する。 <BR>
     * <BR>
     * ２）検索結果を返却する。 <BR>
     * 
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strRequestNumber - 識別コード
     * @@return webbroker3.aio.data.GftTransferStatusParams
     * @@throws WEB3BaseException
     * @@roseuid 41C12D9E00E9
     */
    public GftTransferStatusParams getGFTTransferStatus(
        String l_strInstitutionCode, String l_strBranchCode,
        String l_strRequestNumber) throws WEB3BaseException
    {
        String STR_METHOD_NAME ="getGFTTransferStatus(String l_strInstitutionCode," +
            " String l_strBranchCode, String l_strRequestNumber)";
        log.entering(STR_METHOD_NAME);
        
        //１）DB検索 
        //　@以下の条件で、GFT振替状況テーブル(gft_transfer_status) 
        //　@を検索する。 
        //　@証券会社コード = 引数.証券会社コード 
        //　@部店コード = 引数.部店コード 
        //　@識別コード = 引数.識別コード
        String l_strWhere = "institution_code = ? and branch_code = ? and order_request_number = ?";
        
        Object[] l_objValue = {l_strInstitutionCode, l_strBranchCode, l_strRequestNumber};
        
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    GftTransferStatusRow.TYPE,
                    l_strWhere,
                    null,
                    l_objValue);
        }
        catch (DataException l_ex)
        {
            log.debug("__DataException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //　@検索結果が取得できなかった場合、nullを返却する。
        if(l_lisRows.size() == 0)
        {
            return null;
        }
        
        if(l_lisRows.size() > 1)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "GFT振替状況テーブルに複数件");
        }
        
        //２）検索結果を返却する。
        GftTransferStatusParams l_gftTransferStatusParams = 
            (GftTransferStatusParams)l_lisRows.get(0);
        
        log.exiting(STR_METHOD_NAME);
        return l_gftTransferStatusParams;
    }

    /**
     * (getGFT振替状況) <BR>
     * 引数の証券会社コード、部店コード、顧客コード、入出金番号に <BR>
     * 該当するGFT振替状況Paramsを返却する。 <BR>
     *  <BR>
     * １）DB検索 <BR>
     * 　@以下の条件で、GFT振替状況テーブル(gft_transfer_status) <BR>
     * 　@を検索する。 <BR>
     *  <BR>
     * 　@証券会社コード = 引数.証券会社コード <BR>
     * 　@部店コード = 引数.部店コード <BR>
     * 　@※顧客コード = 引数.顧客コード <BR>
     * 　@入出金番号 = 入出金番号 <BR>
     *  <BR>
     * 　@検索結果が取得できなかった場合、nullを返却する。 <BR>
     *  <BR>
     * ※　@顧客コード <BR>
     * 　@引数.顧客コードが6桁の場合は、最初の6byteのみ比較する。 <BR>
     *  <BR>
     * ２）検索結果を返却する。 <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * @@param l_strAccountCode - (顧客コード)<BR>
     * @@param l_strCashInOutNumber - (入出金番号)<BR>
     * @@return GftTransferStatusParams
     * @@throws WEB3BaseException
     * @@roseuid 41C130DC001E
     */
    public GftTransferStatusParams getGFTTransferStatus(
        String l_strInstitutionCode, String l_strBranchCode,
        String l_strAccountCode, String l_strCashInOutNumber) throws WEB3BaseException
    {
        String STR_METHOD_NAME ="getGFTTransferStatus(String,String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //１）DB検索
        //　@以下の条件で、GFT振替状況テーブル(gft_transfer_status)
        //　@を検索する。
        //　@証券会社コード = 引数.証券会社コード
        //　@部店コード = 引数.部店コード
        //　@※顧客コード = 引数.顧客コード
        //　@入出金番号 = 入出金番号
        //　@検索結果が取得できなかった場合、nullを返却する。
        //※　@顧客コード
        //　@引数.顧客コードが6桁の場合は、最初の6byteのみ比較する。
        String l_strWhere = " institution_code = ? " +
                            " and branch_code = ? " +
                            " and account_code = ? " +
                            " and fx_upload_number = ? ";

        l_strWhere = " institution_code = ? and branch_code = ? ";
        if (l_strAccountCode.length() == 6)
        {
            l_strWhere = l_strWhere + " and substr(account_code, 0, 6) = ? ";
        }
        else
        {
            l_strWhere = l_strWhere + " and account_code = ? ";
        }
        
        Object[] l_objValue = null ;
        if (l_strCashInOutNumber == null)
        {
            l_strWhere = l_strWhere + " and fx_upload_number is null ";
            l_objValue = new Object[3];
            l_objValue[0] = l_strInstitutionCode;
            l_objValue[1] = l_strBranchCode;
            l_objValue[2] = l_strAccountCode;
        }
        else
        {
            l_strWhere = l_strWhere + " and fx_upload_number = ? ";
            l_objValue = new Object[4];
            l_objValue[0] = l_strInstitutionCode;
            l_objValue[1] = l_strBranchCode;
            l_objValue[2] = l_strAccountCode;
            l_objValue[3] = l_strCashInOutNumber;
        }

        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    GftTransferStatusRow.TYPE,
                    l_strWhere,
                    null,
                    l_objValue);
        }
        catch (DataQueryException l_dqex)
        {
            log.error("テーブルに該当するデータがありません。", l_dqex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqex.getMessage(),
                l_dqex);
        }
        catch (DataNetworkException l_dnex)
        {
            log.error("DBアクセスエラー", l_dnex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dnex.getMessage(),
                l_dnex);
        }
        
        //　@検索結果が取得できなかった場合、nullを返却する。
        if (l_lisRows.size() == 0)
        {
            log.debug("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        if (l_lisRows.size() > 1)
        {
            log.debug("GFT振替状況テーブルに複数件");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "GFT振替状況テーブルに複数件");
        }
        
        //２）検索結果を返却する。
        GftTransferStatusParams l_gftTransferStatusParams = (GftTransferStatusParams) l_lisRows.get(0);
        
        log.exiting(STR_METHOD_NAME);
        return l_gftTransferStatusParams;
    }

    /**
     * (getGFT電文保存) <BR>
     * 引数の電文種別区分、処理区分、証券会社コード、部店コード、識別コードに <BR>
     * 該当するGFT電文保存Paramsを返却する。 <BR>
     * <BR>
     * １）DB検索 <BR>
     * 以下の条件で、GFT電文保存テーブル(gft_message) <BR>
     * を検索する。 <BR>
     * <BR>
     * 電文種別区分 = 引数.電文種別区分 <BR>
     * 処理区分 = 引数.処理区分 <BR>
     * 会社コード = 引数.証券会社コード <BR>
     * 部店コード = 引数.部店コード <BR>
     * 識別コード = 引数.識別コード <BR>
     * <BR>
     * 検索結果が取得できなかった場合、nullを返却する。 <BR>
     * <BR>
     * 検索結果が2件以上あった場合、例外をthrowする。 <BR>
     * <BR>
     * class: WEB3SystemLayerException <BR>
     * tag: SYSTEM_ERROR_80006 <BR>
     * <BR>
     * ２）検索結果を返却する。 <BR>
     * 
     * @@param l_strMessageDiv - 電文種別区分
     * @@param l_strOperationDiv - 処理区分
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strRequestNumber - 識別コード
     * @@return webbroker3.aio.data.GftMessageParams
     * @@throws WEB3BaseException
     * @@roseuid 41C158CA01B4
     */
    public GftMessageParams getGFTMessage(String l_strMessageDiv,
        String l_strOperationDiv, String l_strInstitutionCode,
        String l_strBranchCode, String l_strRequestNumber)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME ="getGFTMessage(String l_strMessageDiv, " +
            "String l_strOperationDiv, String l_strInstitutionCode, " +
            "String l_strBranchCode, String l_strRequestNumber)";
        log.entering(STR_METHOD_NAME);

        //１）DB検索 
        //　@以下の条件で、GFT電文保存テーブル(gft_message) 
        //　@を検索する。
        //　@電文種別区分 = 引数.電文種別区分 
        //　@処理区分 = 引数.処理区分 
        //　@会社コード = 引数.証券会社コード 
        //　@部店コード = 引数.部店コード 
        //　@識別コード = 引数.識別コード 
        String l_strWhere = "message_div = ? and operation = ? " +
            "and cpy = ? and brn = ? and req = ? ";
    
        Object[] l_objValue = {l_strMessageDiv, l_strOperationDiv,
            l_strInstitutionCode, l_strBranchCode, l_strRequestNumber};
        
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    GftMessageRow.TYPE,
                    l_strWhere,
                    null,
                    l_objValue);
        }
        catch (DataException l_ex)
        {
            log.debug("__DataException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //　@検索結果が取得できなかった場合、nullを返却する。 
        if(l_lisRows.size() == 0)
        {
            return null;
        }
        
        //　@検索結果が2件以上あった場合、例外をthrowする。
        if(l_lisRows.size() >= 2)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "GFT電文保存データ不整合エラー");
        }
        
        //２）検索結果を返却する。
        GftMessageParams l_gftMessageParams = (GftMessageParams)l_lisRows.get(0);
        
        log.exiting(STR_METHOD_NAME);
        return l_gftMessageParams;
    }

    /**
     * (insertFX顧客) <BR>
     * GFT口座開設状況Paramsの内容より、 <BR>
     * FX顧客テーブル(fx_account)に行のinsertを行う。 <BR>
     * <BR>
     * 挿入する行の内容は下記を参照。 <BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新 <BR>
     * 「管理者・口座開設管理_FX顧客テーブルDB更新仕様.xls」 <BR>
     * 
     * @@param l_gftAccontOpenStatusParams - GFT口座開設状況Paramsオブジェクト
     * @@param l_strUpdaterCode - 更新者コード
     * @@throws WEB3BaseException
     * @@roseuid 41C810BE034C
     */
    public void insertFXAccount(GftAccountOpenStatusParams l_gftAccontOpenStatusParams,
        String l_strUpdaterCode) throws WEB3BaseException
    {
        String STR_METHOD_NAME ="insertFXAccount(GftAccountOpenStatusParams l_gftAccontOpenStatusParams, " +
            "String l_strUpdaterCode)";
        log.entering(STR_METHOD_NAME);

        if(l_gftAccontOpenStatusParams == null )
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        FxAccountParams l_fxAccountParams = new FxAccountParams();
        
        //1) FX顧客ID
        //説明 : FXデータ制御サービスImpl.get新規FX顧客ID(this.証券会社コード, this.部店コード, 
        //this.顧客コード、this.FXシステムコード)
        String l_strFXAccountID =  this.getNewFXAccountID(
                l_gftAccontOpenStatusParams.getInstitutionCode(),
                l_gftAccontOpenStatusParams.getBranchCode(), 
                l_gftAccontOpenStatusParams.getAccountCode(),
                l_gftAccontOpenStatusParams.getFxSystemCode());
            
        l_fxAccountParams.setFxAccountId(Long.parseLong(l_strFXAccountID));
        
        //2) 証券会社コード
        //説明 : GFT口座開設状況Params.証券会社コード
        l_fxAccountParams.setInstitutionCode(l_gftAccontOpenStatusParams.getInstitutionCode());
        
        //3) 部店コード
        //説明 : GFT口座開設状況Params.部店コード
        l_fxAccountParams.setBranchCode(l_gftAccontOpenStatusParams.getBranchCode());

        //FXシステムコード
        //説明 : GFT口座開設状況Params.FXシステムコード
        l_fxAccountParams.setFxSystemCode(l_gftAccontOpenStatusParams.getFxSystemCode());

        try
        {
            //5) 顧客コード
            //説明 : GFT口座開設状況Params.顧客コード
            l_fxAccountParams.setAccountCode(l_gftAccontOpenStatusParams.getAccountCode());
            
            //6) FX口座区分
            //説明 : 1：開設済
            l_fxAccountParams.setFxAccountDiv(WEB3AioFxAccountOpenDivDef.OPEN);
            
            //7) 名前（姓）
            //説明 : GFT口座開設状況Params.名前（姓）
            l_fxAccountParams.setFxLastName(l_gftAccontOpenStatusParams.getLastName());
            
            //8) 名前（名）
            //説明 : GFT口座開設状況Params.名前（名）
            l_fxAccountParams.setFxFirstName(l_gftAccontOpenStatusParams.getFirstName());
            
            //9) FXメールアドレス
            //説明 : GFT口座開設状況Params.GFTメールアドレス -------> GFT口座開設状況Params.メールアドレス
            l_fxAccountParams.setFxMailAddress(l_gftAccontOpenStatusParams.getMailAddress());
            
            //10) FXログインID
            //説明 : GFT口座開設状況Params.GFTログインID ---------> GFT口座開設状況Params.ログインID
            l_fxAccountParams.setFxLoginId(Long.parseLong(l_gftAccontOpenStatusParams.getLoginId()));
            
            //11) 更新者コード
            //説明 : 引数.更新者コード
            l_fxAccountParams.setLastUpdater(l_strUpdaterCode);
            
            //12) 作成日付
            //説明 : 現在時刻
            l_fxAccountParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //13) 更新日付
            //説明 : 現在時刻
            l_fxAccountParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            //insert FX顧客テーブル
            WEB3DataAccessUtility.insertRow(l_fxAccountParams);
        }
        catch (DataException l_ex)
        {
            log.debug("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insertFX顧客) <BR>
     * GFT結果通知電文の内容でFX顧客テーブル(fx_account)に行のinsertを行う。 <BR>
     * <BR>
     * 挿入する行の内容は下記を参照。 <BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新 <BR>
     * 「FX口座開設_FX顧客テーブル.xls」 <BR>
     * 
     * @@param l_fxGftResultNoticeTelegramUnit - GFT結果通知電文明細
     * @@param l_gftAccontOpenStatusParams - GFT口座開設状況Params
     * @@throws WEB3BaseException
     * @@roseuid 41C97177009A
     */
    public void insertFXAccount(
            WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit, 
            GftAccountOpenStatusParams l_gftAccontOpenStatusParams)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME ="insertFXAccount(WEB3FXGftResultNoticeTelegramUnit " +
            "l_fxGftResultNoticeTelegramUnit)";
        log.entering(STR_METHOD_NAME);
        
        if(l_fxGftResultNoticeTelegramUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        FxAccountParams l_fxAccountParams = new FxAccountParams();
        
        //1) FX顧客ID
        //説明 : get新規FX顧客ID(this.証券会社コード, this.部店コード, 
        //this.顧客コード、this.FXシステムコード)
        String l_strFXAccountID = this.getNewFXAccountID(
            l_fxGftResultNoticeTelegramUnit.institutionCode,
            l_fxGftResultNoticeTelegramUnit.branchCode, 
            l_fxGftResultNoticeTelegramUnit.accountCode,
            l_gftAccontOpenStatusParams.getFxSystemCode());
        log.debug("l_strFXAccountID = " + l_strFXAccountID);
        l_fxAccountParams.setFxAccountId(Long.parseLong(l_strFXAccountID));
        
        //2) 証券会社コード
        //説明 : 引数.GFT結果通知電文明細.会社コード
        l_fxAccountParams.setInstitutionCode(l_fxGftResultNoticeTelegramUnit.institutionCode);
        
        //3) 部店コード
        //説明 : 引数.GFT結果通知電文明細.部店コード
        l_fxAccountParams.setBranchCode(l_fxGftResultNoticeTelegramUnit.branchCode);

        //FXシステムコード
        //説明 : 引数.GFT口座開設状況Params.FXシステムコード
        l_fxAccountParams.setFxSystemCode(l_gftAccontOpenStatusParams.getFxSystemCode());

        try
        {
            //5) 顧客コード
            //説明 : 引数.GFT結果通知電文明細.顧客コード
            String l_strAccountCodeToSet = l_fxGftResultNoticeTelegramUnit.accountCode;
            if (l_strAccountCodeToSet != null && l_strAccountCodeToSet.length() == 6)
            {
                WEB3GentradeAccountManager l_web3GentradeAccountManager =
                    (WEB3GentradeAccountManager)GtlUtils.getAccountManager();
                
                MainAccount l_mainAccount = 
                    l_web3GentradeAccountManager.getMainAccount(
                        l_fxGftResultNoticeTelegramUnit.institutionCode,
                        l_fxGftResultNoticeTelegramUnit.branchCode,
                        l_fxGftResultNoticeTelegramUnit.accountCode);
                l_strAccountCodeToSet = l_mainAccount.getAccountCode();
            }
            
            l_fxAccountParams.setAccountCode(l_strAccountCodeToSet);
            
            //6) FX口座区分
            //説明 : 1：開設済
            l_fxAccountParams.setFxAccountDiv(WEB3AioFxAccountOpenDivDef.OPEN);
            
            //7) 名前（姓）
            //説明 : 引数.GFT口座開設状況Params.名前（姓）
            l_fxAccountParams.setFxLastName(l_gftAccontOpenStatusParams.getLastName());
            
            //8) 名前（名）
            //説明 : 引数.GFT口座開設状況Params.名前（名）
            l_fxAccountParams.setFxFirstName(l_gftAccontOpenStatusParams.getFirstName());
            
            //9) FXメールアドレス
            //説明 : 引数.GFT結果通知電文明細.メールアドレス
            l_fxAccountParams.setFxMailAddress(l_fxGftResultNoticeTelegramUnit.fxMailAddress);
            
            //10) FXログインID
            //説明 : 引数.GFT結果通知電文明細.初期ログインID
            l_fxAccountParams.setFxLoginId(Long.parseLong(l_fxGftResultNoticeTelegramUnit.fxFirstLoginId));
            
            //11) 更新者コード
            //説明 : null
            l_fxAccountParams.setLastUpdater(null);
            
            //12) 作成日付
            //説明 : 現在時刻
            l_fxAccountParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //13) 更新日付
            //説明 : 現在時刻
            l_fxAccountParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            //insert FX顧客テーブル
            WEB3DataAccessUtility.insertRow(l_fxAccountParams);
        }
        catch (DataException l_ex)
        {
            log.debug("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insertFX口座番号) <BR>
     * GFT口座開設状況Params、FX口座情報の内容より、 <BR>
     * FX口座番号テーブル(fx_account_code)に行のinsertを行う。 <BR>
     * <BR>
     * 挿入する行の内容は下記を参照。 <BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新 <BR>
     * 「管理者・口座開設管理_FX口座番号テーブルDB更新仕様.xls」 <BR>
     * 
     * @@param l_gftAccountOpenStatusParams - GFT口座開設状況Paramsオブジェクト
     * @@param l_fxAccInformation - FX口座情報オブジェクト
     * @@param l_strUpdaterCode - 更新者コード
     * @@throws WEB3BaseException
     * @@roseuid 41C81168021E
     */
    public void insertFXAccountCode(
        GftAccountOpenStatusParams l_gftAccountOpenStatusParams,
        WEB3FXAccInformationUnit l_fxAccInformation, String l_strUpdaterCode)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "insertFXAccountCode(GftAccountOpenStatusParams l_gftAccountOpenStatusParams, " +
            "WEB3FXAccInformationUnit l_fxAccInformation, String l_strUpdaterCode)";
        log.entering(STR_METHOD_NAME);
        
        if(l_gftAccountOpenStatusParams == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        FxAccountCodeParams l_fxAccountCodeParams = new FxAccountCodeParams();
        
        //1) 証券会社コード
        //説明 : GFT口座開設状況Params.証券会社コード
        l_fxAccountCodeParams.setInstitutionCode(l_gftAccountOpenStatusParams.getInstitutionCode());
        
        //2) 部店コード
        //説明 : GFT口座開設状況Params.部店コード
        l_fxAccountCodeParams.setBranchCode(l_gftAccountOpenStatusParams.getBranchCode());

        //FXシステムコード
        //説明 : GFT口座開設状況Params.FXシステムコード
        l_fxAccountCodeParams.setFxSystemCode(l_gftAccountOpenStatusParams.getFxSystemCode());

        try
        {
            //4) 顧客コード
            //説明 : GFT口座開設状況Params.顧客コード
            l_fxAccountCodeParams.setAccountCode(l_gftAccountOpenStatusParams.getAccountCode());
            
            //5) FXコース区分
            //説明 : FX口座情報.コース区分
            l_fxAccountCodeParams.setFxCourseDiv(l_fxAccInformation.fxCourseDiv);
            
            //6) FX口座番号
            //説明 : FX口座情報.口座番号
            l_fxAccountCodeParams.setFxAccountCode(l_fxAccInformation.fxAccountCode);

            //7) 更新者コード
            //説明 : 引数.更新者コード
            l_fxAccountCodeParams.setLastUpdater(l_strUpdaterCode);
            
            //8) 作成日付
            //説明 : 現在時刻
            l_fxAccountCodeParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //9) 更新日付
            //説明 : 現在時刻
            l_fxAccountCodeParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            //insert FX顧客テーブル
            WEB3DataAccessUtility.insertRow(l_fxAccountCodeParams);
        }
        catch (DataException l_ex)
        {
            log.debug("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insertFX口座番号)<BR>
     * GFT結果通知電文の内容とFXシステムコードでFX口座番号テーブル(fx_account_code)<BR>
     * 　@に行のinsertを行う<BR>
     * ※為替保証金口座番号もしくは、CFD口座番号がinsertされる。<BR>
     * <BR>
     * insert処理は引数.GFT結果通知電文明細.為替保証金口座情報一覧の要素数分実施する。<BR>
     * <BR>
     * 挿入する行の内容は下記を参照。<BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新<BR>
     * 「FX口座開設_FX口座番号テーブル.xls/ <BR>
     * (FX口座開設)FX口座番号テーブル_DB更新仕様」<BR>
     * <BR>
     * @@param l_fxGftResultNoticeTelegramUnit - (GFT結果通知電文明細)<BR>
     * GFT結果通知電文明細<BR>
     * @@param l_strFxSystemCode - (FXシステムコード)<BR>
     * FXシステムコード<BR>
     * @@param l_strSimultaneousFxSystemCode - (同時開設FXシステムコード)<BR>
     * 同時開設FXシステムコード<BR>
     * @@throws WEB3BaseException
     */
    public void insertFXAccountCode(
        WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit,
        String l_strFxSystemCode,
        String l_strSimultaneousFxSystemCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "insertFXAccountCode(WEB3FXGftResultNoticeTelegramUnit, String, String)";
        log.entering(STR_METHOD_NAME);

        if(l_fxGftResultNoticeTelegramUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //insert処理は引数.GFT結果通知電文明細.為替保証金口座情報一覧の要素数分実施する。
        WEB3FXAccInformationUnit[] l_fXAccInformationUnits =
            l_fxGftResultNoticeTelegramUnit.fxAccInformationList;
        int l_intFXAccInformationUnitsLength = 0;
        if (l_fXAccInformationUnits != null)
        {
        	l_intFXAccInformationUnitsLength = l_fXAccInformationUnits.length;
        }

        //挿入する行の内容は下記を参照
        //【ｘTrade】補足資料.DB更新
        //「FX口座開設_FX口座番号テーブル.xls/
        //(FX口座開設)FX口座番号テーブル_DB更新仕様」
        FxAccountCodeParams l_fxAccountCodeParams =
            new FxAccountCodeParams();

        //証券会社コード: 引数.GFT結果通知電文明細.会社コード
        l_fxAccountCodeParams.setInstitutionCode(
            l_fxGftResultNoticeTelegramUnit.institutionCode);

        //部店コード: 引数.GFT結果通知電文明細.部店コード
        l_fxAccountCodeParams.setBranchCode(
            l_fxGftResultNoticeTelegramUnit.branchCode);

        //顧客コード: 引数.GFT結果通知電文明細.顧客コード
        String l_strAccountCode = l_fxGftResultNoticeTelegramUnit.accountCode;
        if (l_strAccountCode != null && l_strAccountCode.length() == 6)
        {
            WEB3GentradeAccountManager l_web3GentradeAccountManager =
                (WEB3GentradeAccountManager)GtlUtils.getAccountManager();

            MainAccount l_mainAccount =
                l_web3GentradeAccountManager.getMainAccount(
                    l_fxGftResultNoticeTelegramUnit.institutionCode,
                    l_fxGftResultNoticeTelegramUnit.branchCode,
                    l_fxGftResultNoticeTelegramUnit.accountCode);
            l_strAccountCode = l_mainAccount.getAccountCode();
        }
        l_fxAccountCodeParams.setAccountCode(l_strAccountCode);

        //更新者コード: null
        l_fxAccountCodeParams.setLastUpdater(null);

        for (int i = 0; i < l_intFXAccInformationUnitsLength; i++)
        {
            //FXシステムコード:
            //<引数.GFT結果通知電文明細.為替保証金口座情報一覧[n].コース区分==”1”or”2”の場合>
            if (WEB3GftTransStatusCourseDivDef.ONE_COSE.equals(
                l_fXAccInformationUnits[i].fxCourseDiv)
                || WEB3GftTransStatusCourseDivDef.TEN_COSE.equals(
                    l_fXAccInformationUnits[i].fxCourseDiv))
            {
                //引数.FXシステムコード
                l_fxAccountCodeParams.setFxSystemCode(l_strFxSystemCode);
            }
            //<引数.GFT結果通知電文明細.為替保証金口座情報一覧[n].コース区分==”3”の場合>
            else if (WEB3GftTransStatusCourseDivDef.CFD_COURSE.equals(
                l_fXAccInformationUnits[i].fxCourseDiv))
            {
                //<引数.同時開設FXシステムコード==nullの場合>
                if (WEB3StringTypeUtility.isEmpty(l_strSimultaneousFxSystemCode))
                {
                    //引数.FXシステムコード
                    l_fxAccountCodeParams.setFxSystemCode(l_strFxSystemCode);
                }
                //<引数.同時開設FXシステムコードがnullでない場合>
                else
                {
                    //引数.同時開設FXシステムコード
                    l_fxAccountCodeParams.setFxSystemCode(
                        l_strSimultaneousFxSystemCode);
                }
            }

            //FXコース区分:引数.GFT結果通知明細.為替保証金口座情報一覧[n].コース区分
            l_fxAccountCodeParams.setFxCourseDiv(
                l_fXAccInformationUnits[i].fxCourseDiv);

            //FX口座番号:引数.GFT結果通知明細.為替保証金口座情報一覧[n].口座番号
            l_fxAccountCodeParams.setFxAccountCode(
                l_fXAccInformationUnits[i].fxAccountCode);

            //作成日付: 現在時刻
            Timestamp l_tsSystemTime = GtlUtils.getSystemTimestamp();
            l_fxAccountCodeParams.setCreatedTimestamp(l_tsSystemTime);

            //更新日付: 現在時刻
            l_fxAccountCodeParams.setLastUpdatedTimestamp(l_tsSystemTime);

            try
            {
                WEB3DataAccessUtility.insertRow(l_fxAccountCodeParams);
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
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insert質問回答) <BR>
     * FX取引同意質問情報の内容で質問回答テーブル(question_answer)に行のinsertを行う。<BR>
     * ※引数.FX取引同意質問情報一覧の要素数分のLoop処理を行い、<BR>
     * <BR>
     * 要素ごとに行のinsertを行う。 <BR>
     * <BR>
     * <引数.FXシステムコードがnull以外の場合><BR>
     * 　@質問区分 =  引数.FXシステムコード<BR>
     * <BR>
     * <引数.FXシステムコードがnullの場合><BR>
     * 　@質問区分 =  0001 (為替保証金)<BR>
     * <BR>
     * 上記以外の項目の内容は下記を参照。<BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新 <BR>
     * 「FX口座開設_質問回答テーブル.xls」 <BR>
     * 
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strRequestNumber - 識別コード
     * @@param l_fxTradeAgreementList - FX取引同意質問情報の一覧
     * @@param l_strFxSystemCode - FXシステムコード
     * @@throws WEB3BaseException
     * @@roseuid 41CF64BF024C
     */
    public void insertQuestionAnswer(String l_strInstitutionCode,
        String l_strBranchCode, String l_strRequestNumber,
        WEB3FXTradeAgreementUnit[] l_fxTradeAgreementList,
        String l_strFxSystemCode) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "insertQuestionAnswer(String l_strInstitutionCode, " +
            "String l_strBranchCode, String l_strRequestNumber, " +
            "WEB3FXTradeAgreementUnit[] l_fxTradeAgreementList, " +
            "String l_strFxSystemCode)";
        log.entering(STR_METHOD_NAME);        
        
        if(l_fxTradeAgreementList == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        try
        {
            for(int i = 0 ; i < l_fxTradeAgreementList.length; i++)
            {
                QuestionAnswerParams l_questionAnswerParamsForUpdate = new QuestionAnswerParams();
                
                //1) 証券会社コード
                //説明 : 引数.証券会社コード
                l_questionAnswerParamsForUpdate.setInstitutionCode(l_strInstitutionCode);
                
                //2) 部店コード
                //説明 : 引数.部店コード
                l_questionAnswerParamsForUpdate.setBranchCode(l_strBranchCode);
                
                //3) 質問区分
                //引数.FXシステムコードがnullでない場合
                //　@ 引数.FXシステムコード
                //引数.FXシステムコードがnullの場合
                //　@　@0001：為替保証金
                String l_strQuestionDiv = l_strFxSystemCode;
                if (l_strFxSystemCode == null)
                {
                    l_strQuestionDiv = WEB3QuestionDivDef.FX;
                }
                l_questionAnswerParamsForUpdate.setQuestionDiv(l_strQuestionDiv);
                
                //4) 識別コー
                //説明 : 引数.識別コード
                l_questionAnswerParamsForUpdate.setOrderRequestNumber(l_strRequestNumber);
                
                //5) 質問番号
                //説明 : 引数.FX取引同意質問情報[i].質問番号
                l_questionAnswerParamsForUpdate.setQuestionNo(l_fxTradeAgreementList[i].questionNumber);
                
                //6) 質問回答
                //説明 : 引数.FX取引同意質問情報[i].質問回答
                l_questionAnswerParamsForUpdate.setQuestionAnswer(l_fxTradeAgreementList[i].questionAnswer);
                
                //7) 更新者コード
                //説明 : null
                l_questionAnswerParamsForUpdate.setLastUpdatedTimestamp(null);
                
                //8) 作成日付
                //説明 : 現在時刻
                l_questionAnswerParamsForUpdate.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                
                //9) 更新日付
                //説明 : 現在時刻
                l_questionAnswerParamsForUpdate.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                
                //insert 質問回答テーブル
                WEB3DataAccessUtility.insertRow(l_questionAnswerParamsForUpdate);
            }    
            
        }
        catch (DataException l_ex)
        {
            log.debug("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insertGFT口座開設状況) <BR>
     * GFT依頼電文の内容とFXシステムコード<BR>
     * 　@でGFT口座開設状況テーブル(gft_account_open_status)に行のinsertを行う。<BR>
     * <BR>
     * 挿入する行の内容は下記を参照。 <BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新 <BR>
     * 「FX口座開設_GFT口座開設状況テーブル.xls」の <BR>
     * 「GFT口座開設状況テーブル_DB更新仕様_[依頼]」シート <BR>
     * @@param l_fxGftAskingTelegramUnit - GFT依頼電文明細
     * @@param l_strAgreementDiv - (約諾書区分)<BR>
     * 約諾書区分<BR>
     * @@param l_strFxSystemCode - (FXシステムコード)<BR>
     * FXシステムコード<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41C9620F02C8
     */
    public void insertGFTAccountOpenStatus(
        WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit, String l_strAgreementDiv,
        String l_strFxSystemCode) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "insertGFTAccountOpenStatus( WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit, " +
            "String l_strAgreementDiv, String l_strFxSystemCode)";
        log.entering(STR_METHOD_NAME);
        
        if(l_fxGftAskingTelegramUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        GftAccountOpenStatusParams l_gftAccountOpenStatusParams = new GftAccountOpenStatusParams();
        try
        {
            //1) 証券会社コード
            //説明 : 引数.GFT依頼電文明細.会社コード
            l_gftAccountOpenStatusParams.setInstitutionCode(l_fxGftAskingTelegramUnit.institutionCode);
            
            //2) 部店コード
            //説明 : 引数.GFT依頼電文明細.部店コード
            l_gftAccountOpenStatusParams.setBranchCode(l_fxGftAskingTelegramUnit.branchCode);
            
            //3) 顧客コード
            //説明 : 引数.GFT依頼電文明細.顧客コード
            String l_strAccountCodeToSet = l_fxGftAskingTelegramUnit.accountCode;
            if (l_strAccountCodeToSet != null && l_strAccountCodeToSet.length() == 6)
            {
                WEB3GentradeAccountManager l_web3GentradeAccountManager =
                    (WEB3GentradeAccountManager)GtlUtils.getAccountManager();
                
                MainAccount l_mainAccount = 
                    l_web3GentradeAccountManager.getMainAccount(
                        l_fxGftAskingTelegramUnit.institutionCode,
                        l_fxGftAskingTelegramUnit.branchCode,
                        l_fxGftAskingTelegramUnit.accountCode);
                l_strAccountCodeToSet = l_mainAccount.getAccountCode();
            }
            
            l_gftAccountOpenStatusParams.setAccountCode(l_strAccountCodeToSet);
            
            //4) 識別コード
            //説明 : 引数.GFT依頼電文明細.識別コード
            l_gftAccountOpenStatusParams.setOrderRequestNumber(l_fxGftAskingTelegramUnit.requestNumber);
            
            //5) 名前（姓）
            //説明 : 引数.GFT結果通知電文明細.名前（姓）
            l_gftAccountOpenStatusParams.setLastName(l_fxGftAskingTelegramUnit.fxLastName);
            
            //6) 名前（名）
            //説明 : 引数.GFT依頼電文明細.名前（名）
            l_gftAccountOpenStatusParams.setFirstName(l_fxGftAskingTelegramUnit.fxFirstName);
            
            //7) メールアドレス
            //説明 : 引数.GFT依頼電文明細.メールアドレス
            l_gftAccountOpenStatusParams.setMailAddress(l_fxGftAskingTelegramUnit.fxMailAddress);
            
            //8) ログインID
            //説明 : 引数.GFT依頼電文明細.初期ログインID
            l_gftAccountOpenStatusParams.setLoginId(l_fxGftAskingTelegramUnit.fxFirstLoginId);
    
            //9) 初期パスワード
            //説明 : null
            l_gftAccountOpenStatusParams.setPassword(null);
            
            //10) 口座番号（1万通貨コース）
            //説明 : null
            l_gftAccountOpenStatusParams.setFxAccountCode01(null);
            
            //11) 口座番号（10万通貨コース）
            //説明 : null
            l_gftAccountOpenStatusParams.setFxAccountCode10(null);
    
            //12) 口座開設状況区分
            //説明 : 0：口座開設中
            l_gftAccountOpenStatusParams.setAccountOpenStatusDiv(WEB3AccountOpenStatusDivDef.ACCOUNT_OPENING);
            
            //13) 送受信区分
            //説明 : 1：送信済
            l_gftAccountOpenStatusParams.setSendRcvDiv(WEB3SendRcvDivDef.SEND_COMPLETE);
            
            //14) 受付結果コード
            //説明 : null
            l_gftAccountOpenStatusParams.setResultCode(null);
            
            //15) エラー理由コード
            //説明 : null
            l_gftAccountOpenStatusParams.setErrorReasonCode(null);
            
            //16) 更新者コード
            //説明 : null
            l_gftAccountOpenStatusParams.setLastUpdater(null);
            
            //17) 作成日付
            //説明 : 現在時刻
            l_gftAccountOpenStatusParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //18) 更新日付
            //説明 : 現在時刻
            l_gftAccountOpenStatusParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            //19) 約諾書区分
            //説明 : 引数.約諾書区分
            l_gftAccountOpenStatusParams.setAgreementDiv(l_strAgreementDiv);

            //ＦＸシステムコード
            //引数.FXシステムコード
            l_gftAccountOpenStatusParams.setFxSystemCode(l_strFxSystemCode);

            //21) 連携用口座番号
            //説明 : null
            l_gftAccountOpenStatusParams.setExtAccountCode(null);

            //insert GFT口座開設状況テーブル
            WEB3DataAccessUtility.insertRow(l_gftAccountOpenStatusParams);
        }
        catch (DataException l_ex)
        {
            log.debug("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insertGFT振替状況) <BR>
     * GFT依頼電文の内容でGFT振替状況テーブル(gft_transfer_status)に行のinsertを行う。<BR>
     * 挿入する行の内容は下記を参照。 <BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新 <BR>
     * 「FX振替共通_GFT振替状況テーブル.xls」の <BR>
     * 「GFT振替状況テーブル_DB更新仕様_[依頼]」シート <BR>
     * 
     * @@param l_fxGftAskingTelegramUnit - GFT依頼電文明細
     * @@param l_strCourseDiv - コース区分
     * @@param l_strTransferDate - 受渡予定日
     * @@param l_strMrgTrnRequestNumber - 信用振替用識別コード
     * ※信用口座からの強制振替を行わない場合、null
     * @@param l_compFxConditionParams - 会社別FXシステム条件Params
     * @@param l_strIoListTradeDiv - 入出金一覧取引区分
     * @@throws WEB3BaseException
     * @@roseuid 41BE98D10109
     */
    public void insertGFTTransferStatus(
        WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit,
        String l_strCourseDiv, String l_strTransferDate,
        String l_strMrgTrnRequestNumber,
        CompFxConditionParams l_compFxConditionParams,
        String l_strIoListTradeDiv) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "insertGFTTransferStatus(WEB3FXGftAskingTelegramUnit, " +
            "String, String, String, CompFxConditionParams, String)";
        log.entering(STR_METHOD_NAME);
        
        if(l_fxGftAskingTelegramUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        GftTransferStatusParams l_gftTransferStatusParams = new GftTransferStatusParams();
        
        try
        {
            //1) 証券会社コード
            //説明 : 引数.GFT依頼電文明細.会社コード
            l_gftTransferStatusParams.setInstitutionCode(l_fxGftAskingTelegramUnit.institutionCode);
    
            //2) 部店コード
            //説明 : 引数.GFT依頼電文明細.部店コード
            l_gftTransferStatusParams.setBranchCode(l_fxGftAskingTelegramUnit.branchCode);
    
            //3) 顧客コード
            //説明 : 引数.GFT依頼電文明細.顧客コード
            String l_strAccountCodeToSet = l_fxGftAskingTelegramUnit.accountCode;
            if (l_strAccountCodeToSet != null && l_strAccountCodeToSet.length() == 6)
            {
                WEB3GentradeAccountManager l_web3GentradeAccountManager =
                    (WEB3GentradeAccountManager)GtlUtils.getAccountManager();
                
                MainAccount l_mainAccount = 
                    l_web3GentradeAccountManager.getMainAccount(
                        l_fxGftAskingTelegramUnit.institutionCode,
                        l_fxGftAskingTelegramUnit.branchCode,
                        l_fxGftAskingTelegramUnit.accountCode);
                l_strAccountCodeToSet = l_mainAccount.getAccountCode();
            }
            
            l_gftTransferStatusParams.setAccountCode(l_strAccountCodeToSet);
            
            //4) 識別コード
            //説明 : 引数.GFT依頼電文明細.識別コード
            l_gftTransferStatusParams.setOrderRequestNumber(l_fxGftAskingTelegramUnit.requestNumber);
    
            //5) 処理区分
            //説明 : 
            //引数.GFT依頼電文明細.処理区分＝04：出金(FX)の場合、01（証券口座から為替保証金へ）
            if(WEB3GftMessageOperationDef.CASH_OUT_FX.equals(l_fxGftAskingTelegramUnit.gftOperationDiv))
            {
                l_gftTransferStatusParams.setOperationDiv(WEB3FxTransStatusOperationDivDef.TO_FX);
            }
            //引数.GFT依頼電文明細.処理区分＝02：入金(FX)の場合、02（為替保証金から証券口座へ）
            else if(WEB3GftMessageOperationDef.CASH_IN_FX.equals(l_fxGftAskingTelegramUnit.gftOperationDiv))
            {
                l_gftTransferStatusParams.setOperationDiv(WEB3FxTransStatusOperationDivDef.FROM_FX);
            }
            //引数.GFT依頼電文明細.処理区分＝06：出金(先OP)の場合、03（証券口座から株先証拠金へ） 
            else if(WEB3GftMessageOperationDef.CASH_OUT_FUOP.equals(l_fxGftAskingTelegramUnit.gftOperationDiv))
            {
                l_gftTransferStatusParams.setOperationDiv(WEB3FxTransStatusOperationDivDef.TO_FUOP);
            }
            //引数.GFT依頼電文明細.処理区分＝05：入金(先OP)の場合、04（株先証拠金から証券口座へ） 
            else if(WEB3GftMessageOperationDef.CASH_IN_FUOP.equals(l_fxGftAskingTelegramUnit.gftOperationDiv))
            {
                l_gftTransferStatusParams.setOperationDiv(WEB3FxTransStatusOperationDivDef.FROM_FUOP);
            }
            
            //6) コース区分
            //説明 : 引数.GFTコース区分
            l_gftTransferStatusParams.setCourseDiv(l_strCourseDiv);                
            
            //7) 口座番号
            //説明 : 引数.GFT依頼電文明細.為替保証金口座番号
            l_gftTransferStatusParams.setFxAccountCode(l_fxGftAskingTelegramUnit.fxAccountCode);
            
            //8) 金額
            //説明 : 引数.GFT依頼電文明細.入出金額
            l_gftTransferStatusParams.setAmount(Long.parseLong(l_fxGftAskingTelegramUnit.cashinoutAmt));
            
            //9) 受渡予定日
            //説明 : 引数.受渡予定日
            l_gftTransferStatusParams.setTransferDate(l_strTransferDate);  
            
            //10) 振替状況区分
            //説明 : 0：決済中
            l_gftTransferStatusParams.setTransferStatusDiv(WEB3TransferStatusDivDef.PROCESSING);   
            
            //11) 送受信区分
            //説明 : 1：送信済
            l_gftTransferStatusParams.setSendRcvDiv(WEB3SendRcvDivDef.SEND_COMPLETE);
    
            //12) 受付結果コード
            //説明 : null
            l_gftTransferStatusParams.setResultCode(null);
    
            //13) エラー理由コード
            //説明 : null
            l_gftTransferStatusParams.setErrorReasonCode(null);
            
            //14) 処理時間（送信）
            //説明 : 引数.GFT依頼電文明細.DIR→GFT送信日時
            l_gftTransferStatusParams.setSendTime(l_fxGftAskingTelegramUnit.dirSendTime);
    
            //15) 処理時間（受信）
            //説明 : null
            l_gftTransferStatusParams.setReceiveTime(null);
            
            //16) 信用振替用識別コード
            //説明 : 引数.信用振替用識別コード
            l_gftTransferStatusParams.setMrgTrnOrderRequestNumber(l_strMrgTrnRequestNumber);
            
            //17) 更新者コード
            //説明 : null
            l_gftTransferStatusParams.setLastUpdater(null);
            
            //18) 作成日付
            //説明 : 現在時刻
            l_gftTransferStatusParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //19) 更新日付
            //説明 : 現在時刻
            l_gftTransferStatusParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            //ＦＸシステムコード
            //引数.会社別FXシステム条件Params.FXシステムコード
            l_gftTransferStatusParams.setFxSystemCode(
                l_compFxConditionParams.getFxSystemCode());

            //入出金一覧取引区分
            //説明 : 引数.入出金一覧取引区分
            l_gftTransferStatusParams.setIoListTradeDiv(l_strIoListTradeDiv);

            //insert GFT振替状況テーブル
            WEB3DataAccessUtility.insertRow(l_gftTransferStatusParams);
        
        }
        catch (DataException l_ex)
        {
            log.debug("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insertGFT振替状況) <BR>
     * GFT依頼電文の内容でGFT振替状況テーブル(gft_transfer_status)に行のinsertを行う。 <BR>
     *  <BR>
     * 挿入する行の内容は下記を参照。 <BR>
     *  <BR>
     * 【ｘTrade】補足資料.DB更新  <BR>
     * 「FX振替共通_GFT振替状況テーブル.xls」 <BR>
     * @@param l_fxGftAskingTelegramUnit - (GFT依頼電文明細)<BR>
     * @@param l_strCourseDiv - (コース区分)<BR>
     * @@param l_strTransferDate - (受渡予定日)<BR>
     * @@param l_strMrgTrnRequestNumber - (信用振替用識別コード)<BR>
     * @@param l_strCashInOutNumber - (入出金番号)<BR>
     * @@param l_strIoListTradeDiv - 入出金一覧取引区分<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41BE98D10109
     */
    public void insertGFTTransferStatus(
        WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit,
        String l_strCourseDiv,
        String l_strTransferDate,
        String l_strMrgTrnRequestNumber,
        String l_strCashInOutNumber,
        String l_strIoListTradeDiv) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "insertGFTTransferStatus(WEB3FXGftAskingTelegramUnit, " +
            "String, String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_fxGftAskingTelegramUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        
        GftTransferStatusParams l_gftTransferStatusParams = new GftTransferStatusParams();
        
        try
        {
            //1) 証券会社コード
            //説明 : 引数.GFT依頼電文明細.会社コード
            l_gftTransferStatusParams.setInstitutionCode(l_fxGftAskingTelegramUnit.institutionCode);
    
            //2) 部店コード
            //説明 : 引数.GFT依頼電文明細.部店コード
            l_gftTransferStatusParams.setBranchCode(l_fxGftAskingTelegramUnit.branchCode);
    
            //3) 顧客コード
            //説明 : 引数.GFT依頼電文明細.顧客コード
            String l_strAccountCodeToSet = l_fxGftAskingTelegramUnit.accountCode;
            if (l_strAccountCodeToSet != null && l_strAccountCodeToSet.length() == 6)
            {
                WEB3GentradeAccountManager l_web3GentradeAccountManager =
                    (WEB3GentradeAccountManager) GtlUtils.getAccountManager();
                
                MainAccount l_mainAccount = 
                    l_web3GentradeAccountManager.getMainAccount(
                        l_fxGftAskingTelegramUnit.institutionCode,
                        l_fxGftAskingTelegramUnit.branchCode,
                        l_fxGftAskingTelegramUnit.accountCode);
                l_strAccountCodeToSet = l_mainAccount.getAccountCode();
            }
            
            l_gftTransferStatusParams.setAccountCode(l_strAccountCodeToSet);
            
            //4) 識別コード
            //説明 : 引数.GFT依頼電文明細.識別コード
            l_gftTransferStatusParams.setOrderRequestNumber(l_fxGftAskingTelegramUnit.requestNumber);
    
            //5) 処理区分
            //説明 : 
            //02（為替保証金から証券口座へ）　@
            l_gftTransferStatusParams.setOperationDiv(WEB3FxTransStatusOperationDivDef.FROM_FX);
            
            //6) コース区分
            //説明 : 引数.GFTコース区分
            l_gftTransferStatusParams.setCourseDiv(l_strCourseDiv);                
            
            //7) 口座番号
            //説明 : 引数.GFT依頼電文明細.為替保証金口座番号
            l_gftTransferStatusParams.setFxAccountCode(l_fxGftAskingTelegramUnit.fxAccountCode);
            
            //8) 金額
            //説明 : 引数.GFT依頼電文明細.入出金額
            l_gftTransferStatusParams.setAmount(Long.parseLong(l_fxGftAskingTelegramUnit.cashinoutAmt));
            
            //9) 受渡予定日
            //説明 : 引数.受渡予定日
            l_gftTransferStatusParams.setTransferDate(l_strTransferDate);  
            
            //10) 振替状況区分
            //説明 : 1：決済完了
            l_gftTransferStatusParams.setTransferStatusDiv(WEB3TransferStatusDivDef.PROCESS_COMPLETE);   
            
            //11) 送受信区分
            //説明 : 2：受信済
            l_gftTransferStatusParams.setSendRcvDiv(WEB3SendRcvDivDef.RECEIVE_COMPLETE);
    
            //12) 受付結果コード
            //説明 : 00000000：正常
            l_gftTransferStatusParams.setResultCode(WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000);
    
            //13) エラー理由コード
            //説明 : 0000：正常
            l_gftTransferStatusParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
            
            //14) 処理時間（送信）
            //説明 : 引数.GFT依頼電文明細.DIR→GFT送信日時
            l_gftTransferStatusParams.setSendTime(l_fxGftAskingTelegramUnit.dirSendTime);
    
            //15) 処理時間（受信）
            //説明 : null
            l_gftTransferStatusParams.setReceiveTime(null);
            
            //16) 信用振替用識別コード
            //説明 : 引数.信用振替用識別コード
            l_gftTransferStatusParams.setMrgTrnOrderRequestNumber(l_strMrgTrnRequestNumber);
            
            //17) 更新者コード
            //説明 : null
            l_gftTransferStatusParams.setLastUpdater(null);
            
            //18) 作成日付
            //説明 : 現在時刻
            l_gftTransferStatusParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //19) 更新日付
            //説明 : 現在時刻
            l_gftTransferStatusParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //20) 入出金番号
            //説明 : 引数.入出金番号
            l_gftTransferStatusParams.setFxUploadNumber(l_strCashInOutNumber);

            //21) 受付結果コード（SOAP）
            //説明 : null
            l_gftTransferStatusParams.setResultCodeSoap(null);

            //ＦＸシステムコード
            //説明 : null
            l_gftTransferStatusParams.setFxSystemCode(null);

            //入出金一覧取引区分
            //説明 : 引数.入出金一覧取引区分
            l_gftTransferStatusParams.setIoListTradeDiv(l_strIoListTradeDiv);

            //insert GFT振替状況テーブル
            WEB3DataAccessUtility.insertRow(l_gftTransferStatusParams);
        }
        catch (DataQueryException l_dqex)
        {
            log.error("テーブルに該当するデータがありません。", l_dqex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqex.getMessage(),
                l_dqex);
        }
        catch (DataNetworkException l_dnex)
        {
            log.error("DBアクセスエラー", l_dnex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dnex.getMessage(),
                l_dnex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insertGFT電文保存) <BR>
     * GFT依頼電文の内容でGFT電文保存テーブル(gft_transfer_status)に行のinsertを行う。<BR>
     * 挿入する行の内容は下記を参照。 <BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新 <BR>
     * 「FX共通_GFT電文保存テーブル.xls」の <BR>
     * 「GFT電文保存テーブル_DB更新仕様[依頼]」シート <BR>
     * @@param l_fxGftAskingTelegramUnit - GFT依頼電文明細
     * @@throws WEB3BaseException
     * @@roseuid 41BEA44A006D
     */
    public void insertGFTMessage(WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "insertGFTMessage(WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit) ";
        log.entering(STR_METHOD_NAME);
        
        if(l_fxGftAskingTelegramUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        GftMessageParams l_gftMessageParams = new GftMessageParams();
        
        try
        {
            //1) 電文種別区分
            //説明 : 1：送信（DIR→GFT）
            l_gftMessageParams.setMessageDiv(WEB3GftMessageDivDef.SEND);
            
            //2) DIR→GFT送信日時
            //説明 : 引数.GFT依頼電文明細.DIR→GFT送信日時
            l_gftMessageParams.setDirSendTime(l_fxGftAskingTelegramUnit.dirSendTime);
            
            //3) 処理区分
            //説明 : 引数.GFT依頼電文明細.処理区分
            l_gftMessageParams.setOperation(l_fxGftAskingTelegramUnit.gftOperationDiv);        
            
            //4) 為替保証金口座番号
            //説明 : 引数.GFT依頼電文明細.為替保証金口座番号
            l_gftMessageParams.setAccount(l_fxGftAskingTelegramUnit.fxAccountCode);        
    
            //5) メールアドレス
            //説明 : 引数.GFT依頼電文明細.メールアドレス
            l_gftMessageParams.setEmail(l_fxGftAskingTelegramUnit.fxMailAddress);
            
            //6) 初期ログインID
            //説明 : 引数.GFT依頼電文明細.初期ログインID
            l_gftMessageParams.setGftLink1(l_fxGftAskingTelegramUnit.fxFirstLoginId);        
            
            //7) 初期パスワード
            //説明 : 引数.GFT依頼電文明細.初期パスワード
            WEB3FXTelegramProcessService l_fXTelegramProcessService = 
                (WEB3FXTelegramProcessService)Services.getService(WEB3FXTelegramProcessService.class);
            
            l_gftMessageParams.setGftLink2(
                l_fXTelegramProcessService.maskPassword(l_fxGftAskingTelegramUnit.fxFirstPassword));        
            
            //8) 担当区分
            //説明 : 引数.GFT依頼電文明細.担当区分
            l_gftMessageParams.setGroupName(l_fxGftAskingTelegramUnit.groupName);
            
            //9) 入出金額
            //説明 : 引数.GFT依頼電文明細.入出金額
            l_gftMessageParams.setAmount(l_fxGftAskingTelegramUnit.cashinoutAmt);        
            
            //10) WOLFセッションキー
            //説明 : 引数.GFT依頼電文明細.WOLFセッションキー
            l_gftMessageParams.setWolfSessionKey(l_fxGftAskingTelegramUnit.wolfSession);    
            
            //11) アプリケーションID
            //説明 : 引数.GFT依頼電文明細.アプリケーションID
            l_gftMessageParams.setAaAid(l_fxGftAskingTelegramUnit.wolfAid);  
            
            //12) 再生成サービスID
            //説明 : 引数.GFT依頼電文明細.再生成サービスID
            l_gftMessageParams.setAaRsid(l_fxGftAskingTelegramUnit.regetServiceId); 
            
            //13) SSID
            //説明 : 引数.GFT依頼電文明細.SSID
            l_gftMessageParams.setSsid(l_fxGftAskingTelegramUnit.wolfSsid); 
            
            //14) 会社コード
            //説明 : 引数.GFT依頼電文明細.会社コード
            l_gftMessageParams.setCpy(l_fxGftAskingTelegramUnit.institutionCode);        
            
            //15) 部店コード
            //説明 : 引数.GFT依頼電文明細.部店コード
            l_gftMessageParams.setBrn(l_fxGftAskingTelegramUnit.branchCode);  
            
            //16) 顧客コード
            //説明 : 引数.GFT依頼電文明細.顧客コード
            String l_strAccountCodeToSet = l_fxGftAskingTelegramUnit.accountCode;
            if (l_strAccountCodeToSet != null && l_strAccountCodeToSet.length() == 6)
            {
                WEB3GentradeAccountManager l_web3GentradeAccountManager =
                    (WEB3GentradeAccountManager)GtlUtils.getAccountManager();
                
                MainAccount l_mainAccount = 
                    l_web3GentradeAccountManager.getMainAccount(
                        l_fxGftAskingTelegramUnit.institutionCode,
                        l_fxGftAskingTelegramUnit.branchCode,
                        l_fxGftAskingTelegramUnit.accountCode);
                l_strAccountCodeToSet = l_mainAccount.getAccountCode();
            }
            
            l_gftMessageParams.setAcc(l_strAccountCodeToSet);        
    
            //17) 識別コード
            //説明 : 引数.GFT依頼電文明細.識別コード
            l_gftMessageParams.setReq(l_fxGftAskingTelegramUnit.requestNumber);
    
            //18) 受付結果
            //説明 : null
            l_gftMessageParams.setResultCode(null);
    
            //19) GFT→DIR送信日時
            //説明 : null
            l_gftMessageParams.setGftSendTime(null);
            
            //20) 為替保証金口座番号（１万通貨）
            //説明 : null
            l_gftMessageParams.setGftAc1(null);
            
            //21) 為替保証金口座番号（１０万通貨）
            //説明 : null
            l_gftMessageParams.setGftAc2(null);
            
            //22) 名前（姓）
            //説明 : 引数.GFT依頼電文明細.名前（姓）
            l_gftMessageParams.setLastName(l_fxGftAskingTelegramUnit.fxLastName);
            
            //23) 名前（名）
            //説明 : 引数.GFT依頼電文明細.名前（名）
            l_gftMessageParams.setFirstName(l_fxGftAskingTelegramUnit.fxFirstName);        
            
            //24) ハッシュ値
            //説明 : 引数.GFT依頼電文明細.ハッシュ値
            l_gftMessageParams.setHashKey(l_fxGftAskingTelegramUnit.hashValue);        
            
            //25) 作成日付
            //説明 : 現在時刻
            l_gftMessageParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //26) 更新日付
            //説明 : 現在時刻
            l_gftMessageParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
           
            //27) 受渡日
            //説明 : 受渡日
            l_gftMessageParams.setDeliveryDate(l_fxGftAskingTelegramUnit.deliveryDate);
            
            //28) 入出金額2
            //説明 : null
            l_gftMessageParams.setAmount2(null);

            //住所１
            //説明 :引数.GFT依頼電文明細.住所１
            l_gftMessageParams.setAddress1(l_fxGftAskingTelegramUnit.address1);

            //住所２
            //説明 :引数.GFT依頼電文明細.住所２
            l_gftMessageParams.setAddress2(l_fxGftAskingTelegramUnit.address2);

            //住所３
            //説明 :引数.GFT依頼電文明細.住所３
            l_gftMessageParams.setAddress3(l_fxGftAskingTelegramUnit.address3);

            //insert FX共通_GFT電文保存テーブル
            WEB3DataAccessUtility.insertRow(l_gftMessageParams);
    
        }
        catch (DataException l_ex)
        {
            log.debug("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insertGFT電文保存) <BR>
     * GFT結果通知電文の内容でGFT電文保存テーブル(gft_transfer_status) <BR>
     * に行のinsertを行う 。<BR>
     * <BR>
     * 挿入する行の内容は下記を参照。 <BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新 <BR>
     * 「FX共通_GFT電文保存テーブル.xls」の <BR>
     * 「GFT電文保存テーブル_DB更新仕様[結果通知]」シート <BR>
     * @@param l_fxGftResultNoticeTelegramUnit - GFT結果通知電文明細
     * @@throws WEB3BaseException
     * @@roseuid 41C10C040369
     */
    public void insertGFTMessage(
        WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "insertGFTMessage(WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit)";
        log.entering(STR_METHOD_NAME);
        
        if(l_fxGftResultNoticeTelegramUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        GftMessageParams l_gftMessageParams = new GftMessageParams();
        
        try
        {
            //1) 電文種別区分
            //説明 : 2：受信（GFT→DIR)
            l_gftMessageParams.setMessageDiv(WEB3GftMessageDivDef.RECEIVE);
            
            //2) DIR→GFT送信日時
            //説明 : 引数.GFT結果通知電文明細.DIR→GFT送信日時
            l_gftMessageParams.setDirSendTime(l_fxGftResultNoticeTelegramUnit.dirSendTime);
            
            //3) 処理区分
            //説明 : 引数.GFT結果通知電文明細.処理区分
            l_gftMessageParams.setOperation(l_fxGftResultNoticeTelegramUnit.gftOperationDiv);        
            
            //4) 為替保証金口座番号
            //説明 : 引数.GFT結果通知電文明細.為替保証金番号---------->為替保証金口座番号
            l_gftMessageParams.setAccount(l_fxGftResultNoticeTelegramUnit.fxAccountCode);        
    
            //5) メールアドレス
            //説明 : 引数.GFT結果通知電文明細.メールアドレス
            l_gftMessageParams.setEmail(l_fxGftResultNoticeTelegramUnit.fxMailAddress);
            
            //6) 初期ログインID
            //説明 : 引数.GFT結果通知電文明細.初期ログインID
            l_gftMessageParams.setGftLink1(l_fxGftResultNoticeTelegramUnit.fxFirstLoginId);        
            
            //7) 初期パスワード
            //説明 : 引数.GFT結果通知電文明細.初期パスワード
            WEB3FXTelegramProcessService l_fXTelegramProcessService = 
                (WEB3FXTelegramProcessService)Services.getService(WEB3FXTelegramProcessService.class);
            
            l_gftMessageParams.setGftLink2(
                l_fXTelegramProcessService.maskPassword(l_fxGftResultNoticeTelegramUnit.fxFirstPassword));        
            
            //8) 担当区分
            //説明 : 引数.GFT結果通知電文明細.担当区分
            l_gftMessageParams.setGroupName(l_fxGftResultNoticeTelegramUnit.groupName);
            
            //9) 入出金額
            //説明 : 引数.GFT結果通知電文明細.入出金額
            l_gftMessageParams.setAmount(l_fxGftResultNoticeTelegramUnit.cashinoutAmt);        
            
            //10) WOLFセッションキー
            //説明 : 引数.GFT結果通知電文明細.WOLFセッションキー
            l_gftMessageParams.setWolfSessionKey(l_fxGftResultNoticeTelegramUnit.wolfSession);    
            
            //11) アプリケーションID
            //説明 : 引数.GFT結果通知電文明細.アプリケーションID
            l_gftMessageParams.setAaAid(l_fxGftResultNoticeTelegramUnit.wolfAid);  
            
            //12) 再生成サービスID
            //説明 : 引数.GFT結果通知電文明細.再生成サービスID
            l_gftMessageParams.setAaRsid(l_fxGftResultNoticeTelegramUnit.regetServiceId); 
            
            //13) SSID
            //説明 : 引数.GFT結果通知電文明細.SSID
            l_gftMessageParams.setSsid(l_fxGftResultNoticeTelegramUnit.wolfSsid); 
            
            //14) 会社コード
            //説明 : 引数.GFT結果通知電文明細.会社コード
            l_gftMessageParams.setCpy(l_fxGftResultNoticeTelegramUnit.institutionCode);        
            
            //15) 部店コード
            //説明 : 引数.GFT結果通知電文明細.部店コード
            l_gftMessageParams.setBrn(l_fxGftResultNoticeTelegramUnit.branchCode);  
            
            //16) 顧客コード
            //説明 : 引数.GFT結果通知電文明細.顧客コード
            String l_strAccountCodeToSet = l_fxGftResultNoticeTelegramUnit.accountCode;
            if (l_strAccountCodeToSet != null && l_strAccountCodeToSet.length() == 6)
            {
                WEB3GentradeAccountManager l_web3GentradeAccountManager =
                    (WEB3GentradeAccountManager)GtlUtils.getAccountManager();
                
                MainAccount l_mainAccount = 
                    l_web3GentradeAccountManager.getMainAccount(
                        l_fxGftResultNoticeTelegramUnit.institutionCode,
                        l_fxGftResultNoticeTelegramUnit.branchCode,
                        l_fxGftResultNoticeTelegramUnit.accountCode);
                l_strAccountCodeToSet = l_mainAccount.getAccountCode();
            }
            
            l_gftMessageParams.setAcc(l_strAccountCodeToSet);        

            //17) 識別コード
            //説明 : 引数.GFT結果通知電文明細.識別コード
            l_gftMessageParams.setReq(l_fxGftResultNoticeTelegramUnit.requestNumber);
    
            //18) 受付結果
            //説明 : 引数.GFT結果通知電文明細.受付結果
            l_gftMessageParams.setResultCode(l_fxGftResultNoticeTelegramUnit.resultCode);
    
            //19) GFT→DIR送信日時
            //説明 : 引数.GFT結果通知電文明細.GFT→DIR送信日時
            l_gftMessageParams.setGftSendTime(l_fxGftResultNoticeTelegramUnit.gftSendTime);
            
            //20) 為替保証金口座番号（１万通貨）
            //説明 : 引数.GFT結果通知電文明細.為替保証金口座番号(1万通貨)
            l_gftMessageParams.setGftAc1(l_fxGftResultNoticeTelegramUnit.gftAcc1);
            
            //21) 為替保証金口座番号（１０万通貨）
            //説明 : 引数.GFT結果通知電文明細.為替保証金口座番号(10万通貨)
            l_gftMessageParams.setGftAc2(l_fxGftResultNoticeTelegramUnit.gftAcc2);
            
            //22) 名前（姓）
            //説明 : 引数.GFT結果通知電文明細.名前（姓）
            l_gftMessageParams.setLastName(l_fxGftResultNoticeTelegramUnit.fxLastName);
            
            //23) 名前（名）
            //説明 : 引数.GFT結果通知電文明細.名前（名）
            l_gftMessageParams.setFirstName(l_fxGftResultNoticeTelegramUnit.fxFirstName);        
            
            //24) ハッシュ値
            //説明 : 引数.GFT結果通知電文明細.ハッシュ値
            l_gftMessageParams.setHashKey(l_fxGftResultNoticeTelegramUnit.hashValue);        
            
            //25) 作成日付
            //説明 : 現在時刻
            l_gftMessageParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //26) 更新日付
            //説明 : 現在時刻
            l_gftMessageParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
           
            //27)受渡日
            //説明 :受渡日
            l_gftMessageParams.setDeliveryDate(l_fxGftResultNoticeTelegramUnit.deliveryDate);

            //28) 入出金額2
            //説明 : 引数.GFT結果通知電文明細.入出金額2
            l_gftMessageParams.setAmount2(l_fxGftResultNoticeTelegramUnit.cashinoutAmt2);

            //住所１
            //説明 : null
            l_gftMessageParams.setAddress1(null);

            //住所２
            //説明 : null
            l_gftMessageParams.setAddress2(null);

            //住所３
            //説明 : null
            l_gftMessageParams.setAddress3(null);

            //insert FX共通_GFT電文保存テーブル
            WEB3DataAccessUtility.insertRow(l_gftMessageParams);
    
        }
        catch (DataException l_ex)
        {
            log.debug("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (updateFX顧客) <BR>
     * 更新後の値でFX顧客テーブルの口座開設状況区分を更新する。 <BR>
     * <BR>
     * (1)FX顧客Paramsインスタンスを生成する。<BR>
     * <BR>
     * (2)生成したインスタンスに、引数.FX顧客Paramのプロパティをコピーする。<BR>
     * <BR>
     * (3)生成したインスタンスに更新後のデータをセットする。<BR>
     * 更新する行の内容は下記を参照。<BR>
     * 【ｘTrade】補足資料.DB更新 <BR>
     * 「管理者・口座管理_FX顧客テーブルDB更新仕様.xls」<BR>
     * <BR>
     * (4)FX顧客のupdate<BR>
     * 　@　@ WEB3DataAccessUtility.updateRow()メソッドをコールする。<BR>
     * 　@　@　@[updateRow()にセットするパラメータ]<BR>
     * 　@　@　@　@l_row：　@更新後のデータをセットしたインスタンス<BR>
     * <BR>
     * @@param l_fxAccountParams - FX顧客Paramオブジェクト
     * @@param l_strUpdatedAccOpenStatusDiv - 更新後口座開設状況区分
     * @@param l_strUpdaterCode - 更新者コード
     * @@throws WEB3BaseException
     * @@roseuid 41BEEAAF01FD
     */
    public void updateFXAccount(FxAccountParams l_fxAccountParams,
        String l_strUpdatedAccOpenStatusDiv,
        String l_strUpdaterCode) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateFXAccount(FxAccountParams l_fxAccountParams," +
            "String l_strUpdatedAccOpenStatusDiv, " +
            "String l_strUpdaterCode)";
        
        log.entering(STR_METHOD_NAME);
        
        if(l_fxAccountParams == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // (1)FX顧客Paramsインスタンスを生成する。
        // (2)生成したインスタンスに、引数.FX顧客Paramのプロパティをコピーする。
        HashMap l_hmUpdateValues = new HashMap();
        try
        {
            
            // (3)生成したインスタンスに更新後のデータをセットする。
            //　@更新する行の内容は下記を参照。 
            //　@【ｘTrade】補足資料.DB更新 
            //　@「管理者・口座管理_FX顧客テーブルDB更新仕様.xls」
            
            //3.1) FX口座区分
            //説明 : 引数.更新後口座開設状況区分
            l_hmUpdateValues.put("fx_account_div", l_strUpdatedAccOpenStatusDiv);
            
            //3.2) 更新者コード
            //説明 : 引数.更新者コード
            l_hmUpdateValues.put("last_updater", l_strUpdaterCode);
            
            //3.3) 更新日付
            //説明 : 現在時刻
            l_hmUpdateValues.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
            
            // (4)FX顧客のupdate
            //WEB3DataAccessUtility.updateRow()メソッドをコールする。 
            //　@[updateRow()にセットするパラメータ] 
            //　@　@l_row：　@更新後のデータをセットしたインスタンス
            
            WEB3DataAccessUtility.updateRow(l_fxAccountParams, l_hmUpdateValues);
        
        }
        catch (DataException l_ex)
        {
            log.debug("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (updateFX顧客) <BR>
     * 更新後の値でFX顧客テーブルのメールアドレスを更新する。 <BR>
     * <BR>
     * 　@　@(1)FX顧客Paramsインスタンスを取得する。<BR>
     * 　@　@　@下記条件にてFX顧客テーブルを検索する。<BR>
     * <BR>
     * 　@　@　@[条件]<BR>
     * 　@　@　@証券会社コード： 引数.FX顧客Params.証券会社コード<BR>
     * 　@　@　@部店コード： 引数.FX顧客Params.部店コード<BR>
     * 　@　@　@顧客コード 引数.FX顧客Params.顧客コード<BR>
     *       FXシステムコード IN 引数.FXシステムコード一覧<BR>
     * <BR>
     * 　@　@　@※nullが返却された場合は、例外をスローする。<BR>
     * <BR>
     * 　@　@(2)(1)で取得したFX顧客の全レコードに対し、下記更新仕様の通りDB更新を行う。<BR>
     * <BR>
     * 　@　@　@【ｘTrade】補足資料.DB更新<BR>
     * 　@　@　@「管理者・口座管理_FX顧客テーブルDB更新仕様.xls」の<BR>
     * 　@　@　@「(口座管理)FX顧客テーブルDB更新仕様(メールアドレス更新)」シート<BR>
     * @@param l_fxAccountParams - FX顧客Paramオブジェクト
     * @@param l_strUpdatedMailAddress - 更新後メールアドレス
     * @@param l_strUpdaterCode - 更新者コード
     * @@param l_fxSystemCodeList - FXシステムコード一覧
     * @@throws WEB3BaseException
     */
     public void updateFXAccount(FxAccountParams l_fxAccountParams,
        String l_strUpdatedMailAddress, String l_strUpdaterCode, 
		ArrayList l_fxSystemCodeList) throws WEB3BaseException
    {
     	
        String STR_METHOD_NAME =
            "updateFXAccount(FxAccountParams l_fxAccountParams," +
            "String l_strUpdatedMailAddress, " +
            "String l_strUpdaterCode," +
			"ArrayList l_fxSystemCodeList)";
        log.entering(STR_METHOD_NAME);
     	
        //(1)FX顧客Paramsインスタンスを取得する。
        //下記条件にてFX顧客テーブルを検索する。
        //[条件]
        //証券会社コード： 引数.FX顧客Params.証券会社コード
        //部店コード： 引数.FX顧客Params.部店コード
        //顧客コード 引数.FX顧客Params.顧客コード
        //FXシステムコード IN 引数.FXシステムコード一覧
        String l_strWhere =
            " institution_code = ? and branch_code = ? and account_code = ? ";
        
		List l_lisValues = new ArrayList();
		l_lisValues.add(l_fxAccountParams.getInstitutionCode());
		l_lisValues.add(l_fxAccountParams.getBranchCode());
		l_lisValues.add(l_fxAccountParams.getAccountCode());
        
		if(l_fxSystemCodeList != null && l_fxSystemCodeList.size() != 0){
			
			l_strWhere += "and fx_system_code in (";
	        
			for(int i=0; i<l_fxSystemCodeList.size(); i++){
				l_lisValues.add(l_fxSystemCodeList.get(i));
                if (i == l_fxSystemCodeList.size() - 1)
                {
                    l_strWhere += " ?";
                }
                else
                {
                    l_strWhere += " ?,";
                }
	          	
	        }
			l_strWhere += " ) ";
		}
		
		Object[] l_objValue = new Object[l_lisValues.size()];
		l_lisValues.toArray(l_objValue);

        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    FxAccountRow.TYPE,
                    l_strWhere,
                    l_objValue);
        }
        catch (DataQueryException l_ex)
        {
            log.debug("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.debug("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        Iterator l_iterator = l_lisRows.iterator();

            while (l_iterator.hasNext())
            {
                FxAccountRow l_fxAccountRow = (FxAccountRow)l_iterator.next();
                FxAccountParams l_Params = new FxAccountParams(l_fxAccountRow);
                try
                {
                    //(2)(1)で取得したFX顧客の全レコードに対し、下記更新仕様の通りDB更新を行う。
                    //【ｘTrade】補足資料.DB更新
                    //「管理者・口座管理_FX顧客テーブルDB更新仕様.xls」の
                    //「(口座管理)FX顧客テーブルDB更新仕様(メールアドレス更新)」シート
                    //FXメールアドレス
                    //説明:引数.更新後メールアドレス
                    l_Params.setFxMailAddress(l_strUpdatedMailAddress);
                    //更新者コード
                    //引数.更新者コード
                    l_Params.setLastUpdater(l_strUpdaterCode);
                    //更新日付
                    //説明:現在時刻
                    l_Params.setLastUpdatedTimestamp(
                        GtlUtils.getSystemTimestamp());

                    WEB3DataAccessUtility.updateRow(l_Params);
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
            }
        
        log.exiting(STR_METHOD_NAME);
    
    }

    /**
     * (updateFX顧客メールアドレス)<BR>
     * 更新後の値でFX顧客テーブルのメールアドレスを更新する。<BR>
     * <BR>
     * FX顧客のレコードに対し、下記更新仕様の通りDB更新を行う<BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新<BR>
     * 「管理者・口座管理_FX顧客テーブルDB更新仕様.xls」の<BR>
     * 「(口座管理)FX顧客テーブルDB更新仕様(メールアドレス更新)」シート<BR>
     * <BR>
     * @@param l_fxAccountParams - (FX顧客Params)<BR>
     * FX顧客Paramオブジェクト<BR>
     * @@param l_strUpdatedMailAddress - (更新後メールアドレス)<BR>
     * 更新後メールアドレス<BR>
     * @@param l_strUpdaterCode - (更新者コード)<BR>
     * 更新者コード<BR>
     * @@throws WEB3BaseException
     */
    public void updateFXAccountMailAddress(
        FxAccountParams l_fxAccountParams,
        String l_strUpdatedMailAddress,
        String l_strUpdaterCode) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateFXAccountMailAddress(FxAccountParams, String, String)";
        log.entering(STR_METHOD_NAME);

        if(l_fxAccountParams == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //「管理者・口座管理_FX顧客テーブルDB更新仕様.xls」の
        //「(口座管理)FX顧客テーブルDB更新仕様(メールアドレス更新)」シート
        HashMap l_hmUpdateValues = new HashMap();
        try
        {
            //FXメールアドレス
            //説明:引数.更新後メールアドレス
            l_hmUpdateValues.put("fx_mail_address", l_strUpdatedMailAddress);

            //更新者コード
            //引数.更新者コード
            l_hmUpdateValues.put("last_updater", l_strUpdaterCode);

            //更新日付
            //説明:現在時刻
            l_hmUpdateValues.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());

            WEB3DataAccessUtility.updateRow(l_fxAccountParams, l_hmUpdateValues);
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
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (updateFX口座番号) <BR>
     * 更新後の値でFX口座番号テーブルを更新する。 <BR>
     * <BR>
     * １）FX口座番号Paramsインスタンスを生成する。 <BR>
     * <BR>
     * ２）生成したインスタンスに、パラメータ.FX口座番号Paramの <BR>
     * プロパティをコピーする。 <BR>
     * <BR>
     * ３）生成したインスタンスに更新後のデータをセットする。 <BR>
     * 更新する行の内容は下記を参照。 <BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新 <BR>
     * 「管理者・口座管理_FX口座番号テーブルDB更新仕様.xls」 <BR>
     * <BR>
     * ４）FX口座番号のupdate <BR>
     * WEB3DataAccessUtility.updateRow()メソッドをコールする。 <BR>
     * 
     * [updateRow()にセットするパラメータ] l_row： 更新後のデータをセットしたインスタンス
     * 
     * @@param l_fxAccountCodeParams - FX口座番号Paramオブジェクト
     * @@param l_strUpdatedAccountCode - 更新後のFX口座番号
     * @@param l_strUpdaterCode - 更新者コード
     * @@throws WEB3BaseException
     * @@roseuid 41C80E2B018C
     */
    public void updateFXAccountCode(FxAccountCodeParams l_fxAccountCodeParams,
        String l_strUpdatedAccountCode, String l_strUpdaterCode) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateFXAccountCode(FxAccountCodeParams l_fxAccountCodeParams," +
            "String l_strUpdatedAccountCode, String l_strUpdaterCode) ";
        log.entering(STR_METHOD_NAME);
        
        if(l_fxAccountCodeParams == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        try
        {
            //１）FX口座番号Paramsインスタンスを生成する。
            //２）生成したインスタンスに、パラメータ.FX口座番号Paramの 
            //　@プロパティをコピーする。 
            FxAccountCodeParams l_fxAccountCodeParamsUpdate = new FxAccountCodeParams(l_fxAccountCodeParams);
            
            //３）生成したインスタンスに更新後のデータをセットする。 
            //　@更新する行の内容は下記を参照。
            //　@【ｘTrade】補足資料.DB更新 
            //　@「管理者・口座管理_FX口座番号テーブルDB更新仕様.xls」
            
            //3.1) FX口座番号
            //説明 : 更新後口座番号
            l_fxAccountCodeParamsUpdate.setFxAccountCode(l_strUpdatedAccountCode);
            
            //3.2) 更新者コード
            //説明 : 引数.更新者コード
            l_fxAccountCodeParamsUpdate.setLastUpdater(l_strUpdaterCode);
            
            //3.3) 更新日付
            //説明 : 現在時刻
            l_fxAccountCodeParamsUpdate.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
    
            
            //４）FX口座番号のupdate 
            //WEB3DataAccessUtility.updateRow()メソッドをコールする。 
            //
            //　@[updateRow()にセットするパラメータ] 
            //　@　@l_row：　@更新後のデータをセットしたインスタンス 
            WEB3DataAccessUtility.updateRow(l_fxAccountCodeParamsUpdate);
        
        }
        catch (DataException l_ex)
        {
            log.debug("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (updateGFT口座開設状況) <BR>
     * 更新後の値でGFT口座開設状況テーブルを更新する。 <BR>
     * <BR>
     * １）GFT口座開設状況Paramsインスタンスを生成する。 <BR>
     * <BR>
     * ２）生成したインスタンスに、パラメータ.GFT口座開設状況Paramの <BR>
     * プロパティをコピーする。 <BR>
     * <BR>
     * ３）生成したインスタンスに更新後のデータをセットする。 <BR>
     * 更新する行の内容は下記を参照。 <BR>
     * <BR>
     * [パラメータ.更新後ステータス区分 == "1：口座開設完了"の場合] <BR>
     * 【ｘTrade】補足資料.DB更新 <BR>
     * 「管理者・口座開設管理_GFT口座開設状況テーブルDB更新仕様.xls」 <BR>
     * の「(口座開設管理)[口座開設完了]GFT口座開設状況テーブル」シート <BR>
     * <BR>
     * [パラメータ.更新後ステータス区分 == "9：削除"の場合] <BR>
     * 【ｘTrade】補足資料.DB更新 <BR>
     * 「管理者・口座開設管理_GFT口座開設状況テーブルDB更新仕様.xls」 <BR>
     * の「(口座開設管理)[削除]GFT口座開設状況テーブル」シート <BR>
     * <BR>
     * [パラメータ.更新後ステータス区分が未設定（=null）の場合]<BR>
     * 【ｘTrade】補足資料.DB更新 <BR>
     * 「管理者・口座開設管理_GFT口座開設状況テーブルDB更新仕様.xls」<BR>
     * の「(口座開設管理)[約諾書更新]GFT口座開設状況テーブル」シート<BR>
     * <BR>
     * ４）GFT口座開設状況の
     *    update WEB3DataAccessUtility.updateRow()メソッドをコールする。<BR>
     * <BR>
     * [updateRow()にセットするパラメータ] l_row： 更新後のデータをセットしたインスタンス
     * 
     * @@param l_gftAccountOpenStatusParams - GFT口座開設状況Paramsオブジェクト
     * @@param l_strUpdatedStatusDiv - 更新後ステータス区分
     * @@param l_updatedFxAccInformationUnits - 更新後のFX口座情報の配列
     * @@param l_strUpdaterCode - 更新者コード
     * @@param l_strUpdatedAgreementDiv - (更新後約諾書区分)<BR>
     * 更新後約諾書区分<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41BE6F0300EE
     */
    public void updateGFTAccountOpenStatus(
        GftAccountOpenStatusParams l_gftAccountOpenStatusParams,
        String l_strUpdatedStatusDiv,
        WEB3FXAccInformationUnit[] l_updatedFxAccInformationUnits,
        String l_strUpdaterCode, 
        String l_strUpdatedAgreementDiv) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateGFTAccountOpenStatus(GftAccountOpenStatusParams l_gftAccountOpenStatusParams," +
            "String l_strUpdatedStatusDiv, WEB3FXAccInformationUnit[] l_updatedFxAccInformation," +
            "String l_strUpdaterCode, String l_strUpdatedAgreementDiv)";
        log.entering(STR_METHOD_NAME);
        
        if(l_gftAccountOpenStatusParams == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        try
        {
        
            //１）GFT口座開設状況Paramsインスタンスを生成する。 
            //２）生成したインスタンスに、パラメータ.GFT口座開設状況Paramの 
            //　@プロパティをコピーする。 
            GftAccountOpenStatusParams l_paramsForUpdate =  
                new GftAccountOpenStatusParams(l_gftAccountOpenStatusParams);
            
            //３）生成したインスタンスに更新後のデータをセットする。 
            //　@更新する行の内容は下記を参照。
            //　@[パラメータ.更新後ステータス区分 == "1：口座開設完了"の場合] 
            //　@　@【ｘTrade】補足資料.DB更新 
            //　@　@「管理者・口座開設管理_GFT口座開設状況テーブルDB更新仕様.xls」 
            //　@　@の「(口座開設管理)[口座開設完了]GFT口座開設状況テーブル」シート
            if(WEB3AccountOpenStatusDivDef.ACCOUNT_OPEN_COMPLETE.equals(l_strUpdatedStatusDiv))
            {
                //3.1) 初期パスワード
                //説明 : null
                l_paramsForUpdate.setPassword(null);
                
                for(int j = 0; j < l_updatedFxAccInformationUnits.length; j++)
                {
                    //3.2) 口座番号（1万通貨コース）
                    //説明 : 更新後FX口座情報一覧のうち、
                    //       コース区分 == "1万通貨コース"であるFX口座情報.口座番号
                    if(WEB3GftTransStatusCourseDivDef.ONE_COSE.equals(l_updatedFxAccInformationUnits[j].fxCourseDiv))
                    {
                        l_paramsForUpdate.setFxAccountCode01(l_updatedFxAccInformationUnits[j].fxAccountCode);
                    }

                    //3.3) 口座番号（10万通貨コース）
                    //説明 : 更新後FX口座情報一覧のうち、
                    //       コース区分 == "10万通貨コース"であるFX口座情報.口座番号
                    if(WEB3GftTransStatusCourseDivDef.TEN_COSE.equals(l_updatedFxAccInformationUnits[j].fxCourseDiv))
                    {
                        l_paramsForUpdate.setFxAccountCode10(l_updatedFxAccInformationUnits[j].fxAccountCode);
                    }

                    //3.8) 連携用口座番号
                    //更新後FX口座情報一覧のうち、
                    //　@コース区分 == "CFDコース"であるFX口座情報.口座番号
                    if (WEB3GftTransStatusCourseDivDef.CFD_COURSE.equals(
                        l_updatedFxAccInformationUnits[j].fxCourseDiv))
                    {
                        l_paramsForUpdate.setExtAccountCode(
                            l_updatedFxAccInformationUnits[j].fxAccountCode);
                    }
                }
                
                //3.4) 口座開設状況区分
                //説明 : 1：口座開設完了
                l_paramsForUpdate.setAccountOpenStatusDiv(WEB3AccountOpenStatusDivDef.ACCOUNT_OPEN_COMPLETE);
                
                //3.5) 更新者コード
                //説明 : 引数.更新者コード
                l_paramsForUpdate.setLastUpdater(l_strUpdaterCode);
                
                //3.6) 更新日付
                //説明 : 現在時刻
                l_paramsForUpdate.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());            
                
                //3.7) 約諾書区分
                //説明 : 更新後約諾書区分==null の場合（既存値）
                //以外、更新後約諾書区分
                if (l_strUpdatedAgreementDiv != null)
                {
                    l_paramsForUpdate.setAgreementDiv(l_strUpdatedAgreementDiv);
                }
            }
            
            //　@[パラメータ.更新後ステータス区分 == "9：削除"の場合] 
            //　@　@【ｘTrade】補足資料.DB更新 
            //　@　@「管理者・口座開設管理_GFT口座開設状況テーブルDB更新仕様.xls」 
            //　@　@の「(口座開設管理)[削除]GFT口座開設状況テーブル」シート 
            if(WEB3AccountOpenStatusDivDef.DELETE.equals(l_strUpdatedStatusDiv))
            {
    
                //3.1)初期パスワード
                //説明 : null
                l_paramsForUpdate.setPassword(null);
                
                //3.2) 口座番号（1万通貨コース）
                //説明 : null
                l_paramsForUpdate.setFxAccountCode01(null);
                
                //3.3) 口座番号（10万通貨コース）
                //説明 : null
                l_paramsForUpdate.setFxAccountCode10(null);
    
                
                //3.4) 口座開設状況区分
                //説明 : 9：削除
                l_paramsForUpdate.setAccountOpenStatusDiv(WEB3AccountOpenStatusDivDef.DELETE);
                
                //3.5) 更新者コード
                //説明 : 引数.更新者コード
                l_paramsForUpdate.setLastUpdater(l_strUpdaterCode);
                
                //3.6) 更新日付
                //説明 : 現在時刻
                l_paramsForUpdate.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());            

                //3.7) 連携用口座番号
                //説明 : null
                l_paramsForUpdate.setExtAccountCode(null);
            }
            
            //　@[パラメータ.更新後ステータス区分が未設定（=null）の場合]
            //   【ｘTrade】補足資料.DB更新 
            //   「管理者・口座開設管理_GFT口座開設状況テーブルDB更新仕様.xls」
            //   の「(口座開設管理)[約諾書更新]GFT口座開設状況テーブル」シート
            if(l_strUpdatedStatusDiv == null)
            {
                //3.1) 更新者コード
                //説明 : 引数.更新者コード
                l_paramsForUpdate.setLastUpdater(l_strUpdaterCode);
                
                //3.2) 更新日付
                //説明 : 現在時刻
                l_paramsForUpdate.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                
                //3.2) 約諾書区分
                //説明 : 更新後約諾書区分
                if(l_strUpdatedAgreementDiv != null)
                {
                    l_paramsForUpdate.setAgreementDiv(l_strUpdatedAgreementDiv);
                }
                else
                {
                    log.debug("パラメータ値不正。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        this.getClass().getName() + "." + STR_METHOD_NAME, 
                        "パラメータ値不正。");
                }
            }
            
            //４）GFT口座開設状況のupdate 
            //WEB3DataAccessUtility.updateRow()メソッドをコールする。 
            //
            //　@[updateRow()にセットするパラメータ] 
            //　@　@l_row：　@更新後のデータをセットしたインスタンス
            WEB3DataAccessUtility.updateRow(l_paramsForUpdate);
        
        }
        catch (DataException l_ex)
        {
            log.debug("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (updateGFT口座開設状況) <BR>
     * GFT結果通知の状態でGFT口座開設状況テーブルのデータをupdateする。 <BR>
     * <BR>
     * １）GFT口座開設状況Paramsインスタンスを作成する。 <BR>
     * <BR>
     * ２）生成したインスタンスに、引数.GFT口座開設状況のプロパティをコピーする。 <BR>
     * <BR>
     * ３）生成したインスタンスに、更新後のデータをセットする。 <BR>
     * <BR>
     * 更新する行の内容は下記を参照。 <BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新 <BR>
     * 「FX口座開設_GFT口座開設状況テーブル.xls」の <BR>
     * 「GFT口座開設状況テーブル_DB更新仕様_[結果通知]」シート <BR>
     * <BR>
     * ４）GFT口座開設状況のupdate <BR>
     * WEB3DataAccessUtility.updateRow()メソッドをコールする。 <BR>
     * <BR>
     * [updateRow()にセットするパラメータ] l_row： <BR>
     * 　@　@l_row：　@３）にて更新後のデータをセットしたインスタンス<BR>
     * <BR>
     * @@param l_gftAccountOpenStatusParams - GFT口座開設状況Paramsオブジェクト
     * @@param l_fxGftResultNoticeTelegramUnit - GFT結果通知電文明細
     * @@param l_strErrorReasonCode - エラー理由コード
     * @@throws WEB3BaseException
     * @@roseuid 41C97A0D02E8
     */
    public void updateGFTAccountOpenStatus(
        GftAccountOpenStatusParams l_gftAccountOpenStatusParams,
        WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit,
        String l_strErrorReasonCode) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateGFTAccountOpenStatus(GftAccountOpenStatusParams l_gftAccountOpenStatusParams, " +
            "WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit," +
            "String l_strErrorReasonCode)";
        log.entering(STR_METHOD_NAME);
        
        if(l_gftAccountOpenStatusParams == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）GFT口座開設状況Paramsインスタンスを作成する。
        //２）生成したインスタンスに、引数.GFT口座開設状況のプロパティをコピーする。
        GftAccountOpenStatusParams l_paramsForUpdate =
            new GftAccountOpenStatusParams(l_gftAccountOpenStatusParams);
        
        //３）生成したインスタンスに、更新後のデータをセットする。
            //更新する行の内容は下記を参照。
            //【ｘTrade】補足資料.DB更新
            //「FX口座開設_GFT口座開設状況テーブル.xls」の
            //「GFT口座開設状況テーブル_DB更新仕様_[結果通知]」シート

            //3.1) 初期パスワード
            //説明 : null
            l_paramsForUpdate.setPassword(null);

            //3.2) 口座番号（1万通貨コース）
            //説明 : 引数.エラー理由コード＝0000（正常）の且つ、
            //引数.GFT結果通知明細.為替保証金口座情報一覧[n].コース区分==”1”の場合:
            //引数.GFT結果通知電文明細.為替保証金口座番号(1万通貨)
            //上記以外の場合：（既存値）
            //3.3) 口座番号（10万通貨コース）
            //説明 : 引数.エラー理由コード＝0000（正常）且つ、
            //引数.GFT結果通知明細.為替保証金口座情報一覧[n].コース区分==”2”の場合：
            //引数.GFT結果通知電文明細.為替保証金口座番号(10万通貨)
            //上記以外の場合：（既存値）
            //3.4) 口座開設状況区分
            //説明 : 引数.エラー理由コード＝0000（正常）の場合：1（口座開設完了）
            //上記以外の場合：2（口座開設エラー）

            if(WEB3GftErrorReasonCodeDef.NORMAL.equals(l_strErrorReasonCode))
            {
                WEB3FXAccInformationUnit[] l_fXAccInformationUnits =
                    l_fxGftResultNoticeTelegramUnit.fxAccInformationList;
                int l_intFXAccInformationUnitsLength = 0;
                if (l_fXAccInformationUnits != null)
                {
                    l_intFXAccInformationUnitsLength = l_fXAccInformationUnits.length;
                }
                for (int i = 0; i < l_intFXAccInformationUnitsLength; i++)
                {

                    if (WEB3GftTransStatusCourseDivDef.ONE_COSE.equals(
                        l_fXAccInformationUnits[i].fxCourseDiv))
                    {
                        l_paramsForUpdate.setFxAccountCode01(
                            l_fXAccInformationUnits[i].fxAccountCode);
                    }

                    if (WEB3GftTransStatusCourseDivDef.TEN_COSE.equals(
                        l_fXAccInformationUnits[i].fxCourseDiv))
                    {
                        l_paramsForUpdate.setFxAccountCode10(
                            l_fXAccInformationUnits[i].fxAccountCode);
                    }

                    //3.9) 連携用口座番号
                    //説明 :
                    //引数.エラー理由コード＝0000（正常）且つ、
                    //引数.GFT結果通知明細.為替保証金口座情報一覧[n].コース区分==”3”の場合：
                    //       引数.GFT結果通知電文明細.為替保証金口座番号(CFDコース)
                    //       上記以外の場合：（既存値）

                    if (WEB3GftTransStatusCourseDivDef.CFD_COURSE.equals(
                            l_fXAccInformationUnits[i].fxCourseDiv))
                    {
                        l_paramsForUpdate.setExtAccountCode(
                            l_fXAccInformationUnits[i].fxAccountCode);
                    }
                }

                l_paramsForUpdate.setAccountOpenStatusDiv(
                    WEB3AccountOpenStatusDivDef.ACCOUNT_OPEN_COMPLETE);
            }
            else
            {
                l_paramsForUpdate.setAccountOpenStatusDiv(
                    WEB3AccountOpenStatusDivDef.ACCOUNT_OPEN_ERROR);
            }

            //3.5) 送受信区分
            //説明 :
            //引数.エラー理由コード＝（0000（正常）、または、0004（受付結果コードチェックエラー））の場合：
            //2（受信済）
            //上記以外の場合：3（受信エラー）
            if(WEB3GftErrorReasonCodeDef.NORMAL.equals(l_strErrorReasonCode)
                || WEB3GftErrorReasonCodeDef.RESULT_CODE_ERROR.equals(
                    l_strErrorReasonCode) )
            {
                l_paramsForUpdate.setSendRcvDiv(
                    WEB3SendRcvDivDef.RECEIVE_COMPLETE);
            }
            else
            {
                l_paramsForUpdate.setSendRcvDiv(
                    WEB3SendRcvDivDef.RECEIVE_ERROR);
            }

            //3.6) 受付結果コード
            //説明 : 引数.GFT結果通知電文明細.受付結果
            l_paramsForUpdate.setResultCode(
                l_fxGftResultNoticeTelegramUnit.resultCode);

            //3.7) エラー理由コード
            //説明 : 引数.エラー理由コード
            l_paramsForUpdate.setErrorReasonCode(l_strErrorReasonCode);

            //3.8) 更新日付
            //説明 : 現在時刻
            l_paramsForUpdate.setLastUpdatedTimestamp(
                GtlUtils.getSystemTimestamp());

        //４）GFT口座開設状況のupdate
        //WEB3DataAccessUtility.updateRow()メソッドをコールする。
        //[updateRow()にセットするパラメータ]
        //l_row：　@３）にて更新後のデータをセットしたインスタンス
        try
        {
            WEB3DataAccessUtility.updateRow(l_paramsForUpdate);
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

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (updateGFT振替状況) <BR>
     * 引数のGFT振替状況ParamsでGFT振替状況テーブルを更新する。 <BR>
     * <BR>
     * １）QueryProcessor.doUpdateQuery()メソッドをコールする。 <BR>
     * <BR>
     * [doUpdateQuery()にセットするパラメータ] <BR>
     * arg0： パラメータ.GFT振替状況Params <BR>
     * @@param l_gftTransferStatusParams - GFT振替状況Paramsオブジェクト
     * @@throws WEB3BaseException
     * @@roseuid 41C1301F0156
     */
    public void updateGFTTransferStatus(
        GftTransferStatusParams l_gftTransferStatusParams) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateGFTTransferStatus(GftTransferStatusParams l_gftTransferStatusParams)";
        log.entering(STR_METHOD_NAME);
        
        if(l_gftTransferStatusParams == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.debug("l_gftTransferStatusParams = " + l_gftTransferStatusParams);
        //１）QueryProcessor.doUpdateQuery()メソッドをコールする。
        //　@[doUpdateQuery()にセットするパラメータ] 
        //　@　@arg0：　@パラメータ.GFT振替状況Params
        try
        {
            Processors.getDefaultProcessor().doUpdateQuery(l_gftTransferStatusParams);
        }
        catch (DataException l_ex)
        {
            log.debug("__DataException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (updateGFT振替状況) <BR>
     * GFT結果通知の状態でGFT振替状況テーブルのデータをupdateする。 <BR>
     * <BR>
     * １）GFT振替状況Paramsインスタンスを作成する。 <BR>
     * <BR>
     * ２）生成したインスタンスに、引数.GFT振替状況のプロパティをコピーする。 <BR>
     * <BR>
     * ３）生成したインスタンスに、更新後のデータをセットする。 <BR>
     * <BR>
     * 更新する行の内容は下記を参照。 <BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新 <BR>
     * 「振替共通_GFT振替状況テーブル.xls」の <BR>
     * 「GFT振替状況テーブル_DB更新仕様_[結果通知]」シート <BR>
     * <BR>
     * ４）GFT振替状況のupdate <BR>
     * WEB3DataAccessUtility.updateRow()メソッドをコールする。 <BR>
     * <BR>
     * [updateRow()にセットするパラメータ] <BR>
     * l_row： ３）にて更新後のデータをセットしたインスタンス <BR>
     * 
     * @@param l_gftTransferStatusParams - GFT振替状況Paramsオブジェクト
     * @@param l_fxGftResultNoticeTelegramUnit - GFT結果通知電文明細
     * @@param l_strUpdatedTransferDate - YYYYMMDD
     * 更新後受渡予定日
     * ※受渡予定日を更新しない場合はnull
     * @@param l_strErrorReasonCode - エラー理由コード
     * @@throws WEB3BaseException
     * @@roseuid 41C18781002B
     */
    public void updateGFTTransferStatus(
        GftTransferStatusParams l_gftTransferStatusParams,
        WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit,
        String l_strUpdatedTransferDate, String l_strErrorReasonCode) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateGFTTransferStatus(GftTransferStatusParams l_gftTransferStatusParams, " +
            "WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit," +
            "String l_strUpdatedTransferDate, String l_strErrorReasonCode)";
        log.entering(STR_METHOD_NAME);
        
        if(l_gftTransferStatusParams == null || l_fxGftResultNoticeTelegramUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）GFT振替状況Paramsインスタンスを作成する。 
        //２）生成したインスタンスに、引数.GFT振替状況のプロパティをコピーする。
        GftTransferStatusParams l_gftTransferStatusParamsForUpdate = 
            new GftTransferStatusParams(l_gftTransferStatusParams);
        
        //３）生成したインスタンスに、更新後のデータをセットする。 
        //　@更新する行の内容は下記を参照。 
        //　@【ｘTrade】補足資料.DB更新 
        //　@「振替共通_GFT振替状況テーブル.xls」の 
        //　@「GFT振替状況テーブル_DB更新仕様_[結果通知]」シート
        try
        {
            //3.1) 受渡予定日
            //説明 : 引数.更新後受渡日予定日 != nullの場合、引数.更新後受渡予定日
            //以外、（既存値）
            if(l_strUpdatedTransferDate != null)
            {
                l_gftTransferStatusParamsForUpdate.setTransferDate(l_strUpdatedTransferDate);
            }
            
            //3.2) 振替状況区分
            //説明 : 引数.エラー理由コード＝0000（正常）の場合：1（決済完了）
            //上記以外の場合：2（決済エラー）
            if(WEB3GftErrorReasonCodeDef.NORMAL.equals(l_strErrorReasonCode))
            {
                l_gftTransferStatusParamsForUpdate.setTransferStatusDiv(WEB3TransferStatusDivDef.PROCESS_COMPLETE);
            }
            else
            {
                l_gftTransferStatusParamsForUpdate.setTransferStatusDiv(WEB3TransferStatusDivDef.PROCESS_ERROR);
            }
            
            //3.3) 送受信区分
            //説明 : 引数.エラー理由コード＝（0000（正常）、または、0004（受付結果コードチェックエラー））の場合：2（受信済）
            //上記以外の場合：3（受信エラー）
            if(WEB3GftErrorReasonCodeDef.NORMAL.equals(l_strErrorReasonCode) 
                || WEB3GftErrorReasonCodeDef.RESULT_CODE_ERROR.equals(l_strErrorReasonCode) )
            {
                l_gftTransferStatusParamsForUpdate.setSendRcvDiv(WEB3SendRcvDivDef.RECEIVE_COMPLETE);    
            }
            else
            {
                l_gftTransferStatusParamsForUpdate.setSendRcvDiv(WEB3SendRcvDivDef.RECEIVE_ERROR);
            }
    
            //3.4) 受付結果コード
            //説明 : 引数.GFT結果通知電文明細.受付結果
            l_gftTransferStatusParamsForUpdate.setResultCode(l_fxGftResultNoticeTelegramUnit.resultCode);
    
            //3.5) エラー理由コード
            //説明 : 引数.エラー理由コード
            l_gftTransferStatusParamsForUpdate.setErrorReasonCode(l_strErrorReasonCode);
            
            //3.6) 処理時間（受信）
            //説明 : 引数.GFT結果通知電文明細.GFT→DIR送信日時
            l_gftTransferStatusParamsForUpdate.setReceiveTime(l_fxGftResultNoticeTelegramUnit.gftSendTime);
            
            
            //3.7) 更新日付
            //説明 : 現在時刻
            l_gftTransferStatusParamsForUpdate.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            //４）GFT振替状況のupdate 
            //WEB3DataAccessUtility.updateRow()メソッドをコールする。 
            //
            //　@[updateRow()にセットするパラメータ] 
            //　@　@l_row：　@３）にて更新後のデータをセットしたインスタンス
            //insert GFT振替状況テーブル
            
            WEB3DataAccessUtility.updateRow(l_gftTransferStatusParamsForUpdate);
        
        }
        catch (DataException l_ex)
        {
            log.debug("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (updateGFT振替状況) <BR>
     * GFT振替状況テーブルの振替状況区分をupdateする。 <BR>
     * <BR>
     * １）GFT振替状況Paramsインスタンスを作成する。 <BR>
     * <BR>
     * ２）生成したインスタンスに、引数.GFT振替状況のプロパティをコピーする。 <BR>
     * <BR>
     * ３）生成したインスタンスに、更新後のデータをセットする。 <BR>
     * <BR>
     * 更新する行の内容は下記を参照。 <BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新 <BR>
     * 「FX振替共通_GFT振替状況テーブル.xls」の <BR>
     * 「GFT振替状況テーブル_DB更新仕様[保証金振替管理]」シート <BR>
     * <BR>
     * ４）GFT振替状況のupdate <BR>
     * WEB3DataAccessUtility.updateRow()メソッドをコールする。 <BR>
     * <BR>
     * [updateRow()にセットするパラメータ] <BR>
     * l_row： ３）にて更新後のデータをセットしたインスタンス <BR>
     * @@param l_gftTransferStatusParams - GFT振替状況Paramsオブジェクト
     * @@param l_strUpdatedTransferStatusDiv - 更新後の振替状況区分
     * @@param l_strUpdaterCode - 更新者コード
     * @@throws WEB3BaseException
     * @@roseuid 41CBC3DD00B3
     */
    public void updateGFTTransferStatus(
        GftTransferStatusParams l_gftTransferStatusParams,
        String l_strUpdatedTransferStatusDiv, String l_strUpdaterCode) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateGFTTransferStatus(GftTransferStatusParams l_gftTransferStatusParams, " +
            "String l_strUpdatedTransferStatusDiv, String l_strUpdaterCode)";
        log.entering(STR_METHOD_NAME);
        
        if(l_gftTransferStatusParams == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）GFT振替状況Paramsインスタンスを作成する。
        //２）生成したインスタンスに、引数.GFT振替状況のプロパティをコピーする。
        GftTransferStatusParams l_gftTransferStatusParamsForUpdate = 
            new GftTransferStatusParams(l_gftTransferStatusParams);
        
        //３）生成したインスタンスに、更新後のデータをセットする。 
        //　@更新する行の内容は下記を参照。 
        //　@【ｘTrade】補足資料.DB更新 
        //　@「FX振替共通_GFT振替状況テーブル.xls」の 
        //　@「GFT振替状況テーブル_DB更新仕様[保証金振替管理]」シート
        try
        {
            //3.1) 振替状況区分
            //説明 : 引数.更新後振替状況区分
            l_gftTransferStatusParamsForUpdate.setTransferStatusDiv(l_strUpdatedTransferStatusDiv);
            
            //3.2) 更新者コード）
            //説明 : 引数.更新者コード
            l_gftTransferStatusParamsForUpdate.setLastUpdater(l_strUpdaterCode);
            
            //3.3) 更新日付
            //説明 : 現在時刻
            l_gftTransferStatusParamsForUpdate.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            //４）GFT振替状況のupdate 
            //WEB3DataAccessUtility.updateRow()メソッドをコールする。 
            //　@[updateRow()にセットするパラメータ] 
            //　@　@l_row：　@３）にて更新後のデータをセットしたインスタンス            
            WEB3DataAccessUtility.updateRow(l_gftTransferStatusParamsForUpdate);
        
        }
        catch (DataException l_ex)
        {
            log.debug("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (updateGFT振替状況) <BR>
     * SOAP受信結果をGFT振替状況テーブルのデータに反映する。 <BR>
     *  <BR>
     * １）GFT振替状況Paramsインスタンスを取得する。 <BR>
     *  <BR>
     *    this.getGFT振替状況()をコールする。 <BR>
     *  <BR>
     *    [引数] <BR>
     *    証券会社コード： 引数.証券会社コード <BR>
     *    部店コード： 引数.部店コード <BR>
     *    識別コード： 引数.識別コード <BR>
     *  <BR>
     * ２）取得したGFT振替状況Paramsのcloneを生成する。 <BR>
     *  <BR>
     * ３）cloneに、受付結果コードをセットする。 <BR>
     *  <BR>
     * 　@更新する行の内容は下記を参照。 <BR>
     *  <BR>
     * 　@【ｘTrade】補足資料.DB更新  <BR>
     * 　@「FX振替共通_GFT振替状況テーブル.xls」の <BR>
     * 　@「GFT振替状況テーブル_DB更新仕様[SOAP接続結果更新]」シート <BR>
     *  <BR>
     * ４）GFT振替状況のupdate <BR>
     *  WEB3DataAccessUtility.updateRow()メソッドをコールする。 <BR>
     *  <BR>
     * 　@[updateRow()にセットするパラメータ] <BR>
     * 　@　@l_row：　@３）にて受付結果コードをセットしたインスタンス <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strRequestCode - (識別コード)<BR>
     * 識別コード<BR>
     * @@param l_strResultCode - (受付結果コード)<BR>
     * 受付結果コード<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41CBC3DD00B3
     */
    public void updateGFTTransferStatus(
        String l_strInstitutionCode, String l_strBranchCode,
        String l_strRequestCode, String l_strResultCode) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateGFTTransferStatus(String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //１）GFT振替状況Paramsインスタンスを取得する。
        //this.getGFT振替状況()をコールする。
        //[引数]
        //証券会社コード： 引数.証券会社コード
        //部店コード： 引数.部店コード
        //識別コード： 引数.識別コード
        GftTransferStatusParams l_gftTransferStatusParams =
            this.getGFTTransferStatus(l_strInstitutionCode, l_strBranchCode, l_strRequestCode);

        if (l_gftTransferStatusParams == null)
        {
            log.debug("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "テーブルに該当するデータがありません。");
        }
    
        //２）取得したGFT振替状況Paramsのcloneを生成する。
        GftTransferStatusParams l_gftTransferStatusParamsClone = new GftTransferStatusParams(l_gftTransferStatusParams);

        //更新する行の内容は下記を参照。
        //【ｘTrade】補足資料.DB更新 
        //「FX振替共通_GFT振替状況テーブル.xls」の
        //「GFT振替状況テーブル_DB更新仕様[SOAP接続結果更新]」シート
        try
        {
            //３）cloneに、受付結果コードをセットする。
            l_gftTransferStatusParamsClone.setResultCodeSoap(l_strResultCode);

            //3.3) 更新日付
            //説明 : 現在時刻
            l_gftTransferStatusParamsClone.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            //４）GFT振替状況のupdate
            //WEB3DataAccessUtility.updateRow()メソッドをコールする。
            //[updateRow()にセットするパラメータ]
            //l_row：　@３）にて受付結果コードをセットしたインスタンス            
            WEB3DataAccessUtility.updateRow(l_gftTransferStatusParamsClone);
        }
        catch (DataQueryException l_dqex)
        {
            log.error("テーブルに該当するデータがありません。", l_dqex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqex.getMessage(),
                l_dqex);
        }
        catch (DataNetworkException l_dnex)
        {
            log.error("DBアクセスエラー", l_dnex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dnex.getMessage(),
                l_dnex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (updateFX口座開設区分) <BR>
     * 顧客マスターテーブルのFX口座開設区分をupdateする。 <BR>
     * <BR>
     * １）顧客の取得 <BR>
     * 拡張アカウントマネージャ.get顧客()にて顧客オブジェクトを取得する。 <BR>
     * <BR>
     * [引数の設定] <BR>
     * 証券会社コード： 引数.証券会社コード <BR>
     * 部店コード： 引数.部店コード <BR>
     * 口座コード： 引数.顧客コード <BR>
     * <BR>
     * 取得した顧客.getDataSourceObject()により顧客Paramsを取得する。 <BR>
     * <BR>
     * ３）生成したインスタンスに、更新後のデータをセットする。 <BR>
     * <BR>
     * 更新する行の内容は下記を参照。 <BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新 <BR>
     * 「FX口座開設共通_顧客マスター.xls」参照 <BR>
     * <BR>
     * ４）顧客のupdate <BR>
     * WEB3DataAccessUtility.updateRow()メソッドをコールする。 <BR>
     * <BR>
     * [updateRow()にセットするパラメータ] <BR>
     * l_row： ３）にて更新後のデータをセットしたインスタンス <BR>
     * 
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strAccountCode - 顧客コード
     * @@param l_strUpdatedFxAccOpenDiv - 更新後FX口座開設区分
     *          0：DEFAULT(口座なし) 1：口座開設
     * @@param l_strUpdaterCode - 更新者コード
     * @@throws WEB3BaseException
     * @@roseuid 41CBF15D02DF
     */
    public void updateFXAccountOpenDiv(String l_strInstitutionCode,
        String l_strBranchCode, String l_strAccountCode,
        String l_strUpdatedFxAccOpenDiv, String l_strUpdaterCode)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateFXAccountOpenDiv(String l_strInstitutionCode," +
            "String l_strBranchCode, String l_strAccountCode, " +
            "String l_strUpdatedFxAccOpenDiv, String l_strUpdaterCode)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //１）顧客の取得 
            //　@拡張アカウントマネージャ.get顧客()にて顧客オブジェクトを取得する。 
            //　@[引数の設定] 
            //　@証券会社コード：　@引数.証券会社コード 
            //　@部店コード：　@引数.部店コード 
            //　@口座コード：　@引数.顧客コード 
            //　@取得した顧客.getDataSourceObject()により顧客Paramsを取得する。
            WEB3GentradeAccountManager l_web3GentradeAccountManager =
                (WEB3GentradeAccountManager)GtlUtils.getAccountManager();
            
            MainAccount l_mainAccount = 
                l_web3GentradeAccountManager.getMainAccount(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
            
            MainAccountRow l_mainAccountRow = 
                (MainAccountRow)l_mainAccount.getDataSourceObject();
            
            //３）生成したインスタンスに、更新後のデータをセットする。 
            //　@更新する行の内容は下記を参照。 
            //　@【ｘTrade】補足資料.DB更新 
            //　@「FX口座開設共通_顧客マスター.xls」参照
            MainAccountParams l_mainAccountParams = new MainAccountParams(l_mainAccountRow);
            
            //3.1) ＦＸ口座開設区分
            //説明 : 引数.更新後FX口座開設区分
            l_mainAccountParams.setFxAccOpenDiv(l_strUpdatedFxAccOpenDiv);
            
            //3.2) ＦＸ口座開設区分更新者コード
            //説明 : 引数.更新者コード
            l_mainAccountParams.setFxAccOpenDivLastUpdater(l_strUpdaterCode);

            //3.3) ＦＸ口座開設区分更新日時
            //説明 : 現在時刻
            l_mainAccountParams.setFxAccOpenUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //3.4) 更新日時
            l_mainAccountParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //４）顧客のupdate 
            //WEB3DataAccessUtility.updateRow()メソッドをコールする。 
            //　@[updateRow()にセットするパラメータ] 
            //　@　@l_row：　@３）にて更新後のデータをセットしたインスタンス
            WEB3DataAccessUtility.updateRow(l_mainAccountParams);
        }
        catch (DataException l_ex)
        {
            log.debug("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (createFX口座情報一覧) <BR>
     * 引数の条件に該当するFX口座情報の一覧を作成する。 <BR>
     * <BR>
     * １）this.getFX口座番号()メソッドをコールする。 <BR>
     * <BR>
     * [getFX口座番号()にセットするパラメータ] <BR>
     * 証券会社コード： 引数.証券会社コード <BR>
     * 部店コード： 引数.部店コード <BR>
     * 顧客コード： 引数.顧客コード <BR>
     * <BR>
     * nullが返却された場合、nullを返却する。 <BR>
     * <BR>
     * ２）ArrayListを生成する。 <BR>
     * <BR>
     * ３）１）の戻り値の要素数分、以下の処理を繰り返す。 <BR>
     * ３－１）FX口座情報インスタンスを生成する。 <BR>
     * <BR>
     * ３－２）生成したインスタンスに以下のプロパティをセットする。 <BR>
     * コース区分 = FX口座番号Params.FXコース区分 <BR>
     * 口座番号 = FX口座番号Params.FX口座番号 <BR>
     * <BR>
     * ３－３）ArrayListにプロパティセットしたインスタンスを追加する。 <BR>
     * <BR>
     * ４）生成したArrayList.toArray()の戻り値を返却する。 <BR>
     * 
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strAccountCode - 顧客コード
     * @@return webbroker3.aio.message.WEB3FXAccInformationUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 41C7EF4C0152
     */
    public WEB3FXAccInformationUnit[] createFXAccInformationUnits(
        String l_strInstitutionCode, String l_strBranchCode,
        String l_strAccountCode) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "createFXAccInformationUnits(String l_strInstitutionCode, " +
            " String l_strBranchCode,String l_strAccountCode)";
        log.entering(STR_METHOD_NAME);

        //１）this.getFX口座番号()メソッドをコールする。 
        //　@[getFX口座番号()にセットするパラメータ] 
        //　@　@証券会社コード：　@引数.証券会社コード 
        //　@　@部店コード：　@引数.部店コード 
        //　@　@顧客コード：　@引数.顧客コード 
        //　@nullが返却された場合、nullを返却する。 
        FxAccountCodeParams[] l_fxAccountCodeParams = 
            this.getFXAccountCodes(l_strInstitutionCode, l_strBranchCode, l_strAccountCode);
        
        if(l_fxAccountCodeParams == null)
        {
            return null;
        }

        //２）ArrayListを生成する。
        List l_lisFXAccInformationUnit = new Vector();
        
        for(int i = 0; i < l_fxAccountCodeParams.length; i++)
        {
            //３）１）の戻り値の要素数分、以下の処理を繰り返す。 
            //　@３－１）FX口座情報インスタンスを生成する。 
            WEB3FXAccInformationUnit l_updatedFxAccInformation = new WEB3FXAccInformationUnit();
            
            //　@３－２）生成したインスタンスに以下のプロパティをセットする。 
            //　@　@コース区分 = FX口座番号Params.FXコース区分
            l_updatedFxAccInformation.fxCourseDiv = l_fxAccountCodeParams[i].getFxCourseDiv();
            
            //　@　@口座番号 = FX口座番号Params.FX口座番号 
            l_updatedFxAccInformation.fxAccountCode = l_fxAccountCodeParams[i].getFxAccountCode();
            
            //　@３－３）ArrayListにプロパティセットしたインスタンスを追加する。 
            l_lisFXAccInformationUnit.add(l_updatedFxAccInformation);
        }

        //４）生成したArrayList.toArray()の戻り値を返却する。
        WEB3FXAccInformationUnit[] l_fXAccInformationUnit = 
            new WEB3FXAccInformationUnit[l_fxAccountCodeParams.length];
        
        l_lisFXAccInformationUnit.toArray(l_fXAccInformationUnit);
        
        log.exiting(STR_METHOD_NAME);
        return l_fXAccInformationUnit;
            
    }

    /**
     * (get新規FXログインID) <BR>
     * FXログインIDを付番して返却する。<BR> 
     * <BR>
     * FXログインIDの付番ルールは<BR> 
     * FXログインID頭文字 + 顧客コードとする。<BR> 
     * <BR>
     * 引数.FXログインID頭文字 + 引数.顧客コード(*)を文字列連結した値を返却する。<BR> 
     * <BR>
     * (*)顧客コードの先頭から6桁目までを使用する<BR> 
     * @@param l_strFXloginFirstChar - FXログインID頭文字
     * @@param l_strAccountCode - 顧客コード
     * @@return String
     * @@roseuid 41C964AA0206
     */
    public String getNewFXLoginID(String l_strFXloginFirstChar,
        String l_strAccountCode)
    {
        String STR_METHOD_NAME =
            "getNewFXLoginID(String l_strFXloginFirstChar,String l_strAccountCode)";
        log.entering(STR_METHOD_NAME);
        
        if(l_strAccountCode == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //FXログインIDの付番ルールは 
        //FXログインID頭文字 + 顧客コードとする。 
        //引数.FXログインID頭文字 + 引数.顧客コード(*)を文字列連結した値を返却する。 
        //(*)顧客コードの先頭から6桁目までを使用する 
        String l_strAccountCodeNew = l_strAccountCode;
        if(l_strAccountCode.length() > 6)
        {
            l_strAccountCodeNew = l_strAccountCode.substring(0, 6);
        }
        
        String l_strFxLoginId = l_strFXloginFirstChar + l_strAccountCodeNew;
        
        log.exiting(STR_METHOD_NAME);
        return l_strFxLoginId;
    }

    /**
     * (get新規FX顧客ID) <BR>
     * FX顧客IDを付番して返却する。 <BR>
     * <BR>
     * FX顧客IDの付番ルールは <BR>
     * 証券会社ID + 部店コード + FXシステムコード + 顧客コード(*1)とする。 <BR>
     * <BR>
     * 証券会社ID(*2) + 引数.部店コード + 引数.FXシステムコード<BR>
     * 　@+ 引数.顧客コードを文字列連結した値を返却する。<BR>
     * <BR>
     * (*1)<BR>
     * 引数.顧客コード == 7桁の場合、 <BR>
     * 引数.顧客コードの先頭から6桁目までを使用する <BR>
     * <BR>
     * (*2)<BR>
     * 拡張アカウントマネージャ.getInstitution().証券会社ID <BR>
     * <BR>
     * [getInstitution()にセットするパラメータ] <BR>
     * 証券会社コード： 引数.証券会社コード <BR>
     * <BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strAccountCode - 顧客コード
     * @@param l_strFxSystemCode - FXシステムコード
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 41C9730F01B7
     */
    public String getNewFXAccountID(String l_strInstitutionCode,
        String l_strBranchCode, String l_strAccountCode, String l_strFxSystemCode)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "getNewFXAccountID(String l_strInstitutionCode, " +
            "String l_strBranchCode, String l_strAccountCode, String l_strFxSystemCode)";
        log.entering(STR_METHOD_NAME);
        
        if(l_strAccountCode == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //FX顧客IDの付番ルールは 
        //証券会社ID + 部店コード + FXシステムコード + 顧客コード(*1)とする。 
        //証券会社ID(*2) + 引数.部店コード + 引数.FXシステムコード + 引数.顧客コードを文字列連結した値を返却する
        //(*1)
        //引数.顧客コード == 7桁の場合、
        //引数.顧客コードの先頭から6桁目までを使用する 
        String l_strAccountCodeNew = l_strAccountCode;
        if(l_strAccountCode.length() == 7)
        {
            l_strAccountCodeNew = l_strAccountCode.substring(0, 6);
        }
        
        long l_lngInstitutionId = 0;
        try
        {
            //(*2)
            //拡張アカウントマネージャ.getInstitution().証券会社ID 
            //[getInstitution()にセットするパラメータ] 
            //証券会社コード：　@引数.証券会社コード
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            Institution l_institution = l_accountManager.getInstitution(
                l_strInstitutionCode);

            l_lngInstitutionId = l_institution.getInstitutionId();
        }
        catch(NotFoundException l_ex)
        {
            log.debug("__NotFoundException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex); 
        }

        String l_strFxAccountId = Long.toString(l_lngInstitutionId) + l_strBranchCode 
            + l_strFxSystemCode + l_strAccountCodeNew;
        
        log.exiting(STR_METHOD_NAME);
        return l_strFxAccountId;
    }

    /**
     * (validateFX取引同意質問) <BR>
     * FX取引同意質問に対する回答の整合性をチェックする。 <BR>
     * <BR>
     * 引数.FX取引同意質問情報一覧の要素ごとのLoop処理にて、 <BR>
     * 以下のチェックを行う。 <BR>
     * <BR>
     * FX取引同意質問情報.質問回答≠”1：同意”の場合、例外をthorwする。 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01796 <BR>
     * <BR>
     * @@param l_fxTradeAgreementList - FX取引同意質問情報の一覧
     * @@throws WEB3BaseException
     * @@roseuid 41D0F49300CF
     */
    public void validateFXTradingAgreeQuestion(
        WEB3FXTradeAgreementUnit[] l_fxTradeAgreementList) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "validateFXTradingAgreeQuestion(WEB3FXTradeAgreementUnit[] l_fxTradeAgreementList)";
        log.entering(STR_METHOD_NAME);
        
        if(l_fxTradeAgreementList == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //引数.FX取引同意質問情報一覧の要素ごとのLoop処理にて、 
        //以下のチェックを行う。
        //FX取引同意質問情報.質問回答≠”1：同意”の場合、例外をthorwする。
        for(int i = 0; i < l_fxTradeAgreementList.length; i++)
        {
            log.debug("l_fxTradeAgreementList[" + i + "].questionAnswer = " + l_fxTradeAgreementList[i].questionAnswer);
            if(!WEB3AioQuestionAnswerDef.AGREE.equals(l_fxTradeAgreementList[i].questionAnswer))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01796,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "FX取引同意質問情報.質問回答≠”1：同意”");
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (submit取消注文) <BR>
     * 振替注文の取消処理と余力の更新処理を行う。 <BR>
     * <BR>
     * １）口座のロックをを行う。 <BR>
     * <BR>
     * 拡張アカウントマネージャ.lock口座()をコールする。 <BR>
     * <BR>
     * [引数] <BR>
     * 証券会社コード： 引数.証券会社コード <BR>
     * 部店コード： 引数.部店コード <BR>
     * 口座コード： 引数.顧客コード <BR>
     * <BR>
     * ２）振替注文の取消処理を行う。 <BR>
     * <BR>
     * AIO注文マネージャ.振替注文取消()をコールする。 <BR>
     * <BR>
     * [引数] <BR>
     * 証券会社コード： 引数.証券会社コード <BR>
     * 部店コード： 引数.部店コード <BR>
     * 顧客コード： 引数.顧客コード <BR>
     * 識別コード： 引数.識別コード <BR>
     * 信用振替用識別コード： 引数.信用振替用識別コード <BR>
     * <BR>
     * ３）補助口座オブジェクトを取得する。 <BR>
     * <BR>
     * ３－１） <BR>
     * 拡張アカウントマネージャ.get顧客()をコールする。 <BR>
     * <BR>
     * [引数] <BR>
     * 証券会社コード： 引数.証券会社コード <BR>
     * 部店コード： 引数.部店コード <BR>
     * 口座コード： 引数.顧客コード <BR>
     * <BR>
     * ３－２） <BR>
     * 顧客.getSubAccount()をコールする。 <BR>
     * <BR>
     * [引数] <BR>
     * 補助口座タイプ： ”預り金口座” <BR>
     * <BR>
     * ４）余力の更新を行う。 <BR>
     * <BR>
     * 余力取引サービスImpl.余力再計算()をコールする。 <BR>
     * <BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strAccountCode - 顧客コード
     * @@param l_strRequestNumber - 識別コード
     * @@param l_strMrgTrnRequestNumber - 信用振替用識別コード
     * @@throws WEB3BaseException
     * @@roseuid 41D0F49300CF
     */
    public void submitCancelOrder(
        String l_strInstitutionCode, String l_strBranchCode, 
        String l_strAccountCode, String l_strRequestNumber, 
        String l_strMrgTrnRequestNumber) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "submitCancelOrder(" +
            "String l_strInstitutionCode, String l_strBranchCode, " + 
            "String l_strAccountCode, String l_strRequestNumber, " +
            "String l_strMrgTrnRequestNumber)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        //１）口座のロックをを行う。
        //拡張アカウントマネージャ.lock口座()をコールする。
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        try
        {
            l_accountManager.lockAccount(
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("__error in lockAccount__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //２）振替注文の取消処理を行う。
        //入出金注文マネージャクラスを取得する。
        WEB3AioOrderManager l_aioOrderManager =
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
        
        //AIO注文マネージャ.振替注文取消()をコールする。
        l_aioOrderManager.transferOrderCancel(l_strInstitutionCode, 
                l_strBranchCode, l_strAccountCode, 
                l_strRequestNumber, l_strMrgTrnRequestNumber);
        
        //３）補助口座オブジェクトを取得する。 
        //３－１） 
        //拡張アカウントマネージャ.get顧客()をコールする。 
        WEB3GentradeAccountManager l_accMgr = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
        MainAccount l_mainAccount = null; 
        
        //補助口座オブジェクトを取得する。 
        l_mainAccount = l_accMgr.getMainAccount(
            l_strInstitutionCode, l_strBranchCode, l_strAccountCode);
        
        //３－２） 
        //顧客.getSubAccount()をコールする。 
        //[引数] 
        //補助口座タイプ： ”預り金口座” 

        //補助口座
        SubAccount l_subAccount = null;
        try
        {
            //補助口座オブジェクトを取得する。 
            l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        }
        catch (NotFoundException l_ex)
        {
            log.error("補助口座オブジェクトを取得", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //４）余力の更新を行う。 
        //余力取引サービスImpl.余力再計算()をコールする。 
        //[引数] 
        //補助口座： ２－２）で取得した補助口座オブジェクト 
        WEB3TPTradingPowerService l_service =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);

        WEB3GentradeSubAccount l_gentradeSubAccount =
            (WEB3GentradeSubAccount)l_subAccount;

        l_service.reCalcTradingPower(l_gentradeSubAccount);        
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (insert説明不要承諾履歴) <BR>
     * 口座開設申込時に「説明不要」を選択した場合、<BR> 
     * FX説明不要承諾履歴管理テーブル.に行のinsertを行う。<BR> 
     * <BR>
     * １）証券会社コード、部店コード、顧客コードをキーとして<BR> 
     * 　@ FX説明不要承諾履歴管理テーブルより履歴番号の最大値を取得する。<BR> 
     * <BR>
     * ２）取得した履歴番号に１プラスした値を<BR> 
     * 　@ FX説明不要承諾履歴管理.履歴番号にセットする。<BR> 
     * 　@ １）にて取得できない場合は、１をセットする。 <BR>
     * <BR>
     * それ以外の挿入する行の内容に関しては、下記を参照。<BR> 
     * 【ｘTrade】補足資料.DB更新  <BR>
     * 「FX共通　@FX説明不要承諾履歴管理テーブル.xls<BR>
     * @@param l_strInstitutionCode - 証券会社コード<BR>
     * @@param l_strBranchCode - 部店コード<BR>
     * @@param l_strAccountCode - 顧客コード<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41D0F49D02B3
     */
    public void insertUnnecessaryExplanation(
        String l_strInstitutionCode, 
        String l_strBranchCode, 
        String l_strAccountCode) 
            throws WEB3BaseException
    {
        String STR_METHOD_NAME = "insertUnnecessaryExplanation(String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //１）証券会社コード、部店コード、顧客コードをキーとして 
        //FX説明不要承諾履歴管理テーブルより履歴番号の最大値を取得する。
        try
        {
            log.debug("顧客コード = " + l_strAccountCode);
            
            String l_strWheres  = "institution_code = ? and branch_code = ? " +
                "and account_code = ? ";
            Object[] l_objBindVal = {
                l_strInstitutionCode, 
                l_strBranchCode, 
                l_strAccountCode, 
                };
            
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            List l_lisFxUnnecessaryExplanationRows = 
                l_processor.doFindAllQuery(
                    FxUnnecessaryExplanationRow.TYPE,
                    l_strWheres,
                    " fx_serial_no desc",
                    null,
                    l_objBindVal); 
                        
            //２）取得した履歴番号に１プラスした値を 
            //FX説明不要承諾履歴管理.履歴番号にセットする。 
            //１）にて取得できない場合は、１をセットする。             
            //それ以外の挿入する行の内容に関しては、下記を参照。 
            //【ｘTrade】補足資料.DB更新  
            //「FX共通　@FX説明不要承諾履歴管理テーブル.xls」    
            
            //最新の履歴番号
            int l_intLastNumber = 1;
            if (l_lisFxUnnecessaryExplanationRows != null &&  
                !l_lisFxUnnecessaryExplanationRows.isEmpty())
            {
                FxUnnecessaryExplanationRow l_fxUnnecessaryExplanationRow = 
                    (FxUnnecessaryExplanationRow)l_lisFxUnnecessaryExplanationRows.get(0);
                
                l_intLastNumber = l_fxUnnecessaryExplanationRow.getFxSerialNo() + 1;
            }
            
            //FX説明不要承諾履歴管理テーブル
            FxUnnecessaryExplanationParams l_fxUnnecessaryExplanationParams = 
                new FxUnnecessaryExplanationParams(); 
            //証券会社コード: 引数.証券会社コード
            l_fxUnnecessaryExplanationParams.setInstitutionCode(l_strInstitutionCode);
            //部店コード: 引数.部店コード
            l_fxUnnecessaryExplanationParams.setBranchCode(l_strBranchCode);
            //顧客コード: 引数.顧客コード
            l_fxUnnecessaryExplanationParams.setAccountCode(l_strAccountCode);
            //履歴番号: 証券会社コード、部店コード、顧客コードをキーに取得した最新履歴番号＋１
            l_fxUnnecessaryExplanationParams.setFxSerialNo(l_intLastNumber);
            //有効フラグ: 0：有効
            l_fxUnnecessaryExplanationParams.setFxValidFlag(WEB3EffectiveDivDef.EFFECTIVE);
            //更新者コード: null
            l_fxUnnecessaryExplanationParams.setLastUpdater(null);
            //作成日付: システムタイムスタンプ
            l_fxUnnecessaryExplanationParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            //更新日付: システムタイムスタンプ
            l_fxUnnecessaryExplanationParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            l_processor.doInsertQuery(l_fxUnnecessaryExplanationParams);
        }
        catch (DataQueryException l_ex)
        {
            log.debug("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.debug("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }        
        log.exiting(STR_METHOD_NAME);
        return; 
    }
    
    /**
     * (validateFXドキュメント閲覧履歴) <BR>
     * 電子鳩システムへ接続し、<BR>
     * 該当顧客の閲覧履歴が存在するかのチェックを行う。<BR> 
     * <BR>
     * １）目論見書閲覧チェック<BR> 
     * 　@１-１）ArrayListの作成 <BR>
     * <BR>
     *  １-２）識別コードの要素数部、１－３）～１－５）の処理を実施<BR> 
     * <BR>
     *  １-３）電子鳩システム接続サービスImpl.validate目論見書閲覧を実施<BR> 
     *   [引数] <BR>
     *    種別コード：引数.種別コード<BR> 
     *    識別コード：引数.識別コード[index]<BR> 
     * <BR>
     *  １-４）取得した目論見書閲覧チェック結果.チェック結果が「1： 閲覧未済」の<BR> 
     *   場合、ArrayListに識別コードを追加。 <BR>
     * <BR>
     * ２）目論見書閲覧チェック結果の確認<BR> 
     * 　@２-１）作成したArrayList（１-１で作成）の要素数が「０」の場合、「NULL」を<BR> 
     *   返却する。 <BR>
     * <BR>
     *   ２-２）作成したArrayList（１-１で作成）の要素数が「０」でない場合、<BR> 
     *   配列に変換した識別コードを返却する。 <BR>
     * 
     * @@param l_strTypeCode - 種別コード
     * @@param l_strRequestCode - 識別コード
     * @@return String[]
     * @@throws WEB3BaseException
     * @@roseuid 41D0F49D02B3
     */
    public String[] validateFxDocReadHistory(
        String l_strTypeCode, 
        String[] l_strRequestCodes) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateFxDocReadHistroy(String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strRequestCodes == null)
        {
            log.debug("識別コード = " + l_strRequestCodes);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）目論見書閲覧チェック 
        //１-１）ArrayListの作成 
        List l_lisRequestCodes = new ArrayList();
        
        // １-２）識別コードの要素数部、１－３）～１－４）の処理を実施
        for (int i = 0; i < l_strRequestCodes.length; i++)
        {
            //１-３）電子鳩システム接続サービスImpl.validate目論見書閲覧を実施 
            // [引数] 
            //  種別コード：引数.種別コード 
            //  識別コード：引数.識別コード[index] 
            WEB3GentradeBatoClientService l_gentradeBatoClientService = 
                (WEB3GentradeBatoClientService)Services.getService(WEB3GentradeBatoClientService.class);
            
            WEB3GentradeProspectusResult l_prospectusResult = 
                l_gentradeBatoClientService.validateProspectus(
                    l_strTypeCode,
                    l_strRequestCodes[i]);
            
            // １-４）取得した目論見書閲覧チェック結果.チェック結果が「1： 閲覧未済」の 
            //場合、ArrayListに識別コードを追加。
            if (WEB3GentradeBatoCheckResultDef.UNINSPECTION.equals(l_prospectusResult.checkResult))
            {
                l_lisRequestCodes.add(l_strRequestCodes[i]);
            }            
        }
        //２）目論見書閲覧チェック結果の確認 
        //　@２-１）作成したArrayList（１-１で作成）の要素数が「０」の場合、「NULL」を 
        //返却する。 
        if (l_lisRequestCodes.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            String[] l_strResult = new String[l_lisRequestCodes.size()];
            l_lisRequestCodes.toArray(l_strResult);
            return l_strResult;
        }        
    }
    
    /**
     * (get会社別FXシステム条件)<BR>
     * 引数の証券会社コード、部店コード、FXシステムコードに一致する<BR>
     * 会社別FXシステム条件Paramsを返却する。<BR> 
     * <BR>
     * １）引数チェック <BR>
     * 　@引数．FXシステムコードがnullかどうかチェックする <BR>
     * <BR>
     * ２－１）DB検索<BR> 
     * 　@引数．FXシステムコードがnullでない場合、<BR>
     * 　@会社別FXシステム条件テーブル(comp_fx_condition)を以下の条件で検索する。<BR> 
     * 　@ただし、複数件取得の場合は例外をスローする。 <BR>
     * <BR>
     * class: WEB3SystemLayerException <BR>
     * tag: SYSTEM_ERROR_80006 <BR>
     * <BR>
     * 　@[条件] <BR>
     * 　@証券会社コード = 引数.証券会社コード <BR>
     * 　@部店コード = 引数.部店コード <BR>
     *   FXシステムコード = 引数.FXシステムコード<BR> 
     * <BR>
     * ２－２）DB検索 <BR>
     * 　@引数．FXシステムコードがnullの場合、<BR>
     * 　@会社別FXシステム条件テーブル(comp_fx_condition)を以下の条件で検索する。<BR> 
     * 　@ただし、複数件取得の場合は例外をスローする。 <BR>
     * <BR>
     * class: WEB3SystemLayerException <BR>
     * tag: SYSTEM_ERROR_80006 <BR>
     * <BR>
     * 　@[条件] <BR>
     * 　@証券会社コード = 引数.証券会社コード<BR> 
     * 　@部店コード = 引数.部店コード <BR>
     * <BR>
     * ３）検索結果の会社別FXシステム条件Paramsを返却する。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)
     * @@param l_strBranchCode - (部店コード)
     * @@param l_strFxSystemCode - (FXシステムコード)
     * @@return webbroker3.aio.data.CompFxConditionParams
     * @@throws WEB3BaseException 
     * @@throws NotFoundException 
     */
    public CompFxConditionParams getCompFxCondition(
        String l_strInstitutionCode, String l_strBranchCode, String l_strFxSystemCode) 
            throws WEB3BaseException, NotFoundException
    {
        String STR_METHOD_NAME = "getCompFxCondition( " +
        "String l_strInstitutionCode, String l_strBranchCode, String l_strFxSystemCode)";
        log.entering(STR_METHOD_NAME);
        
        //２－１）DB検索 
        //　@引数．FXシステムコードがnullでない場合、会社別FXシステム条件テーブル(comp_fx_condition)を以下の条件で検索する。 
        //　@ただし、複数件取得の場合は例外をスローする。 
        //
        //　@[条件] 
        //　@証券会社コード = 引数.証券会社コード 
        //　@部店コード = 引数.部店コード 
        //  FXシステムコード = 引数.FXシステムコード 
        List l_lisRows = null;
        if (l_strFxSystemCode != null) 
        {
            String l_strWhere = "institution_code = ? and branch_code = ? and fx_system_code = ?";        
            Object[] l_objValue = {l_strInstitutionCode, l_strBranchCode, l_strFxSystemCode};
            try 
            {
                l_lisRows =
                    Processors.getDefaultProcessor().doFindAllQuery(
                        CompFxConditionRow.TYPE,
                        l_strWhere,
                        null,
                        l_objValue);
            } 
            catch (DataException l_ex) 
            {
                log.debug("DBへのアクセスに失敗しました。", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            if (l_lisRows.size() == 0)
            {
                throw new NotFoundException("会社別FXシステム条件テーブルが取得できませんでした");
            }
            
            //複数件取得の場合は例外をスローする。
            if (l_lisRows.size() > 1)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "会社別FXシステム条件テーブルに複数件");
            }
        }        
        //２－２）DB検索 
        //　@引数．FXシステムコードがnullの場合、会社別FXシステム条件テーブル(comp_fx_condition)を以下の条件で検索する。 
        //　@ただし、複数件取得の場合は例外をスローする。 
        //
        //　@[条件] 
        //　@証券会社コード = 引数.証券会社コード 
        //　@部店コード = 引数.部店コード 
        //
        //３）検索結果の会社別FXシステム条件Paramsを返却する。
        else 
        {
            String l_strWhere = "institution_code = ? and branch_code = ? ";        
            Object[] l_objValue = {l_strInstitutionCode, l_strBranchCode};
            try 
            {
                l_lisRows =
                    Processors.getDefaultProcessor().doFindAllQuery(
                        CompFxConditionRow.TYPE,
                        l_strWhere,
                        null,
                        l_objValue);
            } 
            catch (DataException l_ex) 
            {
                log.debug("DBへのアクセスに失敗しました。", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            if (l_lisRows.size() == 0)
            {
                throw new NotFoundException("会社別FXシステム条件テーブルが取得できませんでした");
            }
            
            //複数件取得の場合は例外をスローする。
            if (l_lisRows.size() > 1)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "会社別FXシステム条件テーブルに複数件");
            }
        } 
        
        //３）検索結果の会社別FXシステム条件Paramsを返却する。
        CompFxConditionParams l_compFxConditionParams = 
            new CompFxConditionParams((CompFxConditionRow)l_lisRows.get(0));
        
        log.exiting(STR_METHOD_NAME);
        return l_compFxConditionParams;
    }

    /**
     * (getFX振替条件マスタ)<BR>
     * FX振替条件マスタParams取得し、返却する。<BR>
     * <BR>
     * １）DB検索<BR>
     * 　@以下の条件で、FX振替条件マスタ(fx_transfer_master)を検索する。<BR>
     * 　@【条件】<BR>
     * 　@FXシステム条件ID　@= 引数.FXシステム条件ID<BR>
     * 　@振替区分 = 引数.振替区分<BR>
     * <BR>
     * 検索結果が0件の場合、例外をスローする。<BR>
     * <BR>
     * ２）検索結果を返却する。<BR>
     * <BR>
     * @@param l_lngFxSystemId - (FXシステム条件ID)<BR>
     * FXシステム条件ID<BR>
     * @@param l_strTransferDiv - (振替区分)<BR>
     * 振替区分<BR>
     * @@return FxTransferMasterParams
     * @@throws WEB3BaseException
     */
    public FxTransferMasterParams getFxTransferMasterParams(
        long l_lngFxSystemId,
        String l_strTransferDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getFxTransferMasterParams(long, String)";
        log.entering(STR_METHOD_NAME);

        //DB検索
        //以下の条件で、FX振替条件マスタ(fx_transfer_master) を検索する。
        //【条件】
        //　@FXシステム条件ID　@= 引数.FXシステム条件ID
        //　@振替区分 = 引数.振替区分
        FxTransferMasterRow l_fxTransferMasterRow = null;
        try
        {
            l_fxTransferMasterRow = FxTransferMasterDao.findRowByPk(
                l_lngFxSystemId,
                l_strTransferDiv);
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
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

        //検索結果を返却する
        FxTransferMasterParams l_fxTransferMasterParams =
            new FxTransferMasterParams(l_fxTransferMasterRow);
        log.exiting(STR_METHOD_NAME);
        return l_fxTransferMasterParams;
    }

    /**
     * (get受渡日)<BR>
     * 以下の条件にて受渡日を取得し、返却する。<BR>
     * <BR>
     * 　@１）FX振替条件マスタ.受渡日設定区分が数字の場合<BR>
     * 　@　@「引数.発注日」に「引数.受渡日設定区分」の値を足した営業日を返却する。<BR>
     * <BR>
     * 　@２）FX振替条件マスタ.受渡日設定区分が数字以外の場合<BR>
     * 　@　@２－１）受渡日設定区分がＡの場合<BR>
     * 　@　@　@－引数.補助口座.getMainAccount.MRF口座開設区分 = ”0：DEFAULT(口座なし)”の場合<BR>
     * 　@　@　@　@　@　@⇒「引数.発注日」を返却する。<BR>
     * 　@　@　@－上記以外の場合<BR>
     * 　@　@　@　@　@　@⇒「引数.発注日の翌営業日」を返却する。<BR>
     * 　@　@２－２）上記以外の場合<BR>
     * 　@　@　@　@　@　@例外をthrowする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@: BUSINESS_ERROR_02865<BR>
     * <BR>
     * @@param l_datOrderBizDate - (発注日)<BR>
     * 発注日<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_strDeliveryDateSetDiv - (受渡日設定区分)<BR>
     * 受渡日設定区分<BR>
     * @@return Date
     * @@throws WEB3BaseException
     */
    public Date getDeliveryDate(
        Date l_datOrderBizDate,
        SubAccount l_subAccount,
        String l_strDeliveryDateSetDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDeliveryDate(Date, SubAccount, String)";
        log.entering(STR_METHOD_NAME);

        Date l_datBizDate = null;
        WEB3GentradeBizDate l_gentradeBizDate =
            new WEB3GentradeBizDate(
                new Timestamp(l_datOrderBizDate.getTime()));
        //FX振替条件マスタ.受渡日設定区分が数字の場合
        if (WEB3StringTypeUtility.isDigit(l_strDeliveryDateSetDiv))
        {
            //「引数.発注日」に「引数.受渡日設定区分」の値を足した営業日を返却する
            l_datBizDate =
                l_gentradeBizDate.roll(Integer.parseInt(l_strDeliveryDateSetDiv));
        }
        else
        {
            //FX振替条件マスタ.受渡日設定区分が数字以外の場合
            //受渡日設定区分がＡの場合
            if (WEB3DeliveryDateDivDef.SPECIAL_SETTING.equals(l_strDeliveryDateSetDiv))
            {
                //引数.補助口座.getMainAccount.MRF口座開設区分
                // = ”0：DEFAULT(口座なし)”の場合
                //      ⇒「引数.発注日」を返却する。
                MainAccount l_mainAccount =
                    l_subAccount.getMainAccount();
                MainAccountRow l_mainAccountRow =
                    (MainAccountRow)l_mainAccount.getDataSourceObject();
                if (WEB3MrfAccOpenDivDef.DEFAULT.equals(
                    l_mainAccountRow.getMrfAccOpenDiv()))
                {
                    l_datBizDate = l_datOrderBizDate;
                }
                else
                {
                    //上記以外の場合
                    //   ⇒「引数.発注日の翌営業日」を返却する。
                    l_datBizDate =
                        l_gentradeBizDate.roll(1);
                }
            }
            else
            {
                //上記以外の場合、例外をthrowする。
                log.debug("受渡日が不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02865,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "受渡日が不正です。");
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_datBizDate;
    }

    /**
     * (updateFX口座開設区分更新者コード)
     * 顧客マスターテーブルのFX口座開設区分更新者コードをupdateする。<BR>
     * <BR>
     * １）顧客の取得 <BR>
     * 　@拡張アカウントマネージャ.get顧客()にて顧客オブジェクトを取得する。<BR>
     * 　@<BR>
     * 　@[引数の設定]<BR>
     * 　@証券会社コード：　@引数.証券会社コード<BR>
     * 　@部店コード：　@引数.部店コード<BR>
     * 　@口座コード：　@引数.顧客コード<BR>
     * <BR>
     * 　@取得した顧客.getDataSourceObject()により顧客Paramsを取得する。<BR>
     * <BR>
     * ３）生成したインスタンスに、更新後のデータをセットする。<BR>
     * <BR>
     * 　@更新する行の内容は下記を参照。<BR>
     * <BR>
     * 　@【ｘTrade】補足資料.DB更新 <BR>
     * 　@「FX口座開設共通_顧客マスター.xls」内ワークシート<BR>
     * 　@「(FX口座開設共通)顧客マスター_DB更新仕様_2」を参照<BR>
     * <BR>
     * ４）顧客のupdate<BR>
     *  WEB3DataAccessUtility.updateRow()メソッドをコールする。<BR>
     * <BR>
     * 　@[updateRow()にセットするパラメータ]<BR>
     * 　@　@l_row：　@３）にて更新後のデータをセットしたインスタンス<BR>
     * <BR>
     * @@throws WEB3BaseException 
     */
    public void updateFXAccountOpenDivUpdaterCode(
        String l_strInstitutionCode, String l_strBranchCode, 
        String l_strAccountCode, String l_strUpdaterCode) 
            throws WEB3BaseException 
	{
		String STR_METHOD_NAME =
            "updateFXAccountOpenDivLastUpdater(String l_strInstitutionCode," +
            "String l_strBranchCode, String l_strAccountCode, " +
            "String l_strUpdaterCode)";
        log.entering(STR_METHOD_NAME);
        try
        {
            //１）顧客の取得 
            //　@拡張アカウントマネージャ.get顧客()にて顧客オブジェクトを取得する。 
            //　@[引数の設定] 
            //　@証券会社コード：　@引数.証券会社コード 
            //　@部店コード：　@引数.部店コード 
            //　@口座コード：　@引数.顧客コード 
            //　@取得した顧客.getDataSourceObject()により顧客Paramsを取得する。
            WEB3GentradeAccountManager l_web3GentradeAccountManager =
                (WEB3GentradeAccountManager)GtlUtils.getAccountManager();
            
            MainAccount l_mainAccount = 
                l_web3GentradeAccountManager.getMainAccount(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
            
            MainAccountRow l_mainAccountRow = 
                (MainAccountRow)l_mainAccount.getDataSourceObject();
            //３）生成したインスタンスに、更新後のデータをセットする。 
            //　@更新する行の内容は下記を参照。 
            //　@【ｘTrade】補足資料.DB更新 
            //　@「FX口座開設共通_顧客マスター.xls」内ワークシート「(FX口座開設共通)顧客マスター_DB更新仕様_2」を参照
            MainAccountParams l_mainAccountParams = new MainAccountParams(l_mainAccountRow);
            
            //3.1) ＦＸ口座開設区分更新者コード
            //説明 : 引数.更新者コード
            l_mainAccountParams.setFxAccOpenDivLastUpdater(l_strUpdaterCode);

            //3.2) ＦＸ口座開設区分更新日時
            //説明 : 現在時刻
            l_mainAccountParams.setFxAccOpenUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //3.3) 更新日時
            l_mainAccountParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //４）顧客のupdate 
            //WEB3DataAccessUtility.updateRow()メソッドをコールする。 
            //　@[updateRow()にセットするパラメータ] 
            //　@　@l_row：　@３）にて更新後のデータをセットしたインスタンス
            WEB3DataAccessUtility.updateRow(l_mainAccountParams);
        }
        catch (DataException l_ex)
        {
            log.debug("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);		
	}

    /**
     * (getアップロード最新履歴)<BR>
     * 当該アップロードファ@イルに関連するアップロード最新履歴を取得する。  <BR>
     * <BR>
     * 以下の条件で「（管理者共通）アップロードテーブル」を検索し、  <BR>
     * 取得した行オブジェクトを返却する。  <BR>
     * レコードがない場合はnullを返却する。  <BR>
     * <BR>
     * [条件]  <BR>
     * （管理者共通）アップロードテーブル.証券会社コード = 引数.証券会社コード <BR>
     * （管理者共通）アップロードテーブル.アップロードファ@イルＩＤ = <BR>
     * 　@　@引数.アップロードファ@イルＩＤ <BR>
     * （管理者共通）アップロードテーブル.銘柄タイプ = 引数.銘柄タイプ <BR>
     * （管理者共通）アップロードテーブル.データキー = 引数.データキー  <BR>
     * （管理者共通）アップロードテーブル.備考１ != "中止"  <BR>
     * ※備考１を比較する時、項目がNULLの場合、文字列で"NULL"と  <BR>
     * 　@　@置き換えてから比較する。(SQL文：nvl(note1,'NULL') != '中止')  <BR>
     * <BR>
     * [取得順]  <BR>
     * アップロード開始日時　@降順（：desc）<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)
     * @@param l_strUploadFileID - (アップロードファ@イルＩＤ)
     * @@param l_strProductType - (銘柄タイプ)
     * @@param l_lngDataKey - (データキー)
     * @@return Object
     * @@throws WEB3BaseException 
     */
    public Object getUploadNewHistory(String l_strInstitutionCode, 
        String l_strUploadFileID, String l_strProductType, long l_lngDataKey) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getUploadNewHistory(String, String, String, long)";
        log.entering(STR_METHOD_NAME );
        List l_lisRecords = null;
        long l_lngProductType = Integer.parseInt(l_strProductType);
        Long l_lngProductType1 = new Long(l_lngProductType);
            
        Long l_lngUpKey = new Long(l_lngDataKey);  
                  
        //「（管理者共通）アップロードテーブル」を検索
        try
        {

            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");            
            l_sbWhere.append(" and upload_file_id = ? ");         
            l_sbWhere.append(" and product_type = ? ");           
            l_sbWhere.append(" and upload_key = ? ");
            l_sbWhere.append(" and nvl(note1,'NULL') != ? ");
                
            //データキー
            Object[] l_objAdministratorUploadWhere = {l_strInstitutionCode, l_strUploadFileID, l_lngProductType1, l_lngUpKey, WEB3AdminFXUploadNoteOneDef.UPLOAD_TERMINATE}; 

            QueryProcessor l_queryProcessor;
                l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords =
                l_queryProcessor.doFindAllQuery(
                    AdministratorUploadRow.TYPE,
                    l_sbWhere.toString(),
                    "upload_start_timestamp desc",
                    null,
                    l_objAdministratorUploadWhere);
     
        }
        catch(DataFindException l_dfex)
        {
            log.debug(l_dfex.getMessage(), l_dfex);
            return null;
        }
        catch (DataNetworkException l_nex) 
        {
            log.error(l_nex.getMessage(), l_nex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nex.getMessage(),
                l_nex);
        }
        catch (DataQueryException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }
        
        if (l_lisRecords == null || l_lisRecords.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        //取得した行オブジェクトを返却する。
        AdministratorUploadRow l_uploadRow = 
            (AdministratorUploadRow)l_lisRecords.get(0); 
            
        log.exiting(STR_METHOD_NAME);
        return l_uploadRow;
        
    }

    /**
     * (createFX口座情報一覧)<BR>
     * 引数の条件に該当するFX口座情報の一覧を作成する。<BR>
     * <BR>
     * １）引数チェック <BR>
     * 　@引数.FXシステムコードがnullかどうかチェックする<BR>
     * <BR>
     * ２）１）の結果により、下記のように分岐し、処理を行う。<BR>
     * ２－１）引数.FXシステムコードがnullの場合、<BR>
     * 　@　@　@　@this.getFX口座番号()メソッド（引数3つ）をコールする。<BR>
     * <BR>
     * 　@[getFX口座番号()にセットするパラメータ]<BR>
     * 　@　@証券会社コード：　@引数.証券会社コード<BR>
     * 　@　@部店コード：　@引数.部店コード<BR>
     * 　@　@顧客コード：　@引数.顧客コード<BR>
     * <BR>
     * 　@nullが返却された場合、nullを返却する。<BR>
     * <BR>
     * ２－２）引数.FXシステムコードがnullでない場合、<BR>
     * 　@　@　@　@this.getFX口座番号()メソッド（引数4つ）をコールする。<BR>
     * <BR>
     * 　@[getFX口座番号()にセットするパラメータ]<BR>
     * 　@　@証券会社コード：　@引数.証券会社コード<BR>
     * 　@　@部店コード：　@引数.部店コード<BR>
     * 　@　@顧客コード：　@引数.顧客コード<BR>
     * 　@　@FXシステムコード：　@引数.FXシステムコード<BR>
     * <BR>
     * 　@nullが返却された場合、nullを返却する。<BR>
     * <BR>
     * ３）ArrayListを生成する。<BR>
     * <BR>
     * ４）２）の戻り値の要素数分、以下の処理を繰り返す。<BR>
     * 　@４－１）FX口座情報インスタンスを生成する。<BR>
     * <BR>
     * 　@４－２）生成したインスタンスに以下のプロパティをセットする。<BR>
     * 　@　@コース区分 = FX口座番号Params.FXコース区分<BR>
     * 　@　@口座番号 = FX口座番号Params.FX口座番号<BR>
     * <BR>
     *　@４－３）ArrayListにプロパティセットしたインスタンスを追加する。<BR>
     * <BR>
     * ５）生成したArrayList.toArray()の戻り値を返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)
     * @@param l_strBranchCode - (部店コード)
     * @@param l_strAccountCode - (顧客コード)
     * @@param l_strFxSystemCode - (FXシステムコード)
     * @@return WEB3FXAccInformationUnit[]
     * @@throws WEB3BaseException 
     */
    public WEB3FXAccInformationUnit[] createFXAccInformationUnits(
        String l_strInstitutionCode, 
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strFxSystemCode) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "createFXAccInformationUnits(String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //１）引数チェック  
        //引数.FXシステムコードがnullかどうかチェックする 
        FxAccountCodeParams[] l_params = null; 
        
        //２－１）引数.FXシステムコードがnullの場合、 
        //this.getFX口座番号()メソッド（引数3つ）をコールする。
        if (l_strFxSystemCode == null)
        {
            l_params = this.getFXAccountCodes(
                l_strInstitutionCode, 
                l_strBranchCode, 
                l_strAccountCode);
        }
        //２－２）引数.FXシステムコードがnullでない場合、 
        //this.getFX口座番号()メソッド（引数4つ）をコールする。 
        else
        {
            l_params = this.getFXAccountCodes(
                l_strInstitutionCode, 
                l_strBranchCode, 
                l_strAccountCode,
                l_strFxSystemCode);
        }
        
        //nullが返却された場合、nullを返却する。 
        if (l_params == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        //３）ArrayListを生成する。
        List l_lisUnits = new ArrayList();
        
        //４）２）の戻り値の要素数分、以下の処理を繰り返す。 
        int l_intCnt = l_params.length;
        
        for (int i= 0; i < l_intCnt; i++)
        {
            //４－１）FX口座情報インスタンスを生成する。
            WEB3FXAccInformationUnit l_unit = new WEB3FXAccInformationUnit();
            
            //４－２）生成したインスタンスに以下のプロパティをセットする。
            //コース区分 = FX口座番号Params.FXコース区分
            FxAccountCodeParams l_param = l_params[i];
            l_unit.fxCourseDiv = l_param.getFxCourseDiv();
            
            //口座番号 = FX口座番号Params.FX口座番号
            l_unit.fxAccountCode = l_param.getFxAccountCode();
            
            //４－３）ArrayListにプロパティセットしたインスタンスを追加する。 
            l_lisUnits.add(l_unit);
        }
        
        //５）生成したArrayList.toArray()の戻り値を返却する。
        WEB3FXAccInformationUnit[] l_units = 
            new WEB3FXAccInformationUnit[l_lisUnits.size()];
        l_lisUnits.toArray(l_units);
        
        log.exiting(STR_METHOD_NAME);
        return l_units;                
    }
    
    /**
     * (getFX口座番号)<BR>
     * 引数の条件に該当するFX口座番号の一覧を取得する。<BR>
     * <BR>
     * １）FXシステムコード取得<BR>
     * <BR>
     * 　@FXシステムコード = 引数.FXシステムコード<BR>
     * <BR>
     * ２）DB検索<BR>
     * 　@FX口座番号テーブル(fx_account_code)を<BR>
     * 　@以下の条件で検索する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@証券会社コード = 引数.証券会社コード<BR>
     * 　@部店コード = 引数.部店コード<BR>
     * 　@FXシステムコード = １）にて取得したFXシステムコード<BR>
     * 　@顧客コード = 引数.顧客コード<BR>
     * <BR>
     * 　@※　@顧客コード<BR>
     * 　@　@（引数.顧客コード.length() == 6）の場合は、最初の6byteのみ比較する。)<BR>
     * <BR>
     * ３）検索結果のFX口座番号Paramsの配列をFXコース区分の昇順で<BR>
     * 　@ソートし、返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)
     * @@param l_strBranchCode - (部店コード)
     * @@param l_strAccountCode - (顧客コード)
     * @@param l_strFxSystemCode - (FXシステムコード)
     * @@return FxAccountCodeParams[]
     * @@throws WEB3BaseException 
     */
    public FxAccountCodeParams[] getFXAccountCodes(
        String l_strInstitutionCode, 
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strFxSystemCode) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getFXAccountCodes(String, String, String, String)";
        log.entering(STR_METHOD_NAME);
         
        if (l_strAccountCode == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        
        //１）FXシステムコード取得 
        //　@FXシステムコード = 引数.FXシステムコード 
        
        //２）DB検索 
        //　@FX口座番号テーブル(fx_account_code)を 
        //　@以下の条件で検索する。                
        //　@[条件] 
        //　@証券会社コード = 引数.証券会社コード 
        //　@部店コード = 引数.部店コード 
        //　@FXシステムコード = １）にて取得したFXシステムコード 
        //　@顧客コード = 引数.顧客コード 
        //　@※　@顧客コード 
        //　@　@（引数.顧客コード.length() == 6）の場合は、最初の6byteのみ比較する。) 
        String l_strWhere = null;
        if (l_strAccountCode.length() == 6)
        {
            l_strWhere = " institution_code = ? and branch_code = ?" +
                " and fx_system_code = ? and substr(account_code ,0 ,6) = ? ";
        }
        else
        {
            l_strWhere = " institution_code = ? and branch_code = ?" +
                " and fx_system_code = ? and account_code = ? ";
        }
        
        Object[] l_objValues = {l_strInstitutionCode, l_strBranchCode, l_strFxSystemCode,
            l_strAccountCode};

        //３）検索結果のFX口座番号Paramsの配列をFXコース区分の昇順で 
        //　@ソートし、返却する。
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    FxAccountCodeRow.TYPE,
                    l_strWhere,
                    "fx_course_div asc",
                    null,
                    l_objValues);
        }
        catch (DataException l_ex)
        {
            log.error("__DataException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        FxAccountCodeParams[] l_fxAccountCodeParams = null;
        if(l_lisRows != null && l_lisRows.size() != 0)
        {
            l_fxAccountCodeParams = 
                new FxAccountCodeParams[l_lisRows.size()]; 
            l_lisRows.toArray(l_fxAccountCodeParams);            
        }

        //３）検索結果のFX口座番号Paramsの配列をFXコース区分の昇順で 
        //　@ソートし、返却する。         
        log.exiting(STR_METHOD_NAME);
        return l_fxAccountCodeParams;
    }
    
    /**
     * (get入出金額区分) <BR>
     * <BR>
     * GFT結果通知電文明細.入出金額と <BR>
     * GFT結果通知電文明細.入出金額2をチェックし、 <BR>
     * 入出金区分を返却する。 <BR>
     * <BR>
     * １）　@（GFT結果通知電文明細.入出金額　@!=　@null　@and <BR>
     * 　@　@　@GFT結果通知電文明細.入出金額　@!=　@0)　@&& <BR>
     * 　@　@　@（GFT結果通知電文明細.入出金額2　@!=　@null　@and <BR>
     * 　@　@　@GFT結果通知電文明細.入出金額2　@!=　@0)の場合、 <BR>
     * 　@　@　@入出金額区分　@=　@3：入出金額と入出金額2の両方に設定されている場合を返却する。<BR>
     * <BR>
     * ２）　@GFT結果通知電文明細.入出金額　@!=　@null　@and <BR>
     * 　@　@　@GFT結果通知電文明細.入出金額　@!=　@0　@の場合、 <BR>
     * 　@　@　@入出金額区分　@=　@1：入出金額に設定されている場合　@を返却する。 <BR>
     * <BR>
     * ３）　@GFT結果通知電文明細.入出金額2　@!=　@null　@and <BR>
     * 　@　@　@GFT結果通知電文明細.入出金額2　@!=　@0　@の場合、 <BR>
     * 　@　@　@入出金額区分　@=　@2：入出金額2に設定されている場合　@を返却する。 <BR>
     * <BR>
     * @@param l_fxGftResultNoticeTelegramUnit - (GFT結果通知電文明細)<BR>
     * GFT結果通知電文明細
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 41D0F49D02B3
     */
    public String getCashInOutAmountDiv(
        WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getCashInOutAmountDiv(WEB3FXGftResultNoticeTelegramUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_fxGftResultNoticeTelegramUnit == null)
        {
            log.debug("パラメータ値不正。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        String l_strCashInOutAmountDiv = null;
        
        //１）　@（GFT結果通知電文明細.入出金額　@!=　@null　@and 
        //　@     GFT結果通知電文明細.入出金額　@!=　@0)　@&& 
        //　@    （GFT結果通知電文明細.入出金額2　@!=　@null　@and 
        //　@     GFT結果通知電文明細.入出金額2　@!=　@0)の場合、 
        if ((l_fxGftResultNoticeTelegramUnit.cashinoutAmt != null &&
            Double.parseDouble(l_fxGftResultNoticeTelegramUnit.cashinoutAmt) != 0) &&
            (l_fxGftResultNoticeTelegramUnit.cashinoutAmt2 != null &&
                Double.parseDouble(l_fxGftResultNoticeTelegramUnit.cashinoutAmt2) != 0))
        {
            //　@入出金額区分　@=　@3：入出金額と入出金額2の両方に設定されている場合　@を返却する。 
            l_strCashInOutAmountDiv = WEB3AioCashInOutAmountDivDef.CASHIN_OUT_AMOUNT_AND_AMOUNT2;
            
            log.exiting(STR_METHOD_NAME);
            return l_strCashInOutAmountDiv;
        }
        
        //２）　@GFT結果通知電文明細.入出金額　@!=　@null　@and 
        //　@    GFT結果通知電文明細.入出金額　@!=　@0　@の場合、 
        if (l_fxGftResultNoticeTelegramUnit.cashinoutAmt != null &&
            Double.parseDouble(l_fxGftResultNoticeTelegramUnit.cashinoutAmt) != 0)
        {
            //　@    入出金額区分　@=　@1：入出金額に設定されている場合　@を返却する。 
            l_strCashInOutAmountDiv = WEB3AioCashInOutAmountDivDef.CASHIN_OUT_AMOUNT;
            
            log.exiting(STR_METHOD_NAME);
            return l_strCashInOutAmountDiv;
        }
        //３）　@GFT結果通知電文明細.入出金額2　@!=　@null　@and 
        //　@    GFT結果通知電文明細.入出金額2　@!=　@0　@の場合、 
        if (l_fxGftResultNoticeTelegramUnit.cashinoutAmt2 != null &&
            Double.parseDouble(l_fxGftResultNoticeTelegramUnit.cashinoutAmt2) != 0)
        {
            //　@    入出金額区分　@=　@2：入出金額2に設定されている場合　@を返却する。
            l_strCashInOutAmountDiv = WEB3AioCashInOutAmountDivDef.CASHIN_OUT_AMOUNT2;
            
            log.exiting(STR_METHOD_NAME);
            return l_strCashInOutAmountDiv;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strCashInOutAmountDiv;
    }

    /**
     * (updateGFT口座開設状況)<BR>
     * SOAP受信結果をGFT口座開設状況テーブルのデータに反映する。<BR>
     * <BR>
     * １）GFT口座開設状況Paramsインスタンスを取得する。<BR>
     * <BR>
     * 　@this.getGFT口座開設状況()をコールする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@証券会社コード： 引数.証券会社コード<BR>
     * 　@部店コード： 引数.部店コード<BR>
     * 　@識別コード： 引数.識別コード<BR>
     * <BR>
     * 　@※this.getGFT口座開設状況()の戻り値がnullの場合、例外をスローする。<BR>
     * <BR>
     * ２）取得したGFT口座開設状況Paramsのcloneを生成する。<BR>
     * <BR>
     * ３）cloneに、受付結果コードをセットする。<BR>
     * <BR>
     * 　@更新する行の内容は下記を参照。<BR>
     * <BR>
     * 　@【ｘTrade】補足資料.DB更新<BR>
     * 　@「FX口座開設_GFT口座開設状況テーブル.xls」の<BR>
     * 　@「GFT口座開設状況テーブル_DB更新仕様[SOAP接続結果更新]」シート<BR>
     * <BR>
     * ４）GFT口座開設状況のupdate<BR>
     * 　@WEB3DataAccessUtility.updateRow()メソッドをコールする。<BR>
     * <BR>
     * 　@[updateRow()にセットするパラメータ]<BR>
     * 　@　@l_row：　@３）にて受付結果コードをセットしたインスタンス<BR>
     * <BR>
     * @@param l_strInstitutionCode - 証券会社コード<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - 部店コード<BR>
     * 部店コード<BR>
     * @@param l_strOrderRequestNumber - 識別コード<BR>
     * 識別コード<BR>
     * @@param l_strResultCode - 受付結果コード<BR>
     * 受付結果コード<BR>
     * @@throws WEB3BaseException
     */
    public void updateGFTAccountOpenStatus(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strOrderRequestNumber,
        String l_strResultCode)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "updateGFTAccountOpenStatus(String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        // １）GFT口座開設状況Paramsインスタンスを取得する。
        // 　@this.getGFT口座開設状況()をコールする。
        // 　@[引数]
        // 　@証券会社コード： 引数.証券会社コード
        // 　@部店コード： 引数.部店コード
        // 　@識別コード： 引数.識別コード
        GftAccountOpenStatusParams l_gftAccountOpenStatusParams =
            this.getGFTAccountOpenStatus(
                l_strInstitutionCode,
                l_strBranchCode,
                l_strOrderRequestNumber);

        //※this.getGFT口座開設状況()の戻り値がnullの場合、例外をスローする。
        if (l_gftAccountOpenStatusParams == null)
        {
            log.debug("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "テーブルに該当するデータがありません。");
        }
        // ２）取得したGFT口座開設状況Paramsのcloneを生成する。
        GftAccountOpenStatusParams l_gftAccountOpenStatusParamsClone =
            new GftAccountOpenStatusParams(l_gftAccountOpenStatusParams);

        try
        {
            // ３）cloneに、受付結果コードをセットする。
            l_gftAccountOpenStatusParamsClone.setResultCodeSoap(l_strResultCode);

            // 　@更新する行の内容は下記を参照。
            // 　@【ｘTrade】補足資料.DB更新
            // 　@「FX口座開設_GFT口座開設状況テーブル.xls」の
            // 　@「GFT口座開設状況テーブル_DB更新仕様[SOAP接続結果更新]」シート
            l_gftAccountOpenStatusParamsClone.setLastUpdatedTimestamp(
                GtlUtils.getSystemTimestamp());

            // ４）GFT口座開設状況のupdate
            // 　@WEB3DataAccessUtility.updateRow()メソッドをコールする。
            // 　@[updateRow()にセットするパラメータ]
            // 　@　@l_row：　@３）にて受付結果コードをセットしたインスタンス
            WEB3DataAccessUtility.updateRow(l_gftAccountOpenStatusParamsClone);
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

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate接続準備) <BR>
     * SOAP接続準備を行う。 <BR>
     * <BR>
     * 1）引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.エンドポイント名を分割し、接続先情報を取得する。 <BR>
     * 　@ getEndpointName().split(arg0 : String) <BR>
     * 　@　@[引数] <BR>
     * 　@　@arg0： ";" <BR>
     * 2）プロキシ設定を行う。 <BR>
     *    ＜外部システムSOAP接続プリファ@レンス（RPC形式）params.エンドポイントの分割数 == 3の場合＞ <BR>
     *     エンドポイントとして1）で取得した接続先情報の配列第3要素を取得する。<BR>
     *   　@＜＜分割データの3件目の文字列に"https"が含まれる場合＞＞ <BR>
     * 　@　@ 下記の通りプロパティセットを行う。 <BR>
     * 　@　@（1）key   : "https.proxyHost" <BR>
     * 　@　@ 　@  value : 1）で取得した接続先情報の配列第1要素 <BR>
     * 　@　@（2）key   : "https.proxyPort" <BR>
     * 　@　@ 　@  value : 1）で取得した接続先情報の配列第2要素 <BR>
     *   　@＜＜上記の条件以外＞＞ <BR>
     * 　@　@ 下記の通りプロパティセットを行う。 <BR>
     * 　@　@（1）key   : "http.proxyHost" <BR>
     * 　@　@ 　@  value : 1）で取得した接続先情報の配列第1要素 <BR>
     * 　@　@（2）key   : "http.proxyPort" <BR>
     * 　@　@ 　@  value : 1）で取得した接続先情報の配列第2要素 <BR>
     *    ＜外部システムSOAP接続プリファ@レンス（RPC形式）params.エンドポイントの分割数 == 1の場合＞<BR>
     *     エンドポイントとして1）で取得した接続先情報の配列第1要素を取得する。<BR>
     *    ＜上記の条件以外＞<BR>
     * 　@   例外をスローする。<BR>
     * 3）SSLAdapterFactoryを生成し、クライアント証明書をセットする。<BR>
     * 　@　@（1）引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.ポートタイプ名を分割し、証明書ファ@イルパスを取得する。<BR>
     * 　@　@　@　@getPortTypeName().split(arg0 : String)<BR>
     * 　@　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@arg0： ";"<BR>
     *         ※外部システムSOAP接続プリファ@レンス（RPC形式）params.ポートタイプ名の分割数 == 2以外の場合、<BR>
     * 　@        例外をスローする。<BR>
     * 　@　@　@　@tag：　@  WEB3BusinessLayerException<BR>
     * 　@　@　@　@class:BUSINESS_ERROR_02398<BR>
     * 　@　@（2）SSLAdapterFactoryを生成する。<BR>
     * 　@　@　@　@getDefaultFactory();<BR>
     * 　@　@（3）（2）で生成したSSLAdapterFactoryからWLSSLAdapterを取得する。<BR>
     * 　@　@　@　@getSSLAdapter()<BR>
     * 　@　@（4）FileInputStreamインスタンスを生成し、クライアント証明書ファ@イル名のフルパスを格納する。<BR>
     * 　@　@　@　@FileInputStream(arg0 : String);<BR>
     * 　@　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@arg0： （1）で取得した証明書ファ@イルパスの配列第1要素<BR>
     * 　@　@（5）（3）で取得したWLSSLAdapterにクライアント証明書及びキーをセットする。<BR>
     * 　@　@　@　@loadLocalIdentity(arg0 : InputStream, arg1 : char[]);<BR>
     * 　@　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@arg0： （4）で生成したFileInputStream<BR>
     * 　@　@　@　@　@　@arg1： 外部システムSOAP接続プリファ@レンス（RPC形式）params.オペレーション名.toCharArray()<BR>
     * 　@　@（6）（3）で取得したWLSSLAdapterにCA証明書ファ@イル名のフルパスを格納する。<BR>
     * 　@　@　@　@setTrustedCertificatesFile(arg0 : String);<BR>
     * 　@　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@arg0： （1）で取得した証明書ファ@イルパスの配列第2要素<BR>
     * 　@　@（7）（2）で生成したSSLAdapterFactoryにデフォルトのWLSSLAdapterをセットする。<BR>
     * 　@　@　@　@setDefaultAdapter(arg0 : SSLAdapter);<BR>
     * 　@　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@arg0： （3）で取得したWLSSLAdapter<BR>
     * 　@　@（8）（1）で生成したSSLAdapterFactoryに（7）でセットしたWLSSLAdapterをデフォルト設定にする。<BR>
     * 　@　@　@　@setUseDefaultAdapter(arg0 : boolean);<BR>
     * 　@　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@arg0： true<BR>
     * <BR>
     * @@param l_rpcParams 外部システムSOAP接続プリファ@レンス（RPC形式）params
     * @@throws WEB3BaseException
     */
    public void validateSetup(SoapConnectPrefRpcParams l_rpcParams) 
    	throws WEB3BaseException{

      String STR_METHOD_NAME = 
      	"validateSetup(SoapConnectPrefRpcParams)";
      log.entering(STR_METHOD_NAME);

      String l_logMessage = "";
      
      
      //1）引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.エンドポイント名を分割し、
      //接続先情報を取得する。
      //getEndpointName().split(arg0 : String)
      //[引数]
      //arg0： ";"
      String[] l_strEndpointNames = l_rpcParams.getEndpointName().split(";");

      //2）プロキシ設定を行う。
      //＜外部システムSOAP接続プリファ@レンス（RPC形式）params.エンドポイントの分割数 == 3の場合＞
      if (l_strEndpointNames.length == 3)
      {
          // ＜＜分割データの3件目の文字列に"https"が含まれる場合＞＞
          //  下記の通りプロパティセットを行う。
          // （1）key   : "https.proxyHost"
          // 　@　@ value : 1）で取得した接続先情報の配列第1要素
          // （2）key   : "https.proxyPort"
          //  　@  value : 1）で取得した接続先情報の配列第2要素
          // ＜＜上記の条件以外＞＞
          //  下記の通りプロパティセットを行う。
          // （1）key   : "http.proxyHost"
          //  　@  value : 1）で取得した接続先情報の配列第1要素
          // （2）key   : "http.proxyPort"
          //  　@  value : 1）で取得した接続先情報の配列第2要素
	      if(l_strEndpointNames[2].trim().indexOf("https") >= 0)
	      {
	          System.setProperty("https.proxyHost", l_strEndpointNames[0].trim());
	          System.setProperty("https.proxyPort", l_strEndpointNames[1].trim());
	      }
	      else
	      {
	          System.setProperty("http.proxyHost", l_strEndpointNames[0].trim());
	          System.setProperty("http.proxyPort", l_strEndpointNames[1].trim());
	      }

      
      }
      //＜外部システムSOAP接続プリファ@レンス（RPC形式）params.エンドポイントの分割数 == 1の場合＞
      else if (l_strEndpointNames.length == 1)
      {
      }
      //＜上記の条件以外＞
      //例外をスローする。
      else
      {
          l_logMessage = 
              "外部システムSOAP接続プリファ@レンス(RPC形式).エンドポイント名" + 
              "のセクション数が、異なっています。\n" +
              "「proxy-host;proxy-port;protocol」でセットしてください。";
          //外部システム接続エラー
          throw new WEB3BusinessLayerException(
              WEB3ErrorCatalog.BUSINESS_ERROR_02398,
              this.getClass().getName() + "." + STR_METHOD_NAME,
              l_logMessage
              );
      }

      //3）SSLAdapterFactoryを生成し、クライアント証明書をセットする。
      //（1）引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.ポートタイプ名を分割し、
      //証明書ファ@イルパスを取得する。
      //getPortTypeName().split(arg0 : String)
      //[引数]
      //arg0： ";"
      String[] l_strPortTypeNames = l_rpcParams.getPortTypeName().split(";");

      //※外部システムSOAP接続プリファ@レンス（RPC形式）params.ポートタイプ名の分割数 == 2以外の場合、
      //例外をスローする。
      if (l_strPortTypeNames.length != 2)
      {
          l_logMessage = 
              "外部システムSOAP接続プリファ@レンス(RPC形式).ポートタイプ名" + 
              "のセクション数が、異なっています。\n" +
              "「クライアント証明書のフルパス名;CA証明書のフルパス名」でセットしてください。";
          //外部システム接続エラー
          throw new WEB3BusinessLayerException(
              WEB3ErrorCatalog.BUSINESS_ERROR_02398,
              this.getClass().getName() + "." + STR_METHOD_NAME,
              l_logMessage
              );
      }

      //（4）FileInputStreamインスタンスを生成し、クライアント証明書ファ@イル名のフルパスを格納する。
      try
      {
    	  if (WEB3SSLCertificateCoder.verifyCertificate(l_strPortTypeNames[1]))
    	  {
              //外部システム接続エラー
              throw new WEB3BusinessLayerException(
                  WEB3ErrorCatalog.BUSINESS_ERROR_02398,
                  this.getClass().getName() + "." + STR_METHOD_NAME,
                  l_logMessage);
    	  }
    	  sslSocketFactory =
    		  WEB3SSLCertificateCoder.getSSLSocketFactory(l_strPortTypeNames[0], l_rpcParams.getOperationName(), l_strPortTypeNames[1]);
      }
      catch (Exception l_ex)
      {
          l_logMessage = 
              "正しいクライアント証明書が取得できません。";
          //外部システム接続エラー
          throw new WEB3BusinessLayerException(
              WEB3ErrorCatalog.BUSINESS_ERROR_02398,
              this.getClass().getName() + "." + STR_METHOD_NAME,
              l_logMessage
              );
      }
    }

    /**
     * (get顧客コード)<BR>
     * 引数の条件に該当する顧客コードを取得する。<BR>
     * <BR>
     * １）FXシステムコード取得<BR>
     * <BR>
     * 　@FXシステムコード = 引数.FXシステムコード<BR>
     * <BR>
     * ２）DB検索<BR>
     * 　@FX口座番号テーブル(fx_account_code)を<BR>
     * 　@以下の条件で検索する。<BR>
     * <BR>
     * 　@引数.FXシステムコードがnullが、<BR>
     * 　@[nullでない場合]<BR>
     * 　@　@[条件]<BR>
     * 　@　@証券会社コード = 引数.証券会社コード<BR>
     * 　@　@部店コード = 引数.部店コード<BR>
     * 　@　@FXシステムコード = １）にて取得したFXシステムコード<BR>
     * 　@　@FX口座番号 = 引数.FX口座番号<BR>
     * <BR>
     * 　@[nullの場合]<BR>
     * 　@　@[条件]<BR>
     * 　@　@証券会社コード = 引数.証券会社コード<BR>
     * 　@　@部店コード = 引数.部店コード<BR>
     * 　@　@FX口座番号 = 引数.FX口座番号<BR>
     * <BR>
     * ３）２）で取得したデータの顧客コードを返却する。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strFXAccountCode - (FX口座番号)<BR>
     * FX口座番号<BR>
     * @@param l_strFxSystemCode - (FXシステムコード)<BR>
     * FXシステムコード<BR>
     * @@return String
     * @@throws WEB3BaseException, NotFoundException
     */
    public String getAccountCode(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strFXAccountCode,
        String l_strFxSystemCode) throws WEB3BaseException, NotFoundException
    {
        final String STR_METHOD_NAME = "getAccountCode(String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //DB検索
        //  引数.FXシステムコードがnullが、
        //  [nullでない場合]
        //　@  [条件]
        //　@  証券会社コード = 引数.証券会社コード
        //　@  部店コード = 引数.部店コード
        //　@  FXシステムコード = １）にて取得したFXシステムコード
        //　@  FX口座番号 = 引数.FX口座番号
        //
        //  [nullの場合]
        //  　@[条件]
        //  　@証券会社コード = 引数.証券会社コード
        //  　@部店コード = 引数.部店コード
        //  　@FX口座番号 = 引数.FX口座番号
        StringBuffer l_sbSql = new StringBuffer();
        List l_lisSqlValues = new ArrayList();

        l_sbSql.append(" institution_code = ? ");
        l_lisSqlValues.add(l_strInstitutionCode);

        l_sbSql.append(" and branch_code = ? ");
        l_lisSqlValues.add(l_strBranchCode);

        if (l_strFxSystemCode != null)
        {
            l_sbSql.append(" and fx_system_code = ? ");
            l_lisSqlValues.add(l_strFxSystemCode);
        }

        l_sbSql.append(" and fx_account_code = ? ");
        l_lisSqlValues.add(l_strFXAccountCode);

        List l_lisResults;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisResults = l_queryProcessor.doFindAllQuery(
                FxAccountCodeRow.TYPE,
                l_sbSql.toString(),
                l_lisSqlValues.toArray());
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

        if (l_lisResults.size() == 0)
        {
            log.debug("FX口座番号テーブルが取得できませんでした");
            log.exiting(STR_METHOD_NAME);
            throw new NotFoundException("FX口座番号テーブルが取得できませんでした");
        }

        if (l_lisResults.size() > 1)
        {
            log.debug("FX口座番号テーブルに複数件");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "データ不整合エラー。");
        }

        String l_strAccountCode = ((FxAccountCodeRow)l_lisResults.get(0)).getAccountCode();

        log.exiting(STR_METHOD_NAME);
        return l_strAccountCode;
    }

    /**
     * (getSOAPTFX受付結果コード)<BR>
     * 結果通知電文にセットする受付結果コードを取得する。<BR>
     * <BR>
     * １）引数.受付結果コードの値により、対応する受付結果コードをセットする。<BR>
     * <BR>
     * 　@　@・引数.GFT受付結果コード == ”0”（正常終了） の場合、<BR>
     * 　@　@　@または、引数.GFT受付結果コード == ”1”（正常終了（取引所入金受付不可）） の場合、<BR>
     * 　@　@　@　@　@受付結果コード.”00000000”（処理完了）<BR>
     * <BR>
     * 　@　@・引数.GFT受付結果コード == ”2”（パラメタエラー） の場合、<BR>
     * 　@　@　@　@　@受付結果コード.”00000609”（上記以外で電文書式に起因するエラー）<BR>
     * <BR>
     * 　@　@・引数.GFT受付結果コード == ”1001”（利用者コード不正） の場合、<BR>
     * 　@　@　@　@　@受付結果コード.”00000501”（入出金時に該当する証拠金口座が存在しない）<BR>
     * <BR>
     * 　@　@・引数.GFT受付結果コード == ”3”（重複エラー） の場合、<BR>
     * 　@　@　@　@　@受付結果コード.”00000801”（二重処理エラー）<BR>
     * <BR>
     * 　@　@・引数.GFT受付結果コード == ”6001”（稼動時間外エラー） の場合、<BR>
     * 　@　@　@　@　@受付結果コード.”00000105”（ホスト処理時間外）<BR>
     * <BR>
     * 　@　@・上記以外の場合、<BR>
     * 　@　@　@　@　@受付結果コード.”00000199”（上記以外でホストシステムに起因するエラー）<BR>
     * <BR>
     * ２）受付結果コードを返却する。<BR>
     * @@param l_strAcceptResultCode - (受付結果コード)<BR>
     * 受付結果コード<BR>
     * @@return String
     */
    public String getSoapTFXAcceptResultCode(String l_strAcceptResultCode)
    {
        final String STR_METHOD_NAME = "getSoapTFXAcceptResultCode(String)";
        log.entering(STR_METHOD_NAME);

        String l_strSoapAcceptResultCode = null;
        //１）引数.受付結果コードの値により、対応する受付結果コードをセットする。
        //・引数.GFT受付結果コード == ”0”（正常終了） の場合、
        //または、引数.GFT受付結果コード == ”1”（正常終了（取引所入金受付不可）） の場合、
        //受付結果コード.”00000000”（処理完了）
        if (WEB3SoapResultCodeDef.NORMAL.equals(l_strAcceptResultCode)
            || WEB3SoapResultCodeDef.NORMAL_CANNOT_IN.equals(l_strAcceptResultCode))
        {
            l_strSoapAcceptResultCode =
                WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000;
        }
        //・引数.GFT受付結果コード == ”2”（パラメタエラー） の場合、
        //受付結果コード.”00000609”（上記以外で電文書式に起因するエラー）
        else if (WEB3SoapResultCodeDef.PARAM_ERROR.equals(l_strAcceptResultCode))
        {
            l_strSoapAcceptResultCode =
                WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000609;
        }
        //・引数.GFT受付結果コード == ”1001”（利用者コード不正） の場合、
        //受付結果コード.”00000501”（入出金時に該当する証拠金口座が存在しない）
        else if (WEB3SoapResultCodeDef.USER_CODE_ERROR.equals(l_strAcceptResultCode))
        {
            l_strSoapAcceptResultCode =
                WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000501;
        }
        //・引数.GFT受付結果コード == ”3”（重複エラー） の場合、
        //受付結果コード.”00000801”（二重処理エラー）
        else if (WEB3SoapResultCodeDef.REPEAT_ERROR.equals(l_strAcceptResultCode))
        {
            l_strSoapAcceptResultCode =
                WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000801;
        }
        //・引数.GFT受付結果コード == ”6001”（稼動時間外エラー） の場合、
        //受付結果コード.”00000105”（ホスト処理時間外）
        else if (WEB3SoapResultCodeDef.WORK_TIME_OUT_ERROR.equals(l_strAcceptResultCode))
        {
            l_strSoapAcceptResultCode =
                WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000105;
        }
        //・上記以外の場合、
        //受付結果コード.”00000199”（上記以外でホストシステムに起因するエラー）
        else
        {
            l_strSoapAcceptResultCode =
                WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000199;
        }

        //２）受付結果コードを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_strSoapAcceptResultCode;
    }

    /**
     * (sendSOAPメッセージ)<BR>
     * SOAP接続を行う。<BR>
     * <BR>
     * 1）サービス実装クラスを生成する。<BR>
     * <BR>
     * 　@ WebService_Impl()<BR>
     * <BR>
     * 2）サービススタブを生成する。<BR>
     * <BR>
     * 　@　@service.getWebServiceSoap(arg0 : String)<BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@　@arg0 : 外部システムSOAP接続プリファ@レンス（RPC形式）params.サービス名<BR>
     * <BR>
     * <BR>
     * 3）サービススタブのプロパティを設定する。<BR>
     * <BR>
     * 　@　@　@（1）プロパティにエンドポイントをセットする。<BR>
     * 　@　@　@　@　@_setProperty(arg0 : String, arg1 : Object)<BR> 
     * 　@　@　@　@　@　@[引数]<BR> 
     * 　@　@　@　@　@　@　@arg0： "javax.xml.rpc.service.endpoint.address"<BR>
     *　@　@　@　@       arg1： －引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.エンドポイント名の分割数 == 3の場合<BR>
     *                          引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.エンドポイント名の配列第3要素<BR>
     *                      －引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.エンドポイント名の分割数 == 1の場合<BR>
     *                          引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.エンドポイント名の配列第1要素<BR>
     * <BR>
     * 　@　@　@（2）プロパティに接続タイムアウト時間をセットする。<BR>
     * 　@　@　@　@　@_setProperty(arg0 : String, arg1 : Object)<BR>
     * 　@　@　@　@　@　@[引数]<BR>
     * <BR>
     * <BR>
     * 4）リクエストデータを生成し、メッセージ送受信を実施し、処理結果レスポンスデータを返却する。<BR>
     * <BR>
     * 　@＜引数.GFT依頼電文明細.処理区分 == "04：出金"の場合(FXへの振替の場合)＞<BR>
     * <BR>
     * 　@　@　@（1）EntryReceiptInインスタンスを生成する。<BR>
     * 　@　@　@　@　@EntryReceiptIn()<BR>
     * <BR>
     * 　@　@　@　@　@　@（A）EntryReceiptInインスタンスの科目コードに値をセットする。<BR>
     * 　@　@　@　@　@　@　@　@setKamokuCd(arg0 : String)<BR>
     * 　@　@　@　@　@　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@　@　@　@　@arg0： ”1”（預入金）<BR>
     * 　@　@　@　@　@　@（B）EntryReceiptInインスタンスの入金額に値をセットする。<BR>
     * 　@　@　@　@　@　@　@　@setReptAmt(arg0 : java.math.BigDecimal)<BR>
     * 　@　@　@　@　@　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@　@　@　@　@arg0： 引数.GFT依頼電文明細.入出金額<BR>
     * 　@　@　@　@　@　@（C）EntryReceiptInインスタンスの利用者コードに値をセットする。<BR>
     * 　@　@　@　@　@　@　@　@setRysCd(arg0 : String)<BR>
     * 　@　@　@　@　@　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@　@　@　@　@arg0： 引数.GFT依頼電文明細.初期ログインID.substring(1)<BR>
     * 　@　@　@　@　@　@（D）EntryReceiptInインスタンスのＷＥＢサービス要求番号に値をセットする。<BR>
     * 　@　@　@　@　@　@　@　@setWebServReqNo(arg0 : String)<BR>
     * 　@　@　@　@　@　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@　@　@　@　@arg0： 引数.GFT依頼電文明細.初期ログインID.substring(1)<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@+ 引数.GFT依頼電文明細.DIR→GFT送信日時.substring(2)<BR>
     * <BR>
     * 　@　@　@（2）SOAPメッセージハンドラをセットする。 
     * <BR>
     * 　@　@　@　@　@　@(A)Web サービス ポートの修飾名を格納するオブジェクトを作成 
     * 　@　@　@　@　@　@　@　@QName( arg0 : l_rpcParams.target_namespace_name, arg1 : l_rpcParams.service_name) 
     * 　@　@　@　@　@　@　@　@　@　@[引数] 
     * 　@　@　@　@　@　@　@　@　@　@　@arg0： 引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.ターゲットネームスペース名 
     * 　@　@　@　@　@　@　@　@　@　@　@arg1： 引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.サービス名 
     * <BR>
     * 　@　@　@　@　@　@(B)HandlerRegistry オブジェクトを作成 
     * <BR>
     * 　@　@　@　@　@　@(C)HandlerInfoに設定するconfig(：Map)データの作成 
     * <BR>
     * 　@　@　@　@　@　@　@　@[put()に指定する引数] 
     * 　@　@　@　@　@　@　@　@　@key："BranchId" 
     * 　@　@　@　@　@　@　@　@　@value：引数.外部システムSOAP接続プリファ@レンス（RPC形式）params. 部店ID 
     * 　@　@　@　@　@　@　@　@[put()に指定する引数] 
     * 　@　@　@　@　@　@　@　@　@key："ConnectDiv" 
     * 　@　@　@　@　@　@　@　@　@value：引数.外部システムSOAP接続プリファ@レンス（RPC形式）params. 接続区分 
     * <BR>
     * 　@　@　@　@　@　@(D)SOAPメッセージのハンドラ チェーン(：ArrayList)生成 
     * <BR>
     * 　@　@　@　@　@　@　@　@[add()に指定する引数] 
     * 　@　@　@　@　@　@　@　@　@new HandlerInfo( arg0 : WEB3FXSOAPLogHandler.class, arg1 : map, arg2 : null ) 
     * 　@　@　@　@　@　@　@　@　@　@[引数] 
     * 　@　@　@　@　@　@　@　@　@　@　@arg0： WEB3FXSOAPLogHandler.class 
     * 　@　@　@　@　@　@　@　@　@　@　@arg1： (3)で生成したMapオブジェクト 
     * 　@　@　@　@　@　@　@　@　@　@　@arg2： null 
     * <BR>
     * 　@　@　@　@　@　@(E)ハンドラ チェーンを登録 
     * 　@　@　@　@　@　@　@　@setHandlerChain( arg0 : portName, arg1 : handlerList ) 
     * 　@　@　@　@　@　@　@　@　@　@[引数] 
     * 　@　@　@　@　@　@　@　@　@　@　@arg0： (1)で生成したオブジェクト 
     * 　@　@　@　@　@　@　@　@　@　@　@arg1： (4)で生成したArrayList 
     * <BR>
     * 　@　@　@（3）メッセージ送受信<BR>
     * 　@　@　@　@　@regSoap_Stub.entryReceipt(arg0 : EntryReceiptIn)<BR>
     * 　@　@　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@　@arg0 : （1）で生成したEntryReceiptInインスタンス<BR>
     * <BR>
     * 　@　@　@（4）（3）で取得した処理結果レスポンスデータから、処理結果コードを取得する。<BR>
     * <BR>
     * <BR>
     * <BR>
     * 5）4）で取得した処理結果コードを返却する。<BR>
     * <BR>
     * @@param l_fxGftAskingTelegramUnit - (電文明細)<BR>
     * 電文明細<BR>
     * @@param l_rpcParams - (SOAPプリファ@レンス)<BR>
     * 外部システムSOAPプリファ@レンス（RPC形式）params<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String sendSoapMessage(
        WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit,
        SoapConnectPrefRpcParams l_rpcParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "sendSoapMessage("
            + "WEB3FXGftAskingTelegramUnit, SoapConnectPrefRpcParams)";
        log.entering(STR_METHOD_NAME);


        WebServiceSoap l_webServiceSoap_Stub = null;
        String l_strReturnCd = null;
            //4）サービス実装クラスを生成する。
            //WebService_Impl()
            WebService service = new WebService();

            //5）サービススタブを生成する。
            //service.getWebServiceSoap(arg0 : String)
            //[引数]
            //arg0 : 外部システムSOAP接続プリファ@レンス（RPC形式）params.サービス名
            l_webServiceSoap_Stub =
                (WebServiceSoap)service.getWebService(
                    l_rpcParams.getServiceName());

            //6）サービススタブのプロパティを設定する。
            //（1）プロパティにエンドポイントをセットする。
            //_setProperty(arg0 : String, arg1 : Object)
            //[引数]
            //arg0： "javax.xml.rpc.service.endpoint.address"
            //arg1： 2）で取得したエンドポイント
            //arg1： －引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.エンドポイント名の分割数 == 3の場合
            //        引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.エンドポイント名の配列第3要素
            //       －引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.エンドポイント名の分割数 == 1の場合
            //        引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.エンドポイント名の配列第1要素
            String[] l_strEndpointNames = l_rpcParams.getEndpointName().split(";");
            String l_strProtocolStr = "";
            if (l_strEndpointNames.length == 3)
            {
                l_strProtocolStr = l_strEndpointNames[2];
            }
            else if (l_strEndpointNames.length == 1)
            {
                l_strProtocolStr = l_strEndpointNames[0];
            }
            
            BindingProvider l_bp = (BindingProvider)l_webServiceSoap_Stub;
            l_bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, l_strProtocolStr);

            //（2）プロパティに接続タイムアウト時間をセットする。
            l_bp.getRequestContext().put(BindingProviderProperties.CONNECT_TIMEOUT, l_rpcParams.response_timeout);
            l_bp.getRequestContext().put(BindingProviderProperties.REQUEST_TIMEOUT, l_rpcParams.response_timeout);

            l_bp.getRequestContext().put(JAXWSProperties.SSL_SOCKET_FACTORY, sslSocketFactory);

            String l_strOpDiv = l_fxGftAskingTelegramUnit.gftOperationDiv;
            //7）リクエストデータを生成し、メッセージ送受信を実施し、処理結果レスポンスデータを返却する。
            //＜引数.GFT依頼電文明細.処理区分 == "04：出金"の場合(FXへの振替の場合)＞
            if (WEB3GftMessageOperationDef.CASH_OUT.equals(l_strOpDiv))
            {
                //（1）EntryReceiptInインスタンスを生成する。
                //EntryReceiptIn()
            	EntryReceiptIn l_entryReceiptIn = new EntryReceiptIn();

                //（A）EntryReceiptInインスタンスの科目コードに値をセットする。
                //setKamokuCd(arg0 : String)
                //[引数]
                //arg0： ”1”（預入金）
                l_entryReceiptIn.setKamokuCd(WEB3KamokuCdDef.DEPOSIT);

                //（B）EntryReceiptInインスタンスの入金額に値をセットする。
                //setReptAmt(arg0 : java.math.BigDecimal)
                //[引数]
                //arg0： 引数.GFT依頼電文明細.入出金額
                l_entryReceiptIn.setReptAmt(
                    new BigDecimal(
                        l_fxGftAskingTelegramUnit.cashinoutAmt));

                //（C）EntryReceiptInインスタンスの利用者コードに値をセットする。
                //setRysCd(arg0 : String)
                //[引数]
                //arg0： 引数.GFT依頼電文明細.初期ログインID.substring(1)
                l_entryReceiptIn.setRysCd(
                    l_fxGftAskingTelegramUnit.fxFirstLoginId.substring(1));

                //（D）EntryReceiptInインスタンスのＷＥＢサービス要求番号に値をセットする。
                //setWebServReqNo(arg0 : String)
                //[引数]
                //arg0： 引数.GFT依頼電文明細.初期ログインID.substring(1)
                //+ 引数.GFT依頼電文明細.DIR→GFT送信日時.substring(2)
                l_entryReceiptIn.setWebServReqNo(
                    l_fxGftAskingTelegramUnit.fxFirstLoginId.substring(1)
                    + l_fxGftAskingTelegramUnit.dirSendTime.substring(2));

                //(2) SOAPメッセージハンドラのセットする。
                ThreadLocalSystemAttributesRegistry.setAttribute("branch_id", String.valueOf(l_rpcParams.branch_id));
                ThreadLocalSystemAttributesRegistry.setAttribute("connect_div", String.valueOf(l_rpcParams.connect_div));
                
                Binding l_bd = l_bp.getBinding();
                List l_lisHandlerChain = l_bd.getHandlerChain();
                WEB3FXSOAPMsgHandler l_handler = new WEB3FXSOAPMsgHandler();
                l_lisHandlerChain.add(l_handler);
                l_bd.setHandlerChain(l_lisHandlerChain);
                
                //（3）メッセージ送受信
                //regSoap_Stub.entryReceipt(arg0 : EntryReceiptIn)
                //[引数]
                //arg0 : （1）で生成したEntryReceiptInインスタンス
                EntryReceiptOut l_entryReceiptOut =
                    l_webServiceSoap_Stub.entryReceipt(l_entryReceiptIn);

                //（4）（3）で取得した処理結果レスポンスデータから、処理結果コードを取得する。
                l_strReturnCd = l_entryReceiptOut.getReturnCd() + "";
            }

        //8）7）で取得した処理結果コードを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_strReturnCd;
    }

    /**
     * (validate振替可能)<BR>
     * 取引可能かチェックを行う。<BR>
     * <BR>
     * 1）FXシステム区分 = 1（先OPシステム）の場合<BR>
     * 　@　@　@AIO注文マネージャ.validate先物取引口座開設()をコール<BR>
     * 2）上記以外の場合<BR>
     * 　@　@　@AIO注文マネージャ.validate振替取引可能（）をコール<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_compFxConditionParams - (会社別FXシステム条件Params)<BR>
     * 会社別FXシステム条件Params<BR>
     * @@throws WEB3BaseException
     */
    public void validateChangePoss(
        SubAccount l_subAccount,
        CompFxConditionParams l_compFxConditionParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateChangePoss(SubAccount, CompFxConditionParams)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_TradingModule = l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_orderManager =
            (WEB3AioOrderManager)l_TradingModule.getOrderManager();

        String l_strFxSystemDiv = l_compFxConditionParams.getFxSystemDiv();
        //FXシステム区分 = 1（先OPシステム）の場合
        if (WEB3FxSystemDivDef.FUOP_SYSTEM.equals(l_strFxSystemDiv))
        {
            //AIO注文マネージャ.validate先物取引口座開設()をコール
            l_orderManager.validateOpenFuturesTradedAccount(l_subAccount);
        }
        else
        {
            //上記以外の場合
            //AIO注文マネージャ.validate振替取引可能（）をコール
            l_orderManager.validateTransferTradePossible(
                l_subAccount,
                l_compFxConditionParams);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （getFXメールアドレス）<BR>
     * 引数の条件に該当するFXメールアドレスを取得する。<BR>
     * <BR>
     * １）以下の値を取得する。<BR>
     * <BR>
     * 　@　@メールアドレス：　@顧客（*１）.emailアドレス（nullの場合、nullをセット）<BR>
     * 　@　@（*１）引数.補助口座.getMainAccount()にて取得
     * ２）１）で取得した値を返却する。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@return String
     */
    public String getFxMailAddress(SubAccount l_subAccount)
    {
        final String STR_METHOD_NAME = "getFxMailAddress(SubAccount)";
        log.entering(STR_METHOD_NAME);

        //メールアドレス：　@顧客（*１）.emailアドレス（nullの場合、nullをセット）
        //（*１）引数.補助口座.getMainAccount()にて取得
        MainAccountRow l_mainAccountRow =
            (MainAccountRow)l_subAccount.getMainAccount().getDataSourceObject();
        String l_strFxMailAddress = l_mainAccountRow.getEmailAddress();

        log.exiting(STR_METHOD_NAME);
        return l_strFxMailAddress;
    }

    /**
     * （getGFTFXメールアドレス）<BR>
     * 以下条件に該当するFXメールアドレスを取得する。<BR>
     * <BR>
     * １）DB検索<BR>
     * <BR>
     * 　@１－１）<BR>
     * 　@　@FX顧客テーブル(fx_account)を以下の条件で検索し、FXメールアドレスを取得する。<BR>
     * 　@　@※レコードが複数件取得された場合は、1件目のFXメールアドレスを取得する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@証券会社コード = 引数.補助口座.証券会社コード<BR>
     * 　@部店コード = 引数.補助口座.get取引店.getBranchCode()<BR>
     * 　@顧客コード = 引数.補助口座.getMainAccount().getAccountCode()<BR>
     * 　@FXシステムコード in 引数.FXシステムコード一覧<BR>
     * <BR>
     * 　@１－２）<BR>
     * 　@　@１－１）で値が取得できなかった場合、以下の値を取得する。<BR>
     * <BR>
     * 　@　@メールアドレス：　@顧客（*１）.emailアドレス（nullの場合、nullをセット）<BR>
     * 　@　@（*１）引数.補助口座.getMainAccount()にて取得<BR>
     * <BR>
     * ２）１）で取得した値を返却する。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座
     * @@param l_lisFxSystemCodeLists - (FXシステムコード一覧)
     * FXシステムコード一覧
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getGFTFxMailAddress(
        SubAccount l_subAccount,
        ArrayList l_lisFxSystemCodeLists) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getGFTFxMailAddress("
            + "SubAccount, ArrayList)";
        log.entering(STR_METHOD_NAME);

        String l_strFxMailAddress = null;
        MainAccount l_mainAccount = l_subAccount.getMainAccount();
        MainAccountRow l_mainAccountRow =
            (MainAccountRow)l_mainAccount.getDataSourceObject();

        //１）DB検索
        //１－１）FX顧客テーブル(fx_account)を以下の条件で検索し、FXメールアドレスを取得する。
        //※レコードが複数件取得された場合は、1件目のFXメールアドレスを取得する。
        //[条件]
        //証券会社コード = 引数.補助口座.証券会社コード
        //部店コード = 引数.補助口座.get取引店.getBranchCode()
        //顧客コード = 引数.補助口座.getMainAccount().getAccountCode()
        //FXシステムコード in 引数.FXシステムコード一覧
        String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
        String l_strAccountCode = l_mainAccount.getAccountCode();
        String l_strInstitutionCode =
            l_subAccount.getInstitution().getInstitutionCode();

        String l_strWhere = " institution_code = ? and branch_code = ?"
            + " and account_code = ? ";

        List l_lisValues = new ArrayList();
        l_lisValues.add(l_strInstitutionCode);
        l_lisValues.add(l_strBranchCode);
        l_lisValues.add(l_strAccountCode);
        if (l_lisFxSystemCodeLists != null && l_lisFxSystemCodeLists.size() != 0)
        {
            l_strWhere += " and fx_system_code in ( ";
            for (int i = 0; i < l_lisFxSystemCodeLists.size(); i++)
            {
                l_lisValues.add(l_lisFxSystemCodeLists.get(i));
                if (i == l_lisFxSystemCodeLists.size() - 1)
                {
                    l_strWhere += " ?";
                }
                else
                {
                    l_strWhere += " ?,";
                }
            }
            l_strWhere += " ) ";
        }

        Object[] l_sqlValues = new Object[l_lisValues.size()];
        l_lisValues.toArray(l_sqlValues);

        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    FxAccountRow.TYPE,
                    l_strWhere,
                    l_sqlValues);
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

        //１－２） １－１）で値が取得できなかった場合、以下の値を取得する。
        if (l_lisRows.size() == 0)
        {
            //メールアドレス：　@顧客（*１）.emailアドレス（nullの場合、nullをセット）
            //（*１）引数.補助口座.getMainAccount()にて取得
            l_strFxMailAddress = l_mainAccountRow.getEmailAddress();
        }
        else
        {
            l_strFxMailAddress =
                ((FxAccountRow)l_lisRows.get(0)).getFxMailAddress();
        }

        log.exiting(STR_METHOD_NAME);
        return l_strFxMailAddress;
    }

    /**
     * （isGFT口座開設）<BR>
     * 顧客がGFT口座開設済であるかチェックを行う。<BR>
     * <BR>
     * １）FX顧客レコードを取得する。<BR>
     * <BR>
     * 　@FX顧客テーブル(fx_account)を以下の条件で検索する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@証券会社コード = 引数.証券会社コード<BR>
     * 　@部店コード = 引数.部店コード<BR>
     * 　@顧客コード = 引数.顧客コード<BR>
     * 　@FXシステムコード in 引数.FXシステムコード一覧<BR>
     * <BR>
     * ２）１）でレコードが取得できた場合は、trueを返却。<BR>
     * 　@　@取得できなかった場合は、falseを返却。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード<BR>
     * @@param l_lisFxSystemCodeLists - (FXシステムコード一覧)<BR>
     * FXシステムコード一覧<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isGFTAccOpen(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        ArrayList l_lisFxSystemCodeLists) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isGFTAccOpen("
            + "String, String, String, ArrayList)";
        log.entering(STR_METHOD_NAME);

        //１）FX顧客レコードを取得する。
        //FX顧客テーブル(fx_account)を以下の条件で検索する。
        //[条件]
        //証券会社コード = 引数.証券会社コード
        //部店コード = 引数.部店コード
        //顧客コード = 引数.顧客コード
        //FXシステムコード in 引数.FXシステムコード一覧
        String l_strWhere = " institution_code = ? and branch_code = ? "
            + " and account_code = ? ";

        List l_lisValues = new ArrayList();
        l_lisValues.add(l_strInstitutionCode);
        l_lisValues.add(l_strBranchCode);
        l_lisValues.add(l_strAccountCode);
        if (l_lisFxSystemCodeLists != null && l_lisFxSystemCodeLists.size() != 0)
        {
            l_strWhere += " and fx_system_code in ( ";
            for (int i = 0; i < l_lisFxSystemCodeLists.size(); i++)
            {
                l_lisValues.add(l_lisFxSystemCodeLists.get(i));
                if (i == l_lisFxSystemCodeLists.size() - 1)
                {
                    l_strWhere += " ?";
                }
                else
                {
                    l_strWhere += " ?,";
                }
            }
            l_strWhere += " ) ";
        }

        Object[] l_objValues = new Object[l_lisValues.size()];
        l_lisValues.toArray(l_objValues);

        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    FxAccountRow.TYPE,
                    l_strWhere,
                    l_objValues);
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

        //２）１）でレコードが取得できた場合は、trueを返却。
        //取得できなかった場合は、falseを返却。
        if (l_lisRows.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (createFX口座情報一覧)<BR>
     * 引数の条件に該当するFX口座情報の一覧を作成する。<BR>
     * <BR>
     * 1)　@ArreyList作成<BR>
     * <BR>
     * 2)　@口座情報取得<BR>
     * <BR>
     * 　@　@2-1　@引数.電文明細.処理区分 == "01"の場合以下処理を行う。<BR>
     * 　@　@　@　@　@引数.口座開設結果 != null かつ、引数.口座開設結果.getAccountIds() != nullの場合<BR>
     * <BR>
     * 　@　@　@　@　@<2-1-1　@引数.同時開設FXシステムコードがnullでなかった場合、以下処理を実施する。><BR>
     * 　@　@　@　@　@１．FX口座情報インスタンスを生成する。<BR>
     * 　@　@　@　@　@２．生成したインスタンスに以下のプロパティをセットする。<BR>
     * 　@　@　@　@　@　@　@○口座番号 = 引数.口座開設結果.getAccountIds()の戻り値第1要素<BR>
     * 　@　@　@　@　@　@　@○コース区分 = 1(1万通貨コース)<BR>
     * 　@　@　@　@　@３．ArrayListにプロパティセットしたインスタンスを追加する。<BR>
     * 　@　@　@　@　@４．FX口座情報インスタンスを生成する。<BR>
     * 　@　@　@　@　@５．生成したインスタンスに以下のプロパティをセットする。<BR>
     * 　@　@　@　@　@　@　@○口座番号 = 引数.口座開設結果.getAccountIds()の戻り値第2要素<BR>
     * 　@　@　@　@　@　@　@○コース区分 = 2(10万通貨コース)<BR>
     * 　@　@　@　@　@６．ArrayListにプロパティセットしたインスタンスを追加する。<BR>
     * 　@　@　@　@　@７．FX口座情報インスタンスを生成する。<BR>
     * 　@　@　@　@　@８．生成したインスタンスに以下のプロパティをセットする。<BR>
     * 　@　@　@　@　@　@　@○口座番号 = 引数.口座開設結果.getAccountIds()の戻り値第3要素<BR>
     * 　@　@　@　@　@　@　@○コース区分 = 3(CFDコース)<BR>
     * 　@　@　@　@　@９．ArrayListにプロパティセットしたインスタンスを追加する。<BR>
     * 　@　@　@　@　@１０．2)の処理を終了する。<BR>
     * <BR>
     * 　@　@　@　@　@<2-1-2　@引数.会社別FXシステム条件Params.FXシステム区分がnullだった場合以下処理を実施する。><BR>
     * 　@　@　@　@　@１．FX口座情報インスタンスを生成する。<BR>
     * 　@　@　@　@　@２．生成したインスタンスに以下のプロパティをセットする。<BR>
     * 　@　@　@　@　@　@　@○口座番号 = 引数.口座開設結果.getAccountIds()の戻り値第1要素<BR>
     * 　@　@　@　@　@　@　@○コース区分 = 1(1万通貨コース)<BR>
     * 　@　@　@　@　@３．ArrayListにプロパティセットしたインスタンスを追加する。<BR>
     * 　@　@　@　@　@４．FX口座情報インスタンスを生成する。<BR>
     * 　@　@　@　@　@５．生成したインスタンスに以下のプロパティをセットする。<BR>
     * 　@　@　@　@　@　@　@○口座番号 = 引数.口座開設結果.getAccountIds()の戻り値第2要素<BR>
     * 　@　@　@　@　@　@　@○コース区分 = 2(10万通貨コース)<BR>
     * 　@　@　@　@　@６．ArrayListにプロパティセットしたインスタンスを追加する。<BR>
     * 　@　@　@　@　@７．2)の処理を終了する。<BR>
     * <BR>
     * 　@　@　@　@　@<2-1-3　@引数.会社別FXシステム条件Params.FXシステム区分==”2”だった場合、以下処理を実施する。><BR>
     * 　@　@　@　@　@１．FX口座情報インスタンスを生成する。<BR>
     * 　@　@　@　@　@２．生成したインスタンスに以下のプロパティをセットする。<BR>
     * 　@　@　@　@　@　@　@○口座番号 = 引数.口座開設結果.getAccountIds()の戻り値第1要素<BR>
     * 　@　@　@　@　@　@　@○コース区分 = 3(CFDコース)<BR>
     * 　@　@　@　@　@３．ArrayListにプロパティセットしたインスタンスを追加する。<BR>
     * 　@　@　@　@　@４．2)の処理を終了する。<BR>
     * <BR>
     * 　@　@2-2　@引数.電文明細.処理区分 == "03"の場合以下処理を行う。<BR>
     * 　@　@　@　@　@引数.追加開設結果 != null かつ、引数.追加開設結果.getAccountId() != nullの場合<BR>
     * <BR>
     * 　@　@　@　@　@<2-2-1　@引数.会社別FXシステム条件Params.FXシステム区分がnullだった場合、以下処理を実施する。><BR>
     * 　@　@　@　@　@１．FX口座情報インスタンスを生成する。<BR>
     * 　@　@　@　@　@２．生成したインスタンスに以下のプロパティをセットする。<BR>
     * 　@　@　@　@　@　@　@○口座番号 = 引数.追加開設結果.getAccountId()の戻り値第1要素<BR>
     * 　@　@　@　@　@　@　@○コース区分 = 1(1万通貨コース)<BR>
     * 　@　@　@　@　@３．ArrayListにプロパティセットしたインスタンスを追加する。<BR>
     * 　@　@　@　@　@４．FX口座情報インスタンスを生成する。<BR>
     * 　@　@　@　@　@５．生成したインスタンスに以下のプロパティをセットする。<BR>
     * 　@　@　@　@　@　@　@○口座番号 = 引数.追加開設結果.getAccountId()の戻り値第2要素<BR>
     * 　@　@　@　@　@　@　@○コース区分 = 2(10万通貨コース)<BR>
     * 　@　@　@　@　@６．ArrayListにプロパティセットしたインスタンスを追加する。<BR>
     * 　@　@　@　@　@７．2)の処理を終了する。<BR>
     * <BR>
     * 　@　@　@　@　@<2-2-2　@引数.会社別FXシステム条件Params.FXシステム区分==”2”だった場合、以下処理を実施する。><BR>
     * 　@　@　@　@　@１．FX口座情報インスタンスを生成する。<BR>
     * 　@　@　@　@　@２．生成したインスタンスに以下のプロパティをセットする。<BR>
     * 　@　@　@　@　@　@　@○口座番号 = 引数.追加開設結果.getAccountId()の戻り値第1要素<BR>
     * 　@　@　@　@　@　@　@○コース区分 = 3(CFDコース)<BR>
     * 　@　@　@　@　@３．ArrayListにプロパティセットしたインスタンスを追加する。<BR>
     * 　@　@　@　@　@４．2)の処理を終了する。<BR>
     * <BR>
     * 3)　@生成したArrayList.toArray()の戻り値を返却する。<BR>
     * <BR>
     * @@param l_fxGftAskingTelegramUnit - (電文明細)<BR>
     * GFT依頼電文明細<BR>
     * @@param l_resultInfoCreateUser - (口座開設結果)<BR>
     * 口座開設結果<BR>
     * @@param l_strSameTimeFxSystemCode - (同時開設FXシステムコード)<BR>
     * 同時開設FXシステムコード<BR>
     * @@param l_compFxConditionParams - (会社別FXシステム条件Params)<BR>
     * 会社別FXシステム条件Params<BR>
     * @@param l_resultInfoAddAccount - (追加開設結果)<BR>
     * 追加開設結果<BR>
     * @@return WEB3FXAccInformationUnit[]
     */
    public WEB3FXAccInformationUnit[] createFXAccInformationUnits(
        WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit,
        ResultInfoCreateUser l_resultInfoCreateUser,
        String l_strSameTimeFxSystemCode,
        CompFxConditionParams l_compFxConditionParams,
        ResultInfoAddAccount l_resultInfoAddAccount)
    {
        final String STR_METHOD_NAME = "createFXAccInformationUnits("
            + "WEB3FXGftAskingTelegramUnit, ResultInfoCreateUser,"
            + "String, CompFxConditionParams, ResultInfoAddAccount)";
        log.entering(STR_METHOD_NAME);

        WEB3FXAccInformationUnit[] l_fxAccInformationUnits = null;
        WEB3FXAccInformationUnit l_fxAccInformationUnit = null;
        //1)　@ArreyList作成
        ArrayList l_lisRecord = new ArrayList();

        String l_strFxSystemDiv = l_compFxConditionParams.getFxSystemDiv();
        String l_strGftOperationDiv = l_fxGftAskingTelegramUnit.gftOperationDiv;
        //2)　@口座情報取得
        //2-1　@引数.電文明細.処理区分 == "01"の場合以下処理を行う。
        if (WEB3AdminAioGftOperationDivDef.ACCOUNT_OPEN.equals(
            l_strGftOperationDiv))
        {
            //引数.口座開設結果 != null かつ、引数.口座開設結果.getAccountIds() != nullの場合
            if (l_resultInfoCreateUser != null
                && l_resultInfoCreateUser.getAccountIds() != null)
            {
                //<2-1-1　@引数.同時開設FXシステムコードがnullでなかった場合、以下処理を実施する。>
                if (!WEB3StringTypeUtility.isEmpty(l_strSameTimeFxSystemCode))
                {
                    //１．FX口座情報インスタンスを生成する。
                	l_fxAccInformationUnit = new WEB3FXAccInformationUnit();

                    //２．生成したインスタンスに以下のプロパティをセットする。
                    //○口座番号 = 引数.口座開設結果.getAccountIds()の戻り値第1要素
                    //○コース区分 = 1(1万通貨コース)
                    l_fxAccInformationUnit.fxAccountCode = l_resultInfoCreateUser.getAccountIds().get(0) + "";
                    l_fxAccInformationUnit.fxCourseDiv =
                        WEB3GftTransStatusCourseDivDef.ONE_COSE;

                    //３．ArrayListにプロパティセットしたインスタンスを追加する。
                    l_lisRecord.add(l_fxAccInformationUnit);

                    //４．FX口座情報インスタンスを生成する。
                    l_fxAccInformationUnit = new WEB3FXAccInformationUnit();

                    //５．生成したインスタンスに以下のプロパティをセットする。
                    //○口座番号 = 引数.口座開設結果.getAccountIds()の戻り値第2要素
                    //○コース区分 = 2(10万通貨コース)
                    l_fxAccInformationUnit.fxAccountCode = l_resultInfoCreateUser.getAccountIds().get(1) + "";
                    l_fxAccInformationUnit.fxCourseDiv =
                        WEB3GftTransStatusCourseDivDef.TEN_COSE;

                    //６．ArrayListにプロパティセットしたインスタンスを追加する。
                    l_lisRecord.add(l_fxAccInformationUnit);

                    //７．FX口座情報インスタンスを生成する。
                    l_fxAccInformationUnit = new WEB3FXAccInformationUnit();

                    //８．生成したインスタンスに以下のプロパティをセットする。
                    //○口座番号 = 引数.口座開設結果.getAccountIds()の戻り値第3要素
                    //○コース区分 = 3(CFDコース)
                    l_fxAccInformationUnit.fxAccountCode = l_resultInfoCreateUser.getAccountIds().get(2) + "";
                    l_fxAccInformationUnit.fxCourseDiv =
                        WEB3GftTransStatusCourseDivDef.CFD_COURSE;

                    //９．ArrayListにプロパティセットしたインスタンスを追加する。
                    l_lisRecord.add(l_fxAccInformationUnit);
                }

                //<2-1-2　@引数.会社別FXシステム条件Params.FXシステム区分がnullだった場合、
                //以下処理を実施する。>
                else if (WEB3StringTypeUtility.isEmpty(l_strFxSystemDiv))
                {
                    //１．FX口座情報インスタンスを生成する。
                	l_fxAccInformationUnit = new WEB3FXAccInformationUnit();

                    //２．生成したインスタンスに以下のプロパティをセットする。
                    //○口座番号 = 引数.口座開設結果.getAccountIds()の戻り値第1要素
                    //○コース区分 = 1(1万通貨コース)
                    l_fxAccInformationUnit.fxAccountCode = l_resultInfoCreateUser.getAccountIds().get(0) + "";
                    l_fxAccInformationUnit.fxCourseDiv =
                        WEB3GftTransStatusCourseDivDef.ONE_COSE;

                    //３．ArrayListにプロパティセットしたインスタンスを追加する。
                    l_lisRecord.add(l_fxAccInformationUnit);

                    //４．FX口座情報インスタンスを生成する。
                    l_fxAccInformationUnit = new WEB3FXAccInformationUnit();

                    //５．生成したインスタンスに以下のプロパティをセットする。
                    //○口座番号 = 引数.口座開設結果.getAccountIds()の戻り値第2要素
                    //○コース区分 = 2(10万通貨コース)
                    l_fxAccInformationUnit.fxAccountCode = l_resultInfoCreateUser.getAccountIds().get(1) + "";
                    l_fxAccInformationUnit.fxCourseDiv =
                        WEB3GftTransStatusCourseDivDef.TEN_COSE;

                    //６．ArrayListにプロパティセットしたインスタンスを追加する。
                    l_lisRecord.add(l_fxAccInformationUnit);
                }

                //<2-1-3　@引数.会社別FXシステム条件Params.FXシステム区分==”2”だった場合、
                //以下処理を実施する。>
                else if (WEB3FxSystemDivDef.CFD_SYSTEM.equals(l_strFxSystemDiv))
                {
                    //１．FX口座情報インスタンスを生成する。
                	l_fxAccInformationUnit = new WEB3FXAccInformationUnit();

                    //２．生成したインスタンスに以下のプロパティをセットする。
                    //○口座番号 = 引数.口座開設結果.getAccountIds()の戻り値第1要素
                    //○コース区分 = 3(CFDコース)
                    l_fxAccInformationUnit.fxAccountCode = l_resultInfoCreateUser.getAccountIds().get(0) + "";
                    l_fxAccInformationUnit.fxCourseDiv =
                        WEB3GftTransStatusCourseDivDef.CFD_COURSE;

                    //３．ArrayListにプロパティセットしたインスタンスを追加する。
                    l_lisRecord.add(l_fxAccInformationUnit);
                }
            }
        }

        //2-2　@引数.電文明細.処理区分 == "03"の場合以下処理を行う。
        //引数.追加開設結果 != null かつ、引数.追加開設結果.getAccountId() != nullの場合
        else if (WEB3AdminAioGftOperationDivDef.ADD_ACCOUNT.equals(
            l_strGftOperationDiv))
        {
            //引数.追加開設結果 != null かつ、引数.追加開設結果.getAccountIds() != nullの場合
            if (l_resultInfoAddAccount != null
                && l_resultInfoAddAccount.getAccountId() != null)
            {
                //<2-2-1　@引数.会社別FXシステム条件Params.FXシステム区分がnullだった場合、
                //以下処理を実施する。>
                if (WEB3StringTypeUtility.isEmpty(l_strFxSystemDiv))
                {
                    //１．FX口座情報インスタンスを生成する。
                	l_fxAccInformationUnit = new WEB3FXAccInformationUnit();

                    //２．生成したインスタンスに以下のプロパティをセットする。
                    //○口座番号 = 引数.追加開設結果.getAccountId()の戻り値第1要素
                    //○コース区分 = 1(1万通貨コース)
                    l_fxAccInformationUnit.fxAccountCode = l_resultInfoAddAccount.getAccountId().get(0) + "";
                    l_fxAccInformationUnit.fxCourseDiv =
                        WEB3GftTransStatusCourseDivDef.ONE_COSE;

                    //３．ArrayListにプロパティセットしたインスタンスを追加する。
                    l_lisRecord.add(l_fxAccInformationUnit);

                    //４．FX口座情報インスタンスを生成する。
                    l_fxAccInformationUnit = new WEB3FXAccInformationUnit();

                    //５．生成したインスタンスに以下のプロパティをセットする。
                    //○口座番号 = 引数.追加開設結果.getAccountId()の戻り値第2要素
                    //○コース区分 = 2(10万通貨コース)
                    l_fxAccInformationUnit.fxAccountCode = l_resultInfoAddAccount.getAccountId().get(1) + "";
                    l_fxAccInformationUnit.fxCourseDiv =
                        WEB3GftTransStatusCourseDivDef.TEN_COSE;

                    //６．ArrayListにプロパティセットしたインスタンスを追加する。
                    l_lisRecord.add(l_fxAccInformationUnit);
                }

                //<2-2-2　@引数.会社別FXシステム条件Params.FXシステム区分==”2”だった場合、
                //以下処理を実施する。>
                else if (WEB3FxSystemDivDef.CFD_SYSTEM.equals(l_strFxSystemDiv))
                {
                    //１．FX口座情報インスタンスを生成する。
                	l_fxAccInformationUnit =
                        new WEB3FXAccInformationUnit();

                    //２．生成したインスタンスに以下のプロパティをセットする。
                    //○口座番号 = 引数.追加開設結果.getAccountId()の戻り値第1要素
                    //○コース区分 = 3(CFDコース)
                	l_fxAccInformationUnit.fxAccountCode = l_resultInfoAddAccount.getAccountId().get(0) + "";
                	l_fxAccInformationUnit.fxCourseDiv =
                        WEB3GftTransStatusCourseDivDef.CFD_COURSE;

                    //３．ArrayListにプロパティセットしたインスタンスを追加する。
                    l_lisRecord.add(l_fxAccInformationUnit);
                }
            }
        }

        //3)　@生成したArrayList.toArray()の戻り値を返却する。
        l_fxAccInformationUnits =
            new WEB3FXAccInformationUnit[l_lisRecord.size()];
        l_lisRecord.toArray(l_fxAccInformationUnits);

        log.exiting(STR_METHOD_NAME);
        return l_fxAccInformationUnits;
    }

    /**
     * (get同時開設FXシステムコード)<BR>
     * 同時開設するFXシステムコードを取得する。<BR>
     * <BR>
     * １）証券会社IDを取得する。
     * <BR>
     * ２）以下の条件で、証券会社プリファ@レンステーブルからレコードを取得する。<BR>
     * 　@　@[条件]<BR>
     * 　@　@証券会社ID = １）で取得した証券会社ID<BR>
     * 　@　@プリファ@レンス名 = "gft.accountopen.fxsystemcode"<BR>
     * <BR>
     * ３）レコードが取得できた場合はプリファ@レンスの値を、<BR>
     * 　@　@できなかった場合はnullを取得する。<BR>
     * <BR>
     * ４）取得した値を返却<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getSameTimeFxSystemCode(String l_strInstitutionCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSameTimeFxSystemCode(String)";
        log.entering(STR_METHOD_NAME);

        //拡張アカウントマネージャ取得する
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();

        //１）証券会社IDを取得する。
        String l_strInstitutionId = null;
        try{
            Institution l_Instituion =
                l_accountManager.getInstitution(l_strInstitutionCode);
            l_strInstitutionId = String.valueOf(l_Instituion.getInstitutionId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //２）以下の条件で、証券会社プリファ@レンステーブルからレコードを取得する。
        //[条件]
        //証券会社ID = １）で取得した証券会社ID
        //プリファ@レンス名 = "gft.accountopen.fxsystemcode"
        String l_strWhere = " institution_id = ? and name = ? ";

        String l_strValue = null;
        Object[] l_objValue =
            new Object[]{
                l_strInstitutionId,
                WEB3InstitutionPreferencesNameDef.GFT_ACCOUNTOPEN_FXSYSTEMCODE};

        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    InstitutionPreferencesRow.TYPE,
                    l_strWhere,
                    l_objValue);
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

        //３）レコードが取得できた場合はプリファ@レンスの値を、
        //できなかった場合はnullを取得する。
        if (l_lisRows.size() != 0)
        {
            l_strValue = ((InstitutionPreferencesRow)l_lisRows.get(0)).getValue();
        }

        //４）取得した値を返却
        log.exiting(STR_METHOD_NAME);
        return l_strValue;
    }

    /**
     * (getGFTFXシステムコード一覧)<BR>
     * 外部接続システムコード がGFTの、全てのFXシステムコードを取得する。<BR>
     * <BR>
     * １）　@FXシステムコード取得<BR>
     * 　@会社別FXシステム条件テーブルを以下の条件で検索し、FXシステムコードを取得する。<BR>
     * <BR>
     * [条件]<BR>
     * 　@証券会社コード = 引数.証券会社コード<BR>
     * 　@部店コード = 引数.部店コード<BR>
     * 　@外部接続システムコード = 01：GFT <BR>
     * <BR>
     * ２）　@１）で取得したFXシステムコードをArreyListで返却する。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@return ArrayList
     * @@throws WEB3BaseException
     */
    public ArrayList getGFTFxSystemCodeLists(
        String l_strInstitutionCode,
        String l_strBranchCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getGFTFxSystemCodeLists(String, String)";
        log.entering(STR_METHOD_NAME);

        ArrayList l_lisFxSystemCodes = new ArrayList();
        List l_lisRows = null;
        //1）　@FXシステムコード取得
        //会社別FXシステム条件テーブルを以下の条件で検索し、FXシステムコードを取得する。
        //[条件]
        //証券会社コード = 引数.証券会社コード
        //部店コード = 引数.部店コード
        //外部接続システムコード = 01：GFT
        String l_strWhere =
            " institution_code = ? and branch_code = ? and ext_connect_system_code = ? ";

        Object[] l_sqlValues =
            new Object[]{
                l_strInstitutionCode,
                l_strBranchCode,
                WEB3ExtConnectSystemCodeDef.GFT};

        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    CompFxConditionRow.TYPE,
                    l_strWhere,
                    l_sqlValues);
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

        //2）　@1）で取得したFXシステムコードをArreyListで返却する。
        Iterator l_iterator = l_lisRows.iterator();
        while (l_iterator.hasNext())
        {
            l_lisFxSystemCodes.add(
                ((CompFxConditionRow)l_iterator.next()).getFxSystemCode());
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisFxSystemCodes;
    }

    /**
     * (insert同時口座開設)<BR>
     * GFT結果通知電文の内容でFX顧客テーブル(fx_account)に行のinsertを行う。<BR>
     * <BR>
     * 挿入する行の内容は下記を参照。<BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新<BR>
     * 「FX口座開設_FX顧客テーブル.xls」<BR>
     * ((FX口座開設)FX顧客テーブル_DB更新仕様 (同時開設）)<BR>
     * @@param l_fxGftResultNoticeTelegramUnit - (GFT結果通知電文明細)<BR>
     * GFT結果通知電文明細<BR>
     * @@param l_gftAccountOpenStatusParams - (GFT口座開設状況Params)<BR>
     * GFT口座開設状況Params<BR>
     * @@param l_strSimultaneousFxSystemCode - (同時開設FXシステムコード)<BR>
     * 同時開設FXシステムコード<BR>
     * @@throws WEB3BaseException
     */
    public void insertSimultaneousAccountOpen(
        WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit,
        GftAccountOpenStatusParams l_gftAccountOpenStatusParams,
        String l_strSimultaneousFxSystemCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "insertSimultaneousAccountOpen(WEB3FXGftResultNoticeTelegramUnit, GftAccountOpenStatusParams, String)";
        log.entering(STR_METHOD_NAME);

        //挿入する行の内容は下記を参照
        //【ｘTrade】補足資料.DB更新
        //「FX口座開設_FX顧客テーブル.xls」
        // ((FX口座開設)FX顧客テーブル_DB更新仕様 (同時開設）
        FxAccountParams l_fxAccountParams =
            new FxAccountParams();

        //FX顧客ID
        //FXデータ制御サービスImpl.get新規FX顧客ID(this.証券会社コード, this.部店コード,
        //this.顧客コード、引数.同時開設FXシステムコード)
        String l_strFXAccountID =  this.getNewFXAccountID(
            l_gftAccountOpenStatusParams.getInstitutionCode(),
            l_gftAccountOpenStatusParams.getBranchCode(),
            l_gftAccountOpenStatusParams.getAccountCode(),
            l_strSimultaneousFxSystemCode);

        l_fxAccountParams.setFxAccountId(Long.parseLong(l_strFXAccountID));

        //証券会社コード:引数.GFT結果通知電文明細.会社コード
        l_fxAccountParams.setInstitutionCode(
            l_fxGftResultNoticeTelegramUnit.institutionCode);

        //部店コード:引数.GFT結果通知電文明細.部店コード
        l_fxAccountParams.setBranchCode(
            l_fxGftResultNoticeTelegramUnit.branchCode);

        //FXシステムコード:引数.同時開設FXシステムコード
        l_fxAccountParams.setFxSystemCode(l_strSimultaneousFxSystemCode);

        //顧客コード
        //引数.GFT結果通知電文明細.顧客コード
        l_fxAccountParams.setAccountCode(
            l_fxGftResultNoticeTelegramUnit.accountCode);

        //FX口座区分:1：開設済
        l_fxAccountParams.setFxAccountDiv(WEB3AioAccountDivDef.OPEN_COMPLETE);

        //名前（姓）:GFT口座開設状況Params.名前（姓）
        l_fxAccountParams.setFxLastName(l_gftAccountOpenStatusParams.getLastName());

        //名前（名）:GFT口座開設状況Params.名前（名）
        l_fxAccountParams.setFxFirstName(l_gftAccountOpenStatusParams.getFirstName());

        //FXメールアドレス:引数.GFT結果通知電文明細.メールアドレス
        l_fxAccountParams.setFxMailAddress(l_fxGftResultNoticeTelegramUnit.fxMailAddress);

        //FXログインID:引数.GFT結果通知電文明細.初期ログインID
        l_fxAccountParams.setFxLoginId(Long.parseLong(l_fxGftResultNoticeTelegramUnit.fxFirstLoginId));

        //更新者コード:null
        l_fxAccountParams.setLastUpdater(null);

        //作成日付:
        //現在時刻
        Timestamp l_tsSystemTime = GtlUtils.getSystemTimestamp();
        l_fxAccountParams.setCreatedTimestamp(l_tsSystemTime);

        //更新日付:
        //現在時刻
        l_fxAccountParams.setLastUpdatedTimestamp(l_tsSystemTime);

        try
        {
            WEB3DataAccessUtility.insertRow(l_fxAccountParams);
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

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insert同時口座開設)<BR>
     * GFT結果通知電文の内容でFX顧客テーブル(fx_account)に行のinsertを行う。<BR>
     * <BR>
     * 挿入する行の内容は下記を参照。<BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新<BR>
     * 「管理者・口座開設管理_FX顧客テーブルDB更新仕様.xls」<BR>
     * ((口座開設管理)FX顧客テーブルDB更新仕様 (同時開設))<BR>
     * @@param l_gftAccountOpenStatusParams - (GFT口座開設状況Params)<BR>
     * GFT口座開設状況Params<BR>
     * @@param l_strSimultaneousFxSystemCode - (同時開設FXシステムコード)<BR>
     * 同時開設FXシステムコード<BR>
     * @@param l_strUpdaterCode - (更新者コード)<BR>
     * 更新者コード<BR>
     * @@throws WEB3BaseException
     */
    public void insertSimultaneousAccountOpen(
        GftAccountOpenStatusParams l_gftAccountOpenStatusParams,
        String l_strSimultaneousFxSystemCode,
        String l_strUpdaterCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "insertSimultaneousAccountOpen(GftAccountOpenStatusParams, String, String)";
        log.entering(STR_METHOD_NAME);

        //挿入する行の内容は下記を参照
        //【ｘTrade】補足資料.DB更新
        //管理者・口座開設管理_FX顧客テーブルDB更新仕様.xls」
        // ((口座開設管理)FX顧客テーブルDB更新仕様 (同時開設))
        FxAccountParams l_fxAccountParams =
            new FxAccountParams();

        //FX顧客ID:
        //FXデータ制御サービスImpl.get新規FX顧客ID(this.証券会社コード, this.部店コード,
        //this.顧客コード、同時開設FXシステムコード)
        String l_strFXAccountID =  this.getNewFXAccountID(
            l_gftAccountOpenStatusParams.getInstitutionCode(),
            l_gftAccountOpenStatusParams.getBranchCode(),
            l_gftAccountOpenStatusParams.getAccountCode(),
            l_strSimultaneousFxSystemCode);

        l_fxAccountParams.setFxAccountId(Long.parseLong(l_strFXAccountID));

        //証券会社コード:GFT口座開設状況Params.証券会社コード
        l_fxAccountParams.setInstitutionCode(
            l_gftAccountOpenStatusParams.getInstitutionCode());

        //部店コード:GFT口座開設状況Params.部店コード
        l_fxAccountParams.setBranchCode(
            l_gftAccountOpenStatusParams.getBranchCode());

        //FXシステムコード:同時開設FXシステムコード
        l_fxAccountParams.setFxSystemCode(
            l_strSimultaneousFxSystemCode);

        //顧客コード:
        //GFT口座開設状況Params.顧客コード
        l_fxAccountParams.setAccountCode(
            l_gftAccountOpenStatusParams.getAccountCode());

        //FX口座区分:1：開設済
        l_fxAccountParams.setFxAccountDiv(WEB3AioAccountDivDef.OPEN_COMPLETE);

        //名前（姓）:GFT口座開設状況Params.名前（姓）
        l_fxAccountParams.setFxLastName(l_gftAccountOpenStatusParams.getLastName());

        //名前（名）:GFT口座開設状況Params.名前（名）
        l_fxAccountParams.setFxFirstName(l_gftAccountOpenStatusParams.getFirstName());

        //FXメールアドレス:GFT口座開設状況Params.GFTメールアドレス
        l_fxAccountParams.setFxMailAddress(l_gftAccountOpenStatusParams.getMailAddress());

        //FXログインID:GFT口座開設状況Params.GFTログインID
        l_fxAccountParams.setFxLoginId(Long.parseLong(l_gftAccountOpenStatusParams.getLoginId()));

        //更新者コード
        //引数.更新者コード
        l_fxAccountParams.setLastUpdater(l_strUpdaterCode);

        //作成日付
        //現在時刻
        Timestamp l_tsSystemTime = GtlUtils.getSystemTimestamp();
        l_fxAccountParams.setCreatedTimestamp(l_tsSystemTime);

        //更新日付
        //現在時刻
        l_fxAccountParams.setLastUpdatedTimestamp(l_tsSystemTime);

        try
        {
            WEB3DataAccessUtility.insertRow(l_fxAccountParams);
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

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insertFX口座番号)<BR>
     * GFT口座開設状況Params、FX口座情報の内容より、<BR>
     * FX口座番号テーブル(fx_account_code)に行のinsertを行う。<BR>
     * <BR>
     * 挿入する行の内容は下記を参照。<BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新<BR>
     * 「管理者・口座開設管理_FX口座番号テーブルDB更新仕様.xls<BR>
     * (口座開設管理)FX口座番号テーブルDB更新仕様　@同時口座開設」<BR>
     * @@param l_gftAccountOpenStatusParams - (GFT口座開設状況Params)<BR>
     * GFT口座開設状況Params<BR>
     * @@param l_fxAccInformation - (FX口座情報)<BR>
     * FX口座情報<BR>
     * @@param l_strUpdateCode - (更新者コード)<BR>
     * 更新者コード<BR>
     * @@param l_strSimultaneousFxSystemCode - (同時開設FXシステムコード)<BR>
     * 同時開設FXシステムコード<BR>
     * @@throws WEB3BaseException
     */
    public void insertFXAccountCode(
        GftAccountOpenStatusParams l_gftAccountOpenStatusParams,
        WEB3FXAccInformationUnit l_fxAccInformation,
        String l_strUpdateCode,
        String l_strSimultaneousFxSystemCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "insertFXAccountCode(GftAccountOpenStatusParams, WEB3FXAccInformationUnit, String, String)";
        log.entering(STR_METHOD_NAME);

        if(l_fxAccInformation == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //挿入する行の内容は下記を参照
        //【ｘTrade】補足資料.DB更新
        //「管理者・口座開設管理_FX口座番号テーブルDB更新仕様.xls
        // (口座開設管理)FX口座番号テーブルDB更新仕様　@同時口座開設」
        FxAccountCodeParams l_fxAccountCodeParams =
            new FxAccountCodeParams();

        //証券会社コード:GFT口座開設状況Params.証券会社コード
        l_fxAccountCodeParams.setInstitutionCode(
            l_gftAccountOpenStatusParams.getInstitutionCode());

        //部店コード:GFT口座開設状況Params.部店コード
        l_fxAccountCodeParams.setBranchCode(
            l_gftAccountOpenStatusParams.getBranchCode());

        //FXシステムコード
        //<引数.FX口座情報.コース区分==”1”or”2”の場合>
        //GFT口座開設状況Params.FXシステムコード
        //<引数.FX口座情報.GFTコース区分==”3”の場合>
        //<引数.同時開設FXシステムコード==nullの場合>
        //GFT口座開設状況Params.FXシステムコード
        //<引数.同時開設FXシステムコードがnullでない場合>
        //引数.同時開設FXシステムコード
        if (WEB3GftTransStatusCourseDivDef.ONE_COSE.equals(l_fxAccInformation.fxCourseDiv)
            || WEB3GftTransStatusCourseDivDef.TEN_COSE.equals(l_fxAccInformation.fxCourseDiv))
        {
            l_fxAccountCodeParams.setFxSystemCode(
                l_gftAccountOpenStatusParams.getFxSystemCode());
        }
        else if (WEB3GftTransStatusCourseDivDef.CFD_COURSE.equals(l_fxAccInformation.fxCourseDiv))
        {
            if (l_strSimultaneousFxSystemCode == null)
            {
                l_fxAccountCodeParams.setFxSystemCode(
                    l_gftAccountOpenStatusParams.getFxSystemCode());
            }
            else
            {
                l_fxAccountCodeParams.setFxSystemCode(l_strSimultaneousFxSystemCode);
            }
        }

        //顧客コード
        //GFT口座開設状況Params.顧客コード
        l_fxAccountCodeParams.setAccountCode(
            l_gftAccountOpenStatusParams.getAccountCode());

        //FXコース区分
        //FX口座情報.コース区分
        l_fxAccountCodeParams.setFxCourseDiv(l_fxAccInformation.fxCourseDiv);

        //FX口座番号
        //FX口座情報.口座番号
        l_fxAccountCodeParams.setFxAccountCode(l_fxAccInformation.fxAccountCode);

        //更新者コード
        //引数.更新者コード
        l_fxAccountCodeParams.setLastUpdater(l_strUpdateCode);

        //作成日付
        //現在時刻
        Timestamp l_tsSystemTime = GtlUtils.getSystemTimestamp();
        l_fxAccountCodeParams.setCreatedTimestamp(l_tsSystemTime);

        //更新日付
        //現在時刻
        l_fxAccountCodeParams.setLastUpdatedTimestamp(l_tsSystemTime);

        try
        {
            WEB3DataAccessUtility.insertRow(l_fxAccountCodeParams);
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

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (insertSOAPMessage)<BR>
     * SOAPメッセージ保存テーブルにSOAPメッセージを保存する。<BR>
     * <BR>
     * １）SOAPメッセージ保存Paramsインスタンスを生成する。<BR>
     * <BR>
     * ２）生成したインスタンスに、以下のとおりにプロパティをセットする。<BR>
     * <BR>
     * 部店ID： 引数.部店ID<BR>
     * 接続区分： 引数.接続区分<BR>
     * 送受信区分： 引数.送受信区分<BR>
     * メッセージ： 引数.メッセージ<BR>
     * 作成日付： システムタイムスタンプ<BR>
     * <BR>
     * ３）テーブルにインサートする。<BR>
     * @@param l_lngBranchId - 部店ID
     * @@param l_strConnectDiv - 接続区分
     * @@param l_strSendReceiveDiv - 送受信区分
     * @@param l_strMessage - メッセージ
     * 
     */
    
    public void insertSOAPMessage(
            long l_lngBranchId, 
            String l_strConnectDiv, 
            String l_strSendReceiveDiv,
            String l_strMessage)
    {
        String STR_METHOD_NAME = "insertSOAPMessage(long, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
  	  
        if(String.valueOf(l_lngBranchId) != null && l_strConnectDiv != null &&
        		l_strSendReceiveDiv != null && l_strMessage != null)
        {
   
	        //１）SOAPメッセージ保存Paramsインスタンスを生成する。
	        SoapMessageParams l_soapMessageParams = new SoapMessageParams();
	        
	        //２）生成したインスタンスに、以下のとおりにプロパティをセットする。
	        //部店ID： 引数.部店ID
	        l_soapMessageParams.setBranchId(l_lngBranchId);
	        
	        //接続区分： 引数.接続区分
	        l_soapMessageParams.setConnectDiv(l_strConnectDiv);
	        
	        //送受信区分： 引数.送受信区分
	        l_soapMessageParams.setSendReceiveDiv(l_strSendReceiveDiv);
	        
	        //メッセージ： 引数.メッセージ
	        l_soapMessageParams.setSoapMessage(l_strMessage);
	        
	        //作成日付： システムタイムスタンプ
	        Timestamp l_tsSystemTimestamp = new Timestamp(new Date().getTime());
	        l_soapMessageParams.setCreatedTimestamp(l_tsSystemTimestamp);
	        
	        try
	        {		
	            //insert SOAPメッセージ保存
	            WEB3DataAccessUtility.insertRow(l_soapMessageParams);
	        }
	        catch (DataException l_ex)
	        {
	            log.debug("DBへのアクセスに失敗しました: ", l_ex);
	        }
        
        }
        
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (get変換FXログインID )<BR>
     * <BR>
     * FXログインIDを変換し、返却する。<BR>
     * <BR>
     * １）　@証券会社プリファ@レンスを以下の条件で検索する。<BR>
     * 　@[検索条件]<BR>
     * 　@証券会社ＩＤ = 引数.証券会社ＩＤ<BR>
     * 　@プリファ@レンス名 = fx.fxloginid.change.div<BR>
     * 　@プリファ@レンス名の連番 = 1<BR>
     * <BR>
     * ２）　@FXログインIDの変換を行う。<BR>
     * ２－１）　@証券会社プリファ@レンスParams.プリファ@レンスの値 = 引数.FXシステムコードの場合<BR>
     * <BR>
     * 引数.FXログインID頭文字 + 引数.FXログインID(*)を文字列連結した値を返却する。<BR>
     * <BR>
     * (*)FXログインIDの先頭から6桁目までを使用する。<BR>
     * <BR>
     * ２－２） 上記以外の場合※証券会社プリファ@レンスが取得出来なかった場合も含む<BR>
     * <BR>
     * 引数.FXログインIDを返却する。<BR>
     * <BR>
     * @@param l_lngInstitutionID - (証券会社ID)<BR>
     * 証券会社ID<BR>
     * @@param l_strFxSystemCode - (FXシステムコード)<BR>
     * FXシステムコード<BR>
     * @@param l_strFXLoginFirstChar - (FXログインID頭文字)<BR>
     * FXログインID頭文字<BR>
     * @@param l_lngFXLoginID - (FXログインＩＤ)<BR>
     * FXログインＩＤ<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getChangedFXLoginID(
        long l_lngInstitutionID,
        String l_strFxSystemCode,
        String l_strFXLoginFirstChar,
        long l_lngFXLoginID) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getChangedFXLoginID(long, String, String, long)";
        log.entering(STR_METHOD_NAME);

        //証券会社プリファ@レンスを以下の条件で検索する。
        //[検索条件]
        //証券会社ＩＤ = 引数.証券会社ＩＤ
        //プリファ@レンス名 = fx.fxloginid.change.div
        //プリファ@レンス名の連番 = 1
        InstitutionPreferencesRow l_institutionPreferencesRow = null;
        try
        {
            l_institutionPreferencesRow = InstitutionPreferencesDao.findRowByPk(
                l_lngInstitutionID,
                WEB3InstitutionPreferencesNameDef.FX_FXLOGINID_CHANGE_DIV,
                1);
        }
        catch (DataFindException l_ex)
        {
            //上記以外の場合※証券会社プリファ@レンスが取得出来なかった場合も含む
            //引数.FXログインIDを返却する。
            log.debug("証券会社プリファ@レンスが取得出来なかった場合");

            log.exiting(STR_METHOD_NAME);
            return l_lngFXLoginID + "";
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

        //FXログインIDの変換を行う。
        //証券会社プリファ@レンスParams.プリファ@レンスの値 = 引数.FXシステムコードの場合
        //引数.FXログインID頭文字 + 引数.FXログインID(*)を文字列連結した値を返却する。
        //(*)FXログインIDの先頭から6桁目までを使用する。
        if (WEB3Toolkit.isEquals(l_institutionPreferencesRow.getValue(), l_strFxSystemCode))
        {
            String l_strChangedFXLoginID = "";
            if (l_strFXLoginFirstChar != null)
            {
                l_strChangedFXLoginID = l_strFXLoginFirstChar;
            }

            String l_strFXLoginID = l_lngFXLoginID + "";
            if(l_strFXLoginID.length() > 6)
            {
                l_strFXLoginID = l_strFXLoginID.substring(0, 6);
            }

            l_strChangedFXLoginID = l_strChangedFXLoginID + l_strFXLoginID;

            log.exiting(STR_METHOD_NAME);
            return l_strChangedFXLoginID;
        }
        //上記以外の場合
        //引数.FXログインIDを返却する。
        else
        {
            log.exiting(STR_METHOD_NAME);
            return l_lngFXLoginID + "";
        }
    }
}@


1.2
log
@*** empty log message ***
@
text
@d69 1
d96 2
d192 2
a6116 4
     * 　@　@（3）key   : "weblogic.webservice.transport.https.proxy.host" <BR>
     * 　@　@ 　@  value : 1）で取得した接続先情報の配列第1要素 <BR>
     * 　@　@（4）key   : "weblogic.webservice.transport.https.proxy.port" <BR>
     * 　@　@ 　@  value : 1）で取得した接続先情報の配列第2要素 <BR>
a6122 4
     * 　@　@（3）key   : "weblogic.webservice.transport.http.proxy.host" <BR>
     * 　@　@ 　@  value : 1）で取得した接続先情報の配列第1要素 <BR>
     * 　@　@（4）key   : "weblogic.webservice.transport.http.proxy.port" <BR>
     * 　@　@ 　@  value : 1）で取得した接続先情報の配列第2要素 <BR>
d6172 37
a6208 170
//      String l_logMessage = "";
//      
//      
//      //1）引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.エンドポイント名を分割し、
//      //接続先情報を取得する。
//      //getEndpointName().split(arg0 : String)
//      //[引数]
//      //arg0： ";"
//      String[] l_strEndpointNames = l_rpcParams.getEndpointName().split(";");
//
//      //2）プロキシ設定を行う。
//      //＜外部システムSOAP接続プリファ@レンス（RPC形式）params.エンドポイントの分割数 == 3の場合＞
//      if (l_strEndpointNames.length == 3)
//      {
//          // ＜＜分割データの3件目の文字列に"https"が含まれる場合＞＞
//          //  下記の通りプロパティセットを行う。
//          // （1）key   : "https.proxyHost"
//          // 　@　@ value : 1）で取得した接続先情報の配列第1要素
//          // （2）key   : "https.proxyPort"
//          //  　@  value : 1）で取得した接続先情報の配列第2要素
//          // （3）key   : "weblogic.webservice.transport.https.proxy.host"
//          //  　@  value : 1）で取得した接続先情報の配列第1要素
//          // （4）key   : "weblogic.webservice.transport.https.proxy.port"
//          //  　@  value : 1）で取得した接続先情報の配列第2要素
//          // ＜＜上記の条件以外＞＞
//          //  下記の通りプロパティセットを行う。
//          // （1）key   : "http.proxyHost"
//          //  　@  value : 1）で取得した接続先情報の配列第1要素
//          // （2）key   : "http.proxyPort"
//          //  　@  value : 1）で取得した接続先情報の配列第2要素
//          // （3）key   : "weblogic.webservice.transport.http.proxy.host"
//          //  　@  value : 1）で取得した接続先情報の配列第1要素
//          // （4）key   : "weblogic.webservice.transport.http.proxy.port"
//          //  　@  value : 1）で取得した接続先情報の配列第2要素
//	      if(l_strEndpointNames[2].trim().indexOf("https") >= 0)
//	      {
//	          System.setProperty("https.proxyHost", l_strEndpointNames[0].trim());
//	          System.setProperty("https.proxyPort", l_strEndpointNames[1].trim());
//	      }
//	      else
//	      {
//	          System.setProperty("http.proxyHost", l_strEndpointNames[0].trim());
//	          System.setProperty("http.proxyPort", l_strEndpointNames[1].trim());
//	      }
//
//      
//      }
//      //＜外部システムSOAP接続プリファ@レンス（RPC形式）params.エンドポイントの分割数 == 1の場合＞
//      else if (l_strEndpointNames.length == 1)
//      {
//      }
//      //＜上記の条件以外＞
//      //例外をスローする。
//      else
//      {
//          l_logMessage = 
//              "外部システムSOAP接続プリファ@レンス(RPC形式).エンドポイント名" + 
//              "のセクション数が、異なっています。\n" +
//              "「proxy-host;proxy-port;protocol」でセットしてください。";
//          //外部システム接続エラー
//          throw new WEB3BusinessLayerException(
//              WEB3ErrorCatalog.BUSINESS_ERROR_02398,
//              this.getClass().getName() + "." + STR_METHOD_NAME,
//              l_logMessage
//              );
//      }
//
//      //3）SSLAdapterFactoryを生成し、クライアント証明書をセットする。
//      //（1）引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.ポートタイプ名を分割し、
//      //証明書ファ@イルパスを取得する。
//      //getPortTypeName().split(arg0 : String)
//      //[引数]
//      //arg0： ";"
//      String[] l_strPortTypeNames = l_rpcParams.getPortTypeName().split(";");
//
//      //※外部システムSOAP接続プリファ@レンス（RPC形式）params.ポートタイプ名の分割数 == 2以外の場合、
//      //例外をスローする。
//      if (l_strPortTypeNames.length != 2)
//      {
//          l_logMessage = 
//              "外部システムSOAP接続プリファ@レンス(RPC形式).ポートタイプ名" + 
//              "のセクション数が、異なっています。\n" +
//              "「クライアント証明書のフルパス名;CA証明書のフルパス名」でセットしてください。";
//          //外部システム接続エラー
//          throw new WEB3BusinessLayerException(
//              WEB3ErrorCatalog.BUSINESS_ERROR_02398,
//              this.getClass().getName() + "." + STR_METHOD_NAME,
//              l_logMessage
//              );
//      }
//
//      //（2）SSLAdapterFactoryを生成する。
//      //getDefaultFactory();
//      SSLAdapterFactory l_adapterFactory = SSLAdapterFactory.getDefaultFactory();
//
//      //（3）（2）で生成したSSLAdapterFactoryからWLSSLAdapterを取得する。
//      //getSSLAdapter()
////      WLSSLAdapter l_adapter = (WLSSLAdapter)l_adapterFactory.getSSLAdapter();
//      WLSSLAdapter l_adapter  = new WLSSLAdapter();
//
//      //（4）FileInputStreamインスタンスを生成し、クライアント証明書ファ@イル名のフルパスを格納する。
//      //FileInputStream(arg0 : String);
//      //[引数]
//      //arg0： （1）で取得した証明書ファ@イルパスの配列第1要素
//      try
//      {
//          FileInputStream l_fileInputStream = new FileInputStream(
//              l_strPortTypeNames[0]);
//
//          //（5）（3）で取得したWLSSLAdapterにクライアント証明書及びキーをセットする。
//          //loadLocalIdentity(arg0 : InputStream, arg1 : char[]);
//          //[引数]
//          //arg0： （4）で生成したFileInputStream
//          //arg1： 外部システムSOAP接続プリファ@レンス（RPC形式）params.オペレーション名.toCharArray()
//          l_adapter.loadLocalIdentity(
//              l_fileInputStream,
//              l_rpcParams.getOperationName().toCharArray());
//
//          //（6）（3）で取得したWLSSLAdapterにCA証明書ファ@イル名のフルパスを格納する。
//          //setTrustedCertificatesFile(arg0 : String);
//          //[引数]
//          //arg0： （1）で取得した証明書ファ@イルパスの配列第2要素
//          l_adapter.setTrustedCertificatesFile(l_strPortTypeNames[1]);
//      }
//      catch (FileNotFoundException l_ex)
//      {
//          l_logMessage = 
//              "正しいクライアント証明書が取得できません。";
//          //外部システム接続エラー
//          throw new WEB3BusinessLayerException(
//              WEB3ErrorCatalog.BUSINESS_ERROR_02398,
//              this.getClass().getName() + "." + STR_METHOD_NAME,
//              l_logMessage
//              );
//      }
//      catch (KeyManagementException l_ex)
//      {
//          l_logMessage = 
//              "正しいクライアント証明書が取得できません。";
//          //外部システム接続エラー
//          throw new WEB3BusinessLayerException(
//              WEB3ErrorCatalog.BUSINESS_ERROR_02398,
//              this.getClass().getName() + "." + STR_METHOD_NAME,
//              l_logMessage
//              );
//      }
//      catch (IllegalArgumentException l_ex)
//      {
//          l_logMessage = 
//              "正しいCA証明書が取得できません。";
//          //外部システム接続エラー
//          throw new WEB3BusinessLayerException(
//              WEB3ErrorCatalog.BUSINESS_ERROR_02398,
//              this.getClass().getName() + "." + STR_METHOD_NAME,
//              l_logMessage
//              );
//      }
//
//      //（7）（2）で生成したSSLAdapterFactoryにデフォルトのWLSSLAdapterをセットする。
//      //setDefaultAdapter(arg0 : SSLAdapter);
//      //[引数]
//      //arg0： （3）で取得したWLSSLAdapter
//      l_adapterFactory.setDefaultAdapter(l_adapter);
//
//      //（8）（1）で生成したSSLAdapterFactoryに（7）でセットしたWLSSLAdapterをデフォルト設定にする。
//      //setUseDefaultAdapter(arg0 : boolean);
//      //[引数]
//      //arg0： true
//      l_adapterFactory.setUseDefaultAdapter(true);
		
d6210 70
a6530 2
     * 　@　@　@　@　@　@　@arg0： "weblogic.webservice.rpc.timeoutsecs"<BR>
     * 　@　@　@　@　@　@　@arg1： 引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.接続タイムアウト時間<BR>
d6658 4
a6661 11
            //_setProperty(arg0 : String, arg1 : Object)
            //[引数]
            //arg0： "weblogic.webservice.rpc.timeoutsecs"
            //arg1： 引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.接続タイムアウト時間
//            l_webServiceSoap_Stub._setProperty(
//                "weblogic.webservice.rpc.timeoutsecs",
//                l_rpcParams.getResponseTimeout());
            //1 minute for connection ((BindingProvider)
            l_bp.getRequestContext().put("com.sun.xml.ws.connect.timeout", l_rpcParams.response_timeout);
            //3 minutos for request ((BindingProvider)
            l_bp.getRequestContext().put("com.sun.xml.ws.request.timeout", l_rpcParams.response_timeout);
@


1.1
log
@*** empty log message ***
@
text
@a68 1
import javax.xml.rpc.ServiceException;
d70 2
a71 2
import javax.xml.rpc.handler.HandlerInfo;
import javax.xml.rpc.handler.HandlerRegistry;
a72 2
import jp.co.hitachi.www.TFX.WebService.EntryReceiptIn;
import jp.co.hitachi.www.TFX.WebService.EntryReceiptOut;
d81 1
d93 2
a94 2
import com.gftforex.soap.api.ResultInfoCreateUser;
import com.gftforex.soap.api.ResultInfoAddAccount;
d96 2
d99 1
a99 2
import fx.client.WebServiceSoap_Stub;
import fx.client.WebService_Impl;
a173 2
import weblogic.webservice.client.SSLAdapterFactory;
import weblogic.webservice.client.WLSSLAdapter;
d6175 169
a6343 177
      String l_logMessage = "";
      
      
      //1）引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.エンドポイント名を分割し、
      //接続先情報を取得する。
      //getEndpointName().split(arg0 : String)
      //[引数]
      //arg0： ";"
      String[] l_strEndpointNames = l_rpcParams.getEndpointName().split(";");

      //2）プロキシ設定を行う。
      //＜外部システムSOAP接続プリファ@レンス（RPC形式）params.エンドポイントの分割数 == 3の場合＞
      if (l_strEndpointNames.length == 3)
      {
          // ＜＜分割データの3件目の文字列に"https"が含まれる場合＞＞
          //  下記の通りプロパティセットを行う。
          // （1）key   : "https.proxyHost"
          // 　@　@ value : 1）で取得した接続先情報の配列第1要素
          // （2）key   : "https.proxyPort"
          //  　@  value : 1）で取得した接続先情報の配列第2要素
          // （3）key   : "weblogic.webservice.transport.https.proxy.host"
          //  　@  value : 1）で取得した接続先情報の配列第1要素
          // （4）key   : "weblogic.webservice.transport.https.proxy.port"
          //  　@  value : 1）で取得した接続先情報の配列第2要素
          // ＜＜上記の条件以外＞＞
          //  下記の通りプロパティセットを行う。
          // （1）key   : "http.proxyHost"
          //  　@  value : 1）で取得した接続先情報の配列第1要素
          // （2）key   : "http.proxyPort"
          //  　@  value : 1）で取得した接続先情報の配列第2要素
          // （3）key   : "weblogic.webservice.transport.http.proxy.host"
          //  　@  value : 1）で取得した接続先情報の配列第1要素
          // （4）key   : "weblogic.webservice.transport.http.proxy.port"
          //  　@  value : 1）で取得した接続先情報の配列第2要素
	      if(l_strEndpointNames[2].trim().indexOf("https") >= 0)
	      {
	          System.setProperty("https.proxyHost", l_strEndpointNames[0].trim());
	          System.setProperty("https.proxyPort", l_strEndpointNames[1].trim());
	          System.setProperty("weblogic.webservice.transport.https.proxy.host",
	              l_strEndpointNames[0].trim());
	          System.setProperty("weblogic.webservice.transport.https.proxy.port",
	              l_strEndpointNames[1].trim());
	      }
	      else
	      {
	          System.setProperty("http.proxyHost", l_strEndpointNames[0].trim());
	          System.setProperty("http.proxyPort", l_strEndpointNames[1].trim());
	          System.setProperty("weblogic.webservice.transport.http.proxy.host",
	              l_strEndpointNames[0].trim());
	          System.setProperty("weblogic.webservice.transport.http.proxy.port",
	              l_strEndpointNames[1].trim());
	      }

      
      }
      //＜外部システムSOAP接続プリファ@レンス（RPC形式）params.エンドポイントの分割数 == 1の場合＞
      else if (l_strEndpointNames.length == 1)
      {
      }
      //＜上記の条件以外＞
      //例外をスローする。
      else
      {
          l_logMessage = 
              "外部システムSOAP接続プリファ@レンス(RPC形式).エンドポイント名" + 
              "のセクション数が、異なっています。\n" +
              "「proxy-host;proxy-port;protocol」でセットしてください。";
          //外部システム接続エラー
          throw new WEB3BusinessLayerException(
              WEB3ErrorCatalog.BUSINESS_ERROR_02398,
              this.getClass().getName() + "." + STR_METHOD_NAME,
              l_logMessage
              );
      }

      //3）SSLAdapterFactoryを生成し、クライアント証明書をセットする。
      //（1）引数.外部システムSOAP接続プリファ@レンス（RPC形式）params.ポートタイプ名を分割し、
      //証明書ファ@イルパスを取得する。
      //getPortTypeName().split(arg0 : String)
      //[引数]
      //arg0： ";"
      String[] l_strPortTypeNames = l_rpcParams.getPortTypeName().split(";");

      //※外部システムSOAP接続プリファ@レンス（RPC形式）params.ポートタイプ名の分割数 == 2以外の場合、
      //例外をスローする。
      if (l_strPortTypeNames.length != 2)
      {
          l_logMessage = 
              "外部システムSOAP接続プリファ@レンス(RPC形式).ポートタイプ名" + 
              "のセクション数が、異なっています。\n" +
              "「クライアント証明書のフルパス名;CA証明書のフルパス名」でセットしてください。";
          //外部システム接続エラー
          throw new WEB3BusinessLayerException(
              WEB3ErrorCatalog.BUSINESS_ERROR_02398,
              this.getClass().getName() + "." + STR_METHOD_NAME,
              l_logMessage
              );
      }

      //（2）SSLAdapterFactoryを生成する。
      //getDefaultFactory();
      SSLAdapterFactory l_adapterFactory = SSLAdapterFactory.getDefaultFactory();

      //（3）（2）で生成したSSLAdapterFactoryからWLSSLAdapterを取得する。
      //getSSLAdapter()
//      WLSSLAdapter l_adapter = (WLSSLAdapter)l_adapterFactory.getSSLAdapter();
      WLSSLAdapter l_adapter  = new WLSSLAdapter();

      //（4）FileInputStreamインスタンスを生成し、クライアント証明書ファ@イル名のフルパスを格納する。
      //FileInputStream(arg0 : String);
      //[引数]
      //arg0： （1）で取得した証明書ファ@イルパスの配列第1要素
      try
      {
          FileInputStream l_fileInputStream = new FileInputStream(
              l_strPortTypeNames[0]);

          //（5）（3）で取得したWLSSLAdapterにクライアント証明書及びキーをセットする。
          //loadLocalIdentity(arg0 : InputStream, arg1 : char[]);
          //[引数]
          //arg0： （4）で生成したFileInputStream
          //arg1： 外部システムSOAP接続プリファ@レンス（RPC形式）params.オペレーション名.toCharArray()
          l_adapter.loadLocalIdentity(
              l_fileInputStream,
              l_rpcParams.getOperationName().toCharArray());

          //（6）（3）で取得したWLSSLAdapterにCA証明書ファ@イル名のフルパスを格納する。
          //setTrustedCertificatesFile(arg0 : String);
          //[引数]
          //arg0： （1）で取得した証明書ファ@イルパスの配列第2要素
          l_adapter.setTrustedCertificatesFile(l_strPortTypeNames[1]);
      }
      catch (FileNotFoundException l_ex)
      {
          l_logMessage = 
              "正しいクライアント証明書が取得できません。";
          //外部システム接続エラー
          throw new WEB3BusinessLayerException(
              WEB3ErrorCatalog.BUSINESS_ERROR_02398,
              this.getClass().getName() + "." + STR_METHOD_NAME,
              l_logMessage
              );
      }
      catch (KeyManagementException l_ex)
      {
          l_logMessage = 
              "正しいクライアント証明書が取得できません。";
          //外部システム接続エラー
          throw new WEB3BusinessLayerException(
              WEB3ErrorCatalog.BUSINESS_ERROR_02398,
              this.getClass().getName() + "." + STR_METHOD_NAME,
              l_logMessage
              );
      }
      catch (IllegalArgumentException l_ex)
      {
          l_logMessage = 
              "正しいCA証明書が取得できません。";
          //外部システム接続エラー
          throw new WEB3BusinessLayerException(
              WEB3ErrorCatalog.BUSINESS_ERROR_02398,
              this.getClass().getName() + "." + STR_METHOD_NAME,
              l_logMessage
              );
      }

      //（7）（2）で生成したSSLAdapterFactoryにデフォルトのWLSSLAdapterをセットする。
      //setDefaultAdapter(arg0 : SSLAdapter);
      //[引数]
      //arg0： （3）で取得したWLSSLAdapter
      l_adapterFactory.setDefaultAdapter(l_adapter);

      //（8）（1）で生成したSSLAdapterFactoryに（7）でセットしたWLSSLAdapterをデフォルト設定にする。
      //setUseDefaultAdapter(arg0 : boolean);
      //[引数]
      //arg0： true
      l_adapterFactory.setUseDefaultAdapter(true);
d6687 1
a6687 1
        WebServiceSoap_Stub l_webServiceSoap_Stub = null;
a6688 2
        try
        {
d6691 1
a6691 1
            WebService service = new WebService_Impl();
d6698 1
a6698 1
                (WebServiceSoap_Stub)service.getWebServiceSoap(
d6721 3
a6723 3
            l_webServiceSoap_Stub._setProperty(
                "javax.xml.rpc.service.endpoint.address",
                l_strProtocolStr);
d6730 7
a6736 3
            l_webServiceSoap_Stub._setProperty(
                "weblogic.webservice.rpc.timeoutsecs",
                l_rpcParams.getResponseTimeout());
d6745 1
a6745 1
                EntryReceiptIn l_entryReceiptIn = new EntryReceiptIn();
d6778 2
d6781 5
a6785 20
                //(A) Web サービスポートの修飾名を格納するオブジェクトを作成
                QName portName = new QName( l_rpcParams.target_namespace_name, l_rpcParams.service_name);

                //(B) HandlerRegistry オブジェクトを作成
                HandlerRegistry registry = service.getHandlerRegistry();
                
                //(C) HandlerInfoに設定するconfig(:Map)データの作成
                HashMap map = new HashMap();
                
                String branch_id_str = String.valueOf(l_rpcParams.branch_id);
                
                map.put("BranchId",branch_id_str);
                map.put("ConnectDiv",l_rpcParams.connect_div);

                //(D) SOAPメッセージのハンドラ チェーン(:ArrayList)生成
                List handlerList = new ArrayList();
                handlerList.add( new HandlerInfo( WEB3FXSOAPMsgHandler.class, map, null ) );

                //(E) ハンドラ チェーンを登録 //
                registry.setHandlerChain( portName, handlerList ); 
a6796 46
        }
        catch (RemoteException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);

            //接続タイムアウトエラー
            if (l_ex.getCause() instanceof SocketTimeoutException)
            {
                // 外部システム接続エラー
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02398,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            // 外部システム接続エラー
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02398,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (ServiceException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            // 致命的なシステムエラー
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (IOException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            // 致命的なシステムエラー
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
d7250 1
a7250 1
                    l_fxAccInformationUnit.fxAccountCode = l_resultInfoCreateUser.getAccountIds()[0] + "";
d7263 1
a7263 1
                    l_fxAccInformationUnit.fxAccountCode = l_resultInfoCreateUser.getAccountIds()[1] + "";
d7276 1
a7276 1
                    l_fxAccInformationUnit.fxAccountCode = l_resultInfoCreateUser.getAccountIds()[2] + "";
d7294 1
a7294 1
                    l_fxAccInformationUnit.fxAccountCode = l_resultInfoCreateUser.getAccountIds()[0] + "";
d7307 1
a7307 1
                    l_fxAccInformationUnit.fxAccountCode = l_resultInfoCreateUser.getAccountIds()[1] + "";
d7325 1
a7325 1
                    l_fxAccInformationUnit.fxAccountCode = l_resultInfoCreateUser.getAccountIds()[0] + "";
d7354 1
a7354 1
                    l_fxAccInformationUnit.fxAccountCode = l_resultInfoAddAccount.getAccountId()[0] + "";
d7367 1
a7367 1
                    l_fxAccInformationUnit.fxAccountCode = l_resultInfoAddAccount.getAccountId()[1] + "";
d7386 1
a7386 1
                	l_fxAccInformationUnit.fxAccountCode = l_resultInfoAddAccount.getAccountId()[0] + "";
@

