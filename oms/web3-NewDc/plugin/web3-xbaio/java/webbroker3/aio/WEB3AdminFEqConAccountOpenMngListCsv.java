head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.33.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminFEqConAccountOpenMngListCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外株口座開設管理一覧CSV(WEB3AdminFEqConAccountOpenMngListCsv)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/3/21 屈陽 (中訊) 新規作成   
*/

package webbroker3.aio;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;

/**
 * (外株口座開設管理一覧CSV)<BR>
 * 外株口座開設管理一覧CSVクラス 
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */

public class WEB3AdminFEqConAccountOpenMngListCsv extends WEB3GentradeCsvDataModel 
{
    
    /**
     * (証券会社コードラベル)<BR>
     * 証券会社コードラベル
     */
    public String institutionCodeLabel = "証券会社コード";
    
    /**
     * (部店コードラベル)<BR>
     * 部店コードラベル
     */
    public String branchCodeLabel = "部店コード";
    
    /**
     * (顧客コードラベル)<BR>
     * 顧客コードラベル
     */
    public String accountCodeLabel = "顧客コード";
    
    /**
     * (識別コードラベル)<BR>
     * 識別コードラベル
     */
    public String requestNumberLabel = "識別コード";
    
    /**
     * (名前（姓）ラベル)<BR>
     * 名前（姓）ラベル
     */
    public String familyNameLabel = "名前（姓）";
    
    /**
     * (名前（名）ラベル)<BR>
     * 名前（名）ラベル
     */
    public String nameLabel = "名前（名）";
    
    /**
     * (メールアドレスラベル)<BR>
     * メールアドレスラベル
     */
    public String mailAddressLabel = "メールアドレス";
    
    /**
     * (外国株式口座番号ラベル)<BR>
     * 外国株式口座番号ラベル
     */
    public String feqAccountCodeLabel = "外国株式口座番号";
    
    /**
     * (口座開設状況区分ラベル)<BR>
     * 口座開設状況区分ラベル
     */
    public String accountOpenStatusDivLabel = "口座開設状況区分";
    
    /**
     * (送受信区分ラベル)<BR>
     * 送受信区分ラベル
     */
    public String sendRcvDivLabel = "送受信区分";
    
    /**
     * (受付結果コードラベル)<BR>
     * 受付結果コードラベル
     */
    public String resultCodeLabel = "受付結果コード";
    
    /**
     * (エラー理由コードラベル)<BR>
     * エラー理由コードラベル
     */
    public String errorReasonCodeLabel = "エラー理由コード";
    
    /**
     * (更新者コードラベル)<BR>
     * 更新者コードラベル
     */
    public String lastUpdaterLabel = "更新者コード";
    
    /**
     * (作成日付ラベル)<BR>
     * 作成日付ラベル
     */
    public String createdTimestampLabel = "作成日付";
    
    /**
     * (更新日付ラベル)<BR>
     * 更新日付ラベル
     */
    public String lastUpdatedTimestampLabel = "更新日付";
    
    /**
     * (外株口座開設管理一覧CSV)<BR>
     * コンストラクタ<BR>
     * <BR>
     * インスタンスを生成し、ヘッダ情報をセットする。<BR>
     * 　@－super()にてインスタンスを生成する。<BR>
     * 　@－createカラムヘッダ()をコールし、カラムヘッダ情報を作成する。
     * @@roseuid 41F9DD3200F0
     */
    public WEB3AdminFEqConAccountOpenMngListCsv() 
    {
        super();
        
        this.createColumnHeader();
    }
    
