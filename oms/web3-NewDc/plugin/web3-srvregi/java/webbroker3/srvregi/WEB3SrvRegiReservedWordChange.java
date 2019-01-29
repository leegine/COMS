head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.40.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiReservedWordChange.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用予約語変換(WEB3SrvRegiReservedWordChange.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/18 張威 (中訊) 新規作成
Revesion History : 2005/10/05 鈴木　@美由紀(SRA) トランスリンク対応
Revesion History : 2005/10/18 鈴木　@美由紀(SRA) フィデリティ対応
Revesion History : 2005/10/18 郭英　@(中訊) 仕様変更モデルNo.230対応
Revesion History : 2008/02/18 武波 (中訊) モデル310
Revesion History : 2008/03/06 武波 (中訊) モデル344,346
Revesion History : 2008/05/22 車進 (中訊) モデル368,374,378,381,385
Revesion History : 2009/04/24 車進 (中訊) モデル405,409
Revesion History : 2009/05/20 柴双紅 (中訊) モデル416,418
*/

package webbroker3.srvregi;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3EffectiveDivDef;
import webbroker3.common.define.WEB3SrvRegiCancelDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSrvRegiApplication;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.service.delegate.WEB3GentradeBatoClientService;
import webbroker3.login.service.delegate.WEB3DigestKey;
import webbroker3.login.service.delegate.WEB3DigestService;
import webbroker3.srvregi.define.WEB3SrvRegiApplicationHasDataDef;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiRegistService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiStartInfoService;
import webbroker3.tradingpower.service.delegate.WEB3TPBondSimplexCooperationService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * サービス利用予約語変換
 *
 * @@author 張威
 * @@version 1.0
 */
public class WEB3SrvRegiReservedWordChange
{

    /**
     * ログ出力ユーティリティ。 <BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
        .getInstance(WEB3SrvRegiReservedWordChange.class);

    /**
     * (予約語：証券会社コード) <BR>
     * （定数） <BR>
     * 予約語：証券会社コード <BR>
     */
    private static final String RESERVED_WORD_INSTITUTION_CODE = "%%INSTITUTION_CODE%%";

    /**
     * (予約語：部店コード) <BR>
     * （定数） <BR>
     * 予約語：部店コード <BR>
     */
    private static final String RESERVED_WORD_BRANCH_CODE = "%%BRANCH_CODE%%";

    /**
     * (予約語：顧客コード) <BR>
     * （定数） <BR>
     * 予約語：顧客コード <BR>
     */
    private static final String RESERVED_WORD_MAIN_ACCOUNT_CODE = "%%ACCOUNT_CODE%%";

    /**
     * (予約語：銘柄コード) <BR>
     * （定数） <BR>
     * 予約語：銘柄コード <BR>
     */
    private static final String RESERVED_WORD_PRODUCT_CODE = "%%PRODUCT_CODE%%";

    /**
     * (予約語：暗号化顧客コード) <BR>
     * （定数） <BR>
     * 予約語：暗号化顧客コード <BR>
     */
    private static final String RESERVED_WORD_ENCRYPTION_ACCOUNT_CODE = "%%ENCRYPTION_ACCOUNT_CODE%%";

    /**
     * (予約語：ハッシュ計算結果) <BR>
     * （定数） <BR>
     * 予約語：ハッシュ計算結果 <BR>
     */
    private static final String RESERVED_WORD_HASH_CALC_VALUE = "%%HASH_VALUE%%";

    /**
     * (予約語：Token) <BR>
     * （定数） <BR>
     * 予約語：Token <BR>
     */
    private static final String RESERVED_WORD_TOKEN = "%%TOKEN%%";

    /**
     * (予約語：注文チャネル) <BR>
     * （定数） <BR>
     * 予約語：注文チャネル <BR>
     */
    private static final String RESERVED_WORD_ORDER_CHANEL = "%%CHANNEL%%";

    /**
     * (予約語：扱者) <BR>
     * （定数） <BR>
     * 予約語：扱者 <BR>
     */
    private static final String RESERVED_WORD_TRADER = "%%TRADER_CODE%%";

    /**
     * (予約語：信用口座区分) <BR>
     * （定数） <BR>
     * 予約語：信用口座区分 <BR>
     */
    private static final String RESERVED_WORD_MARGIN_ACCOUNT_DIV = "%%MARGIN_ACCOUNT%%";

    /**
     * (予約語：先物OP口座区分（大証）) <BR>
     * （定数） <BR>
     * 予約語：先物OP口座区分（大証） <BR>
     */
    private static final String RESERVED_WORD_FUOP_OSE_ACCOUNT_DIV = "%%FUOP_OSE_ACCOUNT%%";

    /**
     * (予約語：顧客名) <BR>
     * （定数） <BR>
     * 予約語：顧客名 <BR>
     */
    private static final String RESERVED_WORD_ACCOUNT_NAME = "%%ACCOUNT_NAME%%";

    /**
     * (予約語：年月日（YYYYMMDD）) <BR>
     * （定数） <BR>
     * 予約語：年月日（YYYYMMDD） <BR>
     */
    private static final String RESERVED_WORD_YYYYMMDD = "%%YYYYMMDD%%";

    /**
     * (予約語：年月日（YYYY-MM-DD-HH-MM）) <BR>
     * （定数） <BR>
     * 予約語：年月日（YYYY-MM-DD-HH-MM） <BR>
     */
    private static final String RESERVED_WORD_YYYYMMDDHHMM = "%%YYYY-MM-DD-HH-MM%%";
    
    /**
     * (予約語：年月日（YYYYMMDDHHMM）) <BR>
     * （定数） <BR>
     * 予約語：年月日（YYYYMMDDHHMM） <BR>
     */
    private static final String RESERVED_WORD_YYYYMMDDHHMM_2 = "%%YYYYMMDDHHMM%%";

    /**
     * (予約語：契約期限（適用終了日）) <BR>
     * （定数） <BR>
     * 予約語：契約期限（適用終了日） <BR>
     */
    private static final String RESERVED_WORD_APPLI_EXPIRE_DATE = "%%APPLI_EXPIRE_DATE%%";

    /**
     * (予約語：ハッシュ計算要素（１）) <BR>
     * （定数） <BR>
     * 予約語：ハッシュ計算要素（１） <BR>
     */
    private static final String RESERVED_WORD_HASH_ELEMENT_1 = "%%HASH_ELEMENT_1%%";

    /**
     * (予約語：ハッシュ計算要素（２）) <BR>
     * （定数） <BR>
     * 予約語：ハッシュ計算要素（２） <BR>
     */
    private static final String RESERVED_WORD_HASH_ELEMENT_2 = "%%HASH_ELEMENT_2%%";

    /**
     * (予約語：扱者名) <BR>
     * （定数） <BR>
     * 予約語：扱者名 <BR>
     */
    private static final String RESERVED_WORD_TRADER_NAME = "%%TRADER_NAME%%";

    /**
     * (予約語：入力区分) <BR>
     * （定数） <BR>
     * 予約語：入力区分 <BR>
     * <BR>
     * クラス図「サービス利用起動情報サービス」のノート「＜補足＞予約語：入力区分につい て」参照 <BR>
     */
    private static final String RESERVED_WORD_INPUT_DIV = "%%INPUT_DIV_(";

    /**
     * (予約語：入力区分末尾) <BR>
     * （定数） <BR>
     * 予約語：入力区分末尾 <BR>
     * <BR>
     * クラス図「サービス利用起動情報サービス」のノート「＜補足＞予約語：入力区分につい て」参照 <BR>
     */
    private static final String RESERVED_WORD_INPUT_DIV_END = ")%%";

    /**
     * (予約語：（非置換）%%HSTR%%) <BR>
     * 岩井証券 イワイトレーダー専用 <BR>
     * （置換えを行わない予約語） <BR>
     */
    private static final String RESERVED_WORD_HSTR = "%%HSTR%%";

    /**
     * (予約語：（非置換）%%FUNDTYPE%%) <BR>
     * 岩井証券 イワイトレーダー専用 <BR>
     * （置換えを行わない予約語） <BR>
     */
    private static final String RESERVED_WORD_FUNDTYPE = "%%FUNDTYPE%%";

    /**
     * (予約語：（非置換）%%FUNDCODE%%) <BR>
     * 岩井証券 イワイトレーダー専用 <BR>
     * （置換えを行わない予約語） <BR>
     */
    private static final String RESERVED_WORD_FUNDCODE = "%%FUNDCODE%%";

    /**
     * (予約語：（非置換）%%DELYEAR%%) <BR>
     * 岩井証券 イワイトレーダー専用 <BR>
     * （置換えを行わない予約語） <BR>
     */
    private static final String RESERVED_WORD_DELYEAR = "%%DELYEAR%%";

    /**
     * (予約語：（非置換）%%DELMONTH%%) <BR>
     * 岩井証券 イワイトレーダー専用 <BR>
     * （置換えを行わない予約語） <BR>
     */
    private static final String RESERVED_WORD_DELMONTH = "%%DELMONTH%%";

    /**
     * (予約語：（非置換）%%PUTCALL%%) <BR>
     * 岩井証券 イワイトレーダー専用 <BR>
     * （置換えを行わない予約語） <BR>
     */
    private static final String RESERVED_WORD_PUTCALL = "%%PUTCALL%%";

    /**
     * (予約語：（非置換）%%STRIKEPRC%%) <BR>
     * 岩井証券 イワイトレーダー専用 <BR>
     * （置換えを行わない予約語） <BR>
     */
    private static final String RESERVED_WORD_STRIKEPRC = "%%STRIKEPRC%%";

    /**
     * (予約語：（非置換）%%TRADETYPE%%) <BR>
     * 岩井証券 イワイトレーダー専用 <BR>
     * （置換えを行わない予約語） <BR>
     */
    private static final String RESERVED_WORD_TRADETYPE = "%%TRADETYPE%%";

    /**
     * (予約語：（非置換）%%BUYSELLFLAG%%) <BR>
     * 岩井証券 イワイトレーダー専用 <BR>
     * （置換えを行わない予約語） <BR>
     */
    private static final String RESERVED_WORD_BUYSELLFLAG = "%%BUYSELLFLAG%%";

    /**
     * (予約語：（非置換）%%STKEXCODE%%) <BR>
     * 岩井証券 イワイトレーダー専用 <BR>
     * （置換えを行わない予約語） <BR>
     */
    private static final String RESERVED_WORD_STKEXCODE = "%%STKEXCODE%%";
    
    /**
     * (予約語：%%HASH_ACCOUNT_ID%%)<BR>
     * （定数） <BR>
     * 予約語：ハッシュ化された顧客ID
     */
    private static final String RESERVED_WORD_HASH_ACCOUNT_ID = "%%HASH_ACCOUNT_ID%%";
    
    /**
     * (予約語：%%MARKET_CODE%%)
     * (定数）<BR>
     * 予約語：市場コード
     */
    private static final String RESERVED_WORD_MARKET_CODE = "%%MARKET_CODE%%";
    
    /**
     * (予約語：%%TYPE%%)
     * (定数）<BR>
     * 予約語：タイプ
     */
    private static final String RESERVED_WORD_TYPE = "%%TYPE%%";
    
    /**
     * (予約語：%%SSID_VALUE%%)
     * (定数）<BR>
     * 予約語：SSID値
     */
    private static final String RESERVED_WORD_SSID_VALUE = "%%SSID_VALUE%%";
    
    /**
     * (予約語：%%ENCRYPTION_MF_ASSET%%)
     * (定数）<BR>
     * 予約語：暗号化保有銘柄情報
     */
    private static final String RESERVED_WORD_ENCRYPTION_MF_ASSET = "%%ENCRYPTION_MF_ASSET%%";
    
    /**
     * (予約語：年月日（YYYYMMDDHHMISS）)<BR>
     * (定数）<BR>
     * 予約語：年月日（YYYYMMDDHHMISS）
     */
    private static final String RESERVED_YEAR_MONTH_DAY = "%%YYYYMMDDHHMISS%%";
    
    /**
     * (予約語：GUID)<BR>
     * (定数）<BR>
     * 予約語：GUID
     */
    private static final String RESERVED_GUID = "%%GUID%%";

    /**
     * (予約語：ID)<BR>
     * (定数）<BR>
     * 予約語：ID<BR>
     */
    private static final String ID = "%%ID%%";

    /**
     * (予約語：PASS)<BR>
     * (定数）<BR>
     * 予約語：PASS<BR>
     */
    private static final String PASS = "%%PASS%%";

	/**
	 * (予約語：BOND_BALANCE_LIST)<BR>
     * (定数）<BR>
	 * 予約語：債券残高リスト<BR>
	 */
	private static final String RESERVED_BOND_BALANCE_LIST = "%%BOND_BALANCE_LIST%%";

	/**
	 * (予約語：TRADINGPOWER_BALANCE_LIST)<BR>
     * (定数）<BR>
	 * 予約語：余力残高リスト<BR>
	 */
	private static final String RESERVED_TRADINGPOWER_BALANCE_LIST = "%%TRADINGPOWER_BALANCE_LIST%%";

	/**
	 * (予約語：STOCK_APPRAISAL_VALUE_INSPECTION)<BR>
     * (定数）<BR>
	 * 予約語：資産評価額一覧<BR>
	 */
	private static final String RESERVED_STOCK_APPRAISAL_VALUE_INSPECTION = "%%STOCK_APPRAISAL_VALUE_INSPECTION%%";

	/**
	 * (予約語：BOND_ENCRYPT_PASS)<BR>
     * (定数）<BR>
	 * 予約語：債券取引用暗号化パスワード<BR>
	 */
	private static final String RESERVED_BOND_ENCRYPT_PASS = "%%BOND_ENCRYPT_PASS%%";

	/**
	 * (予約語：DENSHI_BATO_URL)<BR>
     * (定数）<BR>
	 * 予約語：電子鳩URL<BR>
	 */
	private static final String RESERVED_DENSHI_BATO_URL = "%%DENSHI_BATO_URL%%";

	/**
	 * (予約語：RESIDENT)<BR>
     * (定数）<BR>
	 * 予約語：居住区分<BR>
	 */
	private static final String RESERVED_RESIDENT = "%%RESIDENT%%";

	/**
	 * (予約語：INFORMATION_SERVICE_LIST)<BR>
     * (定数）<BR>
	 * 予約語：情報サービスリスト<BR>
	 */
	private static final String RESERVED_INFORMATION_SERVICE_LIST = "%%INFORMATION_SERVICE_LIST%%";

    /**
     * (予約語：OSE_LOGINID)<BR>
     * (定数）<BR>
     * 予約語：大証FXログインID <BR>
     */
    private static final String RESERVED_OSE_LOGINID = "%%OSE_LOGINID%%";

    /**
     * (予約語：OTHER_SRV_REGI_STATUS)<BR>
     * (定数）<BR>
     * 予約語：他サービス申込状況<BR>
     */
    private static final String RESERVED_OTHER_SRV_REGI_STATUS = "%%OTHER_SRV_REGI_STATUS%%";

    /**
     * (予約語：EQUITY_TAXTYPE)<BR>
     * (定数）<BR>
     * 予約語：現物税区分<BR>
     */
    private static final String RESERVED_EQUITY_TAXTYPE = "%%EQUITY_TAXTYPE%%";

    /**
     * (予約語：EQUITY_TAXTYPE_N)<BR>
     * (定数）<BR>
     * 予約語：現物税区分（次年）<BR>
     */
    private static final String RESERVED_EQUITY_TAXTYPE_N = "%%EQUITY_TAXTYPE_N%%";

    /**
     * (予約語：MARGIN_TAXTYPE)<BR>
     * (定数）<BR>
     * 予約語：信用税区分<BR>
     */
    private static final String RESERVED_MARGIN_TAXTYPE = "%%MARGIN_TAXTYPE%%";

    /**
     * (予約語：MARGIN_TAXTYPE_N)<BR>
     * (定数）<BR>
     * 予約語：信用税区分（次年）<BR>
     */
    private static final String RESERVED_MARGIN_TAXTYPE_N = "%%MARGIN_TAXTYPE_N%%";

    /**
     * (予約語：CD_KEY)<BR>
     * (定数）<BR>
     * 予約語：CDキー<BR>
     */
    private static final String RESERVED_CD_KEY = "%%CD_KEY%%";

    /**
     * PASS
     */
    private String pass;

    /**
     * ID
     */
    private String id;

    /**
     * 証券会社コード
     */
    private String institutionCode;

    /**
     * サービス区分
     */
    private String srvDiv;

    /**
     * 部店コード
     */
    private String branchCode;

    /**
     * 顧客コード
     */
    private String mainAccountCode;

    /**
     * 銘柄コード
     */
    private String productCode;

    /**
     * Token
     */
    private String token;

    /**
     * 注文チャネル
     */
    private String orderChanel;

    /**
     * 扱者コード
     */
    private String traderCode;

    /**
     * 信用口座区分
     */
    private String marginAccountDiv;

    /**
     * 先物OP口座区分（大証）
     */
    private String futureOPAccountDiv;

    /**
     * 現在日付
     */
    private Timestamp currentTimestamp;
    
    /**
     * 市場コード
     */
    private String marketCode;
    
    /**
     * タイプ
     */
    private String type;
    
    /**
     * ハッシュ計算方式
     */
    private String hashCalHowTo;
    
    /**
     * SSID値
     */
    private String ssidValue;

    /**
     * 補助口座
     */
    private SubAccount subAccount;

    /**
     * ダイジェストキー
     */
    private WEB3DigestKey  digestKey;

    /**
     * (サービス利用予約語変換) <BR>
     * （コンストラクタ）<BR>
     * <BR>
     * １）引数の全てを生成したインスタンスの同名の属性にセットする。<BR>
     * ２）WEB3DigestServiceの呼び出し<BR>
     * ３）getRandomKeyメソッドの戻り値（WEB3DigestKey型のオブジェクト）を<BR>
     * 　@ダイジェストキーにセットする。<BR>
     * @@param l_strInstitutionCode -
     *            証券会社コード
     * @@param l_strSrvDiv -
     *            サービス区分
     * @@param l_strBranchCode -
     *            部店コード
     * @@param l_strMainAccountCode -
     *            顧客コード
     * @@param l_strProductCode -
     *            銘柄コード
     * @@param l_strToken -
     *            Token <BR>
     *            （リテラクレア証券 日経テレコン21用パラメータ） <BR>
     * @@param l_strOrderChanel -
     *            注文チャネル
     * @@param l_strTraderCode -
     *            扱者コード
     * @@param l_strMarginAccountDiv -
     *            信用口座区分 <BR>
     *            （PR層より渡される値） <BR>
     * @@param l_strFutureOPAccounDiv -
     *            先物OP口座区分（大証） <BR>
     *            （PR層より渡される値） <BR>
     * @@param l_tsCurrentTimestamp -
     *            現在日付
     * @@param l_strMarketCode<BR>
     *            市場コード<BR>
     * @@param l_strType<BR>
     *            タイプ<BR>
     * @@param l_strHashCalHowTo<BR>
     *            ハッシュ計算方式
     * @@param l_strSsidValue<BR>
     *            SSID値
     * @@param l_strId - (Id)<BR>
     * Id<BR>
     * @@param l_strPass - (Pass)<BR>
     * Pass<BR>
     * @@param l_subAccount<BR>
     *            補助口座<BR>
     * @@roseuid 41B6616D0096
     */
    public WEB3SrvRegiReservedWordChange(String l_strInstitutionCode, String l_strSrvDiv,
        String l_strBranchCode, String l_strMainAccountCode, String l_strProductCode,
        String l_strToken, String l_strOrderChanel, String l_strTraderCode,
        String l_strMarginAccountDiv, String l_strFutureOPAccounDiv, Timestamp l_tsCurrentTimestamp,
        String l_strMarketCode, String l_strType, String l_strHashCalHowTo, String l_strSsidValue,
        String l_strId, String l_strPass, SubAccount l_subAccount)
    {
        //１）引数の全てを生成したインスタンスの同名の属性にセットする。
        this.institutionCode = l_strInstitutionCode;
        this.srvDiv = l_strSrvDiv;
        this.branchCode = l_strBranchCode;
        this.mainAccountCode = l_strMainAccountCode;
        this.productCode = l_strProductCode;
        this.token = l_strToken;
        this.orderChanel = l_strOrderChanel;
        this.traderCode = l_strTraderCode;
        this.marginAccountDiv = l_strMarginAccountDiv;
        this.futureOPAccountDiv = l_strFutureOPAccounDiv;
        this.currentTimestamp = l_tsCurrentTimestamp;
        this.marketCode = l_strMarketCode;
        this.type = l_strType;
        this.hashCalHowTo = l_strHashCalHowTo;
        this.ssidValue = l_strSsidValue;
        this.id = l_strId;
        this.pass = l_strPass;
        this.subAccount = l_subAccount;
        
        //２）WEB3DigestServiceの呼び出し
        WEB3DigestService l_service = (WEB3DigestService) 
            Services.getService(WEB3DigestService.class);
        
        //３）getRandomKeyメソッドの戻り値（WEB3DigestKey型のオブジェクト）を
        //ダイジェストキーにセットする。
        this.digestKey = l_service.getRandomKey();
    }

    /**
     * (replace予約語) <BR>
     * 引数.登録値の中に"%%～%%"で区切られた文字列が存在するかどうかを判定し、 <BR>
     * 存在した場合その"%%～%%"で区切られた文字列を該当する文字列に置き換えて返却する。 <BR>
     * <BR>
     * 1) 引数.登録値に"%%～%%"で区切られた文字列が存在するかどうかを判定し、 <BR>
     * 存在しなかった場合、引数.登録値を返却する。 <BR>
     * <BR>
     * 2) 引数.登録値に存在していた予約語によって、以下の分岐を実施。 <BR>
     * <BR>
     * ○”予約語：証券会社コード”の場合、予約語を以下の値に置き換える。 <BR>
     * －this.証券会社コード <BR>
     * <BR>
     * ○”予約語：部店コード”の場合、予約語を以下の値に置き換える。 <BR>
     * －this.部店コード <BR>
     * <BR>
     * ○”予約語：顧客コード”の場合、予約語を以下の値に置き換える。 <BR>
     * －this.顧客コード <BR>
     * <BR>
     * ○”予約語：銘柄コード”の場合、予約語を以下の値に置き換える。 <BR>
     * －this.銘柄コード <BR>
     * <BR>
     * ○”予約語：暗号化顧客コード”の場合、予約語を以下の値に置き換える。 <BR>
     * －サービス利用起動情報サービス.get暗号化顧客コード()の戻り値 <BR>
     * [get暗号化顧客コードに渡す引数] <BR>
     * 顧客コード=引数.顧客コード <BR>
     * <BR>
     * ○”予約語：ハッシュ計算結果”の場合、予約語を以下の値に置き換える。 <BR>
     * －サービス利用起動情報サービス.createハッシュ値()の戻り値 <BR>
     * [createハッシュ値に渡す引数]<BR>
     * 　@証券会社コード=this.証券会社コード<BR>
     * 　@サービス区分=this.サービス区分<BR>
     * 　@部店コード=this.部店コード<BR>
     * 　@顧客コード=this.顧客コード<BR>
     * 　@現在日付=this.現在日付<BR>
     * 　@市場コード=this.市場コード<BR>
     * 　@銘柄コード=this.銘柄コード<BR>
     * 　@タイプ=this.タイプ<BR>
     * 　@ダイジェストキー=this.ダイジェストキー<BR>
     * 　@SSID値=this.SSID値<BR>
     * <BR>
     * ○”予約語：Token”の場合、予約語を以下の値に置き換える。 <BR>
     * －this.Token <BR>
     * <BR>
     * ○”予約語：注文チャネル”の場合、予約語を以下の値に置き換える。 <BR>
     * －this.注文チャネル <BR>
     * <BR>
     * ○”予約語：扱者”の場合、予約語を以下の値に置き換える。 <BR>
     * －this.扱者コード <BR>
     * （this.扱者コード==nullの場合でもそのまま置き換える） <BR>
     * <BR>
     * ○”予約語：信用口座区分”の場合、予約語を以下の値に置き換える。 <BR>
     * －this.信用口座区分 <BR>
     * <BR>
     * ○”予約語：先物OP口座区分（大証）”の場合、予約語を以下の値に置き換える。 <BR>
     * －this.先物OP口座区分（大証） <BR>
     * <BR>
     * ○”予約語：顧客名”の場合、予約語を以下の値に置き換える。 <BR>
     * －拡張アカウントマネージャ.get顧客().get顧客表示名()の戻り値をセットする。 <BR>
     * [get顧客に渡す引数] <BR>
     * 証券会社コード=引数.証券会社コード <BR>
     * 部店コード=引数.部店コード <BR>
     * 口座コード=引数.顧客コード <BR>
     * <BR>
     * ○”予約語：年月日（YYYYMMDD）”の場合、予約語を以下の値に置き換える。 <BR>
     * －現在日付(*)を"YYYYMMDD"の書式で変換したものをセットする。 <BR>
     * <BR>
     * ○”予約語：年月日（YYYY-MM-DD-HH-MM）”の場合、予約語を以下の値に <BR>
     * 置き換える。 <BR>
     * －現在日付(*)を"YYYY-MM-DD-HH-MM"の書式で変換したものをセットする。 <BR>
     * <BR>
     * ○”予約語：年月日（YYYYMMDDHHMM）”の場合、予約語を以下の値に 置き換える。<BR> 
     * -現在日付を"YYYYMMDDHHMM"の書式で変換したものをセットする。<BR>
     * <BR>
     * ○”予約語：契約期限（適用終了日）”の場合、予約語を以下の値に置き換える。 <BR>
     * －サービス利用申込登録サービス.getサービス申込登録().get適用終了日() <BR>
     * の戻り値をセットする。 <BR>
     * [getサービス申込登録に渡す引数] <BR>
     * 証券会社コード=引数.証券会社コード <BR>
     * 部店コード=引数.部店コード <BR>
     * サービス区分=引数.サービス区分 <BR>
     * 口座コード=引数.顧客コード <BR>
     * 取消区分="通常" <BR>
     * 有効区分="有効" <BR>
     * is行ロック=false <BR>
     * <BR>
     * ○”予約語：ハッシュ計算要素１”の場合、予約語を以下の値に置き換える。 <BR>
     * －サービス情報管理.getサービスマスター().getハッシュ値一覧()をコールする。 <BR>
     * －取得した配列から最初の1件を取得する。 <BR>
     * －取得したサービス利用キーオブジェクト.getサービス利用キー()の戻り値をセットす る。<BR>
     * <BR>
     * ○”予約語：ハッシュ計算要素２”の場合、予約語を以下の値に置き換える。 <BR>
     * －サービス情報管理.getサービスマスター().getハッシュ値一覧()をコールする。 <BR>
     * －取得した配列から最初から2件目を取得する。 <BR>
     * －取得したサービス利用キーオブジェクト.getサービス利用キー()の戻り値をセットす る。<BR>
     * <BR>
     * ○”予約語：扱者名”の場合、予約語を以下の値に置き換える。 <BR>
     * －引数.扱者コード!=nullの場合、以下の手順で設定する。 <BR>
     * （引数.扱者コード==nullの場合、”予約語：扱者名”をnullで置き換える） <BR>
     * －拡張アカウントマネージャ.getInstitution()をコールし、証券会社オブジェクト? 取得。 <BR>
     * [getInstitutionに渡す引数] <BR>
     * 証券会社コード=this.証券会社コード <BR>
     * －拡張金融オブジェクトマネージャ.getTraderをコールし、扱者オブジェクトを取得 。<BR>
     * [getTraderに渡す引数] <BR>
     * 証券会社=取得した証券会社オブジェクト <BR>
     * トレーダーコード=this.扱者コード <BR>
     * 部店コード=this.部店コード <BR>
     * －取得した扱者オブジェクトから取得した扱者苗字をセットする。 <BR>
     * <BR>
     * ○”予約語：入力区分”の場合、予約語を以下の値に置き換える。(EX3参照) <BR>
     * －this.扱者コード==nullの場合 <BR>
     * コールセンターからの操作では無いと判断して、”予約語：入力区分”から <BR>
     * ”予約語：入力区分末尾”の間に指定された数値をセットする。 <BR>
     * －this.扱者コード!=nullの場合 <BR>
     * コールセンターからの操作と判断して"1"をセットする。 <BR>
     * <BR>
     * ○”予約語：ハッシュ化された顧客ID”の場合、予約語を以下の値に置き換える。<BR> 
     * －サービス利用起動情報サービス.createAccountCodeHashValue()の戻り値 <BR>
     * [createAccountCodeHashValue()に渡すパラメタ] <BR>
     * ハッシュ計算方式：　@this.ハッシュ計算方式 <BR>
     * 証券会社コード：　@this.証券会社コード <BR>
     * 部店コード：　@this.部店コード <BR>
     * 顧客コード：　@this.顧客コード <BR>
     * <BR>
     * ○”予約語：市場コード”の場合、予約語を以下の値に置き換える。<BR> 
     * －this.市場コード <BR>
     * <BR>
     * ○”予約語：タイプ”の場合、予約語を以下の値に置き換える。<BR> 
     * －this.タイプ <BR>
     * <BR>
     * ○”予約語：SSID値”の場合、予約語を以下の値に置き換える。<BR>
     * －this.SSID値<BR>
     * <BR>
     * ○”予約語：暗号化保有銘柄情報”の場合、以下の値に置き換える。 
     * －サービス利用起動情報サービス.get暗号化保有銘柄情報（）の戻り値をセットする。 
     * [get暗号化保有銘柄情報()に渡すパラメタ] 
     * 証券会社コード=引数.証券会社コード 
     * 部店コード=引数.部店コード 
     * 口座コード=引数.顧客コード 
     * <BR>
     * ○”予約語：年月日（YYYYMMDDHHMISS）”の場合、予約語を以下の値に置き換える。<BR>
     * 　@－this.ダイジェストキーのキー1(西暦日時分秒)<BR>
     * <BR>
     * ○”予約語：GUID”の場合、予約語を以下の値に置き換える。<BR>
     * 　@－this.ダイジェストキーのキー3(GUID)<BR>
     * <BR>
     * ○”予約語：ID”の場合、予約語を以下の値に置き換える。<BR>
     * 　@－this.ID<BR>
     * ○”予約語：PASS”の場合、予約語を以下の値に置き換える。<BR>
     * 　@－this.PASS<BR>
     * ○”予約語：債券残高リスト”の場合、予約語を以下の値に置き換える。<BR>
     * 　@－サービス利用債券連携共通.get債券残高リスト()の戻り値をセットする。<BR>  
     * [get債券残高リスト()に渡す引数]<BR>  
     * 　@補助口座:this.補助口座<BR>
     * ※補助口座がnullの場合はエラーとする。<BR> 
     * <BR>
     * ○”予約語：余力残高リスト”の場合、予約語を以下の値に置き換える。<BR>
     * 　@－サービス利用債券連携共通.get余力残高リスト()の戻り値をセットする。<BR> 
     * [get余力残高リスト()に渡す引数]<BR>  
     * 　@補助口座：this.補助口座<BR>
     * 　@現在日付：this.現在日付<BR>
     * ※補助口座がnullの場合はエラーとする。<BR> 
     * <BR>
     * ○”予約語：資産評価額一覧”の場合、予約語を以下の値に置き換える。<BR>
     * 　@－債券シンプレクス連携サービス.get資産評価額一覧()の戻り値をセットする。<BR> 
     * [get資産評価額一覧()に渡す引数]<BR>  
     * 　@補助口座：this.補助口座<BR>
     * ※補助口座がnullの場合はエラーとする。<BR> 
     * <BR>
     * ○”予約語：債券取引用暗号化パスワード”の場合、予約語を以下の値に置き換える。<BR>
     * 　@－サービス利用債券連携共通.get債券連携用暗号化パスワード()の戻り値をセットする。<BR> 
     * [get債券連携用暗号化パスワード()に渡す引数]<BR>  
     * 　@証券会社コード：　@this.証券会社コード<BR>  
     * 　@部店コード：　@this.部店コード<BR>
     * 　@顧客コード：　@this.顧客コード<BR>
     * 　@サービス区分：this.サービス区分<BR>
     * 　@現在日付：this.現在日付<BR> 
     * 　@扱者コード：this.扱者コード<BR> 
     * <BR>
     * ○”予約語：電子鳩URL”の場合、予約語を以下の値に置き換える。<BR>
     * 　@－拡張アカウントマネージャ.get顧客()をコールし、顧客オブジェクトを取得<BR> 
     * [get顧客に渡す引数]<BR>
     * 　@証券会社コード：this.証券会社コード<BR>
     * 　@部店コード：this.部店コード<BR>
     * 　@口座コード：this.顧客コード<BR>
     * <BR>
     * 　@－電子鳩システム接続サービスImpl.get電子鳩接続情報()の戻り値をセットする。<BR> 
     * [get電子鳩接続情報()に渡す引数]<BR>
     * 　@顧客：拡張アカウントマネージャ.get顧客()の戻り値<BR>
     * <BR>
     * ○”予約語：情報サービスリスト”の場合、予約語を以下の値に置き換える。<BR> 
     * －サービス利用債券連携共通.get情報サービスリスト()の戻り値をセットする。<BR>  
     * [get情報サービスリストに渡す引数]<BR> 
     * 　@証券会社コード：this.証券会社コード<BR>  
     * 　@部店コード：this.部店コード<BR>
     * 　@口座コード：this.顧客コード<BR>
     * 　@現在日付：this.現在日付<BR> 
     * <BR>
     * ○”予約語：居住区分”の場合、予約語を以下の値に置き換える。<BR>  
     * 　@－拡張アカウントマネージャ.get顧客()の戻り値から居住区分を取得しセットする。<BR> 
     * [get顧客に渡す引数]<BR>  
     * 　@証券会社コード：this.証券会社コード<BR>  
     * 　@部店コード：this.部店コード<BR>
     * 　@口座コード：this.顧客コード<BR>
     * <BR>
     * ○”予約語：大証FXログインID”の場合、予約語を以下の値に置き換える。<BR>
     * 　@－サービス情報管理.getサービスマスター().get付加区分()をコールする。<BR>
     * 　@－get付加区分()の戻り値=nullの場合、「付加区分がnullです。」例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag　@: BUSINESS_ERROR_03160<BR>
     * 　@－以外の場合、get付加区分()の戻り値+this.顧客コード.substring(0,6)をセットする。<BR>
     * <BR>
     * ○”予約語：他サービス申込状況”の場合、予約語を以下の値に置き換える。<BR>
     * －サービス情報管理.getサービスマスター().get付加区分(サービス利用キーID)をコールする。<BR>
     * [get付加区分に渡す引数]<BR>
     * 　@サービス利用キーID : 2（固定値）<BR>
     * <BR>
     * 　@－get付加区分()の戻り値=nullの場合、「付加区分がnullです。」例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag　@: BUSINESS_ERROR_03160<BR>
     * <BR>
     * －サービス利用申込登録サービス.getサービス申込登録()をコールする。<BR>
     * [getサービス申込登録に渡す引数]<BR>
     * 　@証券会社コード=this.証券会社コード<BR>
     * 　@部店コード=this.部店コード<BR>
     * 　@サービス区分=get付加区分()の戻り値<BR>
     * 　@口座コード=this.顧客コード<BR>
     * 　@取消区分="通常"<BR>
     * 　@有効区分="有効"<BR>
     * 　@is行ロック=false<BR>
     * <BR>
     * サービス申込登録テーブルにデータが存在する場合、「1」をセットする、<BR>
     * 存在しない場合、「0」をセットする。<BR>
     * <BR>
     * ○”予約語：現物税区分”の場合、予約語を以下の値に置き換える。<BR>
     * 　@－拡張アカウントマネージャ.get顧客()の戻り値から税区分を取得しセットする。<BR>
     * [get顧客に渡す引数]<BR>
     * 　@証券会社コード：this.証券会社コード<BR>
     * 　@部店コード：this.部店コード<BR>
     * 　@口座コード：this.顧客コード<BR>
     * <BR>
     * ○”予約語：現物税区分（次年）”の場合、予約語を以下の値に置き換える。<BR>
     * 　@－拡張アカウントマネージャ.get顧客()の戻り値から税区分（次年）を取得しセットする。<BR>
     * [get顧客に渡す引数]<BR>
     * 　@証券会社コード：this.証券会社コード<BR>
     * 　@部店コード：this.部店コード<BR>
     * 　@口座コード：this.顧客コード<BR>
     * <BR>
     * ○”予約語：信用税区分”の場合、予約語を以下の値に置き換える。<BR>
     * 　@－拡張アカウントマネージャ.get顧客()の戻り値から信用取引税区分を取得しセットする。<BR>
     * [get顧客に渡す引数]<BR>
     * 　@証券会社コード：this.証券会社コード<BR>
     * 　@部店コード：this.部店コード<BR>
     * 　@口座コード：this.顧客コード<BR>
     * <BR>
     * ○”予約語：信用税区分（次年）”の場合、予約語を以下の値に置き換える。<BR>
     * 　@－拡張アカウントマネージャ.get顧客()の戻り値から信用取引税区分（次年）を<BR>
     * 　@　@取得しセットする。<BR>
     * [get顧客に渡す引数]<BR>
     * 　@証券会社コード：this.証券会社コード<BR>
     * 　@部店コード：this.部店コード<BR>
     * 　@口座コード：this.顧客コード<BR>
     * <BR>
     * ○”予約語：CDキー”の場合、予約語を以下の値に置き換える。<BR>
     * 　@－サービス利用起動情報サービス.getCDキー()の戻り値を取得しセットする。<BR>
     * [getCDキーに渡す引数]<BR>
     * 　@部店コード：this.部店コード<BR>
     * 　@口座コード：this.顧客コード<BR>
     * 　@サービス区分：this.サービス区分<BR>
     * 　@証券会社コード：this.証券会社コード<BR>
     * <BR>
     * ○以下の予約語の場合、置換えを行わずそのままにする。 <BR>
     * ・予約語：（非置換）%%HSTR%% <BR>
     * ・予約語：（非置換）%%FUNDTYPE%% <BR>
     * ・予約語：（非置換）%%FUNDCODE%% <BR>
     * ・予約語：（非置換）%%DELYEAR%% <BR>
     * ・予約語：（非置換）%%DELMONTH%% <BR>
     * ・予約語：（非置換）%%PUTCALL%% <BR>
     * ・予約語：（非置換）%%STRIKEPRC%% <BR>
     * ・予約語：（非置換）%%TRADETYPE%% <BR>
     * ・予約語：（非置換）%%BUYSELLFLAG%% <BR>
     * ・予約語：（非置換）%%STKEXCODE%% <BR>
     * <BR>
     * 3) 置換後の文字列返却 <BR>
     * 3-1) 2)で置き換えた文字列に、他の予約語が残っている場合 <BR>
     * 置き換えた文字列に対して、2)を再度実施する。 <BR>
     * <BR>
     * 3-2) 2)で置き換えた文字列に、他の予約語が残っていない場合 <BR>
     * 置き換えた文字列を返却する。 <BR>
     * <BR>
     * <BR>
     * EX1) （証券会社コード="40"、部店コード="123"の場合） <BR>
     * ・引数.登録値："AAAAA_%%INSTITUTION_CODE%%_AAAAA"の場合 <BR>
     * 返却される文字列："AAAAA_40_AAAAA" <BR>
     * <BR>
     * ・引数.登録値： <BR>
     * "BBBBB_%%INSTITUTION_CODE%%_BBBB_%%BRANCH_CODE%%BBB" <BR>
     * 返却される文字列："BBBBB_40_BBBB_123BBB" <BR>
     * <BR>
     * EX2) 置換えを行わない予約語の場合 <BR>
     * 引数.登録値： <BR>
     * "BBBBB_%%INSTITUTION_CODE%%_BBBB_%%HSTR%%BBB" <BR>
     * 返却される文字列："BBBBB_40_BBBB_%%HSTR%%BBB" <BR>
     * <BR>
     * EX3) 入力区分の場合 <BR>
     * 引数.登録値："abcde%%INPUT_DIV_(40)%%" <BR>
     * 返却される文字列："abcde40" <BR>
     * <BR>
     * (*) サービス利用起動情報サービス.get現在日付()の戻り値 <BR>
     * [get現在日付に渡す引数] <BR>
     * 現在日付=this.現在日付 <BR>
     *
     * @@param l_strRegValue -
     *            登録値
     * @@return String
     * @@roseuid 41B661560113
     */
    public String replaceReservedWord(String l_strRegValue) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " replaceReservedWord(String)";
        log.entering(STR_METHOD_NAME);

        //1) 引数.登録値に"%%～%%"で区切られた文字列が存在するかどうかを判定し、
        //存在しなかった場合、引数.登録値を返却する。
        if (!this.isRequiredFormat(l_strRegValue))
        {
            log.debug("引数.登録値を返却する。");
            log.exiting(STR_METHOD_NAME);
            return l_strRegValue;
        }

        String[] l_reservedWords = this.getReservedWords(l_strRegValue);
        StringBuffer l_sbReturnValue = new StringBuffer();

        String l_reservedWord = null;

        WEB3SrvRegiStartInfoService l_startInfoService = (WEB3SrvRegiStartInfoService)Services.getService(WEB3SrvRegiStartInfoService.class);
        WEB3SrvRegiRegistService l_registService = (WEB3SrvRegiRegistService)Services.getService(WEB3SrvRegiRegistService.class);
        WEB3SrvRegiStreamCommon l_srvRegiStreamCommon = new WEB3SrvRegiStreamCommon();
        WEB3GentradeBatoClientService l_genBatoClientService = (WEB3GentradeBatoClientService)Services.getService(
        	WEB3GentradeBatoClientService.class);
        WEB3SrvRegiServiceInfoManagement l_serviceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3GentradeAccountManager l_accManager = (WEB3GentradeAccountManager)GtlUtils.getAccountManager();

        int l_intReservedWordCnt = 0;
        int l_intRegValueLength = l_strRegValue.length();
        for (int i = 0; i < l_intRegValueLength; i++)
        {
            if (!l_strRegValue.startsWith(l_reservedWords[l_intReservedWordCnt]))
            {
                //戻り値追加
                l_sbReturnValue.append(l_strRegValue.charAt(0));
                //登録値収縮
                l_strRegValue = l_strRegValue.substring(1, l_intRegValueLength-i);
            }
            else
            {
                //2) 引数.登録値に存在していた予約語によって、以下の分岐を実施。
                if (RESERVED_WORD_INSTITUTION_CODE.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("”予約語：証券会社コード”の場合");
                    l_reservedWord = this.institutionCode;
                }
                else if (RESERVED_WORD_BRANCH_CODE.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("”予約語：部店コード”の場合");
                    l_reservedWord = this.branchCode;
                }
                else if (RESERVED_WORD_MAIN_ACCOUNT_CODE.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("”予約語：顧客コード”の場合");
                    l_reservedWord = this.mainAccountCode;
                }
                else if (RESERVED_WORD_PRODUCT_CODE.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("”予約語：銘柄コード”の場合");
                    l_reservedWord = this.productCode;
                }
                else if (RESERVED_WORD_ENCRYPTION_ACCOUNT_CODE.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("”予約語：暗号化顧客コード”の場合");
                    l_reservedWord = l_startInfoService.getCryptAccountCode(this.mainAccountCode);
                }
                else if (RESERVED_WORD_HASH_CALC_VALUE.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("”予約語：ハッシュ計算結果”の場合");
                    l_reservedWord = l_startInfoService.createHashValue(
                        this.institutionCode, 
                        this.srvDiv, 
                        this.branchCode,
                        this.mainAccountCode, 
                        this.currentTimestamp, 
                        this.marketCode, 
                        this.productCode, 
                        this.type, 
                        this.digestKey,
                        this.ssidValue);
                }
                //仕様変更 NO_195 docId削除
                else if (RESERVED_WORD_TOKEN.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("”予約語：Token”の場合");
                    l_reservedWord = this.token;
                }
                else if (RESERVED_WORD_ORDER_CHANEL.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("”予約語：注文チャネル”の場合");
                    l_reservedWord = this.orderChanel;
                }
                else if (RESERVED_WORD_TRADER.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("”予約語：扱者”の場合");
                    l_reservedWord = this.traderCode;
                }
                else if (RESERVED_WORD_MARGIN_ACCOUNT_DIV.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("”予約語：信用口座区分”の場合");
                    l_reservedWord = this.marginAccountDiv;
                }
                else if (RESERVED_WORD_FUOP_OSE_ACCOUNT_DIV.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("”予約語：先物OP口座区分（大証）");
                    l_reservedWord = this.futureOPAccountDiv;
                }
                else if (RESERVED_WORD_ACCOUNT_NAME.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("”予約語：顧客名”の場合");
                    l_reservedWord = l_accManager.getMainAccount(this.institutionCode, this.branchCode, this.mainAccountCode).getDisplayAccountName();
                }
                else if (RESERVED_WORD_YYYYMMDD.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("”予約語：年月日（YYYYMMDD）”の場合");
                    l_reservedWord = WEB3DateUtility.formatDate(this.currentTimestamp, "yyyyMMdd");
                }
                else if (RESERVED_WORD_YYYYMMDDHHMM.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("”予約語：年月日（YYYY-MM-DD-HH-MM）”の場合");
                    l_reservedWord = WEB3DateUtility.formatDate(this.currentTimestamp, "yyyy-MM-dd-HH-mm");
                }
                else if (RESERVED_WORD_YYYYMMDDHHMM_2.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("”予約語：年月日（YYYYMMDDHHMM）”の場合");
                    l_reservedWord = WEB3DateUtility.formatDate(this.currentTimestamp, "yyyyMMddHHmm");
                }
                else if (RESERVED_WORD_APPLI_EXPIRE_DATE.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("”予約語：契約期限（適用終了日）”の場合");
                    WEB3GentradeSrvRegiApplication l_application = l_registService.getServiceRegist(this.institutionCode, this.branchCode, this.srvDiv, this.mainAccountCode, WEB3SrvRegiCancelDivDef.USUAL_DEFAULT, WEB3EffectiveDivDef.EFFECTIVE, false);
                    Timestamp l_tsAppliEndDate = l_application == null ? null : l_application.getAppliEndDate();
                    l_reservedWord = l_tsAppliEndDate == null ? null : WEB3DateUtility.formatDate(l_tsAppliEndDate, "yyyyMMdd");
                }
                else if (RESERVED_WORD_HASH_ELEMENT_1.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("”予約語：ハッシュ計算要素１”の場合");
                    WEB3SrvRegiServiceUseKey[] l_serviceUseKey = l_serviceInfoManagement.getSrvMaster(this.institutionCode, this.srvDiv, false).getHashList();

                    if (l_serviceUseKey != null && l_serviceUseKey.length >= 1)
                    {
                        l_reservedWord = l_serviceUseKey[0].getSrvUseKey();
                    }
                    else
                    {
                        l_reservedWord = null;
                    }
                }
                else if (RESERVED_WORD_HASH_ELEMENT_2.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("”予約語：ハッシュ計算要素２”の場合");
                    WEB3SrvRegiServiceUseKey[] l_serviceUseKey = l_serviceInfoManagement.getSrvMaster(this.institutionCode, this.srvDiv, false).getHashList();

                    if (l_serviceUseKey != null && l_serviceUseKey.length >= 2)
                    {
                        l_reservedWord = l_serviceUseKey[1].getSrvUseKey();
                    }
                    else
                    {
                        l_reservedWord = null;
                    }
                }
                else if (RESERVED_WORD_TRADER_NAME.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("”予約語：扱者名”の場合");

                    if (this.traderCode != null)
                    {
                        try
                        {
                            Institution l_institution = l_accManager.getInstitution(this.institutionCode);

                            l_reservedWord = ((TraderRow)GtlUtils.getFinObjectManager().getTrader(l_institution, this.traderCode, this.branchCode).getDataSourceObject()).getFamilyName();
                        }
                        catch (NotFoundException l_ex)
                        {
                            log.error(this.getClass().getName() + STR_METHOD_NAME);

                            log.exiting(STR_METHOD_NAME);

                            //例外をスローする
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                                this.getClass().getName() + STR_METHOD_NAME);
                        }
                    }
                    else
                    {
                        l_reservedWord = null;
                    }
                }
                else if (l_reservedWords[l_intReservedWordCnt].startsWith(RESERVED_WORD_INPUT_DIV) && l_reservedWords[l_intReservedWordCnt].endsWith(RESERVED_WORD_INPUT_DIV_END))
                {
                    log.debug("”予約語：入力区分”の場合");

                    if (this.traderCode == null)
                    {
                        l_reservedWord = l_reservedWords[l_intReservedWordCnt].substring(RESERVED_WORD_INPUT_DIV.length(), l_reservedWords[l_intReservedWordCnt].length() - RESERVED_WORD_INPUT_DIV_END.length());
                    }
                    else
                    {
                        l_reservedWord = "1";
                    }
                }
                else if(RESERVED_WORD_HASH_ACCOUNT_ID.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("”予約語：ハッシュ化された顧客ID”の場合");
                    l_reservedWord = l_startInfoService.createAccountCodeHashValue(this.hashCalHowTo, this.institutionCode, this.branchCode, this.mainAccountCode);
                }
                else if(RESERVED_WORD_MARKET_CODE.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("”予約語：市場コード”の場合");
                    l_reservedWord = this.marketCode;
                }
                else if(RESERVED_WORD_TYPE.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("”予約語：タイプ”の場合");
                    l_reservedWord = this.type;
                }
                else if(RESERVED_WORD_SSID_VALUE.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("”予約語：SSID値”の場合");
                    l_reservedWord = this.ssidValue;
                }
                else if (RESERVED_WORD_ENCRYPTION_MF_ASSET.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("”予約語：暗号化保有銘柄情報”の場合");
                    l_reservedWord = l_startInfoService.getEncryptionMfAsset(this.institutionCode, this.branchCode, this.mainAccountCode);
                }
                else if (RESERVED_WORD_HSTR.equals(l_reservedWords[l_intReservedWordCnt])
                    || RESERVED_WORD_FUNDTYPE.equals(l_reservedWords[l_intReservedWordCnt])
                    || RESERVED_WORD_FUNDCODE.equals(l_reservedWords[l_intReservedWordCnt])
                    || RESERVED_WORD_DELYEAR.equals(l_reservedWords[l_intReservedWordCnt])
                    || RESERVED_WORD_DELMONTH.equals(l_reservedWords[l_intReservedWordCnt])
                    || RESERVED_WORD_PUTCALL.equals(l_reservedWords[l_intReservedWordCnt])
                    || RESERVED_WORD_STRIKEPRC.equals(l_reservedWords[l_intReservedWordCnt])
                    || RESERVED_WORD_TRADETYPE.equals(l_reservedWords[l_intReservedWordCnt])
                    || RESERVED_WORD_BUYSELLFLAG.equals(l_reservedWords[l_intReservedWordCnt])
                    || RESERVED_WORD_STKEXCODE.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("（非置換）の予約語の場合");
                    l_reservedWord = l_reservedWords[l_intReservedWordCnt];
                }
                else if (RESERVED_YEAR_MONTH_DAY.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("予約語：年月日（YYYYMMDDHHMISS）の場合");
                    //this.ダイジェストキーのキー1(西暦日時分秒)
                    l_reservedWord = this.digestKey.getKey1();
                }
                else if (RESERVED_GUID.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    log.debug("予約語：GUIDの場合");
                    //this.ダイジェストキーのキー3(GUID)
                    l_reservedWord = this.digestKey.getKey3();
                }
                else if (ID.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    //○”予約語：ID”の場合、予約語を以下の値に置き換える。
                    //－this.ID
                    l_reservedWord = this.id;
                }
                else if (PASS.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    //○”予約語：PASS”の場合、予約語を以下の値に置き換える。
                    //－this.PASS
                    l_reservedWord = this.pass;
                }
                else if (RESERVED_BOND_BALANCE_LIST.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    //○”予約語：債券残高リスト”の場合、予約語を以下の値に置き換える。
                    //　@－サービス利用債券連携共通.get債券残高リスト()の戻り値をセットする。  
                	//[get債券残高リスト()に渡す引数]
                	//　@補助口座:this.補助口座
                	//※補助口座がnullの場合はエラーとする。
                	if (this.subAccount == null)
                	{
                        log.debug("補助口座が未指定です。");
                        log.exiting(STR_METHOD_NAME);

                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_03091,
                            this.getClass().getName() + STR_METHOD_NAME);
                	}
                	else
                	{
                		l_reservedWord = l_srvRegiStreamCommon.getBondBalanceList(this.subAccount);
                	}

                }
                else if (RESERVED_TRADINGPOWER_BALANCE_LIST.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                	//○”予約語：余力残高リスト”の場合、予約語を以下の値に置き換える。
                	//　@－サービス利用債券連携共通.get余力残高リスト()の戻り値をセットする。
                	//[get余力残高リスト()に渡す引数]
                	//　@補助口座：this.補助口座
                	//※補助口座がnullの場合はエラーとする。
                  	if (this.subAccount == null)
                	{
                        log.debug("補助口座が未指定です。");
                        log.exiting(STR_METHOD_NAME);

                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_03091,
                            this.getClass().getName() + STR_METHOD_NAME);
                	}
                	else
                	{
                		l_reservedWord = l_srvRegiStreamCommon.getTradingPowerBalanceList(
                            this.subAccount);
                	}
                }
                else if (RESERVED_STOCK_APPRAISAL_VALUE_INSPECTION.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                	//○”予約語：資産評価額一覧”の場合、予約語を以下の値に置き換える。
                	//　@－債券シンプレクス連携サービス.get資産評価額一覧()の戻り値をセットする。
                	//[get資産評価額一覧()に渡す引数]
                	//　@補助口座：this.補助口座
                	//※補助口座がnullの場合はエラーとする。
                  	if (this.subAccount == null)
                	{
                        log.debug("補助口座が未指定です。");
                        log.exiting(STR_METHOD_NAME);

                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_03091,
                            this.getClass().getName() + STR_METHOD_NAME);
                	}
                	else
                	{
                        WEB3TPBondSimplexCooperationService l_bondSimplexCooperationService =
                            (WEB3TPBondSimplexCooperationService)Services.getService(
                                WEB3TPBondSimplexCooperationService.class);
                        l_reservedWord =
                            l_bondSimplexCooperationService.getAssetList((WEB3GentradeSubAccount)this.subAccount);
                	}
                }
                else if (RESERVED_BOND_ENCRYPT_PASS.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                	//○”予約語：債券取引用暗号化パスワード”の場合、予約語を以下の値に置き換える。
                	//　@－サービス利用債券連携共通.get債券連携用暗号化パスワード()の戻り値をセットする。
                	//[get債券連携用暗号化パスワード()に渡す引数]
                	// 証券会社コード：　@this.証券会社コード
                	//　@部店コード：　@this.部店コード
                	//　@顧客コード：　@this.顧客コード
                	//　@サービス区分：this.サービス区分
                	//　@現在日付：this.現在日付
                	//　@扱者コード：this.扱者コード
                	l_reservedWord = l_srvRegiStreamCommon.getBondOrgUsedCryptPassword(
                	    this.institutionCode,
                	    this.branchCode,
                	    this.mainAccountCode,
                	    this.srvDiv,
                	    this.currentTimestamp,
                	    this.traderCode);
                }
                else if (RESERVED_DENSHI_BATO_URL.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                	//○”予約語：電子鳩URL”の場合、予約語を以下の値に置き換える。
                	//　@－拡張アカウントマネージャ.get顧客()をコールし、顧客オブジェクトを取得
                	//[get顧客に渡す引数]
                	//　@証券会社コード：this.証券会社コード
                	//　@部店コード：this.部店コード
                	//　@口座コード：this.顧客コード
                	//　@－電子鳩システム接続サービスImpl.get電子鳩接続情報()の戻り値をセットする。
                	//[get電子鳩接続情報()に渡す引数]
                	//顧客：拡張アカウントマネージャ.get顧客()の戻り値
                	WEB3GentradeMainAccount l_mainAccount =
                	    l_accManager.getMainAccount(
                            this.institutionCode,
                    	    this.branchCode,
                    	    this.mainAccountCode);

                	l_reservedWord = l_genBatoClientService.getBatoConnectionInfo(l_mainAccount);
                	
                }
                else if (RESERVED_INFORMATION_SERVICE_LIST.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                	//○”予約語：情報サービスリスト”の場合、予約語を以下の値に置き換える。
                	//－サービス利用債券連携共通.get情報サービスリスト()の戻り値をセットする。  
                	//[get情報サービスリストに渡す引数]
                	//証券会社コード：this.証券会社コード  
                	//部店コード：this.部店コード
                	//口座コード：this.顧客コード  
                	//現在日付：this.現在日付
                	l_reservedWord = l_srvRegiStreamCommon.getInfoServiceList(
                	    this.institutionCode,
                	    this.branchCode,
                	    this.mainAccountCode,
                	    this.currentTimestamp);
                }
                else if (RESERVED_RESIDENT.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                	//○”予約語：居住区分”の場合、予約語を以下の値に置き換える。
                	//－拡張アカウントマネージャ.get顧客()の戻り値から居住区分を取得しセットする。
                	//[get顧客に渡す引数]
                	//証券会社コード：this.証券会社コード
                	//部店コード：this.部店コード
                	//口座コード：this.顧客コード
                	WEB3GentradeMainAccount l_mainAccount =
                	    l_accManager.getMainAccount(
                            this.institutionCode,
                    	    this.branchCode,
                    	    this.mainAccountCode);
                	l_reservedWord = l_mainAccount.getMainAccountRow().getResident();
                }
                else if (RESERVED_OSE_LOGINID.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    //○”予約語：大証FXログインID”の場合、予約語を以下の値に置き換える。
                    //－サービス情報管理.getサービスマスター().get付加区分()をコールする。
                    String l_strAdditionDiv = l_serviceInfoManagement.getSrvMaster(
                        this.institutionCode, this.srvDiv, false).getAdditionDiv();
                    //－get付加区分()の戻り値=nullの場合、「付加区分がnullです。」例外をスローする。
                    if (l_strAdditionDiv == null)
                    {
                        log.debug("付加区分がnullです。");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_03160,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "付加区分がnullです。");
                    }
                    //－以外の場合、get付加区分()の戻り値+this.顧客コード.substring(0,6)をセットする。
                    else
                    {
                        l_reservedWord = l_strAdditionDiv;
                        if (this.mainAccountCode != null && this.mainAccountCode.length() > 5)
                        {
                            l_reservedWord = l_strAdditionDiv + this.mainAccountCode.substring(0, 6);
                        }
                    }
                }
                else if (RESERVED_OTHER_SRV_REGI_STATUS.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    //○”予約語：他サービス申込状況”の場合、予約語を以下の値に置き換える
                    //サービス情報管理.getサービスマスター().get付加区分(サービス利用キーID)をコールする
                    //[get付加区分に渡す引数]
                    //　@サービス利用キーID : 2（固定値）
                    WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
                        l_serviceInfoManagement.getSrvMaster(
                            this.institutionCode, this.srvDiv, false);
                    String l_strAdditionDiv = l_srvRegiServiceMaster.getAdditionDiv(2);

                    //－get付加区分()の戻り値=nullの場合、「付加区分がnullです。」例外をスローする。
                    if (l_strAdditionDiv == null)
                    {
                        log.debug("付加区分がnullです。");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_03160,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "付加区分がnullです。");
                    }

                    //－サービス利用申込登録サービス.getサービス申込登録()をコールする。
                    //[getサービス申込登録に渡す引数]
                    //　@証券会社コード=this.証券会社コード
                    //　@部店コード=this.部店コード
                    //　@サービス区分=get付加区分()の戻り値
                    //　@口座コード=this.顧客コード
                    //　@取消区分="通常"
                    //　@有効区分="有効"
                    //　@is行ロック=false
                    WEB3GentradeSrvRegiApplication l_SrvRegiApplication =
                        l_registService.getServiceRegist(
                            this.institutionCode,
                            this.branchCode,
                            l_strAdditionDiv,
                            this.mainAccountCode,
                            WEB3SrvRegiCancelDivDef.USUAL_DEFAULT,
                            WEB3EffectiveDivDef.EFFECTIVE,
                            false);

                    if (l_SrvRegiApplication != null)
                    {
                        //サービス申込登録テーブルにデータが存在する場合、「1」をセットする、
                        l_reservedWord = WEB3SrvRegiApplicationHasDataDef.HASDATA;
                    }
                    else
                    {
                        //存在しない場合、「0」をセットする。
                        l_reservedWord = WEB3SrvRegiApplicationHasDataDef.NODATA;
                    }
                }
                else if (RESERVED_EQUITY_TAXTYPE.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    //○”予約語：現物税区分”の場合、予約語を以下の値に置き換える
                    //－拡張アカウントマネージャ.get顧客()の戻り値から税区分を取得しセットする。
                    //[get顧客に渡す引数]
                    //　@証券会社コード：this.証券会社コード
                    //　@部店コード：this.部店コード
                    //　@口座コード：this.顧客コード
                    WEB3GentradeMainAccount l_mainAccount =
                        l_accManager.getMainAccount(
                            this.institutionCode,
                            this.branchCode,
                            this.mainAccountCode);
                    l_reservedWord =
                        l_mainAccount.getMainAccountRow().getTaxType().intValue() + "";
                }
                else if (RESERVED_EQUITY_TAXTYPE_N.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    //○”予約語：現物税区分（次年）”の場合、予約語を以下の値に置き換える
                    //－拡張アカウントマネージャ.get顧客()の戻り値から税区分（次年）を取得しセットする。
                    //[get顧客に渡す引数]
                    //　@証券会社コード：this.証券会社コード
                    //　@部店コード：this.部店コード
                    //　@口座コード：this.顧客コード
                    WEB3GentradeMainAccount l_mainAccount =
                        l_accManager.getMainAccount(
                            this.institutionCode,
                            this.branchCode,
                            this.mainAccountCode);
                    l_reservedWord =
                        l_mainAccount.getMainAccountRow().getTaxTypeNext().intValue() + "";
                }
                else if (RESERVED_MARGIN_TAXTYPE.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    //○”予約語：信用税区分”の場合、予約語を以下の値に置き換える。
                    //－拡張アカウントマネージャ.get顧客()の戻り値から信用取引税区分を取得しセットする。
                    //[get顧客に渡す引数]
                    //　@証券会社コード：this.証券会社コード
                    //　@部店コード：this.部店コード
                    //　@口座コード：this.顧客コード
                    WEB3GentradeMainAccount l_mainAccount =
                        l_accManager.getMainAccount(
                            this.institutionCode,
                            this.branchCode,
                            this.mainAccountCode);
                    TaxTypeEnum l_marginTaxType =
                        l_mainAccount.getMainAccountRow().getMarginTaxType();
                    if (l_marginTaxType != null)
                    {
                        l_reservedWord = l_marginTaxType.intValue() + "";
                    }
                    else
                    {
                        l_reservedWord = null;
                    }
                }
                else if (RESERVED_MARGIN_TAXTYPE_N.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    //○”予約語：信用税区分（次年）”の場合、予約語を以下の値に置き換える。
                    //－拡張アカウントマネージャ.get顧客()の戻り値から信用取引税区分（次年）を取得しセットする。
                    //[get顧客に渡す引数]
                    //　@証券会社コード：this.証券会社コード
                    //　@部店コード：this.部店コード
                    //　@口座コード：this.顧客コード
                    WEB3GentradeMainAccount l_mainAccount =
                        l_accManager.getMainAccount(
                            this.institutionCode,
                            this.branchCode,
                            this.mainAccountCode);
                    TaxTypeEnum l_marginTaxType =
                        l_mainAccount.getMainAccountRow().getMarginTaxTypeNext();
                    if (l_marginTaxType != null)
                    {
                        l_reservedWord = l_marginTaxType.intValue() + "";
                    }
                    else
                    {
                        l_reservedWord = null;
                    }
                }
                else if (RESERVED_CD_KEY.equals(l_reservedWords[l_intReservedWordCnt]))
                {
                    //○”予約語：CDキー”の場合、予約語を以下の値に置き換える。
                    //－サービス利用起動情報サービス.getCDキー()の戻り値を取得しセットする。
                    //[getCDキーに渡す引数]
                    //　@部店コード：this.部店コード
                    //　@口座コード：this.顧客コード
                    //　@サービス区分：this.サービス区分
                    //  証券会社コード：this.証券会社コード
                    l_reservedWord = l_startInfoService.getCDKey(
                        this.branchCode,
                        this.mainAccountCode,
                        this.srvDiv,
                        this.institutionCode);
                }
                else
                {
                    log.debug(getClass().getName() + STR_METHOD_NAME);
                    log.exiting(STR_METHOD_NAME);
                    log.debug("【予約語該当なし】: "+l_reservedWords[l_intReservedWordCnt]);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                        this.getClass().getName() + STR_METHOD_NAME);
                }

                //削除早くも置き換えるの予約語
                //l_strRegValue = l_strRegValue.replaceFirst(l_reservedWords[l_intReservedWordCnt], "");
                l_strRegValue = replace(l_strRegValue, l_reservedWords[l_intReservedWordCnt], "");
                //予約語追加する戻りの
                if (l_reservedWord != null)
                {
                    l_sbReturnValue.append(l_reservedWord);
                }
                i += l_reservedWords[l_intReservedWordCnt].length() - 1;
                l_intReservedWordCnt += 1;

                //予約語置き換えるない
                if (l_intReservedWordCnt >= l_reservedWords.length)
                {
                    if (l_reservedWord != null)
                    {
                        l_sbReturnValue.append(l_strRegValue);
                    }
                    break;
                }
            }
        }


        log.exiting(STR_METHOD_NAME);
        return l_sbReturnValue.toString();
    }

    /**
     * 引数の中に"%%～%%"で区切の獲得
     * @@param l_strUrl
     * @@return String[]
     */
    private String[] getReservedWords(String l_strUrl)
    {
        if (WEB3StringTypeUtility.isEmptyOrBlank(l_strUrl))
        {
            return null;
        }

        final String l_strDoublePercents = "%%";
        List l_lisReservedWords = new ArrayList();

        int l_intUrlLength = l_strUrl.length();

        for (int i = 0; i < l_intUrlLength - 1; i++)
        {
            if (l_strDoublePercents.equals(l_strUrl.substring(i, i + 2)))
            {
                int l_intHead = 0;
                int l_intTail = 0;

                for (int j = i + 2; j < l_intUrlLength - 1; j++)
                {
                    l_intHead = i;
                    if (l_strDoublePercents.equals(l_strUrl.substring(j, j + 2)))
                    {
                        l_intTail = j + 2;
                        i = j + 1;
                        l_lisReservedWords.add(l_strUrl.substring(l_intHead, l_intTail));
                        break;
                    }
                }
            }
        }

        String[] l_strReservedWords = new String[l_lisReservedWords.size()];
        l_lisReservedWords.toArray(l_strReservedWords);

        return l_strReservedWords;
    }

    /**
     * 引数の中に"%%～%%"で区切られた文字列が存在するかどうかを判定し
     * @@param l_strUrl
     * @@return boolean
     */
    private boolean isRequiredFormat(String l_strUrl)
    {
        if (WEB3StringTypeUtility.isEmptyOrBlank(l_strUrl))
        {
            return false;
        }

        final String l_strDoublePercents = "%%";

        int l_intUrlLength = l_strUrl.length();

        for (int i = 0; i < l_intUrlLength - 1; i++)
        {
            if (l_strDoublePercents.equals(l_strUrl.substring(i, i + 2)))
            {
                for (int j = i + 2; j < l_intUrlLength - 1; j++)
                {
                    if (l_strDoublePercents.equals(l_strUrl.substring(j, j + 2)))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 文字列を置換します。<BR>
     * 文字列置換対象の文字列の中にある検索文字列を全て、置換文字列に置き換えた<BR>
     * 文字列を返却します。
     *
     * @@param l_str 文字列置換対象の文字列
     * @@param l_strSearch 検索文字列
     * @@param l_strReplace 置換文字列
     * @@return 文字列置換後の文字列を返す。
     */
    public static String replace(String l_str, String l_strSearch, String l_strReplace)
    {

        if (l_strReplace == null)
        {
            l_strReplace = "";
        }

        for (int i = l_str.indexOf(l_strSearch); i != -1; i = l_str.indexOf(l_strSearch, i + l_strReplace.length()))
        {
            l_str = l_str.substring(0, i) + l_strReplace + l_str.substring(i + l_strSearch.length());
        }
        return l_str;
    }
}@
