head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.28.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenPasswordVoucher.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 暗証番号伝票(WEB3AccOpenPasswordVoucher.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/12/14 李頴淵 新規作成
                      2006/07/13 徐大方 仕様変更・モデル077
                      2006/07/18 徐大方 (中訊)仕様変更・モデル081
*/

package webbroker3.accountopen;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.Hashtable;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.accountopen.data.AccOpenVoucherItemRow;
import webbroker3.accountopen.data.ExpAccountOpenParams;
import webbroker3.accountopen.data.ExpAccountOpenRow;
import webbroker3.accountopen.data.PasswordVoucherParams;
import webbroker3.accountopen.data.PasswordVoucherRow;
import webbroker3.accountopen.define.WEB3AccountOpenExpAccountOpenSymbolNameDef;
import webbroker3.accountopen.define.WEB3AccountOpenOutputItemSymbolNameDef;
import webbroker3.accountopen.define.WEB3AccountOpenVoucherCodeDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CatDelimitterDef;
import webbroker3.common.define.WEB3DataClassDef;
import webbroker3.common.define.WEB3EditWayDivDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3PubReasonDivDef;
import webbroker3.common.define.WEB3RegistDeleteDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3VoucherNameDef;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (暗証番号伝票)<BR>
 * 暗証番号伝票<BR>
 * 
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3AccOpenPasswordVoucher extends WEB3AccOpenVoucher
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AccOpenPasswordVoucher.class);
    
    /**
     * @@roseuid 41B45E6E003E
     */
    public WEB3AccOpenPasswordVoucher()
    {

    }

    /**
     * (getInstance)<BR>
     * 口座開設見込客オブジェクトを指定し、暗証番号伝票インスタンスを作成する。<BR>
     * <BR>
     * １）　@インスタンス生成<BR>
     * 　@暗証番号伝票インスタンスを生成する。<BR>
     * <BR>
     * ２）　@口座開設見込客プロパティセット<BR>
     * 　@生成したインスタンス.set口座開設見込客()にて、口座開設見込客<BR>
     * プロパティをセットする。<BR>
     * <BR>
     * 　@[set口座開設見込客()に指定する引数]<BR>
     * 　@口座開設見込客：　@口座開設見込客<BR>
     * <BR>
     * ３）　@口座開設伝票マスタプロパティセット<BR>
     * 　@生成したインスタンス.set伝票マスタ()にて、伝票マスタ行プロパティをセットする。<BR>
     * <BR>　@
     * ４）　@生成したインスタンスを返却する。<BR>
     * @@param l_accOpenExpAccountOpen - 口座開設見込客オブジェクト
     *
     * @@return webbroker3.accountopen.WEB3AccOpenPasswordVoucher
     * @@roseuid 4193128F015E
     */
    public static WEB3AccOpenPasswordVoucher getInstance(WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInstance(WEB3AccOpenExpAccountOpen)";
        log.entering(STR_METHOD_NAME);
        //１）　@インスタンス生成
        WEB3AccOpenPasswordVoucher l_accOpenPasswordVoucher = new WEB3AccOpenPasswordVoucher();
        
        //２）　@口座開設見込客プロパティセット
        l_accOpenPasswordVoucher.setAccOpenExpAccountOpen(l_accOpenExpAccountOpen);
        
        //３）　@口座開設伝票マスタプロパティセット
        l_accOpenPasswordVoucher.setAccOpenVoucherMaster();

        log.exiting(STR_METHOD_NAME);
        return l_accOpenPasswordVoucher;
    }

    /**
     * (isオンライン伝票)<BR>
     * （isオンライン伝票()の実装）<BR>
     * <BR>
     * falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 4193128F0160
     */
    public boolean isOnlineVoucher()
    {
        final String STR_METHOD_NAME = " isOnlineVoucher()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (getデータコード)<BR>
     * （getデータコード()の実装）<BR>
     * <BR>
     * データコード.暗証番号（GI842）を返却する。<BR>
     * @@return String
     * @@roseuid 4193128F0161
     */
    public String getRequestCode()
    {
        final String STR_METHOD_NAME = " getRequestCode()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        return WEB3HostRequestCodeDef.ACCOPEN_PASSWORD;
    }

    /**
     * (get伝票コード)<BR>
     * （get伝票コード()の実装）<BR>
     * <BR>
     * ”G5511”を返却する。<BR>
     * @@return String
     * @@roseuid 419DDE4A016E
     */
    public String getVoucherCode()
    {
        final String STR_METHOD_NAME = " getVoucherCode()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        return WEB3AccountOpenVoucherCodeDef.PASSWORD_VOUCHER_CODE;
    }

    /**
     * (get確定済項目名)<BR>
     * （get確定済項目名()の実装）<BR>
     * <BR>
     * 当該伝票で使用している口座開設見込客の列物理名を配列で取得する。<BR>
     * <BR>
     * １）　@伝票使用項目Table（：Hashtable）生成<BR>
     * 　@Hashtableを生成する。<BR>
     * <BR>
     * ２）　@使用項目セット<BR>
     * <BR>
     * 　@this.口座開設伝票マスタ行[]の各要素毎に、２－１）～２－３）の処理を<BR>
     * 実施する。<BR>
     * <BR>
     * 　@２－１）　@作成可能判定<BR>
     * 　@　@is作成可能伝票()にて、作成可能な伝票かを判定する。<BR>
     * <BR>
     * 　@　@[is作成可能伝票()に指定する引数]<BR>
     * 　@　@伝票通番：　@口座開設伝票マスタ行[index].伝票通番<BR>
     * <BR>
     * 　@　@作成可能でない場合（is作成可能伝票() == false）のみ、２－２）を実施する。<BR>
     * 　@　@作成可能な場合（is作成可能伝票() == true）、当該要素の処理を<BR>
     * 行わず次の要素を処理する。（continue;）<BR>
     * <BR>
     * 　@　@＃作成可能な場合、項目値は変更しても良いので<BR>
     * 伝票参照項目名には含めない。<BR>
     * <BR>
     * 　@２－２）　@カスタマイズ参照項目列物理名取得<BR>
     * 　@　@暗証番号伝票（G5511）テーブルの各項目について、下記の処理を実施する。<BR>
     * <BR>
     * 　@　@this.getカスタマイズ参照項目()をコールし、口座開設見込客テーブル列<BR>
     * 物理名の配列を取得する。<BR>
     * <BR>
     * 　@　@[getカスタマイズ参照項目()に指定する引数]<BR>
     * 　@　@伝票通番：　@口座開設伝票マスタ行[index].伝票通番<BR>
     * 　@　@伝票出力項目物理名：　@（※1 暗証番号伝票（G5511）テーブルの<BR>
     * 処理対象項目）<BR>
     * 　@　@伝票参照項目初期値：　@（※2 暗証番号伝票（G5511）テーブルの<BR>
     * 処理対象項目デフォルト設定値）<BR>
     * <BR>
     * 　@　@（※2）　@暗証番号伝票（G5511）テーブルの処理対象項目デフォルト設定値<BR>
     * 　@　@DBレイアウト 「暗証番号伝票（G5511）テーブル.xls#デフォルトDB設定論理」<BR>
     * シートを参照し、<BR>
     * 　@　@該当項目の説明欄に、口座開設見込客テーブルから編集する<BR>
     * 指定があれば、指定項目の列物理名配列。<BR>
     * 　@　@以外は、null。<BR>
     * <BR>
     * 　@２－３）　@伝票使用項目Table（：Hashtable）に追加<BR>
     * 　@　@伝票使用項目Table（：Hashtable）.put()にてthis.getカスタマイズ参照項目()<BR>
     * 戻り値を一要素ずつ追加する。<BR>
     * <BR>
     * 　@　@[put()に指定する引数]<BR>
     * 　@　@key：　@this.getカスタマイズ参照項目()[n]<BR>
     * 　@　@value：　@this.getカスタマイズ参照項目()[n]<BR>
     * <BR>
     * 　@　@※ key，valueに同じ値をセットする。<BR>
     * <BR>
     * ３）　@項目名配列返却<BR>
     * 　@伝票使用項目Table（：Hashtable）.values()　@を返却する。<BR>
     * @@return String[]
     * @@roseuid 4193128F016F
     */
    public String[] getConfirmedItemName() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getConfirmedItemName()";
        log.entering(STR_METHOD_NAME);
        //伝票使用項目Table（：Hashtable）生成 
        Hashtable l_voucherItemList = new Hashtable();
        
        //this.口座開設伝票マスタ行[]の各要素毎に、２－１）～２－３）の処理を実施する。
        int l_intLength = 0;
        if (this.accOpenVoucherMasterParamses != null)
        {
            l_intLength = this.accOpenVoucherMasterParamses.length;
        }
        
        log.debug("&&&&&&&&&&&&&&&&&l_intLength = " + l_intLength);
        for (int i = 0; i < l_intLength; i++)
        {
            log.debug("口座開設伝票マスタ行[]の各要素毎に、２－１）～２－３）の処理を実施する");
            //２－１）　@作成可能判定
            String l_strSerialNo = accOpenVoucherMasterParamses[i].getSerialNo();
            //２－１）　@作成可能判定
            boolean l_blnCreatedPossibleVoucher = this.isCreatedPossibleVoucher(l_strSerialNo);
            
            //作成可能でない場合（is作成可能伝票() == false）のみ、２－２）を実施する
            if (!l_blnCreatedPossibleVoucher)
            {
                
                log.debug("作成可能でない場合（is作成可能伝票() == false）のみ、２－２）を実施する");
                String[] l_strValues1 = new String[1];
                //U01000
                //証券会社コード
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.INSTITUTION_CODE;        
                //２－２）　@カスタマイズ参照項目列物理名取得
                String[] l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.INSTITUTION_CODE, l_strValues1);
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                int l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                    log.debug("********************************証券会社コード");
                }     
                
                //データコード
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.REQUEST_CODE, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("**********************データコード");
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }          
                
                //部店コード
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.BRANCH_CODE;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.BRANCH_CODE, l_strValues1);   
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("***************************部店コード");
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }               
                
                //顧客コード
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ACCOUNT_CODE;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_CODE, l_strValues1);    
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("****************************顧客コード");
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }                
                
                //扱者コード
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.SONAR_TRADER_CODE;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.TRADER_CODE, l_strValues1);
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("**************************扱者コード");
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }               
                
                //識別コード（口座開設見込客）
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues1[0] = WEB3AccountOpenOutputItemSymbolNameDef.ACC_OPEN_REQUEST_NUMBER;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACC_OPEN_REQUEST_NUMBER, l_strValues1);    
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("************************識別コード（口座開設見込客）");
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //伝票通番
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.SERIAL_NO, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("***************************伝票通番");
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //発行取消区分
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.REGIST_DELETE_DIV, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("****************************発行取消区分");
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //デ－タ種別
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.DATA_CLASS, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("************************デ－タ種別");
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //データコード（伝票名）
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.VOUCHER_NAME, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("*****************************データコード（伝票名）");
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //申込年月日
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.REGIST_DATE, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("**************************申込年月日");
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //暗証番号
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.INITIAL_PASSWORD;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.PASSWORD, l_strValues1);    
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("***********************暗証番号");
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //発行理由
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.PUB_REASON_DIV, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("********************発行理由");
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //整理番号
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.REF_NUMBER, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("***********************整理番号");
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //訂正氏名
                //２－２）　@カスタマイズ参照項目列物理名取得
                String[] l_strValues2 = new String[2];
                l_strValues2[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.FAMILY_NAME_ALT1;
                l_strValues2[1] = WEB3AccountOpenExpAccountOpenSymbolNameDef.GIVEN_NAME_ALT1;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.MODIFIED_NAME, l_strValues2);    
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("**************************訂正氏名");
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //カードタイプ
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.CARD_TYPE, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("**************************カードタイプ");
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //オンライン・バッチ区分
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ONLINE_BATCH_DIV, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("************************オンライン・バッチ区分");
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //SEQ_NO
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.SEQ_NO, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("************************SEQ_NO");
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //処理区分
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.STATUS, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("**********************処理区分");
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
            }    
        }
        String[] l_strValues = new String[l_voucherItemList.size()];
        l_voucherItemList.values().toArray(l_strValues);
        
        log.exiting(STR_METHOD_NAME);
        return l_strValues;
    }

    /**
     * (save伝票行)<BR>
     * （save伝票行()の実装）<BR>
     * 口座開設伝票を１件登録する。<BR>
     * <BR>
     * １）　@デフォルト設定行生成<BR>
     * 　@暗証番号伝票（G5511）テーブル行オブジェクトを生成し、<BR>
     * 　@デフォルト設定（※1）の通りにプロパティをセットする。<BR>
     * <BR>
     * 　@文字列をセットする場合、出力項目のデータサイズを超えた場合は、<BR>
     * データサイズ以降の文字を切り捨てる。<BR>
     * <BR>
     * 　@（※1）　@暗証番号伝票（G5511）テーブルの処理対象項目デフォルト設定<BR>
     * 　@DBレイアウト 「暗証番号伝票（G5511）テーブル.xls#デフォルトDB設定論理」<BR>
     * シート参照。<BR>
     * <BR>
     * ２）　@カスタマイズ項目セット <BR>
     * 　@口座開設伝票項目マスタテーブルを以下の条件①@で検索する。 <BR>
     * 　@該当行がない場合、条件②で検索する。 <BR>
     * <BR>
     * 　@[条件①@] <BR>
     * 　@口座開設伝票項目マスタ.証券会社コード = this.get証券会社コード() And<BR>
     * 　@口座開設伝票項目マスタ.部店コード = this.get部店コード() And <BR>
     * 　@口座開設伝票項目マスタ.口座区分 = this.get口座区分() And <BR>
     * 　@口座開設伝票項目マスタ.データコード = this.getデータコード() And <BR>
     * 　@口座開設伝票項目マスタ.伝票通番 = 伝票通番 <BR>
     * <BR>
     * 　@[条件②] <BR>
     * 　@口座開設伝票項目マスタ.証券会社コード = this.get証券会社コード() And <BR>
     * 　@口座開設伝票項目マスタ.部店コード = "000" And <BR>
     * 　@口座開設伝票項目マスタ.口座区分 = this.get口座区分() And <BR>
     * 　@口座開設伝票項目マスタ.データコード = this.getデータコード() And <BR>
     * 　@口座開設伝票項目マスタ.伝票通番 = 伝票通番 <BR>
     * <BR>
     * 　@条件①@，②のどちらかで該当行がある場合は、２－１）の処理を実施する。 <BR>
     * <BR>
     * 　@２－１）　@カスタマイズ編集<BR>
     * 　@　@検索結果の各行毎に、出力項目物理名が示す伝票項目の値を、<BR>
     * 指定の方法@で再セットする。<BR>
     * 　@　@文字列をセットする場合、出力項目のデータサイズを超えた場合は、<BR>
     * データサイズ以降の文字を切り捨てる。<BR>
     * <BR>
     * 　@　@○ （項目編集方法@ == 固定値）の場合、固定セット値の値をセットする。<BR>
     * 　@　@○ 以外の場合、入力項目物理名１～３が示す口座開設見込客の<BR>
     * 項目値(※2)をセットする。<BR>
     * 　@　@　@　@－入力項目物理名１～３がnullの場合は、<BR>
     * 　@　@　@　@－連結項目デリミッタが指定されている場合（連結項目デリミッタ !=
     * null）、<BR>
     * 　@　@　@　@　@入力項目物理名１，２，３の値をデリミッタにて連結する。<BR>
     * <BR>
     * 　@(※2) DBレイアウト「口座開設伝票項目マスタ」参照。<BR>
     * <BR>
     * 　@２－２）　@伝票の識別コード新規採番<BR>
     * 　@　@注文識別コード採番サービス.get新規識別コード()にて識別コードを取得し、<BR>
     * 　@　@行オブジェクトの識別コード（order_request_number）にセットする。<BR>
     * <BR>
     * 　@　@[get新規識別コード()に指定する引数]<BR>
     * 　@　@証券会社コード：　@this.get証券会社コード()<BR>
     * 　@　@部店コード：　@this.get部店コード()<BR>
     * 　@　@銘柄タイプ：　@ProductTypeEnum.その他<BR>
     * <BR>
     * ３）　@DB更新<BR>
     * 　@３－１）　@既存行削除<BR>
     * 　@　@以下の条件にて暗証番号伝票（G5511）テーブルを検索する。<BR>
     * 　@　@該当行が既に存在する場合は、該当行をdeleteする。<BR>
     * <BR>
     * 　@　@[条件]<BR>
     * 　@　@証券会社コード =  this.get証券会社コード() And<BR>
     * 　@　@識別コード = this.get識別コード() And<BR>
     * 　@　@伝票通番 = 伝票通番 And<BR>
     * 　@　@処理区分 = ”未処理”<BR>
     * <BR>
     * 　@３－２）　@伝票行挿入<BR>
     * 　@　@１）～２）で編集した行オブジェクトをDBに更新（DB-insertする）。<BR>
     * @@param l_strSerialNo - 伝票通番
     * @@roseuid 4193128F0170
     */
    public void saveVoucherRow(String l_strSerialNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveVoucherRow(String)";
        log.entering(STR_METHOD_NAME);
        //１）　@デフォルト設定行生成 
        PasswordVoucherParams l_passwordVoucherParams = new PasswordVoucherParams();
        
        try
        {
            //デフォルト設定（※1）の通りにプロパティをセットする。
            String l_strInstitutionCode = this.accOpenExpAccountOpen.getInstitutionCode();
            String l_strBranchCode = this.accOpenExpAccountOpen.getBranchCode();
            String l_strAccountCode = this.accOpenExpAccountOpen.getAccountCode();
            String l_strRequestNumber = this.accOpenExpAccountOpen.getRequestNumber();
            
            //データコード
            l_passwordVoucherParams.setRequestCode(super.getStringByByteNumber(WEB3HostRequestCodeDef.ACCOPEN_PASSWORD, 5));
            //証券会社コード
            l_passwordVoucherParams.setInstitutionCode(super.getStringByByteNumber(l_strInstitutionCode, 3));
            //部店コード
            l_passwordVoucherParams.setBranchCode(super.getStringByByteNumber(l_strBranchCode, 3));
            //顧客コード
            l_passwordVoucherParams.setAccountCode(super.getStringByByteNumber(l_strAccountCode, 7));
            
            //扱者コード
            ExpAccountOpenRow l_expAccountOpenRow = (ExpAccountOpenRow)this.accOpenExpAccountOpen.getDataSourceObject();
            String l_strSonarTraderCode = l_expAccountOpenRow.getSonarTraderCode();
            l_passwordVoucherParams.setTraderCode(super.getStringByByteNumber(l_strSonarTraderCode, 5));
            
            //識別コード（口座開設見込客）
            l_passwordVoucherParams.setAccOpenRequestNumber(super.getStringByByteNumber(l_strRequestNumber, 13));
            //伝票通番
            l_passwordVoucherParams.setSerialNo(super.getStringByByteNumber(l_strSerialNo, 3));
            
            //発行取消区分
            l_passwordVoucherParams.setRegistDeleteDiv(super.getStringByByteNumber(WEB3RegistDeleteDivDef.REGIST, 1));
            //デ－タ種別
            l_passwordVoucherParams.setDataClass(super.getStringByByteNumber(WEB3DataClassDef.DATA_RECORD, 2));
            //データコード（伝票名）
            l_passwordVoucherParams.setVoucherName(super.getStringByByteNumber(WEB3VoucherNameDef.ISSUE_REGIST_VOUCHER, 4));
            
            //申込年月日
            l_passwordVoucherParams.setRegistDate(null);
            String l_strInitialPassword = l_expAccountOpenRow.getInitialPassword();
            //暗証番号
            l_passwordVoucherParams.setPassword(super.getStringByByteNumber(l_strInitialPassword, 48));
            //発行理由
            l_passwordVoucherParams.setPubReasonDiv(super.getStringByByteNumber(WEB3PubReasonDivDef.NEW, 1));
            //整理番号
            l_passwordVoucherParams.setRefNumber(super.getStringByByteNumber("00000", 5));
            
            String l_strFamilyNameAlt1 = WEB3StringTypeUtility.to1byteKana(l_expAccountOpenRow.getFamilyNameAlt1());
            String l_strGivenNameAlt1 = WEB3StringTypeUtility.to1byteKana(l_expAccountOpenRow.getGivenNameAlt1());
            String l_strModifiedName = l_strFamilyNameAlt1 + " " + l_strGivenNameAlt1;
            //訂正氏名
            l_passwordVoucherParams.setModifiedName(super.getStringByByteNumber(l_strModifiedName, 19));
            
            //カードタイプ
            l_passwordVoucherParams.setCardType(null);
            //オンライン・バッチ区分
            l_passwordVoucherParams.setOnlineBatchDiv(null);
            
            //SEQ_NO
            l_passwordVoucherParams.setSeqNo(null);
            //処理区分
            l_passwordVoucherParams.setStatus(super.getStringByByteNumber(WEB3StatusDef.NOT_DEAL, 1));
            //送信日時
            l_passwordVoucherParams.setSendTimestamp(null);
            Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();
            //作成日時
            l_passwordVoucherParams.setCreatedTimestamp(l_tsSystemTimestamp);
            //更新日時
            l_passwordVoucherParams.setLastUpdatedTimestamp(l_tsSystemTimestamp);
            
            //２）　@カスタマイズ項目セット
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor(); //DataNetworkException, DataQueryException
            //[条件①@] 
            String l_strWhereItem =
                "institution_code = ? and " +        //口座開設伝票項目マスタ.証券会社コード = this.get証券会社コード() And 
                "branch_code = ? and " +             //口座開設伝票項目マスタ.部店コード = this.get部店コード() And  
                "account_div = ? and " +             //口座開設伝票項目マスタ.口座区分 = this.get口座区分() And  
                "request_code = ? and " +            //口座開設伝票項目マスタ.データコード = this.getデータコード() And  
                "serial_no = ? ";                    //口座開設伝票項目マスタ.伝票通番 = 伝票通番

            String l_strRequestCode = this.getRequestCode();

            Object l_bindVarsItem[] =
                {l_strInstitutionCode,
                 l_strBranchCode,
                 this.getAccountDiv(),
                 l_strRequestCode,
                 l_strSerialNo};
                    
            List l_lisRowItems = null;
            l_lisRowItems =
                l_queryProcesser.doFindAllQuery(
                    AccOpenVoucherItemRow.TYPE,
                    l_strWhereItem,
                    l_bindVarsItem);                  //DataQueryException, DataNetworkException
                     
            //該当行がない場合、条件②で検索する。 
            if (l_lisRowItems == null || l_lisRowItems.size() == 0)
            {
                //[条件②]  
                Object l_bindVarsItem2[] =
                    {l_strInstitutionCode,
                     "000",
                     this.getAccountDiv(),
                     l_strRequestCode,
                     l_strSerialNo};
                    
                l_lisRowItems = null;
                l_lisRowItems =
                    l_queryProcesser.doFindAllQuery(
                        AccOpenVoucherItemRow.TYPE,
                        l_strWhereItem,
                        l_bindVarsItem2);             //DataQueryException, DataNetworkException
            }
            
            int l_intSize = 0;
            if (l_lisRowItems != null)
            {
                l_intSize = l_lisRowItems.size();
            }
             
            AccOpenVoucherItemRow l_accOpenVoucherItemRow = null;
            if (l_intSize > 0)
            {
                log.debug("２－１）　@カスタマイズ編集");
                //２－１）　@カスタマイズ編集
                for (int i = 0; i < l_intSize; i++)
                {
                    log.debug("２－１）　@カスタマイズ編集");
                    l_accOpenVoucherItemRow = (AccOpenVoucherItemRow)l_lisRowItems.get(i);
                    String l_strValue = null;
                    //（項目編集方法@ == 固定値）の場合、固定セット値の値をセットする。
                    if (WEB3EditWayDivDef.FIXED_VALUE.equals(l_accOpenVoucherItemRow.getEditWayDiv()))
                    {
                        l_strValue = l_accOpenVoucherItemRow.getFixedValue();
                    }
                    else
                    {
                        String l_strValue1 = this.nameCompare(l_accOpenVoucherItemRow.getInputItemSymbolName1());
                        String l_strValue2 = this.nameCompare(l_accOpenVoucherItemRow.getInputItemSymbolName2());
                        String l_strValue3 = this.nameCompare(l_accOpenVoucherItemRow.getInputItemSymbolName3());
                        if (WEB3EditWayDivDef.EXP_ACCOUNT_OPEN_ITEM_TO_HALFKANA.equals(l_accOpenVoucherItemRow.getEditWayDiv()))
                        {
                            l_strValue1 = WEB3StringTypeUtility.to1byteKana(l_strValue1);
                            l_strValue2 = WEB3StringTypeUtility.to1byteKana(l_strValue2);
                            l_strValue3 = WEB3StringTypeUtility.to1byteKana(l_strValue3);
                        }

                        //入力項目物理名１，２，３の値をデリミッタにて連結する。 
                        
                        if (l_accOpenVoucherItemRow.getCatDelimitter() != null 
                            && !WEB3CatDelimitterDef.WITHOUT.equals(l_accOpenVoucherItemRow.getCatDelimitter()))
                        {
                            if (l_strValue1 != null)
                            {
                                l_strValue = l_strValue1;
                                if (l_strValue2 != null)
                                {
                                    if (WEB3CatDelimitterDef.HALF_SPACE.equals(l_accOpenVoucherItemRow.getCatDelimitter()))
                                    {
                                        l_strValue = l_strValue + " " + l_strValue2;
                                    }
                                    else if (WEB3CatDelimitterDef.FULL_SPACE.equals(l_accOpenVoucherItemRow.getCatDelimitter()))
                                    {
                                        l_strValue = l_strValue + "　@" + l_strValue2;
                                    }
                                    else
                                    {
                                        l_strValue = l_strValue + "-" + l_strValue2;
                                    }
                                }
                                if (l_strValue3 != null)
                                {
                                    if (WEB3CatDelimitterDef.HALF_SPACE.equals(l_accOpenVoucherItemRow.getCatDelimitter()))
                                    {
                                        l_strValue = l_strValue + " " + l_strValue3;
                                    }
                                    else if (WEB3CatDelimitterDef.FULL_SPACE.equals(l_accOpenVoucherItemRow.getCatDelimitter()))
                                    {
                                        l_strValue = l_strValue + "　@" + l_strValue3;
                                    }
                                    else
                                    {
                                        l_strValue = l_strValue + "-" + l_strValue3;
                                    }
                                }
                            }
                        }
                        else
                        {
                            if (l_strValue1 != null)
                            {
                                l_strValue = l_strValue1;
                                if (l_strValue2 != null)
                                {
                                    l_strValue = l_strValue + l_strValue2;
                                }
                                if (l_strValue3 != null)
                                {
                                    l_strValue = l_strValue + l_strValue3;
                                }
                            }    
                        }
                    }
                    
                    log.debug("%%%%%%%%%%%l_strValue = %%%%%%%%%%%%%%" + l_strValue);
                    
                    //データコード
                    if (WEB3AccountOpenOutputItemSymbolNameDef.REQUEST_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        log.debug("%%%%%%%%%%%%%%データコード");
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_passwordVoucherParams.setRequestCode(super.getStringByByteNumber(l_strValue, 5));    
                    }
                    
                    //証券会社コード
                    if (WEB3AccountOpenOutputItemSymbolNameDef.INSTITUTION_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        log.debug("%%%%%%%%%%%%%%%%%証券会社コード");
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_passwordVoucherParams.setInstitutionCode(super.getStringByByteNumber(l_strValue, 3));
                    }
                    //部店コード
                    if (WEB3AccountOpenOutputItemSymbolNameDef.BRANCH_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        log.debug("%%%%%%%%%%%%%%%%部店コード");
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_passwordVoucherParams.setBranchCode(super.getStringByByteNumber(l_strValue, 3));
                    }
                    //顧客コード
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        log.debug("%%%%%%%%%%%%%%%顧客コード");
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_passwordVoucherParams.setAccountCode(super.getStringByByteNumber(l_strValue, 7));
                    }
                    //扱者コード
                    if (WEB3AccountOpenOutputItemSymbolNameDef.TRADER_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        log.debug("%%%%%%%%%%%%%%%%%%扱者コード");
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_passwordVoucherParams.setTraderCode(super.getStringByByteNumber(l_strValue, 5));
                    }
                    //識別コード（口座開設見込客）
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ACC_OPEN_REQUEST_NUMBER.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        log.debug("%%%%%%%%%%%%識別コード（口座開設見込客）");
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_passwordVoucherParams.setAccOpenRequestNumber(super.getStringByByteNumber(l_strValue, 13));
                    }
                        
                    //伝票通番
                    if (WEB3AccountOpenOutputItemSymbolNameDef.SERIAL_NO.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        log.debug("%%%%%%%%%%%%%%伝票通番");
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_passwordVoucherParams.setSerialNo(super.getStringByByteNumber(l_strValue, 3));    
                    }
                        
                    //発行取消区分
                    if (WEB3AccountOpenOutputItemSymbolNameDef.REGIST_DELETE_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        log.debug("%%%%%%%%%%%%%発行取消区分");
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_passwordVoucherParams.setRegistDeleteDiv(super.getStringByByteNumber(l_strValue, 1));    
                    }
                        
                    //デ－タ種別
                    if (WEB3AccountOpenOutputItemSymbolNameDef.DATA_CLASS.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_passwordVoucherParams.setDataClass(super.getStringByByteNumber(l_strValue, 2));    
                    }
                        
                    //データコード（伝票名）
                    if (WEB3AccountOpenOutputItemSymbolNameDef.VOUCHER_NAME.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_passwordVoucherParams.setVoucherName(super.getStringByByteNumber(l_strValue, 4));    
                    }
                        
                    //申込年月日
                    if (WEB3AccountOpenOutputItemSymbolNameDef.REGIST_DATE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_passwordVoucherParams.setRegistDate(super.getStringByByteNumber(l_strValue, 6));
    
                    }
                        
                    //発行理由
                    if (WEB3AccountOpenOutputItemSymbolNameDef.PUB_REASON_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_passwordVoucherParams.setPubReasonDiv(super.getStringByByteNumber(l_strValue, 1));    
                    }
                        
                    //整理番号
                    if (WEB3AccountOpenOutputItemSymbolNameDef.REF_NUMBER.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_passwordVoucherParams.setRefNumber(super.getStringByByteNumber(l_strValue, 5));
                    }
                        
                    //カードタイプ
                    if (WEB3AccountOpenOutputItemSymbolNameDef.CARD_TYPE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_passwordVoucherParams.setCardType(super.getStringByByteNumber(l_strValue, 1));   
                    }
                        
                    //オンライン・バッチ区分
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ONLINE_BATCH_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_passwordVoucherParams.setOnlineBatchDiv(super.getStringByByteNumber(l_strValue, 1));     
                    }
                        
                    //SEQ_NO
                    if (WEB3AccountOpenOutputItemSymbolNameDef.SEQ_NO.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_passwordVoucherParams.setSeqNo(super.getStringByByteNumber(l_strValue, 4));
                    }
                        
                    //処理区分
                    if (WEB3AccountOpenOutputItemSymbolNameDef.STATUS.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_passwordVoucherParams.setStatus(super.getStringByByteNumber(l_strValue, 1));    
                    }
                          
                    //暗証番号
                    if (WEB3AccountOpenOutputItemSymbolNameDef.PASSWORD.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_passwordVoucherParams.setPassword(super.getStringByByteNumber(l_strValue, 48));     
                    } 
                    //訂正氏名
                    if (WEB3AccountOpenOutputItemSymbolNameDef.MODIFIED_NAME.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_passwordVoucherParams.setModifiedName(super.getStringByByteNumber(l_strValue, 19));
                    } 
                }
            }
            
            //２－２）　@伝票の識別コード新規採番 
            WEB3HostReqOrderNumberManageService l_reqOrderNumberManageService=
                (WEB3HostReqOrderNumberManageService)Services.getService(
                    WEB3HostReqOrderNumberManageService.class);    
            String l_strNewNumber = l_reqOrderNumberManageService.getNewNumber(
                l_strInstitutionCode,
                l_strBranchCode,
                ProductTypeEnum.OTHER);
            l_passwordVoucherParams.setOrderRequestNumber(l_strNewNumber);
            
            //３－１）　@既存行削除
            String l_strWhere =
                "institution_code = ? and " +        //証券会社コード = this.get証券会社コード() And 
                "acc_open_request_number = ? and " +    //識別コード = this.get識別コード() And   
                "serial_no = ? and " +               //伝票通番 = 伝票通番 And   
                "status = ? ";                       //処理区分 = ”未処理”  

            Object l_bindVars[] =
                {l_strInstitutionCode,
                 l_strRequestNumber,
                 l_strSerialNo,
                 WEB3StatusDef.NOT_DEAL};
                    
            l_queryProcesser.doDeleteAllQuery(
                PasswordVoucherRow.TYPE,
                l_strWhere,
                l_bindVars);                        //DataQueryException, DataNetworkException
            
            //３－２）　@伝票行挿入 
            l_queryProcesser.doInsertQuery(l_passwordVoucherParams);  
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (saveDelete伝票行)<BR>
     * （saveDelete伝票行()の実装）<BR>
     * 口座開設伝票を１件削除する。<BR>
     * <BR>
     * 暗証番号伝票（G5511）テーブルの以下の条件に当てはまる行を取得する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@証券会社コード =  this.get証券会社コード() And<BR>
     * 　@識別コード = this.get識別コード() And<BR>
     * 　@伝票通番 = 伝票通番 And<BR>
     * 　@処理区分 = ”未処理”<BR>
     * <BR>
     * 行が取得できなかった場合、falseを返却する。<BR>
     * 行が取得できた場合、該当行の削除（delete row）を行い、trueを返却する。<BR>
     * @@param l_strSerialNo - 伝票通番
     *
     * @@return boolean
     * @@roseuid 419C2B7C02C2
     */
    public boolean saveDeleteVoucherRow(String l_strSerialNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveDeleteVoucherRow(String)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor(); //DataNetworkException, DataQueryException
            String l_strWhere =
                "institution_code = ? and " +        //証券会社コード = this.get証券会社コード() And 
                "acc_open_request_number = ? and " +    //識別コード = this.get識別コード() And   
                "serial_no = ? and " +               //伝票通番 = 伝票通番 And   
                "status = ? ";                       //処理区分 = ”未処理”  

            String l_strInstitutionCode = this.getInstitutionCode();
            String l_strRequestNumber = this.getRequestNumber();
            
            Object l_bindVars[] =
                {l_strInstitutionCode,
                 l_strRequestNumber,
                 l_strSerialNo,
                 WEB3StatusDef.NOT_DEAL};
                    
            List l_lisRows = l_queryProcesser.doFindAllQuery(
                PasswordVoucherRow.TYPE,
                l_strWhere,
                l_bindVars);                       //DataQueryException, DataNetworkException
                
            if (l_lisRows == null || l_lisRows.size() == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return false;    
            }
            else
            {
                l_queryProcesser.doDeleteAllQuery(
                    PasswordVoucherRow.TYPE,
                    l_strWhere,
                    l_bindVars);                  //DataQueryException, DataNetworkException
                
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
    }

    /**
     * @@return Object
     * @@roseuid 41B45E6E006D
     */
    public Object getDataSourceObject()
    {
        return null;
    }
    
    /**
     * 入力物理名と口座開設見込客テーブルの物理名の比較
     * 
     * @@return Object
     * @@roseuid 41B45E6D033C
     */
    private String nameCompare(String l_str)
    {
        final String STR_METHOD_NAME = " nameCompare(String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_str == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        ExpAccountOpenParams l_expAccountOpenParams = new ExpAccountOpenParams((ExpAccountOpenRow)this.accOpenExpAccountOpen.getDataSourceObject());

        Field[] l_field = l_expAccountOpenParams.getClass().getDeclaredFields();
        
        int l_int = 0;
        if (l_field != null)
        {
            l_int = l_field.length;
        }
        
        for (int i = 0; i < l_int; i++)
        {
            if (l_str.equals(l_field[i].getName()))
            {
                log.exiting(STR_METHOD_NAME);
                if (l_expAccountOpenParams.getColumn(l_str) != null)
                {
                    String l_strReturn = l_expAccountOpenParams.getColumn(l_str).toString();
                    return l_strReturn;  
                }
                else
                {
                    return null;  
                } 
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return null;   
    }
    
}
@
