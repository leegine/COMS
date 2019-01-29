head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.33.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3CatDelimitterDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 連結項目デリミッタ(WEB3CatDelimitterDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/09 張宝楠 (中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 連結項目デリミッタ 定数定義インタフェイス
 *
 * @@author 張宝楠
 * @@version 1.0
 */
public interface WEB3CatDelimitterDef
{

    /**
     * 0：なし
     */
    public final static String WITHOUT  = "0";

    /**
     * 1：半角SPACE
     */
    public final static String HALF_SPACE  = "1";

    /**
     * 2：全角SPACE
     */
    public final static String FULL_SPACE  = "2";

    /**
     * 3：ハイフン（’-’）
     */
    public final static String HYPHEN  = "3";

    /**
     * 4：郵便番号
     */
    public final static String ZIP_CODE  = "4";
}
@
