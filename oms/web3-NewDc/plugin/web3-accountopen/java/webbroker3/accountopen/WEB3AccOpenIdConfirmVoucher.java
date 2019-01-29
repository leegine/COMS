head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.29.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenIdConfirmVoucher.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 本人確認伝票(WEB3AccOpenIdConfirmVoucher.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/12/21 李頴淵 新規作成
                      2006/07/13 徐大方 仕様変更・モデル077
                      2006/07/18 徐大方 式樣變更・モデル081
Revesion History    : 2009/08/13 武波 ＤＢレイアウトNo.059,No.060
Revesion History    : 2009/08/28 張騰宇　@モデル194
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
import webbroker3.accountopen.data.IdConfirmVoucherParams;
import webbroker3.accountopen.data.IdConfirmVoucherRow;
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
import webbroker3.common.define.WEB3RegDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.gentrade.WEB3GentradeEra;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (本人確認伝票)<BR>
 * 本人確認伝票<BR>
 * 
 * @@author 李頴淵
 * @@version 1.0 
 */
public class WEB3AccOpenIdConfirmVoucher extends WEB3AccOpenVoucher
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AccOpenIdConfirmVoucher.class);

    /**
     * @@roseuid 41B45E6D00EA
     */
    public WEB3AccOpenIdConfirmVoucher()
    {

    }

    /**
     * (getInstance)<BR>
     * 口座開設見込客オブジェクトを指定し、本人確認伝票インスタンスを作成する。<BR>
     * <BR>
     * １）　@インスタンス生成<BR>
     * 　@本人確認伝票インスタンスを生成する。<BR>
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
     * @@return webbroker3.accountopen.WEB3AccOpenIdConfirmVoucher
     * @@roseuid 41931181018D
     */
    public static WEB3AccOpenIdConfirmVoucher getInstance(WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInstance(WEB3AccOpenExpAccountOpen)";
        log.entering(STR_METHOD_NAME);
        //１）　@インスタンス生成
        WEB3AccOpenIdConfirmVoucher l_accOpenIdConfirmVoucher = new WEB3AccOpenIdConfirmVoucher();
        
        //２）　@口座開設見込客プロパティセット
        l_accOpenIdConfirmVoucher.setAccOpenExpAccountOpen(l_accOpenExpAccountOpen);
        
        //３）　@口座開設伝票マスタプロパティセット
        l_accOpenIdConfirmVoucher.setAccOpenVoucherMaster();

        log.exiting(STR_METHOD_NAME);
        return l_accOpenIdConfirmVoucher;
    }

    /**
     * (isオンライン伝票)<BR>
     * （isオンライン伝票()の実装）<BR>
     * <BR>
     * falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 41931181019D
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
     * データコード.本人確認（GI839）を返却する。<BR>
     * @@return String
     * @@roseuid 41931181019E
     */
    public String getRequestCode()
    {
        final String STR_METHOD_NAME = " getRequestCode()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        return WEB3HostRequestCodeDef.ACCOPEN_ID_CONFIRM;
    }

    /**
     * (get伝票コード)<BR>
     * （get伝票コード()の実装）<BR>
     * <BR>
     * ”G1175”を返却する。<BR>
     * @@return String
     * @@roseuid 419DDFC101CC
     */
    public String getVoucherCode()
    {
        final String STR_METHOD_NAME = " getVoucherCode()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        return WEB3AccountOpenVoucherCodeDef.ID_CONFIRM_VOUCHER_CODE;
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
     * 　@　@本人確認伝票（G1175）テーブルの各項目について、下記の処理を実施する。<BR>
     * <BR>
     * 　@　@this.getカスタマイズ参照項目()をコールし、口座開設見込客テーブル列<BR>
     * 物理名の配列を取得する。<BR>
     * <BR>
     * 　@　@[getカスタマイズ参照項目()に指定する引数]<BR>
     * 　@　@伝票通番：　@口座開設伝票マスタ行[index].伝票通番<BR>
     * 　@　@伝票出力項目物理名：　@（※1 本人確認伝票（G1175）テーブルの<BR>
     * 処理対象項目）<BR>
     * 　@　@伝票参照項目初期値：　@（※2 本人確認伝票（G1175）テーブルの<BR>
     * 処理対象項目デフォルト設定値）<BR>
     * <BR>
     * 　@　@（※2）　@本人確認伝票（G1175）テーブルの処理対象項目デフォルト設定値<BR>
     * 　@　@DBレイアウト 「本人確認伝票（G1175）テーブル.xls#デフォルトDB設定論理」<BR>
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
     * @@roseuid 4193118101A0
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
                
                //本人属性区分
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ID_ATTRIBUTE_DIV, null);  
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
                
                //対象取引区分
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.TRADING_DIV, null);  
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
                
                //確認方法@区分
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.CONFIRM_WAY_DIV, null);  
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
                
                //確認書類区分
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ID_CONFIRM_DOC_DIV;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.CONFIRM_DOC_DIV, l_strValues);  
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
                
                //住所確認書類区分
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_CONFIRM_DOC, null);  
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
                
                //確認日
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.CONFIRM_DATE, null);  
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
                
                //取引動機@区分
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.APPLI_MOTIVAT_DIV, null);  
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
                
                //投資目的区分
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.INVEST_PURPOSE_DIV;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.INVEST_PURPOSE_DIV, l_strValues);  
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
                
                //投資経験（現物株式）
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_FLAG_EQUITY;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.EXPERIENCE_EQUITY, l_strValues);  
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
                
                //投資経験（信用取引）
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_FLAG_MARGIN;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.EXPERIENCE_MARGIN, l_strValues);  
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
                
                //投資経験（債券）
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_FLAG_BOND;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.EXPERIENCE_BOND, l_strValues);  
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
                
                //投資経験（投資信託）
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_FLAG_FUND_SK;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.EXPERIENCE_FUND, l_strValues);  
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
                
                //投資経験（先物・オプション）
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_FLAG_FO;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.EXPERIENCE_FO, l_strValues);  
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
                
                //投資経験（外国証券）
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_FLAG_F_EQUITY;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.EXPERIENCE_F_EQUITY, l_strValues);  
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
                
                //投資経験（その他）
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_FLAG_ETC;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.EXPERIENCE_ETC, l_strValues);  
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
                
                //経験年数（自）
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_FROM;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.EXPERIENCE_FROM, l_strValues);  
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
                
                //経験年数（至）
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_TO;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.EXPERIENCE_TO, l_strValues);  
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
                
                //取引種類（現物株式）
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_EQUITY;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.EQUITY_TRADE_DIV, l_strValues);  
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
                
                //取引種類（信用取引）
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_MARGIN;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.MARGIN_TRADE_DIIV, l_strValues);  
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
                
                //取引種類（債券）
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_BOND;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.BOND_TRADE_DIV, l_strValues);  
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
                
                //取引種類（投資信託）
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_FUND;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.FUND_TRADE_DIV, l_strValues);  
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
                
                //取引種類（先物・オプション）
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_FO;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.FO_TRADE_DIV, l_strValues);  
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
                
                //取引種類（外国証券）
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_F_EQUITY;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.F_EQUITY_TRADE_DIV, l_strValues);  
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
                
                //取引種類（その他）
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_ETC;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ETC_TRADE_DIV, l_strValues);  
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
                
                //年収（自）
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ANNUAL_INCOME_FROM;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ANNUAL_INCOME_FROM, l_strValues);  
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
                
                //年収（至）
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ANNUAL_INCOME_TO;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ANNUAL_INCOME_TO, l_strValues);  
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
                
                //金融資産（自）
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ASSET_VALUE_FROM;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ASSET_VALUE_FROM, l_strValues);  
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
                
                //金融資産（至）
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ASSET_VALUE_TO;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ASSET_VALUE_TO, l_strValues);  
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

                //担当者または代理人項目
                //区分
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.DIV, null);
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

                //関係
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.RELATION, null);
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

                //確認方法@
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.CONFIRM_WAY, null);
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

                //確認書類
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.CONFIRM_DOC, null);
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

                //住所確認書類
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.ADD_CONFIRM_DOC, null);
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

                //確認日
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.CHARGE_CONFIRM_DATE, null);
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

                //担当者（代理人）名
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.CHARGE_NAME, null);
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

                //生年月日
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.BORN_DATE, null);
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

                //連絡先電話番号(市外局番）
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.TELEPHONE1, null);
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

                //連絡先電話番号(局番）
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.TELEPHONE2, null);
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

                //連絡先電話番号(番号）
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.TELEPHONE3, null);
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

                //郵便番号（親）
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.ZIP_CODE1, null);
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

                //郵便番号（子）
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.ZIP_CODE2, null);
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

                //住所１（漢字）
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE1, null);
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

                //住所２（漢字）
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE2, null);
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

                //住所３（漢字）
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE3, null);
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

                //住所１（カナ）
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE1_KANA, null);
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

                //住所２（カナ）
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE2_KANA, null);
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

                //住所３（カナ）
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE3_KANA, null);
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
     * 　@本人確認伝票（G1175）テーブル行オブジェクトを生成し、<BR>
     * 　@デフォルト設定（※1）の通りにプロパティをセットする。<BR>
     * <BR>
     * 　@文字列をセットする場合、出力項目のデータサイズを超えた場合は、<BR>
     * データサイズ以降の文字を切り捨てる。<BR>
     * <BR>
     * 　@（※1）　@本人確認伝票（G1175）テーブルの処理対象項目デフォルト設定<BR>
     * 　@DBレイアウト 「本人確認伝票（G1175）テーブル.xls#デフォルトDB設定論理」<BR>
     * シート参照。<BR>
     * <BR>
     * ２）　@カスタマイズ項目セット <BR>
     * 　@口座開設伝票項目マスタテーブルを以下の条件①@で検索する。<BR>
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
     * 　@　@○ （項目編集方法@ == 和暦日付を西暦日付に変換）の場合、 <BR>
     * 　@　@　@　@和暦日付(入力項目物理名1(年号)、入力項目物理名2(年月日)が<BR>
     * 示す口座開設見込客の項目値)を西暦日付に変換（yyyymmdd）した値をセットする。 <BR>
     * 　@　@　@　@※西暦への変換は、WEB3GentradeEra#toDate()を使用する<BR>
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
     * 　@　@以下の条件にて本人確認伝票（G1175）テーブルを検索する。<BR>
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
     * @@roseuid 4193118101AC
     */
    public void saveVoucherRow(String l_strSerialNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveVoucherRow(String)";
        log.entering(STR_METHOD_NAME);
        //デフォルト設定行生成 
        IdConfirmVoucherParams l_idConfirmVoucherParams = new IdConfirmVoucherParams();
        
        //デフォルト設定（※1）の通りにプロパティをセットする。
        try
        {             
            String l_strInstitutionCode = this.accOpenExpAccountOpen.getInstitutionCode();
            String l_strBranchCode = this.accOpenExpAccountOpen.getBranchCode();
            String l_strAccountCode = this.accOpenExpAccountOpen.getAccountCode();
            String l_strRequestNumber = this.accOpenExpAccountOpen.getRequestNumber();

            //データコード
            l_idConfirmVoucherParams.setRequestCode(WEB3HostRequestCodeDef.ACCOPEN_ID_CONFIRM);
            //証券会社コード
            l_idConfirmVoucherParams.setInstitutionCode(super.getStringByByteNumber(l_strInstitutionCode, 3));            
            //部店コード                    
            l_idConfirmVoucherParams.setBranchCode(super.getStringByByteNumber(l_strBranchCode, 3));            
            //顧客コード
            l_idConfirmVoucherParams.setAccountCode(super.getStringByByteNumber(l_strAccountCode, 7));
            
            //扱者コード
            ExpAccountOpenRow l_expAccountOpenRow = (ExpAccountOpenRow)this.accOpenExpAccountOpen.getDataSourceObject();
            String l_strSonarTraderCode = l_expAccountOpenRow.getSonarTraderCode();
            l_idConfirmVoucherParams.setTraderCode(super.getStringByByteNumber(l_strSonarTraderCode, 5));
            
            //識別コード（口座開設見込客）
            l_idConfirmVoucherParams.setAccOpenRequestNumber(super.getStringByByteNumber(l_strRequestNumber, 13));
            //伝票通番
            l_idConfirmVoucherParams.setSerialNo(super.getStringByByteNumber(l_strSerialNo, 3));
            //登録区分
            l_idConfirmVoucherParams.setRegistDiv(WEB3RegDivDef.NEW);
            //デ－タ種別
            l_idConfirmVoucherParams.setDataClass(WEB3DataClassDef.DATA_RECORD);
            //本人属性区分
            l_idConfirmVoucherParams.setIdAttributeDiv("1");
            
            //対象取引区分
            l_idConfirmVoucherParams.setTradingDiv("1");
            //確認方法@区分
            l_idConfirmVoucherParams.setConfirmWayDiv("1");
            //確認書類区分
            l_idConfirmVoucherParams.setConfirmDocDiv(super.getStringByByteNumber(l_expAccountOpenRow.getIdConfirmDocDiv(), 2));
            //住所確認書類区分
            l_idConfirmVoucherParams.setAddressConfirmDoc("1");
            //確認日
            l_idConfirmVoucherParams.setConfirmDate(null);
            
            //取引動機@区分
            l_idConfirmVoucherParams.setAppliMotivatDiv("9");
            //投資目的区分
            l_idConfirmVoucherParams.setInvestPurposeDiv(super.getStringByByteNumber(l_expAccountOpenRow.getInvestPurposeDiv(), 1));
            //投資経験（現物株式）
            l_idConfirmVoucherParams.setExperienceEquity(super.getStringByByteNumber(Integer.toString(l_expAccountOpenRow.getExperienceFlagEquity().intValue()), 1));
            //投資経験（信用取引）
            l_idConfirmVoucherParams.setExperienceMargin(super.getStringByByteNumber(Integer.toString(l_expAccountOpenRow.getExperienceFlagMargin().intValue()), 1));
            //投資経験（債券）
            l_idConfirmVoucherParams.setExperienceBond(super.getStringByByteNumber(Integer.toString(l_expAccountOpenRow.getExperienceFlagBond().intValue()), 1));
            //投資経験（投資信託）
            l_idConfirmVoucherParams.setExperienceFund(super.getStringByByteNumber(Integer.toString(l_expAccountOpenRow.getExperienceFlagFundSk().intValue()), 1));
            //投資経験（先物・オプション）
            l_idConfirmVoucherParams.setExperienceFo(super.getStringByByteNumber(Integer.toString(l_expAccountOpenRow.getExperienceFlagFo().intValue()), 1));
            //投資経験（外国証券）
            l_idConfirmVoucherParams.setExperienceFEquity(super.getStringByByteNumber(Integer.toString(l_expAccountOpenRow.getExperienceFlagFEquity().intValue()), 1));
            //投資経験（その他）
            l_idConfirmVoucherParams.setExperienceEtc(super.getStringByByteNumber(Integer.toString(l_expAccountOpenRow.getExperienceFlagEtc().intValue()), 1));
            //経験年数（自）
            l_idConfirmVoucherParams.setExperienceFrom(super.getStringByByteNumber(l_expAccountOpenRow.getExperienceFrom(), 2));
            //経験年数（至）
            l_idConfirmVoucherParams.setExperienceTo(super.getStringByByteNumber(l_expAccountOpenRow.getExperienceTo(), 2));
            //取引種類（現物株式）
            l_idConfirmVoucherParams.setEquityTradeDiv(super.getStringByByteNumber(Integer.toString(l_expAccountOpenRow.getInterestFlagEquity().intValue()), 1));
            //取引種類（信用取引）
            l_idConfirmVoucherParams.setMarginTradeDiv(super.getStringByByteNumber(Integer.toString(l_expAccountOpenRow.getInterestFlagMargin().intValue()), 1));
            //取引種類（債券）
            l_idConfirmVoucherParams.setBondTradeDiv(super.getStringByByteNumber(Integer.toString(l_expAccountOpenRow.getInterestFlagBond().intValue()), 1));
            //取引種類（投資信託）
            l_idConfirmVoucherParams.setFundTradeDiv(super.getStringByByteNumber(Integer.toString(l_expAccountOpenRow.getInterestFlagFund().intValue()), 1));
            //取引種類（先物・オプション）
            l_idConfirmVoucherParams.setFoTradeDiv(super.getStringByByteNumber(Integer.toString(l_expAccountOpenRow.getInterestFlagFo().intValue()), 1));
            //取引種類（外国証券）
            l_idConfirmVoucherParams.setFEquityTradeDiv(super.getStringByByteNumber(Integer.toString(l_expAccountOpenRow.getInterestFlagFEquity().intValue()), 1));
            //取引種類（その他）
            l_idConfirmVoucherParams.setEtcTradeDiv(super.getStringByByteNumber(Integer.toString(l_expAccountOpenRow.getInterestFlagEtc().intValue()), 1));
            //年収（自）
            l_idConfirmVoucherParams.setAnnualIncomeFrom(super.getStringByByteNumber(l_expAccountOpenRow.getAnnualIncomeFrom(), 6));
            //年収（至）
            l_idConfirmVoucherParams.setAnnualIncomeTo(super.getStringByByteNumber(l_expAccountOpenRow.getAnnualIncomeTo(), 6));
            //金融資産（自）
            l_idConfirmVoucherParams.setAssetValueFrom(super.getStringByByteNumber(l_expAccountOpenRow.getAssetValueFrom(), 6));
            //金融資産（至）
            l_idConfirmVoucherParams.setAssetValueTo(super.getStringByByteNumber(l_expAccountOpenRow.getAssetValueTo(), 6));
            //処理区分
            l_idConfirmVoucherParams.setStatus(WEB3StatusDef.NOT_DEAL);
            Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();
            //送信日時
            l_idConfirmVoucherParams.setSendTimestamp(null);
            //作成日時
            l_idConfirmVoucherParams.setCreatedTimestamp(l_tsSystemTimestamp);
            //更新日時
            l_idConfirmVoucherParams.setLastUpdatedTimestamp(l_tsSystemTimestamp);

            //担当者または代理人項目
            l_idConfirmVoucherParams.setDiv(null);
            l_idConfirmVoucherParams.setRelation(null);
            l_idConfirmVoucherParams.setConfirmWay(null);
            l_idConfirmVoucherParams.setConfirmDoc(null);
            l_idConfirmVoucherParams.setAddConfirmDoc(null);
            l_idConfirmVoucherParams.setChargeConfirmDate(null);
            l_idConfirmVoucherParams.setChargeName(null);
            l_idConfirmVoucherParams.setBornDate(null);
            l_idConfirmVoucherParams.setTelephone1(null);
            l_idConfirmVoucherParams.setTelephone2(null);
            l_idConfirmVoucherParams.setTelephone3(null);
            l_idConfirmVoucherParams.setZipCode1(null);
            l_idConfirmVoucherParams.setZipCode2(null);
            l_idConfirmVoucherParams.setAddressLine1(null);
            l_idConfirmVoucherParams.setAddressLine2(null);
            l_idConfirmVoucherParams.setAddressLine3(null);
            l_idConfirmVoucherParams.setAddressLine1Kana(null);
            l_idConfirmVoucherParams.setAddressLine2Kana(null);
            l_idConfirmVoucherParams.setAddressLine3Kana(null);

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
                    l_bindVarsItem);        //DataQueryException, DataNetworkException
                     
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
                        l_bindVarsItem2);     //DataQueryException, DataNetworkException
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
                for (int i = 0; i < l_intSize; i++)
                {
                    l_accOpenVoucherItemRow = (AccOpenVoucherItemRow)l_lisRowItems.get(i);
                    String l_strValue = null;
                    log.debug("（項目編集方法@ == 固定値）の場合、固定セット値の値をセットする。");
                    //（項目編集方法@ == 固定値）の場合、固定セット値の値をセットする。
                    if (WEB3EditWayDivDef.FIXED_VALUE.equals(l_accOpenVoucherItemRow.getEditWayDiv()))
                    {
                        l_strValue = l_accOpenVoucherItemRow.getFixedValue();
                    }
                    else if (WEB3EditWayDivDef.WEST_DATE_CHANGE_TO_JAP_DATE.equals(l_accOpenVoucherItemRow.getEditWayDiv()))
                    {
                        String l_strValue1 = this.nameCompare(l_accOpenVoucherItemRow.getInputItemSymbolName1());
                        String l_strValue2 = this.nameCompare(l_accOpenVoucherItemRow.getInputItemSymbolName2());
                        l_strValue = WEB3DateUtility.formatDate(
                            WEB3GentradeEra.toDate(l_strValue1, l_strValue2), WEB3GentradeTimeDef.DATE_FORMAT_YMD);
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

                        log.debug("入力項目物理名１，２，３の値をデリミッタにて連結する");
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
                    
                    int l_intValueLength = 0;
                    if (l_strValue != null)
                    {
                        l_intValueLength = WEB3StringTypeUtility.getByteLength(l_strValue);
                    }
                    
                    //データコード
                    if (WEB3AccountOpenOutputItemSymbolNameDef.REQUEST_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {         
                        l_idConfirmVoucherParams.setRequestCode(super.getStringByByteNumber(l_strValue, 5));          
                    }
                        
                    //証券会社コード
                    if (WEB3AccountOpenOutputItemSymbolNameDef.INSTITUTION_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる 
                        l_idConfirmVoucherParams.setInstitutionCode(super.getStringByByteNumber(l_strValue, 3));    
                    }
                        
                    //部店コード
                    if (WEB3AccountOpenOutputItemSymbolNameDef.BRANCH_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setBranchCode(super.getStringByByteNumber(l_strValue, 3));    
                    }
                        
                    //顧客コード
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる  
                        l_idConfirmVoucherParams.setAccountCode(super.getStringByByteNumber(l_strValue, 7));   
                    }
                        
                    //扱者コード
                    if (WEB3AccountOpenOutputItemSymbolNameDef.TRADER_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setTraderCode(super.getStringByByteNumber(l_strValue, 5));     
                    }
                        
                    //識別コード（口座開設見込客）
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ACC_OPEN_REQUEST_NUMBER.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setAccOpenRequestNumber(super.getStringByByteNumber(l_strValue, 13));     
                    }
                        
                    //伝票通番
                    if (WEB3AccountOpenOutputItemSymbolNameDef.SERIAL_NO.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setSerialNo(super.getStringByByteNumber(l_strValue, 3));     
                    }
                        
                    //登録区分
                    if (WEB3AccountOpenOutputItemSymbolNameDef.REGIST_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setRegistDiv(super.getStringByByteNumber(l_strValue, 1));     
                    }
                    
                    //デ－タ種別
                    if (WEB3AccountOpenOutputItemSymbolNameDef.DATA_CLASS.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる    
                        l_idConfirmVoucherParams.setDataClass(super.getStringByByteNumber(l_strValue, 2)); 
                    }

                    //本人属性区分
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ID_ATTRIBUTE_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setIdAttributeDiv(super.getStringByByteNumber(l_strValue, 1));   
                    }
            
                    //対象取引区分
                    if (WEB3AccountOpenOutputItemSymbolNameDef.TRADING_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる     
                        l_idConfirmVoucherParams.setTradingDiv(super.getStringByByteNumber(l_strValue, 1));   
                    }

                    //確認方法@区分
                    if (WEB3AccountOpenOutputItemSymbolNameDef.CONFIRM_WAY_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setConfirmWayDiv(super.getStringByByteNumber(l_strValue, 1));     
                    }

                    //確認書類区分
                    if (WEB3AccountOpenOutputItemSymbolNameDef.CONFIRM_DOC_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setConfirmDocDiv(super.getStringByByteNumber(l_strValue, 2));     
                    }

                    //住所確認書類区分
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_CONFIRM_DOC.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる 
                        l_idConfirmVoucherParams.setAddressConfirmDoc(super.getStringByByteNumber(l_strValue, 1));     
                    }

                    //確認日
                    if (WEB3AccountOpenOutputItemSymbolNameDef.CONFIRM_DATE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        if (l_intValueLength >= 8)
                        {
                            l_idConfirmVoucherParams.setConfirmDate(
                                WEB3DateUtility.getDate(l_strValue, WEB3GentradeTimeDef.DATE_FORMAT_YMD));
                        }
                    }

                    //取引動機@区分
                    if (WEB3AccountOpenOutputItemSymbolNameDef.APPLI_MOTIVAT_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setAppliMotivatDiv(super.getStringByByteNumber(l_strValue, 1));     
                    }

                    //投資目的区分
                    if (WEB3AccountOpenOutputItemSymbolNameDef.INVEST_PURPOSE_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setInvestPurposeDiv(super.getStringByByteNumber(l_strValue, 1));     
                    }

                    //投資経験（現物株式）
                    if (WEB3AccountOpenOutputItemSymbolNameDef.EXPERIENCE_EQUITY.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setExperienceEquity(super.getStringByByteNumber(l_strValue, 1));    
                    }
                    
                    //投資経験（信用取引）
                    if (WEB3AccountOpenOutputItemSymbolNameDef.EXPERIENCE_MARGIN.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setExperienceMargin(super.getStringByByteNumber(l_strValue, 1));    
                    }
                    
                    //投資経験（債券）
                    if (WEB3AccountOpenOutputItemSymbolNameDef.EXPERIENCE_BOND.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setExperienceBond(super.getStringByByteNumber(l_strValue, 1));      
                    }
            
                    //投資経験（投資信託）
                    if (WEB3AccountOpenOutputItemSymbolNameDef.EXPERIENCE_FUND.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setExperienceFund(super.getStringByByteNumber(l_strValue, 1));     
                    }
                    
                    //投資経験（先物・オプション）
                    if (WEB3AccountOpenOutputItemSymbolNameDef.EXPERIENCE_FO.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setExperienceFo(super.getStringByByteNumber(l_strValue, 1));     
                    }
                    
                    //投資経験（外国証券）
                    if (WEB3AccountOpenOutputItemSymbolNameDef.EXPERIENCE_F_EQUITY.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setExperienceFEquity(super.getStringByByteNumber(l_strValue, 1));     
                    }
                    
                    //投資経験（その他）
                    if (WEB3AccountOpenOutputItemSymbolNameDef.EXPERIENCE_ETC.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setExperienceEtc(super.getStringByByteNumber(l_strValue, 1));      
                    }
            
                    //経験年数（自）
                    if (WEB3AccountOpenOutputItemSymbolNameDef.EXPERIENCE_FROM.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setExperienceFrom(super.getStringByByteNumber(l_strValue, 2));      
                    }
                    
                    //経験年数（至）
                    if (WEB3AccountOpenOutputItemSymbolNameDef.EXPERIENCE_TO.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setExperienceTo(super.getStringByByteNumber(l_strValue, 2));     
                    }
                    
                    //取引種類（現物株式）
                    if (WEB3AccountOpenOutputItemSymbolNameDef.EQUITY_TRADE_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setEquityTradeDiv(super.getStringByByteNumber(l_strValue, 1));     
                    }
                    
                    //取引種類（信用取引）
                    if (WEB3AccountOpenOutputItemSymbolNameDef.MARGIN_TRADE_DIIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setMarginTradeDiv(super.getStringByByteNumber(l_strValue, 1));     
                    }
                    
                    //取引種類（債券）
                    if (WEB3AccountOpenOutputItemSymbolNameDef.BOND_TRADE_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setBondTradeDiv(super.getStringByByteNumber(l_strValue, 1));     
                    }
            
                    //取引種類（投資信託）
                    if (WEB3AccountOpenOutputItemSymbolNameDef.FUND_TRADE_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setFundTradeDiv(super.getStringByByteNumber(l_strValue, 1));       
                    }
                    
                    //取引種類（先物・オプション）
                    if (WEB3AccountOpenOutputItemSymbolNameDef.FO_TRADE_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setFoTradeDiv(super.getStringByByteNumber(l_strValue, 1));            
                    }
                    
                    //取引種類（外国証券）
                    if (WEB3AccountOpenOutputItemSymbolNameDef.F_EQUITY_TRADE_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setFEquityTradeDiv(super.getStringByByteNumber(l_strValue, 1));     
                    }
                    
                    //取引種類（その他）
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ETC_TRADE_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
     
                        l_idConfirmVoucherParams.setEtcTradeDiv(super.getStringByByteNumber(l_strValue, 1));    
                    }
            
                    //年収（自）
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ANNUAL_INCOME_FROM.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setAnnualIncomeFrom(super.getStringByByteNumber(l_strValue, 6));
                    }
                    
                    //年収（至）
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ANNUAL_INCOME_TO.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setAnnualIncomeTo(super.getStringByByteNumber(l_strValue, 6));   
                    }

                    //金融資産（自）
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ASSET_VALUE_FROM.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setAssetValueFrom(super.getStringByByteNumber(l_strValue, 6));
    
                    }

                    //金融資産（至）
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ASSET_VALUE_TO.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setAssetValueTo(super.getStringByByteNumber(l_strValue, 6));     
                    }
                        
                    //処理区分
                    if (WEB3AccountOpenOutputItemSymbolNameDef.STATUS.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setStatus(super.getStringByByteNumber(l_strValue, 1));          
                    }    
                    //担当者または代理人項目
                    //区分
                    if (WEB3AccountOpenOutputItemSymbolNameDef.DIV.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、
                        //データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setDiv(super.getStringByByteNumber(l_strValue, 1));          
                    }
                    //関係
                    if (WEB3AccountOpenOutputItemSymbolNameDef.RELATION.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、
                        //データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setRelation(super.getStringByByteNumber(l_strValue, 1));          
                    }
                    //確認方法@
                    if (WEB3AccountOpenOutputItemSymbolNameDef.CONFIRM_WAY.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、
                        //データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setConfirmWay(super.getStringByByteNumber(l_strValue, 1));          
                    }
                    //確認書類
                    if (WEB3AccountOpenOutputItemSymbolNameDef.CONFIRM_DOC.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、
                        //データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setConfirmDoc(super.getStringByByteNumber(l_strValue, 2));          
                    }
                    //住所確認書類
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ADD_CONFIRM_DOC.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、
                        //データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setAddConfirmDoc(super.getStringByByteNumber(l_strValue, 1));          
                    }
                    //確認日
                    if (WEB3AccountOpenOutputItemSymbolNameDef.CHARGE_CONFIRM_DATE.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、
                        //データサイズ以降の文字を切り捨てる
                        if (l_intValueLength >= 8)
                        {
                            l_idConfirmVoucherParams.setChargeConfirmDate(
                                WEB3DateUtility.getDate(l_strValue, WEB3GentradeTimeDef.DATE_FORMAT_YMD));
                        }
                    }
                    //担当者（代理人）名
                    if (WEB3AccountOpenOutputItemSymbolNameDef.CHARGE_NAME.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、
                        //データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setChargeName(super.getStringByByteNumber(l_strValue, 18));
                    }
                    //生年月日
                    if (WEB3AccountOpenOutputItemSymbolNameDef.BORN_DATE.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、
                        //データサイズ以降の文字を切り捨てる
                        if (l_intValueLength >= 8)
                        {
                            l_idConfirmVoucherParams.setBornDate(
                                WEB3DateUtility.getDate(l_strValue, WEB3GentradeTimeDef.DATE_FORMAT_YMD));
                        }
                    }
                    //連絡先電話番号(市外局番）
                    if (WEB3AccountOpenOutputItemSymbolNameDef.TELEPHONE1.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、
                        //データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setTelephone1(super.getStringByByteNumber(l_strValue, 16));
                    }
                    //連絡先電話番号(局番）
                    if (WEB3AccountOpenOutputItemSymbolNameDef.TELEPHONE2.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、
                        //データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setTelephone2(super.getStringByByteNumber(l_strValue, 4));
                    }
                    //連絡先電話番号(番号）
                    if (WEB3AccountOpenOutputItemSymbolNameDef.TELEPHONE3.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、
                        //データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setTelephone3(super.getStringByByteNumber(l_strValue, 4));
                    }
                    //郵便番号（親）
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ZIP_CODE1.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、
                        //データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setZipCode1(super.getStringByByteNumber(l_strValue, 7));
                    }
                    //郵便番号（子）
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ZIP_CODE2.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、
                        //データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setZipCode2(super.getStringByByteNumber(l_strValue, 4));
                    }
                    //住所１（漢字）
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE1.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、
                        //データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setAddressLine1(super.getStringByByteNumber(l_strValue, 32));
                    }
                    //住所２（漢字）
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE2.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、
                        //データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setAddressLine2(super.getStringByByteNumber(l_strValue, 32));
                    }
                    //住所３（漢字）
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE3.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、
                        //データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setAddressLine3(super.getStringByByteNumber(l_strValue, 32));
                    }
                    //住所１（カナ）
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE1_KANA.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、
                        //データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setAddressLine1Kana(super.getStringByByteNumber(l_strValue, 16));
                    }
                    //住所２（カナ）
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE2_KANA.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、
                        //データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setAddressLine2Kana(super.getStringByByteNumber(l_strValue, 27));
                    }
                    //住所３（カナ）
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE3_KANA.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、
                        //データサイズ以降の文字を切り捨てる
                        l_idConfirmVoucherParams.setAddressLine3Kana(super.getStringByByteNumber(l_strValue, 27));
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
            l_idConfirmVoucherParams.setOrderRequestNumber(l_strNewNumber);
            
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
                IdConfirmVoucherRow.TYPE,
                l_strWhere,
                l_bindVars);       //DataQueryException, DataNetworkException
            
            //３－２）　@伝票行挿入 
            l_queryProcesser.doInsertQuery(l_idConfirmVoucherParams);  
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
     * 本人確認伝票（G1175）テーブルの以下の条件に当てはまる行を取得する。<BR>
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
     * @@roseuid 419C2C8B0293
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
                IdConfirmVoucherRow.TYPE,
                l_strWhere,
                l_bindVars);   //DataQueryException, DataNetworkException
                
            if (l_lisRows == null || l_lisRows.size() == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return false;    
            }
            else
            {
                l_queryProcesser.doDeleteAllQuery(
                    IdConfirmVoucherRow.TYPE,
                    l_strWhere,
                    l_bindVars);    //DataQueryException, DataNetworkException
                
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
     * @@roseuid 41B45E6D0119
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
