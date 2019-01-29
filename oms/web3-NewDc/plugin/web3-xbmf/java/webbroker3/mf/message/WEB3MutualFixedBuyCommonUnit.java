head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.00.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyCommonUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信定時定額買付共通情報(WEB3MutualFixedBuyCommonUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/26 徐宏偉 (中訊) 新規作成
Revesion History : 2008/07/14 安陽 (中訊) 仕様変更モデル611
*/
package webbroker3.mf.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;
/**
 * (投信定時定額買付共通情報)<BR>
 * 投信定時定額買付共通情報
 * 
 * @@author 徐宏偉(中訊)
 * @@version 1.0 
 */

public class WEB3MutualFixedBuyCommonUnit extends Message  
{

    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     */
    public String mutualProductCode;
  
    /**
     * (買付金額(月々))<BR>
     * 買付金額(月々)<BR>
     */
    public String monthlyBuyAmount;
   
    /**
     * (買付金額(積み増し))<BR>
     * 買付金額(積み増し)<BR>
     */
    public String increaseBuyAmount;
   
    /**
     * (電子鳩チェックフラグ)<BR>
     * 電子鳩チェックフラグ<BR> 
     */
    public boolean batoCheckFlag;
   
    /**
     * (種別コード)<BR>
     * 種別コード<BR> 
     */
    public String typeCode;    

    /**
     * (変更区分)<BR>
     * 変更区分<BR>
     */
    public String changeDiv;

    /**
     * (適用開始年月)<BR>
     * 適用開始年月<BR>
     */
    public Date validStartDate;

    /**
     * (デフォルトコンストラクタ)<BR>
     * デフォルトコンストラクタ<BR>
     */
    public WEB3MutualFixedBuyCommonUnit()
    {
    }
}
@
