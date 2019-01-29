head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.47.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiReservedWordDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用予約語(WEB3SrvRegiReservedWordDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/18 郭英 (中訊) 新規作成
Revesion History : 2005/10/05 鈴木　@美由紀(SRA) トランスリンク対応
Revesion History : 2005/10/18 鈴木　@美由紀(SRA) フィデリティ対応
Revesion History : 2008/03/14 松井　@亮二　@(SCS) QTP連携対応
Revesion History : 2008/05/19 車進 (中訊) 仕様変更モデルNo.368
Revesion History : 2009/04/23 車進 (中訊) 仕様変更モデルNo.412
Revesion History : 2009/05/20 柴双紅(中訊) 仕様変更モデルNo.417
*/
package webbroker3.srvregi.define;

/**
 * サービス利用予約語
 * 
 * @@author 郭英
 * @@version 1.0
 */
public interface WEB3SrvRegiReservedWordDef
{
    /**
     * 予約語：証券会社コード
     */
    public static final String RESERVED_WORD_INSTITUTION_CODE = "%%INSTITUTION_CODE%%";
   
    /**
     * 予約語：部店コード
     */
    public static final String RESERVED_WORD_BRANCH_CODE = "%%BRANCH_CODE%%";
   
    /**
     * 予約語：顧客コード
     */
    public static final String RESERVED_WORD_MAIN_ACCOUNT_CODE = "%%ACCOUNT_CODE%%";
   
    /**
     * 予約語：銘柄コード
     */
    public static final String RESERVED_WORD_PRODUCT_CODE = "%%PRODUCT_CODE%%";
   
    /**
     * 予約語：暗号化顧客コード
     */
    public static final String RESERVED_WORD_ENCRYPTION_ACCOUNT_CODE = "%%ENCRYPTION_ACCOUNT_CODE%%";
   
    /**
     * 予約語：ハッシュ計算結果
     */
    public static final String RESERVED_WORD_HASH_CALC_VALUE = "%%HASH_VALUE%%";
   
    /**
     * 予約語：Token
     */
    public static final String RESERVED_WORD_TOKEN = "%%TOKEN%%";
   
    /**
     * 予約語：注文チャネル
     */
    public static final String RESERVED_WORD_ORDER_CHANEL = "%%CHANNEL%%";
   
    /**
     * 予約語：扱者
     */
    public static final String RESERVED_WORD_TRADER = "%%TRADER_CODE%%";
   
    /**
     * 予約語：信用口座区分
     */
    public static final String RESERVED_WORD_MARGIN_ACCOUNT_DIV = "%%MARGIN_ACCOUNT%%";
   
    /**
     * 予約語：先物OP口座区分（大証）
     */
    public static final String RESERVED_WORD_FUOP_OSE_ACCOUNT_DIV = "%%FUOP_OSE_ACCOUNT%%";
   
    /**
     * 予約語：顧客名
     */
    public static final String RESERVED_WORD_ACCOUNT_NAME = "%%ACCOUNT_NAME%%";
   
    /**
     * 予約語：年月日（YYYYMMDD）
     */
    public static final String RESERVED_WORD_YYYYMMDD = "%%YYYYMMDD%%";
   
    /**
     * 予約語：年月日（YYYY-MM-DD-HH-MM）
     */
    public static final String RESERVED_WORD_YYYYMMDDHHMM = "%%YYYY-MM-DD-HH-MM%%";
    
    /**
     * 予約語：年月日（YYYYMMDDHHMM） <BR>
     */
    public static final String RESERVED_WORD_YYYYMMDDHHMM_2 = "%%YYYYMMDDHHMM%%";

   
    /**
     * 予約語：契約期限（適用終了日）
     */
    public static final String RESERVED_WORD_APPLI_EXPIRE_DATE = "%%APPLI_EXPIRE_DATE%%";
   
    /**
     * 予約語：ハッシュ計算要素（１）
     */
    public static final String RESERVED_WORD_HASH_ELEMENT_1 = "%%HASH_ELEMENT_1%%";
   
    /**
     * 予約語：ハッシュ計算要素（２）
     */
    public static final String RESERVED_WORD_HASH_ELEMENT_2 = "%%HASH_ELEMENT_2%%";
   
    /**
     * 予約語：扱者名
     */
    public static final String RESERVED_WORD_TRADER_NAME = "%%TRADER_NAME%%";
   
    /**
     * 予約語：入力区分
     */
    public static final String RESERVED_WORD_INPUT_DIV = "%%INPUT_DIV_(";
   
    /**
     * 予約語：入力区分末尾
     */
    public static final String RESERVED_WORD_INPUT_DIV_END = ")%%";
   
    /**
     * (予約語：（非置換）%%HSTR%%)
     * 岩井証券 イワイトレーダー専用
     */
    public static final String RESERVED_WORD_HSTR = "%%HSTR%%";
   
    /**
     * (予約語：（非置換）%%FUNDTYPE%%)
     * 岩井証券 イワイトレーダー専用
     */
    public static final String RESERVED_WORD_FUNDTYPE = "%%FUNDTYPE%%";
   
    /**
     * (予約語：（非置換）%%FUNDCODE%%)
     * 岩井証券 イワイトレーダー専用
     */
    public static final String RESERVED_WORD_FUNDCODE = "%%FUNDCODE%%";
   
    /**
     * (予約語：（非置換）%%DELYEAR%%)
     * 岩井証券 イワイトレーダー専用
     */
    public static final String RESERVED_WORD_DELYEAR = "%%DELYEAR%%";
   
    /**
     * (予約語：（非置換）%%DELMONTH%%)
     * 岩井証券 イワイトレーダー専用
     */
    public static final String RESERVED_WORD_DELMONTH = "%%DELMONTH%%";
   
    /**
     * (予約語：（非置換）%%PUTCALL%%)
     * 岩井証券 イワイトレーダー専用
     */
    public static final String RESERVED_WORD_PUTCALL = "%%PUTCALL%%";
   
    /**
     * (予約語：（非置換）%%STRIKEPRC%%)
     * 岩井証券 イワイトレーダー専用
     */
    public static final String RESERVED_WORD_STRIKEPRC = "%%STRIKEPRC%%";
   
    /**
     * (予約語：（非置換）%%TRADETYPE%%)
     * 岩井証券 イワイトレーダー専用
     */
    public static final String RESERVED_WORD_TRADETYPE = "%%TRADETYPE%%";
   
    /**
     * (予約語：（非置換）%%BUYSELLFLAG%%)
     * 岩井証券 イワイトレーダー専用
     */
    public static final String RESERVED_WORD_BUYSELLFLAG = "%%BUYSELLFLAG%%";
   
    /**
     * (予約語：（非置換）%%STKEXCODE%%)
     * 岩井証券 イワイトレーダー専用
     */
    public static final String RESERVED_WORD_STKEXCODE = "%%STKEXCODE%%";
    
    /**
     * 予約語：ハッシュ化された顧客ID
     */
    public static final String RESERVED_WORD_HASH_ACCOUNT_ID = "%%HASH_ACCOUNT_ID%%";
    
    /**
     * 予約語：市場コード
     */
    public static final String RESERVED_WORD_MARKET_CODE = "%%MARKET_CODE%%";
    
    /**
     * 予約語：タイプ
     */
    public static final String RESERVED_WORD_TYPE = "%%TYPE%%";
    
    /**
     * 予約語：SSID値
     */
    public static final String RESERVED_WORD_SSID_VALUE = "%%SSID_VALUE%%";
     
    /**
     * 予約語：暗号化保有銘柄情報
     */
    public static final String RESERVED_WORD_ENCRYPTION_MF_ASSET = "%%ENCRYPTION_MF_ASSET%%";
    
    /**
     * 予約語：年月日（YYYYMMDDHHMISS）
     */
    public static final String RESERVED_WORD_YEAR_MONTH_DAY = "%%YYYYMMDDHHMISS%%";
    
    /**
     * 予約語：GUID
     */
    public static final String RESERVED_WORD_GUID = "%%GUID%%";
    
	/**
	 * 予約語：ID
	 */
	public static final String RESERVED_WORD_ID = "%%ID%%";
	
	/**
	 * 予約語：PASS
	 */
	public static final String RESERVED_WORD_PASS = "%%PASS%%";

	/**
	 * 債券残高リスト：BOND_BALANCE_LIST
	 */
	public static final String RESERVED_WORD_BOND_BALANCE_LIST = "%%BOND_BALANCE_LIST%%";

	/**
	 * 余力残高リスト：TRADINGPOWER_BALANCE_LIST
	 */
	public static final String RESERVED_WORD_TRADINGPOWER_BALANCE_LIST = "%%TRADINGPOWER_BALANCE_LIST%%";

	/**
	 * 資産評価額一覧：STOCK_APPRAISAL_VALUE_INSPECTION
	 */
	public static final String RESERVED_WORD_STOCK_APPRAISAL_VALUE_INSPECTION = "%%STOCK_APPRAISAL_VALUE_INSPECTION%%";

	/**
	 * 債券取引用暗号化パスワード：BOND_ENCRYPT_PASS
	 */
	public static final String RESERVED_WORD_BOND_ENCRYPT_PASS = "%%BOND_ENCRYPT_PASS%%";

	/**
	 * 電子鳩URL：DENSHI_BATO_URL
	 */
	public static final String RESERVED_WORD_DENSHI_BATO_URL = "%%DENSHI_BATO_URL%%";

	/**
	 * 居住区分：RESIDENT
	 */
	public static final String RESERVED_WORD_RESIDENT = "%%RESIDENT%%";

	/**
	 * 情報サービスリスト：INFORMATION_SERVICE_LIST
	 */
	public static final String RESERVED_WORD_INFORMATION_SERVICE_LIST = "%%INFORMATION_SERVICE_LIST%%";

    /**
     * 大証FXログインID：OSE_LOGINID
     */
    public static final String RESERVED_OSE_LOGINID = "%%OSE_LOGINID%%";

    /**
     * 他サービス申込状況：OTHER_SRV_REGI_STATUS
     */
    public static final String  RESERVED_WORD_OTHER_SRV_REGI_STATUS = "%%OTHER_SRV_REGI_STATUS%%";

    /**
     * 現物税区分：EQUITY_TAXTYPE
     */
    public static final String RESERVED_WORD_EQUITY_TAXTYPE = "%%EQUITY_TAXTYPE%%";

    /**
     * 現物税区分（次年）：EQUITY_TAXTYPE_N
     */
    public static final String RESERVED_WORD_EQUITY_TAXTYPE_N = "%%EQUITY_TAXTYPE_N%%";

    /**
     * 信用税区分：MARGIN_TAXTYPE
     */
    public static final String RESERVED_WORD_MARGIN_TAXTYPE = "%%MARGIN_TAXTYPE%%";

    /**
     * 信用税区分（次年）：MARGIN_TAXTYPE_N
     */
    public static final String RESERVED_WORD_MARGIN_TAXTYPE_N = "%%MARGIN_TAXTYPE_N%%";

    /**
     * CDキー：CD_KEY
     */
    public static final String RESERVED_WORD_CD_KEY = "%%CD_KEY%%";
}
@
