head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderCarryOverSkipUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文繰越スキップ銘柄通知一件サービスImpl(WEB3EquityOrderCarryOverSkipPartServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/27 鄒政 (中訊) 新規作成
Revesion History : 2004/09/21 盧法@旭(中訊) 修正
Revesion History : 2004/12/13 中尾寿彦(SRA) 残案件対応による修正
Revesion History : 2005/01/06 岡村和明(SRA) JavaDoc修正
Revesion History : 2006/11/20 唐性峰　@(中訊)モデルNo.1043
Revesion History : 2007/01/31 唐性峰　@(中訊)モデルNo.1117
Revesion History : 2008/12/05 張少傑　@(中訊)モデルNo.1326
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeClosingContractSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeContractSettleOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.data.HostEqtypeOrderAllRow;
import webbroker3.equity.data.HostEquityCarryoverSkipParams;
import webbroker3.equity.data.OrderCarryoverSkipProdDao;
import webbroker3.equity.data.OrderCarryoverSkipProdParams;
import webbroker3.equity.service.delegate.WEB3EquityOrderCarryOverSkipObjectCheckService;
import webbroker3.equity.service.delegate.WEB3EquityOrderCarryOverSkipUnitService;
import webbroker3.equity.service.delegate.WEB3EquityOrderCarryOverUnitService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CarryoverEndTypeDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3HostStatusDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3RegistDivisionDef;
import webbroker3.common.define.WEB3SkipMarketCodeDef;
import webbroker3.common.define.WEB3SkipRegistTypeDef;

import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.OrderexecutionEndParams;
import webbroker3.gentrade.data.OrderexecutionEndRow;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationEqTypeOrderUpdateService;

/**
 * （株式注文繰越スキップ銘柄通知一件サービスImpl）。
 * @@version 1.0
 */
public class WEB3EquityOrderCarryOverSkipUnitServiceImpl
    implements WEB3EquityOrderCarryOverSkipUnitService
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityOrderCarryOverSkipUnitServiceImpl.class);

    private final String ALL_PRODUCT = "ALL";

    /**
     * @@roseuid 40B2D544007F
     */
    public WEB3EquityOrderCarryOverSkipUnitServiceImpl()
    {

    }

    /**
     * (注文繰越結果更新)<BR>
     * スキップ銘柄の登録／登録抹消通知内容、<BR>
     * 及び注文繰越処理の実行状況（未処理／処理済）により、<BR>
     * 必要な繰越処理結果の更新を行う。<BR>
     * <BR>
     * １）　@引数の出来終了Listから、以下の条件に該当するレコードの<BR>
     * 　@「注文繰越処理区分」を取得する。<BR>
     * <BR>
     * 　@　@　@＜取得条件＞<BR>
     * 　@　@　@証券会社コード＝引数の通知キューParams.証券会社コード<BR>
     * 　@　@　@かつ　@銘柄タイプ＝株式<BR>
     * <BR>
     * ２）　@this.updateスキップ銘柄テーブル(引数の注文繰越スキップ銘柄通知キューParams)をコールする。<BR>
     * <BR>
     * ３）  該当データなしの場合、または 取得したデータの注文繰越処理区分＝未処理 の場合は、<BR>
     *       何もせずにreturnする。<BR>   
     * <BR>
     * ４）　@取得したデータの注文繰越処理区分＝処理済 の場合は、<BR>
     * 　@引数の注文繰越スキップ銘柄通知キューParams.スキップ登録区分の値により<BR>
     * 　@処理分岐する。<BR>
     * <BR>
     * 　@＜スキップ登録区分＝登録　@の場合＞<BR>
     * 　@undo注文繰越( )をコールする。（繰越注文の削除）<BR>
     * <BR>
     * 　@＜スキップ登録区分＝抹消　@の場合＞<BR>
     * 　@call注文繰越( )をコールする。（繰越注文の作成）<BR>
     * @@param l_hostEquityCarryoverSkipParams - (注文繰越スキップ銘柄通知キューParams)<BR>
     * @@param l_lisOrderExecEndList (出来終了List)<BR>
     * 【出来終了テーブル】レコードのList。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4137CF99002F
     */
    public void updateOrderCarryOverResult(
        HostEquityCarryoverSkipParams l_hostEquityCarryoverSkipParams, 
        List l_lisOrderExecEndList)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "updateOrderCarryOverResult(HostEquityCarryoverSkipParams)";        
        log.entering(STR_METHOD_NAME);
        
        if (l_hostEquityCarryoverSkipParams == null)
        {
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);            
        }
        
        if(l_lisOrderExecEndList == null)
        {
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);            
        }
        int l_intNum = l_lisOrderExecEndList.size();
        log.debug("l_intNum = " + l_intNum);
        
        String l_strInstitutionCode = l_hostEquityCarryoverSkipParams.getInstitutionCode();
        OrderexecutionEndParams l_orderExeutionParamas = null;
        OrderexecutionEndParams l_orderExeutionParamasPer = null;
        
        // １）　@引数の出来終了Listから、以下の条件に該当するレコードの
        // 　@「注文繰越処理区分」を取得する。
        // 
        // 　@　@　@＜取得条件＞
        // 　@　@　@証券会社コード＝引数の通知キューParams.証券会社コード
        // 　@　@　@かつ　@銘柄タイプ＝株式
        for (int i = 0;i < l_intNum; i++)
        {
            l_orderExeutionParamasPer = new OrderexecutionEndParams((OrderexecutionEndRow)l_lisOrderExecEndList.get(i));
            String l_strInstitutionCode1 = l_orderExeutionParamasPer.getInstitutionCode();
            ProductTypeEnum l_productType = l_orderExeutionParamasPer.getProductType();
            log.debug("l_strInstitutionCode1 = " + l_strInstitutionCode1);
            log.debug("l_productType = " + l_productType);
            if(l_strInstitutionCode.equals(l_strInstitutionCode1)
                && ProductTypeEnum.EQUITY.equals(l_productType))
            {
                l_orderExeutionParamas = l_orderExeutionParamasPer;
                break;                      
            }
        }
                
		//２）　@this.updateスキップ銘柄テーブル(引数の注文繰越スキップ銘柄通知キューParams)をコールする。
		this.updateSkipProdTable(l_hostEquityCarryoverSkipParams);
        
        //３）　@該当データなしの場合、または 取得したデータの注文繰越処理区分＝未処理 の場合は、
        // 　@ 　@何もせずにreturnする。
        if (l_orderExeutionParamas == null)
        {
            log.debug("該当データなしの場合");
            return;    
        }
        else if (WEB3CarryoverEndTypeDef.NOT_STARTED_PROCESS.equals(l_orderExeutionParamas.getCarryoverEndType()))
        {
            log.debug("注文繰越処理区分＝未処理 の場合");
            return;
        }        
        
        // ４）　@取得したデータの注文繰越処理区分＝処理済 の場合は、
        // 　@引数の注文繰越スキップ銘柄通知キューParams.スキップ登録区分の値により
        // 　@処理分岐する。
        if (WEB3CarryoverEndTypeDef.COMPLETE_PROCESS.equals(l_orderExeutionParamas.getCarryoverEndType()))
        {
            //注文繰越処理区分＝処理済 の場合            
            log.debug("注文繰越処理区分＝処理済 の場合");
            
            String l_strRegType = l_hostEquityCarryoverSkipParams.getSkipRegistType();
            log.debug("l_strRegType = " + l_strRegType);
            // 　@＜スキップ登録区分＝登録　@の場合＞
            if (WEB3SkipRegistTypeDef.REGISTRATION.equals(l_strRegType))
            {
                // 　@undo注文繰越( )をコールする。（繰越注文の削除）
                log.debug("undo注文繰越( )をコールする");
                undoOrderCarryOver(l_hostEquityCarryoverSkipParams);
            }
            // 　@＜スキップ登録区分＝抹消　@の場合＞
            else if (WEB3SkipRegistTypeDef.DELETE.equals(l_strRegType))
            {
                // 　@call注文繰越( )をコールする。（繰越注文の作成）
                log.debug("call注文繰越( )をコールする");
                callOrderCarryOver(l_hostEquityCarryoverSkipParams);
            }    
        }
    }

    /**
     * (undo注文繰越)<BR>
     * 注文繰越スキップ銘柄の登録を、注文繰越結果に反映する。<BR>
     * （既に当該証券会社について注文繰越処理が終了している場合、<BR>
     * 　@スキップ銘柄登録によって必要となった、繰越結果の翌日注文の削除を行う）<BR>
     * <BR>
     * シーケンス図「（スキップ銘柄通知）process」undo注文繰越( )部分参照。<BR>
     * <BR>
     * １）　@以下の条件に合致する、スキップ銘柄の注文単位オブジェクトのうち、<BR>
     * 　@　@　@注文繰越処理で作成されたデータを取得する。<BR>
     * <BR>
     * 　@　@　@＜抽出条件＞<BR>
     * 　@　@　@部店ID＝注文繰越スキップ銘柄通知キューParams.証券会社コード に該当する<BR>
     * 　@　@　@　@　@証券会社オブジェクト.getBranches( )で取得した全部店オブジェクトの部店IDのいずれか<BR>
     * 　@　@　@かつ　@銘柄ID＝注文繰越スキップ銘柄通知キューParams.銘柄コード に該当する<BR>
     * 　@　@　@　@　@銘柄オブジェクトの銘柄ID<BR>
     * 　@　@　@かつ　@市場ID＝注文繰越スキップ銘柄通知キューParams.スキップ市場コード に該当する<BR>
     * 　@　@　@　@　@市場オブジェクトの市場IDのいずれか<BR>
     * 　@　@　@かつ　@注文有効状態（OrderOpenStatus）＝オープン<BR>
     * 　@　@　@かつ　@発注日＞当日<BR>
     * 　@　@　@かつ　@初回注文の注文単位ID≠（null or 0）<BR>
     * 　@　@　@※口座ID昇順指定。<BR>
     * <BR>
     * 　@　@　@注文繰越スキップ銘柄通知キューParams.銘柄コード＝ALL（全銘柄）の場合は、<BR>
     * 　@　@　@抽出条件に銘柄コードは指定しない。<BR>
     * 　@　@　@注文繰越スキップ銘柄通知キューParams.スキップ市場コード＝F（全市場）の場合は、<BR>
     * 　@　@　@　@　@抽出条件に市場コードは指定しない。<BR>
     * 　@　@　@注文繰越スキップ銘柄通知キューParams.スキップ市場コード＝1（東京）の場合は、<BR>
     * 　@　@　@　@　@抽出条件に市場コード（東京）。<BR>
     * 　@　@　@注文繰越スキップ銘柄通知キューParams.スキップ市場コード＝2（大阪及びNNM）の場合は、<BR>
     * 　@　@　@　@　@抽出条件に市場コード（大阪＆NNM）。<BR>
     * 　@　@　@注文繰越スキップ銘柄通知キューParams.スキップ市場コード＝3（名古屋）の場合は、<BR>
     * 　@　@　@　@　@抽出条件に市場コード（名古屋）。<BR>
     * 　@　@　@注文繰越スキップ銘柄通知キューParams.スキップ市場コード＝6（JASDAQ）の場合は、<BR>
     * 　@　@　@　@　@抽出条件に市場コード（JASDAQ）。<BR>
     * <BR>
     * 　@　@　@※顧客自身によって繰越した翌日注文が取り消されている場合は、<BR>
     * 　@　@　@※上記抽出条件に合致せず繰越のundo対象とならない。<BR>
     * <BR>
     * ２）　@１）で取得したスキップ銘柄の注文単位オブジェクト(*1)数分、以下の処理を繰り返す。<BR>
     * 　@　@　@以下、データベースのupdate／deleteは、全てクエリプロセッサを使用し<BR>
     * 　@　@　@SQL文を発行することで行う。<BR>
     * <BR>
     * ２－１）　@顧客単位（Loopの一件目、または口座IDがブレイク）で口座をロックする。<BR>
     * <BR>
     * 　@　@　@　@　@拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。<BR>
     * 　@　@　@　@　@※引数は、注文単位.口座IDに該当する顧客オブジェクトより編集する。<BR>
     * <BR>
     * ２－２）　@ルールエンジンサーバに、条件付注文の注文繰越スキップを通知する。<BR>
     * <BR>
     * ２－２－１）　@拡張株式注文マネージャ.notifyルールエンジンサーバ()をコールする。<BR>
     * 　@　@　@------------------------------------------------------------ <BR>
     * 　@　@　@＜notifyルールエンジンサーバ()：引数設定仕様＞ <BR>
     * <BR>
     * 　@　@　@注文単位：　@１）で取得した該当の注文単位オブジェクト<BR>
     * 　@　@　@処理：　@ORDER_INVALIDATED_BY_MKT <BR>
     * 　@　@　@------------------------------------------------------------ <BR>
     * ２－２－２）　@notifyルールエンジンサーバ()にて業務エラーがスローされた場合 <BR>
     * 　@　@　@　@　@　@　@catchして処理を継続する。（ロールバックはおこなわない）<BR>
     * <BR>
     * ２－３）　@繰越元注文の注文エラー理由コード をupdateする。<BR>
     * <BR>
     * ２－３－１）　@繰越元注文の注文単位レコードを取得する。<BR>
     * <BR>
     * 　@　@　@　@　@＜取得条件＞<BR>
     * 　@　@　@　@　@（注文単位ID または 初回注文の注文単位ID）が、<BR>
     * 　@　@　@　@　@(*1)の注文単位.初回注文の注文単位ID と等しく、<BR>
     * 　@　@　@　@　@かつ　@注文有効状態（OrderOpenStatus）＝CLOSED に該当するデータのうち、<BR>
     * 　@　@　@　@　@作成日付が最も新しいもの。<BR>
     * <BR>
     * ２－３－２）　@２－３－１）で取得した注文単位レコード.注文エラー理由コード を<BR>
     * 　@　@　@　@　@　@　@　@「注文繰越スキップ銘柄エラー」に、<BR>
     * 　@　@　@　@　@　@　@　@更新日付を現在日時に、それぞれ更新する。<BR>
     * <BR>
     * ２－３－３）　@繰越元注文の注文履歴の、最終履歴レコードの注文エラー理由コード を<BR>
     * 　@　@　@　@　@　@　@　@updateする。<BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@注文単位ID＝２－３－１）で取得した注文単位レコード.注文単位ID<BR>
     * 　@　@　@　@　@　@　@　@かつ　@注文履歴番号＝２－３－１）で取得した注文単位レコード.注文履歴最終通番<BR>
     * 　@　@　@　@　@　@　@　@に該当する注文履歴レコード.注文エラー理由コードを「注文繰越スキップ銘柄エラー」に、<BR>
     * 　@　@　@　@　@　@　@　@更新日付を現在日時に、それぞれ更新する。<BR>
     * <BR>
     * ２－３－４）　@繰越元注文の注文（ヘッダ）の更新日時をupdateする。<BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@注文ID＝２－３－１）で取得した注文単位レコード.注文ID<BR>
     * 　@　@　@　@　@　@　@　@に該当する注文（ヘッダ）レコード.更新日付を現在日時に更新する。<BR>
     * <BR>
     * ２－４）　@【株式注文取引キューテーブル】から、該当する繰越注文データをdeleteする。<BR>
     * 　@　@　@　@　@削除条件には、１）で取得したスキップ銘柄の注文単位 のプロパティを指定する。<BR>
     * <BR>
     * 　@　@　@＜削除条件＞<BR>
     * 　@　@　@証券会社コード＝注文単位.部店ID の部店オブジェクト.証券会社コード<BR>
     * 　@　@　@かつ　@部店コード＝注文単位.部店ID の部店オブジェクト.部店コード<BR>
     * 　@　@　@かつ　@銘柄コード＝拡張プロダクトマネージャ.getProduct(注文単位.銘柄ID).銘柄コード<BR>
     * 　@　@　@かつ　@識別コード＝注文単位.識別コード<BR>
     * 　@　@　@かつ　@処理区分＝未処理<BR>
     * <BR>
     * ２－５）　@繰越処理で作成されたスキップ銘柄の注文に<BR>
     * 　@　@　@　@　@有効な予約注文が設定されていた場合、予約注文の削除を行う。<BR>
     * <BR>
     * ２－５－１）　@拡張株式注文マネージャ.is予約注文確認要()をコールする。<BR>
     * 　@　@　@　@　@　@　@　@引数の注文単位には、１）で取得した注文単位を指定する。<BR>
     * <BR>
     * ２－５－２）　@２－５－１）の戻り値がtrueの場合、<BR>
     * 　@　@　@　@　@　@　@　@株式予約注文更新サービスImpl.deleteAll予約注文単位()をコールする。<BR>
     * 　@　@　@　@　@　@　@　@引数の親注文の注文IDには、１）で取得した注文単位.注文ID を指定する。<BR>
     * <BR>
     * ２－６）　@繰越処理で作成されたスキップ銘柄の注文履歴データを、<BR>
     * 　@　@　@　@　@【注文履歴テーブル】よりdeleteする。<BR>
     * 　@　@　@　@　@削除キーには、１）で取得した注文単位.注文ID を指定する。<BR>
     * <BR>
     * ２－７）　@繰越注文で作成されたスキップ銘柄の注文単位.注文カテゴリ=="返済注文"の場合、<BR>
     * 　@　@　@　@　@返済注文中数量を減算（ロックを解除）してから、建株返済指定情報データを<BR>
     * 　@　@　@　@　@【建株返済指定情報テーブル】よりdeleteする。<BR>
     * <BR>
     * ２－７－１）　@注文単位.getContractsToClose()により、建株返済指定情報の一覧を取得する。<BR>
     * <BR>
     * ２－７－２）　@取得した建株返済指定情報の数分、以下の処理を繰り返し、<BR>
     * 　@　@　@　@　@　@　@　@返済指定対応の建の返済注文中数量を減算した後で、<BR>
     * 　@　@　@　@　@　@　@　@返済指定データをdeleteする。<BR>
     * <BR>
     * ↓↓↓↓↓ START LOOP ↓↓↓↓↓<BR>
     * <BR>
     * ２－７－２－１）　@返済数量を取得する。<BR>
     * <BR>
     * 　@　@　@返済数量(*1) = 建株返済指定情報.getQuantity()<BR>
     * <BR>
     * ２－７－２－２）　@返済注文中数量を調整する。<BR>
     * <BR>
     * 　@　@　@株式ポジションマネージャ.updateLockedQuantity(long, Contract, double)をコールする。<BR>
     * 　@　@　@------------------------------------------------------------<BR>
     * 　@　@　@＜updateLockedQuantity()：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@注文単位ID：　@１）で取得した注文単位.注文単位ID<BR>
     * 　@　@　@建株：　@取得した建株返済指定情報.建株IDに該当する建株<BR>
     * 　@　@　@調整数量：　@返済数量(*1)×（－１）<BR>
     * 　@　@　@------------------------------------------------------------<BR>
     * <BR>
     * ↑↑↑↑↑ END LOOP ↑↑↑↑↑<BR>
     * <BR>
     * ２－７－３）　@建株返済指定情報データをdeleteする。<BR>
     * 　@　@　@　@　@　@　@　@削除キーには、１）で取得した注文単位.注文ID を指定する。<BR>
     * <BR>
     * ２－８）　@繰越注文で作成されたスキップ銘柄の注文単位.注文種別=="現物売注文"の場合、<BR>
     * 　@　@　@　@　@売付注文中数量を減算する。（ロックを解除する）<BR>
     * <BR>
     * ２－８－１）　@売付数量を取得する。<BR>
     * <BR>
     * 　@　@　@売付数量(*2) = １）で取得した注文単位.getQuantity()<BR>
     * <BR>
     * ２－８－２）　@売付注文中数量を調整する。<BR>
     * <BR>
     * 　@　@　@株式ポジションマネージャ.updateロック中数量(long, long, long, long, double)をコールする。<BR>
     * 　@　@　@------------------------------------------------------------<BR>
     * 　@　@　@＜updateロック中数量()：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@口座ID：　@１）で取得した注文単位.口座ID<BR>
     * 　@　@　@補助口座ID：　@１）で取得した注文単位.補助口座ID<BR>
     * 　@　@　@注文単位ID：　@１）で取得した注文単位.注文単位ID<BR>
     * 　@　@　@銘柄ID：　@１）で取得した注文単位.銘柄ID<BR>
     * 　@　@　@調整数量：　@売付数量(*2)×（－１）<BR>
     * 　@　@　@------------------------------------------------------------<BR>
     * <BR>
     * ２－９）　@繰越処理で作成されたスキップ銘柄の注文単位データを、<BR>
     * 　@　@　@　@　@【注文単位テーブル】よりdeleteする。<BR>
     * 　@　@　@　@　@削除キーには、１）で取得した注文単位.注文ID を指定する。<BR>
     * <BR>
     * ２－１０）　@繰越処理で作成されたスキップ銘柄の注文データを、<BR>
     * 　@　@　@　@　@【注文テーブル（ヘッダ）】よりdeleteする。<BR>
     * 　@　@　@　@　@削除キーには、１）で取得した注文単位.注文ID を指定する。<BR>
     * <BR>
     * ２－１１）　@余力更新処理<BR>
     * 　@　@顧客単位（口座IDがブレイクした場合、または最後の注文単位の処理時）に、<BR>
     * 　@　@.余力再計算サービス.余力再計算(補助口座)をコールする。<BR>
     * <BR>
     * 　@　@引数の補助口座は、<BR>
     * 　@　@口座IDの顧客オブジェクト.getSubAccount(SubAccountTypeEnum.株式取引口座)<BR>
     * 　@　@にて取得する。<BR>
     * @@param l_hostEquityCarryoverSkipParams <BR>
     * - 注文繰越スキップ銘柄通知キューParams<BR>
     * @@throws WEB3BaseLayerException
     * @@roseuid 4137CF99008A
     */
    public void undoOrderCarryOver(HostEquityCarryoverSkipParams l_hostEquityCarryoverSkipParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "undoOrderCarryOver(HostEquityCarryoverSkipParams)";
        log.entering(STR_METHOD_NAME);
        if (l_hostEquityCarryoverSkipParams == null)
        {
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);            
        }
        
        // １）　@以下の条件に合致する、スキップ銘柄の注文単位オブジェクトのうち、
        // 　@　@　@注文繰越処理で作成されたデータを取得する。
        //get証券会社コード
        String l_strInstitutionCode =
            l_hostEquityCarryoverSkipParams.getInstitutionCode();
        log.debug("l_strInstitutionCode = " + l_strInstitutionCode);
        
        //get銘柄コード
        String l_strProductCode =
            l_hostEquityCarryoverSkipParams.getProductCode();
        log.debug("l_strProductCode = " + l_strProductCode);
        if (ALL_PRODUCT.equals(l_strProductCode))
        {
            l_strProductCode = null;
        }
        //getスキップ市場コード
        String l_strSkipMarketCode =
            l_hostEquityCarryoverSkipParams.getSkipMarketCode();
        log.debug("l_strSkipMarketCode = " + l_strSkipMarketCode);
        
        //get市場コードList
        String[] l_strMarketCodes = new String[2];
        String l_strMarketCode = null;
        if (WEB3SkipMarketCodeDef.TOKYO.equals(l_strSkipMarketCode))
        {
            l_strMarketCodes = null;
            l_strMarketCode = WEB3MarketCodeDef.TOKYO;
        }
        else if (WEB3SkipMarketCodeDef.OSAKA_AND_NNM.equals(l_strSkipMarketCode))
        {
            l_strMarketCodes[0] = WEB3MarketCodeDef.OSAKA;
            l_strMarketCodes[1] = WEB3MarketCodeDef.NNM;
        }
        else if (WEB3SkipMarketCodeDef.NAGOYA.equals(l_strSkipMarketCode))
        {
            l_strMarketCodes = null;
            l_strMarketCode = WEB3MarketCodeDef.NAGOYA;
        }
		else if (WEB3SkipMarketCodeDef.JASDAQ.equals(l_strSkipMarketCode))
		{
            l_strMarketCodes = null;
            l_strMarketCode = WEB3MarketCodeDef.JASDAQ;
		}
		else
		{
			l_strMarketCodes = null;
			l_strMarketCode = null;
		}

        log.debug("l_strMarketCodes = " + l_strMarketCodes);
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
        WEB3EquityProductManager l_eqProductManager =
            (WEB3EquityProductManager)
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
        
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getPositionManager();
        
        WEB3ToSuccReservationEqTypeOrderUpdateService l_updateService =
            (WEB3ToSuccReservationEqTypeOrderUpdateService)Services.getService(
                WEB3ToSuccReservationEqTypeOrderUpdateService.class);
        
        Institution l_institution = null;
        Long l_lngProductId = null;
        Long[] l_lngMarketIds = new Long[2];
        Long l_lngMarketId = null;
        
        try
        {
            //get証券会社オブジェクト
            l_institution =
                l_accountManager.getInstitution(l_strInstitutionCode);
            log.debug("l_strProductCode = " + l_strProductCode);
            if (l_strProductCode != null)
            {
                //get銘柄ID
                l_lngProductId = new Long(
                    l_eqProductManager.getProduct(l_institution, l_strProductCode).getProductId());
            }
            else
            {
                l_lngProductId = null;
            }
            log.debug("l_lngProductId = " + l_lngProductId);
            
            //get市場ID
            if (l_strMarketCodes != null)
            {
                l_lngMarketIds[0] = new Long(
                    l_finApp.getFinObjectManager().getMarket(l_institution, l_strMarketCodes[0]).getMarketId());
                log.debug("l_lngMarketIds[0] = " + l_lngMarketIds[0]);
                l_lngMarketIds[1] = new Long(
                    l_finApp.getFinObjectManager().getMarket(l_institution, l_strMarketCodes[1]).getMarketId());
                log.debug("l_lngMarketIds[1] = " + l_lngMarketIds[1]);
            }
			else if (l_strMarketCode != null)
			{
				l_lngMarketIds = null;
				l_lngMarketId = 
					new Long(
						l_finApp.getFinObjectManager().getMarket(l_institution, l_strMarketCode).getMarketId());
			}
			else
			{
				l_lngMarketIds = null;
			}
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME,l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);

        }
        
        //get部店オブジェクト
        Branch[] l_branchs = l_institution.getBranches();
        //get当日
        Timestamp l_tsCurTime =
            GtlUtils.getTradingSystem().getSystemTimestamp();
            
        String l_strCurTime = WEB3DateUtility.formatDate(l_tsCurTime, "yyyyMMdd");

        StringBuffer l_sbWhere = new StringBuffer();
        Object[] l_objWhere = null;
        ArrayList l_lisObjWhere = new ArrayList();
        //全部店オブジェクトの部店ID
        int l_intSize = l_branchs.length;        
        l_sbWhere.append(" (branch_id = ? ");        
        l_lisObjWhere.add(new Long(l_branchs[0].getBranchId()));
        for (int i = 1; i < l_intSize; i++)
        {
            l_sbWhere.append(" or branch_id = ? ");
            l_lisObjWhere.add(new Long(l_branchs[i].getBranchId()));
        }
        l_sbWhere.append(") ");
        
        log.debug("l_sbWhere = " + l_sbWhere);
        log.debug("l_lstObjWhere = " + l_lisObjWhere);
        
        //注文繰越スキップ銘柄通知キューParams.銘柄コード＝ALL（全銘柄）の場合は、
        //抽出条件に銘柄コードは指定しない。
        if (l_lngProductId != null)
        {
            l_sbWhere.append(" and product_id = ? ");            
            l_lisObjWhere.add(l_lngProductId);
        }
        
        //注文繰越スキップ銘柄通知キューParams.スキップ市場コード＝F（全市場）の場合は
        //抽出条件に市場コードは指定しない。
        if (l_lngMarketIds != null)
        {
            l_sbWhere.append(" and (market_id = ? or market_id = ?) ");
            l_lisObjWhere.add(l_lngMarketIds[0]);
            l_lisObjWhere.add(l_lngMarketIds[1]);
        }
		else if (l_lngMarketId != null)
		{
			l_sbWhere.append(" and market_id = ? ");
			l_lisObjWhere.add(l_lngMarketId);
		}        

        //注文有効状態（OrderOpenStatus）＝オープン
        l_sbWhere.append(" and order_open_status = ? ");
        l_lisObjWhere.add(String.valueOf(OrderOpenStatusEnum.OPEN.intValue()));
        //発注日＞当日
        l_sbWhere.append(" and  biz_date  > ? ");
        l_lisObjWhere.add(l_strCurTime);
        //繰越元注文単位ID≠null かつ 繰越元注文単位ID≠0
        l_sbWhere.append(" and  (first_order_unit_id != 0) ");
        l_sbWhere.append(" and  (first_order_unit_id is not null) ");
        log.debug("l_sbWhere = " + l_sbWhere);
        // ※口座ID昇順指定
        String l_strSort = "account_id asc";
        
        l_intSize = l_lisObjWhere.size();
        l_objWhere = new Object[l_intSize];
        
        l_lisObjWhere.toArray(l_objWhere);
        
        List l_lisRecords;
        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords =
                l_QueryProcessor.doFindAllQuery(
                    EqtypeOrderUnitRow.TYPE,
                    l_sbWhere.toString(),
                    l_strSort,
                    null,
                    l_objWhere);
        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME, l_dfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME, l_dqe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        //件数チェック
        int l_intSize1 = 0;
        if(l_lisRecords != null)
        {
            l_intSize1 = l_lisRecords.size();
        }
        if (l_lisRecords.size() == 0)
        {
			log.exiting(STR_METHOD_NAME);
            return;
        }
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {

            // ２）　@１）で取得したスキップ銘柄の注文単位オブジェクト(*1)数分、以下の処理を繰り返す。
            EqtypeOrderUnitRow l_oldOrderUnitRow;
            long l_lngAccountId = 0L;
            for (int i = 0; i < l_intSize1; i++)
            {
                l_oldOrderUnitRow =
                    (EqtypeOrderUnitRow) l_lisRecords.get(i);
                
                // ２－１）　@顧客単位（Loopの一件目、または口座IDがブレイク）で口座をロックする。
                if (l_lngAccountId != l_oldOrderUnitRow.getAccountId())
                {
                    l_lngAccountId = l_oldOrderUnitRow.getAccountId();
                    l_mainAccount =
                        (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_lngAccountId);
                    l_accountManager.lockAccount(
                        l_mainAccount.getInstitution().getInstitutionCode(),
                        l_mainAccount.getBranch().getBranchCode(),
                        l_mainAccount.getAccountCode());
                }

                //２－２）　@ルールエンジンサーバに、条件付注文の注文繰越スキップを通知する。
                //
                //２－２－１）　@拡張株式注文マネージャ.notifyルールエンジンサーバ()をコールする。
                //　@　@　@------------------------------------------------------------
                //　@　@　@＜notifyルールエンジンサーバ()：引数設定仕様＞
                //
                //　@　@　@注文単位：　@１）で取得した該当の注文単位オブジェクト
                //　@　@　@処理：　@ORDER_INVALIDATED_BY_MKT
                //　@　@　@------------------------------------------------------------
                EqTypeOrderUnit l_orderUnit =
                    (EqTypeOrderUnit)l_orderManager.toOrderUnit(l_oldOrderUnitRow);
                try
                {
                    l_orderManager.notifyRLS(
                        l_orderUnit,
                        OrderManagerPersistenceContext.ORDER_INVALIDATED_BY_MKT);
                }
                //２－２－２）　@notifyルールエンジンサーバ()にて業務エラーがスローされた場合
                //　@　@　@　@　@　@　@catchして処理を継続する。（ロールバックはおこなわない）
                catch (WEB3BusinessLayerException l_ex)
                {
                    log.debug("notifyルールエンジンサーバ()にて業務エラーがスローされた場合, ロールバックはおこなわない");
                }

                // ２－２）　@繰越元注文の注文エラー理由コード をupdateする。
                
                // ２－２－１）　@繰越元注文の注文単位レコードを取得する。
                // 
                // 　@　@　@　@　@＜取得条件＞
                // 　@　@　@　@　@（注文単位ID または 初回注文の注文単位ID）が、
                // 　@　@　@　@　@(*1)の注文単位.初回注文の注文単位ID と等しく、
                // 　@　@　@　@　@かつ　@注文有効状態（OrderOpenStatus）＝CLOSED に該当するデータのうち、
                // 　@　@　@　@　@作成日付が最も新しいもの。
                StringBuffer l_sbOrderUnitWhere = new StringBuffer();
                l_sbOrderUnitWhere.append("(order_unit_id = ?");
                l_sbOrderUnitWhere.append(" or first_order_unit_id = ?)");
                l_sbOrderUnitWhere.append(" and (order_open_status = ?)");
                //初回注文の注文単位ID
                long l_lgnFirstOrderUnitId = l_oldOrderUnitRow.getFirstOrderUnitId();
                Object[] l_objOrderUnitWhere =
                    {new Long(l_lgnFirstOrderUnitId),new Long(l_lgnFirstOrderUnitId),OrderOpenStatusEnum.CLOSED};
                    
                QueryProcessor l_queryProcessor =
                    Processors.getDefaultProcessor();
                List l_listRecords =
                    l_queryProcessor.doFindAllQuery(
                        EqtypeOrderUnitRow.TYPE,
                        l_sbOrderUnitWhere.toString(),
                        "created_timestamp desc",
                        null,
                        l_objOrderUnitWhere);
                if(l_listRecords == null || l_listRecords.size() == 0 )
                {
                    throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                       this.getClass().getName() + "." + STR_METHOD_NAME);            
                }  
                EqtypeOrderUnitParams  l_orderUnitParams = 
                    new EqtypeOrderUnitParams((EqtypeOrderUnitRow) l_listRecords.get(0));
                
                // ２－２－２）　@２－２－１）で取得した注文単位レコード.注文エラー理由コード を
                // 　@　@　@　@　@　@　@　@「注文繰越スキップ銘柄エラー」に、
                // 　@　@　@　@　@　@　@　@更新日付を現在日時に、それぞれ更新する。
                l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.CARRY_OVER_SKIP_PRODUCT_ERROR); 
                l_orderUnitParams.setLastUpdatedTimestamp(l_tsCurTime);
                l_queryProcessor.doUpdateQuery(l_orderUnitParams);
                                
                // ２－２－３）　@繰越元注文の注文履歴の、最終履歴レコードの注文エラー理由コード を
                // 　@　@　@　@　@　@　@　@updateする。
                // 
                // 　@　@　@　@　@　@　@　@注文単位ID＝２－２－１）で取得した注文単位レコード.注文単位ID
                // 　@　@　@　@　@　@　@　@かつ　@注文履歴番号＝２－２－１）で取得した注文単位レコード.注文履歴最終通番
                // 　@　@　@　@　@　@　@　@に該当する注文履歴レコード.注文エラー理由コードを「注文繰越スキップ銘柄エラー」に、
                // 　@　@　@　@　@　@　@　@更新日付を現在日時に、それぞれ更新する。
                long l_lngOrderUnitId = l_orderUnitParams.getOrderUnitId();
                int l_intActionSerialNo = l_orderUnitParams.getLastOrderActionSerialNo();
                StringBuffer l_sbOrderActionWhere = new StringBuffer();
                l_sbOrderActionWhere.append("order_unit_id = ?");
                l_sbOrderActionWhere.append(" and order_action_serial_no = ?");
                Object[] l_objOrderActionWhere =
                    {new Long(l_lngOrderUnitId),new Integer(l_intActionSerialNo)};
                List l_listOrderAction =l_queryProcessor.doFindAllQuery(
                    EqtypeOrderActionRow.TYPE,
                    l_sbOrderActionWhere.toString(),
                    null,
                    null,
                    l_objOrderActionWhere);
                if (l_listOrderAction == null || l_listOrderAction.size() == 0  )
                {
                    throw new WEB3SystemLayerException(
                       WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                       this.getClass().getName() + "." + STR_METHOD_NAME);            
                }  
                EqtypeOrderActionParams l_orderActionParams = 
                    new EqtypeOrderActionParams((EqtypeOrderActionRow) l_listOrderAction.get(0));
                l_orderActionParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.CARRY_OVER_SKIP_PRODUCT_ERROR);
                l_orderActionParams.setLastUpdatedTimestamp(l_tsCurTime);
                l_queryProcessor.doUpdateQuery(l_orderActionParams);

                // ２－２－４）　@繰越元注文の注文（ヘッダ）の更新日時をupdateする。
                // 
                // 　@　@　@　@　@　@　@　@注文ID＝２－２－１）で取得した注文単位レコード.注文ID
                // 　@　@　@　@　@　@　@　@に該当する注文（ヘッダ）レコード.更新日付を現在日時に更新する。
                long l_lngOrderId = l_orderUnitParams.getOrderId();
                EqtypeOrderParams l_eqtypeOrderParams = 
                    new EqtypeOrderParams(EqtypeOrderDao.findRowByPk(l_lngOrderId));
                l_eqtypeOrderParams.setLastUpdatedTimestamp(l_tsCurTime);
                l_queryProcessor.doUpdateQuery(l_eqtypeOrderParams);

                // ２－３）　@【株式注文取引キューテーブル】から、該当する繰越注文データをdeleteする。
                // 　@　@　@　@　@削除条件には、１）で取得したスキップ銘柄の注文単位 のプロパティを指定する。
                // 
                // 　@　@　@＜削除条件＞
                // 　@　@　@証券会社コード＝注文単位.部店ID の部店オブジェクト.証券会社コード
                // 　@　@　@かつ　@部店コード＝注文単位.部店ID の部店オブジェクト.部店コード
                // 　@　@　@かつ　@銘柄コード＝拡張プロダクトマネージャ.getProduct(注文単位.銘柄ID).銘柄コード
                // 　@　@　@かつ　@識別コード＝注文単位.識別コード
                // 　@　@　@かつ　@処理区分＝未処理
                StringBuffer l_sbHostEqtypeOrderWhere = new StringBuffer();
                l_sbHostEqtypeOrderWhere.append(" institution_code = ? ");
                l_sbHostEqtypeOrderWhere.append(" and branch_code = ? ");
                l_sbHostEqtypeOrderWhere.append(" and product_code = ? ");
                l_sbHostEqtypeOrderWhere.append(" and order_request_number = ? ");
                l_sbHostEqtypeOrderWhere.append(" and status = ? ");

                //get部店オブジェクト&銘柄オブジェクト
                Branch l_branch = null;
                EqTypeProduct l_product = null;

                l_branch =
                    l_accountManager.getBranch(l_oldOrderUnitRow.getBranchId());
                l_product =
                    (EqTypeProduct)l_eqProductManager.getProduct(
                        l_oldOrderUnitRow.getProductId());

                Object[] l_objHostEqtypeOrderWhere =
                    {
                        l_branch.getInstitution().getInstitutionCode(),
                        l_branch.getBranchCode(),
                        l_product.getProductCode(),
                        l_oldOrderUnitRow.getOrderRequestNumber(),
                        WEB3HostStatusDef.NOT_STARTED_PROCESS };

                l_queryProcessor = Processors.getDefaultProcessor();
                l_queryProcessor.doDeleteAllQuery(
                    HostEqtypeOrderAllRow.TYPE,
                    l_sbHostEqtypeOrderWhere.toString(),
                    l_objHostEqtypeOrderWhere);
                
                if (l_orderManager.isReserveOrderConfirmRequire(l_orderUnit))
                {
                    l_updateService.deleteAllOrderUnit(l_orderUnit.getOrderId());
                }

                // ２－５）　@繰越処理で作成されたスキップ銘柄の注文履歴データを、
                // 　@　@　@　@　@【注文履歴テーブル】よりdeleteする。
                // 　@　@　@　@　@削除キーには、１）で取得した注文単位.注文ID を指定する。
                l_queryProcessor.doDeleteAllQuery(
                    EqtypeOrderActionRow.TYPE,
                    "order_id = ?",
                    new Object[]{new Long(l_oldOrderUnitRow.getOrderId())});
                
                if (OrderCategEnum.CLOSE_MARGIN.equals(l_orderUnit.getOrderCateg()))
                {
                    EqTypeClosingContractSpec[] l_closingContractSpecs =
                        ((EqTypeContractSettleOrderUnit)l_orderUnit).getContractsToClose();
                    int l_intSpecLength = 0;
                    if (l_closingContractSpecs != null)
                    {
                        l_intSpecLength = l_closingContractSpecs.length;
                    }
                    for (int j = 0;j < l_intSpecLength;j++)
                    {
                        long l_lngContractId =
                            l_closingContractSpecs[j].getContractId();
                        double l_dblAdjustQuantity = l_closingContractSpecs[j].getQuantity() * -1D;
                        l_positionManager.updateLockedQuantity(
                            l_orderUnit.getOrderUnitId(),
                            l_positionManager.getContract(l_lngContractId),
                            l_dblAdjustQuantity);
                    }
                    
                    l_queryProcessor.doDeleteAllQuery(
                        EqtypeClosingContractSpecRow.TYPE,
                        "order_id = ?",
                        new Long[] { new Long(l_orderUnit.getOrderId())});
                }
                
                if (OrderTypeEnum.EQUITY_SELL.equals(l_orderUnit.getOrderType()))
                {
                    double l_dblAdjustQuantity = l_orderUnit.getQuantity() * -1D;
                    l_positionManager.updateLockedQuantity(
                        l_orderUnit.getAccountId(),
                        l_orderUnit.getSubAccountId(),
                        l_orderUnit.getOrderUnitId(),
                        l_orderUnit.getProduct().getProductId(),
                        l_dblAdjustQuantity);
                }
                
                // ２－８）　@繰越処理で作成されたスキップ銘柄の注文単位データを、
                // 　@　@　@　@　@【注文単位テーブル】よりdeleteする。
                // 　@　@　@　@　@削除キーには、１）で取得した注文単位.注文ID を指定する。
                l_queryProcessor.doDeleteQuery(
                    l_oldOrderUnitRow.getPrimaryKey());
                    
                // ２－９）　@繰越処理で作成されたスキップ銘柄の注文データを、
                // 　@　@　@　@　@【注文テーブル（ヘッダ）】よりdeleteする。
                // 　@　@　@　@　@削除キーには、１）で取得した注文単位.注文ID を指定する。
                Object[] l_objOrderWhere =  {new Long(l_oldOrderUnitRow.getOrderId())};
                l_queryProcessor.doDeleteAllQuery(EqtypeOrderRow.TYPE,"order_id = ?",l_objOrderWhere);
                
                // ２－１０）　@余力更新処理
                // 　@　@顧客単位（口座IDがブレイクした場合、または最後の注文単位の処理時）に、
                // 　@　@.余力再計算サービスのインスタンスを生成し、余力再計算(補助口座)をコールする。
                // 
                // 　@　@引数の補助口座は、
                // 　@　@口座IDの顧客オブジェクト.getSubAccount(SubAccountTypeEnum.株式取引口座)
                // 　@　@にて取得する。
                WEB3GentradeMainAccount l_gentradeMainAccount = 
                    new WEB3GentradeMainAccount(l_oldOrderUnitRow.getAccountId());
                WEB3GentradeSubAccount l_gentradeSubAccount = 
                    (WEB3GentradeSubAccount)
                    l_gentradeMainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
                    (WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
                l_tpTradingPowerReCalcService.reCalcTradingPower(l_gentradeSubAccount);
            }
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME,
                l_nfe.getMessage());
        }
        catch (DataException l_de)
        {
            log.error(STR_METHOD_NAME, l_de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }
        catch (WEB3BaseException l_wbe)
        {
            if (l_wbe.getErrorInfo() == WEB3ErrorCatalog.SYSTEM_ERROR_80076)
            {
                log.error(
                    "口座ロックに失敗しました。： " +
                    "証券会社コード=[" + l_mainAccount.getInstitution().getInstitutionCode() + "], " +
                    "部店コード=[" + l_mainAccount.getBranch().getBranchCode() + "], " +
                    "顧客コード=[" + l_mainAccount.getAccountCode() + "]");
            }
            throw l_wbe;
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (call注文繰越)<BR>
     * 注文繰越スキップ銘柄の登録抹消を、注文繰越結果に反映する。<BR>
     * （既に当該証券会社について注文繰越処理が終了している場合、<BR>
     * 　@スキップ銘柄登録抹消によって必要となった注文繰越処理を行う）<BR>
     * <BR>
     * シーケンス図「（スキップ銘柄通知）process」call注文繰越( )部分参照。<BR>
     * <BR>
     * １）　@以下の条件に合致する、スキップ登録抹消銘柄の注文データのうち、<BR>
     * 　@　@　@注文繰越対象となる「出来るまで注文」の注文単位オブジェクトの候補を取得する。<BR>
     * 　@　@　@※クエリプロセッサを使用する。<BR>
     * <BR>
     * 　@　@　@＜抽出条件＞<BR>
     * 　@　@　@部店ID＝注文繰越スキップ銘柄通知キューParams.証券会社コード に該当する<BR>
     * 　@　@　@　@　@証券会社オブジェクト.getBranches( )で取得した全部店オブジェクトの部店IDのいずれか<BR>
     * 　@　@　@かつ　@銘柄ID＝注文繰越スキップ銘柄通知キューParams.銘柄コード に該当する<BR>
     * 　@　@　@　@　@銘柄オブジェクトの銘柄ID<BR>
     * 　@　@　@かつ　@市場ID＝注文繰越スキップ銘柄通知キューParams.スキップ市場コード に該当する<BR>
     * 　@　@　@　@　@市場オブジェクトの市場IDのいずれか<BR>
     * 　@　@　@かつ　@注文有効状態（OrderOpenStatus）＝CLOSED<BR>
     * 　@　@　@かつ　@注文の失効状態（OrderExpirationStatus）＝EXPIRED<BR>
     * 　@　@　@かつ　@注文エラー理由コード＝注文繰越スキップ銘柄エラー<BR>
     * 　@　@　@※口座ID昇順指定。<BR>
     * <BR>
     * 　@　@　@注文繰越スキップ銘柄通知キューParams.銘柄コード＝ALL（全銘柄）の場合は、<BR>
     * 　@　@　@抽出条件に銘柄コードは指定しない。<BR>
     * 　@　@　@注文繰越スキップ銘柄通知キューParams.スキップ市場コード＝F（全市場）の場合は、<BR>
     * 　@　@　@　@　@抽出条件に市場コードは指定しない。<BR>
     * 　@　@　@注文繰越スキップ銘柄通知キューParams.スキップ市場コード＝1（東京）の場合は、<BR>
     * 　@　@　@　@　@抽出条件に市場コード（東京）。<BR>
     * 　@　@　@注文繰越スキップ銘柄通知キューParams.スキップ市場コード＝2（大阪及びNNM）の場合は、<BR>
     * 　@　@　@　@　@抽出条件に市場コード（大阪＆NNM）。<BR>
     * 　@　@　@注文繰越スキップ銘柄通知キューParams.スキップ市場コード＝3（名古屋）の場合は、<BR>
     * 　@　@　@　@　@抽出条件に市場コード（名古屋）。<BR>
     * 　@　@　@注文繰越スキップ銘柄通知キューParams.スキップ市場コード＝6（JASDAQ）の場合は、<BR>
     * 　@　@　@　@　@抽出条件に市場コード（JASDAQ）。<BR>
     * <BR>
     * ２）　@１）で取得した注文単位オブジェクト数分、<BR>
     * 　@　@　@注文繰越スキップ銘柄通知繰越対象チェックサービスImpl.is繰越注文単位( )をコールする。<BR>
     * 　@　@　@戻り値＝falseの場合は、当該注文単位オブジェクトを<BR>
     * 　@　@　@１）で取得した繰越対象の注文単位オブジェクトの候補から外す。<BR>
     * <BR>
     * ３）　@２）で繰越対象となった注文単位オブジェクト数分(*1)、Loopで以下の処理を行う。<BR>
     * <BR>
     * ３－１）　@顧客単位（Loopの一件目、または口座IDがブレイク）で口座をロックする。<BR>
     * <BR>
     * 　@　@　@　@　@拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。<BR>
     * 　@　@　@　@　@※引数は、注文単位.口座IDに該当する顧客オブジェクトより編集する。<BR>
     * <BR>
     * ３－２）　@繰越注文を作成する。<BR>
     * <BR>
     * 　@　@　@注文繰越一件サービスImpl.insert繰越注文( ２）で繰越対象となった注文単位オブジェクト(*1))を<BR>
     * 　@　@　@コールし、<BR>
     * 　@　@　@繰越注文を作成する。<BR>
     * <BR>
     * 　@　@　@(*1)注文単位オブジェクトはlock口座( )の前に取得しているので、<BR>
     * 　@　@　@　@　@他処理により更新されている可能性があるため、拡張株式注文マネージャ.getOrderUnit( )で<BR>
     * 　@　@　@　@　@再取得してからセットする。<BR>
     * <BR>
     * 　@　@　@繰越注文作成によるDBの登録／更新内容は、注文繰越サービスの<BR>
     * 　@　@　@「注文繰越_注文（ヘッダ）テーブル仕様.xls」<BR>
     * 　@　@　@「注文繰越_注文単位テーブル仕様.xls」<BR>
     * 　@　@　@「注文繰越_注文履歴テーブル仕様.xls」<BR>
     * 　@　@　@「信用注文繰越_注文（ヘッダ）テーブル仕様.xls」<BR>
     * 　@　@　@「信用注文繰越_注文単位テーブル仕様.xls」<BR>
     * 　@　@　@「信用注文繰越_注文履歴テーブル仕様.xls」を参照。<BR>
     * <BR>
     * ３－３）　@繰越元注文をupdateする。<BR>
     * <BR>
     * 　@　@　@注文繰越一件サービスImpl.update繰越元注文()をコールする。<BR>
     * <BR>
     * 　@　@　@引数は以下の通り設定する。<BR>
     * 　@　@　@注文単位  ＝　@注文単位(繰越元)<BR>
     * 　@　@　@エラー理由コード  ＝　@”0000:正常”<BR>
     * <BR>
     * ３－４）　@余力更新処理<BR>
     * 　@　@顧客単位（口座IDがブレイクした場合、または最後の注文単位の処理時）に、<BR>
     * 　@　@.余力再計算サービスのインスタンスを生成し、余力再計算(補助口座)をコールする。<BR>
     * <BR>
     * 　@　@引数の補助口座は、<BR>
     * 　@　@口座IDの顧客オブジェクト.getSubAccount(SubAccountTypeEnum.株式取引口座)<BR>
     * 　@　@にて取得する。<BR>
     * 注文単位.getSubAccountId()に該当する補助口座オブジェクト<BR>
     * @@param l_hostEquityCarryoverSkipParams <BR>
     * - 注文繰越スキップ銘柄通知キューParams<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4137CF9900E4
     */
    public void callOrderCarryOver(HostEquityCarryoverSkipParams l_hostEquityCarryoverSkipParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "callOrderCarryOver(HostEquityCarryoverSkipParams)";
        log.entering(STR_METHOD_NAME);
        if (l_hostEquityCarryoverSkipParams == null)
        {
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);            
        }
        // １）　@以下の条件に合致する、スキップ登録抹消銘柄の注文データのうち、
        // 　@　@　@注文繰越対象となる「出来るまで注文」の注文単位オブジェクトの候補を取得する。
        //get証券会社コード
        String l_strInstitutionCode =
            l_hostEquityCarryoverSkipParams.getInstitutionCode();
        //get銘柄コード
        String l_strProductCode =
            l_hostEquityCarryoverSkipParams.getProductCode();
        if (ALL_PRODUCT.equals(l_strProductCode))
        {
            l_strProductCode = null;
        }
        //getスキップ市場コード
        String l_strSkipMarketCode =
            l_hostEquityCarryoverSkipParams.getSkipMarketCode();
        //get市場コード
        String[] l_strMarketCodes = new String[2];
        String l_strMarketCode = null;
        if (WEB3SkipMarketCodeDef.TOKYO.equals(l_strSkipMarketCode))
        {
            l_strMarketCodes = null;
            l_strMarketCode = WEB3MarketCodeDef.TOKYO;
        }
        else if (WEB3SkipMarketCodeDef.OSAKA_AND_NNM.equals(l_strSkipMarketCode))
        {
            l_strMarketCodes[0] = WEB3MarketCodeDef.OSAKA;
            l_strMarketCodes[1] = WEB3MarketCodeDef.NNM;
        }
        else if (WEB3SkipMarketCodeDef.NAGOYA.equals(l_strSkipMarketCode))
        {
            l_strMarketCodes = null;
            l_strMarketCode = WEB3MarketCodeDef.NAGOYA;
        }
        else if (WEB3SkipMarketCodeDef.JASDAQ.equals(l_strSkipMarketCode))
        {
            l_strMarketCodes = null;
            l_strMarketCode = WEB3MarketCodeDef.JASDAQ;
        }
        else
        {
            l_strMarketCodes = null;
            l_strMarketCode = null;
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3EquityProductManager l_eqProductManager =
            (WEB3EquityProductManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        Branch[] l_branchs = null;
        Institution l_institution = null;
        Long l_productId = null;
        Long[] l_marketIds = new Long[2];
        Long l_marketId = null;
        try
        {
            //get証券会社オブジェクト
            l_institution =
                l_accountManager.getInstitution(l_strInstitutionCode);
            //get全部店オブジェクト
            l_branchs = l_institution.getBranches();
            //get銘柄ID
            if (l_strProductCode != null)
            {
                l_productId =
                    new Long(
                        l_eqProductManager.getProduct(l_institution, l_strProductCode).getProductId());
            }
            else
            {
                l_productId = null;
            }
            //get市場ID
            if (l_strMarketCodes != null)
            {
                l_marketIds[0] =
                    new Long(
                        l_finApp.getFinObjectManager().getMarket(l_institution, l_strMarketCodes[0]).getMarketId());
                l_marketIds[1] =
                    new Long(
                        l_finApp.getFinObjectManager().getMarket(l_institution, l_strMarketCodes[1]).getMarketId());
            }
            else if (l_strMarketCode != null)
            {
			    l_marketIds = null;
				l_marketId = 
				    new Long(
				        l_finApp.getFinObjectManager().getMarket(l_institution, l_strMarketCode).getMarketId());
            }
            else
            {
                l_marketIds = null;
            }

        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME,l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);

        }
        
        // 　@　@　@＜抽出条件＞
        // 　@　@　@部店ID＝注文繰越スキップ銘柄通知キューParams.証券会社コード に該当する
        // 　@　@　@　@　@証券会社オブジェクト.getBranches( )で取得した全部店オブジェクトの部店IDのいずれか
        // 　@　@　@かつ　@銘柄ID＝注文繰越スキップ銘柄通知キューParams.銘柄コード に該当する
        // 　@　@　@　@　@銘柄オブジェクトの銘柄ID
        // 　@　@　@かつ　@市場ID＝注文繰越スキップ銘柄通知キューParams.スキップ市場コード に該当する
        // 　@　@　@　@　@市場オブジェクトの市場IDのいずれか
        // 　@　@　@かつ　@注文有効状態（OrderOpenStatus）＝CLOSED
        // 　@　@　@かつ　@注文の失効状態（OrderExpirationStatus）＝EXPIRED
        // 　@　@　@かつ　@注文エラー理由コード＝注文繰越スキップ銘柄エラー
        StringBuffer l_sbWhere = new StringBuffer();
        Object[] l_objWhere = null;
        ArrayList l_lstObjWhere = new ArrayList();
        //全部店オブジェクトの部店ID
        int l_intSize = l_branchs.length;
        l_sbWhere.append(" (branch_id = ? ");
        l_lstObjWhere.add(new Long(l_branchs[0].getBranchId()));
        for (int i = 1; i < l_intSize; i++)
        {
            l_sbWhere.append(" or branch_id = ? ");
            l_lstObjWhere.add(new Long(l_branchs[i].getBranchId()));
        }
        l_sbWhere.append(") ");
        //注文繰越スキップ銘柄通知キューParams.銘柄コード＝ALL（全銘柄）の場合は、
        //抽出条件に銘柄コードは指定しない。
        if (l_productId != null)
        {
            l_sbWhere.append(" and product_id = ? ");
            l_lstObjWhere.add(l_productId);
        }
        //注文繰越スキップ銘柄通知キューParams.スキップ市場コード＝F（全市場）の場合は
        //抽出条件に市場コードは指定しない。
        if (l_marketIds != null)
        {
            l_sbWhere.append(" and (market_id = ? or market_id = ?) ");
            l_lstObjWhere.add(l_marketIds[0]);
            l_lstObjWhere.add(l_marketIds[1]);
        }
        else if (l_marketId != null)
        {
			l_sbWhere.append(" and market_id = ? ");
			l_lstObjWhere.add(l_marketId);
        }
        
        //注文有効状態（OrderOpenStatus）＝CLOSED
        l_sbWhere.append(" and order_open_status = ? ");
        l_lstObjWhere.add(OrderOpenStatusEnum.CLOSED);
        //注文の失効状態（OrderExpirationStatus）＝EXPIRED
        l_sbWhere.append(" and expiration_status = ? ");
        l_lstObjWhere.add(OrderExpirationStatusEnum.EXPIRED);
        //注文エラー理由コード＝スキップ銘柄エラー
        l_sbWhere.append(" and error_reason_code = ? ");
        l_lstObjWhere.add(WEB3ErrorReasonCodeDef.CARRY_OVER_SKIP_PRODUCT_ERROR);

        l_intSize = l_lstObjWhere.size();
        l_objWhere = new Object[l_intSize];
        l_lstObjWhere.toArray(l_objWhere);

        List l_lisRecords;
        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords =
                l_QueryProcessor.doFindAllQuery(
                    EqtypeOrderUnitRow.TYPE,
                    l_sbWhere.toString(),
                    l_objWhere);

        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME, l_dfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME, l_dqe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        //件数チェック
        int l_intSize1 = 0;
        if(l_lisRecords != null)
        {
            l_intSize1 = l_lisRecords.size();
        }
        
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            //取得したスキップ銘柄の注文単位オブジェクト数分、以下の処理を繰り返す。
            OrderUnit l_orderUnit = null;
            EqtypeOrderUnitRow l_oldOrderUnitRow = null;
            long l_lngAccountId = 0L;
            for (int i = 0; i < l_intSize1; i++)
            { 
                l_oldOrderUnitRow =
                    (EqtypeOrderUnitRow) l_lisRecords.get(i);

                // 注文単位取得
                OrderManager l_orderManager =
                    l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
                l_orderUnit =
                    l_orderManager.getOrderUnit(
                        l_oldOrderUnitRow.getOrderUnitId());

                //注文繰越スキップ銘柄通知繰越対象チェックサービスを取得する
                WEB3EquityOrderCarryOverSkipObjectCheckService l_objectCheckService =
                    (WEB3EquityOrderCarryOverSkipObjectCheckService)Services.getService(
                            WEB3EquityOrderCarryOverSkipObjectCheckService.class);

                // ２）　@１）で取得した注文単位オブジェクト数分、
                // 　@　@　@注文繰越スキップ銘柄通知繰越対象チェックサービスImpl.is繰越注文単位( )をコールする。
                // 　@　@　@戻り値＝falseの場合は、当該注文単位オブジェクトを
                // 　@　@　@１）で取得した繰越対象の注文単位オブジェクトの候補から外す。
                if (l_objectCheckService.isCarryOverOrderUnit(l_orderUnit))
                {
                    // ３）　@２）で繰越対象となった注文単位オブジェクト数分(*1)、Loopで以下の処理を行う。
                    // ３－１）　@顧客単位（Loopの一件目、または口座IDがブレイク）で口座をロックする。
                    if (l_lngAccountId != l_oldOrderUnitRow.getAccountId())
                    {
                        l_lngAccountId = l_oldOrderUnitRow.getAccountId();
                        l_mainAccount =
                            (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_lngAccountId);
                        l_accountManager.lockAccount(
                            l_mainAccount.getInstitution().getInstitutionCode(),
                            l_mainAccount.getBranch().getBranchCode(),
                            l_mainAccount.getAccountCode());
                    }
                    // ３－２）　@繰越注文を作成する。
                    l_orderUnit = l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
                    WEB3EquityOrderCarryOverUnitService l_carryOverPartService =
                        (WEB3EquityOrderCarryOverUnitService)Services.getService(
                        WEB3EquityOrderCarryOverUnitService.class);
                    boolean l_blnOk = l_carryOverPartService.insertCarryOverOrder(l_orderUnit);

                    // ３－３）　@繰越元注文をupdateする。
                    if (l_blnOk)
                    {
                        l_carryOverPartService.updateOriginalOrder(
                            (EqTypeOrderUnit)l_orderUnit, WEB3ErrorReasonCodeDef.NORMAL);
                    }
                    
                    // ３－４）　@余力更新処理
                    WEB3GentradeMainAccount l_gentradeMainAccount = 
                        new WEB3GentradeMainAccount(l_oldOrderUnitRow.getAccountId());
                    WEB3GentradeSubAccount l_gentradeSubAccount = 
                        (WEB3GentradeSubAccount)
                        l_gentradeMainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                    WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
                        (WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
                    l_tpTradingPowerReCalcService.reCalcTradingPower(l_gentradeSubAccount);
                }
            }
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME,l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage());
        }
        catch (DataException l_de)
        {
            log.error(STR_METHOD_NAME, l_de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }
        catch (WEB3BaseException l_wbe)
        {
            if (l_wbe.getErrorInfo() == WEB3ErrorCatalog.SYSTEM_ERROR_80076)
            {
                log.error(
                    "口座ロックに失敗しました。： " +
                    "証券会社コード=[" + l_mainAccount.getInstitution().getInstitutionCode() + "], " +
                    "部店コード=[" + l_mainAccount.getBranch().getBranchCode() + "], " +
                    "顧客コード=[" + l_mainAccount.getAccountCode() + "]");
            }
            throw l_wbe;
        }

        log.exiting(STR_METHOD_NAME);
    }
    /**
     * (updateスキップ銘柄テーブル)<BR>
     * 引数の株式注文繰越スキップ銘柄通知キューの内容に従い、<BR>
     * 【注文繰越スキップ銘柄テーブル】を更新する。<BR>
     * <BR>
     * 更新仕様は、スキップ登録区分の値（登録／登録の抹消）により、<BR>
     * 以下の２パターンに分かれる。<BR>
     * <BR>
     * 【注文繰越スキップ銘柄テーブル】の登録／更新内容は、<BR>
     * 「注文繰越スキップ銘柄通知_注文繰越スキップ銘柄テーブル.xls」を参照。<BR>
     * <BR>
     * ====================================================================<BR>
	     * ＜スキップ登録区分＝登録　@の場合＞<BR>
	     * <BR>
     * ・通知キューParams.スキップ市場コードの値により、<BR>
     * 　@以下の１）または２）のどちらかの処理を行う。<BR>
     * <BR>
     * １）　@通知キューParams.スキップ市場コード＝2（大阪及びNNM）の場合<BR>
     * <BR>
     * 　@　@通知キューParamsの内容より、<BR>
     * 　@　@【注文繰越スキップ銘柄テーブル】に、(*1)の該当する市場コード数分、<BR>
     * 　@　@レコードを登録する。<BR>
     * 　@　@主キーが重複するレコードが既に存在する場合は、当該レコードを更新する。<BR>
     * <BR>
     * ２）　@通知キューParams.スキップ市場コードが上記以外の場合<BR>
     * <BR>
     * 　@　@通知キューParamsの内容より、<BR>
     * 　@　@【注文繰越スキップ銘柄テーブル】に１レコードを登録する。<BR>
     * 　@　@主キーが重複するレコードが既に存在する場合は、当該レコードを更新する。<BR>
     * <BR>
     * <BR>
     * ====================================================================<BR>
     * ＜スキップ登録区分＝抹消　@の場合＞<BR>
     * <BR>
     * ・通知キューParams.スキップ市場コードの値により、<BR>
     * 　@以下の１）または２）のどちらかの処理を行う。<BR>
     * <BR>
     * ・更新時に該当データが存在しない場合は、例外をthrowする。<BR>
     * -該当データなし-<BR>
     * class    : WEB3SystemLayerException<BR>
     * tag      : SYSTEM_ERROR_80005<BR>
     * <BR>
     * １）　@通知キューParams.スキップ市場コード＝2（大阪及びNNM）の場合<BR>
     * <BR>
     * 　@　@【注文繰越スキップ銘柄テーブル】の以下の条件に該当するレコードの登録区分を、<BR>
     * 　@　@「注文繰越スキップ取消（登録抹消）」に更新する。<BR>
     * <BR>
     * 　@　@＜更新条件＞<BR>
     * 　@　@証券会社コード＝通知キューParams.証券会社コード<BR>
     * 　@　@かつ　@銘柄コード＝通知キューParams.銘柄コード<BR>
     * 　@　@かつ　@市場コード＝(*1)の該当する市場コードを全て指定<BR>
     * <BR>
     * ２）　@通知キューParams.スキップ市場コードが上記以外の場合<BR>
     * <BR>
     * 　@　@【注文繰越スキップ銘柄テーブル】の以下の条件に該当するレコードの登録区分を、<BR>
     * 　@　@「注文繰越スキップ取消（登録抹消）」に更新する。<BR>
     * <BR>
     * 　@　@＜更新条件＞<BR>
     * 　@　@証券会社コード＝通知キューParams.証券会社コード<BR>
     * 　@　@かつ　@銘柄コード＝通知キューParams.銘柄コード<BR>
     * 　@　@かつ　@市場コード＝通知キューParams.スキップ市場コード<BR>
     * <BR>
     * -------------------------------------------------------------<BR>
     * (*1)<BR>
     * 該当するWEB3MarketCodeDefで定義されているコードを指定する。<BR>
     * ・通知キューParams.スキップ市場コード＝2（大阪及びNNM）の場合は、<BR>
     * 　@大阪、及びNNMの両方を指定する。<BR>
     * ・上記以外の場合は、通知キューParams.スキップ市場コードの値をそのまま指定する。<BR>
     * <BR>
     * @@param l_hostEquityCarryoverSkipParams - 注文繰越スキップ銘柄通知キューParams<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40639A0800E3
     */
    public void updateSkipProdTable(HostEquityCarryoverSkipParams l_hostEquityCarryoverSkipParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "updateSkipProdTable(HostEquityCarryoverSkipParams)";
        log.entering(STR_METHOD_NAME);
        if (l_hostEquityCarryoverSkipParams == null)
        {
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);            
        }
        
        //注文繰越スキップ銘柄キューテーブル.証券会社コード
        String l_strInstitutionCode =
            l_hostEquityCarryoverSkipParams.getInstitutionCode();

        //注文繰越スキップ銘柄キューテーブル.銘柄コード
        String l_strProductCode =
            l_hostEquityCarryoverSkipParams.getProductCode();

        //注文繰越スキップ銘柄キューテーブル.スキップ市場コードに相当する市場コード
        ArrayList l_lstMarketCodes = new ArrayList();
        String l_strSkipMarketCode =
            l_hostEquityCarryoverSkipParams.getSkipMarketCode();
        // １）　@通知キューParams.スキップ市場コード＝2（大阪及びNNM）の場合
        if (WEB3SkipMarketCodeDef.OSAKA_AND_NNM.equals(l_strSkipMarketCode))
        {
            l_lstMarketCodes.add(WEB3MarketCodeDef.OSAKA);
            l_lstMarketCodes.add(WEB3MarketCodeDef.NNM);
        }
        // ２）　@通知キューParams.スキップ市場コードが上記以外の場合
        else if (WEB3SkipMarketCodeDef.TOKYO.equals(l_strSkipMarketCode))
        {
            l_lstMarketCodes.add(WEB3MarketCodeDef.TOKYO);
        }
        else if (WEB3SkipMarketCodeDef.JASDAQ.equals(l_strSkipMarketCode))
        {
            l_lstMarketCodes.add(WEB3MarketCodeDef.JASDAQ);
        }
        else
        {
            l_lstMarketCodes.add(l_strSkipMarketCode);
        }

        int l_intSize = l_lstMarketCodes.size();
        OrderCarryoverSkipProdParams l_orderCarryoverSkipProdParams = null;
        String l_strSkipRegistType =
            l_hostEquityCarryoverSkipParams.getSkipRegistType();
        // ＜スキップ登録区分＝登録　@の場合＞
        if (WEB3SkipRegistTypeDef.REGISTRATION.equals(l_strSkipRegistType))
        {
            // 　@　@通知キューParamsの内容より、
            // 　@　@【注文繰越スキップ銘柄テーブル】に、(*1)の該当する市場コード数分、
            // 　@　@レコードを登録する。
            for (int i = 0; i < l_intSize; i++)
            {
                l_orderCarryoverSkipProdParams = null;
                try
                {                
                    l_orderCarryoverSkipProdParams = 
                        (OrderCarryoverSkipProdParams)
                        OrderCarryoverSkipProdDao.findRowByPk(
                            l_strInstitutionCode,
                            l_strProductCode,
                            (String)l_lstMarketCodes.get(i));
                }
                catch (DataFindException l_ex)
                {
                }
                catch (DataQueryException l_ex)
                {
                    log.error(STR_METHOD_NAME,l_ex);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataNetworkException l_e)
                {
                    log.error(STR_METHOD_NAME,l_e);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_e.getMessage(),
                        l_e);
                }

                try
                {
                    QueryProcessor l_QueryProcessor =
                        Processors.getDefaultProcessor();
                    Timestamp l_tsCurTime =
                        GtlUtils.getTradingSystem().getSystemTimestamp();
                    if (l_orderCarryoverSkipProdParams == null)
                    {
                        log.debug("スキップ登録区分＝登録かつ、主キーが重複するレコードが存在しない場合");
                        l_orderCarryoverSkipProdParams =
                            new OrderCarryoverSkipProdParams();
                        //set証券会社コード
                        l_orderCarryoverSkipProdParams.setInstitutionCode(
                            l_strInstitutionCode);
                        //set銘柄コード
                        l_orderCarryoverSkipProdParams.setProductCode(
                            l_strProductCode);
                        //set市場コード
                        l_orderCarryoverSkipProdParams.setMarketCode(
                            (String)l_lstMarketCodes.get(i));
                        //set登録区分(1：注文繰越スキップ)
                        l_orderCarryoverSkipProdParams.setRegistDivision(
                            WEB3RegistDivisionDef.ORDER_TRANSFER_STOP);
                        //set作成日付
                        l_orderCarryoverSkipProdParams.setCreatedTimestamp(l_tsCurTime);
                        //set更新日付
                        l_orderCarryoverSkipProdParams.setLastUpdatedTimestamp(l_tsCurTime);
                        //レコードを登録する
                        l_QueryProcessor.doInsertQuery(
                            l_orderCarryoverSkipProdParams);
                    }
                    else
                    {
                        log.debug("スキップ登録区分＝登録かつ、主キーが重複するレコードが存在する場合");

						OrderCarryoverSkipProdParams 
						    l_cloneOrderCarryoverSkipProdParams = 
						        new OrderCarryoverSkipProdParams();

						GtlUtils.copyRow2Params(
						    l_orderCarryoverSkipProdParams,
							l_cloneOrderCarryoverSkipProdParams);

                        //set登録区分(1：注文繰越スキップ)
						l_cloneOrderCarryoverSkipProdParams.setRegistDivision(
                            WEB3RegistDivisionDef.ORDER_TRANSFER_STOP);
                        //set更新日付
						l_cloneOrderCarryoverSkipProdParams.setLastUpdatedTimestamp(l_tsCurTime);
                        //レコードを更新する
                        l_QueryProcessor.doUpdateQuery(
						    l_cloneOrderCarryoverSkipProdParams);
                    }
                }
                catch (DataQueryException l_e)
                {
                    log.error(STR_METHOD_NAME,l_e);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_e.getMessage(),
                        l_e);
                }
                catch (DataNetworkException l_e)
                {
                    log.error(STR_METHOD_NAME,l_e);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_e.getMessage(),
                        l_e);
                }

            }
        }
        // ＜スキップ登録区分＝抹消　@の場合＞
        else if (WEB3SkipRegistTypeDef.DELETE.equals(l_strSkipRegistType))
        {
            for (int i = 0; i < l_intSize; i++)
            {
                try
                {
                    l_orderCarryoverSkipProdParams =
                        (OrderCarryoverSkipProdParams)OrderCarryoverSkipProdDao.findRowByPk(
                            l_strInstitutionCode,
                            l_strProductCode,
                            (String)l_lstMarketCodes.get(i));

                    QueryProcessor l_QueryProcessor =
                        Processors.getDefaultProcessor();
                    
					OrderCarryoverSkipProdParams 
					    l_cloneOrderCarryoverSkipProdParams = 
					        new OrderCarryoverSkipProdParams();
					
					GtlUtils.copyRow2Params(
					    l_orderCarryoverSkipProdParams,
					    l_cloneOrderCarryoverSkipProdParams);

                    //set登録区分(0：注文繰越スキップ取消（登録抹消）)
					l_cloneOrderCarryoverSkipProdParams.setRegistDivision(
                        WEB3RegistDivisionDef.ORDER_TRANSFER_ACTIVE);
                    //set更新日付
					l_cloneOrderCarryoverSkipProdParams.setLastUpdatedTimestamp(
                        GtlUtils.getTradingSystem().getSystemTimestamp());
                    //レコードを更新する
                    l_QueryProcessor.doUpdateQuery(
					    l_cloneOrderCarryoverSkipProdParams);
                }
                catch (DataFindException l_dfe)
                {
                    log.error(STR_METHOD_NAME,l_dfe);
                    // ・更新時に該当データが存在しない場合は、例外をthrowする。
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_dfe.getMessage(),
                        l_dfe);
                }
                catch (DataNetworkException l_dne)
                {
                    log.error(STR_METHOD_NAME,l_dne);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_dne.getMessage(),
                        l_dne);
                }
                catch (DataQueryException l_dqe)
                {
                    log.error(STR_METHOD_NAME,l_dqe);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_dqe.getMessage(),
                        l_dqe);
                }
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
