head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeProspectusResult.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 目論見書閲覧チェック結果クラス(WEB3GentradeProspectusResult)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/15 仲川(ＳＲＡ) 新規作成
*/
package webbroker3.gentrade.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * 目論見書閲覧チェック結果クラス
 */
public class WEB3GentradeProspectusResult extends Message 
{
   
   /**
    * チェック結果<br />
    * <br />
    * 0： 閲覧済<br />
    * 1： 閲覧未済<br />
    * 2： 閲覧未済（障害中）<br />
    * @@see WEB3GentradeBatoCheckResultDef
    */
   public String checkResult;
   
   /**
    * チェック結果が”閲覧未済”の場合、目論見書表示の際に使用するURL
    */
   public String url;
   
   /**
    * ハッシュ値
    */
   public String hashValue;
   
   /**
    * コンストラクタ
    * @@roseuid 4211C29A0122
    */
   public WEB3GentradeProspectusResult() 
   {
   }
}
@
