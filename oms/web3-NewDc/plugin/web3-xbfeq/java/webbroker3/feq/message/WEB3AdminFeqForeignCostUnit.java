head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.31.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqForeignCostUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : »nè¿îñ(WEB3AdminFeqForeignCostUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 sp (u) VKì¬
                 : 2005/08/02 ¤ûU (u) r[
*/

package webbroker3.feq.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (»nè¿îñ)<BR>
 * »nè¿îñNX
 *   
 * @@author sp
 * @@version 1.0
 */
public class WEB3AdminFeqForeignCostUnit extends Message 
{
    
    /**
     * OoÍ[eBeBB<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminFeqForeignCostUnit.class);
        
    /**
     * (KpúÔi©j)<BR>
     * KpúÔi©j
     */
    public Date applyStartDate;
    
    /**
     * (KpúÔij)<BR>
     * KpúÔij
     */
    public Date applyEndDate;
    
    /**
     * (æøàzi©j)<BR>
     * æøàzi©j
     */
    public String tradingAmtFrom;
    
    /**
     * (æøàzij)<BR>
     * æøàzij
     */
    public String tradingAmtTo;
    
    /**
     * (¥û¦)<BR>
     * ¥û¦<BR>
     * PÊF%
     */
    public String collectRate;
    
    /**
     * (tÁàz)<BR>
     * tÁàz
     */
    public String additionalAmt;
    
    /**
     * (»nè¿îñ)<BR>
     * RXgN^
     * @@roseuid 4200B8FE0104
     */
    public WEB3AdminFeqForeignCostUnit() 
    {
     
    }
    
    /**
     * NGXgf[^Ì®«ð`FbN·éB<BR>
     * <BR>
     * PjKpúÔi©j<BR>
     * <BR>
     *    this.KpúÔi©j == null<BR>
     * <BR>
     *    ÌêAáOðX[·éB<BR>
     * @@class: WEB3BusinessLayerException<BR>
     * @@tag:   BUSINESS_ERROR_02073<BR>
     * <BR>
     * QjKpúÔij<BR>
     * <BR>
     *    this.KpúÔij != null and<BR>
     *    this.KpúÔi©j > this.KpúÔij<BR>
     * <BR>
     *    ÌêAáOðX[·éB<BR>
     * @@class: WEB3BusinessLayerException<BR>
     * @@tag:   BUSINESS_ERROR_01446<BR>
     * <BR>
     * Rjæøàzi©j<BR>
     * <BR>
     * R|Pj<BR>
     * <BR>
     *    this.æøàzi©j == null<BR>
     * <BR>
     *    ÌêAáOðX[·éB<BR>
     * @@class: WEB3BusinessLayerException<BR>
     * @@tag:   BUSINESS_ERROR_02074<BR>
     * <BR>
     * R|Qj<BR>
     * <BR>
     *    this.æøàzi©j < 0<BR>
     * <BR>
     *    ÌêAáOðX[·éB<BR>
     * @@class: WEB3BusinessLayerException<BR>
     * @@tag:   BUSINESS_ERROR_02075<BR>
     * <BR>
     * R|Rjthis.æøàzi©jÉ¬_ªÜÜêéê<BR>
     * ithis.æøàzi©j.indexOf('.') >= 0 Ìêj<BR>
     * <BR>
     *    this.æøàzi©j.split('.')[0].length() > 11 or<BR>
     *    this.æøàzi©j.split('.')[1].length() > 2<BR>
     * <BR>
     *    ÌêAáOðX[·éB<BR>
     * @@class: WEB3BusinessLayerException<BR>
     * @@tag:   BUSINESS_ERROR_02076<BR>
     * <BR>
     * R|Sjthis.æøàzi©jÉ¬_ªÜÜêÈ¢ê<BR>
     * ithis.æøàzi©j.indexOf('.') = -1 Ìêj<BR>
     *    this.æøàzi©j.length() > 11<BR>
     * <BR>
     *    ÌêAáOðX[·éB<BR>
     * @@class: WEB3BusinessLayerException<BR>
     * @@tag:   BUSINESS_ERROR_02076<BR>
     * <BR>
     * Sjæøàzij<BR>
     * <BR>
     *    this.æøàzij != null ÌêAÈºÌ`FbNðs¤B<BR>
     * <BR>
     * S|Pj<BR>
     * <BR>
     *    this.æøàzij < 0<BR>
     * <BR>
     *    ÌêAáOðX[·éB<BR>
     * @@class: WEB3BusinessLayerException<BR>
     * @@tag:   BUSINESS_ERROR_02077<BR>
     * <BR>
     * S|Qjthis.æøàzijÉ¬_ªÜÜêéê<BR>
     * ithis.æøàzij.indexOf('.') >= 0 Ìêj<BR>
     * <BR>
     *    this.æøàzij.split('.')[0].length() > 11 or<BR>
     *    this.æøàzij.split('.')[1].length() > 2<BR>
     * <BR>
     *    ÌêAáOðX[·éB<BR>
     * @@class: WEB3BusinessLayerException<BR>
     * @@tag:   BUSINESS_ERROR_02078<BR>
     * <BR>
     * S|Rjthis.æøàzijÉ¬_ªÜÜêÈ¢ê<BR>
     * ithis.æøàzij.indexOf('.') = -1 Ìêj<BR>
     * <BR>
     *    this.æøàzij.length() > 11<BR>
     * <BR>
     *    ÌêAáOðX[·éB<BR>
     * @@class: WEB3BusinessLayerException<BR>
     * @@tag:   BUSINESS_ERROR_02078<BR>
     * <BR>
     * S|Sj<BR>
     * <BR>
     *    this.æøàzi©j > this.æøàzij<BR>
     * <BR>
     *    ÌêAáOðX[·éB<BR>
     * @@class: WEB3BusinessLayerException<BR>
     * @@tag:   BUSINESS_ERROR_02079<BR>
     * <BR>
     * Tj¥û¦<BR>
     * <BR>
     * T|Pj<BR>
     * <BR>
     *    this.¥û¦ == null<BR>
     * <BR>
     *    ÌêAáOðX[·éB<BR>
     * @@class: WEB3BusinessLayerException<BR>
     * @@tag:   BUSINESS_ERROR_02080<BR>
     * <BR>
     * T|Qj<BR>
     * <BR>
     *    this.¥û¦ < 0<BR>
     * <BR>
     *    ÌêAáOðX[·éB<BR>
     * @@class: WEB3BusinessLayerException<BR>
     * @@tag:   BUSINESS_ERROR_02081<BR>
     * <BR>
     * T|Rjthis.¥û¦É¬_ªÜÜêéê<BR>
     * ithis.¥û¦.indexOf('.') >= 0 Ìêj<BR>
     * <BR>
     *    this.¥û¦.split('.')[0].length() > 3 or<BR>
     *    this.¥û¦.split('.')[1].length() > 5<BR>
     * <BR>
     *    ÌêAáOðX[·éB<BR>
     * @@class: WEB3BusinessLayerException<BR>
     * @@tag:   BUSINESS_ERROR_02082<BR>
     * <BR>
     * T|Sjthis.¥û¦É¬_ªÜÜêÈ¢ê<BR>
     * ithis.¥û¦.indexOf('.') = -1 Ìêj<BR>
     * <BR>
     *    this.¥û¦.length() > 3<BR>
     * <BR>
     *    ÌêAáOðX[·éB<BR>
     * @@class: WEB3BusinessLayerException<BR>
     * @@tag:   BUSINESS_ERROR_02082<BR>
     * <BR>
     * UjtÁàz<BR>
     * <BR>
     * U|Pj<BR>
     * <BR>
     *    this.tÁàz == null<BR>
     * <BR>
     *    ÌêAáOðX[·éB<BR>
     * @@class: WEB3BusinessLayerException<BR>
     * @@tag:   BUSINESS_ERROR_02083<BR>
     * <BR>
     * U|Qj<BR>
     * <BR>
     *    this.tÁàz < 0<BR>
     * <BR>
     *    ÌêAáOðX[·éB<BR>
     * @@class: WEB3BusinessLayerException<BR>
     * @@tag:   BUSINESS_ERROR_02084<BR>
     * <BR>
     * U|Rjthis.tÁàzÉ¬_ªÜÜêéê<BR>
     * ithis.tÁàz.indexOf('.') >= 0 Ìêj<BR>
     * <BR>
     *    this.tÁàz.split('.')[0].length() > 9 or<BR>
     *    this.tÁàz.split('.')[1].length() > 5<BR>
     * <BR>
     *    ÌêAáOðX[·éB<BR>
     * @@class: WEB3BusinessLayerException<BR>
     * @@tag:   BUSINESS_ERROR_02085<BR>
     * <BR>
     * U|Sjthis.tÁàzÉ¬_ªÜÜêÈ¢ê<BR>
     * ithis.tÁàz.indexOf('.') = -1 Ìêj<BR>
     * <BR>
     *    this.tÁàz.length() > 9<BR>
     * <BR>
     *    ÌêAáOðX[·éB<BR>
     * @@class: WEB3BusinessLayerException<BR>
     * @@tag:   BUSINESS_ERROR_02085<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42B0E859024B
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        //PjKpúÔi©j 
        //this.KpúÔi©j == null ÌêAáOðX[·éB 
        if (this.applyStartDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02073,
                this.getClass().getName() + STR_METHOD_NAME,
                " KpúÔi©jª¢üÍÅ·B"); 
        }
        
        //QjKpúÔij 
        // this.KpúÔij != null and this.KpúÔi©j > this.KpúÔij 
        //ÌêAáOðX[·éB 
        if (this.applyEndDate != null)
        {
            int l_intFlag = WEB3DateUtility.compareToDay(this.applyStartDate, this.applyEndDate);
            if (l_intFlag > 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01446,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " KpúÔFrom/To®«G[:" + 
                    this.applyStartDate + "(KpúÔi©j), " + 
                    this.applyEndDate + "(KpúÔij) "); 
            }
        }

        //Rjæøàzi©j 
        //R|Pj this.æøàzi©j == null ÌêAáOðX[·éB 
        if (WEB3StringTypeUtility.isEmpty(this.tradingAmtFrom))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02074,
                this.getClass().getName() + STR_METHOD_NAME,
                " æøàzi©jª¢üÍÅ·B"); 
        }
        
        double l_dblTradingAmtFrom = Double.parseDouble(this.tradingAmtFrom);
        //R|Qj this.æøàzi©j < 0 ÌêAáOðX[·éB 
        if (l_dblTradingAmtFrom < 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02075,
                this.getClass().getName() + STR_METHOD_NAME,
                " æøàzi©jª}CiXÌlÅ·:" + this.tradingAmtFrom); 
        }
        
        //R|Rjthis.æøàzi©jÉ¬_ªÜÜêéê
        //ithis.æøàzi©j.indexOf('.') >= 0 Ìêj
        if (this.tradingAmtFrom.indexOf('.') >= 0)            
        {
            // this.æøàzi©j.split('.')[0].length() > 11 or 
            //this.æøàzi©j.split('.')[1].length() > 2 
            //ÌêAáOðX[·éB 
            String[] l_strs = this.tradingAmtFrom.trim().split("\\.");
            if (l_strs[0].length() > 11 || l_strs[1].length() > 2)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02076,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " æøàzi©jÌlªs³Å·B:" + this.tradingAmtFrom); 
            }
        }
        
        //R|Sjthis.æøàzi©jÉ¬_ªÜÜêÈ¢êithis.æøàzi©j.indexOf('.') = -1 Ìêj         
        if (this.tradingAmtFrom.indexOf('.') == -1)
        {
            //this.æøàzi©j.length() > 11 ÌêAáOðX[·éB 
            if (WEB3StringTypeUtility.getNubmerLength(this.tradingAmtFrom) > 11)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02076,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " æøàzi©jÌlªs³Å·B:" + this.tradingAmtFrom); 
            }
        }
        
        //Sjæøàzij 
        //this.æøàzij != null ÌêAÈºÌ`FbNðs¤B
        if (!WEB3StringTypeUtility.isEmpty(this.tradingAmtTo))
        {            
            double l_dblTradingAmtTo = Double.parseDouble(this.tradingAmtTo);
            //S|Pj 
            //this.æøàzij < 0 ÌêAáOðX[·éB 
            if (l_dblTradingAmtTo < 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02077,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " æøàzijª}CiXÌlÅ·B:" + this.tradingAmtTo);
            }

            //S|Qjthis.æøàzijÉ¬_ªÜÜêéêithis.æøàzij.indexOf('.') >= 0 Ìêj 
            if (this.tradingAmtTo.indexOf('.') >= 0)
            {
                String[] l_strs = this.tradingAmtTo.trim().split("\\.");
                //this.æøàzij.split('.')[0].length() > 11 or 
                //this.æøàzij.split('.')[1].length() > 2 
                //ÌêAáOðX[·éB
                if (l_strs[0].length() > 11 || l_strs[1].length() > 2)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02078,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " æøàzijÌlªs³Å·:" + this.tradingAmtTo);
                }
            }
 

            //S|Rjthis.æøàzijÉ¬_ªÜÜêÈ¢êithis.æøàzij.indexOf('.') = -1 Ìêj 
            //this.æøàzij.length() > 11 ÌêAáOðX[·éB 
            if (this.tradingAmtTo.indexOf('.') == -1)
            {
                //this.æøàzi©j.length() > 11 ÌêAáOðX[·éB 
                if (WEB3StringTypeUtility.getNubmerLength(this.tradingAmtTo) > 11)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02078,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " æøàzijÌlªs³Å·:" + this.tradingAmtTo); 
                }
            }
            
            //S|Sjthis.æøàzi©j > this.æøàzijÌêAáOðX[·éB 
            if (l_dblTradingAmtFrom > l_dblTradingAmtTo)
            {                
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02079,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " æøàzi©jªæøàzijð´¦Üµ½:" + 
                    this.tradingAmtFrom + " > " + this.tradingAmtTo); 
            }

        }
         
        //Tj¥û¦ 
        //T|Pjthis.¥û¦ == nullÌêAáOðX[·éB 
        if (WEB3StringTypeUtility.isEmpty(this.collectRate))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02080,
                this.getClass().getName() + STR_METHOD_NAME,
                " ¥û¦ª¢üÍÅ·B"); 
        }        
        
        //T|Qjthis.¥û¦ < 0 ÌêAáOðX[·éB 
        if (WEB3StringTypeUtility.isMinus(this.collectRate))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02081,
                this.getClass().getName() + STR_METHOD_NAME,
                " ¥û¦ª}CiXÌlÅ·:" + this.collectRate); 
        }
        //T|Rjthis.¥û¦É¬_ªÜÜêéêithis.¥û¦.indexOf('.') >= 0 Ìêj 
        //this.¥û¦.split('.')[0].length() > 3 or this.¥û¦.split('.')[1].length() > 5 
        //ÌêAáOðX[·éB 
        if (this.collectRate.indexOf('.') >= 0)
        {
            String[] l_strs = this.collectRate.trim().split("\\.");

            if (l_strs[0].length() > 3 || l_strs[1].length() > 5)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02082,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " ¥û¦Ìlªs³Å·:" + this.collectRate);
            }
        }

        //T|Sjthis.¥û¦É¬_ªÜÜêÈ¢êithis.¥û¦.indexOf('.') = -1 Ìêj 
        //this.¥û¦.length() > 3 /ÌêAáOðX[·éB
        if (this.collectRate.indexOf('.') == -1)
        { 
            if (WEB3StringTypeUtility.getNubmerLength(this.collectRate) > 3)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02082,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " ¥û¦Ìlªs³Å·:" + this.collectRate); 
            }
        } 

        //UjtÁàz 
        //U|Pjthis.tÁàz == null ÌêAáOðX[·éB 
        if (WEB3StringTypeUtility.isEmpty(this.additionalAmt))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02083,
                this.getClass().getName() + STR_METHOD_NAME,
                " tÁàzª¢üÍÅ·B"); 
        } 
        
        //U|Qjthis.tÁàz < 0 ÌêAáOðX[·éB 
        if (WEB3StringTypeUtility.isMinus(this.additionalAmt))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02084,
                this.getClass().getName() + STR_METHOD_NAME,
                " tÁàzª}CiXÌlÅ·:" + this.additionalAmt); 
        }
        
        //U|Rjthis.tÁàzÉ¬_ªÜÜêéêithis.tÁàz.indexOf('.') >= 0 Ìêj         
        //this.tÁàz.split('.')[0].length() > 9 orthis.tÁàz.split('.')[1].length() > 5 
        //ÌêAáOðX[·éB
        if (this.additionalAmt.indexOf('.') >= 0)
        {
            String[] l_strs = this.additionalAmt.trim().split("\\.");

            if (l_strs[0].length() > 9 || l_strs[1].length() > 5)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02085,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " tÁàzÌlªs³Å·:" + this.additionalAmt);
            }
        }
         
        //U|Sjthis.tÁàzÉ¬_ªÜÜêÈ¢êithis.tÁàz.indexOf('.') = -1 Ìêj 
        //this.tÁàz.length() > 9 ÌêAáOðX[·éB
        if (this.additionalAmt.indexOf('.') == -1)
        {
            if (WEB3StringTypeUtility.getNubmerLength(this.additionalAmt) > 9)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02085,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " tÁàzÌlªs³Å·:" + this.additionalAmt);
            }
        } 

        log.exiting(STR_METHOD_NAME);       
    }
}
@
