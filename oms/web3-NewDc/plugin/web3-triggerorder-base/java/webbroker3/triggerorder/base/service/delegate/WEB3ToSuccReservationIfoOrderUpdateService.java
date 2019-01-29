head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.20.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8a44d7ecc762b5b;
filename	WEB3ToSuccReservationIfoOrderUpdateService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP予約注文更新サービス(WEB3ToSuccReservationIfoOrderUpdateService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/03/11 崔遠鵬 (中訊) 新規作成 
*/
package webbroker3.triggerorder.base.service.delegate;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.triggerorder.base.data.RsvIfoOrderActionRow;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;

/**
 * (先物OP予約注文更新サービス)<BR>
 * 先物OP予約注文更新サービスのインタフェース。<BR>
 * @@author 崔遠鵬
 * @@version 1.0
 */
public interface WEB3ToSuccReservationIfoOrderUpdateService extends Service
{
    /**
     * (insert予約注文履歴)<BR>
     * 最新の先物OP予約注文単位テーブルの内容より、<BR>
     * 先物OP予約注文履歴を１レコード作成し登録する。<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 注文ID。<BR>
     * （先物OP予約注文単位.注文IDをセット）
     * @@throws WEB3BaseException
     */
    public void insertReserveOrderAction(long l_lngOrderId) throws WEB3BaseException;

    /**
     * (invalidate予約注文単位)<BR>
     * （invalidateOrderUnit）<BR>
     * <BR>
     * 先物OP予約注文単位行を失効させる。（updateする）<BR>
     * @@param l_rsvIfoOrderUnitRow - (先物OP予約注文単位行)<BR>
     * 先物OP予約注文単位行オブジェクト<BR>
     * <BR>
     * ※DDLより自動生成される。<BR>
     * @@param l_strOrderErrorCode - (発注エラーコード)<BR>
     * 発注エラーコード。<BR>
     * （エラー原因の特定が可能なErrorInfo.error_codeをセット、<BR>
     * 発注エラー以外で失効する場合、nullをセット）
     * @@throws WEB3BaseException
     */
    public void invalidateOrderUnit(
        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow,
        String l_strOrderErrorCode) throws WEB3BaseException;

    /**
     * (invalidateAll予約注文単位)<BR>
     * （invalidateAllOrderUnit）<BR>
     * 引数の（親注文）注文IDに該当する予約注文単位を<BR>
     * 全て失効させる。<BR>
     * @@param l_lngParentOrderId - (親注文の注文ID)<BR>
     * 親注文の注文ID。
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean invalidateAllOrderUnit(long l_lngParentOrderId) throws WEB3BaseException;

    /**
     * (cancel予約注文単位)<BR>
     * （cancelOrderUnit）<BR>
     * 引数の先物OP予約注文単位行を取消する。<BR>
     * @@param l_rsvIfoOrderUnitRow - (先物OP予約注文単位行)<BR>
     * 先物OP予約注文単位行オブジェクト<BR>
     * <BR>
     * ※DDLより自動生成される。
     * @@throws WEB3BaseException
     */
    public void cancelOrderUnit(RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow) throws WEB3BaseException;

    /**
     * (cancelAll予約注文単位)<BR>
     * （cancelAllOrderUnit）<BR>
     * 引数の（親注文）注文IDに該当する予約注文単位を<BR>
     * 全て取消する。<BR>
     * @@param l_lngParentOrderId - (親注文の注文ID)<BR>
     * 親注文の注文ID。
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean cancelAllOrderUnit(long l_lngParentOrderId) throws WEB3BaseException;

    /**
     * (expire予約注文単位)<BR>
     * （expireOrderUnit）<BR>
     * 引数の先物OP予約注文単位行を失効させる。<BR>
     * @@param l_rsvIfoOrderUnitRow - (先物OP予約注文単位行)<BR>
     * 先物OP予約注文単位行オブジェクト<BR>
     * <BR>
     * ※DDLより自動生成される。
     * @@throws WEB3BaseException
     */
    public void expireOrderUnit(RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow) throws WEB3BaseException;

    /**
     * (expireAll予約注文単位)<BR>
     * （expireAllOrderUnit）<BR>
     * 引数の（親注文）注文IDに該当する予約注文単位を<BR>
     * 全て失効させる。<BR>
     * @@param l_lngParentOrderId - (親注文の注文ID)<BR>
     * 親注文の注文ID。
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean expireAllOrderUnit(long l_lngParentOrderId) throws WEB3BaseException;

    /**
     * (get有効予約注文単位一覧)<BR>
     * （getOpenReserveIfoOrderUnits）<BR>
     * <BR>
     * 指定された親注文に紐付く、有効な先物OP予約注文単位行の配列を返却する。<BR>
     * @@param l_lngParentOrderId - (親注文の注文ID)<BR>
     * 親注文の注文ID。
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getOpenReserveIfoOrderUnits(long l_lngParentOrderId) throws WEB3BaseException;

    /**
     * (deleteAll予約注文単位)<BR>
     * （deleteAllOrderUnit）<BR>
     * 引数の（親注文）注文IDに該当する予約注文データを<BR>
     * 全て削除する。<BR>
     * @@param l_lngParentOrderId - (親注文の注文ID)<BR>
     * 親注文の注文ID。
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean deleteAllOrderUnit(long l_lngParentOrderId) throws WEB3BaseException;

    /**
     * (get予約注文単位一覧)<BR>
     * （getReserveIfoOrderUnits）<BR>
     * <BR>
     * 指定された親注文に紐付く、先物OP予約注文単位行の配列を返却する。<BR>
     * @@param l_lngParentOrderId - (親注文の注文ID)<BR>
     * 親注文の注文ID。
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getReserveIfoOrderUnits(long l_lngParentOrderId) throws WEB3BaseException;

    /**
     * (update予約注文データ)<BR>
     * （updateOrderData）<BR>
     * <BR>
     * 指定された注文オブジェクトを使用し、 <BR>
     * QueryProcessorにより予約注文データ類の更新を行う。<BR>
     * @@param l_rsvIfoOrderUnitRow - (先物OP予約注文単位行)<BR>
     * 先物OP予約注文単位行
     * @@param l_rsvIfoOrderActionRow - (先物OP予約注文履歴行)<BR>
     * 先物OP予約注文履歴行
     * @@throws WEB3BaseException
     */
    public void updateReserveOrderData(
        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow, 
        RsvIfoOrderActionRow l_rsvIfoOrderActionRow) throws WEB3BaseException;
}
@
