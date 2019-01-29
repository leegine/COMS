head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderCarryOverUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文繰越一件サービス(WEB3EquityOrderCarryOverUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 鄒政 (中訊) 新規作成
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/

package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3SystemLayerException;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;

/**
 * （注文繰越一件サービス）。<BR>
 * <BR>
 * （トランザクション属性：TransactionalInterceptor.TX_JOIN_EXISTING）
 * @@version 1.0
 */
public interface WEB3EquityOrderCarryOverUnitService extends Service 
{
   
    /**
     * (insert繰越注文)<BR>
     * 引数に指定された注文単位オブジェクトから、
     * 繰越の新規注文データ（現物株式 or 新規建 or 返済）を作成する。
     * @@param l_orderUnit - 注文単位<BR>
     * 繰越対象の注文単位オブジェクト。<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 4137CDD9011C
     */
    public boolean insertCarryOverOrder(OrderUnit l_orderUnit) throws WEB3BaseException;
    
    /**
     * (update繰越元注文)<BR>
     * 発注審査でエラーとなった繰越元注文の注文エラー理由コードなどを更新する。<BR>
     * <BR>
     * １）　@繰越元注文の注文エラー理由コード をupdateする。<BR>
     * <BR>
     * １－１）　@以下の条件に該当する繰越元注文の注文単位レコードをupdateする。<BR>
     * 　@　@<条件><BR>
     * 　@　@　@注文単位テーブル.注文単位ID = パラメータ.注文単位.注文単位ID<BR>
     * <BR>
     * 　@　@<更新内容><BR>
     * 　@　@　@注文単位レコード.注文エラー理由コード = <BR>
     * パラメータ.注文エラー理由コード<BR>
     * 　@　@　@注文単位レコード.更新日付 = 現在日時<BR>
     * <BR>
     * １－２）　@以下の条件に該当する繰越元注文の注文履歴の、<BR>
     * 　@　@　@　@　@最終履歴レコードの注文エラー理由コード をupdateする。<BR>
     * <BR>
     * 　@　@<条件><BR>
     * 　@　@履歴テーブル.注文単位ID　@＝　@<BR>
     * パラメータ.注文単位.注文単位ID　@かつ<BR>
     * 　@　@履歴テーブル.注文履歴番号　@＝　@<BR>
     * パラメータ.注文単位.注文履歴最終通番<BR>
     * <BR>
     * 　@　@<更新内容><BR>
     * 　@　@履歴レコード.注文エラー理由コード　@＝　@<BR>
     * パラメータ.注文エラー理由コード<BR>
     * 　@　@履歴レコード.更新日付　@＝　@現在日時<BR>
     * <BR>
     * １－３）　@以下の条件に該当する、<BR>
     * 繰越元注文の注文（ヘッダ）の更新日時をupdateする。<BR>
     * <BR>
     * 　@　@<条件><BR>
     * 　@　@注文（ヘッダ）テーブル.注文ID　@＝　@パラメータ.注文単位.注文ID<BR>
     * <BR>
     * 　@　@<更新内容><BR>
     * 　@　@注文（ヘッダ）レコード.更新日付　@＝　@現在日時<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 繰越元の注文単位
     * @@param l_strOrderErrReasonCode - (注文エラー理由コード)<BR>
     * 注文エラー理由
     * @@roseuid 4121D908036F
     */
    public void updateOriginalOrder(
        EqTypeOrderUnit l_orderUnit,
        String l_strOrderErrReasonCode)
        throws WEB3SystemLayerException;
}
@
