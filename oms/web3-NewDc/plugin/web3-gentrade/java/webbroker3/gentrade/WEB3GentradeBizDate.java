head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeBizDate.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()εa€ Ψ\[VVXeζρ
File Name        : cΖϊvZ(WEB3GentradeBizDate.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/12 ϋό΄@@Ηa(SRA) VKμ¬
Revesion History : 2004/07/12 ηΎ­ (u) ΟX
Revesion History : 2005/07/07 Π (u) calcFeqBizDate()πΗΑ
Revesion History : 2007/12/18 Σω (u)y€ΚzdlΟXEfNo.294
Revesion History : 2008/08/15 ζβΡQ (u)y€ΚzdlΟXEfNo.333
*/
package webbroker3.gentrade;

import java.sql.Timestamp;
import java.util.Calendar;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * cΖϊvZ[eBeBNX<BR>
 */
public class WEB3GentradeBizDate
{
    /**
     * O[eBeB<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeBizDate.class);

    /**
     * vZξϊ<BR>
     */
    private Timestamp baseDate = null;

    /**
     * RXgN^<BR>
     *<BR> 
     * @@param l_tsBaseBizDate ξϊ
     */
    public WEB3GentradeBizDate(Timestamp l_tsBaseDate)
    {
        setBaseDate(l_tsBaseDate);
    }

    /**
     * ξϊΜΔέθB<BR>
     *<BR> 
     * @@param l_tsBaseDate ξϊ
     */
    public void setBaseDate(Timestamp l_tsBaseDate)
    {
        this.baseDate = new Timestamp(l_tsBaseDate.getTime());
    }

    /**
     * ξϊ©ηΑZEΈZ΅½cΖϊπίΤ΅ά·B<BR>
     *<BR> 
     * Pjthis.calccΖϊ()πR[΅AcΖϊπZo·ιB<BR>
     * <BR>
     * [calccΖϊΙn·ψ]<BR>
     * ξϊF@@this.vZξϊ<BR>
     * ΑZ^ΈZϊF@@p[^.ΑZ^ΈZϊ<BR>
     * <BR>
     * QjZo³κ½cΖϊπΤp·ιB<BR>
     * @@param l_intRollDays - ΑZ^ΈZϊ<BR>
     * ex) 1EEEcΖϊπΤp<BR>
     *      0EEEcΖϊπΤp(ρcΖϊΜκΝαOπX[)<BR>
     * @@@@-1EEEOcΖϊπΤp<BR>
     * @@throws WEB3SystemLayerException
     */
    public Timestamp roll(int l_intRoll) throws WEB3SystemLayerException
    {
        return calcBizDate(this.baseDate, l_intRoll);
    }

    /**
     * (getTcΖϊ)<BR>
     * ξϊπάί½TcΖϊπίιB<BR>
     * <BR>
     * PjJ_[NXΜCX^XπζΎ΅Athis.ξϊπZbg·ιB<BR>
     * <BR>
     * QjJ_[NX.add()\bhπR[΅AϊtπϊjϊΜϊtάΕί·B<BR>
     * <BR>
     * Rjthis.calccΖϊ()\bhπR[΅AcΖϊπZo·ιB<BR>
     * <BR>
     * [calccΖϊ()Ιn·ψ] <BR>
     * ξϊF@@J_[NX.getTime().getTime()Μίθlπ³ΙΆ¬΅½TimestampNX<BR>
     * ΑZ^ΈZϊF@@+1<BR>
     * <BR>
     * SjZo³κ½cΖϊπΤp·ιB<BR>
     * @@return Timestamp
     * @@throws WEB3BaseException
     */
    public Timestamp getWeekStartBizDate() throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getWeekStartBizDate()";

        log.entering(STR_METHOD_NAME);

        //PjJ_[NXΜCX^XπζΎ΅Athis.ξϊπZbg·ιB
        Calendar l_bizDateCalendar = Calendar.getInstance();
        l_bizDateCalendar.setTime(this.baseDate);

        //QjJ_[NX.add()\bhπR[΅AϊtπϊjϊΜϊtάΕί·B
        int l_intSunday = 0;
        switch (l_bizDateCalendar.get(Calendar.DAY_OF_WEEK))
        {
            case Calendar.MONDAY :
                l_intSunday = -1;
                break;
            case Calendar.TUESDAY :
                l_intSunday = -2;
                break;
            case Calendar.WEDNESDAY :
                l_intSunday = -3;
                break;
            case Calendar.THURSDAY :
                l_intSunday = -4;
                break;
            case Calendar.FRIDAY :
                l_intSunday = -5;
                break;
            case Calendar.SATURDAY :
                l_intSunday = -6;
                break;
            default :

                }
        // ϊjϊΙέθ·ι
        l_bizDateCalendar.add(Calendar.DATE, l_intSunday);

        //Rjthis.calccΖϊ()\bhπR[΅AcΖϊπZo·ιB
        Timestamp l_tsWeekStartBizDate =
            calcBizDate(
                new Timestamp(l_bizDateCalendar.getTime().getTime()),
                1);

        log.exiting(STR_METHOD_NAME);

        // SjZo³κ½cΖϊπΤp·ιB
        return l_tsWeekStartBizDate;
    }

    /**
     * ξϊπάί½TcΖϊπίΤ΅ά·B<BR>
     *<BR> 
     * PjJ_[NXΜCX^XπζΎ΅Athis.ξϊπZbg·ιB<BR>
     *  <BR>
     * QjJ_[NX.add()\bhπR[΅Aϊtπyjϊ<BR>
     *    ΜϊtάΕiίιB<BR>
     *  <BR>
     * Rjthis.calccΖϊ()\bhπR[΅AcΖϊπZo·ιB<BR>
     *  <BR>
     *    [calccΖϊ()Ιn·ψ] <BR>
     *    ξϊF@@J_[NX.getTime().getTime()Μίθl<BR>
     *    π³ΙΆ¬΅½TimestampNX <BR>
     *    ΑZ^ΈZϊF@@-1 <BR>
     *  <BR>
     * SjZo³κ½cΖϊπΤp·ιB<BR>
     * @@return TcΖϊ
     * @@throws WEB3SystemLayerException
     */
    public Timestamp getWeekEndBizDate() throws WEB3SystemLayerException
    {
        String STR_METHOD_NAME = "calcWeekEndBizDate()";

        log.entering(STR_METHOD_NAME);

        //PjJ_[NXΜCX^XπζΎ΅Athis.ξϊπZbg·ιB
        Calendar l_bizDateCalendar = Calendar.getInstance();
        l_bizDateCalendar.setTime(this.baseDate);

        //QjJ_[NX.add()\bhπR[΅Aϊtπyjϊ
        //ΜϊtάΕiίι
        int l_intSaturday = 0;
        switch (l_bizDateCalendar.get(Calendar.DAY_OF_WEEK))
        {
            case Calendar.SUNDAY :
                l_intSaturday = 6;
                break;
            case Calendar.MONDAY :
                l_intSaturday = 5;
                break;
            case Calendar.TUESDAY :
                l_intSaturday = 4;
                break;
            case Calendar.WEDNESDAY :
                l_intSaturday = 3;
                break;
            case Calendar.THURSDAY :
                l_intSaturday = 2;
                break;
            case Calendar.FRIDAY :
                l_intSaturday = 1;
                break;
            default :

                }
        // yjϊΙέθ·ι
        l_bizDateCalendar.add(Calendar.DATE, l_intSaturday);

        //Rjthis.calccΖϊ()\bhπR[΅AcΖϊπZo·ιB
        Timestamp l_tsWeekEndBizDate =
            calcBizDate(
                new Timestamp(l_bizDateCalendar.getTime().getTime()),
                -1);

        log.exiting(STR_METHOD_NAME);

        // SjZo³κ½cΖϊπΤp·ιB
        return l_tsWeekEndBizDate;
    }

    /**
     * (calccΖϊ) <BR>
     * ξϊ©ηΑZ^ΈZ΅½cΖϊπZo΅AΤp·ιB<BR>
     *  <BR>
     * PjcΖϊΖ΅ΔJEg³κ½ξϊΜ = p[^.ΑZ^ΈZ<BR>
     *    ϊΜβΞlΖΘιάΕΘΊΜπJθΤ·B<BR>
     *   ¦p[^.ΑZ^ΈZϊ = 0ΜκΝA1ΖΘιάΕJθΤ·B<BR>
     * <BR>
     * P|Pjp[^.ξϊΙ1πΑZ(ά½ΝΈZ)·ιB<BR>
     * @@¦ΑZ³κ½ξϊΜjϊπζΎ΅AyEϊΎΑ½κΝA<BR>
     *      yEϊΘOΙΘιάΕ1πΑZ(ά½ΝΈZ)·ιB<BR>
     * <BR>
     * P|QjΘΊΜπΙΔJ_[e[uπυ΅Af[^<BR>
     *   ͺζΎΕ«Θ©Α½κΝAcΖϊΖ΅ΔJEg·ιB<BR>
     * <BR>
     *   [π] <BR>
     * @@  ϊt@@@@@@@@@@ @@ΑZ(ά½ΝΈZ)³κ½ξϊ <BR>
     * @@  cΖϊζͺ@@@@hρcΖϊh <BR>
     *  <BR>
     * QjΕγΙcΖϊΖ΅ΔJEg³κ½ξϊπΤp·ιB<BR>
     * DBANZXΙΈs΅½κ<BR>
     * class    : WEB3SystemLayerException<BR>
     * tag      : SYSTEM_ERROR_80003<BR>
     *<BR> 
     * cΖϊΜvZAξϊͺρcΖϊΕ[lͺ0ͺwθ³κ½κ<BR>
     * class    : WEB3SystemLayerException<BR>
     * tag      : SYSTEM_ERROR_80010<BR>
     * @@param l_tsBaseDate - ξϊ
     * @@param l_intRoll - ΑZEΈZϊ
     * @@return cΖϊ
     * @@throws WEB3SystemLayerException
     */
    private Timestamp calcBizDate(Timestamp l_tsBaseDate, int l_intRoll)
        throws WEB3SystemLayerException
    {
        String STR_METHOD_NAME = "calcBizDate(Timestamp, int)";
        log.entering(STR_METHOD_NAME);

        //ξϊ=ρcΖϊ ©Β ΑZ^ΈZϊ = 0ΜκΝαOπX[·ι
        String l_strBizDateType =
            WEB3GentradeTradingTimeManagement.getBizDateType(l_tsBaseDate);
        if ((WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
            && (l_intRoll == 0))
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //J_[NXΜCX^XπζΎ΅AξϊπZbg·ι
        Calendar l_bizDateCalendar = Calendar.getInstance();
        l_bizDateCalendar.setTime(l_tsBaseDate);
        // DBυpΙͺb~bπϊ»
        l_bizDateCalendar.set(Calendar.HOUR_OF_DAY, 0);
        l_bizDateCalendar.set(Calendar.MINUTE, 0);
        l_bizDateCalendar.set(Calendar.SECOND, 0);
        l_bizDateCalendar.set(Calendar.MILLISECOND, 0);
        Timestamp l_tsTmpBizDate;
        int l_intRollDays = l_intRoll;

        if (l_intRollDays >= 0)
        {
            while (l_intRollDays != 0)
            {
                //ξϊΙ1πΑZ·ι
                l_bizDateCalendar.add(Calendar.DATE, 1);
                l_tsTmpBizDate =
                    new Timestamp(l_bizDateCalendar.getTime().getTime());
                //cΖϊζͺζΎ
                l_strBizDateType =
                    WEB3GentradeTradingTimeManagement.getBizDateType(
                        l_tsTmpBizDate);
                //cΖϊΜκ
                if (!WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
                {
                    l_intRollDays = l_intRollDays - 1;
                }
            }

        }
        else
        {
            while (l_intRollDays != 0)
            {
                //ξϊΙ1πΈZ·ι
                l_bizDateCalendar.add(Calendar.DATE, -1);
                l_tsTmpBizDate =
                    new Timestamp(l_bizDateCalendar.getTime().getTime());
                //cΖϊζͺζΎ
                l_strBizDateType =
                    WEB3GentradeTradingTimeManagement.getBizDateType(
                        l_tsTmpBizDate);
                //cΖϊΜκ
                if (!WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
                {
                    l_intRollDays = l_intRollDays + 1;
                }
            }
        }

        //ΕγΙcΖϊΖ΅ΔJEg³κ½ξϊπΤp·ι
        log.exiting(STR_METHOD_NAME);
        return new Timestamp(l_bizDateCalendar.getTime().getTime());

    }

    /**
     * (getwθcΖϊ) <BR>
     * ξϊ©ηAψΕwθ³κ½NEEϊΜOά½ΝγΜϊtπ<BR>
     * Zo΅AΤ·B ϊtΜJEgϋ@@ΝAΠ[όκΖ·ιB<BR>
     *  <BR>
     *Pj@@ψ.ξϊΙΞ΅AΘΊΜϊtvZiNjπs€B <BR>
     * ψ.ΑZ^ΈZ"ΑZ"ΜκΝAψ.N γΜϊtπZo·ιB<BR> 
     * ψ.ΑZ^ΈZ"ΈZ"ΜκΝAψ.N OΜϊtπZo·ιB<BR> 
     *  <BR>
     * Qj@@PjΕvZ΅½ϊtΙΞ΅AΘΊΜϊtvZijπs€B<BR> 
     * ψ.ΑZ^ΈZ"ΑZ"ΜκΝAψ. γΜϊtπZo·ιB<BR> 
     * ψ.ΑZ^ΈZ"ΈZ"ΜκΝAψ. OΜϊtπZo·ιB<BR> 
     *  <BR>
     * vZ΅½ϊtͺA <BR>
     *     J_[γΙΆέ΅Θ’κiURPϊj <BR> 
     *     ΝAYΜΕIϊtπZo΅Θ~Εgp·ιB<BR> 
     *  <BR>
     * Rj@@QjΕvZ΅½ϊtΙΞ΅AΘΊΜϊtvZiϊjπs€B<BR> 
     * ψ.ΑZ^ΈZ"ΑZ"ΜκΝAψ.ϊ γΜϊtπZo·ιB<BR> 
     * ψ.ΑZ^ΈZ"ΈZ"ΜκΝAψ.ϊ OΜϊtπZo·ιB<BR> 
     *  <BR>
     * Sj@@RjΕvZ΅½ϊtͺρcΖϊΜκAΌOΜcΖϊπZo·ιB<BR> 
     *  <BR>
     * Tj@@vZ΅½ϊtπΤ·B <BR>   
     * <BR>   
     * @@param l_tsBaseDate - (ξϊ) <BR>   
     *       cΖϊvZΙgp·ιξϊ <BR> 
     * @@param l_lngYear - (N) <BR>
     * @@param l_lngMonth - () <BR>
     * @@param l_lngDay - (ϊ) <BR> 
     * @@param l_intFlag - (ΑZ^ΈZ)<BR>
     *       ξϊΙΞ΅AΑZi’ΜϊtπίιjΜκA1πZbgB<BR> 
     *       ξϊΙΞ΅AΈZiίΜϊtπίιjΜκA-1πZbgB<BR>
     *  <BR>  
     * @@throws WEB3SystemLayerException<BR> 
     *  <BR> 
     */
    public static Timestamp getAppointmentBizDate(
        Timestamp l_tsBaseDate,
        long l_lngYear,
        long l_lngMonth,
        long l_lngDay,
        int l_intFlag)
        throws WEB3SystemLayerException
    {
        String STR_METHOD_NAME = "getAppointmentBizDate(Timestamp, long, long, long, int)";
        log.entering(STR_METHOD_NAME);
        
        if((l_intFlag != 1) && (l_intFlag != -1))
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3GentradeBizDate.class.getName() + "." + STR_METHOD_NAME,
                "(ΑZ^ΈZ = " + l_intFlag + ") F " 
                + "ξϊΙΞ΅AΑZi’ΜϊtπίιjΜκA1πZbgB"
                +"ξϊΙΞ΅AΈZiίΜϊtπίιjΜκA-1πZbgB");
        }
        
        //J_[NXΜCX^XπζΎ΅AξϊπZbg·ιB
        Calendar l_bizDateCalendar = Calendar.getInstance();
        l_bizDateCalendar.setTime(l_tsBaseDate);
        
        //get N  ϊ
        int l_intYear;
        int l_intMonth;
        int l_intDay;
        if(l_intFlag == 1)
        {
            l_intYear = Integer.parseInt(String.valueOf(l_lngYear));
            l_intMonth = Integer.parseInt(String.valueOf(l_lngMonth));
            l_intDay = Integer.parseInt(String.valueOf(l_lngDay));
        }
        else
        {
            l_intYear = - Integer.parseInt(String.valueOf(l_lngYear));
            l_intMonth = - Integer.parseInt(String.valueOf(l_lngMonth));
            l_intDay = - Integer.parseInt(String.valueOf(l_lngDay));
        }
          
        //ϊtπvZ·ι
        l_bizDateCalendar.add(Calendar.YEAR, l_intYear);
        l_bizDateCalendar.add(Calendar.MONTH, l_intMonth);
        l_bizDateCalendar.add(Calendar.DATE, l_intDay);
        
        //vZ΅½ϊtͺρcΖϊΜκAΌOΜcΖϊπZo·ιB
        Timestamp l_tsAppointmentBizDate = new Timestamp(l_bizDateCalendar.getTime().getTime());
        String l_strBizDateType = WEB3GentradeTradingTimeManagement.getBizDateType(l_tsAppointmentBizDate);
        if(WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
        {
            WEB3GentradeBizDate l_genBizDate = new WEB3GentradeBizDate(l_tsAppointmentBizDate);
            l_tsAppointmentBizDate = l_genBizDate.roll(-1);
            
            //ͺb~bπίθ
            Calendar l_appBizDateCalendar = Calendar.getInstance();
            l_appBizDateCalendar.setTime(l_tsAppointmentBizDate);
            l_appBizDateCalendar.set(Calendar.HOUR,l_bizDateCalendar.get(Calendar.HOUR));
            l_appBizDateCalendar.set(Calendar.MINUTE,l_bizDateCalendar.get(Calendar.MINUTE));
            l_appBizDateCalendar.set(Calendar.SECOND,l_bizDateCalendar.get(Calendar.SECOND));
            l_appBizDateCalendar.set(Calendar.MILLISECOND,l_bizDateCalendar.get(Calendar.MILLISECOND));
            l_tsAppointmentBizDate = new Timestamp(l_appBizDateCalendar.getTime().getTime());
        }

        log.exiting(STR_METHOD_NAME);
        return l_tsAppointmentBizDate;
    }

    /**
     * (calcOcΖϊ) <BR>
     * ξϊ©ηΑZ^ΈZ΅½cΖϊπZo΅AΤp·ιB<BR>
     * <BR>
     * ¦ξϊ=ρcΖϊ ©Β ΑZ^ΈZϊ = 0ΜκΝαOπX[·ιB<BR>
     * <BR>
     * <BR>
     * PjcΖϊΖ΅ΔJEg³κ½ξϊΜ =<BR> 
     * p[^.ΑZ^ΈZϊΜβΞlΖΘιάΕΘΊΜπJθΤ·B<BR>
     * ¦p[^.ΑZ^ΈZϊ = 0ΜκΝA1ΖΘιάΕJθΤ·B<BR>
     * <BR>
     * P|Pjp[^.ξϊΙ1πΑZ(ά½ΝΈZ)·ιB<BR>
     * ¦ΑZ³κ½ξϊΜjϊπζΎ΅AyEϊΎΑ½κΝA<BR>
     * yEϊΘOΙΘιάΕ1πΑZ(ά½ΝΈZ)·ιB<BR>
     * <BR>
     * P|QjΘΊΜπΙΔJ_[e[uC<BR>
     * OCOsκJ_[πυ΅A<BR>
     * ΗΏηΰf[^ͺζΎΕ«Θ©Α½κΝAcΖϊΖ΅ΔJEg·ιB<BR>
     * <BR>
     * [J_[e[uυπ]<BR>
     * ϊt = ΑZ(ά½ΝΈZ)³κ½ξϊ<BR>
     * cΖϊζͺ = hρcΖϊh<BR>
     * <BR>
     * [OCOsκJ_[υπ]<BR>
     * ΨοΠR[h = ΨοΠR[h<BR>
     * sκR[h = sκR[h<BR>
     * ϊt = ΑZ(ά½ΝΈZ)³κ½ξϊ<BR>
     * cΖϊζͺ = hρcΖϊh<BR>
     * <BR>
     * QjΕγΙcΖϊΖ΅ΔJEg³κ½ξϊπΤp·ιB<BR> 
     *<BR> 
     * @@param l_strInstitutionCode ΨοΠR[h
     * @@param l_strMarketCode sκR[h
     * @@param l_tsBaseDate ξϊ
     * @@param l_intRoll ΑZEΈZϊ
     * @@return cΖϊ
     * @@throws WEB3SystemLayerException
     */
    public Timestamp calcFeqBizDate(
        String l_strInstitutionCode,
        String l_strMarketCode,
        Timestamp l_tsBaseDate,
        int l_intRoll)
        throws WEB3SystemLayerException
    {
        String STR_METHOD_NAME = "calcFeqBizDate(String, String, Timestamp, int)";
        log.entering(STR_METHOD_NAME);

        //ξϊ=ρcΖϊ ©Β ΑZ^ΈZϊ = 0ΜκΝαOπX[·ι
        String l_strBizDateType = 
            WEB3GentradeTradingTimeManagement.getBizDateType(l_tsBaseDate);
        String l_strFeqBizDateType = 
            WEB3GentradeTradingTimeManagement.getFeqBizDateType(
                l_strInstitutionCode,
                l_strMarketCode,
                l_tsBaseDate);
        
        if ((WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType) || 
            (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strFeqBizDateType)))
            && (l_intRoll == 0))
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //J_[NXΜCX^XπζΎ΅AξϊπZbg·ι
        Calendar l_bizDateCalendar = Calendar.getInstance();
        l_bizDateCalendar.setTime(l_tsBaseDate);
        // DBυpΙͺb~bπϊ»
        l_bizDateCalendar.set(Calendar.HOUR_OF_DAY, 0);
        l_bizDateCalendar.set(Calendar.MINUTE, 0);
        l_bizDateCalendar.set(Calendar.SECOND, 0);
        l_bizDateCalendar.set(Calendar.MILLISECOND, 0);
        Timestamp l_tsTmpBizDate;
        int l_intRollDays = l_intRoll;

        if (l_intRollDays >= 0)
        {
            while (l_intRollDays != 0)
            {
                //ξϊΙ1πΑZ·ι
                l_bizDateCalendar.add(Calendar.DATE, 1);
                l_tsTmpBizDate =
                    new Timestamp(l_bizDateCalendar.getTime().getTime());
                //J_[e[u©ηcΖϊζͺζΎ
                l_strBizDateType =
                    WEB3GentradeTradingTimeManagement.getBizDateType(
                        l_tsTmpBizDate);
                //OCOsκJ_[©ηcΖϊζͺζΎ        
                l_strFeqBizDateType = 
                    WEB3GentradeTradingTimeManagement.getFeqBizDateType(
                        l_strInstitutionCode,
                        l_strMarketCode,
                        l_tsTmpBizDate);        
                //cΖϊΜκ
                if (!WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType) &&
                    !WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strFeqBizDateType))
                {
                    l_intRollDays = l_intRollDays - 1;
                }
            }
        }
        else
        {
            while (l_intRollDays != 0)
            {
                //ξϊΙ1πΈZ·ι
                l_bizDateCalendar.add(Calendar.DATE, -1);
                l_tsTmpBizDate =
                    new Timestamp(l_bizDateCalendar.getTime().getTime());
                //J_[e[u©ηcΖϊζͺζΎ
                l_strBizDateType =
                    WEB3GentradeTradingTimeManagement.getBizDateType(
                        l_tsTmpBizDate);
                //OCOsκJ_[©ηcΖϊζͺζΎ
                l_strFeqBizDateType = 
                    WEB3GentradeTradingTimeManagement.getFeqBizDateType(
                        l_strInstitutionCode,
                        l_strMarketCode,
                        l_tsTmpBizDate);        
                //cΖϊΜκ
                if (!WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType) &&
                    !WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strFeqBizDateType))
                {
                    l_intRollDays = l_intRollDays + 1;
                }
            }
        }

        //ΕγΙcΖϊΖ΅ΔJEg³κ½ξϊπΤp·ι
        log.exiting(STR_METHOD_NAME);
        return new Timestamp(l_bizDateCalendar.getTime().getTime());
    }

    /**
     * (calcPTScΖϊ)<BR>
     * ξϊ©ηΑZ^ΈZ΅½cΖϊπZo΅AΤp·ιB  <BR>
     * <BR>
     * ¦@@ζψΤΗ.getPTScΖϊζͺ( )Μίθlͺ"ρcΖϊ"©ΒA  <BR>
     * ¦@@ΑZ^ΈZϊ = 0ΜκΝαOπX[·ιB  <BR>
     * @@@@mgetPTScΖϊζͺ( )Μψn  <BR>
     * @@@@@@ϊtF@@ξϊ  <BR>
     * <BR>
     * Pj@@cΖϊΖ΅ΔJEg³κ½ξϊΜ = p[^.ΑZ^ΈZϊ<BR>
     * @@@@@@ΜβΞlΖΘιάΕΘΊΜπJθΤ·B  <BR>
     * ¦p[^.ΑZ^ΈZϊ = 0ΜκΝA1ΖΘιάΕJθΤ·B  <BR>
     * <BR>
     * P|Pjp[^.ξϊΙ1πΑZ(ά½ΝΈZ)·ιB  <BR>
     * @@¦@@ζψΤΗ.getPTScΖϊζͺ( )πR[΅A<BR>
     * @@@@@@@@ίθlͺ"ρcΖϊ"ΜκΝ  <BR>
     * @@¦@@"ρcΖϊ"ΘOΙΘιάΕ1πΑZ(ά½ΝΈZ)·ιB  <BR>
     * @@@@@@mgetPTScΖϊζͺ( )Μψn  <BR>
     * @@@@@@@@ϊtF@@1πΑZ(ά½ΝΈZ)΅½ξϊ  <BR>
     * @@  <BR>
     * Qj@@ΕγΙcΖϊΖ΅ΔJEg³κ½ξϊπΤp·ιB<BR>
     * <BR>
     * @@param l_tsBaseDate - ξϊ
     * @@param l_intRoll - ΑZEΈZϊ
     * @@return cΖϊ
     * @@throws WEB3SystemLayerException
     */
    public Timestamp calcPTSBizDate(Timestamp l_tsBaseDate, int l_intRoll)
        throws WEB3SystemLayerException
    {
        String STR_METHOD_NAME = "calcPTSBizDate(Timestamp, int)";
        log.entering(STR_METHOD_NAME);

        //ζψΤΗ.getPTScΖϊζͺ( )Μίθlͺ"ρcΖϊ"©ΒA
        //ΑZ^ΈZϊ = 0ΜκΝαOπX[·ιB
        String l_strBizDateType =
            WEB3GentradeTradingTimeManagement.getPTSBizDateType(l_tsBaseDate);
        if ((WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
            && (l_intRoll == 0))
        {
            log.debug("p[^ls³B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //J_[NXΜCX^XπζΎ΅AξϊπZbg·ι
        Calendar l_bizDateCalendar = Calendar.getInstance();
        l_bizDateCalendar.setTime(l_tsBaseDate);
        // DBυpΙͺb~bπϊ»
        l_bizDateCalendar.set(Calendar.HOUR_OF_DAY, 0);
        l_bizDateCalendar.set(Calendar.MINUTE, 0);
        l_bizDateCalendar.set(Calendar.SECOND, 0);
        l_bizDateCalendar.set(Calendar.MILLISECOND, 0);
        Timestamp l_tsTmpBizDate;
        int l_intRollDays = l_intRoll;

        if (l_intRollDays >= 0)
        {
            while (l_intRollDays != 0)
            {
                //ξϊΙ1πΑZ·ι
                l_bizDateCalendar.add(Calendar.DATE, 1);
                l_tsTmpBizDate =
                    new Timestamp(l_bizDateCalendar.getTime().getTime());
                //cΖϊζͺζΎ
                l_strBizDateType =
                    WEB3GentradeTradingTimeManagement.getPTSBizDateType(
                        l_tsTmpBizDate);
                //cΖϊΜκ
                if (!WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
                {
                    l_intRollDays = l_intRollDays - 1;
                }
            }
        }
        else
        {
            while (l_intRollDays != 0)
            {
                //ξϊΙ1πΈZ·ι
                l_bizDateCalendar.add(Calendar.DATE, -1);
                l_tsTmpBizDate =
                    new Timestamp(l_bizDateCalendar.getTime().getTime());
                //cΖϊζͺζΎ
                l_strBizDateType =
                    WEB3GentradeTradingTimeManagement.getPTSBizDateType(
                        l_tsTmpBizDate);
                //cΖϊΜκ
                if (!WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
                {
                    l_intRollDays = l_intRollDays + 1;
                }
            }
        }

        //ΕγΙcΖϊΖ΅ΔJEg³κ½ξϊπΤp·ι
        log.exiting(STR_METHOD_NAME);
        return new Timestamp(l_bizDateCalendar.getTime().getTime());
    }
}
@
