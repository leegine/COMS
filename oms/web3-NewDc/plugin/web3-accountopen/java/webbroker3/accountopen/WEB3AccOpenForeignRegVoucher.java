head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.29.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenForeignRegVoucher.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外貨預金口座登録伝票(WEB3AccOpenForeignRegVoucher.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/14 柴雙紅(中訊) 新規作成
                   2006/09/08 柴雙紅(中訊) 仕様変更 モデル099
                   2006/10/12 柴雙紅 ＤＢレイアウト  No.038
                   2007/06/18 岡安 (SCS) 実装の問題 No.005
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
import webbroker3.accountopen.data.HostFDepositVoucherParams;
import webbroker3.accountopen.data.HostFDepositVoucherRow;
import webbroker3.accountopen.define.WEB3AccountOpenExpAccountOpenSymbolNameDef;
import webbroker3.accountopen.define.WEB3AccountOpenOutputItemSymbolNameDef;
import webbroker3.accountopen.define.WEB3AccountOpenVoucherCodeDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CatDelimitterDef;
import webbroker3.common.define.WEB3EditWayDivDef;
import webbroker3.common.define.WEB3FeeDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3RegDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (外貨預金口座登録伝票)<BR>
 * 外貨預金口座登録伝票<BR>
 * <BR>  
 * @@author 柴雙紅
 * @@version 1.0
 */
public class WEB3AccOpenForeignRegVoucher extends WEB3AccOpenVoucher
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenForeignRegVoucher.class);

    /**
     *口座開設見込客オブジェクトを指定し、外貨預金口座登録伝票インスタンスを作成する。<BR>
     *<BR>
     *１）インスタンス生成 <BR>
     *　@外貨預金口座登録伝票インスタンスを生成する。<BR>
     *<BR> 
     *２）口座開設見込客プロパティセット<BR> 
     *　@生成したインスタンス.set口座開設見込客()にて、口座開設見込客プロパティをセットする。<BR>
     *<BR>  
     *　@[set口座開設見込客()に指定する引数]<BR> 
     *　@口座開設見込客：　@口座開設見込客<BR> 
     *<BR>  
     *３）口座開設伝票マスタプロパティセット<BR>  
     *　@生成したインスタンス.set伝票マスタ()にて、伝票マスタ行プロパティをセットする。<BR>  
     *<BR>  
     *４）生成したインスタンスを返却する。<BR> 
     *@@param l_accOpenExpAccountOpen - (口座開設見込客オブジェクト)<BR>
     *口座開設見込客オブジェクト<BR>
     *@@return WEB3AccOpenForeignRegVoucher
     *@@throws WEB3BaseException
     */
    public static  WEB3AccOpenForeignRegVoucher getInstance(WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInstance(WEB3AccOpenExpAccountOpen)";
        log.entering(STR_METHOD_NAME);
        
        //１）インスタンス生成
        WEB3AccOpenForeignRegVoucher l_accOpenForeignSaveRegVoucher = 
            new WEB3AccOpenForeignRegVoucher();
        
        //２）口座開設見込客プロパティセット
        l_accOpenForeignSaveRegVoucher.setAccOpenExpAccountOpen(l_accOpenExpAccountOpen);
        
        //３）口座開設伝票マスタプロパティセット
        l_accOpenForeignSaveRegVoucher.setAccOpenVoucherMaster();
        
        log.exiting(STR_METHOD_NAME);
        return l_accOpenForeignSaveRegVoucher;
    }
    
    /**
     *(isオンライン伝票)<BR>
     *(isオンライン伝票()の実装)<BR>
     *<BR>
     *trueを返却する。<BR>
     *@@return boolean
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
     *(getデータコード()の実装)<BR>
     *<BR>
     *データコード.外貨預金口座登録（GI854）を返却する。<BR>
     *@@return String
     */
    public String getRequestCode()
    {
        final String STR_METHOD_NAME = " getRequestCode()";
        log.entering(STR_METHOD_NAME);
         
        log.exiting(STR_METHOD_NAME);
        return WEB3HostRequestCodeDef.F_DEPOSIT_REG;
    }

    /**
     *(get伝票コード)<BR>
     *(get伝票コード()の実装)<BR>
     *<BR>
     *”G43”を返却する。<BR>
     *@@return String
     */
    public String getVoucherCode()
    {
        final String STR_METHOD_NAME = " getVoucherCode()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        return WEB3AccountOpenVoucherCodeDef.FOREIGN_SAVE_REG_VOUCHER_CODE;
    }
     
    /**
     *(get確定済項目名)<BR>
     *(get確定済項目名()の実装)<BR>
     *<BR>
     *当該伝票で使用している口座開設見込客の列物理名を配列で取得する。<BR>
     *１）伝票使用項目Table（：Hashtable）生成 <BR>
     *　@　@Hashtableを生成する。<BR>
     *<BR>
     *２）使用項目セット <BR>
     *<BR>
     *　@　@this.口座開設伝票マスタ行[]の各要素毎に、２−１）〜２−３）の処理を実施する。<BR>
     *<BR>
     *　@２−１）作成可能判定<BR>
     *　@　@is作成可能伝票()にて、作成可能な伝票かを判定する。<BR>
     *<BR>
     *　@　@[is作成可能伝票()に指定する引数]<BR>
     *　@　@伝票通番：　@口座開設伝票マスタ行[index].伝票通番<BR>
     *<BR>
     *　@　@作成可能でない場合（is作成可能伝票() == false）のみ、２−２）を実施する。<BR>
     *　@　@作成可能な場合（is作成可能伝票() == true）、<BR>
     *　@　@当該要素の処理を行わず次の要素を処理する。（continue;）<BR> 
     *<BR>
     *　@　@＃作成可能な場合、項目値は変更しても良いので伝票参照項目名には含めない。<BR>
     *<BR>
     *　@２−２）　@カスタマイズ参照項目列物理名取得<BR>
     *　@　@外貨預金口座登録伝票（G43）テーブルの各項目について、下記の処理を実施する。<BR>
     *<BR>
     *　@　@this.getカスタマイズ参照項目()をコールし、<BR>
     *　@　@口座開設見込客テーブル列物理名の配列を取得する。<BR>
     *<BR>
     *　@　@[getカスタマイズ参照項目()に指定する引数]<BR>
     *　@　@伝票通番：　@口座開設伝票マスタ行[index].伝票通番<BR>
     *　@　@伝票出力項目物理名：<BR>　@
     *　@　@（※1 外貨預金口座登録伝票（G43）テーブルの処理対象項目）<BR>
     *　@　@伝票参照項目初期値：<BR>　@
     *　@　@（※2 外貨預金口座登録伝票（G43）テーブルの処理対象項目デフォルト設定値）<BR>
     *<BR>
     *　@　@（※2）外貨預金口座登録伝票（G43）テーブルの処理対象項目デフォルト設定値<BR>
     *　@　@DBレイアウト<BR> 
     *　@　@「外貨預金口座登録伝票（G43）テーブル.xls#デフォルトDB設定論理」シートを参照し<BR>
     *　@　@該当項目の説明欄に、<BR>
     *　@　@口座開設見込客テーブルから編集する指定があれば、指定項目の列物理名配列。 <BR>
     *　@　@以外は、null。<BR>
     *<BR>
     *　@２−３）　@伝票使用項目Table（：Hashtable）に追加<BR>
     *　@　@伝票使用項目Table（：Hashtable）.put()<BR>
     *　@　@にてthis.getカスタマイズ参照項目()戻り値を一要素ずつ追加する。<BR> 
     *<BR>
     *　@　@[put()に指定する引数]<BR> 
     *　@　@key：　@this.getカスタマイズ参照項目()[n]<BR>
     *　@　@value：　@this.getカスタマイズ参照項目()[n] <BR>
     *　@　@※ key，valueに同じ値をセットする。<BR>
     *<BR>
     *３）　@項目名配列返却 <BR>
     *　@伝票使用項目Table（：Hashtable）.values()　@を返却する。<BR>
     *@@return String[]
     *@@throws WEB3BaseException
     */
    public String[] getConfirmedItemName() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getConfirmedItemName()";
        log.entering(STR_METHOD_NAME);
        
        //１）伝票使用項目Table（：Hashtable）生成 
        Hashtable l_voucherItemList = new Hashtable();
        
        //２）使用項目セット
        //this.口座開設伝票マスタ行[]の各要素毎に、２−１）〜２−３）の処理を実施する。
        int l_intLength = 0;
        if (this.accOpenVoucherMasterParamses != null)
        {
            l_intLength = this.accOpenVoucherMasterParamses.length;
        }
        for (int i = 0; i < l_intLength; i++)
        {
            //２−１）　@作成可能判定
            //is作成可能伝票()にて、作成可能な伝票かを判定する。
            String l_strSerialNo = this.accOpenVoucherMasterParamses[i].getSerialNo();
            boolean l_blnCreatedPossibleVoucher = this.isCreatedPossibleVoucher(l_strSerialNo);
            
            //作成可能でない場合（is作成可能伝票() == false）のみ、２−２）を実施する。
            if(!l_blnCreatedPossibleVoucher)
            {
                String[] l_strValues = new String[1];
                
                //証券会社コード
                //２−２）カスタマイズ参照項目列物理名取得
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.INSTITUTION_CODE;                
                String[] l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.INSTITUTION_CODE, l_strValues);
                //２−３）　@伝票使用項目Table（：Hashtable）に追加
                int l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("**********************証券会社コード");
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //データコード
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.REQUEST_CODE, null);                
                //２−３）　@伝票使用項目Table（：Hashtable）に追加
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
                
                //部店コード
                //２−２）　@カスタマイズ参照項目列物理名取得
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.BRANCH_CODE;
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.BRANCH_CODE, l_strValues);   
                //２−３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("**********************部店コード");
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //顧客コード
                //２−２）　@カスタマイズ参照項目列物理名取得
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ACCOUNT_CODE;
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_CODE, l_strValues);   
                //２−３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("**********************顧客コード");
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //扱者コード
                //２−２）　@カスタマイズ参照項目列物理名取得
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.SONAR_TRADER_CODE;
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.TRADER_CODE, l_strValues);   
                //２−３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("**********************扱者コード");
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }

                //識別コード（口座開設見込客）
                //２−２）　@カスタマイズ参照項目列物理名取得
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ACC_OPEN_REQUEST_NUMBER;
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACC_OPEN_REQUEST_NUMBER, l_strValues);  
                //２−３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("**********************識別コード（口座開設見込客）");
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //伝票通番
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.SERIAL_NO, null);  
                //２−３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("**********************伝票通番");
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //登録区分
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.REGIST_DIV, null);  
                //２−３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("**********************登録区分");
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //通貨コード
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.CURENCY_CODE, null);  
                //２−３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("**********************通貨コード");
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //銀行コード
                //２−２）　@カスタマイズ参照項目列物理名取得
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.FIN_INSTITUTION_CODE;
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.BANK_CODE, l_strValues);  
                //２−３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("**********************銀行コード");
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //支店コード
                //２−２）　@カスタマイズ参照項目列物理名取得
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.FIN_BRANCH_CODE;
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.OTHER_BRANCH_CODE, l_strValues);  
                //２−３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("**********************支店コード");
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //口座番号
                //２−２）　@カスタマイズ参照項目列物理名取得
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.FOREIGN_ACCOUNT_NO;
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.FIN_ACCOUNT_NO, l_strValues);  
                //２−３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("**********************口座番号");
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //口座名義人名
                //２−２）　@カスタマイズ参照項目列物理名取得
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.FOREIGN_ACCOUNT_NAME;
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.FIN_ACCOUNT_NAME, l_strValues);  
                //２−３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("**********************口座名義人名");
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //預金
                //２−２）　@カスタマイズ参照項目列物理名取得
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.FOREIGN_SAVE_DIV;
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.AMMOUNT, l_strValues);  
                //２−３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("**********************預金");
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //手数料
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.FEE, null);  
                //２−３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("**********************手数料");
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //口座名義人（英数）
                //２−２）　@カスタマイズ参照項目列物理名取得
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.FOREIGN_ACCOUNT_NAME_ENG;
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_NAME_ENG, l_strValues);  
                //２−３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("**********************口座名義人（英数）");
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //銀行名
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.BANK_NAME, null);  
                //２−３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("**********************銀行名");
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //支店名
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.BANK_BRANCH_NAME, null);  
                //２−３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("**********************支店名");
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //予備
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.EXTRA, null);  
                //２−３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("**********************予備");
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //処理区分
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.STATUS, null);  
                //２−３）　@伝票使用項目Table（：Hashtable）に追加
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("**********************処理区分");
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
     *(save伝票行()の実装)<BR>
     *口座開設伝票を１件登録する。<BR>
     *<BR>
     *１）デフォルト設定行生成<BR>
     *　@外貨預金口座登録伝票（G43）テーブル行オブジェクトを生成し、<BR>
     *　@デフォルト設定（※1）の通りにプロパティをセットする。<BR>
     *<BR>
     *　@文字列をセットする場合、<BR>
     *　@出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる。<BR>
     *<BR>
     *　@（※1）外貨預金口座登録伝票（G43）テーブルの処理対象項目デフォルト設定<BR>
     *　@DBレイアウト <BR>
     *　@「重要事項確認書伝票（G1159）テーブル.xls#デフォルトDB設定論理」シート参照。<BR>
     *<BR>
     *２）カスタマイズ項目セット<BR>
     *　@口座開設伝票項目マスタテーブルを以下の条件�@@で検索する。<BR>
     *　@該当行がない場合、条件�Aで検索する。<BR>
     *<BR>
     *　@[条件�@@]<BR>
     *　@口座開設伝票項目マスタ.証券会社コード = this.get証券会社コード() And <BR>
     *　@口座開設伝票項目マスタ.部店コード = this.get部店コード() And<BR>
     *　@口座開設伝票項目マスタ.口座区分 = this.get口座区分() And<BR>
     *　@口座開設伝票項目マスタ.データコード = this.getデータコード() And<BR>
     *　@口座開設伝票項目マスタ.伝票通番 = 伝票通番 <BR>
     *<BR>
     *　@[条件�A]<BR>
     *　@口座開設伝票項目マスタ.証券会社コード = this.get証券会社コード() And<BR>
     *　@口座開設伝票項目マスタ.部店コード = "000" And<BR>
     *　@口座開設伝票項目マスタ.口座区分 = this.get口座区分() And<BR>
     *　@口座開設伝票項目マスタ.データコード = this.getデータコード() And<BR>
     *　@口座開設伝票項目マスタ.伝票通番 = 伝票通番<BR>
     *<BR>
     *　@条件�@@，�Aのどちらかで該当行がある場合は、２−１）の処理を実施する。<BR>
     * <BR>
     *　@２−１）　@カスタマイズ編集<BR>
     *　@　@検索結果の各行毎に、出力項目物理名が示す伝票項目の値を、指定の方法@で再セットする。<BR>
     *　@　@文字列をセットする場合、出力項目のデータサイズを超えた場合は、<BR>
     *　@　@データサイズ以降の文字を切り捨てる。<BR>
     *<BR>
     *　@　@○ （項目編集方法@ == 固定値）の場合、固定セット値の値をセットする。<BR>
     *　@　@○ 以外の場合、入力項目物理名１〜３が示す口座開設見込客の項目値(※2)をセットする。<BR>
     *　@　@−入力項目物理名１〜３がnullの場合は、<BR>
     *　@　@−連結項目デリミッタが指定されている場合（連結項目デリミッタ != null）、<BR>
     *　@　@入力項目物理名１，２，３の値をデリミッタにて連結する。<BR>
     *<BR>
     *　@　@（※2） DBレイアウト「口座開設伝票項目マスタ」参照。<BR>
     *<BR>  
     *　@２−２）　@伝票の識別コード新規採番<BR> 
     *　@　@注文識別コード採番サービス.get新規識別コード()にて識別コードを取得し、<BR>
     *　@　@行オブジェクトの識別コード（order_request_number）にセットする。<BR>
     *<BR>  
     *　@　@[get新規識別コード()に指定する引数]  <BR>
     *　@　@証券会社コード：　@this.get証券会社コード() <BR>
     *　@　@部店コード：　@this.get部店コード() <BR>
     *　@　@銘柄タイプ：　@ProductTypeEnum.その他<BR>
     *<BR>  
     *３）　@DB更新<BR>
     *　@３−１）　@既存行削除<BR>
     *　@　@以下の条件にて重要事項確認書伝票（G1159）テーブルを検索する。<BR>
     *　@　@該当行が既に存在する場合は、該当行をdeleteする。<BR>
     *<BR>  
     *　@　@[条件]<BR>
     *　@　@証券会社コード =  this.get証券会社コード() And <BR> 
     *　@　@識別コード = this.get識別コード() And <BR>
     *　@　@伝票通番 = 伝票通番 And<BR>
     *　@　@処理区分 = ”未処理”<BR>
     *<BR>  
     *　@３−２）　@伝票行挿入<BR> 
     *　@　@１）〜２）で編集した行オブジェクトをDBに更新（DB-insertする）。<BR>
     *@@param l_strSerialNo - (伝票通番)<BR>
     *伝票通番<BR>
     *@@throws WEB3BaseException
     */
    public void saveVoucherRow(String l_strSerialNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveVoucherRow(String)";
        log.entering(STR_METHOD_NAME);
        
        //１）デフォルト設定行生成 
        HostFDepositVoucherParams l_fDepositVoucherParams = 
            new HostFDepositVoucherParams();
        
        //デフォルト設定（※1）の通りにプロパティをセットする。
        try
        {
            String l_strInstitutionCode = this.accOpenExpAccountOpen.getInstitutionCode();
            String l_strBranchCode = this.accOpenExpAccountOpen.getBranchCode();
            String l_strAccountCode = this.accOpenExpAccountOpen.getAccountCode();
            String l_strRequestNumber = this.accOpenExpAccountOpen.getRequestNumber();
            
            log.debug("デフォルト設定（※1）の通りにプロパティをセットする");
            
            //データコード  
            l_fDepositVoucherParams.setRequestCode(WEB3HostRequestCodeDef.F_DEPOSIT_REG);
            
            //証券会社コード       
            l_fDepositVoucherParams.setInstitutionCode(
                super.getStringByByteNumber(l_strInstitutionCode, 3));
            
            //部店コード
            l_fDepositVoucherParams.setBranchCode(
                super.getStringByByteNumber(l_strBranchCode, 3));
            
            //顧客コード
            l_fDepositVoucherParams.setAccountCode(
                super.getStringByByteNumber(l_strAccountCode, 7));
            
            //扱者コード 
            ExpAccountOpenRow l_expAccountOpenRow = (ExpAccountOpenRow)this.accOpenExpAccountOpen.getDataSourceObject();
            String l_strSonarTraderCode = l_expAccountOpenRow.getSonarTraderCode();
                l_fDepositVoucherParams.setTraderCode(super.getStringByByteNumber(l_strSonarTraderCode, 5));
            
            //識別コード（口座開設見込客） 
            l_fDepositVoucherParams.setAccOpenRequestNumber(
                super.getStringByByteNumber(l_strRequestNumber, 13));
            
            //伝票通番   
            l_fDepositVoucherParams.setSerialNo(l_strSerialNo);
            
            //登録区分 
            l_fDepositVoucherParams.setRegistDiv(WEB3RegDivDef.NEW);
            
            //通貨コード  
            l_fDepositVoucherParams.setCurrencyCode(null);
            
            //銀行コード  
            l_fDepositVoucherParams.setBankCode(
                super.getStringByByteNumber(l_expAccountOpenRow.getFinInstitutionCode(), 4));
            
            //支店コード  
            l_fDepositVoucherParams.setBankBranchCode(
                super.getStringByByteNumber(l_expAccountOpenRow.getFinBranchCode(), 3));
            
            //口座番号 
            l_fDepositVoucherParams.setAccountNo(
                super.getStringByByteNumber(l_expAccountOpenRow.getForeignAccountNo(), 20));
            
            //口座名義人名  
//            l_fDepositVoucherParams.setAccountName(
//                super.getStringByByteNumber(l_expAccountOpenRow.getForeignAccountName(), 60));
            l_fDepositVoucherParams.setAccountName(
                    super.getEmStringByByteNumber(l_expAccountOpenRow.getForeignAccountName(), 60));
            
            //預金  
            l_fDepositVoucherParams.setAmmount(
                super.getStringByByteNumber(l_expAccountOpenRow.getForeignSaveDiv(), 1));
            
            //手数料 
            l_fDepositVoucherParams.setFee(WEB3FeeDef.OUR_COMPANY_BURDEN);
            
            //口座名義人（英数）
            l_fDepositVoucherParams.setAccountNameEng(
                    super.getStringByByteNumber(l_expAccountOpenRow.getForeignAccountNameEng(), 30));
            
            //銀行名 
            l_fDepositVoucherParams.setBankName(null);
            
            //支店名 
            l_fDepositVoucherParams.setBankBranchName(null);
            
            //予備  
            l_fDepositVoucherParams.setExtra(null);
            
            //処理区分  
            l_fDepositVoucherParams.setStatus(WEB3StatusDef.NOT_DEAL);            
                       
            //送信日時 
            l_fDepositVoucherParams.setSendTimestamp(null);
            
            Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp(); 
            //作成日時  
            l_fDepositVoucherParams.setCreatedTimestamp(l_tsSystemTimestamp);
            
            //更新日時              
            l_fDepositVoucherParams.setLastUpdatedTimestamp(l_tsSystemTimestamp);
            
            //２）　@カスタマイズ項目セット
            log.debug("カスタマイズ項目セット");
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor(); 
            
            //[条件�@@]  
            log.debug("==========>[条件�@@]");
            String l_strWhereItem =
                "institution_code = ? and " +        //口座開設伝票項目マスタ.証券会社コード = this.get証券会社コード() And 
                "branch_code = ? and " +             //口座開設伝票項目マスタ.部店コード = this.get部店コード() And  
                "account_div = ? and " +             //口座開設伝票項目マスタ.口座区分 = this.get口座区分() And  
                "request_code = ? and " +            //口座開設伝票項目マスタ.データコード = this.getデータコード() And  
                "serial_no = ? ";                    //口座開設伝票項目マスタ.伝票通番 = 伝票通番
            
            String l_strRequestCode = this.getRequestCode();

            Object[] l_bindVarsItems =
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
                    l_bindVarsItems);
            
            //該当行がない場合、条件�Aで検索する。
            if (l_lisRowItems == null || l_lisRowItems.size() == 0)
            {
                log.debug("該当行がない場合、条件�Aで検索する");
                //[条件�A] 
                Object[] l_bindVarsItem2s =
                    {l_strInstitutionCode,
                     "000",                            //口座開設伝票項目マスタ.部店コード = "000" 
                     this.getAccountDiv(),
                     l_strRequestCode,
                     l_strSerialNo};
                l_lisRowItems =
                    l_queryProcesser.doFindAllQuery(
                        AccOpenVoucherItemRow.TYPE,
                        l_strWhereItem,
                        l_bindVarsItem2s); 
            }
            
            int l_intSize = 0;
            if (l_lisRowItems != null)
            {
                l_intSize = l_lisRowItems.size();
            }
            AccOpenVoucherItemRow l_accOpenVoucherItemRow = null;
            if(l_intSize > 0)
            {
                //２−１）カスタマイズ編集
                log.debug("２−１）　@カスタマイズ編集");
                for(int i = 0; i < l_intSize; i++)
                {
                    l_accOpenVoucherItemRow = (AccOpenVoucherItemRow)l_lisRowItems.get(i);
                    String l_strValue = null;
                    //（項目編集方法@ == 固定値）の場合、固定セット値の値をセットする。                    
                    if (WEB3EditWayDivDef.FIXED_VALUE.equals(l_accOpenVoucherItemRow.getEditWayDiv()))
                    {
                    	log.debug("（項目編集方法@ == 固定値）の場合、固定セット値の値をセットする。");
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
                        
                        log.debug("入力項目物理名１，２，３の値をデリミッタにて連結する");
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
                        l_fDepositVoucherParams.setRequestCode(super.getStringByByteNumber(l_strValue, 5));
                    }
                    
                    //証券会社コード
                    if (WEB3AccountOpenOutputItemSymbolNameDef.INSTITUTION_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる 
                        l_fDepositVoucherParams.setInstitutionCode(super.getStringByByteNumber(l_strValue, 3));    
                    }
                    
                    //部店コード
                    if (WEB3AccountOpenOutputItemSymbolNameDef.BRANCH_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_fDepositVoucherParams.setBranchCode(super.getStringByByteNumber(l_strValue, 3));    
                    }
                    
                    //顧客コード
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる  
                        l_fDepositVoucherParams.setAccountCode(super.getStringByByteNumber(l_strValue, 7));   
                    }
                        
                    //扱者コード
                    if (WEB3AccountOpenOutputItemSymbolNameDef.TRADER_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_fDepositVoucherParams.setTraderCode(super.getStringByByteNumber(l_strValue, 5));     
                    }
                        
                    //識別コード（口座開設見込客）
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ACC_OPEN_REQUEST_NUMBER.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_fDepositVoucherParams.setAccOpenRequestNumber(super.getStringByByteNumber(l_strValue, 13));     
                    }
                        
                    //伝票通番
                    if (WEB3AccountOpenOutputItemSymbolNameDef.SERIAL_NO.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_fDepositVoucherParams.setSerialNo(super.getStringByByteNumber(l_strValue, 3));     
                    }
                        
                    //登録区分
                    if (WEB3AccountOpenOutputItemSymbolNameDef.REGIST_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_fDepositVoucherParams.setRegistDiv(super.getStringByByteNumber(l_strValue, 1));     
                    }
                    
                    //通貨コード
                    if (WEB3AccountOpenOutputItemSymbolNameDef.CURENCY_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_fDepositVoucherParams.setCurrencyCode(super.getStringByByteNumber(l_strValue, 2));     
                    }
                    
                    //銀行コード
                    if (WEB3AccountOpenOutputItemSymbolNameDef.BANK_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_fDepositVoucherParams.setBankCode(super.getStringByByteNumber(l_strValue, 4));     
                    }
                    
                    //支店コード
                    if (WEB3AccountOpenOutputItemSymbolNameDef.OTHER_BRANCH_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_fDepositVoucherParams.setBankBranchCode(super.getStringByByteNumber(l_strValue, 3));     
                    }
                    
                    //口座番号
                    if (WEB3AccountOpenOutputItemSymbolNameDef.FIN_ACCOUNT_NO.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_fDepositVoucherParams.setAccountNo(super.getStringByByteNumber(l_strValue, 20));     
                    }
                    
                    //口座名義人名
                    if (WEB3AccountOpenOutputItemSymbolNameDef.FIN_ACCOUNT_NAME.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
//                        l_fDepositVoucherParams.setAccountName(super.getStringByByteNumber(l_strValue, 60));     
                        l_fDepositVoucherParams.setAccountName(super.getEmStringByByteNumber(l_strValue, 60));     
                    }
                    
                    //預金
                    if (WEB3AccountOpenOutputItemSymbolNameDef.AMMOUNT.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_fDepositVoucherParams.setAmmount(super.getStringByByteNumber(l_strValue, 1));     
                    }
                    
                    //手数料
                    if (WEB3AccountOpenOutputItemSymbolNameDef.FEE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_fDepositVoucherParams.setFee(super.getStringByByteNumber(l_strValue, 1));     
                    }
                    
                    //口座名義人（英数）
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_NAME_ENG.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_fDepositVoucherParams.setAccountNameEng(super.getStringByByteNumber(l_strValue, 30));     
                    }
                    
                    //銀行名
                    if (WEB3AccountOpenOutputItemSymbolNameDef.BANK_NAME.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_fDepositVoucherParams.setBankName(super.getStringByByteNumber(l_strValue, 32));     
                    }
                                       
                    //支店名
                    if (WEB3AccountOpenOutputItemSymbolNameDef.BANK_BRANCH_NAME.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_fDepositVoucherParams.setBankBranchName(super.getStringByByteNumber(l_strValue, 32));     
                    }
                    
                    //予備
                    if (WEB3AccountOpenOutputItemSymbolNameDef.EXTRA.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_fDepositVoucherParams.setExtra(super.getStringByByteNumber(l_strValue, 18));     
                    }
                    
                    //処理区分
                    if (WEB3AccountOpenOutputItemSymbolNameDef.STATUS.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる
                        l_fDepositVoucherParams.setStatus(super.getStringByByteNumber(l_strValue, 1));     
                    }    
                }                
            }
            
            //２−２）伝票の識別コード新規採番
            log.debug("伝票の識別コード新規採番 "); 
            WEB3HostReqOrderNumberManageService l_reqOrderNumberManageService=
                (WEB3HostReqOrderNumberManageService)Services.getService(
                    WEB3HostReqOrderNumberManageService.class);
            
            //注文識別コード採番サービス.get新規識別コード()にて識別コードを取得し、
            String l_strNewNumber = 
                l_reqOrderNumberManageService.getNewNumber(
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    ProductTypeEnum.OTHER);
            l_fDepositVoucherParams.setOrderRequestNumber(l_strNewNumber);
            
            //３−１）　@既存行削除
            String l_strWhere =
                "institution_code = ? and " +           //証券会社コード = this.get証券会社コード() And 
                "order_request_number = ? and " +    //識別コード = this.get識別コード() And   
                "serial_no = ? and " +                  //伝票通番 = 伝票通番 And   
                "status = ? ";                          //処理区分 = ”未処理” 
            
            Object[] l_bindVars =
                {l_strInstitutionCode,
                 l_strRequestNumber,
                 l_strSerialNo,
                 WEB3StatusDef.NOT_DEAL};
            
            l_queryProcesser.doDeleteAllQuery(
                HostFDepositVoucherRow.TYPE,
                l_strWhere,
                l_bindVars); 
            
            //３−２）　@伝票行挿入 
            l_queryProcesser.doInsertQuery(l_fDepositVoucherParams);
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
     *（saveDelete伝票行()の実装）<BR>
     *　@口座開設伝票を１件削除する。<BR>
     *<BR> 
     *　@外貨預金口座登録伝票（G43）テーブルの以下の条件に当てはまる行を取得する。<BR> 
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
     *@@throws WEB3BaseException
     */
    public boolean saveDeleteVoucherRow(String l_strSerialNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveDeleteVoucherRow(String)";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            String l_strWhere =
                "institution_code = ? and " +        //証券会社コード = this.get証券会社コード() And 
                "acc_open_request_number = ? and " +    //識別コード = this.get識別コード() And   
                "serial_no = ? and " +               //伝票通番 = 伝票通番 And   
                "status = ? ";                       //処理区分 = ”未処理”
            
            String l_strInstitutionCode = this.getInstitutionCode();
            String l_strRequestNumber = this.getRequestNumber();
            
            Object[] l_bindVars =
                {l_strInstitutionCode,
                 l_strRequestNumber,
                 l_strSerialNo,
                 WEB3StatusDef.NOT_DEAL};
            
            List l_lisRows = 
                l_queryProcesser.doFindAllQuery(
                    HostFDepositVoucherRow.TYPE,
                    l_strWhere,
                    l_bindVars);
            
            if (l_lisRows == null || l_lisRows.size() == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return false;    
            }
            else
            {
                l_queryProcesser.doDeleteAllQuery(
                    HostFDepositVoucherRow.TYPE,
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
     * @@return Object<BR>
     */
    public Object getDataSourceObject()
    {        
        return null;
    }
    
    /**
     * 入力物理名と口座開設見込客テーブルの物理名の比較<BR>
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

        Field[] l_fields = l_expAccountOpenParams.getClass().getDeclaredFields();
        
        int l_int = 0;
        if (l_fields != null)
        {
            l_int = l_fields.length;
        }
        for (int i = 0; i < l_int; i++)
        {
            if (l_str.equals(l_fields[i].getName()))
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
    
    /**
     * @@roseuid 41B45E6D00EA
     */
    public WEB3AccOpenForeignRegVoucher()
    {
        
    }
    

}
@
