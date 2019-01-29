head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.03.24.05.27.39;	author che-jin;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	3c84d8ad6447b3c;
filename	WEB3AcceptInfo.java;

1.1
date	2011.03.15.05.27.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3AcceptInfo.java;


desc
@@


1.2
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研ビジネス・イノベーション
File Name           : 顧客情報をxTradeクライアントに渡す為のメッセージクラス(WEB3AcceptInfo.java)
Author Name         : Daiwa Institute of Research Business Innovation
Revesion History    : 2004/04/28 菊地(SRA)
Revesion History    : 2006/08/24 栄イ (中訊)仕様変更・モデル030
Revesion History    : 2007/06/12 劉立峰 (中訊)仕様変更・モデル034
Revesion History    : 2007/08/30 周墨洋 (中訊)仕様変更・モデル044
Revesion History    : 2007/10/31 柴双紅 (中訊) 仕様変更・モデル048
Revesion History    : 2008/01/28 金シュ (中訊) 仕様変更・モデル050
Revesion History    : 2008/02/15 武波 (中訊) 仕様変更・モデル054
Revesion History    : 2009/03/12 車進 (中訊) 仕様変更・モデル057
Revesion History    : 2010/11/10 劉レイ (中訊) 仕様変更・モデル058
 */

package webbroker3.login.message;


import java.util.Date;

import webbroker3.common.define.WEB3EraBornDef;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (顧客情報)<BR>
 * 顧客情報をxTradeクライアントに渡す為のメッセージクラス<BR>
 * xTradeレスポンスに含められる様にxTrade.Messageを継承して<BR>
 * 作成するWOLF2側セッション保持用の顧客情報格納クラス。<BR>
 * <BR> 
 * @@author      菊地(SRA)
 * @@version     1.00
 */
public class WEB3AcceptInfo extends Message 
{
   /**
    * 西暦－明治元年
    */
   public static final int AD_MEIJI = 1867;
   
   /**
    * 西暦－大正元年
    */
   public static final int AD_TAISYO = 1911;
   
   /**
    * 西暦－昭和元年
    */
   public static final int AD_SYOWA = 1925;
   
   /**
    * 西暦－平成元年
    */
   public static final int AD_HEISEI = 1988;
   
   /**
    * (顧客コード)<BR>
    * 画面から入力された６桁の顧客コード。<BR>
    */
   public String acceptCode;
   
   /**
    * （CD付顧客コード）<BR>
    * CD付７桁の顧客コード。<BR>
    */
   public String acceptCodeCD;
   
   /**
    * (xTradeユーザ名)
    */
   public String xTradeUsername;
   
   /**
    * (口座ID)<BR>
    * xTrade側顧客マスタのPK。<BR>
    */
   public long accountID;
   
   /**
    * (名前（漢字）)<BR>
    * 顧客マスタ．名前（苗字）<BR>
    */
   public String nameKanji;
   
   /**
    * (名前（カナ）)<BR>
    * 顧客マスタ．名前（苗字1）<BR>
    */
   public String nameKana;
   
   /**
    * (性別)
    */
   public String sex;
   
   /**
    * (最終ログイン時刻)
    */
   public Date lastLoginTime;
   
   /**
    * (信用取引登録フラグ)<BR>
    * 登録済み=true／未登録=false<BR>
    */
   public boolean marginRegistFlag;
   
   /**
    * (先物OP取引登録)<BR>
    * 0：　@登録なし<BR>
    * 1：　@登録済み（OP買建取引） <BR>
    * 2：　@登録済み（先物取引） <BR>
    * 3：　@登録済み（先物／OP取引）<BR> 
    */
   public String futOpTradeRegist;
   
   /**
    * (累投登録フラグ)<BR>
    * 登録済み=true／未登録=false<BR>
    */
   public boolean ruitoRegistFlag;
   
   /**
    * (リアル時価取得フラグ)<BR>
    * リアル時価を取得できる場合=true／ディレイ時価の場合=false<BR>
    */
   public boolean quotoFlag;
   
   /**
    * (停止フラグ)<BR>
    * 取引停止顧客の場合=true／取引停止顧客以外=false<BR>
    */
   public boolean stopFlag;
   
