head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.05.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPHistoryPerContract.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 建玉ごと変動情報(WEB3TPHistoryPerContract.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/02 齋藤　@栄三 (FLJ) 新規作成
Revesion History : 2007/07/28 孟亜南 (中訊)モデルNo.116 No.138
Revesion History : 2008/02/01 崔遠鵬(中訊) モデルNo.259
Revesion History : 2008/10/21 張少傑(中訊) モデルNo.327
Revesion History : 2008/10/31 張少傑(中訊) モデルNo.351
Revesion History : 2008/11/04 張少傑(中訊) モデルNo.356
Revesion History : 2008/11/07 孟亞南(中訊) モデルNo.364
Revesion History : 2008/11/14 孟亞南(中訊) モデルNo.367
Revesion History : 2009/12/03 張騰宇(中訊) モデルNo.401 No.402 No.403
*/
package webbroker3.tradingpower.updtpower.contract;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.define.WEB3TPContractAmountApplyDateDef;
import webbroker3.tradingpower.define.WEB3TPRestraintDivDef;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.util.ToStringBuilder;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;

/**
 * (建玉ごと変動情報)
 */
public class WEB3TPHistoryPerContract 
{
    
    /**
     * (ログ出力ユーティリティ)
     */
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3TPHistoryPerContract.class);
     
     /**
      * (デバッグison)
      */
     private static boolean DBG = log.ison();

    /**
     * (建玉変動)
     */
    private List histories;
    
    /**
     * (対象建玉)
     */
    private WEB3TPTargetContract targetContract;
    
    /**
     * (建玉情報)
     */
    private WEB3TPContractInfo contractInfo;
    
    /**
     * (保証金率の分母)
     */
    private static final double depositDenominator = 100;
    
    /**
     * (按分計算時の誤差補正値)
     */
    private static final double errorCorrection = 0.000001;
    
    /**
     * @@roseuid 4104AB4702FD
     */
    public WEB3TPHistoryPerContract() 
    {
        histories = new ArrayList();
    }
    
    /**
     * (create建玉ごと変動情報)<BR>
     * 建玉ごと変動情報を生成する。<BR>
     * @@param l_contractInfo - (建玉情報)
     * @@param l_targetContract - (対象建玉)
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPHistoryPerContract
     * @@roseuid 40DC087E02EE
     */
    public static WEB3TPHistoryPerContract create(WEB3TPContractInfo l_contractInfo, WEB3TPTargetContract l_targetContract) 
    {
        WEB3TPHistoryPerContract l_thisInstance = new WEB3TPHistoryPerContract();
        l_thisInstance.setContractInfo(l_contractInfo);
        l_thisInstance.setTargetContract(l_targetContract);
        return l_thisInstance;
    }
    
    /**
     * (get建玉変動)<BR>
     * 建玉変動を取得する。<BR>
     * @@return java.util.List
     * @@roseuid 40F3B85B0188
     */
    public List getHistories() 
    {
        return histories;
    }
    
    /**
     * (get対象建玉)<BR>
     * 対象建玉を取得する。<BR>
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPTargetContract
     * @@roseuid 40DFB452037A
     */
    public WEB3TPTargetContract getTargetContract() 
    {
        return targetContract;
    }
    
    /**
     * (set対象建玉)<BR>
     * 引数の対象建玉をセットする。<BR>
     * @@param l_targetContract - (対象建玉)
     * @@roseuid 40DFB4520399
     */
    public void setTargetContract(WEB3TPTargetContract l_targetContract) 
    {
        targetContract = l_targetContract;
    }
    
    /**
     * (get建玉情報)<BR>
     * 建玉情報を取得する。<BR>
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPContractInfo
     * @@roseuid 
     */
    public WEB3TPContractInfo getContractInfo() 
    {
        return contractInfo;
    }
    
    /**
     * (set建玉情報)<BR>
     * 引数の建玉情報をセットする。<BR>
     * @@param l_contractInfo - (建玉情報)
     * @@roseuid 
     */
    public void setContractInfo(WEB3TPContractInfo l_contractInfo) 
    {
        contractInfo = l_contractInfo;
    }
    
    /**
     * (get未決済建玉の集計)<BR>
     * 引数で指定した日の未決済建玉の集計をする。<BR>
     * <BR>
     * １）元建株数を取得する。 <BR>
     * 　@　@未決済建株数＝対象建玉詳細.元建株数 <BR>
     * <BR>
     * ２）建玉変動一覧を取得する。 <BR>
     * <BR>
     * ３）未決済建玉の建株数から各集計項目を導き出す。 <BR>
     * 　@－集計項目： <BR>
     * 　@　@・建玉代金 <BR>
     * 　@　@・必要保証金 <BR>
     * 　@　@・現金必要保証金 <BR>
     * 　@　@・発注分建玉代金 <BR>
     * 　@　@・発注分必要保証金 <BR>
     * 　@　@・発注分現金必要保証金 <BR>
     * 　@　@・未決済建玉評価損 <BR>
     * 　@　@・未決済建玉評価益 <BR>
     * 　@　@・建手数料 <BR>
     * 　@　@・日歩・逆日歩損 <BR>
     * 　@　@・日歩・逆日歩益 <BR>
     * 　@　@・その他建玉諸経費 <BR>
     * <BR>
     * 　@【前提条件】 <BR>
     * 　@・当日建玉代金計上開始基準日を取得する。 <BR>
     * 　@　@［取得方法@］ <BR>
     * 　@　@　@・余力計算条件().get会社部店別余力計算条件（"contractamount.apply.date"：当日建玉代金計上開始日）の戻り値が、<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@1：FROM_BIZ_DATEの場合<BR> 
     * 　@　@　@　@・.当日建玉代金計上開始基準日に0をセットする。 <BR>
     * <BR>
     * 　@　@　@・余力計算条件().get会社部店別余力計算条件（"contractamount.apply.date"：当日建玉代金計上開始日）の戻り値が、<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@2：FROM_T2の場合 <BR>
     * 　@　@　@　@・当日建玉代金計上開始基準日に2をセットする。 <BR>
     * <BR>
     * 　@　@　@・以外の場合 <BR>
     * 　@　@　@　@・当日建玉代金計上開始基準日に1をセットする。 <BR>
     * <BR>
     * 　@・対象建玉詳細.建日＞＝余力計算条件.営業日(T+0) - 当日建玉代金計上開始基準日の場合 <BR>
     * 　@　@変動反映開始日に建日 + 当日建玉代金計上開始基準日をセットする。 <BR>
     * 　@・対象建玉詳細.建日＜余力計算条件.営業日(T+0) - 当日建玉代金計上開始基準日の場合 <BR>
     * 　@　@変動反映開始日＝余力計算条件.営業日(T+0) <BR>
     * 　@・( 変動反映開始日＜＝n ) 且つ ( n＜＝余力計算条件.営業日[T+5] ) の場合<BR> 
     * <BR>
     * 　@　@　@・対象建玉.is約定済＝true の場合、且つ、元建株数 > 0の場合以下の処理を実行する。 <BR>
     * <BR>
     * 　@　@　@　@－未決済建玉の建株数の求め方 <BR>
     * 　@　@　@　@　@－対象建玉.新規建約定済＝true 且つ 建玉変動.トランザクションカテゴリ≠新規建　@の場合 <BR>
     * 　@　@　@　@　@　@未決済建株数＝未決済建株数－建玉変動.建株数 <BR>
     * 　@　@　@　@　@　@※当日建株の場合、未約定現引現渡の時だけ建玉変動.建株数をマイナスする。 <BR>
     * <BR>
     * 　@　@　@　@－建手数料の求め方 <BR>
     * 　@　@　@　@　@・建手数料(n)＝対象建玉詳細.建手数料　@　@　@　@　@　@　@×　@未決済建株数　@/　@対象建玉詳細.元建株数 <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@＋　@対象建玉詳細.建手数料消費税　@　@×　@未決済建株数　@/　@対象建玉詳細.元建株数 <BR>
     * <BR>
     * 　@　@　@　@－その他建玉諸経費の求め方 <BR>
     * 　@　@　@　@　@・その他建玉諸経費(n)＝対象建玉詳細.名義書換料　@　@　@　@　@　@　@×　@未決済建株数　@/　@対象建玉詳細.元建株数 <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@＋　@対象建玉詳細.名義書換料消費税　@　@×　@未決済建株数　@/　@対象建玉詳細.元建株数 <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@＋　@対象建玉詳細.管理費　@　@　@　@　@　@　@×　@未決済建株数　@/　@対象建玉詳細.元建株数 <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@＋　@対象建玉詳細.管理費消費税　@　@　@　@×　@未決済建株数　@/　@対象建玉詳細.元建株数 <BR>
     * <BR>
     * 　@　@　@　@－順日歩・逆日歩損益、未決済建玉評価損益の求め方 <BR>
     * 　@　@　@　@　@・対象建玉詳細.建区分＝買建　@の場合 <BR>
     * 　@　@　@　@　@　@順日歩・逆日歩損(n)＝対象建玉詳細.順日歩　@×　@未決済建株数　@/　@対象建玉詳細.元建株数 <BR>
     * 　@　@　@　@　@　@順日歩・逆日歩益(n)＝対象建玉詳細.逆日歩　@×　@未決済建株数　@/　@対象建玉詳細.元建株数 <BR>
     * 　@　@　@　@　@　@・対象建玉詳細.評価単価＞0　@の場合 <BR>
     * 　@　@　@　@　@　@　@未決済建玉評価損益(n)＝対象建玉詳細.評価単価　@×　@未決済建株数　@-　@対象建玉詳細.建単価　@×　@未決済建株数<BR>
     * 　@　@　@　@　@・対象建玉詳細.建区分＝売建　@の場合 <BR>
     * 　@　@　@　@　@　@順日歩・逆日歩損(n)＝対象建玉詳細.逆日歩　@×　@未決済建株数　@/　@対象建玉詳細.元建株数 <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@ ＋　@対象建玉詳細.貸株料　@　@　@ ×　@未決済建株数　@/　@対象建玉詳細.元建株数 <BR>
     * 　@　@　@　@　@　@順日歩・逆日歩益(n)＝対象建玉詳細.順日歩　@×　@未決済建株数　@/　@対象建玉詳細.元建株数 <BR>
     * 　@　@　@　@　@　@・対象建玉詳細.評価単価＞0　@の場合<BR>
     * 　@　@　@　@　@　@　@未決済建玉評価損益(n)＝対象建玉詳細.建単価　@×　@未決済建株数　@-　@対象建玉詳細.評価単価 ×　@未決済建株数<BR>
     * 　@　@　@　@　@・未決済建玉評価損益(n)＜0　@の場合<BR>
     * 　@　@　@　@　@　@　@未決済建玉評価損(n)＝ABS(未決済建玉評価損益(n))<BR>
     * 　@　@　@　@　@・未決済建玉評価損益(n)＞＝0　@の場合<BR>
     * 　@　@　@　@　@　@　@未決済建玉評価益(n)＝未決済建玉評価損益(n)<BR>
     * <BR>
     * 　@　@－建玉代金／必要保証金／現金必要保証金／発注分建玉代金／発注分必要保証金／発注分現金必要保証金の求め方<BR>
     * 　@　@　@・対象建玉.is約定済＝true の場合<BR>
     * 　@　@　@　@建玉代金(n)　@　@　@　@　@　@　@＝対象建玉詳細.建単価<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@×　@未決済建株数<BR>
     * 　@　@　@　@必要保証金(n)　@　@　@　@　@　@＝建玉代金(n)<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@×　@対象建玉詳細.保証金率　@/　@100<BR>
     * 　@　@　@　@現金必要保証金(n)　@　@　@　@＝建玉代金(n)<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@×　@対象建玉詳細.現金保証金率　@/　@100<BR>
     * 　@　@　@・対象建玉.is約定済＝false の場合<BR>
     * 　@　@　@　@発注分建玉代金(n)　@　@　@　@＝対象建玉詳細.建単価<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@×　@未決済建株数<BR>
     * 　@　@　@　@発注分必要保証金(n)　@　@　@＝建玉代金(n)<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@×　@対象建玉詳細.保証金率　@/　@100<BR>
     * 　@　@　@　@発注分現金必要保証金(n)　@＝建玉代金(n)<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@×　@対象建玉詳細.現金保証金率　@/　@100<BR>
     * <BR>
     * ４）未決済建玉の集計結果を返す。 <BR>
     * <BR>
     * ※nは、引数の指定日。<BR>
     * ※計算式に使用する各値の取得方法@<BR>
     * ・対象建玉詳細.元建株数　@　@　@　@　@　@　@　@　@　@・・・対象建玉詳細.get元建株数()<BR>
     * ・対象建玉詳細.未決済建玉評価損益　@　@　@　@　@・・・対象建玉詳細.get未決済建玉評価損益()<BR>
     * ・対象建玉詳細.建区分　@　@　@　@　@　@　@　@　@　@　@・・・対象建玉詳細.get建区分()<BR>
     * ・対象建玉詳細.順日歩　@　@　@　@　@　@　@　@　@　@　@・・・対象建玉詳細.get順日歩()<BR>
     * ・対象建玉詳細.逆日歩損　@　@　@　@　@　@　@　@　@　@・・・対象建玉詳細.get逆日歩()<BR>
     * ・対象建玉詳細.建手数料　@　@　@　@　@　@　@　@　@　@・・・対象建玉詳細.get建手数料()<BR>
     * ・対象建玉詳細.建手数料消費税　@　@　@　@　@　@　@・・・対象建玉詳細.get建手数料消費税()<BR>
     * ・対象建玉詳細.名義書換料　@　@　@　@　@　@　@　@　@・・・対象建玉詳細.get名義書換料()<BR>
     * ・対象建玉詳細.名義書換料消費税　@　@　@　@　@　@・・・対象建玉詳細.get名義書換料消費税()<BR>
     * ・対象建玉詳細.管理費　@　@　@　@　@　@　@　@　@　@　@・・・対象建玉詳細.get管理費()<BR>
     * ・対象建玉詳細.管理費消費税　@　@　@　@　@　@　@　@・・・対象建玉詳細.get管理費消費税()<BR>
     * ・対象建玉詳細.貸株料　@　@　@　@　@　@　@　@　@　@　@・・・対象建玉詳細.get貸株料()<BR>
     * ・対象建玉詳細.建日　@　@　@　@　@　@　@　@　@　@　@　@・・・対象建玉詳細.get建日()<BR>
     * ・対象建玉詳細.建単価　@　@　@　@　@　@　@　@　@　@　@・・・対象建玉詳細.get建単価()<BR>
     * ・対象建玉詳細.保証金率　@　@　@　@　@　@　@　@　@　@・・・対象建玉詳細.get保証金率()<BR>
     * ・対象建玉詳細.現金保証金率　@　@　@　@　@　@　@　@・・・対象建玉詳細.get現金保証金率()<BR>
     * ・対象建玉詳細.評価単価　@　@　@　@　@　@　@　@　@　@・・・対象建玉詳細.get評価単価()<BR>
     * ・余力計算条件.営業日(T+0)　@　@　@　@　@　@　@　@ ・・・余力計算条件.get営業日(T+0)<BR>
     * ・建玉変動.建株数　@　@　@　@　@　@　@　@　@　@　@　@　@・・・建玉変動.get建株数()<BR>
     * ・保証金率　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@ ・・・対象建玉詳細.get保証金率()<BR>
     * ・現金保証金率　@　@　@　@　@　@　@　@　@　@　@　@　@　@ ・・・対象建玉詳細.get現金保証金率()<BR>
     * @@param l_datDate - (指定日)
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPSummaryOpenContract
     * @@roseuid 40EB66BC014A
     */
    public WEB3TPSummaryOpenContract getSummaryOpenContract(Date l_datDate) 
    {        
        //create未決済建玉の集計      
        WEB3TPSummaryOpenContract l_sumOpenContract = WEB3TPSummaryOpenContract.create();
            
        //対象建玉を取得
        WEB3TPTargetContract l_targetContract = getTargetContract();
            
        //対象建玉詳細を取得
        WEB3TPTargetContractDetail l_targetContractDetail = l_targetContract.getTargetContractDetail();

        //余力計算条件を取得
        WEB3TPCalcCondition l_calcCondition = getContractInfo().getCalcCondition();
    
        //営業日(T+0)を取得
        Date l_datBizDate0 = l_calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_0);
    
        //建日を取得
        Date l_datOpenDate = l_targetContractDetail.getOpenDate();
        
        //当日建玉計上開始日の設定値を取得
        String l_strContractApplyDate = l_calcCondition.getInstBranCalcCondition(WEB3TPCalcCondition.CONTRACTAMOUNT_APPLY_DATE);        

        //当日建株フラグ
        boolean isTodaysContract = false;
        
        //建日=営業日(T+0)の場合
        if(WEB3DateUtility.compareToDay(l_datOpenDate, l_datBizDate0) == 0)
        {
            //約定済みの場合
            if(l_targetContract.isContractExecuted())
            {
                isTodaysContract = true;
            }
        }

        //元建株数を取得
        double l_dblOriginalQuantity = 0.0;

        if(isTodaysContract)
        {
            //基準となるのは建株数
            l_dblOriginalQuantity = l_targetContractDetail.getQuantity();
        }
        else
        {
            //基準となるのは元建株数(逆按分後の数値)
            l_dblOriginalQuantity = l_targetContractDetail.getOriginalQuantity();
        }
        

        //元建株数を未決済建株数の計算元とする
        double l_dblOpenContractQuantity = l_dblOriginalQuantity;
        
        //建玉変動一覧を取得
        List l_histories = getHistories();
        int l_intHistorySize = l_histories.size();

        //　@・余力計算条件().get会社部店別余力計算条件（
        //    "contractamount.apply.date"：当日建玉代金計上開始日）の戻り値が、1：FROM_BIZ_DATEの場合 
        //　@・.当日建玉代金計上開始基準日に0をセットする。 
        //　@・余力計算条件().get会社部店別余力計算条件（
        //    "contractamount.apply.date"：当日建玉代金計上開始日）の戻り値が、2：FROM_T2の場合 
        // ・当日建玉代金計上開始基準日に2をセットする。 
        // ・以外の場合 
        // ・当日建玉代金計上開始基準日に1をセットする。 
        int l_intContractApplyDate = 1;
        if (WEB3TPContractAmountApplyDateDef.DEFAULT.equals(l_strContractApplyDate))
        {
            l_intContractApplyDate = 1;
        }
        else if (WEB3TPContractAmountApplyDateDef.FROM_BIZ_DATE.equals(l_strContractApplyDate))
        {
            l_intContractApplyDate = 0;
        }
        else if (WEB3TPContractAmountApplyDateDef.FROM_T2.equals(l_strContractApplyDate))
        {
            l_intContractApplyDate = 2;
        }

        //対象建玉詳細.建日＞＝余力計算条件.営業日(T+0) - 当日建玉代金計上開始基準日の場合
        //変動反映開始日に建日 + 当日建玉代金計上開始基準日をセットする。
        //対象建玉詳細.建日＜余力計算条件.営業日(T+0) - 当日建玉代金計上開始基準日の場合
        //変動反映開始日＝余力計算条件.営業日(T+0)
        //変動反映開始日
        Date l_datReflectStartDay;
        if ((WEB3DateUtility.compareToDay(l_datOpenDate,
            l_calcCondition.rollBizDate(l_datBizDate0, - l_intContractApplyDate)) >= 0))
        {
            l_datReflectStartDay =
                l_calcCondition.rollBizDate(l_datOpenDate, l_intContractApplyDate);
        }
        else
        {
            l_datReflectStartDay = l_datBizDate0;
        }

        //( 変動反映開始日＜＝n ) 且つ ( n＜＝余力計算条件.営業日[T+5] ) の場合 
        //変動反映終了日＝営業日(T+5)
        Date l_datReflectEndDay = l_calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_5);

        //指定日
        long l_lngDatDate = l_datDate.getTime();

        //期間内の場合
        if ((l_datReflectStartDay.getTime() <= l_lngDatDate)
            && (l_lngDatDate <= l_datReflectEndDay.getTime()))
        {
            //対象建玉.is約定済＝true の場合、且つ、元建株数 > 0の場合以下の処理を実行する。 
            //新規建として未約定の時は按分を行う必要ない(手数料=0,評価損益=0)
            if(l_targetContract.isContractExecuted())
            {
                //元建株数が0以下の時(建株テーブルに入った後に約定取消等)は按分を行う必要ない(手数料=0,評価損益=0)
                if(l_dblOriginalQuantity > 0)
                {
                    //建玉変動一覧のサイズでループ
                    for(int i = 0; i < l_intHistorySize; i++)
                    {
                        //建玉変動を取得
                        WEB3TPHistory l_history = (WEB3TPHistory)l_histories.get(i);
                
                        //トランザクションカテゴリ取得
                        FinTransactionCateg l_transCateg = l_history.getTransactionCateg();
                
                        //返済・現引現渡
                        if(FinTransactionCateg.EQTYPE_CLOSE_MARGIN.equals(l_transCateg)
                            || FinTransactionCateg.EQTYPE_SWAP_MARGIN.equals(l_transCateg))
                        {
                            //当日建株の場合、未約定現引現渡だけマイナスする
                            if(isTodaysContract)
                            {
                                if(! l_history.isExecuted())
                                {
                                    //未決済建株数 -= 変動株数
                                    l_dblOpenContractQuantity -= l_history.getQuantity();
                                }
                            }
                            else
                            {
                                //未決済建株数 -= 変動株数
                                l_dblOpenContractQuantity -= l_history.getQuantity();
                            }
                        }
                    }

                    //按分率を計算
                    double l_dblRate = l_dblOpenContractQuantity / l_dblOriginalQuantity;

                    if(DBG)
                    {
                        StringBuffer l_sbLog = new StringBuffer(" net_open_contract_quantity=");
                        l_sbLog.append(l_dblOpenContractQuantity);
                        l_sbLog.append(" proportional_rate=");
                        l_sbLog.append(l_dblRate);
                        log.debug(l_sbLog.toString());
                    }

                    //建手数料
                    double l_dblSetupFee =
                        Math.floor(l_targetContractDetail.getSetupFee()     * l_dblRate + errorCorrection)
                            + Math.floor(l_targetContractDetail.getSetupFeeTax() * l_dblRate + errorCorrection);

                    //その他建玉諸経費
                    double l_dblOtherCost =
                        Math.floor(l_targetContractDetail.getNameTransferFee()     * l_dblRate + errorCorrection)
                            + Math.floor(l_targetContractDetail.getNameTransferFeeTax() * l_dblRate + errorCorrection)
                            + Math.floor(l_targetContractDetail.getManagementFee()      * l_dblRate + errorCorrection)
                            + Math.floor(l_targetContractDetail.getManagementFeeTax()   * l_dblRate + errorCorrection);

                    //順日歩・逆日歩損
                    double l_dblInterestLoss = 0.0;
                    //順日歩・逆日歩益
                    double l_dblInterestProfit = 0.0;
                    
                    //建単価を取得
                    double l_dblContractPrice = l_targetContractDetail.getContractPrice();
                    //評価単価
                    double l_dblUnitPrice = l_targetContractDetail.getUnitPrice();
                    //建玉代金
                    double l_dblTmpContractAmount = Math.floor(l_dblContractPrice * l_dblOpenContractQuantity);
                    //評価代金
                    double l_dblTmpEstimatedAmount = Math.floor(l_dblUnitPrice * l_dblOpenContractQuantity);
                    //未決済建玉評価損益
                    double l_dblAssetProfitLoss = 0.0;

                    //建区分を取得
                    ContractTypeEnum l_contractType = l_targetContractDetail.getContractType();
                
                    //買建の場合
                    if(ContractTypeEnum.LONG.equals(l_contractType))
                    {
                        //順日歩が損になる
                        l_dblInterestLoss =
                            Math.floor(l_targetContractDetail.getInterestFee() * l_dblRate + errorCorrection);
                        
                        //逆日歩が益になる
                        l_dblInterestProfit =
                            Math.floor(l_targetContractDetail.getPayInterestFee() * l_dblRate + errorCorrection);
                        
                        //評価単価が存在する場合に未決済建玉評価損益を評価する
                        if(l_dblUnitPrice > 0)
                        {
                            //未決済建玉評価損益 = 評価代金 - 建玉代金
                            l_dblAssetProfitLoss = l_dblTmpEstimatedAmount - l_dblTmpContractAmount;
                        }
                    }
                    //売建の場合
                    else if(ContractTypeEnum.SHORT.equals(l_contractType))
                    {
                        //逆日歩が損になる
                        l_dblInterestLoss =
                            Math.floor(l_targetContractDetail.getPayInterestFee() * l_dblRate + errorCorrection);
                        
                        //貸株料を追加
                        l_dblInterestLoss =
                            l_dblInterestLoss
                                + Math.floor(l_targetContractDetail.getLoanEquityFee() * l_dblRate + errorCorrection);
                        
                        //順日歩が益になる
                        l_dblInterestProfit =
                            Math.floor(l_targetContractDetail.getInterestFee() * l_dblRate + errorCorrection);
                        
                        //評価単価が存在する場合に未決済建玉評価損益を評価する
                        if(l_dblUnitPrice > 0)
                        {
                            //未決済建玉評価損益 = 建玉代金 - 評価代金
                            l_dblAssetProfitLoss = l_dblTmpContractAmount - l_dblTmpEstimatedAmount;
                        }
                    }
                    
                    //未決済建玉評価損
                    double l_dblAssetLoss = 0.0;
                    //未決済建玉評価益
                    double l_dblAssetProfit = 0.0;
                    if(l_dblAssetProfitLoss < 0)
                    {
                        l_dblAssetLoss = Math.abs(Math.ceil(l_dblAssetProfitLoss));
                    }
                    else
                    {
                        l_dblAssetProfit = Math.floor(l_dblAssetProfitLoss);
                    }
                    
                    //建手数料をセット
                    l_sumOpenContract.setSetupFee(l_dblSetupFee);
                    
                    //その他建玉諸経費をセット
                    l_sumOpenContract.setOtherCost(l_dblOtherCost);
                    
                    //順日歩・逆日歩損をセット
                    l_sumOpenContract.setInterestLoss(l_dblInterestLoss);
                    
                    //順日歩・逆日歩益をセット
                    l_sumOpenContract.setInterestProfit(l_dblInterestProfit);
                    
                    //未決済建玉評価損をセット
                    l_sumOpenContract.setAssetLoss(l_dblAssetLoss);
                    
                    //未決済建玉評価益をセット
                    l_sumOpenContract.setAssetProfit(l_dblAssetProfit);
                }
            }
            
            //建玉代金
            double l_dblContractAmount = Math.floor(l_targetContractDetail.getContractPrice() * l_dblOpenContractQuantity);

            //必要保証金
            double l_dblMarginDeposit = Math.floor(l_targetContractDetail.getContractPrice()
                * l_dblOpenContractQuantity
                * l_targetContractDetail.getMarginDepositRate() / depositDenominator);
        
            //現金必要保証金
            double l_dblCashMarginDeposit = Math.floor(l_targetContractDetail.getContractPrice() 
                * l_dblOpenContractQuantity 
                * l_targetContractDetail.getCashMarginDepositRate() / depositDenominator);
            //約定済みの場合、純粋未決済フィールドにセット
            if(l_targetContract.isContractExecuted())
            {
                l_sumOpenContract.setContractAmount(l_dblContractAmount);
                l_sumOpenContract.setMarginDeposit(l_dblMarginDeposit);
                l_sumOpenContract.setCashMarginDeposit(l_dblCashMarginDeposit);
            }
            //未約定新規建の場合、発注分フィールドにセット
            else
            {
                l_sumOpenContract.setUnExecContractAmount(l_dblContractAmount);
                l_sumOpenContract.setUnExecMarginDeposit(l_dblMarginDeposit);
                l_sumOpenContract.setUnExecCashMarginDeposit(l_dblCashMarginDeposit);   
            }
        }

        return l_sumOpenContract;
    }
    
    /**
     * (get未受渡建玉の集計)<BR>
     * 引数で指定した日の未受渡建玉の集計をする。<BR>
     * <BR>
     * １）建玉変動一覧を取得する。<BR>
     * <BR>
     * ２）非日計り返済・現引現渡建玉を未受渡建玉として集計する。<BR>
     * 　@－集計項目：<BR>
     * 　@　@・決済益<BR>
     * 　@　@・決済損<BR>
     * 　@　@・建玉代金<BR>
     * 　@　@・必要保証金<BR>
     * 　@　@・現金必要保証金<BR>
     * <BR>
     * 　@－建玉代金／必要保証金／現金必要保証金／決済益／決済損の求め方<BR>
     * <BR>
     * 　@　@・建玉変動．is未受渡建玉<非日計り返済・現引現渡>（:String =　@「1：必要保証金」）)＝true<BR>
     * 　@　@　@且つ<BR>
     * 　@　@・建玉変動.is変動期間内(n)＝true　@の場合<BR>
     * 　@　@　@建玉代金(n)　@　@　@　@＝建玉代金(n)<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@＋　@建玉変動.建玉代金<BR>
     * 　@　@　@必要保証金(n)　@　@　@＝必要保証金(n)<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@＋　@建玉変動.必要保証金<BR>
     * 　@　@　@現金必要保証金(n)　@＝現金必要保証金(n)<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@＋　@建玉変動.現金必要保証金<BR>
     * 　@　@・建玉変動.is未受渡建玉<非日計り返済・現引現渡>（:String =　@「0：DEFAULT」）)＝true<BR>
     * 　@且つ<BR>
     * 　@　@・建玉変動.is変動期間内(n)＝true　@の場合<BR>
     * <BR>
     * 　@　@　@決済益(n)　@　@　@　@　@＝決済益(n)<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@＋　@建玉変動.決済益<BR>
     * 　@　@　@決済損(n)　@　@　@　@　@＝決済損(n)<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@＋　@建玉変動.決済損<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@＋　@建玉変動.建玉諸経費<BR>
     * <BR>
     * ３）日計り返済建玉を未受渡建玉として集計する。<BR>
     * 　@－集計項目：<BR>
     * 　@　@・決済益<BR>
     * 　@　@・決済損<BR>
     * <BR>
     * 　@－決済益／決済損の求め方<BR>
     * 　@　@・建玉変動.is日計り返済・現引現渡建玉（:String =　@「0：DEFAULT」）)＝true<BR>
     * 　@　@　@且つ<BR>
     * 　@　@・建玉変動.is変動期間内(n)＝true　@の場合<BR>
     * 　@　@　@決済益(n)　@　@　@　@　@＝決済益(n)<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@＋　@建玉変動.決済益<BR>
     * 　@　@　@決済損(n)　@　@　@　@　@＝決済損(n)<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@＋　@建玉変動.決済損<BR>
     * <BR>
     * ４）日計り返済建玉を未受渡建玉として集計する。<BR>
     * 　@－集計項目：<BR>
     * 　@　@・決済損<当日><BR>
     * 　@　@・決済益<当日><BR>
     * 　@　@・決済建玉前日価格評価<当日><BR>
     * 　@　@・決済損<指定日><BR>
     * 　@　@・決済益<指定日><BR>
     * <BR>
     * 　@－決済益<当日>／決済損<当日>／決済建玉前日価格評価<当日>の求め方<BR>
     * 　@　@・建玉変動.トランザクションカテゴリ＝返済<BR>
     * 　@　@　@且つ<BR>
     * 　@　@・指定日＝余力計算条件.営業日(T+0)<BR>
     * 　@　@　@且つ<BR>
     * 　@　@・建玉変動.トランザクション発生日＝余力計算条件.営業日(T+0)　@の場合<BR>
     * 　@　@　@決済損<当日>(n)　@　@＝決済損<当日>(n)<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@＋　@建玉変動.決済損<BR>
     * 　@　@　@決済益<当日>(n)　@　@＝決済益<当日>(n)<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@＋　@建玉変動.決済益<BR>
     * 　@　@　@・対象建玉詳細.建日＜余力計算条件.営業日(T+0)<BR>
     * 　@　@　@　@且つ<BR>
     * 　@　@　@・対象建玉詳細.前日終値＞0　@の場合<BR>
     * 　@　@　@　@・対象建玉詳細.建区分＝買建　@の場合<BR>
     * 　@　@　@　@　@決済建玉前日価格評価<当日>＝対象建玉詳細.前日終値　@×　@建玉変動.株数　@-　@対象建玉詳細.建単価　@×　@建玉変動.株数<BR>
     * 　@　@　@　@・対象建玉詳細.建区分＝売建　@の場合<BR>
     * 　@　@　@　@　@決済建玉前日価格評価<当日>＝対象建玉詳細.建単価　@×　@建玉変動.株数  -　@対象建玉詳細.前日終値　@×　@建玉変動.株数<BR>
     * <BR>
     * 　@－決済益<指定日>／決済損<指定日>の求め方<BR>
     * 　@　@・建玉変動.トランザクションカテゴリ＝返済<BR>
     * 　@　@　@且つ<BR>
     * 　@　@・指定日＝建玉変動.受渡日　@の場合<BR>
     * 　@　@　@決済損<指定日>(n)　@　@＝決済損<指定日>(n)<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@＋　@建玉変動.決済損<BR>
     * 　@　@　@決済益<指定日>(n)　@　@＝決済益<指定日>(n)<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@＋　@建玉変動.決済益<BR>
     * ５）未受渡建玉の集計結果を返す。<BR>
     * <BR>
     * ※nは、引数の指定日。<BR>
     * ※計算式に使用する各値の取得方法@<BR>
     * ・建玉変動.建玉代金　@　@　@　@　@　@　@　@・・・建玉変動.calc建玉代金()<BR>
     * ・建玉変動.必要保証金　@　@　@　@　@　@　@・・・建玉変動.calc必要保証金(保証金率)<BR>
     * ・建玉変動.現金必要保証金　@　@　@　@　@・・・建玉変動.calc現金必要保証金(現金保証金率)<BR>
     * ・建玉変動.トランザクション発生日　@・・・建玉変動.getトランザクション発生日()<BR>
     * ・建玉変動.株数　@　@　@　@　@　@　@　@　@　@・・・建玉変動.get株数()<BR>
     * ・建玉変動.受渡日　@　@　@　@　@　@　@　@　@・・・建玉変動.get受渡日()<BR>
     * ・建玉変動.決済益　@　@　@　@　@　@　@　@　@・・・建玉変動.calc決済益()<BR>
     * ・建玉変動.決済損　@　@　@　@　@　@　@　@　@・・・建玉変動.calc決済損()<BR>
     * ・建玉変動.建玉諸経費　@　@　@　@　@　@　@・・・建玉変動.get建玉諸経費()<BR>
     * ・建単価　@　@　@　@　@　@　@　@ 　@　@　@　@　@・・・対象建玉詳細.get建単価()<BR>
     * ・保証金率　@　@　@　@　@　@　@　@ 　@　@　@　@・・・対象建玉詳細.get保証金率()<BR>
     * ・現金保証金率　@　@　@　@　@　@ 　@　@　@　@・・・対象建玉詳細.get現金保証金率()<BR>
     * ・前日終値　@　@　@　@　@　@ 　@　@　@　@　@　@・・・対象建玉詳細.get前日終値()<BR>
     * ・余力計算条件.営業日(T+0)　@　@　@　@ ・・・余力計算条件.get営業日(T+0)<BR>
     * @@param l_datDate - (指定日)
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPSummaryUndeliveredContract
     * @@roseuid 40EB66BC01A7
     */
    public WEB3TPSummaryUndeliveredContract getSummaryUndeliveredContract(Date l_datDate) 
    {
        //create未受渡建玉の集計      
        WEB3TPSummaryUndeliveredContract l_sumUndeliveredContract = WEB3TPSummaryUndeliveredContract.create();

        //対象建玉を取得
        WEB3TPTargetContract l_targetContract = getTargetContract();

        //対象建玉詳細を取得
        WEB3TPTargetContractDetail l_targetContractDetail = l_targetContract.getTargetContractDetail();

        //余力計算条件を取得
        WEB3TPCalcCondition l_calcCondition = getContractInfo().getCalcCondition();
    
        //営業日(T+0)を取得
        Date l_datBizDate0 = l_calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_0);

        //建玉変動一覧を取得
        List l_histories = getHistories();
        
        //建玉変動一覧のサイズでループ
        int l_intHistorySize = l_histories.size();
        for(int i = 0; i < l_intHistorySize; i++)
        {
            //建玉変動を取得
            WEB3TPHistory l_history = (WEB3TPHistory)l_histories.get(i);

            //非日計り返済・現引現渡の場合
            if (l_history.isUndeliveredContractNotDayTradeSwap(WEB3TPRestraintDivDef.MARGIN_DEPOSIT))
            {
                //変動期間内の場合
                if(l_history.isDuringReflectTime(l_datDate))
                {
                    if(DBG)
                    {
                        log.debug("WEB3TPSummaryUndeliveredContract summed up" + "[" + i + "]:" + l_history.toString());
                    }

                    //建玉代金
                    double l_dblContractAmount = l_sumUndeliveredContract.getContractAmount() + Math.floor(l_history.calcContractAmount());
                    l_sumUndeliveredContract.setContractAmount(l_dblContractAmount);
                                        
                    //必要保証金
                    double l_dblMarginDeposit = l_sumUndeliveredContract.getMarginDeposit() + Math.floor(l_history.calcMarginDeposit(l_targetContractDetail.getMarginDepositRate()));
                    l_sumUndeliveredContract.setMarginDeposit(l_dblMarginDeposit);
                    
                    //現金必要保証金
                    double l_dblCashMarginDeposit = l_sumUndeliveredContract.getCashMarginDeposit() + Math.floor(l_history.calcCashMarginDeposit(l_targetContractDetail.getCashMarginDepositRate()));
                    l_sumUndeliveredContract.setCashMarginDeposit(l_dblCashMarginDeposit);
                }
            }
            if (l_history.isUndeliveredContractNotDayTradeSwap(WEB3TPRestraintDivDef.DEFAULT))
            {
                if (l_history.isDuringReflectTime(l_datDate))
                {
                    //決済益
                    double l_dblContractProfit = l_sumUndeliveredContract.getContractProfit() + Math.floor(l_history.calcContractProfit());
                    l_sumUndeliveredContract.setContractProfit(l_dblContractProfit);

                    //決済損
                    double l_dblContractLoss = l_sumUndeliveredContract.getContractLoss()
                        + Math.floor(l_history.calcContractLoss()) + Math.floor(l_history.getContractTotalCost());
                    l_sumUndeliveredContract.setContractLoss(l_dblContractLoss);
                }
            }
            //日計り返済の場合
            else if(l_history.isDayTradeSwap(WEB3TPRestraintDivDef.DEFAULT))
            {
                //変動期間内の場合
                if(l_history.isDuringReflectTime(l_datDate))
                {
                    if(DBG)
                    {
                        log.debug("WEB3TPSummaryUndeliveredContract Only Profit/Loss summed up" + "[" + i + "]:" + l_history.toString());
                    }

                    //決済益
                    double l_dblContractProfit = l_sumUndeliveredContract.getContractProfit() + Math.floor(l_history.calcContractProfit());
                    l_sumUndeliveredContract.setContractProfit(l_dblContractProfit);

                    //決済損
                    double l_dblContractLoss = l_sumUndeliveredContract.getContractLoss() + Math.floor(l_history.calcContractLoss());
                    l_sumUndeliveredContract.setContractLoss(l_dblContractLoss);
                }
            }
            
            //決済建玉の<当日>の決済損益を求める
            //返済の場合
            if(FinTransactionCateg.EQTYPE_CLOSE_MARGIN.equals(l_history.getTransactionCateg()))
            {
                //引数.指定日=返済日=営業日(T+0)の場合のみ、決済損益<当日>を求める
                if(WEB3DateUtility.compareToDay(l_datBizDate0, l_datDate) == 0)
                {
                    if(WEB3DateUtility.compareToDay(l_datBizDate0, l_history.getTransactionDate()) == 0)
                    {
                        //決済損<当日>
                        double l_dblTodayRepayContractLoss = 
                            l_sumUndeliveredContract.getTodayRepayContractLoss() + Math.floor(l_history.calcContractLoss());
                        l_sumUndeliveredContract.setTodayRepayContractLoss(l_dblTodayRepayContractLoss);

                        //決済益<当日>
                        double l_dblTodayRepayContractProfit = 
                            l_sumUndeliveredContract.getTodayRepayContractProfit() + Math.floor(l_history.calcContractProfit());
                        l_sumUndeliveredContract.setTodayRepayContractProfit(l_dblTodayRepayContractProfit);
                        
                        //営業日(T+0)よりも前に建てた建玉で、前日終値がある場合に、決済建玉の前日価格評価<当日>を求める
                        if(WEB3DateUtility.compareToDay(l_targetContractDetail.getOpenDate(), l_datBizDate0) < 0)
                        {
                            if(l_targetContractDetail.getLastClosingPrice() > 0)
                            {
                                //建区分を取得
                                ContractTypeEnum l_contractType = l_targetContractDetail.getContractType();
                                
                                //建単価を取得
                                double l_dblContractPrice = l_targetContractDetail.getContractPrice();
                     
                                //前日終値を取得
                                double l_dblLastClosingPrice = l_targetContractDetail.getLastClosingPrice();
                                
                                //決済株数を取得
                                double l_dblRepayQuantity = l_history.getQuantity();
                                
                                //決済建玉代金
                                double l_dblTmpContractAmount = Math.floor(l_dblContractPrice * l_dblRepayQuantity);
                                
                                //決済建玉の前日価格評価代金
                                double l_dblTmpEstimatedAmount = Math.floor(l_dblLastClosingPrice * l_dblRepayQuantity);
                                
                                //決済建玉の前日価格評価<当日>
                                double l_dblTodayRepayContractPrevAsset = 0.0;
                                
                                //買建の場合
                                if(ContractTypeEnum.LONG.equals(l_contractType))
                                {
                                    //決済建玉前日価格評価<当日> = 前日評価代金 - 決済建玉代金
                                    l_dblTodayRepayContractPrevAsset = l_dblTmpEstimatedAmount - l_dblTmpContractAmount;
                                }
                                //売建の場合
                                else if(ContractTypeEnum.SHORT.equals(l_contractType))
                                {
                                    //決済建玉前日価格評価<当日> = 決済建玉代金 - 前日評価代金
                                    l_dblTodayRepayContractPrevAsset = l_dblTmpContractAmount - l_dblTmpEstimatedAmount;
                                }
                                if(l_dblTodayRepayContractPrevAsset < 0)
                                {
                                    l_dblTodayRepayContractPrevAsset = Math.ceil(l_dblTodayRepayContractPrevAsset);
                                }
                                else
                                {
                                    l_dblTodayRepayContractPrevAsset = Math.floor(l_dblTodayRepayContractPrevAsset);
                                }
                                l_dblTodayRepayContractPrevAsset += l_sumUndeliveredContract.getTodayRepayContractPrevAsset();
                                l_sumUndeliveredContract.setTodayRepayContractPrevAsset(l_dblTodayRepayContractPrevAsset);
                            }
                        }
                    }
                }
                
                //決済建玉の<指定日>の決済損益を求める
                //指定日=受渡日の場合のみ、決済損益<指定日>を求める
                if(WEB3DateUtility.compareToDay(l_history.getDeliveryDate(), l_datDate) == 0)
                {
                    //決済損<指定日>
                    double l_dblDesignateDateContractLoss = 
                        l_sumUndeliveredContract.getDesignateDateContractLoss() + Math.floor(l_history.calcContractLoss());
                    l_sumUndeliveredContract.setDesignateDateContractLoss(l_dblDesignateDateContractLoss);

                    //決済益<指定日>
                    double l_dblDesignateDateContractProfit = 
                        l_sumUndeliveredContract.getDesignateDateContractProfit() + Math.floor(l_history.calcContractProfit());
                    l_sumUndeliveredContract.setDesignateDateContractProfit(l_dblDesignateDateContractProfit);
                }
            }
        }
        
        return l_sumUndeliveredContract;
    }
    
    /**
     * (get日計り返済・現引現渡建玉の集計) <BR>
     * <BR>
     * 引数.指定日の日計り返済・現引現渡建玉を集計する。 <BR>
     * <BR>
     * 1)日計り返済・現引現渡建玉の集計オブジェクトを作成する。<BR> 
     * 　@-日計り返済・現引現渡建玉の集計.create日計り返済・現引現渡建玉の集計()をコール <BR>
     * <BR>
     * 2)対象建玉オブジェクトを取得する。 <BR>
     * 　@-this.get対象建玉()をコール <BR>
     * <BR>
     * 3)取得した対象建玉オブジェクトより、対象建玉詳細オブジェクトを取得する。 <BR>
     * 　@-対象建玉.get建玉変動()をコール <BR>
     * <BR>
     * 4)建玉変動オブジェクトのリストを取得する。 <BR>
     * 　@-this.get建玉変動()をコール <BR>
     * <BR>
     * 5)日計り返済・現引現渡建玉の集計をする。 <BR>
     * <BR>
     * 　@＜取得した建玉変動オブジェクトのリストの要素数回LOOP処理＞<BR>
     * <BR>
     * 　@　@　@[引数.指定日が変動期間内の場合]<BR>
     * 　@　@　@(建玉変動.is変動期間中(n) = true)<BR>
     * <BR>
     * 　@　@　@①@．[( 建玉変動.is日計り返済・現引現渡建玉（:String =　@「1：必要保証金」））= trueの場合]<BR>
     * 　@　@　@　@建玉代金／必要保証金／現金必要保証金を集計する。<BR>
     * 　@　@　@　@－建玉代金 = 建玉代金 + 建玉変動.calc建玉代金()<BR>
     * 　@　@　@　@－必要保証金 = 必要保証金 + 建玉変動.calc必要保証金()<BR>
     * 　@　@　@　@－現金必要保証金 = 現金必要保証金 + 建玉変動.calc現金必要保証金()<BR>
     * <BR>
     * 　@　@　@②．[現引・現渡である場合]<BR>
     * 　@　@　@　@(建玉変動.getトランザクションカテゴリ = 60：現引・現渡取引 )<BR>
     * <BR>
     * 　@　@　@　@[(建玉変動.is日計り返済・現引現渡建玉（:String =「2：評価損益」)　@= true]の場合<BR>
     * <BR>
     * 　@　@　@　@　@－評価損益を計算する。<BR>
     * 　@　@　@　@　@　@[現引の場合]<BR>
     * 　@　@　@　@　@　@(対象建玉詳細.get建区分() = 1：買建)<BR>
     * 　@　@　@　@　@　@　@評価損益 = (建単価 - 評価単価) × 建玉変動.get株数()<BR>
     * <BR>
     * 　@　@　@　@　@　@[現渡の場合]<BR>
     * 　@　@　@　@　@　@(対象建玉詳細.get建区分() = 2：売建)<BR>
     * 　@　@　@　@　@　@　@評価損益 = (評価単価 - 建単価) × 数量<BR>
     * <BR>
     * 　@　@　@　@　@　@※数量 = 建玉変動.get株数()<BR>
     * 　@　@　@　@　@　@※建単価 = 対象建玉詳細.get建単価()<BR>
     * 　@　@　@　@　@　@※評価単価 = 対象建玉詳細.get評価単価()<BR>
     * 　@　@　@　@　@　@※評価単価が0の場合、評価しない（評価損益 = 0とする）<BR>
     * <BR>
     * 　@　@　@　@　@－現引現渡建玉評価損／現引現渡建玉評価益を計算する。<BR>
     * <BR>
     * 　@　@　@　@　@　@[評価損の場合]<BR>
     * 　@　@　@　@　@　@計算した評価損益 < 0)<BR>
     * 　@　@　@　@　@　@　@現引現渡建玉評価損 = ABS(評価損益)<BR>
     * <BR>
     * 　@　@　@　@　@　@[評価益の場合]<BR>
     * 　@　@　@　@　@　@(以外の場合)<BR>
     * 　@　@　@　@　@　@　@現引現渡建玉評価益 = 評価損益<BR>
     * <BR>
     * 　@　@　@　@　@－現引現渡建玉決済損を計算する。<BR>
     * 　@　@　@　@　@　@　@現引現渡建玉決済損 = 現引現渡建玉決済損 + 建玉変動.get建玉諸経費()<BR> 
     * <BR>
     * 6)日計り返済・現引現渡建玉の集計オブジェクトに値をセットし返却する。 <BR>
     * <BR>
     * 　@－日計り返済・現引現渡建玉の集計.建玉代金 = 5)で集計された建玉代金 <BR>
     * 　@－日計り返済・現引現渡建玉の集計.必要保証金 = 5)で集計された必要保証金 <BR>
     * 　@－日計り返済・現引現渡建玉の集計.現金必要保証金 = 5)で集計された現金必要保証金 <BR>
     * 　@－日計り返済・現引現渡建玉の集計.現引現渡建玉評価損 = 5)で計算された現引現渡建玉評価損 <BR>
     * 　@－日計り返済・現引現渡建玉の集計.現引現渡建玉評価益 = 5)で計算された現引現渡建玉評価益 <BR>
     * 　@－日計り返済・現引現渡建玉の集計.現引現渡建玉決済損 = 5)で計算された現引現渡建玉決済損<BR>
     * 
     * @@param l_datDate - (指定日)
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPSummaryDayTradeSwapContract
     */
    public WEB3TPSummaryDayTradeSwapContract getSummaryDayTradeSwapContract(Date l_datDate) 
    {
        //create日計り返済・現引現渡建玉の集計      
        WEB3TPSummaryDayTradeSwapContract l_sumDayTradeSwapContract = WEB3TPSummaryDayTradeSwapContract.create();

        //対象建玉を取得
        WEB3TPTargetContract l_targetContract = getTargetContract();

        //対象建玉詳細を取得
        WEB3TPTargetContractDetail l_targetContractDetail = l_targetContract.getTargetContractDetail();

        //建玉変動一覧を取得
        List l_histories = getHistories();
        
        //建玉変動一覧のサイズでループ
        int l_intHistorySize = l_histories.size();
        for(int i = 0; i < l_intHistorySize; i++)
        {
            //建玉変動を取得
            WEB3TPHistory l_history = (WEB3TPHistory)l_histories.get(i);

            //変動期間内の場合
            if (l_history.isDuringReflectTime(l_datDate))
            {
                //①@．[( 建玉変動.is日計り返済・現引現渡建玉（:String =　@「1：必要保証金」））= trueの場合]
                if (l_history.isDayTradeSwap(WEB3TPRestraintDivDef.MARGIN_DEPOSIT))
                {
                    if(DBG)
                    {
                        log.debug("WEB3TPSummaryDayTradeSwapContract summed up" + "[" + i + "]:" + l_history.toString());
                    }

                    //建玉代金
                    double l_dblContractAmount = l_sumDayTradeSwapContract.getContractAmount() + Math.floor(l_history.calcContractAmount());
                    l_sumDayTradeSwapContract.setContractAmount(l_dblContractAmount);
                                        
                    //必要保証金
                    double l_dblMarginDeposit = l_sumDayTradeSwapContract.getMarginDeposit() + Math.floor(l_history.calcMarginDeposit(l_targetContractDetail.getMarginDepositRate()));
                    l_sumDayTradeSwapContract.setMarginDeposit(l_dblMarginDeposit);
                    
                    //現金必要保証金
                    double l_dblCashMarginDeposit = l_sumDayTradeSwapContract.getCashMarginDeposit() + Math.floor(l_history.calcCashMarginDeposit(l_targetContractDetail.getCashMarginDepositRate()));
                    l_sumDayTradeSwapContract.setCashMarginDeposit(l_dblCashMarginDeposit);
                }
                //②．[現引・現渡である場合]
                if (FinTransactionCateg.EQTYPE_SWAP_MARGIN.equals(l_history.getTransactionCateg()))
                {
                    //[(建玉変動.is日計り返済・現引現渡建玉（:String =「2：評価損益」)　@= true]の場合
                    if (l_history.isDayTradeSwap(WEB3TPRestraintDivDef.PROFITLOSS))
                    {
                        //建単価
                        double l_dblContractPrice = l_targetContractDetail.getContractPrice();
                        //評価単価
                        double l_dblUnitPrice = l_targetContractDetail.getUnitPrice();
                        
                        //建玉代金
                        double l_dblTmpContractAmount = Math.floor(l_dblContractPrice * l_history.getQuantity());
                        //評価代金
                        double l_dblTmpEstimatedAmount = Math.floor(l_dblUnitPrice * l_history.getQuantity());
                        //現引現渡建玉評価損益
                        double l_dblSwapProfitLoss = 0.0;

                        //建区分を取得
                        ContractTypeEnum l_contractType = l_targetContractDetail.getContractType();

                        //買建の場合
                        if(ContractTypeEnum.LONG.equals(l_contractType))
                        {
                            //評価単価が存在する場合に現引現渡建玉評価損益を評価する
                            if(l_dblUnitPrice > 0)
                            {
                                //現引現渡建玉評価損益 = 評価代金 - 建玉代金
                                l_dblSwapProfitLoss = l_dblTmpEstimatedAmount - l_dblTmpContractAmount;
                            }
                        }
                        //売建の場合
                        else if(ContractTypeEnum.SHORT.equals(l_contractType))
                        {
                            //評価単価が存在する場合に現引現渡建玉評価損益を評価する
                            if(l_dblUnitPrice > 0)
                            {
                                //現引現渡建玉評価損益 = 建玉代金 - 評価代金
                                l_dblSwapProfitLoss = l_dblTmpContractAmount - l_dblTmpEstimatedAmount;
                            }
                        }
                        
                        //現引現渡建玉評価損
                        double l_dblSwapLoss = 0.0;
                        //現引現渡建玉評価益
                        double l_dblSwapProfit = 0.0;
                        if(l_dblSwapProfitLoss < 0)
                        {
                            l_dblSwapLoss = Math.abs(Math.ceil(l_dblSwapProfitLoss));
                        }
                        else
                        {
                            l_dblSwapProfit = Math.floor(l_dblSwapProfitLoss);
                        }

                        //現引現渡建玉評価損をセット
                        double l_dblSwapLossTemp = l_sumDayTradeSwapContract.getSwapContractAssetLoss() + l_dblSwapLoss;
                        l_sumDayTradeSwapContract.setSwapContractAssetLoss(l_dblSwapLossTemp);
                        //現引現渡建玉評価益をセット
                        double l_dblSwapProfitTemp = l_sumDayTradeSwapContract.getSwapContractAssetProfit() + l_dblSwapProfit;
                        l_sumDayTradeSwapContract.setSwapContractAssetProfit(l_dblSwapProfitTemp);     
                    }

                    //[(建玉変動.is日計り返済・現引現渡建玉（:String =「0：DEFAULT」) = true)]の場合
                    if (l_history.isDayTradeSwap(WEB3TPRestraintDivDef.DEFAULT))
                    {
                        //現引現渡建玉決済損をセット
                        BigDecimal l_bdSwapContractSettleLoss = new BigDecimal(Double.toString(
                            l_sumDayTradeSwapContract.getSwapContractSettleLoss()));
                        BigDecimal l_bdContractTotalCost = new BigDecimal(Double.toString(l_history.getContractTotalCost()));

                        double l_dblContractTotalCost =
                            l_bdSwapContractSettleLoss.add(l_bdContractTotalCost).doubleValue();
                        l_sumDayTradeSwapContract.setSwapContractSettleLoss(l_dblContractTotalCost);
                    }
                }
            }
        }
        return l_sumDayTradeSwapContract;
    }
    
    /**
     * (add建玉変動)<BR>
     * 引数の建玉変動を建玉変動一覧に追加する。<BR>
     * @@param l_history - (建玉変動)
     * @@roseuid 40BAFC3E008C
     */
    public void addHistory(WEB3TPHistory l_history) 
    {
        List l_histories = getHistories();
        l_histories.add(l_history);
    }

    /**
     * このオブジェクトの文字列表現を返す。
     */
    public String toString()
    {
        ToStringBuilder l_builder = ToStringUtils.newToStringBuilder(this);
        
        //対象建玉
        l_builder.append("targetContract", getTargetContract());
        
        //建玉変動
        int l_intSize = histories.size();
        for(int i = 0; i < l_intSize; i++)
        {
            l_builder.append("histories" + "[" + i + "]",histories.get(i));
        }
        return l_builder.toString();
    }

    /**
     * (get当日返済建玉株数の集計) <BR>
     * <BR>
     * 集計した当日返済建玉株数を返却する。<BR>
     * （戻り値の型：double）<BR>
     * <BR>
     * １）　@T+0の日付を取得する。<BR>
     * ［取得方法@］<BR>
     * 　@this.get建玉情報().get余力計算条件().get営業日(T+0)<BR>
     * <BR>
     * ２）　@建玉変動の一覧を取得し、判定を行う。<BR>
     * ［取得方法@］<BR>
     * 　@this.get建玉変動()<BR>
     * 　@※ this.get建玉変動()　@==　@null<BR>
     * 　@又は this.get建玉変動().size()　@==　@0 の場合、0を返却する。<BR>
     * <BR>
     * ３）　@取得した建玉変動一覧の要素数分LOOPし、以下の処理を行う。<BR>
     * <BR>
     * （1） 建玉変動一覧より、建玉変動オブジェクトを取得する。<BR>
     * <BR>
     * （2） 建玉変動オブジェクトが以下の条件を満たす場合、（3）以降の処理を行う。<BR>
     * ［抽出条件］<BR>
     *　@・建玉変動.getトランザクションカテゴリ()　@IN<BR>
     *　@　@（"40" (返済取引),　@"60" (現引・現渡取引)）AND<BR>
     *　@・建玉変動.getトランザクション発生日()　@==　@１）で取得した日付<BR>
     * <BR>
     * （3） 株数を取得する。<BR>
     * ［取得方法@］
     * 　@建玉変動.get株数() <BR>
     * <BR>
     * （4） 株数を集計する。<BR>
     * ［計算式］<BR>
     * 　@集計結果　@=　@集計結果　@＋　@(3)で取得した株数<BR>
     * <BR>
     * ４）　@株数の集計結果を返却する。<BR>
     * <BR>
     * @@return double
     */
    public double getSummaryTodayRepayContractQuantity()
    {
        final String STR_METHOD_NAME = "getSummaryTodayRepayContractQuantity()";
        log.entering(STR_METHOD_NAME);

        //T+0の日付を取得する。
        Date l_datBizDate0 = this.getContractInfo().getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0);

        //建玉変動の一覧を取得し、判定を行う。
        List l_lisHistories = this.getHistories();

        //株数の集計
        double l_dblSumQuantity = 0;
        BigDecimal l_bdSumQuantity = new BigDecimal(Double.toString(l_dblSumQuantity));

        //※ this.get建玉変動()　@==　@null　@又は this.get建玉変動().size()　@==　@0 の場合、0を返却する。
        if (l_lisHistories == null || l_lisHistories.size() == 0)
        {
            log.debug(" 集計結果 = " + l_dblSumQuantity);
            log.exiting(STR_METHOD_NAME);
            return l_dblSumQuantity;
        }

        int l_intSize = l_lisHistories.size();

        for (int i = 0; i < l_intSize; i++)
        {
            //建玉変動一覧より、建玉変動オブジェクトを取得する。
            WEB3TPHistory l_history = (WEB3TPHistory)l_lisHistories.get(i);

            FinTransactionCateg l_transactionCateg = l_history.getTransactionCateg();

            Date l_datTransactionDate = l_history.getTransactionDate();

            //建玉変動オブジェクトが以下の条件を満たす場合、以降の処理を行う。
            //建玉変動.getトランザクションカテゴリ()　@IN（"40" (返済取引),　@"60" (現引・現渡取引)）
            //AND  建玉変動.getトランザクション発生日()　@==　@１）で取得した日付
            if ((FinTransactionCateg.EQTYPE_CLOSE_MARGIN.equals(l_transactionCateg)
                || FinTransactionCateg.EQTYPE_SWAP_MARGIN.equals(l_transactionCateg))
                && WEB3DateUtility.compareToDay(l_datTransactionDate, l_datBizDate0) == 0)
            {
                //株数を取得する。
                double l_dblQuantity = l_history.getQuantity();

                BigDecimal l_bdQuantity = new BigDecimal(Double.toString(l_dblQuantity));

                //集計結果　@=　@集計結果 + 建玉変動.get株数()
                l_bdSumQuantity = l_bdSumQuantity.add(l_bdQuantity);
            }
        }

        //株数の集計結果を返却する
        log.debug(" 集計結果 = " + l_bdSumQuantity.doubleValue());
        log.exiting(STR_METHOD_NAME);
        return l_bdSumQuantity.doubleValue();
    }
}
@
