head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.49.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiStartInfoServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用起動情報サービスImpl(WEB3SrvRegiStartInfoServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/18 李頴淵 新規作成
Revesion History : 2005/10/05 鈴木　@美由紀(SRA) トランスリンク対応
Revesion History : 2005/10/18 鈴木　@美由紀(SRA) フィデリティ対応
Revesion History : 2006/06/14 郭英　@仕様変更・モデルNo.229対応
Revesion History : 2005/10/18 郭英　@(中訊) 仕様変更モデルNo.230,231対応
Revesion History : 2009/04/28 車進　@(中訊) 仕様変更モデルNo.410,411対応
Revesion History : 2009/05/20 柴双紅(中訊) 仕様変更モデルNo.419,422対応
Revesion History : 2009/05/31 柴双紅(中訊) 仕様変更モデルNo.425
Revesion History : 2009/06/01 末満　@正巳(SCS) 仕様変更モデルNo.426
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.DefaultSortKeySpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundAsset;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HashCalHowToDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.login.service.delegate.WEB3DigestKey;
import webbroker3.mf.WEB3MutualFundPositionManager;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.srvregi.WEB3SrvRegiServiceInfoManagement;
import webbroker3.srvregi.WEB3SrvRegiServiceMaster;
import webbroker3.srvregi.WEB3SrvRegiServiceUseKey;
import webbroker3.srvregi.define.WEB3SrvRegiHashCalHowToDivValueDef;
import webbroker3.srvregi.define.WEB3SrvRegiHashCalOrderDivDef;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiStartInfoService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (サービス利用起動情報サービスImpl) <BR>
 * サービス利用起動情報サービス実装クラス <BR>
 *
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3SrvRegiStartInfoServiceImpl implements WEB3SrvRegiStartInfoService
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SrvRegiStartInfoServiceImpl.class);

    /**
     * (ケンミレニアム暗号化計算値) <BR>
     * （オリックス証券 ケンミレニアム用定数） <BR>
     * get暗号化顧客コードにて使用 <BR>
     */
    private static final int KENMILLENNIUM_CRYPT_CALC_VALUE = 333221;

    /**
     * (ハッシュ計算用日付切替基準時間) <BR>
     * ハッシュ計算時に用いる日付は、全社共通で <BR>
     * 16:30:00を基準に翌日に切り替えられる。 <BR>
     */
    private static final int DATE_CONVERSION_CONSTANT_TIME_FOR_HASH_CALC = 430;

    /**
     * @@roseuid 41EB94E803A9
     */
    public WEB3SrvRegiStartInfoServiceImpl()
    {

    }

    /**
     * (get暗号化顧客コード) <BR>
     * （オリックス証券 ケンミレニアムにて使用） <BR>
     * <BR>
     * 引数.顧客コードに暗号化を施し、その結果を返却する。 <BR>
     * <BR>
     * 1) 引数.顧客コードを一文字ずつ（顧客コード[0]～[5]）に分割。 <BR>
     * <BR>
     * 2) 暗号化 <BR>
     * 2-1) 以下の①@～⑥の計算結果の合計を算出する。 <BR>
     * ①@顧客コード[0] * 100 <BR>
     * ②顧客コード[1] <BR>
     * ③顧客コード[2] * 100000 <BR>
     * ④顧客コード[3] * 10000 <BR>
     * ⑤顧客コード[4] * 1000 <BR>
     * ⑥顧客コード[5] * 10 <BR>
     * <BR>
     * 2-2) 以下を算出する。 <BR>
     * ①@2-1)の計算結果≧ケンミレニアム暗号化計算値の場合 <BR>
     * 2-1)の計算結果－ケンミレニアム暗号化計算値を算出 <BR>
     * ②上記以外の場合 <BR>
     * 2-1)の計算結果－ケンミレニアム暗号化計算値＋1000000を算出 <BR>
     * <BR>
     * 2-3) ６桁に調整 <BR>
     * 2-2)の計算結果が６桁に満たない場合、先頭を"0"で埋め返却する。 <BR>
     * <BR>
     * EX) 2-2)の計算結果が 123 だった場合、000123とする。 <BR>
     *
     * @@param l_strMainAccountCode -
     *            (顧客コード)
     * @@return String
     * @@roseuid 41B58B0702B1
     */
    public String getCryptAccountCode(String l_strMainAccountCode)
    {
        String STR_METHOD_NAME = " getEncryptedMainAccountCode(String)";
        log.entering(STR_METHOD_NAME);

        //1) 引数.顧客コードを一文字ずつ（顧客コード[0]～[5]）に分割。
        int l_int0 = Integer.parseInt(l_strMainAccountCode.substring(0, 1));
        int l_int1 = Integer.parseInt(l_strMainAccountCode.substring(1, 2));
        int l_int2 = Integer.parseInt(l_strMainAccountCode.substring(2, 3));
        int l_int3 = Integer.parseInt(l_strMainAccountCode.substring(3, 4));
        int l_int4 = Integer.parseInt(l_strMainAccountCode.substring(4, 5));
        int l_int5 = Integer.parseInt(l_strMainAccountCode.substring(5, 6));

        //2-1) 以下の①@～⑥の計算結果の合計を算出する。
        int l_intCnt = l_int0 * 100 + l_int1 + l_int2 * 100000 + l_int3 * 10000 + l_int4 * 1000 + l_int5 * 10;

        //①@2-1)の計算結果≧ケンミレニアム暗号化計算値の場合
        int l_intReturn = 0;
        if (l_intCnt >= KENMILLENNIUM_CRYPT_CALC_VALUE)
        {
            log.debug("2-1)の計算結果≧ケンミレニアム暗号化計算値の場合");
            l_intReturn = l_intCnt - KENMILLENNIUM_CRYPT_CALC_VALUE;
        }
        else
        {
            log.debug("2-1)の計算結果<ケンミレニアム暗号化計算値の場合");
            l_intReturn = l_intCnt - KENMILLENNIUM_CRYPT_CALC_VALUE + 1000000;
        }

        //2-3) ６桁に調整
        String l_strReturn = Integer.toString(l_intReturn);

        if (l_strReturn.length() < 6)
        {
            log.debug("2-3) ６桁に調整");
            int l_int = 6 - l_strReturn.length();
            for (int i = 0; i < l_int; i++)
            {
                l_strReturn = "0" + l_strReturn;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_strReturn;
    }

    /**
     * (createハッシュ値) <BR>
     * 引数から指定された他ベンダーサービスのハッシュ値を求める。 <BR>
     * <BR>
     * シーケンス図「（サービス利用）createハッシュ値」参照 <BR>
     * ========================================================<BR>
     * 　@シーケンス図：（サービス利用）createハッシュ値<BR>
     * 　@具体位置　@　@： get付加区分( )の戻り値=nullの場合、<BR>
     * 　@　@　@　@　@　@　@　@「付加区分がnullです。」例外をスローする。<BR>
     * 　@　@　@　@class： WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@： BUSINESS_ERROR_03160<BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_strInstitutionCode -
     *            証券会社コード
     * @@param l_strSrvDiv -
     *            サービス区分
     * @@param l_strBranchCode -
     *            部店コード
     * @@param l_strMainAccountCode -
     *            顧客コード
     * @@param l_tsCurrentTimestamp -
     *            現在日付
     * @@param l_strMarketCode
     *            市場コード
     * @@param l_strProductCode
     *            銘柄コード
     * @@param l_strType
     *            タイプ
     * @@param l_digestKey - (ダイジェストキー)<BR>
     * ダイジェストキー
     * @@param l_strSSIDValue - (SSID値)<BR>
     * SSID値
     * @@return String
     * @@roseuid 417E376801B4
     */
    public String createHashValue(
        String l_strInstitutionCode, 
        String l_strSrvDiv,
        String l_strBranchCode, 
        String l_strMainAccountCode, 
        Timestamp l_tsCurrentTimestamp, 
        String l_strMarketCode,
        String l_strProductCode, 
        String l_strType,
        WEB3DigestKey l_digestKey,
        String l_strSSIDValue) throws WEB3BaseException
    {
        String STR_METHOD_NAME = " createHashValue(String, String, String, String, Timestamp, " +
            "String, String, String, WEB3DigestKey, String)";
        log.entering(STR_METHOD_NAME);

        //1.1 getサービスマスター
        WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
            l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_strSrvDiv, false);

        //1.2 getハッシュ計算手順区分
        String l_strHashCalOrderDiv = l_srvRegiServiceMaster.getHashCalOrderDiv();
        log.debug("l_strHashCalOrderDiv = " + l_strHashCalOrderDiv);

        //1.3getハッシュ値一覧
        WEB3SrvRegiServiceUseKey[] l_srvRegiServiceUseKey = l_srvRegiServiceMaster.getHashList();

        //1.4 getハッシュ計算方式区分
        String l_strHashCalHowToDiv = l_srvRegiServiceMaster.getHashCalHowToDiv();
        log.debug("l_strHashCalHowToDiv = " + l_strHashCalHowToDiv);

        //1.5 replaceハッシュ計算方式
        String l_strHashCalHowTo = this.replaceHashCalHowTo(l_strHashCalHowToDiv);
        log.debug("l_strHashCalHowTo = " + l_strHashCalHowTo);

        String l_strSrvUseKey0 = null;
        if (l_srvRegiServiceUseKey.length > 0 && l_srvRegiServiceUseKey[0] != null)
        {
            l_strSrvUseKey0 = l_srvRegiServiceUseKey[0].getSrvUseKey();
            log.debug("l_strSrvUseKey0 = " + l_strSrvUseKey0);
        }

        String l_strSrvUseKey1 = null;
        if (l_srvRegiServiceUseKey.length > 1 && l_srvRegiServiceUseKey[1] != null)
        {
            l_strSrvUseKey1 = l_srvRegiServiceUseKey[1].getSrvUseKey();
            log.debug("l_strSrvUseKey1 = " + l_strSrvUseKey1);
        }

        List l_lis = new ArrayList();

		//障害対応 起動日付不正対応
        //1.6 get制御日付(Timestamp)
        
		//制御日付　@getControl() 通常計算(1),通常計算(2)で使用
		Date l_datControlDate = this.getControlTimestamp(l_tsCurrentTimestamp);
		//制御日付　@format("yyyyMMdd")
		String l_strControlDate = WEB3DateUtility.formatDate(l_datControlDate, "yyyyMMdd");
		log.debug("制御日付："+l_strControlDate);
		
		//実日付　@format("yyyyMMddHHmm") ２段階計算で使用
		String l_strCurrentDate = WEB3DateUtility.formatDate(l_tsCurrentTimestamp, "yyyyMMddHHmm");
		log.debug("実日付："+l_strCurrentDate);

        //1.7 ＜getハッシュ計算手順区分()＝”＜２段階計算＞”の場合＞
        if (WEB3SrvRegiHashCalOrderDivDef.TWO_STEP_CALCULATION.equals(l_strHashCalOrderDiv))
        {
            log.debug("＜getハッシュ計算手順区分()＝”＜２段階計算＞”の場合＞");
			log.debug("使用日付：" + l_strCurrentDate);
            //1.7.1 createHashValue
            l_lis.add(l_strSrvUseKey0);
            l_lis.add(l_strMarketCode);
            l_lis.add(l_strProductCode);
            l_lis.add(l_strType);
            String l_strHashAccountId = createAccountCodeHashValue(l_strHashCalHowTo, l_strInstitutionCode,
                l_strBranchCode, l_strMainAccountCode);
            l_lis.add(l_strHashAccountId);
            l_lis.add(l_strCurrentDate);
            l_lis.add(l_strSrvUseKey1);
        }
        //1.8 ＜getハッシュ計算手順区分()＝”＜通常計算（１）＞”の場合＞
        else if (WEB3SrvRegiHashCalOrderDivDef.NORMAL1.equals(l_strHashCalOrderDiv))
        {
            log.debug("＜getハッシュ計算手順区分()＝”＜通常計算（１）＞”の場合＞");
			log.debug("使用日付：" + l_strControlDate);
            l_lis.add(l_strControlDate);
            l_lis.add(l_strSrvUseKey0);
            l_lis.add(l_strInstitutionCode);
            l_lis.add(l_strBranchCode);
            l_lis.add(l_strMainAccountCode);
            l_lis.add(l_strSrvUseKey1);
        }
        //1.9 ＜getハッシュ計算手順区分()＝”＜通常計算（２）＞”の場合＞
        else if (WEB3SrvRegiHashCalOrderDivDef.NORMAL2.equals(l_strHashCalOrderDiv))
        {
            log.debug("＜getハッシュ計算手順区分()＝”＜通常計算（２）＞”の場合＞");
			log.debug("使用日付：" + l_strControlDate);
            l_lis.add(l_strControlDate);
            l_lis.add(l_strSrvUseKey0);
        }
        //1.10 ＜getハッシュ計算手順区分()＝”＜ログイン認証＞”の場合＞
        else if (WEB3SrvRegiHashCalOrderDivDef.LOGIN_CERTIFICATION.equals(l_strHashCalOrderDiv))
        {
            log.debug("＜getハッシュ計算手順区分()＝”＜ログイン認証＞”の場合＞");
            //引数.ダイジェストキーより、キー4(SHA1コード)を取得し、返却する。
            return l_digestKey.getKey4();
        }
        //getハッシュ計算手順区分()＝”＜シングルサインオン連携＞”の場合
        else if (WEB3SrvRegiHashCalOrderDivDef.SINGLE_SIGNON_COOPERATION.equals(l_strHashCalOrderDiv))
        {
            log.debug("＜getハッシュ計算手順区分()＝”＜＜シングルサインオン連携＞（7）＞”の場合＞");
            log.debug("使用日付：" + l_strControlDate);

            //get付加区分( )
            String l_strAdditionDiv = l_srvRegiServiceMaster.getAdditionDiv();

            // get付加区分( )の戻り値=nullの場合、「付加区分がnullです。」例外をスローする。
            if (l_strAdditionDiv == null)
            {
                log.debug("付加区分がnullです。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03160,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "付加区分がnullです。");
            }

            //文字列の配列を以下の順でセットする。
            //get付加区分( )の戻り値 + 引数.顧客コード.subString(0,6)
            String l_strAddDivMainAccount = l_strAdditionDiv;
            if (l_strMainAccountCode != null && l_strMainAccountCode.length() > 5)
            {
                l_strAddDivMainAccount = l_strAddDivMainAccount +
                    l_strMainAccountCode.substring(0, 6);
            }
            l_lis.add(l_strAddDivMainAccount);
            //getハッシュ値一覧の戻り値の最初の１件目.getサービス利用キー()の戻り値
            l_lis.add(l_strSrvUseKey0);

            //引数.SSID値
            l_lis.add(l_strSSIDValue);

            //引数.ダイジェストキー.getKey1の戻り値。
            l_lis.add(l_digestKey.getKey1());

            //getハッシュ値一覧の戻り値のの2件目.getサービス利用キー()の戻り値。
            l_lis.add(l_strSrvUseKey1);
        }

        String[] l_str = new String[l_lis.size()];
        l_lis.toArray(l_str);

        //1.11 createHashValue
        String l_strReturn = WEB3StringTypeUtility.createHashValue(l_strHashCalHowTo, l_str);
        log.debug("l_strReturn = " + "*" +l_strReturn.hashCode() + "*");
        
        //getハッシュ計算手順区分()＝”＜シングルサインオン連携＞”の場合
        if(WEB3SrvRegiHashCalOrderDivDef.SINGLE_SIGNON_COOPERATION.equals(l_strHashCalOrderDiv))
        {
            //大文字に変換して返却
            log.exiting(STR_METHOD_NAME);
            return l_strReturn.toUpperCase();
         }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return l_strReturn;
        }
    }

    /**
     * (replaceハッシュ計算方式) <BR>
     * 引数で指定されたハッシュ計算方式区分を、 <BR>
     * MessageDigestオブジェクト生成時に使用する文字列へと変換する。 <BR>
     * <BR>
     * ○引数.ハッシュ計算方式区分＝”1:MD2”の場合 <BR>
     * "MD2"を返却する。 <BR>
     * <BR>
     * ○引数.ハッシュ計算方式区分＝”2:MD5”の場合 <BR>
     * "MD5"を返却する。 <BR>
     * <BR>
     * ○引数.ハッシュ計算方式区分＝”3:SHA-1”の場合 <BR>
     * "SHA-1"を返却する。 <BR>
     * <BR>
     * ○引数.ハッシュ計算方式区分＝”4:SHA-256”の場合 <BR>
     * "SHA-256"を返却する。 <BR>
     * <BR>
     * ○引数.ハッシュ計算方式区分＝”5:SHA-384”の場合 <BR>
     * "SHA-384"を返却する。 <BR>
     * <BR>
     * ○引数.ハッシュ計算方式区分＝”6:SHA-512”の場合 <BR>
     * "SHA-512"を返却する。 <BR>
     *
     * @@param l_strHashCalcMethodDiv -
     *            ハッシュ計算方式区分
     * @@return String
     * @@roseuid 41B6CEDA03D7
     */
    public String replaceHashCalHowTo(String l_strHashCalHowToDiv)
    {
        String STR_METHOD_NAME = " replaceHashCalHowTo(String)";
        log.entering(STR_METHOD_NAME);

        if (WEB3HashCalHowToDivDef.MD2.equals(l_strHashCalHowToDiv))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3SrvRegiHashCalHowToDivValueDef.MD2;
        }
        else if (WEB3HashCalHowToDivDef.MD5.equals(l_strHashCalHowToDiv))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3SrvRegiHashCalHowToDivValueDef.MD5;
        }
        else if (WEB3HashCalHowToDivDef.SHA_1.equals(l_strHashCalHowToDiv))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3SrvRegiHashCalHowToDivValueDef.SHA_1;
        }
        else if (WEB3HashCalHowToDivDef.SHA_256.equals(l_strHashCalHowToDiv))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3SrvRegiHashCalHowToDivValueDef.SHA_256;
        }
        else if (WEB3HashCalHowToDivDef.SHA_384.equals(l_strHashCalHowToDiv))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3SrvRegiHashCalHowToDivValueDef.SHA_384;
        }
        else if (WEB3HashCalHowToDivDef.SHA_512.equals(l_strHashCalHowToDiv))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3SrvRegiHashCalHowToDivValueDef.SHA_512;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }

    /**
     * (get制御日付) <BR>
     * 引数.現在日付の時間部分を判定し、 <BR>
     * ハッシュ計算・予約語変換の際に「現在日付」として用いる日付を返却する。 <BR>
     * <BR>
     * 1) 引数.現在日付の時間部分をhhmmの数値として取得する。 <BR>
     * <BR>
     * 2) 1)で取得した数値＜ハッシュ計算用日付切替基準時間の場合 <BR>
     * 引数.現在日付－１日の日付を返却する。 <BR>
     * <BR>
     * 3) 1)で取得した数値≧ハッシュ計算用日付切替基準時間の場合 <BR>
     * 引数.現在日付を返却する。 <BR>
     *
     * @@param l_tsCurrentTimestamp -
     *            現在日付
     * @@return java.util.Date
     * @@roseuid 41BE4F7701F5
     */
    public Date getControlTimestamp(Timestamp l_tsCurrentTimestamp)
    {
        String STR_METHOD_NAME = " getCurrentTimestamp(Timestamp)";
        log.entering(STR_METHOD_NAME);
		
		//障害対応 起動日付不正対応
        //1) 引数.現在日付の時間部分をhhmmの数値として取得する。
		Calendar l_cal = Calendar.getInstance();
		l_cal.setTimeInMillis(l_tsCurrentTimestamp.getTime());
		
		int l_intCurrentDate = 
			l_cal.get(Calendar.HOUR_OF_DAY) * 100 + l_cal.get(Calendar.MINUTE);
		
		log.debug("実日付(hh:ss)：" + l_intCurrentDate);
		log.debug("ハッシュ計算用日付切替基準時間：" + DATE_CONVERSION_CONSTANT_TIME_FOR_HASH_CALC);

        //1)で取得した数値＜ハッシュ計算用日付切替基準時間の場合
        if (l_intCurrentDate < DATE_CONVERSION_CONSTANT_TIME_FOR_HASH_CALC)
        {
        	log.debug("制御日付使用");
            log.exiting(STR_METHOD_NAME);
            return WEB3DateUtility.addDay(l_tsCurrentTimestamp, -1);
        }
        else
        {
			log.debug("実日付使用");
            log.exiting(STR_METHOD_NAME);
            return l_tsCurrentTimestamp;
        }
    }
    
    /**
     * （create顧客IDハッシュ値）<BR>
     * 引数.顧客コード、証券会社コード、部店コードから<BR>
     * 顧客を取得し、顧客.顧客IDを引数.ハッシュ計算方式区分で<BR>
     * ハッシュ化して返却する。<BR>
     * 
     * @@param l_strHashCalHowTo
     *            ハッシュ計算方式区分
     * @@param l_strInstitusionCode
     *            証券会社コード
     * @@param l_strBranchCode
     *            部店コード
     * @@param l_strAccountCode
     *            顧客コード
     * @@return String
     *
     */
    public String createAccountCodeHashValue(String l_strHashCalHowTo, String l_strInstitutionCode,
                String l_strBranchCode, String l_strAccountCode) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "createAccountCodeHashValue(String, String)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();     
        MainAccount l_mainAccount;
        try
        {
            long l_longInstitutionId = l_accMgr.getInstitution(l_strInstitutionCode).getInstitutionId();
            l_mainAccount = l_accMgr.getMainAccount(l_longInstitutionId, l_strBranchCode, l_strAccountCode);
        }
        catch (NotFoundException l_exc)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_exc.getMessage(),
                l_exc);
        }
        String l_strAccountId = Long.toString(l_mainAccount.getAccountId());
        String[] l_str = {l_strAccountId};
        
        String l_strHashAccountId = WEB3StringTypeUtility.createHashValue(l_strHashCalHowTo, l_str);
        
        return l_strHashAccountId;
    
    }
    
    /**
     *  (get暗号化保有銘柄情報）
     * 　@(フィデリティ証券にて使用)
     * 補助口座から該当顧客の保有銘柄を取得し、
     * 取得した保有銘柄情報を暗号化して返却する。
     * <BR>
     * シーケンス図「（サービス利用）get暗号化保有銘柄情報」参照 <BR>
     * 
     * @@param l_strInstitusionCode
     *            証券会社コード
     * @@param l_strBranchCode
     *            部店コード
     * @@param l_strAccountCode
     *            顧客コード
     * @@return String
     *
     */
    public String getEncryptionMfAsset(String l_strInstitutionCode,
                String l_strBranchCode, String l_strAccountCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getEncryptionMfAsset";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        //拡張アカウントマネージャを取得する。
        WEB3GentradeAccountManager l_gentradeAccountManaer = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager(); 
        WEB3GentradeMainAccount l_mainAccount = null;
        SubAccount l_subAccount = null;
        try
        {
            //顧客を取得する。
            long l_longInstitutionId = l_gentradeAccountManaer.getInstitution(l_strInstitutionCode).getInstitutionId();
            l_mainAccount = (WEB3GentradeMainAccount)l_gentradeAccountManaer.
                                getMainAccount(l_longInstitutionId, l_strBranchCode, l_strAccountCode);
                                
            //取得した顧客オブジェクト.is信用口座開設()をコールする。
            // ※）is信用口座開設に渡す引数 
            //     弁済区分＝”0：指定なし 
            boolean l_blnisMarginAccountEstablished =
                l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
                
            //is信用口座開設=true の場合、以下を指定する。 
            // SubAccountTypeEnum.株式信用取引口座（保証金） 
            if (l_blnisMarginAccountEstablished == true)
            {
                log.exiting(STR_METHOD_NAME);
                l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            }
            else
            {
                //is信用口座開設=false の場合、以下を指定する。 
                //SubAccountTypeEnum.株式取引口座（預り金）
                log.exiting(STR_METHOD_NAME);
                l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            }
        }
        catch (NotFoundException l_exc)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_exc.getMessage(),
                l_exc);
        }
        
        //拡張投信ポジションマネージャを取得する。
        WEB3MutualFundPositionManager l_mutualFundPositionMgr =
            (WEB3MutualFundPositionManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getPositionManager();
            
        //保有資産銘柄一覧を取得する。 
        //  [引数] 
        //    補助口座：　@get補助口座()の戻り値 
        //    ソートキー：　@銘柄ID　@昇順 
        //    銘柄タイプ：　@ProductTypeEnum.投資信託
        DefaultSortKeySpec l_sortKeySpec = new DefaultSortKeySpec("product_id ASC");;
        List l_lisAssets = l_mutualFundPositionMgr.getAssets(
                l_subAccount,
                l_sortKeySpec,
                ProductTypeEnum.MUTUAL_FUND); 
       
        String l_strMutualFundAssets = null;
        int l_intNumber = 1;
               
        //getAssets()の戻り値の要素(=保有資産Params)数分Loop処理 
        int l_intAssetsLength = 0;
        if(l_lisAssets != null)
        {
            l_intAssetsLength = l_lisAssets.size();
        }
        
        for(int i = 0; i < l_intAssetsLength; i++)
        {
            MutualFundAsset l_mfAsset = (MutualFundAsset) l_lisAssets.get(i);
            AssetRow l_assetRow = (AssetRow) l_mfAsset.getDataSourceObject();
           
            //銘柄IDを取得する。
            long l_lngProductId = l_assetRow.getProductId();

            //拡張投信銘柄マネージャを取得する。
            WEB3MutualFundProductManager l_mutualFundProductManager = null;
            l_mutualFundProductManager =
               (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();
                
            //－拡張投信銘柄マネージャ.get投信銘柄()をコールし、拡張投信銘柄を取得する。
            WEB3MutualFundProduct l_mutualFundProduct = null; //拡張投信銘柄
            try
            {
                //銘柄オブジェクトを取得する。  
                // [引数] 
                // 銘柄ID：　@処理対象の保有資産Params.getProductId()
                Product l_product = 
                l_mutualFundProductManager.getProduct(l_lngProductId);
               
                //拡張投信銘柄を取得する。
                // [引数] 
                // 銘柄Row：　@getProduct()の戻り値
                l_mutualFundProduct = 
                    (WEB3MutualFundProduct) l_mutualFundProductManager.toProduct(
                        (MutualFundProductRow) l_product.getDataSourceObject());
            }
            catch (NotFoundException l_ex)
            {
                log.error("__NotFoundException__ 銘柄オブジェクトを取得できない!");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }  
               
            //銘柄コードを取得する。
            String l_strMutualFundProductCode = l_mutualFundProduct.getProductCode();
            log.debug("銘柄コード = " + l_strMutualFundProductCode);
            
            //資産IDを取得する。
            long l_longAssetId = l_assetRow.getAssetId();
            String l_strAssetId = Long.toString(l_longAssetId);
                    
            //解約可能残高口数を取得する。 
            BigDecimal l_bdSellPossibleQty =
                new BigDecimal(Double.toString(l_mutualFundPositionMgr.calcSellPossiblePositionQty(
                    l_subAccount, l_mutualFundProduct, l_strAssetId)));
            l_bdSellPossibleQty = l_bdSellPossibleQty.setScale(2,BigDecimal.ROUND_HALF_UP);
            String l_strSellPossibleQty = WEB3StringTypeUtility.formatNumber(l_bdSellPossibleQty.doubleValue());
            log.debug("解約可能残高口数 = " + l_strSellPossibleQty);
            
            //解約基準価額を取得する。
            BigDecimal l_bdSellValue = new BigDecimal(Double.toString(l_mutualFundProduct.getSellValue()));
            l_bdSellValue = l_bdSellValue.setScale(2, BigDecimal.ROUND_HALF_UP);
            String l_strSellValue = WEB3StringTypeUtility.formatNumber(l_bdSellValue.doubleValue());
            log.debug("解約基準価額 = " + l_strSellValue);
                    
            //評価額を取得する。
            double l_dblMarketValue = l_mutualFundPositionMgr.calcMarketValue(
                l_subAccount, l_mutualFundProduct, l_strAssetId);
            String l_strMarketValue = WEB3StringTypeUtility.formatNumber(l_dblMarketValue);
            log.debug("評価額 = " + l_strMarketValue);

            //保有銘柄情報文字列を作成する。
            String l_strMfAsset = null;
            if (l_strMutualFundProductCode != null || l_strSellPossibleQty != null
                    || l_strSellValue != null || l_strMarketValue != null)
            {
                l_strMfAsset = "c" + l_intNumber + "=" + l_strMutualFundProductCode + "&" +
                               "k" + l_intNumber + "=" + l_strSellPossibleQty + "&" +
                               "p" + l_intNumber + "=" + l_strSellValue + "&" +
                               "v" + l_intNumber + "=" + l_strMarketValue;                               
                log.debug("l_strMfAsset = " + l_strMfAsset);
                
                if (l_intNumber == 1)
                {
                    l_strMutualFundAssets = l_strMfAsset;
                }
                else
                {                        
                    l_strMutualFundAssets = l_strMutualFundAssets + "&" + l_strMfAsset;
                }
                
                log.debug("l_strMutualFundAssets = " + l_strMutualFundAssets);
                l_intNumber = l_intNumber + 1;
            }
        }
        
        String l_strEncryptString = null;
        if (l_strMutualFundAssets != null)
        {
            //チェックデジットを取得する。
            String l_strCeckDigit = createCheckDigit(l_strMutualFundAssets);
            log.debug("チェックデジット = " + l_strCeckDigit);
        
            //保有銘柄情報を暗号化する。
            String l_strEncryptMutualFundAssets = createEncryption(l_strMutualFundAssets);
            log.debug("暗号化された保有銘柄情報 = " + l_strEncryptMutualFundAssets);
        
            //チェックデジット＋暗号化された保有銘柄情報
            l_strEncryptString = l_strCeckDigit + l_strEncryptMutualFundAssets;
        }
        log.debug("チェックデジット+暗号化された保有銘柄情報 = " + l_strEncryptString);
        
        log.exiting(STR_METHOD_NAME);
        return l_strEncryptString;

    }

    /**
     * (フィデリティ証券暗号化用計算値) <BR>
     * チェックデジットの初期値 
     */
    private static final int    CHECK_DIGIT_INIT = 601;
    
    /**
     * (フィデリティ証券暗号化用計算値) <BR>
     * チェックデジットの割る数 
     */
    private static final int    CHECK_DIGIT_DIVISION = 1000;

    /**
     * (フィデリティ証券暗号化用変換テーブル) <BR>
     * 変換テーブル
     */
    private static final char[] CONVERT_TBL       
        = {'5','v','G','@@','h','U','*','d','0','=','4','R','p','j','n','s','B','1','&','.','6',
            'A','M','o','i','8','9','w','F','x','Y','l','3','-','k','T','7','q','c','2','e','Z'};

    /**
    *（create暗号化文字列）
    * (フィデリティ証券にて使用)
    *   引数.保有銘柄情報文字列を暗号化して返却する
    * 
    *@@param     l_strMfAssetString 
    *                 保有銘柄情報文字列
    *@@return    String 暗号化された保有銘柄情報
    */
    private String createEncryption(String l_strMfAssetString) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createEncryption";
        log.entering(STR_METHOD_NAME);
        
        StringBuffer l_sbEncryption = new StringBuffer();
        // 該当文字の変換テーブルにおける位置
        int l_iPosConvertTbl        = -1;
        // 変換後の文字
        int l_iSeachPosConvertTbl   = -1;
        // 変換テーブルのサイズ
        int p_iLenConvertTbl        = CONVERT_TBL.length;

        for (int i = 0; i < l_strMfAssetString.length(); i++)
        {
            // 1.変換テーブルから該当文字の位置を取得
            // 2.取得した位置に現在の桁をプラス
            // 3.変換テーブルのサイズを越えた場合先頭に戻る処理
            // 4.変換テーブルから2で取得した位置の文字を取得しStringBufferに追加
            l_iPosConvertTbl       = getPosConvertTbl(l_strMfAssetString.charAt(i)); // 1
            l_iSeachPosConvertTbl  = l_iPosConvertTbl + (i+1);                       // 2
            l_iSeachPosConvertTbl = (l_iSeachPosConvertTbl - 1) % p_iLenConvertTbl;  // 3
            l_sbEncryption.append(CONVERT_TBL[l_iSeachPosConvertTbl]);               // 4
        }
            
        log.exiting(STR_METHOD_NAME);
        return l_sbEncryption.toString();

    }

    /**
    * (get変換テーブル）
    * (フィデリティ証券にて使用)
    *   変換テーブル内の該当文字の位置を返却する
    * 
    *@@param     l_charSearchCharacter
    *                 検索文字
    *@@return    int  変換テーブル内の該当文字の位置を格納する変数
    */
    private int getPosConvertTbl(char l_charSearchCharacter) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getPosConvertTbl";
        log.entering(STR_METHOD_NAME);
        try
        {
            // 変換テーブル内の該当文字の位置を格納する変数
            int l_iPosConvertTbl = -1;
            // 変換テーブルから該当文字の位置を取得する
            for (int i = 0; i < CONVERT_TBL.length; i++)
            {
                if (Character.toUpperCase(l_charSearchCharacter) == Character.toUpperCase(CONVERT_TBL[i]))
                {
                    l_iPosConvertTbl = i;
                    break;
                }
            }
            // 変換テーブルに該当文字が無い場合エラーとする
            if (l_iPosConvertTbl == -1)
            {
                throw new NotFoundException("変換テーブルに該当文字列がありません。");
            }

            // 実際の変換テーブルは1からなので、1プラスしたものを返す
            log.exiting(STR_METHOD_NAME);
            return l_iPosConvertTbl + 1;
        }
        catch(NotFoundException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
        
    /**
    * (createチェックデジット）
    * (フィデリティ証券にて使用)
    *   引数.保有銘柄情報文字列からチェックデジットを生成する
    * 
    *@@param     l_strMfAssetString
    *               保有銘柄情報文字列
    *@@return    String  チェックデジット
    */
    private String createCheckDigit(String l_strMfAssetString) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createCheckDigit";
        log.entering(STR_METHOD_NAME);
        // チェックデジット（文字列）
        String l_sCheckDigit = null;
            
        // チェックデジットに初期値を代入
        int l_iCheckDigit = CHECK_DIGIT_INIT;
        // 該当文字の変換テーブルにおける位置
        int l_iPosConvertTbl        = 0;
        for (int i = 0; i < l_strMfAssetString.length(); i++)
        {
            // 変換テーブルから該当文字の位置を取得し加算していく
            l_iPosConvertTbl = getPosConvertTbl(l_strMfAssetString.charAt(i));
            l_iCheckDigit    = l_iCheckDigit + l_iPosConvertTbl;
        }

        // チェックデジット取得
        l_iCheckDigit = l_iCheckDigit % CHECK_DIGIT_DIVISION;

        l_sCheckDigit = Integer.toString(l_iCheckDigit);

        // チェックデジットが3桁になるようゼロ詰めする
        if (l_sCheckDigit != null && l_sCheckDigit.length() == 1)
        {
            l_sCheckDigit = "00" + l_sCheckDigit;
        }
        else if (l_sCheckDigit != null && l_sCheckDigit.length() == 2)
        {
            l_sCheckDigit = "0" + l_sCheckDigit;
        }

        log.exiting(STR_METHOD_NAME);
        return l_sCheckDigit;
                
    }

    /**
     * (getCDキー)<BR>
     * CDキー（ユーザー識別子）を作成して返却する。<BR>
     * <BR>
     * １） １（固定値）+引数.部店コード+引数.顧客コード（6桁）を2倍した値の文字列を生成する。<BR>
     * <BR>
     * ２）サービス情報管理.getサービスマスター()をコールする。<BR>
     * 　@　@[引数の設定]<BR>
     * 　@　@証券会社コード：引数の証券会社コード<BR>
     * 　@　@サービス区分：引数のサービス区分<BR>
     * 　@　@is行ロック：false<BR>
     * <BR>
     * ３）サービス利用キーオブジェクトを取得する。<BR>
     * 　@３－１）２）で取得したサービスマスターオブジェクト.getハッシュ値一覧()をコールする。<BR>
     * 　@　@データが取得出来ない場合、「該当データなし。」の例外をスローする。<BR>
     * 　@３－２）３－１）で取得した一覧から最初の1件を取得する。<BR>
     * <BR>
     * ４）文字列配列に以下の文字列を追加する。<BR>
     * 　@４－１）１）で取得した文字列<BR>
     * 　@４－２）３）で取得したサービス利用キーオブジェクト.getサービス利用キー()の先頭110バイト<BR>
     * 　@４－３）this.get制御日付()の戻り値（'YYYYMMDD'に変換）<BR>
     * 　@　@[get制御日付()引数の設定]<BR>
     * 　@　@　@現在日付：new Timestamp(new Date().getTime())<BR>
     * <BR>
     * ５）計算方式を取得する。<BR>
     * 　@５－１）２）で取得したサービスマスターオブジェクト.getハッシュ計算方式区分（）をコールする。<BR>
     * 　@５－２）this.replaceハッシュ計算方式()をコールする。<BR>
     * <BR>
     * ６）ハッシュ値の生成<BR>
     * 　@　@WEB3StringTypeUtility.createHashValue()を用いてハッシュ値を生成する。<BR>
     * 　@　@[引数の設定]<BR>
     * 　@　@計算方式： ５）で取得した計算方式<BR>
     * 　@　@計算対象：４）にて作成した文字列配列<BR>
     * <BR>
     * ７）下記の文字列を生成する。<BR>
     * 　@　@１）の文字列＋６）の前半20バイト<BR>
     * <BR>
     * ８）７）を返却する。<BR>
     * <BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * 口座コード<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * サービス区分<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getCDKey(
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strSrvDiv,
        String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCDKey(String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //１（固定値）+引数.部店コード+引数.顧客コード（6桁）を2倍した値の文字列を生成する
        String l_strAccountCodeSub = "";
        if (l_strAccountCode != null)
        {
            l_strAccountCodeSub = l_strAccountCode;
        }
        if (l_strAccountCode != null && l_strAccountCode.length() > 6)
        {
            l_strAccountCodeSub = l_strAccountCode.substring(0, 6);
        }

        long l_lngMergeCode =
            Long.parseLong(1 + l_strBranchCode + l_strAccountCodeSub);
        String l_strMergeCode = l_lngMergeCode * 2 + "";

        //サービス情報管理.getサービスマスター()をコールする。
        //[引数の設定]
        //　@証券会社コード：引数の証券会社コード
        //　@サービス区分：引数のサービス区分
        //　@is行ロック：false
        WEB3SrvRegiServiceInfoManagement l_serviceInfoManagement =
            new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
            l_serviceInfoManagement.getSrvMaster(
                l_strInstitutionCode, l_strSrvDiv, false);

        //サービス利用キーオブジェクトを取得する
        WEB3SrvRegiServiceUseKey[] l_serviceUseKeys =
            l_srvRegiServiceMaster.getHashList();
        //データが取得出来ない場合、「該当データなし。」の例外をスローする
        if (l_serviceUseKeys.length == 0)
        {
            log.debug("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "テーブルに該当するデータがありません。");
        }

        //文字列配列に以下の文字列を追加する。
        List l_lisMergeStrs = new ArrayList();
        //１）で取得した文字列
        l_lisMergeStrs.add(l_strMergeCode);

        //取得したサービス利用キーオブジェクト.getサービス利用キー()の先頭110バイト
        String l_strSrvUseKey = l_serviceUseKeys[0].getSrvUseKey();
        if (WEB3StringTypeUtility.getByteLength(l_strSrvUseKey) > 110)
        {
            l_strSrvUseKey = new String(l_strSrvUseKey.getBytes(), 0, 110);
        }
        l_lisMergeStrs.add(l_strSrvUseKey);

        //this.get制御日付()の戻り値（'YYYYMMDD'に変換）
        //引数の設定
        //現在日付：new Timestamp(new Date().getTime())
        Timestamp l_tsSystemTime = new Timestamp(new Date().getTime());
        Date l_datControlDate =
            this.getControlTimestamp(l_tsSystemTime);
        String l_strControlDate =
            WEB3DateUtility.formatDate(
                l_datControlDate,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        l_lisMergeStrs.add(l_strControlDate);

        //計算方式を取得する
        //取得したサービスマスターオブジェクト.getハッシュ計算方式区分（）をコールする
        String l_strHowToDiv = l_srvRegiServiceMaster.getHashCalHowToDiv();

        //this.replaceハッシュ計算方式()をコールする
        String l_strCalHowTo = this.replaceHashCalHowTo(l_strHowToDiv);

        //ハッシュ値の生成
        //WEB3StringTypeUtility.createHashValue()を用いてハッシュ値を生成する。
        //[引数の設定]
        //計算方式： ５）で取得した計算方式
        //計算対象：４）にて作成した文字列配列
        String[] l_strCombines = new String[l_lisMergeStrs.size()];
        l_lisMergeStrs.toArray(l_strCombines);
        String l_strHashValue = WEB3StringTypeUtility.createHashValue(
            l_strCalHowTo,
            l_strCombines);

        //下記の文字列を生成する
        //１）の文字列＋６）の前半20バイト
        if (WEB3StringTypeUtility.getByteLength(l_strHashValue) > 20)
        {
            l_strHashValue = new String(l_strHashValue.getBytes(), 0, 20);
        }
        String l_strReturnValue = l_strMergeCode + l_strHashValue;

        log.exiting(STR_METHOD_NAME);
        return l_strReturnValue;
    }
}@
