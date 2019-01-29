head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.44.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoLotResultUploadCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 抽選結果アップロードCSVクラス(WEB3AdminIpoLotResultUploadCsv.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/26 李海波 (中訊) 新規作成
Revesion History : 2004/12/27 坂上(SRA) 残対応>>>038
Revesion History : 2005/01/05 坂上(SRA) 残対応>>>051
Revesion History : 2005/01/07 坂上(SRA) 残対応>>>071,066
Revesion History : 2007/04/19 柴双紅(中訊) モデルNo.171
Revision History : 2007/07/20 趙林鵬(中訊) 実装の問題015
*/

package webbroker3.ipo;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3LotResultRetryDef;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvUploadDataModel;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.ipo.data.IpoOrderParams;
import webbroker3.ipo.data.IpoProductParams;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;


/**
 * (抽選結果アップロードCSV)<BR>
 * コンストラクタ。<BR>
 * <BR>
 * インスタンスを生成し、IPO銘柄をプロパティにセットする。
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AdminIpoLotResultUploadCsv extends WEB3GentradeCsvUploadDataModel 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIpoLotResultUploadCsv.class);
    
    /**
     * 定数定義プロパティ　@”部店コード”
     */
    public static final String BRANCH_CODE_LABEL = "部店コード";
    
    /**
     * 定数定義プロパティ　@”顧客コード”
     */
    public static final String ACCOUNT_CODE_LABEL = "顧客コード";
    
    /**
     * 定数定義プロパティ　@”顧客名”
     */
    public static final String ACCOUNT_NAME_LABEL = "顧客名";
    
    /**
     * 定数定義プロパティ　@”抽選結果”
     */
    public static final String LOT_RESULT_LABEL = "抽選結果";
    
    /**
     * 定数定義プロパティ　@”当選数量”
     */
    public static final String ELECTED_QUANTITY_LABEL = "当選数量";
    
    /**
     * 定数定義プロパティ　@”優先順位”
     */
    public static final String SUBSTITUTE_PRIORITY_LABEL = "優先順位";
    
    /**
     * 定数定義プロパティ　@新規／繰上抽選種別_新規抽選
     */
    public static final String NEW_ADVANCE_LOT_TYPE_NEW_LOT = "1";
    
    /**
     * 定数定義プロパティ　@新規／繰上抽選種別_繰上抽選
     */
    public static final String NEW_ADVANCE_LOT_TYPE_ADVANCE_LOT = "2";
    
    /**
     * 定数定義プロパティ　@抽選結果当選表示
     */
    public static final String LOT_RESULT_PRIZED = "1";
    
    /**
     * 定数定義プロパティ　@抽選結果補欠表示
     */
    public static final String LOT_RESULT_WAITING = "2";
    
    /**
     * 定数定義プロパティ　@抽選結果落選表示
     */
    public static final String LOT_RESULT_REJECTED = "3";
    
    /**
     * アップロードファ@イルＩＤ_抽選結果アップロード<BR>
     * <BR>
     * ※（管理者共通）アップロードテーブル.アップロードファ@イルＩＤに格納する文字列
     */
    public String uploadFileId = "IPO抽選結果アップロード";
    
    /**
     * IPO銘柄
     */
    private WEB3IpoProductImpl ipoProduct;
    
    /**
     * 新規／繰上抽選種別<BR>
     * <BR>
     * 1：　@新規抽選<BR>
     * 2：　@繰上抽選
     */
    private String newAdvanceLotType;
    
    /**
     * 当選数量合計値
     */
    private double electedQuantityTotal = 0;
    
    /**
     * 割当確定数量合計値<BR>
     * <BR>
     * ※繰上抽選の場合に使用
     */
    private double allotQuantityTotal = 0;
    
    /**
     * コンストラクタ<BR>
     * ※　@アップロード中止の場合に使用する。<BR>
     * <BR>
     * －引数のアップロードIDをプロパティにセットする。
     * @@param l_lngUploadId - アップロードID<BR>
     * <BR>
     * ※　@（管理者共通）アップロードテーブルのPK
     * @@return webbroker3.ipo.WEB3AdminIpoLotResultUploadCsv
     * @@roseuid 40F77E150316
     */
    public WEB3AdminIpoLotResultUploadCsv(long l_lngUploadId) 
    {
        
        final String STR_METHOD_NAME = " WEB3AdminIpoLotResultUploadCsv(long)";
        log.entering(STR_METHOD_NAME);
        
        //引数のアップロードIDをプロパティにセットする
        this.administratorUploadId = l_lngUploadId;
        
        log.exiting(STR_METHOD_NAME);
     
    }
    
    /**
     * コンストラクタ<BR>
     * <BR>
     * －引数のIPO銘柄をプロパティにセットする。<BR>
     * －this.createカラムヘッダ()をコールする。
     * @@param l_ipoProduct - IPO銘柄オブジェクト
     * 
     * @@return webbroker3.ipo.WEB3AdminIPOLotResultUploadCSV
     * @@roseuid 40F228DE0272
     */
    public WEB3AdminIpoLotResultUploadCsv(WEB3IpoProductImpl l_ipoProduct) 
    {
        
        final String STR_METHOD_NAME = " WEB3AdminIpoLotResultUploadCsv(WEB3IpoProductImpl)";
        log.entering(STR_METHOD_NAME);
        
        //引数のIPO銘柄をプロパティにセットする
        this.ipoProduct = l_ipoProduct;
        //this.createカラムヘッダ()をコールする
        this.createColumnHeader();
        
        log.exiting(STR_METHOD_NAME);
     
    }
    
    /**
     * (getアップロードファ@イルＩＤ)<BR>
     * 抽選結果アップロードCSV.アップロードファ@イルＩＤを返却する。
     * @@return String
     * @@roseuid 40F228FA031E
     */
    public String getUploadFileId() 
    {

        //抽選結果アップロードCSV.アップロードファ@イルＩＤを返却する
        return this.uploadFileId;
        
    }
    
    /**
     * (get銘柄タイプ)<BR>
     * 銘柄タイプを返却する。<BR>
     * <BR>
     * ProductTypeEnum.IPOを返却する。
     * @@return ProductTypeEnum
     * @@roseuid 40F38E52003D
     */
    public ProductTypeEnum getProductType() 
    {
        
        //ProductTypeEnum.IPOを返却する
        return ProductTypeEnum.IPO;
        
    }
    
    /**
     * (getアップロード履歴)<BR>
     * 当該アップロードファ@イルに関連するアップロード履歴をすべて取得する。<BR>
     * <BR>
     * this.getアップロード履歴()（overloadメソッド）をコールし、戻り値を返却する。<BR>
     * <BR>
     * [getアップロード履歴()に指定する引数]<BR>
     * データキー：　@this.IPO銘柄().getIPO銘柄ＩＤ()<BR>
     * @@return AdministratorUploadRow
     * @@roseuid 40F38FA50147
     */
    public AdministratorUploadRow[] getUploadActions() throws WEB3BaseException
    {
        
        //this.getアップロード履歴()（overloadメソッド）をコールし、戻り値を返却する
        long l_lngProductId = this.getIpoProduct().getProductId();
        return this.getUploadAction(l_lngProductId);
        
    }
    
    /**
     * (get最新アップロード履歴)<BR>
     * 当該アップロードファ@イルに関連する直近のアップロード履歴を取得する。<BR>
     * <BR>
     * this.get最新アップロード履歴()（overloadメソッド）をコールし、戻り値を返却する。<BR>
     * <BR>
     * [getアップロード履歴()に指定する引数]<BR>
     * データキー：　@this.IPO銘柄().getIPO銘柄ＩＤ()
     * @@return AdministratorUploadRow
     * @@roseuid 40F38FAB0137
     */
    public AdministratorUploadRow getLatestUploadAction() throws WEB3BaseException
    {
        
        long l_lngProductId = this.getIpoProduct().getProductId();
        //this.get最新アップロード履歴()（overloadメソッド）をコールし、戻り値を返却する
        return this.getLatestUploadAction(l_lngProductId);

    }
    
    /**
     * カラムヘッダをセットする。<BR>
     * <BR>
     * 　@以下の通りCSVカラムモデルの配列を生成し、setカラムヘッダ()にてインスタンスに<BR>セットする。<BR>
     * <BR>
     * [カラムヘッダ配列]<BR>
     * <BR>
     * －　@index = 0　@※部店コード<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@抽選結果アップロードCSV.部店コードラベル<BR>
     * 　@カラム番号： 0<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 1　@※顧客コード<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@抽選結果アップロードCSV.顧客コードラベル<BR>
     * 　@カラム番号： 1<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 2　@※顧客名<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@抽選結果アップロードCSV.顧客名ラベル<BR>
     * 　@カラム番号： 2<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 3　@※抽選結果<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@抽選結果アップロードCSV.抽選結果ラベル<BR>
     * 　@カラム番号： 3<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 4　@※当選数量<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@抽選結果アップロードCSV.当選数量ラベル<BR>
     * 　@カラム番号： 4<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_数値（double）<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 5　@※優先順位<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@抽選結果アップロードCSV.優先順位ラベル<BR>
     * 　@カラム番号： 5<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_数値（long）<BR>
     * 　@日付フォーマット：　@null
     * @@roseuid 40F3D51D03A3
     */
    private void createColumnHeader() 
    {
        
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME);
        
        WEB3GentradeCsvColumnModel[] columnHeader = new WEB3GentradeCsvColumnModel[6];
        columnHeader[0] = new WEB3GentradeCsvColumnModel(BRANCH_CODE_LABEL, 0, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        columnHeader[1] = new WEB3GentradeCsvColumnModel(ACCOUNT_CODE_LABEL, 1, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        columnHeader[2] = new WEB3GentradeCsvColumnModel(ACCOUNT_NAME_LABEL, 2, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        columnHeader[3] = new WEB3GentradeCsvColumnModel(LOT_RESULT_LABEL, 3, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        columnHeader[4] = new WEB3GentradeCsvColumnModel(ELECTED_QUANTITY_LABEL, 4, WEB3GentradeCsvColumnModel.DOUBLETYPE, null);
        columnHeader[5] = new WEB3GentradeCsvColumnModel(SUBSTITUTE_PRIORITY_LABEL, 5, WEB3GentradeCsvColumnModel.LONGTYPE, null);
        
        setColumnHeader(columnHeader);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * キーヘッダ文字列の妥当性をチェックし、内容をプロパティにセットする。<BR>
     * <BR>
     * １）　@キーヘッダ指定文字列解析<BR>
     * 　@キーヘッダ.split(抽選結果アップロードCSV.区切り文字)にて、区切り文字ごと<BR>のtoken[]に分割する。<BR>
     * 　@tokenの要素数が2でない場合（token.length != 2）、ヘッダのフォーマットが<BR>違うと判定し例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00522<BR>
     * ２）　@値取得<BR>
     * 　@２－１）　@銘柄コードチェック<BR>
     * 　@　@token[0]が正しい銘柄コードでない場合<BR>
     * 　@　@（token[0] != this.IPO銘柄.IPO銘柄行.銘柄コード）、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00523<BR>
     * <BR>
     * 　@２－２）　@新規／抽選区分チェック<BR>
     * 　@　@－token[1]が正しい新規／抽選区分でない場合<BR>
     * 　@　@（token[1] != 抽選結果アップロードCSV.新規／繰上抽選種別_新規抽選 &&<BR>
     * 　@　@ token[1] != 抽選結果アップロードCSV.新規／繰上抽選種別_繰上抽選）、<BR>
     * 　@　@例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00524<BR>
     * 　@　@－token[1]が引数の抽選区分と一致しない場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01998<BR>
     * <BR>
     * ３）　@キーヘッダセット<BR>
     * 　@this.setキーヘッダ()にてキーヘッダをセットする。<BR>
     * <BR>
     * 　@[setキーヘッダ()に指定する引数]<BR>
     * 　@キーヘッダ：　@token[]<BR>
     * <BR>
     * ４）　@新規／繰上抽選種別セット<BR>
     * 　@this.新規／繰上抽選種別にtoken[1]をセットする。<BR>
     * <BR>
     * @@see String#split<BR>
     * @@param l_strKeyHeaderLine - キーヘッダ行<BR>
     * @@param l_strLotDiv 抽選区分<BR>
     * <BR>
     * "銘柄コード,新規／繰上抽選結果種別"
     * @@roseuid 40F4883D03AE
     */
    public void validateKeyHeader(String l_strKeyHeaderLine, String l_strLotDiv) throws WEB3BaseException
    {
    
        final String STR_METHOD_NAME = " validateKeyHeader(String, String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strKeyHeaderLine == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //１）　@キーヘッダ指定文字列解析
        String[] l_tokens = l_strKeyHeaderLine.split(regex);
        if(l_tokens.length != 2)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00522,
                getClass().getName() + STR_METHOD_NAME);
            
        }
        //２）　@値取得
        //２－１）　@銘柄コードチェック
        IpoProductParams l_ipoProductParams = (IpoProductParams)this.getIpoProduct().getDataSourceObject();
        //2004/11/30 U00492 銘柄コードではなく、IPO銘柄コードの取得に修正　@坂上@@SRA  START
//		String l_strProductCode = String.valueOf(l_ipoProductParams.getIpoProductId());
		String l_strProductCode = l_ipoProductParams.getProductCode();
		//2004/11/30 U00492 銘柄コードではなく、IPO銘柄コードの取得に修正　@坂上@@SRA  END
        if(!l_strProductCode.equals(l_tokens[0]))
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00523,
                getClass().getName() + STR_METHOD_NAME);
            
        }
        //２－２）　@新規／抽選区分チェック
        if(!NEW_ADVANCE_LOT_TYPE_NEW_LOT.equals(l_tokens[1]) && !NEW_ADVANCE_LOT_TYPE_ADVANCE_LOT.equals(l_tokens[1]))
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00524,
                getClass().getName() + STR_METHOD_NAME);            
            
        }
        if ((l_strLotDiv == null && l_tokens[1] != null)
            || (l_strLotDiv != null && l_tokens[1] == null) 
            || (l_strLotDiv != null && !l_strLotDiv.equals(l_tokens[1])))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01998,
                getClass().getName() + STR_METHOD_NAME);            
        }
        //３）　@キーヘッダセット
        this.setKeyHeader(l_tokens);
        //４）　@新規／繰上抽選種別セット
        this.newAdvanceLotType = l_tokens[1];
        
        log.exiting(STR_METHOD_NAME);
        
    }

    /**
     * (set顧客)<BR>
     * 顧客コード（checkDigit付き）をセットし直す。 <BR>
     * １）　@（顧客コード）カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR> 
     * 　@[getカラムモデル()に指定する引数] <BR>
     * 　@項目ラベル：　@抽選結果アップロードCSV.顧客コードラベル <BR>
     * <BR>
     * ２）　@値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * 　@[set項目値()に指定する引数] <BR>
     * 　@行番号：　@引数の行番号 <BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR> 
     * 　@値：　@顧客.getAccountCode() <BR>
     * <BR>
     * <BR>
     * @@param l_intLineNumber - 行番号を指定する。
     * @@param l_lngAccountId - 口座ＩＤ
     * @@roseuid XXXXXXXXXXX
     */
    public void setAccount(int l_intLineNumber, long l_lngAccountId) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " setAccount(int, long)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //顧客オブジェクト取得
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            MainAccount l_mainAccount= l_finApp.getAccountManager().getMainAccount(l_lngAccountId); //throw NotFoundException
            
            MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
            
            //（顧客コード）カラムモデル取得
            WEB3GentradeCsvColumnModel l_csvDataModelAccountCode = this.getColumnModel(ACCOUNT_CODE_LABEL);
            
            //（顧客コード）値セット
            String l_strCode= l_mainAccount.getAccountCode();            
            this.setValue(l_intLineNumber, l_csvDataModelAccountCode, l_strCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    
    /**
     * (is新規抽選)<BR>
     * 新規抽選かを判定する。<BR>
     * <BR>
     * 　@－（this.新規／繰上抽選種別 == 抽選結果アップロード<BR>
     * CSV.新規／繰上抽選種別_新規抽選）の場合、trueを返却する。<BR>
     * 　@－以外、falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 40F489430005
     */
    public boolean isNewLot() 
    {

        //新規抽選かを判定する
        return NEW_ADVANCE_LOT_TYPE_NEW_LOT.equals(this.newAdvanceLotType);
        
    }
    
    /**
     * 当選数量合計値を取得する。<BR>
     * <BR>
     * this.当選数量合計値を返却する。
     * @@return double
     * @@roseuid 40F4A31700A1
     */
    public double getElectedQuantityTotal() 
    {

        //当選数量合計値を取得する        
        return this.electedQuantityTotal;
     
    }
    
    /**
     * 割当確定数量合計値を取得する。<BR>
     * <BR>
     * this.割当確定数量合計値を返却する。
     * @@return double
     * @@roseuid 40F605070205
     */
    public double getAllotQuantityTotal() 
    {

        //割当確定数量合計値を取得する        
        return this.allotQuantityTotal;
        
    }
    
    /**
     * 当選数量を合計値に加算する。<BR>
     * <BR>
     * this.当選数量合計値 = this.当選数量合計値 + 当選数量
     * @@param l_dblElectedQuantity - 当選数量
     * @@roseuid 40F4A3340321
     */
    public void addElectedQuantity(double l_dblElectedQuantity) 
    {

        //当選数量を合計値に加算する
        this.electedQuantityTotal = this.electedQuantityTotal + l_dblElectedQuantity;
     
    }
    
    /**
     * 繰上待ち数量を含む、割当確定数量を割当確定数量合計値に加算する。<BR>
     * <BR>
     * １）　@IPO申告の割当確定数量を取得。<BR>
     * 　@○ 当選者で購入申込済の場合(*1)<BR>
     * 　@　@－IPO申告.IPO申告行.購入申込数量。<BR>
     * <BR>
     * 　@○ 当選者で購入申込未済の場合(*2)　@※繰上待ち数量<BR>
     * 　@　@－IPO申告.IPO申告行.当選数量。<BR>
     * <BR>
     * 　@○ 補欠者で補欠当選済の場合(*3)、<BR>
     * 　@　@－IPO申告.IPO申告行.当選数量。<BR>
     * <BR>
     * 　@○ 以外の場合、0。<BR>
     * <BR>
     * 　@(*1) 当選者で購入申込済の判定<BR>
     * 　@　@IPO申告.is当選者() == true && IPO申告.is購入申込() == true<BR>
     * 　@(*2) 当選者で購入申込未済の判定<BR>
     * 　@　@IPO申告.is当選者() == true && IPO申告.is購入申込() == false &&<BR>
     * 　@　@　@IPO申告.is辞退() == false && IPO銘柄.is購入申込終了（当社設定）() <BR>== false<BR>
     * 　@(*3) 補欠者で補欠当選の判定<BR>
     * 　@　@IPO申告.is補欠者() == true && IPO申告.IPO申告行.抽選結果（繰上） == <BR>1：当選<BR>
     * <BR>
     * ２）　@割当確定数量加算<BR>
     * <BR>
     * 　@this.割当確定数量合計値 = this.割当確定数量合計値 + （１）で取得した<BR>割当確定数量）<BR>
     * <BR>
     * @@param l_ipoOrder - IPO申告オブジェクト
     * @@param l_ipoProduct - IPO銘柄オブジェクト
     * @@roseuid 40F5FF8801C7
     */
    public void addAllotQuantity(WEB3IpoOrderImpl l_ipoOrder, WEB3IpoProductImpl l_ipoProduct) 
    {
        
        final String STR_METHOD_NAME = " addAllotQuantity(WEB3IpoOrderImpl, WEB3IPOProductImpl )";
        log.entering(STR_METHOD_NAME);
        
        if (l_ipoOrder == null || l_ipoProduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //IPO申告の割当確定数量を取得。
        IpoOrderParams l_ipoOrderParams = (IpoOrderParams)l_ipoOrder.getDataSourceObject();
        //割当確定数量
        double l_dblApplicationQuantity = 0;
        //(*1) 当選者で購入申込済の判定
        if(l_ipoOrder.isElected() && l_ipoOrder.isOffered())
        {
            
            if(l_ipoOrderParams.getApplicationQuantityIsNull())
            {
                
                l_ipoOrderParams.setApplicationQuantity(0); 
                
            }
            else
            {
                
                l_dblApplicationQuantity = l_ipoOrderParams.getApplicationQuantity();
                
            }
            
            
        }
        //(*2) 当選者で購入申込未済の判定
        else if(l_ipoOrder.isElected() && !l_ipoOrder.isOffered() 
            && !l_ipoOrder.isDecline() && !l_ipoProduct.isOfferEnd())
        {
            
            l_dblApplicationQuantity = l_ipoOrderParams.getElectedQuantity();
            
        }
        //(*3) 補欠者で補欠当選の判定
        else if(l_ipoOrder.isWaiting() && WEB3LotResultRetryDef.ELECTION.equals(l_ipoOrderParams.getLotResultRetry()))
        {
            
            l_dblApplicationQuantity = l_ipoOrderParams.getElectedQuantity();
            
        }
        //以外の場合、0
        else
        {
            
            l_dblApplicationQuantity = 0;
            
        }
        //２）　@割当確定数量加算
        this.allotQuantityTotal = allotQuantityTotal + l_dblApplicationQuantity;
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 行番号に対応する明細行の部店コードを取得する。<BR>
     * <BR>
     * this.get項目値()にて部店コードを取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(抽選結果アップロードCSV.部店コードラベル)の戻り値。<BR>
     * @@param l_intLineNo - 行番号
     * @@return String
     * @@roseuid 40F4FC2601FD
     */
    public String getBranchCode(int l_intLineNo) 
    {
        
        final String STR_METHOD_NAME = " getBranchCode(int)";
        log.entering(STR_METHOD_NAME);
        //行番号に対応する明細行の部店コードを取得する
        WEB3GentradeCsvColumnModel l_columnModel = this.getColumnModel(BRANCH_CODE_LABEL);
        String l_strValue = (String)this.getValue(l_intLineNo, l_columnModel);
        
        log.exiting(STR_METHOD_NAME);
        return l_strValue;
        
    }
    
    /**
     * (get顧客コード)<BR>
     * 行番号に対応する明細行の顧客コードを取得する。<BR>
     * <BR>
     * this.get項目値()にて顧客コードを取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(抽選結果アップロードCSV.顧客コードラベル)の戻り値。
     * @@param l_intLineNo - 行番号
     * @@return String
     * @@roseuid 40F505C90393
     */
    public String getAccountCode(int l_intLineNo) 
    {

        final String STR_METHOD_NAME = " getAccountCode(int)";
        log.entering(STR_METHOD_NAME);
        //行番号に対応する明細行の顧客コードを取得する
        WEB3GentradeCsvColumnModel l_columnModel = this.getColumnModel(ACCOUNT_CODE_LABEL);
        String l_strValue = (String)this.getValue(l_intLineNo, l_columnModel);
        
        log.exiting(STR_METHOD_NAME);
        return l_strValue;

    }
    
    /**
     * (get抽選結果)<BR>
     * 行番号に対応する明細行の抽選結果を取得する。<BR>
     * <BR>
     * this.get項目値()にて抽選結果を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(抽選結果アップロードCSV.抽選結果ラベル)の戻り値。
     * @@param l_intLineNo - 行番号
     * @@return String
     * @@roseuid 40F50600026A
     */
    public String getLotResult(int l_intLineNo) 
    {

        final String STR_METHOD_NAME = " getLotResult(int)";
        log.entering(STR_METHOD_NAME);
        //行番号に対応する明細行の抽選結果を取得する
        WEB3GentradeCsvColumnModel l_columnModel = this.getColumnModel(LOT_RESULT_LABEL);
        String l_strValue = (String)this.getValue(l_intLineNo, l_columnModel);
        
        log.exiting(STR_METHOD_NAME);
        return l_strValue;

    }
    
    /**
     * (get当選数量)<BR>
     * 行番号に対応する明細行の当選数量を取得する。<BR>
     * <BR>
     * this.get項目値()にて当選数量を取得し、標準データ型（double）に変換して返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(抽選結果アップロードCSV.当選数量ラベル)の戻り値。
     * @@param l_intLineNo - 行番号
     * @@return double
     * @@roseuid 40F5061900F3
     */
    public double getElectedQuantity(int l_intLineNo) 
    {

        final String STR_METHOD_NAME = " getElectedQuantity(int)";
        log.entering(STR_METHOD_NAME);
        //行番号に対応する明細行の当選数量を取得する
        WEB3GentradeCsvColumnModel l_columnModel = this.getColumnModel(ELECTED_QUANTITY_LABEL);
        Double l_value = (Double)this.getValue(l_intLineNo, l_columnModel);
		// 2004/12/2 U00509 NaNではなく、0をセットする　@坂上@@SRA  START
        double l_dblValue = 0.0D;
//		double l_dblValue = 0.0D / 0.0D;
		if(l_value != null)
		// 2004/12/2 U00509 NaNではなく、0をセットする　@坂上@@SRA  END
        {
            l_dblValue = l_value.doubleValue();
        }
        return l_dblValue;

    }
    
    /**
     * (get優先順位)<BR>
     * 行番号に対応する明細行の優先順位を取得する。<BR>
     * <BR>
     * this.get項目値()にて優先順位を取得し、標準データ型（long）に変換して返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(抽選結果アップロードCSV.優先順位ラベル)の戻り値。
     * @@param l_intLineNo - 行番号
     * @@return Long
     * @@roseuid 40F506570086
     */
    public Long getSubstitutePriority(int l_intLineNo) 
    {

        final String STR_METHOD_NAME = " getSubstitutePriority(int)";
        log.entering(STR_METHOD_NAME);
        //行番号に対応する明細行の当選数量を取得する
        WEB3GentradeCsvColumnModel l_columnModel = this.getColumnModel(SUBSTITUTE_PRIORITY_LABEL);
        Long l_lngValueL = (Long)this.getValue(l_intLineNo, l_columnModel);
        
//        long l_lngValue = 0;
//        if(l_lngValueL != null)
//        {
//            l_lngValue = l_lngValueL.longValue();
//        }

        return l_lngValueL;

    }
    
    /**
     * this.IPO銘柄を返却する。
     * @@return webbroker3.ipo.WEB3IpoProductImpl
     * @@roseuid 40F5070B01AF
     */
    public WEB3IpoProductImpl getIpoProduct() 
    {

        //this.IPO銘柄を返却する
        return this.ipoProduct;
     
    }
    
    /**
     * (validate新規抽選抽選結果)<BR>
     * 抽選結果の値が正しいかチェックを行う。<BR>
     * ※　@新規抽選の場合<BR>
     * <BR>
     * 以下の条件に当てはまる場合、コード値不正として例外をスローする。<BR>
     * <BR>
     *   [Error条件]<BR>
     *   （抽選結果 != 抽選結果アップロードCSV.抽選結果_当選） and<BR>
     *   （抽選結果 != 抽選結果アップロードCSV.抽選結果_補欠）<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00519<BR>
     * @@param l_strLotResult - 抽選結果
     * @@roseuid 40F50BCB0160
     */
    public void validateNewLotLotResult(String l_strLotResult) throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " validateNewLotLotResult(String)";
        log.entering(STR_METHOD_NAME);
        
        //以下の条件に当てはまる場合、コード値不正として例外をスローする
        //Q&A:WEB3-IPO-A-FT-0058
        if(!LOT_RESULT_PRIZED.equals(l_strLotResult) && !LOT_RESULT_WAITING.equals(l_strLotResult))
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00519,
                getClass().getName() + STR_METHOD_NAME);
            
        }
        
        log.exiting(STR_METHOD_NAME);
        
    }
    
    /**
     * (validate繰上抽選抽選結果)<BR>
     * 抽選結果の値が正しいかチェックを行う。<BR>
     * ※　@繰上抽選の場合<BR>
     * <BR>
     * １）　@抽選結果の値チェック<BR>
     * 　@以下の条件に当てはまる場合、コード値不正として例外をスローする。<BR>
     * <BR>
     * 　@[Error条件]<BR>
     * 　@（抽選結果 != 抽選結果アップロードCSV.抽選結果_当選） And<BR>
     * 　@（抽選結果 != 抽選結果アップロードCSV.抽選結果_落選）<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00520<BR>
     * <BR>
     * ２）　@当選数量／抽選結果の関連チェック<BR>
     * 　@以下の条件に当てはまる場合、補欠落選者に当選数量が発生していると<BR>判定し、例外をスローする。<BR>
     * <BR>
     * 　@[Error条件]<BR>
     * 　@（抽選結果 == 抽選結果アップロードCSV.抽選結果_落選） &&<BR>
     * 　@（当選数量 != 0）<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00521<BR>
     * <BR>
     * @@param l_strLotResult - 抽選結果
     * @@param l_dblPrizeQuantity - 当選数量
     * @@roseuid 40F50DC50364
     */
    public void validateAdvanceLotLotResult(String l_strLotResult, double l_dblPrizeQuantity) throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " validateAdvanceLotLotResult(String, double)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@抽選結果の値チェック
        if(!LOT_RESULT_PRIZED.equals(l_strLotResult) && !LOT_RESULT_REJECTED.equals(l_strLotResult))
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00520,
                getClass().getName() + STR_METHOD_NAME);
            
        }
        //２）　@当選数量／抽選結果の関連チェック
        if(LOT_RESULT_REJECTED.equals(l_strLotResult) && l_dblPrizeQuantity != 0)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00521,
                getClass().getName() + STR_METHOD_NAME);
            
        }        
        log.exiting(STR_METHOD_NAME);
             
    }
    
    /**
     * (validate優先順位)<BR>
     * 優先順位のチェックを行う。<BR>
     * 以下の条件に当てはまる場合、例外をスローする。<BR>
     * <BR>
     * [Error条件]<BR>
     * （抽選結果 == 抽選結果アップロードCSV.抽選結果_当選） &&<BR>
     * （優先順位 != 0）<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00516<BR>
     * @@param l_strLotResult - 抽選結果
     * @@param l_lngSubstitutePriority - 優先順位
     * @@roseuid 40F50EBB0112
     */
    public void validateSubstitutePriority(String l_strLotResult, Long l_lngSubstitutePriority) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " validateSubstitutePriority(String, long)";
        log.entering(STR_METHOD_NAME);
        
        if(l_lngSubstitutePriority == null)
        {
            l_lngSubstitutePriority = new Long(0);
        }
        
        //優先順位のチェックを行う
        if(LOT_RESULT_PRIZED.equals(l_strLotResult) && !l_lngSubstitutePriority.equals(new Long(0)))
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00516,
                getClass().getName() + STR_METHOD_NAME);
            
        }
        
        log.exiting(STR_METHOD_NAME);
        
    }
    
    /**
     * (validate重複顧客)<BR>
     * 重複顧客が追加されていないかチェックを行う。<BR>
     * <BR>
     * get顧客コード(番号)<BR>
     * get部店コード(番号)<BR>
     * にて、指定行番号の顧客コードを取得する。<BR>
     * <BR>
     * 取得した部店コード+顧客コードと指定行番号より<BR>
     * 前の明細行の部店コード+顧客コードを比較し、<BR>
     * 同じ値が存在する場合は、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00517<BR>
     * @@param l_intLineNo - 行番号
     * @@roseuid 40F512FA0374
     */
    public void validateDuplicateAccount(int l_intLineNo) throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " validateRepeatAccount(int)";
        log.entering(STR_METHOD_NAME);
        
        //get顧客コード(番号)
        //get部店コード(番号)
        //にて、指定行番号の顧客コードを取得する
        String l_strAccountCode = this.getAccountCode(l_intLineNo);
        String l_strBranchCode = this.getBranchCode(l_intLineNo);
        String l_strCompareCode = l_strBranchCode + l_strAccountCode;
        for(int i = 0; i < l_intLineNo; i++)
        {
            
            String l_strAccCode = getAccountCode(i);
            String l_strBraCode = getBranchCode(i);
            String l_strComCode = l_strBraCode + l_strAccCode;
            if(l_strCompareCode.equals(l_strComCode))
            {
                
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00517,
                    getClass().getName() + STR_METHOD_NAME);
                
            }
            
        }
        
        log.exiting(STR_METHOD_NAME);
        
    }
    
    /**
     * (validate当選数量)<BR>
     * 当選数量が申告数量を超えていないかチェックを行う。<BR>
     * <BR>
     * 以下の条件に当てはまる場合、例外をスローする。<BR>
     * <BR>
     * [Error条件]<BR>
     * 申告数量 < 当選数量 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00518<BR>
     * @@param l_dblElectedQuantity - 当選数量
     * @@param l_dblOrderQuantity - 申告数量
     * @@roseuid 40F518310038
     */
    public void validateElectedQuantity(double l_dblElectedQuantity, double l_dblOrderQuantity) throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " validateElectedQuantity(double, double)";
        log.entering(STR_METHOD_NAME);
        
        if(l_dblOrderQuantity < l_dblElectedQuantity)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00518,
                getClass().getName() + STR_METHOD_NAME);            
            
        }
        
        log.exiting(STR_METHOD_NAME);
        
    }

    /**
     * (validate項目レングス)<BR>
     * アップロードファ@イル内の項目値がDBレングスの範囲内であるかをチェックする。<BR>
     * <BR>
     * アップロードファ@イル内の以下の項目をgetterメソッドで取得し、<BR>
     * 項目長が以下に示す最大値を超えていないことをチェックする。 <BR>
     * 超えている場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01742<BR>
     * <BR>
     * 　@[項目最大長] <BR>
     * 　@顧客名：　@40byte <BR>
     * 　@抽選結果：　@1byte <BR>
     * 　@当選数量：　@9byte <BR>
     * 　@優先順位：　@7byte <BR>
     * <BR>
     * アップロードファ@イル内の以下の項目をgetterメソッドで取得し、<BR>
     * 項目長が以下に示すレングスであるかをチェックする。 <BR>
     * レングスが違う場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01994<BR>
     * <BR>
     * 　@[項目長] <BR>
     * 　@部店コード：　@3byte <BR>
     * 　@顧客コード：　@6byte <BR>
     * <BR>
     * @@param l_intLineNo - 行番号
     * <BR>
     */
    public void validateItemLength(int l_intLineNo) throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " validateItemLength(int)";
        log.entering(STR_METHOD_NAME);
        
        //getterメソッドで指定行番号の各６項目を取得
        ////①@部店コード
        String l_strBranchCode  = this.getBranchCode(l_intLineNo);
        ////②顧客コード
        String l_strAccountCode = this.getAccountCode(l_intLineNo);
        ////③顧客名  >>>>>>カラムモデル取得。行番号に対応する明細行の顧客名を取得する
        WEB3GentradeCsvColumnModel l_csvDataModelAccountName = this.getColumnModel(ACCOUNT_NAME_LABEL);
        String l_strAccountName = (String)this.getValue(l_intLineNo, l_csvDataModelAccountName);
        ////④抽選結果
        String l_strLotResult   = this.getLotResult(l_intLineNo);
        ////⑤当選数量
        String l_strElectedQuantity =WEB3StringTypeUtility.formatNumber(this.getElectedQuantity(l_intLineNo));
        ////⑥優先順位
        Long l_LngSubstitutePriority = this.getSubstitutePriority(l_intLineNo);
        String l_strSubstitutePriority = null;
        
        if(l_LngSubstitutePriority != null)
        {
            l_strSubstitutePriority = l_LngSubstitutePriority.toString();
        }
        else
        {
            
        }
        
        
        //①@～⑥について、nullでない時レングスチェックを行う
        if((l_strAccountName != null && l_strAccountName.length() > 40)
            || (l_strLotResult != null && l_strLotResult.length() > 1)
            || (l_strElectedQuantity != null && l_strElectedQuantity.length() > 9)
            || (l_strSubstitutePriority != null && l_strSubstitutePriority.length() > 7))
        {
            log.exiting(STR_METHOD_NAME);
             throw new WEB3BusinessLayerException(
                 WEB3ErrorCatalog.BUSINESS_ERROR_01742,
                 getClass().getName() + STR_METHOD_NAME);
        }

        if ((l_strBranchCode != null && l_strBranchCode.length() != 3)
            || (l_strAccountCode != null && l_strAccountCode.length() != 6))
        {
            log.debug("レングスが違う場合、例外をスローする。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01994,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        
    }
    
    /**
     * (setDataFromアップロードTemp)<BR>
     * １）　@super.setDataFromアップロードTemp()をコールする。<BR>
     * ２）　@this.新規／繰上抽選種別に、this.カラムヘッダ[1]をセットする。<BR>
     * @@param l_lngUploadId - アップロードＩＤ<BR>
     * @@throws WEB3SystemLayerException
     */
    public void setDataFromUploadTemp(long l_lngUploadId)
            throws WEB3SystemLayerException
    {

        final String STR_METHOD_NAME = " setDataFromUploadTemp(long)";
        log.entering(STR_METHOD_NAME);
        super.setDataFromUploadTemp(l_lngUploadId);

        this.newAdvanceLotType = this.keyHeader[1];
        
        
    }
    
    /**
     * (saveアップロード開始)<BR>
     * super.saveアップロード開始()をコールする。<BR>
     * <BR>
     * saveアップロード開始()に指定する引数] <BR>
     * データキー：　@this.IPO銘柄.getIPO銘柄ＩＤ() <BR>
     * 備考１：　@抽選種別区分 <BR>
     * 備考２：　@null<BR>
     * 備考３：　@null<BR>
     * 更新者コード：　@管理者コード<BR>
     * <BR>
     * @@param  更新者コード 
     * @@param  抽選区分
     * @@author sra523
     *
     */
    public void saveUpLoadStart(String l_strAdministratorCode,String l_strLotDiv)
            throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = " saveUpLoadStart(String l_strAdministratorCode)";
        log.entering(STR_METHOD_NAME);
        super.saveUpLoadStart(
                this.getIpoProduct().getProductId(),
                l_strLotDiv,
                null,
                null,
                l_strAdministratorCode);
                    
    }
}
@
