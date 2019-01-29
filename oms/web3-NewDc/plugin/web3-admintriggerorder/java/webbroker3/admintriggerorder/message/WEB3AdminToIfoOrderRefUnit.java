head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToIfoOrderRefUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・先物OP注文照会Unit(WEB3AdminToIfoOrderRefUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/15　@余新敏(中訊) 新規作成
                 : 2006/08/23　@肖志偉(中訊) 仕様変更No.66
*/

package webbroker3.admintriggerorder.message;

/**
 * (トリガー注文管理者・先物OP注文照会Unit)<BR>
 * 
 * @@author 余新敏
 * @@version 1.0
 */
public class WEB3AdminToIfoOrderRefUnit extends WEB3AdminToOrderRefCommonUnit
{
    
    /**
     * (口座区分)<BR>
     * 口座区分<BR>
     * <BR>
     * 0：　@オプション買建口座 <BR>
     * 1：　@先物オプション口座<BR>
     */
    public String taxType;
    
    /**
     * (指数種別)<BR>
     * 指数種別<BR>
     * <BR>
     * 0005：　@TOPIX <BR>
     * 0018：　@日経225 <BR>
     * 0016：　@日経300 <BR>
     * 0019：　@ミニ日経225<BR>
     */
    public String targetProductCode;
    
    /**
     * (限月)<BR>
     * 限月<BR>
     * (YYYYMM)<BR>
     */
    public String delivaryMonth;
    
    /**
     * (行使価格)<BR>
     * 行使価格<BR>
     */
    public String strikePrice = null;
    
    /**
     * (オプション商品区分)<BR>
     * オプション商品区分<BR>
     * <BR>
     * P：プットオプション <BR>
     * C：コールオプション <BR>
     * <BR>
     * ※先物の場合は、nullをセット。<BR>
     */
    public String opProductType = null;
    
    /**
     * (発注条件単価区分)<BR>
     * 発注条件単価区分<BR>
     * <BR>
     * 0：　@原資産価格　@<BR>
     * 1：　@プレミアム <BR>
     * <BR>
     * ※条件注文種別が"逆指値注文"または"Ｗ指値"の場合設定<BR>
     */
    public String orderCondPriceDiv = null;
    
    /**
     * (トリガー注文管理者・先物OP注文照会Unit)<BR>
     * コンストラクタ<BR>
     * @@roseuid 43E9BEE0000A
     */
    public WEB3AdminToIfoOrderRefUnit() 
    {
     
    }
}
@
