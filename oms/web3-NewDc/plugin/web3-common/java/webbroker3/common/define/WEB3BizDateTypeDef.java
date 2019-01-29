head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.43.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3BizDateTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 営業日区分 定数定義インタフェイス(WEB3BizDateTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/09 今井　@高史(SRA) 新規作成
Revesion History : 2007/12/18 孟暁シン (中訊) 【共通】仕様変更管理台帳No572(ＤＢレイアウト)を対応
*/
package webbroker3.common.define;

/**
 * 営業日区分　@定数定義インタフェイス
 *
 * @@author 今井　@高史(SRA)
 * @@version 1.0
 */
public interface WEB3BizDateTypeDef
{

    /**
     * 0 : 非営業日
     */
    public static final String NO_BIZ_DATE = "0";

    /**
     * 1 : 終日営業日
     */
    public static final String BIZ_DATE = "1";
    
    /**
     * 2 : 半日(午前ﾉﾐ) 
     */
    public static final String BIZ_DATE_AM = "2";

    /**
     * 3 : 半日(午後ﾉﾐ)
     */
    public static final String BIZ_DATE_PM = "3";
    
    /**
     * 4：終日営業日（午前のみ）
     */
    public static final String ALL_BIZ_DATE_AM = "4";
    
    /**
     * 5：終日営業日（午後のみ）
     */
    public static final String ALL_BIZ_DATE_PM = "5";
}
@
