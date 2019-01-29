head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.59.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyConditionUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信定時定額買付条件行(WEB3MutualFixedBuyConditionUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/26 徐宏偉 (中訊) 新規作成
                 : 2006/07/22 徐宏偉 (中訊) 仕様変更 モデル 460
Revesion History : 2008/07/08 王志葵 (中訊) 仕様変更 モデルNo.604,610
*/
package webbroker3.mf.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (投信定時定額買付条件行)<BR>
 * 投信定時定額買付条件行<BR>
 * 
 * @@author 徐宏偉(中訊)
 * @@version 1.0 
 */
public class WEB3MutualFixedBuyConditionUnit extends Message  
{
    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     */
    public String mutualProductCode;
  
    /**
     * (銘柄名)<BR>
     * 銘柄名<BR>
     */
    public String mutualProductName;
 
    /**
     * (投信銘柄カテゴリーコード)<BR>
     * 投信銘柄カテゴリーコード<BR>
     */
    public String categoryCode;
 
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
     * (銘柄表示順序)<BR>
     * 銘柄表示順序<BR> 
     * 非表示　@APでソートに使用する。<BR>
     */
    public String displayOrder;
   
    /**
     * (適用開始年月)<BR>
     * 適用開始年月<BR> 
     */
    public Date validStartDate;    
    
    /**
     * (更新日時)<BR>
     * 更新日時<BR>
     */
    public Date updateDate;
    
    /**
     * (口座引落年月)<BR>
     * 口座引落年月<BR>
     */
    public Date debitAccountYM;

    /**
     * (確定引落金額（積み増し）)<BR>
     * 確定引落金額（積み増し）<BR>
     */
    public String definiteIncreaseBuyAmount;

    /**
     * (目論見書閲覧チェック)<BR>
     * 目論見書閲覧チェック<BR>
     */
    public String checkResult;

    /**
     * (一時停止中フラグ)<BR>
     * 一時停止中フラグ<BR>
     */
    public boolean suspensionFlag;

    /**
     * (sonar送信チェック)<BR>
     * sonar送信チェック<BR>
     */
    public String sonarSendCheck;

    /**
     * (投信定時定額買付条件行のインスタンスを生成する。)<BR>
     * デフォルトコンストラクタ<BR>
     */
    public WEB3MutualFixedBuyConditionUnit()
    {
    }
}




@
