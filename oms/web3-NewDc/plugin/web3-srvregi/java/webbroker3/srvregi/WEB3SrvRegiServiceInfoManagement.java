head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.40.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiServiceInfoManagement.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス情報管理(WEB3SrvRegiServiceInfoManagement.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 郭英 (中訊) 新規作成
Revesion History : 2007/06/05 張騰宇 (中訊) モデル240
Revesion History : 2007/06/21 崔遠鵬 (中訊) モデル269
Revesion History : 2007/06/22 崔遠鵬 (中訊) モデル271
Revesion History : 2007/07/24 栄イ (中訊) モデル294
Revesion History : 2007/11/01 張騰宇 (中訊) モデル305
Revesion History : 2008/02/18 武波 (中訊) モデル310,313
Revesion History : 2008/03/03 武波 仕様変更 モデル330,343
Revesion History : 2008/03/20 周墨洋 仕様変更 モデル348
*/

package webbroker3.srvregi;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AppliLotDivDef;
import webbroker3.common.define.WEB3ConditionsValueDivDef;
import webbroker3.common.define.WEB3EffectiveDivDef;
import webbroker3.common.define.WEB3SpecialProcessDivDef;
import webbroker3.common.define.WEB3SrvAppliAttributeProcDivDef;
import webbroker3.common.define.WEB3SrvRegiCancelDivDef;
import webbroker3.common.define.WEB3SupplyDivDef;
import webbroker3.gentrade.WEB3GentradeSrvRegiApplication;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeBatoFunctionDivDef;
import webbroker3.gentrade.define.WEB3GentradeBatoTranHistServiceResultDef;
import webbroker3.gentrade.service.delegate.WEB3GentradeBatoClientService;
import webbroker3.srvregi.data.SrvAppliAttributeRow;
import webbroker3.srvregi.data.SrvRegiCommCondMasterRow;
import webbroker3.srvregi.data.SrvRegiCommCondRow;
import webbroker3.srvregi.data.SrvRegiDealingCommRow;
import webbroker3.srvregi.data.SrvRegiLotInfoRow;
import webbroker3.srvregi.data.SrvRegiMasterParams;
import webbroker3.srvregi.data.SrvRegiMasterRow;
import webbroker3.srvregi.define.WEB3SrvRegiOfferingDivAppendDef;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiOtherOrgService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiRegistService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (サービス情報管理)<BR>
 * サービス情報管理クラス<BR>
 *
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3SrvRegiServiceInfoManagement
{


    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SrvRegiServiceInfoManagement.class);

    /**
     * @@roseuid 416F4A9E036B
     */
    public WEB3SrvRegiServiceInfoManagement()
    {

    }

    /**
     * (getサービスマスター)<BR>
     * 引数で指定された証券会社コード、サービス区分に該当する<BR>
     * サービスマスターオブジェクトを返す。<BR>
     * <BR>
     * 1) サービスマスターテーブルを検索し、サービスマスターParamsを取得する。<BR>
     * （引数.is行ロックがtrueの場合、select for updateで検索を行う。)<BR>
     * [検索条件]<BR>
     * 　@証券会社コード=引数.証券会社コード and<BR>
     * 　@サービス区分=引数.サービス区分<BR>
     * <BR>
     * 2) サービスマスターParamsが取得できない場合は例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00982<BR>
     * <BR>
     * 3) サービスマスターオブジェクトを生成する。<BR>
     * [引数]<BR>
     * 　@サービスマスターRow: 取得したサービスマスターParams<BR>
     * <BR>
     * 4) 引数.is行ロック=trueの場合<BR>
     * 　@サービスマスターオブジェクト.createForUpdateParams( )をコールする。<BR>
     * <BR>
     * 5) サービスマスターオブジェクトを返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@param l_blnIsRowLock - (is行ロック)<BR>
     * true:行ロックを行う　@false:行ロックを行わない<BR>
     * @@return webbroker3.srvregi.WEB3SrvRegiServiceMaster
     * @@roseuid 40F6563C02B1
     */
    public WEB3SrvRegiServiceMaster getSrvMaster(String l_strInstitutionCode, String l_strSrvDiv, boolean l_blnIsRowLock) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSrvMaster(String, String, boolean) ";
        log.entering(STR_METHOD_NAME);

        WEB3SrvRegiServiceMaster l_serviceMaster = null;

        try
        {
            //according to the QA of WEB3-SRVREGI-A-CD-0026
            //1) サービスマスターテーブルを検索し、サービスマスターParamsを取得する。
            String l_strWhere = " institution_code = ? and srv_div = ? ";

            Object[] l_obj = {l_strInstitutionCode, l_strSrvDiv};

            QueryProcessor l_queryProcessor =
                Processors.getDefaultProcessor();//DataNetworkException, DataQueryException

            List l_lisServiceMasterRowList = null;
            if (l_blnIsRowLock)
            {
                l_lisServiceMasterRowList = l_queryProcessor.doFindAllQuery(
                    SrvRegiMasterRow.TYPE,
                    l_strWhere,
                    " FOR UPDATE ",
                    l_obj);//DataNetworkException, DataQueryException
            }
            else
            {
                l_lisServiceMasterRowList =
                    l_queryProcessor.doFindAllQuery(SrvRegiMasterRow.TYPE, l_strWhere, l_obj);//DataNetworkException, DataQueryException
            }

            //2) サービスマスターParamsが取得できない場合は例外をスローする。
            if (l_lisServiceMasterRowList == null || l_lisServiceMasterRowList.size() == 0)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00982,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            int l_intServiceMasterRowCnt = l_lisServiceMasterRowList.size();

            if (l_intServiceMasterRowCnt > 1)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //3) サービスマスターオブジェクトを生成する。
            l_serviceMaster = new WEB3SrvRegiServiceMaster((SrvRegiMasterRow)l_lisServiceMasterRowList.get(0));

            //4) 引数.is行ロック=trueの場合
            if (l_blnIsRowLock)
            {
                log.debug("isRowLock = true");
                l_serviceMaster.createForUpdateParams();
            }
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME, l_ex);

            log.exiting(STR_METHOD_NAME);

            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME, l_ex);

            log.exiting(STR_METHOD_NAME);

            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME);
        }

        //5) サービスマスターオブジェクトを返却する。
        return l_serviceMaster;
    }

    /**
     * (getサービスマスター一覧)<BR>
     * サービスマスターの一覧を検出し、返却する。<BR>
     * <BR>
     * 1) 以下の条件で「サービスマスターテーブル」を検出する。<BR>
     * [検索条件]<BR>
     * 　@証券会社コード=引数.証券会社コード<BR>
     * <BR>
     * 　@※）引数.申込区分="全て"以外の場合、以下も条件に含める<BR>
     * 　@and 申込区分=引数.申込区分<BR>
     * [並び順]<BR>
     * 　@サービス区分　@昇順<BR>
     * <BR>
     * 2) 検索結果の件数分、以下を繰り返す。<BR>
     *  2-1) 検索結果のサービスマスターParamsを引数に、<BR>
     * 　@　@サービスマスタークラスのコンストラクタをコールする。<BR>
     * [引数]<BR>
     * 　@サービスマスターRow: 取得したサービスマスターParams<BR>
     * <BR>
     *  2-2) 生成したサービスマスターオブジェクトを配列に追加する。<BR>
     * <BR>
     * 3) 2)で作成したサービスマスタークラスの配列を返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strOfferingDiv - (申込区分)<BR>
     * 0:不要　@1:要　@2:全て<BR>
     * @@return webbroker3.srvregi.WEB3SrvRegiServiceMaster[ ]
     * @@roseuid 4108E2E0025C
     */
    public WEB3SrvRegiServiceMaster[] getSrvMasterList(String l_strInstitutionCode, String l_strOfferingDiv) throws WEB3BaseException
    {
        WEB3SrvRegiServiceMaster[] l_serviceMasters = null;
        final String STR_METHOD_NAME = " getSrvMasterList(String, String) ";
        log.entering(STR_METHOD_NAME);

        try
        {
            //1) 以下の条件で「サービスマスターテーブル」を検出する。
            String l_strWhere = " institution_code = ? ";
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException

            List l_lisServiceMasterRowList = null;

            if (!WEB3SrvRegiOfferingDivAppendDef.EVERYTHING.equals(l_strOfferingDiv))
            {
                l_strWhere += " and offering_div = ? ";
                Object[] l_obj = {l_strInstitutionCode, l_strOfferingDiv};
                l_lisServiceMasterRowList = l_queryProcessor.doFindAllQuery(
                    SrvRegiMasterRow.TYPE,
                    l_strWhere,
                    " srv_div asc ",
                    "",
                    l_obj);//DataNetworkException, DataQueryException
            }
            else
            {
                Object[] l_obj = {l_strInstitutionCode};
                l_lisServiceMasterRowList = l_queryProcessor.doFindAllQuery(
                    SrvRegiMasterRow.TYPE,
                    l_strWhere,
                    " srv_div asc ",
                    "",
                    l_obj);//DataNetworkException, DataQueryException
            }

            if (l_lisServiceMasterRowList != null)
            {

                int l_intServiceMasterRowCnt = l_lisServiceMasterRowList.size();

                //2) 検索結果の件数分、以下を繰り返す。
                l_serviceMasters = new WEB3SrvRegiServiceMaster[l_intServiceMasterRowCnt];

                for (int i = 0; i < l_intServiceMasterRowCnt; i++)
                {
                    //2-1) 検索結果のサービスマスターParamsを引数に、
                    //2-2) 生成したサービスマスターオブジェクトを配列に追加する。
                    l_serviceMasters[i] =
                        new WEB3SrvRegiServiceMaster((SrvRegiMasterRow)l_lisServiceMasterRowList.get(i));
                }
            }
            else
            {
                l_serviceMasters = new WEB3SrvRegiServiceMaster[0];
            }

        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME, l_ex);

            log.exiting(STR_METHOD_NAME);

            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME, l_ex);

            log.exiting(STR_METHOD_NAME);

            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);

        //3) 2)で作成したサービスマスタークラスの配列を返却する。
        return l_serviceMasters;
    }

    /**
     * (getサービス抽選情報)<BR>
     * 指定日時からサービスに紐付く抽選情報を特定し、<BR>
     * それを基準にロール値で指定されたサービス抽選情報オブジェクトを返却する。<BR>
     * (指定された抽選情報が取得できなかった場合、nullを返却する。)<BR>
     * <BR>
     * 1) 以下の条件で「サービス抽選情報テーブル」を検索する。<BR>
     * [検索条件]<BR>
     * 　@証券会社コード=引数.証券会社コード and<BR>
     * 　@サービス区分=引数.サービス区分 and<BR>
     * 　@申込期間（自）≦引数.指定日時 and<BR>
     * 　@申込期間（至）≧引数.指定日時<BR>
     * <BR>
     * 2) 現在申込期間中のサービス抽選情報の取得<BR>
     * 　@引数.ロール値=0の場合<BR>
     * 　@特定したサービス抽選情報Paramsを引数に、サービス抽選情報の<BR>
     * 　@コンストラクタをコールし、生成したインスタンスを返却する。<BR>
     * [引数]<BR>
     * 　@サービス抽選情報Row=特定したサービス抽選情報Params<BR>
     * <BR>
     * 3) 次回分のサービス抽選情報の取得（引数.ロール値＞0の場合）<BR>
     * 　@以下の条件で「サービス抽選情報テーブル」を検索する。<BR>
     * [検索条件]<BR>
     * 　@証券会社コード=引数.証券会社コード and<BR>
     * 　@サービス区分=引数.サービス区分 and<BR>
     * 　@申込期間（自）≧引数.指定日時<BR>
     * [並び順]<BR>
     * 　@申込期間（自） 昇順<BR>
     * <BR>
     *  3-1) 検索結果[(引数.ロール値-1)]の要素を取得する。<BR>
     * <BR>
     * 4) 前回分のサービス抽選情報の取得（引数.ロール値＜0の場合）<BR>
     * 　@以下の条件で「サービス抽選情報テーブル」を検索する。<BR>
     * [検索条件]<BR>
     * 　@証券会社コード=引数.証券会社コード and<BR>
     * 　@サービス区分=引数.サービス区分 and<BR>
     * 　@申込期間（至）≦引数.指定日時<BR>
     * [並び順]<BR>
     * 　@申込期間（自） 降順<BR>
     * <BR>
     *  4-1) 検索結果[(引数.ロール値の絶対値-1)]の要素を取得する。<BR>
     * <BR>
     * 5) 取得した検索結果を引数に、サービス抽選情報のコンストラクタを<BR>
     * 　@　@コールし、生成したインスタンスを返却する。<BR>
     * [引数]<BR>
     * 　@サービス抽選情報Row=取得したサービス抽選情報Params<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@param l_tsDesignateDateTime - (指定日時)<BR>
     * @@param l_intRollValue - (ロール値)<BR>
     * <BR>
     * EX)<BR>
     * 0…申込期間内に、指定された申込日が存在する抽選情報<BR>
     * 1…申込期間内に、指定された申込日が存在する抽選情報の次の抽選情報<BR>
     * -1…申込期間内に、指定された申込日が存在する抽選情報の前の抽選情報<BR>
     * @@return webbroker3.srvregi.WEB3SrvRegiServiceLotInfo
     * @@roseuid 4106232A023B
     */
    public WEB3SrvRegiServiceLotInfo getSrvLotInfo(String l_strInstitutionCode, String l_strSrvDiv, Timestamp l_tsDesignateDateTime, int l_intRollValue) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSrvLotInfo(String, String, Timestamp, int) ";
        log.entering(STR_METHOD_NAME);

        if (l_tsDesignateDateTime == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        WEB3SrvRegiServiceLotInfo l_serviceLotInfo = null;

        try
        {
            //2) 現在申込期間中のサービス抽選情報の取得引数.ロール値=0の場合
            if (l_intRollValue == 0)
            {
                //1) 以下の条件で「サービス抽選情報テーブル」を検索する。
                String l_strWhere = " institution_code = ? and srv_div = ? " +
                    " and appli_date_from <= ? and appli_date_to >= ? ";

                Object[] l_obj = {l_strInstitutionCode,
                    l_strSrvDiv,
                    l_tsDesignateDateTime,
                    l_tsDesignateDateTime};

                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException

                List l_lisServiceLotInfoRowList =
                    l_queryProcessor.doFindAllQuery(SrvRegiLotInfoRow.TYPE, l_strWhere, l_obj);//DataNetworkException, DataQueryException

                if (l_lisServiceLotInfoRowList == null || l_lisServiceLotInfoRowList.size() == 0)
                {
                    log.exiting(STR_METHOD_NAME);
                    return null;
                }

                l_serviceLotInfo =
                    new WEB3SrvRegiServiceLotInfo((SrvRegiLotInfoRow)l_lisServiceLotInfoRowList.get(0));
            }
            //3) 次回分のサービス抽選情報の取得（引数.ロール値＞0の場合）
            else if (l_intRollValue > 0)
            {
                String l_strWhere =
                    " institution_code = ? and srv_div = ? and appli_date_from >= ? ";

                Object[] l_obj = {l_strInstitutionCode,
                    l_strSrvDiv,
                    l_tsDesignateDateTime};

                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException

                List l_lisServiceLotInfoRowList = l_queryProcessor.doFindAllQuery(
                    SrvRegiLotInfoRow.TYPE,
                    l_strWhere,
                    " appli_date_from asc ",
                    "",
                    l_obj);//DataNetworkException, DataQueryException


                if (l_lisServiceLotInfoRowList == null || l_lisServiceLotInfoRowList.size() < l_intRollValue)
                {
                    log.exiting(STR_METHOD_NAME);
                    return null;
                }

                //5) 取得した検索結果を引数に、サービス抽選情報のコンストラクタを
                l_serviceLotInfo =
                    new WEB3SrvRegiServiceLotInfo((SrvRegiLotInfoRow)l_lisServiceLotInfoRowList.get(l_intRollValue - 1));
            }
            //according to the QA of WEB3-SEVREGI-1-CD-0020
            //4) 前回分のサービス抽選情報の取得（引数.ロール値＜0の場合）
            else
            {
                String l_strWhere =
                    " institution_code = ? and srv_div = ? and appli_date_to <= ? ";

                Object[] l_obj = {
                    l_strInstitutionCode,
                    l_strSrvDiv,
                    l_tsDesignateDateTime};

                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException

                List l_lisServiceLotInfoRowList = l_queryProcessor.doFindAllQuery(
                    SrvRegiLotInfoRow.TYPE,
                    l_strWhere,
                    " appli_date_from desc ",
                    "",
                    l_obj);//DataNetworkException, DataQueryException

                if (l_lisServiceLotInfoRowList == null || l_lisServiceLotInfoRowList.size() < Math.abs(l_intRollValue))
                {
                    log.exiting(STR_METHOD_NAME);
                    return null;
                }

                //5) 取得した検索結果を引数に、サービス抽選情報のコンストラクタを
                l_serviceLotInfo = new WEB3SrvRegiServiceLotInfo(
                    (SrvRegiLotInfoRow)l_lisServiceLotInfoRowList.get(Math.abs(l_intRollValue) - 1));
            }
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME, l_ex);

            log.exiting(STR_METHOD_NAME);

            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME);
         }
         catch (DataQueryException l_ex)
         {
             //DBアクセスが失敗の場合
             log.error(WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME, l_ex);

             log.exiting(STR_METHOD_NAME);

             //例外をスローする
             throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                 WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME);
         }

         log.exiting(STR_METHOD_NAME);

         return l_serviceLotInfo;
    }

    /**
     * (get履歴サービス抽選情報一覧)<BR>
     * 当サービスのサービス抽選情報のうち、抽選日が現在日付以前の配列を返す。<BR>
     * <BR>
     * 1) サービス抽選情報テーブルを検索し、サービス抽選情報Paramsの<BR>
     * Listを取得する。<BR>
     * [検索条件]<BR>
     * 　@証券会社コード=引数.証券会社コード and<BR>
     * 　@サービス区分=this.サービス区分 and<BR>
     * 　@(抽選日≦現在日付<BR>
     *   or<BR>
     *   (抽選日=null and 申込期間(至)＜現在日時))<BR>
     * [並び替え]<BR>
     * 　@申込期間（自）で昇順<BR>
     * <BR>
     * 2) 取得したListからサービス抽選情報Paramsをとりだし、サービス<BR>
     * 抽選情報オブジェクトを<BR>
     * 　@生成する。生成したサービス抽選情報オブジェクトを配列に設定して返す。<BR>
     * [引数]<BR>
     * 　@サービス抽選情報Row=取得したサービス抽選情報Params<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@return webbroker3.srvregi.WEB3SrvRegiServiceLotInfo[ ]
     * @@roseuid 412EED890065
     */
    public WEB3SrvRegiServiceLotInfo[] getActionSrvLotInfoList(String l_strInstitutionCode, String l_strSrvDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getActionSrvLotInfoList(String, String) ";
        log.entering(STR_METHOD_NAME);

        WEB3SrvRegiServiceLotInfo[] l_srvLotInfos = null;

        try
        {
        	//障害対応 NO_2040
            //1) サービス抽選情報テーブルを検索し、サービス抽選情報Paramsの
            String l_strWhere =
                " institution_code = ? and srv_div = ? and (lot_date <= ? or (lot_date is null and appli_date_to < ?))";

			//障害対応  NO_U01711
            //現在日時の取得
			Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem( ).getSystemTimestamp();
			Date l_datSystemDate = WEB3DateUtility.toDay(l_tsSystemTimestamp);
			log.debug("【l_datSystemDate（日付）】 : " + l_datSystemDate);
			
			Date l_datSystemDateTime = new Date(l_tsSystemTimestamp.getTime());
			log.debug("【l_datSystemDateTime（日時）】 : " + l_datSystemDateTime);

            Object[] l_obj = {l_strInstitutionCode, l_strSrvDiv, l_datSystemDate, l_datSystemDateTime};

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException

            List l_lisSrvLotInfoRowList = l_queryProcessor.doFindAllQuery(
                SrvRegiLotInfoRow.TYPE,
                l_strWhere,
                " appli_date_from asc ",
                "",
                l_obj);//DataNetworkException, DataQueryException

            if (l_lisSrvLotInfoRowList != null)
            {
                int l_intSrvLotInfoRowCnt = l_lisSrvLotInfoRowList.size();

                //2) 取得したListからサービス抽選情報Paramsをとりだし、サービス
                l_srvLotInfos = new WEB3SrvRegiServiceLotInfo[l_intSrvLotInfoRowCnt];

                for (int i = 0; i < l_intSrvLotInfoRowCnt; i++)
                {
                    l_srvLotInfos[i] =  new WEB3SrvRegiServiceLotInfo(
                        (SrvRegiLotInfoRow)l_lisSrvLotInfoRowList.get(i));
                }
            }
            else
            {
                l_srvLotInfos = new WEB3SrvRegiServiceLotInfo[0];
            }
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME, l_ex);

            log.exiting(STR_METHOD_NAME);

            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME, l_ex);

            log.exiting(STR_METHOD_NAME);

            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);

        return l_srvLotInfos;
    }

    /**
     * (is顧客申込可能)<BR>
     * 当該サービスが顧客にとって申込可能なサービスかどうかを判定する。<BR>
     * <BR>
     * シーケンス図「（サービス情報管理）is顧客申込可能」参照<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_srvMaster - (サービスマスター)<BR>
     * サービスマスターオブジェクト<BR>
     * @@return boolean
     * @@roseuid 411A04800396
     */
    public boolean isAccountAppliPossible(WEB3GentradeSubAccount l_subAccount, WEB3SrvRegiServiceMaster l_srvMaster) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " isAccountAppliPossible(WEB3GentradeSubAccount, WEB3SrvRegiServiceMaster)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_srvMaster == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.1:<現在日付の取得>
        //U00838
        Timestamp l_tsNowDateTmp = GtlUtils.getTradingSystem().getSystemTimestamp();
        Date l_tsNowDate = WEB3DateUtility.toDay(l_tsNowDateTmp);

        //1.2:is申込可能()
        boolean l_blnAppliPossible = l_srvMaster.isAppliPossible();

        //1.3:<分岐処理 *1>
        //1.3.1:false
        if (!l_blnAppliPossible)
        {
            log.debug("<分岐処理 *1>");
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //according to the QA of WEB3-SRVREGI-A-CD-0027
        //1.4:get申込要サービス(is行ロック : boolean)
        WEB3SrvRegiApplicationRequiredService l_applicationRequiredService =
            l_srvMaster.getAppliRequiredSrv(false);

        if (l_applicationRequiredService == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME);
        }

        //1.4.1:get抽選設定()
        String l_strLotDiv = l_applicationRequiredService.getLotDiv();

        //1.5:<分岐処理 *2>
        if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv))
        {
            
			//障害対応　@NO_U01563 
			//申込要サービスオブジェクト.get申込可能期間（自）　@≦ 現在日付(*) ≦ 
			//申込要サービスオブジェクト.get申込可能期間（至）」 
			if(l_applicationRequiredService.getAppliDateFrom() != null &&
				l_applicationRequiredService.getAppliDateTo() != null){
								
				int l_appliFrom = l_applicationRequiredService.getAppliDateFrom().intValue();
				int l_appliTo = l_applicationRequiredService.getAppliDateTo().intValue();
				
				Calendar l_cal = new GregorianCalendar();
				l_cal.setTime(l_tsNowDateTmp);
				int l_day = l_cal.get(Calendar.DAY_OF_MONTH);
				
				//申込可能期間　@(自)≦(至)			
				if(l_appliFrom <= l_appliTo){
					if(!(l_appliFrom <= l_day) || !(l_day <= l_appliTo)){
						return false;
					}
				}
				else
				{
				//申込可能期間　@(至)＜(自)
					if( !(l_appliFrom <= l_day && l_day <= 31) || !(1 <= l_day && l_day <= l_appliTo)){
						return false;
					}
				}
			}
        }

        //1.6:<分岐処理 *4>
        if (WEB3ConditionsValueDivDef.HAVE.equals(l_strLotDiv))
        {
            log.debug("<分岐処理 *4>");
            //1.6.1:getサービス抽選情報(String, String, Timestamp, int)
            if (this.getSrvLotInfo(l_srvMaster.getInstitutionCode(),
                l_srvMaster.getSrvDiv(),
                GtlUtils.getTradingSystem().getSystemTimestamp(),0) == null)
            {
                log.debug("getSrvLotInfo = null");
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }

        //1.7:getサービス申込登録(証券会社コード : String, 部店コード : String, サービス区分
        //: String, 口座コード : String, 取消区分 : String, 有効区分 : String, is行ロック : boolean)
        WEB3SrvRegiRegistService l_registSrv =
            (WEB3SrvRegiRegistService)Services.getService(WEB3SrvRegiRegistService.class);
        WEB3GentradeSrvRegiApplication l_registSrvAppli = l_registSrv.getServiceRegist(
            l_srvMaster.getInstitutionCode(),
            l_subAccount.getMainAccount().getBranch().getBranchCode(),
            l_srvMaster.getSrvDiv(),
            l_subAccount.getMainAccount().getAccountCode(),
            WEB3SrvRegiCancelDivDef.USUAL_DEFAULT,
            WEB3EffectiveDivDef.EFFECTIVE,
            false);

        //1.8:<分岐処理 *5>
        if (l_registSrvAppli != null)
        {
            log.debug("<分岐処理 *5>");
            //1.8.1:get申込抽選区分( )
            //String l_strAppliLotDiv = l_registSrvAppli.getAppliLotDiv();

            //1.8.2:<分岐処理 *6>
            if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv))
            {
                log.debug("<分岐処理 *6>");
                Timestamp l_tsAppliEndDate = l_registSrvAppli.getAppliEndDate();

                Calendar l_cal = new GregorianCalendar();
                l_cal.setTime(l_tsAppliEndDate);
                l_cal.add(Calendar.MONTH, -1);

                l_tsAppliEndDate = new Timestamp(l_cal.getTime().getTime());

                if (((WEB3AppliLotDivDef.ELECTION_FORMAL_APPLI.equals(l_registSrvAppli.getAppliLotDiv()) &&
                WEB3DateUtility.compareToDay(l_tsNowDate,l_tsAppliEndDate) < 0)) &&
                (!WEB3AppliLotDivDef.TRIAL_APPLI.equals(l_registSrvAppli.getAppliLotDiv())))
                {
                    log.debug("<分岐処理 *6>return false");
                    log.exiting(STR_METHOD_NAME);
                    return false;
                }
            }
        }

        log.exiting(STR_METHOD_NAME);
        //1.9:<各チェックでfalseが返却されなかった場合>
        //1.9.1:true
        return true;
    }

    /**
     * (is試用申込可能)<BR>
     * 当該サービスが顧客にとって試用申込可能なサービスかどうかを判定する。<BR>
     * <BR>
     * 1) サービス情報管理.getサービスマスター( ).get申込要<BR>
     * サービス( ).get抽選設定( )<BR>
     * 　@をコールし、取得した抽選設定が"無"以外の場合、falseを返却する。<BR>
     * <BR>
     * [getサービスマスターに渡す引数]<BR>
     * 　@証券会社コード=引数.証券会社コード<BR>
     * 　@サービス区分=引数.サービス区分<BR>
     * 　@is行ロック=false<BR>
     * <BR>
     * [get申込要サービスに渡す引数]<BR>
     * 　@is行ロック=false<BR>
     * <BR>
     * 2) サービス利用申込登録サービス.get初期申込区分( )をコールする。<BR>
     * <BR>
     * [get初期申込区分に渡す引数]<BR>
　@   * 証券会社コード=引数.証券会社コード<BR>
　@   * 部店コード=引数.部店コード<BR>
　@   * サービス区分=引数.サービス区分<BR>
　@   * 口座コード=引数.口座コード<BR>
     * <BR>
     * 3) 返却値の設定<BR>
     *  3-1) 2)の戻り値="無"の場合、trueを返却する。<BR>
     *  3-2) 2)の戻り値="有"の場合、falseを返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * @@return boolean
     * @@roseuid 411C133903D4
     */
    public boolean isTrialAppliPossible(String l_strInstitutionCode, String l_strBranchCode, String l_strSrvDiv, String l_strAccountCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isTrialAppliPossible(String, String, String, String) ";
        log.entering(STR_METHOD_NAME);

        boolean l_blnIsTrialAppliPossible = false;
        //according to the QA of WEB3-SEVREGI-1-CD-0021
            //1) サービス情報管理.getサービスマスター().get申込要サービス().get抽選設定()
        if (!WEB3ConditionsValueDivDef.HAVE_NOT.equals(
            this.getSrvMaster(l_strInstitutionCode, l_strSrvDiv, false).getAppliRequiredSrv(false).getLotDiv()))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //2) サービス利用申込登録サービス.get初期申込区分( )をコールする。
        WEB3SrvRegiRegistService l_service =
            (WEB3SrvRegiRegistService)Services.getService(WEB3SrvRegiRegistService.class);

        String l_strResult = l_service.getInitializeAppliDiv(l_strInstitutionCode, l_strBranchCode, l_strSrvDiv,l_strAccountCode);

        if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strResult))
        {
            l_blnIsTrialAppliPossible = true;
        }
        else
        {
            l_blnIsTrialAppliPossible = false;
        }

        log.exiting(STR_METHOD_NAME);

        //3) 返却値の設定
        return l_blnIsTrialAppliPossible;
    }

    /**
     * (is手数料条件)<BR>
     * 顧客の前月分の手数料累計額と現在の申込状況から、<BR>
     * 当該顧客が手数料条件をクリアしているかを判定する。<BR>
     * <BR>
     * シーケンス図「（サービス情報管理）is手数料条件」参照<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * @@param l_srvServiceMaster - (サービスマスター)<BR>
     * @@return boolean
     */
    public boolean isCommCond(WEB3GentradeSubAccount l_subAccount, WEB3SrvRegiServiceMaster l_srvServiceMaster) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isCommCond(WEB3GentradeSubAccount, WEB3SrvRegiServiceMaster)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_srvServiceMaster == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        try
        {
            //get申込要サービス(boolean)
            WEB3SrvRegiApplicationRequiredService l_AppliRequiredSrv =
                l_srvServiceMaster.getAppliRequiredSrv(false);

            if (l_AppliRequiredSrv == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + "." + STR_METHOD_NAME);
            }

            //get基準手数料合計額( )
            double l_dblAmount2 = Double.parseDouble(l_AppliRequiredSrv.getMinCommAmt());

            //get提供形式( )
            //get提供形式()＝ 0 or 1 以外の場合、falseを返却する
            String l_strSuppltDiv = l_AppliRequiredSrv.getSupplyDiv();
            if (!(WEB3SupplyDivDef.FREE_SUPPLY.equals(l_strSuppltDiv)
                || WEB3SupplyDivDef.CHARGE_OR_FREE_SUPPLY.equals(l_strSuppltDiv)))
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }

        	//障害対応  NO_U01711
            //1.1:＜現在年月の取得＞
			Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem( ).getSystemTimestamp();
			Date l_datSystemDate = WEB3DateUtility.toDay(l_tsSystemTimestamp);

            //1.2:get手数料条件一覧( )
            List l_lisCommCondList = l_srvServiceMaster.getCommCondList();

            if (l_lisCommCondList == null)
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }

            int l_intCommCondCnt = l_lisCommCondList.size();

            if (l_intCommCondCnt == 0)
            {
                 log.debug("get手数料条件一覧()=0");

                 log.exiting(STR_METHOD_NAME);
                 return false;
            }

            //1.3:getDefaultProcessor( )
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException

            //1.4:＜売買手数料累計テーブル検索処理＞
            String l_strWhere =
                " institution_code = ? and branch_code = ? and account_code = ? and accumulate_term = ? and (order_acc_product = ?";

            Calendar l_cal = new GregorianCalendar();
			l_cal.setTime(l_datSystemDate );
            l_cal.add(Calendar.MONTH, -1);

            Date l_dat = l_cal.getTime();

            String l_strYearMonth = WEB3DateUtility.formatDate(l_dat, "yyyyMM");

            Object[] obj = new Object[4 + l_intCommCondCnt];
            obj[0] = l_srvServiceMaster.getInstitutionCode();
            obj[1] = l_subAccount.getMainAccount().getBranch().getBranchCode();
            obj[2] = l_subAccount.getMainAccount().getAccountCode();
            obj[3] = l_strYearMonth;

            for (int i = 0; i < l_intCommCondCnt; i++)
            {
                if (i > 0)
                {
                    l_strWhere += " or order_acc_product = ?";
                }

                SrvRegiCommCondRow l_srvRegiCommCondRow = (SrvRegiCommCondRow)l_lisCommCondList.get(i);
                obj[4 + i] = l_srvRegiCommCondRow.getOrderAccProduct();
            }
            l_strWhere += ") ";

            log.debug("l_strWhere:" + l_strWhere);

            log.debug("l_strYearMonth:" + l_strYearMonth + ":" + l_dat.toString());

            List l_lisSrvRegiDealingCommRowList = l_queryProcessor.doFindAllQuery(
                SrvRegiDealingCommRow.TYPE, l_strWhere, obj);

            //1.5:＜検索結果＝0件の場合、falseを返却する＞
            if (l_lisSrvRegiDealingCommRowList == null || l_lisSrvRegiDealingCommRowList.size() == 0)
            {
                log.debug("1.5:＜検索結果＝0件の場合、falseを返却する＞");
                log.exiting(STR_METHOD_NAME);
                return false;
            }

            int l_intSrvRegiDealingCommRowLisRowCnt = l_lisSrvRegiDealingCommRowList.size();
            //1.6:＜手数料累計額合算＞
            double l_dblAmount = 0;
            for (int i = 0; i < l_intSrvRegiDealingCommRowLisRowCnt; i++)
            {
                SrvRegiDealingCommRow l_srvRegiDealingCommRow =
                    (SrvRegiDealingCommRow)l_lisSrvRegiDealingCommRowList.get(i);
                l_dblAmount += l_srvRegiDealingCommRow.getCommAmt();
            }

            log.debug("＜手数料累計額合算＞:" + l_dblAmount);

            //1.8:＜get基準手数料合計額＞算出した手数料累計額の場合＞
            if (l_dblAmount2 > l_dblAmount)
            {
                log.debug("get基準手数料合計額＞算出した手数料累計額の場合");
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);

            log.exiting(STR_METHOD_NAME);

            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);

            log.exiting(STR_METHOD_NAME);

            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);

        //1.9:true
        return true;
    }

    /**
     * (get手数料条件一覧)<BR>
     * 指定されたサービスに紐付くサービス利用手数料条件マスターの<BR>
     * 一覧を取得し、返却する。<BR>
     * <BR>
     * 1) 以下の検索条件で「サービス利用手数料条件マスターテーブル」を検索する。<BR>
     *  [検索条件] <BR>
     * 証券会社コード=引数.証券会社コード <BR>
     * <BR>
     * 2) 検索結果を「サービス利用手数料条件マスター」オブジェクト<BR>
     * のリストとして返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@return List
     */
    public List getCommCondList(String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getCommCondList(String)";
        log.entering(STR_METHOD_NAME);

        List l_lisCommCondMasters = null;

        try
        {
            //1) 以下の検索条件で「サービス利用手数料条件マスターテーブル」を検索する。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException

            List l_lisSrvRegiCommCondRows = l_queryProcessor.doFindAllQuery(
                SrvRegiCommCondMasterRow.TYPE,
                " institution_code = ? ",
                new Object[]{l_strInstitutionCode});//DataNetworkException, DataQueryException

            l_lisCommCondMasters = new ArrayList();

            if (l_lisSrvRegiCommCondRows != null)
            {
                //2) 検索結果を「サービス利用手数料条件マスター」オブジェクトのリストとして返却する。
                int l_intSrvRegiCommCondRowCnt = l_lisSrvRegiCommCondRows.size();

                for (int i = 0; i < l_intSrvRegiCommCondRowCnt; i++)
                {
                    SrvRegiCommCondMasterRow l_srvRegiCommCondMasterRow = (SrvRegiCommCondMasterRow)l_lisSrvRegiCommCondRows.get(i);

                    WEB3SrvRegiCommCondMaster l_commCondMaster =
                        new  WEB3SrvRegiCommCondMaster(
                            l_strInstitutionCode,
                            l_srvRegiCommCondMasterRow.getOrderAccProduct(),
                            l_srvRegiCommCondMasterRow.getCommProdTypeName());

                    l_lisCommCondMasters.add(l_commCondMaster);
                }
            }
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);

            log.exiting(STR_METHOD_NAME);

            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);

            log.exiting(STR_METHOD_NAME);

            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);

        return l_lisCommCondMasters;
    }

    /**
     * (validate申込期間)<BR>
     * 指定された申込期間の妥当性をチェックする。<BR>
     * <BR>
     * 1) 以下を条件に、「サービス抽選情報テーブル」を検索する。<BR>
     * [検索条件]<BR>
     * 　@証券会社コード=引数.証券会社コード and<BR>
     * 　@サービス区分=引数.サービス区分 and<BR>
     * 　@適用終了日≧現在日付(*1) and<BR>
     *   通番!=引数.抽選情報ID and<BR>
     *  ((申込期間（自）≧引数.申込開始日 and 申込期間（自）≦引数.申込終了日) or<BR>
     * 　@(申込期間（至）≧引数.申込開始日 and 申込期間（至）≦引数.申込終了日) or<BR>
     * 　@(申込期間（自）≦引数.申込開始日 and 申込期間（至）≧引数.申込終了日))<BR>
     * <BR>
     * (*1)GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値<BR>
     * <BR>
     * 2) 検索結果＞0件の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00983<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@param l_lngLotteryId - (抽選情報ID)<BR>
     * @@param l_tsAppliStartDate - (申込開始日)<BR>
     * @@param l_tsAppliEndDate - (申込終了日)<BR>
     * @@roseuid 41206EDF0358
     */
    public void validateAppliDate(String l_strInstitutionCode, String l_strSrvDiv, Long l_lngLotteryId, Timestamp l_tsAppliStartDate, Timestamp l_tsAppliEndDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateAppliDate(String, String, Timestamp, Timestamp) ";
        log.entering(STR_METHOD_NAME);

        if (l_tsAppliStartDate == null || l_tsAppliEndDate == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        try
        {
            //1) 以下を条件に、「サービス抽選情報テーブル」を検索する。
			String l_strWhere = null;
			Object[] l_obj = null;
            if (l_lngLotteryId != null)
            {
				l_strWhere = " institution_code = ? and srv_div = ? and appli_end_date >= ? and " +
					"consecutive_numbers != ? and " +
					"((appli_date_from >= ? and appli_date_from <= ?) or " +
					"(appli_date_to >= ? and appli_date_to <= ?) or " +
					"(appli_date_from <= ? and appli_date_to >= ?))";

				Timestamp l_tsNowDate = GtlUtils.getTradingSystem().getSystemTimestamp();

				l_obj = new Object[10];
				
				l_obj[0] = l_strInstitutionCode;
				l_obj[1] = l_strSrvDiv;
				l_obj[2] = l_tsNowDate;
				l_obj[3] = l_lngLotteryId;
				l_obj[4] = l_tsAppliStartDate;
				l_obj[5] = l_tsAppliEndDate;
				l_obj[6] = l_tsAppliStartDate;
				l_obj[7] = l_tsAppliEndDate;
				l_obj[8] = l_tsAppliStartDate;
				l_obj[9] = l_tsAppliEndDate;	
            }
            else
            {
				l_strWhere = " institution_code = ? and srv_div = ? and appli_end_date >= ? and " +
					"((appli_date_from >= ? and appli_date_from <= ?) or " +
					"(appli_date_to >= ? and appli_date_to <= ?) or " +
					"(appli_date_from <= ? and appli_date_to >= ?))";

				Timestamp l_tsNowDate = GtlUtils.getTradingSystem().getSystemTimestamp();

				l_obj = new Object[9];
				
				l_obj[0] = l_strInstitutionCode;
				l_obj[1] = l_strSrvDiv;
				l_obj[2] = l_tsNowDate;
				l_obj[3] = l_tsAppliStartDate;
				l_obj[4] = l_tsAppliEndDate;
				l_obj[5] = l_tsAppliStartDate;
				l_obj[6] = l_tsAppliEndDate;
				l_obj[7] = l_tsAppliStartDate;
				l_obj[8] = l_tsAppliEndDate;	
            }

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException

            List l_lisSrvLotInfoRowList = l_queryProcessor.doFindAllQuery(
                SrvRegiLotInfoRow.TYPE, l_strWhere, l_obj);//DataNetworkException, DataQueryException

            //2) 検索結果＞0件の場合、例外をスローする。
            if ( l_lisSrvLotInfoRowList != null && l_lisSrvLotInfoRowList.size() > 0)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00983,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME, l_ex);

            log.exiting(STR_METHOD_NAME);

            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME, l_ex);

            log.exiting(STR_METHOD_NAME);

            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate適用期間)<BR>
     * 指定された適用期間の妥当性をチェックする。<BR>
     * <BR>
     * 1) 以下を条件に、「サービス抽選情報テーブル」を検索する。<BR>
     * [検索条件]<BR>
     * 　@証券会社コード=引数.証券会社コード and<BR>
     * 　@サービス区分=引数.サービス区分 and<BR>
     * 　@適用終了日≧現在日付(*1)の日付部分 and<BR>
     *   通番!=引数.抽選情報ID and<BR>
     *  ((適用開始日≧引数.適用開始日 and 適用開始日≦引数.適用終了日) or<BR>
     * 　@(適用終了日≧引数.適用開始日 and 適用終了日≦引数.適用終了日) or<BR>
     * 　@(適用開始日≦引数.適用開始日 and 適用終了日≧引数.適用終了日))<BR>
     * <BR>
     * (*1)GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値<BR>
     * <BR>
     * 2) 検索結果＞0件の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00946<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@param l_lngLotteryId - (抽選情報ID)<BR>
     * @@param l_tsAppliStartDate - (適用開始日)<BR>
     * @@param l_tsAppliEndDate - (適用終了日)<BR>
     * @@roseuid 41206EDF0368
     */
    public void validateAppliPeriod(String l_strInstitutionCode, String l_strSrvDiv, Long l_lngLotteryId, Timestamp l_tsAppliStartDate, Timestamp l_tsAppliEndDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateAppliPeriod(String, String, Timestamp, Timestamp) ";
        log.entering(STR_METHOD_NAME);

        if (l_tsAppliStartDate == null || l_tsAppliEndDate == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        try
        {
            //1) 以下を条件に、「サービス抽選情報テーブル」を検索する。
			String l_strWhere = null;
			Object[] l_obj = null;
            if (l_lngLotteryId != null)
            {
				l_strWhere = " institution_code = ? and srv_div = ? and appli_end_date >= ? and " +
					"consecutive_numbers != ? and " +
					"((appli_start_date >= ? and appli_start_date <= ?) or " +
					"(appli_end_date >= ? and appli_end_date <= ?) or " +
					"(appli_start_date <= ? and appli_end_date >= ?))";

				Timestamp l_tsNowDate = GtlUtils.getTradingSystem().getSystemTimestamp();

				l_obj = new Object[10];
				
				l_obj[0] = l_strInstitutionCode;
				l_obj[1] = l_strSrvDiv;
				l_obj[2] = l_tsNowDate;
				l_obj[3] = l_lngLotteryId;
				l_obj[4] = l_tsAppliStartDate;
				l_obj[5] = l_tsAppliEndDate;
				l_obj[6] = l_tsAppliStartDate;
				l_obj[7] = l_tsAppliEndDate;
				l_obj[8] = l_tsAppliStartDate;
				l_obj[9] = l_tsAppliEndDate;
            }
            else
            {
				l_strWhere = " institution_code = ? and srv_div = ? and appli_end_date >= ? and " +
					"((appli_start_date >= ? and appli_start_date <= ?) or " +
					"(appli_end_date >= ? and appli_end_date <= ?) or " +
					"(appli_start_date <= ? and appli_end_date >= ?))";

				Timestamp l_tsNowDate = GtlUtils.getTradingSystem().getSystemTimestamp();

				l_obj = new Object[9];
				
				l_obj[0] = l_strInstitutionCode;
				l_obj[1] = l_strSrvDiv;
				l_obj[2] = l_tsNowDate;
				l_obj[3] = l_tsAppliStartDate;
				l_obj[4] = l_tsAppliEndDate;
				l_obj[5] = l_tsAppliStartDate;
				l_obj[6] = l_tsAppliEndDate;
				l_obj[7] = l_tsAppliStartDate;
				l_obj[8] = l_tsAppliEndDate;
            }

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException

            List l_lisSrvLotInfoRowList =
                l_queryProcessor.doFindAllQuery(SrvRegiLotInfoRow.TYPE, l_strWhere, l_obj);//DataNetworkException, DataQueryException
            
            //2) 検索結果＞0件の場合、例外をスローする。
            if (l_lisSrvLotInfoRowList != null && l_lisSrvLotInfoRowList.size() > 0)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00946,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME, l_ex);

            log.exiting(STR_METHOD_NAME);

            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME, l_ex);

            log.exiting(STR_METHOD_NAME);

            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate電子鳩同意)<BR>
     * 顧客が電子鳩に同意しているか判定する。<BR> 
     * <BR>
     * １．申込要サービス.is電子鳩条件設定()をコールして、<BR> 
     * 電子鳩同意が条件として設定されているかチェックする。<BR> 
     * ２．is電子鳩条件設定()==trueの場合、電子鳩システム接続サービス.validate電子鳩実施()<BR> 
     * をコールし、電子鳩同意チェックを実施する。<BR> 
     * <BR>
