head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.42.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccReservationOrderUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 予約注文明細(WEB3SuccReservationOrderUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/17 呉艶飛(中訊) 新規作成
*/

package webbroker3.triggerorder.message;


/**
 * (予約注文明細)<BR>
 * 予約注文明細<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3SuccReservationOrderUnit extends WEB3SuccOrderUnit 
{
    
    /**
     * (単価調整値)<BR>
     * 単価調整値<BR>
     * <BR>
     * ※連続注文の注文単価として約定単価を<BR>
     * 　@使用する場合、その約定単価に<BR>
     * 　@いくらプラス(マイナス)するかの値をセット。<BR>
     */
    public String priceAdjustmentValue = null;
    
    /**
     * (親注文の注文ID)<BR>
     * 親注文の注文ID<BR>
     */
    public String parentOrderId;
    
    /**
     * (親注文内連番)<BR>
     * 親注文内連番<BR>
     */
    public String parentOrderSequentialNo;
    
    /**
     * (注文エラー理由コード)<BR>
     * 注文エラー理由コード<BR>
     * （連続注文発注で失敗した場合のみセットされる）<BR>
     */
    public String orderErrorCode;
        
    /**
     * (予約注文明細)<BR>
     * コンストラクタ<BR>
     * @@roseuid 431F84BD0004
     */
    public WEB3SuccReservationOrderUnit() 
    {
     
    }
}
@
