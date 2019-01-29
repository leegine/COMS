head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.22.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminAioVirtualAccCashinULCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : バーチャル口座入金CSV (WEB3AdminAioVirtualAccCashinULCsv)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/5/9 周捷 (中訊) 新規作成      
Revesion History : 2006/5/9 周捷 (中訊) モデルNo.555 
Revesion History : 2006/5/16 周捷 (中訊) モデルNo.583       
Revesion History : 2006/5/19 山田昌和 (SCS) モデルNo.585
Revesion History : 2006/5/20 山田昌和 (SCS) モデルNo.586
Revesion History : 2006/5/22 鈴木剛 (SCS) モデルNo.588
Revesion History : 2006/06/12 呉艶飛 (中訊) モデルNo.591,594
Revesion History : 2006/06/22 山田昌和 (SCS) モデルNo.596,597
Revesion History : 2006/07/21 徐宏偉 (中訊) モデルNo.606
Revesion History : 2006/08/04 齊珂 (中訊) モデルNo.611
Revesion History : 2006/08/17 長瀬亜紀 (SCS) 実装の問題No.004
Revesion History : 2006/08/31 山田昌和 (SCS) モデルNo.638
Revesion History : 2006/10/17 車進 (中訊) モデルNo.659
Revesion History : 2006/10/20 車進 (中訊) モデルNo.672
Revesion History : 2007/01/08 車進 (中訊) モデルNo.689
Revesion History : 2007/01/10 何文敏 (中訊) モデルNo.690
Revesion History : 2009/02/05 柴双紅 (中訊) モデルNo.1094,No.1104
*/

package webbroker3.aio;

import java.sql.Timestamp;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.aio.define.WEB3AdminFXAccountCodeDef;
import webbroker3.aio.define.WEB3AdminFXBankBranchCodeDef;
import webbroker3.aio.define.WEB3AdminFXBankCodeDef;
import webbroker3.aio.define.WEB3AdminFXDataDivDef;
import webbroker3.aio.define.WEB3AdminFXPersonCodeDef;
import webbroker3.aio.define.WEB3AdminFXTypeCodeDef;
import webbroker3.aio.define.WEB3AdminFXUploadNoteOneDef;
import webbroker3.aio.message.WEB3AdminAioVirtualAccCashinULConfirmRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CancelDivDef;
import webbroker3.common.define.WEB3DaemonTriggerStatusDef;
import webbroker3.common.define.WEB3DaemonTriggerTypeDef;
import webbroker3.common.define.WEB3FinSaveDivDef;
import webbroker3.gentrade.WEB3GentradeCsvUploadDataModel;
import webbroker3.gentrade.WEB3GentradeEra;
import webbroker3.gentrade.data.AdministratorUploadDao;
import webbroker3.gentrade.data.AdministratorUploadParams;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.gentrade.data.AdministratorUploadTempDao;
import webbroker3.gentrade.data.AdministratorUploadTempParams;
import webbroker3.gentrade.data.AdministratorUploadTempRow;
import webbroker3.gentrade.data.DaemonTriggerParams;
import webbroker3.gentrade.data.DaemonTriggerRow;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (バーチャル口座入金CSV)<BR>
 * 
 * @@author 周捷(中訊)
 * @@version 1.0
 */

public class WEB3AdminAioVirtualAccCashinULCsv extends WEB3GentradeCsvUploadDataModel
{
   
    /**
     * ログ出力オブジェクト。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioVirtualAccCashinULCsv.class);
    
    /**
     * (アップロードファ@イルID)<BR>
     */
    public String UPLOAD_FILEID = "バーチャル口座入金";
   
    /**
     * (アップロード中止)<BR>
     * アップロード中止用コメント<BR>
     */
    public String UPLOAD_STOP = "中止";
   
    /**
     * (銀行コード)<BR>
     */
    public String bankCode;
   
    /**
     * (口座番号)<BR>
     */
    public String accountCode;
   
    /**
     * (預金種目)<BR>
     */
    public String depositBankAccountType;
   
    /**
     * (ヘッダーレコード件数)<BR>
     * ヘッダーレコードをカウントするための変数<BR>
     */
    public int headerRecordCount = 0;
   
    /**
     * (データレコード件数)<BR>
     * データレコードをカウントするための変数<BR>
     */
    public int dataRecordCount = 0;
   
    /**
     * (トレーラーレコード件数)<BR>
     * トレーラーレコードをカウントするための変数<BR>
     */
    public int trailerRecordCount = 0;
   
    /**
     * (エンドレコード件数)<BR>
     * エンドレコードをカウントするための変数<BR>
     */
    public int endRecordCount = 0;
   
    /**
     * (トータル件数)<BR>
     * 全レコード数をカウントするための変数<BR>
     */
    public int totalCount = 0;
   
    /**
     * (読み飛ばしたレコード件数)<BR>
     */
    public int skipReadRecordCount = 0;
   
    /**
     * (チェック用データレコード件数)<BR>
     */
    public int checkDataRecordCount = 0;
   
    /**
     * (値チェック)<BR>
     * validateでエラーが発生した場合のチェック内容表示用<BR>
     */
    private String VALUE_CHECK = "値チェック";
   
    /**
     * (数値チェック)<BR>
     * validateでエラーが発生した場合のチェック内容表示用<BR>
     */
    private String NUMBER_CHECK = "数値チェック";
   
    /**
     * (桁数チェック)<BR>
     * validateでエラーが発生した場合のチェック内容表示用<BR>
     */
    private String LENGTH_CHECK = "桁数チェック";
   
    /**
     * (日付チェック)<BR>
     * validateでエラーが発生した場合のチェック内容表示用<BR>
     */
    private String DATE_CHECK = "日付チェック";
    
    /**
     * (金額チェック)<BR>
     * validateでエラーが発生した場合のチェック内容表示用<BR>
     */
    private String AMOUNT_CHECK = "金額チェック";
   
    /**
     * (バーチャル口座入金CSV)<BR>
     * コンストラクタ <BR>
     * <BR>
     * @@roseuid 4456FBD80078
     */
    public WEB3AdminAioVirtualAccCashinULCsv () 
    {
               
    }
   
    /**
     * (バーチャル口座入金CSV)<BR>
     * コンストラクタ <BR>
     * ※　@アップロード中止の場合に使用する。 <BR>
     * <BR>
     * －引数のアップロードIDをプロパティにセットする。<BR>
     * @@param l_lngUploadID - (アップロードID)
     * @@roseuid 4456FB6A02CD
     */
    public WEB3AdminAioVirtualAccCashinULCsv (long l_lngUploadId) 
    {
        final String STR_METHOD_NAME = 
            " WEB3AdminAioVirtualAccCashinULCsv (long l_lngUploadId) ";
        log.entering (STR_METHOD_NAME );
        
        //－引数のアップロードIDをプロパティにセットする。
        super.administratorUploadId = l_lngUploadId;       
             
        log.exiting (STR_METHOD_NAME);
    }
   
    /**
     * (checkデータ区分)<BR>
     * 引数.データ区分をチェックし、以下の処理を行う。 <BR>
     * <BR>
     * 　@１）トータル件数を1件分インクリメントする。 <BR>
     * <BR>
     * 　@２－１）データ区分が1の場合 ： this.ヘッダーレコード件数を1件分インクリメントし、 <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@返却値"1"を返す。 <BR>
     * 　@２－２）データ区分が2の場合 ： this.データレコード件数を1件分インクリメントし、 <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@this.チェック用データレコード件数を1件分インクリメントし、 <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@返却値"2"を返す。 <BR>
     * 　@２－３）データ区分が8の場合 ： this.トレーラーレコード件数を1件分インクリメントし、 <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@返却値"0"を返する。 <BR>
     * 　@２－４）データ区分が9の場合 ： this.エンドレコード件数を1件分インクリメントし、 <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@返却値"9"を返す。 <BR>
     * 　@２－５）データ区分が上記のもの以外の場合 ： 返却値"0"を返する。<BR>
     * @@param l_strDataDiv - (データ区分)
     * @@return int
     * @@roseuid 445C07810100
     */
    public int checkDataDiv (String l_strDataDiv) 
    {
        final String STR_METHOD_NAME = " checkDataDiv (String l_strDataDiv) ";
        log.entering (STR_METHOD_NAME );    
        
        //１）トータル件数を1件分インクリメントする。       
        totalCount ++;
        
        //２－１）データ区分が1の場合 ： this.ヘッダーレコード件数を1件分インクリメントし、
        // 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@返却値"1"を返す。
        if (WEB3AdminFXDataDivDef.HEADER_RECORD_COUNT.equals(l_strDataDiv))
        {
            this.headerRecordCount ++;
            
            log.exiting (STR_METHOD_NAME);
            return 1;
        }
        
        //２－２）データ区分が2の場合 ： this.データレコード件数を1件分インクリメントし、
        //                             this.チェック用データレコード件数を1件分インクリメントし、
        //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@返却値"2"を返す。
        else if (WEB3AdminFXDataDivDef.DATA_RECORD_COUNT.equals(l_strDataDiv))
        {
            this.dataRecordCount ++;
            this.checkDataRecordCount ++;
            
            log.exiting (STR_METHOD_NAME);
            return 2;
        }
        
        //２－３）データ区分が8の場合 ： this.トレーラーレコード件数を1件分インクリメントし、
        //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@返却値"0"を返し。
        else if (WEB3AdminFXDataDivDef.TRAILER_RECORD_COUNT.equals(l_strDataDiv))
        {
            this.trailerRecordCount ++;
            
            log.exiting (STR_METHOD_NAME);
            return 0;
        }
        
        //２－４）データ区分が9の場合 ： this.エンドレコード件数を1件分インクリメントし、
        //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@返却値"9"を返す。 
        else if (WEB3AdminFXDataDivDef.END_RECORD_COUNT.equals(l_strDataDiv))
        {
            this.endRecordCount ++;
            
            log.exiting (STR_METHOD_NAME);
            return 9;
        }
        
        //２－５）データ区分が上記のもの以外の場合 ： 返却値"0"を返する。
        else
        {
            log.exiting (STR_METHOD_NAME);
            return 0;
        }
    }
   
