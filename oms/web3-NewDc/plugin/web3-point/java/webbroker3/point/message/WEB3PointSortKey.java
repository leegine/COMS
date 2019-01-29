head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.58.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ポイント交換一覧ソートキー(WEB3PointSortKey.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 鄭海良(中訊) 新規作成
*/
package webbroker3.point.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (ポイント交換一覧ソートキー)<BR>
 * ポイント交換一覧ソートキークラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3PointSortKey extends Message
{
    
    /**
     * (キー項目)<BR>
     * キー項目<BR>
     * <BR>
     *   ・部店コード<BR>
     *   ・顧客コード<BR>
     *   ・景品番号<BR>
     *   ・景品名<BR>
     *   ・申込日時<BR>
     *   ・受付区分<BR>
     *   ・更新日時<BR>
     *   ・受付ユーザ<BR>
     *   ・取消区分<BR>
     */
    public String keyItem;
    
    /**
     * (昇順/降順)<BR>
     * A： 昇順<BR>
     * D： 降順<BR>
     */
    public String ascDesc;
    
    /**
     * @@roseuid 41D1254B030D
     */
    public WEB3PointSortKey() 
    {
     
    }
}
@
