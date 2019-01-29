head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.27.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoTargetPeriodCheckDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 対象期間チェック 定数定義インタフェイス(WEB3AccInfoTargetPeriodCheckDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/07 栄イ (中訊) 新規作成
*/
package webbroker3.accountinfo.define;

public interface WEB3AccInfoTargetPeriodCheckDef
{
    /**
     * 0：　@警告なし
     */
    public final static String NO_WARNING = "0";
    
    /**
     * 1：　@警告有
     */
    public final static String WARNING = "1";
}
@
