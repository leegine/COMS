head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.24.08.51.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6544d8b05f516f9;
filename	WEB3AccInfoEleDeliveryInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤rWlXECmx[V
File Name        : dqðtîñ(WEB3AccInfoEleDeliveryInfo.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revision History : 2010/11/12 £«F(u) VKì¬ dlÏXfNo.277 280
*/
package webbroker3.accountinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (dqðtîñ)<BR>
 * dqðtîñNX<BR>
 * <BR>
 * @@author £«F
 * @@version 1.0
 */
public class WEB3AccInfoEleDeliveryInfo extends Message
{
    /**
     * (ÊTðtæªXVú)<BR>
     * ÊTðtæªXVú
     */
    public Date reportDivUpdateDate5;

    /**
     * (ÊT\æª)<BR>
     * ÊT\æª <BR>
     * <BR>
     * 0F@@\ <BR>
     * 1F@@\®¹<BR>
     */
    public String reportRegDiv5;

    /**
     * (ÊTðtæª)<BR>
     * ÊTðtæª <BR>
     * <BR>
     * 0F@@XÖzz <BR>
     * 1F@@dqzz<BR>
     */
    public String reportDiv5;

    /**
     * (ÊSðtæªXVú)<BR>
     * ÊSðtæªXVú
     */
    public Date reportDivUpdateDate4;

    /**
     * (ÊS\æª)<BR>
     * ÊS\æª <BR>
     * <BR>
     * 0F@@\ <BR>
     * 1F@@\®¹<BR>
     */
    public String reportRegDiv4;

    /**
     * (ÊSðtæª)<BR>
     * ÊSðtæª <BR>
     * <BR>
     * 0F@@XÖzz <BR>
     * 1F@@dqzz<BR>
     */
    public String reportDiv4;

    /**
     * (ÊRðtæªXVú)<BR>
     * ÊRðtæªXVú
     */
    public Date reportDivUpdateDate3;

    /**
     * (ÊR\æª)<BR>
     * ÊR\æª <BR>
     * <BR>
     * 0F@@\ <BR>
     * 1F@@\®¹<BR>
     */
    public String reportRegDiv3;

    /**
     * (ÊRðtæª)<BR>
     * ÊRðtæª <BR>
     * <BR>
     * 0F@@XÖzz <BR>
     * 1F@@dqzz<BR>
     */
    public String reportDiv3;
    
    /**
     * (ÊQðtæªXVú)<BR>
     * ÊQðtæªXVú
     */
    public Date reportDivUpdateDate2;

    /**
     * (ÊQ\æª)<BR>
     * ÊQ\æª <BR>
     * <BR>
     * 0F@@\ <BR>
     * 1F@@\®¹<BR>
     */
    public String reportRegDiv2;

    /**
     * (ÊQðtæª)<BR>
     * ÊQðtæª <BR>
     * <BR>
     * 0F@@XÖzz <BR>
     * 1F@@dqzz<BR>
     */
    public String reportDiv2;

    /**
     * (ÊPðtæªXVú)<BR>
     * ÊPðtæªXVú
     */
    public Date reportDivUpdateDate1;

    /**
     * (ÊP\æª)<BR>
     * ÊP\æª <BR>
     * <BR>
     * 0F@@\ <BR>
     * 1F@@\®¹<BR>
     */
    public String reportRegDiv1;

    /**
     * (ÊPðtæª)<BR>
     * ÊPðtæª <BR>
     * <BR>
     * 0F@@XÖzz <BR>
     * 1F@@dqzz<BR>
     */
    public String reportDiv1;

    /**
     * (ñ¼EKèWñ\óÔæª)<BR>
     * ñ¼EKèWñ\óÔæª <BR>
     * <BR>
     * 0F@@\ <BR>
     * 1F@@\®¹<BR>
     */
    public String ordRulRepRegDiv;

    /**
     * (ñ¼EKèWñðtóÔæªXVú)<BR>
     * ñ¼EKèWñðtóÔæªXVú<BR>
     */
    public Date ordRulReportDivUpdateDate;

    /**
     * (ñ¼EKèWñðtóÔæª)<BR>
     * ñ¼EKèWñðtóÔæª <BR>
     * <BR>
     * 0F@@XÖzz<BR>
     * 1F@@dqzz<BR>
     */
    public String ordRulReportDiv;

    /**
     * (æøñ\óÔæª)<BR>
     * æøñ\óÔæª <BR>
     * <BR>
     * 0F@@\ <BR>
     * 1F@@\®¹ <BR>
     */
    public String tradingReportRegDiv;

    /**
     * (æøñðtóÔæªXVú)<BR>
     * æøñðtóÔæªXVú<BR>
     */
    public Date tradingReportDivUpdateDate;

    /**
     * (æøcñ\óÔæª)<BR>
     * æøcñ\óÔæª <BR>
     * <BR>
     * 0F@@\ <BR>
     * 1F@@\®¹ <BR>
     */
    public String positionReportRegDiv;

    /**
     * (æøcñðtóÔæªXVú)<BR>
     * æøcñðtóÔæªXVú<BR>
     */
    public Date positionReportDivUpdateDate;

    /**
     * (^pñ\óÔæª)<BR>
     * ^pñ\óÔæª <BR>
     * <BR>
     * 0F@@\ <BR>
     * 1F@@\®¹ <BR>
     */
    public String opeReportRegDiv;

    /**
     * (^pñðtóÔæªXVú)<BR>
     * ^pñðtóÔæªXVú<BR>
     */
    public Date opeReportDivUpdateDate;

    /**
     * (^pñðtóÔæª)<BR>
     * ^pñðtóÔæª <BR>
     * <BR>
     * 0F@@XÖzz <BR>
     * 1F@@dqzz<BR>
     */
    public String opeReportDiv;

    /**
     * (æøñðtóÔæª)<BR>
     * æøñðtóÔæª<BR>
     * <BR>
     * 0F@@XÖzz <BR>
     * 1F@@dqzz<BR>
     */
    public String tradingReportDiv;

    /**
     * (æøcñðtóÔæª)<BR>
     * æøcñðtóÔæª<BR>
     * <BR>
     * 0F@@XÖzz <BR>
     * 1F@@dqzz<BR>
     */
    public String positionReportDiv;

    /**
     * RXgN^B<BR>
     */
    public WEB3AccInfoEleDeliveryInfo()
    {

    }
}
@