    /**
     * (validateヘッダーレコード)<BR>
     * ヘッダーレコードのチェックを行う。 <BR>
     * 例外が発生した場合は、例外の追加文字列に以下をセットする。 <BR>
     * チェック内容 + "," + 対象項目 + "," + 対象値 + "," + this.トータル件数 <BR>
     * （BUSINESS_ERROR_02437） <BR>
     * <BR>
     * 　@１） レコードを取得する。 <BR>
     * 　@　@・レコード　@=　@リクエストデータ.アップロードファ@イル[引数.行番号] <BR>
     * <BR>
     * 　@２） 銀行共通チェック <BR>
     * 　@　@　@2)-1 レコード桁数チェック <BR>
     * 　@　@　@　@　@・レコード桁数を取得し、200桁以外の場合はエラー。<BR> 
     * 　@　@　@　@　@・チェック内容は"桁数チェック"、対象項目は"レコード" <BR>
     * 　@　@　@　@　@（※対象値には、チェック対象のレコードを設定する） <BR>
     * <BR>
     * 　@　@　@2)-2 種別コードチェック <BR>
     * 　@　@　@　@　@・レコード2～3桁目（substring(1, 3)）を取得し、<BR> 
     *          「01：振込入金通知」以外の場合はエラー。 <BR>
     * 　@　@　@　@　@・チェック内容は"値チェック"、対象項目は"種別コード"<BR> 
     * <BR>
     * 　@　@　@2)-3 銀行コードチェック <BR>
     * 　@　@　@　@　@・レコード23～26桁目（substring(22, 26)）を取得し、<BR> 
     *          「リクエストデータ.銀行コード」以外の場合はエラー。 <BR>
     * 　@　@　@　@　@・チェック内容は"値チェック"、対象項目は"銀行コード" <BR>
     * <BR>
     * 　@　@　@2)-5 口座番号チェック  <BR>
     * 　@　@　@　@　@・レコード61～67桁目（substring(60, 67)）を取得し、数値以外の場合はエラー。<BR> 
     * 　@　@　@　@　@・ただし、例外として、オールスペースの場合はチェックをしない。<BR> 
     * 　@　@　@　@　@・チェック内容は"数値チェック"、対象項目は"口座番号" <BR>
     * <BR>
     * 　@３） 銀行個別チェック <BR>
     * 　@　@　@3)-1 リクエストデータ.銀行コード = 「0005(三菱東京UFJ)」または「0149(静岡)」の場合 <BR>
     * <BR>
     * 　@　@　@　@①@ 預金種目チェック <BR>
     * 　@　@　@　@　@・レコード60桁目（substring(59, 60)）を取得し、 <BR>
     * 　@　@　@　@　@　@「1：普通預金」、「2：当座預金」、「4：貯蓄預金」以外の場合はエラー。<BR> 
     * 　@　@　@　@　@・チェック内容は"値チェック"、対象項目は"預金種目" <BR>
     * <BR>
     * 　@　@　@3)-2 リクエストデータ.銀行コード = 「0001(みずほ)」の場合 <BR>
     * <BR>
     * 　@　@　@　@①@ 預金種目チェック <BR>
     * 　@　@　@　@　@・レコード60桁目（substring(59, 60)）を取得し、 <BR>
     * 　@　@　@　@　@　@「1：普通預金」、「2：当座預金」以外の場合はエラー。 <BR>
     * 　@　@　@　@　@・チェック内容は"値チェック"、対象項目は"預金種目" <BR>
     * @@param  l_intLineNumber - (行番号)
     * @@param  l_request - (バーチャル口座入金UL確認リクエスト)
     * @@throws WEB3BaseException
     * @@roseuid 445C09E00207
     */
    public void validateHeaderRecord(int l_intLineNumber, WEB3AdminAioVirtualAccCashinULConfirmRequest l_request) throws WEB3BaseException
    { 
        final String STR_METHOD_NAME = " validateHeaderRecord(int, WEB3AdminAioVirtualAccCashinULConfirmRequest) ";
        log.entering (STR_METHOD_NAME );       
        
        //１）レコードを取得する。
        //  レコード　@=　@リクエストデータ.アップロードファ@イル[引数.行番号]
        String l_strRecord = l_request.uploadFile[l_intLineNumber];
        
        //２） 銀行共通チェック 
        //2)-1レコード桁数チェック 
        //　@  レコード桁数を取得し、200桁以外の場合はエラー。
        //　@　@チェック内容は"桁数チェック"、対象項目は"レコード"
        if (l_strRecord.length() != 200)
        {
            log.exiting(STR_METHOD_NAME); 
            throw new WEB3BusinessLayerException (
                WEB3ErrorCatalog.BUSINESS_ERROR_02437,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                LENGTH_CHECK + "," + "レコード" + "," + l_strRecord + "," + this.totalCount);
        }
        
        //2)-2 種別コードチェック 
        // レコード2～3桁目（substring(1, 3)）を取得し、 
        //「01：振込入金通知」以外の場合はエラー。 
        // チェック内容は"値チェック"、対象項目は"種別コード" 
        if (!WEB3AdminFXTypeCodeDef.TRANSFER_CASHIN_NOTIFY.equals(l_strRecord.substring(1, 3)))
        {
            log.exiting(STR_METHOD_NAME); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02437,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                VALUE_CHECK + "," + "種別コード" + "," + l_strRecord.substring(1, 3) + "," + this.totalCount);
        }
        
