head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.28.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenAgencyInfoRegVoucher.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 機@構通知情報登録伝票（WEB3AccOpenAgencyInfoRegVoucher.java）
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/13 武波 (中訊) 新規作成 モデルNo.171
Revision History : 2009/09/04 張騰宇 (中訊) 仕様変更 モデル210
Revision History : 2009/09/09 張騰宇 (中訊) ＤＢレイアウト No.064
Revision History : 2009/09/16 張騰宇 (中訊) ＤＢレイアウト No.067
*/
package webbroker3.accountopen;

import java.lang.reflect.Field;
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
import webbroker3.accountopen.data.HostAgencyNotifyVoucherParams;
import webbroker3.accountopen.data.HostAgencyNotifyVoucherRow;
import webbroker3.accountopen.define.WEB3AccountOpenExpAccountOpenSymbolNameDef;
import webbroker3.accountopen.define.WEB3AccountOpenOutputItemSymbolNameDef;
import webbroker3.accountopen.define.WEB3AccountOpenVoucherCodeDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BranchCodeDef;
import webbroker3.common.define.WEB3CatDelimitterDef;
import webbroker3.common.define.WEB3EditWayDivDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3RegDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (機@構通知情報登録伝票)<BR>
 * 機@構通知情報登録伝票<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AccOpenAgencyInfoRegVoucher extends WEB3AccOpenVoucher
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenAgencyInfoRegVoucher.class);

    /**
     * (機@構通知情報登録伝票)<BR>
     * 機@構通知情報登録伝票<BR>
     */
    public WEB3AccOpenAgencyInfoRegVoucher()
    {

    }

    /**
     * 口座開設見込客オブジェクトを指定し、機@構通知情報登録伝票インスタンスを作成する。<BR>
     * <BR>
     * １）　@インスタンス生成<BR>
     * 　@機@構通知情報登録伝票インスタンスを生成する。<BR>
     * <BR>
     * ２）　@口座開設見込客プロパティセット<BR>
     * 　@生成したインスタンス.set口座開設見込客()にて、口座開設見込客プロパティをセットする。<BR>
     * <BR>
     * 　@[set口座開設見込客()に指定する引数]<BR>
     * 　@口座開設見込客：　@口座開設見込客<BR>
     * <BR>
     * ３）　@口座開設伝票マスタプロパティセット<BR>
     * 　@生成したインスタンス.set伝票マスタ()にて、伝票マスタ行プロパティをセットする。<BR>
     * <BR>
     * ４）　@生成したインスタンスを返却する。<BR>
     * @@param l_accOpenExpAccountOpen - (口座開設見込客)<BR>
     * 口座開設見込客オブジェクト<BR>
     * @@return WEB3AccOpenAgencyInfoRegVoucher
     * @@throws WEB3BaseException
     */
    public static  WEB3AccOpenAgencyInfoRegVoucher getInstance(
        WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInstance(WEB3AccOpenExpAccountOpen)";
        log.entering(STR_METHOD_NAME);

        //１）インスタンス生成
        WEB3AccOpenAgencyInfoRegVoucher l_accOpenAgencyInfoRegVoucher =
            new WEB3AccOpenAgencyInfoRegVoucher();

        //２）口座開設見込客プロパティセット
        l_accOpenAgencyInfoRegVoucher.setAccOpenExpAccountOpen(l_accOpenExpAccountOpen);

        //３）口座開設伝票マスタプロパティセット
        l_accOpenAgencyInfoRegVoucher.setAccOpenVoucherMaster();

        //４）　@生成したインスタンスを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_accOpenAgencyInfoRegVoucher;
    }

    /**
     * (isオンライン伝票)<BR>
     * (isオンライン伝票()の実装)<BR>
     * <BR>
     * trueを返却する。<BR>
     * @@return boolean
     */
    public boolean isOnlineVoucher()
    {
        final String STR_METHOD_NAME = "isOnlineVoucher()";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (getデータコード)<BR>
     * (getデータコード()の実装)<BR>
     * <BR>
     * データコード.外貨預金口座登録（GI865）を返却する。<BR>
     * @@return String
     */
    public String getRequestCode()
    {
        final String STR_METHOD_NAME = "getRequestCode()";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return WEB3HostRequestCodeDef.ACCOPEN_AGENCY_INFO_REGIST;
    }

    /**
     * (get伝票コード)<BR>
     * (get伝票コード()の実装)<BR>
     * <BR>
     * ”GS103”を返却する。<BR>
     * @@return String
     */
    public String getVoucherCode()
    {
        final String STR_METHOD_NAME = "getVoucherCode()";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return WEB3AccountOpenVoucherCodeDef.ACCOPEN_AGENCY_INFO_REGIST;
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
     * 　@　@作成可能な場合（is作成可能伝票() == true）、<BR>
     * 　@　@　@当該要素の処理を行わず次の要素を処理する。（continue;）<BR>
     * <BR>
     * 　@　@＃作成可能な場合、項目値は変更しても良いので伝票参照項目名には含めない。<BR>
     * <BR>
     * 　@２－２）　@カスタマイズ参照項目列物理名取得<BR>
     * 　@　@機@構通知情報登録伝票（GS103）キューテーブルの各項目について、<BR>
     * 　@　@　@下記の処理を実施する。<BR>
     * <BR>
     * 　@　@this.getカスタマイズ参照項目()をコールし、<BR>
     * 　@　@　@口座開設見込客テーブル列物理名の配列を取得する。<BR>
     * <BR>
     * 　@　@[getカスタマイズ参照項目()に指定する引数]<BR>
     * 　@　@伝票通番：　@口座開設伝票マスタ行[index].伝票通番<BR>
     * 　@　@伝票出力項目物理名：<BR>
     * 　@　@　@（※1 機@構通知情報登録伝票（GS103）キューテーブルの処理対象項目）<BR>
     * 　@　@伝票参照項目初期値：<BR>
     * 　@　@　@（※2 機@構通知情報登録伝票（GS103）キューテーブルの処理対象項目デフォルト設定値）<BR>
     * <BR>
     * 　@　@（※2）　@機@構通知情報登録伝票（GS103）キューテーブルの処理対象項目デフォルト設定値<BR>
     * 　@　@DBレイアウト<BR>
     * 　@　@　@「機@構通知情報登録伝票（GS103）キューテーブル.xls#デフォルトDB設定論理」シートを参照し、<BR>
     * 　@　@該当項目の説明欄に、口座開設見込客テーブルから編集する指定があれば、<BR>
     * 　@　@　@指定項目の列物理名配列。<BR>
     * 　@　@以外は、null。<BR>
     * <BR>
     * 　@２－３）　@伝票使用項目Table（：Hashtable）に追加<BR>
     * 　@　@伝票使用項目Table（：Hashtable）.put()<BR>
     * 　@　@　@にてthis.getカスタマイズ参照項目()戻り値を一要素ずつ追加する。<BR>
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
     * @@throws WEB3BaseException
     */
    public String[] getConfirmedItemName() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getConfirmedItemName()";
        log.entering(STR_METHOD_NAME);

        //１）伝票使用項目Table（：Hashtable）生成
        Hashtable l_voucherItemList = new Hashtable();

        //２）使用項目セット
        //this.口座開設伝票マスタ行[]の各要素毎に、２－１）～２－３）の処理を実施する。
        int l_intLength = 0;
        if (accOpenVoucherMasterParamses != null)
        {
            l_intLength = this.accOpenVoucherMasterParamses.length;
        }

        for (int i = 0; i < l_intLength; i++)
        {
            //２－１）　@作成可能判定
            //is作成可能伝票()にて、作成可能な伝票かを判定する。
            //[is作成可能伝票()に指定する引数]
            //伝票通番：　@口座開設伝票マスタ行[index].伝票通番
            String l_strSerialNo = accOpenVoucherMasterParamses[i].getSerialNo();
            boolean l_blnCreatedPossibleVoucher = this.isCreatedPossibleVoucher(l_strSerialNo);

            //作成可能でない場合（is作成可能伝票() == false）のみ、２－２）を実施する。
            if (!l_blnCreatedPossibleVoucher)
            {
                //機@構通知情報登録伝票（GS103）キューテーブルの各項目について、下記の処理を実施する。
                //this.getカスタマイズ参照項目()をコールし、口座開設見込客テーブル列物理名の配列を取得する。
                //[getカスタマイズ参照項目()に指定する引数]
                //伝票通番：　@口座開設伝票マスタ行[index].伝票通番
                //伝票出力項目物理名：　@（※1 機@構通知情報登録伝票（GS103）キューテーブルの処理対象項目）
                //伝票参照項目初期値：　@（※2 機@構通知情報登録伝票（GS103）キューテーブルの処理対象項目デフォルト設定値）
                //（※2）　@機@構通知情報登録伝票（GS103）キューテーブルの処理対象項目デフォルト設定値
                //DBレイアウト 「機@構通知情報登録伝票（GS103）キューテーブル.xls#デフォルトDB設定論理」シートを参照し、
                //該当項目の説明欄に、口座開設見込客テーブルから編集する指定があれば、指定項目の列物理名配列。
                //以外は、null。

                String[] l_strValues1 = new String[1];

                //証券会社コード
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.INSTITUTION_CODE;
                //２－２）　@カスタマイズ参照項目列物理名取得
                String[] l_strCustomizingRefItem = this.getCustomizingRefItem(
                    l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.INSTITUTION_CODE,
                    l_strValues1);
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
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }

                //識別コード（口座開設見込客）
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ACC_OPEN_REQUEST_NUMBER;
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

                //フリガナ
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_ACC_NAME_KANA1;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_NAME_KANA1, l_strValues1);
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

                //名称
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_ACC_NAME1;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_NAME1, l_strValues1);
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
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ZIP_CODE;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.ZIP_CODE1, l_strValues1);
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
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ZIP_CODE;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.ZIP_CODE2, l_strValues1);
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

                //住所
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_ADDRESS_LINE1;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE1, l_strValues1);
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

                //代表者の役職
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_REP_POST;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.REPRESENT_POST, l_strValues1);
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

                //代表者のフリガナ
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_REP_NAME_KANA1;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.REPRESENT_NAME_KANA1, l_strValues1);
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

                //代表者の氏名
                //２－２）　@カスタマイズ参照項目列物理名取得
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_REP_NAME1;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.REPRESENT_NAME1, l_strValues1);
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

                //フリガナ
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_KANA, null);
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

                //氏名1
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_NAME1, null);
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

                //金融機@関等コード
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_INSTITUTION, null);
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

                //店舗コード
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_BRANCH, null);
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

                //預金種目
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_ACC_TYPE, null);
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

                //口座番号
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_ACC_NO, null);
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

                //名義人区分
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_ACC_DIV, null);
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
     * 　@機@構通知情報登録伝票（GS103）キューテーブル行オブジェクトを生成し、<BR>
     * 　@デフォルト設定（※1）の通りにプロパティをセットする。<BR>
     * <BR>
     * 　@文字列をセットする場合、出力項目のデータサイズを超えた場合は、<BR>
     * 　@　@データサイズ以降の文字を切り捨てる。<BR>
     * <BR>
     * 　@（※1）　@機@構通知情報登録伝票（GS103）キューテーブルの処理対象項目デフォルト設定<BR>
     * 　@DBレイアウト 「機@構通知情報登録伝票（GS103）キューテーブル.xls#デフォルトDB設定論理」シート参照。<BR>
     * <BR>
     * ２）　@カスタマイズ項目セット<BR>
     * 　@口座開設伝票項目マスタテーブルを以下の条件①@で検索する。<BR>
     * 　@該当行がない場合、条件②で検索する。<BR>
     * <BR>
     * 　@[条件①@]<BR>
     * 　@口座開設伝票項目マスタ.証券会社コード = this.get証券会社コード() And<BR>
     * 　@口座開設伝票項目マスタ.部店コード = this.get部店コード() And<BR>
     * 　@口座開設伝票項目マスタ.口座区分 = this.get口座区分() And<BR>
     * 　@口座開設伝票項目マスタ.データコード = this.getデータコード() And<BR>
     * 　@口座開設伝票項目マスタ.伝票通番 = 伝票通番<BR>
     * <BR>
     * 　@[条件②]<BR>
     * 　@口座開設伝票項目マスタ.証券会社コード = this.get証券会社コード() And<BR>
     * 　@口座開設伝票項目マスタ.部店コード = "000" And<BR>
     * 　@口座開設伝票項目マスタ.口座区分 = this.get口座区分() And<BR>
     * 　@口座開設伝票項目マスタ.データコード = this.getデータコード() And<BR>
     * 　@口座開設伝票項目マスタ.伝票通番 = 伝票通番<BR>
     * <BR>
     * 　@条件①@，②のどちらかで該当行がある場合は、２－１）の処理を実施する。<BR>
     * <BR>
     * 　@２－１）　@カスタマイズ編集<BR>
     * 　@　@検索結果の各行毎に、出力項目物理名が示す伝票項目の値を、指定の方法@で再セットする。<BR>
     * 　@　@文字列をセットする場合、<BR>
     * 　@　@出力項目のデータサイズを超えた場合は、データサイズ以降の文字を切り捨てる。<BR>
     * <BR>
     * 　@　@○ （項目編集方法@ == 固定値）の場合、固定セット値の値をセットする。<BR>
     * 　@　@○ 以外の場合、入力項目物理名１～３が示す口座開設見込客の項目値(※2)をセットする。<BR>
     * 　@　@　@　@－入力項目物理名１～３がnullの場合は、<BR>
     * 　@　@　@　@－連結項目デリミッタが指定されている場合（連結項目デリミッタ != null）、<BR>
     * 　@　@　@　@　@入力項目物理名１，２，３の値をデリミッタにて連結する。<BR>
     * <BR>
     * 　@　@（※2） DBレイアウト「口座開設伝票項目マスタ」参照。<BR>
     * 　@<BR>
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
     * 　@　@以下の条件にて機@構通知情報登録伝票（GS103）キューテーブルを検索する。<BR>
     * 　@　@該当行が既に存在する場合は、該当行をdeleteする。<BR>
     * <BR>
     * 　@　@[条件]<BR>
     * 　@　@証券会社コード = this.get証券会社コード() And<BR>
     * 　@　@識別コード = this.get識別コード() And<BR>
     * 　@　@伝票通番 = 伝票通番 And<BR>
     * 　@　@処理区分 = ”未処理”<BR>
     * <BR>
     * 　@３－２）　@伝票行挿入<BR>
     * 　@　@１）～２）で編集した行オブジェクトをDBに更新（DB-insertする）。<BR>
     * <BR>
     * @@param l_strSerialNo - (伝票通番)<BR>
     * 伝票通番<BR>
     * @@throws WEB3BaseException
     */
    public void saveVoucherRow(String l_strSerialNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "saveVoucherRow(String)";
        log.entering(STR_METHOD_NAME);
        //１）　@デフォルト設定行生成
        //機@構通知情報登録伝票（GS103）キューテーブル行オブジェクトを生成し、
        //デフォルト設定（※1）の通りにプロパティをセットする。
        //文字列をセットする場合、出力項目のデータサイズを超えた場合は、
        //データサイズ以降の文字を切り捨てる。
        //（※1）　@機@構通知情報登録伝票（GS103）キューテーブルの処理対象項目デフォルト設定
        //DBレイアウト
        //「機@構通知情報登録伝票（GS103）キューテーブル.xls#デフォルトDB設定論理」シート参照。
        HostAgencyNotifyVoucherParams l_hostAgencyNotifyVoucherParams =
            new HostAgencyNotifyVoucherParams();

        String l_strInstitutionCode = this.accOpenExpAccountOpen.getInstitutionCode();
        String l_strBranchCode = this.accOpenExpAccountOpen.getBranchCode();
        String l_strAccountCode = this.accOpenExpAccountOpen.getAccountCode();
        String l_strRequestNumber = this.accOpenExpAccountOpen.getRequestNumber();

        //データコード:機@構通知情報登録：”GI865”
        l_hostAgencyNotifyVoucherParams.setRequestCode(
            super.getStringByByteNumber(WEB3HostRequestCodeDef.ACCOPEN_AGENCY_INFO_REGIST, 5));

        //証券会社コード:口座開設見込客.証券会社コードを編集する。
        l_hostAgencyNotifyVoucherParams.setInstitutionCode(
            super.getStringByByteNumber(l_strInstitutionCode, 3));

        //部店コード:口座開設見込客.部店コードを編集する。
        l_hostAgencyNotifyVoucherParams.setBranchCode(
            super.getStringByByteNumber(l_strBranchCode, 3));

        //顧客コード:口座開設見込客.口座コードを編集する。
        l_hostAgencyNotifyVoucherParams.setAccountCode(
            super.getStringByByteNumber(l_strAccountCode, 7));

        //扱者コード:口座開設見込客.扱者コード（SONAR）を編集する。
        ExpAccountOpenRow l_expAccountOpenRow =
            (ExpAccountOpenRow)this.accOpenExpAccountOpen.getDataSourceObject();
        String l_strSonarTraderCode = l_expAccountOpenRow.getSonarTraderCode();
        l_hostAgencyNotifyVoucherParams.setTraderCode(
            super.getStringByByteNumber(l_strSonarTraderCode, 5));

        //識別コード（口座開設見込客）:口座開設見込客.識別コードを編集する。
        l_hostAgencyNotifyVoucherParams.setAccOpenRequestNumber(
            super.getStringByByteNumber(l_strRequestNumber, 13));

        //伝票通番:引数.伝票通番
        l_hostAgencyNotifyVoucherParams.setSerialNo(
            super.getStringByByteNumber(l_strSerialNo, 3));

        //登録区分:1：新規
        l_hostAgencyNotifyVoucherParams.setRegistDiv(
            super.getStringByByteNumber(WEB3RegDivDef.NEW, 1));

        //フリガナ:口座開設見込客.機@構通知情報.フリガナ1を編集する。
        String l_strAgencyAccNameKana1 =
            WEB3StringTypeUtility.to1byteKana(l_expAccountOpenRow.getAgencyAccNameKana1());
        l_hostAgencyNotifyVoucherParams.setAccountNameKana1(
            super.getStringByByteNumber(l_strAgencyAccNameKana1, 120));

        //名称:口座開設見込客.機@構通知情報.名称1を編集する。
        String l_strAgencyAccName1 = l_expAccountOpenRow.getAgencyAccName1();
        l_hostAgencyNotifyVoucherParams.setAccountName1(
            super.getEmStringByByteNumber(l_strAgencyAccName1, 120));

        //郵便番号（親）:口座開設見込客.郵便番号の1～3桁目を編集する。
        String l_strZipCode = l_expAccountOpenRow.getZipCode();
        l_hostAgencyNotifyVoucherParams.setZipCode1(
            super.getStringByByteNumber(l_strZipCode, 3));

        //郵便番号（子）:口座開設見込客.郵便番号の4～7桁目を編集する。
        String l_strZipCode2 = this.getZipCode2(l_strZipCode);
        l_hostAgencyNotifyVoucherParams.setZipCode2(l_strZipCode2);

        //住所:  口座開設見込客.機@構通知情報.住所1を編集する。
        l_hostAgencyNotifyVoucherParams.setAddressLine1(
            super.getEmStringByByteNumber(
                l_expAccountOpenRow.getAgencyAddressLine1(), 96));

        //代表者の役職:口座開設見込客.機@構通知情報.代表者の役職を編集する。
        l_hostAgencyNotifyVoucherParams.setRepresentPost(
            super.getStringByByteNumber(l_expAccountOpenRow.getAgencyRepPost(), 40));

        //代表者のフリガナ:口座開設見込客.機@構通知情報.代表者のフリガナ1を編集する。
        String l_strAgencyRepNameKana1 =
            WEB3StringTypeUtility.to1byteKana(l_expAccountOpenRow.getAgencyRepNameKana1());
        l_hostAgencyNotifyVoucherParams.setRepresentNameKana1(
            super.getStringByByteNumber(l_strAgencyRepNameKana1, 120));

        //代表者の氏名:口座開設見込客.機@構通知情報.代表者の氏名1を編集する。
        l_hostAgencyNotifyVoucherParams.setRepresentName1(
            super.getEmStringByByteNumber(l_expAccountOpenRow.getAgencyRepName1(), 120));

        //フリガナ:null
        l_hostAgencyNotifyVoucherParams.setReceiptKana(null);

        //氏名1:null
        l_hostAgencyNotifyVoucherParams.setReceiptName1(null);

        //金融機@関等コード:null
        l_hostAgencyNotifyVoucherParams.setReceiptFinInstitution(null);

        //店舗コード:null
        l_hostAgencyNotifyVoucherParams.setReceiptFinBranch(null);

        //預金種目:null
        l_hostAgencyNotifyVoucherParams.setReceiptFinAccType(null);

        //口座番号:null
        l_hostAgencyNotifyVoucherParams.setReceiptFinAccNo(null);

        //名義人区分:null
        l_hostAgencyNotifyVoucherParams.setReceiptFinAccDiv(null);

        //処理区分:0：未処理
        l_hostAgencyNotifyVoucherParams.setStatus(
            super.getStringByByteNumber(WEB3StatusDef.NOT_DEAL, 1));

        //送信日時:null
        l_hostAgencyNotifyVoucherParams.setSendTimestamp(null);

        //作成日時:処理日時
        l_hostAgencyNotifyVoucherParams.setCreatedTimestamp(
            GtlUtils.getSystemTimestamp());

        //更新日時:処理日時
        l_hostAgencyNotifyVoucherParams.setLastUpdatedTimestamp(
            GtlUtils.getSystemTimestamp());

        try
        {
            QueryProcessor l_queryProcesser =
                Processors.getDefaultProcessor();

            //２）　@カスタマイズ項目セット
            //口座開設伝票項目マスタテーブルを以下の条件①@で検索する。
            //該当行がない場合、条件②で検索する。
            //[条件①@]
            //口座開設伝票項目マスタ.証券会社コード = this.get証券会社コード() And
            //口座開設伝票項目マスタ.部店コード = this.get部店コード() And
            //口座開設伝票項目マスタ.口座区分 = this.get口座区分() And
            //口座開設伝票項目マスタ.データコード = this.getデータコード() And
            //口座開設伝票項目マスタ.伝票通番 = 伝票通番
            String l_strWhereItem =
                "institution_code = ? and "
                + "branch_code = ? and "
                + "account_div = ? and "
                + "request_code = ? and "
                + "serial_no = ? ";

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
                    l_bindVarsItem);

            //該当行がない場合、条件②で検索する。
            if (l_lisRowItems == null || l_lisRowItems.size() == 0)
            {
                //[条件②]
                //口座開設伝票項目マスタ.証券会社コード = this.get証券会社コード() And
                //口座開設伝票項目マスタ.部店コード = "000" And
                //口座開設伝票項目マスタ.口座区分 = this.get口座区分() And
                //口座開設伝票項目マスタ.データコード = this.getデータコード() And
                //口座開設伝票項目マスタ.伝票通番 = 伝票通番
                Object l_bindVarsItem2[] =
                    {l_strInstitutionCode,
                    WEB3BranchCodeDef.DEFAULT,
                     this.getAccountDiv(),
                     l_strRequestCode,
                     l_strSerialNo};

                l_lisRowItems =
                    l_queryProcesser.doFindAllQuery(
                        AccOpenVoucherItemRow.TYPE,
                        l_strWhereItem,
                        l_bindVarsItem2);
            }

            //条件①@，②のどちらかで該当行がある場合は、２－１）の処理を実施する。
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
                    //検索結果の各行毎に、出力項目物理名が示す伝票項目の値を、
                    //指定の方法@で再セットする。
                    l_accOpenVoucherItemRow = (AccOpenVoucherItemRow)l_lisRowItems.get(i);
                    String l_strValue = null;
                    //○ （項目編集方法@ == 固定値）の場合、固定セット値の値をセットする。
                    if (WEB3EditWayDivDef.FIXED_VALUE.equals(l_accOpenVoucherItemRow.getEditWayDiv()))
                    {
                        l_strValue = l_accOpenVoucherItemRow.getFixedValue();
                    }
                    //○ 以外の場合、入力項目物理名１～３が示す口座開設見込客の項目値(※2)をセットする。
                    //－入力項目物理名１～３がnullの場合は
                    //－連結項目デリミッタが指定されている場合（連結項目デリミッタ != null）
                    //入力項目物理名１，２，３の値をデリミッタにて連結する。
                    //（※2） DBレイアウト「口座開設伝票項目マスタ」参照。
                    else
                    {
                        String l_strValue1 =
                            this.nameCompare(l_accOpenVoucherItemRow.getInputItemSymbolName1());
                        String l_strValue2 =
                            this.nameCompare(l_accOpenVoucherItemRow.getInputItemSymbolName2());
                        String l_strValue3 =
                            this.nameCompare(l_accOpenVoucherItemRow.getInputItemSymbolName3());
                        if (WEB3EditWayDivDef.EXP_ACCOUNT_OPEN_ITEM_TO_HALFKANA.equals(
                            l_accOpenVoucherItemRow.getEditWayDiv()))
                        {
                            l_strValue1 = WEB3StringTypeUtility.to1byteKana(l_strValue1);
                            l_strValue2 = WEB3StringTypeUtility.to1byteKana(l_strValue2);
                            l_strValue3 = WEB3StringTypeUtility.to1byteKana(l_strValue3);
                        }

                        //入力項目物理名１，２，３の値をデリミッタにて連結する。

                        if (l_accOpenVoucherItemRow.getCatDelimitter() != null
                            && !WEB3CatDelimitterDef.WITHOUT.equals(
                                l_accOpenVoucherItemRow.getCatDelimitter()))
                        {
                            if (l_strValue1 != null)
                            {
                                l_strValue = l_strValue1;
                                if (l_strValue2 != null)
                                {
                                    if (WEB3CatDelimitterDef.HALF_SPACE.equals(
                                        l_accOpenVoucherItemRow.getCatDelimitter()))
                                    {
                                        l_strValue = l_strValue + " " + l_strValue2;
                                    }
                                    else if (WEB3CatDelimitterDef.FULL_SPACE.equals(
                                        l_accOpenVoucherItemRow.getCatDelimitter()))
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
                                    if (WEB3CatDelimitterDef.HALF_SPACE.equals(
                                        l_accOpenVoucherItemRow.getCatDelimitter()))
                                    {
                                        l_strValue = l_strValue + " " + l_strValue3;
                                    }
                                    else if (WEB3CatDelimitterDef.FULL_SPACE.equals(
                                        l_accOpenVoucherItemRow.getCatDelimitter()))
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

                    //文字列をセットする場合、出力項目のデータサイズを超えた場合は、
                    //データサイズ以降の文字を切り捨てる。
                    //データコード
                    if (WEB3AccountOpenOutputItemSymbolNameDef.REQUEST_CODE.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setRequestCode(
                            super.getStringByByteNumber(l_strValue, 5));
                    }

                    //証券会社コード
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.INSTITUTION_CODE.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setInstitutionCode(
                            super.getStringByByteNumber(l_strValue, 3));
                    }
                    //部店コード
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.BRANCH_CODE.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setBranchCode(
                            super.getStringByByteNumber(l_strValue, 3));
                    }
                    //顧客コード
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_CODE.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setAccountCode(
                            super.getStringByByteNumber(l_strValue, 7));
                    }
                    //扱者コード
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.TRADER_CODE.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setTraderCode(
                            super.getStringByByteNumber(l_strValue, 5));
                    }
                    //識別コード（口座開設見込客）
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.ACC_OPEN_REQUEST_NUMBER.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setAccOpenRequestNumber(
                            super.getStringByByteNumber(l_strValue, 13));
                    }
                    //伝票通番
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.SERIAL_NO.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setSerialNo(
                            super.getStringByByteNumber(l_strValue, 3));
                    }
                    //登録区分
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.REGIST_DIV.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setRegistDiv(
                            super.getStringByByteNumber(l_strValue, 1));
                    }
                    //フリガナ
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_NAME_KANA1.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setAccountNameKana1(
                            super.getStringByByteNumber(WEB3StringTypeUtility.to1byteKana(l_strValue), 120));
                    }
                    //名称
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_NAME1.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setAccountName1(
                            super.getEmStringByByteNumber(l_strValue, 120));
                    }
                    //郵便番号（親）
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.ZIP_CODE1.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setZipCode1(
                            super.getStringByByteNumber(l_strValue, 3));
                    }
                    //郵便番号（子）
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.ZIP_CODE2.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setZipCode2(
                            this.getZipCode2(l_strValue));
                    }
                    //住所
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE1.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setAddressLine1(
                            super.getEmStringByByteNumber(l_strValue, 96));
                    }
                    //代表者の役職
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.REPRESENT_POST.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setRepresentPost(
                            super.getStringByByteNumber(l_strValue, 40));
                    }
                    //代表者のフリガナ
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.REPRESENT_NAME_KANA1.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setRepresentNameKana1(
                            super.getStringByByteNumber(WEB3StringTypeUtility.to1byteKana(l_strValue), 120));
                    }
                    //代表者の氏名
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.REPRESENT_NAME1.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setRepresentName1(
                            super.getEmStringByByteNumber(l_strValue, 120));
                    }
                    //フリガナ
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_KANA.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setReceiptKana(
                            super.getStringByByteNumber(l_strValue, 38));
                    }
                    //氏名
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_NAME1.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setReceiptName1(
                            super.getStringByByteNumber(l_strValue, 80));
                    }
                    //金融機@関等コード
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_INSTITUTION.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setReceiptFinInstitution(
                            super.getStringByByteNumber(l_strValue, 4));
                    }
                    //店舗コード
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_BRANCH.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setReceiptFinBranch(
                            super.getStringByByteNumber(l_strValue, 3));
                    }
                    //預金種目
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_ACC_TYPE.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setReceiptFinAccType(
                            super.getStringByByteNumber(l_strValue, 1));
                    }
                    //口座番号
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_ACC_NO.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setReceiptFinAccNo(
                            super.getStringByByteNumber(l_strValue, 7));
                    }
                    //名義人区分
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_ACC_DIV.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setReceiptFinAccDiv(
                            super.getStringByByteNumber(l_strValue, 1));
                    }
                    //処理区分
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.STATUS.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setStatus(
                            super.getStringByByteNumber(l_strValue, 1));
                    }
                }
            }

            //２－２）　@伝票の識別コード新規採番
            //注文識別コード採番サービス.get新規識別コード()にて識別コードを取得し、
            //行オブジェクトの識別コード（order_request_number）にセットする。
            //[get新規識別コード()に指定する引数]
            //証券会社コード：　@this.get証券会社コード()
            //部店コード：　@this.get部店コード()
            //銘柄タイプ：　@ProductTypeEnum.その他
            WEB3HostReqOrderNumberManageService l_reqOrderNumberManageService=
                (WEB3HostReqOrderNumberManageService)Services.getService(
                    WEB3HostReqOrderNumberManageService.class);
            String l_strNewNumber = l_reqOrderNumberManageService.getNewNumber(
                l_strInstitutionCode,
                l_strBranchCode,
                ProductTypeEnum.OTHER);
            l_hostAgencyNotifyVoucherParams.setOrderRequestNumber(l_strNewNumber);

            //３－１）　@既存行削除
            //以下の条件にて機@構通知情報登録伝票（GS103）キューテーブルを検索する。
            //該当行が既に存在する場合は、該当行をdeleteする。
            //証券会社コード = this.get証券会社コード() And
            //識別コード = this.get識別コード() And
            //伝票通番 = 伝票通番 And
            //処理区分 = ”未処理”
            String l_strWhere =
                "institution_code = ? and "
                + "acc_open_request_number = ? and "
                + "serial_no = ? and "
                + "status = ? ";

            Object l_bindVars[] =
                {l_strInstitutionCode,
                 l_strRequestNumber,
                 l_strSerialNo,
                 WEB3StatusDef.NOT_DEAL};
            l_queryProcesser.doDeleteAllQuery(
                HostAgencyNotifyVoucherParams.TYPE,
                l_strWhere,
                l_bindVars);

            //３－２）　@伝票行挿入
            //１）～２）で編集した行オブジェクトをDBに更新（DB-insertする）。
            l_queryProcesser.doInsertQuery(l_hostAgencyNotifyVoucherParams);
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
     * (saveDelete伝票行)<BR>
     * （saveDelete伝票行()の実装）<BR>
     * 口座開設伝票を１件削除する。<BR>
     * <BR>
     * 機@構通知情報登録伝票（GS103）キューテーブルの以下の条件に当てはまる行を取得する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@証券会社コード = this.get証券会社コード() And<BR>
     * 　@識別コード = this.get識別コード() And<BR>
     * 　@伝票通番 = 伝票通番 And<BR>
     * 　@処理区分 = ”未処理”<BR>
     * <BR>
     * 行が取得できなかった場合、falseを返却する。<BR>
     * 行が取得できた場合、該当行の削除（delete row）を行い、trueを返却する。<BR>
     * @@param l_strSerialNo - (伝票通番)<BR>
     * 伝票通番<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean saveDeleteVoucherRow(String l_strSerialNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "saveDeleteVoucherRow(String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //機@構通知情報登録伝票（GS103）キューテーブルの以下の条件に当てはまる行を取得する。
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();

            //証券会社コード = this.get証券会社コード() And
            //識別コード = this.get識別コード() And
            //伝票通番 = 伝票通番 And
            //処理区分 = ”未処理”
            String l_strWhere =
                " institution_code = ? and "
                + "acc_open_request_number = ? and "
                + "serial_no = ? and "
                + "status = ? ";

            String l_strInstitutionCode = this.getInstitutionCode();
            String l_strRequestNumber = this.getRequestNumber();

            Object l_bindVars[] =
                {l_strInstitutionCode,
                 l_strRequestNumber,
                 l_strSerialNo,
                 WEB3StatusDef.NOT_DEAL};

            List l_lisRows = l_queryProcesser.doFindAllQuery(
                HostAgencyNotifyVoucherRow.TYPE,
                l_strWhere,
                l_bindVars);
            if (l_lisRows == null || l_lisRows.size() == 0)
            {
                //行が取得できなかった場合、falseを返却する。
                log.exiting(STR_METHOD_NAME);
                return false;
            }
            else
            {
                //行が取得できた場合、該当行の削除（delete row）を行い、trueを返却する。
                l_queryProcesser.doDeleteAllQuery(
                    HostAgencyNotifyVoucherRow.TYPE,
                    l_strWhere,
                    l_bindVars);

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
     * 入力物理名と口座開設見込客テーブルの物理名の比較
     *
     * @@return Object
     * @@roseuid 41B45E6D033C
     */
    private String nameCompare(String l_str)
    {
        final String STR_METHOD_NAME = "nameCompare(String)";
        log.entering(STR_METHOD_NAME);

        if (l_str == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        ExpAccountOpenParams l_expAccountOpenParams =
            new ExpAccountOpenParams((ExpAccountOpenRow)this.accOpenExpAccountOpen.getDataSourceObject());

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

    /**
     * 口座開設見込客.郵便番号の4～7桁目を編集する。
     * @@param l_strZipCode
     * @@return
     */
    private String getZipCode2(String l_strZipCode)
    {
        final String STR_METHOD_NAME = "getZipCode2(String)";
        log.entering(STR_METHOD_NAME);
        if (l_strZipCode == null)
        {
            return null;
        }

        if (l_strZipCode.length() >= 7)
        {
            log.entering(STR_METHOD_NAME);
            return l_strZipCode.substring(3, 7);
        }
        else if (l_strZipCode.length() < 7 && l_strZipCode.length() > 4)
        {
            log.entering(STR_METHOD_NAME);
            return l_strZipCode.substring(l_strZipCode.length()-4, l_strZipCode.length());
        }
        else
        {
            log.entering(STR_METHOD_NAME);
            return l_strZipCode;
        }
    }
}
@
