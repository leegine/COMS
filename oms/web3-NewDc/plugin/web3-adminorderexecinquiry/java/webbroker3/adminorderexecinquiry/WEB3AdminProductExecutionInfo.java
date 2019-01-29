head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.47.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminProductExecutionInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 商品実施情報(WEB3ProductExecutionInfo.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.adminorderexecinquiry;

/**
 * (商品実施情報)<BR>
 * <BR>
 * 商品の実施情報を格納するクラス<BR>
 * <BR>
 * WEB3AdminProductExecutionInfo<BR>
 * <BR>
 * It is a class to store execution information of a product<BR>
 * <BR>
 * @@author Amarnath
 * @@version 1.0
 */
public class WEB3AdminProductExecutionInfo
{
    /**
     * (制度信用実施フラグ)<BR>
     * <BR>
     * 制度信用を実施しているかどうかのフラグ<BR>
     * <BR>
     * false：　@未実施<BR>
     * true：　@実施<BR>
     * <BR>
     * ----<English>----------------------<BR>
     * <BR>
     * marginSysFlag<BR>
     * <BR>
     * Flag to see if marginSys is executed<BR>
     * <BR>
     * false: FALSE<BR>
     * true: TRUE<BR>
     * <BR>
     */
    public boolean marginSysFlag = false;

    /**
     * (一般信用実施フラグ)<BR>
     * <BR>
     * 一般信用を実施しているかどうかのフラグ<BR>
     * <BR>
     * false：　@未実施<BR>
     * true：　@実施<BR>
     * <BR>
     * ----<English>----------------------<BR>
     * <BR>
     * marginGenFlag<BR>
     * <BR>
     * Flag to see if marginGen is executed<BR>
     * <BR>
     * false: FALSE<BR>
     * true: TRUE<BR>
     * <BR>
     */
    public boolean marginGenFlag = false;

    /**
     * (ミニ株実施フラグ)<BR>
     * <BR>
     * ミニ株を実施しているかどうかのフラグ<BR>
     * <BR>
     * false：　@未実施<BR>
     * true：　@実施<BR>
     * <BR>
     * ----<English>----------------------<BR>
     * <BR>
     * miniFlag<BR>
     * <BR>
     * Flag to see if mini stock is executed<BR>
     * <BR>
     * false: FALSE<BR>
     * true: TRUE<BR>
     * <BR>
     */
    public boolean miniFlag = false;

    /**
     * (オプション実施フラグ)<BR>
     * <BR>
     * オプションを実施しているかどうかのフラグ<BR>
     * <BR>
     * false：　@未実施<BR>
     * true：　@実施<BR>
     * <BR>
     * ----<English>----------------------<BR>
     * <BR>
     * optionFlag<BR>
     * <BR>
     * Flag to see if option is executed<BR>
     * <BR>
     * false: FALSE<BR>
     * true: TRUE<BR>
     * <BR>
     */
    public boolean optionFlag = false;

    /**
     * (先物実施フラグ)<BR>
     * <BR>
     * 先物を実施しているかどうかのフラグ<BR>
     * <BR>
     * false：　@未実施<BR>
     * true：　@実施<BR>
     * <BR>
     * ----<English>----------------------<BR>
     * <BR>
     * futureFlag<BR>
     * <BR>
     * Flag to see if future is executed<BR>
     * <BR>
     * false: FALSE<BR>
     * true: TRUE<BR>
     * <BR>
     */
    public boolean futureFlag = false;

    /**
     * (投信実施フラグ)<BR>
     * <BR>
     * 投信を実施しているかどうかのフラグ<BR>
     * <BR>
     * false：　@未実施<BR>
     * true：　@実施<BR>
     * <BR>
     * ----<English>----------------------<BR>
     * <BR>
     * mutualFlag<BR>
     * <BR>
     * Flag to see if mutual is executed<BR>
     * <BR>
     * false: FALSE<BR>
     * true: TRUE<BR>
     * <BR>
     */
    public boolean mutualFlag = false;

    /**
     * (累投実施フラグ)<BR>
     * <BR>
     * 累投を実施しているかどうかのフラグ<BR>
     * <BR>
     * false：　@未実施<BR>
     * true：　@実施<BR>
     * <BR>
     * ----<English>----------------------<BR>
     * <BR>
     * ruitoFlag<BR>
     * <BR>
     * Flag to see if ruito is executed<BR>
     * <BR>
     * false: FALSE<BR>
     * true: TRUE<BR>
     * <BR>
     */
    public boolean ruitoFlag = false;

    /**
     * (外国株式実施フラグ)<BR>
     * <BR>
     * 外国株式を実施しているかどうかのフラグ<BR>
     * <BR>
     * false：　@未実施<BR>
     * true：　@実施<BR>
     * <BR>
     * ----<English>----------------------<BR>
     * <BR>
     * fstkFlag<BR>
     * <BR>
     * Flag to see if fstk is executed<BR>
     * <BR>
     * false: FALSE<BR>
     * true: TRUE<BR>
     * <BR>
     */
    public boolean fstkFlag = false;
    
    /**
     * @@roseuid 42130464020B
     */
    public WEB3AdminProductExecutionInfo()
    {
    }
}
@