        //2)-3 銀行コードチェック 
        //  レコード23～26桁目（substring(22, 26)）を取得し、 
        //  リクエストデータ.銀行コード」以外の場合はエラー。
        //  チェック内容は"値チェック"、対象項目は"銀行コード"
        if (l_request.financialInstitutionCode != null)
        {
            if (!l_request.financialInstitutionCode.equals(l_strRecord.substring(22, 26)))
            {
                log.exiting(STR_METHOD_NAME); 
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02437,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    VALUE_CHECK + "," + "銀行コード" + "," + l_strRecord.substring(22, 26) + "," + this.totalCount);
            }
        }

        
        //2)-5 口座番号チェック  
        //  レコード61～67桁目を取得し、数値以外の場合はエラー。 
        //  ただし、例外として、オールスペースの場合はチェックをしない。　@ 
        //  チェック内容は"数値チェック"、対象項目は"口座番号"
        if (!WEB3StringTypeUtility.isNumber(l_strRecord.substring(60, 67))
            && !("".equals(l_strRecord.substring(60, 67).trim())))
        {
            log.exiting(STR_METHOD_NAME); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02437,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                NUMBER_CHECK + "," + "口座番号" + "," + l_strRecord.substring(60, 67) + "," + this.totalCount);                     
        }      

        //３） 銀行個別チェック 
        //3)-1 リクエストデータ.銀行コード = 「0005(三菱東京UFJ)」または「0149(静岡)」の場合 
        if (WEB3AdminFXBankCodeDef.TOKYO_MITSUBISHI_BANK.equals(l_request.financialInstitutionCode) || 
            WEB3AdminFXBankCodeDef.SHIZUOKA_BANK.equals(l_request.financialInstitutionCode))
        {                   
            //①@ 預金種目チェック 
            //・レコード60桁目（substring(59, 60)）を取得し、
            //「1：普通預金」、「2：当座預金」、「4：貯蓄預金」以外の場合はエラー。 
            //・チェック内容は"値チェック"、対象項目は"預金種目" 
            if (!WEB3FinSaveDivDef.GENERAL_FIN_SAVE.equals(l_strRecord.substring(59, 60)) 
                && !WEB3FinSaveDivDef.ACCOUNT_FIN_SAVE.equals(l_strRecord.substring(59, 60))
                && !WEB3FinSaveDivDef.SAVING_FIN_SAVE.equals(l_strRecord.substring(59, 60)))
            {
                log.exiting(STR_METHOD_NAME); 
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02437,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    VALUE_CHECK + "," + "預金種目" + "," + l_strRecord.substring(59, 60) + "," + this.totalCount);
            }

        }

        //(3)-2 リクエストデータ.銀行コード = 「0001(みずほ)」の場合 
        else if (WEB3AdminFXBankCodeDef.MIZUHO.equals(l_request.financialInstitutionCode))
        {
            //①@ 預金種目チェック 
            //　@　@・レコード60桁目（substring(59, 60)）を取得し、 
            //　@　@　@「1：普通預金」、「2：当座預金」以外の場合はエラー。 
            //　@　@・チェック内容は"値チェック"、対象項目は"預金種目" 
            if (!WEB3FinSaveDivDef.GENERAL_FIN_SAVE.equals(l_strRecord.substring(59, 60)) 
                && !WEB3FinSaveDivDef.ACCOUNT_FIN_SAVE.equals(l_strRecord.substring(59, 60)))
            {
                log.exiting(STR_METHOD_NAME); 
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02437,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    VALUE_CHECK + "," + "預金種目" + "," + l_strRecord.substring(59, 60) + "," + this.totalCount);
            }
        }
           
    
        log.exiting(STR_METHOD_NAME);   
    }
    
    /**
     * (validateデータレコード)<BR>
     * データレコードのチェックを行う。 <BR>
     * 例外が発生した場合は、例外の追加文字列に以下をセットする。<BR> 
     * チェック内容 + "," + 対象項目 + "," + 対象値 + "," + this.トータル件数 <BR>
     * （BUSINESS_ERROR_02437） <BR>
     * <BR>
     * 　@１） レコードを取得する。<BR> 
     * 　@　@レコード　@=　@リクエストデータ.アップロードファ@イル[引数.行番号] <BR>
     * <BR>
     * 　@２） 銀行共通チェック <BR>
     * 　@　@　@2)-1 レコード桁数チェック <BR>
     * 　@　@　@　@・レコード桁数を取得し、200桁以外の場合はエラー。<BR> 
     * 　@　@　@　@・チェック内容は"桁数チェック"、対象項目は"レコード" <BR>
     * 　@　@　@　@（※対象値には、チェック対象のレコードを設定する）<BR> 
     * <BR>
     * 　@　@　@2)-2 勘定日 有効日付チェック  <BR>
     * 　@　@　@　@・勘定日 ＝ レコード8～13桁目（substring(7, 13)）を取得<BR>
     * 　@　@　@　@・「年号.getJapEraDiv(勘定日)」にて年号を取得<BR>
     * 　@　@　@　@・「年号.toDate(取得した年号 , 勘定日)」の戻り値<BR>
     * 　@　@　@　@　@　@　@がnullの場合はエラー。<BR>
     * 　@　@　@　@・チェック内容は"日付チェック"、対象項目は"勘定日"<BR>
     * <BR>
     * 　@　@　@2)-3 起算日 有効日付チェック <BR>
     * 　@　@　@　@・起算日 ＝ レコード14～19桁目（substring(13, 19)）を取得<BR>
     * 　@　@　@　@・「年号.getJapEraDiv(起算日)」にて年号を取得<BR>
     * 　@　@　@　@・「年号.toDate(取得した年号 , 起算日)」の戻り値<BR>
     * 　@　@　@　@　@　@　@がnullの場合はエラー。<BR>
     * 　@　@　@　@・チェック内容は"日付チェック"、対象項目は"起算日"<BR>
     * <BR>
     * 　@３） 銀行個別チェック <BR>
     * 　@　@　@3)-1 リクエストデータ.銀行コード = 「0001(みずほ)」の場合 <BR>
     *          ・金額（1）＝ レコード20～29桁目（substring(19, 29)）を取得  <BR>
     *          ・金額（2）＝ レコード129～140桁目（substring(128, 140)）を取得  <BR>
     * <BR>
     *          ①@金額（1）チェック   <BR>    
     *          ・「金額（1）」が数値以外の場合はエラー。    <BR>   
     *          ・チェック内容は"数値チェック"、対象項目は"金額（1）"  <BR>
     * <BR>          
     * 　@　@　@　@②金額（2）チェック  <BR>
     *          ・「金額（2）」が数値以外の場合はエラー。 <BR>
     *          ・チェック内容は"数値チェック"、対象項目は"金額（2）" <BR>
     * <BR>          
     * 　@　@　@  ③金額0円以下チェック  <BR>
     *          ・「金額（1）」且つ「金額（2）」が、0以下の場合はエラー。 <BR>
     *          ・チェック内容は"金額チェック"、対象項目は"金額" <BR>
     * <BR>               
     *        3)-2 リクエストデータ.銀行コード = 「0005(三菱東京UFJ)」または「0149(静岡)」の場合<BR>
     *          ・金額 ＝ レコード20～29桁目（substring(19, 29)）を取得  <BR>
     *  <BR>           
     *          ①@金額チェック  <BR>
     *          ・「金額」が数値以外の場合はエラー。 <BR>
     *          ・チェック内容は"数値チェック"、対象項目は"金額"  <BR>
     *  <BR>           
     *          ②金額0円以下チェック  <BR>
     *          ・「金額」が0以下の場合はエラー。  <BR>
     *          ・チェック内容は"金額チェック"、対象項目は"金額" <BR>
     * @@param  l_intLineNumber - (行番号)
     * @@param  l_request - (バーチャル口座入金UL確認リクエスト)
     * @@throws WEB3BaseException
     * @@roseuid 445C0A020293
     */
    public void validateDataRecord(
        int l_intLineNumber, 
        WEB3AdminAioVirtualAccCashinULConfirmRequest l_request) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateDataRecord(" +
            "int, WEB3AdminAioVirtualAccCashinULConfirmRequest) ";
        log.entering(STR_METHOD_NAME );   

        //１）レコードを取得する。
        //  レコード　@=　@リクエストデータ.アップロードファ@イル[引数.行番号]
        String l_strRecord = l_request.uploadFile[l_intLineNumber];
        
        //２） 銀行共通チェック 
        //2)-1レコード桁数チェック
        //　@  レコード桁数を取得し、200桁以外の場合はエラー。
        //　@　@チェック内容は"桁数チェック"、対象項目は"レコード"
        if (l_strRecord.length() != 200)
        {
            log.exiting(STR_METHOD_NAME); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02437,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                LENGTH_CHECK + "," + "レコード" + "," + l_strRecord + "," + this.totalCount);
        }
        
        //2)-2 勘定日 有効日付チェック 
        //　@　@・勘定日 ＝ レコード8～13桁目（substring(7, 13)）を取得
        //　@　@・「年号.getJapEraDiv(勘定日)」にて年号を取得
        //　@　@・「年号.toDate(取得した年号 , 勘定日)」の戻り値がnullの場合はエラー。
        //　@　@・チェック内容は"日付チェック"、対象項目は"勘定日"
        String l_strSettlementDate = l_strRecord.substring(7, 13);
        String l_strSettlementJapEraDiv = WEB3GentradeEra.getJapEraDiv(l_strSettlementDate);
        if (WEB3GentradeEra.toDate(l_strSettlementJapEraDiv, l_strSettlementDate) == null)
        {
            log.exiting(STR_METHOD_NAME); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02437,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                DATE_CHECK + "," + "勘定日" + "," + l_strRecord.substring(7, 13) + "," + this.totalCount);
        }
        
        //2)-3 起算日 有効日付チェック
        //　@　@・起算日 ＝ レコード14～19桁目（substring(13, 19)）を取得
        //　@　@・「年号.getJapEraDiv(起算日)」にて年号を取得
        //　@　@・「年号.toDate(取得した年号 , 起算日)」の戻り値がnullの場合はエラー。
        //　@　@・チェック内容は"日付チェック"、対象項目は"起算日"
        String l_strStartDay = l_strRecord.substring(13, 19);
        String l_strStartDayJapEraDiv = WEB3GentradeEra.getJapEraDiv(l_strStartDay);
        if (WEB3GentradeEra.toDate(l_strStartDayJapEraDiv, l_strStartDay) == null)
        {
            log.exiting(STR_METHOD_NAME); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02437,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                DATE_CHECK + "," + "起算日" + "," + l_strRecord.substring(13, 19) + "," + this.totalCount);
        }
            
        //　@３） 銀行個別チェック 
        //      3)-1 リクエストデータ.銀行コード = 「0001(みずほ)」の場合
        if (WEB3AdminFXBankCodeDef.MIZUHO.equals(l_request.financialInstitutionCode))
        {   
            // 金額（1）＝ レコード20～29桁目（substring(19, 29)）を取得
            // ①@金額（1）チェック   
            //   ・「金額（1）」が数値以外の場合はエラー。      
            //   ・チェック内容は"数値チェック"、対象項目は"金額（1）"  
            if (!WEB3StringTypeUtility.isNumber(l_strRecord.substring(19, 29)))
            {
                log.exiting(STR_METHOD_NAME); 
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02437,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    NUMBER_CHECK + "," + "金額(1)" + "," + l_strRecord.substring(19, 29) + "," + this.totalCount);
            } 
            
            // 金額（2）＝ レコード129～140桁目（substring(128, 140)）を取得 
            //　@②金額（2）チェック 
            //  ・「金額（2）」が数値以外の場合はエラー。 
            //  ・チェック内容は"数値チェック"、対象項目は"金額（2）" 
            if (!WEB3StringTypeUtility.isNumber(l_strRecord.substring(128, 140)))
            {
                log.exiting(STR_METHOD_NAME); 
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02437,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    NUMBER_CHECK + "," + "金額(2)" + "," + l_strRecord.substring(128, 140) + "," + this.totalCount);
            }
            
            //  ③金額0円以下チェック  
            //   ・「金額（1）」且つ「金額（2）」が、0以下の場合はエラー。 
            //   ・チェック内容は"金額チェック"、対象項目は"金額" 
            if (Double.parseDouble(l_strRecord.substring(19, 29)) <= 0 
                && Double.parseDouble(l_strRecord.substring(128, 140)) <= 0)
            {
                log.exiting(STR_METHOD_NAME); 
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02437,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    AMOUNT_CHECK + "," + "金額" + ","+ 
                    l_strRecord.substring(19, 29) + "," +
                    l_strRecord.substring(128, 140) + "," + 
                    this.totalCount);
            }   
        } 
        //    3)-2 リクエストデータ.銀行コード = 「0005(三菱東京UFJ)」または「0149(静岡)」の場合
        if (WEB3AdminFXBankCodeDef.TOKYO_MITSUBISHI_BANK.equals(l_request.financialInstitutionCode) || 
            WEB3AdminFXBankCodeDef.SHIZUOKA_BANK.equals(l_request.financialInstitutionCode))
        {     
            //金額 ＝ レコード20～29桁目（substring(19, 29)）を取得
            //①@金額チェック  
            //  ・「金額」が数値以外の場合はエラー。 
            //  ・チェック内容は"数値チェック"、対象項目は"金額"  
            if (!WEB3StringTypeUtility.isNumber(l_strRecord.substring(19, 29)))
            {
                log.exiting(STR_METHOD_NAME); 
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02437,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    NUMBER_CHECK + "," + "金額" + "," + l_strRecord.substring(19, 29) + "," + this.totalCount);
            }    
            
            //   ②金額0円以下チェック  
            //    ・「金額」が0以下の場合はエラー。  
            //    ・チェック内容は"金額チェック"、対象項目は"金額"
            if (Double.parseDouble(l_strRecord.substring(19, 29)) <= 0)
            {
                log.exiting(STR_METHOD_NAME); 
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02437,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    AMOUNT_CHECK + "," + "金額" + "," + l_strRecord.substring(19, 29) + "," + this.totalCount);
            }   
        } 
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (calc読み飛ばし件数)<BR>
     * 読み飛ばしたレコード件数を計算する。<BR>
     * <BR>
     * 読み飛ばしたレコード件数 = this.トータル件数<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@ - this.ヘッダレコード件数<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@ - this.データレコード件数<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@ - this.トレーラーレコード件数<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@ - this.エンドレコード件数 <BR>
     * @@roseuid 445C0B690345
     */
    public void calcSkipReadCount() 
    {
        final String STR_METHOD_NAME = " calcSkipReadCount() ";
        log.entering(STR_METHOD_NAME ); 
        
        skipReadRecordCount = this.totalCount 
            - this.headerRecordCount 
            - this.dataRecordCount
            - this.trailerRecordCount 
            - this.endRecordCount;
                
        log.exiting(STR_METHOD_NAME);  
    }
   
    /**
     * (checkデータレコード件数)<BR>
     * データレコード件数のチェックを行う。<BR>
     * <BR>
     * 　@１）this.チェック用データレコード件数が1件以上あるかチェックを行う。<BR>
     * 　@　@　@this.チェック用データレコード件数が0件の場合、例外をスローする。<BR>
     * 　@　@　@（BUSINESS_ERROR_02438）<BR>
     * <BR>
     * 　@２）this.チェック用データレコード件数を初期化（=0）にする。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 445C0F490294
     */
    public void checkDataRecordCount() throws WEB3BaseException
    { 
        final String STR_METHOD_NAME = 
            " checkDataRecordCount() ";
        log.entering(STR_METHOD_NAME ); 
        
        //１）this.チェック用データレコード件数が1件以上あるかチェックを行う。
        //　@　@this.チェック用データレコード件数が0件の場合、例外をスローする。
        //　@ （BUSINESS_ERROR_02438）
        if (this.checkDataRecordCount == 0)
        {
            log.exiting(STR_METHOD_NAME); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02438,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "データレコード件数が0件");
        }
        
        //２）this.チェック用データレコード件数を初期化（=0）にする。
        checkDataRecordCount = 0;
        
        log.exiting(STR_METHOD_NAME);    
    }
   
    /**
     * (check入金処理)<BR>
     * 他の機@能にて入金処理が行われているかチェックをする。<BR>
     * <BR>
     * 　@１）デモーモントリガーテーブルを「処理タイプ = O：入金通知」の条件で検索する。<BR>
     * <BR>
     * 　@２）取得したレコード内にて「処理状態 = 0：AP未稼働」以外の<BR>
     * 　@　@  レコードである場合、例外をスローする。<BR>
     * 　@　@（BUSINESS_ERROR_02439）<BR>
     *<BR>　@　@　@
     * @@throws WEB3BaseException 
     * @@roseuid 445C26CD0023
     */
    public void checkCashinTransaction() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " checkCashinTransaction() ";
        log.entering(STR_METHOD_NAME );  
        
        //１）デモーモントリガーテーブルを「処理タイプ = O：入金通知」の条件で検索する。
        String l_strWhere = "trigger_type = ?";
        Object[] l_objValue = {WEB3DaemonTriggerTypeDef.ORIX_CASHIN_NOTICE};
                
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    DaemonTriggerRow.TYPE,
                    l_strWhere,
                    null,
                    l_objValue);
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
        
        if(l_lisRows.size() == 0 || l_lisRows == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "レコードが取得できませんでした");
        }
        for (int i = 0; i < l_lisRows.size(); i++)
        {
            //２）取得したレコード内にて「処理状態 = 0：AP未稼働」以外の
            //　@  レコードである場合、例外をスローする。
            //　@　@（BUSINESS_ERROR_02439）
            DaemonTriggerRow l_daemonTriggerRow = (DaemonTriggerRow)l_lisRows.get(i);  
            DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams(l_daemonTriggerRow);
            
            if (!WEB3DaemonTriggerStatusDef.THREAD_IDLE.equals(l_daemonTriggerParams.getTriggerStatus()))
            {
                log.exiting(STR_METHOD_NAME); 
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02439,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "処理状態 = 0：AP未稼働以外");
            }
        }
                
        log.exiting(STR_METHOD_NAME);      
    }
   
    /**
     * (stop入金デーモン)<BR>
     * デーモントリガーテーブルの停止を行う。<BR>
     * <BR>
     * 　@１）デーモントリガーテーブルを「処理タイプ = O：入金通知」で検索する。<BR>
     * 　@２）取得したレコードの「処理状態」を1：AP処理中に更新する。<BR>
     * 　@３）commitを行う。<BR>
     * @@throws WEB3BaseException 
     * @@throws NotFoundException 
     * @@roseuid 445C2BAA0109
     */
    public void stopCashinDaemon() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " stopCashinDaemon() ";
        log.entering(STR_METHOD_NAME );    
        
        //１）デーモントリガーテーブルを「処理タイプ = O：入金通知」で検索する。
        String l_strWhere = "trigger_type = ?";
        Object[] l_objValue = {WEB3DaemonTriggerTypeDef.ORIX_CASHIN_NOTICE};
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    DaemonTriggerRow.TYPE,
                    l_strWhere,
                    null,
                    l_objValue);
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
        
        if(l_lisRows.size() == 0 || l_lisRows == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "レコードが取得できませんでした");
        }
        
        for (int i = 0; i < l_lisRows.size(); i++)
        {
            //２）取得したレコードの「処理状態」を1：AP処理中に更新する。
            DaemonTriggerRow l_daemonTriggerRow = (DaemonTriggerRow)l_lisRows.get(i);
            DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams(l_daemonTriggerRow);
            l_daemonTriggerParams.setTriggerStatus(WEB3DaemonTriggerStatusDef.THREAD_PROCESSING);
            l_daemonTriggerParams.setTriggerDate(GtlUtils.getTradingSystem().getSystemTimestamp());
            
            try
            {
                Processors.getDefaultProcessor().doUpdateQuery(l_daemonTriggerParams);
            }
            catch(DataException l_ex)
            {
                log.error("__DataException__", l_ex);
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
     * (start入金デーモン)<BR>
     * デーモントリガーテーブルの開始を行う。<BR>
     * <BR>
     * 　@１）デーモントリガーテーブルを「処理タイプ = O：入金通知」で検索する。<BR>
     * 　@２）取得したレコードの「処理状態」を0：AP未稼働に更新する。<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 445C2CA402B1
     */
    public void startCashinDaemon() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " startCashinDaemon() ";
        log.entering(STR_METHOD_NAME ); 
        
        //１）デーモントリガーテーブルを「処理タイプ = O：入金通知」で検索する。
        String l_strWhere = "trigger_type = ?";
        Object[] l_objValue = {WEB3DaemonTriggerTypeDef.ORIX_CASHIN_NOTICE};
        List l_lisRows = null;
        
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    DaemonTriggerRow.TYPE,
                    l_strWhere,
                    null,
                    l_objValue);
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
        
        if(l_lisRows.size() == 0 || l_lisRows == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "レコードが取得できませんでした");
        }
        
        for (int i = 0; i < l_lisRows.size(); i++)
        {
            //２）取得したレコードの「処理状態」を0：AP処理中に更新する。
            DaemonTriggerRow l_daemonTriggerRow = (DaemonTriggerRow)l_lisRows.get(i);
            DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams(l_daemonTriggerRow);
            l_daemonTriggerParams.setTriggerStatus(WEB3DaemonTriggerStatusDef.THREAD_IDLE);
            l_daemonTriggerParams.setTriggerDate(GtlUtils.getTradingSystem().getSystemTimestamp());
            
            try
            {
                Processors.getDefaultProcessor().doUpdateQuery(l_daemonTriggerParams);
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
        }
               
        log.exiting(STR_METHOD_NAME);  
    }
   
    /**
     * (setヘッダーレコード)<BR>
     * 行番号に対応するレコードを取得し、銀行コード、口座番号、預金種別を保持する。<BR>
     * <BR>
     * [引数] <BR>
     * 　@行番号：　@行番号 <BR>
     * <BR>
     * 　@１）銀行コード取得<BR>
     * 　@　@レコード23～26桁目（substring(22, 26)）を取得し、<BR>
     * 　@　@this.銀行コードにセットする。<BR>
     * <BR>
     * 　@２）預金種目取得<BR>
     * 　@　@レコード60桁目（substring(59, 60)）を取得し、<BR>
     * 　@　@this.預金種目にセットする。<BR>
     * <BR>
     * 　@３）口座番号取得<BR>
     * 　@　@３-1）レコード61～67桁目（substring(60, 67)）を取得する<BR>
     * 　@　@３-２）オールスペースの場合、"0000000"をセットする。<BR>
     * 　@　@３-３）this.口座番号にセットする。<BR>
     * @@param l_intLineNumber - (行番号)
     * @@throws WEB3BaseException 
     * @@roseuid 445C366E0299
     */
    public void setHeaderRecord(int l_intLineNumber) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " setHeaderRecord(int l_intLineNumber) ";
        log.entering(STR_METHOD_NAME );  
        
        AdministratorUploadTempRow  administratorUploadTempRow = null;
        String l_strCsvLineValue = null;
        String l_strAccountCode = null;
        
        try
        {
            administratorUploadTempRow = 
                AdministratorUploadTempDao.findRowByAdministratorUploadIdLineNumber(
                    super.administratorUploadId, l_intLineNumber);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DataQueryException", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);            
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DataNetworkException", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }               
        if (administratorUploadTempRow == null)
        {
            log.debug("(管理者共通)ｱｯﾌﾟﾛｰﾄﾞﾃﾝﾎﾟﾗﾘのレコードを取得しない");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        l_strCsvLineValue = administratorUploadTempRow.getCsvLineValue();
        
        //１）銀行コード取得
        //レコード23～26桁目（substring(22, 26)）を取得し、
        //this.銀行コードにセットする。
        this.bankCode = l_strCsvLineValue.substring(22, 26);
        
        //２）預金種目取得<BR>
        // 　@ レコード60桁目（substring(59, 60)）を取得し、
        //　@　@this.預金種目にセットする。
        this.depositBankAccountType = l_strCsvLineValue.substring(59, 60); 
        
        //３）口座番号取得
        //  ３-1）レコード61～67桁目（substring(60, 67)）を取得する
        l_strAccountCode = l_strCsvLineValue.substring(60, 67);
        
        //  ３-２）オールスペースの場合、"0000000"をセットする。
        if ("".equals(l_strAccountCode.trim()))
        {
            l_strAccountCode = WEB3AdminFXAccountCodeDef.ACCOUNT_CODE_0000000;
        }
        
        //３-３）this.口座番号にセットする。
        this.accountCode = l_strAccountCode;
               
        log.exiting(STR_METHOD_NAME);     
    }
   
    /**
     * (get銀行支店コード)<BR>
     * 行番号に対応するレコードを取得し、銀行支店コードを取得する。<BR>
     * <BR>
     * レコード：　@明細行[引数.行番号]<BR>
     * <BR>
     * １）銀行個別処理<BR> 
     * <BR>
     * 　@１-１）引数.銀行コード = 「0149(静岡)」の場合<BR> 
     * 　@１-１-１）レコード内130～132桁目（substring(129, 132)）を返却する。<BR> 
     * <BR>
     * 　@１-２）引数.銀行コード = 「0149(静岡)」以外の場合<BR> 
     * 　@１-２-１）レコード内40～42桁目（substring(39, 42)）を返却する。<BR> 
     * <BR>
     * 　@ただし、オールスペースの場合、"000"を返却する。<BR>　@　@
     * @@param l_intLineNumber - (行番号)
     * @@param l_strBankCode - (銀行コード)
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 445C2CE003B5
     */
    public String getBankBranchCode(int l_intLineNumber, String l_strBankCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBankBranchCode(int l_intLineNumber, " + 
            "String l_strBankCode)";
        log.entering(STR_METHOD_NAME ); 
      
        AdministratorUploadTempRow  administratorUploadTempRow = null;
        String l_strCsvLineValue = null;
        String l_strBankBranchCode = null;
        
        try
        {
            administratorUploadTempRow = 
                AdministratorUploadTempDao.findRowByAdministratorUploadIdLineNumber(
                    super.administratorUploadId, l_intLineNumber);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DataQueryException", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);            
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DataNetworkException", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }      
        if (administratorUploadTempRow == null)
        {
            log.debug("(管理者共通)ｱｯﾌﾟﾛｰﾄﾞﾃﾝﾎﾟﾗﾘのレコードを取得しない");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //行番号に対応するレコードを取得し、銀行支店コードを取得する。
          l_strCsvLineValue = administratorUploadTempRow.getCsvLineValue();

        // １-１）引数.銀行コード = 「0149(静岡)」の場合 
        // １-１-１）レコード内130～132桁目（substring(129, 132)）を返却する。
          if (WEB3AdminFXBankCodeDef.SHIZUOKA_BANK.equals(l_strBankCode))
          {
              l_strBankBranchCode = l_strCsvLineValue.substring(129, 132);
          }
         // １-２）引数.銀行コード = 「0149(静岡)」以外の場合 
         // １-２-１）レコード内40～42桁目（substring(39, 42)）を返却する。 
          else
          {
              //レコード内40～42桁目（substring(39, 42)）を取得する。
              l_strBankBranchCode = l_strCsvLineValue.substring(39, 42);
          }

        //オールスペースの場合、"000"をセットする。
        if ("".equals(l_strBankBranchCode.trim()))
        {
            l_strBankBranchCode = WEB3AdminFXBankBranchCodeDef.BANK_BRANCH_CODE_000;
        }

        log.exiting(STR_METHOD_NAME);
        return l_strBankBranchCode;
    }

    /**
     * (get勘定日)<BR>
     * 行番号に対応するレコードを取得し、勘定日を取得する。<BR>
     * <BR>
     * [引数]<BR> 
     * 　@行番号：　@行番号 <BR>
     * <BR>
     * 　@レコード内8～13桁目（substring(7, 13)）を取得する。<BR>
     * @@param l_intLineNumber - (行番号)
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 445C3A850336
     */
    public String getSettlementDate(int l_intLineNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSettlementDate(int l_intLineNumber) ";
        log.entering(STR_METHOD_NAME );
       
        AdministratorUploadTempRow  administratorUploadTempRow = null;
        String l_strCsvLineValue = null;
        String l_strSettlementDate = null;

        try
        {
            administratorUploadTempRow = 
                AdministratorUploadTempDao.findRowByAdministratorUploadIdLineNumber(
                    super.administratorUploadId, l_intLineNumber);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DataQueryException", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);            
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DataNetworkException", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }               
        if (administratorUploadTempRow == null)
        {
            log.debug("(管理者共通)ｱｯﾌﾟﾛｰﾄﾞﾃﾝﾎﾟﾗﾘのレコードを取得しない");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        l_strCsvLineValue = administratorUploadTempRow.getCsvLineValue();
        //レコード内8～13桁目（substring(7, 13)）を取得する。
        l_strSettlementDate = l_strCsvLineValue.substring(7, 13);
               
        log.exiting(STR_METHOD_NAME);
        return l_strSettlementDate;
    }
   
    /**
     * (get起算日)<BR>
     * 行番号に対応するレコードを取得し、起算日を取得する。<BR>
     * <BR>
     * [引数] <BR>
     * 　@行番号：　@行番号 <BR>
     * <BR>
     * 　@レコード内14～19桁目（substring(13, 19)）を取得する。<BR>
     * @@param l_intLineNumber - (行番号)
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 445C3AFF0117
     */
    public String getBaseDate(int l_intLineNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBaseDate(int l_intLineNumber) ";
        log.entering(STR_METHOD_NAME );
        
        AdministratorUploadTempRow  administratorUploadTempRow = null;
        String l_strCsvLineValue = null;
        String l_strBaseDate = null;

        try
        {
            administratorUploadTempRow = 
                AdministratorUploadTempDao.findRowByAdministratorUploadIdLineNumber(
                    super.administratorUploadId, l_intLineNumber);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DataQueryException", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);            
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DataNetworkException", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }               
        if (administratorUploadTempRow == null)
        {
            log.debug("(管理者共通)ｱｯﾌﾟﾛｰﾄﾞﾃﾝﾎﾟﾗﾘのレコードを取得しない");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        l_strCsvLineValue = administratorUploadTempRow.getCsvLineValue();
        //レコード内14～19桁目（substring(13, 19)）を取得する。
        l_strBaseDate = l_strCsvLineValue.substring(13, 19);
               
        log.exiting(STR_METHOD_NAME);
        return l_strBaseDate;
    }
   
    /**
     * (get金額)<BR>
     * 行番号に対応するレコードを取得し、金額を取得する。<BR> 
     * <BR>
     * レコード：　@明細行[引数.行番号] <BR>
     * <BR>
     * １）銀行個別処理 <BR>
     * <BR>
     * 　@１）引数.銀行コード = 「0001(みずほ)」の場合 <BR>
     * 　@１-１）　@レコード内20～29桁目（substring(19, 29)）を取得する。 <BR>
     * <BR>
     * 　@１-２）　@固定長の為、10桁全てに文字が入っているので、 <BR>
     * 　@　@　@　@　@数値と認識できる形式に変換する。 <BR>
     * 　@　@　@　@　@（左の桁についている余分な"0"を取り除く）<BR>
     * 　@　@　@　@　@（金額（１）の取得） <BR>
     * <BR>
     * 　@１-３-１）　@レコード内129～140桁目（substring(128, 140)）を取得する。 <BR>
     * 　@　@　@　@　@　@　@（金額（２））の取得 <BR>
     * <BR>
     * 　@１-３-２）　@固定長の為、12桁全てに文字が入っているので、 <BR>
     * 　@　@　@　@　@　@　@数値と認識できる形式に変換する。 <BR>
     * 　@　@　@　@　@　@　@（左の桁についている余分な"0"を取り除く） <BR>
     * <BR>
     * 　@１-３-３）　@１-２）で取得した金額が0の場合、１-３-２）で取得した金額を <BR>
     * 　@　@　@　@　@　@　@返却する。 <BR>
     * <BR>
     * 　@１-３-４）　@１-２）で取得した金額が0以外の場合、１-２）で取得した金額を 
     *　@　@　@　@　@　@　@返却する。 <BR>
     * <BR>
     * 　@２）引数.銀行コード = 「0001(みずほ)」以外の場合 <BR>
     * 　@２-１）　@レコード内20～29桁目（substring(19, 29)）を取得する。 <BR>
     * <BR>
     * 　@２-２）　@固定長の為、10桁全てに文字が入っているので、 <BR>
     * 　@　@　@　@　@数値と認識できる形式に変換する。 <BR>
     * 　@　@　@　@　@（左の桁についている余分な"0"を取り除く） <BR>
     * <BR>
     * 　@２-３）　@２-２）で取得した値を返却する。<BR>
     * <BR>
     * @@param l_intLineNumber - (行番号)
     * @@param l_strBankCode - (銀行コード)
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 445C3B590288
     */
    public String getAmount(int l_intLineNumber, String l_strBankCode) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getAmount(int, String) ";
        log.entering(STR_METHOD_NAME );
       
        AdministratorUploadTempRow  administratorUploadTempRow = null;
        String l_strCsvLineValue = null;
        String l_strAmount = null;
        double l_dblAmount = 0.0;

        try
        {
            administratorUploadTempRow = 
                AdministratorUploadTempDao.findRowByAdministratorUploadIdLineNumber(
                    super.administratorUploadId, l_intLineNumber);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DataQueryException", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);            
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DataNetworkException", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }               
        if (administratorUploadTempRow == null)
        {
            log.debug("(管理者共通)ｱｯﾌﾟﾛｰﾄﾞﾃﾝﾎﾟﾗﾘのレコードを取得しない");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        l_strCsvLineValue = administratorUploadTempRow.getCsvLineValue();
        
        //１）引数.銀行コード = 「0001(みずほ)」の場合 
        //１-１）　@レコード内20～29桁目（substring(19, 29)）を取得する。 
        if (WEB3AdminFXBankCodeDef.MIZUHO.equals(l_strBankCode))
        {
            l_strAmount = l_strCsvLineValue.substring(19, 29);
        
            //１-２）　@固定長の為、10桁全てに文字が入っているので、 
            //     数値と認識できる形式に変換する。 
            //     （左の桁についている余分な"0"を取り除く） 
            //     （金額（１）の取得） 
            
            double l_dblAmount1 = Double.parseDouble(l_strAmount);         
        
            //１-３-１）　@レコード内129～140桁目（substring(128, 140)）を取得する。 
            //　@　@　@（金額（２））の取得 
            
            l_strAmount = l_strCsvLineValue.substring(128, 140);
            
            //１-３-２）　@固定長の為、12桁全てに文字が入っているので、 
            //      数値と認識できる形式に変換する。 
            //      （左の桁についている余分な"0"を取り除く） 
            double l_dblAmount2 = Double.parseDouble(l_strAmount);
            
            //１-３-３）　@１-２）で取得した金額が0の場合、１-３-２）で取得した金額を 
            //  返却する。 
            if(l_dblAmount1 == 0) {
                l_dblAmount = l_dblAmount2;

            //１-３-４）　@１-２）で取得した金額が0以外の場合、１-２）で取得した金額を 
            //返却する。
            } else {
                l_dblAmount = l_dblAmount1;
            }

        }
        //２）引数.銀行コード = 「0001(みずほ)」以外の場合 
        else
        {
            //２-１）　@レコード内20～29桁目（substring(19, 29)）を取得する。 
            l_strAmount = l_strCsvLineValue.substring(19, 29);
            
            //２-２）　@固定長の為、10桁全てに文字が入っているので、 
            //      数値と認識できる形式に変換する。 
            //      （左の桁についている余分な"0"を取り除く）
            //２-３）　@２-２）で取得した値を返却する。
            
            l_dblAmount = Double.parseDouble(l_strAmount);                                 
        }
        
        log.exiting(STR_METHOD_NAME);
        return WEB3StringTypeUtility.formatNumber(l_dblAmount);     
    }
    
    /**
     * (get依頼人コード)<BR>
     * 行番号に対応するレコードを取得し、依頼人コードを取得する。<BR>
     * <BR>
     * レコード：　@明細行[引数.行番号] <BR>
     * <BR>
     * １）銀行個別処理<BR> 
     * <BR>
     * 　@１-１）引数.銀行コード = 「0149(静岡)」の場合<BR> 
     * 　@１-１-１）レコード内133～139桁目（substring(132, 139)）を返却する。<BR> 
     * <BR>
     * 　@１-２）引数.銀行コード = 「0149(静岡)」以外の場合<BR> 
     * 　@１-２-１）レコード内43～49桁目（substring(42, 49)）を返却する。<BR> 
     * <BR>
     * 　@ただし、オールスペースの場合、"0000000"を返却する。<BR>
     * @@param l_intLineNumber - (行番号)
     * @@param l_strBankCode - (銀行コード)
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 445C3C420337
     */
    public String getPersonCode(int l_intLineNumber, String l_strFinancialInstitutionCode) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getPersonCode(int l_intLineNumber, " + 
            "String l_strBankCode) ";
        log.entering(STR_METHOD_NAME );
       
        AdministratorUploadTempRow  administratorUploadTempRow = null;
        String l_strCsvLineValue = null;
        String l_strPersonCode = null;

        try
        {
            administratorUploadTempRow = 
                AdministratorUploadTempDao.findRowByAdministratorUploadIdLineNumber(
                    super.administratorUploadId, l_intLineNumber);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DataQueryException", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);            
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DataNetworkException", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }               
        if (administratorUploadTempRow == null)
        {
            log.debug("(管理者共通)ｱｯﾌﾟﾛｰﾄﾞﾃﾝﾎﾟﾗﾘのレコードを取得しない");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        l_strCsvLineValue = administratorUploadTempRow.getCsvLineValue();
        // １-１）引数.銀行コード = 「0149(静岡)」の場合 
        // １-１-１）レコード内133～139桁目（substring(132, 139)）を返却する。 
        if (WEB3AdminFXBankCodeDef.SHIZUOKA_BANK.equals(l_strFinancialInstitutionCode))
        {
            l_strPersonCode = l_strCsvLineValue.substring(132, 139);
        }
        // １-２）引数.銀行コード = 「0149(静岡)」以外の場合 
        // １-２-１）レコード内43～49桁目（substring(42, 49)）を返却する。 
        else
        {
            l_strPersonCode = l_strCsvLineValue.substring(42, 49);
        }

      //オールスペースの場合、"0000000"をセットする。
      if ("".equals(l_strPersonCode.trim()))
      {
          l_strPersonCode = WEB3AdminFXPersonCodeDef.PERSON_CODE_0000000;
      }

        log.exiting(STR_METHOD_NAME);
        return l_strPersonCode;
    }
   
    /**
     * (get依頼人名)<BR>
     * 行番号に対応するレコードを取得し、依頼人名を取得する。<BR> 
     * <BR> 
     * レコード：　@明細行[引数.行番号] <BR> 
     * <BR> 
     * １）銀行個別処理<BR>
     * <BR>
     * １-１）引数.銀行コード = 「0005(三菱東京UFJ)」または「0149(静岡)」の場合<BR>
     *    ・レコード内50～97桁目（substring(49, 97)）を取得する。<BR>
     * <BR>
     * １-２）引数.銀行コード = 「0001(みずほ)」の場合<BR>
     *    ・レコード内61～97桁目（substring(60, 97)）を取得する。<BR>
     *    ※みずほの場合、依頼人名には「依頼人コード(10)+スペース(1)+依頼人名(37)」となっている為<BR>
     * <BR>
     * ２）銀行共通処理<BR>
     * <BR>
     * ２-１）固定長の為、取得した桁数全てに文字が入っているので、右の桁についている余分な空白を取り除く。<BR>
     * <BR>
     * ２-２）１-２の処理で取得した文字を全角変換し、返却する。<BR>
     * @@param l_intLineNumber - (行番号)
     * @@param l_strBankCode - (銀行コード) 
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 445C46FA0353
     */
    public String getPersonName(int l_intLineNumber, String l_strBankCode) 
         throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getPersonName(int, String) ";
        log.entering(STR_METHOD_NAME );
       
        AdministratorUploadTempRow  administratorUploadTempRow = null;
        String l_strCsvLineValue = null;
        String l_strPersonName = null;

        try
        {
            administratorUploadTempRow = 
                AdministratorUploadTempDao.findRowByAdministratorUploadIdLineNumber(
                    super.administratorUploadId, l_intLineNumber);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DataQueryException", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);            
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DataNetworkException", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }               
        if (administratorUploadTempRow == null)
        {
            log.debug("(管理者共通)ｱｯﾌﾟﾛｰﾄﾞﾃﾝﾎﾟﾗﾘのレコードを取得しない");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        l_strCsvLineValue = administratorUploadTempRow.getCsvLineValue();
                
        //１）銀行個別処理 

        //１-１）引数.銀行コード = 「0005(三菱東京UFJ)」または「0149(静岡)」の場合 
        //   ・レコード内50～97桁目（substring(49, 97)）を取得する。
        if (WEB3AdminFXBankCodeDef.TOKYO_MITSUBISHI_BANK.equals(l_strBankCode) || 
            WEB3AdminFXBankCodeDef.SHIZUOKA_BANK.equals(l_strBankCode))
        {
            l_strPersonName = l_strCsvLineValue.substring(49, 97);
        }
        
        //１-２）引数.銀行コード = 「0001(みずほ)」の場合 
        //   ・レコード内61～97桁目（substring(60, 97)）を取得する。 
        //   ※みずほの場合、依頼人名には「依頼人コード(10)+スペース(1)+依頼人名(37)」となっている為
        else if (WEB3AdminFXBankCodeDef.MIZUHO.equals(l_strBankCode))
        {
            l_strPersonName = l_strCsvLineValue.substring(60, 97);
        }       

        //２）銀行共通処理 

        //２-１）固定長の為、取得した桁数全てに文字が入っているので、右の桁についている余分な空白を取り除く。
        l_strPersonName = l_strPersonName.trim();
        
        //２-２）１-２の処理で取得した文字を全角変換し、返却する。
        String l_strPersonNameRen = WEB3StringTypeUtility.toWbyteKana(l_strPersonName);
               
        log.exiting(STR_METHOD_NAME);
        return l_strPersonNameRen;
    }
   
    /**
     * (get仕向銀行名)<BR>
     * 行番号に対応するレコードを取得し、仕向銀行名を取得する。<BR>
     * <BR>
     * [引数] <BR>
     * 　@行番号：　@行番号 <BR>
     * <BR>
     * 　@１）レコード内98～112桁目（substring(97, 112)）を取得する。<BR>
     * <BR>
     * 　@２）固定長の為、15桁全てに文字が入っているので、<BR>
     * 　@　@　@右の桁についている余分な空白を取り除く。<BR>
     * @@param l_intLineNumber - (行番号)
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 445C3D0F0063
     */
    public String getDeliveredBankName(int l_intLineNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getDeliveredBankName(int l_intLineNumber) ";
        log.entering(STR_METHOD_NAME );
       
        AdministratorUploadTempRow  administratorUploadTempRow = null;
        String l_strCsvLineValue = null;
        String l_strDeliveredBankName = null;

        try
        {
            administratorUploadTempRow = 
                AdministratorUploadTempDao.findRowByAdministratorUploadIdLineNumber(
                    super.administratorUploadId, l_intLineNumber);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DataQueryException", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);            
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DataNetworkException", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }               
        if (administratorUploadTempRow == null)
        {
            log.debug("(管理者共通)ｱｯﾌﾟﾛｰﾄﾞﾃﾝﾎﾟﾗﾘのレコードを取得しない");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        l_strCsvLineValue = administratorUploadTempRow.getCsvLineValue();
        l_strDeliveredBankName = l_strCsvLineValue.substring(97, 112);
               
        log.exiting(STR_METHOD_NAME);
        return l_strDeliveredBankName.trim();
    }
   
    /**
     * (get仕向店名)<BR>
     * 行番号に対応するレコードを取得し、仕向店名を取得する。<BR>
     * <BR>
     * [引数] <BR>
     * 　@行番号：　@行番号 <BR>
     * <BR>
     * 　@１）レコード内113～127桁目（substring(112, 127)）を取得する。<BR>
     * <BR>
     * 　@２）固定長の為、15桁全てに文字が入っているので、<BR>
     * 　@　@　@右の桁についている余分な空白を取り除く。<BR>
     * @@param l_intLineNumber - (行番号)
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 445C3DC903A9
     */
    public String getDeliveredBranchName(int l_intLineNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getDeliveredBranchName(int l_intLineNumber) ";
        log.entering(STR_METHOD_NAME );
       
        AdministratorUploadTempRow  administratorUploadTempRow = null;
        String l_strCsvLineValue = null;
        String l_strDeliveredBranchName = null;
        
        try
        {
            administratorUploadTempRow = 
                AdministratorUploadTempDao.findRowByAdministratorUploadIdLineNumber(
                    super.administratorUploadId, l_intLineNumber);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DataQueryException", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);            
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DataNetworkException", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }               
        if (administratorUploadTempRow == null)
        {
            log.debug("(管理者共通)ｱｯﾌﾟﾛｰﾄﾞﾃﾝﾎﾟﾗﾘのレコードを取得しない");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        l_strCsvLineValue = administratorUploadTempRow.getCsvLineValue();
        l_strDeliveredBranchName = l_strCsvLineValue.substring(112, 127);
                
        log.exiting(STR_METHOD_NAME);
        return l_strDeliveredBranchName.trim();
    }
   
    /**
     * (getEDI情報)<BR>
     * 行番号に対応するレコードを取得し、EDI情報を取得する。 <BR>
     * <BR>
     * レコード：　@明細行[引数.行番号] <BR>
     * <BR>
     * １）銀行個別処理 <BR>
     * <BR>
     * 　@１-１）引数.銀行コード = 「0005(三菱東京UFJ)」または「0149(静岡)」の場合 <BR>
     * 　@　@　@　@　@　@レコード内129～148桁目（substring(128, 148)）を取得する。 <BR>
     * <BR>
     * 　@１-２）引数.銀行コード = 「0001(みずほ)」の場合 <BR>
     * 　@　@　@　@　@　@レコード内153～172桁目（substring(152, 172)）を取得する。 <BR>
     * <BR>
     * ２）銀行共通処理 <BR>
     * 　@２-１）固定長の為、20桁全てに文字が入っているので、１）で取得したデータの<BR> 
     * 　@　@　@　@右の桁についている余分な空白を取り除き、返却する。<BR>
     * <BR>
     * @@param l_intLineNumber - (行番号)
     * @@param l_strBankCode - (銀行コード)
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 445C3E0D0109
     */
    public String getEDIInfo(int l_intLineNumber, String l_strBankCode) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getEDIInfo(int, String) ";
        log.entering(STR_METHOD_NAME);
                    
        AdministratorUploadTempRow  administratorUploadTempRow = null;
        String l_strCsvLineValue = null;
        String l_strEDIInfo = null;
        
        try
        {
            administratorUploadTempRow = 
                AdministratorUploadTempDao.findRowByAdministratorUploadIdLineNumber(
                    super.administratorUploadId, l_intLineNumber);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DataQueryException", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);            
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DataNetworkException", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }               
        
        if (administratorUploadTempRow == null)
        {
            log.debug("(管理者共通)ｱｯﾌﾟﾛｰﾄﾞﾃﾝﾎﾟﾗﾘのレコードを取得しない");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
      
        l_strCsvLineValue = administratorUploadTempRow.getCsvLineValue();
        
        //１）銀行個別処理 

        //１-１）引数.銀行コード = 「0005(三菱東京UFJ)」または「0149(静岡)」の場合 
        //     レコード内129～148桁目（substring(128, 148)）を取得する。
        if (WEB3AdminFXBankCodeDef.TOKYO_MITSUBISHI_BANK.equals(l_strBankCode) || 
            WEB3AdminFXBankCodeDef.SHIZUOKA_BANK.equals(l_strBankCode))
        {
            l_strEDIInfo = l_strCsvLineValue.substring(128, 148);
        }
        
        //１-２）引数.銀行コード = 「0001(みずほ)」の場合 
        //　@　@　@レコード内153～172桁目（substring(152, 172)）を取得する。 
        else if (WEB3AdminFXBankCodeDef.MIZUHO.equals(l_strBankCode))
        {
            l_strEDIInfo = l_strCsvLineValue.substring(152, 172);
        }       

        //２）銀行共通処理 
        //２-１）固定長の為、20桁全てに文字が入っているので、１）で取得したデータの 
        //　@　@　@右の桁についている余分な空白を取り除き、返却する。                    
        log.exiting(STR_METHOD_NAME);
        return l_strEDIInfo.trim();
    }

    /**
     * (getアップロードファ@イルID)
     * バーチャル口座入金アップロードCSV.アップロードファ@イルＩＤを返却する。<BR>  
     *<BR>
     *※（管理者共通）アップロードテーブル.アップロードファ@イルＩＤに格納する文字列<BR>
     *@@return String
     */
    public String getUploadFileId()
    {
        final String STR_METHOD_NAME = " getUploadFileId() ";
        log.entering(STR_METHOD_NAME );
        
        log.exiting(STR_METHOD_NAME);
        return this.UPLOAD_FILEID;      
    }

    /**
     * (get銘柄タイプ)<BR>
     * ProductTypeEnum.その他 を返却する。
     * @@return ProductTypeEnum
     */
    public ProductTypeEnum getProductType() 
    {
        final String STR_METHOD_NAME = " getProductType() ";
        log.entering(STR_METHOD_NAME );
        
        log.exiting(STR_METHOD_NAME);
        return ProductTypeEnum.OTHER;
    }

    /**
     * (setDataListFromアップロードTemp)<BR>
     * 「（管理者共通）アップロードテンポラリ」テーブルの指定アップロードＩＤのデータをプロパティにをセットする。 <BR>
     *<BR>
     * １）　@テンポラリテーブル読込 <BR>
     *  「（管理者共通）アップロードテンポラリ」テーブルを以下の条件で検索し、<BR> 
     *  行番号順に（管理者共通）アップロードテンポラリParams[]を取得する。<BR> 
     *<BR>
     *   アップロードＩＤ = 引数.アップロードＩＤ <BR>
     *<BR>   
     *   ※（管理者共通）アップロードテンポラリParams <BR>
     *   DDLにて自動生成する行オブジェクト <BR>
     *<BR>   
     *<BR>
     *  ２）　@明細行のセット <BR>
     *   （管理者共通）アップロードテンポラリParams[]の要素について、this.明細行に追加する。<BR>　@ 
     *<BR>   
     *  ３）　@アップロードＩＤセット <BR>
     *  　@this.アップロードＩＤに引数.アップロードＩＤをセットする。<BR> 
     *@@param l_lngUploadId - (アップロードＩＤ)
     *@@throws WEB3SystemLayerException 
     */
    public void setDataFromUploadTemp(long l_lngUploadId) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "setDataFromUploadTemp(long)";
        log.entering(STR_METHOD_NAME);
        
        List l_lisRecords;        
        String l_strOrderBy = "line_number ";
         
        //１）　@テンポラリテーブル読込
        try
        {
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" administrator_upload_id = ? ");    
             
            Object[] l_objAdministratorUploadTempWhere = { 
                new Long(l_lngUploadId)
                }; 

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                AdministratorUploadTempRow.TYPE,
                l_sbWhere.toString(),
                l_strOrderBy,
                null,
                l_objAdministratorUploadTempWhere);
        }
        catch (DataException l_dex)
        {
            log.error("__DataException__", l_dex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dex.getMessage(),
                l_dex);
        }

        if (l_lisRecords == null || l_lisRecords.size() == 0)
        {
            log.exiting(STR_METHOD_NAME); 
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "レコードが取得できませんでした");
        }

        AdministratorUploadTempParams l_administratorUploadTempParams;
        
        //２）　@明細行のセット
        int l_intSize = l_lisRecords.size();
        for (int i = 0; i < l_intSize; i++)
        {
            AdministratorUploadTempRow l_administratorUploadTempRow = (AdministratorUploadTempRow)l_lisRecords.get(i);
            l_administratorUploadTempParams = new AdministratorUploadTempParams(l_administratorUploadTempRow);
            this.rows.add(l_administratorUploadTempParams.getCsvLineValue());
        }
         
        //３）　@アップロードＩＤセット
        //this.アップロードＩＤに引数.アップロードＩＤをセットする
        this.administratorUploadId = l_lngUploadId;
        log.exiting(STR_METHOD_NAME);
    }
     
    /**
     * (saveアップロードファ@イル)<BR>
     * アップロードファ@イル行を「（管理者共通）アップロードテンポラリ」テーブルに更新する。<BR>  
     * <BR>
     * リクエストデータ.アップロードファ@イル[].lengthにて、ファ@イル行数（index）を取得する。<BR>  
     * 取得したリクエストデータ.アップロードファ@イル[]各要素について、次の通り <BR> 
     *「（管理者共通）アップロードテンポラリ」テーブルに行を挿入（insert）する。<BR> 
     * <BR>
     * 　@アップロードＩＤ = this.getアップロードＩＤ() <BR> 
     * 　@行番号 = index  <BR>
     * 　@CSV行 = リクエストデータ.アップロードファ@イル[index] <BR> 
     * 　@更新日時 = TradingSystem.getSystemTimestamp()<BR>  
     * @@param  l_strRequestDataUploadFiles - (リクエストデータ.アップロードファ@イル[])
     * @@throws WEB3BaseException 
     */
    public void saveUploadFile(String[] l_strRequestDataUploadFiles) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "saveUploadFile(String[])";
        log.entering(STR_METHOD_NAME);
        
        //リクエストデータ.アップロードファ@イル[].lengthにて、ファ@イル行数（index）を取得する。
        int l_intFileLength = l_strRequestDataUploadFiles.length;
        
        int l_intIndex = -1;
        
        AdministratorUploadTempParams l_uploadTempParams = new AdministratorUploadTempParams();
        for(int i = 0; i < l_intFileLength; i++)
        {
            if(l_strRequestDataUploadFiles[i] == null || "".equals(l_strRequestDataUploadFiles[i]))
            {
                continue;
            }

            //アップロードＩＤ = this.getアップロードＩＤ()
            l_uploadTempParams.setAdministratorUploadId(this.getAdministratorUploadId());
            
            //CSV行 = リクエストデータ.アップロードファ@イル[index]
            l_uploadTempParams.setCsvLineValue(l_strRequestDataUploadFiles[i]);
            
            l_intIndex ++;
            
            //行番号 = index
            l_uploadTempParams.setLineNumber(l_intIndex);
            
            //更新日時 = TradingSystem.getSystemTimestamp()
            l_uploadTempParams.setUpdateTimestamp(GtlUtils.getTradingSystem().getSystemTimestamp());
            
            try
            {
                //「（管理者共通）アップロードテンポラリ」テーブルに更新する。
                WEB3DataAccessUtility.insertRow(l_uploadTempParams);
            }
            catch(DataException l_dex)
            {
                log.error("__DataException__", l_dex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dex.getMessage(),
                    l_dex);
            }
        }
         
        log.exiting(STR_METHOD_NAME);
    }
     
    /**
     * (get明細行)<BR>
     * 親クラスの属性　@明細行：Stirng[]を取得する為のメソッド <BR>
     * <BR>
     *  ○this.明細行を取得し、返却する。<BR>
     */
    public String[] getRows()
    {
        final String STR_METHOD_NAME = "getRows()";
        log.entering(STR_METHOD_NAME);
        
        String[] l_strRows = new String[rows.size()];
        rows.toArray(l_strRows);

        log.exiting(STR_METHOD_NAME);
        return l_strRows;
    }
    
    /**
     * (saveアップロード中止)<BR>
     * 該当アップロード行にアップロード中止を更新する。 <BR>
     *（既存のデータモデルCSV.saveアップロード中止()のオーバーライド） <BR>
     * <BR>
     * this.getアップロードＩＤ()に該当する行について <BR>
     * 以下の通り、（管理者共通）アップロードテーブルを更新する。 <BR>
     * <BR>
     *　@アップロード終了日時 = System.currentTimeMillis() <BR>
     *　@アップロード件数 = 0 <BR>
     *　@備考１ = "中止" <BR>
     *<BR>
     * ※該当データがなくても、例外を上位にスローしない。<BR>
     * @@throws WEB3SystemLayerException 
     */
    public void saveUploadStop() throws WEB3SystemLayerException
    {
        
        final String STR_METHOD_NAME = "saveUploadStop()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            // this.getアップロードＩＤ()に該当する行について
            // 以下の通り、（管理者共通）アップロードテーブルを更新する。
            
            AdministratorUploadRow l_administratorUploadRow = (AdministratorUploadRow)
                AdministratorUploadDao.findRowByAdministratorUploadId(this.getAdministratorUploadId());             
        
            if(l_administratorUploadRow == null)
            {
                //※該当データがなくても、例外を上位にスローしない。
                log.exiting(STR_METHOD_NAME);
                return;
            }
           
            AdministratorUploadParams l_uploadParams = 
                new AdministratorUploadParams(l_administratorUploadRow);
            
            //アップロード終了日時 = System.currentTimeMillis()
            l_uploadParams.setUploadEndTimestamp(new Timestamp(System.currentTimeMillis()));
            
            //アップロード件数 = 0
            l_uploadParams.setUploadCount(0);
            
            //備考１ = "中止"
            l_uploadParams.setNote1(WEB3AdminFXUploadNoteOneDef.UPLOAD_TERMINATE);
     
        
            //以下の通り、（管理者共通）アップロードテーブルを更新する。
            Processors.getDefaultProcessor().doUpdateQuery(l_uploadParams);
        }
        catch (DataException l_ex)
        {
            log.debug("__DataException__", l_ex);
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
     * (is読み飛ばしレコード)<BR>
     *レコードのチェックを行い、処理対象か判別する。<BR> 
     *<BR>
     *レコード：　@引数.明細行[引数.行番号] <BR>
     *<BR>
     *１）銀行共通チェック <BR>
     *　@　@・下記1-①@と1-②の条件に合致した場合、"false"を返却する。 <BR>
     *　@　@　@　@1-①@データ区分（レコード1～1桁目（substring(0, 1)））が、<BR>
     *　@　@　@　@　@"2：データ・レコード"。 <BR>
     *　@　@　@　@1-②取消区分（レコード128桁目（substring(127, 128)））が、<BR>
     *　@　@　@　@　@"1：取消し"。 <BR>
     *<BR>
     *２）銀行個別チェック <BR>
     *　@２-1） 引数.銀行コード = 「0001(みずほ)」の場合 <BR>
     *<BR>
     *　@２-1）-1 オンライン入金チェック <BR>
     *　@　@・下記2-①@と2-②の条件に合致した場合、"false"を返却する。 <BR>
     *　@　@　@　@2-①@データ区分（レコード1～1桁目（substring(0, 1)））が、<BR>
     *　@　@　@　@　@"2：データ・レコード"。<BR> 
     *　@　@　@　@2-②振込依頼人コード（レコード40～49桁目（substring(39, 49)））が、<BR>
     *　@　@　@　@　@"ALLスペース"。 <BR>
     *<BR>
     *　@２-２） 引数.銀行コード = 「0149(静岡)」の場合 <BR>
     *<BR>
     *　@２-２）-1 依頼人コードチェック<BR>
     *　@　@・下記2-③と2-④の条件に合致した場合、"false"を返却する。<BR>
     *　@　@　@　@2-③データ区分（レコード1～1桁目（substring(0, 1)））が、"2：データ・レコード"。<BR>
     *　@　@　@　@2-④依頼人コード（レコード133～139桁目（substring(132, 139)）が、"ALLスペース"　@又は　@"ALL 0" 。<BR>
     *<BR>
     *　@２-３）引数.銀行コード = （「0001(みずほ)」、又は 「0149(静岡)」）以外の場合 <BR>
     *<BR>
     *　@２-３）-1 チェック無し <BR>
     *<BR>
     *<BR>
     *３）上記銀行共通チェック、銀行個別チェックに当てはまらない場合、trueを返却する。<BR>
     *<BR>
     *※"false"だった場合には、[this.トータル件数]を1件分インクリメントする。<BR> 
     *<BR>  
     * @@param l_intLineNumber - (行番号)<BR>
     * バーチャルアップロードファ@イルの行番号<BR>
     * @@param l_strBankCode - (銀行コード)<BR>
     * 銀行コード<BR>
     * @@param l_strRowCounts - (明細行)<BR>
     * 明細行（アップロードファ@イル）<BR>
     * @@throws WEB3BaseException 
     */
    public boolean isSkipReadRecord(
        int l_intLineNumber, 
        String l_strBankCode, 
        String[] l_strRowCounts) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isSkipReadRecord(int, String, String[])";
        log.entering(STR_METHOD_NAME);
                
        if (l_strRowCounts == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //レコード：　@引数.明細行[引数.行番号]
        String l_strRecordData = l_strRowCounts[l_intLineNumber];
     
        //１）銀行共通チェック
        //・下記1-①@と1-②の条件に合致した場合、"false"を返却する。 
        //　@　@　@　@1-①@データ区分（レコード1～1桁目（substring(0, 1)））が、2：データ・レコード"。 
        //　@　@　@　@1-②取消区分（レコード128桁目（substring(127, 128)））が、"1：取消し"。
        if (WEB3AdminFXDataDivDef.DATA_RECORD_COUNT.equals(l_strRecordData.substring(0, 1)) 
            && WEB3CancelDivDef.CANCEL.equals(l_strRecordData.substring(127, 128)))
        {
            this.totalCount ++;
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //２）銀行個別チェック 
        //　@２-1） 引数.銀行コード = 「0001(みずほ)」の場合 
        //　@２-1）-1 オンライン入金チェック 
        //　@　@・下記2-①@と2-②の条件に合致した場合、"false"を返却する。
        //　@　@　@　@2-①@データ区分（レコード1～1桁目（substring(0, 1)））が、
        //       "2：データ・レコード"。 
        //　@　@　@　@2-②振込依頼人コード（レコード40～49桁目（substring(39, 49)））が、
        //       "ALLスペース"。
        if (WEB3AdminFXBankCodeDef.MIZUHO.equals(l_strBankCode))
        {
            if (WEB3AdminFXDataDivDef.DATA_RECORD_COUNT.equals(l_strRecordData.substring(0, 1))
                && "".equals(l_strRecordData.substring(39, 49).trim()))
            {
            
                this.totalCount ++;
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }

        //２-２） 引数.銀行コード = 「0149(静岡)」の場合
        else if (WEB3AdminFXBankCodeDef.SHIZUOKA_BANK.equals(l_strBankCode))
        {
            //２-２）-1 依頼人コードチェック
            //下記2-③と2-④の条件に合致した場合、"false"を返却する。
            //2-③データ区分（レコード1～1桁目（substring(0, 1)））が、"2：データ・レコード"。
            //2-④依頼人コード（レコード133～139桁目（substring(132, 139)）が、"ALLスペース"　@又は　@"ALL 0" 。
            String l_strPerson = l_strRecordData.substring(132, 139);

            if (WEB3AdminFXDataDivDef.DATA_RECORD_COUNT.equals(l_strRecordData.substring(0, 1)) &&
                ("".equals(l_strPerson.trim()) || "".equals(l_strPerson.replaceAll("0",""))))
            {
                log.debug("依頼人コードがALL スペース又はALL 0");
                this.totalCount ++;
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }

        //　@３）上記銀行共通チェック、銀行個別チェックに当てはまらない場合、trueを返却する。
        log.exiting(STR_METHOD_NAME);
        return true;
    }
}
@
