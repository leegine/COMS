head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.30.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenAccountRegBrokerageFirmVoucher.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 顧客登録（仲介業）伝票(WEB3AccOpenAccountRegBrokerageFirmVoucher.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2006/07/07 徐大方 新規作成
		              2006/07/13 徐大方 仕様変更・モデル077
		              2006/07/18 徐大方 式樣變更・モデル081
                      2006/10/12 柴雙紅 ＤＢレイアウト  No.038
Revesion History    : 2007/09/28 肖志偉(中訊) 仕様変更 DBレイアウト047
Revesion History    : 2008/07/25 馮海濤(中訊) DB更新仕様　@No.034
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
import webbroker3.accountopen.data.HostAccRegVoucherParams;
import webbroker3.accountopen.data.HostAccRegVoucherRow;
import webbroker3.accountopen.define.WEB3AccountOpenExpAccountOpenSymbolNameDef;
import webbroker3.accountopen.define.WEB3AccountOpenOutputItemSymbolNameDef;
import webbroker3.accountopen.define.WEB3AccountOpenVoucherCodeDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccRegAccountDivDef;
import webbroker3.common.define.WEB3AccRegTaxationDivDef;
import webbroker3.common.define.WEB3AccountOpenDef;
import webbroker3.common.define.WEB3AgentDivDef;
import webbroker3.common.define.WEB3CatDelimitterDef;
import webbroker3.common.define.WEB3DividendTransferDivDef;
import webbroker3.common.define.WEB3DocumentDef;
import webbroker3.common.define.WEB3EditWayDivDef;
import webbroker3.common.define.WEB3ForeignerDivDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3ProamDivDef;
import webbroker3.common.define.WEB3RegDivDef;
import webbroker3.common.define.WEB3ResidentDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3TaxationDivDef;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (顧客登録（仲介業）伝票)<BR>
 * 顧客登録（仲介業）伝票<BR>
 * 
 * @@author 徐大方
 * @@version 1.0 
 */
public class WEB3AccOpenAccountRegBrokerageFirmVoucher extends WEB3AccOpenVoucher
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenAccountRegBrokerageFirmVoucher.class);

    /**
     * @@roseuid 41B45E6E0148
     */
    public WEB3AccOpenAccountRegBrokerageFirmVoucher()
    {

    }    

    /**
     *口座開設見込客オブジェクトを指定し、顧客登録（仲介業）伝票インスタンスを作成する。<BR> 
     *<BR>
     *１）インスタンス生成<BR> 　@
     *　@顧客登録（仲介業）伝票インスタンスを生成する 。<BR>
     *<BR>
     *２）口座開設見込客プロパティセット<BR> 　@
     *　@生成したインスタンス.set口座開設見込客() にて 、<BR> 
     *　@口座開設見込客プロパティをセットする 。<BR>
     *<BR>
     *　@[set口座開設見込客()に指定する引数]<BR> 
     *　@口座開設見込客：　@口座開設見込客<BR> 
     *<BR>
     *３）口座開設伝票マスタプロパティセット <BR>
     *　@生成したインスタンス.set伝票マスタ()にて、<BR>
     *  伝票マスタ行プロパティをセットする。<BR> 
     *<BR>
     *４）生成したインスタンスを返却する。<BR>
     *@@param l_accOpenExpAccountOpen - (口座開設見込客)<BR>
     *口座開設見込客オブジェクト<BR>
     *@@return WEB3AccOpenRealNameRegistVoucher 
     *@@roseuid 41930C8C0381 
     */
    public static WEB3AccOpenAccountRegBrokerageFirmVoucher getInstance(WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInstance(WEB3AccOpenExpAccountOpen)";
        log.entering(STR_METHOD_NAME);
        //１）　@インスタンス生成
        WEB3AccOpenAccountRegBrokerageFirmVoucher l_accOpenAccountRegBrokerageFirmVoucher = new WEB3AccOpenAccountRegBrokerageFirmVoucher();
        
        //２）　@口座開設見込客プロパティセット
        l_accOpenAccountRegBrokerageFirmVoucher.setAccOpenExpAccountOpen(l_accOpenExpAccountOpen);

        //３）　@口座開設伝票マスタプロパティセット
        l_accOpenAccountRegBrokerageFirmVoucher.setAccOpenVoucherMaster();

        log.exiting(STR_METHOD_NAME);
        return l_accOpenAccountRegBrokerageFirmVoucher;
    }
    
    /**
     *(isオンライン伝票)<BR>
     *(isオンライン伝票()の実装）<BR>
     *<BR>
     *trueを返却する。<BR>
     *@@return boolean
     *@@roseuid 41930C8C0383
     */
    public boolean isOnlineVoucher()
    {
        final String STR_METHOD_NAME = " isOnlineVoucher()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        return true;
    }
    
    /**
     *(getデータコード)<BR>
     *(getデータコード()の実装）<BR>
     *<BR>
     *データコード.顧客登録・仲介業（GI845）を返却する。 <BR>
     *@@return String
     *@@roseuid 41930C8C0384
     */
    public String getRequestCode()
    {
        final String STR_METHOD_NAME = " getRequestCode()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        return WEB3HostRequestCodeDef.ACCOPEN_REG_BROKERAGE;
    }
    
    /**
     *(get伝票コード)<BR>
     *(get伝票コード()の実装）<BR>
     *<BR>
     *”G11”を返却する。<BR>
     *@@return String
     *@@roseuid 419DDF6E021A
     */
    public String getVoucherCode()
    {
        final String STR_METHOD_NAME = " getVoucherCode()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        return WEB3AccountOpenVoucherCodeDef.ACCOUNT_REG_VOUCHER_CODE;
    }
    
    /**
     *(get確定済項目名)<BR>
     *(get確定済項目名()の実装）<BR>
     *<BR>
     *当該伝票で使用している口座開設見込客の列物理名を配列で取得する。<BR> 
     *<BR>
     *１）伝票使用項目Table（：Hashtable）生成<BR> 
     *　@Hashtableを生成する。<BR> 
     *<BR>
     *　@２）使用項目セット<BR> 
     *<BR>
     *　@this.口座開設伝票マスタ行[]の各要素毎に、２－１）～２－３）の処理を実施する。<BR> 
     *<BR>
     *２－１）作成可能判定<BR> 
     *　@is作成可能伝票()にて、作成可能な伝票かを判定する。<BR> 
     *<BR>
     *　@[is作成可能伝票()に指定する引数]<BR> 
     *　@伝票通番：　@口座開設伝票マスタ行[index].伝票通番<BR> 
     *<BR>
     *　@作成可能でない場合（is作成可能伝票() == false）のみ、２－２）を実施する。<BR> 
     *　@作成可能な場合（is作成可能伝票() == true）、当該要素の処理を行わず次の<BR>
     *　@要素を処理する。（continue;）<BR> 
     *<BR>
     *　@＃作成可能な場合、項目値は変更しても良いので伝票参照項目名には含めない。<BR> 
     *<BR>
     *２－２）カスタマイズ参照項目列物理名取得<BR>
     *　@顧客登録伝票（G11）キューテーブルの各項目について、下記の処理を実施する。 <BR> 
     *<BR>
     *　@this.getカスタマイズ参照項目()をコールし、口座開設見込客テーブル列物理名の<BR>
     *　@配列を取得する。<BR> 
     *<BR>
     *　@[getカスタマイズ参照項目()に指定する引数]<BR> 
     *　@伝票通番：　@口座開設伝票マスタ行[index].伝票通番<BR>
     *　@伝票出力項目物理名：　@（※1 顧客登録伝票（G11）キューテーブルの処理対象項目）<BR> 
     *　@伝票参照項目初期値：　@（※2 顧客登録伝票（G11）キューテーブルの<BR>
     *　@処理対象項目デフォルト設定値）<BR> 
     *<BR>
     *　@（※2）　@顧客登録伝票（G11）キューテーブルの処理対象項目デフォルト設定値<BR> 
     *　@DBレイアウト 「顧客登録伝票（G11）キューテーブル.xls#<BR>
     *　@デフォルトDB設定論理・仲介業」シートを参照し、<BR>
     *　@該当項目の説明欄に、口座開設見込客テーブルから編集する指定があれば、<BR>
     *　@指定項目の列物理名配列。<BR>
     *　@以外は、null。<BR>
     *<BR>
     *２－３）伝票使用項目Table（：Hashtable）に追加<BR>
     *　@伝票使用項目Table（：Hashtable）.put()にてthis.getカスタマイズ参照項目()戻り値を<BR>
     *　@一要素ずつ追加する。<BR> 
     *<BR>
     *　@[put()に指定する引数]<BR>
     *　@key：　@this.getカスタマイズ参照項目()[n]<BR> 
     *　@value：　@this.getカスタマイズ参照項目()[n]<BR> 
     *<BR>
     *　@※ key，valueに同じ値をセットする。<BR> 
     *<BR>
     *３）項目名配列返却<BR> 
     *　@伝票使用項目Table（：Hashtable）.values()　@を返却する。<BR> 
     *@@return String[]
     *@@throws WEB3BaseException
     *@@roseuid 41930C8C0392
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
            log.debug("口座開設伝票マスタ行[]の各要素毎に、２－１）～２－３）の処理を実施する");
            String l_strSerialNo = accOpenVoucherMasterParamses[i].getSerialNo();
            //２－１）　@作成可能判定
            boolean l_blnCreatedPossibleVoucher = this.isCreatedPossibleVoucher(l_strSerialNo);

            //作成可能でない場合（is作成可能伝票() == false）のみ、２－２）を実施する
            if (!l_blnCreatedPossibleVoucher)
            {                
                log.debug("作成可能でない場合（is作成可能伝票() == false）のみ、２－２）を実施する");
                String[] l_strValues1 = new String[1];

                //証券会社コード:口座開設見込客.証券会社コードを編集する。
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.INSTITUTION_CODE;
                //２－２）　@カスタマイズ参照項目列物理名取得
                String[] l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.INSTITUTION_CODE, l_strValues1);   
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                int l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }                     
                
                //データコード:null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.REQUEST_CODE, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("**********************データコード");
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }

                //部店コード:口座開設見込客.部店コードを編集する。
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.BRANCH_CODE;
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.BRANCH_CODE, l_strValues1);   
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }

                //顧客コード:口座開設見込客.口座コードを編集する。
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ACCOUNT_CODE;
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_CODE, l_strValues1);   
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }

                //扱者コード:口座開設見込客.扱者コード（SONAR）を編集する。
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.SONAR_TRADER_CODE;
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.TRADER_CODE, l_strValues1);   
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }

                //識別コード（口座開設見込客）:口座開設見込客.識別コードを編集する。
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ACC_OPEN_REQUEST_NUMBER;
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACC_OPEN_REQUEST_NUMBER, l_strValues1);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //伝票通番:null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.SERIAL_NO, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //登録区分:null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.REGIST_DIV, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //顧客名（ｶﾅ）:口座開設見込客.顧客姓（カナ）
                //２－２）　@カスタマイズ参照項目列物理名取得
                String[] l_strValues2 = new String[2];
                l_strValues2[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.FAMILY_NAME_ALT1;
                l_strValues2[1] = WEB3AccountOpenExpAccountOpenSymbolNameDef.GIVEN_NAME_ALT1;
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_NAME_KANA, l_strValues2);   
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //顧客名（漢字）:口座開設見込客.顧客名（漢字）
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues2[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.FAMILY_NAME;
                l_strValues2[1] = WEB3AccountOpenExpAccountOpenSymbolNameDef.GIVEN_NAME;
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_NAME, l_strValues2); 
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //郵便番号:口座開設見込客.郵便番号を編集する。
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ZIP_CODE;
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ZIP_CODE, l_strValues1);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //住所１:口座開設見込客.住所１を編集する。
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ADDRESS_LINE1;
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE1, l_strValues1);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //住所１（カナ）:口座開設見込客.住所１（カナ）を半角ｶﾅに変換したものを編集する。。
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ADDRESS_LINE1_KANA;
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE1_KANA, l_strValues1);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //住所２:口座開設見込客.住所２を編集する。
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ADDRESS_LINE2;
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE2, l_strValues1);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //住所２（カナ）:口座開設見込客.住所２（カナ）を半角ｶﾅに変換したものを編集する。
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ADDRESS_LINE2_KANA;
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE2_KANA, l_strValues1);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //住所３:口座開設見込客.住所３を編集する。
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ADDRESS_LINE3;
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE3, l_strValues1);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //住所３（カナ）:口座開設見込客.住所３（カナ）を半角ｶﾅに変換したものを編集する。
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ADDRESS_LINE3_KANA;
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE3_KANA, l_strValues1);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //電話番号:口座開設見込客.電話番号を編集する。
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.TELEPHONE;
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.TELEPHONE, l_strValues1);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //連絡先住所:口座開設見込客.連絡先住所
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.CONTACT_ADDRESS;
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.CONTACT_ADDRESS, l_strValues1);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //連絡先電話番号:口座開設見込客.連絡先電話番号を編集する。
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.CONTACT_TELEPHONE;
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.CONTACT_TELEPHONE, l_strValues1);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //移管前部店名:null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.EX_BRANCH_NAME, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                
                //移管前口座コード:null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.EX_ACCOUNT_CODE, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //職業区分:口座開設見込客.職業区分を編集する。
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.OCCUPATION_DIV;
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.OCCUPATION_DIV, l_strValues1);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //生年月日年号:口座開設見込客.生年月日年号を編集する。
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ERA_BORN;
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ERA_BORN, l_strValues1);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //生年月日:口座開設見込客.生年月日を編集する。
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.BORN_DATE;
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.BORN_DATE, l_strValues1);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //性別:口座開設見込客.性別を編集する。
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.SEX;
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.SEX, l_strValues1);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //資料:null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.DOCUMENT, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //住所不明:null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.UNKNOWN_ADDRESS, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //報告書:null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.REPORT, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //居住／非居住区分:null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.RESIDENT, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //課税区分:null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.TAXATION_DIV, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //課税区分（外国）:null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.FORIGN_TAXATION_DIV, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //顧客区分:null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_DIV, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //口座開設
                //１（保護預り）:null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV1, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //２（積立投資）:null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV2, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //３（信用取引）:null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV3, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //４（発行日取引）:null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV4, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //５（外国証券）:null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV5, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //６（金述取引）:null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV6, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //７（優）:null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV7, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //８（総）:null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV8, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //９（債券先物）:null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV9, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //１０（株式先物）:null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV10, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //１１（株先オプション）:null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV11, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //１２（TBONDオプション）:null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV12, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //１３（株券オプション）:null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV13, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //申請区分（申告分離課税）:null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.TAXATION_APPL_DIV, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //譲渡課税区分:null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.TAXATION_TRAN_DIV, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //発送区分:null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.SEND_DIV, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //信託経由区分:null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.TRUST_VIA_DIV, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //徴収区分
                //１（保護預り）:null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.CORRECT_DIV1, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //２（外国証券）:null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.CORRECT_DIV2, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //３（金地金）:null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.CORRECT_DIV3, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //４（金貯蓄）:null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.CORRECT_DIV4, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //５（株累投）:null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.CORRECT_DIV5, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //先物OP口座開設区分（東証）:null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.IFO_ACC_OPEN_DIV_TOKYO, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //先物OP口座開設区分（大証）:null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.IFO_ACC_OPEN_DIV_OSAKA, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //先物OP口座開設区分（名証）:null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.IFO_ACC_OPEN_DIV_NAGOYA, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //処理区分:null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.STATUS, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //その他連絡先電話番号:null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.OTHER_CONTACT_TELEPHONE, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }  
                
                //仲介業扱者コード:口座開設見込客.仲介業扱者コードを編集する。
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.BROKERAGE_TRADER_CODE;
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.BROKERAGE_TRADER_CODE, l_strValues1); 
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //プロ・アマ区分 :null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.PROAM_DIV, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }

                //外国人区分（放送法@） :null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.FOREIGNER_DIV_BROADCAST, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }

                //外国人区分（航空法@） :null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.FOREIGNER_DIV_AVIATION, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }                

                //外国人区分（NTT法@） :null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.FOREIGNER_DIV_NTT, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }

                //配当金振込指定区分 :null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.DIVIDEND_TRANSFER_DIV, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
 
                //代理人区分（常任代理人） :null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.AGENT_DIV_PERMANENT, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }

                //代理人区分（法@定代理人） :null
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.AGENT_DIV_LEGAL, null);  
                //２－３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
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
     *(save伝票行)<BR>
     *(save伝票行()の実装）<BR>
     *口座開設伝票を１件登録する。<BR> 
     *<BR>
     *１）デフォルト設定行生成<BR> 
     *　@顧客登録伝票（G11）キューテーブル行オブジェクトを生成し、<BR> 
     *　@デフォルト設定（※1）の通りにプロパティをセットする。<BR> 
     *<BR>
     *　@文字列をセットする場合、出力項目のデータサイズを超えた場合は、<BR>
     *　@データサイズ以降の文字を切り捨てる。<BR>
     *<BR>
     *（※1）　@顧客登録伝票（G11）キューテーブルの処理対象項目デフォルト設定<BR> 
     *　@DBレイアウト 「顧客登録伝票（G11）キューテーブル.xls#<BR>
     *　@デフォルトDB設定論理・仲介業」シート参照。<BR> 
     *<BR>
     *２）カスタマイズ項目セット<BR> 
     *　@口座開設伝票項目マスタテーブルを以下の条件①@で検索する。<BR> 
     *　@該当行がない場合、条件②で検索する。<BR> 
     *<BR>
     *　@[条件①@]<BR> 
     *　@口座開設伝票項目マスタ.証券会社コード = this.get証券会社コード() And<BR> 
     *　@口座開設伝票項目マスタ.部店コード = this.get部店コード() And<BR> 
     *　@口座開設伝票項目マスタ.口座区分 = this.get口座区分() And<BR> 
     *　@口座開設伝票項目マスタ.データコード = this.getデータコード() And<BR> 
     *　@口座開設伝票項目マスタ.伝票通番 = 伝票通番<BR> 
     *<BR>
     *　@[条件②]<BR> 
     *　@口座開設伝票項目マスタ.証券会社コード = this.get証券会社コード() And<BR> 
     *　@口座開設伝票項目マスタ.部店コード = "000" And<BR> 
     *　@口座開設伝票項目マスタ.口座区分 = this.get口座区分() And<BR> 
     *　@口座開設伝票項目マスタ.データコード = this.getデータコード() And<BR> 
     *　@口座開設伝票項目マスタ.伝票通番 = 伝票通番<BR> 
     *<BR>
     *　@条件①@，②のどちらかで該当行がある場合は、２－１）の処理を実施する。<BR> 
     *<BR>
     *２－１）カスタマイズ編集<BR> 
     *　@検索結果の各行毎に、出力項目物理名が示す伝票項目の値を、指定の方法@で再セットする。<BR> 
     *　@文字列をセットする場合、出力項目のデータサイズを超えた場合は、<BR>
     *　@データサイズ以降の文字を切り捨てる。<BR> 
     *<BR>
     *　@○ （項目編集方法@ == 固定値）の場合、固定セット値の値をセットする。<BR> 
     *　@○ 以外の場合、入力項目物理名１～３が示す口座開設見込客の項目値(※2)をセットする。<BR> 
     *　@－入力項目物理名１～３がnullの場合は、<BR> 
     *　@－連結項目デリミッタが指定されている場合（連結項目デリミッタ != null）、<BR> 
     *　@入力項目物理名１，２，３の値をデリミッタにて連結する。<BR> 
     *<BR>
     *　@(※2) DBレイアウト「口座開設伝票項目マスタ」参照。<BR> 
     *<BR>
     *２－２）伝票の識別コード新規採番<BR>
     *　@注文識別コード採番サービス.get新規識別コード()にて識別コードを取得し、<BR> 
     *　@行オブジェクトの識別コード（order_request_number）にセットする。<BR> 
     *<BR>
     *　@[get新規識別コード()に指定する引数]<BR> 
     *　@証券会社コード：　@this.get証券会社コード()<BR> 
     *　@部店コード：　@this.get部店コード()<BR> 
     *　@銘柄タイプ：　@ProductTypeEnum.その他<BR>
     *　@<BR>
     *３）DB更新<BR> 
     *３－１）既存行削除<BR> 
     *　@以下の条件にて顧客登録伝票（G11）キューテーブルを検索する。<BR> 
     *　@該当行が既に存在する場合は、該当行をdeleteする。<BR> 
     *<BR>
     *　@[条件]<BR>
     *　@証券会社コード =  this.get証券会社コード() And<BR> 
     *　@識別コード = this.get識別コード() And<BR> 
     *　@伝票通番 = 伝票通番 And<BR> 
     *　@処理区分 = ”未処理”<BR> 
     *　@<BR>
     *３－２）伝票行挿入<BR> 
     *１）～２）で編集した行オブジェクトをDBに更新（DB-insertする）。<BR> 
     *@@param l_strSerialNo - (伝票通番)<BR>
     *伝票通番<BR>
     *@@throws WEB3BaseException
     *@@roseuid 41930C8C0393
     */
    public void saveVoucherRow(String l_strSerialNo) throws WEB3BaseException
    {    
        final String STR_METHOD_NAME = " saveVoucherRow(String)";
        log.entering(STR_METHOD_NAME);
        //デフォルト設定行生成 
        HostAccRegVoucherParams l_hostAccRegVoucherParams = new HostAccRegVoucherParams();
        
        //デフォルト設定（※1）の通りにプロパティをセットする。
        try
        {             
            String l_strInstitutionCode = this.accOpenExpAccountOpen.getInstitutionCode();
            String l_strBranchCode = this.accOpenExpAccountOpen.getBranchCode();
            String l_strAccountCode = this.accOpenExpAccountOpen.getAccountCode();
            String l_strRequestNumber = this.accOpenExpAccountOpen.getRequestNumber();

            //データコード
            l_hostAccRegVoucherParams.setRequestCode(WEB3HostRequestCodeDef.ACCOPEN_REG_BROKERAGE);
            //証券会社コード
            l_hostAccRegVoucherParams.setInstitutionCode(super.getStringByByteNumber(l_strInstitutionCode, 3));
            //部店コード                    
            l_hostAccRegVoucherParams.setBranchCode(super.getStringByByteNumber(l_strBranchCode, 3));
            //顧客コード
            l_hostAccRegVoucherParams.setAccountCode(super.getStringByByteNumber(l_strAccountCode, 7));
            
            //扱者コード
            ExpAccountOpenRow l_expAccountOpenRow = (ExpAccountOpenRow)this.accOpenExpAccountOpen.getDataSourceObject();
            String l_strSonarTraderCode = l_expAccountOpenRow.getSonarTraderCode();
            l_hostAccRegVoucherParams.setTraderCode(super.getStringByByteNumber(l_strSonarTraderCode, 5));
            
            //識別コード（口座開設見込客）
            l_hostAccRegVoucherParams.setAccOpenRequestNumber(super.getStringByByteNumber(l_strRequestNumber, 13));
            //伝票通番
            l_hostAccRegVoucherParams.setSerialNo(l_strSerialNo);
            //登録区分
            l_hostAccRegVoucherParams.setRegistDiv(WEB3RegDivDef.NEW);
            //顧客名（ｶﾅ）
            String l_strFamilyNameAlt1 = WEB3StringTypeUtility.to1byteKana(l_expAccountOpenRow.getFamilyNameAlt1());
            String l_strGivenNameAlt1 = WEB3StringTypeUtility.to1byteKana(l_expAccountOpenRow.getGivenNameAlt1());
            String l_strAccountNameKana = l_strFamilyNameAlt1 + " " + l_strGivenNameAlt1;
            
            l_hostAccRegVoucherParams.setAccountNameKana(super.getStringByByteNumber(l_strAccountNameKana, 19));
            
            //顧客名（漢字）
            String l_strFamilyName = l_expAccountOpenRow.getFamilyName();
            String l_strGivenName = l_expAccountOpenRow.getGivenName();
            String l_strAccountName = l_strFamilyName + "　@" + l_strGivenName; 
            
            l_hostAccRegVoucherParams.setAccountName(super.getStringByByteNumber(l_strAccountName, 18));         
            
            //郵便番号
            l_hostAccRegVoucherParams.setZipCode(super.getStringByByteNumber(l_expAccountOpenRow.getZipCode(), 7));
            //住所１
            String l_strAddressLine1 = l_expAccountOpenRow.getAddressLine1();    
                   
            l_hostAccRegVoucherParams.setAddressLine1(super.getStringByByteNumber(l_strAddressLine1, 32));

            //住所１（カナ）
            String l_strAddressLine1Kana = WEB3StringTypeUtility.to1byteKana(l_expAccountOpenRow.getAddressLine1Kana());
            l_hostAccRegVoucherParams.setAddressLine1Kana(super.getStringByByteNumber(l_strAddressLine1Kana, 30));
            
            //住所２
            String l_strAddressLine2 = l_expAccountOpenRow.getAddressLine2();
            
            l_hostAccRegVoucherParams.setAddressLine2(super.getStringByByteNumber(l_strAddressLine2, 32));
            
            //住所２（カナ）
            String l_strAddressLine2Kana = WEB3StringTypeUtility.to1byteKana(l_expAccountOpenRow.getAddressLine2Kana());
            l_hostAccRegVoucherParams.setAddressLine2Kana(super.getStringByByteNumber(l_strAddressLine2Kana, 30));
            
            //住所３
            String l_strAddressLine3 = l_expAccountOpenRow.getAddressLine3();

            l_hostAccRegVoucherParams.setAddressLine3(super.getStringByByteNumber(l_strAddressLine3, 32));
            
            //住所３（カナ）
            String l_strAddressLine3Kana = WEB3StringTypeUtility.to1byteKana(l_expAccountOpenRow.getAddressLine3Kana());
            //※住所１（カナ）、住所２（カナ）、住所３（カナ）にセットする値の合計サイズが68文字を超過している場合は、
            //68文字以降を住所３（カナ）より切り捨てる。
            String l_strAddressLine = l_hostAccRegVoucherParams.getAddressLine1Kana() + l_hostAccRegVoucherParams.getAddressLine2Kana();
            int l_intAddressLineLength = Math.min(68 - l_strAddressLine.length(), 30);
            l_hostAccRegVoucherParams.setAddressLine3Kana(super.getStringByByteNumber(l_strAddressLine3Kana, l_intAddressLineLength));
            
            //電話番号
            l_hostAccRegVoucherParams.setTelephone(super.getStringByByteNumber(l_expAccountOpenRow.getTelephone(), 16));
            //連絡先住所
            String l_strContactAddress = l_expAccountOpenRow.getContactAddress();
            
            l_hostAccRegVoucherParams.setContactAddress(super.getStringByByteNumber(l_strContactAddress, 36));
            
            //連絡先電話番号
            l_hostAccRegVoucherParams.setContactTelephone(super.getStringByByteNumber(l_expAccountOpenRow.getContactTelephone(), 16));
            
            //移管前部店名
            l_hostAccRegVoucherParams.setExBranchName(super.getStringByByteNumber("1", 3));
            //移管前口座コード
            l_hostAccRegVoucherParams.setExAccountCode(super.getStringByByteNumber("1", 7));
            //職業区分
            l_hostAccRegVoucherParams.setOccupationDiv(super.getStringByByteNumber(l_expAccountOpenRow.getOccupationDiv(), 2));
            //生年月日年号
            l_hostAccRegVoucherParams.setEraBorn(super.getStringByByteNumber(l_expAccountOpenRow.getEraBorn(), 1));
            //生年月日
            l_hostAccRegVoucherParams.setBornDate(super.getStringByByteNumber(l_expAccountOpenRow.getBornDate(), 6));
            //性別
            l_hostAccRegVoucherParams.setSex(super.getStringByByteNumber(l_expAccountOpenRow.getSex(), 1));
            
            //資料
            l_hostAccRegVoucherParams.setDocument(WEB3DocumentDef.NEED);
            //住所不明
            l_hostAccRegVoucherParams.setUnknownAddress(null);
            //報告書
            l_hostAccRegVoucherParams.setReport(null);
            //居住／非居住区分
            l_hostAccRegVoucherParams.setResident(WEB3ResidentDef.RESIDENT);
            //課税区分
            l_hostAccRegVoucherParams.setTaxationDiv(WEB3AccRegTaxationDivDef.OLD_INTEGRATION);
            //課税区分（外国）
            l_hostAccRegVoucherParams.setForignTaxationDiv(WEB3AccRegTaxationDivDef.OLD_INTEGRATION);
            //顧客区分
            l_hostAccRegVoucherParams.setAccountDiv(WEB3AccRegAccountDivDef.GENERAL);
            //１（保護預り）
            l_hostAccRegVoucherParams.setAccountOpenDiv1(WEB3AccountOpenDef.OPEN);
            //２（積立投資）
            l_hostAccRegVoucherParams.setAccountOpenDiv2(WEB3AccountOpenDef.NOT_OPEN);
            //３（信用取引）
            l_hostAccRegVoucherParams.setAccountOpenDiv3(WEB3AccountOpenDef.NOT_OPEN);
            //４（発行日取引）
            l_hostAccRegVoucherParams.setAccountOpenDiv4(WEB3AccountOpenDef.NOT_OPEN);
            //５（外国証券）
            l_hostAccRegVoucherParams.setAccountOpenDiv5(WEB3AccountOpenDef.OPEN);
            //６（金述取引）
            l_hostAccRegVoucherParams.setAccountOpenDiv6(WEB3AccountOpenDef.NOT_OPEN);
            //７（優）
            l_hostAccRegVoucherParams.setAccountOpenDiv7(WEB3AccountOpenDef.NOT_OPEN);
            //８（総）
            l_hostAccRegVoucherParams.setAccountOpenDiv8(WEB3AccountOpenDef.NOT_OPEN);
            //９（債券先物）
            l_hostAccRegVoucherParams.setAccountOpenDiv9(WEB3AccountOpenDef.NOT_OPEN);
            //１０（株式先物）
            l_hostAccRegVoucherParams.setAccountOpenDiv10(WEB3AccountOpenDef.NOT_OPEN);
            //１１（株先オプション）
            l_hostAccRegVoucherParams.setAccountOpenDiv11(WEB3AccountOpenDef.NOT_OPEN);
            //１２（TBONDオプション）
            l_hostAccRegVoucherParams.setAccountOpenDiv12(WEB3AccountOpenDef.NOT_OPEN);
            //１３（株券オプション）
            l_hostAccRegVoucherParams.setAccountOpenDiv13(WEB3AccountOpenDef.NOT_OPEN);
            
            //申請区分（申告分離課税）
            l_hostAccRegVoucherParams.setTaxationApplDiv(WEB3TaxationDivDef.SEPARATE_SELF_ACCESSMENT);
            //譲渡課税区分
            l_hostAccRegVoucherParams.setTaxationTranDiv(WEB3TaxationDivDef.SEPARATE_SELF_ACCESSMENT);
            //発送区分
            l_hostAccRegVoucherParams.setSendDiv(null);
            //信託経由区分
            l_hostAccRegVoucherParams.setTrustViaDiv(null);
            //徴収区分
            l_hostAccRegVoucherParams.setCorrectDiv1(null);
            l_hostAccRegVoucherParams.setCorrectDiv2(null);
            l_hostAccRegVoucherParams.setCorrectDiv3(null);
            l_hostAccRegVoucherParams.setCorrectDiv4(null);
            l_hostAccRegVoucherParams.setCorrectDiv5(null);
            
            //先物OP口座開設区分（東証）
            l_hostAccRegVoucherParams.setIfoAccOpenDivTokyo(WEB3AccountOpenDef.NOT_OPEN);
            //先物OP口座開設区分（大証）
            l_hostAccRegVoucherParams.setIfoAccOpenDivOsaka(WEB3AccountOpenDef.NOT_OPEN);
            //先物OP口座開設区分（名証）
            l_hostAccRegVoucherParams.setIfoAccOpenDivNagoya(WEB3AccountOpenDef.NOT_OPEN);
            //処理区分
            l_hostAccRegVoucherParams.setStatus(WEB3StatusDef.NOT_DEAL);
            
            //その他連絡先電話番号
            l_hostAccRegVoucherParams.setOtherContactTelephone(null);
            //仲介業扱者コード
            l_hostAccRegVoucherParams.setBrokerageTraderCode(super.getStringByByteNumber(l_expAccountOpenRow.getBrokerageTraderCode(), 5));                        
            
            Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();
            //送信日時
            l_hostAccRegVoucherParams.setSendTimestamp(null);
            //作成日時
            l_hostAccRegVoucherParams.setCreatedTimestamp(l_tsSystemTimestamp);
            //更新日時
            l_hostAccRegVoucherParams.setLastUpdatedTimestamp(l_tsSystemTimestamp);
            
            //プロ・アマ区分
            l_hostAccRegVoucherParams.setProamDiv(WEB3ProamDivDef.AM);

            //外国人区分（放送法@）
            l_hostAccRegVoucherParams.setForeignerDivBroadcast(WEB3ForeignerDivDef.EXCEPT_FOREIGNER);

            //外国人区分（航空法@）
            l_hostAccRegVoucherParams.setForeignerDivAviation(WEB3ForeignerDivDef.EXCEPT_FOREIGNER);

            //外国人区分（NTT法@）
            l_hostAccRegVoucherParams.setForeignerDivNtt(WEB3ForeignerDivDef.EXCEPT_FOREIGNER);

            //配当金振込指定区分
            l_hostAccRegVoucherParams.setDividendTransferDiv(WEB3DividendTransferDivDef.DEFAULT);

            //代理人区分（常任代理人）
            l_hostAccRegVoucherParams.setAgentDivPermanent(WEB3AgentDivDef.NOT_ELECTION);

            //代理人区分（法@定代理人）
            l_hostAccRegVoucherParams.setAgentDivLegal(WEB3AgentDivDef.NOT_ELECTION);

            //２）　@カスタマイズ項目セット
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor(); //DataNetworkException, DataQueryException
            //[条件①@] 
            String l_strWhereItem =
                "institution_code = ? and " +        //口座開設伝票項目マスタ.証券会社コード = this.get証券会社コード() And 
                "branch_code = ? and " +             //口座開設伝票項目マスタ.部店コード = this.get部店コード() And  
                "account_div = ? and " +             //口座開設伝票項目マスタ.口座区分 = this.get口座区分() And  
                "request_code = ? and " +            //口座開設伝票項目マスタ.データコード = this.getデータコード() And  
                "serial_no = ? ";                    //口座開設伝票項目マスタ.伝票通番 = 伝票通番

            Object[] l_objBindVarsItem =
                {this.getInstitutionCode(),
                 this.getBranchCode(),
                 this.getAccountDiv(),
                 this.getRequestCode(),
                 l_strSerialNo};
                    
            List l_lisRowItems =
                l_queryProcesser.doFindAllQuery(
                    AccOpenVoucherItemRow.TYPE,
                    l_strWhereItem,
                    l_objBindVarsItem);             //DataQueryException, DataNetworkException
                     
            //該当行がない場合、条件②で検索する。 
            if (l_lisRowItems == null || l_lisRowItems.size() == 0)
            {
                log.debug("該当行がない場合、条件②で検索する。");
                //[条件②]  
                Object[] l_objBindVarsItem2 =
                    {this.getInstitutionCode(),
                     "000",
                     this.getAccountDiv(),
                     this.getRequestCode(),
                     l_strSerialNo};
                    
                l_lisRowItems =
                    l_queryProcesser.doFindAllQuery(
                        AccOpenVoucherItemRow.TYPE,
                        l_strWhereItem,
                        l_objBindVarsItem2);        //DataQueryException, DataNetworkException 
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
                    if (WEB3EditWayDivDef.FIXED_VALUE.equals(l_accOpenVoucherItemRow.getEditWayDiv()))
                    {
                        log.debug("項目編集方法@ == 固定値）の場合、固定セット値の値をセットする");
                        l_strValue = l_accOpenVoucherItemRow.getFixedValue();
                    }
                    else
                    {
                        log.debug("項目編集方法@ != 固定値）の場合");
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
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setRequestCode(super.getStringByByteNumber(l_strValue, 5));    
                    }
                    
                    //証券会社コード
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.INSTITUTION_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setInstitutionCode(super.getStringByByteNumber(l_strValue, 3)); 
                    }
                    //部店コード
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.BRANCH_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setBranchCode(super.getStringByByteNumber(l_strValue, 3));  
                    }
                    //顧客コード
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setAccountCode(super.getStringByByteNumber(l_strValue, 7)); 
                    }
                    //扱者コード
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.TRADER_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setTraderCode(super.getStringByByteNumber(l_strValue, 5));
                    }
                    //識別コード（口座開設見込客）
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.ACC_OPEN_REQUEST_NUMBER.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setAccOpenRequestNumber(super.getStringByByteNumber(l_strValue, 13));
                    } 
                        
                    //伝票通番
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.SERIAL_NO.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setSerialNo(super.getStringByByteNumber(l_strValue, 3));   
                    }
                        
                    //登録区分
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.REGIST_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setRegistDiv(super.getStringByByteNumber(l_strValue, 1));    
                    }
                    
                    //顧客名（ｶﾅ）
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_NAME_KANA.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAccRegVoucherParams.setAccountNameKana(super.getStringByByteNumber(l_strValue, 19));
                    }
            
                    //顧客名（漢字）
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_NAME.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAccRegVoucherParams.setAccountName(super.getStringByByteNumber(l_strValue, 18));  
                    }
                    
                    //郵便番号
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.ZIP_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAccRegVoucherParams.setZipCode(super.getStringByByteNumber(l_strValue, 7));
                    }
                    //住所１
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE1.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAccRegVoucherParams.setAddressLine1(super.getStringByByteNumber(l_strValue, 32));
                    }
           
                    //住所１（カナ）
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE1_KANA.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAccRegVoucherParams.setAddressLine1Kana(super.getStringByByteNumber(l_strValue, 30));
                    }

                    //住所２
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE2.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAccRegVoucherParams.setAddressLine2(super.getStringByByteNumber(l_strValue, 32));
                    }

                    //住所２（カナ）
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE2_KANA.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAccRegVoucherParams.setAddressLine2Kana(super.getStringByByteNumber(l_strValue, 30));
                    }
            
                    //住所３
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE3.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAccRegVoucherParams.setAddressLine3(super.getStringByByteNumber(l_strValue, 32));
                    }

                    //住所３（カナ）
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE3_KANA.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAccRegVoucherParams.setAddressLine3Kana(super.getStringByByteNumber(l_strValue, 30));
                    }
                    
                    //電話番号
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.TELEPHONE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAccRegVoucherParams.setTelephone(super.getStringByByteNumber(l_strValue, 16));
                    }
                        
                    //連絡先住所
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.CONTACT_ADDRESS.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAccRegVoucherParams.setContactAddress(super.getStringByByteNumber(l_strValue, 36));
                    }

                    //連絡先電話番号
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.CONTACT_TELEPHONE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAccRegVoucherParams.setContactTelephone(super.getStringByByteNumber(l_strValue, 16));
                    }
                        
                    //移管前部店名
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.EX_BRANCH_NAME.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setExBranchName(super.getStringByByteNumber(l_strValue, 3));    
                    }
                        
                    //移管前口座コード
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.EX_ACCOUNT_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setExAccountCode(super.getStringByByteNumber(l_strValue, 7));    
                    }
                    
                    //職業区分
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.OCCUPATION_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setOccupationDiv(super.getStringByByteNumber(l_strValue, 2));
                    }
                        
                    //生年月日年号
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.ERA_BORN.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setEraBorn(super.getStringByByteNumber(l_strValue, 1));
                    }
                        
                    //生年月日
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.BORN_DATE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setBornDate(super.getStringByByteNumber(l_strValue, 6));
                    }
                        
                    //性別
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.SEX.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setSex(super.getStringByByteNumber(l_strValue, 1));
                    } 
                        
                    //資料
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.DOCUMENT.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setDocument(super.getStringByByteNumber(l_strValue, 1));   
                    }
                        
                    //住所不明
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.UNKNOWN_ADDRESS.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setUnknownAddress(super.getStringByByteNumber(l_strValue, 1));    
                    }
                        
                    //報告書
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.REPORT.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setReport(super.getStringByByteNumber(l_strValue, 1));   
                    }
                        
                    //居住／非居住区分
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.RESIDENT.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setResident(super.getStringByByteNumber(l_strValue, 1));    
                    }
                        
                    //課税区分
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.TAXATION_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setTaxationDiv(super.getStringByByteNumber(l_strValue, 1));    
                    }
                        
                    //課税区分（外国）
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.FORIGN_TAXATION_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setForignTaxationDiv(super.getStringByByteNumber(l_strValue, 1));   
                    }
                        
                    //顧客区分
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setAccountDiv(super.getStringByByteNumber(l_strValue, 1));    
                    }
                        
                    //口座開設
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV1.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setAccountOpenDiv1(super.getStringByByteNumber(l_strValue, 1));    
                    }
                        
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV2.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setAccountOpenDiv2(super.getStringByByteNumber(l_strValue, 1));   
                    }
                        
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV3.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setAccountOpenDiv3(super.getStringByByteNumber(l_strValue, 1));    
                    }
                        
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV4.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setAccountOpenDiv4(super.getStringByByteNumber(l_strValue, 1));   
                    }
                        
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV5.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setAccountOpenDiv5(super.getStringByByteNumber(l_strValue, 1));     
                    }
                        
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV6.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setAccountOpenDiv6(super.getStringByByteNumber(l_strValue, 1));    
                    }
                        
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV7.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setAccountOpenDiv7(super.getStringByByteNumber(l_strValue, 1));   
                    }
                        
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV8.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setAccountOpenDiv8(super.getStringByByteNumber(l_strValue, 1)); 
                    }
                        
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV9.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setAccountOpenDiv9(super.getStringByByteNumber(l_strValue, 1));    
                    }
                        
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV10.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setAccountOpenDiv10(super.getStringByByteNumber(l_strValue, 1));   
                    }
                        
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV11.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setAccountOpenDiv11(super.getStringByByteNumber(l_strValue, 1));    
                    }
                        
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV12.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setAccountOpenDiv12(super.getStringByByteNumber(l_strValue, 1));    
                    }
                        
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV13.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setAccountOpenDiv13(super.getStringByByteNumber(l_strValue, 1));    
                    }
                        
                    //申請区分（申告分離課税）
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.TAXATION_APPL_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setTaxationApplDiv(super.getStringByByteNumber(l_strValue, 1));  
                    }
                        
                    //譲渡課税区分
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.TAXATION_TRAN_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setTaxationTranDiv(super.getStringByByteNumber(l_strValue, 1));    
                    }
                        
                    //発送区分
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.SEND_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setSendDiv(super.getStringByByteNumber(l_strValue, 1));    
                    }
                        
                    //信託経由区分
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.TRUST_VIA_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setTrustViaDiv(super.getStringByByteNumber(l_strValue, 1));    
                    }
                        
                    //徴収区分
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.CORRECT_DIV1.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setCorrectDiv1(super.getStringByByteNumber(l_strValue, 1));    
                    }
                        
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.CORRECT_DIV2.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setCorrectDiv2(super.getStringByByteNumber(l_strValue, 1));    
                    }
                        
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.CORRECT_DIV3.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setCorrectDiv3(super.getStringByByteNumber(l_strValue, 1));    
                    }
                        
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.CORRECT_DIV4.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setCorrectDiv4(super.getStringByByteNumber(l_strValue, 1));    
                    }
                        
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.CORRECT_DIV5.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setCorrectDiv5(super.getStringByByteNumber(l_strValue, 1));   
                    }
                        
                    //先物OP口座開設区分（東証）
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.IFO_ACC_OPEN_DIV_TOKYO.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setIfoAccOpenDivTokyo(super.getStringByByteNumber(l_strValue, 1));    
                    }
                        
                    //先物OP口座開設区分（大証）
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.IFO_ACC_OPEN_DIV_OSAKA.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setIfoAccOpenDivOsaka(super.getStringByByteNumber(l_strValue, 1));     
                    }
                        
                    //先物OP口座開設区分（名証）
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.IFO_ACC_OPEN_DIV_NAGOYA.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setIfoAccOpenDivNagoya(super.getStringByByteNumber(l_strValue, 1));   
                    }
                        
                    //処理区分
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.STATUS.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setStatus(super.getStringByByteNumber(l_strValue, 1));   
                    }

                    //その他連絡先電話番号
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.OTHER_CONTACT_TELEPHONE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setOtherContactTelephone(super.getStringByByteNumber(l_strValue, 16));   
                    }
                   
                    //仲介業扱者コード
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.BROKERAGE_TRADER_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setBrokerageTraderCode(super.getStringByByteNumber(l_strValue, 5));   
                    }
                    
                    //プロ・アマ区分
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.PROAM_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setProamDiv(super.getStringByByteNumber(l_strValue, 1));
                    }

                    //外国人区分（放送法@）
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.FOREIGNER_DIV_BROADCAST.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setForeignerDivBroadcast(super.getStringByByteNumber(l_strValue, 1));
                    }

                    //外国人区分（航空法@）
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.FOREIGNER_DIV_AVIATION.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setForeignerDivAviation(super.getStringByByteNumber(l_strValue, 1));
                    }

                    //外国人区分（NTT法@）
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.FOREIGNER_DIV_NTT.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setForeignerDivNtt(super.getStringByByteNumber(l_strValue, 1));
                    }

                    //配当金振込指定区分
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.DIVIDEND_TRANSFER_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setDividendTransferDiv(super.getStringByByteNumber(l_strValue, 1));
                    }

                    //代理人区分（常任代理人）
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.AGENT_DIV_PERMANENT.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setAgentDivPermanent(super.getStringByByteNumber(l_strValue, 1));
                    }

                    //代理人区分（法@定代理人）
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.AGENT_DIV_LEGAL.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_hostAccRegVoucherParams.setAgentDivLegal(super.getStringByByteNumber(l_strValue, 1));
                    } 
                }
            }
            
            //２－２）　@伝票の識別コード新規採番 
            WEB3HostReqOrderNumberManageService l_reqOrderNumberManageService=
                (WEB3HostReqOrderNumberManageService)Services.getService(
                    WEB3HostReqOrderNumberManageService.class);    
            String l_strNewNumber = l_reqOrderNumberManageService.getNewNumber(
                this.getInstitutionCode(),
                this.getBranchCode(),
                ProductTypeEnum.OTHER);
            l_hostAccRegVoucherParams.setOrderRequestNumber(l_strNewNumber);
            
            //３－１）　@既存行削除
            String l_strWhere =
                "institution_code = ? and " +        //証券会社コード = this.get証券会社コード() And 
                "acc_open_request_number = ? and " +    //識別コード = this.get識別コード() And   
                "serial_no = ? and " +               //伝票通番 = 伝票通番 And   
                "status = ? ";                       //処理区分 = ”未処理”  

            Object[] l_objBindVars =
                {this.getInstitutionCode(),
                 this.getRequestNumber(),
                 l_strSerialNo,
                 WEB3StatusDef.NOT_DEAL};
                    
            l_queryProcesser.doDeleteAllQuery(
                HostAccRegVoucherRow.TYPE,
                l_strWhere,
                l_objBindVars);                 //DataQueryException, DataNetworkException
            
            //３－２）　@伝票行挿入 
            l_queryProcesser.doInsertQuery(l_hostAccRegVoucherParams);  
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
     *(saveDelete伝票行)<BR>
     *(saveDelete伝票行()の実装）<BR>
     *口座開設伝票を１件削除する。<BR>
     *<BR>
     *　@顧客登録伝票（G11）キューテーブルの以下の条件に当てはまる行を取得する。<BR> 
     *<BR>
     *　@[条件]<BR> 
     *　@証券会社コード =  this.get証券会社コード() And<BR> 
     *　@識別コード = this.get識別コード() And<BR> 
     *　@伝票通番 = 伝票通番 And<BR> 
     *　@処理区分 = ”未処理”<BR> 
     *<BR>
     *　@行が取得できなかった場合、falseを返却する。<BR> 
     *　@行が取得できた場合、該当行の削除（delete row）を行い、trueを返却する。<BR>
     *@@param l_strSerialNo - (伝票通番)<BR>
     *伝票通番<BR>
     *@@return boolean
     *@@roseuid 419C2C5C012C
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
            
            Object[] l_objBindVars =
                {this.getInstitutionCode(),
                 this.getRequestNumber(),
                 l_strSerialNo,
                 WEB3StatusDef.NOT_DEAL};

            //顧客登録伝票（G11）キューテーブル
            List l_lisRows = l_queryProcesser.doFindAllQuery(
                HostAccRegVoucherRow.TYPE,
                l_strWhere,
                l_objBindVars);     //DataQueryException, DataNetworkException
                
            if (l_lisRows == null || l_lisRows.size() == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return false;    
            }
            else
            {
                l_queryProcesser.doDeleteAllQuery(
                    HostAccRegVoucherRow.TYPE,
                    l_strWhere,
                    l_objBindVars);       //DataQueryException, DataNetworkException   

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
     *　@@@return Object
     *　@@@roseuid 41B45E6E0177
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