　@   * [引数] <BR>
　@   * 機@能区分：00（電子鳩承諾チェック）<BR>
     * <BR>
     * ３．validate電子鳩実施()の戻り値が、「未同意顧客」だった場合は、例外をスローする。<BR> 
     * （電子鳩未登録エラー）<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01965<BR>
     * <BR>
     * <BR>
     * ※電子鳩システム接続サービスは、共通.他システムインタフェースのサービス<BR>
     * @@param l_srvMaster - (サービスマスター)<BR>
     */
    public void validateBatoAgreement(WEB3SrvRegiServiceMaster l_srvMaster) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateBatoAgreement(WEB3SrvRegiServiceMaster) ";
        log.entering(STR_METHOD_NAME);
        
        //WEB3-SRVREGI-A-CD-0139
        WEB3SrvRegiApplicationRequiredService l_requiredSrv = l_srvMaster.getAppliRequiredSrv(false);
        
        boolean l_blnIsElectricIssue = l_requiredSrv.isElectricIssue();
        
        if (l_blnIsElectricIssue)
        {
            WEB3GentradeBatoClientService l_batoClientService = 
                (WEB3GentradeBatoClientService) Services.getService(WEB3GentradeBatoClientService.class);
            String l_strResult =l_batoClientService.validateBato(
                WEB3GentradeBatoFunctionDivDef.BATO_SERVICE_REG_SERVICE);
                
            if (WEB3GentradeBatoTranHistServiceResultDef.NOT_AGREEMENT.equals(l_strResult))
            {
                String l_srMessage = "戻り値「" + l_strResult + "」は未同意顧客です.";
                log.debug(l_srMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01965,
                    getClass().getName() + STR_METHOD_NAME,
                    l_srMessage);
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (getサービス申込属性情報)<BR>
     * サービス申込属性オブジェクトを返却する。<BR>
     * <BR>
     * １） サービス申込属性テーブルよりデータを取得する。<BR>
     * <BR>
     * 　@１） Object配列を生成し、以下を要素に設定<BR>
     * 　@　@　@　@Object[0]（引数）証券会社コード<BR>
     * 　@　@　@　@Object[1]（引数）部店コード<BR>
     * 　@　@　@　@Object[2]（引数）口座コード + "%"<BR>
     * 　@　@　@　@Object[3]（引数）サービス区分<BR>
     * 　@　@　@　@Object[4]（引数）アップロード区分<BR>
     * <BR>
     * 　@２） サービス申込属性テーブルにレコードが存在するか検索する。<BR>
     * 　@　@　@　@QueryProcessor.doFindAllQuery()メソッドをコールする。<BR>
     * <BR>
     * 　@　@　@　@[doFindAllQuery()にセットするパラメータ]<BR>
     * 　@　@　@　@　@arg0：  サービス申込属性テーブルRowType<BR>
     * 　@　@　@　@　@arg1：  "institution_code=?<BR>
     * 　@　@　@　@　@　@　@　@　@　@and branch_code=?<BR>
     * 　@　@　@　@　@　@　@　@　@　@and account_code like ?<BR>
     * 　@　@　@　@　@　@　@　@　@　@and srv_div=?"<BR>
     * 　@　@　@　@　@　@　@　@　@　@(引数.アップロード区分() == null の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@以下の条件も追加する)<BR>
     * 　@　@　@　@　@　@　@　@　@　@"and proc_div == '0' or null <BR>
     * 　@　@　@　@　@　@　@　@　@　@and (appli_start_date <= 現在日付(YYYYMMDD) <BR>
     * 　@　@　@　@　@　@　@　@　@　@or appli_start_date == null)<BR>
     * 　@　@　@　@　@　@　@　@　@　@and (appli_end_date >= 現在日付(YYYYMMDD) <BR>
     * 　@　@　@　@　@　@　@　@　@　@or appli_end_date == null)"<BR>
     * 　@　@　@　@　@arg2：  １）で作成したObject配列<BR>
     * <BR>
     * 　@３） ２）の戻り値Listの長さ > 0 の場合、２）の戻り値を返却する。<BR>
     * <BR>
     * 　@４） ２）の戻り値Listの長さ = 0 の場合、nullを返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * 口座コード<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * サービス区分<BR>
     * @@param l_strUploadDiv - (アップロード区分)<BR>
     * アップロード区分<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getServiceAppliAttributeInfo(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strSrvDiv,
        String l_strUploadDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getServiceAppliAttributeInfo(String, String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //１） サービス申込属性テーブルよりデータを取得する。
        //１） Object配列を生成し、以下を要素に設定
        //      １） Object配列を生成し、以下を要素に設定
        //      Object[0]（引数）証券会社コード
        //      Object[1]（引数）部店コード
        //      Object[2]（引数）口座コード + "%"
        //      Object[3]（引数）サービス区分
        //      Object[4]（引数）アップロード区分
        List l_lisWheres = new ArrayList();
        l_lisWheres.add(l_strInstitutionCode);
        l_lisWheres.add(l_strBranchCode);
        l_lisWheres.add(l_strAccountCode + "%");
        l_lisWheres.add(l_strSrvDiv);

        //arg1：  "institution_code=?
        //         and branch_code=?
        //         and account_code like ?
        //         and srv_div=?"
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and　@branch_code = ? ");
        l_sbWhere.append(" and　@account_code like ? ");
        l_sbWhere.append(" and　@srv_div = ? ");

        //         (引数.アップロード区分() == null の場合、以下の条件も追加する)
        //        "and proc_div == '0' or null
        //         and (appli_start_date <= 現在日付(YYYYMMDD) or appli_start_date == null)
        //         and (appli_end_date >= 現在日付(YYYYMMDD) or appli_end_date == null)"
        if (l_strUploadDiv == null)
        {
            l_lisWheres.add(WEB3SrvAppliAttributeProcDivDef.NOT_PROCESSED);
            l_lisWheres.add(WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp()));
            l_lisWheres.add(WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp()));
            l_sbWhere.append(" and (proc_div = ? ");
            l_sbWhere.append(" or proc_div is null) ");
            l_sbWhere.append(" and (appli_start_date <= ? ");
            l_sbWhere.append(" or appli_start_date is null) ");
            l_sbWhere.append(" and (appli_end_date >= ? ");
            l_sbWhere.append(" or appli_end_date is null) ");
        }

        Object[] l_objWheres = new Object[l_lisWheres.size()];
        l_lisWheres.toArray(l_objWheres);

        List l_lisRecords = null;
        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcesser.doFindAllQuery(
                SrvAppliAttributeRow.TYPE,
                l_sbWhere.toString(),
                l_objWheres);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (l_lisRecords == null || l_lisRecords.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisRecords;
    }

    /**
     * (validate特殊申込)<BR>
     * 当該サービスが外部連携サービス、且つ顧客が新規顧客の場合、<BR>
     * 外部連携情報管理テーブルに未使用のレコードがあるかチェックを行う。<BR>
     * <BR>
     * シーケンス図「（サービス情報管理）validate特殊申込｣参照<BR>
     * <BR>
     * ========================================================<BR>
     * シーケンス図 （サービス情報管理）validate特殊申込: <BR>
     * 　@　@　@　@　@1.1.2.1get外部連携情報( ) の戻り値が null 以外の場合、<BR>
     * 　@　@　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_03027<BR>
     * ==========================================================<BR>
     * <BR>
     * ========================================================<BR>
     * シーケンス図 （サービス情報管理）validate特殊申込: <BR>
     * 　@　@　@　@　@1.1.3.1get外部連携情報()の戻り値が null の場合、<BR>
     * 　@　@　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_03019<BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_srvRegiServiceMaster - (サービスマスター)<BR>
     * サービスマスター<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * 口座コード<BR>
     * @@param l_blnNewApplyDiv - (新規申込区分)<BR>
     * 新規申込区分<BR>
     * 新規申込：true  継続申込：false<BR>
     * @@throws WEB3BaseException
     */
    public void validateSpecialApply(
        WEB3SrvRegiServiceMaster l_srvRegiServiceMaster,
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        boolean l_blnNewApplyDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateSpecialApply(WEB3SrvRegiServiceMaster, String, String, String, boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_srvRegiServiceMaster == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //引数.サービスマスタ.特殊処理区分 = 1（外部連携サービス） の場合
        SrvRegiMasterParams l_srvRegiMasterParams =
            (SrvRegiMasterParams)l_srvRegiServiceMaster.getDataSourceObject();
        String l_strSpecialProcessDiv = l_srvRegiMasterParams.getSpecialProcessDiv();

        if (WEB3SpecialProcessDivDef.OTHER_ORG_SERVICE.equals(l_strSpecialProcessDiv))
        {
            //get外部連携情報(String, String, String, String, boolean)
            //[引数]
            //サービス区分 = 引数.サービスマスタ.サービス区分
            //証券会社コード = 引数.証券会社コード
            //部店コード = 引数.部店コード
            //口座コード = 引数.口座コード
            //is行ロック = false
            WEB3SrvRegiOtherOrgService l_srvRegiOtherOrgService =
                (WEB3SrvRegiOtherOrgService)Services.getService(WEB3SrvRegiOtherOrgService.class);

            WEB3SrvRegiOtherOrgInfoAdmin l_srvRegiOtherOrgInfoAdmin =
                l_srvRegiOtherOrgService.getOtherOrgInfo(
                    l_srvRegiServiceMaster.getSrvDiv(),
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    false);

            //引数.新規申込区分 = true の場合
            if (l_blnNewApplyDiv)
            {
                //get外部連携情報( ) の戻り値が null 以外の場合、例外をスローする。
                if (l_srvRegiOtherOrgInfoAdmin != null)
                {
                    log.debug("外部連携情報を取得するはずがない。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03027,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "外部連携情報を取得するはずがない。");
                }

                //get未使用通番情報(String, boolean)
                //サービス区分 = 引数.サービスマスタ.サービス区分
                //is行ロック = false
                l_srvRegiOtherOrgService.getUnUseSequenceNumberInfo(
                    l_srvRegiServiceMaster.getSrvDiv(),
                    false);
            }
            else
            {
                //get外部連携情報()の戻り値が null の場合、例外をスローする。
                if (l_srvRegiOtherOrgInfoAdmin == null)
                {
                    log.debug("外部連携情報を取得できません。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03019,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "外部連携情報を取得できません。");
                }
            }
        }
        log.debug("サービス情報管理#validate特殊申込pass");
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (is新規申込)<BR>
     * 該当顧客が、サービスを新規申込か判定を行う。<BR>
     * <BR>
     * シーケンス図「（サービス情報管理）is新規申込｣参照<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_strServiceDiv - (サービス区分)<BR>
     * サービス区分<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isNewApply(
        WEB3GentradeSubAccount l_subAccount,
        String l_strServiceDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isNewApply(WEB3GentradeSubAccount, String)";
        log.entering(STR_METHOD_NAME);

        //getサービス申込登録(String, String, String, String, String, String, boolean)
        //証券会社コード = 引数.補助口座オブジェクト.getInstitution( ).getInstitutionCode( )
        //部店コード = 引数.補助口座オブジェクト.getBranch( ).getBranchCode( )
        //サービス区分=引数.サービス区分
        //口座コード = 引数.補助口座オブジェクト.getMainAccoutn( ).getAccountCode( )
        //取消区分="指定無"
        //有効区分="有効"
        //is行ロック=false
        WEB3SrvRegiRegistService l_srvRegiRegistService =
            (WEB3SrvRegiRegistService)Services.getService(WEB3SrvRegiRegistService.class);
        WEB3GentradeSrvRegiApplication l_srvRegiApplication =
            l_srvRegiRegistService.getServiceRegist(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getWeb3GenBranch().getBranchCode(),
                l_strServiceDiv,
                l_subAccount.getMainAccount().getAccountCode(),
                null,
                WEB3EffectiveDivDef.EFFECTIVE,
                false);

        //getサービス申込登録() の戻り値が null の場合
        if (l_srvRegiApplication == null)
        {
            log.debug("getサービス申込登録() の戻り値が null の場合 return true");
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.debug("getサービス申込登録() の戻り値 != null の場合 return false");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (get特殊処理サービスマスター一覧)<BR>
     * サービスマスターの一覧を検出し、返却する。<BR>
     *<BR>
     * 1) 以下の条件で「サービスマスターテーブル」を検出する。<BR>
     * [検索条件]<BR>
     * 　@証券会社コード=引数.証券会社コード<BR>
     * 　@and 特殊処理区分=引数.特殊処理区分<BR>
     *<BR>
     * [並び順]<BR>
     * 　@サービス区分　@昇順<BR>
     *<BR>
     * 2) 検索結果の件数分、以下を繰り返す。（取得できない場合は、例外をスローする。 ）<BR>
     *  2-1) 検索結果のサービスマスターParamsを引数に、<BR>
     * 　@　@サービスマスタークラスのコンストラクタをコールする。<BR>
     * [引数]<BR>
     * 　@サービスマスターRow: 取得したサービスマスターParams<BR>
     *<BR>
     *  2-2) 生成したサービスマスターオブジェクトを配列に追加する。<BR>
     *<BR>
     * 3) 2)で作成したサービスマスタークラスの配列を返却する。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strSpecialProcessDiv - (特殊処理区分)<BR>
     * 特殊処理区分<BR>
     * @@return WEB3SrvRegiServiceMaster[ ]
     */
    public WEB3SrvRegiServiceMaster[] getSpecialProcessSrvMasterList(
        String l_strInstitutionCode,
        String l_strSpecialProcessDiv)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getSpecialProcessSrvMasterList(String, String) ";
        log.entering(STR_METHOD_NAME);

        WEB3SrvRegiServiceMaster[] l_serviceMasters = null;

        try
        {
            // 1) 以下の条件で「サービスマスターテーブル」を検出する。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            // [検索条件]
            // 　@証券会社コード=引数.証券会社コード
            // 　@and 特殊処理区分=引数.特殊処理区分
            String l_strWhere = " institution_code = ? ";

            List l_lisServiceMasterRowList = null;

            Object[] l_obj;

            if (l_strSpecialProcessDiv == null)
            {
                l_strWhere += " and special_process_div is null ";
                l_obj = new Object[]{l_strInstitutionCode};
            }
            else
            {
                l_strWhere += " and special_process_div = ? ";
                l_obj = new Object[]{l_strInstitutionCode, l_strSpecialProcessDiv};
            }

            // [並び順]<BR>
            // 　@サービス区分　@昇順
            String l_strOrderBy = " srv_div asc ";

            l_lisServiceMasterRowList =
                l_queryProcessor.doFindAllQuery(
                    SrvRegiMasterRow.TYPE,
                    l_strWhere,
                    l_strOrderBy,
                    null,
                    l_obj);

            // 2) 検索結果の件数分、以下を繰り返す。
            // 取得できない場合は、例外をスローする。）
            if (!l_lisServiceMasterRowList.isEmpty())
            {

                // 2-1) 検索結果のサービスマスターParamsを引数に、
                // 　@サービスマスタークラスのコンストラクタをコールする。
                // [引数]
                // 　@サービスマスターRow: 取得したサービスマスターParams
                int l_intServiceMasterRowCnt = l_lisServiceMasterRowList.size();

                l_serviceMasters = new WEB3SrvRegiServiceMaster[l_intServiceMasterRowCnt];

                for (int i = 0; i < l_intServiceMasterRowCnt; i++)
                {
                    // 2-2) 生成したサービスマスターオブジェクトを配列に追加する。
                    l_serviceMasters[i] =
                        new WEB3SrvRegiServiceMaster(
                            (SrvRegiMasterRow)l_lisServiceMasterRowList.get(i));
                }
            }
            else
            {
                String l_strErrorMessage =
                    "サービスマスターデータを取得できません。";
                log.debug(l_strErrorMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00982,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "サービスマスターデータを取得できません。");
            }
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);

            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "DBアクセスエラー。");
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);

            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "DBアクセスエラー。");
        }

        log.exiting(STR_METHOD_NAME);

        // 3) 2)で作成したサービスマスタークラスの配列を返却する。
        return l_serviceMasters;
    }

}
@
