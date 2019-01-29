head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeCsvUploadDataModel.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : CSVファ@イルデータモデル(WEB3GentradeCsvUploadDataModel.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/07/28 石 炎(中訊) 新規作成
Revesion History    : 2005/07/08 孟 東(中訊) saveAllUploadStop()を追加
*/

package webbroker3.gentrade;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;             
import com.fitechlabs.xtrade.kernel.data.DataQueryException;              
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.AdministratorUploadDao;
import webbroker3.gentrade.data.AdministratorUploadParams;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.gentrade.data.AdministratorUploadTempParams;
import webbroker3.gentrade.data.AdministratorUploadTempRow;
import webbroker3.util.WEB3LogUtility;

/**
 * （CsvUploadDataModel）<BR>
 * CSVファ@イルデータモデルクラス。<BR>
 * CSVアップロードにて使用する。<BR>
 * <BR>
 * 使用前提条件<BR>
 * 　@・サービスインタセプタにて、取引時間管理オブジェクトをThreadLocal <BR>
 *     に格納していること。<BR>
 * 　@・ファ@イルフォーマットは以下の通りとする。<BR>
 * <BR>
 * [CSVファ@イルフォーマットについて] <BR>
 * 1行目（index=0）はキーヘッダと認識する。<BR>
 * 3行目（index=2）以降を明細行と認識する。<BR>
 * <BR>
 * --- CSV upload file sample---------------<BR>
 * 2004/06/21 16:00:03,86010,(株)大和証券グループ本社<BR>
 * 625,2512211,増田　@哲也,5000<BR>
 * 624,2412339,森田　@正樹,5500<BR>
 * 610,2110991,齋藤　@貴弥,2000<BR>
 * 610,2121400,羽賀　@輝明,2500<BR>
 * --- CSV upload file sample---------------<BR>
 * <BR>
 */
public abstract class WEB3GentradeCsvUploadDataModel
    extends WEB3GentradeCsvDataModel
{

    /**
     * ログ出力オブジェクト。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeCsvUploadDataModel.class);
        
    /**
     * (アップロードＩＤ) <BR>
     * <BR>
     * ※　@（管理者共通）アップロードテーブルのPK<BR>
     */
    protected long administratorUploadId;

    /**
     * (isカラムヘッダ行出力) <BR>
     * falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 40F5048102D7
     */
    public boolean isColumnHeadOutput()
    {
        //falseを返却する。
        return false;
    }

    /**
     * (getアップロードファ@イルＩＤ) <BR>
     * アップロードファ@イルＩＤを返却する。<BR>
     * <BR>
     * ※（管理者共通）アップロードテーブル.アップロードファ@イルＩＤに<BR>
     * 格納する文字列<BR>
     * @@return java.lang.String
     * @@roseuid 40F2030100DC
     */
    public abstract String getUploadFileId();

    /**
     * (get銘柄タイプ)<BR>
     * 銘柄タイプを返却する。<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum
     * @@roseuid 40F38DD7006C
     */
    public abstract ProductTypeEnum getProductType();
    
    /**
     * (get証券会社コード) <BR>
     * ThreadLocalより証券会社コードを取得する。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute(<BR>
     * 　@　@取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * 　@　@)<BR>
     * にて、取引時間コンテキストを取得する。 <BR>
     * <BR>
     * 取引時間コンテキスト.get証券会社コード()を返却する。<BR>
     * @@return java.lang.String
     * @@roseuid 40F3CEE60190
     */
    public String getInstitutionCode()
    {
        final String STR_METHOD_NAME = "getInstitutionCode()";
        log.entering(STR_METHOD_NAME);
        
        //取引時間コンテキストの取得
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        
        //取引時間コンテキスト.get証券会社コード()を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_clendarContext.getInstitutionCode();
    }

    /**
     * (get部店コード) <BR>
     * ThreadLocalより部店コードを取得する。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute( <BR>
     * 　@　@取引時間管理.TRADING_CAL_CONTEXT_PATH  <BR>
     * 　@　@) <BR>
     * にて、取引時間コンテキストを取得する。 <BR>
     * <BR>
     * 取引時間コンテキスト.get部店コード()を返却する。<BR>
     * @@return java.lang.String
     * @@roseuid 40F3CF6A0123
     */
    public String getBranchCode()
    {
        final String STR_METHOD_NAME = "getBranchCode()";
        log.entering(STR_METHOD_NAME);
        
        //取引時間コンテキストの取得
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        
        //取引時間コンテキスト.get部店コード()を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_clendarContext.getBranchCode();
    }

    /**
     * (validate同時アップロード) <BR>
     * 他プロセスが起動中でないかをチェックする。<BR>
     * （証券会社，部店，銘柄タイプにて同時アップロードを禁止する）<BR>
     * <BR>
     * 以下の条件で「（管理者共通）アップロードテーブル」を検索する。<BR>
     * <BR>
     * [条件]<BR>
     * （管理者共通）アップロードテーブル.証券会社コード = <BR>
     * this.get証券会社コード()　@And <BR>
     * （管理者共通）アップロードテーブル.部店コード = this.get部店コード() And <BR>
     * （管理者共通）アップロードテーブル.銘柄タイプ = this.get銘柄タイプ() And <BR>
     * （管理者共通）アップロードテーブル.アップロード終了日時 = null <BR>
     * <BR>
     * ○　@（アップロードＩＤ == null）の場合 <BR>
     * 　@－行が取得できれば、他のアップロードプロセスが起動中であると <BR>
     * 判断し、例外をスローする。<BR>
     * class    : WEB3SystemLayerException<BR>
     * tag      : SYSTEM_ERROR_80024<BR>
     * <BR>
     * ○　@（アップロードＩＤ != null）の場合 <BR>
     * 　@－別プロセスの行(*1)が取得できれば、<BR>
     *      他のアップロードプロセスが起動中であると判断し、<BR>
     *      例外をスローする。<BR>
     * class    : WEB3SystemLayerException<BR>
     * tag      : SYSTEM_ERROR_80024<BR>
     * <BR>
     * 　@　@(*1) 別プロセスの行の判定<BR>
     * 　@　@　@（管理者共通）アップロードテーブル.アップロードＩＤ != 
     * アップロードＩＤ<BR>
     * @@param l_administratorUploadId - アップロードＩＤ<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40F390E702ED
     */
    public void validateSameTimeUpload(Long l_administratorUploadId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateSameTimeUpload(Long)";
        log.entering(STR_METHOD_NAME);

        List l_lisRecords;
        String l_strInstitutionCode = null;
        String l_strBranchCode = null;
        ProductTypeEnum l_productTypeEnum = null;
                    
        try
        {
            l_strInstitutionCode = this.getInstitutionCode();
            l_strBranchCode = this.getBranchCode();
            l_productTypeEnum = this.getProductType();

            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? "); //証券会社コード
            l_sbWhere.append(" and branch_code = ? "); //部店ID
            l_sbWhere.append(" and product_type = ? "); //銘柄タイプ
            l_sbWhere.append(" and upload_end_timestamp is null "); //アップロード終了日時

            Object[] l_objAdministratorUploadWhere =
                { l_strInstitutionCode, //証券会社コード
                l_strBranchCode, //部店ID
                l_productTypeEnum //銘柄タイプ
            };

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords =
                l_queryProcessor.doFindAllQuery(
                    AdministratorUploadRow.TYPE,
                    l_sbWhere.toString(),
                    l_objAdministratorUploadWhere);
        }
        catch (DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        //（アップロードＩＤ == null）の場合
        if(l_administratorUploadId == null)
        {
            if (l_lisRecords.size() > 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01969,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        else//（アップロードＩＤ != null）の場合
        {
            if(l_lisRecords.size() == 0)
            {
                return;
            }
            if (l_lisRecords.size() > 1)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "（管理者共通）アップロードテーブル検索： 件数 > 1");
            }
            //get （管理者共通）アップロードテーブル.アップロードＩＤ
            AdministratorUploadRow l_row = (AdministratorUploadRow)l_lisRecords.get(0);
            long l_lngAdministratorUploadId = l_row.getAdministratorUploadId();
            //別プロセスの行の判定
            if (l_administratorUploadId.longValue() != l_lngAdministratorUploadId)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01969,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
        }
   
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (saveアップロード開始) <BR>
     * アップロードテーブルに新規行を挿入し、アップロードＩＤを返却する。<BR>
     * <BR>
     * １）　@行オブジェクト生成<BR>
     * 　@（管理者共通）アップロードテーブルParamsを生成し、<BR>
     *   NotNull項目以外の各項目をNullで初期化する。<BR>
     * <BR>
     * 　@※（管理者共通）アップロードテーブルParamsクラスは<BR>
     *   DDLより自動生成する。<BR>
     * <BR>
     * ２）　@以下の通り、行オブジェクトに値をセットする。<BR>
     * <BR>
     * 　@アップロードＩＤ = （新規採番）(*1)<BR>
     * 　@証券会社コード = this.get証券会社コード()<BR>
     * 　@部店コード = this.get部店コード()<BR>
     * 　@銘柄タイプ = this.get銘柄タイプ()<BR>
     * 　@アップロードファ@イルＩＤ = this.getアップロードファ@イルＩＤ()<BR>
     * 　@アップロード開始日時 = GtlUtils.getSystemTimestamp()<BR>
     * 　@更新者コード = 更新者コード<BR>
     * 　@データキー = 引数.データキー<BR>
     * 　@備考１ = 引数.備考１<BR>
     * 　@備考２ = 引数.備考２<BR>
     * 　@備考３ = 引数.備考３<BR>
     * <BR>
     * 　@(*1)　@アップロードＩＤ新規採番<BR>
     * 　@（管理者共通）アップロードテーブルDAO.newPkValue()メソッド<BR>
     *   にて取得する。<BR>
     * 　@※（管理者共通）アップロードテーブルDAOクラスは<BR>
     *   DDLより自動生成する。<BR>
     * <BR>
     * ３）　@TransactionCallback生成<BR>
     * 　@アップロードTransactionCallbackクラスを生成し、<BR>
     *   ２）で作成した行オブジェクトをプロパティにセットする。<BR>
     * <BR>
     * ４）　@DB-insert<BR>
     * 　@QueryProcessor.doTransaction()にてDB-insertを実行する。<BR>
     * <BR>
     * 　@[doTransaction()に指定する引数]<BR>
     * 　@トランザクション属性：　@TX_CREATE_NEW <BR>
     * 　@トランザクションコールバック：　@４）で生成したTransactionCallback<BR>
     * <BR>
     * ５）　@アップロードＩＤセット<BR>
     * 　@this.アップロードＩＤに新規採番したアップロードＩＤ(*1)をセットする。<BR>
     * @@param l_lngUploadKey - データキー <BR>
     * 「（管理者共通）アップロード管理テーブル」.データキーに更新する内容。<BR>
     * 
     * @@param l_strNote1 - 備考１ <BR>
     * 「（管理者共通）アップロード管理テーブル」.備考１に更新する内容。<BR>
     * 
     * @@param l_strNote2 - 備考２ <BR>
     * 「（管理者共通）アップロード管理テーブル」.備考２に更新する内容。<BR>
     * 
     * @@param l_strNote3 - 備考３ <BR>
     * 「（管理者共通）アップロード管理テーブル」.備考３に更新する内容。<BR>
     * 
     * @@param l_strLastUpdater - 更新者コード <BR>
     * 
     * @@return long
     * @@throws WEB3SystemLayerException
     * @@roseuid 40F204100205
     */
    public long saveUpLoadStart(
        long l_lngUploadKey,
        String l_strNote1,
        String l_strNote2,
        String l_strNote3,
        String l_strLastUpdater)
        throws WEB3SystemLayerException
    {

        final String STR_METHOD_NAME = "saveUpLoadStart(long,String,String,String,String)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //１）　@行オブジェクト生成
            //（管理者共通）アップロードテーブルParamsを生成し、
            //NotNull項目以外の各項目をNullで初期化する。
            AdministratorUploadParams l_administratorUploadParms = 
                new AdministratorUploadParams();
            
            //２）　@以下の通り、行オブジェクトに値をセットする。
            
            //* 　@アップロードＩＤ = （新規採番）
            long l_lngNewPkValue = AdministratorUploadDao.newPkValue();
            l_administratorUploadParms.setAdministratorUploadId(l_lngNewPkValue);
            
            //* 　@証券会社コード = this.get証券会社コード()
            l_administratorUploadParms.setInstitutionCode(this.getInstitutionCode());
            
            //* 　@部店コード = this.get部店コード()
            l_administratorUploadParms.setBranchCode(this.getBranchCode());
            
            //* 　@銘柄タイプ = this.get銘柄タイプ()
            l_administratorUploadParms.setProductType(this.getProductType());
            
            //* 　@アップロードファ@イルＩＤ = this.getアップロードファ@イルＩＤ()
            l_administratorUploadParms.setUploadFileId(this.getUploadFileId());
            
            //* 　@アップロード開始日時 = GtlUtils.getSystemTimestamp()
            Timestamp l_tsUploadStartTimestamp = GtlUtils.getSystemTimestamp();
            l_administratorUploadParms.setUploadStartTimestamp(l_tsUploadStartTimestamp);
            
            //* 　@アップロード終了日時 = null
            l_administratorUploadParms.setUploadEndTimestamp(null);
            
            //* 　@メッセージコード = null
            l_administratorUploadParms.setMessageCode(null);
            
            //* 　@アップロード件数 = null
            l_administratorUploadParms.setUploadCount(null);
            
            //* 　@更新者コード = 更新者コード
            l_administratorUploadParms.setLastUpdater(l_strLastUpdater);
            
            //* 　@データキー = 引数.データキー
            l_administratorUploadParms.setUploadKey(l_lngUploadKey);
            
            //* 　@備考１ = 引数.備考１
            l_administratorUploadParms.setNote1(l_strNote1);
            //* 　@備考２ = 引数.備考２
            l_administratorUploadParms.setNote2(l_strNote2);
            //* 　@備考３ = 引数.備考３
            l_administratorUploadParms.setNote3(l_strNote3);
        
            //３）　@TransactionCallback生成
            UploadTransactionCallback l_uploadTransactionCallback = 
                new UploadTransactionCallback();
            //２）で作成した行オブジェクトをプロパティにセットする。
            l_uploadTransactionCallback.administratorUploadParams = l_administratorUploadParms;
            
            //４）　@DB-insert
            //QueryProcessor.doTransaction()にてDB-insertを実行する。
            QueryProcessor l_queryProcessor =
                Processors.getDefaultProcessor();
            l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_uploadTransactionCallback
                );
            
            //５）　@アップロードＩＤセット
            this.administratorUploadId = l_lngNewPkValue;
            
            //アップロードＩＤを返却する。
            log.exiting(STR_METHOD_NAME);
            return administratorUploadId;
        }
        catch (DataException de)
        {
            log.debug(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
    }

    /**
     * (saveアップロード終了) <BR>
     * 指定のアップロード行にアップロード終了情報を更新する。<BR>
     * <BR>
     * 　@this.getアップロードＩＤ()に該当する行について<BR>
     * 以下の通り、（管理者共通）アップロードテーブルを更新する。<BR>
     * <BR>
     * 　@アップロード終了日時 = GtlUtils.getSystemTimestamp() <BR>
     * 　@アップロード件数 = this.get明細行数() <BR>
     * @@throws WEB3SystemLayerException
     * @@roseuid 40F20AFB01A7
     */
    public void saveUploadEnd() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "saveUploadEnd()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //this.getアップロードＩＤ()に該当する行について
            //以下の通り、（管理者共通）アップロードテーブルを更新する。
            AdministratorUploadRow l_administratorUploadRow = 
                (AdministratorUploadRow)
                AdministratorUploadDao.findRowByAdministratorUploadId(this.getAdministratorUploadId());
            
            if(l_administratorUploadRow == null)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            AdministratorUploadParams l_administratorUploadParams = 
                new AdministratorUploadParams(l_administratorUploadRow);

            //アップロード終了日時 = GtlUtils.getSystemTimestamp()
            //アップロード件数 = this.get明細行数()
            Timestamp l_tsSystemTime = GtlUtils.getSystemTimestamp();
            l_administratorUploadParams.setUploadEndTimestamp(l_tsSystemTime);
            l_administratorUploadParams.setUploadCount(this.getRowCount());
            
            QueryProcessor l_queryProcessor =
                 Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_administratorUploadParams);
        }
        catch (DataException de)
        {
            log.debug(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (saveアップロードエラー) <BR>
     * 該当アップロード行にアップロードエラー情報を更新する。<BR>
     * <BR>
     * 　@this.getアップロードＩＤ()に該当する行について<BR>
     * 以下の通り、（管理者共通）アップロードテーブルを更新する。<BR>
     * <BR>
     * 　@アップロード終了日時 = GtlUtils.getSystemTimestamp()<BR>
     * 　@メッセージコード = ErrorInfo.getErrorCode()<BR>
     * 　@アップロード件数 = 0<BR>
     * @@param l_errorInfo - エラー情報 <BR>
     * @@throws WEB3SystemLayerException
     * @@roseuid 40F4DE0803C2
     */
    public void saveUploadError(ErrorInfo l_errorInfo) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "saveUploadError(ErrorInfo)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //this.getアップロードＩＤ()に該当する行について
            //以下の通り、（管理者共通）アップロードテーブルを更新する。
            AdministratorUploadRow l_administratorUploadRow = 
                (AdministratorUploadRow)
                AdministratorUploadDao.findRowByAdministratorUploadId(this.getAdministratorUploadId());
            
            if(l_administratorUploadRow == null)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            AdministratorUploadParams l_administratorUploadParams = 
                new AdministratorUploadParams(l_administratorUploadRow);
                
            //アップロード終了日時 = GtlUtils.getSystemTimestamp()
            //メッセージコード = ErrorInfo.getErrorCode()
            //アップロード件数 = 0
            Timestamp l_tsSystemTime = GtlUtils.getSystemTimestamp();
            l_administratorUploadParams.setUploadEndTimestamp(l_tsSystemTime);
            l_administratorUploadParams.setMessageCode(l_errorInfo.getErrorCode());
            l_administratorUploadParams.setUploadCount(0);
            
            UploadErrorTransactionCallback l_uploadErrorTransactionCallback = 
                new UploadErrorTransactionCallback();
            l_uploadErrorTransactionCallback.administratorUploadParams = l_administratorUploadParams;
            //QueryProcessor.doTransaction()にてDB-insertを実行する。
            QueryProcessor l_queryProcessor =
                Processors.getDefaultProcessor();
            l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_uploadErrorTransactionCallback
                );
        }
        catch (DataException de)
        {
            log.debug(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (saveアップロード中止) <BR>
     * 該当アップロード行にアップロード中止を更新する。<BR>
     * <BR>
     * 　@this.getアップロードＩＤ()に該当する行について<BR>
     * 以下の通り、（管理者共通）アップロードテーブルを更新する。<BR>
     * <BR>
     * 　@アップロード終了日時 = GtlUtils.getSystemTimestamp()<BR>
     * 　@アップロード件数 = 0<BR>
     * <BR>
     *  ※該当データがなくても、例外を上位にスローしない。<BR>
     * @@throws WEB3SystemLayerException
     * @@roseuid 40F77D980057
     */
    public void saveUploadStop() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "saveUploadStop()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //this.getアップロードＩＤ()に該当する行について
            //以下の通り、（管理者共通）アップロードテーブルを更新する。
            AdministratorUploadRow l_administratorUploadRow = 
                (AdministratorUploadRow)
                AdministratorUploadDao.findRowByAdministratorUploadId(this.getAdministratorUploadId());
            
            //該当データがなくても、例外を上位にスローしない。
            if(l_administratorUploadRow == null)
            {
                return;
            }
            
            AdministratorUploadParams l_administratorUploadParams = 
                new AdministratorUploadParams(l_administratorUploadRow);

            //アップロード終了日時 = GtlUtils.getSystemTimestamp()
            //アップロード件数 = 0
            Timestamp l_tsSystemTime = GtlUtils.getSystemTimestamp();
            l_administratorUploadParams.setUploadEndTimestamp(l_tsSystemTime);
            l_administratorUploadParams.setUploadCount(0);
            
            QueryProcessor l_queryProcessor =
                 Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_administratorUploadParams);
        }
        catch (DataException de)
        {
            log.debug(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (saveアップロードTemp) <BR>
     * アップロードファ@イル行を<BR>
     * 「（管理者共通）アップロードテンポラリ」テーブルに更新する。<BR>
     * <BR>
     * this.getCSVファ@イル行()にて、CSVファ@イル行[]を取得する。<BR>
     * 取得したCSVファ@イル行[]各要素について、次の通り<BR>
     * 「（管理者共通）アップロードテンポラリ」テーブルに<BR>
     * 行を挿入（insert）する。<BR>
     * <BR>
     * 　@アップロードＩＤ = this.getアップロードＩＤ()<BR>
     * 　@行番号 = index<BR>
     * 　@CSV行 = CSVファ@イル行[index]<BR>
     * 　@更新日時 = TradingSystem.getSystemTimestamp()<BR>
     * <BR>
     * this.get明細行数()を返却する。<BR>
     * @@return int
     * @@throws WEB3SystemLayerException
     * @@roseuid 40F216DE00AD
     */
    public int saveUploadTemp() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "saveUploadTemp()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            //アップロードファ@イル行を
            //「（管理者共通）アップロードテンポラリ」テーブルに更新する。
            String[] l_csvFileLines = this.getCsvFileLines();
            int l_intLength = l_csvFileLines.length;
            for (int i = 0; i < l_intLength; i++)
            {
                AdministratorUploadTempParams l_administratorUploadTempParms = 
                    new AdministratorUploadTempParams();
                //アップロードＩＤ = this.getアップロードＩＤ()
                l_administratorUploadTempParms.setAdministratorUploadId(this.getAdministratorUploadId());
                //行番号 = index
                l_administratorUploadTempParms.setLineNumber(i);
                //CSV行 = CSVファ@イル行[index]
                l_administratorUploadTempParms.setCsvLineValue(l_csvFileLines[i]);
                //更新日時 = TradingSystem.getSystemTimestamp()
                // Timestamp設定
                FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                TradingSystem l_tradingSys = l_finApp.getTradingSystem();
                java.sql.Timestamp l_processTime = l_tradingSys.getSystemTimestamp();
                l_administratorUploadTempParms.setUpdateTimestamp(l_processTime);

                l_queryProcessor.doInsertQuery(l_administratorUploadTempParms);
            }
        }
        catch (DataException de)
        {
            log.debug(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        log.exiting(STR_METHOD_NAME);
        return this.getRowCount();
    }

    /**
     * (deleteアップロードTemp) <BR>
     * 「（管理者共通）アップロードテンポラリ」テーブルより行を削除する。<BR>
     * <BR>
     * 　@以下の条件に当てはまる行を<BR>
     *   「（管理者共通）アップロードテンポラリ」テーブルより削除（delete）する。<BR>
     * <BR>
     * 　@アップロードＩＤ = this.getアップロードＩＤ()<BR>
     * <BR>
     *  ※該当データがなくても、例外を上位にスローしない。<BR>
     * @@throws WEB3SystemLayerException
     * @@roseuid 40F77CE10326
     */
    public void deleteUploadTemp() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "deleteUploadTemp()";
        log.entering(STR_METHOD_NAME);
        
        try
        {        
            //アップロードＩＤ = this.getアップロードＩＤ()
            Long l_lngAdministratorUploadId = new Long(this.getAdministratorUploadId());

            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" administrator_upload_id = ? ");    //アップロードＩＤ

            Object[] l_objAdministratorUploadTempWhere = { 
                l_lngAdministratorUploadId    //アップロードＩＤ
                }; 
            
            //「（管理者共通）アップロードテンポラリ」テーブルより行を削除する。
            //※該当データがなくても、例外を上位にスローしない。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doDeleteAllQuery(
                AdministratorUploadTempRow.TYPE,
                l_sbWhere.toString(),
                l_objAdministratorUploadTempWhere
            );
        }
        catch (DataException de)
        {
            log.debug(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        log.exiting(STR_METHOD_NAME); 
    }

    /**
     * (setDataFromアップロードTemp) <BR>
     * 「（管理者共通）アップロードテンポラリ」テーブルの<BR>
     * 指定アップロードＩＤのデータをプロパティにをセットする。<BR>
     * <BR>
     * １）　@テンポラリテーブル読込<BR>
     * 　@「（管理者共通）アップロードテンポラリ」テーブルを以下の条件で検索し、<BR>
     * 行番号順に（管理者共通）アップロードテンポラリParams[]を取得する。<BR>
     * <BR>
     * 　@アップロードＩＤ = 引数.アップロードＩＤ<BR>
     * <BR>
     * 　@※（管理者共通）アップロードテンポラリParams<BR>
     * 　@DDLにて自動生成する行オブジェクト<BR>
     * <BR>
     * ２）　@キーヘッダ行のセット<BR>
     * 　@this.setキーヘッダ()にてキーヘッダをセットする。<BR>
     * 　@※１）で読み込んだ行オブジェクト配列のindex = 0の要素<BR>
     *   をキーヘッダ行と判定する<BR>
     * <BR>
     * 　@[setキーヘッダ()に指定する引数]<BR>
     * 　@キーヘッダ：　@（管理者共通）アップロードテンポラリ<BR>
     *   Params[0].split(区切り文字)<BR>
     * <BR>
     * ３）　@明細行のセット<BR>
     * 　@（管理者共通）アップロードテンポラリ<BR>
     *   Params[]の2番目（index = 1）以降の要素について、<BR>
     *   this.add明細行()にて明細行に追加する。　@<BR>
     * <BR>
     * 　@[add明細行()に指定する引数]<BR>
     * 　@明細行文字列：　@（管理者共通）アップロードテンポラリ<BR>
     *   Params[index].CSV行<BR>
     * <BR>
     * ４）　@アップロードＩＤセット<BR>
     * this.アップロードＩＤに引数.アップロードＩＤをセットする。<BR>
     * 　@
     * @@param l_lngUploadId - アップロードＩＤ<BR>
     * @@throws WEB3SystemLayerException
     * @@roseuid 40F4E9A303C2
     */
    public void setDataFromUploadTemp(long l_lngUploadId)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "setDataFromUploadTemp(long)";
        log.entering(STR_METHOD_NAME);
        
        List l_lisRecords;
        Long l_lngUpID = new Long(l_lngUploadId);
        String l_strOrderBy = "line_number ";
        
        //１）　@テンポラリテーブル読込
        try
        {
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" administrator_upload_id = ? ");    //アップロードＩＤ
            
            Object[] l_objAdministratorUploadTempWhere = { 
                l_lngUpID    //アップロードＩＤ
                }; 

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                AdministratorUploadTempRow.TYPE,
                l_sbWhere.toString(),
                l_strOrderBy,
                null,
                l_objAdministratorUploadTempWhere);
        }
        catch (DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

        if (l_lisRecords.size() < 1)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "（管理者共通）アップロードテンポラリテーブル検索： 件数 < 1");
        }

        AdministratorUploadTempParams l_administratorUploadTempParamsTmp;

        //２）　@キーヘッダ行のセット
        l_administratorUploadTempParamsTmp =
            (AdministratorUploadTempParams) l_lisRecords.get(0);
        this.setKeyHeader(
            l_administratorUploadTempParamsTmp.csv_line_value.split(
                WEB3GentradeCsvDataModel.regex));

        //３）　@明細行のセット
        int l_intSize = l_lisRecords.size();
        for (int i = 1; i < l_intSize; i++)
        {
            l_administratorUploadTempParamsTmp =
                (AdministratorUploadTempParams) l_lisRecords.get(i);
            this.addRow(l_administratorUploadTempParamsTmp.getCsvLineValue());
        }
        
        //４）　@アップロードＩＤセット
        //this.アップロードＩＤに引数.アップロードＩＤをセットする
        this.administratorUploadId = l_lngUploadId;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (getアップロード履歴) <BR>
     * 当該アップロードファ@イルに関連するアップロード履歴をすべて取得する。<BR>
     * <BR>
     * 以下の条件で「（管理者共通）アップロードテーブル」を検索し、<BR>
     * 取得した行オブジェクトの配列を返却する。<BR>
     * <BR>
     * [条件]<BR>
     * （管理者共通）アップロードテーブル.証券会社コード = <BR>
     *      this.get証券会社コード() <BR>
     * （管理者共通）アップロードテーブル.アップロードファ@イルＩＤ = <BR>
     *      this.getアップロードファ@イルＩＤ() <BR>
     * （管理者共通）アップロードテーブル.銘柄タイプ = this.get銘柄タイプ() <BR>
     * （管理者共通）アップロードテーブル.データキー = 引数.データキー <BR>
     *  <BR>
     *  [取得順]<BR>
     *  アップロード開始日時　@降順（：desc）<BR>
     * @@param l_lngUploadKey - データキー <BR>
     *   「（管理者共通）アップロード管理テーブル」.データキーに更新する内容。<BR>
     * @@return （管理者共通）アップロード行[] <BR>
     * @@throws WEB3SystemLayerException
     * @@roseuid 40F214CC01E5
     */
    public AdministratorUploadRow[] getUploadAction(long l_lngUploadKey)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getUploadAction(long)";
        log.entering(STR_METHOD_NAME);

        List l_lisRecords;
        String l_strInstitutionCode = null;          //証券会社コード
        String l_strUploadFileId = null;             //アップロードファ@イルＩＤ
        ProductTypeEnum l_productTypeEnum = null;    //銘柄タイプ
        
        Long l_lngUpKey = new Long(l_lngUploadKey);  //引数.データキー
              
        //「（管理者共通）アップロードテーブル」を検索
        try
        {
            l_strInstitutionCode = this.getInstitutionCode();
            l_strUploadFileId = this.getUploadFileId();
            l_productTypeEnum = this.getProductType();

            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");            //証券会社コード
            l_sbWhere.append(" and upload_file_id = ? ");              //アップロードファ@イルＩＤ
            l_sbWhere.append(" and product_type = ? ");            //銘柄タイプ
            l_sbWhere.append(" and upload_key = ? ");              //データキー

            Object[] l_objAdministratorUploadWhere = { 
                l_strInstitutionCode, //証券会社コード
                l_strUploadFileId,    //部店ID
                l_productTypeEnum,    //商品タイプ
                l_lngUpKey            //データキー
                }; 

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords =
                l_queryProcessor.doFindAllQuery(
                    AdministratorUploadRow.TYPE,
                    l_sbWhere.toString(),
                    "upload_start_timestamp desc",
                    null,
                    l_objAdministratorUploadWhere);

        }
        catch (DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        int l_intSize  = l_lisRecords.size();
        AdministratorUploadRow[] l_administratorUploadRows = 
            new AdministratorUploadRow[l_intSize];    
        for (int i = 0; i < l_intSize; i++)
        {
            l_administratorUploadRows[i] = (AdministratorUploadRow)l_lisRecords.get(i);
        }

        log.exiting(STR_METHOD_NAME);
        return l_administratorUploadRows;
    }

    /**
     * (get最新アップロード履歴) <BR>
     * 当該アップロードファ@イルに関連する直近のアップロード履歴を取得する。<BR>
     * <BR>
     * 　@this.getアップロード履歴()にて、当該アップロードファ@イルに<BR>
     *   関連する（管理者共通）アップロードテーブル行をすべて取得する。<BR>
     * <BR>
     * 　@[getアップロード履歴()に指定する引数]<BR>
     * 　@データキー：　@引数のデータキー<BR>
     * <BR>
     * 　@取得した行のうち、アップロード開始日時が<BR>
     *   現在日時(*1)の直近である行を返却する。<BR>
     * <BR>
     * (*1) GtlUtils.getSystemTimestamp()<BR>
     * @@param l_lngUploadKey - データキー <BR>
     * 「（管理者共通）アップロード管理テーブル」.データキーに更新する内容。<BR>
     * 
     * @@return （管理者共通）アップロード行
     * @@throws WEB3SystemLayerException
     * @@roseuid 40F2155C0253
     */
    public AdministratorUploadRow getLatestUploadAction(long l_lngUploadKey)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getLatestUploadAction(long)";
        log.entering(STR_METHOD_NAME);
        
        int l_intReturnRowNum = 0;
        
        //this.getアップロード履歴()にて、当該アップロードファ@イルに
        //関連する（管理者共通）アップロードテーブル行をすべて取得する。
        AdministratorUploadRow[] l_rows = this.getUploadAction(l_lngUploadKey);
        if(l_rows == null || l_rows.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        //取得した行のうち、アップロード開始日時が
        //現在日時(*1)の直近である行を返却する。(*1) GtlUtils.getSystemTimestamp()
        long l_lngCurrentTimeMillis = GtlUtils.getSystemTimestamp().getTime();
        long[] l_temps = new long[l_rows.length];
        for (int i = 0; i < l_rows.length; i++)
        {
            l_temps[i] = Math.abs(l_rows[i].getUploadStartTimestamp().getTime() - l_lngCurrentTimeMillis);
        }
        
        //昇順を取得する
        long[] l_orderByAscLongs = this.getOrderByAsc(l_temps);
        
        for (int j = 0; j < l_rows.length; j++)
        {
            if (Math.abs(l_rows[j].getUploadStartTimestamp().getTime() - l_lngCurrentTimeMillis) == l_orderByAscLongs[0])
            {
                l_intReturnRowNum = j;
                break;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_rows[l_intReturnRowNum];
    }

    /**
     * (getアップロードＩＤ) <BR>
     * this.アップロードＩＤを返却する。<BR>
     * @@return long
     * @@roseuid 40F5333501FD
     */
    public long getAdministratorUploadId()
    {
        //this.アップロードＩＤを返却する。
        return this.administratorUploadId;
    }

    /**
     * (getOrderByAsc) <BR>
     * 昇順を返却する。<BR>
     * @@return long[]
     * @@roseuid 40F5333501FD
     */
    private long[] getOrderByAsc(long[] l_lngTemp)
    {
        final String STR_METHOD_NAME = "getOrderByAsc(long[])";
        log.entering(STR_METHOD_NAME);

        long[] l_lngOrderTemp = l_lngTemp;

        int l_intJLoop = 0;
        int l_intILoop = 0;
        boolean l_isDone = false;
        long l_lngTp = 0;
        int l_intLength = l_lngOrderTemp.length;
        
        while ((l_intJLoop < l_intLength) && (!l_isDone))
        {
            l_isDone = true;
            int l_intRange = l_intLength - l_intJLoop - 1;
            
            for (l_intILoop = 0; l_intILoop < l_intRange; l_intILoop++)
            {
                if (l_lngOrderTemp[l_intILoop] > l_lngOrderTemp[l_intILoop + 1])
                {
                    l_isDone = false;
                    l_lngTp = l_lngOrderTemp[l_intILoop];
                    l_lngOrderTemp[l_intILoop] = l_lngOrderTemp[l_intILoop + 1];
                    l_lngOrderTemp[l_intILoop + 1] = l_lngTp;
                }
            }
            l_intJLoop++;
        }

        log.exiting(STR_METHOD_NAME);
        
        //昇順を返却する。
        return l_lngOrderTemp;
    }


    /**
     * (アップロードTransactionCallback) <BR>
     * <BR>
     * アップロードテーブルに行を作成するTransactionCallbackクラス<BR>
     */
    public class UploadTransactionCallback implements TransactionCallback
    {

        /**
         * （管理者共通）アップロード行<BR>
         * （管理者共通）アップロード行オブジェクト<BR>
         * <BR>
         * ※　@（管理者共通）アップロードParamsクラスはDDLにて自動生成する。<BR>
         */
        public AdministratorUploadParams administratorUploadParams;

        /**
         * @@roseuid 4107644702DE
         */
        public UploadTransactionCallback()
        {

        }

        /**
         * （管理者共通）アップロードテーブルに行をinsertする。<BR>
         * <BR>
         * this.（管理者共通）アップロード行 の内容でDBに行を挿入（insert）する。<BR>
         * nullを返却する。<BR>
         * @@return Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataCallbackException
         * @@roseuid 40F209CF01F5
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            AdministratorUploadParams l_administratorUploadParams = 
                this.administratorUploadParams;

            //this.（管理者共通）アップロード行 の内容でDBに行を挿入（insert）する。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_queryProcessor.doInsertQuery(l_administratorUploadParams);

            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
    
    private class UploadErrorTransactionCallback implements TransactionCallback
    {
        public AdministratorUploadParams administratorUploadParams;

        public UploadErrorTransactionCallback()
        {
        }
        
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            AdministratorUploadParams l_administratorUploadParams = 
                this.administratorUploadParams;

            //（管理者共通）アップロードテーブルを更新する
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_queryProcessor.doUpdateQuery(l_administratorUploadParams);

            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }

    /**
     * (saveAllアップロード中止) <BR>
     * 指定のファ@イルタイプの行すべてにアップロード終了情報を更新する。<BR>
     * <BR>
     * １）　@アップロードＩＤ取得<BR>
     * 　@以下の条件に該当する行のアップロードＩＤをすべて取得する。<BR>
     * 　@該当行がない場合、処理を終了する。（return）<BR>
     * <BR>
     * [条件]<BR>
     * 証券会社コード = this.get証券会社コード()<BR>
     * 部店コード = this.get部店コード()<BR>
     * 銘柄タイプ = this.get銘柄タイプ()<BR>
     * アップロードファ@イルID = this.getアップロードファ@イルＩＤ()<BR>
     * アップロード終了日時 = null<BR> 
     * <BR>
     * ２）　@アップロードテーブル更新<BR>
     * 　@１）で取得した行について、<BR>
     * 　@以下の通り、（管理者共通）アップロードテーブルを更新する。 <BR>
     * <BR>
     * 　@アップロード終了日時 = System.currentTimeMillis()<BR>
     *   アップロード件数 = 0<BR>
     * <BR>
     * ３）　@アップロードテンポラリ削除<BR>
     * 　@以下の条件に当てはまる行を<BR>
     * 「（管理者共通）アップロードテンポラリ」テーブルより削除（delete）する。<BR>
     * <BR>
     * 　@アップロードＩＤ in （１）で取得したアップロードＩＤ）<BR>
     * <BR>
     * ※該当データがなくても、例外を上位にスローしない。<BR>
     * <BR>
     * @@throws WEB3SystemLayerException
     * @@roseuid 40F2155C0253
     */
    public void saveAllUploadStop() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "saveAllUploadStop()";
        log.entering(STR_METHOD_NAME);

        //１）　@アップロードＩＤ取得
        String l_strInstitutionCode = this.getInstitutionCode();
        String l_strBranchCode = this.getBranchCode();
        ProductTypeEnum l_enumProductType = this.getProductType();
        String l_strUploadFileId = this.getUploadFileId();

        List l_lisRecords;
        StringBuffer l_sbWhere = new StringBuffer();
        Object[] l_objAdministratorUploadWhere = {
            l_strInstitutionCode,   //証券会社コード
            l_strBranchCode,        //部店コード
            l_enumProductType,      //銘柄タイプ
            l_strUploadFileId       //アップロードファ@イルＩＤ
            };

        //以下の条件に該当する行のアップロードＩＤをすべて取得する。
        //「（管理者共通）アップロードテーブル」を検索
        try
        {
            l_sbWhere.append(" institution_code = ? ");            //証券会社コード
            l_sbWhere.append(" and branch_code = ? ");             //部店コード
            l_sbWhere.append(" and product_type = ? ");            //銘柄タイプ
            l_sbWhere.append(" and upload_file_id = ? ");          //アップロードファ@イルＩＤ
            l_sbWhere.append(" and upload_end_timestamp is null ");//アップロード終了日時

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords =
                l_queryProcessor.doFindAllQuery(
                    AdministratorUploadRow.TYPE,
                    l_sbWhere.toString(),
                    l_objAdministratorUploadWhere);
        }
        catch (DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

        //該当行がない場合、処理を終了する。
        if ((l_lisRecords == null) || l_lisRecords.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return ;
        }

        int l_intSize  = l_lisRecords.size();

        //２）　@アップロードテーブル更新
        Map l_map = new Hashtable();
        //アップロード終了日時 = System.currentTimeMillis()
        l_map.put("upload_end_timestamp", new Date(System.currentTimeMillis()));
        //アップロード件数 = 0
        l_map.put("upload_count", new Integer(0));
        
        try
        {        
            QueryProcessor l_queryProcessor =
                 Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateAllQuery(
                AdministratorUploadParams.TYPE,
                l_sbWhere.toString(),
                l_objAdministratorUploadWhere,
                l_map);
        }
        catch (DataException de)
        {
            log.debug(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

        //３）　@アップロードテンポラリ削除
        try
        {
            long l_lngAdministratorUploadId;
            Long l_upLoadId;
            StringBuffer l_delWhere = new StringBuffer();
            l_delWhere.append(" administrator_upload_id = ? ");    //アップロードＩＤ

            for (int i = 0; i< l_intSize; i++)
            {
                Object l_Obj = l_lisRecords.get(i);
                
                //※該当データがなくても、例外を上位にスローしない。
                if (l_Obj == null)
                    continue;

                l_lngAdministratorUploadId =
                     ((AdministratorUploadRow)l_Obj).getAdministratorUploadId();
                l_upLoadId = new Long(l_lngAdministratorUploadId);

                Object[] l_objAdministratorUploadTempWhere = {
                    l_upLoadId    //アップロードＩＤ
                    };
                QueryProcessor l_delQueryProcessor = 
                    Processors.getDefaultProcessor();
                //「（管理者共通）アップロードテンポラリ」テーブルより行を削除する。
                l_delQueryProcessor.doDeleteAllQuery(
                    AdministratorUploadTempRow.TYPE,
                    l_delWhere.toString(),
                    l_objAdministratorUploadTempWhere
                );
            }
        }
        catch (DataException de)
        {
            log.debug(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