   /**
    * (Ｙ客フラグ)<BR>
    * Y客の場合=true／Y客以外=false<BR>
    */
   public boolean yellowCustomerFlag;
   
   /**
    * (為替保証金口座開設区分)<BR>
    * 0：口座なし  1：口座開設  2：口座抹消<BR>
    */
   public String fxAccOpenDiv;
   
   /**
    * (外国株式連携口座開設区分)<BR>
    * 0：口座なし  1：口座開設  2：口座抹消<BR>
    */
   public String fstkCooperateAccOpenDiv;
   
   /**
    * (外国証券口座開設区分)<BR>
    * 0:口座なし　@1:口座開設<BR>
    */
   public String feAccOpenDiv;
   
   /**
    * (預り証券評価制区分)<BR>
    * 0:未実施　@1:実施<BR>
    */
   public String assetEvaluationDiv;

   /**
    * (ストックオプション口座開設区分)<BR>
    * 0:口座なし　@1:口座開設<BR>
    */
   public String stockOpAccOpenDiv;
   
   /**
    * (モバイル専用口座開設区分)<BR>
    * 0:口座なし　@1:口座開設<BR>
    */
   public String mobileAccOpenDiv;

   /**
    * (担保ローン口座開設区分)<BR>
    * 0:口座なし　@1:口座開設<BR>
    */
   public String securedLoanAccOpenDiv;

   /**
    * (書面交付管理情報)<BR>
    * 書面交付管理情報<BR>
    */
   public WEB3DocumentDeliverInfoUnit[] documentDeliverList;

   /**
    * SONAR扱者情報
    */
   public WEB3SonarOperatorInfo sonarOperatorInfo;

   /**
    * (PTS口座開設区分)<BR>
    * 0:口座なし　@1:口座開設<BR>
    */
   public String ptsAccOpenDiv;

   /**
    * (口座開設区分一覧)<BR>
    * 口座開設区分一覧<BR>
    */
   public WEB3AccOpenDivUnit[] accOpenDivList;

   /**
    * (電子交付申込チェックフラグ)<BR>
    * 0：未済み　@1：済み<BR>
    */
   public String edCheckFlag;

   /**
    * @@roseuid 4021A07F0167
    */
   public WEB3AcceptInfo() 
   {
    
   }
   
   /**
    * (calc生年月日（西暦）)<BR>
    * 引数：生年月日年号、生年月日（和暦）から生年月日（西暦）を計算する。<BR>
    * @@param l_eraBorn - 
    * @@param l_bornDate - 
    * @@return java.lang.String
    * @@roseuid 4016456E0370
    */
   public static String calcADBornDate(String l_eraBorn, String l_bornDate) 
   {
       if ( (l_eraBorn == null) || (l_bornDate == null) )
       {
           return null;
       }
       
       String l_strDateOfYear = l_bornDate.substring(0,2);
       int    l_dateOfYear    = Integer.parseInt(l_strDateOfYear);

       if(WEB3EraBornDef.MEIJI.equals(l_eraBorn))
       {
           l_dateOfYear = l_dateOfYear + AD_MEIJI;
       }
       else if(WEB3EraBornDef.TAISYO.equals(l_eraBorn))
       {
           l_dateOfYear = l_dateOfYear + AD_TAISYO;
       }
       else if(WEB3EraBornDef.SYOWA.equals(l_eraBorn))
       {
           l_dateOfYear = l_dateOfYear + AD_SYOWA;
       }
       else if(WEB3EraBornDef.HEISEI.equals(l_eraBorn))
       {
           l_dateOfYear = l_dateOfYear + AD_HEISEI;
       }
       else
       {
           l_dateOfYear = 0;
       }
       
       if ( l_dateOfYear == 0 )
       {
           l_strDateOfYear = "0000";
       }
       else
       {
           l_strDateOfYear = Integer.toString(l_dateOfYear);
       }

       return l_strDateOfYear + l_bornDate.substring(2);
   }
}
@


1.1
log
@*** empty log message ***
@
text
@d2 1
a2 1
Copyright           : (株)大和総研 証券ソリューションシステム第二部
d4 1
a4 1
Author Name         : Daiwa Institute of Research
d13 1
d205 6
@

