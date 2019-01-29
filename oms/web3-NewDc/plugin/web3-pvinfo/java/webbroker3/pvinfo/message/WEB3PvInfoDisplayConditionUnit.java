head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.04.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoDisplayConditionUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 表示条件情報(WEB3PvInfoDisplayConditionUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
Revesion History : 2006/05/22 凌建平(中訊) 仕様変更管理台帳・モデルNo.063
*/
package webbroker3.pvinfo.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (表示条件情報)<BR>
 * 表示条件情報クラス<BR>
 * @@author 王亞洲
 * @@version 1.00
 */
public class WEB3PvInfoDisplayConditionUnit extends Message 
{
    /**
     * (表示条件番号)<BR>
     * 表示条件番号<BR>
     * <BR>
     * 0000：　@ダイレクト指定<BR>
     * 1001：　@入金請求発生&信用口座開設<BR>
     * 1002：　@入金請求発生&信用口座未開設<BR>
     * 1003：　@立替金発生<BR>
     * 1004：　@立替金実績<BR>
     * 1005：　@証拠金不足<BR>
     * 1006：　@決済期限間近（一ヶ月前）の建玉保有<BR>
     * 1007：　@決済期限間近（一週間前）の建玉保有<BR>
     * 1008：　@信用口座開設<BR>
     * 1009：　@信用口座未開設<BR>
     * 1010：　@オプション口座開設<BR>
     * 1011：　@株式保有<BR>
     * 1012：　@信用建玉保有<BR>
     * 1013：　@投信保有<BR>
     * 1014：　@累投保有<BR>
     * 1015：　@オプション建玉保有<BR>
     * 1016：　@ミニ株保有<BR>
     * 1017：　@先物保有<BR>
     * 1018：　@預り金有り&証券残無し<BR>
     * 1019：　@預り金無し&証券残無し<BR>
     * 1020：　@株式・信用注文発生（当日）<BR>
     * 1021：　@株式・信用注文発生（翌日）<BR>
     * 1022：　@株式・信用約定発生<BR>
     * 1023：　@全顧客<BR>
     * 1024：　@メールアドレス未登録<BR>
     * 1025：　@IPO当選<BR>
     * 1026：　@IPO繰上げ当選<BR>
     * 1027：　@取引停止<BR>
     * 1028：　@ログインパスワード変更要<BR>
     * 1029：　@外国証券口座開設<BR>
     * 1030： 外株保有<BR>
     * 1031： 外株注文発生（当日）<BR>
     * 1032： 外株注文発生（翌日）<BR>
     * 1033： 外株約定発生<BR>
     * 1041：　@20％割れ1日＆30％割れ5日以下<BR>
     * 1042：　@20％割れ1日＆30％割れ6日<BR>
     * 1043：　@20％割れ2日＆30％割れ6日以下<BR>
     * 1044：　@20％割れ3日以上<BR>
     * 1045：　@30％割れ2～4日<BR>
     * 1046：　@30％割れ5日<BR>
     * 1047：　@30％割れ6日<BR>
     * 1048：　@30％割れ7日以上<BR>
     */
    public String conditionNumber;
    
    /**
     * (表示名)<BR>
     * 表示名<BR>
     * <BR>
     * 0000：　@ダイレクト指定<BR>
     * 1001：　@入金請求発生&信用口座開設<BR>
     * 1002：　@入金請求発生&信用口座未開設<BR>
     * 1003：　@立替金発生<BR>
     * 1004：　@立替金実績<BR>
     * 1005：　@証拠金不足<BR>
     * 1006：　@決済期限間近（一ヶ月前）の建玉保有<BR>
     * 1007：　@決済期限間近（一週間前）の建玉保有<BR>
     * 1008：　@信用口座開設<BR>
     * 1009：　@信用口座未開設<BR>
     * 1010：　@オプション口座開設<BR>
     * 1011：　@株式保有<BR>
     * 1012：　@信用建玉保有<BR>
     * 1013：　@投信保有<BR>
     * 1014：　@累投保有<BR>
     * 1015：　@オプション建玉保有<BR>
     * 1016：　@ミニ株保有<BR>
     * 1017：　@先物保有<BR>
     * 1018：　@預り金有り&証券残無し<BR>
     * 1019：　@預り金無し&証券残無し<BR>
     * 1020：　@株式・信用注文発生（当日）<BR>
     * 1021：　@株式・信用注文発生（翌日）<BR>
     * 1022：　@株式・信用約定発生<BR>
     * 1023：　@全顧客<BR>
     * 1024：　@メールアドレス未登録<BR>
     * 1025：　@IPO当選<BR>
     * 1026：　@IPO繰上げ当選<BR>
     * 1027：　@取引停止<BR>
     */
    public String conditionName;
}
@
