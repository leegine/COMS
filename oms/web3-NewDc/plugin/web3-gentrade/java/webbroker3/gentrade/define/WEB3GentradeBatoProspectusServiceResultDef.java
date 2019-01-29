head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeBatoProspectusServiceResultDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （電子鳩システム返却値）目論見書閲覧チェック　@定数定義インタフェイス(WEB3GentradeBatoProspectusServiceResultDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/15 仲川(ＳＲＡ) 新規作成
*/
package webbroker3.gentrade.define;

/**
 * （電子鳩システム）目論見書閲覧チェックサービス戻り値　@定数定義インタフェイス<br />
 * <br />
 * 【目論見書閲覧チェックサービス】<br />
 * ターゲットネームスペース：Bato_HistCheck_Service<br />
 * サービス名：BatoProspectusService<br />
 * メソッド名：chkHistry<br />
 * 
 */
public interface WEB3GentradeBatoProspectusServiceResultDef
{
    /**
     * 0： 履歴あり 
     */
    public static final String HISTORY = "0";
    
    /**
     * 1： 履歴なし
     */
    public static final String NO_HISTORY = "1";
    
    /**
     * 2： 履歴管理なし 
     */
    public static final String CHECK_OFF = "2";
    

    /**
     * InvalidCompCode： 会社コードフォーマットエラー 
     */
    public static final String ERR_INSTITUTION_CODE = "InvalidCompCode";
    
    /**
     * InvalidBranCode： 部店コードフォーマットエラー 
     */
    public static final String ERR_BRANCH_CODE = "InvalidBranCode";
    
    /**
     * InvalidCustCode： 顧客コードフォーマットエラー 
     */
    public static final String ERR_ACCOUNT_CODE = "InvalidCustCode";

    /**
     * InvalidTypeCode： 種別コードフォーマットエラー 
     */
    public static final String ERR_TYPE_CODE = "InvalidTypeCode";

    /**
     * InvalidFundCode： 銘柄コードフォーマットエラー 
     */
    public static final String ERR_PRODUCT_CODE = "InvalidFundCode";

    /**
     * AuthenticationError： 認証エラー 
     */
    public static final String ERR_AUTHENTICATE = "AuthenticationError";

    /**
     * InvalidBussinessTime： 受付時間外 
     */
    public static final String ERR_TRADING_TIME = "InvalidBussinessTime";

    /**
     * NoProspectus： 目論見書未登録 
     */
    public static final String ERR_NO_PROSPEC = "NoProspectus";

    /**
     * DBError： DBエラー 
     */
    public static final String ERR_DB_ACCESS = "DBError";

}
@
