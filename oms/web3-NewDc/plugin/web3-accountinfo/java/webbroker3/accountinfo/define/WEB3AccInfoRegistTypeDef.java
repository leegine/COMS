head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.26.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoRegistTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 登録タイプ 定数定義インタフェイス(WEB3AccInfoRegistTypeDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/2/1 齊珂 (中訊) 新規作成
*/

package webbroker3.accountinfo.define;

/**
 * 登録タイプ 定数定義インタフェイス
 * 
 * @@author 齊珂 (中訊)
 * @@version 1.0
 */
public interface WEB3AccInfoRegistTypeDef
{
    /**
     * 0： 口座開設条件設定
     */
    public final static String ACCOPEN_CONDITION = "0";
    
    /**
     * 1： 個別顧客指定
     */
    public final static String INDIVIDUAL = "1";
    
    /**
     * 2： 強制個別顧客指定
     */
    public final static String FORCE_INDIVIDUAL = "2";
}
@
