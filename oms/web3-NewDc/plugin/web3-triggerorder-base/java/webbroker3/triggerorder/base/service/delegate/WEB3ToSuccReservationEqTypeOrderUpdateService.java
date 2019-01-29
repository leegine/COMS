head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.21.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8a44d7ecc762b5b;
filename	WEB3ToSuccReservationEqTypeOrderUpdateService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式予約注文更新サービス(WEB3ToSuccReservationEqTypeOrderUpdateService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/13 郭英 (中訊) 新規作成 
Revesion History : 2007/4/25 劉立峰 (中訊) モデルNO.232 
*/

package webbroker3.triggerorder.base.service.delegate;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.triggerorder.base.data.RsvEqOrderActionRow;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;


/**
 * (株式予約注文更新サービス)<BR>
 * 株式予約注文更新サービスのインタフェース。
 *   
 * @@author 郭英
 * @@version 1.0
 */
public interface WEB3ToSuccReservationEqTypeOrderUpdateService extends Service 
{
    
    /**
     * (insert予約注文履歴)<BR>
     * 最新の株式予約注文単位テーブルの内容より、<BR>
     * 株式予約注文履歴を１レコード作成し登録する。
     * @@param l_lngOrderId - (注文ID)<BR>
     * 注文ID。<BR>
     * （株式予約注文単位.注文IDをセット）
     * @@throws WEB3BaseException
     * @@roseuid 433A22CA0070
     */
    public void insertReserveOrderAction(long l_lngOrderId) throws WEB3BaseException;
    
    /**
     * (invalidate予約注文単位)<BR>
     * （invalidateOrderUnit）<BR>
     * <BR>
     * 株式予約注文単位行を失効させる。（updateする）
     * @@param l_rsvEqOrderUnitRow - (株式予約注文単位行)<BR>
     * 株式予約注文単位行オブジェクト<BR>
     * <BR>
     * ※DDLより自動生成される。
     * @@param l_strOrderErrorCode - (発注エラーコード)<BR>
     * 発注エラーコード。<BR>
     * （エラー原因の特定が可能なErrorInfo.error_codeをセット、<BR>
     * 発注エラー以外で失効する場合、nullをセット）
     * @@throws WEB3BaseException
     * @@roseuid 433A22CA007D
     */
    public void invalidateOrderUnit(RsvEqOrderUnitRow l_rsvEqOrderUnitRow, String l_strOrderErrorCode) throws WEB3BaseException;
    
    /**
     * (invalidateAll予約注文単位)<BR>
     * （invalidateAllOrderUnit）<BR>
     * 引数の（親注文）注文IDに該当する予約注文単位を<BR>
     * 全て失効させる。<BR>
     * @@param l_lngParentOrderId - (親注文の注文ID)<BR>
     * 親注文の注文ID。<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean invalidateAllOrderUnit(long l_lngParentOrderId) throws WEB3BaseException;
    
    /**
     * (cancel予約注文単位)<BR>
     * （cancelOrderUnit）<BR>
     * 引数の株式予約注文単位行を取消する。<BR>
     * @@param l_rsvEqOrderUnitRow - (株式予約注文単位行)<BR>
     * 株式予約注文単位行オブジェクト<BR>
     * <BR>
     * ※DDLより自動生成される。<BR>
     * @@throws WEB3BaseException
     */
    public void cancelOrderUnit(RsvEqOrderUnitRow l_rsvEqOrderUnitRow) throws WEB3BaseException;
    
    /**
     * (cancelAll予約注文単位)<BR>
     * （cancelAllOrderUnit）<BR>
     * 引数の（親注文）注文IDに該当する予約注文単位を<BR>
     * 全て取消する。<BR>
     * @@param l_lngParentOrderId - (親注文の注文ID)<BR>
     * 親注文の注文ID。<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean cancelAllOrderUnit(long l_lngParentOrderId) throws WEB3BaseException;
    
    /**
     * (expire予約注文単位)<BR>
     *（expireOrderUnit）<BR>
     * 引数の株式予約注文単位行を失効させる。<BR>
     * @@param l_rsvEqOrderUnitRow - (株式予約注文単位行)<BR>
     * 株式予約注文単位行オブジェクト<BR>
     * <BR>
     * ※DDLより自動生成される。<BR>
     * @@throws WEB3BaseException
     */
    public void expireOrderUnit(RsvEqOrderUnitRow l_rsvEqOrderUnitRow) throws WEB3BaseException;
    
    /**
     * (expireAll予約注文単位)<BR>
     * （expireAllOrderUnit）<BR>
     * 引数の（親注文）注文IDに該当する予約注文単位を<BR>
     * 全て失効させる。<BR>
     * @@param l_lngParentOrderId - (親注文の注文ID)<BR>
     * 親注文の注文ID。<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean expireAllOrderUnit(long l_lngParentOrderId) throws WEB3BaseException;
    
    /**
     * (get有効予約注文単位一覧)<BR>
     * （getOpenReserveEqtypeOrderUnits）<BR>
     * <BR>
     * 指定された親注文に紐付く、有効な株式予約注文単位行の配列を返却する。
     * @@param l_lngParentOrderId - (親注文の注文ID)<BR>
     * 親注文の注文ID。
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 433A22CA009E
     */
    public List getOpenReserveEqtypeOrderUnits(long l_lngParentOrderId) throws WEB3BaseException;
    
    /**
     * (deleteAll予約注文単位)<BR>
     * （deleteAllOrderUnit）<BR>
     * 引数の（親注文）注文IDに該当する予約注文データを<BR>
     * 全て削除する。<BR>
     * @@param l_lngParentOrderId - （親注文の注文ID）<BR>
     * 親注文の注文ID。<BR>
     * 
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 4355E27F034B
     */
    public boolean deleteAllOrderUnit(long l_lngParentOrderId) throws WEB3BaseException;
    
    /**
     * (get予約注文単位一覧)<BR>
     * （getReserveEqtypeOrderUnits）<BR>
     * <BR>
     * 指定された親注文に紐付く、株式予約注文単位行の配列を返却する。
     * @@param l_lngParentOrderId - (親注文の注文ID)<BR>
     * 親注文の注文ID。
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getReserveEqtypeOrderUnits(long l_lngParentOrderId) throws WEB3BaseException;
    
    /**
     * (update予約注文データ)<BR>
     * (updateReserveOrderData)<BR>
     * <BR>
     * 指定された注文オブジェクトを使用し、QueryProcessorにより予約注文データ類の更新を行う。<BR>
     * @@param l_rsvEqOrderUnitRow - (株式予約注文単位行)<BR>
     * 株式予約注文単位行。
     * @@param l_rsvEqOrderActionRow - (株式予約注文履歴行)<BR>
     * 株式予約注文履歴行。
     * @@throws WEB3BaseException
     */
    public void updateReserveOrderData(RsvEqOrderUnitRow l_rsvEqOrderUnitRow, 
        RsvEqOrderActionRow l_rsvEqOrderActionRow) throws WEB3BaseException;

}
@
