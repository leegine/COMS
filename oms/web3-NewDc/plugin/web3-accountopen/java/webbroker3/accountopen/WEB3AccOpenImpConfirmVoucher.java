head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.29.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenImpConfirmVoucher.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 重要事項確認書伝票(WEB3AccOpenImpConfirmVoucher.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/12/21 李頴淵 新規作成
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
import webbroker3.accountopen.data.ImpConfirmVoucherParams;
import webbroker3.accountopen.data.ImpConfirmVoucherRow;
import webbroker3.accountopen.define.WEB3AccountOpenExpAccountOpenSymbolNameDef;
import webbroker3.accountopen.define.WEB3AccountOpenOutputItemSymbolNameDef;
import webbroker3.accountopen.define.WEB3AccountOpenVoucherCodeDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CatDelimitterDef;
import webbroker3.common.define.WEB3CategoryNoDef;
import webbroker3.common.define.WEB3ConfirmDivDef;
import webbroker3.common.define.WEB3DataClassDef;
import webbroker3.common.define.WEB3EditWayDivDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3RegDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (重要事項確認書伝票)<BR>
 * 重要事項確認書伝票<BR>
 * 
 * @@author 李頴淵
 * @@version 1.0 
 */
public class WEB3AccOpenImpConfirmVoucher extends WEB3AccOpenVoucher
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AccOpenImpConfirmVoucher.class);
    
    /**
     * @@roseuid 41B45E6D01F4
     */
    public WEB3AccOpenImpConfirmVoucher()
    {

    }

    /**
     * (getInstance)<BR>
     * 口座開設見込客オブジェクトを指定し、重要事項確認書伝票インスタンスを<BR>
     * 作成する。<BR>
     * <BR>
     * １）　@インスタンス生成<BR>
     * 　@重要事項確認書伝票インスタンスを生成する。<BR>
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
     * <BR>
     * ４）　@生成したインスタンスを返却する。<BR>
     * @@param l_accOpenExpAccountOpen - 口座開設見込客オブジェクト
     *
     * @@return webbroker3.accountopen.WEB3AccOpenImpConfirmVoucher
     * @@roseuid 4193108B0074
     */
    public static WEB3AccOpenImpConfirmVoucher getInstance(WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInstance(WEB3AccOpenExpAccountOpen)";
        log.entering(STR_METHOD_NAME);
        //１）　@インスタンス生成
        WEB3AccOpenImpConfirmVoucher l_accOpenImpConfirmVoucher = new WEB3AccOpenImpConfirmVoucher();
        
        //２）　@口座開設見込客プロパティセット
        l_accOpenImpConfirmVoucher.setAccOpenExpAccountOpen(l_accOpenExpAccountOpen);
        
        //３）　@口座開設伝票マスタプロパティセット
        l_accOpenImpConfirmVoucher.setAccOpenVoucherMaster();

        log.exiting(STR_METHOD_NAME);
        return l_accOpenImpConfirmVoucher;
    }

    /**
     * (isオンライン伝票)<BR>
     * （isオンライン伝票()の実装）<BR>
     * <BR>
     * falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 4193108B0076
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
     * データコード.重要事項（GI838）を返却する。<BR>
     * @@return String
     * @@roseuid 4193108B0084
     */
    public String getRequestCode()
    {
        final String STR_METHOD_NAME = " getRequestCode()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        return WEB3HostRequestCodeDef.ACCOPEN_IMPORTANT_ITEM;
    }

    /**
     * (get伝票コード)<BR>
     * （get伝票コード()の実装）<BR>
     * <BR>
     * ”G1159”を返却する。<BR>
     * @@return String
     * @@roseuid 419DDEF80343
     */
    public String getVoucherCode()
    {
        final String STR_METHOD_NAME = " getVoucherCode()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        return WEB3AccountOpenVoucherCodeDef.IMP_CONFIRM_VOUCHER_CODE;
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
     * 　@this.口座開設伝票マスタ行[]の各要素毎に、２－１）～２－３）の処理を実施する。<BR>
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
     * 　@　@重要事項確認書伝票（G1159）テーブルの各項目について、下記の<BR>
     * 処理を実施する。<BR>
     * <BR>
     * 　@　@this.getカスタマイズ参照項目()をコールし、口座開設見込客テーブル列<BR>
     * 物理名の配列を取得する。<BR>
     * <BR>
     * 　@　@[getカスタマイズ参照項目()に指定する引数]<BR>
     * 　@　@伝票通番：　@口座開設伝票マスタ行[index].伝票通番<BR>
     * 　@　@伝票出力項目物理名：　@（※1 重要事項確認書伝票（G1159）テーブルの<BR>
     * 処理対象項目）<BR>
     * 　@　@伝票参照項目初期値：　@（※2 重要事項確認書伝票（G1159）テーブルの<BR>
     * 処理対象項目デフォルト設定値）<BR>
     * <BR>
     * 　@　@（※2）　@重要事項確認書伝票（G1159）テーブルの処理対象項目<BR>
     * デフォルト設定値<BR>
     * 　@　@DBレイアウト 「重要事項確認書伝票（G1159）テーブル.xls#<BR>
     * デフォルトDB設定論理」シートを参照し、<BR>
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
     * @@roseuid 4193108B0086
     */
    public String[] getConfirmedItemName() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getConfirmedItemName()";
        log.entering(STR_METHOD_NAME);
        //伝票使用項目Table（：Hashtable）生成 
        Hashtable l_voucherItemList = new Hashtable();
        
        //this.口座開設伝票マスタ行[]の各要素毎に、２－１）～２－３）の処理を実施する。
        int l_intLength = 0;
        if (accOpenVoucherMasterParamses != null)
        {
            l_intLength = this.accOpenVoucherMasterParamses.length;
        }
        
        for (int i = 0; i < l_intLength; i++)
        {
            String l_strSerialNo = accOpenVoucherMasterParamses[i].getSerialNo();
            //２－１）　@作成可能判定
            boolean l_blnCreatedPossibleVoucher = this.isCreatedPossibleVoucher(l_strSerialNo);

            //作成可能でない場合（is作成可能伝票() == false）のみ、２－２）を実施する
            if (!l_blnCreatedPossibleVoucher)
            {                
                String[] l_strValues = new String[1];
                //U01000
                //証券会社コード
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.INSTITUTION_CODE;
                //２－２）　@カスタマイズ参照項目列物理名取得
                String[] l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.INSTITUTION_CODE, l_strValues);   
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
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.BRANCH_CODE;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.BRANCH_CODE, l_strValues);   
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
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
                }

                //顧客コード
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ACCOUNT_CODE;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_CODE, l_strValues);   
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
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
                }

                //扱者コード
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.SONAR_TRADER_CODE;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.TRADER_CODE, l_strValues);   
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
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
                }

                //識別コード（口座開設見込客）
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ACC_OPEN_REQUEST_NUMBER;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACC_OPEN_REQUEST_NUMBER, l_strValues);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
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
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //登録区分
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.REGIST_DIV, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
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
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //カテゴリＮＯ
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.CATEGORY_NO, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
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
                }
                
                //確認区分
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.CONFIRM_DIV, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
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
                }
                
                //備考
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.NOTE, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
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
                }
                
                //徴収日
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.CORRECT_DATE, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
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
     * 　@重要事項確認書伝票（G1159）テーブル行オブジェクトを生成し、<BR>
     * 　@デフォルト設定（※1）の通りにプロパティをセットする。<BR>
     * <BR>
     * 　@文字列をセットする場合、出力項目のデータサイズを超えた場合は、<BR>
     * データサイズ以降の文字を切り捨てる。<BR>
     * <BR>
     * 　@（※1）　@重要事項確認書伝票（G1159）テーブルの処理対象項目<BR>
     * デフォルト設定<BR>
     * 　@DBレイアウト 「重要事項確認書伝票（G1159）テーブル.xls#<BR>
     * デフォルトDB設定論理」シート参照。<BR>
     * <BR>
     * ２）　@カスタマイズ項目セット <BR>
     * 　@口座開設伝票項目マスタテーブルを以下の条件①@で検索する。 <BR>
     * 　@該当行がない場合、条件②で検索する。 <BR>
     * <BR>
     * 　@[条件①@] <BR>
     * 　@口座開設伝票項目マスタ.証券会社コード = this.get証券会社コード() And <BR>
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
     * <BR>　@
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
     * 　@　@以下の条件にて重要事項確認書伝票（G1159）テーブルを検索する。<BR>
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
     * @@roseuid 4193108B0087
     */
    public void saveVoucherRow(String l_strSerialNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveVoucherRow(String)";
        log.entering(STR_METHOD_NAME);
        //デフォルト設定行生成 
        ImpConfirmVoucherParams l_impConfirmVoucherParams = new ImpConfirmVoucherParams();
        
        //デフォルト設定（※1）の通りにプロパティをセットする。
        try
        {             
            String l_strInstitutionCode = this.accOpenExpAccountOpen.getInstitutionCode();
            String l_strBranchCode = this.accOpenExpAccountOpen.getBranchCode();
            String l_strAccountCode = this.accOpenExpAccountOpen.getAccountCode();
            String l_strRequestNumber = this.accOpenExpAccountOpen.getRequestNumber();

            //データコード
            l_impConfirmVoucherParams.setRequestCode(WEB3HostRequestCodeDef.ACCOPEN_IMPORTANT_ITEM);
            //証券会社コード
            l_impConfirmVoucherParams.setInstitutionCode(
                super.getStringByByteNumber(l_strInstitutionCode, 3));
            //部店コード                    
            l_impConfirmVoucherParams.setBranchCode(
                super.getStringByByteNumber(l_strBranchCode, 3));
            //顧客コード
            l_impConfirmVoucherParams.setAccountCode(
                super.getStringByByteNumber(l_strAccountCode, 7));
            
            //扱者コード
            ExpAccountOpenRow l_expAccountOpenRow = (ExpAccountOpenRow)this.accOpenExpAccountOpen.getDataSourceObject();
            String l_strSonarTraderCode = l_expAccountOpenRow.getSonarTraderCode();
            l_impConfirmVoucherParams.setTraderCode(
                super.getStringByByteNumber(l_strSonarTraderCode, 5));
            
            //識別コード（口座開設見込客）
            l_impConfirmVoucherParams.setAccOpenRequestNumber(
                super.getStringByByteNumber(l_strRequestNumber, 13));
            //伝票通番
            l_impConfirmVoucherParams.setSerialNo(
                super.getStringByByteNumber(l_strSerialNo, 3));
            //登録区分
            l_impConfirmVoucherParams.setRegistDiv(WEB3RegDivDef.NEW);
            //デ－タ種別
            l_impConfirmVoucherParams.setDataClass(WEB3DataClassDef.DATA_RECORD);
            //カテゴリＮＯ
            l_impConfirmVoucherParams.setCategoryNo(WEB3CategoryNoDef.DOMESTIC_EQUITY);
            
            //確認区分
            l_impConfirmVoucherParams.setConfirmDiv(WEB3ConfirmDivDef.CONFIRM_COMPLETE);
            //備考
            l_impConfirmVoucherParams.setNote(null);
            //徴収日
            l_impConfirmVoucherParams.setCorrectDate(null);
            //処理区分
            l_impConfirmVoucherParams.setStatus(WEB3StatusDef.NOT_DEAL);
            Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();
            //送信日時
            l_impConfirmVoucherParams.setSendTimestamp(null);
            //作成日時
            l_impConfirmVoucherParams.setCreatedTimestamp(l_tsSystemTimestamp);
            //更新日時
            l_impConfirmVoucherParams.setLastUpdatedTimestamp(l_tsSystemTimestamp);
            
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
                    l_bindVarsItem);    //DataQueryException, DataNetworkException
                     
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
                        l_bindVarsItem2);    //DataQueryException, DataNetworkException
            }
            
            int l_intSize = 0;
            if (l_lisRowItems != null)
            {
                l_intSize = l_lisRowItems.size();
            }
            
            AccOpenVoucherItemRow l_accOpenVoucherItemRow = null;
            if (l_intSize > 0)
            {
                //２－１）　@カスタマイズ編集
                log.debug("２－１）　@カスタマイズ編集");
                for (int i = 0; i < l_intSize; i++)
                {
                    l_accOpenVoucherItemRow = (AccOpenVoucherItemRow)l_lisRowItems.get(i);
                    String l_strValue = null;
                    //（項目編集方法@ == 固定値）の場合、固定セット値の値をセットする。
                    log.debug("（項目編集方法@ == 固定値）の場合、固定セット値の値をセットする。");
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
                    
                    //データコード
                    if (WEB3AccountOpenOutputItemSymbolNameDef.REQUEST_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_impConfirmVoucherParams.setRequestCode(super.getStringByByteNumber(l_strValue, 5));
                    }
                        
                    //証券会社コード
                    if (WEB3AccountOpenOutputItemSymbolNameDef.INSTITUTION_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_impConfirmVoucherParams.setInstitutionCode(super.getStringByByteNumber(l_strValue, 3));
                    }
                        
                    //部店コード
                    if (WEB3AccountOpenOutputItemSymbolNameDef.BRANCH_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_impConfirmVoucherParams.setBranchCode(super.getStringByByteNumber(l_strValue, 3));
                    }
                        
                    //顧客コード
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_impConfirmVoucherParams.setAccountCode(super.getStringByByteNumber(l_strValue, 7));
                    }
                        
                    //扱者コード
                    if (WEB3AccountOpenOutputItemSymbolNameDef.TRADER_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_impConfirmVoucherParams.setTraderCode(super.getStringByByteNumber(l_strValue, 5));
                    }
                        
                    //識別コード（口座開設見込客）
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ACC_OPEN_REQUEST_NUMBER.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_impConfirmVoucherParams.setAccOpenRequestNumber(super.getStringByByteNumber(l_strValue, 13));
                    }
                        
                    //伝票通番
                    if (WEB3AccountOpenOutputItemSymbolNameDef.SERIAL_NO.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_impConfirmVoucherParams.setSerialNo(super.getStringByByteNumber(l_strValue, 3));
                    }
                        
                    //登録区分
                    if (WEB3AccountOpenOutputItemSymbolNameDef.REGIST_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_impConfirmVoucherParams.setRegistDiv(super.getStringByByteNumber(l_strValue, 1));
                    }
                        
                    //デ－タ種別
                    if (WEB3AccountOpenOutputItemSymbolNameDef.DATA_CLASS.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_impConfirmVoucherParams.setDataClass(super.getStringByByteNumber(l_strValue, 2));
                    }
                    
                    //カテゴリＮＯ
                    if (WEB3AccountOpenOutputItemSymbolNameDef.CATEGORY_NO.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_impConfirmVoucherParams.setCategoryNo(super.getStringByByteNumber(l_strValue, 2));
                    }
            
                    //確認区分
                    if (WEB3AccountOpenOutputItemSymbolNameDef.CONFIRM_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_impConfirmVoucherParams.setConfirmDiv(super.getStringByByteNumber(l_strValue, 3));
                    }

                    //備考
                    if (WEB3AccountOpenOutputItemSymbolNameDef.NOTE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_impConfirmVoucherParams.setNote(super.getStringByByteNumber(l_strValue, 9));
                    }

                    //徴収日
                    l_impConfirmVoucherParams.setCorrectDate(null);
                        
                    //処理区分
                    if (WEB3AccountOpenOutputItemSymbolNameDef.STATUS.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_impConfirmVoucherParams.setStatus(super.getStringByByteNumber(l_strValue, 1));
                    }    
                }
            }
            
            //２－２）　@伝票の識別コード新規採番
            log.debug("伝票の識別コード新規採番 "); 
            WEB3HostReqOrderNumberManageService l_reqOrderNumberManageService=
                (WEB3HostReqOrderNumberManageService)Services.getService(
                    WEB3HostReqOrderNumberManageService.class);    
            String l_strNewNumber = l_reqOrderNumberManageService.getNewNumber(
                l_strInstitutionCode,
                l_strBranchCode,
                ProductTypeEnum.OTHER);
            l_impConfirmVoucherParams.setOrderRequestNumber(l_strNewNumber);
            
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
                ImpConfirmVoucherRow.TYPE,
                l_strWhere,
                l_bindVars);         //DataQueryException, DataNetworkException
            
            //３－２）　@伝票行挿入 
            l_queryProcesser.doInsertQuery(l_impConfirmVoucherParams);  
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
     * 重要事項確認書伝票（G1159）テーブルの以下の条件に当てはまる行を取得する。<BR>
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
     * @@roseuid 419C2C2C011C
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
                ImpConfirmVoucherRow.TYPE,
                l_strWhere,
                l_bindVars);               //DataQueryException, DataNetworkException
                
            if (l_lisRows == null || l_lisRows.size() == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return false;    
            }
            else
            {
                l_queryProcesser.doDeleteAllQuery(
                    ImpConfirmVoucherRow.TYPE,
                    l_strWhere,
                    l_bindVars);           //DataQueryException, DataNetworkException
                
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
     * @@roseuid 41B45E6D0232
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
