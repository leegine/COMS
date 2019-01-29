head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.25.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoContactPriorityDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 連絡先優先順位(WEB3AccInfoContactPriorityDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/22 凌建平 (中訊) 新規作成
*/

package webbroker3.accountinfo.define;

/**
 * 連絡先優先順位 定数定義インタフェイス
 *
 * @@author 凌建平
 * @@version 1.0
 */
public interface WEB3AccInfoContactPriorityDef
{
    /**
     * 0：なし
     */
    public final static String DEFAULT = "0";

    /**
     * 1：自宅/本社代表番号
     */
    public final static String HOME_COMPANY_NUMBER = "1";
    
    /**
     * 2：勤務先/取引責任者勤務先
     */
    public final static String OFFICE_TRADER_DUTY = "2";

    /**
     * 3：携帯・その他/その他連絡先
     */
    public final static String MOBILE_OTHER_CONTACT = "3";

    /**
     * null：未設定
     */
    public final static String NULL = "null";
}@
