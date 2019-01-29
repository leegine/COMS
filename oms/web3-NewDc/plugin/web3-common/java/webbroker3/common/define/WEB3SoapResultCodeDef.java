head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.38.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SoapResultCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 受付結果コード（SOAP）定数定義インタフェイス(WEB3SoapResultCodeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/01 凌建平(中訊) 新規作成
Revesion History : 2008/09/09 陸文静 (中訊) 仕様変更ＤＢレイアウトNo.144
*/
package webbroker3.common.define;

/**
 * 受付結果コード（SOAP）定数定義インタフェイス
 *
 * @@author 凌建平(中訊)
 * @@version 1.0
 */
public interface WEB3SoapResultCodeDef                
{
    /**
     *  0: 正常終了
     */
    public static final String  NORMAL = "0";
    
    /**
     *  1: 正常終了（取引所入金受付不可）
     */
    public static final String  NORMAL_CANNOT_IN = "1";

    /**
     *  2: パラメタエラー
     */
    public static final String  PARAM_ERROR = "2";
    
    /**
     *  3: 重複エラー
     */
    public static final String  REPEAT_ERROR = "3";

    /**
     *  1001: 利用者コード不正
     */
    public static final String  USER_CODE_ERROR = "1001";

    /**
     *  1003: 顧客コード不正
     */
    public static final String  ACCOUNT_CODE_ERROR = "1003";
    
    /**
     *  3007: 重複登録エラー
     */
    public static final String  DUP_SUBMIT_ERROR = "3007";
    
    /**
     *  3008: 科目コード不正
     */
    public static final String  SUBJECT_CODE_ERROR = "3008";

    /**
     *  3009: 入金額不正
     */
    public static final String  IN_AMOUNT_ERROR = "3009";

    /**
     *  6001: 稼動時間外エラー
     */
    public static final String  WORK_TIME_OUT_ERROR = "6001";

    /**
     *  27: その他エラー）
     */
    public static final String  OTHER_ERROR = "27";
 
    /**
     *  9991: 接続エラー（システムエラー）
     */
    public static final String  CONNECT_ERROR = "9991";

    /**
     *  9992: 接続タイムアウトエラー（システムエラー）
     */
    public static final String  CONNECT_TIME_OUT_ERROR = "9992";
}
@
