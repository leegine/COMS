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
filename	WEB3GentradeBatoServiceRegServiceResultDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （電子鳩システム返却値）電子鳩承諾チェック　@定数定義インタフェイス(WEB3GentradeBatoServiceRegServiceResultDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/15 仲川(ＳＲＡ) 新規作成
*/
package webbroker3.gentrade.define;

/**
 * （電子鳩システム）電子鳩承諾チェックサービス戻り値　@定数定義インタフェイス<br />
 * <br />
 * 【電子鳩承諾チェックサービス】<br />
 * ターゲットネームスペース：Bato_Trade_Bal_Check_Servic<br />
 * サービス名：BatoServiceRegService<br />
 * メソッド名：chkSrvRegist<br />
 * 
 */
public interface WEB3GentradeBatoServiceRegServiceResultDef
{
    /**
     * 0： 未同意顧客 
     */
    public static final String NOT_AGREEMENT = "0";
    
    /**
     * 1： 同意顧客 
     */
    public static final String AGREEMENT = "1";
    
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
     * AuthenticationError： 認証エラー 
     */
    public static final String ERR_AUTHENTICATE = "AuthenticationError";

    /**
     * InvalidBussinessTime： 受付時間外 
     */
    public static final String ERR_TRADING_TIME = "InvalidBussinessTime";

    /**
     * DBError： DBエラー 
     */
    public static final String ERR_DB_ACCESS = "DBError";

}
@
