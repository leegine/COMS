head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondRecruitInvitationFormDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 応募勧誘形式(WEB3BondRecruitInvitationFormDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/08/13 馮海濤 (中訊) 新規作成 仕様変更・モデルNo.260
*/
package webbroker3.bd.define;

/**
 * (応募勧誘形式)<BR>
 * 応募勧誘形式<BR>
 * <BR>
 * @@author 馮海濤
 * @@version 1.0
 */
public interface WEB3BondRecruitInvitationFormDef
{

    /**
     * 01：募集
     */
    public static final String RECRUIT = "01";

    /**
     * 02：売出し
     */
    public static final String SELL = "02";

    /**
     * 03：私募
     */
    public static final String PRIVATE_RECRUIT = "03";

    /**
     * 04：募集売出し
     */
    public static final String RECRUIT_SELL = "04";
}
@
