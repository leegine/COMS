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
filename	WEB3BondRecruitAcceptDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 応募引受け区分(WEB3BondRecruitAcceptDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/08/13 馮海濤 (中訊) 新規作成 仕様変更・モデルNo.260
*/
package webbroker3.bd.define;

/**
 * (応募引受け区分)<BR>
 * 応募引受け区分<BR>
 * <BR>
 * @@author 馮海濤
 * @@version 1.0
 */
public interface WEB3BondRecruitAcceptDivDef
{

    /**
     * 01：引受け
     */
    public static final String ACCEPT = "01";

    /**
     * 02：非引受け
     */
    public static final String UNACCEPT = "02";
}
@
