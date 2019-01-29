head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.55.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ValidateTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : チェックタイプ(WEB3ValidateTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/09 張宝楠 (中訊) 新規作成
Revesion History : 2007/11/26 栄イ(中訊) 口座開設仕様変更・ＤＢレイアウト048
*/

package webbroker3.common.define;

/**
 * チェックタイプ 定数定義インタフェイス
 *
 * @@author 張宝楠
 * @@version 1.0
 */
public interface WEB3ValidateTypeDef
{

    /**
     *  0：DEFAULT（顧客申込）
     */
    public final static String DEFAULT  = "0";

    /**
     * 1：管理者申込
     */
    public final static String ADMINISTRATOR_REGIST  = "1";

    /**
     * 2：管理者申込更新
     */
    public final static String ADMINISTRATOR_REGIST_UPDATE  = "2";

    /**
     * 3：伝票作成
     */
    public final static String VOUCHER_CREATED  = "3";

    /**
     * 4：口座開設アップロード
     */
    public final static String ACCOUNT_OPEN_UPLOAD  = "4";

}@
