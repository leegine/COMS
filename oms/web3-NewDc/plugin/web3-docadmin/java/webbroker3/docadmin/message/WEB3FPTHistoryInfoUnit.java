head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FPTHistoryInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : à¤@@ðt{ðîñ(WEB3FPTHistoryInfoUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/29 g (u) VKì¬
Revision History : 2008/01/25 ünm (u) dlÏXEfNo.022
Revision History : 2008/03/17 égC· (u) dlÏXEfNo.045
*/

package webbroker3.docadmin.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (à¤@@ðt{ðîñ)<BR>
 * à¤@@ðt{ðîñNX<BR>
 *
 * @@author g
 * @@version 1.0
 */
public class WEB3FPTHistoryInfoUnit extends Message
{

    /**
     * (XR[h)<BR>
     * XR[h<BR>
     */
    public String branchCode;

    /**
     * (ÚqR[h)<BR>
     * ÚqR[h<BR>
     */
    public String acceptCode;

    /**
     * (Úq¼)<BR>
     * Úq¼<BR>
     */
    public String acceptName;

    /**
     * (Á¿¼)<BR>
     * Á¿¼<BR>
     */
    public String productName;

    /**
     * (Êæª)<BR>
     * Êæª<BR>
     */
    public String documentDiv;

    /**
     * (Ê¼Ì)<BR>
     * Ê¼Ì<BR>
     */
    public String documentNames;

    /**
     * (dqµÁ¿R[h)<BR>
     * dqµÁ¿R[h<BR>
     */
    public String batoProductCode;

    /**
     * (Êðtú)<BR>
     * Êðtú<BR>
     */
    public Date docuDeliDate;

    /**
     * (ÊíÞR[h)<BR>
     * ÊíÞR[h<BR>
     */
    public String documentCategory;

    /**
     * (ÊíÞ¼Ì)<BR>
     * ÊíÞ¼Ì<BR>
     */
    public String documentCategoryName;

    /**
     * (ÊíÞÊÔ)<BR>
     * ÊíÞÊÔ<BR>
     */
    public String documentCategoryNumber;

    /**
     * (ítO)<BR>
     * ítO<BR>
     */
    public String deleteFlg;

    /**
     * (ÝÈµðtú)<BR>
     * ÝÈµðtú<BR>
     */
    public Date deemedDeliveryDate;

    /**
     * (XVÒR[h)<BR>
     * XVÒR[h<BR>
     */
    public String updaterCode;

    /**
     * (ì¬út)<BR>
     * ì¬út<BR>
     */
    public Date createDate;

    /**
     * (XVút)<BR>
     * XVút<BR>
     */
    public Date updateTimeStamp;

    /**
     * @@roseuid 46FDDD3D00EA
     */
    public WEB3FPTHistoryInfoUnit()
    {

    }
}
@