    /**
     * (createカラムヘッダ)<BR>
     * カラムヘッダをセットする。<BR>
     * <BR>
     * 　@以下の通りCSVカラムモデルの配列を生成し、setカラムヘッダ()にてインスタンスにセットする。<BR> 
     * <BR>
     * [カラムヘッダ配列] <BR>
     * <BR>
     * －　@index = 0 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@外株口座開設管理一覧CSV.証券会社コードラベル <BR>
     * 　@カラム番号： 0 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 1 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@外株口座開設管理一覧CSV.部店コードラベル <BR>
     * 　@カラム番号： 1 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 2 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@外株口座開設管理一覧CSV.顧客コードラベル <BR>
     * 　@カラム番号： 2 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 3 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@外株口座開設管理一覧CSV.識別コードラベル <BR>
     * 　@カラム番号： 3 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 4 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@外株口座開設管理一覧CSV.名前（姓）ラベル <BR>
     * 　@カラム番号： 4 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 5 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@外株口座開設管理一覧CSV.名前（名）ラベル <BR>
     * 　@カラム番号： 5 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 6 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@外株口座開設管理一覧CSV.メールアドレスラベル <BR>
     * 　@カラム番号： 6 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 7 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@外株口座開設管理一覧CSV.外国株式口座番号ラベル <BR>
     * 　@カラム番号： 7 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 8 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@外株口座開設管理一覧CSV.口座開設状況区分ラベル <BR>
     * 　@カラム番号： 8 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 9 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@外株口座開設管理一覧CSV.送受信区分ラベル <BR>
     * 　@カラム番号： 9 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 10 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@外株口座開設管理一覧CSV.受付結果コードラベル <BR>
     * 　@カラム番号： 10 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 11 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@外株口座開設管理一覧CSV.エラー理由コードラベル <BR>
     * 　@カラム番号： 11 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 12 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@外株口座開設管理一覧CSV.更新者コードラベル <BR>
     * 　@カラム番号： 12 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 13 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@外株口座開設管理一覧CSV.作成日付ラベル 　@カラム番号： 13 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_日付時間 <BR>
     * 　@日付フォーマット：　@new SimpleDateFormat("yyyy/MM/dd HH:mm:ss") <BR>
     * <BR>
     * －　@index = 14 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@外株口座開設管理一覧CSV.更新日付ラベル <BR>
     * 　@カラム番号： 14 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_日付時間 <BR>
     * 　@日付フォーマット：　@new SimpleDateFormat("yyyy/MM/dd HH:mm:ss") <BR>
     * <BR>
     * @@roseuid 41F9DC220024
     */
    private void createColumnHeader() 
    {
        List l_lisLabels = new Vector();
        
        l_lisLabels.add(this.institutionCodeLabel);
        l_lisLabels.add(this.branchCodeLabel);
        l_lisLabels.add(this.accountCodeLabel);
        l_lisLabels.add(this.requestNumberLabel);
        l_lisLabels.add(this.familyNameLabel);
        l_lisLabels.add(this.nameLabel);
        l_lisLabels.add(this.mailAddressLabel);
        l_lisLabels.add(this.feqAccountCodeLabel);
        l_lisLabels.add(this.accountOpenStatusDivLabel);
        l_lisLabels.add(this.sendRcvDivLabel);
        l_lisLabels.add(this.resultCodeLabel);
        l_lisLabels.add(this.errorReasonCodeLabel);
        l_lisLabels.add(this.lastUpdaterLabel);
        l_lisLabels.add(this.createdTimestampLabel);
        l_lisLabels.add(this.lastUpdatedTimestampLabel);
        
        //以下の通りCSVカラムモデルの配列を生成し
        WEB3GentradeCsvColumnModel[] l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel[l_lisLabels.size()];
        for (int i = 0; i < l_lisLabels.size(); i ++) 
        {
            //カラムヘッダ配列           
            if (l_lisLabels.get(i).equals(this.createdTimestampLabel) || 
                    l_lisLabels.get(i).equals(this.lastUpdatedTimestampLabel))
            {
                l_gentradeCsvColumnModel[i] = 
                    new WEB3GentradeCsvColumnModel(
                        (String)l_lisLabels.get(i),
                        i,
                        WEB3GentradeCsvColumnModel.STRINGTYPE,
                        new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));
                continue;
            }
            l_gentradeCsvColumnModel[i] = 
                new WEB3GentradeCsvColumnModel(
                    (String)l_lisLabels.get(i),
                    i,
                    WEB3GentradeCsvColumnModel.STRINGTYPE,
                    null);
        }
        //setカラムヘッダ()にてインスタンスにセットする
        this.setColumnHeader(l_gentradeCsvColumnModel);
    }
    
    /**
     * (set証券会社コード)<BR>
     * 証券会社コードをセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： this.証券会社コードラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数.行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.値
     * @@param l_intRowId - 行番号
     * @@param l_strValue - CSVにセットする値
     * @@roseuid 41FF4D64031C
     */
    public void setInstitutionCode(int l_intRowId, String l_strValue) 
    {
        //１）カラムモデル取得
        //this.getカラムモデル()にて、CSVカラムモデルを取得する。
        //[引数]
        //項目ラベル： this.証券会社コードラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(this.institutionCodeLabel);

        //２）値セット
        //this.set項目値()にて、値をセットする。
        //[引数]
        //行番号： 引数.行番号
        //カラム： １）で取得したカラムモデル
        //値： 引数.値
        this.setValue(l_intRowId, l_gentradeCsvColumnModel, l_strValue);
    }
    
    /**
     * (set部店コード)<BR>
     * 部店コードをセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： this.部店コードラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数.行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.値
     * @@param l_intRowId - 行番号
     * 
     * @@param l_strValue - CSVにセットする値
     * @@roseuid 41FF4F9B0222
     */
    public void setBranchCode(int l_intRowId, String l_strValue) 
    {
        //１）カラムモデル取得
        //this.getカラムモデル()にて、CSVカラムモデルを取得する。
        //[引数]
        //項目ラベル： this.部店コードラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(this.branchCodeLabel);

        //２）値セット
        //this.set項目値()にて、値をセットする。
        //[引数]
        //行番号： 引数.行番号
        //カラム： １）で取得したカラムモデル
        //値： 引数.値 
        this.setValue(l_intRowId, l_gentradeCsvColumnModel, l_strValue);
    }
    
    /**
     * (set顧客コード)<BR>
     * 顧客コードをセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： this.顧客コードラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数.行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.値
     * @@param l_intRowId - 行番号
     * 
     * @@param l_strValue - CSVにセットする値
     * @@roseuid 41FF4F9C035A
     */
    public void setAccountCode(int l_intRowId, String l_strValue) 
    {
        //１）カラムモデル取得
        //this.getカラムモデル()にて、CSVカラムモデルを取得する。
        //[引数]
        //項目ラベル： this.顧客コードラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(this.accountCodeLabel);

        //２）値セット
        //this.set項目値()にて、値をセットする。
        //[引数]
        //行番号： 引数.行番号
        //カラム： １）で取得したカラムモデル
        //値： 引数.値 
        this.setValue(l_intRowId, l_gentradeCsvColumnModel, l_strValue);
    }
    
    /**
     * (set識別コード)<BR>
     * 識別コードをセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： this.識別コードラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数.行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.値
     * @@param l_intRowId - 行番号
     * 
     * @@param l_strValue - CSVにセットする値
     * @@roseuid 41FF4F9E0270
     */
    public void setRequestNumber(int l_intRowId, String l_strValue) 
    {
        //１）カラムモデル取得
        //this.getカラムモデル()にて、CSVカラムモデルを取得する。
        //[引数]
        //項目ラベル： this.識別コードラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(this.requestNumberLabel);

        //２）値セット
        //this.set項目値()にて、値をセットする。
        //[引数]
        //行番号： 引数.行番号
        //カラム： １）で取得したカラムモデル
        //値： 引数.値 
        this.setValue(l_intRowId, l_gentradeCsvColumnModel, l_strValue);
    }
    
    /**
     * (set名前（姓）)<BR>
     * 名前（姓）をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： this.名前（姓）ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数.行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.値
     * @@param l_intRowId - 行番号
     * 
     * @@param l_strValue - CSVにセットする値
     * @@roseuid 41FF5036036A
     */
    public void setFamilyName(int l_intRowId, String l_strValue) 
    {
        //１）カラムモデル取得
        //this.getカラムモデル()にて、CSVカラムモデルを取得する。
        //[引数]
        //項目ラベル： this.名前（姓）ラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(this.familyNameLabel);

        //２）値セット
        //this.set項目値()にて、値をセットする。
        //[引数]
        //行番号： 引数.行番号
        //カラム： １）で取得したカラムモデル
        //値： 引数.値 
        this.setValue(l_intRowId, l_gentradeCsvColumnModel, l_strValue);
    }
    
    /**
     * (set名前（名）)<BR>
     * 名前（名）をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： this.名前（名）ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数.行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.値
     * @@param l_intRowId - 行番号
     * 
     * @@param l_strValue - CSVにセットする値
     * @@roseuid 41FF50E500DA
     */
    public void setName(int l_intRowId, String l_strValue) 
    {
        //１）カラムモデル取得
        //this.getカラムモデル()にて、CSVカラムモデルを取得する。
        //[引数]
        //項目ラベル： this.名前（名）ラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(this.nameLabel);

        //２）値セット
        //this.set項目値()にて、値をセットする。
        //[引数]
        //行番号： 引数.行番号
        //カラム： １）で取得したカラムモデル
        //値： 引数.値 
        this.setValue(l_intRowId, l_gentradeCsvColumnModel, l_strValue);
    }
    
    /**
     * (setメールアドレス)<BR>
     * メールアドレスをセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： this.メールアドレスラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数.行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.値
     * @@param l_intRowId - 行番号
     * 
     * @@param l_strValue - CSVにセットする値
     * @@roseuid 41FF50F903C8
     */
    public void setMailAddress(int l_intRowId, String l_strValue) 
    {
        //１）カラムモデル取得
        //this.getカラムモデル()にて、CSVカラムモデルを取得する。
        //[引数]
        //項目ラベル： this.メールアドレスラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(this.mailAddressLabel);

        //２）値セット
        //this.set項目値()にて、値をセットする。
        //[引数]
        //行番号： 引数.行番号
        //カラム： １）で取得したカラムモデル
        //値： 引数.値 
        this.setValue(l_intRowId, l_gentradeCsvColumnModel, l_strValue);
    }
    
    /**
     * (set外国株式口座番号)<BR>
     * 外国株式口座番号をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： this.外国株式口座番号ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数.行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.値
     * @@param l_intRowId - 行番号
     * 
     * @@param l_strValue - CSVにセットする値
     * @@roseuid 41FF5117000F
     */
    public void setFeqAccountCode(int l_intRowId, String l_strValue) 
    {
        //１）カラムモデル取得
        //this.getカラムモデル()にて、CSVカラムモデルを取得する。
        //[引数]
        //項目ラベル： this.外国株式口座番号ラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(this.feqAccountCodeLabel);

        //２）値セット
        //this.set項目値()にて、値をセットする。
        //[引数]
        //行番号： 引数.行番号
        //カラム： １）で取得したカラムモデル
        //値： 引数.値 
        this.setValue(l_intRowId, l_gentradeCsvColumnModel, l_strValue);
    }
    
    /**
     * (set口座開設状況区分)<BR>
     * 口座開設状況区分をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： this.口座開設状況区分ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数.行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.値
     * @@param l_intRowId - 行番号
     * 
     * @@param l_strValue - CSVにセットする値
     * @@roseuid 41FF512D0251
     */
    public void setAccountOpenStatusDiv(int l_intRowId, String l_strValue) 
    {
        //１）カラムモデル取得
        //this.getカラムモデル()にて、CSVカラムモデルを取得する。
        //[引数]
        //項目ラベル： this.口座開設状況区分ラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(this.accountOpenStatusDivLabel);

        //２）値セット
        //this.set項目値()にて、値をセットする。
        //[引数]
        //行番号： 引数.行番号
        //カラム： １）で取得したカラムモデル
        //値： 引数.値 
        this.setValue(l_intRowId, l_gentradeCsvColumnModel, l_strValue);
    }
    
    /**
     * (set送受信区分)<BR>
     * 送受信区分をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： this.送受信区分ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数.行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.値
     * @@param l_intRowId - 行番号
     * 
     * @@param l_strValue - CSVにセットする値
     * @@roseuid 41FF514D03D7
     */
    public void setSendRcvDiv(int l_intRowId, String l_strValue) 
    {
        //１）カラムモデル取得
        //this.getカラムモデル()にて、CSVカラムモデルを取得する。
        //[引数]
        //項目ラベル： this.送受信区分ラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(this.sendRcvDivLabel);

        //２）値セット
        //this.set項目値()にて、値をセットする。
        //[引数]
        //行番号： 引数.行番号
        //カラム： １）で取得したカラムモデル
        //値： 引数.値 
        this.setValue(l_intRowId, l_gentradeCsvColumnModel, l_strValue);
    }
    
    /**
     * (set受付結果コード)<BR>
     * 受付結果コードをセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： this.受付結果コードラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数.行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.値
     * @@param l_intRowId - 行番号
     * 
     * @@param l_strValue - CSVにセットする値
     * @@roseuid 41FF516602AE
     */
    public void setResultCode(int l_intRowId, String l_strValue) 
    {
        //１）カラムモデル取得
        //this.getカラムモデル()にて、CSVカラムモデルを取得する。
        //[引数]
        //項目ラベル： this.受付結果コードラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(this.resultCodeLabel);

        //２）値セット
        //this.set項目値()にて、値をセットする。
        //[引数]
        //行番号： 引数.行番号
        //カラム： １）で取得したカラムモデル
        //値： 引数.値 
        this.setValue(l_intRowId, l_gentradeCsvColumnModel, l_strValue);
    }
    
    /**
     * (setエラー理由コード)<BR>
     * エラー理由コードをセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： this.エラー理由コードラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数.行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.値
     * @@param l_intRowId - 行番号
     * 
     * @@param l_strValue - CSVにセットする値
     * @@roseuid 41FF518603A8
     */
    public void setErrorReasonCode(int l_intRowId, String l_strValue) 
    {
        //１）カラムモデル取得
        //this.getカラムモデル()にて、CSVカラムモデルを取得する。
        //[引数]
        //項目ラベル： this.エラー理由コードラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(this.errorReasonCodeLabel);

        //２）値セット
        //this.set項目値()にて、値をセットする。
        //[引数]
        //行番号： 引数.行番号
        //カラム： １）で取得したカラムモデル
        //値： 引数.値 
        this.setValue(l_intRowId, l_gentradeCsvColumnModel, l_strValue);
    }
    
    /**
     * (set更新者コード)<BR>
     * 更新者コードをセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： this.更新者コードラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数.行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.値
     * @@param l_intRowId - 行番号
     * 
     * @@param l_strValue - CSVにセットする値
     * @@roseuid 41FF519B0176
     */
    public void setLastUpdater(int l_intRowId, String l_strValue) 
    {
        //１）カラムモデル取得
        //this.getカラムモデル()にて、CSVカラムモデルを取得する。
        //[引数]
        //項目ラベル： this.更新者コードラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(this.lastUpdaterLabel);

        //２）値セット
        //this.set項目値()にて、値をセットする。
        //[引数]
        //行番号： 引数.行番号
        //カラム： １）で取得したカラムモデル
        //値： 引数.値 
        this.setValue(l_intRowId, l_gentradeCsvColumnModel, l_strValue);
    }
    
    /**
     * (set作成日付)<BR>
     * 作成日付をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： this.作成日付ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数.行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.値
     * @@param l_intRowId - 行番号
     * 
     * @@param l_datValue - CSVにセットする値
     * @@roseuid 41FF51AE00DA
     */
    public void setCreatedTimestamp(int l_intRowId, Date l_strValue) 
    {
        //１）カラムモデル取得
        //this.getカラムモデル()にて、CSVカラムモデルを取得する。
        //[引数]
        //項目ラベル： this.作成日付ラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(this.createdTimestampLabel);

        //２）値セット
        //this.set項目値()にて、値をセットする。
        //[引数]
        //行番号： 引数.行番号
        //カラム： １）で取得したカラムモデル
        //値： 引数.値 
        this.setValue(l_intRowId, l_gentradeCsvColumnModel, l_strValue);
    }
    
    /**
     * (set更新日付)<BR>
     * 更新日付をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： this.更新日付ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数.行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.値
     * @@param l_intRowId - 行番号
     * 
     * @@param l_datValue - CSVにセットする値
     * @@roseuid 41FF51AE0186
     */
    public void setLastUpdatedTimestamp(int l_intRowId, Date l_datValue) 
    {
        //１）カラムモデル取得
        //this.getカラムモデル()にて、CSVカラムモデルを取得する。
        //[引数]
        //項目ラベル： this.更新日付ラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(this.lastUpdatedTimestampLabel);

        //２）値セット
        //this.set項目値()にて、値をセットする。
        //[引数]
        //行番号： 引数.行番号
        //カラム： １）で取得したカラムモデル
        //値： 引数.値 
        this.setValue(l_intRowId, l_gentradeCsvColumnModel, l_datValue);
    }
}
@
