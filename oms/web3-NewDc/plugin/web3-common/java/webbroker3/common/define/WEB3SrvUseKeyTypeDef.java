head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.31.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SrvUseKeyTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 利用キー種別区分( WEB3SrvUseKeyType.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/17 郭英(sinocom) 新規作成
Revesion History : 2009/04/24 趙林鵬 (中訊)【サービス利用】仕様変更管理台帳 ＤＢレイアウトNo.034
*/
package webbroker3.common.define;

/**
 * 利用キー種別区分
 *
 * @@author 郭英
 * @@version 1.0
 */
public interface WEB3SrvUseKeyTypeDef
{
    /**
     * 0：第２URL 
     */
    public final static String URL2 = "0";

    /**
     * 1：ハッシュ値
     */
    public final static String HSAH_VALUE = "1";
    
    /**
     * 2：送信パラメータ
     */
    public final static String SEND_PARAM = "2";
    
    /**
     * 3：送信方法@区分
     */
    public final static String SEND_HOW_TO_DIV = "3";
    
    /**
     * 4：ハッシュ計算方式区分
     */
    public final static String HASH_CAL_HOW_TO_DIV = "4";
    
    /**
     * 5：ハッシュ計算手順区分
     */
    public final static String HASH_CAL_ORDER_DIV = "5";
    
    /**
     * 6：送信パラメータ区分
     */
    public final static String SEND_PARAM_DIV = "6";
    
    /**
     * 7：暗号化顧客コード区分
     */
    public final static String CRYPT_ACCOUNT_CODE_DIV = "7";

    /**
     * 8：付加区分
     */
    public final static String ADDITION_DIV = "8";
}
@
