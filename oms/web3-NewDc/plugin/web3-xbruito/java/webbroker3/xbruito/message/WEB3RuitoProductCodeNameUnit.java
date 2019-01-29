head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoProductCodeNameUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累積投資銘柄一覧用データクラス(WEB3RuitoProductCodeName)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 周 勇 (中訊) 新規作成
                   2004/12/03 韋念瓊 (中訊) 残対応
*/
package webbroker3.xbruito.message;

import com.fitechlabs.xtrade.kernel.message.Message;
/**
 * 累積投資銘柄一覧用データクラス
 */
public class WEB3RuitoProductCodeNameUnit extends Message
{

    /**
     * 銘柄名に対応した銘柄コード
     */
    public String ruitoProductCode;

    /**
     * 銘柄コードに対応した銘柄名
     */
    public String ruitoProductName;

    /**
     * 買付可能区分
     * null：取引可能 
     * １：システム取引停止エラー 
     * ２：受付時間エラー 
     * ３：取引停止中 
     * ４：緊急停止中
     */
    public String buyPosDiv;
    
    /**
     * デフォルトコンストラクタ
     * @@roseuid 40C96B670138
     */
    public WEB3RuitoProductCodeNameUnit()
    {

    }
}
@
