head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.38.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AccountLockErrorCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 顧客ロックエラーコード定数定義インターフェイス(WEB3AccountLockErrorCodeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/24 凌建平(sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * 顧客ロックエラーコード定数定義インターフェイス
 *
 * @@author 凌建平
 * @@version 1.0
 */
public interface WEB3AccountLockErrorCodeDef
{

    /**
     * 1900：二重登録エラー
     */
    public static final String DOUBLE_REGI_ERROR = "1900";

    /**
     * FF51：抹消済エラー
     */
    public static final String ERASED_ERROR = "FF51";

    /**
     * FF72：ロックエラー
     */
    public static final String LOCK_ERROR = "FF72";

    /**
     * FF70：解除エラー
     */
    public static final String LOCK_OFF_ERROR = "FF70";

    /**
     * FF73：認可エラー
     */
    public static final String PERMISSION_ERROR = "FF73";

    /**
     * 1E00：取消区分エラー
     */
    public static final String CANCEL_DIV_ERROR = "1E00";

    /**
     * 2700：該当無し
     */
    public static final String NO_DATA_ERROR = "2700";
} @
