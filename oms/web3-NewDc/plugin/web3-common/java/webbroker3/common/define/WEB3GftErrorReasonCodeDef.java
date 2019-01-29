head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.56.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GftErrorReasonCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3GftErrorReasonCodeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/24 王蘭芬(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * GFTエラー理由コード　@定数定義インタフェイス
 *                                                                     
 * @@author 王蘭芬(中訊)
 * @@version 1.0
 */
public interface WEB3GftErrorReasonCodeDef
{
    /**
     *  正常
     */
    public static final String NORMAL = "0000";

    /**
     *  送受信区分チェックエラー
     */
    public static final String SENDRCV_ERROR = "0001";    

    /**
     *  パラメータ妥当性チェックエラー
     */
    public static final String PARAM_VALIDITY_ERROR = "0002";    

    /**
     *  パラメータ一致チェックエラー
     */
    public static final String PARAM_MISMATCH_ERROR = "0003";    

    /**
     *  受付結果コードチェックエラー
     */
    public static final String RESULT_CODE_ERROR = "0004";    

    /**
     *  その他エラー
     */
    public static final String OTHER_ERROR = "9001";    
}
@
