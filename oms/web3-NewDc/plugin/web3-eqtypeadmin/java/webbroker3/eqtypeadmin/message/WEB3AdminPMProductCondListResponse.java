head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPMProductCondListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�������������\��ꗗ���X�|���X(WEB3AdminPMProductCondListResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE�������������\��ꗗ���X�|���X)<BR>
 * <BR>
 * �Ǘ��ҁE�������������\��ꗗ���X�|���X�N���X<BR>
 * <BR>
 * WEB3AdminPMProductCondListResponse<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminPMProductCondListResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_pm_product_cond_list";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * �i���ݓ����j<BR>
     * <BR>
     * ���ݓ���<BR>
     * <BR>
     * currentDate<BR>
     * <BR>
     */
    public Date currentDate;

    /**
     * �i����K���ꗗ�j<BR>
     * <BR>
     * ����K���̈ꗗ<BR>
     * <BR>
     * tradingRegulation list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondScheduleUnit[] tradingRegulationList = null;

    /**
     * �i��{���ꗗ�j<BR>
     * <BR>
     * ��{���̈ꗗ<BR>
     * <BR>
     * basicInfo list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondScheduleUnit[] basicInfoList = null;

    /**
     * �i�M�p�������ꗗ�j<BR>
     * <BR>
     * �M�p�������̈ꗗ<BR>
     * <BR>
     * stockMarginInfo list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondScheduleUnit[] stockMarginInfoList = null;

    /**
     * �i�ϑ��ۏ؋����ꗗ�j<BR>
     * <BR>
     * �ϑ��ۏ؋����̈ꗗ<BR>
     * <BR>
     * depositRate list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondScheduleUnit[] depositRateList = null;

    /**
     * �i��p�L���،����ꗗ�j<BR>
     * <BR>
     * ��p�L���،����̈ꗗ<BR>
     * <BR>
     * substituteSecurityInfo list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondScheduleUnit[] substituteSecurityInfoList = null;

    /**
     * �i�l�i���ꗗ�j<BR>
     * <BR>
     * �l�i���̈ꗗ<BR>
     * <BR>
     * priceInfo list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondScheduleUnit[] priceInfoList = null;

    /**
     * @@roseuid 41FD92020119
     */
    public WEB3AdminPMProductCondListResponse()
    {

    }

    /**
     *
     * @@param l_request WEB3GenRequest
     */
    public WEB3AdminPMProductCondListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
