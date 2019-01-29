head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.39.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderEmpCodeGettingServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式運用コード取得サービスImpl（WEB3FeqOrderEmpCodeGettingServiceImpl.java）
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/03 武波 (中訊) 新規作成 モデルNo.501
*/
package webbroker3.feq.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3InstitutionPreferencesNameDef;
import webbroker3.feq.service.delegate.WEB3FeqOrderEmpCodeGettingService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.data.InstitutionPreferencesDao;
import webbroker3.gentrade.data.InstitutionPreferencesRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (外国株式運用コード取得サービスImpl) <BR>
 *  外国株式運用コード取得サービスImpl<BR>
 * @@author 武波
 * @@version 1.0 
 */
public class WEB3FeqOrderEmpCodeGettingServiceImpl implements WEB3FeqOrderEmpCodeGettingService
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderEmpCodeGettingServiceImpl.class);

    /**
     * (外国株式運用コード取得サービスImpl) <BR>
     */
    public WEB3FeqOrderEmpCodeGettingServiceImpl()
    {
    }

    /**
     * (getPREFIX)<BR>
     * 運用コードの左2桁を取得する。<BR>
     * <BR>
     * １）　@証券会社オブジェクトを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@証券会社コード： 引数.証券会社コード<BR>
     * <BR>
     * ２）　@証券会社プリファ@レンステーブルから以下条件全てに該当するレコードを取得する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@証券会社ＩＤ＝証券会社オブジェクト.証券会社ID<BR>
     * 　@プリファ@レンス名 = ”feq.order.emp.code.div”<BR>
     * 　@項目名連番 = 1<BR>
     * <BR>
     * ３）　@取得したレコード.プリファ@レンスの値を返却する。<BR>
     * <BR>
     * 　@レコードが取得できなかった場合は、例外をスローする。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getPREFIX(String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getPREFIX(String)";
        log.entering(STR_METHOD_NAME);

        //１）　@証券会社オブジェクトを取得する。
        //証券会社コード： 引数.証券会社コード
        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();

            Institution l_institution = l_accountManager.getInstitution(l_strInstitutionCode);

            //２）　@証券会社プリファ@レンステーブルから以下条件全てに該当するレコードを取得する。
            //証券会社ＩＤ＝証券会社オブジェクト.証券会社ID
            //プリファ@レンス名 = ”feq.order.emp.code.div”
            //項目名連番 = 1
            InstitutionPreferencesRow l_institutionPreferencesRow =
                InstitutionPreferencesDao.findRowByPk(
                    l_institution.getInstitutionId(),
                    WEB3InstitutionPreferencesNameDef.FEQ_ORDER_EMP_CODE_DIV,
                    1);

            log.exiting(STR_METHOD_NAME);
            return l_institutionPreferencesRow.getValue();
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
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
    }

    /**
     * (get運用コード)<BR>
     * ７桁の「運用コード」文字列を取得し返却する。<BR>
     * <BR>
     * １）　@引数.運用コード！＝nullの場合、以下の処理を行う。<BR>
     * <BR>
     * 　@１－１）　@運用コードの左２桁を取得する。<BR>
     * <BR>
     * 　@　@this.getPREFIX（）をコールする。<BR>
     * <BR>
     * 　@　@［引数]<BR>
     * 　@　@証券会社コード： 引数.証券会社コード<BR>
     * <BR>
     * 　@１－２）　@7桁の運用コードを取得する。<BR>
     * <BR>
     * 　@　@getPREFIX（）の戻り値＋引数の運用コード<BR>
     * <BR>
     * 　@１－３）　@取得した7桁の運用コードを返却する。<BR>
     * <BR>
     * ２）　@上記以外の場合、nullを返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strEmpCode - (運用コード)<BR>
     * 運用コード<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getEmpCode(String l_strInstitutionCode, String l_strEmpCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getEmpCode(String, String)";
        log.entering(STR_METHOD_NAME);

        //１）　@引数.運用コード！＝nullの場合、以下の処理を行う。
        if (!WEB3StringTypeUtility.isEmptyOrBlank(l_strEmpCode))
        {
            //１－１）　@運用コードの左２桁を取得する。
            //this.getPREFIX（）をコールする。
            //証券会社コード： 引数.証券会社コード
            String l_strEmpCodePREFIX = this.getPREFIX(l_strInstitutionCode);

            //１－２）　@7桁の運用コードを取得する。
            //getPREFIX（）の戻り値＋引数の運用コード
            //１－３）　@取得した7桁の運用コードを返却する。
            log.exiting(STR_METHOD_NAME);
            return l_strEmpCodePREFIX + l_strEmpCode;
        }

        //２）　@上記以外の場合、nullを返却する。
        log.exiting(STR_METHOD_NAME);
        return null;
    }

}
@
